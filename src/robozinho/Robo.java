package robozinho;

import java.util.ArrayList;

public class Robo {

	private int capacidadeTotal;
	private int bateriaTotal;
	private int capacidadeAtual;
	private int bateriaAtual;
	private int xRobo;
	private int yRobo;
	private boolean sentido;
	public Robo(int capacidade, int bateria){ 
		capacidadeTotal = capacidade;
		bateriaTotal = bateria;
		sentido = true;
		
	}
	
	
	public void resetaCapacidade(Mapa map){
		int cout =0;
		int [] lixeiras = map.getLixeiras();
		
		ArrayList<String> resposta = new ArrayList<String>();
		while(cout < lixeiras.length){
		 
		if(resposta.size() == 0){ 
			resposta = aStar(xRobo, yRobo, lixeiras[cout], lixeiras[cout+1], map);
			}
		else{ 
			ArrayList <String> aux = aStar(xRobo, yRobo, lixeiras[cout], lixeiras[cout+1], map);
			if(aux.size() < resposta.size()) resposta = aux;
		}
		cout +=2;
		}
		
	}
	
	public void recarregar(Mapa map){
		
		int cout =0;
		int [] carregadores = map.getCarregadores();
		
		ArrayList<String> resposta = new ArrayList<String>();
		while(cout < carregadores.length){
		 
		if(resposta.size() == 0){ 
			resposta = aStar(xRobo, yRobo, carregadores[cout], carregadores[cout+1],map);
			}
		else{ 
			ArrayList <String> aux = aStar(xRobo, yRobo, carregadores[cout], carregadores[cout+1],map);
			if(aux.size() < resposta.size()) resposta = aux;
		}
		cout +=2;
		}
	}
	
	public void anda(Mapa map){
		int estado = 0;
		int teste = xRobo;
		
		while(estado != 6){
			
			if(bateriaAtual == bateriaTotal) recarregar(map);
			
			if(capacidadeAtual == capacidadeTotal) resetaCapacidade(map);
			
			if(sentido){
				estado= map.estadoPosicao(xRobo +1, yRobo);
			}
			else{
				estado = map.estadoPosicao(xRobo -1, yRobo);
			}
			
			if(estado == 1){
				if(sentido) xRobo++;
				else xRobo--;
				map.limpaEspaco(xRobo, yRobo);
				capacidadeAtual++;
			}
			
			if(estado == 2 || estado == 3 || estado == 4){
				passaObstaculo();
			}
		
			if(estado == 5){
				if(map.estadoPosicao(xRobo, yRobo +1)!=6){
					sentido = !sentido;
					yRobo++;
				}
				else estado = 6;
			}
		}
		
		
	}
	
	private void passaObstaculo() {
		// TODO Auto-generated method stub
		
	}


	public ArrayList<String> aStar(int xInicial, int yInicial, int xFinal, int yFinal, Mapa map){
		
		
		
		ArrayList closedSet = new ArrayList <Nodo>();
		ArrayList openSet = new ArrayList <Nodo>();
		Nodo [][] nodos = new Nodo[map.tamanhoX()][map.tamanhoY()];
	
		for(int xa=0; xa< nodos.length ; xa++){
			for(int ya=0; ya< nodos[0].length ; ya++){
				nodos[xa][ya].x = xa;
				nodos[xa] [ya].y= ya;
			}
		}
		Nodo inicial = nodos[xInicial][yInicial];
		Nodo goal = nodos[xFinal][yFinal];
		openSet.add(inicial);
		inicial.gScore = 0;
		
		//inicial.fscore = heuristica(inicial, final);

	 	 
	    while (openSet.size() >0){
	       Nodo current = new Nodo(0,0,0); //= menor fscore em openSet;
	        if (current == goal){
	             reconstruct_path(inicial, current);
	        }
	        openSet.remove(current);
	        closedSet.add(current);

	        //for each neighbor of current
	           // if neighbor in closedSet
	               //continue		// Ignore the neighbor which is already evaluated.

	         //  if neighbor not in openSet	// Discover a new node
	               // openSet.Add(neighbor)
	            
	            // The distance from start to a neighbor
	           // tentative_gScore := gScore[current] + dist_between(current, neighbor)
	            //if tentative_gScore >= gScore[neighbor]
	              //  continue		// This is not a better path.

	            // This path is the best until now. Record it!
	            //cameFrom[neighbor] := current
	            //gScore[neighbor] := tentative_gScore
	            //fScore[neighbor] := gScore[neighbor] + heuristic_cost_estimate(neighbor, goal)
	    }
	    return null;
	    }
	public void reconstruct_path(Nodo cameFrom, Nodo current){
	    Nodo total_path = current;
	    //while (current in cameFrom.Keys){
	      //  current := cameFrom[current]
	       // total_path.append(current)
	    //}
	   // return total_path
	}
		
		
		
		
			
	
}