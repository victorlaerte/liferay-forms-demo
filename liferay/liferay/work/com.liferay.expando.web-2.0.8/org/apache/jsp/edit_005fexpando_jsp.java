package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.liferay.expando.kernel.exception.ColumnNameException;
import com.liferay.expando.kernel.exception.ColumnTypeException;
import com.liferay.expando.kernel.exception.DuplicateColumnNameException;
import com.liferay.expando.kernel.exception.NoSuchColumnException;
import com.liferay.expando.kernel.exception.ValueDataException;
import com.liferay.expando.kernel.model.CustomAttributesDisplay;
import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.model.ExpandoColumn;
import com.liferay.expando.kernel.model.ExpandoColumnConstants;
import com.liferay.expando.kernel.service.ExpandoColumnLocalServiceUtil;
import com.liferay.expando.kernel.service.ExpandoColumnServiceUtil;
import com.liferay.expando.kernel.service.permission.ExpandoColumnPermissionUtil;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.expando.web.internal.display.context.ExpandoDisplayContext;
import com.liferay.expando.web.internal.search.CustomFieldChecker;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.ResourceActionsUtil;
import com.liferay.portal.kernel.service.PortletLocalServiceUtil;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.TextFormatter;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portlet.expando.util.comparator.CustomAttributesDisplayComparator;
import com.liferay.taglib.search.ResultRow;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.portlet.PortletURL;
import javax.portlet.WindowState;

public final class edit_005fexpando_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(2);
    _jspx_dependants.add("/init.jsp");
    _jspx_dependants.add("/init-ext.jsp");
  }

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_actionURL_var_name;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_input_value_type_required_name_label_helpMessage_cssClass_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_param_value_name_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_input_value_type_name_label_helpMessage_cssClass_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_if_test;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_option_value_label_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_select_name_label_helpMessage;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_button_type_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_form_name_method_cssClass_action;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_option_value_selected_label_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_fieldset$1group_markupView;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_select_name_helpMessage;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_choose;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_input_name_label_helpMessage_autoFocus_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_input$1localized_xml_name_cssClass_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1frontend_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_fieldset;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_when_test;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_input_value_type_name_helpMessage_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_fieldset_label;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_button_type_href_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_message_key_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_input$1date_yearValue_yearParam_monthValue_monthParam_firstDayOfWeek_disabled_dayValue_dayParam_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_input_value_type_name_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_field$1wrapper_label;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_input_value_type_name_cssClass_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_select_value_name_label_helpMessage;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_otherwise;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_error_message_exception_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_select_name;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1theme_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_button$1row;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_input$1time_minuteValue_minuteParam_hourValue_hourParam_disabled_amPmValue_amPmParam_nobody;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_portlet_actionURL_var_name = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_input_value_type_required_name_label_helpMessage_cssClass_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_param_value_name_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_input_value_type_name_label_helpMessage_cssClass_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_if_test = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_option_value_label_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_select_name_label_helpMessage = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_button_type_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_form_name_method_cssClass_action = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_option_value_selected_label_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_fieldset$1group_markupView = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_select_name_helpMessage = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_choose = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_input_name_label_helpMessage_autoFocus_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_input$1localized_xml_name_cssClass_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1frontend_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_fieldset = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_when_test = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_input_value_type_name_helpMessage_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_fieldset_label = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_button_type_href_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_message_key_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_input$1date_yearValue_yearParam_monthValue_monthParam_firstDayOfWeek_disabled_dayValue_dayParam_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_input_value_type_name_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_field$1wrapper_label = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_input_value_type_name_cssClass_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_select_value_name_label_helpMessage = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_otherwise = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_error_message_exception_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_select_name = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1theme_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_button$1row = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_input$1time_minuteValue_minuteParam_hourValue_hourParam_disabled_amPmValue_amPmParam_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_portlet_actionURL_var_name.release();
    _jspx_tagPool_aui_input_value_type_required_name_label_helpMessage_cssClass_nobody.release();
    _jspx_tagPool_portlet_param_value_name_nobody.release();
    _jspx_tagPool_aui_input_value_type_name_label_helpMessage_cssClass_nobody.release();
    _jspx_tagPool_c_if_test.release();
    _jspx_tagPool_aui_option_value_label_nobody.release();
    _jspx_tagPool_aui_select_name_label_helpMessage.release();
    _jspx_tagPool_aui_button_type_nobody.release();
    _jspx_tagPool_aui_form_name_method_cssClass_action.release();
    _jspx_tagPool_aui_option_value_selected_label_nobody.release();
    _jspx_tagPool_aui_fieldset$1group_markupView.release();
    _jspx_tagPool_aui_select_name_helpMessage.release();
    _jspx_tagPool_c_choose.release();
    _jspx_tagPool_aui_input_name_label_helpMessage_autoFocus_nobody.release();
    _jspx_tagPool_liferay$1ui_input$1localized_xml_name_cssClass_nobody.release();
    _jspx_tagPool_liferay$1frontend_defineObjects_nobody.release();
    _jspx_tagPool_aui_fieldset.release();
    _jspx_tagPool_c_when_test.release();
    _jspx_tagPool_aui_input_value_type_name_helpMessage_nobody.release();
    _jspx_tagPool_aui_fieldset_label.release();
    _jspx_tagPool_aui_button_type_href_nobody.release();
    _jspx_tagPool_liferay$1ui_message_key_nobody.release();
    _jspx_tagPool_liferay$1ui_input$1date_yearValue_yearParam_monthValue_monthParam_firstDayOfWeek_disabled_dayValue_dayParam_nobody.release();
    _jspx_tagPool_aui_input_value_type_name_nobody.release();
    _jspx_tagPool_aui_field$1wrapper_label.release();
    _jspx_tagPool_aui_input_value_type_name_cssClass_nobody.release();
    _jspx_tagPool_aui_select_value_name_label_helpMessage.release();
    _jspx_tagPool_c_otherwise.release();
    _jspx_tagPool_portlet_defineObjects_nobody.release();
    _jspx_tagPool_liferay$1ui_error_message_exception_nobody.release();
    _jspx_tagPool_aui_select_name.release();
    _jspx_tagPool_liferay$1theme_defineObjects_nobody.release();
    _jspx_tagPool_aui_button$1row.release();
    _jspx_tagPool_liferay$1ui_input$1time_minuteValue_minuteParam_hourValue_hourParam_disabled_amPmValue_amPmParam_nobody.release();
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
      out.write('\n');
      out.write('\n');

String redirect = ParamUtil.getString(request, "redirect");

String modelResource = ParamUtil.getString(request, "modelResource");
String modelResourceName = ResourceActionsUtil.getModelResource(request, modelResource);

long columnId = ParamUtil.getLong(request, "columnId");

ExpandoColumn column = null;

if (columnId > 0) {
	column = ExpandoColumnServiceUtil.fetchExpandoColumn(columnId);
}

int type = 0;

if (column != null) {
	type = column.getType();
}

ExpandoBridge expandoBridge = ExpandoBridgeFactoryUtil.getExpandoBridge(company.getCompanyId(), modelResource);

UnicodeProperties properties = new UnicodeProperties(true);
Serializable defaultValue = null;

if (column != null) {
	properties = expandoBridge.getAttributeProperties(column.getName());
	defaultValue = expandoBridge.getAttributeDefault(column.getName());
}

boolean propertyHidden = GetterUtil.getBoolean(properties.get(ExpandoColumnConstants.PROPERTY_HIDDEN));
boolean propertyVisibleWithUpdatePermission = GetterUtil.getBoolean(properties.get(ExpandoColumnConstants.PROPERTY_VISIBLE_WITH_UPDATE_PERMISSION));
int propertyIndexType = GetterUtil.getInteger(properties.get(ExpandoColumnConstants.INDEX_TYPE));
boolean propertySecret = GetterUtil.getBoolean(properties.get(ExpandoColumnConstants.PROPERTY_SECRET));
int propertyHeight = GetterUtil.getInteger(properties.get(ExpandoColumnConstants.PROPERTY_HEIGHT));
int propertyWidth = GetterUtil.getInteger(properties.get(ExpandoColumnConstants.PROPERTY_WIDTH));
String propertyDisplayType = GetterUtil.getString(properties.get(ExpandoColumnConstants.PROPERTY_DISPLAY_TYPE));

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcPath", "/view_attributes.jsp");
portletURL.setParameter("redirect", redirect);
portletURL.setParameter("modelResource", modelResource);

portletDisplay.setShowBackIcon(true);
portletDisplay.setURLBack(redirect);

renderResponse.setTitle(modelResourceName + ": " + ((column == null) ? LanguageUtil.get(request, "new-custom-field") : column.getName()));

      out.write('\n');
      out.write('\n');
      //  portlet:actionURL
      com.liferay.taglib.portlet.ActionURLTag _jspx_th_portlet_actionURL_0 = (com.liferay.taglib.portlet.ActionURLTag) _jspx_tagPool_portlet_actionURL_var_name.get(com.liferay.taglib.portlet.ActionURLTag.class);
      _jspx_th_portlet_actionURL_0.setPageContext(_jspx_page_context);
      _jspx_th_portlet_actionURL_0.setParent(null);
      _jspx_th_portlet_actionURL_0.setName( (column == null) ? "addExpando" : "updateExpando" );
      _jspx_th_portlet_actionURL_0.setVar("editExpandoURL");
      int _jspx_eval_portlet_actionURL_0 = _jspx_th_portlet_actionURL_0.doStartTag();
      if (_jspx_eval_portlet_actionURL_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        out.write('\n');
        out.write('	');
        if (_jspx_meth_portlet_param_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_portlet_actionURL_0, _jspx_page_context))
          return;
        out.write('\n');
      }
      if (_jspx_th_portlet_actionURL_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_portlet_actionURL_var_name.reuse(_jspx_th_portlet_actionURL_0);
        return;
      }
      _jspx_tagPool_portlet_actionURL_var_name.reuse(_jspx_th_portlet_actionURL_0);
      java.lang.String editExpandoURL = null;
      editExpandoURL = (java.lang.String) _jspx_page_context.findAttribute("editExpandoURL");
      out.write('\n');
      out.write('\n');
      //  aui:form
      com.liferay.taglib.aui.FormTag _jspx_th_aui_form_0 = (com.liferay.taglib.aui.FormTag) _jspx_tagPool_aui_form_name_method_cssClass_action.get(com.liferay.taglib.aui.FormTag.class);
      _jspx_th_aui_form_0.setPageContext(_jspx_page_context);
      _jspx_th_aui_form_0.setParent(null);
      _jspx_th_aui_form_0.setAction( editExpandoURL );
      _jspx_th_aui_form_0.setCssClass("container-fluid-1280");
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
        _jspx_th_aui_input_0.setName("redirect");
        _jspx_th_aui_input_0.setType("hidden");
        _jspx_th_aui_input_0.setValue( redirect );
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
        _jspx_th_aui_input_1.setName("columnId");
        _jspx_th_aui_input_1.setType("hidden");
        _jspx_th_aui_input_1.setValue( columnId );
        int _jspx_eval_aui_input_1 = _jspx_th_aui_input_1.doStartTag();
        if (_jspx_th_aui_input_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_1);
          return;
        }
        _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_1);
        out.write('\n');
        out.write('	');
        //  aui:input
        com.liferay.taglib.aui.InputTag _jspx_th_aui_input_2 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
        _jspx_th_aui_input_2.setPageContext(_jspx_page_context);
        _jspx_th_aui_input_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
        _jspx_th_aui_input_2.setName("modelResource");
        _jspx_th_aui_input_2.setType("hidden");
        _jspx_th_aui_input_2.setValue( modelResource );
        int _jspx_eval_aui_input_2 = _jspx_th_aui_input_2.doStartTag();
        if (_jspx_th_aui_input_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_2);
          return;
        }
        _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_2);
        out.write("\n\n\t");
        //  liferay-ui:error
        com.liferay.taglib.ui.ErrorTag _jspx_th_liferay$1ui_error_0 = (com.liferay.taglib.ui.ErrorTag) _jspx_tagPool_liferay$1ui_error_message_exception_nobody.get(com.liferay.taglib.ui.ErrorTag.class);
        _jspx_th_liferay$1ui_error_0.setPageContext(_jspx_page_context);
        _jspx_th_liferay$1ui_error_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
        _jspx_th_liferay$1ui_error_0.setException( ColumnNameException.class );
        _jspx_th_liferay$1ui_error_0.setMessage("please-enter-a-valid-name");
        int _jspx_eval_liferay$1ui_error_0 = _jspx_th_liferay$1ui_error_0.doStartTag();
        if (_jspx_th_liferay$1ui_error_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_liferay$1ui_error_message_exception_nobody.reuse(_jspx_th_liferay$1ui_error_0);
          return;
        }
        _jspx_tagPool_liferay$1ui_error_message_exception_nobody.reuse(_jspx_th_liferay$1ui_error_0);
        out.write('\n');
        out.write('	');
        //  liferay-ui:error
        com.liferay.taglib.ui.ErrorTag _jspx_th_liferay$1ui_error_1 = (com.liferay.taglib.ui.ErrorTag) _jspx_tagPool_liferay$1ui_error_message_exception_nobody.get(com.liferay.taglib.ui.ErrorTag.class);
        _jspx_th_liferay$1ui_error_1.setPageContext(_jspx_page_context);
        _jspx_th_liferay$1ui_error_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
        _jspx_th_liferay$1ui_error_1.setException( ColumnTypeException.class );
        _jspx_th_liferay$1ui_error_1.setMessage("please-select-a-valid-type");
        int _jspx_eval_liferay$1ui_error_1 = _jspx_th_liferay$1ui_error_1.doStartTag();
        if (_jspx_th_liferay$1ui_error_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_liferay$1ui_error_message_exception_nobody.reuse(_jspx_th_liferay$1ui_error_1);
          return;
        }
        _jspx_tagPool_liferay$1ui_error_message_exception_nobody.reuse(_jspx_th_liferay$1ui_error_1);
        out.write('\n');
        out.write('	');
        //  liferay-ui:error
        com.liferay.taglib.ui.ErrorTag _jspx_th_liferay$1ui_error_2 = (com.liferay.taglib.ui.ErrorTag) _jspx_tagPool_liferay$1ui_error_message_exception_nobody.get(com.liferay.taglib.ui.ErrorTag.class);
        _jspx_th_liferay$1ui_error_2.setPageContext(_jspx_page_context);
        _jspx_th_liferay$1ui_error_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
        _jspx_th_liferay$1ui_error_2.setException( DuplicateColumnNameException.class );
        _jspx_th_liferay$1ui_error_2.setMessage("please-enter-a-unique-name");
        int _jspx_eval_liferay$1ui_error_2 = _jspx_th_liferay$1ui_error_2.doStartTag();
        if (_jspx_th_liferay$1ui_error_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_liferay$1ui_error_message_exception_nobody.reuse(_jspx_th_liferay$1ui_error_2);
          return;
        }
        _jspx_tagPool_liferay$1ui_error_message_exception_nobody.reuse(_jspx_th_liferay$1ui_error_2);
        out.write('\n');
        out.write('	');
        //  liferay-ui:error
        com.liferay.taglib.ui.ErrorTag _jspx_th_liferay$1ui_error_3 = (com.liferay.taglib.ui.ErrorTag) _jspx_tagPool_liferay$1ui_error_message_exception_nobody.get(com.liferay.taglib.ui.ErrorTag.class);
        _jspx_th_liferay$1ui_error_3.setPageContext(_jspx_page_context);
        _jspx_th_liferay$1ui_error_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
        _jspx_th_liferay$1ui_error_3.setException( ValueDataException.class );
        _jspx_th_liferay$1ui_error_3.setMessage("please-enter-a-valid-value");
        int _jspx_eval_liferay$1ui_error_3 = _jspx_th_liferay$1ui_error_3.doStartTag();
        if (_jspx_th_liferay$1ui_error_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_liferay$1ui_error_message_exception_nobody.reuse(_jspx_th_liferay$1ui_error_3);
          return;
        }
        _jspx_tagPool_liferay$1ui_error_message_exception_nobody.reuse(_jspx_th_liferay$1ui_error_3);
        out.write("\n\n\t");
        //  aui:fieldset-group
        com.liferay.taglib.aui.FieldsetGroupTag _jspx_th_aui_fieldset$1group_0 = (com.liferay.taglib.aui.FieldsetGroupTag) _jspx_tagPool_aui_fieldset$1group_markupView.get(com.liferay.taglib.aui.FieldsetGroupTag.class);
        _jspx_th_aui_fieldset$1group_0.setPageContext(_jspx_page_context);
        _jspx_th_aui_fieldset$1group_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
        _jspx_th_aui_fieldset$1group_0.setMarkupView("lexicon");
        int _jspx_eval_aui_fieldset$1group_0 = _jspx_th_aui_fieldset$1group_0.doStartTag();
        if (_jspx_eval_aui_fieldset$1group_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          out.write("\n\t\t");
          //  aui:fieldset
          com.liferay.taglib.aui.FieldsetTag _jspx_th_aui_fieldset_0 = (com.liferay.taglib.aui.FieldsetTag) _jspx_tagPool_aui_fieldset.get(com.liferay.taglib.aui.FieldsetTag.class);
          _jspx_th_aui_fieldset_0.setPageContext(_jspx_page_context);
          _jspx_th_aui_fieldset_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset$1group_0);
          int _jspx_eval_aui_fieldset_0 = _jspx_th_aui_fieldset_0.doStartTag();
          if (_jspx_eval_aui_fieldset_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t\t");
            //  c:choose
            com.liferay.taglib.core.ChooseTag _jspx_th_c_choose_0 = (com.liferay.taglib.core.ChooseTag) _jspx_tagPool_c_choose.get(com.liferay.taglib.core.ChooseTag.class);
            _jspx_th_c_choose_0.setPageContext(_jspx_page_context);
            _jspx_th_c_choose_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_0);
            int _jspx_eval_c_choose_0 = _jspx_th_c_choose_0.doStartTag();
            if (_jspx_eval_c_choose_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\t\t\t\t");
              //  c:when
              com.liferay.taglib.core.WhenTag _jspx_th_c_when_0 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
              _jspx_th_c_when_0.setPageContext(_jspx_page_context);
              _jspx_th_c_when_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_0);
              _jspx_th_c_when_0.setTest( column != null );
              int _jspx_eval_c_when_0 = _jspx_th_c_when_0.doStartTag();
              if (_jspx_eval_c_when_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\t\t\t\t\t");
                //  aui:input
                com.liferay.taglib.aui.InputTag _jspx_th_aui_input_3 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
                _jspx_th_aui_input_3.setPageContext(_jspx_page_context);
                _jspx_th_aui_input_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_0);
                _jspx_th_aui_input_3.setName("name");
                _jspx_th_aui_input_3.setType("hidden");
                _jspx_th_aui_input_3.setValue( column.getName() );
                int _jspx_eval_aui_input_3 = _jspx_th_aui_input_3.doStartTag();
                if (_jspx_th_aui_input_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_3);
                  return;
                }
                _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_3);
                out.write("\n\n\t\t\t\t\t");
                //  aui:input
                com.liferay.taglib.aui.InputTag _jspx_th_aui_input_4 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_helpMessage_nobody.get(com.liferay.taglib.aui.InputTag.class);
                _jspx_th_aui_input_4.setPageContext(_jspx_page_context);
                _jspx_th_aui_input_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_0);
                _jspx_th_aui_input_4.setHelpMessage("custom-field-key-help");
                _jspx_th_aui_input_4.setName("key");
                _jspx_th_aui_input_4.setType("resource");
                _jspx_th_aui_input_4.setValue( column.getName() );
                int _jspx_eval_aui_input_4 = _jspx_th_aui_input_4.doStartTag();
                if (_jspx_th_aui_input_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_aui_input_value_type_name_helpMessage_nobody.reuse(_jspx_th_aui_input_4);
                  return;
                }
                _jspx_tagPool_aui_input_value_type_name_helpMessage_nobody.reuse(_jspx_th_aui_input_4);
                out.write("\n\t\t\t\t");
              }
              if (_jspx_th_c_when_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_0);
                return;
              }
              _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_0);
              out.write("\n\t\t\t\t");
              //  c:otherwise
              com.liferay.taglib.core.OtherwiseTag _jspx_th_c_otherwise_0 = (com.liferay.taglib.core.OtherwiseTag) _jspx_tagPool_c_otherwise.get(com.liferay.taglib.core.OtherwiseTag.class);
              _jspx_th_c_otherwise_0.setPageContext(_jspx_page_context);
              _jspx_th_c_otherwise_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_0);
              int _jspx_eval_c_otherwise_0 = _jspx_th_c_otherwise_0.doStartTag();
              if (_jspx_eval_c_otherwise_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\t\t\t\t\t");
                //  aui:input
                com.liferay.taglib.aui.InputTag _jspx_th_aui_input_5 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_name_label_helpMessage_autoFocus_nobody.get(com.liferay.taglib.aui.InputTag.class);
                _jspx_th_aui_input_5.setPageContext(_jspx_page_context);
                _jspx_th_aui_input_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_0);
                _jspx_th_aui_input_5.setAutoFocus( windowState.equals(WindowState.MAXIMIZED) );
                _jspx_th_aui_input_5.setHelpMessage("custom-field-key-help");
                _jspx_th_aui_input_5.setLabel("key");
                _jspx_th_aui_input_5.setName("name");
                int _jspx_eval_aui_input_5 = _jspx_th_aui_input_5.doStartTag();
                if (_jspx_th_aui_input_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_aui_input_name_label_helpMessage_autoFocus_nobody.reuse(_jspx_th_aui_input_5);
                  return;
                }
                _jspx_tagPool_aui_input_name_label_helpMessage_autoFocus_nobody.reuse(_jspx_th_aui_input_5);
                out.write("\n\t\t\t\t");
              }
              if (_jspx_th_c_otherwise_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_0);
                return;
              }
              _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_0);
              out.write("\n\t\t\t");
            }
            if (_jspx_th_c_choose_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_0);
              return;
            }
            _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_0);
            out.write("\n\n\t\t\t");
            //  c:choose
            com.liferay.taglib.core.ChooseTag _jspx_th_c_choose_1 = (com.liferay.taglib.core.ChooseTag) _jspx_tagPool_c_choose.get(com.liferay.taglib.core.ChooseTag.class);
            _jspx_th_c_choose_1.setPageContext(_jspx_page_context);
            _jspx_th_c_choose_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_0);
            int _jspx_eval_c_choose_1 = _jspx_th_c_choose_1.doStartTag();
            if (_jspx_eval_c_choose_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\t\t\t\t");
              //  c:when
              com.liferay.taglib.core.WhenTag _jspx_th_c_when_1 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
              _jspx_th_c_when_1.setPageContext(_jspx_page_context);
              _jspx_th_c_when_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_1);
              _jspx_th_c_when_1.setTest( column != null );
              int _jspx_eval_c_when_1 = _jspx_th_c_when_1.doStartTag();
              if (_jspx_eval_c_when_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\t\t\t\t\t");
                //  aui:input
                com.liferay.taglib.aui.InputTag _jspx_th_aui_input_6 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
                _jspx_th_aui_input_6.setPageContext(_jspx_page_context);
                _jspx_th_aui_input_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_1);
                _jspx_th_aui_input_6.setName("type");
                _jspx_th_aui_input_6.setType("hidden");
                _jspx_th_aui_input_6.setValue( type );
                int _jspx_eval_aui_input_6 = _jspx_th_aui_input_6.doStartTag();
                if (_jspx_th_aui_input_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_6);
                  return;
                }
                _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_6);
                out.write("\n\n\t\t\t\t\t");
                //  aui:input
                com.liferay.taglib.aui.InputTag _jspx_th_aui_input_7 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
                _jspx_th_aui_input_7.setPageContext(_jspx_page_context);
                _jspx_th_aui_input_7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_1);
                _jspx_th_aui_input_7.setName("type");
                _jspx_th_aui_input_7.setType("resource");
                _jspx_th_aui_input_7.setValue( LanguageUtil.get(request, ExpandoColumnConstants.getTypeLabel(type)) );
                int _jspx_eval_aui_input_7 = _jspx_th_aui_input_7.doStartTag();
                if (_jspx_th_aui_input_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_7);
                  return;
                }
                _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_7);
                out.write("\n\t\t\t\t");
              }
              if (_jspx_th_c_when_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_1);
                return;
              }
              _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_1);
              out.write("\n\t\t\t\t");
              //  c:otherwise
              com.liferay.taglib.core.OtherwiseTag _jspx_th_c_otherwise_1 = (com.liferay.taglib.core.OtherwiseTag) _jspx_tagPool_c_otherwise.get(com.liferay.taglib.core.OtherwiseTag.class);
              _jspx_th_c_otherwise_1.setPageContext(_jspx_page_context);
              _jspx_th_c_otherwise_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_1);
              int _jspx_eval_c_otherwise_1 = _jspx_th_c_otherwise_1.doStartTag();
              if (_jspx_eval_c_otherwise_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\t\t\t\t\t");
                //  aui:select
                com.liferay.taglib.aui.SelectTag _jspx_th_aui_select_0 = (com.liferay.taglib.aui.SelectTag) _jspx_tagPool_aui_select_name_helpMessage.get(com.liferay.taglib.aui.SelectTag.class);
                _jspx_th_aui_select_0.setPageContext(_jspx_page_context);
                _jspx_th_aui_select_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_1);
                _jspx_th_aui_select_0.setHelpMessage("custom-field-type-help");
                _jspx_th_aui_select_0.setName("type");
                int _jspx_eval_aui_select_0 = _jspx_th_aui_select_0.doStartTag();
                if (_jspx_eval_aui_select_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  if (_jspx_eval_aui_select_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                    out = _jspx_page_context.pushBody();
                    _jspx_th_aui_select_0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                    _jspx_th_aui_select_0.doInitBody();
                  }
                  do {
                    out.write("\n\t\t\t\t\t\t<optgroup label=\"");
                    if (_jspx_meth_liferay$1ui_message_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_select_0, _jspx_page_context))
                      return;
                    out.write("\">\n\t\t\t\t\t\t\t");
                    if (_jspx_meth_aui_option_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_select_0, _jspx_page_context))
                      return;
                    out.write("\n\t\t\t\t\t\t\t");
                    if (_jspx_meth_aui_option_1((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_select_0, _jspx_page_context))
                      return;
                    out.write("\n\t\t\t\t\t\t\t");
                    if (_jspx_meth_aui_option_2((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_select_0, _jspx_page_context))
                      return;
                    out.write("\n\t\t\t\t\t\t\t");
                    if (_jspx_meth_aui_option_3((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_select_0, _jspx_page_context))
                      return;
                    out.write("\n\t\t\t\t\t\t\t");
                    if (_jspx_meth_aui_option_4((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_select_0, _jspx_page_context))
                      return;
                    out.write("\n\t\t\t\t\t\t\t");
                    if (_jspx_meth_aui_option_5((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_select_0, _jspx_page_context))
                      return;
                    out.write("\n\t\t\t\t\t\t\t");
                    //  aui:option
                    com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_6 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_value_selected_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
                    _jspx_th_aui_option_6.setPageContext(_jspx_page_context);
                    _jspx_th_aui_option_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_0);
                    _jspx_th_aui_option_6.setLabel(new String("text-field-indexed"));
                    _jspx_th_aui_option_6.setSelected( true );
                    _jspx_th_aui_option_6.setValue(new String("PresetTextFieldIndexed()"));
                    int _jspx_eval_aui_option_6 = _jspx_th_aui_option_6.doStartTag();
                    if (_jspx_th_aui_option_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_aui_option_value_selected_label_nobody.reuse(_jspx_th_aui_option_6);
                      return;
                    }
                    _jspx_tagPool_aui_option_value_selected_label_nobody.reuse(_jspx_th_aui_option_6);
                    out.write("\n\t\t\t\t\t\t</optgroup>\n\n\t\t\t\t\t\t<optgroup label=\"");
                    if (_jspx_meth_liferay$1ui_message_1((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_select_0, _jspx_page_context))
                      return;
                    out.write("\">\n\n\t\t\t\t\t\t\t");

							for (int curType : ExpandoColumnConstants.TYPES) {
								if ((curType == ExpandoColumnConstants.BOOLEAN_ARRAY) || (curType == ExpandoColumnConstants.DATE_ARRAY) || (curType == ExpandoColumnConstants.STRING_ARRAY_LOCALIZED)) {
									continue;
								}
							
                    out.write("\n\n\t\t\t\t\t\t\t\t");
                    //  aui:option
                    com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_7 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_value_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
                    _jspx_th_aui_option_7.setPageContext(_jspx_page_context);
                    _jspx_th_aui_option_7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_0);
                    _jspx_th_aui_option_7.setLabel( ExpandoColumnConstants.getTypeLabel(curType) );
                    _jspx_th_aui_option_7.setValue( curType );
                    int _jspx_eval_aui_option_7 = _jspx_th_aui_option_7.doStartTag();
                    if (_jspx_th_aui_option_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_aui_option_value_label_nobody.reuse(_jspx_th_aui_option_7);
                      return;
                    }
                    _jspx_tagPool_aui_option_value_label_nobody.reuse(_jspx_th_aui_option_7);
                    out.write("\n\n\t\t\t\t\t\t\t");

							}
							
                    out.write("\n\n\t\t\t\t\t\t</optgroup>\n\t\t\t\t\t");
                    int evalDoAfterBody = _jspx_th_aui_select_0.doAfterBody();
                    if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                      break;
                  } while (true);
                  if (_jspx_eval_aui_select_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                    out = _jspx_page_context.popBody();
                }
                if (_jspx_th_aui_select_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_aui_select_name_helpMessage.reuse(_jspx_th_aui_select_0);
                  return;
                }
                _jspx_tagPool_aui_select_name_helpMessage.reuse(_jspx_th_aui_select_0);
                out.write("\n\t\t\t\t");
              }
              if (_jspx_th_c_otherwise_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_1);
                return;
              }
              _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_1);
              out.write("\n\t\t\t");
            }
            if (_jspx_th_c_choose_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_1);
              return;
            }
            _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_1);
            out.write("\n\n\t\t\t");
            //  c:if
            com.liferay.taglib.core.IfTag _jspx_th_c_if_0 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
            _jspx_th_c_if_0.setPageContext(_jspx_page_context);
            _jspx_th_c_if_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_0);
            _jspx_th_c_if_0.setTest( column != null );
            int _jspx_eval_c_if_0 = _jspx_th_c_if_0.doStartTag();
            if (_jspx_eval_c_if_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\t\t\t\t");
              //  c:choose
              com.liferay.taglib.core.ChooseTag _jspx_th_c_choose_2 = (com.liferay.taglib.core.ChooseTag) _jspx_tagPool_c_choose.get(com.liferay.taglib.core.ChooseTag.class);
              _jspx_th_c_choose_2.setPageContext(_jspx_page_context);
              _jspx_th_c_choose_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_0);
              int _jspx_eval_c_choose_2 = _jspx_th_c_choose_2.doStartTag();
              if (_jspx_eval_c_choose_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\t\t\t\t\t");
                //  c:when
                com.liferay.taglib.core.WhenTag _jspx_th_c_when_2 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
                _jspx_th_c_when_2.setPageContext(_jspx_page_context);
                _jspx_th_c_when_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_2);
                _jspx_th_c_when_2.setTest( type == ExpandoColumnConstants.BOOLEAN );
                int _jspx_eval_c_when_2 = _jspx_th_c_when_2.doStartTag();
                if (_jspx_eval_c_when_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  out.write("\n\n\t\t\t\t\t\t");

						boolean curValue = ((Boolean)defaultValue).booleanValue();
						
                  out.write("\n\n\t\t\t\t\t\t");
                  //  aui:select
                  com.liferay.taglib.aui.SelectTag _jspx_th_aui_select_1 = (com.liferay.taglib.aui.SelectTag) _jspx_tagPool_aui_select_name.get(com.liferay.taglib.aui.SelectTag.class);
                  _jspx_th_aui_select_1.setPageContext(_jspx_page_context);
                  _jspx_th_aui_select_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_2);
                  _jspx_th_aui_select_1.setName("defaultValue");
                  int _jspx_eval_aui_select_1 = _jspx_th_aui_select_1.doStartTag();
                  if (_jspx_eval_aui_select_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    if (_jspx_eval_aui_select_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                      out = _jspx_page_context.pushBody();
                      _jspx_th_aui_select_1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                      _jspx_th_aui_select_1.doInitBody();
                    }
                    do {
                      out.write("\n\t\t\t\t\t\t\t");
                      //  aui:option
                      com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_8 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_value_selected_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
                      _jspx_th_aui_option_8.setPageContext(_jspx_page_context);
                      _jspx_th_aui_option_8.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_1);
                      _jspx_th_aui_option_8.setLabel( true );
                      _jspx_th_aui_option_8.setSelected( curValue );
                      _jspx_th_aui_option_8.setValue( true );
                      int _jspx_eval_aui_option_8 = _jspx_th_aui_option_8.doStartTag();
                      if (_jspx_th_aui_option_8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_aui_option_value_selected_label_nobody.reuse(_jspx_th_aui_option_8);
                        return;
                      }
                      _jspx_tagPool_aui_option_value_selected_label_nobody.reuse(_jspx_th_aui_option_8);
                      out.write("\n\t\t\t\t\t\t\t");
                      //  aui:option
                      com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_9 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_value_selected_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
                      _jspx_th_aui_option_9.setPageContext(_jspx_page_context);
                      _jspx_th_aui_option_9.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_1);
                      _jspx_th_aui_option_9.setLabel( false );
                      _jspx_th_aui_option_9.setSelected( !curValue );
                      _jspx_th_aui_option_9.setValue( false );
                      int _jspx_eval_aui_option_9 = _jspx_th_aui_option_9.doStartTag();
                      if (_jspx_th_aui_option_9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_aui_option_value_selected_label_nobody.reuse(_jspx_th_aui_option_9);
                        return;
                      }
                      _jspx_tagPool_aui_option_value_selected_label_nobody.reuse(_jspx_th_aui_option_9);
                      out.write("\n\t\t\t\t\t\t");
                      int evalDoAfterBody = _jspx_th_aui_select_1.doAfterBody();
                      if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                        break;
                    } while (true);
                    if (_jspx_eval_aui_select_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                      out = _jspx_page_context.popBody();
                  }
                  if (_jspx_th_aui_select_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_aui_select_name.reuse(_jspx_th_aui_select_1);
                    return;
                  }
                  _jspx_tagPool_aui_select_name.reuse(_jspx_th_aui_select_1);
                  out.write("\n\t\t\t\t\t");
                }
                if (_jspx_th_c_when_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_2);
                  return;
                }
                _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_2);
                out.write("\n\t\t\t\t\t");
                //  c:when
                com.liferay.taglib.core.WhenTag _jspx_th_c_when_3 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
                _jspx_th_c_when_3.setPageContext(_jspx_page_context);
                _jspx_th_c_when_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_2);
                _jspx_th_c_when_3.setTest( type == ExpandoColumnConstants.BOOLEAN_ARRAY );
                int _jspx_eval_c_when_3 = _jspx_th_c_when_3.doStartTag();
                if (_jspx_eval_c_when_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  out.write("\n\t\t\t\t\t");
                }
                if (_jspx_th_c_when_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_3);
                  return;
                }
                _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_3);
                out.write("\n\t\t\t\t\t");
                //  c:when
                com.liferay.taglib.core.WhenTag _jspx_th_c_when_4 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
                _jspx_th_c_when_4.setPageContext(_jspx_page_context);
                _jspx_th_c_when_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_2);
                _jspx_th_c_when_4.setTest( type == ExpandoColumnConstants.DATE );
                int _jspx_eval_c_when_4 = _jspx_th_c_when_4.doStartTag();
                if (_jspx_eval_c_when_4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  out.write("\n\n\t\t\t\t\t\t");

						Calendar defaultValueDate = CalendarFactoryUtil.getCalendar(timeZone, locale);

						if (defaultValue != null) {
							defaultValueDate.setTime((Date)defaultValue);
						}
						
                  out.write("\n\n\t\t\t\t\t\t");
                  //  aui:field-wrapper
                  com.liferay.taglib.aui.FieldWrapperTag _jspx_th_aui_field$1wrapper_0 = (com.liferay.taglib.aui.FieldWrapperTag) _jspx_tagPool_aui_field$1wrapper_label.get(com.liferay.taglib.aui.FieldWrapperTag.class);
                  _jspx_th_aui_field$1wrapper_0.setPageContext(_jspx_page_context);
                  _jspx_th_aui_field$1wrapper_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_4);
                  _jspx_th_aui_field$1wrapper_0.setLabel("default-value");
                  int _jspx_eval_aui_field$1wrapper_0 = _jspx_th_aui_field$1wrapper_0.doStartTag();
                  if (_jspx_eval_aui_field$1wrapper_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    out.write("\n\t\t\t\t\t\t\t");
                    //  liferay-ui:input-date
                    com.liferay.taglib.ui.InputDateTag _jspx_th_liferay$1ui_input$1date_0 = (com.liferay.taglib.ui.InputDateTag) _jspx_tagPool_liferay$1ui_input$1date_yearValue_yearParam_monthValue_monthParam_firstDayOfWeek_disabled_dayValue_dayParam_nobody.get(com.liferay.taglib.ui.InputDateTag.class);
                    _jspx_th_liferay$1ui_input$1date_0.setPageContext(_jspx_page_context);
                    _jspx_th_liferay$1ui_input$1date_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_field$1wrapper_0);
                    _jspx_th_liferay$1ui_input$1date_0.setDayParam("defaultValueDay");
                    _jspx_th_liferay$1ui_input$1date_0.setDayValue( defaultValueDate.get(Calendar.DATE) );
                    _jspx_th_liferay$1ui_input$1date_0.setDisabled( false );
                    _jspx_th_liferay$1ui_input$1date_0.setFirstDayOfWeek( defaultValueDate.getFirstDayOfWeek() - 1 );
                    _jspx_th_liferay$1ui_input$1date_0.setMonthParam("defaultValueMonth");
                    _jspx_th_liferay$1ui_input$1date_0.setMonthValue( defaultValueDate.get(Calendar.MONTH) );
                    _jspx_th_liferay$1ui_input$1date_0.setYearParam("defaultValueYear");
                    _jspx_th_liferay$1ui_input$1date_0.setYearValue( defaultValueDate.get(Calendar.YEAR) );
                    int _jspx_eval_liferay$1ui_input$1date_0 = _jspx_th_liferay$1ui_input$1date_0.doStartTag();
                    if (_jspx_th_liferay$1ui_input$1date_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_liferay$1ui_input$1date_yearValue_yearParam_monthValue_monthParam_firstDayOfWeek_disabled_dayValue_dayParam_nobody.reuse(_jspx_th_liferay$1ui_input$1date_0);
                      return;
                    }
                    _jspx_tagPool_liferay$1ui_input$1date_yearValue_yearParam_monthValue_monthParam_firstDayOfWeek_disabled_dayValue_dayParam_nobody.reuse(_jspx_th_liferay$1ui_input$1date_0);
                    out.write("\n\n\t\t\t\t\t\t\t");
                    //  liferay-ui:input-time
                    com.liferay.taglib.ui.InputTimeTag _jspx_th_liferay$1ui_input$1time_0 = (com.liferay.taglib.ui.InputTimeTag) _jspx_tagPool_liferay$1ui_input$1time_minuteValue_minuteParam_hourValue_hourParam_disabled_amPmValue_amPmParam_nobody.get(com.liferay.taglib.ui.InputTimeTag.class);
                    _jspx_th_liferay$1ui_input$1time_0.setPageContext(_jspx_page_context);
                    _jspx_th_liferay$1ui_input$1time_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_field$1wrapper_0);
                    _jspx_th_liferay$1ui_input$1time_0.setAmPmParam("defaultValueAmPm");
                    _jspx_th_liferay$1ui_input$1time_0.setAmPmValue( defaultValueDate.get(Calendar.AM_PM) );
                    _jspx_th_liferay$1ui_input$1time_0.setDisabled( false );
                    _jspx_th_liferay$1ui_input$1time_0.setHourParam("defaultValueHour");
                    _jspx_th_liferay$1ui_input$1time_0.setHourValue( defaultValueDate.get(Calendar.HOUR) );
                    _jspx_th_liferay$1ui_input$1time_0.setMinuteParam("defaultValueMinute");
                    _jspx_th_liferay$1ui_input$1time_0.setMinuteValue( defaultValueDate.get(Calendar.MINUTE) );
                    int _jspx_eval_liferay$1ui_input$1time_0 = _jspx_th_liferay$1ui_input$1time_0.doStartTag();
                    if (_jspx_th_liferay$1ui_input$1time_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_liferay$1ui_input$1time_minuteValue_minuteParam_hourValue_hourParam_disabled_amPmValue_amPmParam_nobody.reuse(_jspx_th_liferay$1ui_input$1time_0);
                      return;
                    }
                    _jspx_tagPool_liferay$1ui_input$1time_minuteValue_minuteParam_hourValue_hourParam_disabled_amPmValue_amPmParam_nobody.reuse(_jspx_th_liferay$1ui_input$1time_0);
                    out.write("\n\t\t\t\t\t\t");
                  }
                  if (_jspx_th_aui_field$1wrapper_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_aui_field$1wrapper_label.reuse(_jspx_th_aui_field$1wrapper_0);
                    return;
                  }
                  _jspx_tagPool_aui_field$1wrapper_label.reuse(_jspx_th_aui_field$1wrapper_0);
                  out.write("\n\t\t\t\t\t");
                }
                if (_jspx_th_c_when_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_4);
                  return;
                }
                _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_4);
                out.write("\n\t\t\t\t\t");
                //  c:when
                com.liferay.taglib.core.WhenTag _jspx_th_c_when_5 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
                _jspx_th_c_when_5.setPageContext(_jspx_page_context);
                _jspx_th_c_when_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_2);
                _jspx_th_c_when_5.setTest( type == ExpandoColumnConstants.DATE_ARRAY );
                int _jspx_eval_c_when_5 = _jspx_th_c_when_5.doStartTag();
                if (_jspx_eval_c_when_5 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  out.write("\n\t\t\t\t\t");
                }
                if (_jspx_th_c_when_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_5);
                  return;
                }
                _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_5);
                out.write("\n\t\t\t\t\t");
                //  c:when
                com.liferay.taglib.core.WhenTag _jspx_th_c_when_6 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
                _jspx_th_c_when_6.setPageContext(_jspx_page_context);
                _jspx_th_c_when_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_2);
                _jspx_th_c_when_6.setTest( type == ExpandoColumnConstants.DOUBLE_ARRAY );
                int _jspx_eval_c_when_6 = _jspx_th_c_when_6.doStartTag();
                if (_jspx_eval_c_when_6 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  out.write("\n\t\t\t\t\t\t");
                  //  aui:input
                  com.liferay.taglib.aui.InputTag _jspx_th_aui_input_8 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_required_name_label_helpMessage_cssClass_nobody.get(com.liferay.taglib.aui.InputTag.class);
                  _jspx_th_aui_input_8.setPageContext(_jspx_page_context);
                  _jspx_th_aui_input_8.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_6);
                  _jspx_th_aui_input_8.setCssClass("lfr-textarea-container");
                  _jspx_th_aui_input_8.setHelpMessage("enter-one-value-per-line");
                  _jspx_th_aui_input_8.setLabel("values");
                  _jspx_th_aui_input_8.setName("defaultValue");
                  _jspx_th_aui_input_8.setRequired( true );
                  _jspx_th_aui_input_8.setType("textarea");
                  _jspx_th_aui_input_8.setValue( StringUtil.merge((double[])defaultValue, StringPool.NEW_LINE) );
                  int _jspx_eval_aui_input_8 = _jspx_th_aui_input_8.doStartTag();
                  if (_jspx_th_aui_input_8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_aui_input_value_type_required_name_label_helpMessage_cssClass_nobody.reuse(_jspx_th_aui_input_8);
                    return;
                  }
                  _jspx_tagPool_aui_input_value_type_required_name_label_helpMessage_cssClass_nobody.reuse(_jspx_th_aui_input_8);
                  out.write("\n\t\t\t\t\t");
                }
                if (_jspx_th_c_when_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_6);
                  return;
                }
                _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_6);
                out.write("\n\t\t\t\t\t");
                //  c:when
                com.liferay.taglib.core.WhenTag _jspx_th_c_when_7 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
                _jspx_th_c_when_7.setPageContext(_jspx_page_context);
                _jspx_th_c_when_7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_2);
                _jspx_th_c_when_7.setTest( type == ExpandoColumnConstants.FLOAT_ARRAY );
                int _jspx_eval_c_when_7 = _jspx_th_c_when_7.doStartTag();
                if (_jspx_eval_c_when_7 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  out.write("\n\t\t\t\t\t\t");
                  //  aui:input
                  com.liferay.taglib.aui.InputTag _jspx_th_aui_input_9 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_required_name_label_helpMessage_cssClass_nobody.get(com.liferay.taglib.aui.InputTag.class);
                  _jspx_th_aui_input_9.setPageContext(_jspx_page_context);
                  _jspx_th_aui_input_9.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_7);
                  _jspx_th_aui_input_9.setCssClass("lfr-textarea-container");
                  _jspx_th_aui_input_9.setHelpMessage("enter-one-value-per-line");
                  _jspx_th_aui_input_9.setLabel("values");
                  _jspx_th_aui_input_9.setName("defaultValue");
                  _jspx_th_aui_input_9.setRequired( true );
                  _jspx_th_aui_input_9.setType("textarea");
                  _jspx_th_aui_input_9.setValue( StringUtil.merge((float[])defaultValue, StringPool.NEW_LINE) );
                  int _jspx_eval_aui_input_9 = _jspx_th_aui_input_9.doStartTag();
                  if (_jspx_th_aui_input_9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_aui_input_value_type_required_name_label_helpMessage_cssClass_nobody.reuse(_jspx_th_aui_input_9);
                    return;
                  }
                  _jspx_tagPool_aui_input_value_type_required_name_label_helpMessage_cssClass_nobody.reuse(_jspx_th_aui_input_9);
                  out.write("\n\t\t\t\t\t");
                }
                if (_jspx_th_c_when_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_7);
                  return;
                }
                _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_7);
                out.write("\n\t\t\t\t\t");
                //  c:when
                com.liferay.taglib.core.WhenTag _jspx_th_c_when_8 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
                _jspx_th_c_when_8.setPageContext(_jspx_page_context);
                _jspx_th_c_when_8.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_2);
                _jspx_th_c_when_8.setTest( type == ExpandoColumnConstants.INTEGER_ARRAY );
                int _jspx_eval_c_when_8 = _jspx_th_c_when_8.doStartTag();
                if (_jspx_eval_c_when_8 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  out.write("\n\t\t\t\t\t\t");
                  //  aui:input
                  com.liferay.taglib.aui.InputTag _jspx_th_aui_input_10 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_required_name_label_helpMessage_cssClass_nobody.get(com.liferay.taglib.aui.InputTag.class);
                  _jspx_th_aui_input_10.setPageContext(_jspx_page_context);
                  _jspx_th_aui_input_10.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_8);
                  _jspx_th_aui_input_10.setCssClass("lfr-textarea-container");
                  _jspx_th_aui_input_10.setHelpMessage("enter-one-value-per-line");
                  _jspx_th_aui_input_10.setLabel("values");
                  _jspx_th_aui_input_10.setName("defaultValue");
                  _jspx_th_aui_input_10.setRequired( true );
                  _jspx_th_aui_input_10.setType("textarea");
                  _jspx_th_aui_input_10.setValue( StringUtil.merge((int[])defaultValue, StringPool.NEW_LINE) );
                  int _jspx_eval_aui_input_10 = _jspx_th_aui_input_10.doStartTag();
                  if (_jspx_th_aui_input_10.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_aui_input_value_type_required_name_label_helpMessage_cssClass_nobody.reuse(_jspx_th_aui_input_10);
                    return;
                  }
                  _jspx_tagPool_aui_input_value_type_required_name_label_helpMessage_cssClass_nobody.reuse(_jspx_th_aui_input_10);
                  out.write("\n\t\t\t\t\t");
                }
                if (_jspx_th_c_when_8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_8);
                  return;
                }
                _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_8);
                out.write("\n\t\t\t\t\t");
                //  c:when
                com.liferay.taglib.core.WhenTag _jspx_th_c_when_9 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
                _jspx_th_c_when_9.setPageContext(_jspx_page_context);
                _jspx_th_c_when_9.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_2);
                _jspx_th_c_when_9.setTest( type == ExpandoColumnConstants.LONG_ARRAY );
                int _jspx_eval_c_when_9 = _jspx_th_c_when_9.doStartTag();
                if (_jspx_eval_c_when_9 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  out.write("\n\t\t\t\t\t\t");
                  //  aui:input
                  com.liferay.taglib.aui.InputTag _jspx_th_aui_input_11 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_required_name_label_helpMessage_cssClass_nobody.get(com.liferay.taglib.aui.InputTag.class);
                  _jspx_th_aui_input_11.setPageContext(_jspx_page_context);
                  _jspx_th_aui_input_11.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_9);
                  _jspx_th_aui_input_11.setCssClass("lfr-textarea-container");
                  _jspx_th_aui_input_11.setHelpMessage("enter-one-value-per-line");
                  _jspx_th_aui_input_11.setLabel("values");
                  _jspx_th_aui_input_11.setName("defaultValue");
                  _jspx_th_aui_input_11.setRequired( true );
                  _jspx_th_aui_input_11.setType("textarea");
                  _jspx_th_aui_input_11.setValue( StringUtil.merge((long[])defaultValue, StringPool.NEW_LINE) );
                  int _jspx_eval_aui_input_11 = _jspx_th_aui_input_11.doStartTag();
                  if (_jspx_th_aui_input_11.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_aui_input_value_type_required_name_label_helpMessage_cssClass_nobody.reuse(_jspx_th_aui_input_11);
                    return;
                  }
                  _jspx_tagPool_aui_input_value_type_required_name_label_helpMessage_cssClass_nobody.reuse(_jspx_th_aui_input_11);
                  out.write("\n\t\t\t\t\t");
                }
                if (_jspx_th_c_when_9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_9);
                  return;
                }
                _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_9);
                out.write("\n\t\t\t\t\t");
                //  c:when
                com.liferay.taglib.core.WhenTag _jspx_th_c_when_10 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
                _jspx_th_c_when_10.setPageContext(_jspx_page_context);
                _jspx_th_c_when_10.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_2);
                _jspx_th_c_when_10.setTest( type == ExpandoColumnConstants.NUMBER_ARRAY );
                int _jspx_eval_c_when_10 = _jspx_th_c_when_10.doStartTag();
                if (_jspx_eval_c_when_10 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  out.write("\n\t\t\t\t\t\t");
                  //  aui:input
                  com.liferay.taglib.aui.InputTag _jspx_th_aui_input_12 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_required_name_label_helpMessage_cssClass_nobody.get(com.liferay.taglib.aui.InputTag.class);
                  _jspx_th_aui_input_12.setPageContext(_jspx_page_context);
                  _jspx_th_aui_input_12.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_10);
                  _jspx_th_aui_input_12.setCssClass("lfr-textarea-container");
                  _jspx_th_aui_input_12.setHelpMessage("enter-one-value-per-line");
                  _jspx_th_aui_input_12.setLabel("values");
                  _jspx_th_aui_input_12.setName("defaultValue");
                  _jspx_th_aui_input_12.setRequired( true );
                  _jspx_th_aui_input_12.setType("textarea");
                  _jspx_th_aui_input_12.setValue( StringUtil.merge((Number[])defaultValue, StringPool.NEW_LINE) );
                  int _jspx_eval_aui_input_12 = _jspx_th_aui_input_12.doStartTag();
                  if (_jspx_th_aui_input_12.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_aui_input_value_type_required_name_label_helpMessage_cssClass_nobody.reuse(_jspx_th_aui_input_12);
                    return;
                  }
                  _jspx_tagPool_aui_input_value_type_required_name_label_helpMessage_cssClass_nobody.reuse(_jspx_th_aui_input_12);
                  out.write("\n\t\t\t\t\t");
                }
                if (_jspx_th_c_when_10.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_10);
                  return;
                }
                _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_10);
                out.write("\n\t\t\t\t\t");
                //  c:when
                com.liferay.taglib.core.WhenTag _jspx_th_c_when_11 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
                _jspx_th_c_when_11.setPageContext(_jspx_page_context);
                _jspx_th_c_when_11.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_2);
                _jspx_th_c_when_11.setTest( type == ExpandoColumnConstants.SHORT_ARRAY );
                int _jspx_eval_c_when_11 = _jspx_th_c_when_11.doStartTag();
                if (_jspx_eval_c_when_11 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  out.write("\n\t\t\t\t\t\t");
                  //  aui:input
                  com.liferay.taglib.aui.InputTag _jspx_th_aui_input_13 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_required_name_label_helpMessage_cssClass_nobody.get(com.liferay.taglib.aui.InputTag.class);
                  _jspx_th_aui_input_13.setPageContext(_jspx_page_context);
                  _jspx_th_aui_input_13.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_11);
                  _jspx_th_aui_input_13.setCssClass("lfr-textarea-container");
                  _jspx_th_aui_input_13.setHelpMessage("enter-one-value-per-line");
                  _jspx_th_aui_input_13.setLabel("values");
                  _jspx_th_aui_input_13.setName("defaultValue");
                  _jspx_th_aui_input_13.setRequired( true );
                  _jspx_th_aui_input_13.setType("textarea");
                  _jspx_th_aui_input_13.setValue( StringUtil.merge((short[])defaultValue, StringPool.NEW_LINE) );
                  int _jspx_eval_aui_input_13 = _jspx_th_aui_input_13.doStartTag();
                  if (_jspx_th_aui_input_13.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_aui_input_value_type_required_name_label_helpMessage_cssClass_nobody.reuse(_jspx_th_aui_input_13);
                    return;
                  }
                  _jspx_tagPool_aui_input_value_type_required_name_label_helpMessage_cssClass_nobody.reuse(_jspx_th_aui_input_13);
                  out.write("\n\t\t\t\t\t");
                }
                if (_jspx_th_c_when_11.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_11);
                  return;
                }
                _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_11);
                out.write("\n\t\t\t\t\t");
                //  c:when
                com.liferay.taglib.core.WhenTag _jspx_th_c_when_12 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
                _jspx_th_c_when_12.setPageContext(_jspx_page_context);
                _jspx_th_c_when_12.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_2);
                _jspx_th_c_when_12.setTest( type == ExpandoColumnConstants.STRING_ARRAY );
                int _jspx_eval_c_when_12 = _jspx_th_c_when_12.doStartTag();
                if (_jspx_eval_c_when_12 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  out.write("\n\t\t\t\t\t\t");
                  //  aui:input
                  com.liferay.taglib.aui.InputTag _jspx_th_aui_input_14 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_required_name_label_helpMessage_cssClass_nobody.get(com.liferay.taglib.aui.InputTag.class);
                  _jspx_th_aui_input_14.setPageContext(_jspx_page_context);
                  _jspx_th_aui_input_14.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_12);
                  _jspx_th_aui_input_14.setCssClass("lfr-textarea-container");
                  _jspx_th_aui_input_14.setHelpMessage("enter-one-value-per-line");
                  _jspx_th_aui_input_14.setLabel("values");
                  _jspx_th_aui_input_14.setName("defaultValue");
                  _jspx_th_aui_input_14.setRequired( true );
                  _jspx_th_aui_input_14.setType("textarea");
                  _jspx_th_aui_input_14.setValue( StringUtil.merge((String[])defaultValue, StringPool.NEW_LINE) );
                  int _jspx_eval_aui_input_14 = _jspx_th_aui_input_14.doStartTag();
                  if (_jspx_th_aui_input_14.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_aui_input_value_type_required_name_label_helpMessage_cssClass_nobody.reuse(_jspx_th_aui_input_14);
                    return;
                  }
                  _jspx_tagPool_aui_input_value_type_required_name_label_helpMessage_cssClass_nobody.reuse(_jspx_th_aui_input_14);
                  out.write("\n\t\t\t\t\t");
                }
                if (_jspx_th_c_when_12.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_12);
                  return;
                }
                _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_12);
                out.write("\n\t\t\t\t\t");
                //  c:when
                com.liferay.taglib.core.WhenTag _jspx_th_c_when_13 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
                _jspx_th_c_when_13.setPageContext(_jspx_page_context);
                _jspx_th_c_when_13.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_2);
                _jspx_th_c_when_13.setTest( type == ExpandoColumnConstants.STRING_LOCALIZED );
                int _jspx_eval_c_when_13 = _jspx_th_c_when_13.doStartTag();
                if (_jspx_eval_c_when_13 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  out.write("\n\n\t\t\t\t\t\t");

						String xml = StringPool.BLANK;

						if (defaultValue != null) {
							xml = LocalizationUtil.updateLocalization((Map<Locale, String>)defaultValue, StringPool.BLANK, "Data", LocaleUtil.toLanguageId(locale));
						}
						
                  out.write("\n\n\t\t\t\t\t\t");
                  //  aui:field-wrapper
                  com.liferay.taglib.aui.FieldWrapperTag _jspx_th_aui_field$1wrapper_1 = (com.liferay.taglib.aui.FieldWrapperTag) _jspx_tagPool_aui_field$1wrapper_label.get(com.liferay.taglib.aui.FieldWrapperTag.class);
                  _jspx_th_aui_field$1wrapper_1.setPageContext(_jspx_page_context);
                  _jspx_th_aui_field$1wrapper_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_13);
                  _jspx_th_aui_field$1wrapper_1.setLabel("default-value");
                  int _jspx_eval_aui_field$1wrapper_1 = _jspx_th_aui_field$1wrapper_1.doStartTag();
                  if (_jspx_eval_aui_field$1wrapper_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    out.write("\n\t\t\t\t\t\t\t");
                    //  liferay-ui:input-localized
                    com.liferay.taglib.ui.InputLocalizedTag _jspx_th_liferay$1ui_input$1localized_0 = (com.liferay.taglib.ui.InputLocalizedTag) _jspx_tagPool_liferay$1ui_input$1localized_xml_name_cssClass_nobody.get(com.liferay.taglib.ui.InputLocalizedTag.class);
                    _jspx_th_liferay$1ui_input$1localized_0.setPageContext(_jspx_page_context);
                    _jspx_th_liferay$1ui_input$1localized_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_field$1wrapper_1);
                    _jspx_th_liferay$1ui_input$1localized_0.setCssClass("lfr-input-text-container");
                    _jspx_th_liferay$1ui_input$1localized_0.setName("defaultValue");
                    _jspx_th_liferay$1ui_input$1localized_0.setXml( xml );
                    int _jspx_eval_liferay$1ui_input$1localized_0 = _jspx_th_liferay$1ui_input$1localized_0.doStartTag();
                    if (_jspx_th_liferay$1ui_input$1localized_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_liferay$1ui_input$1localized_xml_name_cssClass_nobody.reuse(_jspx_th_liferay$1ui_input$1localized_0);
                      return;
                    }
                    _jspx_tagPool_liferay$1ui_input$1localized_xml_name_cssClass_nobody.reuse(_jspx_th_liferay$1ui_input$1localized_0);
                    out.write("\n\t\t\t\t\t\t");
                  }
                  if (_jspx_th_aui_field$1wrapper_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_aui_field$1wrapper_label.reuse(_jspx_th_aui_field$1wrapper_1);
                    return;
                  }
                  _jspx_tagPool_aui_field$1wrapper_label.reuse(_jspx_th_aui_field$1wrapper_1);
                  out.write("\n\t\t\t\t\t");
                }
                if (_jspx_th_c_when_13.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_13);
                  return;
                }
                _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_13);
                out.write("\n\t\t\t\t\t");
                //  c:otherwise
                com.liferay.taglib.core.OtherwiseTag _jspx_th_c_otherwise_2 = (com.liferay.taglib.core.OtherwiseTag) _jspx_tagPool_c_otherwise.get(com.liferay.taglib.core.OtherwiseTag.class);
                _jspx_th_c_otherwise_2.setPageContext(_jspx_page_context);
                _jspx_th_c_otherwise_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_2);
                int _jspx_eval_c_otherwise_2 = _jspx_th_c_otherwise_2.doStartTag();
                if (_jspx_eval_c_otherwise_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  out.write("\n\t\t\t\t\t\t");
                  //  aui:input
                  com.liferay.taglib.aui.InputTag _jspx_th_aui_input_15 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_cssClass_nobody.get(com.liferay.taglib.aui.InputTag.class);
                  _jspx_th_aui_input_15.setPageContext(_jspx_page_context);
                  _jspx_th_aui_input_15.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_2);
                  _jspx_th_aui_input_15.setCssClass("lfr-input-text-container");
                  _jspx_th_aui_input_15.setName("defaultValue");
                  _jspx_th_aui_input_15.setType("text");
                  _jspx_th_aui_input_15.setValue( String.valueOf(defaultValue) );
                  int _jspx_eval_aui_input_15 = _jspx_th_aui_input_15.doStartTag();
                  if (_jspx_th_aui_input_15.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_aui_input_value_type_name_cssClass_nobody.reuse(_jspx_th_aui_input_15);
                    return;
                  }
                  _jspx_tagPool_aui_input_value_type_name_cssClass_nobody.reuse(_jspx_th_aui_input_15);
                  out.write("\n\t\t\t\t\t");
                }
                if (_jspx_th_c_otherwise_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_2);
                  return;
                }
                _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_2);
                out.write("\n\t\t\t\t");
              }
              if (_jspx_th_c_choose_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_2);
                return;
              }
              _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_2);
              out.write("\n\t\t\t");
            }
            if (_jspx_th_c_if_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
              return;
            }
            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
            out.write("\n\t\t");
          }
          if (_jspx_th_aui_fieldset_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_aui_fieldset.reuse(_jspx_th_aui_fieldset_0);
            return;
          }
          _jspx_tagPool_aui_fieldset.reuse(_jspx_th_aui_fieldset_0);
          out.write("\n\n\t\t");
          //  c:if
          com.liferay.taglib.core.IfTag _jspx_th_c_if_1 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
          _jspx_th_c_if_1.setPageContext(_jspx_page_context);
          _jspx_th_c_if_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset$1group_0);
          _jspx_th_c_if_1.setTest( column != null );
          int _jspx_eval_c_if_1 = _jspx_th_c_if_1.doStartTag();
          if (_jspx_eval_c_if_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t\t");
            //  aui:fieldset
            com.liferay.taglib.aui.FieldsetTag _jspx_th_aui_fieldset_1 = (com.liferay.taglib.aui.FieldsetTag) _jspx_tagPool_aui_fieldset_label.get(com.liferay.taglib.aui.FieldsetTag.class);
            _jspx_th_aui_fieldset_1.setPageContext(_jspx_page_context);
            _jspx_th_aui_fieldset_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_1);
            _jspx_th_aui_fieldset_1.setLabel("properties");
            int _jspx_eval_aui_fieldset_1 = _jspx_th_aui_fieldset_1.doStartTag();
            if (_jspx_eval_aui_fieldset_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\t\t\t\t");
              if (_jspx_meth_aui_input_16((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_fieldset_1, _jspx_page_context))
                return;
              out.write("\n\n\t\t\t\t");
              //  aui:select
              com.liferay.taglib.aui.SelectTag _jspx_th_aui_select_2 = (com.liferay.taglib.aui.SelectTag) _jspx_tagPool_aui_select_name_label_helpMessage.get(com.liferay.taglib.aui.SelectTag.class);
              _jspx_th_aui_select_2.setPageContext(_jspx_page_context);
              _jspx_th_aui_select_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_1);
              _jspx_th_aui_select_2.setHelpMessage("custom-field-hidden-help");
              _jspx_th_aui_select_2.setLabel("hidden");
              _jspx_th_aui_select_2.setName("Property--hidden--");
              int _jspx_eval_aui_select_2 = _jspx_th_aui_select_2.doStartTag();
              if (_jspx_eval_aui_select_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                if (_jspx_eval_aui_select_2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                  out = _jspx_page_context.pushBody();
                  _jspx_th_aui_select_2.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                  _jspx_th_aui_select_2.doInitBody();
                }
                do {
                  out.write("\n\t\t\t\t\t");
                  //  aui:option
                  com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_10 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_value_selected_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
                  _jspx_th_aui_option_10.setPageContext(_jspx_page_context);
                  _jspx_th_aui_option_10.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_2);
                  _jspx_th_aui_option_10.setLabel( true );
                  _jspx_th_aui_option_10.setSelected( propertyHidden );
                  _jspx_th_aui_option_10.setValue(new String("1"));
                  int _jspx_eval_aui_option_10 = _jspx_th_aui_option_10.doStartTag();
                  if (_jspx_th_aui_option_10.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_aui_option_value_selected_label_nobody.reuse(_jspx_th_aui_option_10);
                    return;
                  }
                  _jspx_tagPool_aui_option_value_selected_label_nobody.reuse(_jspx_th_aui_option_10);
                  out.write("\n\t\t\t\t\t");
                  //  aui:option
                  com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_11 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_value_selected_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
                  _jspx_th_aui_option_11.setPageContext(_jspx_page_context);
                  _jspx_th_aui_option_11.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_2);
                  _jspx_th_aui_option_11.setLabel( false );
                  _jspx_th_aui_option_11.setSelected( !propertyHidden );
                  _jspx_th_aui_option_11.setValue(new String("0"));
                  int _jspx_eval_aui_option_11 = _jspx_th_aui_option_11.doStartTag();
                  if (_jspx_th_aui_option_11.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_aui_option_value_selected_label_nobody.reuse(_jspx_th_aui_option_11);
                    return;
                  }
                  _jspx_tagPool_aui_option_value_selected_label_nobody.reuse(_jspx_th_aui_option_11);
                  out.write("\n\t\t\t\t");
                  int evalDoAfterBody = _jspx_th_aui_select_2.doAfterBody();
                  if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                    break;
                } while (true);
                if (_jspx_eval_aui_select_2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                  out = _jspx_page_context.popBody();
              }
              if (_jspx_th_aui_select_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_aui_select_name_label_helpMessage.reuse(_jspx_th_aui_select_2);
                return;
              }
              _jspx_tagPool_aui_select_name_label_helpMessage.reuse(_jspx_th_aui_select_2);
              out.write("\n\n\t\t\t\t");
              if (_jspx_meth_aui_input_17((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_fieldset_1, _jspx_page_context))
                return;
              out.write("\n\n\t\t\t\t");
              //  aui:select
              com.liferay.taglib.aui.SelectTag _jspx_th_aui_select_3 = (com.liferay.taglib.aui.SelectTag) _jspx_tagPool_aui_select_name_label_helpMessage.get(com.liferay.taglib.aui.SelectTag.class);
              _jspx_th_aui_select_3.setPageContext(_jspx_page_context);
              _jspx_th_aui_select_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_1);
              _jspx_th_aui_select_3.setHelpMessage("custom-field-visible-with-update-permission-help");
              _jspx_th_aui_select_3.setLabel("visible-with-update-permission");
              _jspx_th_aui_select_3.setName("Property--visible-with-update-permission--");
              int _jspx_eval_aui_select_3 = _jspx_th_aui_select_3.doStartTag();
              if (_jspx_eval_aui_select_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                if (_jspx_eval_aui_select_3 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                  out = _jspx_page_context.pushBody();
                  _jspx_th_aui_select_3.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                  _jspx_th_aui_select_3.doInitBody();
                }
                do {
                  out.write("\n\t\t\t\t\t");
                  //  aui:option
                  com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_12 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_value_selected_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
                  _jspx_th_aui_option_12.setPageContext(_jspx_page_context);
                  _jspx_th_aui_option_12.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_3);
                  _jspx_th_aui_option_12.setLabel( true );
                  _jspx_th_aui_option_12.setSelected( propertyVisibleWithUpdatePermission );
                  _jspx_th_aui_option_12.setValue(new String("1"));
                  int _jspx_eval_aui_option_12 = _jspx_th_aui_option_12.doStartTag();
                  if (_jspx_th_aui_option_12.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_aui_option_value_selected_label_nobody.reuse(_jspx_th_aui_option_12);
                    return;
                  }
                  _jspx_tagPool_aui_option_value_selected_label_nobody.reuse(_jspx_th_aui_option_12);
                  out.write("\n\t\t\t\t\t");
                  //  aui:option
                  com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_13 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_value_selected_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
                  _jspx_th_aui_option_13.setPageContext(_jspx_page_context);
                  _jspx_th_aui_option_13.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_3);
                  _jspx_th_aui_option_13.setLabel( false );
                  _jspx_th_aui_option_13.setSelected( !propertyVisibleWithUpdatePermission );
                  _jspx_th_aui_option_13.setValue(new String("0"));
                  int _jspx_eval_aui_option_13 = _jspx_th_aui_option_13.doStartTag();
                  if (_jspx_th_aui_option_13.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_aui_option_value_selected_label_nobody.reuse(_jspx_th_aui_option_13);
                    return;
                  }
                  _jspx_tagPool_aui_option_value_selected_label_nobody.reuse(_jspx_th_aui_option_13);
                  out.write("\n\t\t\t\t");
                  int evalDoAfterBody = _jspx_th_aui_select_3.doAfterBody();
                  if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                    break;
                } while (true);
                if (_jspx_eval_aui_select_3 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                  out = _jspx_page_context.popBody();
              }
              if (_jspx_th_aui_select_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_aui_select_name_label_helpMessage.reuse(_jspx_th_aui_select_3);
                return;
              }
              _jspx_tagPool_aui_select_name_label_helpMessage.reuse(_jspx_th_aui_select_3);
              out.write("\n\n\t\t\t\t");
              if (_jspx_meth_aui_input_18((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_fieldset_1, _jspx_page_context))
                return;
              out.write("\n\n\t\t\t\t");
              //  aui:select
              com.liferay.taglib.aui.SelectTag _jspx_th_aui_select_4 = (com.liferay.taglib.aui.SelectTag) _jspx_tagPool_aui_select_name_label_helpMessage.get(com.liferay.taglib.aui.SelectTag.class);
              _jspx_th_aui_select_4.setPageContext(_jspx_page_context);
              _jspx_th_aui_select_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_1);
              _jspx_th_aui_select_4.setHelpMessage("custom-field-index-type-help");
              _jspx_th_aui_select_4.setLabel("searchability");
              _jspx_th_aui_select_4.setName("Property--index-type--");
              int _jspx_eval_aui_select_4 = _jspx_th_aui_select_4.doStartTag();
              if (_jspx_eval_aui_select_4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                if (_jspx_eval_aui_select_4 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                  out = _jspx_page_context.pushBody();
                  _jspx_th_aui_select_4.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                  _jspx_th_aui_select_4.doInitBody();
                }
                do {
                  out.write("\n\t\t\t\t\t");
                  //  aui:option
                  com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_14 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_value_selected_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
                  _jspx_th_aui_option_14.setPageContext(_jspx_page_context);
                  _jspx_th_aui_option_14.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_4);
                  _jspx_th_aui_option_14.setLabel(new String("not-searchable"));
                  _jspx_th_aui_option_14.setSelected( propertyIndexType == ExpandoColumnConstants.INDEX_TYPE_NONE );
                  _jspx_th_aui_option_14.setValue( ExpandoColumnConstants.INDEX_TYPE_NONE );
                  int _jspx_eval_aui_option_14 = _jspx_th_aui_option_14.doStartTag();
                  if (_jspx_th_aui_option_14.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_aui_option_value_selected_label_nobody.reuse(_jspx_th_aui_option_14);
                    return;
                  }
                  _jspx_tagPool_aui_option_value_selected_label_nobody.reuse(_jspx_th_aui_option_14);
                  out.write("\n\n\t\t\t\t\t");
                  //  c:if
                  com.liferay.taglib.core.IfTag _jspx_th_c_if_2 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                  _jspx_th_c_if_2.setPageContext(_jspx_page_context);
                  _jspx_th_c_if_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_4);
                  _jspx_th_c_if_2.setTest( (type == ExpandoColumnConstants.STRING) || (type == ExpandoColumnConstants.STRING_ARRAY) );
                  int _jspx_eval_c_if_2 = _jspx_th_c_if_2.doStartTag();
                  if (_jspx_eval_c_if_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    out.write("\n\t\t\t\t\t\t");
                    //  aui:option
                    com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_15 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_value_selected_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
                    _jspx_th_aui_option_15.setPageContext(_jspx_page_context);
                    _jspx_th_aui_option_15.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_2);
                    _jspx_th_aui_option_15.setLabel(new String("as-text"));
                    _jspx_th_aui_option_15.setSelected( propertyIndexType == ExpandoColumnConstants.INDEX_TYPE_TEXT );
                    _jspx_th_aui_option_15.setValue( ExpandoColumnConstants.INDEX_TYPE_TEXT );
                    int _jspx_eval_aui_option_15 = _jspx_th_aui_option_15.doStartTag();
                    if (_jspx_th_aui_option_15.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_aui_option_value_selected_label_nobody.reuse(_jspx_th_aui_option_15);
                      return;
                    }
                    _jspx_tagPool_aui_option_value_selected_label_nobody.reuse(_jspx_th_aui_option_15);
                    out.write("\n\t\t\t\t\t");
                  }
                  if (_jspx_th_c_if_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_2);
                    return;
                  }
                  _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_2);
                  out.write("\n\n\t\t\t\t\t");
                  //  aui:option
                  com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_16 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_value_selected_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
                  _jspx_th_aui_option_16.setPageContext(_jspx_page_context);
                  _jspx_th_aui_option_16.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_4);
                  _jspx_th_aui_option_16.setLabel(new String("as-keyword"));
                  _jspx_th_aui_option_16.setSelected( propertyIndexType == ExpandoColumnConstants.INDEX_TYPE_KEYWORD );
                  _jspx_th_aui_option_16.setValue( ExpandoColumnConstants.INDEX_TYPE_KEYWORD );
                  int _jspx_eval_aui_option_16 = _jspx_th_aui_option_16.doStartTag();
                  if (_jspx_th_aui_option_16.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_aui_option_value_selected_label_nobody.reuse(_jspx_th_aui_option_16);
                    return;
                  }
                  _jspx_tagPool_aui_option_value_selected_label_nobody.reuse(_jspx_th_aui_option_16);
                  out.write("\n\t\t\t\t");
                  int evalDoAfterBody = _jspx_th_aui_select_4.doAfterBody();
                  if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                    break;
                } while (true);
                if (_jspx_eval_aui_select_4 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                  out = _jspx_page_context.popBody();
              }
              if (_jspx_th_aui_select_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_aui_select_name_label_helpMessage.reuse(_jspx_th_aui_select_4);
                return;
              }
              _jspx_tagPool_aui_select_name_label_helpMessage.reuse(_jspx_th_aui_select_4);
              out.write("\n\n\t\t\t\t");
              //  c:if
              com.liferay.taglib.core.IfTag _jspx_th_c_if_3 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
              _jspx_th_c_if_3.setPageContext(_jspx_page_context);
              _jspx_th_c_if_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_1);
              _jspx_th_c_if_3.setTest( (type == ExpandoColumnConstants.DOUBLE_ARRAY) || (type == ExpandoColumnConstants.FLOAT_ARRAY) || (type == ExpandoColumnConstants.INTEGER_ARRAY) || (type == ExpandoColumnConstants.LONG_ARRAY) || (type == ExpandoColumnConstants.NUMBER_ARRAY) || (type == ExpandoColumnConstants.SHORT_ARRAY) || (type == ExpandoColumnConstants.STRING_ARRAY) );
              int _jspx_eval_c_if_3 = _jspx_th_c_if_3.doStartTag();
              if (_jspx_eval_c_if_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\t\t\t\t\t");
                if (_jspx_meth_aui_input_19((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_3, _jspx_page_context))
                  return;
                out.write("\n\n\t\t\t\t\t");
                //  aui:select
                com.liferay.taglib.aui.SelectTag _jspx_th_aui_select_5 = (com.liferay.taglib.aui.SelectTag) _jspx_tagPool_aui_select_value_name_label_helpMessage.get(com.liferay.taglib.aui.SelectTag.class);
                _jspx_th_aui_select_5.setPageContext(_jspx_page_context);
                _jspx_th_aui_select_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_3);
                _jspx_th_aui_select_5.setHelpMessage("custom-field-display-type-help");
                _jspx_th_aui_select_5.setLabel("display-type");
                _jspx_th_aui_select_5.setName("Property--display-type--");
                _jspx_th_aui_select_5.setValue( propertyDisplayType );
                int _jspx_eval_aui_select_5 = _jspx_th_aui_select_5.doStartTag();
                if (_jspx_eval_aui_select_5 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  if (_jspx_eval_aui_select_5 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                    out = _jspx_page_context.pushBody();
                    _jspx_th_aui_select_5.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                    _jspx_th_aui_select_5.doInitBody();
                  }
                  do {
                    out.write("\n\t\t\t\t\t\t");
                    //  aui:option
                    com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_17 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_value_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
                    _jspx_th_aui_option_17.setPageContext(_jspx_page_context);
                    _jspx_th_aui_option_17.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_5);
                    _jspx_th_aui_option_17.setLabel(new String("checkbox"));
                    _jspx_th_aui_option_17.setValue( ExpandoColumnConstants.PROPERTY_DISPLAY_TYPE_CHECKBOX );
                    int _jspx_eval_aui_option_17 = _jspx_th_aui_option_17.doStartTag();
                    if (_jspx_th_aui_option_17.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_aui_option_value_label_nobody.reuse(_jspx_th_aui_option_17);
                      return;
                    }
                    _jspx_tagPool_aui_option_value_label_nobody.reuse(_jspx_th_aui_option_17);
                    out.write("\n\t\t\t\t\t\t");
                    //  aui:option
                    com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_18 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_value_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
                    _jspx_th_aui_option_18.setPageContext(_jspx_page_context);
                    _jspx_th_aui_option_18.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_5);
                    _jspx_th_aui_option_18.setLabel(new String("radio"));
                    _jspx_th_aui_option_18.setValue( ExpandoColumnConstants.PROPERTY_DISPLAY_TYPE_RADIO );
                    int _jspx_eval_aui_option_18 = _jspx_th_aui_option_18.doStartTag();
                    if (_jspx_th_aui_option_18.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_aui_option_value_label_nobody.reuse(_jspx_th_aui_option_18);
                      return;
                    }
                    _jspx_tagPool_aui_option_value_label_nobody.reuse(_jspx_th_aui_option_18);
                    out.write("\n\t\t\t\t\t\t");
                    //  aui:option
                    com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_19 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_value_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
                    _jspx_th_aui_option_19.setPageContext(_jspx_page_context);
                    _jspx_th_aui_option_19.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_5);
                    _jspx_th_aui_option_19.setLabel(new String("selection-list"));
                    _jspx_th_aui_option_19.setValue( ExpandoColumnConstants.PROPERTY_DISPLAY_TYPE_SELECTION_LIST );
                    int _jspx_eval_aui_option_19 = _jspx_th_aui_option_19.doStartTag();
                    if (_jspx_th_aui_option_19.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_aui_option_value_label_nobody.reuse(_jspx_th_aui_option_19);
                      return;
                    }
                    _jspx_tagPool_aui_option_value_label_nobody.reuse(_jspx_th_aui_option_19);
                    out.write("\n\t\t\t\t\t\t");
                    //  aui:option
                    com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_20 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_value_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
                    _jspx_th_aui_option_20.setPageContext(_jspx_page_context);
                    _jspx_th_aui_option_20.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_5);
                    _jspx_th_aui_option_20.setLabel(new String("text-box"));
                    _jspx_th_aui_option_20.setValue( ExpandoColumnConstants.PROPERTY_DISPLAY_TYPE_TEXT_BOX );
                    int _jspx_eval_aui_option_20 = _jspx_th_aui_option_20.doStartTag();
                    if (_jspx_th_aui_option_20.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_aui_option_value_label_nobody.reuse(_jspx_th_aui_option_20);
                      return;
                    }
                    _jspx_tagPool_aui_option_value_label_nobody.reuse(_jspx_th_aui_option_20);
                    out.write("\n\t\t\t\t\t");
                    int evalDoAfterBody = _jspx_th_aui_select_5.doAfterBody();
                    if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                      break;
                  } while (true);
                  if (_jspx_eval_aui_select_5 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                    out = _jspx_page_context.popBody();
                }
                if (_jspx_th_aui_select_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_aui_select_value_name_label_helpMessage.reuse(_jspx_th_aui_select_5);
                  return;
                }
                _jspx_tagPool_aui_select_value_name_label_helpMessage.reuse(_jspx_th_aui_select_5);
                out.write("\n\t\t\t\t");
              }
              if (_jspx_th_c_if_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_3);
                return;
              }
              _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_3);
              out.write("\n\n\t\t\t\t");
              //  c:if
              com.liferay.taglib.core.IfTag _jspx_th_c_if_4 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
              _jspx_th_c_if_4.setPageContext(_jspx_page_context);
              _jspx_th_c_if_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_1);
              _jspx_th_c_if_4.setTest( type == ExpandoColumnConstants.STRING );
              int _jspx_eval_c_if_4 = _jspx_th_c_if_4.doStartTag();
              if (_jspx_eval_c_if_4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\t\t\t\t\t");
                if (_jspx_meth_aui_input_20((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_4, _jspx_page_context))
                  return;
                out.write("\n\n\t\t\t\t\t");
                //  aui:select
                com.liferay.taglib.aui.SelectTag _jspx_th_aui_select_6 = (com.liferay.taglib.aui.SelectTag) _jspx_tagPool_aui_select_name_label_helpMessage.get(com.liferay.taglib.aui.SelectTag.class);
                _jspx_th_aui_select_6.setPageContext(_jspx_page_context);
                _jspx_th_aui_select_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_4);
                _jspx_th_aui_select_6.setHelpMessage("custom-field-secret-help");
                _jspx_th_aui_select_6.setLabel("secret");
                _jspx_th_aui_select_6.setName("Property--secret--");
                int _jspx_eval_aui_select_6 = _jspx_th_aui_select_6.doStartTag();
                if (_jspx_eval_aui_select_6 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  if (_jspx_eval_aui_select_6 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                    out = _jspx_page_context.pushBody();
                    _jspx_th_aui_select_6.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                    _jspx_th_aui_select_6.doInitBody();
                  }
                  do {
                    out.write("\n\t\t\t\t\t\t");
                    //  aui:option
                    com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_21 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_value_selected_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
                    _jspx_th_aui_option_21.setPageContext(_jspx_page_context);
                    _jspx_th_aui_option_21.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_6);
                    _jspx_th_aui_option_21.setLabel( true );
                    _jspx_th_aui_option_21.setSelected( propertySecret );
                    _jspx_th_aui_option_21.setValue(new String("1"));
                    int _jspx_eval_aui_option_21 = _jspx_th_aui_option_21.doStartTag();
                    if (_jspx_th_aui_option_21.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_aui_option_value_selected_label_nobody.reuse(_jspx_th_aui_option_21);
                      return;
                    }
                    _jspx_tagPool_aui_option_value_selected_label_nobody.reuse(_jspx_th_aui_option_21);
                    out.write("\n\t\t\t\t\t\t");
                    //  aui:option
                    com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_22 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_value_selected_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
                    _jspx_th_aui_option_22.setPageContext(_jspx_page_context);
                    _jspx_th_aui_option_22.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_6);
                    _jspx_th_aui_option_22.setLabel( false );
                    _jspx_th_aui_option_22.setSelected( !propertySecret );
                    _jspx_th_aui_option_22.setValue(new String("0"));
                    int _jspx_eval_aui_option_22 = _jspx_th_aui_option_22.doStartTag();
                    if (_jspx_th_aui_option_22.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_aui_option_value_selected_label_nobody.reuse(_jspx_th_aui_option_22);
                      return;
                    }
                    _jspx_tagPool_aui_option_value_selected_label_nobody.reuse(_jspx_th_aui_option_22);
                    out.write("\n\t\t\t\t\t");
                    int evalDoAfterBody = _jspx_th_aui_select_6.doAfterBody();
                    if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                      break;
                  } while (true);
                  if (_jspx_eval_aui_select_6 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                    out = _jspx_page_context.popBody();
                }
                if (_jspx_th_aui_select_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_aui_select_name_label_helpMessage.reuse(_jspx_th_aui_select_6);
                  return;
                }
                _jspx_tagPool_aui_select_name_label_helpMessage.reuse(_jspx_th_aui_select_6);
                out.write("\n\n\t\t\t\t\t");
                if (_jspx_meth_aui_input_21((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_4, _jspx_page_context))
                  return;
                out.write("\n\n\t\t\t\t\t");
                //  aui:input
                com.liferay.taglib.aui.InputTag _jspx_th_aui_input_22 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_label_helpMessage_cssClass_nobody.get(com.liferay.taglib.aui.InputTag.class);
                _jspx_th_aui_input_22.setPageContext(_jspx_page_context);
                _jspx_th_aui_input_22.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_4);
                _jspx_th_aui_input_22.setCssClass("lfr-input-text short-input-text");
                _jspx_th_aui_input_22.setHelpMessage("custom-field-height-help");
                _jspx_th_aui_input_22.setLabel("height");
                _jspx_th_aui_input_22.setName("Property--height--");
                _jspx_th_aui_input_22.setType("text");
                _jspx_th_aui_input_22.setValue( propertyHeight );
                int _jspx_eval_aui_input_22 = _jspx_th_aui_input_22.doStartTag();
                if (_jspx_th_aui_input_22.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_aui_input_value_type_name_label_helpMessage_cssClass_nobody.reuse(_jspx_th_aui_input_22);
                  return;
                }
                _jspx_tagPool_aui_input_value_type_name_label_helpMessage_cssClass_nobody.reuse(_jspx_th_aui_input_22);
                out.write("\n\n\t\t\t\t\t");
                if (_jspx_meth_aui_input_23((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_4, _jspx_page_context))
                  return;
                out.write("\n\n\t\t\t\t\t");
                //  aui:input
                com.liferay.taglib.aui.InputTag _jspx_th_aui_input_24 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_label_helpMessage_cssClass_nobody.get(com.liferay.taglib.aui.InputTag.class);
                _jspx_th_aui_input_24.setPageContext(_jspx_page_context);
                _jspx_th_aui_input_24.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_4);
                _jspx_th_aui_input_24.setCssClass("lfr-input-text short-input-text");
                _jspx_th_aui_input_24.setHelpMessage("custom-field-height-help");
                _jspx_th_aui_input_24.setLabel("width");
                _jspx_th_aui_input_24.setName("Property--width--");
                _jspx_th_aui_input_24.setType("text");
                _jspx_th_aui_input_24.setValue( propertyWidth );
                int _jspx_eval_aui_input_24 = _jspx_th_aui_input_24.doStartTag();
                if (_jspx_th_aui_input_24.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_aui_input_value_type_name_label_helpMessage_cssClass_nobody.reuse(_jspx_th_aui_input_24);
                  return;
                }
                _jspx_tagPool_aui_input_value_type_name_label_helpMessage_cssClass_nobody.reuse(_jspx_th_aui_input_24);
                out.write("\n\t\t\t\t");
              }
              if (_jspx_th_c_if_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_4);
                return;
              }
              _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_4);
              out.write("\n\t\t\t");
            }
            if (_jspx_th_aui_fieldset_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_aui_fieldset_label.reuse(_jspx_th_aui_fieldset_1);
              return;
            }
            _jspx_tagPool_aui_fieldset_label.reuse(_jspx_th_aui_fieldset_1);
            out.write("\n\t\t");
          }
          if (_jspx_th_c_if_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_1);
            return;
          }
          _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_1);
          out.write('\n');
          out.write('	');
        }
        if (_jspx_th_aui_fieldset$1group_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_aui_fieldset$1group_markupView.reuse(_jspx_th_aui_fieldset$1group_0);
          return;
        }
        _jspx_tagPool_aui_fieldset$1group_markupView.reuse(_jspx_th_aui_fieldset$1group_0);
        out.write("\n\n\t");
        //  aui:button-row
        com.liferay.taglib.aui.ButtonRowTag _jspx_th_aui_button$1row_0 = (com.liferay.taglib.aui.ButtonRowTag) _jspx_tagPool_aui_button$1row.get(com.liferay.taglib.aui.ButtonRowTag.class);
        _jspx_th_aui_button$1row_0.setPageContext(_jspx_page_context);
        _jspx_th_aui_button$1row_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
        int _jspx_eval_aui_button$1row_0 = _jspx_th_aui_button$1row_0.doStartTag();
        if (_jspx_eval_aui_button$1row_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          out.write("\n\t\t");
          if (_jspx_meth_aui_button_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_button$1row_0, _jspx_page_context))
            return;
          out.write("\n\n\t\t");
          //  aui:button
          com.liferay.taglib.aui.ButtonTag _jspx_th_aui_button_1 = (com.liferay.taglib.aui.ButtonTag) _jspx_tagPool_aui_button_type_href_nobody.get(com.liferay.taglib.aui.ButtonTag.class);
          _jspx_th_aui_button_1.setPageContext(_jspx_page_context);
          _jspx_th_aui_button_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_button$1row_0);
          _jspx_th_aui_button_1.setHref( redirect );
          _jspx_th_aui_button_1.setType("cancel");
          int _jspx_eval_aui_button_1 = _jspx_th_aui_button_1.doStartTag();
          if (_jspx_th_aui_button_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_aui_button_type_href_nobody.reuse(_jspx_th_aui_button_1);
            return;
          }
          _jspx_tagPool_aui_button_type_href_nobody.reuse(_jspx_th_aui_button_1);
          out.write('\n');
          out.write('	');
        }
        if (_jspx_th_aui_button$1row_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_aui_button$1row.reuse(_jspx_th_aui_button$1row_0);
          return;
        }
        _jspx_tagPool_aui_button$1row.reuse(_jspx_th_aui_button$1row_0);
        out.write('\n');
      }
      if (_jspx_th_aui_form_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_aui_form_name_method_cssClass_action.reuse(_jspx_th_aui_form_0);
        return;
      }
      _jspx_tagPool_aui_form_name_method_cssClass_action.reuse(_jspx_th_aui_form_0);
      out.write('\n');
      out.write('\n');

PortalUtil.addPortletBreadcrumbEntry(request, modelResourceName, portletURL.toString());

if (column != null) {
	PortalUtil.addPortletBreadcrumbEntry(request, column.getName(), null);
}

PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(request, ((column == null) ? "add-attribute" : "edit")), currentURL);

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

  private boolean _jspx_meth_portlet_param_0(javax.servlet.jsp.tagext.JspTag _jspx_th_portlet_actionURL_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:param
    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_0 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
    _jspx_th_portlet_param_0.setPageContext(_jspx_page_context);
    _jspx_th_portlet_param_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_actionURL_0);
    _jspx_th_portlet_param_0.setName("mvcPath");
    _jspx_th_portlet_param_0.setValue("/edit_expando.jsp");
    int _jspx_eval_portlet_param_0 = _jspx_th_portlet_param_0.doStartTag();
    if (_jspx_th_portlet_param_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_0);
      return true;
    }
    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_0);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_0(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_select_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_0 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_0.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_0);
    _jspx_th_liferay$1ui_message_0.setKey("presets");
    int _jspx_eval_liferay$1ui_message_0 = _jspx_th_liferay$1ui_message_0.doStartTag();
    if (_jspx_th_liferay$1ui_message_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_0);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_0);
    return false;
  }

  private boolean _jspx_meth_aui_option_0(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_select_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:option
    com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_0 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_value_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
    _jspx_th_aui_option_0.setPageContext(_jspx_page_context);
    _jspx_th_aui_option_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_0);
    _jspx_th_aui_option_0.setLabel(new String("selection-of-integer-values"));
    _jspx_th_aui_option_0.setValue(new String("PresetSelectionIntegerArray()"));
    int _jspx_eval_aui_option_0 = _jspx_th_aui_option_0.doStartTag();
    if (_jspx_th_aui_option_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_option_value_label_nobody.reuse(_jspx_th_aui_option_0);
      return true;
    }
    _jspx_tagPool_aui_option_value_label_nobody.reuse(_jspx_th_aui_option_0);
    return false;
  }

  private boolean _jspx_meth_aui_option_1(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_select_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:option
    com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_1 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_value_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
    _jspx_th_aui_option_1.setPageContext(_jspx_page_context);
    _jspx_th_aui_option_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_0);
    _jspx_th_aui_option_1.setLabel(new String("selection-of-decimal-values"));
    _jspx_th_aui_option_1.setValue(new String("PresetSelectionDoubleArray()"));
    int _jspx_eval_aui_option_1 = _jspx_th_aui_option_1.doStartTag();
    if (_jspx_th_aui_option_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_option_value_label_nobody.reuse(_jspx_th_aui_option_1);
      return true;
    }
    _jspx_tagPool_aui_option_value_label_nobody.reuse(_jspx_th_aui_option_1);
    return false;
  }

  private boolean _jspx_meth_aui_option_2(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_select_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:option
    com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_2 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_value_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
    _jspx_th_aui_option_2.setPageContext(_jspx_page_context);
    _jspx_th_aui_option_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_0);
    _jspx_th_aui_option_2.setLabel(new String("selection-of-text-values"));
    _jspx_th_aui_option_2.setValue(new String("PresetSelectionStringArray()"));
    int _jspx_eval_aui_option_2 = _jspx_th_aui_option_2.doStartTag();
    if (_jspx_th_aui_option_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_option_value_label_nobody.reuse(_jspx_th_aui_option_2);
      return true;
    }
    _jspx_tagPool_aui_option_value_label_nobody.reuse(_jspx_th_aui_option_2);
    return false;
  }

  private boolean _jspx_meth_aui_option_3(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_select_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:option
    com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_3 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_value_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
    _jspx_th_aui_option_3.setPageContext(_jspx_page_context);
    _jspx_th_aui_option_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_0);
    _jspx_th_aui_option_3.setLabel(new String("text-box"));
    _jspx_th_aui_option_3.setValue(new String("PresetTextBox()"));
    int _jspx_eval_aui_option_3 = _jspx_th_aui_option_3.doStartTag();
    if (_jspx_th_aui_option_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_option_value_label_nobody.reuse(_jspx_th_aui_option_3);
      return true;
    }
    _jspx_tagPool_aui_option_value_label_nobody.reuse(_jspx_th_aui_option_3);
    return false;
  }

  private boolean _jspx_meth_aui_option_4(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_select_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:option
    com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_4 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_value_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
    _jspx_th_aui_option_4.setPageContext(_jspx_page_context);
    _jspx_th_aui_option_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_0);
    _jspx_th_aui_option_4.setLabel(new String("text-box-indexed"));
    _jspx_th_aui_option_4.setValue(new String("PresetTextBoxIndexed()"));
    int _jspx_eval_aui_option_4 = _jspx_th_aui_option_4.doStartTag();
    if (_jspx_th_aui_option_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_option_value_label_nobody.reuse(_jspx_th_aui_option_4);
      return true;
    }
    _jspx_tagPool_aui_option_value_label_nobody.reuse(_jspx_th_aui_option_4);
    return false;
  }

  private boolean _jspx_meth_aui_option_5(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_select_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:option
    com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_5 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_value_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
    _jspx_th_aui_option_5.setPageContext(_jspx_page_context);
    _jspx_th_aui_option_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_0);
    _jspx_th_aui_option_5.setLabel(new String("text-field-secret"));
    _jspx_th_aui_option_5.setValue(new String("PresetTextFieldSecret()"));
    int _jspx_eval_aui_option_5 = _jspx_th_aui_option_5.doStartTag();
    if (_jspx_th_aui_option_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_option_value_label_nobody.reuse(_jspx_th_aui_option_5);
      return true;
    }
    _jspx_tagPool_aui_option_value_label_nobody.reuse(_jspx_th_aui_option_5);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_1(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_select_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_1 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_1.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_0);
    _jspx_th_liferay$1ui_message_1.setKey("primitives");
    int _jspx_eval_liferay$1ui_message_1 = _jspx_th_liferay$1ui_message_1.doStartTag();
    if (_jspx_th_liferay$1ui_message_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_1);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_1);
    return false;
  }

  private boolean _jspx_meth_aui_input_16(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_fieldset_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:input
    com.liferay.taglib.aui.InputTag _jspx_th_aui_input_16 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
    _jspx_th_aui_input_16.setPageContext(_jspx_page_context);
    _jspx_th_aui_input_16.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_1);
    _jspx_th_aui_input_16.setName("PropertyName--hidden--");
    _jspx_th_aui_input_16.setType("hidden");
    _jspx_th_aui_input_16.setValue(new String("hidden"));
    int _jspx_eval_aui_input_16 = _jspx_th_aui_input_16.doStartTag();
    if (_jspx_th_aui_input_16.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_16);
      return true;
    }
    _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_16);
    return false;
  }

  private boolean _jspx_meth_aui_input_17(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_fieldset_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:input
    com.liferay.taglib.aui.InputTag _jspx_th_aui_input_17 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
    _jspx_th_aui_input_17.setPageContext(_jspx_page_context);
    _jspx_th_aui_input_17.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_1);
    _jspx_th_aui_input_17.setName("PropertyName--visible-with-update-permission--");
    _jspx_th_aui_input_17.setType("hidden");
    _jspx_th_aui_input_17.setValue(new String("visible-with-update-permission"));
    int _jspx_eval_aui_input_17 = _jspx_th_aui_input_17.doStartTag();
    if (_jspx_th_aui_input_17.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_17);
      return true;
    }
    _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_17);
    return false;
  }

  private boolean _jspx_meth_aui_input_18(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_fieldset_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:input
    com.liferay.taglib.aui.InputTag _jspx_th_aui_input_18 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
    _jspx_th_aui_input_18.setPageContext(_jspx_page_context);
    _jspx_th_aui_input_18.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_1);
    _jspx_th_aui_input_18.setName("PropertyName--index-type--");
    _jspx_th_aui_input_18.setType("hidden");
    _jspx_th_aui_input_18.setValue(new String("index-type"));
    int _jspx_eval_aui_input_18 = _jspx_th_aui_input_18.doStartTag();
    if (_jspx_th_aui_input_18.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_18);
      return true;
    }
    _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_18);
    return false;
  }

  private boolean _jspx_meth_aui_input_19(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:input
    com.liferay.taglib.aui.InputTag _jspx_th_aui_input_19 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
    _jspx_th_aui_input_19.setPageContext(_jspx_page_context);
    _jspx_th_aui_input_19.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_3);
    _jspx_th_aui_input_19.setName("PropertyName--display-type--");
    _jspx_th_aui_input_19.setType("hidden");
    _jspx_th_aui_input_19.setValue(new String("display-type"));
    int _jspx_eval_aui_input_19 = _jspx_th_aui_input_19.doStartTag();
    if (_jspx_th_aui_input_19.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_19);
      return true;
    }
    _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_19);
    return false;
  }

  private boolean _jspx_meth_aui_input_20(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:input
    com.liferay.taglib.aui.InputTag _jspx_th_aui_input_20 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
    _jspx_th_aui_input_20.setPageContext(_jspx_page_context);
    _jspx_th_aui_input_20.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_4);
    _jspx_th_aui_input_20.setName("PropertyName--secret--");
    _jspx_th_aui_input_20.setType("hidden");
    _jspx_th_aui_input_20.setValue(new String("secret"));
    int _jspx_eval_aui_input_20 = _jspx_th_aui_input_20.doStartTag();
    if (_jspx_th_aui_input_20.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_20);
      return true;
    }
    _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_20);
    return false;
  }

  private boolean _jspx_meth_aui_input_21(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:input
    com.liferay.taglib.aui.InputTag _jspx_th_aui_input_21 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
    _jspx_th_aui_input_21.setPageContext(_jspx_page_context);
    _jspx_th_aui_input_21.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_4);
    _jspx_th_aui_input_21.setName("PropertyName--height--");
    _jspx_th_aui_input_21.setType("hidden");
    _jspx_th_aui_input_21.setValue(new String("height"));
    int _jspx_eval_aui_input_21 = _jspx_th_aui_input_21.doStartTag();
    if (_jspx_th_aui_input_21.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_21);
      return true;
    }
    _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_21);
    return false;
  }

  private boolean _jspx_meth_aui_input_23(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:input
    com.liferay.taglib.aui.InputTag _jspx_th_aui_input_23 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
    _jspx_th_aui_input_23.setPageContext(_jspx_page_context);
    _jspx_th_aui_input_23.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_4);
    _jspx_th_aui_input_23.setName("PropertyName--width--");
    _jspx_th_aui_input_23.setType("hidden");
    _jspx_th_aui_input_23.setValue(new String("width"));
    int _jspx_eval_aui_input_23 = _jspx_th_aui_input_23.doStartTag();
    if (_jspx_th_aui_input_23.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_23);
      return true;
    }
    _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_23);
    return false;
  }

  private boolean _jspx_meth_aui_button_0(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_button$1row_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:button
    com.liferay.taglib.aui.ButtonTag _jspx_th_aui_button_0 = (com.liferay.taglib.aui.ButtonTag) _jspx_tagPool_aui_button_type_nobody.get(com.liferay.taglib.aui.ButtonTag.class);
    _jspx_th_aui_button_0.setPageContext(_jspx_page_context);
    _jspx_th_aui_button_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_button$1row_0);
    _jspx_th_aui_button_0.setType("submit");
    int _jspx_eval_aui_button_0 = _jspx_th_aui_button_0.doStartTag();
    if (_jspx_th_aui_button_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_button_type_nobody.reuse(_jspx_th_aui_button_0);
      return true;
    }
    _jspx_tagPool_aui_button_type_nobody.reuse(_jspx_th_aui_button_0);
    return false;
  }
}
