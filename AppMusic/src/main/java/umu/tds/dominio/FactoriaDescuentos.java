package umu.tds.dominio;

import java.lang.reflect.InvocationTargetException;

public class FactoriaDescuentos {
	private static FactoriaDescuentos instancia = null;
	
	private FactoriaDescuentos() {}
	
	public static FactoriaDescuentos getInstancia() {
		if(instancia == null) {
			instancia = new FactoriaDescuentos();
		}
		return instancia;
	}
	
	public Descuento crearDescuento(String claseDescuento) {
		Class<?> descuento = null;
		if(!(claseDescuento == null)) {
			try {
				descuento = Class.forName(claseDescuento);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			if(descuento!=null && Descuento.class.isAssignableFrom(descuento))
				try {
					return (Descuento)descuento.getDeclaredConstructor().newInstance();
				} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
						| InvocationTargetException | NoSuchMethodException | SecurityException e) {
					e.printStackTrace();
				}
		}
		return null;
	}
}
