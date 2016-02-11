import java.util.Scanner;

import javax.print.attribute.IntegerSyntax;


public class samplesModule extends Thread {
	static long startTime;
	static long startTime1;
	static long endTime;
	static long endTime1;
	static int threads;
	static char flag;
	static long iteration=1000000000;
	int a,b,c,d;
	float e=10,f=30,g=20,h=40;
	/**
	 * @param args
	 */
	@Override
			public void run() {
			// TODO Auto-generated method stub
			if(flag=='i'){
				for(int i=0;i<(iteration/threads);i++){
					a=a*b;
					b=d-a;
					c=d*a;
					a=a*b;
					b=d-a;
					c=d*a;
					a=a*b;
				/*	b=d-a;
					c=d*a;
					c=d*a;
					a=a*b;
					b=d-a;
					c=d*a;
					a=a*b;
					b=d-a;
					c=d*a;
					a=a*b;
					b=d-a;
					c=d*a;
					c=d*a;*/
				}	
			}
			else if(flag=='f'){	
				for(int i=0;i<(iteration/threads);i++){
					e=e+f;
					f=h+e;
					g=h*e;
					h=e+h;
					g=g-e;
					e=e-10;
					f=h-10;
				/*	g=h*e;
					h=e+h;
					g=g-e;
					e=e*f;
					f=h-e;
					g=h*e;
					h=e+h;
					g=g-e;
					e=e*f;
					f=h-e;
					g=h*e;
					h=e+h;
					g=g-e;*/	
				}
			}
		}

		public static void main(String[] args) throws InterruptedException {
			// TODO Auto-generated method stub
	//	Scanner s=new Scanner(System.in);
	    
	//	System.out.println("Enter No. of Threads:");
		threads=4;
		//s.nextInt();
		
		samplesModule[] t1=new samplesModule[threads];
		
		flag = 'i';
		startTime = System.currentTimeMillis();
			 
			 for (int i=0;i<threads;i++)
			 {
				 t1[i]=new samplesModule();
				 t1[i].start();
			 }
			 
			 for (int i=0;i<threads;i++)
			 {
				 t1[i].join();
			 }		 
				
		endTime = System.currentTimeMillis();
		
		long estimatedTime=endTime-startTime;

		System.out.println("Iops estimated time: "+estimatedTime);
		
		double giops1= ((iteration*1000*7*5)/(estimatedTime));
		double giops= (giops1/1000000000);
		
		System.out.println("GIOPS : "+giops);

		samplesModule[] t2=new samplesModule[threads];
		flag='f';
		startTime1= System.currentTimeMillis();
			
			 for (int i=0;i<threads;i++)
			 {
				 t2[i]=new samplesModule();
				 t2[i].start();
			 }
			 
			 for (int i=0;i<threads;i++)
			 {
				 t2[i].join();
			 }		 
				
		endTime1= System.currentTimeMillis();

		long estimatedTime1=endTime1-startTime1;
		System.out.println("flops estimated time:"+estimatedTime1);

		double flops1= ((iteration*1000*5*5)/(estimatedTime1));
		double flops= (flops1/1000000000);
		
		System.out.println("GFLOPS : "+flops);

		}
	}
