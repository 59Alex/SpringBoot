/*переменная для записи id юзера, которого нужно изменить*/
let user_id;

/*события на загрузке страницы*/
$(window).on('load',function (){
    getUsers();
})

/*события при полной загрузке страницы*/
$(document).ready(function () {
    /*Изменение поведения классов collapse в bootstrap*/
    $('.collapse').on('show.bs.collapse hide.bs.collapse', function (e) {
        e.preventDefault();
    })
    /*обработчик кнопки edit user обновляет юзера
    отправляет JSON user на контроллер /update*/
    $('#edit_user_btn').on('click', function () {
        updataUser(user_id);
    })
    /*обработчик кнопки delete user удаляет юзера
    отправляет JSON user на контроллер /delete*/
    $('#delete_user_btn').on('click', function () {
        deleteUser(user_id);
    })
    /*обработчик события на кнопку sava user сохраняет юзера
    отправляет json юзера на контроллер /save*/
    $('#saveUser').on('click', function (e) {
        save();
    });
    //добавление логики при открытии модальных окон
    addUserModalLogic();
    //добавление логики переключения admin панели
    addToggleAdminPannel();
})















