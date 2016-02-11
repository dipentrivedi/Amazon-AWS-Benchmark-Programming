import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;


public class MultiTcpClient extends Thread {

	/**
	 * @param args
	 */
	static String host;
	int port,len;
	static int iteration=100;

	public MultiTcpClient(int i) {
		// TODO Auto-generated constructor stub
		this.port=i;
		start();
	}
	public void run(){
	try{
								
			//String host;
			Socket s=new Socket(host,port); 
			
		//	System.out.println("port :"+port);
		/*	
			if(len==1){
				iteration=100;
			}else if(len==1000){
				iteration=100;
			}else{
				iteration=10;
			}
		    
		*/	
			byte[] b=new byte[len];

			for(int i=0;i<b.length;i++){
				b[i]=(byte)i;
			}
		
					
				DataOutputStream dout=new DataOutputStream(s.getOutputStream());  
				DataInputStream din=new DataInputStream(s.getInputStream());
				dout.writeInt(b.length);
					
				for(int j=0;j<iteration;j++){
					dout.write(b);
				}
			
			//	DataInputStream din=new DataInputStream(s.getInputStream());
		
				int length=din.readInt();
				byte[] b1 = new byte[length];
						
				for(int j=0;j<iteration;j++){	
					din.read(b1);
				}
	
				dout.flush();
				s.close();
			}
			catch(Exception e)
			{
				System.out.println(e);
			}		
	

		
	}
	
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		host=args[0];
		
		int len=Integer.parseInt(args[1]);
		
		System.out.println("the Byte Length: 1byte 1024byte 65536byte :----> "+len);
		
		double startTime=System.currentTimeMillis();
		
		MultiTcpClient t1=new MultiTcpClient(5000);
		MultiTcpClient t2=new MultiTcpClient(6000);
		
	/*	t1.start();
		t2.start();
	*/	
		t1.join();
		t2.join();
		
		double endTime=System.currentTimeMillis();
		
		double elapsedTime=(endTime-startTime);
		
		double latency= (elapsedTime*len)/iteration;
		

		System.out.println("\nElapsed Time :"+elapsedTime+ " ms");
	    
		System.out.println("\nLatency :"+latency+ " ms");
	    
		double throughput = (2*len*iteration*8)/(elapsedTime*1000);
	
		System.out.println("\nThroughput :"+throughput+" Mbits/sec");

	}

}
