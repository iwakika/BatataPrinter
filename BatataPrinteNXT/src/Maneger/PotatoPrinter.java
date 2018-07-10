package Maneger;

import java.util.ArrayList;

import org.apache.bcel.generic.RETURN;

public class PotatoPrinter {
	
	
	public static void main(String[] args) {
		
	}
	
	private static int[][] matrizImpressao;
	private static PotatoManegerPrinter pmp; 
	private static PotatoPrinter pp;
	
	
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
	
	public static EnumImprime EnumImprimeEquivalente(int valor){
		
		if(valor == 0){
			
			return EnumImprime.NAO_DESENHA;
		
		}else if(valor == 1){
			return EnumImprime.DESENHA;
		}else if(valor == 2){
			return EnumImprime.PROXIMA_LINHA;	
		}else if(valor == 3){
			return EnumImprime.FINALIZA;
		}
		return null;
	}
	
	private PotatoPrinter(){
		
	}
	
	public static PotatoPrinter Inicialize(int[][] matriz){
		if(pp == null){
			pp = new PotatoPrinter();
		}
		
		pmp = new PotatoManegerPrinter(100,100,100);
		
		matrizImpressao = matriz;
		
		return pp;
	}
	
	public static PotatoPrinter Inicialize(){
		if(pp == null){
			pp = new PotatoPrinter();
		}
		
		pmp = new PotatoManegerPrinter(100,100,100);
		
		
		
		return pp;
	}
	
	
	/**
	 * Imprime um ponto por vez no papel atraez de uma matriz de inteiro
	 */
	public void imprimePontos() {
		
		pmp.AcaoMovimentaInicial();
		
		int sizeI = matrizImpressao.length;
		int sizeJ = matrizImpressao[0].length;
		
		for(int i = 0 ; i < sizeI; i++) {
			for(int j = 0; i <sizeJ; j++ ) {
				
				if(matrizImpressao[i][j] == 1) {
					pmp.acaoMoveCanetaDesceMetade();
					pmp.acaoMoveCanetaSobeMetade();					
				}
				
				//pmp.acaoMoveCanetaLateralDireita();
				pmp.acaoMoveCanetaProximo(1);;// lado para qual deve desenhar
				
			}
			pmp.acaoMovePapelFrente(1);; //puxa papel para  a próxima linha
			
		}
		pmp.acaoMoveCanetaLateralDireita(sizeI);	
		
		
	}
	

	/***
	 * Executa a impressão a partir de uma matriz inicializada.
	 */
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
		
			ExecutaimprimeLinha(acaoLista);
	}

public String ExecutaimprimeLinhaInteger(ArrayList<Integer> acaoLista) {
	String saida = "";
	int acaoValor =0;
	int linha = 0;
	int x =0;
	ArrayList<PotatoLinha> acaoListax = new ArrayList<PotatoLinha>();
	for(int i = 0 ; i < acaoLista.size(); i++) {
		
		x = acaoLista.get(i);
		if(i % 2 == 0){ // então é acão			
			acaoValor  = x;
		}else{
			linha = x;
			acaoListax.add(new PotatoLinha(linha, EnumImprimeEquivalente(acaoValor)));
		}
	}
		
		ExecutaimprimeLinha(acaoListax);
		
	
	return saida;
}


private String ExecutaimprimeLinha(ArrayList<PotatoLinha> acaoLista) {
	String saida = "";
	pmp.AcaoMovimentaInicial();
	
	for(PotatoLinha p : acaoLista) {
		
		switch (p.getAcao()) {
		case DESENHA:
			//saida+= String.format("%"+p.getLinha()+"s", "*").replace(' ', '*');
			System.out.println("Desenha");
			pmp.acaoMoveCanetaDesceMetade();
			pmp.acaoMoveCanetaProximo(p.getLinhaQtd());
			
			
			break;
		case NAO_DESENHA:
			System.out.println("N_Desenha");
			//saida +=" ";
			pmp.acaoMoveCanetaSobeMetade();
			pmp.acaoMoveCanetaProximo(p.getLinhaQtd());			
			
			break;
		case PROXIMA_LINHA:
			
			//System.out.println("Proxima_Linha");
			
			pmp.acaoMoveCanetaProximaLinha();
			//pmp.acaoEjetarPapel();
			break;
		case FINALIZA:
			System.out.println("Finaliza");
			pmp.acaoEjetarPapel();
			break;			
		default:
			break;
		}
		
	}
	
	
	return saida;
}

	

}
