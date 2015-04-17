package run;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import exception.VertexNotFoundException;
import graph.Graph;

public class Timer {

	public Algos alg;
	public Graph g;
	public String timeG;
	
	public Timer(int n, float p, int N) throws IllegalArgumentException, VertexNotFoundException
	{
		this.timeG = timeGraph(n,p,N);
		alg = new Algos();
		
	}
	
	public Timer()
	{
		this.alg=new Algos();
		this.timeG="";
		this.g=null;
	}
	
	public String timeGraph(int n,float p, int N)
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
		return duration/1000000+""; 
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
				timeG.append(timeGraph(j,i,100000)+" ");
				timePrim.append(alg.meanTimePrim(g)+" ");
				timeKruskal.append(alg.meanTimeKruskal(g)+" ");
			}
			timeG.append("\n");
			timePrim.append("\n");
			timeKruskal.append("\n");
		}
		
		System.out.println(timeG);
		System.out.println(timePrim);
		System.out.println(timeKruskal);
		
		writeToFile("generationGraphTime",timeG.toString());
		writeToFile("Prim",timeG.toString());
		writeToFile("KruskalTime",timeG.toString());
	}
	

	
}
