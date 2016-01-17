package com.company;

/**
 * Created by Benjamin Clark on 13/01/2016.
 */
public class Battery extends Component
{
    public Battery(int ComponentID, String FilePath, double Resistance, double Power)
    {
        super(ComponentID, Resistance, Power);
        this.FilePath = "INSERT PATH HERE";
    }
}
