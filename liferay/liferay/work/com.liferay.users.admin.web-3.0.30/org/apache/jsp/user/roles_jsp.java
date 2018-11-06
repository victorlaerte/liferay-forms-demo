package org.apache.jsp.user;

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

public final class roles_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(2);
    _jspx_dependants.add("/init.jsp");
    _jspx_dependants.add("/init-ext.jsp");
  }

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_message_key_arguments_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_if_test;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container_total_iteratorURL_id_headerNames_curParam_cssClass;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_script;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container$1row_rowIdProperty_modelVar_keyProperty_className;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_error$1marker_value_key_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_input_type_name_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1util_buffer_var;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_choose;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1frontend_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_script_use;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_cssClass_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_when_test;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_namespace_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_message_key_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1iterator_markupView_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container$1results_results_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container$1row_modelVar_keyProperty_className;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_otherwise;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1staging_descriptive$1name_group_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1theme_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_membership$1policy$1error_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container$1column$1text;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container$1column$1text_name_cssClass;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_icon_message_label_iconCssClass_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_icon_message_markupView_icon_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_icon_url_method_message_linkCssClass_label_id_cssClass_nobody;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_liferay$1ui_message_key_arguments_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_if_test = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container_total_iteratorURL_id_headerNames_curParam_cssClass = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_script = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container$1row_rowIdProperty_modelVar_keyProperty_className = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_error$1marker_value_key_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_input_type_name_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1util_buffer_var = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_choose = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1frontend_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_script_use = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_cssClass_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_when_test = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_namespace_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_message_key_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1iterator_markupView_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container$1results_results_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container$1row_modelVar_keyProperty_className = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_otherwise = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1staging_descriptive$1name_group_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1theme_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_membership$1policy$1error_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_name_cssClass = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_icon_message_label_iconCssClass_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_icon_message_markupView_icon_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_icon_url_method_message_linkCssClass_label_id_cssClass_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_liferay$1ui_message_key_arguments_nobody.release();
    _jspx_tagPool_c_if_test.release();
    _jspx_tagPool_liferay$1ui_search$1container_total_iteratorURL_id_headerNames_curParam_cssClass.release();
    _jspx_tagPool_aui_script.release();
    _jspx_tagPool_liferay$1ui_search$1container$1row_rowIdProperty_modelVar_keyProperty_className.release();
    _jspx_tagPool_liferay$1ui_error$1marker_value_key_nobody.release();
    _jspx_tagPool_aui_input_type_name_nobody.release();
    _jspx_tagPool_liferay$1util_buffer_var.release();
    _jspx_tagPool_c_choose.release();
    _jspx_tagPool_liferay$1frontend_defineObjects_nobody.release();
    _jspx_tagPool_aui_script_use.release();
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_cssClass_nobody.release();
    _jspx_tagPool_c_when_test.release();
    _jspx_tagPool_portlet_namespace_nobody.release();
    _jspx_tagPool_liferay$1ui_message_key_nobody.release();
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_nobody.release();
    _jspx_tagPool_liferay$1ui_search$1iterator_markupView_nobody.release();
    _jspx_tagPool_liferay$1ui_search$1container$1results_results_nobody.release();
    _jspx_tagPool_liferay$1ui_search$1container$1row_modelVar_keyProperty_className.release();
    _jspx_tagPool_c_otherwise.release();
    _jspx_tagPool_portlet_defineObjects_nobody.release();
    _jspx_tagPool_liferay$1staging_descriptive$1name_group_nobody.release();
    _jspx_tagPool_liferay$1theme_defineObjects_nobody.release();
    _jspx_tagPool_liferay$1ui_membership$1policy$1error_nobody.release();
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text.release();
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_name_cssClass.release();
    _jspx_tagPool_liferay$1ui_icon_message_label_iconCssClass_nobody.release();
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name.release();
    _jspx_tagPool_liferay$1ui_icon_message_markupView_icon_nobody.release();
    _jspx_tagPool_liferay$1ui_icon_url_method_message_linkCssClass_label_id_cssClass_nobody.release();
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

User selUser = userDisplayContext.getSelectedUser();
List<Group> groups = userDisplayContext.getGroups();
List<Organization> organizations = userDisplayContext.getOrganizations();
Long[] organizationIds = UsersAdminUtil.getOrganizationIds(organizations);
List<Role> roles = userDisplayContext.getRoles();
List<UserGroupRole> organizationRoles = userDisplayContext.getOrganizationRoles();
List<UserGroupRole> siteRoles = userDisplayContext.getSiteRoles();
List<UserGroupGroupRole> inheritedSiteRoles = userDisplayContext.getInheritedSiteRoles();
List<Group> roleGroups = userDisplayContext.getRoleGroups();

currentURLObj.setParameter("historyKey", renderResponse.getNamespace() + "roles");

String regularRoleSyncEntitiesEventName = liferayPortletResponse.getNamespace() + "syncRegularRoles";
String siteRoleSyncEntitiesEventName = liferayPortletResponse.getNamespace() + "syncSiteRoles";
String organizationRoleSyncEntitiesEventName = liferayPortletResponse.getNamespace() + "syncOrganizationRoles";

      out.write('\n');
      out.write('\n');
      //  liferay-ui:error-marker
      com.liferay.taglib.ui.ErrorMarkerTag _jspx_th_liferay$1ui_error$1marker_0 = (com.liferay.taglib.ui.ErrorMarkerTag) _jspx_tagPool_liferay$1ui_error$1marker_value_key_nobody.get(com.liferay.taglib.ui.ErrorMarkerTag.class);
      _jspx_th_liferay$1ui_error$1marker_0.setPageContext(_jspx_page_context);
      _jspx_th_liferay$1ui_error$1marker_0.setParent(null);
      _jspx_th_liferay$1ui_error$1marker_0.setKey( WebKeys.ERROR_SECTION );
      _jspx_th_liferay$1ui_error$1marker_0.setValue("roles");
      int _jspx_eval_liferay$1ui_error$1marker_0 = _jspx_th_liferay$1ui_error$1marker_0.doStartTag();
      if (_jspx_th_liferay$1ui_error$1marker_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_liferay$1ui_error$1marker_value_key_nobody.reuse(_jspx_th_liferay$1ui_error$1marker_0);
        return;
      }
      _jspx_tagPool_liferay$1ui_error$1marker_value_key_nobody.reuse(_jspx_th_liferay$1ui_error$1marker_0);
      out.write('\n');
      out.write('\n');
      if (_jspx_meth_liferay$1ui_membership$1policy$1error_0(_jspx_page_context))
        return;
      out.write('\n');
      out.write('\n');
      //  liferay-util:buffer
      com.liferay.taglib.util.BufferTag _jspx_th_liferay$1util_buffer_0 = (com.liferay.taglib.util.BufferTag) _jspx_tagPool_liferay$1util_buffer_var.get(com.liferay.taglib.util.BufferTag.class);
      _jspx_th_liferay$1util_buffer_0.setPageContext(_jspx_page_context);
      _jspx_th_liferay$1util_buffer_0.setParent(null);
      _jspx_th_liferay$1util_buffer_0.setVar("removeRoleIcon");
      int _jspx_eval_liferay$1util_buffer_0 = _jspx_th_liferay$1util_buffer_0.doStartTag();
      if (_jspx_eval_liferay$1util_buffer_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        if (_jspx_eval_liferay$1util_buffer_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
          out = _jspx_page_context.pushBody();
          _jspx_th_liferay$1util_buffer_0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
          _jspx_th_liferay$1util_buffer_0.doInitBody();
        }
        do {
          out.write('\n');
          out.write('	');
          if (_jspx_meth_liferay$1ui_icon_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1util_buffer_0, _jspx_page_context))
            return;
          out.write('\n');
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
      java.lang.String removeRoleIcon = null;
      removeRoleIcon = (java.lang.String) _jspx_page_context.findAttribute("removeRoleIcon");
      out.write('\n');
      out.write('\n');
      if (_jspx_meth_aui_input_0(_jspx_page_context))
        return;
      out.write('\n');
      if (_jspx_meth_aui_input_1(_jspx_page_context))
        return;
      out.write('\n');
      if (_jspx_meth_aui_input_2(_jspx_page_context))
        return;
      out.write('\n');
      if (_jspx_meth_aui_input_3(_jspx_page_context))
        return;
      out.write('\n');
      if (_jspx_meth_aui_input_4(_jspx_page_context))
        return;
      out.write('\n');
      if (_jspx_meth_aui_input_5(_jspx_page_context))
        return;
      out.write("\n\n<div class=\"sheet-section\">\n\t<h3 class=\"sheet-subtitle\">\n\t\t<span class=\"autofit-padded-no-gutters autofit-row\">\n\t\t\t<span class=\"autofit-col autofit-col-expand\">\n\t\t\t\t<span class=\"heading-text\">\n\t\t\t\t\t");
      if (_jspx_meth_liferay$1ui_message_0(_jspx_page_context))
        return;
      out.write("\n\t\t\t\t</span>\n\t\t\t</span>\n\n\t\t\t");
      //  c:if
      com.liferay.taglib.core.IfTag _jspx_th_c_if_0 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
      _jspx_th_c_if_0.setPageContext(_jspx_page_context);
      _jspx_th_c_if_0.setParent(null);
      _jspx_th_c_if_0.setTest( !portletName.equals(myAccountPortletId) );
      int _jspx_eval_c_if_0 = _jspx_th_c_if_0.doStartTag();
      if (_jspx_eval_c_if_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        out.write("\n\t\t\t\t<span class=\"autofit-col\">\n\t\t\t\t\t");
        //  liferay-ui:icon
        com.liferay.taglib.ui.IconTag _jspx_th_liferay$1ui_icon_1 = (com.liferay.taglib.ui.IconTag) _jspx_tagPool_liferay$1ui_icon_url_method_message_linkCssClass_label_id_cssClass_nobody.get(com.liferay.taglib.ui.IconTag.class);
        _jspx_th_liferay$1ui_icon_1.setPageContext(_jspx_page_context);
        _jspx_th_liferay$1ui_icon_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_0);
        _jspx_th_liferay$1ui_icon_1.setCssClass("modify-link");
        _jspx_th_liferay$1ui_icon_1.setId("selectRegularRoleLink");
        _jspx_th_liferay$1ui_icon_1.setLabel( true );
        _jspx_th_liferay$1ui_icon_1.setLinkCssClass("btn btn-secondary btn-sm");
        _jspx_th_liferay$1ui_icon_1.setMessage("select");
        _jspx_th_liferay$1ui_icon_1.setMethod("get");
        _jspx_th_liferay$1ui_icon_1.setUrl("javascript:;");
        int _jspx_eval_liferay$1ui_icon_1 = _jspx_th_liferay$1ui_icon_1.doStartTag();
        if (_jspx_th_liferay$1ui_icon_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_liferay$1ui_icon_url_method_message_linkCssClass_label_id_cssClass_nobody.reuse(_jspx_th_liferay$1ui_icon_1);
          return;
        }
        _jspx_tagPool_liferay$1ui_icon_url_method_message_linkCssClass_label_id_cssClass_nobody.reuse(_jspx_th_liferay$1ui_icon_1);
        out.write("\n\t\t\t\t</span>\n\t\t\t");
      }
      if (_jspx_th_c_if_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
        return;
      }
      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
      out.write("\n\t\t</span>\n\t</h3>\n\n\t");
      //  liferay-ui:search-container
      com.liferay.taglib.ui.SearchContainerTag _jspx_th_liferay$1ui_search$1container_0 = (com.liferay.taglib.ui.SearchContainerTag) _jspx_tagPool_liferay$1ui_search$1container_total_iteratorURL_id_headerNames_curParam_cssClass.get(com.liferay.taglib.ui.SearchContainerTag.class);
      _jspx_th_liferay$1ui_search$1container_0.setPageContext(_jspx_page_context);
      _jspx_th_liferay$1ui_search$1container_0.setParent(null);
      _jspx_th_liferay$1ui_search$1container_0.setCssClass("lfr-search-container-roles");
      _jspx_th_liferay$1ui_search$1container_0.setCurParam("regularRolesCur");
      _jspx_th_liferay$1ui_search$1container_0.setHeaderNames("title,null");
      _jspx_th_liferay$1ui_search$1container_0.setId("rolesSearchContainer");
      _jspx_th_liferay$1ui_search$1container_0.setIteratorURL( currentURLObj );
      _jspx_th_liferay$1ui_search$1container_0.setTotal( roles.size() );
      int _jspx_eval_liferay$1ui_search$1container_0 = _jspx_th_liferay$1ui_search$1container_0.doStartTag();
      if (_jspx_eval_liferay$1ui_search$1container_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        java.lang.Integer total = null;
        com.liferay.portal.kernel.dao.search.SearchContainer searchContainer = null;
        total = (java.lang.Integer) _jspx_page_context.findAttribute("total");
        searchContainer = (com.liferay.portal.kernel.dao.search.SearchContainer) _jspx_page_context.findAttribute("searchContainer");
        out.write("\n\t\t");
        //  liferay-ui:search-container-results
        java.util.List results = null;
        java.lang.Integer deprecatedTotal = null;
        com.liferay.taglib.ui.SearchContainerResultsTag _jspx_th_liferay$1ui_search$1container$1results_0 = (com.liferay.taglib.ui.SearchContainerResultsTag) _jspx_tagPool_liferay$1ui_search$1container$1results_results_nobody.get(com.liferay.taglib.ui.SearchContainerResultsTag.class);
        _jspx_th_liferay$1ui_search$1container$1results_0.setPageContext(_jspx_page_context);
        _jspx_th_liferay$1ui_search$1container$1results_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container_0);
        _jspx_th_liferay$1ui_search$1container$1results_0.setResults( roles.subList(searchContainer.getStart(), searchContainer.getResultEnd()) );
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
        out.write("\n\n\t\t");
        //  liferay-ui:search-container-row
        com.liferay.taglib.ui.SearchContainerRowTag _jspx_th_liferay$1ui_search$1container$1row_0 = (com.liferay.taglib.ui.SearchContainerRowTag) _jspx_tagPool_liferay$1ui_search$1container$1row_modelVar_keyProperty_className.get(com.liferay.taglib.ui.SearchContainerRowTag.class);
        _jspx_th_liferay$1ui_search$1container$1row_0.setPageContext(_jspx_page_context);
        _jspx_th_liferay$1ui_search$1container$1row_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container_0);
        _jspx_th_liferay$1ui_search$1container$1row_0.setClassName("com.liferay.portal.kernel.model.Role");
        _jspx_th_liferay$1ui_search$1container$1row_0.setKeyProperty("roleId");
        _jspx_th_liferay$1ui_search$1container$1row_0.setModelVar("role");
        int _jspx_eval_liferay$1ui_search$1container$1row_0 = _jspx_th_liferay$1ui_search$1container$1row_0.doStartTag();
        if (_jspx_eval_liferay$1ui_search$1container$1row_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          java.lang.Integer index = null;
          com.liferay.portal.kernel.model.Role role = null;
          com.liferay.portal.kernel.dao.search.ResultRow row = null;
          if (_jspx_eval_liferay$1ui_search$1container$1row_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
            out = _jspx_page_context.pushBody();
            _jspx_th_liferay$1ui_search$1container$1row_0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
            _jspx_th_liferay$1ui_search$1container$1row_0.doInitBody();
          }
          index = (java.lang.Integer) _jspx_page_context.findAttribute("index");
          role = (com.liferay.portal.kernel.model.Role) _jspx_page_context.findAttribute("role");
          row = (com.liferay.portal.kernel.dao.search.ResultRow) _jspx_page_context.findAttribute("row");
          do {
            out.write("\n\t\t\t");
            //  liferay-ui:search-container-column-text
            com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_0 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text_name_cssClass.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
            _jspx_th_liferay$1ui_search$1container$1column$1text_0.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1ui_search$1container$1column$1text_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1row_0);
            _jspx_th_liferay$1ui_search$1container$1column$1text_0.setCssClass("table-cell-content");
            _jspx_th_liferay$1ui_search$1container$1column$1text_0.setName("title");
            int _jspx_eval_liferay$1ui_search$1container$1column$1text_0 = _jspx_th_liferay$1ui_search$1container$1column$1text_0.doStartTag();
            if (_jspx_eval_liferay$1ui_search$1container$1column$1text_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              if (_jspx_eval_liferay$1ui_search$1container$1column$1text_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                out = _jspx_page_context.pushBody();
                _jspx_th_liferay$1ui_search$1container$1column$1text_0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                _jspx_th_liferay$1ui_search$1container$1column$1text_0.doInitBody();
              }
              do {
                out.write("\n\t\t\t\t");
                //  liferay-ui:icon
                com.liferay.taglib.ui.IconTag _jspx_th_liferay$1ui_icon_2 = (com.liferay.taglib.ui.IconTag) _jspx_tagPool_liferay$1ui_icon_message_label_iconCssClass_nobody.get(com.liferay.taglib.ui.IconTag.class);
                _jspx_th_liferay$1ui_icon_2.setPageContext(_jspx_page_context);
                _jspx_th_liferay$1ui_icon_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1column$1text_0);
                _jspx_th_liferay$1ui_icon_2.setIconCssClass( RolesAdminUtil.getIconCssClass(role) );
                _jspx_th_liferay$1ui_icon_2.setLabel( true );
                _jspx_th_liferay$1ui_icon_2.setMessage( HtmlUtil.escape(role.getTitle(locale)) );
                int _jspx_eval_liferay$1ui_icon_2 = _jspx_th_liferay$1ui_icon_2.doStartTag();
                if (_jspx_th_liferay$1ui_icon_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_liferay$1ui_icon_message_label_iconCssClass_nobody.reuse(_jspx_th_liferay$1ui_icon_2);
                  return;
                }
                _jspx_tagPool_liferay$1ui_icon_message_label_iconCssClass_nobody.reuse(_jspx_th_liferay$1ui_icon_2);
                out.write("\n\t\t\t");
                int evalDoAfterBody = _jspx_th_liferay$1ui_search$1container$1column$1text_0.doAfterBody();
                if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                  break;
              } while (true);
              if (_jspx_eval_liferay$1ui_search$1container$1column$1text_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                out = _jspx_page_context.popBody();
            }
            if (_jspx_th_liferay$1ui_search$1container$1column$1text_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_liferay$1ui_search$1container$1column$1text_name_cssClass.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_0);
              return;
            }
            _jspx_tagPool_liferay$1ui_search$1container$1column$1text_name_cssClass.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_0);
            out.write("\n\n\t\t\t");
            //  c:if
            com.liferay.taglib.core.IfTag _jspx_th_c_if_1 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
            _jspx_th_c_if_1.setPageContext(_jspx_page_context);
            _jspx_th_c_if_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1row_0);
            _jspx_th_c_if_1.setTest( !portletName.equals(myAccountPortletId) && !RoleMembershipPolicyUtil.isRoleRequired(selUser.getUserId(), role.getRoleId()) );
            int _jspx_eval_c_if_1 = _jspx_th_c_if_1.doStartTag();
            if (_jspx_eval_c_if_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\t\t\t\t");
              //  liferay-ui:search-container-column-text
              com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_1 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
              _jspx_th_liferay$1ui_search$1container$1column$1text_1.setPageContext(_jspx_page_context);
              _jspx_th_liferay$1ui_search$1container$1column$1text_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_1);
              int _jspx_eval_liferay$1ui_search$1container$1column$1text_1 = _jspx_th_liferay$1ui_search$1container$1column$1text_1.doStartTag();
              if (_jspx_eval_liferay$1ui_search$1container$1column$1text_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                if (_jspx_eval_liferay$1ui_search$1container$1column$1text_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                  out = _jspx_page_context.pushBody();
                  _jspx_th_liferay$1ui_search$1container$1column$1text_1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                  _jspx_th_liferay$1ui_search$1container$1column$1text_1.doInitBody();
                }
                do {
                  out.write("\n\t\t\t\t\t<a class=\"modify-link\" data-rowId=\"");
                  out.print( role.getRoleId() );
                  out.write("\" href=\"javascript:;\">");
                  out.print( removeRoleIcon );
                  out.write("</a>\n\t\t\t\t");
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
              out.write("\n\t\t\t");
            }
            if (_jspx_th_c_if_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_1);
              return;
            }
            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_1);
            out.write("\n\t\t");
            int evalDoAfterBody = _jspx_th_liferay$1ui_search$1container$1row_0.doAfterBody();
            index = (java.lang.Integer) _jspx_page_context.findAttribute("index");
            role = (com.liferay.portal.kernel.model.Role) _jspx_page_context.findAttribute("role");
            row = (com.liferay.portal.kernel.dao.search.ResultRow) _jspx_page_context.findAttribute("row");
            if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
              break;
          } while (true);
          if (_jspx_eval_liferay$1ui_search$1container$1row_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
            out = _jspx_page_context.popBody();
        }
        if (_jspx_th_liferay$1ui_search$1container$1row_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_liferay$1ui_search$1container$1row_modelVar_keyProperty_className.reuse(_jspx_th_liferay$1ui_search$1container$1row_0);
          return;
        }
        _jspx_tagPool_liferay$1ui_search$1container$1row_modelVar_keyProperty_className.reuse(_jspx_th_liferay$1ui_search$1container$1row_0);
        out.write("\n\n\t\t");
        if (_jspx_meth_liferay$1ui_search$1iterator_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1ui_search$1container_0, _jspx_page_context))
          return;
        out.write('\n');
        out.write('	');
      }
      if (_jspx_th_liferay$1ui_search$1container_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_liferay$1ui_search$1container_total_iteratorURL_id_headerNames_curParam_cssClass.reuse(_jspx_th_liferay$1ui_search$1container_0);
        return;
      }
      _jspx_tagPool_liferay$1ui_search$1container_total_iteratorURL_id_headerNames_curParam_cssClass.reuse(_jspx_th_liferay$1ui_search$1container_0);
      out.write("\n\n\t");
      //  c:if
      com.liferay.taglib.core.IfTag _jspx_th_c_if_2 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
      _jspx_th_c_if_2.setPageContext(_jspx_page_context);
      _jspx_th_c_if_2.setParent(null);
      _jspx_th_c_if_2.setTest( !portletName.equals(myAccountPortletId) );
      int _jspx_eval_c_if_2 = _jspx_th_c_if_2.doStartTag();
      if (_jspx_eval_c_if_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        out.write("\n\t\t");
        //  aui:script
        com.liferay.taglib.aui.ScriptTag _jspx_th_aui_script_0 = (com.liferay.taglib.aui.ScriptTag) _jspx_tagPool_aui_script.get(com.liferay.taglib.aui.ScriptTag.class);
        _jspx_th_aui_script_0.setPageContext(_jspx_page_context);
        _jspx_th_aui_script_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_2);
        int _jspx_eval_aui_script_0 = _jspx_th_aui_script_0.doStartTag();
        if (_jspx_eval_aui_script_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          if (_jspx_eval_aui_script_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
            out = _jspx_page_context.pushBody();
            _jspx_th_aui_script_0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
            _jspx_th_aui_script_0.doInitBody();
          }
          do {
            out.write("\n\t\t\tAUI.$('#");
            if (_jspx_meth_portlet_namespace_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_0, _jspx_page_context))
              return;
            out.write("selectRegularRoleLink').on(\n\t\t\t\t'click',\n\t\t\t\tfunction(event) {\n\t\t\t\t\tvar searchContainerName = '");
            if (_jspx_meth_portlet_namespace_1((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_0, _jspx_page_context))
              return;
            out.write("rolesSearchContainer';\n\n\t\t\t\t\tvar searchContainer = Liferay.SearchContainer.get(searchContainerName);\n\n\t\t\t\t\tvar searchContainerData = searchContainer.getData();\n\n\t\t\t\t\tif (!searchContainerData.length) {\n\t\t\t\t\t\tsearchContainerData = [];\n\t\t\t\t\t}\n\t\t\t\t\telse {\n\t\t\t\t\t\tsearchContainerData = searchContainerData.split(',');\n\t\t\t\t\t}\n\n\t\t\t\t\tLiferay.Util.selectEntity(\n\t\t\t\t\t\t{\n\t\t\t\t\t\t\tdialog: {\n\t\t\t\t\t\t\t\tconstrain: true,\n\t\t\t\t\t\t\t\tmodal: true\n\t\t\t\t\t\t\t},\n\n\t\t\t\t\t\t\t");

							String regularRoleEventName = liferayPortletResponse.getNamespace() + "selectRegularRole";
							
            out.write("\n\n\t\t\t\t\t\t\tid: '");
            out.print( regularRoleEventName );
            out.write("',\n\t\t\t\t\t\t\tselectedData: searchContainerData,\n\t\t\t\t\t\t\ttitle: '");
            if (_jspx_meth_liferay$1ui_message_1((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_0, _jspx_page_context))
              return;
            out.write("',\n\n\t\t\t\t\t\t\t");

							PortletURL selectRegularRoleURL = PortletProviderUtil.getPortletURL(request, Role.class.getName(), PortletProvider.Action.BROWSE);

							selectRegularRoleURL.setParameter("p_u_i_d", (selUser == null) ? "0" : String.valueOf(selUser.getUserId()));
							selectRegularRoleURL.setParameter("eventName", regularRoleEventName);
							selectRegularRoleURL.setParameter("syncEntitiesEventName", regularRoleSyncEntitiesEventName);
							selectRegularRoleURL.setWindowState(LiferayWindowState.POP_UP);
							
            out.write("\n\n\t\t\t\t\t\t\turi: '");
            out.print( selectRegularRoleURL.toString() );
            out.write("'\n\t\t\t\t\t\t},\n\t\t\t\t\t\tfunction(event) {\n\t\t\t\t\t\t\t");
            if (_jspx_meth_portlet_namespace_2((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_0, _jspx_page_context))
              return;
            out.write("selectRole(event.entityid, event.entityname, event.searchcontainername, event.groupdescriptivename, event.groupid, event.iconcssclass);\n\t\t\t\t\t\t}\n\t\t\t\t\t);\n\t\t\t\t}\n\t\t\t);\n\t\t");
            int evalDoAfterBody = _jspx_th_aui_script_0.doAfterBody();
            if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
              break;
          } while (true);
          if (_jspx_eval_aui_script_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
            out = _jspx_page_context.popBody();
        }
        if (_jspx_th_aui_script_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_aui_script.reuse(_jspx_th_aui_script_0);
          return;
        }
        _jspx_tagPool_aui_script.reuse(_jspx_th_aui_script_0);
        out.write('\n');
        out.write('	');
      }
      if (_jspx_th_c_if_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_2);
        return;
      }
      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_2);
      out.write("\n\n\t<h4 class=\"sheet-tertiary-title\">");
      if (_jspx_meth_liferay$1ui_message_2(_jspx_page_context))
        return;
      out.write("</h4>\n\n\t");
      //  c:if
      com.liferay.taglib.core.IfTag _jspx_th_c_if_3 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
      _jspx_th_c_if_3.setPageContext(_jspx_page_context);
      _jspx_th_c_if_3.setParent(null);
      _jspx_th_c_if_3.setTest( roleGroups.isEmpty() );
      int _jspx_eval_c_if_3 = _jspx_th_c_if_3.doStartTag();
      if (_jspx_eval_c_if_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        out.write("\n\t\t<div class=\"sheet-text\">");
        if (_jspx_meth_liferay$1ui_message_3((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_3, _jspx_page_context))
          return;
        out.write("</div>\n\t");
      }
      if (_jspx_th_c_if_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_3);
        return;
      }
      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_3);
      out.write("\n\n\t");
      //  liferay-ui:search-container
      com.liferay.taglib.ui.SearchContainerTag _jspx_th_liferay$1ui_search$1container_1 = (com.liferay.taglib.ui.SearchContainerTag) _jspx_tagPool_liferay$1ui_search$1container_total_iteratorURL_id_headerNames_curParam_cssClass.get(com.liferay.taglib.ui.SearchContainerTag.class);
      _jspx_th_liferay$1ui_search$1container_1.setPageContext(_jspx_page_context);
      _jspx_th_liferay$1ui_search$1container_1.setParent(null);
      _jspx_th_liferay$1ui_search$1container_1.setCssClass("lfr-search-container-inherited-regular-roles");
      _jspx_th_liferay$1ui_search$1container_1.setCurParam("inheritedRegularRolesCur");
      _jspx_th_liferay$1ui_search$1container_1.setHeaderNames("title,group");
      _jspx_th_liferay$1ui_search$1container_1.setId("inheritedRolesSearchContainer");
      _jspx_th_liferay$1ui_search$1container_1.setIteratorURL( currentURLObj );
      _jspx_th_liferay$1ui_search$1container_1.setTotal( roleGroups.size() );
      int _jspx_eval_liferay$1ui_search$1container_1 = _jspx_th_liferay$1ui_search$1container_1.doStartTag();
      if (_jspx_eval_liferay$1ui_search$1container_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        java.lang.Integer total = null;
        com.liferay.portal.kernel.dao.search.SearchContainer searchContainer = null;
        total = (java.lang.Integer) _jspx_page_context.findAttribute("total");
        searchContainer = (com.liferay.portal.kernel.dao.search.SearchContainer) _jspx_page_context.findAttribute("searchContainer");
        out.write("\n\t\t");
        //  liferay-ui:search-container-results
        java.util.List results = null;
        java.lang.Integer deprecatedTotal = null;
        com.liferay.taglib.ui.SearchContainerResultsTag _jspx_th_liferay$1ui_search$1container$1results_1 = (com.liferay.taglib.ui.SearchContainerResultsTag) _jspx_tagPool_liferay$1ui_search$1container$1results_results_nobody.get(com.liferay.taglib.ui.SearchContainerResultsTag.class);
        _jspx_th_liferay$1ui_search$1container$1results_1.setPageContext(_jspx_page_context);
        _jspx_th_liferay$1ui_search$1container$1results_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container_1);
        _jspx_th_liferay$1ui_search$1container$1results_1.setResults( roleGroups.subList(searchContainer.getStart(), searchContainer.getResultEnd()) );
        int _jspx_eval_liferay$1ui_search$1container$1results_1 = _jspx_th_liferay$1ui_search$1container$1results_1.doStartTag();
        results = (java.util.List) _jspx_page_context.findAttribute("results");
        deprecatedTotal = (java.lang.Integer) _jspx_page_context.findAttribute("deprecatedTotal");
        if (_jspx_th_liferay$1ui_search$1container$1results_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_liferay$1ui_search$1container$1results_results_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1results_1);
          return;
        }
        results = (java.util.List) _jspx_page_context.findAttribute("results");
        deprecatedTotal = (java.lang.Integer) _jspx_page_context.findAttribute("deprecatedTotal");
        _jspx_tagPool_liferay$1ui_search$1container$1results_results_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1results_1);
        out.write("\n\n\t\t");
        //  liferay-ui:search-container-row
        com.liferay.taglib.ui.SearchContainerRowTag _jspx_th_liferay$1ui_search$1container$1row_1 = (com.liferay.taglib.ui.SearchContainerRowTag) _jspx_tagPool_liferay$1ui_search$1container$1row_rowIdProperty_modelVar_keyProperty_className.get(com.liferay.taglib.ui.SearchContainerRowTag.class);
        _jspx_th_liferay$1ui_search$1container$1row_1.setPageContext(_jspx_page_context);
        _jspx_th_liferay$1ui_search$1container$1row_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container_1);
        _jspx_th_liferay$1ui_search$1container$1row_1.setClassName("com.liferay.portal.kernel.model.Group");
        _jspx_th_liferay$1ui_search$1container$1row_1.setKeyProperty("groupId");
        _jspx_th_liferay$1ui_search$1container$1row_1.setModelVar("group");
        _jspx_th_liferay$1ui_search$1container$1row_1.setRowIdProperty("friendlyURL");
        int _jspx_eval_liferay$1ui_search$1container$1row_1 = _jspx_th_liferay$1ui_search$1container$1row_1.doStartTag();
        if (_jspx_eval_liferay$1ui_search$1container$1row_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          java.lang.Integer index = null;
          com.liferay.portal.kernel.model.Group group = null;
          com.liferay.portal.kernel.dao.search.ResultRow row = null;
          if (_jspx_eval_liferay$1ui_search$1container$1row_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
            out = _jspx_page_context.pushBody();
            _jspx_th_liferay$1ui_search$1container$1row_1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
            _jspx_th_liferay$1ui_search$1container$1row_1.doInitBody();
          }
          index = (java.lang.Integer) _jspx_page_context.findAttribute("index");
          group = (com.liferay.portal.kernel.model.Group) _jspx_page_context.findAttribute("group");
          row = (com.liferay.portal.kernel.dao.search.ResultRow) _jspx_page_context.findAttribute("row");
          do {
            out.write("\n\n\t\t\t");

			List<Role> groupRoles = RoleLocalServiceUtil.getGroupRoles(group.getGroupId());
			
            out.write("\n\n\t\t\t");
            //  liferay-ui:search-container-column-text
            com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_2 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
            _jspx_th_liferay$1ui_search$1container$1column$1text_2.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1ui_search$1container$1column$1text_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1row_1);
            _jspx_th_liferay$1ui_search$1container$1column$1text_2.setName("title");
            _jspx_th_liferay$1ui_search$1container$1column$1text_2.setValue( HtmlUtil.escape(ListUtil.toString(groupRoles, Role.NAME_ACCESSOR)) );
            int _jspx_eval_liferay$1ui_search$1container$1column$1text_2 = _jspx_th_liferay$1ui_search$1container$1column$1text_2.doStartTag();
            if (_jspx_eval_liferay$1ui_search$1container$1column$1text_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              if (_jspx_eval_liferay$1ui_search$1container$1column$1text_2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                out = _jspx_page_context.pushBody();
                _jspx_th_liferay$1ui_search$1container$1column$1text_2.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                _jspx_th_liferay$1ui_search$1container$1column$1text_2.doInitBody();
              }
              do {
                out.write("\n\t\t\t\t");
                //  liferay-ui:icon
                com.liferay.taglib.ui.IconTag _jspx_th_liferay$1ui_icon_3 = (com.liferay.taglib.ui.IconTag) _jspx_tagPool_liferay$1ui_icon_message_label_iconCssClass_nobody.get(com.liferay.taglib.ui.IconTag.class);
                _jspx_th_liferay$1ui_icon_3.setPageContext(_jspx_page_context);
                _jspx_th_liferay$1ui_icon_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1column$1text_2);
                _jspx_th_liferay$1ui_icon_3.setIconCssClass( RolesAdminUtil.getIconCssClass(groupRoles.get(0)) );
                _jspx_th_liferay$1ui_icon_3.setLabel( true );
                _jspx_th_liferay$1ui_icon_3.setMessage( HtmlUtil.escape(ListUtil.toString(groupRoles, Role.NAME_ACCESSOR)) );
                int _jspx_eval_liferay$1ui_icon_3 = _jspx_th_liferay$1ui_icon_3.doStartTag();
                if (_jspx_th_liferay$1ui_icon_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_liferay$1ui_icon_message_label_iconCssClass_nobody.reuse(_jspx_th_liferay$1ui_icon_3);
                  return;
                }
                _jspx_tagPool_liferay$1ui_icon_message_label_iconCssClass_nobody.reuse(_jspx_th_liferay$1ui_icon_3);
                out.write("\n\t\t\t");
                int evalDoAfterBody = _jspx_th_liferay$1ui_search$1container$1column$1text_2.doAfterBody();
                if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                  break;
              } while (true);
              if (_jspx_eval_liferay$1ui_search$1container$1column$1text_2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                out = _jspx_page_context.popBody();
            }
            if (_jspx_th_liferay$1ui_search$1container$1column$1text_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_2);
              return;
            }
            _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_2);
            out.write("\n\n\t\t\t");
            //  liferay-ui:search-container-column-text
            com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_3 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_nobody.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
            _jspx_th_liferay$1ui_search$1container$1column$1text_3.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1ui_search$1container$1column$1text_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1row_1);
            _jspx_th_liferay$1ui_search$1container$1column$1text_3.setName("group");
            _jspx_th_liferay$1ui_search$1container$1column$1text_3.setValue( HtmlUtil.escape(group.getDescriptiveName(locale)) );
            int _jspx_eval_liferay$1ui_search$1container$1column$1text_3 = _jspx_th_liferay$1ui_search$1container$1column$1text_3.doStartTag();
            if (_jspx_th_liferay$1ui_search$1container$1column$1text_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_3);
              return;
            }
            _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_3);
            out.write("\n\t\t");
            int evalDoAfterBody = _jspx_th_liferay$1ui_search$1container$1row_1.doAfterBody();
            index = (java.lang.Integer) _jspx_page_context.findAttribute("index");
            group = (com.liferay.portal.kernel.model.Group) _jspx_page_context.findAttribute("group");
            row = (com.liferay.portal.kernel.dao.search.ResultRow) _jspx_page_context.findAttribute("row");
            if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
              break;
          } while (true);
          if (_jspx_eval_liferay$1ui_search$1container$1row_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
            out = _jspx_page_context.popBody();
        }
        if (_jspx_th_liferay$1ui_search$1container$1row_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_liferay$1ui_search$1container$1row_rowIdProperty_modelVar_keyProperty_className.reuse(_jspx_th_liferay$1ui_search$1container$1row_1);
          return;
        }
        _jspx_tagPool_liferay$1ui_search$1container$1row_rowIdProperty_modelVar_keyProperty_className.reuse(_jspx_th_liferay$1ui_search$1container$1row_1);
        out.write("\n\n\t\t");
        if (_jspx_meth_liferay$1ui_search$1iterator_1((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1ui_search$1container_1, _jspx_page_context))
          return;
        out.write('\n');
        out.write('	');
      }
      if (_jspx_th_liferay$1ui_search$1container_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_liferay$1ui_search$1container_total_iteratorURL_id_headerNames_curParam_cssClass.reuse(_jspx_th_liferay$1ui_search$1container_1);
        return;
      }
      _jspx_tagPool_liferay$1ui_search$1container_total_iteratorURL_id_headerNames_curParam_cssClass.reuse(_jspx_th_liferay$1ui_search$1container_1);
      out.write("\n</div>\n\n<div class=\"sheet-section\">\n\t<h3 class=\"sheet-subtitle\">\n\t\t<span class=\"autofit-padded-no-gutters autofit-row\">\n\t\t\t<span class=\"autofit-col autofit-col-expand\">\n\t\t\t\t<span class=\"heading-text\">\n\t\t\t\t\t");
      if (_jspx_meth_liferay$1ui_message_4(_jspx_page_context))
        return;
      out.write("\n\t\t\t\t</span>\n\t\t\t</span>\n\n\t\t\t");
      //  c:if
      com.liferay.taglib.core.IfTag _jspx_th_c_if_4 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
      _jspx_th_c_if_4.setPageContext(_jspx_page_context);
      _jspx_th_c_if_4.setParent(null);
      _jspx_th_c_if_4.setTest( !organizations.isEmpty() && !portletName.equals(myAccountPortletId) );
      int _jspx_eval_c_if_4 = _jspx_th_c_if_4.doStartTag();
      if (_jspx_eval_c_if_4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        out.write("\n\t\t\t\t<span class=\"autofit-col\">\n\t\t\t\t\t");
        //  liferay-ui:icon
        com.liferay.taglib.ui.IconTag _jspx_th_liferay$1ui_icon_4 = (com.liferay.taglib.ui.IconTag) _jspx_tagPool_liferay$1ui_icon_url_method_message_linkCssClass_label_id_cssClass_nobody.get(com.liferay.taglib.ui.IconTag.class);
        _jspx_th_liferay$1ui_icon_4.setPageContext(_jspx_page_context);
        _jspx_th_liferay$1ui_icon_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_4);
        _jspx_th_liferay$1ui_icon_4.setCssClass("modify-link");
        _jspx_th_liferay$1ui_icon_4.setId("selectOrganizationRoleLink");
        _jspx_th_liferay$1ui_icon_4.setLabel( true );
        _jspx_th_liferay$1ui_icon_4.setLinkCssClass("btn btn-secondary btn-sm");
        _jspx_th_liferay$1ui_icon_4.setMessage("select");
        _jspx_th_liferay$1ui_icon_4.setMethod("get");
        _jspx_th_liferay$1ui_icon_4.setUrl("javascript:;");
        int _jspx_eval_liferay$1ui_icon_4 = _jspx_th_liferay$1ui_icon_4.doStartTag();
        if (_jspx_th_liferay$1ui_icon_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_liferay$1ui_icon_url_method_message_linkCssClass_label_id_cssClass_nobody.reuse(_jspx_th_liferay$1ui_icon_4);
          return;
        }
        _jspx_tagPool_liferay$1ui_icon_url_method_message_linkCssClass_label_id_cssClass_nobody.reuse(_jspx_th_liferay$1ui_icon_4);
        out.write("\n\t\t\t\t</span>\n\t\t\t");
      }
      if (_jspx_th_c_if_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_4);
        return;
      }
      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_4);
      out.write("\n\t\t</span>\n\t</h3>\n\n\t");
      //  c:if
      com.liferay.taglib.core.IfTag _jspx_th_c_if_5 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
      _jspx_th_c_if_5.setPageContext(_jspx_page_context);
      _jspx_th_c_if_5.setParent(null);
      _jspx_th_c_if_5.setTest( organizations.isEmpty() && organizationRoles.isEmpty() );
      int _jspx_eval_c_if_5 = _jspx_th_c_if_5.doStartTag();
      if (_jspx_eval_c_if_5 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        out.write("\n\t\t<div class=\"sheet-text\">");
        if (_jspx_meth_liferay$1ui_message_5((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_5, _jspx_page_context))
          return;
        out.write("</div>\n\t");
      }
      if (_jspx_th_c_if_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_5);
        return;
      }
      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_5);
      out.write("\n\n\t");
      //  c:if
      com.liferay.taglib.core.IfTag _jspx_th_c_if_6 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
      _jspx_th_c_if_6.setPageContext(_jspx_page_context);
      _jspx_th_c_if_6.setParent(null);
      _jspx_th_c_if_6.setTest( !organizations.isEmpty() );
      int _jspx_eval_c_if_6 = _jspx_th_c_if_6.doStartTag();
      if (_jspx_eval_c_if_6 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        out.write("\n\t\t");
        //  liferay-ui:search-container
        com.liferay.taglib.ui.SearchContainerTag _jspx_th_liferay$1ui_search$1container_2 = (com.liferay.taglib.ui.SearchContainerTag) _jspx_tagPool_liferay$1ui_search$1container_total_iteratorURL_id_headerNames_curParam_cssClass.get(com.liferay.taglib.ui.SearchContainerTag.class);
        _jspx_th_liferay$1ui_search$1container_2.setPageContext(_jspx_page_context);
        _jspx_th_liferay$1ui_search$1container_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_6);
        _jspx_th_liferay$1ui_search$1container_2.setCssClass("lfr-search-container-organization-roles");
        _jspx_th_liferay$1ui_search$1container_2.setCurParam("organizationRolesCur");
        _jspx_th_liferay$1ui_search$1container_2.setHeaderNames("title,organization,null");
        _jspx_th_liferay$1ui_search$1container_2.setId("organizationRolesSearchContainer");
        _jspx_th_liferay$1ui_search$1container_2.setIteratorURL( currentURLObj );
        _jspx_th_liferay$1ui_search$1container_2.setTotal( organizationRoles.size() );
        int _jspx_eval_liferay$1ui_search$1container_2 = _jspx_th_liferay$1ui_search$1container_2.doStartTag();
        if (_jspx_eval_liferay$1ui_search$1container_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          java.lang.Integer total = null;
          com.liferay.portal.kernel.dao.search.SearchContainer searchContainer = null;
          total = (java.lang.Integer) _jspx_page_context.findAttribute("total");
          searchContainer = (com.liferay.portal.kernel.dao.search.SearchContainer) _jspx_page_context.findAttribute("searchContainer");
          out.write("\n\t\t\t");
          //  liferay-ui:search-container-results
          java.util.List results = null;
          java.lang.Integer deprecatedTotal = null;
          com.liferay.taglib.ui.SearchContainerResultsTag _jspx_th_liferay$1ui_search$1container$1results_2 = (com.liferay.taglib.ui.SearchContainerResultsTag) _jspx_tagPool_liferay$1ui_search$1container$1results_results_nobody.get(com.liferay.taglib.ui.SearchContainerResultsTag.class);
          _jspx_th_liferay$1ui_search$1container$1results_2.setPageContext(_jspx_page_context);
          _jspx_th_liferay$1ui_search$1container$1results_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container_2);
          _jspx_th_liferay$1ui_search$1container$1results_2.setResults( organizationRoles.subList(searchContainer.getStart(), searchContainer.getResultEnd()) );
          int _jspx_eval_liferay$1ui_search$1container$1results_2 = _jspx_th_liferay$1ui_search$1container$1results_2.doStartTag();
          results = (java.util.List) _jspx_page_context.findAttribute("results");
          deprecatedTotal = (java.lang.Integer) _jspx_page_context.findAttribute("deprecatedTotal");
          if (_jspx_th_liferay$1ui_search$1container$1results_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_liferay$1ui_search$1container$1results_results_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1results_2);
            return;
          }
          results = (java.util.List) _jspx_page_context.findAttribute("results");
          deprecatedTotal = (java.lang.Integer) _jspx_page_context.findAttribute("deprecatedTotal");
          _jspx_tagPool_liferay$1ui_search$1container$1results_results_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1results_2);
          out.write("\n\n\t\t\t");
          //  liferay-ui:search-container-row
          com.liferay.taglib.ui.SearchContainerRowTag _jspx_th_liferay$1ui_search$1container$1row_2 = (com.liferay.taglib.ui.SearchContainerRowTag) _jspx_tagPool_liferay$1ui_search$1container$1row_modelVar_keyProperty_className.get(com.liferay.taglib.ui.SearchContainerRowTag.class);
          _jspx_th_liferay$1ui_search$1container$1row_2.setPageContext(_jspx_page_context);
          _jspx_th_liferay$1ui_search$1container$1row_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container_2);
          _jspx_th_liferay$1ui_search$1container$1row_2.setClassName("com.liferay.portal.kernel.model.UserGroupRole");
          _jspx_th_liferay$1ui_search$1container$1row_2.setKeyProperty("roleId");
          _jspx_th_liferay$1ui_search$1container$1row_2.setModelVar("userGroupRole");
          int _jspx_eval_liferay$1ui_search$1container$1row_2 = _jspx_th_liferay$1ui_search$1container$1row_2.doStartTag();
          if (_jspx_eval_liferay$1ui_search$1container$1row_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            java.lang.Integer index = null;
            com.liferay.portal.kernel.model.UserGroupRole userGroupRole = null;
            com.liferay.portal.kernel.dao.search.ResultRow row = null;
            if (_jspx_eval_liferay$1ui_search$1container$1row_2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
              out = _jspx_page_context.pushBody();
              _jspx_th_liferay$1ui_search$1container$1row_2.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
              _jspx_th_liferay$1ui_search$1container$1row_2.doInitBody();
            }
            index = (java.lang.Integer) _jspx_page_context.findAttribute("index");
            userGroupRole = (com.liferay.portal.kernel.model.UserGroupRole) _jspx_page_context.findAttribute("userGroupRole");
            row = (com.liferay.portal.kernel.dao.search.ResultRow) _jspx_page_context.findAttribute("row");
            do {
              out.write("\n\t\t\t\t");
              //  liferay-ui:search-container-column-text
              com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_4 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text_name_cssClass.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
              _jspx_th_liferay$1ui_search$1container$1column$1text_4.setPageContext(_jspx_page_context);
              _jspx_th_liferay$1ui_search$1container$1column$1text_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1row_2);
              _jspx_th_liferay$1ui_search$1container$1column$1text_4.setCssClass("table-cell-content");
              _jspx_th_liferay$1ui_search$1container$1column$1text_4.setName("title");
              int _jspx_eval_liferay$1ui_search$1container$1column$1text_4 = _jspx_th_liferay$1ui_search$1container$1column$1text_4.doStartTag();
              if (_jspx_eval_liferay$1ui_search$1container$1column$1text_4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                if (_jspx_eval_liferay$1ui_search$1container$1column$1text_4 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                  out = _jspx_page_context.pushBody();
                  _jspx_th_liferay$1ui_search$1container$1column$1text_4.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                  _jspx_th_liferay$1ui_search$1container$1column$1text_4.doInitBody();
                }
                do {
                  out.write("\n\t\t\t\t\t");
                  //  liferay-ui:icon
                  com.liferay.taglib.ui.IconTag _jspx_th_liferay$1ui_icon_5 = (com.liferay.taglib.ui.IconTag) _jspx_tagPool_liferay$1ui_icon_message_label_iconCssClass_nobody.get(com.liferay.taglib.ui.IconTag.class);
                  _jspx_th_liferay$1ui_icon_5.setPageContext(_jspx_page_context);
                  _jspx_th_liferay$1ui_icon_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1column$1text_4);
                  _jspx_th_liferay$1ui_icon_5.setIconCssClass( RolesAdminUtil.getIconCssClass(userGroupRole.getRole()) );
                  _jspx_th_liferay$1ui_icon_5.setLabel( true );
                  _jspx_th_liferay$1ui_icon_5.setMessage( HtmlUtil.escape(userGroupRole.getRole().getTitle(locale)) );
                  int _jspx_eval_liferay$1ui_icon_5 = _jspx_th_liferay$1ui_icon_5.doStartTag();
                  if (_jspx_th_liferay$1ui_icon_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_liferay$1ui_icon_message_label_iconCssClass_nobody.reuse(_jspx_th_liferay$1ui_icon_5);
                    return;
                  }
                  _jspx_tagPool_liferay$1ui_icon_message_label_iconCssClass_nobody.reuse(_jspx_th_liferay$1ui_icon_5);
                  out.write("\n\t\t\t\t");
                  int evalDoAfterBody = _jspx_th_liferay$1ui_search$1container$1column$1text_4.doAfterBody();
                  if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                    break;
                } while (true);
                if (_jspx_eval_liferay$1ui_search$1container$1column$1text_4 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                  out = _jspx_page_context.popBody();
              }
              if (_jspx_th_liferay$1ui_search$1container$1column$1text_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_liferay$1ui_search$1container$1column$1text_name_cssClass.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_4);
                return;
              }
              _jspx_tagPool_liferay$1ui_search$1container$1column$1text_name_cssClass.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_4);
              out.write("\n\n\t\t\t\t");
              //  liferay-ui:search-container-column-text
              com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_5 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_cssClass_nobody.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
              _jspx_th_liferay$1ui_search$1container$1column$1text_5.setPageContext(_jspx_page_context);
              _jspx_th_liferay$1ui_search$1container$1column$1text_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1row_2);
              _jspx_th_liferay$1ui_search$1container$1column$1text_5.setCssClass("table-cell-content");
              _jspx_th_liferay$1ui_search$1container$1column$1text_5.setName("organization");
              _jspx_th_liferay$1ui_search$1container$1column$1text_5.setValue( HtmlUtil.escape(userGroupRole.getGroup().getDescriptiveName(locale)) );
              int _jspx_eval_liferay$1ui_search$1container$1column$1text_5 = _jspx_th_liferay$1ui_search$1container$1column$1text_5.doStartTag();
              if (_jspx_th_liferay$1ui_search$1container$1column$1text_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_cssClass_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_5);
                return;
              }
              _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_cssClass_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_5);
              out.write("\n\n\t\t\t\t");

				boolean membershipProtected = false;

				Group group = userGroupRole.getGroup();

				Role role = userGroupRole.getRole();

				if (role.getType() == RoleConstants.TYPE_ORGANIZATION) {
					membershipProtected = OrganizationMembershipPolicyUtil.isMembershipProtected(permissionChecker, userGroupRole.getUserId(), group.getOrganizationId());
				}
				else {
					membershipProtected = SiteMembershipPolicyUtil.isMembershipProtected(permissionChecker, userGroupRole.getUserId(), group.getGroupId());
				}
				
              out.write("\n\n\t\t\t\t");
              //  c:if
              com.liferay.taglib.core.IfTag _jspx_th_c_if_7 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
              _jspx_th_c_if_7.setPageContext(_jspx_page_context);
              _jspx_th_c_if_7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1row_2);
              _jspx_th_c_if_7.setTest( !portletName.equals(myAccountPortletId) && !membershipProtected );
              int _jspx_eval_c_if_7 = _jspx_th_c_if_7.doStartTag();
              if (_jspx_eval_c_if_7 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\t\t\t\t\t");
                //  liferay-ui:search-container-column-text
                com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_6 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
                _jspx_th_liferay$1ui_search$1container$1column$1text_6.setPageContext(_jspx_page_context);
                _jspx_th_liferay$1ui_search$1container$1column$1text_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_7);
                int _jspx_eval_liferay$1ui_search$1container$1column$1text_6 = _jspx_th_liferay$1ui_search$1container$1column$1text_6.doStartTag();
                if (_jspx_eval_liferay$1ui_search$1container$1column$1text_6 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  if (_jspx_eval_liferay$1ui_search$1container$1column$1text_6 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                    out = _jspx_page_context.pushBody();
                    _jspx_th_liferay$1ui_search$1container$1column$1text_6.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                    _jspx_th_liferay$1ui_search$1container$1column$1text_6.doInitBody();
                  }
                  do {
                    out.write("\n\t\t\t\t\t\t<a class=\"modify-link\" data-groupId=\"");
                    out.print( userGroupRole.getGroupId() );
                    out.write("\" data-rowId=\"");
                    out.print( userGroupRole.getRoleId() );
                    out.write("\" href=\"javascript:;\">");
                    out.print( removeRoleIcon );
                    out.write("</a>\n\t\t\t\t\t");
                    int evalDoAfterBody = _jspx_th_liferay$1ui_search$1container$1column$1text_6.doAfterBody();
                    if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                      break;
                  } while (true);
                  if (_jspx_eval_liferay$1ui_search$1container$1column$1text_6 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                    out = _jspx_page_context.popBody();
                }
                if (_jspx_th_liferay$1ui_search$1container$1column$1text_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_liferay$1ui_search$1container$1column$1text.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_6);
                  return;
                }
                _jspx_tagPool_liferay$1ui_search$1container$1column$1text.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_6);
                out.write("\n\t\t\t\t");
              }
              if (_jspx_th_c_if_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_7);
                return;
              }
              _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_7);
              out.write("\n\t\t\t");
              int evalDoAfterBody = _jspx_th_liferay$1ui_search$1container$1row_2.doAfterBody();
              index = (java.lang.Integer) _jspx_page_context.findAttribute("index");
              userGroupRole = (com.liferay.portal.kernel.model.UserGroupRole) _jspx_page_context.findAttribute("userGroupRole");
              row = (com.liferay.portal.kernel.dao.search.ResultRow) _jspx_page_context.findAttribute("row");
              if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                break;
            } while (true);
            if (_jspx_eval_liferay$1ui_search$1container$1row_2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
              out = _jspx_page_context.popBody();
          }
          if (_jspx_th_liferay$1ui_search$1container$1row_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_liferay$1ui_search$1container$1row_modelVar_keyProperty_className.reuse(_jspx_th_liferay$1ui_search$1container$1row_2);
            return;
          }
          _jspx_tagPool_liferay$1ui_search$1container$1row_modelVar_keyProperty_className.reuse(_jspx_th_liferay$1ui_search$1container$1row_2);
          out.write("\n\n\t\t\t");
          if (_jspx_meth_liferay$1ui_search$1iterator_2((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1ui_search$1container_2, _jspx_page_context))
            return;
          out.write("\n\t\t");
        }
        if (_jspx_th_liferay$1ui_search$1container_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_liferay$1ui_search$1container_total_iteratorURL_id_headerNames_curParam_cssClass.reuse(_jspx_th_liferay$1ui_search$1container_2);
          return;
        }
        _jspx_tagPool_liferay$1ui_search$1container_total_iteratorURL_id_headerNames_curParam_cssClass.reuse(_jspx_th_liferay$1ui_search$1container_2);
        out.write("\n\n\t\t");
        //  c:if
        com.liferay.taglib.core.IfTag _jspx_th_c_if_8 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
        _jspx_th_c_if_8.setPageContext(_jspx_page_context);
        _jspx_th_c_if_8.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_6);
        _jspx_th_c_if_8.setTest( !portletName.equals(myAccountPortletId) );
        int _jspx_eval_c_if_8 = _jspx_th_c_if_8.doStartTag();
        if (_jspx_eval_c_if_8 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          out.write("\n\t\t\t");
          //  aui:script
          com.liferay.taglib.aui.ScriptTag _jspx_th_aui_script_1 = (com.liferay.taglib.aui.ScriptTag) _jspx_tagPool_aui_script_use.get(com.liferay.taglib.aui.ScriptTag.class);
          _jspx_th_aui_script_1.setPageContext(_jspx_page_context);
          _jspx_th_aui_script_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_8);
          _jspx_th_aui_script_1.setUse("liferay-search-container");
          int _jspx_eval_aui_script_1 = _jspx_th_aui_script_1.doStartTag();
          if (_jspx_eval_aui_script_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            if (_jspx_eval_aui_script_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
              out = _jspx_page_context.pushBody();
              _jspx_th_aui_script_1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
              _jspx_th_aui_script_1.doInitBody();
            }
            do {
              out.write("\n\t\t\t\tvar Util = Liferay.Util;\n\n\t\t\t\tvar searchContainer = Liferay.SearchContainer.get('");
              if (_jspx_meth_portlet_namespace_3((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
                return;
              out.write("organizationRolesSearchContainer');\n\n\t\t\t\tvar searchContainerContentBox = searchContainer.get('contentBox');\n\n\t\t\t\tsearchContainerContentBox.delegate(\n\t\t\t\t\t'click',\n\t\t\t\t\tfunction(event) {\n\t\t\t\t\t\tvar link = event.currentTarget;\n\t\t\t\t\t\tvar tr = link.ancestor('tr');\n\n\t\t\t\t\t\tvar groupId = link.getAttribute('data-groupId');\n\t\t\t\t\t\tvar rowId = link.getAttribute('data-rowId');\n\n\t\t\t\t\t\tvar selectOrganizationRole = Util.getWindow('");
              if (_jspx_meth_portlet_namespace_4((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
                return;
              out.write("selectOrganizationRole');\n\n\t\t\t\t\t\tif (selectOrganizationRole) {\n\t\t\t\t\t\t\tvar selectButton = selectOrganizationRole.iframe.node.get('contentWindow.document').one('.selector-button[data-groupid=\"' + groupId + '\"][data-entityid=\"' + rowId + '\"]');\n\n\t\t\t\t\t\t\tUtil.toggleDisabled(selectButton, false);\n\t\t\t\t\t\t}\n\n\t\t\t\t\t\tsearchContainer.deleteRow(tr, rowId);\n\n\t\t\t\t\t\t");
              if (_jspx_meth_portlet_namespace_5((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
                return;
              out.write("deleteGroupRole(rowId, groupId);\n\t\t\t\t\t},\n\t\t\t\t\t'.modify-link'\n\t\t\t\t);\n\n\t\t\t\tLiferay.on(\n\t\t\t\t\t'");
              out.print( organizationRoleSyncEntitiesEventName );
              out.write("',\n\t\t\t\t\tfunction(event) {\n\t\t\t\t\t\tevent.selectors.each(\n\t\t\t\t\t\t\tfunction(item, index, collection) {\n\t\t\t\t\t\t\t\tvar groupId = item.attr('data-groupid');\n\t\t\t\t\t\t\t\tvar roleId = item.attr('data-entityid');\n\n\t\t\t\t\t\t\t\tfor (var i = 0; i < ");
              if (_jspx_meth_portlet_namespace_6((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
                return;
              out.write("addGroupRolesGroupIds.length; i++) {\n\t\t\t\t\t\t\t\t\tif ((");
              if (_jspx_meth_portlet_namespace_7((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
                return;
              out.write("addGroupRolesGroupIds[i] == groupId) && (");
              if (_jspx_meth_portlet_namespace_8((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
                return;
              out.write("addGroupRolesRoleIds[i] == roleId)) {\n\t\t\t\t\t\t\t\t\t\tUtil.toggleDisabled(item, true);\n\n\t\t\t\t\t\t\t\t\t\tbreak;\n\t\t\t\t\t\t\t\t\t}\n\t\t\t\t\t\t\t\t}\n\n\t\t\t\t\t\t\t\tfor (var i = 0; i < ");
              if (_jspx_meth_portlet_namespace_9((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
                return;
              out.write("deleteGroupRolesGroupIds.length; i++) {\n\t\t\t\t\t\t\t\t\tif ((");
              if (_jspx_meth_portlet_namespace_10((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
                return;
              out.write("deleteGroupRolesGroupIds[i] == groupId) && (");
              if (_jspx_meth_portlet_namespace_11((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
                return;
              out.write("deleteGroupRolesRoleIds[i] == roleId)) {\n\t\t\t\t\t\t\t\t\t\tUtil.toggleDisabled(item, false);\n\n\t\t\t\t\t\t\t\t\t\tbreak;\n\t\t\t\t\t\t\t\t\t}\n\t\t\t\t\t\t\t\t}\n\t\t\t\t\t\t\t}\n\t\t\t\t\t\t);\n\t\t\t\t\t}\n\t\t\t\t);\n\t\t\t");
              int evalDoAfterBody = _jspx_th_aui_script_1.doAfterBody();
              if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                break;
            } while (true);
            if (_jspx_eval_aui_script_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
              out = _jspx_page_context.popBody();
          }
          if (_jspx_th_aui_script_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_aui_script_use.reuse(_jspx_th_aui_script_1);
            return;
          }
          _jspx_tagPool_aui_script_use.reuse(_jspx_th_aui_script_1);
          out.write("\n\t\t");
        }
        if (_jspx_th_c_if_8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_8);
          return;
        }
        _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_8);
        out.write('\n');
        out.write('	');
      }
      if (_jspx_th_c_if_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_6);
        return;
      }
      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_6);
      out.write("\n\n\t");
      //  c:if
      com.liferay.taglib.core.IfTag _jspx_th_c_if_9 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
      _jspx_th_c_if_9.setPageContext(_jspx_page_context);
      _jspx_th_c_if_9.setParent(null);
      _jspx_th_c_if_9.setTest( !organizations.isEmpty() && !portletName.equals(myAccountPortletId) );
      int _jspx_eval_c_if_9 = _jspx_th_c_if_9.doStartTag();
      if (_jspx_eval_c_if_9 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        out.write("\n\t\t");
        //  aui:script
        com.liferay.taglib.aui.ScriptTag _jspx_th_aui_script_2 = (com.liferay.taglib.aui.ScriptTag) _jspx_tagPool_aui_script.get(com.liferay.taglib.aui.ScriptTag.class);
        _jspx_th_aui_script_2.setPageContext(_jspx_page_context);
        _jspx_th_aui_script_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_9);
        int _jspx_eval_aui_script_2 = _jspx_th_aui_script_2.doStartTag();
        if (_jspx_eval_aui_script_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          if (_jspx_eval_aui_script_2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
            out = _jspx_page_context.pushBody();
            _jspx_th_aui_script_2.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
            _jspx_th_aui_script_2.doInitBody();
          }
          do {
            out.write("\n\t\t\tAUI.$('#");
            if (_jspx_meth_portlet_namespace_12((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_2, _jspx_page_context))
              return;
            out.write("selectOrganizationRoleLink').on(\n\t\t\t\t'click',\n\t\t\t\tfunction(event) {\n\t\t\t\t\tLiferay.Util.selectEntity(\n\t\t\t\t\t\t{\n\t\t\t\t\t\t\tdialog: {\n\t\t\t\t\t\t\t\tconstrain: true,\n\t\t\t\t\t\t\t\tmodal: true\n\t\t\t\t\t\t\t},\n\n\t\t\t\t\t\t\t");

							String organizationRoleEventName = liferayPortletResponse.getNamespace() + "selectOrganizationRole";
							
            out.write("\n\n\t\t\t\t\t\t\tid: '");
            out.print( organizationRoleEventName );
            out.write("',\n\t\t\t\t\t\t\tselectedData: [],\n\t\t\t\t\t\t\ttitle: '");
            if (_jspx_meth_liferay$1ui_message_6((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_2, _jspx_page_context))
              return;
            out.write("',\n\n\t\t\t\t\t\t\t");

							PortletURL selectOrganizationRoleURL = PortletProviderUtil.getPortletURL(request, Role.class.getName(), PortletProvider.Action.BROWSE);

							selectOrganizationRoleURL.setParameter("p_u_i_d", (selUser == null) ? "0" : String.valueOf(selUser.getUserId()));
							selectOrganizationRoleURL.setParameter("step", "1");
							selectOrganizationRoleURL.setParameter("roleType", String.valueOf(RoleConstants.TYPE_ORGANIZATION));
							selectOrganizationRoleURL.setParameter("organizationIds", StringUtil.merge(organizationIds));
							selectOrganizationRoleURL.setParameter("eventName", organizationRoleEventName);
							selectOrganizationRoleURL.setParameter("syncEntitiesEventName", organizationRoleSyncEntitiesEventName);
							selectOrganizationRoleURL.setWindowState(LiferayWindowState.POP_UP);
							
            out.write("\n\n\t\t\t\t\t\t\turi: '");
            out.print( selectOrganizationRoleURL.toString() );
            out.write("'\n\t\t\t\t\t\t},\n\t\t\t\t\t\tfunction(event) {\n\t\t\t\t\t\t\t");
            if (_jspx_meth_portlet_namespace_13((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_2, _jspx_page_context))
              return;
            out.write("selectRole(event.entityid, event.entityname, event.searchcontainername, event.groupdescriptivename, event.groupid, event.iconcssclass);\n\t\t\t\t\t\t}\n\t\t\t\t\t);\n\t\t\t\t}\n\t\t\t);\n\t\t");
            int evalDoAfterBody = _jspx_th_aui_script_2.doAfterBody();
            if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
              break;
          } while (true);
          if (_jspx_eval_aui_script_2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
            out = _jspx_page_context.popBody();
        }
        if (_jspx_th_aui_script_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_aui_script.reuse(_jspx_th_aui_script_2);
          return;
        }
        _jspx_tagPool_aui_script.reuse(_jspx_th_aui_script_2);
        out.write('\n');
        out.write('	');
      }
      if (_jspx_th_c_if_9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_9);
        return;
      }
      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_9);
      out.write("\n</div>\n\n<div class=\"sheet-section\">\n\t<h3 class=\"sheet-subtitle\">\n\t\t<span class=\"autofit-padded-no-gutters autofit-row\">\n\t\t\t<span class=\"autofit-col autofit-col-expand\">\n\t\t\t\t<span class=\"heading-text\">\n\t\t\t\t\t");
      if (_jspx_meth_liferay$1ui_message_7(_jspx_page_context))
        return;
      out.write("\n\t\t\t\t</span>\n\t\t\t</span>\n\n\t\t\t");
      //  c:if
      com.liferay.taglib.core.IfTag _jspx_th_c_if_10 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
      _jspx_th_c_if_10.setPageContext(_jspx_page_context);
      _jspx_th_c_if_10.setParent(null);
      _jspx_th_c_if_10.setTest( !groups.isEmpty() && !portletName.equals(myAccountPortletId) );
      int _jspx_eval_c_if_10 = _jspx_th_c_if_10.doStartTag();
      if (_jspx_eval_c_if_10 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        out.write("\n\t\t\t\t<span class=\"autofit-col\">\n\t\t\t\t\t");
        //  liferay-ui:icon
        com.liferay.taglib.ui.IconTag _jspx_th_liferay$1ui_icon_6 = (com.liferay.taglib.ui.IconTag) _jspx_tagPool_liferay$1ui_icon_url_method_message_linkCssClass_label_id_cssClass_nobody.get(com.liferay.taglib.ui.IconTag.class);
        _jspx_th_liferay$1ui_icon_6.setPageContext(_jspx_page_context);
        _jspx_th_liferay$1ui_icon_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_10);
        _jspx_th_liferay$1ui_icon_6.setCssClass("modify-link");
        _jspx_th_liferay$1ui_icon_6.setId("selectSiteRoleLink");
        _jspx_th_liferay$1ui_icon_6.setLabel( true );
        _jspx_th_liferay$1ui_icon_6.setLinkCssClass("btn btn-secondary btn-sm");
        _jspx_th_liferay$1ui_icon_6.setMessage("select");
        _jspx_th_liferay$1ui_icon_6.setMethod("get");
        _jspx_th_liferay$1ui_icon_6.setUrl("javascript:;");
        int _jspx_eval_liferay$1ui_icon_6 = _jspx_th_liferay$1ui_icon_6.doStartTag();
        if (_jspx_th_liferay$1ui_icon_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_liferay$1ui_icon_url_method_message_linkCssClass_label_id_cssClass_nobody.reuse(_jspx_th_liferay$1ui_icon_6);
          return;
        }
        _jspx_tagPool_liferay$1ui_icon_url_method_message_linkCssClass_label_id_cssClass_nobody.reuse(_jspx_th_liferay$1ui_icon_6);
        out.write("\n\t\t\t\t</span>\n\t\t\t");
      }
      if (_jspx_th_c_if_10.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_10);
        return;
      }
      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_10);
      out.write("\n\t\t</span>\n\t</h3>\n\n\t");
      //  c:choose
      com.liferay.taglib.core.ChooseTag _jspx_th_c_choose_0 = (com.liferay.taglib.core.ChooseTag) _jspx_tagPool_c_choose.get(com.liferay.taglib.core.ChooseTag.class);
      _jspx_th_c_choose_0.setPageContext(_jspx_page_context);
      _jspx_th_c_choose_0.setParent(null);
      int _jspx_eval_c_choose_0 = _jspx_th_c_choose_0.doStartTag();
      if (_jspx_eval_c_choose_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        out.write("\n\t\t");
        //  c:when
        com.liferay.taglib.core.WhenTag _jspx_th_c_when_0 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
        _jspx_th_c_when_0.setPageContext(_jspx_page_context);
        _jspx_th_c_when_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_0);
        _jspx_th_c_when_0.setTest( groups.isEmpty() );
        int _jspx_eval_c_when_0 = _jspx_th_c_when_0.doStartTag();
        if (_jspx_eval_c_when_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          out.write("\n\t\t\t<div class=\"sheet-text\">");
          if (_jspx_meth_liferay$1ui_message_8((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_when_0, _jspx_page_context))
            return;
          out.write("</div>\n\t\t");
        }
        if (_jspx_th_c_when_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_0);
          return;
        }
        _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_0);
        out.write("\n\t\t");
        //  c:otherwise
        com.liferay.taglib.core.OtherwiseTag _jspx_th_c_otherwise_0 = (com.liferay.taglib.core.OtherwiseTag) _jspx_tagPool_c_otherwise.get(com.liferay.taglib.core.OtherwiseTag.class);
        _jspx_th_c_otherwise_0.setPageContext(_jspx_page_context);
        _jspx_th_c_otherwise_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_0);
        int _jspx_eval_c_otherwise_0 = _jspx_th_c_otherwise_0.doStartTag();
        if (_jspx_eval_c_otherwise_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          out.write("\n\t\t\t");
          //  liferay-ui:search-container
          com.liferay.taglib.ui.SearchContainerTag _jspx_th_liferay$1ui_search$1container_3 = (com.liferay.taglib.ui.SearchContainerTag) _jspx_tagPool_liferay$1ui_search$1container_total_iteratorURL_id_headerNames_curParam_cssClass.get(com.liferay.taglib.ui.SearchContainerTag.class);
          _jspx_th_liferay$1ui_search$1container_3.setPageContext(_jspx_page_context);
          _jspx_th_liferay$1ui_search$1container_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_0);
          _jspx_th_liferay$1ui_search$1container_3.setCssClass("lfr-search-container-site-roles");
          _jspx_th_liferay$1ui_search$1container_3.setCurParam("siteRolesCur");
          _jspx_th_liferay$1ui_search$1container_3.setHeaderNames("title,site,null");
          _jspx_th_liferay$1ui_search$1container_3.setId("siteRolesSearchContainer");
          _jspx_th_liferay$1ui_search$1container_3.setIteratorURL( currentURLObj );
          _jspx_th_liferay$1ui_search$1container_3.setTotal( siteRoles.size() );
          int _jspx_eval_liferay$1ui_search$1container_3 = _jspx_th_liferay$1ui_search$1container_3.doStartTag();
          if (_jspx_eval_liferay$1ui_search$1container_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            java.lang.Integer total = null;
            com.liferay.portal.kernel.dao.search.SearchContainer searchContainer = null;
            total = (java.lang.Integer) _jspx_page_context.findAttribute("total");
            searchContainer = (com.liferay.portal.kernel.dao.search.SearchContainer) _jspx_page_context.findAttribute("searchContainer");
            out.write("\n\t\t\t\t");
            //  liferay-ui:search-container-results
            java.util.List results = null;
            java.lang.Integer deprecatedTotal = null;
            com.liferay.taglib.ui.SearchContainerResultsTag _jspx_th_liferay$1ui_search$1container$1results_3 = (com.liferay.taglib.ui.SearchContainerResultsTag) _jspx_tagPool_liferay$1ui_search$1container$1results_results_nobody.get(com.liferay.taglib.ui.SearchContainerResultsTag.class);
            _jspx_th_liferay$1ui_search$1container$1results_3.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1ui_search$1container$1results_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container_3);
            _jspx_th_liferay$1ui_search$1container$1results_3.setResults( siteRoles.subList(searchContainer.getStart(), searchContainer.getResultEnd()) );
            int _jspx_eval_liferay$1ui_search$1container$1results_3 = _jspx_th_liferay$1ui_search$1container$1results_3.doStartTag();
            results = (java.util.List) _jspx_page_context.findAttribute("results");
            deprecatedTotal = (java.lang.Integer) _jspx_page_context.findAttribute("deprecatedTotal");
            if (_jspx_th_liferay$1ui_search$1container$1results_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_liferay$1ui_search$1container$1results_results_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1results_3);
              return;
            }
            results = (java.util.List) _jspx_page_context.findAttribute("results");
            deprecatedTotal = (java.lang.Integer) _jspx_page_context.findAttribute("deprecatedTotal");
            _jspx_tagPool_liferay$1ui_search$1container$1results_results_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1results_3);
            out.write("\n\n\t\t\t\t");
            //  liferay-ui:search-container-row
            com.liferay.taglib.ui.SearchContainerRowTag _jspx_th_liferay$1ui_search$1container$1row_3 = (com.liferay.taglib.ui.SearchContainerRowTag) _jspx_tagPool_liferay$1ui_search$1container$1row_modelVar_keyProperty_className.get(com.liferay.taglib.ui.SearchContainerRowTag.class);
            _jspx_th_liferay$1ui_search$1container$1row_3.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1ui_search$1container$1row_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container_3);
            _jspx_th_liferay$1ui_search$1container$1row_3.setClassName("com.liferay.portal.kernel.model.UserGroupRole");
            _jspx_th_liferay$1ui_search$1container$1row_3.setKeyProperty("roleId");
            _jspx_th_liferay$1ui_search$1container$1row_3.setModelVar("userGroupRole");
            int _jspx_eval_liferay$1ui_search$1container$1row_3 = _jspx_th_liferay$1ui_search$1container$1row_3.doStartTag();
            if (_jspx_eval_liferay$1ui_search$1container$1row_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              java.lang.Integer index = null;
              com.liferay.portal.kernel.model.UserGroupRole userGroupRole = null;
              com.liferay.portal.kernel.dao.search.ResultRow row = null;
              if (_jspx_eval_liferay$1ui_search$1container$1row_3 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                out = _jspx_page_context.pushBody();
                _jspx_th_liferay$1ui_search$1container$1row_3.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                _jspx_th_liferay$1ui_search$1container$1row_3.doInitBody();
              }
              index = (java.lang.Integer) _jspx_page_context.findAttribute("index");
              userGroupRole = (com.liferay.portal.kernel.model.UserGroupRole) _jspx_page_context.findAttribute("userGroupRole");
              row = (com.liferay.portal.kernel.dao.search.ResultRow) _jspx_page_context.findAttribute("row");
              do {
                out.write("\n\t\t\t\t\t");
                //  liferay-ui:search-container-column-text
                com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_7 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text_name_cssClass.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
                _jspx_th_liferay$1ui_search$1container$1column$1text_7.setPageContext(_jspx_page_context);
                _jspx_th_liferay$1ui_search$1container$1column$1text_7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1row_3);
                _jspx_th_liferay$1ui_search$1container$1column$1text_7.setCssClass("table-cell-content");
                _jspx_th_liferay$1ui_search$1container$1column$1text_7.setName("title");
                int _jspx_eval_liferay$1ui_search$1container$1column$1text_7 = _jspx_th_liferay$1ui_search$1container$1column$1text_7.doStartTag();
                if (_jspx_eval_liferay$1ui_search$1container$1column$1text_7 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  if (_jspx_eval_liferay$1ui_search$1container$1column$1text_7 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                    out = _jspx_page_context.pushBody();
                    _jspx_th_liferay$1ui_search$1container$1column$1text_7.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                    _jspx_th_liferay$1ui_search$1container$1column$1text_7.doInitBody();
                  }
                  do {
                    out.write("\n\t\t\t\t\t\t");
                    //  liferay-ui:icon
                    com.liferay.taglib.ui.IconTag _jspx_th_liferay$1ui_icon_7 = (com.liferay.taglib.ui.IconTag) _jspx_tagPool_liferay$1ui_icon_message_label_iconCssClass_nobody.get(com.liferay.taglib.ui.IconTag.class);
                    _jspx_th_liferay$1ui_icon_7.setPageContext(_jspx_page_context);
                    _jspx_th_liferay$1ui_icon_7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1column$1text_7);
                    _jspx_th_liferay$1ui_icon_7.setIconCssClass( RolesAdminUtil.getIconCssClass(userGroupRole.getRole()) );
                    _jspx_th_liferay$1ui_icon_7.setLabel( true );
                    _jspx_th_liferay$1ui_icon_7.setMessage( HtmlUtil.escape(userGroupRole.getRole().getTitle(locale)) );
                    int _jspx_eval_liferay$1ui_icon_7 = _jspx_th_liferay$1ui_icon_7.doStartTag();
                    if (_jspx_th_liferay$1ui_icon_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_liferay$1ui_icon_message_label_iconCssClass_nobody.reuse(_jspx_th_liferay$1ui_icon_7);
                      return;
                    }
                    _jspx_tagPool_liferay$1ui_icon_message_label_iconCssClass_nobody.reuse(_jspx_th_liferay$1ui_icon_7);
                    out.write("\n\t\t\t\t\t");
                    int evalDoAfterBody = _jspx_th_liferay$1ui_search$1container$1column$1text_7.doAfterBody();
                    if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                      break;
                  } while (true);
                  if (_jspx_eval_liferay$1ui_search$1container$1column$1text_7 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                    out = _jspx_page_context.popBody();
                }
                if (_jspx_th_liferay$1ui_search$1container$1column$1text_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_liferay$1ui_search$1container$1column$1text_name_cssClass.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_7);
                  return;
                }
                _jspx_tagPool_liferay$1ui_search$1container$1column$1text_name_cssClass.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_7);
                out.write("\n\n\t\t\t\t\t");
                //  liferay-ui:search-container-column-text
                com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_8 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text_name_cssClass.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
                _jspx_th_liferay$1ui_search$1container$1column$1text_8.setPageContext(_jspx_page_context);
                _jspx_th_liferay$1ui_search$1container$1column$1text_8.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1row_3);
                _jspx_th_liferay$1ui_search$1container$1column$1text_8.setCssClass("table-cell-content");
                _jspx_th_liferay$1ui_search$1container$1column$1text_8.setName("site");
                int _jspx_eval_liferay$1ui_search$1container$1column$1text_8 = _jspx_th_liferay$1ui_search$1container$1column$1text_8.doStartTag();
                if (_jspx_eval_liferay$1ui_search$1container$1column$1text_8 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  if (_jspx_eval_liferay$1ui_search$1container$1column$1text_8 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                    out = _jspx_page_context.pushBody();
                    _jspx_th_liferay$1ui_search$1container$1column$1text_8.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                    _jspx_th_liferay$1ui_search$1container$1column$1text_8.doInitBody();
                  }
                  do {
                    out.write("\n\t\t\t\t\t\t");
                    //  liferay-staging:descriptive-name
                    com.liferay.staging.taglib.servlet.taglib.DescriptiveNameTag _jspx_th_liferay$1staging_descriptive$1name_0 = (com.liferay.staging.taglib.servlet.taglib.DescriptiveNameTag) _jspx_tagPool_liferay$1staging_descriptive$1name_group_nobody.get(com.liferay.staging.taglib.servlet.taglib.DescriptiveNameTag.class);
                    _jspx_th_liferay$1staging_descriptive$1name_0.setPageContext(_jspx_page_context);
                    _jspx_th_liferay$1staging_descriptive$1name_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1column$1text_8);
                    _jspx_th_liferay$1staging_descriptive$1name_0.setGroup( userGroupRole.getGroup() );
                    int _jspx_eval_liferay$1staging_descriptive$1name_0 = _jspx_th_liferay$1staging_descriptive$1name_0.doStartTag();
                    if (_jspx_th_liferay$1staging_descriptive$1name_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_liferay$1staging_descriptive$1name_group_nobody.reuse(_jspx_th_liferay$1staging_descriptive$1name_0);
                      return;
                    }
                    _jspx_tagPool_liferay$1staging_descriptive$1name_group_nobody.reuse(_jspx_th_liferay$1staging_descriptive$1name_0);
                    out.write("\n\t\t\t\t\t");
                    int evalDoAfterBody = _jspx_th_liferay$1ui_search$1container$1column$1text_8.doAfterBody();
                    if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                      break;
                  } while (true);
                  if (_jspx_eval_liferay$1ui_search$1container$1column$1text_8 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                    out = _jspx_page_context.popBody();
                }
                if (_jspx_th_liferay$1ui_search$1container$1column$1text_8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_liferay$1ui_search$1container$1column$1text_name_cssClass.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_8);
                  return;
                }
                _jspx_tagPool_liferay$1ui_search$1container$1column$1text_name_cssClass.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_8);
                out.write("\n\n\t\t\t\t\t");

					boolean membershipProtected = false;

					Group group = userGroupRole.getGroup();

					Role role = userGroupRole.getRole();

					if (role.getType() == RoleConstants.TYPE_ORGANIZATION) {
						membershipProtected = OrganizationMembershipPolicyUtil.isMembershipProtected(permissionChecker, userGroupRole.getUserId(), group.getOrganizationId());
					}
					else {
						membershipProtected = SiteMembershipPolicyUtil.isMembershipProtected(permissionChecker, userGroupRole.getUserId(), group.getGroupId());
					}
					
                out.write("\n\n\t\t\t\t\t");
                //  c:if
                com.liferay.taglib.core.IfTag _jspx_th_c_if_11 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                _jspx_th_c_if_11.setPageContext(_jspx_page_context);
                _jspx_th_c_if_11.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1row_3);
                _jspx_th_c_if_11.setTest( !portletName.equals(myAccountPortletId) && !membershipProtected );
                int _jspx_eval_c_if_11 = _jspx_th_c_if_11.doStartTag();
                if (_jspx_eval_c_if_11 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  out.write("\n\t\t\t\t\t\t");
                  //  liferay-ui:search-container-column-text
                  com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_9 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
                  _jspx_th_liferay$1ui_search$1container$1column$1text_9.setPageContext(_jspx_page_context);
                  _jspx_th_liferay$1ui_search$1container$1column$1text_9.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_11);
                  int _jspx_eval_liferay$1ui_search$1container$1column$1text_9 = _jspx_th_liferay$1ui_search$1container$1column$1text_9.doStartTag();
                  if (_jspx_eval_liferay$1ui_search$1container$1column$1text_9 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    if (_jspx_eval_liferay$1ui_search$1container$1column$1text_9 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                      out = _jspx_page_context.pushBody();
                      _jspx_th_liferay$1ui_search$1container$1column$1text_9.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                      _jspx_th_liferay$1ui_search$1container$1column$1text_9.doInitBody();
                    }
                    do {
                      out.write("\n\t\t\t\t\t\t\t<a class=\"modify-link\" data-groupId=\"");
                      out.print( userGroupRole.getGroupId() );
                      out.write("\" data-rowId=\"");
                      out.print( userGroupRole.getRoleId() );
                      out.write("\" href=\"javascript:;\">");
                      out.print( removeRoleIcon );
                      out.write("</a>\n\t\t\t\t\t\t");
                      int evalDoAfterBody = _jspx_th_liferay$1ui_search$1container$1column$1text_9.doAfterBody();
                      if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                        break;
                    } while (true);
                    if (_jspx_eval_liferay$1ui_search$1container$1column$1text_9 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                      out = _jspx_page_context.popBody();
                  }
                  if (_jspx_th_liferay$1ui_search$1container$1column$1text_9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_liferay$1ui_search$1container$1column$1text.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_9);
                    return;
                  }
                  _jspx_tagPool_liferay$1ui_search$1container$1column$1text.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_9);
                  out.write("\n\t\t\t\t\t");
                }
                if (_jspx_th_c_if_11.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_11);
                  return;
                }
                _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_11);
                out.write("\n\t\t\t\t");
                int evalDoAfterBody = _jspx_th_liferay$1ui_search$1container$1row_3.doAfterBody();
                index = (java.lang.Integer) _jspx_page_context.findAttribute("index");
                userGroupRole = (com.liferay.portal.kernel.model.UserGroupRole) _jspx_page_context.findAttribute("userGroupRole");
                row = (com.liferay.portal.kernel.dao.search.ResultRow) _jspx_page_context.findAttribute("row");
                if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                  break;
              } while (true);
              if (_jspx_eval_liferay$1ui_search$1container$1row_3 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                out = _jspx_page_context.popBody();
            }
            if (_jspx_th_liferay$1ui_search$1container$1row_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_liferay$1ui_search$1container$1row_modelVar_keyProperty_className.reuse(_jspx_th_liferay$1ui_search$1container$1row_3);
              return;
            }
            _jspx_tagPool_liferay$1ui_search$1container$1row_modelVar_keyProperty_className.reuse(_jspx_th_liferay$1ui_search$1container$1row_3);
            out.write("\n\n\t\t\t\t");
            if (_jspx_meth_liferay$1ui_search$1iterator_3((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1ui_search$1container_3, _jspx_page_context))
              return;
            out.write("\n\t\t\t");
          }
          if (_jspx_th_liferay$1ui_search$1container_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_liferay$1ui_search$1container_total_iteratorURL_id_headerNames_curParam_cssClass.reuse(_jspx_th_liferay$1ui_search$1container_3);
            return;
          }
          _jspx_tagPool_liferay$1ui_search$1container_total_iteratorURL_id_headerNames_curParam_cssClass.reuse(_jspx_th_liferay$1ui_search$1container_3);
          out.write("\n\n\t\t\t");
          //  c:if
          com.liferay.taglib.core.IfTag _jspx_th_c_if_12 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
          _jspx_th_c_if_12.setPageContext(_jspx_page_context);
          _jspx_th_c_if_12.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_0);
          _jspx_th_c_if_12.setTest( !portletName.equals(myAccountPortletId) );
          int _jspx_eval_c_if_12 = _jspx_th_c_if_12.doStartTag();
          if (_jspx_eval_c_if_12 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t\t\t");
            //  aui:script
            com.liferay.taglib.aui.ScriptTag _jspx_th_aui_script_3 = (com.liferay.taglib.aui.ScriptTag) _jspx_tagPool_aui_script_use.get(com.liferay.taglib.aui.ScriptTag.class);
            _jspx_th_aui_script_3.setPageContext(_jspx_page_context);
            _jspx_th_aui_script_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_12);
            _jspx_th_aui_script_3.setUse("liferay-search-container");
            int _jspx_eval_aui_script_3 = _jspx_th_aui_script_3.doStartTag();
            if (_jspx_eval_aui_script_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              if (_jspx_eval_aui_script_3 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                out = _jspx_page_context.pushBody();
                _jspx_th_aui_script_3.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                _jspx_th_aui_script_3.doInitBody();
              }
              do {
                out.write("\n\t\t\t\t\tvar Util = Liferay.Util;\n\n\t\t\t\t\tvar searchContainer = Liferay.SearchContainer.get('");
                if (_jspx_meth_portlet_namespace_14((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_3, _jspx_page_context))
                  return;
                out.write("siteRolesSearchContainer');\n\n\t\t\t\t\tvar searchContainerContentBox = searchContainer.get('contentBox');\n\n\t\t\t\t\tsearchContainerContentBox.delegate(\n\t\t\t\t\t\t'click',\n\t\t\t\t\t\tfunction(event) {\n\t\t\t\t\t\t\tvar link = event.currentTarget;\n\t\t\t\t\t\t\tvar tr = link.ancestor('tr');\n\n\t\t\t\t\t\t\tvar groupId = link.getAttribute('data-groupId');\n\t\t\t\t\t\t\tvar rowId = link.getAttribute('data-rowId');\n\n\t\t\t\t\t\t\tvar selectSiteRole = Util.getWindow('");
                if (_jspx_meth_portlet_namespace_15((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_3, _jspx_page_context))
                  return;
                out.write("selectSiteRole');\n\n\t\t\t\t\t\t\tif (selectSiteRole) {\n\t\t\t\t\t\t\t\tvar selectButton = selectSiteRole.iframe.node.get('contentWindow.document').one('.selector-button[data-groupid=\"' + groupId + '\"][data-entityid=\"' + rowId + '\"]');\n\n\t\t\t\t\t\t\t\tUtil.toggleDisabled(selectButton, false);\n\t\t\t\t\t\t\t}\n\n\t\t\t\t\t\t\tsearchContainer.deleteRow(tr, rowId);\n\n\t\t\t\t\t\t\t");
                if (_jspx_meth_portlet_namespace_16((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_3, _jspx_page_context))
                  return;
                out.write("deleteGroupRole(rowId, groupId);\n\t\t\t\t\t\t},\n\t\t\t\t\t\t'.modify-link'\n\t\t\t\t\t);\n\n\t\t\t\t\tLiferay.on(\n\t\t\t\t\t\t'");
                out.print( siteRoleSyncEntitiesEventName );
                out.write("',\n\t\t\t\t\t\tfunction(event) {\n\t\t\t\t\t\t\tevent.selectors.each(\n\t\t\t\t\t\t\t\tfunction(item, index, collection) {\n\t\t\t\t\t\t\t\t\tvar groupId = item.attr('data-groupid');\n\t\t\t\t\t\t\t\t\tvar roleId = item.attr('data-entityid');\n\n\t\t\t\t\t\t\t\t\tfor (var i = 0; i < ");
                if (_jspx_meth_portlet_namespace_17((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_3, _jspx_page_context))
                  return;
                out.write("addGroupRolesGroupIds.length; i++) {\n\t\t\t\t\t\t\t\t\t\tif ((");
                if (_jspx_meth_portlet_namespace_18((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_3, _jspx_page_context))
                  return;
                out.write("addGroupRolesGroupIds[i] == groupId) && (");
                if (_jspx_meth_portlet_namespace_19((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_3, _jspx_page_context))
                  return;
                out.write("addGroupRolesRoleIds[i] == roleId)) {\n\t\t\t\t\t\t\t\t\t\t\tUtil.toggleDisabled(item, true);\n\n\t\t\t\t\t\t\t\t\t\t\tbreak;\n\t\t\t\t\t\t\t\t\t\t}\n\t\t\t\t\t\t\t\t\t}\n\n\t\t\t\t\t\t\t\t\tfor (var i = 0; i < ");
                if (_jspx_meth_portlet_namespace_20((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_3, _jspx_page_context))
                  return;
                out.write("deleteGroupRolesGroupIds.length; i++) {\n\t\t\t\t\t\t\t\t\t\tif ((");
                if (_jspx_meth_portlet_namespace_21((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_3, _jspx_page_context))
                  return;
                out.write("deleteGroupRolesGroupIds[i] == groupId) && (");
                if (_jspx_meth_portlet_namespace_22((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_3, _jspx_page_context))
                  return;
                out.write("deleteGroupRolesRoleIds[i] == roleId)) {\n\t\t\t\t\t\t\t\t\t\t\tUtil.toggleDisabled(item, false);\n\n\t\t\t\t\t\t\t\t\t\t\tbreak;\n\t\t\t\t\t\t\t\t\t\t}\n\t\t\t\t\t\t\t\t\t}\n\t\t\t\t\t\t\t\t}\n\t\t\t\t\t\t\t);\n\t\t\t\t\t\t}\n\t\t\t\t\t);\n\n\t\t\t\t\tA.one('#");
                if (_jspx_meth_portlet_namespace_23((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_3, _jspx_page_context))
                  return;
                out.write("selectSiteRoleLink').on(\n\t\t\t\t\t\t'click',\n\t\t\t\t\t\tfunction(event) {\n\t\t\t\t\t\t\tUtil.selectEntity(\n\t\t\t\t\t\t\t\t{\n\t\t\t\t\t\t\t\t\tdialog: {\n\t\t\t\t\t\t\t\t\t\tconstrain: true,\n\t\t\t\t\t\t\t\t\t\tmodal: true\n\t\t\t\t\t\t\t\t\t},\n\n\t\t\t\t\t\t\t\t\t");

									String siteRoleEventName = liferayPortletResponse.getNamespace() + "selectSiteRole";
									
                out.write("\n\n\t\t\t\t\t\t\t\t\tid: '");
                out.print( siteRoleEventName );
                out.write("',\n\t\t\t\t\t\t\t\t\tselectedData: [],\n\t\t\t\t\t\t\t\t\ttitle: '");
                if (_jspx_meth_liferay$1ui_message_9((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_3, _jspx_page_context))
                  return;
                out.write("',\n\n\t\t\t\t\t\t\t\t\t");

									PortletURL selectSiteRoleURL = PortletProviderUtil.getPortletURL(request, Role.class.getName(), PortletProvider.Action.BROWSE);

									selectSiteRoleURL.setParameter("p_u_i_d", (selUser == null) ? "0" : String.valueOf(selUser.getUserId()));
									selectSiteRoleURL.setParameter("step", "1");
									selectSiteRoleURL.setParameter("roleType", String.valueOf(RoleConstants.TYPE_SITE));
									selectSiteRoleURL.setParameter("eventName", siteRoleEventName);
									selectSiteRoleURL.setParameter("syncEntitiesEventName", siteRoleSyncEntitiesEventName);
									selectSiteRoleURL.setWindowState(LiferayWindowState.POP_UP);
									
                out.write("\n\n\t\t\t\t\t\t\t\t\turi: '");
                out.print( selectSiteRoleURL.toString() );
                out.write("'\n\t\t\t\t\t\t\t\t},\n\t\t\t\t\t\t\t\tfunction(event) {\n\t\t\t\t\t\t\t\t\t");
                if (_jspx_meth_portlet_namespace_24((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_3, _jspx_page_context))
                  return;
                out.write("selectRole(event.entityid, event.entityname, event.searchcontainername, event.groupdescriptivename, event.groupid, event.iconcssclass);\n\t\t\t\t\t\t\t\t}\n\t\t\t\t\t\t\t);\n\t\t\t\t\t\t}\n\t\t\t\t\t);\n\t\t\t\t");
                int evalDoAfterBody = _jspx_th_aui_script_3.doAfterBody();
                if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                  break;
              } while (true);
              if (_jspx_eval_aui_script_3 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                out = _jspx_page_context.popBody();
            }
            if (_jspx_th_aui_script_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_aui_script_use.reuse(_jspx_th_aui_script_3);
              return;
            }
            _jspx_tagPool_aui_script_use.reuse(_jspx_th_aui_script_3);
            out.write("\n\t\t\t");
          }
          if (_jspx_th_c_if_12.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_12);
            return;
          }
          _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_12);
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
      if (_jspx_th_c_choose_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_0);
        return;
      }
      _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_0);
      out.write("\n\n\t<h4 class=\"sheet-tertiary-title\">");
      if (_jspx_meth_liferay$1ui_message_10(_jspx_page_context))
        return;
      out.write("</h4>\n\n\t");
      //  c:if
      com.liferay.taglib.core.IfTag _jspx_th_c_if_13 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
      _jspx_th_c_if_13.setPageContext(_jspx_page_context);
      _jspx_th_c_if_13.setParent(null);
      _jspx_th_c_if_13.setTest( inheritedSiteRoles.isEmpty() );
      int _jspx_eval_c_if_13 = _jspx_th_c_if_13.doStartTag();
      if (_jspx_eval_c_if_13 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        out.write("\n\t\t<div class=\"sheet-text\">");
        if (_jspx_meth_liferay$1ui_message_11((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_13, _jspx_page_context))
          return;
        out.write("</div>\n\t");
      }
      if (_jspx_th_c_if_13.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_13);
        return;
      }
      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_13);
      out.write("\n\n\t");
      //  c:if
      com.liferay.taglib.core.IfTag _jspx_th_c_if_14 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
      _jspx_th_c_if_14.setPageContext(_jspx_page_context);
      _jspx_th_c_if_14.setParent(null);
      _jspx_th_c_if_14.setTest( !inheritedSiteRoles.isEmpty() );
      int _jspx_eval_c_if_14 = _jspx_th_c_if_14.doStartTag();
      if (_jspx_eval_c_if_14 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        out.write("\n\t\t");
        //  liferay-ui:search-container
        com.liferay.taglib.ui.SearchContainerTag _jspx_th_liferay$1ui_search$1container_4 = (com.liferay.taglib.ui.SearchContainerTag) _jspx_tagPool_liferay$1ui_search$1container_total_iteratorURL_id_headerNames_curParam_cssClass.get(com.liferay.taglib.ui.SearchContainerTag.class);
        _jspx_th_liferay$1ui_search$1container_4.setPageContext(_jspx_page_context);
        _jspx_th_liferay$1ui_search$1container_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_14);
        _jspx_th_liferay$1ui_search$1container_4.setCssClass("lfr-search-container-inherited-site-roles");
        _jspx_th_liferay$1ui_search$1container_4.setCurParam("inheritedSiteRolesCur");
        _jspx_th_liferay$1ui_search$1container_4.setHeaderNames("title,site,user-group");
        _jspx_th_liferay$1ui_search$1container_4.setId("inheritedSiteRolesSearchContainer");
        _jspx_th_liferay$1ui_search$1container_4.setIteratorURL( currentURLObj );
        _jspx_th_liferay$1ui_search$1container_4.setTotal( inheritedSiteRoles.size() );
        int _jspx_eval_liferay$1ui_search$1container_4 = _jspx_th_liferay$1ui_search$1container_4.doStartTag();
        if (_jspx_eval_liferay$1ui_search$1container_4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          java.lang.Integer total = null;
          com.liferay.portal.kernel.dao.search.SearchContainer searchContainer = null;
          total = (java.lang.Integer) _jspx_page_context.findAttribute("total");
          searchContainer = (com.liferay.portal.kernel.dao.search.SearchContainer) _jspx_page_context.findAttribute("searchContainer");
          out.write("\n\t\t\t");
          //  liferay-ui:search-container-results
          java.util.List results = null;
          java.lang.Integer deprecatedTotal = null;
          com.liferay.taglib.ui.SearchContainerResultsTag _jspx_th_liferay$1ui_search$1container$1results_4 = (com.liferay.taglib.ui.SearchContainerResultsTag) _jspx_tagPool_liferay$1ui_search$1container$1results_results_nobody.get(com.liferay.taglib.ui.SearchContainerResultsTag.class);
          _jspx_th_liferay$1ui_search$1container$1results_4.setPageContext(_jspx_page_context);
          _jspx_th_liferay$1ui_search$1container$1results_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container_4);
          _jspx_th_liferay$1ui_search$1container$1results_4.setResults( inheritedSiteRoles.subList(searchContainer.getStart(), searchContainer.getResultEnd()) );
          int _jspx_eval_liferay$1ui_search$1container$1results_4 = _jspx_th_liferay$1ui_search$1container$1results_4.doStartTag();
          results = (java.util.List) _jspx_page_context.findAttribute("results");
          deprecatedTotal = (java.lang.Integer) _jspx_page_context.findAttribute("deprecatedTotal");
          if (_jspx_th_liferay$1ui_search$1container$1results_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_liferay$1ui_search$1container$1results_results_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1results_4);
            return;
          }
          results = (java.util.List) _jspx_page_context.findAttribute("results");
          deprecatedTotal = (java.lang.Integer) _jspx_page_context.findAttribute("deprecatedTotal");
          _jspx_tagPool_liferay$1ui_search$1container$1results_results_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1results_4);
          out.write("\n\n\t\t\t");
          //  liferay-ui:search-container-row
          com.liferay.taglib.ui.SearchContainerRowTag _jspx_th_liferay$1ui_search$1container$1row_4 = (com.liferay.taglib.ui.SearchContainerRowTag) _jspx_tagPool_liferay$1ui_search$1container$1row_modelVar_keyProperty_className.get(com.liferay.taglib.ui.SearchContainerRowTag.class);
          _jspx_th_liferay$1ui_search$1container$1row_4.setPageContext(_jspx_page_context);
          _jspx_th_liferay$1ui_search$1container$1row_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container_4);
          _jspx_th_liferay$1ui_search$1container$1row_4.setClassName("com.liferay.portal.kernel.model.UserGroupGroupRole");
          _jspx_th_liferay$1ui_search$1container$1row_4.setKeyProperty("roleId");
          _jspx_th_liferay$1ui_search$1container$1row_4.setModelVar("userGroupGroupRole");
          int _jspx_eval_liferay$1ui_search$1container$1row_4 = _jspx_th_liferay$1ui_search$1container$1row_4.doStartTag();
          if (_jspx_eval_liferay$1ui_search$1container$1row_4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            java.lang.Integer index = null;
            com.liferay.portal.kernel.model.UserGroupGroupRole userGroupGroupRole = null;
            com.liferay.portal.kernel.dao.search.ResultRow row = null;
            if (_jspx_eval_liferay$1ui_search$1container$1row_4 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
              out = _jspx_page_context.pushBody();
              _jspx_th_liferay$1ui_search$1container$1row_4.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
              _jspx_th_liferay$1ui_search$1container$1row_4.doInitBody();
            }
            index = (java.lang.Integer) _jspx_page_context.findAttribute("index");
            userGroupGroupRole = (com.liferay.portal.kernel.model.UserGroupGroupRole) _jspx_page_context.findAttribute("userGroupGroupRole");
            row = (com.liferay.portal.kernel.dao.search.ResultRow) _jspx_page_context.findAttribute("row");
            do {
              out.write("\n\t\t\t\t");
              //  liferay-ui:search-container-column-text
              com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_10 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text_name_cssClass.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
              _jspx_th_liferay$1ui_search$1container$1column$1text_10.setPageContext(_jspx_page_context);
              _jspx_th_liferay$1ui_search$1container$1column$1text_10.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1row_4);
              _jspx_th_liferay$1ui_search$1container$1column$1text_10.setCssClass("table-cell-content");
              _jspx_th_liferay$1ui_search$1container$1column$1text_10.setName("title");
              int _jspx_eval_liferay$1ui_search$1container$1column$1text_10 = _jspx_th_liferay$1ui_search$1container$1column$1text_10.doStartTag();
              if (_jspx_eval_liferay$1ui_search$1container$1column$1text_10 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                if (_jspx_eval_liferay$1ui_search$1container$1column$1text_10 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                  out = _jspx_page_context.pushBody();
                  _jspx_th_liferay$1ui_search$1container$1column$1text_10.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                  _jspx_th_liferay$1ui_search$1container$1column$1text_10.doInitBody();
                }
                do {
                  out.write("\n\t\t\t\t\t");
                  //  liferay-ui:icon
                  com.liferay.taglib.ui.IconTag _jspx_th_liferay$1ui_icon_8 = (com.liferay.taglib.ui.IconTag) _jspx_tagPool_liferay$1ui_icon_message_label_iconCssClass_nobody.get(com.liferay.taglib.ui.IconTag.class);
                  _jspx_th_liferay$1ui_icon_8.setPageContext(_jspx_page_context);
                  _jspx_th_liferay$1ui_icon_8.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1column$1text_10);
                  _jspx_th_liferay$1ui_icon_8.setIconCssClass( RolesAdminUtil.getIconCssClass(userGroupGroupRole.getRole()) );
                  _jspx_th_liferay$1ui_icon_8.setLabel( true );
                  _jspx_th_liferay$1ui_icon_8.setMessage( HtmlUtil.escape(userGroupGroupRole.getRole().getTitle(locale)) );
                  int _jspx_eval_liferay$1ui_icon_8 = _jspx_th_liferay$1ui_icon_8.doStartTag();
                  if (_jspx_th_liferay$1ui_icon_8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_liferay$1ui_icon_message_label_iconCssClass_nobody.reuse(_jspx_th_liferay$1ui_icon_8);
                    return;
                  }
                  _jspx_tagPool_liferay$1ui_icon_message_label_iconCssClass_nobody.reuse(_jspx_th_liferay$1ui_icon_8);
                  out.write("\n\t\t\t\t");
                  int evalDoAfterBody = _jspx_th_liferay$1ui_search$1container$1column$1text_10.doAfterBody();
                  if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                    break;
                } while (true);
                if (_jspx_eval_liferay$1ui_search$1container$1column$1text_10 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                  out = _jspx_page_context.popBody();
              }
              if (_jspx_th_liferay$1ui_search$1container$1column$1text_10.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_liferay$1ui_search$1container$1column$1text_name_cssClass.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_10);
                return;
              }
              _jspx_tagPool_liferay$1ui_search$1container$1column$1text_name_cssClass.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_10);
              out.write("\n\n\t\t\t\t");
              //  liferay-ui:search-container-column-text
              com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_11 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text_name_cssClass.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
              _jspx_th_liferay$1ui_search$1container$1column$1text_11.setPageContext(_jspx_page_context);
              _jspx_th_liferay$1ui_search$1container$1column$1text_11.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1row_4);
              _jspx_th_liferay$1ui_search$1container$1column$1text_11.setCssClass("table-cell-content");
              _jspx_th_liferay$1ui_search$1container$1column$1text_11.setName("site");
              int _jspx_eval_liferay$1ui_search$1container$1column$1text_11 = _jspx_th_liferay$1ui_search$1container$1column$1text_11.doStartTag();
              if (_jspx_eval_liferay$1ui_search$1container$1column$1text_11 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                if (_jspx_eval_liferay$1ui_search$1container$1column$1text_11 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                  out = _jspx_page_context.pushBody();
                  _jspx_th_liferay$1ui_search$1container$1column$1text_11.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                  _jspx_th_liferay$1ui_search$1container$1column$1text_11.doInitBody();
                }
                do {
                  out.write("\n\t\t\t\t\t");
                  //  liferay-staging:descriptive-name
                  com.liferay.staging.taglib.servlet.taglib.DescriptiveNameTag _jspx_th_liferay$1staging_descriptive$1name_1 = (com.liferay.staging.taglib.servlet.taglib.DescriptiveNameTag) _jspx_tagPool_liferay$1staging_descriptive$1name_group_nobody.get(com.liferay.staging.taglib.servlet.taglib.DescriptiveNameTag.class);
                  _jspx_th_liferay$1staging_descriptive$1name_1.setPageContext(_jspx_page_context);
                  _jspx_th_liferay$1staging_descriptive$1name_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1column$1text_11);
                  _jspx_th_liferay$1staging_descriptive$1name_1.setGroup( userGroupGroupRole.getGroup() );
                  int _jspx_eval_liferay$1staging_descriptive$1name_1 = _jspx_th_liferay$1staging_descriptive$1name_1.doStartTag();
                  if (_jspx_th_liferay$1staging_descriptive$1name_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_liferay$1staging_descriptive$1name_group_nobody.reuse(_jspx_th_liferay$1staging_descriptive$1name_1);
                    return;
                  }
                  _jspx_tagPool_liferay$1staging_descriptive$1name_group_nobody.reuse(_jspx_th_liferay$1staging_descriptive$1name_1);
                  out.write("\n\t\t\t\t");
                  int evalDoAfterBody = _jspx_th_liferay$1ui_search$1container$1column$1text_11.doAfterBody();
                  if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                    break;
                } while (true);
                if (_jspx_eval_liferay$1ui_search$1container$1column$1text_11 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                  out = _jspx_page_context.popBody();
              }
              if (_jspx_th_liferay$1ui_search$1container$1column$1text_11.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_liferay$1ui_search$1container$1column$1text_name_cssClass.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_11);
                return;
              }
              _jspx_tagPool_liferay$1ui_search$1container$1column$1text_name_cssClass.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_11);
              out.write("\n\n\t\t\t\t");
              //  liferay-ui:search-container-column-text
              com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_12 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_cssClass_nobody.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
              _jspx_th_liferay$1ui_search$1container$1column$1text_12.setPageContext(_jspx_page_context);
              _jspx_th_liferay$1ui_search$1container$1column$1text_12.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1row_4);
              _jspx_th_liferay$1ui_search$1container$1column$1text_12.setCssClass("table-cell-content");
              _jspx_th_liferay$1ui_search$1container$1column$1text_12.setName("user-group");
              _jspx_th_liferay$1ui_search$1container$1column$1text_12.setValue( HtmlUtil.escape(userGroupGroupRole.getUserGroup().getName()) );
              int _jspx_eval_liferay$1ui_search$1container$1column$1text_12 = _jspx_th_liferay$1ui_search$1container$1column$1text_12.doStartTag();
              if (_jspx_th_liferay$1ui_search$1container$1column$1text_12.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_cssClass_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_12);
                return;
              }
              _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_cssClass_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_12);
              out.write("\n\t\t\t");
              int evalDoAfterBody = _jspx_th_liferay$1ui_search$1container$1row_4.doAfterBody();
              index = (java.lang.Integer) _jspx_page_context.findAttribute("index");
              userGroupGroupRole = (com.liferay.portal.kernel.model.UserGroupGroupRole) _jspx_page_context.findAttribute("userGroupGroupRole");
              row = (com.liferay.portal.kernel.dao.search.ResultRow) _jspx_page_context.findAttribute("row");
              if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                break;
            } while (true);
            if (_jspx_eval_liferay$1ui_search$1container$1row_4 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
              out = _jspx_page_context.popBody();
          }
          if (_jspx_th_liferay$1ui_search$1container$1row_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_liferay$1ui_search$1container$1row_modelVar_keyProperty_className.reuse(_jspx_th_liferay$1ui_search$1container$1row_4);
            return;
          }
          _jspx_tagPool_liferay$1ui_search$1container$1row_modelVar_keyProperty_className.reuse(_jspx_th_liferay$1ui_search$1container$1row_4);
          out.write("\n\n\t\t\t");
          if (_jspx_meth_liferay$1ui_search$1iterator_4((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1ui_search$1container_4, _jspx_page_context))
            return;
          out.write("\n\t\t");
        }
        if (_jspx_th_liferay$1ui_search$1container_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_liferay$1ui_search$1container_total_iteratorURL_id_headerNames_curParam_cssClass.reuse(_jspx_th_liferay$1ui_search$1container_4);
          return;
        }
        _jspx_tagPool_liferay$1ui_search$1container_total_iteratorURL_id_headerNames_curParam_cssClass.reuse(_jspx_th_liferay$1ui_search$1container_4);
        out.write('\n');
        out.write('	');
      }
      if (_jspx_th_c_if_14.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_14);
        return;
      }
      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_14);
      out.write("\n\n\t");
      //  c:if
      com.liferay.taglib.core.IfTag _jspx_th_c_if_15 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
      _jspx_th_c_if_15.setPageContext(_jspx_page_context);
      _jspx_th_c_if_15.setParent(null);
      _jspx_th_c_if_15.setTest( !portletName.equals(myAccountPortletId) );
      int _jspx_eval_c_if_15 = _jspx_th_c_if_15.doStartTag();
      if (_jspx_eval_c_if_15 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        out.write("\n\t\t");
        //  aui:script
        com.liferay.taglib.aui.ScriptTag _jspx_th_aui_script_4 = (com.liferay.taglib.aui.ScriptTag) _jspx_tagPool_aui_script.get(com.liferay.taglib.aui.ScriptTag.class);
        _jspx_th_aui_script_4.setPageContext(_jspx_page_context);
        _jspx_th_aui_script_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_15);
        int _jspx_eval_aui_script_4 = _jspx_th_aui_script_4.doStartTag();
        if (_jspx_eval_aui_script_4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          if (_jspx_eval_aui_script_4 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
            out = _jspx_page_context.pushBody();
            _jspx_th_aui_script_4.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
            _jspx_th_aui_script_4.doInitBody();
          }
          do {
            out.write("\n\t\t\tvar ");
            if (_jspx_meth_portlet_namespace_25((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_4, _jspx_page_context))
              return;
            out.write("addRoleIds = [];\n\t\t\tvar ");
            if (_jspx_meth_portlet_namespace_26((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_4, _jspx_page_context))
              return;
            out.write("deleteRoleIds = [];\n\n\t\t\tvar ");
            if (_jspx_meth_portlet_namespace_27((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_4, _jspx_page_context))
              return;
            out.write("addGroupRolesGroupIds = [];\n\t\t\tvar ");
            if (_jspx_meth_portlet_namespace_28((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_4, _jspx_page_context))
              return;
            out.write("addGroupRolesRoleIds = [];\n\t\t\tvar ");
            if (_jspx_meth_portlet_namespace_29((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_4, _jspx_page_context))
              return;
            out.write("deleteGroupRolesGroupIds = [];\n\t\t\tvar ");
            if (_jspx_meth_portlet_namespace_30((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_4, _jspx_page_context))
              return;
            out.write("deleteGroupRolesRoleIds = [];\n\n\t\t\tfunction ");
            if (_jspx_meth_portlet_namespace_31((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_4, _jspx_page_context))
              return;
            out.write("deleteRegularRole(roleId) {\n\t\t\t\tvar A = AUI();\n\n\t\t\t\tA.Array.removeItem(");
            if (_jspx_meth_portlet_namespace_32((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_4, _jspx_page_context))
              return;
            out.write("addRoleIds, roleId);\n\n\t\t\t\t");
            if (_jspx_meth_portlet_namespace_33((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_4, _jspx_page_context))
              return;
            out.write("deleteRoleIds.push(roleId);\n\n\t\t\t\tdocument.");
            if (_jspx_meth_portlet_namespace_34((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_4, _jspx_page_context))
              return;
            out.write("fm.");
            if (_jspx_meth_portlet_namespace_35((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_4, _jspx_page_context))
              return;
            out.write("addRoleIds.value = ");
            if (_jspx_meth_portlet_namespace_36((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_4, _jspx_page_context))
              return;
            out.write("addRoleIds.join(',');\n\t\t\t\tdocument.");
            if (_jspx_meth_portlet_namespace_37((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_4, _jspx_page_context))
              return;
            out.write("fm.");
            if (_jspx_meth_portlet_namespace_38((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_4, _jspx_page_context))
              return;
            out.write("deleteRoleIds.value = ");
            if (_jspx_meth_portlet_namespace_39((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_4, _jspx_page_context))
              return;
            out.write("deleteRoleIds.join(',');\n\t\t\t}\n\n\t\t\tfunction ");
            if (_jspx_meth_portlet_namespace_40((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_4, _jspx_page_context))
              return;
            out.write("deleteGroupRole(roleId, groupId) {\n\t\t\t\tfor (var i = 0; i < ");
            if (_jspx_meth_portlet_namespace_41((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_4, _jspx_page_context))
              return;
            out.write("addGroupRolesRoleIds.length; i++) {\n\t\t\t\t\tif ((");
            if (_jspx_meth_portlet_namespace_42((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_4, _jspx_page_context))
              return;
            out.write("addGroupRolesGroupIds[i] == groupId) && (");
            if (_jspx_meth_portlet_namespace_43((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_4, _jspx_page_context))
              return;
            out.write("addGroupRolesRoleIds[i] == roleId)) {\n\t\t\t\t\t\t");
            if (_jspx_meth_portlet_namespace_44((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_4, _jspx_page_context))
              return;
            out.write("addGroupRolesGroupIds.splice(i, 1);\n\t\t\t\t\t\t");
            if (_jspx_meth_portlet_namespace_45((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_4, _jspx_page_context))
              return;
            out.write("addGroupRolesRoleIds.splice(i, 1);\n\n\t\t\t\t\t\tbreak;\n\t\t\t\t\t}\n\t\t\t\t}\n\n\t\t\t\t");
            if (_jspx_meth_portlet_namespace_46((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_4, _jspx_page_context))
              return;
            out.write("deleteGroupRolesGroupIds.push(groupId);\n\t\t\t\t");
            if (_jspx_meth_portlet_namespace_47((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_4, _jspx_page_context))
              return;
            out.write("deleteGroupRolesRoleIds.push(roleId);\n\n\t\t\t\tdocument.");
            if (_jspx_meth_portlet_namespace_48((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_4, _jspx_page_context))
              return;
            out.write("fm.");
            if (_jspx_meth_portlet_namespace_49((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_4, _jspx_page_context))
              return;
            out.write("addGroupRolesGroupIds.value = ");
            if (_jspx_meth_portlet_namespace_50((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_4, _jspx_page_context))
              return;
            out.write("addGroupRolesGroupIds.join(',');\n\t\t\t\tdocument.");
            if (_jspx_meth_portlet_namespace_51((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_4, _jspx_page_context))
              return;
            out.write("fm.");
            if (_jspx_meth_portlet_namespace_52((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_4, _jspx_page_context))
              return;
            out.write("addGroupRolesRoleIds.value = ");
            if (_jspx_meth_portlet_namespace_53((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_4, _jspx_page_context))
              return;
            out.write("addGroupRolesRoleIds.join(',');\n\t\t\t\tdocument.");
            if (_jspx_meth_portlet_namespace_54((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_4, _jspx_page_context))
              return;
            out.write("fm.");
            if (_jspx_meth_portlet_namespace_55((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_4, _jspx_page_context))
              return;
            out.write("deleteGroupRolesGroupIds.value = ");
            if (_jspx_meth_portlet_namespace_56((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_4, _jspx_page_context))
              return;
            out.write("deleteGroupRolesGroupIds.join(',');\n\t\t\t\tdocument.");
            if (_jspx_meth_portlet_namespace_57((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_4, _jspx_page_context))
              return;
            out.write("fm.");
            if (_jspx_meth_portlet_namespace_58((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_4, _jspx_page_context))
              return;
            out.write("deleteGroupRolesRoleIds.value = ");
            if (_jspx_meth_portlet_namespace_59((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_4, _jspx_page_context))
              return;
            out.write("deleteGroupRolesRoleIds.join(',');\n\t\t\t}\n\n\t\t\tLiferay.provide(\n\t\t\t\twindow,\n\t\t\t\t'");
            if (_jspx_meth_portlet_namespace_60((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_4, _jspx_page_context))
              return;
            out.write("selectRole',\n\t\t\t\tfunction(roleId, name, searchContainer, groupName, groupId, iconCssClass) {\n\t\t\t\t\tvar A = AUI();\n\t\t\t\t\tvar LString = A.Lang.String;\n\n\t\t\t\t\tvar searchContainerName = '");
            if (_jspx_meth_portlet_namespace_61((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_4, _jspx_page_context))
              return;
            out.write("' + searchContainer + 'SearchContainer';\n\n\t\t\t\t\tsearchContainer = Liferay.SearchContainer.get(searchContainerName);\n\n\t\t\t\t\tvar rowColumns = [];\n\n\t\t\t\t\trowColumns.push('<i class=\"' + iconCssClass + '\"></i> ' + LString.escapeHTML(name));\n\n\t\t\t\t\tif (groupName) {\n\t\t\t\t\t\trowColumns.push(groupName);\n\t\t\t\t\t}\n\n\t\t\t\t\tif (groupId) {\n\t\t\t\t\t\trowColumns.push('<a class=\"modify-link\" data-groupId=\"' + groupId + '\" data-rowId=\"' + roleId + '\" href=\"javascript:;\">");
            out.print( UnicodeFormatter.toString(removeRoleIcon) );
            out.write("</a>');\n\n\t\t\t\t\t\tfor (var i = 0; i < ");
            if (_jspx_meth_portlet_namespace_62((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_4, _jspx_page_context))
              return;
            out.write("deleteGroupRolesRoleIds.length; i++) {\n\t\t\t\t\t\t\tif ((");
            if (_jspx_meth_portlet_namespace_63((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_4, _jspx_page_context))
              return;
            out.write("deleteGroupRolesGroupIds[i] == groupId) && (");
            if (_jspx_meth_portlet_namespace_64((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_4, _jspx_page_context))
              return;
            out.write("deleteGroupRolesRoleIds[i] == roleId)) {\n\t\t\t\t\t\t\t\t");
            if (_jspx_meth_portlet_namespace_65((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_4, _jspx_page_context))
              return;
            out.write("deleteGroupRolesGroupIds.splice(i, 1);\n\t\t\t\t\t\t\t\t");
            if (_jspx_meth_portlet_namespace_66((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_4, _jspx_page_context))
              return;
            out.write("deleteGroupRolesRoleIds.splice(i, 1);\n\n\t\t\t\t\t\t\t\tbreak;\n\t\t\t\t\t\t\t}\n\t\t\t\t\t\t}\n\n\t\t\t\t\t\t");
            if (_jspx_meth_portlet_namespace_67((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_4, _jspx_page_context))
              return;
            out.write("addGroupRolesGroupIds.push(groupId);\n\t\t\t\t\t\t");
            if (_jspx_meth_portlet_namespace_68((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_4, _jspx_page_context))
              return;
            out.write("addGroupRolesRoleIds.push(roleId);\n\n\t\t\t\t\t\tdocument.");
            if (_jspx_meth_portlet_namespace_69((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_4, _jspx_page_context))
              return;
            out.write("fm.");
            if (_jspx_meth_portlet_namespace_70((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_4, _jspx_page_context))
              return;
            out.write("addGroupRolesGroupIds.value = ");
            if (_jspx_meth_portlet_namespace_71((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_4, _jspx_page_context))
              return;
            out.write("addGroupRolesGroupIds.join(',');\n\t\t\t\t\t\tdocument.");
            if (_jspx_meth_portlet_namespace_72((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_4, _jspx_page_context))
              return;
            out.write("fm.");
            if (_jspx_meth_portlet_namespace_73((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_4, _jspx_page_context))
              return;
            out.write("addGroupRolesRoleIds.value = ");
            if (_jspx_meth_portlet_namespace_74((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_4, _jspx_page_context))
              return;
            out.write("addGroupRolesRoleIds.join(',');\n\t\t\t\t\t\tdocument.");
            if (_jspx_meth_portlet_namespace_75((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_4, _jspx_page_context))
              return;
            out.write("fm.");
            if (_jspx_meth_portlet_namespace_76((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_4, _jspx_page_context))
              return;
            out.write("deleteGroupRolesGroupIds.value = ");
            if (_jspx_meth_portlet_namespace_77((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_4, _jspx_page_context))
              return;
            out.write("deleteGroupRolesGroupIds.join(',');\n\t\t\t\t\t\tdocument.");
            if (_jspx_meth_portlet_namespace_78((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_4, _jspx_page_context))
              return;
            out.write("fm.");
            if (_jspx_meth_portlet_namespace_79((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_4, _jspx_page_context))
              return;
            out.write("deleteGroupRolesRoleIds.value = ");
            if (_jspx_meth_portlet_namespace_80((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_4, _jspx_page_context))
              return;
            out.write("deleteGroupRolesRoleIds.join(',');\n\t\t\t\t\t}\n\t\t\t\t\telse {\n\t\t\t\t\t\trowColumns.push('<a class=\"modify-link\" data-rowId=\"' + roleId + '\" href=\"javascript:;\">");
            out.print( UnicodeFormatter.toString(removeRoleIcon) );
            out.write("</a>');\n\n\t\t\t\t\t\tA.Array.removeItem(");
            if (_jspx_meth_portlet_namespace_81((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_4, _jspx_page_context))
              return;
            out.write("deleteRoleIds, roleId);\n\n\t\t\t\t\t\t");
            if (_jspx_meth_portlet_namespace_82((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_4, _jspx_page_context))
              return;
            out.write("addRoleIds.push(roleId);\n\n\t\t\t\t\t\tdocument.");
            if (_jspx_meth_portlet_namespace_83((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_4, _jspx_page_context))
              return;
            out.write("fm.");
            if (_jspx_meth_portlet_namespace_84((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_4, _jspx_page_context))
              return;
            out.write("addRoleIds.value = ");
            if (_jspx_meth_portlet_namespace_85((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_4, _jspx_page_context))
              return;
            out.write("addRoleIds.join(',');\n\t\t\t\t\t\tdocument.");
            if (_jspx_meth_portlet_namespace_86((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_4, _jspx_page_context))
              return;
            out.write("fm.");
            if (_jspx_meth_portlet_namespace_87((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_4, _jspx_page_context))
              return;
            out.write("deleteRoleIds.value = ");
            if (_jspx_meth_portlet_namespace_88((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_4, _jspx_page_context))
              return;
            out.write("deleteRoleIds.join(',');\n\t\t\t\t\t}\n\n\t\t\t\t\tsearchContainer.addRow(rowColumns, roleId);\n\n\t\t\t\t\tsearchContainer.updateDataStore();\n\t\t\t\t},\n\t\t\t\t['liferay-search-container']\n\t\t\t);\n\t\t");
            int evalDoAfterBody = _jspx_th_aui_script_4.doAfterBody();
            if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
              break;
          } while (true);
          if (_jspx_eval_aui_script_4 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
            out = _jspx_page_context.popBody();
        }
        if (_jspx_th_aui_script_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_aui_script.reuse(_jspx_th_aui_script_4);
          return;
        }
        _jspx_tagPool_aui_script.reuse(_jspx_th_aui_script_4);
        out.write("\n\n\t\t");
        //  aui:script
        com.liferay.taglib.aui.ScriptTag _jspx_th_aui_script_5 = (com.liferay.taglib.aui.ScriptTag) _jspx_tagPool_aui_script_use.get(com.liferay.taglib.aui.ScriptTag.class);
        _jspx_th_aui_script_5.setPageContext(_jspx_page_context);
        _jspx_th_aui_script_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_15);
        _jspx_th_aui_script_5.setUse("liferay-search-container");
        int _jspx_eval_aui_script_5 = _jspx_th_aui_script_5.doStartTag();
        if (_jspx_eval_aui_script_5 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          if (_jspx_eval_aui_script_5 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
            out = _jspx_page_context.pushBody();
            _jspx_th_aui_script_5.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
            _jspx_th_aui_script_5.doInitBody();
          }
          do {
            out.write("\n\t\t\tvar Util = Liferay.Util;\n\n\t\t\tvar searchContainer = Liferay.SearchContainer.get('");
            if (_jspx_meth_portlet_namespace_89((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_5, _jspx_page_context))
              return;
            out.write("rolesSearchContainer');\n\n\t\t\tvar searchContainerContentBox = searchContainer.get('contentBox');\n\n\t\t\tsearchContainerContentBox.delegate(\n\t\t\t\t'click',\n\t\t\t\tfunction(event) {\n\t\t\t\t\tvar link = event.currentTarget;\n\n\t\t\t\t\tvar rowId = link.attr('data-rowId');\n\n\t\t\t\t\tvar tr = link.ancestor('tr');\n\n\t\t\t\t\tvar selectRegularRole = Util.getWindow('");
            if (_jspx_meth_portlet_namespace_90((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_5, _jspx_page_context))
              return;
            out.write("selectRegularRole');\n\n\t\t\t\t\tif (selectRegularRole) {\n\t\t\t\t\t\tvar selectButton = selectRegularRole.iframe.node.get('contentWindow.document').one('.selector-button[data-entityid=\"' + rowId + '\"]');\n\n\t\t\t\t\t\tUtil.toggleDisabled(selectButton, false);\n\t\t\t\t\t}\n\n\t\t\t\t\tsearchContainer.deleteRow(tr, link.getAttribute('data-rowId'));\n\n\t\t\t\t\t");
            if (_jspx_meth_portlet_namespace_91((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_5, _jspx_page_context))
              return;
            out.write("deleteRegularRole(rowId);\n\t\t\t\t},\n\t\t\t\t'.modify-link'\n\t\t\t);\n\n\t\t\tLiferay.on(\n\t\t\t\t'");
            out.print( regularRoleSyncEntitiesEventName );
            out.write("',\n\t\t\t\tfunction(event) {\n\t\t\t\t\tevent.selectors.each(\n\t\t\t\t\t\tfunction(item, index, collection) {\n\t\t\t\t\t\t\tvar roleId = item.attr('data-entityid');\n\n\t\t\t\t\t\t\tif (");
            if (_jspx_meth_portlet_namespace_92((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_5, _jspx_page_context))
              return;
            out.write("deleteRoleIds.indexOf(roleId) != -1) {\n\t\t\t\t\t\t\t\tUtil.toggleDisabled(item, false);\n\t\t\t\t\t\t\t}\n\t\t\t\t\t\t}\n\t\t\t\t\t);\n\t\t\t\t}\n\t\t\t);\n\t\t");
            int evalDoAfterBody = _jspx_th_aui_script_5.doAfterBody();
            if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
              break;
          } while (true);
          if (_jspx_eval_aui_script_5 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
            out = _jspx_page_context.popBody();
        }
        if (_jspx_th_aui_script_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_aui_script_use.reuse(_jspx_th_aui_script_5);
          return;
        }
        _jspx_tagPool_aui_script_use.reuse(_jspx_th_aui_script_5);
        out.write('\n');
        out.write('	');
      }
      if (_jspx_th_c_if_15.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_15);
        return;
      }
      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_15);
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

  private boolean _jspx_meth_liferay$1ui_membership$1policy$1error_0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:membership-policy-error
    com.liferay.taglib.ui.MembershipPolicyErrorTag _jspx_th_liferay$1ui_membership$1policy$1error_0 = (com.liferay.taglib.ui.MembershipPolicyErrorTag) _jspx_tagPool_liferay$1ui_membership$1policy$1error_nobody.get(com.liferay.taglib.ui.MembershipPolicyErrorTag.class);
    _jspx_th_liferay$1ui_membership$1policy$1error_0.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_membership$1policy$1error_0.setParent(null);
    int _jspx_eval_liferay$1ui_membership$1policy$1error_0 = _jspx_th_liferay$1ui_membership$1policy$1error_0.doStartTag();
    if (_jspx_th_liferay$1ui_membership$1policy$1error_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_membership$1policy$1error_nobody.reuse(_jspx_th_liferay$1ui_membership$1policy$1error_0);
      return true;
    }
    _jspx_tagPool_liferay$1ui_membership$1policy$1error_nobody.reuse(_jspx_th_liferay$1ui_membership$1policy$1error_0);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_icon_0(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1util_buffer_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:icon
    com.liferay.taglib.ui.IconTag _jspx_th_liferay$1ui_icon_0 = (com.liferay.taglib.ui.IconTag) _jspx_tagPool_liferay$1ui_icon_message_markupView_icon_nobody.get(com.liferay.taglib.ui.IconTag.class);
    _jspx_th_liferay$1ui_icon_0.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_icon_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1util_buffer_0);
    _jspx_th_liferay$1ui_icon_0.setIcon("times");
    _jspx_th_liferay$1ui_icon_0.setMarkupView("lexicon");
    _jspx_th_liferay$1ui_icon_0.setMessage("remove");
    int _jspx_eval_liferay$1ui_icon_0 = _jspx_th_liferay$1ui_icon_0.doStartTag();
    if (_jspx_th_liferay$1ui_icon_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_icon_message_markupView_icon_nobody.reuse(_jspx_th_liferay$1ui_icon_0);
      return true;
    }
    _jspx_tagPool_liferay$1ui_icon_message_markupView_icon_nobody.reuse(_jspx_th_liferay$1ui_icon_0);
    return false;
  }

  private boolean _jspx_meth_aui_input_0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:input
    com.liferay.taglib.aui.InputTag _jspx_th_aui_input_0 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
    _jspx_th_aui_input_0.setPageContext(_jspx_page_context);
    _jspx_th_aui_input_0.setParent(null);
    _jspx_th_aui_input_0.setName("addGroupRolesGroupIds");
    _jspx_th_aui_input_0.setType("hidden");
    int _jspx_eval_aui_input_0 = _jspx_th_aui_input_0.doStartTag();
    if (_jspx_th_aui_input_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_input_type_name_nobody.reuse(_jspx_th_aui_input_0);
      return true;
    }
    _jspx_tagPool_aui_input_type_name_nobody.reuse(_jspx_th_aui_input_0);
    return false;
  }

  private boolean _jspx_meth_aui_input_1(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:input
    com.liferay.taglib.aui.InputTag _jspx_th_aui_input_1 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
    _jspx_th_aui_input_1.setPageContext(_jspx_page_context);
    _jspx_th_aui_input_1.setParent(null);
    _jspx_th_aui_input_1.setName("addGroupRolesRoleIds");
    _jspx_th_aui_input_1.setType("hidden");
    int _jspx_eval_aui_input_1 = _jspx_th_aui_input_1.doStartTag();
    if (_jspx_th_aui_input_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_input_type_name_nobody.reuse(_jspx_th_aui_input_1);
      return true;
    }
    _jspx_tagPool_aui_input_type_name_nobody.reuse(_jspx_th_aui_input_1);
    return false;
  }

  private boolean _jspx_meth_aui_input_2(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:input
    com.liferay.taglib.aui.InputTag _jspx_th_aui_input_2 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
    _jspx_th_aui_input_2.setPageContext(_jspx_page_context);
    _jspx_th_aui_input_2.setParent(null);
    _jspx_th_aui_input_2.setName("addRoleIds");
    _jspx_th_aui_input_2.setType("hidden");
    int _jspx_eval_aui_input_2 = _jspx_th_aui_input_2.doStartTag();
    if (_jspx_th_aui_input_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_input_type_name_nobody.reuse(_jspx_th_aui_input_2);
      return true;
    }
    _jspx_tagPool_aui_input_type_name_nobody.reuse(_jspx_th_aui_input_2);
    return false;
  }

  private boolean _jspx_meth_aui_input_3(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:input
    com.liferay.taglib.aui.InputTag _jspx_th_aui_input_3 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
    _jspx_th_aui_input_3.setPageContext(_jspx_page_context);
    _jspx_th_aui_input_3.setParent(null);
    _jspx_th_aui_input_3.setName("deleteGroupRolesGroupIds");
    _jspx_th_aui_input_3.setType("hidden");
    int _jspx_eval_aui_input_3 = _jspx_th_aui_input_3.doStartTag();
    if (_jspx_th_aui_input_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_input_type_name_nobody.reuse(_jspx_th_aui_input_3);
      return true;
    }
    _jspx_tagPool_aui_input_type_name_nobody.reuse(_jspx_th_aui_input_3);
    return false;
  }

  private boolean _jspx_meth_aui_input_4(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:input
    com.liferay.taglib.aui.InputTag _jspx_th_aui_input_4 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
    _jspx_th_aui_input_4.setPageContext(_jspx_page_context);
    _jspx_th_aui_input_4.setParent(null);
    _jspx_th_aui_input_4.setName("deleteGroupRolesRoleIds");
    _jspx_th_aui_input_4.setType("hidden");
    int _jspx_eval_aui_input_4 = _jspx_th_aui_input_4.doStartTag();
    if (_jspx_th_aui_input_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_input_type_name_nobody.reuse(_jspx_th_aui_input_4);
      return true;
    }
    _jspx_tagPool_aui_input_type_name_nobody.reuse(_jspx_th_aui_input_4);
    return false;
  }

  private boolean _jspx_meth_aui_input_5(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:input
    com.liferay.taglib.aui.InputTag _jspx_th_aui_input_5 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
    _jspx_th_aui_input_5.setPageContext(_jspx_page_context);
    _jspx_th_aui_input_5.setParent(null);
    _jspx_th_aui_input_5.setName("deleteRoleIds");
    _jspx_th_aui_input_5.setType("hidden");
    int _jspx_eval_aui_input_5 = _jspx_th_aui_input_5.doStartTag();
    if (_jspx_th_aui_input_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_input_type_name_nobody.reuse(_jspx_th_aui_input_5);
      return true;
    }
    _jspx_tagPool_aui_input_type_name_nobody.reuse(_jspx_th_aui_input_5);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_0 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_0.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_0.setParent(null);
    _jspx_th_liferay$1ui_message_0.setKey("regular-roles");
    int _jspx_eval_liferay$1ui_message_0 = _jspx_th_liferay$1ui_message_0.doStartTag();
    if (_jspx_th_liferay$1ui_message_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_0);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_0);
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

  private boolean _jspx_meth_liferay$1ui_message_1(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_1 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_arguments_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_1.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_0);
    _jspx_th_liferay$1ui_message_1.setArguments(new String("regular-role"));
    _jspx_th_liferay$1ui_message_1.setKey("select-x");
    int _jspx_eval_liferay$1ui_message_1 = _jspx_th_liferay$1ui_message_1.doStartTag();
    if (_jspx_th_liferay$1ui_message_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_arguments_nobody.reuse(_jspx_th_liferay$1ui_message_1);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_arguments_nobody.reuse(_jspx_th_liferay$1ui_message_1);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_2(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_2 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_2.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_0);
    int _jspx_eval_portlet_namespace_2 = _jspx_th_portlet_namespace_2.doStartTag();
    if (_jspx_th_portlet_namespace_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_2);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_2);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_2(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_2 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_2.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_2.setParent(null);
    _jspx_th_liferay$1ui_message_2.setKey("inherited-regular-roles");
    int _jspx_eval_liferay$1ui_message_2 = _jspx_th_liferay$1ui_message_2.doStartTag();
    if (_jspx_th_liferay$1ui_message_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_2);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_2);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_3(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_3 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_3.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_3);
    _jspx_th_liferay$1ui_message_3.setKey("this-user-does-not-have-any-inherited-regular-roles");
    int _jspx_eval_liferay$1ui_message_3 = _jspx_th_liferay$1ui_message_3.doStartTag();
    if (_jspx_th_liferay$1ui_message_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_3);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_3);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_search$1iterator_1(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1ui_search$1container_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:search-iterator
    com.liferay.taglib.ui.SearchIteratorTag _jspx_th_liferay$1ui_search$1iterator_1 = (com.liferay.taglib.ui.SearchIteratorTag) _jspx_tagPool_liferay$1ui_search$1iterator_markupView_nobody.get(com.liferay.taglib.ui.SearchIteratorTag.class);
    _jspx_th_liferay$1ui_search$1iterator_1.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_search$1iterator_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container_1);
    _jspx_th_liferay$1ui_search$1iterator_1.setMarkupView("lexicon");
    int _jspx_eval_liferay$1ui_search$1iterator_1 = _jspx_th_liferay$1ui_search$1iterator_1.doStartTag();
    if (_jspx_th_liferay$1ui_search$1iterator_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_search$1iterator_markupView_nobody.reuse(_jspx_th_liferay$1ui_search$1iterator_1);
      return true;
    }
    _jspx_tagPool_liferay$1ui_search$1iterator_markupView_nobody.reuse(_jspx_th_liferay$1ui_search$1iterator_1);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_4(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_4 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_4.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_4.setParent(null);
    _jspx_th_liferay$1ui_message_4.setKey("organization-roles");
    int _jspx_eval_liferay$1ui_message_4 = _jspx_th_liferay$1ui_message_4.doStartTag();
    if (_jspx_th_liferay$1ui_message_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_4);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_4);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_5(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_5, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_5 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_5.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_5);
    _jspx_th_liferay$1ui_message_5.setKey("this-user-does-not-belong-to-an-organization-to-which-an-organization-role-can-be-assigned");
    int _jspx_eval_liferay$1ui_message_5 = _jspx_th_liferay$1ui_message_5.doStartTag();
    if (_jspx_th_liferay$1ui_message_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_5);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_5);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_search$1iterator_2(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1ui_search$1container_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:search-iterator
    com.liferay.taglib.ui.SearchIteratorTag _jspx_th_liferay$1ui_search$1iterator_2 = (com.liferay.taglib.ui.SearchIteratorTag) _jspx_tagPool_liferay$1ui_search$1iterator_markupView_nobody.get(com.liferay.taglib.ui.SearchIteratorTag.class);
    _jspx_th_liferay$1ui_search$1iterator_2.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_search$1iterator_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container_2);
    _jspx_th_liferay$1ui_search$1iterator_2.setMarkupView("lexicon");
    int _jspx_eval_liferay$1ui_search$1iterator_2 = _jspx_th_liferay$1ui_search$1iterator_2.doStartTag();
    if (_jspx_th_liferay$1ui_search$1iterator_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_search$1iterator_markupView_nobody.reuse(_jspx_th_liferay$1ui_search$1iterator_2);
      return true;
    }
    _jspx_tagPool_liferay$1ui_search$1iterator_markupView_nobody.reuse(_jspx_th_liferay$1ui_search$1iterator_2);
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

  private boolean _jspx_meth_portlet_namespace_12(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_12 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_12.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_12.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_2);
    int _jspx_eval_portlet_namespace_12 = _jspx_th_portlet_namespace_12.doStartTag();
    if (_jspx_th_portlet_namespace_12.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_12);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_12);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_6(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_6 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_arguments_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_6.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_2);
    _jspx_th_liferay$1ui_message_6.setArguments(new String("organization-role"));
    _jspx_th_liferay$1ui_message_6.setKey("select-x");
    int _jspx_eval_liferay$1ui_message_6 = _jspx_th_liferay$1ui_message_6.doStartTag();
    if (_jspx_th_liferay$1ui_message_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_arguments_nobody.reuse(_jspx_th_liferay$1ui_message_6);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_arguments_nobody.reuse(_jspx_th_liferay$1ui_message_6);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_13(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_13 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_13.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_13.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_2);
    int _jspx_eval_portlet_namespace_13 = _jspx_th_portlet_namespace_13.doStartTag();
    if (_jspx_th_portlet_namespace_13.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_13);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_13);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_7(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_7 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_7.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_7.setParent(null);
    _jspx_th_liferay$1ui_message_7.setKey("site-roles");
    int _jspx_eval_liferay$1ui_message_7 = _jspx_th_liferay$1ui_message_7.doStartTag();
    if (_jspx_th_liferay$1ui_message_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_7);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_7);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_8(javax.servlet.jsp.tagext.JspTag _jspx_th_c_when_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_8 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_8.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_8.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_0);
    _jspx_th_liferay$1ui_message_8.setKey("this-user-does-not-belong-to-a-site-to-which-a-site-role-can-be-assigned");
    int _jspx_eval_liferay$1ui_message_8 = _jspx_th_liferay$1ui_message_8.doStartTag();
    if (_jspx_th_liferay$1ui_message_8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_8);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_8);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_search$1iterator_3(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1ui_search$1container_3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:search-iterator
    com.liferay.taglib.ui.SearchIteratorTag _jspx_th_liferay$1ui_search$1iterator_3 = (com.liferay.taglib.ui.SearchIteratorTag) _jspx_tagPool_liferay$1ui_search$1iterator_markupView_nobody.get(com.liferay.taglib.ui.SearchIteratorTag.class);
    _jspx_th_liferay$1ui_search$1iterator_3.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_search$1iterator_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container_3);
    _jspx_th_liferay$1ui_search$1iterator_3.setMarkupView("lexicon");
    int _jspx_eval_liferay$1ui_search$1iterator_3 = _jspx_th_liferay$1ui_search$1iterator_3.doStartTag();
    if (_jspx_th_liferay$1ui_search$1iterator_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_search$1iterator_markupView_nobody.reuse(_jspx_th_liferay$1ui_search$1iterator_3);
      return true;
    }
    _jspx_tagPool_liferay$1ui_search$1iterator_markupView_nobody.reuse(_jspx_th_liferay$1ui_search$1iterator_3);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_14(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_14 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_14.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_14.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_3);
    int _jspx_eval_portlet_namespace_14 = _jspx_th_portlet_namespace_14.doStartTag();
    if (_jspx_th_portlet_namespace_14.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_14);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_14);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_15(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_15 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_15.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_15.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_3);
    int _jspx_eval_portlet_namespace_15 = _jspx_th_portlet_namespace_15.doStartTag();
    if (_jspx_th_portlet_namespace_15.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_15);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_15);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_16(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_16 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_16.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_16.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_3);
    int _jspx_eval_portlet_namespace_16 = _jspx_th_portlet_namespace_16.doStartTag();
    if (_jspx_th_portlet_namespace_16.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_16);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_16);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_17(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_17 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_17.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_17.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_3);
    int _jspx_eval_portlet_namespace_17 = _jspx_th_portlet_namespace_17.doStartTag();
    if (_jspx_th_portlet_namespace_17.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_17);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_17);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_18(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_18 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_18.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_18.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_3);
    int _jspx_eval_portlet_namespace_18 = _jspx_th_portlet_namespace_18.doStartTag();
    if (_jspx_th_portlet_namespace_18.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_18);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_18);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_19(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_19 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_19.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_19.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_3);
    int _jspx_eval_portlet_namespace_19 = _jspx_th_portlet_namespace_19.doStartTag();
    if (_jspx_th_portlet_namespace_19.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_19);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_19);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_20(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_20 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_20.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_20.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_3);
    int _jspx_eval_portlet_namespace_20 = _jspx_th_portlet_namespace_20.doStartTag();
    if (_jspx_th_portlet_namespace_20.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_20);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_20);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_21(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_21 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_21.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_21.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_3);
    int _jspx_eval_portlet_namespace_21 = _jspx_th_portlet_namespace_21.doStartTag();
    if (_jspx_th_portlet_namespace_21.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_21);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_21);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_22(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_22 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_22.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_22.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_3);
    int _jspx_eval_portlet_namespace_22 = _jspx_th_portlet_namespace_22.doStartTag();
    if (_jspx_th_portlet_namespace_22.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_22);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_22);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_23(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_23 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_23.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_23.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_3);
    int _jspx_eval_portlet_namespace_23 = _jspx_th_portlet_namespace_23.doStartTag();
    if (_jspx_th_portlet_namespace_23.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_23);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_23);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_9(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_9 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_arguments_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_9.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_9.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_3);
    _jspx_th_liferay$1ui_message_9.setArguments(new String("site-role"));
    _jspx_th_liferay$1ui_message_9.setKey("select-x");
    int _jspx_eval_liferay$1ui_message_9 = _jspx_th_liferay$1ui_message_9.doStartTag();
    if (_jspx_th_liferay$1ui_message_9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_arguments_nobody.reuse(_jspx_th_liferay$1ui_message_9);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_arguments_nobody.reuse(_jspx_th_liferay$1ui_message_9);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_24(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_24 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_24.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_24.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_3);
    int _jspx_eval_portlet_namespace_24 = _jspx_th_portlet_namespace_24.doStartTag();
    if (_jspx_th_portlet_namespace_24.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_24);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_24);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_10(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_10 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_10.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_10.setParent(null);
    _jspx_th_liferay$1ui_message_10.setKey("inherited-site-roles");
    int _jspx_eval_liferay$1ui_message_10 = _jspx_th_liferay$1ui_message_10.doStartTag();
    if (_jspx_th_liferay$1ui_message_10.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_10);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_10);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_11(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_13, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_11 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_11.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_11.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_13);
    _jspx_th_liferay$1ui_message_11.setKey("this-user-does-not-have-any-inherited-site-roles");
    int _jspx_eval_liferay$1ui_message_11 = _jspx_th_liferay$1ui_message_11.doStartTag();
    if (_jspx_th_liferay$1ui_message_11.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_11);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_11);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_search$1iterator_4(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1ui_search$1container_4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:search-iterator
    com.liferay.taglib.ui.SearchIteratorTag _jspx_th_liferay$1ui_search$1iterator_4 = (com.liferay.taglib.ui.SearchIteratorTag) _jspx_tagPool_liferay$1ui_search$1iterator_markupView_nobody.get(com.liferay.taglib.ui.SearchIteratorTag.class);
    _jspx_th_liferay$1ui_search$1iterator_4.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_search$1iterator_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container_4);
    _jspx_th_liferay$1ui_search$1iterator_4.setMarkupView("lexicon");
    int _jspx_eval_liferay$1ui_search$1iterator_4 = _jspx_th_liferay$1ui_search$1iterator_4.doStartTag();
    if (_jspx_th_liferay$1ui_search$1iterator_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_search$1iterator_markupView_nobody.reuse(_jspx_th_liferay$1ui_search$1iterator_4);
      return true;
    }
    _jspx_tagPool_liferay$1ui_search$1iterator_markupView_nobody.reuse(_jspx_th_liferay$1ui_search$1iterator_4);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_25(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_25 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_25.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_25.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_4);
    int _jspx_eval_portlet_namespace_25 = _jspx_th_portlet_namespace_25.doStartTag();
    if (_jspx_th_portlet_namespace_25.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_25);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_25);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_26(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_26 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_26.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_26.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_4);
    int _jspx_eval_portlet_namespace_26 = _jspx_th_portlet_namespace_26.doStartTag();
    if (_jspx_th_portlet_namespace_26.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_26);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_26);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_27(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_27 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_27.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_27.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_4);
    int _jspx_eval_portlet_namespace_27 = _jspx_th_portlet_namespace_27.doStartTag();
    if (_jspx_th_portlet_namespace_27.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_27);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_27);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_28(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_28 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_28.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_28.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_4);
    int _jspx_eval_portlet_namespace_28 = _jspx_th_portlet_namespace_28.doStartTag();
    if (_jspx_th_portlet_namespace_28.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_28);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_28);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_29(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_29 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_29.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_29.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_4);
    int _jspx_eval_portlet_namespace_29 = _jspx_th_portlet_namespace_29.doStartTag();
    if (_jspx_th_portlet_namespace_29.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_29);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_29);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_30(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_30 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_30.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_30.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_4);
    int _jspx_eval_portlet_namespace_30 = _jspx_th_portlet_namespace_30.doStartTag();
    if (_jspx_th_portlet_namespace_30.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_30);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_30);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_31(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_31 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_31.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_31.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_4);
    int _jspx_eval_portlet_namespace_31 = _jspx_th_portlet_namespace_31.doStartTag();
    if (_jspx_th_portlet_namespace_31.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_31);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_31);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_32(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_32 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_32.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_32.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_4);
    int _jspx_eval_portlet_namespace_32 = _jspx_th_portlet_namespace_32.doStartTag();
    if (_jspx_th_portlet_namespace_32.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_32);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_32);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_33(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_33 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_33.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_33.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_4);
    int _jspx_eval_portlet_namespace_33 = _jspx_th_portlet_namespace_33.doStartTag();
    if (_jspx_th_portlet_namespace_33.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_33);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_33);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_34(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_34 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_34.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_34.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_4);
    int _jspx_eval_portlet_namespace_34 = _jspx_th_portlet_namespace_34.doStartTag();
    if (_jspx_th_portlet_namespace_34.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_34);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_34);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_35(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_35 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_35.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_35.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_4);
    int _jspx_eval_portlet_namespace_35 = _jspx_th_portlet_namespace_35.doStartTag();
    if (_jspx_th_portlet_namespace_35.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_35);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_35);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_36(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_36 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_36.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_36.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_4);
    int _jspx_eval_portlet_namespace_36 = _jspx_th_portlet_namespace_36.doStartTag();
    if (_jspx_th_portlet_namespace_36.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_36);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_36);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_37(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_37 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_37.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_37.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_4);
    int _jspx_eval_portlet_namespace_37 = _jspx_th_portlet_namespace_37.doStartTag();
    if (_jspx_th_portlet_namespace_37.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_37);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_37);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_38(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_38 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_38.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_38.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_4);
    int _jspx_eval_portlet_namespace_38 = _jspx_th_portlet_namespace_38.doStartTag();
    if (_jspx_th_portlet_namespace_38.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_38);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_38);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_39(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_39 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_39.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_39.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_4);
    int _jspx_eval_portlet_namespace_39 = _jspx_th_portlet_namespace_39.doStartTag();
    if (_jspx_th_portlet_namespace_39.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_39);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_39);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_40(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_40 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_40.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_40.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_4);
    int _jspx_eval_portlet_namespace_40 = _jspx_th_portlet_namespace_40.doStartTag();
    if (_jspx_th_portlet_namespace_40.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_40);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_40);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_41(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_41 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_41.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_41.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_4);
    int _jspx_eval_portlet_namespace_41 = _jspx_th_portlet_namespace_41.doStartTag();
    if (_jspx_th_portlet_namespace_41.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_41);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_41);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_42(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_42 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_42.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_42.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_4);
    int _jspx_eval_portlet_namespace_42 = _jspx_th_portlet_namespace_42.doStartTag();
    if (_jspx_th_portlet_namespace_42.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_42);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_42);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_43(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_43 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_43.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_43.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_4);
    int _jspx_eval_portlet_namespace_43 = _jspx_th_portlet_namespace_43.doStartTag();
    if (_jspx_th_portlet_namespace_43.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_43);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_43);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_44(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_44 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_44.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_44.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_4);
    int _jspx_eval_portlet_namespace_44 = _jspx_th_portlet_namespace_44.doStartTag();
    if (_jspx_th_portlet_namespace_44.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_44);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_44);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_45(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_45 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_45.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_45.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_4);
    int _jspx_eval_portlet_namespace_45 = _jspx_th_portlet_namespace_45.doStartTag();
    if (_jspx_th_portlet_namespace_45.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_45);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_45);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_46(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_46 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_46.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_46.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_4);
    int _jspx_eval_portlet_namespace_46 = _jspx_th_portlet_namespace_46.doStartTag();
    if (_jspx_th_portlet_namespace_46.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_46);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_46);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_47(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_47 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_47.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_47.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_4);
    int _jspx_eval_portlet_namespace_47 = _jspx_th_portlet_namespace_47.doStartTag();
    if (_jspx_th_portlet_namespace_47.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_47);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_47);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_48(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_48 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_48.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_48.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_4);
    int _jspx_eval_portlet_namespace_48 = _jspx_th_portlet_namespace_48.doStartTag();
    if (_jspx_th_portlet_namespace_48.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_48);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_48);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_49(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_49 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_49.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_49.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_4);
    int _jspx_eval_portlet_namespace_49 = _jspx_th_portlet_namespace_49.doStartTag();
    if (_jspx_th_portlet_namespace_49.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_49);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_49);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_50(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_50 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_50.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_50.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_4);
    int _jspx_eval_portlet_namespace_50 = _jspx_th_portlet_namespace_50.doStartTag();
    if (_jspx_th_portlet_namespace_50.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_50);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_50);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_51(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_51 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_51.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_51.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_4);
    int _jspx_eval_portlet_namespace_51 = _jspx_th_portlet_namespace_51.doStartTag();
    if (_jspx_th_portlet_namespace_51.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_51);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_51);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_52(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_52 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_52.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_52.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_4);
    int _jspx_eval_portlet_namespace_52 = _jspx_th_portlet_namespace_52.doStartTag();
    if (_jspx_th_portlet_namespace_52.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_52);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_52);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_53(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_53 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_53.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_53.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_4);
    int _jspx_eval_portlet_namespace_53 = _jspx_th_portlet_namespace_53.doStartTag();
    if (_jspx_th_portlet_namespace_53.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_53);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_53);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_54(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_54 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_54.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_54.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_4);
    int _jspx_eval_portlet_namespace_54 = _jspx_th_portlet_namespace_54.doStartTag();
    if (_jspx_th_portlet_namespace_54.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_54);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_54);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_55(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_55 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_55.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_55.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_4);
    int _jspx_eval_portlet_namespace_55 = _jspx_th_portlet_namespace_55.doStartTag();
    if (_jspx_th_portlet_namespace_55.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_55);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_55);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_56(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_56 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_56.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_56.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_4);
    int _jspx_eval_portlet_namespace_56 = _jspx_th_portlet_namespace_56.doStartTag();
    if (_jspx_th_portlet_namespace_56.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_56);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_56);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_57(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_57 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_57.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_57.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_4);
    int _jspx_eval_portlet_namespace_57 = _jspx_th_portlet_namespace_57.doStartTag();
    if (_jspx_th_portlet_namespace_57.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_57);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_57);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_58(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_58 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_58.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_58.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_4);
    int _jspx_eval_portlet_namespace_58 = _jspx_th_portlet_namespace_58.doStartTag();
    if (_jspx_th_portlet_namespace_58.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_58);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_58);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_59(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_59 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_59.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_59.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_4);
    int _jspx_eval_portlet_namespace_59 = _jspx_th_portlet_namespace_59.doStartTag();
    if (_jspx_th_portlet_namespace_59.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_59);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_59);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_60(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_60 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_60.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_60.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_4);
    int _jspx_eval_portlet_namespace_60 = _jspx_th_portlet_namespace_60.doStartTag();
    if (_jspx_th_portlet_namespace_60.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_60);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_60);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_61(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_61 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_61.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_61.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_4);
    int _jspx_eval_portlet_namespace_61 = _jspx_th_portlet_namespace_61.doStartTag();
    if (_jspx_th_portlet_namespace_61.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_61);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_61);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_62(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_62 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_62.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_62.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_4);
    int _jspx_eval_portlet_namespace_62 = _jspx_th_portlet_namespace_62.doStartTag();
    if (_jspx_th_portlet_namespace_62.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_62);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_62);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_63(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_63 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_63.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_63.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_4);
    int _jspx_eval_portlet_namespace_63 = _jspx_th_portlet_namespace_63.doStartTag();
    if (_jspx_th_portlet_namespace_63.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_63);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_63);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_64(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_64 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_64.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_64.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_4);
    int _jspx_eval_portlet_namespace_64 = _jspx_th_portlet_namespace_64.doStartTag();
    if (_jspx_th_portlet_namespace_64.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_64);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_64);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_65(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_65 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_65.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_65.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_4);
    int _jspx_eval_portlet_namespace_65 = _jspx_th_portlet_namespace_65.doStartTag();
    if (_jspx_th_portlet_namespace_65.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_65);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_65);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_66(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_66 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_66.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_66.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_4);
    int _jspx_eval_portlet_namespace_66 = _jspx_th_portlet_namespace_66.doStartTag();
    if (_jspx_th_portlet_namespace_66.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_66);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_66);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_67(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_67 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_67.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_67.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_4);
    int _jspx_eval_portlet_namespace_67 = _jspx_th_portlet_namespace_67.doStartTag();
    if (_jspx_th_portlet_namespace_67.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_67);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_67);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_68(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_68 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_68.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_68.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_4);
    int _jspx_eval_portlet_namespace_68 = _jspx_th_portlet_namespace_68.doStartTag();
    if (_jspx_th_portlet_namespace_68.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_68);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_68);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_69(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_69 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_69.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_69.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_4);
    int _jspx_eval_portlet_namespace_69 = _jspx_th_portlet_namespace_69.doStartTag();
    if (_jspx_th_portlet_namespace_69.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_69);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_69);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_70(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_70 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_70.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_70.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_4);
    int _jspx_eval_portlet_namespace_70 = _jspx_th_portlet_namespace_70.doStartTag();
    if (_jspx_th_portlet_namespace_70.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_70);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_70);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_71(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_71 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_71.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_71.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_4);
    int _jspx_eval_portlet_namespace_71 = _jspx_th_portlet_namespace_71.doStartTag();
    if (_jspx_th_portlet_namespace_71.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_71);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_71);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_72(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_72 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_72.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_72.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_4);
    int _jspx_eval_portlet_namespace_72 = _jspx_th_portlet_namespace_72.doStartTag();
    if (_jspx_th_portlet_namespace_72.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_72);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_72);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_73(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_73 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_73.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_73.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_4);
    int _jspx_eval_portlet_namespace_73 = _jspx_th_portlet_namespace_73.doStartTag();
    if (_jspx_th_portlet_namespace_73.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_73);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_73);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_74(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_74 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_74.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_74.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_4);
    int _jspx_eval_portlet_namespace_74 = _jspx_th_portlet_namespace_74.doStartTag();
    if (_jspx_th_portlet_namespace_74.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_74);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_74);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_75(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_75 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_75.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_75.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_4);
    int _jspx_eval_portlet_namespace_75 = _jspx_th_portlet_namespace_75.doStartTag();
    if (_jspx_th_portlet_namespace_75.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_75);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_75);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_76(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_76 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_76.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_76.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_4);
    int _jspx_eval_portlet_namespace_76 = _jspx_th_portlet_namespace_76.doStartTag();
    if (_jspx_th_portlet_namespace_76.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_76);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_76);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_77(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_77 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_77.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_77.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_4);
    int _jspx_eval_portlet_namespace_77 = _jspx_th_portlet_namespace_77.doStartTag();
    if (_jspx_th_portlet_namespace_77.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_77);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_77);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_78(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_78 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_78.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_78.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_4);
    int _jspx_eval_portlet_namespace_78 = _jspx_th_portlet_namespace_78.doStartTag();
    if (_jspx_th_portlet_namespace_78.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_78);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_78);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_79(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_79 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_79.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_79.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_4);
    int _jspx_eval_portlet_namespace_79 = _jspx_th_portlet_namespace_79.doStartTag();
    if (_jspx_th_portlet_namespace_79.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_79);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_79);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_80(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_80 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_80.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_80.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_4);
    int _jspx_eval_portlet_namespace_80 = _jspx_th_portlet_namespace_80.doStartTag();
    if (_jspx_th_portlet_namespace_80.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_80);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_80);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_81(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_81 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_81.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_81.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_4);
    int _jspx_eval_portlet_namespace_81 = _jspx_th_portlet_namespace_81.doStartTag();
    if (_jspx_th_portlet_namespace_81.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_81);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_81);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_82(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_82 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_82.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_82.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_4);
    int _jspx_eval_portlet_namespace_82 = _jspx_th_portlet_namespace_82.doStartTag();
    if (_jspx_th_portlet_namespace_82.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_82);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_82);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_83(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_83 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_83.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_83.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_4);
    int _jspx_eval_portlet_namespace_83 = _jspx_th_portlet_namespace_83.doStartTag();
    if (_jspx_th_portlet_namespace_83.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_83);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_83);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_84(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_84 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_84.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_84.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_4);
    int _jspx_eval_portlet_namespace_84 = _jspx_th_portlet_namespace_84.doStartTag();
    if (_jspx_th_portlet_namespace_84.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_84);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_84);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_85(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_85 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_85.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_85.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_4);
    int _jspx_eval_portlet_namespace_85 = _jspx_th_portlet_namespace_85.doStartTag();
    if (_jspx_th_portlet_namespace_85.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_85);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_85);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_86(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_86 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_86.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_86.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_4);
    int _jspx_eval_portlet_namespace_86 = _jspx_th_portlet_namespace_86.doStartTag();
    if (_jspx_th_portlet_namespace_86.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_86);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_86);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_87(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_87 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_87.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_87.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_4);
    int _jspx_eval_portlet_namespace_87 = _jspx_th_portlet_namespace_87.doStartTag();
    if (_jspx_th_portlet_namespace_87.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_87);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_87);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_88(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_88 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_88.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_88.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_4);
    int _jspx_eval_portlet_namespace_88 = _jspx_th_portlet_namespace_88.doStartTag();
    if (_jspx_th_portlet_namespace_88.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_88);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_88);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_89(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_5, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_89 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_89.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_89.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_5);
    int _jspx_eval_portlet_namespace_89 = _jspx_th_portlet_namespace_89.doStartTag();
    if (_jspx_th_portlet_namespace_89.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_89);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_89);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_90(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_5, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_90 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_90.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_90.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_5);
    int _jspx_eval_portlet_namespace_90 = _jspx_th_portlet_namespace_90.doStartTag();
    if (_jspx_th_portlet_namespace_90.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_90);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_90);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_91(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_5, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_91 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_91.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_91.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_5);
    int _jspx_eval_portlet_namespace_91 = _jspx_th_portlet_namespace_91.doStartTag();
    if (_jspx_th_portlet_namespace_91.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_91);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_91);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_92(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_5, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_92 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_92.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_92.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_5);
    int _jspx_eval_portlet_namespace_92 = _jspx_th_portlet_namespace_92.doStartTag();
    if (_jspx_th_portlet_namespace_92.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_92);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_92);
    return false;
  }
}
