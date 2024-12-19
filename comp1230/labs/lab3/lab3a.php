<?php
    // lab3a.php
    //Practice: Superglobal variables, isset() function, filter_input() function
?>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maxium-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge"> 
    <title> Document</title>
<body>
    <?php
        //Data types in PHP 
        $employee_name = "Al Bope " // String
        $employee_id = 23453; // Interger
        $is_active = true; 
        $salaries = [40000, 60000, 80000]
        $employees = ["secretary" => "Al Boope", "president" => "Donna Call", "treasurer" => "Julie Man" ] //A Associateive array (AA)
        // AA is also called dictionary, also called map
        // keys and values, where the keys are strings and the values can be any data type
        echo "Employee name" . $employee_name . "employee id" . $employee_id . "is active" . $is_active

        //for loop is meant to numerical arrays, because it has an index $i that iterates over the loop 
        for($i - 0; $i < sizeof($salaries); $i++){
            echo $salaries
        }

        //For loop for an associative array
        foreach($employees as $key => $value){
            echo "$key $value"; 
        }
    ?>
    <form method="GET">
        <label for="positions">Postion</label>
        <select name="positions" id="postions">
            <option value="Ceo">CEO</option>
            <option value="supervisor">supervisor</option>
            <option value="clerk">clerk</option>
        </select>

        <br>
        <label for="fullname">Full Name</label>
        <input type="text" name="fullname" id="fullname">
        <input type="submit" value="GO">
    </form>
        <?php
        // Complete lab2a.php in Week 3 lab session -> In the first 10-20 mins
            $is_valid = False;

        if(isset($_GET["fullname"]) && isset($_GET["positions"])) { // Check if the values are not null
             echo $_GET["fullname"];
         if(!empty($_GET["fullname"]) && !empty($_GET["positions"])) {
          if(is_string($_GET["fullname"]) && is_string($_GET["positions"])) {
              echo "<p>Thank you for submitting</p>";
            $is_valid = True;
        }
    }
}

// Once the validation is complete, then actually save the values (cleaned up) into some local variables
// If you don't filter input: SQL injection (Code could be inserted into a input text box)
$position_entered = filter_input(INPUT_GET, "positions");
$fullname_entered = filter_input(INPUT_GET, "fullname");

if(!$is_valid) {
    // HTML code is read by PHP code as text
    // Why: Because everything passing over the internet is converted to text as the most simplest data type
    echo "<p>Please fill in all of the form fields and then press submit</p>";
}

        ?>
</body>
</html>