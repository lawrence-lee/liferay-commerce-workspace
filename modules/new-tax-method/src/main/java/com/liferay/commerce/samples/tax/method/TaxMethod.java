package com.liferay.commerce.samples.tax.method;

import java.math.BigDecimal;
import java.util.Locale;
import java.util.ResourceBundle;

import org.osgi.service.component.annotations.Component;

import com.liferay.commerce.exception.CommerceTaxEngineException;
import com.liferay.commerce.tax.CommerceTaxCalculateRequest;
import com.liferay.commerce.tax.CommerceTaxEngine;
import com.liferay.commerce.tax.CommerceTaxValue;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;

@Component(immediate = true, property = "commerce.tax.engine.key=sample-tax", service = CommerceTaxEngine.class)
public class TaxMethod  implements CommerceTaxEngine {

    public static final String KEY = "sample-tax";

	@Override
	public CommerceTaxValue getCommerceTaxValue(CommerceTaxCalculateRequest commerceTaxCalculateRequest) throws CommerceTaxEngineException{
    CommerceTaxValue commerceTaxValue = null;

    BigDecimal rate = BigDecimal.TEN;

    BigDecimal amount = commerceTaxCalculateRequest.getPrice();

    BigDecimal taxValue = rate;

    if (commerceTaxCalculateRequest.isPercentage()) {
       taxValue = amount.multiply(rate);

       taxValue = taxValue.divide(_ONE_HUNDRED);
    }

    commerceTaxValue = new CommerceTaxValue(KEY, KEY, taxValue);

    return commerceTaxValue;
	}

	@Override
	public String getDescription(Locale locale) {
	    ResourceBundle resourceBundle = _getResourceBundle(locale);

	    return LanguageUtil.get(resourceBundle, "sample-tax-description");
	}

	@Override
	public String getName(Locale locale) {
	    ResourceBundle resourceBundle = _getResourceBundle(locale);

	    return LanguageUtil.get(resourceBundle, KEY);
	}

	private ResourceBundle _getResourceBundle(Locale locale) {
	    return ResourceBundleUtil.getBundle("content.Language", locale, getClass());
	}

    private static final BigDecimal _ONE_HUNDRED = BigDecimal.valueOf(100);


}
