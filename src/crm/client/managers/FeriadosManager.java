package crm.client.managers;

import java.rmi.RemoteException;

import org.apache.wsif.WSIFException;

import crm.services.wsdl2.sei.FeriadosManagerSEI;

public class FeriadosManager extends CongressCRMServiceWSDL2 implements FeriadosManagerSEI{

	private static FeriadosManager instance;
	
	private FeriadosManagerSEI stub;
	
	public static FeriadosManager instance(){
		try {
			if (instance == null) {
				instance = new FeriadosManager();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}
	
	public FeriadosManager() throws WSIFException{
		super("FeriadosManagerSEI");
	}
		
	protected void registerTypes() throws WSIFException {
		try {
			addType("Feriados", Class.forName("crm.libraries.abm.entities.Feriados"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		stub = (FeriadosManagerSEI)service.getStub(FeriadosManagerSEI.class);
	}
	
	public String getIdPorFecha(String f) throws RemoteException{
		return stub.getIdPorFecha(f);
	}
}
