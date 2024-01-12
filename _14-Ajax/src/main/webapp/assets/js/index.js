$('#btnSave').on('click', function () {
    console.log("click !")
    let http = new XMLHttpRequest();
    http.open('get','customer.json');
    http.send();
    console.log(http.responseText);

    for (let cus of http.responseText)

    $('#order-table').append(`
            <tr>
                <td>${cus.id}</td>
            </tr>
    `);
});


$('#btnUpdate').on('click', function () {

});


$('#btnDelete').on('click', function () {

});


$('#btnGetAll').on('click', function () {

});