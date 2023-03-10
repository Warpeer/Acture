$(document).ready(()=>{
    calculatePrice();
});
let slider = document.getElementById("myRange");
let output = document.getElementById("demo");
output.innerHTML = slider.value; // Display the default slider value

// Update the current slider value (each time you drag the slider handle)

$('#more_info').click(()=>{
    let div = $('#top_text_info');
    if(div.css("display")=== "none"){
        div.show("fast");
    }else div.hide("fast");
});
$('#more_info2').click(()=>{
    let div = $('#design_info');
    if(div.css("display")=== "none"){
        div.show("fast");
    }else div.hide("fast");
});

$('.btn').click(()=>{
    $(this).addClass('btn_active');
});


let webappType = "static";
let domain = true;
let pages = 1;
let design = "low";
let content = true;

$('#btn_1_1').click(()=>{
    webappType="static";
    calculatePrice();
});
$('#btn_1_2').click(()=>{
    webappType="interactive";
     calculatePrice();
});
$('#btn_1_3').click(()=>{
    webappType="fullstack";
     calculatePrice();
});


$('#btn_2_1').click(()=>{
    domain=true;
    calculatePrice();
});
$('#btn_2_2').click(()=>{
    domain=false;
     calculatePrice();
});

slider.oninput = function() {
    output.innerHTML = this.value;
    pages=this.value;
    calculatePrice();
}

$('#btn_3_1').click(()=>{
    design="low";
    calculatePrice();
});
$('#btn_3_2').click(()=>{
    design="medium";
    calculatePrice();
});
$('#btn_3_3').click(()=>{
    design="high";
    calculatePrice();
});

$('#btn_4_1').click(()=>{
    content=true;
    calculatePrice();
});
$('#btn_4_2').click(()=>{
    content=false;
    calculatePrice();
});



function calculatePrice(){
      let price=0;
      pagePrice = 0;

      switch(webappType) {
        case 'static':
            pagePrice = 1000;
            break;
        case 'interactive':
            pagePrice = 2000;
            break;
            case 'fullstack':
            pagePrice = 3500;
            break;
      }
      price = pagePrice*pages;
      if(domain===false){
        price+=200;
      }

      
      switch (design){
        case 'medium':
            price*=1.3;
            break;
            case 'high' :
              price*=2;
      }
      if(content===false){
        let contentPrice = 150*pages;
        price+=contentPrice;
      }
        const outputElement = $("#final_result");
        outputElement.html(price);
}

function toHomepage(){
    window.location.href = "../index.html";
}


