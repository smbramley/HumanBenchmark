/**
 * @Author Shane Bramley
 * @Date 10/27/2020
 *
 * Typing, This class will control the Typing
 * human benchmark. This benchmark will test,
 * "How many words per minute can you type"
 */

package main.backend;

import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import main.Loader;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
//import java.awt.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Typing {

    public TextFlow textFlow;
    public TextArea textArea;
    public Label wpmLabel;
    public VBox titleImage;
    public VBox bottomImage;
    public Button restartButton;
    @FXML
    private Pane root;
    @FXML
    private final String homePage;
    private static final String directory = "resources/";
    private final String hoverMusic;
    private final String clickMusic;
    String usingString;
    double startTime,end,elapsedTime;
    int number;
    //private List<>
    private String text1 = "The new boy went off brushing the dust from his clothes, sobbing"+
            ", snuffing, and occasionally looking back and shaking his head and threating what"+
            " he would do to Tom the \"next time he caught him out.\" To which Tom responded"+
            " with jeers, and started off in high feather, and as soon as his back was turned the"+
            " new boy snatched up a stone, threw it and hit him between the shoulders and then turned"+
            " tail and ran like an antelope. Tom chased the traitor home, and thus found out where he lived.";
    private String text2 = "The summer evenings were long. It was not dark, yet. Presently Tom checked his whistle."+
            "A strange was before him - a boy a shade larger than himself. A newcomer of any age or either gender"+
            "was an impressive curiosity in the poor little shabby village of St. Petersburg. This boy was well dressed"+
            ", too well dressed on a weekday. This was simply astounding. His cap was a dainty thing, his close-buttoned"+
            "blue cloth roundabout was new and natty, and so were astounding. His cap was a dainty thing, his"+
            "close-buttoned blue clothed roundabout was new and natty, and so were his pantaloons.";
    private String text3 = "It was a smart little landau which rattled up to the door of Briony Lodge. As it pulled"+
            "up, one of the loafing men at the corner dashed forward to open the door in the hope of earning a copper"+
            ", but was elbowed away by another loafer, who had rushed up with the same intention. A fierce quarrel"+
            "broke out, which was increased by the two guardsmen, who took sides with one of the loungers, and by"+
            "the scissors-grinder, who was equally hot upon the other side.";
    private String text4 = "From time to time I heard some vague account of his doings: of his summons to Odessa in"+
            " the case of the Trepoff murder, of his clearing up of the singular tragedy of the Atkinson brothers at"+
            " Trincomalee, and finally of the mission which he had accomplished so delicately and successfully for the"+
            " reigning family to Holland.";
    private String text5 = "So he huffed and he puffed and he blew the house down! The wolf was greedy and he tried to"+
            " to catch both pigs at once, but he was too greedy and got neither! His big jaws clamped down on nothing"+
            " but air and the two little pigs scrambled away as fast as their little hooves would carry them. The wolf"+
            " chased them down the lane and he almost caught them. But they made it to the brick house and slammed the"+
            " door closed before the wolf could catch them.";
    private List<String> typingStrings = new ArrayList<>();

    /**
     * Typing()
     * This method is the constructor of the class
     * it will initialize the values of the variables in typing
     *
     */
    public Typing(){
        homePage = directory + "home.fxml";
        clickMusic = "/resource/MRTK_Shell_Click_Out.wav";
        hoverMusic = "/resource/MRTK_Shell_Click_Init.wav";
        typingStrings.add(text1);
        typingStrings.add(text2);
        typingStrings.add(text3);
        typingStrings.add(text4);
        typingStrings.add(text5);
        number=-1;
    }

    /**
     * initialize()
     * This method will be used to set the
     * width of the text flow for typing when this page
     * is opened
     *
     */
    public void initialize() {
        textFlow.setPrefWidth(470);
        setUpText();
        textArea.setWrapText(true);
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

    /**
     * setUpText()
     * This method is used to select which text is being used
     * for the typing test
     *
     */
    public void setUpText(){
        Random rand = new Random();
        int low = 0,high = 4;
        int result = rand.nextInt(high - low) + low;
        usingString = typingStrings.get(result);
        titleImage.setVisible(true);
        bottomImage.setVisible(true);
        for(int i=0;i<usingString.length();i++){
            Label item = new Label(String.valueOf(usingString.charAt(i)));
            item.setStyle("-fx-background-color: white");
            textFlow.getChildren().add(item);
        }
    }

    /**
     * checkTextChanged()
     * This method is used to check for the text change
     * within the text flow
     *
     */
    public void checkTextChanged(){
        if(number==-1){
            startTime = LocalTime.now().toNanoOfDay();
            //titleImage.setVisible(false);
            //bottomImage.setVisible(false);
        }

        if(number != usingString.length()-1) {
            if (number < textArea.getText().length() - 1 || number == 0) {
                if (textArea.getText().charAt(textArea.getText().length() - 1) == usingString.charAt(textArea.getText().length() - 1)) {
                    ((Label) textFlow.getChildren().get(textArea.getText().length() - 1)).setStyle("-fx-background-color: lightgreen");//.setStroke(Color.GREEN);
                } else {
                    ((Label) textFlow.getChildren().get(textArea.getText().length() - 1)).setStyle("-fx-background-color: orange");//.setStroke(Color.RED);
                }
                number = textArea.getText().length() - 1;
            } else {
                ((Label) textFlow.getChildren().get(textArea.getText().length())).setStyle("-fx-background-color: white");
                number--;
            }
        }
        else{
            textFlow.setVisible(false);
            textArea.setVisible(false);
            checkWPM();
            wpmLabel.setVisible(true);
            restartButton.setVisible(true);
            //titleImage.setVisible(true);
            //bottomImage.setVisible(true);
            //setUpText();
        }
    }

    /**
     * checkWPM this will set up the math for the timer change
     * to see how many words per minute
     *
     */
    public void checkWPM(){
        end = LocalTime.now().toNanoOfDay();
        elapsedTime = end-startTime;
        double seconds = elapsedTime/1000000000.0;
        int numberOfWords = usingString.length();
        int wpm = (int)((((double)numberOfWords/5) / seconds)*60);
        wpmLabel.setText(String.valueOf(wpm)+" wpm");
    }

    /**
     * restartedTyping()
     * This method will reset the typing mini game.
     * It will reset the text flow, text area, and the timing.
     *
     * @param mouseEvent
     */
    public void restartTyping(MouseEvent mouseEvent) {
        startTime=0;
        end=0;
        restartButton.setVisible(false);
        textFlow.setVisible(true);
        textArea.setVisible(true);
        textArea.setText("");
        usingString = "";
        number=-1;
        textFlow.getChildren().clear();
        textFlow.setPrefWidth(470);
        setUpText();
        textArea.setWrapText(true);
    }
}
