/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crm.client.managers;

import crm.libraries.abm.entities.EquiposFamilias;
import crm.services.wsdl2.sei.EquiposFamiliasManagerSEI;
import java.rmi.RemoteException;
import org.apache.wsif.WSIFException;

/**
 *
 * @author saciar
 */
public class EquiposFamiliasManager extends CongressCRMServiceWSDL2 implements EquiposFamiliasManagerSEI{
    private EquiposFamiliasManagerSEI stub;
    private static EquiposFamiliasManager instance;

    public static EquiposFamiliasManager instance() {
		try {
			if (instance == null) {
				instance = new EquiposFamiliasManager();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}

    public EquiposFamiliasManager() throws WSIFException{
        super("EquiposFamiliasManagerSEI");
    }

    protected void registerTypes() throws WSIFException{
        try{
            addType("EquiposFamilias", Class.forName("crm.libraries.abm.entities.EquiposFamilias"));
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        stub=(EquiposFamiliasManagerSEI)service.getStub(EquiposFamiliasManagerSEI.class);
    }

    public EquiposFamilias getById(String codigo) throws RemoteException {
        return stub.getById(codigo);
    }

    public EquiposFamilias[] getAll() throws RemoteException {
        return stub.getAll();
    }

    public EquiposFamilias[] findByField(String field,String value) throws RemoteException {
        return stub.findByField(field, value);
    }

    public void remove(String codigo) throws RemoteException {
        stub.remove(codigo);
    }

    public void update(EquiposFamilias equipo) throws RemoteException{
        stub.update(equipo);
    }
}
