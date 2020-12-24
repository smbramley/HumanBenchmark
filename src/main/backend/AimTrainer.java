/**
 * @Author Shane Bramley
 * @Date 10/27/2020
 *
 * AimTrainer, This class will control the aim trainer
 * human benchmark. This benchmark will test,
 * "How quickly can you hit all the targets?"
 */

package main.backend;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import main.Loader;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class AimTrainer {
    public VBox buttonImage;
    public VBox aimTitle;
    @FXML
    private Pane root;
    @FXML
    private Label aimTime;
    @FXML
    private Label totalLeft;
    private final String homePage;
    private static final String directory = "resources/";
    private long startTime;
    private final LongProperty aimTimerValue;
    private int total;

    private final String hoverMusic;
    private final String clickMusic;
    private List<Long> times;
    private int averageTime;

    /**
     * AimTrainer()
     * this is the constructor
     * for the AimTrainer application
     * this constructor will initialize values for variables
     *
     */
    public AimTrainer(){
        clickMusic = "/resource/MRTK_Shell_Click_Out.wav";
        hoverMusic = "/resource/MRTK_Shell_Click_Init.wav";
        total=30;
        aimTimerValue = new SimpleLongProperty(0);
        homePage = directory + "home.fxml";
        times = new ArrayList<>();
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

    /**
     * startTime()
     * This method will start the timer for this app
     * it will be called on the click
     *
     * @throws InterruptedException
     */
    public void startTimer() throws InterruptedException {
        aimTitle.setVisible(false);
        //long rand = ThreadLocalRandom.current().nextLong(1000, 5000);
        //TimeUnit.MILLISECONDS.sleep(rand);
        startTime = System.nanoTime();
    }

    /**
     * finishTimer()
     * This method will stop the timer
     * as soon as all of the targets are hit
     *
     */
    public void finishTimer() {
        long finishTime = System.nanoTime();
        long timeNano = finishTime - startTime;
        long milliValue = TimeUnit.NANOSECONDS.toMillis(timeNano);
        aimTimerValue.setValue(milliValue);
        times.add(milliValue);
    }

    public void displayInfo(){
        for(int i=0;i<times.size();i++){
            averageTime += Integer.parseInt(String.valueOf(times.get(i)));
        }
        averageTime = averageTime/times.size();

        totalLeft.setText("Total Targets Left: " + total);
        total=30;
        aimTime.setVisible(true);
        aimTitle.setVisible(true);
        buttonImage.setLayoutX(400);
        buttonImage.setLayoutY(400);
        aimTime.setText("Your time is: "+String.valueOf(averageTime)
                +"ms\nClick the target to start again");
    }

    /**
     * onClickAimTrainer()
     * This method is called when a target it clicked
     * with method controls the amount of targets left and starts
     * and stops the timer
     *
     * @param actionEvent
     * @throws InterruptedException hit when looking for the sounds and cant find it
     */
    public void onClickAimTrainer(MouseEvent actionEvent) throws InterruptedException {
        AudioClip hover = new AudioClip(this.getClass().getResource(clickMusic).toString());
        hover.play();
        if(total!=30) {
            finishTimer();
        }
        total--;
        if(total==0){
            displayInfo();
        }
        else if(total>0) {
            if (total==29) {
                aimTime.setVisible(false);
                totalLeft.setText("Total Targets Left: " + total);
                Random rand = new Random();
                int lowX = 200;
                int highX = 500;
                int lowY = 400;
                int highY = 600;
                int resultX = rand.nextInt(highX - lowX) + lowX;
                int resultY = rand.nextInt(highY - lowY) + lowY;
                buttonImage.setLayoutX(resultX);
                buttonImage.setLayoutY(resultY);
                startTimer();
            }
            else {
                totalLeft.setText("Total Targets Left: " + total);
                Random rand = new Random();
                int lowX = 200;
                int highX = 500;
                int lowY = 400;
                int highY = 600;
                int resultX = rand.nextInt(highX - lowX) + lowX;
                int resultY = rand.nextInt(highY - lowY) + lowY;
                buttonImage.setLayoutX(resultX);
                buttonImage.setLayoutY(resultY);
                startTimer();
            }
        }
    }
}
