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
        Spark.port(8080);
        Spark.get("/welcome", (req, res) -> new CommandExecutor(req, res).execute("welcome"));
        Spark.get("/add/:a/:b", (req, res) -> new CommandExecutor(req, res).execute("add"));
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
