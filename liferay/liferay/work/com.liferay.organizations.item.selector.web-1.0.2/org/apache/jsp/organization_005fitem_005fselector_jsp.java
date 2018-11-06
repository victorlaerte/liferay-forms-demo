package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.liferay.organizations.item.selector.web.internal.constants.OrganizationItemSelectorViewConstants;
import com.liferay.organizations.item.selector.web.internal.display.context.OrganizationItemSelectorViewDisplayContext;
import com.liferay.portal.kernel.util.HtmlUtil;
import java.util.HashMap;
import java.util.Map;
import javax.portlet.PortletURL;

public final class organization_005fitem_005fselector_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(1);
    _jspx_dependants.add("/init.jsp");
  }

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_namespace_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container$1row_modelVar_keyProperty_cssClass_className;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1frontend_management$1bar$1navigation_portletURL_navigationKeys_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1iterator_searchContainer_markupView_displayStyle_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1frontend_management$1bar$1display$1buttons_selectedDisplayStyle_portletURL_displayViews_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1frontend_management$1bar_searchContainerId_includeCheckBox;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1frontend_management$1bar$1sort_portletURL_orderColumns_orderByType_orderByCol_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container$1column$1text_property_cssClass_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1theme_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1frontend_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_script_use;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_cssClass_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1frontend_management$1bar$1buttons;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1frontend_management$1bar$1filters;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container_searchContainer_id;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1item$1selector_search_nobody;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_portlet_namespace_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container$1row_modelVar_keyProperty_cssClass_className = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1frontend_management$1bar$1navigation_portletURL_navigationKeys_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1iterator_searchContainer_markupView_displayStyle_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1frontend_management$1bar$1display$1buttons_selectedDisplayStyle_portletURL_displayViews_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1frontend_management$1bar_searchContainerId_includeCheckBox = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1frontend_management$1bar$1sort_portletURL_orderColumns_orderByType_orderByCol_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_property_cssClass_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1theme_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1frontend_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_script_use = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_cssClass_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1frontend_management$1bar$1buttons = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1frontend_management$1bar$1filters = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container_searchContainer_id = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1item$1selector_search_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_portlet_namespace_nobody.release();
    _jspx_tagPool_liferay$1ui_search$1container$1row_modelVar_keyProperty_cssClass_className.release();
    _jspx_tagPool_liferay$1frontend_management$1bar$1navigation_portletURL_navigationKeys_nobody.release();
    _jspx_tagPool_liferay$1ui_search$1iterator_searchContainer_markupView_displayStyle_nobody.release();
    _jspx_tagPool_liferay$1frontend_management$1bar$1display$1buttons_selectedDisplayStyle_portletURL_displayViews_nobody.release();
    _jspx_tagPool_liferay$1frontend_management$1bar_searchContainerId_includeCheckBox.release();
    _jspx_tagPool_portlet_defineObjects_nobody.release();
    _jspx_tagPool_liferay$1frontend_management$1bar$1sort_portletURL_orderColumns_orderByType_orderByCol_nobody.release();
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_property_cssClass_nobody.release();
    _jspx_tagPool_liferay$1theme_defineObjects_nobody.release();
    _jspx_tagPool_liferay$1frontend_defineObjects_nobody.release();
    _jspx_tagPool_aui_script_use.release();
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_cssClass_nobody.release();
    _jspx_tagPool_liferay$1frontend_management$1bar$1buttons.release();
    _jspx_tagPool_liferay$1frontend_management$1bar$1filters.release();
    _jspx_tagPool_liferay$1ui_search$1container_searchContainer_id.release();
    _jspx_tagPool_liferay$1item$1selector_search_nobody.release();
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
      out.write("\n\n\n\n\n\n\n\n\n\n\n\n");
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

OrganizationItemSelectorViewDisplayContext organizationItemSelectorViewDisplayContext = (OrganizationItemSelectorViewDisplayContext)request.getAttribute(OrganizationItemSelectorViewConstants.ORGANIZATION_ITEM_SELECTOR_VIEW_DISPLAY_CONTEXT);

String itemSelectedEventName = organizationItemSelectorViewDisplayContext.getItemSelectedEventName();

PortletURL portletURL = organizationItemSelectorViewDisplayContext.getPortletURL();

      out.write('\n');
      out.write('\n');
      //  liferay-frontend:management-bar
      com.liferay.frontend.taglib.servlet.taglib.ManagementBarTag _jspx_th_liferay$1frontend_management$1bar_0 = (com.liferay.frontend.taglib.servlet.taglib.ManagementBarTag) _jspx_tagPool_liferay$1frontend_management$1bar_searchContainerId_includeCheckBox.get(com.liferay.frontend.taglib.servlet.taglib.ManagementBarTag.class);
      _jspx_th_liferay$1frontend_management$1bar_0.setPageContext(_jspx_page_context);
      _jspx_th_liferay$1frontend_management$1bar_0.setParent(null);
      _jspx_th_liferay$1frontend_management$1bar_0.setIncludeCheckBox( true );
      _jspx_th_liferay$1frontend_management$1bar_0.setSearchContainerId("organizations");
      int _jspx_eval_liferay$1frontend_management$1bar_0 = _jspx_th_liferay$1frontend_management$1bar_0.doStartTag();
      if (_jspx_eval_liferay$1frontend_management$1bar_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        if (_jspx_eval_liferay$1frontend_management$1bar_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
          out = _jspx_page_context.pushBody();
          _jspx_th_liferay$1frontend_management$1bar_0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
          _jspx_th_liferay$1frontend_management$1bar_0.doInitBody();
        }
        do {
          out.write('\n');
          out.write('	');
          //  liferay-frontend:management-bar-buttons
          com.liferay.frontend.taglib.servlet.taglib.ManagementBarButtonsTag _jspx_th_liferay$1frontend_management$1bar$1buttons_0 = (com.liferay.frontend.taglib.servlet.taglib.ManagementBarButtonsTag) _jspx_tagPool_liferay$1frontend_management$1bar$1buttons.get(com.liferay.frontend.taglib.servlet.taglib.ManagementBarButtonsTag.class);
          _jspx_th_liferay$1frontend_management$1bar$1buttons_0.setPageContext(_jspx_page_context);
          _jspx_th_liferay$1frontend_management$1bar$1buttons_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_management$1bar_0);
          int _jspx_eval_liferay$1frontend_management$1bar$1buttons_0 = _jspx_th_liferay$1frontend_management$1bar$1buttons_0.doStartTag();
          if (_jspx_eval_liferay$1frontend_management$1bar$1buttons_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            if (_jspx_eval_liferay$1frontend_management$1bar$1buttons_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
              out = _jspx_page_context.pushBody();
              _jspx_th_liferay$1frontend_management$1bar$1buttons_0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
              _jspx_th_liferay$1frontend_management$1bar$1buttons_0.doInitBody();
            }
            do {
              out.write("\n\t\t");
              //  liferay-frontend:management-bar-display-buttons
              com.liferay.frontend.taglib.servlet.taglib.ManagementBarDisplayButtonsTag _jspx_th_liferay$1frontend_management$1bar$1display$1buttons_0 = (com.liferay.frontend.taglib.servlet.taglib.ManagementBarDisplayButtonsTag) _jspx_tagPool_liferay$1frontend_management$1bar$1display$1buttons_selectedDisplayStyle_portletURL_displayViews_nobody.get(com.liferay.frontend.taglib.servlet.taglib.ManagementBarDisplayButtonsTag.class);
              _jspx_th_liferay$1frontend_management$1bar$1display$1buttons_0.setPageContext(_jspx_page_context);
              _jspx_th_liferay$1frontend_management$1bar$1display$1buttons_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_management$1bar$1buttons_0);
              _jspx_th_liferay$1frontend_management$1bar$1display$1buttons_0.setDisplayViews( new String[] {"list"} );
              _jspx_th_liferay$1frontend_management$1bar$1display$1buttons_0.setPortletURL( portletURL );
              _jspx_th_liferay$1frontend_management$1bar$1display$1buttons_0.setSelectedDisplayStyle("list");
              int _jspx_eval_liferay$1frontend_management$1bar$1display$1buttons_0 = _jspx_th_liferay$1frontend_management$1bar$1display$1buttons_0.doStartTag();
              if (_jspx_th_liferay$1frontend_management$1bar$1display$1buttons_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_liferay$1frontend_management$1bar$1display$1buttons_selectedDisplayStyle_portletURL_displayViews_nobody.reuse(_jspx_th_liferay$1frontend_management$1bar$1display$1buttons_0);
                return;
              }
              _jspx_tagPool_liferay$1frontend_management$1bar$1display$1buttons_selectedDisplayStyle_portletURL_displayViews_nobody.reuse(_jspx_th_liferay$1frontend_management$1bar$1display$1buttons_0);
              out.write('\n');
              out.write('	');
              int evalDoAfterBody = _jspx_th_liferay$1frontend_management$1bar$1buttons_0.doAfterBody();
              if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                break;
            } while (true);
            if (_jspx_eval_liferay$1frontend_management$1bar$1buttons_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
              out = _jspx_page_context.popBody();
          }
          if (_jspx_th_liferay$1frontend_management$1bar$1buttons_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_liferay$1frontend_management$1bar$1buttons.reuse(_jspx_th_liferay$1frontend_management$1bar$1buttons_0);
            return;
          }
          _jspx_tagPool_liferay$1frontend_management$1bar$1buttons.reuse(_jspx_th_liferay$1frontend_management$1bar$1buttons_0);
          out.write("\n\n\t");
          //  liferay-frontend:management-bar-filters
          com.liferay.frontend.taglib.servlet.taglib.ManagementBarFiltersTag _jspx_th_liferay$1frontend_management$1bar$1filters_0 = (com.liferay.frontend.taglib.servlet.taglib.ManagementBarFiltersTag) _jspx_tagPool_liferay$1frontend_management$1bar$1filters.get(com.liferay.frontend.taglib.servlet.taglib.ManagementBarFiltersTag.class);
          _jspx_th_liferay$1frontend_management$1bar$1filters_0.setPageContext(_jspx_page_context);
          _jspx_th_liferay$1frontend_management$1bar$1filters_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_management$1bar_0);
          int _jspx_eval_liferay$1frontend_management$1bar$1filters_0 = _jspx_th_liferay$1frontend_management$1bar$1filters_0.doStartTag();
          if (_jspx_eval_liferay$1frontend_management$1bar$1filters_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            if (_jspx_eval_liferay$1frontend_management$1bar$1filters_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
              out = _jspx_page_context.pushBody();
              _jspx_th_liferay$1frontend_management$1bar$1filters_0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
              _jspx_th_liferay$1frontend_management$1bar$1filters_0.doInitBody();
            }
            do {
              out.write("\n\t\t");
              //  liferay-frontend:management-bar-navigation
              com.liferay.frontend.taglib.servlet.taglib.ManagementBarNavigationTag _jspx_th_liferay$1frontend_management$1bar$1navigation_0 = (com.liferay.frontend.taglib.servlet.taglib.ManagementBarNavigationTag) _jspx_tagPool_liferay$1frontend_management$1bar$1navigation_portletURL_navigationKeys_nobody.get(com.liferay.frontend.taglib.servlet.taglib.ManagementBarNavigationTag.class);
              _jspx_th_liferay$1frontend_management$1bar$1navigation_0.setPageContext(_jspx_page_context);
              _jspx_th_liferay$1frontend_management$1bar$1navigation_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_management$1bar$1filters_0);
              _jspx_th_liferay$1frontend_management$1bar$1navigation_0.setNavigationKeys( new String[] {"all"} );
              _jspx_th_liferay$1frontend_management$1bar$1navigation_0.setPortletURL( portletURL );
              int _jspx_eval_liferay$1frontend_management$1bar$1navigation_0 = _jspx_th_liferay$1frontend_management$1bar$1navigation_0.doStartTag();
              if (_jspx_th_liferay$1frontend_management$1bar$1navigation_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_liferay$1frontend_management$1bar$1navigation_portletURL_navigationKeys_nobody.reuse(_jspx_th_liferay$1frontend_management$1bar$1navigation_0);
                return;
              }
              _jspx_tagPool_liferay$1frontend_management$1bar$1navigation_portletURL_navigationKeys_nobody.reuse(_jspx_th_liferay$1frontend_management$1bar$1navigation_0);
              out.write("\n\n\t\t");
              //  liferay-frontend:management-bar-sort
              com.liferay.frontend.taglib.servlet.taglib.ManagementBarSortTag _jspx_th_liferay$1frontend_management$1bar$1sort_0 = (com.liferay.frontend.taglib.servlet.taglib.ManagementBarSortTag) _jspx_tagPool_liferay$1frontend_management$1bar$1sort_portletURL_orderColumns_orderByType_orderByCol_nobody.get(com.liferay.frontend.taglib.servlet.taglib.ManagementBarSortTag.class);
              _jspx_th_liferay$1frontend_management$1bar$1sort_0.setPageContext(_jspx_page_context);
              _jspx_th_liferay$1frontend_management$1bar$1sort_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_management$1bar$1filters_0);
              _jspx_th_liferay$1frontend_management$1bar$1sort_0.setOrderByCol( organizationItemSelectorViewDisplayContext.getOrderByCol() );
              _jspx_th_liferay$1frontend_management$1bar$1sort_0.setOrderByType( organizationItemSelectorViewDisplayContext.getOrderByType() );
              _jspx_th_liferay$1frontend_management$1bar$1sort_0.setOrderColumns( new String[] {"name", "type"} );
              _jspx_th_liferay$1frontend_management$1bar$1sort_0.setPortletURL( portletURL );
              int _jspx_eval_liferay$1frontend_management$1bar$1sort_0 = _jspx_th_liferay$1frontend_management$1bar$1sort_0.doStartTag();
              if (_jspx_th_liferay$1frontend_management$1bar$1sort_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_liferay$1frontend_management$1bar$1sort_portletURL_orderColumns_orderByType_orderByCol_nobody.reuse(_jspx_th_liferay$1frontend_management$1bar$1sort_0);
                return;
              }
              _jspx_tagPool_liferay$1frontend_management$1bar$1sort_portletURL_orderColumns_orderByType_orderByCol_nobody.reuse(_jspx_th_liferay$1frontend_management$1bar$1sort_0);
              out.write("\n\n\t\t<li>\n\t\t\t");
              if (_jspx_meth_liferay$1item$1selector_search_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1frontend_management$1bar$1filters_0, _jspx_page_context))
                return;
              out.write("\n\t\t</li>\n\t");
              int evalDoAfterBody = _jspx_th_liferay$1frontend_management$1bar$1filters_0.doAfterBody();
              if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                break;
            } while (true);
            if (_jspx_eval_liferay$1frontend_management$1bar$1filters_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
              out = _jspx_page_context.popBody();
          }
          if (_jspx_th_liferay$1frontend_management$1bar$1filters_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_liferay$1frontend_management$1bar$1filters.reuse(_jspx_th_liferay$1frontend_management$1bar$1filters_0);
            return;
          }
          _jspx_tagPool_liferay$1frontend_management$1bar$1filters.reuse(_jspx_th_liferay$1frontend_management$1bar$1filters_0);
          out.write('\n');
          int evalDoAfterBody = _jspx_th_liferay$1frontend_management$1bar_0.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
        if (_jspx_eval_liferay$1frontend_management$1bar_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
          out = _jspx_page_context.popBody();
      }
      if (_jspx_th_liferay$1frontend_management$1bar_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_liferay$1frontend_management$1bar_searchContainerId_includeCheckBox.reuse(_jspx_th_liferay$1frontend_management$1bar_0);
        return;
      }
      _jspx_tagPool_liferay$1frontend_management$1bar_searchContainerId_includeCheckBox.reuse(_jspx_th_liferay$1frontend_management$1bar_0);
      out.write("\n\n<div class=\"container-fluid-1280\" id=\"");
      if (_jspx_meth_portlet_namespace_0(_jspx_page_context))
        return;
      out.write("organizationSelectorWrapper\">\n\t");
      //  liferay-ui:search-container
      com.liferay.taglib.ui.SearchContainerTag _jspx_th_liferay$1ui_search$1container_0 = (com.liferay.taglib.ui.SearchContainerTag) _jspx_tagPool_liferay$1ui_search$1container_searchContainer_id.get(com.liferay.taglib.ui.SearchContainerTag.class);
      _jspx_th_liferay$1ui_search$1container_0.setPageContext(_jspx_page_context);
      _jspx_th_liferay$1ui_search$1container_0.setParent(null);
      _jspx_th_liferay$1ui_search$1container_0.setId("organizations");
      _jspx_th_liferay$1ui_search$1container_0.setSearchContainer( organizationItemSelectorViewDisplayContext.getSearchContainer() );
      int _jspx_eval_liferay$1ui_search$1container_0 = _jspx_th_liferay$1ui_search$1container_0.doStartTag();
      if (_jspx_eval_liferay$1ui_search$1container_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        java.lang.Integer total = null;
        com.liferay.portal.kernel.dao.search.SearchContainer searchContainer = null;
        total = (java.lang.Integer) _jspx_page_context.findAttribute("total");
        searchContainer = (com.liferay.portal.kernel.dao.search.SearchContainer) _jspx_page_context.findAttribute("searchContainer");
        out.write("\n\t\t");
        //  liferay-ui:search-container-row
        com.liferay.taglib.ui.SearchContainerRowTag _jspx_th_liferay$1ui_search$1container$1row_0 = (com.liferay.taglib.ui.SearchContainerRowTag) _jspx_tagPool_liferay$1ui_search$1container$1row_modelVar_keyProperty_cssClass_className.get(com.liferay.taglib.ui.SearchContainerRowTag.class);
        _jspx_th_liferay$1ui_search$1container$1row_0.setPageContext(_jspx_page_context);
        _jspx_th_liferay$1ui_search$1container$1row_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container_0);
        _jspx_th_liferay$1ui_search$1container$1row_0.setClassName("com.liferay.portal.kernel.model.Organization");
        _jspx_th_liferay$1ui_search$1container$1row_0.setCssClass("organization-row");
        _jspx_th_liferay$1ui_search$1container$1row_0.setKeyProperty("organizationId");
        _jspx_th_liferay$1ui_search$1container$1row_0.setModelVar("organization");
        int _jspx_eval_liferay$1ui_search$1container$1row_0 = _jspx_th_liferay$1ui_search$1container$1row_0.doStartTag();
        if (_jspx_eval_liferay$1ui_search$1container$1row_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          java.lang.Integer index = null;
          com.liferay.portal.kernel.model.Organization organization = null;
          com.liferay.portal.kernel.dao.search.ResultRow row = null;
          if (_jspx_eval_liferay$1ui_search$1container$1row_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
            out = _jspx_page_context.pushBody();
            _jspx_th_liferay$1ui_search$1container$1row_0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
            _jspx_th_liferay$1ui_search$1container$1row_0.doInitBody();
          }
          index = (java.lang.Integer) _jspx_page_context.findAttribute("index");
          organization = (com.liferay.portal.kernel.model.Organization) _jspx_page_context.findAttribute("organization");
          row = (com.liferay.portal.kernel.dao.search.ResultRow) _jspx_page_context.findAttribute("row");
          do {
            out.write("\n\n\t\t\t");

			Map<String, Object> data = new HashMap<>();

			data.put("id", organization.getOrganizationId());
			data.put("name", organization.getName());

			row.setData(data);
			
            out.write("\n\n\t\t\t");
            if (_jspx_meth_liferay$1ui_search$1container$1column$1text_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1ui_search$1container$1row_0, _jspx_page_context))
              return;
            out.write("\n\n\t\t\t");
            //  liferay-ui:search-container-column-text
            com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_1 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_cssClass_nobody.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
            _jspx_th_liferay$1ui_search$1container$1column$1text_1.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1ui_search$1container$1column$1text_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1row_0);
            _jspx_th_liferay$1ui_search$1container$1column$1text_1.setCssClass("table-cell-content");
            _jspx_th_liferay$1ui_search$1container$1column$1text_1.setName("path");
            _jspx_th_liferay$1ui_search$1container$1column$1text_1.setValue( organizationItemSelectorViewDisplayContext.getPath(organization) );
            int _jspx_eval_liferay$1ui_search$1container$1column$1text_1 = _jspx_th_liferay$1ui_search$1container$1column$1text_1.doStartTag();
            if (_jspx_th_liferay$1ui_search$1container$1column$1text_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_cssClass_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_1);
              return;
            }
            _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_cssClass_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_1);
            out.write("\n\n\t\t\t");
            if (_jspx_meth_liferay$1ui_search$1container$1column$1text_2((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1ui_search$1container$1row_0, _jspx_page_context))
              return;
            out.write("\n\t\t");
            int evalDoAfterBody = _jspx_th_liferay$1ui_search$1container$1row_0.doAfterBody();
            index = (java.lang.Integer) _jspx_page_context.findAttribute("index");
            organization = (com.liferay.portal.kernel.model.Organization) _jspx_page_context.findAttribute("organization");
            row = (com.liferay.portal.kernel.dao.search.ResultRow) _jspx_page_context.findAttribute("row");
            if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
              break;
          } while (true);
          if (_jspx_eval_liferay$1ui_search$1container$1row_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
            out = _jspx_page_context.popBody();
        }
        if (_jspx_th_liferay$1ui_search$1container$1row_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_liferay$1ui_search$1container$1row_modelVar_keyProperty_cssClass_className.reuse(_jspx_th_liferay$1ui_search$1container$1row_0);
          return;
        }
        _jspx_tagPool_liferay$1ui_search$1container$1row_modelVar_keyProperty_cssClass_className.reuse(_jspx_th_liferay$1ui_search$1container$1row_0);
        out.write("\n\n\t\t");
        //  liferay-ui:search-iterator
        com.liferay.taglib.ui.SearchIteratorTag _jspx_th_liferay$1ui_search$1iterator_0 = (com.liferay.taglib.ui.SearchIteratorTag) _jspx_tagPool_liferay$1ui_search$1iterator_searchContainer_markupView_displayStyle_nobody.get(com.liferay.taglib.ui.SearchIteratorTag.class);
        _jspx_th_liferay$1ui_search$1iterator_0.setPageContext(_jspx_page_context);
        _jspx_th_liferay$1ui_search$1iterator_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container_0);
        _jspx_th_liferay$1ui_search$1iterator_0.setDisplayStyle("list");
        _jspx_th_liferay$1ui_search$1iterator_0.setMarkupView("lexicon");
        _jspx_th_liferay$1ui_search$1iterator_0.setSearchContainer( organizationItemSelectorViewDisplayContext.getSearchContainer() );
        int _jspx_eval_liferay$1ui_search$1iterator_0 = _jspx_th_liferay$1ui_search$1iterator_0.doStartTag();
        if (_jspx_th_liferay$1ui_search$1iterator_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_liferay$1ui_search$1iterator_searchContainer_markupView_displayStyle_nobody.reuse(_jspx_th_liferay$1ui_search$1iterator_0);
          return;
        }
        _jspx_tagPool_liferay$1ui_search$1iterator_searchContainer_markupView_displayStyle_nobody.reuse(_jspx_th_liferay$1ui_search$1iterator_0);
        out.write('\n');
        out.write('	');
      }
      if (_jspx_th_liferay$1ui_search$1container_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_liferay$1ui_search$1container_searchContainer_id.reuse(_jspx_th_liferay$1ui_search$1container_0);
        return;
      }
      _jspx_tagPool_liferay$1ui_search$1container_searchContainer_id.reuse(_jspx_th_liferay$1ui_search$1container_0);
      out.write("\n</div>\n\n");
      //  aui:script
      com.liferay.taglib.aui.ScriptTag _jspx_th_aui_script_0 = (com.liferay.taglib.aui.ScriptTag) _jspx_tagPool_aui_script_use.get(com.liferay.taglib.aui.ScriptTag.class);
      _jspx_th_aui_script_0.setPageContext(_jspx_page_context);
      _jspx_th_aui_script_0.setParent(null);
      _jspx_th_aui_script_0.setUse("liferay-search-container");
      int _jspx_eval_aui_script_0 = _jspx_th_aui_script_0.doStartTag();
      if (_jspx_eval_aui_script_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        if (_jspx_eval_aui_script_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
          out = _jspx_page_context.pushBody();
          _jspx_th_aui_script_0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
          _jspx_th_aui_script_0.doInitBody();
        }
        do {
          out.write("\n\tvar searchContainer = Liferay.SearchContainer.get('");
          if (_jspx_meth_portlet_namespace_1((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_0, _jspx_page_context))
            return;
          out.write("organizations');\n\n\tsearchContainer.on(\n\t\t'rowToggled',\n\t\tfunction(event) {\n\t\t\tvar allSelectedElements = event.elements.allSelectedElements;\n\n\t\t\tvar arr = [];\n\n\t\t\tallSelectedElements.each(\n\t\t\t\tfunction() {\n\t\t\t\t\tvar row = this.ancestor('tr');\n\n\t\t\t\t\tvar data = row.getDOM().dataset;\n\n\t\t\t\t\tarr.push(\n\t\t\t\t\t\t{\n\t\t\t\t\t\t\tid: data.id,\n\t\t\t\t\t\t\tname: data.name\n\t\t\t\t\t\t}\n\t\t\t\t\t);\n\t\t\t\t}\n\t\t\t);\n\n\t\t\tLiferay.Util.getOpener().Liferay.fire(\n\t\t\t\t'");
          out.print( HtmlUtil.escapeJS(itemSelectedEventName) );
          out.write("',\n\t\t\t\t{\n\t\t\t\t\tdata: arr\n\t\t\t\t}\n\t\t\t);\n\t\t}\n\t);\n");
          int evalDoAfterBody = _jspx_th_aui_script_0.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
        if (_jspx_eval_aui_script_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
          out = _jspx_page_context.popBody();
      }
      if (_jspx_th_aui_script_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_aui_script_use.reuse(_jspx_th_aui_script_0);
        return;
      }
      _jspx_tagPool_aui_script_use.reuse(_jspx_th_aui_script_0);
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

  private boolean _jspx_meth_liferay$1item$1selector_search_0(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1frontend_management$1bar$1filters_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-item-selector:search
    com.liferay.item.selector.taglib.servlet.taglib.SearchTag _jspx_th_liferay$1item$1selector_search_0 = (com.liferay.item.selector.taglib.servlet.taglib.SearchTag) _jspx_tagPool_liferay$1item$1selector_search_nobody.get(com.liferay.item.selector.taglib.servlet.taglib.SearchTag.class);
    _jspx_th_liferay$1item$1selector_search_0.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1item$1selector_search_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_management$1bar$1filters_0);
    int _jspx_eval_liferay$1item$1selector_search_0 = _jspx_th_liferay$1item$1selector_search_0.doStartTag();
    if (_jspx_th_liferay$1item$1selector_search_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1item$1selector_search_nobody.reuse(_jspx_th_liferay$1item$1selector_search_0);
      return true;
    }
    _jspx_tagPool_liferay$1item$1selector_search_nobody.reuse(_jspx_th_liferay$1item$1selector_search_0);
    return false;
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

  private boolean _jspx_meth_liferay$1ui_search$1container$1column$1text_0(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1ui_search$1container$1row_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:search-container-column-text
    com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_0 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text_property_cssClass_nobody.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
    _jspx_th_liferay$1ui_search$1container$1column$1text_0.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_search$1container$1column$1text_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1row_0);
    _jspx_th_liferay$1ui_search$1container$1column$1text_0.setCssClass("table-cell-content");
    _jspx_th_liferay$1ui_search$1container$1column$1text_0.setProperty("name");
    int _jspx_eval_liferay$1ui_search$1container$1column$1text_0 = _jspx_th_liferay$1ui_search$1container$1column$1text_0.doStartTag();
    if (_jspx_th_liferay$1ui_search$1container$1column$1text_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_search$1container$1column$1text_property_cssClass_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_0);
      return true;
    }
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_property_cssClass_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_0);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_search$1container$1column$1text_2(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1ui_search$1container$1row_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:search-container-column-text
    com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_2 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text_property_cssClass_nobody.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
    _jspx_th_liferay$1ui_search$1container$1column$1text_2.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_search$1container$1column$1text_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1row_0);
    _jspx_th_liferay$1ui_search$1container$1column$1text_2.setCssClass("table-cell-content");
    _jspx_th_liferay$1ui_search$1container$1column$1text_2.setProperty("type");
    int _jspx_eval_liferay$1ui_search$1container$1column$1text_2 = _jspx_th_liferay$1ui_search$1container$1column$1text_2.doStartTag();
    if (_jspx_th_liferay$1ui_search$1container$1column$1text_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_search$1container$1column$1text_property_cssClass_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_2);
      return true;
    }
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_property_cssClass_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_2);
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
}
