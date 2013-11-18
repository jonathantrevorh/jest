package in.codef.jest;

import java.io.PrintWriter;
import java.util.Map;

public class View {

    Request request;
    Response response;

    public View(Request request, Response response) {
        this.request = request;
        this.response = response;
    }

    public void render(String relative, Map<String, Object> params) {
        renderParams(params);
    }

    public void renderParams(Map<String, Object> params) {
        PrintWriter writer = response.getWriter();
        for (Map.Entry<String, Object> param : params.entrySet()) {
            writer.println(param.getKey());
            writer.println(param.getValue().toString());
            writer.flush();
        }
    }

}
