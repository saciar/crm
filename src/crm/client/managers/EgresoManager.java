package crm.client.managers;

import java.rmi.RemoteException;

import org.apache.wsif.WSIFException;

import crm.libraries.abm.entities.Egreso;
import crm.services.wsdl2.sei.EgresoManagerSEI;

public class EgresoManager extends CongressCRMServiceWSDL2 implements EgresoManagerSEI{

    private EgresoManagerSEI stub;
    private static EgresoManager instance;

    public static EgresoManager instance() {
		try {
			if (instance == null) {
				instance = new EgresoManager();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}

    public EgresoManager() throws WSIFException{
        super("EgresoManagerSEI");
    }

    protected void registerTypes() throws WSIFException{
        try{
            addType("Egreso", Class.forName("crm.libraries.abm.entities.Egreso"));
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        stub=(EgresoManagerSEI)service.getStub(EgresoManagerSEI.class);
    }

    public Egreso getById(String codigo) throws RemoteException {
        return stub.getById(codigo);
    }

    public Egreso[] getAll() throws RemoteException {
        return stub.getAll();
    }

    public Egreso[] findByField(String field, String value) throws RemoteException {
        return stub.findByField(field, value);
    }

    public void remove(String codigo) throws RemoteException {
        stub.remove(codigo);
    }

    public String update(Egreso model) throws RemoteException {
        return stub.update(model);
    }

    public Egreso[] findByFields(Object[] field, Object[] value) throws RemoteException {
        return stub.findByFields(field, value);
    }

    public Egreso[] findByFieldExactly(String field, String value) throws RemoteException {
        return stub.findByFieldExactly(field, value);
    }

}
