package org.apache.jsp.blogs;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.liferay.asset.constants.AssetWebKeys;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.kernel.model.AssetTag;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetEntryServiceUtil;
import com.liferay.asset.kernel.service.AssetTagLocalServiceUtil;
import com.liferay.asset.util.AssetHelper;
import com.liferay.blogs.configuration.BlogsGroupServiceOverriddenConfiguration;
import com.liferay.blogs.constants.BlogsConstants;
import com.liferay.blogs.constants.BlogsPortletKeys;
import com.liferay.blogs.exception.EntryContentException;
import com.liferay.blogs.exception.EntryCoverImageCropException;
import com.liferay.blogs.exception.EntryDescriptionException;
import com.liferay.blogs.exception.EntrySmallImageNameException;
import com.liferay.blogs.exception.EntrySmallImageScaleException;
import com.liferay.blogs.exception.EntryTitleException;
import com.liferay.blogs.exception.EntryUrlTitleException;
import com.liferay.blogs.exception.NoSuchEntryException;
import com.liferay.blogs.model.BlogsEntry;
import com.liferay.blogs.service.BlogsEntryLocalServiceUtil;
import com.liferay.blogs.service.BlogsEntryServiceUtil;
import com.liferay.blogs.settings.BlogsGroupServiceSettings;
import com.liferay.blogs.util.comparator.EntryModifiedDateComparator;
import com.liferay.blogs.web.constants.BlogsWebKeys;
import com.liferay.blogs.web.internal.BlogsItemSelectorHelper;
import com.liferay.blogs.web.internal.configuration.BlogsPortletInstanceConfiguration;
import com.liferay.blogs.web.internal.display.context.BlogEntriesDisplayContext;
import com.liferay.blogs.web.internal.display.context.BlogEntriesManagementToolbarDisplayContext;
import com.liferay.blogs.web.internal.display.context.BlogImagesDisplayContext;
import com.liferay.blogs.web.internal.display.context.BlogImagesManagementToolbarDisplayContext;
import com.liferay.blogs.web.internal.display.context.BlogsPortletInstanceSettingsHelper;
import com.liferay.blogs.web.internal.security.permission.resource.BlogsEntryPermission;
import com.liferay.blogs.web.internal.security.permission.resource.BlogsPermission;
import com.liferay.blogs.web.internal.social.SocialBookmarksUtil;
import com.liferay.blogs.web.internal.util.BlogsEntryUtil;
import com.liferay.blogs.web.internal.util.BlogsUtil;
import com.liferay.document.library.display.context.DLMimeTypeDisplayContext;
import com.liferay.document.library.kernel.exception.FileSizeException;
import com.liferay.document.library.kernel.util.DLUtil;
import com.liferay.friendly.url.exception.DuplicateFriendlyURLEntryException;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.JSPNavigationItemList;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.bean.BeanParamUtil;
import com.liferay.portal.kernel.comment.CommentManagerUtil;
import com.liferay.portal.kernel.comment.Discussion;
import com.liferay.portal.kernel.dao.search.EmptyOnClickRowChecker;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.dao.search.SearchContainerResults;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.module.configuration.ConfigurationProviderUtil;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.portlet.PortletProvider;
import com.liferay.portal.kernel.portlet.PortletProviderUtil;
import com.liferay.portal.kernel.portlet.PortletURLUtil;
import com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactory;
import com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.ResourceActionsUtil;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.liferay.portal.kernel.service.PortletLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContextFunction;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.service.WorkflowDefinitionLinkLocalServiceUtil;
import com.liferay.portal.kernel.settings.GroupServiceSettingsLocator;
import com.liferay.portal.kernel.settings.ParameterMapSettingsLocator;
import com.liferay.portal.kernel.settings.PortletInstanceSettingsLocator;
import com.liferay.portal.kernel.upload.LiferayFileItemException;
import com.liferay.portal.kernel.upload.UploadRequestSizeException;
import com.liferay.portal.kernel.upload.UploadServletRequestConfigurationHelperUtil;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.TextFormatter;
import com.liferay.portal.kernel.util.URLCodec;
import com.liferay.portal.kernel.util.UnicodeFormatter;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.upload.LiferayFileItem;
import com.liferay.portal.util.PropsValues;
import com.liferay.ratings.kernel.model.RatingsEntry;
import com.liferay.ratings.kernel.model.RatingsStats;
import com.liferay.ratings.kernel.service.RatingsEntryLocalServiceUtil;
import com.liferay.ratings.kernel.service.RatingsStatsLocalServiceUtil;
import com.liferay.rss.util.RSSUtil;
import com.liferay.taglib.search.ResultRow;
import java.text.Format;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.portlet.PortletURL;
import javax.portlet.WindowState;
import com.liferay.document.library.kernel.util.DLValidatorUtil;

public final class view_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {


private List<BlogsEntry> _convertToBlogEntries(List results) throws PortalException, SystemException {
	if (results.isEmpty() || (results.get(0) instanceof BlogsEntry)) {
		return results;
	}

	List<BlogsEntry> entries = new ArrayList<>(results.size());

	for (Object result : results) {
		AssetEntry assetEntry = (AssetEntry)result;

		BlogsEntry entry = BlogsEntryLocalServiceUtil.getEntry(assetEntry.getClassPK());

		entries.add(entry);
	}

	return entries;
}

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(5);
    _jspx_dependants.add("/blogs/init.jsp");
    _jspx_dependants.add("/init.jsp");
    _jspx_dependants.add("/init-ext.jsp");
    _jspx_dependants.add("/blogs/init-ext.jsp");
    _jspx_dependants.add("/blogs/view_entries.jspf");
  }

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_actionURL_var_name;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1asset_categorization$1filter_portletURL_assetType_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_param_value_name_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1trash_undo_portletURL_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_if_test;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_input_value_type_name_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ddm_template$1renderer_entries_displayStyleGroupId_displayStyle_contextObjects_className;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1theme_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_clay_navigation$1bar_navigationItems_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1frontend_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_empty$1result$1message_message_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1util_include_servletContext_page_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1paginator_searchContainer_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1trash_defineObjects_nobody;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_portlet_actionURL_var_name = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1asset_categorization$1filter_portletURL_assetType_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_param_value_name_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1trash_undo_portletURL_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_if_test = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_input_value_type_name_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ddm_template$1renderer_entries_displayStyleGroupId_displayStyle_contextObjects_className = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1theme_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_clay_navigation$1bar_navigationItems_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1frontend_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_empty$1result$1message_message_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1util_include_servletContext_page_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1paginator_searchContainer_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1trash_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_portlet_actionURL_var_name.release();
    _jspx_tagPool_liferay$1asset_categorization$1filter_portletURL_assetType_nobody.release();
    _jspx_tagPool_portlet_param_value_name_nobody.release();
    _jspx_tagPool_liferay$1trash_undo_portletURL_nobody.release();
    _jspx_tagPool_c_if_test.release();
    _jspx_tagPool_aui_input_value_type_name_nobody.release();
    _jspx_tagPool_portlet_defineObjects_nobody.release();
    _jspx_tagPool_liferay$1ddm_template$1renderer_entries_displayStyleGroupId_displayStyle_contextObjects_className.release();
    _jspx_tagPool_liferay$1theme_defineObjects_nobody.release();
    _jspx_tagPool_clay_navigation$1bar_navigationItems_nobody.release();
    _jspx_tagPool_liferay$1frontend_defineObjects_nobody.release();
    _jspx_tagPool_liferay$1ui_empty$1result$1message_message_nobody.release();
    _jspx_tagPool_liferay$1util_include_servletContext_page_nobody.release();
    _jspx_tagPool_liferay$1ui_search$1paginator_searchContainer_nobody.release();
    _jspx_tagPool_liferay$1trash_defineObjects_nobody.release();
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

AssetHelper assetHelper = (AssetHelper)request.getAttribute(AssetWebKeys.ASSET_HELPER);

Format dateFormatDate = FastDateFormatFactoryUtil.getDate(locale, timeZone);

      out.write('\n');
      out.write('\n');
      out.write("\n\n\n\n");

String portletResource = ParamUtil.getString(request, "portletResource");

String portletId = portletDisplay.getId();

if (Validator.isNotNull(portletResource)) {
	portletId = portletResource;
	portletName = portletResource;
}

BlogsGroupServiceSettings blogsGroupServiceSettings = BlogsGroupServiceSettings.getInstance(scopeGroupId);
BlogsPortletInstanceConfiguration blogsPortletInstanceConfiguration = ConfigurationProviderUtil.getConfiguration(BlogsPortletInstanceConfiguration.class, new PortletInstanceSettingsLocator(themeDisplay.getLayout(), portletDisplay.getId()));

BlogsPortletInstanceSettingsHelper blogsPortletInstanceSettingsHelper = new BlogsPortletInstanceSettingsHelper(request, blogsPortletInstanceConfiguration);

int pageAbstractLength = PropsValues.BLOGS_PAGE_ABSTRACT_LENGTH;

      out.write('\n');
      out.write('\n');
      out.write('\n');
      out.write('\n');

String mvcRenderCommandName = ParamUtil.getString(request, "mvcRenderCommandName");

long assetCategoryId = ParamUtil.getLong(request, "categoryId");
String assetTagName = ParamUtil.getString(request, "tag");

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcRenderCommandName", "/blogs/view");

      out.write('\n');
      out.write('\n');
      //  portlet:actionURL
      com.liferay.taglib.portlet.ActionURLTag _jspx_th_portlet_actionURL_0 = (com.liferay.taglib.portlet.ActionURLTag) _jspx_tagPool_portlet_actionURL_var_name.get(com.liferay.taglib.portlet.ActionURLTag.class);
      _jspx_th_portlet_actionURL_0.setPageContext(_jspx_page_context);
      _jspx_th_portlet_actionURL_0.setParent(null);
      _jspx_th_portlet_actionURL_0.setName("/blogs/edit_entry");
      _jspx_th_portlet_actionURL_0.setVar("restoreTrashEntriesURL");
      int _jspx_eval_portlet_actionURL_0 = _jspx_th_portlet_actionURL_0.doStartTag();
      if (_jspx_eval_portlet_actionURL_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        out.write('\n');
        out.write('	');
        //  portlet:param
        com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_0 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
        _jspx_th_portlet_param_0.setPageContext(_jspx_page_context);
        _jspx_th_portlet_param_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_actionURL_0);
        _jspx_th_portlet_param_0.setName( Constants.CMD );
        _jspx_th_portlet_param_0.setValue( Constants.RESTORE );
        int _jspx_eval_portlet_param_0 = _jspx_th_portlet_param_0.doStartTag();
        if (_jspx_th_portlet_param_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_0);
          return;
        }
        _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_0);
        out.write('\n');
      }
      if (_jspx_th_portlet_actionURL_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_portlet_actionURL_var_name.reuse(_jspx_th_portlet_actionURL_0);
        return;
      }
      _jspx_tagPool_portlet_actionURL_var_name.reuse(_jspx_th_portlet_actionURL_0);
      java.lang.String restoreTrashEntriesURL = null;
      restoreTrashEntriesURL = (java.lang.String) _jspx_page_context.findAttribute("restoreTrashEntriesURL");
      out.write('\n');
      out.write('\n');
      //  liferay-trash:undo
      com.liferay.trash.taglib.servlet.taglib.UndoTag _jspx_th_liferay$1trash_undo_0 = (com.liferay.trash.taglib.servlet.taglib.UndoTag) _jspx_tagPool_liferay$1trash_undo_portletURL_nobody.get(com.liferay.trash.taglib.servlet.taglib.UndoTag.class);
      _jspx_th_liferay$1trash_undo_0.setPageContext(_jspx_page_context);
      _jspx_th_liferay$1trash_undo_0.setParent(null);
      _jspx_th_liferay$1trash_undo_0.setPortletURL( restoreTrashEntriesURL );
      int _jspx_eval_liferay$1trash_undo_0 = _jspx_th_liferay$1trash_undo_0.doStartTag();
      if (_jspx_th_liferay$1trash_undo_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_liferay$1trash_undo_portletURL_nobody.reuse(_jspx_th_liferay$1trash_undo_0);
        return;
      }
      _jspx_tagPool_liferay$1trash_undo_portletURL_nobody.reuse(_jspx_th_liferay$1trash_undo_0);
      out.write('\n');
      out.write('\n');
      //  aui:input
      com.liferay.taglib.aui.InputTag _jspx_th_aui_input_0 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
      _jspx_th_aui_input_0.setPageContext(_jspx_page_context);
      _jspx_th_aui_input_0.setParent(null);
      _jspx_th_aui_input_0.setName("redirect");
      _jspx_th_aui_input_0.setType("hidden");
      _jspx_th_aui_input_0.setValue( currentURL );
      int _jspx_eval_aui_input_0 = _jspx_th_aui_input_0.doStartTag();
      if (_jspx_th_aui_input_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_0);
        return;
      }
      _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_0);
      out.write('\n');
      out.write('\n');

int pageDelta = GetterUtil.getInteger(blogsPortletInstanceConfiguration.pageDelta());

SearchContainer searchContainer = new SearchContainer(renderRequest, null, null, SearchContainer.DEFAULT_CUR_PARAM, pageDelta, currentURLObj, null, null);

searchContainer.setDelta(pageDelta);
searchContainer.setDeltaConfigurable(false);

int total = 0;
List results = null;

int notPublishedEntriesCount = BlogsEntryServiceUtil.getGroupUserEntriesCount(scopeGroupId, themeDisplay.getUserId(), new int[] {WorkflowConstants.STATUS_DRAFT, WorkflowConstants.STATUS_PENDING, WorkflowConstants.STATUS_SCHEDULED});

if ((assetCategoryId != 0) || Validator.isNotNull(assetTagName)) {
	SearchContainerResults<AssetEntry> searchContainerResults = BlogsUtil.getSearchContainerResults(searchContainer);

	searchContainer.setTotal(searchContainerResults.getTotal());

	results = searchContainerResults.getResults();
}
else if ((notPublishedEntriesCount > 0) && mvcRenderCommandName.equals("/blogs/view_not_published_entries")) {
	total = notPublishedEntriesCount;

	searchContainer.setTotal(total);

	results = BlogsEntryServiceUtil.getGroupUserEntries(scopeGroupId, themeDisplay.getUserId(), new int[] {WorkflowConstants.STATUS_DRAFT, WorkflowConstants.STATUS_PENDING, WorkflowConstants.STATUS_SCHEDULED}, searchContainer.getStart(), searchContainer.getEnd(), new EntryModifiedDateComparator());

	searchContainer.setResults(results);
}
else {
	int status = WorkflowConstants.STATUS_APPROVED;

	total = BlogsEntryServiceUtil.getGroupEntriesCount(scopeGroupId, status);

	searchContainer.setTotal(total);

	results = BlogsEntryServiceUtil.getGroupEntries(scopeGroupId, status, searchContainer.getStart(), searchContainer.getEnd());
}

searchContainer.setResults(results);

      out.write('\n');
      out.write('\n');
      //  c:if
      com.liferay.taglib.core.IfTag _jspx_th_c_if_0 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
      _jspx_th_c_if_0.setPageContext(_jspx_page_context);
      _jspx_th_c_if_0.setParent(null);
      _jspx_th_c_if_0.setTest( notPublishedEntriesCount > 0 );
      int _jspx_eval_c_if_0 = _jspx_th_c_if_0.doStartTag();
      if (_jspx_eval_c_if_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        out.write('\n');
        out.write('	');
        //  clay:navigation-bar
        com.liferay.frontend.taglib.clay.servlet.taglib.soy.NavigationBarTag _jspx_th_clay_navigation$1bar_0 = (com.liferay.frontend.taglib.clay.servlet.taglib.soy.NavigationBarTag) _jspx_tagPool_clay_navigation$1bar_navigationItems_nobody.get(com.liferay.frontend.taglib.clay.servlet.taglib.soy.NavigationBarTag.class);
        _jspx_th_clay_navigation$1bar_0.setPageContext(_jspx_page_context);
        _jspx_th_clay_navigation$1bar_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_0);
        _jspx_th_clay_navigation$1bar_0.setNavigationItems(
			new JSPNavigationItemList(pageContext) {
				{
					add(
						navigationItem -> {
							navigationItem.setActive(!mvcRenderCommandName.equals("/blogs/view_not_published_entries"));
							navigationItem.setHref(portletURL);
							navigationItem.setLabel(LanguageUtil.get(request, "published"));
						});

					add(
						navigationItem -> {
							navigationItem.setActive(mvcRenderCommandName.equals("/blogs/view_not_published_entries"));
							navigationItem.setHref(renderResponse.createRenderURL(), "mvcRenderCommandName", "/blogs/view_not_published_entries");
							navigationItem.setLabel(LanguageUtil.format(request, "not-published-x", notPublishedEntriesCount, false));
						});
				}
			}
		);
        int _jspx_eval_clay_navigation$1bar_0 = _jspx_th_clay_navigation$1bar_0.doStartTag();
        if (_jspx_th_clay_navigation$1bar_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_clay_navigation$1bar_navigationItems_nobody.reuse(_jspx_th_clay_navigation$1bar_0);
          return;
        }
        _jspx_tagPool_clay_navigation$1bar_navigationItems_nobody.reuse(_jspx_th_clay_navigation$1bar_0);
        out.write('\n');
      }
      if (_jspx_th_c_if_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
        return;
      }
      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
      out.write('\n');
      out.write('\n');
      out.write('\n');
      out.write('\n');
      //  liferay-asset:categorization-filter
      com.liferay.asset.taglib.servlet.taglib.CategorizationFilterTag _jspx_th_liferay$1asset_categorization$1filter_0 = (com.liferay.asset.taglib.servlet.taglib.CategorizationFilterTag) _jspx_tagPool_liferay$1asset_categorization$1filter_portletURL_assetType_nobody.get(com.liferay.asset.taglib.servlet.taglib.CategorizationFilterTag.class);
      _jspx_th_liferay$1asset_categorization$1filter_0.setPageContext(_jspx_page_context);
      _jspx_th_liferay$1asset_categorization$1filter_0.setParent(null);
      _jspx_th_liferay$1asset_categorization$1filter_0.setAssetType("entries");
      _jspx_th_liferay$1asset_categorization$1filter_0.setPortletURL( portletURL );
      int _jspx_eval_liferay$1asset_categorization$1filter_0 = _jspx_th_liferay$1asset_categorization$1filter_0.doStartTag();
      if (_jspx_th_liferay$1asset_categorization$1filter_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_liferay$1asset_categorization$1filter_portletURL_assetType_nobody.reuse(_jspx_th_liferay$1asset_categorization$1filter_0);
        return;
      }
      _jspx_tagPool_liferay$1asset_categorization$1filter_portletURL_assetType_nobody.reuse(_jspx_th_liferay$1asset_categorization$1filter_0);
      out.write('\n');
      out.write('\n');
      //  c:if
      com.liferay.taglib.core.IfTag _jspx_th_c_if_1 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
      _jspx_th_c_if_1.setPageContext(_jspx_page_context);
      _jspx_th_c_if_1.setParent(null);
      _jspx_th_c_if_1.setTest( results.isEmpty() );
      int _jspx_eval_c_if_1 = _jspx_th_c_if_1.doStartTag();
      if (_jspx_eval_c_if_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        out.write('\n');
        out.write('	');
        if (_jspx_meth_liferay$1ui_empty$1result$1message_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_1, _jspx_page_context))
          return;
        out.write('\n');
      }
      if (_jspx_th_c_if_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_1);
        return;
      }
      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_1);
      out.write('\n');
      out.write('\n');

Map<String, Object> contextObjects = new HashMap<>();

contextObjects.put("blogsPortletInstanceConfiguration", blogsPortletInstanceConfiguration);
contextObjects.put("blogsPortletInstanceSettingsHelper", blogsPortletInstanceSettingsHelper);

List<BlogsEntry> blogsEntries = _convertToBlogEntries(results);

      out.write('\n');
      out.write('\n');
      //  liferay-ddm:template-renderer
      com.liferay.dynamic.data.mapping.taglib.servlet.taglib.TemplateRendererTag _jspx_th_liferay$1ddm_template$1renderer_0 = (com.liferay.dynamic.data.mapping.taglib.servlet.taglib.TemplateRendererTag) _jspx_tagPool_liferay$1ddm_template$1renderer_entries_displayStyleGroupId_displayStyle_contextObjects_className.get(com.liferay.dynamic.data.mapping.taglib.servlet.taglib.TemplateRendererTag.class);
      _jspx_th_liferay$1ddm_template$1renderer_0.setPageContext(_jspx_page_context);
      _jspx_th_liferay$1ddm_template$1renderer_0.setParent(null);
      _jspx_th_liferay$1ddm_template$1renderer_0.setClassName( BlogsEntry.class.getName() );
      _jspx_th_liferay$1ddm_template$1renderer_0.setContextObjects( contextObjects );
      _jspx_th_liferay$1ddm_template$1renderer_0.setDisplayStyle( blogsPortletInstanceConfiguration.displayStyle() );
      _jspx_th_liferay$1ddm_template$1renderer_0.setDisplayStyleGroupId( blogsPortletInstanceSettingsHelper.getDisplayStyleGroupId() );
      _jspx_th_liferay$1ddm_template$1renderer_0.setEntries( blogsEntries );
      int _jspx_eval_liferay$1ddm_template$1renderer_0 = _jspx_th_liferay$1ddm_template$1renderer_0.doStartTag();
      if (_jspx_eval_liferay$1ddm_template$1renderer_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        out.write("\n\t<div class=\"widget-mode-simple\">\n\n\t\t");

		long[] classPKs = ListUtil.toLongArray(blogsEntries, BlogsEntry.ENTRY_ID_ACCESSOR);

		Map<Long, RatingsEntry> ratingsEntries = null;
		Map<Long, RatingsStats> ratingsStats = RatingsStatsLocalServiceUtil.getStats(BlogsEntry.class.getName(), classPKs);

		if (ratingsStats.isEmpty()) {
			ratingsEntries = Collections.emptyMap();
		}
		else {
			ratingsEntries = RatingsEntryLocalServiceUtil.getEntries(themeDisplay.getUserId(), BlogsEntry.class.getName(), classPKs);
		}

		int index = 0;

		for (Object result : results) {
			BlogsEntry entry = blogsEntries.get(index);

			AssetEntry assetEntry = null;

			if (result instanceof AssetEntry) {
				assetEntry = (AssetEntry)result;

				assetHelper.addLayoutTags(request, assetEntry.getTags());
			}

			index++;

			request.setAttribute("view_entry_content.jsp-searchContainer", searchContainer);

			request.setAttribute("view_entry_content.jsp-entry", entry);

			request.setAttribute("view_entry_content.jsp-assetEntry", assetEntry);

			request.setAttribute("view_entry_content.jsp-ratingsEntry", ratingsEntries.get(entry.getEntryId()));
			request.setAttribute("view_entry_content.jsp-ratingsStats", ratingsStats.get(entry.getEntryId()));
		
        out.write("\n\n\t\t\t");
        //  liferay-util:include
        com.liferay.taglib.util.IncludeTag _jspx_th_liferay$1util_include_0 = (com.liferay.taglib.util.IncludeTag) _jspx_tagPool_liferay$1util_include_servletContext_page_nobody.get(com.liferay.taglib.util.IncludeTag.class);
        _jspx_th_liferay$1util_include_0.setPageContext(_jspx_page_context);
        _jspx_th_liferay$1util_include_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ddm_template$1renderer_0);
        _jspx_th_liferay$1util_include_0.setPage("/blogs/view_entry_content.jsp");
        _jspx_th_liferay$1util_include_0.setServletContext( application );
        int _jspx_eval_liferay$1util_include_0 = _jspx_th_liferay$1util_include_0.doStartTag();
        if (_jspx_th_liferay$1util_include_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_liferay$1util_include_servletContext_page_nobody.reuse(_jspx_th_liferay$1util_include_0);
          return;
        }
        _jspx_tagPool_liferay$1util_include_servletContext_page_nobody.reuse(_jspx_th_liferay$1util_include_0);
        out.write("\n\n\t\t");

		}
		
        out.write("\n\n\t</div>\n");
      }
      if (_jspx_th_liferay$1ddm_template$1renderer_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_liferay$1ddm_template$1renderer_entries_displayStyleGroupId_displayStyle_contextObjects_className.reuse(_jspx_th_liferay$1ddm_template$1renderer_0);
        return;
      }
      _jspx_tagPool_liferay$1ddm_template$1renderer_entries_displayStyleGroupId_displayStyle_contextObjects_className.reuse(_jspx_th_liferay$1ddm_template$1renderer_0);
      out.write('\n');
      out.write('\n');
      //  liferay-ui:search-paginator
      com.liferay.taglib.ui.SearchPaginatorTag _jspx_th_liferay$1ui_search$1paginator_0 = (com.liferay.taglib.ui.SearchPaginatorTag) _jspx_tagPool_liferay$1ui_search$1paginator_searchContainer_nobody.get(com.liferay.taglib.ui.SearchPaginatorTag.class);
      _jspx_th_liferay$1ui_search$1paginator_0.setPageContext(_jspx_page_context);
      _jspx_th_liferay$1ui_search$1paginator_0.setParent(null);
      _jspx_th_liferay$1ui_search$1paginator_0.setSearchContainer( searchContainer );
      int _jspx_eval_liferay$1ui_search$1paginator_0 = _jspx_th_liferay$1ui_search$1paginator_0.doStartTag();
      if (_jspx_th_liferay$1ui_search$1paginator_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_liferay$1ui_search$1paginator_searchContainer_nobody.reuse(_jspx_th_liferay$1ui_search$1paginator_0);
        return;
      }
      _jspx_tagPool_liferay$1ui_search$1paginator_searchContainer_nobody.reuse(_jspx_th_liferay$1ui_search$1paginator_0);
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

  private boolean _jspx_meth_liferay$1ui_empty$1result$1message_0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:empty-result-message
    com.liferay.taglib.ui.EmptyResultMessageTag _jspx_th_liferay$1ui_empty$1result$1message_0 = (com.liferay.taglib.ui.EmptyResultMessageTag) _jspx_tagPool_liferay$1ui_empty$1result$1message_message_nobody.get(com.liferay.taglib.ui.EmptyResultMessageTag.class);
    _jspx_th_liferay$1ui_empty$1result$1message_0.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_empty$1result$1message_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_1);
    _jspx_th_liferay$1ui_empty$1result$1message_0.setMessage("no-entries-were-found");
    int _jspx_eval_liferay$1ui_empty$1result$1message_0 = _jspx_th_liferay$1ui_empty$1result$1message_0.doStartTag();
    if (_jspx_th_liferay$1ui_empty$1result$1message_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_empty$1result$1message_message_nobody.reuse(_jspx_th_liferay$1ui_empty$1result$1message_0);
      return true;
    }
    _jspx_tagPool_liferay$1ui_empty$1result$1message_message_nobody.reuse(_jspx_th_liferay$1ui_empty$1result$1message_0);
    return false;
  }
}