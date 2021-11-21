class gioHang {
    constructor(product_id, soluong, mausac, size) {
        this.product_id = product_id;
        this.soluong = soluong;
        this.mausac = mausac;
        this.size = size;
    }
}

let cartItems = [];

let carSl = document.getElementsByClassName("cart-sl").item(0);
var total;
let addCart = (masp, mau, size) => {
    let dem = 0;
    cartItems.forEach(value => {
        if (value.product_id === masp && value.mausac === mau && value.size === size) {
            value.soluong++;
            dem = 1;
        }
    })
    if (dem === 0)
        cartItems.push(new gioHang(masp, 1, mau, size));

    toast({
        title: "Thêm thành công!",
        message: listsp[masp].ten + " đã được cho vào giỏ.",
        type: "success",
    });

    const d = new Date();
    d.setTime(d.getTime() + (10 * 24 * 60 * 60 * 1000));
    document.cookie = "cart=" + btoa(JSON.stringify(cartItems)) + ";expires=" + d.toUTCString() + "; path=/";
    getCookie();
}

const cartItemsist = document.getElementById("cart-list");
let createCart = (ma, soLuong, mausac, size) => {
    const div = document.createElement("div");
    div.style.display = "flex";
    div.style.alignItems = "center";
    div.style.justifyContent = "space-between";
    div.style.flexDirection = "row";
    const ten = document.createElement("div");
    const anh = document.createElement("img");
    let buttonX = document.createElement("button");
    anh.onclick = () => location.href = location.protocol + '//' + location.host + "/sanpham/" + ma;
    anh.style.cursor = "pointer";
    ten.onclick = () => location.href = location.protocol + '//' + location.host + "/sanpham/" + ma;
    ten.style.cursor = "pointer";
    buttonX.innerHTML = "Xóa";
    buttonX.onclick = () => {
        cartItems = cartItems.filter(value => {
            if (value.product_id !== ma || value.mausac !== mausac || value.size !== size)
                return true;
        });
        const d = new Date();
        d.setTime(d.getTime() + (10 * 24 * 60 * 60 * 1000));
        document.cookie = "cart=" + btoa(JSON.stringify(cartItems)) + ";expires=" + d.toUTCString() + "; path=/";
        getCookie();
        if (cartItems.length === 0) {
            carSl.style.visibility = "hidden";
            cartPop.classList.remove("show");
            cartPop.classList.remove("hide");
        }
    }
    buttonX.style.cursor = "pointer";
    buttonX.classList.add("link-description");
    buttonX.style.color = "#181a1b";
    buttonX.style.width = "15%";
    buttonX.style.padding = "10px";
    buttonX.style.textAlign = "center";
    anh.src = listsp[ma].link;
    anh.style.maxWidth = "15%";
    anh.style.objectFit = "contain";
    anh.style.marginRight = "10px";
    anh.style.marginLeft = "7%";
    ten.innerHTML = "<h5 style='margin: 9px 0 3px 0'>" + listsp[ma].ten + "</h5>" +
        "<h5 style='margin: 3px 0 9px 0;text-align: center'><span style='color: " + mausac + "'>" + mausac + "</span> size: " + size + " sl: " + soLuong + "</h5>";
    div.appendChild(anh);
    div.appendChild(ten);
    div.appendChild(buttonX);
    cartItemsist.appendChild(div);
}
let getCookie = () => {
    cartItems.splice(0, cartItems.length);
    while (cartItemsist.firstChild) {
        cartItemsist.removeChild(cartItemsist.lastChild);
    }
    let decodedCookie = decodeURIComponent(document.cookie);
    let ca = decodedCookie.split(';');
    for (let i = 0; i < ca.length; i++) {
        if (ca[i].includes("cart=") && ca[i].length > 8) {
            cartItems = JSON.parse(atob(ca[i].replace("cart=", "")));
            cartItems.forEach(value => createCart(value.product_id, value.soluong, value.mausac, value.size));
        }
    }
    total = 0;
    cartItems.forEach(value => total += value.soluong);
    if (total !== 0) {
        carSl.innerHTML = total;
        carSl.style.visibility = "visible";
    } else {
        cartPop.classList.remove("showcart");
        cartPop.classList.add("hidecart");
    }
}

getCookie();

let i = 0, j = 0;
let scrollChat = () => {
    chatHistory.scrollTop = i;
    i += chatHistory.scrollHeight / 100;
    j++;
    const sc = setTimeout(scrollChat, 5);
    if (j === 90)
        clearTimeout(sc);
}
let chatArea = document.getElementById("chat-area");
let chatHistory = document.getElementById("chat-history");
let chatEnter = false;
let firstShow = false;
let showchat = () => {
    if (chatArea.classList.contains("show")) {
        chatArea.classList.remove("show");
        chatArea.classList.add("hide");
        chatHistory.style.visibility = "hidden";
        chatEnter = false;
    } else {
        chatEnter = true;
        chatArea.classList.add("show");
        chatHistory.style.visibility = "visible";
        if (chatArea.classList.contains("hide"))
            chatArea.classList.remove("hide");
    }
}

setInterval(() => {
    if (chatEnter) {
        const xhttp = new XMLHttpRequest();
        xhttp.onload = function () {
            let kq = this.responseText;
            while (chatHistory.firstChild) {
                chatHistory.removeChild(chatHistory.lastChild);
            }
            if (kq === "null") {
                const chat = document.createElement("h4");
                chat.innerHTML = "Trò chuyện cùng chúng tôi...";
                chat.classList.add("chat-line1");
                chatHistory.appendChild(chat);
            } else if (kq === "false") {
                const chat = document.createElement("h4");
                chat.innerHTML = "Đăng nhập để trò chuyện cùng chúng tôi...";
                chat.classList.add("chat-line1");
                chatHistory.appendChild(chat);
                noiDungChat.disabled = true;
            } else {
                let listText = kq.split("|z|");
                listText.forEach(value => {
                    if (value.startsWith("|a|")) {
                        const chat = document.createElement("h4");
                        chat.innerHTML = value.substring(3);
                        chat.classList.add("chat-line1");
                        chatHistory.appendChild(chat);
                    } else {
                        const chat = document.createElement("h4");
                        chat.innerHTML = value;
                        chat.classList.add("chat-line2");
                        chatHistory.appendChild(chat);
                    }
                })
                if (!firstShow) {
                    firstShow = true;
                    scrollChat();
                }
            }
        }
        xhttp.open("GET", location.protocol + '//' + location.host + "/gettn");
        xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xhttp.send();
    }
}, 1000);

let noiDungChat = document.getElementById("chat");
let sendchat = () => {
    const xhttp = new XMLHttpRequest();
    xhttp.onload = function () {
    }
    xhttp.open("POST", location.protocol + '//' + location.host + "/guitn");
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhttp.send("nd=" + noiDungChat.value);
    noiDungChat.value = "";
    setTimeout(() => chatHistory.scrollTop = chatHistory.scrollHeight, 1000);
}

noiDungChat.onkeyup = ev => {
    if (ev.code === "Enter") {
        sendchat();
    }
}