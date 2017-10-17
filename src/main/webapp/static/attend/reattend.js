function check_form() {
    var comments = $("#comments").val();
    if (comments == "") {
        alert("备注不能为空！");
        return false;
    }

    return true;
}