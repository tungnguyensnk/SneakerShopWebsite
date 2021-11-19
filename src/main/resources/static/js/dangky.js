let user = document.getElementById("username");
let pass = document.getElementById("password");
let ten = document.getElementById("ten");
let sdt = document.getElementById("sdt");
let diachi = document.getElementById("diachi");
let register = () => {
    const xhttp = new XMLHttpRequest();
    xhttp.onload = function () {
        let kq = this.responseText;
        if (kq === "true") {
            toast({
                title: "Đăng ký thành công",
                message: "Bạn sẽ được chuyển tới trang đăng nhập.",
                type: "success",
            });

            setTimeout(() => {
                location.href = location.protocol + '//' + location.host + "/dangnhap";
            }, 2000);
        } else if (kq === "false") {
            toast({
                title: "Lỗi",
                message: "Tài khoản hoặc số điện thoại đã tồn tại.",
                type: "error",
            });
        } else if (kq === "0") {
            toast({
                title: "Lỗi",
                message: "Tài khoản và mật khẩu tổi thiểu 4 kí tự.",
                type: "error",
            });
        } else if (kq === "1") {
            toast({
                title: "Lỗi",
                message: "Tên chỉ chứa chữ cái và không được để trống.",
                type: "error",
            });
        } else if (kq === "2") {
            toast({
                title: "Lỗi",
                message: "Sai định dạng sô điện thoại.",
                type: "error",
            });
        } else {
            toast({
                title: "Lỗi",
                message: "Địa chỉ không được để trống.",
                type: "error",
            });
        }

    }
    xhttp.open("POST", location.protocol + '//' + location.host + "/dangky");
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhttp.send("user=" + user.value + "&pass=" + pass.value + "&ten=" + ten.value + "&sdt=" + sdt.value + "&diachi=" + diachi.value);
}

let formdk = document.getElementById("form-dang-ky");
formdk.onkeyup = ev => {
    if (ev.code === "Enter")
        register();
}