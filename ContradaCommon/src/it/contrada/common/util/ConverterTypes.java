package it.contrada.common.util;

public class ConverterTypes {
	
	private ConverterTypes()
	{}
	
	public static java.sql.Date valueOf(java.util.Date dateUtil)
	{
		if (dateUtil==null)
			return null;
	
		java.sql.Date sqlDate = new java.sql.Date(dateUtil.getTime());
		return sqlDate;

	}
	
	public static java.util.Date valueOf(java.sql.Date sqlDate)
	{

		if (sqlDate==null)
			return null;
		java.util.Date utildDate = new java.util.Date(sqlDate.getTime());
		return utildDate;

	}
}
