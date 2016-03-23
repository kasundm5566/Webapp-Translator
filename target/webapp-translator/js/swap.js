/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function swap() {
    var from = document.getElementById("fromtext").value;
    var to = document.getElementById("totext").value;
    document.getElementById("totext").value = from;
    document.getElementById("fromtext").value = to;
}

