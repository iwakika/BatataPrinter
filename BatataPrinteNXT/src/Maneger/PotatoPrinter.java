package Maneger;

import java.util.ArrayList;

public class PotatoPrinter {
	
	
	public static void main(String[] args) {
		
	}
	
	private int[][] matrizImpressao;
	private PotatoManegerPrinter pmp = new PotatoManegerPrinter(); 
	
	
	public enum EnumImprime{		
		
		NAO_DESENHA(0),
		DESENHA(1),
		PROXIMA_LINHA(2),
		FINALIZA(3);
		
		int value;
		EnumImprime(int i) {
			this.value = i;
		
		}
		
	}
	
	
	/**
	 * Imprime um ponto por vez no papel atraez de uma matriz de inteiro
	 */
	public void imprimePontos() {
		
		int sizeI = matrizImpressao.length;
		int sizeJ = matrizImpressao[0].length;
		
		for(int i = 0 ; i < sizeI; i++) {
			for(int j = 0; i <sizeJ; j++ ) {
				
				if(matrizImpressao[i][j] == 1) {
					pmp.acaoMoveCanetaDesce();
					pmp.acaoMoveCanetaSobe();					
				}
				
				//pmp.acaoMoveCanetaLateralDireita();
				pmp.acaoMoveCanetaProximo(1);;// lado para qual deve desenhar
				
			}
			pmp.acaoMovePapelFrente(1);; //puxa papel para  a próxima linha
			
		}
		pmp.acaoMoveCanetaLateralDireita(sizeI);	
		
		
	}
	

public void imprimeLinha() {
		
		int sizeI = matrizImpressao.length;
		int sizeJ = matrizImpressao[0].length;
		ArrayList<PotatoLinha> acaoLista = new ArrayList<PotatoLinha>();
		
		EnumImprime statusAtual = null;
		EnumImprime statusAnterior = null;
		
		int linhaTamanho = 0;
		
		for(int i = 0 ; i < sizeI; i++) {		
			 
			for(int j = 0; j <sizeJ; j++ ) {
								
				if(matrizImpressao[i][j] == EnumImprime.DESENHA.value) {
					statusAtual = EnumImprime.DESENHA;
				}else {
					statusAtual = EnumImprime.NAO_DESENHA;					
				}
							
					if(j>0) { // não passa por isso na primeira verificacao pois é apenas parafinalizara linha
						if(statusAtual != statusAnterior) 
						{
							//finaliza acao anterioro
							acaoLista.add(new PotatoLinha(linhaTamanho, statusAnterior));
							linhaTamanho = 0;
							statusAnterior = statusAtual;					
						}
					}else {
						statusAnterior = statusAtual;
					}
					linhaTamanho++;	
			}
			
			//imnprime a ultima cao da linha
			acaoLista.add(new PotatoLinha(linhaTamanho, statusAnterior));
			linhaTamanho = 0;	
			statusAtual = null;
			acaoLista.add(new PotatoLinha(0, EnumImprime.PROXIMA_LINHA));
				
			}
			acaoLista.add(new PotatoLinha(0, EnumImprime.FINALIZA));
			
			
			//System.out.println(ExecutaimprimeLinhaTeste(acaoLista));
		
			ExecutaimprimeLinhaTeste(acaoLista);
	}

private String ExecutaimprimeLinha(ArrayList<PotatoLinha> acaoLista) {
	String saida = "";
	
	for(PotatoLinha p : acaoLista) {
		
		switch (p.getAcao()) {
		case DESENHA:
			//saida+= String.format("%"+p.getLinha()+"s", "*").replace(' ', '*');
			pmp.acaoMoveCanetaDesce();
			pmp.acaoMoveCanetaProximo(p.getLinhaQtd());
			
			
			break;
		case NAO_DESENHA:
			//saida +=" ";
			pmp.acaoMoveCanetaSobe();
			pmp.acaoMoveCanetaProximo(p.getLinhaQtd());			
			
			break;
		case PROXIMA_LINHA:
			pmp.acaoMovePapelFrente(1);
			pmp.acaoEjetarPapel();
			break;
		case FINALIZA:
			pmp.acaoEjetarPapel();
			break;			
		default:
			break;
		}
		
	}
	
	
	return saida;
}

	

	private String ExecutaimprimeLinhaTeste(ArrayList<PotatoLinha> acaoLista) {
		String saida = "";
		
		for(PotatoLinha p : acaoLista) {
			
			switch (p.getAcao()) {
			case DESENHA:				
				
				
				//saida+= String.format("%"+p.getLinhaQtd()+"s", " ");
	
				
				break;
			case NAO_DESENHA:
				//saida +=" ";
				//saida+= String.format("%"+p.getLinhaQtd()+"s", " ");				
				
				
				break;
			case PROXIMA_LINHA:
				saida +="\n";
				break;
			case FINALIZA:
				break;

			default:
				break;
			}
			
		}
		
		
		return saida;
	}

}
