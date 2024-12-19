<?php
require 'functions.php';
session_start();

if (!isset($_SESSION['username'])) {
    header('Location: login.php');
    exit;
}

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $username = $_SESSION['username'];
    $title = $_POST['title'];
    $description = $_POST['description'];

    if (createTopic($username, $title, $description)) {
        header('Location: topicslist.php');
        exit;
    } else {
        echo "Error creating topic.";
    }
}
?>

<!-- HTML Form for Topic Creation -->
<form method="POST">
    Title: <input type="text" name="title" required>
    Description: <textarea name="description" required></textarea>
    <button type="submit">Create Topic</button>
</form>
<a href="logout.php">Logout</a>
