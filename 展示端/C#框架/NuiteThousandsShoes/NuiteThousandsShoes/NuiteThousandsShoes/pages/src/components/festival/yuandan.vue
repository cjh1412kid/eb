<template>
    <div class="christmas">
        <canvas class="flare"></canvas>
        <canvas class="money1"></canvas>
        <canvas class="money3"></canvas>
        <canvas class="redPacket1"></canvas>
        <canvas class="redPacket2"></canvas>
    </div>
</template>

<script>
  /* eslint-disable */
  var $ = window.jQuery;

  export default {
    name: 'yuandan',
    props: {},
    data: function () {
      return {}
    },
    created: function () {
    },
    mounted: function () {
      console.log("元旦动画组件初始化完成...");
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
            interaction: false,
          });

          $("canvas.money1").let_it_snow({
            windPower: -2,
            speed: 3,
            count: 5,
            size: 40,
            opacity: 0.8,
            imageSrc: "static/images/yuandan/money1.png",
          });


          $("canvas.money3").let_it_snow({
            windPower: 2,
            speed: 1,
            count: 3,
            size: 40,
            opacity: 0.8,
            imageSrc: "static/images/yuandan/money3.png",
          });

          $("canvas.redPacket1").let_it_snow({
            windPower: -2,
            speed: 3,
            count: 2,
            size: 50,
            opacity: 0.8,
            imageSrc: "static/images/yuandan/redPacket1.png",
          });

          $("canvas.redPacket2").let_it_snow({
            windPower: 2,
            speed: 3,
            count: 3,
            size: 50,
            opacity: 0.8,
            imageSrc: "static/images/yuandan/redPacket2.png",
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