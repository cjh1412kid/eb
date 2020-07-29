<template>
    <div class="video_box" ref="video_box">
        <video id="video1" class="video_dom" ref="video_dom1" v-show="oddShow"></video>
        <video id="video2" class="video_dom" ref="video_dom2" v-show="!oddShow"></video>
    </div>
</template>

<script>
  /* eslint-disable */

  var $ = window.jQuery;
  var vm;
  export default {
    name: 'VideoPlay',
    props: {
      videos: {type: Array, default: () => [], required: true},
      showType: {type: Number, default: () => 0, required: true},
      imageExist: {type: Boolean, default: () => true, required: true}
    },
    data() {
      return {
        videoBox: null,
        /*视频加载的位置*/
        vIndex: 0,
        oddShow: true,
        videoDom1: null,
        videoDom2: null,
        /*加载是否成功，能否播放标记*/
        canPlay1: false,
        canPlay2: false,
        /*manyPlay视频存在标记，存在一个及已上为true*/
        videoExistFlag: false,
        endFlag: false,
        /*记录视频播放个数，切换视频播放归0，播完归0*/
        start: 0,
      }
    },
    created: function () {
    },
    mounted: function () {
      console.log("视频播放组件2初始化完成")
      vm = this;
      this.videoBox = this.$refs.video_box;
      this.videoDom1 = this.$refs.video_dom1;
      this.videoDom2 = this.$refs.video_dom2;
      this.bindCommonEvent();
    },
    methods: {
      /*绑定通用的事件*/
      bindCommonEvent: function () {
        this.videoDom1.onpause = function () {
          this.currentTime = 1;
        };
        this.videoDom2.onpause = function () {
          this.currentTime = 1;
        };
      },
      /*播放视频，暂停后继续播放*/
      startPlay: function () {

        if (this.oddShow && this.canPlay1) {
          this.videoDom1.play();
        } else if (!this.oddShow && this.canPlay2) {
          this.videoDom2.play();
        } else {
          this.$emit('listenVideoPlayEnd');
        }
      },
      /*单个视频播放模式*/
      onePlay: function () {
        this.videoDom1.onloadeddata = function () {
          this.loop = true;
          vm.canPlay1 = true;
          this.play();
        };
        this.videoDom1.onerror = function () {
          vm.canPlay1 = false;
          vm.$emit('listenVideoExist', false);
        };
        this.videoDom1.onended = function () {
          if (vm.imageExist) {
            vm.showType == 0 && vm.$emit('listenVideoPlayEnd');
          } else {
            console.log("单视频模式: 前十大不存在，继续播放视频")
          }
          vm.startPlay();
        };

        this.videoDom1.src = this.videos[0].path;
      },
      /*两个视频播放模式*/
      twoPlay: function () {
        this.videoDom1.onloadeddata = function () {
          vm.canPlay1 = true;
          this.play();
        };
        this.videoDom1.onerror = function () {
          vm.canPlay1 = false;
          vm.oddShow = false;
        };
        this.videoDom1.onended = function () {
          if (vm.canPlay2) {
            vm.oddShow = false;
            console.log("双视频模式: 继续播放视频2")
          } else {
            if (vm.imageExist) {
              vm.showType == 0 && vm.$emit('listenVideoPlayEnd');
            } else {
              console.log("双视频模式: 无前十大，视频2不能播放，继续播放视频1")
            }
          }
          vm.startPlay();
        };

        this.videoDom2.onloadeddata = function () {
          vm.canPlay2 = true;
          !vm.canPlay1 && this.play();
        };
        this.videoDom2.onerror = function () {
          vm.canPlay2 = false;
          vm.oddShow = true;
          !vm.canPlay1 && vm.$emit('listenVideoExist', false);
        };
        this.videoDom2.onended = function () {
          vm.canPlay1 && (vm.oddShow = true);
          if (vm.imageExist) {
            vm.showType == 0 && vm.$emit('listenVideoPlayEnd');
          } else {
            console.log("双视频模式: 无前十大，继续播放视频%d", (vm.canPlay1 ? 1 : 2))
          }
          vm.startPlay();
        };

        this.videoDom1.src = this.videos[0].path;
        setTimeout(function () {
          vm.videoDom2.src = vm.videos[1].path;
        }, 500)

      },
      /*多视频播放模式*/
      manyPlay: function () {
        console.log(">>> 多视频播放模式")
        this.videoDom1.onloadeddata = function () {
          vm.canPlay1 = true;
          this.play();
          vm.videoExistFlag = true;
          vm.loadVideo(vm.videoDom2);
          //重置onloadeddata事件
          this.onloadeddata = function () {
            vm.canPlay1 = true;
          }
        };
        this.videoDom1.onerror = function () {
          vm.canPlay1 = false;
          vm.loadVideo(this);
        };
        this.videoDom1.onended = function () {
          vm.playCounter();
          console.log("===>>video1结束，播放video2");
          vm.videoDom2.play();
          vm.oddShow = false;
          vm.loadVideo(this);
        };


        this.videoDom2.onloadeddata = function () {
          vm.canPlay2 = true;
        };
        this.videoDom2.onerror = function () {
          vm.canPlay2 = false;
          vm.loadVideo(this);
        };
        this.videoDom2.onended = function () {
          vm.playCounter();
          console.log("===>>video2结束，播放video1");
          vm.videoDom1.play();
          vm.oddShow = true;
          vm.loadVideo(this);
        };

        //初始化视频
        this.loadVideo(this.videoDom1);
      },
      /*加载视频，加载失败后会加载下一个,manyPlay使用*/
      loadVideo: function (el) {
        if (this.vIndex >= this.videos.length) {
          this.vIndex = 0;
          if (!this.videoExistFlag) {
            this.$emit('listenVideoExist', false);
            return;
          }
        }
        console.log(">>> %s正在加载视频: %d   path: %s", el.id, this.vIndex, this.videos[this.vIndex].path)
        el.src = this.videos[this.vIndex].path;
        this.vIndex++;
      },
      /*视频播放计数器*/
      playCounter: function () {
        this.start++;
        console.log("第 %d 个视频播放结束,视频总数: %d", this.start, this.videos.length);

        if (this.start >= this.videos.length) {
          if (this.imageExist) {
            this.showType == 0 && vm.$emit('listenVideoPlayEnd');
          }
          this.start = 0;
        }
      },
      pausePlay: function () {
        this.videoDom1.pause();
        this.videoDom2.pause();
      },
      becomeSmallVideo: function () {
        $(this.videoBox).animate({width: "180px", height: "320px", top: "40px", right: "40px"}, 1000);
        this.videoDom1.muted = true;
        this.videoDom2.muted = true;
      },
      quitSmallVideo: function () {
        $(this.videoBox).animate({width: "100%", height: "100%", top: "0", right: "0"}, 1000);
        this.videoDom1.muted = false;
        this.videoDom2.muted = false;
      },
      restartPlay: function () {
        this.vIndex = 0;
        this.loadVideo(this.videoDom1);
      }
    },
    watch: {
      showType: function (val, oldVal) {
        if (val == 0) {
          if (oldVal == 2) {
            console.log("前十大切换到视频播放》");
            this.start = 0;
          }
          this.quitSmallVideo();
        } else if (oldVal == 0) {
          this.becomeSmallVideo();
        }
      },
      videos: {
        handler: function (val) {
          console.log("加载到视频数据,启动视频播放")

          if (val.length == 1) {
            this.onePlay();
          } else if (val.length > 1) {
            this.manyPlay();
          }

        },
        deep: true,
      }
    }
  }
</script>

<style scoped>
    .video_box {
        width: 100%;
        height: 100%;
        position: absolute;
        right: 0;
        top: 0;
        z-index: 20;
    }

    .video_dom {
        width: 100%;
        height: 100%;
    }
</style>