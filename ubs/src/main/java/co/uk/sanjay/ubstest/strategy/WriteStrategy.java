package co.uk.sanjay.ubstest.strategy;

import co.uk.sanjay.ubstest.message.Message;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 * Created with IntelliJ IDEA.
 * User: sanusoft
 * Date: 17/05/2014
 * Time: 23:40
 * To change this template use File | Settings | File Templates.
 */
/*public*/ class WriteStrategy implements Strategy {
    @Override
    public void execute(Message message) {

        PrintWriter out = null;
        try {

            out = new PrintWriter(new BufferedWriter(new FileWriter(message.getFilename(), true)));
            out.println(message.getContent());
        } catch (Exception ex) {
            ex.printStackTrace();//todo log error
        } finally {
            if (out != null) {
                out.close();

            }
        }
    }
}
