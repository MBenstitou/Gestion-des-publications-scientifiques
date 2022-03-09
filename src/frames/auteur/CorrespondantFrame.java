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

import acteurs.Auteur;
import connection.MyConnection;

public class CorrespondantFrame extends JFrame implements ActionListener {

	Auteur correpondant ;
	JLabel formuleAttaque,J,J1,J2;
	JButton mesArticles, ajouterArticle,seDeconnecter; 
	MyConnection myConnection;
	int idCorres ;
	String username ;
	
	public CorrespondantFrame(String username){
		
		this.username =username;
		String infoCorres[] = getCoordonneesCorres(username);
		correpondant =  new Auteur(infoCorres[0],infoCorres[1],infoCorres[2],infoCorres[3]);
		
		
		J2= new JLabel("");
		J2.setIcon(new ImageIcon("icons\\bleu-pastel-500ml.jpg"));
		J2.setBounds(0, 0, 800, 600);
		add(J2);
		
		J= new JLabel("");
		J.setIcon(new ImageIcon("icons/bleuFonce2.jpg"));
		J.setBounds(0, 0, 350, 600);
		
		
		J1= new JLabel("");
		J1.setIcon(new ImageIcon("icons\\OIP (2).jfif"));
		J1.setBounds(0, 0, 800, 600);
		J1.add(J);
		J2.add(J1);

		
		String txtFormuleAttaque = "Bonjour "+infoCorres[0]+" "+infoCorres[1];
		formuleAttaque = new JLabel(txtFormuleAttaque);
		formuleAttaque.setBounds(280, 25, 400, 35);
		formuleAttaque.setForeground(new Color(255, 140, 0));
		formuleAttaque.setFont(new Font("Serif",Font.BOLD+Font.ITALIC,30));
		J2.add(formuleAttaque);
	
		
		
		//ImageIcon icon1 = new ImageIcon("C:\\Users\\h\\Downloads\\plus.jpeg");
		mesArticles = new JButton("Mes articles");
		mesArticles.setVerticalTextPosition(JButton.BOTTOM);
		mesArticles.setHorizontalTextPosition(JButton.CENTER);
		mesArticles.setBounds(40, 130, 200, 150);
		mesArticles.setFocusable(false);
		mesArticles.addActionListener(this);
		mesArticles.setBackground(new Color(255, 140, 0));
		mesArticles.setFont(new Font("Serif",Font.ITALIC,20));
		J.add(mesArticles);

		ajouterArticle = new JButton("Ajouter article");
		ajouterArticle.setBounds(40, 330, 200, 150);
		ajouterArticle.setFocusable(false);
		ajouterArticle.addActionListener(this);
		ajouterArticle.setBackground(new Color(255, 140, 0));
		ajouterArticle.setFont(new Font("Serif",Font.ITALIC,20));
		J.add(ajouterArticle);
		
		seDeconnecter = new JButton("se déconnecter");
		seDeconnecter.setFocusable(false);
		seDeconnecter.addActionListener(this);
		seDeconnecter.setFont(new Font("Serif",Font.ITALIC,20));
		seDeconnecter.setBackground(new Color(255, 140, 0));
		seDeconnecter.setBounds(40, 0, 200, 30);
		J.add(seDeconnecter);
		
		articleExiste();
		
		this.setTitle("Page du correspondant");
		this.setSize(600, 600);
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
	}
	
//************************************************************************************************
	
	public String[] getCoordonneesCorres(String username){
				
		try {
			
			myConnection = new MyConnection();

			myConnection.setSt(myConnection.getCn().createStatement()) ;
			
			String sql = "SELECT chercheurs.idChercheur, nom, prenom, nomInstitution, adresseInstitution FROM chercheurs, acceuil WHERE "
					+ "acceuil.idChercheur=chercheurs.idChercheur AND Username = '"+username+"'";
			myConnection.setRs(myConnection.getSt().executeQuery(sql)); 
			myConnection.getRs().next();
			
			String infoCorres[] = new String[4];
			
			idCorres = myConnection.getRs().getInt("idChercheur");
			
			infoCorres[0] = myConnection.getRs().getString("nom");
			infoCorres[1] = myConnection.getRs().getString("prenom");
			infoCorres[2] = myConnection.getRs().getString("nomInstitution");
			infoCorres[3] = myConnection.getRs().getString("adresseInstitution");
			
			
			return infoCorres;
			
		} catch (SQLException e) {

			e.printStackTrace();
			return null;
		}
		
		
		
	}
	
//********************************************************************
public void articleExiste() {
		

		try {
			
			MyConnection myConnection = new MyConnection();
			myConnection.setSt(myConnection.getCn().createStatement()) ;
			
			String sql = "SELECT COUNT(*) FROM chercheurs, soumettre, articles WHERE "
					+ "soumettre.idChercheur=chercheurs.idChercheur AND "
					+ "chercheurs.idChercheur="+idCorres+" AND "
					+ "articles.idArticle=soumettre.idArticle ";
			
			myConnection.setRs(myConnection.getSt().executeQuery(sql)); 
			myConnection.getRs().next();
			

			if(myConnection.getRs().getInt(1) == 0) {
				
				mesArticles.setEnabled(false);
			}
			
		} catch (SQLException e) {

			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		
	}
//*********************************************************************
	
	public int getIdArticle() {
		


		try {
			
			myConnection = new MyConnection();

			myConnection.setSt(myConnection.getCn().createStatement()) ;
			
			String sql = "SELECT articles.idArticle FROM articles, soumettre WHERE "
					+ "articles.idArticle = soumettre.idArticle AND idChercheur = '"+idCorres+"' "
							+ "ORDER BY dateSoumission DESC "
							+ "LIMIT 1";
			
		
			
			myConnection.setRs(myConnection.getSt().executeQuery(sql)); 
			myConnection.getRs().next();
			
			int n = myConnection.getRs().getInt("idArticle");
			
			myConnection.fermerConnection();
			return n;
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			System.out.println(e.getMessage());
			return 0;
		}
		
		
	}
	
//************************************************************************************
	
	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == ajouterArticle) {
			
			new CorresAjouterArticleFrame(String.valueOf(username));

			this.dispose();
		}
		if(e.getSource() == mesArticles) {
			
			
			
			new CorresArticlesFrame(idCorres,String.valueOf(username));

			this.dispose();
		}
		
		if(e.getSource()== seDeconnecter) {
			
			new Acceuil();
			this.dispose();
			
		}
		
		
	}
}
