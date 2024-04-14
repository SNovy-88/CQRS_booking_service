document.addEventListener("DOMContentLoaded", function () {
  //Get all free Rooms for a set period in time
  document
    .getElementById("findRoomsBtn")
    .addEventListener("click", async function (event) {
      const formData = new FormData(document.getElementById("findRoomsForm"));
      const checkInDate = formData.get("checkInDate");
      const checkOutDate = formData.get("checkOutDate");
      const capacity = parseInt(formData.get("numberOfPersons"));

      try {
        const response = await fetch("http://localhost:8082/freeRooms", {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify({
            checkInDate,
            checkOutDate,
            capacity,
          }),
        });

        const availableRooms = await response.json();
        displayFreeRooms(availableRooms);
      } catch (error) {
        console.error("Error fetching free rooms:", error);
        alert("Failed to find available rooms. Please try again later.");
      }
    });

  // Add a booking
  document.getElementById("bookRoomBtn").addEventListener("click", function () {
    const formData = new FormData(document.getElementById("bookRoomForm"));
    const customerID = formData.get("customerID");
    const roomID = formData.get("roomID");
    const checkInDate = formData.get("checkInDate");
    const checkOutDate = formData.get("checkOutDate");

    console.log("Customer ID: " + customerID + " Room Id: " + roomID);

    const response = fetch("http://localhost:8081/book", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        customerID,
        roomID,
        checkInDate,
        checkOutDate,
      }),
    })
      .then((response) => response.text())
      .then((message) => {
        alert(message);
      })
      .catch((error) => {
        console.error("Error:", error);
        alert("Failed to book the room.");
      });
  });

  // Generate MasterData
  document
    .getElementById("generateMasterDataBtn")
    .addEventListener("click", function () {
      // Make a POST request to trigger master data creation
      fetch("http://localhost:8081/masterdata", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
      })
        .then((response) => response.text())
        .then((message) => {
          alert(message); // Show success message
        })
        .catch((error) => {
          console.error("Error:", error);
          alert("Failed to generate master data."); // Show error message
        });
    });

  // Delete the complete query model
  document
    .getElementById("deleteQueryModelBtn")
    .addEventListener("click", function () {
      fetch("http://localhost:8082/queryModelsDeletedEvent", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
      })
        .then((response) => response.json())
        .then((message) => {
          alert(message); // Show success message
        })
        .catch((error) => {
          console.error("Error:", error);
          alert("Failed to delete the query model."); // Show error message
        });
    });

  // Restore complete query model from Eventstore
  document
    .getElementById("restoreQueryModelBtn")
    .addEventListener("click", function () {
      // Make a POST request to restore the query model
      fetch("http://localhost:8080/restoreQueryModelsEvent", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
      })
        .then((response) => response.text())
        .then((message) => {
          alert(message); // Show success message
        })
        .catch((error) => {
          console.error("Error:", error);
          alert("Failed to restore the query model."); // Show error message
        });
    });

  // Get all customers
  document
    .getElementById("getAllCustomersBtn")
    .addEventListener("click", async function () {
      try {
        const response = await fetch("http://localhost:8082/customers");
        const customers = await response.json();
        displayCustomers(customers);
      } catch (error) {
        console.error("Error fetching customers:", error);
        alert("Failed to fetch customers. Please try again later.");
      }
    });

  // Get customers by name
  document
    .getElementById("getCustomersByNameBtn")
    .addEventListener("click", async function () {
      const name = document.getElementById("customerName").value;
      try {
        const response = await fetch("http://localhost:8082/customerByName", {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify({
            name: name,
          }),
        });
        const customers = await response.json();
        displayCustomers(customers);
      } catch (error) {
        console.error("Error fetching customers by name:", error);
        alert("Failed to fetch customers by name. Please try again later.");
      }
    });

  // Get bookings by date
  document
    .getElementById("getBookingsByDateBtn")
    .addEventListener("click", async function () {
      const fromDate = document.getElementById("bookingFromDate").value;
      const toDate = document.getElementById("bookingToDate").value;
      try {
        const response = await fetch("http://localhost:8082/bookingsByDate", {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify({
            fromDate: fromDate,
            toDate: toDate,
          }),
        });
        const bookings = await response.json();
        displayBookings(bookings);
      } catch (error) {
        console.error("Error fetching bookings by date:", error);
        alert("Failed to fetch bookings by date. Please try again later.");
      }
    });

  // Get all bookings
  document
    .getElementById("getAllBookingsBtn")
    .addEventListener("click", async function () {
      try {
        const response = await fetch("http://localhost:8082/bookings");
        const bookings = await response.json();
        displayBookings(bookings);
      } catch (error) {
        console.error("Error fetching all bookings:", error);
        alert("Failed to fetch all bookings. Please try again later.");
      }
    });

  function displayFreeRooms(rooms) {
    let roomsList = document.getElementById("availableRoomsList");
    if (!roomsList) {
      roomsList = document.createElement("ul");
      roomsList.id = "availableRoomsList";
      roomsList.innerHTML = "<h2>Available Rooms</h2>";
      document.body.appendChild(roomsList);
    } else {
      roomsList.innerHTML = "<h2>Available Rooms</h2>";
    }
    rooms.forEach((room) => {
      const listItem = document.createElement("li");
      listItem.textContent = `Room Number: ${room.roomNumber}, Beds: ${room.capacity}`;
      roomsList.appendChild(listItem);
    });
  }

  // Function to display bookings
  function displayBookings(bookings) {
    let bookingsList = document.getElementById("bookingsList");
    if (!bookingsList) {
      bookingsList = document.createElement("ul");
      bookingsList.id = "bookingsList";
      bookingsList.innerHTML = "<h2>Bookings</h2>";
      document.body.appendChild(bookingsList);
    } else {
      bookingsList.innerHTML = "<h2>Bookings</h2>";
    }
    bookings.forEach((booking) => {
      const listItem = document.createElement("li");
      listItem.textContent = `Booking ID: ${booking.bookingID}, Customer ID: ${booking.customerID}, Room Number: ${booking.roomNumber}, Check-In Date: ${booking.checkInDate}, Check-Out Date: ${booking.checkOutDate}`;
      bookingsList.appendChild(listItem);
    });
  }

  // Function to display customers
  function displayCustomers(customers) {
    let customersList = document.getElementById("customersList");
    if (!customersList) {
      customersList = document.createElement("ul");
      customersList.id = "customersList";
      customersList.innerHTML = "<h2>Customers</h2>";
      document.body.appendChild(customersList);
    } else {
      customersList.innerHTML = "<h2>Customers</h2>";
    }
    customers.forEach((customer) => {
      const listItem = document.createElement("li");
      listItem.textContent = `Customer ID: ${customer.customerID}, Name: ${customer.name}, Address: ${customer.address}, Birthdate: ${customer.birthdate}`;
      customersList.appendChild(listItem);
    });
  }
});
