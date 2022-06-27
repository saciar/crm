package gui.inventario.componentes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.CellEditor;
import javax.swing.JComboBox;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.plaf.ComboBoxUI;
import javax.swing.plaf.ComponentUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

import org.apache.commons.lang.StringUtils;

import crm.client.util.Util;


public abstract class ABMComboBox extends JComboBox{
	protected Vector m_codigoForeign;
	protected CellEditor cellEditor = null;
	protected TableCellRenderer tableCellRenderer = null;
	private JPopupMenu popup;
	
	public ABMComboBox (int x, int y){
		super();
/*		this.setPreferredSize( new Dimension(x,y));
		this.setMaximumSize( new Dimension(x,y));
		this.setSize( new Dimension(x,y));*/
		m_codigoForeign = new Vector();	
		popup = new JPopupMenu();
		JMenuItem menuItem = new JMenuItem("Recargar opciones");
		/*menuItem.setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_R, ActionEvent.CTRL_MASK+ActionEvent.ALT_MASK));*/
	    menuItem.addActionListener(new RecargarComboActionListener());
	    popup.add(menuItem);
	    
	    //MouseListener popupListener = new PopupListener();
	    this.addMouseListener(new PopUpListener2());
	    
		//this.addMouseListener(new RecargarComboMouseListener());
	}
	
	public ABMComboBox(){
		super();	
	}
	
	/**
	 * Preselecciona un elemento del combo
	 * 
	 * @param foreign
	 */
	public void setForeign(String foreign){
		if(this.getItemCount() > 0){
			if(!Util.isNaturalNumber(foreign)){
				this.setSelectedIndex(0);
			}else{
				for(int i = 0;i < m_codigoForeign.size();i++ ){
					if(foreign.equals(((String)m_codigoForeign.get(i)))){
						this.setSelectedIndex(i + 1);
					}	
				}
			}
		}
	}
	
	public String searchForeign(){
		Object returnValue = null;
		
		if (this.getSelectedIndex() > 0){
			 Object o = m_codigoForeign.get(this.getSelectedIndex()-1);
			 if (o instanceof String)
				 returnValue = o;
			 else if (o instanceof Long)
				 returnValue = ((Long)o).toString();
		}
		
		return (returnValue != null ? ((String)returnValue) : null);
	}

	public Vector getForeigns(){
		return this.m_codigoForeign;
	}
	
	public void removeForeign(String foreign){
		if(!StringUtils.isBlank(foreign)){					
			for(int i = 0;i < m_codigoForeign.size();i++ ){
				if(foreign.equals(((String)m_codigoForeign.get(i)))){
					this.removeItemAt(i + 1);
					m_codigoForeign.remove(i);
					break;
				}	
			}
		}
	}
	
	public CellEditor getCellEditor() {
		return cellEditor;
	}

	public void setCellEditor(CellEditor cellEditor) {
		this.cellEditor = cellEditor;
	}

	public TableCellRenderer getTableCellRenderer() {
		return tableCellRenderer;
	}

	public void setTableCellRenderer(TableCellRenderer tableCellRenderer) {
		this.tableCellRenderer = tableCellRenderer;
	}
	
	public boolean isEmpty(){
		return this.getItemCount() <= 1;
	}
	
	@Override
	public void setSelectedIndex(int idx) {
		super.setSelectedIndex(idx);
		/*
		if (cellEditor != null && cellEditor instanceof DefaultCellEditor){
			System.out.println("Refrescando");
			((DefaultCellEditor)cellEditor).setClickCountToStart())
			((DefaultCellEditor)cellEditor).stopCellEditing();
		}*/
		
		if (tableCellRenderer != null && tableCellRenderer instanceof DefaultTableCellRenderer){
			System.out.println("Refrescando");
			Object o = getSelectedItem();
			((DefaultTableCellRenderer)tableCellRenderer).setText((o==null)?"":o.toString());
		}
	}
	
	public void loadItems(){
		
	}
	
	public void recargar(){
		m_codigoForeign.removeAllElements();
		removeAllItems();			
        loadItems();
	}
	
	private class RecargarComboActionListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			
			recargar();
		}
	}
	
	class PopupListener extends MouseAdapter {
	    public void mousePressed(MouseEvent e) {
	        maybeShowPopup(e);
	    }

	    public void mouseReleased(MouseEvent e) {
	        maybeShowPopup(e);
	    }

	    private void maybeShowPopup(MouseEvent e) {
	        if (e.isPopupTrigger()) {
	            popup.show(e.getComponent(),
	                       e.getX(), e.getY());
	        }
	    }
	}
	
	class PopUpListener2 implements MouseListener{

		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			maybeShowPopup(e);
		}

		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			maybeShowPopup(e);
		}
		
		private void maybeShowPopup(MouseEvent e) {
	        if (e.isPopupTrigger()) {
	            popup.show(e.getComponent(),
	                       e.getX(), e.getY());
	        }
	    }
		
	}
}
