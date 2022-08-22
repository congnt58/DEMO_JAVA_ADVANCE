var username = "";
var password = "";

$(document).ready(function () {
  username = getValue("U_NAME");
  password = getValue("M_PASS");

  getAllData();
});

function getAllData() {
  var url_link = "http://localhost:8080/v1/api/accounts";

  if (sortType != null) {
    url_link =
      "http://localhost:8080/v1/api/accounts?sort=username," + sortType;
  }

  $.ajax({
    url: url_link,
    type: "GET",
    headers: {
      Authorization: `Basic ${btoa(username + ":" + password)}`,
    },
    dataType: "json",
    success: function (listData, status, xhr) {
      setTimeout(createTable(listData.content), 1000);
    },
  });
}

function createTable(listData) {
  var textHtml = "";
  listData.forEach((item) => {
    textHtml += `<tr>
    <td>${item.id}</td>
    <td>${item.username}</td>
    <td>${item.departmentName}</td>
    <td>
        <span class="material-icons edit" onclick="clickEdit(${item.id})">
                edit
              </span>
        <span class="material-icons delete" onclick="clickEdit(${item.id})">
        delete_forever
        </span>
    </td>
  </tr>`;
  });

  $("tbody").empty();
  $("tbody").html(textHtml);
}

var sortType = null;

function sortName() {
  if (sortType == null || sortType == "desc") {
    sortType = "asc";
  } else {
    sortType = "desc";
  }

  getAllData();
}
