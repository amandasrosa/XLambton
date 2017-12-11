package com.aa.xlambton.Model;

/**
 * Created by Amanda on 11/12/2017.
 */

public class AgentMission {
    private int id;
    private Agent agent;
    private Mission mission;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public Mission getMission() {
        return mission;
    }

    public void setMission(Mission mission) {
        this.mission = mission;
    }
}
