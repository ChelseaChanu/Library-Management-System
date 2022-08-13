package jFrame;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import rojeru_san.componentes.RSDateChooser;
import javax.swing.JButton;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import java.util.Date;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class IssueBooks extends JFrame {

	private JPanel contentPane;
	private JTextField txtBookId;
	private JTextField txtBookName;
	private JTextField txtAuthor;
	private JTextField txtQuan;
	private JTextField txtStuId;
	private JTextField txtCourse;
	private JTextField txtBranch;
	private JTextField txtStuName;
	private JTextField txtBookid;
	private JTextField txtStuid;
	private Object date_issue;
	private Object date_due;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IssueBooks frame = new IssueBooks();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
					frame.setSize(new Dimension(1250,750));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//fetch book details
	public void getBookDetails() {
		int bookId = Integer.parseInt(txtBookid.getText());
		
		try {
			Connection con = DBConnection.getConnection();
			PreparedStatement pst = con.prepareStatement("select * from book_details where book_Id =?");
			pst.setInt(1, bookId);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				txtBookId.setText(rs.getString("book_Id"));
				txtBookName.setText(rs.getString("book_name"));
				txtAuthor.setText(rs.getString("author"));
				txtQuan.setText(rs.getString("quantity"));	
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//fetch book details
		public void getStudentDetails() {
			int studentId = Integer.parseInt(txtStuid.getText());
			
			try {
				Connection con = DBConnection.getConnection();
				PreparedStatement pst = con.prepareStatement("select * from student_details where student_Id =?");
				pst.setInt(1, studentId);
				ResultSet rs = pst.executeQuery();
				
				while(rs.next()) {
					txtStuId.setText(rs.getString("student_Id"));
					txtStuName.setText(rs.getString("name"));
					txtCourse.setText(rs.getString("course"));
					txtBranch.setText(rs.getString("branch"));	
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		//insert issue book details into databse
		public boolean issueBook() {
			boolean isIssued = false;
			int bookId = Integer.parseInt(txtBookId.getText());
			int studentId = Integer.parseInt(txtStuId.getText());
			String bookName = txtBookName.getText();
			String studentName = txtStuName.getText();
			
			Date uIssueDate = ((RSDateChooser) date_issue).getDatoFecha(); 
			Date uDueDate = ((RSDateChooser) date_due).getDatoFecha();
			Long l1 = uIssueDate.getTime();
			Long l2 = uDueDate.getTime();
			java.sql.Date sIssueDate = new java.sql.Date(l1);
			java.sql.Date sDueDate = new java.sql.Date(l2);
			
			try {
				Connection con = DBConnection.getConnection();
				String sql = "insert into issue_book(book_Id,book_name,student_Id,student_name,issue_book,due_date,status) values(?,?,?,?,?,?,?)";
				
				PreparedStatement pst = con.prepareStatement(sql);
				pst.setInt(1, bookId);
				pst.setString(2, bookName);
				pst.setInt(3, studentId);
				pst.setString(4, studentName);
				pst.setDate(5, sIssueDate);
				pst.setDate(6, sDueDate);
				pst.setString(7, "pending");
				
				int rowCount = pst.executeUpdate();
				if(rowCount>0) {
					isIssued = true;
				}
				else
					isIssued = false;
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			
			return isIssued;
		}
		
		//updating book count
		public void updateBookCount() {
			int bookId = Integer.parseInt(txtBookId.getText());
			
			try {
				Connection con = DBConnection.getConnection();
				String sql  = "update book_details set quantity = quantity-1 where book_Id = ?";
				PreparedStatement pst = con.prepareStatement(sql);
				
				pst.setInt(1, bookId);
				int rowCount = pst.executeUpdate();
				if(rowCount>0) {
					JOptionPane.showMessageDialog(this, "Book Count Updated");
					int initialCount = Integer.parseInt(txtQuan.getText());
					txtQuan.setText(Integer.toString(initialCount -1));
				}
				else {
					JOptionPane.showMessageDialog(this, "Book Count Updation Failure");
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		//check if book is issued to a particular student
		public boolean isAlreadyIssued() {
			boolean isIssued = false;
			int bookId = Integer.parseInt(txtBookId.getText());
			int studentId = Integer.parseInt(txtStuId.getText());
			
			try {
				Connection con = DBConnection.getConnection();
				String sql = "select * from issue_book where book_Id = ? and student_Id = ? and status = ?";
				
				PreparedStatement pst = con.prepareStatement(sql);
				pst.setInt(1, bookId);
				pst.setInt(2, studentId);
				pst.setString(3, "pending");
				
				ResultSet rs = pst.executeQuery(); 
				
				if(rs.next()) {
					isIssued = true;
				}
				else
					isIssued = false;
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			
			return isIssued;
		}
	/**
	 * Create the frame.
	 */
	public IssueBooks() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1253, 750);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(32, 178, 170));
		panel.setBorder(null);
		panel.setBounds(0, 0, 395, 750);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HomePage home = new HomePage();
				home.setVisible(true);
				dispose();
			}
		});
		panel_1_1.setLayout(null);
		panel_1_1.setBackground(new Color(0, 139, 139));
		panel_1_1.setBounds(0, 0, 168, 43);
		panel.add(panel_1_1);
		
		JLabel lblNewLabel = new JLabel("  BACK");
		lblNewLabel.setIcon(new ImageIcon(IssueBooks.class.getResource("/AddNewBookIcons/AddNewBookIcons/icons8_Rewind_48px.png")));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Verdana", Font.PLAIN, 15));
		lblNewLabel.setBounds(10, 10, 130, 28);
		panel_1_1.add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("   Manage Books");
		lblNewLabel_2.setIcon(new ImageIcon(IssueBooks.class.getResource("/AddNewBookIcons/AddNewBookIcons/icons8_Literature_100px_1.png")));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("Sylfaen", Font.BOLD, 25));
		lblNewLabel_2.setBounds(37, 82, 317, 106);
		panel.add(lblNewLabel_2);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(null);
		panel_3.setBackground(new Color(255, 255, 255));
		panel_3.setBounds(47, 198, 307, 5);
		panel.add(panel_3);
		
		JLabel lblNewLabel_1 = new JLabel("Book Id :");
		lblNewLabel_1.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(37, 281, 100, 23);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Book Name :");
		lblNewLabel_1_1.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblNewLabel_1_1.setBounds(37, 352, 121, 23);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Author :");
		lblNewLabel_1_2.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblNewLabel_1_2.setBounds(37, 426, 100, 23);
		panel.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Quantity :");
		lblNewLabel_1_3.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblNewLabel_1_3.setBounds(37, 506, 100, 23);
		panel.add(lblNewLabel_1_3);
		
		txtBookId = new JTextField();
		txtBookId.setEditable(false);
		txtBookId.setHorizontalAlignment(SwingConstants.CENTER);
		txtBookId.setForeground(Color.BLACK);
		txtBookId.setFont(new Font("Arial", Font.PLAIN, 18));
		txtBookId.setColumns(10);
		txtBookId.setBorder(new MatteBorder(0, 0, 3, 0, (Color) new Color(255, 255, 255)));
		txtBookId.setBackground(new Color(32, 178, 170));
		txtBookId.setBounds(168, 271, 201, 33);
		panel.add(txtBookId);
		
		txtBookName = new JTextField();
		txtBookName.setEditable(false);
		txtBookName.setHorizontalAlignment(SwingConstants.CENTER);
		txtBookName.setForeground(Color.BLACK);
		txtBookName.setFont(new Font("Arial", Font.PLAIN, 18));
		txtBookName.setColumns(10);
		txtBookName.setBorder(new MatteBorder(0, 0, 3, 0, (Color) new Color(255, 255, 255)));
		txtBookName.setBackground(new Color(32, 178, 170));
		txtBookName.setBounds(168, 342, 201, 33);
		panel.add(txtBookName);
		
		txtAuthor = new JTextField();
		txtAuthor.setEditable(false);
		txtAuthor.setHorizontalAlignment(SwingConstants.CENTER);
		txtAuthor.setForeground(Color.BLACK);
		txtAuthor.setFont(new Font("Arial", Font.PLAIN, 18));
		txtAuthor.setColumns(10);
		txtAuthor.setBorder(new MatteBorder(0, 0, 3, 0, (Color) new Color(255, 255, 255)));
		txtAuthor.setBackground(new Color(32, 178, 170));
		txtAuthor.setBounds(168, 416, 201, 33);
		panel.add(txtAuthor);
		
		txtQuan = new JTextField();
		txtQuan.setEditable(false);
		txtQuan.setHorizontalAlignment(SwingConstants.CENTER);
		txtQuan.setForeground(Color.BLACK);
		txtQuan.setFont(new Font("Arial", Font.PLAIN, 18));
		txtQuan.setColumns(10);
		txtQuan.setBorder(new MatteBorder(0, 0, 3, 0, (Color) new Color(255, 255, 255)));
		txtQuan.setBackground(new Color(32, 178, 170));
		txtQuan.setBounds(168, 496, 201, 33);
		panel.add(txtQuan);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(null);
		panel_1.setBackground(new Color(102, 205, 170));
		panel_1.setBounds(405, 0, 377, 750);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_2_1 = new JLabel("   Manage Books");
		lblNewLabel_2_1.setIcon(new ImageIcon(IssueBooks.class.getResource("/AddNewBookIcons/AddNewBookIcons/icons8_Student_Registration_100px_2.png")));
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1.setForeground(Color.WHITE);
		lblNewLabel_2_1.setFont(new Font("Sylfaen", Font.BOLD, 25));
		lblNewLabel_2_1.setBounds(27, 78, 317, 106);
		panel_1.add(lblNewLabel_2_1);
		
		JPanel panel_3_1 = new JPanel();
		panel_3_1.setBorder(null);
		panel_3_1.setBackground(Color.WHITE);
		panel_3_1.setBounds(27, 194, 307, 5);
		panel_1.add(panel_3_1);
		
		JLabel lblNewLabel_1_4 = new JLabel("Student Id :");
		lblNewLabel_1_4.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblNewLabel_1_4.setBounds(27, 275, 120, 23);
		panel_1.add(lblNewLabel_1_4);
		
		JLabel lblNewLabel_1_5 = new JLabel("Name :");
		lblNewLabel_1_5.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblNewLabel_1_5.setBounds(27, 352, 86, 23);
		panel_1.add(lblNewLabel_1_5);
		
		JLabel lblNewLabel_1_6 = new JLabel("Course :");
		lblNewLabel_1_6.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblNewLabel_1_6.setBounds(27, 426, 100, 23);
		panel_1.add(lblNewLabel_1_6);
		
		JLabel lblNewLabel_1_7 = new JLabel("Branch :");
		lblNewLabel_1_7.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblNewLabel_1_7.setBounds(27, 509, 100, 23);
		panel_1.add(lblNewLabel_1_7);
		
		txtStuId = new JTextField();
		txtStuId.setEditable(false);
		txtStuId.setHorizontalAlignment(SwingConstants.CENTER);
		txtStuId.setForeground(Color.BLACK);
		txtStuId.setFont(new Font("Arial", Font.PLAIN, 18));
		txtStuId.setColumns(10);
		txtStuId.setBorder(new MatteBorder(0, 0, 3, 0, (Color) new Color(255, 255, 255)));
		txtStuId.setBackground(new Color(102, 205, 170));
		txtStuId.setBounds(157, 265, 201, 33);
		panel_1.add(txtStuId);
		
		txtCourse = new JTextField();
		txtCourse.setEditable(false);
		txtCourse.setHorizontalAlignment(SwingConstants.CENTER);
		txtCourse.setForeground(Color.BLACK);
		txtCourse.setFont(new Font("Arial", Font.PLAIN, 18));
		txtCourse.setColumns(10);
		txtCourse.setBorder(new MatteBorder(0, 0, 3, 0, (Color) new Color(255, 255, 255)));
		txtCourse.setBackground(new Color(102, 205, 170));
		txtCourse.setBounds(157, 416, 201, 33);
		panel_1.add(txtCourse);
		
		txtBranch = new JTextField();
		txtBranch.setEditable(false);
		txtBranch.setHorizontalAlignment(SwingConstants.CENTER);
		txtBranch.setForeground(Color.BLACK);
		txtBranch.setFont(new Font("Arial", Font.PLAIN, 18));
		txtBranch.setColumns(10);
		txtBranch.setBorder(new MatteBorder(0, 0, 3, 0, (Color) new Color(255, 255, 255)));
		txtBranch.setBackground(new Color(102, 205, 170));
		txtBranch.setBounds(157, 499, 201, 33);
		panel_1.add(txtBranch);
		
		txtStuName = new JTextField();
		txtStuName.setEditable(false);
		txtStuName.setHorizontalAlignment(SwingConstants.CENTER);
		txtStuName.setForeground(Color.BLACK);
		txtStuName.setFont(new Font("Arial", Font.PLAIN, 18));
		txtStuName.setColumns(10);
		txtStuName.setBorder(new MatteBorder(0, 0, 3, 0, (Color) new Color(255, 255, 255)));
		txtStuName.setBackground(new Color(102, 205, 170));
		txtStuName.setBounds(157, 342, 201, 33);
		panel_1.add(txtStuName);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		panel_2.setBorder(null);
		panel_2.setBounds(781, 0, 472, 750);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblX = new JLabel("X");
		lblX.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		lblX.setHorizontalAlignment(SwingConstants.CENTER);
		lblX.setForeground(Color.BLACK);
		lblX.setFont(new Font("Verdana", Font.PLAIN, 20));
		lblX.setBounds(421, 10, 41, 38);
		panel_2.add(lblX);
		
		JLabel lblNewLabel_2_2 = new JLabel("   Issue Books");
		lblNewLabel_2_2.setIcon(new ImageIcon(IssueBooks.class.getResource("/AddNewBookIcons/AddNewBookIcons/icons8_Books_52px_1.png")));
		lblNewLabel_2_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_2.setForeground(new Color(255, 69, 0));
		lblNewLabel_2_2.setFont(new Font("Sylfaen", Font.BOLD, 24));
		lblNewLabel_2_2.setBounds(67, 95, 317, 69);
		panel_2.add(lblNewLabel_2_2);
		
		JPanel panel_3_2 = new JPanel();
		panel_3_2.setBorder(null);
		panel_3_2.setBackground(new Color(255, 69, 0));
		panel_3_2.setBounds(94, 174, 307, 5);
		panel_2.add(panel_3_2);
		
		txtBookid = new JTextField();
		txtBookid.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(!txtBookid.getText().equals(""))
					getBookDetails();
			}
		});
		txtBookid.setHorizontalAlignment(SwingConstants.CENTER);
		txtBookid.setForeground(Color.BLACK);
		txtBookid.setFont(new Font("Arial", Font.PLAIN, 18));
		txtBookid.setColumns(10);
		txtBookid.setBorder(new MatteBorder(0, 0, 3, 0, (Color) new Color(178, 34, 34)));
		txtBookid.setBackground(new Color(255, 255, 255));
		txtBookid.setBounds(182, 261, 239, 33);
		panel_2.add(txtBookid);
		
		JLabel lblNewLabel_1_8 = new JLabel("Book Id :");
		lblNewLabel_1_8.setForeground(new Color(178, 34, 34));
		lblNewLabel_1_8.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblNewLabel_1_8.setBounds(52, 271, 100, 23);
		panel_2.add(lblNewLabel_1_8);
		
		JLabel lblNewLabel_1_4_1 = new JLabel("Student Id :");
		lblNewLabel_1_4_1.setForeground(new Color(178, 34, 34));
		lblNewLabel_1_4_1.setBorder(null);
		lblNewLabel_1_4_1.setBackground(new Color(255, 255, 255));
		lblNewLabel_1_4_1.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblNewLabel_1_4_1.setBounds(52, 352, 120, 23);
		panel_2.add(lblNewLabel_1_4_1);
		
		txtStuid = new JTextField();
		txtStuid.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(!txtStuid.getText().equals(""))
					getStudentDetails();
			}
		});
		txtStuid.setHorizontalAlignment(SwingConstants.CENTER);
		txtStuid.setForeground(Color.BLACK);
		txtStuid.setFont(new Font("Arial", Font.PLAIN, 18));
		txtStuid.setColumns(10);
		txtStuid.setBorder(new MatteBorder(0, 0, 3, 0, (Color) new Color(178, 34, 34)));
		txtStuid.setBackground(Color.WHITE);
		txtStuid.setBounds(182, 342, 239, 33);
		panel_2.add(txtStuid);
		
		JLabel lblNewLabel_1_8_1 = new JLabel("Issue Date :");
		lblNewLabel_1_8_1.setForeground(new Color(178, 34, 34));
		lblNewLabel_1_8_1.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblNewLabel_1_8_1.setBounds(52, 461, 120, 23);
		panel_2.add(lblNewLabel_1_8_1);
		
		JLabel lblNewLabel_1_8_2 = new JLabel("Due Date :");
		lblNewLabel_1_8_2.setForeground(new Color(178, 34, 34));
		lblNewLabel_1_8_2.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblNewLabel_1_8_2.setBounds(52, 538, 120, 23);
		panel_2.add(lblNewLabel_1_8_2);
		
		RSDateChooser date_issue = new RSDateChooser();
		date_issue.setPlaceholder("Select Issue Date...");
		date_issue.setColorForeground(new Color(178, 34, 34));
		date_issue.setColorButtonHover(new Color(178, 34, 34));
		date_issue.setColorBackground(new Color(178, 34, 34));
		date_issue.setBounds(181, 444, 240, 40);
		panel_2.add(date_issue);
		
		RSDateChooser date_due = new RSDateChooser();
		date_due.setPlaceholder("Select Due Date...");
		date_due.setColorForeground(new Color(178, 34, 34));
		date_due.setColorButtonHover(new Color(178, 34, 34));
		date_due.setColorBackground(new Color(178, 34, 34));
		date_due.setBounds(181, 521, 240, 40);
		panel_2.add(date_due);
		
		JButton btnNewButton = new JButton("Issue Book");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtQuan.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Book Is Not Available");
				}
				else {
					if(isAlreadyIssued()==false) {
						if(issueBook()==true) {
							JOptionPane.showMessageDialog(null, "Book Issued Successfully");
							updateBookCount();
						}
						else {
							JOptionPane.showMessageDialog(null, "Book Issue Failure");
						}
					}
					else {
						JOptionPane.showMessageDialog(null, "Student has already acquired the book");
					}
				}
				}
		});
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBorder(null);
		btnNewButton.setBackground(new Color(178, 34, 34));
		btnNewButton.setFont(new Font("Verdana", Font.PLAIN, 18));
		btnNewButton.setBounds(129, 639, 255, 40);
		panel_2.add(btnNewButton);
	}
}
