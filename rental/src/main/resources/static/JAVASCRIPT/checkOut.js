document.addEventListener("DOMContentLoaded", () => {
  const section = document.querySelector(".boxes");
  const confirmBtn = document.getElementById("confirm-order");
  const fetch_Items_For_Rent = async () => {
    const response = await fetch("http://localhost:8080/item/rent")
      .then((data) => {
        if (!data.ok) throw new Error("No items selected for rent!");
        return data.json();
      })
      .catch((err) => {
        console.error(err);
        alert(err);
      });
    return response;
  };

  const itemTemplate = (item) => {
    return `
            <div class="item-equipment-image"><img src="../../PNG-JPG/Item-Equipment.png" alt="Item"></div>
            <div class="item-equipment-name">Name: ${item.name}</div>
            <div class="description">Description: ${item.description}</div>`;
  };

  const datesTemplate = () => {
    return `
        <div class="reservation-date">
            <input type="date" id="start-date" value="yyyy-MM-dd" required>
            <input type="date" id="end-date" value="yyyy-MM-dd" required>
        </div>`;
  };

  const renderItems = async () => {
    const items = await fetch_Items_For_Rent();
    console.log(items);
    const itemEquipment = document.createElement("div");
    itemEquipment.className = "item-equipment";
    items.map((item) => {
      itemEquipment.innerHTML += itemTemplate(item);
    });
    itemEquipment.innerHTML += datesTemplate();
    section.appendChild(itemEquipment);
    console.log(items);
    confirmBtn.addEventListener("click", () => reserveItems(items));
  };

  const reserveItems = async (items) => {
    const firstDateInput = document.getElementById("start-date");
    const secondDateInput = document.getElementById("end-date");
    const firstDate = firstDateInput.value;
    const secondDate = secondDateInput.value;
    if (firstDate.length < 10 || secondDate.length < 10) return;
    const values = {
      dates: {
        first: firstDate,
        second: secondDate,
      },
      itemsToRent: items.map((item) => ({
        id: item.id,
        name: item.name,
        description: item.description,
        amountAvailable: item.amountAvailable,
      })),
    };
    const response = await fetch("http://localhost:8080/history/rent", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(values),
    })
      .then((data) => {
        if (!data.ok) throw new Error("Couldn't rent the items!");
        return data;
      })
      .catch((err) => {
        console.error(err);
        alert(err);
      });
    console.log(response);
    postNotifications();
  };

  const postNotifications = async () => {
    const response = await fetch("http://localhost:8080/api/notification", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
    })
      .then((data) => {
        if (!data.ok) throw new Error("Couldn't get notifications!");
      })
      .catch((err) => {
        console.log(err);
        alert(err);
      });
    window.location.href = "/User/notificationsUser.html";
  };

  renderItems();
});
