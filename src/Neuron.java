public class Neuron {

    public boolean INPUT;
    public char direction;
    public int fitness = 0;
    /*
    directions = wasd

        w
    a   /   d
        s

     */

    public Neuron(boolean _input, char _direction) {
        INPUT = _input;
        direction = _direction;
    }

    public char fireNeuron(boolean _input) {

        if (_input == INPUT) {

            return direction;
            //If the input of the neuron is valid, go to output with the response
        }
        return '.';
        //Remain doing the action...
    }

    public String toString() {
        return INPUT + ":" + direction;
    }

}
