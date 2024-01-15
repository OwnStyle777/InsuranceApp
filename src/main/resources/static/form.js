(() => {
  'use strict'

  const forms = document.querySelectorAll('.needs-validation')

  Array.from(forms).forEach(form => {
    form.addEventListener('submit', event => {
      if (!form.checkValidity()) {
        event.preventDefault()
        event.stopPropagation()
      }

      form.classList.add('was-validated')
    }, false)
  })

  const togglePassword = document.getElementById('eye');
  const passwordField = document.getElementById('password');

  togglePassword.addEventListener('click', function() {
    const type = passwordField.getAttribute('type') === 'password' ? 'text' : 'password';
    passwordField.setAttribute('type', type);
    this.textContent = type === 'password' ? ' Show' : ' Hide';
  });

  const continueField = document.getElementById('continue');

  // Event listener for button size change on mouse hover
  continueField.addEventListener('mouseenter', () => {
    continueField.style.transform = 'scale(1.1)';
    continueField.style.transition = 'transform 0.3s ease';
  });

  continueField.addEventListener('mouseleave', () => {
    continueField.style.transform = 'scale(1)';
  });


})();


 function sendData() {
    const form = document.getElementById('form');
    const insuranceSelect = form.querySelector('#insurance');
    const selectedInsurance = insuranceSelect.options[insuranceSelect.selectedIndex].value;

    const data = {
       "firstName": form.querySelector('#firstName').value,
       "lastName": form.querySelector('#lastName').value,
       "email": form.querySelector('#email').value,
       "password": form.querySelector('#password').value,
       "birthDate": form.querySelector('#birthDate').value, // ISO 8601 format
       "birthNumber": form.querySelector('#birthNumber').value,
       "phoneNumber": form.querySelector('#phoneNumber').value,
       "insuranceCompany": selectedInsurance
    };
    console.log('Před pokusem o přístup k error');
    console.log('Hodnota něco:', data); // Přidejte výpis hodnoty něco

  fetch('/Insurance/register', {
      method: 'POST',
      headers: {
          'Content-Type': 'application/json',
          'Accept': 'application/json',
          'Origin': 'http://localhost:8080'
      },
      body: JSON.stringify(data)
  })
  .then(response => {
      console.log('Response status code:', response.status);

      if (!response.ok) {
          throw new Error(`Registration failed with status: ${response.status}`);
      }
      console.log('Registration successful!');
      window.location.href = "/Insurance/login";
      console.log('Přístup k error:', data.error || 'Vlastnost "error" neexistuje nebo je hodnota null/undefined.');
      console.log('Po pokusu o přístup k error');
  })
  .catch(error => {
      console.error('Error during registration:', error.message);
  });
 }