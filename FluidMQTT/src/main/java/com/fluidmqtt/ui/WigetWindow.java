/**
 * 
 */
package com.fluidmqtt.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.fluid.client.ClientFan;
import com.fluid.client.ClientLight;
import com.fluid.client.ClientTV;
import com.fluidmqtt.service.FluidListener;

import net.miginfocom.swing.MigLayout;

/**
 * @author mazhar
 *
 *         Feb 3, 2020
 */
public class WigetWindow extends JFrame {

	private JButton tv;
	private JButton fan;
	private JButton light;
	private JPanel mainPanel;
	private FluidListener listener;
	private ClientView view;
	private ClientTV clientTv;
	private ClientFan clientFan;
	private ClientLight clientLight;
    private static WigetWindow wigetWindow = new WigetWindow();
	private WigetWindow() {
		initDialog();
		this.setTitle("Fluid's Wiget");
		setIconImage(new javax.swing.ImageIcon(getClass().getResource("/icon.png")).getImage());
		clientTv = ClientTV.getClientTV();
		clientFan = ClientFan.getClientFan();
		clientLight = ClientLight.getClientLight();
	}
    public static WigetWindow getWigets() {
    	return wigetWindow;
    }
	/**
	 * 
	 */
	private void initDialog() {
		mainPanel = new JPanel(new GridLayout(1, 0, 0, 0));
		mainPanel.add(customPanel("TV"));
		mainPanel.add(customPanel("Fan"));
		mainPanel.add(customPanel("Light"));
		mainPanel.setBackground(Color.green);
		this.add(mainPanel);

		this.setSize(500, 200);
		this.setAlwaysOnTop(true);

		this.setLocationRelativeTo(null);
		pack();
		

	}

	private JButton createImageButton(String name) {
		JButton button = new JButton();
		button.setOpaque(true);
		button.setPreferredSize(new Dimension(140, 140));
		button.setFocusable(false);
		button.setFont(new Font("", Font.BOLD, 24));
		button.setBackground(Color.WHITE);
		button.setText(name);
		button.setName(name);
		button.setBorder(BorderFactory.createLineBorder(Color.green, 2));
		button.addActionListener(e -> wigetPressed(e, button));
		return button;

	}

	/**
	 * @return
	 */
	private void wigetPressed(ActionEvent e, JButton button) {

		String name = ((JComponent) e.getSource()).getName();
		switch (name) {
		
		case "TV":
			view = new ClientView(clientTv);
			windowAction(button);
			break;
			
		case "Fan":
			view = new ClientView(clientFan);
			windowAction(button);
			break;
			
		case "Light":
			view = new ClientView(clientLight);
			windowAction(button);
			break;
		}

	}

	private void windowAction(JButton button) {
		view.addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {
                System.out.println("window is open");
                button.setEnabled(false);
			}

			@Override
			public void windowIconified(WindowEvent e) {
				System.out.println("window is iconfied");

			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				System.out.println("window is deiconfied");

			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				System.out.println("window is deactivated");
				//button.setEnabled(true);

			}

			@Override
			public void windowClosing(WindowEvent e) {
				System.out.println("window is closing");
				 button.setEnabled(true);

			}

			@Override
			public void windowClosed(WindowEvent e) {
				System.out.println("window is closed");
				 ///button.setEnabled(true);

			}

			@Override
			public void windowActivated(WindowEvent e) {
				System.out.println("window is activated");
				button.setEnabled(false);

			}
		});
	}

	private JPanel customPanel(String name) {
		JPanel panel = new JPanel(new MigLayout("insets 10 10 10 10"));
		panel.setPreferredSize(new Dimension(150, 150));
		panel.setBorder(BorderFactory.createLineBorder(Color.yellow));
		panel.setBackground(Color.red);
		panel.add(createImageButton(name));
		return panel;

	}

}
