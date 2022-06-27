package crm.client.managers;

import java.rmi.RemoteException;

import org.apache.wsif.WSIFException;

import crm.libraries.abm.entities.EquiposSubFamilias;
import crm.services.wsdl2.sei.EquiposSubFamiliasManagerSEI;

public class EquiposSubFamiliasManager extends CongressCRMServiceWSDL2 implements EquiposSubFamiliasManagerSEI{
    private EquiposSubFamiliasManagerSEI stub;
    private static EquiposSubFamiliasManager instance;

    public static EquiposSubFamiliasManager instance() {
		try {
			if (instance == null) {
				instance = new EquiposSubFamiliasManager();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}

    public EquiposSubFamiliasManager() throws WSIFException{
        super("EquiposSubFamiliasManagerSEI");
    }

    protected void registerTypes() throws WSIFException{
        try{
            addType("EquiposSubFamilias", Class.forName("crm.libraries.abm.entities.EquiposSubFamilias"));
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        stub=(EquiposSubFamiliasManagerSEI)service.getStub(EquiposSubFamiliasManagerSEI.class);
    }

    public EquiposSubFamilias getById(String codigo) throws RemoteException {
        return stub.getById(codigo);
    }

    public EquiposSubFamilias[] getAll() throws RemoteException {
        return stub.getAll();
    }

    public EquiposSubFamilias[] findByField(String field,String value) throws RemoteException {
        return stub.findByField(field, value);
    }

    public void remove(String codigo) throws RemoteException {
        stub.remove(codigo);
    }

    public void update(EquiposSubFamilias equipo) throws RemoteException{
        stub.update(equipo);
    }

}
