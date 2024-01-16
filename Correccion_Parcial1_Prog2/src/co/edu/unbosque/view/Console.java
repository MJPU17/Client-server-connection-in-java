package co.edu.unbosque.view;

import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.InputMismatchException;
import java.util.Scanner;

import co.edu.unbosque.util.exception.UserException;

public class Console {
	
	private Scanner sc;
	private PrintWriter pw;
	
	public Console() {
		sc=new Scanner(System.in);
		pw=new PrintWriter(System.out,true,StandardCharsets.UTF_8);
	}
	
	public int readOption(String mensaje) {
		pw.print(mensaje);
		pw.flush();
		while(true) {
			try {
				return sc.nextInt();
			}catch (InputMismatchException e) {
				pw.println("\nOpcion invalida.\n");
				pw.print(mensaje);
				pw.flush();
				sc.nextLine();
			}
		}
	}
	
	public int readIndex(String mensaje,int size) {
		pw.print(mensaje);
		pw.flush();
		while(true) {
			try {
				int index=sc.nextInt();
				if(index<=0||index>size)throw new UserException(0);
				return index;
			}catch (InputMismatchException e) {
				pw.println("\nIndice invalido. Ingrese un nomero de 1 a "+size+".\n");
				pw.print(mensaje);
				pw.flush();
				sc.nextLine();
			}
			catch (UserException e) {
				pw.println("\n"+e.getMessage()+". Ingrese un nomero de 1 a "+size+".\n");
				pw.print(mensaje);
				pw.flush();
			}
		}
	}
	
	public String readName (String mensaje) {
		pw.print(mensaje);
		pw.flush();
		while(true) {
			try {
				String name=sc.nextLine();
				if(name.matches(".*[^a-zA-ZáéíóÁÉÍÓÚ ].*"))throw new UserException(1);
				return name;
			}catch (UserException e) {
				pw.println("\n"+e.getMessage()+"\n");
				pw.print(mensaje);
				pw.flush();
			}
		}
	}
	
	public String readTypeDocument (String mensaje) {
		pw.print(mensaje);
		pw.flush();
		while(true) {
			try {
				String tdocument=sc.nextLine();
				if(tdocument.matches(".*[^a-zA-ZáéíóÁÉÍÓÚ ].*"))throw new UserException(2);
				return tdocument;
			}catch (UserException e) {
				pw.println("\n"+e.getMessage()+"\n");
				pw.print(mensaje);
				pw.flush();
			}
		}
	}
	
	public long readNumberDocument(String mensaje) {
		pw.print(mensaje);
		pw.flush();
		while(true) {
			try {
				return sc.nextLong();
			}catch (InputMismatchException e) {
				pw.println("\nNumero de documento invalido. Ingrese un solo numeros.\n");
				pw.print(mensaje);
				pw.flush();
				sc.nextLine();
			}
		}
	}
	
	public void printWithJump(String mensaje) {
		pw.println(mensaje);
		pw.flush();
	}
	
	public void printWithoutJump(String mensaje) {
		pw.print(mensaje);
		pw.flush();
	}
	
	public void burnLine() {
		sc.nextLine();
	}

}
