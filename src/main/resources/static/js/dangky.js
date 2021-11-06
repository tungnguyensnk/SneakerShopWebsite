let user = document.getElementById("username");
let pass = document.getElementById("password");
let ten = document.getElementById("ten");
let sdt = document.getElementById("sdt");
let diachi = document.getElementById("diachi");
let register = () => {
    const xhttp = new XMLHttpRequest();
    xhttp.onload = function() {
        let kq = this.responseText;
        if(kq==="true") {
            toast({
                title: "Đăng ký thành công",
                message: "Bạn sẽ được chuyển tới trang đăng nhập.",
                type: "success",
            });

            setTimeout(() => {
                location.href = location.protocol + '//' +location.host +"/dangnhap";
            },2000);
        }
        else
            toast({
                title: "Lỗi",
                message: "Tài khoản hoặc số điện thoại đã tồn tại.",
                type: "error",
            });

    }
    xhttp.open("POST", location.protocol + '//' +location.host +"/dangky");
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhttp.send("user="+user.value+"&pass="+pass.value+"&ten="+ten.value+"&sdt="+sdt.value+"&diachi="+diachi.value);
}