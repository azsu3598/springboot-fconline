const submitbtn = document.getElementById("submit");

if(submitbtn){
    submitbtn.addEventListener("click", (event) => {
        let nickname = document.getElementById("nickname").value;
        function success(){
            // console.log("성공합니다.");
            // alert("성공합니다.");
            location.replace("/test?nickname"+nickname)
        }
        function fail(){
            alert("닉네임이 잘못돼었습니다.");
        }

        httpRequest("GET", "/test?nickname="+nickname, null,"success", "fail");
    })
}
function httpRequest(method, url, body, success ,fail){
    fetch(url, {
        method: method,
    }).then((response) => {
        if(response.status == 200 || response.status == 201){
            return success();
        }
        else{
            return fail();
        }
    })
}