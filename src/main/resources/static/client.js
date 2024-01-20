
(() => {
    'use strict';

    //$(document).ready(function () {
    //  $(".nav-link").click(function (e) {
    //    e.preventDefault();
    //
    //    // Hide all content sections
    //    $(".content-section").removeClass("active");
    //
    //    // Get the class of the clicked link's span
    //    var sectionClass = $(this).find("span").attr("class");
    //
    //    // Display the corresponding section
    //    $("." + sectionClass).addClass("active");
    //  });


   document.addEventListener('DOMContentLoaded', function () {
       var sidebarToggleElement = document.querySelector('.sidebar-toggle-icon');
       if (sidebarToggleElement) {
           sidebarToggleElement.addEventListener('click', function () {
               toggleSidebar();
           });
       }
   });

   function toggleSidebar() {
       var sidebarElement = document.querySelector('#sidebarContainer');
       if (sidebarElement) {
           sidebarElement.classList.toggle('collapsed');
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
})();

