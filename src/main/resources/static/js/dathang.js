let thongBao = document.getElementById("thong-bao");
let passxm = document.getElementById("passwordxm");
let sp1 = document.getElementById("sp1");
let sp2 = document.getElementById("sp2");
let sp3 = document.getElementById("sp3");
let sp4 = document.getElementById("sp4");

let changesp = bool => {
    if(bool){
        sp1.style.display = "block";
        sp2.style.display = "block";
        sp3.style.display = "block";
        sp4.style.display = "block";
    }
    else {
        sp1.style.display = "none";
        sp2.style.display = "none";
        sp3.style.display = "none";
        sp4.style.display = "none";
    }
}

let dathang = () => {
    changesp(true);
    const xhttp = new XMLHttpRequest();
    xhttp.onload = function() {
        let kq = this.responseText;
        if(kq==="true") {
            toast({
                title: "Đặt hàng thành công",
                message: "Hãy đợi QTV xác nhận đơn hàng nhé.",
                type: "success",
            });
            setTimeout(() => {
                location.href = location.protocol + '//' +location.host +"/kiemtra";
            },3000);
        }
        else if(kq==="passx") {
            toast({
                title: "Lỗi",
                message: "Sai mật khẩu.",
                type: "error",
            });
            changesp(false);
        }
        else {
            toast({
                title: "Lỗi",
                message: "Có lỗi xảy ra. Liên hệ QTV để biết thêm chi tiết.",
                type: "error",
            });
            changesp(false);
        }
    }
    xhttp.open("POST", location.protocol + '//' +location.host +"/dathang");
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhttp.send("keydh="+keydh+"&passxm="+passxm.value);
}