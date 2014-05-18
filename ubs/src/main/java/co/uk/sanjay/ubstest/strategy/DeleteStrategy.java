package co.uk.sanjay.ubstest.strategy;

import co.uk.sanjay.ubstest.message.Message;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: sanusoft
 * Date: 17/05/2014
 * Time: 23:44
 * To change this template use File | Settings | File Templates.
 */
/*public*/ class DeleteStrategy implements Strategy {
    @Override
    public void execute(Message message) {

        String filename = message.getFilename();
        File f = new File(filename);
        if (!f.exists())
            throw new IllegalArgumentException(
                    "Delete: no such file exist: " + filename);

        if (!f.canWrite())
            throw new IllegalArgumentException("Delete: write protected: " + filename);

        if (!f.isFile()) {
            throw new IllegalArgumentException(
                    "Delete: It is a directory and cannot be delete : " + filename);
        }

        // Attempt to delete it
        boolean success = f.delete();

        if (!success) {
            throw new IllegalArgumentException("Delete: deletion failed");
        }

    }
}
