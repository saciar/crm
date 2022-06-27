package crm.client.managers;

import java.rmi.RemoteException;

import org.apache.wsif.WSIFException;

import crm.libraries.abm.entities.TipoArmado;
import crm.services.sei.TipoArmadoManagerSEI;

public class TipoArmadoManager extends CongressCRMService implements TipoArmadoManagerSEI {
	private static TipoArmadoManager instance;
	
	private TipoArmadoManagerSEI stub;	

	public static TipoArmadoManager instance() {
		try {
			if (instance == null) {
				instance = new TipoArmadoManager();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}
	
	public TipoArmadoManager() throws WSIFException{
		super("TipoArmadoManagerSEI");
	}
		
	protected void registerTypes() throws WSIFException {
		try {
			addType("TipoArmado", Class.forName("crm.libraries.abm.entities.TipoArmado"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		stub = (TipoArmadoManagerSEI)service.getStub(TipoArmadoManagerSEI.class);
	}

	public TipoArmado getById(String codigo) throws RemoteException {
		return stub.getById(codigo);
	}

	public TipoArmado[] getAll() throws RemoteException {
		return stub.getAll();
	}

	public void remove(String codigo) throws RemoteException {
		stub.remove(codigo);
	}

	public void update(TipoArmado model) throws RemoteException {
		stub.update(model);
	}

	public TipoArmado[] findByField(String field, String value) throws RemoteException {
		return stub.findByField(field,value);
	}
	
	public Object[] getTipoArmadoReport() throws RemoteException{
		return stub.getTipoArmadoReport();
	}

}
