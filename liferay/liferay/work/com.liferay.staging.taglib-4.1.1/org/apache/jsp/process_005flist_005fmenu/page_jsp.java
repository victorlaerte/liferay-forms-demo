package org.apache.jsp.process_005flist_005fmenu;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.liferay.background.task.kernel.util.comparator.BackgroundTaskComparatorFactoryUtil;
import com.liferay.exportimport.kernel.background.task.BackgroundTaskExecutorNames;
import com.liferay.exportimport.kernel.configuration.ExportImportConfigurationConstants;
import com.liferay.exportimport.kernel.configuration.ExportImportConfigurationHelper;
import com.liferay.exportimport.kernel.exception.RemoteExportException;
import com.liferay.exportimport.kernel.lar.ExportImportDateUtil;
import com.liferay.exportimport.kernel.lar.ExportImportHelperUtil;
import com.liferay.exportimport.kernel.lar.ManifestSummary;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.PortletDataContextFactoryUtil;
import com.liferay.exportimport.kernel.lar.PortletDataHandler;
import com.liferay.exportimport.kernel.lar.PortletDataHandlerBoolean;
import com.liferay.exportimport.kernel.lar.PortletDataHandlerChoice;
import com.liferay.exportimport.kernel.lar.PortletDataHandlerControl;
import com.liferay.exportimport.kernel.lar.PortletDataHandlerKeys;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.exportimport.kernel.model.ExportImportConfiguration;
import com.liferay.exportimport.kernel.service.ExportImportConfigurationLocalServiceUtil;
import com.liferay.exportimport.kernel.staging.LayoutStagingUtil;
import com.liferay.exportimport.kernel.staging.StagingUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.backgroundtask.BackgroundTask;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskConstants;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskManagerUtil;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskStatus;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskStatusRegistryUtil;
import com.liferay.portal.kernel.backgroundtask.display.BackgroundTaskDisplay;
import com.liferay.portal.kernel.backgroundtask.display.BackgroundTaskDisplayFactoryUtil;
import com.liferay.portal.kernel.dao.search.EmptyOnClickRowChecker;
import com.liferay.portal.kernel.dao.search.ResultRowSplitter;
import com.liferay.portal.kernel.exception.LayoutPrototypeException;
import com.liferay.portal.kernel.exception.NoSuchGroupException;
import com.liferay.portal.kernel.exception.NoSuchLayoutException;
import com.liferay.portal.kernel.exception.NoSuchRoleException;
import com.liferay.portal.kernel.exception.RemoteOptionsException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.language.UnicodeLanguageUtil;
import com.liferay.portal.kernel.lock.DuplicateLockException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.LayoutConstants;
import com.liferay.portal.kernel.model.LayoutRevision;
import com.liferay.portal.kernel.model.LayoutRevisionConstants;
import com.liferay.portal.kernel.model.LayoutSetBranch;
import com.liferay.portal.kernel.model.LayoutSetBranchConstants;
import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.model.StagedGroupedModel;
import com.liferay.portal.kernel.model.StagedModel;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.portlet.PortletBag;
import com.liferay.portal.kernel.portlet.PortletBagPool;
import com.liferay.portal.kernel.security.auth.AuthException;
import com.liferay.portal.kernel.security.auth.RemoteAuthException;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.ResourceActionsUtil;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;
import com.liferay.portal.kernel.service.LayoutSetBranchLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.service.permission.GroupPermissionUtil;
import com.liferay.portal.kernel.util.AggregateResourceBundle;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.DateRange;
import com.liferay.portal.kernel.util.FastDateFormatConstants;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PortletKeys;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Tuple;
import com.liferay.portal.kernel.util.UnicodeFormatter;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.staging.StagingGroupHelper;
import com.liferay.staging.StagingGroupHelperUtil;
import com.liferay.staging.constants.StagingProcessesWebKeys;
import com.liferay.taglib.ui.util.SessionTreeJSClicks;
import java.io.Serializable;
import java.text.Format;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Set;
import javax.portlet.PortletMode;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.PortletURL;

public final class page_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(5);
    _jspx_dependants.add("/process_list_menu/init.jsp");
    _jspx_dependants.add("/init.jsp");
    _jspx_dependants.add("/process_list_menu/items/relaunch.jspf");
    _jspx_dependants.add("/process_list_menu/items/delete.jspf");
    _jspx_dependants.add("/process_list_menu/items/summary.jspf");
  }

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_actionURL_var_name;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1staging_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1staging_process$1summary$1link_backgroundTaskId_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_param_value_name_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1theme_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_icon$1menu_showWhenSingleIcon_message_markupView_icon_direction;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_if_test;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1frontend_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_icon_url_message_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_defineObjects_nobody;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_portlet_actionURL_var_name = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1staging_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1staging_process$1summary$1link_backgroundTaskId_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_param_value_name_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1theme_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_icon$1menu_showWhenSingleIcon_message_markupView_icon_direction = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_if_test = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1frontend_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_icon_url_message_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_portlet_actionURL_var_name.release();
    _jspx_tagPool_liferay$1staging_defineObjects_nobody.release();
    _jspx_tagPool_liferay$1staging_process$1summary$1link_backgroundTaskId_nobody.release();
    _jspx_tagPool_portlet_param_value_name_nobody.release();
    _jspx_tagPool_liferay$1theme_defineObjects_nobody.release();
    _jspx_tagPool_liferay$1ui_icon$1menu_showWhenSingleIcon_message_markupView_icon_direction.release();
    _jspx_tagPool_c_if_test.release();
    _jspx_tagPool_liferay$1frontend_defineObjects_nobody.release();
    _jspx_tagPool_liferay$1ui_icon_url_message_nobody.release();
    _jspx_tagPool_portlet_defineObjects_nobody.release();
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
      out.write('\n');
      out.write('\n');
      out.write("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
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
      //  liferay-staging:defineObjects
      com.liferay.staging.taglib.servlet.taglib.DefineObjectsTag _jspx_th_liferay$1staging_defineObjects_0 = (com.liferay.staging.taglib.servlet.taglib.DefineObjectsTag) _jspx_tagPool_liferay$1staging_defineObjects_nobody.get(com.liferay.staging.taglib.servlet.taglib.DefineObjectsTag.class);
      _jspx_th_liferay$1staging_defineObjects_0.setPageContext(_jspx_page_context);
      _jspx_th_liferay$1staging_defineObjects_0.setParent(null);
      int _jspx_eval_liferay$1staging_defineObjects_0 = _jspx_th_liferay$1staging_defineObjects_0.doStartTag();
      if (_jspx_th_liferay$1staging_defineObjects_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_liferay$1staging_defineObjects_nobody.reuse(_jspx_th_liferay$1staging_defineObjects_0);
        return;
      }
      _jspx_tagPool_liferay$1staging_defineObjects_nobody.reuse(_jspx_th_liferay$1staging_defineObjects_0);
      com.liferay.portal.kernel.model.Group group = null;
      java.lang.Long groupId = null;
      com.liferay.portal.kernel.model.Group liveGroup = null;
      java.lang.Long liveGroupId = null;
      java.lang.Boolean privateLayout = null;
      com.liferay.portal.kernel.model.Group scopeGroup = null;
      java.lang.Long scopeGroupId = null;
      com.liferay.portal.kernel.model.Group stagingGroup = null;
      java.lang.Long stagingGroupId = null;
      group = (com.liferay.portal.kernel.model.Group) _jspx_page_context.findAttribute("group");
      groupId = (java.lang.Long) _jspx_page_context.findAttribute("groupId");
      liveGroup = (com.liferay.portal.kernel.model.Group) _jspx_page_context.findAttribute("liveGroup");
      liveGroupId = (java.lang.Long) _jspx_page_context.findAttribute("liveGroupId");
      privateLayout = (java.lang.Boolean) _jspx_page_context.findAttribute("privateLayout");
      scopeGroup = (com.liferay.portal.kernel.model.Group) _jspx_page_context.findAttribute("scopeGroup");
      scopeGroupId = (java.lang.Long) _jspx_page_context.findAttribute("scopeGroupId");
      stagingGroup = (com.liferay.portal.kernel.model.Group) _jspx_page_context.findAttribute("stagingGroup");
      stagingGroupId = (java.lang.Long) _jspx_page_context.findAttribute("stagingGroupId");
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

PortletRequest portletRequest = (PortletRequest)request.getAttribute(JavaConstants.JAVAX_PORTLET_REQUEST);

PortletResponse portletResponse = (PortletResponse)request.getAttribute(JavaConstants.JAVAX_PORTLET_RESPONSE);

if ((portletRequest == null) || (portletResponse == null)) {
	currentURL = PortalUtil.getCurrentURL(request);
}

      out.write('\n');
      out.write('\n');

BackgroundTask backgroundTask = (BackgroundTask)request.getAttribute("liferay-staging:process-list-menu:backgroundTask");
boolean deleteMenu = GetterUtil.getBoolean(request.getAttribute("liferay-staging:process-list-menu:deleteMenu"));
boolean localPublishing = GetterUtil.getBoolean(request.getAttribute("liferay-staging:process-list-menu:localPublishing"));
boolean relaunchMenu = GetterUtil.getBoolean(request.getAttribute("liferay-staging:process-list-menu:relaunchMenu"));
boolean summaryMenu = GetterUtil.getBoolean(request.getAttribute("liferay-staging:process-list-menu:summaryMenu"));

Date completionDate = backgroundTask.getCompletionDate();

String deleteLabel = LanguageUtil.get(request, ((completionDate != null) && completionDate.before(new Date())) ? "clear" : "cancel");

      out.write('\n');
      out.write('\n');
      //  liferay-ui:icon-menu
      com.liferay.taglib.ui.IconMenuTag _jspx_th_liferay$1ui_icon$1menu_0 = (com.liferay.taglib.ui.IconMenuTag) _jspx_tagPool_liferay$1ui_icon$1menu_showWhenSingleIcon_message_markupView_icon_direction.get(com.liferay.taglib.ui.IconMenuTag.class);
      _jspx_th_liferay$1ui_icon$1menu_0.setPageContext(_jspx_page_context);
      _jspx_th_liferay$1ui_icon$1menu_0.setParent(null);
      _jspx_th_liferay$1ui_icon$1menu_0.setDirection("left-side");
      _jspx_th_liferay$1ui_icon$1menu_0.setIcon( StringPool.BLANK );
      _jspx_th_liferay$1ui_icon$1menu_0.setMarkupView("lexicon");
      _jspx_th_liferay$1ui_icon$1menu_0.setMessage( StringPool.BLANK );
      _jspx_th_liferay$1ui_icon$1menu_0.setShowWhenSingleIcon( true );
      int _jspx_eval_liferay$1ui_icon$1menu_0 = _jspx_th_liferay$1ui_icon$1menu_0.doStartTag();
      if (_jspx_eval_liferay$1ui_icon$1menu_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        if (_jspx_eval_liferay$1ui_icon$1menu_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
          out = _jspx_page_context.pushBody();
          _jspx_th_liferay$1ui_icon$1menu_0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
          _jspx_th_liferay$1ui_icon$1menu_0.doInitBody();
        }
        do {
          out.write('\n');
          out.write('	');
          //  c:if
          com.liferay.taglib.core.IfTag _jspx_th_c_if_0 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
          _jspx_th_c_if_0.setPageContext(_jspx_page_context);
          _jspx_th_c_if_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_icon$1menu_0);
          _jspx_th_c_if_0.setTest( relaunchMenu );
          int _jspx_eval_c_if_0 = _jspx_th_c_if_0.doStartTag();
          if (_jspx_eval_c_if_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t");
            out.write('\n');
            out.write('\n');
            //  c:if
            com.liferay.taglib.core.IfTag _jspx_th_c_if_1 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
            _jspx_th_c_if_1.setPageContext(_jspx_page_context);
            _jspx_th_c_if_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_0);
            _jspx_th_c_if_1.setTest( !localPublishing || (backgroundTask.getGroupId() != liveGroupId) );
            int _jspx_eval_c_if_1 = _jspx_th_c_if_1.doStartTag();
            if (_jspx_eval_c_if_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write('\n');
              out.write('	');
              //  portlet:actionURL
              com.liferay.taglib.portlet.ActionURLTag _jspx_th_portlet_actionURL_0 = (com.liferay.taglib.portlet.ActionURLTag) _jspx_tagPool_portlet_actionURL_var_name.get(com.liferay.taglib.portlet.ActionURLTag.class);
              _jspx_th_portlet_actionURL_0.setPageContext(_jspx_page_context);
              _jspx_th_portlet_actionURL_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_1);
              _jspx_th_portlet_actionURL_0.setName("editPublishConfiguration");
              _jspx_th_portlet_actionURL_0.setVar("relaunchURL");
              int _jspx_eval_portlet_actionURL_0 = _jspx_th_portlet_actionURL_0.doStartTag();
              if (_jspx_eval_portlet_actionURL_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\t\t");
                if (_jspx_meth_portlet_param_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_portlet_actionURL_0, _jspx_page_context))
                  return;
                out.write("\n\t\t");
                //  portlet:param
                com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_1 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                _jspx_th_portlet_param_1.setPageContext(_jspx_page_context);
                _jspx_th_portlet_param_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_actionURL_0);
                _jspx_th_portlet_param_1.setName( Constants.CMD );
                _jspx_th_portlet_param_1.setValue( Constants.RELAUNCH );
                int _jspx_eval_portlet_param_1 = _jspx_th_portlet_param_1.doStartTag();
                if (_jspx_th_portlet_param_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_1);
                  return;
                }
                _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_1);
                out.write("\n\t\t");
                //  portlet:param
                com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_2 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                _jspx_th_portlet_param_2.setPageContext(_jspx_page_context);
                _jspx_th_portlet_param_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_actionURL_0);
                _jspx_th_portlet_param_2.setName("redirect");
                _jspx_th_portlet_param_2.setValue( currentURL.toString() );
                int _jspx_eval_portlet_param_2 = _jspx_th_portlet_param_2.doStartTag();
                if (_jspx_th_portlet_param_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_2);
                  return;
                }
                _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_2);
                out.write("\n\t\t");
                //  portlet:param
                com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_3 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                _jspx_th_portlet_param_3.setPageContext(_jspx_page_context);
                _jspx_th_portlet_param_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_actionURL_0);
                _jspx_th_portlet_param_3.setName("backgroundTaskId");
                _jspx_th_portlet_param_3.setValue( String.valueOf(backgroundTask.getBackgroundTaskId()) );
                int _jspx_eval_portlet_param_3 = _jspx_th_portlet_param_3.doStartTag();
                if (_jspx_th_portlet_param_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_3);
                  return;
                }
                _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_3);
                out.write('\n');
                out.write('	');
              }
              if (_jspx_th_portlet_actionURL_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_portlet_actionURL_var_name.reuse(_jspx_th_portlet_actionURL_0);
                return;
              }
              _jspx_tagPool_portlet_actionURL_var_name.reuse(_jspx_th_portlet_actionURL_0);
              java.lang.String relaunchURL = null;
              relaunchURL = (java.lang.String) _jspx_page_context.findAttribute("relaunchURL");
              out.write("\n\n\t");
              //  liferay-ui:icon
              com.liferay.taglib.ui.IconTag _jspx_th_liferay$1ui_icon_0 = (com.liferay.taglib.ui.IconTag) _jspx_tagPool_liferay$1ui_icon_url_message_nobody.get(com.liferay.taglib.ui.IconTag.class);
              _jspx_th_liferay$1ui_icon_0.setPageContext(_jspx_page_context);
              _jspx_th_liferay$1ui_icon_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_1);
              _jspx_th_liferay$1ui_icon_0.setMessage("relaunch");
              _jspx_th_liferay$1ui_icon_0.setUrl( relaunchURL );
              int _jspx_eval_liferay$1ui_icon_0 = _jspx_th_liferay$1ui_icon_0.doStartTag();
              if (_jspx_th_liferay$1ui_icon_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_liferay$1ui_icon_url_message_nobody.reuse(_jspx_th_liferay$1ui_icon_0);
                return;
              }
              _jspx_tagPool_liferay$1ui_icon_url_message_nobody.reuse(_jspx_th_liferay$1ui_icon_0);
              out.write('\n');
            }
            if (_jspx_th_c_if_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_1);
              return;
            }
            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_1);
            out.write('\n');
            out.write('	');
          }
          if (_jspx_th_c_if_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
            return;
          }
          _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
          out.write("\n\n\t");
          //  c:if
          com.liferay.taglib.core.IfTag _jspx_th_c_if_2 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
          _jspx_th_c_if_2.setPageContext(_jspx_page_context);
          _jspx_th_c_if_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_icon$1menu_0);
          _jspx_th_c_if_2.setTest( deleteMenu );
          int _jspx_eval_c_if_2 = _jspx_th_c_if_2.doStartTag();
          if (_jspx_eval_c_if_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t");
            out.write('\n');
            out.write('\n');
            //  portlet:actionURL
            com.liferay.taglib.portlet.ActionURLTag _jspx_th_portlet_actionURL_1 = (com.liferay.taglib.portlet.ActionURLTag) _jspx_tagPool_portlet_actionURL_var_name.get(com.liferay.taglib.portlet.ActionURLTag.class);
            _jspx_th_portlet_actionURL_1.setPageContext(_jspx_page_context);
            _jspx_th_portlet_actionURL_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_2);
            _jspx_th_portlet_actionURL_1.setName("deleteBackgroundTasks");
            _jspx_th_portlet_actionURL_1.setVar("deleteBackgroundTaskURL");
            int _jspx_eval_portlet_actionURL_1 = _jspx_th_portlet_actionURL_1.doStartTag();
            if (_jspx_eval_portlet_actionURL_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write('\n');
              out.write('	');
              //  portlet:param
              com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_4 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
              _jspx_th_portlet_param_4.setPageContext(_jspx_page_context);
              _jspx_th_portlet_param_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_actionURL_1);
              _jspx_th_portlet_param_4.setName("redirect");
              _jspx_th_portlet_param_4.setValue( currentURL.toString() );
              int _jspx_eval_portlet_param_4 = _jspx_th_portlet_param_4.doStartTag();
              if (_jspx_th_portlet_param_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_4);
                return;
              }
              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_4);
              out.write('\n');
              out.write('	');
              //  portlet:param
              com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_5 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
              _jspx_th_portlet_param_5.setPageContext(_jspx_page_context);
              _jspx_th_portlet_param_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_actionURL_1);
              _jspx_th_portlet_param_5.setName("deleteBackgroundTaskIds");
              _jspx_th_portlet_param_5.setValue( String.valueOf(backgroundTask.getBackgroundTaskId()) );
              int _jspx_eval_portlet_param_5 = _jspx_th_portlet_param_5.doStartTag();
              if (_jspx_th_portlet_param_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_5);
                return;
              }
              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_5);
              out.write('\n');
            }
            if (_jspx_th_portlet_actionURL_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_portlet_actionURL_var_name.reuse(_jspx_th_portlet_actionURL_1);
              return;
            }
            _jspx_tagPool_portlet_actionURL_var_name.reuse(_jspx_th_portlet_actionURL_1);
            java.lang.String deleteBackgroundTaskURL = null;
            deleteBackgroundTaskURL = (java.lang.String) _jspx_page_context.findAttribute("deleteBackgroundTaskURL");
            out.write('\n');
            out.write('\n');
            //  liferay-ui:icon
            com.liferay.taglib.ui.IconTag _jspx_th_liferay$1ui_icon_1 = (com.liferay.taglib.ui.IconTag) _jspx_tagPool_liferay$1ui_icon_url_message_nobody.get(com.liferay.taglib.ui.IconTag.class);
            _jspx_th_liferay$1ui_icon_1.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1ui_icon_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_2);
            _jspx_th_liferay$1ui_icon_1.setMessage( deleteLabel );
            _jspx_th_liferay$1ui_icon_1.setUrl( deleteBackgroundTaskURL );
            int _jspx_eval_liferay$1ui_icon_1 = _jspx_th_liferay$1ui_icon_1.doStartTag();
            if (_jspx_th_liferay$1ui_icon_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_liferay$1ui_icon_url_message_nobody.reuse(_jspx_th_liferay$1ui_icon_1);
              return;
            }
            _jspx_tagPool_liferay$1ui_icon_url_message_nobody.reuse(_jspx_th_liferay$1ui_icon_1);
            out.write('\n');
            out.write('	');
          }
          if (_jspx_th_c_if_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_2);
            return;
          }
          _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_2);
          out.write("\n\n\t");
          //  c:if
          com.liferay.taglib.core.IfTag _jspx_th_c_if_3 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
          _jspx_th_c_if_3.setPageContext(_jspx_page_context);
          _jspx_th_c_if_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_icon$1menu_0);
          _jspx_th_c_if_3.setTest( summaryMenu );
          int _jspx_eval_c_if_3 = _jspx_th_c_if_3.doStartTag();
          if (_jspx_eval_c_if_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t");
            out.write('\n');
            out.write('\n');

long exportImportConfigurationId = MapUtil.getLong(backgroundTask.getTaskContextMap(), "exportImportConfigurationId");

ExportImportConfiguration exportImportConfiguration = ExportImportConfigurationLocalServiceUtil.getExportImportConfiguration(exportImportConfigurationId);

Map<String, Serializable> settingsMap = exportImportConfiguration.getSettingsMap();

Map<String, String[]> parameterMap = (Map<String, String[]>)settingsMap.get("parameterMap");

String processCmd = MapUtil.getString(parameterMap, "cmd");

            out.write('\n');
            out.write('\n');
            //  c:if
            com.liferay.taglib.core.IfTag _jspx_th_c_if_4 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
            _jspx_th_c_if_4.setPageContext(_jspx_page_context);
            _jspx_th_c_if_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_3);
            _jspx_th_c_if_4.setTest( backgroundTask.isCompleted() && Validator.isNotNull(processCmd) );
            int _jspx_eval_c_if_4 = _jspx_th_c_if_4.doStartTag();
            if (_jspx_eval_c_if_4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write('\n');
              out.write('	');
              //  liferay-staging:process-summary-link
              com.liferay.staging.taglib.servlet.taglib.ProcessSummaryLinkTag _jspx_th_liferay$1staging_process$1summary$1link_0 = (com.liferay.staging.taglib.servlet.taglib.ProcessSummaryLinkTag) _jspx_tagPool_liferay$1staging_process$1summary$1link_backgroundTaskId_nobody.get(com.liferay.staging.taglib.servlet.taglib.ProcessSummaryLinkTag.class);
              _jspx_th_liferay$1staging_process$1summary$1link_0.setPageContext(_jspx_page_context);
              _jspx_th_liferay$1staging_process$1summary$1link_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_4);
              _jspx_th_liferay$1staging_process$1summary$1link_0.setBackgroundTaskId( backgroundTask.getBackgroundTaskId() );
              int _jspx_eval_liferay$1staging_process$1summary$1link_0 = _jspx_th_liferay$1staging_process$1summary$1link_0.doStartTag();
              if (_jspx_th_liferay$1staging_process$1summary$1link_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_liferay$1staging_process$1summary$1link_backgroundTaskId_nobody.reuse(_jspx_th_liferay$1staging_process$1summary$1link_0);
                return;
              }
              _jspx_tagPool_liferay$1staging_process$1summary$1link_backgroundTaskId_nobody.reuse(_jspx_th_liferay$1staging_process$1summary$1link_0);
              out.write('\n');
            }
            if (_jspx_th_c_if_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_4);
              return;
            }
            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_4);
            out.write('\n');
            out.write('	');
          }
          if (_jspx_th_c_if_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_3);
            return;
          }
          _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_3);
          out.write('\n');
          int evalDoAfterBody = _jspx_th_liferay$1ui_icon$1menu_0.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
        if (_jspx_eval_liferay$1ui_icon$1menu_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
          out = _jspx_page_context.popBody();
      }
      if (_jspx_th_liferay$1ui_icon$1menu_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_liferay$1ui_icon$1menu_showWhenSingleIcon_message_markupView_icon_direction.reuse(_jspx_th_liferay$1ui_icon$1menu_0);
        return;
      }
      _jspx_tagPool_liferay$1ui_icon$1menu_showWhenSingleIcon_message_markupView_icon_direction.reuse(_jspx_th_liferay$1ui_icon$1menu_0);
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
    _jspx_th_portlet_param_0.setName("mvcRenderCommandName");
    _jspx_th_portlet_param_0.setValue("editPublishConfiguration");
    int _jspx_eval_portlet_param_0 = _jspx_th_portlet_param_0.doStartTag();
    if (_jspx_th_portlet_param_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_0);
      return true;
    }
    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_0);
    return false;
  }
}