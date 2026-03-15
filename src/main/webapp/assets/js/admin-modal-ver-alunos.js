function abrirModalCadastroAluno(){
    document.getElementById("modalCadastroAluno").style.display="flex";
}

function fecharModalCadastroAluno(){
    document.getElementById("modalCadastroAluno").style.display="none";
}

function abrirModalEditarAluno(id,nome,matricula){
    document.getElementById("editIdAluno").value = id;
    document.getElementById("editNome").value = nome;
    document.getElementById("editMatricula").value = matricula;
    document.getElementById("modalEditarAluno").style.display = "flex";
}

function fecharModalEditarAluno(){
    document.getElementById("modalEditarAluno").style.display = "none";
}

window.onclick = function(event){
    if(event.target.classList.contains('modal')){
        event.target.style.display = "none";
    }
}