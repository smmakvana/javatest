package co.uk.sanjay.ubstest.message;

/**
 * Message object which created by wrapping the information received from service interface and
 * will be used as message to be process.
 */
public class Message {

    final private String name;
    final private String content;
    private String command;
    private String filename;


    public Message(final String name, final String subject, final String content) {
        this.name = name;
        this.content = content;
        if (subject != null) {
            String subjectInfo[] = subject.trim().split(" ");
            if (subjectInfo.length == 2) {
                this.command = subjectInfo[0].trim();
                this.filename = subjectInfo[1].trim();
            }

        }
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }

    public String getCommand() {
        return command;
    }

    public String getFilename() {
        return filename;
    }

    @Override
    public String toString() {
        return "Message{" +
                "name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", command='" + command + '\'' +
                ", filename='" + filename + '\'' +
                '}';
    }

}
