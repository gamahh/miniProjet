package Dao;
import java.util.List;
import metier.*;

public interface I_ProduitDAO {
	public abstract List<I_Produit> getTousLesProduits(int numCatalogue);

	public abstract boolean createProduit(String nom, double prixUnitaireHT, int quantiteStock, int numCatalogue);

	public abstract boolean deleteProduit(String nom, int numCatalogue);

	public abstract boolean enleverQuantiteProduit(String nom, int quantiteStock);
	
	public abstract boolean ajouterQuantiteProduit(String nom, int quantiteStock);
	
	public abstract boolean deleteAll();
	
	public abstract I_Produit getProduit(String nom);
	
	public abstract void fermerAccesAuDonnees();

}
