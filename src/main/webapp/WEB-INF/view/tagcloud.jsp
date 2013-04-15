<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<script src="./static/javascript/tagcanvas.js" type="text/javascript"></script>
 <script type="text/javascript">
  var gradient = {
   0:    '#f00', // red
   0.33: '#ff0', // yellow
   0.66: '#0f0', // green
   1:    '#00f'  // blue
  };
  window.onload = function() {
    try {
      TagCanvas.weightGradient = gradient;
      TagCanvas.maxSpeed=0.00;
      TagCanvas.weight=true;
      TagCanvas.weightMode='size';
      TagCanvas.textColour = 'blue';
      TagCanvas.textHeight = 25.0;
      TagCanvas.textFont = 'sans-serif'
      TagCanvas.outlineColour = '#00FF00';
      TagCanvas.weightFrom = 'data-weight';
      TagCanvas.wheelZoom = false;
      TagCanvas.minBrightness = 1.1;
      TagCanvas.outlineColour = 'red';
      TagCanvas.depth = 0.50;
      TagCanvas.weightSize = 5;
      TagCanvas.shuffleTags =true;
      TagCanvas.stretchX =1.7;
      TagCanvas.Start('myCanvas');
    } catch(e) {
      document.getElementById('myCanvasContainer').style.display = 'none';
    }
  };




 </script>

<%@include file="header.jsp" %>
<div id="myCanvasContainer" >
 <canvas width="1300" height="700" id="myCanvas">

  <p>Anything in here will be replaced on browsers that support the canvas element</p>
  <ul>
   <%@page import="java.util.List"%>
           <% for (String tag : (List<String>) request.getAttribute("tags") ) { %>
                       <li><a href="tagsearch?tag=<%=tag%>"><%=tag%></a></li>
           <%}%>
  </ul>
 </canvas>
</div>