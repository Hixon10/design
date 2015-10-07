package controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.commons.codec.binary.Base64;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import ru.spbau.pavlyutchenko.task1.Shell;
import views.html.index;

import java.io.IOException;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render("Web shell"));
    }

    public static Result shell(String command) {
        ObjectNode result = Json.newObject();
        String commandResult = "";

        Shell shell = null;

        try {
            shell = new Shell();

            byte[] decodedCmd = Base64.decodeBase64(command);
            commandResult = shell.runHelper(new String(decodedCmd));
        } catch (ReflectiveOperationException | IOException e) {
            e.printStackTrace();
        }

        result.put("result", commandResult);

        return ok(result);
    }
}
