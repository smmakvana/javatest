package co.uk.sanjay.ubstest;

/**
 * Created with IntelliJ IDEA.
 * User: sanusoft
 * Date: 15/05/2014
 * Time: 22:12
 * To change this template use File | Settings | File Templates.
 */
public interface CommandProcessor {

    public void processEmailCommand(String from, String subject, String content) throws IllegalArgumentException;
}
