let admInfo = JSON.parse(localStorage.getItem("currentAccount"));
$("#admin-name").html(admInfo.firstName+' '+admInfo.lastName);
$(document).ready(function () {
    getAll();

    function getAll() {
        $.ajax({
            type: "GET",
            headers: {
                'Accept': 'application/json',
                'Authorization': 'Bearer ' + localStorage.getItem('token')
            },
            url: "http://localhost:8080/customerInfo",
            success: function (data) {
                getUserOderInfo(data);
                console.log(data)
            },
            error: function (err) {
                console.log(err) // lỗi
            }
        });
    }

    function getUserOderInfo(arr) {
        let str = ` <tr>
                                    <th>STT</th>
                                    <th>Họ và tên</th>
                                    <th>Địa chỉ</th>
                                    <th>Quê quán</th>
                                    <th>Email</th>
                                    <th>Số điện thoại</th>
                                    <th>Ghi chú</th>
                                    <th>Tên sản phẩm</th>
                                    <th>Size</th>
                                    <th>Ngày đặt hàng</th>                   
                                    <th>Tình trạng</th>
                                </tr>`;
        for (let i = 0; i < arr.length; i++) {
            findOderDetail(arr[i].oder.id, function (oderDetail) {
                for (let j = 0; j < oderDetail.length; j++) {
              str += `
              <tr>
                  <td>${arr[i].id}</td>
                  <td>${arr[i].fullname}</td>
                  <td>${arr[i].address}</td>
                  <td>${arr[i].country}</td>
                  <td>${arr[i].email}</td>
                  <td>${arr[i].phoneNumber}</td>
                  <td>${arr[i].orderNotes}</td>         
                  <td>${oderDetail[j].product.productName}</td>
                  <td>${oderDetail[j].size}</td>
                  <td>${formatDate(oderDetail[j].oder.date)}</td>
                  <td> <button class="pd-setting">Đã thanh toán</button></td>
              </tr>`;

                }
                $("#body-table").html(str);
            });
        }
    }

    function formatDate(dateTimeString) {
        const dateTime = new Date(dateTimeString);
        const dateOptions = { day: 'numeric', month: 'numeric', year: 'numeric' };
        const timeOptions = { hour: 'numeric', minute: 'numeric', second: 'numeric' };

        const datePart = dateTime.toLocaleDateString(undefined, dateOptions);
        const timePart = dateTime.toLocaleTimeString(undefined, timeOptions);

        return `${datePart} ${timePart}`;
    }


    function findOderDetail(oderID, callback) {
        $.ajax({
            type: "GET",
            headers: {
                'Accept': 'application/json',
                'Authorization': 'Bearer ' + localStorage.getItem('token')
            },
            url: "http://localhost:8080/oderDetail/getOder/" + oderID,
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