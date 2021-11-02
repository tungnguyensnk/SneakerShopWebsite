const cart = new Map();
let carSl = document.getElementsByClassName("cart-sl").item(0);
let total;
let addCart = masp => {
    masp = masp + "";
    if (cart.has(masp)) {
        cart.set(masp, cart.get(masp) + 1);
    } else cart.set(masp, 1);
    document.cookie = getCookie();
}
let getCookie = () => {
    let decodedCookie = decodeURIComponent(document.cookie);
    let ca = decodedCookie.split(';');
    for (let i = 0; i < ca.length; i++) {
        let tmp = ca[i].split("=");
        if(!isNaN(parseInt(tmp[0])))
            cart.set(parseInt(tmp[0]),parseInt(tmp[1]));
    }
    total = 0;
    let text = "";
    cart.forEach((value, key) => {
        total += value;
        text += key + "=" + value + ";";
    });
    console.log(text)
    document.cookie = text;
    if (total !== 0) {
        carSl.innerHTML = total;
        carSl.style.visibility = "visible";
    }
    return text;
}
getCookie();