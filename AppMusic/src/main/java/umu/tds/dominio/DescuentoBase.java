package umu.tds.dominio;

public class DescuentoBase implements Descuento {

	private final double porcentaje = 0.1;
	
	public DescuentoBase(){

	}


	@Override
	public double aplicarDescuento(double precio) {
		return precio*(1-porcentaje);
	}

}
