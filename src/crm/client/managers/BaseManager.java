package crm.client.managers;

import java.util.HashMap;
import java.util.Map;

import javax.wsdl.Definition;
import javax.wsdl.PortType;
import javax.wsdl.Service;
import javax.wsdl.WSDLException;
import javax.xml.namespace.QName;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.wsif.WSIFException;
import org.apache.wsif.WSIFService;
import org.apache.wsif.WSIFServiceFactory;
import org.apache.wsif.util.WSIFUtils;

import crm.client.util.SystemConfig;

public abstract class BaseManager {
	private static final Log log = LogFactory.getLog(BaseManager.class);

	// connection objects
	private static Map<String,WsdlConnection> connections;
	
	// service used by each subclass
	protected WSIFService service;
	
	
	protected BaseManager(String serviceName, String portType) {
		//this.portType = portType;
		synchronized(BaseManager.class){
			if (connections == null){
				connections = new HashMap<String,WsdlConnection>();
			}
		}

		try {
			
			WsdlConnection conn = null; 
			
			synchronized(BaseManager.class){
				conn = connections.get(serviceName);
				if (conn == null){
					conn = WsdlConnection.instance(serviceName);					
					connections.put(serviceName,conn);
				}
			}
			
			// creo el service
			service = conn.createService(portType);
			
			// mapeo las clases
			registerTypes();
			
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	protected abstract void registerTypes() throws WSIFException;
	
	protected void addType(String name, Class clazz) throws WSIFException{
		 /*if (log.isDebugEnabled()){
			log.debug("Agregando soporte para tipo de datos definido por el usuario: " + name);
		}*/
		 
		// map types
		service.mapType(new QName(SystemConfig.getTypesNameSpace(), name), clazz);
	}
}

class WsdlConnection {
	private static final Log log = LogFactory.getLog(WsdlConnection.class);
	private Definition def;
	private Map portTypes; 
	private Service service;
	private WSIFServiceFactory factory;
	private static Map connections;
	private static String servicenamespace;
	
	private WsdlConnection(){}
	
	private void init(String wsdlUrl, String serviceName) throws WSIFException{
        // create a service factory
        if (factory == null)
        	factory = WSIFServiceFactory.newInstance();
        
        synchronized(WsdlConnection.class){
        	if (def == null){
        		
        		/*if (log.isDebugEnabled()){
        			log.debug("Inicializando WsdlConnection con wsdl: " + wsdlUrl);
        		}*/
        		
        		servicenamespace = SystemConfig.getNameSpace();
        		//typesnamespace = SystemConfig.getTypesNameSpace();
        			
				// bajo el wsdl				
				try {
					def = WSIFUtils.readWSDL(null, wsdlUrl);
				} catch (WSDLException e) {
					e.printStackTrace();	
				}
				
				// bajo el service
				service = WSIFUtils.selectService(def,servicenamespace,serviceName);
				
				// bajo la lista de port types	private String portType;
				portTypes = WSIFUtils.getAllItems(def,"PortType");
			}
		}
		

        //service = factory.getService(wsdlUrl,null,null,SystemConfig.getNameSpace(),portType);
		//service = factory.getService(def,null,null,SystemConfig.getNameSpace(),portType);
		//service = factory.getService(def,null,null,SystemConfig.getNameSpace(),portType);

	}

	/**
	 * Permite crear un nuevo proxy.
	 * @param portType
	 * @return
	 * @throws WSIFException
	 */
	public WSIFService createService(String portType) throws WSIFException{
		long t = 0;
		/*if (log.isDebugEnabled()){
			t = System.currentTimeMillis();
			log.debug("Creando Proxy para " + portType + "...");
		}*/
		
		WSIFService serv = factory.getService(def,service,getPortType(portType));
		
        /*if (log.isDebugEnabled()){
        	t = System.currentTimeMillis() - t;
			log.debug("...conectado a "+ portType +" en " + t + "ms.");
		}*/
        
        return serv;
	}
	
	/**
	 * Busca un porttype
	 * @param name
	 * @return
	 */
	private PortType getPortType(String name){
		return (PortType)portTypes.get(new QName(servicenamespace, name));
	}
	
	public static WsdlConnection instance(String serviceName) throws WSIFException {
		if (connections == null){
			connections = new HashMap();
		}
		WsdlConnection connection = (WsdlConnection)connections.get(serviceName);
		if (connection == null){
			connection = new WsdlConnection();
			connection.init(SystemConfig.getWsdlFor(serviceName),serviceName);
			connections.put(serviceName,connection);
		}
		
		return connection;
	}
}
