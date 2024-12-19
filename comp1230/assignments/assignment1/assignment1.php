<?php
session_start(); // Start the session to manage data between page reloads

// Define the quiz array
$quiz_Array = [
    'Programming' => [
        [
            'question' => 'What are Maziars favourite programming languages?',
            'answers' => ['PHP', 'HTML', 'JavaScript', 'CSS'],
            'correct' => [0, 2]
        ],
        [
            'question' => 'What is PHP used for?',
            'answers' => ['Backend development', 'Game development', 'Data analysis', 'Embedded systems'],
            'correct' => [0]
        ]
    ],
    'Video Games' => [
        [
            'question' => 'Which of the following are video game genres?',
            'answers' => ['RPG', 'Blockchain', 'FPS', 'AI'],
            'correct' => [0, 2]
        ],
        [
            'question' => 'Which game engine is used for making games?',
            'answers' => ['Unity', 'TensorFlow', 'Unreal Engine', 'React'],
            'correct' => [0, 2]
        ]
    ],
    'Geopolitics' => [
        [
            'question' => 'Which countries are in the European Union?',
            'answers' => ['Germany', 'Canada', 'France', 'China'],
            'correct' => [0, 2]
        ],
        [
            'question' => 'Which of these cities is a capital?',
            'answers' => ['Berlin', 'Toronto', 'Paris', 'Sydney'],
            'correct' => [0, 2]
        ]
    ]
];

// Handle category selection form submission
if ($_SERVER['REQUEST_METHOD'] == 'POST' && isset($_POST['category'])) {
    $selectedCategory = $_POST['category'];
    $_SESSION['selectedCategory'] = $selectedCategory;
    $randomQuestionIndex = array_rand($quiz_Array[$selectedCategory]);
    $_SESSION['currentQuestion'] = $quiz_Array[$selectedCategory][$randomQuestionIndex];
}

// Process the user's answers when they are submitted
if ($_SERVER['REQUEST_METHOD'] == 'POST' && isset($_POST['submitAnswer'])) {
    $userAnswers = isset($_POST['answers']) ? $_POST['answers'] : [];
    $_SESSION['userAnswers'] = $userAnswers;
    $_SESSION['answerSubmitted'] = true;
}

?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quiz Application</title>
    <style>
        .correct { color: green; }
        .incorrect { color: red; }
    </style>
    <script>
        // JavaScript validation to ensure a category is selected
        function validateCategorySelection() {
            const category = document.querySelector('input[name="category"]:checked');
            if (!category) {
                alert("Please select a quiz category!");
                return false;
            }
            return true;
        }

        // JavaScript validation to ensure an answer is selected
        function validateAnswerSelection() {
            const answers = document.querySelectorAll('input[name="answers[]"]:checked');
            if (answers.length === 0) {
                alert("Please select at least one answer!");
                return false;
            }
            return true;
        }
    </script>
</head>
<body>

<h2>Select a Quiz Category</h2>
<form method="POST" onsubmit="return validateCategorySelection();">
    <label><input type="radio" name="category" value="Programming"> Programming</label><br>
    <label><input type="radio" name="category" value="Video Games"> Video Games</label><br>
    <label><input type="radio" name="category" value="Geopolitics"> Geopolitics</label><br>
    <button type="submit">Submit</button>
</form>

<?php if (isset($_SESSION['currentQuestion']) && !isset($_SESSION['answerSubmitted'])): ?>
    <h2>Quiz Question</h2>
    <p><?php echo $_SESSION['currentQuestion']['question']; ?></p>
    <form method="POST" onsubmit="return validateAnswerSelection();">
        <?php foreach ($_SESSION['currentQuestion']['answers'] as $index => $answer): ?>
            <label>
                <input type="checkbox" name="answers[]" value="<?php echo $index; ?>"> <?php echo $answer; ?>
            </label><br>
        <?php endforeach; ?>
        <button type="submit" name="submitAnswer">Submit Answer</button>
    </form>
<?php endif; ?>

<?php if (isset($_SESSION['answerSubmitted'])): ?>
    <h2>Results</h2>
    <p><?php echo $_SESSION['currentQuestion']['question']; ?></p>
    <?php foreach ($_SESSION['currentQuestion']['answers'] as $index => $answer): ?>
        <p class="<?php echo in_array($index, $_SESSION['currentQuestion']['correct']) ? 'correct' : (in_array($index, $_SESSION['userAnswers']) ? 'incorrect' : ''); ?>">
            <?php echo $answer; ?>
        </p>
    <?php endforeach; ?>

    <?php if (array_diff($_SESSION['currentQuestion']['correct'], $_SESSION['userAnswers']) === [] && count($_SESSION['currentQuestion']['correct']) === count($_SESSION['userAnswers'])): ?>
        <p class="correct">Congratulations! All your answers are correct!</p>
    <?php else: ?>
        <p class="incorrect">Some of your answers were incorrect. Please try again.</p>
    <?php endif; ?>

    <?php
    // Show the source code
    echo '<h2>Source Code</h2>';
    show_source(__FILE__);
    // Reset session variables for a new round
    session_unset();
    session_destroy();
    ?>
<?php endif; ?>

</body>
</html>
