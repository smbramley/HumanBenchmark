/**
 * @Author Shane Bramley
 * @Date 10/27/2020
 *
 * ChimpTest, This class will test,
 * "Are you smarter than a chimpanzee?"
 * All of the logic will be in this
 * class to run the chimp test including
 * next level and populate grid
 */

package main.backend;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import main.Loader;

//import javax.swing.text.html.ImageView;
//import java.awt.*;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ChimpTest {

    public VBox numberVBox;
    public Pane holderPane;
    public VBox mainImage;
    public Label displayScore;
    public VBox nextLevelChimpTestButton;
    public Label numbers;
    public Label strikesLabel;
    public Label displayStrikes;
    @FXML
    private Pane root;
    @FXML
    private Button startChimpTestButton;
    private final String homePage;
    private static final String directory = "resources/";
    private int totalNumbers;
    private int startNumber;
    private int strikes;
    private int score;
    private List<GridPane> currentNumbers = new ArrayList<>();
    //private int gridSizeX;
    //private int gridSizeY;

    private final String hoverMusic;
    private final String clickMusic;

    /**
     * ChimpTest()
     * This method is the constructor of the class
     */
    public ChimpTest(){
        homePage = directory + "home.fxml";
        clickMusic = "/resource/MRTK_Shell_Click_Out.wav";
        hoverMusic = "/resource/MRTK_Shell_Click_Init.wav";
        totalNumbers=4;
        startNumber=1;
        strikes=0;
    }

    /**
     * initialize()
     * this method is used to initialize variables on start
     */
    public void initialize() { }

    public void home(MouseEvent actionEvent){
        AudioClip hover = new AudioClip(this.getClass().getResource(clickMusic).toString());
        hover.play();
        Scene scene = root.getScene();
        Pane newRoot = Loader.loadFxmlFile(homePage);
        scene.setRoot(newRoot);
        displayScore.setStyle("-fx-font-size: 35;");
    }

    /**
     * startChimpTest()
     * This method will start the chimp test on click
     * on the start button then clicks the populate grid method
     * to populate the number grid
     *
     * @param mouseEvent
     * @throws Exception
     */
    public void startChimpTest(MouseEvent mouseEvent) throws Exception {
        AudioClip hover = new AudioClip(this.getClass().getResource(clickMusic).toString());
        hover.play();
        startNumber=1;
        strikes=0;
        totalNumbers=4;
        startChimpTestButton.setVisible(false);
        mainImage.setVisible(false);
        numbers.setVisible(false);
        displayScore.setVisible(false);
        displayStrikes.setVisible(false);
        strikesLabel.setVisible(false);
        populateGrid();
    }

    /**
     * populateGrid()
     * This method will populate the number grid for the chimp test
     *
     * @throws Exception thrown for not finding the image in resources
     */
    public void populateGrid() throws Exception {
        GridPane gridHolder = new GridPane();
        for(int i=1;i<=totalNumbers;i++) {
            int number = i;
            GridPane gridPane = new GridPane();
            final Image image = new Image(getClass().getResource("/resource/ChimpTestNumberExit.png").toURI().toString());
            final ImageView button = new ImageView(image);
            final Label label = new Label(String.valueOf("  "+number));
            label.setStyle("-fx-font-size:50;");
            label.setTextFill(Color.WHITE);

            gridPane.add(button,0,0);
            gridPane.add(label,0,0);
            gridPane.prefWidth(30);
            gridPane.prefHeight(30);
            Random rand = new Random();
            int lowX = 1;
            int highX = 30;
            int lowY = 1;
            int highY = 30;
            int resultX = rand.nextInt(highX - lowX) + lowX;
            int resultY = rand.nextInt(highY - lowY) + lowY;
            gridHolder.add(gridPane,resultX,resultY);

            gridPane.setOnMouseClicked(mouseEvent -> {
                AudioClip hover = new AudioClip(this.getClass().getResource(clickMusic).toString());
                hover.play();
                try {
                    numberCheck(number,gridPane);
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
            });
            gridPane.setOnMouseEntered(mouseEvent -> {
                AudioClip hover = new AudioClip(this.getClass().getResource(hoverMusic).toString());
                hover.play();
                try {
                    button.setImage(new Image(getClass().getResource("/resource/ChimpTestNumberHover.png").toURI().toString()));
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
            });
            gridPane.setOnMouseExited(mouseEvent -> {
                try {
                    button.setImage(new Image(getClass().getResource("/resource/ChimpTestNumberExit.png").toURI().toString()));
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
            });
            currentNumbers.add(gridPane);
        }
        holderPane.getChildren().add(gridHolder);
    }

    /**
     * numberCheck()
     * This method will check the number clicked then
     * remove this grid item if its a correct number to click
     *
     * @param number pass in number to see the number on click
     * @param gridPane gridPane holds the current number clicked
     * @throws URISyntaxException
     */
    public void numberCheck(int number,GridPane gridPane) throws URISyntaxException {
        if(number==1&&totalNumbers!=4){
            for(int i=0;i<currentNumbers.size();i++){
                final Image image = new Image(getClass().getResource("/resource/ChimpTestSolid.PNG").toURI().toString());
                final ImageView button = new ImageView(image);
                currentNumbers.get(i).add(button,0,0);
            }
        }
        if(number==startNumber&&number==totalNumbers){
            totalNumbers++;
            holderPane.getChildren().clear();
            score = totalNumbers;
            displayScore.setText(String.valueOf(score));
            displayScore.setVisible(true);
            nextLevelChimpTestButton.setVisible(true);
            numbers.setVisible(true);
            strikesLabel.setVisible(true);
            displayStrikes.setVisible(true);
            displayStrikes.setText(strikes+" of 3");
        }
        else if(number!=startNumber){
            strikes++;
            if(strikes==3){
                strikesLabel.setVisible(true);
                displayStrikes.setVisible(true);
                displayStrikes.setText(strikes+" of 3");
                startChimpTestButton.setVisible(true);
                //mainImage.setVisible(true);
                score = totalNumbers;
                numbers.setVisible(true);
                displayScore.setText(String.valueOf(score));
                displayScore.setVisible(true);
                holderPane.getChildren().clear();
            }
            else {
                holderPane.getChildren().clear();
                displayScore.setVisible(true);
                nextLevelChimpTestButton.setVisible(true);
                numbers.setVisible(true);
                strikesLabel.setVisible(true);
                displayStrikes.setVisible(true);
                displayStrikes.setText(strikes + " of 3");
            }
        }
        else{
            gridPane.setVisible(false);
            startNumber++;
        }
    }

    /**
     * nextLevel()
     * This method will be called on
     * click of the next level button this will reset everything then
     * show the number grid
     *
     * @param mouseEvent on click of the next level button
     * @throws Exception this is thrown if the mouse click fails
     */
    public void nextLevel(MouseEvent mouseEvent) throws Exception {
        currentNumbers.clear();
        nextLevelChimpTestButton.setVisible(false);
        numbers.setVisible(false);
        strikesLabel.setVisible(false);
        displayStrikes.setVisible(false);
        displayScore.setVisible(false);
        startNumber=1;
        populateGrid();
    }
}
