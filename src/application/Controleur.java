package application;


import java.util.ArrayList;
import java.util.List;

import Dao.DAOFactory;
import Dao.FactorySQL;
import Dao.I_CatalogueDAO;
import presentation.FenetrePrincipale;
import metier.*;

public class Controleur {

	private I_Catalogue cat;
	private ControleurNouveauSuppression cns;
	private ControleurAchatVente cav;
	private ControleurStock cs;
	
	private I_CatalogueDAO catalogueDAO; //partie 3
	
	public Controleur() {
		//cat = catalogueDAO.
		cns = new ControleurNouveauSuppression(cat);
		cav = new ControleurAchatVente(cat);
		cs = new ControleurStock(cat);
		
		DAOFactory factory = new FactorySQL();
		catalogueDAO = factory.createCatalogueDAO();
	}
	
	/*public static void main(String[] args) {
		new Controleur();
		new FenetrePrincipale(cns,cav);/*,cav,cs);
	}*/

	public I_Catalogue getCat() {
		return cat;
	}

	public ControleurNouveauSuppression getCns() {
		return cns;
	}

	public ControleurAchatVente getCav() {
		return cav;
	}
	public ControleurStock getCs(){
		return cs;
	}
	public void fermerAccesAuDonnees() {
		cat.fermerAccesAuDonnees();
	}


	public ArrayList<String> getTousLesCatalogues(){
		ArrayList<String> nomCatalogues = new ArrayList<String>();
		List<I_Catalogue> lesCatalogues = catalogueDAO.getToutLesCatalogues();
		for (int i = 0; i < lesCatalogues.size(); i++) {
			nomCatalogues.add(lesCatalogues.get(i).getNom());
		}
		return nomCatalogues;
	}
	
	public void AjouterCatalogue(String p_nom){
		catalogueDAO.createCatalogue(p_nom);
	}
	
	public void CreerCatalogue(String p_nom){
		catalogueDAO.createCatalogue(p_nom);
	}
	
	public void CreerFenetrePrincipale(String p_nom){
		cat = catalogueDAO.getCatalogue(p_nom);
		cns = new ControleurNouveauSuppression(cat);
		cav = new ControleurAchatVente(cat);
		cs = new ControleurStock(cat);
		new FenetrePrincipale(this);
	}
	
	public void SupprimerCatalogue(String nom){
		catalogueDAO.deleteCatalogue(nom);
	}
	
}
