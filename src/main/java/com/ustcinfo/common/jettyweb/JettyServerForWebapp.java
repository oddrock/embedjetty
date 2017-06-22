package com.ustcinfo.common.jettyweb;

import org.eclipse.jetty.server.Server;  
import org.eclipse.jetty.webapp.WebAppContext;  

public class JettyServerForWebapp {
	private static final PropertiesReader PROP_READER = new PropertiesReader("jettyserver-cfg.properties");
	private static final int DEFAULT_PORT = Integer.parseInt(PROP_READER.getValue("default.port"));
	private static final String DEFAULT_WAR_NAME = PROP_READER.getValue("default.warname");
	private static final String DEFAULT_CONTEXT_PATH = PROP_READER.getValue("default.contextpath");

	public static void main(String args[]) throws Exception {
		int port = DEFAULT_PORT;
		if (args != null && args.length > 0 && args[0] != null){
			port = Integer.parseInt(args[0].trim());
		}
		String warName = DEFAULT_WAR_NAME;
		if (args != null && args.length > 1 && args[1] != null && !args[1].trim().equals("")){
			warName = args[1].trim();
		}
		String contextPath = DEFAULT_CONTEXT_PATH;
		if (args != null && args.length > 2 && args[2] != null && !args[2].trim().equals("")){
			contextPath = args[2].trim();
		}
		Server server = new Server(port);
		WebAppContext webapp = new WebAppContext();
		webapp.setContextPath(contextPath);
		webapp.setWar(warName);
		server.setHandler(webapp);
		server.start();
		server.join();
	}
}