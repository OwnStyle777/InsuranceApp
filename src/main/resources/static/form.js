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
