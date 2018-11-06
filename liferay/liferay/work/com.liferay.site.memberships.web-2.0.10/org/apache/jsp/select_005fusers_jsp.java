package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.search.ResultRow;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.DuplicateGroupException;
import com.liferay.portal.kernel.exception.GroupKeyException;
import com.liferay.portal.kernel.exception.MembershipRequestCommentsException;
import com.liferay.portal.kernel.exception.RequiredGroupException;
import com.liferay.portal.kernel.exception.RequiredUserException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.model.MembershipRequest;
import com.liferay.portal.kernel.model.MembershipRequestConstants;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.OrganizationConstants;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.Team;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.UserGroup;
import com.liferay.portal.kernel.model.UserGroupGroupRole;
import com.liferay.portal.kernel.model.UserGroupRole;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.security.membershippolicy.SiteMembershipPolicyUtil;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.service.MembershipRequestLocalServiceUtil;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.TeamLocalServiceUtil;
import com.liferay.portal.kernel.service.UserGroupGroupRoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserGroupRoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.service.permission.GroupPermissionUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.webserver.WebServerServletTokenUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.site.memberships.web.internal.constants.SiteMembershipWebKeys;
import com.liferay.site.memberships.web.internal.display.context.OrganizationsDisplayContext;
import com.liferay.site.memberships.web.internal.display.context.SelectOrganizationsDisplayContext;
import com.liferay.site.memberships.web.internal.display.context.SelectSiteRolesDisplayContext;
import com.liferay.site.memberships.web.internal.display.context.SelectUserGroupsDisplayContext;
import com.liferay.site.memberships.web.internal.display.context.SelectUsersDisplayContext;
import com.liferay.site.memberships.web.internal.display.context.SiteMembershipsDisplayContext;
import com.liferay.site.memberships.web.internal.display.context.SiteRolesDisplayContext;
import com.liferay.site.memberships.web.internal.display.context.UserGroupRolesDisplayContext;
import com.liferay.site.memberships.web.internal.display.context.UserGroupsDisplayContext;
import com.liferay.site.memberships.web.internal.display.context.UserRolesDisplayContext;
import com.liferay.site.memberships.web.internal.display.context.UsersDisplayContext;
import com.liferay.site.memberships.web.internal.display.context.ViewMembershipRequestsDisplayContext;
import com.liferay.sites.kernel.util.SitesUtil;
import com.liferay.users.admin.kernel.util.UsersAdmin;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import javax.portlet.PortletURL;
import javax.portlet.WindowState;

public final class select_005fusers_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(3);
    _jspx_dependants.add("/init.jsp");
    _jspx_dependants.add("/init-ext.jsp");
    _jspx_dependants.add("/user_columns.jspf");
  }

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_namespace_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_if_test;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container$1column$1jsp_path_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container$1row_rowIdProperty_modelVar_keyProperty_escapedModel_className;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container$1column$1text_property_orderable_name_cssClass_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_otherwise;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container$1column$1text_colspan;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1theme_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_choose;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1frontend_user$1vertical$1card_userId_title_subtitle_rowChecker_resultRow_cssClass_actionJspServletContext_actionJsp;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1frontend_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_membership$1policy$1error_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container$1column$1text;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_script_use;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_cssClass_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container_searchContainer_id;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_user$1portrait_userId_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_clay_management$1toolbar_viewTypeItems_sortingURL_sortingOrder_showSearch_searchFormName_searchContainerId_searchActionURL_itemsTotal_filterDropdownItems_disabled_componentId_clearResultsURL_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_form_name_cssClass;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_when_test;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1frontend_vertical$1card$1footer;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1iterator_markupView_displayStyle_nobody;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_portlet_namespace_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_if_test = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container$1column$1jsp_path_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container$1row_rowIdProperty_modelVar_keyProperty_escapedModel_className = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_property_orderable_name_cssClass_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_otherwise = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_colspan = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1theme_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_choose = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1frontend_user$1vertical$1card_userId_title_subtitle_rowChecker_resultRow_cssClass_actionJspServletContext_actionJsp = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1frontend_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_membership$1policy$1error_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_script_use = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_cssClass_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container_searchContainer_id = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_user$1portrait_userId_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_clay_management$1toolbar_viewTypeItems_sortingURL_sortingOrder_showSearch_searchFormName_searchContainerId_searchActionURL_itemsTotal_filterDropdownItems_disabled_componentId_clearResultsURL_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_form_name_cssClass = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_when_test = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1frontend_vertical$1card$1footer = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1iterator_markupView_displayStyle_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_portlet_namespace_nobody.release();
    _jspx_tagPool_c_if_test.release();
    _jspx_tagPool_liferay$1ui_search$1container$1column$1jsp_path_nobody.release();
    _jspx_tagPool_liferay$1ui_search$1container$1row_rowIdProperty_modelVar_keyProperty_escapedModel_className.release();
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_property_orderable_name_cssClass_nobody.release();
    _jspx_tagPool_c_otherwise.release();
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_colspan.release();
    _jspx_tagPool_portlet_defineObjects_nobody.release();
    _jspx_tagPool_liferay$1theme_defineObjects_nobody.release();
    _jspx_tagPool_c_choose.release();
    _jspx_tagPool_liferay$1frontend_user$1vertical$1card_userId_title_subtitle_rowChecker_resultRow_cssClass_actionJspServletContext_actionJsp.release();
    _jspx_tagPool_liferay$1frontend_defineObjects_nobody.release();
    _jspx_tagPool_liferay$1ui_membership$1policy$1error_nobody.release();
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text.release();
    _jspx_tagPool_aui_script_use.release();
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_cssClass_nobody.release();
    _jspx_tagPool_liferay$1ui_search$1container_searchContainer_id.release();
    _jspx_tagPool_liferay$1ui_user$1portrait_userId_nobody.release();
    _jspx_tagPool_clay_management$1toolbar_viewTypeItems_sortingURL_sortingOrder_showSearch_searchFormName_searchContainerId_searchActionURL_itemsTotal_filterDropdownItems_disabled_componentId_clearResultsURL_nobody.release();
    _jspx_tagPool_aui_form_name_cssClass.release();
    _jspx_tagPool_c_when_test.release();
    _jspx_tagPool_liferay$1frontend_vertical$1card$1footer.release();
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

SiteMembershipsDisplayContext siteMembershipsDisplayContext = new SiteMembershipsDisplayContext(request, liferayPortletResponse);

portletDisplay.setShowStagingIcon(false);

      out.write('\n');
      out.write('\n');
      out.write('\n');
      out.write('\n');

SelectUsersDisplayContext selectUsersDisplayContext = new SelectUsersDisplayContext(request, renderRequest, renderResponse);

      out.write('\n');
      out.write('\n');
      //  clay:management-toolbar
      com.liferay.frontend.taglib.clay.servlet.taglib.soy.ManagementToolbarTag _jspx_th_clay_management$1toolbar_0 = (com.liferay.frontend.taglib.clay.servlet.taglib.soy.ManagementToolbarTag) _jspx_tagPool_clay_management$1toolbar_viewTypeItems_sortingURL_sortingOrder_showSearch_searchFormName_searchContainerId_searchActionURL_itemsTotal_filterDropdownItems_disabled_componentId_clearResultsURL_nobody.get(com.liferay.frontend.taglib.clay.servlet.taglib.soy.ManagementToolbarTag.class);
      _jspx_th_clay_management$1toolbar_0.setPageContext(_jspx_page_context);
      _jspx_th_clay_management$1toolbar_0.setParent(null);
      _jspx_th_clay_management$1toolbar_0.setClearResultsURL( selectUsersDisplayContext.getClearResultsURL() );
      _jspx_th_clay_management$1toolbar_0.setComponentId("usersManagementToolbar");
      _jspx_th_clay_management$1toolbar_0.setDisabled( selectUsersDisplayContext.isDisabledManagementBar() );
      _jspx_th_clay_management$1toolbar_0.setFilterDropdownItems( selectUsersDisplayContext.getFilterDropdownItems() );
      _jspx_th_clay_management$1toolbar_0.setItemsTotal( selectUsersDisplayContext.getTotalItems() );
      _jspx_th_clay_management$1toolbar_0.setSearchActionURL( selectUsersDisplayContext.getSearchActionURL() );
      _jspx_th_clay_management$1toolbar_0.setSearchContainerId("users");
      _jspx_th_clay_management$1toolbar_0.setSearchFormName("searchFm");
      _jspx_th_clay_management$1toolbar_0.setShowSearch( selectUsersDisplayContext.isShowSearch() );
      _jspx_th_clay_management$1toolbar_0.setSortingOrder( selectUsersDisplayContext.getOrderByType() );
      _jspx_th_clay_management$1toolbar_0.setSortingURL( selectUsersDisplayContext.getSortingURL() );
      _jspx_th_clay_management$1toolbar_0.setViewTypeItems( selectUsersDisplayContext.getViewTypeItems() );
      int _jspx_eval_clay_management$1toolbar_0 = _jspx_th_clay_management$1toolbar_0.doStartTag();
      if (_jspx_th_clay_management$1toolbar_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_clay_management$1toolbar_viewTypeItems_sortingURL_sortingOrder_showSearch_searchFormName_searchContainerId_searchActionURL_itemsTotal_filterDropdownItems_disabled_componentId_clearResultsURL_nobody.reuse(_jspx_th_clay_management$1toolbar_0);
        return;
      }
      _jspx_tagPool_clay_management$1toolbar_viewTypeItems_sortingURL_sortingOrder_showSearch_searchFormName_searchContainerId_searchActionURL_itemsTotal_filterDropdownItems_disabled_componentId_clearResultsURL_nobody.reuse(_jspx_th_clay_management$1toolbar_0);
      out.write('\n');
      out.write('\n');
      //  aui:form
      com.liferay.taglib.aui.FormTag _jspx_th_aui_form_0 = (com.liferay.taglib.aui.FormTag) _jspx_tagPool_aui_form_name_cssClass.get(com.liferay.taglib.aui.FormTag.class);
      _jspx_th_aui_form_0.setPageContext(_jspx_page_context);
      _jspx_th_aui_form_0.setParent(null);
      _jspx_th_aui_form_0.setCssClass("container-fluid-1280 portlet-site-memberships-select-users");
      _jspx_th_aui_form_0.setName("fm");
      int _jspx_eval_aui_form_0 = _jspx_th_aui_form_0.doStartTag();
      if (_jspx_eval_aui_form_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        out.write('\n');
        out.write('	');
        if (_jspx_meth_liferay$1ui_membership$1policy$1error_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_form_0, _jspx_page_context))
          return;
        out.write("\n\n\t");
        //  liferay-ui:search-container
        com.liferay.taglib.ui.SearchContainerTag _jspx_th_liferay$1ui_search$1container_0 = (com.liferay.taglib.ui.SearchContainerTag) _jspx_tagPool_liferay$1ui_search$1container_searchContainer_id.get(com.liferay.taglib.ui.SearchContainerTag.class);
        _jspx_th_liferay$1ui_search$1container_0.setPageContext(_jspx_page_context);
        _jspx_th_liferay$1ui_search$1container_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
        _jspx_th_liferay$1ui_search$1container_0.setId("users");
        _jspx_th_liferay$1ui_search$1container_0.setSearchContainer( selectUsersDisplayContext.getUserSearchContainer() );
        int _jspx_eval_liferay$1ui_search$1container_0 = _jspx_th_liferay$1ui_search$1container_0.doStartTag();
        if (_jspx_eval_liferay$1ui_search$1container_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          java.lang.Integer total = null;
          com.liferay.portal.kernel.dao.search.SearchContainer searchContainer = null;
          total = (java.lang.Integer) _jspx_page_context.findAttribute("total");
          searchContainer = (com.liferay.portal.kernel.dao.search.SearchContainer) _jspx_page_context.findAttribute("searchContainer");
          out.write("\n\t\t");
          //  liferay-ui:search-container-row
          com.liferay.taglib.ui.SearchContainerRowTag _jspx_th_liferay$1ui_search$1container$1row_0 = (com.liferay.taglib.ui.SearchContainerRowTag) _jspx_tagPool_liferay$1ui_search$1container$1row_rowIdProperty_modelVar_keyProperty_escapedModel_className.get(com.liferay.taglib.ui.SearchContainerRowTag.class);
          _jspx_th_liferay$1ui_search$1container$1row_0.setPageContext(_jspx_page_context);
          _jspx_th_liferay$1ui_search$1container$1row_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container_0);
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
              out.write("\n\n\t\t\t");

			String displayStyle = selectUsersDisplayContext.getDisplayStyle();

			boolean selectUsers = true;
			
              out.write("\n\n\t\t\t");
              out.write('\n');
              out.write('\n');

Set<String> names = new TreeSet<String>();

List<Role> userGroupGroupRoles = RoleLocalServiceUtil.getUserGroupGroupRoles(user2.getUserId(), siteMembershipsDisplayContext.getGroupId());

names.addAll(ListUtil.toList(userGroupGroupRoles, Role.TITLE_ACCESSOR));

List<UserGroupRole> userGroupRoles = UserGroupRoleLocalServiceUtil.getUserGroupRoles(user2.getUserId(), siteMembershipsDisplayContext.getGroupId());

names.addAll(ListUtil.toList(userGroupRoles, UsersAdmin.USER_GROUP_ROLE_TITLE_ACCESSOR));

List<Team> teams = TeamLocalServiceUtil.getUserOrUserGroupTeams(siteMembershipsDisplayContext.getGroupId(), user2.getUserId());

names.addAll(ListUtil.toList(teams, Team.NAME_ACCESSOR));

              out.write('\n');
              out.write('\n');
              //  c:choose
              com.liferay.taglib.core.ChooseTag _jspx_th_c_choose_0 = (com.liferay.taglib.core.ChooseTag) _jspx_tagPool_c_choose.get(com.liferay.taglib.core.ChooseTag.class);
              _jspx_th_c_choose_0.setPageContext(_jspx_page_context);
              _jspx_th_c_choose_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1row_0);
              int _jspx_eval_c_choose_0 = _jspx_th_c_choose_0.doStartTag();
              if (_jspx_eval_c_choose_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write('\n');
                out.write('	');
                //  c:when
                com.liferay.taglib.core.WhenTag _jspx_th_c_when_0 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
                _jspx_th_c_when_0.setPageContext(_jspx_page_context);
                _jspx_th_c_when_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_0);
                _jspx_th_c_when_0.setTest( displayStyle.equals("icon") );
                int _jspx_eval_c_when_0 = _jspx_th_c_when_0.doStartTag();
                if (_jspx_eval_c_when_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  out.write("\n\n\t\t");

		row.setCssClass("entry-card lfr-asset-item");
		
                  out.write("\n\n\t\t");
                  //  liferay-ui:search-container-column-text
                  com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_0 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
                  _jspx_th_liferay$1ui_search$1container$1column$1text_0.setPageContext(_jspx_page_context);
                  _jspx_th_liferay$1ui_search$1container$1column$1text_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_0);
                  int _jspx_eval_liferay$1ui_search$1container$1column$1text_0 = _jspx_th_liferay$1ui_search$1container$1column$1text_0.doStartTag();
                  if (_jspx_eval_liferay$1ui_search$1container$1column$1text_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    if (_jspx_eval_liferay$1ui_search$1container$1column$1text_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                      out = _jspx_page_context.pushBody();
                      _jspx_th_liferay$1ui_search$1container$1column$1text_0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                      _jspx_th_liferay$1ui_search$1container$1column$1text_0.doInitBody();
                    }
                    do {
                      out.write("\n\t\t\t");
                      //  liferay-frontend:user-vertical-card
                      com.liferay.frontend.taglib.servlet.taglib.UserVerticalCardTag _jspx_th_liferay$1frontend_user$1vertical$1card_0 = (com.liferay.frontend.taglib.servlet.taglib.UserVerticalCardTag) _jspx_tagPool_liferay$1frontend_user$1vertical$1card_userId_title_subtitle_rowChecker_resultRow_cssClass_actionJspServletContext_actionJsp.get(com.liferay.frontend.taglib.servlet.taglib.UserVerticalCardTag.class);
                      _jspx_th_liferay$1frontend_user$1vertical$1card_0.setPageContext(_jspx_page_context);
                      _jspx_th_liferay$1frontend_user$1vertical$1card_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1column$1text_0);
                      _jspx_th_liferay$1frontend_user$1vertical$1card_0.setActionJsp( !selectUsers ? "/user_action.jsp" : StringPool.BLANK );
                      _jspx_th_liferay$1frontend_user$1vertical$1card_0.setActionJspServletContext( application );
                      _jspx_th_liferay$1frontend_user$1vertical$1card_0.setCssClass("entry-display-style");
                      _jspx_th_liferay$1frontend_user$1vertical$1card_0.setResultRow( row );
                      _jspx_th_liferay$1frontend_user$1vertical$1card_0.setRowChecker( searchContainer.getRowChecker() );
                      _jspx_th_liferay$1frontend_user$1vertical$1card_0.setSubtitle( user2.getScreenName() );
                      _jspx_th_liferay$1frontend_user$1vertical$1card_0.setTitle( user2.getFullName() );
                      _jspx_th_liferay$1frontend_user$1vertical$1card_0.setUserId( user2.getUserId() );
                      int _jspx_eval_liferay$1frontend_user$1vertical$1card_0 = _jspx_th_liferay$1frontend_user$1vertical$1card_0.doStartTag();
                      if (_jspx_eval_liferay$1frontend_user$1vertical$1card_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                        out.write("\n\t\t\t\t");
                        //  c:if
                        com.liferay.taglib.core.IfTag _jspx_th_c_if_0 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                        _jspx_th_c_if_0.setPageContext(_jspx_page_context);
                        _jspx_th_c_if_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_user$1vertical$1card_0);
                        _jspx_th_c_if_0.setTest( !selectUsers );
                        int _jspx_eval_c_if_0 = _jspx_th_c_if_0.doStartTag();
                        if (_jspx_eval_c_if_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                          out.write("\n\t\t\t\t\t");
                          //  liferay-frontend:vertical-card-footer
                          com.liferay.frontend.taglib.servlet.taglib.VerticalCardFooterTag _jspx_th_liferay$1frontend_vertical$1card$1footer_0 = (com.liferay.frontend.taglib.servlet.taglib.VerticalCardFooterTag) _jspx_tagPool_liferay$1frontend_vertical$1card$1footer.get(com.liferay.frontend.taglib.servlet.taglib.VerticalCardFooterTag.class);
                          _jspx_th_liferay$1frontend_vertical$1card$1footer_0.setPageContext(_jspx_page_context);
                          _jspx_th_liferay$1frontend_vertical$1card$1footer_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_0);
                          int _jspx_eval_liferay$1frontend_vertical$1card$1footer_0 = _jspx_th_liferay$1frontend_vertical$1card$1footer_0.doStartTag();
                          if (_jspx_eval_liferay$1frontend_vertical$1card$1footer_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                            if (_jspx_eval_liferay$1frontend_vertical$1card$1footer_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                              out = _jspx_page_context.pushBody();
                              _jspx_th_liferay$1frontend_vertical$1card$1footer_0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                              _jspx_th_liferay$1frontend_vertical$1card$1footer_0.doInitBody();
                            }
                            do {
                              out.write("\n\t\t\t\t\t\t<span class=\"text-default\">\n\t\t\t\t\t\t\t");
                              //  c:choose
                              com.liferay.taglib.core.ChooseTag _jspx_th_c_choose_1 = (com.liferay.taglib.core.ChooseTag) _jspx_tagPool_c_choose.get(com.liferay.taglib.core.ChooseTag.class);
                              _jspx_th_c_choose_1.setPageContext(_jspx_page_context);
                              _jspx_th_c_choose_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_vertical$1card$1footer_0);
                              int _jspx_eval_c_choose_1 = _jspx_th_c_choose_1.doStartTag();
                              if (_jspx_eval_c_choose_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                              out.write("\n\t\t\t\t\t\t\t\t");
                              //  c:when
                              com.liferay.taglib.core.WhenTag _jspx_th_c_when_1 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
                              _jspx_th_c_when_1.setPageContext(_jspx_page_context);
                              _jspx_th_c_when_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_1);
                              _jspx_th_c_when_1.setTest( !SetUtil.isEmpty(names) );
                              int _jspx_eval_c_when_1 = _jspx_th_c_when_1.doStartTag();
                              if (_jspx_eval_c_when_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                              out.write("\n\t\t\t\t\t\t\t\t\t");
                              out.print( HtmlUtil.escape(StringUtil.merge(names, StringPool.COMMA_AND_SPACE)) );
                              out.write("\n\t\t\t\t\t\t\t\t");
                              }
                              if (_jspx_th_c_when_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_1);
                              return;
                              }
                              _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_1);
                              out.write("\n\t\t\t\t\t\t\t\t");
                              if (_jspx_meth_c_otherwise_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_choose_1, _jspx_page_context))
                              return;
                              out.write("\n\t\t\t\t\t\t\t");
                              }
                              if (_jspx_th_c_choose_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_1);
                              return;
                              }
                              _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_1);
                              out.write("\n\t\t\t\t\t\t</span>\n\t\t\t\t\t");
                              int evalDoAfterBody = _jspx_th_liferay$1frontend_vertical$1card$1footer_0.doAfterBody();
                              if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                              break;
                            } while (true);
                            if (_jspx_eval_liferay$1frontend_vertical$1card$1footer_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                              out = _jspx_page_context.popBody();
                          }
                          if (_jspx_th_liferay$1frontend_vertical$1card$1footer_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                            _jspx_tagPool_liferay$1frontend_vertical$1card$1footer.reuse(_jspx_th_liferay$1frontend_vertical$1card$1footer_0);
                            return;
                          }
                          _jspx_tagPool_liferay$1frontend_vertical$1card$1footer.reuse(_jspx_th_liferay$1frontend_vertical$1card$1footer_0);
                          out.write("\n\t\t\t\t");
                        }
                        if (_jspx_th_c_if_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                          _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
                          return;
                        }
                        _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
                        out.write("\n\t\t\t");
                      }
                      if (_jspx_th_liferay$1frontend_user$1vertical$1card_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_liferay$1frontend_user$1vertical$1card_userId_title_subtitle_rowChecker_resultRow_cssClass_actionJspServletContext_actionJsp.reuse(_jspx_th_liferay$1frontend_user$1vertical$1card_0);
                        return;
                      }
                      _jspx_tagPool_liferay$1frontend_user$1vertical$1card_userId_title_subtitle_rowChecker_resultRow_cssClass_actionJspServletContext_actionJsp.reuse(_jspx_th_liferay$1frontend_user$1vertical$1card_0);
                      out.write("\n\t\t");
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
                  out.write('\n');
                  out.write('	');
                }
                if (_jspx_th_c_when_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_0);
                  return;
                }
                _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_0);
                out.write('\n');
                out.write('	');
                //  c:when
                com.liferay.taglib.core.WhenTag _jspx_th_c_when_2 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
                _jspx_th_c_when_2.setPageContext(_jspx_page_context);
                _jspx_th_c_when_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_0);
                _jspx_th_c_when_2.setTest( displayStyle.equals("descriptive") );
                int _jspx_eval_c_when_2 = _jspx_th_c_when_2.doStartTag();
                if (_jspx_eval_c_when_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  out.write("\n\t\t");
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
                      out.write("\n\t\t\t");
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
                      out.write("\n\t\t");
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
                  out.write("\n\n\t\t");
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
                      out.write("\n\t\t\t<h5>");
                      out.print( user2.getFullName() );
                      out.write("</h5>\n\n\t\t\t<h6 class=\"text-default\">\n\t\t\t\t<span>");
                      out.print( user2.getScreenName() );
                      out.write("</span>\n\t\t\t</h6>\n\n\t\t\t");
                      //  c:if
                      com.liferay.taglib.core.IfTag _jspx_th_c_if_1 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                      _jspx_th_c_if_1.setPageContext(_jspx_page_context);
                      _jspx_th_c_if_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1column$1text_2);
                      _jspx_th_c_if_1.setTest( !selectUsers );
                      int _jspx_eval_c_if_1 = _jspx_th_c_if_1.doStartTag();
                      if (_jspx_eval_c_if_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                        out.write("\n\t\t\t\t<h6>\n\t\t\t\t\t");
                        out.print( HtmlUtil.escape(StringUtil.merge(names, StringPool.COMMA_AND_SPACE)) );
                        out.write("\n\t\t\t\t</h6>\n\t\t\t");
                      }
                      if (_jspx_th_c_if_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_1);
                        return;
                      }
                      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_1);
                      out.write("\n\t\t");
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
                  out.write("\n\n\t\t");
                  //  c:if
                  com.liferay.taglib.core.IfTag _jspx_th_c_if_2 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                  _jspx_th_c_if_2.setPageContext(_jspx_page_context);
                  _jspx_th_c_if_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_2);
                  _jspx_th_c_if_2.setTest( !selectUsers );
                  int _jspx_eval_c_if_2 = _jspx_th_c_if_2.doStartTag();
                  if (_jspx_eval_c_if_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    out.write("\n\t\t\t");
                    if (_jspx_meth_liferay$1ui_search$1container$1column$1jsp_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_2, _jspx_page_context))
                      return;
                    out.write("\n\t\t");
                  }
                  if (_jspx_th_c_if_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_2);
                    return;
                  }
                  _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_2);
                  out.write('\n');
                  out.write('	');
                }
                if (_jspx_th_c_when_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_2);
                  return;
                }
                _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_2);
                out.write('\n');
                out.write('	');
                //  c:otherwise
                com.liferay.taglib.core.OtherwiseTag _jspx_th_c_otherwise_1 = (com.liferay.taglib.core.OtherwiseTag) _jspx_tagPool_c_otherwise.get(com.liferay.taglib.core.OtherwiseTag.class);
                _jspx_th_c_otherwise_1.setPageContext(_jspx_page_context);
                _jspx_th_c_otherwise_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_0);
                int _jspx_eval_c_otherwise_1 = _jspx_th_c_otherwise_1.doStartTag();
                if (_jspx_eval_c_otherwise_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  out.write("\n\t\t");
                  //  liferay-ui:search-container-column-text
                  com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_3 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_cssClass_nobody.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
                  _jspx_th_liferay$1ui_search$1container$1column$1text_3.setPageContext(_jspx_page_context);
                  _jspx_th_liferay$1ui_search$1container$1column$1text_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_1);
                  _jspx_th_liferay$1ui_search$1container$1column$1text_3.setCssClass("table-cell-expand table-cell-minw-200 table-title");
                  _jspx_th_liferay$1ui_search$1container$1column$1text_3.setName("name");
                  _jspx_th_liferay$1ui_search$1container$1column$1text_3.setValue( user2.getFullName() );
                  int _jspx_eval_liferay$1ui_search$1container$1column$1text_3 = _jspx_th_liferay$1ui_search$1container$1column$1text_3.doStartTag();
                  if (_jspx_th_liferay$1ui_search$1container$1column$1text_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_cssClass_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_3);
                    return;
                  }
                  _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_cssClass_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_3);
                  out.write("\n\n\t\t");
                  //  liferay-ui:search-container-column-text
                  com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_4 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text_property_orderable_name_cssClass_nobody.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
                  _jspx_th_liferay$1ui_search$1container$1column$1text_4.setPageContext(_jspx_page_context);
                  _jspx_th_liferay$1ui_search$1container$1column$1text_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_1);
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
                  out.write("\n\n\t\t");
                  //  c:if
                  com.liferay.taglib.core.IfTag _jspx_th_c_if_3 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                  _jspx_th_c_if_3.setPageContext(_jspx_page_context);
                  _jspx_th_c_if_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_1);
                  _jspx_th_c_if_3.setTest( !selectUsers );
                  int _jspx_eval_c_if_3 = _jspx_th_c_if_3.doStartTag();
                  if (_jspx_eval_c_if_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    out.write("\n\t\t\t");
                    //  liferay-ui:search-container-column-text
                    com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_5 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_cssClass_nobody.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
                    _jspx_th_liferay$1ui_search$1container$1column$1text_5.setPageContext(_jspx_page_context);
                    _jspx_th_liferay$1ui_search$1container$1column$1text_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_3);
                    _jspx_th_liferay$1ui_search$1container$1column$1text_5.setCssClass("table-cell-expand table-cell-minw-200");
                    _jspx_th_liferay$1ui_search$1container$1column$1text_5.setName("site-roles-and-teams");
                    _jspx_th_liferay$1ui_search$1container$1column$1text_5.setValue( HtmlUtil.escape(StringUtil.merge(names, StringPool.COMMA_AND_SPACE)) );
                    int _jspx_eval_liferay$1ui_search$1container$1column$1text_5 = _jspx_th_liferay$1ui_search$1container$1column$1text_5.doStartTag();
                    if (_jspx_th_liferay$1ui_search$1container$1column$1text_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_cssClass_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_5);
                      return;
                    }
                    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_cssClass_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_5);
                    out.write("\n\n\t\t\t");
                    if (_jspx_meth_liferay$1ui_search$1container$1column$1jsp_1((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_3, _jspx_page_context))
                      return;
                    out.write("\n\t\t");
                  }
                  if (_jspx_th_c_if_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_3);
                    return;
                  }
                  _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_3);
                  out.write('\n');
                  out.write('	');
                }
                if (_jspx_th_c_otherwise_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_1);
                  return;
                }
                _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_1);
                out.write('\n');
              }
              if (_jspx_th_c_choose_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_0);
                return;
              }
              _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_0);
              out.write("\n\t\t");
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
          out.write("\n\n\t\t");
          //  liferay-ui:search-iterator
          com.liferay.taglib.ui.SearchIteratorTag _jspx_th_liferay$1ui_search$1iterator_0 = (com.liferay.taglib.ui.SearchIteratorTag) _jspx_tagPool_liferay$1ui_search$1iterator_markupView_displayStyle_nobody.get(com.liferay.taglib.ui.SearchIteratorTag.class);
          _jspx_th_liferay$1ui_search$1iterator_0.setPageContext(_jspx_page_context);
          _jspx_th_liferay$1ui_search$1iterator_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container_0);
          _jspx_th_liferay$1ui_search$1iterator_0.setDisplayStyle( selectUsersDisplayContext.getDisplayStyle() );
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
          _jspx_tagPool_liferay$1ui_search$1container_searchContainer_id.reuse(_jspx_th_liferay$1ui_search$1container_0);
          return;
        }
        _jspx_tagPool_liferay$1ui_search$1container_searchContainer_id.reuse(_jspx_th_liferay$1ui_search$1container_0);
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
          out.write("users');\n\n\tsearchContainer.on(\n\t\t'rowToggled',\n\t\tfunction(event) {\n\t\t\tvar result = {};\n\n\t\t\tvar data = event.elements.allSelectedElements.getDOMNodes();\n\n\t\t\tif (data.length) {\n\t\t\t\tresult = {\n\t\t\t\t\tdata: data\n\t\t\t\t};\n\t\t\t}\n\n\t\t\tLiferay.Util.getOpener().Liferay.fire(\n\t\t\t\t'");
          out.print( HtmlUtil.escapeJS(selectUsersDisplayContext.getEventName()) );
          out.write("',\n\t\t\t\tresult);\n\t\t}\n\t);\n");
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

  private boolean _jspx_meth_liferay$1ui_membership$1policy$1error_0(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_form_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:membership-policy-error
    com.liferay.taglib.ui.MembershipPolicyErrorTag _jspx_th_liferay$1ui_membership$1policy$1error_0 = (com.liferay.taglib.ui.MembershipPolicyErrorTag) _jspx_tagPool_liferay$1ui_membership$1policy$1error_nobody.get(com.liferay.taglib.ui.MembershipPolicyErrorTag.class);
    _jspx_th_liferay$1ui_membership$1policy$1error_0.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_membership$1policy$1error_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
    int _jspx_eval_liferay$1ui_membership$1policy$1error_0 = _jspx_th_liferay$1ui_membership$1policy$1error_0.doStartTag();
    if (_jspx_th_liferay$1ui_membership$1policy$1error_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_membership$1policy$1error_nobody.reuse(_jspx_th_liferay$1ui_membership$1policy$1error_0);
      return true;
    }
    _jspx_tagPool_liferay$1ui_membership$1policy$1error_nobody.reuse(_jspx_th_liferay$1ui_membership$1policy$1error_0);
    return false;
  }

  private boolean _jspx_meth_c_otherwise_0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_choose_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:otherwise
    com.liferay.taglib.core.OtherwiseTag _jspx_th_c_otherwise_0 = (com.liferay.taglib.core.OtherwiseTag) _jspx_tagPool_c_otherwise.get(com.liferay.taglib.core.OtherwiseTag.class);
    _jspx_th_c_otherwise_0.setPageContext(_jspx_page_context);
    _jspx_th_c_otherwise_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_1);
    int _jspx_eval_c_otherwise_0 = _jspx_th_c_otherwise_0.doStartTag();
    if (_jspx_eval_c_otherwise_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      out.write("\n\t\t\t\t\t\t\t\t\t&nbsp;\n\t\t\t\t\t\t\t\t");
    }
    if (_jspx_th_c_otherwise_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_0);
      return true;
    }
    _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_0);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_search$1container$1column$1jsp_0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:search-container-column-jsp
    com.liferay.taglib.ui.SearchContainerColumnJSPTag _jspx_th_liferay$1ui_search$1container$1column$1jsp_0 = (com.liferay.taglib.ui.SearchContainerColumnJSPTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1jsp_path_nobody.get(com.liferay.taglib.ui.SearchContainerColumnJSPTag.class);
    _jspx_th_liferay$1ui_search$1container$1column$1jsp_0.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_search$1container$1column$1jsp_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_2);
    _jspx_th_liferay$1ui_search$1container$1column$1jsp_0.setPath("/user_action.jsp");
    int _jspx_eval_liferay$1ui_search$1container$1column$1jsp_0 = _jspx_th_liferay$1ui_search$1container$1column$1jsp_0.doStartTag();
    if (_jspx_th_liferay$1ui_search$1container$1column$1jsp_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_search$1container$1column$1jsp_path_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1jsp_0);
      return true;
    }
    _jspx_tagPool_liferay$1ui_search$1container$1column$1jsp_path_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1jsp_0);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_search$1container$1column$1jsp_1(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:search-container-column-jsp
    com.liferay.taglib.ui.SearchContainerColumnJSPTag _jspx_th_liferay$1ui_search$1container$1column$1jsp_1 = (com.liferay.taglib.ui.SearchContainerColumnJSPTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1jsp_path_nobody.get(com.liferay.taglib.ui.SearchContainerColumnJSPTag.class);
    _jspx_th_liferay$1ui_search$1container$1column$1jsp_1.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_search$1container$1column$1jsp_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_3);
    _jspx_th_liferay$1ui_search$1container$1column$1jsp_1.setPath("/user_action.jsp");
    int _jspx_eval_liferay$1ui_search$1container$1column$1jsp_1 = _jspx_th_liferay$1ui_search$1container$1column$1jsp_1.doStartTag();
    if (_jspx_th_liferay$1ui_search$1container$1column$1jsp_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_search$1container$1column$1jsp_path_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1jsp_1);
      return true;
    }
    _jspx_tagPool_liferay$1ui_search$1container$1column$1jsp_path_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1jsp_1);
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
