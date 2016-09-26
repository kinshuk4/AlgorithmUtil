package com.vaani.algo.compete.hackerrank;

/**
 * Created by andersonkmi on 8/28/16.
 */
public class LoggedUser {
    private String name;
    private int login;
    private int logout;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setLogin(int login) {
        this.login = login;
    }

    public int getLogin() {
        return login;
    }

    public void setLogout(int logout) {
        this.logout = logout;
    }

    public int getLogout() {
        return logout;
    }
}
