package com.chinarewards.gwt.elt.server.gift;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.slf4j.Logger;

import com.chinarewards.elt.model.common.PageStore;
import com.chinarewards.elt.model.common.PaginationDetail;
import com.chinarewards.elt.model.common.SortingDetail;
import com.chinarewards.elt.model.gift.search.GiftListVo;
import com.chinarewards.elt.model.gift.search.GiftStatus;
import com.chinarewards.elt.model.user.UserContext;
import com.chinarewards.elt.service.gift.GiftService;
import com.chinarewards.gwt.elt.adapter.gift.GiftAdapter;
import com.chinarewards.gwt.elt.client.gift.model.GiftCriteria;
import com.chinarewards.gwt.elt.client.gift.request.SearchGiftRequest;
import com.chinarewards.gwt.elt.client.gift.request.SearchGiftResponse;
import com.chinarewards.gwt.elt.server.BaseActionHandler;
import com.chinarewards.gwt.elt.server.logger.InjectLogger;
import com.chinarewards.gwt.elt.util.UserRoleTool;
import com.google.inject.Inject;

/**
 * @author nicho
 * @since 2012年1月10日 16:09:07
 */
public class SearchGiftHandler extends
		BaseActionHandler<SearchGiftRequest, SearchGiftResponse> {

	@InjectLogger
	Logger logger;

	GiftService giftService;

	@Inject
	public SearchGiftHandler(GiftService giftService) {
		this.giftService = giftService;

	}

	@Override
	public SearchGiftResponse execute(SearchGiftRequest request,
			ExecutionContext context) throws DispatchException {

		SearchGiftResponse resp = new SearchGiftResponse();

		GiftCriteria gift = request.getGift();
		GiftListVo criteria = adapter(gift);
		PageStore<GiftListVo> giftPage = null;

		UserContext uc = new UserContext();
		uc.setCorporationId(request.getCorporationId());
		uc.setUserRoles(UserRoleTool.adaptToRole(request.getUserRoles()));
		uc.setUserId(request.getUserId());

		giftPage = giftService.giftList(uc, criteria);
		resp.setTotal(giftPage.getResultCount());
		resp.setResult(GiftAdapter.adapter(giftPage.getResultList()));

		return resp;
	}

	private GiftListVo adapter(GiftCriteria criteria) {
		GiftListVo vo = new GiftListVo();
		if (criteria.getName() != null) {
			vo.setName(criteria.getName());
		}
		if (criteria.getStatus() != null) {
			vo.setStatus(GiftStatus.valueOf(criteria.getStatus().toString()));
		}
		if (criteria.getPagination() != null) {
			PaginationDetail detail = new PaginationDetail();
			detail.setLimit(criteria.getPagination().getLimit());
			detail.setStart(criteria.getPagination().getStart());

			vo.setPaginationDetail(detail);
		}

		if (criteria.getSorting() != null) {
			SortingDetail sortingDetail = new SortingDetail();
			sortingDetail.setSort(criteria.getSorting().getSort());
			sortingDetail.setDirection(criteria.getSorting().getDirection());
			vo.setSortingDetail(sortingDetail);
		}
		return vo;
	}

	@Override
	public Class<SearchGiftRequest> getActionType() {
		return SearchGiftRequest.class;
	}

	@Override
	public void rollback(SearchGiftRequest req, SearchGiftResponse resp,
			ExecutionContext cxt) throws DispatchException {
	}

}
