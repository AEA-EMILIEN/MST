package heap;

import java.util.List;

import graph.Vertex;

/**
 * 
 * Implementation of a d-ary min Heap
 * 
 * d is the number of children max of each node in the heap
 * 
 */
public class Heap {

	
	public Vertex[] tab;
	public int d;
	public int size;
	public int indexLastElement;
	
	public Heap()
	{
		this.tab=null;
		this.d=0;
		this.size=0;
		this.indexLastElement=-1;
	}
	
	public Heap(int size,int d)
	{
		this.tab=new Vertex[size];
		this.d=d;
		this.size=size;
		this.indexLastElement=-1;
	}
	
	/**
	 * rebalancing of the heap for one element at the index passed in parameter
	 * @param index
	 */
	public void min_heapify(int index)
	{/*
		Max-Heapify (A, i):
			 left ← 2i
			 right ← 2i + 1
			 largest ← i
			 if left ≤ heap_length[A] and A[left] > A[largest] then:
			 largest ← left
			 if right ≤ heap_length[A] and A[right] > A[largest] then:
			 largest ← right
			 if largest ≠ i then:
			 swap A[i] ↔ A[largest]
			 Max-Heapify(A, largest)
	  */
		
		int children[] = new int[this.d];
		for(int i=0;i<d;i++)
			children[i]=this.d*index + i+1;
		int tiniest = index;
		
		for(int i=0;i<d;i++)
			if(children[i] <= this.indexLastElement && this.tab[children[i]].weight<this.tab[tiniest].weight)
				tiniest = children[i];
		
		if(tiniest!=index)
		{
			swap(index,tiniest);
			
			min_heapify(tiniest);
		}
		return;
	}
	
	// swap 2 elements of tab
	public void swap(int e1, int e2)
	{
		Vertex e = this.tab[e1];
		this.tab[e1] = this.tab[e2];
		this.tab[e2] = e;
	}
	
	public void delete()
	{
		/*
	    Replace the root of the heap with the last element on the last level.
	    Compare the new root with its children; if they are in the correct order, stop.
	    If not, swap the element with one of its children and return to the previous step. (Swap with its smaller child in a min-heap and its larger child in a max-heap.)
		*/
		this.tab[0]=null;
		swap(0,this.indexLastElement);
		this.indexLastElement--;
		this.min_heapify(0);
	}

	//return the index of the father of the index in parameter
	public int father(int index)
	{
		if(index>0)
			return (int) Math.floor((index-1)/this.d);
		else
			return 0;
	}

	public void insert(Vertex e)
	{
		/*
	    Add the element to the bottom level of the heap.
	    Compare the added element with its parent; if they are in the correct order, stop.
	    If not, swap the element with its parent and return to the previous step.
		*/
		
		this.indexLastElement++;
		if(this.indexLastElement<this.size)
		{
			this.tab[this.indexLastElement]=e;
			
			int ind = this.indexLastElement;
			while(ind>0 && e.weight<this.tab[father(ind)].weight)
			{
				swap(this.indexLastElement,father(ind));
				ind = father(ind);
			}
		}
		else
		{
			this.indexLastElement--;
			System.out.println("trop d'element dans le tas!");
		}
	}
	
	/*
	necessite de creer Heap avec l.size() et un d dans le constructeur!
	exemple : Heap h = new Heap(l.size(),4)
	et ensuite seulement build_heap(l);
	
	construire le tas comme ça est censé être plus performant que en utilisant des appels successifs a insert.
	*/
	public void build_heap(List<Integer> l)
	{
		/*
		Build-Max-Heap (A):
 		heap_length[A] ← length[A]
		for i ← floor(length[A]/2) downto 1 do
		Max-Heapify(A, i)
		*/
		for(int i=0;i<this.size;i++)
			this.tab[i] = new Vertex(l.get(i),i);
		
		this.indexLastElement=this.size-1;
		
		for(int i=this.indexLastElement;i>=0;i--)
			this.min_heapify(i);
	}
	
	public void build_heap_prim(List<Integer> l)
	{
	
		for(int i=0;i<this.size;i++)
			this.tab[i] = new Vertex(l.get(i),Integer.MAX_VALUE);
		this.tab[0].weight=0;
		
		this.indexLastElement=this.size-1;
	}
	
	
	public Vertex extract_min()
	{
		Vertex res = this.tab[0];
		delete();
		return res;
	}
	
	
	@Override
	public String toString()
	{	if(this.indexLastElement<0)
			return "";
		return this.toString(0,0);
	}
	
	
	public String toString(int index,int level)
	{
		StringBuilder sb = new StringBuilder();
		
		for(int i=0;i<level;i++)
			sb.append(" ");
		
		sb.append(this.tab[index].weight);
		sb.append("\n");
		
		for(int i=0;index*this.d+i+1<=this.indexLastElement && i<this.d;i++)
		{
			sb.append(this.toString(index*this.d+i+1,level+1));
		}		
		
		return sb.toString();
		
	}

	public boolean isEmpty() {
	
		return this.indexLastElement<0;
	}

	
	public Vertex get(int v) {
		
		for(int i=0;i<=this.indexLastElement;i++)
			if(this.tab[i].id==v)
				return this.tab[i];
		return null;
	}
	
	public int getIndice(int v) {
		
		for(int i=0;i<=this.indexLastElement;i++)
			if(this.tab[i].id==v)
				return i;
		return -1;
	}
	
	public void set(int v,int weight,int pere)
	{
		
		for(int i=0;i<=this.indexLastElement;i++)
			if(this.tab[i].id==v)
			{
				this.tab[i].weight=weight;
				this.tab[i].pere=pere;
				
				int oldFatherVal=0;
				int oldFatherInd=father(i);
				int newFatherVal=1;
				
				while(oldFatherVal!=newFatherVal)
				{
					oldFatherVal=this.tab[father(i)].weight;
					min_heapify(father(i));
					newFatherVal=this.tab[father(i)].weight;
					i=father(i);
					
				}
				
				break;
			}
	}
}