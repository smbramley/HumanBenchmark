/**
 * @Author Shane Bramley
 * @Date 10/27/2020
 *
 * VerbalMemory, This class will control the verbal memory
 * human benchmark. This benchmark will test,
 * "Keep as many words in short term memory as possible"
 */

package main.backend;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import main.Loader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class VerbalMemory {

    @FXML
    public ImageView mainImage;
    @FXML
    public Label livesDisplay;
    @FXML
    public Label scoreDisplay;
    @FXML
    private Pane root;
    @FXML
    private Label wordDisplay;
    @FXML
    private Button restartButton,newButton,seenButton,startButton;
    private final String homePage;
    private static final String directory = "resources/";
    private final String hoverMusic;
    private final String clickMusic;
    private final List<String> verbalList
            = Arrays.asList("adroiter",
            "mistaker","lambasts","myriapod","sense","medium","outstrips",
            "threes","scores","calumniated","explosion","total","polymorphism",
            "benzol","canonicals","oils","exploration","footprint","inheritance",
            "subordinately","oracularly","kinghoods","coal","integer","instance",
            "cembali","unprofitably","prostrated","rib","hippo","watermelon",
            "runabouts","nylons","midship","decodes","infinity","final",
            "reviewability","agrarians","tailbone","crutches","smidgeons",
            "monocytes","doomsters","biorhythmicities","create","twinning",
            "caves","arbalist","retied","spy","roadrunners","cyber",
            "captainship","aleutian","shakeups","conduits","programmer",
            "phonically","perceptiveness","muckraking","giller","lamplight",
            "manifestoes","inefficient","razed","sew","dolphine","algorithm",
            "substitutes","philters","reconditely","hiccuped","creation",
            "babbling","gabardine","hodgepodge","upreached","keyboard",
            "befouled","visionaries","offeree","planar","mouse");
    private List<String> checkList;
    private int score;
    private int lives;

    /**
     * VerbalMemory()
     * This method is the constructor of the class
     * this is used to initialize the values
     *
     */
    public VerbalMemory(){
        homePage = directory + "home.fxml";
        clickMusic = "/resource/MRTK_Shell_Click_Out.wav";
        hoverMusic = "/resource/MRTK_Shell_Click_Init.wav";
        checkList = new ArrayList<>();
        score = 0;
        lives = 3;
    }

    /**
     * initialize()
     * This method is used to initialize values
     * on page start
     *
     */
    public void initialize() { }

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

    /**
     * startClicked()
     * This method is used to start the verbal
     * app.
     *
     * @param action action is the on click of the start button
     */
    public void startClicked(MouseEvent action){
        getNewLabelWord();
        wordDisplay.setVisible(true);
        seenButton.setVisible(true);
        newButton.setVisible(true);
        livesDisplay.setVisible(true);
        livesDisplay.setText(String.valueOf("Lives: "+lives));
        scoreDisplay.setVisible(true);
        scoreDisplay.setText(String.valueOf("Score: "+score));
        startButton.setVisible(false);
        mainImage.setVisible(false);
    }

    /**
     * restartClicked()
     * This method is used to restarted the verbal app
     *
     * @param action this is on mouse click on the button
     */
    public void restartClicked(MouseEvent action){
        score=0;
        lives=3;
        scoreDisplay.setText(String.valueOf("Score: "+score));
        livesDisplay.setText(String.valueOf("Lives: "+lives));
        getNewLabelWord();
        wordDisplay.setVisible(true);
        seenButton.setVisible(true);
        newButton.setVisible(true);
        startButton.setVisible(false);
        mainImage.setVisible(false);
        restartButton.setVisible(false);
    }

    /**
     * newClicked()
     * This method is when the new button is clicked
     * checking to see if the word displayed is
     * a new word
     *
     * @param action
     */
    public void newClicked(MouseEvent action){
        boolean notNew = false;
        if(checkList.size()==0){
            checkList.add(wordDisplay.getText());
        }
        else {
            for (int i = 0; i <checkList.size();i++){
                if(wordDisplay.getText().equals(checkList.get(i))){
                    lives--;
                    livesDisplay.setText(String.valueOf("Lives: "+lives));
                    notNew=true;
                    break;
                }
            }
        }
        if(!notNew){
            checkList.add(wordDisplay.getText());
            score++;
            scoreDisplay.setText(String.valueOf("Score: "+score));
        }
        if(lives==0){
            restartButton.setVisible(true);
            newButton.setVisible(false);
            seenButton.setVisible(false);
            mainImage.setVisible(true);
            wordDisplay.setText("Your score is: "+score);
        }
        else{
           getNewLabelWord();
        }
    }

    /**
     * seenClicked()
     * This method is used to check to see
     * if the word is seen or not
     *
     * @param action on seen button click action if word seen
     */
    public void seenClicked(MouseEvent action){
        boolean seen = false;

        for (int i = 0; i <checkList.size();i++){
            if(wordDisplay.getText().equals(checkList.get(i))){
                score++;
                scoreDisplay.setText(String.valueOf("Score: "+score));
                seen=true;
                break;
            }
        }
        if(!seen){
            lives--;
            livesDisplay.setText(String.valueOf("Lives: "+lives));
        }
        if(lives==0){
            restartButton.setVisible(true);
            newButton.setVisible(false);
            seenButton.setVisible(false);
            mainImage.setVisible(true);
            wordDisplay.setText("Your score is: "+score);
        }
        else{
            getNewLabelWord();
        }
    }

    /**
     * getNewLabelWord()
     * This method will get the random next new work to
     * display to the user
     *
     */
    public void getNewLabelWord(){
        Random rand = new Random();
        int low=0;
        int high=verbalList.size()-1;
        String randomWord = verbalList.get(rand.nextInt(high - low) + low);
        wordDisplay.setText(randomWord);
    }
}
