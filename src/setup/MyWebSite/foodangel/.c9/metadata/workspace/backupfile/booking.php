{"changed":false,"filter":false,"title":"booking.php","tooltip":"/backupfile/booking.php","undoManager":{"mark":0,"position":0,"stack":[[{"start":{"row":286,"column":0},"end":{"row":482,"column":6},"action":"remove","lines":["\t","\t","\t","\t","\t","\t","\t","\t","\t","\t","\t","\t<div class=\"extra\">","\t\t<div class=\"extra-inner\">","\t\t\t<div class=\"container\">","\t\t\t\t<div class=\"row\">","\t\t\t\t\t<div class=\"span3\">","\t\t\t\t\t\t<h4>","\t\t\t\t\t\t\tAbout Free Admin Template</h4>","\t\t\t\t\t\t<ul>","\t\t\t\t\t\t\t<li><a href=\"javascript:;\">EGrappler.com</a></li>","\t\t\t\t\t\t\t<li><a href=\"javascript:;\">Web Development Resources</a></li>","\t\t\t\t\t\t\t<li><a href=\"javascript:;\">Responsive php5 Portfolio Templates</a></li>","\t\t\t\t\t\t\t<li><a href=\"javascript:;\">Free Resources and Scripts</a></li>","\t\t\t\t\t\t</ul>","\t\t\t\t\t</div>","\t\t\t\t\t<!-- /span3 -->","\t\t\t\t\t<div class=\"span3\">","\t\t\t\t\t\t<h4>","\t\t\t\t\t\t\tSupport</h4>","\t\t\t\t\t\t<ul>","\t\t\t\t\t\t\t<li><a href=\"javascript:;\">Frequently Asked Questions</a></li>","\t\t\t\t\t\t\t<li><a href=\"javascript:;\">Ask a Question</a></li>","\t\t\t\t\t\t\t<li><a href=\"javascript:;\">Video Tutorial</a></li>","\t\t\t\t\t\t\t<li><a href=\"javascript:;\">Feedback</a></li>","\t\t\t\t\t\t</ul>","\t\t\t\t\t</div>","\t\t\t\t\t<!-- /span3 -->","\t\t\t\t\t<div class=\"span3\">","\t\t\t\t\t\t<h4>","\t\t\t\t\t\t\tSomething Legal</h4>","\t\t\t\t\t\t<ul>","\t\t\t\t\t\t\t<li><a href=\"javascript:;\">Read License</a></li>","\t\t\t\t\t\t\t<li><a href=\"javascript:;\">Terms of Use</a></li>","\t\t\t\t\t\t\t<li><a href=\"javascript:;\">Privacy Policy</a></li>","\t\t\t\t\t\t</ul>","\t\t\t\t\t</div>","\t\t\t\t\t<!-- /span3 -->","\t\t\t\t\t<div class=\"span3\">","\t\t\t\t\t\t<h4>","\t\t\t\t\t\t\tOpen Source jQuery Plugins</h4>","\t\t\t\t\t\t<ul>","\t\t\t\t\t\t\t<li><a href=\"http://www.egrappler.com\">Open Source jQuery Plugins</a></li>","\t\t\t\t\t\t\t<li><a href=\"http://www.egrappler.com;\">php5 Responsive Tempaltes</a></li>","\t\t\t\t\t\t\t<li><a href=\"http://www.egrappler.com;\">Free Contact Form Plugin</a></li>","\t\t\t\t\t\t\t<li><a href=\"http://www.egrappler.com;\">Flat UI PSD</a></li>","\t\t\t\t\t\t</ul>","\t\t\t\t\t</div>","\t\t\t\t\t<!-- /span3 -->","\t\t\t\t</div>","\t\t\t\t<!-- /row -->","\t\t\t</div>","\t\t\t<!-- /container -->","\t\t</div>","\t\t<!-- /extra-inner -->","\t</div>","\t<!-- /extra -->","\t<div class=\"footer\">","\t\t<div class=\"footer-inner\">","\t\t\t<div class=\"container\">","\t\t\t\t<div class=\"row\">","\t\t\t\t\t<div class=\"span12\"> &copy; 2013 <a href=\"http://www.egrappler.com/\">Bootstrap Responsive Admin Template</a>. </div>","\t\t\t\t\t<!-- /span12 -->","\t\t\t\t</div>","\t\t\t\t<!-- /row -->","\t\t\t</div>","\t\t\t<!-- /container -->","\t\t</div>","\t\t<!-- /footer-inner -->","\t</div>","\t<!-- /footer -->","\t<!-- Le javascript","================================================== -->","\t<!-- Placed at the end of the document so the pages load faster -->","\t<script src=\"js/jquery-1.7.2.min.js\"></script>","\t<script src=\"js/excanvas.min.js\"></script>","\t<script src=\"js/chart.min.js\" type=\"text/javascript\"></script>","\t<script src=\"js/bootstrap.js\"></script>","\t<script language=\"javascript\" type=\"text/javascript\" src=\"js/full-calendar/fullcalendar.min.js\"></script>","","\t<script src=\"js/base.js\"></script>","\t<script>","\t\tvar lineChartData = {","\t\t\tlabels: [\"January\", \"February\", \"March\", \"April\", \"May\", \"June\", \"July\"],","\t\t\tdatasets: [{","\t\t\t\tfillColor: \"rgba(220,220,220,0.5)\",","\t\t\t\tstrokeColor: \"rgba(220,220,220,1)\",","\t\t\t\tpointColor: \"rgba(220,220,220,1)\",","\t\t\t\tpointStrokeColor: \"#fff\",","\t\t\t\tdata: [65, 59, 90, 81, 56, 55, 40]","\t\t\t}, {","\t\t\t\tfillColor: \"rgba(151,187,205,0.5)\",","\t\t\t\tstrokeColor: \"rgba(151,187,205,1)\",","\t\t\t\tpointColor: \"rgba(151,187,205,1)\",","\t\t\t\tpointStrokeColor: \"#fff\",","\t\t\t\tdata: [28, 48, 40, 19, 96, 27, 100]","\t\t\t}]","","\t\t}","","\t\tvar myLine = new Chart(document.getElementById(\"area-chart\").getContext(\"2d\")).Line(lineChartData);","","","\t\tvar barChartData = {","\t\t\tlabels: [\"January\", \"February\", \"March\", \"April\", \"May\", \"June\", \"July\"],","\t\t\tdatasets: [{","\t\t\t\tfillColor: \"rgba(220,220,220,0.5)\",","\t\t\t\tstrokeColor: \"rgba(220,220,220,1)\",","\t\t\t\tdata: [65, 59, 90, 81, 56, 55, 40]","\t\t\t}, {","\t\t\t\tfillColor: \"rgba(151,187,205,0.5)\",","\t\t\t\tstrokeColor: \"rgba(151,187,205,1)\",","\t\t\t\tdata: [28, 48, 40, 19, 96, 27, 100]","\t\t\t}]","","\t\t}","","\t\t$(document).ready(function() {","\t\t\tvar date = new Date();","\t\t\tvar d = date.getDate();","\t\t\tvar m = date.getMonth();","\t\t\tvar y = date.getFullYear();","\t\t\tvar calendar = $('#calendar').fullCalendar({","\t\t\t\theader: {","\t\t\t\t\tleft: 'prev,next today',","\t\t\t\t\tcenter: 'title',","\t\t\t\t\tright: 'month,agendaWeek,agendaDay'","\t\t\t\t},","\t\t\t\tselectable: true,","\t\t\t\tselectHelper: true,","\t\t\t\tselect: function(start, end, allDay) {","\t\t\t\t\tvar title = prompt('Event Title:');","\t\t\t\t\tif (title) {","\t\t\t\t\t\tcalendar.fullCalendar('renderEvent', {","\t\t\t\t\t\t\t\ttitle: title,","\t\t\t\t\t\t\t\tstart: start,","\t\t\t\t\t\t\t\tend: end,","\t\t\t\t\t\t\t\tallDay: allDay","\t\t\t\t\t\t\t},","\t\t\t\t\t\t\ttrue // make the event \"stick\"","\t\t\t\t\t\t);","\t\t\t\t\t}","\t\t\t\t\tcalendar.fullCalendar('unselect');","\t\t\t\t},","\t\t\t\teditable: true,","\t\t\t\tevents: [{","\t\t\t\t\ttitle: 'All Day Event',","\t\t\t\t\tstart: new Date(y, m, 1)","\t\t\t\t}, {","\t\t\t\t\ttitle: 'Long Event',","\t\t\t\t\tstart: new Date(y, m, d + 5),","\t\t\t\t\tend: new Date(y, m, d + 7)","\t\t\t\t}, {","\t\t\t\t\tid: 999,","\t\t\t\t\ttitle: 'Repeating Event',","\t\t\t\t\tstart: new Date(y, m, d - 3, 16, 0),","\t\t\t\t\tallDay: false","\t\t\t\t}, {","\t\t\t\t\tid: 999,","\t\t\t\t\ttitle: 'Repeating Event',","\t\t\t\t\tstart: new Date(y, m, d + 4, 16, 0),","\t\t\t\t\tallDay: false","\t\t\t\t}, {","\t\t\t\t\ttitle: 'Meeting',","\t\t\t\t\tstart: new Date(y, m, d, 10, 30),","\t\t\t\t\tallDay: false","\t\t\t\t}, {","\t\t\t\t\ttitle: 'Lunch',","\t\t\t\t\tstart: new Date(y, m, d, 12, 0),","\t\t\t\t\tend: new Date(y, m, d, 14, 0),","\t\t\t\t\tallDay: false","\t\t\t\t}, {","\t\t\t\t\ttitle: 'Birthday Party',","\t\t\t\t\tstart: new Date(y, m, d + 1, 19, 0),","\t\t\t\t\tend: new Date(y, m, d + 1, 22, 30),","\t\t\t\t\tallDay: false","\t\t\t\t}, {","\t\t\t\t\ttitle: 'EGrappler.com',","\t\t\t\t\tstart: new Date(y, m, 28),","\t\t\t\t\tend: new Date(y, m, 29),","\t\t\t\t\turl: 'http://EGrappler.com/'","\t\t\t\t}]","\t\t\t});","\t\t});","\t</script>","\t<!-- /Calendar -->","</body>","</php>"],"id":87},{"start":{"row":286,"column":0},"end":{"row":288,"column":2},"action":"insert","lines":["<?php","require_once('require/footer.php');","?>"]}]]},"ace":{"folds":[],"scrolltop":0,"scrollleft":0,"selection":{"start":{"row":8,"column":1},"end":{"row":8,"column":1},"isBackwards":false},"options":{"guessTabSize":true,"useWrapMode":false,"wrapToView":true},"firstLineState":0},"timestamp":1465626836019}