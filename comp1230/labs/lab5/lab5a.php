<!-- lab5a.php 
Practice: Reading from a .txt file

Path:
------------
Absolute path: Path starting from the root of the computer file system

Relative path: This is the path relative to the current file that is being executed
E.g. Go to current folder to find file: ./file1.txt
E.g. Go to parent folder to find file: ../file1.txt
E.g. Go to grandparent folder to find file: ../../file1.txt
-->

<?php
    // Validate whether the file is on our system and in the right location
    // Built-in php function: file_exists()
    $filename = "/file1.txt"
    if(file_exists($filename)){
        echo = "<h2>"; // Injecting HTML code in PHP code
        print("The was successfully found on the server");
        echo = "</h2>";

        // Built in php function file_get_contents()
        $text_from_file = file_get_contents($filename)

        // Built in php function readfile()
        // $text_from_file = readfile($filename)
        
        print(nl2br($text_from_file))
    }
    // Validate the file type, wheter the file has contents...
   /* else if (){
    }
    */
    else {
        print("Error: The was not found on the file server")
    }
?>
