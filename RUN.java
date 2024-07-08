import java.util.concurrent.TimeUnit;

public class RUN {
    public static void main(String[] args) {
        main lol = new main(10, 10);
        GridDisplay gridDisplay = new GridDisplay(lol.board, lol.sellers, lol.size, lol.size);
        gridDisplay.createAndShowGUI();
        for (int i = 0; i < 100; i++) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lol.newPosition(lol.seller);
            gridDisplay.refresh(lol.sellers);
        }
    }
}
