package Project;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;
import oracle.jdbc.OracleTypes;

public class showReport extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private Connection connection = null;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					showReport frame = new showReport();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public showReport(Connection conn) {
		//connection = OracleConnection.dbConnector();
		connection = conn;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 615, 458);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton ReportButton = new JButton("Monthly Sales Report");
		ReportButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String pid = null;
				int flag = 0;
				try{
					pid = textField.getText();
				}catch(NullPointerException ex){
					JOptionPane.showMessageDialog(null, "Please enter proper values.");
					flag = 1;
				}
				
				
				if(flag == 0){
                try {
                	CallableStatement cs = connection.prepareCall("begin ? := retail.report_monthly_sale(?);end;");
					cs.registerOutParameter(1, OracleTypes.CURSOR);
					cs.setString(2, pid);
					cs.execute();
					
					ResultSet rs = (ResultSet)cs.getObject(1);
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, e1);
				}
				}
			}
		});
		ReportButton.setBounds(175, 99, 233, 29);
		contentPane.add(ReportButton);
		
		table = new JTable();
		table.setBounds(57, 140, 524, 290);
		contentPane.add(table);
		
		JLabel pidLabel = new JLabel("PID of product");
		pidLabel.setBounds(105, 58, 96, 16);
		contentPane.add(pidLabel);
		
		textField = new JTextField();
		textField.setBounds(213, 52, 157, 28);
		contentPane.add(textField);
		textField.setColumns(10);
	}
}
