

import lejos.pc.comm.NXTCommFactory;

public class ExecutaImpressaoPC {

	public static void main(String[] args) {
		
		///Ler imagem
		System.out.println("Inicia Leitura de Imagem");
		PotatoImagem img = new PotatoImagem("Xalakom");
		///Gera matriz para impressão
		System.out.println("GeraMatriz para Impressão");
		int[][] matriz = img.transformaMatrizPB();  
		
		
		//inicia conecxão
		System.out.println("----Envio de dados---");
		PotatoEnvia pe = new PotatoEnvia(NXTCommFactory.USB);
		
		//envia dados da matriz
		pe.enviaDados(matriz);	
		
		
	}

}
