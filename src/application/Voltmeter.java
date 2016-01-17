package com.company;

/**
 * Created by benfc on 13/01/2016.
 */
public class Voltmeter extends Component
{
    private double Reading;

    public Voltmeter(int ComponentID)
    {
        super(ComponentID, 10000, 0);
        this.FilePath = "INSERT PATH HERE";
    }

    public void SetReading(double reading)
    {
        Reading = reading;
    }

    public double GetReading()
    {
        return Reading;
    }
}
