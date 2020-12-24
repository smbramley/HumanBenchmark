/**
 * @Author Shane Bramley
 * @Date 10/27/2020
 *
 * VisualMemory, This class will control the visual memory
 * human benchmark. This benchmark will test,
 * "Remember an increasingly large board of squares"
 */

package main.backend;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.util.Duration;
import main.Loader;

import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;

/**
 * VisualMemory
 * This class is used to control the logic
 * of the VisualMemory application
 *
 */
public class VisualMemory {

    public ImageView mainImage;
    public GridPane gridVisualHolder;
    public Button startButton;
    public Label Level;
    public Label Lives;
    @FXML
    private Pane root;
    @FXML
    private final String homePage;
    private static final String directory = "resources/";
    private final String hoverMusic;
    private final String clickMusic;
    private int gridX;
    private int gridY;
    private int gridSize;
    private int lives;
    private int level;
    private int totalTiles;
    private int totalTilesFinal;
    private int failedClicks;
    private int successfulClicks;
    private List<Integer> Xs = new ArrayList<>();
    private List<Integer> Ys = new ArrayList<>();

    /**
     * VisualMemory()
     * This method is the constructor of the class
     * it is used to initialize the value of the variables
     *
     */
    public VisualMemory() {
        homePage = directory + "home.fxml";
        clickMusic = "/resource/MRTK_Shell_Click_Out.wav";
        hoverMusic = "/resource/MRTK_Shell_Click_Init.wav";
        gridX=3;
        gridY=3;
        lives=3;
        totalTiles=3;
        totalTilesFinal=3;
        failedClicks=0;
        successfulClicks=0;
        level=1;
    }

    /**
     * initialize()
     * This method is used to initialize values on page start
     *
     */
    public void initialize() { }

    /**
     * home()
     * This method is used to call the and change to the homepage
     * this is the on mouse click
     *
     * @param actionEvent
     */
    public void home(MouseEvent actionEvent) {
        AudioClip hover = new AudioClip(this.getClass().getResource(clickMusic).toString());
        hover.play();
        Scene scene = root.getScene();
        Pane newRoot = Loader.loadFxmlFile(homePage);
        scene.setRoot(newRoot);
    }

    /**
     * createRandomGrid()
     * This method is used to
     * set up the random grid items
     *
     */
    public void createRandomGrid(){
        for(int i=0;i<gridX;i++){
            Xs.add(i);
            Ys.add(i);
        }
        Collections.shuffle(Xs);
    }

    /**
     * startClicked()
     * This method is used to start the visual
     * grid application
     *
     * @param mouseEvent
     * @throws URISyntaxException
     */
    public void startClicked(MouseEvent mouseEvent) throws URISyntaxException {
        lives=3;
        Level.setVisible(true);
        Lives.setVisible(true);
        startButton.setVisible(false);
        mainImage.setVisible(false);
        Level.setText("Level: "+level);
        Lives.setText("Lives: "+lives);
        gridSize=gridX*gridY;
        createRandomGrid();
        populateGrid();
        new VisualTimer(1,gridVisualHolder);
    }

    /**
     * populateGrid()
     * This method is used to build and populate the
     * tiles for the grid
     *
     * @throws URISyntaxException image is not found exception is thrown
     */
    public void populateGrid() throws URISyntaxException {
        totalTiles=totalTilesFinal;
        int count=0;
        boolean check = false;
        boolean checkClicked = false;
        for(int i=0;i<Xs.size();i++) {
            for (int j = 0;j<Ys.size();j++) {
                final Image image;
                if (count < totalTiles) {
                    image = new Image(getClass().getResource("/resource/ChimpTestSolid.PNG").toURI().toString());
                    count++;
                    check=true;
                } else {
                    image = new Image(getClass().getResource("/resource/VisualMemoryTile.PNG").toURI().toString());
                    check=false;
                }
                final ImageView button = new ImageView(image);
                boolean finalCheck = check;
                button.setOnMouseClicked(mouseEvent -> {
                    try {
                        checkSelectedTile(button, finalCheck);
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    }
                });
                gridVisualHolder.add(button, Xs.get(i), Ys.get(j));
            }
        }
    }

    /**
     * checkSelectedTile()
     * This method is called by the tile click
     * it checks to see if the image is a tile that
     * cant be selected
     *
     * @param iv
     * @param check
     * @throws URISyntaxException
     */
    public void checkSelectedTile(ImageView iv,boolean check) throws URISyntaxException {
        if(check){
            iv.setOnMouseClicked(mouseEvent -> {});
            Image image = new Image(getClass().getResource("/resource/ChimpTestSolid.PNG").toURI().toString());
            iv.setImage(image);
            successfulClicks++;
        }
        else {
            Image image = new Image(getClass().getResource("/resource/VisualMemoryTileDark.PNG").toURI().toString());
            iv.setImage(image);
            failedClicks++;
        }

        if(failedClicks==3){
            resetLevel();
        }
        else if(successfulClicks==totalTiles){
            nextLevel();
        }
    }

    /**
     * nextLevel()
     * This method will reset the board and add to the total tiles
     *
     * @throws URISyntaxException image not found throw exception
     */
    public void nextLevel() throws URISyntaxException {
        successfulClicks=0;
        failedClicks=0;
        level++;
        totalTilesFinal++;
        if(totalTilesFinal==5){
            gridX++;
            gridY++;
        }
        if(totalTilesFinal==8){
            gridX++;
            gridY++;
        }
        if(totalTilesFinal==12){
            gridX++;
            gridY++;
        }
        Xs.clear();
        Ys.clear();
        Level.setText("Level: "+level);
        Lives.setText("Lives: "+lives);
        gridVisualHolder.getChildren().clear();
        gridSize=gridX*gridY;
        createRandomGrid();
        populateGrid();

        new VisualTimer(1,gridVisualHolder);
    }

    /**
     * resetLevel()
     * This method used to reset if lost subtract lives or call
     * gameover()
     *
     */
    public void resetLevel(){
        lives--;
        if(lives==0){
            gameOver();
        }
    }

    /**
     * gameOver()
     * This method is used to reset the
     * entire game if lives are up
     *
     */
    public void gameOver(){

    }
}

/**
 * Reminder
 * This class is used to check the grid and
 * set a second few seconds tile change so
 * the user can see the tiles to select
 *
 */
class VisualTimer {
    Timer timer;
    GridPane grid;

    /**
     * Reminder()
     * This method is the constructor
     * of the class it takes in the seconds and
     * the gridPane being changed
     *
     * @param seconds - the amount of time till change
     * @param gridVisualHolder - grid that holds the tiles
     */
    public VisualTimer(int seconds, GridPane gridVisualHolder) {
        grid = gridVisualHolder;
        timer = new Timer();
        timer.schedule(new RemindTask(), seconds * 1000);
    }

    class RemindTask extends TimerTask {
        public void run() {
            for(int i=0;i<grid.getChildren().size();i++){
                try {
                    ((ImageView)grid.getChildren().get(i)).setImage(new Image(getClass().getResource("/resource/VisualMemoryTile.PNG").toURI().toString()));
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
            }
            timer.cancel(); //Terminate the timer thread
        }
    }
}
