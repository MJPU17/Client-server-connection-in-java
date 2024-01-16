package co.edu.unbosque.controller;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import co.edu.unbosque.model.UserDTO;
import co.edu.unbosque.view.Communicator;
import co.edu.unbosque.view.Console;

public class Client extends Thread{
	
	private final int PORT=8000;
	private final String HOST="127.0.0.1";
	
	private Socket cs;
	private Console console;
	private Communicator communicator;
	
	public Client() {
		try {
			cs=new Socket(HOST, PORT);
			console=new Console();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		try {
			communicator=new Communicator(cs,false);
			int opcion=0;
			do {
				opcion=console.readOption("**USERS**\n\n1)Crear Usuario.\n2)Actualizar usuario.\n3)Eliminar usuario.\n4)Mortrar usuario.\n5)Mostar todo.\n6)Salir\n\nIngrese opcion: ");
				communicator.sendInt(opcion);
				switch (opcion) {
				case 1:{
					long ndocument=console.readNumberDocument("Ingrese numero de documento: ");
					console.burnLine();
					String name=console.readName("Ingrese nombre: ");
					String tdocument=console.readTypeDocument("Ingrese tipo documento: ");
					communicator.sendObject(new UserDTO(name, ndocument, tdocument));
					break;
				}
				case 2:{
					int size=communicator.obtainInt();
					if(size!=0) {
						int index=console.readIndex("Ingrese indice de usuario a actualizar: ", size);
						communicator.sendInt(index);
						long ndocument=console.readNumberDocument("Ingrese numero de documento: ");
						console.burnLine();
						String name=console.readName("Ingrese nombre: ");
						String tdocument=console.readTypeDocument("Ingrese tipo documento: ");
						communicator.sendObject(new UserDTO(name, ndocument, tdocument));
					}
					break;
				}
				case 3:{
					int size=communicator.obtainInt();
					if(size!=0) {
						int index=console.readIndex("Ingrese indice de usuario a eliminar: ", size);
						communicator.sendInt(index);
					}
					break;
				}
				case 4:{
					int size=communicator.obtainInt();
					if(size!=0) {
						int index=console.readIndex("Ingrese indice de usuario a mostar: ", size);
						communicator.sendInt(index);
					}
					break;
				}
				}
				console.printWithJump((String)communicator.obtainObject());
			}while(opcion!=6);
			communicator.finish();
			cs.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	

}
