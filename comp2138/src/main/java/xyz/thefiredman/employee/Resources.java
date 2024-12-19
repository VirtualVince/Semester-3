package xyz.thefiredman.employee;
import java.io.File;
import java.io.IOException;

public final class Resources {
    // gets path from /resources/ dir next to .jar/executable
    private static String get(String path) {
        String root = new File(String.format("%s/%s", Main.RESOURCES_DIR, path)).getAbsolutePath().replace("\\", "/");
        return String.format("%s/%s", "file://", root);
    }

    // path to main css file
    public static final String css = Resources.get("index.css");

    // creates a file at path (if it doesn't exist)
    // either way it must return File info or crashes
    public static File initFS(String path) {
        File file = new File(String.format("%s/%s", Main.DATA_DIR, path));
        if (!file.exists()) {
            file.getParentFile().mkdirs();
        }

        try {
            file.createNewFile();
        } catch (IOException e) {
            crashFS(e);
        }

        return file;
    }

    // utlity crash function if unable to save changes
    public static void crashFS(IOException e) {
        System.out.println(String.format("Unable to save changes, do you have permission to read and write?: %s", e));
        System.exit(-1);
    }
}
