function setTheme(theme) {
    document.cookie = "theme=" + theme + "; path=/";
    document.body.className = theme + "-theme";
}

function applySavedTheme() {
    const theme = getCookie("theme") || "light";
    document.body.className = theme + "-theme";
}

function getCookie(name) {
    const value = `; ${document.cookie}`;
    const parts = value.split(`; ${name}=`);
    if (parts.length === 2) return parts.pop().split(';').shift();
}

document.addEventListener("DOMContentLoaded", applySavedTheme);
