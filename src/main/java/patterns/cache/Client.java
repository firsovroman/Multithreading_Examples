package patterns.cache;

import java.util.concurrent.ExecutionException;

public class Client {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        MemoizerTest memoizerTest = new MemoizerTest();

        memoizerTest.testCompute();


    }

}
