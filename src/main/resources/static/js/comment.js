let listStar = document.getElementsByClassName("rating-star");
let fac = document.getElementsByClassName("fac").item(0).classList;
fac.remove("fa");
fac.add("fab");
sao = parseFloat(sao + "");
for (let i = 0; i < Math.floor(sao); i++) {
    listStar.item(i).classList.remove("far");
    listStar.item(i).classList.add("fas");
}
if ((sao - Math.floor(sao)) === 0.5) {
    listStar.item(Math.floor(sao)).classList.remove("far");
    listStar.item(Math.floor(sao)).classList.add("fas");
    listStar.item(Math.floor(sao)).classList.remove("fa-star");
    listStar.item(Math.floor(sao)).classList.add("fa-star-half-alt");
}
