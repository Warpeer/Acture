
$('.cards').click(function(){
    $('.cards').removeClass("clicked");
});
$("#text-1").show();
$("#cards-1").toggleClass("clicked");
$('#cards-1').click(function(){
    $('.text').hide();
    $('#text-1').show();
    $('#cards-1').show();
    $('#cards-1').toggleClass("clicked"); //<== toggleClass
});

$('#cards-2').click(function(){
    $('.text').hide();
    $('#text-2').show();
    $('#cards-2').show();
    $('#cards-2').toggleClass("clicked");
});
$('#cards-3').click(function(){
    $('.text').hide();
    $('#text-3').show();
    $('#cards-3').show();
    $('#cards-3').toggleClass("clicked"); //<== toggleClass
});

$('#cards-4').click(function(){
    $('.text').hide();
    $('#text-4').show();
    $('#cards-4').show();
    $('#cards-4').toggleClass("clicked");
});


var swiper = new Swiper( ".swiper-container", {
    autoplay: {
        delay: 4000,
    },
    navigation: {
        nextEl: ".swiper-button-next",
        prevEl: ".swiper-button-prev"
    },
    pagination: {
        el: '.swiper-pagination',
    },
});


function showMore1(){
    const paragraph = $("#tjenester-lesmer1")
    if(paragraph.css("display")==="none"){
        paragraph.show();
        console.log("funker")
    }else{
        paragraph.hide() ;
        console.log("funker ikke")
    }

}

function showMore2(){
    const paragraph = $("#tjenester-lesmer2")
    if(paragraph.css("display")==="none"){
        paragraph.show();
        console.log("funker")
    }else{
        paragraph.hide() ;
        console.log("funker ikke")
    }

}

function showMore3(){
    const paragraph = $("#tjenester-lesmer3")
    if(paragraph.css("display")==="none"){
        paragraph.show();
        console.log(paragraph)
    }else{
        paragraph.hide() ;
        console.log("funker ikke")
    }

}

function showMore4(){
    const paragraph = $("#tjenester-lesmer4")
    if(paragraph.css("display")==="none"){
        paragraph.show();
        console.log("funker")
    }else{
        paragraph.hide() ;
        console.log("funker ikke")
    }

}

