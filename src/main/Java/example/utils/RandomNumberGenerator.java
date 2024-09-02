package example.utils;

import java.util.Random;

public class RandomNumberGenerator {

    public int generateRandomNumber(int start, int end) {
        Random random = new Random();
        return random.nextInt(end - start) + start;
    }
}