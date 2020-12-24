/**
 * @Author Shane Bramley
 * @Date 10/27/2020
 *
 * Hearing, This class will test,
 * "A test for high frequency hearing loss,
 * the most common form of hearing loss"
 */

package main.backend;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import main.Loader;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;


public class Hearing {

    @FXML
    private Pane root;
    @FXML
    private Button startstop;
    @FXML
    private Label timerLabel;
    private final String homePage;
    private static final String directory = "resources/";
    private final String hoverMusic;
    private final String clickMusic;
    private final String hearingMusic;
    private int timerNumber = 22000;

    private Timer timer;

    /**
     * Hearing()
     * This method is the constructor of the hearing app
     * it will initialize the values of the variable used
     *
     */
    public Hearing(){
        homePage = directory + "home.fxml";
        clickMusic = "/resource/MRTK_Shell_Click_Out.wav";
        hoverMusic = "/resource/MRTK_Shell_Click_Init.wav";
        hearingMusic = "/resource/HumanBenchmarkHearingTest.wav";
        //timerLabel.setText(timerNumber+"Hz");
        timer = new Timer();
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
    }


    boolean checkOnOff=false;

    /**
     * startHearingTest()
     * This method is called on the start button click
     * this will turn the sound on and off and top the htz timer
     *
     * @param mouseEvent mouse click check of the start button
     * @throws InterruptedException
     */
    public void startHearingTest(MouseEvent mouseEvent) throws InterruptedException {
        AudioClip hearing = new AudioClip(this.getClass().getResource(hearingMusic).toString());
        checkOnOff = !checkOnOff;
        if(checkOnOff){
            //hearing.play();
            startstop.setText("Stop Hearing Test");
            startCountDown();
            //TimeUnit.MILLISECONDS.sleep(1);
            hearing.play();
        }
        else{
            hearing.stop();
            startstop.setText("Start Hearing Test");
            timer.cancel();
            timer = new Timer();
        }

    }

    /**
     * startCountDown()
     * This method will start the hrz count down for the timer
     * this includes a timer delay to match the sound
     *
     */
    public void startCountDown() {
        timerNumber=22000;
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    public void run() {
                        timerNumber--;
                        timerLabel.setText(timerNumber+"Hz");

                        if (timerNumber == 0)
                            timer.cancel();
                    }
                });
            }
        }, 1, 1); //Every 1 second
    }
}
