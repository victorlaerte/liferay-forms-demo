package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.liferay.map.constants.MapProviderWebKeys;
import com.liferay.petra.content.ContentUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.AccountNameException;
import com.liferay.portal.kernel.exception.CompanyMxException;
import com.liferay.portal.kernel.exception.CompanyVirtualHostException;
import com.liferay.portal.kernel.exception.LocaleException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.language.UnicodeLanguageUtil;
import com.liferay.portal.kernel.model.*;
import com.liferay.portal.kernel.model.RoleConstants;
import com.liferay.portal.kernel.model.impl.*;
import com.liferay.portal.kernel.portlet.PortletProvider;
import com.liferay.portal.kernel.portlet.PortletProviderUtil;
import com.liferay.portal.kernel.security.permission.ResourceActionsUtil;
import com.liferay.portal.kernel.service.*;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.servlet.taglib.DynamicInclude;
import com.liferay.portal.kernel.servlet.taglib.ui.FormNavigatorConstants;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ClassLoaderUtil;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.KeyValuePair;
import com.liferay.portal.kernel.util.KeyValuePairComparator;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PortletKeys;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.TermsOfUseContentProvider;
import com.liferay.portal.kernel.util.TermsOfUseContentProviderRegistryUtil;
import com.liferay.portal.kernel.util.TextFormatter;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.webserver.WebServerServletTokenUtil;
import com.liferay.portal.model.impl.*;
import com.liferay.portal.service.*;
import com.liferay.portal.settings.web.internal.constants.PortalSettingsWebKeys;
import com.liferay.portal.settings.web.internal.exception.RequiredLocaleException;
import com.liferay.portal.util.PrefsPropsUtil;
import com.liferay.portal.util.PropsValues;
import com.liferay.ratings.kernel.RatingsType;
import com.liferay.ratings.kernel.display.context.CompanyPortletRatingsDefinitionDisplayContext;
import com.liferay.ratings.kernel.transformer.RatingsDataTransformerUtil;
import com.liferay.sites.kernel.util.Sites;
import com.liferay.taglib.servlet.PipingServletResponse;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletURL;

public final class email_005fnotifications_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(3);
    _jspx_dependants.add("/init.jsp");
    _jspx_dependants.add("/init-ext.jsp");
    _jspx_dependants.add("/definition_of_terms.jspf");
  }

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_input_value_type_name_label_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_input$1localized_xml_name_fieldPrefixSeparator_fieldPrefix_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_section;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_message_key_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_if_test;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_field$1wrapper_label;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_fieldset_label_cssClass;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1frontend_email$1notification$1settings_showEmailEnabled_fieldPrefix_emailSubject_emailParam_emailBody_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1frontend_email$1notification$1settings_showSubject_showEmailEnabled_helpMessage_fieldPrefix_emailParam_emailBody_bodyLabel_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_tabs_refresh_names;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_error_message_key_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1theme_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_error$1marker_value_key_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1frontend_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_input_value_type_name_label_cssClass_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_fieldset;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_aui_input_value_type_name_label_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_input$1localized_xml_name_fieldPrefixSeparator_fieldPrefix_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_section = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_message_key_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_if_test = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_field$1wrapper_label = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_fieldset_label_cssClass = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1frontend_email$1notification$1settings_showEmailEnabled_fieldPrefix_emailSubject_emailParam_emailBody_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1frontend_email$1notification$1settings_showSubject_showEmailEnabled_helpMessage_fieldPrefix_emailParam_emailBody_bodyLabel_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_tabs_refresh_names = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_error_message_key_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1theme_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_error$1marker_value_key_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1frontend_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_input_value_type_name_label_cssClass_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_fieldset = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_aui_input_value_type_name_label_nobody.release();
    _jspx_tagPool_liferay$1ui_input$1localized_xml_name_fieldPrefixSeparator_fieldPrefix_nobody.release();
    _jspx_tagPool_liferay$1ui_section.release();
    _jspx_tagPool_liferay$1ui_message_key_nobody.release();
    _jspx_tagPool_c_if_test.release();
    _jspx_tagPool_aui_field$1wrapper_label.release();
    _jspx_tagPool_aui_fieldset_label_cssClass.release();
    _jspx_tagPool_liferay$1frontend_email$1notification$1settings_showEmailEnabled_fieldPrefix_emailSubject_emailParam_emailBody_nobody.release();
    _jspx_tagPool_liferay$1frontend_email$1notification$1settings_showSubject_showEmailEnabled_helpMessage_fieldPrefix_emailParam_emailBody_bodyLabel_nobody.release();
    _jspx_tagPool_portlet_defineObjects_nobody.release();
    _jspx_tagPool_liferay$1ui_tabs_refresh_names.release();
    _jspx_tagPool_liferay$1ui_error_message_key_nobody.release();
    _jspx_tagPool_liferay$1theme_defineObjects_nobody.release();
    _jspx_tagPool_liferay$1ui_error$1marker_value_key_nobody.release();
    _jspx_tagPool_liferay$1frontend_defineObjects_nobody.release();
    _jspx_tagPool_aui_input_value_type_name_label_cssClass_nobody.release();
    _jspx_tagPool_aui_fieldset.release();
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
      out.write("\n\n<h3>");
      if (_jspx_meth_liferay$1ui_message_0(_jspx_page_context))
        return;
      out.write("</h3>\n\n");

String adminEmailFromName = PrefsPropsUtil.getString(company.getCompanyId(), PropsKeys.ADMIN_EMAIL_FROM_NAME);
String adminEmailFromAddress = PrefsPropsUtil.getString(company.getCompanyId(), PropsKeys.ADMIN_EMAIL_FROM_ADDRESS);

PortletPreferences companyPortletPreferences = PrefsPropsUtil.getPreferences(company.getCompanyId(), true);

      out.write('\n');
      out.write('\n');
      //  liferay-ui:error-marker
      com.liferay.taglib.ui.ErrorMarkerTag _jspx_th_liferay$1ui_error$1marker_0 = (com.liferay.taglib.ui.ErrorMarkerTag) _jspx_tagPool_liferay$1ui_error$1marker_value_key_nobody.get(com.liferay.taglib.ui.ErrorMarkerTag.class);
      _jspx_th_liferay$1ui_error$1marker_0.setPageContext(_jspx_page_context);
      _jspx_th_liferay$1ui_error$1marker_0.setParent(null);
      _jspx_th_liferay$1ui_error$1marker_0.setKey( WebKeys.ERROR_SECTION );
      _jspx_th_liferay$1ui_error$1marker_0.setValue("email_notifications");
      int _jspx_eval_liferay$1ui_error$1marker_0 = _jspx_th_liferay$1ui_error$1marker_0.doStartTag();
      if (_jspx_th_liferay$1ui_error$1marker_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_liferay$1ui_error$1marker_value_key_nobody.reuse(_jspx_th_liferay$1ui_error$1marker_0);
        return;
      }
      _jspx_tagPool_liferay$1ui_error$1marker_value_key_nobody.reuse(_jspx_th_liferay$1ui_error$1marker_0);
      out.write('\n');
      out.write('\n');
      //  liferay-ui:tabs
      com.liferay.taglib.ui.TabsTag _jspx_th_liferay$1ui_tabs_0 = (com.liferay.taglib.ui.TabsTag) _jspx_tagPool_liferay$1ui_tabs_refresh_names.get(com.liferay.taglib.ui.TabsTag.class);
      _jspx_th_liferay$1ui_tabs_0.setPageContext(_jspx_page_context);
      _jspx_th_liferay$1ui_tabs_0.setParent(null);
      _jspx_th_liferay$1ui_tabs_0.setNames("sender,account-created-notification,email-verification-notification,password-changed-notification,password-reset-notification");
      _jspx_th_liferay$1ui_tabs_0.setRefresh( false );
      int _jspx_eval_liferay$1ui_tabs_0 = _jspx_th_liferay$1ui_tabs_0.doStartTag();
      if (_jspx_eval_liferay$1ui_tabs_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        out.write('\n');
        out.write('	');
        //  liferay-ui:section
        com.liferay.taglib.ui.SectionTag _jspx_th_liferay$1ui_section_0 = (com.liferay.taglib.ui.SectionTag) _jspx_tagPool_liferay$1ui_section.get(com.liferay.taglib.ui.SectionTag.class);
        _jspx_th_liferay$1ui_section_0.setPageContext(_jspx_page_context);
        _jspx_th_liferay$1ui_section_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_tabs_0);
        int _jspx_eval_liferay$1ui_section_0 = _jspx_th_liferay$1ui_section_0.doStartTag();
        if (_jspx_eval_liferay$1ui_section_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          java.lang.String sectionParam = null;
          java.lang.String sectionName = null;
          java.lang.Boolean sectionSelected = null;
          java.lang.String sectionScroll = null;
          java.lang.String sectionRedirectParams = null;
          sectionParam = (java.lang.String) _jspx_page_context.findAttribute("sectionParam");
          sectionName = (java.lang.String) _jspx_page_context.findAttribute("sectionName");
          sectionSelected = (java.lang.Boolean) _jspx_page_context.findAttribute("sectionSelected");
          sectionScroll = (java.lang.String) _jspx_page_context.findAttribute("sectionScroll");
          sectionRedirectParams = (java.lang.String) _jspx_page_context.findAttribute("sectionRedirectParams");
          out.write("\n\t\t");
          //  aui:fieldset
          com.liferay.taglib.aui.FieldsetTag _jspx_th_aui_fieldset_0 = (com.liferay.taglib.aui.FieldsetTag) _jspx_tagPool_aui_fieldset.get(com.liferay.taglib.aui.FieldsetTag.class);
          _jspx_th_aui_fieldset_0.setPageContext(_jspx_page_context);
          _jspx_th_aui_fieldset_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_section_0);
          int _jspx_eval_aui_fieldset_0 = _jspx_th_aui_fieldset_0.doStartTag();
          if (_jspx_eval_aui_fieldset_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t\t");
            //  liferay-ui:error
            com.liferay.taglib.ui.ErrorTag _jspx_th_liferay$1ui_error_0 = (com.liferay.taglib.ui.ErrorTag) _jspx_tagPool_liferay$1ui_error_message_key_nobody.get(com.liferay.taglib.ui.ErrorTag.class);
            _jspx_th_liferay$1ui_error_0.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1ui_error_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_0);
            _jspx_th_liferay$1ui_error_0.setKey("emailFromName");
            _jspx_th_liferay$1ui_error_0.setMessage("please-enter-a-valid-name");
            int _jspx_eval_liferay$1ui_error_0 = _jspx_th_liferay$1ui_error_0.doStartTag();
            if (_jspx_th_liferay$1ui_error_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_liferay$1ui_error_message_key_nobody.reuse(_jspx_th_liferay$1ui_error_0);
              return;
            }
            _jspx_tagPool_liferay$1ui_error_message_key_nobody.reuse(_jspx_th_liferay$1ui_error_0);
            out.write("\n\n\t\t\t");
            //  aui:input
            com.liferay.taglib.aui.InputTag _jspx_th_aui_input_0 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_label_cssClass_nobody.get(com.liferay.taglib.aui.InputTag.class);
            _jspx_th_aui_input_0.setPageContext(_jspx_page_context);
            _jspx_th_aui_input_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_0);
            _jspx_th_aui_input_0.setCssClass("lfr-input-text-container");
            _jspx_th_aui_input_0.setLabel("name");
            _jspx_th_aui_input_0.setName( "settings--" + PropsKeys.ADMIN_EMAIL_FROM_NAME + "--" );
            _jspx_th_aui_input_0.setType("text");
            _jspx_th_aui_input_0.setValue( adminEmailFromName );
            int _jspx_eval_aui_input_0 = _jspx_th_aui_input_0.doStartTag();
            if (_jspx_th_aui_input_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_aui_input_value_type_name_label_cssClass_nobody.reuse(_jspx_th_aui_input_0);
              return;
            }
            _jspx_tagPool_aui_input_value_type_name_label_cssClass_nobody.reuse(_jspx_th_aui_input_0);
            out.write("\n\n\t\t\t");
            //  liferay-ui:error
            com.liferay.taglib.ui.ErrorTag _jspx_th_liferay$1ui_error_1 = (com.liferay.taglib.ui.ErrorTag) _jspx_tagPool_liferay$1ui_error_message_key_nobody.get(com.liferay.taglib.ui.ErrorTag.class);
            _jspx_th_liferay$1ui_error_1.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1ui_error_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_0);
            _jspx_th_liferay$1ui_error_1.setKey("emailFromAddress");
            _jspx_th_liferay$1ui_error_1.setMessage("please-enter-a-valid-email-address");
            int _jspx_eval_liferay$1ui_error_1 = _jspx_th_liferay$1ui_error_1.doStartTag();
            if (_jspx_th_liferay$1ui_error_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_liferay$1ui_error_message_key_nobody.reuse(_jspx_th_liferay$1ui_error_1);
              return;
            }
            _jspx_tagPool_liferay$1ui_error_message_key_nobody.reuse(_jspx_th_liferay$1ui_error_1);
            out.write("\n\n\t\t\t");
            //  aui:input
            com.liferay.taglib.aui.InputTag _jspx_th_aui_input_1 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_label_cssClass_nobody.get(com.liferay.taglib.aui.InputTag.class);
            _jspx_th_aui_input_1.setPageContext(_jspx_page_context);
            _jspx_th_aui_input_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_0);
            _jspx_th_aui_input_1.setCssClass("lfr-input-text-container");
            _jspx_th_aui_input_1.setLabel("address");
            _jspx_th_aui_input_1.setName( "settings--" + PropsKeys.ADMIN_EMAIL_FROM_ADDRESS + "--" );
            _jspx_th_aui_input_1.setType("text");
            _jspx_th_aui_input_1.setValue( adminEmailFromAddress );
            int _jspx_eval_aui_input_1 = _jspx_th_aui_input_1.doStartTag();
            if (_jspx_th_aui_input_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_aui_input_value_type_name_label_cssClass_nobody.reuse(_jspx_th_aui_input_1);
              return;
            }
            _jspx_tagPool_aui_input_value_type_name_label_cssClass_nobody.reuse(_jspx_th_aui_input_1);
            out.write("\n\t\t");
          }
          if (_jspx_th_aui_fieldset_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_aui_fieldset.reuse(_jspx_th_aui_fieldset_0);
            return;
          }
          _jspx_tagPool_aui_fieldset.reuse(_jspx_th_aui_fieldset_0);
          out.write('\n');
          out.write('	');
        }
        if (_jspx_th_liferay$1ui_section_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_liferay$1ui_section.reuse(_jspx_th_liferay$1ui_section_0);
          return;
        }
        _jspx_tagPool_liferay$1ui_section.reuse(_jspx_th_liferay$1ui_section_0);
        out.write("\n\n\t");
        //  liferay-ui:section
        com.liferay.taglib.ui.SectionTag _jspx_th_liferay$1ui_section_1 = (com.liferay.taglib.ui.SectionTag) _jspx_tagPool_liferay$1ui_section.get(com.liferay.taglib.ui.SectionTag.class);
        _jspx_th_liferay$1ui_section_1.setPageContext(_jspx_page_context);
        _jspx_th_liferay$1ui_section_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_tabs_0);
        int _jspx_eval_liferay$1ui_section_1 = _jspx_th_liferay$1ui_section_1.doStartTag();
        if (_jspx_eval_liferay$1ui_section_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          java.lang.String sectionParam = null;
          java.lang.String sectionName = null;
          java.lang.Boolean sectionSelected = null;
          java.lang.String sectionScroll = null;
          java.lang.String sectionRedirectParams = null;
          sectionParam = (java.lang.String) _jspx_page_context.findAttribute("sectionParam");
          sectionName = (java.lang.String) _jspx_page_context.findAttribute("sectionName");
          sectionSelected = (java.lang.Boolean) _jspx_page_context.findAttribute("sectionSelected");
          sectionScroll = (java.lang.String) _jspx_page_context.findAttribute("sectionScroll");
          sectionRedirectParams = (java.lang.String) _jspx_page_context.findAttribute("sectionRedirectParams");
          out.write("\n\t\t");
          //  aui:fieldset
          com.liferay.taglib.aui.FieldsetTag _jspx_th_aui_fieldset_1 = (com.liferay.taglib.aui.FieldsetTag) _jspx_tagPool_aui_fieldset.get(com.liferay.taglib.aui.FieldsetTag.class);
          _jspx_th_aui_fieldset_1.setPageContext(_jspx_page_context);
          _jspx_th_aui_fieldset_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_section_1);
          int _jspx_eval_aui_fieldset_1 = _jspx_th_aui_fieldset_1.doStartTag();
          if (_jspx_eval_aui_fieldset_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t\t");
            //  aui:input
            com.liferay.taglib.aui.InputTag _jspx_th_aui_input_2 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_label_nobody.get(com.liferay.taglib.aui.InputTag.class);
            _jspx_th_aui_input_2.setPageContext(_jspx_page_context);
            _jspx_th_aui_input_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_1);
            _jspx_th_aui_input_2.setLabel("enabled");
            _jspx_th_aui_input_2.setName( "settings--" + PropsKeys.ADMIN_EMAIL_USER_ADDED_ENABLED + "--" );
            _jspx_th_aui_input_2.setType("checkbox");
            _jspx_th_aui_input_2.setValue( PrefsPropsUtil.getBoolean(company.getCompanyId(), PropsKeys.ADMIN_EMAIL_USER_ADDED_ENABLED) );
            int _jspx_eval_aui_input_2 = _jspx_th_aui_input_2.doStartTag();
            if (_jspx_th_aui_input_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_aui_input_value_type_name_label_nobody.reuse(_jspx_th_aui_input_2);
              return;
            }
            _jspx_tagPool_aui_input_value_type_name_label_nobody.reuse(_jspx_th_aui_input_2);
            out.write("\n\n\t\t\t");
            //  liferay-ui:error
            com.liferay.taglib.ui.ErrorTag _jspx_th_liferay$1ui_error_2 = (com.liferay.taglib.ui.ErrorTag) _jspx_tagPool_liferay$1ui_error_message_key_nobody.get(com.liferay.taglib.ui.ErrorTag.class);
            _jspx_th_liferay$1ui_error_2.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1ui_error_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_1);
            _jspx_th_liferay$1ui_error_2.setKey("emailUserAddedSubject");
            _jspx_th_liferay$1ui_error_2.setMessage("please-enter-a-valid-subject");
            int _jspx_eval_liferay$1ui_error_2 = _jspx_th_liferay$1ui_error_2.doStartTag();
            if (_jspx_th_liferay$1ui_error_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_liferay$1ui_error_message_key_nobody.reuse(_jspx_th_liferay$1ui_error_2);
              return;
            }
            _jspx_tagPool_liferay$1ui_error_message_key_nobody.reuse(_jspx_th_liferay$1ui_error_2);
            out.write("\n\n\t\t\t");
            //  aui:field-wrapper
            com.liferay.taglib.aui.FieldWrapperTag _jspx_th_aui_field$1wrapper_0 = (com.liferay.taglib.aui.FieldWrapperTag) _jspx_tagPool_aui_field$1wrapper_label.get(com.liferay.taglib.aui.FieldWrapperTag.class);
            _jspx_th_aui_field$1wrapper_0.setPageContext(_jspx_page_context);
            _jspx_th_aui_field$1wrapper_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_1);
            _jspx_th_aui_field$1wrapper_0.setLabel("subject");
            int _jspx_eval_aui_field$1wrapper_0 = _jspx_th_aui_field$1wrapper_0.doStartTag();
            if (_jspx_eval_aui_field$1wrapper_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\t\t\t\t");
              //  liferay-ui:input-localized
              com.liferay.taglib.ui.InputLocalizedTag _jspx_th_liferay$1ui_input$1localized_0 = (com.liferay.taglib.ui.InputLocalizedTag) _jspx_tagPool_liferay$1ui_input$1localized_xml_name_fieldPrefixSeparator_fieldPrefix_nobody.get(com.liferay.taglib.ui.InputLocalizedTag.class);
              _jspx_th_liferay$1ui_input$1localized_0.setPageContext(_jspx_page_context);
              _jspx_th_liferay$1ui_input$1localized_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_field$1wrapper_0);
              _jspx_th_liferay$1ui_input$1localized_0.setFieldPrefix("settings");
              _jspx_th_liferay$1ui_input$1localized_0.setFieldPrefixSeparator("--");
              _jspx_th_liferay$1ui_input$1localized_0.setName("adminEmailUserAddedSubject");
              _jspx_th_liferay$1ui_input$1localized_0.setXml( LocalizationUtil.getLocalizationXmlFromPreferences(companyPortletPreferences, renderRequest, "adminEmailUserAddedSubject", "settings", ContentUtil.get(ClassLoaderUtil.getPortalClassLoader(), PropsValues.ADMIN_EMAIL_USER_ADDED_SUBJECT)) );
              int _jspx_eval_liferay$1ui_input$1localized_0 = _jspx_th_liferay$1ui_input$1localized_0.doStartTag();
              if (_jspx_th_liferay$1ui_input$1localized_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_liferay$1ui_input$1localized_xml_name_fieldPrefixSeparator_fieldPrefix_nobody.reuse(_jspx_th_liferay$1ui_input$1localized_0);
                return;
              }
              _jspx_tagPool_liferay$1ui_input$1localized_xml_name_fieldPrefixSeparator_fieldPrefix_nobody.reuse(_jspx_th_liferay$1ui_input$1localized_0);
              out.write("\n\t\t\t");
            }
            if (_jspx_th_aui_field$1wrapper_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_aui_field$1wrapper_label.reuse(_jspx_th_aui_field$1wrapper_0);
              return;
            }
            _jspx_tagPool_aui_field$1wrapper_label.reuse(_jspx_th_aui_field$1wrapper_0);
            out.write("\n\n\t\t\t");
            //  liferay-ui:error
            com.liferay.taglib.ui.ErrorTag _jspx_th_liferay$1ui_error_3 = (com.liferay.taglib.ui.ErrorTag) _jspx_tagPool_liferay$1ui_error_message_key_nobody.get(com.liferay.taglib.ui.ErrorTag.class);
            _jspx_th_liferay$1ui_error_3.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1ui_error_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_1);
            _jspx_th_liferay$1ui_error_3.setKey("emailUserAddedBody");
            _jspx_th_liferay$1ui_error_3.setMessage("please-enter-a-valid-body");
            int _jspx_eval_liferay$1ui_error_3 = _jspx_th_liferay$1ui_error_3.doStartTag();
            if (_jspx_th_liferay$1ui_error_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_liferay$1ui_error_message_key_nobody.reuse(_jspx_th_liferay$1ui_error_3);
              return;
            }
            _jspx_tagPool_liferay$1ui_error_message_key_nobody.reuse(_jspx_th_liferay$1ui_error_3);
            out.write("\n\n\t\t\t");
            //  liferay-frontend:email-notification-settings
            com.liferay.frontend.taglib.servlet.taglib.EmailNotificationSettingsTag _jspx_th_liferay$1frontend_email$1notification$1settings_0 = (com.liferay.frontend.taglib.servlet.taglib.EmailNotificationSettingsTag) _jspx_tagPool_liferay$1frontend_email$1notification$1settings_showSubject_showEmailEnabled_helpMessage_fieldPrefix_emailParam_emailBody_bodyLabel_nobody.get(com.liferay.frontend.taglib.servlet.taglib.EmailNotificationSettingsTag.class);
            _jspx_th_liferay$1frontend_email$1notification$1settings_0.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1frontend_email$1notification$1settings_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_1);
            _jspx_th_liferay$1frontend_email$1notification$1settings_0.setBodyLabel("body-with-password");
            _jspx_th_liferay$1frontend_email$1notification$1settings_0.setEmailBody( LocalizationUtil.getLocalizationXmlFromPreferences(companyPortletPreferences, renderRequest, "adminEmailUserAddedBody", "settings", ContentUtil.get(ClassLoaderUtil.getPortalClassLoader(), PropsValues.ADMIN_EMAIL_USER_ADDED_BODY)) );
            _jspx_th_liferay$1frontend_email$1notification$1settings_0.setEmailParam("adminEmailUserAdded");
            _jspx_th_liferay$1frontend_email$1notification$1settings_0.setFieldPrefix("settings");
            _jspx_th_liferay$1frontend_email$1notification$1settings_0.setHelpMessage("account-created-notification-body-with-password-help");
            _jspx_th_liferay$1frontend_email$1notification$1settings_0.setShowEmailEnabled( false );
            _jspx_th_liferay$1frontend_email$1notification$1settings_0.setShowSubject( false );
            int _jspx_eval_liferay$1frontend_email$1notification$1settings_0 = _jspx_th_liferay$1frontend_email$1notification$1settings_0.doStartTag();
            if (_jspx_th_liferay$1frontend_email$1notification$1settings_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_liferay$1frontend_email$1notification$1settings_showSubject_showEmailEnabled_helpMessage_fieldPrefix_emailParam_emailBody_bodyLabel_nobody.reuse(_jspx_th_liferay$1frontend_email$1notification$1settings_0);
              return;
            }
            _jspx_tagPool_liferay$1frontend_email$1notification$1settings_showSubject_showEmailEnabled_helpMessage_fieldPrefix_emailParam_emailBody_bodyLabel_nobody.reuse(_jspx_th_liferay$1frontend_email$1notification$1settings_0);
            out.write("\n\n\t\t\t");
            //  liferay-ui:error
            com.liferay.taglib.ui.ErrorTag _jspx_th_liferay$1ui_error_4 = (com.liferay.taglib.ui.ErrorTag) _jspx_tagPool_liferay$1ui_error_message_key_nobody.get(com.liferay.taglib.ui.ErrorTag.class);
            _jspx_th_liferay$1ui_error_4.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1ui_error_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_1);
            _jspx_th_liferay$1ui_error_4.setKey("emailUserAddedNoPasswordBody");
            _jspx_th_liferay$1ui_error_4.setMessage("please-enter-a-valid-body");
            int _jspx_eval_liferay$1ui_error_4 = _jspx_th_liferay$1ui_error_4.doStartTag();
            if (_jspx_th_liferay$1ui_error_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_liferay$1ui_error_message_key_nobody.reuse(_jspx_th_liferay$1ui_error_4);
              return;
            }
            _jspx_tagPool_liferay$1ui_error_message_key_nobody.reuse(_jspx_th_liferay$1ui_error_4);
            out.write("\n\n\t\t\t");
            //  liferay-frontend:email-notification-settings
            com.liferay.frontend.taglib.servlet.taglib.EmailNotificationSettingsTag _jspx_th_liferay$1frontend_email$1notification$1settings_1 = (com.liferay.frontend.taglib.servlet.taglib.EmailNotificationSettingsTag) _jspx_tagPool_liferay$1frontend_email$1notification$1settings_showSubject_showEmailEnabled_helpMessage_fieldPrefix_emailParam_emailBody_bodyLabel_nobody.get(com.liferay.frontend.taglib.servlet.taglib.EmailNotificationSettingsTag.class);
            _jspx_th_liferay$1frontend_email$1notification$1settings_1.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1frontend_email$1notification$1settings_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_1);
            _jspx_th_liferay$1frontend_email$1notification$1settings_1.setBodyLabel("body-without-password");
            _jspx_th_liferay$1frontend_email$1notification$1settings_1.setEmailBody( LocalizationUtil.getLocalizationXmlFromPreferences(companyPortletPreferences, renderRequest, "adminEmailUserAddedNoPasswordBody", "settings", ContentUtil.get(ClassLoaderUtil.getPortalClassLoader(), PropsValues.ADMIN_EMAIL_USER_ADDED_NO_PASSWORD_BODY)) );
            _jspx_th_liferay$1frontend_email$1notification$1settings_1.setEmailParam("adminEmailUserAddedNoPassword");
            _jspx_th_liferay$1frontend_email$1notification$1settings_1.setFieldPrefix("settings");
            _jspx_th_liferay$1frontend_email$1notification$1settings_1.setHelpMessage("account-created-notification-body-without-password-help");
            _jspx_th_liferay$1frontend_email$1notification$1settings_1.setShowEmailEnabled( false );
            _jspx_th_liferay$1frontend_email$1notification$1settings_1.setShowSubject( false );
            int _jspx_eval_liferay$1frontend_email$1notification$1settings_1 = _jspx_th_liferay$1frontend_email$1notification$1settings_1.doStartTag();
            if (_jspx_th_liferay$1frontend_email$1notification$1settings_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_liferay$1frontend_email$1notification$1settings_showSubject_showEmailEnabled_helpMessage_fieldPrefix_emailParam_emailBody_bodyLabel_nobody.reuse(_jspx_th_liferay$1frontend_email$1notification$1settings_1);
              return;
            }
            _jspx_tagPool_liferay$1frontend_email$1notification$1settings_showSubject_showEmailEnabled_helpMessage_fieldPrefix_emailParam_emailBody_bodyLabel_nobody.reuse(_jspx_th_liferay$1frontend_email$1notification$1settings_1);
            out.write("\n\n\t\t\t");
            //  aui:fieldset
            com.liferay.taglib.aui.FieldsetTag _jspx_th_aui_fieldset_2 = (com.liferay.taglib.aui.FieldsetTag) _jspx_tagPool_aui_fieldset_label_cssClass.get(com.liferay.taglib.aui.FieldsetTag.class);
            _jspx_th_aui_fieldset_2.setPageContext(_jspx_page_context);
            _jspx_th_aui_fieldset_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_1);
            _jspx_th_aui_fieldset_2.setCssClass("definition-of-terms email-user-add terms");
            _jspx_th_aui_fieldset_2.setLabel("definition-of-terms");
            int _jspx_eval_aui_fieldset_2 = _jspx_th_aui_fieldset_2.doStartTag();
            if (_jspx_eval_aui_fieldset_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\t\t\t\t");
              out.write("\n\n<dl>\n\t");
              //  c:if
              com.liferay.taglib.core.IfTag _jspx_th_c_if_0 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
              _jspx_th_c_if_0.setPageContext(_jspx_page_context);
              _jspx_th_c_if_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_2);
              _jspx_th_c_if_0.setTest( sectionName.equals("email-verification-notification") );
              int _jspx_eval_c_if_0 = _jspx_th_c_if_0.doStartTag();
              if (_jspx_eval_c_if_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\t\t<dt>\n\t\t\t[$EMAIL_VERIFICATION_CODE$]\n\t\t</dt>\n\t\t<dd>\n\t\t\t");
                if (_jspx_meth_liferay$1ui_message_1((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_0, _jspx_page_context))
                  return;
                out.write("\n\t\t</dd>\n\t\t<dt>\n\t\t\t[$EMAIL_VERIFICATION_URL$]\n\t\t</dt>\n\t\t<dd>\n\t\t\t");
                if (_jspx_meth_liferay$1ui_message_2((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_0, _jspx_page_context))
                  return;
                out.write("\n\t\t</dd>\n\t");
              }
              if (_jspx_th_c_if_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
                return;
              }
              _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
              out.write("\n\n\t<dt>\n\t\t[$FROM_ADDRESS$]\n\t</dt>\n\t<dd>\n\t\t");
              out.print( HtmlUtil.escape(adminEmailFromAddress) );
              out.write("\n\t</dd>\n\t<dt>\n\t\t[$FROM_NAME$]\n\t</dt>\n\t<dd>\n\t\t");
              out.print( HtmlUtil.escape(adminEmailFromName) );
              out.write("\n\t</dd>\n\n\t");
              //  c:if
              com.liferay.taglib.core.IfTag _jspx_th_c_if_1 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
              _jspx_th_c_if_1.setPageContext(_jspx_page_context);
              _jspx_th_c_if_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_2);
              _jspx_th_c_if_1.setTest( sectionName.equals("password-reset-notification") );
              int _jspx_eval_c_if_1 = _jspx_th_c_if_1.doStartTag();
              if (_jspx_eval_c_if_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\t\t<dt>\n\t\t\t[$PASSWORD_RESET_URL$]\n\t\t</dt>\n\t\t<dd>\n\t\t\t");
                if (_jspx_meth_liferay$1ui_message_3((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_1, _jspx_page_context))
                  return;
                out.write("\n\t\t</dd>\n\t");
              }
              if (_jspx_th_c_if_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_1);
                return;
              }
              _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_1);
              out.write("\n\n\t<dt>\n\t\t[$PORTAL_URL$]\n\t</dt>\n\t<dd>\n\t\t");
              out.print( company.getPortalURL(GroupConstants.DEFAULT_PARENT_GROUP_ID) );
              out.write("\n\t</dd>\n\t<dt class=\"password-changed-notification\">\n\t\t[$REMOTE_ADDRESS$]\n\t</dt>\n\t<dd class=\"password-changed-notification\">\n\t\t");
              if (_jspx_meth_liferay$1ui_message_4((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_fieldset_2, _jspx_page_context))
                return;
              out.write("\n\t</dd>\n\t<dt class=\"password-changed-notification\">\n\t\t[$REMOTE_HOST$]\n\t</dt>\n\t<dd class=\"password-changed-notification\">\n\t\t");
              if (_jspx_meth_liferay$1ui_message_5((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_fieldset_2, _jspx_page_context))
                return;
              out.write("\n\t</dd>\n\t<dt>\n\t\t[$TO_ADDRESS$]\n\t</dt>\n\t<dd>\n\t\t");
              if (_jspx_meth_liferay$1ui_message_6((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_fieldset_2, _jspx_page_context))
                return;
              out.write("\n\t</dd>\n\t<dt>\n\t\t[$TO_NAME$]\n\t</dt>\n\t<dd>\n\t\t");
              if (_jspx_meth_liferay$1ui_message_7((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_fieldset_2, _jspx_page_context))
                return;
              out.write("\n\t</dd>\n\t<dt>\n\t\t[$USER_ID$]\n\t</dt>\n\t<dd>\n\t\t");
              if (_jspx_meth_liferay$1ui_message_8((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_fieldset_2, _jspx_page_context))
                return;
              out.write("\n\t</dd>\n\n\t");
              //  c:if
              com.liferay.taglib.core.IfTag _jspx_th_c_if_2 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
              _jspx_th_c_if_2.setPageContext(_jspx_page_context);
              _jspx_th_c_if_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_2);
              _jspx_th_c_if_2.setTest( !(sectionName.equals("email-verification-notification") || sectionName.equals("password-reset-notification")) );
              int _jspx_eval_c_if_2 = _jspx_th_c_if_2.doStartTag();
              if (_jspx_eval_c_if_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\t\t<dt>\n\t\t\t[$USER_PASSWORD$]\n\t\t</dt>\n\t\t<dd>\n\t\t\t");
                if (_jspx_meth_liferay$1ui_message_9((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_2, _jspx_page_context))
                  return;
                out.write("\n\t\t</dd>\n\t");
              }
              if (_jspx_th_c_if_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_2);
                return;
              }
              _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_2);
              out.write("\n\n\t<dt>\n\t\t[$USER_SCREENNAME$]\n\t</dt>\n\t<dd>\n\t\t");
              if (_jspx_meth_liferay$1ui_message_10((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_fieldset_2, _jspx_page_context))
                return;
              out.write("\n\t</dd>\n</dl>");
              out.write("\n\t\t\t");
            }
            if (_jspx_th_aui_fieldset_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_aui_fieldset_label_cssClass.reuse(_jspx_th_aui_fieldset_2);
              return;
            }
            _jspx_tagPool_aui_fieldset_label_cssClass.reuse(_jspx_th_aui_fieldset_2);
            out.write("\n\t\t");
          }
          if (_jspx_th_aui_fieldset_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_aui_fieldset.reuse(_jspx_th_aui_fieldset_1);
            return;
          }
          _jspx_tagPool_aui_fieldset.reuse(_jspx_th_aui_fieldset_1);
          out.write('\n');
          out.write('	');
        }
        if (_jspx_th_liferay$1ui_section_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_liferay$1ui_section.reuse(_jspx_th_liferay$1ui_section_1);
          return;
        }
        _jspx_tagPool_liferay$1ui_section.reuse(_jspx_th_liferay$1ui_section_1);
        out.write("\n\n\t");
        //  liferay-ui:section
        com.liferay.taglib.ui.SectionTag _jspx_th_liferay$1ui_section_2 = (com.liferay.taglib.ui.SectionTag) _jspx_tagPool_liferay$1ui_section.get(com.liferay.taglib.ui.SectionTag.class);
        _jspx_th_liferay$1ui_section_2.setPageContext(_jspx_page_context);
        _jspx_th_liferay$1ui_section_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_tabs_0);
        int _jspx_eval_liferay$1ui_section_2 = _jspx_th_liferay$1ui_section_2.doStartTag();
        if (_jspx_eval_liferay$1ui_section_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          java.lang.String sectionParam = null;
          java.lang.String sectionName = null;
          java.lang.Boolean sectionSelected = null;
          java.lang.String sectionScroll = null;
          java.lang.String sectionRedirectParams = null;
          sectionParam = (java.lang.String) _jspx_page_context.findAttribute("sectionParam");
          sectionName = (java.lang.String) _jspx_page_context.findAttribute("sectionName");
          sectionSelected = (java.lang.Boolean) _jspx_page_context.findAttribute("sectionSelected");
          sectionScroll = (java.lang.String) _jspx_page_context.findAttribute("sectionScroll");
          sectionRedirectParams = (java.lang.String) _jspx_page_context.findAttribute("sectionRedirectParams");
          out.write("\n\t\t");
          //  liferay-ui:error
          com.liferay.taglib.ui.ErrorTag _jspx_th_liferay$1ui_error_5 = (com.liferay.taglib.ui.ErrorTag) _jspx_tagPool_liferay$1ui_error_message_key_nobody.get(com.liferay.taglib.ui.ErrorTag.class);
          _jspx_th_liferay$1ui_error_5.setPageContext(_jspx_page_context);
          _jspx_th_liferay$1ui_error_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_section_2);
          _jspx_th_liferay$1ui_error_5.setKey("emailVerificationSubject");
          _jspx_th_liferay$1ui_error_5.setMessage("please-enter-a-valid-subject");
          int _jspx_eval_liferay$1ui_error_5 = _jspx_th_liferay$1ui_error_5.doStartTag();
          if (_jspx_th_liferay$1ui_error_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_liferay$1ui_error_message_key_nobody.reuse(_jspx_th_liferay$1ui_error_5);
            return;
          }
          _jspx_tagPool_liferay$1ui_error_message_key_nobody.reuse(_jspx_th_liferay$1ui_error_5);
          out.write("\n\t\t");
          //  liferay-ui:error
          com.liferay.taglib.ui.ErrorTag _jspx_th_liferay$1ui_error_6 = (com.liferay.taglib.ui.ErrorTag) _jspx_tagPool_liferay$1ui_error_message_key_nobody.get(com.liferay.taglib.ui.ErrorTag.class);
          _jspx_th_liferay$1ui_error_6.setPageContext(_jspx_page_context);
          _jspx_th_liferay$1ui_error_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_section_2);
          _jspx_th_liferay$1ui_error_6.setKey("emailVerificationBody");
          _jspx_th_liferay$1ui_error_6.setMessage("please-enter-a-valid-body");
          int _jspx_eval_liferay$1ui_error_6 = _jspx_th_liferay$1ui_error_6.doStartTag();
          if (_jspx_th_liferay$1ui_error_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_liferay$1ui_error_message_key_nobody.reuse(_jspx_th_liferay$1ui_error_6);
            return;
          }
          _jspx_tagPool_liferay$1ui_error_message_key_nobody.reuse(_jspx_th_liferay$1ui_error_6);
          out.write("\n\n\t\t");
          //  liferay-frontend:email-notification-settings
          com.liferay.frontend.taglib.servlet.taglib.EmailNotificationSettingsTag _jspx_th_liferay$1frontend_email$1notification$1settings_2 = (com.liferay.frontend.taglib.servlet.taglib.EmailNotificationSettingsTag) _jspx_tagPool_liferay$1frontend_email$1notification$1settings_showEmailEnabled_fieldPrefix_emailSubject_emailParam_emailBody_nobody.get(com.liferay.frontend.taglib.servlet.taglib.EmailNotificationSettingsTag.class);
          _jspx_th_liferay$1frontend_email$1notification$1settings_2.setPageContext(_jspx_page_context);
          _jspx_th_liferay$1frontend_email$1notification$1settings_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_section_2);
          _jspx_th_liferay$1frontend_email$1notification$1settings_2.setEmailBody( LocalizationUtil.getLocalizationXmlFromPreferences(companyPortletPreferences, renderRequest, "adminEmailVerificationBody", "settings", ContentUtil.get(ClassLoaderUtil.getPortalClassLoader(), PropsValues.ADMIN_EMAIL_VERIFICATION_BODY)) );
          _jspx_th_liferay$1frontend_email$1notification$1settings_2.setEmailParam("adminEmailVerification");
          _jspx_th_liferay$1frontend_email$1notification$1settings_2.setEmailSubject( LocalizationUtil.getLocalizationXmlFromPreferences(companyPortletPreferences, renderRequest, "adminEmailVerificationSubject", "settings", ContentUtil.get(ClassLoaderUtil.getPortalClassLoader(), PropsValues.ADMIN_EMAIL_VERIFICATION_SUBJECT)) );
          _jspx_th_liferay$1frontend_email$1notification$1settings_2.setFieldPrefix("settings");
          _jspx_th_liferay$1frontend_email$1notification$1settings_2.setShowEmailEnabled( false );
          int _jspx_eval_liferay$1frontend_email$1notification$1settings_2 = _jspx_th_liferay$1frontend_email$1notification$1settings_2.doStartTag();
          if (_jspx_th_liferay$1frontend_email$1notification$1settings_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_liferay$1frontend_email$1notification$1settings_showEmailEnabled_fieldPrefix_emailSubject_emailParam_emailBody_nobody.reuse(_jspx_th_liferay$1frontend_email$1notification$1settings_2);
            return;
          }
          _jspx_tagPool_liferay$1frontend_email$1notification$1settings_showEmailEnabled_fieldPrefix_emailSubject_emailParam_emailBody_nobody.reuse(_jspx_th_liferay$1frontend_email$1notification$1settings_2);
          out.write("\n\n\t\t");
          //  aui:fieldset
          com.liferay.taglib.aui.FieldsetTag _jspx_th_aui_fieldset_3 = (com.liferay.taglib.aui.FieldsetTag) _jspx_tagPool_aui_fieldset_label_cssClass.get(com.liferay.taglib.aui.FieldsetTag.class);
          _jspx_th_aui_fieldset_3.setPageContext(_jspx_page_context);
          _jspx_th_aui_fieldset_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_section_2);
          _jspx_th_aui_fieldset_3.setCssClass("definition-of-terms email-verification terms");
          _jspx_th_aui_fieldset_3.setLabel("definition-of-terms");
          int _jspx_eval_aui_fieldset_3 = _jspx_th_aui_fieldset_3.doStartTag();
          if (_jspx_eval_aui_fieldset_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t\t");
            out.write("\n\n<dl>\n\t");
            //  c:if
            com.liferay.taglib.core.IfTag _jspx_th_c_if_3 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
            _jspx_th_c_if_3.setPageContext(_jspx_page_context);
            _jspx_th_c_if_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_3);
            _jspx_th_c_if_3.setTest( sectionName.equals("email-verification-notification") );
            int _jspx_eval_c_if_3 = _jspx_th_c_if_3.doStartTag();
            if (_jspx_eval_c_if_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\t\t<dt>\n\t\t\t[$EMAIL_VERIFICATION_CODE$]\n\t\t</dt>\n\t\t<dd>\n\t\t\t");
              if (_jspx_meth_liferay$1ui_message_11((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_3, _jspx_page_context))
                return;
              out.write("\n\t\t</dd>\n\t\t<dt>\n\t\t\t[$EMAIL_VERIFICATION_URL$]\n\t\t</dt>\n\t\t<dd>\n\t\t\t");
              if (_jspx_meth_liferay$1ui_message_12((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_3, _jspx_page_context))
                return;
              out.write("\n\t\t</dd>\n\t");
            }
            if (_jspx_th_c_if_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_3);
              return;
            }
            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_3);
            out.write("\n\n\t<dt>\n\t\t[$FROM_ADDRESS$]\n\t</dt>\n\t<dd>\n\t\t");
            out.print( HtmlUtil.escape(adminEmailFromAddress) );
            out.write("\n\t</dd>\n\t<dt>\n\t\t[$FROM_NAME$]\n\t</dt>\n\t<dd>\n\t\t");
            out.print( HtmlUtil.escape(adminEmailFromName) );
            out.write("\n\t</dd>\n\n\t");
            //  c:if
            com.liferay.taglib.core.IfTag _jspx_th_c_if_4 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
            _jspx_th_c_if_4.setPageContext(_jspx_page_context);
            _jspx_th_c_if_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_3);
            _jspx_th_c_if_4.setTest( sectionName.equals("password-reset-notification") );
            int _jspx_eval_c_if_4 = _jspx_th_c_if_4.doStartTag();
            if (_jspx_eval_c_if_4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\t\t<dt>\n\t\t\t[$PASSWORD_RESET_URL$]\n\t\t</dt>\n\t\t<dd>\n\t\t\t");
              if (_jspx_meth_liferay$1ui_message_13((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_4, _jspx_page_context))
                return;
              out.write("\n\t\t</dd>\n\t");
            }
            if (_jspx_th_c_if_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_4);
              return;
            }
            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_4);
            out.write("\n\n\t<dt>\n\t\t[$PORTAL_URL$]\n\t</dt>\n\t<dd>\n\t\t");
            out.print( company.getPortalURL(GroupConstants.DEFAULT_PARENT_GROUP_ID) );
            out.write("\n\t</dd>\n\t<dt class=\"password-changed-notification\">\n\t\t[$REMOTE_ADDRESS$]\n\t</dt>\n\t<dd class=\"password-changed-notification\">\n\t\t");
            if (_jspx_meth_liferay$1ui_message_14((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_fieldset_3, _jspx_page_context))
              return;
            out.write("\n\t</dd>\n\t<dt class=\"password-changed-notification\">\n\t\t[$REMOTE_HOST$]\n\t</dt>\n\t<dd class=\"password-changed-notification\">\n\t\t");
            if (_jspx_meth_liferay$1ui_message_15((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_fieldset_3, _jspx_page_context))
              return;
            out.write("\n\t</dd>\n\t<dt>\n\t\t[$TO_ADDRESS$]\n\t</dt>\n\t<dd>\n\t\t");
            if (_jspx_meth_liferay$1ui_message_16((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_fieldset_3, _jspx_page_context))
              return;
            out.write("\n\t</dd>\n\t<dt>\n\t\t[$TO_NAME$]\n\t</dt>\n\t<dd>\n\t\t");
            if (_jspx_meth_liferay$1ui_message_17((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_fieldset_3, _jspx_page_context))
              return;
            out.write("\n\t</dd>\n\t<dt>\n\t\t[$USER_ID$]\n\t</dt>\n\t<dd>\n\t\t");
            if (_jspx_meth_liferay$1ui_message_18((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_fieldset_3, _jspx_page_context))
              return;
            out.write("\n\t</dd>\n\n\t");
            //  c:if
            com.liferay.taglib.core.IfTag _jspx_th_c_if_5 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
            _jspx_th_c_if_5.setPageContext(_jspx_page_context);
            _jspx_th_c_if_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_3);
            _jspx_th_c_if_5.setTest( !(sectionName.equals("email-verification-notification") || sectionName.equals("password-reset-notification")) );
            int _jspx_eval_c_if_5 = _jspx_th_c_if_5.doStartTag();
            if (_jspx_eval_c_if_5 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\t\t<dt>\n\t\t\t[$USER_PASSWORD$]\n\t\t</dt>\n\t\t<dd>\n\t\t\t");
              if (_jspx_meth_liferay$1ui_message_19((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_5, _jspx_page_context))
                return;
              out.write("\n\t\t</dd>\n\t");
            }
            if (_jspx_th_c_if_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_5);
              return;
            }
            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_5);
            out.write("\n\n\t<dt>\n\t\t[$USER_SCREENNAME$]\n\t</dt>\n\t<dd>\n\t\t");
            if (_jspx_meth_liferay$1ui_message_20((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_fieldset_3, _jspx_page_context))
              return;
            out.write("\n\t</dd>\n</dl>");
            out.write("\n\t\t");
          }
          if (_jspx_th_aui_fieldset_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_aui_fieldset_label_cssClass.reuse(_jspx_th_aui_fieldset_3);
            return;
          }
          _jspx_tagPool_aui_fieldset_label_cssClass.reuse(_jspx_th_aui_fieldset_3);
          out.write('\n');
          out.write('	');
        }
        if (_jspx_th_liferay$1ui_section_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_liferay$1ui_section.reuse(_jspx_th_liferay$1ui_section_2);
          return;
        }
        _jspx_tagPool_liferay$1ui_section.reuse(_jspx_th_liferay$1ui_section_2);
        out.write("\n\n\t");
        //  liferay-ui:section
        com.liferay.taglib.ui.SectionTag _jspx_th_liferay$1ui_section_3 = (com.liferay.taglib.ui.SectionTag) _jspx_tagPool_liferay$1ui_section.get(com.liferay.taglib.ui.SectionTag.class);
        _jspx_th_liferay$1ui_section_3.setPageContext(_jspx_page_context);
        _jspx_th_liferay$1ui_section_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_tabs_0);
        int _jspx_eval_liferay$1ui_section_3 = _jspx_th_liferay$1ui_section_3.doStartTag();
        if (_jspx_eval_liferay$1ui_section_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          java.lang.String sectionParam = null;
          java.lang.String sectionName = null;
          java.lang.Boolean sectionSelected = null;
          java.lang.String sectionScroll = null;
          java.lang.String sectionRedirectParams = null;
          sectionParam = (java.lang.String) _jspx_page_context.findAttribute("sectionParam");
          sectionName = (java.lang.String) _jspx_page_context.findAttribute("sectionName");
          sectionSelected = (java.lang.Boolean) _jspx_page_context.findAttribute("sectionSelected");
          sectionScroll = (java.lang.String) _jspx_page_context.findAttribute("sectionScroll");
          sectionRedirectParams = (java.lang.String) _jspx_page_context.findAttribute("sectionRedirectParams");
          out.write("\n\t\t");
          //  liferay-ui:error
          com.liferay.taglib.ui.ErrorTag _jspx_th_liferay$1ui_error_7 = (com.liferay.taglib.ui.ErrorTag) _jspx_tagPool_liferay$1ui_error_message_key_nobody.get(com.liferay.taglib.ui.ErrorTag.class);
          _jspx_th_liferay$1ui_error_7.setPageContext(_jspx_page_context);
          _jspx_th_liferay$1ui_error_7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_section_3);
          _jspx_th_liferay$1ui_error_7.setKey("emailPasswordSentSubject");
          _jspx_th_liferay$1ui_error_7.setMessage("please-enter-a-valid-subject");
          int _jspx_eval_liferay$1ui_error_7 = _jspx_th_liferay$1ui_error_7.doStartTag();
          if (_jspx_th_liferay$1ui_error_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_liferay$1ui_error_message_key_nobody.reuse(_jspx_th_liferay$1ui_error_7);
            return;
          }
          _jspx_tagPool_liferay$1ui_error_message_key_nobody.reuse(_jspx_th_liferay$1ui_error_7);
          out.write("\n\t\t");
          //  liferay-ui:error
          com.liferay.taglib.ui.ErrorTag _jspx_th_liferay$1ui_error_8 = (com.liferay.taglib.ui.ErrorTag) _jspx_tagPool_liferay$1ui_error_message_key_nobody.get(com.liferay.taglib.ui.ErrorTag.class);
          _jspx_th_liferay$1ui_error_8.setPageContext(_jspx_page_context);
          _jspx_th_liferay$1ui_error_8.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_section_3);
          _jspx_th_liferay$1ui_error_8.setKey("emailPasswordSentBody");
          _jspx_th_liferay$1ui_error_8.setMessage("please-enter-a-valid-body");
          int _jspx_eval_liferay$1ui_error_8 = _jspx_th_liferay$1ui_error_8.doStartTag();
          if (_jspx_th_liferay$1ui_error_8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_liferay$1ui_error_message_key_nobody.reuse(_jspx_th_liferay$1ui_error_8);
            return;
          }
          _jspx_tagPool_liferay$1ui_error_message_key_nobody.reuse(_jspx_th_liferay$1ui_error_8);
          out.write("\n\n\t\t");
          //  liferay-frontend:email-notification-settings
          com.liferay.frontend.taglib.servlet.taglib.EmailNotificationSettingsTag _jspx_th_liferay$1frontend_email$1notification$1settings_3 = (com.liferay.frontend.taglib.servlet.taglib.EmailNotificationSettingsTag) _jspx_tagPool_liferay$1frontend_email$1notification$1settings_showEmailEnabled_fieldPrefix_emailSubject_emailParam_emailBody_nobody.get(com.liferay.frontend.taglib.servlet.taglib.EmailNotificationSettingsTag.class);
          _jspx_th_liferay$1frontend_email$1notification$1settings_3.setPageContext(_jspx_page_context);
          _jspx_th_liferay$1frontend_email$1notification$1settings_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_section_3);
          _jspx_th_liferay$1frontend_email$1notification$1settings_3.setEmailBody( LocalizationUtil.getLocalizationXmlFromPreferences(companyPortletPreferences, renderRequest, "adminEmailPasswordSentBody", "settings", ContentUtil.get(ClassLoaderUtil.getPortalClassLoader(), PropsValues.ADMIN_EMAIL_PASSWORD_SENT_BODY)) );
          _jspx_th_liferay$1frontend_email$1notification$1settings_3.setEmailParam("adminEmailPasswordSent");
          _jspx_th_liferay$1frontend_email$1notification$1settings_3.setEmailSubject( LocalizationUtil.getLocalizationXmlFromPreferences(companyPortletPreferences, renderRequest, "adminEmailPasswordSentSubject", "settings", ContentUtil.get(ClassLoaderUtil.getPortalClassLoader(), PropsValues.ADMIN_EMAIL_PASSWORD_SENT_SUBJECT)) );
          _jspx_th_liferay$1frontend_email$1notification$1settings_3.setFieldPrefix("settings");
          _jspx_th_liferay$1frontend_email$1notification$1settings_3.setShowEmailEnabled( false );
          int _jspx_eval_liferay$1frontend_email$1notification$1settings_3 = _jspx_th_liferay$1frontend_email$1notification$1settings_3.doStartTag();
          if (_jspx_th_liferay$1frontend_email$1notification$1settings_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_liferay$1frontend_email$1notification$1settings_showEmailEnabled_fieldPrefix_emailSubject_emailParam_emailBody_nobody.reuse(_jspx_th_liferay$1frontend_email$1notification$1settings_3);
            return;
          }
          _jspx_tagPool_liferay$1frontend_email$1notification$1settings_showEmailEnabled_fieldPrefix_emailSubject_emailParam_emailBody_nobody.reuse(_jspx_th_liferay$1frontend_email$1notification$1settings_3);
          out.write("\n\n\t\t");
          //  aui:fieldset
          com.liferay.taglib.aui.FieldsetTag _jspx_th_aui_fieldset_4 = (com.liferay.taglib.aui.FieldsetTag) _jspx_tagPool_aui_fieldset_label_cssClass.get(com.liferay.taglib.aui.FieldsetTag.class);
          _jspx_th_aui_fieldset_4.setPageContext(_jspx_page_context);
          _jspx_th_aui_fieldset_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_section_3);
          _jspx_th_aui_fieldset_4.setCssClass("definition-of-terms email-verification terms");
          _jspx_th_aui_fieldset_4.setLabel("definition-of-terms");
          int _jspx_eval_aui_fieldset_4 = _jspx_th_aui_fieldset_4.doStartTag();
          if (_jspx_eval_aui_fieldset_4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t\t");
            out.write("\n\n<dl>\n\t");
            //  c:if
            com.liferay.taglib.core.IfTag _jspx_th_c_if_6 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
            _jspx_th_c_if_6.setPageContext(_jspx_page_context);
            _jspx_th_c_if_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_4);
            _jspx_th_c_if_6.setTest( sectionName.equals("email-verification-notification") );
            int _jspx_eval_c_if_6 = _jspx_th_c_if_6.doStartTag();
            if (_jspx_eval_c_if_6 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\t\t<dt>\n\t\t\t[$EMAIL_VERIFICATION_CODE$]\n\t\t</dt>\n\t\t<dd>\n\t\t\t");
              if (_jspx_meth_liferay$1ui_message_21((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_6, _jspx_page_context))
                return;
              out.write("\n\t\t</dd>\n\t\t<dt>\n\t\t\t[$EMAIL_VERIFICATION_URL$]\n\t\t</dt>\n\t\t<dd>\n\t\t\t");
              if (_jspx_meth_liferay$1ui_message_22((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_6, _jspx_page_context))
                return;
              out.write("\n\t\t</dd>\n\t");
            }
            if (_jspx_th_c_if_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_6);
              return;
            }
            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_6);
            out.write("\n\n\t<dt>\n\t\t[$FROM_ADDRESS$]\n\t</dt>\n\t<dd>\n\t\t");
            out.print( HtmlUtil.escape(adminEmailFromAddress) );
            out.write("\n\t</dd>\n\t<dt>\n\t\t[$FROM_NAME$]\n\t</dt>\n\t<dd>\n\t\t");
            out.print( HtmlUtil.escape(adminEmailFromName) );
            out.write("\n\t</dd>\n\n\t");
            //  c:if
            com.liferay.taglib.core.IfTag _jspx_th_c_if_7 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
            _jspx_th_c_if_7.setPageContext(_jspx_page_context);
            _jspx_th_c_if_7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_4);
            _jspx_th_c_if_7.setTest( sectionName.equals("password-reset-notification") );
            int _jspx_eval_c_if_7 = _jspx_th_c_if_7.doStartTag();
            if (_jspx_eval_c_if_7 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\t\t<dt>\n\t\t\t[$PASSWORD_RESET_URL$]\n\t\t</dt>\n\t\t<dd>\n\t\t\t");
              if (_jspx_meth_liferay$1ui_message_23((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_7, _jspx_page_context))
                return;
              out.write("\n\t\t</dd>\n\t");
            }
            if (_jspx_th_c_if_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_7);
              return;
            }
            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_7);
            out.write("\n\n\t<dt>\n\t\t[$PORTAL_URL$]\n\t</dt>\n\t<dd>\n\t\t");
            out.print( company.getPortalURL(GroupConstants.DEFAULT_PARENT_GROUP_ID) );
            out.write("\n\t</dd>\n\t<dt class=\"password-changed-notification\">\n\t\t[$REMOTE_ADDRESS$]\n\t</dt>\n\t<dd class=\"password-changed-notification\">\n\t\t");
            if (_jspx_meth_liferay$1ui_message_24((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_fieldset_4, _jspx_page_context))
              return;
            out.write("\n\t</dd>\n\t<dt class=\"password-changed-notification\">\n\t\t[$REMOTE_HOST$]\n\t</dt>\n\t<dd class=\"password-changed-notification\">\n\t\t");
            if (_jspx_meth_liferay$1ui_message_25((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_fieldset_4, _jspx_page_context))
              return;
            out.write("\n\t</dd>\n\t<dt>\n\t\t[$TO_ADDRESS$]\n\t</dt>\n\t<dd>\n\t\t");
            if (_jspx_meth_liferay$1ui_message_26((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_fieldset_4, _jspx_page_context))
              return;
            out.write("\n\t</dd>\n\t<dt>\n\t\t[$TO_NAME$]\n\t</dt>\n\t<dd>\n\t\t");
            if (_jspx_meth_liferay$1ui_message_27((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_fieldset_4, _jspx_page_context))
              return;
            out.write("\n\t</dd>\n\t<dt>\n\t\t[$USER_ID$]\n\t</dt>\n\t<dd>\n\t\t");
            if (_jspx_meth_liferay$1ui_message_28((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_fieldset_4, _jspx_page_context))
              return;
            out.write("\n\t</dd>\n\n\t");
            //  c:if
            com.liferay.taglib.core.IfTag _jspx_th_c_if_8 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
            _jspx_th_c_if_8.setPageContext(_jspx_page_context);
            _jspx_th_c_if_8.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_4);
            _jspx_th_c_if_8.setTest( !(sectionName.equals("email-verification-notification") || sectionName.equals("password-reset-notification")) );
            int _jspx_eval_c_if_8 = _jspx_th_c_if_8.doStartTag();
            if (_jspx_eval_c_if_8 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\t\t<dt>\n\t\t\t[$USER_PASSWORD$]\n\t\t</dt>\n\t\t<dd>\n\t\t\t");
              if (_jspx_meth_liferay$1ui_message_29((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_8, _jspx_page_context))
                return;
              out.write("\n\t\t</dd>\n\t");
            }
            if (_jspx_th_c_if_8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_8);
              return;
            }
            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_8);
            out.write("\n\n\t<dt>\n\t\t[$USER_SCREENNAME$]\n\t</dt>\n\t<dd>\n\t\t");
            if (_jspx_meth_liferay$1ui_message_30((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_fieldset_4, _jspx_page_context))
              return;
            out.write("\n\t</dd>\n</dl>");
            out.write("\n\t\t");
          }
          if (_jspx_th_aui_fieldset_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_aui_fieldset_label_cssClass.reuse(_jspx_th_aui_fieldset_4);
            return;
          }
          _jspx_tagPool_aui_fieldset_label_cssClass.reuse(_jspx_th_aui_fieldset_4);
          out.write('\n');
          out.write('	');
        }
        if (_jspx_th_liferay$1ui_section_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_liferay$1ui_section.reuse(_jspx_th_liferay$1ui_section_3);
          return;
        }
        _jspx_tagPool_liferay$1ui_section.reuse(_jspx_th_liferay$1ui_section_3);
        out.write("\n\n\t");
        //  liferay-ui:section
        com.liferay.taglib.ui.SectionTag _jspx_th_liferay$1ui_section_4 = (com.liferay.taglib.ui.SectionTag) _jspx_tagPool_liferay$1ui_section.get(com.liferay.taglib.ui.SectionTag.class);
        _jspx_th_liferay$1ui_section_4.setPageContext(_jspx_page_context);
        _jspx_th_liferay$1ui_section_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_tabs_0);
        int _jspx_eval_liferay$1ui_section_4 = _jspx_th_liferay$1ui_section_4.doStartTag();
        if (_jspx_eval_liferay$1ui_section_4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          java.lang.String sectionParam = null;
          java.lang.String sectionName = null;
          java.lang.Boolean sectionSelected = null;
          java.lang.String sectionScroll = null;
          java.lang.String sectionRedirectParams = null;
          sectionParam = (java.lang.String) _jspx_page_context.findAttribute("sectionParam");
          sectionName = (java.lang.String) _jspx_page_context.findAttribute("sectionName");
          sectionSelected = (java.lang.Boolean) _jspx_page_context.findAttribute("sectionSelected");
          sectionScroll = (java.lang.String) _jspx_page_context.findAttribute("sectionScroll");
          sectionRedirectParams = (java.lang.String) _jspx_page_context.findAttribute("sectionRedirectParams");
          out.write("\n\t\t");
          //  liferay-ui:error
          com.liferay.taglib.ui.ErrorTag _jspx_th_liferay$1ui_error_9 = (com.liferay.taglib.ui.ErrorTag) _jspx_tagPool_liferay$1ui_error_message_key_nobody.get(com.liferay.taglib.ui.ErrorTag.class);
          _jspx_th_liferay$1ui_error_9.setPageContext(_jspx_page_context);
          _jspx_th_liferay$1ui_error_9.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_section_4);
          _jspx_th_liferay$1ui_error_9.setKey("emailPasswordResetSubject");
          _jspx_th_liferay$1ui_error_9.setMessage("please-enter-a-valid-subject");
          int _jspx_eval_liferay$1ui_error_9 = _jspx_th_liferay$1ui_error_9.doStartTag();
          if (_jspx_th_liferay$1ui_error_9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_liferay$1ui_error_message_key_nobody.reuse(_jspx_th_liferay$1ui_error_9);
            return;
          }
          _jspx_tagPool_liferay$1ui_error_message_key_nobody.reuse(_jspx_th_liferay$1ui_error_9);
          out.write("\n\t\t");
          //  liferay-ui:error
          com.liferay.taglib.ui.ErrorTag _jspx_th_liferay$1ui_error_10 = (com.liferay.taglib.ui.ErrorTag) _jspx_tagPool_liferay$1ui_error_message_key_nobody.get(com.liferay.taglib.ui.ErrorTag.class);
          _jspx_th_liferay$1ui_error_10.setPageContext(_jspx_page_context);
          _jspx_th_liferay$1ui_error_10.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_section_4);
          _jspx_th_liferay$1ui_error_10.setKey("emailPasswordResetBody");
          _jspx_th_liferay$1ui_error_10.setMessage("please-enter-a-valid-body");
          int _jspx_eval_liferay$1ui_error_10 = _jspx_th_liferay$1ui_error_10.doStartTag();
          if (_jspx_th_liferay$1ui_error_10.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_liferay$1ui_error_message_key_nobody.reuse(_jspx_th_liferay$1ui_error_10);
            return;
          }
          _jspx_tagPool_liferay$1ui_error_message_key_nobody.reuse(_jspx_th_liferay$1ui_error_10);
          out.write("\n\n\t\t");
          //  liferay-frontend:email-notification-settings
          com.liferay.frontend.taglib.servlet.taglib.EmailNotificationSettingsTag _jspx_th_liferay$1frontend_email$1notification$1settings_4 = (com.liferay.frontend.taglib.servlet.taglib.EmailNotificationSettingsTag) _jspx_tagPool_liferay$1frontend_email$1notification$1settings_showEmailEnabled_fieldPrefix_emailSubject_emailParam_emailBody_nobody.get(com.liferay.frontend.taglib.servlet.taglib.EmailNotificationSettingsTag.class);
          _jspx_th_liferay$1frontend_email$1notification$1settings_4.setPageContext(_jspx_page_context);
          _jspx_th_liferay$1frontend_email$1notification$1settings_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_section_4);
          _jspx_th_liferay$1frontend_email$1notification$1settings_4.setEmailBody( LocalizationUtil.getLocalizationXmlFromPreferences(companyPortletPreferences, renderRequest, "adminEmailPasswordResetBody", "settings", ContentUtil.get(ClassLoaderUtil.getPortalClassLoader(), PropsValues.ADMIN_EMAIL_PASSWORD_RESET_BODY)) );
          _jspx_th_liferay$1frontend_email$1notification$1settings_4.setEmailParam("adminEmailPasswordReset");
          _jspx_th_liferay$1frontend_email$1notification$1settings_4.setEmailSubject( LocalizationUtil.getLocalizationXmlFromPreferences(companyPortletPreferences, renderRequest, "adminEmailPasswordResetSubject", "settings", ContentUtil.get(ClassLoaderUtil.getPortalClassLoader(), PropsValues.ADMIN_EMAIL_PASSWORD_RESET_SUBJECT)) );
          _jspx_th_liferay$1frontend_email$1notification$1settings_4.setFieldPrefix("settings");
          _jspx_th_liferay$1frontend_email$1notification$1settings_4.setShowEmailEnabled( false );
          int _jspx_eval_liferay$1frontend_email$1notification$1settings_4 = _jspx_th_liferay$1frontend_email$1notification$1settings_4.doStartTag();
          if (_jspx_th_liferay$1frontend_email$1notification$1settings_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_liferay$1frontend_email$1notification$1settings_showEmailEnabled_fieldPrefix_emailSubject_emailParam_emailBody_nobody.reuse(_jspx_th_liferay$1frontend_email$1notification$1settings_4);
            return;
          }
          _jspx_tagPool_liferay$1frontend_email$1notification$1settings_showEmailEnabled_fieldPrefix_emailSubject_emailParam_emailBody_nobody.reuse(_jspx_th_liferay$1frontend_email$1notification$1settings_4);
          out.write("\n\n\t\t");
          //  aui:fieldset
          com.liferay.taglib.aui.FieldsetTag _jspx_th_aui_fieldset_5 = (com.liferay.taglib.aui.FieldsetTag) _jspx_tagPool_aui_fieldset_label_cssClass.get(com.liferay.taglib.aui.FieldsetTag.class);
          _jspx_th_aui_fieldset_5.setPageContext(_jspx_page_context);
          _jspx_th_aui_fieldset_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_section_4);
          _jspx_th_aui_fieldset_5.setCssClass("definition-of-terms email-verification terms");
          _jspx_th_aui_fieldset_5.setLabel("definition-of-terms");
          int _jspx_eval_aui_fieldset_5 = _jspx_th_aui_fieldset_5.doStartTag();
          if (_jspx_eval_aui_fieldset_5 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t\t");
            out.write("\n\n<dl>\n\t");
            //  c:if
            com.liferay.taglib.core.IfTag _jspx_th_c_if_9 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
            _jspx_th_c_if_9.setPageContext(_jspx_page_context);
            _jspx_th_c_if_9.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_5);
            _jspx_th_c_if_9.setTest( sectionName.equals("email-verification-notification") );
            int _jspx_eval_c_if_9 = _jspx_th_c_if_9.doStartTag();
            if (_jspx_eval_c_if_9 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\t\t<dt>\n\t\t\t[$EMAIL_VERIFICATION_CODE$]\n\t\t</dt>\n\t\t<dd>\n\t\t\t");
              if (_jspx_meth_liferay$1ui_message_31((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_9, _jspx_page_context))
                return;
              out.write("\n\t\t</dd>\n\t\t<dt>\n\t\t\t[$EMAIL_VERIFICATION_URL$]\n\t\t</dt>\n\t\t<dd>\n\t\t\t");
              if (_jspx_meth_liferay$1ui_message_32((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_9, _jspx_page_context))
                return;
              out.write("\n\t\t</dd>\n\t");
            }
            if (_jspx_th_c_if_9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_9);
              return;
            }
            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_9);
            out.write("\n\n\t<dt>\n\t\t[$FROM_ADDRESS$]\n\t</dt>\n\t<dd>\n\t\t");
            out.print( HtmlUtil.escape(adminEmailFromAddress) );
            out.write("\n\t</dd>\n\t<dt>\n\t\t[$FROM_NAME$]\n\t</dt>\n\t<dd>\n\t\t");
            out.print( HtmlUtil.escape(adminEmailFromName) );
            out.write("\n\t</dd>\n\n\t");
            //  c:if
            com.liferay.taglib.core.IfTag _jspx_th_c_if_10 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
            _jspx_th_c_if_10.setPageContext(_jspx_page_context);
            _jspx_th_c_if_10.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_5);
            _jspx_th_c_if_10.setTest( sectionName.equals("password-reset-notification") );
            int _jspx_eval_c_if_10 = _jspx_th_c_if_10.doStartTag();
            if (_jspx_eval_c_if_10 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\t\t<dt>\n\t\t\t[$PASSWORD_RESET_URL$]\n\t\t</dt>\n\t\t<dd>\n\t\t\t");
              if (_jspx_meth_liferay$1ui_message_33((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_10, _jspx_page_context))
                return;
              out.write("\n\t\t</dd>\n\t");
            }
            if (_jspx_th_c_if_10.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_10);
              return;
            }
            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_10);
            out.write("\n\n\t<dt>\n\t\t[$PORTAL_URL$]\n\t</dt>\n\t<dd>\n\t\t");
            out.print( company.getPortalURL(GroupConstants.DEFAULT_PARENT_GROUP_ID) );
            out.write("\n\t</dd>\n\t<dt class=\"password-changed-notification\">\n\t\t[$REMOTE_ADDRESS$]\n\t</dt>\n\t<dd class=\"password-changed-notification\">\n\t\t");
            if (_jspx_meth_liferay$1ui_message_34((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_fieldset_5, _jspx_page_context))
              return;
            out.write("\n\t</dd>\n\t<dt class=\"password-changed-notification\">\n\t\t[$REMOTE_HOST$]\n\t</dt>\n\t<dd class=\"password-changed-notification\">\n\t\t");
            if (_jspx_meth_liferay$1ui_message_35((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_fieldset_5, _jspx_page_context))
              return;
            out.write("\n\t</dd>\n\t<dt>\n\t\t[$TO_ADDRESS$]\n\t</dt>\n\t<dd>\n\t\t");
            if (_jspx_meth_liferay$1ui_message_36((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_fieldset_5, _jspx_page_context))
              return;
            out.write("\n\t</dd>\n\t<dt>\n\t\t[$TO_NAME$]\n\t</dt>\n\t<dd>\n\t\t");
            if (_jspx_meth_liferay$1ui_message_37((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_fieldset_5, _jspx_page_context))
              return;
            out.write("\n\t</dd>\n\t<dt>\n\t\t[$USER_ID$]\n\t</dt>\n\t<dd>\n\t\t");
            if (_jspx_meth_liferay$1ui_message_38((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_fieldset_5, _jspx_page_context))
              return;
            out.write("\n\t</dd>\n\n\t");
            //  c:if
            com.liferay.taglib.core.IfTag _jspx_th_c_if_11 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
            _jspx_th_c_if_11.setPageContext(_jspx_page_context);
            _jspx_th_c_if_11.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_5);
            _jspx_th_c_if_11.setTest( !(sectionName.equals("email-verification-notification") || sectionName.equals("password-reset-notification")) );
            int _jspx_eval_c_if_11 = _jspx_th_c_if_11.doStartTag();
            if (_jspx_eval_c_if_11 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\t\t<dt>\n\t\t\t[$USER_PASSWORD$]\n\t\t</dt>\n\t\t<dd>\n\t\t\t");
              if (_jspx_meth_liferay$1ui_message_39((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_11, _jspx_page_context))
                return;
              out.write("\n\t\t</dd>\n\t");
            }
            if (_jspx_th_c_if_11.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_11);
              return;
            }
            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_11);
            out.write("\n\n\t<dt>\n\t\t[$USER_SCREENNAME$]\n\t</dt>\n\t<dd>\n\t\t");
            if (_jspx_meth_liferay$1ui_message_40((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_fieldset_5, _jspx_page_context))
              return;
            out.write("\n\t</dd>\n</dl>");
            out.write("\n\t\t");
          }
          if (_jspx_th_aui_fieldset_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_aui_fieldset_label_cssClass.reuse(_jspx_th_aui_fieldset_5);
            return;
          }
          _jspx_tagPool_aui_fieldset_label_cssClass.reuse(_jspx_th_aui_fieldset_5);
          out.write('\n');
          out.write('	');
        }
        if (_jspx_th_liferay$1ui_section_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_liferay$1ui_section.reuse(_jspx_th_liferay$1ui_section_4);
          return;
        }
        _jspx_tagPool_liferay$1ui_section.reuse(_jspx_th_liferay$1ui_section_4);
        out.write('\n');
      }
      if (_jspx_th_liferay$1ui_tabs_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_liferay$1ui_tabs_refresh_names.reuse(_jspx_th_liferay$1ui_tabs_0);
        return;
      }
      _jspx_tagPool_liferay$1ui_tabs_refresh_names.reuse(_jspx_th_liferay$1ui_tabs_0);
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

  private boolean _jspx_meth_liferay$1ui_message_0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_0 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_0.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_0.setParent(null);
    _jspx_th_liferay$1ui_message_0.setKey("email-notifications");
    int _jspx_eval_liferay$1ui_message_0 = _jspx_th_liferay$1ui_message_0.doStartTag();
    if (_jspx_th_liferay$1ui_message_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_0);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_0);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_1(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_1 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_1.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_0);
    _jspx_th_liferay$1ui_message_1.setKey("the-email-verification-code");
    int _jspx_eval_liferay$1ui_message_1 = _jspx_th_liferay$1ui_message_1.doStartTag();
    if (_jspx_th_liferay$1ui_message_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_1);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_1);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_2(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_2 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_2.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_0);
    _jspx_th_liferay$1ui_message_2.setKey("the-email-verification-url");
    int _jspx_eval_liferay$1ui_message_2 = _jspx_th_liferay$1ui_message_2.doStartTag();
    if (_jspx_th_liferay$1ui_message_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_2);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_2);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_3(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_3 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_3.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_1);
    _jspx_th_liferay$1ui_message_3.setKey("the-password-reset-url");
    int _jspx_eval_liferay$1ui_message_3 = _jspx_th_liferay$1ui_message_3.doStartTag();
    if (_jspx_th_liferay$1ui_message_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_3);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_3);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_4(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_fieldset_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_4 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_4.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_2);
    _jspx_th_liferay$1ui_message_4.setKey("the-browser's-remote-address");
    int _jspx_eval_liferay$1ui_message_4 = _jspx_th_liferay$1ui_message_4.doStartTag();
    if (_jspx_th_liferay$1ui_message_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_4);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_4);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_5(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_fieldset_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_5 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_5.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_2);
    _jspx_th_liferay$1ui_message_5.setKey("the-browser's-remote-host");
    int _jspx_eval_liferay$1ui_message_5 = _jspx_th_liferay$1ui_message_5.doStartTag();
    if (_jspx_th_liferay$1ui_message_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_5);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_5);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_6(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_fieldset_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_6 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_6.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_2);
    _jspx_th_liferay$1ui_message_6.setKey("the-address-of-the-email-recipient");
    int _jspx_eval_liferay$1ui_message_6 = _jspx_th_liferay$1ui_message_6.doStartTag();
    if (_jspx_th_liferay$1ui_message_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_6);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_6);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_7(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_fieldset_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_7 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_7.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_2);
    _jspx_th_liferay$1ui_message_7.setKey("the-name-of-the-email-recipient");
    int _jspx_eval_liferay$1ui_message_7 = _jspx_th_liferay$1ui_message_7.doStartTag();
    if (_jspx_th_liferay$1ui_message_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_7);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_7);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_8(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_fieldset_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_8 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_8.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_8.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_2);
    _jspx_th_liferay$1ui_message_8.setKey("the-user-id");
    int _jspx_eval_liferay$1ui_message_8 = _jspx_th_liferay$1ui_message_8.doStartTag();
    if (_jspx_th_liferay$1ui_message_8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_8);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_8);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_9(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_9 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_9.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_9.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_2);
    _jspx_th_liferay$1ui_message_9.setKey("the-user-password");
    int _jspx_eval_liferay$1ui_message_9 = _jspx_th_liferay$1ui_message_9.doStartTag();
    if (_jspx_th_liferay$1ui_message_9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_9);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_9);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_10(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_fieldset_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_10 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_10.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_10.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_2);
    _jspx_th_liferay$1ui_message_10.setKey("the-user-screen-name");
    int _jspx_eval_liferay$1ui_message_10 = _jspx_th_liferay$1ui_message_10.doStartTag();
    if (_jspx_th_liferay$1ui_message_10.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_10);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_10);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_11(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_11 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_11.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_11.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_3);
    _jspx_th_liferay$1ui_message_11.setKey("the-email-verification-code");
    int _jspx_eval_liferay$1ui_message_11 = _jspx_th_liferay$1ui_message_11.doStartTag();
    if (_jspx_th_liferay$1ui_message_11.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_11);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_11);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_12(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_12 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_12.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_12.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_3);
    _jspx_th_liferay$1ui_message_12.setKey("the-email-verification-url");
    int _jspx_eval_liferay$1ui_message_12 = _jspx_th_liferay$1ui_message_12.doStartTag();
    if (_jspx_th_liferay$1ui_message_12.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_12);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_12);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_13(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_13 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_13.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_13.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_4);
    _jspx_th_liferay$1ui_message_13.setKey("the-password-reset-url");
    int _jspx_eval_liferay$1ui_message_13 = _jspx_th_liferay$1ui_message_13.doStartTag();
    if (_jspx_th_liferay$1ui_message_13.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_13);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_13);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_14(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_fieldset_3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_14 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_14.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_14.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_3);
    _jspx_th_liferay$1ui_message_14.setKey("the-browser's-remote-address");
    int _jspx_eval_liferay$1ui_message_14 = _jspx_th_liferay$1ui_message_14.doStartTag();
    if (_jspx_th_liferay$1ui_message_14.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_14);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_14);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_15(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_fieldset_3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_15 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_15.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_15.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_3);
    _jspx_th_liferay$1ui_message_15.setKey("the-browser's-remote-host");
    int _jspx_eval_liferay$1ui_message_15 = _jspx_th_liferay$1ui_message_15.doStartTag();
    if (_jspx_th_liferay$1ui_message_15.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_15);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_15);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_16(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_fieldset_3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_16 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_16.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_16.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_3);
    _jspx_th_liferay$1ui_message_16.setKey("the-address-of-the-email-recipient");
    int _jspx_eval_liferay$1ui_message_16 = _jspx_th_liferay$1ui_message_16.doStartTag();
    if (_jspx_th_liferay$1ui_message_16.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_16);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_16);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_17(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_fieldset_3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_17 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_17.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_17.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_3);
    _jspx_th_liferay$1ui_message_17.setKey("the-name-of-the-email-recipient");
    int _jspx_eval_liferay$1ui_message_17 = _jspx_th_liferay$1ui_message_17.doStartTag();
    if (_jspx_th_liferay$1ui_message_17.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_17);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_17);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_18(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_fieldset_3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_18 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_18.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_18.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_3);
    _jspx_th_liferay$1ui_message_18.setKey("the-user-id");
    int _jspx_eval_liferay$1ui_message_18 = _jspx_th_liferay$1ui_message_18.doStartTag();
    if (_jspx_th_liferay$1ui_message_18.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_18);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_18);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_19(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_5, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_19 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_19.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_19.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_5);
    _jspx_th_liferay$1ui_message_19.setKey("the-user-password");
    int _jspx_eval_liferay$1ui_message_19 = _jspx_th_liferay$1ui_message_19.doStartTag();
    if (_jspx_th_liferay$1ui_message_19.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_19);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_19);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_20(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_fieldset_3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_20 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_20.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_20.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_3);
    _jspx_th_liferay$1ui_message_20.setKey("the-user-screen-name");
    int _jspx_eval_liferay$1ui_message_20 = _jspx_th_liferay$1ui_message_20.doStartTag();
    if (_jspx_th_liferay$1ui_message_20.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_20);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_20);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_21(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_6, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_21 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_21.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_21.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_6);
    _jspx_th_liferay$1ui_message_21.setKey("the-email-verification-code");
    int _jspx_eval_liferay$1ui_message_21 = _jspx_th_liferay$1ui_message_21.doStartTag();
    if (_jspx_th_liferay$1ui_message_21.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_21);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_21);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_22(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_6, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_22 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_22.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_22.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_6);
    _jspx_th_liferay$1ui_message_22.setKey("the-email-verification-url");
    int _jspx_eval_liferay$1ui_message_22 = _jspx_th_liferay$1ui_message_22.doStartTag();
    if (_jspx_th_liferay$1ui_message_22.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_22);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_22);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_23(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_7, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_23 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_23.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_23.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_7);
    _jspx_th_liferay$1ui_message_23.setKey("the-password-reset-url");
    int _jspx_eval_liferay$1ui_message_23 = _jspx_th_liferay$1ui_message_23.doStartTag();
    if (_jspx_th_liferay$1ui_message_23.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_23);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_23);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_24(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_fieldset_4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_24 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_24.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_24.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_4);
    _jspx_th_liferay$1ui_message_24.setKey("the-browser's-remote-address");
    int _jspx_eval_liferay$1ui_message_24 = _jspx_th_liferay$1ui_message_24.doStartTag();
    if (_jspx_th_liferay$1ui_message_24.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_24);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_24);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_25(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_fieldset_4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_25 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_25.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_25.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_4);
    _jspx_th_liferay$1ui_message_25.setKey("the-browser's-remote-host");
    int _jspx_eval_liferay$1ui_message_25 = _jspx_th_liferay$1ui_message_25.doStartTag();
    if (_jspx_th_liferay$1ui_message_25.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_25);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_25);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_26(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_fieldset_4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_26 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_26.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_26.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_4);
    _jspx_th_liferay$1ui_message_26.setKey("the-address-of-the-email-recipient");
    int _jspx_eval_liferay$1ui_message_26 = _jspx_th_liferay$1ui_message_26.doStartTag();
    if (_jspx_th_liferay$1ui_message_26.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_26);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_26);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_27(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_fieldset_4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_27 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_27.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_27.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_4);
    _jspx_th_liferay$1ui_message_27.setKey("the-name-of-the-email-recipient");
    int _jspx_eval_liferay$1ui_message_27 = _jspx_th_liferay$1ui_message_27.doStartTag();
    if (_jspx_th_liferay$1ui_message_27.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_27);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_27);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_28(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_fieldset_4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_28 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_28.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_28.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_4);
    _jspx_th_liferay$1ui_message_28.setKey("the-user-id");
    int _jspx_eval_liferay$1ui_message_28 = _jspx_th_liferay$1ui_message_28.doStartTag();
    if (_jspx_th_liferay$1ui_message_28.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_28);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_28);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_29(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_8, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_29 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_29.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_29.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_8);
    _jspx_th_liferay$1ui_message_29.setKey("the-user-password");
    int _jspx_eval_liferay$1ui_message_29 = _jspx_th_liferay$1ui_message_29.doStartTag();
    if (_jspx_th_liferay$1ui_message_29.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_29);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_29);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_30(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_fieldset_4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_30 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_30.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_30.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_4);
    _jspx_th_liferay$1ui_message_30.setKey("the-user-screen-name");
    int _jspx_eval_liferay$1ui_message_30 = _jspx_th_liferay$1ui_message_30.doStartTag();
    if (_jspx_th_liferay$1ui_message_30.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_30);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_30);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_31(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_9, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_31 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_31.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_31.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_9);
    _jspx_th_liferay$1ui_message_31.setKey("the-email-verification-code");
    int _jspx_eval_liferay$1ui_message_31 = _jspx_th_liferay$1ui_message_31.doStartTag();
    if (_jspx_th_liferay$1ui_message_31.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_31);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_31);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_32(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_9, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_32 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_32.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_32.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_9);
    _jspx_th_liferay$1ui_message_32.setKey("the-email-verification-url");
    int _jspx_eval_liferay$1ui_message_32 = _jspx_th_liferay$1ui_message_32.doStartTag();
    if (_jspx_th_liferay$1ui_message_32.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_32);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_32);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_33(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_10, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_33 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_33.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_33.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_10);
    _jspx_th_liferay$1ui_message_33.setKey("the-password-reset-url");
    int _jspx_eval_liferay$1ui_message_33 = _jspx_th_liferay$1ui_message_33.doStartTag();
    if (_jspx_th_liferay$1ui_message_33.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_33);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_33);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_34(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_fieldset_5, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_34 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_34.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_34.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_5);
    _jspx_th_liferay$1ui_message_34.setKey("the-browser's-remote-address");
    int _jspx_eval_liferay$1ui_message_34 = _jspx_th_liferay$1ui_message_34.doStartTag();
    if (_jspx_th_liferay$1ui_message_34.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_34);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_34);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_35(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_fieldset_5, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_35 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_35.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_35.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_5);
    _jspx_th_liferay$1ui_message_35.setKey("the-browser's-remote-host");
    int _jspx_eval_liferay$1ui_message_35 = _jspx_th_liferay$1ui_message_35.doStartTag();
    if (_jspx_th_liferay$1ui_message_35.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_35);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_35);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_36(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_fieldset_5, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_36 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_36.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_36.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_5);
    _jspx_th_liferay$1ui_message_36.setKey("the-address-of-the-email-recipient");
    int _jspx_eval_liferay$1ui_message_36 = _jspx_th_liferay$1ui_message_36.doStartTag();
    if (_jspx_th_liferay$1ui_message_36.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_36);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_36);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_37(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_fieldset_5, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_37 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_37.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_37.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_5);
    _jspx_th_liferay$1ui_message_37.setKey("the-name-of-the-email-recipient");
    int _jspx_eval_liferay$1ui_message_37 = _jspx_th_liferay$1ui_message_37.doStartTag();
    if (_jspx_th_liferay$1ui_message_37.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_37);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_37);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_38(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_fieldset_5, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_38 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_38.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_38.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_5);
    _jspx_th_liferay$1ui_message_38.setKey("the-user-id");
    int _jspx_eval_liferay$1ui_message_38 = _jspx_th_liferay$1ui_message_38.doStartTag();
    if (_jspx_th_liferay$1ui_message_38.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_38);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_38);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_39(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_11, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_39 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_39.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_39.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_11);
    _jspx_th_liferay$1ui_message_39.setKey("the-user-password");
    int _jspx_eval_liferay$1ui_message_39 = _jspx_th_liferay$1ui_message_39.doStartTag();
    if (_jspx_th_liferay$1ui_message_39.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_39);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_39);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_40(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_fieldset_5, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_40 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_40.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_40.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_5);
    _jspx_th_liferay$1ui_message_40.setKey("the-user-screen-name");
    int _jspx_eval_liferay$1ui_message_40 = _jspx_th_liferay$1ui_message_40.doStartTag();
    if (_jspx_th_liferay$1ui_message_40.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_40);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_40);
    return false;
  }
}
