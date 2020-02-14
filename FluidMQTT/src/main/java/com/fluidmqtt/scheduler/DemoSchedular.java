/**
 * 
 */
package com.fluidmqtt.scheduler;

import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author mazhar
 *
 *         Feb 11, 2020
 */
public class DemoSchedular {
	
	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args)  {
		new Thread(()-> {
			ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
			scheduler.scheduleAtFixedRate(new threadRun(), 8, 8, TimeUnit.SECONDS);
		}).start();
		
		
			for (int i = 0; i < 50; i++) {
				System.out.println("outer " + i);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		

	}

	private static class threadRun extends TimerTask{

		@Override
		public void run() {
			new DemoClass();
		}
		
	}
	
}
