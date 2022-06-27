package crm.client.managers;

import java.rmi.RemoteException;

import org.apache.wsif.WSIFException;

import crm.libraries.abm.entities.CondIVA;
import crm.services.sei.CondIVAManagerSEI;

public class CondIVAManager extends CongressCRMService implements CondIVAManagerSEI {
	private CondIVAManagerSEI stub;
	
	public CondIVAManager() throws WSIFException{
		super("CondIVAManagerSEI");
	}
	
	
	protected void registerTypes() throws WSIFException {
		// map types
		try {
			addType("CondIVA", Class.forName("crm.libraries.abm.entities.CondIVA"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			//throw new WSIFException(e);
		}
		
		stub = (CondIVAManagerSEI)service.getStub(CondIVAManagerSEI.class);
	}


	public CondIVA getCondIVAById(String codigo) throws RemoteException {
		return stub.getCondIVAById(codigo);
	}


	public CondIVA[] getAllCondIVAs() throws RemoteException {
		return stub.getAllCondIVAs();
	}


	public void remove(String codigo) throws RemoteException {
		stub.remove(codigo);
	}


	public void update(CondIVA condIVA) throws RemoteException {
		stub.update(condIVA);
	}


	public CondIVA[] findByField(String field, String value) throws RemoteException {
		return stub.findByField(field,value);
	}
	
	private static CondIVAManager instance;

	public static CondIVAManager instance() {
		try {
			if (instance == null) {
				instance = new CondIVAManager();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}


	public CondIVA getCondIVAByDescripcion(String descripcion) throws RemoteException {
		return stub.getCondIVAByDescripcion(descripcion);
	}
}
