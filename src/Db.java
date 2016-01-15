package application;


import java.sql.*;
import java.io.*;
import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

public class Db
{
	public ArrayList<String> imagesURL = new ArrayList<String>();
	public ArrayList<Double> xPos = new ArrayList<Double>();
	public ArrayList<Double> yPos = new ArrayList<Double>();
	Connection connection = null;
    String connectionURL = "jdbc:mysql://localhost:3306/test2";
    ResultSet set = null;
    PreparedStatement pst = null;
    FileInputStream input;
    Components components=new Components();
    public void saveToDb(String projectName)
    {
    	for(String s:components.list)
    		imagesURL.add(s);
    	for(double x:components.xPos)
    		xPos.add(x);
    	for(double y:components.yPos)
    		yPos.add(y);
    	try
    	{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection(connectionURL, "root", "deanisanidiot");
			Statement st = connection.createStatement();
			String query = "CREATE TABLE " + projectName +
	                   " (id INTEGER not NULL AUTO_INCREMENT, " +
	                   " image MEDIUMBLOB, " +
	                   " xPOS DOUBLE, " +
	                   " yPOS DOUBLE, " +
	                   " PRIMARY KEY ( id ))";
			st.executeUpdate(query);
			int i;
			for(i=0;i<imagesURL.size();i++)
			{
				pst = connection.prepareStatement("insert into "+projectName+"(id, image, xPOS, yPOS) "+"values(?,?,?,?)");
				File image = new File(imagesURL.get(i));
				input = new FileInputStream(image);
				pst.setInt(1, i+1);
				pst.setBinaryStream(2, (InputStream)input, (int)(image.length()));
				pst.setDouble(3, xPos.get(i));
				pst.setDouble(4, yPos.get(i));
				pst.executeUpdate();
			}
		}
    	catch (Exception e1)
    	{
			// TODO Auto-generated catch block
			e1.printStackTrace();
        }
    }
    public void loadFromDb(String projectName, BorderPane layout1)
    {
    	try
    	{
    	    String query = "SELECT * FROM "+projectName;
    	    connection = DriverManager.getConnection(connectionURL, "root", "deanisanidiot");
            pst = connection.prepareStatement(query);
            set = pst.executeQuery();
            int i=1;
            while(set.next())
            {
            	Blob blob = set.getBlob(2);
            	double x = set.getDouble(3);
            	double y = set.getDouble(4);
            	String location=""+i+".png";
            	FileOutputStream output = new FileOutputStream(location);
            	int length = (int) blob.length();
            	byte[] buffer = blob.getBytes(1, length);
            	output.write(buffer,0,length);
            	Image image = new Image(location);
            	ImageView img = new ImageView();
            	img.setX(x);
            	img.setY(y);
            	img.setImage(image);
            	layout1.getChildren().add(img);
            	i++;
            }
        }
    	catch (Exception e1)
    	{
			// TODO Auto-generated catch block
			e1.printStackTrace();
        }
    }
    public void deleteFromDb(String projectName)
    {
    	try
    	{
    		connection = DriverManager.getConnection(connectionURL, "root", "deanisanidiot");
    	    Statement st = connection.createStatement();
    	    String query = "DROP TABLE "+projectName;
    	    st.executeUpdate(query);
    	}
    	catch(Exception e1)
    	{
    		// TODO Auto-generated catch block
    		e1.printStackTrace();
    	}
    }
    public ArrayList<String> getTables()
    {
    	ArrayList<String> list = new ArrayList<String>();
    	try
    	{
    	    connection = DriverManager.getConnection(connectionURL, "root", "deanisanidiot");
            DatabaseMetaData data = connection.getMetaData();
            set = data.getTables(null, null, "%", null);
            while (set.next())
            {
                list.add(set.getString(3));
            }
        }
    	catch (Exception e1)
    	{
			// TODO Auto-generated catch block
			e1.printStackTrace();
        }
    	return list;
    }
}