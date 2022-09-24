package jFrame;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ReturnBooks extends JFrame {

	private JPanel contentPane;
	private JTextField txtIssueId;
	private JTextField txtBookName;
	private JTextField txtStuName;
	private JTextField txt_bookId;
	private JTextField txt_stuId;
	private JTextField txtIssueDate;
	private JTextField txtDueDate;
	private JLabel lbl_bookError;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReturnBooks frame = new ReturnBooks();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	//return book
	public boolean returnBook() {
		boolean isReturned = false;
		int bookId = Integer.parseInt(txt_bookId.getText());
		int stuId = Integer.parseInt(txt_stuId.getText());
		
		try {
			Connection con = DBConnection.getConnection();
			String sql = " update issue_book set status = ? where student_id =? and book_id = ? and status= ?";
			
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, "returned");
			pst.setInt(2, stuId);
			pst.setInt(3, bookId);
			pst.setString(4, "pending");
			
			int rowCount = pst.executeUpdate();
			if(rowCount>0) {
				isReturned = true;
			}
			else {
				isReturned = false;
			}
		}
		catch(Exception e) {
			
		}
		
		
		return isReturned;
	}
		
		
	//updating book count
	public void updateBookCount() {
		int bookId = Integer.parseInt(txt_bookId.getText());
				
		try {
			Connection con = DBConnection.getConnection();
			String sql  = "update book_details set quantity = quantity+1 where book_Id = ?";
			PreparedStatement pst = con.prepareStatement(sql);
					
			pst.setInt(1, bookId);
			int rowCount = pst.executeUpdate();
			if(rowCount>0) {
				JOptionPane.showMessageDialog(this, "Book Count Updated");
			}
			else {
					JOptionPane.showMessageDialog(this, "Book Count Updation Failure");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	// search book
		public void issueBookDetails() {
			int bookId = Integer.parseInt(txt_bookId.getText());
			int stuId = Integer.parseInt(txt_stuId.getText());
			
			
			try {
				Connection con = DBConnection.getConnection();
				String sql = " select * from issue_book where book_id=? and student_id=? and status = ?";
				
				PreparedStatement pst = con.prepareStatement(sql);
				pst.setInt(1, bookId);
				pst.setInt(2, stuId);
				pst.setString(3, "pending");
				
				ResultSet rs = pst.executeQuery();
				if(rs.next()) {
					txtIssueId.setText(rs.getString("id"));
					txtBookName.setText(rs.getString("book_name"));
					txtStuName.setText(rs.getString("student_name"));
					txtIssueDate.setText(rs.getString("issue_date"));
					txtDueDate.setText(rs.getString("due_date"));
					lbl_bookError.setText("");
				}
				else {
					lbl_bookError.setText("No Record Found");
					txtIssueId.setText("");
					txtBookName.setText("");
					txtIssueDate.setText("");
					txtDueDate.setText("");
				}
			}
			catch(Exception e) {
				
			}
			
		}
		
			
	/**
	 * Create the frame.
	 */
	public ReturnBooks() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1040, 730);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(null);
		panel.setBackground(new Color(32, 178, 170));
		panel.setBounds(0, 0, 496, 730);
		contentPane.add(panel);
		
		JPanel panel_1_1 = new JPanel();
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
		lblNewLabel.setIcon(new ImageIcon(ReturnBooks.class.getResource("/AddNewBookIcons/AddNewBookIcons/icons8_Rewind_48px.png")));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Verdana", Font.PLAIN, 15));
		lblNewLabel.setBounds(10, 10, 130, 28);
		panel_1_1.add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("   Manage Books");
		lblNewLabel_2.setIcon(new ImageIcon(ReturnBooks.class.getResource("/AddNewBookIcons/AddNewBookIcons/icons8_Literature_100px_1.png")));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Sylfaen", Font.BOLD, 25));
		lblNewLabel_2.setBounds(37, 82, 317, 106);
		panel.add(lblNewLabel_2);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(null);
		panel_3.setBackground(Color.WHITE);
		panel_3.setBounds(47, 198, 307, 5);
		panel.add(panel_3);
		
		JLabel lblNewLabel_1 = new JLabel("Issue Id :");
		lblNewLabel_1.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(37, 281, 100, 23);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Book Name :");
		lblNewLabel_1_1.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblNewLabel_1_1.setBounds(37, 352, 121, 23);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Student Name:");
		lblNewLabel_1_2.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblNewLabel_1_2.setBounds(37, 416, 142, 31);
		panel.add(lblNewLabel_1_2);
		
		txtIssueId = new JTextField();
		txtIssueId.setHorizontalAlignment(SwingConstants.CENTER);
		txtIssueId.setForeground(Color.BLACK);
		txtIssueId.setFont(new Font("Arial", Font.PLAIN, 18));
		txtIssueId.setEditable(false);
		txtIssueId.setColumns(10);
		txtIssueId.setBorder(new MatteBorder(0, 0, 3, 0, (Color) new Color(255, 255, 255)));
		txtIssueId.setBackground(new Color(32, 178, 170));
		txtIssueId.setBounds(187, 271, 249, 33);
		panel.add(txtIssueId);
		
		txtBookName = new JTextField();
		txtBookName.setHorizontalAlignment(SwingConstants.CENTER);
		txtBookName.setForeground(Color.BLACK);
		txtBookName.setFont(new Font("Arial", Font.PLAIN, 18));
		txtBookName.setEditable(false);
		txtBookName.setColumns(10);
		txtBookName.setBorder(new MatteBorder(0, 0, 3, 0, (Color) new Color(255, 255, 255)));
		txtBookName.setBackground(new Color(32, 178, 170));
		txtBookName.setBounds(187, 342, 249, 33);
		panel.add(txtBookName);
		
		txtStuName = new JTextField();
		txtStuName.setHorizontalAlignment(SwingConstants.CENTER);
		txtStuName.setForeground(Color.BLACK);
		txtStuName.setFont(new Font("Arial", Font.PLAIN, 18));
		txtStuName.setEditable(false);
		txtStuName.setColumns(10);
		txtStuName.setBorder(new MatteBorder(0, 0, 3, 0, (Color) new Color(255, 255, 255)));
		txtStuName.setBackground(new Color(32, 178, 170));
		txtStuName.setBounds(189, 414, 247, 33);
		panel.add(txtStuName);
		
		JLabel lbl_bookError = new JLabel("");
		lbl_bookError.setFont(new Font("Verdana", Font.PLAIN, 18));
		lbl_bookError.setBackground(Color.WHITE);
		lbl_bookError.setBounds(37, 635, 317, 23);
		panel.add(lbl_bookError);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Issue Date:");
		lblNewLabel_1_2_1.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblNewLabel_1_2_1.setBounds(37, 498, 131, 23);
		panel.add(lblNewLabel_1_2_1);
		
		txtIssueDate = new JTextField();
		txtIssueDate.setHorizontalAlignment(SwingConstants.CENTER);
		txtIssueDate.setForeground(Color.BLACK);
		txtIssueDate.setFont(new Font("Arial", Font.PLAIN, 18));
		txtIssueDate.setEditable(false);
		txtIssueDate.setColumns(10);
		txtIssueDate.setBorder(new MatteBorder(0, 0, 3, 0, (Color) new Color(255, 255, 255)));
		txtIssueDate.setBackground(new Color(32, 178, 170));
		txtIssueDate.setBounds(187, 486, 249, 33);
		panel.add(txtIssueDate);
		
		JLabel lblNewLabel_1_2_2 = new JLabel("Due Date:");
		lblNewLabel_1_2_2.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblNewLabel_1_2_2.setBounds(37, 572, 100, 23);
		panel.add(lblNewLabel_1_2_2);
		
		txtDueDate = new JTextField();
		txtDueDate.setHorizontalAlignment(SwingConstants.CENTER);
		txtDueDate.setForeground(Color.BLACK);
		txtDueDate.setFont(new Font("Arial", Font.PLAIN, 18));
		txtDueDate.setEditable(false);
		txtDueDate.setColumns(10);
		txtDueDate.setBorder(new MatteBorder(0, 0, 3, 0, (Color) new Color(255, 255, 255)));
		txtDueDate.setBackground(new Color(32, 178, 170));
		txtDueDate.setBounds(187, 557, 249, 33);
		panel.add(txtDueDate);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(null);
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(496, 0, 544, 730);
		contentPane.add(panel_2);
		
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
		lblX.setBounds(477, 24, 41, 38);
		panel_2.add(lblX);
		
		JLabel lblNewLabel_2_2 = new JLabel("   Issue Books");
		lblNewLabel_2_2.setIcon(new ImageIcon(ReturnBooks.class.getResource("/AddNewBookIcons/AddNewBookIcons/icons8_Books_52px_1.png")));
		lblNewLabel_2_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_2.setForeground(new Color(255, 69, 0));
		lblNewLabel_2_2.setFont(new Font("Sylfaen", Font.BOLD, 24));
		lblNewLabel_2_2.setBounds(69, 95, 317, 69);
		panel_2.add(lblNewLabel_2_2);
		
		JPanel panel_3_2 = new JPanel();
		panel_3_2.setBorder(null);
		panel_3_2.setBackground(new Color(255, 69, 0));
		panel_3_2.setBounds(94, 174, 307, 5);
		panel_2.add(panel_3_2);
		
		txt_bookId = new JTextField();
		txt_bookId.setHorizontalAlignment(SwingConstants.CENTER);
		txt_bookId.setForeground(Color.BLACK);
		txt_bookId.setFont(new Font("Arial", Font.PLAIN, 18));
		txt_bookId.setColumns(10);
		txt_bookId.setBorder(new MatteBorder(0, 0, 3, 0, (Color) new Color(178, 34, 34)));
		txt_bookId.setBackground(Color.WHITE);
		txt_bookId.setBounds(182, 261, 239, 33);
		panel_2.add(txt_bookId);
		
		JLabel lblNewLabel_1_8 = new JLabel("Book Id :");
		lblNewLabel_1_8.setForeground(new Color(178, 34, 34));
		lblNewLabel_1_8.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblNewLabel_1_8.setBounds(52, 271, 100, 23);
		panel_2.add(lblNewLabel_1_8);
		
		JLabel lblNewLabel_1_4_1 = new JLabel("Student Id :");
		lblNewLabel_1_4_1.setForeground(new Color(178, 34, 34));
		lblNewLabel_1_4_1.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblNewLabel_1_4_1.setBorder(null);
		lblNewLabel_1_4_1.setBackground(Color.WHITE);
		lblNewLabel_1_4_1.setBounds(52, 388, 120, 23);
		panel_2.add(lblNewLabel_1_4_1);
		
		txt_stuId = new JTextField();
		txt_stuId.setHorizontalAlignment(SwingConstants.CENTER);
		txt_stuId.setForeground(Color.BLACK);
		txt_stuId.setFont(new Font("Arial", Font.PLAIN, 18));
		txt_stuId.setColumns(10);
		txt_stuId.setBorder(new MatteBorder(0, 0, 3, 0, (Color) new Color(178, 34, 34)));
		txt_stuId.setBackground(Color.WHITE);
		txt_stuId.setBounds(182, 370, 239, 33);
		panel_2.add(txt_stuId);
		
		
		JButton btnReturnBook_1 = new JButton("Return Book");
		btnReturnBook_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(returnBook()==true) {
					JOptionPane.showMessageDialog(null,"Book Returned Successfully");
					updateBookCount();
				}
				else {
					JOptionPane.showMessageDialog(null,"Book Return Failure");
				}
			}
		});
		btnReturnBook_1.setForeground(Color.WHITE);
		btnReturnBook_1.setFont(new Font("Verdana", Font.PLAIN, 18));
		btnReturnBook_1.setBorder(null);
		btnReturnBook_1.setBackground(new Color(178, 34, 34));
		btnReturnBook_1.setBounds(166, 576, 255, 40);
		panel_2.add(btnReturnBook_1);
		
		JButton btnReturnBook_1_1 = new JButton("Search");
		btnReturnBook_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				issueBookDetails();
			}
		});
		btnReturnBook_1_1.setForeground(Color.WHITE);
		btnReturnBook_1_1.setFont(new Font("Verdana", Font.PLAIN, 18));
		btnReturnBook_1_1.setBorder(null);
		btnReturnBook_1_1.setBackground(new Color(178, 34, 34));
		btnReturnBook_1_1.setBounds(166, 504, 255, 40);
		panel_2.add(btnReturnBook_1_1);
	}
}
