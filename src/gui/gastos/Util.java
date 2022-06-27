package gui.gastos;

import java.awt.Component;
import java.awt.Point;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.apache.commons.lang.math.NumberUtils;

public class Util {
	private static final String ERR_TITLE = "Error";
	private static final String ALERT_TITLE = "Advertencia";
	private static final String QUESTION_TITLE = "¿Qué desea hacer?";
	private static final String CONFIRM_TITLE = "¿Confirma?";
	
	/**
	 * show an error message and print a stack trace to the console if in development mode (DEV_MODE = true)
	 * 
	 * @param owner
	 *            the owner of the message, or null
	 * @param msg
	 *            the message to display, or null
	 * @param e
	 *            the exception object describing the error, or null
	 */
	public static void errMsg(Component owner, String msg, Exception e) {
		if (msg != null) {
			JOptionPane.showMessageDialog(owner, msg, ERR_TITLE, JOptionPane.ERROR_MESSAGE);
		}
		if (e != null) {
			e.printStackTrace();
		}
	}
	
	public static void alertMsg(Component owner, String msg) {
		if(msg != null) {
			JOptionPane.showMessageDialog(owner, msg, ALERT_TITLE, JOptionPane.INFORMATION_MESSAGE);
		}
	} 
	
	public static boolean confirm(Component owner, String msg){
		int response = JOptionPane.showConfirmDialog(owner, msg, CONFIRM_TITLE, JOptionPane.YES_NO_OPTION);

		return (response == JOptionPane.YES_OPTION);
	}
	
	/*public static void questionMsg(Component owner, String msg) {
		if(msg != null) {
			JOptionPane.showOptionDialog(owner, msg, ALERT_TITLE, JOptionPane.INFORMATION_MESSAGE);
		}
	}*/ 	
	
    /**
     * Checks and answers if the given string is empty (<code>""</code>) 
     * or <code>null</code>.
     * 
     * <pre>
     * ValidationUtils.isEmpty(null)  == true
     * ValidationUtils.isEmpty("")    == true
     * ValidationUtils.isEmpty(" ")   == false
     * ValidationUtils.isEmpty("Hi ") == false
     * </pre>
     * 
     * @param str   the string to check, may be <code>null</code>
     * @return <code>true</code> if the string is empty or <code>null</code>
     * @see #isBlank(String)
     */
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }
    
    /**
     * Checks and answers if the given string is whitespace, 
     * empty (<code>""</code>) or <code>null</code>.
     * 
     * <pre>
     * ValidationUtils.isBlank(null)  == true
     * ValidationUtils.isBlank("")    == true
     * ValidationUtils.isBlank(" ")   == true
     * ValidationUtils.isBlank("Hi ") == false
     * </pre>
     * 
     * @param str   the string to check, may be <code>null</code>
     * @return <code>true</code> if the string is whitespace, empty 
     *    or <code>null</code>
     * @see #isEmpty(String)
     */
    public static boolean isBlank(String str) {
        return str == null || str.trim().length() == 0;
    }
    
    /**
     * Indica si el string contiene un numero entero valido
     * 
     * @param number
     * @return
     */
    public static boolean isNumber(String number){
    	if (isBlank(number))
    		return false;
    	if (!NumberUtils.isDigits(number))
    		return false;
    	
    	return true;
    }
    
    /**
     * Indica si el string contiene un numero valido y si ademas es un 
     * numero natural
     * 
     * @param number
     * @return
     */
    public static boolean isNaturalNumber(String number){
    	if (!isNumber(number))
    		return false;
    	
    	return Integer.parseInt(number.trim()) > 0;
    }
    
    public static Component getOpennerComponent(Component component){
    	component = component.getParent();
    	boolean finish = false;
    	while(!finish){
    		if(component == null){
    			finish = true;
    		}else{
    			if(component instanceof JFrame || component instanceof JDialog){
    				finish = true;
    			}else{    			    			
    				component = component.getParent();
    			}
    		}
    	}
    	return component;
    }    
    
    public static void center(Component parent, Component child) {
    	if(parent != null && child != null){    	
    		Point p = parent.getLocation();				
    		Point parentCenter = new Point ( p.x + parent.getWidth()/2 ,p.y + parent.getHeight()/2);
		
    		child.setLocation(parentCenter.x - child.getWidth()/2, parentCenter.y - child.getHeight()/2);
    	}
      }
    
    
    public static boolean isMail(String mail){
    	int length = mail.length();
    	int arroba = mail.indexOf("@");
    	int dot = mail.indexOf(".");
    	
    	return ((arroba > 0 && length > (dot + 1))) ; 	
    }
    
    public static boolean isCuit(String cuit){
    	//int guion  = cuit.indexOf("-");
    	//int guion2 = cuit.lastIndexOf("-");
    	
    	return (cuit.length()==11); 	
    }
    
    public static long convertCuitToLong(String prefijo, String inscripcion, String digVerificador){
    	String aux = prefijo.concat(inscripcion);
    	String cuit = aux.concat(digVerificador);
    	return Long.parseLong(cuit);
    	
    }
    
    public static boolean isTelephoneNumber(String tel){
    	int length = tel.length();
    	int guion = tel.indexOf("-");
    	
    	return ((guion > 0 && length > (guion + 1)));
    }

}
