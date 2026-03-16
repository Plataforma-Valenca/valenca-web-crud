document.addEventListener("DOMContentLoaded", function () {
    document.querySelectorAll(".btn-loading").forEach(function (btn) {
        btn.closest("form").addEventListener("submit", function () {
            btn.style.display = "none";

            const loading = document.createElement("div");
            loading.classList.add("login-loading");
            loading.innerHTML = '<span class="spinner"></span>';
            btn.parentElement.insertBefore(loading, btn);
        });
    });
});