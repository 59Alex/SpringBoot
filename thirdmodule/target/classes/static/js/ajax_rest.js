
/*отправляет запрос на контроллер /get получает всех юзеров*/
function getUsers() {
    $.get('http://localhost:8080/admin/get', function (data) {
        let insert_users = '';
        for(let i = 0; i < data.length; i++) {
            insert_users += '<tr><th scope="row">' + data[i].id + '</th>';
            insert_users += '<td>' + data[i].firstName + '</td>';
            insert_users += '<td>' + data[i].lastName + '</td>';
            insert_users += '<td>' + data[i].age + '</td>';
            insert_users += '<td>' + data[i].username + '</td><td>';
            for(let t = 0; t < data[i].roles.length; t++) {
                insert_users += '<p>' + data[i].roles[t].role + '</p>';
            }
            insert_users += '</td><td><a type="button" href="/' + data[i].id + '" id="edit_user_open_form_btn" class="btn btn-info" data-bs-toggle="modal" data-bs-target="#edit_user_modal">Edit</a></td><td><a href="/' + data[i].id + '" type="button" id="delete_user_open_form" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#delete_user_modal">Delete</a></td></tr>';
        }
        $('#insert_users').html("");
        $('#insert_users').html(insert_users);
    })
}

/* функция изменения юзера*/
function updataUser(user_id) {
    $.ajax({
        url: 'http://localhost:8080/admin/update' + user_id,
        method: 'put',
        dataType: 'json',
        processData: false,
        contentType: 'application/json',
        data: JSON.stringify(getJSONFromForm("#edit_user_form")),
        complete: function () {
            getUsers();
        }
    })
}

/*функция удаления юзера*/
function deleteUser(user_id) {
    $.ajax({
        url: 'http://localhost:8080/admin/delete' + user_id,
        method: 'delete',
        dataType: 'json',
        processData: false,
        contentType: 'application/json',
        data: JSON.stringify(getJSONFromForm("#delete_user_form")),
        complete: function () {
            getUsers();
        }
    })
}

/* функция сохранения юзера */
function save() {
    $.ajax({
        url: 'http://localhost:8080/admin/save',
        method: 'post',
        dataType: 'json',
        processData: false,
        contentType: 'application/json',
        data: JSON.stringify(getJSONFromForm("#saveForm")),
        success: function (data) {
            if(data.status_save == 'not_saved') {
                console.log(data);
                $('#save_user_iformation').html('');
                $('#save_user_iformation').html('<div class="alert alert-danger"" role="alert">This email is already taken!</div>');
            } else {
                console.log(data);
                $('#save_user_iformation').html('');
                $('#save_user_iformation').html('<div class="alert alert-success" role="alert">The user has been saved!</div>');
            }

        },
        complete: function () {
            getUsers();
        }
    })
}