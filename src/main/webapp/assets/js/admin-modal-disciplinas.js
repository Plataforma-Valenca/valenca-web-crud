// INSERT
function abrirModalCadastro(){
    document.getElementById("modalCadastro").style.display="flex";
}
function fecharModalCadastro(){
    document.getElementById("modalCadastro").style.display="none";
}


//UPDATE
function abrirModalEditar(id, nome, idProfessor, professor) {
    document.getElementById("modalEditar").style.display = "flex";
    document.getElementById("idDisciplina").value = id;
    document.getElementById("editarNomeDisciplina").value = nome;
    document.getElementById("editarIdProfessor").value = idProfessor;
    document.getElementById("editarNomeProfessor").value = professor;
}
function fecharModalEditar(){
    document.getElementById("modalEditar").style.display="none";
}


//DELETE
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