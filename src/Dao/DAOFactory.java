package Dao;

public interface DAOFactory {
	
	public I_ProduitDAO createProduitDAO();
	
	public I_CatalogueDAO createCatalogueDAO();
}
