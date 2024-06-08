(() => {
document.addEventListener('DOMContentLoaded', () => {
    const container = document.getElementById('itemsContainer');
    console.log(container);
    const fetchItems = async () => {
        const response = await fetch('http://localhost:8080/api/iteminfo')
        .then((data) => {
            return data.json();
        }).catch((err) => console.error(err));
        return response;
    }

    const articleTemplate = (item) =>  {
        return (`<article class="item-equipment">
                <div class="item-equipment-content">
                    <div class="item-equipment-image">
                        <img src="../../PNG-JPG/Item-Equipment.png" alt=${item.name}>
                    </div>
                    <div class="item-equipment-name">${item.name}</div>
                </div>
            </article>`);
    }

    const renderArticles = async () => {
        const items = await fetchItems();
        items.map((item) => {
            console.log(item);
            container.innerHTML += articleTemplate(item);
        })
    }
    renderArticles();
})
})();