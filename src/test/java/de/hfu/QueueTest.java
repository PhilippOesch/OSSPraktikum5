package de.hfu;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class QueueTest {
	private Queue queue;
	
	@Before
	public void createQueue() {
		queue= new Queue(3);
	} 
	
	@Test
	public void testEnqueue(){
		final int soll = 1; 
		queue.enqueue(1);
		assertEquals(soll, queue.queue[queue.head]);
	}

	@Test
	public void testQueueFull() {
		final int soll = 14; 
		queue.enqueue(10);
		queue.enqueue(12);
		queue.enqueue(13);
		queue.enqueue(14);
		assertEquals(soll, queue.queue[queue.tail]);
	}
	
	@Test
	public void testDequeue() {
		final int soll = 1;
		queue.enqueue(1);
		queue.enqueue(2);
		queue.enqueue(3);
		assertEquals(soll, queue.dequeue());
		assertEquals(2, queue.queue[queue.head]);
	}
	
	@Test(expected= IllegalStateException.class, timeout= 1000)
	public void testEmptyDequeue() {
		queue.dequeue();
	}

}
