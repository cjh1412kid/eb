<template>
    <div class="imageBox">
        <component :is="componentId" @listenSlidePlayEnd="slidePlayEnd" ref="image_comp"></component>
        <span class="img-title" v-text="imgTitle" ref="title_dom"></span>
        <!--音频-->
        <audio ref="audio_dom"></audio>
    </div>
</template>

<script>
  /* eslint-disable */

  import banner from './banner.vue'
  import banner2 from './banner2.vue'
  import $ from 'jquery'

  export default {
    name: 'ImagePlay',
    components: {
      banner,
      banner2
    },
    props: {
      showType: Number,
      audios: {type: Array, default: () => [], required: true}
    },
    data: function () {
      return {
        /*动态组件名*/
        componentId: "banner2",
        /*播放音频当前索引*/
        audioIndex: 0,
        audioDom: null,
        audioExist: false,
        imgTitle: ""
      }
    },
    created: function () {
    },
    mounted: function () {
      console.log("轮播图框架组件初始化完成")
      this.audioDom = this.$refs.audio_dom;
      this.bindAudioEvent();
    },
    computed: {},
    methods: {
      /*素材播放结束*/
      slidePlayEnd: function () {
        console.log("ImagePlay素材播放结束");
        this.$emit("listenImagePlayEnd");
      },
      bindAudioEvent: function () {
        var vm = this;
        this.audioDom.onloadeddata = function () {
          vm.audioExist = true;
          //加载到音频开始播放
          vm.showType != 0 && this.play();
        }
        this.audioDom.onerror = function () {
          //加载下一首
          vm.loadNextAudio();
        }
        this.audioDom.onended = function () {
          //播放下一首
          vm.loadNextAudio();
        }
        this.audioDom.onpause = function () {
          this.currentTime = 0;
        }
      },
      loadNextAudio: function () {
        //获得下一首索引
        if (this.audioIndex + 1 < this.audios.length) {
          this.audioIndex++;
          this.audioDom.src = this.audios[this.audioIndex].path;
        } else {
          this.audioIndex = 0;
          //音频数组播放完，且没有可播放视频，不处理
          if (this.audioExist) {
            //存在可播放音频，继续循环加载
            this.audioDom.src = this.audios[this.audioIndex].path;
          }
        }

      },
      continuePlayMusic: function () {
        if (this.audios.length == 0) return;

        if (this.audioDom.readyState > 0) {
          this.audioDom.play();
        } else {
          this.restartPlayMusic();
        }
      },
      restartPlayMusic: function () {
        this.audioExist = false;
        this.audioIndex = 0;
        this.audioDom.src = this.audios[0].path;
      },
      playOneMusic: function () {
        var vm = this;
        this.audioDom.onerror = null;
        this.audioDom.onended = null;
        this.audioDom.onloadeddata = function () {
          vm.showType != 0 && this.play();
        }
        this.audioDom.loop = true;

        this.audioDom.src = this.audios[0].path;
      },
      /*暂停播放*/
      pauseAudioPlay: function () {
        this.audioDom.pause();
      },
      /*播放轮播图*/
      playImages: function (arr) {
        this.$refs.image_comp.playImages(arr);
      },
      /*重置轮播图组件宽高*/
      changePlayAreaSize: function (w, h) {
        var imagePlayCompo = this.$refs.image_comp;
        imagePlayCompo && imagePlayCompo.resize(w, h);
      },
      changeImgTitleStart: function () {
        $(".img-title").css({
          bottom: "630px",
          opacity: 0
        })
      },
      /*标题动画*/
      imgTitleAnime: function (title) {
        if (title) {
          this.imgTitle = title;
          $(".img-title").animate({left: "30px", bottom: "30px", opacity: 1}, 3000, "linear");
        }
      }
    },
    watch: {
      showType: function (val, oldVal) {
        if (val == 0) {
          this.pauseAudioPlay();
        } else if (oldVal == 0) {
          this.continuePlayMusic();
        }
      },
      audios: {
        handler: function (val) {
          if (val.length == 1) {
            this.playOneMusic();
          } else if (val.length > 1) {
            this.restartPlayMusic();
          }
        },
        deep: true,
      }
    }

  }
</script>


<style scoped>

    .imageBox {
        width: 100%;
        height: 100%;
        position: absolute;
        z-index: 10;
    }

    .img-title {
        position: absolute;
        left: 30px;
        bottom: 630px;
        background: linear-gradient(to bottom right, red, blue);
        -webkit-background-clip: text;
        color: transparent;
        /*方正兰亭黑，方正兰亭纤黑*/
        font-family: "Microsoft YaHei", "方正兰亭黑", "方正兰亭纤黑";
        font-size: 36px;
        z-index: 15;
        opacity: 0;
        text-shadow: 5px 5px 15px #999999;
    }
</style>