package crm.client.managers;

import java.rmi.RemoteException;

import org.apache.wsif.WSIFException;

import crm.libraries.abm.entities.TipoCobrador;
import crm.services.wsdl2.sei.TipoCobradorManagerSEI;

public class TipoCobradorManager extends CongressCRMServiceWSDL2 implements TipoCobradorManagerSEI {
	private TipoCobradorManagerSEI stub;
	
	public TipoCobradorManager() throws WSIFException{
		super("TipoCobradorManagerSEI");
	}
	
	
	protected void registerTypes() throws WSIFException {
		// map types
		try {
			addType("TipoCobrador", Class.forName("crm.libraries.abm.entities.TipoCobrador"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			//throw new WSIFException(e);
		}
		
		stub = (TipoCobradorManagerSEI)service.getStub(TipoCobradorManagerSEI.class);
	}


	public TipoCobrador getTipoCobradorById(String codigo) throws RemoteException {
		return stub.getTipoCobradorById(codigo);
	}


	public TipoCobrador[] getAllTipoCobradores() throws RemoteException {
		return stub.getAllTipoCobradores();
	}


	public void remove(String codigo) throws RemoteException {
		stub.remove(codigo);
	}


	public void update(TipoCobrador tipoEvento) throws RemoteException {
		stub.update(tipoEvento);
	}


	public TipoCobrador[] findByField(String field, String value) throws RemoteException {
		return stub.findByField(field,value);
	}
	
	private static TipoCobradorManager instance;

	public static TipoCobradorManager instance() {
		try {
			if (instance == null) {
				instance = new TipoCobradorManager();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}


	public Object[] getTipoCobradoresReport() throws RemoteException {
		return stub.getTipoCobradoresReport();
	}


	public TipoCobrador getTipoCobradorByDescripcion(String descripcion) throws RemoteException {
		return stub.getTipoCobradorByDescripcion(descripcion);
	}

}
