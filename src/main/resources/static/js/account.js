let document;
document.addEventListener = function (domContentLoaded, param2) {

};
document.getElementById = function (loginForm) {
    return undefined;
};
document.addEventListener('DOMContentLoaded', function() {
    var LoginForm = document.getElementById("LoginForm");
    var RegForm = document.getElementById("RegForm");
    var Indicator = document.getElementById("Indicator");

    window.login = function() {
        RegForm.style.transform = "translateX(300px)";
        LoginForm.style.transform = "translateX(0px)";
        Indicator.style.transform = "translateX(0px)";
    }

    window.register = function() {
        RegForm.style.transform = "translateX(0px)";
        LoginForm.style.transform = "translateX(-300px)";
        Indicator.style.transform = "translateX(100px)";
    }

    window.submitForm = function() {
        var form = document.getElementById('RegForm');
        var formData = {
            firstName: form.firstName.value,
            lastName: form.lastName.value,
            email: form.email.value,
            password: form.password.value
        };

        fetch('/register/user', {
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

    // Seleziona il comportamento iniziale della pagina.
    register();
});
