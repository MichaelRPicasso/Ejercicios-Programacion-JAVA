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
	public static void sumarHambre(Tamagochi[] tamagochi, int id) {

		if (tamagochi[id].vivo) {
			tamagochi[id].setHambre(tamagochi[id].getHambre() + 30);

			interactuarTamagochi(tamagochi[id]);
		}
		if (tamagochi[id].getHambre() >= 100) {
			tamagochi[id].setHambre(100);

		}

	}

//IGUAL QUE EL ANTERIOR PERO CON SUEÑO
	public static void sumarSuenio(Tamagochi tamagochi[], int id) {

		if (tamagochi[id].vivo) {
			tamagochi[id].setSuenio(tamagochi[id].getSuenio() + 30);

			interactuarTamagochi(tamagochi[id]);
		}
		if (tamagochi[id].getSuenio() >= 100) {
			tamagochi[id].setSuenio(100);

		}

	}

//IGUAL QUE EL ANTERIOR PERO CON HIGIENE
	public static void sumarHigiene(Tamagochi tamagochi[], int id) {
		boolean pletorico = false;
		if (tamagochi[id].vivo) {
			tamagochi[id].setHigiene(tamagochi[id].getHigiene() + 30);

			interactuarTamagochi(tamagochi[id]);
		}
		if (tamagochi[id].getHigiene() >= 100) {
			tamagochi[id].setHigiene(100);

		}

	}

//IGUAL QUE EL ANTERIOR PERO CON DIVERSION
	public static void sumarDiversion(Tamagochi tamagochi[], int id) {
		boolean pletorico = false;
		if (tamagochi[id].vivo) {

			tamagochi[id].setDiversion(tamagochi[id].getDiversion() + 0);

			interactuarTamagochi(tamagochi[id]);
		}
		if (tamagochi[id].getDiversion() >= 100) {
			tamagochi[id].setDiversion(100);

		}

	}

	// METODO PARA SER LLAMADO CUANDO LOS TAMAGOCHIS SON CUIDADOS

	public static boolean interactuarTamagochi(Tamagochi tamagochi) {

		if (tamagochi.vivo) {

			tamagochi.setHambre(tamagochi.hambre - 10);
			tamagochi.setSuenio(tamagochi.suenio - 10);
			tamagochi.setHigiene(tamagochi.higiene - 10);
			tamagochi.setDiversion(tamagochi.diversion - 10);

			if (tamagochi.hambre <= 0 || tamagochi.suenio <= 0 || tamagochi.higiene <= 0 || tamagochi.diversion <= 0) {

				tamagochi.setHambre(0);
				tamagochi.setSuenio(0);
				tamagochi.setHigiene(0);
				tamagochi.setDiversion(0);

				tamagochi.setVivo(false);

			}
		}
		return tamagochi.vivo;
	}

//METODO PARA CREAR FACILMENTE UN TAMAGOCHI AL USARLO DESDE EL MAIN	

	public static boolean agregarTamagochi(Tamagochi[] tamagochi, String nombre, int hambre, int suenio, int higiene,
			int diversion) {

		boolean flag = true;
		for (int i = 0; (i < tamagochi.length) && flag; i++) {
			if (tamagochi[i] == null) {

				tamagochi[i] = new Tamagochi(nombre, hambre, suenio, higiene, diversion);
				flag = false;

			}

		}

		return flag;
	}

}
