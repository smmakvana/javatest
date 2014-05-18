package co.uk.sanjay.ubstest.strategy;

import co.uk.sanjay.ubstest.message.Message;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * A Strategy to append the content.
 */
/*public*/ class AppendStrategy implements Strategy {
    @Override
    public void execute(Message message) throws IOException{
        PrintWriter out = null;
        try {
            out = new PrintWriter(new BufferedWriter(new FileWriter(message.getFilename(), true)));
            out.println(message.getContent());
        } finally {
            if (out != null) {
                out.close();

            }
        }
    }
}
