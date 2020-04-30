package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.awt.*;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        Scene scene = new Scene(grid, 1250, 750);
        primaryStage.setScene(scene);

        Button spin = new Button();
        Button sqr1 = new Button();
        Button sqr2 = new Button();
        Button sqr3 = new Button();
        Button bet = new Button();
        Button betMax = new Button();
        Button score = new Button();
        Button wager = new Button();

        wager.setId("money");


        grid.add(sqr1,0,0);
        grid.add(sqr2, 1,0);
        grid.add(sqr3,2,0);
        grid.add(spin,3,0);
        grid.add(bet,0,1);
        grid.add(betMax,1,1);
        grid.add(score,4,2);
        grid.add(wager,0,2);

        score.setMinSize(100,10);
        sqr1.setMinSize(100,200);
        sqr2.setMinSize(100,200);
        sqr3.setMinSize(100,200);
        spin.setMinSize(100,100);
        bet.setMinSize(100,50);
        betMax.setMinSize(100,50);

        spin.setText("Spin!");

        Label betText = new Label("   Bet One");
        grid.add(betText,0,1);

        Label betTextMax = new Label("   Bet Max");
        grid.add(betTextMax, 1,1);

        Label scoreboard = new Label("");
        grid.add(scoreboard,4,2);

        sqr1.setText("7");
        sqr2.setText("7");
        sqr3.setText("7");

        scene.getStylesheets().add
                (Main.class.getResource("slotmachine.css").toExternalForm());

        slotcode.Symbol[] board = new slotcode.Symbol[3];
        slotcode.Board slots = new slotcode.Board(board);
        slots.setMoney(1000);
        scoreboard.setText(String.valueOf(slots.getMoney()));
        wager.setText(String.valueOf(slots.getWager()));

        spin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                slots.setMoney(slots.getMoney()-slots.getWager());
                scoreboard.setText(String.valueOf(slots.getMoney()));

                for(int i = 0; i<board.length; i++){
                    board[i] = new slotcode.Symbol();
                    board[i] = board[i].createNewSymbol();
                }


                sqr1.setText(board[0].getText());
                sqr2.setText(board[1].getText());
                sqr3.setText(board[2].getText());

                slots.setMoney(slots.winCondition(board, slots));
                scoreboard.setText(String.valueOf(slots.getMoney()));

            }
        });

        bet.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(slots.getWager()<15){
                    slots.setWager(slots.getWager()+5);
                    wager.setText(String.valueOf(slots.getWager()));
                }
                else{
                    slots.setWager(5);
                    wager.setText(String.valueOf(slots.getWager()));
                }
            }
        });

        betMax.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                slots.setWager(15);
                wager.setText(String.valueOf(slots.getWager()));
            }
        });

                primaryStage.setTitle("Slot Machine");
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
