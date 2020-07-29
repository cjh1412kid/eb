<template>
    <div class="festival">
        <component :is="okName"></component>
    </div>
</template>

<script>
  import christmas from './festival/christmas.vue'
  import yuandan from './festival/yuandan.vue'
  import newyear from './festival/spring.vue'

  export default {
    name: 'Festival',
    components: {
      christmas,
      yuandan,
      newyear
    },
    props: {
      festivalName: String
    },
    data: function () {
      return {
        /*正确的节日动画名*/
        okName: null
      }
    },
    created: function () {
    },
    mounted: function () {
      this.validateFestivalName();
    },
    methods: {
      validateFestivalName: function () {
        var fCompoArr = [];
        for (var key in this.$options.components) {
          fCompoArr.push(key);
        }
        var fCompoArr2 = fCompoArr.slice(0, fCompoArr.length - 4);
        for (var i in fCompoArr2) {
          // console.log(fCompoArr2[i], this.festivalName)
          if (fCompoArr2[i] == this.festivalName) {
            this.okName = fCompoArr2[i];
            return;
          }
        }
      }
    }

  }
</script>


<style scoped>
    .festival {
        position: absolute;
        width: 100%;
        height: 100%;
        left: 0px;
        top: 0px;
        z-index: 999;
    }
</style>