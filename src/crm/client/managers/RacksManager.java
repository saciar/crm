package crm.client.managers;

import java.rmi.RemoteException;

import org.apache.wsif.WSIFException;

import crm.libraries.abm.entities.Rack;
import crm.services.wsdl2.sei.RacksManagerSEI;

public class RacksManager extends CongressCRMServiceWSDL2 implements RacksManagerSEI{
    private RacksManagerSEI stub;
    private static RacksManager instance;

    public static RacksManager instance() {
		try {
			if (instance == null) {
				instance = new RacksManager();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}

    public RacksManager() throws WSIFException{
        super("RacksManagerSEI");
    }

    protected void registerTypes() throws WSIFException{
        try{
            addType("Rack", Class.forName("crm.libraries.abm.entities.Rack"));
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        stub=(RacksManagerSEI)service.getStub(RacksManagerSEI.class);
    }

	@Override
	public Rack getClienteContactoById(String codigo) throws RemoteException {
		// TODO Auto-generated method stub
		return stub.getClienteContactoById(codigo);
	}

	@Override
	public Rack[] findByField(String field, String value) throws RemoteException {
		// TODO Auto-generated method stub
		return stub.findByField(field, value);
	}

	@Override
	public void remove(String codigo) throws RemoteException {
		stub.remove(codigo);
		
	}

	@Override
	public String update(Rack rack) throws RemoteException {
		// TODO Auto-generated method stub
		return stub.update(rack);
	}
    
	public void removeRack(int codigo) throws RemoteException{
		stub.removeRack(codigo);
	}

}
