let user = document.getElementById("username");
let pass = document.getElementById("password");
let ten = document.getElementById("ten");
let sdt = document.getElementById("sdt");
let diachi = document.getElementById("diachi");
let thongBao = document.getElementById("thong-bao");
let register = () => {
    const xhttp = new XMLHttpRequest();
    xhttp.onload = function() {
        let kq = this.responseText;
        if(kq==="true") {
            thongBao.innerHTML = "Đăng ký thành công.";
            setTimeout(() => {
                location.href = location.protocol + '//' +location.host +"/dangnhap";
            },1000);
        }
        else
            thongBao.innerHTML = "Tài khoản hoặc số điện thoại đã tồn tại.";
    }
    xhttp.open("POST", location.protocol + '//' +location.host +"/dangky");
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhttp.send("user="+user.value+"&pass="+pass.value+"&ten="+ten.value+"&sdt="+sdt.value+"&diachi="+diachi.value);
}