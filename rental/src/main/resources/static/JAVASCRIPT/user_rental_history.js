const fetchRentalHistory = async () => {
    const response = await fetch('/api/rentalhistory');
    return await response.json();
}

const createStaticBox = (text) => {
    const elem = document.createElement('div');
    elem.className = 'static-box'
    elem.appendChild(document.createTextNode(text))
    return elem;
}
const addStaticBoxes = (elem) => {
    elem.appendChild(createStaticBox('Item/Equipment'));
    elem.appendChild(createStaticBox('Description'));
    elem.appendChild(createStaticBox('Rental Data'));
    return elem;
}

const createDynamicBox = (texts, addImg = false) => {
    const elem = document.createElement('div');
    elem.className = 'dynamic-box'
    if (addImg) {
        const imgEl = document.createElement('img');
        imgEl.src = '/PNG-JPG/Item-Equipment.png';
        elem.appendChild(imgEl);
    }
    for (const text of texts) {
        elem.appendChild(document.createTextNode(text))
        elem.appendChild(document.createElement('br'));
    }
    return elem;
}

const addDynamicBoxes = (elem, itemHistory) => {
    elem.appendChild(createDynamicBox([itemHistory.itemName]));
    elem.appendChild(createDynamicBox([itemHistory.itemDescription], true));
    elem.appendChild(createDynamicBox(['Take out: ' + itemHistory.rentalDate, 'Return In: ' + itemHistory.returnDate, 'Duration: ' + itemHistory.rentalDuration]));
    return elem;
}

const displayRentalHistory = (itemRentalHistoryHolder, totalRentalHistory) => {
    addStaticBoxes(itemRentalHistoryHolder)
    for (const itemRentalHistory of totalRentalHistory) {
        addDynamicBoxes(itemRentalHistoryHolder, itemRentalHistory)
    }
}


const displayHistory = async () => {
    const rentalHistoryHolderEl = document.getElementById("rentalHistoryHolder");
    const rentalCounterEl = document.getElementById("rentalCounter");
    const totalRentalHistory = await fetchRentalHistory();

    rentalHistoryHolderEl.innerHTML = '';
    displayRentalHistory(rentalHistoryHolderEl, totalRentalHistory);
    rentalCounterEl.innerText = totalRentalHistory.length;
}

displayHistory();
setInterval(displayHistory, 30000);