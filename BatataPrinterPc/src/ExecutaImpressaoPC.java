

import lejos.pc.comm.NXTCommFactory;

public class ExecutaImpressaoPC {

	public static void main(String[] args) {
		
		///Ler imagem
		PotatoImagem img = new PotatoImagem("Xalakom");
		///Gera matriz para impressão
		int[][] matriz = img.transformaMatrizPB();  
		
		
		//inicia conecxão
		PotatoEnvia pe = new PotatoEnvia(NXTCommFactory.USB);
		
		//envia dados da matriz
		pe.enviaDados(matriz);	
		
		
	}

}
