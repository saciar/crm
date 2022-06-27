package crm.client.managers;

import java.rmi.RemoteException;

import org.apache.wsif.WSIFException;

import crm.libraries.abm.entities.Usuario;
import crm.services.sei.UsuarioManagerSEI;

public class UsuarioManager extends CongressCRMService implements UsuarioManagerSEI {
	private static UsuarioManager instance;
	private UsuarioManagerSEI stub;
	
	public UsuarioManager() throws WSIFException{
		super("UsuarioManagerSEI");
	}
	
	protected void registerTypes() throws WSIFException {
		// map types
		try {
			addType("Usuario", Class.forName("crm.libraries.abm.entities.Usuario"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			//throw new WSIFException(e);
		}
		
		stub = (UsuarioManagerSEI)service.getStub(UsuarioManagerSEI.class);
	}


	public Usuario getUsuarioById(String codigo) throws RemoteException {
		return stub.getUsuarioById(codigo);
	}

	public Usuario[] findByField(String field,String value) throws RemoteException {
		return stub.findByField(field,value);
	}


	public void remove(String codigo) throws RemoteException {
		stub.remove(codigo);
	}


	public void update(Usuario usuario) throws RemoteException {
		stub.update(usuario);
	}
	
	public static UsuarioManager instance() {
		try {
			if (instance == null) {
				instance = new UsuarioManager();
			}
		} catch (WSIFException e) {
			e.printStackTrace();			
		}
		return instance;
	}

	public Usuario getUsuarioById2(String name, String pass) throws RemoteException{
		return stub.getUsuarioById2(name, pass);
	}
	
	public String getUserCodeByUsername(String username, String password) throws RemoteException {
		return stub.getUserCodeByUsername(username,password);
	}
	
	public String getNameByCode(String code) throws RemoteException {
		return stub.getNameByCode(code);
	}
	
	/*public String getEmailByCode(String code) throws RemoteException {
		return stub.getEmailByCode(code);
	}*/
	
	public boolean getAccessTo(long codUsuario, int something) throws RemoteException {
		return stub.getAccessTo(codUsuario, something);
	}

	public boolean hasPerfil(String userId, String perfilId) throws RemoteException {
		return stub.hasPerfil(userId, perfilId);
	}
	
	public void sendPasswordByEmail (String codUsuario,String pass, String userName, String name) throws RemoteException{
		stub.sendPasswordByEmail(codUsuario, pass, userName, name);
	}
	
	
}
