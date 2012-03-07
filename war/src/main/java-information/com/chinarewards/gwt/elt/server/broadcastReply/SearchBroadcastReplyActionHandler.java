package com.chinarewards.gwt.elt.server.broadcastReply;

import java.util.ArrayList;
import java.util.List;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.slf4j.Logger;

import com.chinarewards.elt.domain.information.BroadcastReply;
import com.chinarewards.elt.model.broadcastReply.BroadcastReplyListCriteria;
import com.chinarewards.elt.model.common.PageStore;
import com.chinarewards.elt.model.common.PaginationDetail;
import com.chinarewards.elt.model.common.SortingDetail;
import com.chinarewards.elt.model.user.UserContext;
import com.chinarewards.elt.service.broadcast.BroadcastService;
import com.chinarewards.gwt.elt.client.broadcastReply.model.ReplyListClient;
import com.chinarewards.gwt.elt.client.broadcastReply.request.SearchBroadcastReplyRequest;
import com.chinarewards.gwt.elt.client.broadcastReply.request.SearchBroadcastReplyResponse;
import com.chinarewards.gwt.elt.server.BaseActionHandler;
import com.chinarewards.gwt.elt.server.logger.InjectLogger;
import com.chinarewards.gwt.elt.util.UserRoleTool;
import com.google.inject.Inject;

/**
 * The handler to correspond the SearchBroadcastReplyRequest.
 * 
 * @author nicho
 * @since 2011年12月7日 09:41:42
 */
public class SearchBroadcastReplyActionHandler
		extends
		BaseActionHandler<SearchBroadcastReplyRequest, SearchBroadcastReplyResponse> {

	@InjectLogger
	Logger logger;

	BroadcastService broadcastService;

	@Inject
	public SearchBroadcastReplyActionHandler(BroadcastService broadcastService) {
		this.broadcastService = broadcastService;
	}

	@Override
	public SearchBroadcastReplyResponse execute(
			SearchBroadcastReplyRequest request, ExecutionContext response)
			throws DispatchException {

		SearchBroadcastReplyResponse staffResponse = new SearchBroadcastReplyResponse();
		BroadcastReplyListCriteria criteria = new BroadcastReplyListCriteria();
		if (request.getCriteria().getPagination() != null) {
			PaginationDetail detail = new PaginationDetail();
			detail.setLimit(request.getCriteria().getPagination().getLimit());
			detail.setStart(request.getCriteria().getPagination().getStart());

			criteria.setPaginationDetail(detail);
		}

		if (request.getCriteria().getSorting() != null) {
			SortingDetail sortingDetail = new SortingDetail();
			sortingDetail.setSort(request.getCriteria().getSorting().getSort());
			sortingDetail.setDirection(request.getCriteria().getSorting()
					.getDirection());
			criteria.setSortingDetail(sortingDetail);
		}
		criteria.setBroadcastId(request.getCriteria().getBroadcastId());

		UserContext context = new UserContext();
		context.setCorporationId(request.getSession().getCorporationId());
		context.setUserId(request.getSession().getToken());
		context.setLoginName(request.getSession().getLoginName());
		context.setUserRoles(UserRoleTool.adaptToRole(request.getSession()
				.getUserRoles()));

		PageStore<BroadcastReply> result = broadcastService
				.findBroadcastReplyList(criteria);

		List<ReplyListClient> lt = new ArrayList<ReplyListClient>();
		if (result != null && result.getResultList() != null
				&& result.getResultList().size() > 0) {
			for (BroadcastReply reply : result.getResultList()) {
				ReplyListClient client = new ReplyListClient();
				client.setId(reply.getId());
				client.setReplyContent(reply.getReplyContent());
				client.setReplyTime(reply.getReplyTime());
				client.setReplyUserId(reply.getReplyUser().getId());
				client.setReplyUserName(reply.getReplyUser().getStaff()
						.getName());
				client.setReplyUserPhoto(reply.getReplyUser().getStaff()
						.getPhoto());
				if(reply.getParent()!=null)
					client.setParent(reply.getParent().getId());
				lt.add(client);
			}

			staffResponse.setResult(lt);
			staffResponse.setTotal(result.getResultCount());
		} else {
			staffResponse.setResult(lt);
			staffResponse.setTotal(0);
		}
		return staffResponse;
	}

	@Override
	public Class<SearchBroadcastReplyRequest> getActionType() {
		return SearchBroadcastReplyRequest.class;
	}

	@Override
	public void rollback(SearchBroadcastReplyRequest request,
			SearchBroadcastReplyResponse response, ExecutionContext context)
			throws DispatchException {
	}

}
