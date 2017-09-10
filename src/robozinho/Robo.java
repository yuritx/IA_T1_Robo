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
			resposta = aStar(xRobo, yRobo, lixeiras[cout], lixeiras[cout+1]);
			}
		else{ 
			ArrayList <String> aux = aStar(xRobo, yRobo, lixeiras[cout], lixeiras[cout+1]);
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
			resposta = aStar(xRobo, yRobo, carregadores[cout], carregadores[cout+1]);
			}
		else{ 
			ArrayList <String> aux = aStar(xRobo, yRobo, carregadores[cout], carregadores[cout+1]);
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


	public ArrayList<String> aStar(int xInicial, int yInicial, int xfinal, int yFinal){
				return null;
	}

}