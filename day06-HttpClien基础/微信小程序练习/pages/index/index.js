// index.js
Page({
  // data用于存数据
  data:{
    msg:'测试',
    avatarUrl:'',
    nickName:'',
    gender:'',
    code:'',
    status:''
  },
  // 获取微信用户头像昵称
  getUserInfo(){
    wx.getUserProfile({
      desc: '获取用户信息',
      success:(res)=>{
        console.log(res.userInfo)
        const g = res.userInfo.gender === 1 ? '男' : (res.userInfo.gender === 2? '女':'未知')
        //属性赋值
        this.setData({
          avatarUrl: res.userInfo.avatarUrl,
          nickName: res.userInfo.nickName,
          gender: g
        })
      }
    })
  },
  //微信登录，获取用户授权码
  wxLogin(){
    wx.login({
      success: (res) => {
        console.log(res.code)
        this.setData({
          code: res.code
        })
      },
    })
  },
  //发送异步请求给后端
  sendRequest(){
    wx.request({
      url: 'http://localhost:8080/user/shop/status',
      method: 'GET',
      success: (res) => {
        console.log(res.data)
        const s = res.data.data === 1? '营业中':'打烊中'
        this.setData({
          status: s
        })
      }
    })
  }
})
