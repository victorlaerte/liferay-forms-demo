package org.apache.jsp.partials;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.liferay.frontend.taglib.clay.sample.web.constants.ClaySamplePortletKeys;
import com.liferay.frontend.taglib.clay.sample.web.internal.display.context.CardsDisplayContext;
import com.liferay.frontend.taglib.clay.sample.web.internal.display.context.DropdownsDisplayContext;
import com.liferay.frontend.taglib.clay.sample.web.internal.display.context.ManagementToolbarsDisplayContext;
import com.liferay.frontend.taglib.clay.sample.web.internal.display.context.NavigationBarsDisplayContext;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.SelectOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class buttons_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(2);
    _jspx_dependants.add("/init.jsp");
    _jspx_dependants.add("/init-ext.jsp");
  }

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_clay_button_style_icon_disabled_ariaLabel_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_clay_button_style_icon_ariaLabel_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1theme_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_clay_button_label_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_clay_button_style_label_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_clay_button_icon_disabled_ariaLabel_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_clay_button_label_icon_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_clay_button_label_block_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_clay_button_style_label_disabled_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_clay_button_icon_ariaLabel_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_clay_button_style_monospaced_icon_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_clay_button_label_disabled_nobody;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_clay_button_style_icon_disabled_ariaLabel_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_clay_button_style_icon_ariaLabel_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1theme_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_clay_button_label_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_clay_button_style_label_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_clay_button_icon_disabled_ariaLabel_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_clay_button_label_icon_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_clay_button_label_block_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_clay_button_style_label_disabled_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_clay_button_icon_ariaLabel_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_clay_button_style_monospaced_icon_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_clay_button_label_disabled_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_clay_button_style_icon_disabled_ariaLabel_nobody.release();
    _jspx_tagPool_clay_button_style_icon_ariaLabel_nobody.release();
    _jspx_tagPool_liferay$1theme_defineObjects_nobody.release();
    _jspx_tagPool_clay_button_label_nobody.release();
    _jspx_tagPool_clay_button_style_label_nobody.release();
    _jspx_tagPool_clay_button_icon_disabled_ariaLabel_nobody.release();
    _jspx_tagPool_clay_button_label_icon_nobody.release();
    _jspx_tagPool_clay_button_label_block_nobody.release();
    _jspx_tagPool_clay_button_style_label_disabled_nobody.release();
    _jspx_tagPool_clay_button_icon_ariaLabel_nobody.release();
    _jspx_tagPool_clay_button_style_monospaced_icon_nobody.release();
    _jspx_tagPool_clay_button_label_disabled_nobody.release();
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write('\n');
      out.write('\n');
      out.write("\n\n\n\n\n\n\n\n");
      //  liferay-theme:defineObjects
      com.liferay.taglib.theme.DefineObjectsTag _jspx_th_liferay$1theme_defineObjects_0 = (com.liferay.taglib.theme.DefineObjectsTag) _jspx_tagPool_liferay$1theme_defineObjects_nobody.get(com.liferay.taglib.theme.DefineObjectsTag.class);
      _jspx_th_liferay$1theme_defineObjects_0.setPageContext(_jspx_page_context);
      _jspx_th_liferay$1theme_defineObjects_0.setParent(null);
      int _jspx_eval_liferay$1theme_defineObjects_0 = _jspx_th_liferay$1theme_defineObjects_0.doStartTag();
      if (_jspx_th_liferay$1theme_defineObjects_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_liferay$1theme_defineObjects_nobody.reuse(_jspx_th_liferay$1theme_defineObjects_0);
        return;
      }
      _jspx_tagPool_liferay$1theme_defineObjects_nobody.reuse(_jspx_th_liferay$1theme_defineObjects_0);
      com.liferay.portal.kernel.theme.ThemeDisplay themeDisplay = null;
      com.liferay.portal.kernel.model.Company company = null;
      com.liferay.portal.kernel.model.Account account = null;
      com.liferay.portal.kernel.model.User user = null;
      com.liferay.portal.kernel.model.User realUser = null;
      com.liferay.portal.kernel.model.Contact contact = null;
      com.liferay.portal.kernel.model.Layout layout = null;
      java.util.List layouts = null;
      java.lang.Long plid = null;
      com.liferay.portal.kernel.model.LayoutTypePortlet layoutTypePortlet = null;
      java.lang.Long scopeGroupId = null;
      com.liferay.portal.kernel.security.permission.PermissionChecker permissionChecker = null;
      java.util.Locale locale = null;
      java.util.TimeZone timeZone = null;
      com.liferay.portal.kernel.model.Theme theme = null;
      com.liferay.portal.kernel.model.ColorScheme colorScheme = null;
      com.liferay.portal.kernel.theme.PortletDisplay portletDisplay = null;
      java.lang.Long portletGroupId = null;
      themeDisplay = (com.liferay.portal.kernel.theme.ThemeDisplay) _jspx_page_context.findAttribute("themeDisplay");
      company = (com.liferay.portal.kernel.model.Company) _jspx_page_context.findAttribute("company");
      account = (com.liferay.portal.kernel.model.Account) _jspx_page_context.findAttribute("account");
      user = (com.liferay.portal.kernel.model.User) _jspx_page_context.findAttribute("user");
      realUser = (com.liferay.portal.kernel.model.User) _jspx_page_context.findAttribute("realUser");
      contact = (com.liferay.portal.kernel.model.Contact) _jspx_page_context.findAttribute("contact");
      layout = (com.liferay.portal.kernel.model.Layout) _jspx_page_context.findAttribute("layout");
      layouts = (java.util.List) _jspx_page_context.findAttribute("layouts");
      plid = (java.lang.Long) _jspx_page_context.findAttribute("plid");
      layoutTypePortlet = (com.liferay.portal.kernel.model.LayoutTypePortlet) _jspx_page_context.findAttribute("layoutTypePortlet");
      scopeGroupId = (java.lang.Long) _jspx_page_context.findAttribute("scopeGroupId");
      permissionChecker = (com.liferay.portal.kernel.security.permission.PermissionChecker) _jspx_page_context.findAttribute("permissionChecker");
      locale = (java.util.Locale) _jspx_page_context.findAttribute("locale");
      timeZone = (java.util.TimeZone) _jspx_page_context.findAttribute("timeZone");
      theme = (com.liferay.portal.kernel.model.Theme) _jspx_page_context.findAttribute("theme");
      colorScheme = (com.liferay.portal.kernel.model.ColorScheme) _jspx_page_context.findAttribute("colorScheme");
      portletDisplay = (com.liferay.portal.kernel.theme.PortletDisplay) _jspx_page_context.findAttribute("portletDisplay");
      portletGroupId = (java.lang.Long) _jspx_page_context.findAttribute("portletGroupId");
      out.write('\n');
      out.write('\n');
      out.write('\n');
      out.write('\n');

CardsDisplayContext cardsDisplayContext = (CardsDisplayContext)request.getAttribute(ClaySamplePortletKeys.CARDS_DISPLAY_CONTEXT);
DropdownsDisplayContext dropdownsDisplayContext = (DropdownsDisplayContext)request.getAttribute(ClaySamplePortletKeys.DROPDOWNS_DISPLAY_CONTEXT);
ManagementToolbarsDisplayContext managementToolbarsDisplayContext = (ManagementToolbarsDisplayContext)request.getAttribute(ClaySamplePortletKeys.MANAGEMENT_TOOLBARS_DISPLAY_CONTEXT);
NavigationBarsDisplayContext navigationBarsDisplayContext = (NavigationBarsDisplayContext)request.getAttribute(ClaySamplePortletKeys.NAVIGATION_BARS_DISPLAY_CONTEXT);

      out.write("\n\n<blockquote><p>Buttons communicate an action to happen on user interaction.</p></blockquote>\n\n<h3>TYPES</h3>\n\n<table class=\"table\">\n\t<thead>\n\t\t<tr>\n\t\t\t<th>TYPE</th>\n\t\t\t<th>USAGE</th>\n\t\t</tr>\n\t</thead>\n\n\t<tbody>\n\t\t<tr>\n\t\t\t<td>\n\t\t\t\t<div class=\"flex-md-nowrap mb-2 row\">\n\t\t\t\t\t<div class=\"col\">");
      if (_jspx_meth_clay_button_0(_jspx_page_context))
        return;
      out.write("</div>\n\t\t\t\t\t<div class=\"col\">");
      if (_jspx_meth_clay_button_1(_jspx_page_context))
        return;
      out.write("</div>\n\t\t\t\t</div>\n\n\t\t\t\t<div class=\"flex-md-nowrap row\">\n\t\t\t\t\t<div class=\"col\">");
      //  clay:button
      com.liferay.frontend.taglib.clay.servlet.taglib.soy.ButtonTag _jspx_th_clay_button_2 = (com.liferay.frontend.taglib.clay.servlet.taglib.soy.ButtonTag) _jspx_tagPool_clay_button_label_disabled_nobody.get(com.liferay.frontend.taglib.clay.servlet.taglib.soy.ButtonTag.class);
      _jspx_th_clay_button_2.setPageContext(_jspx_page_context);
      _jspx_th_clay_button_2.setParent(null);
      _jspx_th_clay_button_2.setDisabled( true );
      _jspx_th_clay_button_2.setLabel("Primary");
      int _jspx_eval_clay_button_2 = _jspx_th_clay_button_2.doStartTag();
      if (_jspx_th_clay_button_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_clay_button_label_disabled_nobody.reuse(_jspx_th_clay_button_2);
        return;
      }
      _jspx_tagPool_clay_button_label_disabled_nobody.reuse(_jspx_th_clay_button_2);
      out.write("</div>\n\t\t\t\t\t<div class=\"col\">");
      //  clay:button
      com.liferay.frontend.taglib.clay.servlet.taglib.soy.ButtonTag _jspx_th_clay_button_3 = (com.liferay.frontend.taglib.clay.servlet.taglib.soy.ButtonTag) _jspx_tagPool_clay_button_icon_disabled_ariaLabel_nobody.get(com.liferay.frontend.taglib.clay.servlet.taglib.soy.ButtonTag.class);
      _jspx_th_clay_button_3.setPageContext(_jspx_page_context);
      _jspx_th_clay_button_3.setParent(null);
      _jspx_th_clay_button_3.setAriaLabel("Workflow");
      _jspx_th_clay_button_3.setDisabled( true );
      _jspx_th_clay_button_3.setIcon("workflow");
      int _jspx_eval_clay_button_3 = _jspx_th_clay_button_3.doStartTag();
      if (_jspx_th_clay_button_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_clay_button_icon_disabled_ariaLabel_nobody.reuse(_jspx_th_clay_button_3);
        return;
      }
      _jspx_tagPool_clay_button_icon_disabled_ariaLabel_nobody.reuse(_jspx_th_clay_button_3);
      out.write("</div>\n\t\t\t\t</div>\n\t\t\t</td>\n\t\t\t<td><strong>Primary</strong>: The primary button is always use for the most important actions. There can't be two primary actions together or near by.</td>\n\t\t</tr>\n\t\t<tr>\n\t\t\t<td>\n\t\t\t\t<div class=\"flex-md-nowrap mb-2 row\">\n\t\t\t\t\t<div class=\"col\">");
      if (_jspx_meth_clay_button_4(_jspx_page_context))
        return;
      out.write("</div>\n\t\t\t\t\t<div class=\"col\">");
      if (_jspx_meth_clay_button_5(_jspx_page_context))
        return;
      out.write("</div>\n\t\t\t\t</div>\n\n\t\t\t\t<div class=\"flex-md-nowrap row\">\n\t\t\t\t\t<div class=\"col\">");
      //  clay:button
      com.liferay.frontend.taglib.clay.servlet.taglib.soy.ButtonTag _jspx_th_clay_button_6 = (com.liferay.frontend.taglib.clay.servlet.taglib.soy.ButtonTag) _jspx_tagPool_clay_button_style_label_disabled_nobody.get(com.liferay.frontend.taglib.clay.servlet.taglib.soy.ButtonTag.class);
      _jspx_th_clay_button_6.setPageContext(_jspx_page_context);
      _jspx_th_clay_button_6.setParent(null);
      _jspx_th_clay_button_6.setDisabled( true );
      _jspx_th_clay_button_6.setLabel("Secondary");
      _jspx_th_clay_button_6.setStyle("secondary");
      int _jspx_eval_clay_button_6 = _jspx_th_clay_button_6.doStartTag();
      if (_jspx_th_clay_button_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_clay_button_style_label_disabled_nobody.reuse(_jspx_th_clay_button_6);
        return;
      }
      _jspx_tagPool_clay_button_style_label_disabled_nobody.reuse(_jspx_th_clay_button_6);
      out.write("</div>\n\t\t\t\t\t<div class=\"col\">");
      //  clay:button
      com.liferay.frontend.taglib.clay.servlet.taglib.soy.ButtonTag _jspx_th_clay_button_7 = (com.liferay.frontend.taglib.clay.servlet.taglib.soy.ButtonTag) _jspx_tagPool_clay_button_style_icon_disabled_ariaLabel_nobody.get(com.liferay.frontend.taglib.clay.servlet.taglib.soy.ButtonTag.class);
      _jspx_th_clay_button_7.setPageContext(_jspx_page_context);
      _jspx_th_clay_button_7.setParent(null);
      _jspx_th_clay_button_7.setAriaLabel("Wiki");
      _jspx_th_clay_button_7.setDisabled( true );
      _jspx_th_clay_button_7.setIcon("wiki");
      _jspx_th_clay_button_7.setStyle("secondary");
      int _jspx_eval_clay_button_7 = _jspx_th_clay_button_7.doStartTag();
      if (_jspx_th_clay_button_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_clay_button_style_icon_disabled_ariaLabel_nobody.reuse(_jspx_th_clay_button_7);
        return;
      }
      _jspx_tagPool_clay_button_style_icon_disabled_ariaLabel_nobody.reuse(_jspx_th_clay_button_7);
      out.write("</div>\n\t\t\t\t</div>\n\t\t\t</td>\n\t\t\t<td><strong>Secondary</strong>: The secondary button is always use for the secondary actions. There can be several secondary actions near by.</td>\n\t\t</tr>\n\t\t<tr>\n\t\t\t<td>\n\t\t\t\t<div class=\"flex-md-nowrap mb-2 row\">\n\t\t\t\t\t<div class=\"col\">");
      if (_jspx_meth_clay_button_8(_jspx_page_context))
        return;
      out.write("</div>\n\t\t\t\t\t<div class=\"col\">");
      if (_jspx_meth_clay_button_9(_jspx_page_context))
        return;
      out.write("</div>\n\t\t\t\t</div>\n\n\t\t\t\t<div class=\"flex-md-nowrap row\">\n\t\t\t\t\t<div class=\"col\">");
      //  clay:button
      com.liferay.frontend.taglib.clay.servlet.taglib.soy.ButtonTag _jspx_th_clay_button_10 = (com.liferay.frontend.taglib.clay.servlet.taglib.soy.ButtonTag) _jspx_tagPool_clay_button_style_label_disabled_nobody.get(com.liferay.frontend.taglib.clay.servlet.taglib.soy.ButtonTag.class);
      _jspx_th_clay_button_10.setPageContext(_jspx_page_context);
      _jspx_th_clay_button_10.setParent(null);
      _jspx_th_clay_button_10.setDisabled( true );
      _jspx_th_clay_button_10.setLabel("Borderless");
      _jspx_th_clay_button_10.setStyle("borderless");
      int _jspx_eval_clay_button_10 = _jspx_th_clay_button_10.doStartTag();
      if (_jspx_th_clay_button_10.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_clay_button_style_label_disabled_nobody.reuse(_jspx_th_clay_button_10);
        return;
      }
      _jspx_tagPool_clay_button_style_label_disabled_nobody.reuse(_jspx_th_clay_button_10);
      out.write("</div>\n\t\t\t\t\t<div class=\"col\">");
      //  clay:button
      com.liferay.frontend.taglib.clay.servlet.taglib.soy.ButtonTag _jspx_th_clay_button_11 = (com.liferay.frontend.taglib.clay.servlet.taglib.soy.ButtonTag) _jspx_tagPool_clay_button_style_icon_disabled_ariaLabel_nobody.get(com.liferay.frontend.taglib.clay.servlet.taglib.soy.ButtonTag.class);
      _jspx_th_clay_button_11.setPageContext(_jspx_page_context);
      _jspx_th_clay_button_11.setParent(null);
      _jspx_th_clay_button_11.setAriaLabel("Page Template");
      _jspx_th_clay_button_11.setDisabled( true );
      _jspx_th_clay_button_11.setIcon("page-template");
      _jspx_th_clay_button_11.setStyle("borderless");
      int _jspx_eval_clay_button_11 = _jspx_th_clay_button_11.doStartTag();
      if (_jspx_th_clay_button_11.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_clay_button_style_icon_disabled_ariaLabel_nobody.reuse(_jspx_th_clay_button_11);
        return;
      }
      _jspx_tagPool_clay_button_style_icon_disabled_ariaLabel_nobody.reuse(_jspx_th_clay_button_11);
      out.write("</div>\n\t\t\t\t</div>\n\t\t\t</td>\n\t\t\t<td><strong>Borderless</strong>: Use in those cases as toolbars where the secondary button would be too heavy for the pattern design. In this way the design gets cleaner.</td>\n\t\t</tr>\n\t\t<tr>\n\t\t\t<td>\n\t\t\t\t<div class=\"flex-md-nowrap mb-2 row\">\n\t\t\t\t\t<div class=\"col\">");
      if (_jspx_meth_clay_button_12(_jspx_page_context))
        return;
      out.write("</div>\n\t\t\t\t\t<div class=\"col\">");
      if (_jspx_meth_clay_button_13(_jspx_page_context))
        return;
      out.write("</div>\n\t\t\t\t</div>\n\n\t\t\t\t<div class=\"flex-md-nowrap row\">\n\t\t\t\t\t<div class=\"col\">");
      //  clay:button
      com.liferay.frontend.taglib.clay.servlet.taglib.soy.ButtonTag _jspx_th_clay_button_14 = (com.liferay.frontend.taglib.clay.servlet.taglib.soy.ButtonTag) _jspx_tagPool_clay_button_style_label_disabled_nobody.get(com.liferay.frontend.taglib.clay.servlet.taglib.soy.ButtonTag.class);
      _jspx_th_clay_button_14.setPageContext(_jspx_page_context);
      _jspx_th_clay_button_14.setParent(null);
      _jspx_th_clay_button_14.setDisabled( true );
      _jspx_th_clay_button_14.setLabel("Link");
      _jspx_th_clay_button_14.setStyle("link");
      int _jspx_eval_clay_button_14 = _jspx_th_clay_button_14.doStartTag();
      if (_jspx_th_clay_button_14.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_clay_button_style_label_disabled_nobody.reuse(_jspx_th_clay_button_14);
        return;
      }
      _jspx_tagPool_clay_button_style_label_disabled_nobody.reuse(_jspx_th_clay_button_14);
      out.write("</div>\n\t\t\t\t\t<div class=\"col\">");
      //  clay:button
      com.liferay.frontend.taglib.clay.servlet.taglib.soy.ButtonTag _jspx_th_clay_button_15 = (com.liferay.frontend.taglib.clay.servlet.taglib.soy.ButtonTag) _jspx_tagPool_clay_button_style_icon_disabled_ariaLabel_nobody.get(com.liferay.frontend.taglib.clay.servlet.taglib.soy.ButtonTag.class);
      _jspx_th_clay_button_15.setPageContext(_jspx_page_context);
      _jspx_th_clay_button_15.setParent(null);
      _jspx_th_clay_button_15.setAriaLabel("Add Role");
      _jspx_th_clay_button_15.setDisabled( true );
      _jspx_th_clay_button_15.setIcon("add-role");
      _jspx_th_clay_button_15.setStyle("link");
      int _jspx_eval_clay_button_15 = _jspx_th_clay_button_15.doStartTag();
      if (_jspx_th_clay_button_15.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_clay_button_style_icon_disabled_ariaLabel_nobody.reuse(_jspx_th_clay_button_15);
        return;
      }
      _jspx_tagPool_clay_button_style_icon_disabled_ariaLabel_nobody.reuse(_jspx_th_clay_button_15);
      out.write("</div>\n\t\t\t\t</div>\n\t\t\t</td>\n\t\t\t<td><strong>Link</strong>: Used for many Cancel actions.</td>\n\t\t</tr>\n\t</tbody>\n</table>\n\n<h3>VARIATIONS</h3>\n\n<div class=\"row text-center\">\n\t\t<div class=\"col-md-2\">\n\t\t\t");
      if (_jspx_meth_clay_button_16(_jspx_page_context))
        return;
      out.write("\n\n\t\t\t<div>Icon and Text Button</div>\n\t\t</div>\n\n\t\t<div class=\"col-md-2\">\n\t\t\t");
      //  clay:button
      com.liferay.frontend.taglib.clay.servlet.taglib.soy.ButtonTag _jspx_th_clay_button_17 = (com.liferay.frontend.taglib.clay.servlet.taglib.soy.ButtonTag) _jspx_tagPool_clay_button_style_monospaced_icon_nobody.get(com.liferay.frontend.taglib.clay.servlet.taglib.soy.ButtonTag.class);
      _jspx_th_clay_button_17.setPageContext(_jspx_page_context);
      _jspx_th_clay_button_17.setParent(null);
      _jspx_th_clay_button_17.setIcon("indent-less");
      _jspx_th_clay_button_17.setMonospaced( true );
      _jspx_th_clay_button_17.setStyle("secondary");
      int _jspx_eval_clay_button_17 = _jspx_th_clay_button_17.doStartTag();
      if (_jspx_th_clay_button_17.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_clay_button_style_monospaced_icon_nobody.reuse(_jspx_th_clay_button_17);
        return;
      }
      _jspx_tagPool_clay_button_style_monospaced_icon_nobody.reuse(_jspx_th_clay_button_17);
      out.write("\n\n\t\t\t<div>Monospaced Button</div>\n\t\t</div>\n\n\t\t<div class=\"col-md-4\">\n\t\t\t");
      //  clay:button
      com.liferay.frontend.taglib.clay.servlet.taglib.soy.ButtonTag _jspx_th_clay_button_18 = (com.liferay.frontend.taglib.clay.servlet.taglib.soy.ButtonTag) _jspx_tagPool_clay_button_label_block_nobody.get(com.liferay.frontend.taglib.clay.servlet.taglib.soy.ButtonTag.class);
      _jspx_th_clay_button_18.setPageContext(_jspx_page_context);
      _jspx_th_clay_button_18.setParent(null);
      _jspx_th_clay_button_18.setBlock( true );
      _jspx_th_clay_button_18.setLabel("Button");
      int _jspx_eval_clay_button_18 = _jspx_th_clay_button_18.doStartTag();
      if (_jspx_th_clay_button_18.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_clay_button_label_block_nobody.reuse(_jspx_th_clay_button_18);
        return;
      }
      _jspx_tagPool_clay_button_label_block_nobody.reuse(_jspx_th_clay_button_18);
      out.write("\n\n\t\t\t<div>Block Level Button</div>\n\t\t</div>\n\n\t\t<div class=\"col-md-2\">\n\t\t\t");
      //  clay:button
      com.liferay.frontend.taglib.clay.servlet.taglib.soy.ButtonTag _jspx_th_clay_button_19 = (com.liferay.frontend.taglib.clay.servlet.taglib.soy.ButtonTag) _jspx_tagPool_clay_button_style_monospaced_icon_nobody.get(com.liferay.frontend.taglib.clay.servlet.taglib.soy.ButtonTag.class);
      _jspx_th_clay_button_19.setPageContext(_jspx_page_context);
      _jspx_th_clay_button_19.setParent(null);
      _jspx_th_clay_button_19.setIcon("plus");
      _jspx_th_clay_button_19.setMonospaced( true );
      _jspx_th_clay_button_19.setStyle("secondary");
      int _jspx_eval_clay_button_19 = _jspx_th_clay_button_19.doStartTag();
      if (_jspx_th_clay_button_19.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_clay_button_style_monospaced_icon_nobody.reuse(_jspx_th_clay_button_19);
        return;
      }
      _jspx_tagPool_clay_button_style_monospaced_icon_nobody.reuse(_jspx_th_clay_button_19);
      out.write("\n\n\t\t\t<div>Plus Button</div>\n\t\t</div>\n\n\t\t<div class=\"col-md-2\">\n\t\t\t");
      //  clay:button
      com.liferay.frontend.taglib.clay.servlet.taglib.soy.ButtonTag _jspx_th_clay_button_20 = (com.liferay.frontend.taglib.clay.servlet.taglib.soy.ButtonTag) _jspx_tagPool_clay_button_style_monospaced_icon_nobody.get(com.liferay.frontend.taglib.clay.servlet.taglib.soy.ButtonTag.class);
      _jspx_th_clay_button_20.setPageContext(_jspx_page_context);
      _jspx_th_clay_button_20.setParent(null);
      _jspx_th_clay_button_20.setIcon("ellipsis-v");
      _jspx_th_clay_button_20.setMonospaced( true );
      _jspx_th_clay_button_20.setStyle("borderless");
      int _jspx_eval_clay_button_20 = _jspx_th_clay_button_20.doStartTag();
      if (_jspx_th_clay_button_20.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_clay_button_style_monospaced_icon_nobody.reuse(_jspx_th_clay_button_20);
        return;
      }
      _jspx_tagPool_clay_button_style_monospaced_icon_nobody.reuse(_jspx_th_clay_button_20);
      out.write("\n\n\t\t\t<div>Action Button</div>\n\t\t</div>\n\t</div>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_clay_button_0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  clay:button
    com.liferay.frontend.taglib.clay.servlet.taglib.soy.ButtonTag _jspx_th_clay_button_0 = (com.liferay.frontend.taglib.clay.servlet.taglib.soy.ButtonTag) _jspx_tagPool_clay_button_label_nobody.get(com.liferay.frontend.taglib.clay.servlet.taglib.soy.ButtonTag.class);
    _jspx_th_clay_button_0.setPageContext(_jspx_page_context);
    _jspx_th_clay_button_0.setParent(null);
    _jspx_th_clay_button_0.setLabel("Primary");
    int _jspx_eval_clay_button_0 = _jspx_th_clay_button_0.doStartTag();
    if (_jspx_th_clay_button_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_clay_button_label_nobody.reuse(_jspx_th_clay_button_0);
      return true;
    }
    _jspx_tagPool_clay_button_label_nobody.reuse(_jspx_th_clay_button_0);
    return false;
  }

  private boolean _jspx_meth_clay_button_1(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  clay:button
    com.liferay.frontend.taglib.clay.servlet.taglib.soy.ButtonTag _jspx_th_clay_button_1 = (com.liferay.frontend.taglib.clay.servlet.taglib.soy.ButtonTag) _jspx_tagPool_clay_button_icon_ariaLabel_nobody.get(com.liferay.frontend.taglib.clay.servlet.taglib.soy.ButtonTag.class);
    _jspx_th_clay_button_1.setPageContext(_jspx_page_context);
    _jspx_th_clay_button_1.setParent(null);
    _jspx_th_clay_button_1.setAriaLabel("Workflow");
    _jspx_th_clay_button_1.setIcon("workflow");
    int _jspx_eval_clay_button_1 = _jspx_th_clay_button_1.doStartTag();
    if (_jspx_th_clay_button_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_clay_button_icon_ariaLabel_nobody.reuse(_jspx_th_clay_button_1);
      return true;
    }
    _jspx_tagPool_clay_button_icon_ariaLabel_nobody.reuse(_jspx_th_clay_button_1);
    return false;
  }

  private boolean _jspx_meth_clay_button_4(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  clay:button
    com.liferay.frontend.taglib.clay.servlet.taglib.soy.ButtonTag _jspx_th_clay_button_4 = (com.liferay.frontend.taglib.clay.servlet.taglib.soy.ButtonTag) _jspx_tagPool_clay_button_style_label_nobody.get(com.liferay.frontend.taglib.clay.servlet.taglib.soy.ButtonTag.class);
    _jspx_th_clay_button_4.setPageContext(_jspx_page_context);
    _jspx_th_clay_button_4.setParent(null);
    _jspx_th_clay_button_4.setLabel("Secondary");
    _jspx_th_clay_button_4.setStyle("secondary");
    int _jspx_eval_clay_button_4 = _jspx_th_clay_button_4.doStartTag();
    if (_jspx_th_clay_button_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_clay_button_style_label_nobody.reuse(_jspx_th_clay_button_4);
      return true;
    }
    _jspx_tagPool_clay_button_style_label_nobody.reuse(_jspx_th_clay_button_4);
    return false;
  }

  private boolean _jspx_meth_clay_button_5(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  clay:button
    com.liferay.frontend.taglib.clay.servlet.taglib.soy.ButtonTag _jspx_th_clay_button_5 = (com.liferay.frontend.taglib.clay.servlet.taglib.soy.ButtonTag) _jspx_tagPool_clay_button_style_icon_ariaLabel_nobody.get(com.liferay.frontend.taglib.clay.servlet.taglib.soy.ButtonTag.class);
    _jspx_th_clay_button_5.setPageContext(_jspx_page_context);
    _jspx_th_clay_button_5.setParent(null);
    _jspx_th_clay_button_5.setAriaLabel("Wiki");
    _jspx_th_clay_button_5.setIcon("wiki");
    _jspx_th_clay_button_5.setStyle("secondary");
    int _jspx_eval_clay_button_5 = _jspx_th_clay_button_5.doStartTag();
    if (_jspx_th_clay_button_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_clay_button_style_icon_ariaLabel_nobody.reuse(_jspx_th_clay_button_5);
      return true;
    }
    _jspx_tagPool_clay_button_style_icon_ariaLabel_nobody.reuse(_jspx_th_clay_button_5);
    return false;
  }

  private boolean _jspx_meth_clay_button_8(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  clay:button
    com.liferay.frontend.taglib.clay.servlet.taglib.soy.ButtonTag _jspx_th_clay_button_8 = (com.liferay.frontend.taglib.clay.servlet.taglib.soy.ButtonTag) _jspx_tagPool_clay_button_style_label_nobody.get(com.liferay.frontend.taglib.clay.servlet.taglib.soy.ButtonTag.class);
    _jspx_th_clay_button_8.setPageContext(_jspx_page_context);
    _jspx_th_clay_button_8.setParent(null);
    _jspx_th_clay_button_8.setLabel("Borderless");
    _jspx_th_clay_button_8.setStyle("borderless");
    int _jspx_eval_clay_button_8 = _jspx_th_clay_button_8.doStartTag();
    if (_jspx_th_clay_button_8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_clay_button_style_label_nobody.reuse(_jspx_th_clay_button_8);
      return true;
    }
    _jspx_tagPool_clay_button_style_label_nobody.reuse(_jspx_th_clay_button_8);
    return false;
  }

  private boolean _jspx_meth_clay_button_9(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  clay:button
    com.liferay.frontend.taglib.clay.servlet.taglib.soy.ButtonTag _jspx_th_clay_button_9 = (com.liferay.frontend.taglib.clay.servlet.taglib.soy.ButtonTag) _jspx_tagPool_clay_button_style_icon_ariaLabel_nobody.get(com.liferay.frontend.taglib.clay.servlet.taglib.soy.ButtonTag.class);
    _jspx_th_clay_button_9.setPageContext(_jspx_page_context);
    _jspx_th_clay_button_9.setParent(null);
    _jspx_th_clay_button_9.setAriaLabel("Page Template");
    _jspx_th_clay_button_9.setIcon("page-template");
    _jspx_th_clay_button_9.setStyle("borderless");
    int _jspx_eval_clay_button_9 = _jspx_th_clay_button_9.doStartTag();
    if (_jspx_th_clay_button_9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_clay_button_style_icon_ariaLabel_nobody.reuse(_jspx_th_clay_button_9);
      return true;
    }
    _jspx_tagPool_clay_button_style_icon_ariaLabel_nobody.reuse(_jspx_th_clay_button_9);
    return false;
  }

  private boolean _jspx_meth_clay_button_12(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  clay:button
    com.liferay.frontend.taglib.clay.servlet.taglib.soy.ButtonTag _jspx_th_clay_button_12 = (com.liferay.frontend.taglib.clay.servlet.taglib.soy.ButtonTag) _jspx_tagPool_clay_button_style_label_nobody.get(com.liferay.frontend.taglib.clay.servlet.taglib.soy.ButtonTag.class);
    _jspx_th_clay_button_12.setPageContext(_jspx_page_context);
    _jspx_th_clay_button_12.setParent(null);
    _jspx_th_clay_button_12.setLabel("Link");
    _jspx_th_clay_button_12.setStyle("link");
    int _jspx_eval_clay_button_12 = _jspx_th_clay_button_12.doStartTag();
    if (_jspx_th_clay_button_12.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_clay_button_style_label_nobody.reuse(_jspx_th_clay_button_12);
      return true;
    }
    _jspx_tagPool_clay_button_style_label_nobody.reuse(_jspx_th_clay_button_12);
    return false;
  }

  private boolean _jspx_meth_clay_button_13(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  clay:button
    com.liferay.frontend.taglib.clay.servlet.taglib.soy.ButtonTag _jspx_th_clay_button_13 = (com.liferay.frontend.taglib.clay.servlet.taglib.soy.ButtonTag) _jspx_tagPool_clay_button_style_icon_ariaLabel_nobody.get(com.liferay.frontend.taglib.clay.servlet.taglib.soy.ButtonTag.class);
    _jspx_th_clay_button_13.setPageContext(_jspx_page_context);
    _jspx_th_clay_button_13.setParent(null);
    _jspx_th_clay_button_13.setAriaLabel("Add Role");
    _jspx_th_clay_button_13.setIcon("add-role");
    _jspx_th_clay_button_13.setStyle("link");
    int _jspx_eval_clay_button_13 = _jspx_th_clay_button_13.doStartTag();
    if (_jspx_th_clay_button_13.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_clay_button_style_icon_ariaLabel_nobody.reuse(_jspx_th_clay_button_13);
      return true;
    }
    _jspx_tagPool_clay_button_style_icon_ariaLabel_nobody.reuse(_jspx_th_clay_button_13);
    return false;
  }

  private boolean _jspx_meth_clay_button_16(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  clay:button
    com.liferay.frontend.taglib.clay.servlet.taglib.soy.ButtonTag _jspx_th_clay_button_16 = (com.liferay.frontend.taglib.clay.servlet.taglib.soy.ButtonTag) _jspx_tagPool_clay_button_label_icon_nobody.get(com.liferay.frontend.taglib.clay.servlet.taglib.soy.ButtonTag.class);
    _jspx_th_clay_button_16.setPageContext(_jspx_page_context);
    _jspx_th_clay_button_16.setParent(null);
    _jspx_th_clay_button_16.setIcon("share");
    _jspx_th_clay_button_16.setLabel("Share");
    int _jspx_eval_clay_button_16 = _jspx_th_clay_button_16.doStartTag();
    if (_jspx_th_clay_button_16.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_clay_button_label_icon_nobody.reuse(_jspx_th_clay_button_16);
      return true;
    }
    _jspx_tagPool_clay_button_label_icon_nobody.reuse(_jspx_th_clay_button_16);
    return false;
  }
}
