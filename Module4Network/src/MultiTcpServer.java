import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class MultiTcpServer extends Thread {
	public int port;
	static int iteration=100;
	
	public Socket s;
	public MultiTcpServer(int i) {
		// TODO Auto-generated constructor stub
		this.port=i;
		start();
	}
	/**
	 * @param args
	 */
	public void run(){
		try{
			
			ServerSocket server=new ServerSocket(port);
			
		//	System.out.println("hi......"+server.getLocalPort());
			
			Socket s=server.accept();
		
			DataInputStream din=new DataInputStream(s.getInputStream());
	//		System.out.println("hi......");
			
			int length=din.readInt();
			
			
			/*if(length==1){
				iteration=100;
			}else if(length==1000){
				iteration=100;
				
			}else{
				iteration=10;
			}
		    */
			byte[] b = new byte[length];
			
			for(int j=0;j<iteration;j++){
				din.read(b);
			}
			
			DataOutputStream dout=new DataOutputStream(s.getOutputStream());  
			
			dout.writeInt(b.length);
			
			for(int j=0;j<iteration;j++){	
				dout.write(b);
			}
			
		//	dout.flush();
			s.close();
		}catch(Exception e){
					System.out.println(e);
		}
	}
	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
	//	int port1,port2;
	//	port1=Integer.parseInt(args[0]);
	//	port2=Integer.parseInt(args[1]);;
	
		MultiTcpServer t1=new MultiTcpServer(5000);
		MultiTcpServer t2=new MultiTcpServer(6000);
		
	//	System.out.println("port :"+port);
		
	/*	System.out.println("hi......");
		
		ServerSocket server2=new ServerSocket(port2);
		
		System.out.println("hi......"+server2.getLocalPort());
		
		 s=server2.accept();
	*/	
		/*t1.start();
		t2.start();
		*/
		t1.join();
		t2.join();
		
	//	s.close();
		//s2.close();

	}

}
