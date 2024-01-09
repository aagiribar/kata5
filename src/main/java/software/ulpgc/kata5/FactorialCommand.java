package software.ulpgc.kata5;

public class FactorialCommand implements Command {
    @Override
    public Output execute(Input input) {
        try {
            int a = Integer.parseInt(input.get(":a"));
            return checkBounds(a);
        }
        catch (NumberFormatException e) {
            return nanOutput();
        }
    }

    private Output checkBounds(int a) {
        if (a >= 0 && a <= 10) return outputOf(a);
        else return outOfRangeOutput();
    }

    private Output outputOf(int a) {
        return new Output() {
            @Override
            public int responseCode() {
                return 200;
            }

            @Override
            public String result() {
                return String.valueOf(factorial(a));
            }
        };
    }

    private int factorial(int a) {
        int result = 1;
        for (int i = a; i > 0; i--) {
            result *= i;
        }
        return result;
    }

    private Output outOfRangeOutput() {
        return new Output() {
            @Override
            public int responseCode() {
                return 400;
            }

            @Override
            public String result() {
                return "a is out of range. a must be between 0 and 10";
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
                return "a is not a number";
            }
        };
    }
}
