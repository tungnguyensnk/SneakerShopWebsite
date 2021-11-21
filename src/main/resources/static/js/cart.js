let cartIcon = document.getElementsByClassName("cart").item(0);
let cartfa = document.getElementById("cartic");
let cartPop = document.getElementsByClassName("cart-pop").item(0);
cartfa.onmouseover = () => {
    if (total !== 0) {
        cartPop.classList.remove("hidecart");
        cartPop.classList.add("showcart");
    }
}
cartIcon.onmouseleave = () => {
    if (!cartPop.matches(":hover")) {
        cartPop.classList.remove("showcart");
        if (total !== 0)
            cartPop.classList.add("hidecart");
    }
}
cartPop.onmouseleave = () => {
    if (!cartIcon.matches(":hover")) {
        cartPop.classList.remove("showcart");
        if (total !== 0)
            cartPop.classList.add("hidecart");
    }
}

// Toast function
const main = document.getElementById("toast");
var toast = function toast({title = "", message = "", type = "info", duration = 2000}) {
    if (main) {
        const toast = document.createElement("div");

        // Auto remove toast
        const autoRemoveId = setTimeout(function () {
            main.removeChild(toast);
        }, duration + 1000);

        // Remove toast when clicked
        toast.onclick = function (e) {
            if (e.target.closest(".toast__close")) {
                main.removeChild(toast);
                clearTimeout(autoRemoveId);
            }
        };

        const icons = {
            success: "\"fa\">&#xf00c;",
            info: "\"material-icons\">&#xe000;",
            warning: "\"fa\">&#xf071;",
            error: "\"fa\">&#xf071;"
        };
        const icon = icons[type];
        const delay = (duration / 1000).toFixed(2);

        toast.classList.add("toast", `toast--${type}`);
        toast.style.animation = `slideInLeft ease .3s, fadeOut linear 1s ${delay}s forwards`;

        toast.innerHTML = `
                    <div class="toast__icon">
                        <i class=${icon}</i>
                    </div>
                    <div class="toast__body">
                        <h3 class="toast__title">${title}</h3>
                        <p class="toast__msg">${message}</p>
                    </div>
                    <div class="toast__close">
                        <i style="font-size:24px" class="fa">&#xf00d;</i>
                    </div>
                `;
        main.appendChild(toast);
    }
}

let sizeForm = document.getElementsByClassName("sizeform").item(0);
let idspCurrent = -1;

let showSizeForm = (id, top, left) => {
    sizeForm.style.top = (top - 60) + "px";
    sizeForm.style.left = (left + 130) + "px";
    if (idspCurrent !== id) {
        sizeForm.classList.remove("showform");
        sizeForm.classList.remove("hideform");
        setTimeout(() => sizeForm.classList.add("showform"), 50);
    }
    idspCurrent = id;
}
let listButton = document.getElementsByClassName("button-in-list");
window.addEventListener('click', function (e) {
    for (let i = 0; i < listButton.length; i++) {
        if (listButton.item(i).contains(e.target))
            return;
    }
    if (!sizeForm.contains(e.target) && sizeForm.classList.contains("showform")) {
        sizeForm.classList.remove("showform");
        sizeForm.classList.add("hideform");
        idspCurrent = -1;
    }
});

let sizeSelect = document.getElementById('size');
sizeSelect.addEventListener("keyup", () => {
    setTimeout(() => {
        let v = sizeSelect.value;
        if (v < 20) sizeSelect.value = 20;
        if (v > 50) sizeSelect.value = 50;
    }, 3000);
})

let checkAddCart = () => {
    let v = sizeSelect.value;
    if (v < 20) sizeSelect.value = 20;
    if (v > 50) sizeSelect.value = 50;
    addCart(idspCurrent, document.querySelector('input[name="color"]:checked').getAttribute('id'),
        parseInt(sizeSelect.value));
}

let checkAddCartInTTSP = (idsp) => {
    let v = sizeSelect.value;
    if (v < 20) sizeSelect.value = 20;
    if (v > 50) sizeSelect.value = 50;
    addCart(idsp, document.querySelector('input[name="color"]:checked').getAttribute('id'),
        parseInt(sizeSelect.value));
}