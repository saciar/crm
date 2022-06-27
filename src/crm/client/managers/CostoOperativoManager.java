package crm.client.managers;

import java.rmi.RemoteException;

import org.apache.wsif.WSIFException;

import crm.libraries.abm.entities.CostoOperativo;
import crm.services.sei.CostoOperativoManagerSEI;

public class CostoOperativoManager extends CongressCRMService implements CostoOperativoManagerSEI {
	private CostoOperativoManagerSEI stub;
	
	public CostoOperativoManager() throws WSIFException{
		super("CostoOperativoManagerSEI");
	}
	
	
	protected void registerTypes() throws WSIFException {
		try {
			addType("CostoOperativo", Class.forName("crm.libraries.abm.entities.CostoOperativo"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		stub = (CostoOperativoManagerSEI)service.getStub(CostoOperativoManagerSEI.class);
	}


	public CostoOperativo getCostoOperativo() throws RemoteException {
		return stub.getCostoOperativo();
	}


	public void update(CostoOperativo costoOperativo) throws RemoteException {
		stub.update(costoOperativo);
	}
	
	private static CostoOperativoManager instance;

	public static CostoOperativoManager instance() {
		try {
			if (instance == null) {
				instance = new CostoOperativoManager();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}
}
