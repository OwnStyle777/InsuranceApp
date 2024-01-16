(() => {
  'use strict';

  function setupPasswordToggle() {
    const togglePassword = document.getElementById('eye');
    const passwordField = document.getElementById('password');

    if (togglePassword && passwordField) {
      togglePassword.addEventListener('click', function () {
        const type = passwordField.getAttribute('type') === 'password' ? 'text' : 'password';
        passwordField.setAttribute('type', type);
        this.textContent = type === 'password' ? ' Show' : ' Hide';
      });
    } else {
      console.error('Elementy neboli nájdené.');
    }
  }

  function setupContinueButton() {
    const continueField = document.getElementById('continue');

    // Event listener for button size change on mouse hover
    continueField.addEventListener('mouseenter', () => {
      continueField.style.transform = 'scale(1.1)';
      continueField.style.transition = 'transform 0.3s ease';
    });

    continueField.addEventListener('mouseleave', () => {
      continueField.style.transform = 'scale(1)';
    });
  }

  function setupForms() {
    const forms = document.querySelectorAll('.needs-validation');

    Array.from(forms).forEach(form => {
      form.addEventListener('submit', event => {
        if (!form.checkValidity()) {
          event.preventDefault();
          event.stopPropagation();
        }

        form.classList.add('was-validated');
      }, false);
    });
  }

  document.addEventListener('DOMContentLoaded', function () {
    setupPasswordToggle();
    setupContinueButton();
    setupForms();
  });

})();


function sendData(form) {

     // Clear the registration cookie
        document.cookie = "registration=";

const formData = new FormData(form);
    // Send the FormData object to the Spring POST method
    fetch("/Insurance/register", {
        method: "POST",
        body: formData
    })
        .then(response => {
            if (response.status === 200) {
                // Registration was successful
                alert("Registrácia bola úspešná!");
                 window.location.href = "/Insurance/login";
            } else {
                // Registration failed
                alert("Registrácia bola neúspešná!");
            }
        })
        .catch(error => {
            // An error occurred
            alert(error);
        });
}
