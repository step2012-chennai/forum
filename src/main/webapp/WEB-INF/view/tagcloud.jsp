<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@include file="homeHeader.jsp" %>
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
      TagCanvas.maxSpeed=0.002;
      TagCanvas.weight=true;
      TagCanvas.weightMode='color';
      TagCanvas.textColour = '#250517';
      TagCanvas.textHeight = 25.0;
      TagCanvas.textFont = 'sans-serif'
      TagCanvas.outlineColour = '#00FF00';
      TagCanvas.weightFrom = 'data-weight';
      TagCanvas.wheelZoom = false;
      TagCanvas.minBrightness = 1.1;
      TagCanvas.outlineColour = 'red';
      TagCanvas.weightSize = 5;
      TagCanvas.shuffleTags =true;
      TagCanvas.stretchX =1.5;
      TagCanvas.Start('myCanvas');
    } catch(e) {
      document.getElementById('myCanvasContainer').style.display = 'none';
    }
  };
 </script>
    <div id="myCanvasContainer" align="center" >
  <canvas width="1200" height="800" id="myCanvas">

   <p>Anything in here will be replaced on browsers that support the canvas element</p>
   <div id="wrapper">
   <div id="colorForHead">
   <ul>
    <%@page import="java.util.List"%>
            <% for (String tag : (List<String>) request.getAttribute("tags") ) { %>
                        <li><a align="center" href="tagsearch?tag=<%=tag%>"><%out.println(tag);%></a></li>
            <%}%>
   </ul>
   </div>
   </div>
  </canvas>
 </div>
