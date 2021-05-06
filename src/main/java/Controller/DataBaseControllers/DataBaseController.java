package Controller.DataBaseControllers;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class DataBaseController {

    public static void makeResourceDirectory() {

        makeFolderByPath(getUsersPath());
        makeFolderByPath(getDecksPath());
        makeFolderByPath(getCardsPath());
    }

    public static void makeFolderByPath(String path){
        File theDir = new File(path);
        if (!theDir.exists()) {
            theDir.mkdirs();
        }
    }

    protected static String getUsersPath() {
        return "Resource\\Users";
    }

    protected static String getDecksPath() {
        return "Resource\\Decks";
    }

    protected static String getCardsPath(){
        return "Resource\\Cards";
    }



    public static boolean createFileByPathAndData(String path, String data){

        File file = new File(path);
        try {
            file.createNewFile();
            writeDataInfile(data, path);

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    public static String makeObjectJson(Object obj) {
        Gson gson = new Gson();
        return gson.toJson(obj);
    }

    protected static String readDataFromFile(File file) {
        Scanner scanner;
        try {
            scanner = new Scanner(file);
            scanner.useDelimiter("\\Z");
            return scanner.next();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String readDataFromFile(String path) {//user in export menu
        try {
            return new String(Files.readAllBytes(Paths.get(path)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    protected static void writeDataInfile(String data, String path) throws IOException {
        FileWriter fileWriter = new FileWriter(path);
        fileWriter.write(data);
        fileWriter.close();
    }

    protected static File[] getFilesOfOneFolder(String path) {
        File folder = new File(path);
        return folder.listFiles();
    }

    public static boolean rewriteFileOfObjectGson(String path, Object newObject){
        File file = new File(path);
        try {
            file.createNewFile();
            writeDataInfile(makeObjectJson(newObject), path);

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static void deleteFile(String path){
        File file =new File(path);
        file.delete();
    }

    protected static boolean isThisFileExist(String path) {
        return Files.exists(new File(path).toPath());
    }

    public static Class getClassByClassName (String type){
        try {
            return Class.forName(type);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <obj> Object getObjectByGson(String gsonData, Class<obj> cls) {

        Gson gson = new Gson();
        try {
            return gson.fromJson(gsonData, cls);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <obj> Object getObjectByGsonFile(String path, Class<obj> cls) {

        Gson gson = new Gson();
        JsonReader reader;
        try {
            reader = new JsonReader(new FileReader(path));
            return gson.fromJson(reader, cls);
        } catch (FileNotFoundException e) {
            return null;
        }
    }


//no need

    public static Object getInstanceOfAnObjectByClassName(String type){


        Class myClass = null;
        try {
            myClass = Class.forName(type);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            return myClass.getDeclaredConstructor().newInstance();

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

}
