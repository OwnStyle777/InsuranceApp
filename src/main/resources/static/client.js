
(() => {
    'use strict';

   document.addEventListener('DOMContentLoaded', function () {
       var sidebarToggleElement = document.querySelector('.sidebar-toggle-icon');
       if (sidebarToggleElement) {
           sidebarToggleElement.addEventListener('click', function () {
               toggleSidebar();
           });
       }
       getUserData();
       // automatic display of home content,when website is loaded
               var homeContent = document.getElementById('homePage');
               if (homeContent) {
                   homeContent.style.display = 'block';
               }
   });

   function toggleSidebar() {
       var sidebarElement = document.querySelector('#sidebarContainer');
       var sidebarContent = document.querySelectorAll('.content')
       var dropdownIcon = document.getElementById('dropdownUser1');

       if (sidebarElement) {
           sidebarElement.classList.toggle('collapsed');
            if (sidebarElement.classList.contains('collapsed')) {
                      dropdownIcon.classList.remove('disabled');
                  } else {
                       dropdownIcon.classList.add('disabled');
                  }
            sidebarContent.forEach(function (contentElement) {
                contentElement.classList.toggle('collapsed');
              });
       }
   }




// wait for the document is loaded
document.addEventListener('DOMContentLoaded', function () {

  var sidebarNav = document.getElementById('sidebarNav');
  var navBar = document.getElementById('navbar');
  var dropdownMenu = document.getElementById('dropdownMenu');
  var dropdown = document.getElementById('dropdown1');

function handleNavClick(event) {
  event.preventDefault();

  var clickedItem = event.target.closest('.nav-link');

  if (clickedItem) {
    var allNavLinks = document.querySelectorAll('.nav-link');
    if (!clickedItem.classList.contains('active')) {
      for (var i = 0; i < allNavLinks.length; i++) {
        allNavLinks[i].classList.remove('active');
      }

      clickedItem.classList.add('active');

      var contentId = clickedItem.getAttribute('data-content');
      var selectedContent = document.getElementById(contentId);

      var allContents = document.getElementsByClassName('content');
      for (var k = 0; k < allContents.length; k++) {
        allContents[k].style.display = 'none';
      }

      if (selectedContent) {
        selectedContent.style.display = 'block';
      }
    }
  }
}

// add event listener for sidebar
sidebarNav.addEventListener('click', handleNavClick);
// add event listener for navbar
navBar.addEventListener('click', handleNavClick);
dropdownMenu.addEventListener('click', handleNavClick);
dropdown.addEventListener('click', handleNavClick);

});
async function getUserData() {
  // get token from cookies
  debugger;
const cookies = document.cookie.split(";").reduce((acc, cookie) => {
  const [key, value] = cookie.trim().split("=");
  acc[key] = value;
  return acc;
}, {});

const authToken = cookies["authToken"];
console.log(authToken);


  const response = await fetch("/Insurance/users", {
    method: "GET",
    headers: {
      "Authorization": `Bearer ${authToken}`,
    },
  });

  if (response.status === 200) {
    const client = await response.json();
    console.log(client);
    displayClientInfo(client);

  } else if (response.status === 401) {
          // auth token is invalid
          window.location.href = "/Insurance/login";
        } else {

          console.error(`error getting data: ${response.status}`);
        }
}



function deleteAllCookies() {
    //delete of cookies
    console.log(cookies);
    var cookies = document.cookie.split("; ");
    for (var c = 0; c < cookies.length; c++) {
        var d = window.location.hostname.split(".");
        while (d.length > 0) {
            var cookieBase = encodeURIComponent(cookies[c].split(";")[0].split("=")[0]) + '=; expires=Thu, 01-Jan-1970 00:00:01 GMT; domain=' + d.join('.') + ' ;path=/Insurance';
            var p = location.pathname.split('/');
            document.cookie = cookieBase + '/';
            while (p.length > 0) {
                document.cookie = cookieBase + p.join('/');
                p.pop();
            };
            d.shift();
        }

        document.cookie = 'authToken=; expires=Thu, 01-Jan-1970 00:00:01 GMT; path=/Insurance';
        console.log(cookies);
    }

    // Vymazanie sessionId
    sessionStorage.clear();
    // delete cache
    if ('caches' in window) {
        caches.keys().then(function(names) {
            names.forEach(function(name) {
                caches.delete(name);
            });
        });
    }
}

function preventBack() {
    window.history.forward();
}
setTimeout(preventBack, 0);
window.onunload = function () { null };

   // Function for logout
   function logout() {
       fetch('/Insurance/logout', {
           method: 'POST',
           headers: {
               'Content-Type': 'application/json'
           },
           body: JSON.stringify({ /* another info */ })
       }).then(response => {
        deleteAllCookies();
         window.location.href = "/Insurance/login";
          alert("Logout was successful");
        preventBack();

       }).catch(error => {
           console.error('Error when logging out:', error);
       });

       }


document.addEventListener('click', function(event) {
    if (event.target && event.target.classList.contains('signOut')) {
        logout();
    }
});

document.addEventListener('click', function(event) {
    var clickedEdit = event.target.closest('.iconPencil');
    if (clickedEdit) {
        var inputField =  clickedEdit.closest('.form-floating').querySelector('input');
        var isDisabled = inputField.disabled;

        if (isDisabled) {
           inputField.disabled = false;
            inputField.focus();

        }
    }
});


})();

function getUserIdFromUrl() {
  // get actual url
  var url = window.location.href;

  // regex for extraction id from url
 var userIdRegex = /\/(?:mobileClient|clientInfo)\/(\d+)[^\d]?/;

  var match = url.match(userIdRegex);

  //if matching number was not found, return it as userId, otherwise return null
  if (match && match.length > 1) {
    return match[1];
  } else {
    return null;
  }
}
function updateData(form) {
   var userId = getUserIdFromUrl();
   var clientType;
   var url;

    // control ,if user is on mobile device
    var isMobileDevice = /Mobi|Android/i.test(navigator.userAgent);

    //set the correct url
    if (isMobileDevice) {
        url = "/Insurance/mobileClient/" + userId;
        clientType = "mobileClient";
    } else {
        url = "/Insurance/clientInfo/" + userId;
        clientType = "clientInfo";
    }
      // Enable all input fields before submitting the form
      var inputFields = form.querySelectorAll('input, textarea, select');
      inputFields.forEach(function(input) {
        input.disabled = false;
      });

  const formData = new FormData(form);
  // Send the FormData object to the Spring POST method
  fetch(url, {
    method: "PUT",
    body: formData
  })
    .then(response => {
      if (response.status === 200) {
        // Registration was successful
        alert("Data has been successfully updated!");
      }else if(response.status === 403){
     alert("The entered data is not in the correct format")
      }else {
        // Registration failed
        alert("Failed to update data!");

      }
    })
    .catch(error => {
      alert(error);
    });
    event.preventDefault();
}

function saveProfilePicture(){
    var inputPicture = document.getElementById("imageInput");
    var image = input.files[0];
    var resizedImage = resizeAndCompressImage(image, 32, 32, 0.7);
    var formData = new FormData();
    var userId = getUserIdFromUrl;
    formData.append('image', resizedImage);
     fetch('/Insurance/changeImage', {
            method: 'POST',
            body: formData
        })
        .then(response => {
            if (response.ok) {
                return response.text();
            }
            throw new Error('Network response was not ok.');
        })
        .then(data => {
            console.log(data);
            // process data from server
        })
        .catch(error => {
            console.error('There was a problem with your fetch operation:', error);
        });
}

function resizeAndCompressImage(file, maxWidth, maxHeight, quality) {
    var img = new Image();
    img.src = URL.createObjectURL(file);

    var canvas = document.createElement('canvas');
    var ctx = canvas.getContext('2d');

    var width = img.width;
    var height = img.height;

    if (width > height) {
        if (width > maxWidth) {
            height *= maxWidth / width;
            width = maxWidth;
        }
    } else {
        if (height > maxHeight) {
            width *= maxHeight / height;
            height = maxHeight;
        }
    }

    canvas.width = width;
    canvas.height = height;
    ctx.drawImage(img, 0, 0, width, height);

    var compressedDataUrl = canvas.toDataURL('image/jpeg', quality);

    // Convert data URL to Blob
    var byteString = atob(compressedDataUrl.split(',')[1]);
    var ab = new ArrayBuffer(byteString.length);
    var ia = new Uint8Array(ab);
    for (var i = 0; i < byteString.length; i++) {
        ia[i] = byteString.charCodeAt(i);
    }
    var blob = new Blob([ab], { type: 'image/jpeg' });

    return blob;
}
}



