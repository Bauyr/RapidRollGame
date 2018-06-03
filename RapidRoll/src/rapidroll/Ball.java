
package rapidroll;

import java.io.InputStream;
import java.net.URL;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;


public class Ball {
    private double dx = 12;
    private double dy = 3;
    private double dyDesk = 2;
    private double x;
    private double y;
   
    private double width = 30;
    private double height = 30;
    public int score = 0;
    public ImageView imv;
    Image image; 
    AudioClip clip;
    public boolean gameIsOver = false;
    public boolean intersects;
    private Pane panel;
    
    
    
    public Ball(double x, double y, Pane BallContainer){
    this.x = x;
    this.y = y;
       
    imv = new ImageView();
    image =new  Image(getClass().getResourceAsStream("mainball.png"));
    imv.setImage(image);
    
    
    URL resource = getClass().getResource("bounce.wav");
    clip = new AudioClip(resource.toString());
    
    
    }
    public void remove(Pane BallContainer){
        BallContainer.getChildren().remove(imv);
        }
    public void apeer(Pane BallContainer){
        BallContainer.getChildren().add(imv);
        imv.setX(150);
        imv.setY(150);
    }
  
    public void Move(){
        setY(getY()+dy);
        
        if(RapidRoll.touch == false) RapidRoll.score += dy;
        RapidRoll.lbl.setText(Integer.toString(RapidRoll.score));
        
       imv.setX(getX());
       imv.setY(getY());
       if (getY()<10 ||getY()>500 ){ gameIsOver = true;
       
       }
      
    }
    public void moveRight(double maxX)
    {
        setX(x+dx);
        if(getX()+width>=maxX){
          x = maxX -width;
        }
        imv.setX(x);
       imv.setY(getY());
    }
    public void moveLeft(double maxX)
    {
     setX(x-dx);
      if(getX()<=0){
          x = 0;
        }
        imv.setX(x);
       imv.setY(getY());
    }
    
    
    public void Intersect(){
        setY(getY()-dyDesk-dy);
        imv.setX(getX());
        imv.setY(getY());
    
    }
   
    
     public void setX(double x){this.x=x;}
    public void setY(double y){this.y=y;}
    public int getScore(){return this.score;}
    public double getX() {return this.x;}
    public double getY() {return this.y;}
    public boolean getGame(){return this.gameIsOver;}
    public double getWidth(){return this.width;}
    public double getHeight(){return this.height;}
    
    
    
}
