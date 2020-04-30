package sample;

public class slotcode {
    public static class Symbol extends Betting{
        private int value;
        private String text;

        public void setText(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
        private int jackpot;
        private int unoDosTres = 150;
        private int lameBar = 50;
        private int cherryBomb = 5;

        public void setJackpot(int jackpot) {
            this.jackpot = jackpot;
        }

        public Symbol createNewSymbol(){
            Symbol symbol;
            int num = (int) Math.floor(Math.random()*6);
            if(num == 0){
                symbol = new Blanks();
            }
            else if (num == 1){
                symbol = new Bars();
            }
            else if (num == 2){
                symbol = new DoubleBars();
            }
            else if (num == 3){
                symbol = new TripleBars();
            }
            else if (num == 4){
                symbol = new Sevens();
            }
            else if (num == 5){
                symbol = new Cherries();
            }
            else  symbol = new Symbol();

            return symbol;
        }
    }
    public static class Bars extends Symbol{
        Bars(){
            setValue(1);
            setJackpot(100);
            setText("-");
        }

    }
    public static class DoubleBars extends Symbol{
        DoubleBars(){
            setValue(2);
            setJackpot(200);
            setText("=");
        }

    }
    public static class TripleBars extends Symbol{
        TripleBars(){
            setValue(3);
            setJackpot(300);
            setText("≡");
        }

    }
    public static class Sevens extends Symbol{
        Sevens(){
            setValue(4);
            setJackpot(500);
            setText("7");
        }

    }
    public static class Cherries extends Symbol{
        Cherries(){
            setValue(5);
            setJackpot(1000);
            setText("§");
        }

    }
    public static class Blanks extends Symbol{
        Blanks(){
            setValue(6);
            setText("");
        }
    }

    public static class Betting{
        private int money;
        private int wager = 5;

        public int getMoney() {
            return money;
        }

        public void setMoney(int money) {
            this.money = money;
        }

        public void setWager(int wager) {
            this.wager = wager;
        }

        public int getWager() {
            return wager;
        }

    }
    public static class Board extends Betting{
        Board(Symbol[] symbols){

        }
        public int winCondition(Symbol[] symbols, Board board){
            int original = getMoney();
            int winnings = 0;
            if(symbols[0].value == symbols[1].value && symbols[0].value == symbols[2].value){

                winnings += symbols[1].jackpot;
            }
            else if(symbols[0].value<=3 && symbols[1].value<=3 && symbols[2].value<=3){
                if(symbols[0].value +symbols[1].value +symbols[2].value ==6){

                    winnings += symbols[1].unoDosTres;
                }
                else{

                    winnings += symbols[1].lameBar;
                }
            }
            else{
                int cherryCount = 0;
                for(int i = 0; i<symbols.length; i++){
                    if(symbols[i].value == 5){
                        cherryCount +=1;
                    }
                }
                winnings += symbols[1].cherryBomb *cherryCount;
                if(cherryCount>0){

                }
            }
            if(board.getWager()<=5){
                return winnings + original;
            }
            else if(board.getWager()<=10){
                return winnings*2 + original;
            }
            else if(board.getWager()>10){
                return winnings*3 + original;
            }
            else return winnings + original;

        }
    }

    public static void main(String args[]){
        slotcode.Symbol[] board = new slotcode.Symbol[3];
        slotcode.Board slots = new slotcode.Board(board);
        slots.setMoney(1000);
        for(int i = 0; i<board.length; i++){
            board[i] = new slotcode.Symbol();
            board[i] = board[i].createNewSymbol();
            System.out.print(board[i].text);
        }
        int num = slots.winCondition(board, slots);
        System.out.println(num);

    }
}
