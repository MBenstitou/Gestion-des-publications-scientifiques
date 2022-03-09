package frames.pair;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

import connection.MyConnection;

public class PairDeciderFrame extends JFrame implements ActionListener {
	
	JLabel maDecision,J,titreFenetre;
	JButton  valider , retourner;
	JRadioButton decision1, decision2, decision3 , decision4;
	MyConnection myConnection;
	int idPair, idArticle;
	String username;
	
	
	public PairDeciderFrame(int idPair, int idArticle, String username) {
		
		this.idArticle =idArticle;
		this.idPair =idPair;
		this.username =username;
		
		
		J= new JLabel("");
	    J.setIcon(new ImageIcon("icons/bleuFonce.jpg"));
	    J.setBounds(0, 0, 650, 450);
	    getContentPane().add(J, BorderLayout.NORTH);
	    
	    JLabel J1 = new JLabel("");
	    J1.setIcon(new ImageIcon("icons/homme.jpeg"));
	    J1.setBounds(0, 0, 100, 100);
	    J.add(J1);
		
	    titreFenetre = new JLabel("Décisions");
		titreFenetre.setBounds(150, 30, 200, 50);
		titreFenetre.setFont(new Font("Calibri", Font.BOLD+Font.ITALIC, 34));
		titreFenetre.setForeground(new Color(255, 140, 0));
		J.add(titreFenetre);
	    
		maDecision = new JLabel("Ma decision");
		maDecision.setBounds(250, 100, 200, 30);
		maDecision.setFont(new Font("Serif",Font.CENTER_BASELINE+Font.ITALIC,30));
		J.add(maDecision);
		
		decision1 = new JRadioButton("Révision majeure",true);
		decision1.setFont(new Font("Serif",Font.BOLD,20));
		decision1.setBounds(250, 150, 230, 30);
		J.add(decision1);
		
		decision2 = new JRadioButton("Révision mineure");
		decision2.setFont(new Font("Serif",Font.BOLD,20));
		decision2.setBounds(250, 170, 230, 30);
		J.add(decision2);

		
		decision3 = new JRadioButton("Décision acceptation");
		decision3.setFont(new Font("Serif",Font.BOLD,20));
		decision3.setBounds(250, 190, 230, 30);
		J.add(decision3);

		
		decision4 = new JRadioButton("Décision refus");
		decision4.setFont(new Font("Serif",Font.BOLD,20));
		decision4.setBounds(250, 210, 230, 30);
		J.add(decision4);
		
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(decision1); 
		bg.add(decision2); 
		bg.add(decision3); 
		bg.add(decision4); 
		
		valider = new JButton("Valider"); // pour afficher les decisions des pairs
		valider.setFocusable(false);
		valider.addActionListener(this);
		valider.setBounds(40, 150, 150, 40);		
		valider.setFont(new Font("Serif",Font.ITALIC,20));
		J.add(valider);
		
		retourner = new JButton("retourner"); 
		retourner.setFocusable(false);
		retourner.addActionListener(this);
		retourner.setBounds(40, 200, 150, 40);		
		retourner.setFont(new Font("Serif",Font.ITALIC,20));
		J.add(retourner);
		
		this.setTitle("Decider");
		this.setSize(500, 350);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(null);
		this.setVisible(true);
		
		
		
		
	}

//**************************************************************
	
	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource()==valider) {

		
			int option = JOptionPane.showConfirmDialog(this,
		             "Voulez vous vraiment attribuer cet décision", 
		             "Confirmation",
		             JOptionPane.OK_CANCEL_OPTION);
			
			if(option == JOptionPane.OK_OPTION) {
			
				String decision = "";
				
				if(decision1.isSelected()) {
					
					decision = decision1.getText();
					
				}
				else if(decision2.isSelected()) {
					
					decision = decision2.getText();
					
				}
				else if(decision3.isSelected()) {
					
					decision = decision3.getText();
					
				}
				else{
					
					decision = decision4.getText();
					
				}
				
				
				try {
					
					
					myConnection = new MyConnection();
					myConnection.setSt(myConnection.getCn().createStatement()) ;
						
						
						String sql = "UPDATE evaluer SET  "
								+ "decision = '"+decision+"' "
												+ " WHERE idArticle = "+idArticle+" AND idChercheur="+idPair+"";
						
						myConnection.getSt().executeUpdate(sql) ;
						
						JOptionPane.showMessageDialog(this,
								"Votre décision a été bien ajoutée", 
					             "Validation",
					             JOptionPane.OK_OPTION);
		
					
						myConnection.fermerConnection();
					
					
				} catch (SQLException e1) {
		
					e1.printStackTrace();
		
					JOptionPane.showMessageDialog(this,
							e1.getMessage(), 
				             "Warning",
				             JOptionPane.OK_OPTION);
					
					
				}
				
			}
	}
		
		if(e.getSource() == retourner) {
			
			
			new PairFrame(idPair,String.valueOf(username));
			this.dispose();
			
		}
		
	}
	
	

}
