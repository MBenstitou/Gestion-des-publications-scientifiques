package frames.editeur;
import java.sql.SQLException;
import java.util.Vector;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import acteurs.Editeur;
import connection.MyConnection;
import frames.auteur.Acceuil;

public class EditeurAcceuilFrame extends JFrame implements ActionListener{

	Vector<Vector<String>> donnees;
	Editeur editeur ;
	MyConnection myconnection ;
	JLabel formuleAttaque, mesArticles, J, J1, J2;
	JPanel paneau,paneau_1;
	JButton affecterPair, decider, proposerMotsCles, ajoutAuteur, seDeconnecter ; 
	JTable table;
	int idEditeur ;
	String username;
	
//********************************************************************************
	
	public EditeurAcceuilFrame(String username){
		
		this.username = username ;
		

		setBounds(100, 100, 450, 300);
		paneau= new JPanel();
		paneau.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(paneau);
		paneau.setLayout(new BorderLayout(0, 0)); 
		
		
		String infoCorres[] = getCoordonneesEdit(username);
		editeur =  new Editeur(infoCorres[0],infoCorres[1],infoCorres[2],infoCorres[3]);
		
		String txtFormuleAttaque = "Bonjour "+infoCorres[0]+" "+infoCorres[1];
		formuleAttaque = new JLabel(txtFormuleAttaque);
		formuleAttaque.setBounds(220, 30, 400, 35);
		formuleAttaque.setFont(new Font("Calibri", Font.BOLD+Font.ITALIC, 30));
		formuleAttaque.setForeground(new Color(255, 140, 0));
		paneau.add(formuleAttaque);
		
		
		
		 seDeconnecter = new JButton("se déconnecter");
		   seDeconnecter.setFocusable(false);
		   seDeconnecter.addActionListener(this);
		   seDeconnecter.setFont(new Font("Calibri", Font.BOLD, 21));
		   seDeconnecter.setBounds(590, 0, 200, 40);
		   seDeconnecter.setBackground(new Color(255, 140, 0));
		   paneau.add(seDeconnecter);
		   
		   ajoutAuteur = new JButton("Ajouter chercheur");
		   ajoutAuteur.setFocusable(false);
		   ajoutAuteur.setSize(150, 50);
		   ajoutAuteur.addActionListener(this);
		   ajoutAuteur.setFont(new Font("Calibri", Font.BOLD, 21));
		   ajoutAuteur.setBackground(new Color(255, 140, 0));
		   
		   affecterPair = new JButton("Affecter pair");
		   affecterPair.setFocusable(false);
		   affecterPair.setSize(150, 50);
		   affecterPair.addActionListener(this);
		   affecterPair.setBackground(new Color(255, 140, 0));
		   affecterPair.setFont(new Font("Calibri", Font.BOLD, 22));
		   
		   
		   decider = new JButton("Decider");
		   decider.setFocusable(false);
		   decider.setSize(150, 50);
		   decider.addActionListener(this);
		   decider.setBackground(new Color(255, 140, 0));
		   decider.setFont(new Font("Calibri", Font.BOLD, 22));
		   
		   proposerMotsCles = new JButton("Mots Cles");
		   proposerMotsCles.setFocusable(false);
		   proposerMotsCles.setSize(150, 50);
		   proposerMotsCles.addActionListener(this);
		   proposerMotsCles.setBackground(new Color(255, 140, 0));
		   proposerMotsCles.setFont(new Font("Calibri", Font.BOLD, 21));
		   
		   paneau_1 = new JPanel();		
		   paneau_1.setBounds(40, 400, 700, 60);
		   paneau_1.setForeground(new Color(253, 245, 230));
		   paneau_1.setBackground(new Color(222, 184, 135));
		   paneau_1.add(affecterPair);
		   paneau_1.add(ajoutAuteur);
		   paneau_1.add(decider);
		   paneau_1.add(proposerMotsCles);
		   getContentPane().add(paneau_1, BorderLayout.SOUTH);
	      
		
		
		try {
			
			myconnection = new MyConnection();

		
				String sql ="SELECT articles.idArticle, titre, contenu, resume, motsCles, taille,dateSoumission ,auteurs ,decision "
					+ "FROM chercheurs INNER JOIN editer "
					+ "ON editer.idChercheur = chercheurs.idChercheur "
					+ "INNER JOIN articles ON editer.idArticle = articles.idArticle "
					+ "INNER JOIN acceuil ON acceuil.idChercheur = chercheurs.idChercheur "
					+"WHERE Username = '"+username+"' ";
			//Création d'un Statement
			myconnection.setSt(myconnection.getCn().createStatement());
			//Execution de la requete SQL 
			myconnection.setRs(myconnection.getSt().executeQuery(sql));
									
			
			//myconnection.getRs().absolute (1 ) resultset n'est pas vide 
			
			
				myconnection.getRs().next();

				donnees = new Vector<Vector<String>>();
				
				Vector<String> colonnes = new Vector<String>();
				colonnes.add("idArticle");
				colonnes.add("titre");
				colonnes.add("contenu");
				colonnes.add("resume");
				colonnes.add("motsCles");
				colonnes.add("taille");
				colonnes.add("dateSoumission");
				colonnes.add("auteurs");
				colonnes.add("decision");

				  
				
				DefaultTableModel model = new DefaultTableModel(donnees, colonnes);
			    table = new JTable(model);
			   // table.setDefaultEditor(Object.class, null);//pour empecher la table d'etre modifée
			    table.setShowGrid(true);
			    table.setShowVerticalLines(true);
			    table.setRowHeight(50);
			    TableColumnModel columnModel = table.getColumnModel();//pour changer la taille de la colonne
			    columnModel.getColumn(0).setPreferredWidth(40);//pour changer la taille de la colonne
			    JScrollPane pane = new JScrollPane(table);
			    pane.setBounds(40, 90, 700, 300);
			    paneau.add(pane);
			   			   
					do{
						
						Vector<String> info = new Vector<String>();
						
						info.add(myconnection.getRs().getInt("idArticle")+"");
						info.add(myconnection.getRs().getString("titre"));
						info.add(myconnection.getRs().getString("contenu"));
						info.add(myconnection.getRs().getString("resume"));
						info.add(myconnection.getRs().getString("motsCles"));
						info.add(myconnection.getRs().getInt("taille")+"");
						info.add(myconnection.getRs().getString("dateSoumission"));
						info.add(myconnection.getRs().getString("auteurs"));
						info.add(myconnection.getRs().getString("decision"));

						model.addRow(info);
						
					}while(myconnection.getRs().next());
				  
			      
		    
	      
		   
		  
		} 
		catch (SQLException e) {
			
			
		}
		

	     J1 = new JLabel("");
	     J1.setBounds(150,0,250,100);
	     J1.setIcon(new ImageIcon("icons/profile.png"));
	     paneau.add(J1);
	      
	      
	      J2 = new JLabel("New label");
	      J2.setIcon(new ImageIcon("icons/bleu-pastel-500ml.jpg"));
	      J2.setBounds(0,0,390,600);
	      paneau.add(J2);
	    
	      
	      
	      J=new JLabel("");
	      J.setIcon(new ImageIcon("icons/bleuFonce.jpg"));
	      J.setBounds(100, 0, 700, 500);
	      paneau.add(J);
		
		 this.setTitle("Page d'éditeur");
	      this.setSize(807, 528);
	      this.setLocationRelativeTo(null);
	      this.setLayout(new BorderLayout());
	      this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	      this.setResizable(false);
	      this.setVisible(true);
			
	}
	
//***************************************************************************
	public String[] getCoordonneesEdit(String username){
		
		try {
			
			myconnection = new MyConnection();

			myconnection.setSt(myconnection.getCn().createStatement()) ;
			
			String sql = "SELECT chercheurs.idChercheur, nom, prenom, nomInstitution, adresseInstitution FROM chercheurs, acceuil WHERE "
					+ "acceuil.idChercheur=chercheurs.idChercheur AND Username = '"+username+"'";
			myconnection.setRs(myconnection.getSt().executeQuery(sql)); 
			myconnection.getRs().next();
			
			String infoCorres[] = new String[4];
			
			idEditeur = myconnection.getRs().getInt("idChercheur");
			
			infoCorres[0] = myconnection.getRs().getString("nom");
			infoCorres[1] = myconnection.getRs().getString("prenom");
			infoCorres[2] = myconnection.getRs().getString("nomInstitution");
			infoCorres[3] = myconnection.getRs().getString("adresseInstitution");
			
			
			return infoCorres;
			
		} catch (SQLException e) {

			e.printStackTrace();
			return null;
		}
		
		
		
	}
	
//***********************************************************************************

	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() ==  affecterPair) {
			
			
			if(table.getSelectedRow() == -1) {
				
				JOptionPane.showMessageDialog(this,
						"Veuillez selectionner l'article", 
		             "Avertissement",
		             JOptionPane.OK_OPTION);
				
			}
			else {
				
				int ligne=0;
				ligne=table.getSelectedRow();
				new EditeurAffecterPairFrame(Integer.parseInt(donnees.elementAt(ligne).elementAt(0)),String.valueOf(username));
				this.dispose();
				

				
			}
			
			
		}
		else if(e.getSource() ==  decider) {
			
			
			if(table.getSelectedRow() == -1) {
				
				JOptionPane.showMessageDialog(this,
						"Veuillez selectionner l'article", 
		             "Avertissement",
		             JOptionPane.OK_OPTION);
				
			}
			else {
				
				int ligne=0;
				ligne=table.getSelectedRow();
				new EditeurDeciderFrame(Integer.parseInt(donnees.elementAt(ligne).elementAt(0)),String.valueOf(username));
				this.dispose();
				

				
			}
			
			
			
			
		}
		else if(e.getSource() ==  proposerMotsCles) {
			
			if(table.getSelectedRow() == -1) {
				
				JOptionPane.showMessageDialog(this,
						"Veuillez selectionner l'article", 
		             "Avertissement",
		             JOptionPane.OK_OPTION);
				
			}
			else {
				
				int ligne=0;
				ligne=table.getSelectedRow();
				new EditeurMotsClesFrame(Integer.parseInt(donnees.elementAt(ligne).elementAt(0)),String.valueOf(username));
				this.dispose();
				

				
			}	
			
		}
		else if(e.getSource() ==  ajoutAuteur) {
			
			new EditeurAjouterAuteurFrame(String.valueOf(username));
			this.dispose();
		}
		else if(e.getSource()== seDeconnecter) {
			
			new Acceuil();
			this.dispose();
			
		}
		
	}
	
	
	
}
