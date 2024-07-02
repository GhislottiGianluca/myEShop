// Toggle menu

var MenuItems = document.getElementById("MenuItems");

MenuItems.style.maxHeight = "0px";

function menutoggle(){
    if(MenuItems.style.maxHeight == "0px"){
        MenuItems.style.maxHeight = "200px";
    }else{
        MenuItems.style.maxHeight = "0px";
    }
}


// Handling product details information

function getProductIDFromURL() {
    const path = window.location.pathname;
    const parts = path.split('/');
    return parts[parts.length - 1];
}


// Page dynamically html generation

document.addEventListener('DOMContentLoaded', function() {
    fetchProductDetails();
});

function fetchProductDetails() {
    const productId = getProductIDFromURL();
    fetch(`/api/v1/product/getById/${productId}`)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(productDTO => {
            updateProductDetails(productDTO);
        })
        .catch(error => {
            console.error('Error fetching product details:', error);
        });
}

function updateProductDetails(product) {
    const placeholder = document.getElementById('productDetailsPlaceholder');

    const productDiv = document.createElement('div');
    productDiv.className = 'single-product';
    productDiv.innerHTML = `
    <div class="row">
        <div class="col-2ProductDetails">
            <img src="${product.image1}" id="ProductImg" width="100%">
            <div class="small-img-row">
                <div class="small-img-col">
                    <img src="${product.image1}" class="small-img" width="100%">
                </div>
                <div class="small-img-col">
                    <img src="${product.image2}" class="small-img" width="100%">
                </div>
                <div class="small-img-col">
                    <img src="${product.image3}" class="small-img" width="100%">
                </div>
                <div class="small-img-col">
                    <img src="${product.image4}" class="small-img" width="100%">
                </div>
            </div>
        </div>
        <div class="col-2ProductDetails">
            <h1>${product.title}</h1>
            <h4>${product.currency} ${product.price}</h4>
            <input type="number" value="1" class="quantity">
            <a href="" class="btnProductDetails" onclick = "addItemToCart(${product.id})">Add To Cart</a>
            <h3>Product Details <i class="fas fa-indent"></i></h3>
            <br>
            <p>${product.description}</p>
        </div>
    </div>`;

    placeholder.innerHTML = '';
    placeholder.appendChild(productDiv);
    setupImageGallery();
}

function addItemToCart(prod_id){

    var quantityInput = document.querySelector('.quantity');
    var quantity = parseInt(quantityInput.value, 10);

    fetch("/api/v1/cart/addProduct", {
        method: "POST",
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            prod_id: prod_id,
            quantity: quantity
        })
    })
        .then(response => {
            if (response.ok) {
                console.log('Product added:', data);
                window.alert("Item added to the Cart correctly!");
            }
            throw new Error('Network response was not ok.');
        })
}

function setupImageGallery() {
    var ProductImg = document.getElementById('ProductImg');
    var SmallImg = document.getElementsByClassName('small-img');
    console.log(SmallImg.length);

    Array.from(SmallImg).forEach(img => {
        if (img.src.includes('ozh335x54aov3b2vlpej9')) {
            img.style.cursor = 'not-allowed';
            img.onclick = function(event) {
                event.preventDefault();
            };
        } else {
            img.onclick = function() {
                ProductImg.src = img.src;
            };
        }
    });
}
