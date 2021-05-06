package Model.Data;

public class DataForServerFromClient {

    public String message = null;
    public String username = null;
    public String menuName = null;

    public DataForServerFromClient(String message, String username, String menuName) {
        this.message = message;
        this.username = username;
        this.menuName = menuName;
    }

    public DataForServerFromClient(String message, String menuName) {
        this.message = message;
        this.username = null;
        this.menuName = menuName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }


}
