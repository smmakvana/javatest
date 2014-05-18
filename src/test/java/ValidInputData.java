import co.uk.sanjay.ubstest.CommandProcessorImpl;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: sanusoft
 * Date: 18/05/2014
 * Time: 01:54
 * To change this template use File | Settings | File Templates.
 */
public class ValidInputData {

    private static final String BAR = "bar@ubs.co.uk";
    private static final String FOO = "foo@ubs.co.uk";

    public CommandProcessorImpl commandProcessor;

    @Before
    public void init() {
        commandProcessor = new CommandProcessorImpl();
    }


    @After
    public void clean() {
        commandProcessor = null;
    }

    @Test
    public void noSender() {
        Assert.assertEquals(0, commandProcessor.getMessagesMap().keySet().size());
        Assert.assertNull(commandProcessor.getMessagesMap().get(FOO));
    }

    @Test
    public void oneSenderOneCommand() {
        commandProcessor.processEmailCommand(FOO, "write foo.txt", "It is foo");
        Assert.assertEquals(1, commandProcessor.getMessagesMap().keySet().size());
        Assert.assertEquals(1, commandProcessor.getMessagesMap().get(FOO).get());
    }

    @Test
    public void oneSenderTwoCommand() {
        commandProcessor.processEmailCommand(FOO, "write foo.txt", "It is foo");
        commandProcessor.processEmailCommand(FOO, "append foo.txt", "It is foo");
        Assert.assertEquals(1, commandProcessor.getMessagesMap().keySet().size());
        Assert.assertEquals(2, commandProcessor.getMessagesMap().get(FOO).get());
    }

    @Test
    public void twoSenderOneCommand() {
        commandProcessor.processEmailCommand(FOO, "write foo.txt", "It is foo");
        commandProcessor.processEmailCommand(BAR, "write bar.txt", "It is bar");
        Assert.assertEquals(2, commandProcessor.getMessagesMap().keySet().size());
        Assert.assertEquals(1, commandProcessor.getMessagesMap().get(FOO).get());
        Assert.assertEquals(1, commandProcessor.getMessagesMap().get(BAR).get());
    }

    @Test
    public void twoSenderTwoCommand() {
        commandProcessor.processEmailCommand(FOO, "write foo.txt", "It is foo");
        commandProcessor.processEmailCommand(FOO, "append foo.txt", "It is foo");
        commandProcessor.processEmailCommand(BAR, "write bar.txt", "It is bar");
        Assert.assertEquals(2, commandProcessor.getMessagesMap().keySet().size());
        Assert.assertEquals(2, commandProcessor.getMessagesMap().get(FOO).get());
        Assert.assertEquals(1, commandProcessor.getMessagesMap().get(BAR).get());

    }


    @Test
    public void deleteFoo() {
        commandProcessor.processEmailCommand(FOO, "write foo.txt", "It is foo creation");
        commandProcessor.processEmailCommand(FOO, "update foo.txt", "It is foo updating");
        commandProcessor.processEmailCommand(FOO, "append foo.txt", "It is foo appending");
        commandProcessor.processEmailCommand(FOO, "delete foo.txt", "It is foo deleting and garbage");
        Assert.assertEquals(1, commandProcessor.getMessagesMap().keySet().size());
        Assert.assertEquals(4, commandProcessor.getMessagesMap().get(FOO).get());
    }

    @Test
    public void deleteBar() {
        commandProcessor.processEmailCommand(BAR, "write bar.txt", "It is bar creation");
        commandProcessor.processEmailCommand(BAR, "update bar.txt", "It is bar updating");
        commandProcessor.processEmailCommand(BAR, "append bar.txt", "It is foo appending");
        commandProcessor.processEmailCommand(BAR, "delete bar.txt", "It is foo deleting and garbage");
        Assert.assertEquals(1, commandProcessor.getMessagesMap().keySet().size());
        Assert.assertEquals(4, commandProcessor.getMessagesMap().get(BAR).get());
    }


}
