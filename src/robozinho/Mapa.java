package robozinho;

// MAPA EM ZERO = VAZIO --    MAPA EM UM = LIXO  --  MAPA EM DOIS = LIXEIRA -- MAPA EM TRÊS = CARREGADOR; --
// MAPA EM QUATRO = PAREDE


public class Mapa {

	
	private Nodo [][] mapa;
	private Nodo [] carregadores;
	private Nodo [] lixeiras;
	public Mapa(int largura, int altura){
		mapa = new Nodo[largura][altura];
		
	}
	
	public void	limpaEspaco (int x, int y){
		
		mapa[x][y].status= 0;
	}
	
	public int estadoPosicao(int x, int y){
		return mapa[x][y].status;
		
	}


	public Nodo[] getLixeiras(){
		return lixeiras;
	}

	public Nodo[] getCarregadores() {
		return carregadores;
	}


 	public int tamanhoX(){
 		return mapa.length;
 	}
 	
 	public int tamanhoY(){
 		return mapa[0].length;
 	}
 	public Nodo[][] getMapa (){ return mapa;}
}
