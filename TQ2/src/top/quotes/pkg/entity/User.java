package top.quotes.pkg.entity;

public class User {
    
    private static boolean loggedIn = false;
    
    private String name;
    private String surename;
    private String email;

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getSurename() {
	return surename;
    }

    public void setSurename(String surename) {
	this.surename = surename;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public static boolean isLoggedIn() {
	return loggedIn;
    }

    public static void setLoggedIn(boolean loggedIn) {
	User.loggedIn = loggedIn;
    }

}
