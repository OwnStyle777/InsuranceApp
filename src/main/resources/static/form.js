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

 function setupForms() {
   const forms = document.querySelectorAll('.needs-validation');

   Array.from(forms).forEach(form => {
     let interacted = false; // Premenná na sledovanie interakcie s formulárom

     form.addEventListener('submit', event => {
       if (!interacted || !form.checkValidity()) {
         event.preventDefault();
         event.stopPropagation();

         // Pridá triedu was-validated iba ak užívateľ aspoň raz interagoval s formulárom
         if (interacted) {
           form.classList.add('was-validated');
         }
       }
     }, false);

     // Event listener pre prvé pokusy interakcie s formulárom
     form.addEventListener('input', () => {
       interacted = true; // Užívateľ interagoval s formulárom

       // Pridá triedu was-validated k jednotlivým elementom po interakcii
       if (!form.checkValidity()) {
         form.classList.add('was-validated');
       }
     });
   });
 }
  document.addEventListener('DOMContentLoaded', function () {
    setupPasswordToggle();
    setupForms();
  });

})();

// Tvoja existujúca funkcia sendData
function sendData(form) {
  // Disable the button to prevent multiple submissions
  document.getElementById('continue').disabled = true;

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
        document.getElementById('continue').disabled = false;
      }
    })
    .catch(error => {
      // An error occurred
      alert(error);
      // Re-enable the button in case of error
      document.getElementById('continue').disabled = false;
    });
}