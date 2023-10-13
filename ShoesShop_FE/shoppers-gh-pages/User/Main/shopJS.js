let token = localStorage.getItem('token');
let cartProduct = localStorage.getItem("cart") == null ? [] : JSON.parse(localStorage.getItem("cart"));
$(".count").html(cartProduct.length);

getAll();
getProductByRangeSlide();

$(document).ready(function () {
    getProductByRangeSlide();
    getCategories();
});

function getAll() {
    $.ajax({
        type: "GET",
        headers: {
            'Accept': 'application/json',
            'Authorization': 'Bearer ' + localStorage.getItem('token')
        },
        url: "http://localhost:8080/products?page=0&limit=3",
        success: function (data) {
            getShoesData(data);
        },
        error: function (err) {
            console.log(err) // lỗi
        }
    });
}

function getShoesData(arr) {
    let str = ``;
    for (let i = 0; i < arr.length; i++) {
        getProductStatus(arr[i].id, i, function (data, index) {
            if (data == false) {
                arr[index].status = 'Hết hàng';
            } else {
                arr[index].status = 'Còn hàng';
            }

            findCategory(arr[i].id, function (sizeData) {
                str += `
             <div class="col-sm-6 col-lg-4 mb-4" data-aos="fade-up" style="font-family: Arial, Helvetica, sans-serif;color: black;height: 100%;width: 100%">
                <div class="block-4 text-center border">
                  <figure class="block-4-image">
                    <a href="shop-single.html?id=${arr[i].id}"><img src="../../ImgProduct/${arr[i].image}" style="width: 300px;height: 300px" alt="Image placeholder" class="img-fluid"></a>
                  </figure>
                  <div class="block-4-text p-4">
                  <div style="display: flex;justify-content: space-between">
                    <p id="sizeNumber">sizes: ${sizeData}</p>
                    <p id="sizeNumber">Màu: ${arr[i].color}</p>
                  </div>
                    <h3><a href="shop-single.html">${arr[i].category.name}</a></h3>
                    <p class="mb-0"  style="font-weight: bold">${arr[i].productName}</p>
                    <div style="display: flex;justify-content: space-between;font-size: 12px;">
                    <p class="text-primary font-weight-bold">Giá tiền: ${arr[i].price}$</p>
                    <p class="text-primary font-weight-bold">Tình trạng: ${arr[i].status}</p>
                    </div>
                  </div>
                </div>
              </div>`;
                $("#t-body").html(str);
            });
        });
    }
}
    function getProductStatus(productId, index, callback) {
        $.ajax({
            type: "GET",
            headers: {
                'Accept': 'application/json',
                'Authorization': 'Bearer ' + localStorage.getItem('token')
            },
            url: "http://localhost:8080/products/checkRemainProduct/" + productId,
            success: function (data) {
                callback(data, index);
            },
            error: function (err) {
                console.log(err) // lỗi
            }
        });
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

    function getCategories() {
        $.ajax({
            type: "GET",
            headers: {
                'Accept': 'application/json',
                'Authorization': 'Bearer ' + localStorage.getItem('token')
            },
            url: "http://localhost:8080/category",
            success: function (data) {
                let str = '';
                let newestBtn = '';
                let listCategory = data;
                for (let i = 0; i < listCategory.length; i++) {
                    getProductQuantity(listCategory[i].id, function (sizeData) {
                        str += `<li class="mb-1">
                                <a href="#" class="d-flex" onclick="getProductByCategory(${listCategory[i].id}); return false;">
                                    <span>${listCategory[i].name}</span>
                                    <span class="text-black ml-auto" id="productQuantity">(${sizeData})</span>
                                </a></li>`;
                        $("#category-list").html(str);
                    })

                    newestBtn += `<a class="dropdown-item" href="#">${listCategory[i].name}</a>`
                }

                $("#newestBtn").html(newestBtn);
            },
            error: function (err) {
                console.log(err) // lỗi
            }
        });
    }

    function getProductByCategory(id) {
        $.ajax({
            type: "GET",
            headers: {
                'Accept': 'application/json',
                'Authorization': 'Bearer ' + localStorage.getItem('token')
            },
            url: "http://localhost:8080/products/findProductByCateID/" + id,
            success: function (data) {
                getShoesData(data);
            },
            error: function (err) {
                console.log(err) // lỗi
            }
        });
    }

    function getProductQuantity(id, callback) {
        $.ajax({
            type: "GET",
            headers: {
                'Accept': 'application/json',
                'Authorization': 'Bearer ' + localStorage.getItem('token')
            },
            url: "http://localhost:8080/products/findByCateID/" + id,
            success: function (data) {
                callback(data);
            },
            error: function (err) {
                console.log(err) // lỗi
            }
        });
    }

    function getProductByRangeSlide() {
        $("#slider-range").slider({
            range: true,
            min: 0,
            max: 1000,         // Giá tối đa sản phẩm
            values: [0, 1000], // Phạm vi giá ban đầu
            slide: function (event, ui) {
                $("#amount").val("$" + ui.values[0] + " - $" + ui.values[1]);
            }
        });
        // Hiển thị giá trị phạm vi giá ban đầu
        $("#amount").val("$" + $("#slider-range").slider("values", 0) +
            " - $" + $("#slider-range").slider("values", 1));

        // Lọc sản phẩm khi thanh kéo được kéo
        $("#slider-range").on("slidechange", function (event, ui) {
            const minPrice = ui.values[0];
            const maxPrice = ui.values[1];
            // hàm lọc sản phẩm dựa trên phạm vi giá (minPrice và maxPrice)
            filterProductsByPrice(minPrice, maxPrice);
        })
    };

    function filterProductsByPrice(minPrice, maxPrice) {
        $.ajax({
            type: "GET",
            headers: {
                'Accept': 'application/json',
                'Authorization': 'Bearer ' + localStorage.getItem('token')
            },
            url: `http://localhost:8080/products/findByPrice/` + minPrice + '/' + maxPrice,
            success: function (data) {
                getShoesData(data)
            },
            error: function (err) {
                console.log(err) // lỗi
            }
        });
    }


