package co.uk.sanjay.ubstest.strategy;

import co.uk.sanjay.ubstest.message.Message;

import java.io.File;
import java.io.IOException;

/**
 * A Strategy to delete a file.
 */
/*public*/ class DeleteStrategy implements Strategy {
    @Override
    public void execute(Message message) throws IOException {

        String filename = message.getFilename();
        File f = new File(filename);
        if (!f.exists())
            throw new IllegalArgumentException("Delete: no such file exist: " + filename);

        if (!f.canWrite())
            throw new IllegalArgumentException("Delete: write protected: " + filename);

        if (!f.isFile()) {
            throw new IllegalArgumentException("Delete: It is a directory and cannot be delete : " + filename);
        }
        boolean success = f.delete();

        if (!success) {
            throw new IllegalArgumentException("Delete: deletion failed");
        }

    }
}
