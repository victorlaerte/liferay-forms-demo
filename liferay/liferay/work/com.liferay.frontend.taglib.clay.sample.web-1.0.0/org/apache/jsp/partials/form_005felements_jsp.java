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

public final class form_005felements_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(2);
    _jspx_dependants.add("/init.jsp");
    _jspx_dependants.add("/init-ext.jsp");
  }

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_clay_checkbox_showLabel_name_label_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_clay_radio_showLabel_name_label_checked_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_clay_select_options_name_multiple_label_disabled_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_clay_radio_showLabel_name_label_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_clay_checkbox_showLabel_name_label_disabled_checked_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_clay_select_options_name_label_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_clay_radio_showLabel_name_label_disabled_checked_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_clay_select_options_name_multiple_label_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_clay_checkbox_showLabel_name_label_indeterminate_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_clay_radio_showLabel_name_label_disabled_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_clay_checkbox_showLabel_name_label_checked_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1theme_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_clay_select_options_name_label_disabled_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_clay_checkbox_showLabel_name_label_disabled_nobody;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_clay_checkbox_showLabel_name_label_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_clay_radio_showLabel_name_label_checked_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_clay_select_options_name_multiple_label_disabled_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_clay_radio_showLabel_name_label_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_clay_checkbox_showLabel_name_label_disabled_checked_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_clay_select_options_name_label_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_clay_radio_showLabel_name_label_disabled_checked_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_clay_select_options_name_multiple_label_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_clay_checkbox_showLabel_name_label_indeterminate_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_clay_radio_showLabel_name_label_disabled_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_clay_checkbox_showLabel_name_label_checked_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1theme_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_clay_select_options_name_label_disabled_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_clay_checkbox_showLabel_name_label_disabled_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_clay_checkbox_showLabel_name_label_nobody.release();
    _jspx_tagPool_clay_radio_showLabel_name_label_checked_nobody.release();
    _jspx_tagPool_clay_select_options_name_multiple_label_disabled_nobody.release();
    _jspx_tagPool_clay_radio_showLabel_name_label_nobody.release();
    _jspx_tagPool_clay_checkbox_showLabel_name_label_disabled_checked_nobody.release();
    _jspx_tagPool_clay_select_options_name_label_nobody.release();
    _jspx_tagPool_clay_radio_showLabel_name_label_disabled_checked_nobody.release();
    _jspx_tagPool_clay_select_options_name_multiple_label_nobody.release();
    _jspx_tagPool_clay_checkbox_showLabel_name_label_indeterminate_nobody.release();
    _jspx_tagPool_clay_radio_showLabel_name_label_disabled_nobody.release();
    _jspx_tagPool_clay_checkbox_showLabel_name_label_checked_nobody.release();
    _jspx_tagPool_liferay$1theme_defineObjects_nobody.release();
    _jspx_tagPool_clay_select_options_name_label_disabled_nobody.release();
    _jspx_tagPool_clay_checkbox_showLabel_name_label_disabled_nobody.release();
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

      out.write("\n\n<h3>CHECKBOX</h3>\n\n<blockquote><p>A checkbox is a component that allows the user selecting something written in its associated text label. A list of consecutive checkboxes would allow the user to select multiple things.</p></blockquote>\n\n<table class=\"table\">\n\t<thead>\n\t\t<tr>\n\t\t\t<th>STATE</th>\n\t\t\t<th>DEFINITION</th>\n\t\t</tr>\n\t</thead>\n\n\t<tbody>\n\t\t<tr>\n\t\t\t<td>");
      //  clay:checkbox
      com.liferay.frontend.taglib.clay.servlet.taglib.soy.CheckboxTag _jspx_th_clay_checkbox_0 = (com.liferay.frontend.taglib.clay.servlet.taglib.soy.CheckboxTag) _jspx_tagPool_clay_checkbox_showLabel_name_label_checked_nobody.get(com.liferay.frontend.taglib.clay.servlet.taglib.soy.CheckboxTag.class);
      _jspx_th_clay_checkbox_0.setPageContext(_jspx_page_context);
      _jspx_th_clay_checkbox_0.setParent(null);
      _jspx_th_clay_checkbox_0.setChecked( true );
      _jspx_th_clay_checkbox_0.setLabel("My Input");
      _jspx_th_clay_checkbox_0.setName("name");
      _jspx_th_clay_checkbox_0.setShowLabel( false );
      int _jspx_eval_clay_checkbox_0 = _jspx_th_clay_checkbox_0.doStartTag();
      if (_jspx_th_clay_checkbox_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_clay_checkbox_showLabel_name_label_checked_nobody.reuse(_jspx_th_clay_checkbox_0);
        return;
      }
      _jspx_tagPool_clay_checkbox_showLabel_name_label_checked_nobody.reuse(_jspx_th_clay_checkbox_0);
      out.write("</td>\n\t\t\t<td>On</td>\n\t\t</tr>\n\t\t<tr>\n\t\t\t<td>");
      //  clay:checkbox
      com.liferay.frontend.taglib.clay.servlet.taglib.soy.CheckboxTag _jspx_th_clay_checkbox_1 = (com.liferay.frontend.taglib.clay.servlet.taglib.soy.CheckboxTag) _jspx_tagPool_clay_checkbox_showLabel_name_label_nobody.get(com.liferay.frontend.taglib.clay.servlet.taglib.soy.CheckboxTag.class);
      _jspx_th_clay_checkbox_1.setPageContext(_jspx_page_context);
      _jspx_th_clay_checkbox_1.setParent(null);
      _jspx_th_clay_checkbox_1.setLabel("My Input");
      _jspx_th_clay_checkbox_1.setName("name");
      _jspx_th_clay_checkbox_1.setShowLabel( false );
      int _jspx_eval_clay_checkbox_1 = _jspx_th_clay_checkbox_1.doStartTag();
      if (_jspx_th_clay_checkbox_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_clay_checkbox_showLabel_name_label_nobody.reuse(_jspx_th_clay_checkbox_1);
        return;
      }
      _jspx_tagPool_clay_checkbox_showLabel_name_label_nobody.reuse(_jspx_th_clay_checkbox_1);
      out.write("</td>\n\t\t\t<td>Off</td>\n\t\t</tr>\n\t\t<tr>\n\t\t\t<td>");
      //  clay:checkbox
      com.liferay.frontend.taglib.clay.servlet.taglib.soy.CheckboxTag _jspx_th_clay_checkbox_2 = (com.liferay.frontend.taglib.clay.servlet.taglib.soy.CheckboxTag) _jspx_tagPool_clay_checkbox_showLabel_name_label_disabled_checked_nobody.get(com.liferay.frontend.taglib.clay.servlet.taglib.soy.CheckboxTag.class);
      _jspx_th_clay_checkbox_2.setPageContext(_jspx_page_context);
      _jspx_th_clay_checkbox_2.setParent(null);
      _jspx_th_clay_checkbox_2.setChecked( true );
      _jspx_th_clay_checkbox_2.setDisabled( true );
      _jspx_th_clay_checkbox_2.setLabel("My Input");
      _jspx_th_clay_checkbox_2.setName("name");
      _jspx_th_clay_checkbox_2.setShowLabel( false );
      int _jspx_eval_clay_checkbox_2 = _jspx_th_clay_checkbox_2.doStartTag();
      if (_jspx_th_clay_checkbox_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_clay_checkbox_showLabel_name_label_disabled_checked_nobody.reuse(_jspx_th_clay_checkbox_2);
        return;
      }
      _jspx_tagPool_clay_checkbox_showLabel_name_label_disabled_checked_nobody.reuse(_jspx_th_clay_checkbox_2);
      out.write("</td>\n\t\t\t<td>On disabled</td>\n\t\t</tr>\n\t\t<tr>\n\t\t\t<td>");
      //  clay:checkbox
      com.liferay.frontend.taglib.clay.servlet.taglib.soy.CheckboxTag _jspx_th_clay_checkbox_3 = (com.liferay.frontend.taglib.clay.servlet.taglib.soy.CheckboxTag) _jspx_tagPool_clay_checkbox_showLabel_name_label_disabled_nobody.get(com.liferay.frontend.taglib.clay.servlet.taglib.soy.CheckboxTag.class);
      _jspx_th_clay_checkbox_3.setPageContext(_jspx_page_context);
      _jspx_th_clay_checkbox_3.setParent(null);
      _jspx_th_clay_checkbox_3.setDisabled( true );
      _jspx_th_clay_checkbox_3.setLabel("My Input");
      _jspx_th_clay_checkbox_3.setName("name");
      _jspx_th_clay_checkbox_3.setShowLabel( false );
      int _jspx_eval_clay_checkbox_3 = _jspx_th_clay_checkbox_3.doStartTag();
      if (_jspx_th_clay_checkbox_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_clay_checkbox_showLabel_name_label_disabled_nobody.reuse(_jspx_th_clay_checkbox_3);
        return;
      }
      _jspx_tagPool_clay_checkbox_showLabel_name_label_disabled_nobody.reuse(_jspx_th_clay_checkbox_3);
      out.write("</td>\n\t\t\t<td>Off disabled</td>\n\t\t</tr>\n\t\t<tr>\n\t\t\t<td>");
      //  clay:checkbox
      com.liferay.frontend.taglib.clay.servlet.taglib.soy.CheckboxTag _jspx_th_clay_checkbox_4 = (com.liferay.frontend.taglib.clay.servlet.taglib.soy.CheckboxTag) _jspx_tagPool_clay_checkbox_showLabel_name_label_indeterminate_nobody.get(com.liferay.frontend.taglib.clay.servlet.taglib.soy.CheckboxTag.class);
      _jspx_th_clay_checkbox_4.setPageContext(_jspx_page_context);
      _jspx_th_clay_checkbox_4.setParent(null);
      _jspx_th_clay_checkbox_4.setIndeterminate( true );
      _jspx_th_clay_checkbox_4.setLabel("My Input");
      _jspx_th_clay_checkbox_4.setName("name");
      _jspx_th_clay_checkbox_4.setShowLabel( false );
      int _jspx_eval_clay_checkbox_4 = _jspx_th_clay_checkbox_4.doStartTag();
      if (_jspx_th_clay_checkbox_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_clay_checkbox_showLabel_name_label_indeterminate_nobody.reuse(_jspx_th_clay_checkbox_4);
        return;
      }
      _jspx_tagPool_clay_checkbox_showLabel_name_label_indeterminate_nobody.reuse(_jspx_th_clay_checkbox_4);
      out.write("</td>\n\t\t\t<td>Checkbox Variable for multiple selection</td>\n\t\t</tr>\n\t</tbody>\n</table>\n\n<h3>RADIO</h3>\n\n<blockquote><p>A radio button is a component that allows the user selecting something written in its associated text label. A list of consecutive radio buttons would allow the user to select just one thing.</p></blockquote>\n\n<table class=\"table\">\n\t<thead>\n\t\t<tr>\n\t\t\t<th>STATE</th>\n\t\t\t<th>DEFINITION</th>\n\t\t</tr>\n\t</thead>\n\n\t<tbody>\n\t\t<tr>\n\t\t\t<td>");
      //  clay:radio
      com.liferay.frontend.taglib.clay.servlet.taglib.soy.RadioTag _jspx_th_clay_radio_0 = (com.liferay.frontend.taglib.clay.servlet.taglib.soy.RadioTag) _jspx_tagPool_clay_radio_showLabel_name_label_checked_nobody.get(com.liferay.frontend.taglib.clay.servlet.taglib.soy.RadioTag.class);
      _jspx_th_clay_radio_0.setPageContext(_jspx_page_context);
      _jspx_th_clay_radio_0.setParent(null);
      _jspx_th_clay_radio_0.setChecked( true );
      _jspx_th_clay_radio_0.setLabel("My Input");
      _jspx_th_clay_radio_0.setName("name");
      _jspx_th_clay_radio_0.setShowLabel( false );
      int _jspx_eval_clay_radio_0 = _jspx_th_clay_radio_0.doStartTag();
      if (_jspx_th_clay_radio_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_clay_radio_showLabel_name_label_checked_nobody.reuse(_jspx_th_clay_radio_0);
        return;
      }
      _jspx_tagPool_clay_radio_showLabel_name_label_checked_nobody.reuse(_jspx_th_clay_radio_0);
      out.write("</td>\n\t\t\t<td>On</td>\n\t\t</tr>\n\t\t<tr>\n\t\t\t<td>");
      //  clay:radio
      com.liferay.frontend.taglib.clay.servlet.taglib.soy.RadioTag _jspx_th_clay_radio_1 = (com.liferay.frontend.taglib.clay.servlet.taglib.soy.RadioTag) _jspx_tagPool_clay_radio_showLabel_name_label_nobody.get(com.liferay.frontend.taglib.clay.servlet.taglib.soy.RadioTag.class);
      _jspx_th_clay_radio_1.setPageContext(_jspx_page_context);
      _jspx_th_clay_radio_1.setParent(null);
      _jspx_th_clay_radio_1.setLabel("My Input");
      _jspx_th_clay_radio_1.setName("name");
      _jspx_th_clay_radio_1.setShowLabel( false );
      int _jspx_eval_clay_radio_1 = _jspx_th_clay_radio_1.doStartTag();
      if (_jspx_th_clay_radio_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_clay_radio_showLabel_name_label_nobody.reuse(_jspx_th_clay_radio_1);
        return;
      }
      _jspx_tagPool_clay_radio_showLabel_name_label_nobody.reuse(_jspx_th_clay_radio_1);
      out.write("</td>\n\t\t\t<td>Off</td>\n\t\t</tr>\n\t\t<tr>\n\t\t\t<td>");
      //  clay:radio
      com.liferay.frontend.taglib.clay.servlet.taglib.soy.RadioTag _jspx_th_clay_radio_2 = (com.liferay.frontend.taglib.clay.servlet.taglib.soy.RadioTag) _jspx_tagPool_clay_radio_showLabel_name_label_disabled_checked_nobody.get(com.liferay.frontend.taglib.clay.servlet.taglib.soy.RadioTag.class);
      _jspx_th_clay_radio_2.setPageContext(_jspx_page_context);
      _jspx_th_clay_radio_2.setParent(null);
      _jspx_th_clay_radio_2.setChecked( true );
      _jspx_th_clay_radio_2.setDisabled( true );
      _jspx_th_clay_radio_2.setLabel("My Input");
      _jspx_th_clay_radio_2.setName("name");
      _jspx_th_clay_radio_2.setShowLabel( false );
      int _jspx_eval_clay_radio_2 = _jspx_th_clay_radio_2.doStartTag();
      if (_jspx_th_clay_radio_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_clay_radio_showLabel_name_label_disabled_checked_nobody.reuse(_jspx_th_clay_radio_2);
        return;
      }
      _jspx_tagPool_clay_radio_showLabel_name_label_disabled_checked_nobody.reuse(_jspx_th_clay_radio_2);
      out.write("</td>\n\t\t\t<td>On disabled</td>\n\t\t</tr>\n\t\t<tr>\n\t\t\t<td>");
      //  clay:radio
      com.liferay.frontend.taglib.clay.servlet.taglib.soy.RadioTag _jspx_th_clay_radio_3 = (com.liferay.frontend.taglib.clay.servlet.taglib.soy.RadioTag) _jspx_tagPool_clay_radio_showLabel_name_label_disabled_nobody.get(com.liferay.frontend.taglib.clay.servlet.taglib.soy.RadioTag.class);
      _jspx_th_clay_radio_3.setPageContext(_jspx_page_context);
      _jspx_th_clay_radio_3.setParent(null);
      _jspx_th_clay_radio_3.setDisabled( true );
      _jspx_th_clay_radio_3.setLabel("My Input");
      _jspx_th_clay_radio_3.setName("name");
      _jspx_th_clay_radio_3.setShowLabel( false );
      int _jspx_eval_clay_radio_3 = _jspx_th_clay_radio_3.doStartTag();
      if (_jspx_th_clay_radio_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_clay_radio_showLabel_name_label_disabled_nobody.reuse(_jspx_th_clay_radio_3);
        return;
      }
      _jspx_tagPool_clay_radio_showLabel_name_label_disabled_nobody.reuse(_jspx_th_clay_radio_3);
      out.write("</td>\n\t\t\t<td>Off disabled</td>\n\t\t</tr>\n\t</tbody>\n</table>\n\n<h3>SELECTOR</h3>\n\n<blockquote><p>Selectors are frequently used as a part of forms. This elements are used when we need to select one or more within several options. These options are displayed in the button once selected.</p></blockquote>\n\n");

List<SelectOption> selectOptions = new ArrayList<>();

for (int i = 0; i < 8; i++) {
	selectOptions.add(new SelectOption("Sample " + i, String.valueOf(i)));
}

      out.write('\n');
      out.write('\n');
      //  clay:select
      com.liferay.frontend.taglib.clay.servlet.taglib.soy.SelectTag _jspx_th_clay_select_0 = (com.liferay.frontend.taglib.clay.servlet.taglib.soy.SelectTag) _jspx_tagPool_clay_select_options_name_label_nobody.get(com.liferay.frontend.taglib.clay.servlet.taglib.soy.SelectTag.class);
      _jspx_th_clay_select_0.setPageContext(_jspx_page_context);
      _jspx_th_clay_select_0.setParent(null);
      _jspx_th_clay_select_0.setLabel("Regular Select Element");
      _jspx_th_clay_select_0.setName("name");
      _jspx_th_clay_select_0.setOptions( selectOptions );
      int _jspx_eval_clay_select_0 = _jspx_th_clay_select_0.doStartTag();
      if (_jspx_th_clay_select_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_clay_select_options_name_label_nobody.reuse(_jspx_th_clay_select_0);
        return;
      }
      _jspx_tagPool_clay_select_options_name_label_nobody.reuse(_jspx_th_clay_select_0);
      out.write('\n');
      out.write('\n');
      //  clay:select
      com.liferay.frontend.taglib.clay.servlet.taglib.soy.SelectTag _jspx_th_clay_select_1 = (com.liferay.frontend.taglib.clay.servlet.taglib.soy.SelectTag) _jspx_tagPool_clay_select_options_name_label_disabled_nobody.get(com.liferay.frontend.taglib.clay.servlet.taglib.soy.SelectTag.class);
      _jspx_th_clay_select_1.setPageContext(_jspx_page_context);
      _jspx_th_clay_select_1.setParent(null);
      _jspx_th_clay_select_1.setDisabled( true );
      _jspx_th_clay_select_1.setLabel("Disabled Regular Select Element");
      _jspx_th_clay_select_1.setName("name");
      _jspx_th_clay_select_1.setOptions( selectOptions );
      int _jspx_eval_clay_select_1 = _jspx_th_clay_select_1.doStartTag();
      if (_jspx_th_clay_select_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_clay_select_options_name_label_disabled_nobody.reuse(_jspx_th_clay_select_1);
        return;
      }
      _jspx_tagPool_clay_select_options_name_label_disabled_nobody.reuse(_jspx_th_clay_select_1);
      out.write('\n');
      out.write('\n');
      //  clay:select
      com.liferay.frontend.taglib.clay.servlet.taglib.soy.SelectTag _jspx_th_clay_select_2 = (com.liferay.frontend.taglib.clay.servlet.taglib.soy.SelectTag) _jspx_tagPool_clay_select_options_name_multiple_label_nobody.get(com.liferay.frontend.taglib.clay.servlet.taglib.soy.SelectTag.class);
      _jspx_th_clay_select_2.setPageContext(_jspx_page_context);
      _jspx_th_clay_select_2.setParent(null);
      _jspx_th_clay_select_2.setLabel("Multiple Select Element");
      _jspx_th_clay_select_2.setMultiple( true );
      _jspx_th_clay_select_2.setName("name");
      _jspx_th_clay_select_2.setOptions( selectOptions );
      int _jspx_eval_clay_select_2 = _jspx_th_clay_select_2.doStartTag();
      if (_jspx_th_clay_select_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_clay_select_options_name_multiple_label_nobody.reuse(_jspx_th_clay_select_2);
        return;
      }
      _jspx_tagPool_clay_select_options_name_multiple_label_nobody.reuse(_jspx_th_clay_select_2);
      out.write('\n');
      out.write('\n');
      //  clay:select
      com.liferay.frontend.taglib.clay.servlet.taglib.soy.SelectTag _jspx_th_clay_select_3 = (com.liferay.frontend.taglib.clay.servlet.taglib.soy.SelectTag) _jspx_tagPool_clay_select_options_name_multiple_label_disabled_nobody.get(com.liferay.frontend.taglib.clay.servlet.taglib.soy.SelectTag.class);
      _jspx_th_clay_select_3.setPageContext(_jspx_page_context);
      _jspx_th_clay_select_3.setParent(null);
      _jspx_th_clay_select_3.setDisabled( true );
      _jspx_th_clay_select_3.setLabel("Disabled Multiple Select Element");
      _jspx_th_clay_select_3.setMultiple( true );
      _jspx_th_clay_select_3.setName("name");
      _jspx_th_clay_select_3.setOptions( selectOptions );
      int _jspx_eval_clay_select_3 = _jspx_th_clay_select_3.doStartTag();
      if (_jspx_th_clay_select_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_clay_select_options_name_multiple_label_disabled_nobody.reuse(_jspx_th_clay_select_3);
        return;
      }
      _jspx_tagPool_clay_select_options_name_multiple_label_disabled_nobody.reuse(_jspx_th_clay_select_3);
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
}
