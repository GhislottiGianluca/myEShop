// Menù toggle

var MenuItems = document.getElementById("MenuItems");

MenuItems.style.maxHeight = "0px";

function menutoggle(){
    if(MenuItems.style.maxHeight == "0px"){
        MenuItems.style.maxHeight = "200px";
    }else{
        MenuItems.style.maxHeight = "0px";
    }
}


// Page dynamically html generation

var category = getProductCategoryFromURL();
document.addEventListener('DOMContentLoaded', function () {generateProductHTML("", category);});

function generateProductHTML(orderType, category){

    var title = "";
    switch(category){
        case "products":
            title = "All Products";
            break;
        case "Cup":
            title = "Cups Category";
            break;
        case "TShirt":
            title = "TShirts Category";
            break;
        case "Sweatshirt":
            title = "Sweatshirts Category";
            break;
    }

    var titleElement = document.querySelector('.titlePlaceholder');
    titleElement.innerHTML = `<h2>${title}</h2>`;

    var api = "";
    if (category === "products"){
        api = `/api/v1/product${orderType}`;
    }else{
        api = `/api/v1/product/${category}${orderType}`;
    }

    fetch(api)
        .then(response => response.json())
        .then(products => {
            const row2 = document.querySelector('.row-2');
            const container = document.createElement('div');
            container.className = 'product-container';
            row2.parentNode.insertBefore(container, row2.nextSibling);

            let groupContainer = document.createElement('div');
            groupContainer.className = 'row';
            container.appendChild(groupContainer);

            products.forEach((product, index) => {
                if (index % 4 === 0 && index !== 0) {
                    groupContainer = document.createElement('div');
                    groupContainer.className = 'row';
                    container.appendChild(groupContainer);
                }

                const productElement = document.createElement('div');
                productElement.className = 'col-4';
                productElement.innerHTML = `
                    <a href="/product-details/${product.id}">
                        <img src="${product.image1}" alt="${product.title}">
                        <h4>${product.title}</h4>
                    </a>
                    <div class="rating">
                        <i class="fas fa-star" aria-hidden="true"></i>
                        <i class="fas fa-star" aria-hidden="true"></i>
                        <i class="fas fa-star" aria-hidden="true"></i>
                        <i class="fas fa-star" aria-hidden="true"></i>
                        <i class="fas fa-star-half-alt" aria-hidden="true"></i>
                    </div>
                    <p>${product.currency} ${product.price.toFixed(2)}</p>
                `;
                groupContainer.appendChild(productElement);
            });

            const remainder = products.length % 4;
            if (remainder > 0 && remainder < 4) {
                while(groupContainer.children.length < 4) {
                    const placeholder = document.createElement('div');
                    placeholder.className = 'col-4';
                    groupContainer.appendChild(placeholder);
                }
            }
        })
        .catch(error => console.error('Errore nel recupero dei prodotti:', error));
}

// Handling the html generation based on the sorting selected by the user

function handleChange() {
    var select = document.querySelector('.product-select');
    var value = select.value;
    removeAllRows()

    var category = getProductCategoryFromURL();

    if(value === "Sort by price"){
        generateProductHTML("/getByPrice", category)
    }else if(value === "Sort by sold"){
        generateProductHTML("/getBySold", category)
    }else{
        generateProductHTML("", category)
    }
}

function removeAllRows() {
    const containers = document.querySelectorAll('.product-container');

    containers.forEach(container => {
        container.parentNode.removeChild(container);
    });
}

// Handling the query based on the category selected by the user in the home page

function getProductCategoryFromURL() {
    const path = window.location.pathname;
    const parts = path.split('/');
    return parts[parts.length - 1];
}