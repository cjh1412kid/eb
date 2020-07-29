<template>
    <div id="app" class="screen">
        <div class="screenBox">
            <div class="mediaBox" ref="show_dom">
                <VideoPlay :videos="videos" :show-type="showType"
                           :image-exist="hotImageExistFlag" v-show="videoExistFlag"
                           ref="video_play" @listenVideoPlayEnd="videoPlayEnd"
                           @listenVideoExist="videoExist"></VideoPlay>
                <ImagePlay :audios="audios" :show-type="showType"
                           ref="image_play" @listenImagePlayEnd="imagePlayEnd"></ImagePlay>
                <!--小程序图标-->
                <!--<img class="xcx" :src="xcxImage"/>-->
            </div>
            <HotPlay class="hot_box" v-show="hotImageExistFlag" :original-images="originalHotImages"
                     :show-type="showType" :video-exist="videoExistFlag"
                     ref="hot_play" @listenCurrImage="resetSlide" @listenHotPlayEnd="hotPlayEnd"></HotPlay>
        </div>
        <!--节日动画-->
        <Festival :festival-name="festivalName" v-if="festivalName"></Festival>
        <!--消息提示-->
        <div id="message" v-if="message" v-text="message"></div>
        <!--背景墙-->
        <div class="bgWall" :style="[screenStyle]"></div>
        <div class="videoBGWall" :style="[screenStyle2]" v-show="isShowVideoBGWall"></div>
    </div>
</template>

<script>
  import VideoPlay from './components/VideoPlay.vue'
  import HotPlay from './components/HotPlay.vue'
  import ImagePlay from './components/ImagePlay.vue'
  import Festival from './components/Festival.vue'

  var vm;
  /* eslint-disable */
  export default {
    name: 'app',
    components: {
      VideoPlay,
      HotPlay,
      ImagePlay,
      Festival
    },
    mounted: function () {
      vm = this;
      this.showDom = this.$refs.show_dom;
      this.imagePlayCompo = this.$refs.image_play;
      this.getDataFromSystem();
      this.findFestivalAnimateName();
      // this.getDataFromSystem2();
      // this.findFestivalAnimateName2();
      this.initBgImage();
    },
    data: function () {
      return {
        showDom: null,
        imagePlayCompo: null,

        /*展示内容的类型，0为视频播放，1为试穿图片，2为20大图片，*/
        showType: 0,
        /*当展示试穿时，暂存切换前的类型*/
        tmpShowType: 0,
        /*节日动画组件名称 christmas*/
        festivalName: "",
        /*节日动画对象*/
        festivalAnimation: null,
        /*无播放素材时，显示该默认图片*/
        defaultImage: "/Images/DisplayClient/noPic.jpg",
        /*小程序图片地址*/
        xcxImage: "static/images/xcx.png",
        /*视频播放列表*/
        videos: [],
        /*视频是否存在的标记*/
        videoExistFlag: false,
        /*音频播放列表*/
        audios: [],

        /*前十大原数据*/
        originalHotImages: [],
        /*小图是否存在的标记，不存在则全屏播放视频*/
        hotImageExistFlag: false,

        /*当前展示的试穿鞋子*/
        tryCurrPlaying: null,
        /*试穿播放当前索引*/
        tIndex: 0,
        /*试穿的标签数组，当数据更新后随之更新*/
        tryCurrData: [],
        /*试穿播放结束后切换状态*/
        tryImagesPlayEndFlag: false,

        //计数器，试穿读取超过3次为空结束
        counter: 0,

        /*通知用户消息*/
        message: null,

        /*用于判断showDom是否变化*/
        showEnlarge: false,
        /*获取节日动画名次数*/
        countOfGetFestivalName: 0,
        screenStyle: {
          backgroundImage: "",
          backgroundSize: "cover",
          backgroundRepeat: "no-repeat",
        },
        isShowVideoBGWall: true,
        screenStyle2: {
          backgroundImage: "url('static/images/bg01.png')",
          backgroundSize: "100% 100%",
          backgroundRepeat: "no-repeat",
        },
      }

    },
    computed: {},
    methods: {
      /*改变展示内容的类型*/
      changeShowType: function (val) {
        this.showType = val;
      },
      /*视频数据存在，加载不到视频，路径异常或无数据源*/
      videoExist: function (exist) {
        if (exist) {
          this.videoExistFlag = true;
        } else {
          this.videoExistFlag = false;
          this.popupMessage("视频数据存在但加载失败，请联系运维人员")
          if (this.hotImageExistFlag) {
            this.changeShowType(2);
          }
        }
      },
      /*视频播放结束后触发*/
      videoPlayEnd: function () {
        this.changeShowType(2);
      },
      /*前十大播放结束后触发*/
      hotPlayEnd: function () {
        this.changeShowType(0);
      },
      /*素材播放结束后触发*/
      imagePlayEnd: function () {
        switch (this.showType) {
          case 1:
            vm.tryImagesPlayEndFlag = true;
            break;
          case 2:
            this.$refs.image_play.changeImgTitleStart();
            this.$refs.hot_play.continuePlayNext();
            break;
        }

      },
      /*小图片切换后触发，重置播放的素材图片，无素材播放默认图*/
      resetSlide: function (oImage) {

        //获取素材
        var playData = oImage.PlayData;

        if (playData && playData.length > 0) {
          var imageList = [];
          playData.forEach(item => {
            imageList.push(item.path);
          })

          //展示素材
          this.playImages(imageList)
          this.$refs.image_play.imgTitleAnime(oImage.ShoesDES);

          console.log("【小图切换】重置素材播放：", playData.length, oImage)
        } else {
          //无素材，展示默认图
          // this.playDefaultImage();
          this.$refs.hot_play.continuePlayNext();
        }

      },
      /*播放素材*/
      playImages: function (imgArr) {
        this.$refs.image_play.playImages(imgArr);
        //改变背景图
        this.changeBgImage(imgArr);
      },
      /*相关产品没有素材图片时，显示默认图片*/
      playDefaultImage: function () {
        var defaultImagePath = window.LocalPath + this.defaultImage;
        var oImage = [defaultImagePath];

        this.playImages(oImage);
      },
      /*播放试穿图*/
      playTryImages: function () {
        vm.tryImagesPlayEndFlag = false;

        //如果第一次播放或前后播放标签不同，背景灯闪一下
        if (!vm.tryCurrPlaying || vm.tryCurrPlaying.RFID != vm.tryCurrData[vm.tIndex].RFID) {
          // vm.letTryShine();
        }

        vm.tryCurrPlaying = vm.tryCurrData[vm.tIndex];
        //获取播放的素材列表
        var PlayData = vm.tryCurrPlaying.PlayData;
        var detailImages = getDetailImages(PlayData);

        if (detailImages.length > 0) {
          vm.playImages(detailImages);
        } else {
          //试穿标签无素材，播放默认图片
          vm.playDefaultImage();
        }

        //设置下一次读取位置
        vm.tIndex = vm.tIndex + 1 >= vm.tryCurrData.length ? 0 : vm.tIndex + 1;
      },
      /*获取数据源*/
      getDataFromSystem: function () {
        console.log("获取window.LocalPath：", window.LocalPath)
        if (window.LocalPath) {
          vm.setMediaBoxBGImage();
          vm.findVideo();
          vm.findAudio();
          vm.findTopShoes();
          vm.findTryShoes();

        } else {
          setTimeout(vm.getDataFromSystem, 500);
        }
      },
      findFestivalAnimateName: function () {
        if (vm.countOfGetFestivalName++ > 20) return;

        console.log("获取节日动画名AnimateName: '%s'  次数: %d", window.AnimateName, vm.countOfGetFestivalName);
        // window.AnimateName = "newyear";
        if (window.AnimateName) {
          vm.festivalName = window.AnimateName.trim();
        } else {
          setTimeout(vm.findFestivalAnimateName, 500);
        }
      },
      /*获取数据源*/
      getDataFromSystem2: function () {

        window.NUITE.getLocalPath().then(function (result) {
          // console.log("获取根目录getLocalPath()：", result)
          if (result) {
            window.LocalPath = result;
            vm.setMediaBoxBGImage();
            vm.findVideo();
            vm.findAudio();
            vm.findTopShoes();
            vm.findTryShoes();
          } else {
            console.log("异常:: 未获取到根路径")
          }
        })
      },
      findFestivalAnimateName2: function () {

        window.NUITE.getAnimate().then(function (result) {

          if (result && result.length > 0) {
            console.log("获取节日动画数据2: ", result);
            var jsonObj = JSON.parse(result);
            var jsonObj2 = JSON.parse(jsonObj);
            // console.log("jsonObj: ",jsonObj,typeof jsonObj)
            // console.log("jsonObj2: ",jsonObj2,typeof jsonObj2)
            vm.festivalAnimation = jsonObj2;
          } else {
            console.log("未获取到节日动画对象")
          }
        })
      },
      findTryShoes: function () {
        var action = function () {
          window.NUITE.getTryShoes().then(function (result) {
            // result为json字符串
            var jsonObj = JSON.parse(result);

            var newPlayData = jsonObj.ShoesPlayList;

            // console.log("定时检测标签中: %d   ShowType:%d   %o", newPlayData.length, vm.showType, newPlayData)

            //新标签数组为空，退出
            if (newPlayData.length == 0) {
              //试穿状态下，新标签数组为空，则退出试穿
              if (vm.showType == 1) {

                vm.counter++;
                //试穿状态下，3次没读到新数据，则退出
                if (vm.counter > 3) {
                  console.log("未读取到标签数据，退出试穿状态")
                  //做退出试穿类型操作,切换到之前的类型
                  vm.tIndex = 0;
                  vm.tryCurrPlaying = null;
                  vm.tryCurrData = [];
                  vm.changeShowType(vm.tmpShowType);
                  vm.counter = 0;
                  return;
                }

              }
              //不操作
              return;
            }

            console.log("获取到新标签数组：%d   %o", newPlayData.length, newPlayData)

            //读取到标签数据，切换到试穿类型
            vm.showType != 1 && (vm.changeShowType(1));
            //读取到标签，空计数器归零
            vm.counter = 0;

            //判断新旧数据是否一致
            var isSame = compareArrayEquals(vm.tryCurrData, newPlayData);
            if (isSame) {
              //一致,继续播放，播放完重新开始
              console.log("当前试穿播放是否结束：", vm.tryImagesPlayEndFlag)
              vm.tryImagesPlayEndFlag && (vm.playTryImages());

            } else {
              //不一致，新数组
              vm.tryCurrData = newPlayData;

              if (vm.tryCurrPlaying && vm.tryCurrPlaying.RFID == newPlayData[0].RFID) {
                //当前正在播放的标签与新数组第一个相同

                if (vm.tryImagesPlayEndFlag) {
                  //设置下一次读取位置
                  vm.tIndex = 0;
                  vm.tIndex = vm.tIndex + 1 >= vm.tryCurrData.length ? 0 : vm.tIndex + 1;
                  //等当前播放结束，播放第二个
                  vm.playTryImages()
                } else {
                  console.log("当前试穿标签还在监测范围,素材播放未结束")
                }

              } else {
                if (vm.tryCurrPlaying) {
                  console.log("当前播放标签与新标签组第一个不一致，从新标签组第一个读取")
                } else {
                  console.log("检测到标签，将读取")
                }
                //与新数组第一个不相同，从第一个开始
                vm.tIndex = 0;
                vm.playTryImages();
              }
            }


          }).catch(function (error) {
            console.log(error)
          });

          setTimeout(action, 1000);
        }

        action();

      },
      findTopShoes: function () {
        var firstFlag = true; //首次执行标记，向子组件传递宽高
        var slow = 1000 * 60 * 30 //半小时
        var fast = 1000 * 30; //半分钟

        /* 大小屏切换测试
        var test = true, slow = 1000 * 60 * 5;
        if (test) {
          oResult = [];
          test = false;
        } else {
          test = true;
        }
        */

        var action = function () {
          window.NUITE.getTopShoes().then(function (result) {
            console.log("获取前十大：", result)
            var oResult = JSON.parse(result);

            if (oResult && oResult.length > 0) {
              vm.originalHotImages = oResult;
              vm.hotImageExistFlag = true;
              //当前大屏，则小屏显示
              vm.showEnlarge && vm.enlargeShowArea(false);
              if (firstFlag) {
                firstFlag = false;
                vm.enlargeShowArea(false);
              }
              setTimeout(action, slow);
            } else {
              // vm.popupMessage("未获取到前十大数据");
              vm.hotImageExistFlag = false;
              !vm.videoExistFlag && vm.popupMessage("未获取到前十大数据和视频，请联系运维人员");

              //小图数据源为空，当前小屏则视频大屏显示
              !vm.showEnlarge && vm.enlargeShowArea(true);
              vm.changeShowType(0);
              vm.$refs.hot_play.stopRotation();

              if (firstFlag) {
                firstFlag = false;
                vm.enlargeShowArea(true);
              }
              setTimeout(action, fast);
            }


          }).catch(function (error) {
            console.log(error)
          });
        }

        action();

      },
      findAudio: function () {
        window.NUITE.getMp3s().then(function (result) {
          console.log("获取音频：", result);
          var oResult = JSON.parse(result);

          if (oResult.length == 0) {
            vm.popupMessage("未获取到音频，请联系运维人员");
          } else {

            var resArr = []
            oResult.forEach((item) => {
              //过滤后缀名不是.ogg
              var filename = item.FileName;
              var suffix = filename.substring(filename.length - 4, filename.length).toLowerCase();
              if (suffix == ".ogg") {
                resArr.push({path: window.LocalPath + item.RelativeURL + item.FileName})
              }
            })
            console.log("可播放的音频列表： ", resArr)

            if (resArr.length == 0) {
              vm.popupMessage("未获取到可播放音频，请联系运维人员");
            } else {
              vm.audios = resArr;
            }

          }

        }).catch(function (error) {
          console.log(error)
        });
      },
      findVideo: function () {
        window.NUITE.getVideos().then(function (result) {
          console.log("获取视频数据：", result)
          var oResult = JSON.parse(result);

          if (oResult.length == 0) {
            vm.videoExistFlag = false;
            vm.popupMessage("未获取到视频，请联系运维人员");
            vm.changeShowType(2);
          } else {

            var resArr = []
            oResult.forEach((item) => {
              //过滤后缀名不是.ogg
              var filename = item.FileName;
              var suffix = filename.substring(filename.length - 4, filename.length).toLowerCase();
              if (suffix == ".ogg") {
                resArr.push({path: window.LocalPath + item.RelativeURL + item.FileName})
              }
            })

            console.log("可播放的视频列表： ", resArr)
            if (resArr.length == 0) {
              vm.videoExistFlag = false;
              vm.popupMessage("未获取到可播放视频，请联系运维人员");
              vm.changeShowType(2);
            } else {
              vm.videoExistFlag = true;
              vm.videos = resArr;
            }
          }

        }).catch(function (error) {
          console.log(error)
        });
      },
      /*弹出消息提示用户*/
      popupMessage: function (msg, mills) {
        this.message = msg;

        setTimeout(function () {
          vm.message = null;
        }, (mills || 10000))
      },
      /*重置播放区大小*/
      enlargeShowArea: function (enlarge) {

        if (enlarge) {
          this.showEnlarge = true;
          this.showDom.style.width = "100%";
          this.showDom.style.height = "100%";
          this.showDom.style.top = 0;
        } else {
          this.showEnlarge = false;
          this.showDom.style.width = "80%";
          this.showDom.style.height = "80%";
          this.showDom.style.top = "1%";
        }
        this.imagePlayCompo.changePlayAreaSize(this.showDom.clientWidth, this.showDom.clientHeight);
      },
      /*播放试穿标签素材时，背景发亮*/
      letTryShine: function () {
        this.showDom.style.boxShadow = "0 0.94em 6em 0 #ffd700";
        // setTimeout(function () {
        //   vm.showDom.style.boxShadow = "0 0.94em 3.13em 0 #932929";
        // }, 6000);
      },
      quitTryShine: function () {
        vm.showDom.style.boxShadow = "0 0.94em 3.13em 0 #932929";
      },
      /*改变屏幕背景*/
      changeBgImage: function (imagePathList) {
        var i = 0;
        var action = function () {
          if (!imagePathList[i]) return;

          var oImage = new Image();
          oImage.src = imagePathList[i];
          oImage.onload = function () {
            vm.screenStyle.backgroundImage = 'url("' + imagePathList[i] + '")';
            // console.log("设置屏幕背景图: ", imagePathList[i]);
          }
          oImage.onerror = function () {
            i++;
            action();
          }
        }

        action();
      },
      /*设置默认背景*/
      initBgImage: function () {
        this.screenStyle2.backgroundImage = "url('static/images/bg01.png')";
      },
      /*设置mediaBox背景图*/
      setMediaBoxBGImage: function () {
        var defaultImagePath = window.LocalPath + this.defaultImage;
        this.showDom.style.backgroundImage = 'url("' + defaultImagePath + '")';
        this.showDom.style.backgroundSize = "100% 100%";
        this.showDom.style.backgroundRepeat = "no-repeat";
      }
    },
    watch: {
      showType: function (val, oldVal) {

        if (val == 1) {
          //保存试穿前的状态
          this.tmpShowType = oldVal;
          this.letTryShine();
        } else if (oldVal == 1) {
          this.quitTryShine();
        }

        this.isShowVideoBGWall = val == 0 ? true : false;
      }
    }
  }


  /*比较新旧标签数组是否相同*/
  function compareArrayEquals(arr1, arr2) {
    if (!arr2 || !arr1) return false;
    if (arr1.length != arr2.length) return false;

    for (var i = 0, l = arr1.length; i < l; i++) {
      if (arr1[i].RFID != arr2[i].RFID) return false;
    }
    return true;
  }

  /* 从标签对象的playData数组中，获取播放素材路径数组*/
  function getDetailImages(playData) {
    var tryImages = [];
    playData.forEach(item => {
      var path = window.LocalPath + item.RelativeURL + "/" + item.name;
      tryImages.push(path)
    })
    return tryImages;
  }


</script>

<style>
    html {
        font-size: 100%;
    }

    body {
        font-size: 1em;
        margin: 0;
        padding: 0;
        overflow: hidden;
    }

    #app {
        font-family: 'Avenir', Helvetica, Arial, sans-serif;
        -webkit-font-smoothing: antialiased;
        -moz-osx-font-smoothing: grayscale;
        color: #2c3e50;
        margin-top: 0;
    }

    /*背景墙*/
    .bgWall {
        position: absolute;
        left: 0;
        top: 0;
        width: 100%;
        height: 100%;
        z-index: -2;
        -webkit-filter: opacity(.3);
        filter: opacity(.3);
    }

    .videoBGWall {
        position: absolute;
        left: 0;
        top: 0;
        width: 100%;
        height: 100%;
        z-index: -1;
        background: -webkit-linear-gradient(to bottom right, #eee, #feefef);
        background: linear-gradient(to bottom right, #eee, #feefef);
    }

    .screen {
        position: relative;
        width: 67.5em;
        height: 120em;
        z-index: 0;
    }

    .screenBox {
        position: relative;
        width: 100%;
        height: 100%;
    }

    .mediaBox {
        position: absolute;
        width: 80%;
        height: 80%;
        left: 0;
        right: 0;
        top: 1%;
        z-index: 2;
        margin: auto;
        box-shadow: 0 0.5em 4em 0 #932929;
    }

    .hot_box {
        width: 100%;
        height: 19%;
        position: absolute;
        left: 0;
        bottom: 0;
        z-index: 1;
        overflow: hidden;
    }

    /*小程序图标*/
    .xcx {
        position: absolute;
        right: 0px;
        bottom: 3px;
        z-index: 30;
    }

    #message {
        position: absolute;
        width: 100%;
        height: 5em;
        z-index: 50;
        background-color: #000;
        text-align: center;
        position: absolute;
        margin: auto;
        top: 0;
        left: 0;
        bottom: 0;
        right: 0;
        font-size: 2em;
        font-family: 华文行楷, "Microsoft YaHei";
        color: #fff;
        line-height: 5em;
    }

</style>
