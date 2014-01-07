package it.contrada.dao.sqlite;

import it.contrada.reversecooking.dto.CategoriaDTO;
import it.contrada.reversecooking.dto.IngredienteDTO;
import it.contrada.reversecooking.dto.PortataDTO;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.aspectj.org.eclipse.jdt.internal.compiler.batch.FileSystem;

public class ReverseCookingDAO {

	Connection connection = null;
	String path = null;
	String pathDB=null;

	public ReverseCookingDAO(String path) throws ClassNotFoundException {
		this.path = path;
		Class.forName("org.sqlite.JDBC");
		pathDB="jdbc:sqlite:/" + path;
	}
	
	public List<CategoriaDTO> getCategorie() throws SQLException {
		ResultSet rs = null;
		Statement statement = null;
		try {

			connection = DriverManager.getConnection(pathDB);
			statement = connection.createStatement();
			rs = statement
					.executeQuery("SELECT _id ,TX_DESCRIZIONE,tx_immagine FROM CATEGORIA");
			List<CategoriaDTO> list = new ArrayList<CategoriaDTO>();
			while (rs.next()) {
				// read the result set
				CategoriaDTO cat = new CategoriaDTO();
				cat.setIdCategoria(rs.getInt("_ID"));
				cat.setDescrizione(rs.getString("tx_descrizione"));

				list.add(cat);
			}

			return list;
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		}

	}


	public List<PortataDTO> getPortate() throws SQLException {
		ResultSet rs = null;
		Statement statement = null;
		try {

			connection = DriverManager.getConnection(pathDB);
			statement = connection.createStatement();
			rs = statement
					.executeQuery("SELECT _ID, tx_descrizione from portata");
			List<PortataDTO> list = new ArrayList<PortataDTO>();
			while (rs.next()) {
				// read the result set
				PortataDTO portata = new PortataDTO();
				portata.setIdPortata(rs.getInt("_ID"));
				portata.setTxPortata(rs.getString("tx_descrizione"));

				list.add(portata);
			}

			return list;
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		}

	}

	public List<IngredienteDTO> getIngredienti() throws SQLException {

		ResultSet rs = null;
		Statement statement = null;
		try {

			connection = DriverManager.getConnection(pathDB);
			statement = connection.createStatement();
			rs = statement
					.executeQuery("SELECT i._ID idIngrediente, TX_NOME nomeIngrediente,ci.TX_IMMAGINE FROM Ingrediente i,Categoria_Ingredienti ci where ci._ID=i.ID_CATEGORIA_INGREDIENTE");
			List<IngredienteDTO> list = new ArrayList<IngredienteDTO>();
			while (rs.next()) {
				// read the result set
				IngredienteDTO ing = new IngredienteDTO();
				ing.setIdIngrediente(rs.getInt("idIngrediente"));
				ing.setNomeIngrediente(rs.getString("nomeIngrediente"));
				ing.setNomeImmagine(rs.getString("TX_IMMAGINE"));
				list.add(ing);
			}

			return list;
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
	}
}
