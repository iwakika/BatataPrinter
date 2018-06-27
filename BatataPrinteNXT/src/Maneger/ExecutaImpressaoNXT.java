package Maneger;

import lejos.pc.comm.NXTCommFactory;

public class ExecutaImpressaoNXT {

	public static void main(String[] args) {
		
		System.out.println("Inicia Recepção");
		PotatoReceptor pr = new PotatoReceptor(NXTCommFactory.USB);
		
		
		int [][] matrizImpressao = pr.montaMatriz(pr.recebeDados());
		
		PotatoPrinter pp  = PotatoPrinter.Inicialize(matrizImpressao);
		pp.imprimeLinha();
		
		

	}

}
