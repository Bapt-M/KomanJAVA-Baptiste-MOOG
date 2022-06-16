package GameManagement;

import com.google.gson.annotations.Expose;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Cell {
    @Expose
    public static int Width = 48;
    @Expose
    public static int Height = 48;
    @Expose
    private int _x;
    @Expose
    private int _y;

    private Rectangle rectangle;

    @Expose
    private CellMaterial _material;
    @Expose
    private CellMaterialProperties _materialProperties;

    public Cell(CellMaterial material, int x, int y) {
        _material = material;
        _materialProperties = CellMaterialProperties.getCellMaterialProperties(material);
        _x = x*Width;
        _y = y*Height;
    }

    public Cell(String imgPath, int x, int y) {
        _x = x*Width;
        _y = y*Height;
        rectangle = new Rectangle(_x,_y, Height, Width);
        Image imgCharacter = new Image(imgPath, 48, 48, true, false);
        rectangle.setFill(new ImagePattern(imgCharacter));
    }

    public Rectangle getRectangle() {
        if (rectangle != null) return rectangle; //Rectangle is already created for character

        if (_materialProperties == null) {
            _materialProperties = CellMaterialProperties.getCellMaterialProperties(_material);
        }
        rectangle = new Rectangle(_x, _y, Height, Width);
        Image img = new Image(_materialProperties.ImgPath, Width, Height, true, false);
        rectangle.setFill(new ImagePattern(img));
        return rectangle;
    }

    public CellMaterialProperties getMaterialProperties() {
        return _materialProperties;
    }

    public int getX() {
        return _x;
    }
    public int getY() {
        return _y;
    }
}
