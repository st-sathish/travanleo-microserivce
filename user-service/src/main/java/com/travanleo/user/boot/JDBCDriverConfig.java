package com.travanleo.user.boot;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class JDBCDriverConfig {

	private final static String DRIVER_CLASS_PROPERTY_NAME = "DRIVERCLASS_NAME" ;
	private final static String PROTOCOL_PROPERTY_NAME = "PROTOCOL" ;
	private final static String SUBPROTOCOL_PROPERTY_NAME = "SUB_PROTOCOL" ;
	private final static String PORT_PROPERTY_NAME = "PORT" ;
	
	private String driverClassName ;
	private String protocol ;
	private String subProtocol ;
	private String port ;
	
	@Autowired
	ApplicationContext context ;
	
	@PostConstruct
	protected void init() {
		Environment environment = context.getEnvironment() ;
		driverClassName = (String)environment.getProperty(DRIVER_CLASS_PROPERTY_NAME) ;
		protocol = (String) environment.getProperty(PROTOCOL_PROPERTY_NAME) ;
		subProtocol = (String) environment.getProperty(SUBPROTOCOL_PROPERTY_NAME) ;
		port = (String) environment.getProperty(PORT_PROPERTY_NAME) ;
	}
	
    public String getDriverClassName() {
    	return this.driverClassName ;
    }
    
    public String getProtocol() {
    	return this.protocol ;
    }

    public String getSubProtocol() {
    	return this.subProtocol ;
    }
    
    public String getPort() {
    	return this.port ;
    }
    
    public String constructProtocol(String schemaServer, String schemaServerPort, String schemaName) {
		// example
		//jdbc:mysql://localhost:3306/db_name
    	final String url = new StringBuilder(protocol).append(":").append(subProtocol).append("://").append(schemaServer).append(':').append(schemaServerPort)
                .append('/').append(schemaName).toString();
        return url;
    }
}
