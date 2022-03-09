package frames.auteur;

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

import connection.MyConnection;

public class CorresArticlesFrame extends JFrame implements ActionListener{

	MyConnection myconnection ;
	JPanel paneau1;
	JLabel mesArticles, avertissement,titreFenetre,J;
	JButton acceuil, soumettre, suivre, supprimer, modifier;
	String username;
	DefaultTableModel model;
	Vector<Vector<String>> donnees;
	JTable table;
	int idCorres;
	
	public CorresArticlesFrame(int idCorres ,String username){
		
		this.username = username;
		this.idCorres = idCorres;
	
		J= new JLabel("");
	    J.setIcon(new ImageIcon("icons/bleuFonce.jpg"));
	    J.setBounds(0, 0, 700, 500);
	    getContentPane().add(J, BorderLayout.NORTH);
		
	    titreFenetre = new JLabel("Mes articles");
		titreFenetre.setBounds(250, 30, 200, 50);
		titreFenetre.setFont(new Font("Calibri", Font.BOLD+Font.ITALIC, 34));
		titreFenetre.setForeground(new Color(255, 140, 0));
		J.add(titreFenetre);
		
		acceuil = new JButton("acceuil");
		acceuil.setFocusable(false);
		acceuil.addActionListener(this);
		acceuil.setFocusable(false);
		acceuil.setBackground(new Color(255, 140, 0));
		acceuil.setFont(new Font("Serif",Font.ITALIC,20));
		
		soumettre = new JButton("soumettre");
		soumettre.setFocusable(false);
		soumettre.addActionListener(this);
		soumettre.setFocusable(false);
		soumettre.setBackground(new Color(255, 140, 0));
		soumettre.setFont(new Font("Serif",Font.ITALIC,20));
		

		suivre = new JButton("suivre");
		suivre.setFocusable(false);
	    suivre.addActionListener(this);
		suivre.setFocusable(false);
		suivre.setBackground(new Color(255, 140, 0));
		suivre.setFont(new Font("Serif",Font.ITALIC,20));
		
		supprimer = new JButton("supprimer");
		supprimer.setFocusable(false);
		supprimer.addActionListener(this);
		supprimer.setFocusable(false);
		supprimer.setBackground(new Color(255, 140, 0));
		supprimer.setFont(new Font("Serif",Font.ITALIC,20));
		
		modifier = new JButton("modifier");
		modifier.setFocusable(false);
		modifier.addActionListener(this);
		modifier.setFocusable(false);
		modifier.setBackground(new Color(255, 140, 0));
		modifier.setFont(new Font("Serif",Font.ITALIC,20));
		
	
		
		paneau1 = new JPanel();
		paneau1.setBounds(30, 400, 630, 50);
		paneau1.setBackground(new Color(222, 184, 135));
		paneau1.add(acceuil);
		paneau1.add(suivre);
		paneau1.add(soumettre);
		paneau1.add(supprimer);
		paneau1.add(modifier);
		J.add(paneau1);
		
		myconnection = new MyConnection();
		try {
			
			String sql ="SELECT articles.idArticle, titre, contenu, resume, motsCles, taille, auteurs, dateSoumission "
					+ "FROM chercheurs INNER JOIN soumettre "
					+ "ON soumettre.idChercheur = chercheurs.idChercheur "
					+ "INNER JOIN articles ON soumettre.idArticle = articles.idArticle "
					+"WHERE chercheurs.idChercheur = "+idCorres+"";
			
			
			//Création d'un Statement
			myconnection.setSt(myconnection.getCn().createStatement());
			//Execution de la requete SQL 
			myconnection.setRs(myconnection.getSt().executeQuery(sql));
									
			
			//myconnection.getRs().absolute (1 ) resultset n'est pas vide 
			
			
		  
			    myconnection.getRs().next();
				donnees = new Vector<Vector<String>>();
				
				Vector<String> colonnes = new Vector<String>();
				
				colonnes.add("id");
				colonnes.add("titre");
				colonnes.add("contenu");
				colonnes.add("resume");
				colonnes.add("motsCles");
				colonnes.add("taille");
				colonnes.add("auteurs");
				colonnes.add("date Soumission");
				  
				
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
						info.add(myconnection.getRs().getString("resume"));
						info.add(myconnection.getRs().getString("motsCles"));
						info.add(myconnection.getRs().getInt("taille")+"");
						info.add(myconnection.getRs().getString("auteurs"));
						info.add(myconnection.getRs().getString("dateSoumission"));

						model.addRow(info);
						
					}while(myconnection.getRs().next());
				
			      myconnection.fermerConnection();
		    	    
		  
	      
	      
		} catch (SQLException e) {
			
			
		}
			
		
		 
		  this.setSize(700, 500);
	      this.setTitle("Mes articles");
	      this.setLayout(null);
	      this.setLocationRelativeTo(null);
	      this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	      this.setResizable(false);
	      this.setVisible(true);
			
	}

//**********************************************************
	
	public int estSoumis(int idArticle) {
		
		try {
			
			MyConnection myConnection = new MyConnection();
			
			myConnection.setSt(myConnection.getCn().createStatement()) ;
			String sql = "SELECT COUNT(*) AS NB FROM articles WHERE "
					+ "idArticle = "+idArticle+" AND dateSoumission IS NOT NULL ";
			
			myConnection.setRs(myConnection.getSt().executeQuery(sql)); 
			myConnection.getRs().next();
			
			if(myConnection.getRs().getInt("NB") == 1) {
				
				return 1;
				
			}
			

			return -1;

			
			 
		}
		catch(SQLException e){
			
			System.out.println(e.getMessage());
			
			return -1;
		}
				
		
	}
	
	
//**********************************************************	

	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == acceuil) {
			
			new CorrespondantFrame(username);
			this.dispose();
		}		
		
		if(e.getSource() == soumettre) {
			
			
			if(table.getSelectedRow() == -1) {
				
				JOptionPane.showMessageDialog(this,
						"Veuillez selectionner l'article", 
		             "Avertissement",
		             JOptionPane.OK_OPTION);
				
			}
			else {
				
				
				int ligne=0;
				ligne=table.getSelectedRow();
					
				if(donnees.elementAt(ligne).elementAt(7) != null){
					
					JOptionPane.showMessageDialog(this,
							"L'article est déja soumi", 
			             "Avertissement",
			             JOptionPane.OK_OPTION);
					
				}
				else {
					
				
						try {
							
							MyConnection myconnection = new MyConnection();
							//Creation d'un Statement
							myconnection.setSt(myconnection.getCn().createStatement());
										
							int option = JOptionPane.showConfirmDialog(this,
						             "La soumission sera finale !!\n Valider l'opération ou annuler", 
						             "Confirmation",
						             JOptionPane.OK_CANCEL_OPTION);
							
							if(option == JOptionPane.OK_OPTION) {							
	
								ligne=0;
								ligne=table.getSelectedRow();
		
								myconnection.getSt().executeUpdate("UPDATE articles SET dateSoumission = '"+java.time.LocalDate.now()+"' "
										+ "WHERE idArticle = "+donnees.elementAt(ligne).elementAt(0)+" ");
								
								soumettre.setEnabled(false);
								
								JOptionPane.showMessageDialog(this,
										"Votre article a été bien soumis.\nSuivez les nouveautés sur votre espace: Suivre l'article", 
						             "Validation",
						             JOptionPane.OK_OPTION);
								
								new CorresArticlesFrame(idCorres, String.valueOf(username));
								this.dispose();
																
							}
													
							myconnection.fermerConnection();
							
						}catch(SQLException e1) {
						
							e1.printStackTrace();
							System.out.println(e1.getMessage());
						}
					
				   	}
				}
				
		}
		
		if(e.getSource() == suivre){
			
			if(table.getSelectedRow() == -1) {
				
				JOptionPane.showMessageDialog(this,
						"Veuillez selectionner l'article", 
		             "Avertissement",
		             JOptionPane.OK_OPTION);
				
			}
			else {
				
				int ligne=0;
				ligne=table.getSelectedRow();
				new CorresMessagesFrame(idCorres,Integer.parseInt(donnees.elementAt(ligne).elementAt(0)), String.valueOf(username));
				this.dispose();

				
			}
			
		}
		
		if(e.getSource() == supprimer) {
			
				if(table.getSelectedRow() == -1) {
				
					JOptionPane.showMessageDialog(this,
							"Veuillez selectionner l'article", 
			             "Avertissement",
			             JOptionPane.OK_OPTION);
				
				}
				else {
					
					int ligne=0;
					ligne=table.getSelectedRow();
					
					if(estSoumis( Integer.parseInt(donnees.elementAt(ligne).elementAt(0))) == 1){
						
						
						JOptionPane.showMessageDialog(this,
								"l'article a été déja soumis\nVous ne pouvez pas le supprimer", 
				             "Avertissement",
				             JOptionPane.OK_OPTION);
					}
					else {
						
						

						int retour = JOptionPane.showConfirmDialog(this,
								"Voulez vous vraiment supprimer l'article ?", 
							      "ATTENTION", JOptionPane.YES_NO_OPTION);
							
						if(retour == JOptionPane.OK_OPTION) {
							
							
							MyConnection myconnection = new MyConnection();
							//Creation d'un Statement
							try {
								
								myconnection.setSt(myconnection.getCn().createStatement());
								
								myconnection.getSt().executeUpdate("DELETE FROM articles "
										+ "WHERE idArticle = "+Integer.parseInt(donnees.elementAt(ligne).elementAt(0))+"");
								

								new CorresArticlesFrame(idCorres, String.valueOf(username));
								this.dispose();
								
							} catch (SQLException e1) {
								
								e1.getMessage();
								
							}
							
						}
						
						
						
					}
	
					
				}		
				
			}
		
		if(e.getSource() == modifier) {
			
			if(table.getSelectedRow() == -1) {
				
				JOptionPane.showMessageDialog(this,
						"Veuillez selectionner l'article", 
		             "Avertissement",
		             JOptionPane.OK_OPTION);
			
			}
			else {
				
				int ligne=0;
				ligne=table.getSelectedRow();
			
				new CorresModifierArticleFrame(idCorres,Integer.parseInt(donnees.elementAt(ligne).elementAt(0)),String.valueOf(username));

				this.dispose();
			
			}
			
			
		}
			
		}
		
		
	}
	

