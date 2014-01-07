package it.contrada.dao.ibatis;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Connection {

	private static SqlSessionFactory sqlMap = null;

	public static SqlSessionFactory getSqlSessionFactory() throws Exception {

		if (sqlMap == null) {
			String resource = "it/contrada/dao/ibatis/Configuration.xml";
			Reader reader = null;
			try {
				reader = Resources.getResourceAsReader(resource);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				throw new Exception("Configuration.xml non trovato", e);

			}
			try {
				sqlMap = new SqlSessionFactoryBuilder().build(reader);
			} catch (Exception ex) {
				throw new Exception("errore nella costruzione di SqlMap", ex);
			}
		}

		return sqlMap;
	}
}
