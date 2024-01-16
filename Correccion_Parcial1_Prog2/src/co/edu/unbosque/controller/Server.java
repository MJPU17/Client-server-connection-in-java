package co.edu.unbosque.controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import co.edu.unbosque.model.UserDTO;
import co.edu.unbosque.model.persistence.UserDAO;
import co.edu.unbosque.view.Communicator;
import co.edu.unbosque.view.Console;

public class Server extends Thread{
	
	private final int PORT=8000;
	
	private UserDAO users;
	private Communicator communicator;
	private Console console;
	private ServerSocket ss;
	private Socket cs;
	
	public Server() {
		try {
			ss=new ServerSocket(PORT);
			users=new UserDAO();
			console=new Console();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		try {
			console.printWithJump("Servidor en línea esperando conexiones...");
			cs=ss.accept();
			communicator=new Communicator(cs,true);
			console.printWithJump("Se conecto un cliente. Esperando acción...");
			int opcion=0;
			do {
				opcion=communicator.obtainInt();
				switch (opcion) {
				case 1:{
					console.printWithJump("Se eligio opcion 1.");
					UserDTO user=(UserDTO)communicator.obtainObject();
					users.create(user);
					communicator.sendObject("\nSe creo el Usuario: "+user.toString()+" exitosamente.\n");
					break;
				}
				case 2:{
					console.printWithJump("Se eligio opcion 2.");
					communicator.sendInt(users.getLusers().size());
					if(!users.getLusers().isEmpty()) {
						int index=communicator.obtainInt();
						UserDTO user=(UserDTO)communicator.obtainObject();
						users.update(index-1, user);
						communicator.sendObject("\nSe actualiza el usuario "+index+" exitosamente.\n");
					}
					else {
						communicator.sendObject("\nLista de usuarios vacia. Ingrese un usuario.\n");
					}
					break;
				}
				case 3:{
					console.printWithJump("Se eligio opcion 3.");
					communicator.sendInt(users.getLusers().size());
					if(!users.getLusers().isEmpty()) {
						int index=communicator.obtainInt();
						UserDTO user=users.getLusers().get(index-1);
						users.eliminate(index-1);
						communicator.sendObject("\nSe elimino el Usuario: "+user.toString()+" exitosamente.\n");
					}
					else {
						communicator.sendObject("\nLista de usuarios vacia. Ingrese un usuario.\n");
					}
					break;
				}
				case 4:{
					console.printWithJump("Se eligio opcion 4.");
					communicator.sendInt(users.getLusers().size());
					if(!users.getLusers().isEmpty()) {
						int index=communicator.obtainInt();
						UserDTO user=users.getLusers().get(index-1);
						communicator.sendObject("\nUsuario: "+user.toString()+".\n");
					}
					else {
						communicator.sendObject("\nLista de usuarios vacia. Ingrese un usuario.\n");
					}
					break;
				}
				case 5:{
					console.printWithJump("Se eligio opcion 5.");
					if(!users.getLusers().isEmpty()) {
						communicator.sendObject("\n"+users.show());
					}
					else {
						communicator.sendObject("\nLista de usuarios vacia. Ingrese un usuario.\n");
					}
					break;
				}
				case 6:{
					console.printWithJump("Se eligio opción 6.");
					communicator.sendObject("\nGracias por utilizar el programa :D");
					break;
				}
				default:{
					console.printWithJump("Se elgio una opción invalida.");
					communicator.sendObject("\nOpción invalida.\n");
				}
				}
			}while(opcion!=6);
			communicator.finish();
			cs.close();
			ss.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
