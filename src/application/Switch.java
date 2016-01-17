package com.company;

/**
 * Created by benfc on 13/01/2016.
 */
public class Switch extends Component
{
    private String State = "OPEN";

    public Switch(int ComponentID)
    {
        super(ComponentID, 0, 0);
        this.FilePath = "INSERT PATH HERE";
    }

    public void ToggleState()
    {
        this.State = (State == "OPEN") ? "CLOSED" : "OPEN";
    }

    public String GetState()
    {
        return this.State;
    }
}