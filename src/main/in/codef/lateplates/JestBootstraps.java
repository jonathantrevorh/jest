package in.codef.lateplates;

public class JestBootstraps {
    public static final String controllerRoot;
    public static final boolean hotLoadEnabled = false;

    static {
        String fullName = JestBootstraps.class.getName();
        controllerRoot = fullName.substring(0, fullName.lastIndexOf(".")) + ".controllers";
    }

}
