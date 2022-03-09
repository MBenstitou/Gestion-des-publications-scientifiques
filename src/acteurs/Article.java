package acteurs;



public class Article {
 

	private String titre ;
	private String contenu ;
	private String motsCles ;
	private String resume ;
	private String auteurs ;
	private int taille ;
	
	
	public Article() {
		
	}


	public String getTitre() {
		return titre;
	}


	public void setTitre(String titre) {
		this.titre = titre;
	}


	public String getContenu() {
		return contenu;
	}


	public void setContenu(String contenu) {
		this.contenu = contenu;
		this.taille = contenu.length() ;
	}


	public String getMotsCles() {
		return motsCles;
	}


	public void setMotsCles(String motsCles) {
		this.motsCles = motsCles;
	}


	public String getResume() {
		return resume;
	}


	public void setResume(String resume) {
		this.resume = resume;
	}


	public int getTaille() {
		return taille;
	}
	
	public void setTaille(int taille) {
		this.taille = taille;
	}
	
	public String getAuteurs() {
		return auteurs;
	}
	
	public void setAuteurs(String auteurs) {
		this.auteurs = auteurs ;
	}
}

