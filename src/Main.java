import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        /*
        int[] example = new Signs().getExamples()[9];
*/
        int[] example = new Signs().setExample();
        Perceptron perceptron = new Perceptron();
        float[] badRes = perceptron.getOutputData(example);
        System.out.println(Arrays.toString(badRes));
        interpretator(badRes);
/*
        Perceptron perceptron = new Perceptron();
        int[][] example = new Signs().getExamples();
        int[][] res = new Signs().getResults();
        for (int i = 0; i < example.length; i++) {
            float[] badRes = perceptron.getOutputData(example[i]);
            perceptron.study(example[i], res[i], badRes);
            System.out.println(Arrays.toString(badRes));
            interpretator(badRes);
        }*/

    }

    public static void interpretator(float[] badRes){
        int max = 0;
        for (int i = 0; i < badRes.length; i++) {
            if (badRes[max] < badRes[i]) max = i;
        }
        switch (max){
            case 0:
                System.out.println("Это птица");
                break;
            case 1:
                System.out.println("Это рыба");
                break;
            case 2:
                System.out.println("Это зверь");
                break;
            case 3:
                System.out.println("Это насекомое");
                break;
        }
    }
}