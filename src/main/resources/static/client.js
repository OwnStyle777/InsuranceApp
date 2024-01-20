
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
})();

