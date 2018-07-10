package Maneger;

import java.util.ArrayList;

import lejos.pc.comm.NXTCommFactory;

public class ExecutaImpreNXT {

	public static void main(String[] args) {
		
		System.out.println("Inicia Recepção");
		PotatoReceptor pr = new PotatoReceptor(true);
		
		ArrayList<Integer> acaoLista = pr.recebeDados();
		PotatoPrinter pp  = PotatoPrinter.Inicialize();		
		pp.ExecutaimprimeLinhaInteger(acaoLista);
		
		

	}

}
