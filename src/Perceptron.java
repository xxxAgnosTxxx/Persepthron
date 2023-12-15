public class Perceptron {
    //признаки
    private static final int SIGNS = 10;
    //результаты
    private static final int RESULTS = 4;
    //выходной массив с итоговыми результатами
    private float[] outputData = new float[RESULTS];
    //слой нейронов
    private float[] neurons = new float[SIGNS];
    //веса выходного слоя
    private static float[][] outputWeights = new float[RESULTS][SIGNS];
    //веса входного слоя
    private static float[][] inputWeights = new float[SIGNS][SIGNS];

    private static final CSVUtils UTILS = new CSVUtils();

    public Perceptron() {
/*
        for (int i = 0; i < SIGNS; i++) {
            for (int j = 0; j < SIGNS; j++) {
                inputWeights[i][j] = (float) (Math.random()*0.2+0.1);
            }
        }
        UTILS.setWeightsToFile(inputWeights, UTILS.getInlayer());

        for (int i = 0; i < RESULTS; i++) {
            for (int j = 0; j < SIGNS; j++) {
                outputWeights[i][j] = (float) (Math.random()*0.2+0.1);
            }
        }
        UTILS.setWeightsToFile(outputWeights, UTILS.getOutlayer());*/

        inputWeights = UTILS.getWeightsFromFile(SIGNS, SIGNS, UTILS.getInlayer());
        outputWeights = UTILS.getWeightsFromFile(RESULTS, SIGNS, UTILS.getOutlayer());
    }

    public float[] getOutputData(int[] example) {
        //массив с данными о животном
        for (int i = 0; i < neurons.length; i++) {
            float sumNeuron = 0;
            for (int j = 0; j < example.length; j++) {
                sumNeuron += example[j] * inputWeights[i][j];
            }
            neurons[i] = sigmoid(sumNeuron);
        }

        for (int i = 0; i < RESULTS; i++) {
            float sum = 0;
            for (int j = 0; j < SIGNS; j++) {
                sum += neurons[j] * outputWeights[i][j];
            }
            sum = sigmoid(sum);
            outputData[i] = sum;
        }
        return outputData;
    }

    private float sigmoid(float arg) {
        return (float) (1 / (1 + Math.pow(Math.E, -arg)));
    }

    public void study(int[] example, int[] result, float[] badRes) {
        for (int i = 0; i < result.length; i++) {
            float delta = result[i] - badRes[i];
            //меняем веса входного слоя
            for (int j = 0; j < inputWeights.length; j++) {
                for (int k = 0; k < inputWeights[j].length; k++) {
                    inputWeights[i][j] += delta * example[j];
                }
            }
            //меняем веса выходного слоя
            for (int j = 0; j < outputWeights[i].length; j++) {
                outputWeights[i][j] += delta * neurons[j];
            }
        }
        //перезаписываем новые данные весов
        UTILS.setWeightsToFile(inputWeights, UTILS.getInlayer());
        UTILS.setWeightsToFile(outputWeights, UTILS.getOutlayer());
    }
}
