package in.codef.jest.controller;

public class HttpException extends Exception {
    private int code = 500;
    private String status = "Internal Server Error";

    public HttpException(int code, String status, String message, Throwable cause) {
        super(message, cause);
        this.construct(code, status);
    }

    public HttpException(int code, String status, String message) {
        super(message);
        this.construct(code, status);
    }

    public HttpException(int code, String status) {
        super();
        this.construct(code, status);
    }

    private void construct(int code, String status) {
        this.code = code;
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public String getStatus() {
        return this.status;
    }

    @Override
    public String getMessage() {
        String message = getStatus();
        String exceptionMessage = super.getMessage();
        if (exceptionMessage != null) exceptionMessage += ": " + exceptionMessage;
        return message;
    }
}
