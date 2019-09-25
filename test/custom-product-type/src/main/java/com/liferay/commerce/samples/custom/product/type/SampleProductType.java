package com.liferay.commerce.samples.custom.product.type;

import java.util.Locale;

import org.osgi.service.component.annotations.Component;

import com.liferay.commerce.product.type.CPType;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;


@Component(
	    immediate = true,
	    property = {
	        "commerce.product.type.display.order:Integer=20",
	        "commerce.product.type.name=sample"
	    },
	    service = CPType.class
	    )
public class SampleProductType  implements CPType {

	@Override
	public void deleteCPDefinition(long cpDefinitionId) throws PortalException {

	}

	@Override
	public String getLabel(Locale locale) {
        return LanguageUtil.get(locale, "sample");

	}

	@Override
	public String getName() {
	       return "sample";
	}

}
