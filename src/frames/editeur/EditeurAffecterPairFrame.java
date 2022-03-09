package frames.editeur;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import connection.MyConnection;



public class EditeurAffecterPairFrame extends JFrame implements ActionListener{

	
	int idArticle ;
	String username;
	MyConnection myconnection ;
	Vector<Vector<String>> donnees;
	DefaultTableModel model;
	JTable table;
	JLabel titreFenetre, message,J;
	JButton affecter, acceuil;
	JPanel paneau;
	
	

	public EditeurAffecterPairFrame(int idArticle, String username) {
		
		this.idArticle = idArticle;
		this.username = username;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		paneau = new JPanel();
		setBounds(100, 100, 709, 457);
		paneau.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		
		
		titreFenetre = new JLabel("Affectation des pairs");
		titreFenetre.setBounds(200, 21, 341, 40);
		titreFenetre.setFont(new Font("Calibri", Font.BOLD+Font.ITALIC, 32));
	    titreFenetre.setForeground(new Color(255, 140, 0));
	    getContentPane().add(titreFenetre);
		
		int infoArticle[] = getInfoArticle();
		
		if(infoArticle[1] <= 4000) {
			
			message = new JLabel("Il vous reste "+(3-infoArticle[0])+"choix");

		}
		if(infoArticle[1] > 4000) {
			
			message = new JLabel("Il vous reste "+(4-infoArticle[0])+"choix");

		}
		
		message.setBounds(140, 400, 300, 50);
		message.setFont(new Font("Serif",Font.BOLD+Font.ITALIC,20));
		message.setForeground(Color.RED);
		this.add(message);
	
		
	
		affecter = new JButton("affecter");
		affecter.setFocusable(false);
		affecter.addActionListener(this);
		affecter.setBounds(30, 400, 100, 50);
		affecter.setBackground(new Color(50, 205, 50));
		affecter.setFont(new Font("Serif",Font.ITALIC,20));
		this.add(affecter);
		
		
		
		acceuil = new JButton("acceuil");
		acceuil.setFocusable(false);
		acceuil.addActionListener(this);
		acceuil.setBounds(500, 400, 100, 50);
		acceuil.setBackground(new Color(255, 140, 0));
		acceuil.setFont(new Font("Serif",Font.ITALIC,20));
		this.add(acceuil);
		
		

		J = new JLabel("");
		J.setBounds(430,400,50,50);
		J.setIcon(new ImageIcon("icons/home.png"));
		getContentPane().add(J);
		
		
		myconnection = new MyConnection();
		try {
			

			String sql ="SELECT  idChercheur, nom, prenom, nomInstitution "
					+ "FROM chercheurs WHERE statut ='pair' AND "
					+ "nomInstitution NOT IN(SELECT nomInstitution FROM chercheurs "
					+"INNER JOIN  soumettre ON chercheurs.idChercheur = soumettre.idChercheur "
					+ "INNER JOIN articles ON articles.idArticle = soumettre.idArticle "
					+ "WHERE articles.idArticle = "+idArticle+") ";
			
			
			//Création d'un Statement
			myconnection.setSt(myconnection.getCn().createStatement());
			//Execution de la requete SQL 
			myconnection.setRs(myconnection.getSt().executeQuery(sql));
									
			
			//myconnection.getRs().absolute (1 ) resultset n'est pas vide 
			
			
		  
			    myconnection.getRs().next();
				donnees = new Vector<Vector<String>>();
				
				Vector<String> colonnes = new Vector<String>();
				
				colonnes.add("id");
				colonnes.add("Nom");
				colonnes.add("Prenom");
				colonnes.add("Institution");

				  
				
				model = new DefaultTableModel(donnees, colonnes);
			    table = new JTable(model);
			    table.setDefaultEditor(Object.class, null);//pour empecher la table d'etre modifée
			    table.setShowGrid(true);
			    table.setShowVerticalLines(true);
			    table.setRowHeight(50);
			    TableColumnModel columnModel = table.getColumnModel();//pour changer la taille de la colonne
			    columnModel.getColumn(0).setPreferredWidth(40);//pour changer la taille de la colonne
			    JScrollPane pane = new JScrollPane(table);
			    pane.setBounds(30, 90, 630, 300);
			    this.add(pane);
			   			   
					do{
						
						Vector<String> info = new Vector<String>();
						
						info.add(myconnection.getRs().getString("idChercheur"+""));
						info.add(myconnection.getRs().getString("nom"));
						info.add(myconnection.getRs().getString("prenom"));
						info.add(myconnection.getRs().getString("nomInstitution"));

						model.addRow(info);
						
					}while(myconnection.getRs().next());
				
			      myconnection.fermerConnection();
		    	    
	      
	      
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		this.setSize(700, 500);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setVisible(true);
		
		
	}
	
//******************************************************************
	
	public int[] getInfoArticle() {
		
		
		myconnection = new MyConnection();
		try {
			

			String sql ="SELECT  COUNT(*) AS NB, taille "
					+ "FROM evaluer, articles "
					+ "WHERE evaluer.idArticle = articles.idArticle AND "
					+ " articles.idArticle = "+idArticle+" ";
			
			
			//Création d'un Statement
			myconnection.setSt(myconnection.getCn().createStatement());
			//Execution de la requete SQL 
			myconnection.setRs(myconnection.getSt().executeQuery(sql));
										
			myconnection.getRs().next();
			
			int info[] = new int[2];
			
			info[0] = myconnection.getRs().getInt("NB");
			info[1] = myconnection.getRs().getInt("taille");

			return 	info;

			    
		}
		catch(SQLException e){
			
			JOptionPane.showMessageDialog(this,
					e.getMessage(), 
	             "ERROR",
	             JOptionPane.OK_OPTION);
			
			return null;
		}
		
		
	}
	
//*****************************************************************
	public int estDejaPair(int idChercheur) {
		
		myconnection = new MyConnection();
		try {
			

			String sql ="SELECT  COUNT(*) AS NB "
					+ "FROM evaluer WHERE "
					+ "idArticle = "+idArticle+"  AND idChercheur = "+idChercheur+" ";
			
			
			//Création d'un Statement
			myconnection.setSt(myconnection.getCn().createStatement());
			//Execution de la requete SQL 
			myconnection.setRs(myconnection.getSt().executeQuery(sql));
										
			myconnection.getRs().next();
		
			
			int NB = myconnection.getRs().getInt("NB");
		

			return 	NB;

			    
		}
		catch(SQLException e){
			
			JOptionPane.showMessageDialog(this,
					e.getMessage(), 
	             "ERROR",
	             JOptionPane.OK_OPTION);
			
			return -1;
		}
		
	}
	
	
//******************************************************************

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == affecter) {
			
			
			
			if(table.getSelectedRow() == -1) {
				
				JOptionPane.showMessageDialog(this,
						"Veuillez selectionner un pair", 
		             "Avertissement",
		             JOptionPane.OK_OPTION);
				
			}
			else {
			
				
				int infoArticle[] = getInfoArticle();
				
				if((infoArticle[0] == 3 && infoArticle[1] <= 4000) || (infoArticle[0] == 4 && infoArticle[1] > 4000) ) {
					
					JOptionPane.showMessageDialog(this,
							"les pairs ont été déja afféctés", 
			             "Avertissement",
			             JOptionPane.OK_OPTION);
					
				}
				else {
					
					int ligne=0;
					ligne=table.getSelectedRow();
					int idPair =Integer.parseInt(donnees.elementAt(ligne).elementAt(0));
					
					if(estDejaPair(idPair) == 1) {
						
						
						JOptionPane.showMessageDialog(this,
								"Ce pair a été déja affécté", 
				             "Avertissement",
				             JOptionPane.OK_OPTION);
						
						
					}
					else {
						
						myconnection = new MyConnection();
						//Creation d'un Statement
						try {
							
							myconnection.setSt(myconnection.getCn().createStatement());
							
							int option = JOptionPane.showConfirmDialog(this,
						             "Voulez vous vraiment affecter ce pair", 
						             "Confirmation",
						             JOptionPane.OK_CANCEL_OPTION);
							
							if(option == JOptionPane.OK_OPTION) {	
								
								if(infoArticle[1] <= 4000 && infoArticle[0]!=3 ) {
									
									message.setText("Il vous reste "+(2-infoArticle[0])+" choix");
									

								}
								if(infoArticle[1] > 4000 && infoArticle[0]!=4) {
									
									message.setText("Il vous reste "+(3-infoArticle[0])+" choix");

								}
								

								myconnection.getSt().executeUpdate("INSERT INTO evaluer(idChercheur,idArticle,dateEvaluation) "
										+ "VALUES("+idPair+","+idArticle+",'"+java.time.LocalDate.now()+"') ");
							
							
							}	
							
							} catch (SQLException e1) {
		
								e1.printStackTrace();
								JOptionPane.showMessageDialog(this,
										e1.getMessage(),
										"Validation",
						             JOptionPane.OK_OPTION);
							
							}
						
						
					}
					
					
				}
				
			}
			
		}
		
		if(e.getSource() == acceuil) {
			
			
			new EditeurAcceuilFrame(String.valueOf(username));
			this.dispose();
		}
		
		
		
	 }

	}
