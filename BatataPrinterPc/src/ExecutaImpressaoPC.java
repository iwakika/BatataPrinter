

import lejos.pc.comm.NXTCommFactory;

public class ExecutaImpressaoPC {

	public static void main(String[] args) {
		
		///Ler imagem
		System.out.println("Inicia Leitura de Imagem");
		PotatoImagem img = new PotatoImagem("Xalakom");
		///Gera matriz para impress�o
		System.out.println("GeraMatriz para Impress�o");
		int[][] matriz = img.transformaMatrizPB();  
		
		
		//inicia conecx�o
		System.out.println("----Envio de dados---");
		PotatoEnvia pe = new PotatoEnvia(NXTCommFactory.USB);
		
		//envia dados da matriz
		pe.enviaDados(matriz);	
		
		
	}

}
