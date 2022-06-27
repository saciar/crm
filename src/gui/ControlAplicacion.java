/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import crm.libraries.abm.entities.Usuario;

/**
 *
 * @author saciar
 */
public class ControlAplicacion {

    private static ControlAplicacion instance;
    private Usuario usuario;


    public ControlAplicacion(){

    }

    public static ControlAplicacion getInstance() {
        if (instance == null) {
            instance = new ControlAplicacion();
        }
        return instance;
    }

    public void setUsuario(Usuario user){
        usuario=user;
    }

    public Usuario getUsuario(){
        return usuario;
    }

}
