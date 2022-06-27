package crm.client.managers;

import java.rmi.RemoteException;

import org.apache.wsif.WSIFException;

import crm.libraries.abm.entities.Asistente;
import crm.services.sei.AsistenteManagerSEI;

public class AsistenteManager extends CongressCRMService implements AsistenteManagerSEI {
	private AsistenteManagerSEI stub;
	
	public AsistenteManager() throws WSIFException{
		super("AsistenteManagerSEI");
	}
	
	
	protected void registerTypes() throws WSIFException {
		// map types
		try {
			addType("Asistente", Class.forName("crm.libraries.abm.entities.Asistente"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			//throw new WSIFException(e);
		}
		
		stub = (AsistenteManagerSEI)service.getStub(AsistenteManagerSEI.class);
	}


	public Asistente getAsistenteById(String codigo) throws RemoteException {
		return stub.getAsistenteById(codigo);
	}


	public Asistente[] getAllAsistentes() throws RemoteException {
		return stub.getAllAsistentes();
	}

	public void remove(String codigo) throws RemoteException {
		stub.remove(codigo);
	}


	public void update(Asistente asistente) throws RemoteException {
		stub.update(asistente);
	}


	public Asistente[] findByField(String field, String value) throws RemoteException {
		return stub.findByField(field,value);
	}
	
	
	private static AsistenteManager instance;

	public static AsistenteManager instance() {
		try {
			if (instance == null) {
				instance = new AsistenteManager();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}


	public Asistente getAsistenteByApYNom(String apYNom) throws RemoteException {
		return stub.getAsistenteByApYNom(apYNom);
	}


	public String getDescrpcion(String codigo) throws RemoteException {
		return stub.getDescrpcion(codigo);
	}
}
