let cartProduct = localStorage.getItem("cart") == null ? [] : JSON.parse(localStorage.getItem("cart"));
$(".count").html(cartProduct.length)
$(document).ready(function () {
        let str = '';
        for (let i = 0 ; i< cartProduct.length;i++) {
            str += `   <tr>
                        <td>${cartProduct[i].productName}<strong class="mx-2">x</strong>${cartProduct[i].quantity}</td>
                        <td>${cartProduct[i].quantity * cartProduct[i].price}</td>
                      </tr>`
        }
        $("#tbody").html(str);

        $("#finish-Order").on('click',function oder(){
                let customerInfo = {
                        fullname : $("#c_fname").val(),
                        address: $("#c_address").val(),
                        country: $("#c_state_country").val(),
                        email: $("#c_email_address").val(),
                        phoneNumber: $("#c_phone").val(),
                        orderNotes: $("#c_order_notes").val()
                }
                let sendingObj = {
                        "products": cartProduct,
                        "customerInfoDTO": customerInfo,

                }
                $.ajax({
                        type: "POST",
                        headers: {
                                "Content-Type": "application/json",
                                'Accept': 'application/json',
                                'Authorization': 'Bearer ' + localStorage.getItem('token')
                        },
                        url: "http://localhost:8080/products/cart",
                        data: JSON.stringify(sendingObj),
                        success: function (data) {
                                alert("Order thành công")
                                localStorage.removeItem("cart");
                                location.href="shop.html"

                        },
                        error: function (err) {
                                localStorage.removeItem("cart");
                                location.href="shop.html"
                                alert("Số lượng sản phẩm bạn mua vượt quá số lượng sản phẩm còn lại, mong bạn thông cảm!")
                                console.log(err) // lỗi
                        }
                });
        })

        function getAll() {
                $.ajax({
                        type: "GET",
                        headers: {
                                'Accept': 'application/json',
                                'Authorization': 'Bearer ' + localStorage.getItem('token')
                        },
                        url: "http://localhost:8080/products",
                        success: function (data) {
                                getShoesData(data);
                        },
                        error: function (err) {
                                console.log(err) // lỗi
                        }
                });
        }

        function getShoesData(arr) {
                document.querySelector('#t-body').innerHTML = '';
                let str = ``;
                for (let i = 0; i < arr.length; i++) {
                        findCategory(arr[i].id, function (sizeData) {
                                str += `
             <div class="col-sm-6 col-lg-4 mb-4" data-aos="fade-up" style="font-family: Arial, Helvetica, sans-serif;color: black">
                <div class="block-4 text-center border">
                  <figure class="block-4-image">
                    <a href="shop-single.html?id=${arr[i].id}"><img src="${arr[i].image}" alt="Image placeholder" class="img-fluid"></a>
                  </figure>
                  <div class="block-4-text p-4">
                  <div style="display: flex;justify-content: space-between">
                    <p id="sizeNumber">sizes: ${sizeData}</p>
                    <p id="sizeNumber">Màu: ${arr[i].color}</p>
                  </div>
                    <h3><a href="shop-single.html">${arr[i].category.name}</a></h3>
                    <p class="mb-0">${arr[i].productName}</p>
                    <p class="text-primary font-weight-bold">Giá tiền: ${arr[i].price}$</p>
                  </div>
                </div>
              </div>
        `;
                                $("#t-body").html(str);
                        });
                }
        }

        function findCategory(id, callback) {
                $.ajax({
                        type: "GET",
                        headers: {
                                'Accept': 'application/json',
                                'Authorization': 'Bearer ' + localStorage.getItem('token')
                        },
                        url: "http://localhost:8080/products/findsize/" + id,
                        success: function (data) {
                                console.log(data)
                                callback(data);
                        },
                        error: function (err) {

                                console.log(err) // lỗi
                        }
                });
        }
});