/**
 * Created by ADMIN on 16-Nov-17.
 */

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GUI extends Application {
    static Label Slot1;
    static Label Slot2;
    static Label Slot3;
    // declaration and innitialisation of the thread
    static Thread t1;
    static Thread t2;
    static Thread t3;
    // Innitialised local variables
    static int Currentamount = 10;

    // the labels in the GUI
    static int Bet = 0;
    static int WonCreddits;
    static ImageView pic1;
    static ImageView pic2;
    static ImageView pic3;
    static Reel r1;
    static Reel r2;
    static Reel r3;
    //variables created to help with statistics
    static int Stages = 0;
    static int win = 0;
    static int losses = 0;
    static volatile int last;
    static volatile int first;
    static volatile int second;
    BorderPane root = new BorderPane();
    // the buttons in the GUI
    Button maxBet;
    Button betOne;
    Button Stat;
    Button Spin;
    Button AddCoin;
    Button Reset;
    ImageView img1;
    ImageView img2;
    ImageView img3;
    Label Currentcredit;
    Label Currentcreditval;
    Label CurrentBid;
    Label CurrentBidVal;
    Label Welcome;
    //to check if all the reels are stopped
    boolean imgVal1 = false;
    boolean imgVal2 = false;
    boolean imgVal3 = false;
    boolean spinnum = false;
    boolean max = false;
    int spin = 0;
 static int betamount=0;
// the main method to execute the program
    public static void main(String[] args) {
        Reel.creatingObjects();
        launch(args);
    }
// an method created to make use of alert easier
    public static void message(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
// the stage is started
    @Override
    public void start(Stage primaryStage) {
        Stage stage1 = new Stage();
        // innitialising the images
        img1 = new ImageView();
        img2 = new ImageView();
        img3 = new ImageView();
        // innitialising the buttons
        maxBet = new Button("Max bet");
        betOne = new Button("Bet One");
        Stat = new Button("Statistics");
        Spin = new Button("Spin");
        AddCoin = new Button("Add Coin");
        Reset = new Button("Reset");
// the sizes of the buttons are set below
        betOne.setMaxSize(200, 150);
        Stat.setMaxSize(200, 150);
        Spin.setMaxSize(200, 150);
        maxBet.setMaxSize(200, 150);
        AddCoin.setMaxSize(200, 150);
        Reset.setMaxSize(200, 150);

        // innitialising the label
        Currentcredit = new Label("Current Credit");
        Currentcreditval = new Label("10");
        CurrentBid = new Label("Bid Amount");
        CurrentBidVal = new Label("0");
        Welcome = new Label("Test Your Spin");
        Welcome.setFont(new Font("Arial", 40));
        // the Image replacing labels
        Slot1 = new Label();
        Slot2 = new Label();
        Slot3 = new Label();

        Spin.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        //.setFont(Font.font("Arial", FontWeight.BOLD, 18));

        pic1 = new ImageView(new Image("Images/bell.png", 150, 150, false, true));
        pic2 = new ImageView(new Image("Images/cherry.png", 150, 150, false, true));
        pic3 = new ImageView(new Image("Images/plum.png", 150, 150, false, true));
        Slot1.setGraphic(pic1);
        Slot2.setGraphic(pic2);
        Slot3.setGraphic(pic3);
// the gridpane is created to help align the objects in the scene
        GridPane Slot = new GridPane();
        Slot.setPadding(new Insets(10));
        Slot.setHgap(20);
        Slot.setVgap(10);
        // Styling for the buttons are impoted into the java class
        Slot.getStylesheets().add(GUI.class.getResource("myStyles.css").toExternalForm());
// an object of the scene is created
        Scene Window = new Scene(Slot, 800, 800);
        stage1.setScene(Window);
        stage1.setMinHeight(800);
        stage1.setMinWidth(800);
        Slot.setAlignment(Pos.CENTER);



//positioning the element in the grid plane
        Slot.add(Welcome, 18, 1, 30, 9);
        Slot.add(img1, 10, 30, 30, 30);
        Slot.add(img2, 15, 30, 30, 30);
        Slot.add(img3, 20, 30, 30, 30);

        Slot.add(Slot1, 10, 14, 9, 9);
        Slot.add(Slot2, 20, 14, 9, 9);
        Slot.add(Slot3, 30, 14, 9, 9);

        Slot.add(AddCoin, 10, 40, 5, 10);
        Slot.add(betOne, 15, 40, 5, 10);
        Slot.add(maxBet, 20, 40, 5, 10);
        Slot.add(Stat, 25, 40, 5, 10);
        Slot.add(Reset, 30, 40, 5, 10);
        Slot.add(Spin, 18, 30, 15, 10);

        Slot.add(CurrentBid, 13, 60, 5, 2);
        Slot.add(CurrentBidVal, 18, 60, 5, 2);
        Slot.add(Currentcredit, 23, 60, 5, 2);
        Slot.add(Currentcreditval, 29, 60, 5, 2);


        stage1.show();

// the action event handlers with the use of lamda expressions are used below
// the max bet can be used once every spin and bets 3 credits at once
        maxBet.setOnAction((ActionEvent e) -> {

            if (max == false) {
                max = true;
                if (Currentamount >= 3) {
                    if (!maxBet.isPressed()) {
                        int value = 3;
                        Bet = Bet + value;
                        betamount=Bet + value;

                        Currentamount = Currentamount - value;

                        Currentcreditval.setText("" + Currentamount);
                        CurrentBidVal.setText("" + Bet);
                    }
                } else {
                    message("Credit limit reached", "Not enough credits to use the bet max");
                }
            } else {
                message("Invalid", "Maxbet can only be used once");
            }
        });

// can be used until the credit limit is reached and the bet area is increamented by one by each click
        betOne.setOnAction((ActionEvent e) -> {
            if (!(Currentamount <= 0)) {
                if (!betOne.isPressed()) {
                    Currentamount = Currentamount - 1;
                    Bet++;
                    betamount++;
                }
                Currentcreditval.setText("" + Currentamount);
                CurrentBidVal.setText("" + Bet);
            } else {
                message("Credit limit", "Insufficient Credit to bet! add more and try again");

            }
        });

        // reset to return to default
        Reset.setOnAction((ActionEvent e) -> {
            max = false;
            Currentamount = Currentamount + Bet;
            Bet = 0;
            Currentcreditval.setText("" + Currentamount);
            CurrentBidVal.setText("" + Bet);
            betamount=0;
        });

        // where the spinning of the reels happen
        Spin.setOnAction((ActionEvent e) -> {

//if conditions to verify whether the bets were made and to stop the user from pressessing the spin while it is in action.
            if (Bet > 0 && spinnum == false) {
                spinnum = true;
                spin++;
                imgVal1 = false;
                imgVal2 = false;
                imgVal3 = false;

                if (!Spin.isPressed()) {

                    // Spin.setDisable(true);
                    r1 = new Reel(Slot1);
                    r2 = new Reel(Slot2);
                    r3 = new Reel(Slot3);
                    t1 = new Thread(r1);
                    t2 = new Thread(r2);
                    t3 = new Thread(r3);
                    Reset.setDisable(true);
                    t1.start();
                    t2.start();
                    t3.start();
                }
            } else {
                if (spinnum == true) {
                    message("Wait ", "Stop the Reels to Spin again");
                } else {
                    message("Invalid ", "Need to bid to spin");
                }
            }
        });

        // to add more coins for the game with each click the currentamount is increased by 1
        AddCoin.setOnAction((ActionEvent e) -> {

            if (!AddCoin.isPressed()) {
                int value = 1;
                Currentamount = Currentamount + value;

                value++;
                if (Currentamount >= 3) {

                }

            } else {

            }
            Currentcreditval.setText("" + Currentamount);
            CurrentBidVal.setText("" + Bet);


        });
        // the event handler to stop the first thread on the click of the label
        Slot1.setOnMouseClicked(e -> {

            imgVal1 = true;
            t1.stop();
            first = r1.List[r1.value].GetValue();
            System.out.print(first);


            notSpinning();

        });
        // the event handler to stop the second thread on the click of the label
        Slot2.setOnMouseClicked(e -> {
            imgVal2 = true;
            t2.stop();
            second = r2.List[r2.value].GetValue();
            System.out.print(second);
            notSpinning();


        });
        // the event handler to stop the third thread on the click of the label
        Slot3.setOnMouseClicked(e -> {
            imgVal3 = true;

            t3.stop();
            last = r3.List[r3.value].GetValue();
            System.out.print(last);
            notSpinning();


        });
        // to display statistics on how the game is progressing
        Stat.setOnAction((ActionEvent e) -> {
            if (spin >= 1) {
                Stage primary = new Stage();
                Statistics stat = new Statistics();
                try {
                    stat.start(primary);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            } else {
                message("wait", "Insufficient spins to calculate the Stats");

            }

        });

    }
// method to innitiate after the all three threads are stopped
    public void notSpinning() {
        int increment = 1;
        // by the comparator below this is done
        if (imgVal1 == true && imgVal2 == true && imgVal3 == true) {
            // some values are reset to default after the reels are stopped
            Reset.setDisable(false);
            spinnum = false;
            max = false;
            Stages = Stages + 1;
            // values are passed into  the symbol classes comparator to compare the numbers to check if the user won
            Symbol.winStatus(first, second, last);
                // if the value is not won then the individual has lost
            if (!(Symbol.winStatus(first, second, last).equalsIgnoreCase("Won"))) {
                Bet = 0;
                message("LOST", "Lost the bet:");
                Currentcreditval.setText("" + Currentamount);
                CurrentBidVal.setText("" + Bet);
                losses = losses + increment;
            } else {
                // again the values are compared
                if (first == second && second == last) {
                    WonCreddits = first  * Bet;
                    Currentamount = Currentamount + WonCreddits;
                    Bet = 0;
                    message("Won", "Won Credits:" + WonCreddits);
                    Currentcreditval.setText("" + Currentamount);
                    CurrentBidVal.setText("" + Bet);
                    win = win + increment;
                } else {
                    if (first == second) {
                        WonCreddits = first * Bet;
                        Currentamount = Currentamount + WonCreddits;
                        Bet = 0;
                        message("Won", "Won Credits:" + WonCreddits);
                        Currentcreditval.setText("" + Currentamount);
                        CurrentBidVal.setText("" + Bet);
                        win = win + increment;
                    } else if (second == last) {
                        WonCreddits = second * Bet;
                        Currentamount = Currentamount + WonCreddits;
                        Bet = 0;
                        message("Won", "Won Credits:" + WonCreddits);
                        Currentcreditval.setText("" + Currentamount);
                        CurrentBidVal.setText("" + Bet);
                        win = win + increment;
                    } else if (first == last) {
                        WonCreddits = first * Bet;
                        Currentamount = Currentamount + WonCreddits;
                        Bet = 0;
                        message("Won", "Won Credits:" + WonCreddits);
                        Currentcreditval.setText("" + Currentamount);
                        CurrentBidVal.setText("" + Bet);
                        win = win + increment;
                    }

                }

            }

        }
    }


}
