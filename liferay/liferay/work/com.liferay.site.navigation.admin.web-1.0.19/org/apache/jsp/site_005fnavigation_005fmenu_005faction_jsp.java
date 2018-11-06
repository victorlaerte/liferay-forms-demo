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

public final class site_005fnavigation_005fmenu_005faction_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(2);
    _jspx_dependants.add("/init.jsp");
    _jspx_dependants.add("/init-ext.jsp");
  }

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_actionURL_var_name;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_icon_url_onClick_message_markupView_iconCssClass_icon_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_param_value_name_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_message_key_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1portlet_actionURL_var_name;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_if_test;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_renderURL_var;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1security_permissionsURL_windowState_var_resourcePrimKey_modelResourceDescription_modelResource_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_icon_url_onClick_message_markupView_iconCssClass_icon_cssClass_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_icon_useDialog_url_method_message_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1portlet_param_value_name_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1theme_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1frontend_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_icon_url_message_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_icon$1menu_showWhenSingleIcon_message_markupView_icon_direction_cssClass;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_icon$1delete_url_trash_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_icon_url_message_data_cssClass_nobody;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_portlet_actionURL_var_name = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_icon_url_onClick_message_markupView_iconCssClass_icon_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_param_value_name_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_message_key_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1portlet_actionURL_var_name = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_if_test = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_renderURL_var = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1security_permissionsURL_windowState_var_resourcePrimKey_modelResourceDescription_modelResource_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_icon_url_onClick_message_markupView_iconCssClass_icon_cssClass_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_icon_useDialog_url_method_message_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1portlet_param_value_name_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1theme_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1frontend_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_icon_url_message_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_icon$1menu_showWhenSingleIcon_message_markupView_icon_direction_cssClass = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_icon$1delete_url_trash_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_icon_url_message_data_cssClass_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_portlet_actionURL_var_name.release();
    _jspx_tagPool_liferay$1ui_icon_url_onClick_message_markupView_iconCssClass_icon_nobody.release();
    _jspx_tagPool_portlet_param_value_name_nobody.release();
    _jspx_tagPool_liferay$1ui_message_key_nobody.release();
    _jspx_tagPool_liferay$1portlet_actionURL_var_name.release();
    _jspx_tagPool_c_if_test.release();
    _jspx_tagPool_portlet_renderURL_var.release();
    _jspx_tagPool_liferay$1security_permissionsURL_windowState_var_resourcePrimKey_modelResourceDescription_modelResource_nobody.release();
    _jspx_tagPool_liferay$1ui_icon_url_onClick_message_markupView_iconCssClass_icon_cssClass_nobody.release();
    _jspx_tagPool_portlet_defineObjects_nobody.release();
    _jspx_tagPool_liferay$1ui_icon_useDialog_url_method_message_nobody.release();
    _jspx_tagPool_liferay$1portlet_param_value_name_nobody.release();
    _jspx_tagPool_liferay$1theme_defineObjects_nobody.release();
    _jspx_tagPool_liferay$1frontend_defineObjects_nobody.release();
    _jspx_tagPool_liferay$1ui_icon_url_message_nobody.release();
    _jspx_tagPool_liferay$1ui_icon$1menu_showWhenSingleIcon_message_markupView_icon_direction_cssClass.release();
    _jspx_tagPool_liferay$1ui_icon$1delete_url_trash_nobody.release();
    _jspx_tagPool_liferay$1ui_icon_url_message_data_cssClass_nobody.release();
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

ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

SiteNavigationMenu siteNavigationMenu = (SiteNavigationMenu)row.getObject();

PortletURL portletURL = renderResponse.createRenderURL();

      out.write('\n');
      out.write('\n');
      //  liferay-ui:icon-menu
      com.liferay.taglib.ui.IconMenuTag _jspx_th_liferay$1ui_icon$1menu_0 = (com.liferay.taglib.ui.IconMenuTag) _jspx_tagPool_liferay$1ui_icon$1menu_showWhenSingleIcon_message_markupView_icon_direction_cssClass.get(com.liferay.taglib.ui.IconMenuTag.class);
      _jspx_th_liferay$1ui_icon$1menu_0.setPageContext(_jspx_page_context);
      _jspx_th_liferay$1ui_icon$1menu_0.setParent(null);
      _jspx_th_liferay$1ui_icon$1menu_0.setCssClass("dropdown-menu-indicator-end");
      _jspx_th_liferay$1ui_icon$1menu_0.setDirection("left-side");
      _jspx_th_liferay$1ui_icon$1menu_0.setIcon( StringPool.BLANK );
      _jspx_th_liferay$1ui_icon$1menu_0.setMarkupView("lexicon");
      _jspx_th_liferay$1ui_icon$1menu_0.setMessage( StringPool.BLANK );
      _jspx_th_liferay$1ui_icon$1menu_0.setShowWhenSingleIcon( true );
      int _jspx_eval_liferay$1ui_icon$1menu_0 = _jspx_th_liferay$1ui_icon$1menu_0.doStartTag();
      if (_jspx_eval_liferay$1ui_icon$1menu_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        if (_jspx_eval_liferay$1ui_icon$1menu_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
          out = _jspx_page_context.pushBody();
          _jspx_th_liferay$1ui_icon$1menu_0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
          _jspx_th_liferay$1ui_icon$1menu_0.doInitBody();
        }
        do {
          out.write('\n');
          out.write('	');
          //  c:if
          com.liferay.taglib.core.IfTag _jspx_th_c_if_0 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
          _jspx_th_c_if_0.setPageContext(_jspx_page_context);
          _jspx_th_c_if_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_icon$1menu_0);
          _jspx_th_c_if_0.setTest( SiteNavigationMenuPermission.contains(permissionChecker, siteNavigationMenu, ActionKeys.UPDATE) && siteNavigationAdminDisplayContext.hasEditPermission() );
          int _jspx_eval_c_if_0 = _jspx_th_c_if_0.doStartTag();
          if (_jspx_eval_c_if_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t");
            //  portlet:renderURL
            com.liferay.taglib.portlet.RenderURLTag _jspx_th_portlet_renderURL_0 = (com.liferay.taglib.portlet.RenderURLTag) _jspx_tagPool_portlet_renderURL_var.get(com.liferay.taglib.portlet.RenderURLTag.class);
            _jspx_th_portlet_renderURL_0.setPageContext(_jspx_page_context);
            _jspx_th_portlet_renderURL_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_0);
            _jspx_th_portlet_renderURL_0.setVar("editSiteNavigationMenuURL");
            int _jspx_eval_portlet_renderURL_0 = _jspx_th_portlet_renderURL_0.doStartTag();
            if (_jspx_eval_portlet_renderURL_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\t\t\t");
              if (_jspx_meth_portlet_param_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_portlet_renderURL_0, _jspx_page_context))
                return;
              out.write("\n\t\t\t");
              //  portlet:param
              com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_1 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
              _jspx_th_portlet_param_1.setPageContext(_jspx_page_context);
              _jspx_th_portlet_param_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_0);
              _jspx_th_portlet_param_1.setName("redirect");
              _jspx_th_portlet_param_1.setValue( portletURL.toString() );
              int _jspx_eval_portlet_param_1 = _jspx_th_portlet_param_1.doStartTag();
              if (_jspx_th_portlet_param_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_1);
                return;
              }
              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_1);
              out.write("\n\t\t\t");
              //  portlet:param
              com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_2 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
              _jspx_th_portlet_param_2.setPageContext(_jspx_page_context);
              _jspx_th_portlet_param_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_0);
              _jspx_th_portlet_param_2.setName("siteNavigationMenuId");
              _jspx_th_portlet_param_2.setValue( String.valueOf(siteNavigationMenu.getSiteNavigationMenuId()) );
              int _jspx_eval_portlet_param_2 = _jspx_th_portlet_param_2.doStartTag();
              if (_jspx_th_portlet_param_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_2);
                return;
              }
              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_2);
              out.write("\n\t\t");
            }
            if (_jspx_th_portlet_renderURL_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_portlet_renderURL_var.reuse(_jspx_th_portlet_renderURL_0);
              return;
            }
            _jspx_tagPool_portlet_renderURL_var.reuse(_jspx_th_portlet_renderURL_0);
            java.lang.String editSiteNavigationMenuURL = null;
            editSiteNavigationMenuURL = (java.lang.String) _jspx_page_context.findAttribute("editSiteNavigationMenuURL");
            out.write("\n\n\t\t");
            //  liferay-ui:icon
            com.liferay.taglib.ui.IconTag _jspx_th_liferay$1ui_icon_0 = (com.liferay.taglib.ui.IconTag) _jspx_tagPool_liferay$1ui_icon_url_message_nobody.get(com.liferay.taglib.ui.IconTag.class);
            _jspx_th_liferay$1ui_icon_0.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1ui_icon_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_0);
            _jspx_th_liferay$1ui_icon_0.setMessage("edit");
            _jspx_th_liferay$1ui_icon_0.setUrl( editSiteNavigationMenuURL );
            int _jspx_eval_liferay$1ui_icon_0 = _jspx_th_liferay$1ui_icon_0.doStartTag();
            if (_jspx_th_liferay$1ui_icon_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_liferay$1ui_icon_url_message_nobody.reuse(_jspx_th_liferay$1ui_icon_0);
              return;
            }
            _jspx_tagPool_liferay$1ui_icon_url_message_nobody.reuse(_jspx_th_liferay$1ui_icon_0);
            out.write('\n');
            out.write('	');
          }
          if (_jspx_th_c_if_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
            return;
          }
          _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
          out.write("\n\n\t");
          //  c:if
          com.liferay.taglib.core.IfTag _jspx_th_c_if_1 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
          _jspx_th_c_if_1.setPageContext(_jspx_page_context);
          _jspx_th_c_if_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_icon$1menu_0);
          _jspx_th_c_if_1.setTest( SiteNavigationMenuPermission.contains(permissionChecker, siteNavigationMenu, ActionKeys.UPDATE) && siteNavigationAdminDisplayContext.hasEditPermission() );
          int _jspx_eval_c_if_1 = _jspx_th_c_if_1.doStartTag();
          if (_jspx_eval_c_if_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t");
            //  portlet:actionURL
            com.liferay.taglib.portlet.ActionURLTag _jspx_th_portlet_actionURL_0 = (com.liferay.taglib.portlet.ActionURLTag) _jspx_tagPool_portlet_actionURL_var_name.get(com.liferay.taglib.portlet.ActionURLTag.class);
            _jspx_th_portlet_actionURL_0.setPageContext(_jspx_page_context);
            _jspx_th_portlet_actionURL_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_1);
            _jspx_th_portlet_actionURL_0.setName("/navigation_menu/update_site_navigation_menu");
            _jspx_th_portlet_actionURL_0.setVar("updateSiteNavigationMenuURL");
            int _jspx_eval_portlet_actionURL_0 = _jspx_th_portlet_actionURL_0.doStartTag();
            if (_jspx_eval_portlet_actionURL_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\t\t\t");
              //  portlet:param
              com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_3 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
              _jspx_th_portlet_param_3.setPageContext(_jspx_page_context);
              _jspx_th_portlet_param_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_actionURL_0);
              _jspx_th_portlet_param_3.setName("siteNavigationMenuId");
              _jspx_th_portlet_param_3.setValue( String.valueOf(siteNavigationMenu.getSiteNavigationMenuId()) );
              int _jspx_eval_portlet_param_3 = _jspx_th_portlet_param_3.doStartTag();
              if (_jspx_th_portlet_param_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_3);
                return;
              }
              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_3);
              out.write("\n\t\t");
            }
            if (_jspx_th_portlet_actionURL_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_portlet_actionURL_var_name.reuse(_jspx_th_portlet_actionURL_0);
              return;
            }
            _jspx_tagPool_portlet_actionURL_var_name.reuse(_jspx_th_portlet_actionURL_0);
            java.lang.String updateSiteNavigationMenuURL = null;
            updateSiteNavigationMenuURL = (java.lang.String) _jspx_page_context.findAttribute("updateSiteNavigationMenuURL");
            out.write("\n\n\t\t");

		Map<String, Object> updateSiteNavigationMenuData = new HashMap<String, Object>();

		updateSiteNavigationMenuData.put("form-submit-url", updateSiteNavigationMenuURL.toString());
		updateSiteNavigationMenuData.put("id-field-value", siteNavigationMenu.getSiteNavigationMenuId());
		updateSiteNavigationMenuData.put("main-field-value", siteNavigationMenu.getName());
		
            out.write("\n\n\t\t");
            //  liferay-ui:icon
            com.liferay.taglib.ui.IconTag _jspx_th_liferay$1ui_icon_1 = (com.liferay.taglib.ui.IconTag) _jspx_tagPool_liferay$1ui_icon_url_message_data_cssClass_nobody.get(com.liferay.taglib.ui.IconTag.class);
            _jspx_th_liferay$1ui_icon_1.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1ui_icon_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_1);
            _jspx_th_liferay$1ui_icon_1.setCssClass( renderResponse.getNamespace() + "update-site-navigation-menu-action-option" );
            _jspx_th_liferay$1ui_icon_1.setData( updateSiteNavigationMenuData );
            _jspx_th_liferay$1ui_icon_1.setMessage("rename");
            _jspx_th_liferay$1ui_icon_1.setUrl("javascript:;");
            int _jspx_eval_liferay$1ui_icon_1 = _jspx_th_liferay$1ui_icon_1.doStartTag();
            if (_jspx_th_liferay$1ui_icon_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_liferay$1ui_icon_url_message_data_cssClass_nobody.reuse(_jspx_th_liferay$1ui_icon_1);
              return;
            }
            _jspx_tagPool_liferay$1ui_icon_url_message_data_cssClass_nobody.reuse(_jspx_th_liferay$1ui_icon_1);
            out.write('\n');
            out.write('	');
          }
          if (_jspx_th_c_if_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_1);
            return;
          }
          _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_1);
          out.write("\n\n\t");
          //  c:if
          com.liferay.taglib.core.IfTag _jspx_th_c_if_2 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
          _jspx_th_c_if_2.setPageContext(_jspx_page_context);
          _jspx_th_c_if_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_icon$1menu_0);
          _jspx_th_c_if_2.setTest( SiteNavigationMenuPermission.contains(permissionChecker, siteNavigationMenu, ActionKeys.PERMISSIONS) && siteNavigationAdminDisplayContext.hasEditPermission() );
          int _jspx_eval_c_if_2 = _jspx_th_c_if_2.doStartTag();
          if (_jspx_eval_c_if_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t");
            //  liferay-security:permissionsURL
            com.liferay.taglib.security.PermissionsURLTag _jspx_th_liferay$1security_permissionsURL_0 = (com.liferay.taglib.security.PermissionsURLTag) _jspx_tagPool_liferay$1security_permissionsURL_windowState_var_resourcePrimKey_modelResourceDescription_modelResource_nobody.get(com.liferay.taglib.security.PermissionsURLTag.class);
            _jspx_th_liferay$1security_permissionsURL_0.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1security_permissionsURL_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_2);
            _jspx_th_liferay$1security_permissionsURL_0.setModelResource( SiteNavigationMenu.class.getName() );
            _jspx_th_liferay$1security_permissionsURL_0.setModelResourceDescription( siteNavigationMenu.getName() );
            _jspx_th_liferay$1security_permissionsURL_0.setResourcePrimKey( String.valueOf(siteNavigationMenu.getSiteNavigationMenuId()) );
            _jspx_th_liferay$1security_permissionsURL_0.setVar("permissionsMenuURL");
            _jspx_th_liferay$1security_permissionsURL_0.setWindowState( LiferayWindowState.POP_UP.toString() );
            int _jspx_eval_liferay$1security_permissionsURL_0 = _jspx_th_liferay$1security_permissionsURL_0.doStartTag();
            if (_jspx_th_liferay$1security_permissionsURL_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_liferay$1security_permissionsURL_windowState_var_resourcePrimKey_modelResourceDescription_modelResource_nobody.reuse(_jspx_th_liferay$1security_permissionsURL_0);
              return;
            }
            _jspx_tagPool_liferay$1security_permissionsURL_windowState_var_resourcePrimKey_modelResourceDescription_modelResource_nobody.reuse(_jspx_th_liferay$1security_permissionsURL_0);
            java.lang.String permissionsMenuURL = null;
            permissionsMenuURL = (java.lang.String) _jspx_page_context.findAttribute("permissionsMenuURL");
            out.write("\n\n\t\t");
            //  liferay-ui:icon
            com.liferay.taglib.ui.IconTag _jspx_th_liferay$1ui_icon_2 = (com.liferay.taglib.ui.IconTag) _jspx_tagPool_liferay$1ui_icon_useDialog_url_method_message_nobody.get(com.liferay.taglib.ui.IconTag.class);
            _jspx_th_liferay$1ui_icon_2.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1ui_icon_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_2);
            _jspx_th_liferay$1ui_icon_2.setMessage("permissions");
            _jspx_th_liferay$1ui_icon_2.setMethod("get");
            _jspx_th_liferay$1ui_icon_2.setUrl( permissionsMenuURL );
            _jspx_th_liferay$1ui_icon_2.setUseDialog( true );
            int _jspx_eval_liferay$1ui_icon_2 = _jspx_th_liferay$1ui_icon_2.doStartTag();
            if (_jspx_th_liferay$1ui_icon_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_liferay$1ui_icon_useDialog_url_method_message_nobody.reuse(_jspx_th_liferay$1ui_icon_2);
              return;
            }
            _jspx_tagPool_liferay$1ui_icon_useDialog_url_method_message_nobody.reuse(_jspx_th_liferay$1ui_icon_2);
            out.write('\n');
            out.write('	');
          }
          if (_jspx_th_c_if_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_2);
            return;
          }
          _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_2);
          out.write("\n\n\t");
          //  c:if
          com.liferay.taglib.core.IfTag _jspx_th_c_if_3 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
          _jspx_th_c_if_3.setPageContext(_jspx_page_context);
          _jspx_th_c_if_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_icon$1menu_0);
          _jspx_th_c_if_3.setTest( SiteNavigationMenuPermission.contains(permissionChecker, siteNavigationMenu, ActionKeys.UPDATE) && siteNavigationAdminDisplayContext.hasEditPermission() );
          int _jspx_eval_c_if_3 = _jspx_th_c_if_3.doStartTag();
          if (_jspx_eval_c_if_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t<div class=\"border-top dropdown-subheader\">\n\t\t\t");
            if (_jspx_meth_liferay$1ui_message_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_3, _jspx_page_context))
              return;
            out.write("\n\t\t</div>\n\n\t\t");
            //  liferay-portlet:actionURL
            com.liferay.taglib.portlet.ActionURLTag _jspx_th_liferay$1portlet_actionURL_0 = (com.liferay.taglib.portlet.ActionURLTag) _jspx_tagPool_liferay$1portlet_actionURL_var_name.get(com.liferay.taglib.portlet.ActionURLTag.class);
            _jspx_th_liferay$1portlet_actionURL_0.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1portlet_actionURL_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_3);
            _jspx_th_liferay$1portlet_actionURL_0.setName("/navigation_menu/edit_site_navigation_menu_settings");
            _jspx_th_liferay$1portlet_actionURL_0.setVar("markAsPrimaryURL");
            int _jspx_eval_liferay$1portlet_actionURL_0 = _jspx_th_liferay$1portlet_actionURL_0.doStartTag();
            if (_jspx_eval_liferay$1portlet_actionURL_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\t\t\t");
              //  liferay-portlet:param
              com.liferay.taglib.util.ParamTag _jspx_th_liferay$1portlet_param_0 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_liferay$1portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
              _jspx_th_liferay$1portlet_param_0.setPageContext(_jspx_page_context);
              _jspx_th_liferay$1portlet_param_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_actionURL_0);
              _jspx_th_liferay$1portlet_param_0.setName("redirect");
              _jspx_th_liferay$1portlet_param_0.setValue( portletURL.toString() );
              int _jspx_eval_liferay$1portlet_param_0 = _jspx_th_liferay$1portlet_param_0.doStartTag();
              if (_jspx_th_liferay$1portlet_param_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_liferay$1portlet_param_value_name_nobody.reuse(_jspx_th_liferay$1portlet_param_0);
                return;
              }
              _jspx_tagPool_liferay$1portlet_param_value_name_nobody.reuse(_jspx_th_liferay$1portlet_param_0);
              out.write("\n\t\t\t");
              //  liferay-portlet:param
              com.liferay.taglib.util.ParamTag _jspx_th_liferay$1portlet_param_1 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_liferay$1portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
              _jspx_th_liferay$1portlet_param_1.setPageContext(_jspx_page_context);
              _jspx_th_liferay$1portlet_param_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_actionURL_0);
              _jspx_th_liferay$1portlet_param_1.setName("siteNavigationMenuId");
              _jspx_th_liferay$1portlet_param_1.setValue( String.valueOf(siteNavigationMenu.getSiteNavigationMenuId()) );
              int _jspx_eval_liferay$1portlet_param_1 = _jspx_th_liferay$1portlet_param_1.doStartTag();
              if (_jspx_th_liferay$1portlet_param_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_liferay$1portlet_param_value_name_nobody.reuse(_jspx_th_liferay$1portlet_param_1);
                return;
              }
              _jspx_tagPool_liferay$1portlet_param_value_name_nobody.reuse(_jspx_th_liferay$1portlet_param_1);
              out.write("\n\t\t\t");
              //  liferay-portlet:param
              com.liferay.taglib.util.ParamTag _jspx_th_liferay$1portlet_param_2 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_liferay$1portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
              _jspx_th_liferay$1portlet_param_2.setPageContext(_jspx_page_context);
              _jspx_th_liferay$1portlet_param_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_actionURL_0);
              _jspx_th_liferay$1portlet_param_2.setName("auto");
              _jspx_th_liferay$1portlet_param_2.setValue( String.valueOf(siteNavigationMenu.isAuto()) );
              int _jspx_eval_liferay$1portlet_param_2 = _jspx_th_liferay$1portlet_param_2.doStartTag();
              if (_jspx_th_liferay$1portlet_param_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_liferay$1portlet_param_value_name_nobody.reuse(_jspx_th_liferay$1portlet_param_2);
                return;
              }
              _jspx_tagPool_liferay$1portlet_param_value_name_nobody.reuse(_jspx_th_liferay$1portlet_param_2);
              out.write("\n\t\t\t");
              //  liferay-portlet:param
              com.liferay.taglib.util.ParamTag _jspx_th_liferay$1portlet_param_3 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_liferay$1portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
              _jspx_th_liferay$1portlet_param_3.setPageContext(_jspx_page_context);
              _jspx_th_liferay$1portlet_param_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_actionURL_0);
              _jspx_th_liferay$1portlet_param_3.setName("type");
              _jspx_th_liferay$1portlet_param_3.setValue( String.valueOf(SiteNavigationConstants.TYPE_PRIMARY) );
              int _jspx_eval_liferay$1portlet_param_3 = _jspx_th_liferay$1portlet_param_3.doStartTag();
              if (_jspx_th_liferay$1portlet_param_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_liferay$1portlet_param_value_name_nobody.reuse(_jspx_th_liferay$1portlet_param_3);
                return;
              }
              _jspx_tagPool_liferay$1portlet_param_value_name_nobody.reuse(_jspx_th_liferay$1portlet_param_3);
              out.write("\n\t\t");
            }
            if (_jspx_th_liferay$1portlet_actionURL_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_liferay$1portlet_actionURL_var_name.reuse(_jspx_th_liferay$1portlet_actionURL_0);
              return;
            }
            _jspx_tagPool_liferay$1portlet_actionURL_var_name.reuse(_jspx_th_liferay$1portlet_actionURL_0);
            java.lang.String markAsPrimaryURL = null;
            markAsPrimaryURL = (java.lang.String) _jspx_page_context.findAttribute("markAsPrimaryURL");
            out.write("\n\n\t\t");

		String taglibOnClickPrimary = "submitForm(document.hrefFm, '" + markAsPrimaryURL + "');";

		SiteNavigationMenu primarySiteNavigationMenu = siteNavigationAdminDisplayContext.getPrimarySiteNavigationMenu();

		if ((siteNavigationMenu.getType() != SiteNavigationConstants.TYPE_PRIMARY) && (primarySiteNavigationMenu != null)) {
			taglibOnClickPrimary = "if (confirm('" + UnicodeLanguageUtil.format(request, "do-you-want-to-replace-x-for-x-as-the-primary-navigation?-this-action-will-affect-all-the-pages-using-primary-navigation", new String[] {siteNavigationMenu.getName(), primarySiteNavigationMenu.getName()}) + "')) { submitForm(document.hrefFm, '" + markAsPrimaryURL + "'); } ";
		}
		
            out.write("\n\n\t\t");
            //  liferay-ui:icon
            com.liferay.taglib.ui.IconTag _jspx_th_liferay$1ui_icon_3 = (com.liferay.taglib.ui.IconTag) _jspx_tagPool_liferay$1ui_icon_url_onClick_message_markupView_iconCssClass_icon_nobody.get(com.liferay.taglib.ui.IconTag.class);
            _jspx_th_liferay$1ui_icon_3.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1ui_icon_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_3);
            _jspx_th_liferay$1ui_icon_3.setIcon( (siteNavigationMenu.getType() == SiteNavigationConstants.TYPE_PRIMARY) ? "check" : StringPool.BLANK );
            _jspx_th_liferay$1ui_icon_3.setIconCssClass("pull-right");
            _jspx_th_liferay$1ui_icon_3.setMarkupView("lexicon");
            _jspx_th_liferay$1ui_icon_3.setMessage("primary-navigation");
            _jspx_th_liferay$1ui_icon_3.setOnClick( taglibOnClickPrimary );
            _jspx_th_liferay$1ui_icon_3.setUrl("javascript:;");
            int _jspx_eval_liferay$1ui_icon_3 = _jspx_th_liferay$1ui_icon_3.doStartTag();
            if (_jspx_th_liferay$1ui_icon_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_liferay$1ui_icon_url_onClick_message_markupView_iconCssClass_icon_nobody.reuse(_jspx_th_liferay$1ui_icon_3);
              return;
            }
            _jspx_tagPool_liferay$1ui_icon_url_onClick_message_markupView_iconCssClass_icon_nobody.reuse(_jspx_th_liferay$1ui_icon_3);
            out.write("\n\n\t\t");
            //  liferay-portlet:actionURL
            com.liferay.taglib.portlet.ActionURLTag _jspx_th_liferay$1portlet_actionURL_1 = (com.liferay.taglib.portlet.ActionURLTag) _jspx_tagPool_liferay$1portlet_actionURL_var_name.get(com.liferay.taglib.portlet.ActionURLTag.class);
            _jspx_th_liferay$1portlet_actionURL_1.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1portlet_actionURL_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_3);
            _jspx_th_liferay$1portlet_actionURL_1.setName("/navigation_menu/edit_site_navigation_menu_settings");
            _jspx_th_liferay$1portlet_actionURL_1.setVar("markAsSecondaryURL");
            int _jspx_eval_liferay$1portlet_actionURL_1 = _jspx_th_liferay$1portlet_actionURL_1.doStartTag();
            if (_jspx_eval_liferay$1portlet_actionURL_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\t\t\t");
              //  liferay-portlet:param
              com.liferay.taglib.util.ParamTag _jspx_th_liferay$1portlet_param_4 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_liferay$1portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
              _jspx_th_liferay$1portlet_param_4.setPageContext(_jspx_page_context);
              _jspx_th_liferay$1portlet_param_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_actionURL_1);
              _jspx_th_liferay$1portlet_param_4.setName("redirect");
              _jspx_th_liferay$1portlet_param_4.setValue( portletURL.toString() );
              int _jspx_eval_liferay$1portlet_param_4 = _jspx_th_liferay$1portlet_param_4.doStartTag();
              if (_jspx_th_liferay$1portlet_param_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_liferay$1portlet_param_value_name_nobody.reuse(_jspx_th_liferay$1portlet_param_4);
                return;
              }
              _jspx_tagPool_liferay$1portlet_param_value_name_nobody.reuse(_jspx_th_liferay$1portlet_param_4);
              out.write("\n\t\t\t");
              //  liferay-portlet:param
              com.liferay.taglib.util.ParamTag _jspx_th_liferay$1portlet_param_5 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_liferay$1portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
              _jspx_th_liferay$1portlet_param_5.setPageContext(_jspx_page_context);
              _jspx_th_liferay$1portlet_param_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_actionURL_1);
              _jspx_th_liferay$1portlet_param_5.setName("siteNavigationMenuId");
              _jspx_th_liferay$1portlet_param_5.setValue( String.valueOf(siteNavigationMenu.getSiteNavigationMenuId()) );
              int _jspx_eval_liferay$1portlet_param_5 = _jspx_th_liferay$1portlet_param_5.doStartTag();
              if (_jspx_th_liferay$1portlet_param_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_liferay$1portlet_param_value_name_nobody.reuse(_jspx_th_liferay$1portlet_param_5);
                return;
              }
              _jspx_tagPool_liferay$1portlet_param_value_name_nobody.reuse(_jspx_th_liferay$1portlet_param_5);
              out.write("\n\t\t\t");
              //  liferay-portlet:param
              com.liferay.taglib.util.ParamTag _jspx_th_liferay$1portlet_param_6 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_liferay$1portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
              _jspx_th_liferay$1portlet_param_6.setPageContext(_jspx_page_context);
              _jspx_th_liferay$1portlet_param_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_actionURL_1);
              _jspx_th_liferay$1portlet_param_6.setName("auto");
              _jspx_th_liferay$1portlet_param_6.setValue( String.valueOf(siteNavigationMenu.isAuto()) );
              int _jspx_eval_liferay$1portlet_param_6 = _jspx_th_liferay$1portlet_param_6.doStartTag();
              if (_jspx_th_liferay$1portlet_param_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_liferay$1portlet_param_value_name_nobody.reuse(_jspx_th_liferay$1portlet_param_6);
                return;
              }
              _jspx_tagPool_liferay$1portlet_param_value_name_nobody.reuse(_jspx_th_liferay$1portlet_param_6);
              out.write("\n\t\t\t");
              //  liferay-portlet:param
              com.liferay.taglib.util.ParamTag _jspx_th_liferay$1portlet_param_7 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_liferay$1portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
              _jspx_th_liferay$1portlet_param_7.setPageContext(_jspx_page_context);
              _jspx_th_liferay$1portlet_param_7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_actionURL_1);
              _jspx_th_liferay$1portlet_param_7.setName("type");
              _jspx_th_liferay$1portlet_param_7.setValue( String.valueOf(SiteNavigationConstants.TYPE_SECONDARY) );
              int _jspx_eval_liferay$1portlet_param_7 = _jspx_th_liferay$1portlet_param_7.doStartTag();
              if (_jspx_th_liferay$1portlet_param_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_liferay$1portlet_param_value_name_nobody.reuse(_jspx_th_liferay$1portlet_param_7);
                return;
              }
              _jspx_tagPool_liferay$1portlet_param_value_name_nobody.reuse(_jspx_th_liferay$1portlet_param_7);
              out.write("\n\t\t");
            }
            if (_jspx_th_liferay$1portlet_actionURL_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_liferay$1portlet_actionURL_var_name.reuse(_jspx_th_liferay$1portlet_actionURL_1);
              return;
            }
            _jspx_tagPool_liferay$1portlet_actionURL_var_name.reuse(_jspx_th_liferay$1portlet_actionURL_1);
            java.lang.String markAsSecondaryURL = null;
            markAsSecondaryURL = (java.lang.String) _jspx_page_context.findAttribute("markAsSecondaryURL");
            out.write("\n\n\t\t");

		String taglibOnClickSecondary = "submitForm(document.hrefFm, '" + markAsSecondaryURL + "');";
		
            out.write("\n\n\t\t");
            //  liferay-ui:icon
            com.liferay.taglib.ui.IconTag _jspx_th_liferay$1ui_icon_4 = (com.liferay.taglib.ui.IconTag) _jspx_tagPool_liferay$1ui_icon_url_onClick_message_markupView_iconCssClass_icon_nobody.get(com.liferay.taglib.ui.IconTag.class);
            _jspx_th_liferay$1ui_icon_4.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1ui_icon_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_3);
            _jspx_th_liferay$1ui_icon_4.setIcon( (siteNavigationMenu.getType() == SiteNavigationConstants.TYPE_SECONDARY) ? "check" : StringPool.BLANK );
            _jspx_th_liferay$1ui_icon_4.setIconCssClass("pull-right");
            _jspx_th_liferay$1ui_icon_4.setMarkupView("lexicon");
            _jspx_th_liferay$1ui_icon_4.setMessage("secondary-navigation");
            _jspx_th_liferay$1ui_icon_4.setOnClick( taglibOnClickSecondary );
            _jspx_th_liferay$1ui_icon_4.setUrl("javascript:;");
            int _jspx_eval_liferay$1ui_icon_4 = _jspx_th_liferay$1ui_icon_4.doStartTag();
            if (_jspx_th_liferay$1ui_icon_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_liferay$1ui_icon_url_onClick_message_markupView_iconCssClass_icon_nobody.reuse(_jspx_th_liferay$1ui_icon_4);
              return;
            }
            _jspx_tagPool_liferay$1ui_icon_url_onClick_message_markupView_iconCssClass_icon_nobody.reuse(_jspx_th_liferay$1ui_icon_4);
            out.write("\n\n\t\t");
            //  liferay-portlet:actionURL
            com.liferay.taglib.portlet.ActionURLTag _jspx_th_liferay$1portlet_actionURL_2 = (com.liferay.taglib.portlet.ActionURLTag) _jspx_tagPool_liferay$1portlet_actionURL_var_name.get(com.liferay.taglib.portlet.ActionURLTag.class);
            _jspx_th_liferay$1portlet_actionURL_2.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1portlet_actionURL_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_3);
            _jspx_th_liferay$1portlet_actionURL_2.setName("/navigation_menu/edit_site_navigation_menu_settings");
            _jspx_th_liferay$1portlet_actionURL_2.setVar("markAsSocialURL");
            int _jspx_eval_liferay$1portlet_actionURL_2 = _jspx_th_liferay$1portlet_actionURL_2.doStartTag();
            if (_jspx_eval_liferay$1portlet_actionURL_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\t\t\t");
              //  liferay-portlet:param
              com.liferay.taglib.util.ParamTag _jspx_th_liferay$1portlet_param_8 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_liferay$1portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
              _jspx_th_liferay$1portlet_param_8.setPageContext(_jspx_page_context);
              _jspx_th_liferay$1portlet_param_8.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_actionURL_2);
              _jspx_th_liferay$1portlet_param_8.setName("redirect");
              _jspx_th_liferay$1portlet_param_8.setValue( portletURL.toString() );
              int _jspx_eval_liferay$1portlet_param_8 = _jspx_th_liferay$1portlet_param_8.doStartTag();
              if (_jspx_th_liferay$1portlet_param_8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_liferay$1portlet_param_value_name_nobody.reuse(_jspx_th_liferay$1portlet_param_8);
                return;
              }
              _jspx_tagPool_liferay$1portlet_param_value_name_nobody.reuse(_jspx_th_liferay$1portlet_param_8);
              out.write("\n\t\t\t");
              //  liferay-portlet:param
              com.liferay.taglib.util.ParamTag _jspx_th_liferay$1portlet_param_9 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_liferay$1portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
              _jspx_th_liferay$1portlet_param_9.setPageContext(_jspx_page_context);
              _jspx_th_liferay$1portlet_param_9.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_actionURL_2);
              _jspx_th_liferay$1portlet_param_9.setName("siteNavigationMenuId");
              _jspx_th_liferay$1portlet_param_9.setValue( String.valueOf(siteNavigationMenu.getSiteNavigationMenuId()) );
              int _jspx_eval_liferay$1portlet_param_9 = _jspx_th_liferay$1portlet_param_9.doStartTag();
              if (_jspx_th_liferay$1portlet_param_9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_liferay$1portlet_param_value_name_nobody.reuse(_jspx_th_liferay$1portlet_param_9);
                return;
              }
              _jspx_tagPool_liferay$1portlet_param_value_name_nobody.reuse(_jspx_th_liferay$1portlet_param_9);
              out.write("\n\t\t\t");
              //  liferay-portlet:param
              com.liferay.taglib.util.ParamTag _jspx_th_liferay$1portlet_param_10 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_liferay$1portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
              _jspx_th_liferay$1portlet_param_10.setPageContext(_jspx_page_context);
              _jspx_th_liferay$1portlet_param_10.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_actionURL_2);
              _jspx_th_liferay$1portlet_param_10.setName("auto");
              _jspx_th_liferay$1portlet_param_10.setValue( String.valueOf(siteNavigationMenu.isAuto()) );
              int _jspx_eval_liferay$1portlet_param_10 = _jspx_th_liferay$1portlet_param_10.doStartTag();
              if (_jspx_th_liferay$1portlet_param_10.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_liferay$1portlet_param_value_name_nobody.reuse(_jspx_th_liferay$1portlet_param_10);
                return;
              }
              _jspx_tagPool_liferay$1portlet_param_value_name_nobody.reuse(_jspx_th_liferay$1portlet_param_10);
              out.write("\n\t\t\t");
              //  liferay-portlet:param
              com.liferay.taglib.util.ParamTag _jspx_th_liferay$1portlet_param_11 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_liferay$1portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
              _jspx_th_liferay$1portlet_param_11.setPageContext(_jspx_page_context);
              _jspx_th_liferay$1portlet_param_11.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_actionURL_2);
              _jspx_th_liferay$1portlet_param_11.setName("type");
              _jspx_th_liferay$1portlet_param_11.setValue( String.valueOf(SiteNavigationConstants.TYPE_SOCIAL) );
              int _jspx_eval_liferay$1portlet_param_11 = _jspx_th_liferay$1portlet_param_11.doStartTag();
              if (_jspx_th_liferay$1portlet_param_11.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_liferay$1portlet_param_value_name_nobody.reuse(_jspx_th_liferay$1portlet_param_11);
                return;
              }
              _jspx_tagPool_liferay$1portlet_param_value_name_nobody.reuse(_jspx_th_liferay$1portlet_param_11);
              out.write("\n\t\t");
            }
            if (_jspx_th_liferay$1portlet_actionURL_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_liferay$1portlet_actionURL_var_name.reuse(_jspx_th_liferay$1portlet_actionURL_2);
              return;
            }
            _jspx_tagPool_liferay$1portlet_actionURL_var_name.reuse(_jspx_th_liferay$1portlet_actionURL_2);
            java.lang.String markAsSocialURL = null;
            markAsSocialURL = (java.lang.String) _jspx_page_context.findAttribute("markAsSocialURL");
            out.write("\n\n\t\t");

		String taglibOnClickSocial = "submitForm(document.hrefFm, '" + markAsSocialURL + "');";
		
            out.write("\n\n\t\t");
            //  liferay-ui:icon
            com.liferay.taglib.ui.IconTag _jspx_th_liferay$1ui_icon_5 = (com.liferay.taglib.ui.IconTag) _jspx_tagPool_liferay$1ui_icon_url_onClick_message_markupView_iconCssClass_icon_nobody.get(com.liferay.taglib.ui.IconTag.class);
            _jspx_th_liferay$1ui_icon_5.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1ui_icon_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_3);
            _jspx_th_liferay$1ui_icon_5.setIcon( (siteNavigationMenu.getType() == SiteNavigationConstants.TYPE_SOCIAL) ? "check" : StringPool.BLANK );
            _jspx_th_liferay$1ui_icon_5.setIconCssClass("pull-right");
            _jspx_th_liferay$1ui_icon_5.setMarkupView("lexicon");
            _jspx_th_liferay$1ui_icon_5.setMessage("social-navigation");
            _jspx_th_liferay$1ui_icon_5.setOnClick( taglibOnClickSocial );
            _jspx_th_liferay$1ui_icon_5.setUrl("javascript:;");
            int _jspx_eval_liferay$1ui_icon_5 = _jspx_th_liferay$1ui_icon_5.doStartTag();
            if (_jspx_th_liferay$1ui_icon_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_liferay$1ui_icon_url_onClick_message_markupView_iconCssClass_icon_nobody.reuse(_jspx_th_liferay$1ui_icon_5);
              return;
            }
            _jspx_tagPool_liferay$1ui_icon_url_onClick_message_markupView_iconCssClass_icon_nobody.reuse(_jspx_th_liferay$1ui_icon_5);
            out.write("\n\n\t\t");
            //  liferay-portlet:actionURL
            com.liferay.taglib.portlet.ActionURLTag _jspx_th_liferay$1portlet_actionURL_3 = (com.liferay.taglib.portlet.ActionURLTag) _jspx_tagPool_liferay$1portlet_actionURL_var_name.get(com.liferay.taglib.portlet.ActionURLTag.class);
            _jspx_th_liferay$1portlet_actionURL_3.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1portlet_actionURL_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_3);
            _jspx_th_liferay$1portlet_actionURL_3.setName("/navigation_menu/edit_site_navigation_menu_settings");
            _jspx_th_liferay$1portlet_actionURL_3.setVar("markAsPrivteURL");
            int _jspx_eval_liferay$1portlet_actionURL_3 = _jspx_th_liferay$1portlet_actionURL_3.doStartTag();
            if (_jspx_eval_liferay$1portlet_actionURL_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\t\t\t");
              //  liferay-portlet:param
              com.liferay.taglib.util.ParamTag _jspx_th_liferay$1portlet_param_12 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_liferay$1portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
              _jspx_th_liferay$1portlet_param_12.setPageContext(_jspx_page_context);
              _jspx_th_liferay$1portlet_param_12.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_actionURL_3);
              _jspx_th_liferay$1portlet_param_12.setName("redirect");
              _jspx_th_liferay$1portlet_param_12.setValue( portletURL.toString() );
              int _jspx_eval_liferay$1portlet_param_12 = _jspx_th_liferay$1portlet_param_12.doStartTag();
              if (_jspx_th_liferay$1portlet_param_12.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_liferay$1portlet_param_value_name_nobody.reuse(_jspx_th_liferay$1portlet_param_12);
                return;
              }
              _jspx_tagPool_liferay$1portlet_param_value_name_nobody.reuse(_jspx_th_liferay$1portlet_param_12);
              out.write("\n\t\t\t");
              //  liferay-portlet:param
              com.liferay.taglib.util.ParamTag _jspx_th_liferay$1portlet_param_13 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_liferay$1portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
              _jspx_th_liferay$1portlet_param_13.setPageContext(_jspx_page_context);
              _jspx_th_liferay$1portlet_param_13.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_actionURL_3);
              _jspx_th_liferay$1portlet_param_13.setName("siteNavigationMenuId");
              _jspx_th_liferay$1portlet_param_13.setValue( String.valueOf(siteNavigationMenu.getSiteNavigationMenuId()) );
              int _jspx_eval_liferay$1portlet_param_13 = _jspx_th_liferay$1portlet_param_13.doStartTag();
              if (_jspx_th_liferay$1portlet_param_13.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_liferay$1portlet_param_value_name_nobody.reuse(_jspx_th_liferay$1portlet_param_13);
                return;
              }
              _jspx_tagPool_liferay$1portlet_param_value_name_nobody.reuse(_jspx_th_liferay$1portlet_param_13);
              out.write("\n\t\t\t");
              //  liferay-portlet:param
              com.liferay.taglib.util.ParamTag _jspx_th_liferay$1portlet_param_14 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_liferay$1portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
              _jspx_th_liferay$1portlet_param_14.setPageContext(_jspx_page_context);
              _jspx_th_liferay$1portlet_param_14.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_actionURL_3);
              _jspx_th_liferay$1portlet_param_14.setName("auto");
              _jspx_th_liferay$1portlet_param_14.setValue( String.valueOf(siteNavigationMenu.isAuto()) );
              int _jspx_eval_liferay$1portlet_param_14 = _jspx_th_liferay$1portlet_param_14.doStartTag();
              if (_jspx_th_liferay$1portlet_param_14.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_liferay$1portlet_param_value_name_nobody.reuse(_jspx_th_liferay$1portlet_param_14);
                return;
              }
              _jspx_tagPool_liferay$1portlet_param_value_name_nobody.reuse(_jspx_th_liferay$1portlet_param_14);
              out.write("\n\t\t\t");
              //  liferay-portlet:param
              com.liferay.taglib.util.ParamTag _jspx_th_liferay$1portlet_param_15 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_liferay$1portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
              _jspx_th_liferay$1portlet_param_15.setPageContext(_jspx_page_context);
              _jspx_th_liferay$1portlet_param_15.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_actionURL_3);
              _jspx_th_liferay$1portlet_param_15.setName("type");
              _jspx_th_liferay$1portlet_param_15.setValue( String.valueOf(SiteNavigationConstants.TYPE_PRIVATE) );
              int _jspx_eval_liferay$1portlet_param_15 = _jspx_th_liferay$1portlet_param_15.doStartTag();
              if (_jspx_th_liferay$1portlet_param_15.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_liferay$1portlet_param_value_name_nobody.reuse(_jspx_th_liferay$1portlet_param_15);
                return;
              }
              _jspx_tagPool_liferay$1portlet_param_value_name_nobody.reuse(_jspx_th_liferay$1portlet_param_15);
              out.write("\n\t\t");
            }
            if (_jspx_th_liferay$1portlet_actionURL_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_liferay$1portlet_actionURL_var_name.reuse(_jspx_th_liferay$1portlet_actionURL_3);
              return;
            }
            _jspx_tagPool_liferay$1portlet_actionURL_var_name.reuse(_jspx_th_liferay$1portlet_actionURL_3);
            java.lang.String markAsPrivteURL = null;
            markAsPrivteURL = (java.lang.String) _jspx_page_context.findAttribute("markAsPrivteURL");
            out.write("\n\n\t\t");

		String taglibOnClickPrivate = "submitForm(document.hrefFm, '" + markAsPrivteURL + "');";
		
            out.write("\n\n\t\t");
            //  liferay-ui:icon
            com.liferay.taglib.ui.IconTag _jspx_th_liferay$1ui_icon_6 = (com.liferay.taglib.ui.IconTag) _jspx_tagPool_liferay$1ui_icon_url_onClick_message_markupView_iconCssClass_icon_cssClass_nobody.get(com.liferay.taglib.ui.IconTag.class);
            _jspx_th_liferay$1ui_icon_6.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1ui_icon_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_3);
            _jspx_th_liferay$1ui_icon_6.setCssClass("border-bottom mb-1");
            _jspx_th_liferay$1ui_icon_6.setIcon( (siteNavigationMenu.getType() == SiteNavigationConstants.TYPE_PRIVATE) ? "check" : StringPool.BLANK );
            _jspx_th_liferay$1ui_icon_6.setIconCssClass("pull-right");
            _jspx_th_liferay$1ui_icon_6.setMarkupView("lexicon");
            _jspx_th_liferay$1ui_icon_6.setMessage("private-navigation");
            _jspx_th_liferay$1ui_icon_6.setOnClick( taglibOnClickPrivate );
            _jspx_th_liferay$1ui_icon_6.setUrl("javascript:;");
            int _jspx_eval_liferay$1ui_icon_6 = _jspx_th_liferay$1ui_icon_6.doStartTag();
            if (_jspx_th_liferay$1ui_icon_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_liferay$1ui_icon_url_onClick_message_markupView_iconCssClass_icon_cssClass_nobody.reuse(_jspx_th_liferay$1ui_icon_6);
              return;
            }
            _jspx_tagPool_liferay$1ui_icon_url_onClick_message_markupView_iconCssClass_icon_cssClass_nobody.reuse(_jspx_th_liferay$1ui_icon_6);
            out.write('\n');
            out.write('	');
          }
          if (_jspx_th_c_if_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_3);
            return;
          }
          _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_3);
          out.write("\n\n\t");
          //  c:if
          com.liferay.taglib.core.IfTag _jspx_th_c_if_4 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
          _jspx_th_c_if_4.setPageContext(_jspx_page_context);
          _jspx_th_c_if_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_icon$1menu_0);
          _jspx_th_c_if_4.setTest( SiteNavigationMenuPermission.contains(permissionChecker, siteNavigationMenu, ActionKeys.DELETE) );
          int _jspx_eval_c_if_4 = _jspx_th_c_if_4.doStartTag();
          if (_jspx_eval_c_if_4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t");
            //  portlet:actionURL
            com.liferay.taglib.portlet.ActionURLTag _jspx_th_portlet_actionURL_1 = (com.liferay.taglib.portlet.ActionURLTag) _jspx_tagPool_portlet_actionURL_var_name.get(com.liferay.taglib.portlet.ActionURLTag.class);
            _jspx_th_portlet_actionURL_1.setPageContext(_jspx_page_context);
            _jspx_th_portlet_actionURL_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_4);
            _jspx_th_portlet_actionURL_1.setName("/navigation_menu/delete_site_navigation_menu");
            _jspx_th_portlet_actionURL_1.setVar("deleteSiteNavigationMenuURL");
            int _jspx_eval_portlet_actionURL_1 = _jspx_th_portlet_actionURL_1.doStartTag();
            if (_jspx_eval_portlet_actionURL_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\t\t\t");
              //  portlet:param
              com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_4 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
              _jspx_th_portlet_param_4.setPageContext(_jspx_page_context);
              _jspx_th_portlet_param_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_actionURL_1);
              _jspx_th_portlet_param_4.setName("redirect");
              _jspx_th_portlet_param_4.setValue( portletURL.toString() );
              int _jspx_eval_portlet_param_4 = _jspx_th_portlet_param_4.doStartTag();
              if (_jspx_th_portlet_param_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_4);
                return;
              }
              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_4);
              out.write("\n\t\t\t");
              //  portlet:param
              com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_5 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
              _jspx_th_portlet_param_5.setPageContext(_jspx_page_context);
              _jspx_th_portlet_param_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_actionURL_1);
              _jspx_th_portlet_param_5.setName("siteNavigationMenuId");
              _jspx_th_portlet_param_5.setValue( String.valueOf(siteNavigationMenu.getSiteNavigationMenuId()) );
              int _jspx_eval_portlet_param_5 = _jspx_th_portlet_param_5.doStartTag();
              if (_jspx_th_portlet_param_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_5);
                return;
              }
              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_5);
              out.write("\n\t\t");
            }
            if (_jspx_th_portlet_actionURL_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_portlet_actionURL_var_name.reuse(_jspx_th_portlet_actionURL_1);
              return;
            }
            _jspx_tagPool_portlet_actionURL_var_name.reuse(_jspx_th_portlet_actionURL_1);
            java.lang.String deleteSiteNavigationMenuURL = null;
            deleteSiteNavigationMenuURL = (java.lang.String) _jspx_page_context.findAttribute("deleteSiteNavigationMenuURL");
            out.write("\n\n\t\t");
            //  liferay-ui:icon-delete
            com.liferay.taglib.ui.IconDeleteTag _jspx_th_liferay$1ui_icon$1delete_0 = (com.liferay.taglib.ui.IconDeleteTag) _jspx_tagPool_liferay$1ui_icon$1delete_url_trash_nobody.get(com.liferay.taglib.ui.IconDeleteTag.class);
            _jspx_th_liferay$1ui_icon$1delete_0.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1ui_icon$1delete_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_4);
            _jspx_th_liferay$1ui_icon$1delete_0.setTrash( false );
            _jspx_th_liferay$1ui_icon$1delete_0.setUrl( deleteSiteNavigationMenuURL );
            int _jspx_eval_liferay$1ui_icon$1delete_0 = _jspx_th_liferay$1ui_icon$1delete_0.doStartTag();
            if (_jspx_th_liferay$1ui_icon$1delete_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_liferay$1ui_icon$1delete_url_trash_nobody.reuse(_jspx_th_liferay$1ui_icon$1delete_0);
              return;
            }
            _jspx_tagPool_liferay$1ui_icon$1delete_url_trash_nobody.reuse(_jspx_th_liferay$1ui_icon$1delete_0);
            out.write('\n');
            out.write('	');
          }
          if (_jspx_th_c_if_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_4);
            return;
          }
          _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_4);
          out.write('\n');
          int evalDoAfterBody = _jspx_th_liferay$1ui_icon$1menu_0.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
        if (_jspx_eval_liferay$1ui_icon$1menu_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
          out = _jspx_page_context.popBody();
      }
      if (_jspx_th_liferay$1ui_icon$1menu_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_liferay$1ui_icon$1menu_showWhenSingleIcon_message_markupView_icon_direction_cssClass.reuse(_jspx_th_liferay$1ui_icon$1menu_0);
        return;
      }
      _jspx_tagPool_liferay$1ui_icon$1menu_showWhenSingleIcon_message_markupView_icon_direction_cssClass.reuse(_jspx_th_liferay$1ui_icon$1menu_0);
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

  private boolean _jspx_meth_portlet_param_0(javax.servlet.jsp.tagext.JspTag _jspx_th_portlet_renderURL_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:param
    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_0 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
    _jspx_th_portlet_param_0.setPageContext(_jspx_page_context);
    _jspx_th_portlet_param_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_0);
    _jspx_th_portlet_param_0.setName("mvcPath");
    _jspx_th_portlet_param_0.setValue("/edit_site_navigation_menu.jsp");
    int _jspx_eval_portlet_param_0 = _jspx_th_portlet_param_0.doStartTag();
    if (_jspx_th_portlet_param_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_0);
      return true;
    }
    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_0);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_0 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_0.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_3);
    _jspx_th_liferay$1ui_message_0.setKey("mark-as");
    int _jspx_eval_liferay$1ui_message_0 = _jspx_th_liferay$1ui_message_0.doStartTag();
    if (_jspx_th_liferay$1ui_message_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_0);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_0);
    return false;
  }
}
