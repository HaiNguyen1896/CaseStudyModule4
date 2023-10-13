let cartProduct = localStorage.getItem("cart") == null ? [] : JSON.parse(localStorage.getItem("cart"));
$(".count").html(cartProduct.length)
$(document).ready(function () {
    let str = '';
    let sum = '';
    let countTotal = 0;
    for (let i = 0; i < cartProduct.length; i++) {
        str += `  <tr>
                    <td class="product-thumbnail">
                      <img src="ImgProduct/${cartProduct[i].image}" alt="Image" class="img-fluid">
                    </td>
                    <td class="product-name">
                      <h2 class="h5 text-black">${cartProduct[i].productName}</h2>
                    </td>
                    <td class="product-size">
                      <h2 class="h5 text-black">${cartProduct[i].size[0]['size']}</h2>
                    </td>
                    <td>${cartProduct[i].price}</td>
                    <td>${cartProduct[i].quantity}</td>
                    <td id="totalPrice">${cartProduct[i].quantity * cartProduct[i].price}</td>
                    <td><a href="#" class="btn btn-primary btn-sm btn-remove-product" id="removeCurrentProduct">X</a></td>
                  </tr>`
        sum += `
                        <div class="col-md-6" id="subtotal">
                             <span class="text-black" >Đơn hàng ${i + 1}</span>
                        </div>
                        <div class="col-md-6 text-right" >
                            <strong class="text-black">$${cartProduct[i].quantity * cartProduct[i].price}</strong><br>
                        </div>`
        countTotal += cartProduct[i].quantity * cartProduct[i].price;
    }
    $("#subtotal").html(sum);
    $("#tbody").html(str);
    $("#total").html('$' + countTotal);
});

$("#tbody").on('click', '.btn-remove-product', function () {
    var index = $(this).closest('tr').index();

    // Xóa sản phẩm khỏi mảng cartProduct
    cartProduct.splice(index, 1);

    // Lưu lại mảng cartProduct đã cập nhật vào localStorage
    localStorage.setItem("cart", JSON.stringify(cartProduct));
    location.reload();
});



