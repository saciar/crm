/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui.tables;

import crm.client.util.Utils;
import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author sergio
 */
public class XLSFillter extends FileFilter {

	    //Accept all directories and all xls files.
	    public boolean accept(File f) {
	        if (f.isDirectory()) {
	            return true;
	        }

	        String extension = Utils.getExtension(f);
	        if (extension != null) {
	            if (extension.equals(Utils.xls)) {
	                    return true;
	            } else {
	                return false;
	            }
	        }

	        return false;
	    }

	    //The description of this filter
	    public String getDescription() {
	        return "Excel (*.xls)";
	    }
    
}
