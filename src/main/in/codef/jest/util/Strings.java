package in.codef.jest.util;

public class Strings {

    public static String ucfirst(String s) {
        return (s.length() > 0) ? s.substring(0, 1).toUpperCase() + s.substring(1) : "";
    }

    public static String lcfirst(String s) {
        return (s.length() > 0) ? s.substring(0, 1).toLowerCase() + s.substring(1) : "";
    }

}
