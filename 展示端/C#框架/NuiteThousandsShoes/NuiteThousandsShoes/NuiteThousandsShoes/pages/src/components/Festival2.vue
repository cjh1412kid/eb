<template>
    <div class="festival">
        <div id="staticMaterial">
            <div id="m1" class="static_material"></div>
            <div id="m2" class="static_material"></div>
            <div id="m3" class="static_material"></div>
            <div id="m4" class="static_material"></div>
            <div id="m5" class="static_material"></div>
            <div id="m6" class="static_material"></div>
            <div id="m7" class="static_material"></div>
            <div id="m8" class="static_material"></div>
        </div>
        <div id="animationMaterial" ref="animation_material_wrap">
            <canvas id="canvas-default"></canvas>
        </div>
    </div>
</template>

<script>

  var $ = window.jQuery;

  /* eslint-disable */
  export default {
    name: 'Festival',
    components: {},
    props: {
      festivalAnimation: Object
    },
    data: function () {
      return {}
    },
    created: function () {
    },
    mounted: function () {
      console.log("初始化自定义节日动画控件 ...")
      this.festivalAnimation && this.initAnimation();
    },
    methods: {
      initAnimation: function () {
        var animateContentEntities = this.festivalAnimation.animateContentEntities;
        animateContentEntities && this.initAnimateContent(animateContentEntities);
        var staticContentEntities = this.festivalAnimation.staticContentEntities;
        staticContentEntities && this.initStaticContent(staticContentEntities)

      },
      initStaticContent: function (arr) {
        // console.log("initStaticContent: ", arr)

        var staticMaterial;
        var material;
        for (var i in arr) {
          staticMaterial = arr[i];
          var suffix = staticMaterial.position;
          material = staticMaterial.material;
          if (material) {
            var imageSrc = "animate://" + material.path;
            var oImage = new Image();
            oImage.src = imageSrc;
            var imageWrapID = "#m" + suffix;
            $(imageWrapID).css({
              width: staticMaterial.wPer + "%",
              height: staticMaterial.hPer + "%",
              opacity: staticMaterial.opacity / 10,
            }).append(oImage);
          }
        }

      },
      initAnimateContent: function (arr) {
        // console.log("initAnimateContent: ", arr)
        var animation;
        var animationWrap = this.$refs.animation_material_wrap;
        var $animationWrap = $(animationWrap);
        var material;
        for (var i in arr) {
          animation = arr[i];
          material = animation.material;
          if (material) {
            var imageSrc = "animate://" + material.path;
            var canvas = document.createElement("CANVAS");
            $animationWrap.append(canvas);
            $(canvas).let_it_snow({
              windPower: animation.windPower,
              speed: animation.speed,
              count: animation.count,
              size: animation.size,
              opacity: animation.opacity / 10,
              imageSrc: imageSrc
            });
          }
        }
      },
      defaultAnimate: function () {
        /*圆球*/
        $("canvas#canvas-default").let_it_snow({
          windPower: 0,
          speed: 1,
          rgb: "241,158,194",
          size: 36,    //圆形半径
          opacity: 0.09,
          count: 24,
        });
      }

    }

  }
</script>


<style>
    .festival {
        position: absolute;
        width: 100%;
        height: 100%;
        left: 0px;
        top: 0px;
        z-index: 999;
    }

    #staticMaterial {
        position: absolute;
        width: 100%;
        height: 100%;
        left: 0px;
        top: 0px;
    }

    #staticMaterial .static_material {
        position: absolute;
    }

    .static_material img {
        width: 100%;
        height: 100%;
    }

    #animationMaterial {
        position: absolute;
        width: 100%;
        height: 100%;
        left: 0px;
        top: 0px;
    }

    #animationMaterial canvas {
        position: absolute;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
    }

    /*顶部*/
    #m1, #m2, #m3 {
        top: 0;
    }

    /*底部*/
    #m6, #m7, #m8 {
        bottom: 0;
    }

    /*左右两侧*/
    #m4, #m5 {
        top: 20%;
    }

    /*左侧*/
    #m1, #m4, #m6 {
        left: 0;
    }

    /*右侧*/
    #m3, #m5, #m8 {
        right: 0;
    }

    /*中部宽度*/
    #m2, #m7 {
        left: 30%;
    }
</style>