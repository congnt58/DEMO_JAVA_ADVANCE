var elementTrParents;
var username = "";
var password = "";

$(document).ready(function () {
  username = getValue("U_NAME");
  password = getValue("M_PASS");

  $(".content-body").load("crud.html");

  getData();

  // $("#btn-login").click(function(){
  //     $(".content-body").load("crud.html");
  // })

  $('[data-toggle="tooltip"]').tooltip();

  // Append table with add row form on add new button click
  $(document).on("click", ".add-new", function () {
    console.log("click ADD new");
    $(this).attr("disabled", "disabled");
    var index = $("table tbody tr:last-child").index();
    var row =
      "<tr>" +
      `<td style = "display:none" id="td-id"><input type="text" class="form-control" name="name" value=-1> </td>` +
      '<td><input type="text" class="form-control" name="name" id="name"></td>' +
      '<td><input type="text" class="form-control" name="department" id="department"></td>' +
      '<td><input type="text" class="form-control" name="phone" id="phone"></td>' +
      `<td>
            <a class="add" title="Add" data-toggle="tooltip">
              <i class="material-icons">&#xE03B;</i>
            </a>
            <a class="edit" title="Edit" data-toggle="tooltip">
              <i class="material-icons">&#xE254;</i>
            </a>
            <a class="delete" title="Delete" data-toggle="tooltip">
              <i class="material-icons">&#xE872;</i></a>
        </td>` +
      "</tr>";
    $("table").append(row);
    $("table tbody tr")
      .eq(index + 1)
      .find(".add, .edit")
      .toggle();
    $('[data-toggle="tooltip"]').tooltip();
  });

  // Add row on add button click
  $(document).on("click", ".add", function () {
    var empty = false;
    var input = $(this).parents("tr").find('input[type="text"]');
    input.each(function () {
      if (!$(this).val()) {
        $(this).addClass("error");
        empty = true;
      } else {
        $(this).removeClass("error");
      }
    });
    $(this).parents("tr").find(".error").first().focus();

    if (!empty) {
      var id, name, dpName, phone;
      var index = 1;
      elementTrParents = $(this).parents("tr");

      input.each(function () {
        let value = $(this).val();
        $(this).parent("td").html(value);
        switch (index) {
          case 1: {
            id = value;
            break;
          }
          case 2: {
            name = value;
            break;
          }
          case 3: {
            dpName = value;
            break;
          }
          case 4: {
            phone = value;
            break;
          }
        }
        index++;
      });
      console.log("88 -> " + id);
      if (id != -1) {
        updateEmployee(id, name, dpName, phone);
      } else {
        createEmployee(id, name, dpName, phone, testEl);
      }

      $(this).parents("tr").find(".add, .edit").toggle();
      $(".add-new").removeAttr("disabled");
    }
  });

  // Edit row on edit button click
  $(document).on("click", ".edit", function () {
    $(this)
      .parents("tr")
      .find("td:not(:last-child)")
      .each(function () {
        $(this).html(
          '<input type="text" class="form-control" value="' +
            $(this).text() +
            '">'
        );
      });
    $(this).parents("tr").find(".add, .edit").toggle();
    $(".add-new").attr("disabled", "disabled");
  });
  // Delete row on delete button click
  $(document).on("click", ".delete", function () {
    var idDelete = $(this).parents("tr").find("#td-id").text();
    deleteItem(idDelete, $(this).parents("tr"));
  });
});

function deleteItem(idDelete, elementDelete) {
  $.ajax({
    url:
      "https://6220c2faafd560ea699c6ee2.mockapi.io/api/v1/employees/" +
      idDelete,
    type: "Delete",
    dataType: "json",
    success: function (result, status, xhr) {
      elementDelete.remove();
      $(".add-new").removeAttr("disabled");
      alert("Xoa thanh cong");
    },
    error: function (data, xhr, status) {},
  });
}

function updateEmployee(id, name, dpName, phone) {
  console.log(`new data = ${id} , ${name}, ${dpName}, ${phone}`);
  $.ajax({
    url: "https://6220c2faafd560ea699c6ee2.mockapi.io/api/v1/employees/" + id,
    type: "PUT",
    contentType: "application/json",
    data: `{"name":"${name}","departmentName":"${dpName}","phone":"${phone}"}`,
    success: function (listData, status, xhr) {
      console.log("Update thanh cong");
    },
  });
}

function createEmployee(id, name_, dpName_, phone_, elementTdID) {
  var objectData = {
    name: name_,
    departmentName: dpName_,
    phone: phone_,
  };

  $.ajax({
    url: "https://6220c2faafd560ea699c6ee2.mockapi.io/api/v1/employees",
    type: "POST",
    contentType: "application/json",
    // data: `{"name":"${name_}","departmentName":"${dpName}","phone":"${phone_}"}`,
    data: JSON.stringify(objectData),
    success: function (data, status, xhr) {
      console.log("Cteate thanh cong ->" + elementTdID);

      console.log(JSON.stringify(data));
      console.log("id = " + data.id);
      if (status == "success") {
        alert("Tạo mới thành công");
        elementTdID.find("#td-id").html(data.id);
      }
    },
    error: function (data, status, xhr) {},
  });
}

function getData() {
  $.ajax({
    url: "http://localhost:8080/v1/api/accounts",
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
  console.log(JSON.stringify(listData));
  var tBodyData = "";

  listData.forEach((element) => {
    tBodyData += "<tr>";
    tBodyData += `<td id="td-id" style = "display:none">${element.id}</td>`;
    tBodyData += `<td>${element.username}</td>`;
    tBodyData += `<td>${element.departmentName}</td>`;
    tBodyData += `<td>${element.id}</td>`;
    tBodyData += `<td>
      <a class="add" title="Add" data-toggle="tooltip">
        <i class="material-icons">&#xE03B;</i>
      </a>
      <a class="edit" title="Edit" data-toggle="tooltip">
        <i class="material-icons">&#xE254;</i>
      </a>
      <a class="delete" title="Delete" data-toggle="tooltip">
        <i class="material-icons">&#xE872;</i></a>
      </td>`;
    tBodyData += "</tr>";
  });

  $("tbody").html(tBodyData);
}

/*
   * <tr>
            <td>John Doe</td>
            <td>Administration</td>
            <td>(171) 555-2222</td>
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
