package crm.client.managers;

import java.rmi.RemoteException;

import org.apache.wsif.WSIFException;

import crm.libraries.abm.entities.CategReferencia;
import crm.services.sei.CategReferenciaManagerSEI;

public class CategReferenciaManager extends CongressCRMService implements CategReferenciaManagerSEI {
	private CategReferenciaManagerSEI stub;
	
	public CategReferenciaManager() throws WSIFException{
		super("CategReferenciaManagerSEI");
	}
	
	
	protected void registerTypes() throws WSIFException {
		// map types
		try {
			addType("CategReferencia", Class.forName("crm.libraries.abm.entities.CategReferencia"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			//throw new WSIFException(e);
		}
		
		stub = (CategReferenciaManagerSEI)service.getStub(CategReferenciaManagerSEI.class);
	}


	public CategReferencia getCategReferenciaById(String codigo) throws RemoteException {
		return stub.getCategReferenciaById(codigo);
	}


	public CategReferencia[] getAllCategReferencias() throws RemoteException {
		return stub.getAllCategReferencias();
	}

	public CategReferencia[] findByField(String field,String value) throws RemoteException {
		return stub.findByField(field,value);
	}

	public void remove(String codigo) throws RemoteException {
		stub.remove(codigo);
	}


	public void update(CategReferencia categReferencia) throws RemoteException {
		stub.update(categReferencia);
	}
	
	private static CategReferenciaManager instance;

	public static CategReferenciaManager instance() {
		try {
			if (instance == null) {
				instance = new CategReferenciaManager();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}
}
