package co.uk.sanjay.ubstest.strategy;

import co.uk.sanjay.ubstest.message.Message;

/**
 * Created with IntelliJ IDEA.
 * User: sanusoft
 * Date: 18/05/2014
 * Time: 00:03
 * To change this template use File | Settings | File Templates.
 */
/*public*/ class UnknownStrategy implements Strategy {
    @Override
    public void execute(Message message) {
        System.out.println("Unknown command! Message cannot be process " + message.toString());
    }
}
