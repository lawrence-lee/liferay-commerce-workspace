package com.liferay.commerce.samples.custom.shipping.method;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import org.osgi.service.component.annotations.Component;

import com.liferay.commerce.context.CommerceContext;
import com.liferay.commerce.exception.CommerceShippingEngineException;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.model.CommerceShippingEngine;
import com.liferay.commerce.model.CommerceShippingOption;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;

@Component(
	    immediate = true,
	    property = "commerce.shipping.engine.key=" + SampleCommerceShippingMethod.KEY,
	    service = CommerceShippingEngine.class
	)
public class SampleCommerceShippingMethod implements CommerceShippingEngine {


	 public static final String KEY = "sample";

	@Override
	public String getCommerceShippingOptionLabel(String name, Locale locale) {
	    ResourceBundle resourceBundle = _getResourceBundle(locale);

	    return ResourceBundleUtil.getString(resourceBundle, name);
	}

	@Override
	public List<CommerceShippingOption> getCommerceShippingOptions(CommerceContext commerceContext,
			CommerceOrder commerceOrder, Locale locale) throws CommerceShippingEngineException {
        List<CommerceShippingOption> commerceShippingOptions =
                new ArrayList<>();


            commerceShippingOptions.add(new CommerceShippingOption("sample-option", "sample-option", BigDecimal.TEN));

                   return commerceShippingOptions;
	}

	@Override
	public String getDescription(Locale locale) {
        ResourceBundle resourceBundle = _getResourceBundle(locale);

        return LanguageUtil.get(resourceBundle, "sample-shipping-description");
	}

	@Override
	public String getName(Locale locale) {
        ResourceBundle resourceBundle = _getResourceBundle(locale);

        return LanguageUtil.get(resourceBundle, "sample-rate");
	}

    private ResourceBundle _getResourceBundle(Locale locale) {
        return ResourceBundleUtil.getBundle(
            "content.Language", locale, getClass());
    }
}
