var params = new window.URLSearchParams(window.location.search);
var idP = params.get('id');
let cartProduct = localStorage.getItem("cart") == null ? [] : JSON.parse(localStorage.getItem("cart"));
$(".count").html(cartProduct.length);
$(".js-btn-minus").hide();
let quantity = 1;
$("#quantity-input").val(quantity);
getAllSize();
checkAddCartBtn(idP);
$(document).ready(function () {
    $.ajax({
        type: "GET",
        headers: {
            'Accept': 'application/json',
            'Authorization': 'Bearer ' + localStorage.getItem('token')
        },
        url: "http://localhost:8080/products/edit/" + idP,
        success: function (data) {
            data.size = [];
            console.log(data)
            $("#product-name").html(data.productName);
            $("#product-describle").html(data.describle);
            $("#product-price").html(data.price + "$")
            $("#product-image").attr('src', '../../ImgProduct/'+data.image)
            $("#addCartBtn").on('click', function () {
                addProduct(idP, data)
            });
        },
        error: function (err) {
            console.log(err) // lỗi
        }
    });

    $("#quantity-input").on('input',function (){
        quantity = parseInt($(this).val());
        if (quantity < 0) {
            $(this).val(0); // Nếu số âm, đặt giá trị mặc định là 1
        }
    })

    $(".js-btn-plus").on('click', function incQuantity() {
        quantity++;
        $("#quantity-input").val(quantity);
        checkQuantityInput($("#quantity-input").val());
    })


    $(".js-btn-minus").on('click', function descQuantity() {
        quantity--;
        $("#quantity-input").val(quantity);
        checkQuantityInput($("#quantity-input").val());
    })
});

function checkAddCartBtn(idP) {
    $.ajax({
        type: "GET",
        headers: {
            'Accept': 'application/json',
            'Authorization': 'Bearer ' + localStorage.getItem('token')
        },
        url: "http://localhost:8080/products/checkRemainProduct/" + idP,
        success: function (data) {
            if (data == false) {
                $("#addCartBtn").hide();
            }else {
                $("#addCartBtn").show();
            }
        },
        error: function (err) {
            console.log(err) // lỗi
        }
    });
}

function addProduct(idProduct, product) {
    let index = checkProduct(idProduct)
    if (index == -1) {
        let size = $("#sizes-list").val();
        product.quantity = quantity;
        let inputSize = {'size': size}
        product.size.push(inputSize)
        cartProduct.push(product);
        quantity = 1;
    } else {
        let size = $("#sizes-list").val();
        let inputSize = {'size': size}
        if (!checkProductSize(product, size)) {
            cartProduct[index].quantity += quantity;
        } else {
            product.size.push(inputSize)
            product.quantity = quantity;
            cartProduct.push(product)
        }
        $("#quantity-input").val(quantity)
        console.log("quantity lúc này là " + quantity)
    }
    localStorage.setItem("cart", JSON.stringify(cartProduct));
    location.reload();
}

function checkProductSize(product, size) {
    for (let p of cartProduct) {
        if (p.id == product.id) {
            if (p.size[0].size == size) {
                return false;
            }
        }
    }
    return true;
}

function checkProduct(idP) {
    for (let i = 0; i < cartProduct.length; i++) {
        if (idP == cartProduct[i].id) {
            return i;
        }
    }
    return -1;
}


function getAllSize() {
    $.ajax({
        type: "GET",
        headers: {
            'Accept': 'application/json',
            'Authorization': 'Bearer ' + localStorage.getItem('token')
        },
        url: "http://localhost:8080/sizes",
        success: function (data) {
            getSizeData(data);
        },
        error: function (err) {
            console.log(err) // lỗi
        }
    });
};

function getSizeData(arr) {
    let strSize = ``;
    for (let i = 0; i < arr.length; i++) {
        strSize += `<option value="${arr[i].size}">${arr[i].size}</option>`;
    }
    ;
    $("#sizes-list").html(strSize);
}


function checkQuantityInput(quantity) {
    if (quantity == 1) {
        $(".js-btn-minus").hide();
    } else {
        $(".js-btn-minus").show();
    };
}

