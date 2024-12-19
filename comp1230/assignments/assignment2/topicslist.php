<?php
require 'functions.php';
session_start();

if (!isset($_SESSION['username'])) {
    header('Location: login.php');
    exit;
}

$username = $_SESSION['username'];
$topics = file('topics.txt', FILE_IGNORE_NEW_LINES | FILE_SKIP_EMPTY_LINES);

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $topicID = $_POST['topicID'];
    $voteType = $_POST['voteType'];

    if (vote($username, $topicID, $voteType)) {
        echo "Vote recorded.";
    } else {
        echo "You have already voted on this topic.";
    }
}
?>

<!-- Display Topics and Voting Options -->
<?php foreach ($topics as $topic) : ?>
    <?php list($topicID, $creator, $title, $description) = explode('|', $topic); ?>
    <div>
        <h3><?php echo htmlspecialchars($title); ?></h3>
        <p><?php echo htmlspecialchars($description); ?></p>
        <form method="POST">
            <input type="hidden" name="topicID" value="<?php echo $topicID; ?>">
            <button type="submit" name="voteType" value="up">Upvote</button>
            <button type="submit" name="voteType" value="down">Downvote</button>
        </form>
    </div>
<?php endforeach; ?>
