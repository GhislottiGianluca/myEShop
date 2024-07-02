// Handling user information filling into the page

document.addEventListener('DOMContentLoaded', function() {
    fillFormPlaceholder();
    getOrders();
});

function fillFormPlaceholder() {
    var firstNameInput = document.getElementById('firstname');
    var lastNameInput = document.getElementById('secondname');

    fetch('/api/v1/user')
        .then(response => {
            if (!response.ok) {
                throw new Error('Failed to fetch cart items');
            }
            return response.json();
        })
        .then(data => {
            console.log('App User data received:', data);
            firstNameInput.placeholder = data.firstName;
            lastNameInput.placeholder = data.lastName;
        })
        .catch(error => {
            console.error('Error:', error);
            alert('Error fetching App User Information.');
        });
}

function getOrders(){
    fetch('/api/v1/order/getOrders')
        .then(response => {
            if (!response.ok) {
                throw new Error('Failed to fetch orders.');
            }
            return response.json();
        })
        .then(orders => {
            console.log('Orders fetched:', orders);
            generateOrdersTables(orders);
        })
        .catch(error => {
            console.error('Error:', error);
            alert('Error fetching orders.');
        });
}

function generateOrdersTables(orders) {
    const ordersContainer = document.querySelector('.cart-page');
    orders.forEach(order => {
        const table = document.createElement('table');
        const thead = document.createElement('thead');
        const tbody = document.createElement('tbody');
        table.style.width = '100%'; // Assicura che la tabella utilizzi tutta la larghezza disponibile

        const formattedDate = new Date(order.createdAt).toLocaleDateString('en-EN', {
            day: 'numeric', month: 'long', year: 'numeric'
        });

        const headerRow = `
        <tr>
            <th colspan="5" style="text-align: center;">Order created at: ${formattedDate} - Status: ${order.status}</th>
        </tr>`;
        thead.innerHTML = headerRow;
        table.appendChild(thead);

        order.products.forEach((product, index) => {
            const quantity = order.quantities[index];
            const row = document.createElement('tr');
            row.innerHTML = `
            <td style="width: 15%;"><img src="${product.image1}" alt="${product.title}" style="width:100px;height:auto;"></td>
            <td style="width: 10%; text-align: left;">x${quantity}</td>
            <td style="width: 75%; text-align: left;">${product.title}</td>
            `;
            tbody.appendChild(row);
        });

        table.appendChild(tbody);
        ordersContainer.appendChild(table);
    });
}


// Handling the submission and updating of new information

function submitUpdatingInformation(){
    var firstNameInput = document.getElementById('firstname');
    var lastNameInput = document.getElementById('secondname');

    var updateForm = document.getElementById('updateForm');

    fetch('/api/v1/user', {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            firstName: firstNameInput.value,
            secondName: lastNameInput.value,
        })
    })
        .then(response => {
            if (response.ok) {
                updateForm.reset();
                fillFormPlaceholder()
            }
            throw new Error('Network response was not ok.');
        })
}