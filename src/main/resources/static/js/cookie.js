const cart = new Map();
let carSl = document.getElementsByClassName("cart-sl").item(0);
const element = document.getElementsByClassName("cart-pop").item(0);
var total;
let addCart = masp => {
    masp = masp + "";
    if (cart.has(masp)) {
        cart.set(masp, cart.get(masp) + 1);
    } else cart.set(masp, 1);
    document.cookie = "z=" + JSON.stringify(Object.fromEntries(cart))+"; path=/";
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
    buttonX.innerHTML = "Xóa";
    buttonX.onclick = () => {
        console.log(cart.delete(ma+""));
        document.cookie = "z=" + JSON.stringify(Object.fromEntries(cart))+"; path=/";
        getCookie();
        if(cart.size===0){
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
    ten.appendChild(document.createTextNode(listsp[ma].ten+"   (x"+soLuong+")"));
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
        ca[i] = ca[i].replaceAll("z=","");
        if (ca[i].trim().startsWith("{") && !ca[i].trim().startsWith("{}")) {
            ca[i] = ca[i].trim().substring(1, ca[i].length - 2);
            let text = ca[i].split(',');
            for (let j = 0; j < text.length; j++) {
                let context = text[j].split(':');
                let ma = context[0].replaceAll("\"", "").trim() + "";
                let soLuong =  parseInt(context[1]);
                cart.set(ma,soLuong);
                createCart(ma,soLuong);
            }

            const div = document.createElement("div");
            const buttonX = document.createElement("button");
            buttonX.innerHTML = "Đặt hàng";
            buttonX.classList.add("link-description");
            buttonX.style.color = "#181a1b";
            buttonX.style.marginBottom = "5px";
            div.appendChild(buttonX);
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