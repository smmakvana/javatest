import co.uk.sanjay.ubstest.CommandProcessor;
import co.uk.sanjay.ubstest.CommandProcessorImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Created with IntelliJ IDEA.
 * User: sanusoft
 * Date: 18/05/2014
 * Time: 01:22
 * To change this template use File | Settings | File Templates.
 */
public class InvalidInputDataTest {

    private static final String FOO = "foo@ubs.co.uk";

    public CommandProcessor commandProcessor;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void init() {
        commandProcessor = new CommandProcessorImpl();

    }


    @After
    public void clean() {
        commandProcessor = null;
    }


    @Test
    public void nullFrom() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("from is null");
        commandProcessor.processEmailCommand(null, "write foo.txt", "It is foo");
    }

    @Test
    public void emptyFrom() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("from is empty");
        commandProcessor.processEmailCommand("", "write foo.txt", "It is foo");
    }

    @Test
    public void nullSubject() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("subject is null");
        commandProcessor.processEmailCommand(FOO, null, "It is foo");
    }

    @Test
    public void emptySubject() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("subject is empty");
        commandProcessor.processEmailCommand(FOO, "", "It is foo");
    }

    @Test
    public void nullContent() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("content is null");
        commandProcessor.processEmailCommand(FOO, "write foo.txt", null);
    }

    @Test
    public void emptyContent() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("content is empty");
        commandProcessor.processEmailCommand(FOO, "write foo.txt", "");
    }


    @Test
    public void onlyOneWordSubject() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("subject must have two arguments");
        commandProcessor.processEmailCommand(FOO, "xxxx", "It is foo");
    }

    @Test
    public void onlyOneWordSubjectWithTrim() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("subject must have two arguments");
        commandProcessor.processEmailCommand(FOO, " xxxx ", "It is foo");
    }

    @Test
    public void subjectWithThreeWord() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("subject must have two arguments");
        commandProcessor.processEmailCommand(FOO, "A b c", "It is foo");
    }


}
