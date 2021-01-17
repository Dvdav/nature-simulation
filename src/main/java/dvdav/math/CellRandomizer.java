package dvdav.math;

import java.util.List;
import java.util.Random;

public class CellRandomizer {
    public static <T> T getRandom(List<T> list) {
        Random random = new Random();
        int move = random.nextInt(list.size());

        return list.get(move);
    }
}
