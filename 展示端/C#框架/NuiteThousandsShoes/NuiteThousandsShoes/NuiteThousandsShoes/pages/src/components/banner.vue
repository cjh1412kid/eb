<template>
    <div class="banner" ref="banner_Dom">
        <div class="banner-view" ref="banner_view"></div>
        <div class="banner-number" ref="banner_num"></div>
        <div class="banner-progress" ref="banner_progress">
            <i class="progress1" ref="progress_dom1"></i>
            <i class="progress2" ref="progress_dom2"></i>
        </div>
    </div>
</template>

<script>

  export default {
    name: 'banner',
    props: {
      // imgArr: {type: Array, default: () => [], required: true},
    },
    data() {
      return {
        container: null,
        /*容器大小*/
        size: {
          width: 864,
          height: 1536,
        },

        /*网格数据*/
        view: null,
        viewBox: [],

        /*位置提示按钮*/
        num: null,
        numBox: [],

        /*进度条*/
        progress: null,
        progressDom1: null,
        progressDom2: null,

        /*栅格 行*列*/
        grid: {
          line: 10,
          list: 5
        },

        /*索引位置*/
        preIndex: 0,
        index: 0,

        /*进度条执行时间，执行完切换下一张图*/
        fnTime: 3500,
        /*栅格动画时间 , fnTime > boxTime*/
        boxTime: 1500,


        /*栅格动画结束的时间，用于判断栅格动画是否结束*/
        activeTime: new Date(),

        /*图片列表*/
        imagesList: []
      }
    },
    created: function () {
    },
    mounted: function () {
      this.init();
      /* eslint-disable */
      console.log(" *轮播图组件banner初始化完成...")
    },
    computed: {
      gridWidth: function () {
        return this.size.width / this.grid.list;
      },
      gridHeight: function () {
        return this.size.height / this.grid.line;
      },

    },
    methods: {
      init: function () {
        this.container = this.$refs.banner_Dom;
        this.view = this.$refs.banner_view;
        this.num = this.$refs.banner_num;
        this.progress = this.$refs.banner_progress;
        this.progressDom1 = this.$refs.progress_dom1;
        this.progressDom2 = this.$refs.progress_dom2;

        console.log("banner.Container", this.container, this.container.clientWidth, this.container.clientHeight)

        this.size.width = this.container.clientWidth || this.size.width;
        this.size.height = this.container.clientHeight || this.size.height;

        this.resize();

      },
      /*宽高改变后触发*/
      resize: function (width, height) {

        if (width && height) {
          if (width == this.size.width && height == this.size.height) {
            return;
          } else {
            console.log("重置banner.size >>> width:%s , height:%s", this.size.width, this.size.height);
            this.size.width = width;
            this.size.height = height;
          }
        }

        var newI;
        this.view.innerHTML = "";
        this.viewBox = [];
        for (var i = 0, iL = this.grid.line; i < iL; i++) {
          for (var j = 0, jL = this.grid.list; j < jL; j++) {

            newI = document.createElement('i');

            setCss(newI, {
              width: this.gridWidth + 'px',
              height: this.gridHeight + 'px',
              left: 0,
              top: 0,
              opacity: 1, //不透明
              // backgroundImage: 'url("' + this.imagesList[0] + '")',
              backgroundSize: this.size.width + 'px ' + this.size.height + 'px',
              backgroundPosition: this.gridWidth * -j + 'px ' + this.gridHeight * -i + 'px'
            });

            this.view.appendChild(newI);
            this.viewBox.push(newI);
          }
        }

      },
      /*播放轮播图*/
      playImages: function (imgList) {
        this.index = 0;
        this.preIndex = 0;
        this.imagesList = imgList;

        console.log("执行了banner.playImages方法：", this.imagesList)

        //播放位置按钮
        this.num.innerHTML = "";
        this.numBox = [];
        var oI;
        for (var n = 0, nL = this.imagesList.length; n < nL; n++) {
          oI = document.createElement('i');
          oI.setIndex = n;
          this.num.appendChild(oI);
          this.numBox.push(oI);
        }

        this.show();
      },
      /*连续播放图片*/
      show: function () {
        //栅格动画未完成，不能继续执行
        // if (new Date() < this.activeTime) return;

        //如果索引超出图片范围，通知父组件素材播放结束
        if (this.index >= this.imagesList.length) {
          this.$emit("listenSlidePlayEnd");
          return;
        }

        this.numBox[this.preIndex].className = '';
        this.numBox[this.index].className = 'on';

        this.preIndex = this.index;

        //进度条2执行完播放下一张
        setCss(this.progressDom2, {width: 0});
        anime(this.progressDom2, {width: this.size.width}, this.fnTime,
          function () {
            this.show();
          }.bind(this));

        this.viewBoxAnimation(this.imagesList[this.index]);

        this.index++;
      },
      /*执行栅格动画*/
      viewBoxAnimation: function (image) {
        var activeTime = 0;

        var startTime, endTime, obj;
        for (var i = 0, iL = this.viewBox.length; i < iL; i++) {

          startTime = this.getTypeTime();
          endTime = this.getTypeTime();
          obj = this.viewBox[i];
          obj.style.backgroundImage = 'url("' + image + '")';

          //栅格动画时间
          activeTime = Math.max(activeTime, startTime[1] + endTime[1]);

          anime(obj,
            {
              left: Math.ceil(Math.random() * this.size.width * 2 - this.size.width),
              top: Math.ceil(Math.random() * this.size.height * 2 - this.size.height),
              opacity: 0
            },
            startTime[0],
            function (obj) {

              anime(obj,
                {
                  left: 0,
                  top: 0,
                  opacity: 1
                },
                endTime[0]);

            }.bind(this, obj));
        }

        //栅格结束运动的时间
        // this.activeTime = new Date(new Date().getTime() + activeTime);

        //栅格动画进度条
        setCss(this.progressDom1, {width: 0});
        anime(this.progressDom1, {width: this.size.width}, activeTime);
      },
      getTypeTime: function () {
        var timer = [];
        timer.push(this.boxTime / 4 + Math.random() * this.boxTime / 4);
        timer.push(timer[0]);
        return timer;
      },
      setProgress: function () {
        setCss(this.progressDom1, {width: 0});

        anime(this.progressDom1,
          {width: this.size.width},
          this.fnTime,
          function () {
            this.show();
          }.bind(this));
      }


    },


  }


  /**
   * 设置样式
   * @param obj 元素对象
   * @param css css样式
   */
  function setCss(obj, css) {
    for (var key in css) {
      if (key == 'opacity') {
        obj.style.opacity = key;
        obj.style.filter = "alpha(opacity=" + (css[key] * 100) + ")";
      } else {
        obj.style[key] = css[key];
      }
    }
  }


  function anime(obj, attr, endTime, callback) {

    (obj.timer) && cancelAnimationFrame(obj.timer);

    var cssJosn = obj.currentStyle || getComputedStyle(obj, null),
      start = {}, end = {}, goTime;

    //设置初始属性值和结束属性值
    for (var key in attr) {

      if (attr[key] != parseFloat(cssJosn[key])) {

        start[key] = parseFloat(cssJosn[key]);
        end[key] = attr[key] - start[key];
      }
    }

    goTime = new Date();

    if (endTime instanceof Array) {

      (function delayFn() {

        if ((new Date() - goTime) >= endTime[0]) {

          endTime = endTime[1];
          goTime = new Date();
          ref();
        } else {

          obj.timer = requestAnimationFrame(delayFn);
        }
      })();
    } else {

      ref();
    }


    function ref() {

      var prop = (new Date() - goTime) / endTime;

      (prop >= 1) ? prop = 1 : obj.timer = requestAnimationFrame(ref);
      for (var key in start) {

        var val = -end[key] * prop * (prop - 2) + start[key];

        if (key == 'opacity') {

          obj.style.opacity = val;
          obj.style.filter = "alpha(opacity=" + (val * 100) + ")";
        } else {

          obj.style[key] = val + 'px';
        }
      }

      (prop === 1) && callback && callback.call(obj);
    }
  }
</script>


<style scoped>
    @import '../assets/css/banner.css';

    .progress1 {
        width: 0;
        background-color: #00c3ff;
    }

    .progress2 {
        width: 0;
        background-color: #ffc300;
    }

</style>