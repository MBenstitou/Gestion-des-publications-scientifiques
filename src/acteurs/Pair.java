package acteurs;

public class Pair  extends Chercheur{

	public Pair(String nom, String prenom, String institution, String adresseInstitution) {
		
		super(nom, prenom, institution, adresseInstitution);

	}
	
	public int decider() {
		
		//évaluer l'article 
		return 0 ;
	}

	
}
