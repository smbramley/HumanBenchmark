/**
 * @Author Shane Bramley
 * @Date 10/27/2020
 *
 * ReactionTime, This class will control the reaction time
 * human benchmark. This benchmark will test,
 * "Test your visual reflexes"
 */

package main.backend;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import main.Loader;

import java.net.URISyntaxException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class ReactionTime {
    public ImageView startImage;
    public ImageView waitImage;
    @FXML
    private Pane root;
    @FXML
    private Label reactionTime;
    private long startTime;
    private final LongProperty reactionTimeValue;
    private final String homePage;
    private static final String directory = "resources/";
    private final String hoverMusic;
    private final String clickMusic;

    /**
     * ReactionTime()
     * This method is the constructor of the class
     * the constructor will initialize the values of the variables
     *
     */
    public ReactionTime() {
        reactionTimeValue = new SimpleLongProperty(0);
        homePage = directory + "home.fxml";
        clickMusic = "/resource/MRTK_Shell_Click_Out.wav";
        hoverMusic = "/resource/MRTK_Shell_Click_Init.wav";
    }

    public void reactionTimeSetTime(long startTime){
        this.startTime=startTime;
    }
    /**
     * initialize()
     * This method will be used to set the reaction time textProperty for the label
     */
    public void initialize() {
        reactionTime.textProperty().bind(reactionTimeValue.asString());
    }

    boolean check = false;
    boolean click = true;
    ReactionTimer rt;
    /**
     * startstopTimer()
     * This method is used to start and top the reaction time
     * it will start the timer and change the image after a
     * set time
     *
     * @param actionEvent
     * @throws Exception
     */
    public void startTimer(MouseEvent actionEvent) throws Exception {
        reactionTime.setVisible(false);
        check=false;
        if(click) {
            click=!click;
            startImage.setImage(new Image(getClass().getResource("/resource/ReactionWaitForGreen.png").toURI().toString()));
            long rand = ThreadLocalRandom.current().nextLong(1000, 5000);
            //TimeUnit.MILLISECONDS.sleep(rand);
            rt = new ReactionTimer(3, startImage, waitImage, startTime);
        }
        else{
            click=!click;
            startImage.setImage(new Image(getClass().getResource("/resource/ReactionTooSoon.png").toURI().toString()));
            rt.timer.cancel();
            //rt = new ReactionTimer(3, startImage, waitImage, startTime);
        }
    }

    public void stopTimer(MouseEvent actionEvent) throws Exception {
        startTime=rt.startTime;
        waitImage.setImage(new Image(getClass().getResource("/resource/ReactionWaitForGreen.png").toURI().toString()));
        waitImage.setVisible(false);
        startImage.setVisible(true);
        click=true;
        startImage.setImage(new Image(getClass().getResource("/resource/ReactionTimeImage.PNG").toURI().toString()));
            reactionTime.setVisible(true);
            long finishTime = System.nanoTime();
            long reactionTimeNano = finishTime - startTime;
            long milliValue = TimeUnit.NANOSECONDS.toMillis(reactionTimeNano);
            reactionTimeValue.setValue(milliValue);
    }

    /**
     * home()
     * This method is used to call the and change to the homepage
     * this is the on mouse click
     *
     */
    public void home(MouseEvent actionEvent){
        AudioClip hover = new AudioClip(this.getClass().getResource(clickMusic).toString());
        hover.play();
        Scene scene = root.getScene();
        Pane newRoot = Loader.loadFxmlFile(homePage);
        scene.setRoot(newRoot);
    }
}

/**
 * Reminder
 * This class is used to check the grid and
 * set a second few seconds tile change so
 * the user can see the tiles to select
 *
 */
class ReactionTimer {

    Timer timer;
    long startTime;
    boolean check=false;
    ImageView start;
    ImageView wait;
    /**
     * NumberTimer()
     * This method is the constructor
     * of the class it takes in the seconds and
     * the gridPane being changed
     *
     * @param seconds - the amount of time till change
     */
    public ReactionTimer(int seconds,ImageView start,ImageView wait,long startTime) {
        this.start = start;
        this.wait = wait;
        this.startTime = startTime;
        timer = new Timer();
        timer.schedule(new RemindTask(), seconds * 1000);
    }

    /**
     * RemindTask
     * This class controls the time delay for the class
     */
    class RemindTask extends TimerTask {
        public void run() {
            try {
                wait.setVisible(true);
                wait.setImage(new Image(getClass().getResource("/resource/ReactionTimeClickImage.png").toURI().toString()));
                start.setVisible(false);
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            startTime = System.nanoTime();
            ReactionTime reactionTime = new ReactionTime();
            reactionTime.reactionTimeSetTime(startTime);
            check=true;
            timer.cancel(); //Terminate the timer thread
        }
    }
}