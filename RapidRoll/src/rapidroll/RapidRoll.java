/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rapidroll;

import java.io.File;
import java.security.Key;
import java.util.ListIterator;
import java.util.Random;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

/**
 *
 * @author Bauka
 */
public class RapidRoll extends Application {
    private ObservableList<Desk> desks = FXCollections.observableArrayList();
   // static boolean dead = false;
    static boolean scoring = false;
    static int score=0;
    static Label lbl;
    static boolean touch;
    Scene MainM;
    AnimationTimer a;
   
    public void createDesks(Pane BallContainer ){
    final Random rng = new Random();
    int space = 100;
    int yaxis = 350;
    int prev = 1;
        for (int i = 0; i < 500; i++) {
            yaxis += space;
            int type = rng.nextInt(2);
            if (prev == 1) type = 0;
            
            Desk desk = new Desk(30+rng.nextInt(250),yaxis,BallContainer,type);
            desks.add(desk);
            prev = type;
        }
    }
    
    
    @Override
    public void start(Stage primaryStage) {
        final Pane BallContainer = new Pane();
        final GridPane MainMenu = new GridPane();
        MainMenu.setHgap(10); 
        MainMenu.setVgap(10);
        MainMenu.setPadding(new Insets(25,25,25,25));
        BallContainer.setStyle("-fx-background-image: url(/rapidroll/blade.png);"
                            + "-fx-background-repeat: repeat-x;"
                            + "-fx-background-color: white;"
                            + "-fx-background-position: top;"
                /*  +"-fx-image: url(/rapidroll/wall.png);"
                + "-fx-repeat: repeat-y;"
                + "-fx-background-position: left;"
                +"-fx-background-image: url(/rapidroll/wall.png);"
                + "-fx-background-repeat: repeat-y;"
                + "-fx-background-position: right;"*/
                            + "-fx-border-color: black;"
                            + "-fx-border-width: 1;"
                            + "-fx-border-radius: 6;"
                            + "-fx-padding: 6;");
        final BorderPane root = new BorderPane();
        HBox menu = new HBox();
        
        menu.setAlignment(Pos.CENTER);
        
        menu.setStyle("-fx-background-color: white;"
                            + "-fx-border-color: black;"
                            + "-fx-border-width: 1;"
                            + "-fx-border-radius: 6;"
                            + "-fx-padding: 6;");
        MainMenu.setStyle("-fx-background-image: url(/rapidroll/Menu.jpg);"
                             + "-fx-background-repeat: stretch;"
                            + "-fx-background-position: center center;");
        root.setCenter(BallContainer);
        root.setTop(menu);
        root.setId("pane");
       // primaryStage.getScene().getStylesheets().add(RapidRoll.class.getResource("style.css").toExternalForm());
      
            
          lbl = new Label("0");
          Ball Ballimg =  new Ball(150,150,BallContainer);
          Desk desk = new Desk(300,550,BallContainer,0);
          
        root.setBottom(lbl);
        //System.out.println(score);
        ////////////////////////////////////////
        //root.getChildren().add(lbl);
        //root.setBottom(lbl);
        Button btn = new Button("Start game!");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.CENTER);
        hbBtn.getChildren().add(btn);
        MainMenu.add(hbBtn, 15, 15);
        
        Button exit = new Button("Exit!");
        HBox ExBtn = new HBox(10);
        ExBtn.setAlignment(Pos.CENTER);
        ExBtn.getChildren().add(exit);
        MainMenu.add(ExBtn, 15, 20);

        // menu.getChildren().add(btn);
        exit.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(final ActionEvent event) {
                        System.exit(0);
                       
                    }
                });
        
        /////////////////////////////////////
        
        /////////////////////////////
        Scene scene = new Scene(root, 400, 500);
        MainM = new Scene(MainMenu, 620,360);
        //MainM.getStylesheets().add(RapidRoll.class.getResource("style.css").toExternalForm());
        primaryStage.setTitle("Rapid Roll!");
        primaryStage.setScene(MainM);
        primaryStage.show();
        
        
         btn.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(final ActionEvent event) {
                        primaryStage.setScene(scene);
                        Ballimg.remove(BallContainer);
                        Ballimg.apeer(BallContainer);
                    }
                });
        
        ////////////////////////////
         
        createDesks(BallContainer);
        
        a = new AnimationTimer(){
                        @Override
            public void handle(long now) {
                
                             desk.Move();
                             Intersetion(Ballimg, desk);
                           Ballimg.Move();
                 touch = false;         
                 for (ListIterator<Desk> fastIt = desks.listIterator(); fastIt.hasNext();){
                     Desk b1 = fastIt.next();
                     b1.Move();
                     Intersetion(Ballimg, b1);
//                     Media bauka = new Media(new File("C:\\Users\\Бауыржан\\Documents\\NetBeansProjects\\RapidRoll\\src\\rapidroll\\bounce.wav").toURI().toString());
//                     MediaPlayer d = new MediaPlayer(bauka);
//                     d.play();
                     
                 }     
                 
                  if(Ballimg.gameIsOver)
                  {
                      primaryStage.setScene(MainM);
                      Ballimg.imv.setX(150);
                      Ballimg.imv.setY(150);
                      Ballimg.setX(150);
                      Ballimg.setY(150);
                      for (ListIterator<Desk> fastIt = desks.listIterator(); fastIt.hasNext();){
                          Desk tmp = fastIt.next();
                          BallContainer.getChildren().remove(tmp.imv);
                      }
                        desks.clear();
                        createDesks(BallContainer);
                      Ballimg.gameIsOver = false;
                      score = 0;
                      a.stop();
                  }
                 System.out.println(Ballimg.getScore());
                    
         }
        };
                 scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
                     
                     @Override
             
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.SPACE){ 
                    a.start();
            }
                        
            else if(event.getCode() == KeyCode.RIGHT){
                    Ballimg.moveRight(scene.getWidth());
                    }
             else if(event.getCode() == KeyCode.LEFT){
                    Ballimg.moveLeft(scene.getWidth());
                }
            }
        });
                
    }
     
    /**
     * @param args the command line arguments
     */
    public void Intersetion(Ball b1, Desk d1 ){
        if((b1.getX()+30>=d1.getX() && b1.getX()<d1.getX()+d1.getLength()) 
                       && b1.getY()+27>=d1.getY() && b1.getY()+27 <= d1.getY()+10)
            
        
        {
            if (d1.type == 1) b1.gameIsOver = true;
            b1.Intersect();
            touch = true;
        }
    }
    
   
    
    
    public static void main(String[] args) {
        launch(args);
    }

    
    
}
