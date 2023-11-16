package software.ulpgc.kata5;

public class WelcomeCommand implements Command{
    @Override
    public Output execute(Input input) {
        return new Output() {
            @Override
            public int responseCode() {
                return 200;
            }

            @Override
            public String result() {
                return "Welcome to my web server. This is a list of available commands:";
            }
        };
    }
}
