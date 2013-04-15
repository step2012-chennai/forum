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
         <script type="text/javascript" src="./static/javascript/printDiv.js"></script>
    </head>
    <body onLoad="loadFaceBox()">
        <a href="#info" rel="facebox[.bolder]">terms and conditions</a>

        <div id="info" style="visibility: hidden; display:none">
            <input type="button" value="Print" onClick="PrintElem('#info')">
            <center>
                <h2>
                    Terms And Conditions
                </h2>
            </center>
            <p>
            By accepting the terms and conditions, you state that you abide and understand our copyright,
            usage, limitation of Forum's liability and privacy policy.
            </p>
        </div>
    </body>
</html>