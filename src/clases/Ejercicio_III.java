package clases;

import java.util.Scanner;

public class Ejercicio_III {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int opcion;
		
		System.out.print("Ingrese el grado: ");
	    int grado = scanner.nextInt();	
	    Arbol arbol = new Arbol(grado);
	    
	    while (true) {
            mostrarMenu();
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese un número a insertar: ");
                    int datoInsertar = scanner.nextInt();
                    arbol.insertar(datoInsertar);
                    arbol.muestraArbol();
                    break;
                case 2:
                    System.out.print("Ingrese un número a eliminar: ");
                    int datoEliminar = scanner.nextInt();
                    arbol.eliminar(datoEliminar);
                    arbol.muestraArbol();
                    break;
                case 3:
                    System.out.print("Ingrese un número a buscar: ");
                    int datoBuscar = scanner.nextInt();
                    arbol.buscarNodoPorClave(datoBuscar);
                    break;
                case 4:
                    System.out.println("¡Saliendo del programa!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opción no válida. Ingrese un número del 1 al 4.");
            }
        }
	}

	private static void mostrarMenu() {
        System.out.println("\n--- Menú Árbol B ---");
        System.out.println("1. Insertar un número");
        System.out.println("2. Eliminar un número");
        System.out.println("3. Buscar un número");
        System.out.println("4. Salir");
        System.out.print("Seleccione una opción: ");
    }
}
