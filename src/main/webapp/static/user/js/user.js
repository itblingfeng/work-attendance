$(function () {
   $.ajax({
       type: "POST",
       dataType: "json",
       data: {},
       contentType: "application/json",
       url: "/user/userInfo",
       success:function (data) {
               $("#realname").html(data.data.realName);
           $("#userimg").attr("src",data.data.headImage);
           $("#realname_left").html(data.data.realName);
           $("#userimg_left").attr("src",data.data.headImage);
       }
   });
});