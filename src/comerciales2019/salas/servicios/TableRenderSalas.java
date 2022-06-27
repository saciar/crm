package comerciales2019.salas.servicios;

import javax.swing.JTable;
import javax.swing.table.TableColumn;

public class TableRenderSalas {
	
	private JTable table;

	public TableRenderSalas(JTable table) {
		this.table = table;
		initialize();
	}


	private void initialize(){

		setUpCantidadColumn(table.getColumnModel().getColumn(SalaServiciosTableModel.COLUMNA_CANTIDAD));
		
		setUpCodigoColumn(table.getColumnModel().getColumn(SalaServiciosTableModel.COLUMNA_CODSERVICIO));
		
		setUpFamiliaColumn(table.getColumnModel().getColumn(SalaServiciosTableModel.COLUMNA_FAMILIA));
		
		setUpServicioColumn(table.getColumnModel().getColumn(SalaServiciosTableModel.COLUMNA_SERVICIO));
		
		setUpDiasColumn(table.getColumnModel().getColumn(SalaServiciosTableModel.COLUMNA_DIAS));
		
		setUpSubcontratadoColumn(table.getColumnModel().getColumn(SalaServiciosTableModel.COLUMNA_SUBCONTRATADO));
		
		setUpOpcionalColumn(table.getColumnModel().getColumn(SalaServiciosTableModel.COLUMNA_OPCIONAL));
		
		setUpDescuentoColumn(table.getColumnModel().getColumn(SalaServiciosTableModel.COLUMNA_DESCUENTO));

		setUpPrecioListaColumn(table.getColumnModel().getColumn(SalaServiciosTableModel.COLUMNA_LISTA));
		
		setUpTotalColumn(table.getColumnModel().getColumn(SalaServiciosTableModel.COLUMNA_TOTAL));
		
		setUpEditarListaColumn(table.getColumnModel().getColumn(SalaServiciosTableModel.COLUMNA_EDITAR));
		
		setUpEliminarColumn(table.getColumnModel().getColumn(SalaServiciosTableModel.COLUMNA_ELIMINAR));

	}
	
	public void setUpCantidadColumn(TableColumn cantidadColumn) {

		cantidadColumn.setPreferredWidth(20);
		cantidadColumn.setCellRenderer(new SalaServiciosCellRender("numerico"));
	}
	
	public void setUpCodigoColumn(TableColumn codigoColumn) {

		codigoColumn.setPreferredWidth(30);
		codigoColumn.setCellRenderer(new SalaServiciosCellRender("texto"));
	}
	
	public void setUpServicioColumn(TableColumn servColumn) {
		servColumn.setCellRenderer(new SalaServiciosCellRender("texto"));
	}
	
	public void setUpFamiliaColumn(TableColumn familiaColumn) {
		familiaColumn.setCellRenderer(new SalaServiciosCellRender("texto"));
	}
	
	public void setUpDiasColumn(TableColumn diasColumn) {

		diasColumn.setPreferredWidth(20);
		diasColumn.setCellRenderer(new SalaServiciosCellRender("numerico"));
	}
	
	public void setUpDescuentoColumn(TableColumn diasColumn) {

		diasColumn.setPreferredWidth(30);
		diasColumn.setCellRenderer(new SalaServiciosCellRender("numerico"));
	}
	
	public void setUpSubcontratadoColumn(TableColumn subcontratColumn) {

		subcontratColumn.setPreferredWidth(20);
		subcontratColumn.setCellRenderer(new SalaServiciosCellRender("checkbox"));
	}
	
	public void setUpOpcionalColumn(TableColumn subcontratColumn) {

		subcontratColumn.setPreferredWidth(20);
		subcontratColumn.setCellRenderer(new SalaServiciosCellRender("checkbox"));
	}

	public void setUpTotalColumn(TableColumn totalColumn) {

		totalColumn.setPreferredWidth(100);
		totalColumn.setCellRenderer(new SalaServiciosCellRender("monetizado"));
	}
	
	public void setUpPrecioListaColumn(TableColumn totalColumn) {

		totalColumn.setPreferredWidth(100);
		totalColumn.setCellRenderer(new SalaServiciosCellRender("monetizado"));
	}
	
	public void setUpEditarListaColumn(TableColumn totalColumn) {

		totalColumn.setPreferredWidth(20);
		totalColumn.setCellRenderer(new SalaServiciosCellRender("icono"));
	}
	
	public void setUpEliminarColumn(TableColumn totalColumn) {

		totalColumn.setPreferredWidth(20);
		totalColumn.setCellRenderer(new SalaServiciosCellRender("icono"));
	}

	public void refreshTable(){

		table.updateUI();
		//t.revalidate();
		initialize();
		
	}

}
