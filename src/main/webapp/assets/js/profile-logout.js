function togglePopup() {
    const popup = document.getElementById("popupMenu");
    popup.style.display = (popup.style.display === "block") ? "none" : "block";
}

window.onclick = function(event) {
    if (!event.target.closest('.header-profile')) {
        const popup = document.getElementById("popupMenu");
        if(popup) popup.style.display = "none";
    }
}