package crm.client.managers;

import java.rmi.RemoteException;

import org.apache.wsif.WSIFException;

import crm.libraries.abm.entities.MarcaEquipo;
import crm.services.wsdl2.sei.MarcasEquiposManagerSEI;

public class MarcasEquiposManager extends CongressCRMServiceWSDL2 implements MarcasEquiposManagerSEI{
    private MarcasEquiposManagerSEI stub;
    private static MarcasEquiposManager instance;

    public static MarcasEquiposManager instance() {
		try {
			if (instance == null) {
				instance = new MarcasEquiposManager();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}

    public MarcasEquiposManager() throws WSIFException{
        super("MarcasEquiposManagerSEI");
    }

    protected void registerTypes() throws WSIFException{
        try{
            addType("MarcaEquipo", Class.forName("crm.libraries.abm.entities.MarcaEquipo"));
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        stub=(MarcasEquiposManagerSEI)service.getStub(MarcasEquiposManagerSEI.class);
    }

    public MarcaEquipo getById(String codigo) throws RemoteException {
        return stub.getById(codigo);
    }

    public MarcaEquipo[] getAll() throws RemoteException {
        return stub.getAll();
    }

    public MarcaEquipo[] findByField(String field,String value) throws RemoteException {
        return stub.findByField(field, value);
    }

    public void remove(String codigo) throws RemoteException {
        stub.remove(codigo);
    }

    public void update(MarcaEquipo equipo) throws RemoteException{
        stub.update(equipo);
    }

}
