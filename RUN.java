import java.util.concurrent.TimeUnit;

public class RUN {
    public static void main(String[] args) {
        main simulation = new main(2, 10);
        GridDisplay gridDisplay = new GridDisplay(simulation.getBoard(), simulation.getSellers(), simulation.getSize());
        gridDisplay.createAndShowGUI();
        for (int i = 0; i < 100; i++) {
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
            }
            simulation.newPosition(simulation.getSeller());
            gridDisplay.refresh(simulation.getSellers());
        }
    }
}
