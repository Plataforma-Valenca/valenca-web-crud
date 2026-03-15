function abrirModalNotas() {
    document.getElementById("modalNotas").style.display = "flex";
}
function fecharModalNotas() {
    document.getElementById("modalNotas").style.display = "none";
}
function selecionarDisciplina(select) {
    document.getElementById("notasIdDisciplina").value = select.value;
}
function abrirModalObs() {
    document.getElementById("modalObs").style.display = "flex";
}
function fecharModalObs() {
    document.getElementById("modalObs").style.display = "none";
}
window.onclick = function(event) {
    if (event.target.classList.contains("modal")) {
        event.target.style.display = "none";
    }
}