package jFrame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginPage extends JFrame {

	private JPanel contentPane;
	private JTextField txtLogin;
	private JTextField textPassword;
	private JTextField textUsername;
	private JTextField txtUsername_1;
	private JTextField txtPassword;
	private JTextField txtWelcome;
	private JTextField txtTheMore;
	private JTextField txtTheMoreYou;
	private JTextField txtTheMore_1;
	private JTextField txtTheLittleYou;
	private JTextField txtNewHere;
	private JButton btnSignUp;
	private JTextField txtX;

	//validation
	public boolean validateLogin() {
		String name = textUsername.getText();
		String pwd = textPassword.getText();
		
		if(name.equals("")) {
			JOptionPane.showMessageDialog(this, "Please enter username");
			return false;
		}
		
		if(pwd.equals("")) {
			JOptionPane.showMessageDialog(this, "Please enter password");
			return false;
		}
		
		return true;
	}
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginPage frame = new LoginPage();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
					frame.setSize(new Dimension(1200,600));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	//verify credential
	public void login() {
		String name = textUsername.getText();
		String pwd = textPassword.getText();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
        	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_manag","root","Chel4@sea");
        	
        	PreparedStatement pst = con.prepareStatement("select * from users where name =? and password=?");
        	pst.setString(1, name);
        	pst.setString(2, pwd);
        	
        	ResultSet rs = pst.executeQuery();
        	if(rs.next()) {
        		JOptionPane.showMessageDialog(this, "Login successful");
        		HomePage home = new HomePage();
        		home.setVisible(true);
        		this.dispose();
        	}
        	else {
        		JOptionPane.showMessageDialog(this, "Incorrect username or password");
        	}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Create the frame.
	 */
	public LoginPage() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1325, 719);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 670, 628);
		contentPane.add(panel);
		panel.setLayout(null);
		
		txtLogin = new JTextField();
		txtLogin.setEditable(false);
		txtLogin.setBackground(new Color(255, 255, 255));
		txtLogin.setBorder(null);
		txtLogin.setForeground(new Color(47, 79, 79));
		txtLogin.setFont(new Font("Bookman Old Style", Font.BOLD, 25));
		txtLogin.setText("Login to Your Account");
		txtLogin.setHorizontalAlignment(SwingConstants.CENTER);
		txtLogin.setBounds(140, 42, 373, 57);
		panel.add(txtLogin);
		txtLogin.setColumns(10);
		
		textPassword = new JTextField();
		textPassword.setBackground(new Color(240, 255, 255));
		textPassword.setHorizontalAlignment(SwingConstants.CENTER);
		textPassword.setForeground(Color.BLACK);
		textPassword.setFont(new Font("Arial", Font.PLAIN, 14));
		textPassword.setColumns(10);
		textPassword.setBorder(null);
		textPassword.setBounds(158, 370, 355, 45);
		panel.add(textPassword);
		
		textUsername = new JTextField();
		textUsername.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				
			}
		});
		textUsername.setHorizontalAlignment(SwingConstants.CENTER);
		textUsername.setForeground(Color.BLACK);
		textUsername.setFont(new Font("Arial", Font.PLAIN, 14));
		textUsername.setColumns(10);
		textUsername.setBorder(null);
		textUsername.setBackground(new Color(240, 255, 255));
		textUsername.setBounds(158, 226, 355, 45);
		panel.add(textUsername);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(validateLogin())
					login();
			}
		});
		btnNewButton.setBorder(null);
		btnNewButton.setBackground(new Color(95, 158, 160));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("Dubai", Font.BOLD, 26));
		btnNewButton.setBounds(196, 489, 287, 39);
		panel.add(btnNewButton);
		
		txtUsername_1 = new JTextField();
		txtUsername_1.setBackground(new Color(255, 255, 255));
		txtUsername_1.setEditable(false);
		txtUsername_1.setBorder(null);
		txtUsername_1.setForeground(Color.BLACK);
		txtUsername_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtUsername_1.setText("User Name");
		txtUsername_1.setBounds(103, 169, 233, 33);
		panel.add(txtUsername_1);
		txtUsername_1.setColumns(10);
		
		txtPassword = new JTextField();
		txtPassword.setBackground(new Color(255, 255, 255));
		txtPassword.setEditable(false);
		txtPassword.setText("Password");
		txtPassword.setForeground(Color.BLACK);
		txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtPassword.setColumns(10);
		txtPassword.setBorder(null);
		txtPassword.setBounds(103, 316, 233, 33);
		panel.add(txtPassword);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(null);
		panel_1.setBackground(new Color(0, 139, 139));
		panel_1.setBounds(671, 0, 555, 628);
		contentPane.add(panel_1);
		
		txtWelcome = new JTextField();
		txtWelcome.setEditable(false);
		txtWelcome.setText("Welcome ...");
		txtWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		txtWelcome.setForeground(new Color(255, 255, 255));
		txtWelcome.setFont(new Font("Bookman Old Style", Font.BOLD, 25));
		txtWelcome.setColumns(10);
		txtWelcome.setBorder(null);
		txtWelcome.setBackground(new Color(0, 139, 139));
		txtWelcome.setBounds(94, 103, 373, 55);
		panel_1.add(txtWelcome);
		
		txtTheMore = new JTextField();
		txtTheMore.setEditable(false);
		txtTheMore.setText("\" The more you read \r\n");
		txtTheMore.setHorizontalAlignment(SwingConstants.CENTER);
		txtTheMore.setForeground(new Color(255, 255, 255));
		txtTheMore.setFont(new Font("Garamond", Font.PLAIN, 25));
		txtTheMore.setColumns(10);
		txtTheMore.setBorder(null);
		txtTheMore.setBackground(new Color(0, 139, 139));
		txtTheMore.setBounds(119, 201, 344, 45);
		panel_1.add(txtTheMore);
		
		txtTheMoreYou = new JTextField();
		txtTheMoreYou.setEditable(false);
		txtTheMoreYou.setText("the more you learn.");
		txtTheMoreYou.setHorizontalAlignment(SwingConstants.LEFT);
		txtTheMoreYou.setForeground(new Color(255, 255, 255));
		txtTheMoreYou.setFont(new Font("Garamond", Font.PLAIN, 25));
		txtTheMoreYou.setColumns(10);
		txtTheMoreYou.setBorder(null);
		txtTheMoreYou.setBackground(new Color(0, 139, 139));
		txtTheMoreYou.setBounds(119, 256, 344, 45);
		panel_1.add(txtTheMoreYou);
		
		txtTheMore_1 = new JTextField();
		txtTheMore_1.setEditable(false);
		txtTheMore_1.setText("\" The more you learn\r\n");
		txtTheMore_1.setHorizontalAlignment(SwingConstants.CENTER);
		txtTheMore_1.setForeground(new Color(255, 255, 255));
		txtTheMore_1.setFont(new Font("Garamond", Font.PLAIN, 25));
		txtTheMore_1.setColumns(10);
		txtTheMore_1.setBorder(null);
		txtTheMore_1.setBackground(new Color(0, 139, 139));
		txtTheMore_1.setBounds(119, 311, 344, 45);
		panel_1.add(txtTheMore_1);
		
		txtTheLittleYou = new JTextField();
		txtTheLittleYou.setEditable(false);
		txtTheLittleYou.setText("the little you know.\"");
		txtTheLittleYou.setHorizontalAlignment(SwingConstants.LEFT);
		txtTheLittleYou.setForeground(new Color(255, 255, 255));
		txtTheLittleYou.setFont(new Font("Garamond", Font.PLAIN, 25));
		txtTheLittleYou.setColumns(10);
		txtTheLittleYou.setBorder(null);
		txtTheLittleYou.setBackground(new Color(0, 139, 139));
		txtTheLittleYou.setBounds(119, 366, 344, 45);
		panel_1.add(txtTheLittleYou);
		
		txtNewHere = new JTextField();
		txtNewHere.setEditable(false);
		txtNewHere.setText("New Here?");
		txtNewHere.setHorizontalAlignment(SwingConstants.CENTER);
		txtNewHere.setForeground(Color.WHITE);
		txtNewHere.setFont(new Font("Garamond", Font.BOLD, 28));
		txtNewHere.setColumns(10);
		txtNewHere.setBorder(null);
		txtNewHere.setBackground(new Color(0, 139, 139));
		txtNewHere.setBounds(108, 460, 344, 45);
		panel_1.add(txtNewHere);
		
		btnSignUp = new JButton("Sign Up");
		btnSignUp.setForeground(new Color(47, 79, 79));
		btnSignUp.setFont(new Font("Dubai", Font.BOLD, 20));
		btnSignUp.setBorder(null);
		btnSignUp.setBackground(new Color(255, 255, 255));
		btnSignUp.setBounds(148, 515, 274, 33);
		panel_1.add(btnSignUp);
		
		txtX = new JTextField();
		txtX.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		txtX.setBorder(null);
		txtX.setForeground(new Color(255, 255, 255));
		txtX.setHorizontalAlignment(SwingConstants.CENTER);
		txtX.setEditable(false);
		txtX.setText("X\r\n");
		txtX.setFont(new Font("OCR A Extended", Font.PLAIN, 16));
		txtX.setBackground(new Color(0, 139, 139));
		txtX.setBounds(444, 39, 40, 33);
		panel_1.add(txtX);
		txtX.setColumns(10);
	}
}
