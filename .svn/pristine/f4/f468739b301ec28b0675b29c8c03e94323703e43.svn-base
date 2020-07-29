<template>
    <div id="christmas" class="christmas">
        <canvas class="flare"></canvas>
        <canvas class="snow"></canvas>
        <canvas class="flake"></canvas>
        <canvas class="mao"></canvas>
        <canvas class="sdlr1"></canvas>
    </div>
</template>

<script>

  var $ = window.jQuery;

  export default {
    name: 'christmas',
    props: {},
    data: function () {
      return {}
    },
    created: function () {
    },
    mounted: function () {
      /* eslint-disable */
      console.log("*圣诞节雪花控件初始化完成...")
      this.anime();
    },
    methods: {
      anime: function () {

        $(function () {
          /*圆球*/
          $("canvas.flare").let_it_snow({
            windPower: 0,
            speed: 1,
            rgb: "241,158,194",
            size: 36,    //圆形半径
            opacity: 0.1,
            count: 24,
          });

          /*小雪花*/
          $("canvas.snow").let_it_snow({
            windPower: 3,
            speed: 2,
            count: 150,
            size: 5,
          });

          /*大雪花*/
          $("canvas.flake").let_it_snow({
            windPower: -3,
            speed: 2,
            count: 6,
            size: 40,
            opacity: 1,
            imageSrc: "static/images/christmas/snowflake.png",
          });

          /*小红帽*/
          $("canvas.mao").let_it_snow({
            windPower: -3,
            speed: 1,
            count: 5,
            size: 40,
            opacity: 0.9,
            imageSrc: "static/images/christmas/maoZi1.png",
          });

          /*小红帽2*/
          /*          $("canvas.mao2").let_it_snow({
                      windPower: 3,
                      speed: 1,
                      count: 5,
                      size: 40,
                      opacity: 0.8,
                      imageSrc: "static/images/christmas/maoZi2.png",
                    });*/

          /*圣诞老人*/
          $("canvas.sdlr1").let_it_snow({
            windPower: 0,
            speed: 2,
            count: 2,
            size: 120,
            opacity: 0.8,
            imageSrc: "static/images/christmas/ShengDanLaoRen1.png",
          });

        });
      }
    }

  }
</script>


<style scoped>

    .christmas {
        position: relative;
        height: 100%;
        width: 100%;
    }

    .christmas canvas {
        display: block;
        width: 100%;
        height: 100%;
        top: 0;
        left: 0;
        position: absolute;
    }

    canvas.flare {
        opacity: 0.5;
    }
</style>