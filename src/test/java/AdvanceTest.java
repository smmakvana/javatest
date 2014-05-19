import co.uk.sanjay.ubstest.CommandProcessorImpl;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created with IntelliJ IDEA.
 * User: sanusoft
 * Date: 18/05/2014
 * Time: 03:53
 * To change this template use File | Settings | File Templates.
 */
public class AdvanceTest {

    private static final Integer TEN_THOUSAND = 10000;
    private static final String BAR = "bar@ubs.co.uk";
    private static final String FOO = "foo@ubs.co.uk";
    public CommandProcessorImpl commandProcessor;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void init() {
        commandProcessor = new CommandProcessorImpl();

    }

    @Test
    public void tenThreadTenThousandSenderTenRequestEach(){

        int threads=10;
        int senders=10000;
        int tasks=10;
        ExecutorService executor = Executors.newFixedThreadPool(threads);
        for (int i = 0; i < senders; i++) {
            Runnable worker = new TenRequestsTask(FOO + i);
            executor.execute(worker);
        }
        executor.shutdown();
        while (!executor.isTerminated()) {
        }
        Assert.assertEquals(senders, commandProcessor.getMessagesMap().size());
        for(AtomicInteger counter: commandProcessor.getMessagesMap().values()){
            Assert.assertEquals(tasks,counter.get());
        }
    }

    @Test
    public void oneThreadOneJob() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new WorkerThread(FOO));
        executorService.shutdown();
        while (!executorService.isTerminated()) {
        }
    }


    @Test
    public void oneThreadTwoJob() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new WorkerThread(FOO));
        executorService.execute(new WorkerThread(BAR));
        executorService.shutdown();
        while (!executorService.isTerminated()) {
        }
    }
    class TenRequestsTask implements Runnable {
        private String command;

        public TenRequestsTask(String s) {
            this.command = s;
        }

        @Override
        public void run() {
            //System.out.println(Thread.currentThread().getName() + " Start. Command = " + command);
            ExecutorService executorService = Executors.newSingleThreadExecutor();
            executorService.execute(new WriteTask(command));
            for (Integer i = 2; i < 10; i++) {
                executorService.execute(new AppendTask(command));
            }
            executorService.execute(new DeleteTask(command));
            executorService.shutdown();
            while (!executorService.isTerminated()) {
            }
            Assert.assertEquals(10, commandProcessor.getMessagesMap().get(command).get());
            //System.out.println(Thread.currentThread().getName() + " End.");
        }

        @Override
        public String toString() {
            return this.command;
        }
    }


    @Test
    public void fiveThreadTenJob() {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++) {
            Runnable worker = new WorkerThread(FOO + i);
            executor.execute(worker);
        }
        executor.shutdown();
        while (!executor.isTerminated()) {
        }
        //System.out.println("Finished all threads");
    }

    @Test
    public void deleteTestFiles() {

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new DeleteTask(FOO));
        executorService.execute(new DeleteTask(BAR));
        for (int i = 0; i < 10; i++) {
            Runnable worker = new DeleteTask(FOO + i);
            executorService.execute(worker);
        }
        executorService.shutdown();
        while (!executorService.isTerminated()) {
        }
    }

    class WorkerThread implements Runnable {
        private String command;

        public WorkerThread(String s) {
            this.command = s;
        }

        @Override
        public void run() {
            //System.out.println(Thread.currentThread().getName() + " Start. Command = " + command);
            ExecutorService executorService = Executors.newFixedThreadPool(100);
            executorService.execute(new WriteTask(command));
            for (Integer i = 2; i < TEN_THOUSAND; i++) {
                executorService.execute(new AppendTask(command));
            }
            executorService.execute(new DeleteTask(command));
            executorService.shutdown();
            while (!executorService.isTerminated()) {
            }
            Assert.assertEquals(TEN_THOUSAND.intValue(), commandProcessor.getMessagesMap().get(command).get());
            //System.out.println(Thread.currentThread().getName() + " End.");
        }

        @Override
        public String toString() {
            return this.command;
        }
    }

    class WriteTask implements Runnable {
        private String command;

        public WriteTask(String s) {
            this.command = s;
        }

        @Override
        public void run() {
            commandProcessor.processEmailCommand(command, "write " + command + ".txt", command + "created");
        }

        @Override
        public String toString() {
            return this.command;
        }
    }

    class AppendTask implements Runnable {
        private String command;

        public AppendTask(String s) {
            this.command = s;
        }

        @Override
        public void run() {
            commandProcessor.processEmailCommand(command, "append " + command + ".txt", command);
        }

        @Override
        public String toString() {
            return this.command;
        }
    }

    class DeleteTask implements Runnable {
        private String command;

        public DeleteTask(String s) {
            this.command = s;
        }

        @Override
        public void run() {
            commandProcessor.processEmailCommand(command, "delete " + command + ".txt", "deleting");
        }

        @Override
        public String toString() {
            return this.command;
        }
    }
}
