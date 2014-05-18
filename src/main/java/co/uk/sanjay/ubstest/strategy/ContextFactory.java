package co.uk.sanjay.ubstest.strategy;

/**
 * Created with IntelliJ IDEA.
 * User: sanusoft
 * Date: 18/05/2014
 * Time: 00:01
 * To change this template use File | Settings | File Templates.
 */
public class ContextFactory {

    public static Context getContext(String command) {
        Context context;
        try {
            Strategies strategies = Strategies.valueOf(command.toUpperCase());
            switch (strategies) {
                case WRITE:
                    context = new Context(new WriteStrategy());
                    break;
                case APPEND:
                    context = new Context(new AppendStrategy());
                    break;
                case DELETE:
                    context = new Context(new DeleteStrategy());
                    break;
                default:
                    context = new Context(new UnknownStrategy());

            }
        } catch (IllegalArgumentException e) {
            context = new Context(new UnknownStrategy());

        }
        return context;
    }

}
