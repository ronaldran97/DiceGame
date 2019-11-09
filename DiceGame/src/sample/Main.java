package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class Main extends Application {

  //holds all the dice images
  ArrayList<Image> imageList = new ArrayList<>(12);

  //The dice
  ArrayList<Dice> diceList = new ArrayList<>(5);

  //Dice objects
  Dice dice1;
  Dice dice2;
  Dice dice3;
  Dice dice4;
  Dice dice5;

  //top section
  int overAllScore = 0;
  Label overallScoreLbl = new Label("Overall Score: ");

  //round score
  int roundScore = 0;
  Label roundScoreLbl = new Label("Round Score: ");

  //Labels remaining
  int rollsRemaining = 3;
  Label rolesRemainLbl = new Label("Rolls Remaining: ");

  //dice rolling button
  Button rollDice = new Button("Roll Dice");

  Boolean newRound = false;

  Label result = new Label();

  //resets text
  void updateText() {
    if (newRound) {
      roundScore = 0;
      rollsRemaining = 3;
      rollDice.setText("Roll Dice");
      newRound = false;
    }

    overallScoreLbl.setText("Overall Score: " + overAllScore);
    roundScoreLbl.setText("Round Score: " + roundScore);
    rolesRemainLbl.setText("Rolls Remaining: " + rollsRemaining);

  }


  //change to relative path
  void buildDice() {
    Image oneDice = new Image("file:./res/Dice1.png");
    Image oneDiceHeld = new Image("file:./res/Dice1Held.png");
    Image twoDice = new Image("file:./res/Dice2.png");
    Image twoDiceHeld = new Image("file:./res/Dice2Held.png");
    Image threeDice = new Image("file:./res/Dice3.png");
    Image threeDiceHeld = new Image("file:./res/Dice3held.png");
    Image fourDice = new Image("file:./res/Dice4.png");
    Image fourDiceHeld = new Image("file:./res/Dice4held.png");
    Image fiveDice = new Image("file:./res/Dice5.png");
    Image fiveDiceHeld = new Image("file:./res/Dice5held.png");
    Image sixDice = new Image("file:./res/Dice6.png");
    Image sixDiceHeld = new Image("file:./res/Dice6held.png");

    Collections.addAll(imageList, oneDice, twoDice, threeDice, fourDice,
            fiveDice, sixDice, oneDiceHeld, twoDiceHeld, threeDiceHeld,
            fourDiceHeld, fiveDiceHeld, sixDiceHeld);

    dice1 = new Dice();
    dice2 = new Dice();
    dice3 = new Dice();
    dice4 = new Dice();
    dice5 = new Dice();

    Collections.addAll(diceList, dice1, dice2, dice3, dice4, dice5);
  }


  //handles whats hppens when dice are clicked
  private void DiceHandlers() {

    dice1.diceImage.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
      if(rollsRemaining < 3 && rollsRemaining > 0) {
        if(dice1.isHeld) {
          dice1.setNotHeld();
        } else {
          dice1.setHeld();
        }
      }
    });

    dice2.diceImage.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
      if(rollsRemaining > 0 && rollsRemaining < 3) {
        if(dice2.isHeld) {
          dice2.setNotHeld();
        } else {
          dice2.setHeld();
        }
      }
    });

    dice3.diceImage.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
      if(rollsRemaining > 0 && rollsRemaining < 3) {
        if(dice3.isHeld) {
          dice3.setNotHeld();
        } else {
          dice3.setHeld();
        }
      }
    });

    dice4.diceImage.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
      if(rollsRemaining < 3 && rollsRemaining > 0) {
        if(dice4.isHeld) {
          dice4.setNotHeld();
        } else {
          dice4.setHeld();
        }
      }
    });

    dice5.diceImage.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
      if(rollsRemaining < 3 && rollsRemaining > 0) {
        if(dice5.isHeld) {
          dice5.setNotHeld();
        } else {
          dice5.setHeld();
        }
      }
    });

    rollDice.setOnAction(new updateScores());
  }

  class updateScores implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent event) {
      rollsRemaining--;
      if (rollsRemaining >= 0) {
        for (Dice dice : diceList) {
          if (!dice.isHeld) {
            dice.randomDice();
          }
        }

        updateText();
        if (rollsRemaining == 0) {
          determineScore();
          rollDice.setText("Play Again");
          updateText();
        }
      } else if (rollsRemaining < 0) {
        newRound = true;
        for (Dice dice : diceList) {
          if (dice.isHeld) {
            dice.setNotHeld();
          }
          dice.setRoll(0);
        }
        updateText();
      }
    }

    private void determineScore() {

      Collections.sort(diceList, new Comparator<Dice>() {

        @Override
        public int compare(Dice d1, Dice d2) {
          return d1.roll > d2.roll ? 1 : (d1.roll < d2.roll) ? -1 : 0;
        }

      });

      if(fiveOfAKind()) {
        result.setText("Five of a Kind!");
        overAllScore += 10;
        roundScore = 10;
      } else if(straightHand()) {
        result.setText("Straight!");
        overAllScore += 8;
        roundScore = 8;
      } else if(fourOfAKind()) {
        result.setText("Four of a Kind!");
        overAllScore += 7;
        roundScore = 7;
      } else if(fullHouse()) {
        result.setText("Full House!");
        overAllScore += 6;
        roundScore = 6;
      } else if(threeOfAKind()) {
        result.setText("Three of a Kind!");
        overAllScore += 5;
        roundScore = 5;
      } else if(twoPair()) {
        result.setText("Two Pair!");
        overAllScore += 4;
        roundScore = 4;
      } else if(twoOfAKind()) {
        result.setText("Two of a Kind!");
        overAllScore += 1;
        roundScore = 1;
      } else {
        result.setText("You didn't roll anything!");
        overAllScore += 0;
        roundScore = 0;
      }
    }


    private boolean fiveOfAKind() {

      int sameDice = 0;

      for(int i = 1; i < 5; i++) {
        if(diceList.get(i - 1).roll == (diceList.get(i).roll)) {
          sameDice++;
        }
      }

      if(sameDice == 4) {
        return true;
      } else {
        return false;
      }
    }
    private boolean straightHand() {
      for(int i = 1; i < 5; i++) {
        if(diceList.get(i - 1).roll != (diceList.get(i).roll - 1)) {
          return false;
        }
      }
      return true;
    }


    private boolean fullHouse() {
      int sameDice = 0;
      int diceValue = 0;
      int diceValueTwo = 0;
      boolean foundFirstMatch = true;
      for(int i = 1; i < 5; i++) {
        if(diceList.get(i - 1).roll == (diceList.get(i).roll)) {
          if(foundFirstMatch) {
            diceValue = diceList.get(i).roll;
            break;
          }
        }
      }

      for(int i = 1; i < 5; i++) {
        if(diceList.get(i - 1).roll == (diceList.get(i).roll)) {
          if(diceList.get(i).roll == diceValue) {
            sameDice++;
          }
          if(!(diceList.get(i).roll == diceValue)) {
            diceValueTwo++;
          }
        }
      }

      if((sameDice == 2 && diceValueTwo == 1) || (sameDice == 1 && diceValueTwo == 2)) {
        return true;
      } else {
        return false;
      }
    }


    private boolean threeOfAKind() {
      boolean firstThree = false;
      boolean secondThree = false;

      //first
      for(int i = 1; i < 3; i++) {
        if(diceList.get(i - 1).roll != diceList.get(i).roll) {
          firstThree = true;
        }
      }

      //last
      if(firstThree) {
        for(int i = 3; i < 5; i++) {
          if(diceList.get(i - 1).roll != diceList.get(i).roll) {
            secondThree = true;
          }
        }
      }

      //middle
      if(secondThree) {
        for(int i = 2; i < 4; i++) {
          if(diceList.get(i - 1).roll != diceList.get(i).roll) {
            return false;
          }
        }
      }
      return true;
    }


    private boolean twoPair() {
      int sameDice = 0;
      int matchedValue = 0;
      int matching2 = 0;
      boolean foundFirstMatch = true;
      for(int i = 1; i < 5; i++) {
        if(diceList.get(i - 1).roll == (diceList.get(i).roll)) {
          if(foundFirstMatch) {
            matchedValue = diceList.get(i).roll;
            break;
          }
        }
      }

      for(int i = 1; i < 5; i++) {
        if(diceList.get(i - 1).roll == (diceList.get(i).roll)) {
          if(diceList.get(i).roll == matchedValue) {
            sameDice++;
          }
          if(!(diceList.get(i).roll == matchedValue)) {
            matching2++;
          }
        }
      }

      if(sameDice == 1 && matching2 == 1) {
        return true;
      } else {
        return false;
      }
    }


    private boolean twoOfAKind() {
      int sameDice = 0;
      for(int i = 1; i < 5; i++) {
        if(diceList.get(i - 1).roll == (diceList.get(i).roll)) {
          sameDice++;
        }
      }

      if(sameDice == 1) {
        return true;
      } else {
        return false;
      }
    }


    private boolean fourOfAKind() {
      boolean firstFour = false;

      //first four
      for(int i = 1; i < 4; i++) {
        if(diceList.get(i - 1).roll != diceList.get(i).roll) {
          firstFour = true;
        }
      }

      //last four
      if(firstFour) {
        for(int i = 2; i < 5; i++) {
          if(diceList.get(i - 1).roll != diceList.get(i).roll) {
            return false;
          }
        }
      }
      return true;
    }
  }


  void StartGame(Stage primaryStage) {

    updateText(); //updates the text per round
    buildDice();  //builds the dice
    DiceHandlers();  //dice rolling

    primaryStage.setTitle("Dice Game");


    //overall score stuff box
    HBox OverallScoreBox = new HBox(10, overallScoreLbl);
    OverallScoreBox.setAlignment(Pos.CENTER);
    OverallScoreBox.setPadding(new Insets(25));

    //hbox for the dice images
    HBox diceBox = new HBox(10, dice1.diceImage, dice2.diceImage,
            dice3.diceImage, dice4.diceImage, dice5.diceImage);
    diceBox.setAlignment(Pos.CENTER);
    diceBox.setPadding(new Insets(25));

    //hbox for button
    HBox buttonBox = new HBox(10, rollDice);
    buttonBox.setAlignment(Pos.CENTER);
    buttonBox.setPadding(new Insets(10));

    //hbox for round score
    HBox roundScoreBox = new HBox(10, roundScoreLbl);
    roundScoreBox.setAlignment(Pos.CENTER);
    roundScoreBox.setPadding(new Insets(10));

    //hbox for remainign rollsRemaining
    HBox remainRollsBox = new HBox(10, rolesRemainLbl);
    remainRollsBox.setAlignment(Pos.CENTER);
    remainRollsBox.setPadding(new Insets(10));

    //hbox for the result word thing
    HBox resultBox = new HBox(10, result);
    resultBox.setAlignment(Pos.CENTER);
    resultBox.setPadding(new Insets(10));

    //gridpane that holds everything
    GridPane mainGridPane = new GridPane();
    mainGridPane.add(OverallScoreBox, 0, 0);
    mainGridPane.add(diceBox, 0, 1);
    mainGridPane.add(buttonBox, 0, 2);
    mainGridPane.add(roundScoreBox, 0, 3);
    mainGridPane.add(remainRollsBox, 0, 4);
    mainGridPane.add(resultBox, 0, 5);
    mainGridPane.setAlignment(Pos.CENTER);
    mainGridPane.setPadding(new Insets(10));

    System.out.println(rollsRemaining);

    Scene scene = new Scene(mainGridPane);
    scene.getStylesheets().add("file:./res/DiceStyleSheets.css");
    primaryStage.setScene(scene);
    primaryStage.show();
  }


  class Dice {
    ImageView diceImage;
    int roll;
    boolean isHeld;

    public Dice() {
      this.roll = 0;
      isHeld = false;
      diceImage = new ImageView(imageList.get(roll));
      diceImage.setFitHeight(50);
      diceImage.setFitWidth(50);
    }


    public void setRoll(int roll) {
      this.roll = roll;
      diceImage.setImage(imageList.get(roll));
    }

    //Generates a random dice roll
    public void randomDice() {
      Random random = new Random();
      roll = random.nextInt(6);
      diceImage.setImage(imageList.get(roll));
    }

    //boolean flag mechanics
    public void setHeld() {
      isHeld = true;
      diceImage.setImage(imageList.get((roll) + 6));
    }

    public void setNotHeld() {
      isHeld = false;
      diceImage.setImage(imageList.get(roll));
    }

  }

  @Override
  public void start(Stage primaryStage) throws Exception{
    StartGame(primaryStage);
  }


  public static void main(String[] args) {
    launch(args);
  }
}
