package dev.aangepast.fmcquests.managers;

import java.util.ArrayList;
import java.util.List;

public class User {

    private List<Quest> quests = new ArrayList<>();
    private int orbsToReceive;
    private int xpToReceive;
    private int points;
    private int completed;

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

    private String uuid;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public int getOrbsToReceive() {
        return orbsToReceive;
    }

    public void setOrbsToReceive(int orbsToReceive) {
        this.orbsToReceive = orbsToReceive;
    }

    public int getXpToReceive() {
        return xpToReceive;
    }

    public void setXpToReceive(int xpToReceive) {
        this.xpToReceive = xpToReceive;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void addOrbs(int amount){
        orbsToReceive = orbsToReceive + amount;
    }

    public void addXp(int xp){
        xpToReceive = xpToReceive + xp;
    }

    public void addPoints(int amount){
        points = points + amount;
    }

    public int getCompleted() {
        return completed;
    }

    public void setCompleted(int completed) {
        this.completed = completed;
    }

    public void addCompleted(int amount){
        completed = completed + amount;
    }

}

