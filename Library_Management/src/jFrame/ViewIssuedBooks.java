package jFrame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ViewIssuedBooks extends JFrame {

	private JPanel contentPane;
	private JTable tbl_BookDetails;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewIssuedBooks frame = new ViewIssuedBooks();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	DefaultTableModel model;
	public void setIssuedBookDetails() {
		try {
        	Connection con = DBConnection.getConnection();
        	Statement st = con.createStatement();
        	ResultSet rs = st.executeQuery("select * from issue_book where status = '"+"pending"+"'");
        	
        	while(rs.next()) {
        		String id = rs.getString("id");
        		String book_name= rs.getString("book_name");
        		String student_name = rs.getString("student_name");
        		String issue_date = rs.getString("issue_date");
        		String due_date = rs.getString("due_date");
        		String status = rs.getString("status");
        		
        		Object obj[] = {id,book_name,student_name,issue_date,due_date,status};
        		model = (DefaultTableModel)tbl_BookDetails.getModel();
        		model.addRow(obj);
        	}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	} 
	
	/**
	 * Create the frame.
	 */
	public ViewIssuedBooks() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1356, 670);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 1356, 670);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_2_2 = new JLabel("Issued Book List");
		lblNewLabel_2_2.setIcon(new ImageIcon(ViewIssuedBooks.class.getResource("/AddNewBookIcons/AddNewBookIcons/icons8_Edit_Property_50px.png")));
		lblNewLabel_2_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_2.setForeground(new Color(255, 69, 0));
		lblNewLabel_2_2.setFont(new Font("Sylfaen", Font.BOLD, 24));
		lblNewLabel_2_2.setBounds(556, 43, 317, 69);
		panel.add(lblNewLabel_2_2);
		
		JPanel panel_3_2 = new JPanel();
		panel_3_2.setBorder(null);
		panel_3_2.setBackground(new Color(255, 0, 0));
		panel_3_2.setBounds(556, 122, 338, 5);
		panel.add(panel_3_2);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBackground(new Color(165, 42, 42));
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
		lblNewLabel.setIcon(new ImageIcon(ViewIssuedBooks.class.getResource("/AddNewBookIcons/AddNewBookIcons/icons8_Rewind_48px.png")));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Verdana", Font.PLAIN, 15));
		lblNewLabel.setBounds(10, 10, 130, 28);
		panel_1_1.add(lblNewLabel);
		
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
		lblX.setBounds(1305, 10, 41, 38);
		panel.add(lblX);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		scrollPane_1.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(211, 211, 211), null, new Color(211, 211, 211), null));
		scrollPane_1.setBounds(108, 246, 1195, 225);
		panel.add(scrollPane_1);
		
		tbl_BookDetails = new JTable();
		tbl_BookDetails.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tbl_BookDetails.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Id", "Book Name", "Student Name", "Issue Date", "Due Date", "Status"
			}
		));
		tbl_BookDetails.setBackground(new Color(64, 224, 208));
		tbl_BookDetails.setRowMargin(6);
		tbl_BookDetails.setRowHeight(40);
		tbl_BookDetails.setEditingRow(10);
		tbl_BookDetails.setEditingColumn(6);
		tbl_BookDetails.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(192, 192, 192), null, null, null));
		scrollPane_1.setViewportView(tbl_BookDetails);
		
		setIssuedBookDetails();
		
	}
}
