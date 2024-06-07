const logout = () => fetch('/api/logout', {method: "POST"}).then(() => {
    console.log('logged out');
    document.location.href='login.html';
});