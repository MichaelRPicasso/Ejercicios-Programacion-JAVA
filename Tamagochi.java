package tamagochi;

public class Tamagochi {
	private String nombre;
	private int hambre;
	private int suenio;
	private int higiene;
	private int diversion;
	private boolean vivo;
	private int identificador;
	public static int contador = 0;

	// CONSTRUCTOR CON TODOS LOS VALORES
	public Tamagochi(String nombre, int hambre, int suenio, int higiene, int diversion) {
		this.nombre = nombre;
		this.hambre = comprobadorInicializacion(hambre);
		this.suenio = comprobadorInicializacion(suenio);
		this.higiene = comprobadorInicializacion(higiene);
		this.diversion = comprobadorInicializacion(diversion);
		this.vivo = true;
		identificador = contador;
		contador++;
	}

//CONSTRUCTOR VACIO
	public Tamagochi() {

		this.hambre = 50;
		this.suenio = 50;
		this.higiene = 50;
		this.diversion = 50;
		this.vivo = true;
		identificador = contador;
		this.nombre = "tamagochi" + identificador;
		contador++;

	}

//GETTER Y SETTER CORRESPONDIENTES
	public int getIdentificador() {
		return identificador;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombreee) {
		this.nombre = nombreee;
	}

	public int getHambre() {
		return hambre;
	}

	public void setHambre(int hambre) {
		this.hambre = hambre;
	}

	public int getSuenio() {
		return suenio;
	}

	public void setSuenio(int suenio) {
		this.suenio = suenio;
	}

	public int getHigiene() {
		return higiene;
	}

	public void setHigiene(int higiene) {
		this.higiene = higiene;
	}

	public boolean isVivo() {
		return vivo;
	}

	public void setVivo(boolean vivo) {
		this.vivo = vivo;
	}

	public int getDiversion() {
		return diversion;
	}

	public void setDiversion(int diversion) {
		this.diversion = diversion;
	}

	public void setIdentificador(int identificador) {
		this.identificador = identificador;
	}

//TOSTRING 
	@Override
	public String toString() {
		return "ID = " + identificador + "\nNombre= " + nombre + "\nHambre= " + hambre + "\nSuenio= " + suenio
				+ "\nHigiene= " + higiene + "\nDiversion= " + diversion + "\n";
	}

//AL CREAR EL TAMAGOCHI POR EL CONSTRUCTOR, COMPRUEBA QUE NO SE ESTAN SALIENDO LOS VALORES DE RANGO,M EN TAL CASO SE PONEN A 50
	public int comprobadorInicializacion(int valor) {

		if (valor > 100 || valor < 0) {
			valor = 50;
		}

		return valor;
	}

	// COMO EL METIDO DE INTERACTUARTAMAGOCHI BAJA TODO EN 10 ESTO LE SUMA 30 PARA
	// QUE QUEDE LA CUENTA EN +20. LOS 3 METODOS SIGUIENTES MANTIENEN MA MISMA
	// ESTRUCTURA PERO CON DISTINTOS VALORES
	public void sumarHambre() {

		if (vivo) {
			hambre += 30;

			interactuarTamagochi();
		}
		if (hambre > 100) {
			hambre = 100;

		}

	}

//IGUAL QUE EL ANTERIOR PERO CON SUEÑO
	public void sumarSuenio() {

		if (vivo) {
			suenio += 30;

			interactuarTamagochi();
		}
		if (suenio > 100) {
			suenio = 100;

		}

	}

//IGUAL QUE EL ANTERIOR PERO CON HIGIENE
	public void sumarHigiene() {
		boolean pletorico = false;
		if (vivo) {
			higiene += 30;

			interactuarTamagochi();
		}
		if (higiene > 100) {
			higiene = 100;

		}

	}

//IGUAL QUE EL ANTERIOR PERO CON DIVERSION
	public void sumarDiversion() {
		boolean pletorico = false;
		if (vivo) {

			diversion += 30;

			interactuarTamagochi();
		}
		if (diversion > 100) {
			diversion = 100;

		}

	}

	// METODO PARA SER LLAMADO CUANDO LOS TAMAGOCHIS SON CUIDADOS

	public boolean interactuarTamagochi() {

		if (vivo) {

			hambre -= 10;
			suenio -= 10;
			higiene -= 10;
			diversion -= 10;

			if (hambre <= 0 || suenio <= 0 || higiene <= 0 || diversion <= 0) {

				hambre = 0;
				suenio = 0;
				higiene = 0;
				diversion = 0;

				vivo = false;

			}
		}
		return vivo;
	}

}

