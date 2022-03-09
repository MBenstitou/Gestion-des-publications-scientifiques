package frames.pair;

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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import acteurs.Editeur;
import acteurs.Pair;
import connection.MyConnection;
import frames.auteur.Acceuil;

public class PairFrame extends JFrame implements ActionListener{


	DefaultTableModel model;
	JLabel titreFenetre,J;
	Vector<Vector<String>> donnees;
	JTable table;
	Pair pair;
	MyConnection myconnection ;
	JButton afficherContenu, decider,seDeconnecter;
	JPanel paneau;
	int idPair ;
	String username ; 
	
	public PairFrame(int idPair, String username){
		
		this.idPair = idPair;
		this.username =username;
		
		
		J= new JLabel("");
	    J.setIcon(new ImageIcon("icons/bleuFonce.jpg"));
	    J.setBounds(0, 0,700, 500);
	    getContentPane().add(J, BorderLayout.NORTH);
		
		
		String infoCorres[] = getCoordonneesEdit(username);
		pair =  new Pair(infoCorres[0],infoCorres[1],infoCorres[2],infoCorres[3]);
		
		String txtFormuleAttaque = "Bonjour "+infoCorres[0]+" "+infoCorres[1];
		titreFenetre = new JLabel(txtFormuleAttaque);
		titreFenetre.setBounds(190, 60, 400, 35);
		titreFenetre.setFont(new Font("Calibri", Font.BOLD+Font.ITALIC, 30));
		titreFenetre.setForeground(new Color(255, 140, 0));
		J.add(titreFenetre);
		
		
		afficherContenu = new JButton("afficher Contenu");
		afficherContenu.setFocusable(false);
		afficherContenu.addActionListener(this);
		afficherContenu.setFocusable(false);
		afficherContenu.setFont(new Font("Serif",Font.ITALIC,20));
		
		decider = new JButton("decider");
		decider.setFocusable(false);
		decider.addActionListener(this);
		decider.setFocusable(false);
		decider.setFont(new Font("Serif",Font.ITALIC,20));
		
		seDeconnecter = new JButton("se déconnecter");
		seDeconnecter.setFocusable(false);
		seDeconnecter.setBounds(0, 0, 200, 30);
		seDeconnecter.addActionListener(this);
		seDeconnecter.setBackground(new Color(255, 140, 0)); 
		seDeconnecter.setFocusable(false);
		seDeconnecter.setFont(new Font("Serif",Font.ITALIC,20));
		J.add(seDeconnecter);
	
		paneau = new JPanel();
		paneau.setBounds(30, 400, 630, 50);
		paneau.add(decider);
		paneau.add(afficherContenu);
		paneau.setBackground(new Color(255, 140, 0));
		J.add(paneau);
		
		
		myconnection = new MyConnection();
		try {
			
			String sql ="SELECT articles.idArticle, titre, contenu, dateEvaluation, dateRevision ,"
					+ " decision FROM chercheurs INNER JOIN evaluer "
					+ "ON evaluer.idChercheur = chercheurs.idChercheur "
					+ "INNER JOIN articles ON evaluer.idArticle = articles.idArticle "
					+"WHERE chercheurs.idChercheur = "+idPair+"";
			
			
			//Création d'un Statement
			myconnection.setSt(myconnection.getCn().createStatement());
			//Execution de la requete SQL 
			myconnection.setRs(myconnection.getSt().executeQuery(sql));
									
			
			//myconnection.getRs().absolute (1 ) resultset n'est pas vide 
			
			
		  
			    myconnection.getRs().next();
				donnees = new Vector<Vector<String>>();
				
				Vector<String> colonnes = new Vector<String>();
				
				colonnes.add("id");
				colonnes.add("Titre");
				colonnes.add("contenu");
				colonnes.add("Date evaluation");
				colonnes.add("Date revision");
				colonnes.add("decision");
				  
				
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
			    J.add(pane);
			   			   
					do{
						
						Vector<String> info = new Vector<String>();
						
						info.add(myconnection.getRs().getString("idArticle"+""));
						info.add(myconnection.getRs().getString("titre"));
						info.add(myconnection.getRs().getString("contenu"));
						info.add(myconnection.getRs().getString("dateEvaluation"));
						info.add(myconnection.getRs().getString("dateRevision"));
						info.add(myconnection.getRs().getString("decision"));

						model.addRow(info);
						
					}while(myconnection.getRs().next());
				
			      myconnection.fermerConnection();
	      
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	
		
			
	      this.setTitle("Page de pair");
	      this.setSize(700, 500);
	      this.setLocationRelativeTo(null);
	      this.setLayout(null);
	      this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	      this.setResizable(false);
	      this.setVisible(true);
	      
	      
		}
//*************************************************************
public String[] getCoordonneesEdit(String username){
		
		try {
			
			myconnection = new MyConnection();

			myconnection.setSt(myconnection.getCn().createStatement()) ;
			
			String sql = "SELECT chercheurs.idChercheur, nom, prenom, nomInstitution, adresseInstitution FROM chercheurs, acceuil WHERE "
					+ "acceuil.idChercheur=chercheurs.idChercheur AND Username = '"+username+"'";
			myconnection.setRs(myconnection.getSt().executeQuery(sql)); 
			myconnection.getRs().next();
			
			String infoCorres[] = new String[4];
			
			idPair = myconnection.getRs().getInt("idChercheur");
			
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
	
//**************************************************************
	
	
	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource()== decider) {
			
			
			if(table.getSelectedRow() == -1) {
				
				JOptionPane.showMessageDialog(this,
						"Veuillez selectionner l'article", 
		             "Avertissement",
		             JOptionPane.OK_OPTION);
				
			}
			else {
				
				int ligne=0;
				ligne=table.getSelectedRow();
				new PairDeciderFrame(idPair,Integer.parseInt(donnees.elementAt(ligne).elementAt(0)),String.valueOf(username));
				this.dispose();
				

				
			}
			
			
		}
		if(e.getSource()== afficherContenu) {
					
				
			if(table.getSelectedRow() == -1) {
				
				JOptionPane.showMessageDialog(this,
						"Veuillez selectionner l'article", 
		             "Avertissement",
		             JOptionPane.OK_OPTION);
				
			}
			else {
				
				int ligne=0;
				ligne=table.getSelectedRow();
				new PairAfficherContenuFrame(Integer.parseInt(donnees.elementAt(ligne).elementAt(0)),idPair,String.valueOf(username));
				this.dispose();
				

				
			}
						
		}
		if(e.getSource()== seDeconnecter) {
			
			new Acceuil();
			this.dispose();
			
		}
		
	} 
			
}
	

