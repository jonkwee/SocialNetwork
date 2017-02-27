package components;

import java.time.LocalDate;
import java.util.Hashtable;


public class Users {
	private Hashtable<String, UserInfo> users = new Hashtable<String, UserInfo>();

	public void add(String username, String password, String name, LocalDate bday,
			        String phone, String email /*String biography*/){
		users.put(username, new UserInfo(password, name, bday, phone, email /*biography*/));
	}
	
	/**
	 * Checks whether username exists in hashtable
	 * @param  		String username
	 * @return      boolean 
	 */
	public boolean checkUserName(String username) {
		return users.containsKey(username);
	}
	
	/**
	 * Checks whether username and password matches.
	 * @param  		String username, password
	 * @return      boolean (Returns true if matches)
	 */
	public boolean checkUser(String username, String password) {
		if (checkUserName(username)) {
			UserInfo info = users.get(username);
			if (info.getPassword().equals(password)) {
				return true;
			} else { return false; }
		} else { return false; }
	}

}
