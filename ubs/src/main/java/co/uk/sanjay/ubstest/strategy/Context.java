package co.uk.sanjay.ubstest.strategy;

import co.uk.sanjay.ubstest.message.Message;

/**
 * Created with IntelliJ IDEA.
 * User: sanusoft
 * Date: 17/05/2014
 * Time: 23:51
 * To change this template use File | Settings | File Templates.
 */
public class Context {
    private Strategy strategy;

    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    public void executeStrategy(Message message) {
        this.strategy.execute(message);
    }
}