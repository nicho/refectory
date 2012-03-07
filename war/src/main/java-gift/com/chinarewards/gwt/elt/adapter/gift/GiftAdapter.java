package com.chinarewards.gwt.elt.adapter.gift;

import java.util.ArrayList;
import java.util.List;
import com.chinarewards.elt.model.gift.search.GiftListVo;
import com.chinarewards.gwt.elt.client.gift.model.GiftClient;
import com.chinarewards.gwt.elt.client.gift.model.GiftCriteria.GiftStatus;

/**
 * This utility class use to adapter EJB entity to WAR domain.
 * 
 * @author nicho
 * @since 0.2.0
 */
public class GiftAdapter {

	public static GiftClient adapter(GiftListVo gift) {
		if (null == gift) {
			return null;
		}

		GiftClient result = new GiftClient();

		result.setId(gift.getId());
		result.setName(gift.getName());
		result.setSource(gift.getSource());
		result.setInventory(gift.getStock() + "");
		result.setIntegral(gift.getIntegral());
		result.setIndate(gift.getIndate());
		result.setPhoto(gift.getPhoto());

		if (gift.getStatus() != null) {
			result.setStatus(GiftStatus.valueOf(gift.getStatus().toString()));
		}
		return result;
	}

	public static List<GiftClient> adapter(List<GiftListVo> giftList) {
		if (null == giftList) {
			return null;
		}

		List<GiftClient> resultList = new ArrayList<GiftClient>();
		for (GiftListVo gift : giftList) {
			resultList.add(adapter(gift));
		}
		return resultList;
	}
	
}
