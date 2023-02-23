const hamburger = document.getElementById('hamburger');
const hamburgers = document.getElementsByClassName('hamburger_line');
const navUL = document.getElementById('nav-ul');
const header = document.getElementById('header');

hamburger.addEventListener('click', ()=>{
    navUL.classList.toggle('show');
    header.classList.toggle('bg');
    for(let i = 0; i<hamburgers.length; i++){
        hamburgers[i].style.backgroundColor="white";
    }
})
