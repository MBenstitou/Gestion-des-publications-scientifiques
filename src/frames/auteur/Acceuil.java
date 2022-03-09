package frames.auteur;

import frames.editeur.*;
import frames.pair.PairFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.*;

import connection.MyConnection;  


public class Acceuil extends JFrame implements ActionListener {
	
	
	JLabel nomUtilisateur, motDePasse, avertissement, message;
	JTextField txtIdUtilisateur ;
	JPasswordField txtMotDePasse ;
	JButton connecter ;
	JLabel JL1;                                                                                                                
	JLabel JL2;                                                                                                                
	JLabel JL3;                                                                                                                
	JLabel J;    
  
	public Acceuil() {
	                                                                                                              
	                                                                                                                                  
		                                                                                                                               
		message = new JLabel("Veuillez saisir vos coordonnées");                                                                       
		message.setBounds(315, 92, 290, 50);                                                                                           
		message.setFont(new Font("Calibri", Font.BOLD, 20));                                                                           
		message.setForeground(new Color(255, 140, 0));                                                                                 
		getContentPane().add(message);                                                                                                 
		                                                                                                                               
		                                                                                                                               
		avertissement = new JLabel("Le mot de passe ou le nom d'utilisateur est incorrect");                                           
		avertissement.setBackground(new Color(240, 240, 240));                                                                         
		avertissement.setBounds(292, 297, 360, 50);                                                                                    
		avertissement.setFont(new Font("Calibri", Font.BOLD, 15));                                                                     
		avertissement.setForeground(new Color(0, 0, 0));                                                                               
		avertissement.setVisible(false);                                                                                               
		getContentPane().add(avertissement);                                                                                           
		                                                                                                                               
		                                                                                                                               
		nomUtilisateur = new JLabel("Username");                                                                                       
		nomUtilisateur.setBounds(303, 183, 100, 30);                                                                                   
		nomUtilisateur.setFont(new Font("Calibri", Font.BOLD, 20));                                                                    
		getContentPane().add(nomUtilisateur);                                                                                          
		                                                                                                                               
	                                                                                                                                   
		txtIdUtilisateur = new JTextField();                                                                                           
		txtIdUtilisateur.setBounds(413, 168, 257, 45);                                                                                 
		getContentPane().add(txtIdUtilisateur);                                                                                        
		                                                                                                                               
	                                                                                                                                   
	    motDePasse = new JLabel("Password");                                                                                           
		motDePasse.setBounds(303, 256, 100, 30);                                                                                       
		motDePasse.setFont(new Font("Calibri", Font.BOLD, 20));                                                                        
		getContentPane().add(motDePasse);                                                                                              
	                                                                                        
	    txtMotDePasse = new JPasswordField();                                                                                          
		txtMotDePasse.setBounds(413, 241, 257, 45);                                                                                    
		getContentPane().add(txtMotDePasse);                                                                                           
	                                                                                                                                
		connecter = new JButton("Connecter");                                                                                          
		connecter.setBackground(new Color(255, 140, 0));                                                                               
		connecter.setBounds(456, 350, 157, 30); 
		connecter.addActionListener(this);
	    connecter.setFont(new Font("Calibri", Font.BOLD, 20));                                                                         
		getContentPane().add(connecter);                                                                                               
	                                                                                                                                   
		                                                                                                                          
	    JL1 = new JLabel("");                                                                                                          
		JL1.setIcon(new ImageIcon("icons/profile.png"));                                                           
		JL1.setBounds(710, 168, 57, 45);                                                                                               
		getContentPane().add(JL1);                                                                                                     
		                                                                                                                           
	                                                                                                                                   
		JL2 = new JLabel("");                                                                                                          
		JL2.setIcon(new ImageIcon("icons/lock.png"));                                                              
		JL2.setBounds(710, 241, 57, 45);                                                                                               
		getContentPane().add(JL2);                                                                                                     
		                                                                                                     
		                                                                                                                               
		JL3= new JLabel("");                                                                                                           
		JL3.setIcon(new ImageIcon("icons/sticker-livre-jaune-bleu-rouge.png"));                                  
		JL3.setBounds(0, 0, 280, 489);                                                                                                 
		getContentPane().add(JL3);                                                                                                     
		                                                                                                                               
		 
		J = new JLabel("");
		J.setIcon(new ImageIcon("icons/bleuFonce.jpg"));                                
		J.setBounds(0, 0, 800, 489);                                                                                                   
		getContentPane().add(J); 

			
            
		this.setTitle("Page d'acceuil");
		this.setSize(807, 528);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout()); 
		this.setResizable(false);
		this.setVisible(true);
		
	}
	
//*******************************************************************************
	
	
	public  String[] getMotPasse(String username) {
		
		try {
			
			MyConnection myConnection = new MyConnection();
			
			myConnection.setSt(myConnection.getCn().createStatement()) ;
			String sql = "SELECT Password, statut, acceuil.idChercheur FROM chercheurs, acceuil WHERE "
					+ "acceuil.idChercheur=chercheurs.idChercheur AND Username = '"+username+"'";
			myConnection.setRs(myConnection.getSt().executeQuery(sql)); 
			myConnection.getRs().next();
			
			String DBUsername = myConnection.getRs().getString("Password") ;
			String statut = myConnection.getRs().getString("statut");
			int idChercheur = myConnection.getRs().getInt("idChercheur");
			
			String[] signUp = new String[3];
			signUp[0] = DBUsername;
			signUp[1] = statut;
			signUp[2] = idChercheur+"";
						
			return signUp ;
			
		} catch (SQLException e) {
			
		 	txtIdUtilisateur.setText("");
			txtMotDePasse.setText("");
			avertissement.setVisible(true);
			
			return null;
		}
		
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()== connecter) {
			
			
			  String username = txtIdUtilisateur.getText(); 
			  String password = String.valueOf(txtMotDePasse.getPassword());

			  String[] signUp = getMotPasse(username);//renvoie les coordonnées stockées dans la DB
			 
			 
			
			  if(signUp[0].equals(password) && signUp[1].equals("correspondant")) {
				  
				this.dispose();
				new CorrespondantFrame(String.valueOf(username));
				  
			  }else {
				  
				  	txtIdUtilisateur.setText("");
					txtMotDePasse.setText("");
					avertissement.setVisible(true);
			  }
			  
			  if(signUp[0].equals(password) && signUp[1].equals("editeur")) {
				  
				this.dispose();
				new EditeurAcceuilFrame(String.valueOf(username));
				  
			  }else {
				  
				  	txtIdUtilisateur.setText("");
					txtMotDePasse.setText("");
					avertissement.setVisible(true);
			  }
			 
			  if(signUp[0].equals(password) && signUp[1].equals("pair")) {
					
				  this.dispose();
				  new PairFrame(Integer.parseInt(signUp[2]) ,String.valueOf(username));
				  
			  }else {
				  
				  	txtIdUtilisateur.setText("");
					txtMotDePasse.setText("");
					avertissement.setVisible(true);
			  }
			 
		
		}
	}	
		
	

}

  
 