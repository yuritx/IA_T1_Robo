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
		Nodo [] lixeiras = map.getLixeiras();
		
		ArrayList<Nodo> resposta = new ArrayList<Nodo>();
		while(cout < lixeiras.length){
		 
		if(resposta.size() == 0){ 
			resposta = aStar( lixeiras[cout], map);
			}
		else{ 
			ArrayList <Nodo> aux = aStar( lixeiras[cout], map);
			if(aux.size() < resposta.size()) resposta = aux;
		}
		cout +=2;
		}
		
	}
	
	public void recarregar(Mapa map){
		
		int cout =0;
		Nodo [] carregadores = map.getCarregadores();
		
		ArrayList<Nodo> resposta = new ArrayList<Nodo>();
		while(cout < carregadores.length){
		 
		if(resposta.size() == 0){ 
			resposta = aStar( carregadores[cout],map);
			}
		else{ 
			ArrayList <Nodo> aux = aStar(carregadores[cout],map);
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


	public ArrayList<Nodo> aStar( Nodo rep, Mapa map){
		
		
		
		ArrayList closedSet = new ArrayList <Nodo>();
		ArrayList openSet = new ArrayList <Nodo>();
		Nodo [][] nodos = map.getMapa();
	
		for(int xa=0; xa< nodos.length ; xa++){
			for(int ya=0; ya< nodos[0].length ; ya++){
				nodos[xa][ya].heuristica = Math.abs((xa-rep.x)*10) + Math.abs((ya-rep.y)*10);
						
			}
		}
		
		for(int xa=0; xa< nodos.length ; xa++){
			for(int ya=0; ya< nodos[0].length ; ya++){
				nodos[xa][ya].nodosHortogonais.add(nodos[xa-1][ya]);
				nodos[xa][ya].nodosHortogonais.add(nodos[xa+1][ya]);
				nodos[xa][ya].nodosHortogonais.add(nodos[xa][ya-1]);
				nodos[xa][ya].nodosHortogonais.add(nodos[xa][ya+1]);
				
				nodos[xa][ya].nodosTransversais.add(nodos[xa-1][ya-1]);
				nodos[xa][ya].nodosTransversais.add(nodos[xa-1][ya+1]);
				nodos[xa][ya].nodosTransversais.add(nodos[xa+1][ya+1]);
				nodos[xa][ya].nodosTransversais.add(nodos[xa+1][ya-1]);
			}
		}
		
		
		Nodo inicial = nodos[xRobo][yRobo];
		Nodo goal = nodos[rep.x][rep.y];
		openSet.add(inicial);
		
		inicial.gScore = 0;
		
		//inicial.fscore = heuristica(inicial, final);

	 	 
	    while (openSet.size() >0){
	       Nodo current = melhorFscore(openSet); //= menor fscore em openSet;
	        if (current == goal){
	           return caminho(inicial, current);
	        }
	        openSet.remove(current);
	        closedSet.add(current);
	        
	        for(Nodo n: current.nodosTransversais){
	        if(!(closedSet.contains(n))){
	        	if(n.gScore==-1){
	        		n.gScore= current.gScore + 14;
	        		n.pai= current;
	        		n.fScore = n.gScore + n.heuristica;
	        	}
	        		else{
	        		if(n.gScore>=  current.gScore+14){
	        			n.gScore =  current.gScore+14;
	        			n.pai= current;
	        			n.fScore = n.gScore + n.heuristica;
	        		}
	        	}
	        	openSet.add(n);
	        }
	        }
	        for(Nodo n: current.nodosHortogonais){
	        	n = nodos[current.x+1][current.y+1];
	        	if(!(closedSet.contains(n))){
	        		if(n.gScore==-1){
	        			n.gScore= current.gScore + 10;
	        			n.pai= current;
	        			n.fScore = n.gScore + n.heuristica;
	        		}
	        			else{
	        				if(n.gScore>=  current.gScore+14){
	        					n.gScore =  current.gScore+14;
	        					n.pai= current;
	        					n.fScore = n.gScore + n.heuristica;
	        				}
	        			}
	        	openSet.add(n);
	        	}
	        }
	    }
	    return caminho(inicial, goal);
	    }
	
	private Nodo melhorFscore(ArrayList<Nodo> openSet) {
		Nodo aux = null;
		for(int i = 0; i<openSet.size();i++){
			if(i==0) aux = openSet.get(0);
			if(aux.fScore > openSet.get(i).fScore) aux = openSet.get(i);
		}
		return aux;
	}


	public ArrayList<Nodo> caminho(Nodo start, Nodo goal){
	    
	    //while (current in cameFrom.Keys){
	      //  current := cameFrom[current]
	       // total_path.append(current)
	    //}
	   // return total_path
		return null;
	}
		
		
		
		
			
	
}