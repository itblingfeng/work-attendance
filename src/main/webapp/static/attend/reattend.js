function check_form(){
   var address = $("#address").val();
   if(address==""){
       alert("地址不能为空！");
       return false;
   }

   return true;
}