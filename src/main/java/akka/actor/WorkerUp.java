package akka.actor;

import akka.actor.ActorRef;
import akka.actor.UntypedAbstractActor;

public class WorkerUp extends UntypedAbstractActor {

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof String) {
            String response = ((String) message).toUpperCase();
            ActorRef sender = getSender();
            sender.tell(response, getSelf());
        } else  {
            unhandled(message);
        }
    }
}
