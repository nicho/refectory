package com.chinarewards.elt.guice;

import com.chinarewards.elt.guice.sub.BroadcastingModule;
import com.chinarewards.elt.guice.sub.BudgetModule;
import com.chinarewards.elt.guice.sub.EmailModule;
import com.chinarewards.elt.guice.sub.GiftModule;
import com.chinarewards.elt.guice.sub.OrderModule;
import com.chinarewards.elt.guice.sub.OrgModule;
import com.chinarewards.elt.guice.sub.RewardModule;
import com.chinarewards.elt.guice.sub.StaffModule;
import com.chinarewards.elt.guice.sub.TeamModule;
import com.chinarewards.elt.guice.sub.UserModule;
import com.chinarewards.elt.tx.guice.TxModule;
import com.google.inject.AbstractModule;

public class EltModule extends AbstractModule {

	@Override
	protected void configure() {

		install(new CommonModule());

		install(new TxModule());

		install(new RewardModule());

		install(new UserModule());
		install(new OrgModule());
		install(new StaffModule());
		install(new GiftModule());
		install(new BudgetModule());
		install(new OrderModule());
		install(new TeamModule());
		install(new BroadcastingModule());
		install(new EmailModule());
	}

}
