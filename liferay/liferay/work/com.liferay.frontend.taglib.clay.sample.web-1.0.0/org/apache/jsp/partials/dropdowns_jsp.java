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

public final class dropdowns_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(2);
    _jspx_dependants.add("/init.jsp");
    _jspx_dependants.add("/init-ext.jsp");
  }

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_clay_dropdown$1menu_triggerCssClasses_style_label_itemsIconAlignment_dropdownItems_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_clay_dropdown$1menu_label_icon_dropdownItems_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1theme_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_clay_dropdown$1menu_searchable_label_dropdownItems_buttonLabel_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_clay_dropdown$1actions_helpText_dropdownItems_caption_buttonStyle_buttonLabel_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_clay_dropdown$1actions_dropdownItems_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_clay_dropdown$1actions_triggerCssClasses_helpText_dropdownItems_caption_buttonStyle_buttonLabel_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_clay_dropdown$1menu_label_dropdownItems_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_clay_dropdown$1menu_label_itemsIconAlignment_dropdownItems_nobody;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_clay_dropdown$1menu_triggerCssClasses_style_label_itemsIconAlignment_dropdownItems_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_clay_dropdown$1menu_label_icon_dropdownItems_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1theme_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_clay_dropdown$1menu_searchable_label_dropdownItems_buttonLabel_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_clay_dropdown$1actions_helpText_dropdownItems_caption_buttonStyle_buttonLabel_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_clay_dropdown$1actions_dropdownItems_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_clay_dropdown$1actions_triggerCssClasses_helpText_dropdownItems_caption_buttonStyle_buttonLabel_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_clay_dropdown$1menu_label_dropdownItems_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_clay_dropdown$1menu_label_itemsIconAlignment_dropdownItems_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_clay_dropdown$1menu_triggerCssClasses_style_label_itemsIconAlignment_dropdownItems_nobody.release();
    _jspx_tagPool_clay_dropdown$1menu_label_icon_dropdownItems_nobody.release();
    _jspx_tagPool_liferay$1theme_defineObjects_nobody.release();
    _jspx_tagPool_clay_dropdown$1menu_searchable_label_dropdownItems_buttonLabel_nobody.release();
    _jspx_tagPool_clay_dropdown$1actions_helpText_dropdownItems_caption_buttonStyle_buttonLabel_nobody.release();
    _jspx_tagPool_clay_dropdown$1actions_dropdownItems_nobody.release();
    _jspx_tagPool_clay_dropdown$1actions_triggerCssClasses_helpText_dropdownItems_caption_buttonStyle_buttonLabel_nobody.release();
    _jspx_tagPool_clay_dropdown$1menu_label_dropdownItems_nobody.release();
    _jspx_tagPool_clay_dropdown$1menu_label_itemsIconAlignment_dropdownItems_nobody.release();
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

      out.write("\n\n<h3>DROPDOWN MENU</h3>\n\n<blockquote><p>A dropdown is a list of options related to the element that triggers it.</p></blockquote>\n\n<div class=\"row\">\n\t<div class=\"col-md-2\">\n\t\t");
      //  clay:dropdown-menu
      com.liferay.frontend.taglib.clay.servlet.taglib.soy.DropdownMenuTag _jspx_th_clay_dropdown$1menu_0 = (com.liferay.frontend.taglib.clay.servlet.taglib.soy.DropdownMenuTag) _jspx_tagPool_clay_dropdown$1menu_label_dropdownItems_nobody.get(com.liferay.frontend.taglib.clay.servlet.taglib.soy.DropdownMenuTag.class);
      _jspx_th_clay_dropdown$1menu_0.setPageContext(_jspx_page_context);
      _jspx_th_clay_dropdown$1menu_0.setParent(null);
      _jspx_th_clay_dropdown$1menu_0.setDropdownItems( dropdownsDisplayContext.getDefaultDropdownItems() );
      _jspx_th_clay_dropdown$1menu_0.setLabel("Default");
      int _jspx_eval_clay_dropdown$1menu_0 = _jspx_th_clay_dropdown$1menu_0.doStartTag();
      if (_jspx_th_clay_dropdown$1menu_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_clay_dropdown$1menu_label_dropdownItems_nobody.reuse(_jspx_th_clay_dropdown$1menu_0);
        return;
      }
      _jspx_tagPool_clay_dropdown$1menu_label_dropdownItems_nobody.reuse(_jspx_th_clay_dropdown$1menu_0);
      out.write("\n\t</div>\n\n\t<div class=\"col-md-2\">\n\t\t");
      //  clay:dropdown-menu
      com.liferay.frontend.taglib.clay.servlet.taglib.soy.DropdownMenuTag _jspx_th_clay_dropdown$1menu_1 = (com.liferay.frontend.taglib.clay.servlet.taglib.soy.DropdownMenuTag) _jspx_tagPool_clay_dropdown$1menu_label_dropdownItems_nobody.get(com.liferay.frontend.taglib.clay.servlet.taglib.soy.DropdownMenuTag.class);
      _jspx_th_clay_dropdown$1menu_1.setPageContext(_jspx_page_context);
      _jspx_th_clay_dropdown$1menu_1.setParent(null);
      _jspx_th_clay_dropdown$1menu_1.setDropdownItems( dropdownsDisplayContext.getGroupDropdownItems() );
      _jspx_th_clay_dropdown$1menu_1.setLabel("Dividers");
      int _jspx_eval_clay_dropdown$1menu_1 = _jspx_th_clay_dropdown$1menu_1.doStartTag();
      if (_jspx_th_clay_dropdown$1menu_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_clay_dropdown$1menu_label_dropdownItems_nobody.reuse(_jspx_th_clay_dropdown$1menu_1);
        return;
      }
      _jspx_tagPool_clay_dropdown$1menu_label_dropdownItems_nobody.reuse(_jspx_th_clay_dropdown$1menu_1);
      out.write("\n\t</div>\n\n\t<div class=\"col-md-2\">\n\t\t");
      //  clay:dropdown-menu
      com.liferay.frontend.taglib.clay.servlet.taglib.soy.DropdownMenuTag _jspx_th_clay_dropdown$1menu_2 = (com.liferay.frontend.taglib.clay.servlet.taglib.soy.DropdownMenuTag) _jspx_tagPool_clay_dropdown$1menu_searchable_label_dropdownItems_buttonLabel_nobody.get(com.liferay.frontend.taglib.clay.servlet.taglib.soy.DropdownMenuTag.class);
      _jspx_th_clay_dropdown$1menu_2.setPageContext(_jspx_page_context);
      _jspx_th_clay_dropdown$1menu_2.setParent(null);
      _jspx_th_clay_dropdown$1menu_2.setButtonLabel("Done");
      _jspx_th_clay_dropdown$1menu_2.setDropdownItems( dropdownsDisplayContext.getInputDropdownItems() );
      _jspx_th_clay_dropdown$1menu_2.setLabel("Inputs");
      _jspx_th_clay_dropdown$1menu_2.setSearchable( true );
      int _jspx_eval_clay_dropdown$1menu_2 = _jspx_th_clay_dropdown$1menu_2.doStartTag();
      if (_jspx_th_clay_dropdown$1menu_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_clay_dropdown$1menu_searchable_label_dropdownItems_buttonLabel_nobody.reuse(_jspx_th_clay_dropdown$1menu_2);
        return;
      }
      _jspx_tagPool_clay_dropdown$1menu_searchable_label_dropdownItems_buttonLabel_nobody.reuse(_jspx_th_clay_dropdown$1menu_2);
      out.write("\n\t</div>\n\n\t<div class=\"col-md-2\">\n\t\t");
      //  clay:dropdown-menu
      com.liferay.frontend.taglib.clay.servlet.taglib.soy.DropdownMenuTag _jspx_th_clay_dropdown$1menu_3 = (com.liferay.frontend.taglib.clay.servlet.taglib.soy.DropdownMenuTag) _jspx_tagPool_clay_dropdown$1menu_label_icon_dropdownItems_nobody.get(com.liferay.frontend.taglib.clay.servlet.taglib.soy.DropdownMenuTag.class);
      _jspx_th_clay_dropdown$1menu_3.setPageContext(_jspx_page_context);
      _jspx_th_clay_dropdown$1menu_3.setParent(null);
      _jspx_th_clay_dropdown$1menu_3.setDropdownItems( dropdownsDisplayContext.getDefaultDropdownItems() );
      _jspx_th_clay_dropdown$1menu_3.setIcon("share");
      _jspx_th_clay_dropdown$1menu_3.setLabel("Icon");
      int _jspx_eval_clay_dropdown$1menu_3 = _jspx_th_clay_dropdown$1menu_3.doStartTag();
      if (_jspx_th_clay_dropdown$1menu_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_clay_dropdown$1menu_label_icon_dropdownItems_nobody.reuse(_jspx_th_clay_dropdown$1menu_3);
        return;
      }
      _jspx_tagPool_clay_dropdown$1menu_label_icon_dropdownItems_nobody.reuse(_jspx_th_clay_dropdown$1menu_3);
      out.write("\n\t</div>\n\n\t<div class=\"col-md-2\">\n\t\t");
      //  clay:dropdown-menu
      com.liferay.frontend.taglib.clay.servlet.taglib.soy.DropdownMenuTag _jspx_th_clay_dropdown$1menu_4 = (com.liferay.frontend.taglib.clay.servlet.taglib.soy.DropdownMenuTag) _jspx_tagPool_clay_dropdown$1menu_label_itemsIconAlignment_dropdownItems_nobody.get(com.liferay.frontend.taglib.clay.servlet.taglib.soy.DropdownMenuTag.class);
      _jspx_th_clay_dropdown$1menu_4.setPageContext(_jspx_page_context);
      _jspx_th_clay_dropdown$1menu_4.setParent(null);
      _jspx_th_clay_dropdown$1menu_4.setDropdownItems( dropdownsDisplayContext.getIconDropdownItems() );
      _jspx_th_clay_dropdown$1menu_4.setItemsIconAlignment("left");
      _jspx_th_clay_dropdown$1menu_4.setLabel("Icons");
      int _jspx_eval_clay_dropdown$1menu_4 = _jspx_th_clay_dropdown$1menu_4.doStartTag();
      if (_jspx_th_clay_dropdown$1menu_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_clay_dropdown$1menu_label_itemsIconAlignment_dropdownItems_nobody.reuse(_jspx_th_clay_dropdown$1menu_4);
        return;
      }
      _jspx_tagPool_clay_dropdown$1menu_label_itemsIconAlignment_dropdownItems_nobody.reuse(_jspx_th_clay_dropdown$1menu_4);
      out.write("\n\t</div>\n</div>\n\n<div class=\"row\">\n\t<div class=\"col-md-4\">\n\t\t");
      //  clay:dropdown-menu
      com.liferay.frontend.taglib.clay.servlet.taglib.soy.DropdownMenuTag _jspx_th_clay_dropdown$1menu_5 = (com.liferay.frontend.taglib.clay.servlet.taglib.soy.DropdownMenuTag) _jspx_tagPool_clay_dropdown$1menu_triggerCssClasses_style_label_itemsIconAlignment_dropdownItems_nobody.get(com.liferay.frontend.taglib.clay.servlet.taglib.soy.DropdownMenuTag.class);
      _jspx_th_clay_dropdown$1menu_5.setPageContext(_jspx_page_context);
      _jspx_th_clay_dropdown$1menu_5.setParent(null);
      _jspx_th_clay_dropdown$1menu_5.setDropdownItems( dropdownsDisplayContext.getDefaultDropdownItems() );
      _jspx_th_clay_dropdown$1menu_5.setItemsIconAlignment("left");
      _jspx_th_clay_dropdown$1menu_5.setLabel("Secondary Borderless");
      _jspx_th_clay_dropdown$1menu_5.setStyle("secondary");
      _jspx_th_clay_dropdown$1menu_5.setTriggerCssClasses("btn-outline-borderless");
      int _jspx_eval_clay_dropdown$1menu_5 = _jspx_th_clay_dropdown$1menu_5.doStartTag();
      if (_jspx_th_clay_dropdown$1menu_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_clay_dropdown$1menu_triggerCssClasses_style_label_itemsIconAlignment_dropdownItems_nobody.reuse(_jspx_th_clay_dropdown$1menu_5);
        return;
      }
      _jspx_tagPool_clay_dropdown$1menu_triggerCssClasses_style_label_itemsIconAlignment_dropdownItems_nobody.reuse(_jspx_th_clay_dropdown$1menu_5);
      out.write("\n\t</div>\n\n\t<div class=\"col-md-2\">\n\t\t");
      //  clay:dropdown-actions
      com.liferay.frontend.taglib.clay.servlet.taglib.soy.DropdownActionsTag _jspx_th_clay_dropdown$1actions_0 = (com.liferay.frontend.taglib.clay.servlet.taglib.soy.DropdownActionsTag) _jspx_tagPool_clay_dropdown$1actions_dropdownItems_nobody.get(com.liferay.frontend.taglib.clay.servlet.taglib.soy.DropdownActionsTag.class);
      _jspx_th_clay_dropdown$1actions_0.setPageContext(_jspx_page_context);
      _jspx_th_clay_dropdown$1actions_0.setParent(null);
      _jspx_th_clay_dropdown$1actions_0.setDropdownItems( dropdownsDisplayContext.getDefaultDropdownItems() );
      int _jspx_eval_clay_dropdown$1actions_0 = _jspx_th_clay_dropdown$1actions_0.doStartTag();
      if (_jspx_th_clay_dropdown$1actions_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_clay_dropdown$1actions_dropdownItems_nobody.reuse(_jspx_th_clay_dropdown$1actions_0);
        return;
      }
      _jspx_tagPool_clay_dropdown$1actions_dropdownItems_nobody.reuse(_jspx_th_clay_dropdown$1actions_0);
      out.write("\n\t</div>\n\n\t<div class=\"col-md-2\">\n\t\t");
      //  clay:dropdown-actions
      com.liferay.frontend.taglib.clay.servlet.taglib.soy.DropdownActionsTag _jspx_th_clay_dropdown$1actions_1 = (com.liferay.frontend.taglib.clay.servlet.taglib.soy.DropdownActionsTag) _jspx_tagPool_clay_dropdown$1actions_helpText_dropdownItems_caption_buttonStyle_buttonLabel_nobody.get(com.liferay.frontend.taglib.clay.servlet.taglib.soy.DropdownActionsTag.class);
      _jspx_th_clay_dropdown$1actions_1.setPageContext(_jspx_page_context);
      _jspx_th_clay_dropdown$1actions_1.setParent(null);
      _jspx_th_clay_dropdown$1actions_1.setButtonLabel("More");
      _jspx_th_clay_dropdown$1actions_1.setButtonStyle("secondary");
      _jspx_th_clay_dropdown$1actions_1.setCaption("Showing 4 of 32 Options");
      _jspx_th_clay_dropdown$1actions_1.setDropdownItems( dropdownsDisplayContext.getDefaultDropdownItems() );
      _jspx_th_clay_dropdown$1actions_1.setHelpText("You can customize this menu or see all you have by pressing \"more\".");
      int _jspx_eval_clay_dropdown$1actions_1 = _jspx_th_clay_dropdown$1actions_1.doStartTag();
      if (_jspx_th_clay_dropdown$1actions_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_clay_dropdown$1actions_helpText_dropdownItems_caption_buttonStyle_buttonLabel_nobody.reuse(_jspx_th_clay_dropdown$1actions_1);
        return;
      }
      _jspx_tagPool_clay_dropdown$1actions_helpText_dropdownItems_caption_buttonStyle_buttonLabel_nobody.reuse(_jspx_th_clay_dropdown$1actions_1);
      out.write("\n\t</div>\n\n\t<div class=\"col-md-2\">\n\t\t");
      //  clay:dropdown-actions
      com.liferay.frontend.taglib.clay.servlet.taglib.soy.DropdownActionsTag _jspx_th_clay_dropdown$1actions_2 = (com.liferay.frontend.taglib.clay.servlet.taglib.soy.DropdownActionsTag) _jspx_tagPool_clay_dropdown$1actions_triggerCssClasses_helpText_dropdownItems_caption_buttonStyle_buttonLabel_nobody.get(com.liferay.frontend.taglib.clay.servlet.taglib.soy.DropdownActionsTag.class);
      _jspx_th_clay_dropdown$1actions_2.setPageContext(_jspx_page_context);
      _jspx_th_clay_dropdown$1actions_2.setParent(null);
      _jspx_th_clay_dropdown$1actions_2.setButtonLabel("More");
      _jspx_th_clay_dropdown$1actions_2.setButtonStyle("secondary");
      _jspx_th_clay_dropdown$1actions_2.setCaption("Showing 4 of 32 Options");
      _jspx_th_clay_dropdown$1actions_2.setDropdownItems( dropdownsDisplayContext.getDefaultDropdownItems() );
      _jspx_th_clay_dropdown$1actions_2.setHelpText("You can customize this menu or see all you have by pressing \"more\".");
      _jspx_th_clay_dropdown$1actions_2.setTriggerCssClasses("btn-outline-borderless");
      int _jspx_eval_clay_dropdown$1actions_2 = _jspx_th_clay_dropdown$1actions_2.doStartTag();
      if (_jspx_th_clay_dropdown$1actions_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_clay_dropdown$1actions_triggerCssClasses_helpText_dropdownItems_caption_buttonStyle_buttonLabel_nobody.reuse(_jspx_th_clay_dropdown$1actions_2);
        return;
      }
      _jspx_tagPool_clay_dropdown$1actions_triggerCssClasses_helpText_dropdownItems_caption_buttonStyle_buttonLabel_nobody.reuse(_jspx_th_clay_dropdown$1actions_2);
      out.write("\n\t</div>\n</div>");
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
