
//获取应用实例
var app = getApp();
Page({
  data: {
    canIUse: wx.canIUse('button.open-type.getUserInfo'),
    remind: '加载中',
    angle: 0,
    userInfo: {},
    copyright:'',
  },
  onLoad:function(){
    var that = this
    wx.setNavigationBarTitle({
      title: wx.getStorageSync('mallName')
    }),
    that.setData({
      copyright: wx.getStorageSync('copyright')
    })
  },
  onShow:function(){

  },
  onReady: function(){
    var that = this;
    setTimeout(function(){
      that.setData({
        remind: ''
      });
    }, 1000);
    wx.onAccelerometerChange(function(res) {
      var angle = -(res.x*30).toFixed(1);
      if(angle>14){ angle=14; }
      else if(angle<-14){ angle=-14; }
      if(that.data.angle !== angle){
        that.setData({
          angle: angle
        });
      }
    });
  },

 /**
  * 授权登录获取用户信息
  */
  bindGetUserInfo: function (e) {
   // console.log(e) //"7839e46b306739cb1ed131ae5e235fb446f398f6"
    app.globalData.userInfo = e.detail.userInfo;
    if (app.globalData.userInfo){
     app.checkToken();
      wx.switchTab({
        url: '/page/component/index',
      });
    } 
  },


 /* getPhoneNumber: function (e) {
    console.log(e.detail.errMsg)
    console.log(e.detail.iv)
    console.log(e.detail.encryptedData)
    if (e.detail.errMsg == 'getPhoneNumber:fail user deny') {
      wx.showModal({
        title: '提示',
        showCancel: false,
        content: '未授权',
        success: function (res) { }
      })
    } else {
      wx.showModal({
        title: '提示',
        showCancel: false,
        content: '同意授权',
        success: function (res) { }
      })
    }
  }  */
});