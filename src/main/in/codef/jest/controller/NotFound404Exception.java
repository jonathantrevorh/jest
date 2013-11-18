package in.codef.jest.controller;

import javax.servlet.http.HttpServletResponse;

public class NotFound404Exception extends HttpException {

    public NotFound404Exception(String message, Throwable cause) {
        super(HttpServletResponse.SC_NOT_FOUND, "Not Found", message, cause);
    }

    public NotFound404Exception(String message) {
        this(message, null);
    }

    public NotFound404Exception() {
        this(null);
    }

}
