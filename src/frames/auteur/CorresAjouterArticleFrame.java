package frames.auteur;

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
import javax.swing.JTextField;


import acteurs.Article;
import connection.MyConnection;

public class CorresAjouterArticleFrame extends JFrame implements ActionListener{

	JLabel titreFenetre, titreArticle, tailleArticle, resume, contenu, auteurs;
	JTextField txtTaille;
	JTextArea txtTitre, txtResume, txtContenu, txtAuteurs ;
	JButton valider, acceuil;
	JScrollPane scroll1, scroll2, scroll3, scroll4 ; 
	private JLabel J;
	private JLabel L1;
	private JLabel L2;
	private JLabel L3;
	private JLabel L4;
	
	String username;
	Article article;
	
	public CorresAjouterArticleFrame(String username){
		
		this.username = username;

		
		L1= new JLabel("");
		L1.setIcon(new ImageIcon("icons/bleu-pastel-500ml.jpg"));
		L1.setBounds(0, 0, 890, 110);
		add(L1);

		L2= new JLabel("");
		L2.setIcon(new ImageIcon("icons/bleu-pastel-500ml.jpg"));
		L2.setBounds(790, 0, 100, 640);
		add(L2);
		
		L3= new JLabel("");
		L3.setFont(new Font("Calibri", Font.BOLD, 11));
		L3.setIcon(new ImageIcon("icons/submit.png"));
		L3.setBounds(472, 460, 60, 60);
		add(L3);
		
		
		L4= new JLabel("");
		L4.setIcon(new ImageIcon("icons/home.png"));
		L4.setBounds(472, 540, 68, 60);
		add(L4);
		
		J = new JLabel("");
		J.setIcon(new ImageIcon("icons/bleuFonce.jpg"));
		J.setBounds(0,10,800,700);
		add(J);
		
		
		titreFenetre = new JLabel("Ajout d'un article");
		titreFenetre.setBounds(300, 30, 350, 40);
		titreFenetre.setFont(new Font("Calibri", Font.BOLD+Font.ITALIC, 39));
		titreFenetre.setForeground(new Color(255, 140, 0));
		L1.add(titreFenetre);
		
		titreArticle = new JLabel("Titre");
		titreArticle.setBounds(40, 100, 180, 30);
		titreArticle.setForeground(new Color(255, 222, 173));
		titreArticle.setFont(new Font("Calibri", Font.BOLD+Font.ITALIC, 20));
		J.add(titreArticle);
		
		txtTitre = new JTextArea();
		txtTitre.setFont(new Font("Serif",Font.CENTER_BASELINE,20));
		txtTitre.setLineWrap(true);
		scroll3 = new JScrollPane(txtTitre, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll3.setBounds(40, 130, 300, 50);
		J.add(scroll3);
		
		tailleArticle = new JLabel("Taille");
		tailleArticle.setBounds(40, 200, 180, 30);
		tailleArticle.setForeground(new Color(255, 228, 181));
		tailleArticle.setFont(new Font("Calibri", Font.BOLD+Font.ITALIC, 20));
		J.add(tailleArticle);
		
		txtTaille = new JTextField();
		txtTaille.setBounds(40, 230, 300, 30);
		txtTaille.setFont(new Font("Serif",Font.CENTER_BASELINE,20));
		J.add(txtTaille);
		
		resume = new JLabel("Resume");
		resume.setBounds(40, 370, 180, 30);
		resume.setForeground(new Color(255, 222, 173));
		resume.setFont(new Font("Serif",Font.CENTER_BASELINE+Font.ITALIC,20));
		J.add(resume);
		
		txtResume = new JTextArea();
		txtResume.setFont(new Font("Serif",Font.CENTER_BASELINE,20));
		txtResume.setLineWrap(true);
		scroll1 = new JScrollPane(txtResume, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll1.setBounds(40, 400, 300, 200);
		J.add(scroll1);
		
		contenu = new JLabel("Contenu");
		contenu.setBounds(380, 100, 210, 30);
		contenu.setForeground(new Color(255, 222, 173));
		contenu.setFont(new Font("Serif",Font.CENTER_BASELINE+Font.ITALIC,20));
		J.add(contenu);
		
		txtContenu = new JTextArea();
		txtContenu.setFont(new Font("Serif",Font.CENTER_BASELINE,20));
		txtContenu.setLineWrap(true);
		scroll2 = new JScrollPane(txtContenu, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll2.setBounds(380, 130, 400, 300);
		J.add(scroll2);
		
		auteurs = new JLabel("Auteurs");
		auteurs.setBounds(40, 270, 210, 30);
		auteurs.setForeground(new Color(255, 228, 196));
		auteurs.setFont(new Font("Serif",Font.CENTER_BASELINE+Font.ITALIC,20));
		J.add(auteurs);
		
		txtAuteurs = new JTextArea();
		txtAuteurs.setFont(new Font("Serif",Font.CENTER_BASELINE,20));
		txtAuteurs.setLineWrap(true);
		scroll4 = new JScrollPane(txtAuteurs, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll4.setBounds(40, 300, 300, 60);
		J.add(scroll4);
		
		valider = new JButton("valider");
		valider.addActionListener(this);
		valider.setBounds(583, 460, 150, 50);
		valider.setFocusable(false);
		valider.setBackground(new Color(255, 140, 0));
		valider.setForeground(new Color(0, 0, 0));
		valider.setFont(new Font("Serif",Font.ITALIC,20));
		J.add(valider);
		
		acceuil = new JButton("acceuil");
		acceuil.addActionListener(this);
		acceuil.setBounds(583,540, 150, 50);
		acceuil.setFocusable(false);
		acceuil.setBackground(new Color(255, 140, 0));
		acceuil.setForeground(new Color(0, 0, 0));
		acceuil.setFont(new Font("Serif",Font.ITALIC,20));
		J.add(acceuil);
		
		
		this.setTitle("Ajouter un article");
		this.setSize(900, 650);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLayout(null);
		this.setVisible(true);
		
	}

//********************************************************************	
	public int getIdArticle() {
		
		try {
			
			MyConnection myConnection = new MyConnection();

			myConnection.setSt(myConnection.getCn().createStatement()) ;
			
			String sql = "SELECT MAX(idArticle) AS max FROM articles  ";
					
			myConnection.setRs(myConnection.getSt().executeQuery(sql)); 
			
			myConnection.getRs().next();
			
			int idArticle = myConnection.getRs().getInt("max");
				
			return idArticle;
			
			
			
		} 
		catch (SQLException e) {
			
			JOptionPane.showMessageDialog(this,
					e.getMessage(), 
	             "ERROR",
	             JOptionPane.OK_OPTION);

			
			return 0;
		}
		
	}
	
//**************************************************************************	
	
	public int getIdCorres() {
		
		try {
			
			MyConnection myConnection = new MyConnection();

			myConnection.setSt(myConnection.getCn().createStatement()) ;
			
			String sql = "SELECT chercheurs.idChercheur FROM chercheurs,acceuil WHERE "
					+ "chercheurs.idChercheur=acceuil.idChercheur AND "
					+ "Username='"+username+"' ";
					
			myConnection.setRs(myConnection.getSt().executeQuery(sql)); 
			
			myConnection.getRs().next();
			
			int idCorres = myConnection.getRs().getInt("idChercheur");
				
			return idCorres;
			
			
			
		} 
		catch (SQLException e) {
			
			JOptionPane.showMessageDialog(this,
					e.getMessage(), 
	             "ERROR",
	             JOptionPane.OK_OPTION);

			
			return 0;
		}
		
	}
	
//***************************************************************************	
	
	public int getIdEditeur() {
		
		try {
			
			MyConnection myConnection = new MyConnection();

			myConnection.setSt(myConnection.getCn().createStatement()) ;
			
			String sql = "SELECT idEditeur FROM chercheurs, acceuil WHERE "
					+ "chercheurs.idChercheur=acceuil.idChercheur AND "
					+ "Username='"+username+"' ";
					
			myConnection.setRs(myConnection.getSt().executeQuery(sql)); 
			
			myConnection.getRs().next();
			
			int idEditeur = myConnection.getRs().getInt("idEditeur");
				
			return idEditeur;
			
			
			
		} 
		catch (SQLException e) {
			
			JOptionPane.showMessageDialog(this,
					e.getMessage(), 
	             "ERROR",
	             JOptionPane.OK_OPTION);

			
			return 0;
		}
		
	}
		
//****************************************************************	
	
	public int titreExiste(String titre) {
		
		try {
			
			MyConnection myConnection = new MyConnection();

			myConnection.setSt(myConnection.getCn().createStatement()) ;
			
			String sql = "SELECT COUNT(titre) AS NB FROM articles WHERE "
					+ "titre='"+titre+"' ";
			
			myConnection.setRs(myConnection.getSt().executeQuery(sql)); 
			myConnection.getRs().next();
			
			if (myConnection.getRs().getInt("NB") == 0) {
				
				return 0;
				
			} else {
				
				return 1;

			}					
		} 
		catch (SQLException e) {
			
			e.printStackTrace();
			JOptionPane.showMessageDialog(this,
					e.getMessage(), 
	             "ERROR",
	             JOptionPane.OK_OPTION);

			
			return -1;
		}
		
	}
	
	
//****************************************************************	
	@Override
	public void actionPerformed(ActionEvent event) {

		if(event.getSource() == valider){
			
			article = new Article();
			article.setTitre(txtTitre.getText());
			article.setResume(txtResume.getText());
			article.setContenu(txtContenu.getText());
			article.setAuteurs(txtAuteurs.getText());
			
		
			if(article.getTitre().isBlank() && article.getContenu().isBlank() && article.getAuteurs().isBlank() && article.getResume().isBlank() && txtTitre.getText().isBlank()) {
			
				JOptionPane.showMessageDialog(this,
						"Veuillez saisir les données de votre article\nAu moins le titre et la taille", 
		             "Avertissement",
		             JOptionPane.OK_OPTION);
				
			}
			else if(article.getTitre().isBlank()) {
				
				JOptionPane.showMessageDialog(this,
						"Veuillez saisir le titre de votre article\nIl doit être unique", 
		             "Avertissement",
		             JOptionPane.OK_OPTION);

			}
			else if(titreExiste(article.getTitre())==1){
				
				JOptionPane.showMessageDialog(this,
						"Veuillez saisir un titre unique. Celui-ci déja existe", 
		             "Avertissement",
		             JOptionPane.OK_OPTION);

				
			}
			else{
				
				try {
					
					article.setTaille(Integer.valueOf(txtTaille.getText()));				
				
					try {
						
						MyConnection myconnection = new MyConnection();
						
						//myconnection.getCn().setAutoCommit(false);
						
						//Creation d'un Statement
						myconnection.setSt(myconnection.getCn().createStatement());
						//Creation d'un Resultset et l'execution d'un query
						String sql = "INSERT INTO articles (titre , contenu , resume , motsCles , taille , auteurs) "
								+ "Values('"+article.getTitre()+"','"+article.getContenu()+"',"
										+ "'"+article.getResume()+"','vide', "+article.getTaille()+" , "
												+ "'"+article.getAuteurs()+"')";
						
						myconnection.getSt().execute(sql);
						
						
						String sql1 = "INSERT INTO soumettre (idArticle , idChercheur ) "
								+ "Values("+getIdArticle()+","+getIdCorres()+") ";
						
						myconnection.getSt().execute(sql1);
						

						String sql2 = "INSERT INTO editer (idArticle , idChercheur ) "
								+ "Values("+getIdArticle()+","+getIdEditeur()+") ";
						
						myconnection.getSt().execute(sql2);
						
						
						valider.setEnabled(false);
						
						JOptionPane.showMessageDialog(this,
							"Votre article a été bien ajouté", 
				             "Avertissement",
				             JOptionPane.OK_OPTION);
						
						myconnection.fermerConnection();
						
					}catch(SQLException e) {
						
						
						e.printStackTrace();
						JOptionPane.showMessageDialog(this,
								e.getMessage(), 
				             "ERROR",
				             JOptionPane.OK_OPTION);
						
					}
					
				}
				catch (Exception e) {
					
					JOptionPane.showMessageDialog(this,
							"Veuillez saisir la taille de votre article\nS'il n'est pas encore précisé mettez 0", 
			             "Avertissement",
			             JOptionPane.OK_OPTION);
				}
				
			}
		
	}
	
		
		
		if(event.getSource() == acceuil) {
			
			new CorrespondantFrame(String.valueOf(username));
			this.dispose();
			
		}	
	
	}
	
}
