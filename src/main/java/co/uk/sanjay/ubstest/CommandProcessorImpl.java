package co.uk.sanjay.ubstest;

import co.uk.sanjay.ubstest.message.Message;
import co.uk.sanjay.ubstest.strategy.Context;
import co.uk.sanjay.ubstest.strategy.ContextFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created with IntelliJ IDEA.
 * User: sanusoft
 * Date: 15/05/2014
 * Time: 22:14
 * To change this template use File | Settings | File Templates.
 */
public class CommandProcessorImpl implements CommandProcessor {

    public static final String ERR_MSG_FROM_IS_NULL = "from is null";
    public static final String ERR_MSG_SUBJECT_IS_NULL = "subject is null";
    public static final String ERR_MSG_CONTENT_IS_NULL = "content is null";
    public static final String ERR_MSG_FROM_IS_EMPTY = "from is empty";
    public static final String ERR_MSG_SUBJECT_IS_EMPTY = "subject is empty";
    public static final String ERR_MSG_CONTENT_IS_EMPTY = "content is empty";
    public static final String ERR_MSG_SUBJECT_MUST_HAVE_TWO_ARGUMENTS = "subject must have two arguments";

    private final Map<String, AtomicInteger> messagesMap;

    public CommandProcessorImpl() {
        messagesMap = new ConcurrentHashMap<String, AtomicInteger>(10);
    }

    @Override
    public void processEmailCommand(final String from, final String subject, final String content) {
        doInputValidation(from, subject, content);
        Message newMessage = new Message(from, subject, content);

        try {
            Context context = ContextFactory.getContext(newMessage.getCommand());
            context.executeStrategy(newMessage);
            collectSenderInfo(from);
        } catch (Exception e) {
           System.err.println("could not execute email command :" + e.getMessage());//
        }

    }

    /**
     * If input is invalid then it will throw IllegalArgumentException
     *
     */
    private void doInputValidation(String from, String subject, String content) {
        if (from == null) {
            throw new IllegalArgumentException(ERR_MSG_FROM_IS_NULL);
        } else if ("".equalsIgnoreCase(from.trim())) {
            throw new IllegalArgumentException(ERR_MSG_FROM_IS_EMPTY);
        } else if (subject == null) {
            throw new IllegalArgumentException(ERR_MSG_SUBJECT_IS_NULL);
        } else if ("".equalsIgnoreCase(subject.trim())) {
            throw new IllegalArgumentException(ERR_MSG_SUBJECT_IS_EMPTY);
        } else if (content == null) {
            throw new IllegalArgumentException(ERR_MSG_CONTENT_IS_NULL);
        } else if ("".equalsIgnoreCase(content.trim())) {
            throw new IllegalArgumentException(ERR_MSG_CONTENT_IS_EMPTY);
        }
        String tokens[] = subject.trim().split(" ");
        if (tokens.length != 2) {
            throw new IllegalArgumentException(ERR_MSG_SUBJECT_MUST_HAVE_TWO_ARGUMENTS);
        }
    }


    /**
     * A most critical  method in this class, it is thread safe and will will protect messagesMap
     * from many concurrent thread
     * @param sender
     */
    private void collectSenderInfo(String sender) {
        messagesMap.putIfAbsent(sender, new AtomicInteger(0));
        messagesMap.get(sender).incrementAndGet();
    }

    public Map<String, AtomicInteger> getMessagesMap() {
        return messagesMap;
    }

    /**
     * Printing information about number of unique client and call they have made!
     * @return
     */
    @Override
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("Number of senders are = ");
        stringBuffer.append(messagesMap.keySet().size());

        for (Map.Entry<String, AtomicInteger> sender:messagesMap.entrySet()) {
            stringBuffer.append("\n");
            stringBuffer.append(sender.getKey());
            stringBuffer.append(" = ");
            stringBuffer.append(sender.getValue());
        }
        return stringBuffer.toString();
    }


}
