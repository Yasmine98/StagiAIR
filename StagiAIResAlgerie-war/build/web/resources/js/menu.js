/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


jQuery(document).ready(function() {
    window.alert("HI")
    $('#accordion ul > li').hover(function() {
        $(this).children("ul").slideToggle('slow');
    });
});