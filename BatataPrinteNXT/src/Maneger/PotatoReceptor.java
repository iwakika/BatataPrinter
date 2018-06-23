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
import lejos.pc.comm.NXTCommFactory;

public class PotatoReceptor {
	private int  tipoConexao;
	private NXTConnection connection =null; 
	
	

	public static void main(String[] args) {
	
		
	}
	
	public PotatoReceptor(int tipoConexao){
		this.tipoConexao = tipoConexao;
	}
	
	public void conectar(){
		
		
		
		  connection = null;
		  	
		 if(tipoConexao == NXTCommFactory.USB) {
	
		      connection = Bluetooth.waitForConnection();
		    } else {
		      LCD.drawString("waiting for USB", 0,1 );
		      connection = USB.waitForConnection();
		    }		 
		 
	}
	
	public ArrayList<Integer> recebeDados(){
		   //DataOutputStream dataOut = connection.openDataOutputStream();
		ArrayList<Integer> list = new ArrayList<Integer>();
	 	DataInputStream dis = connection.openDataInputStream();
	   int x = 0;
	 	do{
		   
	 		try {
				x = dis.read();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 		list.add(x);
	 		
	   }while(x != -1);
	 	
		return list;
	 	
	}
	
	public int[][] montaMatriz(ArrayList<Integer> list) {
		
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
