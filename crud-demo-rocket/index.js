/*
<tr>
                <td>1</td>
                <td>VTi</td>
                <td>abcd@gmail.com</td>
                <td>
                  <a class="add" title="Add" data-toggle="tooltip"
                    ><i class="material-icons">&#xE03B;</i></a
                  >
                  <a class="edit" title="Edit" data-toggle="tooltip"
                    ><i class="material-icons">&#xE254;</i></a
                  >
                  <a class="delete" title="Delete" data-toggle="tooltip"
                    ><i class="material-icons">&#xE872;</i></a
                  >
                </td>
              </tr>
*/

var page = 0;
var size = 5;

var listDataAccount = [];

$(document).ready(function () {
  getAllAccount(page, size);
});

//goi API
function getAllAccount(a, b) {
  let url = `http://localhost:8080/v1/api/accounts?page=${page}&size=${size}`;
  var settings = {
    url: url,
    method: "GET",
    timeout: 0,
  };

  $.ajax(settings).done(function (data) {
    listDataAccount = data.content;

    //goi den funtion fillDataToTable
    fillDataToTable(listDataAccount);
  });
}

function fillDataToTable(listData) {
  var textHtml = "";

  listData.forEach((item) => {
    textHtml += `<tr>
        <td>${item.id}</td>
        <td>${item.username}</td>
        <td>${item.email}</td>
        <td>
        <a class="add" title="Add" data-toggle="tooltip"><i class="material-icons">&#xE03B;</i></a>
        <a class="edit" title="Edit" data-toggle="tooltip"><i class="material-icons">&#xE254;</i></a>
        <a class="delete" title="Delete" data-toggle="tooltip" onclick=deleteItem(${item.id})><i class="material-icons">&#xE872;</i></a>
        </td>
    </tr>`;
  });

  $("tbody").html(textHtml);
}

function deleteItem(idItem) {
  let url = `http://localhost:8080/v1/api/accounts/${idItem}`;
  var settings = {
    url: url,
    method: "GET",
    timeout: 0,
  };

  $.ajax(settings).done(function (data) {
    alert("Item muon xoa: " + JSON.stringify(data));
  });
}
