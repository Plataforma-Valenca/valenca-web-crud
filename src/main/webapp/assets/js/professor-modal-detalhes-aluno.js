function abrirModalNotas(idAluno){

    document.getElementById("modalNotas").style.display = "flex";
    document.getElementById("idAlunoNotas").value = idAluno;

}

function fecharModalNotas(){

    document.getElementById("modalNotas").style.display = "none";

}


function abrirModalObservacao(idAluno, idProfessor){

    document.getElementById("modalObservacao").style.display = "flex";

    document.getElementById("obsIdAluno").value = idAluno;
    document.getElementById("obsIdProfessor").value = idProfessor;

}

function fecharModalObservacao(){

    document.getElementById("modalObservacao").style.display = "none";

}


window.onclick = function(event){

    if(event.target.className === "modal"){

        event.target.style.display = "none";

    }

}
