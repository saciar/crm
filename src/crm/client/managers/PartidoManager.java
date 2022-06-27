package crm.client.managers;

import java.rmi.RemoteException;

import org.apache.wsif.WSIFException;

import crm.libraries.abm.entities.Partido;
import crm.services.sei.PartidoManagerSEI;

public class PartidoManager extends CongressCRMService implements PartidoManagerSEI {
	private PartidoManagerSEI stub;
	
	public PartidoManager() throws WSIFException{
		super("PartidoManagerSEI");
	}
	
	
	protected void registerTypes() throws WSIFException {
		// map types
		try {
			addType("Partido", Class.forName("crm.libraries.abm.entities.Partido"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			//throw new WSIFException(e);
		}
		
		stub = (PartidoManagerSEI)service.getStub(PartidoManagerSEI.class);
	}

	public Partido[] findByProvinciaId(String value) throws RemoteException{
		return stub.findByProvinciaId(value);
	}

	public Partido getPartidoById(String codigo) throws RemoteException {
		return stub.getPartidoById(codigo);
	}

	public Partido getPartidoByDescripcion(String desc) throws RemoteException {
		return stub.getPartidoByDescripcion(desc);
	}
	
	public Partido[] getAllPartidos() throws RemoteException {
		return stub.getAllPartidos();
	}
	
	public Object[] findNamesByProvinciaId(String value) throws RemoteException{
		return stub.findNamesByProvinciaId(value);
	}

	public void remove(String codigo) throws RemoteException {
		stub.remove(codigo);
	}


	public void update(Partido partido) throws RemoteException {
		stub.update(partido);
	}


	public Partido[] findByField(String field, String value) throws RemoteException {
		return stub.findByField(field,value);
	}
	
	private static PartidoManager instance;

	public static PartidoManager instance() {
		try {
			if (instance == null) {
				instance = new PartidoManager();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}


	public String getNombrePartidoById(String codigo) throws RemoteException {
		return stub.getNombrePartidoById(codigo);
	}


	public Partido getPartidoByCodPartido(String codPartido) throws RemoteException {
		return stub.getPartidoByCodPartido(codPartido);
	}
}
