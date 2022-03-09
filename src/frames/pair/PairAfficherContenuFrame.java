package frames.pair;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import connection.MyConnection;

public class PairAfficherContenuFrame extends JFrame implements ActionListener{

	JTextArea contenu ;
	JScrollPane scroll;
	JLabel titreFenetre, J1;
	JButton retourner;
	MyConnection myconnection;
	int idPair, idArticle;
	String username;
	JPanel J;
	
	public PairAfficherContenuFrame(int idArticle,int idPair ,String username) {

		this.idArticle =idArticle;
		this.idPair =idPair;
		this.username =username;
		
		J1= new JLabel();
        J1.setIcon(new ImageIcon("icons/bleuFonce3.jpg"));
        J1.setBounds(0, 0, 750, 600);
        getContentPane().add(J1);
        
	
		
		titreFenetre = new JLabel("Lire l'article");
		titreFenetre.setBounds(150, 30, 270, 30);
        titreFenetre.setForeground(new Color(255, 140, 0));
		titreFenetre.setFont(new Font("Serif",Font.BOLD+Font.ITALIC,30));
		J1.add(titreFenetre);
		
		
		contenu = new JTextArea();
		contenu.setFont(new Font("Serif",Font.CENTER_BASELINE,20));
		contenu.setLineWrap(true);
		scroll = new JScrollPane(contenu, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setBounds(40, 80, 400, 400);
		J1.add(scroll);
		
		retourner = new JButton("retourner");
		retourner.setFocusable(false);
		retourner.addActionListener(this);
        retourner.setBackground(new Color(176, 224, 230));
		retourner.setFont(new Font("Calibri", Font.BOLD+Font.ITALIC, 24));
		retourner.setBounds(40, 500, 150, 50);
		J1.add(retourner);
		

		
		
		myconnection = new MyConnection();
		try {
			
			String sql ="SELECT contenu FROM articles "
					+"WHERE idArticle = "+idArticle+" ";
			
			
			//Création d'un Statement
			myconnection.setSt(myconnection.getCn().createStatement());
			//Execution de la requete SQL 
			myconnection.setRs(myconnection.getSt().executeQuery(sql));
									
			myconnection.getRs().next();
			
			String cont = myconnection.getRs().getString("contenu");
			
			contenu.setText(cont);
			
		}
		catch (Exception e) {

		
		
		}
		
	
	
		
		this.setTitle("Contenu");
		this.setSize(500, 600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.setVisible(true);
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		if(e.getSource() == retourner) {
			
			
			new PairFrame(idPair, username);
			this.dispose();
			
		}
		
	}

	
	
	
}
