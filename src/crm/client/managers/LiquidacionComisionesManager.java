package crm.client.managers;

import java.rmi.RemoteException;

import org.apache.wsif.WSIFException;

import crm.libraries.abm.entities.LiquidacionComisiones;
import crm.services.sei.LiquidacionComisionesManagerSEI;

public class LiquidacionComisionesManager extends CongressCRMService implements LiquidacionComisionesManagerSEI {
	private LiquidacionComisionesManagerSEI stub;
	
	public LiquidacionComisionesManager() throws WSIFException{
		super("LiquidacionComisionesManagerSEI");
	}
	
	
	protected void registerTypes() throws WSIFException {
		// map types
		try {
			addType("LiquidacionComisiones", Class.forName("crm.libraries.abm.entities.LiquidacionComisiones"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			//throw new WSIFException(e);
		}
		
		stub = (LiquidacionComisionesManagerSEI)service.getStub(LiquidacionComisionesManagerSEI.class);
	}


	public void update(LiquidacionComisiones l) throws RemoteException {
		stub.update(l);		
	}


	public boolean isPptoLiquidado(String nroPpto) throws RemoteException {
		return stub.isPptoLiquidado(nroPpto);
	}
	
	private static LiquidacionComisionesManager instance;

	public static LiquidacionComisionesManager instance() {
		try {
			if (instance == null) {
				instance = new LiquidacionComisionesManager();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}
	
	public String getTipoComisionByNroPpto(String nroPpto) throws RemoteException{
		return stub.getTipoComisionByNroPpto(nroPpto);
	}
}
