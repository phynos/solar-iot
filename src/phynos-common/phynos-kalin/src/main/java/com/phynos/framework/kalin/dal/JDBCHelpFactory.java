package com.phynos.framework.kalin.dal;

public class JDBCHelpFactory {
	
	private static final JDBCHelp jdbcHelp;
	
	public static JDBCHelp getJDBCHelp(){
		return jdbcHelp;
	}
	
	static {
		switch(DBFactory.getDatabaseType()){
			case MySQL:
				jdbcHelp = new JDBCHelpMySQL();
				break;
			case Oracle:
				jdbcHelp = new JDBCHelpOracle();
				break;
			default:
				jdbcHelp = null;
		}
	}
}
