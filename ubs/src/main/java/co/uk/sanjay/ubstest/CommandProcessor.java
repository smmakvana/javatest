package co.uk.sanjay.ubstest;

/**
 * An interface which accept the request from sender with subject and content details and execute appropriate action..
 */
public interface CommandProcessor {

    /**
     * @param from
     * @param subject
     * @param content
     * @throws IllegalArgumentException
     */
    public void processEmailCommand(String from, String subject, String content);
}
