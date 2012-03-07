package com.chinarewards.gwt.elt.adapter.order;

import java.util.ArrayList;
import java.util.List;

import com.chinarewards.elt.model.gift.search.GiftListVo;
import com.chinarewards.elt.model.order.search.OrderListVo;
import com.chinarewards.gwt.elt.client.gift.model.GiftClient;
import com.chinarewards.gwt.elt.client.gift.model.GiftCriteria.GiftStatus;
import com.chinarewards.gwt.elt.client.order.model.OrderSearchVo;
import com.chinarewards.gwt.elt.client.order.model.OrderStatus;


/**
 * This utility class use to adapter EJB entity to WAR domain.
 * 
 * @author lw
 */
public class OrderAdapter {

	//从服务端得到的数据到客户端在列表显示的数据
	public static List<OrderSearchVo> adapterToClient(List<OrderListVo> service) {
		List<OrderSearchVo> resultList = new ArrayList<OrderSearchVo>();

		for (OrderListVo item : service) {
			OrderSearchVo client = new OrderSearchVo();
			client.setId(item.getId());
			client.setName(item.getName());
			client.setAmount(item.getAmount());
			if (item.getStatus() != null) {
				client.setStatus(OrderStatus.valueOf(item.getStatus().toString()));
			}
			
			client.setIntegral(item.getIntegral());
			client.setOrderCode(item.getOrderCode());
			client.setRecorddate(item.getRecorddate());
			client.setExchangeDate(item.getExchangeDate());
			client.setGiftvo(adapter(item.getGiftvo()));
			resultList.add(client);
		}

		return resultList;
	}
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

}
