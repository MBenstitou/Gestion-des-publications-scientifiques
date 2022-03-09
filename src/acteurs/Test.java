package acteurs;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import connection.MyConnection;
import frames.auteur.Acceuil;
import frames.auteur.CorresAjouterArticleFrame;
import frames.auteur.CorresArticlesFrame;
import frames.auteur.CorresMessagesFrame;
import frames.auteur.CorresModifierArticleFrame;
import frames.auteur.CorrespondantFrame;
import frames.editeur.*;
import frames.pair.PairAfficherContenuFrame;
import frames.pair.PairDeciderFrame;
import frames.pair.PairFrame;

public class Test {

	public static void main(String[] args) throws UnsupportedLookAndFeelException {
		
		UIManager.setLookAndFeel(new NimbusLookAndFeel());
		new Acceuil() ;

	}
	
}

