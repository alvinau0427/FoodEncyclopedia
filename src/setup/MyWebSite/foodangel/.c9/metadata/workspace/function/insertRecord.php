{"filter":false,"title":"insertRecord.php","tooltip":"/function/insertRecord.php","undoManager":{"mark":43,"position":43,"stack":[[{"start":{"row":20,"column":73},"end":{"row":20,"column":74},"action":"insert","lines":[" "],"id":1}],[{"start":{"row":20,"column":74},"end":{"row":20,"column":87},"action":"insert","lines":["BoardGameType"],"id":2}],[{"start":{"row":20,"column":87},"end":{"row":20,"column":88},"action":"insert","lines":[","],"id":3}],[{"start":{"row":20,"column":164},"end":{"row":20,"column":165},"action":"insert","lines":[","],"id":4}],[{"start":{"row":20,"column":165},"end":{"row":20,"column":171},"action":"insert","lines":["Status"],"id":5}],[{"start":{"row":20,"column":165},"end":{"row":20,"column":166},"action":"insert","lines":[" "],"id":6}],[{"start":{"row":21,"column":77},"end":{"row":21,"column":108},"action":"insert","lines":[", '{$_POST['BoardGameDetail']}'"],"id":7}],[{"start":{"row":21,"column":89},"end":{"row":21,"column":104},"action":"remove","lines":["BoardGameDetail"],"id":8},{"start":{"row":21,"column":89},"end":{"row":21,"column":102},"action":"insert","lines":["BoardGameType"]}],[{"start":{"row":21,"column":281},"end":{"row":21,"column":302},"action":"insert","lines":[", '{$_POST['Photo']}'"],"id":9}],[{"start":{"row":21,"column":293},"end":{"row":21,"column":298},"action":"remove","lines":["Photo"],"id":10},{"start":{"row":21,"column":293},"end":{"row":21,"column":299},"action":"insert","lines":["Status"]}],[{"start":{"row":28,"column":20},"end":{"row":29,"column":0},"action":"insert","lines":["",""],"id":11},{"start":{"row":29,"column":0},"end":{"row":29,"column":20},"action":"insert","lines":["                    "]}],[{"start":{"row":29,"column":20},"end":{"row":30,"column":0},"action":"insert","lines":["",""],"id":12},{"start":{"row":30,"column":0},"end":{"row":30,"column":20},"action":"insert","lines":["                    "]}],[{"start":{"row":29,"column":8},"end":{"row":29,"column":20},"action":"remove","lines":["            "],"id":13},{"start":{"row":29,"column":8},"end":{"row":32,"column":18},"action":"insert","lines":["case \"boardgamebooking\":","            $sql = \"INSERT INTO boardgamebooking(BoardGameID, Quantity, TotalPrice, MemberName, Contact, OrderDate, OrderTime, ReceiptDate, ReceiptTime, Status) ","            VALUES('{$_POST['BoardGameID']}', '{$_POST['Quantity']}', '{$_POST['TotalPrice']}', '{$_POST['MemberName']}', '{$_POST['Contact']}', '{$_POST['OrderDate']}', '{$_POST['OrderTime']}', '{$_POST['ReceiptDate']}', '{$_POST['ReceiptTime']}', '{$_POST['Status']}')\";","            break;"]}],[{"start":{"row":29,"column":14},"end":{"row":29,"column":30},"action":"remove","lines":["boardgamebooking"],"id":14},{"start":{"row":29,"column":14},"end":{"row":29,"column":27},"action":"insert","lines":["boardgametype"]}],[{"start":{"row":30,"column":32},"end":{"row":30,"column":48},"action":"remove","lines":["boardgamebooking"],"id":15},{"start":{"row":30,"column":32},"end":{"row":30,"column":45},"action":"insert","lines":["boardgametype"]}],[{"start":{"row":30,"column":46},"end":{"row":30,"column":156},"action":"remove","lines":["BoardGameID, Quantity, TotalPrice, MemberName, Contact, OrderDate, OrderTime, ReceiptDate, ReceiptTime, Status"],"id":16},{"start":{"row":30,"column":46},"end":{"row":30,"column":50},"action":"insert","lines":["Type"]}],[{"start":{"row":31,"column":44},"end":{"row":31,"column":269},"action":"remove","lines":[", '{$_POST['Quantity']}', '{$_POST['TotalPrice']}', '{$_POST['MemberName']}', '{$_POST['Contact']}', '{$_POST['OrderDate']}', '{$_POST['OrderTime']}', '{$_POST['ReceiptDate']}', '{$_POST['ReceiptTime']}', '{$_POST['Status']}'"],"id":17}],[{"start":{"row":30,"column":52},"end":{"row":31,"column":12},"action":"remove","lines":["","            "],"id":18}],[{"start":{"row":30,"column":69},"end":{"row":30,"column":80},"action":"remove","lines":["BoardGameID"],"id":20},{"start":{"row":30,"column":69},"end":{"row":30,"column":73},"action":"insert","lines":["Type"]}],[{"start":{"row":38,"column":14},"end":{"row":38,"column":24},"action":"remove","lines":["indexphoto"],"id":21},{"start":{"row":38,"column":14},"end":{"row":38,"column":26},"action":"insert","lines":["indexsetting"]}],[{"start":{"row":39,"column":32},"end":{"row":39,"column":42},"action":"remove","lines":["indexphoto"],"id":22},{"start":{"row":39,"column":32},"end":{"row":39,"column":44},"action":"insert","lines":["indexsetting"]}],[{"start":{"row":39,"column":45},"end":{"row":39,"column":54},"action":"remove","lines":["PhotoName"],"id":23},{"start":{"row":39,"column":45},"end":{"row":39,"column":49},"action":"insert","lines":["Name"]}],[{"start":{"row":39,"column":51},"end":{"row":39,"column":57},"action":"remove","lines":["Status"],"id":24},{"start":{"row":39,"column":51},"end":{"row":39,"column":59},"action":"insert","lines":["ShowItem"]}],[{"start":{"row":39,"column":78},"end":{"row":39,"column":87},"action":"remove","lines":["PhotoName"],"id":25},{"start":{"row":39,"column":78},"end":{"row":39,"column":82},"action":"insert","lines":["Name"]}],[{"start":{"row":39,"column":98},"end":{"row":39,"column":104},"action":"remove","lines":["Status"],"id":26},{"start":{"row":39,"column":98},"end":{"row":39,"column":106},"action":"insert","lines":["ShowItem"]}],[{"start":{"row":37,"column":0},"end":{"row":40,"column":18},"action":"remove","lines":["                    ","        case \"indexsetting\":    ","            $sql = \"INSERT INTO indexsetting(Name, ShowItem) VALUES('{$_POST['Name']}', '{$_POST['ShowItem']}')\";","            break;"],"id":27}],[{"start":{"row":36,"column":18},"end":{"row":37,"column":0},"action":"remove","lines":["",""],"id":28}],[{"start":{"row":45,"column":18},"end":{"row":46,"column":0},"action":"insert","lines":["",""],"id":29},{"start":{"row":46,"column":0},"end":{"row":46,"column":12},"action":"insert","lines":["            "]}],[{"start":{"row":46,"column":12},"end":{"row":47,"column":0},"action":"insert","lines":["",""],"id":30},{"start":{"row":47,"column":0},"end":{"row":47,"column":12},"action":"insert","lines":["            "]}],[{"start":{"row":47,"column":8},"end":{"row":47,"column":12},"action":"remove","lines":["    "],"id":31}],[{"start":{"row":47,"column":8},"end":{"row":50,"column":18},"action":"insert","lines":["case \"notification\":","            $sql = \"INSERT INTO notification(Title, Body, Uid, Name, Date) ","            VALUES('{$_POST['Title']}', '{$_POST['Body']}', '{$_POST['Uid']}', '{$_POST['Name']}', '{$_POST['Date']}')\";","            break;"],"id":32}],[{"start":{"row":47,"column":14},"end":{"row":47,"column":26},"action":"remove","lines":["notification"],"id":33},{"start":{"row":47,"column":14},"end":{"row":47,"column":15},"action":"insert","lines":["p"]}],[{"start":{"row":47,"column":15},"end":{"row":47,"column":16},"action":"insert","lines":["h"],"id":34}],[{"start":{"row":47,"column":16},"end":{"row":47,"column":17},"action":"insert","lines":["o"],"id":35}],[{"start":{"row":47,"column":17},"end":{"row":47,"column":18},"action":"insert","lines":["t"],"id":36}],[{"start":{"row":47,"column":18},"end":{"row":47,"column":19},"action":"insert","lines":["o"],"id":37}],[{"start":{"row":48,"column":32},"end":{"row":48,"column":44},"action":"remove","lines":["notification"],"id":38},{"start":{"row":48,"column":32},"end":{"row":48,"column":37},"action":"insert","lines":["photo"]}],[{"start":{"row":48,"column":49},"end":{"row":48,"column":66},"action":"remove","lines":[", Uid, Name, Date"],"id":39}],[{"start":{"row":48,"column":38},"end":{"row":48,"column":43},"action":"remove","lines":["Title"],"id":40},{"start":{"row":48,"column":38},"end":{"row":48,"column":47},"action":"insert","lines":["PhotoName"]}],[{"start":{"row":48,"column":49},"end":{"row":48,"column":53},"action":"remove","lines":["Body"],"id":41},{"start":{"row":48,"column":49},"end":{"row":48,"column":55},"action":"insert","lines":["Status"]}],[{"start":{"row":49,"column":29},"end":{"row":49,"column":34},"action":"remove","lines":["Title"],"id":42},{"start":{"row":49,"column":29},"end":{"row":49,"column":38},"action":"insert","lines":["PhotoName"]}],[{"start":{"row":49,"column":54},"end":{"row":49,"column":58},"action":"remove","lines":["Body"],"id":43},{"start":{"row":49,"column":54},"end":{"row":49,"column":60},"action":"insert","lines":["Status"]}],[{"start":{"row":49,"column":64},"end":{"row":49,"column":123},"action":"remove","lines":[", '{$_POST['Uid']}', '{$_POST['Name']}', '{$_POST['Date']}'"],"id":44}],[{"start":{"row":48,"column":57},"end":{"row":49,"column":12},"action":"remove","lines":["","            "],"id":45}]]},"ace":{"folds":[],"scrolltop":360,"scrollleft":0,"selection":{"start":{"row":48,"column":57},"end":{"row":48,"column":57},"isBackwards":false},"options":{"guessTabSize":true,"useWrapMode":false,"wrapToView":true},"firstLineState":{"row":7,"state":"php-start","mode":"ace/mode/php"}},"timestamp":1472554700885,"hash":"e18e8f0d7aad3d2e3b1327ba904a2e25a334d22e"}