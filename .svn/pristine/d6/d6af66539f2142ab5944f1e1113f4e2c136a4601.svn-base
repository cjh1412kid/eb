<template>
    <div>
        <canvas id="canvas" ref="canvas_dom"> IE8 以及更早的版本不支持画布技术</canvas>
    </div>
</template>

<script>

  /* eslint-disable */
  var vm;

  export default {
    name: 'HotPlay',
    props: {
      originalImages: {type: Array, default: () => [], required: true},
      showType: {type: Number, default: () => 0, required: true},
      videoExist: {type: Boolean, default: () => true, required: true}
    },
    data() {
      return {
        /*当前轮转图展示图的索引，可控制播放顺序*/
        currIndex: 0,
        /*当前展示的小图*/
        currDiapo: null,
        /*自转速度(ms)*/
        rotationSpeed: 4000,
        /*小图延时放大*/
        waitEnlargeTime: 1000,
        /*自转定时器，是否存在*/
        rotationStyleTimer: null,
        hotImageStyleTimer: null,

        /*播放图片数组，12张1组*/
        hotImages: [],
        /*hotImagesPool当前播放图片组的起始位置*/
        hIndex: 0,
        /*小图正在播放列表，开始播放时重置，从等待播放列表取值*/
        hotImagesPool: [],
        /*小图等待播放列表，数量为12倍数*/
        hotImagesWaitPool: [],
        /*初始化完成标记*/
        initFlag: false
      }
    },
    mounted: function () {
      vm = this;
      console.log("前十大轮播组件#1 初始化完成...")
    },
    methods: {
      /*启动自转*/
      enableRotation: function () {
        clearTimeout(vm.hotImageStyleTimer);
        clearTimeout(vm.rotationStyleTimer);

        if (this.showType == 2) {
          this.currIndex = 0;
          enableHotImageStyleTimer();
        } else {
          enableRotationStyleTimer();
        }
      },
      /*继续播放下一个，取消暂停*/
      continuePlayNext: function () {
        console.log("转动到下一张diapo")
        this.pauseFlag = false;
      },
      /*停止所有定时器*/
      stopRotation: function () {

        if (vm.hotImageStyleTimer) {
          clearTimeout(vm.hotImageStyleTimer);
          vm.hotImageStyleTimer = null;
        }

        if (vm.rotationStyleTimer) {
          clearTimeout(vm.rotationStyleTimer)
          vm.rotationStyleTimer = null;
        }
      },
      /*初始化小图播放*/
      initPlayHotImages: function () {

        if (vm.hotImagesWaitPool.length >= 12) {
          vm.hIndex = 0;
          vm.hotImagesPool = vm.hotImagesWaitPool.slice(0);
          vm.hotImages = vm.hotImagesPool.slice(0, 12);
          vm.enableRotation();
        } else {
          setTimeout(vm.initPlayHotImages, 500)
        }
      },
      /*一轮小图12张播放完后触发，播放下一轮12张图，播完切换视频播放*/
      playNextGroup: function () {
        //下一轮播放图起始索引
        var hNextIndex = this.hIndex + 12;

        if (hNextIndex >= this.hotImagesPool.length) {
          this.$emit("listenHotPlayEnd");
          //视频不存在，则循环播放小图
          !this.videoExist && this.restartPlayHotImages();
        } else {
          //未播放结束，切换新一轮小图
          this.hotImages = this.hotImagesPool.slice(hNextIndex, hNextIndex + 12);
          this.hIndex = hNextIndex;
          console.log("小图未播放结束，更换下一轮小图：", hNextIndex, this.hotImages)
          this.enableRotation();
        }
      },
      /*继续重头播放小图，用于无视频情况下，循环播放小图*/
      continuePlayHotImages: function () {
        this.hIndex = 0;
        this.hotImagesPool = this.hotImagesWaitPool.slice(0);
        this.hotImages = this.hotImagesPool.slice(0, 12);
        this.enableRotation();
      },
      /*重新开始播放小图,视频播放结束后调用*/
      restartPlayHotImages: function () {
        this.hIndex = 0;
        this.hotImagesPool = this.hotImagesWaitPool.slice(0);
        this.hotImages = this.hotImagesPool.slice(0, 12);
        this.enableRotation();
      },
    },
    watch: {
      /*图片数组的更新，重置展示图*/
      originalImages: {
        handler: function (newArr) {

          if (!newArr || newArr.length == 0) return;

          console.log("》》获得新前十大数据》》: %o ,数量:%s", newArr, newArr && newArr.length)

          //格式化源数据
          newArr.forEach((item, index) => {
            var path = window.LocalPath + "/Resource/ShoesResource/ShoesIcons/" + item.PeriodSeq + "/" + item.ShoesID + ".jpg";
            item.path = path;
            item.hotTitle = "top" + (index + 1);

            item.PlayData.forEach(item2 => {
              var path2 = window.LocalPath + item2.RelativeURL + "/" + item2.name;
              item2.path = path2;
            })
          })

          //小图数据源发生变化，更新小图等待播放列表
          //PS: 存放在等待列表，数据的变化不影响正在播放列表，下次重播时，正在播放列表从等待列表中取数据
          var len = newArr.length;
          this.hotImagesWaitPool = [];

          if (len < 12) {
            //缺少的数
            var que = 12 - len;
            //长度不足12，补足
            if (que <= len) {
              this.hotImagesWaitPool = newArr.concat(newArr.slice(0, que));
            } else {
              //缺少的个数 > 数组长度
              var quot = que / len;
              var tmpArr = [];
              for (var i = 0; i < quot + 1; i++) {
                tmpArr = tmpArr.concat(newArr);
              }
              this.hotImagesWaitPool = newArr.concat(tmpArr.slice(0, que));
            }

          } else if (len == 12) {
            this.hotImagesWaitPool = newArr;
          } else {
            //长度大于12，结果为12倍数
            var yu = len % 12;
            this.hotImagesWaitPool = newArr.concat(newArr.slice(0, 12 - yu));
          }

          //拼接标记
          len != 12 && (this.hotImagesWaitPool[len].concatFlag = true);
          console.log("hotImagesWaitPool: %o ,数量: %d", this.hotImagesWaitPool, this.hotImagesWaitPool.length)
          //获取到新数据，重新播放小图
          this.initPlayHotImages();
        },
        deep: true,
      },
      /*播放数组发生变化*/
      hotImages: {
        handler: function (newArr) {
          if (this.initFlag) {
            console.log("重置前十大播放图组: ", newArr.length);
            resetImages(newArr);
          } else {
            console.log("开始初始化前十大播放环境...");
            initEnv(newArr);
            this.initFlag = true;
          }
        },
        deep: true
      },
      /*showType的转变，改变播放的方式*/
      showType: function (val, old) {

        if (val == 2) {
          if (old == 0) {
            this.restartPlayHotImages();
          } else {
            clearTimeout(vm.rotationStyleTimer);
            enableHotImageStyleTimer();
          }
        } else if (old == 2) {
          clearTimeout(vm.hotImageStyleTimer);
          enableRotationStyleTimer();
        }

      },

    }
  }

  /**加工源图片数据,使之符合插件要求，images为图片对象数组 */
  function imgFactory(images) {
    /*轮播图背景墙样式，4面墙，12张图，1墙3张*/
    var imgArrTemplate = [
      // north
      {img: '1.jpg', x: -1000, y: 0, z: 1500, nx: 0, nz: 1, flag: 1},
      {img: '2.jpg', x: 0, y: 0, z: 1500, nx: 0, nz: 1, flag: 1},
      {img: '3.jpg', x: 1000, y: 0, z: 1500, nx: 0, nz: 1, flag: 1},
      // east
      {img: '4.jpg', x: 1500, y: 0, z: 1000, nx: -1, nz: 0, flag: 2},
      {img: '5.jpg', x: 1500, y: 0, z: 0, nx: -1, nz: 0, flag: 2},
      {img: '6.jpg', x: 1500, y: 0, z: -1000, nx: -1, nz: 0, flag: 2},
      // south
      {img: '7.jpg', x: 1000, y: 0, z: -1500, nx: 0, nz: -1, flag: 3},
      {img: '8.jpg', x: 0, y: 0, z: -1500, nx: 0, nz: -1, flag: 3},
      {img: '9.jpg', x: -1000, y: 0, z: -1500, nx: 0, nz: -1, flag: 3},
      // west
      {img: '10.jpg', x: -1500, y: 0, z: -1000, nx: 1, nz: 0, flag: 4},
      {img: '11.jpg', x: -1500, y: 0, z: 0, nx: 1, nz: 0, flag: 4},
      {img: '12.jpg', x: -1500, y: 0, z: 1000, nx: 1, nz: 0, flag: 4}
    ];
    var newImgArrLen = images.length < imgArrTemplate.length ? images.length : imgArrTemplate.length;
    var newImgArr = imgArrTemplate.slice(0, newImgArrLen);

    //新设置的属性与diapo对象关联
    for (var i in newImgArr) {
      newImgArr[i].path = images[i].path;    //图片路径
      newImgArr[i].id = images[i].ShoesID; //新增属性
      newImgArr[i].concatFlag = images[i].concatFlag; //拼接标记
      newImgArr[i].hotTitle = images[i].hotTitle; //拼接标记
      newImgArr[i].self = images[i]; //拼接标记
    }
    return newImgArr;
  }

  /*结构参数，不动*/
  var structureValue = [
    {
      // wall 1
      fill: {r: 255, g: 255, b: 255, light: 1},
      x: [-1001, -490, -490, -1001],
      z: [-500, -500, -500, -500],
      y: [500, 500, -500, -500]
    }, {
      // wall 2
      fill: {r: 255, g: 255, b: 255, light: 1},
      x: [-501, 2, 2, -500],
      z: [-500, -500, -500, -500],
      y: [500, 500, -500, -500]
    }, {
      // wall 3
      fill: {r: 255, g: 255, b: 255, light: 1},
      x: [0, 502, 502, 0],
      z: [-500, -500, -500, -500],
      y: [500, 500, -500, -500]
    }, {
      // wall 4
      fill: {r: 255, g: 255, b: 255, light: 1},
      x: [490, 1002, 1002, 490],
      z: [-500, -500, -500, -500],
      y: [500, 500, -500, -500]
    }

    , {
      // shadow 1
      fill: {r: 0, g: 0, b: 0, a: 0.2},
      x: [-420, 420, 420, -420],
      z: [-500, -500, -500, -500],
      y: [150, 150, -320, -320]
    }, {
      // shadow 2
      fill: {r: 0, g: 0, b: 0, a: 0.2},
      x: [-20, 20, 20, -20],
      z: [-500, -500, -500, -500],
      y: [250, 250, 150, 150]
    }, {
      // shadow 3
      fill: {r: 0, g: 0, b: 0, a: 0.2},
      x: [-20, 20, 20, -20],
      z: [-500, -500, -500, -500],
      y: [-320, -320, -500, -500]
    }, {
      // shadow 4
      fill: {r: 0, g: 0, b: 0, a: 0.2},
      x: [-20, 20, 10, -10],
      z: [-500, -500, -100, -100],
      y: [-500, -500, -500, -500]
    }

    , {
      // base
      fill: {r: 32, g: 32, b: 32},
      x: [-50, 50, 50, -50],
      z: [-150, -150, -50, -50],
      y: [-500, -500, -500, -500]
    }, {
      // support
      fill: {r: 16, g: 16, b: 16},
      x: [-10, 10, 10, -10],
      z: [-100, -100, -100, -100],
      y: [300, 300, -500, -500]
    }, {
      // frame
      fill: {r: 255, g: 255, b: 255},
      x: [-320, -320, -320, -320],
      z: [0, -20, -20, 0],
      y: [-190, -190, 190, 190]
    }, {
      // frame
      fill: {r: 255, g: 255, b: 255},
      x: [320, 320, 320, 320],
      z: [0, -20, -20, 0],
      y: [-190, -190, 190, 190]
    },
    {img: true},
    {
      // ceilingLight
      fill: {r: 255, g: 128, b: 0},
      x: [-50, 50, 50, -50],
      z: [450, 450, 550, 550],
      y: [500, 500, 500, 500]
    }, {
      // groundLight
      fill: {r: 255, g: 128, b: 0},
      x: [-50, 50, 50, -50],
      z: [450, 450, 550, 550],
      y: [-500, -500, -500, -500]
    }
  ];

  /* ==== definitions ==== */
  /**
   * 幻灯片
   * @type {Array}
   */
  var diapo = [],
    layers = [],
    ctx, pointer, scr, camera, light, fps = 0,
    quality = [1, 2],
    // ---- poly constructor ----
    /**polygons 多边形*/
    Poly = function (parent, face) {
      this.parent = parent;
      this.ctx = ctx;
      this.color = face.fill || false;
      this.points = [];
      if (!face.img) {
        // ---- create points ----
        for (var i = 0; i < 4; i++) {
          this.points[i] = new ge1doot.transform3D.Point(
            parent.pc.x + (face.x[i] * parent.normalZ) + (face.z[i] * parent.normalX),
            parent.pc.y + face.y[i],
            parent.pc.z + (face.x[i] * parent.normalX) + (-face.z[i] * parent.normalZ)
          );
        }
        this.points[3].next = false;
      }
    },

    // ---- diapo constructor ----
    Diapo = function (path, img, structure) {
      this.oImage = img.self;
      this.id = img.id; //新增属性，根据id获取图片相关素材
      this.flag = img.flag; // 新增属性，判断图片位置，4面墙，哪面墙，用于设定视距
      this.concatFlag = img.concatFlag; // 新增属性，判断图片位置，4面墙，哪面墙，用于设定视距
      this.hotTitle = img.hotTitle; //小图片标题
      this.visible = false;
      this.normalX = img.nx;
      this.normalZ = img.nz;

      // ---- create image ----
      this.img = new ge1doot.transform3D.Image(
        this, path + img.path, 1, {
          isLoaded: function (img) {
            img.parent.isLoaded = true;
            img.parent.loaded(img);
          }
        }
      );
      // ---- point center ----
      this.pc = new ge1doot.transform3D.Point(img.x, img.y, img.z);
      // ---- target positions ----
      this.tx = img.x + (img.nx * Math.sqrt(camera.focalLength) * 20);
      this.tz = img.z - (img.nz * Math.sqrt(camera.focalLength) * 20);
      /*新增，调整视线距离照片的远近*/
      switch (this.flag) {
        case 1:
          this.tz -= 300;
          break;
        case 2:
          this.tx -= 300;
          break;
        case 3:
          this.tz += 300;
          break;
        case 4:
          this.tx += 300;
          break;
      }
      // ---- create polygons ----
      this.poly = [];

      for (var i = 0, p; i < structure.length; i++) {
        p = structure[i];
        layers[i] = (p.img === true ? 1 : 2);
        this.poly.push(
          new Poly(this, p)
        );
      }
    },

    // ---- init section ----
    init = function (json) {
      // draw poly primitive
      Poly.prototype.drawPoly = ge1doot.transform3D.drawPoly;
      // ---- init screen ----
      scr = new ge1doot.Screen({
        container: vm.$refs.canvas_dom
      });
      ctx = scr.ctx;
      scr.resize();

      // ---- init pointer ----
      pointer = new ge1doot.Pointer({
        /*该tap方法点击画布时触发*/
        tap: function () {
          /*
           *camera.over
           * 点击图片时，是diapo对象
           * 点击screen其他地方，是false；
           */
          if (camera.over) {
            /*camera.target.elem 图片放大前为false*/
            if (camera.over === camera.target.elem) {
              // console.log("图片回到中间")
              // ---- return to the center ----
              camera.target.x = 0;
              camera.target.z = 0;
              camera.target.elem = false;
            } else {
              // console.log("图片放大了。。。")
              // ---- goto diapo ----
              camera.target.elem = camera.over;
              camera.target.x = camera.over.tx;
              camera.target.z = camera.over.tz;

              // ---- adapt tesselation level to distance ----
              var i = 0, d;
              while ((d = diapo[i])) {
                var dx = camera.target.x - d.pc.x;
                var dz = camera.target.z - d.pc.z;
                var dist = Math.sqrt(dx * dx + dz * dz);
                var lev = (dist > 1500) ? quality[0] : quality[1];
                d.img.setLevel(lev);
                i++;
              }
            }
          }
        }

      });


      // ---- init camera ----
      camera = new ge1doot.transform3D.Camera({
        focalLength: Math.sqrt(scr.width) * 10,
        easeTranslation: 0.2,
        easeRotation: 0.2,
        disableRz: true,
        disableRX: true,
      }, {
        move: function () {
          this.over = false;
          // ---- rotation ----
          if (pointer.isDraging) {
            // console.log("拖拽图片时触发", this, camera == this)
            this.target.elem = false;
            this.target.ry = -pointer.Xi * 0.01;
            this.target.rx = (pointer.Y - scr.height * 0.5) / (scr.height * 0.5);
          } else {
            if (this.target.elem) {
              /*图片放大时，调整相机转动的弧度*/
              this.target.ry = Math.atan2(
                this.target.elem.pc.x - this.x,
                this.target.elem.pc.z - this.z
              );

            }
          }
          this.target.rx *= 0.9;
        }
      });

      camera.z = -10000;
      camera.py = 0;
      // ---- create images ----
      diapo = [];
      for (var i = 0; i < json.imgdata.length; i++) {
        diapo.push(
          new Diapo(
            json.options.imagesPath,
            json.imgdata[i],
            json.structure
          )
        );
      }

      // ---- start engine ---- >>>
      setInterval(function () {
        // console.log("fps： %d  quality: %o",fps,quality)
        quality = (fps > 50) ? [2, 3] : [1, 2];
        fps = 0;
      }, 1000);

      run();
    },

    // ---- main loop ----
    run = function () {
      // ---- clear screen ----
      ctx.clearRect(0, 0, scr.width, scr.height);
      // ---- camera ----
      camera.move();
      // ---- draw layers ----
      var k = 0, l;
      while ((l = layers[k])) {
        light = false;
        var i = 0, d;
        while ((d = diapo[i])) {
          (l === 1 && d.draw()) || (d.visible && d.poly[k].draw());
          i++;
        }
        k++;
      }

      // ---- cursor ----
      /*      if (camera.over && !pointer.isDraging) {
              scr.setCursor("pointer");
            } else {
              scr.setCursor("move");
            }*/
      // ---- loop ----
      fps++;
      requestAnimFrame(run);
    };
  /* ==== prototypes ==== */
  Poly.prototype.draw = function () {
    // ---- color light ----
    var c = this.color;
    if (c.light || !light) {
      var s = c.light ? this.parent.light : 1;
      // ---- rgba color ----
      light = "rgba(" +
        Math.round(c.r * s) + "," +
        Math.round(c.g * s) + "," +
        Math.round(c.b * s) + "," + (c.a || 1) + ")";
      ctx.fillStyle = light;
    }
    // ---- paint poly ----
    if (!c.light || this.parent.light < 1) {
      // ---- projection ----
      for (
        var i = 0; this.points[i++].projection();
      ) ;
      this.drawPoly();
      ctx.fill();
    }
  }
  /* ==== image onload ==== */
  Diapo.prototype.loaded = function (img) {
    // ---- create points ----
    var d = [-1, 1, 1, -1, 1, 1, -1, -1];
    var w = img.texture.width * 0.5;
    var h = img.texture.height * 0.5;
    for (var i = 0; i < 4; i++) {
      img.points[i] = new ge1doot.transform3D.Point(
        this.pc.x + (w * this.normalZ * d[i]),
        this.pc.y + (h * d[i + 4]),
        this.pc.z + (w * this.normalX * d[i])
      );
    }
  }
  /* ==== images draw ==== */
  Diapo.prototype.draw = function () {
    // ---- visibility ----
    this.pc.projection();
    if (this.pc.Z > -(camera.focalLength >> 1) && this.img.transform3D(true)) {
      // ---- light ----
      //显示背景墙
      this.light = 0.5 + Math.abs(this.normalZ * camera.cosY - this.normalX * camera.sinY) * 0.6;
      // ---- draw image ----
      this.visible = true; //显示图片背景屏，注释掉则只剩图片
      this.img.draw(); //展示图片
      // ---- test pointer inside ----
      if (pointer.hasMoved || pointer.isDown) {
        if (
          this.img.isPointerInside(
            pointer.X,
            pointer.Y
          )
        ) {
          camera.over = this;
        }

      }
    } else {
      this.visible = false;
    }
    return true;
  }

  /** 初始化轮播图插件配置，第一次运行，只执行一次*/
  function initEnv(imgArr) {
    init({
      imgdata: imgFactory(imgArr),
      structure: structureValue,
      options: {imagesPath: ''}
    });
  }

  /** 重置幻灯片图片*/
  function resetImages(imgArr) {
    diapo = [];
    var newImgArr = imgFactory(imgArr)

    for (var i = 0; i < newImgArr.length; i++) {
      diapo.push(new Diapo('', newImgArr[i], structureValue));
    }

    resumePlay();
  }

  /**重头开始播放*/
  function resumePlay() {
    vm.currIndex = 0;
    vm.pauseFlag = false; //取消暂停
    if (diapo[0]) {
      camera && (camera.target.elem = camera.over = diapo[0])
      pointer.setup.tap && pointer.setup.tap();
    }
  }

  /*回归正轨操作，为了正常自转*/
  function getBackOnTrack() {
    if (!camera) return;
    if (camera.over) {
      camera.target.elem = camera.over;
      pointer.setup.tap && pointer.setup.tap();
    } else if (diapo[0]) {
      camera.target.elem = camera.over = diapo[0]
      pointer.setup.tap && pointer.setup.tap();
    }
  }


  /*定时器1，启动自转风格定时器*/
  function enableRotationStyleTimer() {
    console.log("启动定时器rotationStyleTimer");
    getBackOnTrack();
    vm.rotationStyleTimer = setTimeout(rotationStyleTimer, vm.rotationSpeed);
  }

  function rotationStyleTimer() {
    if (vm.showType == 2) return;

    /*相机转动的弧度，一圈为2PI*/
    camera.target.ry = camera.target.ry >= Math.PI * 3 / 2 ? 0 : camera.target.ry += Math.PI / 2;

    vm.rotationStyleTimer = setTimeout(rotationStyleTimer, vm.rotationSpeed);
  }


  /**定时器2，动态展示轮转图*/
  function enableHotImageStyleTimer() {
    console.log("启动定时器hotImageStyleTimer");
    vm.pauseFlag = false;

    hotImageStyleTimer();
    vm.hotImageStyleTimer = setTimeout(hotImageStyleTimer, vm.waitEnlargeTime);
  }

  function hotImageStyleTimer() {
    if (vm.showType != 2) return;

    //播放前十大销售或前十大试穿
    if (!vm.pauseFlag && vm.currIndex < diapo.length) {

      camera.over = vm.currDiapo = diapo[vm.currIndex];
      /*如果两个对象相同，则执行回退动作*/
      if (camera.over == camera.target.elem) {
        //循环播放，切换下一张图片，超出数组边界置0
        var nextIndex = vm.currIndex + 1;
        vm.currIndex = nextIndex >= diapo.length ? 0 : nextIndex;
        //执行当前图片的缩回动作
        pointer.setup.tap && pointer.setup.tap();

        //一轮图播放完，通知更换新一轮图,或到拼接位置结束
        if (nextIndex >= diapo.length || diapo[nextIndex].concatFlag) {
          vm.playNextGroup();
        } else {
          //延时放大
          vm.hotImageStyleTimer = setTimeout(hotImageStyleTimer, vm.waitEnlargeTime);
        }

      } else {
        //执行当前图片的放大动作
        pointer.setup.tap && pointer.setup.tap();
        //暂停标志，等待当前素材播放结束后取消
        vm.pauseFlag = true;
        //通知父组件当前放大图片的id，用于获取素材展示
        vm.$emit("listenCurrImage", vm.currDiapo.oImage);

        //3秒后继续执行，判断是否取消暂停
        vm.hotImageStyleTimer = setTimeout(hotImageStyleTimer, 3000);
      }

    } else {

      vm.hotImageStyleTimer = setTimeout(function () {
        hotImageStyleTimer();
      }, 1000)
    }

  }

</script>

<style scoped>
    #canvas {
        width: 100%;
        height: 100%;
    }
</style>