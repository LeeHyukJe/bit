package TextEditor;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.*;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Collections;
public class TextToolEx10 extends Frame implements WindowListener{

	TextArea ta;
	TextField tfParam1, tfParam2;
	Panel pNorth, pSouth;
	Label lb1, lb2;

	String[] btnName = { "Undo", "짝수줄삭제", "문자삭제", "trim", "빈줄 삭제", "접두사 추가", "substring", "substring2","distinct" ,"distinct2",
						"패턴적용"};

	Button[] btn = new Button[btnName.length];
	private final String CR_LF = System.getProperty("line.separator");
	private String preText = "";

	private void registerEventHandler() {
		addWindowListener(this);

		int n = 0;

		btn[n++].addActionListener(new ActionListener() { // undo
			public void actionPerformed(ActionEvent ae) {
				String PreText = ta.getText();
				StringBuffer sb = new StringBuffer(preText.length());
				Scanner sc = new Scanner(preText);
				for (int i = 0; sc.hasNext(); i++) {
					preText = sc.nextLine();
					sb.append(preText).append(CR_LF);
				}
				ta.setText(sb.toString());
			}
		});

		btn[n++].addActionListener(new ActionListener() { // 삭제하기
			public void actionPerformed(ActionEvent ae) {
				String curText = ta.getText();
				StringBuffer sb = new StringBuffer(curText.length());
				preText = curText;
				Scanner sc = new Scanner(curText);
				for (int i = 0; sc.hasNext(); i++) {
					String line = sc.nextLine();
					if (i % 2 == 0) {
						sb.append(line).append(CR_LF);
					}
				}
				ta.setText(sb.toString());
			}
		});

		btn[n++].addActionListener(new ActionListener() {// 문자 삭제
			public void actionPerformed(ActionEvent ae) {
				String curText = ta.getText();
				StringBuffer sb = new StringBuffer(curText.length());

				preText = curText;
				String pline = tfParam1.getText();
				Scanner sc = new Scanner(curText);
				String temp = sc.nextLine();
				for (int i = 0; i < temp.length(); i++) {
					while (sc.hasNext()) {
						for (int j = 0; j < temp.length(); j++) {
							if (pline.charAt(i) == temp.charAt(j)) {
								sb.append(temp.charAt(j));
							}
						}
						temp = sc.nextLine();
					}
				}
				ta.setText(sb.toString());
			}
		});

		btn[n++].addActionListener(new ActionListener() {// trim
			public void actionPerformed(ActionEvent ae) {
				String curText = ta.getText();
				StringBuffer sb = new StringBuffer(curText.length());
				preText = curText;
				Scanner sc = new Scanner(curText);
				while (sc.hasNextLine()) {
					String temp = sc.nextLine().trim();
					sb.append(temp).append(CR_LF);
				}
				ta.setText(sb.toString());
			}
		});

		btn[n++].addActionListener(new ActionListener() {// 빈줄삭제
			public void actionPerformed(ActionEvent ae) {
				String curText = ta.getText();
				StringBuffer sb = new StringBuffer(curText.length());
				preText = curText;
				Scanner sc = new Scanner(curText);
				while (sc.hasNextLine()) {
					String temp = sc.nextLine();
					for (int i = 0; i < temp.length(); i++) {
						if (temp.charAt(i) != ' ') {
							sb.append(temp.charAt(i));
						}
					}
					sb.append(CR_LF);
				}
				ta.setText(sb.toString());
			}
		});

		btn[n++].addActionListener(new ActionListener() {// 접두사 접미사 붙이기
			public void actionPerformed(ActionEvent ae) {
				String curText = ta.getText();
				StringBuffer sb = new StringBuffer(curText.length());
				preText = curText;
				String p1 = tfParam1.getText();
				String p2 = tfParam2.getText();
				Scanner sc = new Scanner(curText);
				while (sc.hasNextLine()) {
					String temp = sc.nextLine();
					temp = p1 + temp + p2;
					sb.append(temp).append(CR_LF);
				}
				ta.setText(sb.toString());
			}
		});

		btn[n++].addActionListener(new ActionListener() {// 접두사 접미사 삭제
			public void actionPerformed(ActionEvent ae) {
				String curText = ta.getText();
				StringBuffer sb = new StringBuffer(curText.length());
				preText = curText;
				String p1 = tfParam1.getText();
				String p2 = tfParam2.getText();
				Scanner sc = new Scanner(curText);
				while (sc.hasNextLine()) {
					String temp = sc.nextLine();
					temp = temp.substring(p1.length(), temp.length() - p2.length());// (aaa)
					sb.append(temp).append(CR_LF);
				}
				ta.setText(sb.toString());
			}
		});

		btn[n++].addActionListener(new ActionListener() {// substring2
			public void actionPerformed(ActionEvent ae) {
				String curText = ta.getText();
				StringBuffer sb = new StringBuffer(curText.length());
				preText = curText;
				String p1 = tfParam1.getText();
				String p2 = tfParam2.getText();
				Scanner sc = new Scanner(curText);
				int p1ind = 0, p2ind = 0;
				String temp = "";
				while (sc.hasNextLine()) {
					temp = sc.nextLine();
					for (int i = 0; i < temp.length(); i++) {
						if (temp.charAt(i) + "" == p1) {
							p1ind = i;
						}
						if (temp.charAt(i) + "" == p2) {
							p2ind = i;
						}
					}
				}
				while (sc.hasNextLine()) {
					String temp2 = sc.nextLine();
					sb.append(temp2).append(CR_LF);
				}
				ta.setText(sb.toString());
			}
		});
		
		btn[n++].addActionListener(new ActionListener() {// distinct
			public void actionPerformed(ActionEvent ae) {
				String curText = ta.getText();
				StringBuffer sb = new StringBuffer(curText.length());
				preText = curText;
				Scanner sc=new Scanner(curText);
				HashSet set=new HashSet();
				while(sc.hasNext()) {
					String temp=sc.nextLine();
					set.add(temp);
				}
				ArrayList list=new ArrayList(set);
				java.util.Collections.sort(list);
				for(int i=0;i<list.size();i++) {
					sb.append(list.get(i)).append(CR_LF);
				}
				ta.setText(sb.toString());
			}
		});
		
		btn[n++].addActionListener(new ActionListener() {// distinct2
			public void actionPerformed(ActionEvent ae) {
				String curText = ta.getText();
				StringBuffer sb = new StringBuffer(curText.length());
				preText = curText;
				Scanner sc=new Scanner(curText);
				TreeMap tm=new TreeMap();
				int dis=0;
				String temp="";
				while(sc.hasNext()) {
					temp=sc.nextLine();
					if(tm.containsKey(temp)) { //중복되어 있으면
						tm.replace(temp, dis, dis++);
					}
					else {
						tm.put(temp, dis);
						tm.replace(temp, dis, 1);
					}	
				}
				Set set=tm.entrySet();
				Iterator it=set.iterator();
				int index;
				while(it.hasNext()) {
					Map.Entry e=(Map.Entry)it.next();
					String gubun=e.toString();
					if(tfParam1.getText()!="") {
						gubun=gubun.replace("=", ",");
						System.out.println(gubun);
					}
					else {
						gubun=gubun.replace("=", ",");
					}
					sb.append(gubun).append(CR_LF);
				}
				ta.setText(sb.toString());
			}
		});
		
		btn[n++].addActionListener(new ActionListener() { // undo
			public void actionPerformed(ActionEvent ae) {
				String curText = ta.getText(); 
                StringBuffer sb = new StringBuffer(curText.length()); 
                preText = curText; 
                String pattern = tfParam1.getText(); 
                String delimiter = tfParam2.getText(); 

				//curText를 라인 단위로 읽는다.
                Scanner sc=new Scanner(curText);
                while(sc.hasNextLine()) {
                String temp=sc.nextLine();
                temp.split(tfParam1.getText());
				sb.append(temp).append(CR_LF);
                }
                ta.setText(sb.toString());
			}
		});
	}

	public TextToolEx10(String title) {
		super(title);
		lb1 = new Label("param1:", Label.RIGHT);
		lb2 = new Label("param2", Label.RIGHT);
		tfParam1 = new TextField(15);
		tfParam2 = new TextField(15);

		ta = new TextArea();
		pNorth = new Panel();
		pSouth = new Panel();

		for (int i = 0; i < btn.length; i++) {
			btn[i] = new Button(btnName[i]);
		}

		pNorth.setLayout(new FlowLayout());
		pNorth.add(lb1);
		pNorth.add(tfParam1);
		pNorth.add(lb2);
		pNorth.add(tfParam2);

		pSouth.setLayout(new GridLayout(2, 10));
		for (int i = 0; i < btn.length; i++) {
			pSouth.add(btn[i]);
		}

		add(pNorth, "North");
		add(ta, "Center");
		add(pSouth, "South");

		setBounds(100, 100, 600, 300);
		ta.requestFocus();
		registerEventHandler();
		setVisible(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TextToolEx10 ex9 = new TextToolEx10("TextTool");
		ex9.show();
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		e.getWindow().setVisible(false);
		e.getWindow().dispose();
		System.exit(0);
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}
}
