package d_akka.loadbalancing;

import akka.actor.ActorRef;
import akka.actor.UntypedAbstractActor;

public class WorkerUpRouted extends UntypedAbstractActor {

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof String) {
            String response = ((String) message).toUpperCase();
            ActorRef sender = getSender();
            sender.tell(response, getSelf());
            while (true); // вешаем поток
        } else  {
            unhandled(message);
        }
    }
}
