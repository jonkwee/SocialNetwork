package components;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;

import controllers.StartController;

public class Users {
	private Hashtable<String, UserInfo> users = new Hashtable<String, UserInfo>();

	/**
	 * Adds a new user to the users hashTable and then writes the information to a file
	 * @param username
	 * @param password
	 * @param name - optional (could be null)
	 * @param bday - optional (could be null)
	 * @param phone - optional (could be null)
	 * @param email - optional (could be null)
	 * @throws IOException
	 */
	public void add(String username, String password, String name, String phone, String email,
					LocalDate bday /*String biography*/) throws IOException{
		for(String user: users.keySet()){System.out.println(user + users.get(user).toString());}
		users.put(username, new UserInfo(password, name, phone, email, bday /*biography*/));
		for(String user: users.keySet()){System.out.println(user + users.get(user).toString());}
		writeToUserFile();
	}

	/**
	 * Writes new user information to a file to be accessed when app reopened
	 */
	public void writeToUserFile() throws IOException{
		PrintWriter out = new PrintWriter("users.txt");
		for(String user: users.keySet()){
			out.println(user + "," + users.get(user).toString());
		}
		out.close();
	}

	public void readFromUserFile(StartController start) throws FileNotFoundException{
		Scanner in = new Scanner(new File("users.txt"));
		while (in.hasNextLine()) {
			String line = in.nextLine();
			String[] parts = line.split(",");
			start.getUsers().users.put(parts[0], new UserInfo(parts[1], parts[2], parts[3], parts[4],
									   LocalDate.parse(parts[5])));
		}
		in.close();
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
		} return false;
	}
	
	public List<String> getCurrentUser(String username) {
		UserInfo info = users.get(username);
		List<String> userInfoList = Arrays.asList(info.toString().split(","));
		return userInfoList;
	}
}
