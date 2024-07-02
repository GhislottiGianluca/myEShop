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

// Page dynamically html generation

function scrollToCategories() {
    var categoriesSection = document.getElementById("categories-section");
    categoriesSection.scrollIntoView({ behavior: 'smooth' });
}

function setUpBestSellers(){
    fetch('/api/v1/product/getBestSellers')
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(products => {
            generateBestSellersHtml(products);
        })
        .catch(error => {
            console.error('Error fetching product details:', error);
        });
}

function generateBestSellersHtml(products) {
    const placeholder = document.getElementById('BestSellersPlaceHolder');
    let htmlContent = '<div class="row">';

    products.forEach(product => {
        htmlContent += `
            <div class="col-4">
                <a href="/product-details/${product.id}"><img src="${product.image1}"></a>
                <a href="/product-details/${product.id}"><h4>${product.title}</h4></a>
                <div class="rating">
                <i class="fas fa-star" aria-hidden="true"></i>
                <i class="fas fa-star" aria-hidden="true"></i>
                <i class="fas fa-star" aria-hidden="true"></i>
                <i class="fas fa-star" aria-hidden="true"></i>
                <i class="fas fa-star-half-alt" aria-hidden="true"></i>
            </div>
                <p>${product.currency} ${product.price}</p>
            </div>
        `;
    });

    htmlContent += '</div>';
    placeholder.innerHTML = htmlContent;
}

function generateStarRating(rating) {
    let starHtml = '';
    for (let i = 0; i < 5; i++) {
        if (i < Math.floor(rating)) {
            starHtml += '<i class="fas fa-star" aria-hidden="true"></i>';
        } else if (i < rating) {
            starHtml += '<i class="fas fa-star-half-alt" aria-hidden="true"></i>';
        } else {
            starHtml += '<i class="far fa-star" aria-hidden="true"></i>';
        }
    }
    return starHtml;
}

document.addEventListener('DOMContentLoaded', setUpBestSellers);