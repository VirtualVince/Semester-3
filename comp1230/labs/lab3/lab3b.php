<?php
$age = 40;
$is_underage = True; // Start with True initialized value because while loop will not loop if the condition is false
// Therefore, we must name the variable so that it makes sense initialized to True as the default

// While loop - Don't want an infinite loop
// Can crash the program because it results in a "Stack overflow" -> memory in the RAM is full!
while($is_underage){
    if($age == 40){
        echo "You are exactly 40. You get free alcohol!";
        $age = 1;
        continue; // Skip the rest of this iteration and go to the next loop iteration
    }
    
    if($age < 19){
        echo "You are not authorized to buy alcohol products";
        break; // We won't have an infinite loop because we either break or change the value of the condition
    } elseif($age < 65){ // Only if the first condition was false, then does it check the second condition
        echo "You cannot get the senior discount";
        $is_underage = False;
    } else { // If they were not < 19 and not < 65 -> Then they were >= 65
        echo "You may buy alcohol products but there is no discount";
        $is_underage = False;
    }
}
?>
