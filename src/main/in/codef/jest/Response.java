package in.codef.jest;

import in.codef.jest.controller.HttpException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Response {

    private final HttpServletResponse response;
    private final PrintWriter out;

    public Response(HttpServletResponse response) throws IOException {
        if (response == null) throw new NullPointerException();
        this.response = response;
        this.out = response.getWriter();
    }

    public PrintWriter getWriter() {
        return out;
    }

    public void sendError(int code, String message, Throwable cause) {
        response.setStatus(code);
        out.println(message);
        if (cause != null) cause.printStackTrace(out);
    }

    public void sendError(HttpException e) {
        this.sendError(e.getCode(), e.getMessage(), e);
    }

    public void sendError(int code, String message) {
        this.sendError(code, message, null);
    }

    public void print(String s) {
        out.print(s);
    }

    public void commit() throws IOException {
        out.flush();
        response.flushBuffer();
    }

    public void close() throws IOException {
        this.commit();
        out.close();
    }
}
