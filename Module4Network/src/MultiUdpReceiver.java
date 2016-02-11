import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


public class MultiUdpReceiver extends Thread {

	/**
	 * @param args
	 */
    
	static int iteration=100,bufferSize;
	int port;
	public MultiUdpReceiver(int i) {
		// TODO Auto-generated constructor stub
		this.port=i;
		start();
	}

	public void run(){
		try{
			
		/*	 System.out.println("hi..."+port);
		*/	
			DatagramSocket dr = new DatagramSocket(port);  

			System.out.println("hi..."+port);

			//Integer.parseInt(args[0]);
			byte[] buf = new byte[bufferSize];  
		    
			DatagramPacket dp = new DatagramPacket(buf, bufferSize);  
		    dr.receive(dp);  
		    for(int j=0;j<iteration;j++){
		    	buf=dp.getData();
		    }
		    
		  //  int length=dp.getLength();
		   // String host="localhost";
		    
		    InetAddress ip = InetAddress.getByName("localhost");  
		    
		    
		//    System.out.println("size of the  received packet :"+length);
		   
		//    System.out.println("host address :"+ip);
		    
		   // String str = new String(dp.getData(), 0, dp.getLength());  
		/*    for(int i=0;i<length;i++){
		    	System.out.println(" "+buf[i]);
		    }
		*/    
		    
		    /*<--Reciever to Sender-->*/
		 /*   System.out.println("port :"+dp.getPort());
		*/   
		    DatagramPacket dataSend = new DatagramPacket(buf, buf.length, ip, dp.getPort()); 
		   // System.out.println("hi....");
			
		    for(int j=0;j<iteration;j++){
				dr.send(dataSend);
			}
		    
		//    System.out.println("size of the  received packet :"+buf.length);
		    
		  }		  catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		} 		
	}
	
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		MultiUdpReceiver t1=new MultiUdpReceiver(1234);
		MultiUdpReceiver t2=new MultiUdpReceiver(1334);
		
		bufferSize=Integer.parseInt(args[0]);
		
		t1.join();
		t2.join();

	}

}
