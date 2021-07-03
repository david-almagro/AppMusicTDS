package umu.tds.dominio;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import umu.tds.dao.FactoriaDAO;

public class CancionTest {

	final String NOMBRE = "nombre";
	final String INTERPRETE = "interprete";
	final String ESTILO = "estilo";
	final String RUTAFICHERO = "rutafichero";

	Cancion cancionTest1 = new Cancion( NOMBRE, INTERPRETE, ESTILO, RUTAFICHERO, 0, 0);



	@Test(expected=IllegalArgumentException.class)
	public final void testCancionStringStringStringString1() {
		@SuppressWarnings("unused")
		Cancion cancion = new Cancion( null, INTERPRETE, ESTILO, RUTAFICHERO);
	}

	@Test(expected=IllegalArgumentException.class)
	public final void testCancionStringStringStringString2() {
		@SuppressWarnings("unused")
		Cancion cancion = new Cancion( NOMBRE, null, ESTILO, RUTAFICHERO);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public final void testCancionStringStringStringString3() {
		@SuppressWarnings("unused")
		Cancion cancion = new Cancion( NOMBRE, INTERPRETE, null, RUTAFICHERO);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public final void testCancionStringStringStringString4() {
		@SuppressWarnings("unused")
		Cancion cancion = new Cancion( NOMBRE, INTERPRETE, ESTILO, null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public final void testCancionStringStringStringString5() {
		@SuppressWarnings("unused")
		Cancion cancion = new Cancion( "", INTERPRETE, ESTILO, RUTAFICHERO);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public final void testCancionStringStringStringString6() {
		@SuppressWarnings("unused")
		Cancion cancion = new Cancion( NOMBRE, "", ESTILO, RUTAFICHERO);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public final void testCancionStringStringStringString7() {
		@SuppressWarnings("unused")
		Cancion cancion = new Cancion( NOMBRE, INTERPRETE, "", RUTAFICHERO);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public final void testCancionStringStringStringString8() {
		@SuppressWarnings("unused")
		Cancion cancion = new Cancion( NOMBRE, INTERPRETE, ESTILO, "");
	}

	@Test(expected=IllegalArgumentException.class)
	public final void testCancionStringStringStringString9() {
		@SuppressWarnings("unused")
		Cancion cancion = new Cancion( NOMBRE, INTERPRETE, ESTILO, RUTAFICHERO, -1, 0);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public final void testCancionStringStringStringString10() {
		@SuppressWarnings("unused")
		Cancion cancion = new Cancion( NOMBRE, INTERPRETE, ESTILO, RUTAFICHERO, 0, -1);
	}
	
	@Test
	public final void testGetNombre() {
		assertEquals(cancionTest1.getNombre(), NOMBRE);
	}

	@Test
	public final void testGetInterprete() {
		assertEquals(cancionTest1.getInterprete(), INTERPRETE);
	}

	@Test
	public final void testGetEstilo() {
		assertEquals(cancionTest1.getEstilo(), ESTILO);
	}

	@Test
	public final void testGetRutaFichero() {
		assertEquals(cancionTest1.getRutaFichero(), RUTAFICHERO);
	}
	
	@Test
	public final void testId() {
		assertFalse(cancionTest1.getId() < 0);
	}
	@Test
	public final void testNumRepro() {
		assertFalse(cancionTest1.getNumReproducciones() < 0);
	}

}
