<?php
require 'functions.php';

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $username = $_POST['username'];
    $password = $_POST['password'];

    if (registerUser($username, $password)) {
        header('Location: login.php');
        exit;
    } else {
        echo "Username already taken.";
    }
}
?>

<script>
    function validateRegistrationForm() {
        const username = document.getElementById('username').value;
        const password = document.getElementById('password').value;
        if (username === '' || password === '') {
            alert('Both fields are required.');
            return false;
        }
        return true;
    }
</script>

<form method="POST">
    Username: <input type="text" name="username" required>
    Password: <input type="password" name="password" required>
    <button type="submit">Register</button>
</form>
