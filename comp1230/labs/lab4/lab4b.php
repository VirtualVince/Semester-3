<?php
 $whole_string = "Vincente";
 $substring = "Sequeira";
 $position_last_name = strpos($whole_string, $substring);
 echo $position_last_name

 // Replace
 $sentence_for_replacements = "Today is a rainy and cold day"
 $bad_weather = ["rainy", "cold"];
 $good_weather = ["sunny", "warm"];
 $sentence_with_replacements = str_replace($bad_weather, $good_weather)
 echo sentence_with_replacements
?>