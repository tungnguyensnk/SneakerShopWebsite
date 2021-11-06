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
