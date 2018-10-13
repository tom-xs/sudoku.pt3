package br.com.poli.classes;

public class  CellIsFixedException extends Exception {

public CellIsFixedException() {
	
	super("objeto Cell é fixo e nao pode ser alterado");

}

public CellIsFixedException(String a) {
	super("objeto Cell é fixo e nao pode ser alterado");
	System.out.println(a);
	
}

}
