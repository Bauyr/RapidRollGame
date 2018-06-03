
package rapidroll;

import static java.lang.Math.PI;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import java.util.Random;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;


public class Desk {
    
    private Pane panel;

    private static final int DELAY = 1;
    ImageView imv;
    int type;
    private Image image;
    private static final int WIDTH = 50;
    private static final int LENGTH = 60;
    private double x;
    private double y;
    private double dx = 20;
     private double dy = 2;
        
    
     public Desk(double x,double y, Pane p,int t){
        this.x = x;
        this.y = y;
        this.type = t;
        panel = p;
        imv = new ImageView();
        if (t == 0)
        image = new Image(getClass().getResourceAsStream("desk.jpg")); else
        image = new Image(getClass().getResourceAsStream("spike.png"));
        imv.setImage(image);
        imv.setFitHeight(15);
        imv.setFitWidth(60);
        panel.getChildren().add(imv);
        imv.setX(getX());
        imv.setY(getY());
       
    }
   
    
     public void Move(){
      setY(getY()-dy);
      imv.setY(getY());
      imv.setX(getX());
     
       
     
     }
      public void setX(double x){this.x=x;}
    public void setY(double y){this.y=y;}
  
    public double getY() {return this.y;}
    
    //public double getHeight(){return this.height;}
    public double getX(){return this.x;}
    public double getWidth(){return WIDTH;}
    public double getLength(){return LENGTH;}
    
}
