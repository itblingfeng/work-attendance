$(function () {

    $.ajax({
        type: "POST",
        dataType: "json",
        data: {},
        contentType: "application/json",
        url: "${rc.contextPath}/user/userInfo",
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
        url: "${rc.contextPath}/mail/newmail",
        success:function (data) {
            var count = data.data;


            if(count !=0) {
                $("#newMailCount").html(count);
                $("#unreadcount").html(count);
            }else{
            }
        }
    })


});