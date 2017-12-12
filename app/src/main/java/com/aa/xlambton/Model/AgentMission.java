package com.aa.xlambton.Model;

/**
 * Created by Amanda on 11/12/2017.
 */

public class AgentMission {
    private Long id;
    private Agent agent;
    private Mission mission;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
