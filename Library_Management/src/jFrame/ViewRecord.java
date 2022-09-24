package jFrame;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewRecord extends JFrame {

	private JPanel contentPane;
	private JTable tbl_BookDetails;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewRecord frame = new ViewRecord();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	// book details 
		String bookName,author;
		int bookId,quantity;
		DefaultTableModel model;
		private JTextField txtFromDate;
		private JTextField txtToDate;

	public void setIssuedBookDetails() {
		try {
        	Connection con = DBConnection.getConnection();
        	Statement st = con.createStatement();
        	ResultSet rs = st.executeQuery("select * from issue_book");
        	
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
	
	// to clear table
		public void clearTable() {
			DefaultTableModel model = (DefaultTableModel)tbl_BookDetails.getModel();
			model.setRowCount(0);
		}

	// to fetch record from date fileds
		
		public void search() throws ParseException {
			SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
			java.util.Date from_date = formatDate.parse(txtFromDate.getText());
			java.sql.Date fromDate = new java.sql.Date(from_date.getTime());
			java.util.Date to_date = formatDate.parse(txtToDate.getText());
			java.sql.Date toDate = new java.sql.Date(to_date.getTime());
		
			try {
				Connection con = DBConnection.getConnection();
				String sql = "select * from issue_book where issue_date between ? and ?";
				PreparedStatement pst = con.prepareStatement(sql);
				pst.setDate(1, fromDate);
				pst.setDate(2, toDate);
				
				ResultSet rs = pst.executeQuery();
				if(rs.next()==false) {
					JOptionPane.showMessageDialog(this,"No Record Found");
				}
				else {
					while(rs.next()){
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
				
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	/**
	 * Create the frame.
	 */
	public ViewRecord() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1415, 747);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 139, 139));
		panel.setForeground(new Color(0, 0, 0));
		panel.setBounds(0, 0, 1415, 272);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("   View All Records");
		lblNewLabel_2.setIcon(new ImageIcon(ViewRecord.class.getResource("/AddNewBookIcons/AddNewBookIcons/icons8_Literature_100px_1.png")));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Sylfaen", Font.BOLD, 25));
		lblNewLabel_2.setBounds(540, 25, 317, 106);
		panel.add(lblNewLabel_2);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(null);
		panel_3.setBackground(Color.WHITE);
		panel_3.setBounds(550, 141, 307, 5);
		panel.add(panel_3);
		
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
		lblNewLabel.setIcon(new ImageIcon(ViewRecord.class.getResource("/AddNewBookIcons/AddNewBookIcons/icons8_Rewind_48px.png")));
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
		lblX.setBounds(1364, 10, 41, 38);
		panel.add(lblX);
		
		JLabel lblNewLabel_1_8_1 = new JLabel("Due Date :");
		lblNewLabel_1_8_1.setBounds(540, 222, 120, 23);
		panel.add(lblNewLabel_1_8_1);
		lblNewLabel_1_8_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_1_8_1.setFont(new Font("Verdana", Font.PLAIN, 18));
		
		JLabel lblNewLabel_1_8_2 = new JLabel("Issue Date :");
		lblNewLabel_1_8_2.setBounds(82, 218, 120, 23);
		panel.add(lblNewLabel_1_8_2);
		lblNewLabel_1_8_2.setForeground(new Color(0, 0, 0));
		lblNewLabel_1_8_2.setFont(new Font("Verdana", Font.PLAIN, 18));
		
		JButton btnSearch = new JButton("SEARCH");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					clearTable();
					search();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnSearch.setForeground(Color.WHITE);
		btnSearch.setFont(new Font("Verdana", Font.PLAIN, 18));
		btnSearch.setBorder(null);
		btnSearch.setBackground(new Color(0, 0, 0));
		btnSearch.setBounds(1007, 213, 197, 40);
		panel.add(btnSearch);
		
		txtFromDate = new JTextField();
		txtFromDate.setColumns(10);
		txtFromDate.setBounds(204, 203, 224, 38);
		panel.add(txtFromDate);
		
		txtToDate = new JTextField();
		txtToDate.setColumns(10);
		txtToDate.setBounds(657, 211, 224, 38);
		panel.add(txtToDate);
		
		JButton btnAll = new JButton("ALL");
		btnAll.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clearTable();
				setIssuedBookDetails();
			}
		});
		btnAll.setForeground(Color.WHITE);
		btnAll.setFont(new Font("Verdana", Font.PLAIN, 18));
		btnAll.setBorder(null);
		btnAll.setBackground(Color.BLACK);
		btnAll.setBounds(1240, 213, 143, 40);
		panel.add(btnAll);
		
		JLabel lblX_1 = new JLabel("X");
		lblX_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblX_1.setForeground(Color.BLACK);
		lblX_1.setFont(new Font("Verdana", Font.PLAIN, 20));
		lblX_1.setBounds(668, 122, 41, 38);
		contentPane.add(lblX_1);
		
		JPanel panel_table = new JPanel();
		panel_table.setBackground(new Color(255, 255, 255));
		panel_table.setBounds(0, 272, 1415, 475);
		contentPane.add(panel_table);
		panel_table.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		scrollPane_1.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(211, 211, 211), null, new Color(211, 211, 211), null));
		scrollPane_1.setBounds(109, 71, 1195, 225);
		panel_table.add(scrollPane_1);
		
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
