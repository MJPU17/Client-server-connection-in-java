package co.edu.unbosque.model;
import java.io.Serializable;

public class UserDTO implements Serializable{
	
	private static final long serialVersionUID = -7552527634494820946L;
	private String name;
	private long ndocument;
	private String tdocument;
	
	public UserDTO() {}

	public UserDTO(String name, long ndocument, String tdocument) {
		this.name = name;
		this.ndocument = ndocument;
		this.tdocument = tdocument;
	}

	@Override
	public String toString() {
		return "[Name=" + name + ", Number document=" + ndocument + ", Type document=" + tdocument + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getNdocument() {
		return ndocument;
	}

	public void setNdocument(long ndocument) {
		this.ndocument = ndocument;
	}

	public String getTdocument() {
		return tdocument;
	}

	public void setTdocument(String tdocument) {
		this.tdocument = tdocument;
	}
	
}
