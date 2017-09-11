package robozinho;

public class Nodo {

	public int x;
	public int y;
	public int heuristica;
	public int gScore;
	public int fscore;
	public Nodo next;
	
	public Nodo(int posX, int posY, int heuristic){

		int x = posX;
		int y = posY;
		int heuristica = heuristic;
		fscore = -1;
		gScore = -1;
		next = null;
	}
}


