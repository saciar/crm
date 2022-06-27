/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crm.client.managers;

import crm.libraries.abm.entities.IngresoEquipo;
import crm.services.wsdl2.sei.IngresoEquipoManagerSEI;
import java.rmi.RemoteException;
import org.apache.wsif.WSIFException;

/**
 *
 * @author saciar
 */
public class IngresoEquipoManager extends CongressCRMServiceWSDL2 implements IngresoEquipoManagerSEI{

    private IngresoEquipoManagerSEI stub;
    private static IngresoEquipoManager instance;

    public static IngresoEquipoManager instance() {
		try {
			if (instance == null) {
				instance = new IngresoEquipoManager();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}

    public IngresoEquipoManager() throws WSIFException{
        super("IngresoEquipoManagerSEI");
    }

    protected void registerTypes() throws WSIFException{
        try{
            addType("IngresoEquipo", Class.forName("crm.libraries.abm.entities.IngresoEquipoManager"));
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        stub=(IngresoEquipoManagerSEI)service.getStub(IngresoEquipoManagerSEI.class);
    }

    public IngresoEquipo getById(String codigo) throws RemoteException {
        return stub.getById(codigo);
    }

    public IngresoEquipo[] getAll() throws RemoteException {
        return stub.getAll();
    }

    public IngresoEquipo[] findByField(String field, String value) throws RemoteException {
        return stub.findByField(field, value);
    }

    public void remove(String codigo) throws RemoteException {
        stub.remove(codigo);
    }

    public void update(IngresoEquipo model) throws RemoteException {
        stub.update(model);
    }

    public IngresoEquipo[] findByFields(Object[] field, Object[] value) throws RemoteException {
        return stub.findByFields(field, value);
    }

    public IngresoEquipo[] findByFieldExactly(String field, String value) throws RemoteException {
        return stub.findByFieldExactly(field, value);
    }

}
