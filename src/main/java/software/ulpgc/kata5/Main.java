package software.ulpgc.kata5;

import spark.Request;
import spark.Response;
import spark.Spark;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        commands.put("welcome", new WelcomeCommand());
        commands.put("add", new AddCommand());
        commands.put("multiply", new MultiplyCommand());
        commands.put("divide", new DivideCommand());
        commands.put("factorial", new FactorialCommand());
        Spark.port(8080);
        Spark.get("/welcome", (req, res) -> new CommandExecutor(req, res).execute("welcome"));
        Spark.get("/add/:a/:b", (req, res) -> new CommandExecutor(req, res).execute("add"));
        Spark.get("/multiply/:a/:b", (req, res) -> new CommandExecutor(req, res).execute("multiply"));
        Spark.get("/divide/:a/:b", (req, res) -> new CommandExecutor(req, res).execute("divide"));
        Spark.get("/factorial/:a", (req, res) -> new CommandExecutor(req, res).execute("factorial"));
    }

    static Map<String, Command> commands = new HashMap<>();
    private static class CommandExecutor {
        private final Request request;
        private final Response response;

        public CommandExecutor(Request request, Response response) {
            this.request = request;
            this.response = response;
        }

        public String execute(String name) {
            Command.Output output = commands.get(name).execute(input());
            response.status(output.responseCode());
            return output.result();
        }

        private Command.Input input() {
            return parameter -> oneOf(request.params(parameter), request.queryParams(parameter));
        }

        private String oneOf(String a, String b) {
            return a != null ? a : b;
        }
    }
}
