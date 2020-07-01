package com.travanleo.user.boot.db;

import javax.validation.constraints.NotNull;

import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;

// NOT a @Component - we do not want this to picked up by component scan,
// only explicitly declared in DataSourceConfiguration (if that's active)
public class DataSourceProperties extends PoolProperties {

    private String port = "3306";

    private String hostname = "localhost";

    private String dbName = "travanleo";

    private String username = "root";

    private String password = "mysql";

    private String jdbcProtocol = "jdbc";

    private String jdbcSubprotocol = "mysql";

    public DataSourceProperties(String driverClassName, String protocol, String subProtocol, String port) {
        super();
        setDriverClassName(driverClassName);
        this.jdbcProtocol = protocol ;
        this.jdbcSubprotocol = subProtocol ;
        this.port = port ;
        setDefaults();
    }


    protected void setDefaults() {
        setInitialSize(3);
        if (getValidationQuery() == null) setValidationQuery("SELECT 1");
        setTestOnBorrow(true);
        setTestOnReturn(true);
        setTestWhileIdle(true);
        setTimeBetweenEvictionRunsMillis(30000);
        setTimeBetweenEvictionRunsMillis(60000);
        setLogAbandoned(true);
        setSuspectTimeout(60);

        setJdbcInterceptors("org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;"
                + "org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer;org.apache.tomcat.jdbc.pool.interceptor.SlowQueryReport");
    }

    @Override
    public void setUrl(@SuppressWarnings("unused") String url) {
	throw new UnsupportedOperationException("Use setHost/Port/DB() instead of setURL()");
    }

	@Override
	public String getUrl() {
		String url = super.getUrl();
		if (StringUtils.hasText(url)) {
			throw new IllegalStateException();
		}
		return jdbcProtocol + ":" + jdbcSubprotocol + "://" + getHost() + ":" + getPort() + "/" + getDBName();
	}

	public String getHost() {
		return hostname;
	}

	public String getPort() {
		return port;
	}

	public String getDBName() {
		return dbName;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public void setHost(String hostname) {
		this.hostname = hostname;
	}

	public void setDBName(String dbName) {
		this.dbName = dbName;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public void setPassword(String password) {
		this.password = password;
	}

}