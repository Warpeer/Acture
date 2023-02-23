 

const body = document.body; 

let lastScroll = 0; 

 
 

window.addEventListener("scroll", () => { 

const currentScroll = window.pageYOffset; 

if (currentScroll <= 0) { 

body.classList.remove("scroll-up"); 

return; 

} 

 
 

if (currentScroll > lastScroll && !body.classList.contains("scroll-down")) { 

body.classList.remove("scroll-up"); 

body.classList.add("scroll-down"); 

} else if ( 

currentScroll < lastScroll && 

body.classList.contains("scroll-down") 

) { 

body.classList.remove("scroll-down"); 

body.classList.add("scroll-up"); 

} 

lastScroll = currentScroll; 

}); 

function updateBackground(){
    const background = $('')
}

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
$('#timeline_item_1').click(()=>{
    console.log("funker!");
    let div = $('#timeline_desc_1');
    if(div.css("display") === "none"){
        console.log("funker!");
        div.show("fast");
        $('#tl_more_info1').css("src", "./images/icons/timeline_icons/read_less.svg");
    }else div.hide("fast");
});
$('#timeline_item_2').click(()=>{
    let div = $('#timeline_desc_2');
    if(div.css("display")=== "none"){
        div.show("fast");
        $('#tl_more_info2').css("src", "./images/icons/timeline_icons/read_less.svg");
    }else div.hide("fast");
});
$('#timeline_item_3').click(()=>{
    let div = $('#timeline_desc_3');
    if(div.css("display")=== "none"){
        div.show("fast");
        $('#tl_more_info3').css("src", "./images/icons/timeline_icons/read_less.svg");
    }else div.hide("fast");
});
$('#timeline_item_4').click(()=>{
    let div = $('#timeline_desc_4');
    if(div.css("display")=== "none"){
        div.show("fast");
        $('#tl_more_info4').css("src", "./images/icons/timeline_icons/read_less.svg");
    }else div.hide("fast");
});

function regMessage(){
    const contactMessage = {
        fullName: $('#fullName').val(),
        email: $('#email').val(),
        message: $('#message').val(),
    };
    $.post("/saveMessage", contactMessage, ()=>{
        console.log("Lastet opp");
    });
}
