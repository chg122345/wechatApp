// page/component/new-pages/user/user.js
var app=getApp();
Page({
  data:{
    thumb:'',
    nickname:'',
    hasOrder: false,
    orders:[],
    hasAddress:false,
    address:{},
  },
  onLoad(){
    var self = this;
    /**
     * 获取用户信息
     */
    if (app.globalData.userInfo!=null){
      self.setData({
        thumb: app.globalData.userInfo.avatarUrl, //头像地址
        nickname: app.globalData.userInfo.nickName
      });
    }
  },
  onShow(){
    var self = this;
    /**
    * 发起请求获取订单列表信息
    */
    self.getOrderInfo();
    //判断是否有订单
    if (self.data.orders.length) {
      self.setData({
        hasOrder: true,
      })
    }
    /**
     * 获取本地缓存 地址信息
     */
    wx.getStorage({
      key: 'address',
      success: function(res){
        self.setData({
          hasAddress: true,
          address: res.data
        })
      }
    })
  },
  /**
   * 下拉刷新
   */
  onPullDownRefresh: function () {
    var that = this;
    wx.showToast({
      title: '加载中...',
      icon: 'loading',
      duration: 10000,
    })
    setTimeout(() => {
      wx.hideToast();
      that.getOrderInfo();
    }, 3000)
    wx.stopPullDownRefresh();
    wx.hideNavigationBarLoading();
  },
  getOrderInfo:function(){
    var that=this;
    var token = app.checkToken();
  //  console.log('token' + token);
    wx.request({
       url: app.globalData.apiUrl+'orderInfo',
      method: 'GET',
      data: {
        token: token,
      },
      success: function (res) {
      //  console.log(res.data)
      if(res.data.code==200){
        that.setData({
          hasOrder: true,
          orders: res.data.data.userOrder
        })
      }  
      }
    })
  },
  /**
   * 发起支付请求
   */
  payOrders:function(){
    wx.requestPayment({
      timeStamp: 'String1',
      nonceStr: 'String2',
      package: 'String3',
      signType: 'MD5',
      paySign: 'String4',
      success: function(res){
        console.log(res)
      },
      fail: function(res) {
        wx.showModal({
          title:'支付提示',
          content:'暂未接入支付',
          showCancel: false
        })
      }
    })
  },
  /**
   * 收货
   */
  updateOrders:function(e){
   // console.log("收获" + e.currentTarget.dataset.id);
    var id = e.currentTarget.dataset.id;
    wx.request({
      url: app.globalData.apiUrl+'orderInfo',
      method: 'PUT',
      data:{
        id:id,
        status:4,
      },
      success: function (res) {
       //  console.log("更新成功" + JSON.stringify(res));
        if (res.data.code == 200) {
          wx.showToast({
            title: '成功',
            icon: 'success',
            duration: 2000,
          });
          wx.redirectTo({
            url: '/page/component/user/user',
          })
        }
      }
    })
  },
  /**
   * 删除订单
   */
  delOrders: function (e) {
    wx.showModal({
      title: '警告',
      content: '确定要删除该条订单记录吗?',
      success:function(res){
        if(res.confirm){
          var id = e.currentTarget.dataset.id;
          wx.request({
            url: app.globalData.apiUrl + 'orderInfo?id=' + id,
            method: 'DELETE',
            success: function (res) {
              // console.log("删除成功" + JSON.stringify(res));
              if (res.data.code == 200) {
                wx.showToast({
                  title: '成功',
                  icon: 'success',
                  duration: 2000,
                }); 
                wx.redirectTo({
                  url: '/page/component/user/user',
                })
              }
            }
          });
        }
      }
    });
  },
  aboutMe:function(){
  wx.showToast({
    title: 'bug联系 80588183@qq.com',
    icon:'none',
    duration:3000,
  })
  },
})