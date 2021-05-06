package Controller.DataBaseControllers;

import Model.Data.DataForClientFromServer;
import Model.Enums.MessageType;
import Model.User;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.File;
import java.util.ArrayList;

public class UserDataBaseController extends DataBaseController {

    public static String getUserFileNameByUsername(String username) {
        return getUsersPath() + "\\" + username + ".json";
    }

    public static DataForClientFromServer createUser(User user) {

        DataForClientFromServer dataSendToClient;

        String path = getUserFileNameByUsername(user.getUsername());

        dataSendToClient = checkCreatingUserErrors(user, path);

        if (dataSendToClient != null) {
            return dataSendToClient;
        }

        if(createFileByPathAndData(path, makeObjectJson(user))){
            dataSendToClient = new DataForClientFromServer
                    ("user create successfully", MessageType.SUCCESSFUL);
            return dataSendToClient;
        }

        return dataSendToClient;
    }


    public static DataForClientFromServer checkCreatingUserErrors(User user, String userPath) {

        DataForClientFromServer dataSendToClient = null;

        if (isThisFileExist(userPath)) {

            dataSendToClient = new DataForClientFromServer(
                    "user with username " + user.getUsername() + " already exists",
                    MessageType.ERROR);

            return dataSendToClient;
        }

        if (isNickNameRepetitious(user.getNickname())) {
            dataSendToClient = new DataForClientFromServer(
                    "user with nickname " + user.getNickname() + " already exists",
                    MessageType.ERROR);

            return dataSendToClient;
        }

        return dataSendToClient;
    }

    public static boolean isNickNameRepetitious(String nickname) {

        String data;

        for (File userData : getFilesOfOneFolder(getUsersPath())) {
            data = readDataFromFile(userData);
            JsonObject jsonObjectAlt = JsonParser.parseString(data).getAsJsonObject();
            JsonElement nicknameJson = jsonObjectAlt.get("nickname");
            if (nicknameJson.toString().equals("\"" + nickname + "\"")) {
                return true;
            }
        }
        return false;

    }

    public static User getUserByUsername(String username) {

        String path = UserDataBaseController.getUserFileNameByUsername(username);
        return User.getUserByGson(path);
    }

    public static boolean doesUserExistWithThisUsername(String username) {

        String path = UserDataBaseController.getUserFileNameByUsername(username);
        return isThisFileExist(path);
    }

    public static boolean isUserPasswordCorrect(String username, String password) {
        String path = UserDataBaseController.getUserFileNameByUsername(username);
        User user = User.getUserByGson(path);
        return user.isPasswordCorrect(password);
    }

    public static boolean changePassword(User user, String newPassword) {
        user.setPassword(newPassword);
        return rewriteFileOfObjectGson(getUserFileNameByUsername(user.getUsername()), user);
    }

    public static boolean changeNickname(User user, String newNickname) {
        user.setNickname(newNickname);
        return rewriteFileOfObjectGson(getUserFileNameByUsername(user.getUsername()), user);
    }

    public static void saveChanges(User user){
        rewriteFileOfObjectGson(getUserFileNameByUsername(user.getUsername()),user);
    }

    public static  ArrayList<User> allUsers(){
        ArrayList<User> allUsers=new ArrayList<>();
        for(File file:getFilesOfOneFolder("Resource\\Users")){
            allUsers.add((User)getObjectByGsonFile(file.getPath(),User.class));
        }
        return allUsers;
    }


}
