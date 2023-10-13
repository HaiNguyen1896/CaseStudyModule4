let curentSelectedChosenCategoryID;
let curentSelectedChosenBrandID;
var params = new window.URLSearchParams(window.location.search);
var idP = params.get('id');

let currentSelectedID;
    showEdit(idP);
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
        let str = ``;
        for (let i = 0; i < arr.length; i++) {
            findCategory(arr[i].id, function (sizeData) {
                str += `
                        <tr>
                            <td><img src="${arr[i].image}" alt="" /></td>
                            <td>${arr[i].productName}</td>
                            <td>${arr[i].quantity}</td>
                            <td>${arr[i].price}</td>
                            <td>${arr[i].salePercentage}%</td>
                            <td>${(arr[i].price*arr[i].salePercentage)}</td>
                            <td>${arr[i].color}</td>
                            <td>${arr[i].category.name}</td>
                            <td>${arr[i].brand.brandName}</td>
                            <td>${arr[i].describle}</td>
                            <td>
                              <a href="product-edit.html?id=${arr[i].id}"><button data-toggle="tooltip" title="Edit" class="pd-setting-ed"><i class="fa fa-pencil-square-o" aria-hidden="true"></i></button></a>
                              <a><button data-toggle="tooltip" title="Trash" class="pd-setting-ed"><i class="fa fa-trash-o" aria-hidden="true"></i></button></a>
                            </td>
                        </tr>`;
                $("#product-tbody").html(str);
            });
        }
    }

// $("#addBtn").on('click', function createNewProduct() {
//     let product = {
//         productName: $("#product-nameA").val(),
//         price: $("#product-priceA").val(),
//         salePercentage: $("#product-saleA").val(),
//         color: $("#product-colorA").val(),
//         salePrice: $("#product-salePriceA").val(),
//         describle: $("#product-describleA").val(),
//         // $("#product-image").attr('src',`${data.image}`);
//         image: $("#product-imageA").val(),
//         brand: {id: $("#product-brandA").val()},
//         category: {id: $("#product-categoryA").val()}
//     }
//     $.ajax({
//         type: "POST",
//         headers: {
//             "Content-Type": "application/json",
//             'Accept': 'application/json',
//             'Authorization': 'Bearer ' + localStorage.getItem('token')
//         },
//         url: "http://localhost:8080/products/add",
//         data: JSON.stringify(product),
//         success: function (data) {
//             getAll();
//             window.location.href = 'user-product-list.html';
//         },
//         error: function (err) {
//             console.log(err) // lỗi
//         }
//     });
// })

$("#addBtn").on('click', function createNewProduct() {
        productName = $("#product-nameA").val();
        price = $("#product-priceA").val();
        salePercentage = $("#product-saleA").val();
        color = $("#product-colorA").val();
        salePrice = $("#product-salePriceA").val();
        describle = $("#product-describleA").val();
        quantity = $("#product-quantityA").val();
        // $("#product-image").attr('src',`${data.image}`);
        // image = $("#product-imageA").val();
        brand=  $("#product-brandA").val();
        category=$("#product-categoryA").val();

        let form = new FormData();
        let file = $("#product-imageA").prop('files')[0];

        form.append("file",file);
        form.append("productName", productName);
        form.append("price", price);
        form.append("salePercentage", salePercentage);
        form.append("salePrice", salePrice);
        form.append("quantity", quantity);
        form.append("color", color);
        form.append("describle", describle);
        form.append("category",category);
        form.append("brand",brand);
    $.ajax({
        type: "POST",
        // headers: {
        //     "Content-Type": false,
        //     'Authorization': 'Bearer ' + localStorage.getItem('token'),
        //     "processData": false
        // },

        url: "http://localhost:8080/products/add",
        data: form,
        contentType: false,
        processData: false,
        headers: {
            'Authorization': 'Bearer ' + localStorage.getItem('token'),
        },

        success: function (data) {
            getAll();
            window.location.href = 'product-list.html';
        },
        error: function (err) {
            console.log(err) // lỗi
        }
    });
})

function showImageAddProduct() {
    let [file] = document.getElementById('product-imageA').files;
    if(file) {
        // let imageURL = URL.createObjectURL(fileInput.files[0]);
        $("#showImg").attr("src",URL.createObjectURL(file))
    }else {
        $("#showImg").style.display = 'hide'
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
    function showEdit(id) {
        $.ajax({
            type: "GET",
            headers: {
                'Accept': 'application/json',
                'Authorization': 'Bearer ' + localStorage.getItem('token')
            },
            url: "http://localhost:8080/products/edit/" + id,
            success: function (data) {
                currentSelectedID = id;
                $("#product-name").val(data.productName);
                $("#product-quantity").val(data.quantity);
                $("#product-sale").val(data.salePercentage);
                $("#product-color").val(data.color);
                $("#product-price").val(data.price);
                $("#product-salePrice").val(data.salePercentage * data.price);
                $("#product-describle").val(data.describle);
                // $("#product-image").attr('src',`${data.image}`);
                $("#product-image").val(data.image);
                curentSelectedChosenCategoryID = data.category.id;
                curentSelectedChosenBrandID = data.brand.id;
                getAllCategory();
                getAllBrand();
            },
            error: function (err) {
                console.log(err) // lỗi
            }
        });
    }

    $("#saveBtn").on('click', function () {
        let product = {
            productName: $("#product-name").val(),
            price: $("#product-price").val(),
            salePercentage: $("#product-sale").val(),
            color: $("#product-color").val(),
            salePrice: $("#product-salePrice").val(),
            describle: $("#product-describle").val(),
            // $("#product-image").attr('src',`${data.image}`);
            image: $("#product-image").val(),
            brand: {id: $("#product-brand").val()},
            category: {id: $("#product-category").val()}
        }
        $.ajax({
            type: "POST",
            headers: {
                "Content-Type": "application/json",
                'Accept': 'application/json',
                'Authorization': 'Bearer ' + localStorage.getItem('token')
            },
            url: "http://localhost:8080/products/edit/" + currentSelectedID,
            data: JSON.stringify(product),
            success: function (data) {
               getAll();
               window.location.href = 'user-product-list.html';
            },
            error: function (err) {
                console.log(err) // lỗi
            }
        });
    })

getAllCategory();
getAllBrand();
    function getAllCategory() {
        $.ajax({
            type: "GET",
            headers: {
                'Accept': 'application/json',
                'Authorization': 'Bearer ' + localStorage.getItem('token')
            },
            url: "http://localhost:8080/category",
            success: function (data) {
                let str = '<option value="opt1">Loại giày</option>';
                let listCategory = data;
                for (let category of listCategory) {
                    if (curentSelectedChosenCategoryID == category.id) {
                        str += `<option value="${category.id}" selected>${category.name}</option>`
                    } else {
                        str += `<option value="${category.id}">${category.name}</option>`
                    }
                }
                $("#product-category").html(str);
                $("#product-categoryA").html(str);
            },
            error: function (err) {

                console.log(err) // lỗi
            }
        });
    }

    function getAllBrand() {
        $.ajax({
            type: "GET",
            headers: {
                'Accept': 'application/json',
                'Authorization': 'Bearer ' + localStorage.getItem('token')
            },
            url: "http://localhost:8080/brand",
            success: function (data) {
                let str = '<option value="opt1">Brand</option>';
                let listBrand = data;
                console.log(listBrand);
                for (let brand of listBrand) {
                    if (curentSelectedChosenBrandID == brand.id) {
                        str += `<option value="${brand.id}" selected>${brand.brandName}</option>`
                    } else {
                        str += `<option value="${brand.id}">${brand.brandName}</option>`
                    }
                }
                $("#product-brand").html(str);
                $("#product-brandA").html(str);
            },
            error: function (err) {

                console.log(err) // lỗi
            }
        });
    }



