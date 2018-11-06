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

public final class configuration_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(4);
    _jspx_dependants.add("/init.jsp");
    _jspx_dependants.add("/configuration/user_settings.jspf");
    _jspx_dependants.add("/configuration/display_settings.jspf");
    _jspx_dependants.add("/configuration/rss.jspf");
  }

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_section;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_fieldset_id_cssClass;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1rss_rss$1settings_feedType_enabled_displayStyle_delta_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_option_value_label_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_script;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_button_type_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_option_value_selected_label_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_option_selected_label_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1frontend_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_script_use;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1frontend_edit$1form_name_method_action;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_fieldset;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_select_name_label;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_input_value_type_name_label_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_namespace_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_input_value_type_name_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_input_value_type_name_label_disabled_cssClass_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_fieldset_label_collapsible;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1frontend_edit$1form$1body;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_tabs_type_refresh_param_names;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1frontend_edit$1form$1footer;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1theme_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1frontend_fieldset$1group;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1portlet_actionURL_var_portletConfiguration_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_select_value_name_label;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_liferay$1ui_section = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_fieldset_id_cssClass = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1rss_rss$1settings_feedType_enabled_displayStyle_delta_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_option_value_label_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_script = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_button_type_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_option_value_selected_label_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_option_selected_label_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1frontend_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_script_use = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1frontend_edit$1form_name_method_action = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_fieldset = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_select_name_label = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_input_value_type_name_label_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_namespace_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_input_value_type_name_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_input_value_type_name_label_disabled_cssClass_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_fieldset_label_collapsible = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1frontend_edit$1form$1body = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_tabs_type_refresh_param_names = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1frontend_edit$1form$1footer = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1theme_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1frontend_fieldset$1group = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1portlet_actionURL_var_portletConfiguration_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_select_value_name_label = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_liferay$1ui_section.release();
    _jspx_tagPool_aui_fieldset_id_cssClass.release();
    _jspx_tagPool_liferay$1rss_rss$1settings_feedType_enabled_displayStyle_delta_nobody.release();
    _jspx_tagPool_aui_option_value_label_nobody.release();
    _jspx_tagPool_aui_script.release();
    _jspx_tagPool_aui_button_type_nobody.release();
    _jspx_tagPool_aui_option_value_selected_label_nobody.release();
    _jspx_tagPool_aui_option_selected_label_nobody.release();
    _jspx_tagPool_liferay$1frontend_defineObjects_nobody.release();
    _jspx_tagPool_aui_script_use.release();
    _jspx_tagPool_liferay$1frontend_edit$1form_name_method_action.release();
    _jspx_tagPool_aui_fieldset.release();
    _jspx_tagPool_aui_select_name_label.release();
    _jspx_tagPool_aui_input_value_type_name_label_nobody.release();
    _jspx_tagPool_portlet_namespace_nobody.release();
    _jspx_tagPool_aui_input_value_type_name_nobody.release();
    _jspx_tagPool_aui_input_value_type_name_label_disabled_cssClass_nobody.release();
    _jspx_tagPool_aui_fieldset_label_collapsible.release();
    _jspx_tagPool_portlet_defineObjects_nobody.release();
    _jspx_tagPool_liferay$1frontend_edit$1form$1body.release();
    _jspx_tagPool_liferay$1ui_tabs_type_refresh_param_names.release();
    _jspx_tagPool_liferay$1frontend_edit$1form$1footer.release();
    _jspx_tagPool_liferay$1theme_defineObjects_nobody.release();
    _jspx_tagPool_liferay$1frontend_fieldset$1group.release();
    _jspx_tagPool_liferay$1portlet_actionURL_var_portletConfiguration_nobody.release();
    _jspx_tagPool_aui_select_value_name_label.release();
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
      //  liferay-frontend:edit-form
      com.liferay.frontend.taglib.servlet.taglib.EditFormTag _jspx_th_liferay$1frontend_edit$1form_0 = (com.liferay.frontend.taglib.servlet.taglib.EditFormTag) _jspx_tagPool_liferay$1frontend_edit$1form_name_method_action.get(com.liferay.frontend.taglib.servlet.taglib.EditFormTag.class);
      _jspx_th_liferay$1frontend_edit$1form_0.setPageContext(_jspx_page_context);
      _jspx_th_liferay$1frontend_edit$1form_0.setParent(null);
      _jspx_th_liferay$1frontend_edit$1form_0.setAction( configurationActionURL );
      _jspx_th_liferay$1frontend_edit$1form_0.setMethod("post");
      _jspx_th_liferay$1frontend_edit$1form_0.setName("fm");
      int _jspx_eval_liferay$1frontend_edit$1form_0 = _jspx_th_liferay$1frontend_edit$1form_0.doStartTag();
      if (_jspx_eval_liferay$1frontend_edit$1form_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        out.write('\n');
        out.write('	');
        //  aui:input
        com.liferay.taglib.aui.InputTag _jspx_th_aui_input_0 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
        _jspx_th_aui_input_0.setPageContext(_jspx_page_context);
        _jspx_th_aui_input_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_edit$1form_0);
        _jspx_th_aui_input_0.setName( Constants.CMD );
        _jspx_th_aui_input_0.setType("hidden");
        _jspx_th_aui_input_0.setValue( Constants.UPDATE );
        int _jspx_eval_aui_input_0 = _jspx_th_aui_input_0.doStartTag();
        if (_jspx_th_aui_input_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_0);
          return;
        }
        _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_0);
        out.write("\n\n\t");
        //  liferay-frontend:edit-form-body
        com.liferay.frontend.taglib.servlet.taglib.EditFormBodyTag _jspx_th_liferay$1frontend_edit$1form$1body_0 = (com.liferay.frontend.taglib.servlet.taglib.EditFormBodyTag) _jspx_tagPool_liferay$1frontend_edit$1form$1body.get(com.liferay.frontend.taglib.servlet.taglib.EditFormBodyTag.class);
        _jspx_th_liferay$1frontend_edit$1form$1body_0.setPageContext(_jspx_page_context);
        _jspx_th_liferay$1frontend_edit$1form$1body_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_edit$1form_0);
        int _jspx_eval_liferay$1frontend_edit$1form$1body_0 = _jspx_th_liferay$1frontend_edit$1form$1body_0.doStartTag();
        if (_jspx_eval_liferay$1frontend_edit$1form$1body_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          out.write("\n\t\t");
          //  liferay-ui:tabs
          com.liferay.taglib.ui.TabsTag _jspx_th_liferay$1ui_tabs_0 = (com.liferay.taglib.ui.TabsTag) _jspx_tagPool_liferay$1ui_tabs_type_refresh_param_names.get(com.liferay.taglib.ui.TabsTag.class);
          _jspx_th_liferay$1ui_tabs_0.setPageContext(_jspx_page_context);
          _jspx_th_liferay$1ui_tabs_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_edit$1form$1body_0);
          _jspx_th_liferay$1ui_tabs_0.setNames( "user-settings,display-settings,rss" );
          _jspx_th_liferay$1ui_tabs_0.setParam("tabs2");
          _jspx_th_liferay$1ui_tabs_0.setRefresh( false );
          _jspx_th_liferay$1ui_tabs_0.setType("tabs nav-tabs-default");
          int _jspx_eval_liferay$1ui_tabs_0 = _jspx_th_liferay$1ui_tabs_0.doStartTag();
          if (_jspx_eval_liferay$1ui_tabs_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t\t");
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
              out.write("\n\t\t\t\t");
              //  liferay-frontend:fieldset-group
              com.liferay.frontend.taglib.servlet.taglib.FieldsetGroupTag _jspx_th_liferay$1frontend_fieldset$1group_0 = (com.liferay.frontend.taglib.servlet.taglib.FieldsetGroupTag) _jspx_tagPool_liferay$1frontend_fieldset$1group.get(com.liferay.frontend.taglib.servlet.taglib.FieldsetGroupTag.class);
              _jspx_th_liferay$1frontend_fieldset$1group_0.setPageContext(_jspx_page_context);
              _jspx_th_liferay$1frontend_fieldset$1group_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_section_0);
              int _jspx_eval_liferay$1frontend_fieldset$1group_0 = _jspx_th_liferay$1frontend_fieldset$1group_0.doStartTag();
              if (_jspx_eval_liferay$1frontend_fieldset$1group_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\t\t\t\t\t");
                out.write('\n');
                out.write('\n');
                //  aui:fieldset
                com.liferay.taglib.aui.FieldsetTag _jspx_th_aui_fieldset_0 = (com.liferay.taglib.aui.FieldsetTag) _jspx_tagPool_aui_fieldset.get(com.liferay.taglib.aui.FieldsetTag.class);
                _jspx_th_aui_fieldset_0.setPageContext(_jspx_page_context);
                _jspx_th_aui_fieldset_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_fieldset$1group_0);
                int _jspx_eval_aui_fieldset_0 = _jspx_th_aui_fieldset_0.doStartTag();
                if (_jspx_eval_aui_fieldset_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  out.write('\n');
                  out.write('	');
                  //  aui:select
                  com.liferay.taglib.aui.SelectTag _jspx_th_aui_select_0 = (com.liferay.taglib.aui.SelectTag) _jspx_tagPool_aui_select_name_label.get(com.liferay.taglib.aui.SelectTag.class);
                  _jspx_th_aui_select_0.setPageContext(_jspx_page_context);
                  _jspx_th_aui_select_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_0);
                  _jspx_th_aui_select_0.setLabel("time-format");
                  _jspx_th_aui_select_0.setName("timeFormat");
                  int _jspx_eval_aui_select_0 = _jspx_th_aui_select_0.doStartTag();
                  if (_jspx_eval_aui_select_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    if (_jspx_eval_aui_select_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                      out = _jspx_page_context.pushBody();
                      _jspx_th_aui_select_0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                      _jspx_th_aui_select_0.doInitBody();
                    }
                    do {
                      out.write("\n\t\t");
                      //  aui:option
                      com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_0 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_value_selected_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
                      _jspx_th_aui_option_0.setPageContext(_jspx_page_context);
                      _jspx_th_aui_option_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_0);
                      _jspx_th_aui_option_0.setLabel(new String("am-pm"));
                      _jspx_th_aui_option_0.setSelected( timeFormat.equals("am-pm") );
                      _jspx_th_aui_option_0.setValue(new String("am-pm"));
                      int _jspx_eval_aui_option_0 = _jspx_th_aui_option_0.doStartTag();
                      if (_jspx_th_aui_option_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_aui_option_value_selected_label_nobody.reuse(_jspx_th_aui_option_0);
                        return;
                      }
                      _jspx_tagPool_aui_option_value_selected_label_nobody.reuse(_jspx_th_aui_option_0);
                      out.write("\n\t\t");
                      //  aui:option
                      com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_1 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_value_selected_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
                      _jspx_th_aui_option_1.setPageContext(_jspx_page_context);
                      _jspx_th_aui_option_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_0);
                      _jspx_th_aui_option_1.setLabel(new String("24-hour"));
                      _jspx_th_aui_option_1.setSelected( timeFormat.equals("24-hour") );
                      _jspx_th_aui_option_1.setValue(new String("24-hour"));
                      int _jspx_eval_aui_option_1 = _jspx_th_aui_option_1.doStartTag();
                      if (_jspx_th_aui_option_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_aui_option_value_selected_label_nobody.reuse(_jspx_th_aui_option_1);
                        return;
                      }
                      _jspx_tagPool_aui_option_value_selected_label_nobody.reuse(_jspx_th_aui_option_1);
                      out.write("\n\t\t");
                      //  aui:option
                      com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_2 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_value_selected_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
                      _jspx_th_aui_option_2.setPageContext(_jspx_page_context);
                      _jspx_th_aui_option_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_0);
                      _jspx_th_aui_option_2.setLabel(new String("locale"));
                      _jspx_th_aui_option_2.setSelected( timeFormat.equals("locale") );
                      _jspx_th_aui_option_2.setValue(new String("locale"));
                      int _jspx_eval_aui_option_2 = _jspx_th_aui_option_2.doStartTag();
                      if (_jspx_th_aui_option_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_aui_option_value_selected_label_nobody.reuse(_jspx_th_aui_option_2);
                        return;
                      }
                      _jspx_tagPool_aui_option_value_selected_label_nobody.reuse(_jspx_th_aui_option_2);
                      out.write('\n');
                      out.write('	');
                      int evalDoAfterBody = _jspx_th_aui_select_0.doAfterBody();
                      if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                        break;
                    } while (true);
                    if (_jspx_eval_aui_select_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                      out = _jspx_page_context.popBody();
                  }
                  if (_jspx_th_aui_select_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_aui_select_name_label.reuse(_jspx_th_aui_select_0);
                    return;
                  }
                  _jspx_tagPool_aui_select_name_label.reuse(_jspx_th_aui_select_0);
                  out.write("\n\n\t");
                  //  aui:select
                  com.liferay.taglib.aui.SelectTag _jspx_th_aui_select_1 = (com.liferay.taglib.aui.SelectTag) _jspx_tagPool_aui_select_value_name_label.get(com.liferay.taglib.aui.SelectTag.class);
                  _jspx_th_aui_select_1.setPageContext(_jspx_page_context);
                  _jspx_th_aui_select_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_0);
                  _jspx_th_aui_select_1.setLabel("default-duration");
                  _jspx_th_aui_select_1.setName("defaultDuration");
                  _jspx_th_aui_select_1.setValue( defaultDuration );
                  int _jspx_eval_aui_select_1 = _jspx_th_aui_select_1.doStartTag();
                  if (_jspx_eval_aui_select_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    if (_jspx_eval_aui_select_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                      out = _jspx_page_context.pushBody();
                      _jspx_th_aui_select_1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                      _jspx_th_aui_select_1.doInitBody();
                    }
                    do {
                      out.write("\n\t\t");
                      //  aui:option
                      com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_3 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_value_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
                      _jspx_th_aui_option_3.setPageContext(_jspx_page_context);
                      _jspx_th_aui_option_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_1);
                      _jspx_th_aui_option_3.setLabel( LanguageUtil.format(request, "x-minutes", "15", false) );
                      _jspx_th_aui_option_3.setValue(new String("15"));
                      int _jspx_eval_aui_option_3 = _jspx_th_aui_option_3.doStartTag();
                      if (_jspx_th_aui_option_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_aui_option_value_label_nobody.reuse(_jspx_th_aui_option_3);
                        return;
                      }
                      _jspx_tagPool_aui_option_value_label_nobody.reuse(_jspx_th_aui_option_3);
                      out.write("\n\t\t");
                      //  aui:option
                      com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_4 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_value_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
                      _jspx_th_aui_option_4.setPageContext(_jspx_page_context);
                      _jspx_th_aui_option_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_1);
                      _jspx_th_aui_option_4.setLabel( LanguageUtil.format(request, "x-minutes", "30", false) );
                      _jspx_th_aui_option_4.setValue(new String("30"));
                      int _jspx_eval_aui_option_4 = _jspx_th_aui_option_4.doStartTag();
                      if (_jspx_th_aui_option_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_aui_option_value_label_nobody.reuse(_jspx_th_aui_option_4);
                        return;
                      }
                      _jspx_tagPool_aui_option_value_label_nobody.reuse(_jspx_th_aui_option_4);
                      out.write("\n\t\t");
                      //  aui:option
                      com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_5 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_value_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
                      _jspx_th_aui_option_5.setPageContext(_jspx_page_context);
                      _jspx_th_aui_option_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_1);
                      _jspx_th_aui_option_5.setLabel( LanguageUtil.format(request, "x-minutes", "60", false) );
                      _jspx_th_aui_option_5.setValue(new String("60"));
                      int _jspx_eval_aui_option_5 = _jspx_th_aui_option_5.doStartTag();
                      if (_jspx_th_aui_option_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_aui_option_value_label_nobody.reuse(_jspx_th_aui_option_5);
                        return;
                      }
                      _jspx_tagPool_aui_option_value_label_nobody.reuse(_jspx_th_aui_option_5);
                      out.write("\n\t\t");
                      //  aui:option
                      com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_6 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_value_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
                      _jspx_th_aui_option_6.setPageContext(_jspx_page_context);
                      _jspx_th_aui_option_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_1);
                      _jspx_th_aui_option_6.setLabel( LanguageUtil.format(request, "x-minutes", "120", false) );
                      _jspx_th_aui_option_6.setValue(new String("120"));
                      int _jspx_eval_aui_option_6 = _jspx_th_aui_option_6.doStartTag();
                      if (_jspx_th_aui_option_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_aui_option_value_label_nobody.reuse(_jspx_th_aui_option_6);
                        return;
                      }
                      _jspx_tagPool_aui_option_value_label_nobody.reuse(_jspx_th_aui_option_6);
                      out.write('\n');
                      out.write('	');
                      int evalDoAfterBody = _jspx_th_aui_select_1.doAfterBody();
                      if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                        break;
                    } while (true);
                    if (_jspx_eval_aui_select_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                      out = _jspx_page_context.popBody();
                  }
                  if (_jspx_th_aui_select_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_aui_select_value_name_label.reuse(_jspx_th_aui_select_1);
                    return;
                  }
                  _jspx_tagPool_aui_select_value_name_label.reuse(_jspx_th_aui_select_1);
                  out.write("\n\n\t");
                  //  aui:select
                  com.liferay.taglib.aui.SelectTag _jspx_th_aui_select_2 = (com.liferay.taglib.aui.SelectTag) _jspx_tagPool_aui_select_value_name_label.get(com.liferay.taglib.aui.SelectTag.class);
                  _jspx_th_aui_select_2.setPageContext(_jspx_page_context);
                  _jspx_th_aui_select_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_0);
                  _jspx_th_aui_select_2.setLabel("default-view");
                  _jspx_th_aui_select_2.setName("defaultView");
                  _jspx_th_aui_select_2.setValue( defaultView );
                  int _jspx_eval_aui_select_2 = _jspx_th_aui_select_2.doStartTag();
                  if (_jspx_eval_aui_select_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    if (_jspx_eval_aui_select_2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                      out = _jspx_page_context.pushBody();
                      _jspx_th_aui_select_2.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                      _jspx_th_aui_select_2.doInitBody();
                    }
                    do {
                      out.write("\n\t\t");
                      if (_jspx_meth_aui_option_7((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_select_2, _jspx_page_context))
                        return;
                      out.write("\n\t\t");
                      if (_jspx_meth_aui_option_8((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_select_2, _jspx_page_context))
                        return;
                      out.write("\n\t\t");
                      if (_jspx_meth_aui_option_9((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_select_2, _jspx_page_context))
                        return;
                      out.write("\n\t\t");
                      if (_jspx_meth_aui_option_10((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_select_2, _jspx_page_context))
                        return;
                      out.write('\n');
                      out.write('	');
                      int evalDoAfterBody = _jspx_th_aui_select_2.doAfterBody();
                      if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                        break;
                    } while (true);
                    if (_jspx_eval_aui_select_2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                      out = _jspx_page_context.popBody();
                  }
                  if (_jspx_th_aui_select_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_aui_select_value_name_label.reuse(_jspx_th_aui_select_2);
                    return;
                  }
                  _jspx_tagPool_aui_select_value_name_label.reuse(_jspx_th_aui_select_2);
                  out.write("\n\n\t");
                  //  aui:select
                  com.liferay.taglib.aui.SelectTag _jspx_th_aui_select_3 = (com.liferay.taglib.aui.SelectTag) _jspx_tagPool_aui_select_value_name_label.get(com.liferay.taglib.aui.SelectTag.class);
                  _jspx_th_aui_select_3.setPageContext(_jspx_page_context);
                  _jspx_th_aui_select_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_0);
                  _jspx_th_aui_select_3.setLabel("week-starts-on");
                  _jspx_th_aui_select_3.setName("weekStartsOn");
                  _jspx_th_aui_select_3.setValue( weekStartsOn );
                  int _jspx_eval_aui_select_3 = _jspx_th_aui_select_3.doStartTag();
                  if (_jspx_eval_aui_select_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    if (_jspx_eval_aui_select_3 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                      out = _jspx_page_context.pushBody();
                      _jspx_th_aui_select_3.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                      _jspx_th_aui_select_3.doInitBody();
                    }
                    do {
                      out.write("\n\t\t");
                      if (_jspx_meth_aui_option_11((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_select_3, _jspx_page_context))
                        return;
                      out.write("\n\t\t");
                      if (_jspx_meth_aui_option_12((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_select_3, _jspx_page_context))
                        return;
                      out.write("\n\t\t");
                      if (_jspx_meth_aui_option_13((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_select_3, _jspx_page_context))
                        return;
                      out.write('\n');
                      out.write('	');
                      int evalDoAfterBody = _jspx_th_aui_select_3.doAfterBody();
                      if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                        break;
                    } while (true);
                    if (_jspx_eval_aui_select_3 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                      out = _jspx_page_context.popBody();
                  }
                  if (_jspx_th_aui_select_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_aui_select_value_name_label.reuse(_jspx_th_aui_select_3);
                    return;
                  }
                  _jspx_tagPool_aui_select_value_name_label.reuse(_jspx_th_aui_select_3);
                  out.write("\n\n\t");
                  //  aui:input
                  com.liferay.taglib.aui.InputTag _jspx_th_aui_input_1 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_label_disabled_cssClass_nobody.get(com.liferay.taglib.aui.InputTag.class);
                  _jspx_th_aui_input_1.setPageContext(_jspx_page_context);
                  _jspx_th_aui_input_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_0);
                  _jspx_th_aui_input_1.setCssClass("calendar-portlet-time-zone-field");
                  _jspx_th_aui_input_1.setDisabled( usePortalTimeZone );
                  _jspx_th_aui_input_1.setLabel("time-zone");
                  _jspx_th_aui_input_1.setName("timeZoneId");
                  _jspx_th_aui_input_1.setType("timeZone");
                  _jspx_th_aui_input_1.setValue( timeZoneId );
                  int _jspx_eval_aui_input_1 = _jspx_th_aui_input_1.doStartTag();
                  if (_jspx_th_aui_input_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_aui_input_value_type_name_label_disabled_cssClass_nobody.reuse(_jspx_th_aui_input_1);
                    return;
                  }
                  _jspx_tagPool_aui_input_value_type_name_label_disabled_cssClass_nobody.reuse(_jspx_th_aui_input_1);
                  out.write("\n\n\t");
                  //  aui:input
                  com.liferay.taglib.aui.InputTag _jspx_th_aui_input_2 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_label_nobody.get(com.liferay.taglib.aui.InputTag.class);
                  _jspx_th_aui_input_2.setPageContext(_jspx_page_context);
                  _jspx_th_aui_input_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_0);
                  _jspx_th_aui_input_2.setLabel("use-global-timezone");
                  _jspx_th_aui_input_2.setName("usePortalTimeZone");
                  _jspx_th_aui_input_2.setType("checkbox");
                  _jspx_th_aui_input_2.setValue( usePortalTimeZone );
                  int _jspx_eval_aui_input_2 = _jspx_th_aui_input_2.doStartTag();
                  if (_jspx_th_aui_input_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_aui_input_value_type_name_label_nobody.reuse(_jspx_th_aui_input_2);
                    return;
                  }
                  _jspx_tagPool_aui_input_value_type_name_label_nobody.reuse(_jspx_th_aui_input_2);
                  out.write('\n');
                }
                if (_jspx_th_aui_fieldset_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_aui_fieldset.reuse(_jspx_th_aui_fieldset_0);
                  return;
                }
                _jspx_tagPool_aui_fieldset.reuse(_jspx_th_aui_fieldset_0);
                out.write('\n');
                out.write('\n');
                if (_jspx_meth_aui_script_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1frontend_fieldset$1group_0, _jspx_page_context))
                  return;
                out.write("\n\t\t\t\t");
              }
              if (_jspx_th_liferay$1frontend_fieldset$1group_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_liferay$1frontend_fieldset$1group.reuse(_jspx_th_liferay$1frontend_fieldset$1group_0);
                return;
              }
              _jspx_tagPool_liferay$1frontend_fieldset$1group.reuse(_jspx_th_liferay$1frontend_fieldset$1group_0);
              out.write("\n\t\t\t");
            }
            if (_jspx_th_liferay$1ui_section_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_liferay$1ui_section.reuse(_jspx_th_liferay$1ui_section_0);
              return;
            }
            _jspx_tagPool_liferay$1ui_section.reuse(_jspx_th_liferay$1ui_section_0);
            out.write("\n\n\t\t\t");
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
              out.write("\n\t\t\t\t");
              //  liferay-frontend:fieldset-group
              com.liferay.frontend.taglib.servlet.taglib.FieldsetGroupTag _jspx_th_liferay$1frontend_fieldset$1group_1 = (com.liferay.frontend.taglib.servlet.taglib.FieldsetGroupTag) _jspx_tagPool_liferay$1frontend_fieldset$1group.get(com.liferay.frontend.taglib.servlet.taglib.FieldsetGroupTag.class);
              _jspx_th_liferay$1frontend_fieldset$1group_1.setPageContext(_jspx_page_context);
              _jspx_th_liferay$1frontend_fieldset$1group_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_section_1);
              int _jspx_eval_liferay$1frontend_fieldset$1group_1 = _jspx_th_liferay$1frontend_fieldset$1group_1.doStartTag();
              if (_jspx_eval_liferay$1frontend_fieldset$1group_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\t\t\t\t\t");
                out.write('\n');
                out.write('\n');
                //  aui:fieldset
                com.liferay.taglib.aui.FieldsetTag _jspx_th_aui_fieldset_1 = (com.liferay.taglib.aui.FieldsetTag) _jspx_tagPool_aui_fieldset.get(com.liferay.taglib.aui.FieldsetTag.class);
                _jspx_th_aui_fieldset_1.setPageContext(_jspx_page_context);
                _jspx_th_aui_fieldset_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_fieldset$1group_1);
                int _jspx_eval_aui_fieldset_1 = _jspx_th_aui_fieldset_1.doStartTag();
                if (_jspx_eval_aui_fieldset_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  out.write('\n');
                  out.write('	');
                  //  aui:input
                  com.liferay.taglib.aui.InputTag _jspx_th_aui_input_3 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_label_nobody.get(com.liferay.taglib.aui.InputTag.class);
                  _jspx_th_aui_input_3.setPageContext(_jspx_page_context);
                  _jspx_th_aui_input_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_1);
                  _jspx_th_aui_input_3.setLabel("display-scheduler-only");
                  _jspx_th_aui_input_3.setName("displaySchedulerOnly");
                  _jspx_th_aui_input_3.setType("checkbox");
                  _jspx_th_aui_input_3.setValue( displaySchedulerOnly );
                  int _jspx_eval_aui_input_3 = _jspx_th_aui_input_3.doStartTag();
                  if (_jspx_th_aui_input_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_aui_input_value_type_name_label_nobody.reuse(_jspx_th_aui_input_3);
                    return;
                  }
                  _jspx_tagPool_aui_input_value_type_name_label_nobody.reuse(_jspx_th_aui_input_3);
                  out.write("\n\n\t");
                  //  aui:input
                  com.liferay.taglib.aui.InputTag _jspx_th_aui_input_4 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_label_nobody.get(com.liferay.taglib.aui.InputTag.class);
                  _jspx_th_aui_input_4.setPageContext(_jspx_page_context);
                  _jspx_th_aui_input_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_1);
                  _jspx_th_aui_input_4.setLabel("display-user-events");
                  _jspx_th_aui_input_4.setName("showUserEvents");
                  _jspx_th_aui_input_4.setType("checkbox");
                  _jspx_th_aui_input_4.setValue( showUserEvents );
                  int _jspx_eval_aui_input_4 = _jspx_th_aui_input_4.doStartTag();
                  if (_jspx_th_aui_input_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_aui_input_value_type_name_label_nobody.reuse(_jspx_th_aui_input_4);
                    return;
                  }
                  _jspx_tagPool_aui_input_value_type_name_label_nobody.reuse(_jspx_th_aui_input_4);
                  out.write("\n\n\t");
                  //  aui:input
                  com.liferay.taglib.aui.InputTag _jspx_th_aui_input_5 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_label_nobody.get(com.liferay.taglib.aui.InputTag.class);
                  _jspx_th_aui_input_5.setPageContext(_jspx_page_context);
                  _jspx_th_aui_input_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_1);
                  _jspx_th_aui_input_5.setLabel("display-schedulers-header");
                  _jspx_th_aui_input_5.setName("displaySchedulerHeader");
                  _jspx_th_aui_input_5.setType("checkbox");
                  _jspx_th_aui_input_5.setValue( displaySchedulerHeader );
                  int _jspx_eval_aui_input_5 = _jspx_th_aui_input_5.doStartTag();
                  if (_jspx_th_aui_input_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_aui_input_value_type_name_label_nobody.reuse(_jspx_th_aui_input_5);
                    return;
                  }
                  _jspx_tagPool_aui_input_value_type_name_label_nobody.reuse(_jspx_th_aui_input_5);
                  out.write("\n\n\t");
                  //  aui:fieldset
                  com.liferay.taglib.aui.FieldsetTag _jspx_th_aui_fieldset_2 = (com.liferay.taglib.aui.FieldsetTag) _jspx_tagPool_aui_fieldset_label_collapsible.get(com.liferay.taglib.aui.FieldsetTag.class);
                  _jspx_th_aui_fieldset_2.setPageContext(_jspx_page_context);
                  _jspx_th_aui_fieldset_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_1);
                  _jspx_th_aui_fieldset_2.setCollapsible( true );
                  _jspx_th_aui_fieldset_2.setLabel("enabled-views");
                  int _jspx_eval_aui_fieldset_2 = _jspx_th_aui_fieldset_2.doStartTag();
                  if (_jspx_eval_aui_fieldset_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    out.write("\n\t\t");
                    //  aui:input
                    com.liferay.taglib.aui.InputTag _jspx_th_aui_input_6 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_label_nobody.get(com.liferay.taglib.aui.InputTag.class);
                    _jspx_th_aui_input_6.setPageContext(_jspx_page_context);
                    _jspx_th_aui_input_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_2);
                    _jspx_th_aui_input_6.setLabel("day");
                    _jspx_th_aui_input_6.setName("showDayView");
                    _jspx_th_aui_input_6.setType("checkbox");
                    _jspx_th_aui_input_6.setValue( showDayView );
                    int _jspx_eval_aui_input_6 = _jspx_th_aui_input_6.doStartTag();
                    if (_jspx_th_aui_input_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_aui_input_value_type_name_label_nobody.reuse(_jspx_th_aui_input_6);
                      return;
                    }
                    _jspx_tagPool_aui_input_value_type_name_label_nobody.reuse(_jspx_th_aui_input_6);
                    out.write("\n\n\t\t");
                    //  aui:input
                    com.liferay.taglib.aui.InputTag _jspx_th_aui_input_7 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_label_nobody.get(com.liferay.taglib.aui.InputTag.class);
                    _jspx_th_aui_input_7.setPageContext(_jspx_page_context);
                    _jspx_th_aui_input_7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_2);
                    _jspx_th_aui_input_7.setLabel("week");
                    _jspx_th_aui_input_7.setName("showWeekView");
                    _jspx_th_aui_input_7.setType("checkbox");
                    _jspx_th_aui_input_7.setValue( showWeekView );
                    int _jspx_eval_aui_input_7 = _jspx_th_aui_input_7.doStartTag();
                    if (_jspx_th_aui_input_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_aui_input_value_type_name_label_nobody.reuse(_jspx_th_aui_input_7);
                      return;
                    }
                    _jspx_tagPool_aui_input_value_type_name_label_nobody.reuse(_jspx_th_aui_input_7);
                    out.write("\n\n\t\t");
                    //  aui:input
                    com.liferay.taglib.aui.InputTag _jspx_th_aui_input_8 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_label_nobody.get(com.liferay.taglib.aui.InputTag.class);
                    _jspx_th_aui_input_8.setPageContext(_jspx_page_context);
                    _jspx_th_aui_input_8.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_2);
                    _jspx_th_aui_input_8.setLabel("month");
                    _jspx_th_aui_input_8.setName("showMonthView");
                    _jspx_th_aui_input_8.setType("checkbox");
                    _jspx_th_aui_input_8.setValue( showMonthView );
                    int _jspx_eval_aui_input_8 = _jspx_th_aui_input_8.doStartTag();
                    if (_jspx_th_aui_input_8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_aui_input_value_type_name_label_nobody.reuse(_jspx_th_aui_input_8);
                      return;
                    }
                    _jspx_tagPool_aui_input_value_type_name_label_nobody.reuse(_jspx_th_aui_input_8);
                    out.write("\n\n\t\t");
                    //  aui:input
                    com.liferay.taglib.aui.InputTag _jspx_th_aui_input_9 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_label_nobody.get(com.liferay.taglib.aui.InputTag.class);
                    _jspx_th_aui_input_9.setPageContext(_jspx_page_context);
                    _jspx_th_aui_input_9.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_2);
                    _jspx_th_aui_input_9.setLabel("agenda");
                    _jspx_th_aui_input_9.setName("showAgendaView");
                    _jspx_th_aui_input_9.setType("checkbox");
                    _jspx_th_aui_input_9.setValue( showAgendaView );
                    int _jspx_eval_aui_input_9 = _jspx_th_aui_input_9.doStartTag();
                    if (_jspx_th_aui_input_9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_aui_input_value_type_name_label_nobody.reuse(_jspx_th_aui_input_9);
                      return;
                    }
                    _jspx_tagPool_aui_input_value_type_name_label_nobody.reuse(_jspx_th_aui_input_9);
                    out.write('\n');
                    out.write('	');
                  }
                  if (_jspx_th_aui_fieldset_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_aui_fieldset_label_collapsible.reuse(_jspx_th_aui_fieldset_2);
                    return;
                  }
                  _jspx_tagPool_aui_fieldset_label_collapsible.reuse(_jspx_th_aui_fieldset_2);
                  out.write("\n\n\t");
                  //  aui:fieldset
                  com.liferay.taglib.aui.FieldsetTag _jspx_th_aui_fieldset_3 = (com.liferay.taglib.aui.FieldsetTag) _jspx_tagPool_aui_fieldset.get(com.liferay.taglib.aui.FieldsetTag.class);
                  _jspx_th_aui_fieldset_3.setPageContext(_jspx_page_context);
                  _jspx_th_aui_fieldset_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_1);
                  int _jspx_eval_aui_fieldset_3 = _jspx_th_aui_fieldset_3.doStartTag();
                  if (_jspx_eval_aui_fieldset_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    out.write("\n\t\t");
                    //  aui:select
                    com.liferay.taglib.aui.SelectTag _jspx_th_aui_select_4 = (com.liferay.taglib.aui.SelectTag) _jspx_tagPool_aui_select_value_name_label.get(com.liferay.taglib.aui.SelectTag.class);
                    _jspx_th_aui_select_4.setPageContext(_jspx_page_context);
                    _jspx_th_aui_select_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_3);
                    _jspx_th_aui_select_4.setLabel("maximum-days-to-display");
                    _jspx_th_aui_select_4.setName("maxDaysDisplayed");
                    _jspx_th_aui_select_4.setValue( maxDaysDisplayed );
                    int _jspx_eval_aui_select_4 = _jspx_th_aui_select_4.doStartTag();
                    if (_jspx_eval_aui_select_4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      if (_jspx_eval_aui_select_4 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                        out = _jspx_page_context.pushBody();
                        _jspx_th_aui_select_4.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                        _jspx_th_aui_select_4.doInitBody();
                      }
                      do {
                        out.write("\n\t\t\t");
                        //  aui:option
                        com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_14 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_value_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
                        _jspx_th_aui_option_14.setPageContext(_jspx_page_context);
                        _jspx_th_aui_option_14.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_4);
                        _jspx_th_aui_option_14.setLabel(new String("1"));
                        _jspx_th_aui_option_14.setValue( 1 );
                        int _jspx_eval_aui_option_14 = _jspx_th_aui_option_14.doStartTag();
                        if (_jspx_th_aui_option_14.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                          _jspx_tagPool_aui_option_value_label_nobody.reuse(_jspx_th_aui_option_14);
                          return;
                        }
                        _jspx_tagPool_aui_option_value_label_nobody.reuse(_jspx_th_aui_option_14);
                        out.write("\n\t\t\t");
                        //  aui:option
                        com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_15 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_value_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
                        _jspx_th_aui_option_15.setPageContext(_jspx_page_context);
                        _jspx_th_aui_option_15.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_4);
                        _jspx_th_aui_option_15.setLabel(new String("2"));
                        _jspx_th_aui_option_15.setValue( 2 );
                        int _jspx_eval_aui_option_15 = _jspx_th_aui_option_15.doStartTag();
                        if (_jspx_th_aui_option_15.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                          _jspx_tagPool_aui_option_value_label_nobody.reuse(_jspx_th_aui_option_15);
                          return;
                        }
                        _jspx_tagPool_aui_option_value_label_nobody.reuse(_jspx_th_aui_option_15);
                        out.write("\n\t\t\t");
                        //  aui:option
                        com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_16 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_value_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
                        _jspx_th_aui_option_16.setPageContext(_jspx_page_context);
                        _jspx_th_aui_option_16.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_4);
                        _jspx_th_aui_option_16.setLabel(new String("3"));
                        _jspx_th_aui_option_16.setValue( 3 );
                        int _jspx_eval_aui_option_16 = _jspx_th_aui_option_16.doStartTag();
                        if (_jspx_th_aui_option_16.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                          _jspx_tagPool_aui_option_value_label_nobody.reuse(_jspx_th_aui_option_16);
                          return;
                        }
                        _jspx_tagPool_aui_option_value_label_nobody.reuse(_jspx_th_aui_option_16);
                        out.write("\n\t\t\t");
                        //  aui:option
                        com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_17 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_value_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
                        _jspx_th_aui_option_17.setPageContext(_jspx_page_context);
                        _jspx_th_aui_option_17.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_4);
                        _jspx_th_aui_option_17.setLabel(new String("4"));
                        _jspx_th_aui_option_17.setValue( 4 );
                        int _jspx_eval_aui_option_17 = _jspx_th_aui_option_17.doStartTag();
                        if (_jspx_th_aui_option_17.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                          _jspx_tagPool_aui_option_value_label_nobody.reuse(_jspx_th_aui_option_17);
                          return;
                        }
                        _jspx_tagPool_aui_option_value_label_nobody.reuse(_jspx_th_aui_option_17);
                        out.write("\n\t\t\t");
                        //  aui:option
                        com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_18 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_value_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
                        _jspx_th_aui_option_18.setPageContext(_jspx_page_context);
                        _jspx_th_aui_option_18.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_4);
                        _jspx_th_aui_option_18.setLabel(new String("5"));
                        _jspx_th_aui_option_18.setValue( 5 );
                        int _jspx_eval_aui_option_18 = _jspx_th_aui_option_18.doStartTag();
                        if (_jspx_th_aui_option_18.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                          _jspx_tagPool_aui_option_value_label_nobody.reuse(_jspx_th_aui_option_18);
                          return;
                        }
                        _jspx_tagPool_aui_option_value_label_nobody.reuse(_jspx_th_aui_option_18);
                        out.write("\n\t\t");
                        int evalDoAfterBody = _jspx_th_aui_select_4.doAfterBody();
                        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                          break;
                      } while (true);
                      if (_jspx_eval_aui_select_4 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                        out = _jspx_page_context.popBody();
                    }
                    if (_jspx_th_aui_select_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_aui_select_value_name_label.reuse(_jspx_th_aui_select_4);
                      return;
                    }
                    _jspx_tagPool_aui_select_value_name_label.reuse(_jspx_th_aui_select_4);
                    out.write("\n\n\t\t");
                    //  aui:select
                    com.liferay.taglib.aui.SelectTag _jspx_th_aui_select_5 = (com.liferay.taglib.aui.SelectTag) _jspx_tagPool_aui_select_name_label.get(com.liferay.taglib.aui.SelectTag.class);
                    _jspx_th_aui_select_5.setPageContext(_jspx_page_context);
                    _jspx_th_aui_select_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_3);
                    _jspx_th_aui_select_5.setLabel("maximum-events-to-display");
                    _jspx_th_aui_select_5.setName("eventsPerPage");
                    int _jspx_eval_aui_select_5 = _jspx_th_aui_select_5.doStartTag();
                    if (_jspx_eval_aui_select_5 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      if (_jspx_eval_aui_select_5 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                        out = _jspx_page_context.pushBody();
                        _jspx_th_aui_select_5.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                        _jspx_th_aui_select_5.doInitBody();
                      }
                      do {
                        out.write("\n\n\t\t\t");

			int[] deltaValues = GetterUtil.getIntegerValues(PrefsPropsUtil.getStringArray(PropsKeys.SEARCH_CONTAINER_PAGE_DELTA_VALUES, StringPool.COMMA));

			for (int pageDeltaValue : deltaValues) {
			
                        out.write("\n\n\t\t\t\t");
                        //  aui:option
                        com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_19 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_selected_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
                        _jspx_th_aui_option_19.setPageContext(_jspx_page_context);
                        _jspx_th_aui_option_19.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_5);
                        _jspx_th_aui_option_19.setLabel( pageDeltaValue );
                        _jspx_th_aui_option_19.setSelected( eventsPerPage == pageDeltaValue );
                        int _jspx_eval_aui_option_19 = _jspx_th_aui_option_19.doStartTag();
                        if (_jspx_th_aui_option_19.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                          _jspx_tagPool_aui_option_selected_label_nobody.reuse(_jspx_th_aui_option_19);
                          return;
                        }
                        _jspx_tagPool_aui_option_selected_label_nobody.reuse(_jspx_th_aui_option_19);
                        out.write("\n\n\t\t\t");

			}
			
                        out.write("\n\n\t\t");
                        int evalDoAfterBody = _jspx_th_aui_select_5.doAfterBody();
                        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                          break;
                      } while (true);
                      if (_jspx_eval_aui_select_5 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                        out = _jspx_page_context.popBody();
                    }
                    if (_jspx_th_aui_select_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_aui_select_name_label.reuse(_jspx_th_aui_select_5);
                      return;
                    }
                    _jspx_tagPool_aui_select_name_label.reuse(_jspx_th_aui_select_5);
                    out.write('\n');
                    out.write('	');
                  }
                  if (_jspx_th_aui_fieldset_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_aui_fieldset.reuse(_jspx_th_aui_fieldset_3);
                    return;
                  }
                  _jspx_tagPool_aui_fieldset.reuse(_jspx_th_aui_fieldset_3);
                  out.write('\n');
                }
                if (_jspx_th_aui_fieldset_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_aui_fieldset.reuse(_jspx_th_aui_fieldset_1);
                  return;
                }
                _jspx_tagPool_aui_fieldset.reuse(_jspx_th_aui_fieldset_1);
                out.write("\n\t\t\t\t");
              }
              if (_jspx_th_liferay$1frontend_fieldset$1group_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_liferay$1frontend_fieldset$1group.reuse(_jspx_th_liferay$1frontend_fieldset$1group_1);
                return;
              }
              _jspx_tagPool_liferay$1frontend_fieldset$1group.reuse(_jspx_th_liferay$1frontend_fieldset$1group_1);
              out.write("\n\t\t\t");
            }
            if (_jspx_th_liferay$1ui_section_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_liferay$1ui_section.reuse(_jspx_th_liferay$1ui_section_1);
              return;
            }
            _jspx_tagPool_liferay$1ui_section.reuse(_jspx_th_liferay$1ui_section_1);
            out.write("\n\n\t\t\t");
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
              out.write("\n\t\t\t\t");
              //  liferay-frontend:fieldset-group
              com.liferay.frontend.taglib.servlet.taglib.FieldsetGroupTag _jspx_th_liferay$1frontend_fieldset$1group_2 = (com.liferay.frontend.taglib.servlet.taglib.FieldsetGroupTag) _jspx_tagPool_liferay$1frontend_fieldset$1group.get(com.liferay.frontend.taglib.servlet.taglib.FieldsetGroupTag.class);
              _jspx_th_liferay$1frontend_fieldset$1group_2.setPageContext(_jspx_page_context);
              _jspx_th_liferay$1frontend_fieldset$1group_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_section_2);
              int _jspx_eval_liferay$1frontend_fieldset$1group_2 = _jspx_th_liferay$1frontend_fieldset$1group_2.doStartTag();
              if (_jspx_eval_liferay$1frontend_fieldset$1group_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\t\t\t\t\t");
                out.write('\n');
                out.write('\n');
                //  liferay-rss:rss-settings
                com.liferay.rss.taglib.servlet.taglib.RSSSettingsTag _jspx_th_liferay$1rss_rss$1settings_0 = (com.liferay.rss.taglib.servlet.taglib.RSSSettingsTag) _jspx_tagPool_liferay$1rss_rss$1settings_feedType_enabled_displayStyle_delta_nobody.get(com.liferay.rss.taglib.servlet.taglib.RSSSettingsTag.class);
                _jspx_th_liferay$1rss_rss$1settings_0.setPageContext(_jspx_page_context);
                _jspx_th_liferay$1rss_rss$1settings_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_fieldset$1group_2);
                _jspx_th_liferay$1rss_rss$1settings_0.setDelta( rssDelta );
                _jspx_th_liferay$1rss_rss$1settings_0.setDisplayStyle( rssDisplayStyle );
                _jspx_th_liferay$1rss_rss$1settings_0.setEnabled( enableRSS );
                _jspx_th_liferay$1rss_rss$1settings_0.setFeedType( rssFeedType );
                int _jspx_eval_liferay$1rss_rss$1settings_0 = _jspx_th_liferay$1rss_rss$1settings_0.doStartTag();
                if (_jspx_th_liferay$1rss_rss$1settings_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_liferay$1rss_rss$1settings_feedType_enabled_displayStyle_delta_nobody.reuse(_jspx_th_liferay$1rss_rss$1settings_0);
                  return;
                }
                _jspx_tagPool_liferay$1rss_rss$1settings_feedType_enabled_displayStyle_delta_nobody.reuse(_jspx_th_liferay$1rss_rss$1settings_0);
                out.write('\n');
                out.write('\n');
                //  aui:fieldset
                com.liferay.taglib.aui.FieldsetTag _jspx_th_aui_fieldset_4 = (com.liferay.taglib.aui.FieldsetTag) _jspx_tagPool_aui_fieldset_id_cssClass.get(com.liferay.taglib.aui.FieldsetTag.class);
                _jspx_th_aui_fieldset_4.setPageContext(_jspx_page_context);
                _jspx_th_aui_fieldset_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_fieldset$1group_2);
                _jspx_th_aui_fieldset_4.setCssClass("rss-time-interval");
                _jspx_th_aui_fieldset_4.setId("rssTimeIntervalContainer");
                int _jspx_eval_aui_fieldset_4 = _jspx_th_aui_fieldset_4.doStartTag();
                if (_jspx_eval_aui_fieldset_4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  out.write('\n');
                  out.write('	');
                  //  aui:select
                  com.liferay.taglib.aui.SelectTag _jspx_th_aui_select_6 = (com.liferay.taglib.aui.SelectTag) _jspx_tagPool_aui_select_value_name_label.get(com.liferay.taglib.aui.SelectTag.class);
                  _jspx_th_aui_select_6.setPageContext(_jspx_page_context);
                  _jspx_th_aui_select_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_4);
                  _jspx_th_aui_select_6.setLabel("time-interval");
                  _jspx_th_aui_select_6.setName("preferences--rssTimeInterval--");
                  _jspx_th_aui_select_6.setValue( rssTimeInterval );
                  int _jspx_eval_aui_select_6 = _jspx_th_aui_select_6.doStartTag();
                  if (_jspx_eval_aui_select_6 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    if (_jspx_eval_aui_select_6 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                      out = _jspx_page_context.pushBody();
                      _jspx_th_aui_select_6.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                      _jspx_th_aui_select_6.doInitBody();
                    }
                    do {
                      out.write("\n\t\t");
                      //  aui:option
                      com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_20 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_value_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
                      _jspx_th_aui_option_20.setPageContext(_jspx_page_context);
                      _jspx_th_aui_option_20.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_6);
                      _jspx_th_aui_option_20.setLabel(new String("week"));
                      _jspx_th_aui_option_20.setValue( Time.WEEK );
                      int _jspx_eval_aui_option_20 = _jspx_th_aui_option_20.doStartTag();
                      if (_jspx_th_aui_option_20.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_aui_option_value_label_nobody.reuse(_jspx_th_aui_option_20);
                        return;
                      }
                      _jspx_tagPool_aui_option_value_label_nobody.reuse(_jspx_th_aui_option_20);
                      out.write("\n\t\t");
                      //  aui:option
                      com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_21 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_value_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
                      _jspx_th_aui_option_21.setPageContext(_jspx_page_context);
                      _jspx_th_aui_option_21.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_6);
                      _jspx_th_aui_option_21.setLabel(new String("month"));
                      _jspx_th_aui_option_21.setValue( Time.MONTH );
                      int _jspx_eval_aui_option_21 = _jspx_th_aui_option_21.doStartTag();
                      if (_jspx_th_aui_option_21.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_aui_option_value_label_nobody.reuse(_jspx_th_aui_option_21);
                        return;
                      }
                      _jspx_tagPool_aui_option_value_label_nobody.reuse(_jspx_th_aui_option_21);
                      out.write("\n\t\t");
                      //  aui:option
                      com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_22 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_value_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
                      _jspx_th_aui_option_22.setPageContext(_jspx_page_context);
                      _jspx_th_aui_option_22.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_6);
                      _jspx_th_aui_option_22.setLabel(new String("year"));
                      _jspx_th_aui_option_22.setValue( Time.YEAR );
                      int _jspx_eval_aui_option_22 = _jspx_th_aui_option_22.doStartTag();
                      if (_jspx_th_aui_option_22.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_aui_option_value_label_nobody.reuse(_jspx_th_aui_option_22);
                        return;
                      }
                      _jspx_tagPool_aui_option_value_label_nobody.reuse(_jspx_th_aui_option_22);
                      out.write('\n');
                      out.write('	');
                      int evalDoAfterBody = _jspx_th_aui_select_6.doAfterBody();
                      if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                        break;
                    } while (true);
                    if (_jspx_eval_aui_select_6 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                      out = _jspx_page_context.popBody();
                  }
                  if (_jspx_th_aui_select_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_aui_select_value_name_label.reuse(_jspx_th_aui_select_6);
                    return;
                  }
                  _jspx_tagPool_aui_select_value_name_label.reuse(_jspx_th_aui_select_6);
                  out.write('\n');
                }
                if (_jspx_th_aui_fieldset_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_aui_fieldset_id_cssClass.reuse(_jspx_th_aui_fieldset_4);
                  return;
                }
                _jspx_tagPool_aui_fieldset_id_cssClass.reuse(_jspx_th_aui_fieldset_4);
                out.write('\n');
                out.write('\n');
                if (_jspx_meth_aui_script_1((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1frontend_fieldset$1group_2, _jspx_page_context))
                  return;
                out.write("\n\t\t\t\t");
              }
              if (_jspx_th_liferay$1frontend_fieldset$1group_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_liferay$1frontend_fieldset$1group.reuse(_jspx_th_liferay$1frontend_fieldset$1group_2);
                return;
              }
              _jspx_tagPool_liferay$1frontend_fieldset$1group.reuse(_jspx_th_liferay$1frontend_fieldset$1group_2);
              out.write("\n\t\t\t");
            }
            if (_jspx_th_liferay$1ui_section_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_liferay$1ui_section.reuse(_jspx_th_liferay$1ui_section_2);
              return;
            }
            _jspx_tagPool_liferay$1ui_section.reuse(_jspx_th_liferay$1ui_section_2);
            out.write("\n\t\t");
          }
          if (_jspx_th_liferay$1ui_tabs_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_liferay$1ui_tabs_type_refresh_param_names.reuse(_jspx_th_liferay$1ui_tabs_0);
            return;
          }
          _jspx_tagPool_liferay$1ui_tabs_type_refresh_param_names.reuse(_jspx_th_liferay$1ui_tabs_0);
          out.write('\n');
          out.write('	');
        }
        if (_jspx_th_liferay$1frontend_edit$1form$1body_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_liferay$1frontend_edit$1form$1body.reuse(_jspx_th_liferay$1frontend_edit$1form$1body_0);
          return;
        }
        _jspx_tagPool_liferay$1frontend_edit$1form$1body.reuse(_jspx_th_liferay$1frontend_edit$1form$1body_0);
        out.write("\n\n\t");
        if (_jspx_meth_liferay$1frontend_edit$1form$1footer_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1frontend_edit$1form_0, _jspx_page_context))
          return;
        out.write('\n');
      }
      if (_jspx_th_liferay$1frontend_edit$1form_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_liferay$1frontend_edit$1form_name_method_action.reuse(_jspx_th_liferay$1frontend_edit$1form_0);
        return;
      }
      _jspx_tagPool_liferay$1frontend_edit$1form_name_method_action.reuse(_jspx_th_liferay$1frontend_edit$1form_0);
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

  private boolean _jspx_meth_aui_option_7(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_select_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:option
    com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_7 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_value_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
    _jspx_th_aui_option_7.setPageContext(_jspx_page_context);
    _jspx_th_aui_option_7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_2);
    _jspx_th_aui_option_7.setLabel(new String("day"));
    _jspx_th_aui_option_7.setValue(new String("day"));
    int _jspx_eval_aui_option_7 = _jspx_th_aui_option_7.doStartTag();
    if (_jspx_th_aui_option_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_option_value_label_nobody.reuse(_jspx_th_aui_option_7);
      return true;
    }
    _jspx_tagPool_aui_option_value_label_nobody.reuse(_jspx_th_aui_option_7);
    return false;
  }

  private boolean _jspx_meth_aui_option_8(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_select_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:option
    com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_8 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_value_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
    _jspx_th_aui_option_8.setPageContext(_jspx_page_context);
    _jspx_th_aui_option_8.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_2);
    _jspx_th_aui_option_8.setLabel(new String("month"));
    _jspx_th_aui_option_8.setValue(new String("month"));
    int _jspx_eval_aui_option_8 = _jspx_th_aui_option_8.doStartTag();
    if (_jspx_th_aui_option_8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_option_value_label_nobody.reuse(_jspx_th_aui_option_8);
      return true;
    }
    _jspx_tagPool_aui_option_value_label_nobody.reuse(_jspx_th_aui_option_8);
    return false;
  }

  private boolean _jspx_meth_aui_option_9(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_select_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:option
    com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_9 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_value_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
    _jspx_th_aui_option_9.setPageContext(_jspx_page_context);
    _jspx_th_aui_option_9.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_2);
    _jspx_th_aui_option_9.setLabel(new String("week"));
    _jspx_th_aui_option_9.setValue(new String("week"));
    int _jspx_eval_aui_option_9 = _jspx_th_aui_option_9.doStartTag();
    if (_jspx_th_aui_option_9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_option_value_label_nobody.reuse(_jspx_th_aui_option_9);
      return true;
    }
    _jspx_tagPool_aui_option_value_label_nobody.reuse(_jspx_th_aui_option_9);
    return false;
  }

  private boolean _jspx_meth_aui_option_10(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_select_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:option
    com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_10 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_value_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
    _jspx_th_aui_option_10.setPageContext(_jspx_page_context);
    _jspx_th_aui_option_10.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_2);
    _jspx_th_aui_option_10.setLabel(new String("agenda"));
    _jspx_th_aui_option_10.setValue(new String("agenda"));
    int _jspx_eval_aui_option_10 = _jspx_th_aui_option_10.doStartTag();
    if (_jspx_th_aui_option_10.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_option_value_label_nobody.reuse(_jspx_th_aui_option_10);
      return true;
    }
    _jspx_tagPool_aui_option_value_label_nobody.reuse(_jspx_th_aui_option_10);
    return false;
  }

  private boolean _jspx_meth_aui_option_11(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_select_3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:option
    com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_11 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_value_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
    _jspx_th_aui_option_11.setPageContext(_jspx_page_context);
    _jspx_th_aui_option_11.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_3);
    _jspx_th_aui_option_11.setLabel(new String("weekday.SU"));
    _jspx_th_aui_option_11.setValue(new String("0"));
    int _jspx_eval_aui_option_11 = _jspx_th_aui_option_11.doStartTag();
    if (_jspx_th_aui_option_11.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_option_value_label_nobody.reuse(_jspx_th_aui_option_11);
      return true;
    }
    _jspx_tagPool_aui_option_value_label_nobody.reuse(_jspx_th_aui_option_11);
    return false;
  }

  private boolean _jspx_meth_aui_option_12(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_select_3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:option
    com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_12 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_value_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
    _jspx_th_aui_option_12.setPageContext(_jspx_page_context);
    _jspx_th_aui_option_12.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_3);
    _jspx_th_aui_option_12.setLabel(new String("weekday.MO"));
    _jspx_th_aui_option_12.setValue(new String("1"));
    int _jspx_eval_aui_option_12 = _jspx_th_aui_option_12.doStartTag();
    if (_jspx_th_aui_option_12.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_option_value_label_nobody.reuse(_jspx_th_aui_option_12);
      return true;
    }
    _jspx_tagPool_aui_option_value_label_nobody.reuse(_jspx_th_aui_option_12);
    return false;
  }

  private boolean _jspx_meth_aui_option_13(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_select_3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:option
    com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_13 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_value_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
    _jspx_th_aui_option_13.setPageContext(_jspx_page_context);
    _jspx_th_aui_option_13.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_3);
    _jspx_th_aui_option_13.setLabel(new String("weekday.SA"));
    _jspx_th_aui_option_13.setValue(new String("6"));
    int _jspx_eval_aui_option_13 = _jspx_th_aui_option_13.doStartTag();
    if (_jspx_th_aui_option_13.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_option_value_label_nobody.reuse(_jspx_th_aui_option_13);
      return true;
    }
    _jspx_tagPool_aui_option_value_label_nobody.reuse(_jspx_th_aui_option_13);
    return false;
  }

  private boolean _jspx_meth_aui_script_0(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1frontend_fieldset$1group_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:script
    com.liferay.taglib.aui.ScriptTag _jspx_th_aui_script_0 = (com.liferay.taglib.aui.ScriptTag) _jspx_tagPool_aui_script_use.get(com.liferay.taglib.aui.ScriptTag.class);
    _jspx_th_aui_script_0.setPageContext(_jspx_page_context);
    _jspx_th_aui_script_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_fieldset$1group_0);
    _jspx_th_aui_script_0.setUse("aui-base");
    int _jspx_eval_aui_script_0 = _jspx_th_aui_script_0.doStartTag();
    if (_jspx_eval_aui_script_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_aui_script_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_aui_script_0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_aui_script_0.doInitBody();
      }
      do {
        out.write("\n\tvar usePortalTimeZoneCheckbox = A.one('#");
        if (_jspx_meth_portlet_namespace_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_0, _jspx_page_context))
          return true;
        out.write("usePortalTimeZone');\n\n\tif (usePortalTimeZoneCheckbox) {\n\t\tusePortalTimeZoneCheckbox.on(\n\t\t\t'change',\n\t\t\tfunction(event) {\n\t\t\t\tdocument.");
        if (_jspx_meth_portlet_namespace_1((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_0, _jspx_page_context))
          return true;
        out.write("fm.");
        if (_jspx_meth_portlet_namespace_2((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_0, _jspx_page_context))
          return true;
        out.write("timeZoneId.disabled = usePortalTimeZoneCheckbox.attr('checked');\n\t\t\t}\n\t\t);\n\t}\n");
        int evalDoAfterBody = _jspx_th_aui_script_0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_aui_script_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
        out = _jspx_page_context.popBody();
    }
    if (_jspx_th_aui_script_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_script_use.reuse(_jspx_th_aui_script_0);
      return true;
    }
    _jspx_tagPool_aui_script_use.reuse(_jspx_th_aui_script_0);
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

  private boolean _jspx_meth_aui_script_1(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1frontend_fieldset$1group_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:script
    com.liferay.taglib.aui.ScriptTag _jspx_th_aui_script_1 = (com.liferay.taglib.aui.ScriptTag) _jspx_tagPool_aui_script.get(com.liferay.taglib.aui.ScriptTag.class);
    _jspx_th_aui_script_1.setPageContext(_jspx_page_context);
    _jspx_th_aui_script_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_fieldset$1group_2);
    int _jspx_eval_aui_script_1 = _jspx_th_aui_script_1.doStartTag();
    if (_jspx_eval_aui_script_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_aui_script_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_aui_script_1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_aui_script_1.doInitBody();
      }
      do {
        out.write("\n\tLiferay.Util.toggleBoxes('");
        if (_jspx_meth_portlet_namespace_3((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
          return true;
        out.write("enableRss', '");
        if (_jspx_meth_portlet_namespace_4((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
          return true;
        out.write("rssTimeIntervalContainer');\n");
        int evalDoAfterBody = _jspx_th_aui_script_1.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_aui_script_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
        out = _jspx_page_context.popBody();
    }
    if (_jspx_th_aui_script_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_script.reuse(_jspx_th_aui_script_1);
      return true;
    }
    _jspx_tagPool_aui_script.reuse(_jspx_th_aui_script_1);
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

  private boolean _jspx_meth_liferay$1frontend_edit$1form$1footer_0(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1frontend_edit$1form_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-frontend:edit-form-footer
    com.liferay.frontend.taglib.servlet.taglib.EditFormFooterTag _jspx_th_liferay$1frontend_edit$1form$1footer_0 = (com.liferay.frontend.taglib.servlet.taglib.EditFormFooterTag) _jspx_tagPool_liferay$1frontend_edit$1form$1footer.get(com.liferay.frontend.taglib.servlet.taglib.EditFormFooterTag.class);
    _jspx_th_liferay$1frontend_edit$1form$1footer_0.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1frontend_edit$1form$1footer_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_edit$1form_0);
    int _jspx_eval_liferay$1frontend_edit$1form$1footer_0 = _jspx_th_liferay$1frontend_edit$1form$1footer_0.doStartTag();
    if (_jspx_eval_liferay$1frontend_edit$1form$1footer_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      out.write("\n\t\t");
      if (_jspx_meth_aui_button_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1frontend_edit$1form$1footer_0, _jspx_page_context))
        return true;
      out.write('\n');
      out.write('	');
    }
    if (_jspx_th_liferay$1frontend_edit$1form$1footer_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1frontend_edit$1form$1footer.reuse(_jspx_th_liferay$1frontend_edit$1form$1footer_0);
      return true;
    }
    _jspx_tagPool_liferay$1frontend_edit$1form$1footer.reuse(_jspx_th_liferay$1frontend_edit$1form$1footer_0);
    return false;
  }

  private boolean _jspx_meth_aui_button_0(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1frontend_edit$1form$1footer_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:button
    com.liferay.taglib.aui.ButtonTag _jspx_th_aui_button_0 = (com.liferay.taglib.aui.ButtonTag) _jspx_tagPool_aui_button_type_nobody.get(com.liferay.taglib.aui.ButtonTag.class);
    _jspx_th_aui_button_0.setPageContext(_jspx_page_context);
    _jspx_th_aui_button_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_edit$1form$1footer_0);
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
