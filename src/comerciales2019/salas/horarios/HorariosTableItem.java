package comerciales2019.salas.horarios;

public class HorariosTableItem {
	private String fecha;
	private Hora horaDesde;
	private Hora horaHasta;
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public Hora getHoraDesde() {
		return horaDesde;
	}
	public void setHoraDesde(Hora horaDesde) {
		this.horaDesde = horaDesde;
	}
	public Hora getHoraHasta() {
		return horaHasta;
	}
	public void setHoraHasta(Hora horaHasta) {
		this.horaHasta = horaHasta;
	}
	public HorariosTableItem(String fecha, Hora horaDesde, Hora horaHasta) {
		super();
		this.fecha = fecha;
		this.horaDesde = horaDesde;
		this.horaHasta = horaHasta;
	}
	
}
