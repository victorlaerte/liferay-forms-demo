package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.liferay.admin.kernel.util.PortalMyAccountApplicationType;
import com.liferay.announcements.kernel.model.AnnouncementsDelivery;
import com.liferay.announcements.kernel.model.AnnouncementsEntryConstants;
import com.liferay.announcements.kernel.service.AnnouncementsDeliveryLocalServiceUtil;
import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.bean.BeanParamUtil;
import com.liferay.portal.kernel.bean.BeanPropertiesUtil;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.AddressCityException;
import com.liferay.portal.kernel.exception.AddressStreetException;
import com.liferay.portal.kernel.exception.AddressZipException;
import com.liferay.portal.kernel.exception.CompanyMaxUsersException;
import com.liferay.portal.kernel.exception.ContactBirthdayException;
import com.liferay.portal.kernel.exception.DuplicateOpenIdException;
import com.liferay.portal.kernel.exception.DuplicateOrganizationException;
import com.liferay.portal.kernel.exception.EmailAddressException;
import com.liferay.portal.kernel.exception.GroupFriendlyURLException;
import com.liferay.portal.kernel.exception.NoSuchCountryException;
import com.liferay.portal.kernel.exception.NoSuchListTypeException;
import com.liferay.portal.kernel.exception.NoSuchOrganizationException;
import com.liferay.portal.kernel.exception.NoSuchRegionException;
import com.liferay.portal.kernel.exception.NoSuchRoleException;
import com.liferay.portal.kernel.exception.NoSuchUserException;
import com.liferay.portal.kernel.exception.OrganizationNameException;
import com.liferay.portal.kernel.exception.OrganizationParentException;
import com.liferay.portal.kernel.exception.PhoneNumberException;
import com.liferay.portal.kernel.exception.PhoneNumberExtensionException;
import com.liferay.portal.kernel.exception.RequiredOrganizationException;
import com.liferay.portal.kernel.exception.RequiredUserException;
import com.liferay.portal.kernel.exception.UserEmailAddressException;
import com.liferay.portal.kernel.exception.UserFieldException;
import com.liferay.portal.kernel.exception.UserIdException;
import com.liferay.portal.kernel.exception.UserLockoutException;
import com.liferay.portal.kernel.exception.UserPasswordException;
import com.liferay.portal.kernel.exception.UserScreenNameException;
import com.liferay.portal.kernel.exception.UserSmsException;
import com.liferay.portal.kernel.exception.WebsiteURLException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.language.UnicodeLanguageUtil;
import com.liferay.portal.kernel.model.Address;
import com.liferay.portal.kernel.model.Contact;
import com.liferay.portal.kernel.model.EmailAddress;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutConstants;
import com.liferay.portal.kernel.model.LayoutSet;
import com.liferay.portal.kernel.model.LayoutSetPrototype;
import com.liferay.portal.kernel.model.ListTypeConstants;
import com.liferay.portal.kernel.model.ModelHintsConstants;
import com.liferay.portal.kernel.model.OrgLabor;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.OrganizationConstants;
import com.liferay.portal.kernel.model.PasswordPolicy;
import com.liferay.portal.kernel.model.Phone;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.RoleConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.UserConstants;
import com.liferay.portal.kernel.model.UserGroup;
import com.liferay.portal.kernel.model.UserGroupGroupRole;
import com.liferay.portal.kernel.model.UserGroupRole;
import com.liferay.portal.kernel.model.Website;
import com.liferay.portal.kernel.module.configuration.ConfigurationProviderUtil;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.portlet.PortalPreferences;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletProvider;
import com.liferay.portal.kernel.portlet.PortletProviderUtil;
import com.liferay.portal.kernel.security.auth.ScreenNameValidator;
import com.liferay.portal.kernel.security.ldap.LDAPSettingsUtil;
import com.liferay.portal.kernel.security.membershippolicy.OrganizationMembershipPolicyUtil;
import com.liferay.portal.kernel.security.membershippolicy.RoleMembershipPolicyUtil;
import com.liferay.portal.kernel.security.membershippolicy.SiteMembershipPolicyUtil;
import com.liferay.portal.kernel.security.membershippolicy.UserGroupMembershipPolicyUtil;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.AddressServiceUtil;
import com.liferay.portal.kernel.service.EmailAddressServiceUtil;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;
import com.liferay.portal.kernel.service.LayoutSetLocalServiceUtil;
import com.liferay.portal.kernel.service.LayoutSetPrototypeLocalServiceUtil;
import com.liferay.portal.kernel.service.LayoutSetPrototypeServiceUtil;
import com.liferay.portal.kernel.service.OrgLaborServiceUtil;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.liferay.portal.kernel.service.OrganizationServiceUtil;
import com.liferay.portal.kernel.service.PhoneServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserGroupLocalServiceUtil;
import com.liferay.portal.kernel.service.UserGroupRoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.service.WebsiteServiceUtil;
import com.liferay.portal.kernel.service.permission.GroupPermissionUtil;
import com.liferay.portal.kernel.service.permission.OrganizationPermissionUtil;
import com.liferay.portal.kernel.service.permission.PortalPermissionUtil;
import com.liferay.portal.kernel.service.permission.UserPermissionUtil;
import com.liferay.portal.kernel.servlet.taglib.ui.FormNavigatorConstants;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.CalendarUtil;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.TextFormatter;
import com.liferay.portal.kernel.util.UnicodeFormatter;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.webserver.WebServerServletTokenUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.impl.AddressImpl;
import com.liferay.portal.model.impl.EmailAddressImpl;
import com.liferay.portal.model.impl.OrgLaborImpl;
import com.liferay.portal.model.impl.PhoneImpl;
import com.liferay.portal.model.impl.WebsiteImpl;
import com.liferay.portal.security.auth.ScreenNameValidatorFactory;
import com.liferay.portal.util.PrefsPropsUtil;
import com.liferay.portal.util.PropsValues;
import com.liferay.portlet.announcements.model.impl.AnnouncementsDeliveryImpl;
import com.liferay.portlet.usersadmin.search.OrganizationSearch;
import com.liferay.roles.admin.kernel.util.RolesAdminUtil;
import com.liferay.taglib.search.ResultRow;
import com.liferay.taglib.search.SearchEntry;
import com.liferay.users.admin.configuration.UserFileUploadsConfiguration;
import com.liferay.users.admin.constants.UserFormConstants;
import com.liferay.users.admin.constants.UsersAdminPortletKeys;
import com.liferay.users.admin.kernel.util.UsersAdmin;
import com.liferay.users.admin.kernel.util.UsersAdminUtil;
import com.liferay.users.admin.user.action.contributor.UserActionContributor;
import com.liferay.users.admin.web.internal.CustomFieldsUtil;
import com.liferay.users.admin.web.internal.constants.UsersAdminWebKeys;
import com.liferay.users.admin.web.internal.display.context.InitDisplayContext;
import com.liferay.users.admin.web.internal.display.context.SelectOrganizationManagementToolbarDisplayContext;
import com.liferay.users.admin.web.internal.display.context.SelectOrganizationUsersManagementToolbarDisplayContext;
import com.liferay.users.admin.web.internal.display.context.SelectUserGroupManagementToolbarDisplayContext;
import com.liferay.users.admin.web.internal.display.context.UserActionDisplayContext;
import com.liferay.users.admin.web.internal.display.context.UserDisplayContext;
import com.liferay.users.admin.web.internal.display.context.ViewOrganizationsManagementToolbarDisplayContext;
import com.liferay.users.admin.web.internal.display.context.ViewTreeManagementToolbarDisplayContext;
import com.liferay.users.admin.web.internal.display.context.ViewUsersManagementToolbarDisplayContext;
import com.liferay.users.admin.web.internal.search.OrganizationResultRowSplitter;
import java.text.Format;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import javax.portlet.PortletURL;
import javax.portlet.WindowState;

public final class view_005fflat_005forganizations_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(3);
    _jspx_dependants.add("/init.jsp");
    _jspx_dependants.add("/init-ext.jsp");
    _jspx_dependants.add("/organization/search_columns.jspf");
  }

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_href_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_param_value_name_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_a_href;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_if_test;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_input_value_type_name_disabled_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container$1row_modelVar_keyProperty_escapedModel_className;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1portlet_renderURL_varImpl;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_input_type_name_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_breadcrumb_showPortletBreadcrumb_showLayout_showGuestGroup_showCurrentGroup_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_choose;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1frontend_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_form_onSubmit_name_method_cssClass_action;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_orderable_name_href_cssClass_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container$1column$1icon_toggleRowChecker_icon_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_when_test;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1portlet_renderURLParams_varImpl_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_message_key_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_href_cssClass_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_input_value_type_name_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container_var_searchContainer_id;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container$1column$1text_colspan;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_otherwise;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container$1column$1jsp_path_cssClass_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1theme_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1frontend_vertical$1card_url_title_subtitle_rowChecker_resultRow_imageUrl_cssClass_actionJspServletContext_actionJsp_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container$1column$1text;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_clay_management$1toolbar_viewTypeItems_sortingURL_sortingOrder_showSearch_showCreationMenu_selectable_searchFormName_searchContainerId_searchActionURL_itemsTotal_filterDropdownItems_creationMenu_clearResultsURL_actionDropdownItems_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1iterator_markupView_displayStyle_nobody;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_href_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_param_value_name_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_a_href = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_if_test = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_input_value_type_name_disabled_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container$1row_modelVar_keyProperty_escapedModel_className = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1portlet_renderURL_varImpl = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_input_type_name_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_breadcrumb_showPortletBreadcrumb_showLayout_showGuestGroup_showCurrentGroup_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_choose = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1frontend_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_form_onSubmit_name_method_cssClass_action = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_orderable_name_href_cssClass_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container$1column$1icon_toggleRowChecker_icon_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_when_test = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1portlet_renderURLParams_varImpl_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_message_key_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_href_cssClass_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_input_value_type_name_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container_var_searchContainer_id = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_colspan = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_otherwise = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container$1column$1jsp_path_cssClass_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1theme_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1frontend_vertical$1card_url_title_subtitle_rowChecker_resultRow_imageUrl_cssClass_actionJspServletContext_actionJsp_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_clay_management$1toolbar_viewTypeItems_sortingURL_sortingOrder_showSearch_showCreationMenu_selectable_searchFormName_searchContainerId_searchActionURL_itemsTotal_filterDropdownItems_creationMenu_clearResultsURL_actionDropdownItems_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1iterator_markupView_displayStyle_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_href_nobody.release();
    _jspx_tagPool_portlet_param_value_name_nobody.release();
    _jspx_tagPool_aui_a_href.release();
    _jspx_tagPool_c_if_test.release();
    _jspx_tagPool_aui_input_value_type_name_disabled_nobody.release();
    _jspx_tagPool_liferay$1ui_search$1container$1row_modelVar_keyProperty_escapedModel_className.release();
    _jspx_tagPool_liferay$1portlet_renderURL_varImpl.release();
    _jspx_tagPool_aui_input_type_name_nobody.release();
    _jspx_tagPool_liferay$1ui_breadcrumb_showPortletBreadcrumb_showLayout_showGuestGroup_showCurrentGroup_nobody.release();
    _jspx_tagPool_c_choose.release();
    _jspx_tagPool_liferay$1frontend_defineObjects_nobody.release();
    _jspx_tagPool_aui_form_onSubmit_name_method_cssClass_action.release();
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_orderable_name_href_cssClass_nobody.release();
    _jspx_tagPool_liferay$1ui_search$1container$1column$1icon_toggleRowChecker_icon_nobody.release();
    _jspx_tagPool_c_when_test.release();
    _jspx_tagPool_liferay$1portlet_renderURLParams_varImpl_nobody.release();
    _jspx_tagPool_liferay$1ui_message_key_nobody.release();
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_href_cssClass_nobody.release();
    _jspx_tagPool_aui_input_value_type_name_nobody.release();
    _jspx_tagPool_liferay$1ui_search$1container_var_searchContainer_id.release();
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_colspan.release();
    _jspx_tagPool_c_otherwise.release();
    _jspx_tagPool_portlet_defineObjects_nobody.release();
    _jspx_tagPool_liferay$1ui_search$1container$1column$1jsp_path_cssClass_nobody.release();
    _jspx_tagPool_liferay$1theme_defineObjects_nobody.release();
    _jspx_tagPool_liferay$1frontend_vertical$1card_url_title_subtitle_rowChecker_resultRow_imageUrl_cssClass_actionJspServletContext_actionJsp_nobody.release();
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text.release();
    _jspx_tagPool_clay_management$1toolbar_viewTypeItems_sortingURL_sortingOrder_showSearch_showCreationMenu_selectable_searchFormName_searchContainerId_searchActionURL_itemsTotal_filterDropdownItems_creationMenu_clearResultsURL_actionDropdownItems_nobody.release();
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
      out.write("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
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

PortalPreferences portalPreferences = PortletPreferencesFactoryUtil.getPortalPreferences(liferayPortletRequest);

String myAccountPortletId = PortletProviderUtil.getPortletId(PortalMyAccountApplicationType.MyAccount.CLASS_NAME, PortletProvider.Action.VIEW);

InitDisplayContext initDisplayContext = new InitDisplayContext(request, portletName);

boolean filterManageableOrganizations = initDisplayContext.isFilterManageableOrganizations();
boolean filterManageableUserGroups = initDisplayContext.isFilterManageableUserGroups();

UserDisplayContext userDisplayContext = new UserDisplayContext(request, initDisplayContext);

      out.write('\n');
      out.write('\n');
      out.write('\n');
      out.write('\n');

String toolbarItem = ParamUtil.getString(request, "toolbarItem", "view-all-organizations");

String displayStyle = ParamUtil.getString(request, "displayStyle");

if (Validator.isNull(displayStyle)) {
	displayStyle = portalPreferences.getValue(UsersAdminPortletKeys.USERS_ADMIN, "display-style", "list");
}
else {
	portalPreferences.setValue(UsersAdminPortletKeys.USERS_ADMIN, "display-style", displayStyle);

	request.setAttribute(WebKeys.SINGLE_PAGE_APPLICATION_CLEAR_CACHE, Boolean.TRUE);
}

String usersListView = (String)request.getAttribute("view.jsp-usersListView");

PortletURL portletURL = (PortletURL)request.getAttribute("view.jsp-portletURL");

portletURL.setParameter("displayStyle", displayStyle);

String keywords = ParamUtil.getString(request, "keywords");

LinkedHashMap<String, Object> organizationParams = new LinkedHashMap<String, Object>();

boolean showList = true;

if (filterManageableOrganizations) {
	List<Organization> userOrganizations = user.getOrganizations(true);

	if (userOrganizations.isEmpty()) {
		showList = false;
	}
	else {
		organizationParams.put("organizationsTree", userOrganizations);
	}
}

      out.write('\n');
      out.write('\n');
      //  c:choose
      com.liferay.taglib.core.ChooseTag _jspx_th_c_choose_0 = (com.liferay.taglib.core.ChooseTag) _jspx_tagPool_c_choose.get(com.liferay.taglib.core.ChooseTag.class);
      _jspx_th_c_choose_0.setPageContext(_jspx_page_context);
      _jspx_th_c_choose_0.setParent(null);
      int _jspx_eval_c_choose_0 = _jspx_th_c_choose_0.doStartTag();
      if (_jspx_eval_c_choose_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        out.write('\n');
        out.write('	');
        //  c:when
        com.liferay.taglib.core.WhenTag _jspx_th_c_when_0 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
        _jspx_th_c_when_0.setPageContext(_jspx_page_context);
        _jspx_th_c_when_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_0);
        _jspx_th_c_when_0.setTest( showList );
        int _jspx_eval_c_when_0 = _jspx_th_c_when_0.doStartTag();
        if (_jspx_eval_c_when_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          out.write("\n\n\t\t");

		ViewOrganizationsManagementToolbarDisplayContext viewOrganizationsManagementToolbarDisplayContext = new ViewOrganizationsManagementToolbarDisplayContext(request, renderRequest, renderResponse, displayStyle);

		SearchContainer searchContainer = viewOrganizationsManagementToolbarDisplayContext.getSearchContainer(organizationParams, filterManageableOrganizations);
		
          out.write("\n\n\t\t");
          //  clay:management-toolbar
          com.liferay.frontend.taglib.clay.servlet.taglib.soy.ManagementToolbarTag _jspx_th_clay_management$1toolbar_0 = (com.liferay.frontend.taglib.clay.servlet.taglib.soy.ManagementToolbarTag) _jspx_tagPool_clay_management$1toolbar_viewTypeItems_sortingURL_sortingOrder_showSearch_showCreationMenu_selectable_searchFormName_searchContainerId_searchActionURL_itemsTotal_filterDropdownItems_creationMenu_clearResultsURL_actionDropdownItems_nobody.get(com.liferay.frontend.taglib.clay.servlet.taglib.soy.ManagementToolbarTag.class);
          _jspx_th_clay_management$1toolbar_0.setPageContext(_jspx_page_context);
          _jspx_th_clay_management$1toolbar_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_0);
          _jspx_th_clay_management$1toolbar_0.setActionDropdownItems( viewOrganizationsManagementToolbarDisplayContext.getActionDropdownItems() );
          _jspx_th_clay_management$1toolbar_0.setClearResultsURL( viewOrganizationsManagementToolbarDisplayContext.getClearResultsURL() );
          _jspx_th_clay_management$1toolbar_0.setCreationMenu( viewOrganizationsManagementToolbarDisplayContext.getCreationMenu() );
          _jspx_th_clay_management$1toolbar_0.setFilterDropdownItems( viewOrganizationsManagementToolbarDisplayContext.getFilterDropdownItems() );
          _jspx_th_clay_management$1toolbar_0.setItemsTotal( searchContainer.getTotal() );
          _jspx_th_clay_management$1toolbar_0.setSearchActionURL( viewOrganizationsManagementToolbarDisplayContext.getSearchActionURL() );
          _jspx_th_clay_management$1toolbar_0.setSearchContainerId("organizations");
          _jspx_th_clay_management$1toolbar_0.setSearchFormName("searchFm");
          _jspx_th_clay_management$1toolbar_0.setSelectable( true );
          _jspx_th_clay_management$1toolbar_0.setShowCreationMenu( viewOrganizationsManagementToolbarDisplayContext.showCreationMenu() );
          _jspx_th_clay_management$1toolbar_0.setShowSearch( true );
          _jspx_th_clay_management$1toolbar_0.setSortingOrder( searchContainer.getOrderByType() );
          _jspx_th_clay_management$1toolbar_0.setSortingURL( viewOrganizationsManagementToolbarDisplayContext.getSortingURL() );
          _jspx_th_clay_management$1toolbar_0.setViewTypeItems( viewOrganizationsManagementToolbarDisplayContext.getViewTypeItems() );
          int _jspx_eval_clay_management$1toolbar_0 = _jspx_th_clay_management$1toolbar_0.doStartTag();
          if (_jspx_th_clay_management$1toolbar_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_clay_management$1toolbar_viewTypeItems_sortingURL_sortingOrder_showSearch_showCreationMenu_selectable_searchFormName_searchContainerId_searchActionURL_itemsTotal_filterDropdownItems_creationMenu_clearResultsURL_actionDropdownItems_nobody.reuse(_jspx_th_clay_management$1toolbar_0);
            return;
          }
          _jspx_tagPool_clay_management$1toolbar_viewTypeItems_sortingURL_sortingOrder_showSearch_showCreationMenu_selectable_searchFormName_searchContainerId_searchActionURL_itemsTotal_filterDropdownItems_creationMenu_clearResultsURL_actionDropdownItems_nobody.reuse(_jspx_th_clay_management$1toolbar_0);
          out.write("\n\n\t\t");
          //  aui:form
          com.liferay.taglib.aui.FormTag _jspx_th_aui_form_0 = (com.liferay.taglib.aui.FormTag) _jspx_tagPool_aui_form_onSubmit_name_method_cssClass_action.get(com.liferay.taglib.aui.FormTag.class);
          _jspx_th_aui_form_0.setPageContext(_jspx_page_context);
          _jspx_th_aui_form_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_0);
          _jspx_th_aui_form_0.setAction( portletURL.toString() );
          _jspx_th_aui_form_0.setCssClass("container-fluid-1280");
          _jspx_th_aui_form_0.setMethod("post");
          _jspx_th_aui_form_0.setName("fm");
          _jspx_th_aui_form_0.setOnSubmit( "event.preventDefault(); " + renderResponse.getNamespace() + "search();" );
          int _jspx_eval_aui_form_0 = _jspx_th_aui_form_0.doStartTag();
          if (_jspx_eval_aui_form_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t\t");
            if (_jspx_meth_liferay$1portlet_renderURLParams_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_form_0, _jspx_page_context))
              return;
            out.write("\n\t\t\t");
            //  aui:input
            com.liferay.taglib.aui.InputTag _jspx_th_aui_input_0 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
            _jspx_th_aui_input_0.setPageContext(_jspx_page_context);
            _jspx_th_aui_input_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
            _jspx_th_aui_input_0.setName( Constants.CMD );
            _jspx_th_aui_input_0.setType("hidden");
            int _jspx_eval_aui_input_0 = _jspx_th_aui_input_0.doStartTag();
            if (_jspx_th_aui_input_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_aui_input_type_name_nobody.reuse(_jspx_th_aui_input_0);
              return;
            }
            _jspx_tagPool_aui_input_type_name_nobody.reuse(_jspx_th_aui_input_0);
            out.write("\n\t\t\t");
            //  aui:input
            com.liferay.taglib.aui.InputTag _jspx_th_aui_input_1 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
            _jspx_th_aui_input_1.setPageContext(_jspx_page_context);
            _jspx_th_aui_input_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
            _jspx_th_aui_input_1.setName("toolbarItem");
            _jspx_th_aui_input_1.setType("hidden");
            _jspx_th_aui_input_1.setValue( toolbarItem );
            int _jspx_eval_aui_input_1 = _jspx_th_aui_input_1.doStartTag();
            if (_jspx_th_aui_input_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_1);
              return;
            }
            _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_1);
            out.write("\n\t\t\t");
            //  aui:input
            com.liferay.taglib.aui.InputTag _jspx_th_aui_input_2 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
            _jspx_th_aui_input_2.setPageContext(_jspx_page_context);
            _jspx_th_aui_input_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
            _jspx_th_aui_input_2.setName("redirect");
            _jspx_th_aui_input_2.setType("hidden");
            _jspx_th_aui_input_2.setValue( portletURL.toString() );
            int _jspx_eval_aui_input_2 = _jspx_th_aui_input_2.doStartTag();
            if (_jspx_th_aui_input_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_2);
              return;
            }
            _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_2);
            out.write("\n\n\t\t\t");
            //  liferay-ui:search-container
            com.liferay.taglib.ui.SearchContainerTag _jspx_th_liferay$1ui_search$1container_0 = (com.liferay.taglib.ui.SearchContainerTag) _jspx_tagPool_liferay$1ui_search$1container_var_searchContainer_id.get(com.liferay.taglib.ui.SearchContainerTag.class);
            _jspx_th_liferay$1ui_search$1container_0.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1ui_search$1container_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
            _jspx_th_liferay$1ui_search$1container_0.setId("organizations");
            _jspx_th_liferay$1ui_search$1container_0.setSearchContainer( searchContainer );
            _jspx_th_liferay$1ui_search$1container_0.setVar("organizationSearchContainer");
            int _jspx_eval_liferay$1ui_search$1container_0 = _jspx_th_liferay$1ui_search$1container_0.doStartTag();
            if (_jspx_eval_liferay$1ui_search$1container_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              java.lang.Integer total = null;
              com.liferay.portal.kernel.dao.search.SearchContainer organizationSearchContainer = null;
              total = (java.lang.Integer) _jspx_page_context.findAttribute("total");
              organizationSearchContainer = (com.liferay.portal.kernel.dao.search.SearchContainer) _jspx_page_context.findAttribute("organizationSearchContainer");
              out.write("\n\t\t\t\t");
              //  aui:input
              com.liferay.taglib.aui.InputTag _jspx_th_aui_input_3 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_disabled_nobody.get(com.liferay.taglib.aui.InputTag.class);
              _jspx_th_aui_input_3.setPageContext(_jspx_page_context);
              _jspx_th_aui_input_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container_0);
              _jspx_th_aui_input_3.setDisabled( true );
              _jspx_th_aui_input_3.setName("organizationsRedirect");
              _jspx_th_aui_input_3.setType("hidden");
              _jspx_th_aui_input_3.setValue( portletURL.toString() );
              int _jspx_eval_aui_input_3 = _jspx_th_aui_input_3.doStartTag();
              if (_jspx_th_aui_input_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_aui_input_value_type_name_disabled_nobody.reuse(_jspx_th_aui_input_3);
                return;
              }
              _jspx_tagPool_aui_input_value_type_name_disabled_nobody.reuse(_jspx_th_aui_input_3);
              out.write("\n\t\t\t\t");
              if (_jspx_meth_aui_input_4((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1ui_search$1container_0, _jspx_page_context))
                return;
              out.write("\n\n\t\t\t\t");
              //  c:if
              com.liferay.taglib.core.IfTag _jspx_th_c_if_0 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
              _jspx_th_c_if_0.setPageContext(_jspx_page_context);
              _jspx_th_c_if_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container_0);
              _jspx_th_c_if_0.setTest( usersListView.equals(UserConstants.LIST_VIEW_FLAT_ORGANIZATIONS) );
              int _jspx_eval_c_if_0 = _jspx_th_c_if_0.doStartTag();
              if (_jspx_eval_c_if_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\t\t\t\t\t<div id=\"breadcrumb\">\n\t\t\t\t\t\t");
                //  liferay-ui:breadcrumb
                com.liferay.taglib.ui.BreadcrumbTag _jspx_th_liferay$1ui_breadcrumb_0 = (com.liferay.taglib.ui.BreadcrumbTag) _jspx_tagPool_liferay$1ui_breadcrumb_showPortletBreadcrumb_showLayout_showGuestGroup_showCurrentGroup_nobody.get(com.liferay.taglib.ui.BreadcrumbTag.class);
                _jspx_th_liferay$1ui_breadcrumb_0.setPageContext(_jspx_page_context);
                _jspx_th_liferay$1ui_breadcrumb_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_0);
                _jspx_th_liferay$1ui_breadcrumb_0.setShowCurrentGroup( false );
                _jspx_th_liferay$1ui_breadcrumb_0.setShowGuestGroup( false );
                _jspx_th_liferay$1ui_breadcrumb_0.setShowLayout( false );
                _jspx_th_liferay$1ui_breadcrumb_0.setShowPortletBreadcrumb( true );
                int _jspx_eval_liferay$1ui_breadcrumb_0 = _jspx_th_liferay$1ui_breadcrumb_0.doStartTag();
                if (_jspx_th_liferay$1ui_breadcrumb_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_liferay$1ui_breadcrumb_showPortletBreadcrumb_showLayout_showGuestGroup_showCurrentGroup_nobody.reuse(_jspx_th_liferay$1ui_breadcrumb_0);
                  return;
                }
                _jspx_tagPool_liferay$1ui_breadcrumb_showPortletBreadcrumb_showLayout_showGuestGroup_showCurrentGroup_nobody.reuse(_jspx_th_liferay$1ui_breadcrumb_0);
                out.write("\n\t\t\t\t\t</div>\n\t\t\t\t");
              }
              if (_jspx_th_c_if_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
                return;
              }
              _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
              out.write("\n\n\t\t\t\t");
              //  liferay-ui:search-container-row
              com.liferay.taglib.ui.SearchContainerRowTag _jspx_th_liferay$1ui_search$1container$1row_0 = (com.liferay.taglib.ui.SearchContainerRowTag) _jspx_tagPool_liferay$1ui_search$1container$1row_modelVar_keyProperty_escapedModel_className.get(com.liferay.taglib.ui.SearchContainerRowTag.class);
              _jspx_th_liferay$1ui_search$1container$1row_0.setPageContext(_jspx_page_context);
              _jspx_th_liferay$1ui_search$1container$1row_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container_0);
              _jspx_th_liferay$1ui_search$1container$1row_0.setClassName("com.liferay.portal.kernel.model.Organization");
              _jspx_th_liferay$1ui_search$1container$1row_0.setEscapedModel( true );
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
                  out.write("\n\t\t\t\t\t");
                  //  liferay-portlet:renderURL
                  com.liferay.taglib.portlet.RenderURLTag _jspx_th_liferay$1portlet_renderURL_0 = (com.liferay.taglib.portlet.RenderURLTag) _jspx_tagPool_liferay$1portlet_renderURL_varImpl.get(com.liferay.taglib.portlet.RenderURLTag.class);
                  _jspx_th_liferay$1portlet_renderURL_0.setPageContext(_jspx_page_context);
                  _jspx_th_liferay$1portlet_renderURL_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1row_0);
                  _jspx_th_liferay$1portlet_renderURL_0.setVarImpl("rowURL");
                  int _jspx_eval_liferay$1portlet_renderURL_0 = _jspx_th_liferay$1portlet_renderURL_0.doStartTag();
                  if (_jspx_eval_liferay$1portlet_renderURL_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    out.write("\n\t\t\t\t\t\t");
                    if (_jspx_meth_portlet_param_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1portlet_renderURL_0, _jspx_page_context))
                      return;
                    out.write("\n\t\t\t\t\t\t");
                    if (_jspx_meth_portlet_param_1((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1portlet_renderURL_0, _jspx_page_context))
                      return;
                    out.write("\n\t\t\t\t\t\t");
                    //  portlet:param
                    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_2 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                    _jspx_th_portlet_param_2.setPageContext(_jspx_page_context);
                    _jspx_th_portlet_param_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_0);
                    _jspx_th_portlet_param_2.setName("redirect");
                    _jspx_th_portlet_param_2.setValue( organizationSearchContainer.getIteratorURL().toString() );
                    int _jspx_eval_portlet_param_2 = _jspx_th_portlet_param_2.doStartTag();
                    if (_jspx_th_portlet_param_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_2);
                      return;
                    }
                    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_2);
                    out.write("\n\t\t\t\t\t\t");
                    //  portlet:param
                    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_3 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                    _jspx_th_portlet_param_3.setPageContext(_jspx_page_context);
                    _jspx_th_portlet_param_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_0);
                    _jspx_th_portlet_param_3.setName("organizationId");
                    _jspx_th_portlet_param_3.setValue( String.valueOf(organization.getOrganizationId()) );
                    int _jspx_eval_portlet_param_3 = _jspx_th_portlet_param_3.doStartTag();
                    if (_jspx_th_portlet_param_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_3);
                      return;
                    }
                    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_3);
                    out.write("\n\t\t\t\t\t\t");
                    //  portlet:param
                    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_4 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                    _jspx_th_portlet_param_4.setPageContext(_jspx_page_context);
                    _jspx_th_portlet_param_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_0);
                    _jspx_th_portlet_param_4.setName("usersListView");
                    _jspx_th_portlet_param_4.setValue( UserConstants.LIST_VIEW_TREE );
                    int _jspx_eval_portlet_param_4 = _jspx_th_portlet_param_4.doStartTag();
                    if (_jspx_th_portlet_param_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_4);
                      return;
                    }
                    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_4);
                    out.write("\n\t\t\t\t\t");
                  }
                  if (_jspx_th_liferay$1portlet_renderURL_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_liferay$1portlet_renderURL_varImpl.reuse(_jspx_th_liferay$1portlet_renderURL_0);
                    return;
                  }
                  _jspx_tagPool_liferay$1portlet_renderURL_varImpl.reuse(_jspx_th_liferay$1portlet_renderURL_0);
                  com.liferay.portal.kernel.portlet.LiferayPortletURL rowURL = null;
                  rowURL = (com.liferay.portal.kernel.portlet.LiferayPortletURL) _jspx_page_context.findAttribute("rowURL");
                  out.write("\n\n\t\t\t\t\t");

					if (!OrganizationPermissionUtil.contains(permissionChecker, organization, ActionKeys.VIEW)) {
						rowURL = null;
					}
					
                  out.write("\n\n\t\t\t\t\t");
                  out.write('\n');
                  out.write('\n');
                  //  c:choose
                  com.liferay.taglib.core.ChooseTag _jspx_th_c_choose_1 = (com.liferay.taglib.core.ChooseTag) _jspx_tagPool_c_choose.get(com.liferay.taglib.core.ChooseTag.class);
                  _jspx_th_c_choose_1.setPageContext(_jspx_page_context);
                  _jspx_th_c_choose_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1row_0);
                  int _jspx_eval_c_choose_1 = _jspx_th_c_choose_1.doStartTag();
                  if (_jspx_eval_c_choose_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    out.write('\n');
                    out.write('	');
                    //  c:when
                    com.liferay.taglib.core.WhenTag _jspx_th_c_when_1 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
                    _jspx_th_c_when_1.setPageContext(_jspx_page_context);
                    _jspx_th_c_when_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_1);
                    _jspx_th_c_when_1.setTest( displayStyle.equals("descriptive") );
                    int _jspx_eval_c_when_1 = _jspx_th_c_when_1.doStartTag();
                    if (_jspx_eval_c_when_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      out.write("\n\t\t");
                      //  liferay-ui:search-container-column-icon
                      com.liferay.taglib.ui.SearchContainerColumnIconTag _jspx_th_liferay$1ui_search$1container$1column$1icon_0 = (com.liferay.taglib.ui.SearchContainerColumnIconTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1icon_toggleRowChecker_icon_nobody.get(com.liferay.taglib.ui.SearchContainerColumnIconTag.class);
                      _jspx_th_liferay$1ui_search$1container$1column$1icon_0.setPageContext(_jspx_page_context);
                      _jspx_th_liferay$1ui_search$1container$1column$1icon_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_1);
                      _jspx_th_liferay$1ui_search$1container$1column$1icon_0.setIcon("users");
                      _jspx_th_liferay$1ui_search$1container$1column$1icon_0.setToggleRowChecker( true );
                      int _jspx_eval_liferay$1ui_search$1container$1column$1icon_0 = _jspx_th_liferay$1ui_search$1container$1column$1icon_0.doStartTag();
                      if (_jspx_th_liferay$1ui_search$1container$1column$1icon_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_liferay$1ui_search$1container$1column$1icon_toggleRowChecker_icon_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1icon_0);
                        return;
                      }
                      _jspx_tagPool_liferay$1ui_search$1container$1column$1icon_toggleRowChecker_icon_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1icon_0);
                      out.write("\n\n\t\t");
                      //  liferay-ui:search-container-column-text
                      com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_0 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text_colspan.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
                      _jspx_th_liferay$1ui_search$1container$1column$1text_0.setPageContext(_jspx_page_context);
                      _jspx_th_liferay$1ui_search$1container$1column$1text_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_1);
                      _jspx_th_liferay$1ui_search$1container$1column$1text_0.setColspan( 2 );
                      int _jspx_eval_liferay$1ui_search$1container$1column$1text_0 = _jspx_th_liferay$1ui_search$1container$1column$1text_0.doStartTag();
                      if (_jspx_eval_liferay$1ui_search$1container$1column$1text_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                        if (_jspx_eval_liferay$1ui_search$1container$1column$1text_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                          out = _jspx_page_context.pushBody();
                          _jspx_th_liferay$1ui_search$1container$1column$1text_0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                          _jspx_th_liferay$1ui_search$1container$1column$1text_0.doInitBody();
                        }
                        do {
                          out.write("\n\t\t\t<h5>\n\t\t\t\t");
                          //  aui:a
                          com.liferay.taglib.aui.ATag _jspx_th_aui_a_0 = (com.liferay.taglib.aui.ATag) _jspx_tagPool_aui_a_href.get(com.liferay.taglib.aui.ATag.class);
                          _jspx_th_aui_a_0.setPageContext(_jspx_page_context);
                          _jspx_th_aui_a_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1column$1text_0);
                          _jspx_th_aui_a_0.setHref( String.valueOf(rowURL) );
                          int _jspx_eval_aui_a_0 = _jspx_th_aui_a_0.doStartTag();
                          if (_jspx_eval_aui_a_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                            out.print( organization.getName() );
                          }
                          if (_jspx_th_aui_a_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                            _jspx_tagPool_aui_a_href.reuse(_jspx_th_aui_a_0);
                            return;
                          }
                          _jspx_tagPool_aui_a_href.reuse(_jspx_th_aui_a_0);
                          out.write("\n\t\t\t</h5>\n\n\t\t\t<h6 class=\"text-default\">\n\t\t\t\t");
                          out.print( HtmlUtil.escape(organization.getParentOrganizationName()) );
                          out.write("\n\t\t\t</h6>\n\n\t\t\t<h6 class=\"text-default\">\n\t\t\t\t");
                          out.print( LanguageUtil.get(request, organization.getType()) );
                          out.write("\n\t\t\t</h6>\n\n\t\t\t<h6 class=\"text-default\">\n\t\t\t\t");
                          out.print( HtmlUtil.escape(organization.getAddress().getCity()) );
                          out.write("\n\t\t\t\t");
                          out.print( UsersAdmin.ORGANIZATION_REGION_NAME_ACCESSOR.get(organization) );
                          out.write("\n\t\t\t\t");
                          out.print( UsersAdmin.ORGANIZATION_COUNTRY_NAME_ACCESSOR.get(organization) );
                          out.write("\n\t\t\t</h6>\n\t\t");
                          int evalDoAfterBody = _jspx_th_liferay$1ui_search$1container$1column$1text_0.doAfterBody();
                          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                            break;
                        } while (true);
                        if (_jspx_eval_liferay$1ui_search$1container$1column$1text_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                          out = _jspx_page_context.popBody();
                      }
                      if (_jspx_th_liferay$1ui_search$1container$1column$1text_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_liferay$1ui_search$1container$1column$1text_colspan.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_0);
                        return;
                      }
                      _jspx_tagPool_liferay$1ui_search$1container$1column$1text_colspan.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_0);
                      out.write("\n\n\t\t");
                      if (_jspx_meth_liferay$1ui_search$1container$1column$1jsp_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_when_1, _jspx_page_context))
                        return;
                      out.write('\n');
                      out.write('	');
                    }
                    if (_jspx_th_c_when_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_1);
                      return;
                    }
                    _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_1);
                    out.write('\n');
                    out.write('	');
                    //  c:when
                    com.liferay.taglib.core.WhenTag _jspx_th_c_when_2 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
                    _jspx_th_c_when_2.setPageContext(_jspx_page_context);
                    _jspx_th_c_when_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_1);
                    _jspx_th_c_when_2.setTest( displayStyle.equals("icon") );
                    int _jspx_eval_c_when_2 = _jspx_th_c_when_2.doStartTag();
                    if (_jspx_eval_c_when_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      out.write("\n\n\t\t");

		row.setCssClass("entry-card lfr-asset-item");
		
                      out.write("\n\n\t\t");
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
                          //  liferay-frontend:vertical-card
                          com.liferay.frontend.taglib.servlet.taglib.VerticalCardTag _jspx_th_liferay$1frontend_vertical$1card_0 = (com.liferay.frontend.taglib.servlet.taglib.VerticalCardTag) _jspx_tagPool_liferay$1frontend_vertical$1card_url_title_subtitle_rowChecker_resultRow_imageUrl_cssClass_actionJspServletContext_actionJsp_nobody.get(com.liferay.frontend.taglib.servlet.taglib.VerticalCardTag.class);
                          _jspx_th_liferay$1frontend_vertical$1card_0.setPageContext(_jspx_page_context);
                          _jspx_th_liferay$1frontend_vertical$1card_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1column$1text_1);
                          _jspx_th_liferay$1frontend_vertical$1card_0.setActionJsp("/organization_action.jsp");
                          _jspx_th_liferay$1frontend_vertical$1card_0.setActionJspServletContext( application );
                          _jspx_th_liferay$1frontend_vertical$1card_0.setCssClass("entry-display-style");
                          _jspx_th_liferay$1frontend_vertical$1card_0.setImageUrl( themeDisplay.getPathImage() + "/organization_logo?img_id=" + organization.getLogoId() + "&t=" + WebServerServletTokenUtil.getToken(organization.getLogoId()) );
                          _jspx_th_liferay$1frontend_vertical$1card_0.setResultRow( row );
                          _jspx_th_liferay$1frontend_vertical$1card_0.setRowChecker( organizationSearchContainer.getRowChecker() );
                          _jspx_th_liferay$1frontend_vertical$1card_0.setSubtitle( LanguageUtil.get(request, organization.getType()) );
                          _jspx_th_liferay$1frontend_vertical$1card_0.setTitle( organization.getName() );
                          _jspx_th_liferay$1frontend_vertical$1card_0.setUrl( String.valueOf(rowURL) );
                          int _jspx_eval_liferay$1frontend_vertical$1card_0 = _jspx_th_liferay$1frontend_vertical$1card_0.doStartTag();
                          if (_jspx_th_liferay$1frontend_vertical$1card_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                            _jspx_tagPool_liferay$1frontend_vertical$1card_url_title_subtitle_rowChecker_resultRow_imageUrl_cssClass_actionJspServletContext_actionJsp_nobody.reuse(_jspx_th_liferay$1frontend_vertical$1card_0);
                            return;
                          }
                          _jspx_tagPool_liferay$1frontend_vertical$1card_url_title_subtitle_rowChecker_resultRow_imageUrl_cssClass_actionJspServletContext_actionJsp_nobody.reuse(_jspx_th_liferay$1frontend_vertical$1card_0);
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
                    com.liferay.taglib.core.OtherwiseTag _jspx_th_c_otherwise_0 = (com.liferay.taglib.core.OtherwiseTag) _jspx_tagPool_c_otherwise.get(com.liferay.taglib.core.OtherwiseTag.class);
                    _jspx_th_c_otherwise_0.setPageContext(_jspx_page_context);
                    _jspx_th_c_otherwise_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_1);
                    int _jspx_eval_c_otherwise_0 = _jspx_th_c_otherwise_0.doStartTag();
                    if (_jspx_eval_c_otherwise_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      out.write("\n\t\t");
                      //  liferay-ui:search-container-column-text
                      com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_2 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_orderable_name_href_cssClass_nobody.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
                      _jspx_th_liferay$1ui_search$1container$1column$1text_2.setPageContext(_jspx_page_context);
                      _jspx_th_liferay$1ui_search$1container$1column$1text_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_0);
                      _jspx_th_liferay$1ui_search$1container$1column$1text_2.setCssClass("table-cell-expand table-cell-minw-200 table-title");
                      _jspx_th_liferay$1ui_search$1container$1column$1text_2.setHref( rowURL );
                      _jspx_th_liferay$1ui_search$1container$1column$1text_2.setName("name");
                      _jspx_th_liferay$1ui_search$1container$1column$1text_2.setOrderable( true );
                      _jspx_th_liferay$1ui_search$1container$1column$1text_2.setValue( organization.getName() );
                      int _jspx_eval_liferay$1ui_search$1container$1column$1text_2 = _jspx_th_liferay$1ui_search$1container$1column$1text_2.doStartTag();
                      if (_jspx_th_liferay$1ui_search$1container$1column$1text_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_orderable_name_href_cssClass_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_2);
                        return;
                      }
                      _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_orderable_name_href_cssClass_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_2);
                      out.write("\n\n\t\t");
                      //  c:if
                      com.liferay.taglib.core.IfTag _jspx_th_c_if_1 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                      _jspx_th_c_if_1.setPageContext(_jspx_page_context);
                      _jspx_th_c_if_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_0);
                      _jspx_th_c_if_1.setTest( Validator.isNotNull(keywords) );
                      int _jspx_eval_c_if_1 = _jspx_th_c_if_1.doStartTag();
                      if (_jspx_eval_c_if_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                        out.write("\n\t\t\t");
                        //  liferay-ui:search-container-column-text
                        com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_3 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_href_cssClass_nobody.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
                        _jspx_th_liferay$1ui_search$1container$1column$1text_3.setPageContext(_jspx_page_context);
                        _jspx_th_liferay$1ui_search$1container$1column$1text_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_1);
                        _jspx_th_liferay$1ui_search$1container$1column$1text_3.setCssClass("table-cell-expand table-cell-minw-200");
                        _jspx_th_liferay$1ui_search$1container$1column$1text_3.setHref( rowURL );
                        _jspx_th_liferay$1ui_search$1container$1column$1text_3.setName("parent-organization");
                        _jspx_th_liferay$1ui_search$1container$1column$1text_3.setValue( HtmlUtil.escape(organization.getParentOrganizationName()) );
                        int _jspx_eval_liferay$1ui_search$1container$1column$1text_3 = _jspx_th_liferay$1ui_search$1container$1column$1text_3.doStartTag();
                        if (_jspx_th_liferay$1ui_search$1container$1column$1text_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                          _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_href_cssClass_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_3);
                          return;
                        }
                        _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_href_cssClass_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_3);
                        out.write("\n\t\t");
                      }
                      if (_jspx_th_c_if_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_1);
                        return;
                      }
                      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_1);
                      out.write("\n\n\t\t");
                      //  liferay-ui:search-container-column-text
                      com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_4 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_orderable_name_href_cssClass_nobody.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
                      _jspx_th_liferay$1ui_search$1container$1column$1text_4.setPageContext(_jspx_page_context);
                      _jspx_th_liferay$1ui_search$1container$1column$1text_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_0);
                      _jspx_th_liferay$1ui_search$1container$1column$1text_4.setCssClass("table-cell-expand table-cell-minw-200");
                      _jspx_th_liferay$1ui_search$1container$1column$1text_4.setHref( rowURL );
                      _jspx_th_liferay$1ui_search$1container$1column$1text_4.setName("type");
                      _jspx_th_liferay$1ui_search$1container$1column$1text_4.setOrderable( true );
                      _jspx_th_liferay$1ui_search$1container$1column$1text_4.setValue( LanguageUtil.get(request, organization.getType()) );
                      int _jspx_eval_liferay$1ui_search$1container$1column$1text_4 = _jspx_th_liferay$1ui_search$1container$1column$1text_4.doStartTag();
                      if (_jspx_th_liferay$1ui_search$1container$1column$1text_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_orderable_name_href_cssClass_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_4);
                        return;
                      }
                      _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_orderable_name_href_cssClass_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_4);
                      out.write("\n\n\t\t");
                      //  c:if
                      com.liferay.taglib.core.IfTag _jspx_th_c_if_2 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                      _jspx_th_c_if_2.setPageContext(_jspx_page_context);
                      _jspx_th_c_if_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_0);
                      _jspx_th_c_if_2.setTest( Validator.isNotNull(keywords) );
                      int _jspx_eval_c_if_2 = _jspx_th_c_if_2.doStartTag();
                      if (_jspx_eval_c_if_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                        out.write("\n\t\t\t");
                        //  liferay-ui:search-container-column-text
                        com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_5 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_href_nobody.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
                        _jspx_th_liferay$1ui_search$1container$1column$1text_5.setPageContext(_jspx_page_context);
                        _jspx_th_liferay$1ui_search$1container$1column$1text_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_2);
                        _jspx_th_liferay$1ui_search$1container$1column$1text_5.setHref( rowURL );
                        _jspx_th_liferay$1ui_search$1container$1column$1text_5.setName("city");
                        _jspx_th_liferay$1ui_search$1container$1column$1text_5.setValue( HtmlUtil.escape(organization.getAddress().getCity()) );
                        int _jspx_eval_liferay$1ui_search$1container$1column$1text_5 = _jspx_th_liferay$1ui_search$1container$1column$1text_5.doStartTag();
                        if (_jspx_th_liferay$1ui_search$1container$1column$1text_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                          _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_href_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_5);
                          return;
                        }
                        _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_href_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_5);
                        out.write("\n\n\t\t\t");
                        //  liferay-ui:search-container-column-text
                        com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_6 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_href_nobody.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
                        _jspx_th_liferay$1ui_search$1container$1column$1text_6.setPageContext(_jspx_page_context);
                        _jspx_th_liferay$1ui_search$1container$1column$1text_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_2);
                        _jspx_th_liferay$1ui_search$1container$1column$1text_6.setHref( rowURL );
                        _jspx_th_liferay$1ui_search$1container$1column$1text_6.setName("region");
                        _jspx_th_liferay$1ui_search$1container$1column$1text_6.setValue( UsersAdmin.ORGANIZATION_REGION_NAME_ACCESSOR.get(organization) );
                        int _jspx_eval_liferay$1ui_search$1container$1column$1text_6 = _jspx_th_liferay$1ui_search$1container$1column$1text_6.doStartTag();
                        if (_jspx_th_liferay$1ui_search$1container$1column$1text_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                          _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_href_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_6);
                          return;
                        }
                        _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_href_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_6);
                        out.write("\n\n\t\t\t");
                        //  liferay-ui:search-container-column-text
                        com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_7 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_href_nobody.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
                        _jspx_th_liferay$1ui_search$1container$1column$1text_7.setPageContext(_jspx_page_context);
                        _jspx_th_liferay$1ui_search$1container$1column$1text_7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_2);
                        _jspx_th_liferay$1ui_search$1container$1column$1text_7.setHref( rowURL );
                        _jspx_th_liferay$1ui_search$1container$1column$1text_7.setName("country");
                        _jspx_th_liferay$1ui_search$1container$1column$1text_7.setValue( UsersAdmin.ORGANIZATION_COUNTRY_NAME_ACCESSOR.get(organization) );
                        int _jspx_eval_liferay$1ui_search$1container$1column$1text_7 = _jspx_th_liferay$1ui_search$1container$1column$1text_7.doStartTag();
                        if (_jspx_th_liferay$1ui_search$1container$1column$1text_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                          _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_href_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_7);
                          return;
                        }
                        _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_href_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_7);
                        out.write("\n\t\t");
                      }
                      if (_jspx_th_c_if_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_2);
                        return;
                      }
                      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_2);
                      out.write("\n\n\t\t");
                      if (_jspx_meth_liferay$1ui_search$1container$1column$1jsp_1((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_otherwise_0, _jspx_page_context))
                        return;
                      out.write('\n');
                      out.write('	');
                    }
                    if (_jspx_th_c_otherwise_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_0);
                      return;
                    }
                    _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_0);
                    out.write('\n');
                  }
                  if (_jspx_th_c_choose_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_1);
                    return;
                  }
                  _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_1);
                  out.write("\n\t\t\t\t");
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
                _jspx_tagPool_liferay$1ui_search$1container$1row_modelVar_keyProperty_escapedModel_className.reuse(_jspx_th_liferay$1ui_search$1container$1row_0);
                return;
              }
              _jspx_tagPool_liferay$1ui_search$1container$1row_modelVar_keyProperty_escapedModel_className.reuse(_jspx_th_liferay$1ui_search$1container$1row_0);
              out.write("\n\n\t\t\t\t");
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
              out.write("\n\t\t\t");
            }
            if (_jspx_th_liferay$1ui_search$1container_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_liferay$1ui_search$1container_var_searchContainer_id.reuse(_jspx_th_liferay$1ui_search$1container_0);
              return;
            }
            _jspx_tagPool_liferay$1ui_search$1container_var_searchContainer_id.reuse(_jspx_th_liferay$1ui_search$1container_0);
            out.write("\n\t\t");
          }
          if (_jspx_th_aui_form_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_aui_form_onSubmit_name_method_cssClass_action.reuse(_jspx_th_aui_form_0);
            return;
          }
          _jspx_tagPool_aui_form_onSubmit_name_method_cssClass_action.reuse(_jspx_th_aui_form_0);
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
        if (_jspx_meth_c_otherwise_1((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_choose_0, _jspx_page_context))
          return;
        out.write('\n');
      }
      if (_jspx_th_c_choose_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_0);
        return;
      }
      _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_0);
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

  private boolean _jspx_meth_liferay$1portlet_renderURLParams_0(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_form_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-portlet:renderURLParams
    com.liferay.taglib.portlet.RenderURLParamsTag _jspx_th_liferay$1portlet_renderURLParams_0 = (com.liferay.taglib.portlet.RenderURLParamsTag) _jspx_tagPool_liferay$1portlet_renderURLParams_varImpl_nobody.get(com.liferay.taglib.portlet.RenderURLParamsTag.class);
    _jspx_th_liferay$1portlet_renderURLParams_0.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1portlet_renderURLParams_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
    _jspx_th_liferay$1portlet_renderURLParams_0.setVarImpl("portletURL");
    int _jspx_eval_liferay$1portlet_renderURLParams_0 = _jspx_th_liferay$1portlet_renderURLParams_0.doStartTag();
    if (_jspx_th_liferay$1portlet_renderURLParams_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1portlet_renderURLParams_varImpl_nobody.reuse(_jspx_th_liferay$1portlet_renderURLParams_0);
      return true;
    }
    _jspx_tagPool_liferay$1portlet_renderURLParams_varImpl_nobody.reuse(_jspx_th_liferay$1portlet_renderURLParams_0);
    return false;
  }

  private boolean _jspx_meth_aui_input_4(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1ui_search$1container_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:input
    com.liferay.taglib.aui.InputTag _jspx_th_aui_input_4 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
    _jspx_th_aui_input_4.setPageContext(_jspx_page_context);
    _jspx_th_aui_input_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container_0);
    _jspx_th_aui_input_4.setName("deleteOrganizationIds");
    _jspx_th_aui_input_4.setType("hidden");
    int _jspx_eval_aui_input_4 = _jspx_th_aui_input_4.doStartTag();
    if (_jspx_th_aui_input_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_input_type_name_nobody.reuse(_jspx_th_aui_input_4);
      return true;
    }
    _jspx_tagPool_aui_input_type_name_nobody.reuse(_jspx_th_aui_input_4);
    return false;
  }

  private boolean _jspx_meth_portlet_param_0(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1portlet_renderURL_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:param
    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_0 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
    _jspx_th_portlet_param_0.setPageContext(_jspx_page_context);
    _jspx_th_portlet_param_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_0);
    _jspx_th_portlet_param_0.setName("mvcRenderCommandName");
    _jspx_th_portlet_param_0.setValue("/users_admin/view");
    int _jspx_eval_portlet_param_0 = _jspx_th_portlet_param_0.doStartTag();
    if (_jspx_th_portlet_param_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_0);
      return true;
    }
    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_0);
    return false;
  }

  private boolean _jspx_meth_portlet_param_1(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1portlet_renderURL_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:param
    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_1 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
    _jspx_th_portlet_param_1.setPageContext(_jspx_page_context);
    _jspx_th_portlet_param_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_0);
    _jspx_th_portlet_param_1.setName("toolbarItem");
    _jspx_th_portlet_param_1.setValue("view-all-organizations");
    int _jspx_eval_portlet_param_1 = _jspx_th_portlet_param_1.doStartTag();
    if (_jspx_th_portlet_param_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_1);
      return true;
    }
    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_1);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_search$1container$1column$1jsp_0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_when_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:search-container-column-jsp
    com.liferay.taglib.ui.SearchContainerColumnJSPTag _jspx_th_liferay$1ui_search$1container$1column$1jsp_0 = (com.liferay.taglib.ui.SearchContainerColumnJSPTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1jsp_path_cssClass_nobody.get(com.liferay.taglib.ui.SearchContainerColumnJSPTag.class);
    _jspx_th_liferay$1ui_search$1container$1column$1jsp_0.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_search$1container$1column$1jsp_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_1);
    _jspx_th_liferay$1ui_search$1container$1column$1jsp_0.setCssClass("entry-action-column");
    _jspx_th_liferay$1ui_search$1container$1column$1jsp_0.setPath("/organization_action.jsp");
    int _jspx_eval_liferay$1ui_search$1container$1column$1jsp_0 = _jspx_th_liferay$1ui_search$1container$1column$1jsp_0.doStartTag();
    if (_jspx_th_liferay$1ui_search$1container$1column$1jsp_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_search$1container$1column$1jsp_path_cssClass_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1jsp_0);
      return true;
    }
    _jspx_tagPool_liferay$1ui_search$1container$1column$1jsp_path_cssClass_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1jsp_0);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_search$1container$1column$1jsp_1(javax.servlet.jsp.tagext.JspTag _jspx_th_c_otherwise_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:search-container-column-jsp
    com.liferay.taglib.ui.SearchContainerColumnJSPTag _jspx_th_liferay$1ui_search$1container$1column$1jsp_1 = (com.liferay.taglib.ui.SearchContainerColumnJSPTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1jsp_path_cssClass_nobody.get(com.liferay.taglib.ui.SearchContainerColumnJSPTag.class);
    _jspx_th_liferay$1ui_search$1container$1column$1jsp_1.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_search$1container$1column$1jsp_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_0);
    _jspx_th_liferay$1ui_search$1container$1column$1jsp_1.setCssClass("entry-action-column");
    _jspx_th_liferay$1ui_search$1container$1column$1jsp_1.setPath("/organization_action.jsp");
    int _jspx_eval_liferay$1ui_search$1container$1column$1jsp_1 = _jspx_th_liferay$1ui_search$1container$1column$1jsp_1.doStartTag();
    if (_jspx_th_liferay$1ui_search$1container$1column$1jsp_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_search$1container$1column$1jsp_path_cssClass_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1jsp_1);
      return true;
    }
    _jspx_tagPool_liferay$1ui_search$1container$1column$1jsp_path_cssClass_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1jsp_1);
    return false;
  }

  private boolean _jspx_meth_c_otherwise_1(javax.servlet.jsp.tagext.JspTag _jspx_th_c_choose_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:otherwise
    com.liferay.taglib.core.OtherwiseTag _jspx_th_c_otherwise_1 = (com.liferay.taglib.core.OtherwiseTag) _jspx_tagPool_c_otherwise.get(com.liferay.taglib.core.OtherwiseTag.class);
    _jspx_th_c_otherwise_1.setPageContext(_jspx_page_context);
    _jspx_th_c_otherwise_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_0);
    int _jspx_eval_c_otherwise_1 = _jspx_th_c_otherwise_1.doStartTag();
    if (_jspx_eval_c_otherwise_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      out.write("\n\t\t<div class=\"alert alert-info\">\n\t\t\t");
      if (_jspx_meth_liferay$1ui_message_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_otherwise_1, _jspx_page_context))
        return true;
      out.write("\n\t\t</div>\n\t");
    }
    if (_jspx_th_c_otherwise_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_1);
      return true;
    }
    _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_1);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_otherwise_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_0 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_0.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_1);
    _jspx_th_liferay$1ui_message_0.setKey("you-do-not-belong-to-an-organization-and-are-not-allowed-to-view-other-organizations");
    int _jspx_eval_liferay$1ui_message_0 = _jspx_th_liferay$1ui_message_0.doStartTag();
    if (_jspx_th_liferay$1ui_message_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_0);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_0);
    return false;
  }
}
