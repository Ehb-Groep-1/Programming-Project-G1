function login() {
    // Get input values
    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;

    // For demonstration purposes, let's just display an alert message:
    if (username && password) {
        alert("Login successful!");
    } else {
        alert("Please enter valid credentials.");
    }
}