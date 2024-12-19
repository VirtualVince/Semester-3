<?php
// Controller file: Controls when to switch the meat (data) of the burger
// This file primarily switches pages

require "model/users.php"; // Would could have used include, but wouldn't give an error if file can't be included

// TODO: We need to create a input box called user_type
$page = ""; // Represents which file is the MEAT of the burger currently
if (!empty($_GET)) { // If the page was submitted (submit button was pressed)
    if (isset($_GET["user_type"])) { // This checks if a value was entered into the user_type textbox
        $page = "users"; // view/users.phtml file
        // Built-in: uses a function that cleans the data inputted from the user first
        // 1ST PARAM: INPUT_GET, INPUT_POST
        // 2ND PARAM: matches the name/id of the input in the html
        // 3RD PARAM: A flag FILTER_VALIDATE_INT, FILTER_VALIDATE_FLOAT: Memorize FILTER_start of the flag
        $user_type = filter_input(type: INPUT_GET, var_name: "user_type", filter: FILTER_SANITIZE_STRING);
        echo $user_type;
    }
} else if (isset($_GET["page"]) && !empty($_GET["page"])) { // The page key for the GET superglobal can be set by clicking on
    // a navigation menu link i.e ?page=home
    $page = filter_input(type: INPUT_GET, var_name: "page", filter: FILTER_SANITIZE_STRING);
    var_dump($page);
}

else if (isset($_GET["page"]) && !empty($_GET["page"])) { // The page key for the GET superglobal can be set by clicking on
    // a navigation menu link i.e ?page=home
    $page = filter_input(type: INPUT_GET, var_name: "page", filter: FILTER_SANITIZE_STRING);
}

if ($page) {
    switch ($page) {
        case "home":
            $page = "view/home.phtml";
            break;
            case "users":
            $page = "view/users.phtml";
            break;
            default:
            include "view/error.phtml"; 

    }
} else {
    //If this is the first time the user is visiting the page, go to the homepage
    include "view/home.phtml";
}
