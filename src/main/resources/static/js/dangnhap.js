let user = document.getElementById("username");
let pass = document.getElementById("password");

let login = () => {
    const xhttp = new XMLHttpRequest();
    xhttp.onload = function() {
        let kq = this.responseText;
        if(kq==="true") {
            toast({
                title: "Đăng nhập thành công",
                message: user.value+" login.",
                type: "success",
            });

            setTimeout(() => {
                location.href = location.protocol + '//' +location.host +"/";
            },2000);
        }
        else
            toast({
                title: "Lỗi",
                message: "Sai tài khoản hoặc mật khẩu.",
                type: "error",
            });
    }
    xhttp.open("POST", location.protocol + '//' +location.host +"/dangnhap");
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhttp.send("user="+user.value+"&pass="+pass.value);
}