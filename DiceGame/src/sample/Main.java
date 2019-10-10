package sample;

import javafx.application.Application;
import javafx.geometry.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;

public class Main extends Application {


    //top section
    int overAllScore = 0;
    Label overallScoreLbl = new Label("Overall Score: ");
    Label numScore = new Label("" + overAllScore);

    //round score
    int roundScore = 0;
    Label roundScoreLbl = new Label("Round Score: ");
    Label numRoundScore = new Label("" + roundScore);

    //Labels remaining
    int rolls = 3;
    Label rolesRemainLbl = new Label("Roles Remaining: ");
    Label numRoles = new Label("" + rolls);


    //dice rolling button
    Button rollDice = new Button("Roll Dice");

    //dice 1 image
    Image dice1 = new Image("file:/Users/ronaldtran/Documents" +
            "/GUIS/DiceGame/src/sample/DiceImages/Dice1.png");
    ImageView dice1Image = new ImageView(dice1);
    ImageView dice1Two = new ImageView(dice1);
    ImageView dice1Three = new ImageView(dice1);
    ImageView dice1Four = new ImageView(dice1);
    ImageView dice1Five = new ImageView(dice1);
    ImageView dice1Six = new ImageView(dice1);

    public int randomDice(){
        int num = (int) Math.ceil(Math.random()*6);
        return num;
    }



    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Dice Game");

        //dice 1 image
        Image dice1 = new Image("file:/Users/ronaldtran/Documents" +
                "/GUIS/DiceGame/src/sample/DiceImages/Dice1.png");
        dice1Image.setFitHeight(50);
        dice1Image.setFitWidth(50);

        dice1Two.setFitWidth(50);
        dice1Two.setFitHeight(50);

        dice1Three.setFitWidth(50);
        dice1Three.setFitHeight(50);

        dice1Four.setFitWidth(50);
        dice1Four.setFitHeight(50);

        dice1Five.setFitWidth(50);
        dice1Five.setFitHeight(50);

        dice1Six.setFitWidth(50);
        dice1Six.setFitHeight(50);

        //overall score stuff box
        HBox OverallScoreBox = new HBox(10, overallScoreLbl, numScore);
        OverallScoreBox.setAlignment(Pos.CENTER);
        OverallScoreBox.setPadding(new Insets(25));

        //hbox for the dice images
        HBox diceBox = new HBox(10, dice1Image, dice1Two,
                dice1Three, dice1Four, dice1Five, dice1Six);
        diceBox.setAlignment(Pos.CENTER);
        diceBox.setPadding(new Insets(25));

        //hbox for button
        HBox buttonBox = new HBox(10, rollDice);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPadding(new Insets(10));

        //hbox for round score
        HBox roundScoreBox = new HBox(10, roundScoreLbl, numRoundScore);
        roundScoreBox.setAlignment(Pos.CENTER);
        roundScoreBox.setPadding(new Insets(10));

        //hbox for remainign rolls
        HBox remainRollsBox = new HBox(10, rolesRemainLbl, numRoles);
        remainRollsBox.setAlignment(Pos.CENTER);
        remainRollsBox.setPadding(new Insets(10));

        GridPane mainGridPane = new GridPane();
        mainGridPane.add(OverallScoreBox, 0, 0);
        mainGridPane.add(diceBox, 0, 1);
        mainGridPane.add(buttonBox, 0, 2);
        mainGridPane.add(roundScoreBox, 0, 3);
        mainGridPane.add(remainRollsBox, 0, 4);

        mainGridPane.setAlignment(Pos.CENTER);
        mainGridPane.setPadding(new Insets(10));


        /*what happens when you click the button
        1. number of rolls will decrement (DONE)
        2. the clicked dice should stay the way it is (held)
        3. Score will change accordingly
        4. Reset everything
        5. Change images randomly
         */
        rollDice.setOnAction(event -> {
            rolls--;
            numRoles.setText("" + rolls);

            int one = randomDice();
            int two = randomDice();
            int three = randomDice();
            int four = randomDice();
            int five = randomDice();
            int six = randomDice();


            dice1Image.setImage(new Image("file:/Users/ronaldtran/" +
                    "Documents/GUIS/DiceGame/src/sample/DiceImages/Dice" +
                    one + ".png"));
            dice1Two.setImage(new Image("file:/Users/ronaldtran/" +
                    "Documents/GUIS/DiceGame/src/sample/DiceImages/Dice" +
                    two + ".png"));
            dice1Three.setImage(new Image("file:/Users/ronaldtran/" +
                    "Documents/GUIS/DiceGame/src/sample/DiceImages/Dice" +
                    three + ".png"));
            dice1Four.setImage(new Image("file:/Users/ronaldtran/" +
                    "Documents/GUIS/DiceGame/src/sample/DiceImages/Dice" +
                    four + ".png"));
            dice1Five.setImage(new Image("file:/Users/ronaldtran/" +
                    "Documents/GUIS/DiceGame/src/sample/DiceImages/Dice" +
                    five + ".png"));
            dice1Six.setImage(new Image("file:/Users/ronaldtran/" +
                    "Documents/GUIS/DiceGame/src/sample/DiceImages/Dice" +
                    six + ".png"));

            //makes the image clickable
            dice1Image.setOnMouseClicked(imageEvent -> {
                dice1Image.setImage(new Image("file:/Users/ronaldtran/" +
                        "Documents/GUIS/DiceGame/src/sample/DiceImages/Dice"+
                        one +"held.png"));
            });

            dice1Two.setOnMouseClicked(imageEvent -> {
                dice1Two.setImage(new Image("file:/Users/ronaldtran/" +
                        "Documents/GUIS/DiceGame/src/sample/DiceImages/Dice" +
                        two + "held.png"));
            });

            dice1Three.setOnMouseClicked(imageEvent -> {
                dice1Three.setImage(new Image("file:/Users/ronaldtran/" +
                        "Documents/GUIS/DiceGame/src/sample/DiceImages/Dice" +
                        three + "held.png"));
            });

            dice1Four.setOnMouseClicked(imageEvent -> {
                dice1Four.setImage(new Image("file:/Users/ronaldtran/" +
                        "Documents/GUIS/DiceGame/src/sample/DiceImages/Dice" +
                        four + "held.png"));
            });

            dice1Five.setOnMouseClicked(imageEvent -> {
                dice1Five.setImage(new Image("file:/Users/ronaldtran/" +
                        "Documents/GUIS/DiceGame/src/sample/DiceImages/Dice" +
                        five + "held.png"));
            });

            dice1Six.setOnMouseClicked(imageEvent -> {
                dice1Six.setImage(new Image("file:/Users/ronaldtran/" +
                        "Documents/GUIS/DiceGame/src/sample/DiceImages/Dice" +
                        six + "held.png"));
            });

            if (rolls == 0) {
//                rollDice.setDisable(true);

                rollDice.setText("Play Again");
                Alert myAlert = new Alert(Alert.AlertType.INFORMATION,
                        "You are out of rolls!");
                myAlert.show();
            }

        });

        //debugging
//        mainGridPane.setGridLinesVisible(true);

        Scene scene = new Scene(mainGridPane);
        scene.getStylesheets().add("file:/Users/ronaldtran/Documents/" +
                "GUIS/DiceGame/src/sample/DiceStyleSheets.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}