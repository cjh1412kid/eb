<template>
    <div class="springFestival">
        <canvas id="pig1"></canvas>
        <canvas id="pig2"></canvas>
        <canvas id="redPacket1"></canvas>
        <canvas id="redPacket2"></canvas>
    </div>
</template>

<script>
  /* eslint-disable */
  var $ = window.jQuery;

  export default {
    name: 'spring',
    props: {},
    data: function () {
      return {}
    },
    created: function () {
    },
    mounted: function () {
      console.log("春节动画组件初始化完成...");
      this.anime();
    },
    methods: {
      anime: function () {

        $(function () {

          $("canvas#pig1").let_it_snow({
            windPower: 0,
            speed: 3,
            count: 6,
            size: 60,
            opacity: 0.9,
            imageSrc: "static/images/spring/pig.png",
          });

          $("canvas#pig2").let_it_snow({
            windPower: 0,
            speed: 3,
            count: 6,
            size: 60,
            opacity: 0.9,
            imageSrc: "static/images/spring/pig2.png",
          });

          $("canvas#redPacket1").let_it_snow({
            windPower: 3,
            speed: 3,
            count: 6,
            size: 80,
            opacity: 0.9,
            imageSrc: "static/images/spring/red-01.png",
          });

          $("canvas#redPacket2").let_it_snow({
            windPower: -3,
            speed: 3,
            count: 6,
            size: 80,
            opacity: 0.9,
            imageSrc: "static/images/spring/red-02.png",
          });


        });
      }
    }

  }
</script>


<style scoped>

    .springFestival {
        position: relative;
        height: 100%;
        width: 100%;
    }

    .springFestival canvas {
        display: block;
        width: 100%;
        height: 100%;
        top: 0;
        left: 0;
        position: absolute;
    }

</style>