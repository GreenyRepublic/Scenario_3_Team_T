package application;

import java.io.File;
import java.util.ArrayList;

import javafx.scene.shape.Line;

public class Components
{
	ArrayList<String> list=new ArrayList<String>();
	ArrayList<Double> xPos=new ArrayList<Double>();
	ArrayList<Double> yPos=new ArrayList<Double>();
	public ArrayList<Line> createWires()
	{
		ArrayList<Line> lines=new ArrayList<Line>();
		for(int i=75;i<=475;i+=100)
		{
			Line line = new Line();
            line.setStartX(220);
            line.setEndX(820);
            line.setStartY(i);
            line.setEndY(i);
            lines.add(line);
		}

			Line line = new Line();
            line.setStartX(220);
            line.setEndX(220);
            line.setStartY(75);
            line.setEndY(475);
            lines.add(line);

            Line line2 = new Line();
            line2.setStartX(820);
            line2.setEndX(820);
            line2.setStartY(75);
            line2.setEndY(475);
            lines.add(line2);

		return lines;
	}

}
