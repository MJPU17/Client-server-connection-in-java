package co.edu.unbosque.model.persistence;

import java.util.LinkedList;

import co.edu.unbosque.model.UserDTO;

public class UserDAO {
	
	private LinkedList<UserDTO> lusers;
	
	public UserDAO() {
		try {
			lusers=(LinkedList<UserDTO>)FileHandler.readSerializable("users.mjpu");
			if(lusers==null)lusers=new LinkedList<>();
		}catch (Exception e) {
			lusers=new LinkedList<>();
		}
	}
	
	public void create(UserDTO user) {
		lusers.add(user);
		FileHandler.writeSerializable("users.mjpu", lusers);
	}
	
	public void update(int index,UserDTO user) {
		lusers.set(index, user);
		FileHandler.writeSerializable("users.mjpu", lusers);
	}
	
	public void eliminate(int index) {
		lusers.remove(index);
		FileHandler.writeSerializable("users.mjpu", lusers);
	}
	
	public String show() {
		StringBuffer buf=new StringBuffer();
		buf.append("Users list:\n");
		for (int i = 0; i < lusers.size(); i++) {
			buf.append((i+1)+lusers.get(i).toString()+"\n");
		}
		return buf.toString();
	}

	public LinkedList<UserDTO> getLusers() {
		return lusers;
	}

	public void setLusers(LinkedList<UserDTO> lusers) {
		this.lusers = lusers;
	}
	
}
