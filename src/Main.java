import org.apache.commons.cli.*;


public class Main {

    private static Options options()
    {
        Option size = new Option("s", "size", true, "size of the image");

        Option rect = new Option("r", "rect", true, "coordinate plane");

        Option tasks = new Option("t", "tasks", true, "number of threads");

        Option filename = new Option("f","filename",true,"picture name");

        Option isquiet = new Option("q","quiet",true,"is quiet");

        Options options = new Options();

        options.addOption(size);

        options.addOption(rect);

        options.addOption(tasks);

        options.addOption(filename);

        options.addOption(isquiet);

        return options;
    }


    private static Fractal mandelbrot(CommandLine cmd)
    {
        int width = 640, height = 480;
        if(cmd.hasOption("s")) {
            String arg = cmd.getOptionValue("s");
            String[] res = arg.split("x");
            width = Integer.parseInt(res[0]);
            height = Integer.parseInt(res[1]);

            if(width < 0 || height < 0) {
                throw new IllegalArgumentException("width and height must be positive integers");
            }
        }

        int threadCount = 4;
        if(cmd.hasOption("t"))
        {
            threadCount = Integer.parseInt(cmd.getOptionValue("t"));

            if(threadCount < 1) {
                throw new IllegalArgumentException("tasks must be bigger than 0");
            }
        }
        String filename = "mandelbrot.png";
        if(cmd.hasOption("f"))
        {
            filename = cmd.getOptionValue("f");
        }

        boolean isquiet = false;
        if(cmd.hasOption("q"))
        {
            isquiet = true;
        }


        return new Fractal(width, height,threadCount,filename,isquiet);
    }

    private static CommandLine cmd(Options options, String[] args)
    {
        CommandLineParser parser = new DefaultParser();
        try {
            return parser.parse(options, args);
        } catch(ParseException ex) {
            throw new IllegalArgumentException("Parsing error: " + ex.getMessage());
        }
    }


    public static void generate(String[] args) {
        Options options = options();
        CommandLine cmd = cmd(options, args);
        Fractal mandelbrot = mandelbrot(cmd);

        mandelbrot.generate();
    }

    public static void main(String[] args) {
        generate(args);
    }
}