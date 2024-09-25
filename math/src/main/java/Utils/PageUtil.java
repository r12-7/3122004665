package Utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;

public class PageUtil  {

    public static void InputPage()  {

                Stage primary=new Stage();
                primary.setTitle("小学四则运算");
                Parent root = null;
        try {
            URL url = Paths.get("./src/main/java/view/InputPage.fxml").toUri().toURL();
            try {
                root = FXMLLoader.load(url);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

//        try {
//                    root = FXMLLoader.load(PageUtil.class.getResource("/view/InputPage.fxml"));
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
                Scene scene=new Scene(root,500,300);
                primary.setScene(scene);
                primary.show();
                //更新JavaFX的主线程的代码放在此处


    }
    public static void Exercise()  {

        Stage primary=new Stage();
        primary.setTitle("练习");
        Parent root = null;
        try {
            URL url = Paths.get("./src/main/java/view/Exercise.fxml").toUri().toURL();
            try {
                root = FXMLLoader.load(url);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

//        try {
//                    root = FXMLLoader.load(PageUtil.class.getResource("/view/InputPage.fxml"));
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
        Scene scene=new Scene(root,700,500);
        primary.setScene(scene);
        primary.show();
        //更新JavaFX的主线程的代码放在此处


    }
    public static void Answer()  {

        Stage primary=new Stage();
        primary.setTitle("答案");
        Parent root = null;
        try {
            URL url = Paths.get("./src/main/java/view/Answer.fxml").toUri().toURL();
            try {
                root = FXMLLoader.load(url);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Scene scene=new Scene(root,700,500);
        primary.setScene(scene);
        primary.show();
        //更新JavaFX的主线程的代码放在此处


    }


}
