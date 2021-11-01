let buttonLeft = document.getElementById("next-button-left");
let buttonRight = document.getElementById("next-button-right");
let slides = document.getElementsByClassName("slide");
let isClick = false;
let mouseOutBanner = function () {
    buttonRight.style.visibility = "visible";
    buttonLeft.style.visibility = "visible";
}
let id = null;

function changeRight(n) {
    let pos = 100;
    clearInterval(id);
    id = setInterval(frame, 3);

    function frame() {
        if (pos === 0) {
            clearInterval(id);
        } else {
            pos-=2;
            slides[n].style.left = pos + "%";
        }
    }
}

let id1 = null;

function changeLeft(n) {
    let pos = -100;
    clearInterval(id1);
    id1 = setInterval(frame, 3);

    function frame() {
        if (pos === 0) {
            clearInterval(id1);
        } else {
            pos+=2;
            slides[n].style.left = pos + "%";
        }
    }
}


let slideIndex = 0;
let changeSlide = () => {
    if(isClick)
        return;
    for (let i = 0; i < slides.length; i++) {
        slides[i].style.display = "none";
        slides[i].style.zIndex = "-1";
    }

    slideIndex++;
    if (slideIndex > slides.length) {
        slideIndex = 1;
    }
    slides[slideIndex - 1].style.display = "flex";
    slides[slideIndex - 1].style.zIndex = "1";
    changeRight(slideIndex - 1);
    setTimeout(function () {
        slides[slideIndex - 1].style.transition = "transform 1s;";
    }, 250);
    setTimeout(changeSlide, 4000);
}

changeSlide();

buttonRight.onclick = () => {
    isClick = true;
    for (let i = 0; i < slides.length; i++) {
        slides[i].style.display = "none";
        slides[i].style.zIndex = "-1";
    }
    (slideIndex++);

    if (slideIndex > slides.length) {
        slideIndex = 1;
    }
    slides[slideIndex - 1].style.display = "flex";
    slides[slideIndex - 1].style.zIndex = "1";
    changeRight(slideIndex - 1);
    setTimeout(function () {
        slides[slideIndex - 1].style.transition = "transform 1s;";
    }, 250);
}

buttonLeft.onclick = () => {
    isClick = true;
    for (let i = 0; i < slides.length; i++) {
        slides[i].style.display = "none";
        slides[i].style.zIndex = "-1";
    }
    slideIndex--;
    if(slideIndex === 0)
        slideIndex = slides.length;
    slides[slideIndex - 1].style.display = "flex";
    slides[slideIndex - 1].style.zIndex = "1";
    changeLeft(slideIndex - 1);
    setTimeout(function () {
        slides[slideIndex - 1].style.transition = "transform 1s;";
    }, 250);
}