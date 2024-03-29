let thongBao = document.getElementById("thong-bao");
let sp1 = document.getElementById("sp1");
let sp2 = document.getElementById("sp2");
let sp3 = document.getElementById("sp3");
let sp4 = document.getElementById("sp4");

let changesp = bool => {
    if (bool) {
        sp1.style.display = "block";
        sp2.style.display = "block";
        sp3.style.display = "block";
        sp4.style.display = "block";
    } else {
        sp1.style.display = "none";
        sp2.style.display = "none";
        sp3.style.display = "none";
        sp4.style.display = "none";
    }
}

let dathang = () => {
    changesp(true);
    const xhttp = new XMLHttpRequest();
    xhttp.onload = function () {
        let kq = this.responseText;
        if (kq === "false") {
            toast({
                title: "Lỗi",
                message: "Có lỗi xảy ra. Liên hệ QTV để biết thêm chi tiết.",
                type: "error",
            });
            changesp(false);
        } else {
            toast({
                title: "Đang tạo đơn...",
                message: "Bạn sẽ được chuyển tới trang thanh toán.",
                type: "success",
            });
            setTimeout(() => {
                location.href = location.protocol + '//' + location.host + "/thanhtoan?id="+kq;
            }, 3000);
        }
    }
    xhttp.open("POST", location.protocol + '//' + location.host + "/dathang");
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhttp.send();
}