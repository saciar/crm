package crm.client.managers;

import java.rmi.RemoteException;

import org.apache.wsif.WSIFException;

import crm.libraries.abm.entities.FamiliaServ;
import crm.services.sei.FamiliaServManagerSEI;

public class FamiliaServManager extends CongressCRMService implements FamiliaServManagerSEI {
	private FamiliaServManagerSEI stub;
	
	public FamiliaServManager() throws WSIFException{
		super("FamiliaServManagerSEI");
	}
	
	
	protected void registerTypes() throws WSIFException {
		// map types
		try {
			addType("FamiliaServ", Class.forName("crm.libraries.abm.entities.FamiliaServ"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			//throw new WSIFException(e);
		}
		
		stub = (FamiliaServManagerSEI)service.getStub(FamiliaServManagerSEI.class);
	}


	public FamiliaServ getFamiliaServById(String codigo) throws RemoteException {
		return stub.getFamiliaServById(codigo);
	}


	public FamiliaServ[] getAllFamiliaServs() throws RemoteException {
		return stub.getAllFamiliaServs();
	}


	public void remove(String codigo) throws RemoteException {
		stub.remove(codigo);
	}


	public void update(FamiliaServ familiaServ) throws RemoteException {
		stub.update(familiaServ);
	}


	public FamiliaServ[] findByField(String field, String value) throws RemoteException {
		return stub.findByField(field,value);
	}
	
	private static FamiliaServManager instance;

	public static FamiliaServManager instance() {
		try {
			if (instance == null) {
				instance = new FamiliaServManager();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}
	
	public Object[] getFamiliaReport() throws RemoteException {
		return stub.getFamiliaReport();
	}


	public FamiliaServ getFamiliaServByDescripcion(String descripcion) throws RemoteException {
		return stub.getFamiliaServByDescripcion(descripcion);
	}
	
	public String getDescripcionByServicio(String codFamServ) throws RemoteException {
		return stub.getDescripcionByServicio(codFamServ);
	}
}
