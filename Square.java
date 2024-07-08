import java.util.ArrayList;

public class Square {
    public int owner;
    public int distance;
    public ArrayList<Integer> distances = new ArrayList<>();
    private final int ROW;
    private final int COLUMN;

    public Square(int sellers, int row, int column) {
        for (int i = 0; i < sellers; i++) {
            distances.add(0);
        }
        COLUMN = column;
        ROW = row;
    }

    public int getDistance(int[] seller) {
        return Math.abs(seller[0] - ROW) + Math.abs(seller[1] - COLUMN);
    }

    public void getDistances(ArrayList<int[]> sellers) {
        for (int i = 0; i < sellers.size(); i++) {
            distances.set(i, getDistance(sellers.get(i)));
        }
        int minIndex = 0;
        int minValue = distances.get(0);
        boolean isDraw = false;

        for (int i = 1; i < distances.size(); i++) {
            if (distances.get(i) < minValue) {
                minValue = distances.get(i);
                minIndex = i;
                isDraw = false;
            } else if (distances.get(i) == minValue) {
                isDraw = true;
            }
        }
        owner = minIndex;
        distance = minValue;
        if (isDraw) {
            owner = distances.size();
        }

    }
}
