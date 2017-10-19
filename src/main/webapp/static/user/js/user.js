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
    });

});