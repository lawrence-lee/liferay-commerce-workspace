package com.liferay.commerce.samples.custom.product.renderer;

import javax.servlet.ServletContext;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.frontend.taglib.servlet.taglib.util.JSPRenderer;

@Component(
		  immediate = true,
		  property = {
		     "commerce.product.content.renderer.key=" + VirtualCPContentRenderer.KEY,
		     "commerce.product.content.renderer.order=" + Integer.MIN_VALUE,
		     "commerce.product.content.renderer.type=" + VirtualCPTypeConstants.NAME
		  },
		  service = CPContentRenderer.class
		)
public class VirtualCPContentRenderer implements CPContentRenderer {
	  public static final String KEY = "virtual";


	  @Reference
	  private JSPRenderer _jspRenderer;
	@Reference(
	     target = "(osgi.web.symbolicname=com.liferay.commerce.samples.custom.product.renderer)"
	  )
	  private ServletContext _servletContext;
}
