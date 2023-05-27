package b_java_util_concurrent.fork_Join_pool_RecursiveAction;

import java.util.concurrent.ForkJoinPool;

public class ForkJoinDemo {
    public static void main(String[] args) {

        long beginTime;
        long endTime;

        int pLevel = 2;

        ForkJoinPool fjp = new ForkJoinPool(pLevel);
        double[] nums = new double[1000000];

        for(int i =0; i< nums.length; i++) {
            nums[i] = (double) i;
        }

        System.out.println("Часть исходной последовательности: ");
        for(int i=0; i<10; i++) {
            System.out.print(nums[i] + " ");
        }
        System.out.println();

        SqrtTransform task = new SqrtTransform(nums, 0, nums.length);

        beginTime = System.nanoTime();
        fjp.invoke(task);
        endTime = System.nanoTime();

        System.out.println("Часть преобразованной последовательности: ");
        for(int i = 0; i<10; i++) {
            System.out.format("%.4f", nums[i]);
        }

        System.out.println();
        System.out.println();
        System.out.println("Уровень параллелизма: "+ pLevel);
        System.out.println("Затраченное время : " + (endTime-beginTime)/1000 + " микросекунд");

    }
}
