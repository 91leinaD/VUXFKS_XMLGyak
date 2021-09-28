<!DOCTYPE HTML >

<html lang = "hu">
    <head>
        
        <title>Űrlap</title>
        <meta charset="UTF-8">

        <style>
            body {background-color: #0099FF; font-family: Arial; font-size: 16pt}
        </style>

    </head>
    <body>
        
        <h2>HTML5 Űrlap</h2>

        <?php
            echo ("<strong>Név:</strong> " . $_POST['nev'] . "<br><br>");
            echo ("<strong>PIN kód:</strong> " . $_POST['kod'] . "<br><br>");
            echo ("<strong>Gyümölcs:</strong> " . $_POST['gyumolcs'] . "<br><br>");
            echo ("<strong>Életkor:</strong> " . $_POST['eletkor'] . "<br><br>");
            echo ("<strong>Lábméret:</strong> " . $_POST['labmeret'] . "<br><br>");
            echo ("<strong>Önbizalom:</strong> " . $_POST['onbizalom'] . "<br><br>");
        ?>
        <a href="urlap_DD.html">vissza az űrlapra...</a>

    </body>
</html>