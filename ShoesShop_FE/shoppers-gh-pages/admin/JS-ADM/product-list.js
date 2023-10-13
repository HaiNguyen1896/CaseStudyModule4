getAll()

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
            if(arr[i].quantity>0) {
                arr[i].status = 'Còn hàng';
            } else {
                arr[i].status = 'Hết hàng';
            }
            str += `
                        <tr>
                            <td><img src="${'../ImgProduct/'+arr[i].image}" alt="" /></td>
                            <td>${arr[i].productName}</td>
                            <td>${arr[i].quantity}</td>
                            <td>${arr[i].status}</td>
                            <td>${arr[i].price}</td>
                            <td>${arr[i].salePercentage}%</td>
                            <td>${arr[i].price-(arr[i].price * arr[i].salePercentage)}</td>
                            <td>${arr[i].color}</td>
                            <td>${arr[i].category.name}</td>
                            <td>${arr[i].brand.brandName}</td>
                            <td style="font-family: inherit ; white-space: nowrap ; overflow-x: hidden; position: relative; display: block ; overflow-y: hidden ; text-overflow: ellipsis;width: 100px">${arr[i].describle}</td>
                            <td>
                              <a href="product-edit.html?id=${arr[i].id}"><button data-toggle="tooltip" title="Edit" class="pd-setting-ed"><i class="fa fa-pencil-square-o" aria-hidden="true"></i></button></a>
                              <button data-toggle="tooltip" title="Trash" class="pd-setting-ed" onclick="deleteProduct(${arr[i].id})"><i class="fa fa-trash-o" aria-hidden="true"></i></button>
                            </td>
                        </tr>`;
            $("#product-tbody").html(str);
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

function findSizeByProductID(id) {
    $.ajax({
        type: "GET",
        headers: {
            'Accept': 'application/json',
            'Authorization': 'Bearer ' + localStorage.getItem('token')
        },
        url: "http://localhost:8080/sizes/findByProductID/" + id,
        success: function (data) {
            let str = '';
            let sizeList = data;
            for (let i = 0; i < sizeList.length; i++) {
                str += `${sizeList[i].size}` + " ";
            }
            return str;
        },
        error: function (err) {
            console.log(err) // lỗi
        }
    });
}

function deleteProduct(id) {
    $.ajax({
        type: "GET",
        headers: {
            'Accept': 'application/json',
            'Authorization': 'Bearer ' + localStorage.getItem('token')
        },
        url: "http://localhost:8080/products/delete/" + id,
        success: function (data) {
            getAll();
        },
        error: function (err) {
            console.log(err) // lỗi
        }
    });
}