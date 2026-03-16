if (!window._loadingButtonInit) {
    window._loadingButtonInit = true;

    document.addEventListener("DOMContentLoaded", function () {

        document.querySelectorAll("form").forEach(function (form) {
            form.addEventListener("submit", function () {
                const btn = form.querySelector(".btn-loading");
                if (!btn) return;

                btn.style.display = "none";

                const loading = document.createElement("div");
                loading.classList.add("login-loading");
                loading.innerHTML = '<span class="spinner"></span>';
                btn.parentElement.insertBefore(loading, btn);

                if (btn.classList.contains("btn-cancel")) {
                    btn.parentElement.querySelectorAll("button[type='button']").forEach(function (other) {
                        other.style.display = "none";
                    });
                }
            });
        });

        document.querySelectorAll(".sidebar-tabs-menu a").forEach(function (link) {
            link.addEventListener("click", function (e) {
                e.preventDefault();

                const pageContent = document.querySelector(".page-content");
                if (!pageContent) return;

                pageContent.style.display = "none";

                const loading = document.createElement("div");
                loading.classList.add("sidebar-loading");
                loading.innerHTML = '<span class="spinner"></span>';
                document.body.appendChild(loading);

                setTimeout(() => {
                    window.location.href = link.href;
                }, 100);
            });
        });

    });
}