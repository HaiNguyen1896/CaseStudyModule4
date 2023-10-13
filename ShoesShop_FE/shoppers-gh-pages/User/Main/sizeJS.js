let currentSelection;
$(document).ready(function () {
    getAllSize();

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
            strSize += `<option value="${arr[i].id}">${arr[i].size}</option>`;
        }
        ;
        $("#sizes-list").html(strSize);
    }

    $("#sizes-list").on("change", function () {
        // Lấy giá trị của phần tử <select> sau khi người dùng đã thực hiện lựa chọn
        const selectedSize = $(this).val();
        $.ajax({
            type: "GET",
            headers: {
                'Accept': 'application/json',
                'Authorization': 'Bearer ' + localStorage.getItem('token')
            },
            url: "http://localhost:8080/products/findProductBySize/" + selectedSize,
            success: function (data) {
                console.log(data);
                getShoesData(data);
            },
            error: function (err) {
                console.log(err) // lỗi
            }
        });

    });

    // Hàm để tìm sản phẩm dựa vào các checkbox được chọn
    function getShoesData(arr) {
        let str = ``;
        for (let i = 0; i < arr.length; i++) {
            findCategory(arr[i].id, function (sizeData) {
                str += `
             <div class="col-sm-6 col-lg-4 mb-4" data-aos="fade-up" style="font-family: Arial, Helvetica, sans-serif;color: black">
                <div class="block-4 text-center border">
                  <figure class="block-4-image">
                    <a href="shop-single.html"><img src="${arr[i].image}" alt="Image placeholder" class="img-fluid"></a>
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
