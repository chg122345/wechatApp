// page/component/list/list.js
var app=getApp();
Page({
  data:{
    img:'',
    goods: [],
  },
  onLoad:function(options){
    // 页面初始化 options为页面跳转所带来的参数
    var that=this;
    console.log("list获取id-->" + options.id)
    var id = options.id;
    if(id==0){
     that.setData({
       img:'/image/list1.png',
     });
    }else if(id==1){
      that.setData({
        img: '/image/list.jpg',
      });
    } else if (id == 2) {
      that.setData({
        img: '/image/list1.jpg',
      });
    }
    that.getGoodsByType(id);
  },
  onReady:function(){
    // 页面渲染完成
  },
  onShow:function(){
    // 页面显示
  },
  onHide:function(){
    // 页面隐藏
  },
  onUnload:function(){
    // 页面关闭
  },
  getGoodsByType: function (type) {
    var that = this;
    wx.request({
      url: app.globalData.apiUrl + 'goodsType',
      data: {
        type: type,
      },
      method: 'GET',
      success: function (res) {
        if (res.data.code == 200) {
          // console.log("分类商品" +JSON.stringify(res.data.data.goodsInfo));
          that.setData({
            goods: res.data.data.goodsInfo,
          });
        }
      },
    });
  }
})