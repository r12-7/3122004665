package Controller;

import Utils.IOUtil;
import Utils.MarkUtil;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class Answer implements Initializable {

    @FXML
    private TextArea answer;

    @FXML
    private AnchorPane display;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String AnswerPath="src/main/resources/answerfile.txt";
        IOUtil.clean("src/main/resources/Grade.txt");
        String s = IOUtil.readTxt("src/main/resources/answerfile.txt");
        String[] split = s.split(";");
        IOUtil.clean(AnswerPath);
        for (int i=0;i<split.length;i++){
            answer.appendText(split[i]+'\n');
            IOUtil.writeTxt(split[i],AnswerPath);
        }
        MarkUtil.getCheckResult("src/main/resources/exercisefile.txt",AnswerPath,"src/main/resources/Grade.txt");


    }
}
