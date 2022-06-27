package crm.client.managers;

import java.rmi.RemoteException;

import org.apache.wsif.WSIFException;

import crm.libraries.abm.entities.GerenciaComercial;
import crm.services.sei.GerenciaComercialManagerSEI;

public class GerenciaComercialManager extends CongressCRMService implements GerenciaComercialManagerSEI {
	private GerenciaComercialManagerSEI stub;
	
	public GerenciaComercialManager() throws WSIFException{
		super("GerenciaComercialManagerSEI");
	}
	
	
	protected void registerTypes() throws WSIFException {
		// map types
		try {
			addType("GerenciaComercial", Class.forName("crm.libraries.abm.entities.GerenciaComercial"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			//throw new WSIFException(e);
		}
		
		stub = (GerenciaComercialManagerSEI)service.getStub(GerenciaComercialManagerSEI.class);
	}


	public GerenciaComercial getGerenciaComercialById(String codigo) throws RemoteException {
		return stub.getGerenciaComercialById(codigo);
	}


	public GerenciaComercial[] getAllGerenciaComercials() throws RemoteException {
		return stub.getAllGerenciaComercials();
	}



	public void remove(String codigo) throws RemoteException {
		stub.remove(codigo);
	}


	public void update(GerenciaComercial gerenciaComercial) throws RemoteException {
		stub.update(gerenciaComercial);
	}


	public GerenciaComercial[] findByField(String field, String value) throws RemoteException {
		return stub.findByField(field,value);
	}
	
	private static GerenciaComercialManager instance;

	public static GerenciaComercialManager instance() {
		try {
			if (instance == null) {
				instance = new GerenciaComercialManager();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}


	public GerenciaComercial[] findByDescripcionOrGerente(String descripcion, String gerente) throws RemoteException {
		return stub.findByDescripcionOrGerente(descripcion,gerente);
	}
}
