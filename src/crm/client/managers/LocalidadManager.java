package crm.client.managers;

import java.rmi.RemoteException;

import org.apache.wsif.WSIFException;

import crm.libraries.abm.entities.Localidad;
import crm.services.sei.LocalidadManagerSEI;

public class LocalidadManager extends CongressCRMService implements LocalidadManagerSEI {
	private LocalidadManagerSEI stub;
	
	public LocalidadManager() throws WSIFException{
		super("LocalidadManagerSEI");
	}
	
	
	protected void registerTypes() throws WSIFException {
		// map types
		try {
			addType("Localidad", Class.forName("crm.libraries.abm.entities.Localidad"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			//throw new WSIFException(e);
		}
		
		stub = (LocalidadManagerSEI)service.getStub(LocalidadManagerSEI.class);
	}

	public Localidad[] findByPartidoId(String value) throws RemoteException{
		return stub.findByPartidoId(value);
	}
	
	public Localidad getLocalidadById(String codigo) throws RemoteException {
		return stub.getLocalidadById(codigo);
	}


	public Localidad[] getAllLocalidades() throws RemoteException {
		return stub.getAllLocalidades();
	}

	public Localidad[] findByField(String field,String value) throws RemoteException{
		return stub.findByField(field,value);
	}

	public void remove(String codigo) throws RemoteException {
		stub.remove(codigo);
	}
	
	public Object[] findNamesByPartidoId(String value) throws RemoteException{
		return stub.findNamesByPartidoId(value);
	}

	public void update(Localidad localidad) throws RemoteException {
		stub.update(localidad);
	}
	
	private static LocalidadManager instance;

	public static LocalidadManager instance() {
		try {
			if (instance == null) {
				instance = new LocalidadManager();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}


	public String getNombreLocalidadById(String codigo) throws RemoteException {
		return stub.getNombreLocalidadById(codigo);
	}


	public Localidad getLocalidadByCodLocalidad(String codLocalidad) throws RemoteException {
		return stub.getLocalidadByCodLocalidad(codLocalidad);
	}
	
	public Localidad getLocalidadByDescripcion(String descripcion) throws RemoteException {
		return stub.getLocalidadByDescripcion(descripcion);
	}
}
