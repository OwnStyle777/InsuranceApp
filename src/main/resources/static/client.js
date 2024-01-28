
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

// Počkáme, až se načte celý dokument
document.addEventListener('DOMContentLoaded', function () {
  // Získáme referenci na navigační menu ve sidebaru
  var sidebarNav = document.getElementById('sidebarNav');

  // Přidáme posluchač události pro kliknutí na navigační odkazy
  sidebarNav.addEventListener('click', function (event) {
    // Zabráníme výchozí akci kliknutí (např. skoku na odkaz)
    event.preventDefault();

    // Získáme odkaz, na který bylo kliknuto
    var clickedItem = event.target.closest('.nav-link');

    // Pokud byl kliknutý nějaký odkaz
    if (clickedItem) {

      var allNavLinks = sidebarNav.getElementsByClassName('nav-link');
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
  });
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
    // ...
  } else if (response.status === 401) {
          // Autentifikačný token je neplatný, možno presmerovať na prihlasovaciu stránku
          window.location.href = "/Insurance/login";
        } else {
          // Iný chybový stav
          console.error(`Chyba pri získavaní údajov: ${response.status}`);
        }
}


})();

