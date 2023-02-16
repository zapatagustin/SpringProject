$(document).ready(function () {});

async function signIn() {
  let data = {};
  data.name = document.getElementById("InputName").value;
  data.lastname = document.getElementById("InputLastname").value;
  data.email = document.getElementById("InputEmail").value;
  data.password = document.getElementById("InputPassword").value;

  let repeatPassword = document.getElementById("RepeatPassword").value;
  if (repeatPassword != data.password) {
    alert("password is not the same");
    return;
  }

  const request = await fetch("api/users", {
    method: "POST",
    headers: {
      "Accept": "application/json",
      "Content-Type": "application/json",
    },
    body: JSON.stringify(data),
  });

  alert("account successfully created")
  window.location.href = "login.html"
}
