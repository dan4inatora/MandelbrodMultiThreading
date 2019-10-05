
import java.awt.image.BufferedImage;
import org.apache.commons.math3.complex.Complex;


public class FractalRunnable implements Runnable {

    private int[] plate;

    private BufferedImage fractalImage;

    private int start;

    private int end;

    private int thread;

    private int maxiumum;

    private boolean quiet;





    public FractalRunnable(int[] _plate, BufferedImage _fractalImage, int _start, int _end, int _thread,boolean _quiet)
    {
        //the colors plate
        plate = _plate;
        //the image
        fractalImage = _fractalImage;

        start = _start;

        end = _end;

        maxiumum = plate.length;
        //the threads count
        thread = _thread;

        quiet = _quiet;

    }

    @Override
    public void run() {


        Timer timer = new Timer();
        timer.start();
        if(!quiet) {

            System.out.println("Started thread[" + thread + "]");

        }

        int w = fractalImage.getWidth();
        int h = fractalImage.getHeight();




        for(int i = 0; i < w; i++)
        {

            double real = (i - w/2.0)*(4.0/w);

            for(int j = start; j < end; j++)
            {
                double imaginary = (j - h/2.0)*(4.0/w);

                int stepCount = set(new Complex(real, imaginary));

                if(stepCount < maxiumum)
                {
                    fractalImage.setRGB(i, j, plate[stepCount]);
                }
                else
                {
                    fractalImage.setRGB(i, j, plate[0]);
                }
            }
        }
        if(!quiet) {
            System.out.println("Thread[" + thread + "] stopped. Time for execution: " + timer.stop() + " millis.");
        }

    }
    private int set(Complex number)
    {
        int stepCount = 0;

        //the escape radius
        double radius = 2.0;

        Complex z = new Complex(0.0, 0.0);



        while(z.abs() <= radius && stepCount < maxiumum) {

            z = z.multiply(z).multiply((z.multiply(z)).exp()).add(number);

            stepCount++;
        }

        return stepCount;
    }
}