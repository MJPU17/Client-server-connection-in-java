package co.edu.unbosque.model.persistence;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FileHandler {
	
	private static ObjectInputStream ois;
	private static ObjectOutputStream oos;
	private static File file;
	
	public FileHandler() {}
	
	public static void writeSerializable(String path, Object obj) {
		file=new File("src/co/edu/unbosque/model/persistence/"+path);
		try {
			oos=new ObjectOutputStream(new FileOutputStream(file));
			oos.writeObject(obj);
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Object readSerializable(String path) {
		file=new File("src/co/edu/unbosque/model/persistence/"+path);
		if(file.length()>0) {
			try {
				ois=new ObjectInputStream(new FileInputStream(file));
				Object obj=ois.readObject();
				ois.close();
				return obj;
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}
