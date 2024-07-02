// Menu toggle handling

var MenuItems = document.getElementById("MenuItems");

MenuItems.style.maxHeight = "0px";

function menutoggle(){
    if(MenuItems.style.maxHeight == "0px"){
        MenuItems.style.maxHeight = "200px";
    }else{
        MenuItems.style.maxHeight = "0px";
    }
}

// Cart handling

function fetchAndRenderCartItems() {
    fetch('/api/v1/cart/getCartItems')
        .then(response => {
            if (!response.ok) {
                throw new Error('Failed to fetch cart items');
            }
            return response.json();
        })
        .then(data => {
            console.log('Data fetched:', data);
            renderCartItems(data);
        })
        .catch(error => {
            console.error('Error:', error);
            alert('Error fetching cart items.');
        });
}

function removeCartItem(product_id){
    fetch('/api/v1/cart/removeCartItem', {
        method: "POST",
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            prod_id: product_id
        })
    })
        .then(response => {
            if (response.ok) {
                fetchAndRenderCartItems();
            }
            throw new Error('Network response was not ok.');
        })
}

function updateCartItemQuantity(product_id, quantity){
    if (quantity === 0){
        removeCartItem(product_id);
    }else{
        fetch('/api/v1/cart/updateItemQuantity', {
            method: "PUT",
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                prod_id: product_id,
                quantity: quantity
            })
        })
            .then(response => {
                if (response.ok) {
                    console.log('Quantity modified');
                    fetchAndRenderCartItems();
                }
                throw new Error('Network response was not ok.');
            })
    }
}

function renderCartItems(cartItems) {
    const cartPage = document.querySelector('.cart-page');
    const orderButton = document.querySelector('.btn');
    if (!cartPage) {
        console.error('Cart page element not found');
        return;
    }

    cartPage.innerHTML = '';

    if (cartItems.length === 0) {
        cartPage.innerHTML = '<h3>The cart is empty.</h3>';
        orderButton.style.display = 'none';
        return;
    }else{
        orderButton.style.display = 'block';
    }

    const table = document.createElement('table');
    cartPage.appendChild(table);

    table.innerHTML = `
    <tr>
        <th>Product</th>
        <th>Quantity</th>
        <th>Subtotal</th>
    </tr>
    `;

    let subtotal = 0;

    cartItems.forEach(item => {
        const product = item.product;
        const quantity = item.quantity;
        const productTotal = product.price * quantity;
        subtotal += productTotal;

        const productRow = document.createElement('tr');
        productRow.innerHTML = `
        <td>
            <div class="cart-info">
                <img src="${product.image1}">
                <div>
                    <a href="/product-details/${product.id}"><p>${product.title}</p></a>
                    <small>Price: ${product.currency} ${product.price}</small>
                    <br>
                    <a href="javascript:void(0)" onclick="removeCartItem(${product.id})">Remove</a>
                </div>
            </div>
        </td>
        <td><input type="number" value="${quantity}" oninput="updateCartItemQuantity(${product.id}, this.value)"></td>
        <td>${product.currency} ${productTotal.toFixed(2)}</td>
        `;
        table.appendChild(productRow);
    });

    const totalDiv = document.createElement('div');
    totalDiv.className = 'total-price';
    totalDiv.innerHTML = `
    <table>
        <tr>
            <td>Subtotal</td>
            <td>${cartItems[0].product.currency} ${subtotal.toFixed(2)}</td>
        </tr>
        <tr>
            <td>Tax</td>
            <td>${cartItems[0].product.currency} ${(subtotal * 0.2).toFixed(2)}</td>
        </tr>
        <tr>
            <td>Total</td>
            <td>${cartItems[0].product.currency} ${(subtotal * 1.2).toFixed(2)}</td>
        </tr>
    </table>
    `;
    cartPage.appendChild(totalDiv);
    cartPage.appendChild(orderButton);
}

document.addEventListener('DOMContentLoaded', fetchAndRenderCartItems);

// Order handling

function createOrder(){
    fetch('/api/v1/order/createOrder', {
        method: 'POST'
    })
        .then(response =>{
            if(response.ok){
                window.alert('Order successfully created.');
                fetchAndRenderCartItems();
            }
        })
}