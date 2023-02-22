package dev.aangepast.fmcquests.managers;

import java.util.ArrayList;
import java.util.List;

public class User {

    private List<Quest> quests = new ArrayList<>();

    public List<Quest> getQuests() {
        return quests;
    }

    public void setQuests(List<Quest> quests) {
        this.quests = quests;
    }

    public void addQuest(Quest quest){
        quests.add(quest);
    }

    public void removeQuest(Quest quest){
        quests.remove(quest);
    }

}

