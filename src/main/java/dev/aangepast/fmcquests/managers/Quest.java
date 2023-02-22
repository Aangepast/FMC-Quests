package dev.aangepast.fmcquests.managers;

public class Quest {

    private String name;
    private int amount;
    private String rawName;
    private boolean weekly;
    private boolean daily;
    private boolean lifetime;
    private boolean personal;
    private boolean kingdom;
    private int rewardOrbs;
    private int rewardXp;
    private int rewardPoints;
    private String description;
    private int progress;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getRawName() {
        return rawName;
    }

    public void setRawName(String rawName) {
        this.rawName = rawName;
    }

    public boolean isWeekly() {
        return weekly;
    }

    public void setWeekly(boolean weekly) {
        this.weekly = weekly;
    }

    public boolean isDaily() {
        return daily;
    }

    public void setDaily(boolean daily) {
        this.daily = daily;
    }

    public boolean isLifetime() {
        return lifetime;
    }

    public void setLifetime(boolean lifetime) {
        this.lifetime = lifetime;
    }

    public boolean isPersonal() {
        return personal;
    }

    public void setPersonal(boolean personal) {
        this.personal = personal;
    }

    public boolean isKingdom() {
        return kingdom;
    }

    public void setKingdom(boolean kingdom) {
        this.kingdom = kingdom;
    }

    public int getRewardOrbs() {
        return rewardOrbs;
    }

    public void setRewardOrbs(int rewardOrbs) {
        this.rewardOrbs = rewardOrbs;
    }

    public int getRewardXp() {
        return rewardXp;
    }

    public void setRewardXp(int rewardXp) {
        this.rewardXp = rewardXp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRewardPoints() {
        return rewardPoints;
    }

    public void setRewardPoints(int rewardPoints) {
        this.rewardPoints = rewardPoints;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }
}
