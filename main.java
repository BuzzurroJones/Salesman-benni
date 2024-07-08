import java.util.ArrayList;
import java.util.Random;

public class main {
    private final ArrayList<ArrayList<Square>> board = new ArrayList<>();
    private final ArrayList<int[]> sellers = new ArrayList<>();
    private int seller = 0;
    private final int size;

    public main(int sellersNum, int size) {
        this.size = size;
        Random rand = new Random();
        if(sellersNum > 2){ for (int i = 0; i < sellersNum; i++) {
            int[] newseller = { rand.nextInt(size), rand.nextInt(size) };
            sellers.add(newseller);
        }}
        else{
            int[] tempSeller = {1,4};
            int[] seller2 = {9,9};
            sellers.add(tempSeller);
            sellers.add(seller2);

        }
        for (int i = 0; i < size; i++) {
            ArrayList<Square> NewColumn = new ArrayList<>();
            for (int j = 0; j < size; j++) {
                Square s = new Square(sellersNum, i, j);
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
                int[] newseller = { i, j };
                int newOwned = 0;
                for (int w = 0; w < size; w++) {
                    for (int z = 0; z < size; z++) {
                        if (board.get(w).get(z).distance > board.get(w).get(z).getDistance(newseller)) {
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

    public ArrayList<ArrayList<Square>> getBoard() {
        return board;
    }

    public ArrayList<int[]> getSellers() {
        return sellers;
    }

    public int getSize() {
        return size;
    }

    public int getSeller() {
        return seller;
    }
}