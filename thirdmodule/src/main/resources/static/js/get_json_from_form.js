/* функция получения JSON бьекта из формы по id формы
преобразует данные из формы в JSON объект USER, принимает id формы*/
function getJSONFromForm(id) {
    let n_user_JSON = {};
    let data = $(id).serializeArray();
    n_user_JSON["roles"] = [];
    for(let i = 0; i < data.length; i++) {
        if(data[i].name != "roles") {
            n_user_JSON[data[i].name] = data[i].value;
        } else if(data[i].name == "id") {
            continue;
        } else {
            n_user_JSON[data[i].name].push(
                {
                    role: data[i].value
                });
        }
    }
    return n_user_JSON;
}