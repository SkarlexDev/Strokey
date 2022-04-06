package main.util;

import java.util.LinkedHashMap;
import java.util.Map;

public class Keys {

	public Map<Integer, String> key = new LinkedHashMap<Integer, String>();

	public Keys() {
		// row 1
		key.put(1, "ESC");
		key.put(59, "F1");
		key.put(60, "F2");
		key.put(61, "F3");
		key.put(62, "F4");
		key.put(63, "F5");
		key.put(64, "F6");
		key.put(65, "F7");
		key.put(66, "F8");
		key.put(67, "F9");
		key.put(68, "F10");
		key.put(87, "F11");
		key.put(88, "F12");
		key.put(14, "←");
		
		// row 2
		key.put(41, "`");
		key.put(2, "1");
		key.put(3, "2");
		key.put(4, "3");
		key.put(5, "4");
		key.put(6, "5");
		key.put(7, "6");
		key.put(8, "7");
		key.put(9, "8");
		key.put(10, "9");
		key.put(11, "0");
		key.put(12, "-");
		key.put(13, "=");
		key.put(28,"Enter");
		
		// row 3
		key.put(15,"Tab");
		key.put(16,"Q");
		key.put(17,"W");
		key.put(18,"E");
		key.put(19,"R");
		key.put(20,"T");
		key.put(21,"Y");
		key.put(22,"U");
		key.put(23,"I");
		key.put(24,"O");
		key.put(25,"P");
		key.put(26,"[");
		key.put(27,"]");
		key.put(57,"Space");
		
		// row 4
		key.put(58,"Caps");
		key.put(30,"A");
		key.put(31,"S");
		key.put(32,"D");
		key.put(33,"F");
		key.put(34,"G");
		key.put(35,"H");
		key.put(36,"J");
		key.put(37,"K");
		key.put(38,"L");
		key.put(39,";");
		key.put(40,"'");
		key.put(43,"|");
		key.put(3675,"Meta");
		
		// row 5
		key.put(42,"Shift");
		key.put(44,"Z");
		key.put(45,"X");
		key.put(46,"C");
		key.put(47,"V");
		key.put(48,"B");
		key.put(49,"N");
		key.put(50,"M");
		key.put(51,",");
		key.put(52,".");
		key.put(53,"/");
		key.put(3638,"LShift");
		key.put(56,"Alt");
		key.put(29,"Ctrl");

	}
}
