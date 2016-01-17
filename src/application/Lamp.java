package com.company;

/**
 * Created by benfc on 13/01/2016.
 */
public class Lamp extends Component
{
    private String State = "ON";

    public Lamp(int ComponentID)
    {
        super(ComponentID, 0, 0);
        this.FilePath = "INSERT PATH HERE";
    }

    public void ToggleState()
    {
        this.State = (State == "ON") ? "OFF" : "ON";
    }

    public String GetState()
    {
        return this.State;
    }
}