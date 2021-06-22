package umu.tds.dominio;

public interface Descuento {

	public boolean descuentoAplicable();
	
	public double aplicarDescuento(int precio);
}
