import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;


public class MultiUdpSender extends Thread {

	/**
	 * @param args
	 */
	public int port;
	static int size;
	static int iteration=100;
	public MultiUdpSender(int i) {
		// TODO Auto-generated constructor stub
		this.port=i;
		start();
	}

	public void run(){
		try {
			DatagramSocket ds = new DatagramSocket();
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		
		String host="localhost";//args[0];
	    
		/*System.out.println("Enter the Size of Packet: 1byte 1000byte 64000byte");
	    Scanner sc=new Scanner(System.in);
	    int size=sc.nextInt();
	    */
	    byte[] b=new byte[size];
	    byte[] b1=new byte[size];
		
	    for(int i=0;i<size;i++){
			b[i]=(byte)i;
		}
		
	/*	System.out.println("length of the byte array: "+b.length);
	*/	
	    InetAddress ip = InetAddress.getByName("localhost");  
	/*    
	    System.out.println("host adsdress: "+ip);
	*/    
	    DatagramPacket dp = new DatagramPacket(b, b.length,ip, port);  
	/*    System.out.println("hi....");
	*/    
	    double startTime=System.currentTimeMillis();
	    	
	    for(int j=0;j<iteration;j++){
	    	ds.send(dp); 
	    }
	/*    System.out.println("hi...."+1);
	    
	    System.out.println("hi....."+2);
	*/    
	    DatagramPacket dr = new DatagramPacket(b, b.length);  
	    
	/*    System.out.println("hi....."+3);
	*/    ds.receive(dr);
	/*    System.out.println("hi....."+4);
	*/    
	    for(int j=0;j<iteration;j++){
	    	b1=dr.getData();	
	    }
	    
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
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		size=Integer.parseInt(args[0]);
		
		System.out.println("the Byte Length: 1byte 1024byte 65536byte :----> "+size);
		
		double startTime=System.currentTimeMillis();

		MultiUdpSender t1=new MultiUdpSender(1234);
		MultiUdpSender t2=new MultiUdpSender(1334);
		
		
		t1.join();
		t2.join();

	    double endTime=System.currentTimeMillis();
		
		double elapsedTime=(endTime-startTime);
		
		double latency= (elapsedTime*size)/iteration;
		

		System.out.println("\nElapsed Time :"+elapsedTime+ " ms");
	    
		System.out.println("\nLatency :"+latency+ " ms");
	    
		double throughput = (2*size*iteration*8)/(elapsedTime*1000);
	
		System.out.println("\nThroughput :"+throughput+" Mbits/sec");

	}

}
