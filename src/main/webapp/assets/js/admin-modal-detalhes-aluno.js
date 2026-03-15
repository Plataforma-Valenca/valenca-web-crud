function abrirModalObs(){

    document.getElementById("modalObs").style.display="flex";

}

function fecharModalObs(){

    document.getElementById("modalObs").style.display="none";

}

window.onclick = function(event){

    let modal = document.getElementById("modalObs");

    if(event.target === modal){

        modal.style.display="none";

    }

}