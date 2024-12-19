<?php
$keep_looping = True;
$age = 15;

// Do ... while loop is special because it executes at least one time, even if the condition is not true
do {
    echo "In do ... while loop";

    // Switch statement - Can check multiple conditions in a short form way
    switch($age) { // Used for checking specific values rather than ranges
        case 40:
            echo "Condition 1 was true";
            $age++;
            break;

        case 15:
            echo "Condition 2 was true";
            $age++;
            break;

        default:
            echo "None of the conditions were true";
            $keep_looping = False;
    }
} while($keep_looping); // Condition is checked at the end of each loop
?>
