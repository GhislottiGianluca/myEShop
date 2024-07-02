var LoginForm = document.getElementById("LoginForm");
var RegForm = document.getElementById("RegForm");
var Indicator = document.getElementById("Indicator");

function register() {
    RegForm.style.transform = "translateX(0px)";
    LoginForm.style.transform = "translateX(-300px)";
    Indicator.style.transform = "translateX(100px)";
}

function login() {
    RegForm.style.transform = "translateX(300px)";
    LoginForm.style.transform = "translateX(0px)";
    Indicator.style.transform = "translateX(0px)";
}

window.onload = function() {
    register();
}

function submitForm() {
    var form = document.getElementById('RegForm');
    var formData = {
        firstName: form.firstName.value,
        lastName: form.lastName.value,
        email: form.email.value,
        password: form.password.value
    };

    fetch('/api/v1/register/user', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(formData)
    })
        .then(response => response.json())
        .then(data => {
            if (data.redirectUrl) {
                window.location.href = data.redirectUrl;
            } else {
                console.log(data);
            }
        })
        .catch(error => console.error('Error:', error));

    return false;
}
function loginSubmit(){

    document.getElementById("LoginForm").addEventListener("submit", function(event) {
        event.preventDefault();

        var usernameInput = document.getElementById('username');
        var passwordInput = document.getElementById('password');

        var loginData = {
            username: usernameInput.value,
            password: passwordInput.value
        };

        fetch('/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            body: new URLSearchParams(loginData)
        })
            .then(response => {
                if (response.ok && response.redirected) {
                    window.location.href = response.url;
                } else {
                    return response.text();
                }
            })
            .then(text => {
                if (text) {
                    console.error('Login failed:', text);
                    alert('Login failed: ' + text);
                }
            })
            .catch(error => console.error('Error during login:', error));
    });

    return false;
}