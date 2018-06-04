import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by ADMIN on 22-Nov-17.
 */

public class Statistics extends Application {
    //the GUI for the statistics
    // below are the variables and data recieved as the spin is run it is
    double averagecreditswon = ((GUI.WonCreddits) / GUI.Stages);
    int wins = GUI.win;
    int Losses = GUI.losses;
    Label creditsWon;
    Label winings;
    Label Loss;
    Button Save;

// the overridden start method of the application class after the application class is extended
    @Override
    public void start(Stage primaryStage) throws Exception {
// the use of a stack plane to adjust the positioning of the content in the scene
        StackPane Sp = new StackPane();

        Scene scene = new Scene(Sp, 600, 700);
        primaryStage.setTitle(" Statistics ");
        // external css was imported
        Sp.getStylesheets().add(GUI.class.getResource("myStyles.css").toExternalForm());
// the pie chart was choosen as it clearly indicates the amount won and loss in a easy and understandable manner.
        PieChart pieChart = new PieChart();
        pieChart.setTitle("User Statistics");
        PieChart.Data won = new PieChart.Data("Wins", wins);
        PieChart.Data Lose = new PieChart.Data("Losses", Losses);
        pieChart.setLegendSide(Side.BOTTOM);
        pieChart.setLabelsVisible(true);
        // the wins and losses were added to the pie chart
        pieChart.getData().add(won);
        pieChart.getData().add(Lose);
        pieChart.setMaxSize(450,450);
        // the labels are innitialised below
        creditsWon = new Label("Average Credits won: " + averagecreditswon + " ");
        winings = new Label("Number of wins: " + wins + " ");
        Loss = new Label("Number of losses: " + Losses + " ");
        Save = new Button("Save to Text");
        // the save onclick event is handled below
        Save.setOnAction((ActionEvent e) -> {
            Date date = new Date();
            SimpleDateFormat SDF = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");

            try {

                FileWriter fw = new FileWriter("Statistics/"+SDF.format(date) + ".txt", true);

                BufferedWriter bw = new BufferedWriter(fw);


                bw.write(wins + "#" + Losses + "#" + averagecreditswon);
                bw.newLine();

                bw.flush();
                bw.newLine();
                fw.close();
                GUI.message("Saved", "The Statistics have been saved to a text file");

            } catch (IOException ex) {
                System.err.println(ex);
            }


        });
        // the positioning was set with the use of the x and y coordinates
            winings.setTranslateY(200);
            Loss.setTranslateX(-150);
            Loss.setTranslateY(220);
            creditsWon.setTranslateX(-150);
            creditsWon.setTranslateY(240);
            Save.setTranslateX(200);
            Save.setTranslateY(300);
            pieChart.setTranslateX(0);
            pieChart.setTranslateY(-100);
            // the labels are added into the stack plane
        Sp.getChildren().addAll(creditsWon,winings,Loss,Save,pieChart);
        primaryStage.setScene(scene);
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.showAndWait();


    }
}
