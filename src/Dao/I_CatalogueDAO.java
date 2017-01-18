package Dao;

import java.util.List;

import metier.I_Catalogue;

public interface I_CatalogueDAO {

	public abstract List<I_Catalogue> getToutLesCatalogues();

	public abstract boolean createCatalogue(String nom);

	public abstract boolean deleteCatalogue(String nom);

	//public abstract boolean enleverQuantiteProduit(String nom, int quantiteStock);
	
	//public abstract boolean ajouterQuantiteProduit(String nom, int quantiteStock);
	
	//public abstract boolean deleteAll();
	
	public abstract I_Catalogue getCatalogue(String nom);
	
	public abstract void fermerAccesAuDonnees();
	
}