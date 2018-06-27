package Maneger;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import lejos.nxt.comm.Bluetooth;
import lejos.nxt.comm.NXTConnection;
import lejos.nxt.comm.USB;
import lejos.pc.tools.Console;

public class RecebeTeste {

	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("Espera Conexão");
		NXTConnection connection =null; 
		connection = USB.waitForConnection();
		//connection = Bluetooth.waitForConnection();
	 	DataInputStream dis = connection.openDataInputStream();
	 	int value = 0;
	 	
	 	int i = 0;
	 	
	 	System.out.println("Inicia Leitura");
	 	while (value != 255|| i > 1000){
	 		try {
				value = dis.read();
				i++;
				System.out.println(value);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("potatoError");
				e.printStackTrace();
			}
	 		
		}
		System.out.println("Finaliza Leitura");
	 	while (true){
		}

	}

}
