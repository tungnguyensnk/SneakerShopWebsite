let huydon = ma => {
    const xhttp = new XMLHttpRequest();
    xhttp.onload = function () {
        let kq = this.responseText;
        if (kq === "true") {
            toast({
                title: "Hủy đơn thành công",
                message: " Đơn hàng số " + (ma+1) + " đã hủy.",
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