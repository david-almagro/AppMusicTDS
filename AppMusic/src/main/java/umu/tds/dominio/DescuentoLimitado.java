package umu.tds.dominio;


public class DescuentoLimitado implements Descuento {
	private final double porcentaje = 0.2;
	
	public DescuentoLimitado(){

	}


	@Override
	public double aplicarDescuento(double precio) {
		return precio*(1-porcentaje);
	}

}
