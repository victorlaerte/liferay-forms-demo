package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.search.ResultRow;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.language.UnicodeLanguageUtil;
import com.liferay.portal.kernel.model.ModelHintsUtil;
import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.PortletLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.site.navigation.admin.web.internal.display.context.SiteNavigationAdminDisplayContext;
import com.liferay.site.navigation.admin.web.internal.security.permission.resource.SiteNavigationMenuPermission;
import com.liferay.site.navigation.constants.SiteNavigationConstants;
import com.liferay.site.navigation.exception.InvalidSiteNavigationMenuItemOrderException;
import com.liferay.site.navigation.exception.RequiredPrimarySiteNavigationMenuException;
import com.liferay.site.navigation.exception.SiteNavigationMenuItemNameException;
import com.liferay.site.navigation.model.SiteNavigationMenu;
import com.liferay.site.navigation.model.SiteNavigationMenuItem;
import com.liferay.site.navigation.service.SiteNavigationMenuItemLocalServiceUtil;
import com.liferay.site.navigation.type.SiteNavigationMenuItemType;
import com.liferay.site.navigation.type.SiteNavigationMenuItemTypeRegistry;
import com.liferay.taglib.aui.AUIUtil;
import com.liferay.taglib.servlet.PipingServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.portlet.ActionRequest;
import javax.portlet.PortletURL;

public final class edit_005fsite_005fnavigation_005fmenu_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(2);
    _jspx_dependants.add("/init.jsp");
    _jspx_dependants.add("/init-ext.jsp");
  }

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_namespace_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1util_param_value_name_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_error_exception;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_message_key_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_param_value_name_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_clay_dropdown$1menu_triggerCssClasses_style_icon_dropdownItems_buttonStyle_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1frontend_contextual$1sidebar_visible_namespace_id_header_componentId_body_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_script_require;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_otherwise;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1util_include_servletContext_page;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1frontend_empty$1result$1message_description_actionDropdownItems_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_renderURL_windowState;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_actionURL_name;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_error_message_key_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1theme_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_icon_markupView_image_cssClass_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_choose;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1frontend_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_icon_markupView_image_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_when_test;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_portlet_namespace_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1util_param_value_name_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_error_exception = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_message_key_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_param_value_name_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_clay_dropdown$1menu_triggerCssClasses_style_icon_dropdownItems_buttonStyle_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1frontend_contextual$1sidebar_visible_namespace_id_header_componentId_body_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_script_require = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_otherwise = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1util_include_servletContext_page = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1frontend_empty$1result$1message_description_actionDropdownItems_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_renderURL_windowState = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_actionURL_name = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_error_message_key_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1theme_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_icon_markupView_image_cssClass_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_choose = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1frontend_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_icon_markupView_image_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_when_test = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_portlet_namespace_nobody.release();
    _jspx_tagPool_liferay$1util_param_value_name_nobody.release();
    _jspx_tagPool_liferay$1ui_error_exception.release();
    _jspx_tagPool_liferay$1ui_message_key_nobody.release();
    _jspx_tagPool_portlet_param_value_name_nobody.release();
    _jspx_tagPool_clay_dropdown$1menu_triggerCssClasses_style_icon_dropdownItems_buttonStyle_nobody.release();
    _jspx_tagPool_liferay$1frontend_contextual$1sidebar_visible_namespace_id_header_componentId_body_nobody.release();
    _jspx_tagPool_aui_script_require.release();
    _jspx_tagPool_c_otherwise.release();
    _jspx_tagPool_portlet_defineObjects_nobody.release();
    _jspx_tagPool_liferay$1util_include_servletContext_page.release();
    _jspx_tagPool_liferay$1frontend_empty$1result$1message_description_actionDropdownItems_nobody.release();
    _jspx_tagPool_portlet_renderURL_windowState.release();
    _jspx_tagPool_portlet_actionURL_name.release();
    _jspx_tagPool_liferay$1ui_error_message_key_nobody.release();
    _jspx_tagPool_liferay$1theme_defineObjects_nobody.release();
    _jspx_tagPool_aui_icon_markupView_image_cssClass_nobody.release();
    _jspx_tagPool_c_choose.release();
    _jspx_tagPool_liferay$1frontend_defineObjects_nobody.release();
    _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody.release();
    _jspx_tagPool_aui_icon_markupView_image_nobody.release();
    _jspx_tagPool_c_when_test.release();
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
      out.write("\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
      //  liferay-frontend:defineObjects
      com.liferay.frontend.taglib.servlet.taglib.DefineObjectsTag _jspx_th_liferay$1frontend_defineObjects_0 = (com.liferay.frontend.taglib.servlet.taglib.DefineObjectsTag) _jspx_tagPool_liferay$1frontend_defineObjects_nobody.get(com.liferay.frontend.taglib.servlet.taglib.DefineObjectsTag.class);
      _jspx_th_liferay$1frontend_defineObjects_0.setPageContext(_jspx_page_context);
      _jspx_th_liferay$1frontend_defineObjects_0.setParent(null);
      int _jspx_eval_liferay$1frontend_defineObjects_0 = _jspx_th_liferay$1frontend_defineObjects_0.doStartTag();
      if (_jspx_th_liferay$1frontend_defineObjects_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_liferay$1frontend_defineObjects_nobody.reuse(_jspx_th_liferay$1frontend_defineObjects_0);
        return;
      }
      _jspx_tagPool_liferay$1frontend_defineObjects_nobody.reuse(_jspx_th_liferay$1frontend_defineObjects_0);
      java.lang.String currentURL = null;
      javax.portlet.PortletURL currentURLObj = null;
      java.util.ResourceBundle resourceBundle = null;
      javax.portlet.WindowState windowState = null;
      currentURL = (java.lang.String) _jspx_page_context.findAttribute("currentURL");
      currentURLObj = (javax.portlet.PortletURL) _jspx_page_context.findAttribute("currentURLObj");
      resourceBundle = (java.util.ResourceBundle) _jspx_page_context.findAttribute("resourceBundle");
      windowState = (javax.portlet.WindowState) _jspx_page_context.findAttribute("windowState");
      out.write('\n');
      out.write('\n');
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
      //  portlet:defineObjects
      com.liferay.taglib.portlet.DefineObjectsTag _jspx_th_portlet_defineObjects_0 = (com.liferay.taglib.portlet.DefineObjectsTag) _jspx_tagPool_portlet_defineObjects_nobody.get(com.liferay.taglib.portlet.DefineObjectsTag.class);
      _jspx_th_portlet_defineObjects_0.setPageContext(_jspx_page_context);
      _jspx_th_portlet_defineObjects_0.setParent(null);
      int _jspx_eval_portlet_defineObjects_0 = _jspx_th_portlet_defineObjects_0.doStartTag();
      if (_jspx_th_portlet_defineObjects_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_portlet_defineObjects_nobody.reuse(_jspx_th_portlet_defineObjects_0);
        return;
      }
      _jspx_tagPool_portlet_defineObjects_nobody.reuse(_jspx_th_portlet_defineObjects_0);
      javax.portlet.ActionRequest actionRequest = null;
      javax.portlet.ActionResponse actionResponse = null;
      javax.portlet.EventRequest eventRequest = null;
      javax.portlet.EventResponse eventResponse = null;
      com.liferay.portal.kernel.portlet.LiferayPortletRequest liferayPortletRequest = null;
      com.liferay.portal.kernel.portlet.LiferayPortletResponse liferayPortletResponse = null;
      javax.portlet.PortletConfig portletConfig = null;
      java.lang.String portletName = null;
      javax.portlet.PortletPreferences portletPreferences = null;
      java.util.Map portletPreferencesValues = null;
      javax.portlet.PortletSession portletSession = null;
      java.util.Map portletSessionScope = null;
      javax.portlet.RenderRequest renderRequest = null;
      javax.portlet.RenderResponse renderResponse = null;
      javax.portlet.ResourceRequest resourceRequest = null;
      javax.portlet.ResourceResponse resourceResponse = null;
      actionRequest = (javax.portlet.ActionRequest) _jspx_page_context.findAttribute("actionRequest");
      actionResponse = (javax.portlet.ActionResponse) _jspx_page_context.findAttribute("actionResponse");
      eventRequest = (javax.portlet.EventRequest) _jspx_page_context.findAttribute("eventRequest");
      eventResponse = (javax.portlet.EventResponse) _jspx_page_context.findAttribute("eventResponse");
      liferayPortletRequest = (com.liferay.portal.kernel.portlet.LiferayPortletRequest) _jspx_page_context.findAttribute("liferayPortletRequest");
      liferayPortletResponse = (com.liferay.portal.kernel.portlet.LiferayPortletResponse) _jspx_page_context.findAttribute("liferayPortletResponse");
      portletConfig = (javax.portlet.PortletConfig) _jspx_page_context.findAttribute("portletConfig");
      portletName = (java.lang.String) _jspx_page_context.findAttribute("portletName");
      portletPreferences = (javax.portlet.PortletPreferences) _jspx_page_context.findAttribute("portletPreferences");
      portletPreferencesValues = (java.util.Map) _jspx_page_context.findAttribute("portletPreferencesValues");
      portletSession = (javax.portlet.PortletSession) _jspx_page_context.findAttribute("portletSession");
      portletSessionScope = (java.util.Map) _jspx_page_context.findAttribute("portletSessionScope");
      renderRequest = (javax.portlet.RenderRequest) _jspx_page_context.findAttribute("renderRequest");
      renderResponse = (javax.portlet.RenderResponse) _jspx_page_context.findAttribute("renderResponse");
      resourceRequest = (javax.portlet.ResourceRequest) _jspx_page_context.findAttribute("resourceRequest");
      resourceResponse = (javax.portlet.ResourceResponse) _jspx_page_context.findAttribute("resourceResponse");
      out.write('\n');
      out.write('\n');
      out.write('\n');
      out.write('\n');

SiteNavigationAdminDisplayContext siteNavigationAdminDisplayContext = new SiteNavigationAdminDisplayContext(liferayPortletRequest, liferayPortletResponse, request);
SiteNavigationMenuItemTypeRegistry siteNavigationMenuItemTypeRegistry = siteNavigationAdminDisplayContext.getSiteNavigationMenuItemTypeRegistry();

      out.write('\n');
      out.write('\n');

String redirect = ParamUtil.getString(request, "redirect");

portletDisplay.setShowBackIcon(true);
portletDisplay.setURLBack(redirect);

renderResponse.setTitle(siteNavigationAdminDisplayContext.getSiteNavigationMenuName());

      out.write("\n\n<nav class=\"management-bar management-bar-light navbar navbar-expand-md site-navigation-management-bar\">\n\t<div class=\"container\">\n\t\t<ul class=\"navbar-nav\"></ul>\n\n\t\t<ul class=\"navbar-nav\">\n\t\t\t<li class=\"nav-item\">\n\t\t\t\t<button class=\"btn btn-unstyled nav-link nav-link-monospaced\" id=\"");
      if (_jspx_meth_portlet_namespace_0(_jspx_page_context))
        return;
      out.write("showSiteNavigationMenuSettings\" type=\"button\">\n\t\t\t\t\t");
      if (_jspx_meth_aui_icon_0(_jspx_page_context))
        return;
      out.write("\n\t\t\t\t</button>\n\t\t\t</li>\n\t\t\t<li class=\"nav-item\">\n\t\t\t\t");
      //  clay:dropdown-menu
      com.liferay.frontend.taglib.clay.servlet.taglib.soy.DropdownMenuTag _jspx_th_clay_dropdown$1menu_0 = (com.liferay.frontend.taglib.clay.servlet.taglib.soy.DropdownMenuTag) _jspx_tagPool_clay_dropdown$1menu_triggerCssClasses_style_icon_dropdownItems_buttonStyle_nobody.get(com.liferay.frontend.taglib.clay.servlet.taglib.soy.DropdownMenuTag.class);
      _jspx_th_clay_dropdown$1menu_0.setPageContext(_jspx_page_context);
      _jspx_th_clay_dropdown$1menu_0.setParent(null);
      _jspx_th_clay_dropdown$1menu_0.setButtonStyle("primary");
      _jspx_th_clay_dropdown$1menu_0.setDropdownItems( siteNavigationAdminDisplayContext.getAddSiteNavigationMenuItemDropdownItems() );
      _jspx_th_clay_dropdown$1menu_0.setIcon("plus");
      _jspx_th_clay_dropdown$1menu_0.setStyle("primary");
      _jspx_th_clay_dropdown$1menu_0.setTriggerCssClasses("nav-btn nav-btn-monospaced");
      int _jspx_eval_clay_dropdown$1menu_0 = _jspx_th_clay_dropdown$1menu_0.doStartTag();
      if (_jspx_th_clay_dropdown$1menu_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_clay_dropdown$1menu_triggerCssClasses_style_icon_dropdownItems_buttonStyle_nobody.reuse(_jspx_th_clay_dropdown$1menu_0);
        return;
      }
      _jspx_tagPool_clay_dropdown$1menu_triggerCssClasses_style_icon_dropdownItems_buttonStyle_nobody.reuse(_jspx_th_clay_dropdown$1menu_0);
      out.write("\n\t\t\t</li>\n\t\t</ul>\n\t</div>\n</nav>\n\n");
      //  liferay-ui:error
      com.liferay.taglib.ui.ErrorTag _jspx_th_liferay$1ui_error_0 = (com.liferay.taglib.ui.ErrorTag) _jspx_tagPool_liferay$1ui_error_message_key_nobody.get(com.liferay.taglib.ui.ErrorTag.class);
      _jspx_th_liferay$1ui_error_0.setPageContext(_jspx_page_context);
      _jspx_th_liferay$1ui_error_0.setParent(null);
      _jspx_th_liferay$1ui_error_0.setKey( InvalidSiteNavigationMenuItemOrderException.class.getName() );
      _jspx_th_liferay$1ui_error_0.setMessage("the-order-of-site-navigation-menu-items-is-invalid");
      int _jspx_eval_liferay$1ui_error_0 = _jspx_th_liferay$1ui_error_0.doStartTag();
      if (_jspx_th_liferay$1ui_error_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_liferay$1ui_error_message_key_nobody.reuse(_jspx_th_liferay$1ui_error_0);
        return;
      }
      _jspx_tagPool_liferay$1ui_error_message_key_nobody.reuse(_jspx_th_liferay$1ui_error_0);
      out.write('\n');
      out.write('\n');
      //  liferay-ui:error
      com.liferay.taglib.ui.ErrorTag _jspx_th_liferay$1ui_error_1 = (com.liferay.taglib.ui.ErrorTag) _jspx_tagPool_liferay$1ui_error_exception.get(com.liferay.taglib.ui.ErrorTag.class);
      _jspx_th_liferay$1ui_error_1.setPageContext(_jspx_page_context);
      _jspx_th_liferay$1ui_error_1.setParent(null);
      _jspx_th_liferay$1ui_error_1.setException( SiteNavigationMenuItemNameException.class );
      int _jspx_eval_liferay$1ui_error_1 = _jspx_th_liferay$1ui_error_1.doStartTag();
      if (_jspx_eval_liferay$1ui_error_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        java.lang.Object errorException = null;
        if (_jspx_eval_liferay$1ui_error_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
          out = _jspx_page_context.pushBody();
          _jspx_th_liferay$1ui_error_1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
          _jspx_th_liferay$1ui_error_1.doInitBody();
        }
        errorException = (java.lang.Object) _jspx_page_context.findAttribute("errorException");
        do {
          out.write('\n');
          out.write('	');
          //  liferay-ui:message
          com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_0 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody.get(com.liferay.taglib.ui.MessageTag.class);
          _jspx_th_liferay$1ui_message_0.setPageContext(_jspx_page_context);
          _jspx_th_liferay$1ui_message_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_error_1);
          _jspx_th_liferay$1ui_message_0.setArguments( ModelHintsUtil.getMaxLength(SiteNavigationMenuItem.class.getName(), "name") );
          _jspx_th_liferay$1ui_message_0.setKey("please-enter-a-name-with-fewer-than-x-characters");
          _jspx_th_liferay$1ui_message_0.setTranslateArguments( false );
          int _jspx_eval_liferay$1ui_message_0 = _jspx_th_liferay$1ui_message_0.doStartTag();
          if (_jspx_th_liferay$1ui_message_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody.reuse(_jspx_th_liferay$1ui_message_0);
            return;
          }
          _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody.reuse(_jspx_th_liferay$1ui_message_0);
          out.write('\n');
          int evalDoAfterBody = _jspx_th_liferay$1ui_error_1.doAfterBody();
          errorException = (java.lang.Object) _jspx_page_context.findAttribute("errorException");
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
        if (_jspx_eval_liferay$1ui_error_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
          out = _jspx_page_context.popBody();
      }
      if (_jspx_th_liferay$1ui_error_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_liferay$1ui_error_exception.reuse(_jspx_th_liferay$1ui_error_1);
        return;
      }
      _jspx_tagPool_liferay$1ui_error_exception.reuse(_jspx_th_liferay$1ui_error_1);
      out.write("\n\n<div class=\"container-fluid-1280 contextual-sidebar-content site-navigation-content\">\n\t<div class=\"lfr-search-container-wrapper site-navigation-menu-container\">\n\n\t\t");

		List<SiteNavigationMenuItem> siteNavigationMenuItems = SiteNavigationMenuItemLocalServiceUtil.getSiteNavigationMenuItems(siteNavigationAdminDisplayContext.getSiteNavigationMenuId(), 0);
		
      out.write("\n\n\t\t");
      //  c:choose
      com.liferay.taglib.core.ChooseTag _jspx_th_c_choose_0 = (com.liferay.taglib.core.ChooseTag) _jspx_tagPool_c_choose.get(com.liferay.taglib.core.ChooseTag.class);
      _jspx_th_c_choose_0.setPageContext(_jspx_page_context);
      _jspx_th_c_choose_0.setParent(null);
      int _jspx_eval_c_choose_0 = _jspx_th_c_choose_0.doStartTag();
      if (_jspx_eval_c_choose_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        out.write("\n\t\t\t");
        //  c:when
        com.liferay.taglib.core.WhenTag _jspx_th_c_when_0 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
        _jspx_th_c_when_0.setPageContext(_jspx_page_context);
        _jspx_th_c_when_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_0);
        _jspx_th_c_when_0.setTest( siteNavigationMenuItems.size() > 0 );
        int _jspx_eval_c_when_0 = _jspx_th_c_when_0.doStartTag();
        if (_jspx_eval_c_when_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          out.write("\n\t\t\t\t<div class=\"hide\" data-site-navigation-menu-item-id=\"0\"></div>\n\n\t\t\t\t");

				for (SiteNavigationMenuItem siteNavigationMenuItem : siteNavigationMenuItems) {
				
          out.write("\n\n\t\t\t\t\t");
          //  liferay-util:include
          com.liferay.taglib.util.IncludeTag _jspx_th_liferay$1util_include_0 = (com.liferay.taglib.util.IncludeTag) _jspx_tagPool_liferay$1util_include_servletContext_page.get(com.liferay.taglib.util.IncludeTag.class);
          _jspx_th_liferay$1util_include_0.setPageContext(_jspx_page_context);
          _jspx_th_liferay$1util_include_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_0);
          _jspx_th_liferay$1util_include_0.setPage("/view_site_navigation_menu_item.jsp");
          _jspx_th_liferay$1util_include_0.setServletContext( application );
          int _jspx_eval_liferay$1util_include_0 = _jspx_th_liferay$1util_include_0.doStartTag();
          if (_jspx_eval_liferay$1util_include_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t\t\t\t\t");
            //  liferay-util:param
            com.liferay.taglib.util.ParamTag _jspx_th_liferay$1util_param_0 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_liferay$1util_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
            _jspx_th_liferay$1util_param_0.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1util_param_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1util_include_0);
            _jspx_th_liferay$1util_param_0.setName("siteNavigationMenuItemId");
            _jspx_th_liferay$1util_param_0.setValue( String.valueOf(siteNavigationMenuItem.getSiteNavigationMenuItemId()) );
            int _jspx_eval_liferay$1util_param_0 = _jspx_th_liferay$1util_param_0.doStartTag();
            if (_jspx_th_liferay$1util_param_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_liferay$1util_param_value_name_nobody.reuse(_jspx_th_liferay$1util_param_0);
              return;
            }
            _jspx_tagPool_liferay$1util_param_value_name_nobody.reuse(_jspx_th_liferay$1util_param_0);
            out.write("\n\t\t\t\t\t");
          }
          if (_jspx_th_liferay$1util_include_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_liferay$1util_include_servletContext_page.reuse(_jspx_th_liferay$1util_include_0);
            return;
          }
          _jspx_tagPool_liferay$1util_include_servletContext_page.reuse(_jspx_th_liferay$1util_include_0);
          out.write("\n\n\t\t\t\t");

				}
				
          out.write("\n\n\t\t\t");
        }
        if (_jspx_th_c_when_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_0);
          return;
        }
        _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_0);
        out.write("\n\t\t\t");
        //  c:otherwise
        com.liferay.taglib.core.OtherwiseTag _jspx_th_c_otherwise_0 = (com.liferay.taglib.core.OtherwiseTag) _jspx_tagPool_c_otherwise.get(com.liferay.taglib.core.OtherwiseTag.class);
        _jspx_th_c_otherwise_0.setPageContext(_jspx_page_context);
        _jspx_th_c_otherwise_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_0);
        int _jspx_eval_c_otherwise_0 = _jspx_th_c_otherwise_0.doStartTag();
        if (_jspx_eval_c_otherwise_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          out.write("\n\t\t\t\t");
          //  liferay-frontend:empty-result-message
          com.liferay.frontend.taglib.servlet.taglib.EmptyResultMessageTag _jspx_th_liferay$1frontend_empty$1result$1message_0 = (com.liferay.frontend.taglib.servlet.taglib.EmptyResultMessageTag) _jspx_tagPool_liferay$1frontend_empty$1result$1message_description_actionDropdownItems_nobody.get(com.liferay.frontend.taglib.servlet.taglib.EmptyResultMessageTag.class);
          _jspx_th_liferay$1frontend_empty$1result$1message_0.setPageContext(_jspx_page_context);
          _jspx_th_liferay$1frontend_empty$1result$1message_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_0);
          _jspx_th_liferay$1frontend_empty$1result$1message_0.setActionDropdownItems( siteNavigationAdminDisplayContext.getAddSiteNavigationMenuItemDropdownItems() );
          _jspx_th_liferay$1frontend_empty$1result$1message_0.setDescription( LanguageUtil.get(request, "fortunately-it-is-very-easy-to-add-new-ones") );
          int _jspx_eval_liferay$1frontend_empty$1result$1message_0 = _jspx_th_liferay$1frontend_empty$1result$1message_0.doStartTag();
          if (_jspx_th_liferay$1frontend_empty$1result$1message_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_liferay$1frontend_empty$1result$1message_description_actionDropdownItems_nobody.reuse(_jspx_th_liferay$1frontend_empty$1result$1message_0);
            return;
          }
          _jspx_tagPool_liferay$1frontend_empty$1result$1message_description_actionDropdownItems_nobody.reuse(_jspx_th_liferay$1frontend_empty$1result$1message_0);
          out.write("\n\t\t\t");
        }
        if (_jspx_th_c_otherwise_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_0);
          return;
        }
        _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_0);
        out.write("\n\t\t");
      }
      if (_jspx_th_c_choose_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_0);
        return;
      }
      _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_0);
      out.write("\n\t</div>\n</div>\n\n");
      //  liferay-frontend:contextual-sidebar
      com.liferay.frontend.taglib.servlet.taglib.soy.ContextualSidebarTag _jspx_th_liferay$1frontend_contextual$1sidebar_0 = (com.liferay.frontend.taglib.servlet.taglib.soy.ContextualSidebarTag) _jspx_tagPool_liferay$1frontend_contextual$1sidebar_visible_namespace_id_header_componentId_body_nobody.get(com.liferay.frontend.taglib.servlet.taglib.soy.ContextualSidebarTag.class);
      _jspx_th_liferay$1frontend_contextual$1sidebar_0.setPageContext(_jspx_page_context);
      _jspx_th_liferay$1frontend_contextual$1sidebar_0.setParent(null);
      _jspx_th_liferay$1frontend_contextual$1sidebar_0.setBody( StringPool.BLANK );
      _jspx_th_liferay$1frontend_contextual$1sidebar_0.setComponentId( renderResponse.getNamespace() + "sidebar" );
      _jspx_th_liferay$1frontend_contextual$1sidebar_0.setHeader( StringPool.BLANK );
      _jspx_th_liferay$1frontend_contextual$1sidebar_0.setId( renderResponse.getNamespace() + "sidebar" );
      _jspx_th_liferay$1frontend_contextual$1sidebar_0.setNamespace( renderResponse.getNamespace() );
      _jspx_th_liferay$1frontend_contextual$1sidebar_0.setVisible( false );
      int _jspx_eval_liferay$1frontend_contextual$1sidebar_0 = _jspx_th_liferay$1frontend_contextual$1sidebar_0.doStartTag();
      if (_jspx_th_liferay$1frontend_contextual$1sidebar_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_liferay$1frontend_contextual$1sidebar_visible_namespace_id_header_componentId_body_nobody.reuse(_jspx_th_liferay$1frontend_contextual$1sidebar_0);
        return;
      }
      _jspx_tagPool_liferay$1frontend_contextual$1sidebar_visible_namespace_id_header_componentId_body_nobody.reuse(_jspx_th_liferay$1frontend_contextual$1sidebar_0);
      out.write('\n');
      out.write('\n');
      //  aui:script
      com.liferay.taglib.aui.ScriptTag _jspx_th_aui_script_0 = (com.liferay.taglib.aui.ScriptTag) _jspx_tagPool_aui_script_require.get(com.liferay.taglib.aui.ScriptTag.class);
      _jspx_th_aui_script_0.setPageContext(_jspx_page_context);
      _jspx_th_aui_script_0.setParent(null);
      _jspx_th_aui_script_0.setRequire("metal-dom/src/all/dom as dom");
      int _jspx_eval_aui_script_0 = _jspx_th_aui_script_0.doStartTag();
      if (_jspx_eval_aui_script_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        if (_jspx_eval_aui_script_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
          out = _jspx_page_context.pushBody();
          _jspx_th_aui_script_0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
          _jspx_th_aui_script_0.doInitBody();
        }
        do {
          out.write("\n\tvar addMenuItemClickHandler = dom.delegate(\n\t\tdocument.body,\n\t\t'click',\n\t\t'.dropdown-item',\n\t\tfunction(event) {\n\t\t\tLiferay.Util.openInDialog(\n\t\t\t\tevent,\n\t\t\t\t{\n\t\t\t\t\tdialog: {\n\t\t\t\t\t\tdestroyOnHide: true\n\t\t\t\t\t},\n\t\t\t\t\tdialogIframe: {\n\t\t\t\t\t\tbodyCssClass: 'dialog-with-footer'\n\t\t\t\t\t},\n\t\t\t\t\tid: '");
          if (_jspx_meth_portlet_namespace_1((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_0, _jspx_page_context))
            return;
          out.write("addMenuItem',\n\t\t\t\t\ttitle: event.delegateTarget.title || event.delegateTarget.innerText,\n\t\t\t\t\turi: event.delegateTarget.href\n\t\t\t\t}\n\t\t\t);\n\t\t}\n\t);\n\n\tvar destroyAddMenuItemClickHandler = function() {\n\t\tif (addMenuItemClickHandler) {\n\t\t\taddMenuItemClickHandler.removeListener();\n\n\t\t\taddMenuItemClickHandler = null;\n\t\t}\n\n\t\tLiferay.detach('");
          out.print( portletDisplay.getId() );
          out.write(":portletRefreshed', destroyAddMenuItemClickHandler);\n\t\tLiferay.detach('destroyPortlet', destroyAddMenuItemClickHandler);\n\t};\n\n\tLiferay.on('");
          out.print( portletDisplay.getId() );
          out.write(":portletRefreshed', destroyAddMenuItemClickHandler);\n\tLiferay.on('destroyPortlet', destroyAddMenuItemClickHandler);\n");
          int evalDoAfterBody = _jspx_th_aui_script_0.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
        if (_jspx_eval_aui_script_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
          out = _jspx_page_context.popBody();
      }
      if (_jspx_th_aui_script_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_aui_script_require.reuse(_jspx_th_aui_script_0);
        return;
      }
      _jspx_tagPool_aui_script_require.reuse(_jspx_th_aui_script_0);
      out.write('\n');
      out.write('\n');
      //  aui:script
      com.liferay.taglib.aui.ScriptTag _jspx_th_aui_script_1 = (com.liferay.taglib.aui.ScriptTag) _jspx_tagPool_aui_script_require.get(com.liferay.taglib.aui.ScriptTag.class);
      _jspx_th_aui_script_1.setPageContext(_jspx_page_context);
      _jspx_th_aui_script_1.setParent(null);
      _jspx_th_aui_script_1.setRequire("site-navigation-menu-web/js/SiteNavigationMenuEditor.es as siteNavigationMenuEditorModule, site-navigation-menu-web/js/SiteNavigationMenuItemDOMHandler.es as siteNavigationMenuItemDOMHandlerModule");
      int _jspx_eval_aui_script_1 = _jspx_th_aui_script_1.doStartTag();
      if (_jspx_eval_aui_script_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        if (_jspx_eval_aui_script_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
          out = _jspx_page_context.pushBody();
          _jspx_th_aui_script_1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
          _jspx_th_aui_script_1.doInitBody();
        }
        do {
          out.write("\n\tvar changed = false;\n\tvar showSiteNavigationMenuSettingsButtonClickHandler = null;\n\tvar sidebar = null;\n\tvar sidebarBodyChangeHandler = null;\n\tvar siteNavigationMenuEditor = null;\n\tvar siteNavigationMenuItemRemoveButtonClickHandler = null;\n\tvar siteNavigationMenuItemRemoveButtonKeyupHandler = null;\n\n\tvar closeSidebar = function() {\n\t\tlet form = document.querySelector('#");
          if (_jspx_meth_portlet_namespace_2((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
            return;
          out.write("fm');\n\t\tlet error = form ? form.querySelector(\"[role='alert']\") : null;\n\n\t\tvar saveChanges = false;\n\n\t\tif (changed) {\n\t\t\tif (!error) {\n\t\t\t\tsaveChanges = confirm(\n\t\t\t\t\t'");
          if (_jspx_meth_liferay$1ui_message_1((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
            return;
          out.write("'\n\t\t\t\t);\n\t\t\t}\n\t\t}\n\n\t\tif (saveChanges) {\n\t\t\tAUI().use(\n\t\t\t\t['aui-base'],\n\t\t\t\tfunction(A) {\n\t\t\t\t\tvar form = A.one('#");
          if (_jspx_meth_portlet_namespace_3((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
            return;
          out.write("sidebarBody form');\n\n\t\t\t\t\tif (form) {\n\t\t\t\t\t\tform.submit();\n\t\t\t\t\t}\n\t\t\t\t}\n\t\t\t);\n\t\t}\n\t\telse {\n\t\t\tif (!error) {\n\t\t\t\tif (sidebarBodyChangeHandler) {\n\t\t\t\t\tsidebarBodyChangeHandler.detach();\n\n\t\t\t\t\tsidebarBodyChangeHandler = null;\n\t\t\t\t}\n\n\t\t\t\tsidebar.body = '';\n\t\t\t\tsidebar.visible = false;\n\n\t\t\t\tchanged = false;\n\t\t\t}\n\t\t}\n\n\t\treturn !saveChanges;\n\t};\n\n\tvar handleSelectedMenuItemChanged = function(event) {\n\t\tconst siteNavigationMenuItem = event.newVal;\n\n\t\tif (!closeSidebar() || !siteNavigationMenuItem) {\n\t\t\treturn;\n\t\t}\n\n\t\tvar data = Liferay.Util.ns(\n\t\t\t'");
          if (_jspx_meth_portlet_namespace_4((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
            return;
          out.write("',\n\t\t\t{\n\t\t\t\tredirect: '");
          out.print( currentURL );
          out.write("',\n\t\t\t\tsiteNavigationMenuItemId: siteNavigationMenuItem.dataset.siteNavigationMenuItemId\n\t\t\t}\n\t\t);\n\n\t\topenSidebar(siteNavigationMenuItem.dataset.title);\n\n\t\tAUI().use(\n\t\t\t['aui-base'],\n\t\t\tfunction(A) {\n\t\t\t\tA.io.request(\n\t\t\t\t\t'");
          //  portlet:renderURL
          com.liferay.taglib.portlet.RenderURLTag _jspx_th_portlet_renderURL_0 = (com.liferay.taglib.portlet.RenderURLTag) _jspx_tagPool_portlet_renderURL_windowState.get(com.liferay.taglib.portlet.RenderURLTag.class);
          _jspx_th_portlet_renderURL_0.setPageContext(_jspx_page_context);
          _jspx_th_portlet_renderURL_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
          _jspx_th_portlet_renderURL_0.setWindowState( LiferayWindowState.EXCLUSIVE.toString() );
          int _jspx_eval_portlet_renderURL_0 = _jspx_th_portlet_renderURL_0.doStartTag();
          if (_jspx_eval_portlet_renderURL_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            if (_jspx_meth_portlet_param_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_portlet_renderURL_0, _jspx_page_context))
              return;
          }
          if (_jspx_th_portlet_renderURL_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_portlet_renderURL_windowState.reuse(_jspx_th_portlet_renderURL_0);
            return;
          }
          _jspx_tagPool_portlet_renderURL_windowState.reuse(_jspx_th_portlet_renderURL_0);
          out.write("',\n\t\t\t\t\t{\n\t\t\t\t\t\tdata: data,\n\t\t\t\t\t\ton: {\n\t\t\t\t\t\t\tsuccess: function(event, id, obj) {\n\t\t\t\t\t\t\t\tvar responseData = this.get('responseData');\n\n\t\t\t\t\t\t\t\tsetSidebarBody(responseData);\n\t\t\t\t\t\t\t}\n\t\t\t\t\t\t}\n\t\t\t\t\t}\n\t\t\t\t);\n\t\t\t}\n\t\t);\n\t};\n\n\tvar handlePortletDestroy = function() {\n\t\tsidebar.dispose();\n\t\tsiteNavigationMenuEditor.dispose();\n\t\tsiteNavigationMenuItemRemoveButtonClickHandler.detach();\n\t\tsiteNavigationMenuItemRemoveButtonKeyupHandler.detach();\n\t\tshowSiteNavigationMenuSettingsButtonClickHandler.detach();\n\n\t\tsidebar = null;\n\t\tsiteNavigationMenuEditor = null;\n\t\tsiteNavigationMenuItemRemoveButtonClickHandler = null;\n\t\tsiteNavigationMenuItemRemoveButtonKeyupHandler = null;\n\t\tshowSiteNavigationMenuSettingsButtonClickHandler = null;\n\n\t\tLiferay.detach('");
          out.print( portletDisplay.getId() );
          out.write(":portletRefreshed', handlePortletDestroy);\n\t\tLiferay.detach('destroyPortlet', handlePortletDestroy);\n\t};\n\n\tvar handleShowSiteNavigationMenuSettingsButtonClick = function(event) {\n\t\tif (!closeSidebar()) {\n\t\t\tevent.stopPropagation();\n\n\t\t\treturn;\n\t\t}\n\n\t\tvar data = Liferay.Util.ns(\n\t\t\t'");
          if (_jspx_meth_portlet_namespace_5((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
            return;
          out.write("',\n\t\t\t{\n\t\t\t\tredirect: '");
          out.print( currentURL );
          out.write("',\n\t\t\t\tsiteNavigationMenuId: ");
          out.print( siteNavigationAdminDisplayContext.getSiteNavigationMenuId() );
          out.write("\n\t\t\t}\n\t\t);\n\n\t\tsiteNavigationMenuItemDOMHandlerModule.unselectAll();\n\n\t\topenSidebar('");
          out.print( HtmlUtil.escape(siteNavigationAdminDisplayContext.getSiteNavigationMenuName()) );
          out.write("');\n\n\t\tAUI().use(\n\t\t\t['aui-base'],\n\t\t\tfunction(A) {\n\t\t\t\tA.io.request(\n\t\t\t\t\t'");
          //  portlet:renderURL
          com.liferay.taglib.portlet.RenderURLTag _jspx_th_portlet_renderURL_1 = (com.liferay.taglib.portlet.RenderURLTag) _jspx_tagPool_portlet_renderURL_windowState.get(com.liferay.taglib.portlet.RenderURLTag.class);
          _jspx_th_portlet_renderURL_1.setPageContext(_jspx_page_context);
          _jspx_th_portlet_renderURL_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
          _jspx_th_portlet_renderURL_1.setWindowState( LiferayWindowState.EXCLUSIVE.toString() );
          int _jspx_eval_portlet_renderURL_1 = _jspx_th_portlet_renderURL_1.doStartTag();
          if (_jspx_eval_portlet_renderURL_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            if (_jspx_meth_portlet_param_1((javax.servlet.jsp.tagext.JspTag) _jspx_th_portlet_renderURL_1, _jspx_page_context))
              return;
          }
          if (_jspx_th_portlet_renderURL_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_portlet_renderURL_windowState.reuse(_jspx_th_portlet_renderURL_1);
            return;
          }
          _jspx_tagPool_portlet_renderURL_windowState.reuse(_jspx_th_portlet_renderURL_1);
          out.write("',\n\t\t\t\t\t{\n\t\t\t\t\t\tdata: data,\n\t\t\t\t\t\ton: {\n\t\t\t\t\t\t\tsuccess: function(event, id, obj) {\n\t\t\t\t\t\t\t\tvar responseData = this.get('responseData');\n\n\t\t\t\t\t\t\t\tsetSidebarBody(responseData);\n\t\t\t\t\t\t\t}\n\t\t\t\t\t\t}\n\t\t\t\t\t}\n\t\t\t\t);\n\t\t\t}\n\t\t);\n\t};\n\n\tvar handleSidebarBodyChange = function() {\n\t\tchanged = true;\n\t};\n\n\tvar handleSidebarCloseButtonClick = function() {\n\t\tcloseSidebar();\n\t\tsiteNavigationMenuItemDOMHandlerModule.unselectAll();\n\t};\n\n\tvar handleSiteNavigationMenuItemRemoveIconClick = function(event) {\n\t\tevent.stopPropagation();\n\t};\n\n\tvar handleSiteNavigationMenuItemRemoveIconKeyup = function(event) {\n\t\tif (event.which === 32) {\n\t\t\tevent.preventDefault();\n\t\t\tevent.stopPropagation();\n\t\t\tevent.target.getDOMNode().click();\n\t\t}\n\t};\n\n\tvar openSidebar = function(title) {\n\t\tsidebar.body = '<div id=\"");
          if (_jspx_meth_portlet_namespace_6((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
            return;
          out.write("sidebarBody\"><div class=\"loading-animation\"></div></div>';\n\t\tsidebar.header = '<div class=\"autofit-row sidebar-section\"><div class=\"autofit-col autofit-col-expand\"><h4 class=\"component-title\"><span class=\"text-truncate-inline\"><span class=\"text-truncate\">' + title + '</span></span></h4></div><div class=\"autofit-col\"><button class=\"btn btn-monospaced btn-unstyled\" id=\"");
          if (_jspx_meth_portlet_namespace_7((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
            return;
          out.write("sidebarHeaderButton\" type=\"button\"><span class=\"icon-monospaced\">");
          if (_jspx_meth_aui_icon_1((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
            return;
          out.write("</span></button></div></div>';\n\t\tsidebar.visible = true;\n\t};\n\n\tvar setSidebarBody = function(content) {\n\t\tAUI().use(\n\t\t\t['aui-base', 'aui-parse-content'],\n\t\t\tfunction(A) {\n\t\t\t\tvar sidebarBody = A.one('#");
          if (_jspx_meth_portlet_namespace_8((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
            return;
          out.write("sidebarBody');\n\t\t\t\tvar sidebarHeaderButton = A.one('#");
          if (_jspx_meth_portlet_namespace_9((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
            return;
          out.write("sidebarHeaderButton');\n\n\t\t\t\tif (sidebarBody) {\n\t\t\t\t\tsidebarBody.plug(A.Plugin.ParseContent);\n\n\t\t\t\t\tsidebarBody.setContent(content);\n\t\t\t\t\tsidebarBodyChangeHandler = sidebarBody.on('change', handleSidebarBodyChange);\n\t\t\t\t}\n\n\t\t\t\tif (sidebarHeaderButton) {\n\t\t\t\t\tsidebarHeaderButton.on('click', handleSidebarCloseButtonClick);\n\t\t\t\t}\n\t\t\t}\n\t\t);\n\t};\n\n\tLiferay.componentReady(\n\t\t'");
          if (_jspx_meth_portlet_namespace_10((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
            return;
          out.write("sidebar'\n\t)\n\t.then(\n\t\tfunction(_sidebar) {\n\t\t\tsidebar = _sidebar;\n\n\t\t\tsidebar.on('hide', closeSidebar);\n\n\t\t\tsiteNavigationMenuEditor = new siteNavigationMenuEditorModule.default(\n\t\t\t\t{\n\t\t\t\t\teditSiteNavigationMenuItemParentURL: '");
          //  portlet:actionURL
          com.liferay.taglib.portlet.ActionURLTag _jspx_th_portlet_actionURL_0 = (com.liferay.taglib.portlet.ActionURLTag) _jspx_tagPool_portlet_actionURL_name.get(com.liferay.taglib.portlet.ActionURLTag.class);
          _jspx_th_portlet_actionURL_0.setPageContext(_jspx_page_context);
          _jspx_th_portlet_actionURL_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
          _jspx_th_portlet_actionURL_0.setName("/navigation_menu/edit_site_navigation_menu_item_parent");
          int _jspx_eval_portlet_actionURL_0 = _jspx_th_portlet_actionURL_0.doStartTag();
          if (_jspx_eval_portlet_actionURL_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            //  portlet:param
            com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_2 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
            _jspx_th_portlet_param_2.setPageContext(_jspx_page_context);
            _jspx_th_portlet_param_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_actionURL_0);
            _jspx_th_portlet_param_2.setName("redirect");
            _jspx_th_portlet_param_2.setValue( currentURL );
            int _jspx_eval_portlet_param_2 = _jspx_th_portlet_param_2.doStartTag();
            if (_jspx_th_portlet_param_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_2);
              return;
            }
            _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_2);
          }
          if (_jspx_th_portlet_actionURL_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_portlet_actionURL_name.reuse(_jspx_th_portlet_actionURL_0);
            return;
          }
          _jspx_tagPool_portlet_actionURL_name.reuse(_jspx_th_portlet_actionURL_0);
          out.write("',\n\t\t\t\t\tnamespace: '");
          if (_jspx_meth_portlet_namespace_11((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
            return;
          out.write("'\n\t\t\t\t}\n\t\t\t);\n\n\t\t\tsiteNavigationMenuEditor.on(\n\t\t\t\t'selectedMenuItemChanged',\n\t\t\t\thandleSelectedMenuItemChanged\n\t\t\t);\n\n\t\t\tAUI().use(\n\t\t\t\t['aui-base'],\n\t\t\t\tfunction(A) {\n\t\t\t\t\tsiteNavigationMenuItemRemoveButtonClickHandler = A\n\t\t\t\t\t\t.all('.site-navigation-menu-item__remove-icon')\n\t\t\t\t\t\t.on('click', handleSiteNavigationMenuItemRemoveIconClick);\n\n\t\t\t\t\tsiteNavigationMenuItemRemoveButtonKeyupHandler = A\n\t\t\t\t\t\t.all('.site-navigation-menu-item__remove-icon')\n\t\t\t\t\t\t.on('keyup', handleSiteNavigationMenuItemRemoveIconKeyup);\n\n\t\t\t\t\tshowSiteNavigationMenuSettingsButtonClickHandler = A\n\t\t\t\t\t\t.one('#");
          if (_jspx_meth_portlet_namespace_12((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
            return;
          out.write("showSiteNavigationMenuSettings')\n\t\t\t\t\t\t.on('click', handleShowSiteNavigationMenuSettingsButtonClick);\n\t\t\t\t}\n\t\t\t);\n\n\t\t\tLiferay.on('");
          out.print( portletDisplay.getId() );
          out.write(":portletRefreshed', handlePortletDestroy);\n\t\t\tLiferay.on('destroyPortlet', handlePortletDestroy);\n\t\t}\n\t);\n");
          int evalDoAfterBody = _jspx_th_aui_script_1.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
        if (_jspx_eval_aui_script_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
          out = _jspx_page_context.popBody();
      }
      if (_jspx_th_aui_script_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_aui_script_require.reuse(_jspx_th_aui_script_1);
        return;
      }
      _jspx_tagPool_aui_script_require.reuse(_jspx_th_aui_script_1);
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

  private boolean _jspx_meth_portlet_namespace_0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_0 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_0.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_0.setParent(null);
    int _jspx_eval_portlet_namespace_0 = _jspx_th_portlet_namespace_0.doStartTag();
    if (_jspx_th_portlet_namespace_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_0);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_0);
    return false;
  }

  private boolean _jspx_meth_aui_icon_0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:icon
    com.liferay.taglib.aui.IconTag _jspx_th_aui_icon_0 = (com.liferay.taglib.aui.IconTag) _jspx_tagPool_aui_icon_markupView_image_cssClass_nobody.get(com.liferay.taglib.aui.IconTag.class);
    _jspx_th_aui_icon_0.setPageContext(_jspx_page_context);
    _jspx_th_aui_icon_0.setParent(null);
    _jspx_th_aui_icon_0.setCssClass("icon-monospaced");
    _jspx_th_aui_icon_0.setImage("cog");
    _jspx_th_aui_icon_0.setMarkupView("lexicon");
    int _jspx_eval_aui_icon_0 = _jspx_th_aui_icon_0.doStartTag();
    if (_jspx_th_aui_icon_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_icon_markupView_image_cssClass_nobody.reuse(_jspx_th_aui_icon_0);
      return true;
    }
    _jspx_tagPool_aui_icon_markupView_image_cssClass_nobody.reuse(_jspx_th_aui_icon_0);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_1(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_1 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_1.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_0);
    int _jspx_eval_portlet_namespace_1 = _jspx_th_portlet_namespace_1.doStartTag();
    if (_jspx_th_portlet_namespace_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_1);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_1);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_2(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_2 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_2.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    int _jspx_eval_portlet_namespace_2 = _jspx_th_portlet_namespace_2.doStartTag();
    if (_jspx_th_portlet_namespace_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_2);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_2);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_1(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_1 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_1.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    _jspx_th_liferay$1ui_message_1.setKey("you-have-unsaved-changes.-do-you-want-to-save-them");
    int _jspx_eval_liferay$1ui_message_1 = _jspx_th_liferay$1ui_message_1.doStartTag();
    if (_jspx_th_liferay$1ui_message_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_1);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_1);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_3(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_3 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_3.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    int _jspx_eval_portlet_namespace_3 = _jspx_th_portlet_namespace_3.doStartTag();
    if (_jspx_th_portlet_namespace_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_3);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_3);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_4(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_4 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_4.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    int _jspx_eval_portlet_namespace_4 = _jspx_th_portlet_namespace_4.doStartTag();
    if (_jspx_th_portlet_namespace_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_4);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_4);
    return false;
  }

  private boolean _jspx_meth_portlet_param_0(javax.servlet.jsp.tagext.JspTag _jspx_th_portlet_renderURL_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:param
    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_0 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
    _jspx_th_portlet_param_0.setPageContext(_jspx_page_context);
    _jspx_th_portlet_param_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_0);
    _jspx_th_portlet_param_0.setName("mvcPath");
    _jspx_th_portlet_param_0.setValue("/edit_site_navigation_menu_item.jsp");
    int _jspx_eval_portlet_param_0 = _jspx_th_portlet_param_0.doStartTag();
    if (_jspx_th_portlet_param_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_0);
      return true;
    }
    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_0);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_5(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_5 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_5.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    int _jspx_eval_portlet_namespace_5 = _jspx_th_portlet_namespace_5.doStartTag();
    if (_jspx_th_portlet_namespace_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_5);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_5);
    return false;
  }

  private boolean _jspx_meth_portlet_param_1(javax.servlet.jsp.tagext.JspTag _jspx_th_portlet_renderURL_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:param
    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_1 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
    _jspx_th_portlet_param_1.setPageContext(_jspx_page_context);
    _jspx_th_portlet_param_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_1);
    _jspx_th_portlet_param_1.setName("mvcPath");
    _jspx_th_portlet_param_1.setValue("/site_navigation_menu_settings.jsp");
    int _jspx_eval_portlet_param_1 = _jspx_th_portlet_param_1.doStartTag();
    if (_jspx_th_portlet_param_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_1);
      return true;
    }
    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_1);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_6(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_6 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_6.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    int _jspx_eval_portlet_namespace_6 = _jspx_th_portlet_namespace_6.doStartTag();
    if (_jspx_th_portlet_namespace_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_6);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_6);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_7(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_7 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_7.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    int _jspx_eval_portlet_namespace_7 = _jspx_th_portlet_namespace_7.doStartTag();
    if (_jspx_th_portlet_namespace_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_7);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_7);
    return false;
  }

  private boolean _jspx_meth_aui_icon_1(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:icon
    com.liferay.taglib.aui.IconTag _jspx_th_aui_icon_1 = (com.liferay.taglib.aui.IconTag) _jspx_tagPool_aui_icon_markupView_image_nobody.get(com.liferay.taglib.aui.IconTag.class);
    _jspx_th_aui_icon_1.setPageContext(_jspx_page_context);
    _jspx_th_aui_icon_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    _jspx_th_aui_icon_1.setImage("times");
    _jspx_th_aui_icon_1.setMarkupView("lexicon");
    int _jspx_eval_aui_icon_1 = _jspx_th_aui_icon_1.doStartTag();
    if (_jspx_th_aui_icon_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_icon_markupView_image_nobody.reuse(_jspx_th_aui_icon_1);
      return true;
    }
    _jspx_tagPool_aui_icon_markupView_image_nobody.reuse(_jspx_th_aui_icon_1);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_8(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_8 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_8.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_8.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    int _jspx_eval_portlet_namespace_8 = _jspx_th_portlet_namespace_8.doStartTag();
    if (_jspx_th_portlet_namespace_8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_8);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_8);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_9(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_9 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_9.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_9.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    int _jspx_eval_portlet_namespace_9 = _jspx_th_portlet_namespace_9.doStartTag();
    if (_jspx_th_portlet_namespace_9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_9);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_9);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_10(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_10 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_10.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_10.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    int _jspx_eval_portlet_namespace_10 = _jspx_th_portlet_namespace_10.doStartTag();
    if (_jspx_th_portlet_namespace_10.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_10);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_10);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_11(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_11 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_11.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_11.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    int _jspx_eval_portlet_namespace_11 = _jspx_th_portlet_namespace_11.doStartTag();
    if (_jspx_th_portlet_namespace_11.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_11);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_11);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_12(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_12 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_12.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_12.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    int _jspx_eval_portlet_namespace_12 = _jspx_th_portlet_namespace_12.doStartTag();
    if (_jspx_th_portlet_namespace_12.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_12);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_12);
    return false;
  }
}
