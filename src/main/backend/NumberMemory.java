/**
 * @Author Shane Bramley
 * @Date 10/27/2020
 *
 * NumberMemory, This class will control the Number Memory
 * human benchmark. This benchmark will test,
 * "Remeber the longest number you can"
 */

package main.backend;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import main.Loader;
import org.w3c.dom.Text;

import java.net.URISyntaxException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * NumberMemory
 * This class implements initialize in order to
 * have an initialization of the values of variables
 * This class is used to run the number memory logic
 */
public class NumberMemory implements Initializable {

    public TextField numberInput;
    public Label numberLabel;
    public ImageView mainImage;
    public ImageView secondImage;
    public Button buttonSubmit;
    public Button buttonNext;
    public Button buttonTryAgain;

    public VBox LevelInformationVBox;
    public Label numberInfoLabel;
    public Label currentNumberLabel;
    public Label yourAnswerLabel;
    public Label currentYourAnswer;
    public Label currentLevel;
    @FXML
    private Pane root;
    @FXML
    private final String homePage;
    private static final String directory = "resources/";
    private final String hoverMusic;
    private final String clickMusic;

    private static final Integer STARTTIME   = 2;
    private final IntegerProperty timeSeconds = new SimpleIntegerProperty(STARTTIME * 100);
    private Timeline timeline;
    @FXML
    private Button buttonStart;
    @FXML
    private Label timerLabel;
    @FXML
    private ProgressBar progressBar;

    private int upperBound,lowerBound,currentNumber,levelNumber,seconds;

    /**
     * NumberMemory()
     * This method is the constructor
     * of the class. It initializes the values
     *
     */
    public NumberMemory(){
        homePage = directory + "home.fxml";
        clickMusic = "/resource/MRTK_Shell_Click_Out.wav";
        hoverMusic = "/resource/MRTK_Shell_Click_Init.wav";
        upperBound=9;
        lowerBound=0;
        currentNumber=0;
        levelNumber=1;
        seconds=2;
    }

    /**
     * initialize()
     * This is method is inherited and used for the call of
     * the use of a progress bar
     *
     * @param location location is the URL location of this
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //timerLabel.textProperty().bind(timeSeconds.divide(100).asString());
        progressBar.progressProperty().bind(timeSeconds.divide(STARTTIME * 100.0).subtract(1).multiply(-1));
        BackgroundFill background_fill = new BackgroundFill(Color.valueOf("#2e7cd1"),
                CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(background_fill);
        progressBar.setBackground(background);
    }

    /**
     * home()
     * This method is used to call and switch to the home page
     * This utilizes the mouse click
     *
     * @param actionEvent mouse click event to click the image as a button
     */
    public void home(MouseEvent actionEvent){
        AudioClip hover = new AudioClip(this.getClass().getResource(clickMusic).toString());
        hover.play();
        Scene scene = root.getScene();
        Pane newRoot = Loader.loadFxmlFile(homePage);
        scene.setRoot(newRoot);
    }

    /**
     * startTimer()
     * This method is used to start the progress timer bar
     *
     * @throws InterruptedException just in case the progressbar doesn't work
     */
    public void startTimer() throws InterruptedException {

        progressBar.setVisible(true);
        if (timeline != null)
        {
            timeline.stop();
        }
        timeSeconds.set((STARTTIME + 1) * 100);
        timeline = new Timeline();
        timeline.getKeyFrames().add(

                new KeyFrame(Duration.seconds(STARTTIME + 1), new KeyValue(timeSeconds, 0)));
        timeline.playFromStart();
    }

    /**
     * handleStartAction()
     * This method is inherited and used for the startTimer action
     *
     * @param event
     * @throws InterruptedException
     */
    @FXML
    protected void handleStartAction(ActionEvent event) throws InterruptedException {
        upperBound=9;
        lowerBound=0;
        currentNumber=0;
        levelNumber=1;
        seconds=2;
        numberInput.setText("");
        buttonStart.setVisible(false);
        mainImage.setVisible(false);
        //TimeUnit.SECONDS.sleep(2);
        startTimer();
        setNumber();
        numberLabel.setText(String.valueOf(currentNumber));
        numberLabel.setVisible(true);

        new NumberTimer(seconds,numberInput,numberLabel,progressBar, mainImage,secondImage,buttonSubmit);
    }

    /**
     * handleSubmitAction()
     * This method is used to trigger the submit button
     * to check the number that was put into the input text
     *
     * @param actionEvent on click of the submit button
     * @throws InterruptedException is click fails exception is thrown
     */
    public void handleSubmitAction(ActionEvent actionEvent) throws InterruptedException {
        buttonSubmit.setVisible(false);
        secondImage.setVisible(false);

        currentNumberLabel.setText(String.valueOf(currentNumber));
        currentYourAnswer.setText(String.valueOf(numberInput.getText()));
        LevelInformationVBox.setVisible(true);
        if(numberInput.getText().equals(String.valueOf(currentNumber))){
            buttonNext.setVisible(true);
            currentLevel.setText("Level "+levelNumber);
            levelNumber++;
            numberInput.setText("");
            numberInput.setVisible(false);

        }else{
            buttonTryAgain.setVisible(true);
            currentLevel.setText("Level "+levelNumber);
            numberInput.setText("");
            numberInput.setVisible(false);
        }
    }

    /**
     * setNumber()
     * This method will set the new number for each level
     * of the number memory game
     *
     */
    public void setNumber(){
        Random rand = new Random();
        currentNumber = rand.nextInt(upperBound - lowerBound) + lowerBound;
        if(lowerBound==0){
            lowerBound=10;
        }
        else {
            lowerBound=lowerBound*10;
        }
        if(upperBound==9){
            upperBound=upperBound*11;
        }
        else{
            upperBound=upperBound*10+upperBound;
        }
    }

    /**
     * handleNextAction()
     * This method is used to trigger the next level of the
     * number memory game it will get the next number and
     * reset everything
     *
     * @param actionEvent
     * @throws InterruptedException
     */
    public void handleNextAction(ActionEvent actionEvent) throws InterruptedException {
        LevelInformationVBox.setVisible(false);
        buttonNext.setVisible(false);
        setNumber();
        numberLabel.setText(String.valueOf(currentNumber));
        numberLabel.setVisible(true);
        startTimer();
        new NumberTimer(seconds,numberInput,numberLabel,progressBar, mainImage,secondImage,buttonSubmit);
    }

    /**
     * handleTryAgainAction()
     * This method is triggered by the Try Again
     * on click
     *
     * @param actionEvent
     */
    public void handleTryAgainAction(ActionEvent actionEvent) {
        mainImage.setVisible(true);
        buttonStart.setVisible(true);
        LevelInformationVBox.setVisible(false);
        buttonTryAgain.setVisible(false);
    }
}

/**
 * Reminder
 * This class is used to check the grid and
 * set a second few seconds tile change so
 * the user can see the tiles to select
 *
 */
class NumberTimer {

    Timer timer;
    TextField numberInput;
    Label label;
    ProgressBar progressBar;
    ImageView mainImage;
    ImageView secondImage;
    Button submit;
    /**
     * NumberTimer()
     * This method is the constructor
     * of the class it takes in the seconds and
     * the gridPane being changed
     *
     * @param seconds - the amount of time till change
     */
    public NumberTimer(int seconds, TextField numberInput,
                       Label label, ProgressBar progressBar,
                       ImageView mainImage,ImageView secondImage,Button submit) {
        this.submit = submit;
        this.mainImage = mainImage;
        this.secondImage = secondImage;
        this.progressBar = progressBar;
        this.label = label;
        this.numberInput = numberInput;
        timer = new Timer();
        timer.schedule(new RemindTask(), seconds * 1000);
    }

    /**
     * RemindTask
     * This class controls the time delay for the class
     */
    class RemindTask extends TimerTask {
        public void run() {

            label.setVisible(false);
            mainImage.setVisible(false);
            progressBar.setVisible(false);

            numberInput.setVisible(true);
            secondImage.setVisible(true);
            submit.setVisible(true);
            timer.cancel(); //Terminate the timer thread
        }
    }
}