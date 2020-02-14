/**
 * 
 */
package com.fluid.client;

import com.fluidmqtt.service.FluidListener;

/**
 * @author mazhar
 *
 * Feb 7, 2020
 */
public class ClientFan implements FluidListener {
   
	public static final String id = "";
	public static final String VIEW_NAME ="Fan View";
	
	private static ClientFan fan = new ClientFan();
	
	private ClientFan() {
		
	}
	
	public static ClientFan getClientFan() {
		return fan;
	}
}
