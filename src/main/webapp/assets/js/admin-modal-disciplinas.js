function abrirModalCadastro(){
    document.getElementById("modalCadastro").style.display="flex";
}

function fecharModalCadastro(){
    document.getElementById("modalCadastro").style.display="none";
}

function abrirModalEditar(id,ano,nome){

    document.getElementById("modalEditar").style.display="flex";

    document.getElementById("editarId").value=id;
    document.getElementById("editarAno").value=ano;
    document.getElementById("editarNome").value=nome;

}

function fecharModalEditar(){
    document.getElementById("modalEditar").style.display="none";
}

function abrirModalExcluir(id) {
    document.getElementById("modalExcluir").style.display = "flex";
    document.getElementById("excluirDisciplinaId").value = id;
}

function fecharModalExcluir(){
    document.getElementById("modalExcluir").style.display="none";
}

window.onclick = function(event){

    if(event.target.classList.contains("modal")){
        event.target.style.display="none";
    }

}