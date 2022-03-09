package frames.editeur;

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
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import connection.MyConnection;

public class EditeurDeciderFrame  extends JFrame implements ActionListener{

	JLabel titreFenetre, maDecision , pairsDecisions;
	JLabel pairdecision1, pairdecision2, pairdecision3, pairdecision4 ,J;
	JButton decider , valider, acceuil;
	JRadioButton decision1, decision2, decision3 , decision4;
	MyConnection myConnection;
	JPanel paneau;
	String username;
	int idArti , taille;
	
	public EditeurDeciderFrame(int idArti,String username) {
		
		this.idArti =idArti;
		this.username = username;
		
		
		
		J= new JLabel("");
	    J.setIcon(new ImageIcon("icons/bleuFonce.jpg"));
	    J.setBounds(0, 0, 650, 450);
	    getContentPane().add(J, BorderLayout.NORTH);
		
		paneau =new JPanel();
		titreFenetre = new JLabel("Décisions");
		titreFenetre.setBounds(250, 30, 200, 50);
		titreFenetre.setFont(new Font("Calibri", Font.BOLD+Font.ITALIC, 34));
		titreFenetre.setForeground(new Color(255, 140, 0));
		J.add(titreFenetre);
		
		
		maDecision = new JLabel("Ma decision");
		maDecision.setBounds(370, 100, 200, 30);
		maDecision.setFont(new Font("Serif",Font.CENTER_BASELINE+Font.ITALIC,30));
		J.add(maDecision);
		

		decision1 = new JRadioButton("Révision majeure",true);
		decision1.setBackground(new Color(255, 0, 0));
		decision1.setFont(new Font("Serif",Font.BOLD,20));
		decision1.setBounds(370, 140, 230, 30);
		J.add(decision1);
	
		
		decision2 = new JRadioButton("Révision mineure");
		decision2.setBackground(new Color(176, 196, 222));
		decision2.setFont(new Font("Serif",Font.BOLD,20));
		decision2.setBounds(370, 160, 230, 30);
		J.add(decision2);
		

		decision3 = new JRadioButton("Décision acceptation");
		decision3.setBackground(new Color(144, 238, 144));
		decision3.setFont(new Font("Serif",Font.BOLD,20));
		decision3.setBounds(370, 180, 230, 30);
		J.add(decision3);

		
		decision4 = new JRadioButton("Décision refus");
		decision4.setBackground(new Color(222, 184, 135));
		decision4.setFont(new Font("Serif",Font.BOLD,20));
		decision4.setBounds(370, 200, 230, 30);
		J.add(decision4);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(decision1); 
		bg.add(decision2); 
		bg.add(decision3); 
		bg.add(decision4); 
		
		
		pairsDecisions = new JLabel("Décisions des pairs");
		pairsDecisions.setBounds(40, 100, 300, 40);
		pairsDecisions.setFont(new Font("Serif",Font.CENTER_BASELINE+Font.ITALIC,30));
		J.add(pairsDecisions);
		
		pairdecision1 = new JLabel("");
		pairdecision1.setFont(new Font("Serif",Font.BOLD,20));
		pairdecision1.setBounds(40, 260, 200, 30);
		pairdecision1.setForeground(Color.RED);
		J.add(pairdecision1);
		
		pairdecision2 = new JLabel("");
		pairdecision2.setFont(new Font("Serif",Font.BOLD,20));
		pairdecision2.setBounds(40, 290, 200, 30);
		pairdecision2.setForeground(Color.RED);
		J.add(pairdecision2);
		
		pairdecision3 = new JLabel("");
		pairdecision3.setFont(new Font("Serif",Font.BOLD,20));
		pairdecision3.setBounds(40, 320, 200, 30);
		pairdecision3.setForeground(Color.RED);
		J.add(pairdecision3);
		
		pairdecision4 = new JLabel("");
		pairdecision4.setFont(new Font("Serif",Font.BOLD,20));
		pairdecision4.setBounds(40, 350, 350, 30);
		pairdecision4.setForeground(Color.RED);
		J.add(pairdecision4);
		
		decider = new JButton("Decider");
		decider.setBackground(new Color(255, 140, 0));
		decider.setFocusable(false);
		decider.setBounds(370, 270, 150, 50);
		decider.setEnabled(false);
		decider.setFont(new Font("Calibri", Font.BOLD+Font.ITALIC, 30));
		J.add(decider);
		
		
		
		valider = new JButton("Valider"); // pour afficher les decisions des pairs
		valider.setBackground(new Color(144, 238, 144));
		valider.setFocusable(false);
		valider.setBounds(60, 170, 200, 40);
		valider.addActionListener(this);
		valider.setFont(new Font("Calibri", Font.BOLD+Font.ITALIC, 24));
		J.add(valider);
		
		

		acceuil = new JButton("Acceuil");
		acceuil.setForeground(new Color(0, 0, 0));
		acceuil.setBackground(new Color(222, 184, 135));
		acceuil.setFocusable(false);
		acceuil.addActionListener(this);
		acceuil.setBounds(370, 340, 150, 50);
		acceuil.setFont(new Font("Calibri", Font.BOLD+Font.ITALIC, 30));
		J.add(acceuil);
	     
		
		this.setTitle("Decider");
		this.setSize(650, 450);
		this.setLocationRelativeTo(null);
		this.getContentPane().setLayout(new BorderLayout());
		this.setLocationRelativeTo(null);
	    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	    this.setResizable(false);
		this.setVisible(true);
		
	}
		
//*******************************************************************
	
	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == valider) {
			
			pairdecision1.setText("");
			pairdecision2.setText("");
			pairdecision3.setText("");
			pairdecision4.setText("");

					try {
						
						

						myConnection = new MyConnection();
						myConnection.setSt(myConnection.getCn().createStatement()) ;
						
						
						String sql = "SELECT  taille, decision FROM evaluer, articles "
												+ " WHERE articles.idArticle = "+idArti+" AND "
												+ "articles.idArticle = evaluer.idArticle AND "
												+ "modifie = 1";
						
						myConnection.setRs(myConnection.getSt().executeQuery(sql));
						
						String parDec1 ="1-";
						
						if( myConnection.getRs().next()) {
							
						taille = myConnection.getRs().getInt("taille");
						parDec1 = "1-"+myConnection.getRs().getString("decision");
						
						}
						
						String parDec2 ="3-";
						
						if( myConnection.getRs().next()) {
							
							parDec2 = "2-"+myConnection.getRs().getString("decision");

						}

						String parDec3 = "3-";
						if( myConnection.getRs().next()) {
							
						parDec3 = "3-"+myConnection.getRs().getString("decision");
						
						}

						String parDec4 = "4-";
						
						if(myConnection.getRs().next()) {
							
							 parDec4 = "4-"+myConnection.getRs().getString("decision");

							
						}

						String sql2 = "SELECT  COUNT(*) AS NB FROM evaluer, articles "
								+ " WHERE articles.idArticle = "+idArti+" AND "
								+ "articles.idArticle = evaluer.idArticle AND "
								+ "modifie = 1";
						
						myConnection.setRs(myConnection.getSt().executeQuery(sql2));
						myConnection.getRs().next();

						int NB = myConnection.getRs().getInt("NB");
					
						
						if(NB==3 && taille <= 4000) {
							
							pairdecision1.setText(parDec1);

							pairdecision2.setText(parDec2);
							
							pairdecision3.setText(parDec3);
								
								if(!pairdecision1.getText().equals("1-null") && !pairdecision2.getText().equals("2-null") && !pairdecision3.getText().equals("3-null") ) {
									
									decider.setEnabled(true);
								}
								else {
									
									JOptionPane.showMessageDialog(this,
								             "les décisions des pairs ne sont pas complètes", 
								             "Avertissement",
								             JOptionPane.OK_OPTION);
								}
								
								

						}
						else if(NB==4 && taille > 4000) {
							
							pairdecision1.setText(parDec1);

							pairdecision2.setText(parDec2);
							
							pairdecision3.setText(parDec3);
							
							pairdecision4.setText(parDec4);

						
							if(!pairdecision1.getText().equals("1-null") && !pairdecision2.getText().equals("2-null") && !pairdecision3.getText().equals("3-null") && !pairdecision4.getText().equals("4-null") ) {
								
								decider.setEnabled(true);
							}
							else {
								
								JOptionPane.showMessageDialog(this,
							             "les décisions des pairs ne sont pas complètes", 
							             "Avertissement",
							             JOptionPane.OK_OPTION);
							}
							
						}
						
						
						String sql3 = "SELECT  COUNT(*) AS NB FROM evaluer, articles "
								+ " WHERE articles.idArticle = "+idArti+" AND "
								+ "articles.idArticle = evaluer.idArticle  ";
						
						myConnection.setRs(myConnection.getSt().executeQuery(sql3));
						myConnection.getRs().next();

						int NB3 = myConnection.getRs().getInt("NB");

						if(NB3<3 && taille <= 4000) {
							
							JOptionPane.showMessageDialog(this,
						             "Veuillez vérifier que vous avez affecter\nautant de pairs qu'il faut", 
						             "Avertissement",
						             JOptionPane.OK_OPTION);
							
						}else if(NB3<4 && taille > 4000) {
							
							JOptionPane.showMessageDialog(this,
						             "Veuillez vérifier que vous avez affecter\nautant de pairs qu'il faut", 
						             "Avertissement",
						             JOptionPane.OK_OPTION);
							

						}
						
						String sql1 = "SELECT COUNT(*) AS NB FROM editer, articles "
								+ " WHERE articles.idArticle = "+idArti+" AND "
								+ "articles.idArticle = editer.idArticle AND "
								+ "modifie = 0";
						
						myConnection.setRs(myConnection.getSt().executeQuery(sql1));
						myConnection.getRs().next();
						
						int NB1 = myConnection.getRs().getInt("NB");
										
										
						if(NB1 == 1) {
							
							JOptionPane.showMessageDialog(this,
						             "Vous avez déja décidé", 
						             "Avertissement",
						             JOptionPane.OK_OPTION);
						
							decider.setEnabled(false);
							
						}
						
						
						
						myConnection.fermerConnection();
						
					} catch (SQLException e1) {
	
						e1.printStackTrace();
						
							JOptionPane.showMessageDialog(this,
										e1.getMessage(), 
							             "Warning",
							             JOptionPane.OK_OPTION);	
	
								
										
							
					}
				
				
		}
		else if(e.getSource() == decider){
			
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
			
			
			int option = JOptionPane.showConfirmDialog(this,
		             "Voulez vous vraiment attribuer cet décision", 
		             "Confirmation",
		             JOptionPane.OK_CANCEL_OPTION);
			
			if(option == JOptionPane.OK_OPTION) {				

			try {
				
				
				myConnection = new MyConnection();
				myConnection.setSt(myConnection.getCn().createStatement()) ;
				
				if(!decision.equals("Décision refus")) {
					
					
					String sql = "UPDATE editer SET  "
							+ "decision = '"+decision+"' "
											+ " WHERE idArticle = "+idArti+"";
					
					myConnection.getSt().executeUpdate(sql) ;
					
					String sql2 = "UPDATE articles SET  "
							+ "modifie = 0 "
											+ " WHERE idArticle = "+idArti+"";
					
					myConnection.getSt().executeUpdate(sql2) ;
					
					
					JOptionPane.showMessageDialog(this,
							"Votre décision a été bien ajoutée", 
				             "Validation",
				             JOptionPane.OK_OPTION);
					
					pairdecision1.setText("");
					pairdecision2.setText("");
					pairdecision3.setText("");
					pairdecision4.setText("");
				
					decider.setEnabled(false);
				}
				else {
					
					String sql = "DELETE FROM articles "
									+ " WHERE idArticle = "+idArti+"";
					
					myConnection.getSt().executeUpdate(sql) ;
					
					JOptionPane.showMessageDialog(this,
							"L'article vient d'être supprimé", 
				             "Validation",
				             JOptionPane.OK_OPTION);
					
					
					
				}
				
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
		
		else {
			
			new EditeurAcceuilFrame(String.valueOf(username));
			this.dispose();
			
		}
		
	}
	
}
