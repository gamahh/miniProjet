package Dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import metier.Catalogue;
import metier.I_Catalogue;
import metier.I_Produit;
import metier.Produit;

public class CatalogueSqlDAO implements I_CatalogueDAO{
	private Connection connection;
	
	public CatalogueSqlDAO() {
		// TODO Auto-generated constructor stub
		connection = ConnexionBddOracle.getConnection();
	}
	
	@Override
	public List<I_Catalogue> getToutLesCatalogues() {
		List<I_Catalogue> lesCatalogues = new ArrayList<I_Catalogue>();

		String req = "Select * from Catalogues";
		Statement statement = null;

		try {

			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(req);
			while (resultSet.next()) {

				String nom = resultSet.getString("NOM");
				int num = resultSet.getInt("NUMCATALOGUE");
				
				Catalogue catalogue = new Catalogue(nom,num);
				lesCatalogues.add(catalogue);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeStatement(statement);
		}

		return lesCatalogues;
	}

	@Override
	public boolean createCatalogue(String nom) {
		boolean catalogueCreer = false;
		String req = "call nouveauCatalogue(?)";
		CallableStatement callableStatement = null;
		try {
			callableStatement = connection.prepareCall(req);
			callableStatement.setString(1, nom);
			if (!callableStatement.execute())
				catalogueCreer = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				callableStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return catalogueCreer;
	}

	@Override
	public boolean deleteCatalogue(String nom) {
		boolean catalogueSupprimee = false;
		Statement statement = null;
		PreparedStatement preparedStatement = null;
		try {
			String res = "delete from Catalogues where nom = ? ";
			preparedStatement = connection.prepareStatement(res);
			preparedStatement.setString(1, nom);
			//statement = connection.createStatement();
			if (preparedStatement.execute())
				catalogueSupprimee = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closePreparedStatement(preparedStatement);
		}
		return catalogueSupprimee;
	}


	private void closePreparedStatement(PreparedStatement preparedStatement) {
		try {
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public I_Catalogue getCatalogue(String nom) {
		// TODO Auto-generated method stub
		I_Catalogue catalogue = null;
		Statement statement = null;
		try {
			String req = "Select * from catalogues where nom= '"+nom+"'";
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(req);
			if (resultSet.next()) {

				String nom1 = resultSet.getString("NOM");
				int num = resultSet.getInt("numCatalogue");

				catalogue = new Catalogue(nom,num);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			closeStatement(statement);
		}
		return catalogue;
	}

	@Override
	public void fermerAccesAuDonnees() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void closeStatement(Statement statement) {
		try {
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
