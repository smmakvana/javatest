package co.uk.sanjay.ubstest.strategy;

import co.uk.sanjay.ubstest.message.Message;

import java.io.IOException;

/**
 * A Strategy to handle all unknown commands.
 */
/*public*/ class UnknownStrategy implements Strategy {
    @Override
    public void execute(Message message) throws IOException{
        System.err.println("Could not process this message due to unknown command : " + message.toString());
    }
}
