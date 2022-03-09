package acteurs;

public class Auteur extends Chercheur{
	

	public Auteur(String nom, String prenom, String institution, String adresseInstitution) {
		
		super(nom, prenom, institution, adresseInstitution);
		
	}
	
	public int soumettreArticle() {
		
		//instructions pour stocker l'article dans la basen de données 
		return 0 ;
	}
	
	public int modifierArticle(String titre) {
		
		//instructions pour stocker le titre dans la base de données pendant 
		//la phase préparation de soumission 
		return 0 ;
	}
	
	
	public int modifierContenu(String titre) {
		
		return 0 ;
	}
	
	public int modifierResumer(String titre) {
		
		return 0;
	}
	
	public int modifierTaille(String titre) {
		
		return 0 ;
	}
	
	public int ajouterAuteur(String nom,String prenom, String nomInstitution, String adresseInstitution, String statut) {
		
		return 0 ;
	}
	
	public int annulerSoumission(String titre) {
		
		return 0;
	}
}
