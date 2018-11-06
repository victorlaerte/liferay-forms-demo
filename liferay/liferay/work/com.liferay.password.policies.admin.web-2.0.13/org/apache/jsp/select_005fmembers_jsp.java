package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.liferay.password.policies.admin.constants.PasswordPoliciesAdminPortletKeys;
import com.liferay.password.policies.admin.web.internal.display.context.EditPasswordPolicyAssignmentsManagementToolbarDisplayContext;
import com.liferay.password.policies.admin.web.internal.display.context.PasswordPolicyDisplayContext;
import com.liferay.password.policies.admin.web.internal.display.context.ViewPasswordPoliciesManagementToolbarDisplayContext;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.bean.BeanParamUtil;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.DuplicatePasswordPolicyException;
import com.liferay.portal.kernel.exception.NoSuchPasswordPolicyException;
import com.liferay.portal.kernel.exception.PasswordPolicyNameException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.PasswordPolicy;
import com.liferay.portal.kernel.model.PasswordPolicyRel;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.portlet.PortalPreferences;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.security.ldap.LDAPSettingsUtil;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.PasswordPolicyLocalServiceUtil;
import com.liferay.portal.kernel.service.PasswordPolicyRelLocalServiceUtil;
import com.liferay.portal.kernel.service.permission.PasswordPolicyPermissionUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.impl.PasswordPolicyImpl;
import com.liferay.taglib.search.ResultRow;
import com.liferay.users.admin.kernel.util.UsersAdmin;
import javax.portlet.PortletURL;
import javax.portlet.WindowState;

public final class select_005fmembers_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(4);
    _jspx_dependants.add("/init.jsp");
    _jspx_dependants.add("/init-ext.jsp");
    _jspx_dependants.add("/user_search_columns.jspf");
    _jspx_dependants.add("/organization_search_columns.jspf");
  }

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_param_value_name_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_icon$1help_message_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_if_test;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container$1column$1text_property_orderable_name_cssClass_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1frontend_icon$1vertical$1card_title_subtitle_rowChecker_resultRow_icon_cssClass_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_orderable_name_cssClass_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container$1row_modelVar_keyProperty_escapedModel_className;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_clay_navigation$1bar_navigationItems_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_choose;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1util_buffer_var;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1frontend_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_script_use;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_cssClass_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_user$1portrait_userId_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container$1column$1icon_toggleRowChecker_icon_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_form_name_cssClass;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_when_test;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_clay_management$1toolbar_viewTypeItems_sortingURL_sortingOrder_showSearch_selectable_searchFormName_searchContainerId_searchActionURL_itemsTotal_filterDropdownItems_clearResultsURL_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_namespace_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container$1row_rowIdProperty_modelVar_keyProperty_escapedModel_className;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_renderURL_var;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container_var_searchContainer_id;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container$1column$1text_colspan;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_otherwise;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1theme_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1frontend_user$1vertical$1card_userId_title_subtitle_rowChecker_resultRow_cssClass_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container$1column$1text;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1iterator_markupView_displayStyle_nobody;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_portlet_param_value_name_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_icon$1help_message_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_if_test = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_property_orderable_name_cssClass_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1frontend_icon$1vertical$1card_title_subtitle_rowChecker_resultRow_icon_cssClass_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_orderable_name_cssClass_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container$1row_modelVar_keyProperty_escapedModel_className = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_clay_navigation$1bar_navigationItems_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_choose = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1util_buffer_var = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1frontend_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_script_use = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_cssClass_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_user$1portrait_userId_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container$1column$1icon_toggleRowChecker_icon_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_form_name_cssClass = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_when_test = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_clay_management$1toolbar_viewTypeItems_sortingURL_sortingOrder_showSearch_selectable_searchFormName_searchContainerId_searchActionURL_itemsTotal_filterDropdownItems_clearResultsURL_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_namespace_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container$1row_rowIdProperty_modelVar_keyProperty_escapedModel_className = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_renderURL_var = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container_var_searchContainer_id = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_colspan = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_otherwise = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1theme_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1frontend_user$1vertical$1card_userId_title_subtitle_rowChecker_resultRow_cssClass_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1iterator_markupView_displayStyle_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_portlet_param_value_name_nobody.release();
    _jspx_tagPool_liferay$1ui_icon$1help_message_nobody.release();
    _jspx_tagPool_c_if_test.release();
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_property_orderable_name_cssClass_nobody.release();
    _jspx_tagPool_liferay$1frontend_icon$1vertical$1card_title_subtitle_rowChecker_resultRow_icon_cssClass_nobody.release();
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_orderable_name_cssClass_nobody.release();
    _jspx_tagPool_liferay$1ui_search$1container$1row_modelVar_keyProperty_escapedModel_className.release();
    _jspx_tagPool_clay_navigation$1bar_navigationItems_nobody.release();
    _jspx_tagPool_c_choose.release();
    _jspx_tagPool_liferay$1util_buffer_var.release();
    _jspx_tagPool_liferay$1frontend_defineObjects_nobody.release();
    _jspx_tagPool_aui_script_use.release();
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_cssClass_nobody.release();
    _jspx_tagPool_liferay$1ui_user$1portrait_userId_nobody.release();
    _jspx_tagPool_liferay$1ui_search$1container$1column$1icon_toggleRowChecker_icon_nobody.release();
    _jspx_tagPool_aui_form_name_cssClass.release();
    _jspx_tagPool_c_when_test.release();
    _jspx_tagPool_clay_management$1toolbar_viewTypeItems_sortingURL_sortingOrder_showSearch_selectable_searchFormName_searchContainerId_searchActionURL_itemsTotal_filterDropdownItems_clearResultsURL_nobody.release();
    _jspx_tagPool_portlet_namespace_nobody.release();
    _jspx_tagPool_liferay$1ui_search$1container$1row_rowIdProperty_modelVar_keyProperty_escapedModel_className.release();
    _jspx_tagPool_portlet_renderURL_var.release();
    _jspx_tagPool_liferay$1ui_search$1container_var_searchContainer_id.release();
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_colspan.release();
    _jspx_tagPool_c_otherwise.release();
    _jspx_tagPool_portlet_defineObjects_nobody.release();
    _jspx_tagPool_liferay$1theme_defineObjects_nobody.release();
    _jspx_tagPool_liferay$1frontend_user$1vertical$1card_userId_title_subtitle_rowChecker_resultRow_cssClass_nobody.release();
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text.release();
    _jspx_tagPool_liferay$1ui_search$1iterator_markupView_displayStyle_nobody.release();
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

PasswordPolicyDisplayContext passwordPolicyDisplayContext = new PasswordPolicyDisplayContext(request, renderResponse);

PortalPreferences portalPreferences = PortletPreferencesFactoryUtil.getPortalPreferences(liferayPortletRequest);

      out.write('\n');
      out.write('\n');
      out.write('\n');
      out.write('\n');

String tabs1 = ParamUtil.getString(request, "tabs1");
String tabs2 = ParamUtil.getString(request, "tabs2", "users");

String redirect = ParamUtil.getString(request, "redirect");

long passwordPolicyId = ParamUtil.getLong(request, "passwordPolicyId");

PasswordPolicy passwordPolicy = PasswordPolicyLocalServiceUtil.fetchPasswordPolicy(passwordPolicyId);

String displayStyle = ParamUtil.getString(request, "displayStyle");

if (Validator.isNull(displayStyle)) {
	displayStyle = portalPreferences.getValue(PasswordPoliciesAdminPortletKeys.PASSWORD_POLICIES_ADMIN, "display-style", "list");
}
else {
	portalPreferences.setValue(PasswordPoliciesAdminPortletKeys.PASSWORD_POLICIES_ADMIN, "display-style", displayStyle);

	request.setAttribute(WebKeys.SINGLE_PAGE_APPLICATION_CLEAR_CACHE, Boolean.TRUE);
}

String eventName = ParamUtil.getString(request, "eventName", liferayPortletResponse.getNamespace() + "selectMember");

EditPasswordPolicyAssignmentsManagementToolbarDisplayContext editPasswordPolicyAssignmentsManagementToolbarDisplayContext = new EditPasswordPolicyAssignmentsManagementToolbarDisplayContext(request, renderRequest, renderResponse, displayStyle, "/select_members.jsp");

SearchContainer searchContainer = editPasswordPolicyAssignmentsManagementToolbarDisplayContext.getSearchContainer();

      out.write('\n');
      out.write('\n');
      //  clay:navigation-bar
      com.liferay.frontend.taglib.clay.servlet.taglib.soy.NavigationBarTag _jspx_th_clay_navigation$1bar_0 = (com.liferay.frontend.taglib.clay.servlet.taglib.soy.NavigationBarTag) _jspx_tagPool_clay_navigation$1bar_navigationItems_nobody.get(com.liferay.frontend.taglib.clay.servlet.taglib.soy.NavigationBarTag.class);
      _jspx_th_clay_navigation$1bar_0.setPageContext(_jspx_page_context);
      _jspx_th_clay_navigation$1bar_0.setParent(null);
      _jspx_th_clay_navigation$1bar_0.setNavigationItems( passwordPolicyDisplayContext.getSelectMembersNavigationItems() );
      int _jspx_eval_clay_navigation$1bar_0 = _jspx_th_clay_navigation$1bar_0.doStartTag();
      if (_jspx_th_clay_navigation$1bar_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_clay_navigation$1bar_navigationItems_nobody.reuse(_jspx_th_clay_navigation$1bar_0);
        return;
      }
      _jspx_tagPool_clay_navigation$1bar_navigationItems_nobody.reuse(_jspx_th_clay_navigation$1bar_0);
      out.write('\n');
      out.write('\n');
      //  clay:management-toolbar
      com.liferay.frontend.taglib.clay.servlet.taglib.soy.ManagementToolbarTag _jspx_th_clay_management$1toolbar_0 = (com.liferay.frontend.taglib.clay.servlet.taglib.soy.ManagementToolbarTag) _jspx_tagPool_clay_management$1toolbar_viewTypeItems_sortingURL_sortingOrder_showSearch_selectable_searchFormName_searchContainerId_searchActionURL_itemsTotal_filterDropdownItems_clearResultsURL_nobody.get(com.liferay.frontend.taglib.clay.servlet.taglib.soy.ManagementToolbarTag.class);
      _jspx_th_clay_management$1toolbar_0.setPageContext(_jspx_page_context);
      _jspx_th_clay_management$1toolbar_0.setParent(null);
      _jspx_th_clay_management$1toolbar_0.setClearResultsURL( editPasswordPolicyAssignmentsManagementToolbarDisplayContext.getClearResultsURL() );
      _jspx_th_clay_management$1toolbar_0.setFilterDropdownItems( editPasswordPolicyAssignmentsManagementToolbarDisplayContext.getFilterDropdownItems() );
      _jspx_th_clay_management$1toolbar_0.setItemsTotal( searchContainer.getTotal() );
      _jspx_th_clay_management$1toolbar_0.setSearchActionURL( editPasswordPolicyAssignmentsManagementToolbarDisplayContext.getSearchActionURL() );
      _jspx_th_clay_management$1toolbar_0.setSearchContainerId("passwordPolicyMembers");
      _jspx_th_clay_management$1toolbar_0.setSearchFormName("searchFm");
      _jspx_th_clay_management$1toolbar_0.setSelectable( true );
      _jspx_th_clay_management$1toolbar_0.setShowSearch( true );
      _jspx_th_clay_management$1toolbar_0.setSortingOrder( searchContainer.getOrderByType() );
      _jspx_th_clay_management$1toolbar_0.setSortingURL( editPasswordPolicyAssignmentsManagementToolbarDisplayContext.getSortingURL() );
      _jspx_th_clay_management$1toolbar_0.setViewTypeItems( editPasswordPolicyAssignmentsManagementToolbarDisplayContext.getViewTypeItems() );
      int _jspx_eval_clay_management$1toolbar_0 = _jspx_th_clay_management$1toolbar_0.doStartTag();
      if (_jspx_th_clay_management$1toolbar_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_clay_management$1toolbar_viewTypeItems_sortingURL_sortingOrder_showSearch_selectable_searchFormName_searchContainerId_searchActionURL_itemsTotal_filterDropdownItems_clearResultsURL_nobody.reuse(_jspx_th_clay_management$1toolbar_0);
        return;
      }
      _jspx_tagPool_clay_management$1toolbar_viewTypeItems_sortingURL_sortingOrder_showSearch_selectable_searchFormName_searchContainerId_searchActionURL_itemsTotal_filterDropdownItems_clearResultsURL_nobody.reuse(_jspx_th_clay_management$1toolbar_0);
      out.write('\n');
      out.write('\n');
      //  aui:form
      com.liferay.taglib.aui.FormTag _jspx_th_aui_form_0 = (com.liferay.taglib.aui.FormTag) _jspx_tagPool_aui_form_name_cssClass.get(com.liferay.taglib.aui.FormTag.class);
      _jspx_th_aui_form_0.setPageContext(_jspx_page_context);
      _jspx_th_aui_form_0.setParent(null);
      _jspx_th_aui_form_0.setCssClass("container-fluid-1280");
      _jspx_th_aui_form_0.setName("selectMemberFm");
      int _jspx_eval_aui_form_0 = _jspx_th_aui_form_0.doStartTag();
      if (_jspx_eval_aui_form_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        out.write('\n');
        out.write('	');
        //  liferay-ui:search-container
        com.liferay.taglib.ui.SearchContainerTag _jspx_th_liferay$1ui_search$1container_0 = (com.liferay.taglib.ui.SearchContainerTag) _jspx_tagPool_liferay$1ui_search$1container_var_searchContainer_id.get(com.liferay.taglib.ui.SearchContainerTag.class);
        _jspx_th_liferay$1ui_search$1container_0.setPageContext(_jspx_page_context);
        _jspx_th_liferay$1ui_search$1container_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
        _jspx_th_liferay$1ui_search$1container_0.setId("passwordPolicyMembers");
        _jspx_th_liferay$1ui_search$1container_0.setSearchContainer( searchContainer );
        _jspx_th_liferay$1ui_search$1container_0.setVar("memberSearchContainer");
        int _jspx_eval_liferay$1ui_search$1container_0 = _jspx_th_liferay$1ui_search$1container_0.doStartTag();
        if (_jspx_eval_liferay$1ui_search$1container_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          java.lang.Integer total = null;
          com.liferay.portal.kernel.dao.search.SearchContainer memberSearchContainer = null;
          total = (java.lang.Integer) _jspx_page_context.findAttribute("total");
          memberSearchContainer = (com.liferay.portal.kernel.dao.search.SearchContainer) _jspx_page_context.findAttribute("memberSearchContainer");
          out.write("\n\t\t");
          //  c:choose
          com.liferay.taglib.core.ChooseTag _jspx_th_c_choose_0 = (com.liferay.taglib.core.ChooseTag) _jspx_tagPool_c_choose.get(com.liferay.taglib.core.ChooseTag.class);
          _jspx_th_c_choose_0.setPageContext(_jspx_page_context);
          _jspx_th_c_choose_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container_0);
          int _jspx_eval_c_choose_0 = _jspx_th_c_choose_0.doStartTag();
          if (_jspx_eval_c_choose_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t\t");
            //  c:when
            com.liferay.taglib.core.WhenTag _jspx_th_c_when_0 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
            _jspx_th_c_when_0.setPageContext(_jspx_page_context);
            _jspx_th_c_when_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_0);
            _jspx_th_c_when_0.setTest( tabs2.equals("users") );
            int _jspx_eval_c_when_0 = _jspx_th_c_when_0.doStartTag();
            if (_jspx_eval_c_when_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\t\t\t\t");
              out.write('\n');
              out.write('\n');
              //  liferay-ui:search-container-row
              com.liferay.taglib.ui.SearchContainerRowTag _jspx_th_liferay$1ui_search$1container$1row_0 = (com.liferay.taglib.ui.SearchContainerRowTag) _jspx_tagPool_liferay$1ui_search$1container$1row_rowIdProperty_modelVar_keyProperty_escapedModel_className.get(com.liferay.taglib.ui.SearchContainerRowTag.class);
              _jspx_th_liferay$1ui_search$1container$1row_0.setPageContext(_jspx_page_context);
              _jspx_th_liferay$1ui_search$1container$1row_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_0);
              _jspx_th_liferay$1ui_search$1container$1row_0.setClassName("com.liferay.portal.kernel.model.User");
              _jspx_th_liferay$1ui_search$1container$1row_0.setEscapedModel( true );
              _jspx_th_liferay$1ui_search$1container$1row_0.setKeyProperty("userId");
              _jspx_th_liferay$1ui_search$1container$1row_0.setModelVar("user2");
              _jspx_th_liferay$1ui_search$1container$1row_0.setRowIdProperty("screenName");
              int _jspx_eval_liferay$1ui_search$1container$1row_0 = _jspx_th_liferay$1ui_search$1container$1row_0.doStartTag();
              if (_jspx_eval_liferay$1ui_search$1container$1row_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                java.lang.Integer index = null;
                com.liferay.portal.kernel.model.User user2 = null;
                com.liferay.portal.kernel.dao.search.ResultRow row = null;
                if (_jspx_eval_liferay$1ui_search$1container$1row_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                  out = _jspx_page_context.pushBody();
                  _jspx_th_liferay$1ui_search$1container$1row_0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                  _jspx_th_liferay$1ui_search$1container$1row_0.doInitBody();
                }
                index = (java.lang.Integer) _jspx_page_context.findAttribute("index");
                user2 = (com.liferay.portal.kernel.model.User) _jspx_page_context.findAttribute("user2");
                row = (com.liferay.portal.kernel.dao.search.ResultRow) _jspx_page_context.findAttribute("row");
                do {
                  out.write("\n\n\t");

	PasswordPolicyRel passwordPolicyRel = PasswordPolicyRelLocalServiceUtil.fetchPasswordPolicyRel(User.class.getName(), user2.getUserId());
	
                  out.write("\n\n\t");
                  //  liferay-util:buffer
                  com.liferay.taglib.util.BufferTag _jspx_th_liferay$1util_buffer_0 = (com.liferay.taglib.util.BufferTag) _jspx_tagPool_liferay$1util_buffer_var.get(com.liferay.taglib.util.BufferTag.class);
                  _jspx_th_liferay$1util_buffer_0.setPageContext(_jspx_page_context);
                  _jspx_th_liferay$1util_buffer_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1row_0);
                  _jspx_th_liferay$1util_buffer_0.setVar("name");
                  int _jspx_eval_liferay$1util_buffer_0 = _jspx_th_liferay$1util_buffer_0.doStartTag();
                  if (_jspx_eval_liferay$1util_buffer_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    if (_jspx_eval_liferay$1util_buffer_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                      out = _jspx_page_context.pushBody();
                      _jspx_th_liferay$1util_buffer_0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                      _jspx_th_liferay$1util_buffer_0.doInitBody();
                    }
                    do {
                      out.write("\n\t\t");
                      out.print( user2.getFullName() );
                      out.write("\n\n\t\t");
                      //  c:if
                      com.liferay.taglib.core.IfTag _jspx_th_c_if_0 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                      _jspx_th_c_if_0.setPageContext(_jspx_page_context);
                      _jspx_th_c_if_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1util_buffer_0);
                      _jspx_th_c_if_0.setTest( (passwordPolicyRel != null) && (passwordPolicyRel.getPasswordPolicyId() != passwordPolicy.getPasswordPolicyId()) );
                      int _jspx_eval_c_if_0 = _jspx_th_c_if_0.doStartTag();
                      if (_jspx_eval_c_if_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                        out.write("\n\n\t\t\t");

			PasswordPolicy curPasswordPolicy = PasswordPolicyLocalServiceUtil.getPasswordPolicy(passwordPolicyRel.getPasswordPolicyId());
			
                        out.write("\n\n\t\t\t");
                        //  portlet:renderURL
                        com.liferay.taglib.portlet.RenderURLTag _jspx_th_portlet_renderURL_0 = (com.liferay.taglib.portlet.RenderURLTag) _jspx_tagPool_portlet_renderURL_var.get(com.liferay.taglib.portlet.RenderURLTag.class);
                        _jspx_th_portlet_renderURL_0.setPageContext(_jspx_page_context);
                        _jspx_th_portlet_renderURL_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_0);
                        _jspx_th_portlet_renderURL_0.setVar("assignMembersURL");
                        int _jspx_eval_portlet_renderURL_0 = _jspx_th_portlet_renderURL_0.doStartTag();
                        if (_jspx_eval_portlet_renderURL_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                          out.write("\n\t\t\t\t");
                          if (_jspx_meth_portlet_param_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_portlet_renderURL_0, _jspx_page_context))
                            return;
                          out.write("\n\t\t\t\t");
                          //  portlet:param
                          com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_1 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                          _jspx_th_portlet_param_1.setPageContext(_jspx_page_context);
                          _jspx_th_portlet_param_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_0);
                          _jspx_th_portlet_param_1.setName("tabs1");
                          _jspx_th_portlet_param_1.setValue( tabs1 );
                          int _jspx_eval_portlet_param_1 = _jspx_th_portlet_param_1.doStartTag();
                          if (_jspx_th_portlet_param_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                            _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_1);
                            return;
                          }
                          _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_1);
                          out.write("\n\t\t\t\t");
                          if (_jspx_meth_portlet_param_2((javax.servlet.jsp.tagext.JspTag) _jspx_th_portlet_renderURL_0, _jspx_page_context))
                            return;
                          out.write("\n\t\t\t\t");
                          //  portlet:param
                          com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_3 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                          _jspx_th_portlet_param_3.setPageContext(_jspx_page_context);
                          _jspx_th_portlet_param_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_0);
                          _jspx_th_portlet_param_3.setName("redirect");
                          _jspx_th_portlet_param_3.setValue( currentURL );
                          int _jspx_eval_portlet_param_3 = _jspx_th_portlet_param_3.doStartTag();
                          if (_jspx_th_portlet_param_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                            _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_3);
                            return;
                          }
                          _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_3);
                          out.write("\n\t\t\t\t");
                          //  portlet:param
                          com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_4 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                          _jspx_th_portlet_param_4.setPageContext(_jspx_page_context);
                          _jspx_th_portlet_param_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_0);
                          _jspx_th_portlet_param_4.setName("passwordPolicyId");
                          _jspx_th_portlet_param_4.setValue( String.valueOf(curPasswordPolicy.getPasswordPolicyId()) );
                          int _jspx_eval_portlet_param_4 = _jspx_th_portlet_param_4.doStartTag();
                          if (_jspx_th_portlet_param_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                            _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_4);
                            return;
                          }
                          _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_4);
                          out.write("\n\t\t\t");
                        }
                        if (_jspx_th_portlet_renderURL_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                          _jspx_tagPool_portlet_renderURL_var.reuse(_jspx_th_portlet_renderURL_0);
                          return;
                        }
                        _jspx_tagPool_portlet_renderURL_var.reuse(_jspx_th_portlet_renderURL_0);
                        java.lang.String assignMembersURL = null;
                        assignMembersURL = (java.lang.String) _jspx_page_context.findAttribute("assignMembersURL");
                        out.write("\n\n\t\t\t");
                        //  liferay-ui:icon-help
                        com.liferay.taglib.ui.IconHelpTag _jspx_th_liferay$1ui_icon$1help_0 = (com.liferay.taglib.ui.IconHelpTag) _jspx_tagPool_liferay$1ui_icon$1help_message_nobody.get(com.liferay.taglib.ui.IconHelpTag.class);
                        _jspx_th_liferay$1ui_icon$1help_0.setPageContext(_jspx_page_context);
                        _jspx_th_liferay$1ui_icon$1help_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_0);
                        _jspx_th_liferay$1ui_icon$1help_0.setMessage( LanguageUtil.format(request, "this-user-is-already-assigned-to-password-policy-x", new Object[] {assignMembersURL, curPasswordPolicy.getName()}, false) );
                        int _jspx_eval_liferay$1ui_icon$1help_0 = _jspx_th_liferay$1ui_icon$1help_0.doStartTag();
                        if (_jspx_th_liferay$1ui_icon$1help_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                          _jspx_tagPool_liferay$1ui_icon$1help_message_nobody.reuse(_jspx_th_liferay$1ui_icon$1help_0);
                          return;
                        }
                        _jspx_tagPool_liferay$1ui_icon$1help_message_nobody.reuse(_jspx_th_liferay$1ui_icon$1help_0);
                        out.write("\n\t\t");
                      }
                      if (_jspx_th_c_if_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
                        return;
                      }
                      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
                      out.write('\n');
                      out.write('	');
                      int evalDoAfterBody = _jspx_th_liferay$1util_buffer_0.doAfterBody();
                      if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                        break;
                    } while (true);
                    if (_jspx_eval_liferay$1util_buffer_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                      out = _jspx_page_context.popBody();
                  }
                  if (_jspx_th_liferay$1util_buffer_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_liferay$1util_buffer_var.reuse(_jspx_th_liferay$1util_buffer_0);
                    return;
                  }
                  _jspx_tagPool_liferay$1util_buffer_var.reuse(_jspx_th_liferay$1util_buffer_0);
                  java.lang.String name = null;
                  name = (java.lang.String) _jspx_page_context.findAttribute("name");
                  out.write("\n\n\t");
                  //  c:choose
                  com.liferay.taglib.core.ChooseTag _jspx_th_c_choose_1 = (com.liferay.taglib.core.ChooseTag) _jspx_tagPool_c_choose.get(com.liferay.taglib.core.ChooseTag.class);
                  _jspx_th_c_choose_1.setPageContext(_jspx_page_context);
                  _jspx_th_c_choose_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1row_0);
                  int _jspx_eval_c_choose_1 = _jspx_th_c_choose_1.doStartTag();
                  if (_jspx_eval_c_choose_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    out.write("\n\t\t");
                    //  c:when
                    com.liferay.taglib.core.WhenTag _jspx_th_c_when_1 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
                    _jspx_th_c_when_1.setPageContext(_jspx_page_context);
                    _jspx_th_c_when_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_1);
                    _jspx_th_c_when_1.setTest( displayStyle.equals("icon") );
                    int _jspx_eval_c_when_1 = _jspx_th_c_when_1.doStartTag();
                    if (_jspx_eval_c_when_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      out.write("\n\n\t\t\t");

			row.setCssClass("entry-card lfr-asset-item");
			
                      out.write("\n\n\t\t\t");
                      //  liferay-ui:search-container-column-text
                      com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_0 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
                      _jspx_th_liferay$1ui_search$1container$1column$1text_0.setPageContext(_jspx_page_context);
                      _jspx_th_liferay$1ui_search$1container$1column$1text_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_1);
                      int _jspx_eval_liferay$1ui_search$1container$1column$1text_0 = _jspx_th_liferay$1ui_search$1container$1column$1text_0.doStartTag();
                      if (_jspx_eval_liferay$1ui_search$1container$1column$1text_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                        if (_jspx_eval_liferay$1ui_search$1container$1column$1text_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                          out = _jspx_page_context.pushBody();
                          _jspx_th_liferay$1ui_search$1container$1column$1text_0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                          _jspx_th_liferay$1ui_search$1container$1column$1text_0.doInitBody();
                        }
                        do {
                          out.write("\n\t\t\t\t");
                          //  liferay-frontend:user-vertical-card
                          com.liferay.frontend.taglib.servlet.taglib.UserVerticalCardTag _jspx_th_liferay$1frontend_user$1vertical$1card_0 = (com.liferay.frontend.taglib.servlet.taglib.UserVerticalCardTag) _jspx_tagPool_liferay$1frontend_user$1vertical$1card_userId_title_subtitle_rowChecker_resultRow_cssClass_nobody.get(com.liferay.frontend.taglib.servlet.taglib.UserVerticalCardTag.class);
                          _jspx_th_liferay$1frontend_user$1vertical$1card_0.setPageContext(_jspx_page_context);
                          _jspx_th_liferay$1frontend_user$1vertical$1card_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1column$1text_0);
                          _jspx_th_liferay$1frontend_user$1vertical$1card_0.setCssClass("entry-display-style");
                          _jspx_th_liferay$1frontend_user$1vertical$1card_0.setResultRow( row );
                          _jspx_th_liferay$1frontend_user$1vertical$1card_0.setRowChecker( memberSearchContainer.getRowChecker() );
                          _jspx_th_liferay$1frontend_user$1vertical$1card_0.setSubtitle( user2.getScreenName() );
                          _jspx_th_liferay$1frontend_user$1vertical$1card_0.setTitle( name );
                          _jspx_th_liferay$1frontend_user$1vertical$1card_0.setUserId( user2.getUserId() );
                          int _jspx_eval_liferay$1frontend_user$1vertical$1card_0 = _jspx_th_liferay$1frontend_user$1vertical$1card_0.doStartTag();
                          if (_jspx_th_liferay$1frontend_user$1vertical$1card_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                            _jspx_tagPool_liferay$1frontend_user$1vertical$1card_userId_title_subtitle_rowChecker_resultRow_cssClass_nobody.reuse(_jspx_th_liferay$1frontend_user$1vertical$1card_0);
                            return;
                          }
                          _jspx_tagPool_liferay$1frontend_user$1vertical$1card_userId_title_subtitle_rowChecker_resultRow_cssClass_nobody.reuse(_jspx_th_liferay$1frontend_user$1vertical$1card_0);
                          out.write("\n\t\t\t");
                          int evalDoAfterBody = _jspx_th_liferay$1ui_search$1container$1column$1text_0.doAfterBody();
                          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                            break;
                        } while (true);
                        if (_jspx_eval_liferay$1ui_search$1container$1column$1text_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                          out = _jspx_page_context.popBody();
                      }
                      if (_jspx_th_liferay$1ui_search$1container$1column$1text_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_liferay$1ui_search$1container$1column$1text.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_0);
                        return;
                      }
                      _jspx_tagPool_liferay$1ui_search$1container$1column$1text.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_0);
                      out.write("\n\t\t");
                    }
                    if (_jspx_th_c_when_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_1);
                      return;
                    }
                    _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_1);
                    out.write("\n\t\t");
                    //  c:when
                    com.liferay.taglib.core.WhenTag _jspx_th_c_when_2 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
                    _jspx_th_c_when_2.setPageContext(_jspx_page_context);
                    _jspx_th_c_when_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_1);
                    _jspx_th_c_when_2.setTest( displayStyle.equals("descriptive") );
                    int _jspx_eval_c_when_2 = _jspx_th_c_when_2.doStartTag();
                    if (_jspx_eval_c_when_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      out.write("\n\t\t\t");
                      //  liferay-ui:search-container-column-text
                      com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_1 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
                      _jspx_th_liferay$1ui_search$1container$1column$1text_1.setPageContext(_jspx_page_context);
                      _jspx_th_liferay$1ui_search$1container$1column$1text_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_2);
                      int _jspx_eval_liferay$1ui_search$1container$1column$1text_1 = _jspx_th_liferay$1ui_search$1container$1column$1text_1.doStartTag();
                      if (_jspx_eval_liferay$1ui_search$1container$1column$1text_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                        if (_jspx_eval_liferay$1ui_search$1container$1column$1text_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                          out = _jspx_page_context.pushBody();
                          _jspx_th_liferay$1ui_search$1container$1column$1text_1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                          _jspx_th_liferay$1ui_search$1container$1column$1text_1.doInitBody();
                        }
                        do {
                          out.write("\n\t\t\t\t");
                          //  liferay-ui:user-portrait
                          com.liferay.taglib.ui.UserPortraitTag _jspx_th_liferay$1ui_user$1portrait_0 = (com.liferay.taglib.ui.UserPortraitTag) _jspx_tagPool_liferay$1ui_user$1portrait_userId_nobody.get(com.liferay.taglib.ui.UserPortraitTag.class);
                          _jspx_th_liferay$1ui_user$1portrait_0.setPageContext(_jspx_page_context);
                          _jspx_th_liferay$1ui_user$1portrait_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1column$1text_1);
                          _jspx_th_liferay$1ui_user$1portrait_0.setUserId( user2.getUserId() );
                          int _jspx_eval_liferay$1ui_user$1portrait_0 = _jspx_th_liferay$1ui_user$1portrait_0.doStartTag();
                          if (_jspx_th_liferay$1ui_user$1portrait_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                            _jspx_tagPool_liferay$1ui_user$1portrait_userId_nobody.reuse(_jspx_th_liferay$1ui_user$1portrait_0);
                            return;
                          }
                          _jspx_tagPool_liferay$1ui_user$1portrait_userId_nobody.reuse(_jspx_th_liferay$1ui_user$1portrait_0);
                          out.write("\n\t\t\t");
                          int evalDoAfterBody = _jspx_th_liferay$1ui_search$1container$1column$1text_1.doAfterBody();
                          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                            break;
                        } while (true);
                        if (_jspx_eval_liferay$1ui_search$1container$1column$1text_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                          out = _jspx_page_context.popBody();
                      }
                      if (_jspx_th_liferay$1ui_search$1container$1column$1text_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_liferay$1ui_search$1container$1column$1text.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_1);
                        return;
                      }
                      _jspx_tagPool_liferay$1ui_search$1container$1column$1text.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_1);
                      out.write("\n\n\t\t\t");
                      //  liferay-ui:search-container-column-text
                      com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_2 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text_colspan.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
                      _jspx_th_liferay$1ui_search$1container$1column$1text_2.setPageContext(_jspx_page_context);
                      _jspx_th_liferay$1ui_search$1container$1column$1text_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_2);
                      _jspx_th_liferay$1ui_search$1container$1column$1text_2.setColspan( 2 );
                      int _jspx_eval_liferay$1ui_search$1container$1column$1text_2 = _jspx_th_liferay$1ui_search$1container$1column$1text_2.doStartTag();
                      if (_jspx_eval_liferay$1ui_search$1container$1column$1text_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                        if (_jspx_eval_liferay$1ui_search$1container$1column$1text_2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                          out = _jspx_page_context.pushBody();
                          _jspx_th_liferay$1ui_search$1container$1column$1text_2.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                          _jspx_th_liferay$1ui_search$1container$1column$1text_2.doInitBody();
                        }
                        do {
                          out.write("\n\t\t\t\t<h5>");
                          out.print( name );
                          out.write("</h5>\n\n\t\t\t\t<h6 class=\"text-default\">\n\t\t\t\t\t<span>");
                          out.print( user2.getScreenName() );
                          out.write("</span>\n\t\t\t\t</h6>\n\t\t\t");
                          int evalDoAfterBody = _jspx_th_liferay$1ui_search$1container$1column$1text_2.doAfterBody();
                          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                            break;
                        } while (true);
                        if (_jspx_eval_liferay$1ui_search$1container$1column$1text_2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                          out = _jspx_page_context.popBody();
                      }
                      if (_jspx_th_liferay$1ui_search$1container$1column$1text_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_liferay$1ui_search$1container$1column$1text_colspan.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_2);
                        return;
                      }
                      _jspx_tagPool_liferay$1ui_search$1container$1column$1text_colspan.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_2);
                      out.write("\n\t\t");
                    }
                    if (_jspx_th_c_when_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_2);
                      return;
                    }
                    _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_2);
                    out.write("\n\t\t");
                    //  c:otherwise
                    com.liferay.taglib.core.OtherwiseTag _jspx_th_c_otherwise_0 = (com.liferay.taglib.core.OtherwiseTag) _jspx_tagPool_c_otherwise.get(com.liferay.taglib.core.OtherwiseTag.class);
                    _jspx_th_c_otherwise_0.setPageContext(_jspx_page_context);
                    _jspx_th_c_otherwise_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_1);
                    int _jspx_eval_c_otherwise_0 = _jspx_th_c_otherwise_0.doStartTag();
                    if (_jspx_eval_c_otherwise_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      out.write("\n\t\t\t");
                      //  liferay-ui:search-container-column-text
                      com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_3 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_cssClass_nobody.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
                      _jspx_th_liferay$1ui_search$1container$1column$1text_3.setPageContext(_jspx_page_context);
                      _jspx_th_liferay$1ui_search$1container$1column$1text_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_0);
                      _jspx_th_liferay$1ui_search$1container$1column$1text_3.setCssClass("table-cell-expand table-cell-minw-200 table-title");
                      _jspx_th_liferay$1ui_search$1container$1column$1text_3.setName("name");
                      _jspx_th_liferay$1ui_search$1container$1column$1text_3.setValue( name );
                      int _jspx_eval_liferay$1ui_search$1container$1column$1text_3 = _jspx_th_liferay$1ui_search$1container$1column$1text_3.doStartTag();
                      if (_jspx_th_liferay$1ui_search$1container$1column$1text_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_cssClass_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_3);
                        return;
                      }
                      _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_cssClass_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_3);
                      out.write("\n\n\t\t\t");
                      //  liferay-ui:search-container-column-text
                      com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_4 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text_property_orderable_name_cssClass_nobody.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
                      _jspx_th_liferay$1ui_search$1container$1column$1text_4.setPageContext(_jspx_page_context);
                      _jspx_th_liferay$1ui_search$1container$1column$1text_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_0);
                      _jspx_th_liferay$1ui_search$1container$1column$1text_4.setCssClass("table-cell-expand table-cell-minw-200");
                      _jspx_th_liferay$1ui_search$1container$1column$1text_4.setName("screen-name");
                      _jspx_th_liferay$1ui_search$1container$1column$1text_4.setOrderable( true );
                      _jspx_th_liferay$1ui_search$1container$1column$1text_4.setProperty("screenName");
                      int _jspx_eval_liferay$1ui_search$1container$1column$1text_4 = _jspx_th_liferay$1ui_search$1container$1column$1text_4.doStartTag();
                      if (_jspx_th_liferay$1ui_search$1container$1column$1text_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_liferay$1ui_search$1container$1column$1text_property_orderable_name_cssClass_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_4);
                        return;
                      }
                      _jspx_tagPool_liferay$1ui_search$1container$1column$1text_property_orderable_name_cssClass_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_4);
                      out.write("\n\t\t");
                    }
                    if (_jspx_th_c_otherwise_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_0);
                      return;
                    }
                    _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_0);
                    out.write('\n');
                    out.write('	');
                  }
                  if (_jspx_th_c_choose_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_1);
                    return;
                  }
                  _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_1);
                  out.write('\n');
                  int evalDoAfterBody = _jspx_th_liferay$1ui_search$1container$1row_0.doAfterBody();
                  index = (java.lang.Integer) _jspx_page_context.findAttribute("index");
                  user2 = (com.liferay.portal.kernel.model.User) _jspx_page_context.findAttribute("user2");
                  row = (com.liferay.portal.kernel.dao.search.ResultRow) _jspx_page_context.findAttribute("row");
                  if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                    break;
                } while (true);
                if (_jspx_eval_liferay$1ui_search$1container$1row_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                  out = _jspx_page_context.popBody();
              }
              if (_jspx_th_liferay$1ui_search$1container$1row_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_liferay$1ui_search$1container$1row_rowIdProperty_modelVar_keyProperty_escapedModel_className.reuse(_jspx_th_liferay$1ui_search$1container$1row_0);
                return;
              }
              _jspx_tagPool_liferay$1ui_search$1container$1row_rowIdProperty_modelVar_keyProperty_escapedModel_className.reuse(_jspx_th_liferay$1ui_search$1container$1row_0);
              out.write("\n\t\t\t");
            }
            if (_jspx_th_c_when_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_0);
              return;
            }
            _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_0);
            out.write("\n\t\t\t");
            //  c:when
            com.liferay.taglib.core.WhenTag _jspx_th_c_when_3 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
            _jspx_th_c_when_3.setPageContext(_jspx_page_context);
            _jspx_th_c_when_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_0);
            _jspx_th_c_when_3.setTest( tabs2.equals("organizations") );
            int _jspx_eval_c_when_3 = _jspx_th_c_when_3.doStartTag();
            if (_jspx_eval_c_when_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\t\t\t\t");
              out.write('\n');
              out.write('\n');
              //  liferay-ui:search-container-row
              com.liferay.taglib.ui.SearchContainerRowTag _jspx_th_liferay$1ui_search$1container$1row_1 = (com.liferay.taglib.ui.SearchContainerRowTag) _jspx_tagPool_liferay$1ui_search$1container$1row_modelVar_keyProperty_escapedModel_className.get(com.liferay.taglib.ui.SearchContainerRowTag.class);
              _jspx_th_liferay$1ui_search$1container$1row_1.setPageContext(_jspx_page_context);
              _jspx_th_liferay$1ui_search$1container$1row_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_3);
              _jspx_th_liferay$1ui_search$1container$1row_1.setClassName("com.liferay.portal.kernel.model.Organization");
              _jspx_th_liferay$1ui_search$1container$1row_1.setEscapedModel( true );
              _jspx_th_liferay$1ui_search$1container$1row_1.setKeyProperty("organizationId");
              _jspx_th_liferay$1ui_search$1container$1row_1.setModelVar("organization");
              int _jspx_eval_liferay$1ui_search$1container$1row_1 = _jspx_th_liferay$1ui_search$1container$1row_1.doStartTag();
              if (_jspx_eval_liferay$1ui_search$1container$1row_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                java.lang.Integer index = null;
                com.liferay.portal.kernel.model.Organization organization = null;
                com.liferay.portal.kernel.dao.search.ResultRow row = null;
                if (_jspx_eval_liferay$1ui_search$1container$1row_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                  out = _jspx_page_context.pushBody();
                  _jspx_th_liferay$1ui_search$1container$1row_1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                  _jspx_th_liferay$1ui_search$1container$1row_1.doInitBody();
                }
                index = (java.lang.Integer) _jspx_page_context.findAttribute("index");
                organization = (com.liferay.portal.kernel.model.Organization) _jspx_page_context.findAttribute("organization");
                row = (com.liferay.portal.kernel.dao.search.ResultRow) _jspx_page_context.findAttribute("row");
                do {
                  out.write("\n\n\t");

	PasswordPolicyRel passwordPolicyRel = PasswordPolicyRelLocalServiceUtil.fetchPasswordPolicyRel(Organization.class.getName(), organization.getOrganizationId());
	
                  out.write("\n\n\t");
                  //  liferay-util:buffer
                  com.liferay.taglib.util.BufferTag _jspx_th_liferay$1util_buffer_1 = (com.liferay.taglib.util.BufferTag) _jspx_tagPool_liferay$1util_buffer_var.get(com.liferay.taglib.util.BufferTag.class);
                  _jspx_th_liferay$1util_buffer_1.setPageContext(_jspx_page_context);
                  _jspx_th_liferay$1util_buffer_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1row_1);
                  _jspx_th_liferay$1util_buffer_1.setVar("name");
                  int _jspx_eval_liferay$1util_buffer_1 = _jspx_th_liferay$1util_buffer_1.doStartTag();
                  if (_jspx_eval_liferay$1util_buffer_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    if (_jspx_eval_liferay$1util_buffer_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                      out = _jspx_page_context.pushBody();
                      _jspx_th_liferay$1util_buffer_1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                      _jspx_th_liferay$1util_buffer_1.doInitBody();
                    }
                    do {
                      out.write("\n\t\t");
                      out.print( organization.getName() );
                      out.write("\n\n\t\t");
                      //  c:if
                      com.liferay.taglib.core.IfTag _jspx_th_c_if_1 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                      _jspx_th_c_if_1.setPageContext(_jspx_page_context);
                      _jspx_th_c_if_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1util_buffer_1);
                      _jspx_th_c_if_1.setTest( (passwordPolicyRel != null) && (passwordPolicyRel.getPasswordPolicyId() != passwordPolicy.getPasswordPolicyId()) );
                      int _jspx_eval_c_if_1 = _jspx_th_c_if_1.doStartTag();
                      if (_jspx_eval_c_if_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                        out.write("\n\n\t\t\t");

			PasswordPolicy curPasswordPolicy = PasswordPolicyLocalServiceUtil.getPasswordPolicy(passwordPolicyRel.getPasswordPolicyId());
			
                        out.write("\n\n\t\t\t");
                        //  portlet:renderURL
                        com.liferay.taglib.portlet.RenderURLTag _jspx_th_portlet_renderURL_1 = (com.liferay.taglib.portlet.RenderURLTag) _jspx_tagPool_portlet_renderURL_var.get(com.liferay.taglib.portlet.RenderURLTag.class);
                        _jspx_th_portlet_renderURL_1.setPageContext(_jspx_page_context);
                        _jspx_th_portlet_renderURL_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_1);
                        _jspx_th_portlet_renderURL_1.setVar("assignMembersURL");
                        int _jspx_eval_portlet_renderURL_1 = _jspx_th_portlet_renderURL_1.doStartTag();
                        if (_jspx_eval_portlet_renderURL_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                          out.write("\n\t\t\t\t");
                          if (_jspx_meth_portlet_param_5((javax.servlet.jsp.tagext.JspTag) _jspx_th_portlet_renderURL_1, _jspx_page_context))
                            return;
                          out.write("\n\t\t\t\t");
                          //  portlet:param
                          com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_6 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                          _jspx_th_portlet_param_6.setPageContext(_jspx_page_context);
                          _jspx_th_portlet_param_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_1);
                          _jspx_th_portlet_param_6.setName("tabs1");
                          _jspx_th_portlet_param_6.setValue( tabs1 );
                          int _jspx_eval_portlet_param_6 = _jspx_th_portlet_param_6.doStartTag();
                          if (_jspx_th_portlet_param_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                            _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_6);
                            return;
                          }
                          _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_6);
                          out.write("\n\t\t\t\t");
                          if (_jspx_meth_portlet_param_7((javax.servlet.jsp.tagext.JspTag) _jspx_th_portlet_renderURL_1, _jspx_page_context))
                            return;
                          out.write("\n\t\t\t\t");
                          //  portlet:param
                          com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_8 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                          _jspx_th_portlet_param_8.setPageContext(_jspx_page_context);
                          _jspx_th_portlet_param_8.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_1);
                          _jspx_th_portlet_param_8.setName("redirect");
                          _jspx_th_portlet_param_8.setValue( currentURL );
                          int _jspx_eval_portlet_param_8 = _jspx_th_portlet_param_8.doStartTag();
                          if (_jspx_th_portlet_param_8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                            _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_8);
                            return;
                          }
                          _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_8);
                          out.write("\n\t\t\t\t");
                          //  portlet:param
                          com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_9 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                          _jspx_th_portlet_param_9.setPageContext(_jspx_page_context);
                          _jspx_th_portlet_param_9.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_1);
                          _jspx_th_portlet_param_9.setName("passwordPolicyId");
                          _jspx_th_portlet_param_9.setValue( String.valueOf(curPasswordPolicy.getPasswordPolicyId()) );
                          int _jspx_eval_portlet_param_9 = _jspx_th_portlet_param_9.doStartTag();
                          if (_jspx_th_portlet_param_9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                            _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_9);
                            return;
                          }
                          _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_9);
                          out.write("\n\t\t\t");
                        }
                        if (_jspx_th_portlet_renderURL_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                          _jspx_tagPool_portlet_renderURL_var.reuse(_jspx_th_portlet_renderURL_1);
                          return;
                        }
                        _jspx_tagPool_portlet_renderURL_var.reuse(_jspx_th_portlet_renderURL_1);
                        java.lang.String assignMembersURL = null;
                        assignMembersURL = (java.lang.String) _jspx_page_context.findAttribute("assignMembersURL");
                        out.write("\n\n\t\t\t");
                        //  liferay-ui:icon-help
                        com.liferay.taglib.ui.IconHelpTag _jspx_th_liferay$1ui_icon$1help_1 = (com.liferay.taglib.ui.IconHelpTag) _jspx_tagPool_liferay$1ui_icon$1help_message_nobody.get(com.liferay.taglib.ui.IconHelpTag.class);
                        _jspx_th_liferay$1ui_icon$1help_1.setPageContext(_jspx_page_context);
                        _jspx_th_liferay$1ui_icon$1help_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_1);
                        _jspx_th_liferay$1ui_icon$1help_1.setMessage( LanguageUtil.format(request, "this-organization-is-already-assigned-to-password-policy-x", new Object[] {assignMembersURL, curPasswordPolicy.getName()}, false) );
                        int _jspx_eval_liferay$1ui_icon$1help_1 = _jspx_th_liferay$1ui_icon$1help_1.doStartTag();
                        if (_jspx_th_liferay$1ui_icon$1help_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                          _jspx_tagPool_liferay$1ui_icon$1help_message_nobody.reuse(_jspx_th_liferay$1ui_icon$1help_1);
                          return;
                        }
                        _jspx_tagPool_liferay$1ui_icon$1help_message_nobody.reuse(_jspx_th_liferay$1ui_icon$1help_1);
                        out.write("\n\t\t");
                      }
                      if (_jspx_th_c_if_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_1);
                        return;
                      }
                      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_1);
                      out.write('\n');
                      out.write('	');
                      int evalDoAfterBody = _jspx_th_liferay$1util_buffer_1.doAfterBody();
                      if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                        break;
                    } while (true);
                    if (_jspx_eval_liferay$1util_buffer_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                      out = _jspx_page_context.popBody();
                  }
                  if (_jspx_th_liferay$1util_buffer_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_liferay$1util_buffer_var.reuse(_jspx_th_liferay$1util_buffer_1);
                    return;
                  }
                  _jspx_tagPool_liferay$1util_buffer_var.reuse(_jspx_th_liferay$1util_buffer_1);
                  java.lang.String name = null;
                  name = (java.lang.String) _jspx_page_context.findAttribute("name");
                  out.write("\n\n\t");
                  //  c:choose
                  com.liferay.taglib.core.ChooseTag _jspx_th_c_choose_2 = (com.liferay.taglib.core.ChooseTag) _jspx_tagPool_c_choose.get(com.liferay.taglib.core.ChooseTag.class);
                  _jspx_th_c_choose_2.setPageContext(_jspx_page_context);
                  _jspx_th_c_choose_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1row_1);
                  int _jspx_eval_c_choose_2 = _jspx_th_c_choose_2.doStartTag();
                  if (_jspx_eval_c_choose_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    out.write("\n\t\t");
                    //  c:when
                    com.liferay.taglib.core.WhenTag _jspx_th_c_when_4 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
                    _jspx_th_c_when_4.setPageContext(_jspx_page_context);
                    _jspx_th_c_when_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_2);
                    _jspx_th_c_when_4.setTest( displayStyle.equals("icon") );
                    int _jspx_eval_c_when_4 = _jspx_th_c_when_4.doStartTag();
                    if (_jspx_eval_c_when_4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      out.write("\n\n\t\t\t");

			row.setCssClass("entry-card lfr-asset-item");
			
                      out.write("\n\n\t\t\t");
                      //  liferay-ui:search-container-column-text
                      com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_5 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
                      _jspx_th_liferay$1ui_search$1container$1column$1text_5.setPageContext(_jspx_page_context);
                      _jspx_th_liferay$1ui_search$1container$1column$1text_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_4);
                      int _jspx_eval_liferay$1ui_search$1container$1column$1text_5 = _jspx_th_liferay$1ui_search$1container$1column$1text_5.doStartTag();
                      if (_jspx_eval_liferay$1ui_search$1container$1column$1text_5 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                        if (_jspx_eval_liferay$1ui_search$1container$1column$1text_5 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                          out = _jspx_page_context.pushBody();
                          _jspx_th_liferay$1ui_search$1container$1column$1text_5.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                          _jspx_th_liferay$1ui_search$1container$1column$1text_5.doInitBody();
                        }
                        do {
                          out.write("\n\t\t\t\t");
                          //  liferay-frontend:icon-vertical-card
                          com.liferay.frontend.taglib.servlet.taglib.IconVerticalCardTag _jspx_th_liferay$1frontend_icon$1vertical$1card_0 = (com.liferay.frontend.taglib.servlet.taglib.IconVerticalCardTag) _jspx_tagPool_liferay$1frontend_icon$1vertical$1card_title_subtitle_rowChecker_resultRow_icon_cssClass_nobody.get(com.liferay.frontend.taglib.servlet.taglib.IconVerticalCardTag.class);
                          _jspx_th_liferay$1frontend_icon$1vertical$1card_0.setPageContext(_jspx_page_context);
                          _jspx_th_liferay$1frontend_icon$1vertical$1card_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1column$1text_5);
                          _jspx_th_liferay$1frontend_icon$1vertical$1card_0.setCssClass("entry-display-style");
                          _jspx_th_liferay$1frontend_icon$1vertical$1card_0.setIcon("users");
                          _jspx_th_liferay$1frontend_icon$1vertical$1card_0.setResultRow( row );
                          _jspx_th_liferay$1frontend_icon$1vertical$1card_0.setRowChecker( memberSearchContainer.getRowChecker() );
                          _jspx_th_liferay$1frontend_icon$1vertical$1card_0.setSubtitle( LanguageUtil.get(request, organization.getType()) );
                          _jspx_th_liferay$1frontend_icon$1vertical$1card_0.setTitle( name );
                          int _jspx_eval_liferay$1frontend_icon$1vertical$1card_0 = _jspx_th_liferay$1frontend_icon$1vertical$1card_0.doStartTag();
                          if (_jspx_th_liferay$1frontend_icon$1vertical$1card_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                            _jspx_tagPool_liferay$1frontend_icon$1vertical$1card_title_subtitle_rowChecker_resultRow_icon_cssClass_nobody.reuse(_jspx_th_liferay$1frontend_icon$1vertical$1card_0);
                            return;
                          }
                          _jspx_tagPool_liferay$1frontend_icon$1vertical$1card_title_subtitle_rowChecker_resultRow_icon_cssClass_nobody.reuse(_jspx_th_liferay$1frontend_icon$1vertical$1card_0);
                          out.write("\n\t\t\t");
                          int evalDoAfterBody = _jspx_th_liferay$1ui_search$1container$1column$1text_5.doAfterBody();
                          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                            break;
                        } while (true);
                        if (_jspx_eval_liferay$1ui_search$1container$1column$1text_5 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                          out = _jspx_page_context.popBody();
                      }
                      if (_jspx_th_liferay$1ui_search$1container$1column$1text_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_liferay$1ui_search$1container$1column$1text.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_5);
                        return;
                      }
                      _jspx_tagPool_liferay$1ui_search$1container$1column$1text.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_5);
                      out.write("\n\t\t");
                    }
                    if (_jspx_th_c_when_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_4);
                      return;
                    }
                    _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_4);
                    out.write("\n\t\t");
                    //  c:when
                    com.liferay.taglib.core.WhenTag _jspx_th_c_when_5 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
                    _jspx_th_c_when_5.setPageContext(_jspx_page_context);
                    _jspx_th_c_when_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_2);
                    _jspx_th_c_when_5.setTest( displayStyle.equals("descriptive") );
                    int _jspx_eval_c_when_5 = _jspx_th_c_when_5.doStartTag();
                    if (_jspx_eval_c_when_5 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      out.write("\n\t\t\t");
                      //  liferay-ui:search-container-column-icon
                      com.liferay.taglib.ui.SearchContainerColumnIconTag _jspx_th_liferay$1ui_search$1container$1column$1icon_0 = (com.liferay.taglib.ui.SearchContainerColumnIconTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1icon_toggleRowChecker_icon_nobody.get(com.liferay.taglib.ui.SearchContainerColumnIconTag.class);
                      _jspx_th_liferay$1ui_search$1container$1column$1icon_0.setPageContext(_jspx_page_context);
                      _jspx_th_liferay$1ui_search$1container$1column$1icon_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_5);
                      _jspx_th_liferay$1ui_search$1container$1column$1icon_0.setIcon("users");
                      _jspx_th_liferay$1ui_search$1container$1column$1icon_0.setToggleRowChecker( true );
                      int _jspx_eval_liferay$1ui_search$1container$1column$1icon_0 = _jspx_th_liferay$1ui_search$1container$1column$1icon_0.doStartTag();
                      if (_jspx_th_liferay$1ui_search$1container$1column$1icon_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_liferay$1ui_search$1container$1column$1icon_toggleRowChecker_icon_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1icon_0);
                        return;
                      }
                      _jspx_tagPool_liferay$1ui_search$1container$1column$1icon_toggleRowChecker_icon_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1icon_0);
                      out.write("\n\n\t\t\t");
                      //  liferay-ui:search-container-column-text
                      com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_6 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text_colspan.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
                      _jspx_th_liferay$1ui_search$1container$1column$1text_6.setPageContext(_jspx_page_context);
                      _jspx_th_liferay$1ui_search$1container$1column$1text_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_5);
                      _jspx_th_liferay$1ui_search$1container$1column$1text_6.setColspan( 2 );
                      int _jspx_eval_liferay$1ui_search$1container$1column$1text_6 = _jspx_th_liferay$1ui_search$1container$1column$1text_6.doStartTag();
                      if (_jspx_eval_liferay$1ui_search$1container$1column$1text_6 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                        if (_jspx_eval_liferay$1ui_search$1container$1column$1text_6 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                          out = _jspx_page_context.pushBody();
                          _jspx_th_liferay$1ui_search$1container$1column$1text_6.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                          _jspx_th_liferay$1ui_search$1container$1column$1text_6.doInitBody();
                        }
                        do {
                          out.write("\n\t\t\t\t<h5>");
                          out.print( name );
                          out.write("</h5>\n\n\t\t\t\t<h6 class=\"text-default\">\n\t\t\t\t\t<span>");
                          out.print( HtmlUtil.escape(organization.getParentOrganizationName()) );
                          out.write("</span>\n\t\t\t\t</h6>\n\n\t\t\t\t<h6 class=\"text-default\">\n\t\t\t\t\t<span>");
                          out.print( LanguageUtil.get(request, organization.getType()) );
                          out.write("</span>\n\t\t\t\t</h6>\n\n\t\t\t\t<h6 class=\"text-default\">\n\t\t\t\t\t<span>");
                          out.print( HtmlUtil.escape(organization.getAddress().getCity()) );
                          out.write("</span>\n\t\t\t\t\t<span>");
                          out.print( UsersAdmin.ORGANIZATION_REGION_NAME_ACCESSOR.get(organization) );
                          out.write("</span>\n\t\t\t\t\t<span>");
                          out.print( UsersAdmin.ORGANIZATION_COUNTRY_NAME_ACCESSOR.get(organization) );
                          out.write("</span>\n\t\t\t\t</h6>\n\t\t\t");
                          int evalDoAfterBody = _jspx_th_liferay$1ui_search$1container$1column$1text_6.doAfterBody();
                          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                            break;
                        } while (true);
                        if (_jspx_eval_liferay$1ui_search$1container$1column$1text_6 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                          out = _jspx_page_context.popBody();
                      }
                      if (_jspx_th_liferay$1ui_search$1container$1column$1text_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_liferay$1ui_search$1container$1column$1text_colspan.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_6);
                        return;
                      }
                      _jspx_tagPool_liferay$1ui_search$1container$1column$1text_colspan.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_6);
                      out.write("\n\t\t");
                    }
                    if (_jspx_th_c_when_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_5);
                      return;
                    }
                    _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_5);
                    out.write("\n\t\t");
                    //  c:otherwise
                    com.liferay.taglib.core.OtherwiseTag _jspx_th_c_otherwise_1 = (com.liferay.taglib.core.OtherwiseTag) _jspx_tagPool_c_otherwise.get(com.liferay.taglib.core.OtherwiseTag.class);
                    _jspx_th_c_otherwise_1.setPageContext(_jspx_page_context);
                    _jspx_th_c_otherwise_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_2);
                    int _jspx_eval_c_otherwise_1 = _jspx_th_c_otherwise_1.doStartTag();
                    if (_jspx_eval_c_otherwise_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      out.write("\n\t\t\t");
                      //  liferay-ui:search-container-column-text
                      com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_7 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_orderable_name_cssClass_nobody.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
                      _jspx_th_liferay$1ui_search$1container$1column$1text_7.setPageContext(_jspx_page_context);
                      _jspx_th_liferay$1ui_search$1container$1column$1text_7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_1);
                      _jspx_th_liferay$1ui_search$1container$1column$1text_7.setCssClass("table-cell-expand table-cell-minw-300 table-title");
                      _jspx_th_liferay$1ui_search$1container$1column$1text_7.setName("name");
                      _jspx_th_liferay$1ui_search$1container$1column$1text_7.setOrderable( true );
                      _jspx_th_liferay$1ui_search$1container$1column$1text_7.setValue( name );
                      int _jspx_eval_liferay$1ui_search$1container$1column$1text_7 = _jspx_th_liferay$1ui_search$1container$1column$1text_7.doStartTag();
                      if (_jspx_th_liferay$1ui_search$1container$1column$1text_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_orderable_name_cssClass_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_7);
                        return;
                      }
                      _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_orderable_name_cssClass_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_7);
                      out.write("\n\n\t\t\t");
                      //  liferay-ui:search-container-column-text
                      com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_8 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_cssClass_nobody.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
                      _jspx_th_liferay$1ui_search$1container$1column$1text_8.setPageContext(_jspx_page_context);
                      _jspx_th_liferay$1ui_search$1container$1column$1text_8.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_1);
                      _jspx_th_liferay$1ui_search$1container$1column$1text_8.setCssClass("table-cell-expand-small table-cell-minw-200");
                      _jspx_th_liferay$1ui_search$1container$1column$1text_8.setName("parent-organization");
                      _jspx_th_liferay$1ui_search$1container$1column$1text_8.setValue( HtmlUtil.escape(organization.getParentOrganizationName()) );
                      int _jspx_eval_liferay$1ui_search$1container$1column$1text_8 = _jspx_th_liferay$1ui_search$1container$1column$1text_8.doStartTag();
                      if (_jspx_th_liferay$1ui_search$1container$1column$1text_8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_cssClass_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_8);
                        return;
                      }
                      _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_cssClass_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_8);
                      out.write("\n\n\t\t\t");
                      //  liferay-ui:search-container-column-text
                      com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_9 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_orderable_name_cssClass_nobody.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
                      _jspx_th_liferay$1ui_search$1container$1column$1text_9.setPageContext(_jspx_page_context);
                      _jspx_th_liferay$1ui_search$1container$1column$1text_9.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_1);
                      _jspx_th_liferay$1ui_search$1container$1column$1text_9.setCssClass("table-cell-expand-smallest table-cell-minw-150");
                      _jspx_th_liferay$1ui_search$1container$1column$1text_9.setName("type");
                      _jspx_th_liferay$1ui_search$1container$1column$1text_9.setOrderable( true );
                      _jspx_th_liferay$1ui_search$1container$1column$1text_9.setValue( LanguageUtil.get(request, organization.getType()) );
                      int _jspx_eval_liferay$1ui_search$1container$1column$1text_9 = _jspx_th_liferay$1ui_search$1container$1column$1text_9.doStartTag();
                      if (_jspx_th_liferay$1ui_search$1container$1column$1text_9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_orderable_name_cssClass_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_9);
                        return;
                      }
                      _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_orderable_name_cssClass_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_9);
                      out.write("\n\n\t\t\t");
                      //  liferay-ui:search-container-column-text
                      com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_10 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_cssClass_nobody.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
                      _jspx_th_liferay$1ui_search$1container$1column$1text_10.setPageContext(_jspx_page_context);
                      _jspx_th_liferay$1ui_search$1container$1column$1text_10.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_1);
                      _jspx_th_liferay$1ui_search$1container$1column$1text_10.setCssClass("table-cell-expand-smallest table-cell-minw-150");
                      _jspx_th_liferay$1ui_search$1container$1column$1text_10.setName("city");
                      _jspx_th_liferay$1ui_search$1container$1column$1text_10.setValue( HtmlUtil.escape(organization.getAddress().getCity()) );
                      int _jspx_eval_liferay$1ui_search$1container$1column$1text_10 = _jspx_th_liferay$1ui_search$1container$1column$1text_10.doStartTag();
                      if (_jspx_th_liferay$1ui_search$1container$1column$1text_10.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_cssClass_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_10);
                        return;
                      }
                      _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_cssClass_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_10);
                      out.write("\n\n\t\t\t");
                      //  liferay-ui:search-container-column-text
                      com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_11 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_cssClass_nobody.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
                      _jspx_th_liferay$1ui_search$1container$1column$1text_11.setPageContext(_jspx_page_context);
                      _jspx_th_liferay$1ui_search$1container$1column$1text_11.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_1);
                      _jspx_th_liferay$1ui_search$1container$1column$1text_11.setCssClass("table-cell-expand-smallest table-cell-minw-150");
                      _jspx_th_liferay$1ui_search$1container$1column$1text_11.setName("region");
                      _jspx_th_liferay$1ui_search$1container$1column$1text_11.setValue( UsersAdmin.ORGANIZATION_REGION_NAME_ACCESSOR.get(organization) );
                      int _jspx_eval_liferay$1ui_search$1container$1column$1text_11 = _jspx_th_liferay$1ui_search$1container$1column$1text_11.doStartTag();
                      if (_jspx_th_liferay$1ui_search$1container$1column$1text_11.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_cssClass_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_11);
                        return;
                      }
                      _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_cssClass_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_11);
                      out.write("\n\n\t\t\t");
                      //  liferay-ui:search-container-column-text
                      com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_12 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_cssClass_nobody.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
                      _jspx_th_liferay$1ui_search$1container$1column$1text_12.setPageContext(_jspx_page_context);
                      _jspx_th_liferay$1ui_search$1container$1column$1text_12.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_1);
                      _jspx_th_liferay$1ui_search$1container$1column$1text_12.setCssClass("table-cell-expand-smallest table-cell-minw-150");
                      _jspx_th_liferay$1ui_search$1container$1column$1text_12.setName("country");
                      _jspx_th_liferay$1ui_search$1container$1column$1text_12.setValue( UsersAdmin.ORGANIZATION_COUNTRY_NAME_ACCESSOR.get(organization) );
                      int _jspx_eval_liferay$1ui_search$1container$1column$1text_12 = _jspx_th_liferay$1ui_search$1container$1column$1text_12.doStartTag();
                      if (_jspx_th_liferay$1ui_search$1container$1column$1text_12.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_cssClass_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_12);
                        return;
                      }
                      _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_cssClass_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_12);
                      out.write("\n\t\t");
                    }
                    if (_jspx_th_c_otherwise_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_1);
                      return;
                    }
                    _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_1);
                    out.write('\n');
                    out.write('	');
                  }
                  if (_jspx_th_c_choose_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_2);
                    return;
                  }
                  _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_2);
                  out.write('\n');
                  int evalDoAfterBody = _jspx_th_liferay$1ui_search$1container$1row_1.doAfterBody();
                  index = (java.lang.Integer) _jspx_page_context.findAttribute("index");
                  organization = (com.liferay.portal.kernel.model.Organization) _jspx_page_context.findAttribute("organization");
                  row = (com.liferay.portal.kernel.dao.search.ResultRow) _jspx_page_context.findAttribute("row");
                  if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                    break;
                } while (true);
                if (_jspx_eval_liferay$1ui_search$1container$1row_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                  out = _jspx_page_context.popBody();
              }
              if (_jspx_th_liferay$1ui_search$1container$1row_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_liferay$1ui_search$1container$1row_modelVar_keyProperty_escapedModel_className.reuse(_jspx_th_liferay$1ui_search$1container$1row_1);
                return;
              }
              _jspx_tagPool_liferay$1ui_search$1container$1row_modelVar_keyProperty_escapedModel_className.reuse(_jspx_th_liferay$1ui_search$1container$1row_1);
              out.write("\n\t\t\t");
            }
            if (_jspx_th_c_when_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_3);
              return;
            }
            _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_3);
            out.write("\n\t\t");
          }
          if (_jspx_th_c_choose_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_0);
            return;
          }
          _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_0);
          out.write("\n\n\t\t");
          //  liferay-ui:search-iterator
          com.liferay.taglib.ui.SearchIteratorTag _jspx_th_liferay$1ui_search$1iterator_0 = (com.liferay.taglib.ui.SearchIteratorTag) _jspx_tagPool_liferay$1ui_search$1iterator_markupView_displayStyle_nobody.get(com.liferay.taglib.ui.SearchIteratorTag.class);
          _jspx_th_liferay$1ui_search$1iterator_0.setPageContext(_jspx_page_context);
          _jspx_th_liferay$1ui_search$1iterator_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container_0);
          _jspx_th_liferay$1ui_search$1iterator_0.setDisplayStyle( displayStyle );
          _jspx_th_liferay$1ui_search$1iterator_0.setMarkupView("lexicon");
          int _jspx_eval_liferay$1ui_search$1iterator_0 = _jspx_th_liferay$1ui_search$1iterator_0.doStartTag();
          if (_jspx_th_liferay$1ui_search$1iterator_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_liferay$1ui_search$1iterator_markupView_displayStyle_nobody.reuse(_jspx_th_liferay$1ui_search$1iterator_0);
            return;
          }
          _jspx_tagPool_liferay$1ui_search$1iterator_markupView_displayStyle_nobody.reuse(_jspx_th_liferay$1ui_search$1iterator_0);
          out.write('\n');
          out.write('	');
        }
        if (_jspx_th_liferay$1ui_search$1container_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_liferay$1ui_search$1container_var_searchContainer_id.reuse(_jspx_th_liferay$1ui_search$1container_0);
          return;
        }
        _jspx_tagPool_liferay$1ui_search$1container_var_searchContainer_id.reuse(_jspx_th_liferay$1ui_search$1container_0);
        out.write('\n');
      }
      if (_jspx_th_aui_form_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_aui_form_name_cssClass.reuse(_jspx_th_aui_form_0);
        return;
      }
      _jspx_tagPool_aui_form_name_cssClass.reuse(_jspx_th_aui_form_0);
      out.write('\n');
      out.write('\n');
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
          if (_jspx_meth_portlet_namespace_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_0, _jspx_page_context))
            return;
          out.write("' + 'passwordPolicyMembers');\n\n\tsearchContainer.on(\n\t\t'rowToggled',\n\t\tfunction(event) {\n\t\t\tvar selectedItems = event.elements.allSelectedElements;\n\n\t\t\tvar result = {};\n\n\t\t\tif (selectedItems.size()) {\n\t\t\t\tresult = {\n\t\t\t\t\tdata: {\n\t\t\t\t\t\titem: selectedItems.attr('value').join(','),\n\t\t\t\t\t\tmemberType: '");
          out.print( HtmlUtil.escapeJS(tabs2) );
          out.write("'\n\t\t\t\t\t}\n\t\t\t\t};\n\t\t\t}\n\n\t\t\tLiferay.Util.getOpener().Liferay.fire(\n\t\t\t\t'");
          out.print( HtmlUtil.escapeJS(eventName) );
          out.write("', result);\n\t\t}\n\t);\n");
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

  private boolean _jspx_meth_portlet_param_0(javax.servlet.jsp.tagext.JspTag _jspx_th_portlet_renderURL_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:param
    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_0 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
    _jspx_th_portlet_param_0.setPageContext(_jspx_page_context);
    _jspx_th_portlet_param_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_0);
    _jspx_th_portlet_param_0.setName("mvcPath");
    _jspx_th_portlet_param_0.setValue("/edit_password_policy_assignments.jsp");
    int _jspx_eval_portlet_param_0 = _jspx_th_portlet_param_0.doStartTag();
    if (_jspx_th_portlet_param_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_0);
      return true;
    }
    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_0);
    return false;
  }

  private boolean _jspx_meth_portlet_param_2(javax.servlet.jsp.tagext.JspTag _jspx_th_portlet_renderURL_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:param
    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_2 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
    _jspx_th_portlet_param_2.setPageContext(_jspx_page_context);
    _jspx_th_portlet_param_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_0);
    _jspx_th_portlet_param_2.setName("tabs2");
    _jspx_th_portlet_param_2.setValue("users");
    int _jspx_eval_portlet_param_2 = _jspx_th_portlet_param_2.doStartTag();
    if (_jspx_th_portlet_param_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_2);
      return true;
    }
    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_2);
    return false;
  }

  private boolean _jspx_meth_portlet_param_5(javax.servlet.jsp.tagext.JspTag _jspx_th_portlet_renderURL_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:param
    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_5 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
    _jspx_th_portlet_param_5.setPageContext(_jspx_page_context);
    _jspx_th_portlet_param_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_1);
    _jspx_th_portlet_param_5.setName("mvcPath");
    _jspx_th_portlet_param_5.setValue("/edit_password_policy_assignments.jsp");
    int _jspx_eval_portlet_param_5 = _jspx_th_portlet_param_5.doStartTag();
    if (_jspx_th_portlet_param_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_5);
      return true;
    }
    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_5);
    return false;
  }

  private boolean _jspx_meth_portlet_param_7(javax.servlet.jsp.tagext.JspTag _jspx_th_portlet_renderURL_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:param
    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_7 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
    _jspx_th_portlet_param_7.setPageContext(_jspx_page_context);
    _jspx_th_portlet_param_7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_1);
    _jspx_th_portlet_param_7.setName("tabs2");
    _jspx_th_portlet_param_7.setValue("organizations");
    int _jspx_eval_portlet_param_7 = _jspx_th_portlet_param_7.doStartTag();
    if (_jspx_th_portlet_param_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_7);
      return true;
    }
    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_7);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_0(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_0 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_0.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_0);
    int _jspx_eval_portlet_namespace_0 = _jspx_th_portlet_namespace_0.doStartTag();
    if (_jspx_th_portlet_namespace_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_0);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_0);
    return false;
  }
}
