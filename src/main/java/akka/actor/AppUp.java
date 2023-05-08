package akka.actor;

import java.util.Scanner;

public class AppUp {

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("demo");
        ActorRef worker = system.actorOf(Props.create(WorkerUp.class), "worker");
        ActorRef callBack = system.actorOf(Props.create(Callback.class), "callback");

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String line = sc.nextLine();
            if ("exit".equals(line)) {
                return;
            }
            worker.tell(line, callBack); // (sender указывается не self) а обратная ссылка указывает на callBack
        }


    }

}
