const $ = document.querySelector.bind(document);
const $$ = document.querySelectorAll.bind(document);

const tabs = $$(".tab-item");
const panes = $$(".tab-pane");

const tabActive = $(".tab-item.activ");
const line = $(".tabs .line");

line.style.left = tabActive.offsetLeft + "px";
line.style.width = tabActive.offsetWidth + "px";

tabs.forEach((tab, index) => {
    const pane = panes[index];

    tab.onclick = function () {
        $(".tab-item.activ").classList.remove("activ");
        $(".tab-pane.activ").classList.remove("activ");

        line.style.left = (this.offsetLeft-8) + "px";
        line.style.width = this.offsetWidth + "px";

        this.classList.add("activ");
        pane.classList.add("activ");
    };
});

let pold = document.getElementById("passwordold");
let pnew = document.getElementById("passwordnew");
let changePass = () => {
    const xhttp = new XMLHttpRequest();
    xhttp.onload = function() {
        let kq = this.responseText;
        if(kq==="true") {
            toast({
                title: "Thành công",
                message: "Mật khẩu đã được đổi.",
                type: "success",
            });
        }
        else
            toast({
                title: "Lỗi",
                message: "Sai mật khẩu hoặc trùng mật khẩu cũ.",
                type: "error",
            });

    }
    xhttp.open("POST", location.protocol + '//' +location.host +"/doipass");
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhttp.send("pass="+pold.value+"&new="+pnew.value);
}

let ten = document.getElementById("ten");
let sdt = document.getElementById("sdt");
let diachi = document.getElementById("diachi");
let changeInfo = () => {
    const xhttp = new XMLHttpRequest();
    xhttp.onload = function() {
        let kq = this.responseText;
        if(kq==="true") {
            toast({
                title: "Thành công",
                message: "Thông tin đã được đổi.",
                type: "success",
            });
        }
        else
            toast({
                title: "Lỗi",
                message: "Có lỗi xảy ra.",
                type: "error",
            });

    }
    xhttp.open("POST", location.protocol + '//' +location.host +"/doiinfo");
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhttp.send("ten="+ten.value+"&sdt="+sdt.value+"&diachi="+diachi.value);
}