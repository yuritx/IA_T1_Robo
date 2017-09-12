package robozinho;

import java.util.ArrayList;

public class Nodo {

	public int x;
	public int y;
	public int heuristica;
	public int gScore;
	public int fScore;
	public Nodo pai;
	public ArrayList <Nodo> nodosHortogonais = new ArrayList<Nodo>();
	public ArrayList <Nodo> nodosTransversais = new ArrayList<Nodo>();
	public int status;
	
	public Nodo(int posX, int posY, int heuristic){

		int x = posX;
		int y = posY;
		int heuristica = heuristic;
		gScore = Integer.MAX_VALUE;
		fScore = 0;
		pai = null;
	}
	
}


