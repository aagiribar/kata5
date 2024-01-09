package software.ulpgc.kata5;

public class DivideCommand implements Command {
    @Override
    public Output execute(Input input) {
        try {
            int a = Integer.parseInt(input.get(":a"));
            int b = Integer.parseInt(input.get(":b"));
            if (b == 0) {
                return bIsZeroOutput();
            }
            return outputOf(a, b);
        }

        catch (NumberFormatException e) {
            return nanOutput();
        }
    }

    private Output outputOf(int a, int b) {
        return new Output() {
            @Override
            public int responseCode() {
                return 200;
            }

            @Override
            public String result() {
                return String.valueOf((double) a / (double) b);
            }
        };
    }

    private Output bIsZeroOutput() {
        return new Output() {
            @Override
            public int responseCode() {
                return 400;
            }

            @Override
            public String result() {
                return "Invalid input. Divisor is equal to zero";
            }
        };
    }

    private Output nanOutput() {
        return new Output() {
            @Override
            public int responseCode() {
                return 400;
            }

            @Override
            public String result() {
                return "Something is not a number";
            }
        };
    }
}
