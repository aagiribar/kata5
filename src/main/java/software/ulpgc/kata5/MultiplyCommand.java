package software.ulpgc.kata5;

public class MultiplyCommand implements Command {
    @Override
    public Output execute(Input input) {
        try {
            int a = Integer.parseInt(input.get(":a"));
            int b = Integer.parseInt(input.get(":b"));
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
                return String.valueOf(a * b);
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
