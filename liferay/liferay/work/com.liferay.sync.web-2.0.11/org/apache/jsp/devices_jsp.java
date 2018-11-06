package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.JSPNavigationItemList;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.search.ResultRow;
import com.liferay.portal.kernel.dao.search.RowChecker;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.portlet.PortletURLUtil;
import com.liferay.portal.kernel.security.permission.ResourceActionsUtil;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.sync.constants.SyncConstants;
import com.liferay.sync.constants.SyncDeviceConstants;
import com.liferay.sync.constants.SyncPermissionsConstants;
import com.liferay.sync.constants.SyncPortletKeys;
import com.liferay.sync.exception.OAuthPortletUndeployedException;
import com.liferay.sync.model.SyncDevice;
import com.liferay.sync.oauth.helper.SyncOAuthHelperUtil;
import com.liferay.sync.service.SyncDeviceLocalServiceUtil;
import com.liferay.sync.service.configuration.SyncServiceConfigurationKeys;
import com.liferay.sync.web.internal.configuration.SyncServiceConfigurationValues;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import javax.portlet.PortletURL;

public final class devices_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(1);
    _jspx_dependants.add("/init.jsp");
  }

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_form_name_method;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1frontend_management$1bar$1navigation_portletURL_navigationKeys_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_if_test;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container$1column$1text_property_name_cssClass_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container$1row_modelVar_keyProperty_escapedModel_className;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1frontend_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1frontend_management$1bar$1filters;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1frontend_management$1bar$1display$1buttons_selectedDisplayStyle_portletURL_displayViews_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1iterator_markupView_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1frontend_management$1bar;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_input_value_type_name_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container$1results_results_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container$1column$1text_orderable_name_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container_iteratorURL_emptyResultsMessage;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_translate_name_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container$1column$1jsp_path_cssClass_align_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1frontend_management$1bar$1sort_portletURL_orderColumns_orderByType_orderByCol_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container$1column$1text_property_name_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1theme_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_form_name_action;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container$1column$1date_property_name_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1frontend_management$1bar$1buttons;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_input$1search_placeholder_markupView_nobody;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_aui_form_name_method = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1frontend_management$1bar$1navigation_portletURL_navigationKeys_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_if_test = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_property_name_cssClass_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container$1row_modelVar_keyProperty_escapedModel_className = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1frontend_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1frontend_management$1bar$1filters = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1frontend_management$1bar$1display$1buttons_selectedDisplayStyle_portletURL_displayViews_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1iterator_markupView_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1frontend_management$1bar = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_input_value_type_name_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container$1results_results_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_orderable_name_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container_iteratorURL_emptyResultsMessage = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_translate_name_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container$1column$1jsp_path_cssClass_align_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1frontend_management$1bar$1sort_portletURL_orderColumns_orderByType_orderByCol_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_property_name_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1theme_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_form_name_action = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container$1column$1date_property_name_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1frontend_management$1bar$1buttons = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_input$1search_placeholder_markupView_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_aui_form_name_method.release();
    _jspx_tagPool_liferay$1frontend_management$1bar$1navigation_portletURL_navigationKeys_nobody.release();
    _jspx_tagPool_c_if_test.release();
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_property_name_cssClass_nobody.release();
    _jspx_tagPool_liferay$1ui_search$1container$1row_modelVar_keyProperty_escapedModel_className.release();
    _jspx_tagPool_liferay$1frontend_defineObjects_nobody.release();
    _jspx_tagPool_liferay$1frontend_management$1bar$1filters.release();
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_nobody.release();
    _jspx_tagPool_liferay$1frontend_management$1bar$1display$1buttons_selectedDisplayStyle_portletURL_displayViews_nobody.release();
    _jspx_tagPool_liferay$1ui_search$1iterator_markupView_nobody.release();
    _jspx_tagPool_liferay$1frontend_management$1bar.release();
    _jspx_tagPool_aui_input_value_type_name_nobody.release();
    _jspx_tagPool_liferay$1ui_search$1container$1results_results_nobody.release();
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_orderable_name_nobody.release();
    _jspx_tagPool_portlet_defineObjects_nobody.release();
    _jspx_tagPool_liferay$1ui_search$1container_iteratorURL_emptyResultsMessage.release();
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_translate_name_nobody.release();
    _jspx_tagPool_liferay$1ui_search$1container$1column$1jsp_path_cssClass_align_nobody.release();
    _jspx_tagPool_liferay$1frontend_management$1bar$1sort_portletURL_orderColumns_orderByType_orderByCol_nobody.release();
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_property_name_nobody.release();
    _jspx_tagPool_liferay$1theme_defineObjects_nobody.release();
    _jspx_tagPool_aui_form_name_action.release();
    _jspx_tagPool_liferay$1ui_search$1container$1column$1date_property_name_nobody.release();
    _jspx_tagPool_liferay$1frontend_management$1bar$1buttons.release();
    _jspx_tagPool_liferay$1ui_input$1search_placeholder_markupView_nobody.release();
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

String tabs1 = ParamUtil.getString(request, "tabs1", "devices");

String keywords = ParamUtil.getString(request, "keywords");

int delta = ParamUtil.getInteger(request, "delta", SearchContainer.DEFAULT_DELTA);
String orderByCol = ParamUtil.getString(request, "orderByCol", "name");
String orderByType = ParamUtil.getString(request, "orderByType", "asc");

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("tabs1", tabs1);
portletURL.setParameter("delta", String.valueOf(delta));

      out.write('\n');
      out.write('\n');
      //  liferay-frontend:management-bar
      com.liferay.frontend.taglib.servlet.taglib.ManagementBarTag _jspx_th_liferay$1frontend_management$1bar_0 = (com.liferay.frontend.taglib.servlet.taglib.ManagementBarTag) _jspx_tagPool_liferay$1frontend_management$1bar.get(com.liferay.frontend.taglib.servlet.taglib.ManagementBarTag.class);
      _jspx_th_liferay$1frontend_management$1bar_0.setPageContext(_jspx_page_context);
      _jspx_th_liferay$1frontend_management$1bar_0.setParent(null);
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
          //  c:if
          com.liferay.taglib.core.IfTag _jspx_th_c_if_0 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
          _jspx_th_c_if_0.setPageContext(_jspx_page_context);
          _jspx_th_c_if_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_management$1bar_0);
          _jspx_th_c_if_0.setTest( Validator.isNull(keywords) );
          int _jspx_eval_c_if_0 = _jspx_th_c_if_0.doStartTag();
          if (_jspx_eval_c_if_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t");
            //  liferay-frontend:management-bar-buttons
            com.liferay.frontend.taglib.servlet.taglib.ManagementBarButtonsTag _jspx_th_liferay$1frontend_management$1bar$1buttons_0 = (com.liferay.frontend.taglib.servlet.taglib.ManagementBarButtonsTag) _jspx_tagPool_liferay$1frontend_management$1bar$1buttons.get(com.liferay.frontend.taglib.servlet.taglib.ManagementBarButtonsTag.class);
            _jspx_th_liferay$1frontend_management$1bar$1buttons_0.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1frontend_management$1bar$1buttons_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_0);
            int _jspx_eval_liferay$1frontend_management$1bar$1buttons_0 = _jspx_th_liferay$1frontend_management$1bar$1buttons_0.doStartTag();
            if (_jspx_eval_liferay$1frontend_management$1bar$1buttons_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              if (_jspx_eval_liferay$1frontend_management$1bar$1buttons_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                out = _jspx_page_context.pushBody();
                _jspx_th_liferay$1frontend_management$1bar$1buttons_0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                _jspx_th_liferay$1frontend_management$1bar$1buttons_0.doInitBody();
              }
              do {
                out.write("\n\t\t\t");
                //  liferay-frontend:management-bar-display-buttons
                com.liferay.frontend.taglib.servlet.taglib.ManagementBarDisplayButtonsTag _jspx_th_liferay$1frontend_management$1bar$1display$1buttons_0 = (com.liferay.frontend.taglib.servlet.taglib.ManagementBarDisplayButtonsTag) _jspx_tagPool_liferay$1frontend_management$1bar$1display$1buttons_selectedDisplayStyle_portletURL_displayViews_nobody.get(com.liferay.frontend.taglib.servlet.taglib.ManagementBarDisplayButtonsTag.class);
                _jspx_th_liferay$1frontend_management$1bar$1display$1buttons_0.setPageContext(_jspx_page_context);
                _jspx_th_liferay$1frontend_management$1bar$1display$1buttons_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_management$1bar$1buttons_0);
                _jspx_th_liferay$1frontend_management$1bar$1display$1buttons_0.setDisplayViews( new String[] {"list"} );
                _jspx_th_liferay$1frontend_management$1bar$1display$1buttons_0.setPortletURL( PortletURLUtil.clone(portletURL, liferayPortletResponse) );
                _jspx_th_liferay$1frontend_management$1bar$1display$1buttons_0.setSelectedDisplayStyle("list");
                int _jspx_eval_liferay$1frontend_management$1bar$1display$1buttons_0 = _jspx_th_liferay$1frontend_management$1bar$1display$1buttons_0.doStartTag();
                if (_jspx_th_liferay$1frontend_management$1bar$1display$1buttons_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_liferay$1frontend_management$1bar$1display$1buttons_selectedDisplayStyle_portletURL_displayViews_nobody.reuse(_jspx_th_liferay$1frontend_management$1bar$1display$1buttons_0);
                  return;
                }
                _jspx_tagPool_liferay$1frontend_management$1bar$1display$1buttons_selectedDisplayStyle_portletURL_displayViews_nobody.reuse(_jspx_th_liferay$1frontend_management$1bar$1display$1buttons_0);
                out.write("\n\t\t");
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
            out.write("\n\n\t\t");
            //  liferay-frontend:management-bar-filters
            com.liferay.frontend.taglib.servlet.taglib.ManagementBarFiltersTag _jspx_th_liferay$1frontend_management$1bar$1filters_0 = (com.liferay.frontend.taglib.servlet.taglib.ManagementBarFiltersTag) _jspx_tagPool_liferay$1frontend_management$1bar$1filters.get(com.liferay.frontend.taglib.servlet.taglib.ManagementBarFiltersTag.class);
            _jspx_th_liferay$1frontend_management$1bar$1filters_0.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1frontend_management$1bar$1filters_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_0);
            int _jspx_eval_liferay$1frontend_management$1bar$1filters_0 = _jspx_th_liferay$1frontend_management$1bar$1filters_0.doStartTag();
            if (_jspx_eval_liferay$1frontend_management$1bar$1filters_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              if (_jspx_eval_liferay$1frontend_management$1bar$1filters_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                out = _jspx_page_context.pushBody();
                _jspx_th_liferay$1frontend_management$1bar$1filters_0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                _jspx_th_liferay$1frontend_management$1bar$1filters_0.doInitBody();
              }
              do {
                out.write("\n\t\t\t");
                //  liferay-frontend:management-bar-navigation
                com.liferay.frontend.taglib.servlet.taglib.ManagementBarNavigationTag _jspx_th_liferay$1frontend_management$1bar$1navigation_0 = (com.liferay.frontend.taglib.servlet.taglib.ManagementBarNavigationTag) _jspx_tagPool_liferay$1frontend_management$1bar$1navigation_portletURL_navigationKeys_nobody.get(com.liferay.frontend.taglib.servlet.taglib.ManagementBarNavigationTag.class);
                _jspx_th_liferay$1frontend_management$1bar$1navigation_0.setPageContext(_jspx_page_context);
                _jspx_th_liferay$1frontend_management$1bar$1navigation_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_management$1bar$1filters_0);
                _jspx_th_liferay$1frontend_management$1bar$1navigation_0.setNavigationKeys( new String[] {"all"} );
                _jspx_th_liferay$1frontend_management$1bar$1navigation_0.setPortletURL( PortletURLUtil.clone(portletURL, liferayPortletResponse) );
                int _jspx_eval_liferay$1frontend_management$1bar$1navigation_0 = _jspx_th_liferay$1frontend_management$1bar$1navigation_0.doStartTag();
                if (_jspx_th_liferay$1frontend_management$1bar$1navigation_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_liferay$1frontend_management$1bar$1navigation_portletURL_navigationKeys_nobody.reuse(_jspx_th_liferay$1frontend_management$1bar$1navigation_0);
                  return;
                }
                _jspx_tagPool_liferay$1frontend_management$1bar$1navigation_portletURL_navigationKeys_nobody.reuse(_jspx_th_liferay$1frontend_management$1bar$1navigation_0);
                out.write("\n\n\t\t\t");
                //  liferay-frontend:management-bar-sort
                com.liferay.frontend.taglib.servlet.taglib.ManagementBarSortTag _jspx_th_liferay$1frontend_management$1bar$1sort_0 = (com.liferay.frontend.taglib.servlet.taglib.ManagementBarSortTag) _jspx_tagPool_liferay$1frontend_management$1bar$1sort_portletURL_orderColumns_orderByType_orderByCol_nobody.get(com.liferay.frontend.taglib.servlet.taglib.ManagementBarSortTag.class);
                _jspx_th_liferay$1frontend_management$1bar$1sort_0.setPageContext(_jspx_page_context);
                _jspx_th_liferay$1frontend_management$1bar$1sort_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_management$1bar$1filters_0);
                _jspx_th_liferay$1frontend_management$1bar$1sort_0.setOrderByCol( orderByCol );
                _jspx_th_liferay$1frontend_management$1bar$1sort_0.setOrderByType( orderByType );
                _jspx_th_liferay$1frontend_management$1bar$1sort_0.setOrderColumns( new String[] {"build", "last-seen", "name", "type"} );
                _jspx_th_liferay$1frontend_management$1bar$1sort_0.setPortletURL( PortletURLUtil.clone(portletURL, liferayPortletResponse) );
                int _jspx_eval_liferay$1frontend_management$1bar$1sort_0 = _jspx_th_liferay$1frontend_management$1bar$1sort_0.doStartTag();
                if (_jspx_th_liferay$1frontend_management$1bar$1sort_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_liferay$1frontend_management$1bar$1sort_portletURL_orderColumns_orderByType_orderByCol_nobody.reuse(_jspx_th_liferay$1frontend_management$1bar$1sort_0);
                  return;
                }
                _jspx_tagPool_liferay$1frontend_management$1bar$1sort_portletURL_orderColumns_orderByType_orderByCol_nobody.reuse(_jspx_th_liferay$1frontend_management$1bar$1sort_0);
                out.write("\n\n\t\t\t");

			PortletURL searchURL = renderResponse.createRenderURL();

			searchURL.setParameter("tabs1", tabs1);
			
                out.write("\n\n\t\t\t<li>\n\t\t\t\t");
                //  aui:form
                com.liferay.taglib.aui.FormTag _jspx_th_aui_form_0 = (com.liferay.taglib.aui.FormTag) _jspx_tagPool_aui_form_name_action.get(com.liferay.taglib.aui.FormTag.class);
                _jspx_th_aui_form_0.setPageContext(_jspx_page_context);
                _jspx_th_aui_form_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_management$1bar$1filters_0);
                _jspx_th_aui_form_0.setAction( searchURL.toString() );
                _jspx_th_aui_form_0.setName("searchFm");
                int _jspx_eval_aui_form_0 = _jspx_th_aui_form_0.doStartTag();
                if (_jspx_eval_aui_form_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  out.write("\n\t\t\t\t\t");
                  //  liferay-ui:input-search
                  com.liferay.taglib.ui.InputSearchTag _jspx_th_liferay$1ui_input$1search_0 = (com.liferay.taglib.ui.InputSearchTag) _jspx_tagPool_liferay$1ui_input$1search_placeholder_markupView_nobody.get(com.liferay.taglib.ui.InputSearchTag.class);
                  _jspx_th_liferay$1ui_input$1search_0.setPageContext(_jspx_page_context);
                  _jspx_th_liferay$1ui_input$1search_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
                  _jspx_th_liferay$1ui_input$1search_0.setMarkupView("lexicon");
                  _jspx_th_liferay$1ui_input$1search_0.setPlaceholder( LanguageUtil.get(request, "search") );
                  int _jspx_eval_liferay$1ui_input$1search_0 = _jspx_th_liferay$1ui_input$1search_0.doStartTag();
                  if (_jspx_th_liferay$1ui_input$1search_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_liferay$1ui_input$1search_placeholder_markupView_nobody.reuse(_jspx_th_liferay$1ui_input$1search_0);
                    return;
                  }
                  _jspx_tagPool_liferay$1ui_input$1search_placeholder_markupView_nobody.reuse(_jspx_th_liferay$1ui_input$1search_0);
                  out.write("\n\t\t\t\t");
                }
                if (_jspx_th_aui_form_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_aui_form_name_action.reuse(_jspx_th_aui_form_0);
                  return;
                }
                _jspx_tagPool_aui_form_name_action.reuse(_jspx_th_aui_form_0);
                out.write("\n\t\t\t</li>\n\t\t");
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
            out.write('	');
          }
          if (_jspx_th_c_if_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
            return;
          }
          _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
          out.write('\n');
          int evalDoAfterBody = _jspx_th_liferay$1frontend_management$1bar_0.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
        if (_jspx_eval_liferay$1frontend_management$1bar_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
          out = _jspx_page_context.popBody();
      }
      if (_jspx_th_liferay$1frontend_management$1bar_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_liferay$1frontend_management$1bar.reuse(_jspx_th_liferay$1frontend_management$1bar_0);
        return;
      }
      _jspx_tagPool_liferay$1frontend_management$1bar.reuse(_jspx_th_liferay$1frontend_management$1bar_0);
      out.write("\n\n<div class=\"container-fluid-1280\">\n\t");
      //  aui:form
      com.liferay.taglib.aui.FormTag _jspx_th_aui_form_1 = (com.liferay.taglib.aui.FormTag) _jspx_tagPool_aui_form_name_method.get(com.liferay.taglib.aui.FormTag.class);
      _jspx_th_aui_form_1.setPageContext(_jspx_page_context);
      _jspx_th_aui_form_1.setParent(null);
      _jspx_th_aui_form_1.setMethod("post");
      _jspx_th_aui_form_1.setName("fm");
      int _jspx_eval_aui_form_1 = _jspx_th_aui_form_1.doStartTag();
      if (_jspx_eval_aui_form_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        out.write("\n\t\t");
        //  aui:input
        com.liferay.taglib.aui.InputTag _jspx_th_aui_input_0 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
        _jspx_th_aui_input_0.setPageContext(_jspx_page_context);
        _jspx_th_aui_input_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_1);
        _jspx_th_aui_input_0.setName("redirect");
        _jspx_th_aui_input_0.setType("hidden");
        _jspx_th_aui_input_0.setValue( currentURL );
        int _jspx_eval_aui_input_0 = _jspx_th_aui_input_0.doStartTag();
        if (_jspx_th_aui_input_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_0);
          return;
        }
        _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_0);
        out.write("\n\n\t\t");
        //  liferay-ui:search-container
        com.liferay.taglib.ui.SearchContainerTag _jspx_th_liferay$1ui_search$1container_0 = (com.liferay.taglib.ui.SearchContainerTag) _jspx_tagPool_liferay$1ui_search$1container_iteratorURL_emptyResultsMessage.get(com.liferay.taglib.ui.SearchContainerTag.class);
        _jspx_th_liferay$1ui_search$1container_0.setPageContext(_jspx_page_context);
        _jspx_th_liferay$1ui_search$1container_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_1);
        _jspx_th_liferay$1ui_search$1container_0.setEmptyResultsMessage("no-devices-were-found");
        _jspx_th_liferay$1ui_search$1container_0.setIteratorURL( portletURL );
        int _jspx_eval_liferay$1ui_search$1container_0 = _jspx_th_liferay$1ui_search$1container_0.doStartTag();
        if (_jspx_eval_liferay$1ui_search$1container_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          java.lang.Integer total = null;
          com.liferay.portal.kernel.dao.search.SearchContainer searchContainer = null;
          total = (java.lang.Integer) _jspx_page_context.findAttribute("total");
          searchContainer = (com.liferay.portal.kernel.dao.search.SearchContainer) _jspx_page_context.findAttribute("searchContainer");
          out.write("\n\n\t\t\t");

			List<SyncDevice> syncDevices = new ArrayList<>();

			OrderByComparator orderByComparator = null;

			if (orderByCol.equals("name")) {
				orderByComparator = OrderByComparatorFactoryUtil.create("SyncDevice", "userName", orderByType.equals("asc"));
			}
			else if (orderByCol.equals("build")) {
				orderByComparator = OrderByComparatorFactoryUtil.create("SyncDevice", "buildNumber", orderByType.equals("asc"));
			}
			else if (orderByCol.equals("last-seen")) {
				orderByComparator = OrderByComparatorFactoryUtil.create("SyncDevice", "modifiedDate", orderByType.equals("asc"));
			}
			else {
				OrderByComparatorFactoryUtil.create("SyncDevice", orderByCol, orderByType.equals("asc"));
			}

			String portletId = (String)request.getAttribute(WebKeys.PORTLET_ID);

			if (portletId.equals(SyncPortletKeys.SYNC_ADMIN_PORTLET)) {
				syncDevices = SyncDeviceLocalServiceUtil.search(themeDisplay.getCompanyId(), keywords, searchContainer.getStart(), searchContainer.getEnd(), orderByComparator);
			}
			else {
				syncDevices = SyncDeviceLocalServiceUtil.getSyncDevices(themeDisplay.getUserId(), searchContainer.getStart(), searchContainer.getEnd(), orderByComparator);
			}
			
          out.write("\n\n\t\t\t");
          //  liferay-ui:search-container-results
          java.util.List results = null;
          java.lang.Integer deprecatedTotal = null;
          com.liferay.taglib.ui.SearchContainerResultsTag _jspx_th_liferay$1ui_search$1container$1results_0 = (com.liferay.taglib.ui.SearchContainerResultsTag) _jspx_tagPool_liferay$1ui_search$1container$1results_results_nobody.get(com.liferay.taglib.ui.SearchContainerResultsTag.class);
          _jspx_th_liferay$1ui_search$1container$1results_0.setPageContext(_jspx_page_context);
          _jspx_th_liferay$1ui_search$1container$1results_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container_0);
          _jspx_th_liferay$1ui_search$1container$1results_0.setResults( syncDevices );
          int _jspx_eval_liferay$1ui_search$1container$1results_0 = _jspx_th_liferay$1ui_search$1container$1results_0.doStartTag();
          results = (java.util.List) _jspx_page_context.findAttribute("results");
          deprecatedTotal = (java.lang.Integer) _jspx_page_context.findAttribute("deprecatedTotal");
          if (_jspx_th_liferay$1ui_search$1container$1results_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_liferay$1ui_search$1container$1results_results_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1results_0);
            return;
          }
          results = (java.util.List) _jspx_page_context.findAttribute("results");
          deprecatedTotal = (java.lang.Integer) _jspx_page_context.findAttribute("deprecatedTotal");
          _jspx_tagPool_liferay$1ui_search$1container$1results_results_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1results_0);
          out.write("\n\n\t\t\t");
          //  liferay-ui:search-container-row
          com.liferay.taglib.ui.SearchContainerRowTag _jspx_th_liferay$1ui_search$1container$1row_0 = (com.liferay.taglib.ui.SearchContainerRowTag) _jspx_tagPool_liferay$1ui_search$1container$1row_modelVar_keyProperty_escapedModel_className.get(com.liferay.taglib.ui.SearchContainerRowTag.class);
          _jspx_th_liferay$1ui_search$1container$1row_0.setPageContext(_jspx_page_context);
          _jspx_th_liferay$1ui_search$1container$1row_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container_0);
          _jspx_th_liferay$1ui_search$1container$1row_0.setClassName("com.liferay.sync.model.SyncDevice");
          _jspx_th_liferay$1ui_search$1container$1row_0.setEscapedModel( true );
          _jspx_th_liferay$1ui_search$1container$1row_0.setKeyProperty("syncDeviceId");
          _jspx_th_liferay$1ui_search$1container$1row_0.setModelVar("syncDevice");
          int _jspx_eval_liferay$1ui_search$1container$1row_0 = _jspx_th_liferay$1ui_search$1container$1row_0.doStartTag();
          if (_jspx_eval_liferay$1ui_search$1container$1row_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            java.lang.Integer index = null;
            com.liferay.sync.model.SyncDevice syncDevice = null;
            com.liferay.portal.kernel.dao.search.ResultRow row = null;
            if (_jspx_eval_liferay$1ui_search$1container$1row_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
              out = _jspx_page_context.pushBody();
              _jspx_th_liferay$1ui_search$1container$1row_0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
              _jspx_th_liferay$1ui_search$1container$1row_0.doInitBody();
            }
            index = (java.lang.Integer) _jspx_page_context.findAttribute("index");
            syncDevice = (com.liferay.sync.model.SyncDevice) _jspx_page_context.findAttribute("syncDevice");
            row = (com.liferay.portal.kernel.dao.search.ResultRow) _jspx_page_context.findAttribute("row");
            do {
              out.write("\n\t\t\t\t");
              if (_jspx_meth_liferay$1ui_search$1container$1column$1text_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1ui_search$1container$1row_0, _jspx_page_context))
                return;
              out.write("\n\n\t\t\t\t");
              //  liferay-ui:search-container-column-text
              com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_1 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_nobody.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
              _jspx_th_liferay$1ui_search$1container$1column$1text_1.setPageContext(_jspx_page_context);
              _jspx_th_liferay$1ui_search$1container$1column$1text_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1row_0);
              _jspx_th_liferay$1ui_search$1container$1column$1text_1.setName("location");
              _jspx_th_liferay$1ui_search$1container$1column$1text_1.setValue( syncDevice.getHostname() );
              int _jspx_eval_liferay$1ui_search$1container$1column$1text_1 = _jspx_th_liferay$1ui_search$1container$1column$1text_1.doStartTag();
              if (_jspx_th_liferay$1ui_search$1container$1column$1text_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_1);
                return;
              }
              _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_1);
              out.write("\n\n\t\t\t\t");
              //  liferay-ui:search-container-column-text
              com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_2 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text_orderable_name_nobody.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
              _jspx_th_liferay$1ui_search$1container$1column$1text_2.setPageContext(_jspx_page_context);
              _jspx_th_liferay$1ui_search$1container$1column$1text_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1row_0);
              _jspx_th_liferay$1ui_search$1container$1column$1text_2.setName("type");
              _jspx_th_liferay$1ui_search$1container$1column$1text_2.setOrderable( true );
              int _jspx_eval_liferay$1ui_search$1container$1column$1text_2 = _jspx_th_liferay$1ui_search$1container$1column$1text_2.doStartTag();
              if (_jspx_th_liferay$1ui_search$1container$1column$1text_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_liferay$1ui_search$1container$1column$1text_orderable_name_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_2);
                return;
              }
              _jspx_tagPool_liferay$1ui_search$1container$1column$1text_orderable_name_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_2);
              out.write("\n\n\t\t\t\t");
              if (_jspx_meth_liferay$1ui_search$1container$1column$1text_3((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1ui_search$1container$1row_0, _jspx_page_context))
                return;
              out.write("\n\n\t\t\t\t");
              if (_jspx_meth_liferay$1ui_search$1container$1column$1date_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1ui_search$1container$1row_0, _jspx_page_context))
                return;
              out.write("\n\n\t\t\t\t");
              //  liferay-ui:search-container-column-text
              com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_4 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_translate_name_nobody.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
              _jspx_th_liferay$1ui_search$1container$1column$1text_4.setPageContext(_jspx_page_context);
              _jspx_th_liferay$1ui_search$1container$1column$1text_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1row_0);
              _jspx_th_liferay$1ui_search$1container$1column$1text_4.setName("status");
              _jspx_th_liferay$1ui_search$1container$1column$1text_4.setTranslate( true );
              _jspx_th_liferay$1ui_search$1container$1column$1text_4.setValue( SyncDeviceConstants.getStatusLabel(syncDevice.getStatus()) );
              int _jspx_eval_liferay$1ui_search$1container$1column$1text_4 = _jspx_th_liferay$1ui_search$1container$1column$1text_4.doStartTag();
              if (_jspx_th_liferay$1ui_search$1container$1column$1text_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_translate_name_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_4);
                return;
              }
              _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_translate_name_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_4);
              out.write("\n\n\t\t\t\t");
              if (_jspx_meth_liferay$1ui_search$1container$1column$1jsp_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1ui_search$1container$1row_0, _jspx_page_context))
                return;
              out.write("\n\t\t\t");
              int evalDoAfterBody = _jspx_th_liferay$1ui_search$1container$1row_0.doAfterBody();
              index = (java.lang.Integer) _jspx_page_context.findAttribute("index");
              syncDevice = (com.liferay.sync.model.SyncDevice) _jspx_page_context.findAttribute("syncDevice");
              row = (com.liferay.portal.kernel.dao.search.ResultRow) _jspx_page_context.findAttribute("row");
              if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                break;
            } while (true);
            if (_jspx_eval_liferay$1ui_search$1container$1row_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
              out = _jspx_page_context.popBody();
          }
          if (_jspx_th_liferay$1ui_search$1container$1row_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_liferay$1ui_search$1container$1row_modelVar_keyProperty_escapedModel_className.reuse(_jspx_th_liferay$1ui_search$1container$1row_0);
            return;
          }
          _jspx_tagPool_liferay$1ui_search$1container$1row_modelVar_keyProperty_escapedModel_className.reuse(_jspx_th_liferay$1ui_search$1container$1row_0);
          out.write("\n\n\t\t\t");
          if (_jspx_meth_liferay$1ui_search$1iterator_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1ui_search$1container_0, _jspx_page_context))
            return;
          out.write("\n\t\t");
        }
        if (_jspx_th_liferay$1ui_search$1container_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_liferay$1ui_search$1container_iteratorURL_emptyResultsMessage.reuse(_jspx_th_liferay$1ui_search$1container_0);
          return;
        }
        _jspx_tagPool_liferay$1ui_search$1container_iteratorURL_emptyResultsMessage.reuse(_jspx_th_liferay$1ui_search$1container_0);
        out.write('\n');
        out.write('	');
      }
      if (_jspx_th_aui_form_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_aui_form_name_method.reuse(_jspx_th_aui_form_1);
        return;
      }
      _jspx_tagPool_aui_form_name_method.reuse(_jspx_th_aui_form_1);
      out.write("\n</div>");
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

  private boolean _jspx_meth_liferay$1ui_search$1container$1column$1text_0(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1ui_search$1container$1row_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:search-container-column-text
    com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_0 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text_property_name_cssClass_nobody.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
    _jspx_th_liferay$1ui_search$1container$1column$1text_0.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_search$1container$1column$1text_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1row_0);
    _jspx_th_liferay$1ui_search$1container$1column$1text_0.setCssClass("content-column name-column title-column");
    _jspx_th_liferay$1ui_search$1container$1column$1text_0.setName("name");
    _jspx_th_liferay$1ui_search$1container$1column$1text_0.setProperty("userName");
    int _jspx_eval_liferay$1ui_search$1container$1column$1text_0 = _jspx_th_liferay$1ui_search$1container$1column$1text_0.doStartTag();
    if (_jspx_th_liferay$1ui_search$1container$1column$1text_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_search$1container$1column$1text_property_name_cssClass_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_0);
      return true;
    }
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_property_name_cssClass_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_0);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_search$1container$1column$1text_3(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1ui_search$1container$1row_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:search-container-column-text
    com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_3 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text_property_name_nobody.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
    _jspx_th_liferay$1ui_search$1container$1column$1text_3.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_search$1container$1column$1text_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1row_0);
    _jspx_th_liferay$1ui_search$1container$1column$1text_3.setName("build");
    _jspx_th_liferay$1ui_search$1container$1column$1text_3.setProperty("buildNumber");
    int _jspx_eval_liferay$1ui_search$1container$1column$1text_3 = _jspx_th_liferay$1ui_search$1container$1column$1text_3.doStartTag();
    if (_jspx_th_liferay$1ui_search$1container$1column$1text_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_search$1container$1column$1text_property_name_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_3);
      return true;
    }
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_property_name_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_3);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_search$1container$1column$1date_0(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1ui_search$1container$1row_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:search-container-column-date
    com.liferay.taglib.ui.SearchContainerColumnDateTag _jspx_th_liferay$1ui_search$1container$1column$1date_0 = (com.liferay.taglib.ui.SearchContainerColumnDateTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1date_property_name_nobody.get(com.liferay.taglib.ui.SearchContainerColumnDateTag.class);
    _jspx_th_liferay$1ui_search$1container$1column$1date_0.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_search$1container$1column$1date_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1row_0);
    _jspx_th_liferay$1ui_search$1container$1column$1date_0.setName("last-seen");
    _jspx_th_liferay$1ui_search$1container$1column$1date_0.setProperty("modifiedDate");
    int _jspx_eval_liferay$1ui_search$1container$1column$1date_0 = _jspx_th_liferay$1ui_search$1container$1column$1date_0.doStartTag();
    if (_jspx_th_liferay$1ui_search$1container$1column$1date_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_search$1container$1column$1date_property_name_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1date_0);
      return true;
    }
    _jspx_tagPool_liferay$1ui_search$1container$1column$1date_property_name_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1date_0);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_search$1container$1column$1jsp_0(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1ui_search$1container$1row_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:search-container-column-jsp
    com.liferay.taglib.ui.SearchContainerColumnJSPTag _jspx_th_liferay$1ui_search$1container$1column$1jsp_0 = (com.liferay.taglib.ui.SearchContainerColumnJSPTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1jsp_path_cssClass_align_nobody.get(com.liferay.taglib.ui.SearchContainerColumnJSPTag.class);
    _jspx_th_liferay$1ui_search$1container$1column$1jsp_0.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_search$1container$1column$1jsp_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1row_0);
    _jspx_th_liferay$1ui_search$1container$1column$1jsp_0.setAlign("right");
    _jspx_th_liferay$1ui_search$1container$1column$1jsp_0.setCssClass("entry-action-column");
    _jspx_th_liferay$1ui_search$1container$1column$1jsp_0.setPath("/devices_action.jsp");
    int _jspx_eval_liferay$1ui_search$1container$1column$1jsp_0 = _jspx_th_liferay$1ui_search$1container$1column$1jsp_0.doStartTag();
    if (_jspx_th_liferay$1ui_search$1container$1column$1jsp_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_search$1container$1column$1jsp_path_cssClass_align_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1jsp_0);
      return true;
    }
    _jspx_tagPool_liferay$1ui_search$1container$1column$1jsp_path_cssClass_align_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1jsp_0);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_search$1iterator_0(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1ui_search$1container_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:search-iterator
    com.liferay.taglib.ui.SearchIteratorTag _jspx_th_liferay$1ui_search$1iterator_0 = (com.liferay.taglib.ui.SearchIteratorTag) _jspx_tagPool_liferay$1ui_search$1iterator_markupView_nobody.get(com.liferay.taglib.ui.SearchIteratorTag.class);
    _jspx_th_liferay$1ui_search$1iterator_0.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_search$1iterator_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container_0);
    _jspx_th_liferay$1ui_search$1iterator_0.setMarkupView("lexicon");
    int _jspx_eval_liferay$1ui_search$1iterator_0 = _jspx_th_liferay$1ui_search$1iterator_0.doStartTag();
    if (_jspx_th_liferay$1ui_search$1iterator_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_search$1iterator_markupView_nobody.reuse(_jspx_th_liferay$1ui_search$1iterator_0);
      return true;
    }
    _jspx_tagPool_liferay$1ui_search$1iterator_markupView_nobody.reuse(_jspx_th_liferay$1ui_search$1iterator_0);
    return false;
  }
}
