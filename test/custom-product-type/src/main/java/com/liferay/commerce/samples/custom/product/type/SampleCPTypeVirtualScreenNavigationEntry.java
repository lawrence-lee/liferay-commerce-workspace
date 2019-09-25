package com.liferay.commerce.samples.custom.product.type;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.frontend.taglib.servlet.taglib.ScreenNavigationCategory;
import com.liferay.frontend.taglib.servlet.taglib.ScreenNavigationEntry;
import com.liferay.frontend.taglib.servlet.taglib.util.JSPRenderer;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.ResourceBundleUtil;

@Component(
	    property = {
	        "screen.navigation.category.order:Integer=20",
	        "screen.navigation.entry.order:Integer=20"
	    },
	    service = {ScreenNavigationCategory.class, ScreenNavigationEntry.class}
	)
public class SampleCPTypeVirtualScreenNavigationEntry implements ScreenNavigationCategory, ScreenNavigationEntry<CPDefinition> {

	@Override
	public String getEntryKey() {
	    return "sample";

	}

	@Override
	public void render(HttpServletRequest request, HttpServletResponse response) throws IOException {

    try {
        _jspRenderer.renderJSP(
        _servletContext, request, response,
        "/edit_sample_product.jsp");
    } finally {
	}
	}

	@Override
	public String getCategoryKey() {
	    return "sample";
	}

	@Override
	public String getLabel(Locale locale) {
	    ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
	            "content.Language", locale, getClass());

	        return LanguageUtil.get(resourceBundle, "sample");
	}

	@Override
	public String getScreenNavigationKey() {
	    return "cp.definition.general";

	}


	@Override
	public boolean isVisible(User user, CPDefinition cpDefinition) {
	    if (cpDefinition == null) {
	        return false;
	    }

	    String productTypeName = cpDefinition.getProductTypeName();

	    if (productTypeName.equals(getCategoryKey())) {
	        return true;
	    }

	    return false;
	}

    @Reference
    private JSPRenderer _jspRenderer;

    @Reference(target = "(osgi.web.symbolicname=com.liferay.commerce.samples.custom.product.type)")
    private ServletContext _servletContext;
}
