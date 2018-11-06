package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.liferay.expando.kernel.exception.ColumnNameException;
import com.liferay.expando.kernel.exception.DuplicateColumnNameException;
import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.captcha.CaptchaTextException;
import com.liferay.portal.kernel.module.configuration.ConfigurationProviderUtil;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PrefsParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.web.form.web.configuration.WebFormServiceConfiguration;
import com.liferay.web.form.web.internal.util.WebFormUtil;
import javax.portlet.ActionRequest;

public final class configuration_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(1);
    _jspx_dependants.add("/init.jsp");
  }

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_message_key_arguments_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1portlet_renderURL_var_portletConfiguration_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_param_value_name_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_if_test;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_button_type_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_error_message_key_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_input$1localized_xml_name_cssClass_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_script_use;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1util_include_servletContext_page_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_fieldset;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_input_value_type_name_helpMessage_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1portlet_actionURL_var_portletName;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_input_value_type_name_label_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_namespace_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_message_key_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_input_value_type_name_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_field$1wrapper_label_cssClass;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_fieldset_label_cssClass;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_input_wrapperCssClass_value_name_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_fieldset_cssClass;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_input_wrapperCssClass_value_name_label_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_button_value_onClick_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_error_message_exception_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_form_name_method_action;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1theme_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_input$1localized_xml_type_name_cssClass_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_panel_title_persistState_markupView_id_extended_collapsible;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1portlet_actionURL_var_portletConfiguration_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1portlet_resourceURL_var_portletName;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_input_wrapperCssClass_value_name_label_helpMessage_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_button$1row;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1portlet_renderURL_windowState_var_portletConfiguration;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_panel$1container_persistState_markupView_id_extended;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_liferay$1ui_message_key_arguments_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1portlet_renderURL_var_portletConfiguration_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_param_value_name_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_if_test = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_button_type_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_error_message_key_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_input$1localized_xml_name_cssClass_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_script_use = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1util_include_servletContext_page_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_fieldset = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_input_value_type_name_helpMessage_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1portlet_actionURL_var_portletName = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_input_value_type_name_label_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_namespace_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_message_key_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_input_value_type_name_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_field$1wrapper_label_cssClass = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_fieldset_label_cssClass = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_input_wrapperCssClass_value_name_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_fieldset_cssClass = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_input_wrapperCssClass_value_name_label_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_button_value_onClick_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_error_message_exception_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_form_name_method_action = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1theme_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_input$1localized_xml_type_name_cssClass_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_panel_title_persistState_markupView_id_extended_collapsible = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1portlet_actionURL_var_portletConfiguration_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1portlet_resourceURL_var_portletName = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_input_wrapperCssClass_value_name_label_helpMessage_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_button$1row = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1portlet_renderURL_windowState_var_portletConfiguration = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_panel$1container_persistState_markupView_id_extended = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_liferay$1ui_message_key_arguments_nobody.release();
    _jspx_tagPool_liferay$1portlet_renderURL_var_portletConfiguration_nobody.release();
    _jspx_tagPool_portlet_param_value_name_nobody.release();
    _jspx_tagPool_c_if_test.release();
    _jspx_tagPool_aui_button_type_nobody.release();
    _jspx_tagPool_liferay$1ui_error_message_key_nobody.release();
    _jspx_tagPool_liferay$1ui_input$1localized_xml_name_cssClass_nobody.release();
    _jspx_tagPool_aui_script_use.release();
    _jspx_tagPool_liferay$1util_include_servletContext_page_nobody.release();
    _jspx_tagPool_aui_fieldset.release();
    _jspx_tagPool_aui_input_value_type_name_helpMessage_nobody.release();
    _jspx_tagPool_liferay$1portlet_actionURL_var_portletName.release();
    _jspx_tagPool_aui_input_value_type_name_label_nobody.release();
    _jspx_tagPool_portlet_namespace_nobody.release();
    _jspx_tagPool_liferay$1ui_message_key_nobody.release();
    _jspx_tagPool_aui_input_value_type_name_nobody.release();
    _jspx_tagPool_aui_field$1wrapper_label_cssClass.release();
    _jspx_tagPool_aui_fieldset_label_cssClass.release();
    _jspx_tagPool_aui_input_wrapperCssClass_value_name_nobody.release();
    _jspx_tagPool_aui_fieldset_cssClass.release();
    _jspx_tagPool_aui_input_wrapperCssClass_value_name_label_nobody.release();
    _jspx_tagPool_aui_button_value_onClick_nobody.release();
    _jspx_tagPool_portlet_defineObjects_nobody.release();
    _jspx_tagPool_liferay$1ui_error_message_exception_nobody.release();
    _jspx_tagPool_aui_form_name_method_action.release();
    _jspx_tagPool_liferay$1theme_defineObjects_nobody.release();
    _jspx_tagPool_liferay$1ui_input$1localized_xml_type_name_cssClass_nobody.release();
    _jspx_tagPool_liferay$1ui_panel_title_persistState_markupView_id_extended_collapsible.release();
    _jspx_tagPool_liferay$1portlet_actionURL_var_portletConfiguration_nobody.release();
    _jspx_tagPool_liferay$1portlet_resourceURL_var_portletName.release();
    _jspx_tagPool_aui_input_wrapperCssClass_value_name_label_helpMessage_nobody.release();
    _jspx_tagPool_aui_button$1row.release();
    _jspx_tagPool_liferay$1portlet_renderURL_windowState_var_portletConfiguration.release();
    _jspx_tagPool_liferay$1ui_panel$1container_persistState_markupView_id_extended.release();
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

String currentURL = PortalUtil.getCurrentURL(request);

String portletResource = ParamUtil.getString(request, "portletResource");

WebFormServiceConfiguration webFormServiceConfiguration = ConfigurationProviderUtil.getCompanyConfiguration(WebFormServiceConfiguration.class, themeDisplay.getCompanyId());

      out.write('\n');
      out.write('\n');

String titleXml = GetterUtil.getString(LocalizationUtil.getLocalizationXmlFromPreferences(portletPreferences, renderRequest, "title"));
String descriptionXml = GetterUtil.getString(LocalizationUtil.getLocalizationXmlFromPreferences(portletPreferences, renderRequest, "description"));
boolean requireCaptcha = GetterUtil.getBoolean(portletPreferences.getValue("requireCaptcha", StringPool.BLANK));
String successURL = portletPreferences.getValue("successURL", StringPool.BLANK);

boolean sendAsEmail = GetterUtil.getBoolean(portletPreferences.getValue("sendAsEmail", StringPool.BLANK));
String emailFromName = GetterUtil.getString(portletPreferences.getValue("emailFromName", webFormServiceConfiguration.emailFromName()));
String emailFromAddress = GetterUtil.getString(portletPreferences.getValue("emailFromAddress", webFormServiceConfiguration.emailFromAddress()));
String emailAddress = portletPreferences.getValue("emailAddress", StringPool.BLANK);
String subject = portletPreferences.getValue("subject", StringPool.BLANK);

boolean saveToDatabase = GetterUtil.getBoolean(portletPreferences.getValue("saveToDatabase", StringPool.BLANK));
String databaseTableName = portletPreferences.getValue("databaseTableName", StringPool.BLANK);

boolean saveToFile = GetterUtil.getBoolean(portletPreferences.getValue("saveToFile", StringPool.BLANK));

boolean fieldsEditingDisabled = false;

if (WebFormUtil.getTableRowsCount(company.getCompanyId(), databaseTableName) > 0) {
	fieldsEditingDisabled = true;
}

      out.write('\n');
      out.write('\n');
      //  liferay-portlet:actionURL
      com.liferay.taglib.portlet.ActionURLTag _jspx_th_liferay$1portlet_actionURL_0 = (com.liferay.taglib.portlet.ActionURLTag) _jspx_tagPool_liferay$1portlet_actionURL_var_portletConfiguration_nobody.get(com.liferay.taglib.portlet.ActionURLTag.class);
      _jspx_th_liferay$1portlet_actionURL_0.setPageContext(_jspx_page_context);
      _jspx_th_liferay$1portlet_actionURL_0.setParent(null);
      _jspx_th_liferay$1portlet_actionURL_0.setPortletConfiguration( true );
      _jspx_th_liferay$1portlet_actionURL_0.setVar("configurationActionURL");
      int _jspx_eval_liferay$1portlet_actionURL_0 = _jspx_th_liferay$1portlet_actionURL_0.doStartTag();
      if (_jspx_th_liferay$1portlet_actionURL_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_liferay$1portlet_actionURL_var_portletConfiguration_nobody.reuse(_jspx_th_liferay$1portlet_actionURL_0);
        return;
      }
      _jspx_tagPool_liferay$1portlet_actionURL_var_portletConfiguration_nobody.reuse(_jspx_th_liferay$1portlet_actionURL_0);
      java.lang.String configurationActionURL = null;
      configurationActionURL = (java.lang.String) _jspx_page_context.findAttribute("configurationActionURL");
      out.write('\n');
      out.write('\n');
      //  liferay-portlet:renderURL
      com.liferay.taglib.portlet.RenderURLTag _jspx_th_liferay$1portlet_renderURL_0 = (com.liferay.taglib.portlet.RenderURLTag) _jspx_tagPool_liferay$1portlet_renderURL_var_portletConfiguration_nobody.get(com.liferay.taglib.portlet.RenderURLTag.class);
      _jspx_th_liferay$1portlet_renderURL_0.setPageContext(_jspx_page_context);
      _jspx_th_liferay$1portlet_renderURL_0.setParent(null);
      _jspx_th_liferay$1portlet_renderURL_0.setPortletConfiguration( true );
      _jspx_th_liferay$1portlet_renderURL_0.setVar("configurationRenderURL");
      int _jspx_eval_liferay$1portlet_renderURL_0 = _jspx_th_liferay$1portlet_renderURL_0.doStartTag();
      if (_jspx_th_liferay$1portlet_renderURL_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_liferay$1portlet_renderURL_var_portletConfiguration_nobody.reuse(_jspx_th_liferay$1portlet_renderURL_0);
        return;
      }
      _jspx_tagPool_liferay$1portlet_renderURL_var_portletConfiguration_nobody.reuse(_jspx_th_liferay$1portlet_renderURL_0);
      java.lang.String configurationRenderURL = null;
      configurationRenderURL = (java.lang.String) _jspx_page_context.findAttribute("configurationRenderURL");
      out.write('\n');
      out.write('\n');
      //  aui:form
      com.liferay.taglib.aui.FormTag _jspx_th_aui_form_0 = (com.liferay.taglib.aui.FormTag) _jspx_tagPool_aui_form_name_method_action.get(com.liferay.taglib.aui.FormTag.class);
      _jspx_th_aui_form_0.setPageContext(_jspx_page_context);
      _jspx_th_aui_form_0.setParent(null);
      _jspx_th_aui_form_0.setAction( configurationActionURL );
      _jspx_th_aui_form_0.setMethod("post");
      _jspx_th_aui_form_0.setName("fm");
      int _jspx_eval_aui_form_0 = _jspx_th_aui_form_0.doStartTag();
      if (_jspx_eval_aui_form_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        out.write('\n');
        out.write('	');
        //  aui:input
        com.liferay.taglib.aui.InputTag _jspx_th_aui_input_0 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
        _jspx_th_aui_input_0.setPageContext(_jspx_page_context);
        _jspx_th_aui_input_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
        _jspx_th_aui_input_0.setName( Constants.CMD );
        _jspx_th_aui_input_0.setType("hidden");
        _jspx_th_aui_input_0.setValue( Constants.UPDATE );
        int _jspx_eval_aui_input_0 = _jspx_th_aui_input_0.doStartTag();
        if (_jspx_th_aui_input_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_0);
          return;
        }
        _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_0);
        out.write('\n');
        out.write('	');
        //  aui:input
        com.liferay.taglib.aui.InputTag _jspx_th_aui_input_1 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
        _jspx_th_aui_input_1.setPageContext(_jspx_page_context);
        _jspx_th_aui_input_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
        _jspx_th_aui_input_1.setName("redirect");
        _jspx_th_aui_input_1.setType("hidden");
        _jspx_th_aui_input_1.setValue( configurationRenderURL );
        int _jspx_eval_aui_input_1 = _jspx_th_aui_input_1.doStartTag();
        if (_jspx_th_aui_input_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_1);
          return;
        }
        _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_1);
        out.write("\n\n\t<div class=\"portlet-configuration-body-content\">\n\t\t<div class=\"container-fluid-1280\">\n\t\t\t");
        //  liferay-ui:error
        com.liferay.taglib.ui.ErrorTag _jspx_th_liferay$1ui_error_0 = (com.liferay.taglib.ui.ErrorTag) _jspx_tagPool_liferay$1ui_error_message_exception_nobody.get(com.liferay.taglib.ui.ErrorTag.class);
        _jspx_th_liferay$1ui_error_0.setPageContext(_jspx_page_context);
        _jspx_th_liferay$1ui_error_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
        _jspx_th_liferay$1ui_error_0.setException( ColumnNameException.class );
        _jspx_th_liferay$1ui_error_0.setMessage("please-enter-valid-field-names");
        int _jspx_eval_liferay$1ui_error_0 = _jspx_th_liferay$1ui_error_0.doStartTag();
        if (_jspx_th_liferay$1ui_error_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_liferay$1ui_error_message_exception_nobody.reuse(_jspx_th_liferay$1ui_error_0);
          return;
        }
        _jspx_tagPool_liferay$1ui_error_message_exception_nobody.reuse(_jspx_th_liferay$1ui_error_0);
        out.write("\n\t\t\t");
        //  liferay-ui:error
        com.liferay.taglib.ui.ErrorTag _jspx_th_liferay$1ui_error_1 = (com.liferay.taglib.ui.ErrorTag) _jspx_tagPool_liferay$1ui_error_message_exception_nobody.get(com.liferay.taglib.ui.ErrorTag.class);
        _jspx_th_liferay$1ui_error_1.setPageContext(_jspx_page_context);
        _jspx_th_liferay$1ui_error_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
        _jspx_th_liferay$1ui_error_1.setException( DuplicateColumnNameException.class );
        _jspx_th_liferay$1ui_error_1.setMessage("please-enter-unique-field-names");
        int _jspx_eval_liferay$1ui_error_1 = _jspx_th_liferay$1ui_error_1.doStartTag();
        if (_jspx_th_liferay$1ui_error_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_liferay$1ui_error_message_exception_nobody.reuse(_jspx_th_liferay$1ui_error_1);
          return;
        }
        _jspx_tagPool_liferay$1ui_error_message_exception_nobody.reuse(_jspx_th_liferay$1ui_error_1);
        out.write("\n\n\t\t\t");
        //  liferay-ui:panel-container
        com.liferay.taglib.ui.PanelContainerTag _jspx_th_liferay$1ui_panel$1container_0 = (com.liferay.taglib.ui.PanelContainerTag) _jspx_tagPool_liferay$1ui_panel$1container_persistState_markupView_id_extended.get(com.liferay.taglib.ui.PanelContainerTag.class);
        _jspx_th_liferay$1ui_panel$1container_0.setPageContext(_jspx_page_context);
        _jspx_th_liferay$1ui_panel$1container_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
        _jspx_th_liferay$1ui_panel$1container_0.setExtended( true );
        _jspx_th_liferay$1ui_panel$1container_0.setId("webFormConfiguration");
        _jspx_th_liferay$1ui_panel$1container_0.setMarkupView("lexicon");
        _jspx_th_liferay$1ui_panel$1container_0.setPersistState( true );
        int _jspx_eval_liferay$1ui_panel$1container_0 = _jspx_th_liferay$1ui_panel$1container_0.doStartTag();
        if (_jspx_eval_liferay$1ui_panel$1container_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          if (_jspx_eval_liferay$1ui_panel$1container_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
            out = _jspx_page_context.pushBody();
            _jspx_th_liferay$1ui_panel$1container_0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
            _jspx_th_liferay$1ui_panel$1container_0.doInitBody();
          }
          do {
            out.write("\n\t\t\t\t");
            //  liferay-ui:panel
            com.liferay.taglib.ui.PanelTag _jspx_th_liferay$1ui_panel_0 = (com.liferay.taglib.ui.PanelTag) _jspx_tagPool_liferay$1ui_panel_title_persistState_markupView_id_extended_collapsible.get(com.liferay.taglib.ui.PanelTag.class);
            _jspx_th_liferay$1ui_panel_0.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1ui_panel_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_panel$1container_0);
            _jspx_th_liferay$1ui_panel_0.setCollapsible( true );
            _jspx_th_liferay$1ui_panel_0.setExtended( true );
            _jspx_th_liferay$1ui_panel_0.setId("webFormGeneral");
            _jspx_th_liferay$1ui_panel_0.setMarkupView("lexicon");
            _jspx_th_liferay$1ui_panel_0.setPersistState( true );
            _jspx_th_liferay$1ui_panel_0.setTitle("form-information");
            int _jspx_eval_liferay$1ui_panel_0 = _jspx_th_liferay$1ui_panel_0.doStartTag();
            if (_jspx_eval_liferay$1ui_panel_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\t\t\t\t\t");
              //  aui:fieldset
              com.liferay.taglib.aui.FieldsetTag _jspx_th_aui_fieldset_0 = (com.liferay.taglib.aui.FieldsetTag) _jspx_tagPool_aui_fieldset.get(com.liferay.taglib.aui.FieldsetTag.class);
              _jspx_th_aui_fieldset_0.setPageContext(_jspx_page_context);
              _jspx_th_aui_fieldset_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_panel_0);
              int _jspx_eval_aui_fieldset_0 = _jspx_th_aui_fieldset_0.doStartTag();
              if (_jspx_eval_aui_fieldset_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\t\t\t\t\t\t");
                //  liferay-ui:error
                com.liferay.taglib.ui.ErrorTag _jspx_th_liferay$1ui_error_2 = (com.liferay.taglib.ui.ErrorTag) _jspx_tagPool_liferay$1ui_error_message_key_nobody.get(com.liferay.taglib.ui.ErrorTag.class);
                _jspx_th_liferay$1ui_error_2.setPageContext(_jspx_page_context);
                _jspx_th_liferay$1ui_error_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_0);
                _jspx_th_liferay$1ui_error_2.setKey("successURLInvalid");
                _jspx_th_liferay$1ui_error_2.setMessage("please-enter-a-valid-url");
                int _jspx_eval_liferay$1ui_error_2 = _jspx_th_liferay$1ui_error_2.doStartTag();
                if (_jspx_th_liferay$1ui_error_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_liferay$1ui_error_message_key_nobody.reuse(_jspx_th_liferay$1ui_error_2);
                  return;
                }
                _jspx_tagPool_liferay$1ui_error_message_key_nobody.reuse(_jspx_th_liferay$1ui_error_2);
                out.write("\n\n\t\t\t\t\t\t");
                //  aui:field-wrapper
                com.liferay.taglib.aui.FieldWrapperTag _jspx_th_aui_field$1wrapper_0 = (com.liferay.taglib.aui.FieldWrapperTag) _jspx_tagPool_aui_field$1wrapper_label_cssClass.get(com.liferay.taglib.aui.FieldWrapperTag.class);
                _jspx_th_aui_field$1wrapper_0.setPageContext(_jspx_page_context);
                _jspx_th_aui_field$1wrapper_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_0);
                _jspx_th_aui_field$1wrapper_0.setCssClass("lfr-input-text-container");
                _jspx_th_aui_field$1wrapper_0.setLabel("title");
                int _jspx_eval_aui_field$1wrapper_0 = _jspx_th_aui_field$1wrapper_0.doStartTag();
                if (_jspx_eval_aui_field$1wrapper_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  out.write("\n\t\t\t\t\t\t\t");
                  //  liferay-ui:input-localized
                  com.liferay.taglib.ui.InputLocalizedTag _jspx_th_liferay$1ui_input$1localized_0 = (com.liferay.taglib.ui.InputLocalizedTag) _jspx_tagPool_liferay$1ui_input$1localized_xml_name_cssClass_nobody.get(com.liferay.taglib.ui.InputLocalizedTag.class);
                  _jspx_th_liferay$1ui_input$1localized_0.setPageContext(_jspx_page_context);
                  _jspx_th_liferay$1ui_input$1localized_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_field$1wrapper_0);
                  _jspx_th_liferay$1ui_input$1localized_0.setCssClass("lfr-input-text");
                  _jspx_th_liferay$1ui_input$1localized_0.setName("title");
                  _jspx_th_liferay$1ui_input$1localized_0.setXml( titleXml );
                  int _jspx_eval_liferay$1ui_input$1localized_0 = _jspx_th_liferay$1ui_input$1localized_0.doStartTag();
                  if (_jspx_th_liferay$1ui_input$1localized_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_liferay$1ui_input$1localized_xml_name_cssClass_nobody.reuse(_jspx_th_liferay$1ui_input$1localized_0);
                    return;
                  }
                  _jspx_tagPool_liferay$1ui_input$1localized_xml_name_cssClass_nobody.reuse(_jspx_th_liferay$1ui_input$1localized_0);
                  out.write("\n\t\t\t\t\t\t");
                }
                if (_jspx_th_aui_field$1wrapper_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_aui_field$1wrapper_label_cssClass.reuse(_jspx_th_aui_field$1wrapper_0);
                  return;
                }
                _jspx_tagPool_aui_field$1wrapper_label_cssClass.reuse(_jspx_th_aui_field$1wrapper_0);
                out.write("\n\n\t\t\t\t\t\t");
                //  aui:field-wrapper
                com.liferay.taglib.aui.FieldWrapperTag _jspx_th_aui_field$1wrapper_1 = (com.liferay.taglib.aui.FieldWrapperTag) _jspx_tagPool_aui_field$1wrapper_label_cssClass.get(com.liferay.taglib.aui.FieldWrapperTag.class);
                _jspx_th_aui_field$1wrapper_1.setPageContext(_jspx_page_context);
                _jspx_th_aui_field$1wrapper_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_0);
                _jspx_th_aui_field$1wrapper_1.setCssClass("lfr-textarea-container");
                _jspx_th_aui_field$1wrapper_1.setLabel("description");
                int _jspx_eval_aui_field$1wrapper_1 = _jspx_th_aui_field$1wrapper_1.doStartTag();
                if (_jspx_eval_aui_field$1wrapper_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  out.write("\n\t\t\t\t\t\t\t");
                  //  liferay-ui:input-localized
                  com.liferay.taglib.ui.InputLocalizedTag _jspx_th_liferay$1ui_input$1localized_1 = (com.liferay.taglib.ui.InputLocalizedTag) _jspx_tagPool_liferay$1ui_input$1localized_xml_type_name_cssClass_nobody.get(com.liferay.taglib.ui.InputLocalizedTag.class);
                  _jspx_th_liferay$1ui_input$1localized_1.setPageContext(_jspx_page_context);
                  _jspx_th_liferay$1ui_input$1localized_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_field$1wrapper_1);
                  _jspx_th_liferay$1ui_input$1localized_1.setCssClass("lfr-input-text");
                  _jspx_th_liferay$1ui_input$1localized_1.setName("description");
                  _jspx_th_liferay$1ui_input$1localized_1.setType("textarea");
                  _jspx_th_liferay$1ui_input$1localized_1.setXml( descriptionXml );
                  int _jspx_eval_liferay$1ui_input$1localized_1 = _jspx_th_liferay$1ui_input$1localized_1.doStartTag();
                  if (_jspx_th_liferay$1ui_input$1localized_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_liferay$1ui_input$1localized_xml_type_name_cssClass_nobody.reuse(_jspx_th_liferay$1ui_input$1localized_1);
                    return;
                  }
                  _jspx_tagPool_liferay$1ui_input$1localized_xml_type_name_cssClass_nobody.reuse(_jspx_th_liferay$1ui_input$1localized_1);
                  out.write("\n\t\t\t\t\t\t");
                }
                if (_jspx_th_aui_field$1wrapper_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_aui_field$1wrapper_label_cssClass.reuse(_jspx_th_aui_field$1wrapper_1);
                  return;
                }
                _jspx_tagPool_aui_field$1wrapper_label_cssClass.reuse(_jspx_th_aui_field$1wrapper_1);
                out.write("\n\n\t\t\t\t\t\t");
                //  aui:input
                com.liferay.taglib.aui.InputTag _jspx_th_aui_input_2 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
                _jspx_th_aui_input_2.setPageContext(_jspx_page_context);
                _jspx_th_aui_input_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_0);
                _jspx_th_aui_input_2.setName("preferences--requireCaptcha--");
                _jspx_th_aui_input_2.setType("checkbox");
                _jspx_th_aui_input_2.setValue( requireCaptcha );
                int _jspx_eval_aui_input_2 = _jspx_th_aui_input_2.doStartTag();
                if (_jspx_th_aui_input_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_2);
                  return;
                }
                _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_2);
                out.write("\n\n\t\t\t\t\t\t");
                //  aui:input
                com.liferay.taglib.aui.InputTag _jspx_th_aui_input_3 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_wrapperCssClass_value_name_label_nobody.get(com.liferay.taglib.aui.InputTag.class);
                _jspx_th_aui_input_3.setPageContext(_jspx_page_context);
                _jspx_th_aui_input_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_0);
                _jspx_th_aui_input_3.setLabel("redirect-url-on-success");
                _jspx_th_aui_input_3.setName("preferences--successURL--");
                _jspx_th_aui_input_3.setValue( HtmlUtil.toInputSafe(successURL) );
                _jspx_th_aui_input_3.setWrapperCssClass("lfr-input-text-container");
                int _jspx_eval_aui_input_3 = _jspx_th_aui_input_3.doStartTag();
                if (_jspx_th_aui_input_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_aui_input_wrapperCssClass_value_name_label_nobody.reuse(_jspx_th_aui_input_3);
                  return;
                }
                _jspx_tagPool_aui_input_wrapperCssClass_value_name_label_nobody.reuse(_jspx_th_aui_input_3);
                out.write("\n\t\t\t\t\t");
              }
              if (_jspx_th_aui_fieldset_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_aui_fieldset.reuse(_jspx_th_aui_fieldset_0);
                return;
              }
              _jspx_tagPool_aui_fieldset.reuse(_jspx_th_aui_fieldset_0);
              out.write("\n\t\t\t\t");
            }
            if (_jspx_th_liferay$1ui_panel_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_liferay$1ui_panel_title_persistState_markupView_id_extended_collapsible.reuse(_jspx_th_liferay$1ui_panel_0);
              return;
            }
            _jspx_tagPool_liferay$1ui_panel_title_persistState_markupView_id_extended_collapsible.reuse(_jspx_th_liferay$1ui_panel_0);
            out.write("\n\n\t\t\t\t");
            //  liferay-ui:panel
            com.liferay.taglib.ui.PanelTag _jspx_th_liferay$1ui_panel_1 = (com.liferay.taglib.ui.PanelTag) _jspx_tagPool_liferay$1ui_panel_title_persistState_markupView_id_extended_collapsible.get(com.liferay.taglib.ui.PanelTag.class);
            _jspx_th_liferay$1ui_panel_1.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1ui_panel_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_panel$1container_0);
            _jspx_th_liferay$1ui_panel_1.setCollapsible( true );
            _jspx_th_liferay$1ui_panel_1.setExtended( true );
            _jspx_th_liferay$1ui_panel_1.setId("webFormData");
            _jspx_th_liferay$1ui_panel_1.setMarkupView("lexicon");
            _jspx_th_liferay$1ui_panel_1.setPersistState( true );
            _jspx_th_liferay$1ui_panel_1.setTitle("handling-of-form-data");
            int _jspx_eval_liferay$1ui_panel_1 = _jspx_th_liferay$1ui_panel_1.doStartTag();
            if (_jspx_eval_liferay$1ui_panel_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\t\t\t\t\t");
              //  aui:fieldset
              com.liferay.taglib.aui.FieldsetTag _jspx_th_aui_fieldset_1 = (com.liferay.taglib.aui.FieldsetTag) _jspx_tagPool_aui_fieldset_label_cssClass.get(com.liferay.taglib.aui.FieldsetTag.class);
              _jspx_th_aui_fieldset_1.setPageContext(_jspx_page_context);
              _jspx_th_aui_fieldset_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_panel_1);
              _jspx_th_aui_fieldset_1.setCssClass("handle-data");
              _jspx_th_aui_fieldset_1.setLabel("email");
              int _jspx_eval_aui_fieldset_1 = _jspx_th_aui_fieldset_1.doStartTag();
              if (_jspx_eval_aui_fieldset_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\t\t\t\t\t\t");
                //  liferay-ui:error
                com.liferay.taglib.ui.ErrorTag _jspx_th_liferay$1ui_error_3 = (com.liferay.taglib.ui.ErrorTag) _jspx_tagPool_liferay$1ui_error_message_key_nobody.get(com.liferay.taglib.ui.ErrorTag.class);
                _jspx_th_liferay$1ui_error_3.setPageContext(_jspx_page_context);
                _jspx_th_liferay$1ui_error_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_1);
                _jspx_th_liferay$1ui_error_3.setKey("emailAddressInvalid");
                _jspx_th_liferay$1ui_error_3.setMessage("please-enter-a-valid-email-address");
                int _jspx_eval_liferay$1ui_error_3 = _jspx_th_liferay$1ui_error_3.doStartTag();
                if (_jspx_th_liferay$1ui_error_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_liferay$1ui_error_message_key_nobody.reuse(_jspx_th_liferay$1ui_error_3);
                  return;
                }
                _jspx_tagPool_liferay$1ui_error_message_key_nobody.reuse(_jspx_th_liferay$1ui_error_3);
                out.write("\n\t\t\t\t\t\t");
                //  liferay-ui:error
                com.liferay.taglib.ui.ErrorTag _jspx_th_liferay$1ui_error_4 = (com.liferay.taglib.ui.ErrorTag) _jspx_tagPool_liferay$1ui_error_message_key_nobody.get(com.liferay.taglib.ui.ErrorTag.class);
                _jspx_th_liferay$1ui_error_4.setPageContext(_jspx_page_context);
                _jspx_th_liferay$1ui_error_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_1);
                _jspx_th_liferay$1ui_error_4.setKey("emailAddressRequired");
                _jspx_th_liferay$1ui_error_4.setMessage("please-enter-an-email-address");
                int _jspx_eval_liferay$1ui_error_4 = _jspx_th_liferay$1ui_error_4.doStartTag();
                if (_jspx_th_liferay$1ui_error_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_liferay$1ui_error_message_key_nobody.reuse(_jspx_th_liferay$1ui_error_4);
                  return;
                }
                _jspx_tagPool_liferay$1ui_error_message_key_nobody.reuse(_jspx_th_liferay$1ui_error_4);
                out.write("\n\t\t\t\t\t\t");
                //  liferay-ui:error
                com.liferay.taglib.ui.ErrorTag _jspx_th_liferay$1ui_error_5 = (com.liferay.taglib.ui.ErrorTag) _jspx_tagPool_liferay$1ui_error_message_key_nobody.get(com.liferay.taglib.ui.ErrorTag.class);
                _jspx_th_liferay$1ui_error_5.setPageContext(_jspx_page_context);
                _jspx_th_liferay$1ui_error_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_1);
                _jspx_th_liferay$1ui_error_5.setKey("handlingRequired");
                _jspx_th_liferay$1ui_error_5.setMessage("please-select-an-action-for-the-handling-of-form-data");
                int _jspx_eval_liferay$1ui_error_5 = _jspx_th_liferay$1ui_error_5.doStartTag();
                if (_jspx_th_liferay$1ui_error_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_liferay$1ui_error_message_key_nobody.reuse(_jspx_th_liferay$1ui_error_5);
                  return;
                }
                _jspx_tagPool_liferay$1ui_error_message_key_nobody.reuse(_jspx_th_liferay$1ui_error_5);
                out.write("\n\t\t\t\t\t\t");
                //  liferay-ui:error
                com.liferay.taglib.ui.ErrorTag _jspx_th_liferay$1ui_error_6 = (com.liferay.taglib.ui.ErrorTag) _jspx_tagPool_liferay$1ui_error_message_key_nobody.get(com.liferay.taglib.ui.ErrorTag.class);
                _jspx_th_liferay$1ui_error_6.setPageContext(_jspx_page_context);
                _jspx_th_liferay$1ui_error_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_1);
                _jspx_th_liferay$1ui_error_6.setKey("subjectRequired");
                _jspx_th_liferay$1ui_error_6.setMessage("please-enter-a-subject");
                int _jspx_eval_liferay$1ui_error_6 = _jspx_th_liferay$1ui_error_6.doStartTag();
                if (_jspx_th_liferay$1ui_error_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_liferay$1ui_error_message_key_nobody.reuse(_jspx_th_liferay$1ui_error_6);
                  return;
                }
                _jspx_tagPool_liferay$1ui_error_message_key_nobody.reuse(_jspx_th_liferay$1ui_error_6);
                out.write("\n\n\t\t\t\t\t\t");
                //  aui:input
                com.liferay.taglib.aui.InputTag _jspx_th_aui_input_4 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_label_nobody.get(com.liferay.taglib.aui.InputTag.class);
                _jspx_th_aui_input_4.setPageContext(_jspx_page_context);
                _jspx_th_aui_input_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_1);
                _jspx_th_aui_input_4.setLabel("send-as-email");
                _jspx_th_aui_input_4.setName("preferences--sendAsEmail--");
                _jspx_th_aui_input_4.setType("checkbox");
                _jspx_th_aui_input_4.setValue( sendAsEmail );
                int _jspx_eval_aui_input_4 = _jspx_th_aui_input_4.doStartTag();
                if (_jspx_th_aui_input_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_aui_input_value_type_name_label_nobody.reuse(_jspx_th_aui_input_4);
                  return;
                }
                _jspx_tagPool_aui_input_value_type_name_label_nobody.reuse(_jspx_th_aui_input_4);
                out.write("\n\n\t\t\t\t\t\t");
                //  aui:fieldset
                com.liferay.taglib.aui.FieldsetTag _jspx_th_aui_fieldset_2 = (com.liferay.taglib.aui.FieldsetTag) _jspx_tagPool_aui_fieldset.get(com.liferay.taglib.aui.FieldsetTag.class);
                _jspx_th_aui_fieldset_2.setPageContext(_jspx_page_context);
                _jspx_th_aui_fieldset_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_1);
                int _jspx_eval_aui_fieldset_2 = _jspx_th_aui_fieldset_2.doStartTag();
                if (_jspx_eval_aui_fieldset_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  out.write("\n\t\t\t\t\t\t\t");
                  //  aui:input
                  com.liferay.taglib.aui.InputTag _jspx_th_aui_input_5 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_wrapperCssClass_value_name_label_nobody.get(com.liferay.taglib.aui.InputTag.class);
                  _jspx_th_aui_input_5.setPageContext(_jspx_page_context);
                  _jspx_th_aui_input_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_2);
                  _jspx_th_aui_input_5.setLabel("name-from");
                  _jspx_th_aui_input_5.setName("preferences--emailFromName--");
                  _jspx_th_aui_input_5.setValue( emailFromName );
                  _jspx_th_aui_input_5.setWrapperCssClass("lfr-input-text-container");
                  int _jspx_eval_aui_input_5 = _jspx_th_aui_input_5.doStartTag();
                  if (_jspx_th_aui_input_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_aui_input_wrapperCssClass_value_name_label_nobody.reuse(_jspx_th_aui_input_5);
                    return;
                  }
                  _jspx_tagPool_aui_input_wrapperCssClass_value_name_label_nobody.reuse(_jspx_th_aui_input_5);
                  out.write("\n\n\t\t\t\t\t\t\t");
                  //  aui:input
                  com.liferay.taglib.aui.InputTag _jspx_th_aui_input_6 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_wrapperCssClass_value_name_label_nobody.get(com.liferay.taglib.aui.InputTag.class);
                  _jspx_th_aui_input_6.setPageContext(_jspx_page_context);
                  _jspx_th_aui_input_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_2);
                  _jspx_th_aui_input_6.setLabel("address-from");
                  _jspx_th_aui_input_6.setName("preferences--emailFromAddress--");
                  _jspx_th_aui_input_6.setValue( emailFromAddress );
                  _jspx_th_aui_input_6.setWrapperCssClass("lfr-input-text-container");
                  int _jspx_eval_aui_input_6 = _jspx_th_aui_input_6.doStartTag();
                  if (_jspx_th_aui_input_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_aui_input_wrapperCssClass_value_name_label_nobody.reuse(_jspx_th_aui_input_6);
                    return;
                  }
                  _jspx_tagPool_aui_input_wrapperCssClass_value_name_label_nobody.reuse(_jspx_th_aui_input_6);
                  out.write("\n\t\t\t\t\t\t");
                }
                if (_jspx_th_aui_fieldset_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_aui_fieldset.reuse(_jspx_th_aui_fieldset_2);
                  return;
                }
                _jspx_tagPool_aui_fieldset.reuse(_jspx_th_aui_fieldset_2);
                out.write("\n\n\t\t\t\t\t\t");
                //  aui:input
                com.liferay.taglib.aui.InputTag _jspx_th_aui_input_7 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_wrapperCssClass_value_name_label_helpMessage_nobody.get(com.liferay.taglib.aui.InputTag.class);
                _jspx_th_aui_input_7.setPageContext(_jspx_page_context);
                _jspx_th_aui_input_7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_1);
                _jspx_th_aui_input_7.setHelpMessage("add-email-addresses-separated-by-commas");
                _jspx_th_aui_input_7.setLabel("addresses-to");
                _jspx_th_aui_input_7.setName("preferences--emailAddress--");
                _jspx_th_aui_input_7.setValue( emailAddress );
                _jspx_th_aui_input_7.setWrapperCssClass("lfr-input-text-container");
                int _jspx_eval_aui_input_7 = _jspx_th_aui_input_7.doStartTag();
                if (_jspx_th_aui_input_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_aui_input_wrapperCssClass_value_name_label_helpMessage_nobody.reuse(_jspx_th_aui_input_7);
                  return;
                }
                _jspx_tagPool_aui_input_wrapperCssClass_value_name_label_helpMessage_nobody.reuse(_jspx_th_aui_input_7);
                out.write("\n\n\t\t\t\t\t\t");
                //  aui:input
                com.liferay.taglib.aui.InputTag _jspx_th_aui_input_8 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_wrapperCssClass_value_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
                _jspx_th_aui_input_8.setPageContext(_jspx_page_context);
                _jspx_th_aui_input_8.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_1);
                _jspx_th_aui_input_8.setName("preferences--subject--");
                _jspx_th_aui_input_8.setValue( subject );
                _jspx_th_aui_input_8.setWrapperCssClass("lfr-input-text-container");
                int _jspx_eval_aui_input_8 = _jspx_th_aui_input_8.doStartTag();
                if (_jspx_th_aui_input_8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_aui_input_wrapperCssClass_value_name_nobody.reuse(_jspx_th_aui_input_8);
                  return;
                }
                _jspx_tagPool_aui_input_wrapperCssClass_value_name_nobody.reuse(_jspx_th_aui_input_8);
                out.write("\n\t\t\t\t\t");
              }
              if (_jspx_th_aui_fieldset_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_aui_fieldset_label_cssClass.reuse(_jspx_th_aui_fieldset_1);
                return;
              }
              _jspx_tagPool_aui_fieldset_label_cssClass.reuse(_jspx_th_aui_fieldset_1);
              out.write("\n\n\t\t\t\t\t");
              //  aui:fieldset
              com.liferay.taglib.aui.FieldsetTag _jspx_th_aui_fieldset_3 = (com.liferay.taglib.aui.FieldsetTag) _jspx_tagPool_aui_fieldset_label_cssClass.get(com.liferay.taglib.aui.FieldsetTag.class);
              _jspx_th_aui_fieldset_3.setPageContext(_jspx_page_context);
              _jspx_th_aui_fieldset_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_panel_1);
              _jspx_th_aui_fieldset_3.setCssClass("handle-data");
              _jspx_th_aui_fieldset_3.setLabel("database");
              int _jspx_eval_aui_fieldset_3 = _jspx_th_aui_fieldset_3.doStartTag();
              if (_jspx_eval_aui_fieldset_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\t\t\t\t\t\t");
                //  aui:input
                com.liferay.taglib.aui.InputTag _jspx_th_aui_input_9 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_helpMessage_nobody.get(com.liferay.taglib.aui.InputTag.class);
                _jspx_th_aui_input_9.setPageContext(_jspx_page_context);
                _jspx_th_aui_input_9.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_3);
                _jspx_th_aui_input_9.setHelpMessage("export-functionality-will-only-be-available-for-data-saved-to-the-database");
                _jspx_th_aui_input_9.setName("preferences--saveToDatabase--");
                _jspx_th_aui_input_9.setType("checkbox");
                _jspx_th_aui_input_9.setValue( saveToDatabase );
                int _jspx_eval_aui_input_9 = _jspx_th_aui_input_9.doStartTag();
                if (_jspx_th_aui_input_9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_aui_input_value_type_name_helpMessage_nobody.reuse(_jspx_th_aui_input_9);
                  return;
                }
                _jspx_tagPool_aui_input_value_type_name_helpMessage_nobody.reuse(_jspx_th_aui_input_9);
                out.write("\n\t\t\t\t\t");
              }
              if (_jspx_th_aui_fieldset_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_aui_fieldset_label_cssClass.reuse(_jspx_th_aui_fieldset_3);
                return;
              }
              _jspx_tagPool_aui_fieldset_label_cssClass.reuse(_jspx_th_aui_fieldset_3);
              out.write("\n\n\t\t\t\t\t");
              //  aui:fieldset
              com.liferay.taglib.aui.FieldsetTag _jspx_th_aui_fieldset_4 = (com.liferay.taglib.aui.FieldsetTag) _jspx_tagPool_aui_fieldset_label_cssClass.get(com.liferay.taglib.aui.FieldsetTag.class);
              _jspx_th_aui_fieldset_4.setPageContext(_jspx_page_context);
              _jspx_th_aui_fieldset_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_panel_1);
              _jspx_th_aui_fieldset_4.setCssClass("handle-data");
              _jspx_th_aui_fieldset_4.setLabel("file");
              int _jspx_eval_aui_fieldset_4 = _jspx_th_aui_fieldset_4.doStartTag();
              if (_jspx_eval_aui_fieldset_4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\t\t\t\t\t\t");
                //  aui:input
                com.liferay.taglib.aui.InputTag _jspx_th_aui_input_10 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
                _jspx_th_aui_input_10.setPageContext(_jspx_page_context);
                _jspx_th_aui_input_10.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_4);
                _jspx_th_aui_input_10.setName("preferences--saveToFile--");
                _jspx_th_aui_input_10.setType("checkbox");
                _jspx_th_aui_input_10.setValue( saveToFile );
                int _jspx_eval_aui_input_10 = _jspx_th_aui_input_10.doStartTag();
                if (_jspx_th_aui_input_10.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_10);
                  return;
                }
                _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_10);
                out.write("\n\n\t\t\t\t\t\t");
                //  liferay-ui:message
                com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_0 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_arguments_nobody.get(com.liferay.taglib.ui.MessageTag.class);
                _jspx_th_liferay$1ui_message_0.setPageContext(_jspx_page_context);
                _jspx_th_liferay$1ui_message_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_4);
                _jspx_th_liferay$1ui_message_0.setArguments( HtmlUtil.escape(WebFormUtil.getFileName(themeDisplay, portletResource, webFormServiceConfiguration.dataRootDir())) );
                _jspx_th_liferay$1ui_message_0.setKey("form-data-will-be-saved-to-x");
                int _jspx_eval_liferay$1ui_message_0 = _jspx_th_liferay$1ui_message_0.doStartTag();
                if (_jspx_th_liferay$1ui_message_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_liferay$1ui_message_key_arguments_nobody.reuse(_jspx_th_liferay$1ui_message_0);
                  return;
                }
                _jspx_tagPool_liferay$1ui_message_key_arguments_nobody.reuse(_jspx_th_liferay$1ui_message_0);
                out.write("\n\t\t\t\t\t");
              }
              if (_jspx_th_aui_fieldset_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_aui_fieldset_label_cssClass.reuse(_jspx_th_aui_fieldset_4);
                return;
              }
              _jspx_tagPool_aui_fieldset_label_cssClass.reuse(_jspx_th_aui_fieldset_4);
              out.write("\n\t\t\t\t");
            }
            if (_jspx_th_liferay$1ui_panel_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_liferay$1ui_panel_title_persistState_markupView_id_extended_collapsible.reuse(_jspx_th_liferay$1ui_panel_1);
              return;
            }
            _jspx_tagPool_liferay$1ui_panel_title_persistState_markupView_id_extended_collapsible.reuse(_jspx_th_liferay$1ui_panel_1);
            out.write("\n\n\t\t\t\t");
            //  liferay-ui:panel
            com.liferay.taglib.ui.PanelTag _jspx_th_liferay$1ui_panel_2 = (com.liferay.taglib.ui.PanelTag) _jspx_tagPool_liferay$1ui_panel_title_persistState_markupView_id_extended_collapsible.get(com.liferay.taglib.ui.PanelTag.class);
            _jspx_th_liferay$1ui_panel_2.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1ui_panel_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_panel$1container_0);
            _jspx_th_liferay$1ui_panel_2.setCollapsible( true );
            _jspx_th_liferay$1ui_panel_2.setExtended( true );
            _jspx_th_liferay$1ui_panel_2.setId("webFormFields");
            _jspx_th_liferay$1ui_panel_2.setMarkupView("lexicon");
            _jspx_th_liferay$1ui_panel_2.setPersistState( true );
            _jspx_th_liferay$1ui_panel_2.setTitle("form-fields");
            int _jspx_eval_liferay$1ui_panel_2 = _jspx_th_liferay$1ui_panel_2.doStartTag();
            if (_jspx_eval_liferay$1ui_panel_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\t\t\t\t\t");
              //  aui:fieldset
              com.liferay.taglib.aui.FieldsetTag _jspx_th_aui_fieldset_5 = (com.liferay.taglib.aui.FieldsetTag) _jspx_tagPool_aui_fieldset_cssClass.get(com.liferay.taglib.aui.FieldsetTag.class);
              _jspx_th_aui_fieldset_5.setPageContext(_jspx_page_context);
              _jspx_th_aui_fieldset_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_panel_2);
              _jspx_th_aui_fieldset_5.setCssClass("rows-container webFields");
              int _jspx_eval_aui_fieldset_5 = _jspx_th_aui_fieldset_5.doStartTag();
              if (_jspx_eval_aui_fieldset_5 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\t\t\t\t\t\t");
                //  c:if
                com.liferay.taglib.core.IfTag _jspx_th_c_if_0 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                _jspx_th_c_if_0.setPageContext(_jspx_page_context);
                _jspx_th_c_if_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_5);
                _jspx_th_c_if_0.setTest( fieldsEditingDisabled );
                int _jspx_eval_c_if_0 = _jspx_th_c_if_0.doStartTag();
                if (_jspx_eval_c_if_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  out.write("\n\t\t\t\t\t\t\t<div class=\"alert\">\n\t\t\t\t\t\t\t\t");
                  if (_jspx_meth_liferay$1ui_message_1((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_0, _jspx_page_context))
                    return;
                  out.write("\n\t\t\t\t\t\t\t</div>\n\n\t\t\t\t\t\t\t");
                  //  c:if
                  com.liferay.taglib.core.IfTag _jspx_th_c_if_1 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                  _jspx_th_c_if_1.setPageContext(_jspx_page_context);
                  _jspx_th_c_if_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_0);
                  _jspx_th_c_if_1.setTest( layoutTypePortlet.hasPortletId(portletResource) );
                  int _jspx_eval_c_if_1 = _jspx_th_c_if_1.doStartTag();
                  if (_jspx_eval_c_if_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    out.write("\n\t\t\t\t\t\t\t\t");
                    //  liferay-portlet:resourceURL
                    com.liferay.taglib.portlet.ResourceURLTag _jspx_th_liferay$1portlet_resourceURL_0 = (com.liferay.taglib.portlet.ResourceURLTag) _jspx_tagPool_liferay$1portlet_resourceURL_var_portletName.get(com.liferay.taglib.portlet.ResourceURLTag.class);
                    _jspx_th_liferay$1portlet_resourceURL_0.setPageContext(_jspx_page_context);
                    _jspx_th_liferay$1portlet_resourceURL_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_1);
                    _jspx_th_liferay$1portlet_resourceURL_0.setPortletName( portletResource );
                    _jspx_th_liferay$1portlet_resourceURL_0.setVar("exportURL");
                    int _jspx_eval_liferay$1portlet_resourceURL_0 = _jspx_th_liferay$1portlet_resourceURL_0.doStartTag();
                    if (_jspx_eval_liferay$1portlet_resourceURL_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      out.write("\n\t\t\t\t\t\t\t\t\t");
                      //  portlet:param
                      com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_0 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                      _jspx_th_portlet_param_0.setPageContext(_jspx_page_context);
                      _jspx_th_portlet_param_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_resourceURL_0);
                      _jspx_th_portlet_param_0.setName( Constants.CMD );
                      _jspx_th_portlet_param_0.setValue("export");
                      int _jspx_eval_portlet_param_0 = _jspx_th_portlet_param_0.doStartTag();
                      if (_jspx_th_portlet_param_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_0);
                        return;
                      }
                      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_0);
                      out.write("\n\t\t\t\t\t\t\t\t");
                    }
                    if (_jspx_th_liferay$1portlet_resourceURL_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_liferay$1portlet_resourceURL_var_portletName.reuse(_jspx_th_liferay$1portlet_resourceURL_0);
                      return;
                    }
                    _jspx_tagPool_liferay$1portlet_resourceURL_var_portletName.reuse(_jspx_th_liferay$1portlet_resourceURL_0);
                    java.lang.String exportURL = null;
                    exportURL = (java.lang.String) _jspx_page_context.findAttribute("exportURL");
                    out.write("\n\n\t\t\t\t\t\t\t\t");

								String taglibExport = "submitForm(document.hrefFm, '" + exportURL + "', false);";
								
                    out.write("\n\n\t\t\t\t\t\t\t\t");
                    //  aui:button
                    com.liferay.taglib.aui.ButtonTag _jspx_th_aui_button_0 = (com.liferay.taglib.aui.ButtonTag) _jspx_tagPool_aui_button_value_onClick_nobody.get(com.liferay.taglib.aui.ButtonTag.class);
                    _jspx_th_aui_button_0.setPageContext(_jspx_page_context);
                    _jspx_th_aui_button_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_1);
                    _jspx_th_aui_button_0.setOnClick( taglibExport );
                    _jspx_th_aui_button_0.setValue("export-data");
                    int _jspx_eval_aui_button_0 = _jspx_th_aui_button_0.doStartTag();
                    if (_jspx_th_aui_button_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_aui_button_value_onClick_nobody.reuse(_jspx_th_aui_button_0);
                      return;
                    }
                    _jspx_tagPool_aui_button_value_onClick_nobody.reuse(_jspx_th_aui_button_0);
                    out.write("\n\n\t\t\t\t\t\t\t\t");
                    //  liferay-portlet:actionURL
                    com.liferay.taglib.portlet.ActionURLTag _jspx_th_liferay$1portlet_actionURL_1 = (com.liferay.taglib.portlet.ActionURLTag) _jspx_tagPool_liferay$1portlet_actionURL_var_portletName.get(com.liferay.taglib.portlet.ActionURLTag.class);
                    _jspx_th_liferay$1portlet_actionURL_1.setPageContext(_jspx_page_context);
                    _jspx_th_liferay$1portlet_actionURL_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_1);
                    _jspx_th_liferay$1portlet_actionURL_1.setPortletName( portletResource );
                    _jspx_th_liferay$1portlet_actionURL_1.setVar("deleteURL");
                    int _jspx_eval_liferay$1portlet_actionURL_1 = _jspx_th_liferay$1portlet_actionURL_1.doStartTag();
                    if (_jspx_eval_liferay$1portlet_actionURL_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      out.write("\n\t\t\t\t\t\t\t\t\t");
                      //  portlet:param
                      com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_1 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                      _jspx_th_portlet_param_1.setPageContext(_jspx_page_context);
                      _jspx_th_portlet_param_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_actionURL_1);
                      _jspx_th_portlet_param_1.setName( ActionRequest.ACTION_NAME );
                      _jspx_th_portlet_param_1.setValue("deleteData");
                      int _jspx_eval_portlet_param_1 = _jspx_th_portlet_param_1.doStartTag();
                      if (_jspx_th_portlet_param_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_1);
                        return;
                      }
                      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_1);
                      out.write("\n\t\t\t\t\t\t\t\t\t");
                      //  portlet:param
                      com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_2 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                      _jspx_th_portlet_param_2.setPageContext(_jspx_page_context);
                      _jspx_th_portlet_param_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_actionURL_1);
                      _jspx_th_portlet_param_2.setName("redirect");
                      _jspx_th_portlet_param_2.setValue( currentURL );
                      int _jspx_eval_portlet_param_2 = _jspx_th_portlet_param_2.doStartTag();
                      if (_jspx_th_portlet_param_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_2);
                        return;
                      }
                      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_2);
                      out.write("\n\t\t\t\t\t\t\t\t");
                    }
                    if (_jspx_th_liferay$1portlet_actionURL_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_liferay$1portlet_actionURL_var_portletName.reuse(_jspx_th_liferay$1portlet_actionURL_1);
                      return;
                    }
                    _jspx_tagPool_liferay$1portlet_actionURL_var_portletName.reuse(_jspx_th_liferay$1portlet_actionURL_1);
                    java.lang.String deleteURL = null;
                    deleteURL = (java.lang.String) _jspx_page_context.findAttribute("deleteURL");
                    out.write("\n\n\t\t\t\t\t\t\t\t");

								String taglibDelete = "submitForm(document." + renderResponse.getNamespace() + "fm, '" + deleteURL + "');";
								
                    out.write("\n\n\t\t\t\t\t\t\t\t");
                    //  aui:button
                    com.liferay.taglib.aui.ButtonTag _jspx_th_aui_button_1 = (com.liferay.taglib.aui.ButtonTag) _jspx_tagPool_aui_button_value_onClick_nobody.get(com.liferay.taglib.aui.ButtonTag.class);
                    _jspx_th_aui_button_1.setPageContext(_jspx_page_context);
                    _jspx_th_aui_button_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_1);
                    _jspx_th_aui_button_1.setOnClick( taglibDelete );
                    _jspx_th_aui_button_1.setValue("delete-data");
                    int _jspx_eval_aui_button_1 = _jspx_th_aui_button_1.doStartTag();
                    if (_jspx_th_aui_button_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_aui_button_value_onClick_nobody.reuse(_jspx_th_aui_button_1);
                      return;
                    }
                    _jspx_tagPool_aui_button_value_onClick_nobody.reuse(_jspx_th_aui_button_1);
                    out.write("\n\t\t\t\t\t\t\t");
                  }
                  if (_jspx_th_c_if_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_1);
                    return;
                  }
                  _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_1);
                  out.write("\n\n\t\t\t\t\t\t\t<br /><br />\n\t\t\t\t\t\t");
                }
                if (_jspx_th_c_if_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
                  return;
                }
                _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
                out.write("\n\n\t\t\t\t\t\t");
                //  aui:input
                com.liferay.taglib.aui.InputTag _jspx_th_aui_input_11 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
                _jspx_th_aui_input_11.setPageContext(_jspx_page_context);
                _jspx_th_aui_input_11.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_5);
                _jspx_th_aui_input_11.setName("updateFields");
                _jspx_th_aui_input_11.setType("hidden");
                _jspx_th_aui_input_11.setValue( !fieldsEditingDisabled );
                int _jspx_eval_aui_input_11 = _jspx_th_aui_input_11.doStartTag();
                if (_jspx_th_aui_input_11.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_11);
                  return;
                }
                _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_11);
                out.write("\n\n\t\t\t\t\t\t");

						String formFieldsIndexesParam = ParamUtil.getString(renderRequest, "formFieldsIndexes");

						int[] formFieldsIndexes = null;

						if (Validator.isNotNull(formFieldsIndexesParam)) {
							formFieldsIndexes = StringUtil.split(formFieldsIndexesParam, 0);
						}
						else {
							formFieldsIndexes = new int[0];

							for (int i = 1; true; i++) {
								String fieldLabel = PrefsParamUtil.getString(portletPreferences, request, "fieldLabel" + i);

								if (Validator.isNull(fieldLabel)) {
									break;
								}

								formFieldsIndexes = ArrayUtil.append(formFieldsIndexes, i);
							}

							if (formFieldsIndexes.length == 0) {
								formFieldsIndexes = ArrayUtil.append(formFieldsIndexes, -1);
							}
						}

						int index = 1;

						for (int formFieldsIndex : formFieldsIndexes) {
							request.setAttribute("configuration.jsp-fieldsEditingDisabled", String.valueOf(fieldsEditingDisabled));
							request.setAttribute("configuration.jsp-formFieldsIndex", String.valueOf(formFieldsIndex));
							request.setAttribute("configuration.jsp-index", String.valueOf(index));
						
                out.write("\n\n\t\t\t\t\t\t\t<div class=\"lfr-form-row\" id=\"");
                if (_jspx_meth_portlet_namespace_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_fieldset_5, _jspx_page_context))
                  return;
                out.write("fieldset");
                out.print( formFieldsIndex );
                out.write("\">\n\t\t\t\t\t\t\t\t<div class=\"row-fields\">\n\t\t\t\t\t\t\t\t\t");
                //  liferay-util:include
                com.liferay.taglib.util.IncludeTag _jspx_th_liferay$1util_include_0 = (com.liferay.taglib.util.IncludeTag) _jspx_tagPool_liferay$1util_include_servletContext_page_nobody.get(com.liferay.taglib.util.IncludeTag.class);
                _jspx_th_liferay$1util_include_0.setPageContext(_jspx_page_context);
                _jspx_th_liferay$1util_include_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_5);
                _jspx_th_liferay$1util_include_0.setPage("/edit_field.jsp");
                _jspx_th_liferay$1util_include_0.setServletContext( application );
                int _jspx_eval_liferay$1util_include_0 = _jspx_th_liferay$1util_include_0.doStartTag();
                if (_jspx_th_liferay$1util_include_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_liferay$1util_include_servletContext_page_nobody.reuse(_jspx_th_liferay$1util_include_0);
                  return;
                }
                _jspx_tagPool_liferay$1util_include_servletContext_page_nobody.reuse(_jspx_th_liferay$1util_include_0);
                out.write("\n\t\t\t\t\t\t\t\t</div>\n\t\t\t\t\t\t\t</div>\n\n\t\t\t\t\t\t");

							index++;
						}
						
                out.write("\n\n\t\t\t\t\t");
              }
              if (_jspx_th_aui_fieldset_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_aui_fieldset_cssClass.reuse(_jspx_th_aui_fieldset_5);
                return;
              }
              _jspx_tagPool_aui_fieldset_cssClass.reuse(_jspx_th_aui_fieldset_5);
              out.write("\n\t\t\t\t");
            }
            if (_jspx_th_liferay$1ui_panel_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_liferay$1ui_panel_title_persistState_markupView_id_extended_collapsible.reuse(_jspx_th_liferay$1ui_panel_2);
              return;
            }
            _jspx_tagPool_liferay$1ui_panel_title_persistState_markupView_id_extended_collapsible.reuse(_jspx_th_liferay$1ui_panel_2);
            out.write("\n\t\t\t");
            int evalDoAfterBody = _jspx_th_liferay$1ui_panel$1container_0.doAfterBody();
            if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
              break;
          } while (true);
          if (_jspx_eval_liferay$1ui_panel$1container_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
            out = _jspx_page_context.popBody();
        }
        if (_jspx_th_liferay$1ui_panel$1container_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_liferay$1ui_panel$1container_persistState_markupView_id_extended.reuse(_jspx_th_liferay$1ui_panel$1container_0);
          return;
        }
        _jspx_tagPool_liferay$1ui_panel$1container_persistState_markupView_id_extended.reuse(_jspx_th_liferay$1ui_panel$1container_0);
        out.write("\n\t\t</div>\n\t</div>\n\n\t");
        if (_jspx_meth_aui_button$1row_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_form_0, _jspx_page_context))
          return;
        out.write('\n');
      }
      if (_jspx_th_aui_form_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_aui_form_name_method_action.reuse(_jspx_th_aui_form_0);
        return;
      }
      _jspx_tagPool_aui_form_name_method_action.reuse(_jspx_th_aui_form_0);
      out.write('\n');
      out.write('\n');
      //  c:if
      com.liferay.taglib.core.IfTag _jspx_th_c_if_2 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
      _jspx_th_c_if_2.setPageContext(_jspx_page_context);
      _jspx_th_c_if_2.setParent(null);
      _jspx_th_c_if_2.setTest( !fieldsEditingDisabled );
      int _jspx_eval_c_if_2 = _jspx_th_c_if_2.doStartTag();
      if (_jspx_eval_c_if_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        out.write('\n');
        out.write('	');
        //  aui:script
        com.liferay.taglib.aui.ScriptTag _jspx_th_aui_script_0 = (com.liferay.taglib.aui.ScriptTag) _jspx_tagPool_aui_script_use.get(com.liferay.taglib.aui.ScriptTag.class);
        _jspx_th_aui_script_0.setPageContext(_jspx_page_context);
        _jspx_th_aui_script_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_2);
        _jspx_th_aui_script_0.setUse("aui-base,liferay-auto-fields");
        int _jspx_eval_aui_script_0 = _jspx_th_aui_script_0.doStartTag();
        if (_jspx_eval_aui_script_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          if (_jspx_eval_aui_script_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
            out = _jspx_page_context.pushBody();
            _jspx_th_aui_script_0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
            _jspx_th_aui_script_0.doInitBody();
          }
          do {
            out.write("\n\t\tvar toggleOptions = function(event) {\n\t\t\tvar instance = this;\n\n\t\t\tvar formRow = instance.ancestor('.lfr-form-row');\n\t\t\tvar value = instance.val();\n\n\t\t\tvar optionsDiv = formRow.one('.options');\n\n\t\t\tif ((value === 'options') || (value === 'radio')) {\n\t\t\t\toptionsDiv.all('label').show();\n\t\t\t\toptionsDiv.show();\n\t\t\t}\n\t\t\telse {\n\t\t\t\toptionsDiv.hide();\n\t\t\t}\n\n\t\t\tvar labelName = formRow.one('.label-name');\n\t\t\tvar optionalControl = formRow.one('.optional-control').ancestor();\n\t\t\tvar paragraphDiv = formRow.one('.paragraph');\n\n\t\t\tvar paragraph = value === 'paragraph';\n\n\t\t\tif (paragraph) {\n\t\t\t\tvar inputName = labelName.one('input.field');\n\n\t\t\t\tvar formFieldsIndex = instance.attr('id').match(/\\d+$/);\n\n\t\t\t\tinputName.val('");
            if (_jspx_meth_liferay$1ui_message_2((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_0, _jspx_page_context))
              return;
            out.write("' + formFieldsIndex);\n\t\t\t\tinputName.fire('change');\n\t\t\t}\n\n\t\t\tlabelName.toggle(!paragraph);\n\t\t\toptionalControl.toggle(!paragraph);\n\t\t\tparagraphDiv.toggle(paragraph);\n\t\t};\n\n\t\tvar webFields = A.one('.webFields');\n\n\t\twebFields.all('select').each(toggleOptions);\n\n\t\twebFields.delegate(['change', 'click', 'keydown'], toggleOptions, 'select');\n\n\t\t");
            //  c:if
            com.liferay.taglib.core.IfTag _jspx_th_c_if_3 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
            _jspx_th_c_if_3.setPageContext(_jspx_page_context);
            _jspx_th_c_if_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_0);
            _jspx_th_c_if_3.setTest( webFormServiceConfiguration.validationScriptEnable() );
            int _jspx_eval_c_if_3 = _jspx_th_c_if_3.doStartTag();
            if (_jspx_eval_c_if_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\t\t\tvar toggleValidationOptions = function(event) {\n\t\t\t\tthis.next().toggle();\n\t\t\t};\n\n\t\t\twebFields.delegate('click', toggleValidationOptions, '.validation-link');\n\t\t");
            }
            if (_jspx_th_c_if_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_3);
              return;
            }
            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_3);
            out.write("\n\n\t\twebFields.delegate(\n\t\t\t'change',\n\t\t\tfunction(event) {\n\t\t\t\tvar input = event.currentTarget;\n\n\t\t\t\tvar row = input.ancestor('.field-row');\n\n\t\t\t\tvar label = row.one('.field-title');\n\n\t\t\t\tif (label) {\n\t\t\t\t\tlabel.html(input.get('value'));\n\t\t\t\t}\n\t\t\t},\n\t\t\t'.label-name input'\n\t\t);\n\n\t\tnew Liferay.AutoFields(\n\t\t\t{\n\t\t\t\tcontentBox: webFields,\n\t\t\t\tfieldIndexes: '");
            if (_jspx_meth_portlet_namespace_1((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_0, _jspx_page_context))
              return;
            out.write("formFieldsIndexes',\n\t\t\t\tnamespace: '");
            if (_jspx_meth_portlet_namespace_2((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_0, _jspx_page_context))
              return;
            out.write("',\n\t\t\t\tsortable: true,\n\t\t\t\tsortableHandle: '.lfr-form-row',\n\n\t\t\t\t");
            //  liferay-portlet:renderURL
            com.liferay.taglib.portlet.RenderURLTag _jspx_th_liferay$1portlet_renderURL_1 = (com.liferay.taglib.portlet.RenderURLTag) _jspx_tagPool_liferay$1portlet_renderURL_windowState_var_portletConfiguration.get(com.liferay.taglib.portlet.RenderURLTag.class);
            _jspx_th_liferay$1portlet_renderURL_1.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1portlet_renderURL_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_0);
            _jspx_th_liferay$1portlet_renderURL_1.setPortletConfiguration( true );
            _jspx_th_liferay$1portlet_renderURL_1.setVar("editFieldURL");
            _jspx_th_liferay$1portlet_renderURL_1.setWindowState( LiferayWindowState.EXCLUSIVE.toString() );
            int _jspx_eval_liferay$1portlet_renderURL_1 = _jspx_th_liferay$1portlet_renderURL_1.doStartTag();
            if (_jspx_eval_liferay$1portlet_renderURL_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\t\t\t\t\t");
              //  portlet:param
              com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_3 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
              _jspx_th_portlet_param_3.setPageContext(_jspx_page_context);
              _jspx_th_portlet_param_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_1);
              _jspx_th_portlet_param_3.setName( Constants.CMD );
              _jspx_th_portlet_param_3.setValue( Constants.ADD );
              int _jspx_eval_portlet_param_3 = _jspx_th_portlet_param_3.doStartTag();
              if (_jspx_th_portlet_param_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_3);
                return;
              }
              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_3);
              out.write("\n\t\t\t\t");
            }
            if (_jspx_th_liferay$1portlet_renderURL_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_liferay$1portlet_renderURL_windowState_var_portletConfiguration.reuse(_jspx_th_liferay$1portlet_renderURL_1);
              return;
            }
            _jspx_tagPool_liferay$1portlet_renderURL_windowState_var_portletConfiguration.reuse(_jspx_th_liferay$1portlet_renderURL_1);
            java.lang.String editFieldURL = null;
            editFieldURL = (java.lang.String) _jspx_page_context.findAttribute("editFieldURL");
            out.write("\n\n\t\t\t\turl: '");
            out.print( editFieldURL );
            out.write("'\n\t\t\t}\n\t\t).render();\n\t");
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
      }
      if (_jspx_th_c_if_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_2);
        return;
      }
      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_2);
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

  private boolean _jspx_meth_liferay$1ui_message_1(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_1 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_1.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_0);
    _jspx_th_liferay$1ui_message_1.setKey("there-is-existing-form-data-please-export-and-delete-it-before-making-changes-to-the-fields");
    int _jspx_eval_liferay$1ui_message_1 = _jspx_th_liferay$1ui_message_1.doStartTag();
    if (_jspx_th_liferay$1ui_message_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_1);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_1);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_0(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_fieldset_5, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_0 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_0.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_5);
    int _jspx_eval_portlet_namespace_0 = _jspx_th_portlet_namespace_0.doStartTag();
    if (_jspx_th_portlet_namespace_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_0);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_0);
    return false;
  }

  private boolean _jspx_meth_aui_button$1row_0(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_form_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:button-row
    com.liferay.taglib.aui.ButtonRowTag _jspx_th_aui_button$1row_0 = (com.liferay.taglib.aui.ButtonRowTag) _jspx_tagPool_aui_button$1row.get(com.liferay.taglib.aui.ButtonRowTag.class);
    _jspx_th_aui_button$1row_0.setPageContext(_jspx_page_context);
    _jspx_th_aui_button$1row_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
    int _jspx_eval_aui_button$1row_0 = _jspx_th_aui_button$1row_0.doStartTag();
    if (_jspx_eval_aui_button$1row_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      out.write("\n\t\t");
      if (_jspx_meth_aui_button_2((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_button$1row_0, _jspx_page_context))
        return true;
      out.write('\n');
      out.write('	');
    }
    if (_jspx_th_aui_button$1row_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_button$1row.reuse(_jspx_th_aui_button$1row_0);
      return true;
    }
    _jspx_tagPool_aui_button$1row.reuse(_jspx_th_aui_button$1row_0);
    return false;
  }

  private boolean _jspx_meth_aui_button_2(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_button$1row_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:button
    com.liferay.taglib.aui.ButtonTag _jspx_th_aui_button_2 = (com.liferay.taglib.aui.ButtonTag) _jspx_tagPool_aui_button_type_nobody.get(com.liferay.taglib.aui.ButtonTag.class);
    _jspx_th_aui_button_2.setPageContext(_jspx_page_context);
    _jspx_th_aui_button_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_button$1row_0);
    _jspx_th_aui_button_2.setType("submit");
    int _jspx_eval_aui_button_2 = _jspx_th_aui_button_2.doStartTag();
    if (_jspx_th_aui_button_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_button_type_nobody.reuse(_jspx_th_aui_button_2);
      return true;
    }
    _jspx_tagPool_aui_button_type_nobody.reuse(_jspx_th_aui_button_2);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_2(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_2 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_2.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_0);
    _jspx_th_liferay$1ui_message_2.setKey("paragraph");
    int _jspx_eval_liferay$1ui_message_2 = _jspx_th_liferay$1ui_message_2.doStartTag();
    if (_jspx_th_liferay$1ui_message_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_2);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_2);
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
}
