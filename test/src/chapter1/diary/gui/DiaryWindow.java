package chapter1.diary.gui;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import chapter1.diary.bean.Diary;
import chapter1.diary.bean.User;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Rectangle;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.util.GregorianCalendar;
import java.util.Vector;

import javax.swing.JButton;
import java.awt.Dimension;

public class DiaryWindow extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JMenu mnuFile = null;
	private JMenuItem mnitem=null;
	private JMenuBar mnb=null; 

	private ImageIcon ic=null; 	

	private JLabel jLabel1 = null;

	private JLabel jLabel2 = null;

	private JLabel jLabel3 = null;

	private JScrollPane jScrollPane = null;

	private JTable jTable = null;

	private User user;
	private Vector<Diary> list=new Vector<Diary>() ;
	private JPanel jPanel = null;

	private JScrollPane jScrollPane1 = null;

	private JTextArea jTextArea = null;

	private JTextField jTextField = null;

	private JButton jButton = null;

	private JButton jButton1 = null;

	private JButton jButton2 = null;

	/**
	 * This is the default constructor
	 */
	public DiaryWindow(User user) {
		this.user = user;
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(429, 408);
		this.setContentPane(getJContentPane());
		this.setTitle("JFrame");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel3 = new JLabel();
			jLabel3.setText("新日记内容");
			jLabel3.setBounds(new Rectangle(2, 20, 66, 24));
			jLabel2 = new JLabel();
			jLabel2.setText("新日记标题");
			jLabel2.setBounds(new Rectangle(0, 2, 65, 18));
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(3, 31, 219, 23));
			jLabel1.setText("日记列表(双击相应日记查看)");
			JMenuBar mnb = new JMenuBar();
			mnuFile = new JMenu("选项");
			mnitem=new JMenuItem("主人信息");
			
			mnitem.addMouseListener(new java.awt.event.MouseAdapter() 
			{ 
			  public   void   mouseReleased(java.awt.event.MouseEvent   e) 
			{ 

			if   (e.getClickCount()   ==   1 ) 
			{ 
		      String name=user.getName();
		    	String gender=user.getGender();
		    	int  age=user.getAge();
		    	if(gender.equals("男")){
		    	ic = new ImageIcon(getClass().getResource("boy.png"));
			    }else if(gender.equals("女")){
			    	ic = new ImageIcon(getClass().getResource("girl.png"));
			    }
		    	JOptionPane.showMessageDialog(null, 
		    	   "姓名: "+name+"\n"+"性别: "+gender+"\n"+"年龄: "+age, "日记主人信息", JOptionPane.WARNING_MESSAGE, ic);
		    	 }

			}});
			mnuFile.add(mnitem);
			mnb.add(mnuFile);


			mnb.setBounds(new Rectangle(3, 5, 53, 25));
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(mnb, null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(getJScrollPane(), null);
			jContentPane.add(getJPanel(), null);
			jContentPane.add(getJButton(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBounds(new Rectangle(1, 52, 383, 86));
			jScrollPane.setViewportView(getJTable());
		}
		return jScrollPane;
	}

	/**
	 * This method initializes jTable	
	 * 	
	 * @return javax.swing.JTable	
	 */
	@SuppressWarnings("unchecked")
	private JTable getJTable() {
		if (jTable == null) {
			
			
			Vector<String> data = new Vector<String>();
			data.add("作者");
			data.add("内容");
			data.add("标题");
			data.add("时间");
			
			Vector<Vector<String>> list2 = new Vector<Vector<String>>();
			
			for(int i=0;i<list.size();i++){
				Vector<String> t = new Vector<String>();
				Diary t1 = list.get(i);
				t.add(t1.getTitle());
				t.add(t1.getContent());
				t.add(t1.getAuthorName());
				
				GregorianCalendar time = t1.getTime();
				int year = time.get(GregorianCalendar.YEAR);
				int month = time.get(GregorianCalendar.MONTH)+1;
				int day = time.get(GregorianCalendar.DATE);
				int hour = time.get(GregorianCalendar.HOUR_OF_DAY);
				int minute = time.get(GregorianCalendar.MINUTE);
				
				t.add(year + "-" + month + "-" + day + " " + hour + ":" + minute);
				
				list2.add(t);
			}
			jTable = new JTable(list2,data);
			jTable.addMouseListener(new java.awt.event.MouseAdapter() 
			{ 
			  public   void   mouseReleased(java.awt.event.MouseEvent   e) 
			{ 

			//是否左建双击 
			if   (e.getClickCount()   ==   2 ) 
			{ 
		       System.out.println("点击！");
		       int selectRow = jTable.getSelectedRow(); //获得鼠标选定的行
		       String title = (String)jTable.getValueAt(selectRow,2); //获得选定行的想要的列的值
		       String value = (String)jTable.getValueAt(selectRow,1); //获得选定行的想要的列的值
		      JOptionPane.showMessageDialog(null,"标题：   "+title+"\n"+"内容：   "+value,"日志详情", JOptionPane.PLAIN_MESSAGE);
			    	
			}}}); 
			 


			
		}
		return jTable;
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			jPanel = new JPanel();
			jPanel.setLayout(null);
			jPanel.setBounds(new Rectangle(4, 162, 408, 208));
			jPanel.add(jLabel2, null);
			jPanel.add(jLabel3, null);
			jPanel.add(getJScrollPane1(), null);
			jPanel.add(getJTextField(), null);
			jPanel.setVisible(false);
			jPanel.add(getJButton1(), null);
			jPanel.add(getJButton2(), null);
		}
		return jPanel;
	}

	/**
	 * This method initializes jScrollPane1	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane1() {
		if (jScrollPane1 == null) {
			jScrollPane1 = new JScrollPane();
			jScrollPane1.setBounds(new Rectangle(6, 45, 390, 109));
			jScrollPane1.setViewportView(getJTextArea());
		}
		return jScrollPane1;
	}

	/**
	 * This method initializes jTextArea	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getJTextArea() {
		if (jTextArea == null) {
			jTextArea = new JTextArea();
		}
		return jTextArea;
	}

	/**
	 * This method initializes jTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField() {
		if (jTextField == null) {
			jTextField = new JTextField();
			jTextField.setBounds(new Rectangle(64, 0, 318, 22));
		}
		return jTextField;
	}

	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setBounds(new Rectangle(139, 140, 92, 20));
			jButton.setText("写新日记");
			jButton.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					
					jPanel.setVisible(true);
				}
				
			});
		}
		return jButton;
	}

	/**
	 * This method initializes jButton1	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton1() {
		if (jButton1 == null) {
			jButton1 = new JButton();
			jButton1.setBounds(new Rectangle(63, 169, 66, 28));
			jButton1.setText("保存");
			jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					
					int result = JOptionPane.showConfirmDialog(null, "确定保存这篇日记？");
					if(result == JOptionPane.YES_OPTION){
						GregorianCalendar time = new GregorianCalendar();
						
						Diary diary = new Diary(user.getName(), jTextArea.getText(), time, jTextField.getText());
						list.add(diary);
						JOptionPane.showMessageDialog(null, "日记保存成功");
						jTextField.setText("");
						jTextArea.setText("");
						jTable = null;					
						jScrollPane.setViewportView(getJTable());
					}
					
					
				}
			});
		}
		return jButton1;
	}

	/**
	 * This method initializes jButton2	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton2() {
		if (jButton2 == null) {
			jButton2 = new JButton();
			jButton2.setBounds(new Rectangle(237, 167, 66, 28));
			jButton2.setText("放弃");
		}
		return jButton2;
	}
	

}  //  @jve:decl-index=0:visual-constraint="245,-13"
