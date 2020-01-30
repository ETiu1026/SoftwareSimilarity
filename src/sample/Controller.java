package sample;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.awt.*;

public class Controller {
    public GridPane gridPane;
    public void initialize(){


        SimilarityChecker Main = new SimilarityChecker();
        Main.run();

        double[][] scores = Main.getSimilarityScore();
        String[] names = Main.getNamesString();

        Text[][] label= new Text[Main.getSubmissionSize()][Main.getSubmissionSize()];
        gridPane.setPadding(new Insets(6,6,6,6));

        for(int i=1;i<Main.getSubmissionSize()+1;i++)
        {
            gridPane.add(new Text(" "+names[i-1]+" "),i+1,1);
            gridPane.add(new Text(" "+names[i-1]+" "),1,i+1);
        }
        for(int i=1;i<Main.getSubmissionSize()+1;i++)
        {
            for(int j=1;j<Main.getSubmissionSize()+1;j++){
                label[i-1][j-1]=new Text(" "+String.valueOf(scores[i-1][j-1])+" ");
                gridPane.add(label[i-1][j-1],i+1,j+1);
            }
        }

    }



}
