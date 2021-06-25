
function addUserModalLogic() {
    /*
обработчик события открытия модальных окон
получает юзера по id и вставляет данные в value формы
*/

    $('#edit_user_modal').on('show.bs.modal', function (event) {
        user_id = $(event.relatedTarget).attr('href');
        $.get('http://localhost:8080/admin/getById' + user_id, function (data) {
            $('#edit_user_form').find('input[name=id]').val(data.id);
            $('#edit_user_form').find('input[name=firstName]').val(data.firstName);
            $('#edit_user_form').find('input[name=lastName]').val(data.lastName);
            $('#edit_user_form').find('input[name=email]').val(data.username);
            $('#edit_user_form').find('input[name=age]').val(data.age);
        })
    })
    $('#delete_user_modal').on('show.bs.modal', function (event) {
        user_id = $(event.relatedTarget).attr('href');
        $.get('http://localhost:8080/admin/getById' + user_id, function (data) {
            $('#delete_user_form').find('input[name=id]').val(data.id);
            $('#delete_user_form').find('input[name=firstName]').val(data.firstName);
            $('#delete_user_form').find('input[name=lastName]').val(data.lastName);
            $('#delete_user_form').find('input[name=email]').val(data.username);
            $('#delete_user_form').find('input[name=age]').val(data.age);
        })
    })
}