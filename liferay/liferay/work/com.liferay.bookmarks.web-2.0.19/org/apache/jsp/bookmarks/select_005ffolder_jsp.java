package org.apache.jsp.bookmarks;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.liferay.asset.kernel.AssetRendererFactoryRegistryUtil;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetEntryServiceUtil;
import com.liferay.asset.kernel.service.persistence.AssetEntryQuery;
import com.liferay.bookmarks.configuration.BookmarksGroupServiceOverriddenConfiguration;
import com.liferay.bookmarks.constants.BookmarksConstants;
import com.liferay.bookmarks.constants.BookmarksPortletKeys;
import com.liferay.bookmarks.constants.BookmarksWebKeys;
import com.liferay.bookmarks.exception.EntryURLException;
import com.liferay.bookmarks.exception.FolderNameException;
import com.liferay.bookmarks.exception.NoSuchEntryException;
import com.liferay.bookmarks.exception.NoSuchFolderException;
import com.liferay.bookmarks.model.BookmarksEntry;
import com.liferay.bookmarks.model.BookmarksFolder;
import com.liferay.bookmarks.model.BookmarksFolderConstants;
import com.liferay.bookmarks.search.BookmarksSearcher;
import com.liferay.bookmarks.service.BookmarksEntryServiceUtil;
import com.liferay.bookmarks.service.BookmarksFolderLocalServiceUtil;
import com.liferay.bookmarks.service.BookmarksFolderServiceUtil;
import com.liferay.bookmarks.web.internal.dao.search.BookmarksResultRowSplitter;
import com.liferay.bookmarks.web.internal.display.context.BookmarksManagementToolbarDisplayContext;
import com.liferay.bookmarks.web.internal.portlet.util.BookmarksUtil;
import com.liferay.bookmarks.web.internal.search.EntriesChecker;
import com.liferay.bookmarks.web.internal.search.EntriesMover;
import com.liferay.bookmarks.web.internal.security.permission.resource.BookmarksEntryPermission;
import com.liferay.bookmarks.web.internal.security.permission.resource.BookmarksFolderPermission;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.JSPNavigationItemList;
import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.bean.BeanParamUtil;
import com.liferay.portal.kernel.dao.search.ResultRow;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.module.configuration.ConfigurationProviderUtil;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.portlet.PortalPreferences;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchContextFactory;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.permission.GroupPermissionUtil;
import com.liferay.portal.kernel.settings.GroupServiceSettingsLocator;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.KeyValuePair;
import com.liferay.portal.kernel.util.KeyValuePairComparator;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.staging.StagingGroupHelper;
import com.liferay.staging.StagingGroupHelperUtil;
import com.liferay.subscription.service.SubscriptionLocalServiceUtil;
import java.text.Format;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import javax.portlet.PortletURL;
import javax.portlet.WindowState;

public final class select_005ffolder_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(3);
    _jspx_dependants.add("/bookmarks/init.jsp");
    _jspx_dependants.add("/init.jsp");
    _jspx_dependants.add("/bookmarks/init-ext.jsp");
  }

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container_total_iteratorURL;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_namespace_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_breadcrumb_showParentGroups_showLayout_showGuestGroup_showCurrentGroup_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_form_name_method;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_href_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_button_value_data_cssClass_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_param_value_name_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container$1column$1text_name;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_if_test;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container$1results_results_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_icon_url_message_markupView_localizeMessage_label_icon_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_renderURL_var;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_button_value_href_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_script;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1iterator_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1theme_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container$1row_modelVar_className;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1frontend_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container$1column$1text;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1trash_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_button$1row;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_liferay$1ui_search$1container_total_iteratorURL = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_namespace_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_breadcrumb_showParentGroups_showLayout_showGuestGroup_showCurrentGroup_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_form_name_method = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_href_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_button_value_data_cssClass_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_param_value_name_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_name = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_if_test = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container$1results_results_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_icon_url_message_markupView_localizeMessage_label_icon_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_renderURL_var = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_button_value_href_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_script = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1iterator_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1theme_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container$1row_modelVar_className = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1frontend_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1trash_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_button$1row = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_liferay$1ui_search$1container_total_iteratorURL.release();
    _jspx_tagPool_portlet_namespace_nobody.release();
    _jspx_tagPool_liferay$1ui_breadcrumb_showParentGroups_showLayout_showGuestGroup_showCurrentGroup_nobody.release();
    _jspx_tagPool_aui_form_name_method.release();
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_href_nobody.release();
    _jspx_tagPool_aui_button_value_data_cssClass_nobody.release();
    _jspx_tagPool_portlet_param_value_name_nobody.release();
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_name.release();
    _jspx_tagPool_c_if_test.release();
    _jspx_tagPool_liferay$1ui_search$1container$1results_results_nobody.release();
    _jspx_tagPool_liferay$1ui_icon_url_message_markupView_localizeMessage_label_icon_nobody.release();
    _jspx_tagPool_portlet_renderURL_var.release();
    _jspx_tagPool_aui_button_value_href_nobody.release();
    _jspx_tagPool_aui_script.release();
    _jspx_tagPool_portlet_defineObjects_nobody.release();
    _jspx_tagPool_liferay$1ui_search$1iterator_nobody.release();
    _jspx_tagPool_liferay$1theme_defineObjects_nobody.release();
    _jspx_tagPool_liferay$1ui_search$1container$1row_modelVar_className.release();
    _jspx_tagPool_liferay$1frontend_defineObjects_nobody.release();
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text.release();
    _jspx_tagPool_liferay$1trash_defineObjects_nobody.release();
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
      //  liferay-trash:defineObjects
      com.liferay.trash.taglib.servlet.taglib.DefineObjectsTag _jspx_th_liferay$1trash_defineObjects_0 = (com.liferay.trash.taglib.servlet.taglib.DefineObjectsTag) _jspx_tagPool_liferay$1trash_defineObjects_nobody.get(com.liferay.trash.taglib.servlet.taglib.DefineObjectsTag.class);
      _jspx_th_liferay$1trash_defineObjects_0.setPageContext(_jspx_page_context);
      _jspx_th_liferay$1trash_defineObjects_0.setParent(null);
      int _jspx_eval_liferay$1trash_defineObjects_0 = _jspx_th_liferay$1trash_defineObjects_0.doStartTag();
      if (_jspx_th_liferay$1trash_defineObjects_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_liferay$1trash_defineObjects_nobody.reuse(_jspx_th_liferay$1trash_defineObjects_0);
        return;
      }
      _jspx_tagPool_liferay$1trash_defineObjects_nobody.reuse(_jspx_th_liferay$1trash_defineObjects_0);
      com.liferay.trash.TrashHelper trashHelper = null;
      trashHelper = (com.liferay.trash.TrashHelper) _jspx_page_context.findAttribute("trashHelper");
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

BookmarksGroupServiceOverriddenConfiguration bookmarksGroupServiceOverriddenConfiguration = ConfigurationProviderUtil.getConfiguration(BookmarksGroupServiceOverriddenConfiguration.class, new GroupServiceSettingsLocator(scopeGroupId, BookmarksConstants.SERVICE_NAME));

PortalPreferences portalPreferences = PortletPreferencesFactoryUtil.getPortalPreferences(request);

String portletResource = ParamUtil.getString(request, "portletResource");

long rootFolderId = bookmarksGroupServiceOverriddenConfiguration.rootFolderId();
String rootFolderName = StringPool.BLANK;

if (rootFolderId != BookmarksFolderConstants.DEFAULT_PARENT_FOLDER_ID) {
	try {
		BookmarksFolder rootFolder = BookmarksFolderLocalServiceUtil.getFolder(rootFolderId);

		rootFolderName = rootFolder.getName();

		if (rootFolder.getGroupId() != scopeGroupId) {
			rootFolderId = BookmarksFolderConstants.DEFAULT_PARENT_FOLDER_ID;
			rootFolderName = StringPool.BLANK;
		}
	}
	catch (NoSuchFolderException nsfe) {
		rootFolderId = BookmarksFolderConstants.DEFAULT_PARENT_FOLDER_ID;
	}
}

String portletId = portletDisplay.getId();

if (Validator.isNotNull(portletResource)) {
	portletId = portletResource;
}

String allFolderColumns = "folder,num-of-folders,num-of-entries";

if (portletId.equals(BookmarksPortletKeys.BOOKMARKS) || portletId.equals(BookmarksPortletKeys.BOOKMARKS_ADMIN)) {
	allFolderColumns += ",action";
}

String[] folderColumns = bookmarksGroupServiceOverriddenConfiguration.folderColumns();

if (!portletId.equals(BookmarksPortletKeys.BOOKMARKS) && !portletId.equals(BookmarksPortletKeys.BOOKMARKS_ADMIN)) {
	folderColumns = ArrayUtil.remove(folderColumns, "action");
}

String allEntryColumns = "name,url,visits,modified-date";

if (portletId.equals(BookmarksPortletKeys.BOOKMARKS) || portletId.equals(BookmarksPortletKeys.BOOKMARKS_ADMIN)) {
	allEntryColumns += ",action";
}

String[] entryColumns = bookmarksGroupServiceOverriddenConfiguration.entryColumns();

if (!portletId.equals(BookmarksPortletKeys.BOOKMARKS) && !portletId.equals(BookmarksPortletKeys.BOOKMARKS_ADMIN)) {
	entryColumns = ArrayUtil.remove(entryColumns, "action");
}

StagingGroupHelper stagingGroupHelper = StagingGroupHelperUtil.getStagingGroupHelper();

Format dateFormatDate = FastDateFormatFactoryUtil.getDate(locale, timeZone);

      out.write('\n');
      out.write('\n');
      out.write('\n');
      out.write('\n');

BookmarksFolder folder = (BookmarksFolder)request.getAttribute(BookmarksWebKeys.BOOKMARKS_FOLDER);

long folderId = BeanParamUtil.getLong(folder, request, "folderId", BookmarksFolderConstants.DEFAULT_PARENT_FOLDER_ID);

String eventName = ParamUtil.getString(request, "eventName", liferayPortletResponse.getNamespace() + "selectFolder");

String folderName = LanguageUtil.get(request, "home");

if (folder != null) {
	folderName = folder.getName();

	BookmarksUtil.addPortletBreadcrumbEntries(folder, request, renderResponse);
}

      out.write("\n\n<div class=\"container-fluid-1280\">\n\t");
      //  aui:form
      com.liferay.taglib.aui.FormTag _jspx_th_aui_form_0 = (com.liferay.taglib.aui.FormTag) _jspx_tagPool_aui_form_name_method.get(com.liferay.taglib.aui.FormTag.class);
      _jspx_th_aui_form_0.setPageContext(_jspx_page_context);
      _jspx_th_aui_form_0.setParent(null);
      _jspx_th_aui_form_0.setMethod("post");
      _jspx_th_aui_form_0.setName("selectFolderFm");
      int _jspx_eval_aui_form_0 = _jspx_th_aui_form_0.doStartTag();
      if (_jspx_eval_aui_form_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        out.write("\n\t\t");
        //  liferay-ui:breadcrumb
        com.liferay.taglib.ui.BreadcrumbTag _jspx_th_liferay$1ui_breadcrumb_0 = (com.liferay.taglib.ui.BreadcrumbTag) _jspx_tagPool_liferay$1ui_breadcrumb_showParentGroups_showLayout_showGuestGroup_showCurrentGroup_nobody.get(com.liferay.taglib.ui.BreadcrumbTag.class);
        _jspx_th_liferay$1ui_breadcrumb_0.setPageContext(_jspx_page_context);
        _jspx_th_liferay$1ui_breadcrumb_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
        _jspx_th_liferay$1ui_breadcrumb_0.setShowCurrentGroup( false );
        _jspx_th_liferay$1ui_breadcrumb_0.setShowGuestGroup( false );
        _jspx_th_liferay$1ui_breadcrumb_0.setShowLayout( false );
        _jspx_th_liferay$1ui_breadcrumb_0.setShowParentGroups( false );
        int _jspx_eval_liferay$1ui_breadcrumb_0 = _jspx_th_liferay$1ui_breadcrumb_0.doStartTag();
        if (_jspx_th_liferay$1ui_breadcrumb_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_liferay$1ui_breadcrumb_showParentGroups_showLayout_showGuestGroup_showCurrentGroup_nobody.reuse(_jspx_th_liferay$1ui_breadcrumb_0);
          return;
        }
        _jspx_tagPool_liferay$1ui_breadcrumb_showParentGroups_showLayout_showGuestGroup_showCurrentGroup_nobody.reuse(_jspx_th_liferay$1ui_breadcrumb_0);
        out.write("\n\n\t\t");

		PortletURL portletURL = renderResponse.createRenderURL();

		portletURL.setParameter("mvcRenderCommandName", "/bookmarks/select_folder");
		portletURL.setParameter("folderId", String.valueOf(folderId));
		portletURL.setParameter("eventName", eventName);
		
        out.write("\n\n\t\t<br />\n\n\t\t");
        //  liferay-ui:search-container
        com.liferay.taglib.ui.SearchContainerTag _jspx_th_liferay$1ui_search$1container_0 = (com.liferay.taglib.ui.SearchContainerTag) _jspx_tagPool_liferay$1ui_search$1container_total_iteratorURL.get(com.liferay.taglib.ui.SearchContainerTag.class);
        _jspx_th_liferay$1ui_search$1container_0.setPageContext(_jspx_page_context);
        _jspx_th_liferay$1ui_search$1container_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
        _jspx_th_liferay$1ui_search$1container_0.setIteratorURL( portletURL );
        _jspx_th_liferay$1ui_search$1container_0.setTotal( BookmarksFolderServiceUtil.getFoldersCount(scopeGroupId, folderId) );
        int _jspx_eval_liferay$1ui_search$1container_0 = _jspx_th_liferay$1ui_search$1container_0.doStartTag();
        if (_jspx_eval_liferay$1ui_search$1container_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          java.lang.Integer total = null;
          com.liferay.portal.kernel.dao.search.SearchContainer searchContainer = null;
          total = (java.lang.Integer) _jspx_page_context.findAttribute("total");
          searchContainer = (com.liferay.portal.kernel.dao.search.SearchContainer) _jspx_page_context.findAttribute("searchContainer");
          out.write("\n\t\t\t");
          //  liferay-ui:search-container-results
          java.util.List results = null;
          java.lang.Integer deprecatedTotal = null;
          com.liferay.taglib.ui.SearchContainerResultsTag _jspx_th_liferay$1ui_search$1container$1results_0 = (com.liferay.taglib.ui.SearchContainerResultsTag) _jspx_tagPool_liferay$1ui_search$1container$1results_results_nobody.get(com.liferay.taglib.ui.SearchContainerResultsTag.class);
          _jspx_th_liferay$1ui_search$1container$1results_0.setPageContext(_jspx_page_context);
          _jspx_th_liferay$1ui_search$1container$1results_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container_0);
          _jspx_th_liferay$1ui_search$1container$1results_0.setResults( BookmarksFolderServiceUtil.getFolders(scopeGroupId, folderId, searchContainer.getStart(), searchContainer.getEnd()) );
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
          out.write("\n\n\t\t\t");
          //  liferay-ui:search-container-row
          com.liferay.taglib.ui.SearchContainerRowTag _jspx_th_liferay$1ui_search$1container$1row_0 = (com.liferay.taglib.ui.SearchContainerRowTag) _jspx_tagPool_liferay$1ui_search$1container$1row_modelVar_className.get(com.liferay.taglib.ui.SearchContainerRowTag.class);
          _jspx_th_liferay$1ui_search$1container$1row_0.setPageContext(_jspx_page_context);
          _jspx_th_liferay$1ui_search$1container$1row_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container_0);
          _jspx_th_liferay$1ui_search$1container$1row_0.setClassName("com.liferay.bookmarks.model.BookmarksFolder");
          _jspx_th_liferay$1ui_search$1container$1row_0.setModelVar("curFolder");
          int _jspx_eval_liferay$1ui_search$1container$1row_0 = _jspx_th_liferay$1ui_search$1container$1row_0.doStartTag();
          if (_jspx_eval_liferay$1ui_search$1container$1row_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            java.lang.Integer index = null;
            com.liferay.bookmarks.model.BookmarksFolder curFolder = null;
            com.liferay.portal.kernel.dao.search.ResultRow row = null;
            if (_jspx_eval_liferay$1ui_search$1container$1row_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
              out = _jspx_page_context.pushBody();
              _jspx_th_liferay$1ui_search$1container$1row_0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
              _jspx_th_liferay$1ui_search$1container$1row_0.doInitBody();
            }
            index = (java.lang.Integer) _jspx_page_context.findAttribute("index");
            curFolder = (com.liferay.bookmarks.model.BookmarksFolder) _jspx_page_context.findAttribute("curFolder");
            row = (com.liferay.portal.kernel.dao.search.ResultRow) _jspx_page_context.findAttribute("row");
            do {
              out.write("\n\t\t\t\t");
              //  portlet:renderURL
              com.liferay.taglib.portlet.RenderURLTag _jspx_th_portlet_renderURL_0 = (com.liferay.taglib.portlet.RenderURLTag) _jspx_tagPool_portlet_renderURL_var.get(com.liferay.taglib.portlet.RenderURLTag.class);
              _jspx_th_portlet_renderURL_0.setPageContext(_jspx_page_context);
              _jspx_th_portlet_renderURL_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1row_0);
              _jspx_th_portlet_renderURL_0.setVar("viewFolderURL");
              int _jspx_eval_portlet_renderURL_0 = _jspx_th_portlet_renderURL_0.doStartTag();
              if (_jspx_eval_portlet_renderURL_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\t\t\t\t\t");
                if (_jspx_meth_portlet_param_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_portlet_renderURL_0, _jspx_page_context))
                  return;
                out.write("\n\t\t\t\t\t");
                //  portlet:param
                com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_1 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                _jspx_th_portlet_param_1.setPageContext(_jspx_page_context);
                _jspx_th_portlet_param_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_0);
                _jspx_th_portlet_param_1.setName("folderId");
                _jspx_th_portlet_param_1.setValue( String.valueOf(curFolder.getFolderId()) );
                int _jspx_eval_portlet_param_1 = _jspx_th_portlet_param_1.doStartTag();
                if (_jspx_th_portlet_param_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_1);
                  return;
                }
                _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_1);
                out.write("\n\t\t\t\t");
              }
              if (_jspx_th_portlet_renderURL_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_portlet_renderURL_var.reuse(_jspx_th_portlet_renderURL_0);
                return;
              }
              _jspx_tagPool_portlet_renderURL_var.reuse(_jspx_th_portlet_renderURL_0);
              java.lang.String viewFolderURL = null;
              viewFolderURL = (java.lang.String) _jspx_page_context.findAttribute("viewFolderURL");
              out.write("\n\n\t\t\t\t");
              //  liferay-ui:search-container-column-text
              com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_0 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text_name.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
              _jspx_th_liferay$1ui_search$1container$1column$1text_0.setPageContext(_jspx_page_context);
              _jspx_th_liferay$1ui_search$1container$1column$1text_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1row_0);
              _jspx_th_liferay$1ui_search$1container$1column$1text_0.setName("folder");
              int _jspx_eval_liferay$1ui_search$1container$1column$1text_0 = _jspx_th_liferay$1ui_search$1container$1column$1text_0.doStartTag();
              if (_jspx_eval_liferay$1ui_search$1container$1column$1text_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                if (_jspx_eval_liferay$1ui_search$1container$1column$1text_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                  out = _jspx_page_context.pushBody();
                  _jspx_th_liferay$1ui_search$1container$1column$1text_0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                  _jspx_th_liferay$1ui_search$1container$1column$1text_0.doInitBody();
                }
                do {
                  out.write("\n\n\t\t\t\t\t");

					AssetRendererFactory<BookmarksFolder> assetRendererFactory = AssetRendererFactoryRegistryUtil.getAssetRendererFactoryByClass(BookmarksFolder.class);

					AssetRenderer<BookmarksFolder> assetRenderer = assetRendererFactory.getAssetRenderer(curFolder.getFolderId());
					
                  out.write("\n\n\t\t\t\t\t");
                  //  liferay-ui:icon
                  com.liferay.taglib.ui.IconTag _jspx_th_liferay$1ui_icon_0 = (com.liferay.taglib.ui.IconTag) _jspx_tagPool_liferay$1ui_icon_url_message_markupView_localizeMessage_label_icon_nobody.get(com.liferay.taglib.ui.IconTag.class);
                  _jspx_th_liferay$1ui_icon_0.setPageContext(_jspx_page_context);
                  _jspx_th_liferay$1ui_icon_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1column$1text_0);
                  _jspx_th_liferay$1ui_icon_0.setIcon( assetRenderer.getIconCssClass() );
                  _jspx_th_liferay$1ui_icon_0.setLabel( true );
                  _jspx_th_liferay$1ui_icon_0.setLocalizeMessage( false );
                  _jspx_th_liferay$1ui_icon_0.setMarkupView("lexicon");
                  _jspx_th_liferay$1ui_icon_0.setMessage( HtmlUtil.escape(curFolder.getName()) );
                  _jspx_th_liferay$1ui_icon_0.setUrl( viewFolderURL );
                  int _jspx_eval_liferay$1ui_icon_0 = _jspx_th_liferay$1ui_icon_0.doStartTag();
                  if (_jspx_th_liferay$1ui_icon_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_liferay$1ui_icon_url_message_markupView_localizeMessage_label_icon_nobody.reuse(_jspx_th_liferay$1ui_icon_0);
                    return;
                  }
                  _jspx_tagPool_liferay$1ui_icon_url_message_markupView_localizeMessage_label_icon_nobody.reuse(_jspx_th_liferay$1ui_icon_0);
                  out.write("\n\t\t\t\t");
                  int evalDoAfterBody = _jspx_th_liferay$1ui_search$1container$1column$1text_0.doAfterBody();
                  if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                    break;
                } while (true);
                if (_jspx_eval_liferay$1ui_search$1container$1column$1text_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                  out = _jspx_page_context.popBody();
              }
              if (_jspx_th_liferay$1ui_search$1container$1column$1text_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_liferay$1ui_search$1container$1column$1text_name.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_0);
                return;
              }
              _jspx_tagPool_liferay$1ui_search$1container$1column$1text_name.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_0);
              out.write("\n\n\t\t\t\t");
              //  liferay-ui:search-container-column-text
              com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_1 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_href_nobody.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
              _jspx_th_liferay$1ui_search$1container$1column$1text_1.setPageContext(_jspx_page_context);
              _jspx_th_liferay$1ui_search$1container$1column$1text_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1row_0);
              _jspx_th_liferay$1ui_search$1container$1column$1text_1.setHref( viewFolderURL );
              _jspx_th_liferay$1ui_search$1container$1column$1text_1.setName("num-of-folders");
              _jspx_th_liferay$1ui_search$1container$1column$1text_1.setValue( String.valueOf(BookmarksFolderServiceUtil.getFoldersCount(scopeGroupId, curFolder.getFolderId(), WorkflowConstants.STATUS_APPROVED)) );
              int _jspx_eval_liferay$1ui_search$1container$1column$1text_1 = _jspx_th_liferay$1ui_search$1container$1column$1text_1.doStartTag();
              if (_jspx_th_liferay$1ui_search$1container$1column$1text_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_href_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_1);
                return;
              }
              _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_href_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_1);
              out.write("\n\n\t\t\t\t");
              //  liferay-ui:search-container-column-text
              com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_2 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_href_nobody.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
              _jspx_th_liferay$1ui_search$1container$1column$1text_2.setPageContext(_jspx_page_context);
              _jspx_th_liferay$1ui_search$1container$1column$1text_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1row_0);
              _jspx_th_liferay$1ui_search$1container$1column$1text_2.setHref( viewFolderURL );
              _jspx_th_liferay$1ui_search$1container$1column$1text_2.setName("num-of-entries");
              _jspx_th_liferay$1ui_search$1container$1column$1text_2.setValue( String.valueOf(BookmarksEntryServiceUtil.getFoldersEntriesCount(scopeGroupId, Arrays.asList(curFolder.getFolderId()))) );
              int _jspx_eval_liferay$1ui_search$1container$1column$1text_2 = _jspx_th_liferay$1ui_search$1container$1column$1text_2.doStartTag();
              if (_jspx_th_liferay$1ui_search$1container$1column$1text_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_href_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_2);
                return;
              }
              _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_href_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_2);
              out.write("\n\n\t\t\t\t");
              //  liferay-ui:search-container-column-text
              com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_3 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
              _jspx_th_liferay$1ui_search$1container$1column$1text_3.setPageContext(_jspx_page_context);
              _jspx_th_liferay$1ui_search$1container$1column$1text_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1row_0);
              int _jspx_eval_liferay$1ui_search$1container$1column$1text_3 = _jspx_th_liferay$1ui_search$1container$1column$1text_3.doStartTag();
              if (_jspx_eval_liferay$1ui_search$1container$1column$1text_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                if (_jspx_eval_liferay$1ui_search$1container$1column$1text_3 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                  out = _jspx_page_context.pushBody();
                  _jspx_th_liferay$1ui_search$1container$1column$1text_3.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                  _jspx_th_liferay$1ui_search$1container$1column$1text_3.doInitBody();
                }
                do {
                  out.write("\n\n\t\t\t\t\t");

					Map<String, Object> data = new HashMap<String, Object>();

					data.put("entityid", curFolder.getFolderId());
					data.put("entityname", curFolder.getName());
					
                  out.write("\n\n\t\t\t\t\t");
                  //  aui:button
                  com.liferay.taglib.aui.ButtonTag _jspx_th_aui_button_0 = (com.liferay.taglib.aui.ButtonTag) _jspx_tagPool_aui_button_value_data_cssClass_nobody.get(com.liferay.taglib.aui.ButtonTag.class);
                  _jspx_th_aui_button_0.setPageContext(_jspx_page_context);
                  _jspx_th_aui_button_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1column$1text_3);
                  _jspx_th_aui_button_0.setCssClass("selector-button");
                  _jspx_th_aui_button_0.setData( data );
                  _jspx_th_aui_button_0.setValue("choose");
                  int _jspx_eval_aui_button_0 = _jspx_th_aui_button_0.doStartTag();
                  if (_jspx_th_aui_button_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_aui_button_value_data_cssClass_nobody.reuse(_jspx_th_aui_button_0);
                    return;
                  }
                  _jspx_tagPool_aui_button_value_data_cssClass_nobody.reuse(_jspx_th_aui_button_0);
                  out.write("\n\t\t\t\t");
                  int evalDoAfterBody = _jspx_th_liferay$1ui_search$1container$1column$1text_3.doAfterBody();
                  if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                    break;
                } while (true);
                if (_jspx_eval_liferay$1ui_search$1container$1column$1text_3 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                  out = _jspx_page_context.popBody();
              }
              if (_jspx_th_liferay$1ui_search$1container$1column$1text_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_liferay$1ui_search$1container$1column$1text.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_3);
                return;
              }
              _jspx_tagPool_liferay$1ui_search$1container$1column$1text.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_3);
              out.write("\n\t\t\t");
              int evalDoAfterBody = _jspx_th_liferay$1ui_search$1container$1row_0.doAfterBody();
              index = (java.lang.Integer) _jspx_page_context.findAttribute("index");
              curFolder = (com.liferay.bookmarks.model.BookmarksFolder) _jspx_page_context.findAttribute("curFolder");
              row = (com.liferay.portal.kernel.dao.search.ResultRow) _jspx_page_context.findAttribute("row");
              if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                break;
            } while (true);
            if (_jspx_eval_liferay$1ui_search$1container$1row_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
              out = _jspx_page_context.popBody();
          }
          if (_jspx_th_liferay$1ui_search$1container$1row_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_liferay$1ui_search$1container$1row_modelVar_className.reuse(_jspx_th_liferay$1ui_search$1container$1row_0);
            return;
          }
          _jspx_tagPool_liferay$1ui_search$1container$1row_modelVar_className.reuse(_jspx_th_liferay$1ui_search$1container$1row_0);
          out.write("\n\n\t\t\t");
          //  aui:button-row
          com.liferay.taglib.aui.ButtonRowTag _jspx_th_aui_button$1row_0 = (com.liferay.taglib.aui.ButtonRowTag) _jspx_tagPool_aui_button$1row.get(com.liferay.taglib.aui.ButtonRowTag.class);
          _jspx_th_aui_button$1row_0.setPageContext(_jspx_page_context);
          _jspx_th_aui_button$1row_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container_0);
          int _jspx_eval_aui_button$1row_0 = _jspx_th_aui_button$1row_0.doStartTag();
          if (_jspx_eval_aui_button$1row_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t\t\t");
            //  c:if
            com.liferay.taglib.core.IfTag _jspx_th_c_if_0 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
            _jspx_th_c_if_0.setPageContext(_jspx_page_context);
            _jspx_th_c_if_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_button$1row_0);
            _jspx_th_c_if_0.setTest( BookmarksFolderPermission.contains(permissionChecker, scopeGroupId, folderId, ActionKeys.ADD_FOLDER) );
            int _jspx_eval_c_if_0 = _jspx_th_c_if_0.doStartTag();
            if (_jspx_eval_c_if_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\t\t\t\t\t");
              //  portlet:renderURL
              com.liferay.taglib.portlet.RenderURLTag _jspx_th_portlet_renderURL_1 = (com.liferay.taglib.portlet.RenderURLTag) _jspx_tagPool_portlet_renderURL_var.get(com.liferay.taglib.portlet.RenderURLTag.class);
              _jspx_th_portlet_renderURL_1.setPageContext(_jspx_page_context);
              _jspx_th_portlet_renderURL_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_0);
              _jspx_th_portlet_renderURL_1.setVar("editFolderURL");
              int _jspx_eval_portlet_renderURL_1 = _jspx_th_portlet_renderURL_1.doStartTag();
              if (_jspx_eval_portlet_renderURL_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\t\t\t\t\t\t");
                if (_jspx_meth_portlet_param_2((javax.servlet.jsp.tagext.JspTag) _jspx_th_portlet_renderURL_1, _jspx_page_context))
                  return;
                out.write("\n\t\t\t\t\t\t");
                //  portlet:param
                com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_3 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                _jspx_th_portlet_param_3.setPageContext(_jspx_page_context);
                _jspx_th_portlet_param_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_1);
                _jspx_th_portlet_param_3.setName("redirect");
                _jspx_th_portlet_param_3.setValue( currentURL );
                int _jspx_eval_portlet_param_3 = _jspx_th_portlet_param_3.doStartTag();
                if (_jspx_th_portlet_param_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_3);
                  return;
                }
                _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_3);
                out.write("\n\t\t\t\t\t\t");
                //  portlet:param
                com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_4 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                _jspx_th_portlet_param_4.setPageContext(_jspx_page_context);
                _jspx_th_portlet_param_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_1);
                _jspx_th_portlet_param_4.setName("parentFolderId");
                _jspx_th_portlet_param_4.setValue( String.valueOf(folderId) );
                int _jspx_eval_portlet_param_4 = _jspx_th_portlet_param_4.doStartTag();
                if (_jspx_th_portlet_param_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_4);
                  return;
                }
                _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_4);
                out.write("\n\t\t\t\t\t");
              }
              if (_jspx_th_portlet_renderURL_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_portlet_renderURL_var.reuse(_jspx_th_portlet_renderURL_1);
                return;
              }
              _jspx_tagPool_portlet_renderURL_var.reuse(_jspx_th_portlet_renderURL_1);
              java.lang.String editFolderURL = null;
              editFolderURL = (java.lang.String) _jspx_page_context.findAttribute("editFolderURL");
              out.write("\n\n\t\t\t\t\t");
              //  aui:button
              com.liferay.taglib.aui.ButtonTag _jspx_th_aui_button_1 = (com.liferay.taglib.aui.ButtonTag) _jspx_tagPool_aui_button_value_href_nobody.get(com.liferay.taglib.aui.ButtonTag.class);
              _jspx_th_aui_button_1.setPageContext(_jspx_page_context);
              _jspx_th_aui_button_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_0);
              _jspx_th_aui_button_1.setHref( editFolderURL );
              _jspx_th_aui_button_1.setValue( (folder == null) ? "add-folder" : "add-subfolder" );
              int _jspx_eval_aui_button_1 = _jspx_th_aui_button_1.doStartTag();
              if (_jspx_th_aui_button_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_aui_button_value_href_nobody.reuse(_jspx_th_aui_button_1);
                return;
              }
              _jspx_tagPool_aui_button_value_href_nobody.reuse(_jspx_th_aui_button_1);
              out.write("\n\t\t\t\t");
            }
            if (_jspx_th_c_if_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
              return;
            }
            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
            out.write("\n\n\t\t\t\t");

				Map<String, Object> data = new HashMap<String, Object>();

				data.put("entityid", folderId);
				data.put("entityname", folderName);
				
            out.write("\n\n\t\t\t\t");
            //  aui:button
            com.liferay.taglib.aui.ButtonTag _jspx_th_aui_button_2 = (com.liferay.taglib.aui.ButtonTag) _jspx_tagPool_aui_button_value_data_cssClass_nobody.get(com.liferay.taglib.aui.ButtonTag.class);
            _jspx_th_aui_button_2.setPageContext(_jspx_page_context);
            _jspx_th_aui_button_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_button$1row_0);
            _jspx_th_aui_button_2.setCssClass("selector-button");
            _jspx_th_aui_button_2.setData( data );
            _jspx_th_aui_button_2.setValue("choose-this-folder");
            int _jspx_eval_aui_button_2 = _jspx_th_aui_button_2.doStartTag();
            if (_jspx_th_aui_button_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_aui_button_value_data_cssClass_nobody.reuse(_jspx_th_aui_button_2);
              return;
            }
            _jspx_tagPool_aui_button_value_data_cssClass_nobody.reuse(_jspx_th_aui_button_2);
            out.write("\n\t\t\t");
          }
          if (_jspx_th_aui_button$1row_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_aui_button$1row.reuse(_jspx_th_aui_button$1row_0);
            return;
          }
          _jspx_tagPool_aui_button$1row.reuse(_jspx_th_aui_button$1row_0);
          out.write("\n\n\t\t\t");
          if (_jspx_meth_liferay$1ui_search$1iterator_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1ui_search$1container_0, _jspx_page_context))
            return;
          out.write("\n\t\t");
        }
        if (_jspx_th_liferay$1ui_search$1container_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_liferay$1ui_search$1container_total_iteratorURL.reuse(_jspx_th_liferay$1ui_search$1container_0);
          return;
        }
        _jspx_tagPool_liferay$1ui_search$1container_total_iteratorURL.reuse(_jspx_th_liferay$1ui_search$1container_0);
        out.write('\n');
        out.write('	');
      }
      if (_jspx_th_aui_form_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_aui_form_name_method.reuse(_jspx_th_aui_form_0);
        return;
      }
      _jspx_tagPool_aui_form_name_method.reuse(_jspx_th_aui_form_0);
      out.write("\n</div>\n\n");
      //  aui:script
      com.liferay.taglib.aui.ScriptTag _jspx_th_aui_script_0 = (com.liferay.taglib.aui.ScriptTag) _jspx_tagPool_aui_script.get(com.liferay.taglib.aui.ScriptTag.class);
      _jspx_th_aui_script_0.setPageContext(_jspx_page_context);
      _jspx_th_aui_script_0.setParent(null);
      int _jspx_eval_aui_script_0 = _jspx_th_aui_script_0.doStartTag();
      if (_jspx_eval_aui_script_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        if (_jspx_eval_aui_script_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
          out = _jspx_page_context.pushBody();
          _jspx_th_aui_script_0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
          _jspx_th_aui_script_0.doInitBody();
        }
        do {
          out.write("\n\tLiferay.Util.selectEntityHandler('#");
          if (_jspx_meth_portlet_namespace_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_0, _jspx_page_context))
            return;
          out.write("selectFolderFm', '");
          out.print( HtmlUtil.escapeJS(eventName) );
          out.write("');\n");
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

  private boolean _jspx_meth_portlet_param_0(javax.servlet.jsp.tagext.JspTag _jspx_th_portlet_renderURL_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:param
    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_0 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
    _jspx_th_portlet_param_0.setPageContext(_jspx_page_context);
    _jspx_th_portlet_param_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_0);
    _jspx_th_portlet_param_0.setName("mvcRenderCommandName");
    _jspx_th_portlet_param_0.setValue("/bookmarks/select_folder");
    int _jspx_eval_portlet_param_0 = _jspx_th_portlet_param_0.doStartTag();
    if (_jspx_th_portlet_param_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_0);
      return true;
    }
    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_0);
    return false;
  }

  private boolean _jspx_meth_portlet_param_2(javax.servlet.jsp.tagext.JspTag _jspx_th_portlet_renderURL_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:param
    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_2 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
    _jspx_th_portlet_param_2.setPageContext(_jspx_page_context);
    _jspx_th_portlet_param_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_1);
    _jspx_th_portlet_param_2.setName("mvcRenderCommandName");
    _jspx_th_portlet_param_2.setValue("/bookmarks/edit_folder");
    int _jspx_eval_portlet_param_2 = _jspx_th_portlet_param_2.doStartTag();
    if (_jspx_th_portlet_param_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_2);
      return true;
    }
    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_2);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_search$1iterator_0(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1ui_search$1container_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:search-iterator
    com.liferay.taglib.ui.SearchIteratorTag _jspx_th_liferay$1ui_search$1iterator_0 = (com.liferay.taglib.ui.SearchIteratorTag) _jspx_tagPool_liferay$1ui_search$1iterator_nobody.get(com.liferay.taglib.ui.SearchIteratorTag.class);
    _jspx_th_liferay$1ui_search$1iterator_0.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_search$1iterator_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container_0);
    int _jspx_eval_liferay$1ui_search$1iterator_0 = _jspx_th_liferay$1ui_search$1iterator_0.doStartTag();
    if (_jspx_th_liferay$1ui_search$1iterator_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_search$1iterator_nobody.reuse(_jspx_th_liferay$1ui_search$1iterator_0);
      return true;
    }
    _jspx_tagPool_liferay$1ui_search$1iterator_nobody.reuse(_jspx_th_liferay$1ui_search$1iterator_0);
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
}
