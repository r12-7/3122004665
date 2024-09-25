package Controller;

import Utils.IOUtil;
import Utils.PageUtil;
import com.sun.org.omg.CORBA.Initializer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class Exercise implements Initializable {

    @FXML
    private Button OK;


    @FXML
    private  TextArea  exercise;


    @FXML
    void submit(ActionEvent event) {
        String s=exercise.getText();
        IOUtil.clean("src/main/resources/exercisefile.txt");
        IOUtil.writeTxt(s,"src/main/resources/exercisefile.txt");
        PageUtil.Answer();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String s = IOUtil.readTxt("src/main/resources/exercisefile.txt");
        String[] split = s.split("=");
        for (int i=0;i<split.length;i++){
            exercise.appendText(split[i]+"="+'\n');

        }

    }
}
