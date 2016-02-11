import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class TcpServer extends Thread {

	int iteration=1000;
	
	/**
	 * @param args
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public void run(){
		
		
		try{
			ServerSocket server=new ServerSocket(6363);
			
			Socket s=server.accept();
			DataInputStream din=new DataInputStream(s.getInputStream());
			
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
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		// TODO Auto-generated method stub
		TcpServer t1=new TcpServer();
		t1.start();
/*	try {
		t1.join();
		TcpServer t2=new TcpServer();
		t2.start();
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}*/
	}
}