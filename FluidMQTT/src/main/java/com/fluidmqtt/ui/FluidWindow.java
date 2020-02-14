/**
 * 
 */
package com.fluidmqtt.ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

import com.fluidmqtt.core.FluidConnection;
import com.fluidmqtt.core.FluidConnectionOld;
import com.fluidmqtt.util.FluidMessages;

import net.miginfocom.swing.MigLayout;

/**
 * @author mazhar
 *
 *         Feb 3, 2020
 */
public class FluidWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton clear;
	private JButton wiget;
	private JButton connection;
	private JToggleButton tv;
	private JToggleButton fan;
	private JToggleButton light;
	private ConnectionWindow conn = null;
	public JLabel statusLabel;
	private JTextArea display;
	public JPanel layer;
	MqttClient client = FluidConnectionOld.getSyncConnection("tcp://localhost:1883");

	public FluidWindow() {
		initUI();
		UpdateView();
		this.setTitle("Fluid Display");
		setIconImage(new javax.swing.ImageIcon(getClass().getResource("/icon.png")).getImage());
	}

	/**
	 * 
	 */
	private void initUI() {
		JPanel mainPanel = new JPanel(new BorderLayout());
		JPanel topPanel = new JPanel(new BorderLayout(10, 10));
		JPanel statusPanel = new JPanel();
		JPanel bottomPanel = new JPanel(new FlowLayout());
		JPanel centerPanle = new JPanel(new CardLayout());
		JPanel header = new JPanel(new MigLayout());
		JPanel buttonPanel = new JPanel(new FlowLayout());
//toggle button make		
		tv = createToggoleButton(tv, "TV");
		fan = createToggoleButton(fan, "Fan");
		light = createToggoleButton(light, "Light");
//button create		
		clear = createButton(clear, "Clear");
		clear.setPreferredSize(new Dimension(120, 35));
		clear.setBackground(Color.orange);

		wiget = createButton(wiget, "Wiget");
		wiget.setPreferredSize(new Dimension(140, 40));
		wiget.setBackground(Color.GREEN);

		connection = createButton(connection, "Connection");
		connection.setPreferredSize(new Dimension(140, 40));
		connection.setBackground(Color.blue);

//		add button to header
		header.add(connection, "cell 0 0");
		header.add(wiget, "pos 98%-pref 0.5al");

		topPanel.add(header, BorderLayout.NORTH);
		topPanel.add(getStatusBar(), BorderLayout.SOUTH);

		centerPanle.setBackground(Color.white);
		centerPanle.setBorder(BorderFactory.createLineBorder(Color.green, 2));

//panel size	

		mainPanel.setPreferredSize(new Dimension(600, 500));
		topPanel.setPreferredSize(new Dimension(600, 100));
		bottomPanel.setPreferredSize(new Dimension(600, 50));
		buttonPanel.setPreferredSize(new Dimension(600, 80));
		bottomPanel.add(clear);
		bottomPanel.add(fan);
		bottomPanel.add(tv);
		bottomPanel.add(light);

		buttonPanel.add(bottomPanel);
		centerPanle.add(createCenterPanel());
		mainPanel.add(topPanel, BorderLayout.PAGE_START);
		mainPanel.add(centerPanle, BorderLayout.CENTER);
		mainPanel.add(buttonPanel, BorderLayout.PAGE_END);

//		add Action listener for button
		wiget.addActionListener(e -> openWiget());
		clear.addActionListener(e -> clearDisplay());
		connection.addActionListener(e -> createConnection());

		this.add(mainPanel);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		pack();

	}

	/**
	 * @return
	 */
	private void createConnection() {
		conn = ConnectionWindow.getConnectionWindow();
		if (client.isConnected()) {
		       conn.setConnectionON(true);
				UpdateView();
		}
	}

	/**
	 * @return
	 */
	private void clearDisplay() {
		display.setText("");

	}

	/**
	 * @return
	 */
	private void openWiget() {
		WigetWindow wiget = WigetWindow.getWigets();
		if (wiget.isActive()) {
			return;
		} else {
			wiget.setVisible(true);
		}

	}

	private JButton createButton(JButton button, String name) {
		button = new JButton();
		button.setOpaque(true);
		button.setFocusable(false);
		button.setText(name);
		button.setFont(new Font("", Font.BOLD, 16));
		button.setForeground(Color.white);
		return button;
	}

	private JToggleButton createToggoleButton(JToggleButton btn, String name) {
		btn = new JToggleButton();
		btn.setPreferredSize(new Dimension(80, 35));
		btn.setText(name);
		btn.setBackground(Color.white);
		btn.setOpaque(true);
		btn.setFocusable(false);
		return btn;
	}

	private JPanel getStatusBar() {
		JPanel status = new JPanel();
		layer = new JPanel(new FlowLayout());
		statusLabel = new JLabel();
		status.setPreferredSize(new Dimension(600, 50));
		layer.setPreferredSize(new Dimension(590, 40));
		layer.setBackground(Color.red);
		statusLabel.setForeground(Color.white);
		statusLabel.setFont(new Font("Helvetica", Font.BOLD, 24));
		statusLabel.setText(FluidMessages.NOT_CONNECTED);
		statusLabel.setOpaque(true);
		statusLabel.setBackground(Color.red);
		layer.add(statusLabel);
		status.add(layer);
		return status;
	}

	private JPanel createCenterPanel() {
		JPanel layer = new JPanel(new CardLayout());
		// JLayeredPane pane = new JLayeredPane();
		// layer.setBackground(Color.blue);
		// pane.setBackground(Color.white);
		// layer.add(pane);
		display = new JTextArea();
		display.setFont(new Font("Helvetica", Font.PLAIN, 14));
		display.setLineWrap(true);
		display.setEditable(false);
		display.setText(FluidMessages.INITIAL_DISPLAY_MESSAGE);
		layer.add(display);
		return layer;

	}

	private void UpdateView() {
		if (client.isConnected()) {
			statusLabel.setText(FluidMessages.CONNECTED);
			layer.setBackground(Color.green);
			statusLabel.setBackground(Color.green);
			connection.setText("Disconnect");
			connection.setBackground(Color.red);
		} else {
			statusLabel.setText(FluidMessages.NOT_CONNECTED);
			layer.setBackground(Color.red);
			statusLabel.setBackground(Color.red);
			connection.setText("Connect");
			connection.setBackground(Color.green);
		}

		repaint();
		revalidate();
	}

}
