(() => {
  'use strict';

function setupPasswordToggle() {
   const togglePassword = document.getElementById('eye');
   const passwordField = document.getElementById('password');

   if (togglePassword && passwordField) {
     togglePassword.addEventListener('click', function () {
       const type = passwordField.getAttribute('type') === 'password' ? 'text' : 'password';
       passwordField.setAttribute('type', type);

       if (type === 'password') {
         this.classList.remove('hide');
       } else {
         this.classList.add('hide');
       }
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
        alert("Registration was successful!");
        window.location.href = "/Insurance/login";
      }else if(response.status === 400){
        alert("This email is already registered!");
      } else if(response.status === 403){
      alert("Please fill in all the necessary information to complete the registration.")}
      else {
        // Registration failed
        alert("Registration failed!");
        document.getElementById('continue').disabled = false;
      }
    })
    .catch(error => {
      alert(error);

      document.getElementById('continue').disabled = false;
    });
}