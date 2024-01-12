$('#btnSave').on('click', function () {
    console.log("click !")
    let http = new XMLHttpRequest();
    http.open('get','customer.json');
    http.send();
    console.log(http.responseText);
});


$('#btnUpdate').on('click', function () {

});


$('#btnDelete').on('click', function () {

});


$('#btnGetAll').on('click', function () {

});