package fork_join;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * 将任务拆分成多个子任务的例子
 * @author lkmc2
 * @date 2019/8/6 8:54
 */
public class CountTask extends RecursiveTask<Integer> {

    /** 拆分任务的阈值 ***/
    private static final int THRESHOLD = 2;

    private int start;
    private int end;

    public CountTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;

        System.out.println(String.format("start = %s, end = %s", start, end));

        // 如果任务达不到阈值，直接计算任务
        boolean canCompute = (end - start) <= THRESHOLD;

        if (canCompute) {
            for (int i = start; i <= end; i++) {
                sum += i;
            }
        } else {
            // 如果任务大于阈值，就拆分成两个子任务计算
            int middle = (start + end) / 2;

            CountTask leftTask = new CountTask(start, middle);
            CountTask rightTask = new CountTask(middle, end);

            // 执行子任务
            leftTask.fork();
            rightTask.fork();

            // 等待子任务执行完，并获取其结果
            int leftResult = leftTask.join();
            int rightResult = rightTask.join();

            // 合并子任务
            sum = leftResult + rightResult;
        }
        return sum;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 线程池
        ForkJoinPool forkJoinPool = new ForkJoinPool();

        // 创建一个计算任务，计算1到100的和
        CountTask task = new CountTask(1, 4);

        // 执行一个任务
        ForkJoinTask<Integer> result = forkJoinPool.submit(task);
        System.out.println(result.get());
    }

}
