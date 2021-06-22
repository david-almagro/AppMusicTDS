package umu.tds.dominio;

public class DescuentoMayores implements Descuento {
	private final double porcentaje = 0.5;

	public DescuentoMayores() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean descuentoAplicable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double aplicarDescuento(int precio) {
		return precio*porcentaje;
	}

}
