package co.uk.sanjay.ubstest.strategy;

/**
 * List of email strategies can be used while processing the request frrom email..
 */
public enum Strategies {
    /**
     * Write string content in new file.
     */
    WRITE,

    /**
     * Append string content into existing file.
     */
    APPEND,

    /**
     * Delete file.
     */
    DELETE
}
