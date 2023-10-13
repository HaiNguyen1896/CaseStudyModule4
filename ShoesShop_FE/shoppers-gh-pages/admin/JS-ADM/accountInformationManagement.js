$(document).ready(function () {
    getAll();

    function getAll() {
        $.ajax({
            type: "GET",
            headers: {
                'Accept': 'application/json',
                'Authorization': 'Bearer ' + localStorage.getItem('token')
            },
            url: "http://localhost:8080/account/findAccountByRoleUser",
            success: function (data) {
                getAccountInfo(data);
            },
            error: function (err) {
                console.log(err) // lỗi
            }
        });
    }

    function getAccountInfo(arr) {
        let str = ` <tr>
                                    <th>ID</th>
                                    <th>Họ và tên</th>
                                    <th>Số điện thoại</th>
                                    <th>Địa chỉ</th>
                                 
                                    <th>Quốc gia</th>
                                    <th>Vai trò</th>                        
                                    <th>Tên đăng nhập</th>
                                    <th>Mật khẩu</th>
                                  
                                </tr>`;
        for (let i = 0; i < arr.length; i++) {
                    str += `
              <tr>
                  <td>${arr[i].id}</td>
                  <td>${arr[i].firstName} ${arr[i].lastName}</td>
                  <td>${arr[i].phone}</td>
                  <td>${arr[i].address}</td>
                  <td>${arr[i].country.countryName}</td>
                  <td>${arr[i].role.roleName}</td>
                  <td>${arr[i].username}</td>
                  <td>${arr[i].password}</td>
              </tr>`;

                }
                $("#body-table").html(str);

        }


    function findAccountInformation() {
        $.ajax({
            type: "GET",
            headers: {
                'Accept': 'application/json',
                'Authorization': 'Bearer ' + localStorage.getItem('token')
            },
            url: "http://localhost:8080/account",
            success: function (data) {
                console.log(data)
            },
            error: function (err) {
                console.log(err) // lỗi
            }
        });
    }
});