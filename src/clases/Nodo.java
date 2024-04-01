package clases;

public class Nodo {
	int clave;
	boolean hoja;
	int key[];
	Nodo hijo[];
	
	public Nodo(int k) {
		clave = 0;
		hoja = true;
		key = new int[((2 * k) - 1)];
		hijo = new Nodo[(2 * k)];
	}

	public void imprimir() {
		System.out.println("[");
		for(int i = 0; i < clave; i++) {
			if (i < clave - 1) {
				System.out.println(key[i] + " | ");
			}else {
				System.out.println(key[i]);
			}
				
		}
		System.out.println("]");
	}
	
	public int buscar(int n) {
		for (int i = 0; i < clave; i++) {
			if(key[i] == n) {
				return i;
			}
		}
		return -1;
	}
}
