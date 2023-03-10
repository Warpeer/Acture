$(()=>{
    $.get("/authenticate", (response)=>{
        if(response){
            fetchAllUnhandledMessages();
            fetchActiveMessages();
            fetchEmployees();
            fetchProfile();
            fetchActiveCustomers();
            fetchArchivedMessages();
        }else{
            window.location.href="../login.html";
        }
    })
})

function logOut(){
    $.get("/logOut", ()=>{
        window.location.href="../login.html";
    });
}

function fetchAllUnhandledMessages(){
    $.get("/fetchAllUnhandledMessages", (messageList)=>{
        formatMessagePool(messageList);
    });
}
function fetchEmployees(){
    $.get("/fetchAllEmployees", (employeeList)=>{
        formatEmployee(employeeList);
    })
}
function fetchProfile() {
    $.get("/fetchOneEmployee", (theEmployee)=>{
        formatProfile(theEmployee);
        getProfilePicture();
    })
}
function fetchActiveMessages(){
    $.get("/fetchAllActiveMessages", (theMessages)=>{
        formatActiveMessagePool(theMessages);
    })
}
function fetchActiveCustomers(){
    $.get("/fetchAllCustomers", (theCustomers)=>{
        formatActiveCustomers(theCustomers);
    })
}
function fetchArchivedMessages(){
    $.get("/fetchProcessedMessages", (theMessages)=>{
        formatArchivedMessages(theMessages);
    })
}

function formatMessagePool(messageList) {
    let output = "<h1>Message pool</h1><table class='messageList'><tr><th>id</th><th>Navn</th><th>Epost</th><th>Melding</th><th>Dato</th><th></th></tr>"
    for(const message of messageList){
        output+="<tr> " +
            "<td>" + message.id + "</td>" +
            "<td>" + message.fullName + "</td>" +
            "<td>" + message.email + "</td>" +
            "<td class='message'>" + message.message + "</td>" +
            "<td>" + message.received + "</td>" +
            "<td><button onclick='claimMessage("+message.id+")' class='claim_button'>Ta krav om melding</button></td>" +
            "</tr>"
    }
    output+="</table>";
    $("#message_wrapper").html(output);
}
function formatActiveMessagePool(messageList){
    let output = "<h1>Aktive dialoger</h1><table class='messageList'><tr><th>id</th><th>Navn</th><th>Epost</th><th>Melding</th><th>Dato</th><th></th></tr>";
    for(const message of messageList){
        output+="<tr> " +
            "<td>" + message.id + "</td>" +
            "<td>" + message.fullName + "</td>" +
            "<td>" + message.email + "</td>" +
            "<td class='message'>" + message.message + "</td>" +
            "<td>" + message.received + "</td>" +
            "<td style='display: flex, flex-direction: row, width: fit-content'><button onclick='addCustomerFromMessage("+message.id+")' class='claim_button'>Legg til som kunde</button><button onclick='archiveMessage("+message.id+")' class='reject_button'>Arkiver</button></td>"
            "</tr>";
    }
    output+="</table>";
    $('#activeMessages').html(output);
}
function formatActiveCustomers(customerList){
    let output = "<h1>Aktive kunder</h1><table class='messageList'><tr><th>id</th><th>Navn</th><th>Epost</th><th>Dato</th><th>Ansvarlig for dialog</th></tr>";
    for(const customer of customerList){
        output+="<tr> " +
            "<td>" + customer.id + "</td>" +
            "<td>" + customer.fullName + "</td>" +
            "<td>" + customer.email + "</td>" +
            "<td>" + customer.dateStarted + "</td>" +
            "<td>"+customer.messenger+"</td></tr>";
    }
    output+="</table>";
    $('#activeCustomers').html(output);
}
function formatArchivedMessages(messageList){
    let output = "<h1>Arkiverte meldinger</h1><table class='messageList'><tr><th>id</th><th>Navn</th><th>Epost</th><th>Melding</th><th>Dato</th><th>Status</th></tr>";
    for(const message of messageList){
        output+="<tr> " +
            "<td>" + message.id + "</td>" +
            "<td>" + message.fullName + "</td>" +
            "<td>" + message.email + "</td>" +
            "<td>" + message.message + "</td>" +
            "<td>" + message.received+"</td>" +
            "<td>" + message.messageStatus + "</td>"
            "</tr>";
    }
    output+="</table>";
    $('#messageArchive').html(output);
}
function regEmployee(){
    const Employee = {
        email: $('#email').val(),
        firstName: $('#firstName').val(),
        lastName: $('#lastName').val(),
        pwd: $('#pwd').val(),
    };
    $.post("/saveEmployee", Employee, ()=>{
        fetchEmployees();
    })
}

function claimMessage(id){
    const status = "active";
    const url = "/updateMessageStatus?status="+status+"&id="+id;
    $.post(url, (response)=>{
        if(response){
            fetchAllUnhandledMessages();
            fetchActiveMessages();
        }
    });
}
function addCustomerFromMessage(id){
    const url = "/saveCustomer?messageID="+id;
    $.post(url, (response)=>{
        if(response){
            fetchAllUnhandledMessages();
            fetchActiveMessages();
            archiveMessage(id);
            fetchActiveCustomers();
        }
    });
}

function archiveMessage(id){
    const status = "processed";
    const url = "/updateMessageStatus?status="+status+"&id="+id;
    $.post(url, (response)=>{
        if(response){
            fetchAllUnhandledMessages();
            fetchActiveMessages();
            fetchArchivedMessages();
        }
    });
}

function checkID(){
    $.get("/getID", (id)=>{
        $('#employeeID').html("id: "+id);
    })
}

function formatEmployee(employeeList){
    let output = "<table class='employeeList'><tr><th>id</th><th>Epost</th><th>Navn</th><th>Ansettelsesdato</th></tr>"
    for (const employee of employeeList){
        output+= "<tr> <td>" + employee.id + "</td><td>" + employee.email + "</td><td>"+employee.firstName+" "+employee.lastName+"</td><td>"+employee.hired+"</td></tr>"
    }
    output+= "</table>";
    $("#employee_wrapper").html(output);
}
function formatProfile(employee){
    $('#welcomeMsg').html(employee.firstName + " " + employee.lastName + ".");
    $('#profileID').val(employee.id);
    $('#profileEmail').val(employee.email);
    $('#profileFirstName').val(employee.firstName);
    $('#profileLastName').val(employee.lastName);
    $('#profileHired').val(employee.hired);
    $('#profilePwd').val(11111111);
}
function enableEditing(){
    $('#showProfile').hide();
    $('#editProfileBtn').hide();
    $('#changeInfo').show();
    $('#confirmChangesBtn').show();

    $('#updateID').val($('#profileID').val());
    $('#updateEmail').val($('#profileEmail').val());
    $('#updateFirstName').val($('#profileFirstName').val());
    $('#updateLastName').val($('#profileLastName').val());
    $('#updateHired').val($('#profileHired').val());
}
function enableEditPwd(){
    $('#showProfile').hide();
    $('#togglePwdField').hide();
    $('#changePwd').show();
    $('#changePwdBtn').show();
}
function updateInfo(){
    const Employee = {
        email: $('#updateEmail').val(),
        firstName: $('#updateFirstName').val(),
        lastName: $('#updateLastName').val(),
    };
    if(validateEmployee(Employee)){
        $.post("/updateInfo", Employee, ()=>{
            $('#changeInfo').hide();
            $('#confirmChangesBtn').hide();
            $('#showProfile').show();
            $('#editProfileBtn').show();

            $('#updateEmail').val("");
            $('#updateFirstName').val("");
            $('#updateLastName').val("");

            fetchProfile();
            fetchEmployees();
        });
    }
}
function updatePwd(){
    const oldPwd = $('#updatePwd').val();
    const newPwd = $('#newPwd').val();
    const repeatPwd = $('#repeatPwd').val();
    if(validatePwds(oldPwd, newPwd, repeatPwd)){
        const url="/updatePwd?oldPwd="+oldPwd+"&newPwd="+newPwd;
        $.get(url, (response)=>{
            switch(response){
                case 1:
                    $('#changePwd').hide();
                    $('#changePwdBtn').hide();
                    $('#showProfile').show();
                    $('#togglePwdField').show();
                    break;
                case 0:
                    $('#repeatPwd').html("Serverfeil, prøv igjen senere!");
                    break;
                case -1:
                    $('#badOldPwd').html("Feil passord!");
                    break;
            }
        })
    }
}
function getProfilePicture(){
    $.get("/getPfp", ()=>{
        $('#profilePFP').attr("src", "/getPfp");
    }, "blob")
        // .fail(()=>{
        //     $('#profilePFP').attr("src", "../images/profile_pictures/defaultpfp.jpg");
        // });
}

function toggleEditPFP(){
    $('#updatePFP').show();
    $('#edit-picture').hide();

}
function updatePFP(){
    const fileInput = $('#pfpFile')[0];
    const file = fileInput.files[0];
    const fileType = file.type;

    const formData = new FormData();
    formData.append('file', file);
    formData.append('fileType', fileType);

    $.ajax({
        url: "/updatePFP",
        method: "POST",
        data: formData,
        contentType: false,
        processData: false,
        success: (response)=>{
            if(response){
                $('#updatePFP').hide();
                $('#edit-picture').show();
                fetchProfile();
            }else{
                $('#errorUpdatePfp').html("Kunne ikke oppdatere bildet.")
            }
        }
    });
}
// function uploadPFP(){
//     const fileInput = $('#pfpFile')[0];
//     const file = fileInput.files[0];
//     const fileType = file.type;
//
//     if(fileInput.get(0).files.length !== 0){
//         const formData = new FormData();
//         formData.append('file', file);
//         formData.append('fileType', fileType);
//
//         $.ajax({
//             url: "/savePfp",
//             method: "POST",
//             data: formData,
//             contentType: false,
//             processData: false,
//             success: (response)=>{
//                 if(response){
//                     $('#updatePFP').hide();
//                     fetchProfile();
//                 }else{
//                     $('#errorUpdatePfp').html("Kunne ikke oppdatere bildet.")
//                 }
//             }
//         });
//     }else{
//
//     }
//}


function validatePwds(oldPwd, newPwd, repeatPwd){
    return validatePwd(newPwd) && validateRepeatPwd(newPwd, repeatPwd);
}
function validateEmployee(employee){
    return validateEmail(employee) && validateFirstName(employee) && validateLastName(employee);

}

function validateEmail(employee){
    const email = employee.email;
    const regexp = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    if(regexp.test(email)){
        $('#badEmail').html("");
        return true;
    }else{
        $('#badEmail').html("Ikke gyldig epostadresse.");
        return false;
    }

}

function validateFirstName(employee){
    const fName = employee.firstName;
    const regexp = /^[a-zA-ZæøåÆØÅ. \-]{2,20}$/;
    if (regexp.test(fName)){
        $('#badFirstname').html("");
        return true;
    }else{
        $("#badFirstname").html("Ikke gyldig fornavn");
        return false;
    }

}
function validateLastName(employee){
    const lName = employee.lastName;
    const regexp = /^[a-zA-ZæøåÆØÅ. \-]{2,20}$/;
    if (regexp.test(lName)){
        $('#badLastname').html("");
        return true;
    }else{
        $("#badLastname").html("Ikke gyldig etternavn");
        return false;
    }
}
function validatePwd(newPwd){
    const regexp = /^(?=.*[A-ZÆØÅa-zøæå])(?=.*\d)[A-ZØÆÅa-zøæå\d]{8,}$/;
    if(regexp.test(newPwd)){
        $('#badPwd').html("");
        return true;
    }else{
        $('#badPwd').html("Passordet må være minimum 8 tegn, minst en bokstav og et tall");
        return false;
    }
}
function validateRepeatPwd(newPwd, repeatPwd){
    if(newPwd===repeatPwd){
        $('#badRepeatPwd').html("");
        return true;
    }else{
        $('#badRepeatPwd').html("Passordet må være likt!");
        return false;
    }
}

