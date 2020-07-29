<template>
    <div class="hot_box">
        <div class="groupBox" ref="group_box"></div>
    </div>
</template>

<script>

  var $ = window.jQuery;

  export default {
    name: 'HotPlay',
    props: {
      originalImages: {type: Array, default: () => [], required: true},
      showType: {type: Number, default: () => 0, required: true}
    },
    data: function () {
      return {
        evn: {
          width: 0
        },
        groupBox: null,
        /*1组3张图*/
        groupCount: 3,
        /*组集合*/
        groupList: [],
        /*前十大数据变化后，组数据先存此*/
        groupListReady: [],


        hIndex: 0,
        hotImagesPool: [],

      }
    },
    created: function () {
    },
    mounted: function () {
      console.log("前十大轮播组件#2 初始化完成...");
      this.groupBox = this.$refs.group_box;
    },
    methods: {
      /*对图片原数据进行加工，使符合规范*/
      imgFactory: function (imgArr) {
        //格式化源数据
        imgArr.forEach((item, index) => {
          var path = window.LocalPath + "/Resource/ShoesResource/ShoesIcons/" + item.PeriodSeq + "/" + item.ShoesID + ".jpg";
          item.path = path;
          item.hotTitle = "top" + (index + 1);

          item.PlayData && item.PlayData.forEach(item2 => {
            var path2 = window.LocalPath + item2.RelativeURL + "/" + item2.name;
            item2.path = path2;
          })
        })

        //数量处理
        var len = imgArr.length;
        var count = this.groupCount;
        this.hotImagesPool = [];

        if (len < count) {
          //缺少的数
          var que = count - len;

          //长度不足，补足
          if (que <= len) {
            this.hotImagesPool = imgArr.concat(imgArr.slice(0, que));
          } else {
            //缺少的个数 > 数组长度
            var quot = que / len;
            var tmpArr = [];
            for (var i = 0; i < quot + 1; i++) {
              tmpArr = tmpArr.concat(imgArr);
            }
            this.hotImagesPool = imgArr.concat(tmpArr.slice(0, que));
          }

        } else if (len == count) {
          this.hotImagesPool = imgArr;
        } else {
          //长度大于count，结果为count倍数
          var yu = len % count;
          this.hotImagesPool = imgArr.concat(imgArr.slice(0, count - yu));
        }

        //拼接标记
        len != count && (this.hotImagesPool[len].concatFlag = true);

        this.groupListReadyHandler();
      },
      groupListReadyHandler: function () {
        var bei = this.hotImagesPool.length / this.groupCount;

        for (var i = 0; i < bei; i++) {
          var group = document.createElement("div");
          group.classList.add("group");
          var start = this.groupCount * i;
          for (var j = start; j < start + this.groupCount; j++) {
            var image = new Image();
            image.src = this.hotImagesPool[j].path;
            $(image).data("oImage", this.hotImagesPool[j]);
            group.appendChild(image);
          }

          this.groupListReady.push(group);
        }

      },
      initEnv: function () {
        this.groupList = this.groupListReady.slice(0);
        this.groupBox.innerHTML = "";
        for (var i in this.groupList) {
          this.groupBox.appendChild(this.groupList[i]);
        }
      },
      restartPlayHotImages:function () {
        
      }
    },
    computed: {},
    watch: {
      originalImages: {
        handler: function (newArr) {
          newArr && newArr.length > 0 && this.imgFactory(newArr);
        },
        deep: true,
      },
      showType: function (val, old) {

      }

    }

  }
</script>


<style scoped>
    .hot_box {
        width: 100%;
        height: 19%;
        position: relative;
        z-index: 1;
    }

    .groupBox{
        width: 100%;
        height: 100%;
        position: relative;
    }

    .group {
        padding-top: 10%;
        width: 100%;
        height: 100%;
        position: absolute;
        left: 0;
        top: 0;
    }

    .group img {
        width: 25%;
        margin-left: 6%;
    }

</style>