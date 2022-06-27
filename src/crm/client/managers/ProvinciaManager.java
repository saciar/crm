package crm.client.managers;

import java.rmi.RemoteException;

import org.apache.wsif.WSIFException;

import crm.libraries.abm.entities.Provincia;
import crm.services.sei.ProvinciaManagerSEI;

public class ProvinciaManager extends CongressCRMService implements ProvinciaManagerSEI {
	private ProvinciaManagerSEI stub;
	
	public ProvinciaManager() throws WSIFException{
		super("ProvinciaManagerSEI");		
	}
	
	
	protected void registerTypes() throws WSIFException {
		// map types
		try {
			addType("Provincia", Class.forName("crm.libraries.abm.entities.Provincia"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			//throw new WSIFException(e);
		}
		
		stub = (ProvinciaManagerSEI)service.getStub(ProvinciaManagerSEI.class);
	}




	public Provincia getProvinciaById(String codigo) throws RemoteException {
		return stub.getProvinciaById(codigo);
	}

	public Provincia[] findByPaisId(String value) throws RemoteException{
		return stub.findByPaisId(value);
	}
	
	public Object[] findCodAndDescriptionByPaisId(String value) throws RemoteException{
		return stub.findCodAndDescriptionByPaisId(value);
	}
	
	public Provincia getProvinciaByDescripcion(String desc) throws RemoteException{
		return stub.getProvinciaByDescripcion(desc);
	}
	
	public Provincia[] getAllProvincias() throws RemoteException {
		return stub.getAllProvincias();
	}

	public void remove(String codigo) throws RemoteException {
		stub.remove(codigo);
	}


	public void update(Provincia provincia) throws RemoteException {
		stub.update(provincia);
	}


	public Provincia[] findByField(String field, String value) throws RemoteException {
		return stub.findByField(field,value);
	}
	
	private static ProvinciaManager instance;

	public static ProvinciaManager instance() {
		try {
			if (instance == null) {
				instance = new ProvinciaManager();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}


	public String getNombreProvinciaById(String codigo) throws RemoteException {
		return stub.getNombreProvinciaById(codigo);
	}


	public Provincia getProvinciaByCodProvincia(String codProvincia) throws RemoteException {
		return stub.getProvinciaByCodProvincia(codProvincia);
	}
}
