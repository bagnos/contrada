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

public class GregorianTypeHandler implements TypeHandler {

	private static Log log = LogFactory.getLog(GregorianTypeHandler.class);

	@Override
	public Object getResult(ResultSet rs, String columnName)
			throws SQLException {
		// TODO Auto-generated method stub

		Date date = rs.getDate(columnName);

		Calendar calendar = null;

		if (date != null) {

			calendar = Calendar.getInstance();

			calendar.setTime(date);
		}

		if (log.isTraceEnabled()) {
			log.trace("columnName=" + columnName);
			log.trace("date value=" + date);
			log.trace("calendar value=" + calendar);
		}

		return calendar;

	}

	@Override
	public Object getResult(CallableStatement cs, int columnIndex)
			throws SQLException {
		// TODO Auto-generated method stub
		Date date = cs.getDate(columnIndex);
		
		
		Calendar calendar = null;

		if (date != null) {

			calendar = Calendar.getInstance();

			calendar.setTime(date);
		}

		if (log.isTraceEnabled()) {
			log.trace("columnIndex=" + columnIndex);
			log.trace("date value=" + date);
		}

		return calendar;
	}

	@Override
	public void setParameter(PreparedStatement ps, int i, Object value,
			JdbcType jdbcType) throws SQLException {
		// TODO Auto-generated method stub
		Date date = null;
		GregorianCalendar calendar = null;

		
		if (value != null) {
			calendar = (GregorianCalendar) value;
			
			date = new Date(calendar.getTimeInMillis());
			
			
		}

		ps.setDate(i, date);
		
		
		if (log.isTraceEnabled()) {
			log.trace("columnIndex=" + i);
			log.trace("date value=" + date);
			log.trace("calendar value=" + calendar);
		}

	}

	

}
