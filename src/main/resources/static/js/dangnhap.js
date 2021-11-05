let user = document.getElementById("username");
let pass = document.getElementById("password");
let thongBao = document.getElementById("thong-bao");
let login = () => {
    const xhttp = new XMLHttpRequest();
    xhttp.onload = function() {
        let kq = this.responseText;
        if(kq==="true") {
            thongBao.innerHTML = "Đăng nhập thành công.";
            setTimeout(() => {
                location.href = location.protocol + '//' +location.host +"/";
            },1000);
        }
        else
            thongBao.innerHTML = "Sai tài khoản hoặc mật khẩu.";
    }
    xhttp.open("POST", location.protocol + '//' +location.host +"/dangnhap");
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhttp.send("user="+user.value+"&pass="+pass.value);
}