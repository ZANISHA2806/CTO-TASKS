package repository;

import model.Card;
import sample.SampleData;
public class CardRepository {

	public static Card getById(String cardId) {
		
	       return SampleData.getCard(cardId);
	}

}
