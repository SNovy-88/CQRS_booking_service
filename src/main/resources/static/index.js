const formNode = document.getElementById("bookRoomForm");
formNode.addEventListener("submit", function (event) {
  event.preventDefault();

  const formData = new FormData(this);
  const roomID = formData.get("roomID");
  const customerID = formData.get("customerID");
  const checkInDate = formData.get("checkInDate");
  const checkOutDate = formData.get("checkOutDate");

  const bookingRequest = { roomID, customerID, checkInDate, checkOutDate };

  console.log("Booking request:", bookingRequest);

  fetch("/api/book", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(bookingRequest),
  })
    .then((response) => {
      if (!response.ok) {
        throw new Error("Failed to book room. Status: " + response.status);
      }
      return response.json();
    })
    .then((data) => {
      console.log("Room booked successfully. Response:", data.message);
    });

  formNode.reset();
});

const eventsList = document.getElementById("eventsList");
const generateEventBtn = document.getElementById("generateEventBtn");
const clearEventsBtn = document.getElementById("clearEventsBtn");
const generateMasterDataBtn = document.getElementById("generateMasterDataBtn");

// Function to fetch all events and display them in the UI
function fetchEvents() {
  fetch("/events")
    .then((response) => response.json())
    .then((data) => {
      eventsList.innerHTML = "";
      data.forEach((event) => {
        const listItem = document.createElement("li");
        listItem.textContent = event.content;
        eventsList.appendChild(listItem);
      });
    })
    .catch((error) => console.error("Error fetching events:", error));
}

// Function to generate a new event
function generateEvent() {
  fetch("/event", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({
      // You can customize the event data here as needed
      content: "New event generated at " + new Date().toLocaleString(),
    }),
  })
    .then((response) => {
      if (response.ok) {
        fetchEvents();
      } else {
        throw new Error("Failed to generate event");
      }
    })
    .catch((error) => console.error("Error generating event:", error));
}

// Function to clear all events
function clearEvents() {
  fetch("/event", {
    method: "DELETE",
  })
    .then((response) => {
      if (response.ok) {
        eventsList.innerHTML = ""; // Clear events list after deleting all events
      } else {
        throw new Error("Failed to clear events");
      }
    })
    .catch((error) => console.error("Error clearing events:", error));
}

function generateMasterData() {
  fetch("/api/generateMasterData", {
    method: "POST",
  })
    .then((response) => {
      if (response.ok) {
        console.log("Master data generated successfully");
      } else {
        throw new Error("Failed to generate master data");
      }
    })
    .catch((error) => console.error("Error generating master data:", error));
}

generateEventBtn.addEventListener("click", generateEvent);
clearEventsBtn.addEventListener("click", clearEvents);
generateMasterDataBtn.addEventListener("click", generateMasterData);
