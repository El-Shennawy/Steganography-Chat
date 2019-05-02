
import java.io.*; 
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList; 

class Server  { 

	public ServerSocket welcomeSocket;
	public ArrayList<String> clientList;
	public ArrayList<Socket> ports;
	public ArrayList<String> Servers;
	public ArrayList<Socket> Sports;
	public ArrayList<Socket> req_turns;
	public ArrayList<String>direct;
	public ConnectServertoServer x=null;
	public static Object lock = new Object();
	public ArrayList<String> reqs;
	public Server() throws IOException {
		clientList = new ArrayList<String>();
		ports=new ArrayList<Socket>();
		Servers = new ArrayList<String>();
		Sports=new ArrayList<Socket>();
		reqs=new ArrayList<String>();
		direct=new ArrayList<String>();
		req_turns=new ArrayList<Socket>();


		try {
			this.welcomeSocket = new ServerSocket(6768);		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		// this.run();
	}
	public void run() throws IOException {
		//Server s = new Server();

		while(true) { 
			//  
			Socket x = this.welcomeSocket.accept();
			// s.clientList.add(x);
			ServerThread w = new ServerThread(x);
			w.Server=this ; 
			Thread t = new Thread(w);
			t.start();



			//   for (int i = 0;i<s.clientList.size();i++) {
			//    	System.out.print((s.clientList.get(i)).toString()+"");
			//    }

			System.out.println(".....");
			//   System.out.println(t.getId());

		}
	} 


	public static void main(String argv[]) throws Exception 
	{ 

		Server s = new Server();

		Thread dealing_with_the_reqs = new Thread(new Runnable() 
		{
			@SuppressWarnings("resource")
			@Override
			public void run() {
				while (true) {
					//System.out.println(".");
					synchronized (lock) {
						if(s.req_turns.size()>0) {
							//System.out.println("wslt l hna");	 
							//---------------the req method--------------
							String lines =s.reqs.get(0);
							String[] line = lines.split("\\n");
							String method="";
							String filename="";
							String connection="";
							String hostname="";
							String type="";
							Boolean error=false;;
							for (int i = 0 ;i<line.length;i++) {
								if (i ==0) {
									String []parts=line[i].split(" ");
									method=parts[0];
									filename=filename+parts[1];
									if(!parts[2].equals("1.1"))
										error=true;
								}
								else if (i == 1) {
									hostname=line[i];
								}
								else if (i == 2) {
									type= line[i];
									filename=filename+"."+ line[i];

								}
								else if (i == 3) {
									connection=line[i];
								}
							}
							if (method.equals("get")){
								//-----------check if the file exits and if yes send the resp and the file to the socket in the resp trun-------------
								File folder = new File("C:\\Users\\mnmos\\Desktop\\test");
								Boolean flag=true;
								File[] listOfFiles = folder.listFiles();
								for (int i = 0; i < listOfFiles.length; i++) {
									if (listOfFiles[i].isFile()) {
										if(listOfFiles[i].getName().equals(filename)){
											flag=false;
										} 
									}
								}   		
								if (flag==true) {
									//-------------------send 404 --------------------------
									PrintWriter out=null;
									try {
										out = new PrintWriter(s.req_turns.get(0).getOutputStream(),true);
										SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
										out = new PrintWriter(s.req_turns.get(0).getOutputStream(),true);
										out.println("404 1.1"+'\n'+sdf.format(System.currentTimeMillis())+'\n'+ type+'\n'+connection);
										s.reqs.remove(0);
										s.req_turns.remove(0);
										//	out.


									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
								else {
									PrintWriter out=null;
									try {
										out = new PrintWriter(s.req_turns.get(0).getOutputStream(),true);
										//found it 
									 File folder_found=new File(folder.getPath()+"\\"+filename);
										SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyyy.HH.mm.ss");
										//out = new PrintWriter(s.req_turns.get(0).getOutputStream(),true);
										out.println("FILEINFO");
										out.println("200 "+filename+" 1.1"+'\n'+sdf.format(System.currentTimeMillis())+'\n'+type+'\n'+connection);
										out.println("file in coming,"+(int)folder_found.length());
										byte[] bytes = new byte[(int)folder_found.length()];
										FileInputStream fileinp = null;
										BufferedInputStream bufferinp = null;							
										fileinp = new FileInputStream(folder_found);
										bufferinp = new BufferedInputStream(fileinp);
										bufferinp.read(bytes,0,bytes.length);
										OutputStream os = null;
										os = s.req_turns.get(0).getOutputStream();
										os.write(bytes,0,bytes.length);
									//	byte []x= new byte[1];
									//	os.write(x,0,x.length);
										if (type.equals("close")) {
											System.out.println("closehere");
											out.println("CLOSETHESOCKET");
											s.req_turns.get(0).close();
											os.close();
											}
										os.flush();
										//out.println("DONEFILE");
										//out.println(folder_found);
										s.req_turns.remove(0);
										s.reqs.remove(0);
										
										//	out.
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}



								}

							}
						}

					}

				}
			}
		});

		dealing_with_the_reqs.start();

		while(true) { 
			//  
			Socket x = s.welcomeSocket.accept();
			// s.clientList.add(x);
			ServerThread w = new ServerThread(x);
			w.Server=s ; 
			Thread t = new Thread(w);
			t.start();



			//   for (int i = 0;i<s.clientList.size();i++) {
			//    	System.out.print((s.clientList.get(i)).toString()+"");
			//    }

			System.out.println(".....");
			//   System.out.println(t.getId());

		}
	} 
} 

