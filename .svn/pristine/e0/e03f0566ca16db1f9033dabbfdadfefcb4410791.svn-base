<template>
    <div class="banner" ref="banner_Dom">
        <div class="banner-view" ref="banner_view">
            <div class="imageBox1" v-show="showOdd" ref="image_box1"></div>
            <div class="imageBox2" v-show="!showOdd" ref="image_box2"></div>
        </div>
        <div class="banner-progress">
            <i class="progress1" ref="progress_dom1"></i>
        </div>
    </div>
</template>

<script>

  var $ = window.jQuery;
  var vm;

  export default {
    name: 'banner2',
    props: {},
    data() {
      return {
        container: null,

        /*进度条*/
        progress: null,
        progressDom1: null,
        /*容器大小*/
        size: {
          width: 864,
          height: 1536,
        },
        /*图片展示框*/
        view: null,

        /*图片组*/
        imageBox2: null,
        imageBox1: null,
        currImageBox: null,

        /*索引位置*/
        index: 0,

        /*切换到下一张图的等待时间*/
        fnTime: 3000,

        /*传入的图片列表*/
        imagesList: [],
        /*将播放的图片元素数组*/
        images: [],
        showOdd: true,

        aImageProcessLen: 1,
        timer: null
      }
    },
    created: function () {
    },
    mounted: function () {
      /* eslint-disable */
      console.log(" *轮播图动画组件banner#2初始化完成...")
      vm = this;
      this.init();
    },
    computed: {},
    methods: {
      init: function () {
        this.container = this.$refs.banner_Dom;
        this.view = this.$refs.banner_view;
        this.imageBox1 = this.$refs.image_box1;
        this.imageBox2 = this.$refs.image_box2;
        this.progressDom1 = this.$refs.progress_dom1;
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

            this.images.length && (this.aImageProcessLen = this.size.width / this.images.length);
            this.imageBox1.style.width = (this.size.width * this.images.length) + "px";
            this.imageBox2.style.width = (this.size.width * this.images.length) + "px";
            for (var i in this.images) {
              this.images[i].style.width = this.size.width + "px";
              this.images[i].style.height = this.size.height + "px";
            }
          }
        }
      },
      /*播放轮播图*/
      playImages: function (imgList) {
        this.index = 0;
        this.imagesList = imgList;
        this.images = [];
        clearTimeout(vm.timer);

        if (this.showOdd) {
          this.currImageBox = this.imageBox2;
          this.imageBox2.innerHTML = "";
          this.imageBox2.style.left = "0px";
        } else {
          this.currImageBox = this.imageBox1;
          this.imageBox1.innerHTML = "";
          this.imageBox1.style.left = "0px";
        }

        console.log("执行了banner2.playImages方法：", this.imagesList);
        this.loadImages();
      },
      /*滑动播放图片*/
      show: function () {

        //进度条提示
        vm.progressDom1.style.width = ((vm.index + 1) * vm.aImageProcessLen) + "px";

        //如果索引超出图片范围，通知父组件素材播放结束
        if (vm.index >= vm.images.length - 1) {
          vm.$emit("listenSlidePlayEnd");
          return;
        }

        vm.timer = setTimeout(function () {
          vm.index++;
          $(vm.currImageBox).animate({left: (-vm.index * vm.size.width) + "px"}, 500,"linear");
          vm.show();
        }, vm.fnTime);

      },
      loadImages: function () {
        //如果图片加载完
        if (vm.index >= vm.imagesList.length) {

          //如果当前展示的是第一组图
          if (vm.showOdd) {
            vm.imageBox2.style.width = (vm.size.width * vm.images.length) + "px";
            for (var i in vm.images) {
              vm.imageBox2.appendChild(vm.images[i]);
            }
          } else {
            vm.imageBox1.style.width = (vm.size.width * vm.images.length) + "px";
            for (var i in vm.images) {
              vm.imageBox1.appendChild(vm.images[i]);
            }
          }

          vm.index = 0;
          vm.aImageProcessLen = vm.size.width / vm.images.length;
          vm.progressDom1.style.width = 0;
          vm.showOdd = !vm.showOdd;
          vm.show();
          return;
        }

        var oImage = new Image(vm.size.width, vm.size.height);

        oImage.onerror = function () {
          vm.loadImages();
        }

        oImage.onload = function () {
          vm.images.push(oImage);
          vm.loadImages();
        }

        oImage.src = this.imagesList[this.index++];

      }

    }
  }


</script>


<style scoped>
    @import '../assets/css/banner.css';

    .progress1 {
        width: 0;
        background-color: #ffc300;
    }

    .imageBox img {
        float: left;
    }

    .imageBox1 {
        position: absolute;
        left: 0;
        top: 0;
        z-index: 12;
    }

    .imageBox2 {
        position: absolute;
        left: 0;
        top: 0;
        z-index: 13;
    }
</style>