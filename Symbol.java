import javafx.scene.image.Image;

/**
 * Created by ADMIN on 06-Nov-17.
 */
public class Symbol implements ISymbol {
    // the private intance variables
    private Image image;
    private int value;

    // the default constructor to set the values
    public Symbol(Image image, int v) {
        this.image = image;
        this.value = v;
    }

    public Symbol() {

    }
// this is the comparator that checks the wins and losses
    public static String winStatus(int num, int num1, int num2) {
        String Status;
        if (num == num1) {
            return Status = "Won";
        } else if (num1 == num2) {
            return Status = "Won";
        } else if (num == num2) {
            return Status = "Won";
        } else {
            return Status = "lose";
        }
    }

    // the getter and setter methods
    @Override
    public void SetImage(Image image) {
        this.image = image;
    }

    @Override
    public Image GetImage() {
        return this.image;
    }

    @Override
    public void SetValue(int v) {
        this.value = v;
    }

    @Override
    public int GetValue() {
        return this.value;
    }


}
