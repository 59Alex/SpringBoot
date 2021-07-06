
/*отправляет запрос на контроллер /get получает всех юзеров*/
async function getUsers() {
    let promise = await fetch('/admin/get');
    if(promise.ok) {
        let data = await promise.json();
        let insert_users = '';
        for(let i = 0; i < data.length; i++) {
            insert_users += '<tr><th scope="row">' + data[i].id + '</th>';
            insert_users += '<td>' + data[i].firstName + '</td>';
            insert_users += '<td>' + data[i].lastName + '</td>';
            insert_users += '<td>' + data[i].age + '</td>';
            insert_users += '<td>' + data[i].username + '</td><td>';
            for(let t = 0; t < data[i].roles.length; t++) {
                insert_users += data[i].roles[t].role + ' ';
            }
            insert_users += '</td><td><a type="button" href="/' + data[i].id + '" id="edit_user_open_form_btn" class="btn btn-info" data-bs-toggle="modal" data-bs-target="#edit_user_modal">Edit</a></td><td><a href="/' + data[i].id + '" type="button" id="delete_user_open_form" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#delete_user_modal">Delete</a></td></tr>';
        }
        $('#insert_users').html("");
        $('#insert_users').html(insert_users);
    } else {
        alert("err");
    }
}

/* функция изменения юзера*/
async function updataUser(user_id) {
    let response = await fetch('/admin/update' + user_id, {
        method: "put",
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify(getJSONFromForm('#edit_user_form'))
    });
    if(response.ok) {
        getUsers();
    } else {
        alert("Такой email уже существует!");
    }
}

/*функция удаления юзера*/
async function deleteUser(user_id) {
    let response = await fetch('/admin/delete' + user_id, {
        method: "delete",
    });
    if(response.ok) {
        getUsers();
    }
}

/* функция сохранения юзера */
async function save() {
    let response = await fetch('/admin/save', {
        method: "post",
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify(getJSONFromForm("#saveForm"))
    });
    if(response.ok) {
        $('#save_user_alert').html('');
        $('#save_user_alert').html('<div class="alert alert-info" role="alert">Пользователь был сохранён.</div>')
        getUsers();
    } else {
        $("#save_user_alert").html('');
        $("#save_user_alert").html('<div class="alert alert-danger" role="alert">Этот Email уже занят.</div>');
    }

}