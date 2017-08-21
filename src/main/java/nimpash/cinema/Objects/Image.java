package nimpash.cinema.Objects;

import org.bson.BsonBinary;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity("Image")
public class Image
{
	@Id
	String Name;
	BsonBinary Data;

	public Image()
	{

	}

	public Image(String name, byte[] imageData)
	{
		Name = name;
		Data = new BsonBinary(imageData);
	}
}
