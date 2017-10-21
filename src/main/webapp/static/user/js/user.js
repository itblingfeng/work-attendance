$(function () {

    $.ajax({
        type: "POST",
        dataType: "json",
        data: {},
        contentType: "application/json",
        url: "/user/userInfo",
        success:function (data) {
            $("#realname").html(data.realName);
            $("#userimg").attr("src",data.headImage);
            $("#realname_left").html(data.realName);
            $("#userimg_left").attr("src",data.headImage);
        }
    }),
    $.ajax({
        type: "POST",
        dataType: "json",
        data: {},
        contentType: "application/json",
        url: "/mail/newmail",
        success:function (data) {
            var count = data.data;


            if(count !=0) {
                $("#newMailCount").html(count);
                $("#unreadcount").html(count);
                $("#newMailMessages").html("you have " + count + " message");
            }else{
                $("#newMailMessages").html("no message");
            }
        }
    })


});