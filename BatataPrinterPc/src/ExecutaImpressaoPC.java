

import lejos.pc.comm.NXTCommFactory;

public class ExecutaImpressaoPC {

	public static void main(String[] args) {
		
		///Ler imagem
		PotatoImagem img = new PotatoImagem("Xalakom");
		///Gera matriz para impress�o
		int[][] matriz = img.transformaMatrizPB();  
		
		
		//inicia conecx�o
		PotatoEnvia pe = new PotatoEnvia(NXTCommFactory.USB);
		
		//envia dados da matriz
		pe.enviaDados(matriz);	
		
		
	}

}
