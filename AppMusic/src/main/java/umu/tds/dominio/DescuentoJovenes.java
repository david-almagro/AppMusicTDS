package umu.tds.dominio;

public class DescuentoJovenes implements Descuento {
	private final double porcentaje = 0.5;

	public DescuentoJovenes() {

	}


	@Override
	public double aplicarDescuento(double precio) {
		return precio*(1-porcentaje);
	}

}
