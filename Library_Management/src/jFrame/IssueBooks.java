package jFrame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;

public class IssueBooks extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtBookId;
	private JTextField txtBookName;
	private JTextField txtAuthor;
	private JTextField txtQuan;
	private JTextField txtStuId;
	private JTextField txtCourse;
	private JTextField txtBranch;
	private JTextField txtStuName;
	private JLabel lbl_bookError;
	private JLabel lbl_stuError;
	private JTextField txt_bookId;
	private JTextField txt_stuId;
	private JTextField txtIssueDate;
	private JTextField txtDueDate;

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
		int bookId = Integer.parseInt(txt_bookId.getText());
		
		try {
			Connection con = DBConnection.getConnection();
			PreparedStatement pst = con.prepareStatement("select * from book_details where book_Id =?");
			pst.setInt(1, bookId);
			ResultSet rs = pst.executeQuery();
			
			if(rs.next()) {
				txtBookId.setText(rs.getString("book_Id"));
				txtBookName.setText(rs.getString("book_name"));
				txtAuthor.setText(rs.getString("author"));
				txtQuan.setText(rs.getString("quantity"));	
			}
			else {
				lbl_bookError.setText("Invalid Book Id");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//fetch book details
		public void getStudentDetails() {
			int studentId = Integer.parseInt(txt_stuId.getText());
			
			try {
				Connection con = DBConnection.getConnection();
				PreparedStatement pst = con.prepareStatement("select * from student_details where student_Id =?");
				pst.setInt(1, studentId);
				ResultSet rs = pst.executeQuery();
				
				if(rs.next()) {
					txtStuId.setText(rs.getString("student_Id"));
					txtStuName.setText(rs.getString("name"));
					txtCourse.setText(rs.getString("course"));
					txtBranch.setText(rs.getString("branch"));	
				}
				else {
					lbl_stuError.setText("Invalid Student Id");
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		//insert issue book details into database
		public boolean issueBook() throws ParseException{
			boolean isIssued = false;
			int bookId = Integer.parseInt(txt_bookId.getText());
			int studentId = Integer.parseInt(txt_stuId.getText());
			String bookName = txtBookName.getText();
			String studentName = txtStuName.getText();
			
			SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
			
			java.util.Date issueDate = formatDate.parse(txtIssueDate.getText());
			java.sql.Date issue_Date = new java.sql.Date(issueDate.getTime());
			
			java.util.Date dueDate = formatDate.parse(txtDueDate.getText());
			java.sql.Date due_Date = new java.sql.Date(dueDate.getTime());
			
			try {
				Connection con = DBConnection.getConnection();
				String sql = "insert into issue_book(book_id,book_name,student_id,student_name,issue_date,due_date,status) values(?,?,?,?,?,?,?)";
				
				PreparedStatement pst = con.prepareStatement(sql);
				pst.setInt(1, bookId);
				pst.setString(2, bookName);
				pst.setInt(3, studentId);
				pst.setString(4, studentName);
				pst.setDate(5, issue_Date);
				pst.setDate(6, due_Date);
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
			int bookId = Integer.parseInt(txt_bookId.getText());
			
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
			boolean isAlreadyIssued = false;
			int bookId = Integer.parseInt(txt_bookId.getText());
			int studentId = Integer.parseInt(txt_stuId.getText());
			
			try {
				Connection con = DBConnection.getConnection();
				String sql = "select * from issue_book where book_id = ? and student_id = ? and status = ?";
				
				PreparedStatement pst = con.prepareStatement(sql);
				pst.setInt(1, bookId);
				pst.setInt(2, studentId);
				pst.setString(3, "pending");
				
				ResultSet rs = pst.executeQuery(); 
				
				if(rs.next()) {
					isAlreadyIssued = true;
				}
				else
					isAlreadyIssued = false;
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			
			return isAlreadyIssued;
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
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HomePage home = new HomePage();
				home.setVisible(true);
				dispose();
			}
		});
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
		
		JLabel lbl_bookError = new JLabel("");
		lbl_bookError.setForeground(new Color(255, 255, 255));
		lbl_bookError.setBackground(new Color(255, 255, 255));
		lbl_bookError.setFont(new Font("Verdana", Font.PLAIN, 18));
		lbl_bookError.setBounds(37, 620, 317, 23);
		panel.add(lbl_bookError);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(null);
		panel_1.setBackground(new Color(102, 205, 170));
		panel_1.setBounds(405, 0, 377, 750);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_2_1 = new JLabel("   Manage Students");
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
		
		JLabel lbl_stuError = new JLabel("");
		lbl_stuError.setForeground(new Color(255, 255, 255));
		lbl_stuError.setFont(new Font("Verdana", Font.PLAIN, 18));
		lbl_stuError.setBounds(27, 621, 317, 23);
		panel_1.add(lbl_stuError);
		
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
		
		txt_bookId = new JTextField();
		txt_bookId.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(!txt_bookId.getText().equals(""))
					getBookDetails();
			}
		});
		txt_bookId.setHorizontalAlignment(SwingConstants.CENTER);
		txt_bookId.setForeground(Color.BLACK);
		txt_bookId.setFont(new Font("Arial", Font.PLAIN, 18));
		txt_bookId.setColumns(10);
		txt_bookId.setBorder(new MatteBorder(0, 0, 3, 0, (Color) new Color(178, 34, 34)));
		txt_bookId.setBackground(new Color(255, 255, 255));
		txt_bookId.setBounds(182, 261, 239, 33);
		panel_2.add(txt_bookId);
		
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
		
		txt_stuId = new JTextField();
		txt_stuId.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(!txt_stuId.getText().equals(""))
					getStudentDetails();
			}
		});
		txt_stuId.setHorizontalAlignment(SwingConstants.CENTER);
		txt_stuId.setForeground(Color.BLACK);
		txt_stuId.setFont(new Font("Arial", Font.PLAIN, 18));
		txt_stuId.setColumns(10);
		txt_stuId.setBorder(new MatteBorder(0, 0, 3, 0, (Color) new Color(178, 34, 34)));
		txt_stuId.setBackground(Color.WHITE);
		txt_stuId.setBounds(182, 342, 239, 33);
		panel_2.add(txt_stuId);
		
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
		
		JButton btnNewButton = new JButton("Issue Book");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtQuan.getText().equals("0")) {
					JOptionPane.showMessageDialog(null, "Book Is Not Available");
				}
				else {
					if(isAlreadyIssued()==false) {
						try {
							if(issueBook()==true) {
								JOptionPane.showMessageDialog(null, "Book Issued Successfully");
								updateBookCount();
							}
							else {
								JOptionPane.showMessageDialog(null, "Book Issue Failure");
							}
						} catch (HeadlessException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
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
		
		txtIssueDate = new JTextField();
		txtIssueDate.setBounds(197, 446, 224, 38);
		panel_2.add(txtIssueDate);
		txtIssueDate.setColumns(10);
		
		txtDueDate = new JTextField();
		txtDueDate.setColumns(10);
		txtDueDate.setBounds(197, 523, 224, 38);
		panel_2.add(txtDueDate);

	}
}
