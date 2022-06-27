/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package crm.client.managers;

import crm.libraries.abm.entities.MovimientoEquipo;
import crm.services.wsdl2.sei.MovimientoEquipoManagerSEI;
import java.rmi.RemoteException;
import org.apache.wsif.WSIFException;

/**
 *
 * @author sergio
 */
public class MovimientoEquipoManager extends CongressCRMServiceWSDL2 implements MovimientoEquipoManagerSEI{
    private MovimientoEquipoManagerSEI stub;
    private static MovimientoEquipoManager instance;

    public static MovimientoEquipoManager instance() {
		try {
			if (instance == null) {
				instance = new MovimientoEquipoManager();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}

    public MovimientoEquipoManager() throws WSIFException{
        super("MovimientoEquipoManagerSEI");
    }

    protected void registerTypes() throws WSIFException{
        try{
            addType("EgresoEquipo", Class.forName("crm.libraries.abm.entities.MovimientoEquipo"));
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        stub=(MovimientoEquipoManagerSEI)service.getStub(MovimientoEquipoManagerSEI.class);
    }

    public MovimientoEquipo getById(String codigo) throws RemoteException {
        return stub.getById(codigo);
    }

    public MovimientoEquipo[] getAll() throws RemoteException {
        return stub.getAll();
    }

    public MovimientoEquipo[] findByField(String field, String value) throws RemoteException {
        return stub.findByField(field, value);
    }

    public void remove(String codigo) throws RemoteException {
        stub.remove(codigo);
    }

    public void update(MovimientoEquipo model) throws RemoteException {
        stub.update(model);
    }

    public MovimientoEquipo[] findByFields(Object[] field, Object[] value) throws RemoteException {
        return stub.findByFields(field, value);
    }

    public MovimientoEquipo[] findByFieldExactly(String field, String value) throws RemoteException {
        return stub.findByFieldExactly(field, value);
    }
}
