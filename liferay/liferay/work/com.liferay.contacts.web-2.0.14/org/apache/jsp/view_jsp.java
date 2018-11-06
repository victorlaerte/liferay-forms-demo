package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.liferay.admin.kernel.util.PortalSearchApplicationType;
import com.liferay.asset.kernel.model.AssetTag;
import com.liferay.asset.kernel.service.AssetTagLocalServiceUtil;
import com.liferay.contacts.constants.ContactsConstants;
import com.liferay.contacts.constants.ContactsWebKeys;
import com.liferay.contacts.model.Entry;
import com.liferay.contacts.service.EntryLocalServiceUtil;
import com.liferay.contacts.util.ContactsExtensionsUtil;
import com.liferay.contacts.util.ContactsUtil;
import com.liferay.contacts.web.internal.constants.ContactsPortletKeys;
import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.language.UnicodeLanguageUtil;
import com.liferay.portal.kernel.model.Address;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.Contact;
import com.liferay.portal.kernel.model.EmailAddress;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.model.Phone;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.UserConstants;
import com.liferay.portal.kernel.model.Website;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.portlet.PortletProvider;
import com.liferay.portal.kernel.portlet.PortletProviderUtil;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.AddressServiceUtil;
import com.liferay.portal.kernel.service.EmailAddressServiceUtil;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;
import com.liferay.portal.kernel.service.PhoneServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.service.WebsiteServiceUtil;
import com.liferay.portal.kernel.service.permission.UserPermissionUtil;
import com.liferay.portal.kernel.servlet.ServletContextPool;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PortletKeys;
import com.liferay.portal.kernel.util.PrefsParamUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.TextFormatter;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.comparator.UserLastNameComparator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.social.kernel.model.SocialRelationConstants;
import com.liferay.social.kernel.model.SocialRequestConstants;
import com.liferay.social.kernel.service.SocialActivityLocalServiceUtil;
import com.liferay.social.kernel.service.SocialRelationLocalServiceUtil;
import com.liferay.social.kernel.service.SocialRequestLocalServiceUtil;
import com.liferay.users.admin.configuration.UserFileUploadsConfiguration;
import com.liferay.users.admin.kernel.util.UsersAdminUtil;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.WindowState;

public final class view_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(1);
    _jspx_dependants.add("/init.jsp");
  }

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_input_type_name_label_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_row;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_param_value_name_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_if_test;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_option_value_label_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_button_value_id_icon_cssClass_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_col_width_cssClass;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_choose;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_script_use;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1util_include_servletContext_page_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_when_test;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1portlet_renderURL_windowState_var;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_namespace_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_select_value_name_label_inlineField_cssClass;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_row_cssClass;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_message_key_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_input_value_type_name_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_header_title_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_otherwise;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_form_name_method_action;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_renderURL_windowState;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_col_width_first_cssClass;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1theme_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_message_unicode_key_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_resourceURL_id;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_input_value_type_size_name_label_id_cssClass_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_resourceURL_id_nobody;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_aui_input_type_name_label_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_row = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_param_value_name_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_if_test = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_option_value_label_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_button_value_id_icon_cssClass_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_col_width_cssClass = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_choose = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_script_use = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1util_include_servletContext_page_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_when_test = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1portlet_renderURL_windowState_var = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_namespace_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_select_value_name_label_inlineField_cssClass = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_row_cssClass = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_message_key_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_input_value_type_name_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_header_title_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_otherwise = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_form_name_method_action = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_renderURL_windowState = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_col_width_first_cssClass = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1theme_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_message_unicode_key_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_resourceURL_id = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_input_value_type_size_name_label_id_cssClass_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_resourceURL_id_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_aui_input_type_name_label_nobody.release();
    _jspx_tagPool_aui_row.release();
    _jspx_tagPool_portlet_param_value_name_nobody.release();
    _jspx_tagPool_c_if_test.release();
    _jspx_tagPool_aui_option_value_label_nobody.release();
    _jspx_tagPool_aui_button_value_id_icon_cssClass_nobody.release();
    _jspx_tagPool_aui_col_width_cssClass.release();
    _jspx_tagPool_c_choose.release();
    _jspx_tagPool_aui_script_use.release();
    _jspx_tagPool_liferay$1util_include_servletContext_page_nobody.release();
    _jspx_tagPool_c_when_test.release();
    _jspx_tagPool_liferay$1portlet_renderURL_windowState_var.release();
    _jspx_tagPool_portlet_namespace_nobody.release();
    _jspx_tagPool_aui_select_value_name_label_inlineField_cssClass.release();
    _jspx_tagPool_aui_row_cssClass.release();
    _jspx_tagPool_liferay$1ui_message_key_nobody.release();
    _jspx_tagPool_aui_input_value_type_name_nobody.release();
    _jspx_tagPool_liferay$1ui_header_title_nobody.release();
    _jspx_tagPool_c_otherwise.release();
    _jspx_tagPool_portlet_defineObjects_nobody.release();
    _jspx_tagPool_aui_form_name_method_action.release();
    _jspx_tagPool_portlet_renderURL_windowState.release();
    _jspx_tagPool_aui_col_width_first_cssClass.release();
    _jspx_tagPool_liferay$1theme_defineObjects_nobody.release();
    _jspx_tagPool_liferay$1ui_message_unicode_key_nobody.release();
    _jspx_tagPool_portlet_resourceURL_id.release();
    _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody.release();
    _jspx_tagPool_aui_input_value_type_size_name_label_id_cssClass_nobody.release();
    _jspx_tagPool_portlet_resourceURL_id_nobody.release();
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

WindowState windowState = renderRequest.getWindowState();

String currentURL = PortalUtil.getCurrentURL(request);

String portletResource = ParamUtil.getString(request, "portletResource");

int displayStyle = PrefsParamUtil.getInteger(portletPreferences, request, "displayStyle", ContactsConstants.DISPLAY_STYLE_FULL);

boolean showAdditionalEmailAddresses = PrefsParamUtil.getBoolean(portletPreferences, request, "showAdditionalEmailAddresses", true);
boolean showAddresses = PrefsParamUtil.getBoolean(portletPreferences, request, "showAddresses", true);
boolean showComments = PrefsParamUtil.getBoolean(portletPreferences, request, "showComments", true);
boolean showCompleteYourProfile = PrefsParamUtil.getBoolean(portletPreferences, request, "showCompleteYourProfile", false);
boolean showIcon = PrefsParamUtil.getBoolean(portletPreferences, request, "showIcon", true);
boolean showInstantMessenger = PrefsParamUtil.getBoolean(portletPreferences, request, "showInstantMessenger", true);
boolean showPhones = PrefsParamUtil.getBoolean(portletPreferences, request, "showPhones", true);
boolean showRecentActivity = PrefsParamUtil.getBoolean(portletPreferences, request, "showRecentActivity", false);

boolean showOnlySiteMembers = false;

String portletId = portletDisplay.getId();

if (portletId.equals(ContactsPortletKeys.MEMBERS)) {
	showOnlySiteMembers = true;
}

boolean showSites = PrefsParamUtil.getBoolean(portletPreferences, request, "showSites", false);
boolean showSMS = PrefsParamUtil.getBoolean(portletPreferences, request, "showSMS", true);
boolean showSocialNetwork = PrefsParamUtil.getBoolean(portletPreferences, request, "showSocialNetwork", true);
boolean showTags = PrefsParamUtil.getBoolean(portletPreferences, request, "showTags", false);
boolean showUsersInformation = PrefsParamUtil.getBoolean(portletPreferences, request, "showUsersInformation", true);
boolean showWebsites = PrefsParamUtil.getBoolean(portletPreferences, request, "showWebsites", true);

      out.write('\n');
      out.write('\n');

String filterBy = ParamUtil.getString(request, "filterBy", ContactsConstants.FILTER_BY_DEFAULT);

String name = ParamUtil.getString(request, "name");

boolean userPublicPage = false;

Group group = themeDisplay.getScopeGroup();

if (group.isUser() && layout.isPublicLayout()) {
	userPublicPage = true;
}

LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

params.put("inherit", Boolean.TRUE);

if (userPublicPage) {
	params.put("socialRelation", new Long[] {group.getClassPK()});
}
else if (filterBy.startsWith(ContactsConstants.FILTER_BY_TYPE)) {
	params.put("socialRelationType", new Long[] {themeDisplay.getUserId(), ContactsUtil.getSocialRelationType(filterBy)});
}

if (showOnlySiteMembers) {
	params.put("usersGroups", Long.valueOf(group.getGroupId()));
}
else if (filterBy.startsWith(ContactsConstants.FILTER_BY_GROUP)) {
	params.put("usersGroups", ContactsUtil.getGroupId(filterBy));
}

List<BaseModel<?>> contacts = null;
int contactsCount = 0;

if (userPublicPage) {
	List<User> users = UserLocalServiceUtil.getSocialUsers(group.getClassPK(), SocialRelationConstants.TYPE_BI_CONNECTION, StringPool.EQUAL, 0, ContactsConstants.MAX_RESULT_COUNT, new UserLastNameComparator(true));

	contacts = new ArrayList<BaseModel<?>>(users);
	contactsCount = UserLocalServiceUtil.getSocialUsersCount(group.getClassPK(), SocialRelationConstants.TYPE_BI_CONNECTION, StringPool.EQUAL);
}
else if (showOnlySiteMembers || !filterBy.equals(ContactsConstants.FILTER_BY_DEFAULT)) {
	List<User> users = UserLocalServiceUtil.search(company.getCompanyId(), name, WorkflowConstants.STATUS_APPROVED, params, 0, ContactsConstants.MAX_RESULT_COUNT, new UserLastNameComparator(true));

	contacts = new ArrayList<BaseModel<?>>(users);
	contactsCount = UserLocalServiceUtil.searchCount(themeDisplay.getCompanyId(), name, WorkflowConstants.STATUS_APPROVED, params);
}
else {
	contacts = EntryLocalServiceUtil.searchUsersAndContacts(themeDisplay.getCompanyId(), user.getUserId(), name, 0, ContactsConstants.MAX_RESULT_COUNT);
	contactsCount = EntryLocalServiceUtil.searchUsersAndContactsCount(themeDisplay.getCompanyId(), user.getUserId(), name);
}

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setWindowState(WindowState.NORMAL);

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
        _jspx_th_c_when_0.setTest( userPublicPage && (contactsCount <= 0) );
        int _jspx_eval_c_when_0 = _jspx_th_c_when_0.doStartTag();
        if (_jspx_eval_c_when_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          out.write("\n\t\t");
          //  aui:row
          com.liferay.taglib.aui.RowTag _jspx_th_aui_row_0 = (com.liferay.taglib.aui.RowTag) _jspx_tagPool_aui_row.get(com.liferay.taglib.aui.RowTag.class);
          _jspx_th_aui_row_0.setPageContext(_jspx_page_context);
          _jspx_th_aui_row_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_0);
          int _jspx_eval_aui_row_0 = _jspx_th_aui_row_0.doStartTag();
          if (_jspx_eval_aui_row_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t\t");
            //  aui:col
            com.liferay.taglib.aui.ColTag _jspx_th_aui_col_0 = (com.liferay.taglib.aui.ColTag) _jspx_tagPool_aui_col_width_cssClass.get(com.liferay.taglib.aui.ColTag.class);
            _jspx_th_aui_col_0.setPageContext(_jspx_page_context);
            _jspx_th_aui_col_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_row_0);
            _jspx_th_aui_col_0.setCssClass("contacts-center-home");
            _jspx_th_aui_col_0.setWidth( 100 );
            int _jspx_eval_aui_col_0 = _jspx_th_aui_col_0.doStartTag();
            if (_jspx_eval_aui_col_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\t\t\t\t<h3 class=\"header-title\">\n\t\t\t\t\t");
              //  liferay-ui:message
              com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_0 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody.get(com.liferay.taglib.ui.MessageTag.class);
              _jspx_th_liferay$1ui_message_0.setPageContext(_jspx_page_context);
              _jspx_th_liferay$1ui_message_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_col_0);
              _jspx_th_liferay$1ui_message_0.setArguments( new Object[] {HtmlUtil.escape(group.getDescriptiveName(locale)), String.valueOf(contactsCount)} );
              _jspx_th_liferay$1ui_message_0.setKey( userPublicPage ? "x-has-no-connections" : "x-has-no-contacts" );
              _jspx_th_liferay$1ui_message_0.setTranslateArguments( false );
              int _jspx_eval_liferay$1ui_message_0 = _jspx_th_liferay$1ui_message_0.doStartTag();
              if (_jspx_th_liferay$1ui_message_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody.reuse(_jspx_th_liferay$1ui_message_0);
                return;
              }
              _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody.reuse(_jspx_th_liferay$1ui_message_0);
              out.write("\n\t\t\t\t</h3>\n\t\t\t");
            }
            if (_jspx_th_aui_col_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_aui_col_width_cssClass.reuse(_jspx_th_aui_col_0);
              return;
            }
            _jspx_tagPool_aui_col_width_cssClass.reuse(_jspx_th_aui_col_0);
            out.write("\n\t\t");
          }
          if (_jspx_th_aui_row_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_aui_row.reuse(_jspx_th_aui_row_0);
            return;
          }
          _jspx_tagPool_aui_row.reuse(_jspx_th_aui_row_0);
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
        //  c:otherwise
        com.liferay.taglib.core.OtherwiseTag _jspx_th_c_otherwise_0 = (com.liferay.taglib.core.OtherwiseTag) _jspx_tagPool_c_otherwise.get(com.liferay.taglib.core.OtherwiseTag.class);
        _jspx_th_c_otherwise_0.setPageContext(_jspx_page_context);
        _jspx_th_c_otherwise_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_0);
        int _jspx_eval_c_otherwise_0 = _jspx_th_c_otherwise_0.doStartTag();
        if (_jspx_eval_c_otherwise_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          out.write("\n\t\t");
          //  aui:form
          com.liferay.taglib.aui.FormTag _jspx_th_aui_form_0 = (com.liferay.taglib.aui.FormTag) _jspx_tagPool_aui_form_name_method_action.get(com.liferay.taglib.aui.FormTag.class);
          _jspx_th_aui_form_0.setPageContext(_jspx_page_context);
          _jspx_th_aui_form_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_0);
          _jspx_th_aui_form_0.setAction( portletURL.toString() );
          _jspx_th_aui_form_0.setMethod("post");
          _jspx_th_aui_form_0.setName("fm");
          int _jspx_eval_aui_form_0 = _jspx_th_aui_form_0.doStartTag();
          if (_jspx_eval_aui_form_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t\t");
            //  aui:input
            com.liferay.taglib.aui.InputTag _jspx_th_aui_input_0 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
            _jspx_th_aui_input_0.setPageContext(_jspx_page_context);
            _jspx_th_aui_input_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
            _jspx_th_aui_input_0.setName( Constants.CMD );
            _jspx_th_aui_input_0.setType("hidden");
            _jspx_th_aui_input_0.setValue(new String(""));
            int _jspx_eval_aui_input_0 = _jspx_th_aui_input_0.doStartTag();
            if (_jspx_th_aui_input_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_0);
              return;
            }
            _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_0);
            out.write("\n\t\t\t");
            if (_jspx_meth_aui_input_1((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_form_0, _jspx_page_context))
              return;
            out.write("\n\t\t\t");
            if (_jspx_meth_aui_input_2((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_form_0, _jspx_page_context))
              return;
            out.write("\n\t\t\t");
            if (_jspx_meth_aui_input_3((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_form_0, _jspx_page_context))
              return;
            out.write("\n\n\t\t\t");
            //  aui:row
            com.liferay.taglib.aui.RowTag _jspx_th_aui_row_1 = (com.liferay.taglib.aui.RowTag) _jspx_tagPool_aui_row.get(com.liferay.taglib.aui.RowTag.class);
            _jspx_th_aui_row_1.setPageContext(_jspx_page_context);
            _jspx_th_aui_row_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
            int _jspx_eval_aui_row_1 = _jspx_th_aui_row_1.doStartTag();
            if (_jspx_eval_aui_row_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\t\t\t\t");
              //  aui:col
              com.liferay.taglib.aui.ColTag _jspx_th_aui_col_1 = (com.liferay.taglib.aui.ColTag) _jspx_tagPool_aui_col_width_cssClass.get(com.liferay.taglib.aui.ColTag.class);
              _jspx_th_aui_col_1.setPageContext(_jspx_page_context);
              _jspx_th_aui_col_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_row_1);
              _jspx_th_aui_col_1.setCssClass("toolbar");
              _jspx_th_aui_col_1.setWidth( 100 );
              int _jspx_eval_aui_col_1 = _jspx_th_aui_col_1.doStartTag();
              if (_jspx_eval_aui_col_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\t\t\t\t\t<div class=\"filter-container\">\n\t\t\t\t\t\t");
                //  aui:row
                com.liferay.taglib.aui.RowTag _jspx_th_aui_row_2 = (com.liferay.taglib.aui.RowTag) _jspx_tagPool_aui_row.get(com.liferay.taglib.aui.RowTag.class);
                _jspx_th_aui_row_2.setPageContext(_jspx_page_context);
                _jspx_th_aui_row_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_col_1);
                int _jspx_eval_aui_row_2 = _jspx_th_aui_row_2.doStartTag();
                if (_jspx_eval_aui_row_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  out.write("\n\t\t\t\t\t\t\t");
                  //  aui:col
                  com.liferay.taglib.aui.ColTag _jspx_th_aui_col_2 = (com.liferay.taglib.aui.ColTag) _jspx_tagPool_aui_col_width_cssClass.get(com.liferay.taglib.aui.ColTag.class);
                  _jspx_th_aui_col_2.setPageContext(_jspx_page_context);
                  _jspx_th_aui_col_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_row_2);
                  _jspx_th_aui_col_2.setCssClass("contact-group-filter");
                  _jspx_th_aui_col_2.setWidth( 100 );
                  int _jspx_eval_aui_col_2 = _jspx_th_aui_col_2.doStartTag();
                  if (_jspx_eval_aui_col_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    out.write("\n\t\t\t\t\t\t\t\t");
                    if (_jspx_meth_aui_input_4((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_col_2, _jspx_page_context))
                      return;
                    out.write("\n\n\t\t\t\t\t\t\t\t");
                    //  c:if
                    com.liferay.taglib.core.IfTag _jspx_th_c_if_0 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                    _jspx_th_c_if_0.setPageContext(_jspx_page_context);
                    _jspx_th_c_if_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_col_2);
                    _jspx_th_c_if_0.setTest( !userPublicPage );
                    int _jspx_eval_c_if_0 = _jspx_th_c_if_0.doStartTag();
                    if (_jspx_eval_c_if_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      out.write("\n\t\t\t\t\t\t\t\t\t");
                      //  aui:select
                      com.liferay.taglib.aui.SelectTag _jspx_th_aui_select_0 = (com.liferay.taglib.aui.SelectTag) _jspx_tagPool_aui_select_value_name_label_inlineField_cssClass.get(com.liferay.taglib.aui.SelectTag.class);
                      _jspx_th_aui_select_0.setPageContext(_jspx_page_context);
                      _jspx_th_aui_select_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_0);
                      _jspx_th_aui_select_0.setCssClass("contact-group-filter-select");
                      _jspx_th_aui_select_0.setInlineField( true );
                      _jspx_th_aui_select_0.setLabel("");
                      _jspx_th_aui_select_0.setName("filterBy");
                      _jspx_th_aui_select_0.setValue( filterBy );
                      int _jspx_eval_aui_select_0 = _jspx_th_aui_select_0.doStartTag();
                      if (_jspx_eval_aui_select_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                        if (_jspx_eval_aui_select_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                          out = _jspx_page_context.pushBody();
                          _jspx_th_aui_select_0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                          _jspx_th_aui_select_0.doInitBody();
                        }
                        do {
                          out.write("\n\t\t\t\t\t\t\t\t\t\t");
                          //  aui:option
                          com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_0 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_value_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
                          _jspx_th_aui_option_0.setPageContext(_jspx_page_context);
                          _jspx_th_aui_option_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_0);
                          _jspx_th_aui_option_0.setLabel(new String("all"));
                          _jspx_th_aui_option_0.setValue( ContactsConstants.FILTER_BY_DEFAULT );
                          int _jspx_eval_aui_option_0 = _jspx_th_aui_option_0.doStartTag();
                          if (_jspx_th_aui_option_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                            _jspx_tagPool_aui_option_value_label_nobody.reuse(_jspx_th_aui_option_0);
                            return;
                          }
                          _jspx_tagPool_aui_option_value_label_nobody.reuse(_jspx_th_aui_option_0);
                          out.write("\n\n\t\t\t\t\t\t\t\t\t\t");
                          //  c:if
                          com.liferay.taglib.core.IfTag _jspx_th_c_if_1 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                          _jspx_th_c_if_1.setPageContext(_jspx_page_context);
                          _jspx_th_c_if_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_0);
                          _jspx_th_c_if_1.setTest( showOnlySiteMembers );
                          int _jspx_eval_c_if_1 = _jspx_th_c_if_1.doStartTag();
                          if (_jspx_eval_c_if_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                            out.write("\n\t\t\t\t\t\t\t\t\t\t\t");
                            //  aui:option
                            com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_1 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_value_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
                            _jspx_th_aui_option_1.setPageContext(_jspx_page_context);
                            _jspx_th_aui_option_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_1);
                            _jspx_th_aui_option_1.setLabel(new String("administrators"));
                            _jspx_th_aui_option_1.setValue( ContactsConstants.FILTER_BY_ADMINS );
                            int _jspx_eval_aui_option_1 = _jspx_th_aui_option_1.doStartTag();
                            if (_jspx_th_aui_option_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_aui_option_value_label_nobody.reuse(_jspx_th_aui_option_1);
                              return;
                            }
                            _jspx_tagPool_aui_option_value_label_nobody.reuse(_jspx_th_aui_option_1);
                            out.write("\n\t\t\t\t\t\t\t\t\t\t");
                          }
                          if (_jspx_th_c_if_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_1);
                            return;
                          }
                          _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_1);
                          out.write("\n\n\t\t\t\t\t\t\t\t\t\t");
                          //  aui:option
                          com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_2 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_value_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
                          _jspx_th_aui_option_2.setPageContext(_jspx_page_context);
                          _jspx_th_aui_option_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_0);
                          _jspx_th_aui_option_2.setLabel(new String("connections"));
                          _jspx_th_aui_option_2.setValue( ContactsConstants.FILTER_BY_TYPE_BI_CONNECTION );
                          int _jspx_eval_aui_option_2 = _jspx_th_aui_option_2.doStartTag();
                          if (_jspx_th_aui_option_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                            _jspx_tagPool_aui_option_value_label_nobody.reuse(_jspx_th_aui_option_2);
                            return;
                          }
                          _jspx_tagPool_aui_option_value_label_nobody.reuse(_jspx_th_aui_option_2);
                          out.write("\n\t\t\t\t\t\t\t\t\t\t");
                          //  aui:option
                          com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_3 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_value_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
                          _jspx_th_aui_option_3.setPageContext(_jspx_page_context);
                          _jspx_th_aui_option_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_0);
                          _jspx_th_aui_option_3.setLabel(new String("following"));
                          _jspx_th_aui_option_3.setValue( ContactsConstants.FILTER_BY_TYPE_UNI_FOLLOWER );
                          int _jspx_eval_aui_option_3 = _jspx_th_aui_option_3.doStartTag();
                          if (_jspx_th_aui_option_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                            _jspx_tagPool_aui_option_value_label_nobody.reuse(_jspx_th_aui_option_3);
                            return;
                          }
                          _jspx_tagPool_aui_option_value_label_nobody.reuse(_jspx_th_aui_option_3);
                          out.write("\n\n\t\t\t\t\t\t\t\t\t\t");
                          //  c:if
                          com.liferay.taglib.core.IfTag _jspx_th_c_if_2 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                          _jspx_th_c_if_2.setPageContext(_jspx_page_context);
                          _jspx_th_c_if_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_0);
                          _jspx_th_c_if_2.setTest( !showOnlySiteMembers );
                          int _jspx_eval_c_if_2 = _jspx_th_c_if_2.doStartTag();
                          if (_jspx_eval_c_if_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                            out.write("\n\t\t\t\t\t\t\t\t\t\t\t");
                            //  aui:option
                            com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_4 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_value_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
                            _jspx_th_aui_option_4.setPageContext(_jspx_page_context);
                            _jspx_th_aui_option_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_2);
                            _jspx_th_aui_option_4.setLabel(new String("followers"));
                            _jspx_th_aui_option_4.setValue( ContactsConstants.FILTER_BY_FOLLOWERS );
                            int _jspx_eval_aui_option_4 = _jspx_th_aui_option_4.doStartTag();
                            if (_jspx_th_aui_option_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_aui_option_value_label_nobody.reuse(_jspx_th_aui_option_4);
                              return;
                            }
                            _jspx_tagPool_aui_option_value_label_nobody.reuse(_jspx_th_aui_option_4);
                            out.write("\n\t\t\t\t\t\t\t\t\t\t\t");
                            //  aui:option
                            com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_5 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_value_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
                            _jspx_th_aui_option_5.setPageContext(_jspx_page_context);
                            _jspx_th_aui_option_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_2);
                            _jspx_th_aui_option_5.setLabel(new String("my-contacts"));
                            _jspx_th_aui_option_5.setValue( ContactsConstants.FILTER_BY_TYPE_MY_CONTACTS );
                            int _jspx_eval_aui_option_5 = _jspx_th_aui_option_5.doStartTag();
                            if (_jspx_th_aui_option_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_aui_option_value_label_nobody.reuse(_jspx_th_aui_option_5);
                              return;
                            }
                            _jspx_tagPool_aui_option_value_label_nobody.reuse(_jspx_th_aui_option_5);
                            out.write("\n\n\t\t\t\t\t\t\t\t\t\t\t");

											List<Group> groups = user.getGroups();
											
                            out.write("\n\n\t\t\t\t\t\t\t\t\t\t\t<optgroup label=\"");
                            if (_jspx_meth_liferay$1ui_message_1((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_2, _jspx_page_context))
                              return;
                            out.write("\">\n\n\t\t\t\t\t\t\t\t\t\t\t\t");

												for (Group curGroup : groups) {
													String filterByGroupId = ContactsConstants.FILTER_BY_GROUP + curGroup.getGroupId();
												
                            out.write("\n\n\t\t\t\t\t\t\t\t\t\t\t\t\t");
                            //  aui:option
                            com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_6 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_value_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
                            _jspx_th_aui_option_6.setPageContext(_jspx_page_context);
                            _jspx_th_aui_option_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_2);
                            _jspx_th_aui_option_6.setLabel( HtmlUtil.escape(curGroup.getDescriptiveName(locale)) );
                            _jspx_th_aui_option_6.setValue( filterByGroupId );
                            int _jspx_eval_aui_option_6 = _jspx_th_aui_option_6.doStartTag();
                            if (_jspx_th_aui_option_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_aui_option_value_label_nobody.reuse(_jspx_th_aui_option_6);
                              return;
                            }
                            _jspx_tagPool_aui_option_value_label_nobody.reuse(_jspx_th_aui_option_6);
                            out.write("\n\n\t\t\t\t\t\t\t\t\t\t\t\t");

												}
												
                            out.write("\n\n\t\t\t\t\t\t\t\t\t\t\t</optgroup>\n\t\t\t\t\t\t\t\t\t\t");
                          }
                          if (_jspx_th_c_if_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_2);
                            return;
                          }
                          _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_2);
                          out.write("\n\t\t\t\t\t\t\t\t\t");
                          int evalDoAfterBody = _jspx_th_aui_select_0.doAfterBody();
                          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                            break;
                        } while (true);
                        if (_jspx_eval_aui_select_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                          out = _jspx_page_context.popBody();
                      }
                      if (_jspx_th_aui_select_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_aui_select_value_name_label_inlineField_cssClass.reuse(_jspx_th_aui_select_0);
                        return;
                      }
                      _jspx_tagPool_aui_select_value_name_label_inlineField_cssClass.reuse(_jspx_th_aui_select_0);
                      out.write("\n\t\t\t\t\t\t\t\t");
                    }
                    if (_jspx_th_c_if_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
                      return;
                    }
                    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
                    out.write("\n\t\t\t\t\t\t\t");
                  }
                  if (_jspx_th_aui_col_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_aui_col_width_cssClass.reuse(_jspx_th_aui_col_2);
                    return;
                  }
                  _jspx_tagPool_aui_col_width_cssClass.reuse(_jspx_th_aui_col_2);
                  out.write("\n\t\t\t\t\t\t");
                }
                if (_jspx_th_aui_row_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_aui_row.reuse(_jspx_th_aui_row_2);
                  return;
                }
                _jspx_tagPool_aui_row.reuse(_jspx_th_aui_row_2);
                out.write("\n\t\t\t\t\t</div>\n\n\t\t\t\t\t");
                //  c:if
                com.liferay.taglib.core.IfTag _jspx_th_c_if_3 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                _jspx_th_c_if_3.setPageContext(_jspx_page_context);
                _jspx_th_c_if_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_col_1);
                _jspx_th_c_if_3.setTest( !showOnlySiteMembers && !userPublicPage );
                int _jspx_eval_c_if_3 = _jspx_th_c_if_3.doStartTag();
                if (_jspx_eval_c_if_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  out.write("\n\t\t\t\t\t\t");
                  if (_jspx_meth_aui_button_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_3, _jspx_page_context))
                    return;
                  out.write("\n\t\t\t\t\t");
                }
                if (_jspx_th_c_if_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_3);
                  return;
                }
                _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_3);
                out.write("\n\t\t\t\t");
              }
              if (_jspx_th_aui_col_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_aui_col_width_cssClass.reuse(_jspx_th_aui_col_1);
                return;
              }
              _jspx_tagPool_aui_col_width_cssClass.reuse(_jspx_th_aui_col_1);
              out.write("\n\t\t\t");
            }
            if (_jspx_th_aui_row_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_aui_row.reuse(_jspx_th_aui_row_1);
              return;
            }
            _jspx_tagPool_aui_row.reuse(_jspx_th_aui_row_1);
            out.write("\n\t\t");
          }
          if (_jspx_th_aui_form_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_aui_form_name_method_action.reuse(_jspx_th_aui_form_0);
            return;
          }
          _jspx_tagPool_aui_form_name_method_action.reuse(_jspx_th_aui_form_0);
          out.write("\n\n\t\t");
          //  aui:row
          com.liferay.taglib.aui.RowTag _jspx_th_aui_row_3 = (com.liferay.taglib.aui.RowTag) _jspx_tagPool_aui_row_cssClass.get(com.liferay.taglib.aui.RowTag.class);
          _jspx_th_aui_row_3.setPageContext(_jspx_page_context);
          _jspx_th_aui_row_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_0);
          _jspx_th_aui_row_3.setCssClass("contacts-result-container lfr-app-column-view");
          int _jspx_eval_aui_row_3 = _jspx_th_aui_row_3.doStartTag();
          if (_jspx_eval_aui_row_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t\t");
            //  aui:col
            com.liferay.taglib.aui.ColTag _jspx_th_aui_col_3 = (com.liferay.taglib.aui.ColTag) _jspx_tagPool_aui_col_width_first_cssClass.get(com.liferay.taglib.aui.ColTag.class);
            _jspx_th_aui_col_3.setPageContext(_jspx_page_context);
            _jspx_th_aui_col_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_row_3);
            _jspx_th_aui_col_3.setCssClass("contacts-list");
            _jspx_th_aui_col_3.setDynamicAttribute(null, "first",  true );
            _jspx_th_aui_col_3.setWidth( 30 );
            int _jspx_eval_aui_col_3 = _jspx_th_aui_col_3.doStartTag();
            if (_jspx_eval_aui_col_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\t\t\t\t<div class=\"toggle-user\">\n\t\t\t\t\t<i class=\"icon-chevron-left\"></i>\n\t\t\t\t</div>\n\n\t\t\t\t<div class=\"contacts-search lfr-search-column search-bar\">\n\t\t\t\t\t");
              //  aui:input
              com.liferay.taglib.aui.InputTag _jspx_th_aui_input_5 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_size_name_label_id_cssClass_nobody.get(com.liferay.taglib.aui.InputTag.class);
              _jspx_th_aui_input_5.setPageContext(_jspx_page_context);
              _jspx_th_aui_input_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_col_3);
              _jspx_th_aui_input_5.setCssClass("search-input");
              _jspx_th_aui_input_5.setId("name");
              _jspx_th_aui_input_5.setLabel("");
              _jspx_th_aui_input_5.setName("name");
              _jspx_th_aui_input_5.setDynamicAttribute(null, "size", new String("30"));
              _jspx_th_aui_input_5.setType("text");
              _jspx_th_aui_input_5.setValue( HtmlUtil.escape(name) );
              int _jspx_eval_aui_input_5 = _jspx_th_aui_input_5.doStartTag();
              if (_jspx_th_aui_input_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_aui_input_value_type_size_name_label_id_cssClass_nobody.reuse(_jspx_th_aui_input_5);
                return;
              }
              _jspx_tagPool_aui_input_value_type_size_name_label_id_cssClass_nobody.reuse(_jspx_th_aui_input_5);
              out.write("\n\n\t\t\t\t\t<i class=\"icon-search\"></i>\n\t\t\t\t</div>\n\n\t\t\t\t");
              //  aui:row
              com.liferay.taglib.aui.RowTag _jspx_th_aui_row_4 = (com.liferay.taglib.aui.RowTag) _jspx_tagPool_aui_row.get(com.liferay.taglib.aui.RowTag.class);
              _jspx_th_aui_row_4.setPageContext(_jspx_page_context);
              _jspx_th_aui_row_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_col_3);
              int _jspx_eval_aui_row_4 = _jspx_th_aui_row_4.doStartTag();
              if (_jspx_eval_aui_row_4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\t\t\t\t\t");
                //  aui:col
                com.liferay.taglib.aui.ColTag _jspx_th_aui_col_4 = (com.liferay.taglib.aui.ColTag) _jspx_tagPool_aui_col_width_cssClass.get(com.liferay.taglib.aui.ColTag.class);
                _jspx_th_aui_col_4.setPageContext(_jspx_page_context);
                _jspx_th_aui_col_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_row_4);
                _jspx_th_aui_col_4.setCssClass( userPublicPage ? "contacts-result personal-contact-list" : "contacts-result" );
                _jspx_th_aui_col_4.setWidth( 100 );
                int _jspx_eval_aui_col_4 = _jspx_th_aui_col_4.doStartTag();
                if (_jspx_eval_aui_col_4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  out.write("\n\n\t\t\t\t\t\t");

						String lastNameAnchor = StringPool.SPACE;

						for (BaseModel<?> curContact : contacts) {
						
                  out.write("\n\n\t\t\t\t\t\t\t");
                  //  c:choose
                  com.liferay.taglib.core.ChooseTag _jspx_th_c_choose_1 = (com.liferay.taglib.core.ChooseTag) _jspx_tagPool_c_choose.get(com.liferay.taglib.core.ChooseTag.class);
                  _jspx_th_c_choose_1.setPageContext(_jspx_page_context);
                  _jspx_th_c_choose_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_col_4);
                  int _jspx_eval_c_choose_1 = _jspx_th_c_choose_1.doStartTag();
                  if (_jspx_eval_c_choose_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    out.write("\n\t\t\t\t\t\t\t\t");
                    //  c:when
                    com.liferay.taglib.core.WhenTag _jspx_th_c_when_1 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
                    _jspx_th_c_when_1.setPageContext(_jspx_page_context);
                    _jspx_th_c_when_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_1);
                    _jspx_th_c_when_1.setTest( curContact instanceof User );
                    int _jspx_eval_c_when_1 = _jspx_th_c_when_1.doStartTag();
                    if (_jspx_eval_c_when_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      out.write("\n\n\t\t\t\t\t\t\t\t\t");

									User user2 = (User)curContact;

									String lastName = user2.getLastName();

									String curLastNameAnchor = LanguageUtil.get(request, "no-last-name");

									if (Validator.isNotNull(lastName)) {
										curLastNameAnchor = StringUtil.upperCase(lastName.substring(0, 1));
									}
									
                      out.write("\n\n\t\t\t\t\t\t\t\t\t");
                      //  c:if
                      com.liferay.taglib.core.IfTag _jspx_th_c_if_4 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                      _jspx_th_c_if_4.setPageContext(_jspx_page_context);
                      _jspx_th_c_if_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_1);
                      _jspx_th_c_if_4.setTest( !curLastNameAnchor.equals(lastNameAnchor) );
                      int _jspx_eval_c_if_4 = _jspx_th_c_if_4.doStartTag();
                      if (_jspx_eval_c_if_4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                        out.write("\n\n\t\t\t\t\t\t\t\t\t\t");

										lastNameAnchor = curLastNameAnchor;
										
                        out.write("\n\n\t\t\t\t\t\t\t\t\t\t<div class=\"last-name-anchor\">\n\t\t\t\t\t\t\t\t\t\t\t<a>");
                        //  liferay-ui:message
                        com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_2 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
                        _jspx_th_liferay$1ui_message_2.setPageContext(_jspx_page_context);
                        _jspx_th_liferay$1ui_message_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_4);
                        _jspx_th_liferay$1ui_message_2.setKey( lastNameAnchor );
                        int _jspx_eval_liferay$1ui_message_2 = _jspx_th_liferay$1ui_message_2.doStartTag();
                        if (_jspx_th_liferay$1ui_message_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                          _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_2);
                          return;
                        }
                        _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_2);
                        out.write("</a>\n\t\t\t\t\t\t\t\t\t\t</div>\n\t\t\t\t\t\t\t\t\t");
                      }
                      if (_jspx_th_c_if_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_4);
                        return;
                      }
                      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_4);
                      out.write("\n\n\t\t\t\t\t\t\t\t\t<div class=\"lfr-contact\">\n\t\t\t\t\t\t\t\t\t\t<div class=\"lfr-contact-checkbox\">\n\t\t\t\t\t\t\t\t\t\t\t<input class=\"contact-ids\" ");
                      out.print( (themeDisplay.getUserId() == user2.getUserId()) ? "disabled=\"true\"" : StringPool.BLANK );
                      out.write(" name=\"contact-ids-");
                      out.print( user2.getUserId() );
                      out.write("\" type=\"checkbox\" value=\"");
                      out.print( user2.getUserId() );
                      out.write("\" />\n\t\t\t\t\t\t\t\t\t\t</div>\n\n\t\t\t\t\t\t\t\t\t\t");
                      //  liferay-portlet:renderURL
                      com.liferay.taglib.portlet.RenderURLTag _jspx_th_liferay$1portlet_renderURL_0 = (com.liferay.taglib.portlet.RenderURLTag) _jspx_tagPool_liferay$1portlet_renderURL_windowState_var.get(com.liferay.taglib.portlet.RenderURLTag.class);
                      _jspx_th_liferay$1portlet_renderURL_0.setPageContext(_jspx_page_context);
                      _jspx_th_liferay$1portlet_renderURL_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_1);
                      _jspx_th_liferay$1portlet_renderURL_0.setVar("viewUserSummaryURL");
                      _jspx_th_liferay$1portlet_renderURL_0.setWindowState( LiferayWindowState.EXCLUSIVE.toString() );
                      int _jspx_eval_liferay$1portlet_renderURL_0 = _jspx_th_liferay$1portlet_renderURL_0.doStartTag();
                      if (_jspx_eval_liferay$1portlet_renderURL_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                        out.write("\n\t\t\t\t\t\t\t\t\t\t\t");
                        if (_jspx_meth_portlet_param_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1portlet_renderURL_0, _jspx_page_context))
                          return;
                        out.write("\n\t\t\t\t\t\t\t\t\t\t\t");
                        //  portlet:param
                        com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_1 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                        _jspx_th_portlet_param_1.setPageContext(_jspx_page_context);
                        _jspx_th_portlet_param_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_0);
                        _jspx_th_portlet_param_1.setName("userId");
                        _jspx_th_portlet_param_1.setValue( String.valueOf(user2.getUserId()) );
                        int _jspx_eval_portlet_param_1 = _jspx_th_portlet_param_1.doStartTag();
                        if (_jspx_th_portlet_param_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                          _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_1);
                          return;
                        }
                        _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_1);
                        out.write("\n\t\t\t\t\t\t\t\t\t\t\t");
                        //  portlet:param
                        com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_2 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                        _jspx_th_portlet_param_2.setPageContext(_jspx_page_context);
                        _jspx_th_portlet_param_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_0);
                        _jspx_th_portlet_param_2.setName("portalUser");
                        _jspx_th_portlet_param_2.setValue( Boolean.TRUE.toString() );
                        int _jspx_eval_portlet_param_2 = _jspx_th_portlet_param_2.doStartTag();
                        if (_jspx_th_portlet_param_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                          _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_2);
                          return;
                        }
                        _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_2);
                        out.write("\n\t\t\t\t\t\t\t\t\t\t");
                      }
                      if (_jspx_th_liferay$1portlet_renderURL_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_liferay$1portlet_renderURL_windowState_var.reuse(_jspx_th_liferay$1portlet_renderURL_0);
                        return;
                      }
                      _jspx_tagPool_liferay$1portlet_renderURL_windowState_var.reuse(_jspx_th_liferay$1portlet_renderURL_0);
                      java.lang.String viewUserSummaryURL = null;
                      viewUserSummaryURL = (java.lang.String) _jspx_page_context.findAttribute("viewUserSummaryURL");
                      out.write("\n\n\t\t\t\t\t\t\t\t\t\t<div class=\"lfr-contact-grid-item\" data-userId=\"");
                      out.print( user2.getUserId() );
                      out.write("\" data-viewSummaryURL=\"");
                      out.print( viewUserSummaryURL );
                      out.write("\">\n\t\t\t\t\t\t\t\t\t\t\t<div class=\"lfr-contact-thumb\">\n\t\t\t\t\t\t\t\t\t\t\t\t<img alt=\"");
                      out.print( HtmlUtil.escapeAttribute(user2.getFullName()) );
                      out.write("\" src=\"");
                      out.print( user2.getPortraitURL(themeDisplay) );
                      out.write("\" />\n\t\t\t\t\t\t\t\t\t\t\t</div>\n\n\t\t\t\t\t\t\t\t\t\t\t<div class=\"lfr-contact-info\">\n\t\t\t\t\t\t\t\t\t\t\t\t<div class=\"lfr-contact-name\">\n\t\t\t\t\t\t\t\t\t\t\t\t\t<a>\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
                      //  c:if
                      com.liferay.taglib.core.IfTag _jspx_th_c_if_5 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                      _jspx_th_c_if_5.setPageContext(_jspx_page_context);
                      _jspx_th_c_if_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_1);
                      _jspx_th_c_if_5.setTest( Validator.isNotNull(user2.getLastName()) );
                      int _jspx_eval_c_if_5 = _jspx_th_c_if_5.doStartTag();
                      if (_jspx_eval_c_if_5 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                        out.write("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
                        out.print( HtmlUtil.escape(user2.getLastName()) );
                        out.write(",\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
                      }
                      if (_jspx_th_c_if_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_5);
                        return;
                      }
                      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_5);
                      out.write("\n\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
                      out.print( HtmlUtil.escape(user2.getFirstName()) );
                      out.write("\n\t\t\t\t\t\t\t\t\t\t\t\t\t</a>\n\t\t\t\t\t\t\t\t\t\t\t\t</div>\n\n\t\t\t\t\t\t\t\t\t\t\t\t<div class=\"lfr-contact-extra\">\n\t\t\t\t\t\t\t\t\t\t\t\t\t");
                      out.print( HtmlUtil.escape(user2.getEmailAddress()) );
                      out.write("\n\t\t\t\t\t\t\t\t\t\t\t\t</div>\n\t\t\t\t\t\t\t\t\t\t\t</div>\n\n\t\t\t\t\t\t\t\t\t\t\t<div class=\"clear\"><!-- --></div>\n\t\t\t\t\t\t\t\t\t\t</div>\n\t\t\t\t\t\t\t\t\t</div>\n\t\t\t\t\t\t\t\t");
                    }
                    if (_jspx_th_c_when_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_1);
                      return;
                    }
                    _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_1);
                    out.write("\n\t\t\t\t\t\t\t\t");
                    //  c:otherwise
                    com.liferay.taglib.core.OtherwiseTag _jspx_th_c_otherwise_1 = (com.liferay.taglib.core.OtherwiseTag) _jspx_tagPool_c_otherwise.get(com.liferay.taglib.core.OtherwiseTag.class);
                    _jspx_th_c_otherwise_1.setPageContext(_jspx_page_context);
                    _jspx_th_c_otherwise_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_1);
                    int _jspx_eval_c_otherwise_1 = _jspx_th_c_otherwise_1.doStartTag();
                    if (_jspx_eval_c_otherwise_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      out.write("\n\n\t\t\t\t\t\t\t\t\t");

									Entry entry = (Entry)curContact;

									String fullName = entry.getFullName();

									String curLastNameAnchor = LanguageUtil.get(request, "no-last-name");

									if (Validator.isNotNull(fullName)) {
										curLastNameAnchor = StringUtil.upperCase(fullName.substring(0, 1));
									}
									
                      out.write("\n\n\t\t\t\t\t\t\t\t\t");
                      //  c:if
                      com.liferay.taglib.core.IfTag _jspx_th_c_if_6 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                      _jspx_th_c_if_6.setPageContext(_jspx_page_context);
                      _jspx_th_c_if_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_1);
                      _jspx_th_c_if_6.setTest( !curLastNameAnchor.equals(lastNameAnchor) );
                      int _jspx_eval_c_if_6 = _jspx_th_c_if_6.doStartTag();
                      if (_jspx_eval_c_if_6 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                        out.write("\n\n\t\t\t\t\t\t\t\t\t\t");

										lastNameAnchor = curLastNameAnchor;
										
                        out.write("\n\n\t\t\t\t\t\t\t\t\t\t<div class=\"last-name-anchor\">\n\t\t\t\t\t\t\t\t\t\t\t<a>");
                        //  liferay-ui:message
                        com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_3 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
                        _jspx_th_liferay$1ui_message_3.setPageContext(_jspx_page_context);
                        _jspx_th_liferay$1ui_message_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_6);
                        _jspx_th_liferay$1ui_message_3.setKey( lastNameAnchor );
                        int _jspx_eval_liferay$1ui_message_3 = _jspx_th_liferay$1ui_message_3.doStartTag();
                        if (_jspx_th_liferay$1ui_message_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                          _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_3);
                          return;
                        }
                        _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_3);
                        out.write("</a>\n\t\t\t\t\t\t\t\t\t\t</div>\n\t\t\t\t\t\t\t\t\t");
                      }
                      if (_jspx_th_c_if_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_6);
                        return;
                      }
                      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_6);
                      out.write("\n\n\t\t\t\t\t\t\t\t\t<div class=\"lfr-contact\">\n\t\t\t\t\t\t\t\t\t\t<div class=\"lfr-contact-checkbox\">\n\t\t\t\t\t\t\t\t\t\t\t<input class=\"contact-ids\" disabled=\"true\" label=\"\" name=\"contact-ids-");
                      out.print( entry.getEntryId() );
                      out.write("\" type=\"checkbox\" value=\"");
                      out.print( entry.getEntryId() );
                      out.write("\" />\n\t\t\t\t\t\t\t\t\t\t</div>\n\n\t\t\t\t\t\t\t\t\t\t");
                      //  liferay-portlet:renderURL
                      com.liferay.taglib.portlet.RenderURLTag _jspx_th_liferay$1portlet_renderURL_1 = (com.liferay.taglib.portlet.RenderURLTag) _jspx_tagPool_liferay$1portlet_renderURL_windowState_var.get(com.liferay.taglib.portlet.RenderURLTag.class);
                      _jspx_th_liferay$1portlet_renderURL_1.setPageContext(_jspx_page_context);
                      _jspx_th_liferay$1portlet_renderURL_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_1);
                      _jspx_th_liferay$1portlet_renderURL_1.setVar("viewContactSummaryURL");
                      _jspx_th_liferay$1portlet_renderURL_1.setWindowState( LiferayWindowState.EXCLUSIVE.toString() );
                      int _jspx_eval_liferay$1portlet_renderURL_1 = _jspx_th_liferay$1portlet_renderURL_1.doStartTag();
                      if (_jspx_eval_liferay$1portlet_renderURL_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                        out.write("\n\t\t\t\t\t\t\t\t\t\t\t");
                        if (_jspx_meth_portlet_param_3((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1portlet_renderURL_1, _jspx_page_context))
                          return;
                        out.write("\n\t\t\t\t\t\t\t\t\t\t\t");
                        //  portlet:param
                        com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_4 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                        _jspx_th_portlet_param_4.setPageContext(_jspx_page_context);
                        _jspx_th_portlet_param_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_1);
                        _jspx_th_portlet_param_4.setName("redirect");
                        _jspx_th_portlet_param_4.setValue( currentURL );
                        int _jspx_eval_portlet_param_4 = _jspx_th_portlet_param_4.doStartTag();
                        if (_jspx_th_portlet_param_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                          _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_4);
                          return;
                        }
                        _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_4);
                        out.write("\n\t\t\t\t\t\t\t\t\t\t\t");
                        //  portlet:param
                        com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_5 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                        _jspx_th_portlet_param_5.setPageContext(_jspx_page_context);
                        _jspx_th_portlet_param_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_1);
                        _jspx_th_portlet_param_5.setName("entryId");
                        _jspx_th_portlet_param_5.setValue( String.valueOf(entry.getEntryId()) );
                        int _jspx_eval_portlet_param_5 = _jspx_th_portlet_param_5.doStartTag();
                        if (_jspx_th_portlet_param_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                          _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_5);
                          return;
                        }
                        _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_5);
                        out.write("\n\t\t\t\t\t\t\t\t\t\t\t");
                        //  portlet:param
                        com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_6 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                        _jspx_th_portlet_param_6.setPageContext(_jspx_page_context);
                        _jspx_th_portlet_param_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_1);
                        _jspx_th_portlet_param_6.setName("portalUser");
                        _jspx_th_portlet_param_6.setValue( Boolean.FALSE.toString() );
                        int _jspx_eval_portlet_param_6 = _jspx_th_portlet_param_6.doStartTag();
                        if (_jspx_th_portlet_param_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                          _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_6);
                          return;
                        }
                        _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_6);
                        out.write("\n\t\t\t\t\t\t\t\t\t\t");
                      }
                      if (_jspx_th_liferay$1portlet_renderURL_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_liferay$1portlet_renderURL_windowState_var.reuse(_jspx_th_liferay$1portlet_renderURL_1);
                        return;
                      }
                      _jspx_tagPool_liferay$1portlet_renderURL_windowState_var.reuse(_jspx_th_liferay$1portlet_renderURL_1);
                      java.lang.String viewContactSummaryURL = null;
                      viewContactSummaryURL = (java.lang.String) _jspx_page_context.findAttribute("viewContactSummaryURL");
                      out.write("\n\n\t\t\t\t\t\t\t\t\t\t<div class=\"lfr-contact-grid-item\" data-userId=\"\" data-viewSummaryURL=\"");
                      out.print( viewContactSummaryURL );
                      out.write("\">\n\t\t\t\t\t\t\t\t\t\t\t<div class=\"lfr-contact-thumb\">\n\t\t\t\t\t\t\t\t\t\t\t\t<img alt=\"");
                      out.print( HtmlUtil.escapeAttribute(fullName) );
                      out.write("\" src=\"");
                      out.print( themeDisplay.getPathImage() + "/user_male_portrait?img_id=0&t=" );
                      out.write("\" />\n\t\t\t\t\t\t\t\t\t\t\t</div>\n\n\t\t\t\t\t\t\t\t\t\t\t<div class=\"lfr-contact-info\">\n\t\t\t\t\t\t\t\t\t\t\t\t<div class=\"lfr-contact-name\">\n\t\t\t\t\t\t\t\t\t\t\t\t\t<a>");
                      out.print( HtmlUtil.escape(fullName) );
                      out.write("</a>\n\t\t\t\t\t\t\t\t\t\t\t\t</div>\n\n\t\t\t\t\t\t\t\t\t\t\t\t<div class=\"lfr-contact-extra\">\n\t\t\t\t\t\t\t\t\t\t\t\t\t");
                      out.print( HtmlUtil.escape(entry.getEmailAddress()) );
                      out.write("\n\t\t\t\t\t\t\t\t\t\t\t\t</div>\n\t\t\t\t\t\t\t\t\t\t\t</div>\n\n\t\t\t\t\t\t\t\t\t\t\t<div class=\"clear\"><!-- --></div>\n\t\t\t\t\t\t\t\t\t\t</div>\n\t\t\t\t\t\t\t\t\t</div>\n\t\t\t\t\t\t\t\t");
                    }
                    if (_jspx_th_c_otherwise_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_1);
                      return;
                    }
                    _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_1);
                    out.write("\n\t\t\t\t\t\t\t");
                  }
                  if (_jspx_th_c_choose_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_1);
                    return;
                  }
                  _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_1);
                  out.write("\n\n\t\t\t\t\t\t");

						}
						
                  out.write("\n\n\t\t\t\t\t\t");
                  //  c:if
                  com.liferay.taglib.core.IfTag _jspx_th_c_if_7 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                  _jspx_th_c_if_7.setPageContext(_jspx_page_context);
                  _jspx_th_c_if_7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_col_4);
                  _jspx_th_c_if_7.setTest( contactsCount > ContactsConstants.MAX_RESULT_COUNT );
                  int _jspx_eval_c_if_7 = _jspx_th_c_if_7.doStartTag();
                  if (_jspx_eval_c_if_7 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    out.write("\n\t\t\t\t\t\t\t<div class=\"more-results\">\n\t\t\t\t\t\t\t\t<a data-end=\"");
                    out.print( ContactsConstants.MAX_RESULT_COUNT );
                    out.write("\" data-lastNameAnchor=\"");
                    out.print( lastNameAnchor );
                    out.write("\" href=\"javascript:;\">");
                    if (_jspx_meth_liferay$1ui_message_4((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_7, _jspx_page_context))
                      return;
                    out.write(' ');
                    out.write('(');
                    out.print( contactsCount - ContactsConstants.MAX_RESULT_COUNT );
                    out.write(")</a>\n\t\t\t\t\t\t\t</div>\n\t\t\t\t\t\t");
                  }
                  if (_jspx_th_c_if_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_7);
                    return;
                  }
                  _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_7);
                  out.write("\n\t\t\t\t\t");
                }
                if (_jspx_th_aui_col_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_aui_col_width_cssClass.reuse(_jspx_th_aui_col_4);
                  return;
                }
                _jspx_tagPool_aui_col_width_cssClass.reuse(_jspx_th_aui_col_4);
                out.write("\n\t\t\t\t");
              }
              if (_jspx_th_aui_row_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_aui_row.reuse(_jspx_th_aui_row_4);
                return;
              }
              _jspx_tagPool_aui_row.reuse(_jspx_th_aui_row_4);
              out.write("\n\t\t\t");
            }
            if (_jspx_th_aui_col_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_aui_col_width_first_cssClass.reuse(_jspx_th_aui_col_3);
              return;
            }
            _jspx_tagPool_aui_col_width_first_cssClass.reuse(_jspx_th_aui_col_3);
            out.write("\n\n\t\t\t");
            //  aui:col
            com.liferay.taglib.aui.ColTag _jspx_th_aui_col_5 = (com.liferay.taglib.aui.ColTag) _jspx_tagPool_aui_col_width_cssClass.get(com.liferay.taglib.aui.ColTag.class);
            _jspx_th_aui_col_5.setPageContext(_jspx_page_context);
            _jspx_th_aui_col_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_row_3);
            _jspx_th_aui_col_5.setCssClass("contacts-container");
            _jspx_th_aui_col_5.setWidth( 70 );
            int _jspx_eval_aui_col_5 = _jspx_th_aui_col_5.doStartTag();
            if (_jspx_eval_aui_col_5 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\t\t\t\t<div id=\"");
              if (_jspx_meth_portlet_namespace_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_col_5, _jspx_page_context))
                return;
              out.write("userToolbarButtons\"><!-- --></div>\n\n\t\t\t\t<div class=\"hide\" id=\"");
              if (_jspx_meth_portlet_namespace_1((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_col_5, _jspx_page_context))
                return;
              out.write("contactCenterToolbarButtons\">\n\t\t\t\t\t");
              //  liferay-util:include
              com.liferay.taglib.util.IncludeTag _jspx_th_liferay$1util_include_0 = (com.liferay.taglib.util.IncludeTag) _jspx_tagPool_liferay$1util_include_servletContext_page_nobody.get(com.liferay.taglib.util.IncludeTag.class);
              _jspx_th_liferay$1util_include_0.setPageContext(_jspx_page_context);
              _jspx_th_liferay$1util_include_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_col_5);
              _jspx_th_liferay$1util_include_0.setPage("/contacts_center/contacts_center_toolbar.jsp");
              _jspx_th_liferay$1util_include_0.setServletContext( application );
              int _jspx_eval_liferay$1util_include_0 = _jspx_th_liferay$1util_include_0.doStartTag();
              if (_jspx_th_liferay$1util_include_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_liferay$1util_include_servletContext_page_nobody.reuse(_jspx_th_liferay$1util_include_0);
                return;
              }
              _jspx_tagPool_liferay$1util_include_servletContext_page_nobody.reuse(_jspx_th_liferay$1util_include_0);
              out.write("\n\t\t\t\t</div>\n\n\t\t\t\t<div id=\"");
              if (_jspx_meth_portlet_namespace_2((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_col_5, _jspx_page_context))
                return;
              out.write("messageContainer\"></div>\n\n\t\t\t\t<div id=\"");
              if (_jspx_meth_portlet_namespace_3((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_col_5, _jspx_page_context))
                return;
              out.write("detailUserView\">\n\t\t\t\t\t");
              //  c:choose
              com.liferay.taglib.core.ChooseTag _jspx_th_c_choose_2 = (com.liferay.taglib.core.ChooseTag) _jspx_tagPool_c_choose.get(com.liferay.taglib.core.ChooseTag.class);
              _jspx_th_c_choose_2.setPageContext(_jspx_page_context);
              _jspx_th_c_choose_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_col_5);
              int _jspx_eval_c_choose_2 = _jspx_th_c_choose_2.doStartTag();
              if (_jspx_eval_c_choose_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\t\t\t\t\t\t");
                //  c:when
                com.liferay.taglib.core.WhenTag _jspx_th_c_when_2 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
                _jspx_th_c_when_2.setPageContext(_jspx_page_context);
                _jspx_th_c_when_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_2);
                _jspx_th_c_when_2.setTest( userPublicPage );
                int _jspx_eval_c_when_2 = _jspx_th_c_when_2.doStartTag();
                if (_jspx_eval_c_when_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  out.write("\n\n\t\t\t\t\t\t\t");

							request.setAttribute(ContactsWebKeys.CONTACTS_USER, contacts.get(0));
							
                  out.write("\n\n\t\t\t\t\t\t\t");
                  //  liferay-util:include
                  com.liferay.taglib.util.IncludeTag _jspx_th_liferay$1util_include_1 = (com.liferay.taglib.util.IncludeTag) _jspx_tagPool_liferay$1util_include_servletContext_page_nobody.get(com.liferay.taglib.util.IncludeTag.class);
                  _jspx_th_liferay$1util_include_1.setPageContext(_jspx_page_context);
                  _jspx_th_liferay$1util_include_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_2);
                  _jspx_th_liferay$1util_include_1.setPage("/view_user.jsp");
                  _jspx_th_liferay$1util_include_1.setServletContext( application );
                  int _jspx_eval_liferay$1util_include_1 = _jspx_th_liferay$1util_include_1.doStartTag();
                  if (_jspx_th_liferay$1util_include_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_liferay$1util_include_servletContext_page_nobody.reuse(_jspx_th_liferay$1util_include_1);
                    return;
                  }
                  _jspx_tagPool_liferay$1util_include_servletContext_page_nobody.reuse(_jspx_th_liferay$1util_include_1);
                  out.write("\n\t\t\t\t\t\t");
                }
                if (_jspx_th_c_when_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_2);
                  return;
                }
                _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_2);
                out.write("\n\t\t\t\t\t\t");
                //  c:otherwise
                com.liferay.taglib.core.OtherwiseTag _jspx_th_c_otherwise_2 = (com.liferay.taglib.core.OtherwiseTag) _jspx_tagPool_c_otherwise.get(com.liferay.taglib.core.OtherwiseTag.class);
                _jspx_th_c_otherwise_2.setPageContext(_jspx_page_context);
                _jspx_th_c_otherwise_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_2);
                int _jspx_eval_c_otherwise_2 = _jspx_th_c_otherwise_2.doStartTag();
                if (_jspx_eval_c_otherwise_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  out.write("\n\t\t\t\t\t\t\t");
                  //  aui:row
                  com.liferay.taglib.aui.RowTag _jspx_th_aui_row_5 = (com.liferay.taglib.aui.RowTag) _jspx_tagPool_aui_row.get(com.liferay.taglib.aui.RowTag.class);
                  _jspx_th_aui_row_5.setPageContext(_jspx_page_context);
                  _jspx_th_aui_row_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_2);
                  int _jspx_eval_aui_row_5 = _jspx_th_aui_row_5.doStartTag();
                  if (_jspx_eval_aui_row_5 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    out.write("\n\t\t\t\t\t\t\t\t");
                    //  aui:col
                    com.liferay.taglib.aui.ColTag _jspx_th_aui_col_6 = (com.liferay.taglib.aui.ColTag) _jspx_tagPool_aui_col_width_cssClass.get(com.liferay.taglib.aui.ColTag.class);
                    _jspx_th_aui_col_6.setPageContext(_jspx_page_context);
                    _jspx_th_aui_col_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_row_5);
                    _jspx_th_aui_col_6.setCssClass("contacts-center-home");
                    _jspx_th_aui_col_6.setWidth( 100 );
                    int _jspx_eval_aui_col_6 = _jspx_th_aui_col_6.doStartTag();
                    if (_jspx_eval_aui_col_6 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      out.write("\n\t\t\t\t\t\t\t\t\t");
                      //  c:choose
                      com.liferay.taglib.core.ChooseTag _jspx_th_c_choose_3 = (com.liferay.taglib.core.ChooseTag) _jspx_tagPool_c_choose.get(com.liferay.taglib.core.ChooseTag.class);
                      _jspx_th_c_choose_3.setPageContext(_jspx_page_context);
                      _jspx_th_c_choose_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_col_6);
                      int _jspx_eval_c_choose_3 = _jspx_th_c_choose_3.doStartTag();
                      if (_jspx_eval_c_choose_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                        out.write("\n\t\t\t\t\t\t\t\t\t\t");
                        //  c:when
                        com.liferay.taglib.core.WhenTag _jspx_th_c_when_3 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
                        _jspx_th_c_when_3.setPageContext(_jspx_page_context);
                        _jspx_th_c_when_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_3);
                        _jspx_th_c_when_3.setTest( !showOnlySiteMembers );
                        int _jspx_eval_c_when_3 = _jspx_th_c_when_3.doStartTag();
                        if (_jspx_eval_c_when_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                          out.write("\n\t\t\t\t\t\t\t\t\t\t\t");
                          if (_jspx_meth_liferay$1ui_header_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_when_3, _jspx_page_context))
                            return;
                          out.write("\n\t\t\t\t\t\t\t\t\t\t");
                        }
                        if (_jspx_th_c_when_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                          _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_3);
                          return;
                        }
                        _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_3);
                        out.write("\n\t\t\t\t\t\t\t\t\t\t");
                        if (_jspx_meth_c_otherwise_3((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_choose_3, _jspx_page_context))
                          return;
                        out.write("\n\t\t\t\t\t\t\t\t\t");
                      }
                      if (_jspx_th_c_choose_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_3);
                        return;
                      }
                      _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_3);
                      out.write("\n\n\t\t\t\t\t\t\t\t\t");

									int allUsersCount = 0;

									if (userPublicPage || showOnlySiteMembers || !filterBy.equals(ContactsConstants.FILTER_BY_DEFAULT)) {
										allUsersCount = UserLocalServiceUtil.searchCount(themeDisplay.getCompanyId(), StringPool.BLANK, WorkflowConstants.STATUS_APPROVED, params);
									}
									else {
										allUsersCount = EntryLocalServiceUtil.searchUsersAndContactsCount(themeDisplay.getCompanyId(), themeDisplay.getUserId(), StringPool.BLANK);
									}

									params.put("socialRelationType", new Long[] {themeDisplay.getUserId(), Long.valueOf(SocialRelationConstants.TYPE_BI_CONNECTION)});

									int connectionUsersCount = UserLocalServiceUtil.searchCount(themeDisplay.getCompanyId(), null, WorkflowConstants.STATUS_APPROVED, params);

									params.put("socialRelationType", new Long[] {themeDisplay.getUserId(), Long.valueOf(SocialRelationConstants.TYPE_UNI_FOLLOWER)});

									int followingUsersCount = UserLocalServiceUtil.searchCount(themeDisplay.getCompanyId(), null, WorkflowConstants.STATUS_APPROVED, params);

									int followerUsersCount = SocialRelationLocalServiceUtil.getInverseRelationsCount(themeDisplay.getUserId(), SocialRelationConstants.TYPE_UNI_FOLLOWER);
									
                      out.write("\n\n\t\t\t\t\t\t\t\t\t");
                      //  aui:row
                      com.liferay.taglib.aui.RowTag _jspx_th_aui_row_6 = (com.liferay.taglib.aui.RowTag) _jspx_tagPool_aui_row.get(com.liferay.taglib.aui.RowTag.class);
                      _jspx_th_aui_row_6.setPageContext(_jspx_page_context);
                      _jspx_th_aui_row_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_col_6);
                      int _jspx_eval_aui_row_6 = _jspx_th_aui_row_6.doStartTag();
                      if (_jspx_eval_aui_row_6 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                        out.write("\n\t\t\t\t\t\t\t\t\t\t");
                        //  aui:col
                        com.liferay.taglib.aui.ColTag _jspx_th_aui_col_7 = (com.liferay.taglib.aui.ColTag) _jspx_tagPool_aui_col_width_cssClass.get(com.liferay.taglib.aui.ColTag.class);
                        _jspx_th_aui_col_7.setPageContext(_jspx_page_context);
                        _jspx_th_aui_col_7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_row_6);
                        _jspx_th_aui_col_7.setCssClass("connections contacts-count");
                        _jspx_th_aui_col_7.setWidth( 100 );
                        int _jspx_eval_aui_col_7 = _jspx_th_aui_col_7.doStartTag();
                        if (_jspx_eval_aui_col_7 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                          out.write("\n\t\t\t\t\t\t\t\t\t\t\t<a href=\"javascript:;\">");
                          //  liferay-ui:message
                          com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_5 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody.get(com.liferay.taglib.ui.MessageTag.class);
                          _jspx_th_liferay$1ui_message_5.setPageContext(_jspx_page_context);
                          _jspx_th_liferay$1ui_message_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_col_7);
                          _jspx_th_liferay$1ui_message_5.setArguments( String.valueOf(connectionUsersCount) );
                          _jspx_th_liferay$1ui_message_5.setKey( showOnlySiteMembers ? "you-have-x-connections-in-this-site" : "you-have-x-connections" );
                          _jspx_th_liferay$1ui_message_5.setTranslateArguments( false );
                          int _jspx_eval_liferay$1ui_message_5 = _jspx_th_liferay$1ui_message_5.doStartTag();
                          if (_jspx_th_liferay$1ui_message_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                            _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody.reuse(_jspx_th_liferay$1ui_message_5);
                            return;
                          }
                          _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody.reuse(_jspx_th_liferay$1ui_message_5);
                          out.write("</a>\n\t\t\t\t\t\t\t\t\t\t");
                        }
                        if (_jspx_th_aui_col_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                          _jspx_tagPool_aui_col_width_cssClass.reuse(_jspx_th_aui_col_7);
                          return;
                        }
                        _jspx_tagPool_aui_col_width_cssClass.reuse(_jspx_th_aui_col_7);
                        out.write("\n\t\t\t\t\t\t\t\t\t");
                      }
                      if (_jspx_th_aui_row_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_aui_row.reuse(_jspx_th_aui_row_6);
                        return;
                      }
                      _jspx_tagPool_aui_row.reuse(_jspx_th_aui_row_6);
                      out.write("\n\n\t\t\t\t\t\t\t\t\t");
                      //  aui:row
                      com.liferay.taglib.aui.RowTag _jspx_th_aui_row_7 = (com.liferay.taglib.aui.RowTag) _jspx_tagPool_aui_row.get(com.liferay.taglib.aui.RowTag.class);
                      _jspx_th_aui_row_7.setPageContext(_jspx_page_context);
                      _jspx_th_aui_row_7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_col_6);
                      int _jspx_eval_aui_row_7 = _jspx_th_aui_row_7.doStartTag();
                      if (_jspx_eval_aui_row_7 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                        out.write("\n\t\t\t\t\t\t\t\t\t\t");
                        //  aui:col
                        com.liferay.taglib.aui.ColTag _jspx_th_aui_col_8 = (com.liferay.taglib.aui.ColTag) _jspx_tagPool_aui_col_width_cssClass.get(com.liferay.taglib.aui.ColTag.class);
                        _jspx_th_aui_col_8.setPageContext(_jspx_page_context);
                        _jspx_th_aui_col_8.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_row_7);
                        _jspx_th_aui_col_8.setCssClass("contacts-count followings");
                        _jspx_th_aui_col_8.setWidth( 100 );
                        int _jspx_eval_aui_col_8 = _jspx_th_aui_col_8.doStartTag();
                        if (_jspx_eval_aui_col_8 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                          out.write("\n\t\t\t\t\t\t\t\t\t\t\t<a href=\"javascript:;\">");
                          //  liferay-ui:message
                          com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_6 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody.get(com.liferay.taglib.ui.MessageTag.class);
                          _jspx_th_liferay$1ui_message_6.setPageContext(_jspx_page_context);
                          _jspx_th_liferay$1ui_message_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_col_8);
                          _jspx_th_liferay$1ui_message_6.setArguments( String.valueOf(followingUsersCount) );
                          _jspx_th_liferay$1ui_message_6.setKey( showOnlySiteMembers ? "you-are-following-x-people-in-this-site" : "you-are-following-x-people" );
                          _jspx_th_liferay$1ui_message_6.setTranslateArguments( false );
                          int _jspx_eval_liferay$1ui_message_6 = _jspx_th_liferay$1ui_message_6.doStartTag();
                          if (_jspx_th_liferay$1ui_message_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                            _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody.reuse(_jspx_th_liferay$1ui_message_6);
                            return;
                          }
                          _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody.reuse(_jspx_th_liferay$1ui_message_6);
                          out.write("</a>\n\t\t\t\t\t\t\t\t\t\t");
                        }
                        if (_jspx_th_aui_col_8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                          _jspx_tagPool_aui_col_width_cssClass.reuse(_jspx_th_aui_col_8);
                          return;
                        }
                        _jspx_tagPool_aui_col_width_cssClass.reuse(_jspx_th_aui_col_8);
                        out.write("\n\t\t\t\t\t\t\t\t\t");
                      }
                      if (_jspx_th_aui_row_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_aui_row.reuse(_jspx_th_aui_row_7);
                        return;
                      }
                      _jspx_tagPool_aui_row.reuse(_jspx_th_aui_row_7);
                      out.write("\n\n\t\t\t\t\t\t\t\t\t");
                      //  c:if
                      com.liferay.taglib.core.IfTag _jspx_th_c_if_8 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                      _jspx_th_c_if_8.setPageContext(_jspx_page_context);
                      _jspx_th_c_if_8.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_col_6);
                      _jspx_th_c_if_8.setTest( !showOnlySiteMembers );
                      int _jspx_eval_c_if_8 = _jspx_th_c_if_8.doStartTag();
                      if (_jspx_eval_c_if_8 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                        out.write("\n\t\t\t\t\t\t\t\t\t\t");
                        //  aui:row
                        com.liferay.taglib.aui.RowTag _jspx_th_aui_row_8 = (com.liferay.taglib.aui.RowTag) _jspx_tagPool_aui_row.get(com.liferay.taglib.aui.RowTag.class);
                        _jspx_th_aui_row_8.setPageContext(_jspx_page_context);
                        _jspx_th_aui_row_8.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_8);
                        int _jspx_eval_aui_row_8 = _jspx_th_aui_row_8.doStartTag();
                        if (_jspx_eval_aui_row_8 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                          out.write("\n\t\t\t\t\t\t\t\t\t\t\t");
                          //  aui:col
                          com.liferay.taglib.aui.ColTag _jspx_th_aui_col_9 = (com.liferay.taglib.aui.ColTag) _jspx_tagPool_aui_col_width_cssClass.get(com.liferay.taglib.aui.ColTag.class);
                          _jspx_th_aui_col_9.setPageContext(_jspx_page_context);
                          _jspx_th_aui_col_9.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_row_8);
                          _jspx_th_aui_col_9.setCssClass("contacts-count followers");
                          _jspx_th_aui_col_9.setWidth( 100 );
                          int _jspx_eval_aui_col_9 = _jspx_th_aui_col_9.doStartTag();
                          if (_jspx_eval_aui_col_9 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                            out.write("\n\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"javascript:;\">");
                            //  liferay-ui:message
                            com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_7 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody.get(com.liferay.taglib.ui.MessageTag.class);
                            _jspx_th_liferay$1ui_message_7.setPageContext(_jspx_page_context);
                            _jspx_th_liferay$1ui_message_7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_col_9);
                            _jspx_th_liferay$1ui_message_7.setArguments( String.valueOf(followerUsersCount) );
                            _jspx_th_liferay$1ui_message_7.setKey("you-have-x-followers");
                            _jspx_th_liferay$1ui_message_7.setTranslateArguments( false );
                            int _jspx_eval_liferay$1ui_message_7 = _jspx_th_liferay$1ui_message_7.doStartTag();
                            if (_jspx_th_liferay$1ui_message_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody.reuse(_jspx_th_liferay$1ui_message_7);
                              return;
                            }
                            _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody.reuse(_jspx_th_liferay$1ui_message_7);
                            out.write("</a>\n\t\t\t\t\t\t\t\t\t\t\t");
                          }
                          if (_jspx_th_aui_col_9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                            _jspx_tagPool_aui_col_width_cssClass.reuse(_jspx_th_aui_col_9);
                            return;
                          }
                          _jspx_tagPool_aui_col_width_cssClass.reuse(_jspx_th_aui_col_9);
                          out.write("\n\t\t\t\t\t\t\t\t\t\t");
                        }
                        if (_jspx_th_aui_row_8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                          _jspx_tagPool_aui_row.reuse(_jspx_th_aui_row_8);
                          return;
                        }
                        _jspx_tagPool_aui_row.reuse(_jspx_th_aui_row_8);
                        out.write("\n\n\t\t\t\t\t\t\t\t\t\t");

										int myContactsCount = EntryLocalServiceUtil.getEntriesCount(user.getUserId());
										
                        out.write("\n\n\t\t\t\t\t\t\t\t\t\t");
                        //  aui:row
                        com.liferay.taglib.aui.RowTag _jspx_th_aui_row_9 = (com.liferay.taglib.aui.RowTag) _jspx_tagPool_aui_row.get(com.liferay.taglib.aui.RowTag.class);
                        _jspx_th_aui_row_9.setPageContext(_jspx_page_context);
                        _jspx_th_aui_row_9.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_8);
                        int _jspx_eval_aui_row_9 = _jspx_th_aui_row_9.doStartTag();
                        if (_jspx_eval_aui_row_9 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                          out.write("\n\t\t\t\t\t\t\t\t\t\t\t");
                          //  aui:col
                          com.liferay.taglib.aui.ColTag _jspx_th_aui_col_10 = (com.liferay.taglib.aui.ColTag) _jspx_tagPool_aui_col_width_cssClass.get(com.liferay.taglib.aui.ColTag.class);
                          _jspx_th_aui_col_10.setPageContext(_jspx_page_context);
                          _jspx_th_aui_col_10.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_row_9);
                          _jspx_th_aui_col_10.setCssClass("contacts contacts-count");
                          _jspx_th_aui_col_10.setWidth( 100 );
                          int _jspx_eval_aui_col_10 = _jspx_th_aui_col_10.doStartTag();
                          if (_jspx_eval_aui_col_10 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                            out.write("\n\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"javascript:;\">");
                            //  liferay-ui:message
                            com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_8 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody.get(com.liferay.taglib.ui.MessageTag.class);
                            _jspx_th_liferay$1ui_message_8.setPageContext(_jspx_page_context);
                            _jspx_th_liferay$1ui_message_8.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_col_10);
                            _jspx_th_liferay$1ui_message_8.setArguments( String.valueOf(myContactsCount) );
                            _jspx_th_liferay$1ui_message_8.setKey("view-my-x-contacts");
                            _jspx_th_liferay$1ui_message_8.setTranslateArguments( false );
                            int _jspx_eval_liferay$1ui_message_8 = _jspx_th_liferay$1ui_message_8.doStartTag();
                            if (_jspx_th_liferay$1ui_message_8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody.reuse(_jspx_th_liferay$1ui_message_8);
                              return;
                            }
                            _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody.reuse(_jspx_th_liferay$1ui_message_8);
                            out.write("</a>\n\t\t\t\t\t\t\t\t\t\t\t");
                          }
                          if (_jspx_th_aui_col_10.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                            _jspx_tagPool_aui_col_width_cssClass.reuse(_jspx_th_aui_col_10);
                            return;
                          }
                          _jspx_tagPool_aui_col_width_cssClass.reuse(_jspx_th_aui_col_10);
                          out.write("\n\t\t\t\t\t\t\t\t\t\t");
                        }
                        if (_jspx_th_aui_row_9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                          _jspx_tagPool_aui_row.reuse(_jspx_th_aui_row_9);
                          return;
                        }
                        _jspx_tagPool_aui_row.reuse(_jspx_th_aui_row_9);
                        out.write("\n\t\t\t\t\t\t\t\t\t");
                      }
                      if (_jspx_th_c_if_8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_8);
                        return;
                      }
                      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_8);
                      out.write("\n\n\t\t\t\t\t\t\t\t\t");
                      //  aui:row
                      com.liferay.taglib.aui.RowTag _jspx_th_aui_row_10 = (com.liferay.taglib.aui.RowTag) _jspx_tagPool_aui_row.get(com.liferay.taglib.aui.RowTag.class);
                      _jspx_th_aui_row_10.setPageContext(_jspx_page_context);
                      _jspx_th_aui_row_10.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_col_6);
                      int _jspx_eval_aui_row_10 = _jspx_th_aui_row_10.doStartTag();
                      if (_jspx_eval_aui_row_10 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                        out.write("\n\t\t\t\t\t\t\t\t\t\t");
                        //  aui:col
                        com.liferay.taglib.aui.ColTag _jspx_th_aui_col_11 = (com.liferay.taglib.aui.ColTag) _jspx_tagPool_aui_col_width_cssClass.get(com.liferay.taglib.aui.ColTag.class);
                        _jspx_th_aui_col_11.setPageContext(_jspx_page_context);
                        _jspx_th_aui_col_11.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_row_10);
                        _jspx_th_aui_col_11.setCssClass("all contacts-count");
                        _jspx_th_aui_col_11.setWidth( 100 );
                        int _jspx_eval_aui_col_11 = _jspx_th_aui_col_11.doStartTag();
                        if (_jspx_eval_aui_col_11 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                          out.write("\n\t\t\t\t\t\t\t\t\t\t\t<a href=\"javascript:;\">");
                          //  liferay-ui:message
                          com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_9 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody.get(com.liferay.taglib.ui.MessageTag.class);
                          _jspx_th_liferay$1ui_message_9.setPageContext(_jspx_page_context);
                          _jspx_th_liferay$1ui_message_9.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_col_11);
                          _jspx_th_liferay$1ui_message_9.setArguments( String.valueOf(allUsersCount) );
                          _jspx_th_liferay$1ui_message_9.setKey("view-all-x-users");
                          _jspx_th_liferay$1ui_message_9.setTranslateArguments( false );
                          int _jspx_eval_liferay$1ui_message_9 = _jspx_th_liferay$1ui_message_9.doStartTag();
                          if (_jspx_th_liferay$1ui_message_9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                            _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody.reuse(_jspx_th_liferay$1ui_message_9);
                            return;
                          }
                          _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody.reuse(_jspx_th_liferay$1ui_message_9);
                          out.write("</a>\n\t\t\t\t\t\t\t\t\t\t");
                        }
                        if (_jspx_th_aui_col_11.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                          _jspx_tagPool_aui_col_width_cssClass.reuse(_jspx_th_aui_col_11);
                          return;
                        }
                        _jspx_tagPool_aui_col_width_cssClass.reuse(_jspx_th_aui_col_11);
                        out.write("\n\t\t\t\t\t\t\t\t\t");
                      }
                      if (_jspx_th_aui_row_10.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_aui_row.reuse(_jspx_th_aui_row_10);
                        return;
                      }
                      _jspx_tagPool_aui_row.reuse(_jspx_th_aui_row_10);
                      out.write("\n\n\t\t\t\t\t\t\t\t\t");
                      //  c:if
                      com.liferay.taglib.core.IfTag _jspx_th_c_if_9 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                      _jspx_th_c_if_9.setPageContext(_jspx_page_context);
                      _jspx_th_c_if_9.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_col_6);
                      _jspx_th_c_if_9.setTest( !showOnlySiteMembers && (connectionUsersCount <= 0) && (followingUsersCount <= 0) );
                      int _jspx_eval_c_if_9 = _jspx_th_c_if_9.doStartTag();
                      if (_jspx_eval_c_if_9 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                        out.write("\n\t\t\t\t\t\t\t\t\t\t");
                        //  aui:row
                        com.liferay.taglib.aui.RowTag _jspx_th_aui_row_11 = (com.liferay.taglib.aui.RowTag) _jspx_tagPool_aui_row.get(com.liferay.taglib.aui.RowTag.class);
                        _jspx_th_aui_row_11.setPageContext(_jspx_page_context);
                        _jspx_th_aui_row_11.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_9);
                        int _jspx_eval_aui_row_11 = _jspx_th_aui_row_11.doStartTag();
                        if (_jspx_eval_aui_row_11 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                          out.write("\n\t\t\t\t\t\t\t\t\t\t\t");
                          //  aui:col
                          com.liferay.taglib.aui.ColTag _jspx_th_aui_col_12 = (com.liferay.taglib.aui.ColTag) _jspx_tagPool_aui_col_width_cssClass.get(com.liferay.taglib.aui.ColTag.class);
                          _jspx_th_aui_col_12.setPageContext(_jspx_page_context);
                          _jspx_th_aui_col_12.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_row_11);
                          _jspx_th_aui_col_12.setCssClass("contacts-center-introduction");
                          _jspx_th_aui_col_12.setWidth( 100 );
                          int _jspx_eval_aui_col_12 = _jspx_th_aui_col_12.doStartTag();
                          if (_jspx_eval_aui_col_12 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                            out.write("\n\t\t\t\t\t\t\t\t\t\t\t\t");
                            if (_jspx_meth_liferay$1ui_message_10((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_col_12, _jspx_page_context))
                              return;
                            out.write("\n\t\t\t\t\t\t\t\t\t\t\t");
                          }
                          if (_jspx_th_aui_col_12.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                            _jspx_tagPool_aui_col_width_cssClass.reuse(_jspx_th_aui_col_12);
                            return;
                          }
                          _jspx_tagPool_aui_col_width_cssClass.reuse(_jspx_th_aui_col_12);
                          out.write("\n\t\t\t\t\t\t\t\t\t\t");
                        }
                        if (_jspx_th_aui_row_11.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                          _jspx_tagPool_aui_row.reuse(_jspx_th_aui_row_11);
                          return;
                        }
                        _jspx_tagPool_aui_row.reuse(_jspx_th_aui_row_11);
                        out.write("\n\t\t\t\t\t\t\t\t\t");
                      }
                      if (_jspx_th_c_if_9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_9);
                        return;
                      }
                      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_9);
                      out.write("\n\t\t\t\t\t\t\t\t");
                    }
                    if (_jspx_th_aui_col_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_aui_col_width_cssClass.reuse(_jspx_th_aui_col_6);
                      return;
                    }
                    _jspx_tagPool_aui_col_width_cssClass.reuse(_jspx_th_aui_col_6);
                    out.write("\n\t\t\t\t\t\t\t");
                  }
                  if (_jspx_th_aui_row_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_aui_row.reuse(_jspx_th_aui_row_5);
                    return;
                  }
                  _jspx_tagPool_aui_row.reuse(_jspx_th_aui_row_5);
                  out.write("\n\t\t\t\t\t\t");
                }
                if (_jspx_th_c_otherwise_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_2);
                  return;
                }
                _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_2);
                out.write("\n\t\t\t\t\t");
              }
              if (_jspx_th_c_choose_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_2);
                return;
              }
              _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_2);
              out.write("\n\t\t\t\t</div>\n\n\t\t\t\t<div id=\"");
              if (_jspx_meth_portlet_namespace_4((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_col_5, _jspx_page_context))
                return;
              out.write("selectedUsersView\"><!-- --></div>\n\t\t\t");
            }
            if (_jspx_th_aui_col_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_aui_col_width_cssClass.reuse(_jspx_th_aui_col_5);
              return;
            }
            _jspx_tagPool_aui_col_width_cssClass.reuse(_jspx_th_aui_col_5);
            out.write("\n\t\t");
          }
          if (_jspx_th_aui_row_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_aui_row_cssClass.reuse(_jspx_th_aui_row_3);
            return;
          }
          _jspx_tagPool_aui_row_cssClass.reuse(_jspx_th_aui_row_3);
          out.write("\n\n\t\t");
          //  aui:script
          com.liferay.taglib.aui.ScriptTag _jspx_th_aui_script_0 = (com.liferay.taglib.aui.ScriptTag) _jspx_tagPool_aui_script_use.get(com.liferay.taglib.aui.ScriptTag.class);
          _jspx_th_aui_script_0.setPageContext(_jspx_page_context);
          _jspx_th_aui_script_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_0);
          _jspx_th_aui_script_0.setUse("aui-io-deprecated,aui-loading-mask-deprecated,datatype-number,liferay-contacts-center");
          int _jspx_eval_aui_script_0 = _jspx_th_aui_script_0.doStartTag();
          if (_jspx_eval_aui_script_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            if (_jspx_eval_aui_script_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
              out = _jspx_page_context.pushBody();
              _jspx_th_aui_script_0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
              _jspx_th_aui_script_0.doInitBody();
            }
            do {
              out.write("\n\t\t\tvar searchInput = A.one('.contacts-portlet #");
              if (_jspx_meth_portlet_namespace_5((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_0, _jspx_page_context))
                return;
              out.write("name');\n\n\t\t\tLiferay.component(\n\t\t\t\t'contactsCenter',\n\t\t\t\tfunction() {\n\t\t\t\t\treturn new Liferay.ContactsCenter(\n\t\t\t\t\t\t{\n\t\t\t\t\t\t\tbaseActionURL: '");
              out.print( PortletURLFactoryUtil.create(request, portletDisplay.getId(), PortletRequest.ACTION_PHASE) );
              out.write("',\n\t\t\t\t\t\t\tbaseRenderURL: '");
              out.print( PortletURLFactoryUtil.create(request, portletDisplay.getId(), PortletRequest.RENDER_PHASE) );
              out.write("',\n\t\t\t\t\t\t\tcontactsResult: '.contacts-portlet .contacts-result',\n\t\t\t\t\t\t\tcontactsResultURL: '");
              //  portlet:resourceURL
              com.liferay.taglib.portlet.ResourceURLTag _jspx_th_portlet_resourceURL_0 = (com.liferay.taglib.portlet.ResourceURLTag) _jspx_tagPool_portlet_resourceURL_id.get(com.liferay.taglib.portlet.ResourceURLTag.class);
              _jspx_th_portlet_resourceURL_0.setPageContext(_jspx_page_context);
              _jspx_th_portlet_resourceURL_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_0);
              _jspx_th_portlet_resourceURL_0.setId("getContacts");
              int _jspx_eval_portlet_resourceURL_0 = _jspx_th_portlet_resourceURL_0.doStartTag();
              if (_jspx_eval_portlet_resourceURL_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                //  portlet:param
                com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_7 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                _jspx_th_portlet_param_7.setPageContext(_jspx_page_context);
                _jspx_th_portlet_param_7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_resourceURL_0);
                _jspx_th_portlet_param_7.setName("portletResource");
                _jspx_th_portlet_param_7.setValue( portletResource );
                int _jspx_eval_portlet_param_7 = _jspx_th_portlet_param_7.doStartTag();
                if (_jspx_th_portlet_param_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_7);
                  return;
                }
                _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_7);
                //  portlet:param
                com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_8 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                _jspx_th_portlet_param_8.setPageContext(_jspx_page_context);
                _jspx_th_portlet_param_8.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_resourceURL_0);
                _jspx_th_portlet_param_8.setName("redirect");
                _jspx_th_portlet_param_8.setValue( currentURL );
                int _jspx_eval_portlet_param_8 = _jspx_th_portlet_param_8.doStartTag();
                if (_jspx_th_portlet_param_8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_8);
                  return;
                }
                _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_8);
              }
              if (_jspx_th_portlet_resourceURL_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_portlet_resourceURL_id.reuse(_jspx_th_portlet_resourceURL_0);
                return;
              }
              _jspx_tagPool_portlet_resourceURL_id.reuse(_jspx_th_portlet_resourceURL_0);
              out.write("',\n\t\t\t\t\t\t\tcontactsSearchInput: '#");
              if (_jspx_meth_portlet_namespace_6((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_0, _jspx_page_context))
                return;
              out.write("name',\n\t\t\t\t\t\t\tdefaultMessageError: '");
              //  liferay-ui:message
              com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_11 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_unicode_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
              _jspx_th_liferay$1ui_message_11.setPageContext(_jspx_page_context);
              _jspx_th_liferay$1ui_message_11.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_0);
              _jspx_th_liferay$1ui_message_11.setKey("an-error-occurred-while-retrieving-the-users-information");
              _jspx_th_liferay$1ui_message_11.setUnicode( true );
              int _jspx_eval_liferay$1ui_message_11 = _jspx_th_liferay$1ui_message_11.doStartTag();
              if (_jspx_th_liferay$1ui_message_11.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_liferay$1ui_message_unicode_key_nobody.reuse(_jspx_th_liferay$1ui_message_11);
                return;
              }
              _jspx_tagPool_liferay$1ui_message_unicode_key_nobody.reuse(_jspx_th_liferay$1ui_message_11);
              out.write("',\n\t\t\t\t\t\t\tdefaultMessageSuccess: '");
              //  liferay-ui:message
              com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_12 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_unicode_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
              _jspx_th_liferay$1ui_message_12.setPageContext(_jspx_page_context);
              _jspx_th_liferay$1ui_message_12.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_0);
              _jspx_th_liferay$1ui_message_12.setKey("your-request-completed-successfully");
              _jspx_th_liferay$1ui_message_12.setUnicode( true );
              int _jspx_eval_liferay$1ui_message_12 = _jspx_th_liferay$1ui_message_12.doStartTag();
              if (_jspx_th_liferay$1ui_message_12.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_liferay$1ui_message_unicode_key_nobody.reuse(_jspx_th_liferay$1ui_message_12);
                return;
              }
              _jspx_tagPool_liferay$1ui_message_unicode_key_nobody.reuse(_jspx_th_liferay$1ui_message_12);
              out.write("',\n\t\t\t\t\t\t\tgetSelectedContactsURL: '");
              if (_jspx_meth_portlet_resourceURL_1((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_0, _jspx_page_context))
                return;
              out.write("',\n\t\t\t\t\t\t\tmaxResultCount: ");
              out.print( ContactsConstants.MAX_RESULT_COUNT );
              out.write(",\n\t\t\t\t\t\t\tnamespace: '");
              if (_jspx_meth_portlet_namespace_7((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_0, _jspx_page_context))
                return;
              out.write("',\n\t\t\t\t\t\t\tshowIcon: '");
              out.print( showIcon );
              out.write("'\n\t\t\t\t\t\t}\n\t\t\t\t\t);\n\t\t\t\t}\n\t\t\t);\n\n\t\t\tvar contactsCenter = Liferay.component('contactsCenter');\n\n\t\t\t");
              //  c:if
              com.liferay.taglib.core.IfTag _jspx_th_c_if_10 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
              _jspx_th_c_if_10.setPageContext(_jspx_page_context);
              _jspx_th_c_if_10.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_0);
              _jspx_th_c_if_10.setTest( !userPublicPage );
              int _jspx_eval_c_if_10 = _jspx_th_c_if_10.doStartTag();
              if (_jspx_eval_c_if_10 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\t\t\t\tvar contactFilterSelect = A.one('#");
                if (_jspx_meth_portlet_namespace_8((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_10, _jspx_page_context))
                  return;
                out.write("filterBy');\n\n\t\t\t\tcontactFilterSelect.on(\n\t\t\t\t\t'change',\n\t\t\t\t\tfunction(event) {\n\t\t\t\t\t\tsearchInput.set('value', '');\n\n\t\t\t\t\t\tcontactsCenter.updateContacts(searchInput.get('value'), contactFilterSelect.get('value'));\n\t\t\t\t\t}\n\t\t\t\t);\n\t\t\t");
              }
              if (_jspx_th_c_if_10.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_10);
                return;
              }
              _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_10);
              out.write("\n\n\t\t\tvar contactsCenterNode = A.one('#p_p_id");
              if (_jspx_meth_portlet_namespace_9((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_0, _jspx_page_context))
                return;
              out.write("');\n\n\t\t\tvar toggleUser = A.one('.contacts-portlet .toggle-user');\n\n\t\t\tif (toggleUser) {\n\t\t\t\ttoggleUser.on(\n\t\t\t\t'click',\n\t\t\t\t\tfunction(event) {\n\t\t\t\t\t\tcontactsCenterNode.toggleClass('show-user', false);\n\t\t\t\t\t}\n\t\t\t\t);\n\t\t\t}\n\n\t\t\tvar contactsResult = A.one('.contacts-portlet .contacts-result');\n\n\t\t\tcontactsResult.delegate(\n\t\t\t\t'click',\n\t\t\t\tfunction(event) {\n\t\t\t\t\tcontactsCenterNode.toggleClass('show-user', true);\n\n\t\t\t\t\tvar contactsContainer = A.one('.contacts-portlet .contacts-container');\n\n\t\t\t\t\tcontactsContainer.plug(A.LoadingMask);\n\n\t\t\t\t\tcontactsContainer.loadingmask.show();\n\n\t\t\t\t\tvar node = event.currentTarget;\n\n\t\t\t\t\tA.io.request(\n\t\t\t\t\t\tnode.getAttribute('data-viewSummaryURL'),\n\t\t\t\t\t\t{\n\t\t\t\t\t\t\tafter: {\n\t\t\t\t\t\t\t\tfailure: function(event, id, obj) {\n\t\t\t\t\t\t\t\t\tcontactsContainer.loadingmask.hide();\n\n\t\t\t\t\t\t\t\t\tcontactsCenter.showMessage(false);\n\t\t\t\t\t\t\t\t},\n\t\t\t\t\t\t\t\tsuccess: function(event, id, obj) {\n\t\t\t\t\t\t\t\t\tcontactsCenter.renderContent(this.get('responseData'), true);\n\n\t\t\t\t\t\t\t\t\twindow.scrollTo(0,0);\n\n\t\t\t\t\t\t\t\t\tcontactsContainer.loadingmask.hide();\n");
              out.write("\t\t\t\t\t\t\t\t}\n\t\t\t\t\t\t\t}\n\t\t\t\t\t\t}\n\t\t\t\t\t);\n\t\t\t\t},\n\t\t\t\t'.lfr-contact-grid-item'\n\t\t\t);\n\n\t\t\tcontactsResult.delegate(\n\t\t\t\t'click',\n\t\t\t\tfunction(event) {\n\t\t\t\t\tvar node = event.currentTarget;\n\n\t\t\t\t\tvar start = A.DataType.Number.parse(node.getAttribute('data-end'));\n\t\t\t\t\tvar end = start + ");
              out.print( ContactsConstants.MAX_RESULT_COUNT );
              out.write(";\n\n\t\t\t\t\tvar lastNameAnchor = node.getAttribute('data-lastNameAnchor');\n\n\t\t\t\t\tA.io.request(\n\t\t\t\t\t\t'");
              //  portlet:resourceURL
              com.liferay.taglib.portlet.ResourceURLTag _jspx_th_portlet_resourceURL_2 = (com.liferay.taglib.portlet.ResourceURLTag) _jspx_tagPool_portlet_resourceURL_id.get(com.liferay.taglib.portlet.ResourceURLTag.class);
              _jspx_th_portlet_resourceURL_2.setPageContext(_jspx_page_context);
              _jspx_th_portlet_resourceURL_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_0);
              _jspx_th_portlet_resourceURL_2.setId("getContacts");
              int _jspx_eval_portlet_resourceURL_2 = _jspx_th_portlet_resourceURL_2.doStartTag();
              if (_jspx_eval_portlet_resourceURL_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                //  portlet:param
                com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_9 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                _jspx_th_portlet_param_9.setPageContext(_jspx_page_context);
                _jspx_th_portlet_param_9.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_resourceURL_2);
                _jspx_th_portlet_param_9.setName("portletResource");
                _jspx_th_portlet_param_9.setValue( portletResource );
                int _jspx_eval_portlet_param_9 = _jspx_th_portlet_param_9.doStartTag();
                if (_jspx_th_portlet_param_9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_9);
                  return;
                }
                _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_9);
                //  portlet:param
                com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_10 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                _jspx_th_portlet_param_10.setPageContext(_jspx_page_context);
                _jspx_th_portlet_param_10.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_resourceURL_2);
                _jspx_th_portlet_param_10.setName("redirect");
                _jspx_th_portlet_param_10.setValue( currentURL );
                int _jspx_eval_portlet_param_10 = _jspx_th_portlet_param_10.doStartTag();
                if (_jspx_th_portlet_param_10.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_10);
                  return;
                }
                _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_10);
              }
              if (_jspx_th_portlet_resourceURL_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_portlet_resourceURL_id.reuse(_jspx_th_portlet_resourceURL_2);
                return;
              }
              _jspx_tagPool_portlet_resourceURL_id.reuse(_jspx_th_portlet_resourceURL_2);
              out.write("',\n\t\t\t\t\t\t{\n\t\t\t\t\t\t\tafter: {\n\t\t\t\t\t\t\t\tsuccess: function(event, id, obj) {\n\t\t\t\t\t\t\t\t\tvar responseData = this.get('responseData');\n\n\t\t\t\t\t\t\t\t\tcontactsCenter.showMoreResult(responseData, lastNameAnchor);\n\t\t\t\t\t\t\t\t}\n\t\t\t\t\t\t\t},\n\t\t\t\t\t\t\tdata: {\n\t\t\t\t\t\t\t\t");
              if (_jspx_meth_portlet_namespace_10((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_0, _jspx_page_context))
                return;
              out.write("end: end,\n\t\t\t\t\t\t\t\t");
              if (_jspx_meth_portlet_namespace_11((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_0, _jspx_page_context))
                return;
              out.write("filterBy: contactFilterSelect.get('value') || '");
              out.print( ContactsConstants.FILTER_BY_DEFAULT );
              out.write("',\n\t\t\t\t\t\t\t\t");
              if (_jspx_meth_portlet_namespace_12((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_0, _jspx_page_context))
                return;
              out.write("keywords: searchInput.get('value'),\n\t\t\t\t\t\t\t\t");
              if (_jspx_meth_portlet_namespace_13((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_0, _jspx_page_context))
                return;
              out.write("start: start\n\t\t\t\t\t\t\t},\n\t\t\t\t\t\t\tdataType: 'JSON'\n\t\t\t\t\t\t}\n\t\t\t\t\t);\n\t\t\t\t},\n\t\t\t\t'.more-results a'\n\t\t\t);\n\n\t\t\tcontactsResult.delegate(\n\t\t\t\t'click',\n\t\t\t\tfunction(event) {\n\t\t\t\t\tvar checkBox = event.target;\n\n\t\t\t\t\tvar userId = checkBox.val();\n\n\t\t\t\t\tif (checkBox.get('checked')) {\n\t\t\t\t\t\tA.io.request(\n\t\t\t\t\t\t\t'");
              //  portlet:resourceURL
              com.liferay.taglib.portlet.ResourceURLTag _jspx_th_portlet_resourceURL_3 = (com.liferay.taglib.portlet.ResourceURLTag) _jspx_tagPool_portlet_resourceURL_id.get(com.liferay.taglib.portlet.ResourceURLTag.class);
              _jspx_th_portlet_resourceURL_3.setPageContext(_jspx_page_context);
              _jspx_th_portlet_resourceURL_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_0);
              _jspx_th_portlet_resourceURL_3.setId("getContact");
              int _jspx_eval_portlet_resourceURL_3 = _jspx_th_portlet_resourceURL_3.doStartTag();
              if (_jspx_eval_portlet_resourceURL_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                //  portlet:param
                com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_11 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                _jspx_th_portlet_param_11.setPageContext(_jspx_page_context);
                _jspx_th_portlet_param_11.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_resourceURL_3);
                _jspx_th_portlet_param_11.setName("portletResource");
                _jspx_th_portlet_param_11.setValue( portletResource );
                int _jspx_eval_portlet_param_11 = _jspx_th_portlet_param_11.doStartTag();
                if (_jspx_th_portlet_param_11.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_11);
                  return;
                }
                _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_11);
              }
              if (_jspx_th_portlet_resourceURL_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_portlet_resourceURL_id.reuse(_jspx_th_portlet_resourceURL_3);
                return;
              }
              _jspx_tagPool_portlet_resourceURL_id.reuse(_jspx_th_portlet_resourceURL_3);
              out.write("',\n\t\t\t\t\t\t\t{\n\t\t\t\t\t\t\t\tafter: {\n\t\t\t\t\t\t\t\t\tfailure: function(event, id, obj) {\n\t\t\t\t\t\t\t\t\t\tcontactsCenter.showMessage(false, responseData.message);\n\t\t\t\t\t\t\t\t\t},\n\t\t\t\t\t\t\t\t\tsuccess: function(event, id, obj) {\n\t\t\t\t\t\t\t\t\t\tvar responseData = this.get('responseData');\n\n\t\t\t\t\t\t\t\t\t\tif (responseData.success) {\n\t\t\t\t\t\t\t\t\t\t\tcontactsCenter.addContactResult(responseData);\n\t\t\t\t\t\t\t\t\t\t}\n\t\t\t\t\t\t\t\t\t}\n\t\t\t\t\t\t\t\t},\n\t\t\t\t\t\t\t\tdata: {\n\t\t\t\t\t\t\t\t\t");
              if (_jspx_meth_portlet_namespace_14((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_0, _jspx_page_context))
                return;
              out.write("userId: userId\n\t\t\t\t\t\t\t\t},\n\t\t\t\t\t\t\t\tdataType: 'JSON'\n\t\t\t\t\t\t\t}\n\t\t\t\t\t\t);\n\t\t\t\t\t}\n\t\t\t\t\telse {\n\t\t\t\t\t\tcontactsCenter.deleteContactResult(userId);\n\t\t\t\t\t}\n\t\t\t\t},\n\t\t\t\t'.contact-ids'\n\t\t\t);\n\n\t\t\tA.one('.contacts-container').delegate(\n\t\t\t\t'click',\n\t\t\t\tfunction(event) {\n\t\t\t\t\tvar instance = this;\n\n\t\t\t\t\tvar node = event.currentTarget;\n\n\t\t\t\t\tvar userId = instance.one('input').val();\n\n\t\t\t\t\tvar ioRequest = A.io.request(\n\t\t\t\t\t\tnode.getAttribute('data-viewSummaryURL'),\n\t\t\t\t\t\t{\n\t\t\t\t\t\t\tafter: {\n\t\t\t\t\t\t\t\tfailure: function(event, id, obj) {\n\t\t\t\t\t\t\t\t\tcontactsCenter.showMessage(false);\n\t\t\t\t\t\t\t\t},\n\t\t\t\t\t\t\t\tsuccess: function(event, id, obj) {\n\t\t\t\t\t\t\t\t\tcontactsCenter.renderContent(this.get('responseData'));\n\t\t\t\t\t\t\t\t}\n\t\t\t\t\t\t\t},\n\t\t\t\t\t\t\tdata: {\n\t\t\t\t\t\t\t\t");
              if (_jspx_meth_portlet_namespace_15((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_0, _jspx_page_context))
                return;
              out.write("showDetailView: true,\n\t\t\t\t\t\t\t\t");
              if (_jspx_meth_portlet_namespace_16((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_0, _jspx_page_context))
                return;
              out.write("userId: userId\n\t\t\t\t\t\t\t}\n\t\t\t\t\t\t}\n\t\t\t\t\t);\n\t\t\t\t},\n\t\t\t\t'.lfr-contact-grid-item'\n\t\t\t);\n\n\t\t\t");
              //  c:if
              com.liferay.taglib.core.IfTag _jspx_th_c_if_11 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
              _jspx_th_c_if_11.setPageContext(_jspx_page_context);
              _jspx_th_c_if_11.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_0);
              _jspx_th_c_if_11.setTest( !userPublicPage );
              int _jspx_eval_c_if_11 = _jspx_th_c_if_11.doStartTag();
              if (_jspx_eval_c_if_11 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\t\t\t\tvar contactsCenterHome = A.one('.contacts-portlet .contacts-center-home');\n\n\t\t\t\t");
                //  c:if
                com.liferay.taglib.core.IfTag _jspx_th_c_if_12 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                _jspx_th_c_if_12.setPageContext(_jspx_page_context);
                _jspx_th_c_if_12.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_11);
                _jspx_th_c_if_12.setTest( !showOnlySiteMembers );
                int _jspx_eval_c_if_12 = _jspx_th_c_if_12.doStartTag();
                if (_jspx_eval_c_if_12 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  out.write("\n\t\t\t\t\tvar addContact = A.one('#");
                  if (_jspx_meth_portlet_namespace_17((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_12, _jspx_page_context))
                    return;
                  out.write("addContact');\n\n\t\t\t\t\tif (addContact) {\n\t\t\t\t\t\taddContact.on(\n\t\t\t\t\t\t\t'click',\n\t\t\t\t\t\t\tfunction(event) {\n\t\t\t\t\t\t\t\tcontactsCenter.showPopup('");
                  out.print( LanguageUtil.get(request, "add-contact") );
                  out.write("', '");
                  //  portlet:renderURL
                  com.liferay.taglib.portlet.RenderURLTag _jspx_th_portlet_renderURL_0 = (com.liferay.taglib.portlet.RenderURLTag) _jspx_tagPool_portlet_renderURL_windowState.get(com.liferay.taglib.portlet.RenderURLTag.class);
                  _jspx_th_portlet_renderURL_0.setPageContext(_jspx_page_context);
                  _jspx_th_portlet_renderURL_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_12);
                  _jspx_th_portlet_renderURL_0.setWindowState( LiferayWindowState.EXCLUSIVE.toString() );
                  int _jspx_eval_portlet_renderURL_0 = _jspx_th_portlet_renderURL_0.doStartTag();
                  if (_jspx_eval_portlet_renderURL_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    if (_jspx_meth_portlet_param_12((javax.servlet.jsp.tagext.JspTag) _jspx_th_portlet_renderURL_0, _jspx_page_context))
                      return;
                    //  portlet:param
                    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_13 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                    _jspx_th_portlet_param_13.setPageContext(_jspx_page_context);
                    _jspx_th_portlet_param_13.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_0);
                    _jspx_th_portlet_param_13.setName("redirect");
                    _jspx_th_portlet_param_13.setValue( currentURL );
                    int _jspx_eval_portlet_param_13 = _jspx_th_portlet_param_13.doStartTag();
                    if (_jspx_th_portlet_param_13.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_13);
                      return;
                    }
                    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_13);
                  }
                  if (_jspx_th_portlet_renderURL_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_portlet_renderURL_windowState.reuse(_jspx_th_portlet_renderURL_0);
                    return;
                  }
                  _jspx_tagPool_portlet_renderURL_windowState.reuse(_jspx_th_portlet_renderURL_0);
                  out.write("');\n\t\t\t\t\t\t\t}\n\t\t\t\t\t\t);\n\t\t\t\t\t}\n\n\t\t\t\t\tvar contacts = contactsCenterHome.one('.contacts');\n\n\t\t\t\t\tif (contacts) {\n\t\t\t\t\t\tcontacts.on(\n\t\t\t\t\t\t\t'click',\n\t\t\t\t\t\t\tfunction(event) {\n\t\t\t\t\t\t\t\tcontactFilterSelect.set('value', '");
                  out.print( ContactsConstants.FILTER_BY_TYPE_MY_CONTACTS );
                  out.write("');\n\n\t\t\t\t\t\t\t\tcontactsCenter.updateContacts(searchInput.get('value'), contactFilterSelect.get('value'));\n\t\t\t\t\t\t\t},\n\t\t\t\t\t\t\t'a'\n\t\t\t\t\t\t);\n\t\t\t\t\t}\n\t\t\t\t");
                }
                if (_jspx_th_c_if_12.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_12);
                  return;
                }
                _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_12);
                out.write("\n\n\t\t\t\tvar connections = contactsCenterHome.one('.connections');\n\n\t\t\t\tif (connections) {\n\t\t\t\t\tconnections.on(\n\t\t\t\t\t\t'click',\n\t\t\t\t\t\tfunction(event) {\n\t\t\t\t\t\t\tcontactFilterSelect.set('value', '");
                out.print( ContactsConstants.FILTER_BY_TYPE_BI_CONNECTION );
                out.write("');\n\n\t\t\t\t\t\t\tcontactsCenter.updateContacts(searchInput.get('value'), contactFilterSelect.get('value'));\n\t\t\t\t\t\t},\n\t\t\t\t\t\t'a'\n\t\t\t\t\t);\n\t\t\t\t}\n\n\t\t\t\tvar following = contactsCenterHome.one('.followings');\n\n\t\t\t\tif (following) {\n\t\t\t\t\tfollowing.on(\n\t\t\t\t\t\t'click',\n\t\t\t\t\t\tfunction(event) {\n\t\t\t\t\t\t\tcontactFilterSelect.set('value', '");
                out.print( ContactsConstants.FILTER_BY_TYPE_UNI_FOLLOWER );
                out.write("');\n\n\t\t\t\t\t\t\tcontactsCenter.updateContacts(searchInput.get('value'), contactFilterSelect.get('value'));\n\t\t\t\t\t\t},\n\t\t\t\t\t\t'a'\n\t\t\t\t\t);\n\t\t\t\t}\n\n\t\t\t\tvar followers = contactsCenterHome.one('.followers');\n\n\t\t\t\tif (followers) {\n\t\t\t\t\tfollowers.on(\n\t\t\t\t\t\t'click',\n\t\t\t\t\t\tfunction(event) {\n\t\t\t\t\t\t\tcontactFilterSelect.set('value', '");
                out.print( ContactsConstants.FILTER_BY_FOLLOWERS );
                out.write("');\n\n\t\t\t\t\t\t\tcontactsCenter.updateContacts(searchInput.get('value'), contactFilterSelect.get('value'));\n\t\t\t\t\t\t},\n\t\t\t\t\t\t'a'\n\t\t\t\t\t);\n\t\t\t\t}\n\n\t\t\t\tvar all = contactsCenterHome.one('.all');\n\n\t\t\t\tif (all) {\n\t\t\t\t\tall.on(\n\t\t\t\t\t\t'click',\n\t\t\t\t\t\tfunction(event) {\n\t\t\t\t\t\t\tcontactFilterSelect.set('value', '");
                out.print( ContactsConstants.FILTER_BY_DEFAULT );
                out.write("');\n\n\t\t\t\t\t\t\tsearchInput.set('value', '');\n\n\t\t\t\t\t\t\tcontactsCenter.updateContacts(searchInput.get('value'), contactFilterSelect.get('value'));\n\t\t\t\t\t\t},\n\t\t\t\t\t\t'a'\n\t\t\t\t\t);\n\t\t\t\t}\n\t\t\t");
              }
              if (_jspx_th_c_if_11.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_11);
                return;
              }
              _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_11);
              out.write("\n\t\t");
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

  private boolean _jspx_meth_aui_input_1(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_form_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:input
    com.liferay.taglib.aui.InputTag _jspx_th_aui_input_1 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
    _jspx_th_aui_input_1.setPageContext(_jspx_page_context);
    _jspx_th_aui_input_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
    _jspx_th_aui_input_1.setName("redirect");
    _jspx_th_aui_input_1.setType("hidden");
    _jspx_th_aui_input_1.setValue(new String(""));
    int _jspx_eval_aui_input_1 = _jspx_th_aui_input_1.doStartTag();
    if (_jspx_th_aui_input_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_1);
      return true;
    }
    _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_1);
    return false;
  }

  private boolean _jspx_meth_aui_input_2(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_form_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:input
    com.liferay.taglib.aui.InputTag _jspx_th_aui_input_2 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
    _jspx_th_aui_input_2.setPageContext(_jspx_page_context);
    _jspx_th_aui_input_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
    _jspx_th_aui_input_2.setName("userIds");
    _jspx_th_aui_input_2.setType("hidden");
    _jspx_th_aui_input_2.setValue(new String(""));
    int _jspx_eval_aui_input_2 = _jspx_th_aui_input_2.doStartTag();
    if (_jspx_th_aui_input_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_2);
      return true;
    }
    _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_2);
    return false;
  }

  private boolean _jspx_meth_aui_input_3(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_form_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:input
    com.liferay.taglib.aui.InputTag _jspx_th_aui_input_3 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
    _jspx_th_aui_input_3.setPageContext(_jspx_page_context);
    _jspx_th_aui_input_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
    _jspx_th_aui_input_3.setName("type");
    _jspx_th_aui_input_3.setType("hidden");
    _jspx_th_aui_input_3.setValue(new String(""));
    int _jspx_eval_aui_input_3 = _jspx_th_aui_input_3.doStartTag();
    if (_jspx_th_aui_input_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_3);
      return true;
    }
    _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_3);
    return false;
  }

  private boolean _jspx_meth_aui_input_4(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_col_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:input
    com.liferay.taglib.aui.InputTag _jspx_th_aui_input_4 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_type_name_label_nobody.get(com.liferay.taglib.aui.InputTag.class);
    _jspx_th_aui_input_4.setPageContext(_jspx_page_context);
    _jspx_th_aui_input_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_col_2);
    _jspx_th_aui_input_4.setLabel("");
    _jspx_th_aui_input_4.setName("checkAll");
    _jspx_th_aui_input_4.setType("checkbox");
    int _jspx_eval_aui_input_4 = _jspx_th_aui_input_4.doStartTag();
    if (_jspx_th_aui_input_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_input_type_name_label_nobody.reuse(_jspx_th_aui_input_4);
      return true;
    }
    _jspx_tagPool_aui_input_type_name_label_nobody.reuse(_jspx_th_aui_input_4);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_1(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_1 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_1.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_2);
    _jspx_th_liferay$1ui_message_1.setKey("members-of");
    int _jspx_eval_liferay$1ui_message_1 = _jspx_th_liferay$1ui_message_1.doStartTag();
    if (_jspx_th_liferay$1ui_message_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_1);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_1);
    return false;
  }

  private boolean _jspx_meth_aui_button_0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:button
    com.liferay.taglib.aui.ButtonTag _jspx_th_aui_button_0 = (com.liferay.taglib.aui.ButtonTag) _jspx_tagPool_aui_button_value_id_icon_cssClass_nobody.get(com.liferay.taglib.aui.ButtonTag.class);
    _jspx_th_aui_button_0.setPageContext(_jspx_page_context);
    _jspx_th_aui_button_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_3);
    _jspx_th_aui_button_0.setCssClass("add-contact");
    _jspx_th_aui_button_0.setIcon("icon-plus-sign");
    _jspx_th_aui_button_0.setId("addContact");
    _jspx_th_aui_button_0.setValue("add-contact");
    int _jspx_eval_aui_button_0 = _jspx_th_aui_button_0.doStartTag();
    if (_jspx_th_aui_button_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_button_value_id_icon_cssClass_nobody.reuse(_jspx_th_aui_button_0);
      return true;
    }
    _jspx_tagPool_aui_button_value_id_icon_cssClass_nobody.reuse(_jspx_th_aui_button_0);
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
    _jspx_th_portlet_param_0.setName("mvcPath");
    _jspx_th_portlet_param_0.setValue("/contacts_center/view_resources.jsp");
    int _jspx_eval_portlet_param_0 = _jspx_th_portlet_param_0.doStartTag();
    if (_jspx_th_portlet_param_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_0);
      return true;
    }
    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_0);
    return false;
  }

  private boolean _jspx_meth_portlet_param_3(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1portlet_renderURL_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:param
    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_3 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
    _jspx_th_portlet_param_3.setPageContext(_jspx_page_context);
    _jspx_th_portlet_param_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_1);
    _jspx_th_portlet_param_3.setName("mvcPath");
    _jspx_th_portlet_param_3.setValue("/contacts_center/view_resources.jsp");
    int _jspx_eval_portlet_param_3 = _jspx_th_portlet_param_3.doStartTag();
    if (_jspx_th_portlet_param_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_3);
      return true;
    }
    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_3);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_4(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_7, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_4 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_4.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_7);
    _jspx_th_liferay$1ui_message_4.setKey("view-more");
    int _jspx_eval_liferay$1ui_message_4 = _jspx_th_liferay$1ui_message_4.doStartTag();
    if (_jspx_th_liferay$1ui_message_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_4);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_4);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_0(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_col_5, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_0 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_0.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_col_5);
    int _jspx_eval_portlet_namespace_0 = _jspx_th_portlet_namespace_0.doStartTag();
    if (_jspx_th_portlet_namespace_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_0);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_0);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_1(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_col_5, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_1 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_1.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_col_5);
    int _jspx_eval_portlet_namespace_1 = _jspx_th_portlet_namespace_1.doStartTag();
    if (_jspx_th_portlet_namespace_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_1);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_1);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_2(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_col_5, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_2 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_2.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_col_5);
    int _jspx_eval_portlet_namespace_2 = _jspx_th_portlet_namespace_2.doStartTag();
    if (_jspx_th_portlet_namespace_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_2);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_2);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_3(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_col_5, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_3 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_3.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_col_5);
    int _jspx_eval_portlet_namespace_3 = _jspx_th_portlet_namespace_3.doStartTag();
    if (_jspx_th_portlet_namespace_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_3);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_3);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_header_0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_when_3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:header
    com.liferay.taglib.ui.HeaderTag _jspx_th_liferay$1ui_header_0 = (com.liferay.taglib.ui.HeaderTag) _jspx_tagPool_liferay$1ui_header_title_nobody.get(com.liferay.taglib.ui.HeaderTag.class);
    _jspx_th_liferay$1ui_header_0.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_header_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_3);
    _jspx_th_liferay$1ui_header_0.setTitle("contacts-center");
    int _jspx_eval_liferay$1ui_header_0 = _jspx_th_liferay$1ui_header_0.doStartTag();
    if (_jspx_th_liferay$1ui_header_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_header_title_nobody.reuse(_jspx_th_liferay$1ui_header_0);
      return true;
    }
    _jspx_tagPool_liferay$1ui_header_title_nobody.reuse(_jspx_th_liferay$1ui_header_0);
    return false;
  }

  private boolean _jspx_meth_c_otherwise_3(javax.servlet.jsp.tagext.JspTag _jspx_th_c_choose_3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:otherwise
    com.liferay.taglib.core.OtherwiseTag _jspx_th_c_otherwise_3 = (com.liferay.taglib.core.OtherwiseTag) _jspx_tagPool_c_otherwise.get(com.liferay.taglib.core.OtherwiseTag.class);
    _jspx_th_c_otherwise_3.setPageContext(_jspx_page_context);
    _jspx_th_c_otherwise_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_3);
    int _jspx_eval_c_otherwise_3 = _jspx_th_c_otherwise_3.doStartTag();
    if (_jspx_eval_c_otherwise_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      out.write("\n\t\t\t\t\t\t\t\t\t\t\t");
      if (_jspx_meth_liferay$1ui_header_1((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_otherwise_3, _jspx_page_context))
        return true;
      out.write("\n\t\t\t\t\t\t\t\t\t\t");
    }
    if (_jspx_th_c_otherwise_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_3);
      return true;
    }
    _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_3);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_header_1(javax.servlet.jsp.tagext.JspTag _jspx_th_c_otherwise_3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:header
    com.liferay.taglib.ui.HeaderTag _jspx_th_liferay$1ui_header_1 = (com.liferay.taglib.ui.HeaderTag) _jspx_tagPool_liferay$1ui_header_title_nobody.get(com.liferay.taglib.ui.HeaderTag.class);
    _jspx_th_liferay$1ui_header_1.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_header_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_3);
    _jspx_th_liferay$1ui_header_1.setTitle("members");
    int _jspx_eval_liferay$1ui_header_1 = _jspx_th_liferay$1ui_header_1.doStartTag();
    if (_jspx_th_liferay$1ui_header_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_header_title_nobody.reuse(_jspx_th_liferay$1ui_header_1);
      return true;
    }
    _jspx_tagPool_liferay$1ui_header_title_nobody.reuse(_jspx_th_liferay$1ui_header_1);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_10(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_col_12, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_10 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_10.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_10.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_col_12);
    _jspx_th_liferay$1ui_message_10.setKey("contacts-center-lets-you-search-view-and-establish-social-relations-with-other-users");
    int _jspx_eval_liferay$1ui_message_10 = _jspx_th_liferay$1ui_message_10.doStartTag();
    if (_jspx_th_liferay$1ui_message_10.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_10);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_10);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_4(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_col_5, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_4 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_4.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_col_5);
    int _jspx_eval_portlet_namespace_4 = _jspx_th_portlet_namespace_4.doStartTag();
    if (_jspx_th_portlet_namespace_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_4);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_4);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_5(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_5 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_5.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_0);
    int _jspx_eval_portlet_namespace_5 = _jspx_th_portlet_namespace_5.doStartTag();
    if (_jspx_th_portlet_namespace_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_5);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_5);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_6(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_6 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_6.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_0);
    int _jspx_eval_portlet_namespace_6 = _jspx_th_portlet_namespace_6.doStartTag();
    if (_jspx_th_portlet_namespace_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_6);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_6);
    return false;
  }

  private boolean _jspx_meth_portlet_resourceURL_1(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:resourceURL
    com.liferay.taglib.portlet.ResourceURLTag _jspx_th_portlet_resourceURL_1 = (com.liferay.taglib.portlet.ResourceURLTag) _jspx_tagPool_portlet_resourceURL_id_nobody.get(com.liferay.taglib.portlet.ResourceURLTag.class);
    _jspx_th_portlet_resourceURL_1.setPageContext(_jspx_page_context);
    _jspx_th_portlet_resourceURL_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_0);
    _jspx_th_portlet_resourceURL_1.setId("getSelectedContacts");
    int _jspx_eval_portlet_resourceURL_1 = _jspx_th_portlet_resourceURL_1.doStartTag();
    if (_jspx_th_portlet_resourceURL_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_resourceURL_id_nobody.reuse(_jspx_th_portlet_resourceURL_1);
      return true;
    }
    _jspx_tagPool_portlet_resourceURL_id_nobody.reuse(_jspx_th_portlet_resourceURL_1);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_7(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_7 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_7.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_0);
    int _jspx_eval_portlet_namespace_7 = _jspx_th_portlet_namespace_7.doStartTag();
    if (_jspx_th_portlet_namespace_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_7);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_7);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_8(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_10, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_8 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_8.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_8.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_10);
    int _jspx_eval_portlet_namespace_8 = _jspx_th_portlet_namespace_8.doStartTag();
    if (_jspx_th_portlet_namespace_8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_8);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_8);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_9(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_9 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_9.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_9.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_0);
    int _jspx_eval_portlet_namespace_9 = _jspx_th_portlet_namespace_9.doStartTag();
    if (_jspx_th_portlet_namespace_9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_9);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_9);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_10(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_10 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_10.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_10.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_0);
    int _jspx_eval_portlet_namespace_10 = _jspx_th_portlet_namespace_10.doStartTag();
    if (_jspx_th_portlet_namespace_10.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_10);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_10);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_11(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_11 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_11.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_11.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_0);
    int _jspx_eval_portlet_namespace_11 = _jspx_th_portlet_namespace_11.doStartTag();
    if (_jspx_th_portlet_namespace_11.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_11);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_11);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_12(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_12 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_12.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_12.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_0);
    int _jspx_eval_portlet_namespace_12 = _jspx_th_portlet_namespace_12.doStartTag();
    if (_jspx_th_portlet_namespace_12.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_12);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_12);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_13(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_13 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_13.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_13.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_0);
    int _jspx_eval_portlet_namespace_13 = _jspx_th_portlet_namespace_13.doStartTag();
    if (_jspx_th_portlet_namespace_13.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_13);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_13);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_14(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_14 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_14.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_14.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_0);
    int _jspx_eval_portlet_namespace_14 = _jspx_th_portlet_namespace_14.doStartTag();
    if (_jspx_th_portlet_namespace_14.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_14);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_14);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_15(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_15 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_15.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_15.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_0);
    int _jspx_eval_portlet_namespace_15 = _jspx_th_portlet_namespace_15.doStartTag();
    if (_jspx_th_portlet_namespace_15.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_15);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_15);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_16(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_16 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_16.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_16.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_0);
    int _jspx_eval_portlet_namespace_16 = _jspx_th_portlet_namespace_16.doStartTag();
    if (_jspx_th_portlet_namespace_16.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_16);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_16);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_17(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_12, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_17 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_17.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_17.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_12);
    int _jspx_eval_portlet_namespace_17 = _jspx_th_portlet_namespace_17.doStartTag();
    if (_jspx_th_portlet_namespace_17.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_17);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_17);
    return false;
  }

  private boolean _jspx_meth_portlet_param_12(javax.servlet.jsp.tagext.JspTag _jspx_th_portlet_renderURL_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:param
    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_12 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
    _jspx_th_portlet_param_12.setPageContext(_jspx_page_context);
    _jspx_th_portlet_param_12.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_0);
    _jspx_th_portlet_param_12.setName("mvcPath");
    _jspx_th_portlet_param_12.setValue("/contacts_center/edit_entry.jsp");
    int _jspx_eval_portlet_param_12 = _jspx_th_portlet_param_12.doStartTag();
    if (_jspx_th_portlet_param_12.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_12);
      return true;
    }
    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_12);
    return false;
  }
}
