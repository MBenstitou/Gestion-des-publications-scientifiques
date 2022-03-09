package frames.auteur;

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

public class CorresModifierArticleFrame extends JFrame implements ActionListener{

	JLabel titreFenetre, titreArticle, tailleArticle, resume, contenu, auteurs, avertissement1,avertissement3,avertissement4,avertissement5;
	JTextField txtTaille;
	JTextArea txtTitre, txtResume, txtContenu, txtAuteurs ;
	JButton modifier, retourner;
	JScrollPane scroll1, scroll2, scroll3, scroll4 ;
	int IdArticle,idCorres;
	String username;
	Article article;
	JLabel J,L1,L2,L3,L4;

	
	
	public CorresModifierArticleFrame(int idCorres,int IdArticle,String username){
		
		this.IdArticle =IdArticle;
		this.username =username;
		this.idCorres =idCorres;

		
		
		L1= new JLabel("");
		L1.setIcon(new ImageIcon("icons/bleu-pastel-500ml.jpg"));
		L1.setBounds(0, 0, 900, 110);
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
		J.setIcon(new ImageIcon("icons/bleuFonce3.jpg"));
		J.setBounds(0,10,1000,700);
		add(J);
		
		
		avertissement1 = new JLabel("Toute information irronée entrenera la suppression de l'article !!");
		avertissement1.setBounds(100, 80, 800, 30);
		avertissement1.setFont(new Font("Serif",Font.BOLD+Font.ITALIC,20));
		avertissement1.setForeground(new Color(255, 140, 0));
		L1.add(avertissement1);
		
		
		
		avertissement3 = new JLabel("Votre article a été bien modifié");
		avertissement3.setBounds(450, 510, 500, 30);
		avertissement3.setFont(new Font("Serif",Font.BOLD+Font.ITALIC,15));
		avertissement3.setForeground(new Color(255, 140, 0));
		avertissement3.setVisible(false);
		J.add(avertissement3);
		
		avertissement4 = new JLabel("Veuillez saisir la nouvelle taille");
		avertissement4.setBounds(70, 250, 300, 30);
		avertissement4.setFont(new Font("Serif",Font.BOLD+Font.ITALIC,15));
		avertissement4.setForeground(new Color(255, 140, 0));
		avertissement4.setVisible(false);
		J.add(avertissement4);
		
		avertissement5 = new JLabel("Veuillez saisir le nouveau contenu");
		avertissement5.setBounds(450, 290, 400, 300);
		avertissement5.setFont(new Font("Serif",Font.BOLD+Font.ITALIC,15));
		avertissement5.setForeground(new Color(255, 140, 0));
		avertissement5.setVisible(false);
		J.add(avertissement5);
		
		titreFenetre = new JLabel("Modifier l'article");
		titreFenetre.setBounds(250, 30, 420, 35);
		titreFenetre.setFont(new Font("Serif",Font.BOLD+Font.ITALIC,30));
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
		
		modifier = new JButton("modifier");
		modifier.setFocusable(false);
		modifier.addActionListener(this);
		modifier.setBounds(583, 460, 150, 50);
		modifier.setBackground(new Color(255, 140, 0));
		modifier.setFont(new Font("Serif",Font.ITALIC,20));
		J.add(modifier);
		
		retourner = new JButton("retourner");
		retourner.setFocusable(false);
		retourner.addActionListener(this);
		retourner.setBounds(583,540, 150, 50);
		retourner.setBackground(new Color(255, 140, 0));
		retourner.setForeground(new Color(0, 0, 0));
		retourner.setFocusable(false);
		retourner.setFont(new Font("Serif",Font.ITALIC,20));
		J.add(retourner);
		
	
		
		estSoumis();
		
		this.setTitle("Modifier l'article");
		this.setSize(830, 660);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLayout(null);
		this.setVisible(true);
		
		
	}

//**********************************************************
	
	public void estSoumis() {
		
		try {
			
			MyConnection myConnection = new MyConnection();
			
			myConnection.setSt(myConnection.getCn().createStatement()) ;
			String sql = "SELECT dateSoumission FROM articles WHERE "
					+ "idArticle = "+IdArticle+" AND dateSoumission IS NULL ";
			myConnection.setRs(myConnection.getSt().executeQuery(sql)); 
			myConnection.getRs().next();
			
			if(!myConnection.getRs().absolute (1)) {
				
				modifier.setEnabled(false);
				txtTitre.setEnabled(false);
				txtResume.setEnabled(false);
				txtContenu.setEnabled(false);
				txtAuteurs.setEnabled(false);
				txtTaille.setEnabled(false);
 
				avertissement1.setText("Votre article a été bien soumis, suivez les nouveautés sur votre espace: Suivre l'article");
				
			}
			
			myConnection.fermerConnection();
			
			 
		}
		catch(SQLException e){
			
			System.out.println(e.getMessage());
		}
				
		
	}
	
//********************************************************************
	
	@Override
	public void actionPerformed(ActionEvent event) {

		if(event.getSource() == modifier){
			
			article = new Article();
			article.setTitre(txtTitre.getText());
			article.setResume(txtResume.getText());
			article.setContenu(txtContenu.getText());
			article.setAuteurs(txtAuteurs.getText());
			
			try{
				
				article.setTaille(Integer.valueOf(txtTaille.getText()));
			}
			catch(Exception e) {
				
				article.setTaille(0);

			}
			
				
				try {
					
					MyConnection myconnection = new MyConnection();
					//Creation d'un Statement
					myconnection.setSt(myconnection.getCn().createStatement());
					//Creation d'un Resultset et l'execution d'une requete
			
					if(!article.getContenu().isBlank() && article.getTaille()!= 0) {
						
						myconnection.getSt().executeUpdate("UPDATE articles SET contenu = '"+article.getContenu()+"', "
								+ "taille = "+article.getTaille()+" WHERE idArticle = "+IdArticle+" ");
						
						avertissement5.setVisible(false);
						avertissement3.setVisible(true);
						avertissement4.setVisible(false);
						txtTaille.setText("");
						txtContenu.setText("");

						
					}else if(!article.getContenu().isBlank() && article.getTaille()== 0) {
						 
						avertissement4.setVisible(true);
						avertissement3.setVisible(false);
						avertissement3.setVisible(false);

						
					}else if(article.getContenu().isBlank() && article.getTaille() != 0) {
						 
						avertissement5.setVisible(true);
						avertissement3.setVisible(false);

						
					}
					
					if(!article.getTitre().isBlank()) {
						 
						  myconnection.getSt().execute("UPDATE articles SET "
						  		+ "titre = '"+article.getTitre()+"' " + "WHERE idArticle = "+IdArticle+" ");
						 
							avertissement3.setVisible(true);
							txtTitre.setText("");
						  
					}
					
					
					 if(!article.getResume().isBlank()) {
						 
					  myconnection.getSt().execute("UPDATE articles SET "
					  		+ "resume = '"+article.getResume()+"' " + "WHERE idArticle = "+IdArticle+" ");
					 
						avertissement3.setVisible(true);
						txtResume.setText("");
					  
					 }
					
					if(!article.getAuteurs().isBlank()) {
						
						myconnection.getSt().executeUpdate("UPDATE articles SET auteurs = '"+article.getAuteurs()+"' "
								+ "WHERE idArticle = "+IdArticle+" ");
						
						avertissement3.setVisible(true);
						avertissement4.setVisible(false);
						txtAuteurs.setText("");

					}
					
					if(article.getAuteurs().isBlank() && article.getResume().isBlank() && article.getContenu().isBlank() && article.getTaille() == 0 && article.getTitre().isBlank()) {
						

						JOptionPane.showMessageDialog(this,
								"Veuillez saisir les nouvelles données", 
				             "ERROR",
				             JOptionPane.OK_OPTION);
						
					}
				
					
					myconnection.fermerConnection();
					
				}catch(SQLException e1) {
					
					e1.printStackTrace();
					System.out.println(e1.getMessage());
				}
				
			
		}
		
		if(event.getSource() == retourner) {
			
			new CorresArticlesFrame(idCorres,String.valueOf(username));
			this.dispose();
		}
		
	}
	
	
}
