package crm.client.managers;

import java.rmi.RemoteException;

import org.apache.wsif.WSIFException;

import crm.libraries.abm.entities.VistaPrecioServicioIdioma;
import crm.services.sei.VistaPrecioServicioIdiomaManagerSEI;


public class VistaPrecioServicioIdiomaManager extends CongressCRMService implements VistaPrecioServicioIdiomaManagerSEI{
private VistaPrecioServicioIdiomaManagerSEI stub;
	
	public VistaPrecioServicioIdiomaManager() throws WSIFException{
		super("VistaPrecioServicioIdiomaManagerSEI");
	}
	
	
	protected void registerTypes() throws WSIFException {
		// map types
		try {
			addType("VistaPrecioServicioIdioma", Class.forName("crm.libraries.abm.entities.VistaPrecioServicioIdioma"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			//throw new WSIFException(e);
		}
		
		stub = (VistaPrecioServicioIdiomaManagerSEI)service.getStub(VistaPrecioServicioIdiomaManagerSEI.class);
	}


	public VistaPrecioServicioIdioma getVistaPrecioServicioIdiomaById(String codigo) throws RemoteException {
		return stub.getVistaPrecioServicioIdiomaById(codigo);
	}


	public VistaPrecioServicioIdioma[] getAllVistaPrecioServicioIdioma() throws RemoteException {
		return stub.getAllVistaPrecioServicioIdioma();
	}

	public VistaPrecioServicioIdioma[] findByField(String field, String value) throws RemoteException {
		return stub.findByField(field,value);
	}
	
	private static VistaPrecioServicioIdiomaManager instance;

	public static VistaPrecioServicioIdiomaManager instance() {
		try {
			if (instance == null) {
				instance = new VistaPrecioServicioIdiomaManager();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}


	public double getVistaPrecioServicioIdiomaByServicioYLugar(String codServ, String codLugar) throws RemoteException {
		return stub.getVistaPrecioServicioIdiomaByServicioYLugar(codServ, codLugar);
	}
	
	public int getCountVistaPrecioServicioByLugar(String codLugar) throws RemoteException{
		return stub.getCountVistaPrecioServicioByLugar(codLugar);
	}
}
