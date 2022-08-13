package jFrame;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class SignInPage extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtLogin;
	private JTextField textPassword;
	private JTextField textUsername;
	private JTextField txtUsername_1;
	private JTextField txtPassword;
	private JTextField txtTheMore;
	private JTextField txtTheMoreYou;
	private JTextField txtTheMore_1;
	private JButton btnSignUp;
	private JTextField txtX;
	private JTextField txtAccelarateYourKnowledge;
	private JTextField txtEmail;
	private JTextField txtContact;
	private JTextField textMail;
	private JTextField textContact;
	private JTextField txtHelloFriend;
	private JTextField txtEnjoy;

	
	//method to insert values in users table
	public void insertSignUpDetails() {
		String name = textUsername.getText();
		String pwd = textPassword.getText();
		String mail = textMail.getText();
		String contact = textContact.getText();
		
		try {
			Connection con = DBConnection.getConnection();
			String sql = "insert into users(name,password,mail,contact) values(?,?,?,?)";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, name);
			pst.setString(2, pwd);
			pst.setString(3, mail);
			pst.setString(4, contact);
			
			int updatedRowCount = pst.executeUpdate();
			if(updatedRowCount>0) {
				JOptionPane.showMessageDialog(this, "Record Inserted Successfully");
				LoginPage page = new LoginPage();
				page.setVisible(true);
				dispose();
			}
			else {
				JOptionPane.showMessageDialog(this, "Insertion Failure");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//validation
	public boolean validateSignUp() {
		String name = textUsername.getText();
		String pwd = textPassword.getText();
		String mail = textMail.getText();
		String contact = textContact.getText();
		
		if(name.equals("")) {
			JOptionPane.showMessageDialog(this, "Please enter username");
			return false;
		}
		
		if(pwd.equals("")) {
			JOptionPane.showMessageDialog(this, "Please enter password");
			return false;
		}
		
		if(mail.equals("") || !mail.matches("^(.+)@(.+)$")) {
			JOptionPane.showMessageDialog(this, "Please enter valid email");
			return false;
		}
		
		if(contact.equals("")) {
			JOptionPane.showMessageDialog(this, "Please enter contact");
			return false;
		}
		
		return true;
	}
	
	//check duplicate user
	public boolean checkDuplicateUser() {
		String name = textUsername.getText();
		boolean isExist = false;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
        	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_manag","root","Chel4@sea");
        	
        	PreparedStatement pst = con.prepareStatement("select * from users where name =?");
        	pst.setString(1, name);
        	ResultSet rs = pst.executeQuery();
        	if(rs.next()) {
        		isExist = true;
        	}
        	else {
        		isExist = false;
        	}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return isExist;
	}
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignInPage frame = new SignInPage();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
					frame.setSize(new Dimension(1200,600));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SignInPage() {
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
		panel.setBounds(0, 0, 700, 719);
		contentPane.add(panel);
		panel.setLayout(null);
		
		txtLogin = new JTextField();
		txtLogin.setEditable(false);
		txtLogin.setBackground(new Color(255, 255, 255));
		txtLogin.setBorder(null);
		txtLogin.setForeground(new Color(47, 79, 79));
		txtLogin.setFont(new Font("Bookman Old Style", Font.BOLD, 25));
		txtLogin.setText("Sign Up\r\n");
		txtLogin.setHorizontalAlignment(SwingConstants.CENTER);
		txtLogin.setBounds(140, 20, 371, 45);
		panel.add(txtLogin);
		txtLogin.setColumns(10);
		
		textPassword = new JTextField();
		textPassword.setBackground(new Color(240, 255, 255));
		textPassword.setHorizontalAlignment(SwingConstants.CENTER);
		textPassword.setForeground(Color.BLACK);
		textPassword.setFont(new Font("Arial", Font.PLAIN, 14));
		textPassword.setColumns(10);
		textPassword.setBorder(null);
		textPassword.setBounds(140, 245, 355, 33);
		panel.add(textPassword);
		
		textUsername = new JTextField();
		textUsername.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(checkDuplicateUser()==true) {
					JOptionPane.showMessageDialog(null, "user already exists");
				}
			}
		});
		textUsername.setHorizontalAlignment(SwingConstants.CENTER);
		textUsername.setForeground(Color.BLACK);
		textUsername.setFont(new Font("Arial", Font.PLAIN, 14));
		textUsername.setColumns(10);
		textUsername.setBorder(null);
		textUsername.setBackground(new Color(240, 255, 255));
		textUsername.setBounds(140, 159, 355, 33);
		panel.add(textUsername);
		
		JButton btnNewButton = new JButton("Sign Up");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(validateSignUp()==true) {
					if(checkDuplicateUser()==false)
						insertSignUpDetails();
					else
						JOptionPane.showMessageDialog(null, "username already exists");
				}
			}
		});
		btnNewButton.setBorder(null);
		btnNewButton.setBackground(new Color(95, 158, 160));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("Dubai", Font.BOLD, 26));
		btnNewButton.setBounds(152, 512, 284, 45);
		panel.add(btnNewButton);
		
		txtUsername_1 = new JTextField();
		txtUsername_1.setBackground(new Color(255, 255, 255));
		txtUsername_1.setEditable(false);
		txtUsername_1.setBorder(null);
		txtUsername_1.setForeground(Color.BLACK);
		txtUsername_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtUsername_1.setText("User Name");
		txtUsername_1.setBounds(72, 116, 233, 33);
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
		txtPassword.setBounds(72, 202, 233, 33);
		panel.add(txtPassword);
		
		txtAccelarateYourKnowledge = new JTextField();
		txtAccelarateYourKnowledge.setText("Accelarate Your Knowledge");
		txtAccelarateYourKnowledge.setHorizontalAlignment(SwingConstants.CENTER);
		txtAccelarateYourKnowledge.setForeground(new Color(47, 79, 79));
		txtAccelarateYourKnowledge.setFont(new Font("Bookman Old Style", Font.BOLD, 20));
		txtAccelarateYourKnowledge.setEditable(false);
		txtAccelarateYourKnowledge.setColumns(10);
		txtAccelarateYourKnowledge.setBorder(null);
		txtAccelarateYourKnowledge.setBackground(Color.WHITE);
		txtAccelarateYourKnowledge.setBounds(140, 61, 371, 45);
		panel.add(txtAccelarateYourKnowledge);
		
		txtEmail = new JTextField();
		txtEmail.setText("Email");
		txtEmail.setForeground(Color.BLACK);
		txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtEmail.setEditable(false);
		txtEmail.setColumns(10);
		txtEmail.setBorder(null);
		txtEmail.setBackground(Color.WHITE);
		txtEmail.setBounds(72, 303, 233, 33);
		panel.add(txtEmail);
		
		txtContact = new JTextField();
		txtContact.setText("Contact");
		txtContact.setForeground(Color.BLACK);
		txtContact.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtContact.setEditable(false);
		txtContact.setColumns(10);
		txtContact.setBorder(null);
		txtContact.setBackground(Color.WHITE);
		txtContact.setBounds(72, 389, 233, 33);
		panel.add(txtContact);
		
		textMail = new JTextField();
		textMail.setHorizontalAlignment(SwingConstants.CENTER);
		textMail.setForeground(Color.BLACK);
		textMail.setFont(new Font("Arial", Font.PLAIN, 14));
		textMail.setColumns(10);
		textMail.setBorder(null);
		textMail.setBackground(new Color(240, 255, 255));
		textMail.setBounds(140, 346, 355, 33);
		panel.add(textMail);
		
		textContact = new JTextField();
		textContact.setHorizontalAlignment(SwingConstants.CENTER);
		textContact.setForeground(Color.BLACK);
		textContact.setFont(new Font("Arial", Font.PLAIN, 14));
		textContact.setColumns(10);
		textContact.setBorder(null);
		textContact.setBackground(new Color(240, 255, 255));
		textContact.setBounds(140, 432, 355, 33);
		panel.add(textContact);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(null);
		panel_1.setBackground(new Color(0, 139, 139));
		panel_1.setBounds(699, 0, 626, 719);
		contentPane.add(panel_1);
		
		txtTheMore = new JTextField();
		txtTheMore.setEditable(false);
		txtTheMore.setText("\" Libraries are \r\n");
		txtTheMore.setHorizontalAlignment(SwingConstants.CENTER);
		txtTheMore.setForeground(new Color(255, 255, 255));
		txtTheMore.setFont(new Font("Script MT Bold", Font.PLAIN, 30));
		txtTheMore.setColumns(10);
		txtTheMore.setBorder(null);
		txtTheMore.setBackground(new Color(0, 139, 139));
		txtTheMore.setBounds(79, 200, 344, 45);
		panel_1.add(txtTheMore);
		
		txtTheMoreYou = new JTextField();
		txtTheMoreYou.setEditable(false);
		txtTheMoreYou.setText("the wardrobes");
		txtTheMoreYou.setHorizontalAlignment(SwingConstants.CENTER);
		txtTheMoreYou.setForeground(new Color(255, 255, 255));
		txtTheMoreYou.setFont(new Font("Script MT Bold", Font.PLAIN, 30));
		txtTheMoreYou.setColumns(10);
		txtTheMoreYou.setBorder(null);
		txtTheMoreYou.setBackground(new Color(0, 139, 139));
		txtTheMoreYou.setBounds(79, 255, 344, 45);
		panel_1.add(txtTheMoreYou);
		
		txtTheMore_1 = new JTextField();
		txtTheMore_1.setEditable(false);
		txtTheMore_1.setText(" of Literature \"");
		txtTheMore_1.setHorizontalAlignment(SwingConstants.CENTER);
		txtTheMore_1.setForeground(new Color(255, 255, 255));
		txtTheMore_1.setFont(new Font("Script MT Bold", Font.PLAIN, 30));
		txtTheMore_1.setColumns(10);
		txtTheMore_1.setBorder(null);
		txtTheMore_1.setBackground(new Color(0, 139, 139));
		txtTheMore_1.setBounds(104, 310, 344, 45);
		panel_1.add(txtTheMore_1);
		
		btnSignUp = new JButton("Login");
		btnSignUp.setForeground(new Color(47, 79, 79));
		btnSignUp.setFont(new Font("Dubai", Font.BOLD, 20));
		btnSignUp.setBorder(null);
		btnSignUp.setBackground(new Color(255, 255, 255));
		btnSignUp.setBounds(126, 483, 274, 62);
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
		txtX.setFont(new Font("Verdana", Font.PLAIN, 20));
		txtX.setBackground(new Color(0, 139, 139));
		txtX.setBounds(433, 37, 40, 33);
		panel_1.add(txtX);
		txtX.setColumns(10);
		
		txtHelloFriend = new JTextField();
		txtHelloFriend.setText("Hello, Friend");
		txtHelloFriend.setHorizontalAlignment(SwingConstants.CENTER);
		txtHelloFriend.setForeground(new Color(255, 255, 255));
		txtHelloFriend.setFont(new Font("Bookman Old Style", Font.BOLD, 25));
		txtHelloFriend.setEditable(false);
		txtHelloFriend.setColumns(10);
		txtHelloFriend.setBorder(null);
		txtHelloFriend.setBackground(new Color(0, 139, 139));
		txtHelloFriend.setBounds(104, 84, 326, 72);
		panel_1.add(txtHelloFriend);
		
		txtEnjoy = new JTextField();
		txtEnjoy.setText("Enjoy!");
		txtEnjoy.setHorizontalAlignment(SwingConstants.CENTER);
		txtEnjoy.setForeground(Color.WHITE);
		txtEnjoy.setFont(new Font("Script MT Bold", Font.PLAIN, 30));
		txtEnjoy.setEditable(false);
		txtEnjoy.setColumns(10);
		txtEnjoy.setBorder(null);
		txtEnjoy.setBackground(new Color(0, 139, 139));
		txtEnjoy.setBounds(79, 413, 344, 45);
		panel_1.add(txtEnjoy);
	}
}
