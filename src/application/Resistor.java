package com.company;

/**
 * Created by Benjamin Clark on 13/01/2016.
 */
public class Resistor extends Component
{
    public Resistor(int ComponentID, double Resistance)
    {
        super(ComponentID, Resistance, 0);
        this.FilePath = "INSERT PATH HERE";
    }

    public void SetResistance(double res)
    {
        Resistance = res;
    }

    public double GetResistance(double res)
    {
        return Resistance;
    }
}
