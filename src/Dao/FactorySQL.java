package Dao;

public class FactorySQL implements DAOFactory {
	
	@Override
	public I_ProduitDAO createProduitDAO(){
		return new ProduitSqlDAO();
	}

	@Override
	public I_CatalogueDAO createCatalogueDAO() {
		return new CatalogueSqlDAO();
	}

}
