package jFrame;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ManagePage extends JFrame {

	private JPanel contentPane;
	private JTextField txtQuan;
	private JTextField txtQuantity;
	private JTextField txtAuthor;
	private JTextField txtAuthorName;
	private JTextField txtBookName;
	private JTextField txtEnterBookName;
	private JTextField txtBookId;
	private JTextField txtEnterBookid;
	private JTable tbl_bookDetails;

	// book details 
	String bookName,author;
	int bookId,quantity;
	DefaultTableModel model;
	
	public void setBookDetails() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
        	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_manag","root","Chel4@sea");
        	Statement st = con.createStatement();
        	ResultSet rs = st.executeQuery("select * from book_details");
        	
        	while(rs.next()) {
        		String book_Id = rs.getString("book_Id");
        		String book_name = rs.getString("book_name");
        		String author = rs.getString("author");
        		int quantity = rs.getInt("quantity");
        		
        		Object obj[] = {book_Id,book_name,author,quantity};
        		model = (DefaultTableModel)tbl_bookDetails.getModel();
        		model.addRow(obj);
        	}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	} 
	
	//to add books
	public boolean addBook() {
		boolean isAdded = false;
		bookId = Integer.parseInt(txtBookId.getText());
		bookName = txtBookName.getText();
		author = txtAuthor.getText();
		quantity = Integer.parseInt(txtQuan.getText());
		
		try {
        	Connection con = DBConnection.getConnection();
        	String sql = "insert into book_details values (?,?,?,?)";
        	
        	PreparedStatement pst = con.prepareStatement(sql);
        	pst.setInt(1, bookId);
        	pst.setString(2, bookName);
        	pst.setString(3, author);
        	pst.setInt(4, quantity);
        	
        	int rowCount = pst.executeUpdate();
        	if(rowCount >0) {
        		isAdded = true;
        	}
        	else
        		isAdded = false;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return isAdded;
	}
	
	//update books
	public boolean updateBook() {
		boolean isUpdated = false;
		bookId = Integer.parseInt(txtBookId.getText());
		bookName = txtBookName.getText();
		author = txtAuthor.getText();
		quantity = Integer.parseInt(txtQuan.getText());
		
		try {
			Connection con = DBConnection.getConnection();
			String sql = "update book_details  set book_name = ?, author = ?, quantity = ? where book_Id = ?";
			
			PreparedStatement pst = con.prepareStatement(sql);
        	pst.setString(1, bookName);
        	pst.setString(2, author);
        	pst.setInt(3, quantity);
        	pst.setInt(4, bookId);
        	
        	int rowCount = pst.executeUpdate();
        	if(rowCount >0) {
        		isUpdated = true;
        	}
        	else {
        		isUpdated = false;
        	}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return isUpdated;
	}
		
	// to delete book records
	public boolean deleteBook() {
		boolean isDeleted = false;
		bookId = Integer.parseInt(txtBookId.getText());
		
		try {
			Connection con = DBConnection.getConnection();
			String sql = "delete from book_details where book_Id = ?";
			
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, bookId);
			
			int rowCount = pst.executeUpdate();
        	if(rowCount >0) {
        		isDeleted = true;
        	}
        	else {
        		isDeleted = false;
        	}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return isDeleted;
	}
	
	
	// to clear table
	public void clearTable() {
		DefaultTableModel model = (DefaultTableModel)tbl_bookDetails.getModel();
		model.setRowCount(0);
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManagePage frame = new ManagePage();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
					frame.setSize(new Dimension(1400,750));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ManagePage() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1401, 745);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(32, 178, 170));
		panel.setBorder(null);
		panel.setBounds(0, 0, 587, 745);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 139, 139));
		panel_1.setBounds(0, 0, 168, 43);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("  BACK");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HomePage home = new HomePage();
				home.setVisible(true);
				dispose();
			}
		});
		lblNewLabel.setIcon(new ImageIcon(ManagePage.class.getResource("/AddNewBookIcons/AddNewBookIcons/icons8_Rewind_48px.png")));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Verdana", Font.PLAIN, 15));
		lblNewLabel.setBounds(10, 10, 130, 28);
		panel_1.add(lblNewLabel);
		
		txtQuan = new JTextField();
		txtQuan.setHorizontalAlignment(SwingConstants.CENTER);
		txtQuan.setForeground(new Color(0, 0, 0));
		txtQuan.setFont(new Font("Arial", Font.PLAIN, 18));
		txtQuan.setColumns(10);
		txtQuan.setBorder(new MatteBorder(0, 0, 3, 0, (Color) new Color(255, 255, 255)));
		txtQuan.setBackground(new Color(32, 178, 170));
		txtQuan.setBounds(106, 479, 355, 33);
		panel.add(txtQuan);
		
		txtQuantity = new JTextField();
		txtQuantity.setText("Quantity :");
		txtQuantity.setForeground(new Color(0, 0, 0));
		txtQuantity.setFont(new Font("Verdana", Font.PLAIN, 18));
		txtQuantity.setEditable(false);
		txtQuantity.setColumns(10);
		txtQuantity.setBorder(null);
		txtQuantity.setBackground(new Color(32, 178, 170));
		txtQuantity.setBounds(106, 436, 233, 33);
		panel.add(txtQuantity);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(ManagePage.class.getResource("/AddNewBookIcons/AddNewBookIcons/icons8_Unit_26px.png")));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(51, 479, 32, 33);
		panel.add(lblNewLabel_1);
		
		txtAuthor = new JTextField();
		txtAuthor.setHorizontalAlignment(SwingConstants.CENTER);
		txtAuthor.setForeground(Color.BLACK);
		txtAuthor.setFont(new Font("Arial", Font.PLAIN, 18));
		txtAuthor.setColumns(10);
		txtAuthor.setBorder(new MatteBorder(0, 0, 3, 0, (Color) new Color(255, 255, 255)));
		txtAuthor.setBackground(new Color(32, 178, 170));
		txtAuthor.setBounds(106, 375, 355, 33);
		panel.add(txtAuthor);
		
		JLabel lblNewLabel_1_1 = new JLabel("");
		lblNewLabel_1_1.setIcon(new ImageIcon(ManagePage.class.getResource("/AddNewBookIcons/AddNewBookIcons/icons8_Collaborator_Male_26px.png")));
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setBounds(51, 375, 32, 33);
		panel.add(lblNewLabel_1_1);
		
		txtAuthorName = new JTextField();
		txtAuthorName.setText("Author Name :");
		txtAuthorName.setForeground(Color.BLACK);
		txtAuthorName.setFont(new Font("Verdana", Font.PLAIN, 18));
		txtAuthorName.setEditable(false);
		txtAuthorName.setColumns(10);
		txtAuthorName.setBorder(null);
		txtAuthorName.setBackground(new Color(32, 178, 170));
		txtAuthorName.setBounds(106, 332, 233, 33);
		panel.add(txtAuthorName);
		
		txtBookName = new JTextField();
		txtBookName.setHorizontalAlignment(SwingConstants.CENTER);
		txtBookName.setForeground(Color.BLACK);
		txtBookName.setFont(new Font("Arial", Font.PLAIN, 18));
		txtBookName.setColumns(10);
		txtBookName.setBorder(new MatteBorder(0, 0, 3, 0, (Color) new Color(255, 255, 255)));
		txtBookName.setBackground(new Color(32, 178, 170));
		txtBookName.setBounds(106, 271, 355, 33);
		panel.add(txtBookName);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("");
		lblNewLabel_1_1_1.setIcon(new ImageIcon(ManagePage.class.getResource("/AddNewBookIcons/AddNewBookIcons/icons8_Moleskine_26px.png")));
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1.setBounds(51, 279, 32, 33);
		panel.add(lblNewLabel_1_1_1);
		
		txtEnterBookName = new JTextField();
		txtEnterBookName.setText("Enter Book Name :");
		txtEnterBookName.setForeground(Color.BLACK);
		txtEnterBookName.setFont(new Font("Verdana", Font.PLAIN, 18));
		txtEnterBookName.setEditable(false);
		txtEnterBookName.setColumns(10);
		txtEnterBookName.setBorder(null);
		txtEnterBookName.setBackground(new Color(32, 178, 170));
		txtEnterBookName.setBounds(106, 236, 233, 33);
		panel.add(txtEnterBookName);
		
		txtBookId = new JTextField();
		txtBookId.setHorizontalAlignment(SwingConstants.CENTER);
		txtBookId.setForeground(Color.BLACK);
		txtBookId.setFont(new Font("Arial", Font.PLAIN, 18));
		txtBookId.setColumns(10);
		txtBookId.setBorder(new MatteBorder(0, 0, 3, 0, (Color) new Color(255, 255, 255)));
		txtBookId.setBackground(new Color(32, 178, 170));
		txtBookId.setBounds(106, 165, 355, 33);
		panel.add(txtBookId);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("");
		lblNewLabel_1_1_2.setIcon(new ImageIcon(ManagePage.class.getResource("/AddNewBookIcons/AddNewBookIcons/icons8_Contact_26px.png")));
		lblNewLabel_1_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_2.setBounds(51, 165, 32, 33);
		panel.add(lblNewLabel_1_1_2);
		
		txtEnterBookid = new JTextField();
		txtEnterBookid.setText("Enter Book_Id :");
		txtEnterBookid.setForeground(Color.BLACK);
		txtEnterBookid.setFont(new Font("Verdana", Font.PLAIN, 18));
		txtEnterBookid.setEditable(false);
		txtEnterBookid.setColumns(10);
		txtEnterBookid.setBorder(null);
		txtEnterBookid.setBackground(new Color(32, 178, 170));
		txtEnterBookid.setBounds(106, 122, 233, 33);
		panel.add(txtEnterBookid);
		
		JButton btnNewButton = new JButton("ADD");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(addBook()==true) {
					JOptionPane.showMessageDialog(null, "Book is Added Successfully");
					clearTable();
					setBookDetails();
				}
				else {
					JOptionPane.showMessageDialog(null, "Book Addition Fail");
				}
			}
		});
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(0, 128, 128));
		btnNewButton.setBorder(null);
		btnNewButton.setFont(new Font("Verdana", Font.BOLD, 20));
		btnNewButton.setBounds(38, 597, 130, 43);
		panel.add(btnNewButton);
		
		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(updateBook()==true) {
					JOptionPane.showMessageDialog(null, "Book Updated Successfully");
					clearTable();
					setBookDetails();
				}
				else {
					JOptionPane.showMessageDialog(null, "Book Updation Fail");
				}
			}
		});
		btnUpdate.setForeground(Color.WHITE);
		btnUpdate.setFont(new Font("Verdana", Font.BOLD, 20));
		btnUpdate.setBorder(null);
		btnUpdate.setBackground(new Color(0, 128, 128));
		btnUpdate.setBounds(218, 597, 132, 43);
		panel.add(btnUpdate);
		
		JButton btnDelete = new JButton("DELETE");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(deleteBook()==true) {
					JOptionPane.showMessageDialog(null, "Book Deletion Successfully");
					clearTable();
					setBookDetails();
				}
				else {
					JOptionPane.showMessageDialog(null, "Book Deletion Fail");
				}
			}
		});
		btnDelete.setForeground(Color.WHITE);
		btnDelete.setFont(new Font("Verdana", Font.BOLD, 20));
		btnDelete.setBorder(null);
		btnDelete.setBackground(new Color(0, 128, 128));
		btnDelete.setBounds(406, 597, 130, 43);
		panel.add(btnDelete);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		panel_2.setBorder(null);
		panel_2.setBounds(586, 0, 815, 745);
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
		lblX.setForeground(new Color(0, 0, 0));
		lblX.setFont(new Font("Verdana", Font.PLAIN, 20));
		lblX.setBounds(764, 10, 41, 38);
		panel_2.add(lblX);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		scrollPane_1.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(211, 211, 211), null, new Color(211, 211, 211), null));
		scrollPane_1.setBounds(55, 285, 697, 216);
		panel_2.add(scrollPane_1);
		
		tbl_bookDetails = new JTable();
		tbl_bookDetails.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tbl_bookDetails.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int rowNo = tbl_bookDetails.getSelectedRow();
				TableModel model = tbl_bookDetails.getModel();
				txtBookId.setText(model.getValueAt(rowNo, 0).toString());
				txtBookName.setText(model.getValueAt(rowNo, 1).toString());
				txtAuthor.setText(model.getValueAt(rowNo, 2).toString());
				txtQuan.setText(model.getValueAt(rowNo, 3).toString());
			}
		});
		tbl_bookDetails.setBackground(new Color(64, 224, 208));
		scrollPane_1.setViewportView(tbl_bookDetails); 
		tbl_bookDetails.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Book_Id", "Name", "Author", "Quantity"
			}
		));
		tbl_bookDetails.getColumnModel().getColumn(0).setPreferredWidth(66);
		tbl_bookDetails.getColumnModel().getColumn(1).setPreferredWidth(141);
		tbl_bookDetails.getColumnModel().getColumn(2).setPreferredWidth(127);
		tbl_bookDetails.setRowMargin(5);
		tbl_bookDetails.setRowHeight(30);
		tbl_bookDetails.setEditingRow(5);
		tbl_bookDetails.setEditingColumn(4);
		tbl_bookDetails.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(192, 192, 192), null, null, null));
		
		JLabel lblNewLabel_2 = new JLabel("   Manage Books");
		lblNewLabel_2.setIcon(new ImageIcon(ManagePage.class.getResource("/AddNewBookIcons/AddNewBookIcons/icons8_Books_52px_1.png")));
		lblNewLabel_2.setForeground(new Color(255, 69, 0));
		lblNewLabel_2.setFont(new Font("Sylfaen", Font.BOLD, 24));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(224, 65, 317, 69);
		panel_2.add(lblNewLabel_2);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(null);
		panel_3.setBackground(new Color(255, 69, 0));
		panel_3.setBounds(255, 144, 307, 5);
		panel_2.add(panel_3);
		
		setBookDetails();
	}

}
