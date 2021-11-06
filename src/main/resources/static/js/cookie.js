const cart = new Map();
let carSl = document.getElementsByClassName("cart-sl").item(0);
const element = document.getElementsByClassName("cart-pop").item(0);
var total;
let addCart = masp => {
    masp = masp + "";
    if (cart.has(masp)) {
        cart.set(masp, cart.get(masp) + 1);
    } else cart.set(masp, 1);

    toast({
        title: "Thêm thành công!",
        message: listsp[masp].ten+" đã được cho vào giỏ.",
        type: "success",
    });

    const d = new Date();
    d.setTime(d.getTime() + (10 * 24 * 60 * 60 * 1000));
    let jsonkey = JSON.stringify(Object.fromEntries(cart));
    jsonkey = jsonkey.substring(1, jsonkey.length - 1);
    jsonkey = jsonkey.replaceAll(",", "a").replaceAll(":", "b").replaceAll("\"", "c");
    document.cookie = "z=" + jsonkey + ";expires=" + d.toUTCString() + "; path=/";
    getCookie();
}
let createCart = (ma, soLuong) => {
    const div = document.createElement("div");
    div.style.display = "flex";
    div.style.alignItems = "center";
    div.style.justifyContent = "space-between";
    div.style.flexDirection = "row";
    const ten = document.createElement("h5");
    const anh = document.createElement("img");
    let buttonX = document.createElement("button");
    anh.onclick = () => location.href = location.protocol + '//' +location.host +"/sanpham/"+ma;
    ten.onclick = () => location.href = location.protocol + '//' +location.host +"/sanpham/"+ma;
    buttonX.innerHTML = "Xóa";
    buttonX.onclick = () => {
        cart.delete(ma + "");
        const d = new Date();
        d.setTime(d.getTime() + (10 * 24 * 60 * 60 * 1000));
        let jsonkey = JSON.stringify(Object.fromEntries(cart));
        jsonkey = jsonkey.substring(1, jsonkey.length - 1);
        jsonkey = jsonkey.replaceAll(",", "a").replaceAll(":", "b").replaceAll("\"", "c");
        document.cookie = "z=" + jsonkey + ";expires=" + d.toUTCString() + "; path=/";
        document.cookie = "z=" + jsonkey + "; path=/";
        getCookie();
        if (cart.size === 0) {
            carSl.style.visibility = "hidden";
            cartPop.style.visibility = "hidden";
        }
    }
    buttonX.style.cursor = "pointer";
    buttonX.classList.add("link-description");
    buttonX.style.color = "#181a1b";
    buttonX.style.width = "15%";
    buttonX.style.padding = "10px";
    buttonX.style.marginRight = "7%";
    anh.src = listsp[ma].link;
    anh.style.maxWidth = "15%";
    anh.style.objectFit = "contain";
    anh.style.marginRight = "10px";
    anh.style.marginLeft = "7%";
    ten.appendChild(document.createTextNode(listsp[ma].ten + "   (x" + soLuong + ")"));
    div.appendChild(anh);
    div.appendChild(ten);
    div.appendChild(buttonX);
    element.appendChild(div);
}
let getCookie = () => {
    cart.clear();
    while (element.firstChild) {
        element.removeChild(element.lastChild);
    }
    let decodedCookie = decodeURIComponent(document.cookie);
    let ca = decodedCookie.split(';');
    for (let i = 0; i < ca.length; i++) {
        if (ca[i].includes("z=") && ca[i].length > 3) {
            ca[i] = ca[i].replaceAll("z=", "");
            let text = ca[i].split('a');
            for (let j = 0; j < text.length; j++) {
                let context = text[j].split('b');
                let ma = context[0].replaceAll("c", "").trim() + "";
                let soLuong = parseInt(context[1]);
                cart.set(ma, soLuong);
                createCart(ma, soLuong);
            }

            const div = document.createElement("div");
            const buttonDH = document.createElement("button");
            buttonDH.innerHTML = "Đặt hàng";
            buttonDH.classList.add("link-description");
            buttonDH.style.color = "#181a1b";
            buttonDH.style.marginBottom = "5px";
            buttonDH.style.cursor = "pointer";
            buttonDH.onclick = () => location.href = location.protocol + '//' + location.host + "/dathang";
            div.appendChild(buttonDH);
            element.appendChild(div);

        }
    }
    total = 0;
    cart.forEach(value => total += value);
    if (total !== 0) {
        carSl.innerHTML = total;
        carSl.style.visibility = "visible";
    }
}

getCookie();