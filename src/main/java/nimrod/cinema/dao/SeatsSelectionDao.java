//package nimrodpasha.cinema.dao;
//
//import nimrod.cinema.objects.SeatsSelection;
//import nimrod.cinema.utils.Constants;
//import org.bson.Document;
//import org.bson.types.ObjectId;
//import org.joda.time.LocalDateTime;
//import org.joda.time.format.DateTimeFormatter;
//import org.joda.time.format.ISODateTimeFormat;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static com.mongodb.client.model.Filters.eq;
//import static com.mongodb.client.model.Projections.*;
//
//public class SeatsSelectionDao implements Crud, UsageCheck
//{
//	private com.mongodb.client.MongoCollection<Document> _collection;
//	private static final DateTimeFormatter DATE_TIME_FORMATTER = ISODateTimeFormat.dateTimeNoMillis();
//
//	public com.mongodb.client.MongoCollection<Document> getCollection()
//	{
//		return _collection;
//	}
//
//	/**
//	 * Ctor
//	 */
//	public SeatsSelectionDao()
//	{
//		this._collection = nimrod.cinema.dao.MongoCollection.getMongoCollection(Constants.DB.SEATS_SELECTION_COLLECTION);
//	}
//
//	/**
//	 * @param obj seat selection
//	 * @return created item Id
//	 */
//	@Override
//	public String create(Object obj)
//	{
//		SeatsSelection seatsSelection = (SeatsSelection) obj;
//
//		String generatedId = ObjectId.get().toString();
//
//		Document doc = new Document(Constants.SeatsSelection.ID, generatedId)
//				.append(Constants.SeatsSelection.SELECTION_TIME, DATE_TIME_FORMATTER.print(LocalDateTime.now()))
//				.append(Constants.SeatsSelection.SEATS, seatsSelection.Seats)
//				.append(Constants.SeatsSelection.SCREENING_ID, seatsSelection.ScreeningId);
//		try
//		{
//			_collection.insertOne(doc);
//		}
//		catch (Exception e)
//		{
//			e.printStackTrace();
//			return "";
//		}
//
//		return generatedId;
//	}
//
//	/**
//	 * get a single document from the DB
//	 *
//	 * @param id of the DB object
//	 * @return single document
//	 */
//	@Override
//	public Document read(String id)
//	{
//		return _collection.find(eq(Constants.SeatsSelection.ID, id)).projection(fields(excludeId())).first();
//	}
//
//	@Override
//	public Document readField(String id, String fieldKey)
//	{
//		return null;
//	}
//
//	/**
//	 * @return all documents in this collection in a arrayList
//	 */
//	@Override
//	public List<Document> readAll()
//	{
//
//		return _collection.find().into(new ArrayList<>());
//	}
//
//	/**
//	 * @param document contain fields to update
//	 * @return true if update was successful ,false otherwise
//	 */
//	@Override
//	public boolean update(Document document)
//	{
//		return false;
//	}
//
//	@Override
//	public boolean UpdateField(String id, String fieldKey, Document document)
//	{
//		return false;
//	}
//
//	/**
//	 * @param id of an object to delete
//	 * @return true if deletion was successful ,false otherwise
//	 */
//	@Override
//	public Document delete(String id)
//	{
//		try
//		{
//			Document doc = _collection.findOneAndDelete(eq(Constants.SeatsSelection.ID, id));
//
//			return doc;
//		}
//		catch (Exception e)
//		{
//			e.printStackTrace();
//			return null;
//		}
//	}
//
//	/**
//	 * @return false
//	 */
//	@Override
//	public boolean deleteAll()
//	{
//		try
//		{
//			_collection.drop();
//		} catch (Exception e)
//		{
//			e.printStackTrace();
//			return false;
//		}
//		return true;
//	}
//
//	@Override
//	public String insertValidation(Document document)
//	{
//		return null;
//	}
//
//	@Override
//	public boolean isInUse(String id)
//	{
//		return false;
//	}
//
//	public Document GetScreeningSeats(String screeningsId)
//	{
//		return _collection.find(eq(Constants.Screening.ID, screeningsId)).projection(fields(include(Constants.Screening.SEATS))).first();
//	}
//}