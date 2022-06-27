package comerciales2019.salas.horarios;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
 
import javax.swing.AbstractCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

public class HorariosCellEditor extends AbstractCellEditor implements TableCellEditor, ActionListener {

	private Hora hora;
	private List<Hora> listHorarios;

	public HorariosCellEditor(List<Hora> list) {
		this.listHorarios = list;
	}

	@Override
	public Object getCellEditorValue() {
		return this.hora;
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
		if (value instanceof Hora) {
			this.hora = (Hora) value;
		}

		JComboBox<Hora> comboHorarios = new JComboBox<Hora>();

		for (Hora aHora: listHorarios) {
			comboHorarios.addItem(aHora);
		}

		comboHorarios.setSelectedItem(hora);
		comboHorarios.addActionListener(this);

		if (isSelected) {
			comboHorarios.setBackground(table.getSelectionBackground());
		} else {
			comboHorarios.setBackground(table.getSelectionForeground());
		}

		return comboHorarios;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		JComboBox<Hora> comboHorarios = (JComboBox<Hora>) event.getSource();
		this.hora = (Hora) comboHorarios.getSelectedItem();
	}

}
