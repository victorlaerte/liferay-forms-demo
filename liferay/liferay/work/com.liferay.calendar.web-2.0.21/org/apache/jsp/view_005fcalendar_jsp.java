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

public final class view_005fcalendar_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {


protected void updateCalendarsJSONArray(HttpServletRequest request, JSONArray calendarsJSONArray) {
	for (int i = 0; i < calendarsJSONArray.length(); i++) {
		JSONObject jsonObject = calendarsJSONArray.getJSONObject(i);

		long calendarId = jsonObject.getLong("calendarId");

		jsonObject.put("color", GetterUtil.getString(SessionClicks.get(request, "com.liferay.calendar.web_calendar" + calendarId + "Color", jsonObject.getString("color"))));
		jsonObject.put("visible", GetterUtil.getBoolean(SessionClicks.get(request, "com.liferay.calendar.web_calendar" + calendarId + "Visible", "true")));
	}
}

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(2);
    _jspx_dependants.add("/init.jsp");
    _jspx_dependants.add("/view_calendar_menus.jspf");
  }

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_namespace_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_container_cssClass;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1util_param_value_name_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_message_key_arguments_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_row;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_message_key_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_param_value_name_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_if_test;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_col_span_id_cssClass;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_renderURL_var;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1security_permissionsURL_windowState_var_resourcePrimKey_modelResourceDescription_modelResource_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1portlet_resourceURL_var_id_copyCurrentRenderParameters_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_script;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_renderURL_windowState_var;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1util_include_servletContext_page;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1theme_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1frontend_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_script_use;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1security_permissionsURL_windowState_var_resourcePrimKey_resourceGroupId_modelResourceDescription_modelResource_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1portlet_resourceURL_varImpl_id;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_portlet_namespace_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_container_cssClass = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1util_param_value_name_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_message_key_arguments_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_row = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_message_key_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_param_value_name_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_if_test = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_col_span_id_cssClass = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_renderURL_var = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1security_permissionsURL_windowState_var_resourcePrimKey_modelResourceDescription_modelResource_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1portlet_resourceURL_var_id_copyCurrentRenderParameters_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_script = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_renderURL_windowState_var = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1util_include_servletContext_page = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1theme_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1frontend_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_script_use = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1security_permissionsURL_windowState_var_resourcePrimKey_resourceGroupId_modelResourceDescription_modelResource_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1portlet_resourceURL_varImpl_id = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_portlet_namespace_nobody.release();
    _jspx_tagPool_aui_container_cssClass.release();
    _jspx_tagPool_liferay$1util_param_value_name_nobody.release();
    _jspx_tagPool_liferay$1ui_message_key_arguments_nobody.release();
    _jspx_tagPool_aui_row.release();
    _jspx_tagPool_liferay$1ui_message_key_nobody.release();
    _jspx_tagPool_portlet_param_value_name_nobody.release();
    _jspx_tagPool_c_if_test.release();
    _jspx_tagPool_aui_col_span_id_cssClass.release();
    _jspx_tagPool_portlet_renderURL_var.release();
    _jspx_tagPool_liferay$1security_permissionsURL_windowState_var_resourcePrimKey_modelResourceDescription_modelResource_nobody.release();
    _jspx_tagPool_liferay$1portlet_resourceURL_var_id_copyCurrentRenderParameters_nobody.release();
    _jspx_tagPool_aui_script.release();
    _jspx_tagPool_portlet_renderURL_windowState_var.release();
    _jspx_tagPool_portlet_defineObjects_nobody.release();
    _jspx_tagPool_liferay$1util_include_servletContext_page.release();
    _jspx_tagPool_liferay$1theme_defineObjects_nobody.release();
    _jspx_tagPool_liferay$1frontend_defineObjects_nobody.release();
    _jspx_tagPool_aui_script_use.release();
    _jspx_tagPool_liferay$1security_permissionsURL_windowState_var_resourcePrimKey_resourceGroupId_modelResourceDescription_modelResource_nobody.release();
    _jspx_tagPool_liferay$1portlet_resourceURL_varImpl_id.release();
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

String activeView = ParamUtil.getString(request, "activeView", sessionClicksDefaultView);
long date = ParamUtil.getLong(request, "date", System.currentTimeMillis());

JSONArray groupCalendarsJSONArray = CalendarUtil.toCalendarsJSONArray(themeDisplay, groupCalendars);
JSONArray userCalendarsJSONArray = CalendarUtil.toCalendarsJSONArray(themeDisplay, userCalendars);
JSONArray otherCalendarsJSONArray = CalendarUtil.toCalendarsJSONArray(themeDisplay, otherCalendars);

boolean columnOptionsVisible = GetterUtil.getBoolean(SessionClicks.get(request, "com.liferay.calendar.web_columnOptionsVisible", "true"));

      out.write('\n');
      out.write('\n');
      //  aui:script
      com.liferay.taglib.aui.ScriptTag _jspx_th_aui_script_0 = (com.liferay.taglib.aui.ScriptTag) _jspx_tagPool_aui_script_use.get(com.liferay.taglib.aui.ScriptTag.class);
      _jspx_th_aui_script_0.setPageContext(_jspx_page_context);
      _jspx_th_aui_script_0.setParent(null);
      _jspx_th_aui_script_0.setUse("liferay-calendar-container,liferay-calendar-remote-services,liferay-component");
      int _jspx_eval_aui_script_0 = _jspx_th_aui_script_0.doStartTag();
      if (_jspx_eval_aui_script_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        if (_jspx_eval_aui_script_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
          out = _jspx_page_context.pushBody();
          _jspx_th_aui_script_0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
          _jspx_th_aui_script_0.doInitBody();
        }
        do {
          out.write("\n\tLiferay.component(\n\t\t'");
          if (_jspx_meth_portlet_namespace_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_0, _jspx_page_context))
            return;
          out.write("calendarContainer',\n\t\tfunction() {\n\t\t\tvar calendarContainer = new Liferay.CalendarContainer(\n\t\t\t\t{\n\t\t\t\t\tgroupCalendarResourceId: ");
          out.print( groupCalendarResource.getCalendarResourceId() );
          out.write(",\n\n\t\t\t\t\t");
          //  c:if
          com.liferay.taglib.core.IfTag _jspx_th_c_if_0 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
          _jspx_th_c_if_0.setPageContext(_jspx_page_context);
          _jspx_th_c_if_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_0);
          _jspx_th_c_if_0.setTest( userCalendarResource != null );
          int _jspx_eval_c_if_0 = _jspx_th_c_if_0.doStartTag();
          if (_jspx_eval_c_if_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t\t\t\t\tuserCalendarResourceId: ");
            out.print( userCalendarResource.getCalendarResourceId() );
            out.write(",\n\t\t\t\t\t");
          }
          if (_jspx_th_c_if_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
            return;
          }
          _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
          out.write("\n\n\t\t\t\t\tnamespace: '");
          if (_jspx_meth_portlet_namespace_1((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_0, _jspx_page_context))
            return;
          out.write("'\n\t\t\t\t}\n\t\t\t);\n\n\t\t\tvar destroyInstance = function(event) {\n\t\t\t\tif (event.portletId === '");
          out.print( portletDisplay.getId() );
          out.write("') {\n\t\t\t\t\tcalendarContainer.destroy();\n\n\t\t\t\t\tLiferay.component('");
          if (_jspx_meth_portlet_namespace_2((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_0, _jspx_page_context))
            return;
          out.write("calendarContainer', null);\n\n\t\t\t\t\tLiferay.detach('destroyPortlet', destroyInstance);\n\t\t\t\t}\n\t\t\t};\n\n\t\t\tLiferay.on('destroyPortlet', destroyInstance);\n\n\t\t\treturn calendarContainer;\n\t\t}\n\t);\n\n\tLiferay.component(\n\t\t'");
          if (_jspx_meth_portlet_namespace_3((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_0, _jspx_page_context))
            return;
          out.write("remoteServices',\n\t\tfunction() {\n\t\t\tvar remoteServices = new Liferay.CalendarRemoteServices(\n\t\t\t\t{\n\t\t\t\t\tinvokerURL: themeDisplay.getPathContext() + '/api/jsonws/invoke',\n\t\t\t\t\tnamespace: '");
          if (_jspx_meth_portlet_namespace_4((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_0, _jspx_page_context))
            return;
          out.write("',\n\t\t\t\t\tuserId: themeDisplay.getUserId()\n\t\t\t\t}\n\t\t\t);\n\n\t\t\tvar destroyInstance = function(event) {\n\t\t\t\tif (event.portletId === '");
          out.print( portletDisplay.getId() );
          out.write("') {\n\t\t\t\t\tremoteServices.destroy();\n\n\t\t\t\t\tLiferay.component('");
          if (_jspx_meth_portlet_namespace_5((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_0, _jspx_page_context))
            return;
          out.write("remoteServices', null);\n\n\t\t\t\t\tLiferay.detach('destroyPortlet', destroyInstance);\n\t\t\t\t}\n\t\t\t};\n\n\t\t\tLiferay.on('destroyPortlet', destroyInstance);\n\n\t\t\treturn remoteServices;\n\t\t}\n\t);\n");
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
      out.write('\n');
      //  aui:container
      com.liferay.taglib.aui.ContainerTag _jspx_th_aui_container_0 = (com.liferay.taglib.aui.ContainerTag) _jspx_tagPool_aui_container_cssClass.get(com.liferay.taglib.aui.ContainerTag.class);
      _jspx_th_aui_container_0.setPageContext(_jspx_page_context);
      _jspx_th_aui_container_0.setParent(null);
      _jspx_th_aui_container_0.setCssClass("calendar-portlet-column-parent");
      int _jspx_eval_aui_container_0 = _jspx_th_aui_container_0.doStartTag();
      if (_jspx_eval_aui_container_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        out.write('\n');
        out.write('	');
        //  aui:row
        com.liferay.taglib.aui.RowTag _jspx_th_aui_row_0 = (com.liferay.taglib.aui.RowTag) _jspx_tagPool_aui_row.get(com.liferay.taglib.aui.RowTag.class);
        _jspx_th_aui_row_0.setPageContext(_jspx_page_context);
        _jspx_th_aui_row_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_container_0);
        int _jspx_eval_aui_row_0 = _jspx_th_aui_row_0.doStartTag();
        if (_jspx_eval_aui_row_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          out.write("\n\t\t");
          //  c:if
          com.liferay.taglib.core.IfTag _jspx_th_c_if_1 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
          _jspx_th_c_if_1.setPageContext(_jspx_page_context);
          _jspx_th_c_if_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_row_0);
          _jspx_th_c_if_1.setTest( !displaySchedulerOnly );
          int _jspx_eval_c_if_1 = _jspx_th_c_if_1.doStartTag();
          if (_jspx_eval_c_if_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t\t");
            //  aui:col
            com.liferay.taglib.aui.ColTag _jspx_th_aui_col_0 = (com.liferay.taglib.aui.ColTag) _jspx_tagPool_aui_col_span_id_cssClass.get(com.liferay.taglib.aui.ColTag.class);
            _jspx_th_aui_col_0.setPageContext(_jspx_page_context);
            _jspx_th_aui_col_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_1);
            _jspx_th_aui_col_0.setCssClass( "calendar-portlet-column-options " + (columnOptionsVisible ? StringPool.BLANK : "hide") );
            _jspx_th_aui_col_0.setId("columnOptions");
            _jspx_th_aui_col_0.setSpan( 3 );
            int _jspx_eval_aui_col_0 = _jspx_th_aui_col_0.doStartTag();
            if (_jspx_eval_aui_col_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\t\t\t\t<div class=\"calendar-portlet-mini-calendar\" id=\"");
              if (_jspx_meth_portlet_namespace_6((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_col_0, _jspx_page_context))
                return;
              out.write("miniCalendarContainer\"></div>\n\n\t\t\t\t<div id=\"");
              if (_jspx_meth_portlet_namespace_7((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_col_0, _jspx_page_context))
                return;
              out.write("calendarListContainer\">\n\t\t\t\t\t<div class=\"calendar-portlet-list\">\n\t\t\t\t\t\t");
              //  c:if
              com.liferay.taglib.core.IfTag _jspx_th_c_if_2 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
              _jspx_th_c_if_2.setPageContext(_jspx_page_context);
              _jspx_th_c_if_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_col_0);
              _jspx_th_c_if_2.setTest( themeDisplay.isSignedIn() && showUserEvents );
              int _jspx_eval_c_if_2 = _jspx_th_c_if_2.doStartTag();
              if (_jspx_eval_c_if_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\t\t\t\t\t\t\t<div class=\"calendar-portlet-list-header toggler-header-expanded\">\n\t\t\t\t\t\t\t\t<span class=\"calendar-portlet-list-arrow\"></span>\n\n\t\t\t\t\t\t\t\t<span class=\"calendar-portlet-list-text\">");
                if (_jspx_meth_liferay$1ui_message_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_2, _jspx_page_context))
                  return;
                out.write("</span>\n\t\t\t\t\t\t\t</div>\n\n\t\t\t\t\t\t\t");
                //  c:if
                com.liferay.taglib.core.IfTag _jspx_th_c_if_3 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                _jspx_th_c_if_3.setPageContext(_jspx_page_context);
                _jspx_th_c_if_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_2);
                _jspx_th_c_if_3.setTest( userCalendarResource != null );
                int _jspx_eval_c_if_3 = _jspx_th_c_if_3.doStartTag();
                if (_jspx_eval_c_if_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  out.write("\n\t\t\t\t\t\t\t\t<span class=\"calendar-list-item-arrow calendar-resource-arrow\" data-calendarResourceId=\"");
                  out.print( userCalendarResource.getCalendarResourceId() );
                  out.write("\" tabindex=\"0\"><i class=\"icon-caret-down\"></i></span>\n\t\t\t\t\t\t\t");
                }
                if (_jspx_th_c_if_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_3);
                  return;
                }
                _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_3);
                out.write("\n\t\t\t\t\t\t");
              }
              if (_jspx_th_c_if_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_2);
                return;
              }
              _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_2);
              out.write("\n\n\t\t\t\t\t\t<div class=\"calendar-portlet-calendar-list\" id=\"");
              if (_jspx_meth_portlet_namespace_8((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_col_0, _jspx_page_context))
                return;
              out.write("myCalendarList\"></div>\n\t\t\t\t\t</div>\n\n\t\t\t\t\t<div class=\"calendar-portlet-list\">\n\t\t\t\t\t\t");
              //  c:if
              com.liferay.taglib.core.IfTag _jspx_th_c_if_4 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
              _jspx_th_c_if_4.setPageContext(_jspx_page_context);
              _jspx_th_c_if_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_col_0);
              _jspx_th_c_if_4.setTest( showSiteCalendars );
              int _jspx_eval_c_if_4 = _jspx_th_c_if_4.doStartTag();
              if (_jspx_eval_c_if_4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\t\t\t\t\t\t\t<div class=\"calendar-portlet-list-header toggler-header-expanded\">\n\t\t\t\t\t\t\t\t<span class=\"calendar-portlet-list-arrow\"></span>\n\n\t\t\t\t\t\t\t\t<span class=\"calendar-portlet-list-text\">");
                //  liferay-ui:message
                com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_1 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_arguments_nobody.get(com.liferay.taglib.ui.MessageTag.class);
                _jspx_th_liferay$1ui_message_1.setPageContext(_jspx_page_context);
                _jspx_th_liferay$1ui_message_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_4);
                _jspx_th_liferay$1ui_message_1.setArguments( new String[] {groupCalendarResource.getName(locale)} );
                _jspx_th_liferay$1ui_message_1.setKey("x-calendars");
                int _jspx_eval_liferay$1ui_message_1 = _jspx_th_liferay$1ui_message_1.doStartTag();
                if (_jspx_th_liferay$1ui_message_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_liferay$1ui_message_key_arguments_nobody.reuse(_jspx_th_liferay$1ui_message_1);
                  return;
                }
                _jspx_tagPool_liferay$1ui_message_key_arguments_nobody.reuse(_jspx_th_liferay$1ui_message_1);
                out.write("</span>\n\t\t\t\t\t\t\t</div>\n\n\t\t\t\t\t\t\t");
                //  c:if
                com.liferay.taglib.core.IfTag _jspx_th_c_if_5 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                _jspx_th_c_if_5.setPageContext(_jspx_page_context);
                _jspx_th_c_if_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_4);
                _jspx_th_c_if_5.setTest( CalendarResourcePermission.contains(permissionChecker, groupCalendarResource, CalendarActionKeys.ADD_CALENDAR) );
                int _jspx_eval_c_if_5 = _jspx_th_c_if_5.doStartTag();
                if (_jspx_eval_c_if_5 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  out.write("\n\t\t\t\t\t\t\t\t<span class=\"calendar-list-item-arrow calendar-resource-arrow\" data-calendarResourceId=\"");
                  out.print( groupCalendarResource.getCalendarResourceId() );
                  out.write("\" tabindex=\"0\"><i class=\"icon-caret-down\"></i></span>\n\t\t\t\t\t\t\t");
                }
                if (_jspx_th_c_if_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_5);
                  return;
                }
                _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_5);
                out.write("\n\n\t\t\t\t\t\t\t<div class=\"calendar-portlet-calendar-list\" id=\"");
                if (_jspx_meth_portlet_namespace_9((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_4, _jspx_page_context))
                  return;
                out.write("siteCalendarList\"></div>\n\t\t\t\t\t\t");
              }
              if (_jspx_th_c_if_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_4);
                return;
              }
              _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_4);
              out.write("\n\t\t\t\t\t</div>\n\n\t\t\t\t\t<div class=\"calendar-portlet-list\">\n\t\t\t\t\t\t");
              //  c:if
              com.liferay.taglib.core.IfTag _jspx_th_c_if_6 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
              _jspx_th_c_if_6.setPageContext(_jspx_page_context);
              _jspx_th_c_if_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_col_0);
              _jspx_th_c_if_6.setTest( themeDisplay.isSignedIn() );
              int _jspx_eval_c_if_6 = _jspx_th_c_if_6.doStartTag();
              if (_jspx_eval_c_if_6 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\t\t\t\t\t\t\t<div class=\"calendar-portlet-list-header toggler-header-expanded\">\n\t\t\t\t\t\t\t\t<span class=\"calendar-portlet-list-arrow\"></span>\n\n\t\t\t\t\t\t\t\t<span class=\"calendar-portlet-list-text\">");
                if (_jspx_meth_liferay$1ui_message_2((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_6, _jspx_page_context))
                  return;
                out.write("</span>\n\t\t\t\t\t\t\t</div>\n\n\t\t\t\t\t\t\t<div class=\"calendar-portlet-calendar-list\" id=\"");
                if (_jspx_meth_portlet_namespace_10((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_6, _jspx_page_context))
                  return;
                out.write("otherCalendarList\">\n\t\t\t\t\t\t\t\t<input class=\"calendar-portlet-add-calendars-input\" id=\"");
                if (_jspx_meth_portlet_namespace_11((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_6, _jspx_page_context))
                  return;
                out.write("addOtherCalendar\" placeholder=\"");
                if (_jspx_meth_liferay$1ui_message_3((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_6, _jspx_page_context))
                  return;
                out.write("\" type=\"text\" />\n\t\t\t\t\t\t\t</div>\n\t\t\t\t\t\t");
              }
              if (_jspx_th_c_if_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_6);
                return;
              }
              _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_6);
              out.write("\n\t\t\t\t\t</div>\n\t\t\t\t</div>\n\t\t\t");
            }
            if (_jspx_th_aui_col_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_aui_col_span_id_cssClass.reuse(_jspx_th_aui_col_0);
              return;
            }
            _jspx_tagPool_aui_col_span_id_cssClass.reuse(_jspx_th_aui_col_0);
            out.write("\n\t\t");
          }
          if (_jspx_th_c_if_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_1);
            return;
          }
          _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_1);
          out.write("\n\n\t\t");
          //  aui:col
          com.liferay.taglib.aui.ColTag _jspx_th_aui_col_1 = (com.liferay.taglib.aui.ColTag) _jspx_tagPool_aui_col_span_id_cssClass.get(com.liferay.taglib.aui.ColTag.class);
          _jspx_th_aui_col_1.setPageContext(_jspx_page_context);
          _jspx_th_aui_col_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_row_0);
          _jspx_th_aui_col_1.setCssClass("calendar-portlet-column-grid");
          _jspx_th_aui_col_1.setId("columnGrid");
          _jspx_th_aui_col_1.setSpan( (columnOptionsVisible && !displaySchedulerOnly) ? 9 : 12 );
          int _jspx_eval_aui_col_1 = _jspx_th_aui_col_1.doStartTag();
          if (_jspx_eval_aui_col_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t\t");
            //  c:if
            com.liferay.taglib.core.IfTag _jspx_th_c_if_7 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
            _jspx_th_c_if_7.setPageContext(_jspx_page_context);
            _jspx_th_c_if_7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_col_1);
            _jspx_th_c_if_7.setTest( !displaySchedulerOnly );
            int _jspx_eval_c_if_7 = _jspx_th_c_if_7.doStartTag();
            if (_jspx_eval_c_if_7 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\t\t\t\t<div class=\"calendar-portlet-column-toggler\" id=\"");
              if (_jspx_meth_portlet_namespace_12((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_7, _jspx_page_context))
                return;
              out.write("columnToggler\">\n\t\t\t\t\t<i class=\"");
              out.print( columnOptionsVisible ? "icon-caret-left" : "icon-caret-right" );
              out.write("\" id=\"");
              if (_jspx_meth_portlet_namespace_13((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_7, _jspx_page_context))
                return;
              out.write("columnTogglerIcon\"></i>\n\t\t\t\t</div>\n\t\t\t");
            }
            if (_jspx_th_c_if_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_7);
              return;
            }
            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_7);
            out.write("\n\n\t\t\t");
            //  liferay-util:include
            com.liferay.taglib.util.IncludeTag _jspx_th_liferay$1util_include_0 = (com.liferay.taglib.util.IncludeTag) _jspx_tagPool_liferay$1util_include_servletContext_page.get(com.liferay.taglib.util.IncludeTag.class);
            _jspx_th_liferay$1util_include_0.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1util_include_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_col_1);
            _jspx_th_liferay$1util_include_0.setPage("/scheduler.jsp");
            _jspx_th_liferay$1util_include_0.setServletContext( application );
            int _jspx_eval_liferay$1util_include_0 = _jspx_th_liferay$1util_include_0.doStartTag();
            if (_jspx_eval_liferay$1util_include_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\t\t\t\t");
              //  liferay-util:param
              com.liferay.taglib.util.ParamTag _jspx_th_liferay$1util_param_0 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_liferay$1util_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
              _jspx_th_liferay$1util_param_0.setPageContext(_jspx_page_context);
              _jspx_th_liferay$1util_param_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1util_include_0);
              _jspx_th_liferay$1util_param_0.setName("activeView");
              _jspx_th_liferay$1util_param_0.setValue( activeView );
              int _jspx_eval_liferay$1util_param_0 = _jspx_th_liferay$1util_param_0.doStartTag();
              if (_jspx_th_liferay$1util_param_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_liferay$1util_param_value_name_nobody.reuse(_jspx_th_liferay$1util_param_0);
                return;
              }
              _jspx_tagPool_liferay$1util_param_value_name_nobody.reuse(_jspx_th_liferay$1util_param_0);
              out.write("\n\t\t\t\t");
              //  liferay-util:param
              com.liferay.taglib.util.ParamTag _jspx_th_liferay$1util_param_1 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_liferay$1util_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
              _jspx_th_liferay$1util_param_1.setPageContext(_jspx_page_context);
              _jspx_th_liferay$1util_param_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1util_include_0);
              _jspx_th_liferay$1util_param_1.setName("date");
              _jspx_th_liferay$1util_param_1.setValue( String.valueOf(date) );
              int _jspx_eval_liferay$1util_param_1 = _jspx_th_liferay$1util_param_1.doStartTag();
              if (_jspx_th_liferay$1util_param_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_liferay$1util_param_value_name_nobody.reuse(_jspx_th_liferay$1util_param_1);
                return;
              }
              _jspx_tagPool_liferay$1util_param_value_name_nobody.reuse(_jspx_th_liferay$1util_param_1);
              out.write("\n\n\t\t\t\t");
              //  portlet:renderURL
              com.liferay.taglib.portlet.RenderURLTag _jspx_th_portlet_renderURL_0 = (com.liferay.taglib.portlet.RenderURLTag) _jspx_tagPool_portlet_renderURL_windowState_var.get(com.liferay.taglib.portlet.RenderURLTag.class);
              _jspx_th_portlet_renderURL_0.setPageContext(_jspx_page_context);
              _jspx_th_portlet_renderURL_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1util_include_0);
              _jspx_th_portlet_renderURL_0.setVar("editCalendarBookingURL");
              _jspx_th_portlet_renderURL_0.setWindowState( LiferayWindowState.POP_UP.toString() );
              int _jspx_eval_portlet_renderURL_0 = _jspx_th_portlet_renderURL_0.doStartTag();
              if (_jspx_eval_portlet_renderURL_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\t\t\t\t\t");
                if (_jspx_meth_portlet_param_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_portlet_renderURL_0, _jspx_page_context))
                  return;
                out.write("\n\t\t\t\t\t");
                if (_jspx_meth_portlet_param_1((javax.servlet.jsp.tagext.JspTag) _jspx_th_portlet_renderURL_0, _jspx_page_context))
                  return;
                out.write("\n\t\t\t\t\t");
                if (_jspx_meth_portlet_param_2((javax.servlet.jsp.tagext.JspTag) _jspx_th_portlet_renderURL_0, _jspx_page_context))
                  return;
                out.write("\n\t\t\t\t\t");
                if (_jspx_meth_portlet_param_3((javax.servlet.jsp.tagext.JspTag) _jspx_th_portlet_renderURL_0, _jspx_page_context))
                  return;
                out.write("\n\t\t\t\t\t");
                if (_jspx_meth_portlet_param_4((javax.servlet.jsp.tagext.JspTag) _jspx_th_portlet_renderURL_0, _jspx_page_context))
                  return;
                out.write("\n\t\t\t\t\t");
                if (_jspx_meth_portlet_param_5((javax.servlet.jsp.tagext.JspTag) _jspx_th_portlet_renderURL_0, _jspx_page_context))
                  return;
                out.write("\n\t\t\t\t\t");
                if (_jspx_meth_portlet_param_6((javax.servlet.jsp.tagext.JspTag) _jspx_th_portlet_renderURL_0, _jspx_page_context))
                  return;
                out.write("\n\t\t\t\t\t");
                if (_jspx_meth_portlet_param_7((javax.servlet.jsp.tagext.JspTag) _jspx_th_portlet_renderURL_0, _jspx_page_context))
                  return;
                out.write("\n\t\t\t\t\t");
                if (_jspx_meth_portlet_param_8((javax.servlet.jsp.tagext.JspTag) _jspx_th_portlet_renderURL_0, _jspx_page_context))
                  return;
                out.write("\n\t\t\t\t\t");
                if (_jspx_meth_portlet_param_9((javax.servlet.jsp.tagext.JspTag) _jspx_th_portlet_renderURL_0, _jspx_page_context))
                  return;
                out.write("\n\t\t\t\t\t");
                if (_jspx_meth_portlet_param_10((javax.servlet.jsp.tagext.JspTag) _jspx_th_portlet_renderURL_0, _jspx_page_context))
                  return;
                out.write("\n\t\t\t\t\t");
                if (_jspx_meth_portlet_param_11((javax.servlet.jsp.tagext.JspTag) _jspx_th_portlet_renderURL_0, _jspx_page_context))
                  return;
                out.write("\n\t\t\t\t\t");
                if (_jspx_meth_portlet_param_12((javax.servlet.jsp.tagext.JspTag) _jspx_th_portlet_renderURL_0, _jspx_page_context))
                  return;
                out.write("\n\t\t\t\t\t");
                if (_jspx_meth_portlet_param_13((javax.servlet.jsp.tagext.JspTag) _jspx_th_portlet_renderURL_0, _jspx_page_context))
                  return;
                out.write("\n\t\t\t\t\t");
                if (_jspx_meth_portlet_param_14((javax.servlet.jsp.tagext.JspTag) _jspx_th_portlet_renderURL_0, _jspx_page_context))
                  return;
                out.write("\n\t\t\t\t\t");
                if (_jspx_meth_portlet_param_15((javax.servlet.jsp.tagext.JspTag) _jspx_th_portlet_renderURL_0, _jspx_page_context))
                  return;
                out.write("\n\t\t\t\t\t");
                if (_jspx_meth_portlet_param_16((javax.servlet.jsp.tagext.JspTag) _jspx_th_portlet_renderURL_0, _jspx_page_context))
                  return;
                out.write("\n\t\t\t\t\t");
                if (_jspx_meth_portlet_param_17((javax.servlet.jsp.tagext.JspTag) _jspx_th_portlet_renderURL_0, _jspx_page_context))
                  return;
                out.write("\n\t\t\t\t");
              }
              if (_jspx_th_portlet_renderURL_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_portlet_renderURL_windowState_var.reuse(_jspx_th_portlet_renderURL_0);
                return;
              }
              _jspx_tagPool_portlet_renderURL_windowState_var.reuse(_jspx_th_portlet_renderURL_0);
              java.lang.String editCalendarBookingURL = null;
              editCalendarBookingURL = (java.lang.String) _jspx_page_context.findAttribute("editCalendarBookingURL");
              out.write("\n\n\t\t\t\t");
              //  liferay-util:param
              com.liferay.taglib.util.ParamTag _jspx_th_liferay$1util_param_2 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_liferay$1util_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
              _jspx_th_liferay$1util_param_2.setPageContext(_jspx_page_context);
              _jspx_th_liferay$1util_param_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1util_include_0);
              _jspx_th_liferay$1util_param_2.setName("editCalendarBookingURL");
              _jspx_th_liferay$1util_param_2.setValue( editCalendarBookingURL );
              int _jspx_eval_liferay$1util_param_2 = _jspx_th_liferay$1util_param_2.doStartTag();
              if (_jspx_th_liferay$1util_param_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_liferay$1util_param_value_name_nobody.reuse(_jspx_th_liferay$1util_param_2);
                return;
              }
              _jspx_tagPool_liferay$1util_param_value_name_nobody.reuse(_jspx_th_liferay$1util_param_2);
              out.write("\n\n\t\t\t\t");
              //  liferay-util:param
              com.liferay.taglib.util.ParamTag _jspx_th_liferay$1util_param_3 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_liferay$1util_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
              _jspx_th_liferay$1util_param_3.setPageContext(_jspx_page_context);
              _jspx_th_liferay$1util_param_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1util_include_0);
              _jspx_th_liferay$1util_param_3.setName("hideAgendaView");
              _jspx_th_liferay$1util_param_3.setValue( String.valueOf(!showAgendaView) );
              int _jspx_eval_liferay$1util_param_3 = _jspx_th_liferay$1util_param_3.doStartTag();
              if (_jspx_th_liferay$1util_param_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_liferay$1util_param_value_name_nobody.reuse(_jspx_th_liferay$1util_param_3);
                return;
              }
              _jspx_tagPool_liferay$1util_param_value_name_nobody.reuse(_jspx_th_liferay$1util_param_3);
              out.write("\n\t\t\t\t");
              //  liferay-util:param
              com.liferay.taglib.util.ParamTag _jspx_th_liferay$1util_param_4 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_liferay$1util_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
              _jspx_th_liferay$1util_param_4.setPageContext(_jspx_page_context);
              _jspx_th_liferay$1util_param_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1util_include_0);
              _jspx_th_liferay$1util_param_4.setName("hideDayView");
              _jspx_th_liferay$1util_param_4.setValue( String.valueOf(!showDayView) );
              int _jspx_eval_liferay$1util_param_4 = _jspx_th_liferay$1util_param_4.doStartTag();
              if (_jspx_th_liferay$1util_param_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_liferay$1util_param_value_name_nobody.reuse(_jspx_th_liferay$1util_param_4);
                return;
              }
              _jspx_tagPool_liferay$1util_param_value_name_nobody.reuse(_jspx_th_liferay$1util_param_4);
              out.write("\n\t\t\t\t");
              //  liferay-util:param
              com.liferay.taglib.util.ParamTag _jspx_th_liferay$1util_param_5 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_liferay$1util_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
              _jspx_th_liferay$1util_param_5.setPageContext(_jspx_page_context);
              _jspx_th_liferay$1util_param_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1util_include_0);
              _jspx_th_liferay$1util_param_5.setName("hideWeekView");
              _jspx_th_liferay$1util_param_5.setValue( String.valueOf(!showWeekView) );
              int _jspx_eval_liferay$1util_param_5 = _jspx_th_liferay$1util_param_5.doStartTag();
              if (_jspx_th_liferay$1util_param_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_liferay$1util_param_value_name_nobody.reuse(_jspx_th_liferay$1util_param_5);
                return;
              }
              _jspx_tagPool_liferay$1util_param_value_name_nobody.reuse(_jspx_th_liferay$1util_param_5);
              out.write("\n\t\t\t\t");
              //  liferay-util:param
              com.liferay.taglib.util.ParamTag _jspx_th_liferay$1util_param_6 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_liferay$1util_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
              _jspx_th_liferay$1util_param_6.setPageContext(_jspx_page_context);
              _jspx_th_liferay$1util_param_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1util_include_0);
              _jspx_th_liferay$1util_param_6.setName("hideMonthView");
              _jspx_th_liferay$1util_param_6.setValue( String.valueOf(!showMonthView) );
              int _jspx_eval_liferay$1util_param_6 = _jspx_th_liferay$1util_param_6.doStartTag();
              if (_jspx_th_liferay$1util_param_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_liferay$1util_param_value_name_nobody.reuse(_jspx_th_liferay$1util_param_6);
                return;
              }
              _jspx_tagPool_liferay$1util_param_value_name_nobody.reuse(_jspx_th_liferay$1util_param_6);
              out.write("\n\t\t\t\t");
              //  liferay-util:param
              com.liferay.taglib.util.ParamTag _jspx_th_liferay$1util_param_7 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_liferay$1util_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
              _jspx_th_liferay$1util_param_7.setPageContext(_jspx_page_context);
              _jspx_th_liferay$1util_param_7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1util_include_0);
              _jspx_th_liferay$1util_param_7.setName("readOnly");
              _jspx_th_liferay$1util_param_7.setValue( Boolean.FALSE.toString() );
              int _jspx_eval_liferay$1util_param_7 = _jspx_th_liferay$1util_param_7.doStartTag();
              if (_jspx_th_liferay$1util_param_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_liferay$1util_param_value_name_nobody.reuse(_jspx_th_liferay$1util_param_7);
                return;
              }
              _jspx_tagPool_liferay$1util_param_value_name_nobody.reuse(_jspx_th_liferay$1util_param_7);
              out.write("\n\n\t\t\t\t");
              //  liferay-security:permissionsURL
              com.liferay.taglib.security.PermissionsURLTag _jspx_th_liferay$1security_permissionsURL_0 = (com.liferay.taglib.security.PermissionsURLTag) _jspx_tagPool_liferay$1security_permissionsURL_windowState_var_resourcePrimKey_resourceGroupId_modelResourceDescription_modelResource_nobody.get(com.liferay.taglib.security.PermissionsURLTag.class);
              _jspx_th_liferay$1security_permissionsURL_0.setPageContext(_jspx_page_context);
              _jspx_th_liferay$1security_permissionsURL_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1util_include_0);
              _jspx_th_liferay$1security_permissionsURL_0.setModelResource( CalendarBooking.class.getName() );
              _jspx_th_liferay$1security_permissionsURL_0.setModelResourceDescription("{modelResourceDescription}");
              _jspx_th_liferay$1security_permissionsURL_0.setResourceGroupId(new String("{resourceGroupId}"));
              _jspx_th_liferay$1security_permissionsURL_0.setResourcePrimKey("{resourcePrimKey}");
              _jspx_th_liferay$1security_permissionsURL_0.setVar("permissionsCalendarBookingURL");
              _jspx_th_liferay$1security_permissionsURL_0.setWindowState( LiferayWindowState.POP_UP.toString() );
              int _jspx_eval_liferay$1security_permissionsURL_0 = _jspx_th_liferay$1security_permissionsURL_0.doStartTag();
              if (_jspx_th_liferay$1security_permissionsURL_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_liferay$1security_permissionsURL_windowState_var_resourcePrimKey_resourceGroupId_modelResourceDescription_modelResource_nobody.reuse(_jspx_th_liferay$1security_permissionsURL_0);
                return;
              }
              _jspx_tagPool_liferay$1security_permissionsURL_windowState_var_resourcePrimKey_resourceGroupId_modelResourceDescription_modelResource_nobody.reuse(_jspx_th_liferay$1security_permissionsURL_0);
              java.lang.String permissionsCalendarBookingURL = null;
              permissionsCalendarBookingURL = (java.lang.String) _jspx_page_context.findAttribute("permissionsCalendarBookingURL");
              out.write("\n\n\t\t\t\t");
              //  liferay-util:param
              com.liferay.taglib.util.ParamTag _jspx_th_liferay$1util_param_8 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_liferay$1util_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
              _jspx_th_liferay$1util_param_8.setPageContext(_jspx_page_context);
              _jspx_th_liferay$1util_param_8.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1util_include_0);
              _jspx_th_liferay$1util_param_8.setName("permissionsCalendarBookingURL");
              _jspx_th_liferay$1util_param_8.setValue( permissionsCalendarBookingURL );
              int _jspx_eval_liferay$1util_param_8 = _jspx_th_liferay$1util_param_8.doStartTag();
              if (_jspx_th_liferay$1util_param_8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_liferay$1util_param_value_name_nobody.reuse(_jspx_th_liferay$1util_param_8);
                return;
              }
              _jspx_tagPool_liferay$1util_param_value_name_nobody.reuse(_jspx_th_liferay$1util_param_8);
              out.write("\n\n\t\t\t\t");
              //  liferay-util:param
              com.liferay.taglib.util.ParamTag _jspx_th_liferay$1util_param_9 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_liferay$1util_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
              _jspx_th_liferay$1util_param_9.setPageContext(_jspx_page_context);
              _jspx_th_liferay$1util_param_9.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1util_include_0);
              _jspx_th_liferay$1util_param_9.setName("showAddEventBtn");
              _jspx_th_liferay$1util_param_9.setValue( String.valueOf((defaultCalendar != null) && CalendarPermission.contains(permissionChecker, defaultCalendar, CalendarActionKeys.MANAGE_BOOKINGS)) );
              int _jspx_eval_liferay$1util_param_9 = _jspx_th_liferay$1util_param_9.doStartTag();
              if (_jspx_th_liferay$1util_param_9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_liferay$1util_param_value_name_nobody.reuse(_jspx_th_liferay$1util_param_9);
                return;
              }
              _jspx_tagPool_liferay$1util_param_value_name_nobody.reuse(_jspx_th_liferay$1util_param_9);
              out.write("\n\t\t\t\t");
              //  liferay-util:param
              com.liferay.taglib.util.ParamTag _jspx_th_liferay$1util_param_10 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_liferay$1util_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
              _jspx_th_liferay$1util_param_10.setPageContext(_jspx_page_context);
              _jspx_th_liferay$1util_param_10.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1util_include_0);
              _jspx_th_liferay$1util_param_10.setName("showSchedulerHeader");
              _jspx_th_liferay$1util_param_10.setValue( String.valueOf(displaySchedulerHeader) );
              int _jspx_eval_liferay$1util_param_10 = _jspx_th_liferay$1util_param_10.doStartTag();
              if (_jspx_th_liferay$1util_param_10.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_liferay$1util_param_value_name_nobody.reuse(_jspx_th_liferay$1util_param_10);
                return;
              }
              _jspx_tagPool_liferay$1util_param_value_name_nobody.reuse(_jspx_th_liferay$1util_param_10);
              out.write("\n\n\t\t\t\t");
              //  portlet:renderURL
              com.liferay.taglib.portlet.RenderURLTag _jspx_th_portlet_renderURL_1 = (com.liferay.taglib.portlet.RenderURLTag) _jspx_tagPool_portlet_renderURL_windowState_var.get(com.liferay.taglib.portlet.RenderURLTag.class);
              _jspx_th_portlet_renderURL_1.setPageContext(_jspx_page_context);
              _jspx_th_portlet_renderURL_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1util_include_0);
              _jspx_th_portlet_renderURL_1.setVar("viewCalendarBookingURL");
              _jspx_th_portlet_renderURL_1.setWindowState( LiferayWindowState.POP_UP.toString() );
              int _jspx_eval_portlet_renderURL_1 = _jspx_th_portlet_renderURL_1.doStartTag();
              if (_jspx_eval_portlet_renderURL_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\t\t\t\t\t");
                if (_jspx_meth_portlet_param_18((javax.servlet.jsp.tagext.JspTag) _jspx_th_portlet_renderURL_1, _jspx_page_context))
                  return;
                out.write("\n\t\t\t\t\t");
                if (_jspx_meth_portlet_param_19((javax.servlet.jsp.tagext.JspTag) _jspx_th_portlet_renderURL_1, _jspx_page_context))
                  return;
                out.write("\n\t\t\t\t\t");
                if (_jspx_meth_portlet_param_20((javax.servlet.jsp.tagext.JspTag) _jspx_th_portlet_renderURL_1, _jspx_page_context))
                  return;
                out.write("\n\t\t\t\t");
              }
              if (_jspx_th_portlet_renderURL_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_portlet_renderURL_windowState_var.reuse(_jspx_th_portlet_renderURL_1);
                return;
              }
              _jspx_tagPool_portlet_renderURL_windowState_var.reuse(_jspx_th_portlet_renderURL_1);
              java.lang.String viewCalendarBookingURL = null;
              viewCalendarBookingURL = (java.lang.String) _jspx_page_context.findAttribute("viewCalendarBookingURL");
              out.write("\n\n\t\t\t\t");
              //  liferay-util:param
              com.liferay.taglib.util.ParamTag _jspx_th_liferay$1util_param_11 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_liferay$1util_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
              _jspx_th_liferay$1util_param_11.setPageContext(_jspx_page_context);
              _jspx_th_liferay$1util_param_11.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1util_include_0);
              _jspx_th_liferay$1util_param_11.setName("viewCalendarBookingURL");
              _jspx_th_liferay$1util_param_11.setValue( viewCalendarBookingURL );
              int _jspx_eval_liferay$1util_param_11 = _jspx_th_liferay$1util_param_11.doStartTag();
              if (_jspx_th_liferay$1util_param_11.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_liferay$1util_param_value_name_nobody.reuse(_jspx_th_liferay$1util_param_11);
                return;
              }
              _jspx_tagPool_liferay$1util_param_value_name_nobody.reuse(_jspx_th_liferay$1util_param_11);
              out.write("\n\t\t\t");
            }
            if (_jspx_th_liferay$1util_include_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_liferay$1util_include_servletContext_page.reuse(_jspx_th_liferay$1util_include_0);
              return;
            }
            _jspx_tagPool_liferay$1util_include_servletContext_page.reuse(_jspx_th_liferay$1util_include_0);
            out.write("\n\t\t");
          }
          if (_jspx_th_aui_col_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_aui_col_span_id_cssClass.reuse(_jspx_th_aui_col_1);
            return;
          }
          _jspx_tagPool_aui_col_span_id_cssClass.reuse(_jspx_th_aui_col_1);
          out.write('\n');
          out.write('	');
        }
        if (_jspx_th_aui_row_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_aui_row.reuse(_jspx_th_aui_row_0);
          return;
        }
        _jspx_tagPool_aui_row.reuse(_jspx_th_aui_row_0);
        out.write('\n');
      }
      if (_jspx_th_aui_container_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_aui_container_cssClass.reuse(_jspx_th_aui_container_0);
        return;
      }
      _jspx_tagPool_aui_container_cssClass.reuse(_jspx_th_aui_container_0);
      out.write("\n\n<div id=\"");
      if (_jspx_meth_portlet_namespace_14(_jspx_page_context))
        return;
      out.write("message\"></div>\n\n");
      //  c:if
      com.liferay.taglib.core.IfTag _jspx_th_c_if_8 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
      _jspx_th_c_if_8.setPageContext(_jspx_page_context);
      _jspx_th_c_if_8.setParent(null);
      _jspx_th_c_if_8.setTest( !displaySchedulerOnly );
      int _jspx_eval_c_if_8 = _jspx_th_c_if_8.doStartTag();
      if (_jspx_eval_c_if_8 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        out.write('\n');
        out.write('	');
        out.write('\n');
        out.write('\n');

String backURL = PortalUtil.getCurrentURL(request);

        out.write('\n');
        out.write('\n');
        //  aui:script
        com.liferay.taglib.aui.ScriptTag _jspx_th_aui_script_1 = (com.liferay.taglib.aui.ScriptTag) _jspx_tagPool_aui_script_use.get(com.liferay.taglib.aui.ScriptTag.class);
        _jspx_th_aui_script_1.setPageContext(_jspx_page_context);
        _jspx_th_aui_script_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_8);
        _jspx_th_aui_script_1.setUse("aui-toggler,calendar,liferay-calendar-simple-menu,liferay-calendar-simple-color-picker,liferay-calendar-util,liferay-store");
        int _jspx_eval_aui_script_1 = _jspx_th_aui_script_1.doStartTag();
        if (_jspx_eval_aui_script_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          if (_jspx_eval_aui_script_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
            out = _jspx_page_context.pushBody();
            _jspx_th_aui_script_1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
            _jspx_th_aui_script_1.doInitBody();
          }
          do {
            out.write("\n\tvar calendarContainer = Liferay.component('");
            if (_jspx_meth_portlet_namespace_15((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
              return;
            out.write("calendarContainer');\n\n\tvar remoteServices = Liferay.component('");
            if (_jspx_meth_portlet_namespace_16((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
              return;
            out.write("remoteServices');\n\n\twindow.");
            if (_jspx_meth_portlet_namespace_17((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
              return;
            out.write("calendarListsMenu = new Liferay.SimpleMenu(\n\t\t{\n\t\t\talign: {\n\t\t\t\tpoints: [A.WidgetPositionAlign.TL, A.WidgetPositionAlign.BL]\n\t\t\t},\n\t\t\tconstrain: true,\n\t\t\titems: [\n\t\t\t\t{\n\t\t\t\t\tcaption: '");
            if (_jspx_meth_liferay$1ui_message_4((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
              return;
            out.write("',\n\t\t\t\t\tfn: function(event) {\n\t\t\t\t\t\tvar instance = this;\n\n\t\t\t\t\t\tvar calendarResourceId = instance.calendarResourceId;\n\n\t\t\t\t\t\tif (calendarResourceId) {\n\t\t\t\t\t\t\tLiferay.Util.openWindow(\n\t\t\t\t\t\t\t\t{\n\t\t\t\t\t\t\t\t\tdialog: {\n\t\t\t\t\t\t\t\t\t\tafter: {\n\t\t\t\t\t\t\t\t\t\t\tdestroy: function(event) {\n\t\t\t\t\t\t\t\t\t\t\t\tremoteServices.getResourceCalendars(\n\t\t\t\t\t\t\t\t\t\t\t\t\tcalendarResourceId,\n\t\t\t\t\t\t\t\t\t\t\t\t\tfunction(calendars) {\n\t\t\t\t\t\t\t\t\t\t\t\t\t\tvar calendarList = window.");
            if (_jspx_meth_portlet_namespace_18((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
              return;
            out.write("calendarLists[calendarResourceId];\n\n\t\t\t\t\t\t\t\t\t\t\t\t\t\tcalendarList.set('calendars', calendars);\n\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
            if (_jspx_meth_portlet_namespace_19((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
              return;
            out.write("syncCalendarsMap();\n\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
            if (_jspx_meth_portlet_namespace_20((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
              return;
            out.write("scheduler.load();\n\t\t\t\t\t\t\t\t\t\t\t\t\t}\n\t\t\t\t\t\t\t\t\t\t\t\t);\n\t\t\t\t\t\t\t\t\t\t\t}\n\t\t\t\t\t\t\t\t\t\t},\n\t\t\t\t\t\t\t\t\t\tdestroyOnHide: true,\n\t\t\t\t\t\t\t\t\t\tmodal: true\n\t\t\t\t\t\t\t\t\t},\n\t\t\t\t\t\t\t\t\trefreshWindow: window,\n\t\t\t\t\t\t\t\t\ttitle: '");
            if (_jspx_meth_liferay$1ui_message_5((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
              return;
            out.write("',\n\n\t\t\t\t\t\t\t\t\t");
            //  portlet:renderURL
            com.liferay.taglib.portlet.RenderURLTag _jspx_th_portlet_renderURL_2 = (com.liferay.taglib.portlet.RenderURLTag) _jspx_tagPool_portlet_renderURL_windowState_var.get(com.liferay.taglib.portlet.RenderURLTag.class);
            _jspx_th_portlet_renderURL_2.setPageContext(_jspx_page_context);
            _jspx_th_portlet_renderURL_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
            _jspx_th_portlet_renderURL_2.setVar("editCalendarURL");
            _jspx_th_portlet_renderURL_2.setWindowState( LiferayWindowState.POP_UP.toString() );
            int _jspx_eval_portlet_renderURL_2 = _jspx_th_portlet_renderURL_2.doStartTag();
            if (_jspx_eval_portlet_renderURL_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\t\t\t\t\t\t\t\t\t\t");
              if (_jspx_meth_portlet_param_21((javax.servlet.jsp.tagext.JspTag) _jspx_th_portlet_renderURL_2, _jspx_page_context))
                return;
              out.write("\n\t\t\t\t\t\t\t\t\t\t");
              if (_jspx_meth_portlet_param_22((javax.servlet.jsp.tagext.JspTag) _jspx_th_portlet_renderURL_2, _jspx_page_context))
                return;
              out.write("\n\t\t\t\t\t\t\t\t\t");
            }
            if (_jspx_th_portlet_renderURL_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_portlet_renderURL_windowState_var.reuse(_jspx_th_portlet_renderURL_2);
              return;
            }
            _jspx_tagPool_portlet_renderURL_windowState_var.reuse(_jspx_th_portlet_renderURL_2);
            java.lang.String editCalendarURL = null;
            editCalendarURL = (java.lang.String) _jspx_page_context.findAttribute("editCalendarURL");
            out.write("\n\n\t\t\t\t\t\t\t\t\turi: Liferay.CalendarUtil.fillURLParameters(\n\t\t\t\t\t\t\t\t\t\tdecodeURIComponent('");
            out.print( editCalendarURL );
            out.write("'),\n\t\t\t\t\t\t\t\t\t\t{\n\t\t\t\t\t\t\t\t\t\t\tcalendarResourceId: calendarResourceId\n\t\t\t\t\t\t\t\t\t\t}\n\t\t\t\t\t\t\t\t\t)\n\t\t\t\t\t\t\t\t}\n\t\t\t\t\t\t\t);\n\t\t\t\t\t\t}\n\t\t\t\t\t},\n\t\t\t\t\ticon: 'icon-plus',\n\t\t\t\t\tid: 'add'\n\t\t\t\t},\n\t\t\t\t{\n\t\t\t\t\tcaption: '");
            if (_jspx_meth_liferay$1ui_message_6((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
              return;
            out.write("',\n\t\t\t\t\tfn: function(event) {\n\t\t\t\t\t\tvar instance = this;\n\n\t\t\t\t\t\tvar calendarResourceId = instance.calendarResourceId;\n\n\t\t\t\t\t\tif (calendarResourceId) {\n\t\t\t\t\t\t\t");
            //  portlet:renderURL
            com.liferay.taglib.portlet.RenderURLTag _jspx_th_portlet_renderURL_3 = (com.liferay.taglib.portlet.RenderURLTag) _jspx_tagPool_portlet_renderURL_var.get(com.liferay.taglib.portlet.RenderURLTag.class);
            _jspx_th_portlet_renderURL_3.setPageContext(_jspx_page_context);
            _jspx_th_portlet_renderURL_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
            _jspx_th_portlet_renderURL_3.setVar("calendarsURL");
            int _jspx_eval_portlet_renderURL_3 = _jspx_th_portlet_renderURL_3.doStartTag();
            if (_jspx_eval_portlet_renderURL_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\t\t\t\t\t\t\t\t");
              if (_jspx_meth_portlet_param_23((javax.servlet.jsp.tagext.JspTag) _jspx_th_portlet_renderURL_3, _jspx_page_context))
                return;
              out.write("\n\t\t\t\t\t\t\t\t");
              //  portlet:param
              com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_24 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
              _jspx_th_portlet_param_24.setPageContext(_jspx_page_context);
              _jspx_th_portlet_param_24.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_3);
              _jspx_th_portlet_param_24.setName("redirect");
              _jspx_th_portlet_param_24.setValue( currentURL );
              int _jspx_eval_portlet_param_24 = _jspx_th_portlet_param_24.doStartTag();
              if (_jspx_th_portlet_param_24.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_24);
                return;
              }
              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_24);
              out.write("\n\t\t\t\t\t\t\t\t");
              if (_jspx_meth_portlet_param_25((javax.servlet.jsp.tagext.JspTag) _jspx_th_portlet_renderURL_3, _jspx_page_context))
                return;
              out.write("\n\t\t\t\t\t\t\t");
            }
            if (_jspx_th_portlet_renderURL_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_portlet_renderURL_var.reuse(_jspx_th_portlet_renderURL_3);
              return;
            }
            _jspx_tagPool_portlet_renderURL_var.reuse(_jspx_th_portlet_renderURL_3);
            java.lang.String calendarsURL = null;
            calendarsURL = (java.lang.String) _jspx_page_context.findAttribute("calendarsURL");
            out.write("\n\n\t\t\t\t\t\t\twindow.location.href = Liferay.CalendarUtil.fillURLParameters(\n\t\t\t\t\t\t\t\tdecodeURIComponent('");
            out.print( calendarsURL );
            out.write("'),\n\t\t\t\t\t\t\t\t{\n\t\t\t\t\t\t\t\t\tcalendarResourceId: calendarResourceId\n\t\t\t\t\t\t\t\t}\n\t\t\t\t\t\t\t);\n\t\t\t\t\t\t}\n\t\t\t\t\t},\n\t\t\t\t\ticon: 'icon-calendar',\n\t\t\t\t\tid: 'manage'\n\t\t\t\t},\n\t\t\t\t{\n\t\t\t\t\tcaption: '");
            if (_jspx_meth_liferay$1ui_message_7((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
              return;
            out.write("',\n\t\t\t\t\tfn: function(event) {\n\t\t\t\t\t\tvar instance = this;\n\n\t\t\t\t\t\t");
            //  liferay-security:permissionsURL
            com.liferay.taglib.security.PermissionsURLTag _jspx_th_liferay$1security_permissionsURL_1 = (com.liferay.taglib.security.PermissionsURLTag) _jspx_tagPool_liferay$1security_permissionsURL_windowState_var_resourcePrimKey_modelResourceDescription_modelResource_nobody.get(com.liferay.taglib.security.PermissionsURLTag.class);
            _jspx_th_liferay$1security_permissionsURL_1.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1security_permissionsURL_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
            _jspx_th_liferay$1security_permissionsURL_1.setModelResource( CalendarConstants.RESOURCE_NAME );
            _jspx_th_liferay$1security_permissionsURL_1.setModelResourceDescription("");
            _jspx_th_liferay$1security_permissionsURL_1.setResourcePrimKey( String.valueOf(scopeGroupId) );
            _jspx_th_liferay$1security_permissionsURL_1.setVar("permissionsURL");
            _jspx_th_liferay$1security_permissionsURL_1.setWindowState( LiferayWindowState.POP_UP.toString() );
            int _jspx_eval_liferay$1security_permissionsURL_1 = _jspx_th_liferay$1security_permissionsURL_1.doStartTag();
            if (_jspx_th_liferay$1security_permissionsURL_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_liferay$1security_permissionsURL_windowState_var_resourcePrimKey_modelResourceDescription_modelResource_nobody.reuse(_jspx_th_liferay$1security_permissionsURL_1);
              return;
            }
            _jspx_tagPool_liferay$1security_permissionsURL_windowState_var_resourcePrimKey_modelResourceDescription_modelResource_nobody.reuse(_jspx_th_liferay$1security_permissionsURL_1);
            java.lang.String permissionsURL = null;
            permissionsURL = (java.lang.String) _jspx_page_context.findAttribute("permissionsURL");
            out.write("\n\n\t\t\t\t\t\tLiferay.Util.openWindow(\n\t\t\t\t\t\t\t{\n\t\t\t\t\t\t\t\tdialog: {\n\t\t\t\t\t\t\t\t\tdestroyOnHide: true,\n\t\t\t\t\t\t\t\t\tmodal: true\n\t\t\t\t\t\t\t\t},\n\t\t\t\t\t\t\t\tdialogIframe: {\n\t\t\t\t\t\t\t\t\tbodyCssClass: 'dialog-with-footer'\n\t\t\t\t\t\t\t\t},\n\t\t\t\t\t\t\t\trefreshWindow: window,\n\t\t\t\t\t\t\t\ttitle: '");
            if (_jspx_meth_liferay$1ui_message_8((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
              return;
            out.write("',\n\t\t\t\t\t\t\t\turi: decodeURIComponent('");
            out.print( permissionsURL );
            out.write("')\n\t\t\t\t\t\t\t}\n\t\t\t\t\t\t);\n\t\t\t\t\t},\n\t\t\t\t\ticon: 'icon-lock',\n\t\t\t\t\tid: 'permissions'\n\t\t\t\t}\n\t\t\t],\n\t\t\ton: {\n\t\t\t\tvisibleChange: function(event) {\n\t\t\t\t\tvar instance = this;\n\n\t\t\t\t\tvar hiddenItems = [];\n\n\t\t\t\t\tif (!(instance.calendarResourceId === '");
            out.print( groupCalendarResource.getCalendarResourceId() );
            out.write("') || !");
            out.print( CalendarPortletPermission.contains(permissionChecker, scopeGroupId, ActionKeys.PERMISSIONS) );
            out.write(") {\n\t\t\t\t\t\thiddenItems.push('permissions');\n\t\t\t\t\t}\n\n\t\t\t\t\tinstance.set('hiddenItems', hiddenItems);\n\t\t\t\t}\n\t\t\t},\n\t\t\tvisible: false,\n\t\t\twidth: 290,\n\t\t\tzIndex: 500\n\t\t}\n\t).render();\n\n\twindow.");
            if (_jspx_meth_portlet_namespace_21((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
              return;
            out.write("calendarsMenu = {\n\t\titems: [\n\t\t\t{\n\t\t\t\tcaption: '");
            if (_jspx_meth_liferay$1ui_message_9((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
              return;
            out.write("',\n\t\t\t\tfn: function(event) {\n\t\t\t\t\tvar instance = this;\n\n\t\t\t\t\tvar calendarList = instance.get('host');\n\n\t\t\t\t\tvar activeCalendar = calendarList.activeItem;\n\n\t\t\t\t\t");
            //  portlet:renderURL
            com.liferay.taglib.portlet.RenderURLTag _jspx_th_portlet_renderURL_4 = (com.liferay.taglib.portlet.RenderURLTag) _jspx_tagPool_portlet_renderURL_windowState_var.get(com.liferay.taglib.portlet.RenderURLTag.class);
            _jspx_th_portlet_renderURL_4.setPageContext(_jspx_page_context);
            _jspx_th_portlet_renderURL_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
            _jspx_th_portlet_renderURL_4.setVar("addCalendarBookingURL");
            _jspx_th_portlet_renderURL_4.setWindowState( LiferayWindowState.POP_UP.toString() );
            int _jspx_eval_portlet_renderURL_4 = _jspx_th_portlet_renderURL_4.doStartTag();
            if (_jspx_eval_portlet_renderURL_4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\t\t\t\t\t\t");
              if (_jspx_meth_portlet_param_26((javax.servlet.jsp.tagext.JspTag) _jspx_th_portlet_renderURL_4, _jspx_page_context))
                return;
              out.write("\n\t\t\t\t\t\t");
              //  portlet:param
              com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_27 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
              _jspx_th_portlet_param_27.setPageContext(_jspx_page_context);
              _jspx_th_portlet_param_27.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_4);
              _jspx_th_portlet_param_27.setName("backURL");
              _jspx_th_portlet_param_27.setValue( backURL );
              int _jspx_eval_portlet_param_27 = _jspx_th_portlet_param_27.doStartTag();
              if (_jspx_th_portlet_param_27.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_27);
                return;
              }
              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_27);
              out.write("\n\t\t\t\t\t\t");
              if (_jspx_meth_portlet_param_28((javax.servlet.jsp.tagext.JspTag) _jspx_th_portlet_renderURL_4, _jspx_page_context))
                return;
              out.write("\n\t\t\t\t\t");
            }
            if (_jspx_th_portlet_renderURL_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_portlet_renderURL_windowState_var.reuse(_jspx_th_portlet_renderURL_4);
              return;
            }
            _jspx_tagPool_portlet_renderURL_windowState_var.reuse(_jspx_th_portlet_renderURL_4);
            java.lang.String addCalendarBookingURL = null;
            addCalendarBookingURL = (java.lang.String) _jspx_page_context.findAttribute("addCalendarBookingURL");
            out.write("\n\n\t\t\t\t\tLiferay.Util.openWindow(\n\t\t\t\t\t\t{\n\t\t\t\t\t\t\tdialog: {\n\t\t\t\t\t\t\t\tafter: {\n\t\t\t\t\t\t\t\t\tdestroy: function(event) {\n\t\t\t\t\t\t\t\t\t\t");
            if (_jspx_meth_portlet_namespace_22((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
              return;
            out.write("scheduler.load();\n\t\t\t\t\t\t\t\t\t}\n\t\t\t\t\t\t\t\t},\n\t\t\t\t\t\t\t\tdestroyOnHide: true,\n\t\t\t\t\t\t\t\tmodal: true\n\t\t\t\t\t\t\t},\n\t\t\t\t\t\t\trefreshWindow: window,\n\t\t\t\t\t\t\ttitle: '");
            if (_jspx_meth_liferay$1ui_message_10((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
              return;
            out.write("',\n\t\t\t\t\t\t\turi: Liferay.CalendarUtil.fillURLParameters(\n\t\t\t\t\t\t\t\tdecodeURIComponent('");
            out.print( addCalendarBookingURL );
            out.write("'),\n\t\t\t\t\t\t\t\t{\n\t\t\t\t\t\t\t\t\tcalendarId: activeCalendar.get('calendarId')\n\t\t\t\t\t\t\t\t}\n\t\t\t\t\t\t\t)\n\t\t\t\t\t\t}\n\t\t\t\t\t);\n\t\t\t\t},\n\t\t\t\ticon: 'icon-plus',\n\t\t\t\tid: 'addEvent'\n\t\t\t},\n\t\t\t{\n\t\t\t\tcaption: '");
            if (_jspx_meth_liferay$1ui_message_11((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
              return;
            out.write("',\n\t\t\t\tfn: function(event) {\n\t\t\t\t\tvar instance = this;\n\n\t\t\t\t\tvar calendarList = instance.get('host');\n\n\t\t\t\t\tcalendarList.remove(calendarList.activeItem);\n\n\t\t\t\t\t");
            if (_jspx_meth_portlet_namespace_23((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
              return;
            out.write("refreshVisibleCalendarRenderingRules();\n\n\t\t\t\t\tinstance.set('visible', false);\n\t\t\t\t},\n\t\t\t\ticon: 'icon-remove',\n\t\t\t\tid: 'hide'\n\t\t\t},\n\t\t\t{\n\t\t\t\tcaption: '");
            if (_jspx_meth_liferay$1ui_message_12((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
              return;
            out.write("',\n\t\t\t\tfn: function(event) {\n\t\t\t\t\tvar instance = this;\n\n\t\t\t\t\tvar calendarList = instance.get('host');\n\n\t\t\t\t\tvar activeCalendar = calendarList.activeItem;\n\n\t\t\t\t\tLiferay.Util.openWindow(\n\t\t\t\t\t\t{\n\t\t\t\t\t\t\tdialog: {\n\t\t\t\t\t\t\t\tafter: {\n\t\t\t\t\t\t\t\t\tdestroy: function(event) {\n\t\t\t\t\t\t\t\t\t\tremoteServices.getCalendar(\n\t\t\t\t\t\t\t\t\t\t\tactiveCalendar.get('calendarId'),\n\t\t\t\t\t\t\t\t\t\t\tfunction(calendar) {\n\t\t\t\t\t\t\t\t\t\t\t\tvar activeCalendarId = activeCalendar.get('calendarId');\n\n\t\t\t\t\t\t\t\t\t\t\t\tvar calendars = calendarList.get('calendars').map(\n\t\t\t\t\t\t\t\t\t\t\t\t\tfunction(item) {\n\t\t\t\t\t\t\t\t\t\t\t\t\t\tif (activeCalendarId === item.get('calendarId')) {\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\titem = calendar;\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t}\n\n\t\t\t\t\t\t\t\t\t\t\t\t\t\treturn item;\n\t\t\t\t\t\t\t\t\t\t\t\t\t}\n\t\t\t\t\t\t\t\t\t\t\t\t);\n\n\t\t\t\t\t\t\t\t\t\t\t\tcalendarList.set('calendars', calendars);\n\n\t\t\t\t\t\t\t\t\t\t\t\t");
            if (_jspx_meth_portlet_namespace_24((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
              return;
            out.write("syncCalendarsMap();\n\n\t\t\t\t\t\t\t\t\t\t\t\t");
            if (_jspx_meth_portlet_namespace_25((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
              return;
            out.write("scheduler.load();\n\t\t\t\t\t\t\t\t\t\t\t}\n\t\t\t\t\t\t\t\t\t\t);\n\t\t\t\t\t\t\t\t\t}\n\t\t\t\t\t\t\t\t},\n\t\t\t\t\t\t\t\tdestroyOnHide: true,\n\t\t\t\t\t\t\t\tmodal: true\n\t\t\t\t\t\t\t},\n\t\t\t\t\t\t\trefreshWindow: window,\n\t\t\t\t\t\t\ttitle: '");
            if (_jspx_meth_liferay$1ui_message_13((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
              return;
            out.write("',\n\n\t\t\t\t\t\t\t");
            //  portlet:renderURL
            com.liferay.taglib.portlet.RenderURLTag _jspx_th_portlet_renderURL_5 = (com.liferay.taglib.portlet.RenderURLTag) _jspx_tagPool_portlet_renderURL_windowState_var.get(com.liferay.taglib.portlet.RenderURLTag.class);
            _jspx_th_portlet_renderURL_5.setPageContext(_jspx_page_context);
            _jspx_th_portlet_renderURL_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
            _jspx_th_portlet_renderURL_5.setVar("editCalendarURL");
            _jspx_th_portlet_renderURL_5.setWindowState( LiferayWindowState.POP_UP.toString() );
            int _jspx_eval_portlet_renderURL_5 = _jspx_th_portlet_renderURL_5.doStartTag();
            if (_jspx_eval_portlet_renderURL_5 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\t\t\t\t\t\t\t\t");
              if (_jspx_meth_portlet_param_29((javax.servlet.jsp.tagext.JspTag) _jspx_th_portlet_renderURL_5, _jspx_page_context))
                return;
              out.write("\n\t\t\t\t\t\t\t\t");
              if (_jspx_meth_portlet_param_30((javax.servlet.jsp.tagext.JspTag) _jspx_th_portlet_renderURL_5, _jspx_page_context))
                return;
              out.write("\n\t\t\t\t\t\t\t");
            }
            if (_jspx_th_portlet_renderURL_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_portlet_renderURL_windowState_var.reuse(_jspx_th_portlet_renderURL_5);
              return;
            }
            _jspx_tagPool_portlet_renderURL_windowState_var.reuse(_jspx_th_portlet_renderURL_5);
            editCalendarURL = (java.lang.String) _jspx_page_context.findAttribute("editCalendarURL");
            out.write("\n\n\t\t\t\t\t\t\turi: Liferay.CalendarUtil.fillURLParameters(\n\t\t\t\t\t\t\t\tdecodeURIComponent('");
            out.print( editCalendarURL );
            out.write("'),\n\t\t\t\t\t\t\t\t{\n\t\t\t\t\t\t\t\t\tcalendarId: activeCalendar.get('calendarId'),\n\t\t\t\t\t\t\t\t\tcalendarResourceId: activeCalendar.get('calendarResourceId')\n\t\t\t\t\t\t\t\t}\n\t\t\t\t\t\t\t)\n\t\t\t\t\t\t}\n\t\t\t\t\t);\n\t\t\t\t},\n\t\t\t\ticon: 'icon-edit',\n\t\t\t\tid: 'settings'\n\t\t\t},\n\t\t\t{\n\t\t\t\tcaption: '");
            if (_jspx_meth_liferay$1ui_message_14((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
              return;
            out.write("',\n\t\t\t\tfn: function(event) {\n\t\t\t\t\tvar instance = this;\n\n\t\t\t\t\tvar calendarList = instance.get('host');\n\n\t\t\t\t\tvar activeCalendar = calendarList.activeItem;\n\n\t\t\t\t\tLiferay.Util.openWindow(\n\t\t\t\t\t\t{\n\t\t\t\t\t\t\tdialog: {\n\t\t\t\t\t\t\t\tafter: {\n\t\t\t\t\t\t\t\t\tdestroy: function(event) {\n\t\t\t\t\t\t\t\t\t\t");
            if (_jspx_meth_portlet_namespace_26((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
              return;
            out.write("scheduler.load();\n\t\t\t\t\t\t\t\t\t}\n\t\t\t\t\t\t\t\t},\n\t\t\t\t\t\t\t\tdestroyOnHide: true,\n\t\t\t\t\t\t\t\tmodal: true\n\t\t\t\t\t\t\t},\n\t\t\t\t\t\t\tdialogIframe: {\n\t\t\t\t\t\t\t\tbodyCssClass: 'dialog-with-footer'\n\t\t\t\t\t\t\t},\n\t\t\t\t\t\t\trefreshWindow: window,\n\t\t\t\t\t\t\ttitle: '");
            if (_jspx_meth_liferay$1ui_message_15((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
              return;
            out.write("',\n\n\t\t\t\t\t\t\t");
            //  liferay-security:permissionsURL
            com.liferay.taglib.security.PermissionsURLTag _jspx_th_liferay$1security_permissionsURL_2 = (com.liferay.taglib.security.PermissionsURLTag) _jspx_tagPool_liferay$1security_permissionsURL_windowState_var_resourcePrimKey_resourceGroupId_modelResourceDescription_modelResource_nobody.get(com.liferay.taglib.security.PermissionsURLTag.class);
            _jspx_th_liferay$1security_permissionsURL_2.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1security_permissionsURL_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
            _jspx_th_liferay$1security_permissionsURL_2.setModelResource( Calendar.class.getName() );
            _jspx_th_liferay$1security_permissionsURL_2.setModelResourceDescription("{modelResourceDescription}");
            _jspx_th_liferay$1security_permissionsURL_2.setResourceGroupId(new String("{resourceGroupId}"));
            _jspx_th_liferay$1security_permissionsURL_2.setResourcePrimKey("{resourcePrimKey}");
            _jspx_th_liferay$1security_permissionsURL_2.setVar("permissionsURL");
            _jspx_th_liferay$1security_permissionsURL_2.setWindowState( LiferayWindowState.POP_UP.toString() );
            int _jspx_eval_liferay$1security_permissionsURL_2 = _jspx_th_liferay$1security_permissionsURL_2.doStartTag();
            if (_jspx_th_liferay$1security_permissionsURL_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_liferay$1security_permissionsURL_windowState_var_resourcePrimKey_resourceGroupId_modelResourceDescription_modelResource_nobody.reuse(_jspx_th_liferay$1security_permissionsURL_2);
              return;
            }
            _jspx_tagPool_liferay$1security_permissionsURL_windowState_var_resourcePrimKey_resourceGroupId_modelResourceDescription_modelResource_nobody.reuse(_jspx_th_liferay$1security_permissionsURL_2);
            permissionsURL = (java.lang.String) _jspx_page_context.findAttribute("permissionsURL");
            out.write("\n\n\t\t\t\t\t\t\turi: Liferay.CalendarUtil.fillURLParameters(\n\t\t\t\t\t\t\t\tdecodeURIComponent('");
            out.print( permissionsURL );
            out.write("'),\n\t\t\t\t\t\t\t\t{\n\t\t\t\t\t\t\t\t\tmodelResourceDescription: activeCalendar.get('name'),\n\t\t\t\t\t\t\t\t\tresourceGroupId: activeCalendar.get('groupId'),\n\t\t\t\t\t\t\t\t\tresourcePrimKey: activeCalendar.get('calendarId')\n\t\t\t\t\t\t\t\t}\n\t\t\t\t\t\t\t)\n\t\t\t\t\t\t}\n\t\t\t\t\t);\n\t\t\t\t},\n\t\t\t\ticon: 'icon-lock',\n\t\t\t\tid: 'permissions'\n\t\t\t},\n\t\t\t{\n\t\t\t\tcaption: '");
            if (_jspx_meth_liferay$1ui_message_16((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
              return;
            out.write("',\n\t\t\t\tfn: function(event) {\n\t\t\t\t\tvar instance = this;\n\n\t\t\t\t\tvar calendarList = instance.get('host');\n\n\t\t\t\t\tvar activeCalendar = calendarList.activeItem;\n\n\t\t\t\t\tif (confirm('");
            if (_jspx_meth_liferay$1ui_message_17((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
              return;
            out.write("')) {\n\t\t\t\t\t\tvar remoteServices = Liferay.component('");
            if (_jspx_meth_portlet_namespace_27((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
              return;
            out.write("remoteServices');\n\n\t\t\t\t\t\tremoteServices.deleteCalendar(\n\t\t\t\t\t\t\tactiveCalendar.get('calendarId'),\n\t\t\t\t\t\t\tfunction() {\n\t\t\t\t\t\t\t\tremoteServices.getResourceCalendars(\n\t\t\t\t\t\t\t\t\tactiveCalendar.get('calendarResourceId'),\n\t\t\t\t\t\t\t\t\tfunction(calendars) {\n\t\t\t\t\t\t\t\t\t\tcalendarList.set('calendars', calendars);\n\n\t\t\t\t\t\t\t\t\t\t");
            if (_jspx_meth_portlet_namespace_28((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
              return;
            out.write("syncCalendarsMap();\n\n\t\t\t\t\t\t\t\t\t\t");
            if (_jspx_meth_portlet_namespace_29((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
              return;
            out.write("scheduler.load();\n\n\t\t\t\t\t\t\t\t\t\tLiferay.CalendarMessageUtil.showAlert('#");
            if (_jspx_meth_portlet_namespace_30((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
              return;
            out.write("alert', '");
            if (_jspx_meth_liferay$1ui_message_18((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
              return;
            out.write("');\n\t\t\t\t\t\t\t\t\t}\n\t\t\t\t\t\t\t\t);\n\t\t\t\t\t\t\t}\n\t\t\t\t\t\t);\n\t\t\t\t\t}\n\t\t\t\t},\n\t\t\t\ticon: 'icon-remove',\n\t\t\t\tid: 'delete'\n\t\t\t},\n\n\t\t\t");
            //  c:if
            com.liferay.taglib.core.IfTag _jspx_th_c_if_9 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
            _jspx_th_c_if_9.setPageContext(_jspx_page_context);
            _jspx_th_c_if_9.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
            _jspx_th_c_if_9.setTest( enableRSS );
            int _jspx_eval_c_if_9 = _jspx_th_c_if_9.doStartTag();
            if (_jspx_eval_c_if_9 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\t\t\t\t{\n\t\t\t\t\tcaption: '");
              if (_jspx_meth_liferay$1ui_message_19((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_9, _jspx_page_context))
                return;
              out.write("',\n\t\t\t\t\tfn: function(event) {\n\t\t\t\t\t\tvar instance = this;\n\n\t\t\t\t\t\tvar calendarList = instance.get('host');\n\n\t\t\t\t\t\tvar activeCalendar = calendarList.activeItem;\n\n\t\t\t\t\t\t");
              //  liferay-portlet:resourceURL
              com.liferay.taglib.portlet.ResourceURLTag _jspx_th_liferay$1portlet_resourceURL_0 = (com.liferay.taglib.portlet.ResourceURLTag) _jspx_tagPool_liferay$1portlet_resourceURL_varImpl_id.get(com.liferay.taglib.portlet.ResourceURLTag.class);
              _jspx_th_liferay$1portlet_resourceURL_0.setPageContext(_jspx_page_context);
              _jspx_th_liferay$1portlet_resourceURL_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_9);
              _jspx_th_liferay$1portlet_resourceURL_0.setId("calendarBookingsRSS");
              _jspx_th_liferay$1portlet_resourceURL_0.setVarImpl("calendarRSSURL");
              int _jspx_eval_liferay$1portlet_resourceURL_0 = _jspx_th_liferay$1portlet_resourceURL_0.doStartTag();
              if (_jspx_eval_liferay$1portlet_resourceURL_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\t\t\t\t\t\t\t");
                if (_jspx_meth_portlet_param_31((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1portlet_resourceURL_0, _jspx_page_context))
                  return;
                out.write("\n\t\t\t\t\t\t");
              }
              if (_jspx_th_liferay$1portlet_resourceURL_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_liferay$1portlet_resourceURL_varImpl_id.reuse(_jspx_th_liferay$1portlet_resourceURL_0);
                return;
              }
              _jspx_tagPool_liferay$1portlet_resourceURL_varImpl_id.reuse(_jspx_th_liferay$1portlet_resourceURL_0);
              com.liferay.portal.kernel.portlet.LiferayPortletURL calendarRSSURL = null;
              calendarRSSURL = (com.liferay.portal.kernel.portlet.LiferayPortletURL) _jspx_page_context.findAttribute("calendarRSSURL");
              out.write("\n\n\t\t\t\t\t\tvar url = Liferay.CalendarUtil.fillURLParameters(\n\t\t\t\t\t\t\tdecodeURIComponent('");
              out.print( calendarRSSURL );
              out.write("'),\n\t\t\t\t\t\t\t{\n\t\t\t\t\t\t\t\tcalendarId: activeCalendar.get('calendarId')\n\t\t\t\t\t\t\t}\n\t\t\t\t\t\t);\n\n\t\t\t\t\t\twindow.open(url, '_blank');\n\n\t\t\t\t\t\tinstance.set('visible', false);\n\t\t\t\t\t},\n\t\t\t\t\ticon: 'icon-rss',\n\t\t\t\t\tid: 'subscribe'\n\t\t\t\t},\n\t\t\t");
            }
            if (_jspx_th_c_if_9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_9);
              return;
            }
            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_9);
            out.write("\n\n\t\t\t{\n\t\t\t\tcaption: '-',\n\t\t\t\tid: 'separator1'\n\t\t\t},\n\t\t\t{\n\t\t\t\tcaption: '<div class=\"calendar-portlet-color-picker\"></div>',\n\t\t\t\tid: 'colorPicker'\n\t\t\t}\n\t\t],\n\t\ton: {\n\t\t\tvisibleChange: function(event) {\n\t\t\t\tvar instance = this;\n\n\t\t\t\tvar calendarList = instance.get('host');\n\n\t\t\t\tvar calendar = calendarList.activeItem;\n\n\t\t\t\tif (calendar && event.newVal) {\n\t\t\t\t\tvar permissions = calendar.get('permissions');\n\n\t\t\t\t\tvar hiddenItems = [];\n\n\t\t\t\t\tif (calendarList !== window.");
            if (_jspx_meth_portlet_namespace_31((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
              return;
            out.write("otherCalendarList) {\n\t\t\t\t\t\thiddenItems.push('hide');\n\t\t\t\t\t}\n\n\t\t\t\t\tvar defaultCalendar = calendar.get('defaultCalendar');\n\n\t\t\t\t\tif ((permissions.DELETE === false) || (defaultCalendar === true)) {\n\t\t\t\t\t\thiddenItems.push('delete');\n\t\t\t\t\t}\n\n\t\t\t\t\tif (permissions.MANAGE_BOOKINGS === false) {\n\t\t\t\t\t\thiddenItems.push('addEvent');\n\t\t\t\t\t}\n\n\t\t\t\t\tif (permissions.PERMISSIONS === false) {\n\t\t\t\t\t\thiddenItems.push('permissions');\n\t\t\t\t\t}\n\n\t\t\t\t\tif (permissions.UPDATE === false) {\n\t\t\t\t\t\thiddenItems.push('settings');\n\t\t\t\t\t\thiddenItems.push('separator1');\n\t\t\t\t\t\thiddenItems.push('colorPicker');\n\t\t\t\t\t}\n\n\t\t\t\t\tinstance.set('hiddenItems', hiddenItems);\n\n\t\t\t\t\t");
            if (_jspx_meth_portlet_namespace_32((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
              return;
            out.write("colorPicker.set('host', instance);\n\n\t\t\t\t\t");
            if (_jspx_meth_portlet_namespace_33((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
              return;
            out.write("colorPicker.setAttrs(\n\t\t\t\t\t\t{\n\t\t\t\t\t\t\tcolor: calendar.get('color'),\n\t\t\t\t\t\t\tvisible: true\n\t\t\t\t\t\t}\n\t\t\t\t\t);\n\n\t\t\t\t\tvar colorPickerContainer = instance.get('boundingBox').one('.calendar-portlet-color-picker');\n\n\t\t\t\t\tcolorPickerContainer.append(window.");
            if (_jspx_meth_portlet_namespace_34((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
              return;
            out.write("colorPicker.get('boundingBox'));\n\t\t\t\t}\n\t\t\t}\n\t\t},\n\t\twidth: 225\n\t};\n\n\t");
            if (_jspx_meth_portlet_namespace_35((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
              return;
            out.write("colorPicker = new Liferay.SimpleColorPicker(\n\t\t{\n\t\t\ton: {\n\t\t\t\tcolorChange: function(event) {\n\t\t\t\t\tvar instance = this;\n\n\t\t\t\t\tvar simpleMenu = instance.get('host');\n\n\t\t\t\t\tif (simpleMenu) {\n\t\t\t\t\t\tvar calendarList = simpleMenu.get('host');\n\n\t\t\t\t\t\tcalendarList.activeItem.set(\n\t\t\t\t\t\t\t'color',\n\t\t\t\t\t\t\tevent.newVal,\n\t\t\t\t\t\t\t{\n\t\t\t\t\t\t\t\tsilent: true\n\t\t\t\t\t\t\t}\n\t\t\t\t\t\t);\n\n\t\t\t\t\t\tsimpleMenu.set('visible', false);\n\t\t\t\t\t}\n\t\t\t\t}\n\t\t\t},\n\t\t\tvisible: false\n\t\t}\n\t).render();\n\n\tA.one('#");
            if (_jspx_meth_portlet_namespace_36((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
              return;
            out.write("calendarListContainer').delegate(\n\t\t'click',\n\t\tfunction(event) {\n\t\t\tvar currentTarget = event.currentTarget;\n\n\t\t\twindow.");
            if (_jspx_meth_portlet_namespace_37((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
              return;
            out.write("calendarListsMenu.calendarResourceId = currentTarget.getAttribute('data-calendarResourceId');\n\n\t\t\twindow.");
            if (_jspx_meth_portlet_namespace_38((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
              return;
            out.write("calendarListsMenu.setAttrs(\n\t\t\t\t{\n\t\t\t\t\talignNode: currentTarget,\n\t\t\t\t\ttoggler: currentTarget,\n\t\t\t\t\tvisible: !window.");
            if (_jspx_meth_portlet_namespace_39((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
              return;
            out.write("calendarListsMenu.get('visible')\n\t\t\t\t}\n\t\t\t);\n\t\t},\n\t\t'.calendar-resource-arrow'\n\t);\n\n\twindow.");
            if (_jspx_meth_portlet_namespace_40((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
              return;
            out.write("toggler = new A.TogglerDelegate(\n\t\t{\n\t\t\tanimated: true,\n\t\t\tcontainer: '#");
            if (_jspx_meth_portlet_namespace_41((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
              return;
            out.write("calendarListContainer',\n\t\t\tcontent: '.calendar-portlet-calendar-list',\n\t\t\theader: '.calendar-portlet-list-header'\n\t\t}\n\t);\n\n\t");
            //  c:if
            com.liferay.taglib.core.IfTag _jspx_th_c_if_10 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
            _jspx_th_c_if_10.setPageContext(_jspx_page_context);
            _jspx_th_c_if_10.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
            _jspx_th_c_if_10.setTest( themeDisplay.isSignedIn() );
            int _jspx_eval_c_if_10 = _jspx_th_c_if_10.doStartTag();
            if (_jspx_eval_c_if_10 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\t\tvar addOtherCalendarInput = A.one('#");
              if (_jspx_meth_portlet_namespace_42((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_10, _jspx_page_context))
                return;
              out.write("addOtherCalendar');\n\n\t\t");
              //  liferay-portlet:resourceURL
              com.liferay.taglib.portlet.ResourceURLTag _jspx_th_liferay$1portlet_resourceURL_1 = (com.liferay.taglib.portlet.ResourceURLTag) _jspx_tagPool_liferay$1portlet_resourceURL_var_id_copyCurrentRenderParameters_nobody.get(com.liferay.taglib.portlet.ResourceURLTag.class);
              _jspx_th_liferay$1portlet_resourceURL_1.setPageContext(_jspx_page_context);
              _jspx_th_liferay$1portlet_resourceURL_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_10);
              _jspx_th_liferay$1portlet_resourceURL_1.setCopyCurrentRenderParameters( false );
              _jspx_th_liferay$1portlet_resourceURL_1.setId("calendarResources");
              _jspx_th_liferay$1portlet_resourceURL_1.setVar("calendarResourcesURL");
              int _jspx_eval_liferay$1portlet_resourceURL_1 = _jspx_th_liferay$1portlet_resourceURL_1.doStartTag();
              if (_jspx_th_liferay$1portlet_resourceURL_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_liferay$1portlet_resourceURL_var_id_copyCurrentRenderParameters_nobody.reuse(_jspx_th_liferay$1portlet_resourceURL_1);
                return;
              }
              _jspx_tagPool_liferay$1portlet_resourceURL_var_id_copyCurrentRenderParameters_nobody.reuse(_jspx_th_liferay$1portlet_resourceURL_1);
              java.lang.String calendarResourcesURL = null;
              calendarResourcesURL = (java.lang.String) _jspx_page_context.findAttribute("calendarResourcesURL");
              out.write("\n\n\t\tcalendarContainer.createCalendarsAutoComplete(\n\t\t\t'");
              out.print( calendarResourcesURL );
              out.write("',\n\t\t\taddOtherCalendarInput,\n\t\t\tfunction(event) {\n\t\t\t\twindow.");
              if (_jspx_meth_portlet_namespace_43((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_10, _jspx_page_context))
                return;
              out.write("otherCalendarList.add(event.result.raw);\n\n\t\t\t\t");
              if (_jspx_meth_portlet_namespace_44((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_10, _jspx_page_context))
                return;
              out.write("refreshVisibleCalendarRenderingRules();\n\n\t\t\t\taddOtherCalendarInput.val('');\n\t\t\t}\n\t\t);\n\t");
            }
            if (_jspx_th_c_if_10.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_10);
              return;
            }
            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_10);
            out.write("\n\n\tA.one('#");
            if (_jspx_meth_portlet_namespace_45((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
              return;
            out.write("columnToggler').on(\n\t\t'click',\n\t\tfunction(event) {\n\t\t\tvar columnGrid = A.one('#");
            if (_jspx_meth_portlet_namespace_46((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
              return;
            out.write("columnGrid');\n\t\t\tvar columnOptions = A.one('#");
            if (_jspx_meth_portlet_namespace_47((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
              return;
            out.write("columnOptions');\n\t\t\tvar columnTogglerIcon = A.one('#");
            if (_jspx_meth_portlet_namespace_48((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
              return;
            out.write("columnTogglerIcon');\n\n\t\t\tLiferay.Store('com.liferay.calendar.web_columnOptionsVisible', columnOptions.hasClass('hide'));\n\n\t\t\tcolumnGrid.toggleClass('col-md-9').toggleClass('col-md-12');\n\n\t\t\tcolumnOptions.toggleClass('hide');\n\n\t\t\tcolumnTogglerIcon.toggleClass('icon-caret-left').toggleClass('icon-caret-right');\n\t\t}\n\t);\n\n\twindow.");
            if (_jspx_meth_portlet_namespace_49((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
              return;
            out.write("refreshMiniCalendarSelectedDates = function() {\n\t\t");
            if (_jspx_meth_portlet_namespace_50((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
              return;
            out.write("miniCalendar._clearSelection();\n\n\t\tvar activeView = ");
            if (_jspx_meth_portlet_namespace_51((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
              return;
            out.write("scheduler.get('activeView');\n\t\tvar viewDate = ");
            if (_jspx_meth_portlet_namespace_52((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
              return;
            out.write("scheduler.get('viewDate');\n\n\t\tvar viewName = activeView.get('name');\n\n\t\tvar total = 1;\n\n\t\tif (viewName == 'month') {\n\t\t\ttotal = A.Date.daysInMonth(viewDate);\n\t\t}\n\t\telse if (viewName == 'week') {\n\t\t\ttotal = 7;\n\t\t}\n\n\t\tvar selectedDates = Liferay.CalendarUtil.getDatesList(viewDate, total);\n\n\t\t");
            if (_jspx_meth_portlet_namespace_53((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
              return;
            out.write("miniCalendar.selectDates(selectedDates);\n\n\t\tvar todayDate = ");
            if (_jspx_meth_portlet_namespace_54((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
              return;
            out.write("scheduler.get('todayDate');\n\n\t\tif ((selectedDates.length > 0) && DateMath.between(todayDate, selectedDates[0], selectedDates[total - 1])) {\n\t\t\tviewDate = todayDate;\n\t\t}\n\n\t\t");
            if (_jspx_meth_portlet_namespace_55((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
              return;
            out.write("miniCalendar.set('date', viewDate);\n\t};\n\n\tvar DateMath = A.DataType.DateMath;\n\n\twindow.");
            if (_jspx_meth_portlet_namespace_56((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
              return;
            out.write("refreshVisibleCalendarRenderingRules = function() {\n\t\tvar miniCalendarStartDate = DateMath.subtract(DateMath.toMidnight(window.");
            if (_jspx_meth_portlet_namespace_57((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
              return;
            out.write("miniCalendar.get('date')), DateMath.WEEK, 1);\n\n\t\tvar miniCalendarEndDate = DateMath.add(DateMath.add(window.");
            if (_jspx_meth_portlet_namespace_58((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
              return;
            out.write("miniCalendar.get('date'), DateMath.MONTH, 1), DateMath.WEEK, 1);\n\n\t\tminiCalendarEndDate.setHours(23, 59, 59, 999);\n\n\t\tremoteServices.getCalendarRenderingRules(\n\t\t\tA.Object.keys(calendarContainer.get('visibleCalendars')),\n\t\t\tLiferay.CalendarUtil.toUTC(miniCalendarStartDate),\n\t\t\tLiferay.CalendarUtil.toUTC(miniCalendarEndDate),\n\t\t\t'busy',\n\t\t\tfunction(rulesDefinition) {\n\t\t\t\tvar selectedDates = ");
            if (_jspx_meth_portlet_namespace_59((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
              return;
            out.write("miniCalendar._getSelectedDatesList();\n\n\t\t\t\twindow.");
            if (_jspx_meth_portlet_namespace_60((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
              return;
            out.write("miniCalendar.set(\n\t\t\t\t\t'customRenderer',\n\t\t\t\t\t{\n\t\t\t\t\t\tfilterFunction: function(date, node, rules) {\n\t\t\t\t\t\t\tnode.addClass('lfr-busy-day');\n\n\t\t\t\t\t\t\tDateMath.toMidnight(date);\n\n\t\t\t\t\t\t\tvar selected = (selectedDates.length > 0) && A.Date.isInRange(date, selectedDates[0], selectedDates[selectedDates.length - 1]);\n\n\t\t\t\t\t\t\tif (A.DataType.DateMath.isToday(date)) {\n\t\t\t\t\t\t\t\tnode.addClass('lfr-current-day');\n\t\t\t\t\t\t\t}\n\n\t\t\t\t\t\t\tnode.toggleClass('yui3-calendar-day-selected', selected);\n\t\t\t\t\t\t},\n\t\t\t\t\t\trules: rulesDefinition\n\t\t\t\t\t}\n\t\t\t\t);\n\n\t\t\t\t");
            if (_jspx_meth_portlet_namespace_61((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
              return;
            out.write("miniCalendar.selectDates(selectedDates);\n\t\t\t}\n\t\t);\n\t};\n\n\twindow.");
            if (_jspx_meth_portlet_namespace_62((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
              return;
            out.write("miniCalendar = new A.Calendar(\n\t\t{\n\t\t\tafter: {\n\t\t\t\tdateChange: ");
            if (_jspx_meth_portlet_namespace_63((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
              return;
            out.write("refreshVisibleCalendarRenderingRules,\n\t\t\t\tdateClick: function(event) {\n\t\t\t\t\t");
            if (_jspx_meth_portlet_namespace_64((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
              return;
            out.write("scheduler.setAttrs(\n\t\t\t\t\t\t{\n\t\t\t\t\t\t\tactiveView: ");
            if (_jspx_meth_portlet_namespace_65((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
              return;
            out.write("dayView,\n\t\t\t\t\t\t\tdate: event.date\n\t\t\t\t\t\t}\n\t\t\t\t\t);\n\t\t\t\t}\n\t\t\t},\n\t\t\tdate: new Date(");
            out.print( String.valueOf(date) );
            out.write("),\n\t\t\theaderRenderer: '");
            out.print( HtmlUtil.escapeJS(LanguageUtil.get(request, "b-y")) );
            out.write("',\n\t\t\tlocale: '");
            out.print( themeDisplay.getLocale() );
            out.write("',\n\t\t\t'strings.first_weekday': ");
            out.print( weekStartsOn );
            out.write("\n\t\t}\n\t).render('#");
            if (_jspx_meth_portlet_namespace_66((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
              return;
            out.write("miniCalendarContainer');\n\n\t");
            if (_jspx_meth_portlet_namespace_67((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
              return;
            out.write("scheduler.after(\n\t\t['*:add', '*:change', '*:load', '*:remove', '*:reset'],\n\t\tA.debounce(");
            if (_jspx_meth_portlet_namespace_68((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
              return;
            out.write("refreshVisibleCalendarRenderingRules, 100)\n\t);\n\n\t");
            if (_jspx_meth_portlet_namespace_69((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
              return;
            out.write("scheduler.after(\n\t\t['activeViewChange', 'dateChange'],\n\t\t");
            if (_jspx_meth_portlet_namespace_70((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
              return;
            out.write("refreshMiniCalendarSelectedDates\n\t);\n\n\t");
            if (_jspx_meth_portlet_namespace_71((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
              return;
            out.write("refreshVisibleCalendarRenderingRules();\n\t");
            if (_jspx_meth_portlet_namespace_72((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
              return;
            out.write("refreshMiniCalendarSelectedDates();\n");
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
      }
      if (_jspx_th_c_if_8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_8);
        return;
      }
      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_8);
      out.write('\n');
      out.write('\n');
      //  aui:script
      com.liferay.taglib.aui.ScriptTag _jspx_th_aui_script_2 = (com.liferay.taglib.aui.ScriptTag) _jspx_tagPool_aui_script_use.get(com.liferay.taglib.aui.ScriptTag.class);
      _jspx_th_aui_script_2.setPageContext(_jspx_page_context);
      _jspx_th_aui_script_2.setParent(null);
      _jspx_th_aui_script_2.setUse("liferay-calendar-list,liferay-scheduler,liferay-store,liferay-calendar-util");
      int _jspx_eval_aui_script_2 = _jspx_th_aui_script_2.doStartTag();
      if (_jspx_eval_aui_script_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        if (_jspx_eval_aui_script_2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
          out = _jspx_page_context.pushBody();
          _jspx_th_aui_script_2.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
          _jspx_th_aui_script_2.doInitBody();
        }
        do {
          out.write("\n\tLiferay.CalendarUtil.USER_CLASS_NAME_ID = ");
          out.print( PortalUtil.getClassNameId(User.class) );
          out.write(";\n\n\tvar calendarContainer = Liferay.component('");
          if (_jspx_meth_portlet_namespace_73((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_2, _jspx_page_context))
            return;
          out.write("calendarContainer');\n\n\tvar syncCalendarsMap = function() {\n\t\tvar calendarLists = [];\n\n\t\t");
          //  c:if
          com.liferay.taglib.core.IfTag _jspx_th_c_if_11 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
          _jspx_th_c_if_11.setPageContext(_jspx_page_context);
          _jspx_th_c_if_11.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_2);
          _jspx_th_c_if_11.setTest( themeDisplay.isSignedIn() || (groupCalendarResource != null) );
          int _jspx_eval_c_if_11 = _jspx_th_c_if_11.doStartTag();
          if (_jspx_eval_c_if_11 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t\tcalendarLists.push(window.");
            if (_jspx_meth_portlet_namespace_74((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_11, _jspx_page_context))
              return;
            out.write("myCalendarList);\n\t\t");
          }
          if (_jspx_th_c_if_11.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_11);
            return;
          }
          _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_11);
          out.write("\n\n\t\t");
          //  c:if
          com.liferay.taglib.core.IfTag _jspx_th_c_if_12 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
          _jspx_th_c_if_12.setPageContext(_jspx_page_context);
          _jspx_th_c_if_12.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_2);
          _jspx_th_c_if_12.setTest( themeDisplay.isSignedIn() );
          int _jspx_eval_c_if_12 = _jspx_th_c_if_12.doStartTag();
          if (_jspx_eval_c_if_12 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t\tcalendarLists.push(window.");
            if (_jspx_meth_portlet_namespace_75((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_12, _jspx_page_context))
              return;
            out.write("otherCalendarList);\n\t\t");
          }
          if (_jspx_th_c_if_12.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_12);
            return;
          }
          _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_12);
          out.write("\n\n\t\t");
          //  c:if
          com.liferay.taglib.core.IfTag _jspx_th_c_if_13 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
          _jspx_th_c_if_13.setPageContext(_jspx_page_context);
          _jspx_th_c_if_13.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_2);
          _jspx_th_c_if_13.setTest( showSiteCalendars );
          int _jspx_eval_c_if_13 = _jspx_th_c_if_13.doStartTag();
          if (_jspx_eval_c_if_13 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t\tcalendarLists.push(window.");
            if (_jspx_meth_portlet_namespace_76((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_13, _jspx_page_context))
              return;
            out.write("siteCalendarList);\n\t\t");
          }
          if (_jspx_th_c_if_13.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_13);
            return;
          }
          _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_13);
          out.write("\n\n\t\tcalendarContainer.syncCalendarsMap(calendarLists);\n\t};\n\n\twindow.");
          if (_jspx_meth_portlet_namespace_77((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_2, _jspx_page_context))
            return;
          out.write("syncCalendarsMap = syncCalendarsMap;\n\n\twindow.");
          if (_jspx_meth_portlet_namespace_78((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_2, _jspx_page_context))
            return;
          out.write("calendarLists = {};\n\n\t");
          //  c:if
          com.liferay.taglib.core.IfTag _jspx_th_c_if_14 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
          _jspx_th_c_if_14.setPageContext(_jspx_page_context);
          _jspx_th_c_if_14.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_2);
          _jspx_th_c_if_14.setTest( themeDisplay.isSignedIn() || (groupCalendarResource != null) );
          int _jspx_eval_c_if_14 = _jspx_th_c_if_14.doStartTag();
          if (_jspx_eval_c_if_14 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\twindow.");
            if (_jspx_meth_portlet_namespace_79((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_14, _jspx_page_context))
              return;
            out.write("myCalendarList = new Liferay.CalendarList(\n\t\t\t{\n\t\t\t\tafter: {\n\t\t\t\t\tcalendarsChange: syncCalendarsMap,\n\t\t\t\t\t'scheduler-calendar:visibleChange': function(event) {\n\t\t\t\t\t\tsyncCalendarsMap();\n\n\t\t\t\t\t\t");
            if (_jspx_meth_portlet_namespace_80((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_14, _jspx_page_context))
              return;
            out.write("refreshVisibleCalendarRenderingRules();\n\t\t\t\t\t}\n\t\t\t\t},\n\t\t\t\tboundingBox: '#");
            if (_jspx_meth_portlet_namespace_81((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_14, _jspx_page_context))
              return;
            out.write("myCalendarList',\n\n\t\t\t\t");

				updateCalendarsJSONArray(request, userCalendarsJSONArray);
				
            out.write("\n\n\t\t\t\tcalendars: ");
            out.print( userCalendarsJSONArray );
            out.write(",\n\t\t\t\tscheduler: ");
            if (_jspx_meth_portlet_namespace_82((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_14, _jspx_page_context))
              return;
            out.write("scheduler,\n\t\t\t\tshowCalendarResourceName: false,\n\t\t\t\tsimpleMenu: window.");
            if (_jspx_meth_portlet_namespace_83((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_14, _jspx_page_context))
              return;
            out.write("calendarsMenu,\n\t\t\t\tvisible: ");
            out.print( !displaySchedulerOnly && themeDisplay.isSignedIn() );
            out.write("\n\t\t\t}\n\t\t).render();\n\n\t\t");
            //  c:if
            com.liferay.taglib.core.IfTag _jspx_th_c_if_15 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
            _jspx_th_c_if_15.setPageContext(_jspx_page_context);
            _jspx_th_c_if_15.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_14);
            _jspx_th_c_if_15.setTest( userCalendarResource != null );
            int _jspx_eval_c_if_15 = _jspx_th_c_if_15.doStartTag();
            if (_jspx_eval_c_if_15 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\t\t\twindow.");
              if (_jspx_meth_portlet_namespace_84((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_15, _jspx_page_context))
                return;
              out.write("calendarLists['");
              out.print( userCalendarResource.getCalendarResourceId() );
              out.write("'] = window.");
              if (_jspx_meth_portlet_namespace_85((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_15, _jspx_page_context))
                return;
              out.write("myCalendarList;\n\t\t");
            }
            if (_jspx_th_c_if_15.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_15);
              return;
            }
            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_15);
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
          com.liferay.taglib.core.IfTag _jspx_th_c_if_16 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
          _jspx_th_c_if_16.setPageContext(_jspx_page_context);
          _jspx_th_c_if_16.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_2);
          _jspx_th_c_if_16.setTest( themeDisplay.isSignedIn() );
          int _jspx_eval_c_if_16 = _jspx_th_c_if_16.doStartTag();
          if (_jspx_eval_c_if_16 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\twindow.");
            if (_jspx_meth_portlet_namespace_86((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_16, _jspx_page_context))
              return;
            out.write("otherCalendarList = new Liferay.CalendarList(\n\t\t\t{\n\t\t\t\tafter: {\n\t\t\t\t\tcalendarsChange: function(event) {\n\t\t\t\t\t\tsyncCalendarsMap();\n\n\t\t\t\t\t\t");
            if (_jspx_meth_portlet_namespace_87((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_16, _jspx_page_context))
              return;
            out.write("scheduler.load();\n\n\t\t\t\t\t\tvar calendarIds = A.Array.invoke(event.newVal, 'get', 'calendarId');\n\n\t\t\t\t\t\tLiferay.Store('com.liferay.calendar.web_otherCalendars', calendarIds.join());\n\t\t\t\t\t},\n\t\t\t\t\t'scheduler-calendar:visibleChange': function(event) {\n\t\t\t\t\t\tsyncCalendarsMap();\n\n\t\t\t\t\t\t");
            if (_jspx_meth_portlet_namespace_88((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_16, _jspx_page_context))
              return;
            out.write("refreshVisibleCalendarRenderingRules();\n\t\t\t\t\t}\n\t\t\t\t},\n\t\t\t\tboundingBox: '#");
            if (_jspx_meth_portlet_namespace_89((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_16, _jspx_page_context))
              return;
            out.write("otherCalendarList',\n\n\t\t\t\t");

				updateCalendarsJSONArray(request, otherCalendarsJSONArray);
				
            out.write("\n\n\t\t\t\tcalendars: ");
            out.print( otherCalendarsJSONArray );
            out.write(",\n\t\t\t\tscheduler: ");
            if (_jspx_meth_portlet_namespace_90((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_16, _jspx_page_context))
              return;
            out.write("scheduler,\n\t\t\t\tsimpleMenu: window.");
            if (_jspx_meth_portlet_namespace_91((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_16, _jspx_page_context))
              return;
            out.write("calendarsMenu,\n\t\t\t\tvisible: ");
            out.print( !displaySchedulerOnly );
            out.write("\n\t\t\t}\n\t\t).render();\n\t");
          }
          if (_jspx_th_c_if_16.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_16);
            return;
          }
          _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_16);
          out.write("\n\n\t");
          //  c:if
          com.liferay.taglib.core.IfTag _jspx_th_c_if_17 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
          _jspx_th_c_if_17.setPageContext(_jspx_page_context);
          _jspx_th_c_if_17.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_2);
          _jspx_th_c_if_17.setTest( showSiteCalendars );
          int _jspx_eval_c_if_17 = _jspx_th_c_if_17.doStartTag();
          if (_jspx_eval_c_if_17 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\twindow.");
            if (_jspx_meth_portlet_namespace_92((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_17, _jspx_page_context))
              return;
            out.write("siteCalendarList = new Liferay.CalendarList(\n\t\t\t{\n\t\t\t\tafter: {\n\t\t\t\t\tcalendarsChange: syncCalendarsMap,\n\t\t\t\t\t'scheduler-calendar:visibleChange': function(event) {\n\t\t\t\t\t\tsyncCalendarsMap();\n\n\t\t\t\t\t\t");
            if (_jspx_meth_portlet_namespace_93((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_17, _jspx_page_context))
              return;
            out.write("refreshVisibleCalendarRenderingRules();\n\t\t\t\t\t}\n\t\t\t\t},\n\t\t\t\tboundingBox: '#");
            if (_jspx_meth_portlet_namespace_94((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_17, _jspx_page_context))
              return;
            out.write("siteCalendarList',\n\n\t\t\t\t");

				updateCalendarsJSONArray(request, groupCalendarsJSONArray);
				
            out.write("\n\n\t\t\t\tcalendars: ");
            out.print( groupCalendarsJSONArray );
            out.write(",\n\t\t\t\tscheduler: ");
            if (_jspx_meth_portlet_namespace_95((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_17, _jspx_page_context))
              return;
            out.write("scheduler,\n\t\t\t\tshowCalendarResourceName: false,\n\t\t\t\tsimpleMenu: window.");
            if (_jspx_meth_portlet_namespace_96((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_17, _jspx_page_context))
              return;
            out.write("calendarsMenu,\n\t\t\t\tvisible: ");
            out.print( !displaySchedulerOnly );
            out.write("\n\t\t\t}\n\t\t).render();\n\n\t\twindow.");
            if (_jspx_meth_portlet_namespace_97((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_17, _jspx_page_context))
              return;
            out.write("calendarLists['");
            out.print( groupCalendarResource.getCalendarResourceId() );
            out.write("'] = window.");
            if (_jspx_meth_portlet_namespace_98((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_17, _jspx_page_context))
              return;
            out.write("siteCalendarList;\n\t");
          }
          if (_jspx_th_c_if_17.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_17);
            return;
          }
          _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_17);
          out.write("\n\n\tsyncCalendarsMap();\n\n\tA.each(\n\t\tcalendarContainer.get('availableCalendars'),\n\t\tfunction(item, index) {\n\t\t\titem.on(\n\t\t\t\t{\n\t\t\t\t\t'visibleChange': function(event) {\n\t\t\t\t\t\tvar instance = this;\n\n\t\t\t\t\t\tvar calendar = event.currentTarget;\n\n\t\t\t\t\t\tLiferay.Store('com.liferay.calendar.web_calendar' + calendar.get('calendarId') + 'Visible', event.newVal);\n\t\t\t\t\t}\n\t\t\t\t}\n\t\t\t);\n\t\t}\n\t);\n");
          int evalDoAfterBody = _jspx_th_aui_script_2.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
        if (_jspx_eval_aui_script_2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
          out = _jspx_page_context.popBody();
      }
      if (_jspx_th_aui_script_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_aui_script_use.reuse(_jspx_th_aui_script_2);
        return;
      }
      _jspx_tagPool_aui_script_use.reuse(_jspx_th_aui_script_2);
      out.write('\n');
      out.write('\n');
      if (_jspx_meth_aui_script_3(_jspx_page_context))
        return;
      out.write('\n');
      out.write('\n');
      //  aui:script
      com.liferay.taglib.aui.ScriptTag _jspx_th_aui_script_4 = (com.liferay.taglib.aui.ScriptTag) _jspx_tagPool_aui_script.get(com.liferay.taglib.aui.ScriptTag.class);
      _jspx_th_aui_script_4.setPageContext(_jspx_page_context);
      _jspx_th_aui_script_4.setParent(null);
      int _jspx_eval_aui_script_4 = _jspx_th_aui_script_4.doStartTag();
      if (_jspx_eval_aui_script_4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        if (_jspx_eval_aui_script_4 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
          out = _jspx_page_context.pushBody();
          _jspx_th_aui_script_4.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
          _jspx_th_aui_script_4.doInitBody();
        }
        do {
          out.write("\n\tvar destroyMenus = function(event) {\n\t\tif (window.");
          if (_jspx_meth_portlet_namespace_105((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_4, _jspx_page_context))
            return;
          out.write("calendarListsMenu) {\n\t\t\twindow.");
          if (_jspx_meth_portlet_namespace_106((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_4, _jspx_page_context))
            return;
          out.write("calendarListsMenu.destroy();\n\t\t}\n\n\t\tif (window.");
          if (_jspx_meth_portlet_namespace_107((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_4, _jspx_page_context))
            return;
          out.write("colorPicker) {\n\t\t\twindow.");
          if (_jspx_meth_portlet_namespace_108((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_4, _jspx_page_context))
            return;
          out.write("colorPicker.destroy();\n\t\t}\n\n\t\tvar myCalendarList = window.");
          if (_jspx_meth_portlet_namespace_109((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_4, _jspx_page_context))
            return;
          out.write("myCalendarList;\n\t\tvar otherCalendarList = window.");
          if (_jspx_meth_portlet_namespace_110((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_4, _jspx_page_context))
            return;
          out.write("otherCalendarList;\n\t\tvar siteCalendarList = window.");
          if (_jspx_meth_portlet_namespace_111((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_4, _jspx_page_context))
            return;
          out.write("siteCalendarList;\n\n\t\tif (myCalendarList && myCalendarList.simpleMenu) {\n\t\t\tmyCalendarList.simpleMenu.destroy();\n\t\t\tmyCalendarList.destroy();\n\t\t}\n\n\t\tif (otherCalendarList && otherCalendarList.simpleMenu) {\n\t\t\totherCalendarList.simpleMenu.destroy();\n\t\t\totherCalendarList.destroy();\n\t\t}\n\n\t\tif (siteCalendarList && siteCalendarList.simpleMenu) {\n\t\t\tsiteCalendarList.simpleMenu.destroy();\n\t\t\tsiteCalendarList.destroy();\n\t\t}\n\n\t\tLiferay.detach('");
          out.print( portletDisplay.getId() );
          out.write(":portletRefreshed', destroyMenus);\n\t\tLiferay.detach('destroyPortlet', destroyMenus);\n\t};\n\tLiferay.on('");
          out.print( portletDisplay.getId() );
          out.write(":portletRefreshed', destroyMenus);\n\tLiferay.on('destroyPortlet', destroyMenus);\n");
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
      out.write('\n');
      out.write('\n');
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

  private boolean _jspx_meth_portlet_namespace_4(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_4 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_4.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_0);
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

  private boolean _jspx_meth_portlet_namespace_6(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_col_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_6 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_6.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_col_0);
    int _jspx_eval_portlet_namespace_6 = _jspx_th_portlet_namespace_6.doStartTag();
    if (_jspx_th_portlet_namespace_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_6);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_6);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_7(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_col_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_7 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_7.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_col_0);
    int _jspx_eval_portlet_namespace_7 = _jspx_th_portlet_namespace_7.doStartTag();
    if (_jspx_th_portlet_namespace_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_7);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_7);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_0 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_0.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_2);
    _jspx_th_liferay$1ui_message_0.setKey("my-calendars");
    int _jspx_eval_liferay$1ui_message_0 = _jspx_th_liferay$1ui_message_0.doStartTag();
    if (_jspx_th_liferay$1ui_message_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_0);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_0);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_8(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_col_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_8 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_8.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_8.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_col_0);
    int _jspx_eval_portlet_namespace_8 = _jspx_th_portlet_namespace_8.doStartTag();
    if (_jspx_th_portlet_namespace_8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_8);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_8);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_9(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_9 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_9.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_9.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_4);
    int _jspx_eval_portlet_namespace_9 = _jspx_th_portlet_namespace_9.doStartTag();
    if (_jspx_th_portlet_namespace_9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_9);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_9);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_2(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_6, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_2 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_2.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_6);
    _jspx_th_liferay$1ui_message_2.setKey("other-calendars");
    int _jspx_eval_liferay$1ui_message_2 = _jspx_th_liferay$1ui_message_2.doStartTag();
    if (_jspx_th_liferay$1ui_message_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_2);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_2);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_10(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_6, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_10 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_10.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_10.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_6);
    int _jspx_eval_portlet_namespace_10 = _jspx_th_portlet_namespace_10.doStartTag();
    if (_jspx_th_portlet_namespace_10.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_10);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_10);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_11(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_6, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_11 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_11.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_11.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_6);
    int _jspx_eval_portlet_namespace_11 = _jspx_th_portlet_namespace_11.doStartTag();
    if (_jspx_th_portlet_namespace_11.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_11);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_11);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_3(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_6, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_3 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_3.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_6);
    _jspx_th_liferay$1ui_message_3.setKey("add-other-calendars");
    int _jspx_eval_liferay$1ui_message_3 = _jspx_th_liferay$1ui_message_3.doStartTag();
    if (_jspx_th_liferay$1ui_message_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_3);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_3);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_12(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_7, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_12 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_12.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_12.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_7);
    int _jspx_eval_portlet_namespace_12 = _jspx_th_portlet_namespace_12.doStartTag();
    if (_jspx_th_portlet_namespace_12.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_12);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_12);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_13(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_7, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_13 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_13.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_13.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_7);
    int _jspx_eval_portlet_namespace_13 = _jspx_th_portlet_namespace_13.doStartTag();
    if (_jspx_th_portlet_namespace_13.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_13);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_13);
    return false;
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
    _jspx_th_portlet_param_0.setValue("/edit_calendar_booking.jsp");
    int _jspx_eval_portlet_param_0 = _jspx_th_portlet_param_0.doStartTag();
    if (_jspx_th_portlet_param_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_0);
      return true;
    }
    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_0);
    return false;
  }

  private boolean _jspx_meth_portlet_param_1(javax.servlet.jsp.tagext.JspTag _jspx_th_portlet_renderURL_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:param
    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_1 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
    _jspx_th_portlet_param_1.setPageContext(_jspx_page_context);
    _jspx_th_portlet_param_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_0);
    _jspx_th_portlet_param_1.setName("activeView");
    _jspx_th_portlet_param_1.setValue("{activeView}");
    int _jspx_eval_portlet_param_1 = _jspx_th_portlet_param_1.doStartTag();
    if (_jspx_th_portlet_param_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_1);
      return true;
    }
    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_1);
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
    _jspx_th_portlet_param_2.setName("allDay");
    _jspx_th_portlet_param_2.setValue("{allDay}");
    int _jspx_eval_portlet_param_2 = _jspx_th_portlet_param_2.doStartTag();
    if (_jspx_th_portlet_param_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_2);
      return true;
    }
    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_2);
    return false;
  }

  private boolean _jspx_meth_portlet_param_3(javax.servlet.jsp.tagext.JspTag _jspx_th_portlet_renderURL_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:param
    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_3 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
    _jspx_th_portlet_param_3.setPageContext(_jspx_page_context);
    _jspx_th_portlet_param_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_0);
    _jspx_th_portlet_param_3.setName("calendarBookingId");
    _jspx_th_portlet_param_3.setValue("{calendarBookingId}");
    int _jspx_eval_portlet_param_3 = _jspx_th_portlet_param_3.doStartTag();
    if (_jspx_th_portlet_param_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_3);
      return true;
    }
    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_3);
    return false;
  }

  private boolean _jspx_meth_portlet_param_4(javax.servlet.jsp.tagext.JspTag _jspx_th_portlet_renderURL_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:param
    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_4 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
    _jspx_th_portlet_param_4.setPageContext(_jspx_page_context);
    _jspx_th_portlet_param_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_0);
    _jspx_th_portlet_param_4.setName("calendarId");
    _jspx_th_portlet_param_4.setValue("{calendarId}");
    int _jspx_eval_portlet_param_4 = _jspx_th_portlet_param_4.doStartTag();
    if (_jspx_th_portlet_param_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_4);
      return true;
    }
    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_4);
    return false;
  }

  private boolean _jspx_meth_portlet_param_5(javax.servlet.jsp.tagext.JspTag _jspx_th_portlet_renderURL_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:param
    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_5 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
    _jspx_th_portlet_param_5.setPageContext(_jspx_page_context);
    _jspx_th_portlet_param_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_0);
    _jspx_th_portlet_param_5.setName("date");
    _jspx_th_portlet_param_5.setValue("{date}");
    int _jspx_eval_portlet_param_5 = _jspx_th_portlet_param_5.doStartTag();
    if (_jspx_th_portlet_param_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_5);
      return true;
    }
    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_5);
    return false;
  }

  private boolean _jspx_meth_portlet_param_6(javax.servlet.jsp.tagext.JspTag _jspx_th_portlet_renderURL_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:param
    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_6 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
    _jspx_th_portlet_param_6.setPageContext(_jspx_page_context);
    _jspx_th_portlet_param_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_0);
    _jspx_th_portlet_param_6.setName("endTimeDay");
    _jspx_th_portlet_param_6.setValue("{endTimeDay}");
    int _jspx_eval_portlet_param_6 = _jspx_th_portlet_param_6.doStartTag();
    if (_jspx_th_portlet_param_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_6);
      return true;
    }
    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_6);
    return false;
  }

  private boolean _jspx_meth_portlet_param_7(javax.servlet.jsp.tagext.JspTag _jspx_th_portlet_renderURL_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:param
    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_7 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
    _jspx_th_portlet_param_7.setPageContext(_jspx_page_context);
    _jspx_th_portlet_param_7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_0);
    _jspx_th_portlet_param_7.setName("endTimeHour");
    _jspx_th_portlet_param_7.setValue("{endTimeHour}");
    int _jspx_eval_portlet_param_7 = _jspx_th_portlet_param_7.doStartTag();
    if (_jspx_th_portlet_param_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_7);
      return true;
    }
    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_7);
    return false;
  }

  private boolean _jspx_meth_portlet_param_8(javax.servlet.jsp.tagext.JspTag _jspx_th_portlet_renderURL_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:param
    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_8 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
    _jspx_th_portlet_param_8.setPageContext(_jspx_page_context);
    _jspx_th_portlet_param_8.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_0);
    _jspx_th_portlet_param_8.setName("endTimeMinute");
    _jspx_th_portlet_param_8.setValue("{endTimeMinute}");
    int _jspx_eval_portlet_param_8 = _jspx_th_portlet_param_8.doStartTag();
    if (_jspx_th_portlet_param_8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_8);
      return true;
    }
    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_8);
    return false;
  }

  private boolean _jspx_meth_portlet_param_9(javax.servlet.jsp.tagext.JspTag _jspx_th_portlet_renderURL_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:param
    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_9 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
    _jspx_th_portlet_param_9.setPageContext(_jspx_page_context);
    _jspx_th_portlet_param_9.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_0);
    _jspx_th_portlet_param_9.setName("endTimeMonth");
    _jspx_th_portlet_param_9.setValue("{endTimeMonth}");
    int _jspx_eval_portlet_param_9 = _jspx_th_portlet_param_9.doStartTag();
    if (_jspx_th_portlet_param_9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_9);
      return true;
    }
    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_9);
    return false;
  }

  private boolean _jspx_meth_portlet_param_10(javax.servlet.jsp.tagext.JspTag _jspx_th_portlet_renderURL_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:param
    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_10 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
    _jspx_th_portlet_param_10.setPageContext(_jspx_page_context);
    _jspx_th_portlet_param_10.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_0);
    _jspx_th_portlet_param_10.setName("endTimeYear");
    _jspx_th_portlet_param_10.setValue("{endTimeYear}");
    int _jspx_eval_portlet_param_10 = _jspx_th_portlet_param_10.doStartTag();
    if (_jspx_th_portlet_param_10.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_10);
      return true;
    }
    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_10);
    return false;
  }

  private boolean _jspx_meth_portlet_param_11(javax.servlet.jsp.tagext.JspTag _jspx_th_portlet_renderURL_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:param
    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_11 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
    _jspx_th_portlet_param_11.setPageContext(_jspx_page_context);
    _jspx_th_portlet_param_11.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_0);
    _jspx_th_portlet_param_11.setName("instanceIndex");
    _jspx_th_portlet_param_11.setValue("{instanceIndex}");
    int _jspx_eval_portlet_param_11 = _jspx_th_portlet_param_11.doStartTag();
    if (_jspx_th_portlet_param_11.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_11);
      return true;
    }
    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_11);
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
    _jspx_th_portlet_param_12.setName("startTimeDay");
    _jspx_th_portlet_param_12.setValue("{startTimeDay}");
    int _jspx_eval_portlet_param_12 = _jspx_th_portlet_param_12.doStartTag();
    if (_jspx_th_portlet_param_12.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_12);
      return true;
    }
    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_12);
    return false;
  }

  private boolean _jspx_meth_portlet_param_13(javax.servlet.jsp.tagext.JspTag _jspx_th_portlet_renderURL_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:param
    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_13 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
    _jspx_th_portlet_param_13.setPageContext(_jspx_page_context);
    _jspx_th_portlet_param_13.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_0);
    _jspx_th_portlet_param_13.setName("startTimeHour");
    _jspx_th_portlet_param_13.setValue("{startTimeHour}");
    int _jspx_eval_portlet_param_13 = _jspx_th_portlet_param_13.doStartTag();
    if (_jspx_th_portlet_param_13.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_13);
      return true;
    }
    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_13);
    return false;
  }

  private boolean _jspx_meth_portlet_param_14(javax.servlet.jsp.tagext.JspTag _jspx_th_portlet_renderURL_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:param
    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_14 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
    _jspx_th_portlet_param_14.setPageContext(_jspx_page_context);
    _jspx_th_portlet_param_14.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_0);
    _jspx_th_portlet_param_14.setName("startTimeMinute");
    _jspx_th_portlet_param_14.setValue("{startTimeMinute}");
    int _jspx_eval_portlet_param_14 = _jspx_th_portlet_param_14.doStartTag();
    if (_jspx_th_portlet_param_14.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_14);
      return true;
    }
    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_14);
    return false;
  }

  private boolean _jspx_meth_portlet_param_15(javax.servlet.jsp.tagext.JspTag _jspx_th_portlet_renderURL_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:param
    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_15 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
    _jspx_th_portlet_param_15.setPageContext(_jspx_page_context);
    _jspx_th_portlet_param_15.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_0);
    _jspx_th_portlet_param_15.setName("startTimeMonth");
    _jspx_th_portlet_param_15.setValue("{startTimeMonth}");
    int _jspx_eval_portlet_param_15 = _jspx_th_portlet_param_15.doStartTag();
    if (_jspx_th_portlet_param_15.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_15);
      return true;
    }
    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_15);
    return false;
  }

  private boolean _jspx_meth_portlet_param_16(javax.servlet.jsp.tagext.JspTag _jspx_th_portlet_renderURL_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:param
    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_16 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
    _jspx_th_portlet_param_16.setPageContext(_jspx_page_context);
    _jspx_th_portlet_param_16.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_0);
    _jspx_th_portlet_param_16.setName("startTimeYear");
    _jspx_th_portlet_param_16.setValue("{startTimeYear}");
    int _jspx_eval_portlet_param_16 = _jspx_th_portlet_param_16.doStartTag();
    if (_jspx_th_portlet_param_16.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_16);
      return true;
    }
    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_16);
    return false;
  }

  private boolean _jspx_meth_portlet_param_17(javax.servlet.jsp.tagext.JspTag _jspx_th_portlet_renderURL_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:param
    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_17 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
    _jspx_th_portlet_param_17.setPageContext(_jspx_page_context);
    _jspx_th_portlet_param_17.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_0);
    _jspx_th_portlet_param_17.setName("titleCurrentValue");
    _jspx_th_portlet_param_17.setValue("{titleCurrentValue}");
    int _jspx_eval_portlet_param_17 = _jspx_th_portlet_param_17.doStartTag();
    if (_jspx_th_portlet_param_17.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_17);
      return true;
    }
    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_17);
    return false;
  }

  private boolean _jspx_meth_portlet_param_18(javax.servlet.jsp.tagext.JspTag _jspx_th_portlet_renderURL_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:param
    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_18 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
    _jspx_th_portlet_param_18.setPageContext(_jspx_page_context);
    _jspx_th_portlet_param_18.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_1);
    _jspx_th_portlet_param_18.setName("mvcPath");
    _jspx_th_portlet_param_18.setValue("/view_calendar_booking.jsp");
    int _jspx_eval_portlet_param_18 = _jspx_th_portlet_param_18.doStartTag();
    if (_jspx_th_portlet_param_18.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_18);
      return true;
    }
    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_18);
    return false;
  }

  private boolean _jspx_meth_portlet_param_19(javax.servlet.jsp.tagext.JspTag _jspx_th_portlet_renderURL_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:param
    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_19 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
    _jspx_th_portlet_param_19.setPageContext(_jspx_page_context);
    _jspx_th_portlet_param_19.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_1);
    _jspx_th_portlet_param_19.setName("calendarBookingId");
    _jspx_th_portlet_param_19.setValue("{calendarBookingId}");
    int _jspx_eval_portlet_param_19 = _jspx_th_portlet_param_19.doStartTag();
    if (_jspx_th_portlet_param_19.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_19);
      return true;
    }
    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_19);
    return false;
  }

  private boolean _jspx_meth_portlet_param_20(javax.servlet.jsp.tagext.JspTag _jspx_th_portlet_renderURL_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:param
    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_20 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
    _jspx_th_portlet_param_20.setPageContext(_jspx_page_context);
    _jspx_th_portlet_param_20.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_1);
    _jspx_th_portlet_param_20.setName("instanceIndex");
    _jspx_th_portlet_param_20.setValue("{instanceIndex}");
    int _jspx_eval_portlet_param_20 = _jspx_th_portlet_param_20.doStartTag();
    if (_jspx_th_portlet_param_20.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_20);
      return true;
    }
    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_20);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_14(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_14 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_14.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_14.setParent(null);
    int _jspx_eval_portlet_namespace_14 = _jspx_th_portlet_namespace_14.doStartTag();
    if (_jspx_th_portlet_namespace_14.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_14);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_14);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_15(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_15 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_15.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_15.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    int _jspx_eval_portlet_namespace_15 = _jspx_th_portlet_namespace_15.doStartTag();
    if (_jspx_th_portlet_namespace_15.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_15);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_15);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_16(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_16 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_16.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_16.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    int _jspx_eval_portlet_namespace_16 = _jspx_th_portlet_namespace_16.doStartTag();
    if (_jspx_th_portlet_namespace_16.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_16);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_16);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_17(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_17 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_17.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_17.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    int _jspx_eval_portlet_namespace_17 = _jspx_th_portlet_namespace_17.doStartTag();
    if (_jspx_th_portlet_namespace_17.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_17);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_17);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_4(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_4 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_4.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    _jspx_th_liferay$1ui_message_4.setKey("add-calendar");
    int _jspx_eval_liferay$1ui_message_4 = _jspx_th_liferay$1ui_message_4.doStartTag();
    if (_jspx_th_liferay$1ui_message_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_4);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_4);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_18(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_18 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_18.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_18.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    int _jspx_eval_portlet_namespace_18 = _jspx_th_portlet_namespace_18.doStartTag();
    if (_jspx_th_portlet_namespace_18.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_18);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_18);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_19(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_19 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_19.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_19.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    int _jspx_eval_portlet_namespace_19 = _jspx_th_portlet_namespace_19.doStartTag();
    if (_jspx_th_portlet_namespace_19.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_19);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_19);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_20(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_20 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_20.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_20.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    int _jspx_eval_portlet_namespace_20 = _jspx_th_portlet_namespace_20.doStartTag();
    if (_jspx_th_portlet_namespace_20.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_20);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_20);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_5(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_5 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_5.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    _jspx_th_liferay$1ui_message_5.setKey("add-calendar");
    int _jspx_eval_liferay$1ui_message_5 = _jspx_th_liferay$1ui_message_5.doStartTag();
    if (_jspx_th_liferay$1ui_message_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_5);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_5);
    return false;
  }

  private boolean _jspx_meth_portlet_param_21(javax.servlet.jsp.tagext.JspTag _jspx_th_portlet_renderURL_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:param
    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_21 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
    _jspx_th_portlet_param_21.setPageContext(_jspx_page_context);
    _jspx_th_portlet_param_21.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_2);
    _jspx_th_portlet_param_21.setName("mvcPath");
    _jspx_th_portlet_param_21.setValue("/edit_calendar.jsp");
    int _jspx_eval_portlet_param_21 = _jspx_th_portlet_param_21.doStartTag();
    if (_jspx_th_portlet_param_21.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_21);
      return true;
    }
    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_21);
    return false;
  }

  private boolean _jspx_meth_portlet_param_22(javax.servlet.jsp.tagext.JspTag _jspx_th_portlet_renderURL_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:param
    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_22 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
    _jspx_th_portlet_param_22.setPageContext(_jspx_page_context);
    _jspx_th_portlet_param_22.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_2);
    _jspx_th_portlet_param_22.setName("calendarResourceId");
    _jspx_th_portlet_param_22.setValue("{calendarResourceId}");
    int _jspx_eval_portlet_param_22 = _jspx_th_portlet_param_22.doStartTag();
    if (_jspx_th_portlet_param_22.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_22);
      return true;
    }
    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_22);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_6(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_6 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_6.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    _jspx_th_liferay$1ui_message_6.setKey("manage-calendars");
    int _jspx_eval_liferay$1ui_message_6 = _jspx_th_liferay$1ui_message_6.doStartTag();
    if (_jspx_th_liferay$1ui_message_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_6);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_6);
    return false;
  }

  private boolean _jspx_meth_portlet_param_23(javax.servlet.jsp.tagext.JspTag _jspx_th_portlet_renderURL_3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:param
    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_23 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
    _jspx_th_portlet_param_23.setPageContext(_jspx_page_context);
    _jspx_th_portlet_param_23.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_3);
    _jspx_th_portlet_param_23.setName("mvcPath");
    _jspx_th_portlet_param_23.setValue("/view_calendars.jsp");
    int _jspx_eval_portlet_param_23 = _jspx_th_portlet_param_23.doStartTag();
    if (_jspx_th_portlet_param_23.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_23);
      return true;
    }
    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_23);
    return false;
  }

  private boolean _jspx_meth_portlet_param_25(javax.servlet.jsp.tagext.JspTag _jspx_th_portlet_renderURL_3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:param
    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_25 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
    _jspx_th_portlet_param_25.setPageContext(_jspx_page_context);
    _jspx_th_portlet_param_25.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_3);
    _jspx_th_portlet_param_25.setName("calendarResourceId");
    _jspx_th_portlet_param_25.setValue("{calendarResourceId}");
    int _jspx_eval_portlet_param_25 = _jspx_th_portlet_param_25.doStartTag();
    if (_jspx_th_portlet_param_25.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_25);
      return true;
    }
    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_25);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_7(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_7 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_7.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    _jspx_th_liferay$1ui_message_7.setKey("permissions");
    int _jspx_eval_liferay$1ui_message_7 = _jspx_th_liferay$1ui_message_7.doStartTag();
    if (_jspx_th_liferay$1ui_message_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_7);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_7);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_8(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_8 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_8.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_8.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    _jspx_th_liferay$1ui_message_8.setKey("permissions");
    int _jspx_eval_liferay$1ui_message_8 = _jspx_th_liferay$1ui_message_8.doStartTag();
    if (_jspx_th_liferay$1ui_message_8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_8);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_8);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_21(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_21 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_21.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_21.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    int _jspx_eval_portlet_namespace_21 = _jspx_th_portlet_namespace_21.doStartTag();
    if (_jspx_th_portlet_namespace_21.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_21);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_21);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_9(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_9 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_9.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_9.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    _jspx_th_liferay$1ui_message_9.setKey("add-calendar-booking");
    int _jspx_eval_liferay$1ui_message_9 = _jspx_th_liferay$1ui_message_9.doStartTag();
    if (_jspx_th_liferay$1ui_message_9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_9);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_9);
    return false;
  }

  private boolean _jspx_meth_portlet_param_26(javax.servlet.jsp.tagext.JspTag _jspx_th_portlet_renderURL_4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:param
    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_26 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
    _jspx_th_portlet_param_26.setPageContext(_jspx_page_context);
    _jspx_th_portlet_param_26.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_4);
    _jspx_th_portlet_param_26.setName("mvcPath");
    _jspx_th_portlet_param_26.setValue("/edit_calendar_booking.jsp");
    int _jspx_eval_portlet_param_26 = _jspx_th_portlet_param_26.doStartTag();
    if (_jspx_th_portlet_param_26.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_26);
      return true;
    }
    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_26);
    return false;
  }

  private boolean _jspx_meth_portlet_param_28(javax.servlet.jsp.tagext.JspTag _jspx_th_portlet_renderURL_4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:param
    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_28 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
    _jspx_th_portlet_param_28.setPageContext(_jspx_page_context);
    _jspx_th_portlet_param_28.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_4);
    _jspx_th_portlet_param_28.setName("calendarId");
    _jspx_th_portlet_param_28.setValue("{calendarId}");
    int _jspx_eval_portlet_param_28 = _jspx_th_portlet_param_28.doStartTag();
    if (_jspx_th_portlet_param_28.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_28);
      return true;
    }
    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_28);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_22(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_22 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_22.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_22.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    int _jspx_eval_portlet_namespace_22 = _jspx_th_portlet_namespace_22.doStartTag();
    if (_jspx_th_portlet_namespace_22.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_22);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_22);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_10(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_10 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_10.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_10.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    _jspx_th_liferay$1ui_message_10.setKey("new-calendar-booking");
    int _jspx_eval_liferay$1ui_message_10 = _jspx_th_liferay$1ui_message_10.doStartTag();
    if (_jspx_th_liferay$1ui_message_10.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_10);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_10);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_11(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_11 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_11.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_11.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    _jspx_th_liferay$1ui_message_11.setKey("hide-calendar");
    int _jspx_eval_liferay$1ui_message_11 = _jspx_th_liferay$1ui_message_11.doStartTag();
    if (_jspx_th_liferay$1ui_message_11.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_11);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_11);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_23(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_23 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_23.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_23.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    int _jspx_eval_portlet_namespace_23 = _jspx_th_portlet_namespace_23.doStartTag();
    if (_jspx_th_portlet_namespace_23.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_23);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_23);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_12(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_12 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_12.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_12.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    _jspx_th_liferay$1ui_message_12.setKey("calendar-settings");
    int _jspx_eval_liferay$1ui_message_12 = _jspx_th_liferay$1ui_message_12.doStartTag();
    if (_jspx_th_liferay$1ui_message_12.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_12);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_12);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_24(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_24 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_24.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_24.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    int _jspx_eval_portlet_namespace_24 = _jspx_th_portlet_namespace_24.doStartTag();
    if (_jspx_th_portlet_namespace_24.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_24);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_24);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_25(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_25 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_25.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_25.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    int _jspx_eval_portlet_namespace_25 = _jspx_th_portlet_namespace_25.doStartTag();
    if (_jspx_th_portlet_namespace_25.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_25);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_25);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_13(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_13 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_13.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_13.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    _jspx_th_liferay$1ui_message_13.setKey("calendar-settings");
    int _jspx_eval_liferay$1ui_message_13 = _jspx_th_liferay$1ui_message_13.doStartTag();
    if (_jspx_th_liferay$1ui_message_13.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_13);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_13);
    return false;
  }

  private boolean _jspx_meth_portlet_param_29(javax.servlet.jsp.tagext.JspTag _jspx_th_portlet_renderURL_5, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:param
    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_29 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
    _jspx_th_portlet_param_29.setPageContext(_jspx_page_context);
    _jspx_th_portlet_param_29.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_5);
    _jspx_th_portlet_param_29.setName("mvcPath");
    _jspx_th_portlet_param_29.setValue("/edit_calendar.jsp");
    int _jspx_eval_portlet_param_29 = _jspx_th_portlet_param_29.doStartTag();
    if (_jspx_th_portlet_param_29.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_29);
      return true;
    }
    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_29);
    return false;
  }

  private boolean _jspx_meth_portlet_param_30(javax.servlet.jsp.tagext.JspTag _jspx_th_portlet_renderURL_5, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:param
    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_30 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
    _jspx_th_portlet_param_30.setPageContext(_jspx_page_context);
    _jspx_th_portlet_param_30.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_5);
    _jspx_th_portlet_param_30.setName("calendarId");
    _jspx_th_portlet_param_30.setValue("{calendarId}");
    int _jspx_eval_portlet_param_30 = _jspx_th_portlet_param_30.doStartTag();
    if (_jspx_th_portlet_param_30.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_30);
      return true;
    }
    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_30);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_14(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_14 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_14.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_14.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    _jspx_th_liferay$1ui_message_14.setKey("permissions");
    int _jspx_eval_liferay$1ui_message_14 = _jspx_th_liferay$1ui_message_14.doStartTag();
    if (_jspx_th_liferay$1ui_message_14.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_14);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_14);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_26(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_26 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_26.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_26.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    int _jspx_eval_portlet_namespace_26 = _jspx_th_portlet_namespace_26.doStartTag();
    if (_jspx_th_portlet_namespace_26.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_26);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_26);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_15(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_15 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_15.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_15.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    _jspx_th_liferay$1ui_message_15.setKey("permissions");
    int _jspx_eval_liferay$1ui_message_15 = _jspx_th_liferay$1ui_message_15.doStartTag();
    if (_jspx_th_liferay$1ui_message_15.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_15);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_15);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_16(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_16 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_16.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_16.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    _jspx_th_liferay$1ui_message_16.setKey("delete");
    int _jspx_eval_liferay$1ui_message_16 = _jspx_th_liferay$1ui_message_16.doStartTag();
    if (_jspx_th_liferay$1ui_message_16.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_16);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_16);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_17(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_17 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_17.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_17.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    _jspx_th_liferay$1ui_message_17.setKey("are-you-sure-you-want-to-delete-this");
    int _jspx_eval_liferay$1ui_message_17 = _jspx_th_liferay$1ui_message_17.doStartTag();
    if (_jspx_th_liferay$1ui_message_17.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_17);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_17);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_27(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_27 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_27.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_27.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    int _jspx_eval_portlet_namespace_27 = _jspx_th_portlet_namespace_27.doStartTag();
    if (_jspx_th_portlet_namespace_27.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_27);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_27);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_28(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_28 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_28.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_28.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    int _jspx_eval_portlet_namespace_28 = _jspx_th_portlet_namespace_28.doStartTag();
    if (_jspx_th_portlet_namespace_28.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_28);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_28);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_29(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_29 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_29.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_29.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    int _jspx_eval_portlet_namespace_29 = _jspx_th_portlet_namespace_29.doStartTag();
    if (_jspx_th_portlet_namespace_29.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_29);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_29);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_30(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_30 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_30.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_30.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    int _jspx_eval_portlet_namespace_30 = _jspx_th_portlet_namespace_30.doStartTag();
    if (_jspx_th_portlet_namespace_30.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_30);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_30);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_18(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_18 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_18.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_18.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    _jspx_th_liferay$1ui_message_18.setKey("the-calendar-was-deleted-successfully");
    int _jspx_eval_liferay$1ui_message_18 = _jspx_th_liferay$1ui_message_18.doStartTag();
    if (_jspx_th_liferay$1ui_message_18.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_18);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_18);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_19(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_9, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_19 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_19.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_19.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_9);
    _jspx_th_liferay$1ui_message_19.setKey("rss");
    int _jspx_eval_liferay$1ui_message_19 = _jspx_th_liferay$1ui_message_19.doStartTag();
    if (_jspx_th_liferay$1ui_message_19.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_19);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_19);
    return false;
  }

  private boolean _jspx_meth_portlet_param_31(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1portlet_resourceURL_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:param
    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_31 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
    _jspx_th_portlet_param_31.setPageContext(_jspx_page_context);
    _jspx_th_portlet_param_31.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_resourceURL_0);
    _jspx_th_portlet_param_31.setName("calendarId");
    _jspx_th_portlet_param_31.setValue("{calendarId}");
    int _jspx_eval_portlet_param_31 = _jspx_th_portlet_param_31.doStartTag();
    if (_jspx_th_portlet_param_31.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_31);
      return true;
    }
    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_31);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_31(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_31 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_31.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_31.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    int _jspx_eval_portlet_namespace_31 = _jspx_th_portlet_namespace_31.doStartTag();
    if (_jspx_th_portlet_namespace_31.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_31);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_31);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_32(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_32 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_32.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_32.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    int _jspx_eval_portlet_namespace_32 = _jspx_th_portlet_namespace_32.doStartTag();
    if (_jspx_th_portlet_namespace_32.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_32);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_32);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_33(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_33 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_33.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_33.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    int _jspx_eval_portlet_namespace_33 = _jspx_th_portlet_namespace_33.doStartTag();
    if (_jspx_th_portlet_namespace_33.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_33);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_33);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_34(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_34 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_34.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_34.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    int _jspx_eval_portlet_namespace_34 = _jspx_th_portlet_namespace_34.doStartTag();
    if (_jspx_th_portlet_namespace_34.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_34);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_34);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_35(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_35 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_35.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_35.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    int _jspx_eval_portlet_namespace_35 = _jspx_th_portlet_namespace_35.doStartTag();
    if (_jspx_th_portlet_namespace_35.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_35);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_35);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_36(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_36 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_36.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_36.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    int _jspx_eval_portlet_namespace_36 = _jspx_th_portlet_namespace_36.doStartTag();
    if (_jspx_th_portlet_namespace_36.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_36);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_36);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_37(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_37 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_37.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_37.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    int _jspx_eval_portlet_namespace_37 = _jspx_th_portlet_namespace_37.doStartTag();
    if (_jspx_th_portlet_namespace_37.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_37);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_37);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_38(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_38 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_38.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_38.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    int _jspx_eval_portlet_namespace_38 = _jspx_th_portlet_namespace_38.doStartTag();
    if (_jspx_th_portlet_namespace_38.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_38);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_38);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_39(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_39 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_39.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_39.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    int _jspx_eval_portlet_namespace_39 = _jspx_th_portlet_namespace_39.doStartTag();
    if (_jspx_th_portlet_namespace_39.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_39);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_39);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_40(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_40 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_40.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_40.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    int _jspx_eval_portlet_namespace_40 = _jspx_th_portlet_namespace_40.doStartTag();
    if (_jspx_th_portlet_namespace_40.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_40);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_40);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_41(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_41 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_41.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_41.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    int _jspx_eval_portlet_namespace_41 = _jspx_th_portlet_namespace_41.doStartTag();
    if (_jspx_th_portlet_namespace_41.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_41);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_41);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_42(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_10, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_42 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_42.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_42.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_10);
    int _jspx_eval_portlet_namespace_42 = _jspx_th_portlet_namespace_42.doStartTag();
    if (_jspx_th_portlet_namespace_42.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_42);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_42);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_43(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_10, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_43 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_43.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_43.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_10);
    int _jspx_eval_portlet_namespace_43 = _jspx_th_portlet_namespace_43.doStartTag();
    if (_jspx_th_portlet_namespace_43.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_43);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_43);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_44(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_10, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_44 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_44.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_44.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_10);
    int _jspx_eval_portlet_namespace_44 = _jspx_th_portlet_namespace_44.doStartTag();
    if (_jspx_th_portlet_namespace_44.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_44);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_44);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_45(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_45 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_45.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_45.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    int _jspx_eval_portlet_namespace_45 = _jspx_th_portlet_namespace_45.doStartTag();
    if (_jspx_th_portlet_namespace_45.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_45);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_45);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_46(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_46 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_46.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_46.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    int _jspx_eval_portlet_namespace_46 = _jspx_th_portlet_namespace_46.doStartTag();
    if (_jspx_th_portlet_namespace_46.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_46);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_46);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_47(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_47 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_47.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_47.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    int _jspx_eval_portlet_namespace_47 = _jspx_th_portlet_namespace_47.doStartTag();
    if (_jspx_th_portlet_namespace_47.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_47);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_47);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_48(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_48 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_48.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_48.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    int _jspx_eval_portlet_namespace_48 = _jspx_th_portlet_namespace_48.doStartTag();
    if (_jspx_th_portlet_namespace_48.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_48);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_48);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_49(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_49 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_49.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_49.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    int _jspx_eval_portlet_namespace_49 = _jspx_th_portlet_namespace_49.doStartTag();
    if (_jspx_th_portlet_namespace_49.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_49);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_49);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_50(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_50 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_50.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_50.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    int _jspx_eval_portlet_namespace_50 = _jspx_th_portlet_namespace_50.doStartTag();
    if (_jspx_th_portlet_namespace_50.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_50);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_50);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_51(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_51 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_51.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_51.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    int _jspx_eval_portlet_namespace_51 = _jspx_th_portlet_namespace_51.doStartTag();
    if (_jspx_th_portlet_namespace_51.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_51);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_51);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_52(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_52 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_52.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_52.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    int _jspx_eval_portlet_namespace_52 = _jspx_th_portlet_namespace_52.doStartTag();
    if (_jspx_th_portlet_namespace_52.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_52);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_52);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_53(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_53 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_53.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_53.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    int _jspx_eval_portlet_namespace_53 = _jspx_th_portlet_namespace_53.doStartTag();
    if (_jspx_th_portlet_namespace_53.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_53);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_53);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_54(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_54 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_54.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_54.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    int _jspx_eval_portlet_namespace_54 = _jspx_th_portlet_namespace_54.doStartTag();
    if (_jspx_th_portlet_namespace_54.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_54);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_54);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_55(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_55 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_55.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_55.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    int _jspx_eval_portlet_namespace_55 = _jspx_th_portlet_namespace_55.doStartTag();
    if (_jspx_th_portlet_namespace_55.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_55);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_55);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_56(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_56 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_56.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_56.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    int _jspx_eval_portlet_namespace_56 = _jspx_th_portlet_namespace_56.doStartTag();
    if (_jspx_th_portlet_namespace_56.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_56);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_56);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_57(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_57 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_57.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_57.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    int _jspx_eval_portlet_namespace_57 = _jspx_th_portlet_namespace_57.doStartTag();
    if (_jspx_th_portlet_namespace_57.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_57);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_57);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_58(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_58 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_58.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_58.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    int _jspx_eval_portlet_namespace_58 = _jspx_th_portlet_namespace_58.doStartTag();
    if (_jspx_th_portlet_namespace_58.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_58);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_58);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_59(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_59 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_59.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_59.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    int _jspx_eval_portlet_namespace_59 = _jspx_th_portlet_namespace_59.doStartTag();
    if (_jspx_th_portlet_namespace_59.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_59);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_59);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_60(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_60 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_60.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_60.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    int _jspx_eval_portlet_namespace_60 = _jspx_th_portlet_namespace_60.doStartTag();
    if (_jspx_th_portlet_namespace_60.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_60);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_60);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_61(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_61 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_61.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_61.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    int _jspx_eval_portlet_namespace_61 = _jspx_th_portlet_namespace_61.doStartTag();
    if (_jspx_th_portlet_namespace_61.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_61);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_61);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_62(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_62 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_62.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_62.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    int _jspx_eval_portlet_namespace_62 = _jspx_th_portlet_namespace_62.doStartTag();
    if (_jspx_th_portlet_namespace_62.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_62);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_62);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_63(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_63 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_63.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_63.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    int _jspx_eval_portlet_namespace_63 = _jspx_th_portlet_namespace_63.doStartTag();
    if (_jspx_th_portlet_namespace_63.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_63);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_63);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_64(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_64 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_64.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_64.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    int _jspx_eval_portlet_namespace_64 = _jspx_th_portlet_namespace_64.doStartTag();
    if (_jspx_th_portlet_namespace_64.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_64);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_64);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_65(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_65 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_65.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_65.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    int _jspx_eval_portlet_namespace_65 = _jspx_th_portlet_namespace_65.doStartTag();
    if (_jspx_th_portlet_namespace_65.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_65);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_65);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_66(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_66 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_66.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_66.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    int _jspx_eval_portlet_namespace_66 = _jspx_th_portlet_namespace_66.doStartTag();
    if (_jspx_th_portlet_namespace_66.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_66);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_66);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_67(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_67 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_67.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_67.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    int _jspx_eval_portlet_namespace_67 = _jspx_th_portlet_namespace_67.doStartTag();
    if (_jspx_th_portlet_namespace_67.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_67);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_67);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_68(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_68 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_68.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_68.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    int _jspx_eval_portlet_namespace_68 = _jspx_th_portlet_namespace_68.doStartTag();
    if (_jspx_th_portlet_namespace_68.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_68);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_68);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_69(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_69 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_69.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_69.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    int _jspx_eval_portlet_namespace_69 = _jspx_th_portlet_namespace_69.doStartTag();
    if (_jspx_th_portlet_namespace_69.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_69);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_69);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_70(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_70 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_70.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_70.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    int _jspx_eval_portlet_namespace_70 = _jspx_th_portlet_namespace_70.doStartTag();
    if (_jspx_th_portlet_namespace_70.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_70);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_70);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_71(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_71 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_71.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_71.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    int _jspx_eval_portlet_namespace_71 = _jspx_th_portlet_namespace_71.doStartTag();
    if (_jspx_th_portlet_namespace_71.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_71);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_71);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_72(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_72 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_72.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_72.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    int _jspx_eval_portlet_namespace_72 = _jspx_th_portlet_namespace_72.doStartTag();
    if (_jspx_th_portlet_namespace_72.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_72);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_72);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_73(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_73 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_73.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_73.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_2);
    int _jspx_eval_portlet_namespace_73 = _jspx_th_portlet_namespace_73.doStartTag();
    if (_jspx_th_portlet_namespace_73.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_73);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_73);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_74(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_11, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_74 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_74.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_74.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_11);
    int _jspx_eval_portlet_namespace_74 = _jspx_th_portlet_namespace_74.doStartTag();
    if (_jspx_th_portlet_namespace_74.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_74);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_74);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_75(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_12, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_75 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_75.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_75.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_12);
    int _jspx_eval_portlet_namespace_75 = _jspx_th_portlet_namespace_75.doStartTag();
    if (_jspx_th_portlet_namespace_75.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_75);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_75);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_76(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_13, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_76 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_76.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_76.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_13);
    int _jspx_eval_portlet_namespace_76 = _jspx_th_portlet_namespace_76.doStartTag();
    if (_jspx_th_portlet_namespace_76.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_76);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_76);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_77(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_77 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_77.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_77.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_2);
    int _jspx_eval_portlet_namespace_77 = _jspx_th_portlet_namespace_77.doStartTag();
    if (_jspx_th_portlet_namespace_77.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_77);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_77);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_78(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_78 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_78.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_78.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_2);
    int _jspx_eval_portlet_namespace_78 = _jspx_th_portlet_namespace_78.doStartTag();
    if (_jspx_th_portlet_namespace_78.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_78);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_78);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_79(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_14, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_79 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_79.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_79.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_14);
    int _jspx_eval_portlet_namespace_79 = _jspx_th_portlet_namespace_79.doStartTag();
    if (_jspx_th_portlet_namespace_79.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_79);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_79);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_80(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_14, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_80 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_80.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_80.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_14);
    int _jspx_eval_portlet_namespace_80 = _jspx_th_portlet_namespace_80.doStartTag();
    if (_jspx_th_portlet_namespace_80.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_80);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_80);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_81(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_14, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_81 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_81.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_81.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_14);
    int _jspx_eval_portlet_namespace_81 = _jspx_th_portlet_namespace_81.doStartTag();
    if (_jspx_th_portlet_namespace_81.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_81);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_81);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_82(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_14, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_82 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_82.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_82.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_14);
    int _jspx_eval_portlet_namespace_82 = _jspx_th_portlet_namespace_82.doStartTag();
    if (_jspx_th_portlet_namespace_82.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_82);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_82);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_83(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_14, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_83 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_83.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_83.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_14);
    int _jspx_eval_portlet_namespace_83 = _jspx_th_portlet_namespace_83.doStartTag();
    if (_jspx_th_portlet_namespace_83.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_83);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_83);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_84(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_15, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_84 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_84.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_84.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_15);
    int _jspx_eval_portlet_namespace_84 = _jspx_th_portlet_namespace_84.doStartTag();
    if (_jspx_th_portlet_namespace_84.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_84);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_84);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_85(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_15, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_85 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_85.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_85.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_15);
    int _jspx_eval_portlet_namespace_85 = _jspx_th_portlet_namespace_85.doStartTag();
    if (_jspx_th_portlet_namespace_85.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_85);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_85);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_86(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_16, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_86 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_86.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_86.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_16);
    int _jspx_eval_portlet_namespace_86 = _jspx_th_portlet_namespace_86.doStartTag();
    if (_jspx_th_portlet_namespace_86.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_86);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_86);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_87(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_16, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_87 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_87.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_87.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_16);
    int _jspx_eval_portlet_namespace_87 = _jspx_th_portlet_namespace_87.doStartTag();
    if (_jspx_th_portlet_namespace_87.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_87);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_87);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_88(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_16, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_88 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_88.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_88.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_16);
    int _jspx_eval_portlet_namespace_88 = _jspx_th_portlet_namespace_88.doStartTag();
    if (_jspx_th_portlet_namespace_88.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_88);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_88);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_89(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_16, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_89 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_89.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_89.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_16);
    int _jspx_eval_portlet_namespace_89 = _jspx_th_portlet_namespace_89.doStartTag();
    if (_jspx_th_portlet_namespace_89.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_89);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_89);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_90(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_16, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_90 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_90.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_90.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_16);
    int _jspx_eval_portlet_namespace_90 = _jspx_th_portlet_namespace_90.doStartTag();
    if (_jspx_th_portlet_namespace_90.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_90);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_90);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_91(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_16, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_91 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_91.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_91.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_16);
    int _jspx_eval_portlet_namespace_91 = _jspx_th_portlet_namespace_91.doStartTag();
    if (_jspx_th_portlet_namespace_91.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_91);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_91);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_92(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_17, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_92 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_92.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_92.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_17);
    int _jspx_eval_portlet_namespace_92 = _jspx_th_portlet_namespace_92.doStartTag();
    if (_jspx_th_portlet_namespace_92.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_92);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_92);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_93(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_17, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_93 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_93.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_93.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_17);
    int _jspx_eval_portlet_namespace_93 = _jspx_th_portlet_namespace_93.doStartTag();
    if (_jspx_th_portlet_namespace_93.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_93);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_93);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_94(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_17, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_94 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_94.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_94.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_17);
    int _jspx_eval_portlet_namespace_94 = _jspx_th_portlet_namespace_94.doStartTag();
    if (_jspx_th_portlet_namespace_94.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_94);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_94);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_95(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_17, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_95 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_95.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_95.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_17);
    int _jspx_eval_portlet_namespace_95 = _jspx_th_portlet_namespace_95.doStartTag();
    if (_jspx_th_portlet_namespace_95.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_95);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_95);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_96(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_17, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_96 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_96.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_96.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_17);
    int _jspx_eval_portlet_namespace_96 = _jspx_th_portlet_namespace_96.doStartTag();
    if (_jspx_th_portlet_namespace_96.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_96);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_96);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_97(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_17, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_97 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_97.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_97.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_17);
    int _jspx_eval_portlet_namespace_97 = _jspx_th_portlet_namespace_97.doStartTag();
    if (_jspx_th_portlet_namespace_97.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_97);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_97);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_98(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_17, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_98 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_98.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_98.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_17);
    int _jspx_eval_portlet_namespace_98 = _jspx_th_portlet_namespace_98.doStartTag();
    if (_jspx_th_portlet_namespace_98.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_98);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_98);
    return false;
  }

  private boolean _jspx_meth_aui_script_3(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:script
    com.liferay.taglib.aui.ScriptTag _jspx_th_aui_script_3 = (com.liferay.taglib.aui.ScriptTag) _jspx_tagPool_aui_script_use.get(com.liferay.taglib.aui.ScriptTag.class);
    _jspx_th_aui_script_3.setPageContext(_jspx_page_context);
    _jspx_th_aui_script_3.setParent(null);
    _jspx_th_aui_script_3.setUse("aui-base,aui-datatype,liferay-calendar-session-listener");
    int _jspx_eval_aui_script_3 = _jspx_th_aui_script_3.doStartTag();
    if (_jspx_eval_aui_script_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_aui_script_3 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_aui_script_3.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_aui_script_3.doInitBody();
      }
      do {
        out.write("\n\twindow.");
        if (_jspx_meth_portlet_namespace_99((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_3, _jspx_page_context))
          return true;
        out.write("refreshSchedulerEventTooltipTitle = function(schedulerEvent) {\n\t\tschedulerEvent.get('node').attr('title', A.Lang.String.unescapeHTML(schedulerEvent.get('content')));\n\t};\n\n\t");
        if (_jspx_meth_portlet_namespace_100((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_3, _jspx_page_context))
          return true;
        out.write("scheduler.after(\n\t\t['scheduler-events:load'],\n\t\tfunction(event) {\n\t\t\tevent.currentTarget.eachEvent(");
        if (_jspx_meth_portlet_namespace_101((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_3, _jspx_page_context))
          return true;
        out.write("refreshSchedulerEventTooltipTitle);\n\t\t}\n\t);\n\n\t");
        if (_jspx_meth_portlet_namespace_102((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_3, _jspx_page_context))
          return true;
        out.write("scheduler.load();\n\n\tvar calendarContainer = Liferay.component('");
        if (_jspx_meth_portlet_namespace_103((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_3, _jspx_page_context))
          return true;
        out.write("calendarContainer');\n\n\tnew Liferay.CalendarSessionListener(\n\t\t{\n\t\t\tcalendars: calendarContainer.get('availableCalendars'),\n\t\t\tscheduler: ");
        if (_jspx_meth_portlet_namespace_104((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_3, _jspx_page_context))
          return true;
        out.write("scheduler\n\t\t}\n\t);\n");
        int evalDoAfterBody = _jspx_th_aui_script_3.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_aui_script_3 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
        out = _jspx_page_context.popBody();
    }
    if (_jspx_th_aui_script_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_script_use.reuse(_jspx_th_aui_script_3);
      return true;
    }
    _jspx_tagPool_aui_script_use.reuse(_jspx_th_aui_script_3);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_99(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_99 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_99.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_99.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_3);
    int _jspx_eval_portlet_namespace_99 = _jspx_th_portlet_namespace_99.doStartTag();
    if (_jspx_th_portlet_namespace_99.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_99);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_99);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_100(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_100 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_100.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_100.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_3);
    int _jspx_eval_portlet_namespace_100 = _jspx_th_portlet_namespace_100.doStartTag();
    if (_jspx_th_portlet_namespace_100.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_100);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_100);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_101(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_101 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_101.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_101.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_3);
    int _jspx_eval_portlet_namespace_101 = _jspx_th_portlet_namespace_101.doStartTag();
    if (_jspx_th_portlet_namespace_101.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_101);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_101);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_102(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_102 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_102.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_102.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_3);
    int _jspx_eval_portlet_namespace_102 = _jspx_th_portlet_namespace_102.doStartTag();
    if (_jspx_th_portlet_namespace_102.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_102);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_102);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_103(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_103 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_103.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_103.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_3);
    int _jspx_eval_portlet_namespace_103 = _jspx_th_portlet_namespace_103.doStartTag();
    if (_jspx_th_portlet_namespace_103.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_103);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_103);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_104(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_104 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_104.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_104.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_3);
    int _jspx_eval_portlet_namespace_104 = _jspx_th_portlet_namespace_104.doStartTag();
    if (_jspx_th_portlet_namespace_104.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_104);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_104);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_105(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_105 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_105.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_105.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_4);
    int _jspx_eval_portlet_namespace_105 = _jspx_th_portlet_namespace_105.doStartTag();
    if (_jspx_th_portlet_namespace_105.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_105);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_105);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_106(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_106 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_106.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_106.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_4);
    int _jspx_eval_portlet_namespace_106 = _jspx_th_portlet_namespace_106.doStartTag();
    if (_jspx_th_portlet_namespace_106.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_106);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_106);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_107(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_107 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_107.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_107.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_4);
    int _jspx_eval_portlet_namespace_107 = _jspx_th_portlet_namespace_107.doStartTag();
    if (_jspx_th_portlet_namespace_107.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_107);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_107);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_108(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_108 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_108.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_108.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_4);
    int _jspx_eval_portlet_namespace_108 = _jspx_th_portlet_namespace_108.doStartTag();
    if (_jspx_th_portlet_namespace_108.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_108);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_108);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_109(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_109 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_109.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_109.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_4);
    int _jspx_eval_portlet_namespace_109 = _jspx_th_portlet_namespace_109.doStartTag();
    if (_jspx_th_portlet_namespace_109.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_109);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_109);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_110(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_110 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_110.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_110.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_4);
    int _jspx_eval_portlet_namespace_110 = _jspx_th_portlet_namespace_110.doStartTag();
    if (_jspx_th_portlet_namespace_110.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_110);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_110);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_111(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_111 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_111.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_111.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_4);
    int _jspx_eval_portlet_namespace_111 = _jspx_th_portlet_namespace_111.doStartTag();
    if (_jspx_th_portlet_namespace_111.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_111);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_111);
    return false;
  }
}
