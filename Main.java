import java.io.*;
class Main {
    public static void main(String[] argv) {
        GameRules  game = new GameRules(argv);

        game.startSimulation();
    }
}