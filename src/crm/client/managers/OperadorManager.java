package crm.client.managers;

import java.rmi.RemoteException;
import java.util.List;

import org.apache.wsif.WSIFException;

import crm.libraries.abm.entities.Operador;
import crm.services.sei.OperadorManagerSEI;

public class OperadorManager extends CongressCRMService implements OperadorManagerSEI {
	private OperadorManagerSEI stub;
	
	public OperadorManager() throws WSIFException{
		super("OperadorManagerSEI");
	}
	
	
	protected void registerTypes() throws WSIFException {
		// map types
		try {
			addType("Operador", Class.forName("crm.libraries.abm.entities.Operador"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			//throw new WSIFException(e);
		}
		
		stub = (OperadorManagerSEI)service.getStub(OperadorManagerSEI.class);
	}


	public Operador getOperadorById(String codigo) throws RemoteException {
		return stub.getOperadorById(codigo);
	}


	public Operador[] getAllOperadores() throws RemoteException {
		return stub.getAllOperadores();
	}

	public Operador[] findByField(String field,String value) throws RemoteException{
		return stub.findByField(field,value);
	}


	public void remove(String codigo) throws RemoteException {
		stub.remove(codigo);
	}


	public void update(Operador operador) throws RemoteException {
		stub.update(operador);
	}
	
	private static OperadorManager instance;

	public static OperadorManager instance() {
		try {
			if (instance == null) {
				instance = new OperadorManager();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}


	public Operador getOperadorByApYNom(String apYNom) throws RemoteException {
		return stub.getOperadorByApYNom(apYNom);
	}


	public String getDescrpcion(String codigo) throws RemoteException {
		return stub.getDescrpcion(codigo);
	}
	
	public Operador[] getOperadorByModalidad(String modalidad) throws RemoteException{
		return stub.getOperadorByModalidad(modalidad);
	}
}
