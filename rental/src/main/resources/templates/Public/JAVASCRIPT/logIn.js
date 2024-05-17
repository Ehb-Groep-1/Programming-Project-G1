document.addEventListener('DOMContentLoaded', () => {
const loginForm = document.getElementById("login-form");
const login = async (e) => {
    e.preventDefault();
    // console.log("logged in");
    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;
    // const response = await fetch("http://localhost:8080/api/hello")
    // .then(result => {
    //     if(!result.ok) {
    //         throw data.status;
    //     }
    //     return result.json();
    // })
    // .catch(err => console.error(err));
    // console.log(response);
    const response = await fetch("http://localhost:8080/api/login", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            'userName': username,
            'password': password
        })
    }).then(data => {
        return data.json();
    }).catch(err => console.error(err))
    console.log(response);
}
loginForm.addEventListener('submit', login);
});

// function login(e) {
//     e.preventDefault();
//     console.log("loged in")
//     // Get input values
//     const username = document.getElementById("username").value;
//     const password = document.getElementById("password").value;

//     // You can add your login logic here (e.g., validate credentials, make API calls, etc.)
//     // For demonstration purposes, let's just display an alert message:
//     if (username && password) {
//         alert("Login successful!");
//     } else {
//         alert("Please enter valid credentials.");
//     }
// }