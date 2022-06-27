package comerciales2019.salas.servicios;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import comerciales2019.salas.DiaSalaPanel;

public class SalaServiciosCellRender extends DefaultTableCellRenderer{
	
	private String tipo="text";

	//se definen por defecto los tipos de datos a usar
	private Font normal = new Font("Berlin Sans FB", Font.PLAIN, 14);

	//etiqueta que almacenará el icono a mostrar
	private JLabel label = new JLabel();
	private JCheckBox check = new JCheckBox();
	
	//iconos disponibles para ser mostrados en la etiqueta dependiendo de la columna que lo contenga
	private ImageIcon iconoEliminar = new ImageIcon(SalaServiciosCellRender.class.getResource("/gui/inventario/imagenes/cross.png"));
	private ImageIcon iconoEditar = new ImageIcon(SalaServiciosCellRender.class.getResource("/gui/inventario/imagenes/pencil.png"));	

	public SalaServiciosCellRender(String tipo){
		this.tipo=tipo;
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused,
			int row, int column) {
		// TODO Auto-generated method stub
		Color colorFondo = null;
		Color colorFondoPorDefecto=new Color( 192, 192, 192);
		Color colorFondoSeleccion=new Color( 140, 140 , 140);

		/*
		 * Si la celda del evento es la seleccionada se asigna el fondo por defecto para la selección
		 */
		if (selected) {                
			this.setBackground(colorFondoPorDefecto );   
		}
		else
		{
			//Para las que no están seleccionadas se pinta el fondo de las celdas de blanco
			this.setBackground(Color.white);
		}

		/*
		 * Se definen los tipos de datos que contendrán las celdas basado en la instancia que
		 * se hace en la ventana de la tabla al momento de construirla
		 */
		if( tipo.equals("texto"))
		{
			//si es tipo texto define el color de fondo del texto y de la celda así como la alineación
			if (focused) {
				colorFondo=colorFondoSeleccion;
			}else{
				colorFondo= colorFondoPorDefecto;
			}
			this.setHorizontalAlignment( JLabel.LEFT );
			this.setText( (String) value );
			//this.setForeground( (selected)? new Color(255,255,255) :new Color(0,0,0) );   
			//this.setForeground( (selected)? new Color(255,255,255) :new Color(32,117,32) );
			this.setBackground( (selected)? colorFondo :Color.WHITE); 
			this.setFont(normal);   
			//this.setFont(bold);
			return this;
		}
		
		if( tipo.equals("checkbox"))
		{
			//si es tipo texto define el color de fondo del texto y de la celda así como la alineación
			if (focused) {
				colorFondo=colorFondoSeleccion;
			}else{
				colorFondo= colorFondoPorDefecto;
			}
			check.setHorizontalAlignment( JLabel.CENTER );
			check.setSelected( (Boolean) value );
			check.setBackground( (selected)? colorFondo :Color.WHITE); 
			return check;
		}

		//si el tipo es icono entonces valida cual icono asignar a la etiqueta.
		if( tipo.equals("icono"))
		{
			if( String.valueOf(value).equals("EDITAR") )
			{
				label.setIcon(iconoEditar);
			}
			else if( String.valueOf(value).equals("ELIMINAR") )
			{
				label.setIcon(iconoEliminar);
			}
			label.setHorizontalAlignment( JLabel.CENTER );
			label.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
			//            return boton;
			return label;
		}

		//definie si el tipo de dato el numerico para personalizarlo
		if( tipo.equals("numerico"))
		{           
			if (focused) {
				colorFondo=colorFondoSeleccion;
			}else{
				colorFondo=colorFondoPorDefecto;
			}
			// System.out.println(value);
			this.setHorizontalAlignment( JLabel.CENTER );
			this.setText( String.valueOf((int) value ));            
			this.setForeground( (selected)? new Color(255,255,255) :new Color(32,117,32) );    
			this.setBackground( (selected)? colorFondo :Color.WHITE);
			// this.setBackground( (selected)? colorFondo :Color.MAGENTA);
			this.setFont(normal);            
			return this;   
		}
		if( tipo.equals("monetizado"))
		{           
			if (focused) {
				colorFondo=colorFondoSeleccion;
			}else{
				colorFondo=colorFondoPorDefecto;
			}
			// System.out.println(value);
			this.setHorizontalAlignment( JLabel.CENTER );
			this.setText((String)value);   
			this.setForeground( (selected)? new Color(255,255,255) :new Color(32,117,32) );    
			this.setBackground( (selected)? colorFondo :Color.WHITE);
			// this.setBackground( (selected)? colorFondo :Color.MAGENTA);
			this.setFont(normal);            
			return this;   
		}

		return this; 
	}

}
