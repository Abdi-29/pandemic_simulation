import java.io.*;
class Main {
    public static void main(String[] argv) {
        gameRules  game = new gameRules(argv);

        game.startSimulation();
    }
}