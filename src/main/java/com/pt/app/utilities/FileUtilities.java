package com.pt.app.utilities;

import java.io.File;

public class FileUtilities {
    public static boolean fileExists(String fileName) {
        File file = new File(fileName);
        return file.exists();
    }
}
