let thongBao = document.getElementById("thong-bao");
let passxm = document.getElementById("passwordxm");
let dathang = () => {
    const xhttp = new XMLHttpRequest();
    xhttp.onload = function() {
        let kq = this.responseText;
        if(kq==="true") {
            thongBao.innerHTML = "Đặt hàng thành công.";
            setTimeout(() => {
                location.href = location.protocol + '//' +location.host +"/kiemtra";
            },1000);
        }
        else if(kq==="passx")
            thongBao.innerHTML = "Sai mật khẩu.";
        else
            thongBao.innerHTML = "Có lỗi xảy ra.";
    }
    xhttp.open("POST", location.protocol + '//' +location.host +"/dathang");
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhttp.send("keydh="+keydh+"&passxm="+passxm.value);
}