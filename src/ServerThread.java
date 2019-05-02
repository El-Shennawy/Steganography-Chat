import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;


class ServerThread implements Runnable {
	public Socket client;
	//public Server Server  ;
	//	private ArrayList<String> clientList;
	//private ArrayList<Socket> ports;
	public Server Server;
	private final Object lock = new Object();
	//Constructor
	public ServerThread(Socket client)
	{
		this.client = client;
		//this.clientList=new  ArrayList<String>(); 
		//	this.ports=new ArrayList<Socket>();
	}

	public void run(){

		String line;
		BufferedReader in =null;
		PrintWriter out=null;
		PrintWriter out1=null;

		try {
			in = new BufferedReader(new InputStreamReader(client.getInputStream()));

			out = new PrintWriter(client.getOutputStream(),true);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while (true) {
			
				String all_lines="";
			
			
				 try {
					for (String linee = in.readLine(); linee!=(null) ; linee = in.readLine()) {
						 if (linee.equals("***^&^")) {
							// in.readLine();
						        break;
						    } 
						 if(all_lines.isEmpty()) {
							 all_lines=linee;
						 }
						 else
						all_lines=all_lines+'\n'+linee;
					
					   //  System.out.println(all_lines);    
					    }
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
				//line=in.readLine();
				
			
				//line=in.readLine();
				line = all_lines;   
			
				//String []xxx=line.split("\\)");
				//System.out.println(xxx[1]);
				//System.out.println(line.split("\\n").length);
				try {
				if (line.length()>=5&&line.substring(0,5).equals("joins")) {
					int start =0;
					int end = 0;
					for (int i =0;i<line.length();i++) {
						if (line.charAt(i)=='(') {
							start=i+1;
						}
						else if (line.charAt(i)==')') {
							end = i;
						}

					}
					String userr= line.substring(start,end);
					if (userr.equals("")) {
						out.println("please enter a name");
					}
					else {
						boolean inn=false;
						for (int i =0;i<Server.Sports.size();i++) {
							if (client.getPort()==Server.Sports.get(i).getPort()) {
								if (userr.equals(Server.Servers.get(i))) {
									out.println("you are already in "+Server.Servers.get(i));
								}
								else {
									out.println("you are already in but as:"+ Server.Servers.get(i));
								}
								inn=true;
							}	
						}
						if (inn==false) {
							Server.Servers.add(userr);
							Server.Sports.add(client);
							out.println("Joined..welcome Server Server "+": "+userr);
						}

					}
				
				}
				else if (line.split(("\\n")).length==4) {
					 synchronized (Server.lock) {
				//	System.out.println("hay");
					Server.req_turns.add(client);	
					Server.reqs.add(line);
					 }
				}
				
				
				else if (line.length()>=6 && line.substring(0,6).equals("logout")) {

					boolean inn=false;
					for (int i =0; i<Server.ports.size();i++) {
						if (client.getPort()==Server.ports.get(i).getPort()) {
							Server.clientList.remove(i);
							Server.ports.remove(i);
							out.println("logot Done");
							inn=true;
						}

					}
					if (inn==false){
						out.println("Your are not a member to logout");
					}
				
				}

				else if (line.length()>=4&&line.substring(0,4).equals("join")&&line.split("\\)").length==1) {
					int start =0;
					int end = 0;
					for (int i =0;i<line.length();i++) {
						if (line.charAt(i)=='(') {
							start=i+1;
						}
						else if (line.charAt(i)==')') {
							end = i;
						}

					}
					String userr= line.substring(start,end);
					if (userr.equals("")) {
						out.println("please enter a name");
					}
					else {
						boolean inn=false;
						for (int i =0; i<Server.ports.size();i++) {
							for(int j=0;j<Server.clientList.size();j++)
								if (userr.equals(Server.clientList.get(j))) {
									out.println("you are already in "+Server.clientList.get(j));
									inn=true;
									break;
								}
							if (client.getPort()==Server.ports.get(i).getPort()) {
								if (!userr.equals(Server.clientList.get(i))) {
									
									out.println("you are already in but as:"+ Server.clientList.get(i));
								}
								inn=true;
								break;
							}	
						}
						if (inn==false) {

							Server.clientList.add(userr);
							Server.ports.add(client);
							out.println("Joined..welcome"+": "+userr);
						}

					}
				
				}



/*
				else if (line.length()>=2&&line.equals("Get")) {
					//out = new PrintWriter(client.getOutputStream(),true);
					//out1 = new PrintWriter(Server.ports.get(0).getOutputStream(),true);
					out.println("aywa wslt");	
					Socket theoneport=Server.ports.get(0);
					PrintWriter pw=	new PrintWriter(theoneport.getOutputStream(),true);
					//	PrintWriter pw1=new PrintWriter(client.getOutputStream(),true);
					pw.println("ayklaam");
				}*/
				else if (line.length()>=6 && line.substring(0,9).equals("broadCast")){
					boolean flag=false;
					int start =0;
					int end = 0;
					for (int i =0;i<line.length();i++) {
						if (line.charAt(i)=='(') {
							start=i+1;
						}
						else if (line.charAt(i)==')') {
							end = i;
						}

					}
					String thesender="";
					String Servername="";
					for (int i = 0 ;i<Server.ports.size();i++) {
						if(Server.ports.get(i).equals(client)) {
							thesender = Server.clientList.get(i);
						}
					}
					for (int i = 0 ;i<Server.Sports.size();i++) {
						if(Server.Sports.get(i).equals(client)) {
							Servername = Server.Servers.get(i);
						}
					}
					if (thesender.equals("")&&Servername.equals("")) {
						PrintWriter pw1=new PrintWriter(client.getOutputStream(),true);
						pw1.println("Your are not a member of that Server baby please join");
					}
					else {
						for (int i = 0 ;i<Server.clientList.size();i++) {
							
								Socket theoneport=Server.ports.get(i);
								PrintWriter pw=	new PrintWriter(theoneport.getOutputStream(),true);
								pw.println(thesender+" Sends:"+line.substring(end+1));
								
								flag=true;
								
														
						}
						PrintWriter pw1=new PrintWriter(client.getOutputStream(),true);

						pw1.println("your msg sent to the server :D ");
						pw1.println(" ");
					}
				}
				else if (line.length()>=6 && line.substring(0,4).equals("chat")){
					boolean flag=false;
					int start =0;
					int end = 0;
					for (int i =0;i<line.length();i++) {
						if (line.charAt(i)=='(') {
							start=i+1;
						}
						else if (line.charAt(i)==')') {
							end = i;
						}

					}
					String theonename=line.substring(start,end);
					String thesender="";
					String Servername="";
					for (int i = 0 ;i<Server.ports.size();i++) {
						if(Server.ports.get(i).equals(client)) {
							thesender = Server.clientList.get(i);
						}
					}
					for (int i = 0 ;i<Server.Sports.size();i++) {
						if(Server.Sports.get(i).equals(client)) {
							Servername = Server.Servers.get(i);
						}
					}
					if (thesender.equals("")&&Servername.equals("")) {
						PrintWriter pw1=new PrintWriter(client.getOutputStream(),true);
						pw1.println("Your are not a member of that Server baby please join");
					}
					else {
						for (int i = 0 ;i<Server.clientList.size();i++) {
							if (theonename.equals(Server.clientList.get(i))) {
								Socket theoneport=Server.ports.get(i);
								PrintWriter pw=	new PrintWriter(theoneport.getOutputStream(),true);
								PrintWriter pw1=new PrintWriter(client.getOutputStream(),true);
								pw.println(thesender+" Sends:"+line.substring(end+1));
								pw1.println("your msg sent to the one :D ");
								flag=true;
								
							}							
						}
				if(flag==false) {
					if (Servername.equals("")) {
								if (Server.x==null) {
									try {
										Server.x= new ConnectServertoServer();
										// System.out.println(Server.x.connect);
										 String y=Server.x.chat(line.substring(end+1),thesender,theonename);
										 out.println(y);
										 
									} catch (Exception e) {
										// TODO Auto-generated catch block
										System.out.println("Server Not Found");	
										PrintWriter pw1=new PrintWriter(client.getOutputStream(),true);
										pw1.println(theonename+" Not Exists");
										
									}
								}
								else {
									try {
									String y=Server.x.chat(line.substring(end+1),thesender,theonename);
									out.println(y);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								}

						}
					else {
						PrintWriter pw1=new PrintWriter(client.getOutputStream(),true);
						pw1.println(theonename+" Not Exists");
					}
					}
					}
					
				}

				else if (line.length()>=15 && line.substring(0,15).equals("GetMemberList()")) {
					String allusers="";
					for (int i=0;i<Server.clientList.size();i++) {
						allusers=allusers+Server.clientList.get(i)+" in port: "+Server.ports.get(i).getPort()+" || ";						
					}
					if (!(Server.Sports.contains(client))){
						if (Server.x==null) {
							try {
								Server.x= new ConnectServertoServer();
							//	 System.out.println(Server.x.connect);
								 Server.x.getconnected();
								 String y=Server.x.Getmem();
								 allusers+=y;
							} catch (Exception e) {
								// TODO Auto-generated catch block
								System.out.println("Server Not Found");	
							}
						}
						else {
							try {
							 String y=Server.x.Getmem();
							 allusers+=y;
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						}
					}
					if (allusers.equals("")) {
						out.println("Thats All");
					}
					else
					out.println(allusers+"...");
				
				}
	
			//	else if () {
					
			//	}
				
				else {
					out.println("join(your name)// to join the server"+".."+"GetMemberList()//to get the joined people"+".."+"chat(the-reciver-name)your-msg//to chat with the people"+".."+"logout//to logout");
				
				}
				//	


			} catch (IOException e) {
				// TODO Auto-generated catch block
				out.println("a3333333333333333");
			}


		}





	}
	public void cont() {
		System.out.println("kml hna");
	}




}