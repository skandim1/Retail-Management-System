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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;
import oracle.jdbc.OracleTypes;


public class showTables extends JFrame {

	private JPanel contentPane;
	private JTable DataTable;
	private Connection connection = null;
    
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					showTables frame = new showTables();
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
	public showTables(Connection conn) {
		//connection = OracleConnection.dbConnector();
		connection = conn;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 628, 470);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton EmpButton = new JButton("Employees");
		EmpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 
	                try {
	                	CallableStatement cs = connection.prepareCall("begin ? := retail.show_employees();end;");
						cs.registerOutParameter(1, OracleTypes.CURSOR);
						cs.execute();
						
						ResultSet rs = (ResultSet)cs.getObject(1);
						DataTable.setModel(DbUtils.resultSetToTableModel(rs));
						
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, e1);
					}
	               
			}
		});
		EmpButton.setBounds(36, 49, 117, 29);
		contentPane.add(EmpButton);
		
		JButton CustomerButton = new JButton("Customers");
		CustomerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					CallableStatement cs = connection.prepareCall("begin ? := retail.show_customers();end;");
					cs.registerOutParameter(1, OracleTypes.CURSOR);
					cs.execute();
					
					ResultSet rs = (ResultSet)cs.getObject(1);
					DataTable.setModel(DbUtils.resultSetToTableModel(rs));
					
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, e1);
				}
			}
		});
		CustomerButton.setBounds(36, 90, 117, 29);
		contentPane.add(CustomerButton);
		
		JButton ProdButton = new JButton("Products");
		ProdButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
                	CallableStatement cs = connection.prepareCall("begin ? := retail.show_products();end;");
					cs.registerOutParameter(1, OracleTypes.CURSOR);
					cs.execute();
					
					ResultSet rs = (ResultSet)cs.getObject(1);
					DataTable.setModel(DbUtils.resultSetToTableModel(rs));
					
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, e1);
				}
			}
		});
		ProdButton.setBounds(36, 131, 117, 29);
		contentPane.add(ProdButton);
		
		JButton PurchaseButton = new JButton("Purchases");
		PurchaseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
                	CallableStatement cs = connection.prepareCall("begin ? := retail.show_purchases();end;");
					cs.registerOutParameter(1, OracleTypes.CURSOR);
					cs.execute();
					
					ResultSet rs = (ResultSet)cs.getObject(1);
					DataTable.setModel(DbUtils.resultSetToTableModel(rs));
					
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, e1);
				}
			}
		});
		PurchaseButton.setBounds(36, 172, 117, 29);
		contentPane.add(PurchaseButton);
		
		JButton SupplierButton = new JButton("Suppliers");
		SupplierButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
                	CallableStatement cs = connection.prepareCall("begin ? := retail.show_suppliers();end;");
					cs.registerOutParameter(1, OracleTypes.CURSOR);
					cs.execute();
					
					ResultSet rs = (ResultSet)cs.getObject(1);
					DataTable.setModel(DbUtils.resultSetToTableModel(rs));
					
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, e1);
				}
			}
		});
		SupplierButton.setBounds(36, 213, 117, 29);
		contentPane.add(SupplierButton);
		
		JButton SupplyButton = new JButton("Supply");
		SupplyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
                	CallableStatement cs = connection.prepareCall("begin ? := retail.show_supply();end;");
					cs.registerOutParameter(1, OracleTypes.CURSOR);
					cs.execute();
					
					ResultSet rs = (ResultSet)cs.getObject(1);
					DataTable.setModel(DbUtils.resultSetToTableModel(rs));
					
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, e1);
				}
			}
		});
		SupplyButton.setBounds(36, 254, 117, 29);
		contentPane.add(SupplyButton);
		
		JButton LogsButton = new JButton("Logs");
		LogsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
                	CallableStatement cs = connection.prepareCall("begin ? := retail.show_logs();end;");
					cs.registerOutParameter(1, OracleTypes.CURSOR);
					cs.execute();
					
					ResultSet rs = (ResultSet)cs.getObject(1);
					DataTable.setModel(DbUtils.resultSetToTableModel(rs));
					
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, e1);
				}
			}
		});
		LogsButton.setBounds(36, 295, 117, 29);
		contentPane.add(LogsButton);
		
		
		DataTable = new JTable();
		DataTable.setBounds(185, 23, 437, 419);
		contentPane.add(DataTable);
		
		JScrollPane scrollPane = new JScrollPane(DataTable);
		scrollPane.setBounds(184, 49, 438, 356);
		contentPane.add(scrollPane);
		
//		JScrollPane js=new JScrollPane(DataTable);
//		js.setVisible(true);
//		contentPane.add(js);

	}
}
