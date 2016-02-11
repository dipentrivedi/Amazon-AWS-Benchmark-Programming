import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;


public class UdpSender extends Thread {

	/**
	 * @param args
	 * @throws IOException 
	 */
	static int iteration=100;
	static int size;
	public void run(){
		try {
			DatagramSocket ds = new DatagramSocket();
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		
		String host="localhost";//args[0];
	    
		
	    byte[] b=new byte[size];
	    byte[] b1=new byte[size];
		
	    for(int i=0;i<size;i++){
			b[i]=(byte)i;
		}
		
		//System.out.println("length of the byte array: "+b.length);
		
	    InetAddress ip = InetAddress.getByName("localhost");  
	    
	   // System.out.println("host adsdress: "+ip);
	    
	    DatagramPacket dp = new DatagramPacket(b, b.length,ip, 6363);  
	    
	    
	    
	    double startTime=System.currentTimeMillis();
	   
	    for(int j=0;j<iteration;j++){
	    	ds.send(dp); 
	    }
	    
	    //System.out.println("hi.....");
	    
	    DatagramPacket dr = new DatagramPacket(b, b.length);  
	    
	    //System.out.println("hi.....");
	       
	    ds.receive(dr);
	   // System.out.println("hi.....");
	    
	    for(int j=0;j<iteration;j++){
	    	b1=dr.getData();	
	    }
	    
	    double endTime=System.currentTimeMillis();
		
		double elapsedTime=(endTime-startTime);
		
		double latency= (elapsedTime*size)/iteration;
		

		System.out.println("\nElapsed Time :"+elapsedTime+ " ms");
	    
		System.out.println("\nLatency :"+latency+ " ms");
	    
		double throughput = (2*size*iteration*8)/(elapsedTime*1000);
	
		System.out.println("\nThroughput :"+throughput+" Mbits/sec");

	    //System.out.println("length :"+b1.length);
	    /*for(int j=0;j<b1.length;j++){
	    System.out.println(" "+b1[j]);
	    }*/
	    ds.close();  
		}  
	    catch(Exception e){
	    	System.out.println(e);
	    }
	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//Scanner sc=new Scanner(System.in);
	    size=Integer.parseInt(args[0]);
	    System.out.println("The Size of Packet: 1byte 1000byte 64000byte--->"+size);
	    
		UdpSender t1=new UdpSender();
		t1.start();
	}

}
