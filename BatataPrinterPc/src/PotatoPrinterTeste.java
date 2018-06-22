

import java.util.ArrayList;

public class PotatoPrinterTeste {
	
	
	public static void main(String[] args) {
		
		PotatoImagem img = new PotatoImagem("MainShip1");
		int[][] matriz = img.transformaMatrizPB();  
		
		PotatoPrinterTeste ppt = new PotatoPrinterTeste(matriz);
		ppt.imprimeLinha();
		
		//System.out.println(img.imprimeMatrizEmString(matriz));
		
	}
	
	private int[][] matrizImpressao;
	
	
	public PotatoPrinterTeste(int[][] matrizImpressao) {
		this.matrizImpressao = matrizImpressao;
		
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
			
			
			System.out.println(ExecutaimprimeLinhaTeste(acaoLista));
		
	}


	

	private String ExecutaimprimeLinhaTeste(ArrayList<PotatoLinha> acaoLista) {
		String saida = "";
		
		for(PotatoLinha p : acaoLista) {
			
			switch (p.getAcao()) {
			case DESENHA:
				//saida+= String.format("%"+p.getLinha()+"s", "*").replace(' ', '*');
				
				for(int i = 0 ; i < p.getLinha(); i++) {
					saida+= "*";
				}
				
				break;
			case NAO_DESENHA:
				//saida +=" ";
				//saida+= String.format("%"+p.getLinha()+"s", " ");				
				for(int i = 0 ; i < p.getLinha(); i++) {
					saida+= " ";
				}
				
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
