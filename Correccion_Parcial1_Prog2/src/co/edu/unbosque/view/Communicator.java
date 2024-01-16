package co.edu.unbosque.view;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Communicator {
	
	private ObjectInputStream in;
	private ObjectOutputStream out;
	
	public Communicator(Socket socket,boolean isServer) throws IOException {
		if(isServer) {
			in=new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
			out=new ObjectOutputStream(socket.getOutputStream());
		}
		else {
			out=new ObjectOutputStream(socket.getOutputStream());
			in=new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
		}
	}
	
	public int obtainInt() throws IOException{
		return in.readInt();
	}
	
	public Object obtainObject() throws ClassNotFoundException, IOException {
		return in.readObject();
	}
	
	public void sendInt(int num) throws IOException {
		out.writeInt(num);
		out.flush();
	}
	
	public void sendObject(Object obj) throws IOException {
		out.writeObject(obj);
		out.flush();
	}
	
	public void finish() throws IOException {
		in.close();
		out.close();
	}

}
