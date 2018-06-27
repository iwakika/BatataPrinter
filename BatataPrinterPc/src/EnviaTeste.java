import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import lejos.pc.comm.NXTComm;
import lejos.pc.comm.NXTCommException;
import lejos.pc.comm.NXTCommFactory;
import lejos.pc.comm.NXTInfo;


public class EnviaTeste {

	public static void main(String[] args) throws NXTCommException, IOException {
		// TODO Auto-generated method stub
		
		
		
		
		NXTInfo nxt = null;
		NXTComm nxtComm = null ;
		
		
		NXTInfo[] nxtInfo = null;
		
	 nxtComm = NXTCommFactory.createNXTComm(NXTCommFactory.USB);
		//nxtComm = NXTCommFactory.createNXTComm(NXTCommFactory.BLUETOOTH);
		nxtInfo = nxtComm.search("NXT");
		nxt = nxtInfo[0];	
		try {
			nxtComm.open(nxt);
		} catch (NXTCommException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		OutputStream dos = nxtComm.getOutputStream();
		
		
		for(int i = 0; i<= 5 ;i ++){
			System.out.println("Escreve"+i);
		 dos.write(i);
		}
		dos.write(-1);
	
		dos.flush();
		dos.close();
	

	}

}
