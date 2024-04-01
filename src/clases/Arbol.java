package clases;

public class Arbol {
	private int grado;
	private Nodo raiz;
	
	public Arbol(int grado) {
		this.grado = grado;
		this.raiz = new Nodo(grado);
	}
	
	public void buscarNodoPorClave(int num) {
		Nodo temp = buscar(raiz, num);
		
		if(temp == null) {
			System.out.println("No se ha encontrado un nodo con el valor ingresado");
		}else {
			print(temp);
		}
	}
	
	private Nodo buscar(Nodo actual, int key) {
		int i = 0;
		
		while(i < actual.clave && key > actual.key[i]) {
			i++;
		}
		
		if(i < actual.clave && key == actual.key[i]) {
			return actual;
		}
		
		if(actual.hoja) {
			return null;
		}else {
			return buscar(actual.hijo[i], key);
		}
	}
	
	public void insertar(int key) {
		Nodo r = raiz;
		
		if (r.clave == ((2 * grado) - 1)) {
			Nodo s = new Nodo(grado);
			raiz = s;
			s.hoja = false;
			s.clave = 0;
			s.hijo[0] = r;
			split(s, 0, r);
			nonFullInsert(s, key);
		}else {
			nonFullInsert(r, key);
		}
	}
	
	public void split(Nodo x, int i, Nodo y) {
		Nodo z = new Nodo(grado);
		z.hoja = y.hoja;
		z.clave = (grado - 1);
		
		for (int j = 0; j < (grado - 1); j++) {
			z.key[j] = y.key[(j + grado)];
		}
		
		if (!y.hoja) {
			for(int k = 0; k < grado; k++) {
				z.hijo[k] = y.hijo[(k + grado)];
			}
		}
		
		y.clave = (grado - 1);
		
		for(int j = x.clave; j > i; j--) {
			x.hijo[(i + 1)] = x.hijo[j];
		}
		
		x.hijo[(i + 1)] = z;
		
		for(int j = x.clave; j > i; j--) {
			x.key[(j + 1)] = x.key[j];
		}
		
		x.key[i] = y.key[(grado - 1)];
		x.clave++;
	}
	
	private void nonFullInsert(Nodo x, int key) {
		if(x.hoja) {
			int i = x.clave;
			
			while(i >= 1 && key < x.key[i - 1]) {
				x.key[i] = x.key[i - 1];
				i--;
			}
			
			x.key[i] = key;
			x.clave++;
		}else {
			int j = 0;
			
			while(j < x.clave && key > x.key[j]) {
				j++;
			}
			
			if(x.hijo[j].clave == (2 * grado - 1)) {
				split(x, j, x.hijo[j]);
				
				if(key > x.key[j]) {
					j++;
				}
			}
			
			nonFullInsert(x.hijo[j], key);
		}
	}
	
	public void muestraArbol() {
		print(raiz);
	}
	
	private void print(Nodo n) {
		n.imprimir();
		
		if(!n.hoja) {
			for(int j = 0; j <= n.clave; j++) {
				if(n.hijo[j] != null) {
					System.out.println();
					print(n.hijo[j]);
				}
			}
		}
	}
	
	public void eliminar(int key) {
	  Nodo actual = buscar(raiz, key);

	  if (actual == null) {
	    System.out.println("No se encontró la clave " + key + " en el árbol");
	    return;
	  }

	  if (actual.hoja) {
	    eliminarDeHoja(actual, key);
	  } else {
	    eliminarDeNodoInterno(actual, key);
	  }
	}

	private void eliminarDeHoja(Nodo actual, int key) {
	  int i = 0;

	  while (i < actual.clave && key > actual.key[i]) {
	    i++;
	  }

	  if (key == actual.key[i]) {
	    for (int j = i; j < actual.clave - 1; j++) {
	      actual.key[j] = actual.key[j + 1];
	    }
	    actual.clave--;
	  }
	}

	private void eliminarDeNodoInterno(Nodo actual, int key) {
	  int i = 0;

	  while (i < actual.clave && key > actual.key[i]) {
	    i++;
	  }

	  if (actual.hijo[i].clave >= grado) {
	    redistribuirDesdeDerecha(actual, i);
	  } else if (actual.hijo[i + 1].clave >= grado) {
	    redistribuirDesdeIzquierda(actual, i + 1);
	  } else {
	    fusionarYEliminar(actual, i);
	  }

	  if (actual.clave == 0 && actual != raiz) {
	    eliminarDePadre(actual);
	  }
	}

	private void redistribuirDesdeDerecha(Nodo actual, int i) {
	  Nodo hijoIzquierdo = actual.hijo[i];
	  Nodo hermanoDerecho = actual.hijo[i + 1];
	  
	  hijoIzquierdo.key[hijoIzquierdo.clave] = actual.key[i];
	  hijoIzquierdo.clave++;

	  for (int j = 0; j < hermanoDerecho.clave - 1; j++) {
	    hijoIzquierdo.key[hijoIzquierdo.clave + j] = hermanoDerecho.key[j];
	  }

	  for (int j = 0; j < hermanoDerecho.clave; j++) {
	    hijoIzquierdo.hijo[hijoIzquierdo.clave + j] = hermanoDerecho.hijo[j];
	  }

	  hijoIzquierdo.clave++;
	  actual.key[i] = hermanoDerecho.key[0];

	  for (int j = 0; j < hermanoDerecho.clave - 1; j++) {
	    hermanoDerecho.key[j] = hermanoDerecho.key[j + 1];
	  }

	  hermanoDerecho.clave--;
	}

	private void redistribuirDesdeIzquierda(Nodo actual, int i) {
	  Nodo hijoDerecho = actual.hijo[i];
	  Nodo hermanoIzquierdo = actual.hijo[i - 1];

	  actual.key[i - 1] = hijoDerecho.key[hijoDerecho.clave - 1];

	  hijoDerecho.key[hijoDerecho.clave] = hijoDerecho.key[hijoDerecho.clave - 1];
	  hijoDerecho.clave++;

	  hijoDerecho.hijo[hijoDerecho.clave] = hijoDerecho.hijo[hijoDerecho.clave];
	  hijoDerecho.clave++;

	  hijoDerecho.clave--;

	  actual.hijo[i] = hijoDerecho;
	}

	private void fusionarYEliminar(Nodo actual, int i) {
	  Nodo hijoIzquierdo = actual.hijo[i];
	  Nodo hermanoDerecho = actual.hijo[i + 1];

	  for (int j = 0; j < hermanoDerecho.clave; j++) {
	    hijoIzquierdo.key[hijoIzquierdo.clave + j] = hermanoDerecho.key[j];
	  }

	  for (int j = 0; j < hermanoDerecho.clave + 1; j++) {
	    hijoIzquierdo.hijo[hijoIzquierdo.clave + j] = hermanoDerecho.hijo[j];
	  }

	  hijoIzquierdo.clave += hermanoDerecho.clave + 1;

	  for (int j = i + 1; j < actual.clave; j++) {
	    actual.key[j - 1] = actual.key[j];
	  }

	  for (int j = i + 2; j <= actual.clave; j++) {
	    actual.hijo[j - 1] = actual.hijo[j];
	  }

	  actual.clave--;

	  if (actual.clave == 0 && actual != raiz) {
	    eliminarDePadre(actual);
	  }
	}

	private void eliminarDePadre(Nodo actual) {
	  Nodo padre = actual;
	  int i = 0;

	  while (i < padre.clave && padre.hijo[i] != actual) {
	    i++;
	  }

	  for (int j = i; j < padre.clave - 1; j++) {
	    padre.key[j] = padre.key[j + 1];
	  }

	  for (int j = i + 1; j <= padre.clave; j++) {
	    padre.hijo[j - 1] = padre.hijo[j];
	  }

	  padre.clave--;

	  if (padre.clave == 0 && padre != raiz) {
	    eliminarDePadre(padre);
	  }
	}
		    

}
