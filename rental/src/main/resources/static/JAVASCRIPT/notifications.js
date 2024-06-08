
const fetchNotifications = async () => {
    const response = await fetch('/api/notifications');
    return await response.json();
}

const refreshNotifications = (holdingElement) => {
    console.log('refreshing notifications');
    fetchNotifications().then((notifications) =>{
        holdingElement.innerHTML = '';
        const listContainerEl = document.createElement('ul');
        holdingElement.appendChild(listContainerEl);

        if(!!notifications){
            for(const notification of notifications){
                const messageEl = document.createTextNode(`${notification.message} - ${notification.dateSent}`);
                const notificationEl = document.createElement('ul')
                notificationEl.appendChild(messageEl);
                listContainerEl.appendChild(notificationEl);
            }
        }
    })
}

const startRefreshNotificationsCycle = () => {
    const notificationHolder = document.getElementById('notificationHolder');
    refreshNotifications(notificationHolder);
    setInterval(() => refreshNotifications(notificationHolder), 15000); // every 15 seconds verify if newer messages have arrived
}

startRefreshNotificationsCycle();
