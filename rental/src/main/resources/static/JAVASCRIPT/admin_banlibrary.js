const bannedUsersEl = document.getElementById('bannedUsers');

const fetchBannedUsers = async () => {
    const response = await fetch('/api/bannedusers');
    return await response.json();
}
const unbanUser = (bannedUser, holder) => {
    fetch('/api/unban', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(bannedUser)
    }).then(() => holder.parentElement.removeChild(holder));
}

const displayBannedUsers = (bannedUsers) => {
    bannedUsersEl.innerHTML = '';
    for(const bannedUser of bannedUsers){
        const holder = document.createElement('div')
        holder.className = 'dynamic-box'
        const userEl = document.createElement('span');
        userEl.appendChild(document.createTextNode(bannedUser.username))
        const bannedUntilEl = document.createElement('span');
        bannedUntilEl.appendChild(document.createTextNode(bannedUser.bannedUntil))
        const unbanButtonEl = document.createElement('button');
        unbanButtonEl.innerText = 'unban';
        unbanButtonEl.addEventListener('click',() => unbanUser(bannedUser, holder),true)

        holder.appendChild(userEl);
        holder.appendChild(bannedUntilEl);
        holder.appendChild(unbanButtonEl);

        bannedUsersEl.appendChild(holder);

    }
}

const fetchAndDisplay = async () => {
    const bannedUsers = await fetchBannedUsers();
    displayBannedUsers(bannedUsers);
}

setInterval(fetchAndDisplay, 30000);
fetchAndDisplay();

