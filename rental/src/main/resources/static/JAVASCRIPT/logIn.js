document.addEventListener('DOMContentLoaded', () => {
const renderDiv = document.querySelector(".render");
let loginForm = document.getElementById("login-form");

const checkState = () => {
    const currentState = window.localStorage.getItem('currentState');
    if(currentState != null) {
        const convertedState = JSON.parse(currentState);
        console.log(convertedState);
        if(convertedState.state == "adminProfile") {
            console.log("You are an admin.");
            window.location.href =  '../Admin/adminInterface.html';
        } else if(convertedState.state == "normalProfile") {
            console.log("You are a normal user.");
            window.location.href =  '../User/userInterface.html';
        }
    } else {
        console.log("You are not logged in.");
    }
}

checkState();

const login = async (e) => {
    e.preventDefault();
    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;
    const response = await fetch("http://localhost:8080/api/login", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            'username': username,
            'password': password
        })
    }).then(data => {
        return data.json();
    }).catch(err => console.error(err))
    console.log(response);
    if(response) {
        console.log("correct");
        if(response.role == "admin") {
            console.log("you are an admin");
            const pageState = {state: 'adminProfile'};
            localStorage.setItem('currentState', JSON.stringify(pageState));
            window.location.href = '../Admin/adminInterface.html'
        } else {
            const pageState = {state: 'normalProfile'};
            localStorage.setItem('currentState', JSON.stringify(pageState));
            window.location.href = '../User/userInterface.html'
            console.log("Normal user.");
        }
    } else {
        console.log("Wrong username or password!");
    }
}
if(loginForm) {
    loginForm.addEventListener('submit', login);
}
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