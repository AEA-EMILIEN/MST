package run;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

import exception.VertexNotFoundException;
import graph.Graph;

public class Timer {

	public Algos alg;
	public Graph g;
	
	
	public Timer()
	{
		this.alg=new Algos();
		this.g=null;
	}
	
	public long timeGraph(int n,float p, int N)
	{
		long startTime = System.nanoTime();
		
		try {
			this.g = new Graph(n,p,N);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (VertexNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long endTime = System.nanoTime();

		long duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
		return duration/1000000; 
	}
	
	
	public void writeToFile(String filename,String data) 
	{
		try (Writer writer = new BufferedWriter(new OutputStreamWriter(
	            new FileOutputStream("data/"+filename), "utf-8"))) 
	    {
			writer.write(data);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void timeMST()
	{
		StringBuilder timeG = new StringBuilder();
		StringBuilder timePrim = new StringBuilder();
		StringBuilder timeKruskal = new StringBuilder();
		
		
		
		int min = 50;
		int max = 1000;
		int increment = 50;
		
		
		timeG.append(min+" ");
		timeG.append(max+" ");
		timeG.append(increment);
		timeG.append("\n");
		
		timePrim.append(min+" ");
		timePrim.append(max+" ");
		timePrim.append(increment);
		timePrim.append("\n");
		
		timeKruskal.append(min+" ");
		timeKruskal.append(max+" ");
		timeKruskal.append(increment);
		timeKruskal.append("\n");
		
		float[] p = {(float) 0.1,(float) 0.3,(float) 0.5,(float) .7,(float) .9};
		
		for(float i : p)
		{
			for(int j=min;j<=max;j+=increment)
			{
				System.out.println(j+" "+i);
				
				long meanG=0;
				long meanK=0;
				long meanP=0;
				for(int k=0;k<50;k++)
				{
					meanG+=timeGraph(j,i,100000);
					meanK+=alg.timeKruskal(this.g);
					meanP+=alg.timePrim(this.g);
				}
				timeG.append(meanG/50+" ");
				timePrim.append(meanP/50+" ");
				timeKruskal.append(meanK/50+" ");
			}
			timeG.append("\n");
			timePrim.append("\n");
			timeKruskal.append("\n");
		}
		
		System.out.println(timeG);
		System.out.println(timePrim);
		System.out.println(timeKruskal);
		
		writeToFile("generationGraphTime",timeG.toString());
		writeToFile("Prim",timePrim.toString());
		writeToFile("KruskalTime",timeKruskal.toString());
	}
	
	public void timeAndNumberColoration()
	{
		StringBuilder timeNaif = new StringBuilder();
		StringBuilder timeWP = new StringBuilder();
		StringBuilder timeDS = new StringBuilder();
		
		StringBuilder ColorNaif = new StringBuilder();
		StringBuilder ColorWP = new StringBuilder();
		StringBuilder ColorDS = new StringBuilder(); 
		
		Naif naif = new Naif();
		Dsatur ds = new Dsatur();
		WelshPowell wp = new WelshPowell();
		
		int min = 50;
		int max = 500;
		int increment = 50;
		
		
		timeNaif.append(min+" ");
		timeNaif.append(max+" ");
		timeNaif.append(increment);
		timeNaif.append("\n");
		
		timeWP.append(min+" ");
		timeWP.append(max+" ");
		timeWP.append(increment);
		timeWP.append("\n");
		
		timeDS.append(min+" ");
		timeDS.append(max+" ");
		timeDS.append(increment);
		timeDS.append("\n");
		
		timeDS.append(min+" ");
		timeDS.append(max+" ");
		timeDS.append(increment);
		timeDS.append("\n");
		
		
		float[] p = {(float) 0.1,(float) 0.3,(float) 0.5,(float) .7,(float) .9};
		
		for(float i : p)
		{
			for(int j=min;j<=max;j+=increment)
			{
				System.out.println(j+" "+i);
				
				long meanNaif=0;
				long meanDS=0;
				long meanWP=0;
				for(int k=0;k<50;k++)
				{
					timeGraph(j,i,100000);
					meanNaif+=naif.timeNaif(this.g);
					meanWP+=wp.timeWP(this.g);
					meanDS+=ds.timeDS(this.g);
				}
				timeNaif.append(meanNaif/50+" ");
				timeWP.append(meanWP/50+" ");
				timeDS.append(meanDS/50+" ");
			}
			timeNaif.append("\n");
			timeWP.append("\n");
			timeDS.append("\n");
		}
		
		System.out.println(timeNaif);
		System.out.println(timeWP);
		System.out.println(timeDS);
		
		writeToFile("TimeColorationNaif",timeNaif.toString());
		writeToFile("TimeColorationWelshPowell",timeWP.toString());
		writeToFile("TimeColorationDsatur",timeDS.toString());
	}
	

	
}
