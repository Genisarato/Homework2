package deim.urv.cat.homework2.model;

public class CredentialsAux { 
    
    private String username;
    private String password;

    public CredentialsAux (String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
