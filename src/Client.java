import java.io.*; 
import java.net.*;

import javax.swing.JTextArea;
import javax.swing.JTextPane; 
class Client { 

	JTextPane chat = new JTextPane();
	JTextArea msg= new JTextArea();
	Socket mysoc ;
	public Client() throws Exception {
		//run();
		//Start();
		chat.setEditable(false);
		this.main(null);
		/*	BufferedReader inFromServer = 
				new BufferedReader(new
						InputStreamReader(mysoc.getInputStream())); 

		 Thread readMessage = new Thread(new Runnable() 
	        {
	            @Override
	            public void run() {
	                while (true) {
	                    try {
	                    	String	modifiedSentence = inFromServer.readLine(); 
	                        chat.setText(chat.getText()+'\n'+modifiedSentence);
	                		System.out.println("1 ya klb"+"FROM SERVER:"+ modifiedSentence);
	                    } catch (IOException e) {
	                        e.printStackTrace();
	                    }
	                }
	            }
	        });
	        readMessage.start();
		 */
	}



	//	public void mstny() throws IOException {
	//		 Thread readMessage = new Thread(new Runnable() 
	//	        {
	//	            @Override
	//	            public void run() {
	//	                while (true) {
	//	                    try {
	//	                    	BufferedReader inFromServer = 
	//	                				new BufferedReader(new
	//	                						InputStreamReader(mysoc.getInputStream())); 
	//	                    	String	modifiedSentence = inFromServer.readLine(); 
	//	                        chat.setText(chat.getText()+'\n'+modifiedSentence);
	//	                    } catch (IOException e) {
	//	                        e.printStackTrace();
	//	                    }
	//	                }
	//	            }
	//	        });
	//	        readMessage.start();
	//	}




	public void sendmsg(String x) throws IOException {

		DataOutputStream outToServer = 
				new DataOutputStream(mysoc.getOutputStream()); 
		//BufferedReader inFromUser = 
		//	new BufferedReader(new InputStreamReader(System.in)); 
		BufferedReader inFromServer = 
				new BufferedReader(new
						InputStreamReader(mysoc.getInputStream())); 
		chat.setText(chat.getText()+'\n'+x);
		outToServer.writeBytes(x +"***^&^"+'\n');

	}
	//	public void Start() throws UnknownHostException, IOException {
	//		Socket clientSocket = new Socket("Mostafa-Nasr", 6768); 
	//		mysoc=clientSocket;
	//	}
	//	
	//	public void run() throws UnknownHostException, IOException {
	//		String sentence; 
	//		String modifiedSentence; 
	//
	//		BufferedReader inFromUser = 
	//				new BufferedReader(new InputStreamReader(System.in)); 
	//
	//		Socket clientSocket = new Socket("Mostafa-Nasr", 6768); 
	//		DataOutputStream outToServer = 
	//				new DataOutputStream(clientSocket.getOutputStream()); 
	//		BufferedReader inFromServer = 
	//				new BufferedReader(new
	//						InputStreamReader(clientSocket.getInputStream())); 
	//		///////////////////////////////////////
	//		DataInputStream dis = new DataInputStream(clientSocket.getInputStream());
	//		   Thread readMessage = new Thread(new Runnable() 
	//	        {
	//	            @Override
	//	            public void run() {
	//	 
	//	                while (true) {
	//	                    try {
	//	                    	String	modifiedSentence = inFromServer.readLine(); 
	//	                        chat.setText(chat.getText()+'\n'+modifiedSentence);
	//	                		System.out.println("1 ya klb"+"FROM SERVER:"+ modifiedSentence);
	//	                    } catch (IOException e) {
	//	 
	//	                        e.printStackTrace();
	//	                    }
	//	                }
	//	            }
	//	        });
	//	        readMessage.start();
	//		///////////////////////////////////////
	//		while (true) {	
	//			
	//			sentence = inFromUser.readLine(); 
	//			String newsentence = msg.getText();
	//					
	//			chat.setText(chat.getText()+'\n'+sentence);
	//			outToServer.writeBytes(newsentence + '\n'); 
	//
	//		//	modifiedSentence = inFromServer.readLine(); 
	//
	//		//	System.out.println("FROM SERVER: "+ modifiedSentence); 
	//
	//			//clientSocket.close(); 
	//			//if (sentence.equals("bye")) {
	//			//	break;
	//			//}
	//		}
	//	} 
	//	
	public void main(String argv[]) throws Exception 
	{

		String sentence; 
		String modifiedSentence; 

		BufferedReader inFromUser = 
				new BufferedReader(new InputStreamReader(System.in)); 

		Socket clientSocket = new Socket("127.0.0.1", 6768); 
		mysoc = clientSocket;
		OutputStream o=  clientSocket.getOutputStream();
		DataOutputStream outToServer = 
				new DataOutputStream(o); 
		InputStream is = clientSocket.getInputStream();
		BufferedReader inFromServer = 
				new BufferedReader(new
						InputStreamReader(is)); 
		///////////////////////////////////////
		DataInputStream dis = new DataInputStream(clientSocket.getInputStream());
		Thread readMessage = new Thread(new Runnable() 
		{
			boolean fileinfo=false;
			int lines=0;
			boolean flag=false;
			int i = 0;
			int size=0;
			String filename="";
			String Response="";
			boolean close=false;
			@SuppressWarnings("resource")
			@Override
			public void run() {

				while (true) {
					try {
						System.out.print(".");

						//		if (modifiedSentence.equals("DONEFILE")) {
						//			flag=false;
						//		}
						if(flag ==true) {
							System.out.println("in the true flag");


							System.out.println(filename);
							String Recordsfile = "C:\\Users\\aly2\\Desktop\\client1\\"+ filename  ;	                    	

							OutputStream os1 = (new FileOutputStream(Recordsfile));	 
							int x=size;
							byte [] mybytearray  = new byte [x];

							System.out.println(x);
							// is.read(mybytearray,0,mybytearray.length);

							int count;
							System.out.println("in the true flag1");
							 int sum = 0 ;
							while (( count = is.read(mybytearray,0,mybytearray.length)) > 0) {
								System.out.println("loop");
								
								
								os1.write(mybytearray, 0, count);
								os1.flush();				
								sum = sum+count;
								//System.out.println("loop1");
								System.out.println(count);
								System.out.println(sum);
								if (sum==size) {
								break;
								}
							}

							//os1.write(mybytearray, 0, size);
							System.out.println("outloop");
						//	os1.flush();
						//	os1.close();
							System.out.println("File " + Recordsfile
									+ " downloaded (" + size + " bytes read)");
							
							if (close==true) {
								System.out.println("aaaa");
								clientSocket.close();
							}
							flag = false;
							i++;
						}


						else {

							String	modifiedSentence = inFromServer.readLine(); 
							if (fileinfo==true) {
								//System.out.println("FILEINFO in the if"+ lines);
								if (lines==0) {
									String[] x =modifiedSentence.split(" ");
									System.out.println(x[1]);
									Response=Response+modifiedSentence+'\n';
									filename=x[1];
									lines++;
								}
								else if (lines==1) {
									System.out.println("line1");
									Response=Response+modifiedSentence+'\n';
									lines++;
								}
								else if (lines==2) {
									System.out.println("line2");
									Response=Response+modifiedSentence+'\n';
									lines++;
								}
								else if (lines==3) {
									fileinfo=false;
									System.out.println("line3");
									Response=Response+modifiedSentence+'\n';
									chat.setText(chat.getText()+'\n'+Response);
									System.out.println("FROM SERVER:"+ Response);
									Response="";
									if (modifiedSentence.equals("close")) {
										
										close=true;
									}
									lines=0;
								}
							}


							else if (modifiedSentence.length()>15&&modifiedSentence.substring(0,14).equals("file in coming")) {
								String[] x =modifiedSentence.split(",");
								System.out.println(x[1]);
								size=Integer.parseInt(x[1]);
								System.out.println(size);
								flag=true;
							}
							else if (modifiedSentence.length()==8&&modifiedSentence.substring(0,8).equals("FILEINFO")) {
								System.out.println("FILEINFO");
								fileinfo=true;
								lines=0;
							}
							else if (modifiedSentence.length()==14&&modifiedSentence.substring(0,14).equals("CLOSETHESOCKET")) {
								System.out.println("aaaa");
								clientSocket.close();
								is.close();
								 o.close();
							}
							else {
								chat.setText(chat.getText()+'\n'+modifiedSentence);
								System.out.println("FROM SERVER:"+ modifiedSentence);

							}
						}
					} catch (IOException e) {

						e.printStackTrace();
					}
				}
			}
		});

		readMessage.start();


		///////////////////////////////////////
	} 
} 
