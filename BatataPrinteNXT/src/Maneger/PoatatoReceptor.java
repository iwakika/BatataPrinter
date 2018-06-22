package Maneger;

import java.io.DataOutputStream;
import java.io.IOException;

import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.comm.Bluetooth;
import lejos.nxt.comm.NXTConnection;
import lejos.nxt.comm.USB;

public class PoatatoReceptor {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		

		   // LCD.drawString("right BT",0, 0);
		      NXTConnection connection = null;

		   // if(Button.waitForAnyPress() == Button.ID_RIGHT){
		    //  LCD.drawString("waiting for BT", 0,1 );
		      connection = Bluetooth.waitForConnection();
		  //  } else {
		      LCD.drawString("waiting for USB", 0,1 );
		      connection = USB.waitForConnection();
		   // }

		    DataOutputStream dataOut = connection.openDataOutputStream();
		    try {
		      dataOut.writeInt(1234);
		    } catch (IOException e ) {
		      System.out.println(" write error "+e); 
		    }

	}

}
