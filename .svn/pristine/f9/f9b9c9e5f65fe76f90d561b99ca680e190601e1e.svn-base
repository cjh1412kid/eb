////////////////////////////////////////////////////////////
// ============= micro HTML5 library =====================
// @author Gerard Ferrandez / http://www.dhteumeuleu.com/
// last update: May 27, 2013
// Released under the MIT license
// http://www.dhteumeuleu.com/LICENSE.html
////////////////////////////////////////////////////////////

// ===== ge1doot global =====

var ge1doot = ge1doot || {
  json: null,
  screen: null,
  pointer: null,
  camera: null,
  loadJS: function (url, callback, data) {
    if (typeof url == "string") url = [url];

    var load = function (src) {
      var script = document.createElement("script");
      if (callback) {
        if (script.readyState) {
          script.onreadystatechange = function () {
            if (script.readyState == "loaded" || script.readyState == "complete") {
              script.onreadystatechange = null;
              if (--n === 0) callback(data || false);
            }
          }
        } else {
          script.onload = function () {
            if (--n === 0) callback(data || false);
          }
        }
      }
      script.src = src;
      document.getElementsByTagName("head")[0].appendChild(script);
    }

    for (var i = 0, n = url.length; i < n; i++) load(url[i]);
  }
}

// ===== html/canvas container =====
/* setup : {container: "canvas"} */
ge1doot.Screen = function (setup) {
  ge1doot.screen = this;
  /* canvas元素 */
  this.elem = document.getElementById(setup.container) || setup.container;
  /*getContext() 方法可返回一个对象，该对象提供了用于在画布上绘图的方法和属性*/
  this.ctx = this.elem.tagName == "CANVAS" ? this.elem.getContext("2d") : false;
  this.style = this.elem.style;
  this.left = 0;
  this.top = 0;
  this.width = 0;
  this.height = 0;
  this.cursor = "default";
  this.setup = setup;

  this.resize = function () {
    var o = this.elem;
    this.width = o.offsetWidth;
    this.height = o.offsetHeight;
    for (this.left = 0, this.top = 0; o != null; o = o.offsetParent) {
      this.left += o.offsetLeft;
      this.top += o.offsetTop;
    }
    if (this.ctx) {
      this.elem.width = this.width;
      this.elem.height = this.height;
    }
    this.setup.resize && this.setup.resize();
  }

  this.setCursor = function (type) {
    if (type !== this.cursor && 'ontouchstart' in window === false) {
      this.cursor = type;
      this.style.cursor = type;
    }
  }

  window.addEventListener('resize', function () {
    ge1doot.screen.resize();
  }, false);
  !this.setup.resize && this.resize();
}


// ==== unified touch events handler ====
ge1doot.Pointer = function (setup) {
  ge1doot.pointer = this;
  var self = this;
  var body = document.body;
  var html = document.documentElement;
  /*{tap:func} setup为包含tap函数的对象*/
  this.setup = setup;
  this.screen = ge1doot.screen;
  this.elem = this.screen.elem;
  /*触发点位于画布内的坐标*/
  this.X = 0;
  this.Y = 0;
  /*xi，yi 记录照相机视角*/
  this.Xi = 0;
  this.Yi = 0;
  /*xi，yi 的备份，记录转动后的位置*/
  this.bXi = 0;
  this.bYi = 0;
  /*触发点位于浏览器窗口的坐标*/
  this.Xr = 0;
  this.Yr = 0;

  this.startX = 0;
  this.startY = 0;
  this.scale = 0;
  this.wheelDelta = 0;
  this.isDraging = false;
  this.hasMoved = false;
  this.isDown = false;
  this.evt = false;
  /*鼠标松开时指针位于画布内的位置*/
  var sX = 0;
  var sY = 0;

  // prevent default behavior
  /*阻止点击画布的默认行为*/
  if (setup.tap) this.elem.onclick = function () {
    return false;
  }

  if (!setup.documentMove) {
    document.ontouchmove = function (e) {
      e.preventDefault();
    }
  }

  document.addEventListener("MSHoldVisual", function (e) {
    e.preventDefault();
  }, false);

  if (typeof this.elem.style.msTouchAction != 'undefined') this.elem.style.msTouchAction = "none";

  this.pointerDown = function (e) {

    if (!this.isDown) {
      if (this.elem.setCapture) this.elem.setCapture();
      this.isDraging = false;
      this.hasMoved = false;
      this.isDown = true;
      this.evt = e;
      this.Xr = (e.clientX !== undefined ? e.clientX : e.touches[0].clientX);
      this.Yr = (e.clientY !== undefined ? e.clientY : e.touches[0].clientY);
      this.X = sX = this.Xr - this.screen.left;
      this.Y = sY = this.Yr - this.screen.top + ((html && html.scrollTop) || body.scrollTop);

      this.setup.down && this.setup.down(e);
      // console.log("[pointerDown] Xr: %s  Yr: %s  X: %s  Y: %s",this.Xr,this.Yr,this.X,this.Y);
    }
  }

  this.pointerMove = function (e) {
    /*点击状态下才可以移动*/
    if (!this.isDown) return;

    this.Xr = (e.clientX !== undefined ? e.clientX : e.touches[0].clientX);
    this.Yr = (e.clientY !== undefined ? e.clientY : e.touches[0].clientY);
    this.X = this.Xr - this.screen.left;
    this.Y = this.Yr - this.screen.top + ((html && html.scrollTop) || body.scrollTop);

    if (this.isDown) {
      /*
      this.X - sX 求相对于点击点的x轴偏移量
      this.Y - sY 求相对于点击点的y轴偏移量
      */
      this.Xi = this.bXi + (this.X - sX);//向左转为负，向右为正,指针移动方向
      this.Yi = this.bYi - (this.Y - sY);//向上为正，向下为负
      // console.log("[move] Xi: %s  Yi: %s  (bXi: %s  bYi: %s)", this.Xi, this.Yi, this.bXi, this.bYi);
    }

    if (Math.abs(this.X - sX) > 11 || Math.abs(this.Y - sY) > 11) {
      // console.log("偏移量超过11")
      /*偏移量超过11才算移动*/
      this.hasMoved = true;
      if (this.isDown) {
        if (!this.isDraging) {
          this.startX = sX;
          this.startY = sY;
          this.setup.startDrag && this.setup.startDrag(e);
          this.isDraging = true;
        } else {
          this.setup.drag && this.setup.drag(e);
        }
      } else {
        /*未点击拖动，随指针移动触发*/
        sX = this.X;
        sY = this.Y;
      }

    }
    // console.log("this.setup.move :" ,this.setup.move)
    //this.setup.move   undefined
    this.setup.move && this.setup.move(e);
  }

  this.pointerUp = function (e) {
    if (!this.isDown) return;

    this.bXi = this.Xi;
    this.bYi = this.Yi;
    if (!this.hasMoved) {
      this.X = sX;
      this.Y = sY;
      /*偏移量小于11*/
      /*点击画布触发该方法*/
      this.setup.tap && this.setup.tap(this.evt);
    } else {
      //移动状态
      this.setup.up && this.setup.up(this.evt);
    }
    this.isDraging = false;
    this.isDown = false;
    this.hasMoved = false;
    this.setup.up && this.setup.up(this.evt);
    if (this.elem.releaseCapture) this.elem.releaseCapture();
    this.evt = false;
    // console.log("[pointerUp] Xi: %s  Yi: %s  (bXi: %s  bYi: %s)", this.Xi, this.Yi, this.bXi, this.bYi);
  }

  this.pointerCancel = function (e) {
    if (this.elem.releaseCapture) this.elem.releaseCapture();
    this.isDraging = false;
    this.hasMoved = false;
    this.isDown = false;
    this.bXi = this.Xi;
    this.bYi = this.Yi;
    sX = 0;
    sY = 0;
    // console.log("[pointerCancel] Xi: %s  Yi: %s  (bXi: %s  bYi: %s)", this.Xi, this.Yi, this.bXi, this.bYi);

  }


  if ('ontouchstart' in window) {
    this.elem.ontouchstart = function (e) {
      self.pointerDown(e);
      return false;
    }
    this.elem.ontouchmove = function (e) {
      self.pointerMove(e);
      return false;
    }
    this.elem.ontouchend = function (e) {
      self.pointerUp(e);
      return false;
    }
    this.elem.ontouchcancel = function (e) {
      self.pointerCancel(e);
      return false;
    }
  }
  document.addEventListener("mousedown", function (e) {


    if (
      e.target === self.elem ||
      (e.target.parentNode && e.target.parentNode === self.elem) ||
      (e.target.parentNode.parentNode && e.target.parentNode.parentNode === self.elem)
    ) {
      if (typeof e.stopPropagation != "undefined") {
        e.stopPropagation();
      } else {
        e.cancelBubble = true;
      }
      e.preventDefault();
      self.pointerDown(e);
    }
  }, false);
  document.addEventListener("mousemove", function (e) {
    self.pointerMove(e);
  }, false);
  document.addEventListener("mouseup", function (e) {
    self.pointerUp(e);
  }, false);
  document.addEventListener('gesturechange', function (e) {
    e.preventDefault();
    if (e.scale > 1) {
      self.scale = 0.1;
    } else if (e.scale < 1) {
      self.scale = -0.1;
    } else {
      self.scale = 0;
    }
    self.setup.scale && self.setup.scale(e);
    return false;
  }, false);

  if (window.navigator.msPointerEnabled) {
    var nContact = 0;
    var myGesture = new MSGesture();
    myGesture.target = this.elem;
    this.elem.addEventListener("MSPointerDown", function (e) {
      if (e.pointerType == e.MSPOINTER_TYPE_TOUCH) {
        myGesture.addPointer(e.pointerId);
        nContact++;
      }
    }, false);
    this.elem.addEventListener("MSPointerOut", function (e) {
      if (e.pointerType == e.MSPOINTER_TYPE_TOUCH) {
        nContact--;
      }
    }, false);
    this.elem.addEventListener("MSGestureHold", function (e) {
      e.preventDefault();
    }, false);
    this.elem.addEventListener("MSGestureChange", function (e) {
      if (nContact > 1) {
        if (e.preventDefault) e.preventDefault();
        self.scale = e.velocityExpansion;
        self.setup.scale && self.setup.scale(e);
      }
      return false;
    }, false);
  }
  if (window.addEventListener) this.elem.addEventListener('DOMMouseScroll', function (e) {
    if (e.preventDefault) e.preventDefault();
    self.wheelDelta = e.detail * 10;
    self.setup.wheel && self.setup.wheel(e);
    return false;
  }, false);
  this.elem.onmousewheel = function () {
    self.wheelDelta = -event.wheelDelta * .25;
    self.setup.wheel && self.setup.wheel(event);
    return false;
  }
};
// ===== request animation frame =====

window.requestAnimFrame = (function () {
  return window.requestAnimationFrame ||
    window.webkitRequestAnimationFrame ||
    window.mozRequestAnimationFrame ||
    window.oRequestAnimationFrame ||
    window.msRequestAnimationFrame ||
    function (run) {
      window.setTimeout(run, 16);
    };
})();