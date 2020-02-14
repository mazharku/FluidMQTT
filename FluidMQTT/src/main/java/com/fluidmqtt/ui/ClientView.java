/**
 * 
 */
package com.fluidmqtt.ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.fluid.client.ClientFan;
import com.fluid.client.ClientLight;
import com.fluid.client.ClientTV;
import com.fluidmqtt.service.FluidListener;

/**
 * @author mazhar
 *
 * Feb 3, 2020
 */
public class ClientView extends JFrame{
	private FluidListener listener;
	private JLabel monitor = new JLabel();
	
	
    public ClientView(FluidListener listener) {
    	initView();
    	setIconImage(new javax.swing.ImageIcon(getClass().getResource("/icon.png")).getImage());
    	if( listener instanceof ClientTV) {
    		setTitle(ClientTV.VIEW_NAME);
    		this.setLocation(10, 10);
    		
    		monitor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tv.gif")));
    	}
    	else if(listener instanceof ClientLight) {
    		setTitle(ClientLight.VIEW_NAME);
    		this.setLocation(260, 10);
    		
    		monitor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/light220.gif")));
    	}
    	else {
    		setTitle(ClientFan.VIEW_NAME);
    		this.setLocation(10, 360);
    		
    		monitor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fan220.gif")));
    	}
    	
    	this.setVisible(true);
    }

	/**
	 * 
	 */
	private void initView() {
		JPanel mainPanel= new JPanel(new BorderLayout());
		mainPanel.setPreferredSize(new Dimension(220, 300));
		JPanel view = new JPanel(new CardLayout());
		JPanel bottom = new JPanel(new FlowLayout());
		bottom.setPreferredSize(new Dimension(220, 80));
		bottom.setBackground(Color.gray);
		//bottom.setBorder(BorderFactory.createLineBorder(Color.white,10));
		mainPanel.add(view, BorderLayout.CENTER);
		mainPanel.add(bottom, BorderLayout.PAGE_END);
		view.add(getView());
		this.add(mainPanel);
		this.setResizable(false);
		
		pack();
		
	}
	
	private JPanel getView() {
		JPanel panel = new JPanel(new CardLayout());
		panel.setBorder(BorderFactory.createLineBorder(Color.gray,10));
		panel.setBackground(Color.green);
		panel.setOpaque(true);
		panel.add(monitor);
		return panel;
	}
   
}
