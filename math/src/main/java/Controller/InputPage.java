package Controller;

import Bean.BinaryTree;
import Utils.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class InputPage {
    @FXML
    private AnchorPane page1;


    @FXML
    private TextField limit;

    @FXML
    private TextField number;

    @FXML
    private Button ok;

    @FXML
    void Submit(ActionEvent event) throws Exception {
        String textPath="src/main/resources/exercisefile.txt";
        String textPath1="src/main/resources/answerfile.txt";
        System.out.println(limit.getText());
        System.out.println(number.getText());
        int m=Integer.parseInt(limit.getText());
        int n=Integer.parseInt(number.getText());
        String[] strArray = FormulaUtil.CreateFormula(m, n);
        IOUtil.clean(textPath);
        IOUtil.clean(textPath1);
        for (int i = 0; i < strArray.length; i++) {
            String s = strArray[i];
            String transform = CalculateUtil.transform(s);
            System.out.println(transform);
            BinaryTree binaryTree = CheckUtil.OptiExpression(transform);
            CheckUtil.AdjustmentTree(binaryTree);
            String s1 = CheckUtil.TreeToExpression(binaryTree);
            System.out.println("s1="+s1);
            String new_transform = CalculateUtil.transform(s1);
            System.out.println(new_transform);
            String calculate = CalculateUtil.calculate(new_transform);

            IOUtil.writeTxt((i+1)+"."+s+"=",textPath);
            IOUtil.writeTxt((i+1)+"."+s+"="+calculate+";","src/main/resources/answerfile.txt");

        }
        PageUtil.Exercise();

    }

}

