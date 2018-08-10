App({
  onLaunch: function () {
    var that = this;
    //获取商城信息
    that.getShopInfo();
    that.userLogin();
    //console.log('App Launch')
  },
 
  onShow: function () {
    console.log('App Show')
  },

  onHide: function () {
    console.log('App Hide')
  },
  /**
   * 获取商城名称
   */
  getShopInfo: function () {
    var that =this;
    //  获取商城信息
    wx.request({
      url: that.globalData.apiUrl + "shopInfo",
      success: function (res) {
        wx.setStorageSync('mallName', res.data.data.shopInfo.title);
        wx.setStorageSync('copyright', res.data.data.shopInfo.copyright);
        wx.setStorageSync('notice', res.data.data.shopInfo.notice);//
      }
    })
  },
  userLogin: function () {
    var that = this;
    wx.login({
      success: function (res) {
        wx.request({
          url: that.globalData.apiUrl+'login',
          data: {
            code: res.code
          },
          success: function (res) {
            if (res.data.code == 10000) {
              // 去注册
              that.registerUser();
              return;
            }
            that.globalData.token = res.data.data.token;
          //  console.log(res.data)
           // that.globalData.uid = res.data.data.uid;
          }
        })
      }
    })
  },

  //获取用户信息
  getUserInfo: function (cb) {
    var that = this
    if (this.globalData.userInfo) {
      typeof cb == "function" && cb(this.globalData.userInfo)
    } else {
      //调用登陆接口
      wx.login({
        success: function () {
          wx.getUserInfo({
            success: function (res) {
              that.globalData.userInfo = res.userInfo
              typeof cb == "function" && cb(that.globalData.userInfo)
            }
          })
        }
      })
    }
  },

  checkToken:function(){
    var token = this.globalData.token;
   // console.log('apptoken--->' + token);
    if (token) {
      wx.request({
        url: this.globalData.apiUrl+'checkToken',
        data: {
          token: token
        },
        success: function (res) {
       //   console.log(res.data);
          if (res.data.code != 200) {
            this.globalData.token = null;
            this.userLogin();
          }
        }
      })
    }
    return this.globalData.token;
  },
  globalData: {
    hasLogin: false,
    userInfo:null,
   //apiUrl:'http://120.78.131.95:8888/api/',
    apiUrl: 'http://127.0.0.1:8888/api/',
    shareProfile: '分享健康，分享快乐！',
    token:'',
  }
})
