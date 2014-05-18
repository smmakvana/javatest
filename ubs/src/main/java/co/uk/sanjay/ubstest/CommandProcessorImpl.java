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
        isInputDataValid(from, subject, content);


        Message newMessage = new Message(from, subject, content);

        try {
            Context context = ContextFactory.getContext(newMessage.getCommand());
            context.executeStrategy(newMessage);
            collectSenderInfo(from);

        } catch (Exception e) {
            //todo
        }


    }

    private boolean isInputDataValid(String from, String subject, String content) {
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

        return true;
    }


    private void collectSenderInfo(String sender) {
        messagesMap.putIfAbsent(sender, new AtomicInteger(0));
        messagesMap.get(sender).incrementAndGet();
    }

    public Map<String, AtomicInteger> getMessagesMap() {
        return messagesMap;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Number of senders are = ");
        stringBuilder.append(messagesMap.keySet().size());
        for (String key : messagesMap.keySet()) {
            stringBuilder.append("\n");
            stringBuilder.append(key);
            stringBuilder.append(" = ");
            stringBuilder.append(messagesMap.get(key));
        }
        return stringBuilder.toString();
    }


}
