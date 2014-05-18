package co.uk.sanjay.ubstest.strategy;

import co.uk.sanjay.ubstest.message.Message;

import java.io.IOException;

/**
 * Context object which will hold the message processing strategy.
 */
final public class Context {
    final private Strategy strategy;

    public Context(final Strategy strategy) {
        this.strategy = strategy;
    }

    public void executeStrategy(final Message message) throws IOException {
        this.strategy.execute(message);
    }
}