package crm.client.managers;

import java.rmi.RemoteException;

import org.apache.wsif.WSIFException;

import crm.libraries.abm.entities.Transporte;
import crm.services.wsdl2.sei.TransporteManagerSEI;

public class TransporteManager extends CongressCRMServiceWSDL2 implements TransporteManagerSEI{
    private TransporteManagerSEI stub;
    private static TransporteManager instance;

    public static TransporteManager instance() {
		try {
			if (instance == null) {
				instance = new TransporteManager();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}

    public TransporteManager() throws WSIFException{
        super("TransporteManagerSEI");
    }

    protected void registerTypes() throws WSIFException{
        try{
            addType("Transporte", Class.forName("crm.libraries.abm.entities.Transporte"));
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        stub=(TransporteManagerSEI)service.getStub(TransporteManagerSEI.class);
    }

    public Transporte getById(String codigo) throws RemoteException {
        return stub.getById(codigo);
    }

    public Transporte[] getAll() throws RemoteException {
        return stub.getAll();
    }

    public Transporte[] findByField(String field,String value) throws RemoteException {
        return stub.findByField(field, value);
    }

    public void remove(String codigo) throws RemoteException {
        stub.remove(codigo);
    }

    public void update(Transporte equipo) throws RemoteException{
        stub.update(equipo);
    }

}
