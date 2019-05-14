package com.liferay.commerce.samples.low.stock.activity;

import java.util.Locale;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.commerce.model.CommerceWarehouseItem;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.service.CPInstanceLocalService;
import com.liferay.commerce.stock.activity.CommerceLowStockActivity;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;

@Component(
	    immediate = true,
	    property = {
	        "commerce.low.stock.activity.key=" + CommerceLowStockActivityImpl.KEY,
	        "commerce.low.stock.activity.priority:Integer=10"
	    },
	    service = CommerceLowStockActivity.class
	)
	public class CommerceLowStockActivityImpl implements CommerceLowStockActivity {

	    public static final String KEY = "default";

		@Override
		public void execute(CommerceWarehouseItem commerceWarehouseItem) throws PortalException {
		    CPInstance cpInstance = commerceWarehouseItem.getCPInstance();

		    if (cpInstance.isPublished()) {
		       cpInstance.setPublished(false);

		       _cpInstanceLocalService.updateCPInstance(cpInstance);
		    }
		}

		@Override
		public String getKey() {
			 return KEY;
		}

		@Override
		public String getLabel(Locale locale) {
		    return LanguageUtil.get(locale, "set-as-unpublished");
		}

		@Reference
		private CPInstanceLocalService _cpInstanceLocalService;
}