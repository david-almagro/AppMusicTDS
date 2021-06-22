package umu.tds.dominio;

import java.util.Date;

public class DescuentoLimitado implements Descuento {
	private final Date fechaLimite;
	private final double porcentaje = 0.2;
	
	public DescuentoLimitado() {
		fechaLimite = new Date("July 22, 2024 00:00:00");
		}

	@Override
	public boolean descuentoAplicable() {
		Date currentDate = new Date();
		return currentDate.before(fechaLimite);
	}

	@Override
	public double aplicarDescuento(int precio) {
		return precio*porcentaje;
	}

}
