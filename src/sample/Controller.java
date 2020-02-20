package sample;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import javafx.scene.control.TextField;

import java.io.File;

public class Controller {
    public GridPane gridPane,top5Pane;
    public Button generateBtn;
    public TextField pathTextField;
    public Text errorText;

    public void generateTable(){
        try {
            SimilarityChecker main = new SimilarityChecker();
            main.setPathName(pathTextField.getText());
            main.run();

            double[][] scores = main.getSimilarityScore();
            String[] names = main.getNamesString();
            PairScore[] top5 = new PairScore[5];

            for(int i=0;i<5;i++)
            {
                top5[i]= new PairScore();
            }

            Text[][] label= new Text[main.getSubmissionSize()][main.getSubmissionSize()];
            StackPane[][] stack = new StackPane[main.getSubmissionSize()][main.getSubmissionSize()];

            for(int i=1;i<main.getSubmissionSize()+1;i++)
            {
                gridPane.add(new Text("     "+names[i-1]+"     "),i+1,1);
                gridPane.add(new Text("  "+names[i-1]+"  "),1,i+1);
            }
            for(int i=1;i<main.getSubmissionSize()+1;i++)
            {
                for(int j=1;j<main.getSubmissionSize()+1;j++){
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

                    if(scores[i-1][j-1]<=1 && scores[i-1][j-1]>.5) stack[i-1][j-1].setStyle("-fx-background-color: red");
                    else if(scores[i-1][j-1]<=.5 && scores[i-1][j-1]>.25) stack[i-1][j-1].setStyle("-fx-background-color: orange");
                    else if(scores[i-1][j-1]<=.25 && scores[i-1][j-1]>-.25) stack[i-1][j-1].setStyle("-fx-background-color: yellow");
                    else if(scores[i-1][j-1]<=-.25 && scores[i-1][j-1]>-.5) stack[i-1][j-1].setStyle("-fx-background-color: lightgreen");
                    else if(scores[i-1][j-1]<=-.5) stack[i-1][j-1].setStyle("-fx-background-color: green");

                    gridPane.add(stack[i-1][j-1],i+1,j+1);
                }
            }

            //Creates the top 5 similarity table
            for(int i=0;i<5;i++)
            {
                if(top5[i].getName()[1]!=null) {
                    System.out.println(top5[i].getName()[0] + "  AND  " + top5[i].getName()[1] + " : " + top5[i].getScore());
                    top5Pane.add(new Label(" " + top5[i].getName()[0] + " "), 1, i + 1);
                    top5Pane.add(new Label(" " + top5[i].getName()[1] + " "), 2, i + 1);
                    top5Pane.add(new Label(" " + top5[i].getScore() + " "), 3, i + 1);
                }
            }
            gridPane.setVisible(true);
            top5Pane.setVisible(true);
            errorText.setVisible(false);
        }
        catch ( NullPointerException e)
        {
            System.out.println("invalid path");
            errorText.setVisible(true);
        }


    }

    public void initialize(){
        gridPane.setVisible(false);
        top5Pane.setVisible(false);
        errorText.setVisible(false);
        pathTextField.setOnDragOver(this::handle);
        pathTextField.setOnDragDropped(this::drop);

    }

    //Resets tables for next input
    private void reset(){
        gridPane.getChildren().clear();
        gridPane.setGridLinesVisible(false);
        gridPane.setGridLinesVisible(true);
        top5Pane.getChildren().clear();
        top5Pane.setGridLinesVisible(false);
        top5Pane.setGridLinesVisible(true);
    }

    //Cursor with file has entered textfield area
    public void handle(DragEvent event){
        System.out.println("Enter");
        event.acceptTransferModes(TransferMode.ANY);
        event.consume();

    }

    //The dropped file's path will be placed on the textfield
    public void drop(DragEvent event){
        System.out.println("Drop");
        Dragboard db = event.getDragboard();
        boolean success = false;
        if(db.hasFiles()){
            File f = db.getFiles().get(0);
            pathTextField.setText(f.getAbsolutePath());
            success=true;
        }
        event.setDropCompleted(true);
        event.consume();
    }




}
