import java.util.Scanner;

public class Pokemon {

	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		int op = 0;
		do {
			imprimirMenu();				// Imprimimos el menú de opciones
			op = elegirOpcionMenu();	// Seleccionamos la opción que queremos
			opcionElegida(op);			// Procesamos la opción seleccionada
		} while(op!=5);					// Si la opción seleccionada es la 5 (salir) se finaliza el programa
	}

	// Imprime el menú de opciones.
	public static void imprimirMenu() {
		System.out.println("Elige una opción:\n" + 
				"1- Mostrar debilidades.\n" + 
				"2- Mostrar eficacias.\n" + 
				"3- Mostrar toda la información relativa de un tipo atacante.\n" +
				"4- Mostrar efiacias de un tipo a otro.\n" + 
				"5- Salir.");
	}

	// Elegir opción: Lee por pantalla la opción seleccionada.
	public static int elegirOpcionMenu() {
		int op = sc.nextInt();
		return op;
	}

	// Switch opción elegida: Procesamos la opción que ha sido seleccionada.
	public static void opcionElegida(int op) {
		switch(op) {
		case 1:	// Mostrar debilidades.
			int num = preguntarNumeroTipos();
			if(num==1) {
				System.out.println("Dime tu tipo: ");
				mostrarTipos();
				int tipo = elegirTipo();
				mostrarDebilidades(tipo);
			} else {
				System.out.println("Dime tu primer tipo: ");
				mostrarTipos();
				int tipoA = elegirTipo();
				int tipoB;
				boolean b = false;
				do {
					System.out.println("Dime tu segundo tipo: ");
					mostrarTipos();
					tipoB = elegirTipo();
					if(tipoB==tipoA) {
						System.out.println("Los tipos tienen que ser distintos.");
						b = true;
					}
				} while(b);
				mostrarDebilidades(tipoA, tipoB);				
			}
			break;

		case 2:	// Mostrar eficacias.
			System.out.println("Dime tu tipo: ");
			mostrarTipos();
			int tipo2 = elegirTipo();
			mostrarEficacias(tipo2);
			break;

		case 3:	// Mostrar la información relativa de un tipo atacante.
			System.out.println("Dime tu tipo: ");
			mostrarTipos();
			int tipo3 = elegirTipo();
			mostrarInfoAtacante(tipo3);
			break;

		case 4:	// Mostrar la eficacia de un tipo a otro.
			System.out.println("Dime el tipo del atacante: ");
			mostrarTipos();
			int tipoAtacante = elegirTipo();
			int num2 = preguntarNumeroTipos();
			if(num2==1) {
				System.out.println("Dime tu tipo:");
				mostrarTipos();
				int tipoReceptor1 = elegirTipo();
				mostrarEficaciaDeUnTipoAOtro(tipoAtacante, tipoReceptor1);
			} else {
				System.out.println("Dime tu primer tipo: ");
				mostrarTipos();
				int tipoReceptorA = elegirTipo();
				int tipoReceptorB;
				boolean b = false;
				do {
					System.out.println("Dime tu segundo tipo: ");
					mostrarTipos();
					tipoReceptorB = elegirTipo();
					if(tipoReceptorB==tipoReceptorA) {
						System.out.println("Los tipos tienen que ser distintos.");
						b = true;
					}
				} while(b);
				mostrarEficaciaDeUnTipoAOtro(tipoAtacante, tipoReceptorA, tipoReceptorB);
			}
			break;
			
		case 5:	// Salir.
			System.out.println("Programa finalizado.");
			break;

		default:	// Si la opción seleccionada no existe se vuelve a preguntar por la opción.
			System.out.println("La opción escogida no existe. Prueba de nuevo...");
			break;
		}
	}
	// Preguntamos el numero de tipos a introducir.
	public static int preguntarNumeroTipos() {
		int num = 0;
		do {
			System.out.println("Dime cuantos tipos:");
			num = sc.nextInt();
		} while(num!=1 && num!=2);
		return num;
	}
	
	// Mostramos los tipos existentes.
	public static void mostrarTipos() {
		for(int i=0; i<Constantes.tiposPokemon.length; i++) {
			System.out.println(i + ". " + Constantes.tiposPokemon[i]);
		}
	}
	
	// Elegimos el tipo.
	public static int elegirTipo() {
		int tipo = 0;
		boolean b = false;
		do {
			tipo = sc.nextInt();
			if(tipo<0 || tipo>17) {
				System.out.println("El tipo tiene que estar entre 0 y 17.");
				b = true;
			}
		} while(b);
		return tipo;
	}

	// Mostrar debilidades del receptor del ataque.
	public static void mostrarDebilidades(int tipo) {
		for(int i=0; i<Constantes.tiposPokemon.length; i++) {
			if(Constantes.efectividadesPokemon[i][tipo]==2) {
				System.out.println("- " + Constantes.tiposPokemon[i]);
			}
		}
	}
	// Comprobamos si el daño es doble o normal.
	public static void mostrarDebilidades(int tipo1, int tipo2) {
		for(int i=0; i<Constantes.tiposPokemon.length; i++) {
			if(Constantes.efectividadesPokemon[i][tipo1]==2 
					&& Constantes.efectividadesPokemon[i][tipo2]==2) {	// Si el daño a ambos tipo es x2, el daño es doble (x4).
				System.out.println("- " + Constantes.tiposPokemon[i] + ": x4");
			} else if((Constantes.efectividadesPokemon[i][tipo1]==2 
					&& Constantes.efectividadesPokemon[i][tipo2]==1) || 
					(Constantes.efectividadesPokemon[i][tipo1]==1 
					&& Constantes.efectividadesPokemon[i][tipo2]==2)) {	// Si solo uno de los daños es x2, el daño se mantiene (x2).
				System.out.println("- " + Constantes.tiposPokemon[i] + ": x2");
			}
		}
	}

	// Mostrar eficacias del pokemon atacante: Muestra una lista de los prokemons contra los que es efectivo.
	public static void mostrarEficacias(int tipo) {
		for(int i=0; i<Constantes.tiposPokemon.length; i++) {
			if(Constantes.efectividadesPokemon[tipo][i]==2) {
				System.out.println("- " + Constantes.tiposPokemon[i]);
			}
		}
	}

	// Mostrar toda la información relativa de un tipo atacante
	public static void mostrarInfoAtacante(int tipo) {
		for(int i=0; i<Constantes.tiposPokemon.length; i++) {
			String str = danioPorTipo(tipo, i);
			System.out.println(str);
		}
	}
	// Muestra los tipos de pokémon y el efecto que tiene contra estos el tipo de pokémon que hayamos seleccionado.
	public static String danioPorTipo(int tipo, int i) {
		if(Constantes.efectividadesPokemon[tipo][i]==Constantes.EFICACIA_NA) {
			return Constantes.tiposPokemon[i] + ": NO AFECTA";
		} else if(Constantes.efectividadesPokemon[tipo][i]==Constantes.EFICACIA_NME) {
			return Constantes.tiposPokemon[i] + ": NO EFICAZ";
		} else if(Constantes.efectividadesPokemon[tipo][i]==Constantes.EFICACIA_NEUTRO) {
			return Constantes.tiposPokemon[i] + ": NEUTRO";
		} else {
			return Constantes.tiposPokemon[i] + ": EFICAZ";
		}
	}

	// Mostrar eficacia de un tipo a otro: Muestra la eficacia del tipo atacante en el tipo receptor.
	public static void mostrarEficaciaDeUnTipoAOtro(int atacante, int receptor) {
		if(Constantes.efectividadesPokemon[atacante][receptor]==0) {
			System.out.println("NO AFECTA");
		} else if(Constantes.efectividadesPokemon[atacante][receptor]==0.5) {
			System.out.println("NO EFICAZ");
		} else if(Constantes.efectividadesPokemon[atacante][receptor]==1) {
			System.out.println("NEUTRO");
		} else {
			System.out.println("EFICAZ");
		}
	}
	// Muestra la eficacia del tipo atacante a los tipos del pokémon receptor.
	public static void mostrarEficaciaDeUnTipoAOtro(int atacante, int receptorA, int receptorB) {
		double ef = Constantes.efectividadesPokemon[atacante][receptorA] * Constantes.efectividadesPokemon[atacante][receptorB];
		if(ef==Constantes.EFICACIA_DE) {
			System.out.println("DOBLEMENTE EFICAZ");
		} else if(ef==Constantes.EFICACIA_EME) {
			System.out.println("EFICAZ");
		} else if(ef==Constantes.EFICACIA_NEUTRO) {
			System.out.println("NEUTRO");
		} else if(ef==Constantes.EFICACIA_NME) {
			System.out.println("NO ES EFICAZ");
		} else if(ef==Constantes.EFICACIA_DNE) {
			System.out.println("DOBLEMENTE NO ES EFICAZ");
		} else {
			System.out.println("NO AFECTA");
		}
	}

}
