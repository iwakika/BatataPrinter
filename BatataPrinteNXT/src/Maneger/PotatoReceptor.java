package Maneger;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.comm.Bluetooth;
import lejos.nxt.comm.NXTCommConnector;
import lejos.nxt.comm.NXTConnection;
import lejos.nxt.comm.USB;

public class PotatoReceptor {
	private Boolean conecaoUSB ;
	private NXTConnection connection =null; 
	
	

	public static void main(String[] args) {
	
		
	}
	
	public PotatoReceptor(Boolean ConecaoUSB){
		this.conecaoUSB = ConecaoUSB;
		conectar();
	}
	
	public void conectar(){
		
		
		System.out.println("Ini Conexção");
		  connection = null;
		  	
		 if(!conecaoUSB ) {
	
		      connection = Bluetooth.waitForConnection();
		      System.out.println("Conectado");
		    } else {
		      LCD.drawString("waiting for USB", 0,1 );
		      connection = USB.waitForConnection();
		      System.out.println("Conectado");
		    }		 
		 
	}
	
	/**
	 * Recebe  dados do byte a byte até terminar todos dados para ler ou
	 * @return
	 */
	public ArrayList<Integer> recebeDados(){
		   //DataOutputStream dataOut = connection.openDataOutputStream();
		ArrayList<Integer> list = new ArrayList<Integer>();
		System.out.println("Inicia = Recebe dados ");
	 	DataInputStream dis = connection.openDataInputStream();
	   int x = 0;
	   int i = 0;
	 	do{
		   
	 		try {
	 			 
				x = dis.read();
				i++;
				System.out.println("Recebe:"+i + "-" +x);
	 			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Erro ao receber");
			}
	 		list.add(x);
	 		/// Limita a leitura para não estourar a memória do brick, ou terminou a leitura
	   }while(x != 255 && i < 1000);
	 	System.out.println("Finaliza Recebe dados ");
	 	
		return list;
	 	
	}
	
	public void montaAcaoLista(ArrayList<Integer> list){
		
	}
	
	public int[][] montaMatriz(ArrayList<Integer> list) {
		System.out.println("montaMatriz");
		int sizeI = list.get(0);
		int sizeJ = list.get(1);
		int contI = 0;
		int contJ = 0;
		int[][] matriz  = new int[sizeI][sizeJ];
		// -1 porque o ultimo valor é o finalizador
		for(int i = 2; i < list.size()-1; i++){
			int value =  list.get(i);
			
			matriz[contI][contJ] = value;
			
			if( contI == sizeI){
				contI = 0;
				contJ++;
			}	
						
			contI++;
			
			
		}
		return matriz;
		
	}

}
