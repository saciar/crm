package crm.client.managers;

import java.rmi.RemoteException;

import org.apache.wsif.WSIFException;

import crm.libraries.abm.entities.MarcosLiquidacion;
import crm.services.sei.MarcosLiquidacionManagerSEI;

public class MarcosLiquidacionManager extends CongressCRMService implements MarcosLiquidacionManagerSEI {
	private MarcosLiquidacionManagerSEI stub;
	
	public MarcosLiquidacionManager() throws WSIFException{
		super("MarcosLiquidacionManagerSEI");
	}
	
	
	protected void registerTypes() throws WSIFException {
		// map types
		try {
			addType("MarcosLiquidacion", Class.forName("crm.libraries.abm.entities.MarcosLiquidacion"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			//throw new WSIFException(e);
		}
		
		stub = (MarcosLiquidacionManagerSEI)service.getStub(MarcosLiquidacionManagerSEI.class);
	}


	public MarcosLiquidacion getMarcosLiquidacionById(String codigo) throws RemoteException {
		return stub.getMarcosLiquidacionById(codigo);
	}


	public MarcosLiquidacion[] getAllMarcosLiquidaciones() throws RemoteException {
		return stub.getAllMarcosLiquidaciones();
	}

	public MarcosLiquidacion[] findByField(String field,String value) throws RemoteException{
		return stub.findByField(field,value);
	}

	public void remove(String codigo) throws RemoteException {
		stub.remove(codigo);
	}


	public void update(MarcosLiquidacion marcosLiquidacion) throws RemoteException {
		stub.update(marcosLiquidacion);
	}
	
	private static MarcosLiquidacionManager instance;

	public static MarcosLiquidacionManager instance() {
		try {
			if (instance == null) {
				instance = new MarcosLiquidacionManager();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}


	public MarcosLiquidacion getMarcosLiquidacionByDescripcion(String descripcion) throws RemoteException {
		return stub.getMarcosLiquidacionByDescripcion(descripcion);
	}
}
