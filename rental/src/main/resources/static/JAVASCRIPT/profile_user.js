const response = await fetch('/api/userprofile');
const profile = await response.json();

const usernameEl = document.getElementById('username');
const emailEl = document.getElementById('email');
const phonenumberEl = document.getElementById('phonenumber');
const addressEl = document.getElementById('address');
const passwordEl = document.getElementById('password');
const confirmPasswordEl = document.getElementById('confirmPassword');

usernameEl.innerText = profile.username;
emailEl.value = profile.email;
phonenumberEl.value = profile.phonenumber;
addressEl.value = profile.address;


const updateProfileData = (profile, onSuccess) => {
  fetch('/api/userprofile',{
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(profile) }
  ).then(onSuccess);
};
const signalPasswordsDiffer = () => {
    document.getElementById('passwordDifferent').style.display = 'block';
    document.getElementById('confirmPasswordDifferent').style.display = 'block';
};

const resetPasswordsDiffer = () => {
    document.getElementById('passwordDifferent').style.display = 'none';
    document.getElementById('confirmPasswordDifferent').style.display = 'none';
};

const signalSuccess = () => {
    const dataChangedEl = document.getElementById('dataChanged');
    dataChangedEl.style.display = 'block';
    setTimeout( () => dataChangedEl.style.display = 'none', 3000);
}

const getProfileData = () => {
    return {
        password: passwordEl.value,
        email: emailEl.value,
        address: addressEl.value,
        phonenumber: phonenumberEl.value
    };
};


const updateProfileWhenValidated = ()=> {
    if (passwordEl.value !== confirmPasswordEl.value) {
        signalPasswordsDiffer();
        return false;
    } else {
        resetPasswordsDiffer();
    }

    updateProfileData(getProfileData(), signalSuccess);
    return true;
};

document.getElementById('changeProfileBtn').addEventListener('click', updateProfileWhenValidated, false);
