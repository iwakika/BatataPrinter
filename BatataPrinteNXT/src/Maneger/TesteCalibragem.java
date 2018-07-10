package Maneger;

import Maneger.PotatoManegerPrinter.CanetaAcao;

public class TesteCalibragem {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
				PotatoManegerPrinter pmp = new PotatoManegerPrinter(100,100,100);
				System.out.println("Imprime");
				//pmp.setStatusAtualSobeDesce(CanetaAcao.DESCE);
				//pmp.acaoMoveCanetaSobe();
				/*for(int i = 0;  i < 2 ;i++){
				
				pmp.acaoMoveCanetaLateralDireita(3);
				pmp.acaoMoveCanetaSobe();
				//
				}*/
				for(int i = 0; i< 10; i++){
				pmp.acaoMovePapelTraz(2);
				//pmp.acaoMoveCanetaDesce}Metade();
				}
				
				
				//pmp.AcaoMovimentaInicial();

				//pmp.acaoMoveCanetaLateralEsquerda(55);
				//pmp.acaoMoveCanetaLateralDireita(55);
				
				/*while (true)
				{
				pmp.verificaExistePapel();
				}
				*/
				
	}

}
