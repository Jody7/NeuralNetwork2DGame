import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class GenNeuron {

    public Neuron[] genNeuron() {
        ArrayList<Neuron> temp = new ArrayList();
        boolean done = false;
        char[] inputs = new char[]{'A', 'S', 'D', 'W'};


        for (int i = 0; i < 4; i++) {
            if (i < 4) {
                temp.add(new Neuron(true, inputs[i]));
            }
        }
        for (int i = 0; i < 4; i++) {
            if (i < 4) {
                temp.add(new Neuron(false, inputs[i]));
            }
        }
        //Generates all possible neuron combo's

        Neuron[] neurons = Arrays.copyOf(temp.toArray(), temp.size(), Neuron[].class);

        System.out.println("generated neurons");

        return neurons;
    }

}
