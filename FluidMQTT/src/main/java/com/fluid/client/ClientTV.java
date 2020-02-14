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
public class ClientTV implements FluidListener{
	public static final String id = "";
	private static ClientTV fan = new ClientTV();
	public static final String VIEW_NAME ="TV View";
	private ClientTV() {
		
	}
	
	public static ClientTV getClientTV() {
		return fan;
	}
}
