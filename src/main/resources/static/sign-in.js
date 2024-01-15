
        function sendData(form) {

                  // Clear the login cookie
                     document.cookie = "login=";

             const formData = new FormData(login);
                 // Send the loginData object to the Spring POST method
                 fetch("/Insurance/login", {
                     method: "POST",
                     body: formData
                 })
                     .then(response => {
                         if (response.status === 200) {
                             // Registration was successful
                             alert("Prihlásenie bolo úspešné!");
                              window.location.href = "/Insurance/clientInfo";
                         } else {
                             // Registration failed
                             alert("Prihlásenie bolo neúspešné!");
                         }
                     })
                     .catch(error => {
                         // An error occurred
                         alert(error);
                     });
             }
