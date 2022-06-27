package crm.client.managers;

import java.rmi.RemoteException;

import org.apache.wsif.WSIFException;

import crm.libraries.abm.entities.ModalidadContrat;
import crm.services.sei.ModalidadContratManagerSEI;

public class ModalidadContratManager extends CongressCRMService implements ModalidadContratManagerSEI {
	private ModalidadContratManagerSEI stub;
	
	public ModalidadContratManager() throws WSIFException{
		super("ModalidadContratManagerSEI");
	}
	
	
	protected void registerTypes() throws WSIFException {
		// map types
		try {
			addType("ModalidadContrat", Class.forName("crm.libraries.abm.entities.ModalidadContrat"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			//throw new WSIFException(e);
		}
		
		stub = (ModalidadContratManagerSEI)service.getStub(ModalidadContratManagerSEI.class);
	}


	public ModalidadContrat getModalidadContratById(String codigo) throws RemoteException {
		return stub.getModalidadContratById(codigo);
	}


	public ModalidadContrat[] getAllModalidadContrats() throws RemoteException {
		return stub.getAllModalidadContrats();
	}

	public void remove(String codigo) throws RemoteException {
		stub.remove(codigo);
	}


	public void update(ModalidadContrat modalidadContrat) throws RemoteException {
		stub.update(modalidadContrat);
	}


	public ModalidadContrat[] findByField(String field, String value) throws RemoteException {
		return stub.findByField(field,value);
	}
	
	private static ModalidadContratManager instance;

	public static ModalidadContratManager instance() {
		try {
			if (instance == null) {
				instance = new ModalidadContratManager();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}


	public ModalidadContrat getModalidadContratByDescripcion(String descripcion) throws RemoteException {
		return stub.getModalidadContratByDescripcion(descripcion);
	}
}
