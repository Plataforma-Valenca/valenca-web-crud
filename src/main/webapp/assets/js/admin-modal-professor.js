function abrirModalCadastro() {
    document.getElementById("modalCadastro").style.display = "flex";
}

function fecharModalCadastro() {
    document.getElementById("modalCadastro").style.display = "none";
}

function abrirModalEditar(idProfessor,idUsuario,idDisciplina,nome,email,cpf,disciplina){

    document.getElementById("modalEditar").style.display="flex";

    document.getElementById("editarIdProfessor").value=idProfessor;
    document.getElementById("editarIdUsuario").value=idUsuario;
    document.getElementById("editarIdDisciplina").value=idDisciplina;
    document.getElementById("editarNome").value=nome;
    document.getElementById("editarEmail").value=email;
    document.getElementById("editarCpf").value=cpf;
    document.getElementById("editarDisciplina").value=disciplina;

}

function fecharModalEditar(){
    document.getElementById("modalEditar").style.display="none";
}


function abrirModalExcluir(id) {
    document.getElementById("modalExcluir").style.display = "flex";
    document.getElementById("excluirProfessorId").value = id;
}

function fecharModalExcluir() {
    document.getElementById("modalExcluir").style.display = "none";
}

window.onload = function(){

    const popup = document.getElementById("popupSucesso");

    if(popup){
        setTimeout(function(){
            popup.style.opacity = "0";

            setTimeout(function(){
            popup.style.display = "none";
            }, 500);

        }, 4000);
    }

}

window.onclick = function(event) {
    if (event.target.className === 'modal') {
        event.target.style.display = "none";
    }
}