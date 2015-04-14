package heapTest;

import static org.junit.Assert.*;
import graph.Vertex;
import heap.Heap;

import org.junit.Assert;
import org.junit.Test;

public class HeapTest {

	@Test
	public void testHeap() {
		Heap h = new Heap();
		assertTrue(h.d==0);
		assertNull(h.tab);
		assertTrue(h.indexLastElement==-1);
		assertTrue(h.size==0);
	}

	@Test
	public void testHeapIntInt() {
		Heap h = new Heap(16,4);
		assertEquals(4, h.d);
		assertEquals(-1, h.indexLastElement);
		assertEquals(16,h.size);
		assertNull(h.tab[0]);
	}



	@Test
	public void testSwap() {
		Heap h = new Heap(10, 4);
		h.tab[0] = new Vertex(1,10);
		h.tab[1] = new Vertex(2,5);

		h.swap(0,1);
		
		assertEquals(5,h.tab[0].weight);
		assertEquals(10,h.tab[1].weight);
		
		h.swap(0, 1);
		
		assertEquals(10,h.tab[0].weight);
		assertEquals(5,h.tab[1].weight);
		
	}

	@Test
	public void testDelete() {
		Heap h = new Heap(10,4);
		
		h.insert(new Vertex(0,1));
		h.insert(new Vertex(0,2));
		h.insert(new Vertex(2,0));
		h.insert(new Vertex(0,3));
		h.insert(new Vertex(0,4));
		h.insert(new Vertex(2,5));
		h.insert(new Vertex(2,6));
		h.insert(new Vertex(2,7));
		
		assertEquals(0,h.tab[0].weight);
		assertEquals(7,h.indexLastElement);
		
		h.delete();
		
		assertEquals(1,h.tab[0].weight);
		assertEquals(6,h.indexLastElement);
		
	}

	@Test
	public void testFather() {
		Heap h = new Heap(10,4);
		assertEquals(0,h.father(0));
		
		assertEquals(0,h.father(1));
		assertEquals(0,h.father(2));
		assertEquals(0,h.father(3));
		assertEquals(0,h.father(4));
		
		assertEquals(1,h.father(5));
		assertEquals(1,h.father(8));
		
		assertEquals(2,h.father(9));
		assertEquals(2,h.father(12));
		
	}

	@Test
	public void testInsert() {
		Heap h = new Heap(5,4);
	
		h.insert(new Vertex(0,1));
		
		assertEquals(1,h.tab[0].weight);
		assertNull(h.tab[1]);
		assertEquals(0,h.indexLastElement);
		
		h.insert(new Vertex(1,2));
		
		assertEquals(1,h.tab[0].weight);
		assertEquals(2,h.tab[1].weight);
		assertNull(h.tab[2]);
		assertEquals(1,h.indexLastElement);
		
		h.insert(new Vertex(1,0));
		
		assertEquals(0,h.tab[0].weight);
		assertEquals(1,h.tab[2].weight);
		assertEquals(2,h.tab[1].weight);
		assertNull(h.tab[3]);
		assertEquals(2,h.indexLastElement);
		
	}

	
}
