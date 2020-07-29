/* ===========================================================
 * jquery-let_it_snow.js v2  修改版 @yangchuang
 * ===========================================================
 * NOTE: This plugin is based on the work by Jason Brown (Loktar00)
 *
 * As the end of the year approaches, let's add
 * some festive to your website!
 *
 *
 * ========================================================== */

!function ($) {

  var defaults = {
    speed: 0,
    size: 2, //圆形半径 || 图片宽高
    count: 100,
    opacity: 0.5,
    rgb: "255,255,255",
    windPower: 0,
  };


  (function () {
    var requestAnimationFrame = window.requestAnimationFrame || window.mozRequestAnimationFrame || window.webkitRequestAnimationFrame || window.msRequestAnimationFrame ||
      function (callback) {
        window.setTimeout(callback, 1000 / 60);
      };
    window.requestAnimationFrame = requestAnimationFrame;
  })();

  $.fn.let_it_snow = function (options) {
    var settings = $.extend({}, defaults, options),
      el = $(this),
      canvas = el.get(0),
      ctx = canvas.getContext("2d"),
      flakeCount = settings.count, //图片数量
      flakes = [];

    //创建图片对象
    var oImage = new Image();

    if (settings.imageSrc) {
      oImage.src = settings.imageSrc;
    }

    canvas.width = window.innerWidth;
    canvas.height = window.innerHeight;

    var wind = 0.01 + settings.windPower / 100;
    var windBlowing = settings.windPower ? windPower1 : windPower2;

    function windPower1(flake) {
      flake.velX += wind;
    }

    /*windPower为0*/
    function windPower2(flake) {
      flake.velX += Math.cos(flake.step += .05) * flake.stepSize;
    }

    /*图形*/
    function snow() {
      //清除画布内的像素
      ctx.clearRect(0, 0, canvas.width, canvas.height);

      for (var i = 0; i < flakeCount; i++) {
        var flake = flakes[i];

        flake.velX *= .98;
        if (flake.velY <= flake.speed) {
          flake.velY = flake.speed
        }

        windBlowing(flake);

        flake.y += flake.velY;
        flake.x += flake.velX;

        if (flake.y >= canvas.height || flake.y <= 0) {
          reset(flake);
        }

        if (flake.x >= canvas.width || flake.x <= 0) {
          reset(flake);
        }

        //填充颜色
        ctx.fillStyle = "rgba(" + settings.rgb + "," + flake.opacity + ")";
        //重置当前路径
        ctx.beginPath();
        //画圆形
        ctx.arc(flake.x, flake.y, flake.size, 0, Math.PI * 2);
        //填充圆形路径
        ctx.fill();
      }

      requestAnimationFrame(snow);
    }

    /*小图片*/
    function snow2() {
      //清除画布内的像素
      ctx.clearRect(0, 0, canvas.width, canvas.height);

      for (var i = 0; i < flakeCount; i++) {
        var flake = flakes[i];

        flake.velX *= .98;
        if (flake.velY <= flake.speed) {
          flake.velY = flake.speed
        }

        windBlowing(flake);

        flake.y += flake.velY;
        flake.x += flake.velX;

        if (flake.y >= canvas.height || flake.y <= 0) {
          reset(flake);
        }

        if (flake.x >= canvas.width || flake.x <= 0) {
          reset(flake);
        }

        //设置透明度
        ctx.globalAlpha = settings.opacity ? settings.opacity : 0.9;
        //将图片画入画布
        ctx.drawImage(oImage, flake.x, flake.y, flake.size, flake.size);
      }
      requestAnimationFrame(snow2);
    }

    function reset(flake) {

      if (!settings.windPower) {
        flake.x = Math.floor(Math.random() * canvas.width);
        flake.y = 0;
      } else {
        var xarray, yarray;

        if (settings.windPower > 0) {
          xarray = Array(Math.floor(Math.random() * canvas.width), 0);
          yarray = Array(0, Math.floor(Math.random() * canvas.height));
        } else {
          xarray = Array(Math.floor(Math.random() * canvas.width), 0);
          yarray = Array(canvas.width, Math.floor(Math.random() * canvas.height))
        }

        if (Math.floor(Math.random() * 2)) {
          flake.x = yarray[0];
          flake.y = yarray[1];
        } else {
          flake.x = xarray[0];
          flake.y = 0;
        }
      }

      flake.size = (Math.random() * 10) + settings.size;
      flake.speed = (Math.random() * 1) + settings.speed;
      flake.velY = flake.speed;
      flake.velX = 0;
      flake.opacity = Math.random() * settings.opacity + 0.01;
    }

    function init() {
      for (var i = 0; i < flakeCount; i++) {
        var x = Math.floor(Math.random() * canvas.width),
          y = Math.floor(Math.random() * canvas.height),
          size = (Math.random() * 10) + settings.size,
          speed = (Math.random() * 1) + settings.speed,
          opacity = Math.random() * settings.opacity + 0.01;

        flakes.push({
          speed: speed,
          velY: speed, //y轴偏移距离
          velX: 0,
          x: x,
          y: y,
          size: size,
          stepSize: (Math.random()) / 30,
          step: 0,
          opacity: opacity
        });
      }

      if (settings.imageSrc) {
        oImage.onload = function () {
          snow2();
        }
      } else {
        snow();
      }

    }

    init();

  }
}(window.jQuery);

