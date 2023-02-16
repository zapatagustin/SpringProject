// Call the dataTables jQuery plugin
$(document).ready(function () {
  loadUsers();
  $("#users").DataTable();
  setUserEmail();
});

function setUserEmail() {
  document.getElementById('inputEmailUser').outerHTML = localStorage.email
}

async function loadUsers() {
  const request = await fetch("api/users", {
    method: "GET",
    headers: getHeaders(),
  });
  const users = await request.json();

  let listHtml = "";

  for (let user of users) {
    let deleteUser =
      '<a href="#" onclick="deleteUser(' +
      user.id +
      ')" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a>';
    let phone = user.phone == null ? "-" : user.phone
    let userHtml =
      "<tr><td>" +
      user.id +
      "</td><td>" +
      user.name +
      " " +
      user.lastname +
      "</td><td>" +
      user.email +
      "</td><td>" +
      phone +
      "</td><td>" +
      deleteUser +
      "</td></tr>";
    listHtml += userHtml;
  }

  document.querySelector("#users tbody").outerHTML = listHtml;
}

function getHeaders() {
  return {
    "Accept": "application/json",
    "Content-Type": "application/json",
    "Authorization": localStorage.token
  }
}

async function deleteUser(id) {
  if (!confirm("delete user?")) {
    return;
  }

  const request = await fetch("api/users/" + id, {
    method: "DELETE",
    headers: getHeaders()
  });

  location.reload();
}
