package nimrod.cinema.objects;

import nimrod.cinema.Managers.ScreeningsManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Row implements Serializable
{
	public ArrayList<Integer> Seats;

	public Row()
	{

	}

	public Row(int seats) {
		Seats = new ArrayList<>(Collections.nCopies(seats, ScreeningsManager.SeatState.Free.getValue()));
	}

	public Row(ArrayList<Integer> seats)
	{
		Seats = seats;
	}
}
