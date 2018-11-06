package org.apache.jsp.menu;

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
    _jspx_dependants = new java.util.ArrayList<String>(3);
    _jspx_dependants.add("/menu/init.jsp");
    _jspx_dependants.add("/init.jsp");
    _jspx_dependants.add("/menu/staging_actions.jspf");
  }

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1portlet_renderURL_windowState_varImpl_portletName_portletMode_plid;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_param_value_name_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_if_test;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_icon_url_message_id_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_choose;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1util_buffer_var;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1frontend_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1portlet_renderURL_windowState_varImpl_portletName;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_a_label_id_href_cssClass_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_nav_cssClass;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_when_test;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_script_sandbox_position;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_namespace_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1staging_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_message_key_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_nav$1bar;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_nav$1item_cssClass;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1portlet_renderURL_windowState_var_portletName;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_otherwise;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_renderURL_windowState_var;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1portlet_param_value_name_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_nav$1item_label_dropdown;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1theme_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_icon_markupView_image_nobody;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_liferay$1portlet_renderURL_windowState_varImpl_portletName_portletMode_plid = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_param_value_name_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_if_test = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_icon_url_message_id_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_choose = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1util_buffer_var = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1frontend_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1portlet_renderURL_windowState_varImpl_portletName = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_a_label_id_href_cssClass_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_nav_cssClass = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_when_test = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_script_sandbox_position = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_namespace_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1staging_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_message_key_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_nav$1bar = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_nav$1item_cssClass = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1portlet_renderURL_windowState_var_portletName = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_otherwise = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_renderURL_windowState_var = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1portlet_param_value_name_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_nav$1item_label_dropdown = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1theme_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_icon_markupView_image_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_liferay$1portlet_renderURL_windowState_varImpl_portletName_portletMode_plid.release();
    _jspx_tagPool_portlet_param_value_name_nobody.release();
    _jspx_tagPool_c_if_test.release();
    _jspx_tagPool_liferay$1ui_icon_url_message_id_nobody.release();
    _jspx_tagPool_c_choose.release();
    _jspx_tagPool_liferay$1util_buffer_var.release();
    _jspx_tagPool_liferay$1frontend_defineObjects_nobody.release();
    _jspx_tagPool_liferay$1portlet_renderURL_windowState_varImpl_portletName.release();
    _jspx_tagPool_aui_a_label_id_href_cssClass_nobody.release();
    _jspx_tagPool_aui_nav_cssClass.release();
    _jspx_tagPool_c_when_test.release();
    _jspx_tagPool_aui_script_sandbox_position.release();
    _jspx_tagPool_portlet_namespace_nobody.release();
    _jspx_tagPool_liferay$1staging_defineObjects_nobody.release();
    _jspx_tagPool_liferay$1ui_message_key_nobody.release();
    _jspx_tagPool_aui_nav$1bar.release();
    _jspx_tagPool_aui_nav$1item_cssClass.release();
    _jspx_tagPool_liferay$1portlet_renderURL_windowState_var_portletName.release();
    _jspx_tagPool_c_otherwise.release();
    _jspx_tagPool_portlet_renderURL_windowState_var.release();
    _jspx_tagPool_portlet_defineObjects_nobody.release();
    _jspx_tagPool_liferay$1portlet_param_value_name_nobody.release();
    _jspx_tagPool_aui_nav$1item_label_dropdown.release();
    _jspx_tagPool_liferay$1theme_defineObjects_nobody.release();
    _jspx_tagPool_aui_icon_markupView_image_nobody.release();
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

String cssClass = "staging-icon-menu " + GetterUtil.getString((String)request.getAttribute("liferay-staging:menu:cssClass"));
long layoutSetBranchId = GetterUtil.getLong((String)request.getAttribute("liferay-staging:menu:layoutSetBranchId"));
boolean onlyActions = GetterUtil.getBoolean((String)request.getAttribute("liferay-staging:menu:onlyActions"));
long selPlid = GetterUtil.getLong((String)request.getAttribute("liferay-staging:menu:selPlid"));
boolean showManageBranches = GetterUtil.getBoolean((String)request.getAttribute("liferay-staging:menu:showManageBranches"));

boolean branchingEnabled = GetterUtil.getBoolean((String)request.getAttribute(StagingProcessesWebKeys.BRANCHING_ENABLED));
boolean hasWorkflowTask = GetterUtil.getBoolean((String)request.getAttribute("view_layout_revision_details.jsp-hasWorkflowTask"));
LayoutRevision layoutRevision = (LayoutRevision)request.getAttribute("view_layout_revision_details.jsp-layoutRevision");

LayoutSetBranch layoutSetBranch = null;
List<LayoutSetBranch> layoutSetBranches = null;

String publishDialogTitle = null;

if (!group.isCompany()) {
	layoutSetBranches = LayoutSetBranchLocalServiceUtil.getLayoutSetBranches(stagingGroup.getGroupId(), privateLayout);
}

boolean localPublishing = group.isStaged() && !group.isStagedRemotely();

if (!localPublishing) {
	if ((layoutSetBranchId > 0) && (layoutSetBranches.size() > 1)) {
		publishDialogTitle = "publish-x-to-remote-live";
	}
	else {
		publishDialogTitle = "publish-to-remote-live";
	}
}
else {
	if ((layoutSetBranchId > 0) && (layoutSetBranches.size() > 1)) {
		publishDialogTitle = "publish-x-to-live";
	}
	else {
		publishDialogTitle = "publish-to-live";
	}
}

String publishMessage = LanguageUtil.get(request, publishDialogTitle);

      out.write('\n');
      out.write('\n');
      //  liferay-portlet:renderURL
      com.liferay.taglib.portlet.RenderURLTag _jspx_th_liferay$1portlet_renderURL_0 = (com.liferay.taglib.portlet.RenderURLTag) _jspx_tagPool_liferay$1portlet_renderURL_windowState_varImpl_portletName_portletMode_plid.get(com.liferay.taglib.portlet.RenderURLTag.class);
      _jspx_th_liferay$1portlet_renderURL_0.setPageContext(_jspx_page_context);
      _jspx_th_liferay$1portlet_renderURL_0.setParent(null);
      _jspx_th_liferay$1portlet_renderURL_0.setPlid( plid );
      _jspx_th_liferay$1portlet_renderURL_0.setPortletMode( PortletMode.VIEW.toString() );
      _jspx_th_liferay$1portlet_renderURL_0.setPortletName( PortletKeys.EXPORT_IMPORT );
      _jspx_th_liferay$1portlet_renderURL_0.setVarImpl("publishRenderURL");
      _jspx_th_liferay$1portlet_renderURL_0.setWindowState( LiferayWindowState.POP_UP.toString() );
      int _jspx_eval_liferay$1portlet_renderURL_0 = _jspx_th_liferay$1portlet_renderURL_0.doStartTag();
      if (_jspx_eval_liferay$1portlet_renderURL_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        out.write('\n');
        out.write('	');
        if (_jspx_meth_liferay$1portlet_param_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1portlet_renderURL_0, _jspx_page_context))
          return;
        out.write('\n');
        out.write('	');
        //  liferay-portlet:param
        com.liferay.taglib.util.ParamTag _jspx_th_liferay$1portlet_param_1 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_liferay$1portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
        _jspx_th_liferay$1portlet_param_1.setPageContext(_jspx_page_context);
        _jspx_th_liferay$1portlet_param_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_0);
        _jspx_th_liferay$1portlet_param_1.setName( Constants.CMD );
        _jspx_th_liferay$1portlet_param_1.setValue( localPublishing ? Constants.PUBLISH_TO_LIVE : Constants.PUBLISH_TO_REMOTE );
        int _jspx_eval_liferay$1portlet_param_1 = _jspx_th_liferay$1portlet_param_1.doStartTag();
        if (_jspx_th_liferay$1portlet_param_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_liferay$1portlet_param_value_name_nobody.reuse(_jspx_th_liferay$1portlet_param_1);
          return;
        }
        _jspx_tagPool_liferay$1portlet_param_value_name_nobody.reuse(_jspx_th_liferay$1portlet_param_1);
        out.write('\n');
        out.write('	');
        //  liferay-portlet:param
        com.liferay.taglib.util.ParamTag _jspx_th_liferay$1portlet_param_2 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_liferay$1portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
        _jspx_th_liferay$1portlet_param_2.setPageContext(_jspx_page_context);
        _jspx_th_liferay$1portlet_param_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_0);
        _jspx_th_liferay$1portlet_param_2.setName("tabs1");
        _jspx_th_liferay$1portlet_param_2.setValue( privateLayout ? "private-pages" : "public-pages" );
        int _jspx_eval_liferay$1portlet_param_2 = _jspx_th_liferay$1portlet_param_2.doStartTag();
        if (_jspx_th_liferay$1portlet_param_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_liferay$1portlet_param_value_name_nobody.reuse(_jspx_th_liferay$1portlet_param_2);
          return;
        }
        _jspx_tagPool_liferay$1portlet_param_value_name_nobody.reuse(_jspx_th_liferay$1portlet_param_2);
        out.write('\n');
        out.write('	');
        //  liferay-portlet:param
        com.liferay.taglib.util.ParamTag _jspx_th_liferay$1portlet_param_3 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_liferay$1portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
        _jspx_th_liferay$1portlet_param_3.setPageContext(_jspx_page_context);
        _jspx_th_liferay$1portlet_param_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_0);
        _jspx_th_liferay$1portlet_param_3.setName("closeRedirect");
        _jspx_th_liferay$1portlet_param_3.setValue( currentURL );
        int _jspx_eval_liferay$1portlet_param_3 = _jspx_th_liferay$1portlet_param_3.doStartTag();
        if (_jspx_th_liferay$1portlet_param_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_liferay$1portlet_param_value_name_nobody.reuse(_jspx_th_liferay$1portlet_param_3);
          return;
        }
        _jspx_tagPool_liferay$1portlet_param_value_name_nobody.reuse(_jspx_th_liferay$1portlet_param_3);
        out.write('\n');
        out.write('	');
        //  liferay-portlet:param
        com.liferay.taglib.util.ParamTag _jspx_th_liferay$1portlet_param_4 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_liferay$1portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
        _jspx_th_liferay$1portlet_param_4.setPageContext(_jspx_page_context);
        _jspx_th_liferay$1portlet_param_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_0);
        _jspx_th_liferay$1portlet_param_4.setName("groupId");
        _jspx_th_liferay$1portlet_param_4.setValue( String.valueOf(groupId) );
        int _jspx_eval_liferay$1portlet_param_4 = _jspx_th_liferay$1portlet_param_4.doStartTag();
        if (_jspx_th_liferay$1portlet_param_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_liferay$1portlet_param_value_name_nobody.reuse(_jspx_th_liferay$1portlet_param_4);
          return;
        }
        _jspx_tagPool_liferay$1portlet_param_value_name_nobody.reuse(_jspx_th_liferay$1portlet_param_4);
        out.write('\n');
        out.write('	');
        //  liferay-portlet:param
        com.liferay.taglib.util.ParamTag _jspx_th_liferay$1portlet_param_5 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_liferay$1portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
        _jspx_th_liferay$1portlet_param_5.setPageContext(_jspx_page_context);
        _jspx_th_liferay$1portlet_param_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_0);
        _jspx_th_liferay$1portlet_param_5.setName("privateLayout");
        _jspx_th_liferay$1portlet_param_5.setValue( String.valueOf(privateLayout) );
        int _jspx_eval_liferay$1portlet_param_5 = _jspx_th_liferay$1portlet_param_5.doStartTag();
        if (_jspx_th_liferay$1portlet_param_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_liferay$1portlet_param_value_name_nobody.reuse(_jspx_th_liferay$1portlet_param_5);
          return;
        }
        _jspx_tagPool_liferay$1portlet_param_value_name_nobody.reuse(_jspx_th_liferay$1portlet_param_5);
        out.write('\n');
        out.write('	');
        //  liferay-portlet:param
        com.liferay.taglib.util.ParamTag _jspx_th_liferay$1portlet_param_6 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_liferay$1portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
        _jspx_th_liferay$1portlet_param_6.setPageContext(_jspx_page_context);
        _jspx_th_liferay$1portlet_param_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_0);
        _jspx_th_liferay$1portlet_param_6.setName("selPlid");
        _jspx_th_liferay$1portlet_param_6.setValue( String.valueOf(selPlid) );
        int _jspx_eval_liferay$1portlet_param_6 = _jspx_th_liferay$1portlet_param_6.doStartTag();
        if (_jspx_th_liferay$1portlet_param_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_liferay$1portlet_param_value_name_nobody.reuse(_jspx_th_liferay$1portlet_param_6);
          return;
        }
        _jspx_tagPool_liferay$1portlet_param_value_name_nobody.reuse(_jspx_th_liferay$1portlet_param_6);
        out.write('\n');
      }
      if (_jspx_th_liferay$1portlet_renderURL_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_liferay$1portlet_renderURL_windowState_varImpl_portletName_portletMode_plid.reuse(_jspx_th_liferay$1portlet_renderURL_0);
        return;
      }
      _jspx_tagPool_liferay$1portlet_renderURL_windowState_varImpl_portletName_portletMode_plid.reuse(_jspx_th_liferay$1portlet_renderURL_0);
      com.liferay.portal.kernel.portlet.LiferayPortletURL publishRenderURL = null;
      publishRenderURL = (com.liferay.portal.kernel.portlet.LiferayPortletURL) _jspx_page_context.findAttribute("publishRenderURL");
      out.write('\n');
      out.write('\n');
      //  c:if
      com.liferay.taglib.core.IfTag _jspx_th_c_if_0 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
      _jspx_th_c_if_0.setPageContext(_jspx_page_context);
      _jspx_th_c_if_0.setParent(null);
      _jspx_th_c_if_0.setTest( stagingGroup != null );
      int _jspx_eval_c_if_0 = _jspx_th_c_if_0.doStartTag();
      if (_jspx_eval_c_if_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        out.write('\n');
        out.write('	');
        //  c:choose
        com.liferay.taglib.core.ChooseTag _jspx_th_c_choose_0 = (com.liferay.taglib.core.ChooseTag) _jspx_tagPool_c_choose.get(com.liferay.taglib.core.ChooseTag.class);
        _jspx_th_c_choose_0.setPageContext(_jspx_page_context);
        _jspx_th_c_choose_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_0);
        int _jspx_eval_c_choose_0 = _jspx_th_c_choose_0.doStartTag();
        if (_jspx_eval_c_choose_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          out.write("\n\t\t");
          //  c:when
          com.liferay.taglib.core.WhenTag _jspx_th_c_when_0 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
          _jspx_th_c_when_0.setPageContext(_jspx_page_context);
          _jspx_th_c_when_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_0);
          _jspx_th_c_when_0.setTest( onlyActions );
          int _jspx_eval_c_when_0 = _jspx_th_c_when_0.doStartTag();
          if (_jspx_eval_c_when_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t\t");
            out.write('\n');
            out.write('\n');
            //  c:choose
            com.liferay.taglib.core.ChooseTag _jspx_th_c_choose_1 = (com.liferay.taglib.core.ChooseTag) _jspx_tagPool_c_choose.get(com.liferay.taglib.core.ChooseTag.class);
            _jspx_th_c_choose_1.setPageContext(_jspx_page_context);
            _jspx_th_c_choose_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_0);
            int _jspx_eval_c_choose_1 = _jspx_th_c_choose_1.doStartTag();
            if (_jspx_eval_c_choose_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write('\n');
              out.write('	');
              //  c:when
              com.liferay.taglib.core.WhenTag _jspx_th_c_when_1 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
              _jspx_th_c_when_1.setPageContext(_jspx_page_context);
              _jspx_th_c_when_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_1);
              _jspx_th_c_when_1.setTest( group.isCompany() && GroupPermissionUtil.contains(permissionChecker, group, ActionKeys.PUBLISH_STAGING) );
              int _jspx_eval_c_when_1 = _jspx_th_c_when_1.doStartTag();
              if (_jspx_eval_c_when_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\t\t");
                //  liferay-ui:icon
                com.liferay.taglib.ui.IconTag _jspx_th_liferay$1ui_icon_0 = (com.liferay.taglib.ui.IconTag) _jspx_tagPool_liferay$1ui_icon_url_message_id_nobody.get(com.liferay.taglib.ui.IconTag.class);
                _jspx_th_liferay$1ui_icon_0.setPageContext(_jspx_page_context);
                _jspx_th_liferay$1ui_icon_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_1);
                _jspx_th_liferay$1ui_icon_0.setId( groupId + "publishGlobalLink" );
                _jspx_th_liferay$1ui_icon_0.setMessage( publishDialogTitle );
                _jspx_th_liferay$1ui_icon_0.setUrl( publishRenderURL.toString() );
                int _jspx_eval_liferay$1ui_icon_0 = _jspx_th_liferay$1ui_icon_0.doStartTag();
                if (_jspx_th_liferay$1ui_icon_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_liferay$1ui_icon_url_message_id_nobody.reuse(_jspx_th_liferay$1ui_icon_0);
                  return;
                }
                _jspx_tagPool_liferay$1ui_icon_url_message_id_nobody.reuse(_jspx_th_liferay$1ui_icon_0);
                out.write("\n\n\t\t");
                //  aui:script
                com.liferay.taglib.aui.ScriptTag _jspx_th_aui_script_0 = (com.liferay.taglib.aui.ScriptTag) _jspx_tagPool_aui_script_sandbox_position.get(com.liferay.taglib.aui.ScriptTag.class);
                _jspx_th_aui_script_0.setPageContext(_jspx_page_context);
                _jspx_th_aui_script_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_1);
                _jspx_th_aui_script_0.setPosition("inline");
                _jspx_th_aui_script_0.setSandbox( true );
                int _jspx_eval_aui_script_0 = _jspx_th_aui_script_0.doStartTag();
                if (_jspx_eval_aui_script_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  if (_jspx_eval_aui_script_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                    out = _jspx_page_context.pushBody();
                    _jspx_th_aui_script_0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                    _jspx_th_aui_script_0.doInitBody();
                  }
                  do {
                    out.write("\n\t\t\tvar publishGlobalLink = $('#");
                    if (_jspx_meth_portlet_namespace_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_0, _jspx_page_context))
                      return;
                    out.print( groupId + "publishGlobalLink" );
                    out.write("');\n\n\t\t\tpublishGlobalLink.on(\n\t\t\t\t'click',\n\t\t\t\tfunction(event) {\n\t\t\t\t\tevent.preventDefault();\n\n\t\t\t\t\tLiferay.LayoutExporter.publishToLive(\n\t\t\t\t\t\t{\n\t\t\t\t\t\t\ttitle: '");
                    out.print( UnicodeFormatter.toString(publishMessage) );
                    out.write("',\n\t\t\t\t\t\t\turl: publishGlobalLink.attr('href')\n\t\t\t\t\t\t}\n\t\t\t\t\t);\n\t\t\t\t}\n\t\t\t);\n\t\t");
                    int evalDoAfterBody = _jspx_th_aui_script_0.doAfterBody();
                    if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                      break;
                  } while (true);
                  if (_jspx_eval_aui_script_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                    out = _jspx_page_context.popBody();
                }
                if (_jspx_th_aui_script_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_aui_script_sandbox_position.reuse(_jspx_th_aui_script_0);
                  return;
                }
                _jspx_tagPool_aui_script_sandbox_position.reuse(_jspx_th_aui_script_0);
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
              //  c:otherwise
              com.liferay.taglib.core.OtherwiseTag _jspx_th_c_otherwise_0 = (com.liferay.taglib.core.OtherwiseTag) _jspx_tagPool_c_otherwise.get(com.liferay.taglib.core.OtherwiseTag.class);
              _jspx_th_c_otherwise_0.setPageContext(_jspx_page_context);
              _jspx_th_c_otherwise_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_1);
              int _jspx_eval_c_otherwise_0 = _jspx_th_c_otherwise_0.doStartTag();
              if (_jspx_eval_c_otherwise_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\n\t\t");

		Group groupType = layout.getGroup();
		
                out.write("\n\n\t\t");
                //  c:if
                com.liferay.taglib.core.IfTag _jspx_th_c_if_1 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                _jspx_th_c_if_1.setPageContext(_jspx_page_context);
                _jspx_th_c_if_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_0);
                _jspx_th_c_if_1.setTest( (liveGroup != null) && GroupPermissionUtil.contains(permissionChecker, liveGroup, ActionKeys.PUBLISH_STAGING) );
                int _jspx_eval_c_if_1 = _jspx_th_c_if_1.doStartTag();
                if (_jspx_eval_c_if_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  out.write("\n\t\t\t");
                  //  liferay-portlet:renderURL
                  com.liferay.taglib.portlet.RenderURLTag _jspx_th_liferay$1portlet_renderURL_1 = (com.liferay.taglib.portlet.RenderURLTag) _jspx_tagPool_liferay$1portlet_renderURL_windowState_var_portletName.get(com.liferay.taglib.portlet.RenderURLTag.class);
                  _jspx_th_liferay$1portlet_renderURL_1.setPageContext(_jspx_page_context);
                  _jspx_th_liferay$1portlet_renderURL_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_1);
                  _jspx_th_liferay$1portlet_renderURL_1.setPortletName( PortletKeys.EXPORT_IMPORT );
                  _jspx_th_liferay$1portlet_renderURL_1.setVar("publishRedirectURL");
                  _jspx_th_liferay$1portlet_renderURL_1.setWindowState( LiferayWindowState.POP_UP.toString() );
                  int _jspx_eval_liferay$1portlet_renderURL_1 = _jspx_th_liferay$1portlet_renderURL_1.doStartTag();
                  if (_jspx_eval_liferay$1portlet_renderURL_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    out.write("\n\t\t\t\t");
                    if (_jspx_meth_portlet_param_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1portlet_renderURL_1, _jspx_page_context))
                      return;
                    out.write("\n\t\t\t\t");
                    //  portlet:param
                    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_1 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                    _jspx_th_portlet_param_1.setPageContext(_jspx_page_context);
                    _jspx_th_portlet_param_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_1);
                    _jspx_th_portlet_param_1.setName( Constants.CMD );
                    _jspx_th_portlet_param_1.setValue( localPublishing ? Constants.PUBLISH_TO_LIVE : Constants.PUBLISH_TO_REMOTE );
                    int _jspx_eval_portlet_param_1 = _jspx_th_portlet_param_1.doStartTag();
                    if (_jspx_th_portlet_param_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_1);
                      return;
                    }
                    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_1);
                    out.write("\n\t\t\t\t");
                    if (_jspx_meth_portlet_param_2((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1portlet_renderURL_1, _jspx_page_context))
                      return;
                    out.write("\n\t\t\t\t");
                    //  portlet:param
                    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_3 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                    _jspx_th_portlet_param_3.setPageContext(_jspx_page_context);
                    _jspx_th_portlet_param_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_1);
                    _jspx_th_portlet_param_3.setName("groupId");
                    _jspx_th_portlet_param_3.setValue( String.valueOf(groupId) );
                    int _jspx_eval_portlet_param_3 = _jspx_th_portlet_param_3.doStartTag();
                    if (_jspx_th_portlet_param_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_3);
                      return;
                    }
                    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_3);
                    out.write("\n\t\t\t\t");
                    //  portlet:param
                    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_4 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                    _jspx_th_portlet_param_4.setPageContext(_jspx_page_context);
                    _jspx_th_portlet_param_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_1);
                    _jspx_th_portlet_param_4.setName("privateLayout");
                    _jspx_th_portlet_param_4.setValue( String.valueOf(privateLayout) );
                    int _jspx_eval_portlet_param_4 = _jspx_th_portlet_param_4.doStartTag();
                    if (_jspx_th_portlet_param_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_4);
                      return;
                    }
                    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_4);
                    out.write("\n\t\t\t\t");
                    //  portlet:param
                    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_5 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                    _jspx_th_portlet_param_5.setPageContext(_jspx_page_context);
                    _jspx_th_portlet_param_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_1);
                    _jspx_th_portlet_param_5.setName("quickPublish");
                    _jspx_th_portlet_param_5.setValue( Boolean.TRUE.toString() );
                    int _jspx_eval_portlet_param_5 = _jspx_th_portlet_param_5.doStartTag();
                    if (_jspx_th_portlet_param_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_5);
                      return;
                    }
                    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_5);
                    out.write("\n\t\t\t");
                  }
                  if (_jspx_th_liferay$1portlet_renderURL_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_liferay$1portlet_renderURL_windowState_var_portletName.reuse(_jspx_th_liferay$1portlet_renderURL_1);
                    return;
                  }
                  _jspx_tagPool_liferay$1portlet_renderURL_windowState_var_portletName.reuse(_jspx_th_liferay$1portlet_renderURL_1);
                  java.lang.String publishRedirectURL = null;
                  publishRedirectURL = (java.lang.String) _jspx_page_context.findAttribute("publishRedirectURL");
                  out.write("\n\n\t\t\t");

			UnicodeProperties liveGroupTypeSettings = liveGroup.getTypeSettingsProperties();
			
                  out.write("\n\n\t\t\t");
                  //  liferay-portlet:renderURL
                  com.liferay.taglib.portlet.RenderURLTag _jspx_th_liferay$1portlet_renderURL_2 = (com.liferay.taglib.portlet.RenderURLTag) _jspx_tagPool_liferay$1portlet_renderURL_windowState_varImpl_portletName.get(com.liferay.taglib.portlet.RenderURLTag.class);
                  _jspx_th_liferay$1portlet_renderURL_2.setPageContext(_jspx_page_context);
                  _jspx_th_liferay$1portlet_renderURL_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_1);
                  _jspx_th_liferay$1portlet_renderURL_2.setPortletName( PortletKeys.EXPORT_IMPORT );
                  _jspx_th_liferay$1portlet_renderURL_2.setVarImpl("simplePublishLayoutsURL");
                  _jspx_th_liferay$1portlet_renderURL_2.setWindowState( LiferayWindowState.POP_UP.toString() );
                  int _jspx_eval_liferay$1portlet_renderURL_2 = _jspx_th_liferay$1portlet_renderURL_2.doStartTag();
                  if (_jspx_eval_liferay$1portlet_renderURL_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    out.write("\n\t\t\t\t");
                    if (_jspx_meth_portlet_param_6((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1portlet_renderURL_2, _jspx_page_context))
                      return;
                    out.write("\n\t\t\t\t");
                    //  portlet:param
                    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_7 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                    _jspx_th_portlet_param_7.setPageContext(_jspx_page_context);
                    _jspx_th_portlet_param_7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_2);
                    _jspx_th_portlet_param_7.setName( Constants.CMD );
                    _jspx_th_portlet_param_7.setValue( localPublishing ? Constants.PUBLISH_TO_LIVE : Constants.PUBLISH_TO_REMOTE );
                    int _jspx_eval_portlet_param_7 = _jspx_th_portlet_param_7.doStartTag();
                    if (_jspx_th_portlet_param_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_7);
                      return;
                    }
                    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_7);
                    out.write("\n\t\t\t\t");
                    //  portlet:param
                    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_8 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                    _jspx_th_portlet_param_8.setPageContext(_jspx_page_context);
                    _jspx_th_portlet_param_8.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_2);
                    _jspx_th_portlet_param_8.setName("redirect");
                    _jspx_th_portlet_param_8.setValue( publishRedirectURL );
                    int _jspx_eval_portlet_param_8 = _jspx_th_portlet_param_8.doStartTag();
                    if (_jspx_th_portlet_param_8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_8);
                      return;
                    }
                    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_8);
                    out.write("\n\t\t\t\t");
                    //  portlet:param
                    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_9 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                    _jspx_th_portlet_param_9.setPageContext(_jspx_page_context);
                    _jspx_th_portlet_param_9.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_2);
                    _jspx_th_portlet_param_9.setName("backURL");
                    _jspx_th_portlet_param_9.setValue( currentURL );
                    int _jspx_eval_portlet_param_9 = _jspx_th_portlet_param_9.doStartTag();
                    if (_jspx_th_portlet_param_9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_9);
                      return;
                    }
                    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_9);
                    out.write("\n\t\t\t\t");
                    //  portlet:param
                    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_10 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                    _jspx_th_portlet_param_10.setPageContext(_jspx_page_context);
                    _jspx_th_portlet_param_10.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_2);
                    _jspx_th_portlet_param_10.setName("lastImportUserName");
                    _jspx_th_portlet_param_10.setValue( user.getFullName() );
                    int _jspx_eval_portlet_param_10 = _jspx_th_portlet_param_10.doStartTag();
                    if (_jspx_th_portlet_param_10.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_10);
                      return;
                    }
                    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_10);
                    out.write("\n\t\t\t\t");
                    //  portlet:param
                    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_11 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                    _jspx_th_portlet_param_11.setPageContext(_jspx_page_context);
                    _jspx_th_portlet_param_11.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_2);
                    _jspx_th_portlet_param_11.setName("lastImportUserUuid");
                    _jspx_th_portlet_param_11.setValue( String.valueOf(user.getUserUuid()) );
                    int _jspx_eval_portlet_param_11 = _jspx_th_portlet_param_11.doStartTag();
                    if (_jspx_th_portlet_param_11.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_11);
                      return;
                    }
                    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_11);
                    out.write("\n\t\t\t\t");
                    //  portlet:param
                    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_12 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                    _jspx_th_portlet_param_12.setPageContext(_jspx_page_context);
                    _jspx_th_portlet_param_12.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_2);
                    _jspx_th_portlet_param_12.setName("localPublishing");
                    _jspx_th_portlet_param_12.setValue( String.valueOf(localPublishing) );
                    int _jspx_eval_portlet_param_12 = _jspx_th_portlet_param_12.doStartTag();
                    if (_jspx_th_portlet_param_12.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_12);
                      return;
                    }
                    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_12);
                    out.write("\n\t\t\t\t");
                    //  portlet:param
                    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_13 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                    _jspx_th_portlet_param_13.setPageContext(_jspx_page_context);
                    _jspx_th_portlet_param_13.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_2);
                    _jspx_th_portlet_param_13.setName("privateLayout");
                    _jspx_th_portlet_param_13.setValue( String.valueOf(privateLayout) );
                    int _jspx_eval_portlet_param_13 = _jspx_th_portlet_param_13.doStartTag();
                    if (_jspx_th_portlet_param_13.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_13);
                      return;
                    }
                    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_13);
                    out.write("\n\t\t\t\t");
                    //  portlet:param
                    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_14 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                    _jspx_th_portlet_param_14.setPageContext(_jspx_page_context);
                    _jspx_th_portlet_param_14.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_2);
                    _jspx_th_portlet_param_14.setName("quickPublish");
                    _jspx_th_portlet_param_14.setValue( Boolean.TRUE.toString() );
                    int _jspx_eval_portlet_param_14 = _jspx_th_portlet_param_14.doStartTag();
                    if (_jspx_th_portlet_param_14.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_14);
                      return;
                    }
                    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_14);
                    out.write("\n\t\t\t\t");
                    //  portlet:param
                    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_15 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                    _jspx_th_portlet_param_15.setPageContext(_jspx_page_context);
                    _jspx_th_portlet_param_15.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_2);
                    _jspx_th_portlet_param_15.setName("remoteAddress");
                    _jspx_th_portlet_param_15.setValue( liveGroupTypeSettings.getProperty("remoteAddress") );
                    int _jspx_eval_portlet_param_15 = _jspx_th_portlet_param_15.doStartTag();
                    if (_jspx_th_portlet_param_15.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_15);
                      return;
                    }
                    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_15);
                    out.write("\n\t\t\t\t");
                    //  portlet:param
                    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_16 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                    _jspx_th_portlet_param_16.setPageContext(_jspx_page_context);
                    _jspx_th_portlet_param_16.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_2);
                    _jspx_th_portlet_param_16.setName("remotePort");
                    _jspx_th_portlet_param_16.setValue( liveGroupTypeSettings.getProperty("remotePort") );
                    int _jspx_eval_portlet_param_16 = _jspx_th_portlet_param_16.doStartTag();
                    if (_jspx_th_portlet_param_16.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_16);
                      return;
                    }
                    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_16);
                    out.write("\n\t\t\t\t");
                    //  portlet:param
                    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_17 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                    _jspx_th_portlet_param_17.setPageContext(_jspx_page_context);
                    _jspx_th_portlet_param_17.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_2);
                    _jspx_th_portlet_param_17.setName("remotePathContext");
                    _jspx_th_portlet_param_17.setValue( liveGroupTypeSettings.getProperty("remotePathContext") );
                    int _jspx_eval_portlet_param_17 = _jspx_th_portlet_param_17.doStartTag();
                    if (_jspx_th_portlet_param_17.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_17);
                      return;
                    }
                    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_17);
                    out.write("\n\t\t\t\t");
                    //  portlet:param
                    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_18 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                    _jspx_th_portlet_param_18.setPageContext(_jspx_page_context);
                    _jspx_th_portlet_param_18.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_2);
                    _jspx_th_portlet_param_18.setName("remoteGroupId");
                    _jspx_th_portlet_param_18.setValue( liveGroupTypeSettings.getProperty("remoteGroupId") );
                    int _jspx_eval_portlet_param_18 = _jspx_th_portlet_param_18.doStartTag();
                    if (_jspx_th_portlet_param_18.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_18);
                      return;
                    }
                    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_18);
                    out.write("\n\t\t\t\t");
                    //  portlet:param
                    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_19 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                    _jspx_th_portlet_param_19.setPageContext(_jspx_page_context);
                    _jspx_th_portlet_param_19.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_2);
                    _jspx_th_portlet_param_19.setName("secureConnection");
                    _jspx_th_portlet_param_19.setValue( liveGroupTypeSettings.getProperty("secureConnection") );
                    int _jspx_eval_portlet_param_19 = _jspx_th_portlet_param_19.doStartTag();
                    if (_jspx_th_portlet_param_19.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_19);
                      return;
                    }
                    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_19);
                    out.write("\n\t\t\t\t");
                    //  portlet:param
                    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_20 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                    _jspx_th_portlet_param_20.setPageContext(_jspx_page_context);
                    _jspx_th_portlet_param_20.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_2);
                    _jspx_th_portlet_param_20.setName("sourceGroupId");
                    _jspx_th_portlet_param_20.setValue( String.valueOf(stagingGroupId) );
                    int _jspx_eval_portlet_param_20 = _jspx_th_portlet_param_20.doStartTag();
                    if (_jspx_th_portlet_param_20.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_20);
                      return;
                    }
                    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_20);
                    out.write("\n\t\t\t\t");
                    //  portlet:param
                    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_21 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                    _jspx_th_portlet_param_21.setPageContext(_jspx_page_context);
                    _jspx_th_portlet_param_21.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_2);
                    _jspx_th_portlet_param_21.setName("targetGroupId");
                    _jspx_th_portlet_param_21.setValue( String.valueOf(liveGroupId) );
                    int _jspx_eval_portlet_param_21 = _jspx_th_portlet_param_21.doStartTag();
                    if (_jspx_th_portlet_param_21.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_21);
                      return;
                    }
                    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_21);
                    out.write("\n\t\t\t");
                  }
                  if (_jspx_th_liferay$1portlet_renderURL_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_liferay$1portlet_renderURL_windowState_varImpl_portletName.reuse(_jspx_th_liferay$1portlet_renderURL_2);
                    return;
                  }
                  _jspx_tagPool_liferay$1portlet_renderURL_windowState_varImpl_portletName.reuse(_jspx_th_liferay$1portlet_renderURL_2);
                  com.liferay.portal.kernel.portlet.LiferayPortletURL simplePublishLayoutsURL = null;
                  simplePublishLayoutsURL = (com.liferay.portal.kernel.portlet.LiferayPortletURL) _jspx_page_context.findAttribute("simplePublishLayoutsURL");
                  out.write("\n\n\t\t\t");

			if (groupId == 0) {
				simplePublishLayoutsURL.setParameter("selPlid", String.valueOf(plid));

				publishRenderURL.setParameter("selPlid", String.valueOf(plid));
			}
			
                  out.write("\n\n\t\t\t");
                  //  c:choose
                  com.liferay.taglib.core.ChooseTag _jspx_th_c_choose_2 = (com.liferay.taglib.core.ChooseTag) _jspx_tagPool_c_choose.get(com.liferay.taglib.core.ChooseTag.class);
                  _jspx_th_c_choose_2.setPageContext(_jspx_page_context);
                  _jspx_th_c_choose_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_1);
                  int _jspx_eval_c_choose_2 = _jspx_th_c_choose_2.doStartTag();
                  if (_jspx_eval_c_choose_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    out.write("\n\t\t\t\t");
                    //  c:when
                    com.liferay.taglib.core.WhenTag _jspx_th_c_when_2 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
                    _jspx_th_c_when_2.setPageContext(_jspx_page_context);
                    _jspx_th_c_when_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_2);
                    _jspx_th_c_when_2.setTest( (layoutSetBranchId > 0) && (layoutSetBranches.size() > 1) );
                    int _jspx_eval_c_when_2 = _jspx_th_c_when_2.doStartTag();
                    if (_jspx_eval_c_when_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      out.write("\n\n\t\t\t\t\t");

					layoutSetBranch = LayoutSetBranchLocalServiceUtil.getLayoutSetBranch(layoutSetBranchId);

					simplePublishLayoutsURL.setParameter("layoutSetBranchName", layoutSetBranch.getName());
					simplePublishLayoutsURL.setParameter("layoutSetBranchId", String.valueOf(layoutSetBranchId));

					publishRenderURL.setParameter("layoutSetBranchId", String.valueOf(layoutSetBranchId));
					publishRenderURL.setParameter("layoutSetBranchName", layoutSetBranch.getName());

					boolean translateLayoutSetBranchName = LayoutSetBranchConstants.MASTER_BRANCH_NAME.equals(HtmlUtil.escape(layoutSetBranch.getName()));

					publishMessage = LanguageUtil.format(request, publishDialogTitle, HtmlUtil.escape(layoutSetBranch.getName()), translateLayoutSetBranchName);
					
                      out.write("\n\n\t\t\t\t");
                    }
                    if (_jspx_th_c_when_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_2);
                      return;
                    }
                    _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_2);
                    out.write("\n\t\t\t\t");
                    //  c:otherwise
                    com.liferay.taglib.core.OtherwiseTag _jspx_th_c_otherwise_1 = (com.liferay.taglib.core.OtherwiseTag) _jspx_tagPool_c_otherwise.get(com.liferay.taglib.core.OtherwiseTag.class);
                    _jspx_th_c_otherwise_1.setPageContext(_jspx_page_context);
                    _jspx_th_c_otherwise_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_2);
                    int _jspx_eval_c_otherwise_1 = _jspx_th_c_otherwise_1.doStartTag();
                    if (_jspx_eval_c_otherwise_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      out.write("\n\t\t\t\t\t");
                      //  c:if
                      com.liferay.taglib.core.IfTag _jspx_th_c_if_2 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                      _jspx_th_c_if_2.setPageContext(_jspx_page_context);
                      _jspx_th_c_if_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_1);
                      _jspx_th_c_if_2.setTest( layoutSetBranches.size() == 1 );
                      int _jspx_eval_c_if_2 = _jspx_th_c_if_2.doStartTag();
                      if (_jspx_eval_c_if_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                        out.write("\n\n\t\t\t\t\t\t");

						layoutSetBranch = layoutSetBranches.get(0);

						simplePublishLayoutsURL.setParameter("layoutSetBranchId", String.valueOf(layoutSetBranch.getLayoutSetBranchId()));

						publishRenderURL.setParameter("layoutSetBranchId", String.valueOf(layoutSetBranch.getLayoutSetBranchId()));
						
                        out.write("\n\n\t\t\t\t\t");
                      }
                      if (_jspx_th_c_if_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_2);
                        return;
                      }
                      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_2);
                      out.write("\n\t\t\t\t");
                    }
                    if (_jspx_th_c_otherwise_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_1);
                      return;
                    }
                    _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_1);
                    out.write("\n\t\t\t");
                  }
                  if (_jspx_th_c_choose_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_2);
                    return;
                  }
                  _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_2);
                  out.write("\n\n\t\t\t");

			String linkCssClass = StringPool.BLANK;

			if (!groupType.isControlPanel()) {
				linkCssClass = "btn btn-default btn-sm";
			}
			
                  out.write("\n\n\t\t\t");
                  //  liferay-util:buffer
                  com.liferay.taglib.util.BufferTag _jspx_th_liferay$1util_buffer_0 = (com.liferay.taglib.util.BufferTag) _jspx_tagPool_liferay$1util_buffer_var.get(com.liferay.taglib.util.BufferTag.class);
                  _jspx_th_liferay$1util_buffer_0.setPageContext(_jspx_page_context);
                  _jspx_th_liferay$1util_buffer_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_1);
                  _jspx_th_liferay$1util_buffer_0.setVar("publishOnControlPanelLink");
                  int _jspx_eval_liferay$1util_buffer_0 = _jspx_th_liferay$1util_buffer_0.doStartTag();
                  if (_jspx_eval_liferay$1util_buffer_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    if (_jspx_eval_liferay$1util_buffer_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                      out = _jspx_page_context.pushBody();
                      _jspx_th_liferay$1util_buffer_0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                      _jspx_th_liferay$1util_buffer_0.doInitBody();
                    }
                    do {
                      out.write("\n\t\t\t\t");
                      //  aui:a
                      com.liferay.taglib.aui.ATag _jspx_th_aui_a_0 = (com.liferay.taglib.aui.ATag) _jspx_tagPool_aui_a_label_id_href_cssClass_nobody.get(com.liferay.taglib.aui.ATag.class);
                      _jspx_th_aui_a_0.setPageContext(_jspx_page_context);
                      _jspx_th_aui_a_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1util_buffer_0);
                      _jspx_th_aui_a_0.setCssClass( linkCssClass + " publish-link" );
                      _jspx_th_aui_a_0.setHref( publishRenderURL.toString() );
                      _jspx_th_aui_a_0.setId( layoutSetBranchId + "publishLink" );
                      _jspx_th_aui_a_0.setLabel( publishMessage );
                      int _jspx_eval_aui_a_0 = _jspx_th_aui_a_0.doStartTag();
                      if (_jspx_th_aui_a_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_aui_a_label_id_href_cssClass_nobody.reuse(_jspx_th_aui_a_0);
                        return;
                      }
                      _jspx_tagPool_aui_a_label_id_href_cssClass_nobody.reuse(_jspx_th_aui_a_0);
                      out.write("\n\t\t\t");
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
                  java.lang.String publishOnControlPanelLink = null;
                  publishOnControlPanelLink = (java.lang.String) _jspx_page_context.findAttribute("publishOnControlPanelLink");
                  out.write("\n\n\t\t\t");
                  //  liferay-util:buffer
                  com.liferay.taglib.util.BufferTag _jspx_th_liferay$1util_buffer_1 = (com.liferay.taglib.util.BufferTag) _jspx_tagPool_liferay$1util_buffer_var.get(com.liferay.taglib.util.BufferTag.class);
                  _jspx_th_liferay$1util_buffer_1.setPageContext(_jspx_page_context);
                  _jspx_th_liferay$1util_buffer_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_1);
                  _jspx_th_liferay$1util_buffer_1.setVar("publishOnLayoutLink");
                  int _jspx_eval_liferay$1util_buffer_1 = _jspx_th_liferay$1util_buffer_1.doStartTag();
                  if (_jspx_eval_liferay$1util_buffer_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    if (_jspx_eval_liferay$1util_buffer_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                      out = _jspx_page_context.pushBody();
                      _jspx_th_liferay$1util_buffer_1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                      _jspx_th_liferay$1util_buffer_1.doInitBody();
                    }
                    do {
                      out.write("\n\t\t\t\t<li class=\"control-menu-nav-item\">\n\t\t\t\t\t<div class=\"dropdown publish-btn-group\">\n\t\t\t\t\t\t<button class=\"btn btn-primary btn-sm\" href=\"");
                      out.print( simplePublishLayoutsURL.toString() );
                      out.write("\" id=\"");
                      out.print( layoutSetBranchId + "publishToLiveLink" );
                      out.write("\" type=\"button\">\n\t\t\t\t\t\t\t");
                      if (_jspx_meth_liferay$1ui_message_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1util_buffer_1, _jspx_page_context))
                        return;
                      out.write("\n\t\t\t\t\t\t</button>\n\n\t\t\t\t\t\t");
                      //  c:if
                      com.liferay.taglib.core.IfTag _jspx_th_c_if_3 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                      _jspx_th_c_if_3.setPageContext(_jspx_page_context);
                      _jspx_th_c_if_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1util_buffer_1);
                      _jspx_th_c_if_3.setTest( branchingEnabled );
                      int _jspx_eval_c_if_3 = _jspx_th_c_if_3.doStartTag();
                      if (_jspx_eval_c_if_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                        out.write("\n\t\t\t\t\t\t\t<button aria-expanded=\"false\" class=\"btn btn-primary btn-sm dropdown-toggle visible-xs\" data-toggle=\"dropdown\" type=\"button\">\n\t\t\t\t\t\t\t\t");
                        if (_jspx_meth_aui_icon_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_3, _jspx_page_context))
                          return;
                        out.write("\n\n\t\t\t\t\t\t\t\t<span class=\"sr-only\">\n\t\t\t\t\t\t\t\t\t");
                        if (_jspx_meth_liferay$1ui_message_1((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_3, _jspx_page_context))
                          return;
                        out.write("\n\t\t\t\t\t\t\t\t</span>\n\t\t\t\t\t\t\t</button>\n\n\t\t\t\t\t\t\t<ul class=\"dropdown-menu\" role=\"menu\">\n\t\t\t\t\t\t\t\t");
                        //  c:if
                        com.liferay.taglib.core.IfTag _jspx_th_c_if_4 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                        _jspx_th_c_if_4.setPageContext(_jspx_page_context);
                        _jspx_th_c_if_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_3);
                        _jspx_th_c_if_4.setTest( !layoutRevision.isIncomplete() );
                        int _jspx_eval_c_if_4 = _jspx_th_c_if_4.doStartTag();
                        if (_jspx_eval_c_if_4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                          out.write("\n\t\t\t\t\t\t\t\t\t<li>\n\t\t\t\t\t\t\t\t\t\t<a href=\"javascript:Liferay.fire('");
                          out.print( liferayPortletResponse.getNamespace() );
                          out.write("viewHistory', {layoutRevisionId: '");
                          out.print( layoutRevision.getLayoutRevisionId() );
                          out.write("', layoutSetBranchId: '");
                          out.print( layoutRevision.getLayoutSetBranchId() );
                          out.write("'}); void(0);\">\n\t\t\t\t\t\t\t\t\t\t\t");
                          if (_jspx_meth_liferay$1ui_message_2((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_4, _jspx_page_context))
                            return;
                          out.write("\n\t\t\t\t\t\t\t\t\t\t</a>\n\t\t\t\t\t\t\t\t\t</li>\n\t\t\t\t\t\t\t\t");
                        }
                        if (_jspx_th_c_if_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                          _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_4);
                          return;
                        }
                        _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_4);
                        out.write("\n\n\t\t\t\t\t\t\t\t");
                        //  c:if
                        com.liferay.taglib.core.IfTag _jspx_th_c_if_5 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                        _jspx_th_c_if_5.setPageContext(_jspx_page_context);
                        _jspx_th_c_if_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_3);
                        _jspx_th_c_if_5.setTest( !hasWorkflowTask );
                        int _jspx_eval_c_if_5 = _jspx_th_c_if_5.doStartTag();
                        if (_jspx_eval_c_if_5 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                          out.write("\n\t\t\t\t\t\t\t\t\t");
                          //  c:if
                          com.liferay.taglib.core.IfTag _jspx_th_c_if_6 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                          _jspx_th_c_if_6.setPageContext(_jspx_page_context);
                          _jspx_th_c_if_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_5);
                          _jspx_th_c_if_6.setTest( !layoutRevision.isMajor() && (layoutRevision.getParentLayoutRevisionId() != LayoutRevisionConstants.DEFAULT_PARENT_LAYOUT_REVISION_ID) );
                          int _jspx_eval_c_if_6 = _jspx_th_c_if_6.doStartTag();
                          if (_jspx_eval_c_if_6 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                            out.write("\n\t\t\t\t\t\t\t\t\t\t<li>\n\t\t\t\t\t\t\t\t\t\t\t<a href=\"javascript:Liferay.fire('");
                            out.print( liferayPortletResponse.getNamespace() );
                            out.write("undo', {layoutRevisionId: '");
                            out.print( layoutRevision.getLayoutRevisionId() );
                            out.write("', layoutSetBranchId: '");
                            out.print( layoutRevision.getLayoutSetBranchId() );
                            out.write("'}); void(0);\">\n\t\t\t\t\t\t\t\t\t\t\t\t");
                            if (_jspx_meth_liferay$1ui_message_3((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_6, _jspx_page_context))
                              return;
                            out.write("\n\t\t\t\t\t\t\t\t\t\t\t</a>\n\t\t\t\t\t\t\t\t\t\t</li>\n\t\t\t\t\t\t\t\t\t");
                          }
                          if (_jspx_th_c_if_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_6);
                            return;
                          }
                          _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_6);
                          out.write("\n\n\t\t\t\t\t\t\t\t\t");
                          //  c:if
                          com.liferay.taglib.core.IfTag _jspx_th_c_if_7 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                          _jspx_th_c_if_7.setPageContext(_jspx_page_context);
                          _jspx_th_c_if_7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_5);
                          _jspx_th_c_if_7.setTest( layoutRevision.hasChildren() );
                          int _jspx_eval_c_if_7 = _jspx_th_c_if_7.doStartTag();
                          if (_jspx_eval_c_if_7 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                            out.write("\n\n\t\t\t\t\t\t\t\t\t\t");

										List<LayoutRevision> childLayoutRevisions = layoutRevision.getChildren();

										LayoutRevision firstChildLayoutRevision = childLayoutRevisions.get(0);

										if (firstChildLayoutRevision.isInactive()) {
										
                            out.write("\n\n\t\t\t\t\t\t\t\t\t\t\t<li>\n\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"javascript:Liferay.fire('");
                            out.print( liferayPortletResponse.getNamespace() );
                            out.write("redo', {layoutRevisionId: '");
                            out.print( firstChildLayoutRevision.getLayoutRevisionId() );
                            out.write("', layoutSetBranchId: '");
                            out.print( firstChildLayoutRevision.getLayoutSetBranchId() );
                            out.write("'}); void(0);\">\n\t\t\t\t\t\t\t\t\t\t\t\t\t");
                            if (_jspx_meth_liferay$1ui_message_4((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_7, _jspx_page_context))
                              return;
                            out.write("\n\t\t\t\t\t\t\t\t\t\t\t\t</a>\n\t\t\t\t\t\t\t\t\t\t\t</li>\n\n\t\t\t\t\t\t\t\t\t\t");

										}
										
                            out.write("\n\n\t\t\t\t\t\t\t\t\t");
                          }
                          if (_jspx_th_c_if_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_7);
                            return;
                          }
                          _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_7);
                          out.write("\n\t\t\t\t\t\t\t\t");
                        }
                        if (_jspx_th_c_if_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                          _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_5);
                          return;
                        }
                        _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_5);
                        out.write("\n\t\t\t\t\t\t\t</ul>\n\t\t\t\t\t\t");
                      }
                      if (_jspx_th_c_if_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_3);
                        return;
                      }
                      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_3);
                      out.write("\n\t\t\t\t\t</div>\n\t\t\t\t</li>\n\t\t\t");
                      int evalDoAfterBody = _jspx_th_liferay$1util_buffer_1.doAfterBody();
                      if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                        break;
                    } while (true);
                    if (_jspx_eval_liferay$1util_buffer_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                      out = _jspx_page_context.popBody();
                  }
                  if (_jspx_th_liferay$1util_buffer_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_liferay$1util_buffer_var.reuse(_jspx_th_liferay$1util_buffer_1);
                    return;
                  }
                  _jspx_tagPool_liferay$1util_buffer_var.reuse(_jspx_th_liferay$1util_buffer_1);
                  java.lang.String publishOnLayoutLink = null;
                  publishOnLayoutLink = (java.lang.String) _jspx_page_context.findAttribute("publishOnLayoutLink");
                  out.write("\n\n\t\t\t");
                  //  c:choose
                  com.liferay.taglib.core.ChooseTag _jspx_th_c_choose_3 = (com.liferay.taglib.core.ChooseTag) _jspx_tagPool_c_choose.get(com.liferay.taglib.core.ChooseTag.class);
                  _jspx_th_c_choose_3.setPageContext(_jspx_page_context);
                  _jspx_th_c_choose_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_1);
                  int _jspx_eval_c_choose_3 = _jspx_th_c_choose_3.doStartTag();
                  if (_jspx_eval_c_choose_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    out.write("\n\t\t\t\t");
                    //  c:when
                    com.liferay.taglib.core.WhenTag _jspx_th_c_when_3 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
                    _jspx_th_c_when_3.setPageContext(_jspx_page_context);
                    _jspx_th_c_when_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_3);
                    _jspx_th_c_when_3.setTest( !onlyActions );
                    int _jspx_eval_c_when_3 = _jspx_th_c_when_3.doStartTag();
                    if (_jspx_eval_c_when_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      out.write("\n\t\t\t\t\t<li class=\"publish-link-container\">\n\t\t\t\t\t\t");
                      out.print( publishOnControlPanelLink );
                      out.write("\n\t\t\t\t\t</li>\n\t\t\t\t");
                    }
                    if (_jspx_th_c_when_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_3);
                      return;
                    }
                    _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_3);
                    out.write("\n\t\t\t\t");
                    //  c:otherwise
                    com.liferay.taglib.core.OtherwiseTag _jspx_th_c_otherwise_2 = (com.liferay.taglib.core.OtherwiseTag) _jspx_tagPool_c_otherwise.get(com.liferay.taglib.core.OtherwiseTag.class);
                    _jspx_th_c_otherwise_2.setPageContext(_jspx_page_context);
                    _jspx_th_c_otherwise_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_3);
                    int _jspx_eval_c_otherwise_2 = _jspx_th_c_otherwise_2.doStartTag();
                    if (_jspx_eval_c_otherwise_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      out.write("\n\t\t\t\t\t");
                      out.print( publishOnLayoutLink );
                      out.write("\n\t\t\t\t");
                    }
                    if (_jspx_th_c_otherwise_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_2);
                      return;
                    }
                    _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_2);
                    out.write("\n\t\t\t");
                  }
                  if (_jspx_th_c_choose_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_3);
                    return;
                  }
                  _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_3);
                  out.write("\n\n\t\t\t");
                  //  aui:script
                  com.liferay.taglib.aui.ScriptTag _jspx_th_aui_script_1 = (com.liferay.taglib.aui.ScriptTag) _jspx_tagPool_aui_script_sandbox_position.get(com.liferay.taglib.aui.ScriptTag.class);
                  _jspx_th_aui_script_1.setPageContext(_jspx_page_context);
                  _jspx_th_aui_script_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_1);
                  _jspx_th_aui_script_1.setPosition("inline");
                  _jspx_th_aui_script_1.setSandbox( true );
                  int _jspx_eval_aui_script_1 = _jspx_th_aui_script_1.doStartTag();
                  if (_jspx_eval_aui_script_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    if (_jspx_eval_aui_script_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                      out = _jspx_page_context.pushBody();
                      _jspx_th_aui_script_1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                      _jspx_th_aui_script_1.doInitBody();
                    }
                    do {
                      out.write("\n\t\t\t\tvar publishLink = $('#");
                      out.print( layoutSetBranchId + "publishLink" );
                      out.write("');\n\n\t\t\t\tpublishLink.on(\n\t\t\t\t\t'click',\n\t\t\t\t\tfunction(event) {\n\t\t\t\t\t\tevent.preventDefault();\n\n\t\t\t\t\t\tLiferay.LayoutExporter.publishToLive(\n\t\t\t\t\t\t\t{\n\t\t\t\t\t\t\t\ttitle: '");
                      out.print( UnicodeFormatter.toString(publishMessage) );
                      out.write("',\n\t\t\t\t\t\t\t\turl: publishLink.attr('href')\n\t\t\t\t\t\t\t}\n\t\t\t\t\t\t);\n\t\t\t\t\t}\n\t\t\t\t);\n\n\t\t\t\tvar publishToLiveLink = $('#");
                      out.print( layoutSetBranchId + "publishToLiveLink" );
                      out.write("');\n\n\t\t\t\tpublishToLiveLink.on(\n\t\t\t\t\t'click',\n\t\t\t\t\tfunction(event) {\n\t\t\t\t\t\tevent.preventDefault();\n\n\t\t\t\t\t\tLiferay.Util.openWindow(\n\t\t\t\t\t\t\t{\n\t\t\t\t\t\t\t\tdialog: {\n\t\t\t\t\t\t\t\t\tafter: {\n\t\t\t\t\t\t\t\t\t\tdestroy: function(event) {\n\t\t\t\t\t\t\t\t\t\t\twindow.location.reload();\n\t\t\t\t\t\t\t\t\t\t}\n\t\t\t\t\t\t\t\t\t},\n\t\t\t\t\t\t\t\t\tdestroyOnHide: true\n\t\t\t\t\t\t\t\t},\n\t\t\t\t\t\t\t\tdialogIframe: {\n\t\t\t\t\t\t\t\t\tbodyCssClass: 'dialog-with-footer'\n\t\t\t\t\t\t\t\t},\n\t\t\t\t\t\t\t\tid: '");
                      if (_jspx_meth_portlet_namespace_1((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
                        return;
                      out.write("publishLatestChangesDialog',\n\t\t\t\t\t\t\t\ttitle: '");
                      out.print( UnicodeFormatter.toString(publishMessage) );
                      out.write("',\n\t\t\t\t\t\t\t\turi: publishToLiveLink.attr('href')\n\t\t\t\t\t\t\t}\n\t\t\t\t\t\t);\n\t\t\t\t\t}\n\t\t\t\t);\n\t\t\t");
                      int evalDoAfterBody = _jspx_th_aui_script_1.doAfterBody();
                      if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                        break;
                    } while (true);
                    if (_jspx_eval_aui_script_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                      out = _jspx_page_context.popBody();
                  }
                  if (_jspx_th_aui_script_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_aui_script_sandbox_position.reuse(_jspx_th_aui_script_1);
                    return;
                  }
                  _jspx_tagPool_aui_script_sandbox_position.reuse(_jspx_th_aui_script_1);
                  out.write("\n\t\t");
                }
                if (_jspx_th_c_if_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_1);
                  return;
                }
                _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_1);
                out.write("\n\n\t\t");
                //  c:if
                com.liferay.taglib.core.IfTag _jspx_th_c_if_8 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                _jspx_th_c_if_8.setPageContext(_jspx_page_context);
                _jspx_th_c_if_8.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_0);
                _jspx_th_c_if_8.setTest( showManageBranches && !layoutSetBranches.isEmpty() );
                int _jspx_eval_c_if_8 = _jspx_th_c_if_8.doStartTag();
                if (_jspx_eval_c_if_8 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  out.write("\n\t\t\t");
                  //  portlet:renderURL
                  com.liferay.taglib.portlet.RenderURLTag _jspx_th_portlet_renderURL_0 = (com.liferay.taglib.portlet.RenderURLTag) _jspx_tagPool_portlet_renderURL_windowState_var.get(com.liferay.taglib.portlet.RenderURLTag.class);
                  _jspx_th_portlet_renderURL_0.setPageContext(_jspx_page_context);
                  _jspx_th_portlet_renderURL_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_8);
                  _jspx_th_portlet_renderURL_0.setVar("layoutSetBranchesURL");
                  _jspx_th_portlet_renderURL_0.setWindowState( LiferayWindowState.POP_UP.toString() );
                  int _jspx_eval_portlet_renderURL_0 = _jspx_th_portlet_renderURL_0.doStartTag();
                  if (_jspx_eval_portlet_renderURL_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    out.write("\n\t\t\t\t");
                    if (_jspx_meth_portlet_param_22((javax.servlet.jsp.tagext.JspTag) _jspx_th_portlet_renderURL_0, _jspx_page_context))
                      return;
                    out.write("\n\t\t\t\t");
                    //  portlet:param
                    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_23 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                    _jspx_th_portlet_param_23.setPageContext(_jspx_page_context);
                    _jspx_th_portlet_param_23.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_0);
                    _jspx_th_portlet_param_23.setName("groupId");
                    _jspx_th_portlet_param_23.setValue( String.valueOf(groupId) );
                    int _jspx_eval_portlet_param_23 = _jspx_th_portlet_param_23.doStartTag();
                    if (_jspx_th_portlet_param_23.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_23);
                      return;
                    }
                    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_23);
                    out.write("\n\t\t\t\t");
                    //  portlet:param
                    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_24 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                    _jspx_th_portlet_param_24.setPageContext(_jspx_page_context);
                    _jspx_th_portlet_param_24.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_0);
                    _jspx_th_portlet_param_24.setName("privateLayout");
                    _jspx_th_portlet_param_24.setValue( String.valueOf(privateLayout) );
                    int _jspx_eval_portlet_param_24 = _jspx_th_portlet_param_24.doStartTag();
                    if (_jspx_th_portlet_param_24.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_24);
                      return;
                    }
                    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_24);
                    out.write("\n\t\t\t\t");
                    //  portlet:param
                    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_25 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                    _jspx_th_portlet_param_25.setPageContext(_jspx_page_context);
                    _jspx_th_portlet_param_25.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_0);
                    _jspx_th_portlet_param_25.setName("selPlid");
                    _jspx_th_portlet_param_25.setValue( String.valueOf(selPlid) );
                    int _jspx_eval_portlet_param_25 = _jspx_th_portlet_param_25.doStartTag();
                    if (_jspx_th_portlet_param_25.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_25);
                      return;
                    }
                    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_25);
                    out.write("\n\t\t\t");
                  }
                  if (_jspx_th_portlet_renderURL_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_portlet_renderURL_windowState_var.reuse(_jspx_th_portlet_renderURL_0);
                    return;
                  }
                  _jspx_tagPool_portlet_renderURL_windowState_var.reuse(_jspx_th_portlet_renderURL_0);
                  java.lang.String layoutSetBranchesURL = null;
                  layoutSetBranchesURL = (java.lang.String) _jspx_page_context.findAttribute("layoutSetBranchesURL");
                  out.write("\n\n\t\t\t");
                  //  liferay-util:buffer
                  com.liferay.taglib.util.BufferTag _jspx_th_liferay$1util_buffer_2 = (com.liferay.taglib.util.BufferTag) _jspx_tagPool_liferay$1util_buffer_var.get(com.liferay.taglib.util.BufferTag.class);
                  _jspx_th_liferay$1util_buffer_2.setPageContext(_jspx_page_context);
                  _jspx_th_liferay$1util_buffer_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_8);
                  _jspx_th_liferay$1util_buffer_2.setVar("manageSitePagesVariationsLink");
                  int _jspx_eval_liferay$1util_buffer_2 = _jspx_th_liferay$1util_buffer_2.doStartTag();
                  if (_jspx_eval_liferay$1util_buffer_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    if (_jspx_eval_liferay$1util_buffer_2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                      out = _jspx_page_context.pushBody();
                      _jspx_th_liferay$1util_buffer_2.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                      _jspx_th_liferay$1util_buffer_2.doInitBody();
                    }
                    do {
                      out.write("\n\t\t\t\t");
                      //  aui:a
                      com.liferay.taglib.aui.ATag _jspx_th_aui_a_1 = (com.liferay.taglib.aui.ATag) _jspx_tagPool_aui_a_label_id_href_cssClass_nobody.get(com.liferay.taglib.aui.ATag.class);
                      _jspx_th_aui_a_1.setPageContext(_jspx_page_context);
                      _jspx_th_aui_a_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1util_buffer_2);
                      _jspx_th_aui_a_1.setCssClass("manage-layout-set-branches");
                      _jspx_th_aui_a_1.setHref( layoutSetBranchesURL );
                      _jspx_th_aui_a_1.setId("manageLayoutSetBranches");
                      _jspx_th_aui_a_1.setLabel("manage-site-pages-variations");
                      int _jspx_eval_aui_a_1 = _jspx_th_aui_a_1.doStartTag();
                      if (_jspx_th_aui_a_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_aui_a_label_id_href_cssClass_nobody.reuse(_jspx_th_aui_a_1);
                        return;
                      }
                      _jspx_tagPool_aui_a_label_id_href_cssClass_nobody.reuse(_jspx_th_aui_a_1);
                      out.write("\n\t\t\t");
                      int evalDoAfterBody = _jspx_th_liferay$1util_buffer_2.doAfterBody();
                      if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                        break;
                    } while (true);
                    if (_jspx_eval_liferay$1util_buffer_2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                      out = _jspx_page_context.popBody();
                  }
                  if (_jspx_th_liferay$1util_buffer_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_liferay$1util_buffer_var.reuse(_jspx_th_liferay$1util_buffer_2);
                    return;
                  }
                  _jspx_tagPool_liferay$1util_buffer_var.reuse(_jspx_th_liferay$1util_buffer_2);
                  java.lang.String manageSitePagesVariationsLink = null;
                  manageSitePagesVariationsLink = (java.lang.String) _jspx_page_context.findAttribute("manageSitePagesVariationsLink");
                  out.write("\n\n\t\t\t");
                  //  c:choose
                  com.liferay.taglib.core.ChooseTag _jspx_th_c_choose_4 = (com.liferay.taglib.core.ChooseTag) _jspx_tagPool_c_choose.get(com.liferay.taglib.core.ChooseTag.class);
                  _jspx_th_c_choose_4.setPageContext(_jspx_page_context);
                  _jspx_th_c_choose_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_8);
                  int _jspx_eval_c_choose_4 = _jspx_th_c_choose_4.doStartTag();
                  if (_jspx_eval_c_choose_4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    out.write("\n\t\t\t\t");
                    //  c:when
                    com.liferay.taglib.core.WhenTag _jspx_th_c_when_4 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
                    _jspx_th_c_when_4.setPageContext(_jspx_page_context);
                    _jspx_th_c_when_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_4);
                    _jspx_th_c_when_4.setTest( groupType.isControlPanel() );
                    int _jspx_eval_c_when_4 = _jspx_th_c_when_4.doStartTag();
                    if (_jspx_eval_c_when_4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      out.write("\n\t\t\t\t\t<li class=\"publish-link-container\">\n\t\t\t\t\t\t");
                      out.print( manageSitePagesVariationsLink );
                      out.write("\n\t\t\t\t\t</li>\n\t\t\t\t");
                    }
                    if (_jspx_th_c_when_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_4);
                      return;
                    }
                    _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_4);
                    out.write("\n\t\t\t\t");
                    //  c:otherwise
                    com.liferay.taglib.core.OtherwiseTag _jspx_th_c_otherwise_3 = (com.liferay.taglib.core.OtherwiseTag) _jspx_tagPool_c_otherwise.get(com.liferay.taglib.core.OtherwiseTag.class);
                    _jspx_th_c_otherwise_3.setPageContext(_jspx_page_context);
                    _jspx_th_c_otherwise_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_4);
                    int _jspx_eval_c_otherwise_3 = _jspx_th_c_otherwise_3.doStartTag();
                    if (_jspx_eval_c_otherwise_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      out.write("\n\t\t\t\t\t<div class=\"");
                      out.print( cssClass );
                      out.write(" publish-link-container\">\n\t\t\t\t\t\t");
                      out.print( manageSitePagesVariationsLink );
                      out.write("\n\t\t\t\t\t</div>\n\t\t\t\t");
                    }
                    if (_jspx_th_c_otherwise_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_3);
                      return;
                    }
                    _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_3);
                    out.write("\n\t\t\t");
                  }
                  if (_jspx_th_c_choose_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_4);
                    return;
                  }
                  _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_4);
                  out.write("\n\n\t\t\t");
                  //  aui:script
                  com.liferay.taglib.aui.ScriptTag _jspx_th_aui_script_2 = (com.liferay.taglib.aui.ScriptTag) _jspx_tagPool_aui_script_sandbox_position.get(com.liferay.taglib.aui.ScriptTag.class);
                  _jspx_th_aui_script_2.setPageContext(_jspx_page_context);
                  _jspx_th_aui_script_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_8);
                  _jspx_th_aui_script_2.setPosition("inline");
                  _jspx_th_aui_script_2.setSandbox( true );
                  int _jspx_eval_aui_script_2 = _jspx_th_aui_script_2.doStartTag();
                  if (_jspx_eval_aui_script_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    if (_jspx_eval_aui_script_2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                      out = _jspx_page_context.pushBody();
                      _jspx_th_aui_script_2.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                      _jspx_th_aui_script_2.doInitBody();
                    }
                    do {
                      out.write("\n\t\t\t\tvar layoutSetBranchesLink = $('#");
                      if (_jspx_meth_portlet_namespace_2((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_2, _jspx_page_context))
                        return;
                      out.write("manageLayoutSetBranches');\n\n\t\t\t\tlayoutSetBranchesLink.on(\n\t\t\t\t\t'click',\n\t\t\t\t\tfunction(event) {\n\t\t\t\t\t\tevent.preventDefault();\n\n\t\t\t\t\t\tLiferay.Util.openWindow(\n\t\t\t\t\t\t\t{\n\t\t\t\t\t\t\t\tdialog: {\n\t\t\t\t\t\t\t\t\twidth: 820\n\t\t\t\t\t\t\t\t},\n\t\t\t\t\t\t\t\tid: '");
                      if (_jspx_meth_portlet_namespace_3((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_2, _jspx_page_context))
                        return;
                      out.write("layoutSetBranches',\n\t\t\t\t\t\t\t\ttitle: '");
                      out.print( UnicodeLanguageUtil.get(request, "manage-site-pages-variations") );
                      out.write("',\n\t\t\t\t\t\t\t\turi: layoutSetBranchesLink.attr('href')\n\t\t\t\t\t\t\t}\n\t\t\t\t\t\t);\n\t\t\t\t\t}\n\t\t\t\t);\n\t\t\t");
                      int evalDoAfterBody = _jspx_th_aui_script_2.doAfterBody();
                      if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                        break;
                    } while (true);
                    if (_jspx_eval_aui_script_2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                      out = _jspx_page_context.popBody();
                  }
                  if (_jspx_th_aui_script_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_aui_script_sandbox_position.reuse(_jspx_th_aui_script_2);
                    return;
                  }
                  _jspx_tagPool_aui_script_sandbox_position.reuse(_jspx_th_aui_script_2);
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
            out.write("\n\t\t");
          }
          if (_jspx_th_c_when_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_0);
            return;
          }
          _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_0);
          out.write("\n\t\t");
          //  c:otherwise
          com.liferay.taglib.core.OtherwiseTag _jspx_th_c_otherwise_4 = (com.liferay.taglib.core.OtherwiseTag) _jspx_tagPool_c_otherwise.get(com.liferay.taglib.core.OtherwiseTag.class);
          _jspx_th_c_otherwise_4.setPageContext(_jspx_page_context);
          _jspx_th_c_otherwise_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_0);
          int _jspx_eval_c_otherwise_4 = _jspx_th_c_otherwise_4.doStartTag();
          if (_jspx_eval_c_otherwise_4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t\t");
            //  aui:nav-bar
            com.liferay.taglib.aui.NavBarTag _jspx_th_aui_nav$1bar_0 = (com.liferay.taglib.aui.NavBarTag) _jspx_tagPool_aui_nav$1bar.get(com.liferay.taglib.aui.NavBarTag.class);
            _jspx_th_aui_nav$1bar_0.setPageContext(_jspx_page_context);
            _jspx_th_aui_nav$1bar_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_4);
            int _jspx_eval_aui_nav$1bar_0 = _jspx_th_aui_nav$1bar_0.doStartTag();
            if (_jspx_eval_aui_nav$1bar_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              if (_jspx_eval_aui_nav$1bar_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                out = _jspx_page_context.pushBody();
                _jspx_th_aui_nav$1bar_0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                _jspx_th_aui_nav$1bar_0.doInitBody();
              }
              do {
                out.write("\n\t\t\t\t");
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
                    out.write("\n\t\t\t\t\t");
                    //  aui:nav-item
                    com.liferay.taglib.aui.NavItemTag _jspx_th_aui_nav$1item_0 = (com.liferay.taglib.aui.NavItemTag) _jspx_tagPool_aui_nav$1item_label_dropdown.get(com.liferay.taglib.aui.NavItemTag.class);
                    _jspx_th_aui_nav$1item_0.setPageContext(_jspx_page_context);
                    _jspx_th_aui_nav$1item_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_nav_0);
                    _jspx_th_aui_nav$1item_0.setDropdown( true );
                    _jspx_th_aui_nav$1item_0.setLabel("staging");
                    int _jspx_eval_aui_nav$1item_0 = _jspx_th_aui_nav$1item_0.doStartTag();
                    if (_jspx_eval_aui_nav$1item_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      if (_jspx_eval_aui_nav$1item_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                        out = _jspx_page_context.pushBody();
                        _jspx_th_aui_nav$1item_0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                        _jspx_th_aui_nav$1item_0.doInitBody();
                      }
                      do {
                        out.write("\n\t\t\t\t\t\t");
                        //  aui:nav-item
                        com.liferay.taglib.aui.NavItemTag _jspx_th_aui_nav$1item_1 = (com.liferay.taglib.aui.NavItemTag) _jspx_tagPool_aui_nav$1item_cssClass.get(com.liferay.taglib.aui.NavItemTag.class);
                        _jspx_th_aui_nav$1item_1.setPageContext(_jspx_page_context);
                        _jspx_th_aui_nav$1item_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_nav$1item_0);
                        _jspx_th_aui_nav$1item_1.setCssClass( cssClass );
                        int _jspx_eval_aui_nav$1item_1 = _jspx_th_aui_nav$1item_1.doStartTag();
                        if (_jspx_eval_aui_nav$1item_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                          if (_jspx_eval_aui_nav$1item_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                            out = _jspx_page_context.pushBody();
                            _jspx_th_aui_nav$1item_1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                            _jspx_th_aui_nav$1item_1.doInitBody();
                          }
                          do {
                            out.write("\n\t\t\t\t\t\t\t");
                            out.write('\n');
                            out.write('\n');
                            //  c:choose
                            com.liferay.taglib.core.ChooseTag _jspx_th_c_choose_5 = (com.liferay.taglib.core.ChooseTag) _jspx_tagPool_c_choose.get(com.liferay.taglib.core.ChooseTag.class);
                            _jspx_th_c_choose_5.setPageContext(_jspx_page_context);
                            _jspx_th_c_choose_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_nav$1item_1);
                            int _jspx_eval_c_choose_5 = _jspx_th_c_choose_5.doStartTag();
                            if (_jspx_eval_c_choose_5 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                              out.write('\n');
                              out.write('	');
                              //  c:when
                              com.liferay.taglib.core.WhenTag _jspx_th_c_when_5 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
                              _jspx_th_c_when_5.setPageContext(_jspx_page_context);
                              _jspx_th_c_when_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_5);
                              _jspx_th_c_when_5.setTest( group.isCompany() && GroupPermissionUtil.contains(permissionChecker, group, ActionKeys.PUBLISH_STAGING) );
                              int _jspx_eval_c_when_5 = _jspx_th_c_when_5.doStartTag();
                              if (_jspx_eval_c_when_5 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                              out.write("\n\t\t");
                              //  liferay-ui:icon
                              com.liferay.taglib.ui.IconTag _jspx_th_liferay$1ui_icon_1 = (com.liferay.taglib.ui.IconTag) _jspx_tagPool_liferay$1ui_icon_url_message_id_nobody.get(com.liferay.taglib.ui.IconTag.class);
                              _jspx_th_liferay$1ui_icon_1.setPageContext(_jspx_page_context);
                              _jspx_th_liferay$1ui_icon_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_5);
                              _jspx_th_liferay$1ui_icon_1.setId( groupId + "publishGlobalLink" );
                              _jspx_th_liferay$1ui_icon_1.setMessage( publishDialogTitle );
                              _jspx_th_liferay$1ui_icon_1.setUrl( publishRenderURL.toString() );
                              int _jspx_eval_liferay$1ui_icon_1 = _jspx_th_liferay$1ui_icon_1.doStartTag();
                              if (_jspx_th_liferay$1ui_icon_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_liferay$1ui_icon_url_message_id_nobody.reuse(_jspx_th_liferay$1ui_icon_1);
                              return;
                              }
                              _jspx_tagPool_liferay$1ui_icon_url_message_id_nobody.reuse(_jspx_th_liferay$1ui_icon_1);
                              out.write("\n\n\t\t");
                              //  aui:script
                              com.liferay.taglib.aui.ScriptTag _jspx_th_aui_script_3 = (com.liferay.taglib.aui.ScriptTag) _jspx_tagPool_aui_script_sandbox_position.get(com.liferay.taglib.aui.ScriptTag.class);
                              _jspx_th_aui_script_3.setPageContext(_jspx_page_context);
                              _jspx_th_aui_script_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_5);
                              _jspx_th_aui_script_3.setPosition("inline");
                              _jspx_th_aui_script_3.setSandbox( true );
                              int _jspx_eval_aui_script_3 = _jspx_th_aui_script_3.doStartTag();
                              if (_jspx_eval_aui_script_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                              if (_jspx_eval_aui_script_3 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                              out = _jspx_page_context.pushBody();
                              _jspx_th_aui_script_3.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                              _jspx_th_aui_script_3.doInitBody();
                              }
                              do {
                              out.write("\n\t\t\tvar publishGlobalLink = $('#");
                              if (_jspx_meth_portlet_namespace_4((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_3, _jspx_page_context))
                              return;
                              out.print( groupId + "publishGlobalLink" );
                              out.write("');\n\n\t\t\tpublishGlobalLink.on(\n\t\t\t\t'click',\n\t\t\t\tfunction(event) {\n\t\t\t\t\tevent.preventDefault();\n\n\t\t\t\t\tLiferay.LayoutExporter.publishToLive(\n\t\t\t\t\t\t{\n\t\t\t\t\t\t\ttitle: '");
                              out.print( UnicodeFormatter.toString(publishMessage) );
                              out.write("',\n\t\t\t\t\t\t\turl: publishGlobalLink.attr('href')\n\t\t\t\t\t\t}\n\t\t\t\t\t);\n\t\t\t\t}\n\t\t\t);\n\t\t");
                              int evalDoAfterBody = _jspx_th_aui_script_3.doAfterBody();
                              if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                              break;
                              } while (true);
                              if (_jspx_eval_aui_script_3 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                              out = _jspx_page_context.popBody();
                              }
                              if (_jspx_th_aui_script_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_aui_script_sandbox_position.reuse(_jspx_th_aui_script_3);
                              return;
                              }
                              _jspx_tagPool_aui_script_sandbox_position.reuse(_jspx_th_aui_script_3);
                              out.write('\n');
                              out.write('	');
                              }
                              if (_jspx_th_c_when_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_5);
                              return;
                              }
                              _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_5);
                              out.write('\n');
                              out.write('	');
                              //  c:otherwise
                              com.liferay.taglib.core.OtherwiseTag _jspx_th_c_otherwise_5 = (com.liferay.taglib.core.OtherwiseTag) _jspx_tagPool_c_otherwise.get(com.liferay.taglib.core.OtherwiseTag.class);
                              _jspx_th_c_otherwise_5.setPageContext(_jspx_page_context);
                              _jspx_th_c_otherwise_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_5);
                              int _jspx_eval_c_otherwise_5 = _jspx_th_c_otherwise_5.doStartTag();
                              if (_jspx_eval_c_otherwise_5 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                              out.write("\n\n\t\t");

		Group groupType = layout.getGroup();
		
                              out.write("\n\n\t\t");
                              //  c:if
                              com.liferay.taglib.core.IfTag _jspx_th_c_if_9 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                              _jspx_th_c_if_9.setPageContext(_jspx_page_context);
                              _jspx_th_c_if_9.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_5);
                              _jspx_th_c_if_9.setTest( (liveGroup != null) && GroupPermissionUtil.contains(permissionChecker, liveGroup, ActionKeys.PUBLISH_STAGING) );
                              int _jspx_eval_c_if_9 = _jspx_th_c_if_9.doStartTag();
                              if (_jspx_eval_c_if_9 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                              out.write("\n\t\t\t");
                              //  liferay-portlet:renderURL
                              com.liferay.taglib.portlet.RenderURLTag _jspx_th_liferay$1portlet_renderURL_3 = (com.liferay.taglib.portlet.RenderURLTag) _jspx_tagPool_liferay$1portlet_renderURL_windowState_var_portletName.get(com.liferay.taglib.portlet.RenderURLTag.class);
                              _jspx_th_liferay$1portlet_renderURL_3.setPageContext(_jspx_page_context);
                              _jspx_th_liferay$1portlet_renderURL_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_9);
                              _jspx_th_liferay$1portlet_renderURL_3.setPortletName( PortletKeys.EXPORT_IMPORT );
                              _jspx_th_liferay$1portlet_renderURL_3.setVar("publishRedirectURL");
                              _jspx_th_liferay$1portlet_renderURL_3.setWindowState( LiferayWindowState.POP_UP.toString() );
                              int _jspx_eval_liferay$1portlet_renderURL_3 = _jspx_th_liferay$1portlet_renderURL_3.doStartTag();
                              if (_jspx_eval_liferay$1portlet_renderURL_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                              out.write("\n\t\t\t\t");
                              if (_jspx_meth_portlet_param_26((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1portlet_renderURL_3, _jspx_page_context))
                              return;
                              out.write("\n\t\t\t\t");
                              //  portlet:param
                              com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_27 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                              _jspx_th_portlet_param_27.setPageContext(_jspx_page_context);
                              _jspx_th_portlet_param_27.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_3);
                              _jspx_th_portlet_param_27.setName( Constants.CMD );
                              _jspx_th_portlet_param_27.setValue( localPublishing ? Constants.PUBLISH_TO_LIVE : Constants.PUBLISH_TO_REMOTE );
                              int _jspx_eval_portlet_param_27 = _jspx_th_portlet_param_27.doStartTag();
                              if (_jspx_th_portlet_param_27.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_27);
                              return;
                              }
                              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_27);
                              out.write("\n\t\t\t\t");
                              if (_jspx_meth_portlet_param_28((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1portlet_renderURL_3, _jspx_page_context))
                              return;
                              out.write("\n\t\t\t\t");
                              //  portlet:param
                              com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_29 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                              _jspx_th_portlet_param_29.setPageContext(_jspx_page_context);
                              _jspx_th_portlet_param_29.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_3);
                              _jspx_th_portlet_param_29.setName("groupId");
                              _jspx_th_portlet_param_29.setValue( String.valueOf(groupId) );
                              int _jspx_eval_portlet_param_29 = _jspx_th_portlet_param_29.doStartTag();
                              if (_jspx_th_portlet_param_29.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_29);
                              return;
                              }
                              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_29);
                              out.write("\n\t\t\t\t");
                              //  portlet:param
                              com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_30 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                              _jspx_th_portlet_param_30.setPageContext(_jspx_page_context);
                              _jspx_th_portlet_param_30.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_3);
                              _jspx_th_portlet_param_30.setName("privateLayout");
                              _jspx_th_portlet_param_30.setValue( String.valueOf(privateLayout) );
                              int _jspx_eval_portlet_param_30 = _jspx_th_portlet_param_30.doStartTag();
                              if (_jspx_th_portlet_param_30.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_30);
                              return;
                              }
                              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_30);
                              out.write("\n\t\t\t\t");
                              //  portlet:param
                              com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_31 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                              _jspx_th_portlet_param_31.setPageContext(_jspx_page_context);
                              _jspx_th_portlet_param_31.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_3);
                              _jspx_th_portlet_param_31.setName("quickPublish");
                              _jspx_th_portlet_param_31.setValue( Boolean.TRUE.toString() );
                              int _jspx_eval_portlet_param_31 = _jspx_th_portlet_param_31.doStartTag();
                              if (_jspx_th_portlet_param_31.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_31);
                              return;
                              }
                              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_31);
                              out.write("\n\t\t\t");
                              }
                              if (_jspx_th_liferay$1portlet_renderURL_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_liferay$1portlet_renderURL_windowState_var_portletName.reuse(_jspx_th_liferay$1portlet_renderURL_3);
                              return;
                              }
                              _jspx_tagPool_liferay$1portlet_renderURL_windowState_var_portletName.reuse(_jspx_th_liferay$1portlet_renderURL_3);
                              java.lang.String publishRedirectURL = null;
                              publishRedirectURL = (java.lang.String) _jspx_page_context.findAttribute("publishRedirectURL");
                              out.write("\n\n\t\t\t");

			UnicodeProperties liveGroupTypeSettings = liveGroup.getTypeSettingsProperties();
			
                              out.write("\n\n\t\t\t");
                              //  liferay-portlet:renderURL
                              com.liferay.taglib.portlet.RenderURLTag _jspx_th_liferay$1portlet_renderURL_4 = (com.liferay.taglib.portlet.RenderURLTag) _jspx_tagPool_liferay$1portlet_renderURL_windowState_varImpl_portletName.get(com.liferay.taglib.portlet.RenderURLTag.class);
                              _jspx_th_liferay$1portlet_renderURL_4.setPageContext(_jspx_page_context);
                              _jspx_th_liferay$1portlet_renderURL_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_9);
                              _jspx_th_liferay$1portlet_renderURL_4.setPortletName( PortletKeys.EXPORT_IMPORT );
                              _jspx_th_liferay$1portlet_renderURL_4.setVarImpl("simplePublishLayoutsURL");
                              _jspx_th_liferay$1portlet_renderURL_4.setWindowState( LiferayWindowState.POP_UP.toString() );
                              int _jspx_eval_liferay$1portlet_renderURL_4 = _jspx_th_liferay$1portlet_renderURL_4.doStartTag();
                              if (_jspx_eval_liferay$1portlet_renderURL_4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                              out.write("\n\t\t\t\t");
                              if (_jspx_meth_portlet_param_32((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1portlet_renderURL_4, _jspx_page_context))
                              return;
                              out.write("\n\t\t\t\t");
                              //  portlet:param
                              com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_33 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                              _jspx_th_portlet_param_33.setPageContext(_jspx_page_context);
                              _jspx_th_portlet_param_33.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_4);
                              _jspx_th_portlet_param_33.setName( Constants.CMD );
                              _jspx_th_portlet_param_33.setValue( localPublishing ? Constants.PUBLISH_TO_LIVE : Constants.PUBLISH_TO_REMOTE );
                              int _jspx_eval_portlet_param_33 = _jspx_th_portlet_param_33.doStartTag();
                              if (_jspx_th_portlet_param_33.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_33);
                              return;
                              }
                              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_33);
                              out.write("\n\t\t\t\t");
                              //  portlet:param
                              com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_34 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                              _jspx_th_portlet_param_34.setPageContext(_jspx_page_context);
                              _jspx_th_portlet_param_34.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_4);
                              _jspx_th_portlet_param_34.setName("redirect");
                              _jspx_th_portlet_param_34.setValue( publishRedirectURL );
                              int _jspx_eval_portlet_param_34 = _jspx_th_portlet_param_34.doStartTag();
                              if (_jspx_th_portlet_param_34.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_34);
                              return;
                              }
                              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_34);
                              out.write("\n\t\t\t\t");
                              //  portlet:param
                              com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_35 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                              _jspx_th_portlet_param_35.setPageContext(_jspx_page_context);
                              _jspx_th_portlet_param_35.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_4);
                              _jspx_th_portlet_param_35.setName("backURL");
                              _jspx_th_portlet_param_35.setValue( currentURL );
                              int _jspx_eval_portlet_param_35 = _jspx_th_portlet_param_35.doStartTag();
                              if (_jspx_th_portlet_param_35.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_35);
                              return;
                              }
                              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_35);
                              out.write("\n\t\t\t\t");
                              //  portlet:param
                              com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_36 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                              _jspx_th_portlet_param_36.setPageContext(_jspx_page_context);
                              _jspx_th_portlet_param_36.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_4);
                              _jspx_th_portlet_param_36.setName("lastImportUserName");
                              _jspx_th_portlet_param_36.setValue( user.getFullName() );
                              int _jspx_eval_portlet_param_36 = _jspx_th_portlet_param_36.doStartTag();
                              if (_jspx_th_portlet_param_36.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_36);
                              return;
                              }
                              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_36);
                              out.write("\n\t\t\t\t");
                              //  portlet:param
                              com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_37 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                              _jspx_th_portlet_param_37.setPageContext(_jspx_page_context);
                              _jspx_th_portlet_param_37.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_4);
                              _jspx_th_portlet_param_37.setName("lastImportUserUuid");
                              _jspx_th_portlet_param_37.setValue( String.valueOf(user.getUserUuid()) );
                              int _jspx_eval_portlet_param_37 = _jspx_th_portlet_param_37.doStartTag();
                              if (_jspx_th_portlet_param_37.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_37);
                              return;
                              }
                              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_37);
                              out.write("\n\t\t\t\t");
                              //  portlet:param
                              com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_38 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                              _jspx_th_portlet_param_38.setPageContext(_jspx_page_context);
                              _jspx_th_portlet_param_38.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_4);
                              _jspx_th_portlet_param_38.setName("localPublishing");
                              _jspx_th_portlet_param_38.setValue( String.valueOf(localPublishing) );
                              int _jspx_eval_portlet_param_38 = _jspx_th_portlet_param_38.doStartTag();
                              if (_jspx_th_portlet_param_38.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_38);
                              return;
                              }
                              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_38);
                              out.write("\n\t\t\t\t");
                              //  portlet:param
                              com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_39 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                              _jspx_th_portlet_param_39.setPageContext(_jspx_page_context);
                              _jspx_th_portlet_param_39.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_4);
                              _jspx_th_portlet_param_39.setName("privateLayout");
                              _jspx_th_portlet_param_39.setValue( String.valueOf(privateLayout) );
                              int _jspx_eval_portlet_param_39 = _jspx_th_portlet_param_39.doStartTag();
                              if (_jspx_th_portlet_param_39.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_39);
                              return;
                              }
                              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_39);
                              out.write("\n\t\t\t\t");
                              //  portlet:param
                              com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_40 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                              _jspx_th_portlet_param_40.setPageContext(_jspx_page_context);
                              _jspx_th_portlet_param_40.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_4);
                              _jspx_th_portlet_param_40.setName("quickPublish");
                              _jspx_th_portlet_param_40.setValue( Boolean.TRUE.toString() );
                              int _jspx_eval_portlet_param_40 = _jspx_th_portlet_param_40.doStartTag();
                              if (_jspx_th_portlet_param_40.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_40);
                              return;
                              }
                              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_40);
                              out.write("\n\t\t\t\t");
                              //  portlet:param
                              com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_41 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                              _jspx_th_portlet_param_41.setPageContext(_jspx_page_context);
                              _jspx_th_portlet_param_41.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_4);
                              _jspx_th_portlet_param_41.setName("remoteAddress");
                              _jspx_th_portlet_param_41.setValue( liveGroupTypeSettings.getProperty("remoteAddress") );
                              int _jspx_eval_portlet_param_41 = _jspx_th_portlet_param_41.doStartTag();
                              if (_jspx_th_portlet_param_41.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_41);
                              return;
                              }
                              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_41);
                              out.write("\n\t\t\t\t");
                              //  portlet:param
                              com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_42 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                              _jspx_th_portlet_param_42.setPageContext(_jspx_page_context);
                              _jspx_th_portlet_param_42.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_4);
                              _jspx_th_portlet_param_42.setName("remotePort");
                              _jspx_th_portlet_param_42.setValue( liveGroupTypeSettings.getProperty("remotePort") );
                              int _jspx_eval_portlet_param_42 = _jspx_th_portlet_param_42.doStartTag();
                              if (_jspx_th_portlet_param_42.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_42);
                              return;
                              }
                              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_42);
                              out.write("\n\t\t\t\t");
                              //  portlet:param
                              com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_43 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                              _jspx_th_portlet_param_43.setPageContext(_jspx_page_context);
                              _jspx_th_portlet_param_43.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_4);
                              _jspx_th_portlet_param_43.setName("remotePathContext");
                              _jspx_th_portlet_param_43.setValue( liveGroupTypeSettings.getProperty("remotePathContext") );
                              int _jspx_eval_portlet_param_43 = _jspx_th_portlet_param_43.doStartTag();
                              if (_jspx_th_portlet_param_43.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_43);
                              return;
                              }
                              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_43);
                              out.write("\n\t\t\t\t");
                              //  portlet:param
                              com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_44 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                              _jspx_th_portlet_param_44.setPageContext(_jspx_page_context);
                              _jspx_th_portlet_param_44.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_4);
                              _jspx_th_portlet_param_44.setName("remoteGroupId");
                              _jspx_th_portlet_param_44.setValue( liveGroupTypeSettings.getProperty("remoteGroupId") );
                              int _jspx_eval_portlet_param_44 = _jspx_th_portlet_param_44.doStartTag();
                              if (_jspx_th_portlet_param_44.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_44);
                              return;
                              }
                              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_44);
                              out.write("\n\t\t\t\t");
                              //  portlet:param
                              com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_45 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                              _jspx_th_portlet_param_45.setPageContext(_jspx_page_context);
                              _jspx_th_portlet_param_45.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_4);
                              _jspx_th_portlet_param_45.setName("secureConnection");
                              _jspx_th_portlet_param_45.setValue( liveGroupTypeSettings.getProperty("secureConnection") );
                              int _jspx_eval_portlet_param_45 = _jspx_th_portlet_param_45.doStartTag();
                              if (_jspx_th_portlet_param_45.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_45);
                              return;
                              }
                              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_45);
                              out.write("\n\t\t\t\t");
                              //  portlet:param
                              com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_46 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                              _jspx_th_portlet_param_46.setPageContext(_jspx_page_context);
                              _jspx_th_portlet_param_46.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_4);
                              _jspx_th_portlet_param_46.setName("sourceGroupId");
                              _jspx_th_portlet_param_46.setValue( String.valueOf(stagingGroupId) );
                              int _jspx_eval_portlet_param_46 = _jspx_th_portlet_param_46.doStartTag();
                              if (_jspx_th_portlet_param_46.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_46);
                              return;
                              }
                              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_46);
                              out.write("\n\t\t\t\t");
                              //  portlet:param
                              com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_47 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                              _jspx_th_portlet_param_47.setPageContext(_jspx_page_context);
                              _jspx_th_portlet_param_47.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_4);
                              _jspx_th_portlet_param_47.setName("targetGroupId");
                              _jspx_th_portlet_param_47.setValue( String.valueOf(liveGroupId) );
                              int _jspx_eval_portlet_param_47 = _jspx_th_portlet_param_47.doStartTag();
                              if (_jspx_th_portlet_param_47.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_47);
                              return;
                              }
                              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_47);
                              out.write("\n\t\t\t");
                              }
                              if (_jspx_th_liferay$1portlet_renderURL_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_liferay$1portlet_renderURL_windowState_varImpl_portletName.reuse(_jspx_th_liferay$1portlet_renderURL_4);
                              return;
                              }
                              _jspx_tagPool_liferay$1portlet_renderURL_windowState_varImpl_portletName.reuse(_jspx_th_liferay$1portlet_renderURL_4);
                              com.liferay.portal.kernel.portlet.LiferayPortletURL simplePublishLayoutsURL = null;
                              simplePublishLayoutsURL = (com.liferay.portal.kernel.portlet.LiferayPortletURL) _jspx_page_context.findAttribute("simplePublishLayoutsURL");
                              out.write("\n\n\t\t\t");

			if (groupId == 0) {
				simplePublishLayoutsURL.setParameter("selPlid", String.valueOf(plid));

				publishRenderURL.setParameter("selPlid", String.valueOf(plid));
			}
			
                              out.write("\n\n\t\t\t");
                              //  c:choose
                              com.liferay.taglib.core.ChooseTag _jspx_th_c_choose_6 = (com.liferay.taglib.core.ChooseTag) _jspx_tagPool_c_choose.get(com.liferay.taglib.core.ChooseTag.class);
                              _jspx_th_c_choose_6.setPageContext(_jspx_page_context);
                              _jspx_th_c_choose_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_9);
                              int _jspx_eval_c_choose_6 = _jspx_th_c_choose_6.doStartTag();
                              if (_jspx_eval_c_choose_6 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                              out.write("\n\t\t\t\t");
                              //  c:when
                              com.liferay.taglib.core.WhenTag _jspx_th_c_when_6 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
                              _jspx_th_c_when_6.setPageContext(_jspx_page_context);
                              _jspx_th_c_when_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_6);
                              _jspx_th_c_when_6.setTest( (layoutSetBranchId > 0) && (layoutSetBranches.size() > 1) );
                              int _jspx_eval_c_when_6 = _jspx_th_c_when_6.doStartTag();
                              if (_jspx_eval_c_when_6 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                              out.write("\n\n\t\t\t\t\t");

					layoutSetBranch = LayoutSetBranchLocalServiceUtil.getLayoutSetBranch(layoutSetBranchId);

					simplePublishLayoutsURL.setParameter("layoutSetBranchName", layoutSetBranch.getName());
					simplePublishLayoutsURL.setParameter("layoutSetBranchId", String.valueOf(layoutSetBranchId));

					publishRenderURL.setParameter("layoutSetBranchId", String.valueOf(layoutSetBranchId));
					publishRenderURL.setParameter("layoutSetBranchName", layoutSetBranch.getName());

					boolean translateLayoutSetBranchName = LayoutSetBranchConstants.MASTER_BRANCH_NAME.equals(HtmlUtil.escape(layoutSetBranch.getName()));

					publishMessage = LanguageUtil.format(request, publishDialogTitle, HtmlUtil.escape(layoutSetBranch.getName()), translateLayoutSetBranchName);
					
                              out.write("\n\n\t\t\t\t");
                              }
                              if (_jspx_th_c_when_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_6);
                              return;
                              }
                              _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_6);
                              out.write("\n\t\t\t\t");
                              //  c:otherwise
                              com.liferay.taglib.core.OtherwiseTag _jspx_th_c_otherwise_6 = (com.liferay.taglib.core.OtherwiseTag) _jspx_tagPool_c_otherwise.get(com.liferay.taglib.core.OtherwiseTag.class);
                              _jspx_th_c_otherwise_6.setPageContext(_jspx_page_context);
                              _jspx_th_c_otherwise_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_6);
                              int _jspx_eval_c_otherwise_6 = _jspx_th_c_otherwise_6.doStartTag();
                              if (_jspx_eval_c_otherwise_6 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                              out.write("\n\t\t\t\t\t");
                              //  c:if
                              com.liferay.taglib.core.IfTag _jspx_th_c_if_10 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                              _jspx_th_c_if_10.setPageContext(_jspx_page_context);
                              _jspx_th_c_if_10.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_6);
                              _jspx_th_c_if_10.setTest( layoutSetBranches.size() == 1 );
                              int _jspx_eval_c_if_10 = _jspx_th_c_if_10.doStartTag();
                              if (_jspx_eval_c_if_10 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                              out.write("\n\n\t\t\t\t\t\t");

						layoutSetBranch = layoutSetBranches.get(0);

						simplePublishLayoutsURL.setParameter("layoutSetBranchId", String.valueOf(layoutSetBranch.getLayoutSetBranchId()));

						publishRenderURL.setParameter("layoutSetBranchId", String.valueOf(layoutSetBranch.getLayoutSetBranchId()));
						
                              out.write("\n\n\t\t\t\t\t");
                              }
                              if (_jspx_th_c_if_10.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_10);
                              return;
                              }
                              _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_10);
                              out.write("\n\t\t\t\t");
                              }
                              if (_jspx_th_c_otherwise_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_6);
                              return;
                              }
                              _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_6);
                              out.write("\n\t\t\t");
                              }
                              if (_jspx_th_c_choose_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_6);
                              return;
                              }
                              _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_6);
                              out.write("\n\n\t\t\t");

			String linkCssClass = StringPool.BLANK;

			if (!groupType.isControlPanel()) {
				linkCssClass = "btn btn-default btn-sm";
			}
			
                              out.write("\n\n\t\t\t");
                              //  liferay-util:buffer
                              com.liferay.taglib.util.BufferTag _jspx_th_liferay$1util_buffer_3 = (com.liferay.taglib.util.BufferTag) _jspx_tagPool_liferay$1util_buffer_var.get(com.liferay.taglib.util.BufferTag.class);
                              _jspx_th_liferay$1util_buffer_3.setPageContext(_jspx_page_context);
                              _jspx_th_liferay$1util_buffer_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_9);
                              _jspx_th_liferay$1util_buffer_3.setVar("publishOnControlPanelLink");
                              int _jspx_eval_liferay$1util_buffer_3 = _jspx_th_liferay$1util_buffer_3.doStartTag();
                              if (_jspx_eval_liferay$1util_buffer_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                              if (_jspx_eval_liferay$1util_buffer_3 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                              out = _jspx_page_context.pushBody();
                              _jspx_th_liferay$1util_buffer_3.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                              _jspx_th_liferay$1util_buffer_3.doInitBody();
                              }
                              do {
                              out.write("\n\t\t\t\t");
                              //  aui:a
                              com.liferay.taglib.aui.ATag _jspx_th_aui_a_2 = (com.liferay.taglib.aui.ATag) _jspx_tagPool_aui_a_label_id_href_cssClass_nobody.get(com.liferay.taglib.aui.ATag.class);
                              _jspx_th_aui_a_2.setPageContext(_jspx_page_context);
                              _jspx_th_aui_a_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1util_buffer_3);
                              _jspx_th_aui_a_2.setCssClass( linkCssClass + " publish-link" );
                              _jspx_th_aui_a_2.setHref( publishRenderURL.toString() );
                              _jspx_th_aui_a_2.setId( layoutSetBranchId + "publishLink" );
                              _jspx_th_aui_a_2.setLabel( publishMessage );
                              int _jspx_eval_aui_a_2 = _jspx_th_aui_a_2.doStartTag();
                              if (_jspx_th_aui_a_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_aui_a_label_id_href_cssClass_nobody.reuse(_jspx_th_aui_a_2);
                              return;
                              }
                              _jspx_tagPool_aui_a_label_id_href_cssClass_nobody.reuse(_jspx_th_aui_a_2);
                              out.write("\n\t\t\t");
                              int evalDoAfterBody = _jspx_th_liferay$1util_buffer_3.doAfterBody();
                              if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                              break;
                              } while (true);
                              if (_jspx_eval_liferay$1util_buffer_3 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                              out = _jspx_page_context.popBody();
                              }
                              if (_jspx_th_liferay$1util_buffer_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_liferay$1util_buffer_var.reuse(_jspx_th_liferay$1util_buffer_3);
                              return;
                              }
                              _jspx_tagPool_liferay$1util_buffer_var.reuse(_jspx_th_liferay$1util_buffer_3);
                              java.lang.String publishOnControlPanelLink = null;
                              publishOnControlPanelLink = (java.lang.String) _jspx_page_context.findAttribute("publishOnControlPanelLink");
                              out.write("\n\n\t\t\t");
                              //  liferay-util:buffer
                              com.liferay.taglib.util.BufferTag _jspx_th_liferay$1util_buffer_4 = (com.liferay.taglib.util.BufferTag) _jspx_tagPool_liferay$1util_buffer_var.get(com.liferay.taglib.util.BufferTag.class);
                              _jspx_th_liferay$1util_buffer_4.setPageContext(_jspx_page_context);
                              _jspx_th_liferay$1util_buffer_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_9);
                              _jspx_th_liferay$1util_buffer_4.setVar("publishOnLayoutLink");
                              int _jspx_eval_liferay$1util_buffer_4 = _jspx_th_liferay$1util_buffer_4.doStartTag();
                              if (_jspx_eval_liferay$1util_buffer_4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                              if (_jspx_eval_liferay$1util_buffer_4 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                              out = _jspx_page_context.pushBody();
                              _jspx_th_liferay$1util_buffer_4.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                              _jspx_th_liferay$1util_buffer_4.doInitBody();
                              }
                              do {
                              out.write("\n\t\t\t\t<li class=\"control-menu-nav-item\">\n\t\t\t\t\t<div class=\"dropdown publish-btn-group\">\n\t\t\t\t\t\t<button class=\"btn btn-primary btn-sm\" href=\"");
                              out.print( simplePublishLayoutsURL.toString() );
                              out.write("\" id=\"");
                              out.print( layoutSetBranchId + "publishToLiveLink" );
                              out.write("\" type=\"button\">\n\t\t\t\t\t\t\t");
                              if (_jspx_meth_liferay$1ui_message_5((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1util_buffer_4, _jspx_page_context))
                              return;
                              out.write("\n\t\t\t\t\t\t</button>\n\n\t\t\t\t\t\t");
                              //  c:if
                              com.liferay.taglib.core.IfTag _jspx_th_c_if_11 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                              _jspx_th_c_if_11.setPageContext(_jspx_page_context);
                              _jspx_th_c_if_11.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1util_buffer_4);
                              _jspx_th_c_if_11.setTest( branchingEnabled );
                              int _jspx_eval_c_if_11 = _jspx_th_c_if_11.doStartTag();
                              if (_jspx_eval_c_if_11 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                              out.write("\n\t\t\t\t\t\t\t<button aria-expanded=\"false\" class=\"btn btn-primary btn-sm dropdown-toggle visible-xs\" data-toggle=\"dropdown\" type=\"button\">\n\t\t\t\t\t\t\t\t");
                              if (_jspx_meth_aui_icon_1((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_11, _jspx_page_context))
                              return;
                              out.write("\n\n\t\t\t\t\t\t\t\t<span class=\"sr-only\">\n\t\t\t\t\t\t\t\t\t");
                              if (_jspx_meth_liferay$1ui_message_6((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_11, _jspx_page_context))
                              return;
                              out.write("\n\t\t\t\t\t\t\t\t</span>\n\t\t\t\t\t\t\t</button>\n\n\t\t\t\t\t\t\t<ul class=\"dropdown-menu\" role=\"menu\">\n\t\t\t\t\t\t\t\t");
                              //  c:if
                              com.liferay.taglib.core.IfTag _jspx_th_c_if_12 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                              _jspx_th_c_if_12.setPageContext(_jspx_page_context);
                              _jspx_th_c_if_12.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_11);
                              _jspx_th_c_if_12.setTest( !layoutRevision.isIncomplete() );
                              int _jspx_eval_c_if_12 = _jspx_th_c_if_12.doStartTag();
                              if (_jspx_eval_c_if_12 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                              out.write("\n\t\t\t\t\t\t\t\t\t<li>\n\t\t\t\t\t\t\t\t\t\t<a href=\"javascript:Liferay.fire('");
                              out.print( liferayPortletResponse.getNamespace() );
                              out.write("viewHistory', {layoutRevisionId: '");
                              out.print( layoutRevision.getLayoutRevisionId() );
                              out.write("', layoutSetBranchId: '");
                              out.print( layoutRevision.getLayoutSetBranchId() );
                              out.write("'}); void(0);\">\n\t\t\t\t\t\t\t\t\t\t\t");
                              if (_jspx_meth_liferay$1ui_message_7((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_12, _jspx_page_context))
                              return;
                              out.write("\n\t\t\t\t\t\t\t\t\t\t</a>\n\t\t\t\t\t\t\t\t\t</li>\n\t\t\t\t\t\t\t\t");
                              }
                              if (_jspx_th_c_if_12.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_12);
                              return;
                              }
                              _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_12);
                              out.write("\n\n\t\t\t\t\t\t\t\t");
                              //  c:if
                              com.liferay.taglib.core.IfTag _jspx_th_c_if_13 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                              _jspx_th_c_if_13.setPageContext(_jspx_page_context);
                              _jspx_th_c_if_13.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_11);
                              _jspx_th_c_if_13.setTest( !hasWorkflowTask );
                              int _jspx_eval_c_if_13 = _jspx_th_c_if_13.doStartTag();
                              if (_jspx_eval_c_if_13 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                              out.write("\n\t\t\t\t\t\t\t\t\t");
                              //  c:if
                              com.liferay.taglib.core.IfTag _jspx_th_c_if_14 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                              _jspx_th_c_if_14.setPageContext(_jspx_page_context);
                              _jspx_th_c_if_14.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_13);
                              _jspx_th_c_if_14.setTest( !layoutRevision.isMajor() && (layoutRevision.getParentLayoutRevisionId() != LayoutRevisionConstants.DEFAULT_PARENT_LAYOUT_REVISION_ID) );
                              int _jspx_eval_c_if_14 = _jspx_th_c_if_14.doStartTag();
                              if (_jspx_eval_c_if_14 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                              out.write("\n\t\t\t\t\t\t\t\t\t\t<li>\n\t\t\t\t\t\t\t\t\t\t\t<a href=\"javascript:Liferay.fire('");
                              out.print( liferayPortletResponse.getNamespace() );
                              out.write("undo', {layoutRevisionId: '");
                              out.print( layoutRevision.getLayoutRevisionId() );
                              out.write("', layoutSetBranchId: '");
                              out.print( layoutRevision.getLayoutSetBranchId() );
                              out.write("'}); void(0);\">\n\t\t\t\t\t\t\t\t\t\t\t\t");
                              if (_jspx_meth_liferay$1ui_message_8((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_14, _jspx_page_context))
                              return;
                              out.write("\n\t\t\t\t\t\t\t\t\t\t\t</a>\n\t\t\t\t\t\t\t\t\t\t</li>\n\t\t\t\t\t\t\t\t\t");
                              }
                              if (_jspx_th_c_if_14.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_14);
                              return;
                              }
                              _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_14);
                              out.write("\n\n\t\t\t\t\t\t\t\t\t");
                              //  c:if
                              com.liferay.taglib.core.IfTag _jspx_th_c_if_15 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                              _jspx_th_c_if_15.setPageContext(_jspx_page_context);
                              _jspx_th_c_if_15.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_13);
                              _jspx_th_c_if_15.setTest( layoutRevision.hasChildren() );
                              int _jspx_eval_c_if_15 = _jspx_th_c_if_15.doStartTag();
                              if (_jspx_eval_c_if_15 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                              out.write("\n\n\t\t\t\t\t\t\t\t\t\t");

										List<LayoutRevision> childLayoutRevisions = layoutRevision.getChildren();

										LayoutRevision firstChildLayoutRevision = childLayoutRevisions.get(0);

										if (firstChildLayoutRevision.isInactive()) {
										
                              out.write("\n\n\t\t\t\t\t\t\t\t\t\t\t<li>\n\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"javascript:Liferay.fire('");
                              out.print( liferayPortletResponse.getNamespace() );
                              out.write("redo', {layoutRevisionId: '");
                              out.print( firstChildLayoutRevision.getLayoutRevisionId() );
                              out.write("', layoutSetBranchId: '");
                              out.print( firstChildLayoutRevision.getLayoutSetBranchId() );
                              out.write("'}); void(0);\">\n\t\t\t\t\t\t\t\t\t\t\t\t\t");
                              if (_jspx_meth_liferay$1ui_message_9((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_15, _jspx_page_context))
                              return;
                              out.write("\n\t\t\t\t\t\t\t\t\t\t\t\t</a>\n\t\t\t\t\t\t\t\t\t\t\t</li>\n\n\t\t\t\t\t\t\t\t\t\t");

										}
										
                              out.write("\n\n\t\t\t\t\t\t\t\t\t");
                              }
                              if (_jspx_th_c_if_15.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_15);
                              return;
                              }
                              _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_15);
                              out.write("\n\t\t\t\t\t\t\t\t");
                              }
                              if (_jspx_th_c_if_13.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_13);
                              return;
                              }
                              _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_13);
                              out.write("\n\t\t\t\t\t\t\t</ul>\n\t\t\t\t\t\t");
                              }
                              if (_jspx_th_c_if_11.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_11);
                              return;
                              }
                              _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_11);
                              out.write("\n\t\t\t\t\t</div>\n\t\t\t\t</li>\n\t\t\t");
                              int evalDoAfterBody = _jspx_th_liferay$1util_buffer_4.doAfterBody();
                              if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                              break;
                              } while (true);
                              if (_jspx_eval_liferay$1util_buffer_4 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                              out = _jspx_page_context.popBody();
                              }
                              if (_jspx_th_liferay$1util_buffer_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_liferay$1util_buffer_var.reuse(_jspx_th_liferay$1util_buffer_4);
                              return;
                              }
                              _jspx_tagPool_liferay$1util_buffer_var.reuse(_jspx_th_liferay$1util_buffer_4);
                              java.lang.String publishOnLayoutLink = null;
                              publishOnLayoutLink = (java.lang.String) _jspx_page_context.findAttribute("publishOnLayoutLink");
                              out.write("\n\n\t\t\t");
                              //  c:choose
                              com.liferay.taglib.core.ChooseTag _jspx_th_c_choose_7 = (com.liferay.taglib.core.ChooseTag) _jspx_tagPool_c_choose.get(com.liferay.taglib.core.ChooseTag.class);
                              _jspx_th_c_choose_7.setPageContext(_jspx_page_context);
                              _jspx_th_c_choose_7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_9);
                              int _jspx_eval_c_choose_7 = _jspx_th_c_choose_7.doStartTag();
                              if (_jspx_eval_c_choose_7 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                              out.write("\n\t\t\t\t");
                              //  c:when
                              com.liferay.taglib.core.WhenTag _jspx_th_c_when_7 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
                              _jspx_th_c_when_7.setPageContext(_jspx_page_context);
                              _jspx_th_c_when_7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_7);
                              _jspx_th_c_when_7.setTest( !onlyActions );
                              int _jspx_eval_c_when_7 = _jspx_th_c_when_7.doStartTag();
                              if (_jspx_eval_c_when_7 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                              out.write("\n\t\t\t\t\t<li class=\"publish-link-container\">\n\t\t\t\t\t\t");
                              out.print( publishOnControlPanelLink );
                              out.write("\n\t\t\t\t\t</li>\n\t\t\t\t");
                              }
                              if (_jspx_th_c_when_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_7);
                              return;
                              }
                              _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_7);
                              out.write("\n\t\t\t\t");
                              //  c:otherwise
                              com.liferay.taglib.core.OtherwiseTag _jspx_th_c_otherwise_7 = (com.liferay.taglib.core.OtherwiseTag) _jspx_tagPool_c_otherwise.get(com.liferay.taglib.core.OtherwiseTag.class);
                              _jspx_th_c_otherwise_7.setPageContext(_jspx_page_context);
                              _jspx_th_c_otherwise_7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_7);
                              int _jspx_eval_c_otherwise_7 = _jspx_th_c_otherwise_7.doStartTag();
                              if (_jspx_eval_c_otherwise_7 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                              out.write("\n\t\t\t\t\t");
                              out.print( publishOnLayoutLink );
                              out.write("\n\t\t\t\t");
                              }
                              if (_jspx_th_c_otherwise_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_7);
                              return;
                              }
                              _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_7);
                              out.write("\n\t\t\t");
                              }
                              if (_jspx_th_c_choose_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_7);
                              return;
                              }
                              _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_7);
                              out.write("\n\n\t\t\t");
                              //  aui:script
                              com.liferay.taglib.aui.ScriptTag _jspx_th_aui_script_4 = (com.liferay.taglib.aui.ScriptTag) _jspx_tagPool_aui_script_sandbox_position.get(com.liferay.taglib.aui.ScriptTag.class);
                              _jspx_th_aui_script_4.setPageContext(_jspx_page_context);
                              _jspx_th_aui_script_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_9);
                              _jspx_th_aui_script_4.setPosition("inline");
                              _jspx_th_aui_script_4.setSandbox( true );
                              int _jspx_eval_aui_script_4 = _jspx_th_aui_script_4.doStartTag();
                              if (_jspx_eval_aui_script_4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                              if (_jspx_eval_aui_script_4 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                              out = _jspx_page_context.pushBody();
                              _jspx_th_aui_script_4.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                              _jspx_th_aui_script_4.doInitBody();
                              }
                              do {
                              out.write("\n\t\t\t\tvar publishLink = $('#");
                              out.print( layoutSetBranchId + "publishLink" );
                              out.write("');\n\n\t\t\t\tpublishLink.on(\n\t\t\t\t\t'click',\n\t\t\t\t\tfunction(event) {\n\t\t\t\t\t\tevent.preventDefault();\n\n\t\t\t\t\t\tLiferay.LayoutExporter.publishToLive(\n\t\t\t\t\t\t\t{\n\t\t\t\t\t\t\t\ttitle: '");
                              out.print( UnicodeFormatter.toString(publishMessage) );
                              out.write("',\n\t\t\t\t\t\t\t\turl: publishLink.attr('href')\n\t\t\t\t\t\t\t}\n\t\t\t\t\t\t);\n\t\t\t\t\t}\n\t\t\t\t);\n\n\t\t\t\tvar publishToLiveLink = $('#");
                              out.print( layoutSetBranchId + "publishToLiveLink" );
                              out.write("');\n\n\t\t\t\tpublishToLiveLink.on(\n\t\t\t\t\t'click',\n\t\t\t\t\tfunction(event) {\n\t\t\t\t\t\tevent.preventDefault();\n\n\t\t\t\t\t\tLiferay.Util.openWindow(\n\t\t\t\t\t\t\t{\n\t\t\t\t\t\t\t\tdialog: {\n\t\t\t\t\t\t\t\t\tafter: {\n\t\t\t\t\t\t\t\t\t\tdestroy: function(event) {\n\t\t\t\t\t\t\t\t\t\t\twindow.location.reload();\n\t\t\t\t\t\t\t\t\t\t}\n\t\t\t\t\t\t\t\t\t},\n\t\t\t\t\t\t\t\t\tdestroyOnHide: true\n\t\t\t\t\t\t\t\t},\n\t\t\t\t\t\t\t\tdialogIframe: {\n\t\t\t\t\t\t\t\t\tbodyCssClass: 'dialog-with-footer'\n\t\t\t\t\t\t\t\t},\n\t\t\t\t\t\t\t\tid: '");
                              if (_jspx_meth_portlet_namespace_5((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_4, _jspx_page_context))
                              return;
                              out.write("publishLatestChangesDialog',\n\t\t\t\t\t\t\t\ttitle: '");
                              out.print( UnicodeFormatter.toString(publishMessage) );
                              out.write("',\n\t\t\t\t\t\t\t\turi: publishToLiveLink.attr('href')\n\t\t\t\t\t\t\t}\n\t\t\t\t\t\t);\n\t\t\t\t\t}\n\t\t\t\t);\n\t\t\t");
                              int evalDoAfterBody = _jspx_th_aui_script_4.doAfterBody();
                              if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                              break;
                              } while (true);
                              if (_jspx_eval_aui_script_4 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                              out = _jspx_page_context.popBody();
                              }
                              if (_jspx_th_aui_script_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_aui_script_sandbox_position.reuse(_jspx_th_aui_script_4);
                              return;
                              }
                              _jspx_tagPool_aui_script_sandbox_position.reuse(_jspx_th_aui_script_4);
                              out.write("\n\t\t");
                              }
                              if (_jspx_th_c_if_9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_9);
                              return;
                              }
                              _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_9);
                              out.write("\n\n\t\t");
                              //  c:if
                              com.liferay.taglib.core.IfTag _jspx_th_c_if_16 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                              _jspx_th_c_if_16.setPageContext(_jspx_page_context);
                              _jspx_th_c_if_16.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_5);
                              _jspx_th_c_if_16.setTest( showManageBranches && !layoutSetBranches.isEmpty() );
                              int _jspx_eval_c_if_16 = _jspx_th_c_if_16.doStartTag();
                              if (_jspx_eval_c_if_16 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                              out.write("\n\t\t\t");
                              //  portlet:renderURL
                              com.liferay.taglib.portlet.RenderURLTag _jspx_th_portlet_renderURL_1 = (com.liferay.taglib.portlet.RenderURLTag) _jspx_tagPool_portlet_renderURL_windowState_var.get(com.liferay.taglib.portlet.RenderURLTag.class);
                              _jspx_th_portlet_renderURL_1.setPageContext(_jspx_page_context);
                              _jspx_th_portlet_renderURL_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_16);
                              _jspx_th_portlet_renderURL_1.setVar("layoutSetBranchesURL");
                              _jspx_th_portlet_renderURL_1.setWindowState( LiferayWindowState.POP_UP.toString() );
                              int _jspx_eval_portlet_renderURL_1 = _jspx_th_portlet_renderURL_1.doStartTag();
                              if (_jspx_eval_portlet_renderURL_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                              out.write("\n\t\t\t\t");
                              if (_jspx_meth_portlet_param_48((javax.servlet.jsp.tagext.JspTag) _jspx_th_portlet_renderURL_1, _jspx_page_context))
                              return;
                              out.write("\n\t\t\t\t");
                              //  portlet:param
                              com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_49 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                              _jspx_th_portlet_param_49.setPageContext(_jspx_page_context);
                              _jspx_th_portlet_param_49.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_1);
                              _jspx_th_portlet_param_49.setName("groupId");
                              _jspx_th_portlet_param_49.setValue( String.valueOf(groupId) );
                              int _jspx_eval_portlet_param_49 = _jspx_th_portlet_param_49.doStartTag();
                              if (_jspx_th_portlet_param_49.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_49);
                              return;
                              }
                              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_49);
                              out.write("\n\t\t\t\t");
                              //  portlet:param
                              com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_50 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                              _jspx_th_portlet_param_50.setPageContext(_jspx_page_context);
                              _jspx_th_portlet_param_50.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_1);
                              _jspx_th_portlet_param_50.setName("privateLayout");
                              _jspx_th_portlet_param_50.setValue( String.valueOf(privateLayout) );
                              int _jspx_eval_portlet_param_50 = _jspx_th_portlet_param_50.doStartTag();
                              if (_jspx_th_portlet_param_50.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_50);
                              return;
                              }
                              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_50);
                              out.write("\n\t\t\t\t");
                              //  portlet:param
                              com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_51 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                              _jspx_th_portlet_param_51.setPageContext(_jspx_page_context);
                              _jspx_th_portlet_param_51.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_1);
                              _jspx_th_portlet_param_51.setName("selPlid");
                              _jspx_th_portlet_param_51.setValue( String.valueOf(selPlid) );
                              int _jspx_eval_portlet_param_51 = _jspx_th_portlet_param_51.doStartTag();
                              if (_jspx_th_portlet_param_51.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_51);
                              return;
                              }
                              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_51);
                              out.write("\n\t\t\t");
                              }
                              if (_jspx_th_portlet_renderURL_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_portlet_renderURL_windowState_var.reuse(_jspx_th_portlet_renderURL_1);
                              return;
                              }
                              _jspx_tagPool_portlet_renderURL_windowState_var.reuse(_jspx_th_portlet_renderURL_1);
                              java.lang.String layoutSetBranchesURL = null;
                              layoutSetBranchesURL = (java.lang.String) _jspx_page_context.findAttribute("layoutSetBranchesURL");
                              out.write("\n\n\t\t\t");
                              //  liferay-util:buffer
                              com.liferay.taglib.util.BufferTag _jspx_th_liferay$1util_buffer_5 = (com.liferay.taglib.util.BufferTag) _jspx_tagPool_liferay$1util_buffer_var.get(com.liferay.taglib.util.BufferTag.class);
                              _jspx_th_liferay$1util_buffer_5.setPageContext(_jspx_page_context);
                              _jspx_th_liferay$1util_buffer_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_16);
                              _jspx_th_liferay$1util_buffer_5.setVar("manageSitePagesVariationsLink");
                              int _jspx_eval_liferay$1util_buffer_5 = _jspx_th_liferay$1util_buffer_5.doStartTag();
                              if (_jspx_eval_liferay$1util_buffer_5 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                              if (_jspx_eval_liferay$1util_buffer_5 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                              out = _jspx_page_context.pushBody();
                              _jspx_th_liferay$1util_buffer_5.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                              _jspx_th_liferay$1util_buffer_5.doInitBody();
                              }
                              do {
                              out.write("\n\t\t\t\t");
                              //  aui:a
                              com.liferay.taglib.aui.ATag _jspx_th_aui_a_3 = (com.liferay.taglib.aui.ATag) _jspx_tagPool_aui_a_label_id_href_cssClass_nobody.get(com.liferay.taglib.aui.ATag.class);
                              _jspx_th_aui_a_3.setPageContext(_jspx_page_context);
                              _jspx_th_aui_a_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1util_buffer_5);
                              _jspx_th_aui_a_3.setCssClass("manage-layout-set-branches");
                              _jspx_th_aui_a_3.setHref( layoutSetBranchesURL );
                              _jspx_th_aui_a_3.setId("manageLayoutSetBranches");
                              _jspx_th_aui_a_3.setLabel("manage-site-pages-variations");
                              int _jspx_eval_aui_a_3 = _jspx_th_aui_a_3.doStartTag();
                              if (_jspx_th_aui_a_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_aui_a_label_id_href_cssClass_nobody.reuse(_jspx_th_aui_a_3);
                              return;
                              }
                              _jspx_tagPool_aui_a_label_id_href_cssClass_nobody.reuse(_jspx_th_aui_a_3);
                              out.write("\n\t\t\t");
                              int evalDoAfterBody = _jspx_th_liferay$1util_buffer_5.doAfterBody();
                              if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                              break;
                              } while (true);
                              if (_jspx_eval_liferay$1util_buffer_5 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                              out = _jspx_page_context.popBody();
                              }
                              if (_jspx_th_liferay$1util_buffer_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_liferay$1util_buffer_var.reuse(_jspx_th_liferay$1util_buffer_5);
                              return;
                              }
                              _jspx_tagPool_liferay$1util_buffer_var.reuse(_jspx_th_liferay$1util_buffer_5);
                              java.lang.String manageSitePagesVariationsLink = null;
                              manageSitePagesVariationsLink = (java.lang.String) _jspx_page_context.findAttribute("manageSitePagesVariationsLink");
                              out.write("\n\n\t\t\t");
                              //  c:choose
                              com.liferay.taglib.core.ChooseTag _jspx_th_c_choose_8 = (com.liferay.taglib.core.ChooseTag) _jspx_tagPool_c_choose.get(com.liferay.taglib.core.ChooseTag.class);
                              _jspx_th_c_choose_8.setPageContext(_jspx_page_context);
                              _jspx_th_c_choose_8.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_16);
                              int _jspx_eval_c_choose_8 = _jspx_th_c_choose_8.doStartTag();
                              if (_jspx_eval_c_choose_8 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                              out.write("\n\t\t\t\t");
                              //  c:when
                              com.liferay.taglib.core.WhenTag _jspx_th_c_when_8 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
                              _jspx_th_c_when_8.setPageContext(_jspx_page_context);
                              _jspx_th_c_when_8.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_8);
                              _jspx_th_c_when_8.setTest( groupType.isControlPanel() );
                              int _jspx_eval_c_when_8 = _jspx_th_c_when_8.doStartTag();
                              if (_jspx_eval_c_when_8 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                              out.write("\n\t\t\t\t\t<li class=\"publish-link-container\">\n\t\t\t\t\t\t");
                              out.print( manageSitePagesVariationsLink );
                              out.write("\n\t\t\t\t\t</li>\n\t\t\t\t");
                              }
                              if (_jspx_th_c_when_8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_8);
                              return;
                              }
                              _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_8);
                              out.write("\n\t\t\t\t");
                              //  c:otherwise
                              com.liferay.taglib.core.OtherwiseTag _jspx_th_c_otherwise_8 = (com.liferay.taglib.core.OtherwiseTag) _jspx_tagPool_c_otherwise.get(com.liferay.taglib.core.OtherwiseTag.class);
                              _jspx_th_c_otherwise_8.setPageContext(_jspx_page_context);
                              _jspx_th_c_otherwise_8.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_8);
                              int _jspx_eval_c_otherwise_8 = _jspx_th_c_otherwise_8.doStartTag();
                              if (_jspx_eval_c_otherwise_8 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                              out.write("\n\t\t\t\t\t<div class=\"");
                              out.print( cssClass );
                              out.write(" publish-link-container\">\n\t\t\t\t\t\t");
                              out.print( manageSitePagesVariationsLink );
                              out.write("\n\t\t\t\t\t</div>\n\t\t\t\t");
                              }
                              if (_jspx_th_c_otherwise_8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_8);
                              return;
                              }
                              _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_8);
                              out.write("\n\t\t\t");
                              }
                              if (_jspx_th_c_choose_8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_8);
                              return;
                              }
                              _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_8);
                              out.write("\n\n\t\t\t");
                              //  aui:script
                              com.liferay.taglib.aui.ScriptTag _jspx_th_aui_script_5 = (com.liferay.taglib.aui.ScriptTag) _jspx_tagPool_aui_script_sandbox_position.get(com.liferay.taglib.aui.ScriptTag.class);
                              _jspx_th_aui_script_5.setPageContext(_jspx_page_context);
                              _jspx_th_aui_script_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_16);
                              _jspx_th_aui_script_5.setPosition("inline");
                              _jspx_th_aui_script_5.setSandbox( true );
                              int _jspx_eval_aui_script_5 = _jspx_th_aui_script_5.doStartTag();
                              if (_jspx_eval_aui_script_5 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                              if (_jspx_eval_aui_script_5 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                              out = _jspx_page_context.pushBody();
                              _jspx_th_aui_script_5.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                              _jspx_th_aui_script_5.doInitBody();
                              }
                              do {
                              out.write("\n\t\t\t\tvar layoutSetBranchesLink = $('#");
                              if (_jspx_meth_portlet_namespace_6((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_5, _jspx_page_context))
                              return;
                              out.write("manageLayoutSetBranches');\n\n\t\t\t\tlayoutSetBranchesLink.on(\n\t\t\t\t\t'click',\n\t\t\t\t\tfunction(event) {\n\t\t\t\t\t\tevent.preventDefault();\n\n\t\t\t\t\t\tLiferay.Util.openWindow(\n\t\t\t\t\t\t\t{\n\t\t\t\t\t\t\t\tdialog: {\n\t\t\t\t\t\t\t\t\twidth: 820\n\t\t\t\t\t\t\t\t},\n\t\t\t\t\t\t\t\tid: '");
                              if (_jspx_meth_portlet_namespace_7((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_5, _jspx_page_context))
                              return;
                              out.write("layoutSetBranches',\n\t\t\t\t\t\t\t\ttitle: '");
                              out.print( UnicodeLanguageUtil.get(request, "manage-site-pages-variations") );
                              out.write("',\n\t\t\t\t\t\t\t\turi: layoutSetBranchesLink.attr('href')\n\t\t\t\t\t\t\t}\n\t\t\t\t\t\t);\n\t\t\t\t\t}\n\t\t\t\t);\n\t\t\t");
                              int evalDoAfterBody = _jspx_th_aui_script_5.doAfterBody();
                              if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                              break;
                              } while (true);
                              if (_jspx_eval_aui_script_5 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                              out = _jspx_page_context.popBody();
                              }
                              if (_jspx_th_aui_script_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_aui_script_sandbox_position.reuse(_jspx_th_aui_script_5);
                              return;
                              }
                              _jspx_tagPool_aui_script_sandbox_position.reuse(_jspx_th_aui_script_5);
                              out.write("\n\t\t");
                              }
                              if (_jspx_th_c_if_16.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_16);
                              return;
                              }
                              _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_16);
                              out.write('\n');
                              out.write('	');
                              }
                              if (_jspx_th_c_otherwise_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_5);
                              return;
                              }
                              _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_5);
                              out.write('\n');
                            }
                            if (_jspx_th_c_choose_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_5);
                              return;
                            }
                            _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_5);
                            out.write("\n\t\t\t\t\t\t");
                            int evalDoAfterBody = _jspx_th_aui_nav$1item_1.doAfterBody();
                            if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                              break;
                          } while (true);
                          if (_jspx_eval_aui_nav$1item_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                            out = _jspx_page_context.popBody();
                        }
                        if (_jspx_th_aui_nav$1item_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                          _jspx_tagPool_aui_nav$1item_cssClass.reuse(_jspx_th_aui_nav$1item_1);
                          return;
                        }
                        _jspx_tagPool_aui_nav$1item_cssClass.reuse(_jspx_th_aui_nav$1item_1);
                        out.write("\n\t\t\t\t\t");
                        int evalDoAfterBody = _jspx_th_aui_nav$1item_0.doAfterBody();
                        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                          break;
                      } while (true);
                      if (_jspx_eval_aui_nav$1item_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                        out = _jspx_page_context.popBody();
                    }
                    if (_jspx_th_aui_nav$1item_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_aui_nav$1item_label_dropdown.reuse(_jspx_th_aui_nav$1item_0);
                      return;
                    }
                    _jspx_tagPool_aui_nav$1item_label_dropdown.reuse(_jspx_th_aui_nav$1item_0);
                    out.write("\n\t\t\t\t");
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
                out.write("\n\t\t\t");
                int evalDoAfterBody = _jspx_th_aui_nav$1bar_0.doAfterBody();
                if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                  break;
              } while (true);
              if (_jspx_eval_aui_nav$1bar_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                out = _jspx_page_context.popBody();
            }
            if (_jspx_th_aui_nav$1bar_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_aui_nav$1bar.reuse(_jspx_th_aui_nav$1bar_0);
              return;
            }
            _jspx_tagPool_aui_nav$1bar.reuse(_jspx_th_aui_nav$1bar_0);
            out.write("\n\t\t");
          }
          if (_jspx_th_c_otherwise_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_4);
            return;
          }
          _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_4);
          out.write('\n');
          out.write('	');
        }
        if (_jspx_th_c_choose_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_0);
          return;
        }
        _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_0);
        out.write('\n');
      }
      if (_jspx_th_c_if_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
        return;
      }
      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
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

  private boolean _jspx_meth_liferay$1portlet_param_0(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1portlet_renderURL_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-portlet:param
    com.liferay.taglib.util.ParamTag _jspx_th_liferay$1portlet_param_0 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_liferay$1portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
    _jspx_th_liferay$1portlet_param_0.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1portlet_param_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_0);
    _jspx_th_liferay$1portlet_param_0.setName("mvcRenderCommandName");
    _jspx_th_liferay$1portlet_param_0.setValue("publishLayouts");
    int _jspx_eval_liferay$1portlet_param_0 = _jspx_th_liferay$1portlet_param_0.doStartTag();
    if (_jspx_th_liferay$1portlet_param_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1portlet_param_value_name_nobody.reuse(_jspx_th_liferay$1portlet_param_0);
      return true;
    }
    _jspx_tagPool_liferay$1portlet_param_value_name_nobody.reuse(_jspx_th_liferay$1portlet_param_0);
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

  private boolean _jspx_meth_portlet_param_0(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1portlet_renderURL_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:param
    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_0 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
    _jspx_th_portlet_param_0.setPageContext(_jspx_page_context);
    _jspx_th_portlet_param_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_1);
    _jspx_th_portlet_param_0.setName("mvcRenderCommandName");
    _jspx_th_portlet_param_0.setValue("publishLayouts");
    int _jspx_eval_portlet_param_0 = _jspx_th_portlet_param_0.doStartTag();
    if (_jspx_th_portlet_param_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_0);
      return true;
    }
    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_0);
    return false;
  }

  private boolean _jspx_meth_portlet_param_2(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1portlet_renderURL_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:param
    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_2 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
    _jspx_th_portlet_param_2.setPageContext(_jspx_page_context);
    _jspx_th_portlet_param_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_1);
    _jspx_th_portlet_param_2.setName("tabs2");
    _jspx_th_portlet_param_2.setValue("current-and-previous");
    int _jspx_eval_portlet_param_2 = _jspx_th_portlet_param_2.doStartTag();
    if (_jspx_th_portlet_param_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_2);
      return true;
    }
    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_2);
    return false;
  }

  private boolean _jspx_meth_portlet_param_6(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1portlet_renderURL_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:param
    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_6 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
    _jspx_th_portlet_param_6.setPageContext(_jspx_page_context);
    _jspx_th_portlet_param_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_2);
    _jspx_th_portlet_param_6.setName("mvcRenderCommandName");
    _jspx_th_portlet_param_6.setValue("publishLayoutsSimple");
    int _jspx_eval_portlet_param_6 = _jspx_th_portlet_param_6.doStartTag();
    if (_jspx_th_portlet_param_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_6);
      return true;
    }
    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_6);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_0(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1util_buffer_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_0 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_0.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1util_buffer_1);
    _jspx_th_liferay$1ui_message_0.setKey("publish-to-live");
    int _jspx_eval_liferay$1ui_message_0 = _jspx_th_liferay$1ui_message_0.doStartTag();
    if (_jspx_th_liferay$1ui_message_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_0);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_0);
    return false;
  }

  private boolean _jspx_meth_aui_icon_0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:icon
    com.liferay.taglib.aui.IconTag _jspx_th_aui_icon_0 = (com.liferay.taglib.aui.IconTag) _jspx_tagPool_aui_icon_markupView_image_nobody.get(com.liferay.taglib.aui.IconTag.class);
    _jspx_th_aui_icon_0.setPageContext(_jspx_page_context);
    _jspx_th_aui_icon_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_3);
    _jspx_th_aui_icon_0.setImage("angle-down");
    _jspx_th_aui_icon_0.setMarkupView("lexicon");
    int _jspx_eval_aui_icon_0 = _jspx_th_aui_icon_0.doStartTag();
    if (_jspx_th_aui_icon_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_icon_markupView_image_nobody.reuse(_jspx_th_aui_icon_0);
      return true;
    }
    _jspx_tagPool_aui_icon_markupView_image_nobody.reuse(_jspx_th_aui_icon_0);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_1(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_1 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_1.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_3);
    _jspx_th_liferay$1ui_message_1.setKey("toggle-dropdown");
    int _jspx_eval_liferay$1ui_message_1 = _jspx_th_liferay$1ui_message_1.doStartTag();
    if (_jspx_th_liferay$1ui_message_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_1);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_1);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_2(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_2 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_2.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_4);
    _jspx_th_liferay$1ui_message_2.setKey("history");
    int _jspx_eval_liferay$1ui_message_2 = _jspx_th_liferay$1ui_message_2.doStartTag();
    if (_jspx_th_liferay$1ui_message_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_2);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_2);
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
    _jspx_th_liferay$1ui_message_3.setKey("undo");
    int _jspx_eval_liferay$1ui_message_3 = _jspx_th_liferay$1ui_message_3.doStartTag();
    if (_jspx_th_liferay$1ui_message_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_3);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_3);
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
    _jspx_th_liferay$1ui_message_4.setKey("redo");
    int _jspx_eval_liferay$1ui_message_4 = _jspx_th_liferay$1ui_message_4.doStartTag();
    if (_jspx_th_liferay$1ui_message_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_4);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_4);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_1(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_1 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_1.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    int _jspx_eval_portlet_namespace_1 = _jspx_th_portlet_namespace_1.doStartTag();
    if (_jspx_th_portlet_namespace_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_1);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_1);
    return false;
  }

  private boolean _jspx_meth_portlet_param_22(javax.servlet.jsp.tagext.JspTag _jspx_th_portlet_renderURL_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:param
    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_22 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
    _jspx_th_portlet_param_22.setPageContext(_jspx_page_context);
    _jspx_th_portlet_param_22.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_0);
    _jspx_th_portlet_param_22.setName("struts_action");
    _jspx_th_portlet_param_22.setValue("/staging_bar/view_layout_set_branches");
    int _jspx_eval_portlet_param_22 = _jspx_th_portlet_param_22.doStartTag();
    if (_jspx_th_portlet_param_22.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_22);
      return true;
    }
    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_22);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_2(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_2 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_2.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_2);
    int _jspx_eval_portlet_namespace_2 = _jspx_th_portlet_namespace_2.doStartTag();
    if (_jspx_th_portlet_namespace_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_2);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_2);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_3(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_3 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_3.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_2);
    int _jspx_eval_portlet_namespace_3 = _jspx_th_portlet_namespace_3.doStartTag();
    if (_jspx_th_portlet_namespace_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_3);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_3);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_4(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_4 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_4.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_3);
    int _jspx_eval_portlet_namespace_4 = _jspx_th_portlet_namespace_4.doStartTag();
    if (_jspx_th_portlet_namespace_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_4);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_4);
    return false;
  }

  private boolean _jspx_meth_portlet_param_26(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1portlet_renderURL_3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:param
    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_26 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
    _jspx_th_portlet_param_26.setPageContext(_jspx_page_context);
    _jspx_th_portlet_param_26.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_3);
    _jspx_th_portlet_param_26.setName("mvcRenderCommandName");
    _jspx_th_portlet_param_26.setValue("publishLayouts");
    int _jspx_eval_portlet_param_26 = _jspx_th_portlet_param_26.doStartTag();
    if (_jspx_th_portlet_param_26.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_26);
      return true;
    }
    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_26);
    return false;
  }

  private boolean _jspx_meth_portlet_param_28(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1portlet_renderURL_3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:param
    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_28 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
    _jspx_th_portlet_param_28.setPageContext(_jspx_page_context);
    _jspx_th_portlet_param_28.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_3);
    _jspx_th_portlet_param_28.setName("tabs2");
    _jspx_th_portlet_param_28.setValue("current-and-previous");
    int _jspx_eval_portlet_param_28 = _jspx_th_portlet_param_28.doStartTag();
    if (_jspx_th_portlet_param_28.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_28);
      return true;
    }
    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_28);
    return false;
  }

  private boolean _jspx_meth_portlet_param_32(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1portlet_renderURL_4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:param
    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_32 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
    _jspx_th_portlet_param_32.setPageContext(_jspx_page_context);
    _jspx_th_portlet_param_32.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_4);
    _jspx_th_portlet_param_32.setName("mvcRenderCommandName");
    _jspx_th_portlet_param_32.setValue("publishLayoutsSimple");
    int _jspx_eval_portlet_param_32 = _jspx_th_portlet_param_32.doStartTag();
    if (_jspx_th_portlet_param_32.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_32);
      return true;
    }
    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_32);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_5(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1util_buffer_4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_5 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_5.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1util_buffer_4);
    _jspx_th_liferay$1ui_message_5.setKey("publish-to-live");
    int _jspx_eval_liferay$1ui_message_5 = _jspx_th_liferay$1ui_message_5.doStartTag();
    if (_jspx_th_liferay$1ui_message_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_5);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_5);
    return false;
  }

  private boolean _jspx_meth_aui_icon_1(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_11, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:icon
    com.liferay.taglib.aui.IconTag _jspx_th_aui_icon_1 = (com.liferay.taglib.aui.IconTag) _jspx_tagPool_aui_icon_markupView_image_nobody.get(com.liferay.taglib.aui.IconTag.class);
    _jspx_th_aui_icon_1.setPageContext(_jspx_page_context);
    _jspx_th_aui_icon_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_11);
    _jspx_th_aui_icon_1.setImage("angle-down");
    _jspx_th_aui_icon_1.setMarkupView("lexicon");
    int _jspx_eval_aui_icon_1 = _jspx_th_aui_icon_1.doStartTag();
    if (_jspx_th_aui_icon_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_icon_markupView_image_nobody.reuse(_jspx_th_aui_icon_1);
      return true;
    }
    _jspx_tagPool_aui_icon_markupView_image_nobody.reuse(_jspx_th_aui_icon_1);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_6(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_11, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_6 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_6.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_11);
    _jspx_th_liferay$1ui_message_6.setKey("toggle-dropdown");
    int _jspx_eval_liferay$1ui_message_6 = _jspx_th_liferay$1ui_message_6.doStartTag();
    if (_jspx_th_liferay$1ui_message_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_6);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_6);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_7(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_12, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_7 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_7.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_12);
    _jspx_th_liferay$1ui_message_7.setKey("history");
    int _jspx_eval_liferay$1ui_message_7 = _jspx_th_liferay$1ui_message_7.doStartTag();
    if (_jspx_th_liferay$1ui_message_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_7);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_7);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_8(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_14, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_8 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_8.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_8.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_14);
    _jspx_th_liferay$1ui_message_8.setKey("undo");
    int _jspx_eval_liferay$1ui_message_8 = _jspx_th_liferay$1ui_message_8.doStartTag();
    if (_jspx_th_liferay$1ui_message_8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_8);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_8);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_9(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_15, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_9 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_9.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_9.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_15);
    _jspx_th_liferay$1ui_message_9.setKey("redo");
    int _jspx_eval_liferay$1ui_message_9 = _jspx_th_liferay$1ui_message_9.doStartTag();
    if (_jspx_th_liferay$1ui_message_9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_9);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_9);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_5(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_5 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_5.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_4);
    int _jspx_eval_portlet_namespace_5 = _jspx_th_portlet_namespace_5.doStartTag();
    if (_jspx_th_portlet_namespace_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_5);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_5);
    return false;
  }

  private boolean _jspx_meth_portlet_param_48(javax.servlet.jsp.tagext.JspTag _jspx_th_portlet_renderURL_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:param
    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_48 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
    _jspx_th_portlet_param_48.setPageContext(_jspx_page_context);
    _jspx_th_portlet_param_48.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_1);
    _jspx_th_portlet_param_48.setName("struts_action");
    _jspx_th_portlet_param_48.setValue("/staging_bar/view_layout_set_branches");
    int _jspx_eval_portlet_param_48 = _jspx_th_portlet_param_48.doStartTag();
    if (_jspx_th_portlet_param_48.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_48);
      return true;
    }
    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_48);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_6(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_5, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_6 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_6.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_5);
    int _jspx_eval_portlet_namespace_6 = _jspx_th_portlet_namespace_6.doStartTag();
    if (_jspx_th_portlet_namespace_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_6);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_6);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_7(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_5, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_7 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_7.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_5);
    int _jspx_eval_portlet_namespace_7 = _jspx_th_portlet_namespace_7.doStartTag();
    if (_jspx_th_portlet_namespace_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_7);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_7);
    return false;
  }
}
