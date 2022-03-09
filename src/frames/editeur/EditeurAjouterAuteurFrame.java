package frames.editeur;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import connection.MyConnection;

public class EditeurAjouterAuteurFrame  extends JFrame implements ActionListener, ItemListener{

	JLabel nom, prenom, nomInstitution, adressInstitution, idArticle, ordreAuteur,titreFenetre, usernameLabel, motPasse ;
	JTextField txtNom, txtPrenom, txtIdArticle, txtOrdreAuteur,txtUsername, txtMotPasse ;
	JTextArea txtNomInstitution, txtAdressInstitution ;
	JPanel paneau ;
	JLabel J;
	JLabel J1;
	JComboBox<String> statuts;
	JScrollPane scroll1, scroll2;
	JButton valider, acceuil;
	String username;
	
	public EditeurAjouterAuteurFrame(String username){
		
		this.username = username;
		
		J = new JLabel("");
		J.setIcon(new ImageIcon("icons/nanai.jpg"));
		J.setBounds(500,0,400,500);
		getContentPane().add(J, BorderLayout.EAST);

		
		titreFenetre = new JLabel("Ajout d'un chercheur");
		titreFenetre.setBounds(350, 30, 400, 50);
		titreFenetre.setFont(new Font("Calibri", Font.BOLD+Font.ITALIC, 38));
		titreFenetre.setForeground(new Color(255, 140, 0));
		J.add(titreFenetre);
		
		nom = new JLabel("Nom");
		nom.setBounds(70, 140, 180, 30);
		nom.setForeground(new Color(255, 140, 0));
		nom.setFont(new Font("Calibri", Font.BOLD+Font.ITALIC, 23));
		J.add(nom);
		
		txtNom = new JTextField();
		txtNom.setBounds(320, 140, 180, 30);
		txtNom.setFont(new Font("Serif",Font.CENTER_BASELINE,20));
		J.add(txtNom);
		
		prenom = new JLabel("Prenom");
		prenom.setForeground(new Color(255, 140, 0));
		prenom.setBounds(70, 200, 180, 30);
		prenom.setFont(new Font("Calibri", Font.BOLD, 23));
		J.add(prenom);
		
		txtPrenom = new JTextField();
		txtPrenom.setBounds(320, 200, 300, 50);
		txtPrenom.setFont(new Font("Serif",Font.CENTER_BASELINE,20));
		J.add(txtPrenom);
		
		nomInstitution = new JLabel("Nom de l'institution");
		nomInstitution.setBounds(70, 260, 300, 30);
		nomInstitution.setForeground(new Color(255, 140, 0));
		nomInstitution.setFont(new Font("Calibri", Font.BOLD+Font.ITALIC, 23));
		J.add(nomInstitution);
		
		txtNomInstitution = new JTextArea();
		txtNomInstitution.setFont(new Font("Serif",Font.CENTER_BASELINE,20));
		txtNomInstitution.setLineWrap(true);
		scroll1 = new JScrollPane(txtNomInstitution, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll1.setBounds(320, 260, 400, 50);
		J.add(scroll1);
		
		adressInstitution = new JLabel("Adresse de l'institution");
		adressInstitution.setBounds(70, 340, 300, 35);
		adressInstitution.setForeground(new Color(255, 140, 0));
		adressInstitution.setFont(new Font("Calibri", Font.BOLD+Font.ITALIC, 22));
		J.add(adressInstitution);
		
		txtAdressInstitution = new JTextArea();
		txtAdressInstitution.setFont(new Font("Serif",Font.CENTER_BASELINE,20));
		txtAdressInstitution.setLineWrap(true);
		scroll2 = new JScrollPane(txtAdressInstitution, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll2.setBounds(320, 340, 400, 50);
		J.add(scroll2);
		
		
		String statusTab[] = {"Correspondant","Auteur","Pair"};
		statuts = new JComboBox<String>(statusTab);
		statuts.setBounds(70, 420, 180, 30);
		statuts.addItemListener(this);
		J.add(statuts);
		
		usernameLabel = new JLabel("Username");
		usernameLabel.setBounds(70, 460, 300, 30);
		usernameLabel.setForeground(new Color(255, 140, 0));
		usernameLabel.setFont(new Font("Serif",Font.CENTER_BASELINE+Font.ITALIC,23));
		usernameLabel.setVisible(false);
		J.add(usernameLabel);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(320, 460, 300, 50);
		txtUsername.setFont(new Font("Serif",Font.CENTER_BASELINE,20));
		txtUsername.setVisible(false);
		J.add(txtUsername);
		
		
		motPasse = new JLabel("Mot de passe");
		motPasse.setBounds(70, 510, 200, 50);
		motPasse.setForeground(new Color(255, 140, 0));
		motPasse.setFont(new Font("Serif",Font.CENTER_BASELINE+Font.ITALIC,23));
		motPasse.setVisible(false);
		J.add(motPasse);
		
		txtMotPasse = new JTextField();
		txtMotPasse.setBounds(320, 530, 300, 50);
		txtMotPasse.setFont(new Font("Serif",Font.CENTER_BASELINE,20));
		txtMotPasse.setVisible(false);
		J.add(txtMotPasse);
		
		
		idArticle = new JLabel("Id Article");
		idArticle.setBounds(70, 510, 200, 50);
		idArticle.setForeground(new Color(255, 140, 0));
		idArticle.setFont(new Font("Serif",Font.CENTER_BASELINE+Font.ITALIC,23));
		idArticle.setVisible(false);
		J.add(idArticle);
		
		txtIdArticle = new JTextField();
		txtIdArticle.setBounds(320, 530, 300, 50);
		txtIdArticle.setFont(new Font("Serif",Font.CENTER_BASELINE,20));
		txtIdArticle.setVisible(false);
		J.add(txtIdArticle);
		
		ordreAuteur = new JLabel("Ordre de l'auteur");
		ordreAuteur.setBounds(70, 460, 300, 30);
		ordreAuteur.setForeground(new Color(255, 140, 0));
		ordreAuteur.setFont(new Font("Serif",Font.CENTER_BASELINE+Font.ITALIC,23));
		ordreAuteur.setVisible(false);
		J.add(ordreAuteur);
		
		txtOrdreAuteur = new JTextField();
		txtOrdreAuteur.setBounds(320, 460, 300, 50);
		txtOrdreAuteur.setFont(new Font("Serif",Font.CENTER_BASELINE,23));
		txtOrdreAuteur.setVisible(false);
		J.add(txtOrdreAuteur);
		
		valider = new JButton("valider");
		valider.setFocusable(false);
		valider.addActionListener(this);
		valider.setBackground(new Color(50, 205, 50));
		valider.setSize(150, 50);
		valider.setFont(new Font("Serif",Font.ITALIC,20));
		

		acceuil = new JButton("acceuil");
		acceuil.setFocusable(false);
		acceuil.addActionListener(this);
		acceuil.setSize(150, 50);
		acceuil.setBackground(new Color(240, 230, 140));
		acceuil.setFont(new Font("Serif",Font.ITALIC,20));

		paneau = new JPanel();
		paneau.setBounds(400, 600, 320, 50);
		paneau.add(valider);
		paneau.add(acceuil);
		paneau.setBackground(new Color(222, 184, 135));
		J.add(paneau);
		
		
		J1 = new JLabel(""); J1.setIcon(new ImageIcon("icons/bleuFonce2.jpg"));
		J1.setBounds(10,0,400,690);
		J.add(J1);
		 

		
		this.setTitle("Ajouter auteur");
		this.setSize(823, 703);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		//this.setLayout(null);
		this.setVisible(true);
	}

	public int idEditeur(String username) {
		
		try {
			
			MyConnection myConnection = new MyConnection();

			myConnection.setSt(myConnection.getCn().createStatement()) ;
			String sql = "SELECT chercheurs.idChercheur FROM acceuil INNER JOIN chercheurs ON "
					+ "acceuil.idChercheur=chercheurs.idChercheur  WHERE "
					+ "Username = '"+username+"'  ";
			
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
	
//*****************************************************************
	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() ==  valider) {
			
			
			String [] infoChercheur = new String[6];
			
			infoChercheur[0] =  txtNom.getText();
			infoChercheur[1] =  txtPrenom.getText();
			infoChercheur[2] =  txtNomInstitution.getText();
			infoChercheur[3] =  txtAdressInstitution.getText();
			
			if(statuts.getSelectedIndex() == 1) {
				infoChercheur[4] =  txtIdArticle.getText();
				infoChercheur[5] =  txtOrdreAuteur.getText();
			}
			
			if(statuts.getSelectedIndex() == 0 || statuts.getSelectedIndex() == 2) {
				infoChercheur[4] =  txtUsername.getText();
				infoChercheur[5] =  txtMotPasse.getText();
			}
			

			if(infoChercheur[0].isBlank() || infoChercheur[1].isBlank() || infoChercheur[2].isBlank()|| infoChercheur[3].isBlank() || infoChercheur[4].isBlank() || infoChercheur[5].isBlank()){
				
				 	JOptionPane.showMessageDialog(this,
			             "Veuillez remplir tous les champs !!", 
			             "Attention",
			             JOptionPane.OK_OPTION);
				
			}else {
				
				
				
				try {
					
					MyConnection myconnection = new MyConnection();

					
					//l'execution des requêtes
					if(statuts.getSelectedIndex() == 1) {
						
						String sql ="SELECT COUNT(*) AS NB FROM articles WHERE idArticle ="+infoChercheur[4]+"";
						
						//Creation d'un Statement
						myconnection.setSt(myconnection.getCn().createStatement());
						//creation d'une Resultset et l'execution d'une requete
						myconnection.setRs(myconnection.getSt().executeQuery(sql));
						myconnection.getRs().next();
						
						if(myconnection.getRs().getInt("NB") == 0) {
							
							JOptionPane.showMessageDialog(this,
						             "L'article n'existe pas dans la base de données", 
						             "ERROR",
						             JOptionPane.OK_OPTION);
							
						}
						else {
						myconnection.getSt().executeUpdate("INSERT INTO chercheurs(nom, prenom, nomInstitution, adresseInstitution, statut) "
								+ " VALUES('"+infoChercheur[0]+"','"+infoChercheur[1]+"', '"+infoChercheur[2]+"'"
										+ ", '"+infoChercheur[3]+"',  'auteur' ) ");
						
						
						myconnection.setRs(myconnection.getSt().executeQuery("SELECT idChercheur FROM chercheurs "
								+ "ORDER BY idchercheur DESC LIMIT 1"));
						
						myconnection.getRs().next();
						 
						int idAuteur = myconnection.getRs().getInt("idChercheur");
						
						myconnection.getSt().executeUpdate("INSERT INTO soumettre (idArticle , idChercheur, ordreChercheur) "
								+ " VALUES( "+infoChercheur[4]+" , "+idAuteur+" , "+Integer.parseInt(infoChercheur[5])+")");
					
					
						JOptionPane.showMessageDialog(this,
					             "L'auteur a été bien ajouté", 
					             "Validation",
					             JOptionPane.OK_OPTION);
						
						txtNom.setText("");
						txtPrenom.setText("");
						txtNomInstitution.setText("");
						txtAdressInstitution.setText("");
						txtIdArticle.setText("");
						txtOrdreAuteur.setText("");
						
						myconnection.fermerConnection();
						
						}
						
					}
					

					if(statuts.getSelectedIndex() == 0) {
						
						if(txtUsername.getText().length() != 10) {
							
							JOptionPane.showMessageDialog(this,
						             "Veuillez saisir un Username de dix caractère", 
						             "Validation",
						             JOptionPane.OK_OPTION);
							
						}
						else {
							
							
							String sql ="SELECT COUNT(*) AS NB FROM acceuil WHERE Username = '"+infoChercheur[4]+"'";
							
							//Creation d'un Statement
							myconnection.setSt(myconnection.getCn().createStatement());
							//creation d'une Resultset et l'execution d'une requete
							myconnection.setRs(myconnection.getSt().executeQuery(sql));
							myconnection.getRs().next();
							
							if(myconnection.getRs().getInt("NB") != 0) {
								
								JOptionPane.showMessageDialog(this,
							             "le Username est déja utilisé", 
							             "ERROR",
							             JOptionPane.OK_OPTION);
								
							}
							else {
							
							myconnection.getSt().executeUpdate("INSERT INTO chercheurs(nom, prenom, nomInstitution, adresseInstitution, statut, idEditeur) "
									+ " VALUES('"+infoChercheur[0]+"','"+infoChercheur[1]+"', '"+infoChercheur[2]+"'"
											+ ", '"+infoChercheur[3]+"',  'correspondant', "+idEditeur(username)+") ");
							
							
							myconnection.setRs(myconnection.getSt().executeQuery("SELECT idChercheur FROM chercheurs "
									+ "ORDER BY idchercheur DESC LIMIT 1"));
							
							myconnection.getRs().next();
							 
							int idAuteur = myconnection.getRs().getInt("idChercheur");
							
							myconnection.getSt().executeUpdate("INSERT INTO acceuil (Username , Password, idChercheur) "
									+ " VALUES( '"+infoChercheur[4]+"' , '"+infoChercheur[5]+"' , "+idAuteur+")");
						
						
							JOptionPane.showMessageDialog(this,
						             "Le correspondant a été bien ajouté", 
						             "Validation",
						             JOptionPane.OK_OPTION);
							
							txtNom.setText("");
							txtPrenom.setText("");
							txtNomInstitution.setText("");
							txtAdressInstitution.setText("");
							txtUsername.setText("");
							txtMotPasse.setText("");
							
							myconnection.fermerConnection();
							
							}
						}
					}
					if(statuts.getSelectedIndex() == 2) {
						
						
						if(txtUsername.getText().length() != 10) {
							
							JOptionPane.showMessageDialog(this,
						             "Veuillez saisir un Username de dix caractère", 
						             "Validation",
						             JOptionPane.OK_OPTION);
							
						}
						else {
							
							String sql ="SELECT COUNT(*) AS NB FROM acceuil WHERE Username = '"+infoChercheur[4]+"'";
							
							//Creation d'un Statement
							myconnection.setSt(myconnection.getCn().createStatement());
							//creation d'une Resultset et l'execution d'une requete
							myconnection.setRs(myconnection.getSt().executeQuery(sql));
							myconnection.getRs().next();
							
							if(myconnection.getRs().getInt("NB") != 0) {
								
								JOptionPane.showMessageDialog(this,
							             "le Username est déja utilisé", 
							             "ERROR",
							             JOptionPane.OK_OPTION);
								
							}
							else {
								myconnection.getSt().executeUpdate("INSERT INTO chercheurs(nom, prenom, nomInstitution, adresseInstitution, statut) "
										+ " VALUES('"+infoChercheur[0]+"','"+infoChercheur[1]+"', '"+infoChercheur[2]+"'"
												+ ", '"+infoChercheur[3]+"',  'pair' ) ");
								
								
								myconnection.setRs(myconnection.getSt().executeQuery("SELECT idChercheur FROM chercheurs "
										+ "ORDER BY idchercheur DESC LIMIT 1"));
								
								myconnection.getRs().next();
								 
								int idAuteur = myconnection.getRs().getInt("idChercheur");
								
								myconnection.getSt().executeUpdate("INSERT INTO acceuil (Username , Password, idChercheur) "
										+ " VALUES( '"+infoChercheur[4]+"' , '"+infoChercheur[5]+"' , "+idAuteur+")");
							
							
								JOptionPane.showMessageDialog(this,
							             "Le pair a été bien ajouté", 
							             "Validation",
							             JOptionPane.OK_OPTION);
								
								txtNom.setText("");
								txtPrenom.setText("");
								txtNomInstitution.setText("");
								txtAdressInstitution.setText("");
								txtUsername.setText("");
								txtMotPasse.setText("");
								
								myconnection.fermerConnection();
							}
						}
					}
				
				} catch (SQLException e1) {
					
					System.out.println(e1.getMessage());
					e1.printStackTrace();
				}
				
			}
			
		}
		else if(e.getSource() == acceuil) {
			
			new EditeurAcceuilFrame(String.valueOf(username)); 
			this.dispose();
			
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {

		int choix = statuts.getSelectedIndex();
		switch(choix) {
		
		case 0 : usernameLabel.setVisible(true); txtUsername.setVisible(true); 
					motPasse.setVisible(true); txtMotPasse.setVisible(true);
					idArticle.setVisible(false); txtIdArticle.setVisible(false);
					ordreAuteur.setVisible(false); txtOrdreAuteur.setVisible(false);
					break;
		
		case 1 : idArticle.setVisible(true); txtIdArticle.setVisible(true);
					ordreAuteur.setVisible(true); txtOrdreAuteur.setVisible(true);
					usernameLabel.setVisible(false); txtUsername.setVisible(false); 
					motPasse.setVisible(false); txtMotPasse.setVisible(false); 
					usernameLabel.setVisible(false); txtUsername.setVisible(false); 
					motPasse.setVisible(false); txtMotPasse.setVisible(false) ;break;
					
		case 2 : usernameLabel.setVisible(true); txtUsername.setVisible(true); 
					motPasse.setVisible(true); txtMotPasse.setVisible(true); 
					idArticle.setVisible(false); txtIdArticle.setVisible(false);
					ordreAuteur.setVisible(false); txtOrdreAuteur.setVisible(false);
					 break;
					
		}
		
	}
	
}
