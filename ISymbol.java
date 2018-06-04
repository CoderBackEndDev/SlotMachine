import javafx.scene.image.Image;

/**
 * Created by ADMIN on 06-Nov-17.
 */
public interface ISymbol {
    public void SetImage(Image image);

    public Image GetImage();

    public void SetValue(int v);

    public int GetValue();

}
