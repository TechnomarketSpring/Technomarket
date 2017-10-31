function myFunction() {
    document.getElementById("dropdown-innert").classList.toggle("show");
}

window.onclick = function(event) {
  if (!event.target.matches('.drop_head_button')) {

    var dropdowns = document.getElementsByClassName("dropdown_content");
    var i;
    for (i = 0; i < dropdowns.length; i++) {
      var openDropdown = dropdowns[i];
      if (openDropdown.classList.contains('show')) {
        openDropdown.classList.remove('show');
      }
    }
  }
}

function isEmpty() {
    var searchedText;
    searchedText = document.getElementById("searched-text").value;
    if (!searchedText || 0 === searchedText) {
        alert("Моля въведете текст в полето!");
        return false;
    };
}