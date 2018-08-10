// page/component/details/details.js

var app = getApp();
Page({
  data:{
    goodsDetail:{},
    // goods: {
    //   id: 1,
    //   image: '/image/goods1.png',
    //   title: '新鲜梨花带雨',
    //   price: 0.01,
    //   stock: '有货',
    //   detail: '这里是梨花带雨详情。',
    //   parameter: '125g/个',
    //   service: '不支持退货'
    // },
    num: 1,
    totalNum: 0,
    hasCarts: false,
    curIndex: 0,
    show: false,
    scaleCart: false
  },
 onLoad:function(e){
   var that=this;
   that.getgoodsById(e);
 },
 //获取指定id商品详情
  getgoodsById:function(e){
    var that = this
   wx.request({
     url: app.globalData.apiUrl + 'goods',
    method:'GET',
    data: {
      id: e.id,
    },
    success: function (res) {
   //   console.log(res.data.data);
      var goodsDisplay = res.data.data.goodsInfo;
      that.setData({
        goodsDetail: goodsDisplay,
      });
    }
   })
  },
  
  addCount() {
    let num = this.data.num;
    num++;
    this.setData({
      num : num
    })
  },

  addToCart() {
    const self = this;
    const num = this.data.num;
    let total = this.data.totalNum;

    self.setData({
      show: true
    })
    setTimeout( function() {
      self.setData({
        show: false,
        scaleCart : true
      })
      setTimeout( function() {
        self.setData({
          scaleCart: false,
          hasCarts : true,
          totalNum: num + total
        })
      }, 200)
    }, 300)

  },

   bindTap(e) {
     const index = parseInt(e.currentTarget.dataset.index);
     this.setData({
       curIndex: index
     })
   },
  toCartTap:function(e){
    var that=this;
   // console.log(e);
    const id = parseInt(e.currentTarget.dataset.id);
    const num = parseInt(e.currentTarget.dataset.num);
 //   console.log(id+' '+num);
    if(num==0||!id){
      wx.showToast({
        title: '当前没有商品..',
        icon: 'none',
      })
     return;
    }
    wx.reLaunch({  
      url: "/page/component/cart/cart?id=" + id + "&num="+num,
    })
  }
})