import java.util.ArrayList;
import java.util.List;

public class MailBox {
    private final List<MailInfo> infos;
    private final MailSender mailSender;

    public MailBox() {
        this.infos = new ArrayList<>();
        this.mailSender = new MailSender();
    }

    public void addMailInfo(MailInfo mailInfo) {
        infos.add(mailInfo);
    }

    public void sendAll() {
        System.out.println("Starting to send " + infos.size() + " email(s)...\n");
        for (MailInfo info : infos) {
            mailSender.sendMail(info);
        }
        System.out.println("All emails sent successfully!");
        infos.clear();
    }
}
