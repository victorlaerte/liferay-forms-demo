package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.calendar.configuration.CalendarServiceConfigurationValues;
import com.liferay.calendar.constants.CalendarActionKeys;
import com.liferay.calendar.constants.CalendarConstants;
import com.liferay.calendar.exception.CalendarBookingDurationException;
import com.liferay.calendar.exception.CalendarBookingRecurrenceException;
import com.liferay.calendar.exception.CalendarNameException;
import com.liferay.calendar.exception.CalendarResourceCodeException;
import com.liferay.calendar.exception.CalendarResourceNameException;
import com.liferay.calendar.exception.DuplicateCalendarResourceException;
import com.liferay.calendar.exception.NoSuchResourceException;
import com.liferay.calendar.model.Calendar;
import com.liferay.calendar.model.CalendarBooking;
import com.liferay.calendar.model.CalendarNotificationTemplate;
import com.liferay.calendar.model.CalendarNotificationTemplateConstants;
import com.liferay.calendar.model.CalendarResource;
import com.liferay.calendar.notification.NotificationField;
import com.liferay.calendar.notification.NotificationTemplateType;
import com.liferay.calendar.notification.NotificationType;
import com.liferay.calendar.notification.NotificationUtil;
import com.liferay.calendar.recurrence.Frequency;
import com.liferay.calendar.recurrence.PositionalWeekday;
import com.liferay.calendar.recurrence.Recurrence;
import com.liferay.calendar.recurrence.Weekday;
import com.liferay.calendar.service.CalendarBookingLocalServiceUtil;
import com.liferay.calendar.service.CalendarBookingServiceUtil;
import com.liferay.calendar.service.CalendarNotificationTemplateLocalServiceUtil;
import com.liferay.calendar.service.CalendarResourceServiceUtil;
import com.liferay.calendar.service.CalendarServiceUtil;
import com.liferay.calendar.util.JCalendarUtil;
import com.liferay.calendar.util.RecurrenceUtil;
import com.liferay.calendar.util.comparator.CalendarNameComparator;
import com.liferay.calendar.web.internal.constants.CalendarWebKeys;
import com.liferay.calendar.web.internal.display.context.CalendarDisplayContext;
import com.liferay.calendar.web.internal.search.CalendarResourceDisplayTerms;
import com.liferay.calendar.web.internal.search.CalendarResourceSearch;
import com.liferay.calendar.web.internal.security.permission.resource.CalendarPermission;
import com.liferay.calendar.web.internal.security.permission.resource.CalendarPortletPermission;
import com.liferay.calendar.web.internal.security.permission.resource.CalendarResourcePermission;
import com.liferay.calendar.web.internal.util.CalendarResourceUtil;
import com.liferay.calendar.web.internal.util.CalendarUtil;
import com.liferay.calendar.web.internal.util.ColorUtil;
import com.liferay.calendar.workflow.CalendarBookingWorkflowConstants;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.bean.BeanParamUtil;
import com.liferay.portal.kernel.bean.BeanPropertiesUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONSerializer;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.ResourceActionsUtil;
import com.liferay.portal.kernel.service.ClassNameLocalServiceUtil;
import com.liferay.portal.kernel.service.GroupServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.service.WorkflowDefinitionLinkLocalServiceUtil;
import com.liferay.portal.kernel.service.WorkflowInstanceLinkLocalServiceUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.FastDateFormatConstants;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.SessionClicks;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.util.comparator.UserScreenNameComparator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.rss.util.RSSUtil;
import com.liferay.taglib.search.ResultRow;
import java.text.Format;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.TimeZone;
import javax.portlet.PortletURL;

public final class edit_005fcalendar_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(3);
    _jspx_dependants.add("/init.jsp");
    _jspx_dependants.add("/edit_calendar_general.jspf");
    _jspx_dependants.add("/edit_calendar_notification_templates.jspf");
  }

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_input_value_name_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_model$1context_model_bean_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_if_test;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1portlet_actionURL_var_name_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_input$1permissions_modelName_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_script;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_input$1editor_editorImpl_contents_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_header_title_cssClass_backURL_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_nav$1bar_markupView_cssClass;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_button_type_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_field$1wrapper_label_inlineLabel;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_form_name_method_cssClass_action;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_input_type_name_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_choose;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_input_name_disabled_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1frontend_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_script_use;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_form_onSubmit_name_method_cssClass_action;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_nav_cssClass;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_nav$1item_selected_label_href_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_fieldset;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_when_test;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_input_value_type_name_label_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_namespace_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_button_type_href_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_message_key_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_input_value_type_name_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_field$1wrapper_label;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_otherwise;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_nav$1bar_markupView;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_error_message_exception_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1theme_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_input_name_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_button$1row;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_aui_input_value_name_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_model$1context_model_bean_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_if_test = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1portlet_actionURL_var_name_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_input$1permissions_modelName_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_script = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_input$1editor_editorImpl_contents_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_header_title_cssClass_backURL_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_nav$1bar_markupView_cssClass = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_button_type_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_field$1wrapper_label_inlineLabel = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_form_name_method_cssClass_action = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_input_type_name_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_choose = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_input_name_disabled_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1frontend_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_script_use = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_form_onSubmit_name_method_cssClass_action = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_nav_cssClass = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_nav$1item_selected_label_href_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_fieldset = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_when_test = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_input_value_type_name_label_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_namespace_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_button_type_href_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_message_key_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_input_value_type_name_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_field$1wrapper_label = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_otherwise = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_nav$1bar_markupView = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_error_message_exception_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1theme_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_input_name_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_button$1row = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_aui_input_value_name_nobody.release();
    _jspx_tagPool_aui_model$1context_model_bean_nobody.release();
    _jspx_tagPool_c_if_test.release();
    _jspx_tagPool_liferay$1portlet_actionURL_var_name_nobody.release();
    _jspx_tagPool_liferay$1ui_input$1permissions_modelName_nobody.release();
    _jspx_tagPool_aui_script.release();
    _jspx_tagPool_liferay$1ui_input$1editor_editorImpl_contents_nobody.release();
    _jspx_tagPool_liferay$1ui_header_title_cssClass_backURL_nobody.release();
    _jspx_tagPool_aui_nav$1bar_markupView_cssClass.release();
    _jspx_tagPool_aui_button_type_nobody.release();
    _jspx_tagPool_aui_field$1wrapper_label_inlineLabel.release();
    _jspx_tagPool_aui_form_name_method_cssClass_action.release();
    _jspx_tagPool_aui_input_type_name_nobody.release();
    _jspx_tagPool_c_choose.release();
    _jspx_tagPool_aui_input_name_disabled_nobody.release();
    _jspx_tagPool_liferay$1frontend_defineObjects_nobody.release();
    _jspx_tagPool_aui_script_use.release();
    _jspx_tagPool_aui_form_onSubmit_name_method_cssClass_action.release();
    _jspx_tagPool_aui_nav_cssClass.release();
    _jspx_tagPool_aui_nav$1item_selected_label_href_nobody.release();
    _jspx_tagPool_aui_fieldset.release();
    _jspx_tagPool_c_when_test.release();
    _jspx_tagPool_aui_input_value_type_name_label_nobody.release();
    _jspx_tagPool_portlet_namespace_nobody.release();
    _jspx_tagPool_aui_button_type_href_nobody.release();
    _jspx_tagPool_liferay$1ui_message_key_nobody.release();
    _jspx_tagPool_aui_input_value_type_name_nobody.release();
    _jspx_tagPool_aui_field$1wrapper_label.release();
    _jspx_tagPool_c_otherwise.release();
    _jspx_tagPool_aui_nav$1bar_markupView.release();
    _jspx_tagPool_portlet_defineObjects_nobody.release();
    _jspx_tagPool_liferay$1ui_error_message_exception_nobody.release();
    _jspx_tagPool_liferay$1theme_defineObjects_nobody.release();
    _jspx_tagPool_aui_input_name_nobody.release();
    _jspx_tagPool_aui_button$1row.release();
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

int defaultDuration = GetterUtil.getInteger(portletPreferences.getValue("defaultDuration", null), 60);
String defaultView = portletPreferences.getValue("defaultView", "week");
String timeFormat = GetterUtil.getString(portletPreferences.getValue("timeFormat", "locale"));
String timeZoneId = portletPreferences.getValue("timeZoneId", user.getTimeZoneId());
boolean usePortalTimeZone = GetterUtil.getBoolean(portletPreferences.getValue("usePortalTimeZone", Boolean.TRUE.toString()));
int weekStartsOn = GetterUtil.getInteger(portletPreferences.getValue("weekStartsOn", null));

String sessionClicksDefaultView = SessionClicks.get(request, "com.liferay.calendar.web_defaultView", defaultView);

if (usePortalTimeZone) {
	timeZoneId = user.getTimeZoneId();
}

boolean displaySchedulerHeader = GetterUtil.getBoolean(portletPreferences.getValue("displaySchedulerHeader", null), true);
boolean displaySchedulerOnly = GetterUtil.getBoolean(portletPreferences.getValue("displaySchedulerOnly", null));
boolean showUserEvents = GetterUtil.getBoolean(portletPreferences.getValue("showUserEvents", null), true);

boolean showAgendaView = GetterUtil.getBoolean(portletPreferences.getValue("showAgendaView", null), true);
boolean showDayView = GetterUtil.getBoolean(portletPreferences.getValue("showDayView", null), true);
boolean showMonthView = GetterUtil.getBoolean(portletPreferences.getValue("showMonthView", null), true);
boolean showWeekView = GetterUtil.getBoolean(portletPreferences.getValue("showWeekView", null), true);

int eventsPerPage = GetterUtil.getInteger(portletPreferences.getValue("eventsPerPage", null), 10);
int maxDaysDisplayed = GetterUtil.getInteger(portletPreferences.getValue("maxDaysDisplayed", null), 1);

boolean enableRSS = !PortalUtil.isRSSFeedsEnabled() ? false : GetterUtil.getBoolean(portletPreferences.getValue("enableRss", null), true);
int rssDelta = GetterUtil.getInteger(portletPreferences.getValue("rssDelta", StringPool.BLANK), SearchContainer.DEFAULT_DELTA);
String rssDisplayStyle = portletPreferences.getValue("rssDisplayStyle", RSSUtil.DISPLAY_STYLE_DEFAULT);
String rssFeedType = portletPreferences.getValue("rssFeedType", RSSUtil.FEED_TYPE_DEFAULT);
long rssTimeInterval = GetterUtil.getLong(portletPreferences.getValue("rssTimeInterval", StringPool.BLANK), Time.WEEK);

CalendarBooking calendarBooking = (CalendarBooking)request.getAttribute(CalendarWebKeys.CALENDAR_BOOKING);

CalendarResource groupCalendarResource = CalendarResourceUtil.getScopeGroupCalendarResource(liferayPortletRequest, scopeGroupId);
CalendarResource userCalendarResource = null;

if (showUserEvents || !themeDisplay.isSignedIn()) {
	userCalendarResource = CalendarResourceUtil.getUserCalendarResource(liferayPortletRequest, themeDisplay.getUserId());
}

Calendar userDefaultCalendar = null;

if (userCalendarResource != null) {
	long defaultCalendarId = userCalendarResource.getDefaultCalendarId();

	if (defaultCalendarId > 0) {
		userDefaultCalendar = CalendarServiceUtil.getCalendar(defaultCalendarId);
	}
}

List<Calendar> groupCalendars = Collections.emptyList();

boolean showSiteCalendars = false;

if (groupCalendarResource != null) {
	showSiteCalendars = (userCalendarResource == null) || (groupCalendarResource.getCalendarResourceId() != userCalendarResource.getCalendarResourceId());
}

if (showSiteCalendars) {
	groupCalendars = CalendarServiceUtil.search(themeDisplay.getCompanyId(), null, new long[] {groupCalendarResource.getCalendarResourceId()}, null, true, QueryUtil.ALL_POS, QueryUtil.ALL_POS, (OrderByComparator)null);
}

List<Calendar> userCalendars = Collections.emptyList();

if (userCalendarResource != null) {
	userCalendars = CalendarServiceUtil.search(themeDisplay.getCompanyId(), null, new long[] {userCalendarResource.getCalendarResourceId()}, null, true, QueryUtil.ALL_POS, QueryUtil.ALL_POS, (OrderByComparator)null);
}

List<Calendar> otherCalendars = new ArrayList<Calendar>();

long[] calendarIds = StringUtil.split(SessionClicks.get(request, "com.liferay.calendar.web_otherCalendars", StringPool.BLANK), 0L);

Calendar defaultCalendar = null;

CalendarDisplayContext calendarDisplayContext = (CalendarDisplayContext)renderRequest.getAttribute(CalendarWebKeys.CALENDAR_DISPLAY_CONTEXT);

if (calendarDisplayContext != null) {
	otherCalendars = calendarDisplayContext.getOtherCalendars(user, calendarIds);

	defaultCalendar = calendarDisplayContext.getDefaultCalendar(groupCalendars, userCalendars);
}

TimeZone userTimeZone = null;

if ((calendarBooking != null) && calendarBooking.isAllDay()) {
	userTimeZone = TimeZone.getTimeZone(StringPool.UTC);
}
else {
	userTimeZone = TimeZone.getTimeZone(timeZoneId);
}

TimeZone utcTimeZone = TimeZone.getTimeZone(StringPool.UTC);

Format dateFormatLongDate = FastDateFormatFactoryUtil.getDate(FastDateFormatConstants.LONG, locale, userTimeZone);

Format dateFormatTime = null;

boolean useIsoTimeFormat = timeFormat.equals("24-hour") || (timeFormat.equals("locale") && !DateUtil.isFormatAmPm(locale));

if (useIsoTimeFormat) {
	dateFormatTime = FastDateFormatFactoryUtil.getSimpleDateFormat("HH:mm", locale, userTimeZone);
}
else {
	dateFormatTime = FastDateFormatFactoryUtil.getSimpleDateFormat("hh:mm a", locale, userTimeZone);
}

      out.write('\n');
      out.write('\n');

String tabs2 = ParamUtil.getString(request, "tabs2", "general");

String redirect = ParamUtil.getString(request, "redirect");

if (Validator.isNull(redirect)) {
	redirect = PortalUtil.getCurrentURL(request);
}

String backURL = ParamUtil.getString(request, "backURL");

Calendar calendar = (Calendar)request.getAttribute(CalendarWebKeys.CALENDAR);

CalendarResource calendarResource = (CalendarResource)request.getAttribute(CalendarWebKeys.CALENDAR_RESOURCE);

if (calendarResource == null) {
	calendarResource = calendar.getCalendarResource();
}

String calendarName = null;

if (calendar != null) {
	calendarName = calendar.getName(locale);
}

String calendarResourceName = calendarResource.getName(locale);

if (Validator.isNotNull(calendarName) && !calendarName.equals(calendarResourceName)) {
	calendarName = calendarResourceName + StringPool.SPACE + StringPool.DASH + StringPool.SPACE + calendarName;
}

String calendarId = (calendar != null) ? String.valueOf(calendar.getCalendarId()) : StringPool.BLANK;
String calendarResourceId = (calendarResource != null) ? String.valueOf(calendarResource.getCalendarResourceId()) : StringPool.BLANK;

PortletURL navigationURL = renderResponse.createRenderURL();

navigationURL.setParameter("mvcPath", "/edit_calendar.jsp");
navigationURL.setParameter("redirect", redirect);
navigationURL.setParameter("backURL", backURL);
navigationURL.setParameter("calendarId", calendarId);
navigationURL.setParameter("calendarResourceId", calendarResourceId);

      out.write('\n');
      out.write('\n');
      //  aui:nav-bar
      com.liferay.taglib.aui.NavBarTag _jspx_th_aui_nav$1bar_0 = (com.liferay.taglib.aui.NavBarTag) _jspx_tagPool_aui_nav$1bar_markupView.get(com.liferay.taglib.aui.NavBarTag.class);
      _jspx_th_aui_nav$1bar_0.setPageContext(_jspx_page_context);
      _jspx_th_aui_nav$1bar_0.setParent(null);
      _jspx_th_aui_nav$1bar_0.setMarkupView("lexicon");
      int _jspx_eval_aui_nav$1bar_0 = _jspx_th_aui_nav$1bar_0.doStartTag();
      if (_jspx_eval_aui_nav$1bar_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        if (_jspx_eval_aui_nav$1bar_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
          out = _jspx_page_context.pushBody();
          _jspx_th_aui_nav$1bar_0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
          _jspx_th_aui_nav$1bar_0.doInitBody();
        }
        do {
          out.write('\n');
          out.write('	');
          //  aui:nav
          com.liferay.taglib.aui.NavTag _jspx_th_aui_nav_0 = (com.liferay.taglib.aui.NavTag) _jspx_tagPool_aui_nav_cssClass.get(com.liferay.taglib.aui.NavTag.class);
          _jspx_th_aui_nav_0.setPageContext(_jspx_page_context);
          _jspx_th_aui_nav_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_nav$1bar_0);
          _jspx_th_aui_nav_0.setCssClass("navbar-nav");
          int _jspx_eval_aui_nav_0 = _jspx_th_aui_nav_0.doStartTag();
          if (_jspx_eval_aui_nav_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            if (_jspx_eval_aui_nav_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
              out = _jspx_page_context.pushBody();
              _jspx_th_aui_nav_0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
              _jspx_th_aui_nav_0.doInitBody();
            }
            do {
              out.write("\n\n\t\t");

		navigationURL.setParameter("tabs2", "general");
		
              out.write("\n\n\t\t");
              //  aui:nav-item
              com.liferay.taglib.aui.NavItemTag _jspx_th_aui_nav$1item_0 = (com.liferay.taglib.aui.NavItemTag) _jspx_tagPool_aui_nav$1item_selected_label_href_nobody.get(com.liferay.taglib.aui.NavItemTag.class);
              _jspx_th_aui_nav$1item_0.setPageContext(_jspx_page_context);
              _jspx_th_aui_nav$1item_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_nav_0);
              _jspx_th_aui_nav$1item_0.setHref( navigationURL.toString() );
              _jspx_th_aui_nav$1item_0.setLabel("general");
              _jspx_th_aui_nav$1item_0.setSelected( tabs2.equals("general") );
              int _jspx_eval_aui_nav$1item_0 = _jspx_th_aui_nav$1item_0.doStartTag();
              if (_jspx_th_aui_nav$1item_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_aui_nav$1item_selected_label_href_nobody.reuse(_jspx_th_aui_nav$1item_0);
                return;
              }
              _jspx_tagPool_aui_nav$1item_selected_label_href_nobody.reuse(_jspx_th_aui_nav$1item_0);
              out.write("\n\n\t\t");
              //  c:if
              com.liferay.taglib.core.IfTag _jspx_th_c_if_0 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
              _jspx_th_c_if_0.setPageContext(_jspx_page_context);
              _jspx_th_c_if_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_nav_0);
              _jspx_th_c_if_0.setTest( calendar != null );
              int _jspx_eval_c_if_0 = _jspx_th_c_if_0.doStartTag();
              if (_jspx_eval_c_if_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\n\t\t\t");

			navigationURL.setParameter("tabs2", "notification-templates");
			
                out.write("\n\n\t\t\t");
                //  aui:nav-item
                com.liferay.taglib.aui.NavItemTag _jspx_th_aui_nav$1item_1 = (com.liferay.taglib.aui.NavItemTag) _jspx_tagPool_aui_nav$1item_selected_label_href_nobody.get(com.liferay.taglib.aui.NavItemTag.class);
                _jspx_th_aui_nav$1item_1.setPageContext(_jspx_page_context);
                _jspx_th_aui_nav$1item_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_0);
                _jspx_th_aui_nav$1item_1.setHref( navigationURL.toString() );
                _jspx_th_aui_nav$1item_1.setLabel("notification-templates");
                _jspx_th_aui_nav$1item_1.setSelected( tabs2.equals("notification-templates") );
                int _jspx_eval_aui_nav$1item_1 = _jspx_th_aui_nav$1item_1.doStartTag();
                if (_jspx_th_aui_nav$1item_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_aui_nav$1item_selected_label_href_nobody.reuse(_jspx_th_aui_nav$1item_1);
                  return;
                }
                _jspx_tagPool_aui_nav$1item_selected_label_href_nobody.reuse(_jspx_th_aui_nav$1item_1);
                out.write("\n\t\t");
              }
              if (_jspx_th_c_if_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
                return;
              }
              _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
              out.write('\n');
              out.write('	');
              int evalDoAfterBody = _jspx_th_aui_nav_0.doAfterBody();
              if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                break;
            } while (true);
            if (_jspx_eval_aui_nav_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
              out = _jspx_page_context.popBody();
          }
          if (_jspx_th_aui_nav_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_aui_nav_cssClass.reuse(_jspx_th_aui_nav_0);
            return;
          }
          _jspx_tagPool_aui_nav_cssClass.reuse(_jspx_th_aui_nav_0);
          out.write('\n');
          int evalDoAfterBody = _jspx_th_aui_nav$1bar_0.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
        if (_jspx_eval_aui_nav$1bar_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
          out = _jspx_page_context.popBody();
      }
      if (_jspx_th_aui_nav$1bar_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_aui_nav$1bar_markupView.reuse(_jspx_th_aui_nav$1bar_0);
        return;
      }
      _jspx_tagPool_aui_nav$1bar_markupView.reuse(_jspx_th_aui_nav$1bar_0);
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
        _jspx_th_c_when_0.setTest( tabs2.equals("general") );
        int _jspx_eval_c_when_0 = _jspx_th_c_when_0.doStartTag();
        if (_jspx_eval_c_when_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          out.write("\n\t\t");
          out.write('\n');
          out.write('\n');
          //  liferay-ui:header
          com.liferay.taglib.ui.HeaderTag _jspx_th_liferay$1ui_header_0 = (com.liferay.taglib.ui.HeaderTag) _jspx_tagPool_liferay$1ui_header_title_cssClass_backURL_nobody.get(com.liferay.taglib.ui.HeaderTag.class);
          _jspx_th_liferay$1ui_header_0.setPageContext(_jspx_page_context);
          _jspx_th_liferay$1ui_header_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_0);
          _jspx_th_liferay$1ui_header_0.setBackURL( backURL );
          _jspx_th_liferay$1ui_header_0.setCssClass("container-fluid-1280");
          _jspx_th_liferay$1ui_header_0.setTitle( (calendar == null) ? LanguageUtil.format(request, "new-calendar-for-x", calendarResourceName) : calendarName );
          int _jspx_eval_liferay$1ui_header_0 = _jspx_th_liferay$1ui_header_0.doStartTag();
          if (_jspx_th_liferay$1ui_header_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_liferay$1ui_header_title_cssClass_backURL_nobody.reuse(_jspx_th_liferay$1ui_header_0);
            return;
          }
          _jspx_tagPool_liferay$1ui_header_title_cssClass_backURL_nobody.reuse(_jspx_th_liferay$1ui_header_0);
          out.write('\n');
          out.write('\n');
          //  liferay-portlet:actionURL
          com.liferay.taglib.portlet.ActionURLTag _jspx_th_liferay$1portlet_actionURL_0 = (com.liferay.taglib.portlet.ActionURLTag) _jspx_tagPool_liferay$1portlet_actionURL_var_name_nobody.get(com.liferay.taglib.portlet.ActionURLTag.class);
          _jspx_th_liferay$1portlet_actionURL_0.setPageContext(_jspx_page_context);
          _jspx_th_liferay$1portlet_actionURL_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_0);
          _jspx_th_liferay$1portlet_actionURL_0.setName("updateCalendar");
          _jspx_th_liferay$1portlet_actionURL_0.setVar("updateCalendarURL");
          int _jspx_eval_liferay$1portlet_actionURL_0 = _jspx_th_liferay$1portlet_actionURL_0.doStartTag();
          if (_jspx_th_liferay$1portlet_actionURL_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_liferay$1portlet_actionURL_var_name_nobody.reuse(_jspx_th_liferay$1portlet_actionURL_0);
            return;
          }
          _jspx_tagPool_liferay$1portlet_actionURL_var_name_nobody.reuse(_jspx_th_liferay$1portlet_actionURL_0);
          java.lang.String updateCalendarURL = null;
          updateCalendarURL = (java.lang.String) _jspx_page_context.findAttribute("updateCalendarURL");
          out.write('\n');
          out.write('\n');
          //  aui:form
          com.liferay.taglib.aui.FormTag _jspx_th_aui_form_0 = (com.liferay.taglib.aui.FormTag) _jspx_tagPool_aui_form_name_method_cssClass_action.get(com.liferay.taglib.aui.FormTag.class);
          _jspx_th_aui_form_0.setPageContext(_jspx_page_context);
          _jspx_th_aui_form_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_0);
          _jspx_th_aui_form_0.setAction( updateCalendarURL );
          _jspx_th_aui_form_0.setCssClass("container-fluid-1280");
          _jspx_th_aui_form_0.setMethod("post");
          _jspx_th_aui_form_0.setName("fm");
          int _jspx_eval_aui_form_0 = _jspx_th_aui_form_0.doStartTag();
          if (_jspx_eval_aui_form_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write('\n');
            out.write('	');
            if (_jspx_meth_aui_input_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_form_0, _jspx_page_context))
              return;
            out.write('\n');
            out.write('	');
            //  aui:input
            com.liferay.taglib.aui.InputTag _jspx_th_aui_input_1 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
            _jspx_th_aui_input_1.setPageContext(_jspx_page_context);
            _jspx_th_aui_input_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
            _jspx_th_aui_input_1.setName("tabs2");
            _jspx_th_aui_input_1.setType("hidden");
            _jspx_th_aui_input_1.setValue( tabs2 );
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
            _jspx_th_aui_input_2.setName("redirect");
            _jspx_th_aui_input_2.setType("hidden");
            _jspx_th_aui_input_2.setValue( redirect );
            int _jspx_eval_aui_input_2 = _jspx_th_aui_input_2.doStartTag();
            if (_jspx_th_aui_input_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_2);
              return;
            }
            _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_2);
            out.write('\n');
            out.write('	');
            //  aui:input
            com.liferay.taglib.aui.InputTag _jspx_th_aui_input_3 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
            _jspx_th_aui_input_3.setPageContext(_jspx_page_context);
            _jspx_th_aui_input_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
            _jspx_th_aui_input_3.setName("backURL");
            _jspx_th_aui_input_3.setType("hidden");
            _jspx_th_aui_input_3.setValue( backURL );
            int _jspx_eval_aui_input_3 = _jspx_th_aui_input_3.doStartTag();
            if (_jspx_th_aui_input_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_3);
              return;
            }
            _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_3);
            out.write('\n');
            out.write('	');
            //  aui:input
            com.liferay.taglib.aui.InputTag _jspx_th_aui_input_4 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
            _jspx_th_aui_input_4.setPageContext(_jspx_page_context);
            _jspx_th_aui_input_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
            _jspx_th_aui_input_4.setName("calendarId");
            _jspx_th_aui_input_4.setType("hidden");
            _jspx_th_aui_input_4.setValue( (calendar != null) ? String.valueOf(calendar.getCalendarId()) : StringPool.BLANK );
            int _jspx_eval_aui_input_4 = _jspx_th_aui_input_4.doStartTag();
            if (_jspx_th_aui_input_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_4);
              return;
            }
            _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_4);
            out.write('\n');
            out.write('	');
            //  aui:input
            com.liferay.taglib.aui.InputTag _jspx_th_aui_input_5 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
            _jspx_th_aui_input_5.setPageContext(_jspx_page_context);
            _jspx_th_aui_input_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
            _jspx_th_aui_input_5.setName("calendarResourceId");
            _jspx_th_aui_input_5.setType("hidden");
            _jspx_th_aui_input_5.setValue( (calendarResource != null) ? String.valueOf(calendarResource.getCalendarResourceId()) : StringPool.BLANK );
            int _jspx_eval_aui_input_5 = _jspx_th_aui_input_5.doStartTag();
            if (_jspx_th_aui_input_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_5);
              return;
            }
            _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_5);
            out.write("\n\n\t");
            //  liferay-ui:error
            com.liferay.taglib.ui.ErrorTag _jspx_th_liferay$1ui_error_0 = (com.liferay.taglib.ui.ErrorTag) _jspx_tagPool_liferay$1ui_error_message_exception_nobody.get(com.liferay.taglib.ui.ErrorTag.class);
            _jspx_th_liferay$1ui_error_0.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1ui_error_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
            _jspx_th_liferay$1ui_error_0.setException( CalendarNameException.class );
            _jspx_th_liferay$1ui_error_0.setMessage("please-enter-a-valid-name");
            int _jspx_eval_liferay$1ui_error_0 = _jspx_th_liferay$1ui_error_0.doStartTag();
            if (_jspx_th_liferay$1ui_error_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_liferay$1ui_error_message_exception_nobody.reuse(_jspx_th_liferay$1ui_error_0);
              return;
            }
            _jspx_tagPool_liferay$1ui_error_message_exception_nobody.reuse(_jspx_th_liferay$1ui_error_0);
            out.write("\n\n\t");
            //  aui:model-context
            com.liferay.taglib.aui.ModelContextTag _jspx_th_aui_model$1context_0 = (com.liferay.taglib.aui.ModelContextTag) _jspx_tagPool_aui_model$1context_model_bean_nobody.get(com.liferay.taglib.aui.ModelContextTag.class);
            _jspx_th_aui_model$1context_0.setPageContext(_jspx_page_context);
            _jspx_th_aui_model$1context_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
            _jspx_th_aui_model$1context_0.setBean( calendar );
            _jspx_th_aui_model$1context_0.setModel( Calendar.class );
            int _jspx_eval_aui_model$1context_0 = _jspx_th_aui_model$1context_0.doStartTag();
            if (_jspx_th_aui_model$1context_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_aui_model$1context_model_bean_nobody.reuse(_jspx_th_aui_model$1context_0);
              return;
            }
            _jspx_tagPool_aui_model$1context_model_bean_nobody.reuse(_jspx_th_aui_model$1context_0);
            out.write("\n\n\t");
            //  aui:fieldset
            com.liferay.taglib.aui.FieldsetTag _jspx_th_aui_fieldset_0 = (com.liferay.taglib.aui.FieldsetTag) _jspx_tagPool_aui_fieldset.get(com.liferay.taglib.aui.FieldsetTag.class);
            _jspx_th_aui_fieldset_0.setPageContext(_jspx_page_context);
            _jspx_th_aui_fieldset_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
            int _jspx_eval_aui_fieldset_0 = _jspx_th_aui_fieldset_0.doStartTag();
            if (_jspx_eval_aui_fieldset_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\t\t");
              if (_jspx_meth_aui_input_6((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_fieldset_0, _jspx_page_context))
                return;
              out.write("\n\n\t\t");
              if (_jspx_meth_aui_input_7((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_fieldset_0, _jspx_page_context))
                return;
              out.write("\n\n\t\t");
              //  aui:input
              com.liferay.taglib.aui.InputTag _jspx_th_aui_input_8 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_label_nobody.get(com.liferay.taglib.aui.InputTag.class);
              _jspx_th_aui_input_8.setPageContext(_jspx_page_context);
              _jspx_th_aui_input_8.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_0);
              _jspx_th_aui_input_8.setLabel("time-zone");
              _jspx_th_aui_input_8.setName("timeZoneId");
              _jspx_th_aui_input_8.setType("timeZone");
              _jspx_th_aui_input_8.setValue( (calendar != null) ? calendar.getTimeZoneId() : calendarResource.getTimeZoneId() );
              int _jspx_eval_aui_input_8 = _jspx_th_aui_input_8.doStartTag();
              if (_jspx_th_aui_input_8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_aui_input_value_type_name_label_nobody.reuse(_jspx_th_aui_input_8);
                return;
              }
              _jspx_tagPool_aui_input_value_type_name_label_nobody.reuse(_jspx_th_aui_input_8);
              out.write("\n\n\t\t");
              if (_jspx_meth_aui_input_9((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_fieldset_0, _jspx_page_context))
                return;
              out.write("\n\n\t\t");
              if (_jspx_meth_aui_field$1wrapper_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_fieldset_0, _jspx_page_context))
                return;
              out.write("\n\n\t\t");
              //  c:choose
              com.liferay.taglib.core.ChooseTag _jspx_th_c_choose_1 = (com.liferay.taglib.core.ChooseTag) _jspx_tagPool_c_choose.get(com.liferay.taglib.core.ChooseTag.class);
              _jspx_th_c_choose_1.setPageContext(_jspx_page_context);
              _jspx_th_c_choose_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_0);
              int _jspx_eval_c_choose_1 = _jspx_th_c_choose_1.doStartTag();
              if (_jspx_eval_c_choose_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\t\t\t");
                //  c:when
                com.liferay.taglib.core.WhenTag _jspx_th_c_when_1 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
                _jspx_th_c_when_1.setPageContext(_jspx_page_context);
                _jspx_th_c_when_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_1);
                _jspx_th_c_when_1.setTest( (calendar != null) && calendar.isDefaultCalendar() );
                int _jspx_eval_c_when_1 = _jspx_th_c_when_1.doStartTag();
                if (_jspx_eval_c_when_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  out.write("\n\t\t\t\t<input name=\"");
                  if (_jspx_meth_portlet_namespace_1((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_when_1, _jspx_page_context))
                    return;
                  out.write("defaultCalendar\" type=\"hidden\" value=\"true\" />\n\n\t\t\t\t");
                  //  aui:input
                  com.liferay.taglib.aui.InputTag _jspx_th_aui_input_10 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_name_disabled_nobody.get(com.liferay.taglib.aui.InputTag.class);
                  _jspx_th_aui_input_10.setPageContext(_jspx_page_context);
                  _jspx_th_aui_input_10.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_1);
                  _jspx_th_aui_input_10.setDisabled( true );
                  _jspx_th_aui_input_10.setName("defaultCalendar");
                  int _jspx_eval_aui_input_10 = _jspx_th_aui_input_10.doStartTag();
                  if (_jspx_th_aui_input_10.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_aui_input_name_disabled_nobody.reuse(_jspx_th_aui_input_10);
                    return;
                  }
                  _jspx_tagPool_aui_input_name_disabled_nobody.reuse(_jspx_th_aui_input_10);
                  out.write("\n\t\t\t");
                }
                if (_jspx_th_c_when_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_1);
                  return;
                }
                _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_1);
                out.write("\n\t\t\t");
                if (_jspx_meth_c_otherwise_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_choose_1, _jspx_page_context))
                  return;
                out.write("\n\t\t");
              }
              if (_jspx_th_c_choose_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_1);
                return;
              }
              _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_1);
              out.write("\n\n\t\t");
              if (_jspx_meth_aui_input_12((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_fieldset_0, _jspx_page_context))
                return;
              out.write("\n\n\t\t");
              if (_jspx_meth_aui_input_13((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_fieldset_0, _jspx_page_context))
                return;
              out.write("\n\n\t\t");
              //  c:if
              com.liferay.taglib.core.IfTag _jspx_th_c_if_1 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
              _jspx_th_c_if_1.setPageContext(_jspx_page_context);
              _jspx_th_c_if_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_0);
              _jspx_th_c_if_1.setTest( calendar == null );
              int _jspx_eval_c_if_1 = _jspx_th_c_if_1.doStartTag();
              if (_jspx_eval_c_if_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\t\t\t");
                //  aui:field-wrapper
                com.liferay.taglib.aui.FieldWrapperTag _jspx_th_aui_field$1wrapper_1 = (com.liferay.taglib.aui.FieldWrapperTag) _jspx_tagPool_aui_field$1wrapper_label.get(com.liferay.taglib.aui.FieldWrapperTag.class);
                _jspx_th_aui_field$1wrapper_1.setPageContext(_jspx_page_context);
                _jspx_th_aui_field$1wrapper_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_1);
                _jspx_th_aui_field$1wrapper_1.setLabel("permissions");
                int _jspx_eval_aui_field$1wrapper_1 = _jspx_th_aui_field$1wrapper_1.doStartTag();
                if (_jspx_eval_aui_field$1wrapper_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  out.write("\n\t\t\t\t");
                  //  liferay-ui:input-permissions
                  com.liferay.taglib.ui.InputPermissionsTag _jspx_th_liferay$1ui_input$1permissions_0 = (com.liferay.taglib.ui.InputPermissionsTag) _jspx_tagPool_liferay$1ui_input$1permissions_modelName_nobody.get(com.liferay.taglib.ui.InputPermissionsTag.class);
                  _jspx_th_liferay$1ui_input$1permissions_0.setPageContext(_jspx_page_context);
                  _jspx_th_liferay$1ui_input$1permissions_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_field$1wrapper_1);
                  _jspx_th_liferay$1ui_input$1permissions_0.setModelName( Calendar.class.getName() );
                  int _jspx_eval_liferay$1ui_input$1permissions_0 = _jspx_th_liferay$1ui_input$1permissions_0.doStartTag();
                  if (_jspx_th_liferay$1ui_input$1permissions_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_liferay$1ui_input$1permissions_modelName_nobody.reuse(_jspx_th_liferay$1ui_input$1permissions_0);
                    return;
                  }
                  _jspx_tagPool_liferay$1ui_input$1permissions_modelName_nobody.reuse(_jspx_th_liferay$1ui_input$1permissions_0);
                  out.write("\n\t\t\t");
                }
                if (_jspx_th_aui_field$1wrapper_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_aui_field$1wrapper_label.reuse(_jspx_th_aui_field$1wrapper_1);
                  return;
                }
                _jspx_tagPool_aui_field$1wrapper_label.reuse(_jspx_th_aui_field$1wrapper_1);
                out.write("\n\t\t");
              }
              if (_jspx_th_c_if_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_1);
                return;
              }
              _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_1);
              out.write("\n\n\t\t");
              //  aui:button-row
              com.liferay.taglib.aui.ButtonRowTag _jspx_th_aui_button$1row_0 = (com.liferay.taglib.aui.ButtonRowTag) _jspx_tagPool_aui_button$1row.get(com.liferay.taglib.aui.ButtonRowTag.class);
              _jspx_th_aui_button$1row_0.setPageContext(_jspx_page_context);
              _jspx_th_aui_button$1row_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_0);
              int _jspx_eval_aui_button$1row_0 = _jspx_th_aui_button$1row_0.doStartTag();
              if (_jspx_eval_aui_button$1row_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\t\t\t");
                if (_jspx_meth_aui_button_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_button$1row_0, _jspx_page_context))
                  return;
                out.write("\n\n\t\t\t");
                //  aui:button
                com.liferay.taglib.aui.ButtonTag _jspx_th_aui_button_1 = (com.liferay.taglib.aui.ButtonTag) _jspx_tagPool_aui_button_type_href_nobody.get(com.liferay.taglib.aui.ButtonTag.class);
                _jspx_th_aui_button_1.setPageContext(_jspx_page_context);
                _jspx_th_aui_button_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_button$1row_0);
                _jspx_th_aui_button_1.setHref( backURL );
                _jspx_th_aui_button_1.setType("cancel");
                int _jspx_eval_aui_button_1 = _jspx_th_aui_button_1.doStartTag();
                if (_jspx_th_aui_button_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_aui_button_type_href_nobody.reuse(_jspx_th_aui_button_1);
                  return;
                }
                _jspx_tagPool_aui_button_type_href_nobody.reuse(_jspx_th_aui_button_1);
                out.write("\n\t\t");
              }
              if (_jspx_th_aui_button$1row_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_aui_button$1row.reuse(_jspx_th_aui_button$1row_0);
                return;
              }
              _jspx_tagPool_aui_button$1row.reuse(_jspx_th_aui_button$1row_0);
              out.write('\n');
              out.write('	');
            }
            if (_jspx_th_aui_fieldset_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_aui_fieldset.reuse(_jspx_th_aui_fieldset_0);
              return;
            }
            _jspx_tagPool_aui_fieldset.reuse(_jspx_th_aui_fieldset_0);
            out.write('\n');
          }
          if (_jspx_th_aui_form_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_aui_form_name_method_cssClass_action.reuse(_jspx_th_aui_form_0);
            return;
          }
          _jspx_tagPool_aui_form_name_method_cssClass_action.reuse(_jspx_th_aui_form_0);
          out.write('\n');
          out.write('\n');
          if (_jspx_meth_aui_script_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_when_0, _jspx_page_context))
            return;
          out.write('\n');
          out.write('\n');
          //  aui:script
          com.liferay.taglib.aui.ScriptTag _jspx_th_aui_script_1 = (com.liferay.taglib.aui.ScriptTag) _jspx_tagPool_aui_script_use.get(com.liferay.taglib.aui.ScriptTag.class);
          _jspx_th_aui_script_1.setPageContext(_jspx_page_context);
          _jspx_th_aui_script_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_0);
          _jspx_th_aui_script_1.setUse("liferay-calendar-simple-color-picker");
          int _jspx_eval_aui_script_1 = _jspx_th_aui_script_1.doStartTag();
          if (_jspx_eval_aui_script_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            if (_jspx_eval_aui_script_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
              out = _jspx_page_context.pushBody();
              _jspx_th_aui_script_1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
              _jspx_th_aui_script_1.doInitBody();
            }
            do {
              out.write("\n\twindow.");
              if (_jspx_meth_portlet_namespace_4((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
                return;
              out.write("colorPicker = new Liferay.SimpleColorPicker(\n\t\t{\n\t\t\tcolor: '");
              out.print( ColorUtil.toHexString((calendar != null) ? calendar.getColor() : CalendarServiceConfigurationValues.CALENDAR_COLOR_DEFAULT) );
              out.write("',\n\t\t\ton: {\n\t\t\t\tcolorChange: function(event) {\n\t\t\t\t\tA.one('#");
              if (_jspx_meth_portlet_namespace_5((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
                return;
              out.write("color').val(parseInt(event.newVal.substring(1), 16));\n\t\t\t\t}\n\t\t\t},\n\t\t\trender: '#");
              if (_jspx_meth_portlet_namespace_6((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
                return;
              out.write("colorPicker'\n\t\t}\n\t);\n");
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
        _jspx_th_c_when_2.setTest( tabs2.equals("notification-templates") );
        int _jspx_eval_c_when_2 = _jspx_th_c_when_2.doStartTag();
        if (_jspx_eval_c_when_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          out.write("\n\t\t");
          out.write('\n');
          out.write('\n');

String tabs3 = ParamUtil.getString(request, "tabs3", "invite-email");

String[] tabs3Values = StringUtil.split(tabs3, StringPool.DASH);

NotificationTemplateType notificationTemplateType = NotificationTemplateType.parse(tabs3Values[0]);
NotificationType notificationType = NotificationType.parse(tabs3Values[1]);

CalendarNotificationTemplate calendarNotificationTemplate = CalendarNotificationTemplateLocalServiceUtil.fetchCalendarNotificationTemplate(calendar.getCalendarId(), notificationType, notificationTemplateType);

long calendarNotificationTemplateId = BeanPropertiesUtil.getLong(calendarNotificationTemplate, "calendarNotificationTemplateId");

String fromAddress = NotificationUtil.getTemplatePropertyValue(calendarNotificationTemplate, CalendarNotificationTemplateConstants.PROPERTY_FROM_ADDRESS, PrefsPropsUtil.getString(PropsKeys.ADMIN_EMAIL_FROM_ADDRESS));
String fromName = NotificationUtil.getTemplatePropertyValue(calendarNotificationTemplate, CalendarNotificationTemplateConstants.PROPERTY_FROM_NAME, PrefsPropsUtil.getString(PropsKeys.ADMIN_EMAIL_FROM_NAME));
String notificationTemplateBody = BeanPropertiesUtil.getString(calendarNotificationTemplate, "body", NotificationUtil.getDefaultTemplate(notificationType, notificationTemplateType, NotificationField.BODY));
String notificationTemplateSubject = BeanPropertiesUtil.getString(calendarNotificationTemplate, "subject", NotificationUtil.getDefaultTemplate(notificationType, notificationTemplateType, NotificationField.SUBJECT));

          out.write('\n');
          out.write('\n');
          //  liferay-ui:header
          com.liferay.taglib.ui.HeaderTag _jspx_th_liferay$1ui_header_1 = (com.liferay.taglib.ui.HeaderTag) _jspx_tagPool_liferay$1ui_header_title_cssClass_backURL_nobody.get(com.liferay.taglib.ui.HeaderTag.class);
          _jspx_th_liferay$1ui_header_1.setPageContext(_jspx_page_context);
          _jspx_th_liferay$1ui_header_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_2);
          _jspx_th_liferay$1ui_header_1.setBackURL( backURL );
          _jspx_th_liferay$1ui_header_1.setCssClass("container-fluid-1280");
          _jspx_th_liferay$1ui_header_1.setTitle( calendarName );
          int _jspx_eval_liferay$1ui_header_1 = _jspx_th_liferay$1ui_header_1.doStartTag();
          if (_jspx_th_liferay$1ui_header_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_liferay$1ui_header_title_cssClass_backURL_nobody.reuse(_jspx_th_liferay$1ui_header_1);
            return;
          }
          _jspx_tagPool_liferay$1ui_header_title_cssClass_backURL_nobody.reuse(_jspx_th_liferay$1ui_header_1);
          out.write('\n');
          out.write('\n');
          //  aui:nav-bar
          com.liferay.taglib.aui.NavBarTag _jspx_th_aui_nav$1bar_1 = (com.liferay.taglib.aui.NavBarTag) _jspx_tagPool_aui_nav$1bar_markupView_cssClass.get(com.liferay.taglib.aui.NavBarTag.class);
          _jspx_th_aui_nav$1bar_1.setPageContext(_jspx_page_context);
          _jspx_th_aui_nav$1bar_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_2);
          _jspx_th_aui_nav$1bar_1.setCssClass("container-fluid-1280");
          _jspx_th_aui_nav$1bar_1.setMarkupView("lexicon");
          int _jspx_eval_aui_nav$1bar_1 = _jspx_th_aui_nav$1bar_1.doStartTag();
          if (_jspx_eval_aui_nav$1bar_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            if (_jspx_eval_aui_nav$1bar_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
              out = _jspx_page_context.pushBody();
              _jspx_th_aui_nav$1bar_1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
              _jspx_th_aui_nav$1bar_1.doInitBody();
            }
            do {
              out.write('\n');
              out.write('	');
              //  aui:nav
              com.liferay.taglib.aui.NavTag _jspx_th_aui_nav_1 = (com.liferay.taglib.aui.NavTag) _jspx_tagPool_aui_nav_cssClass.get(com.liferay.taglib.aui.NavTag.class);
              _jspx_th_aui_nav_1.setPageContext(_jspx_page_context);
              _jspx_th_aui_nav_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_nav$1bar_1);
              _jspx_th_aui_nav_1.setCssClass("navbar-nav");
              int _jspx_eval_aui_nav_1 = _jspx_th_aui_nav_1.doStartTag();
              if (_jspx_eval_aui_nav_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                if (_jspx_eval_aui_nav_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                  out = _jspx_page_context.pushBody();
                  _jspx_th_aui_nav_1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                  _jspx_th_aui_nav_1.doInitBody();
                }
                do {
                  out.write("\n\n\t\t");

		navigationURL.setParameter("tabs3", "invite-email");
		
                  out.write("\n\n\t\t");
                  //  aui:nav-item
                  com.liferay.taglib.aui.NavItemTag _jspx_th_aui_nav$1item_2 = (com.liferay.taglib.aui.NavItemTag) _jspx_tagPool_aui_nav$1item_selected_label_href_nobody.get(com.liferay.taglib.aui.NavItemTag.class);
                  _jspx_th_aui_nav$1item_2.setPageContext(_jspx_page_context);
                  _jspx_th_aui_nav$1item_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_nav_1);
                  _jspx_th_aui_nav$1item_2.setHref( navigationURL.toString() );
                  _jspx_th_aui_nav$1item_2.setLabel("invite-email");
                  _jspx_th_aui_nav$1item_2.setSelected( tabs3.equals("invite-email") );
                  int _jspx_eval_aui_nav$1item_2 = _jspx_th_aui_nav$1item_2.doStartTag();
                  if (_jspx_th_aui_nav$1item_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_aui_nav$1item_selected_label_href_nobody.reuse(_jspx_th_aui_nav$1item_2);
                    return;
                  }
                  _jspx_tagPool_aui_nav$1item_selected_label_href_nobody.reuse(_jspx_th_aui_nav$1item_2);
                  out.write("\n\n\t\t");

		navigationURL.setParameter("tabs3", "reminder-email");
		
                  out.write("\n\n\t\t");
                  //  aui:nav-item
                  com.liferay.taglib.aui.NavItemTag _jspx_th_aui_nav$1item_3 = (com.liferay.taglib.aui.NavItemTag) _jspx_tagPool_aui_nav$1item_selected_label_href_nobody.get(com.liferay.taglib.aui.NavItemTag.class);
                  _jspx_th_aui_nav$1item_3.setPageContext(_jspx_page_context);
                  _jspx_th_aui_nav$1item_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_nav_1);
                  _jspx_th_aui_nav$1item_3.setHref( navigationURL.toString() );
                  _jspx_th_aui_nav$1item_3.setLabel("reminder-email");
                  _jspx_th_aui_nav$1item_3.setSelected( tabs3.equals("reminder-email") );
                  int _jspx_eval_aui_nav$1item_3 = _jspx_th_aui_nav$1item_3.doStartTag();
                  if (_jspx_th_aui_nav$1item_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_aui_nav$1item_selected_label_href_nobody.reuse(_jspx_th_aui_nav$1item_3);
                    return;
                  }
                  _jspx_tagPool_aui_nav$1item_selected_label_href_nobody.reuse(_jspx_th_aui_nav$1item_3);
                  out.write('\n');
                  out.write('	');
                  int evalDoAfterBody = _jspx_th_aui_nav_1.doAfterBody();
                  if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                    break;
                } while (true);
                if (_jspx_eval_aui_nav_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                  out = _jspx_page_context.popBody();
              }
              if (_jspx_th_aui_nav_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_aui_nav_cssClass.reuse(_jspx_th_aui_nav_1);
                return;
              }
              _jspx_tagPool_aui_nav_cssClass.reuse(_jspx_th_aui_nav_1);
              out.write('\n');
              int evalDoAfterBody = _jspx_th_aui_nav$1bar_1.doAfterBody();
              if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                break;
            } while (true);
            if (_jspx_eval_aui_nav$1bar_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
              out = _jspx_page_context.popBody();
          }
          if (_jspx_th_aui_nav$1bar_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_aui_nav$1bar_markupView_cssClass.reuse(_jspx_th_aui_nav$1bar_1);
            return;
          }
          _jspx_tagPool_aui_nav$1bar_markupView_cssClass.reuse(_jspx_th_aui_nav$1bar_1);
          out.write('\n');
          out.write('\n');
          //  liferay-portlet:actionURL
          com.liferay.taglib.portlet.ActionURLTag _jspx_th_liferay$1portlet_actionURL_1 = (com.liferay.taglib.portlet.ActionURLTag) _jspx_tagPool_liferay$1portlet_actionURL_var_name_nobody.get(com.liferay.taglib.portlet.ActionURLTag.class);
          _jspx_th_liferay$1portlet_actionURL_1.setPageContext(_jspx_page_context);
          _jspx_th_liferay$1portlet_actionURL_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_2);
          _jspx_th_liferay$1portlet_actionURL_1.setName("updateCalendarNotificationTemplate");
          _jspx_th_liferay$1portlet_actionURL_1.setVar("updateCalendarNotificationTemplateURL");
          int _jspx_eval_liferay$1portlet_actionURL_1 = _jspx_th_liferay$1portlet_actionURL_1.doStartTag();
          if (_jspx_th_liferay$1portlet_actionURL_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_liferay$1portlet_actionURL_var_name_nobody.reuse(_jspx_th_liferay$1portlet_actionURL_1);
            return;
          }
          _jspx_tagPool_liferay$1portlet_actionURL_var_name_nobody.reuse(_jspx_th_liferay$1portlet_actionURL_1);
          java.lang.String updateCalendarNotificationTemplateURL = null;
          updateCalendarNotificationTemplateURL = (java.lang.String) _jspx_page_context.findAttribute("updateCalendarNotificationTemplateURL");
          out.write('\n');
          out.write('\n');
          //  aui:form
          com.liferay.taglib.aui.FormTag _jspx_th_aui_form_1 = (com.liferay.taglib.aui.FormTag) _jspx_tagPool_aui_form_onSubmit_name_method_cssClass_action.get(com.liferay.taglib.aui.FormTag.class);
          _jspx_th_aui_form_1.setPageContext(_jspx_page_context);
          _jspx_th_aui_form_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_2);
          _jspx_th_aui_form_1.setAction( updateCalendarNotificationTemplateURL );
          _jspx_th_aui_form_1.setCssClass("container-fluid-1280");
          _jspx_th_aui_form_1.setMethod("post");
          _jspx_th_aui_form_1.setName("fm");
          _jspx_th_aui_form_1.setOnSubmit( "event.preventDefault(); " + renderResponse.getNamespace() + "updateCalendarNotificationTemplate();" );
          int _jspx_eval_aui_form_1 = _jspx_th_aui_form_1.doStartTag();
          if (_jspx_eval_aui_form_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write('\n');
            out.write('	');
            if (_jspx_meth_aui_input_14((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_form_1, _jspx_page_context))
              return;
            out.write('\n');
            out.write('	');
            //  aui:input
            com.liferay.taglib.aui.InputTag _jspx_th_aui_input_15 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
            _jspx_th_aui_input_15.setPageContext(_jspx_page_context);
            _jspx_th_aui_input_15.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_1);
            _jspx_th_aui_input_15.setName("calendarNotificationTemplateId");
            _jspx_th_aui_input_15.setType("hidden");
            _jspx_th_aui_input_15.setValue( calendarNotificationTemplateId );
            int _jspx_eval_aui_input_15 = _jspx_th_aui_input_15.doStartTag();
            if (_jspx_th_aui_input_15.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_15);
              return;
            }
            _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_15);
            out.write('\n');
            out.write('	');
            //  aui:input
            com.liferay.taglib.aui.InputTag _jspx_th_aui_input_16 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
            _jspx_th_aui_input_16.setPageContext(_jspx_page_context);
            _jspx_th_aui_input_16.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_1);
            _jspx_th_aui_input_16.setName("tabs2");
            _jspx_th_aui_input_16.setType("hidden");
            _jspx_th_aui_input_16.setValue( tabs2 );
            int _jspx_eval_aui_input_16 = _jspx_th_aui_input_16.doStartTag();
            if (_jspx_th_aui_input_16.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_16);
              return;
            }
            _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_16);
            out.write('\n');
            out.write('	');
            //  aui:input
            com.liferay.taglib.aui.InputTag _jspx_th_aui_input_17 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
            _jspx_th_aui_input_17.setPageContext(_jspx_page_context);
            _jspx_th_aui_input_17.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_1);
            _jspx_th_aui_input_17.setName("tabs3");
            _jspx_th_aui_input_17.setType("hidden");
            _jspx_th_aui_input_17.setValue( tabs3 );
            int _jspx_eval_aui_input_17 = _jspx_th_aui_input_17.doStartTag();
            if (_jspx_th_aui_input_17.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_17);
              return;
            }
            _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_17);
            out.write('\n');
            out.write('	');
            //  aui:input
            com.liferay.taglib.aui.InputTag _jspx_th_aui_input_18 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
            _jspx_th_aui_input_18.setPageContext(_jspx_page_context);
            _jspx_th_aui_input_18.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_1);
            _jspx_th_aui_input_18.setName("redirect");
            _jspx_th_aui_input_18.setType("hidden");
            _jspx_th_aui_input_18.setValue( currentURL );
            int _jspx_eval_aui_input_18 = _jspx_th_aui_input_18.doStartTag();
            if (_jspx_th_aui_input_18.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_18);
              return;
            }
            _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_18);
            out.write('\n');
            out.write('	');
            //  aui:input
            com.liferay.taglib.aui.InputTag _jspx_th_aui_input_19 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
            _jspx_th_aui_input_19.setPageContext(_jspx_page_context);
            _jspx_th_aui_input_19.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_1);
            _jspx_th_aui_input_19.setName("calendarId");
            _jspx_th_aui_input_19.setType("hidden");
            _jspx_th_aui_input_19.setValue( String.valueOf(calendar.getCalendarId()) );
            int _jspx_eval_aui_input_19 = _jspx_th_aui_input_19.doStartTag();
            if (_jspx_th_aui_input_19.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_19);
              return;
            }
            _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_19);
            out.write('\n');
            out.write('	');
            //  aui:input
            com.liferay.taglib.aui.InputTag _jspx_th_aui_input_20 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
            _jspx_th_aui_input_20.setPageContext(_jspx_page_context);
            _jspx_th_aui_input_20.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_1);
            _jspx_th_aui_input_20.setName("calendarResourceId");
            _jspx_th_aui_input_20.setType("hidden");
            _jspx_th_aui_input_20.setValue( (calendarResource != null) ? String.valueOf(calendarResource.getCalendarResourceId()) : StringPool.BLANK );
            int _jspx_eval_aui_input_20 = _jspx_th_aui_input_20.doStartTag();
            if (_jspx_th_aui_input_20.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_20);
              return;
            }
            _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_20);
            out.write('\n');
            out.write('	');
            //  aui:input
            com.liferay.taglib.aui.InputTag _jspx_th_aui_input_21 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
            _jspx_th_aui_input_21.setPageContext(_jspx_page_context);
            _jspx_th_aui_input_21.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_1);
            _jspx_th_aui_input_21.setName("notificationType");
            _jspx_th_aui_input_21.setType("hidden");
            _jspx_th_aui_input_21.setValue( notificationType.getValue() );
            int _jspx_eval_aui_input_21 = _jspx_th_aui_input_21.doStartTag();
            if (_jspx_th_aui_input_21.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_21);
              return;
            }
            _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_21);
            out.write('\n');
            out.write('	');
            //  aui:input
            com.liferay.taglib.aui.InputTag _jspx_th_aui_input_22 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
            _jspx_th_aui_input_22.setPageContext(_jspx_page_context);
            _jspx_th_aui_input_22.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_1);
            _jspx_th_aui_input_22.setName("notificationTemplateType");
            _jspx_th_aui_input_22.setType("hidden");
            _jspx_th_aui_input_22.setValue( notificationTemplateType.getValue() );
            int _jspx_eval_aui_input_22 = _jspx_th_aui_input_22.doStartTag();
            if (_jspx_th_aui_input_22.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_22);
              return;
            }
            _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_22);
            out.write("\n\n\t");
            //  aui:model-context
            com.liferay.taglib.aui.ModelContextTag _jspx_th_aui_model$1context_1 = (com.liferay.taglib.aui.ModelContextTag) _jspx_tagPool_aui_model$1context_model_bean_nobody.get(com.liferay.taglib.aui.ModelContextTag.class);
            _jspx_th_aui_model$1context_1.setPageContext(_jspx_page_context);
            _jspx_th_aui_model$1context_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_1);
            _jspx_th_aui_model$1context_1.setBean( calendarNotificationTemplate );
            _jspx_th_aui_model$1context_1.setModel( CalendarNotificationTemplate.class );
            int _jspx_eval_aui_model$1context_1 = _jspx_th_aui_model$1context_1.doStartTag();
            if (_jspx_th_aui_model$1context_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_aui_model$1context_model_bean_nobody.reuse(_jspx_th_aui_model$1context_1);
              return;
            }
            _jspx_tagPool_aui_model$1context_model_bean_nobody.reuse(_jspx_th_aui_model$1context_1);
            out.write("\n\n\t");
            //  aui:fieldset
            com.liferay.taglib.aui.FieldsetTag _jspx_th_aui_fieldset_1 = (com.liferay.taglib.aui.FieldsetTag) _jspx_tagPool_aui_fieldset.get(com.liferay.taglib.aui.FieldsetTag.class);
            _jspx_th_aui_fieldset_1.setPageContext(_jspx_page_context);
            _jspx_th_aui_fieldset_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_1);
            int _jspx_eval_aui_fieldset_1 = _jspx_th_aui_fieldset_1.doStartTag();
            if (_jspx_eval_aui_fieldset_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\t\t");
              //  aui:input
              com.liferay.taglib.aui.InputTag _jspx_th_aui_input_23 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
              _jspx_th_aui_input_23.setPageContext(_jspx_page_context);
              _jspx_th_aui_input_23.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_1);
              _jspx_th_aui_input_23.setName("fromName");
              _jspx_th_aui_input_23.setValue( fromName );
              int _jspx_eval_aui_input_23 = _jspx_th_aui_input_23.doStartTag();
              if (_jspx_th_aui_input_23.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_aui_input_value_name_nobody.reuse(_jspx_th_aui_input_23);
                return;
              }
              _jspx_tagPool_aui_input_value_name_nobody.reuse(_jspx_th_aui_input_23);
              out.write("\n\n\t\t");
              //  aui:input
              com.liferay.taglib.aui.InputTag _jspx_th_aui_input_24 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
              _jspx_th_aui_input_24.setPageContext(_jspx_page_context);
              _jspx_th_aui_input_24.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_1);
              _jspx_th_aui_input_24.setName("fromAddress");
              _jspx_th_aui_input_24.setValue( fromAddress );
              int _jspx_eval_aui_input_24 = _jspx_th_aui_input_24.doStartTag();
              if (_jspx_th_aui_input_24.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_aui_input_value_name_nobody.reuse(_jspx_th_aui_input_24);
                return;
              }
              _jspx_tagPool_aui_input_value_name_nobody.reuse(_jspx_th_aui_input_24);
              out.write("\n\n\t\t");
              //  aui:input
              com.liferay.taglib.aui.InputTag _jspx_th_aui_input_25 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
              _jspx_th_aui_input_25.setPageContext(_jspx_page_context);
              _jspx_th_aui_input_25.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_1);
              _jspx_th_aui_input_25.setName("subject");
              _jspx_th_aui_input_25.setValue( notificationTemplateSubject );
              int _jspx_eval_aui_input_25 = _jspx_th_aui_input_25.doStartTag();
              if (_jspx_th_aui_input_25.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_aui_input_value_name_nobody.reuse(_jspx_th_aui_input_25);
                return;
              }
              _jspx_tagPool_aui_input_value_name_nobody.reuse(_jspx_th_aui_input_25);
              out.write("\n\n\t\t");
              //  aui:field-wrapper
              com.liferay.taglib.aui.FieldWrapperTag _jspx_th_aui_field$1wrapper_2 = (com.liferay.taglib.aui.FieldWrapperTag) _jspx_tagPool_aui_field$1wrapper_label.get(com.liferay.taglib.aui.FieldWrapperTag.class);
              _jspx_th_aui_field$1wrapper_2.setPageContext(_jspx_page_context);
              _jspx_th_aui_field$1wrapper_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_1);
              _jspx_th_aui_field$1wrapper_2.setLabel("body");
              int _jspx_eval_aui_field$1wrapper_2 = _jspx_th_aui_field$1wrapper_2.doStartTag();
              if (_jspx_eval_aui_field$1wrapper_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\t\t\t");
                //  liferay-ui:input-editor
                com.liferay.taglib.ui.InputEditorTag _jspx_th_liferay$1ui_input$1editor_0 = (com.liferay.taglib.ui.InputEditorTag) _jspx_tagPool_liferay$1ui_input$1editor_editorImpl_contents_nobody.get(com.liferay.taglib.ui.InputEditorTag.class);
                _jspx_th_liferay$1ui_input$1editor_0.setPageContext(_jspx_page_context);
                _jspx_th_liferay$1ui_input$1editor_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_field$1wrapper_2);
                _jspx_th_liferay$1ui_input$1editor_0.setContents( notificationTemplateBody );
                _jspx_th_liferay$1ui_input$1editor_0.setEditorImpl("ckeditor");
                int _jspx_eval_liferay$1ui_input$1editor_0 = _jspx_th_liferay$1ui_input$1editor_0.doStartTag();
                if (_jspx_th_liferay$1ui_input$1editor_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_liferay$1ui_input$1editor_editorImpl_contents_nobody.reuse(_jspx_th_liferay$1ui_input$1editor_0);
                  return;
                }
                _jspx_tagPool_liferay$1ui_input$1editor_editorImpl_contents_nobody.reuse(_jspx_th_liferay$1ui_input$1editor_0);
                out.write("\n\n\t\t\t");
                //  aui:input
                com.liferay.taglib.aui.InputTag _jspx_th_aui_input_26 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
                _jspx_th_aui_input_26.setPageContext(_jspx_page_context);
                _jspx_th_aui_input_26.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_field$1wrapper_2);
                _jspx_th_aui_input_26.setName("body");
                _jspx_th_aui_input_26.setType("hidden");
                _jspx_th_aui_input_26.setValue( notificationTemplateBody );
                int _jspx_eval_aui_input_26 = _jspx_th_aui_input_26.doStartTag();
                if (_jspx_th_aui_input_26.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_26);
                  return;
                }
                _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_26);
                out.write("\n\t\t");
              }
              if (_jspx_th_aui_field$1wrapper_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_aui_field$1wrapper_label.reuse(_jspx_th_aui_field$1wrapper_2);
                return;
              }
              _jspx_tagPool_aui_field$1wrapper_label.reuse(_jspx_th_aui_field$1wrapper_2);
              out.write('\n');
              out.write('	');
            }
            if (_jspx_th_aui_fieldset_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_aui_fieldset.reuse(_jspx_th_aui_fieldset_1);
              return;
            }
            _jspx_tagPool_aui_fieldset.reuse(_jspx_th_aui_fieldset_1);
            out.write("\n\n\t<div class=\"definition-of-terms\">\n\t\t<h4>");
            if (_jspx_meth_liferay$1ui_message_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_form_1, _jspx_page_context))
              return;
            out.write("</h4>\n\n\t\t<dl>\n\t\t\t<dt>\n\t\t\t\t[$EVENT_LOCATION$]\n\t\t\t</dt>\n\t\t\t<dd>\n\t\t\t\t");
            if (_jspx_meth_liferay$1ui_message_1((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_form_1, _jspx_page_context))
              return;
            out.write("\n\t\t\t</dd>\n\t\t\t<dt>\n\t\t\t\t[$EVENT_START_DATE$]\n\t\t\t</dt>\n\t\t\t<dd>\n\t\t\t\t");
            if (_jspx_meth_liferay$1ui_message_2((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_form_1, _jspx_page_context))
              return;
            out.write("\n\t\t\t</dd>\n\t\t\t<dt>\n\t\t\t\t[$EVENT_TITLE$]\n\t\t\t</dt>\n\t\t\t<dd>\n\t\t\t\t");
            if (_jspx_meth_liferay$1ui_message_3((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_form_1, _jspx_page_context))
              return;
            out.write("\n\t\t\t</dd>\n\n\t\t\t");
            //  c:if
            com.liferay.taglib.core.IfTag _jspx_th_c_if_2 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
            _jspx_th_c_if_2.setPageContext(_jspx_page_context);
            _jspx_th_c_if_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_1);
            _jspx_th_c_if_2.setTest( notificationTemplateType == NotificationTemplateType.INVITE );
            int _jspx_eval_c_if_2 = _jspx_th_c_if_2.doStartTag();
            if (_jspx_eval_c_if_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\t\t\t\t<dt>\n\t\t\t\t\t[$EVENT_URL$]\n\t\t\t\t</dt>\n\t\t\t\t<dd>\n\t\t\t\t\t");
              if (_jspx_meth_liferay$1ui_message_4((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_2, _jspx_page_context))
                return;
              out.write("\n\t\t\t\t</dd>\n\t\t\t");
            }
            if (_jspx_th_c_if_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_2);
              return;
            }
            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_2);
            out.write("\n\n\t\t\t<dt>\n\t\t\t\t[$FROM_ADDRESS$]\n\t\t\t</dt>\n\t\t\t<dd>\n\t\t\t\t");
            out.print( HtmlUtil.escape(fromAddress) );
            out.write("\n\t\t\t</dd>\n\t\t\t<dt>\n\t\t\t\t[$FROM_NAME$]\n\t\t\t</dt>\n\t\t\t<dd>\n\t\t\t\t");
            out.print( HtmlUtil.escape(fromName) );
            out.write("\n\t\t\t</dd>\n\t\t\t<dt>\n\t\t\t\t[$PORTAL_URL$]\n\t\t\t</dt>\n\t\t\t<dd>\n\t\t\t\t");
            out.print( company.getVirtualHostname() );
            out.write("\n\t\t\t</dd>\n\t\t\t<dt>\n\t\t\t\t[$TO_ADDRESS$]\n\t\t\t</dt>\n\t\t\t<dd>\n\t\t\t\t");
            if (_jspx_meth_liferay$1ui_message_5((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_form_1, _jspx_page_context))
              return;
            out.write("\n\t\t\t</dd>\n\t\t\t<dt>\n\t\t\t\t[$TO_NAME$]\n\t\t\t</dt>\n\t\t\t<dd>\n\t\t\t\t");
            if (_jspx_meth_liferay$1ui_message_6((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_form_1, _jspx_page_context))
              return;
            out.write("\n\t\t\t</dd>\n\t\t</dl>\n\t</div>\n\n\t");
            //  aui:button-row
            com.liferay.taglib.aui.ButtonRowTag _jspx_th_aui_button$1row_1 = (com.liferay.taglib.aui.ButtonRowTag) _jspx_tagPool_aui_button$1row.get(com.liferay.taglib.aui.ButtonRowTag.class);
            _jspx_th_aui_button$1row_1.setPageContext(_jspx_page_context);
            _jspx_th_aui_button$1row_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_1);
            int _jspx_eval_aui_button$1row_1 = _jspx_th_aui_button$1row_1.doStartTag();
            if (_jspx_eval_aui_button$1row_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\t\t");
              if (_jspx_meth_aui_button_2((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_button$1row_1, _jspx_page_context))
                return;
              out.write("\n\n\t\t");
              //  aui:button
              com.liferay.taglib.aui.ButtonTag _jspx_th_aui_button_3 = (com.liferay.taglib.aui.ButtonTag) _jspx_tagPool_aui_button_type_href_nobody.get(com.liferay.taglib.aui.ButtonTag.class);
              _jspx_th_aui_button_3.setPageContext(_jspx_page_context);
              _jspx_th_aui_button_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_button$1row_1);
              _jspx_th_aui_button_3.setHref( redirect );
              _jspx_th_aui_button_3.setType("cancel");
              int _jspx_eval_aui_button_3 = _jspx_th_aui_button_3.doStartTag();
              if (_jspx_th_aui_button_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_aui_button_type_href_nobody.reuse(_jspx_th_aui_button_3);
                return;
              }
              _jspx_tagPool_aui_button_type_href_nobody.reuse(_jspx_th_aui_button_3);
              out.write('\n');
              out.write('	');
            }
            if (_jspx_th_aui_button$1row_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_aui_button$1row.reuse(_jspx_th_aui_button$1row_1);
              return;
            }
            _jspx_tagPool_aui_button$1row.reuse(_jspx_th_aui_button$1row_1);
            out.write('\n');
          }
          if (_jspx_th_aui_form_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_aui_form_onSubmit_name_method_cssClass_action.reuse(_jspx_th_aui_form_1);
            return;
          }
          _jspx_tagPool_aui_form_onSubmit_name_method_cssClass_action.reuse(_jspx_th_aui_form_1);
          out.write('\n');
          out.write('\n');
          if (_jspx_meth_aui_script_2((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_when_2, _jspx_page_context))
            return;
          out.write('\n');
          out.write('	');
        }
        if (_jspx_th_c_when_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_2);
          return;
        }
        _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_2);
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

  private boolean _jspx_meth_aui_input_0(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_form_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:input
    com.liferay.taglib.aui.InputTag _jspx_th_aui_input_0 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
    _jspx_th_aui_input_0.setPageContext(_jspx_page_context);
    _jspx_th_aui_input_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
    _jspx_th_aui_input_0.setName("mvcPath");
    _jspx_th_aui_input_0.setType("hidden");
    _jspx_th_aui_input_0.setValue(new String("/edit_calendar.jsp"));
    int _jspx_eval_aui_input_0 = _jspx_th_aui_input_0.doStartTag();
    if (_jspx_th_aui_input_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_0);
      return true;
    }
    _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_0);
    return false;
  }

  private boolean _jspx_meth_aui_input_6(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_fieldset_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:input
    com.liferay.taglib.aui.InputTag _jspx_th_aui_input_6 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
    _jspx_th_aui_input_6.setPageContext(_jspx_page_context);
    _jspx_th_aui_input_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_0);
    _jspx_th_aui_input_6.setName("name");
    int _jspx_eval_aui_input_6 = _jspx_th_aui_input_6.doStartTag();
    if (_jspx_th_aui_input_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_input_name_nobody.reuse(_jspx_th_aui_input_6);
      return true;
    }
    _jspx_tagPool_aui_input_name_nobody.reuse(_jspx_th_aui_input_6);
    return false;
  }

  private boolean _jspx_meth_aui_input_7(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_fieldset_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:input
    com.liferay.taglib.aui.InputTag _jspx_th_aui_input_7 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
    _jspx_th_aui_input_7.setPageContext(_jspx_page_context);
    _jspx_th_aui_input_7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_0);
    _jspx_th_aui_input_7.setName("description");
    int _jspx_eval_aui_input_7 = _jspx_th_aui_input_7.doStartTag();
    if (_jspx_th_aui_input_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_input_name_nobody.reuse(_jspx_th_aui_input_7);
      return true;
    }
    _jspx_tagPool_aui_input_name_nobody.reuse(_jspx_th_aui_input_7);
    return false;
  }

  private boolean _jspx_meth_aui_input_9(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_fieldset_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:input
    com.liferay.taglib.aui.InputTag _jspx_th_aui_input_9 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
    _jspx_th_aui_input_9.setPageContext(_jspx_page_context);
    _jspx_th_aui_input_9.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_0);
    _jspx_th_aui_input_9.setName("color");
    _jspx_th_aui_input_9.setType("hidden");
    int _jspx_eval_aui_input_9 = _jspx_th_aui_input_9.doStartTag();
    if (_jspx_th_aui_input_9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_input_type_name_nobody.reuse(_jspx_th_aui_input_9);
      return true;
    }
    _jspx_tagPool_aui_input_type_name_nobody.reuse(_jspx_th_aui_input_9);
    return false;
  }

  private boolean _jspx_meth_aui_field$1wrapper_0(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_fieldset_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:field-wrapper
    com.liferay.taglib.aui.FieldWrapperTag _jspx_th_aui_field$1wrapper_0 = (com.liferay.taglib.aui.FieldWrapperTag) _jspx_tagPool_aui_field$1wrapper_label_inlineLabel.get(com.liferay.taglib.aui.FieldWrapperTag.class);
    _jspx_th_aui_field$1wrapper_0.setPageContext(_jspx_page_context);
    _jspx_th_aui_field$1wrapper_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_0);
    _jspx_th_aui_field$1wrapper_0.setInlineLabel("left");
    _jspx_th_aui_field$1wrapper_0.setLabel("color");
    int _jspx_eval_aui_field$1wrapper_0 = _jspx_th_aui_field$1wrapper_0.doStartTag();
    if (_jspx_eval_aui_field$1wrapper_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      out.write("\n\t\t\t<div class=\"calendar-portlet-colors\" id=\"");
      if (_jspx_meth_portlet_namespace_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_field$1wrapper_0, _jspx_page_context))
        return true;
      out.write("colorPicker\"></div>\n\t\t");
    }
    if (_jspx_th_aui_field$1wrapper_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_field$1wrapper_label_inlineLabel.reuse(_jspx_th_aui_field$1wrapper_0);
      return true;
    }
    _jspx_tagPool_aui_field$1wrapper_label_inlineLabel.reuse(_jspx_th_aui_field$1wrapper_0);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_0(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_field$1wrapper_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_0 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_0.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_field$1wrapper_0);
    int _jspx_eval_portlet_namespace_0 = _jspx_th_portlet_namespace_0.doStartTag();
    if (_jspx_th_portlet_namespace_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_0);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_0);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_1(javax.servlet.jsp.tagext.JspTag _jspx_th_c_when_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_1 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_1.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_1);
    int _jspx_eval_portlet_namespace_1 = _jspx_th_portlet_namespace_1.doStartTag();
    if (_jspx_th_portlet_namespace_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_1);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_1);
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
      out.write("\n\t\t\t\t");
      if (_jspx_meth_aui_input_11((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_otherwise_0, _jspx_page_context))
        return true;
      out.write("\n\t\t\t");
    }
    if (_jspx_th_c_otherwise_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_0);
      return true;
    }
    _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_0);
    return false;
  }

  private boolean _jspx_meth_aui_input_11(javax.servlet.jsp.tagext.JspTag _jspx_th_c_otherwise_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:input
    com.liferay.taglib.aui.InputTag _jspx_th_aui_input_11 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
    _jspx_th_aui_input_11.setPageContext(_jspx_page_context);
    _jspx_th_aui_input_11.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_0);
    _jspx_th_aui_input_11.setName("defaultCalendar");
    int _jspx_eval_aui_input_11 = _jspx_th_aui_input_11.doStartTag();
    if (_jspx_th_aui_input_11.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_input_name_nobody.reuse(_jspx_th_aui_input_11);
      return true;
    }
    _jspx_tagPool_aui_input_name_nobody.reuse(_jspx_th_aui_input_11);
    return false;
  }

  private boolean _jspx_meth_aui_input_12(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_fieldset_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:input
    com.liferay.taglib.aui.InputTag _jspx_th_aui_input_12 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
    _jspx_th_aui_input_12.setPageContext(_jspx_page_context);
    _jspx_th_aui_input_12.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_0);
    _jspx_th_aui_input_12.setName("enableComments");
    int _jspx_eval_aui_input_12 = _jspx_th_aui_input_12.doStartTag();
    if (_jspx_th_aui_input_12.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_input_name_nobody.reuse(_jspx_th_aui_input_12);
      return true;
    }
    _jspx_tagPool_aui_input_name_nobody.reuse(_jspx_th_aui_input_12);
    return false;
  }

  private boolean _jspx_meth_aui_input_13(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_fieldset_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:input
    com.liferay.taglib.aui.InputTag _jspx_th_aui_input_13 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
    _jspx_th_aui_input_13.setPageContext(_jspx_page_context);
    _jspx_th_aui_input_13.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_0);
    _jspx_th_aui_input_13.setName("enableRatings");
    int _jspx_eval_aui_input_13 = _jspx_th_aui_input_13.doStartTag();
    if (_jspx_th_aui_input_13.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_input_name_nobody.reuse(_jspx_th_aui_input_13);
      return true;
    }
    _jspx_tagPool_aui_input_name_nobody.reuse(_jspx_th_aui_input_13);
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

  private boolean _jspx_meth_aui_script_0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_when_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:script
    com.liferay.taglib.aui.ScriptTag _jspx_th_aui_script_0 = (com.liferay.taglib.aui.ScriptTag) _jspx_tagPool_aui_script.get(com.liferay.taglib.aui.ScriptTag.class);
    _jspx_th_aui_script_0.setPageContext(_jspx_page_context);
    _jspx_th_aui_script_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_0);
    int _jspx_eval_aui_script_0 = _jspx_th_aui_script_0.doStartTag();
    if (_jspx_eval_aui_script_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_aui_script_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_aui_script_0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_aui_script_0.doInitBody();
      }
      do {
        out.write("\n\tLiferay.Util.focusFormField(document.");
        if (_jspx_meth_portlet_namespace_2((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_0, _jspx_page_context))
          return true;
        out.write("fm.");
        if (_jspx_meth_portlet_namespace_3((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_0, _jspx_page_context))
          return true;
        out.write("name);\n");
        int evalDoAfterBody = _jspx_th_aui_script_0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_aui_script_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
        out = _jspx_page_context.popBody();
    }
    if (_jspx_th_aui_script_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_script.reuse(_jspx_th_aui_script_0);
      return true;
    }
    _jspx_tagPool_aui_script.reuse(_jspx_th_aui_script_0);
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

  private boolean _jspx_meth_portlet_namespace_3(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_3 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_3.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_0);
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

  private boolean _jspx_meth_aui_input_14(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_form_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:input
    com.liferay.taglib.aui.InputTag _jspx_th_aui_input_14 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
    _jspx_th_aui_input_14.setPageContext(_jspx_page_context);
    _jspx_th_aui_input_14.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_1);
    _jspx_th_aui_input_14.setName("mvcPath");
    _jspx_th_aui_input_14.setType("hidden");
    _jspx_th_aui_input_14.setValue(new String("/edit_calendar.jsp"));
    int _jspx_eval_aui_input_14 = _jspx_th_aui_input_14.doStartTag();
    if (_jspx_th_aui_input_14.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_14);
      return true;
    }
    _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_14);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_0(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_form_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_0 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_0.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_1);
    _jspx_th_liferay$1ui_message_0.setKey("definition-of-terms");
    int _jspx_eval_liferay$1ui_message_0 = _jspx_th_liferay$1ui_message_0.doStartTag();
    if (_jspx_th_liferay$1ui_message_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_0);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_0);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_1(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_form_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_1 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_1.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_1);
    _jspx_th_liferay$1ui_message_1.setKey("the-calendar-booking-location");
    int _jspx_eval_liferay$1ui_message_1 = _jspx_th_liferay$1ui_message_1.doStartTag();
    if (_jspx_th_liferay$1ui_message_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_1);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_1);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_2(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_form_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_2 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_2.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_1);
    _jspx_th_liferay$1ui_message_2.setKey("the-calendar-booking-start-date");
    int _jspx_eval_liferay$1ui_message_2 = _jspx_th_liferay$1ui_message_2.doStartTag();
    if (_jspx_th_liferay$1ui_message_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_2);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_2);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_3(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_form_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_3 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_3.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_1);
    _jspx_th_liferay$1ui_message_3.setKey("the-calendar-booking-title");
    int _jspx_eval_liferay$1ui_message_3 = _jspx_th_liferay$1ui_message_3.doStartTag();
    if (_jspx_th_liferay$1ui_message_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_3);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_3);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_4(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_4 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_4.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_2);
    _jspx_th_liferay$1ui_message_4.setKey("the-calendar-booking-url");
    int _jspx_eval_liferay$1ui_message_4 = _jspx_th_liferay$1ui_message_4.doStartTag();
    if (_jspx_th_liferay$1ui_message_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_4);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_4);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_5(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_form_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_5 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_5.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_1);
    _jspx_th_liferay$1ui_message_5.setKey("the-address-of-the-email-recipient");
    int _jspx_eval_liferay$1ui_message_5 = _jspx_th_liferay$1ui_message_5.doStartTag();
    if (_jspx_th_liferay$1ui_message_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_5);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_5);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_6(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_form_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_6 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_6.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_1);
    _jspx_th_liferay$1ui_message_6.setKey("the-name-of-the-email-recipient");
    int _jspx_eval_liferay$1ui_message_6 = _jspx_th_liferay$1ui_message_6.doStartTag();
    if (_jspx_th_liferay$1ui_message_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_6);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_6);
    return false;
  }

  private boolean _jspx_meth_aui_button_2(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_button$1row_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:button
    com.liferay.taglib.aui.ButtonTag _jspx_th_aui_button_2 = (com.liferay.taglib.aui.ButtonTag) _jspx_tagPool_aui_button_type_nobody.get(com.liferay.taglib.aui.ButtonTag.class);
    _jspx_th_aui_button_2.setPageContext(_jspx_page_context);
    _jspx_th_aui_button_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_button$1row_1);
    _jspx_th_aui_button_2.setType("submit");
    int _jspx_eval_aui_button_2 = _jspx_th_aui_button_2.doStartTag();
    if (_jspx_th_aui_button_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_button_type_nobody.reuse(_jspx_th_aui_button_2);
      return true;
    }
    _jspx_tagPool_aui_button_type_nobody.reuse(_jspx_th_aui_button_2);
    return false;
  }

  private boolean _jspx_meth_aui_script_2(javax.servlet.jsp.tagext.JspTag _jspx_th_c_when_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:script
    com.liferay.taglib.aui.ScriptTag _jspx_th_aui_script_2 = (com.liferay.taglib.aui.ScriptTag) _jspx_tagPool_aui_script.get(com.liferay.taglib.aui.ScriptTag.class);
    _jspx_th_aui_script_2.setPageContext(_jspx_page_context);
    _jspx_th_aui_script_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_2);
    int _jspx_eval_aui_script_2 = _jspx_th_aui_script_2.doStartTag();
    if (_jspx_eval_aui_script_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_aui_script_2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_aui_script_2.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_aui_script_2.doInitBody();
      }
      do {
        out.write("\n\tfunction ");
        if (_jspx_meth_portlet_namespace_7((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_2, _jspx_page_context))
          return true;
        out.write("updateCalendarNotificationTemplate() {\n\t\tvar notificationTemplateContentBody = document.getElementById('");
        if (_jspx_meth_portlet_namespace_8((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_2, _jspx_page_context))
          return true;
        out.write("body');\n\n\t\tif (notificationTemplateContentBody) {\n\t\t\tnotificationTemplateContentBody.value = window.");
        if (_jspx_meth_portlet_namespace_9((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_2, _jspx_page_context))
          return true;
        out.write("editor.getHTML();\n\t\t}\n\n\t\tsubmitForm(document.");
        if (_jspx_meth_portlet_namespace_10((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_2, _jspx_page_context))
          return true;
        out.write("fm);\n\t}\n");
        int evalDoAfterBody = _jspx_th_aui_script_2.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_aui_script_2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
        out = _jspx_page_context.popBody();
    }
    if (_jspx_th_aui_script_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_script.reuse(_jspx_th_aui_script_2);
      return true;
    }
    _jspx_tagPool_aui_script.reuse(_jspx_th_aui_script_2);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_7(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_7 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_7.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_2);
    int _jspx_eval_portlet_namespace_7 = _jspx_th_portlet_namespace_7.doStartTag();
    if (_jspx_th_portlet_namespace_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_7);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_7);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_8(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_8 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_8.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_8.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_2);
    int _jspx_eval_portlet_namespace_8 = _jspx_th_portlet_namespace_8.doStartTag();
    if (_jspx_th_portlet_namespace_8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_8);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_8);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_9(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_9 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_9.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_9.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_2);
    int _jspx_eval_portlet_namespace_9 = _jspx_th_portlet_namespace_9.doStartTag();
    if (_jspx_th_portlet_namespace_9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_9);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_9);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_10(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_10 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_10.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_10.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_2);
    int _jspx_eval_portlet_namespace_10 = _jspx_th_portlet_namespace_10.doStartTag();
    if (_jspx_th_portlet_namespace_10.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_10);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_10);
    return false;
  }
}
