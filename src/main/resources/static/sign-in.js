function sendData (){

 const login = document.getElementById('login');

//  const loginInfo = {
//        "email": login.querySelector('#email').value,
//        "password": login.querySelector('#password').value,
//     };

       fetch('/Insurance/login', {
           method: 'POST',
           headers: {
               'Content-Type': 'application/json',
               'Accept': 'application/json',
               'Origin': 'http://localhost:8080'
           },
           body: JSON.stringify({
                  email: login.querySelector('#email').value,
                  password: login.querySelector('#password').value
              })
       })
       .then(response => {
           console.log('Response status code:', response.status);

           if (!response.ok) {
               throw new Error(`Registration failed with status: ${response.status}`);
           }
           console.log('Registration successful!');
           window.location.href = "/Insurance/clientInfo";

       })
       .catch(error => {
           console.error('Error during registration:', error.message);
       });

}