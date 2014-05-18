package co.uk.sanjay.ubstest.message;

/**
 * Created with IntelliJ IDEA.
 * User: sanusoft
 * Date: 15/05/2014
 * Time: 22:19
 * To change this template use File | Settings | File Templates.
 */
public class Message {

    final private String name;
    final private String content;
    private String command;
    private String filename;


    public Message(String name, String subject, String content) {
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
