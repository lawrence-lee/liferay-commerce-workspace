package com.liferay.commerce.samples.custom.region.starter;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.commerce.model.CommerceCountry;
import com.liferay.commerce.service.CommerceCountryLocalService;
import com.liferay.commerce.service.CommerceRegionLocalService;
import com.liferay.commerce.starter.CommerceRegionsStarter;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;

@Component(
	    immediate = true,
	    property = "commerce.region.starter.key=" + CanadaCommerceRegionsStarter.CANADA_NUMERIC_ISO_CODE,
	    service = CommerceRegionsStarter.class
	)
public class CanadaCommerceRegionsStarter implements CommerceRegionsStarter {

    public static final int CANADA_NUMERIC_ISO_CODE = 124;

    public CommerceCountry getCommerceCountry(long groupId)
        throws PortalException {

        return _commerceCountryLocalService.fetchCommerceCountry(
            groupId, CANADA_NUMERIC_ISO_CODE);
    }

	@Override
	public void start(ServiceContext serviceContext) throws Exception {
        CommerceCountry commerceCountry = getCommerceCountry(
                serviceContext.getScopeGroupId());

            if (commerceCountry == null) {
                return;
            }

            _commerceRegionLocalService.addCommerceRegion(
                    commerceCountry.getCommerceCountryId(),"Ontario" , "ON", 1,
                    true, serviceContext);
            _commerceRegionLocalService.addCommerceRegion(
                    commerceCountry.getCommerceCountryId(),"Manitoba" , "MB", 2,
                    true, serviceContext);
            _commerceRegionLocalService.addCommerceRegion(
                    commerceCountry.getCommerceCountryId(),"British Colombia" , "BC", 3,
                    true, serviceContext);
            _commerceRegionLocalService.addCommerceRegion(
                    commerceCountry.getCommerceCountryId(),"Yukon" , "YT", 4,
                    true, serviceContext);
	}

    @Reference
    private CommerceCountryLocalService _commerceCountryLocalService;

    @Reference
    private CommerceRegionLocalService _commerceRegionLocalService;
}
