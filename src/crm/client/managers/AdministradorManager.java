package crm.client.managers;

import java.rmi.RemoteException;

import org.apache.wsif.WSIFException;

import crm.libraries.abm.entities.Administrador;
import crm.services.wsdl2.sei.AdministradorManagerSEI;

public class AdministradorManager extends CongressCRMServiceWSDL2 implements AdministradorManagerSEI {
	
	private AdministradorManagerSEI stub;
	private static AdministradorManager instance;
	
	public static AdministradorManager instance(){
		try {
			if (instance == null) {
				instance = new AdministradorManager();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}
	
	public AdministradorManager() throws WSIFException {
		super("AdministradorManagerSEI");
	}	
	
	public void remove(String codigo) throws RemoteException{
		stub.remove(codigo);
	}
	public String update(Administrador administrador) throws RemoteException{
		return stub.update(administrador);
	}

	@Override
	protected void registerTypes() throws WSIFException {
		try {
			addType("Administrador", Class.forName("crm.libraries.abm.entities.Administrador"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			//throw new WSIFException(e);
		}
		
		stub = (AdministradorManagerSEI)service.getStub(AdministradorManagerSEI.class);
		
	}

	public Object[] getAdministradoresSinUnidadAdministrativa() throws RemoteException {
		return stub.getAdministradoresSinUnidadAdministrativa();
	}

	public Administrador[] findByField(String field, String value) throws RemoteException {
		return stub.findByField(field, value);
	}

	public Administrador getAdministradorByUserId(String codigo) throws RemoteException {
		return stub.getAdministradorByUserId(codigo);
	}
	
	public Object[] getAdministradoresSinUnidadAdministrativaPorUnidad(String codUnidad) throws RemoteException{
		return stub.getAdministradoresSinUnidadAdministrativaPorUnidad(codUnidad);
	}
	
	public String getCodAdministradorByCodUsuario(String codUsuario) throws RemoteException{
		return stub.getCodAdministradorByCodUsuario(codUsuario);
	}

}
