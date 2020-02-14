/**
 * 
 */
package com.fluidmqtt.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

import com.fluidmqtt.core.FluidConnectionOld;
import com.fluidmqtt.service.IFluidConnectionService;

import net.miginfocom.swing.MigLayout;

/**
 * @author mazhar
 *
 *         Feb 2, 2020
 */
public class ConnectionWindow extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField connectionUrl;
	private static ConnectionWindow connectionWindow = new ConnectionWindow();
	private MqttClient client;
	private boolean isConnectionON = false;

	private ConnectionWindow() {
		initUI();
		this.setTitle("Fluid Connection");
		setIconImage(new javax.swing.ImageIcon(getClass().getResource("/icon.png")).getImage());

	}

	public static ConnectionWindow getConnectionWindow() {
		return connectionWindow;
	}

	private void cancelCall() {
		connectionUrl.setText("");
		this.dispose();
	}

	private void initUI() {
		JPanel mainPanel = new JPanel(new MigLayout("insets 10 10 10 10"));
		connectionUrl = new JTextField();
		JLabel label = new JLabel();
		JButton clearBtn = new JButton();
		JButton connectBtn = new JButton();
//sizing		
		connectionUrl.setPreferredSize(new Dimension(220, 40));
		// label.setPreferredSize(new Dimension(60,40));
		clearBtn.setPreferredSize(new Dimension(120, 30));
		connectBtn.setPreferredSize(new Dimension(140, 30));
//text set		
		connectionUrl.setText("tcp://localhost:1883");
		label.setText(" TCP/SSL Broker Address:");
		clearBtn.setText("Clear");
		connectBtn.setText("Connect");
//color set		
		label.setBackground(Color.white);
		clearBtn.setBackground(Color.orange);
		connectBtn.setBackground(Color.green);

		clearBtn.setForeground(Color.white);
		connectBtn.setForeground(Color.white);
//aditional	
		label.setFont(new Font("Helvetica", Font.BOLD, 14));
		label.setOpaque(false);
		clearBtn.setOpaque(true);
		clearBtn.setFocusable(false);
		connectBtn.setOpaque(true);
		connectBtn.setFocusable(false);
//add to panel		
		mainPanel.add(label, "center,grow");
		mainPanel.add(connectionUrl, "grow, wrap");
		mainPanel.add(clearBtn, "gap 50");
		mainPanel.add(connectBtn, "gap 5");
//		actionListner

//		frame attribute
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.add(mainPanel);
		this.setAlwaysOnTop(true);
		pack();

		connectBtn.addActionListener(e -> connectMe());
		clearBtn.addActionListener(e -> cancelCall());
	}

	/**
	 * @return
	 */
	private void connectMe() {
		String url = connectionUrl.getText();
		if (url == null || url.isEmpty()) {
			return;
		}
		MqttClient client = FluidConnectionOld.getSyncConnection(url);
		if (client.isConnected()) {
			try {
				client.disconnect();
				isConnectionON = false;
			} catch (MqttException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			try {
				client.connect();
				isConnectionON = true;
			} catch (MqttException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	/**
	 * @return the isConnectionON
	 */
	public boolean isConnectionON() {
		return isConnectionON;
	}

	/**
	 * @param isConnectionON the isConnectionON to set
	 */
	public void setConnectionON(boolean isConnectionON) {
		this.isConnectionON = isConnectionON;
	}

}
