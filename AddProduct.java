package Project;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import oracle.jdbc.OracleTypes;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddProduct extends JFrame {

	private JPanel contentPane;
	private Connection connection = null;
	private JTextField PIDtextField;
	private JTextField PNAMEtextField;
	private JTextField QOHtextField;
	private JTextField QOH_ThresholdtextField;
	private JTextField PricetextField;
	private JTextField DiscnttextField;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					AddProduct frame = new AddProduct();
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
	public AddProduct(Connection conn) {
		//connection = OracleConnection.dbConnector();
		connection = conn;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 620, 474);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel PIDLabel = new JLabel("PID");
		PIDLabel.setBounds(153, 55, 21, 16);
		contentPane.add(PIDLabel);
		
		PIDtextField = new JTextField();
		PIDtextField.setBounds(186, 49, 134, 28);
		contentPane.add(PIDtextField);
		PIDtextField.setColumns(10);
		
		JLabel PNAMELabel = new JLabel("PNAME");
		PNAMELabel.setBounds(132, 95, 44, 16);
		contentPane.add(PNAMELabel);
		
		JLabel QOHLabel = new JLabel("QOH");
		QOHLabel.setBounds(142, 129, 32, 16);
		contentPane.add(QOHLabel);
		
		JLabel ThresholdLabel = new JLabel("QOH THRESHOLD");
		ThresholdLabel.setBounds(61, 166, 117, 16);
		contentPane.add(ThresholdLabel);
		
		JLabel Org_PriceLabel = new JLabel("ORIGINAL PRICE");
		Org_PriceLabel.setBounds(75, 199, 101, 16);
		contentPane.add(Org_PriceLabel);
		
		JLabel DiscntLabel = new JLabel("DISCOUNT RATE");
		DiscntLabel.setBounds(70, 227, 115, 16);
		contentPane.add(DiscntLabel);
		
		JLabel MessageLabel = new JLabel("Message");
		MessageLabel.setBounds(19, 311, 595, 36);
		contentPane.add(MessageLabel);
		
		PNAMEtextField = new JTextField();
		PNAMEtextField.setBounds(186, 89, 134, 28);
		contentPane.add(PNAMEtextField);
		PNAMEtextField.setColumns(10);
		
		QOHtextField = new JTextField();
		QOHtextField.setBounds(186, 123, 134, 28);
		contentPane.add(QOHtextField);
		QOHtextField.setColumns(10);
		
		QOH_ThresholdtextField = new JTextField();
		QOH_ThresholdtextField.setBounds(186, 160, 134, 28);
		contentPane.add(QOH_ThresholdtextField);
		QOH_ThresholdtextField.setColumns(10);
		
		PricetextField = new JTextField();
		PricetextField.setBounds(186, 189, 134, 28);
		contentPane.add(PricetextField);
		PricetextField.setColumns(10);
		
		DiscnttextField = new JTextField();
		DiscnttextField.setBounds(186, 221, 134, 28);
		contentPane.add(DiscnttextField);
		DiscnttextField.setColumns(10);
		
		JButton AddButton = new JButton("Add");
		AddButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pid = null;
				String pname = null;
				int qoh = 0;
				int qoh_threshold = 0;
				float discount_rate = 0.00f;
				float original_price = 0.00f;
				int flag = 0;
				try {
					
                    pid =  PIDtextField.getText();
                    pname = PNAMEtextField.getText();
                    qoh = Integer.parseInt(QOHtextField.getText());
                    qoh_threshold = Integer.parseInt(QOH_ThresholdtextField.getText());
                    original_price = Float.parseFloat(PricetextField.getText());
                    discount_rate = Float.parseFloat(DiscnttextField.getText());
				} catch(NullPointerException ex){
					MessageLabel.setText("Please give proper inputs");
					flag = 1;
				} catch(NumberFormatException ex){
					MessageLabel.setText("Please enter proper inputs.");
					flag = 1;
				}
				
				if(flag == 0){
					try {
                   
                    if(pid != null && !pid.isEmpty() && pname != null && !pname.isEmpty()){
                        if(qoh > 0 && qoh_threshold > 0 && original_price > 0 && discount_rate > 0 && discount_rate < 0.8){
                        	CallableStatement cs = connection.prepareCall("begin ? := retail_add.add_product(?,?,?,?,?,?);end;");
                            cs.registerOutParameter(1, OracleTypes.NUMBER);
                            cs.setString(2, pid);
                            cs.setString(3, pname);
                            cs.setInt(4, qoh);
                            cs.setInt(5, qoh_threshold);
                            cs.setFloat(6, original_price);
                            cs.setFloat(7, discount_rate);
                            cs.execute();
                    int ret = cs.getInt(1);
                    if(ret == 1)
                     {
                    	MessageLabel.setText("Product details added successfully to the products table");
                         
                     }
                    }
                    }
                    
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Unable to add product");
				} 
				}
			}
		});
		AddButton.setBounds(198, 275, 117, 29);
		contentPane.add(AddButton);
	}

}
