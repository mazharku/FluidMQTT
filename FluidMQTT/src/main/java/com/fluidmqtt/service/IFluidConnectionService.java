/**
 * 
 */
package com.fluidmqtt.service;

/**
 * @author mazhar
 *
 *         Feb 9, 2020
 */
public interface IFluidConnectionService {

	public boolean connectionEstablised();

	public void reConnect();

	public boolean connectionLost();

}
