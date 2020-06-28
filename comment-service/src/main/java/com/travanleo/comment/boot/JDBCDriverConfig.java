package com.travanleo.comment.boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class JDBCDriverConfig {

	private final static String DRIVER_CLASS_PROPERTY_NAME = "DRIVERCLASS_NAME" ;
	private final static String PROTOCOL_PROPERTY_NAME = "PROTOCOL" ;
	private final static String SUBPROTOCOL_PROPERTY_NAME = "SUB_PROTOCOL" ;
	private final static String PORT_PROPERTY_NAME = "PORT" ;
	
	private String driverClassName ;
	private String protocol ;
	private String subProtocol ;
	private Integer port ;
	
	@Autowired
	ApplicationContext context ;
	
	@PostConstruct
	protected void init() {
		Environment environment = context.getEnvironment() ;
		driverClassName = (String)environment.getProperty(DRIVER_CLASS_PROPERTY_NAME) ;
		protocol = (String) environment.getProperty(PROTOCOL_PROPERTY_NAME) ;
		subProtocol = (String) environment.getProperty(SUBPROTOCOL_PROPERTY_NAME) ;
		port = Integer.parseInt((String) environment.getProperty(PORT_PROPERTY_NAME)) ;
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
    
    public Integer getPort() {
    	return this.port ;
    }
    
    public String constructProtocol(String schemaServer, String schemaServerPort, String schemaName) {
		// example
		//jdbc:mongodb://localhost:3306/db_name
    	final String url = new StringBuilder(protocol).append(":").append(subProtocol).append("://").append(schemaServer).append(':').append(schemaServerPort)
                .append('/').append(schemaName).toString();
        return url;
    }
}
