<?php
require 'functions.php';
session_start();

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $username = $_POST['username'];
    $password = $_POST['password'];

    if (authenticateUser($username, $password)) {
        $_SESSION['username'] = $username;
        header('Location: dashboard.php');
        exit;
    } else {
        echo "Invalid credentials.";
    }
}
?>

<!-- HTML Form for Login -->
<form method="POST">
    Username: <input type="text" name="username" required>
    Password: <input type="password" name="password" required>
    <button type="submit">Login</button>
</form>
