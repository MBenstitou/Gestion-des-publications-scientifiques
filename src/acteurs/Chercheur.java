package acteurs;

public class Chercheur {

	protected String nom ;
	protected String prenom ;
	protected String institution ;
	protected String adresseInstitution ;
	
	
	public Chercheur(String nom, String prenom, String institution, String adresseInstitution) {
		
		this.nom = nom;
		this.prenom = prenom;
		this.institution = institution;
		this.adresseInstitution = adresseInstitution;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getPrenom() {
		return prenom;
	}


	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}


	public String getInstitution() {
		return institution;
	}


	public void setInstitution(String institution) {
		this.institution = institution;
	}


	public String getAdresseInstitution() {
		return adresseInstitution;
	}


	public void setAdresseInstitution(String adresseInstitution) {
		this.adresseInstitution = adresseInstitution;
	}
	
	
}
