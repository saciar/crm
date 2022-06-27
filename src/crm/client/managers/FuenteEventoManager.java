package crm.client.managers;

import java.rmi.RemoteException;

import org.apache.wsif.WSIFException;

import crm.libraries.abm.entities.FuenteEvento;
import crm.services.wsdl2.sei.FuenteEventoManagerSEI;

public class FuenteEventoManager extends CongressCRMServiceWSDL2 implements FuenteEventoManagerSEI {
	private FuenteEventoManagerSEI stub;
	
	public FuenteEventoManager() throws WSIFException{
		super("FuenteEventoManagerSEI");
	}
	
	
	protected void registerTypes() throws WSIFException {
		// map types
		try {
			addType("FuenteEvento", Class.forName("crm.libraries.abm.entities.FuenteEvento"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			//throw new WSIFException(e);
		}
		
		stub = (FuenteEventoManagerSEI)service.getStub(FuenteEventoManagerSEI.class);
	}

	public void remove(String codigo) throws RemoteException {
		stub.remove(codigo);
	}


	public void update(FuenteEvento tipoEvento) throws RemoteException {
		stub.update(tipoEvento);
	}


	public FuenteEvento[] findByField(String field, String value) throws RemoteException {
		return stub.findByField(field,value);
	}
	
	private static FuenteEventoManager instance;

	public static FuenteEventoManager instance() {
		try {
			if (instance == null) {
				instance = new FuenteEventoManager();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}


	public Object[] getFuenteEventosReport() throws RemoteException {
		return stub.getFuenteEventosReport();
	}


	@Override
	public FuenteEvento getFuenteEventoById(String codigo) throws RemoteException {
		// TODO Auto-generated method stub
		return stub.getFuenteEventoById(codigo);
	}


	@Override
	public FuenteEvento getFuenteEventoByDescripcion(String descripcion) throws RemoteException {
		// TODO Auto-generated method stub
		return stub.getFuenteEventoByDescripcion(descripcion);
	}


	@Override
	public FuenteEvento[] getAllFuenteEventos() throws RemoteException {
		// TODO Auto-generated method stub
		return stub.getAllFuenteEventos();
	}

}
