package com.company;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;
import javax.imageio.*;
import javax.swing.*;

/**
 * Created by Benjamin Clark on 13/01/2016.
 */
public abstract class Component
{
    //Unique Component ID Number
    int ComponentID;

    //Values for the resistance and power of the component
    double Resistance;
    double Power;

    //The component's graphical image
    BufferedImage Image;

    String FilePath = "INSERT PATH HERE";

    //ArrayLists showing which other components in the circuit that this component is connected to.
    ArrayList<Component> NegativeTerminal = new ArrayList<>();
    ArrayList<Component> PositiveTerminal = new ArrayList<>();

    public Component(int ComponentID, double Resistance, double Power)
    {
        this.ComponentID = ComponentID;
        this.Resistance = Resistance;
        this.Power = Power;

        try
        {
        Image = ImageIO.read(new File(FilePath));
        }
        catch (IOException e)
        {
        e.printStackTrace();
        }
    }

    //Connect the argument component to the argument terminal of the current component.
    public void ConnectComponent (Component comp, String terminal)
    {
        if (terminal.toLowerCase() == "positive")
        {
            PositiveTerminal.add(comp);
        }
        if (terminal.toLowerCase() == "negative")
        {
            NegativeTerminal.add(comp);
        }
    }

    //Change the resistance of the component.
    public void setResistance(double res)
    {
        this.Resistance = res;
    }

    //Change the power of the component.
    public void setPower(double pow)
    {
        this.Power = pow;
    }
}
