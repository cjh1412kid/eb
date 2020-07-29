门店展示页面代码

浏览器外壳提供两个属性和四个方法

两个属性：
window.LocalPath 当前运行目录的路径
示例：file://E:/Debug/
window.AnimateName 当前需要展示的动画名称

四个方法：
1.getVideos() 获取视频播放列表

2.getMp3s() 获取背景音乐播放列表

3.getTryShoes() 获取当前正在试穿的鞋子

4.getTopShoes() 获取前十大的鞋子信息

调用方式：window.NUITE.getVideos().then(function(result){}).catch(function(error){});
