package co.uk.sanjay.ubstest.strategy;

import co.uk.sanjay.ubstest.message.Message;

/**
 * Created with IntelliJ IDEA.
 * User: sanusoft
 * Date: 17/05/2014
 * Time: 23:03
 * To change this template use File | Settings | File Templates.
 */
public interface Strategy {

    /**
     * A method will be called when strategy needs to be execute.
     */
    public void execute(Message message);
}
