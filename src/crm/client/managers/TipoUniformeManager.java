package crm.client.managers;

import java.rmi.RemoteException;

import org.apache.wsif.WSIFException;

import crm.libraries.abm.entities.TipoUniforme;
import crm.services.sei.TipoUniformeManagerSEI;

public class TipoUniformeManager extends CongressCRMService implements TipoUniformeManagerSEI {
	private TipoUniformeManagerSEI stub;
	
	private TipoUniformeManager() throws WSIFException{
		super("TipoUniformeManagerSEI");
	}
	
	
	protected void registerTypes() throws WSIFException {
		// map types
		try {
			addType("TipoUniforme", Class.forName("crm.libraries.abm.entities.TipoUniforme"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			//throw new WSIFException(e);
		}
		
		stub = (TipoUniformeManagerSEI)service.getStub(TipoUniformeManagerSEI.class);
	}


	public TipoUniforme getTipoUniformeById(String codigo) throws RemoteException {
		return stub.getTipoUniformeById(codigo);
	}


	public TipoUniforme[] getAllTipoUniformes() throws RemoteException {
		return stub.getAllTipoUniformes();
	}

	public void remove(String codigo) throws RemoteException {
		stub.remove(codigo);
	}


	public void update(TipoUniforme tipoUniforme) throws RemoteException {
		stub.update(tipoUniforme);
	}


	public TipoUniforme[] findByField(String field, String value) throws RemoteException {
		return stub.findByField(field,value);
	}
	
	private static TipoUniformeManager instance;

	public static TipoUniformeManager instance() {
		try {
			if (instance == null) {
				instance = new TipoUniformeManager();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}


	public Object[] getTiposUniformeReport() throws RemoteException {
		return stub.getTiposUniformeReport();
	}
}
