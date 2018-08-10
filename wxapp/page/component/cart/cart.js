// page/component/new-pages/cart/cart.js
var app=getApp();
Page({
  data: {
    carts:[],               // 购物车列表
    hasList:false,          // 列表是否有数据
    totalPrice:0,           // 总价，初始为0
    selectAllStatus:false,    // 全选状态，默认不全选
    obj:{
        name:"hello"
    }
  },
  onLoad: function (options) {
    var that=this;
    const num = parseInt(options.num);
    if (options.id!=null){
      wx.request({
        url: app.globalData.apiUrl + 'goods',
        method: 'GET',
        data: {
          id: options.id,
        },
        success: function (res) {
          //缓存级别
          var carts1 = wx.getStorageSync('carts')||[];
          console.log("cart00" + carts1);
            var goods = res.data.data.goodsInfo;
            var number = num;
            var selected = true;
            var items = { goods: goods, number: number, selected: selected };
            carts1.push(items);
            wx.setStorageSync('carts', carts1);
             that.setData({
               hasList: true,
               carts: carts1
             });
        }
      }); 
    }
    that.getTotalPrice();
  },
   onShow() {
     var carts0 = wx.getStorageSync('carts');
     if (carts0.length){
       this.setData({
         hasList: true,
         carts: carts0,
     })
     }
     this.getTotalPrice();
   },
  /**
   * 当前商品选中事件
   */
  selectList(e) {
    const index = e.currentTarget.dataset.index;
    let carts = this.data.carts;
    const selected = carts[index].selected;
    carts[index].selected = !selected;
    //清除缓存
    wx.removeStorageSync('carts')
    //重新设缓存值
    wx.setStorageSync('carts', carts);
    this.setData({
      carts: carts
    });
    this.getTotalPrice();
  },

  /**
   * 删除购物车当前商品
   */
  deleteList(e) {
    const index = e.currentTarget.dataset.index;
    let carts = this.data.carts;
    carts.splice(index,1);
    //清除缓存
    wx.removeStorageSync('carts')
    //重新设缓存值
    wx.setStorageSync('carts', carts);
    this.setData({
      carts: carts
    });
    if(!carts.length){
      this.setData({
        hasList: false
      });
    }else{
      this.getTotalPrice();
    }
  },

  /**
   * 购物车全选事件
   */
  selectAll(e) {
    let selectAllStatus = this.data.selectAllStatus;
    selectAllStatus = !selectAllStatus;
    let carts = this.data.carts;

    for (let i = 0; i < carts.length; i++) {
      carts[i].selected = selectAllStatus;
    }
    //清除缓存
    wx.removeStorageSync('carts')
    //重新设缓存值
    wx.setStorageSync('carts', carts);
    this.setData({
      selectAllStatus: selectAllStatus,
      carts: carts
    });
    this.getTotalPrice();
  },

  /**
   * 绑定加数量事件
   */
  addCount(e) {
    const index = e.currentTarget.dataset.index;
    let carts = this.data.carts;
    let num = carts[index].number;
    num = num + 1;
    carts[index].number = num;
    //清除缓存
    wx.removeStorageSync('carts')
    //重新设缓存值
    wx.setStorageSync('carts', carts);
    this.setData({
      carts: carts
    });
    this.getTotalPrice();
  },

  /**
   * 绑定减数量事件
   */
  minusCount(e) {
    const index = e.currentTarget.dataset.index;
    const obj = e.currentTarget.dataset.obj;
    let carts = this.data.carts;
    let num = carts[index].number;
    if(num <= 1){
      return false;
    }
    num = num - 1;
    carts[index].number = num;
    //清除缓存
    wx.removeStorageSync('carts')
    //重新设缓存值
    wx.setStorageSync('carts', carts);
    this.setData({
      carts: carts
    });
    this.getTotalPrice();
  },

  /**
   * 计算总价
   */
  getTotalPrice() {
    let carts = this.data.carts;                  // 获取购物车列表
    let total = 0;
    for(let i = 0; i<carts.length; i++) {         // 循环列表得到每个数据
      if (carts[i].selected) {                     // 判断选中才会计算价格
        total += carts[i].number * carts[i].goods.price;   // 所有价格加起来
      }
    }
    this.setData({                                // 最后赋值到data中渲染到页面
      carts: carts,
      totalPrice: total.toFixed(2)
    });
  }

})