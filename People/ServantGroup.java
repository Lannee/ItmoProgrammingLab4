package People;

import Classes.Messager;
import Interfaces.GetAwayFromChief;

public class ServantGroup extends Group<Servant> implements GetAwayFromChief {

    public ServantGroup(Servant[] participants, Person chief) {
        super(participants);
        for(Servant participant : getParticipants()) {
            participant.setChief(chief);
        }
    }

    @Override
    public void getAwayFromChief(Messager messager) {
        if(messager != null) messager.addMessage(this + "удрали от богачей. Теперь они сами себе хозяева.\n");
        for(Servant participant : getParticipants()) {
            participant.getAwayFromChief(null);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(Servant participant : getParticipants()) {
            sb.append(participant.servantSpecialization.getTranslation() + " ");
        }
        return sb.toString();
    }
}
