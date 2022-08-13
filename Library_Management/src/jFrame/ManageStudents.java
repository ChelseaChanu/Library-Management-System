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

public class ManageStudents extends JFrame {

	private JPanel contentPane;
	private JTextField txtBranch;
	private JTextField txtCourse;
	private JTextField txtStuName;
	private JTextField txtStuId;
	private JTable tbl_StuDetails;

	// book details 
	String studentName,course,branch;
	int studentId;
	DefaultTableModel model;
	private JTextField txtEnterStudentid;
	private JTextField txtEnterStudentName;
	private JTextField txtCourse_1;
	private JTextField txtBranch_1;
	
	public void setStudentDetails() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
        	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_manag","root","Chel4@sea");
        	Statement st = con.createStatement();
        	ResultSet rs = st.executeQuery("select * from student_details");
        	
        	while(rs.next()) {
        		String student_Id = rs.getString("student_Id");
        		String student_name = rs.getString("name");
        		String course = rs.getString("course");
        		String branch = rs.getString("branch");
        		
        		Object obj[] = {student_Id,student_name,course,branch};
        		model = (DefaultTableModel)tbl_StuDetails.getModel();
        		model.addRow(obj);
        	}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	} 
	
	//to add books
	public boolean addStudent() {
		boolean isAdded = false;
		studentId = Integer.parseInt(txtStuId.getText());
	    studentName = txtStuName.getText();
		course = txtCourse.getText();
		branch = txtBranch.getText();
		
		try {
        	Connection con = DBConnection.getConnection();
        	String sql = "insert into student_details values (?,?,?,?)";
        	
        	PreparedStatement pst = con.prepareStatement(sql);
        	pst.setInt(1, studentId);
        	pst.setString(2,studentName);
        	pst.setString(3, course);
        	pst.setString(4, branch);
        	
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
	public boolean updateStudent() {
		boolean isUpdated = false;
		studentId = Integer.parseInt(txtStuId.getText());
		studentName = txtStuName.getText();
		course = txtCourse.getText();
		branch = txtBranch.getText();
		
		try {
			Connection con = DBConnection.getConnection();
			String sql = "update student_details  set name = ?, course = ?, branch = ? where student_Id = ?";
			
			PreparedStatement pst = con.prepareStatement(sql);
        	pst.setString(1, studentName);
        	pst.setString(2, course);
        	pst.setString(3, branch);
        	pst.setInt(4, studentId);
        	
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
		studentId = Integer.parseInt(txtStuId.getText());
		
		try {
			Connection con = DBConnection.getConnection();
			String sql = "delete from student_details where student_Id = ?";
			
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, studentId);
			
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
		DefaultTableModel model = (DefaultTableModel)tbl_StuDetails.getModel();
		model.setRowCount(0);
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageStudents frame = new ManageStudents();
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
	public ManageStudents() {
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
		lblNewLabel.setIcon(new ImageIcon(ManageStudents.class.getResource("/AddNewBookIcons/AddNewBookIcons/icons8_Rewind_48px.png")));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Verdana", Font.PLAIN, 15));
		lblNewLabel.setBounds(10, 10, 130, 28);
		panel_1.add(lblNewLabel);
		
		txtBranch = new JTextField();
		txtBranch.setHorizontalAlignment(SwingConstants.CENTER);
		txtBranch.setForeground(new Color(0, 0, 0));
		txtBranch.setFont(new Font("Arial", Font.PLAIN, 18));
		txtBranch.setColumns(10);
		txtBranch.setBorder(new MatteBorder(0, 0, 3, 0, (Color) new Color(255, 255, 255)));
		txtBranch.setBackground(new Color(32, 178, 170));
		txtBranch.setBounds(106, 548, 355, 33);
		panel.add(txtBranch);
		
		txtCourse = new JTextField();
		txtCourse.setHorizontalAlignment(SwingConstants.CENTER);
		txtCourse.setForeground(Color.BLACK);
		txtCourse.setFont(new Font("Arial", Font.PLAIN, 18));
		txtCourse.setColumns(10);
		txtCourse.setBorder(new MatteBorder(0, 0, 3, 0, (Color) new Color(255, 255, 255)));
		txtCourse.setBackground(new Color(32, 178, 170));
		txtCourse.setBounds(106, 444, 355, 33);
		panel.add(txtCourse);
		
		txtStuName = new JTextField();
		txtStuName.setHorizontalAlignment(SwingConstants.CENTER);
		txtStuName.setForeground(Color.BLACK);
		txtStuName.setFont(new Font("Arial", Font.PLAIN, 18));
		txtStuName.setColumns(10);
		txtStuName.setBorder(new MatteBorder(0, 0, 3, 0, (Color) new Color(255, 255, 255)));
		txtStuName.setBackground(new Color(32, 178, 170));
		txtStuName.setBounds(106, 341, 355, 33);
		panel.add(txtStuName);
		
		txtStuId = new JTextField();
		txtStuId.setHorizontalAlignment(SwingConstants.CENTER);
		txtStuId.setForeground(Color.BLACK);
		txtStuId.setFont(new Font("Arial", Font.PLAIN, 18));
		txtStuId.setColumns(10);
		txtStuId.setBorder(new MatteBorder(0, 0, 3, 0, (Color) new Color(255, 255, 255)));
		txtStuId.setBackground(new Color(32, 178, 170));
		txtStuId.setBounds(106, 231, 355, 33);
		panel.add(txtStuId);
		
		JButton btnNewButton = new JButton("ADD");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(addStudent()==true) {
					JOptionPane.showMessageDialog(null, "Student is Added Successfully");
					clearTable();
					setStudentDetails();
				}
				else {
					JOptionPane.showMessageDialog(null, "Student Addition Fail");
				}
			}
		});
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(0, 128, 128));
		btnNewButton.setBorder(null);
		btnNewButton.setFont(new Font("Verdana", Font.BOLD, 20));
		btnNewButton.setBounds(38, 644, 130, 43);
		panel.add(btnNewButton);
		
		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(updateStudent()==true) {
					JOptionPane.showMessageDialog(null, "Student Updated Successfully");
					clearTable();
					setStudentDetails();
				}
				else {
					JOptionPane.showMessageDialog(null, "Student Updation Fail");
				}
			}
		});
		btnUpdate.setForeground(Color.WHITE);
		btnUpdate.setFont(new Font("Verdana", Font.BOLD, 20));
		btnUpdate.setBorder(null);
		btnUpdate.setBackground(new Color(0, 128, 128));
		btnUpdate.setBounds(217, 644, 132, 43);
		panel.add(btnUpdate);
		
		JButton btnDelete = new JButton("DELETE");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(deleteBook()==true) {
					JOptionPane.showMessageDialog(null, "Student Deletion Successfully");
					clearTable();
					setStudentDetails();
				}
				else {
					JOptionPane.showMessageDialog(null, "Student Deletion Fail");
				}
			}
		});
		btnDelete.setForeground(Color.WHITE);
		btnDelete.setFont(new Font("Verdana", Font.BOLD, 20));
		btnDelete.setBorder(null);
		btnDelete.setBackground(new Color(0, 128, 128));
		btnDelete.setBounds(405, 644, 130, 43);
		panel.add(btnDelete);
		
		txtEnterStudentid = new JTextField();
		txtEnterStudentid.setText("Enter Student_Id :");
		txtEnterStudentid.setForeground(Color.BLACK);
		txtEnterStudentid.setFont(new Font("Verdana", Font.PLAIN, 18));
		txtEnterStudentid.setEditable(false);
		txtEnterStudentid.setColumns(10);
		txtEnterStudentid.setBorder(null);
		txtEnterStudentid.setBackground(new Color(32, 178, 170));
		txtEnterStudentid.setBounds(101, 179, 233, 33);
		panel.add(txtEnterStudentid);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("");
		lblNewLabel_1_1_2.setIcon(new ImageIcon(ManageStudents.class.getResource("/AddNewBookIcons/AddNewBookIcons/icons8_Contact_26px.png")));
		lblNewLabel_1_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_2.setBounds(50, 231, 32, 33);
		panel.add(lblNewLabel_1_1_2);
		
		txtEnterStudentName = new JTextField();
		txtEnterStudentName.setText("Enter Student Name");
		txtEnterStudentName.setForeground(Color.BLACK);
		txtEnterStudentName.setFont(new Font("Verdana", Font.PLAIN, 18));
		txtEnterStudentName.setEditable(false);
		txtEnterStudentName.setColumns(10);
		txtEnterStudentName.setBorder(null);
		txtEnterStudentName.setBackground(new Color(32, 178, 170));
		txtEnterStudentName.setBounds(101, 298, 233, 33);
		panel.add(txtEnterStudentName);
		
		txtCourse_1 = new JTextField();
		txtCourse_1.setText("Course");
		txtCourse_1.setForeground(Color.BLACK);
		txtCourse_1.setFont(new Font("Verdana", Font.PLAIN, 18));
		txtCourse_1.setEditable(false);
		txtCourse_1.setColumns(10);
		txtCourse_1.setBorder(null);
		txtCourse_1.setBackground(new Color(32, 178, 170));
		txtCourse_1.setBounds(106, 401, 233, 33);
		panel.add(txtCourse_1);
		
		txtBranch_1 = new JTextField();
		txtBranch_1.setText("Branch");
		txtBranch_1.setForeground(Color.BLACK);
		txtBranch_1.setFont(new Font("Verdana", Font.PLAIN, 18));
		txtBranch_1.setEditable(false);
		txtBranch_1.setColumns(10);
		txtBranch_1.setBorder(null);
		txtBranch_1.setBackground(new Color(32, 178, 170));
		txtBranch_1.setBounds(106, 505, 233, 33);
		panel.add(txtBranch_1);
		
		JLabel lblNewLabel_1_1_2_1 = new JLabel("");
		lblNewLabel_1_1_2_1.setIcon(new ImageIcon(ManageStudents.class.getResource("/AddNewBookIcons/AddNewBookIcons/icons8_Moleskine_26px.png")));
		lblNewLabel_1_1_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_2_1.setBounds(50, 341, 32, 33);
		panel.add(lblNewLabel_1_1_2_1);
		
		JLabel lblNewLabel_1_1_2_2 = new JLabel("");
		lblNewLabel_1_1_2_2.setIcon(new ImageIcon(ManageStudents.class.getResource("/AddNewBookIcons/AddNewBookIcons/icons8_Collaborator_Male_26px.png")));
		lblNewLabel_1_1_2_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_2_2.setBounds(50, 444, 32, 33);
		panel.add(lblNewLabel_1_1_2_2);
		
		JLabel lblNewLabel_1_1_2_3 = new JLabel("");
		lblNewLabel_1_1_2_3.setIcon(new ImageIcon(ManageStudents.class.getResource("/AddNewBookIcons/AddNewBookIcons/icons8_Unit_26px.png")));
		lblNewLabel_1_1_2_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_2_3.setBounds(50, 548, 32, 33);
		panel.add(lblNewLabel_1_1_2_3);
		
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
		scrollPane_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int rowNo = tbl_StuDetails.getSelectedRow();
				TableModel model = tbl_StuDetails.getModel();
				txtStuId.setText(model.getValueAt(rowNo, 0).toString());
				txtStuName.setText(model.getValueAt(rowNo, 1).toString());
				txtCourse.setText(model.getValueAt(rowNo, 2).toString());
				txtBranch.setText(model.getValueAt(rowNo, 3).toString());
			}
		});
		scrollPane_1.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		scrollPane_1.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(211, 211, 211), null, new Color(211, 211, 211), null));
		scrollPane_1.setBounds(55, 285, 697, 216);
		panel_2.add(scrollPane_1);
		
		tbl_StuDetails = new JTable();
		tbl_StuDetails.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tbl_StuDetails.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int rowNo = tbl_StuDetails.getSelectedRow();
				TableModel model = tbl_StuDetails.getModel();
				txtStuId.setText(model.getValueAt(rowNo, 0).toString());
				txtStuName.setText(model.getValueAt(rowNo, 1).toString());
				txtCourse.setText(model.getValueAt(rowNo, 2).toString());
				txtBranch.setText(model.getValueAt(rowNo, 3).toString());
			}
		});
		tbl_StuDetails.setBackground(new Color(64, 224, 208));
		scrollPane_1.setViewportView(tbl_StuDetails);
		tbl_StuDetails.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Student_Id", "Name", "Course", "Branch"
			}
		));
		tbl_StuDetails.setRowMargin(5);
		tbl_StuDetails.setRowHeight(30);
		tbl_StuDetails.setEditingRow(5);
		tbl_StuDetails.setEditingColumn(4);
		tbl_StuDetails.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(192, 192, 192), null, null, null));
		
		JLabel lblNewLabel_2 = new JLabel("   Manage Students");
		lblNewLabel_2.setIcon(new ImageIcon(ManageStudents.class.getResource("/AddNewBookIcons/AddNewBookIcons/icons8_Student_Male_100px.png")));
		lblNewLabel_2.setForeground(new Color(255, 69, 0));
		lblNewLabel_2.setFont(new Font("Sylfaen", Font.BOLD, 26));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(224, 48, 394, 100);
		panel_2.add(lblNewLabel_2);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(null);
		panel_3.setBackground(new Color(255, 69, 0));
		panel_3.setBounds(234, 166, 371, 5);
		panel_2.add(panel_3);
		
		setStudentDetails();
	}
}
