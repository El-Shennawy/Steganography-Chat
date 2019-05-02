import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

public class Client1gui {
	
	JFrame x = new JFrame();
	Client c1;
	
	public Client1gui() throws Exception {
		
		
		
		
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					c1 = new Client();
					x.add(c1.chat);
					System.out.println("clientttt");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		});
		thread.start();
					
		this.helper();
	}
	
	public void helper() throws Exception {
		
		
		JTextPane txtpane = new JTextPane();
		JScrollPane jsp = new JScrollPane(txtpane);
		x.setSize(800, 800);
		JButton run = new JButton();
		JTextArea msg = new JTextArea();
		run.setText("Send");
		x.setLayout(null);
		msg.setBounds(50, 650, 150, 50);
		run.setBounds(250, 650, 100, 50);
		jsp.setBounds(50, 50, 400, 400);
		x.add(msg);
		x.add(run);
		x.add(jsp);
		x.revalidate();
		x.setVisible(true);		// c1.sendmsg(msg.getText());
		// while(true) {
		// c1.chat=txtpane;
		// c1.msg=msg;
		// }

		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {	
					while(true)
						if(!txtpane.getText().equals(c1.chat.getText()))
							txtpane.setText(c1.chat.getText());
	
			}
			
		});
		thread.start();
		
		run.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					System.out.println(msg.getText());
					txtpane.setText(c1.chat.getText());
					c1.sendmsg(msg.getText()+'\n');
					
				} catch (IOException excep) {
					// TODO Auto-generated catch block
					excep.printStackTrace();
				}

			}

		});
	}
	
	public static void main(String[] args) throws Exception {
		Client1gui x = new Client1gui();
	}
}
