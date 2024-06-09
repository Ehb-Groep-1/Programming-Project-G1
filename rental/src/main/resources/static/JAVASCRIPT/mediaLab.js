(() => {
  document.addEventListener("DOMContentLoaded", () => {
    const container = document.getElementById("itemsContainer");
    const popup = document.querySelector(".popup");
    const closePopupBtn = document.getElementById("closePopupBtn");
    console.log(container);
    const fetchItems = async () => {
      const response = await fetch("http://localhost:8080/item/info")
        .then((data) => {
          return data.json();
        })
        .catch((err) => console.error(err));
      return response;
    };

    const articleTemplate = (item) => {
      return `<article class="item-equipment">
                <div class="item-equipment-content">
                    <div class="item-equipment-image">
                        <img src="../../PNG-JPG/Item-Equipment.png" alt=${item.name}>
                    </div>
                    <div class="item-equipment-name">${item.name}</div>
                </div>
            </article>`;
    };

    const renderArticles = async () => {
      const items = await fetchItems();
      items.map((item) => {
        console.log(item);
        const article = document.createElement("div");
        article.innerHTML = articleTemplate(item);
        article.addEventListener("click", () => createPopup(item));
        container.appendChild(article);
      });
    };

    const popupTemplate = (item) => {
      return `<span id="closePopupBtn" class="close-btn">&times;</span>
                <section class="boxes">
                    <figure class="item-equipment">
                        <div class="item-equipment-image"><img src="../../PNG-JPG/Item-Equipment.png" alt="Item"></div>
                    </figure>
                    <div class="item-equipment-name">Name: ${item.name}</div>
                    <div class="description">Description: ${item.description}</div>
                    <div class="quantity-available">Available quantity: ${item.amountAvailable}</div>
                    <button class="rent">Rent</button>
                </section>`;
    };

    const createPopup = (item) => {
      const popupContent = document.querySelector(".popup-content");
      popupContent.innerHTML = popupTemplate(item);
      popup.style.display = "block";
      const closePopupBtn = document.getElementById("closePopupBtn");
      const rentBtn = document.querySelector(".rent");
      closePopupBtn.addEventListener("click", () => {
        popup.style.display = "none";
      });
      rentBtn.addEventListener("click", () => rentItem(item));
    };

    const rentItem = async (item) => {
      try {
        const response = await fetch("http://localhost:8080/item/rent", {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify(item),
        });
        if (!response.ok) throw new Error(`Can't rent this item:\t${item}`);
        window.location.href = "../User/checkOut.html";
      } catch (err) {
        console.error(err);
        alert("An error occured while renting the item.");
      }
    };

    renderArticles();
    window.addEventListener("click", (e) => {
      if (e.target === popup) {
        popup.style.display = "none";
      }
    });
  });
})();
