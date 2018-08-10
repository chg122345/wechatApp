// page/component/orders/orders.js
var app=getApp();
Page({
  data:{
    address:{},
    hasAddress: false,
    total:0, //总价
    orders:[], //订单
  },
  onLoad:function(){
  var that=this;
  var orders=[];
      var carts = wx.getStorageSync("carts");
      for (let i = 0; i < carts.length; i++) {
        if (carts[i].selected) {
          orders.push(carts[i]);
        }
      }
      that.setData({
        orders: orders,
      });
  },
  onReady() {
    this.getTotalPrice();
  },
  
  onShow:function(){
    const self = this;
    wx.getStorage({
      key:'address',
      success(res) {
        self.setData({
          address: res.data,
          hasAddress: true
        })
      }
    })
  },

  /**
   * 计算总价
   */
  getTotalPrice() {
    let orders = this.data.orders;
    let total = 0;
    for(let i = 0; i < orders.length; i++) {
      total += orders[i].number * orders[i].goods.price;
    }
    this.setData({
      total: total
    })
  },

  toPay() {
    if(!this.data.orders.length){
      wx.showModal({
        title: '提示',
        content: '当前还没有订单信息，请先选择要购买的商品..',
        showCancel: false
      })
      return;
    }
    var address=this.data.address;
   // console.log(address);
    if (!(address.name && address.phone && address.detail)){
      wx.showModal({
        title: '提示',
        content: '请填写详细收获地址资料',
        showCancel: false
      })
      return;
    }
    var item={};
    var items=[];
    for (var i=0; i < this.data.orders.length;i++){
      item = { goods: this.data.orders[i].goods, number: this.data.orders[i].number}
      items.push(item)
    }
    //console.log(JSON.stringify(items));
    //发送请求前验证token
    var token = app.checkToken();
    wx.request({
      url: app.globalData.apiUrl +'orderInfo',
      header: {
         'Content-Type': 'application/x-www-form-urlencoded'
       },
      method:'POST',
      data:{
        token: token,
        items: JSON.stringify(items),
        address: JSON.stringify(address),
      },
     success:function(res){
    // console.log(res.data);
     //生成订单成功清除购物项
     if(res.data.code==200){
       var carts = wx.getStorageSync('carts');
       var carts1 = [];
       for (let i = 0; i < carts.length; i++) {
         if (!carts[i].selected) {
           carts1.push(carts[i]);
         }
       }
       //清除缓存
       wx.removeStorageSync('carts');
       wx.setStorageSync('carts', carts1);
       //跳转订单页
       wx.switchTab({
         url: '/page/component/user/user'
       });
     } else if (res.data.code == 400){
       wx.showModal({
         title: '提示',
         content: '提交订单失败，请稍后再试..',
         showCancel:false,
       })
       return;
     }else{
       wx.showModal({
         title: '提示',
         content: '未知错误..',
         showCancel: false,
       })
       return;
     }
    //  wx.showModal({
    //   title: '提示',
    //   content: '本系统只做演示，支付系统已屏蔽',
    //   text:'center',
    //   complete() {
    //     wx.switchTab({
    //       url: '/page/component/user/user'
    //     })
    //   }
    // })
     }
    })
  }
})