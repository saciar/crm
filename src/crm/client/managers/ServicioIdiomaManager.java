package crm.client.managers;

import java.rmi.RemoteException;

import org.apache.wsif.WSIFException;

import crm.libraries.abm.entities.ServicioIdioma;
import crm.services.sei.ServicioIdiomaManagerSEI;
 
public class ServicioIdiomaManager extends CongressCRMService implements ServicioIdiomaManagerSEI {
	private ServicioIdiomaManagerSEI stub;
	
	public ServicioIdiomaManager() throws WSIFException{
		super("ServicioIdiomaManagerSEI");
	}
	
	
	protected void registerTypes() throws WSIFException {
		// map types
		try {
			addType("ServicioIdioma", Class.forName("crm.libraries.abm.entities.ServicioIdioma"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			//throw new WSIFException(e);
		}
		
		stub = (ServicioIdiomaManagerSEI)service.getStub(ServicioIdiomaManagerSEI.class);
	}

	public String getDescripcionServicio(String codigoServicio,
			String codigoIdioma) throws RemoteException {
		return stub.getDescripcionServicio(codigoServicio,codigoIdioma);
	}
	
	public ServicioIdioma getServicioIdiomaByIdNoIdioma(String codigoServicio) throws RemoteException{
		return stub.getServicioIdiomaByIdNoIdioma(codigoServicio);
	}

	public ServicioIdioma getServicioIdiomaById(String codigoServicio,String codigoIdioma) throws RemoteException {
		return stub.getServicioIdiomaById(codigoServicio,codigoIdioma);
	}


	public ServicioIdioma[] getAllServicioIdiomas() throws RemoteException {
		return stub.getAllServicioIdiomas();
	}


	public void remove(String codigoServicio,String codigoIdioma) throws RemoteException {
		stub.remove(codigoServicio,codigoIdioma);
	}


	public void update(ServicioIdioma servicioIdioma) throws RemoteException {
		stub.update(servicioIdioma);
	}


	public ServicioIdioma[] findByField(String field, String value) throws RemoteException {
		return stub.findByField(field,value);
	}
	
	private static ServicioIdiomaManager instance;

	public static ServicioIdiomaManager instance() {
		try {
			if (instance == null) {
				instance = new ServicioIdiomaManager();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}
}
