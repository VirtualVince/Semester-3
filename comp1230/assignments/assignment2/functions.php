<?php

// User Registration Function
function registerUser($username, $password) {
    $file = 'users.txt';
    $users = file($file, FILE_IGNORE_NEW_LINES | FILE_SKIP_EMPTY_LINES);

    foreach ($users as $user) {
        list($existingUsername) = explode(':', $user);
        if ($existingUsername === $username) {
            return false; // Username already exists
        }
    }

    // Store new user
    $newUser = "$username:$password\n";
    file_put_contents($file, $newUser, FILE_APPEND);
    return true;
}

// User Authentication Function
function authenticateUser($username, $password) {
    $file = 'users.txt';
    $users = file($file, FILE_IGNORE_NEW_LINES | FILE_SKIP_EMPTY_LINES);

    foreach ($users as $user) {
        list($storedUsername, $storedPassword) = explode(':', $user);
        if ($storedUsername === $username && $storedPassword === $password) {
            return true; // Login successful
        }
    }
    return false; // Login failed
}

// Topic Creation Function
function createTopic($username, $title, $description) {
    $file = 'topics.txt';
    $topics = file($file, FILE_IGNORE_NEW_LINES | FILE_SKIP_EMPTY_LINES);
    $topicID = count($topics) + 1; // Unique ID

    $newTopic = "$topicID|$username|$title|$description\n";
    file_put_contents($file, $newTopic, FILE_APPEND);
    return true;
}

// Voting Function
function hasVoted($username, $topicID) {
    $file = 'votes.txt';
    $votes = file($file, FILE_IGNORE_NEW_LINES | FILE_SKIP_EMPTY_LINES);

    foreach ($votes as $vote) {
        list($voter, $votedTopicID) = explode('|', $vote);
        if ($voter === $username && $votedTopicID == $topicID) {
            return true; // User has already voted
        }
    }
    return false;
}

function vote($username, $topicID, $voteType) {
    if (hasVoted($username, $topicID)) {
        return false; // Already voted
    }

    $file = 'votes.txt';
    $newVote = "$username|$topicID|$voteType\n";
    file_put_contents($file, $newVote, FILE_APPEND);
    return true;
}

// User Voting History
function getUserVotingHistory($username) {
    $file = 'votes.txt';
    $votes = file($file, FILE_IGNORE_NEW_LINES | FILE_SKIP_EMPTY_LINES);
    $history = [];

    foreach ($votes as $vote) {
        list($voter, $topicID, $voteType) = explode('|', $vote);
        if ($voter === $username) {
            $history[] = ['topicID' => $topicID, 'voteType' => $voteType];
        }
    }
    return $history;
}

// Theme Setting Functions
function setTheme($theme) {
    setcookie("theme", $theme, time() + (86400 * 30), "/"); // 30 days
    return true;
}

function getTheme() {
    return $_COOKIE['theme'] ?? 'light';
}

?>
