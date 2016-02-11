import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Random;


public class Disk extends Thread{

	/**
	 * @param args
	 */
	static FileChannel readChannel,writeChannel;
	static ByteBuffer byteBuf;
	static int threads,bufferSize;
	static RandomAccessFile ReadFile,WritFile;
	static double seqWTime,seqRTime,randWTime,randRTime;
	static int iteration=1000;
	static double throughput;
	static double latency;
	static byte[] bArray;
	static Disk diskObj;
	
	public void run(){
		 diskObj=new Disk();
		 
		 System.out.println("buffersize :"+bufferSize);
		try {
			ReadFile = new RandomAccessFile("ReadOutput", "rw");
			readChannel = ReadFile.getChannel();
            
			WritFile = new RandomAccessFile("WriteInput", "rw");
			writeChannel = WritFile.getChannel();
            
			byteBuf = ByteBuffer.allocate(bArray.length);
	           
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			seqWTime=diskObj.seqWrite(bArray);
			seqRTime=diskObj.seqRead(bArray);
			randRTime=diskObj.randRead(bArray);
			randWTime=diskObj.randWrite(bArray);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		threads=Integer.parseInt(args[0]);
		bufferSize=Integer.parseInt(args[1]);
		System.out.println("size of byte : 1Byte 1024Byte 65536Byte :---> "+bufferSize);
		
		bArray = new byte[bufferSize];
		
		for(int i=0;i<bufferSize;i++)
		{
			bArray[i]=(byte)i;
		}
		
		Disk []t1=new Disk[threads];
		
		for(int i=0;i<threads;i++){
			t1[i]=new Disk();
			t1[i].start();
		}
		
		for(int j=0;j<threads;j++){
			t1[j].join();
		}
		
		seqWTime=seqWTime/1000000;
		throughput=(bufferSize*iteration*1000)/(seqWTime*1024*1024);
		latency=seqWTime*bufferSize/iteration;
		
		System.out.println("Sequential Write Time :"+seqWTime+" ms");
		
		System.out.println("Sequential Write Throughput :"+throughput);
		System.out.println("Sequential Write Latency "+latency);
		
		System.out.println("\n");
		
		seqRTime=seqRTime/1000000;
		throughput=(bufferSize*iteration*1000)/(seqRTime*1024*1024);
		latency=seqRTime*bufferSize/iteration;
		
		System.out.println("Sequential Read Time :"+seqRTime);
			
		System.out.println("Sequential Read Throughput :"+throughput);
		System.out.println("Sequential Read Latency "+latency);

		System.out.println("\n");

		randWTime=randWTime/1000000;
		throughput=(bufferSize*iteration*1000)/(randWTime*1024*1024);
		latency=randWTime*bufferSize/iteration;
		
		System.out.println("Random Write Time :"+randWTime);
		System.out.println("Random Write Throughput :"+throughput);
		System.out.println("Random Write Latency"+latency);
		
		System.out.println("\n");

		randRTime=randRTime/1000000;
		throughput=(bufferSize*iteration*1000)/(randRTime*1024*1024);
		latency=randRTime*bufferSize/iteration;
		
		System.out.println("Random Read Time :"+randRTime);
			
		
		System.out.println("Random Read Throughput :"+throughput);
		System.out.println("Random Read Latency"+latency);
		
	}
    public int random(int i) {
        Random rand = new Random();
        int randNum = rand.nextInt(i);
        return randNum;
    }

	public double seqWrite(byte[] bArray2) throws IOException{	
		WritFile.seek(0);
		double startTime=System.nanoTime();
		
		for(int i=0;i<iteration;i++){
			WritFile.write(bArray2, 0,bufferSize);
		}
		
		double endTime=System.nanoTime();
		double elapsedTime=endTime-startTime;
		return elapsedTime;
	}
	
	
	public double seqRead(byte[] bArray2) throws IOException{
		ReadFile.seek(0);
		double startTime=System.nanoTime();
		for(int i=0;i<iteration;i++){
			ReadFile.read(bArray2, 0,bufferSize-1);
		}
		double endTime=System.nanoTime();
		double elapsedTime=endTime-startTime;
		return elapsedTime;
		}
	
	public double randWrite(byte[] bArray2) throws IOException{
		double startTime=System.nanoTime();
		
		for (int i = 0; i < iteration; i++) {
            int rn = diskObj.random(bufferSize);
            writeChannel.position(rn);
            while(byteBuf.hasRemaining())
            {
            	writeChannel.write(byteBuf);
            }
          	
		}
		double endTime=System.nanoTime();
		
		double elapsedTime=endTime-startTime;
		return elapsedTime;
	}
	
	
	public double randRead(byte[] bArray2) throws IOException{
		double startTime=System.nanoTime();
		
		for (int i = 0; i < iteration; i++) {
            int rn = diskObj.random(bufferSize);
            readChannel.position(rn);
            while(byteBuf.hasRemaining())
            {
            	readChannel.read(byteBuf);
            }
          	
		}
		double endTime=System.nanoTime();
		
		double elapsedTime=endTime-startTime;
		return elapsedTime;
	
	}
}
