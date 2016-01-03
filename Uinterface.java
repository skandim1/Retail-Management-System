/*
 * Uinterface.java
 * Main class for UI implementation.
*/
package Project;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Color;

public class Uinterface {

	private JFrame frame;
	private Connection connection = null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Uinterface window = new Uinterface();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Uinterface() {
		initialize();
		connection = OracleConnection.dbConnector();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 552, 383);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton TableButton = new JButton("Display Tables");
		TableButton.setBackground(new Color(0, 206, 209));
		TableButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				showTables show = new showTables(connection);
				show.setVisible(true);
			}
		});
		TableButton.setBounds(209, 92, 188, 29);
		frame.getContentPane().add(TableButton);
		
		JButton ReportButton = new JButton("Monthly Sales Report");
		ReportButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showReport report = new showReport(connection);
				report.setVisible(true);
			}
		});
		ReportButton.setBounds(209, 134, 188, 29);
		frame.getContentPane().add(ReportButton);
		
		JButton ProductButton = new JButton("Add Product");
		ProductButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddProduct product = new AddProduct(connection);
				product.setVisible(true);
			}
		});
		ProductButton.setBounds(209, 214, 188, 29);
		frame.getContentPane().add(ProductButton);
		
		JButton PurchaseButton = new JButton("Add Purchase");
		PurchaseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddPurchase purchase = new AddPurchase(connection);
				purchase.setVisible(true);
			}
		});
		PurchaseButton.setBounds(209, 173, 188, 29);
		frame.getContentPane().add(PurchaseButton);
		
		JLabel lblNewLabel = new JLabel("Retail Business Management System");
		lblNewLabel.setForeground(SystemColor.controlHighlight);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 26));
		lblNewLabel.setBounds(94, 31, 405, 29);
		frame.getContentPane().add(lblNewLabel);
		
		JButton ConnectionButton = new JButton("Close Connection");
		ConnectionButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					connection.close();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, e1);
				}
			}
		});
		ConnectionButton.setBounds(209, 255, 188, 29);
		frame.getContentPane().add(ConnectionButton);
	}
}
