/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crm.client.util;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

/**
 *
 * @author saciar
 */
public class SystemConfig {
	private Properties prop;
	private static SystemConfig instance;
	private HashMap seicache;
	private String nameSpace;
	private String typesNameSpace;
	private boolean preloadClientesEnabled;
	private boolean preloadCommonWebservicesEnabled;

	private SystemConfig(){
		seicache = new HashMap();
		prop = new Properties();
		ClassLoader classLoader = getClass().getClassLoader();

		try {
			InputStream is = classLoader.getResourceAsStream("client.properties");
			if (is != null){
				prop.load(is);

				is.close();
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}

		nameSpace = prop.getProperty("webservice.nameSpace");
		typesNameSpace = prop.getProperty("webservice.typesNameSpace");

		preloadClientesEnabled = getBooleanProperty("preload.clientes.enabled");
		preloadCommonWebservicesEnabled = getBooleanProperty("preload.common-webservices.enabled");
	}

	private boolean getBooleanProperty(String name){
		return (getIntProperty(name)== 0)?false: true;
	}

	private int getIntProperty(String name){
		int result = 0;

		try {
			result = Integer.parseInt(prop.getProperty(name));
		}
		catch (Exception e){}

		return result;
	}

	private static SystemConfig _getInstance(){
		if (instance == null){
			synchronized (SystemConfig.class){
				if (instance == null){
					instance = new SystemConfig();
				}
			}
		}

		return instance;
	}

	public static String getWsdlFor(String serviceName){
		return _getInstance()._getWsdlFor(serviceName);
	}

	public String _getWsdlFor(String serviceName){
		String result = (String)seicache.get(serviceName);

		if (result == null){
			StringBuffer url = new StringBuffer();
			url.append("http://");
			url.append(prop.getProperty("jboss.address"));
			url.append(":");
			url.append(prop.getProperty("jboss.port"));
			url.append(prop.getProperty("jboss.contexturl"));
			url.append(prop.getProperty("webservice.wsdl-url."+serviceName));

			result = url.toString();

			seicache.put(serviceName, result);
		}

		return result;
	}

	private String _getNameSpace() {
		return nameSpace;
	}

	private String _getTypesNameSpace() {
		return typesNameSpace;
	}

	public static String getNameSpace() {
		return _getInstance()._getNameSpace();
	}

	public static String getTypesNameSpace() {
		return _getInstance()._getTypesNameSpace();
	}

	private boolean _isPreloadClientesEnabled() {
		return preloadClientesEnabled;
	}

	private boolean _isPreloadCommonWebservicesEnabled() {
		return preloadCommonWebservicesEnabled;
	}

	public static boolean isPreloadClientesEnabled() {
		return _getInstance()._isPreloadClientesEnabled();
	}

	public static boolean isPreloadCommonWebservicesEnabled() {
		return _getInstance()._isPreloadCommonWebservicesEnabled();
	}
}
