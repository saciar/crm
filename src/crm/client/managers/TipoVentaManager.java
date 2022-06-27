package crm.client.managers;

import java.rmi.RemoteException;

import org.apache.wsif.WSIFException;

import crm.libraries.abm.entities.TipoVenta;
import crm.services.wsdl2.sei.TipoVentaManagerSEI;

public class TipoVentaManager extends CongressCRMServiceWSDL2 implements TipoVentaManagerSEI {
	private TipoVentaManagerSEI stub;
	
	public TipoVentaManager() throws WSIFException{
		super("TipoVentaManagerSEI");
	}
	
	
	protected void registerTypes() throws WSIFException {
		// map types
		try {
			addType("TipoVenta", Class.forName("crm.libraries.abm.entities.TipoVenta"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			//throw new WSIFException(e);
		}
		
		stub = (TipoVentaManagerSEI)service.getStub(TipoVentaManagerSEI.class);
	}

	public void remove(String codigo) throws RemoteException {
		stub.remove(codigo);
	}


	public void update(TipoVenta tipoEvento) throws RemoteException {
		stub.update(tipoEvento);
	}


	public TipoVenta[] findByField(String field, String value) throws RemoteException {
		return stub.findByField(field,value);
	}
	
	private static TipoVentaManager instance;

	public static TipoVentaManager instance() {
		try {
			if (instance == null) {
				instance = new TipoVentaManager();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}


	public Object[] getTipoVentasReport() throws RemoteException {
		return stub.getTipoVentasReport();
	}


	public TipoVenta getTipoEventoByDescripcion(String descripcion) throws RemoteException {
		return stub.getTipoVentaByDescripcion(descripcion);
	}


	@Override
	public TipoVenta getTipoVentaById(String codigo) throws RemoteException {
		// TODO Auto-generated method stub
		return stub.getTipoVentaById(codigo);
	}


	@Override
	public TipoVenta getTipoVentaByDescripcion(String descripcion) throws RemoteException {
		// TODO Auto-generated method stub
		return stub.getTipoVentaByDescripcion(descripcion);
	}


	@Override
	public TipoVenta[] getAllTipoVentas() throws RemoteException {
		// TODO Auto-generated method stub
		return stub.getAllTipoVentas();
	}

}
