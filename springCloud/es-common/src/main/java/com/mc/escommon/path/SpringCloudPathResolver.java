package com.mc.escommon.path;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class SpringCloudPathResolver {
    private static final String SPRING_CLOUD_DIR = "springCloud";

    private SpringCloudPathResolver() {
    }

    // Running a single module from IDEA changes user.dir, so locate the shared springCloud root first.
    public static Path resolveSpringCloudRoot() {
        Path current = Paths.get(System.getProperty("user.dir")).toAbsolutePath().normalize();
        for (Path cursor = current; cursor != null; cursor = cursor.getParent()) {
            if (cursor.getFileName() != null && SPRING_CLOUD_DIR.equals(cursor.getFileName().toString())) {
                return cursor;
            }

            Path child = cursor.resolve(SPRING_CLOUD_DIR);
            if (Files.isDirectory(child)) {
                return child;
            }
        }
        return current;
    }

    public static Path resolveFilesDir() {
        return resolveSpringCloudRoot().resolve("files").normalize();
    }

    public static String resolveSm2Dir() {
        return resolveFilesDir().resolve("sm2File").toString() + File.separator;
    }
}
