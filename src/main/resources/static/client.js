
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
       // Automaticky zobraz domovský obsah po načítaní stránky
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




// Počkáme, až se načíta celý dokument
document.addEventListener('DOMContentLoaded', function () {
  // Získanie referencie na navigačné menu ve sidebaru
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

// Pridanie event listenera pre navigačný panel
sidebarNav.addEventListener('click', handleNavClick);
// Pridanie event listenera pre navigačný panel v navbar
navBar.addEventListener('click', handleNavClick);
dropdownMenu.addEventListener('click', handleNavClick);
dropdown.addEventListener('click', handleNavClick);

});
async function getUserData() {
  // Získanie tokenu z cookies
  debugger;
const cookies = document.cookie.split(";").reduce((acc, cookie) => {
  const [key, value] = cookie.trim().split("=");
  acc[key] = value;
  return acc;
}, {});

const authToken = cookies["authToken"];
console.log(authToken);

  // Volanie metódy na backende na získanie používateľských údajov
  const response = await fetch("/Insurance/users", {
    method: "GET",
    headers: {
      "Authorization": `Bearer ${authToken}`,
    },
  });

  // Ak bola odpoveď úspešná, spracujeme používateľské údaje
  if (response.status === 200) {
    const client = await response.json();
    console.log(client);
    displayClientInfo(client);

  } else if (response.status === 401) {
          // Autentifikačný token je neplatný, možno presmerovať na prihlasovaciu stránku
          window.location.href = "/Insurance/login";
        } else {
          // Iný chybový stav
          console.error(`Chyba pri získavaní údajov: ${response.status}`);
        }
}



function deleteAllCookies() {
 // Vymazanie cookies
    var cookies = document.cookie.split("; ");
    for (var c = 0; c < cookies.length; c++) {
        var d = window.location.hostname.split(".");
        while (d.length > 0) {
            var cookieBase = encodeURIComponent(cookies[c].split(";")[0].split("=")[0]) + '=; expires=Thu, 01-Jan-1970 00:00:01 GMT; domain=' + d.join('.') + ' ;path=';
            var p = location.pathname.split('/');
            document.cookie = cookieBase + '/';
            while (p.length > 0) {
                document.cookie = cookieBase + p.join('/');
                p.pop();
            };
            d.shift();
        }
    }

    // Vyčistenie cache
    if ('caches' in window) {
        caches.keys().then(function(names) {
            names.forEach(function(name) {
                caches.delete(name);
            });
        });
    }
}

   // Funkcia pre odhlásenie
   function logout() {
       fetch('/logout', {
             method: 'POST',
             headers: {
                 'Content-Type': 'application/json'
             },
             body: JSON.stringify({ /* akékoľvek ďalšie informácie potrebné pre odhlásenie na serveri */ })
         }).then(response => {
             // Po úspešnom odhlásení presmerujte na prihlasovaciu stránku
             deleteAllCookies();
             window.location.href = "/Insurance/login";
         }).catch(error => {
             console.error('Chyba pri odhlasovaní:', error);
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

