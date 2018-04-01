package ocp2book;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LionPenManager {
	protected void removeAnimals() {
		System.out.println("removing animals...");
	}
	protected void cleanPen() {
		System.out.println("cleaning pen...");
	}
	protected void addAnimals() {
		System.out.println("adding animals...");
	}
	protected void manage(CyclicBarrier c1, CyclicBarrier c2) {
		try {
		removeAnimals();
		c1.await();
		cleanPen();
		c2.await();
		addAnimals();
		} catch(Exception e) {
			System.out.println("exception in manage");
			e.printStackTrace();
		}
	}
	public static void main(String [] args) {
		
		System.out.println("LionPenManager starting...");

		ExecutorService es = Executors.newFixedThreadPool(4);
		try {
			LionPenManager mgr = new LionPenManager();
			CyclicBarrier c1 = new CyclicBarrier(4);
			CyclicBarrier c2 = new CyclicBarrier(4,
					() -> System.out.println("=== pens cleaned! ==="));
			for(int i = 0; i < 4; i++) {
				es.submit(() -> mgr.manage(c1, c2));
			}
		} finally {
			if(es != null) {
				es.shutdown();
			}
		}
		
		System.out.println("LionPenManager done.");
		
	}
}
