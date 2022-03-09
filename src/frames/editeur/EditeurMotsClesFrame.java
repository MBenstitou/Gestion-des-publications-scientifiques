package frames.editeur;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import connection.MyConnection;

public class EditeurMotsClesFrame extends JFrame implements ActionListener {

	JLabel proposition1, proposition2, proposition3, titreFenetre,J;
	JTextArea txtProposition1, txtProposition2, txtProposition3;
	JScrollPane scroll1, scroll2, scroll3;
	JButton valider, acceuil ;
	MyConnection myConnection;
	String username;
	int idArticle;
	public EditeurMotsClesFrame(int idArticle, String username) {
		
		this.username = username ;
		this.idArticle = idArticle ;

		
		J= new JLabel("");
	    J.setIcon(new ImageIcon("icons/bleuFonce.jpg"));
	    J.setBounds(0, 0,550, 500);
	    getContentPane().add(J, BorderLayout.NORTH);
	    
		titreFenetre = new JLabel("Mots Cles");
		titreFenetre.setBounds(190, 20, 230, 30);
		titreFenetre.setFont(new Font("Serif",Font.BOLD+Font.ITALIC,30));
		titreFenetre.setForeground(new Color(255, 140, 0));
		J.add(titreFenetre);
		
		
		proposition1 = new JLabel("Première proposition");
		proposition1.setBounds(70, 60, 180, 30);
		proposition1.setFont(new Font("Serif",Font.CENTER_BASELINE+Font.ITALIC,20));
		J.add(proposition1);
		
		txtProposition1 = new JTextArea();
		txtProposition1.setFont(new Font("Serif",Font.CENTER_BASELINE,20));
		txtProposition1.setLineWrap(true);
		scroll1 = new JScrollPane(txtProposition1, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll1.setBounds(70, 90, 400, 85);
		J.add(scroll1);
		
		
		proposition2 = new JLabel("Deuxième proposition");
		proposition2.setBounds(70, 180, 200, 30);
		proposition2.setFont(new Font("Serif",Font.CENTER_BASELINE+Font.ITALIC,20));
		J.add(proposition2);
		
		
		txtProposition2 = new JTextArea();
		txtProposition2.setFont(new Font("Serif",Font.CENTER_BASELINE,20));
		txtProposition2.setLineWrap(true);
		scroll2 = new JScrollPane(txtProposition2, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll2.setBounds(70, 210, 400, 85);
		J.add(scroll2);
		
		proposition3 = new JLabel("Troisième proposition");
		proposition3.setBounds(70, 300, 200, 30);
		proposition3.setFont(new Font("Serif",Font.CENTER_BASELINE+Font.ITALIC,20));
		J.add(proposition3);
		
		txtProposition3 = new JTextArea();
		txtProposition3.setFont(new Font("Serif",Font.CENTER_BASELINE,20));
		txtProposition3.setLineWrap(true);
		scroll3 = new JScrollPane(txtProposition3, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll3.setBounds(70, 330, 400, 85);
		J.add(scroll3);
		
		valider = new JButton("valider");
		valider.setFocusable(false);
		valider.addActionListener(this);
		valider.setBounds(70, 430, 150, 50);
		valider.setFont(new Font("Serif",Font.ITALIC,20));
		J.add(valider);
		
		acceuil = new JButton("acceuil");
		acceuil.setFocusable(false);
		acceuil.addActionListener(this);
		acceuil.setBounds(250, 430, 150, 50);
		acceuil.setFont(new Font("Serif",Font.ITALIC,20));
		J.add(acceuil);
		
		this.setTitle("Proposer les mots Cles");
		this.setSize(550, 530);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(null);
		this.setVisible(true);
		
		
	}

//**********************************************************************************
	public int getIdEditeur(String username) {
		
		try {
			
			myConnection = new MyConnection();

			myConnection.setSt(myConnection.getCn().createStatement()) ;
			String sql = "SELECT acceuil.idChercheur FROM acceuil INNER JOIN chercheurs ON "
					+ "acceuil.idChercheur=chercheurs.idChercheur INNER JOIN editer ON "
					+ "editer.idChercheur=chercheurs.idChercheur INNER JOIN articles ON "
					+ "editer.idArticle=articles.idArticle WHERE "
					+ "Username = '"+username+"' AND "
					+ "articles.idArticle = "+idArticle+"" ;
			
			myConnection.setRs(myConnection.getSt().executeQuery(sql)); 
			
			if(myConnection.getRs().wasNull()) {
				
				return 0 ;
			}
			else {
				
				myConnection.getRs().next();
				int idEditeur = myConnection.getRs().getInt("idChercheur");
				
				return idEditeur;
			}
			
			
		} 
		catch (SQLException e) {
			
			return 0;
		}
		
	}
	
//**********************************************************************************
	
	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() ==  acceuil) {
			
			new EditeurAcceuilFrame(username);
			this.dispose();
			
		}
		else {
			
			
			try {
								
				myConnection = new MyConnection();
				
				
				if(txtProposition1.getText().isBlank()){
					
					int retour = JOptionPane.showConfirmDialog(this, "Voulez vous supprimer la première proposition ?", 
					      "ATTENTION", JOptionPane.YES_NO_OPTION);
					
					if(retour == JOptionPane.OK_OPTION) {
						
						String sql2 = "UPDATE editer SET  "
								+ "motsCles1 = '"+txtProposition1.getText()+"' "
												+ " WHERE idArticle = "+idArticle+" AND "
														+ "idChercheur="+getIdEditeur(username)+"";
						
						myConnection.getSt().executeUpdate(sql2) ;

					}
					
				}	
				else {
						
						String sql2 = "UPDATE editer SET  "
								+ "motsCles1 = '"+txtProposition1.getText()+"' "
												+ " WHERE idArticle = "+idArticle+" AND "
														+ "idChercheur="+getIdEditeur(username)+"";
						
						myConnection.getSt().executeUpdate(sql2) ;

						JOptionPane.showMessageDialog(this,
								"Votre première proposition a été bien ajoutée", 
					             "Validation",
					             JOptionPane.OK_OPTION);
						
						txtProposition1.setText("");

					}
					
				if(txtProposition2.getText().isBlank()){
				
					int retour = JOptionPane.showConfirmDialog(this, "Voulez vous supprimer la deuxième proposition ?", 
					      "ATTENTION", JOptionPane.YES_NO_OPTION);
					
					if(retour == JOptionPane.OK_OPTION) {
						
						String sql2 = "UPDATE editer SET  "
								+ "motsCles2 = '"+txtProposition2.getText()+"' "
												+ " WHERE idArticle = "+idArticle+" AND "
														+ "idChercheur="+getIdEditeur(username)+"";
						
						myConnection.getSt().executeUpdate(sql2) ;

					}
				}	
				else {
						
						String sql2 = "UPDATE editer SET  "
								+ "motsCles2 = '"+txtProposition2.getText()+"' "
												+ " WHERE idArticle = "+idArticle+" AND "
														+ "idChercheur="+getIdEditeur(username)+"";
						
						myConnection.getSt().executeUpdate(sql2) ;

						JOptionPane.showMessageDialog(this,
								"Votre deuxième proposition a été bien ajoutée", 
					             "Validation",
					             JOptionPane.OK_OPTION);
						
						txtProposition2.setText("");

						
					}
			
				if(txtProposition3.getText().isBlank()){
					
					int retour = JOptionPane.showConfirmDialog(this, "Voulez vous supprimer la troisème proposition ?", 
					      "ATTENTION", JOptionPane.YES_NO_OPTION);
					
					if(retour == JOptionPane.OK_OPTION) {
						
						String sql2 = "UPDATE editer SET  "
								+ "motsCles3 = '"+txtProposition3.getText()+"' "
												+ " WHERE idArticle = "+idArticle+" AND "
														+ "idChercheur="+getIdEditeur(username)+"";
						
						myConnection.getSt().executeUpdate(sql2) ;

					}
				}
				else {
						
						String sql2 = "UPDATE editer SET  "
								+ "motsCles3 = '"+txtProposition3.getText()+"' "
												+ " WHERE idArticle = "+idArticle+" AND "
														+ "idChercheur="+getIdEditeur(username)+"";
						
						myConnection.getSt().executeUpdate(sql2) ;

						JOptionPane.showMessageDialog(this,
								"Votre troisième proposition a été bien ajoutée", 
					             "Validation",
					             JOptionPane.OK_OPTION);
						
						txtProposition3.setText("");

					}
				
						
			} catch (SQLException e1) {


				e1.getMessage();
				
				
			}
			
		}
		
	}
	
}
