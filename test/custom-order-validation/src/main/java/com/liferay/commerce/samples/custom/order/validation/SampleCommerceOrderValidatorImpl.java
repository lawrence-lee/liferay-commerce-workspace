package com.liferay.commerce.samples.custom.order.validation;

import java.util.Locale;

import org.osgi.service.component.annotations.Component;

import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.model.CommerceOrderItem;
import com.liferay.commerce.order.CommerceOrderValidator;
import com.liferay.commerce.order.CommerceOrderValidatorResult;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.portal.kernel.exception.PortalException;

@Component(
	    immediate = true,
	    property = {
	        "commerce.order.validator.key=" + SampleCommerceOrderValidatorImpl.KEY,
	        "commerce.order.validator.priority:Integer=10"
	    },
	    service = CommerceOrderValidator.class
	)
public class SampleCommerceOrderValidatorImpl implements CommerceOrderValidator{

    public static final String KEY = "sample-validator";

	@Override
	public CommerceOrderValidatorResult validate(Locale locale, CommerceOrderItem commerceOrderItem)
			throws PortalException {
	    if (commerceOrderItem.getQuantity() != 100) {

	        return new CommerceOrderValidatorResult(
	            commerceOrderItem.getCommerceOrderItemId(), false,
	            "quantity-is-not-valid");
	    }

	    return new CommerceOrderValidatorResult(true);
	}

    @Override
    public String getKey() {
        return KEY;
    }

	@Override
	public CommerceOrderValidatorResult validate(Locale locale, CommerceOrder commerceOrder, CPInstance cpInstance,
			int quantity) throws PortalException {
        if (cpInstance == null) {
            return new CommerceOrderValidatorResult(false);
        }

        if (quantity != 100) {

        return new CommerceOrderValidatorResult(false, "quantity-is-not-valid");
    }
        return new CommerceOrderValidatorResult(true);
    }

}
