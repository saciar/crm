package crm.client.managers;

import java.rmi.RemoteException;

import org.apache.wsif.WSIFException;

import crm.libraries.abm.entities.Equipamientos;
import crm.services.wsdl2.sei.EquipamientosManagerSEI;

public class EquipamientosManager extends CongressCRMServiceWSDL2 implements EquipamientosManagerSEI{
    private EquipamientosManagerSEI stub;
    private static EquipamientosManager instance;

    public static EquipamientosManager instance() {
		try {
			if (instance == null) {
				instance = new EquipamientosManager();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}

    public EquipamientosManager() throws WSIFException{
        super("EquipamientosManagerSEI");
    }

    protected void registerTypes() throws WSIFException{
        try{
            addType("Equipamientos", Class.forName("crm.libraries.abm.entities.Equipamientos"));            
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        stub=(EquipamientosManagerSEI)service.getStub(EquipamientosManagerSEI.class);
    }

    public Equipamientos getById(String codigo) throws RemoteException {
        return stub.getById(codigo);
    }

    public Equipamientos[] getAll() throws RemoteException {
        return stub.getAll();
    }

    public Equipamientos[] findByField(String field,String value) throws RemoteException {
        return stub.findByField(field, value);
    }

    public void remove(String codigo) throws RemoteException {
        stub.remove(codigo);
    }

    public Equipamientos update(Equipamientos equipo) throws RemoteException{
        return stub.update(equipo);
    }

    public Equipamientos[] findByFields(Object[] field,Object[] value) throws RemoteException{
        return stub.findByFields(field, value);
    }
    public Equipamientos[] findByFieldExactly(String field,String value) throws RemoteException{
        return stub.findByFieldExactly(field, value);
    }

	@Override
	public Object[] buscarEquipamientoxCodigoBarras(int codigo, String valor) throws RemoteException {
		// TODO Auto-generated method stub
		return stub.buscarEquipamientoxCodigoBarras(codigo,valor);
	}

}
