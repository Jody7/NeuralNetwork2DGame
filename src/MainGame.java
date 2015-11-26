import javax.swing.*;
import java.awt.*;

public class MainGame {

    static int x = 750;
    static int y = 400;
    static boolean moveUpAval = false;
    static boolean moveRightAval = false;
    static int fitness = 0;

    static int input1Best = 0;
    static int input2Best = 0;


    public static void W() {
        if (y == 50) {
            //Can't go up when at the top
        } else {
            y = y - 50;
        }
    }

    public static void A() {
        if (y == 50) {
            //Can't go to the left unless at top
            x = x - 50;
        }
    }

    public static void S() {
        y = y + 50;
    }

    public static void D() {
        if (y == 50) {
            //Can't go to the right unless at top
            x = x + 50;
        }
    }

    public static void main(String[] args) throws Exception {


        JFrame jframe = new JFrame();
        jframe.setSize(1000, 600);
        Canvas canvas = new Canvas();
        jframe.getContentPane().add(canvas);
        jframe.setVisible(true);

        Graphics g = canvas.getGraphics();

        g.setColor(Color.blue);

        GenNeuron n = new GenNeuron();

        Neuron[] neurons = n.genNeuron();

        System.out.println(neurons.length);

        for (Neuron ne : neurons) {
            System.out.println(ne.toString());
        }

        int steps = 0;

        int input = 0;

        int typeOfInput = 0;

        while (true) {

            if (steps > 10) {

                if (input < 8) {
                    //System.out.println("set fitness");
                    neurons[input].fitness = Math.abs(fitness);
                }

                x = 750;
                y = 400;
                moveUpAval = false;
                moveRightAval = false;
                fitness = 0;
                input++;

                steps = 0;

                if (input == 8 && typeOfInput == 0) {
                    input = 0;
                    typeOfInput = 1;

                    int tempA = 0;

                    for (int i = 0; i < neurons.length; i++) {
                        System.out.println(neurons[i].fitness);
                        if (neurons[i].fitness > tempA) {
                            tempA = neurons[i].fitness;
                            input1Best = i;

                        }

                    }

                    System.out.println("BEST NEURON IS: " + neurons[input1Best].toString());


                    System.out.println("Switching Input Type... Generating Network for Next Input");
                } else if (input == 8 && typeOfInput == 1) {

                    input = -9;
                    int tempA = 0;
                    for (int i = 0; i < neurons.length; i++) {
                        if (neurons[i].fitness > tempA) {
                            tempA = neurons[i].fitness;
                            input2Best = i;
                        }

                    }


                    if (input == -9) {
                        System.out.println("Finished. Read Out for Best: input1: " + neurons[input1Best].toString() + " input2: " + neurons[input2Best].toString());

                    }
                    System.out.println("BEST NEURON IS: " + neurons[input2Best].toString());


                }

                if (input != -9) {
                    System.out.println("Restarting Simulation. Selecting Neuron: " + input);
                    System.out.println("NEURON DETAILS:  " + neurons[input].toString());
                }

            }

            if (input != -9) {

                steps++;


                fitness = (y - 900) + (50 - x);

                System.out.println(moveUpAval + ":" + moveRightAval + " fitness =" + fitness);

                moveUpAval = y > 50;
                moveRightAval = !moveUpAval;


                if (moveUpAval == neurons[input].INPUT && typeOfInput == 0) {

                    //Begin input1 network
                    if (neurons[input].direction == 'W') {
                        W();
                    } else if (neurons[input].direction == 'A') {
                        A();
                    } else if (neurons[input].direction == 'S') {
                        S();
                    } else if (neurons[input].direction == 'D') {
                        D();
                    } else {
                        System.out.println("Error with neuron input check.");
                    }
                } else if (typeOfInput == 1) {


                    //Use input1's best input

                    System.out.println(moveUpAval + " : " + neurons[input1Best].INPUT);

                    if (moveUpAval == neurons[input1Best].INPUT) {


                        //Begin input1 network
                        if (neurons[input1Best].direction == 'W') {
                            W();
                        } else if (neurons[input1Best].direction == 'A') {
                            A();
                        } else if (neurons[input1Best].direction == 'S') {
                            S();
                        } else if (neurons[input1Best].direction == 'D') {
                            D();
                        } else {
                            System.out.println("Error with neuron input check.");
                        }
                    }

                    if (moveRightAval == neurons[input].INPUT) {

                        //Begin input2 network

                        if (neurons[input].direction == 'W') {
                            W();
                        } else if (neurons[input].direction == 'A') {
                            A();
                        } else if (neurons[input].direction == 'S') {
                            S();
                        } else if (neurons[input].direction == 'D') {
                            D();
                        } else {
                            System.out.println("Error with neuron input check.");
                        }
                    }

                }


                g.setColor(Color.red);
                g.fillRect(x, y, 50, 50);

                //draw target
                g.fillOval(900, 50, 50, 50);
                g.setColor(Color.blue);


                g.fillRect(700, 100, 50, 500);
                g.fillRect(700, 0, 600, 50);

                g.fillRect(800, 100, 600, 50);
                g.fillRect(800, 100, 600, 500);


                g.drawString("Can Move Up", 100, 100);
                g.drawOval(100, 100, 50, 50);

                g.drawString("Can Move Right", 100, 200);
                g.drawOval(100, 200, 50, 50);

                int index = 0;
                int indexL = 0;
                for (Neuron ne : neurons) {

                    g.setColor(Color.blue);


                    g.fillRect(700, 100, 50, 500);
                    g.fillRect(700, 0, 600, 50);

                    g.fillRect(800, 100, 600, 50);
                    g.fillRect(800, 100, 600, 500);

                    g.drawLine(120, 120, 200, 100 + index);
                    g.drawLine(120, 220, 200, 100 + index);

                    if (input == indexL && typeOfInput == 0) {

                        g.setColor(Color.red);
                        g.drawLine(120, 120, 200, 100 + index);
                        g.setColor(Color.blue);
                    }

                    if (input == indexL && typeOfInput == 1) {
                        g.setColor(Color.red);
                        g.drawLine(120, 220, 200, 100 + index);
                        g.drawLine(120, 130, 200, 99 + (input1Best * 40));
                        g.setColor(Color.blue);
                    }

                    g.drawOval(200, 100 + index, 20, 20);
                    g.drawString(ne.toString(), 200, 100 + index);
                    index = index + 40;
                    indexL++;
                }

            } else {


                g.drawString("Can Move Up", 100, 100);
                g.drawOval(100, 100, 50, 50);

                g.drawString("Can Move Right", 100, 200);
                g.drawOval(100, 200, 50, 50);

                int index = 0;
                int indexL = 0;
                for (Neuron ne : neurons) {
                    g.setColor(Color.red);
                    g.drawLine(120, 120, 200, 100 + (input1Best * 40));
                    g.drawLine(120, 220, 200, 100 + (input2Best * 40));
                    g.setColor(Color.blue);

                    g.drawOval(200, 100 + index, 20, 20);
                    g.drawString(ne.toString(), 200, 100 + index);
                    index = index + 40;
                    indexL++;
                }

            }


            Thread.sleep(100);
            canvas.paint(g);
        }


    }

}
