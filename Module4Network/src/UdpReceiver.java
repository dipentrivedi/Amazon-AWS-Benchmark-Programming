import java.io.IOException;
import java.net.BindException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;


public class UdpReceiver extends Thread {

	/**
	 * @param args
	 * @throws IOException 
	 */
	static int iteration=100,bufferSize;
	public void run(){
		  try{
			DatagramSocket dr = new DatagramSocket(6363);  
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
		    
		    DatagramPacket dataSend = new DatagramPacket(buf, buf.length, ip, dp.getPort()); 
		   // System.out.println("hi....");
			
		    for(int j=0;j<iteration;j++){
				dr.send(dataSend);
			}
		    
		    System.out.println("size of the  received packet :"+buf.length);
		    
		  }		  catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		} 		
	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		UdpReceiver t1=new UdpReceiver();
		bufferSize=Integer.parseInt(args[0]);
		t1.start();
		    //System.out.println(str);  
	}

}
