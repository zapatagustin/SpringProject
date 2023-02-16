//on ready
$(document).ready(function () {});

async function loginUser() {
  let data = {};
  data.email = document.getElementById("InputEmail").value;
  data.password = document.getElementById("InputPassword").value;

  const request = await fetch("api/login", {
    method: "POST",
    headers: {
      "Accept": "application/json",
      "Content-Type": "application/json",
    },
    body: JSON.stringify(data),
  });

  const response = await request.json();
  if (response != 'FAIL') {
    localStorage.token = response
    localStorage.email = data.email
    window.location.href = 'users.html'
  } else {
    alert("wrong email/password")
  }
}
