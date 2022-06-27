package crm.client.managers;

import java.rmi.RemoteException;

import org.apache.wsif.WSIFException;

import crm.libraries.abm.entities.AgendaTareasCobranzas;
import crm.services.wsdl2.sei.AgendaTareasCobranzasManagerSEI;

public class AgendaTareasCobranzasManager extends CongressCRMServiceWSDL2 implements AgendaTareasCobranzasManagerSEI{

	private AgendaTareasCobranzasManagerSEI stub;
	private static AgendaTareasCobranzasManager instance;
	
	public static AgendaTareasCobranzasManager instance(){
		try {
			if (instance == null) {
				instance = new AgendaTareasCobranzasManager();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}
	
	public AgendaTareasCobranzasManager() throws WSIFException {
		super("AgendaTareasCobranzasManagerSEI");
	}	
	
	public void remove(String codigo) throws RemoteException{
		stub.remove(codigo);
	}
	public String update(AgendaTareasCobranzas administrador) throws RemoteException{
		return stub.update(administrador);
	}

	@Override
	protected void registerTypes() throws WSIFException {
		try {
			addType("AgendaTareasCobranzas", Class.forName("crm.libraries.abm.entities.AgendaTareasCobranzas"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			//throw new WSIFException(e);
		}
		
		stub = (AgendaTareasCobranzasManagerSEI)service.getStub(AgendaTareasCobranzasManagerSEI.class);
		
	}

	public AgendaTareasCobranzas[] findByField(String field, String value) throws RemoteException {
		// TODO Auto-generated method stub
		return stub.findByField(field,value);
	}

	public AgendaTareasCobranzas getAgendaById(String codigo) throws RemoteException {
		// TODO Auto-generated method stub
		return stub.getAgendaById(codigo);
	}
	
	public AgendaTareasCobranzas[] findAlertaToday(String date) throws RemoteException{
		return stub.findAlertaToday(date);
	}
	
	public Object[] getClienteEventoToAgenda(long nroppto) throws RemoteException{
		return stub.getClienteEventoToAgenda(nroppto);
	}

}
