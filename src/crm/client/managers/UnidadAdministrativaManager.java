package crm.client.managers;

import java.rmi.RemoteException;

import org.apache.wsif.WSIFException;

import crm.libraries.abm.entities.UnidadAdministrativa;
import crm.services.wsdl2.sei.UnidadesAdministrativasManagerSEI;

public class UnidadAdministrativaManager extends CongressCRMServiceWSDL2 implements UnidadesAdministrativasManagerSEI{
	
	private static UnidadAdministrativaManager instance;
	
	private UnidadesAdministrativasManagerSEI stub;
	
	public UnidadAdministrativaManager() throws WSIFException{
		super("UnidadesAdministrativasManagerSEI");
	}
	
	public static UnidadAdministrativaManager instance() {
		try {
			if (instance == null) {
				instance = new UnidadAdministrativaManager();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}
	
	@Override
	protected void registerTypes() throws WSIFException {
		try {
			addType("UnidadAdministrativa", Class.forName("crm.libraries.abm.entities.UnidadAdministrativa"));			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			//throw new WSIFException(e);
		}
		
		stub = (UnidadesAdministrativasManagerSEI)service.getStub(UnidadesAdministrativasManagerSEI.class);
		
	}

	public UnidadAdministrativa getUnidadComercialById(String codigo) throws RemoteException {		
		return stub.getUnidadComercialById(codigo);
	}

	public void remove(String codigo) throws RemoteException {
		stub.remove(codigo);		
	}

	public String update(UnidadAdministrativa unidadAdministrativa) throws RemoteException {
		return stub.update(unidadAdministrativa);
	}
	
	public UnidadAdministrativa[] findByField(String field,String value)throws RemoteException{
		return stub.findByField(field, value);
	}
	
	public UnidadAdministrativa[] getAll()throws RemoteException{
		return stub.getAll();
	}

}
