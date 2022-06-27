package crm.client.managers;

import java.rmi.RemoteException;

import org.apache.wsif.WSIFException;

import crm.libraries.abm.entities.ResultadoSeguimiento;
import crm.services.sei.ResultadoSeguimientoManagerSEI;

public class ResultadoSeguimientoManager extends CongressCRMService implements ResultadoSeguimientoManagerSEI {
	private ResultadoSeguimientoManagerSEI stub;
	
	public ResultadoSeguimientoManager() throws WSIFException{
		super("ResultadoSeguimientoManagerSEI");
	}
	
	
	protected void registerTypes() throws WSIFException {
		// map types
		try {
			addType("ResultadoSeguimiento", Class.forName("crm.libraries.abm.entities.ResultadoSeguimiento"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			//throw new WSIFException(e);
		}
		
		stub = (ResultadoSeguimientoManagerSEI)service.getStub(ResultadoSeguimientoManagerSEI.class);
	}


	public ResultadoSeguimiento getResultadoSeguimientoById(String codigo) throws RemoteException {
		return stub.getResultadoSeguimientoById(codigo);
	}


	public ResultadoSeguimiento[] getAllResultadoSeguimientos() throws RemoteException {
		return stub.getAllResultadoSeguimientos();
	}

	public void remove(String codigo) throws RemoteException {
		stub.remove(codigo);
	}


	public void update(ResultadoSeguimiento resultadoSeguimiento) throws RemoteException {
		stub.update(resultadoSeguimiento);
	}


	public ResultadoSeguimiento[] findByField(String field, String value) throws RemoteException {
		return stub.findByField(field,value);
	}
	
	private static ResultadoSeguimientoManager instance;

	public static ResultadoSeguimientoManager instance() {
		try {
			if (instance == null) {
				instance = new ResultadoSeguimientoManager();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}


	public ResultadoSeguimiento getResultadoSeguimientoByDescripcion(String descripcion) throws RemoteException {
		return stub.getResultadoSeguimientoByDescripcion(descripcion);
	}
	
	public Object[] getResultadosReportByAccion(String codAccion) throws RemoteException{
		return stub.getResultadosReportByAccion(codAccion);
	}
}
