function clickLogin() {
  var uName = $("#username").val();
  var pass = $("#password").val();

  if (uName == null || pass == null || uName == "" || pass == "") {
    alert("Ten dang nhap, password ko duowc de trong!!!");
    return;
  }

  //goi API Login

  //Neu Login Success luu vao storage
  saveValue("U_NAME", uName);
  saveValue("M_PASS", pass);

  //chuyen sang man hinh tiep theo
  location.href = "../html/test_crud.html";
}
