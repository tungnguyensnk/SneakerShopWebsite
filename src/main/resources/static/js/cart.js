let cartIcon = document.getElementsByClassName("cart").item(0);
let cartPop = document.getElementsByClassName("cart-pop").item(0);
cartIcon.onmouseover = () => {
    if(total!==0){
        cartPop.style.visibility = "visible";
    }
}
cartIcon.onmouseleave = () => {
    if(!cartPop.matches(":hover"))
        cartPop.style.visibility = "hidden";
}
cartPop.onmouseleave = () => {
    if(!cartIcon.matches(":hover"))
        cartPop.style.visibility = "hidden";
}