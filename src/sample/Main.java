package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.nio.file.Paths;

public class Main extends Application {

    public static String rootPath;
    public static String filename;
    public static ClassLoader loader;

    @Override
    public void start(Stage primaryStage) throws Exception{

        rootPath=Paths.get("").toAbsolutePath().toString()+ File.separator+"JSON"+File.separator;
        System.out.println("Path is "+rootPath);
        ClassLoader classLoader = getClass().getClassLoader();
        Main.loader=classLoader;

        FXMLLoader loader=new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Configure Rules");
        ((Controller)loader.getController()).setPrimary_stage(primaryStage);
        Scene scene=new Scene(root, 1038, 564);

        scene.getStylesheets().add(Main.class.getResource("javafxcss/jfoenix-main.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static JSONObject getParentJSONObject(){
        JSONObject parent=new JSONObject();
        JSONObject env=new JSONObject();
        env.put("browser",true);
        parent.put("env",env);
        parent.put("plugins",new JSONArray("[\"angular\"]"));
        return parent;
    }

    public static JSONArray getExtendedObject(){
        JSONArray extended= new JSONArray();
        extended.put("angular");
        extended.put("eslint:recommended");
        return extended;
    }


    public static JSONObject getfileRuleObject(String filename){
        String total="";
        try {
            String sCurrentLine;
            BufferedReader reader=new BufferedReader(new FileReader(filename));
            while ((sCurrentLine = reader.readLine()) != null) {
                total+=sCurrentLine+"\n";
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new JSONObject(total);
    }


    public static void main(String[] args) {
        if (args.length>0){
            filename=args[0];
        }
        launch(args);
    }
}
