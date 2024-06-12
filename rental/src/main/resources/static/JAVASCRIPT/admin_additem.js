const equipmentAddedNotificationEl = document.getElementById('equipmentAddedNotification');
const addEquipmentBtnEl = document.getElementById('addEquipmentBtn');

const indicateItemAdded = () => {
    equipmentAddedNotificationEl.style.visibility = 'visible';
    setTimeout(() => equipmentAddedNotificationEl.style.visibility = 'hidden', 5000);
}

const eraseFormFields = () => {
    for(const elem of document.getElementsByTagName('input')){
        elem.value = null;
    }
}

const addItem =  () => {
    const quantityValue = document.getElementById('quantity').value;
    const nameValue = document.getElementById('itemName').value;
    const descriptionValue = document.getElementById('description').value;
    fetch('/item/',{method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(
        {
                    name: nameValue,
                    description: descriptionValue,
                    quantity: quantityValue })})
        .then( () => {
            indicateItemAdded();
            eraseFormFields();
            })
        .catch(err => console.log(err));
}

addEquipmentBtnEl.addEventListener('click', addItem, false);
