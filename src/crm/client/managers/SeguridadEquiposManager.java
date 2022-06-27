package crm.client.managers;

import java.rmi.RemoteException;

import org.apache.wsif.WSIFException;

import crm.libraries.abm.entities.SeguridadEquipos;
import crm.services.wsdl2.sei.SeguridadEquiposManagerSEI;

public class SeguridadEquiposManager extends CongressCRMServiceWSDL2 implements SeguridadEquiposManagerSEI {
	private static SeguridadEquiposManager instance;
	
	private SeguridadEquiposManagerSEI stub;	

	public static SeguridadEquiposManager instance() {
		try {
			if (instance == null) {
				instance = new SeguridadEquiposManager();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}
	
	public SeguridadEquiposManager() throws WSIFException{
		super("SeguridadEquiposManagerSEI");
	}
		
	protected void registerTypes() throws WSIFException {
		try {
			addType("SeguridadEquipos", Class.forName("crm.libraries.abm.entities.SeguridadEquipos"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		stub = (SeguridadEquiposManagerSEI)service.getStub(SeguridadEquiposManagerSEI.class);
	}

	public SeguridadEquipos getById(String codigo) throws RemoteException {
		return stub.getById(codigo);
	}

	public SeguridadEquipos[] getAll() throws RemoteException {
		return stub.getAll();
	}

	public void remove(String codigo) throws RemoteException {
		stub.remove(codigo);
	}

	public void update(SeguridadEquipos model) throws RemoteException {
		stub.update(model);
	}

	public SeguridadEquipos[] findByField(String field, String value) throws RemoteException {
		return stub.findByField(field,value);
	}
	

}
