<template>
    <div ref="hot_box">
    </div>
</template>

<script>

  var $ = window.jQuery;

  var vm;
  export default {
    name: 'HotPlay',
    props: {
      originalImages: {type: Array, default: () => [], required: true},
      showType: {type: Number, default: () => 0, required: true}
    },
    data: function () {
      return {
        container: null,
        /*当前播放的一组图*/
        currBar: null,

        hIndex: 0,
        currSquareBox: null,

        /**新数据缓存*/
        tmpBar: null,
        tmpSquareBox: [],
        tmpImageData: [],
        imageData: [],

        /*移动一步距离px*/
        stepLen: 330,
        squareWidth: 300,

        /*图片过滤器索引*/
        fIndex: 0,
        imagesPool: [],

        normalModelTimer: null,
        time1: 2000,
        playModelTimer: null,
        time2: 1000,
        /*滑动动画时间*/
        moveTime: 500,
        isFirst: true,
      }
    },
    created: function () {
    },
    mounted: function () {
      console.log("前十大轮播组件#3 初始化完成...");
      this.container = this.$refs.hot_box;
      vm = this;
    },
    methods: {
      normalModel: function () {

        if (vm.hIndex < $(".bar_curr").children().length) {

          if (vm.hIndex == 0) {
            var leftV = (this.container.offsetWidth - vm.squareWidth) / 2;
            $(".bar_curr").animate({left: leftV + "px"}, vm.moveTime, "linear", function () {
              if (vm.showType != 2) {
                vm.hIndex++;
                vm.normalModelTimer = setTimeout(vm.normalModel, vm.time1);
              }
            });

          } else {
            $(".bar_curr").animate({left: "-=" + vm.stepLen + "px"}, vm.moveTime, "linear", function () {
              if (vm.showType != 2) {
                vm.hIndex++;
                vm.normalModelTimer = setTimeout(vm.normalModel, vm.time1);
              }
            });
          }

        } else {
          var curr = $(".bar_curr");
          curr.removeClass("bar_curr").addClass("bar_next");
          curr.animate({left: -curr.outerWidth() + "px"}, vm.moveTime, "linear", function () {
            $(this).css("left", "100%");
          });
          curr.siblings().addClass("bar_curr").removeClass("bar_next");
          vm.hIndex = 0;
          vm.normalModel();
        }
      },
      playModel: function () {

        var curr = $(".bar_curr");
        var currIndex = vm.hIndex;
        if (currIndex < curr.children().length) {
          if (currIndex == 0) {

            var leftV = (this.container.offsetWidth - vm.squareWidth) / 2;
            curr.animate({left: leftV + "px"}, vm.moveTime, "linear", function () {
              $(this).children().eq(currIndex).addClass("square_curr");
              vm.hIndex++;
              //播放当前商品素材
              vm.$emit("listenCurrImage", vm.imageData[currIndex]);
            });

          } else {
            curr.animate({left: "-=" + vm.stepLen + "px"}, vm.moveTime, "linear", function () {
              $(this).children().eq(currIndex - 1).removeClass("square_curr");
              $(this).children().eq(currIndex).addClass("square_curr");
              vm.hIndex++;
              vm.$emit("listenCurrImage", vm.imageData[currIndex]);
            });
          }

        } else {
          curr.removeClass("bar_curr").addClass("bar_next");
          curr.siblings().addClass("bar_curr").removeClass("bar_next");
          curr.siblings().animate({left: -curr.outerWidth() + "px"}, vm.moveTime, function () {
            $(this).css("left", "100%");
          });
          vm.hIndex = 0;
          vm.$emit("listenHotPlayEnd");
        }
      },
      addNewBar: function (className) {
        var bar = this.tmpBar.cloneNode(true);
        bar.classList.add(className);
        this.container.appendChild(bar);
      },
      initEnv: function () {
        //恢复初始状态
        this.hIndex = 0;
        this.imageData = this.tmpImageData;
        this.stopRotation();

        if (this.isFirst) {
          this.isFirst = false;
        } else {
          //数据发生更新,重置轮播图
          this.stopRotation();
          this.container.innerHTML = "";
        }
        this.addNewBar("bar_curr");
        this.addNewBar("bar_next");

        this.startPlay();
      },
      startPlay: function () {
        if (!this.tmpBar) return;

        if (this.showType == 2) {
          this.playModel()
        } else {
          this.normalModel()
        }
      },
      imagesLoader: function () {
        var wrap = document.createElement("div");
        for (var i in this.tmpSquareBox) {
          wrap.appendChild(this.tmpSquareBox[i]);
        }
        wrap.style.width = this.tmpSquareBox.length * this.stepLen + "px";
        wrap.classList.add("hot_box_wrap");
        this.tmpBar = wrap;

        this.initEnv();
      },

      /*过滤不合格图片，生成图片元素*/
      imagesFilter: function () {
        if (this.fIndex >= this.imagesPool.length) {
          this.imagesLoader();
          this.fIndex = 0;
          return;
        }

        var currImage = this.imagesPool[this.fIndex];
        var oImage = new Image();
        oImage.src = currImage.path;

        oImage.onload = function () {
          var square = document.createElement("div");
          var title = document.createElement("span")
          title.innerText = currImage.title;
          square.appendChild(this);
          square.appendChild(title);
          vm.tmpSquareBox.push(square);
          vm.tmpImageData.push(currImage);
          vm.fIndex++;
          vm.imagesFilter();
        }
        oImage.onerror = function () {
          vm.fIndex++;
          vm.imagesFilter();
        }

      },
      /*对图片原数据进行加工，使符合规范*/
      imgFactory: function (imgArr) {
        //格式化源数据
        imgArr.forEach((item, index) => {
          item.path = window.LocalPath + "/Resource/ShoesResource/ShoesIcons/" + item.PeriodSeq + "/" + item.ShoesID + ".jpg";
          item.title = "top" + (index + 1);
          item.PlayData && item.PlayData.forEach(item2 => {
            item2.path = window.LocalPath + item2.RelativeURL + "/" + item2.name;
          });
        })

        this.imagesPool = imgArr;
        this.tmpSquareBox = [];
        this.tmpImageData = [];
        this.imagesFilter();
      },
      continuePlayNext: function () {
        this.playModel();
      },
      stopRotation: function () {
        clearTimeout(vm.normalModelTimer);
        clearTimeout(vm.playModelTimer);
      },
      stopPlayModel: function () {
        clearTimeout(vm.playModelTimer);
        $(".square_curr").removeClass("square_curr");
      }
    },
    watch: {
      originalImages: {
        handler: function (newArr) {
          newArr && newArr.length > 0 && this.imgFactory(newArr);
        },
        deep: true,
      },
      showType: function (val, old) {
        if (!this.tmpBar) return;

        if (val == 2) {
          if (old == 0) this.hIndex = 0;
          clearTimeout(vm.normalModelTimer);
          this.playModel();
        } else if (old == 2) {
          this.stopPlayModel();
          this.normalModel()
        }
      }

    }
  }
</script>


<style>

    .hot_box_wrap {
        position: absolute;
        width: 666px;
        height: 222px;
        left: 100%;
        /*background-color: gray;*/
        top: 0;
        bottom: 0;
        margin: auto;
        font-size: 0;
    }

    .hot_box_wrap > div {
        display: inline-block;
        width: 300px;
        height: 100%;
        background-color: pink;
        text-align: center;
        margin-right: 30px;
    }

    .square_curr {
        background-color: gold !important;
        font-weight: bold;
    }

    .hot_box_wrap img {
        margin-top: 20px;
        width: 90%;
    }

    .hot_box_wrap span {
        display: block;
        font-size: 16px;
        font-family: "微软雅黑";
        margin-top: 10px;
        color: #000;
    }

</style>