const logout = () => fetch('/api/logout', {method: "POST"}).then(() => {
    console.log('logged out');
    document.location.href='login.html';
});

const getUserInfo = async () => {
    const response = await fetch('/api/userinfo');
    return await response.json();
}

(function (){
    getUserInfo().then( info => {

        // adds
        // <div id="headerContainer">Welcome username <a src="link to profile">profile</a>! | admin options | <a onclick="logout()">logout</a></div>
        // just after <body>
        const holder = document.createElement('div');
        holder.id = 'headerContainer';


        const profileLinkEl = document.createElement('a');
        const welcomeEl = document.createTextNode(`Welcome ${info.username}! `);
       if(document.location.href.endsWith("/User/profileUser.html")){
           profileLinkEl.href = "/User/userInterface.html";
           profileLinkEl.innerText = `interface`;
       } else {
           profileLinkEl.href = "/User/profileUser.html";
           profileLinkEl.innerText = `profile`;

       }

        const separatorEl = document.createTextNode(' | ');

        const logoutLinkEl = document.createElement('a');
        logoutLinkEl.href = '#';
        logoutLinkEl.innerText = 'log out'
        logoutLinkEl.addEventListener('click', logout, true);

        const adminOptionsEl = document.createElement('a');
        adminOptionsEl.href = '/Admin/adminInterface.html';
        adminOptionsEl.innerText = 'admin options'

        holder.appendChild(welcomeEl);
        holder.appendChild(profileLinkEl);
        holder.appendChild(separatorEl);
        if(info.role === 'admin'){
            holder.appendChild(adminOptionsEl);
            holder.appendChild(separatorEl.cloneNode(true));
        }
        holder.appendChild(logoutLinkEl);

        const divs = document.getElementsByTagName('div');
        for(const div of divs){
            if(div.className.split(' ')
                .map(s => s.trim().toLowerCase())
                .filter(c => c.endsWith('container'))
                .length > 0){
                div.insertBefore(holder, div.children[0]);
                return;
            }
        }
    });

})();
