package tamagochi;

import java.util.InputMismatchException;
import java.util.Scanner;

public class App {

	// CREAMOS COMO STATIC EL ARRAY DE TAMAGOCHIS Y AL SCANNER PARA NO ESTAR
	// LLAMANDOLO EN CADA UNO DE LOS METODOS

	private static Tamagochi tamagochis[] = new Tamagochi[20];
	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {

		crearCincoTamagochis();
		tratarMenu();

	}

	// INICIAMOS 5 TAMACOCHIS
	public static void crearCincoTamagochis() {

		tamagochis[0] = new Tamagochi("Tobias", 10, 20, 30, 40);
		tamagochis[1] = new Tamagochi("Matilde", 20, 30, 30, 20);
		tamagochis[2] = new Tamagochi("Paulino", 60, 20, 20, 20);
		tamagochis[3] = new Tamagochi("Benjamin", 10, 20, 30, 40);
		tamagochis[4] = new Tamagochi("Goku", 10, 100, 100, 100);

	}

//eL MENU USANDO EL SYSOUT DE TRIPLE COMILLA QUE TE MOSTRE EN CLASE(POR PROBARLO) QUE SI ES SENSIBLE A LOS SALTOS DE LINEA
	public static void menu() {

		System.out.println("""
				_______________________
				1-Añadir tamagochi.

				2-Listar tamagochi.

				3-Cuidar tamagochi.

				4-Atender necesidades urgentes.

				5-Salir.
				_______________________
				""");

	}

	// METODO QUE INTERACTIA CON EL MENU
	public static void tratarMenu() {

		boolean comprobador;
		int id, opcion = 0;
		String nombre = "";

		boolean flag = true;
		while (flag) {
			menu();
			opcion = throwScannerInt(1, 5);

			switch (opcion) {
			case 1:
				aniadirTamagochi();
				break;
			case 2:
				listarTamagochi();
				break;
			case 3:
				cuidarTamagochi();
				break;
			case 4:
				atenderNecesidadesUrgentes();
				break;
			default:
				System.out.println("Adios.");
				flag = false;
				break;
			}

		}

	}

	// METODO PARA PEDIR LOS DATOS DE UN TAMAGOCHI, E IR ORIENTANDO AL USUARIO PARA
	// IR DETENERMINANDO LOS VALORES POR CONSOLA
	public static void aniadirTamagochi() {
		String nombre;
		int hambre, suenio, higiene, diversion;

		System.out.println("\nEscogiste añadir tamagochi");
		System.out.println("\nIntroduce un nombre para el Tamagochi");
		nombre = sc.nextLine();
		System.out.println("\nIntroduce su nivel de hambre (entre 1 y 100)");

		hambre = throwCrearTamagochi(1, 100);

		System.out.println("\nIntroduce su nivel de sueño (entre 1 y 100)");
		suenio = throwCrearTamagochi(1, 100);

		System.out.println("\nIntroduce su nivel de higiene (entre 1 y 100)");
		higiene = throwCrearTamagochi(1, 100);

		System.out.println("\nIntroduce su nivel de (entre 1 y 100)");
		diversion = throwCrearTamagochi(1, 100);

		System.out.println(agregarTamagochi(nombre, hambre, suenio, higiene, diversion)
				? "\nNo quedan huecos para crear nuevos tamagochis"
				: nombre + " ha sido creado correctamente");

	}

	// EN ESTA PARTE ES DONDE LOS VALORES INTRODUCIDOS EN EL METODO ANTERIOR SE USAN
	// PARA CREAR UN OBJETO EN EL PRIMER HUECO NO NULL DEL ARRAY
	public static boolean agregarTamagochi(String nombre, int hambre, int suenio, int higiene,
			int diversion) {

		boolean flag = true;
		for (int i = 0; (i < tamagochis.length) && flag; i++) {
			if (tamagochis[i] == null) {

				tamagochis[i] = new Tamagochi(nombre, hambre, suenio, higiene, diversion);
				flag = false;

			}

		}

		return flag;
	}

	// METODO PARA MOSTRAR A LOS TAMAGOCHIS CON BUEN FORMATO, Y SI UNO HA FALLECIDO,
	// TE MUESTRA SU TUMBA EN LUGAR DE SUS ATRUBUTOS(YA QUE SERIAN CERO)
	public static String listarTamagochi() {
		String listaTamagochis = "";

		for (int i = 0; i < tamagochis.length; i++) {
			if (tamagochis[i] != null) {
				// System.out.printf(tamagochis[i].toString());

				if (tamagochis[i].isVivo()) {
					System.out.printf(
							"ID = %d\nNombre=\033[33m%10s\033[0m\nHambre=\033[32m%10d\033[0m\nSuenio=\033[32m%10d\033[0m\nHigiene=\033[32m%9d\033[0m\nDiversion=\033[32m%7d\033[0m\n\n",
							tamagochis[i].getIdentificador(), tamagochis[i].getNombre(), tamagochis[i].getHambre(),
							tamagochis[i].getSuenio(), tamagochis[i].getHigiene(), tamagochis[i].getDiversion());
				} else {
					funeral(i);
				}
			}
		}

		return listaTamagochis;
	}

	// METODO QUE DETECTA EN QUE VALOR TIENE UN TAMAGOCHI MENOS DE 20 Y TE PREGUNTA
	// SI HA DE CURARLO
	public static void atenderNecesidadesUrgentes() {

		for (int i = 0; i < tamagochis.length; i++) {
			if (tamagochis[i] != null) {
				if (tamagochis[i].getDiversion() < 20 || tamagochis[i].getHambre() < 20
						|| tamagochis[i].getHigiene() < 20 || tamagochis[i].getSuenio() < 20) {

					System.out.println("(" + tamagochis[i].getIdentificador() + ")\033[33m" + tamagochis[i].getNombre()
							+ " está moribundo.\033[0m");

				}
			}

		}
		System.out.println();

		System.out.println("_______________________\n¿Cual quieres curar?");
		System.out.flush();
		/*
		 * HE BUSCADO POR QUE SE ME DESORDENACAN LOS STRING AL MOSTRAR POR PANTALLA, Y
		 * ME SALIA QUE EL BUFFER A VECES NO IMPRIME INMEDIATAMENTE, Y ESTO FUERZA A QUE
		 * IMPRIMA ANTES DE PEDIR LA ENTRADA POR SCANNER . CON ESTO SE COLUCIONA. AL SER
		 * DE SYSTEM.OUT IMAGINO QUE PUEDE SER USADO, COMO DIJISTE EL OTRO DIA EN CLASE
		 */

		// AQUI COMBINO EL METODO QUE CHEQUEA LOS SCANNER NUMERICOS PARA EVITAR
		// EXCEPCIONES, CON EL DE DESCUBRIR A PARTIR DE QUE NUMERO HAY NULL PARA QUE
		// ME DE EL VALOR MAXIMO A INTRODUCIR POR ESE SCANNER
		int id = throwScannerInt(0, checkTamagochiNull());

		if (tamagochis[id].getHambre() <= 20 && tamagochis[id].getHambre() > 0) {

			tamagochis[id].sumarHambre();

		} else if (tamagochis[id].getSuenio() <= 20 && tamagochis[id].getSuenio() > 0) {

			tamagochis[id].sumarSuenio();

		} else if (tamagochis[id].getHigiene() <= 20 && tamagochis[id].getHigiene() > 0) {

			tamagochis[id].sumarHigiene();

		} else if (tamagochis[id].getDiversion() <= 20 && tamagochis[id].getDiversion() > 0) {

			tamagochis[id].sumarDiversion();
		} else {
			System.out.printf(
					"\033[032m" + tamagochis[id].getNombre() + " no tiene necesidades urgentes que atender.\033[0m\n");

		}

		matarTamagochi(id);

	}

	// A ESTE METODO LE AGREGO UN VALOR IN QUE SEA EL LIMITE POR INICIO, OTRO QUE SE
	// EL LIMITE POR EL FINAL, Y DENTRO CONTROLO LOS ERRORES POSIBLES PARA STRING,
	// DECIMALES O NUMEROS FUERA DE RANGO
	public static int throwScannerInt(int valorMinimo, int valorMaximo) {
		boolean flag = true;
		int opcion = 0;

		while (flag) {

			try {

				opcion = sc.nextInt();
				sc.nextLine();
				if (opcion < valorMinimo || opcion > valorMaximo) {

					throw new IllegalArgumentException("Valor Introducido no Valido");
				}
				flag = false;
			} catch (IllegalArgumentException e) {

				System.err.println(e.getMessage());

			} catch (InputMismatchException e) {
				System.err.println("Introduce valores numericos entre " + valorMinimo + " y " + valorMaximo);
				sc.nextLine();
			}
		}
		return opcion;
	}

	// SIMILAR AL METODO DE ANTES, PERO ESTE CONTROLA LOS VALORES PARA INICIALIZAR A
	// 50 LOS ATRIBUTOS EN CASO DE QUE SEAN MAYORES QUE 100 O MENORES QUE 0
	public static int throwCrearTamagochi(int valorMinimo, int valorMaximo) {
		boolean flag = true;
		int opcion = 0;

		try {

			opcion = sc.nextInt();
			sc.nextLine();
			if (opcion < valorMinimo || opcion > valorMaximo) {
				opcion = 50;
				throw new IllegalArgumentException("Valor fuera de rango, iniciado a 50 por defecto.");

			}

		} catch (IllegalArgumentException e) {

			System.err.println(e.getMessage());

		} catch (InputMismatchException e) {
			System.err.println("Introduce valores numericos entre " + valorMinimo + " y " + valorMaximo);
			sc.nextLine();

		}
		return opcion;
	}

	// METODO CON MENU PARA SUBIR LOS VALORES AL TAMAGOCHI UTILIZANDO LOS METODOS
	// PROPIOS DEL LA CLASE TAMAGOCHI
	public static void cuidarTamagochi() {
		boolean flag = true;
		while (flag) {

			System.out.println("Introduce la id del tamagochi a tratar. Entre 0 y " + tamagochis.length);

			int id = throwScannerInt(0, checkTamagochiNull());

			flag = false;

			System.out.println(
					"_______________________\n\033[36m\n1-Alimentar.\n\n2-Acostar.\n\n3-Bañar.\n\n4-Jugar.\n\033[0m");
			int opcion = throwScannerInt(1, 4);

			switch (opcion) {
			case 1:

				tamagochis[id].sumarHambre();

				break;
			case 2:
				tamagochis[id].sumarSuenio();
				break;
			case 3:
				tamagochis[id].sumarHigiene();
				break;
			case 4:
				tamagochis[id].sumarDiversion();
				break;
			default:
				break;

			}
			ponerPletorico(id);
			matarTamagochi(id);
		}
	}

	// SI UN TAMAGOCHI SUBE UN ATRIBUTO A 100 SE PONE PLETORICO!!
	public static void ponerPletorico(int id) {

		if (tamagochis[id].getDiversion() >= 100 || tamagochis[id].getHambre() >= 100
				|| tamagochis[id].getHigiene() >= 100 || tamagochis[id].getSuenio() >= 100) {
			System.out.println("\033[32m" + (tamagochis[id].getNombre()).toUpperCase() + " ESTÁ PLETORICO!! \033[0m");
		}
	}

	// eSTE METODO HACE QUE LOS VALORES DE UN TAMAGOCHI SEAN LOS CORRESPONDIENTES,
	// TODO A CERO, Y SU BOOLEAN DE VIDA EN FALSE, EL CUAL ES LLAMADO DE FORMA
	// RECURRENTE PARA VER SI ESTA CON VIDA, COMO SI DE LOS LATIDOS DE SU CORAZON SE
	// TRATARTA
	public static void matarTamagochi(int id) {

		if (tamagochis[id].getDiversion() <= 0 || tamagochis[id].getHambre() <= 0 || tamagochis[id].getHigiene() <= 0
				|| tamagochis[id].getSuenio() <= 0) {
			tamagochis[id].setVivo(false);

			System.out.println("\033[31m" + tamagochis[id].getNombre() + " está muerto\033[0m");
			funeral(id);

		}

	}
//UNA HUMILDE TUMBA PARA NUESTRO QUERIDO AMIGO QUE MARCHO, AHCIENDO UN FORMATEO DIGNO QUE PERMITA QUE SU NOMRBE ENCAJE PERFECTAMENTE EN LA LAPIDA
	public static void funeral(int numero) {
		System.out.println();
		System.out.println(" 	  ______ ");
		System.out.println("       //       \\ ");
		System.out.println("      ||         | ");
		System.out.println("      || -R.I.P- |");
		System.out.println("      ||         |");
		System.out.printf("      ||.%-7s.|\n",
				((tamagochis[numero].getNombre().length() <= 7) ? (tamagochis[numero].getNombre().toUpperCase())
						: (tamagochis[numero].getNombre().toUpperCase()).substring(0, 7)));
		System.out.println("      ||         |");
		System.out.println("\033[32m,,|\\/,\033[0m||\033[32m-_,-,_,,-\033[0m|\033[32m|,/_\\\033[31m*\033[32m/\033[0m");
		System.out.println();
	}

	
	//ESTE METODO COMPRUEBA QUE UNA POSICION DE TAMAGOPCHI NO SEA NULL PARA EVITAR ERRORES
	public static int checkTamagochiNull() {

		int idMaxima = 0;
		boolean check = true;
		while (check) {

			if (tamagochis[idMaxima] != null) {

				idMaxima++;

			} else {
				check = false;
			}

		}
		return idMaxima-1;
	}
	
}
