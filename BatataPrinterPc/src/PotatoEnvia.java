import java.awt.List;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;

import org.omg.CORBA.Environment;


import lejos.pc.comm.NXTComm;
import lejos.pc.comm.NXTCommException;
import lejos.pc.comm.NXTCommFactory;
import lejos.pc.comm.NXTInfo;

public class PotatoEnvia {
	private int  tipoConexao;
	private int limiteTentativas = 5;
	private NXTInfo nxt;
	private NXTComm nxtComm ;
	
	
	public static void main(String[] args) {
		
		
		PotatoImagem img = new PotatoImagem("MainShip1");
		int[][] matriz = img.transformaMatrizPB();  
		
		System.out.println("USBCod:" +NXTCommFactory.USB);
		PotatoEnvia pe = new PotatoEnvia(NXTCommFactory.USB);
		int[][] matrizNova = pe.montaMatrizTeste(pe.enviaDadosTeste(matriz));
		
		PotatoPrinterTeste ppt = new PotatoPrinterTeste(matrizNova);
		
		ppt.imprimeLinha();
		
		
		
	}
	
	public PotatoEnvia(int tipoConexao) {
	
			this.tipoConexao = tipoConexao;
			try {
				conectar();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	
	/**Converte Os dados da matriz em uma lista de açoes antes de enviar
	 * 
	 * @param matrizImpressao
	 */
	public void enviaDadosAcaoLista(int[][] matrizImpressao){
		
		ArrayList<PotatoLinha> acaoLista  = converteMatrizParaAcao(matrizImpressao);
		
		try {
			System.out.println("Inicia envio de dados");
			 nxtComm.open(nxt);
			 OutputStream dos = nxtComm.getOutputStream();
			
			 int x= 0;
			 System.out.println("AcaoLista," + acaoLista.size());
			 for (PotatoLinha potatoLinha : acaoLista) {
				 dos.write(potatoLinha.getAcao().value);
				 dos.write(potatoLinha.getLinha());
				 x++;
				 
			}			
			 
			 dos.write(-1); //sinaliza final do envio
			 dos.flush();
			 dos.close();
			 System.out.println("Finaliza Envio de Dados");
			 
			
		} catch (NXTCommException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public ArrayList<PotatoLinha> converteMatrizParaAcao(int[][] matrizImpressao){
		
			
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
				
				return acaoLista;
				//System.out.println(ExecutaimprimeLinhaTeste(acaoLista));
	}

	
	
	public void enviaDados(int[][] matriz){
		try {
			System.out.println("Inicia envio de dados");
			 nxtComm.open(nxt);
			 OutputStream dos = nxtComm.getOutputStream();
			
			 int sizeI = matriz.length;
			 int sizeJ = matriz[0].length;

			 dos.write(sizeI);
			 dos.write(sizeJ);
			 int x= 0;
			 for(int i = 0; i < sizeI ; i++){
				 for(int j = 0; j < sizeJ; j++){
					 dos.write(matriz[i][j]);
					 x++;
					 System.out.println("Enviando," + i + ","+j + ":" + x);
					 //se der erro tentar enviar bytes
				 }
			 }
			 
			 dos.write(-1); //sinaliza final do envio
			 dos.flush();
			 dos.close();
			 System.out.println("Finaliza Envio de Dados");
			 
			
		} catch (NXTCommException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<Integer> enviaDadosTeste(int[][] matriz){
		 ArrayList<Integer> list = null;
		
	//	try {

			 //OutputStream dos = nxtComm.getOutputStream();	 
			 list = new ArrayList<Integer>();
			
			 int sizeI = matriz.length;
			 int sizeJ = matriz[0].length;

			 //dos.write(sizeI);
			 list.add(sizeI);
			 list.add(sizeJ);
			 //dos.write(sizeJ);
			 
			 for(int i = 0; i < sizeI ; i++){
				 for(int j = 0; j < sizeJ; j++){
					 list.add(matriz[i][j]);
					 //dos.write(matriz[i][j]);
					 //se der erro tentar enviar bytes
				 }
			 }
			 
			 list.add(-1); //sinaliza final do envio
			
			
	///	} catch (NXTCommException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
	//	}
		return list;
	}
	
	
	
	public void conectar() throws Exception{
		System.out.println("Inicia Conexão");
		NXTInfo[] nxtInfo = null;
		
		int cont = 0;
		while(nxtInfo == null || cont == limiteTentativas ){
			if(tipoConexao == NXTCommFactory.USB) {
				System.out.println("Busca USB");
				nxtInfo = buscaPorNxtUSB();
			}else{
				nxtInfo = buscaPorNXTBlueTooth();
			}
			cont++;
		}
		if(nxtInfo == null){
			 throw new Exception("Conexão não efetuada");
		}else{		
		nxt = nxtInfo[0];
		System.out.println("Conecação efetuada com:"+ nxt);
		}
		
	}
	
	
	public NXTInfo[] buscaPorNxtUSB(){		
		NXTInfo[] nxtInfo = null;
		try {
			nxtComm = NXTCommFactory.createNXTComm(NXTCommFactory.USB);
			nxtInfo = nxtComm.search("NXT");
			//Se houver apenas um NXT conectado, você poderá usar um parâmetro de nome nulo no método search ().
		} catch (NXTCommException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Retorna Info");
		return nxtInfo;
		
	}
	
	public NXTInfo[] buscaPorNXTBlueTooth(){
		NXTInfo[] nxtInfo = null;
		try {
			nxtComm = NXTCommFactory.createNXTComm(NXTCommFactory.BLUETOOTH);
			nxtInfo = nxtComm.search("NXT");
			//Se houver apenas um NXT conectado, você poderá usar um parâmetro de nome nulo no método search ().
		} catch (NXTCommException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nxtInfo;
		
	}

	
	/**
	 * Teste local da conversão
	 * @return
	 */
	
	public ArrayList<Integer> recebeDadosTeste(ArrayList<Integer> dataOutStream){
		int cont = 0;
		int x = 0;
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		do{
			
			 x = dataOutStream.get(cont);
			 cont++;
			
		}while(x != -0);
		
		return dataOutStream;
		
		
	}
	
	
	public int[][] montaMatrizTeste(ArrayList<Integer> list) {
		
		int sizeI = list.get(0);
		int sizeJ = list.get(1);
		int contI = 0;
		int contJ = 0;
		int[][] matriz  = new int[sizeI][sizeJ];
		//System.out.println("Tamanho:" + sizeI + "," + sizeJ);
		// -1 porque o ultimo valor é o finalizador
		for(int i = 2; i < list.size()-1; i++){
			int value =  list.get(i);
			
			
			if( contJ == sizeJ){
				contJ = 0;
				contI++;
			}	
				
			//System.out.println("I:"+ contI + "-J:" + contJ + " - Value:" + value);
			matriz[contI][contJ] = value;			
					
			contJ++;
			
			
		}
		return matriz;
		
	}

	

}
