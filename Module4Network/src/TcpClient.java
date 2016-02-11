import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;


public class TcpClient extends Thread {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	static String host=null;
	static int len;
/*	TcpClient(String h){
		host = h;
	}
*/	public void run(){
	try{
								
			Socket s=new Socket(host,6363); 
			
			//int len=in.nextInt();
			int iteration=1000;
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
				
				double startTime=System.currentTimeMillis();
				
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
				
				double endTime=System.currentTimeMillis();
				
				double elapsedTime=(endTime-startTime);
				
				double latency= (elapsedTime*len)/iteration;
				

				System.out.println("\nElapsed Time :"+elapsedTime+ " ms");
			    
				System.out.println("\nLatency :"+latency+ " ms");
			    
				double throughput = (2*len*iteration*8)/(elapsedTime*1000);
			
				System.out.println("\nThroughput :"+throughput+" Mbits/sec");

				dout.flush();
				s.close();
			}
			catch(Exception e)
			{
				System.out.println(e);
			}		
	
	}
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		// TODO Auto-generated method stub
		host = args[0];
		len=Integer.parseInt(args[1]);
	
		System.out.println("Give the Byte Length: 1byte 1024byte 65536byte :"+len);
	
		TcpClient t1=new TcpClient();

		t1.start();	
		/*try {
			t1.join();
			TcpClient t2=new TcpClient();
			t2.start();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
	}

}
