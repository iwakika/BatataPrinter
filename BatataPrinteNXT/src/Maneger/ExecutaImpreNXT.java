package Maneger;

import java.util.ArrayList;

import lejos.pc.comm.NXTCommFactory;

public class ExecutaImpreNXT {
	
	private static boolean concluidaImpressao;

	public static void main(String[] args) {
		
		System.out.println("Inicia Recepção");
		PotatoReceptor pr = new PotatoReceptor(true);
		
		concluidaImpressao = false;
		while(!concluidaImpressao){
			
			ArrayList<Integer> acaoLista = pr.recebeDados();
			PotatoPrinter pp  = PotatoPrinter.Inicialize();		
			concluidaImpressao = pp.ExecutaimprimeLinhaInteger(acaoLista);
		}
		
		

	}

}
