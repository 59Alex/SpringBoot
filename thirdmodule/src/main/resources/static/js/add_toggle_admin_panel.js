/*добавляет логику переключения admin панели*/
function addToggleAdminPannel() {
    /*обработчик на кнопе New User переключает стили*/
    $('#newUserBtn').on('click', function (e) {
        e.preventDefault();
        toggleAdminPanel();
        $('#adminPanelHead').text("Add new user");
    });

    /*бработчик на кнопке Users Table переключает стили*/
    $('#usersTableBtn').on('click', function (e) {
        e.preventDefault();
        toggleAdminPanel();
        $('#adminPanelHead').text("All users");
    });


}
/*функция переключения стилей кнопок и collapse*/
function toggleAdminPanel() {
    $('#newUser').toggleClass('show');
    $('#usersTable').toggleClass('show');
    $('#newUserBtn').toggleClass('active');
    $('#usersTableBtn').toggleClass('active');
}