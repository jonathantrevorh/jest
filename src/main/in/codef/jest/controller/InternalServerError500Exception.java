package in.codef.jest.controller;

import javax.servlet.http.HttpServletResponse;

public class InternalServerError500Exception extends HttpException {

    public InternalServerError500Exception(String message, Throwable cause) {
        super(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal Server Errors", message, cause);
    }

    public InternalServerError500Exception(String message) {
        this(message, null);
    }

    public InternalServerError500Exception() {
        this(null);
    }

}
