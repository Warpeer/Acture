function logIn() {
    const url = "/logIn?email="+$('#email').val()+"&pwd="+$('#pwd').val();
    $.get(url, (response)=>{
        if(response){
            window.location.href="../dashboard.html";
        }else{
            $('#error').html("Feil brukernavn eller passord");
        }
    })
}