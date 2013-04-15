 function PrintElem(elem){
         Popup($(elem).html());
 }

 function Popup(data){
     var mywindow = window.open('', 'my div', 'height=400,width=600');
     mywindow.document.write('<html><head><title></title>');
     mywindow.document.write('</head><body >');
     mywindow.document.write(data);
     mywindow.document.write('</body></html>');

     mywindow.print();
     mywindow.close();

     return true;
 }
