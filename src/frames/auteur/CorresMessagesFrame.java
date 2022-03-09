package frames.auteur;

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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


import connection.MyConnection;

public class CorresMessagesFrame extends JFrame implements ActionListener{

	JLabel nvContenu, nvTaille, titreFenetre, motsCles ,message, avertissement1,J,L2;
	JTextField txtNvTaille;
	JTextArea txtNvContenu;
	JButton valider, ok, retourner;
	JRadioButton motscles1 ,motscles2 ,motscles3;
	ButtonGroup bg;
	JScrollPane scroll;
	JScrollPane scroll1, scroll2, scroll3;
	MyConnection myConnection;
	int idArticle, idCorres;
	String username;
	
	public CorresMessagesFrame(int idCorres,int idArticle, String username){
		
		this.idArticle = idArticle ;
		this.idCorres = idCorres ;
		this.username = username;
				
		J= new JLabel("");
	    J.setIcon(new ImageIcon("icons/bleuFonce3.jpg"));
	    J.setBounds(0, 0, 900, 500);
	    getContentPane().add(J, BorderLayout.NORTH);
		
		L2= new JLabel("");
		L2.setIcon(new ImageIcon("icons/bleu-pastel-500ml.jpg"));
		L2.setBounds(790, 0, 100, 640);
		add(L2);
		
		titreFenetre = new JLabel("Réctification");
		titreFenetre.setBounds(280, 30, 200, 50);
		titreFenetre.setFont(new Font("Calibri", Font.BOLD+Font.ITALIC, 34));
		titreFenetre.setForeground(new Color(255, 140, 0));
		J.add(titreFenetre);
		
		message = new JLabel();
		message.setBounds(70, 90, 440, 30);
		message.setFont(new Font("Serif",Font.BOLD+Font.ITALIC,25));
		message.setForeground(Color.MAGENTA);
		J.add(message);
		
		nvContenu = new JLabel("Nouveau contenu");
		nvContenu.setBounds(70, 140, 230, 30);
		nvContenu.setForeground(new Color(255, 140, 0));
		nvContenu.setFont(new Font("Serif",Font.CENTER_BASELINE+Font.ITALIC,25));
		J.add(nvContenu);
		
		txtNvContenu = new JTextArea();
		txtNvContenu.setBounds(70, 170, 400, 200);
		txtNvContenu.setFont(new Font("Serif",Font.CENTER_BASELINE,20));
		txtNvContenu.setLineWrap(true);
		scroll = new JScrollPane(txtNvContenu, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setBounds(70, 170, 400, 200);
		J.add(scroll);
		
		
		nvTaille = new JLabel("Nouvelle taille");
		nvTaille.setBounds(550, 140, 180, 30);
		nvTaille.setForeground(new Color(255, 140, 0));
		nvTaille.setFont(new Font("Serif",Font.CENTER_BASELINE+Font.ITALIC,25));
		J.add(nvTaille);
	
		
		txtNvTaille = new JTextField();
		txtNvTaille.setBounds(550, 170, 180, 30);
		txtNvTaille.setFont(new Font("Serif",Font.CENTER_BASELINE,20));
		J.add(txtNvTaille);
		
		avertissement1 = new JLabel("Veuillez saisir la nouvelle taille");
		avertissement1.setBounds(550, 200, 220, 30);
		avertissement1.setFont(new Font("Serif",Font.BOLD+Font.ITALIC,15));
		avertissement1.setForeground(Color.MAGENTA);
		avertissement1.setVisible(false);
		J.add(avertissement1);
		
		valider = new JButton("valider");
		valider.setFocusable(false);
		valider.addActionListener(this);
		valider.setBounds(70, 380, 150, 50);
		valider.setFont(new Font("Serif",Font.ITALIC,20));
		valider.setBackground(new Color(255, 140, 0));
		J.add(valider);
		
		ok = new JButton("ok");
		ok.setFocusable(false);
		ok.addActionListener(this);
		ok.setBounds(550, 380, 150, 50);
		ok.setFont(new Font("Serif",Font.ITALIC,20));
		ok.setBackground(new Color(255, 140, 0));
		ok.setEnabled(false);
		J.add(ok);
		
		retourner = new JButton("retourner");
		retourner.setFocusable(false);
		retourner.addActionListener(this);
		retourner.setBounds(230, 380, 150, 50);
		retourner.setFont(new Font("Serif",Font.ITALIC,20));
		retourner.setBackground(new Color(255, 140, 0));
		J.add(retourner);
		
		motsCles = new JLabel("listes des mots cles");
		motsCles.setBounds(550, 220, 230, 30);
		motsCles.setForeground(new Color(255, 140, 0));
		motsCles.setFont(new Font("Serif",Font.CENTER_BASELINE+Font.ITALIC,25));
		J.add(motsCles);
		

		if(estSoumis() == 0) {
		
			
			message.setText("L'article n'est pas encore soumi");
			
			txtNvContenu.setEnabled(false);
			valider.setEnabled(false);
			txtNvTaille.setEnabled(false);
		
		}
		else {
		
			String [] motsCles = getMotsCles(idArticle);
			
			
			if(motsCles != null) {
				
			
				if(motsCles[0] != null && motsCles[1] != null && motsCles[2] != null ) {
					
					
						motscles1 = new JRadioButton(motsCles[0],true);
						motscles1.setFont(new Font("Serif",Font.BOLD,15));
						scroll1 = new JScrollPane(motscles1, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
						scroll1.setBounds(550, 250, 300, 40);
						J.add(scroll1);
						
						motscles2 = new JRadioButton(motsCles[1]);
						motscles2.setFont(new Font("Serif",Font.BOLD,15));
						scroll2 = new JScrollPane(motscles2, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
						scroll2.setBounds(550, 290, 300, 40);
						J.add(scroll2);
						
						motscles3 = new JRadioButton(motsCles[2]); 
						motscles3.setFont(new Font("Serif",Font.BOLD,15));
						scroll3 = new JScrollPane(motscles3, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
						scroll3.setBounds(550, 330, 300, 40);
						J.add(scroll3);
				
					
						// Ajouter les boutons radio au groupe 
						bg = new ButtonGroup();
						bg.add(motscles1); 
						bg.add(motscles2); 
						bg.add(motscles3);
						
						ok.setEnabled(true);
			
				}
			
				 

				if(motsCles[3].equals("aucune")) {
					
						message.setText("L'article est en phase d'étude");
						txtNvContenu.setEnabled(false);
						valider.setEnabled(false);
						txtNvTaille.setEnabled(false);
						

		
				}
				else
					{
					
						if(motsCles[3].equals("Décision acceptation")) {
						
						
							message.setText("Votre article a été accépté");
							txtNvContenu.setEnabled(false);
							valider.setEnabled(false);
							txtNvTaille.setEnabled(false);
							ok.setEnabled(false);
							motscles1.setEnabled(false);
							motscles2.setEnabled(false);
							motscles3.setEnabled(false);
						
						}else if(motsCles[3].equals("Révision mineure")) {
						
							message.setText("La décision est révision mineure");
						
						}else if(motsCles[3].equals("Révision majeure")) {
						
							message.setText("La décision est révision majeure");
							
						}
						
						if(getModifie() == 0) {
							
							message.setText("Votre article est en phase de révision.");
							txtNvContenu.setEnabled(false);
							valider.setEnabled(false);
							txtNvTaille.setEnabled(false);
						
					}
						
			}
				
				
			}
		
	  }
		
		
		

		this.setTitle("Suivre l'article");
		this.setSize(900, 500);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(null);
		this.setVisible(true);
	}
	
	
//***************************************************************
	
	public String[] getMotsCles(int id) {
		
		
		
		try {
			
			myConnection = new MyConnection();

			myConnection.setSt(myConnection.getCn().createStatement()) ;
			String sql = "SELECT motsCles1, motsCles2, motsCles3, decision FROM editer, articles WHERE "
					+ "articles.idArticle=editer.idArticle AND articles.idArticle = '"+id+"'";
			myConnection.setRs(myConnection.getSt().executeQuery(sql)); 
			myConnection.getRs().next();
			
			String motsCles[] = new String[4];
			
			motsCles[0] = myConnection.getRs().getString("motsCles1");
			motsCles[1] = myConnection.getRs().getString("motsCles2");
			motsCles[2] = myConnection.getRs().getString("motsCles3");
			motsCles[3] = myConnection.getRs().getString("decision");
			
			myConnection.fermerConnection();
			
			return motsCles;
			
		} catch (SQLException e) {
						
			e.printStackTrace();
			System.out.println(e.getMessage());
			
			ok.setEnabled(false);
			txtNvContenu.setEnabled(false);
			valider.setEnabled(false);
			txtNvTaille.setEnabled(false);
			
			return null;

			
		}
		
		
		
	}
	
//*********************************************************
	
	public int estSoumis() {
		
		

		try {
			
			myConnection = new MyConnection();

			myConnection.setSt(myConnection.getCn().createStatement()) ;
			String sql = "SELECT COUNT(*) AS NB FROM articles WHERE "
					+ "idArticle = '"+idArticle+"' AND dateSoumission IS NOT NULL";
			
			myConnection.setRs(myConnection.getSt().executeQuery(sql)); 
			myConnection.getRs().next();
			
			if(myConnection.getRs().getInt("NB") == 1) {
				
				return 1;
	
			}
			else {
				
				return 0;
			}
			
		} catch (SQLException e) {
						
			e.printStackTrace();
			System.out.println(e.getMessage());
			
			ok.setEnabled(false);
			txtNvContenu.setEnabled(false);
			valider.setEnabled(false);
			txtNvTaille.setEnabled(false);
			
			return -1;

			
		}
		
		
	}
	
//**********************************************************
	
	public int getModifie() {
		


		try {
			
			myConnection = new MyConnection();

			myConnection.setSt(myConnection.getCn().createStatement()) ;
			String sql = "SELECT modifie FROM articles WHERE "
					+ "articles.idArticle = '"+idArticle+"'";
			
			myConnection.setRs(myConnection.getSt().executeQuery(sql)); 
			myConnection.getRs().next();
			
			int n = myConnection.getRs().getInt("modifie");
			
			myConnection.fermerConnection();
			return n;
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			return -1;
		}
		
		
	}

//***********************************************************************
	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == valider) {
				
					
					try {
							
							Integer.valueOf(txtNvTaille.getText());
							
							try {
								
							
								myConnection = new MyConnection();
								myConnection.setSt(myConnection.getCn().createStatement()) ;
								String sql = "UPDATE articles SET  "
										+ "contenu = '"+txtNvContenu.getText()+"' ,"
												+ "taille= "+Integer.valueOf(txtNvTaille.getText()) +",  "
													+ " modifie = 0 "
														+ " WHERE idArticle = "+idArticle+"";
								
								myConnection.getSt().executeUpdate(sql) ;
								
								String sql1 = "UPDATE evaluer SET  "
										+ "dateRevision = '"+java.time.LocalDate.now()+"' "
														+ " WHERE idArticle = "+idArticle+"";
								
								myConnection.getSt().executeUpdate(sql1) ;

								
								JOptionPane.showMessageDialog(this,
										"Vous avez bien modifé votre article", 
							             "Avertissement",
							             JOptionPane.OK_OPTION);
								
								myConnection.fermerConnection();
							
							} catch (SQLException e1) {
							
								e1.printStackTrace();
								System.out.println(e1.getMessage());
								
							}catch(NumberFormatException e2) {
								
								avertissement1.setVisible(true);
								
							}
						
				}
				catch(Exception ex){
					
					
					JOptionPane.showMessageDialog(this,
							"Veuillez saisir la taille correctement", 
				             "Avertissement",
				             JOptionPane.OK_OPTION);
					
				}
			
		}		
		else if(e.getSource() == ok) {
			
			String motsCles = "";
			
			if(motscles1.isSelected()) {
				
				motsCles = motscles1.getText();
				
			}
			if(motscles2.isSelected()) {
				
				motsCles = motscles2.getText();
				
			}
			if(motscles3.isSelected()) {
				
				motsCles = motscles3.getText();
				
			}
			
			
			try {
				
				
				myConnection = new MyConnection();
				myConnection.setSt(myConnection.getCn().createStatement()) ;
				String sql = "UPDATE articles SET  "
						+ "motsCles = '"+motsCles+"' "
										+ " WHERE idArticle = "+idArticle+"";
				
				myConnection.getSt().executeUpdate(sql) ;
				
				JOptionPane.showMessageDialog(this,
						"Votre choix a été ajouté", 
		             "Validation",
		             JOptionPane.OK_OPTION);
				
				myConnection.fermerConnection();
				
			} catch (SQLException e1) {

				e1.printStackTrace();
				System.out.println(e1.getMessage());
				
			}
				
			
		}
		else if(e.getSource() == retourner) {
		
			new CorresArticlesFrame(idCorres ,String.valueOf(username)); 
			this.dispose();
		}
		
		
		
	}
	
	
	
}
