package com.anonymous;

import java.io.IOException;
import java.nio.file.*;
import java.util.concurrent.TimeUnit;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

/**
 * Created by Adam on 10.04.2018.
 */
public class DirWatcher {

    void start(String path)
    {
        try {
            WatchService watcher = FileSystems.getDefault().newWatchService();
            Path dir = Paths.get(path);
            dir.register(watcher, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);

            System.out.println("Watch Service registered for dir: " + dir.getFileName());

            while (true) {
                WatchKey key;
                try {
                    key = watcher.take();
                } catch (InterruptedException ex) {
                    return;
                }



                for (WatchEvent<?> event : key.pollEvents()) {
                    WatchEvent.Kind<?> kind = event.kind();

                    System.out.println("Hi");
                    //Thread.sleep(1000);

                    @SuppressWarnings("unchecked")
                    WatchEvent<Path> ev = (WatchEvent<Path>) event;
                    Path fileName = ev.context();

                    System.out.println(kind.name() + ": " + fileName);

                    if (kind == ENTRY_MODIFY &&
                            fileName.toString().equals("DirectoryWatchDemo.java")) {
                        System.out.println("My source file has changed!!!");
                    }
                }

                boolean valid = key.reset();
                if (!valid) {
                    break;
                }

            }

        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

}
