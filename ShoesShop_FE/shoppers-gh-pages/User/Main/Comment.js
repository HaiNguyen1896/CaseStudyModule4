let currentLoginAccount = JSON.parse(localStorage.getItem("currentAccount"));
var params = new window.URLSearchParams(window.location.search);
var idP = params.get('id');

$("#user-nameComment").html(currentLoginAccount.firstName+' '+currentLoginAccount.lastName);

$("#commentBtn").on('click',function (){
    receiveComment();
});

getAllComment();
function getAllComment() {
    $.ajax({
        type: "GET",
        headers: {
            'Accept': 'application/json',
            'Authorization': 'Bearer ' + localStorage.getItem('token')
        },
        url: "http://localhost:8080/comment/getCommentByProductID/"+idP,
        success: function (data) {
            getCommentData(data);
        },
        error: function (err) {
            console.log(err) // lỗi
        }
    });
}

function getCommentData(arr) {
    let str = ``;
    for (let i = 0; i < arr.length; i++) {
                str += `
                    <div class="d-flex flex-start">
                      <img class="rounded-circle shadow-1-strong me-3"
                           src="https://img.lovepik.com/free-png/20220124/lovepik-avatar-png-image_401708318_wh1200.png" alt="avatar" height="30" width="30"
                           height="65" />
                      <div class="flex-grow-1 flex-shrink-1" >
                          <div>
                              <div class="d-flex justify-content-between align-items-center">
                                <p class="mb-1" id="user-nameComment">${currentLoginAccount.firstName+' '+currentLoginAccount.lastName}</p>
                                <p class="small">${arr[i].commentTime}</p>
                                <a href="#!"><i class="fas fa-reply fa-xs"></i><span class="small"> Phản hồi</span></a>
                              </div>
                              <p class="small mb-0" id="user-comment">
                                ${arr[i].comment}
                              </p>
                                </div>
                        </div>
                        </div>`;
                $("#comment-body").html(str);
            };


}

function receiveComment() {
        let comment = $("#input-comment").val();
    $.ajax({
        type: "POST",
        headers: {
            'Authorization': 'Bearer ' + localStorage.getItem('token')
        },
        url: "http://localhost:8080/comment/add/" + comment + '/'+ idP,
        success: function (data) {
            getAllComment();
        },
        error: function (err) {
            console.log(err) // lỗi
        }
    });
}
