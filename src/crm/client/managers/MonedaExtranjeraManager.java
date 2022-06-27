package crm.client.managers;

import java.rmi.RemoteException;

import org.apache.wsif.WSIFException;

import crm.libraries.abm.entities.MonedaExtranjera;

import crm.services.wsdl2.sei.MonedaExtranjeraManagerSEI;

public class MonedaExtranjeraManager extends CongressCRMServiceWSDL2 implements MonedaExtranjeraManagerSEI{
private static MonedaExtranjeraManager instance;
	
	private MonedaExtranjeraManagerSEI stub;	

	public static MonedaExtranjeraManager instance() {
		try {
			if (instance == null) {
				instance = new MonedaExtranjeraManager();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}
	
	public MonedaExtranjeraManager() throws WSIFException{
		super("MonedaExtranjeraManagerSEI");
	}
		
	protected void registerTypes() throws WSIFException {
		try {
			addType("MonedaExtranjera", Class.forName("crm.libraries.abm.entities.MonedaExtranjera"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		stub = (MonedaExtranjeraManagerSEI)service.getStub(MonedaExtranjeraManagerSEI.class);
	}

	public MonedaExtranjera getById(String codigo) throws RemoteException {
		return stub.getById(codigo);
	}

	public MonedaExtranjera[] getAll() throws RemoteException {
		return stub.getAll();
	}

	public void remove(String codigo) throws RemoteException {
		stub.remove(codigo);
	}

	public void update(MonedaExtranjera model) throws RemoteException {
		stub.update(model);
	}

	public MonedaExtranjera[] findByField(String field, String value) throws RemoteException {
		return stub.findByField(field,value);
	}
}
