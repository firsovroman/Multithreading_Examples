package a_primitives.c_wait_notify.two;

public class MailServer implements Runnable {
    private Mail mail;

    public MailServer(Mail mail) {
        this.mail = mail;
    }

    @Override
    public void run() {
        long startTime = System.currentTimeMillis();

        synchronized (mail) {

            try {
                mail.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        //сделайте что-то тут - do something here

        String name = Thread.currentThread().getName();
        long endTime = System.currentTimeMillis();
        System.out.format("%s MailServer received: [%s] in %d ms after start", name, mail.getText(), (endTime - startTime));
    }
}
