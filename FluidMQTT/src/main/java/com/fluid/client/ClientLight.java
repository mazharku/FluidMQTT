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
public class ClientLight implements FluidListener {
	public static final String id = "";
	public static final String VIEW_NAME ="Light View";
	private static ClientLight fan = new ClientLight();
	
	private ClientLight() {
		
	}
	
	public static ClientLight getClientLight() {
		return fan;
	}
}
