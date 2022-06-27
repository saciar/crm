package crm.client.managers;

import java.rmi.RemoteException;

import org.apache.wsif.WSIFException;

import crm.libraries.abm.entities.UnidadBonus;
import crm.services.sei.UnidadBonusManagerSEI;

public class UnidadBonusManager extends CongressCRMService implements UnidadBonusManagerSEI {
	private UnidadBonusManagerSEI stub;
	
	public UnidadBonusManager() throws WSIFException{
		super("UnidadBonusManagerSEI");
	}
	
	
	protected void registerTypes() throws WSIFException {
		// map types
		try {
			addType("UnidadBonus", Class.forName("crm.libraries.abm.entities.UnidadBonus"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			//throw new WSIFException(e);
		}
		
		stub = (UnidadBonusManagerSEI)service.getStub(UnidadBonusManagerSEI.class);
	}


	public UnidadBonus getUnidadBonusById(String codigo,String nivel) throws RemoteException {
		return stub.getUnidadBonusById(codigo,nivel);
	}


	public UnidadBonus[] findByUnidadComercialId(String unidadComercial) throws RemoteException {
		return stub.findByUnidadComercialId(unidadComercial);
	}

	public UnidadBonus[] getAllUnidadBonus() throws RemoteException {
		return stub.getAllUnidadBonus();
	}


	public UnidadBonus[] findByField(String field,String value) throws RemoteException {
		return stub.findByField(field,value);
	}

	public void remove(String codigo,String nivel) throws RemoteException {
		stub.remove(codigo,nivel);
	}


	public void update(UnidadBonus unidadBonus) throws RemoteException {
		stub.update(unidadBonus);
	}
	
	private static UnidadBonusManager instance;

	public static UnidadBonusManager instance() {
		try {
			if (instance == null) {
				instance = new UnidadBonusManager();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}


}
