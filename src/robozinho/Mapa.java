package robozinho;

// MAPA EM ZERO = VAZIO --    MAPA EM UM = LIXO  --  MAPA EM DOIS = LIXEIRA -- MAPA EM TRÊS = CARREGADOR; --
// MAPA EM QUATRO = PAREDE


public class Mapa {

	
	private int [][] mapa;
	private int [] carregadores;
	private int [] lixeiras;
	public Mapa(int largura, int altura){
		mapa = new int[largura][altura];
		
	}
	
	public void	limpaEspaco (int x, int y){
		
		mapa[x][y] = 0;
	}
	
	public int estadoPosicao(int x, int y){
		return mapa[x][y];
		
	}


	public int[] getLixeiras(){
		return lixeiras;
	}

	public int[] getCarregadores() {
		return carregadores;
	}
}
