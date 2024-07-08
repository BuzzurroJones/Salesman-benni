import java.util.ArrayList;
import java.util.Random;

public class main {
    public ArrayList<ArrayList<Cell>> board = new ArrayList<>();
    public ArrayList<int[]> sellers = new ArrayList<>();
    public int seller = 0;
    public int size;

    public main(int sellersNum, int size) {
        this.size = size;
        Random rand = new Random();
        for (int i = 0; i < sellersNum; i++) {
            int[] seller = { rand.nextInt(size), rand.nextInt(size) };
            sellers.add(seller);
        }
        for (int i = 0; i < size; i++) {
            ArrayList<Cell> NewColumn = new ArrayList<>();
            for (int j = 0; j < size; j++) {
                Cell s = new Cell(sellersNum, i, j);
                NewColumn.add(s);
            }
            board.add(NewColumn);
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board.get(i).get(j).getDistances(sellers);
            }
        }
    }

    public void newPosition(int currentSeller) {
        int owned = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board.get(i).get(j).owner == currentSeller) {
                    owned++;
                }
            }
        }
        int sellerRow = sellers.get(currentSeller)[0];
        int sellerColumn = sellers.get(currentSeller)[1];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int[] seller = { i, j };
                int newOwned = 0;
                for (int w = 0; w < size; w++) {
                    for (int z = 0; z < size; z++) {
                        if (board.get(w).get(z).distance > board.get(w).get(z).getDistance(seller)) {
                            newOwned++;
                        }
                    }
                }
                if (newOwned > owned) {
                    sellerRow = i;
                    sellerColumn = j;
                    owned = newOwned;
                }
            }
        }
        int[] newCoord = { sellerRow, sellerColumn };
        sellers.set(currentSeller, newCoord);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board.get(i).get(j).getDistances(sellers);
            }
        }
        seller++;
        if (seller == sellers.size()) {
            seller = 0;
        }
    }
}