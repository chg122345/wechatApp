var app = getApp();
Page({
  data: {
    imgUrls: [
      '/image/b1.jpg',
      '/image/b2.jpg',
      '/image/b3.jpg'
    ],
    noticeList:{
      dataList:[
      { title: '小商城上线了..'},
      { title: '大量优惠，速来抢购吧.' },
      { title: '最新优惠03' },
      { title: '最新优惠04' },
    ]}, //公告
    categories:[
      { id: 0, name: "全部" },
      { id: 1, name: "鲜果" },
      { id: 2, name: "蔬菜" },
      { id: 3, name: "肉类" },
      { id: 4, name: "其他" },
    ],//分类
    activeCategoryId: 0,
    indicatorDots: false,
    autoplay: false,
    interval: 3000,
    duration: 800,
    goods:[],
    copyright:'',
  },
  onLoad: function () {
    var that = this
    //设置标题
    wx.setNavigationBarTitle({
      title: wx.getStorageSync('mallName')
    });
    that.getGoodsInfo();
    that.getNotice();
  },

  onShow: function () {
    var that=this;
   
  },
  /**
   * 下拉刷新
   */
  onPullDownRefresh:function() {
    var that=this;
    wx.showToast({
      title: '加载中...',
      icon:'loading',
      duration:10000,
    })
    setTimeout(() => {
     wx.hideToast();
     that.getGoodsInfo();
     that.getNotice();
    },3000)
    wx.stopPullDownRefresh();
    wx.hideNavigationBarLoading();
  },
  getGoodsInfo:function(){
   var that=this;
   wx.request({
    url: app.globalData.apiUrl + 'goodsInfo',
    success:function(res){
      that.setData({
        goods: []
      });
      var goods = [];
      for(var i= 0;i<res.data.data.goodsInfo.length;i++){
      goods.push(res.data.data.goodsInfo[i]);
      }
      //获取商品信息注入
     that.setData({
       goods: goods,
     });
   //  console.log(goods);
    }
  })
  },
  /**
   * 获取公告
   */
  getNotice:function(){
    var that=this;
    //公告
    var notice = wx.getStorageSync('notice');
    var notices = notice.toString().split('|');
    var dataList = [];
    for (var i = 0; i < notices.length; i++) {
      dataList.push({ title: notices[i] });
    }
    that.setData({
      noticeList: { dataList },
      copyright: wx.getStorageSync('copyright')
    });
  },
  //商品詳情
  toDetailsTap: function (e) {
    wx.navigateTo({
      url: "details/details?id=" + e.currentTarget.dataset.id
    })
  },
  /**
   * 分享
   */
  onShareAppMessage: function () {
    return {
      title: wx.getStorageSync('mallName') + '——' + app.globalData.shareProfile,
      path: '/page/start/start',
      success: function (res) {
        // 转发成功
        wx.showToast({
          title: '分享成功！',
        })
      },
      fail: function (res) {
        // 转发失败
        wx.showModal({
          title: '提示',
          content: '分享失败了..',
          showCancel: false,
        })
      }
    }
  },
 
  tabClick: function (e) {
    var that=this;
   // console.log("获取id"+e.currentTarget.id)
    var type = e.currentTarget.id;
    this.setData({
      activeCategoryId: e.currentTarget.id
    });
    if(type>0){
      that.getGoodsByType(type);
    }else{
      that.getGoodsInfo();
    }
  },
  getGoodsByType:function(type){
    var that=this;
    wx.request({
      url: app.globalData.apiUrl + 'goodsType',
      data:{
        type:type,
      },
      method:'GET',
      success:function(res){
          if(res.data.code==200){
           // console.log("分类商品" +JSON.stringify(res.data.data.goodsInfo));
            that.setData({
              goods: res.data.data.goodsInfo,
            });
          }
      },
    });
  }
})