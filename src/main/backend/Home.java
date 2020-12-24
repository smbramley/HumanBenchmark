/**
 * @Author Shane Bramley
 * @Date 10/27/2020
 *
 * Home, This class will control the
 * main page of the program for all of
 * the various trainings and tests
 */


package main.backend;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.media.MediaPlayer;
import main.Loader;

import javafx.scene.media.Media;
import java.io.File;
import java.net.URISyntaxException;

public class Home {
    private static final String directory = "resources/";

    private final String homePage,reactionTimePage,aimTrainerPage,chimpTestPage;
    private final String numberMemoryPage,hearingPage,typingPage,verbalMemoryPage,visualMemoryPage;

    private final String hoverMusic;
    private final String clickMusic;

    private static Scene scene;
    public ImageView reactionIV,aimIV,chimpIV,numberIV,visualIV,hearingIV,typingIV,verbalIV;

    /**
     * Home()
     * This method is the constructor of the home class
     * this method will initialize the values for the variables
     *
     */
    public Home() {
        clickMusic = "/resource/MRTK_Shell_Click_Out.wav";
        hoverMusic = "/resource/MRTK_Shell_Click_Init.wav";
        homePage = directory + "home.fxml";
        reactionTimePage = directory + "reactionTime.fxml";
        aimTrainerPage = directory + "aimTrainer.fxml";
        chimpTestPage = directory + "chimpTest.fxml";
        numberMemoryPage = directory + "numberMemory.fxml";
        hearingPage = directory + "hearing.fxml";
        typingPage = directory + "typing.fxml";
        verbalMemoryPage = directory + "verbalMemory.fxml";
        visualMemoryPage = directory + "visualMemory.fxml";
    }

    /**
     * getScene()
     * This method will be a getter
     * for the scene that is needed
     * for the other pages
     *
     * @return scene variable of type Scene
     */
    public Scene getScene() {
        return scene;
    }

    /**
     * setScene()
     * This method will be a setter to
     * set the scene into the class variable
     *
     * @param scene scene varaible of type Scene being passed in
     */
    public void setScene(Scene scene) {
        this.scene = scene;
    }

    /**
     * initialize()
     * this method is used to initialize variables on start
     */
    public void initialize() { }

    /**
     * reactionTimeClicked()
     * This method will be triggered by a reactionTime button click
     * to start the reaction time app
     *
     * @param actionEvent this is the mouse click action that occurs when clicking
     *                    the reactionTime button
     * @throws URISyntaxException exception throw by image not being found in resources folder
     */
    public void reactionTimeClicked(MouseEvent actionEvent) throws URISyntaxException {
        Pane newRoot = Loader.loadFxmlFile(reactionTimePage);
        scene.setRoot(newRoot);
        AudioClip hover = new AudioClip(this.getClass().getResource(clickMusic).toString());
        hover.play();
    }

    /**
     * aimTrainerClicked()
     * This method calls the aimTrainerClick page
     * it is triggered by the aimTrainer button
     *
     * @param actionEvent mouse click action call, called by aimTrainer button
     */
    public void aimTrainerClicked(MouseEvent actionEvent) {
        Pane newRoot = Loader.loadFxmlFile(aimTrainerPage);
        scene.setRoot(newRoot);
        AudioClip hover = new AudioClip(this.getClass().getResource(clickMusic).toString());
        hover.play();
    }

    /**
     * chimpTestClicked()
     * This method is called when the chimpTest Button is clicked
     *
     * @param actionEvent action of the mouse being clicked by the chimpTestClick
     */
    public void chimpTestClicked(MouseEvent actionEvent) {
        Pane newRoot = Loader.loadFxmlFile(chimpTestPage);
        scene.setRoot(newRoot);
        AudioClip hover = new AudioClip(this.getClass().getResource(clickMusic).toString());
        hover.play();
    }

    /**
     * numberMemoryClicked()
     * This method is used to call the
     * numberMemory page of the app
     * this is called by the numberMemory button
     *
     * @param actionEvent used for the numberMemory button click
     */
    public void numberMemoryClicked(MouseEvent actionEvent) {
        Pane newRoot = Loader.loadFxmlFile(numberMemoryPage);
        scene.setRoot(newRoot);
        AudioClip hover = new AudioClip(this.getClass().getResource(clickMusic).toString());
        hover.play();
    }

    /**
     * visualMemoryClicked()
     * This method is used to get to the visualMemory
     * page of the application
     *
     * @param actionEvent called when the visualMemory button is called
     */
    public void visualMemoryClicked(MouseEvent actionEvent) {
        Pane newRoot = Loader.loadFxmlFile(visualMemoryPage);
        scene.setRoot(newRoot);
        AudioClip hover = new AudioClip(this.getClass().getResource(clickMusic).toString());
        hover.play();
    }

    /**
     * hearingClicked()
     * This method is used to call the hearingTest page
     *
     * @param actionEvent the action is called by the button click the hearing button
     */
    public void hearingClicked(MouseEvent actionEvent) {
        Pane newRoot = Loader.loadFxmlFile(hearingPage);
        scene.setRoot(newRoot);
        AudioClip hover = new AudioClip(this.getClass().getResource(clickMusic).toString());
        hover.play();
    }

    /**
     * typingClicked()
     * This method is used to call the typing page
     * of the application
     *
     * @param actionEvent called when the typing button is clicked
     */
    public void typingClicked(MouseEvent actionEvent) {
        Pane newRoot = Loader.loadFxmlFile(typingPage);
        scene.setRoot(newRoot);
        AudioClip hover = new AudioClip(this.getClass().getResource(clickMusic).toString());
        hover.play();
    }

    /**
     * verbalMemoryClicked()
     * This method is used to call and load the verbalMemoryPage
     *
     * @param actionEvent this is called for by the click of the verbal Button
     */
    public void verbalMemoryClicked(MouseEvent actionEvent) {
        Pane newRoot = Loader.loadFxmlFile(verbalMemoryPage);
        scene.setRoot(newRoot);
        AudioClip hover = new AudioClip(this.getClass().getResource(clickMusic).toString());
        hover.play();
    }

    /**
     * reactionTimeHover()
     * This method is used to change image of the reactionTimeButton
     * on hover
     *
     * @param mouseEvent on mouse hover event
     * @throws URISyntaxException called when the resource image cannot be found
     */
    public void reactionTimeHover(MouseEvent mouseEvent) throws URISyntaxException {
        reactionIV.setImage(new Image(getClass()
                .getResource("/resource/FullReactionTimeOrange.PNG").toURI().toString()));
        AudioClip hover = new AudioClip(this.getClass().getResource(hoverMusic).toString());
        hover.play();
    }

    /**
     * aimTrainerHover()
     * This method is used to change the aimTrainerButton
     * on hover
     *
     * @param mouseEvent mouse hover the button even
     * @throws URISyntaxException exception thrown when the image cannot be found
     */
    public void aimTrainerHover(MouseEvent mouseEvent) throws URISyntaxException {
        aimIV.setImage(new Image(getClass()
                .getResource("/resource/FullAimTrainerOrange.PNG").toURI().toString()));
        AudioClip hover = new AudioClip(this.getClass().getResource(hoverMusic).toString());
        hover.play();
    }

    /**
     * chimpTestHover()
     * This method is used to change the
     * chimpTest button image
     *
     * @param mouseEvent mouse enter event for button image change
     * @throws URISyntaxException exception thrown when image not found
     */
    public void chimpTestHover(MouseEvent mouseEvent) throws URISyntaxException {
        chimpIV.setImage(new Image(getClass()
                .getResource("/resource/FullChimpTestOrange.PNG").toURI().toString()));
        AudioClip hover = new AudioClip(this.getClass().getResource(hoverMusic).toString());
        hover.play();
    }

    /**
     * numberMemoryHover()
     * This method is used to change the image
     * of of the numberMemory button on hover
     *
     * @param mouseEvent on mouse enter change the image
     * @throws URISyntaxException exception thrown when the image is not found
     */
    public void numberMemoryHover(MouseEvent mouseEvent) throws URISyntaxException {
        numberIV.setImage(new Image(getClass()
                .getResource("/resource/FullNumberMemoryOrange.PNG").toURI().toString()));
        AudioClip hover = new AudioClip(this.getClass().getResource(hoverMusic).toString());
        hover.play();
    }

    /**
     * visualMemoryHover()
     * This method is used to
     * change the visualMemory button image on hover
     *
     * @param mouseEvent on mouse enter change the image event
     * @throws URISyntaxException exception thrown when the image is not found
     */
    public void visualMemoryHover(MouseEvent mouseEvent) throws URISyntaxException {
        visualIV.setImage(new Image(getClass()
                .getResource("/resource/FullVisualMemoryOrange.PNG").toURI().toString()));
        AudioClip hover = new AudioClip(this.getClass().getResource(hoverMusic).toString());
        hover.play();
    }

    /**
     * hearingHover()
     * This method is called when the mouse enters
     * this changes the image of hearing button
     *
     * @param mouseEvent on mouse enter the image changes
     * @throws URISyntaxException exception thrown when the image is not found
     */
    public void hearingHover(MouseEvent mouseEvent) throws URISyntaxException {
        hearingIV.setImage(new Image(getClass()
                .getResource("/resource/FullHearingOrange.PNG").toURI().toString()));
        AudioClip hover = new AudioClip(this.getClass().getResource(hoverMusic).toString());
        hover.play();
    }

    /**
     * typingHover()
     * This method is called when mouse hovers over the
     * typing button changes the image
     *
     * @param mouseEvent mouse enter hover changes the image of the button
     * @throws URISyntaxException exception thrown when the image is not found
     */
    public void typingHover(MouseEvent mouseEvent) throws URISyntaxException {
        typingIV.setImage(new Image(getClass()
                .getResource("/resource/FullTypingOrange.PNG").toURI().toString()));
        AudioClip hover = new AudioClip(this.getClass().getResource(hoverMusic).toString());
        hover.play();
    }

    /**
     * verbalMemoryHover()
     * This method is called when ever the mouse enters
     * this changes the image
     *
     * @param mouseEvent mouse enter the image change occurs
     * @throws URISyntaxException exception thrown if the image is not found
     */
    public void verbalMemoryHover(MouseEvent mouseEvent) throws URISyntaxException {
        verbalIV.setImage(new Image(getClass()
                .getResource("/resource/FullVerbalMemoryOrange.PNG").toURI().toString()));
        AudioClip hover = new AudioClip(this.getClass().getResource(hoverMusic).toString());
        hover.play();
    }

    /**
     * reactionTimeExit()
     * This method will change the reactionTime button image
     * back to the original image
     *
     * @param mouseDragEvent on mouse enter image changes back
     * @throws URISyntaxException image not found throw exception
     */
    public void reactionTimeExit(MouseEvent mouseDragEvent) throws URISyntaxException {
        reactionIV.setImage(new Image(getClass()
                .getResource("/resource/FullReactionTime.PNG").toURI().toString()));
    }

    /**
     * aimTrainerExit()
     * This method is called when the mouse leaves the
     * aimTrainer button changes the image back to the original
     *
     * @param mouseEvent mouse leaves the button change image
     * @throws URISyntaxException image not found throw exception
     */
    public void aimTrainerExit(MouseEvent mouseEvent) throws URISyntaxException {
        aimIV.setImage(new Image(getClass()
                .getResource("/resource/FullAimTrainer.PNG").toURI().toString())); }

    /**
     * chimpTestExit()
     * This method will change the image
     * back to the original image on mouse leave
     *
      * @param mouseEvent
     * @throws URISyntaxException
     */
    public void chimpTestExit(MouseEvent mouseEvent) throws URISyntaxException {
        chimpIV.setImage(new Image(getClass()
                .getResource("/resource/FullChimpTest.PNG").toURI().toString())); }

    /**
     * numberMemoryExit()
     * This method is used to change the image of the numberMemory button
     * on mouse exit
     *
      * @param mouseEvent on mouse leave change the image
     * @throws URISyntaxException image not found throw exception
     */
    public void numberMemoryExit(MouseEvent mouseEvent) throws URISyntaxException {
        numberIV.setImage(new Image(getClass()
                .getResource("/resource/FullNumberMemory.PNG").toURI().toString())); }

    /**
     * visualMemoryExit()
     * This method is used to change the button image back to the original
     *
     * @param mouseEvent this is triggered my the mouse over
     * @throws URISyntaxException no image this exception gets thrown
     */
    public void visualMemoryExit(MouseEvent mouseEvent) throws URISyntaxException {
        visualIV.setImage(new Image(getClass()
                .getResource("/resource/FullVisualMemory.PNG").toURI().toString())); }

    /**
     * hearingExit()
     * This method is used to change the button image back to the original
     *
     * @param mouseEvent this is triggered my the mouse over
     * @throws URISyntaxException no image this exception gets thrown
     */
    public void hearingExit(MouseEvent mouseEvent) throws URISyntaxException {
        hearingIV.setImage(new Image(getClass()
                .getResource("/resource/FullHearing.PNG").toURI().toString())); }

    /**
     * typingExit()
     * This method is used to change the button image back to the original
     *
     * @param mouseEvent this is triggered my the mouse over
     * @throws URISyntaxException no image this exception gets thrown
     */
    public void typingExit(MouseEvent mouseEvent) throws URISyntaxException {
        typingIV.setImage(new Image(getClass()
                .getResource("/resource/FullTyping.PNG").toURI().toString())); }

    /**
     * verbalMemoryExit()
     * This method is used to change the button image back to the original
     *
     * @param mouseEvent this is triggered my the mouse over
     * @throws URISyntaxException no image this exception gets thrown
     */
    public void verbalMemoryExit(MouseEvent mouseEvent) throws URISyntaxException {
        verbalIV.setImage(new Image(getClass()
                .getResource("/resource/FullVerbalMemory.PNG").toURI().toString())); }
}
