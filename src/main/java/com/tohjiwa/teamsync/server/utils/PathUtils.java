package com.tohjiwa.teamsync.server.utils;

import java.util.Optional;

public class PathUtils {
    /**
     * To verify a String path is a path of class path or not. classpath path indicate with prefix classpath: before the actual path.
     * example classpath:path/of/resource/file.jpg
     *
     * @param path String of path need to check
     * @return true if the path is a classpath path
     */
    boolean isClassPath(String path) {
        var strSegment = path.split(":");
        if (strSegment.length > 0 && strSegment.length <= 2) {
            return strSegment[0].equals("classpath");
        }
        return false;
    }

    Optional<String> getPath(String path) {
        if(isClassPath(path)) {
            var purePath = getPurePath(path);
            if(purePath.isPresent()) {
                return Optional.of(this.getClass().getClassLoader().getResource(purePath.get()).getPath());
            }
            return Optional.empty();
        } else {
            return Optional.of(path);
        }
    }

    Optional<String> getPurePath(String path) {
        var strSegment = path.split(":");
        if (strSegment.length == 2 && strSegment[0].equals("classpath")) {
            return Optional.of(strSegment[1]);
        } else if(strSegment.length == 1 && !strSegment[0].equals("classpath")) {
            return Optional.of(strSegment[0]);
        }

        return Optional.empty();
    }
}
