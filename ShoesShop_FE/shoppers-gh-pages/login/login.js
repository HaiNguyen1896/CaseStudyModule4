let login = function () {
    let username = document.getElementById("username").value;
    let password = document.getElementById("password").value;
    let account = {username, password};

    let settings = {
        "url": "http://localhost:8080/login",
        "method": "POST",
        "timeout": 0,
        "headers": {
            "Content-Type": "application/json"
        },
        "data": JSON.stringify(account),
    };

    $.ajax(settings).done(function (response) {
        localStorage.setItem ("currentAccount",JSON.stringify(response));
        if (response.role.roleName === "ROLE_ADMIN") {
            localStorage.setItem("token", response.token);
            window.location.href="../admin/index.html";
        } else if (response.role.roleName === "ROLE_USER") {
            localStorage.setItem("token", response.token);
            window.location.href = "../User/Main/index.html";
        }
    }).fail(function (xhr, status, error) {
        alert("Sai Username/Password");
        window.location.href = "login-sign-up.html";
    });
}

let checkLogin = function () {
    let username = document.getElementById("username").value;
    let password = document.getElementById("password").value;

    if (username !== "" && password !== "") {
        login();
    } else {
        alert("Tài khoản và mật khẩu không được là khoảng trắng");
    }
}

let signUp = function () {
    let firstName =  document.getElementById("firstNameC").value;
    let lastName =  document.getElementById("lastNameC").value;
    let address =  document.getElementById("addressC").value;
    let phone =  document.getElementById("phoneC").value;
    let usernameC = document.getElementById("usernameC").value;
    let passwordC = document.getElementById("passwordC").value;
    let roleId = 2;
    let countryId = 1;
    let accountC = {firstName: firstName,lastName:lastName,address:address,phone: phone,username: usernameC, password: passwordC,country:{id:countryId}, role: {id: roleId}};

    let settings = {
        "url": "http://localhost:8080/account/add",
        "method": "POST",
        "timeout": 0,
        "headers": {
            "Content-Type": "application/json"
        },
        "data": JSON.stringify(accountC),
    };

    $.ajax(settings).done(function (response) {
        console.log(response);
        alert("Tạo tài khoản thành công");
        window.location.href = "login-sign-up.html";
    }).fail(function (xhr, status, error) {
        console.log("Error:", error);
        alert("Tạo tài khoản không thành công");
        window.location.href = "login-sign-up.html";
    });
}

let checkSignUp = function () {
    let usernameT = document.getElementById("usernameC").value;
    let passwordT = document.getElementById("passwordC").value;

    if (usernameT !== "" && passwordT !== "") {
        checkSignUpAccount();
    } else {
        alert("Không được để khoảng trắng");
    }
}

let checkSignUpAccount = function () {
    let usernameCheck = document.getElementById("usernameC").value;
    // $.ajax({
    //     type: "GET",
    //     headers: {
    //         'Accept': 'application/json',
    //         "Content-Type": "application/json",
    //     },
    //     url: "http://localhost:8080/account/checkByUsername/" + usernameCheck,
    //     success: function (data) {
    //         console.log(data)
    //         if (data == true) {
    //             signUp();
    //         }else {
    //             alert("Lỗi")
    //         }
    //     },
    //     error: function (err) {
    //         alert("Lỗi rồi")
    //         console.log(err) // lỗi
    //     }
    //     });
        let settings = {
            "url": "http://localhost:8080/account/checkByUsername/"+usernameCheck,
            "method": "GET",
            "timeout": 0,
            "headers": {
                "Content-Type": "application/json",
            },
        };
        $.ajax(settings).done(function (response) {
            console.log(response)
            if(response==true) {
                signUp();
            }
        }).fail(function (xhr, status, error) {
            console.log("Error:", error);
            alert("Tên đăng nhập đã tồn tại");
            window.location.href = "login-sign-up.html";
        });

    //     let flag = false;
    //     for (const a of JSON.parse(localStorage.getItem("accountList"))) {
    //         if (a.username === usernameCheck) {
    //             flag = true;
    //             break
    //         }
    //     }
    //
    //     if (!flag) {
    //         signUp();
    //     } else {
    //         alert("Tài khoản đã tốn tại");
    //     }
    // });
}


