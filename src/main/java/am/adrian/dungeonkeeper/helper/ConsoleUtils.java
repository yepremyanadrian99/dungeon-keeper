package am.adrian.dungeonkeeper.helper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.IOException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConsoleUtils {

    public static void clear() throws InterruptedException, IOException {
        ProcessBuilder pb = new ProcessBuilder("clear");
        Process startProcess = pb.inheritIO().start();
        startProcess.waitFor();
    }
}
