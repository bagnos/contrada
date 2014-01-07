package it.contrada.dao.typehandlers;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

public class UtilDateTypeHandler implements TypeHandler {

	private static Log log = LogFactory.getLog(GregorianTypeHandler.class);

	@Override
	public Object getResult(ResultSet rs, String columnName)
			throws SQLException {
		// TODO Auto-generated method stub

		Date date = rs.getDate(columnName);

		java.util.Date dateUtil = null;

		if (date != null) {

			dateUtil = new Date(date.getTime());
		}

		if (log.isTraceEnabled()) {
			log.trace("columnName=" + columnName);
			log.trace("date value=" + date);
			log.trace("dateUtil value=" + dateUtil);
		}

		return dateUtil;

	}

	@Override
	public Object getResult(CallableStatement cs, int columnIndex)
			throws SQLException {
		// TODO Auto-generated method stub
		Date date = cs.getDate(columnIndex);

		java.util.Date dateUtil = null;

		if (date != null) {

			dateUtil = new Date(date.getTime());
		}

		if (log.isTraceEnabled()) {
			log.trace("columnIndex=" + columnIndex);
			log.trace("date value=" + date);
		}

		return dateUtil;
	}

	@Override
	public void setParameter(PreparedStatement ps, int i, Object value,
			JdbcType jdbcType) throws SQLException {
		// TODO Auto-generated method stub
		Date date = null;
		java.util.Date dateUtil = null;

		if (value != null) {
			dateUtil = (java.util.Date) value;

			date = new Date(dateUtil.getTime());

		}

		ps.setDate(i, date);

		if (log.isTraceEnabled()) {
			log.trace("columnIndex=" + i);
			log.trace("date value=" + date);
			log.trace("dateUtil value=" + dateUtil);
		}

	}

}
