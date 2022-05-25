import java.io.*;
class Main {
    public static void main(String[] argv) {
        GameRules  game = new GameRules(argv);
        Visualiser   visualiser;

        game.startSimulation();
//        visualiser = new Visualiser(game);
//        visualiser.printingOut(game.simulation.getMap());
    }
}