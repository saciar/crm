package crm.client.managers;

import java.rmi.RemoteException;

import org.apache.wsif.WSIFException;

import crm.libraries.abm.entities.TipoFactura;
import crm.services.wsdl2.sei.TipoFacturaManagerSEI;

public class TipoFacturaManager extends CongressCRMServiceWSDL2 implements TipoFacturaManagerSEI {
	private TipoFacturaManagerSEI stub;
	
	public TipoFacturaManager() throws WSIFException{
		super("TipoFacturaManagerSEI");
	}
	
	
	protected void registerTypes() throws WSIFException {
		// map types
		try {
			addType("TipoFactura", Class.forName("crm.libraries.abm.entities.TipoFactura"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			//throw new WSIFException(e);
		}
		
		stub = (TipoFacturaManagerSEI)service.getStub(TipoFacturaManagerSEI.class);
	}


	public TipoFactura getTipoFacturaById(String codigo) throws RemoteException {
		return stub.getTipoFacturaById(codigo);
	}


	public TipoFactura[] getAllTipoFacturas() throws RemoteException {
		return stub.getAllTipoFacturas();
	}


	public void remove(String codigo) throws RemoteException {
		stub.remove(codigo);
	}


	public void update(TipoFactura tipoFactura) throws RemoteException {
		stub.update(tipoFactura);
	}


	public TipoFactura[] findByField(String field, String value) throws RemoteException {
		return stub.findByField(field,value);
	}
	
	private static TipoFacturaManager instance;

	public static TipoFacturaManager instance() {
		try {
			if (instance == null) {
				instance = new TipoFacturaManager();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}


	public Object[] getTipoFacturasReport() throws RemoteException {
		return stub.getTipoFacturasReport();
	}


	public TipoFactura getTipoFacturaByDescripcion(String descripcion) throws RemoteException {
		return stub.getTipoFacturaByDescripcion(descripcion);
	}

}
