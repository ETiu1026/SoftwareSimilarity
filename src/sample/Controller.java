package sample;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.awt.*;

public class Controller {
    public GridPane gridPane,top5Pane;
    public void initialize(){


        SimilarityChecker Main = new SimilarityChecker();
        Main.run();

        double[][] scores = Main.getSimilarityScore();
        String[] names = Main.getNamesString();
        PairScore[] top5 = new PairScore[5];

        for(int i=0;i<5;i++)
        {
            top5[i]= new PairScore();
        }

        Text[][] label= new Text[Main.getSubmissionSize()][Main.getSubmissionSize()];
        StackPane[][] stack = new StackPane[Main.getSubmissionSize()][Main.getSubmissionSize()];

        for(int i=1;i<Main.getSubmissionSize()+1;i++)
        {
            gridPane.add(new Text("     "+names[i-1]+"     "),i+1,1);
            gridPane.add(new Text("  "+names[i-1]+"  "),1,i+1);
        }
        for(int i=1;i<Main.getSubmissionSize()+1;i++)
        {
            for(int j=1;j<Main.getSubmissionSize()+1;j++){
                label[i-1][j-1]=new Text(" "+String.valueOf(scores[i-1][j-1])+" ");

                if((i-1)!=(j-1))
                {
                    for(int k=0;k<5;k++)
                    {
                        if(top5[k].getScore()<=scores[i-1][j-1])
                        {
                            top5[k].setName(names[i-1],names[j-1]);
                            top5[k].setScore(scores[i-1][j-1]);
                            break;
                        }
                    }
                }

                stack[i-1][j-1]= new StackPane();
                stack[i-1][j-1].getChildren().addAll(label[i-1][j-1]);

                if(scores[i-1][j-1]<=1 && scores[i-1][j-1]>.5) label[i-1][j-1].setFill(Color.RED);
                else if(scores[i-1][j-1]<=.5 && scores[i-1][j-1]>.25) label[i-1][j-1].setFill(Color.ORANGE);
                else if(scores[i-1][j-1]<=.25 && scores[i-1][j-1]>-.25) label[i-1][j-1].setFill(Color.YELLOW);
                else if(scores[i-1][j-1]<=-.25 && scores[i-1][j-1]>-.5) label[i-1][j-1].setFill(Color.GREEN);
                else if(scores[i-1][j-1]<=-.5) {
                    label[i-1][j-1].setFill(Color.BLUE);
                }

                gridPane.add(stack[i-1][j-1],i+1,j+1);
            }
        }

        for(int i=0;i<5;i++)
        {
            System.out.println(top5[i].getName()[0]+"  AND  "+top5[i].getName()[1]+" : "+top5[i].getScore());
            top5Pane.add(new Label(" "+top5[i].getName()[0]+" "),1,i+1);
            top5Pane.add(new Label(" "+top5[i].getName()[1]+" "),2,i+1);
            top5Pane.add(new Label(" "+top5[i].getScore()+" "),3,i+1);
        }

    }



}
