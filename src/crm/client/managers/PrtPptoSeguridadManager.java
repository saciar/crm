package crm.client.managers;

import java.rmi.RemoteException;

import org.apache.wsif.WSIFException;

import crm.libraries.abm.entities.PrtPptoSeguridad;
import crm.services.wsdl2.sei.PrtPptoSeguridadManagerSEI;

public class PrtPptoSeguridadManager extends CongressCRMServiceWSDL2 implements PrtPptoSeguridadManagerSEI {
	private static PrtPptoSeguridadManager instance;
	
	private PrtPptoSeguridadManagerSEI stub;	

	public static PrtPptoSeguridadManager instance() {
		try {
			if (instance == null) {
				instance = new PrtPptoSeguridadManager();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}
	
	public PrtPptoSeguridadManager() throws WSIFException{
		super("PrtPptoSeguridadManagerSEI");
	}
		
	protected void registerTypes() throws WSIFException {
		try {
			addType("PrtPptoSeguridad", Class.forName("crm.libraries.abm.entities.PrtPptoSeguridad"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		stub = (PrtPptoSeguridadManagerSEI)service.getStub(PrtPptoSeguridadManagerSEI.class);
	}

	public PrtPptoSeguridad getById(String codigo) throws RemoteException {
		return stub.getById(codigo);
	}

	public PrtPptoSeguridad[] getAll() throws RemoteException {
		return stub.getAll();
	}

	public void remove(String codigo) throws RemoteException {
		stub.remove(codigo);
	}

	public void update(PrtPptoSeguridad model) throws RemoteException {
		stub.update(model);
	}

	public PrtPptoSeguridad[] findByField(String field, String value) throws RemoteException{
		return stub.findByField(field,value);
	}

}
