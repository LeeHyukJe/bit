import java.awt.Font;
import java.util.Set;

import javax.swing.UIManager;

// Main.java Java Chatting Client 시작 부분 C 의 main() 함수
public class Main {
	
	public static void setDefaultSize(int size) {

	    Set<Object> keySet = UIManager.getLookAndFeelDefaults().keySet();
	    Object[] keys = keySet.toArray(new Object[keySet.size()]);

	    for (Object key : keys) {

	        if (key != null && key.toString().toLowerCase().contains("font")) {

	            System.out.println(key);
	            Font font = UIManager.getDefaults().getFont(key);
	            if (font != null) {
	                font = font.deriveFont((float)size);
	                UIManager.put(key, font);
	            }

	        }

	    }
	}

	public static void main(String[] args) {
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		Main.setDefaultSize(30);
		
		Client client = new Client();
		client.setVisible(true);

	}

}
