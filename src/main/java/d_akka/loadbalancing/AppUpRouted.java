package d_akka.loadbalancing;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.routing.SmallestMailboxPool;

import java.util.Scanner;

public class AppUpRouted {

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("demo");
        ActorRef worker = system.actorOf(new SmallestMailboxPool(5).props(Props.create(WorkerUpRouted.class)), "worker");
        ActorRef callBack = system.actorOf(Props.create(CallbackRouted.class), "callback");

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
