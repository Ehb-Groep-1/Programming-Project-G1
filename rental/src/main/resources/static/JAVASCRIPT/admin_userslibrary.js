const unbannedUsersEl = document.getElementById('unbannedUsers');

const fetchUnbannedUsers = async () => {
    const response = await fetch('/api/unbannedusers');
    return await response.json();
}
const banUser = (user, holder) => {
    fetch('/api/ban', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(user)
    }).then(() => holder.parentElement.removeChild(holder));
}

const displayUnbannedUsers = (unbannedUsers) => {
    unbannedUsersEl.innerHTML = '';
    for(const unbannedUser of unbannedUsers){
        const holder = document.createElement('div')
        holder.className = 'dynamic-box'
        const userEl = document.createElement('span');
        userEl.appendChild(document.createTextNode(unbannedUser.username))
        const banButtonEl = document.createElement('button');
        banButtonEl.innerText = 'ban';
        banButtonEl.addEventListener('click',() => banUser(unbannedUser, holder),true)

        holder.appendChild(userEl);
        holder.appendChild(banButtonEl);

        unbannedUsersEl.appendChild(holder);

    }
}

const fetchAndDisplay = async () => {
    const unbannedUsers = await fetchUnbannedUsers();
    displayUnbannedUsers(unbannedUsers);
}

setInterval(fetchAndDisplay, 30000);
fetchAndDisplay();

