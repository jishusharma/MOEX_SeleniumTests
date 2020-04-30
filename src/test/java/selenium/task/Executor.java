package selenium.task;

import org.junit.Test;
import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import selenium.sync.SyncClass;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Executor {

    @Test
    public void executeTests() throws InterruptedException, ExecutionException {
        JUnitCore junit = new JUnitCore();
        junit.addListener(new TextListener(System.out));

        ArrayList<CompletableFuture> results = new ArrayList<>();
        String[] classes = new String[]{"Test1", "Test2", "Test3"};

        for (String className : classes) {
            CompletableFuture test = CompletableFuture.supplyAsync(() -> {
                try {
                    return junit.run(Class.forName(className));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                return null;
            });
            results.add(test);
        }

        new Thread(() -> {
            try {
                Thread.sleep(1000);
                System.out.println("Call notify from other thread");
                SyncClass.syncTest(false);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        for (CompletableFuture completableFuture : results) {
            System.out.println(completableFuture.get());
        }

    }
}
