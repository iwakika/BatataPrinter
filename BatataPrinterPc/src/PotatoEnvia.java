import java.awt.List;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
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
	}
	
	
	public void enviaDados(int[][] matriz){
		try {
			 nxtComm.open(nxt);
			 OutputStream dos = nxtComm.getOutputStream();
			
			 int sizeI = matriz.length;
			 int sizeJ = matriz[0].length;

			 dos.write(sizeI);
			 dos.write(sizeJ);
			 
			 for(int i = 0; i < sizeI ; i++){
				 for(int j = 0; j < sizeJ; j++){
					 dos.write(matriz[i][j]);
					 //se der erro tentar enviar bytes
				 }
			 }
			 
			 dos.write(-1); //sinaliza final do envio
			
			
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
		
		NXTInfo[] nxtInfo = null;
		int cont = 0;
		while(nxtInfo == null || cont == limiteTentativas ){
			if(tipoConexao == NXTCommFactory.USB) {
				buscaPorNxtUSB();
			}else{
				buscaPorNXTBlueTooth();
			}
			cont++;
		}
		if(nxtInfo == null){
			 throw new Exception("Conexão não efetuada");
		}else{		
		nxt = nxtInfo[0];
		}
		
	}
	
	
	public NXTInfo[] buscaPorNxtUSB(){		
		NXTInfo[] nxtInfo = null;
		try {
			nxtComm = NXTCommFactory.createNXTComm(NXTCommFactory.USB);
			nxtInfo = nxtComm.search("MYNXT");
			//Se houver apenas um NXT conectado, você poderá usar um parâmetro de nome nulo no método search ().
		} catch (NXTCommException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nxtInfo;
		
	}
	
	public NXTInfo[] buscaPorNXTBlueTooth(){
		NXTInfo[] nxtInfo = null;
		try {
			nxtComm = NXTCommFactory.createNXTComm(NXTCommFactory.BLUETOOTH);
			nxtInfo = nxtComm.search("MYNXT");
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
