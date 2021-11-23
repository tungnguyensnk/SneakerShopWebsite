let huydon = ma => {
    const xhttp = new XMLHttpRequest();
    xhttp.onload = function () {
        let kq = this.responseText;
        if (kq === "true") {
            toast({
                title: "Hủy đơn thành công",
                message: " Đơn hàng số " + ma + " đã hủy.",
                type: "success",
            });

            setTimeout(() => {
                location.href = location.protocol + '//' + location.host + "/kiemtra";
            }, 1000);
        }
    }
    xhttp.open("POST", location.protocol + '//' + location.host + "/huydon");
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhttp.send("id=" + ma);
}

let thanhtoan = id => {
    toast({
        title: "Đang chuyển hướng",
        message: "Chuyển hướng thanh toán đơn hàng " + id + ".",
        type: "success",
    });

    setTimeout(() => {
        location.href = location.protocol + '//' + location.host + "/thanhtoan?id=" + id;
    }, 1000);

}


let fac = document.getElementsByClassName("fac").item(0).classList;
fac.remove("fa");
fac.add("fab");


let trahang = orderId => {
    const xhttp = new XMLHttpRequest();
    xhttp.onload = function () {
        let kq = this.responseText;
        if (kq === "true") {
            toast({
                title: "Đã tạo yêu cầu hoàn tiền",
                message: "Hãy đợi chủ cửa hàng liên hệ lại cho bạn.",
                type: "success",
            });
            document.getElementById("t" + orderId).remove();
        } else
            toast({
                title: "Lỗi",
                message: "Có lỗi xảy ra.",
                type: "error",
            });
    }
    xhttp.open("POST", location.protocol + '//' + location.host + "/trahang");
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhttp.send('id=' + orderId);
}

let review = orderId => {
    document.getElementById("r" + orderId).remove();
    let listReview = document.querySelectorAll('#d' + orderId + ' div.review-area');
    listReview.forEach(value => {
        value.classList.add("review-show");
    })
}

let checkSao = (hangId, saoId) => {
    let sao = [];
    sao[0] = document.querySelector("#h" + hangId + " > div:nth-child(1) > i:nth-child(1)");
    sao[1] = document.querySelector("#h" + hangId + " > div:nth-child(1) > i:nth-child(2)");
    sao[2] = document.querySelector("#h" + hangId + " > div:nth-child(1) > i:nth-child(3)");
    sao[3] = document.querySelector("#h" + hangId + " > div:nth-child(1) > i:nth-child(4)");
    sao[4] = document.querySelector("#h" + hangId + " > div:nth-child(1) > i:nth-child(5)");

    let taiCho = false;
    if (sao[saoId - 1].classList.contains("fas") && (saoId === 5 || sao[saoId].classList.contains("far")))
        taiCho = true;

    for (let i = 0; i < 5; i++) {
        sao[i].classList.remove("fas");
        sao[i].classList.add("far");
    }

    sao[0].setAttribute("sao", 0);
    if (taiCho)
        return;

    for (let i = 0; i < saoId; i++) {
        sao[i].classList.remove("far");
        sao[i].classList.add("fas");
        sao[i].classList.add("star-click");
    }
    sao[0].setAttribute("sao", saoId);
    setTimeout(() => {
        for (let i = 0; i < saoId; i++) {
            sao[i].classList.remove("star-click");
        }
    }, 300);
}

let sendReview = (ctdId, productId) => {
    let sao = document.querySelector("#h" + ctdId + " > div:nth-child(1) > i:nth-child(1)").getAttribute("sao");
    let noiDung = document.querySelector("#h" + ctdId + " > label > textarea").value;

    const xhttp = new XMLHttpRequest();
    xhttp.onload = function () {
        let kq = this.responseText;
        if (kq === "true") {
            toast({
                title: "Đã gửi Review",
                message: "Đã thêm nhận xét cho sản phẩm " + listsp[productId].ten,
                type: "success",
            });

            let reviewArea = document.querySelector("#h" + ctdId).classList;
            reviewArea.remove("review-show");
            reviewArea.add("review-hide");
        } else
            toast({
                title: "Lỗi",
                message: "Vui lòng thử lại sau.",
                type: "error",
            });
    }
    xhttp.open("POST", location.protocol + '//' + location.host + "/guireview");
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhttp.send("id=" + productId + "&sao=" + sao + "&nd=" + noiDung + "&ctdid=" + ctdId);

}