// page/component/new-pages/user/address/address.js
Page({
  data:{
    address:{}
  },
  onLoad(){
    var self = this;
    wx.getStorage({
      key: 'address',
      success: function(res){
        self.setData({
          address : res.data
        })
      }
    })
    wx.chooseAddress({
      success: function (res) {
        self.setData({
          address:{
            name: res.userName,
            phone: res.telNumber,
            detail: res.provinceName + res.cityName + res.countyName + res.detailInfo,
          },
        });
       /* console.log(res.userName)
        console.log(res.postalCode)
        console.log(res.provinceName)
        console.log(res.cityName)
        console.log(res.countyName)
        console.log(res.detailInfo)
        console.log(res.nationalCode)
        console.log(res.telNumber)*/
      }
    })
  },
  formSubmit(e){
    const value = e.detail.value;
    if (value.name && value.phone && value.detail){
      wx.setStorage({
        key: 'address',
        data: value,
        success(){
          wx.navigateBack();
        }
      })
    }else{
      wx.showModal({
        title:'提示',
        content:'请填写完整资料',
        showCancel:false
      })
    }
  }
})