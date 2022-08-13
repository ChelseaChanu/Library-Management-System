package jFrame;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.MatteBorder;
import java.awt.Rectangle;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.BevelBorder;

public class HomePage extends JFrame {
	private JTextField txtX;
	private JTextField textField;
	private JTable table;
	private JTextField txtStudentDetails;
	private JTextField txtBookDetails;
	private JTable table_1;

	
	Color mouseEnterColor = new Color(32,178,170);
	Color mouseExitColor = new Color(173,216,230);
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomePage frame = new HomePage();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
					frame.setSize(new Dimension(1450,800));
					frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public HomePage() {
		getContentPane().setBackground(new Color(255, 255, 255));
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1404, 751);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 139, 139));
		panel.setBounds(0, 0, 1450, 69);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		txtX = new JTextField();
		txtX.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		txtX.setBorder(null);
		txtX.setBackground(new Color(0, 139, 139));
		txtX.setHorizontalAlignment(SwingConstants.CENTER);
		txtX.setFont(new Font("Verdana", Font.PLAIN, 20));
		txtX.setText("X");
		txtX.setForeground(new Color(0, 0, 0));
		txtX.setEditable(false);
		txtX.setBounds(1342, 10, 53, 49);
		panel.add(txtX);
		txtX.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(HomePage.class.getResource("/adminIcons/adminIcons/icons8_menu_48px_1.png")));
		lblNewLabel.setBorder(null);
		lblNewLabel.setBounds(21, 10, 48, 49);
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(80, 19, 2, 40);
		panel.add(textField);
		textField.setBorder(null);
		textField.setEditable(false);
		textField.setBackground(new Color(0, 0, 0));
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Library Management System");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setFont(new Font("Microsoft YaHei UI Light", Font.BOLD, 20));
		lblNewLabel_1.setBounds(114, 18, 284, 40);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("   Welcome, Admin");
		lblNewLabel_1_1.setIcon(new ImageIcon(HomePage.class.getResource("/adminIcons/adminIcons/male_user_50px.png")));
		lblNewLabel_1_1.setFont(new Font("Microsoft YaHei UI Light", Font.BOLD, 20));
		lblNewLabel_1_1.setBounds(1027, 10, 254, 49);
		panel.add(lblNewLabel_1_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(173, 216, 230));
		panel_1.setBorder(null);
		panel_1.setBounds(0, 68, 270, 733);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(32, 178, 170));
		panel_2.setBorder(null);
		panel_2.setBounds(0, 39, 270, 55);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("    Home Page");
		lblNewLabel_2.setBounds(32, 10, 181, 35);
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2.setIcon(new ImageIcon(HomePage.class.getResource("/adminIcons/adminIcons/icons8_Home_26px_2.png")));
		panel_2.add(lblNewLabel_2);
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setBorder(null);
		panel_2_1.setBackground(new Color(173, 216, 230));
		panel_2_1.setBounds(0, 92, 270, 55);
		panel_1.add(panel_2_1);
		panel_2_1.setLayout(null);
		
		JLabel lblNewLabel_2_1 = new JLabel("    Dashboard");
		lblNewLabel_2_1.setBounds(32, 10, 181, 35);
		lblNewLabel_2_1.setIcon(new ImageIcon(HomePage.class.getResource("/adminIcons/adminIcons/icons8_Library_26px_1.png")));
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2_1.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		panel_2_1.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("    Features");
		lblNewLabel_2_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblNewLabel_2_1_1.setBounds(0, 146, 181, 35);
		panel_1.add(lblNewLabel_2_1_1);
		
		JPanel panel_2_1_1 = new JPanel();
		panel_2_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				panel_2_1_1.setBackground(mouseEnterColor);
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panel_2_1_1.setBackground(mouseExitColor);
			}
		});
		panel_2_1_1.setBorder(null);
		panel_2_1_1.setBackground(new Color(173, 216, 230));
		panel_2_1_1.setBounds(0, 182, 270, 55);
		panel_1.add(panel_2_1_1);
		panel_2_1_1.setLayout(null);
		
		JLabel lblNewLabel_2_1_2 = new JLabel("    Manage Books");
		lblNewLabel_2_1_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ManagePage book = new ManagePage();
				book.setVisible(true);
				dispose();
			}
		});
		lblNewLabel_2_1_2.setBounds(32, 10, 202, 35);
		lblNewLabel_2_1_2.setIcon(new ImageIcon(HomePage.class.getResource("/adminIcons/adminIcons/icons8_Book_26px.png")));
		lblNewLabel_2_1_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2_1_2.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		panel_2_1_1.add(lblNewLabel_2_1_2);
		
		JPanel panel_2_1_2 = new JPanel();
		panel_2_1_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				panel_2_1_2.setBackground(mouseEnterColor);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panel_2_1_2.setBackground(mouseExitColor);
			}
		});
		panel_2_1_2.setBorder(null);
		panel_2_1_2.setBackground(new Color(173, 216, 230));
		panel_2_1_2.setBounds(0, 236, 270, 55);
		panel_1.add(panel_2_1_2);
		panel_2_1_2.setLayout(null);
		
		JLabel lblNewLabel_2_1_3 = new JLabel("    Manage Students");
		lblNewLabel_2_1_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ManageStudents student = new ManageStudents();
				student.setVisible(true);
				dispose();
			}
		});
		lblNewLabel_2_1_3.setBounds(32, 10, 226, 35);
		lblNewLabel_2_1_3.setIcon(new ImageIcon(HomePage.class.getResource("/adminIcons/adminIcons/icons8_Read_Online_26px.png")));
		lblNewLabel_2_1_3.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2_1_3.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		panel_2_1_2.add(lblNewLabel_2_1_3);
		
		JPanel panel_2_1_3 = new JPanel();
		panel_2_1_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				panel_2_1_3.setBackground(mouseEnterColor);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panel_2_1_3.setBackground(mouseExitColor);
			}
		});
		panel_2_1_3.setBorder(null);
		panel_2_1_3.setBackground(new Color(173, 216, 230));
		panel_2_1_3.setBounds(0, 291, 270, 55);
		panel_1.add(panel_2_1_3);
		panel_2_1_3.setLayout(null);
		
		JLabel lblNewLabel_2_1_4 = new JLabel("    Issue Book");
		lblNewLabel_2_1_4.setBounds(32, 10, 181, 35);
		lblNewLabel_2_1_4.setIcon(new ImageIcon(HomePage.class.getResource("/adminIcons/adminIcons/icons8_Sell_26px.png")));
		lblNewLabel_2_1_4.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2_1_4.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		panel_2_1_3.add(lblNewLabel_2_1_4);
		
		JPanel panel_2_1_4 = new JPanel();
		panel_2_1_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				panel_2_1_4.setBackground(mouseEnterColor);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panel_2_1_4.setBackground(mouseExitColor);
			}
		});
		panel_2_1_4.setBorder(null);
		panel_2_1_4.setBackground(new Color(173, 216, 230));
		panel_2_1_4.setBounds(0, 346, 270, 55);
		panel_1.add(panel_2_1_4);
		panel_2_1_4.setLayout(null);
		
		JLabel lblNewLabel_2_1_5 = new JLabel("    Return Book");
		lblNewLabel_2_1_5.setBounds(32, 10, 181, 35);
		lblNewLabel_2_1_5.setIcon(new ImageIcon(HomePage.class.getResource("/adminIcons/adminIcons/icons8_Return_Purchase_26px.png")));
		lblNewLabel_2_1_5.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2_1_5.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		panel_2_1_4.add(lblNewLabel_2_1_5);
		
		JPanel panel_2_1_5 = new JPanel();
		panel_2_1_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				panel_2_1_5.setBackground(mouseEnterColor);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panel_2_1_5.setBackground(mouseExitColor);
			}
		});
		panel_2_1_5.setBorder(null);
		panel_2_1_5.setBackground(new Color(173, 216, 230));
		panel_2_1_5.setBounds(0, 400, 270, 55);
		panel_1.add(panel_2_1_5);
		panel_2_1_5.setLayout(null);
		
		JLabel lblNewLabel_2_1_6 = new JLabel("    View Records");
		lblNewLabel_2_1_6.setBounds(32, 10, 181, 35);
		lblNewLabel_2_1_6.setIcon(new ImageIcon(HomePage.class.getResource("/adminIcons/adminIcons/icons8_View_Details_26px.png")));
		lblNewLabel_2_1_6.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2_1_6.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		panel_2_1_5.add(lblNewLabel_2_1_6);
		
		JPanel panel_2_1_6 = new JPanel();
		panel_2_1_6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				panel_2_1_6.setBackground(mouseEnterColor);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panel_2_1_6.setBackground(mouseExitColor);
			}
		});
		panel_2_1_6.setBorder(null);
		panel_2_1_6.setBackground(new Color(173, 216, 230));
		panel_2_1_6.setBounds(0, 454, 270, 55);
		panel_1.add(panel_2_1_6);
		panel_2_1_6.setLayout(null);
		
		JLabel lblNewLabel_2_1_7 = new JLabel("    View Issued Books");
		lblNewLabel_2_1_7.setBounds(32, 10, 234, 35);
		lblNewLabel_2_1_7.setIcon(new ImageIcon(HomePage.class.getResource("/adminIcons/adminIcons/icons8_Books_26px.png")));
		lblNewLabel_2_1_7.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2_1_7.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		panel_2_1_6.add(lblNewLabel_2_1_7);
		
		JPanel panel_2_1_5_1 = new JPanel();
		panel_2_1_5_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				panel_2_1_5_1.setBackground(mouseEnterColor);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panel_2_1_5_1.setBackground(mouseExitColor);
			}		
			
		});
		panel_2_1_5_1.setBorder(null);
		panel_2_1_5_1.setBackground(new Color(173, 216, 230));
		panel_2_1_5_1.setBounds(0, 507, 270, 55);
		panel_1.add(panel_2_1_5_1);
		panel_2_1_5_1.setLayout(null);
		
		JLabel lblNewLabel_2_1_6_1 = new JLabel("    Defaulter List");
		lblNewLabel_2_1_6_1.setBounds(32, 10, 181, 35);
		lblNewLabel_2_1_6_1.setIcon(new ImageIcon(HomePage.class.getResource("/adminIcons/adminIcons/icons8_Conference_26px.png")));
		lblNewLabel_2_1_6_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2_1_6_1.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		panel_2_1_5_1.add(lblNewLabel_2_1_6_1);
		
		JPanel panel_2_1_5_2 = new JPanel();
		panel_2_1_5_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				panel_2_1_5_2.setBackground(mouseEnterColor);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panel_2_1_5_2.setBackground(mouseExitColor);
			}	
		});
		panel_2_1_5_2.setBorder(null);
		panel_2_1_5_2.setBackground(new Color(173, 216, 230));
		panel_2_1_5_2.setBounds(0, 580, 270, 55);
		panel_1.add(panel_2_1_5_2);
		panel_2_1_5_2.setLayout(null);
		
		JLabel lblNewLabel_2_1_6_2 = new JLabel("    Logout");
		lblNewLabel_2_1_6_2.setBounds(32, 10, 181, 35);
		lblNewLabel_2_1_6_2.setIcon(new ImageIcon(HomePage.class.getResource("/adminIcons/adminIcons/icons8_Exit_26px.png")));
		lblNewLabel_2_1_6_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2_1_6_2.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		panel_2_1_5_2.add(lblNewLabel_2_1_6_2);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBackground(Color.WHITE);
		panel_3.setBounds(268, 68, 1136, 683);
		getContentPane().add(panel_3);
		
		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBounds(new Rectangle(0, 0, 260, 140));
		panel_4.setBorder(new MatteBorder(15, 0, 0, 0, (Color) new Color(32, 178, 170)));
		panel_4.setBounds(38, 71, 244, 142);
		panel_3.add(panel_4);
		
		JLabel lblNewLabel_3_1 = new JLabel("   10");
		lblNewLabel_3_1.setIcon(new ImageIcon(HomePage.class.getResource("/adminIcons/adminIcons/icons8_Book_Shelf_50px.png")));
		lblNewLabel_3_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_1.setForeground(Color.GRAY);
		lblNewLabel_3_1.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 30));
		lblNewLabel_3_1.setBounds(49, 46, 145, 55);
		panel_4.add(lblNewLabel_3_1);
		
		JLabel lblNewLabel_3 = new JLabel("No. of Books");
		lblNewLabel_3.setForeground(Color.GRAY);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_3.setBounds(38, 35, 200, 26);
		panel_3.add(lblNewLabel_3);
		
		JPanel panel_4_1 = new JPanel();
		panel_4_1.setLayout(null);
		panel_4_1.setBounds(new Rectangle(0, 0, 260, 140));
		panel_4_1.setBorder(new MatteBorder(15, 0, 0, 0, (Color) new Color(32, 178, 170)));
		panel_4_1.setBounds(313, 71, 244, 142);
		panel_3.add(panel_4_1);
		
		JLabel lblNewLabel_3_1_1 = new JLabel("   10");
		lblNewLabel_3_1_1.setIcon(new ImageIcon(HomePage.class.getResource("/AddNewBookIcons/adminIcons/adminIcons/icons8_People_50px.png")));
		lblNewLabel_3_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_1_1.setForeground(Color.GRAY);
		lblNewLabel_3_1_1.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 30));
		lblNewLabel_3_1_1.setBounds(49, 47, 145, 55);
		panel_4_1.add(lblNewLabel_3_1_1);
		
		JLabel lblNewLabel_3_2 = new JLabel("No. of Students");
		lblNewLabel_3_2.setForeground(Color.GRAY);
		lblNewLabel_3_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_3_2.setBounds(313, 35, 200, 26);
		panel_3.add(lblNewLabel_3_2);
		
		JPanel panel_4_1_1 = new JPanel();
		panel_4_1_1.setLayout(null);
		panel_4_1_1.setBounds(new Rectangle(0, 0, 260, 140));
		panel_4_1_1.setBorder(new MatteBorder(15, 0, 0, 0, (Color) new Color(32, 178, 170)));
		panel_4_1_1.setBounds(592, 71, 244, 142);
		panel_3.add(panel_4_1_1);
		
		JLabel lblNewLabel_3_1_1_1 = new JLabel("   10");
		lblNewLabel_3_1_1_1.setIcon(new ImageIcon(HomePage.class.getResource("/adminIcons/adminIcons/icons8_Sell_50px.png")));
		lblNewLabel_3_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_1_1_1.setForeground(Color.GRAY);
		lblNewLabel_3_1_1_1.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 30));
		lblNewLabel_3_1_1_1.setBounds(49, 47, 145, 55);
		panel_4_1_1.add(lblNewLabel_3_1_1_1);
		
		JLabel lblNewLabel_3_2_1 = new JLabel("Issued Books");
		lblNewLabel_3_2_1.setForeground(Color.GRAY);
		lblNewLabel_3_2_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_3_2_1.setBounds(592, 35, 200, 26);
		panel_3.add(lblNewLabel_3_2_1);
		
		JPanel panel_4_1_2 = new JPanel();
		panel_4_1_2.setLayout(null);
		panel_4_1_2.setBounds(new Rectangle(0, 0, 260, 140));
		panel_4_1_2.setBorder(new MatteBorder(15, 0, 0, 0, (Color) new Color(32, 178, 170)));
		panel_4_1_2.setBounds(865, 71, 244, 142);
		panel_3.add(panel_4_1_2);
		
		JLabel lblNewLabel_3_1_1_2 = new JLabel("   10");
		lblNewLabel_3_1_1_2.setIcon(new ImageIcon(HomePage.class.getResource("/adminIcons/adminIcons/icons8_List_of_Thumbnails_50px.png")));
		lblNewLabel_3_1_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_1_1_2.setForeground(Color.GRAY);
		lblNewLabel_3_1_1_2.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 30));
		lblNewLabel_3_1_1_2.setBounds(49, 47, 145, 55);
		panel_4_1_2.add(lblNewLabel_3_1_1_2);
		
		JLabel lblNewLabel_3_2_2 = new JLabel("Defaulter");
		lblNewLabel_3_2_2.setForeground(Color.GRAY);
		lblNewLabel_3_2_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_3_2_2.setBounds(865, 35, 200, 26);
		panel_3.add(lblNewLabel_3_2_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		scrollPane.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(211, 211, 211), null, new Color(211, 211, 211), null));
		scrollPane.setBounds(38, 271, 761, 148);
		panel_3.add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("Arial", Font.PLAIN, 14));
		scrollPane.setViewportView(table);
		table.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(192, 192, 192), null, null, null));
		table.setRowMargin(5);
		table.setRowHeight(30);
		table.setEditingColumn(4);
		table.setEditingRow(5);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"1", "Chelsea", "Web Development", "18"},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"Student_Id", "Name", "Course", "Branch"
			}
		));
		table.getColumnModel().getColumn(1).setPreferredWidth(96);
		table.getColumnModel().getColumn(2).setPreferredWidth(96);
		
		txtStudentDetails = new JTextField();
		txtStudentDetails.setBorder(null);
		txtStudentDetails.setFont(new Font("Times New Roman", Font.BOLD, 18));
		txtStudentDetails.setText("Student Details");
		txtStudentDetails.setBounds(38, 239, 161, 34);
		panel_3.add(txtStudentDetails);
		txtStudentDetails.setColumns(10);
		
		txtBookDetails = new JTextField();
		txtBookDetails.setText("Book Details");
		txtBookDetails.setFont(new Font("Times New Roman", Font.BOLD, 18));
		txtBookDetails.setColumns(10);
		txtBookDetails.setBorder(null);
		txtBookDetails.setBounds(38, 439, 161, 34);
		panel_3.add(txtBookDetails);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		scrollPane_1.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(211, 211, 211), null, new Color(211, 211, 211), null));
		scrollPane_1.setBounds(38, 483, 756, 148);
		panel_3.add(scrollPane_1);
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
				{"1", null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"Book_Id", "Name", "Author", "Quantity"
			}
		));
		table_1.setRowMargin(5);
		table_1.setRowHeight(30);
		table_1.setEditingRow(5);
		table_1.setEditingColumn(4);
		table_1.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(192, 192, 192), null, null, null));
		scrollPane_1.setViewportView(table_1);
		
		JLabel lblNewLabel_4 = new JLabel("\r\n");
		Image img = new ImageIcon (this.getClass().getResource("/image2.jpg")).getImage();
		lblNewLabel_4.setIcon(new ImageIcon(img));
		lblNewLabel_4.setBounds(818, 230, 318, 453);
		panel_3.add(lblNewLabel_4);
	}	
}
