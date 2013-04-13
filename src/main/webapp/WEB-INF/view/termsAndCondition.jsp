<html>
    <head>
         <script src="./static/facebox/lib/jquery.js" type="text/javascript"></script>
         <link href="./static/facebox/src/facebox.css" media="screen" rel="stylesheet" type="text/css"/>
         <script src="./static/facebox/src/facebox.js" type="text/javascript"></script>
         <script>
            function loadFaceBox(){
                jQuery(document).ready(function($) {
                $('a[rel*=facebox]').facebox()
                });
            }
         </script>
    </head>
    <body onLoad="loadFaceBox()">
        <a href="#info" rel="facebox[.bolder]">terms and conditions</a>

        <div id="info" style="visibility: hidden">
            <center>
                <h2>
                    Terms And Conditions
                </h2>
            </center>
            <p>
                These are the terms and conditions to create an account
                on this site.
            </p>
        </div>
    </body>
</html>