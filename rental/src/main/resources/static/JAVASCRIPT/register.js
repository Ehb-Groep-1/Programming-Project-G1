document.addEventListener('DOMContentLoaded', () => {
const registerBtn = document.getElementById("registerBtn");

const passwordEl = document.getElementById("password");
const confirmedPasswordEl = document.getElementById("confirm_password");

const register = async (e) => {
    e.preventDefault();

    document.getElementById("usernameAlreadyTaken").style.visibility = 'hidden';

    const username = document.getElementById("username").value;
    const password = passwordEl.value;
    const confirmedPassword = confirmedPasswordEl.value;
    const email = document.getElementById("email").value;
    const phonenumber = document.getElementById("phonenumber").value;
    const address = document.getElementById("address").value;

    console.log('registering user');

    if(password !== confirmedPassword){
        console.log('passwords do not match');
        return false;
    }

    const response = await fetch("http://localhost:8080/api/register", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            'username': username,
            'password': password,
            'email': email,
            'phonenumber': phonenumber,
            'address': address
        })
    }).then(data => {
        return data.json();
    })
        .catch(err => console.error(err))

    console.log(response);
    if(!!response && response.status === 200 || !!!response.status ) {
        window.location.href = '/login.html'
        console.log("user created");
    } else if(!!response){
        console.log(response.detail);
        document.getElementById("usernameAlreadyTaken").style.visibility = 'visible';
    }
    return false;
}

const verifyPasswordMatching = () => {
    console.log('verifying password match');
    const password = passwordEl.value;
    const confirmedPassword = confirmedPasswordEl.value;
    const notMatchingError = document.getElementById("passwordsDoNotMatch");

    notMatchingError.style.visibility = password === confirmedPassword ? 'hidden' : 'visible';
}

passwordEl.addEventListener('blur', verifyPasswordMatching);
confirmedPasswordEl.addEventListener('blur', verifyPasswordMatching);
registerBtn.addEventListener('click', register);
});
