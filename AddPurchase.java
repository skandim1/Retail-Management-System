package Project;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import net.proteanit.sql.DbUtils;
import oracle.jdbc.OracleTypes;

import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class AddPurchase extends JFrame {

	private JPanel contentPane;
	private Connection connection = null;
	private JTextField EIDtextField;
	private JTextField PIDtextField;
	private JTextField CIDtextField;
	private JTextField QtytextField;

	/**
	 * Create the frame.
	 */
	public AddPurchase(Connection conn) {
		//connection = OracleConnection.dbConnector();
		connection = conn;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 615, 461);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel EIDLabel = new JLabel("EID");
		EIDLabel.setBounds(211, 45, 21, 16);
		contentPane.add(EIDLabel);
		
		JLabel PIDLabel = new JLabel("PID");
		PIDLabel.setBounds(211, 90, 27, 16);
		contentPane.add(PIDLabel);
		
		JLabel CIDLabel = new JLabel("CID");
		CIDLabel.setBounds(211, 136, 27, 16);
		contentPane.add(CIDLabel);
		
		JLabel QtyLabel = new JLabel("Qunatity");
		QtyLabel.setBounds(177, 179, 61, 16);
		contentPane.add(QtyLabel);
		
		JLabel MessageLabel = new JLabel("Message :");
		MessageLabel.setBounds(16, 252, 593, 43);
		contentPane.add(MessageLabel);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(27, 319, 582, 114);
		contentPane.add(textArea);
		
		JButton AddButton = new JButton("Add");
		AddButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String eid = null;
	        	String pid = null;
	        	String cid = null;
	        	String qty = null;
	        	int flag = 0;
	        	try{
	        		eid = EIDtextField.getText();
	        		pid = PIDtextField.getText();
	        		cid = CIDtextField.getText();
	        		qty = QtytextField.getText();
	        	    if(eid.isEmpty() || pid.isEmpty() || cid.isEmpty() || qty.isEmpty()) {
	        	    	flag = 1;
	        	    	JOptionPane.showMessageDialog(null, "Please enter some data ");
	        	    }
	        	} catch(NullPointerException ex) {
	        		MessageLabel.setText("Please enter proper values.");
	        		flag = 1;
	        	}
				
				if(flag == 0) {
				try {
					TriggerMessage dbmsOutput = new TriggerMessage( connection );
					dbmsOutput.enable( 1000000 );
					CallableStatement cs = connection.prepareCall("begin ? := retail_add.add_purchase(?,?,?,?);end;");
                    cs.registerOutParameter(1, OracleTypes.NUMBER);
                    cs.setString(2, eid);
                    cs.setString(3, pid);
                    cs.setString(4, cid);
                    cs.setString(5, qty);
                    cs.execute();

                    int ret = cs.getInt(1);
                    if(ret == 1)
                     {
                    	MessageLabel.setText("Purchase details added successfully to the purchase table");
                         
                     }
                     else if(ret == 100)
                     {
                    	 MessageLabel.setText("The pid is not present in the products");
                             
                     }
                     else if(ret == 101)
                     {
                    	 MessageLabel.setText("The cid is not present in the customers");
                           
                     }
                     else if(ret == 102)
                     {
                    	 MessageLabel.setText("The eid is not present in the employees");
                        
                     }
                     else if(ret == 103)
                     {
                    	 MessageLabel.setText("The quantity should be greater than 0");
                        
                     }
                     else {
                    	 MessageLabel.setText("Please enter valid arguments");
                             
                             
                     }
                    
                    textArea.setText(dbmsOutput.show());
                    dbmsOutput.close();
                    
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Insufficient quantity in stock.");
				}
				}
			}
		});
		AddButton.setBackground(SystemColor.textHighlight);
		AddButton.setBounds(245, 211, 117, 29);
		contentPane.add(AddButton);
		
		EIDtextField = new JTextField();
		EIDtextField.setBounds(244, 39, 134, 28);
		contentPane.add(EIDtextField);
		EIDtextField.setColumns(10);
		
		PIDtextField = new JTextField();
		PIDtextField.setBounds(244, 84, 134, 28);
		contentPane.add(PIDtextField);
		PIDtextField.setColumns(10);
		
		CIDtextField = new JTextField();
		CIDtextField.setBounds(244, 130, 134, 28);
		contentPane.add(CIDtextField);
		CIDtextField.setColumns(10);
		
		QtytextField = new JTextField();
		QtytextField.setBounds(244, 173, 134, 28);
		contentPane.add(QtytextField);
		QtytextField.setColumns(10);
		
		
		
		
	}

}
