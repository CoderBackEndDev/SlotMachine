import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Random;


/**
 * Created by ADMIN on 06-Nov-17.
 */
public class Reel implements Runnable {
    // the static symbol array
    static volatile Symbol[] List = new Symbol[6];
    // the local variables
    // the randomized number is saved below
    int value;

    // with the below local variables the images are first assigned to the imgv
    // and then it is set with the method of set graphic to the label
    ImageView imgv;
    Label lblnew;
    // overloaded default constructor

    public Reel(Label lbl) {
        lblnew = lbl;
    }

    // the method used in the  Creation of the Symbol objects
    public static void creatingObjects() throws NullPointerException {
// the objects are created and are assigned values before being assigned into a array
        Symbol Cherry = new Symbol();
        Cherry.SetImage(new Image("/Images/cherry.png"));
        Cherry.SetValue(2);
        Symbol Seven = new Symbol();
        Seven.SetImage(new Image("/Images/redseven.png"));
        Seven.SetValue(7);
        Symbol Bell = new Symbol();
        Bell.SetImage(new Image("/Images/bell.png"));
        Bell.SetValue(6);

        Symbol Watermelon = new Symbol();
        Watermelon.SetImage(new Image("/Images/watermelon.png"));
        Watermelon.SetValue(5);

        Symbol Plum = new Symbol();
        Plum.SetImage(new Image("/Images/plum.png"));
        Plum.SetValue(4);
        Symbol Lemon = new Symbol();
        Lemon.SetImage(new Image("/Images/lemon.png"));
        Lemon.SetValue(3);

        // adding the objects to the array
        List[0] = Cherry;
        List[1] = Seven;
        List[2] = Bell;
        List[3] = Watermelon;
        List[4] = Plum;
        List[5] = Lemon;


    }

    // the randomizer method
    public void Spin() {
// the random function is below
        Random r1 = new Random();
        value = r1.nextInt(5);


    }
// the overridden runmethod after the runnable has been implemented
    @Override
    public void run() {
        creatingObjects();
// this checks if the active thread conunt is less than or equal to zero
        while (!(Thread.activeCount() <= 0)) {


            Platform.runLater(new Runnable() {

// the run method of the platfrom later
                public void run() {
                    // the spin is invoked
                    Spin();
                    // a object of image is created
                    Image imgv4 = new Image(List[value].GetImage().impl_getUrl(), 150, 150, false, false);
                   // the object is assigned to the local Image view
                    imgv = new ImageView(imgv4);
                    // then the image view is set into a label
                    lblnew.setGraphic(imgv);
                }

            });
            try {
                // this is to give some sleeptime between the threads access
                Thread.sleep(100);
            } catch (InterruptedException e) {
                // if an error exsists to print it
                e.printStackTrace();
            }


        }
    }

}
