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

public final class view_005fentry_005fcontent_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {


private AssetEntry _getAssetEntry(HttpServletRequest request, BlogsEntry entry) throws PortalException, SystemException {
	AssetEntry assetEntry = (AssetEntry)request.getAttribute("view_entry_content.jsp-assetEntry");

	if (assetEntry == null) {
		assetEntry = AssetEntryLocalServiceUtil.getEntry(BlogsEntry.class.getName(), entry.getEntryId());

		request.setAttribute("view_entry_content.jsp-assetEntry", assetEntry);
	}

	return assetEntry;
}

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(4);
    _jspx_dependants.add("/blogs/init.jsp");
    _jspx_dependants.add("/init.jsp");
    _jspx_dependants.add("/init-ext.jsp");
    _jspx_dependants.add("/blogs/init-ext.jsp");
  }

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_namespace_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_user$1portrait_user_cssClass_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_message_key_arguments_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_param_value_name_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_message_key_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_if_test;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1asset_asset$1tags$1available_classPK_className;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_renderURL_var;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_otherwise;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1asset_asset$1categories$1summary_portletURL_classPK_className_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1theme_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_choose;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1asset_asset$1categories$1available_classPK_className;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1frontend_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_a_href_cssClass;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1asset_asset$1links_classPK_className_assetEntryId_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1util_include_servletContext_page_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1reading$1time_reading$1time_model_displayStyle_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1trash_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1asset_asset$1tags$1summary_portletURL_classPK_className_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_when_test;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_portlet_namespace_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_user$1portrait_user_cssClass_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_message_key_arguments_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_param_value_name_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_message_key_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_if_test = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1asset_asset$1tags$1available_classPK_className = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_renderURL_var = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_otherwise = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1asset_asset$1categories$1summary_portletURL_classPK_className_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1theme_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_choose = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1asset_asset$1categories$1available_classPK_className = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1frontend_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_a_href_cssClass = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1asset_asset$1links_classPK_className_assetEntryId_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1util_include_servletContext_page_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1reading$1time_reading$1time_model_displayStyle_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1trash_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1asset_asset$1tags$1summary_portletURL_classPK_className_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_when_test = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_portlet_namespace_nobody.release();
    _jspx_tagPool_liferay$1ui_user$1portrait_user_cssClass_nobody.release();
    _jspx_tagPool_liferay$1ui_message_key_arguments_nobody.release();
    _jspx_tagPool_portlet_param_value_name_nobody.release();
    _jspx_tagPool_liferay$1ui_message_key_nobody.release();
    _jspx_tagPool_c_if_test.release();
    _jspx_tagPool_liferay$1asset_asset$1tags$1available_classPK_className.release();
    _jspx_tagPool_portlet_renderURL_var.release();
    _jspx_tagPool_c_otherwise.release();
    _jspx_tagPool_portlet_defineObjects_nobody.release();
    _jspx_tagPool_liferay$1asset_asset$1categories$1summary_portletURL_classPK_className_nobody.release();
    _jspx_tagPool_liferay$1theme_defineObjects_nobody.release();
    _jspx_tagPool_c_choose.release();
    _jspx_tagPool_liferay$1asset_asset$1categories$1available_classPK_className.release();
    _jspx_tagPool_liferay$1frontend_defineObjects_nobody.release();
    _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody.release();
    _jspx_tagPool_aui_a_href_cssClass.release();
    _jspx_tagPool_liferay$1asset_asset$1links_classPK_className_assetEntryId_nobody.release();
    _jspx_tagPool_liferay$1util_include_servletContext_page_nobody.release();
    _jspx_tagPool_liferay$1reading$1time_reading$1time_model_displayStyle_nobody.release();
    _jspx_tagPool_liferay$1trash_defineObjects_nobody.release();
    _jspx_tagPool_liferay$1asset_asset$1tags$1summary_portletURL_classPK_className_nobody.release();
    _jspx_tagPool_c_when_test.release();
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

SearchContainer searchContainer = (SearchContainer)request.getAttribute("view_entry_content.jsp-searchContainer");

BlogsEntry entry = (BlogsEntry)request.getAttribute("view_entry_content.jsp-entry");

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
        _jspx_th_c_when_0.setTest( BlogsEntryPermission.contains(permissionChecker, entry, ActionKeys.VIEW) && (entry.isVisible() || (entry.getUserId() == user.getUserId()) || BlogsEntryPermission.contains(permissionChecker, entry, ActionKeys.UPDATE)) );
        int _jspx_eval_c_when_0 = _jspx_th_c_when_0.doStartTag();
        if (_jspx_eval_c_when_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          out.write("\n\t\t");
          //  portlet:renderURL
          com.liferay.taglib.portlet.RenderURLTag _jspx_th_portlet_renderURL_0 = (com.liferay.taglib.portlet.RenderURLTag) _jspx_tagPool_portlet_renderURL_var.get(com.liferay.taglib.portlet.RenderURLTag.class);
          _jspx_th_portlet_renderURL_0.setPageContext(_jspx_page_context);
          _jspx_th_portlet_renderURL_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_0);
          _jspx_th_portlet_renderURL_0.setVar("viewEntryURL");
          int _jspx_eval_portlet_renderURL_0 = _jspx_th_portlet_renderURL_0.doStartTag();
          if (_jspx_eval_portlet_renderURL_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t\t");
            if (_jspx_meth_portlet_param_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_portlet_renderURL_0, _jspx_page_context))
              return;
            out.write("\n\t\t\t");
            //  portlet:param
            com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_1 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
            _jspx_th_portlet_param_1.setPageContext(_jspx_page_context);
            _jspx_th_portlet_param_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_0);
            _jspx_th_portlet_param_1.setName("redirect");
            _jspx_th_portlet_param_1.setValue( currentURL );
            int _jspx_eval_portlet_param_1 = _jspx_th_portlet_param_1.doStartTag();
            if (_jspx_th_portlet_param_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_1);
              return;
            }
            _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_1);
            out.write("\n\n\t\t\t");
            //  c:choose
            com.liferay.taglib.core.ChooseTag _jspx_th_c_choose_1 = (com.liferay.taglib.core.ChooseTag) _jspx_tagPool_c_choose.get(com.liferay.taglib.core.ChooseTag.class);
            _jspx_th_c_choose_1.setPageContext(_jspx_page_context);
            _jspx_th_c_choose_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_0);
            int _jspx_eval_c_choose_1 = _jspx_th_c_choose_1.doStartTag();
            if (_jspx_eval_c_choose_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\t\t\t\t");
              //  c:when
              com.liferay.taglib.core.WhenTag _jspx_th_c_when_1 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
              _jspx_th_c_when_1.setPageContext(_jspx_page_context);
              _jspx_th_c_when_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_1);
              _jspx_th_c_when_1.setTest( Validator.isNotNull(entry.getUrlTitle()) );
              int _jspx_eval_c_when_1 = _jspx_th_c_when_1.doStartTag();
              if (_jspx_eval_c_when_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\t\t\t\t\t");
                //  portlet:param
                com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_2 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                _jspx_th_portlet_param_2.setPageContext(_jspx_page_context);
                _jspx_th_portlet_param_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_1);
                _jspx_th_portlet_param_2.setName("urlTitle");
                _jspx_th_portlet_param_2.setValue( entry.getUrlTitle() );
                int _jspx_eval_portlet_param_2 = _jspx_th_portlet_param_2.doStartTag();
                if (_jspx_th_portlet_param_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_2);
                  return;
                }
                _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_2);
                out.write("\n\t\t\t\t");
              }
              if (_jspx_th_c_when_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_1);
                return;
              }
              _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_1);
              out.write("\n\t\t\t\t");
              //  c:otherwise
              com.liferay.taglib.core.OtherwiseTag _jspx_th_c_otherwise_0 = (com.liferay.taglib.core.OtherwiseTag) _jspx_tagPool_c_otherwise.get(com.liferay.taglib.core.OtherwiseTag.class);
              _jspx_th_c_otherwise_0.setPageContext(_jspx_page_context);
              _jspx_th_c_otherwise_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_1);
              int _jspx_eval_c_otherwise_0 = _jspx_th_c_otherwise_0.doStartTag();
              if (_jspx_eval_c_otherwise_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\t\t\t\t\t");
                //  portlet:param
                com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_3 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                _jspx_th_portlet_param_3.setPageContext(_jspx_page_context);
                _jspx_th_portlet_param_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_0);
                _jspx_th_portlet_param_3.setName("entryId");
                _jspx_th_portlet_param_3.setValue( String.valueOf(entry.getEntryId()) );
                int _jspx_eval_portlet_param_3 = _jspx_th_portlet_param_3.doStartTag();
                if (_jspx_th_portlet_param_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_3);
                  return;
                }
                _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_3);
                out.write("\n\t\t\t\t");
              }
              if (_jspx_th_c_otherwise_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_0);
                return;
              }
              _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_0);
              out.write("\n\t\t\t");
            }
            if (_jspx_th_c_choose_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_1);
              return;
            }
            _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_1);
            out.write("\n\t\t");
          }
          if (_jspx_th_portlet_renderURL_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_portlet_renderURL_var.reuse(_jspx_th_portlet_renderURL_0);
            return;
          }
          _jspx_tagPool_portlet_renderURL_var.reuse(_jspx_th_portlet_renderURL_0);
          java.lang.String viewEntryURL = null;
          viewEntryURL = (java.lang.String) _jspx_page_context.findAttribute("viewEntryURL");
          out.write("\n\n\t\t<div class=\"widget-mode-simple-entry\">\n\t\t\t<div class=\"autofit-row widget-topbar\">\n\t\t\t\t<div class=\"autofit-col autofit-col-expand\">\n\t\t\t\t\t<h3 class=\"title\">\n\t\t\t\t\t\t");
          //  aui:a
          com.liferay.taglib.aui.ATag _jspx_th_aui_a_0 = (com.liferay.taglib.aui.ATag) _jspx_tagPool_aui_a_href_cssClass.get(com.liferay.taglib.aui.ATag.class);
          _jspx_th_aui_a_0.setPageContext(_jspx_page_context);
          _jspx_th_aui_a_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_0);
          _jspx_th_aui_a_0.setCssClass("title-link");
          _jspx_th_aui_a_0.setHref( viewEntryURL );
          int _jspx_eval_aui_a_0 = _jspx_th_aui_a_0.doStartTag();
          if (_jspx_eval_aui_a_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.print( HtmlUtil.escape(BlogsEntryUtil.getDisplayTitle(resourceBundle, entry)) );
          }
          if (_jspx_th_aui_a_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_aui_a_href_cssClass.reuse(_jspx_th_aui_a_0);
            return;
          }
          _jspx_tagPool_aui_a_href_cssClass.reuse(_jspx_th_aui_a_0);
          out.write("\n\t\t\t\t\t</h3>\n\n\t\t\t\t\t");

					String subtitle = entry.getSubtitle();
					
          out.write("\n\n\t\t\t\t\t");
          //  c:if
          com.liferay.taglib.core.IfTag _jspx_th_c_if_0 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
          _jspx_th_c_if_0.setPageContext(_jspx_page_context);
          _jspx_th_c_if_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_0);
          _jspx_th_c_if_0.setTest( blogsPortletInstanceConfiguration.displayStyle().equals(BlogsUtil.DISPLAY_STYLE_FULL_CONTENT) && Validator.isNotNull(subtitle) );
          int _jspx_eval_c_if_0 = _jspx_th_c_if_0.doStartTag();
          if (_jspx_eval_c_if_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t\t\t\t\t<h4 class=\"sub-title\">");
            out.print( HtmlUtil.escape(subtitle) );
            out.write("</h4>\n\t\t\t\t\t");
          }
          if (_jspx_th_c_if_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
            return;
          }
          _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
          out.write("\n\t\t\t\t</div>\n\n\t\t\t\t<div class=\"autofit-col visible-interaction\">\n\t\t\t\t\t<div class=\"dropdown dropdown-action\">\n\t\t\t\t\t\t");
          //  liferay-util:include
          com.liferay.taglib.util.IncludeTag _jspx_th_liferay$1util_include_0 = (com.liferay.taglib.util.IncludeTag) _jspx_tagPool_liferay$1util_include_servletContext_page_nobody.get(com.liferay.taglib.util.IncludeTag.class);
          _jspx_th_liferay$1util_include_0.setPageContext(_jspx_page_context);
          _jspx_th_liferay$1util_include_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_0);
          _jspx_th_liferay$1util_include_0.setPage("/blogs/entry_action.jsp");
          _jspx_th_liferay$1util_include_0.setServletContext( application );
          int _jspx_eval_liferay$1util_include_0 = _jspx_th_liferay$1util_include_0.doStartTag();
          if (_jspx_th_liferay$1util_include_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_liferay$1util_include_servletContext_page_nobody.reuse(_jspx_th_liferay$1util_include_0);
            return;
          }
          _jspx_tagPool_liferay$1util_include_servletContext_page_nobody.reuse(_jspx_th_liferay$1util_include_0);
          out.write("\n\t\t\t\t\t</div>\n\t\t\t\t</div>\n\t\t\t</div>\n\n\t\t\t<div class=\"autofit-row widget-metadata\">\n\t\t\t\t<div class=\"autofit-col inline-item-before\">\n\n\t\t\t\t\t");

					User entryUser = UserLocalServiceUtil.fetchUser(entry.getUserId());

					String entryUserURL = StringPool.BLANK;

					if ((entryUser != null) && !entryUser.isDefaultUser()) {
						entryUserURL = entryUser.getDisplayURL(themeDisplay);
					}
					
          out.write("\n\n\t\t\t\t\t");
          //  liferay-ui:user-portrait
          com.liferay.taglib.ui.UserPortraitTag _jspx_th_liferay$1ui_user$1portrait_0 = (com.liferay.taglib.ui.UserPortraitTag) _jspx_tagPool_liferay$1ui_user$1portrait_user_cssClass_nobody.get(com.liferay.taglib.ui.UserPortraitTag.class);
          _jspx_th_liferay$1ui_user$1portrait_0.setPageContext(_jspx_page_context);
          _jspx_th_liferay$1ui_user$1portrait_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_0);
          _jspx_th_liferay$1ui_user$1portrait_0.setCssClass("user-icon-lg");
          _jspx_th_liferay$1ui_user$1portrait_0.setUser( entryUser );
          int _jspx_eval_liferay$1ui_user$1portrait_0 = _jspx_th_liferay$1ui_user$1portrait_0.doStartTag();
          if (_jspx_th_liferay$1ui_user$1portrait_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_liferay$1ui_user$1portrait_user_cssClass_nobody.reuse(_jspx_th_liferay$1ui_user$1portrait_0);
            return;
          }
          _jspx_tagPool_liferay$1ui_user$1portrait_user_cssClass_nobody.reuse(_jspx_th_liferay$1ui_user$1portrait_0);
          out.write("\n\t\t\t\t</div>\n\n\t\t\t\t<div class=\"autofit-col autofit-col-expand\">\n\t\t\t\t\t<div class=\"autofit-row\">\n\t\t\t\t\t\t<div class=\"autofit-col autofit-col-expand\">\n\t\t\t\t\t\t\t<div class=\"text-truncate-inline\">\n\t\t\t\t\t\t\t\t<a class=\"text-truncate username\" href=\"");
          out.print( entryUserURL );
          out.write('"');
          out.write('>');
          out.print( entry.getUserName() );
          out.write("</a>\n\t\t\t\t\t\t\t</div>\n\n\t\t\t\t\t\t\t<div class=\"text-secondary\">\n\t\t\t\t\t\t\t\t<span class=\"hide-accessible\">");
          if (_jspx_meth_liferay$1ui_message_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_when_0, _jspx_page_context))
            return;
          out.write("</span>");
          //  liferay-ui:message
          com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_1 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody.get(com.liferay.taglib.ui.MessageTag.class);
          _jspx_th_liferay$1ui_message_1.setPageContext(_jspx_page_context);
          _jspx_th_liferay$1ui_message_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_0);
          _jspx_th_liferay$1ui_message_1.setArguments( LanguageUtil.getTimeDescription(request, System.currentTimeMillis() - entry.getStatusDate().getTime(), true) );
          _jspx_th_liferay$1ui_message_1.setKey("x-ago");
          _jspx_th_liferay$1ui_message_1.setTranslateArguments( false );
          int _jspx_eval_liferay$1ui_message_1 = _jspx_th_liferay$1ui_message_1.doStartTag();
          if (_jspx_th_liferay$1ui_message_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody.reuse(_jspx_th_liferay$1ui_message_1);
            return;
          }
          _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody.reuse(_jspx_th_liferay$1ui_message_1);
          out.write("\n\n\t\t\t\t\t\t\t\t");
          //  c:if
          com.liferay.taglib.core.IfTag _jspx_th_c_if_1 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
          _jspx_th_c_if_1.setPageContext(_jspx_page_context);
          _jspx_th_c_if_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_0);
          _jspx_th_c_if_1.setTest( blogsPortletInstanceConfiguration.enableReadingTime() );
          int _jspx_eval_c_if_1 = _jspx_th_c_if_1.doStartTag();
          if (_jspx_eval_c_if_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t\t\t\t\t\t\t\t- ");
            //  liferay-reading-time:reading-time
            com.liferay.reading.time.taglib.servlet.taglib.ReadingTimeTag _jspx_th_liferay$1reading$1time_reading$1time_0 = (com.liferay.reading.time.taglib.servlet.taglib.ReadingTimeTag) _jspx_tagPool_liferay$1reading$1time_reading$1time_model_displayStyle_nobody.get(com.liferay.reading.time.taglib.servlet.taglib.ReadingTimeTag.class);
            _jspx_th_liferay$1reading$1time_reading$1time_0.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1reading$1time_reading$1time_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_1);
            _jspx_th_liferay$1reading$1time_reading$1time_0.setDisplayStyle("descriptive");
            _jspx_th_liferay$1reading$1time_reading$1time_0.setModel( entry );
            int _jspx_eval_liferay$1reading$1time_reading$1time_0 = _jspx_th_liferay$1reading$1time_reading$1time_0.doStartTag();
            if (_jspx_th_liferay$1reading$1time_reading$1time_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_liferay$1reading$1time_reading$1time_model_displayStyle_nobody.reuse(_jspx_th_liferay$1reading$1time_reading$1time_0);
              return;
            }
            _jspx_tagPool_liferay$1reading$1time_reading$1time_model_displayStyle_nobody.reuse(_jspx_th_liferay$1reading$1time_reading$1time_0);
            out.write("\n\t\t\t\t\t\t\t\t");
          }
          if (_jspx_th_c_if_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_1);
            return;
          }
          _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_1);
          out.write("\n\n\t\t\t\t\t\t\t\t");
          //  c:if
          com.liferay.taglib.core.IfTag _jspx_th_c_if_2 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
          _jspx_th_c_if_2.setPageContext(_jspx_page_context);
          _jspx_th_c_if_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_0);
          _jspx_th_c_if_2.setTest( blogsPortletInstanceConfiguration.enableViewCount() );
          int _jspx_eval_c_if_2 = _jspx_th_c_if_2.doStartTag();
          if (_jspx_eval_c_if_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\n\t\t\t\t\t\t\t\t\t");

									AssetEntry assetEntry = _getAssetEntry(request, entry);
									
            out.write("\n\n\t\t\t\t\t\t\t\t\t- ");
            //  liferay-ui:message
            com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_2 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_arguments_nobody.get(com.liferay.taglib.ui.MessageTag.class);
            _jspx_th_liferay$1ui_message_2.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1ui_message_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_2);
            _jspx_th_liferay$1ui_message_2.setArguments( assetEntry.getViewCount() );
            _jspx_th_liferay$1ui_message_2.setKey( (assetEntry.getViewCount() == 1) ? "x-view" : "x-views" );
            int _jspx_eval_liferay$1ui_message_2 = _jspx_th_liferay$1ui_message_2.doStartTag();
            if (_jspx_th_liferay$1ui_message_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_liferay$1ui_message_key_arguments_nobody.reuse(_jspx_th_liferay$1ui_message_2);
              return;
            }
            _jspx_tagPool_liferay$1ui_message_key_arguments_nobody.reuse(_jspx_th_liferay$1ui_message_2);
            out.write("\n\t\t\t\t\t\t\t\t");
          }
          if (_jspx_th_c_if_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_2);
            return;
          }
          _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_2);
          out.write("\n\t\t\t\t\t\t\t</div>\n\t\t\t\t\t\t</div>\n\t\t\t\t\t</div>\n\t\t\t\t</div>\n\t\t\t</div>\n\n\t\t\t<div class=\"widget-content\" id=\"");
          if (_jspx_meth_portlet_namespace_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_when_0, _jspx_page_context))
            return;
          out.print( entry.getEntryId() );
          out.write("\">\n\n\t\t\t\t");

				String coverImageURL = entry.getCoverImageURL(themeDisplay);
				
          out.write("\n\n\t\t\t\t");
          //  c:if
          com.liferay.taglib.core.IfTag _jspx_th_c_if_3 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
          _jspx_th_c_if_3.setPageContext(_jspx_page_context);
          _jspx_th_c_if_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_0);
          _jspx_th_c_if_3.setTest( Validator.isNotNull(coverImageURL) );
          int _jspx_eval_c_if_3 = _jspx_th_c_if_3.doStartTag();
          if (_jspx_eval_c_if_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t\t\t\t<a href=\"");
            out.print( viewEntryURL.toString() );
            out.write("\">\n\t\t\t\t\t\t<div class=\"aspect-ratio aspect-ratio-8-to-3 aspect-ratio-bg-cover cover-image\" style=\"background-image: url(");
            out.print( coverImageURL );
            out.write(")\"></div>\n\t\t\t\t\t</a>\n\t\t\t\t");
          }
          if (_jspx_th_c_if_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_3);
            return;
          }
          _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_3);
          out.write("\n\n\t\t\t\t");
          //  c:choose
          com.liferay.taglib.core.ChooseTag _jspx_th_c_choose_2 = (com.liferay.taglib.core.ChooseTag) _jspx_tagPool_c_choose.get(com.liferay.taglib.core.ChooseTag.class);
          _jspx_th_c_choose_2.setPageContext(_jspx_page_context);
          _jspx_th_c_choose_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_0);
          int _jspx_eval_c_choose_2 = _jspx_th_c_choose_2.doStartTag();
          if (_jspx_eval_c_choose_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t\t\t\t");
            //  c:when
            com.liferay.taglib.core.WhenTag _jspx_th_c_when_2 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
            _jspx_th_c_when_2.setPageContext(_jspx_page_context);
            _jspx_th_c_when_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_2);
            _jspx_th_c_when_2.setTest( blogsPortletInstanceConfiguration.displayStyle().equals(BlogsUtil.DISPLAY_STYLE_ABSTRACT) );
            int _jspx_eval_c_when_2 = _jspx_th_c_when_2.doStartTag();
            if (_jspx_eval_c_when_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\n\t\t\t\t\t\t");

						String summary = entry.getDescription();

						if (Validator.isNull(summary)) {
							summary = entry.getContent();
						}
						
              out.write("\n\n\t\t\t\t\t\t<p>\n\t\t\t\t\t\t\t");
              out.print( StringUtil.shorten(HtmlUtil.stripHtml(summary), pageAbstractLength) );
              out.write("\n\t\t\t\t\t\t</p>\n\t\t\t\t\t");
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
            _jspx_th_c_when_3.setTest( blogsPortletInstanceConfiguration.displayStyle().equals(BlogsUtil.DISPLAY_STYLE_FULL_CONTENT) );
            int _jspx_eval_c_when_3 = _jspx_th_c_when_3.doStartTag();
            if (_jspx_eval_c_when_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\t\t\t\t\t\t");
              out.print( entry.getContent() );
              out.write("\n\t\t\t\t\t");
            }
            if (_jspx_th_c_when_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_3);
              return;
            }
            _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_3);
            out.write("\n\t\t\t\t");
          }
          if (_jspx_th_c_choose_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_2);
            return;
          }
          _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_2);
          out.write("\n\n\t\t\t\t");

				request.setAttribute("entry_toolbar.jsp-entry", entry);
				
          out.write("\n\n\t\t\t\t");
          //  liferay-util:include
          com.liferay.taglib.util.IncludeTag _jspx_th_liferay$1util_include_1 = (com.liferay.taglib.util.IncludeTag) _jspx_tagPool_liferay$1util_include_servletContext_page_nobody.get(com.liferay.taglib.util.IncludeTag.class);
          _jspx_th_liferay$1util_include_1.setPageContext(_jspx_page_context);
          _jspx_th_liferay$1util_include_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_0);
          _jspx_th_liferay$1util_include_1.setPage("/blogs/entry_toolbar.jsp");
          _jspx_th_liferay$1util_include_1.setServletContext( application );
          int _jspx_eval_liferay$1util_include_1 = _jspx_th_liferay$1util_include_1.doStartTag();
          if (_jspx_th_liferay$1util_include_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_liferay$1util_include_servletContext_page_nobody.reuse(_jspx_th_liferay$1util_include_1);
            return;
          }
          _jspx_tagPool_liferay$1util_include_servletContext_page_nobody.reuse(_jspx_th_liferay$1util_include_1);
          out.write("\n\t\t\t</div>\n\n\t\t\t");
          //  c:if
          com.liferay.taglib.core.IfTag _jspx_th_c_if_4 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
          _jspx_th_c_if_4.setPageContext(_jspx_page_context);
          _jspx_th_c_if_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_0);
          _jspx_th_c_if_4.setTest( blogsPortletInstanceConfiguration.displayStyle().equals(BlogsUtil.DISPLAY_STYLE_FULL_CONTENT) );
          int _jspx_eval_c_if_4 = _jspx_th_c_if_4.doStartTag();
          if (_jspx_eval_c_if_4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t\t\t");
            //  liferay-asset:asset-tags-available
            com.liferay.asset.taglib.servlet.taglib.AssetTagsAvailableTag _jspx_th_liferay$1asset_asset$1tags$1available_0 = (com.liferay.asset.taglib.servlet.taglib.AssetTagsAvailableTag) _jspx_tagPool_liferay$1asset_asset$1tags$1available_classPK_className.get(com.liferay.asset.taglib.servlet.taglib.AssetTagsAvailableTag.class);
            _jspx_th_liferay$1asset_asset$1tags$1available_0.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1asset_asset$1tags$1available_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_4);
            _jspx_th_liferay$1asset_asset$1tags$1available_0.setClassName( BlogsEntry.class.getName() );
            _jspx_th_liferay$1asset_asset$1tags$1available_0.setClassPK( entry.getEntryId() );
            int _jspx_eval_liferay$1asset_asset$1tags$1available_0 = _jspx_th_liferay$1asset_asset$1tags$1available_0.doStartTag();
            if (_jspx_eval_liferay$1asset_asset$1tags$1available_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\t\t\t\t\t<div class=\"entry-tags\">\n\t\t\t\t\t\t");
              //  liferay-asset:asset-tags-summary
              com.liferay.asset.taglib.servlet.taglib.AssetTagsSummaryTag _jspx_th_liferay$1asset_asset$1tags$1summary_0 = (com.liferay.asset.taglib.servlet.taglib.AssetTagsSummaryTag) _jspx_tagPool_liferay$1asset_asset$1tags$1summary_portletURL_classPK_className_nobody.get(com.liferay.asset.taglib.servlet.taglib.AssetTagsSummaryTag.class);
              _jspx_th_liferay$1asset_asset$1tags$1summary_0.setPageContext(_jspx_page_context);
              _jspx_th_liferay$1asset_asset$1tags$1summary_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1asset_asset$1tags$1available_0);
              _jspx_th_liferay$1asset_asset$1tags$1summary_0.setClassName( BlogsEntry.class.getName() );
              _jspx_th_liferay$1asset_asset$1tags$1summary_0.setClassPK( entry.getEntryId() );
              _jspx_th_liferay$1asset_asset$1tags$1summary_0.setPortletURL( renderResponse.createRenderURL() );
              int _jspx_eval_liferay$1asset_asset$1tags$1summary_0 = _jspx_th_liferay$1asset_asset$1tags$1summary_0.doStartTag();
              if (_jspx_th_liferay$1asset_asset$1tags$1summary_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_liferay$1asset_asset$1tags$1summary_portletURL_classPK_className_nobody.reuse(_jspx_th_liferay$1asset_asset$1tags$1summary_0);
                return;
              }
              _jspx_tagPool_liferay$1asset_asset$1tags$1summary_portletURL_classPK_className_nobody.reuse(_jspx_th_liferay$1asset_asset$1tags$1summary_0);
              out.write("\n\t\t\t\t\t</div>\n\t\t\t\t");
            }
            if (_jspx_th_liferay$1asset_asset$1tags$1available_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_liferay$1asset_asset$1tags$1available_classPK_className.reuse(_jspx_th_liferay$1asset_asset$1tags$1available_0);
              return;
            }
            _jspx_tagPool_liferay$1asset_asset$1tags$1available_classPK_className.reuse(_jspx_th_liferay$1asset_asset$1tags$1available_0);
            out.write("\n\n\t\t\t\t");
            //  c:if
            com.liferay.taglib.core.IfTag _jspx_th_c_if_5 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
            _jspx_th_c_if_5.setPageContext(_jspx_page_context);
            _jspx_th_c_if_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_4);
            _jspx_th_c_if_5.setTest( blogsPortletInstanceConfiguration.enableRelatedAssets() );
            int _jspx_eval_c_if_5 = _jspx_th_c_if_5.doStartTag();
            if (_jspx_eval_c_if_5 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\t\t\t\t\t<div class=\"entry-links\">\n\n\t\t\t\t\t\t");

						AssetEntry assetEntry = _getAssetEntry(request, entry);
						
              out.write("\n\n\t\t\t\t\t\t");
              //  liferay-asset:asset-links
              com.liferay.asset.taglib.servlet.taglib.AssetLinksTag _jspx_th_liferay$1asset_asset$1links_0 = (com.liferay.asset.taglib.servlet.taglib.AssetLinksTag) _jspx_tagPool_liferay$1asset_asset$1links_classPK_className_assetEntryId_nobody.get(com.liferay.asset.taglib.servlet.taglib.AssetLinksTag.class);
              _jspx_th_liferay$1asset_asset$1links_0.setPageContext(_jspx_page_context);
              _jspx_th_liferay$1asset_asset$1links_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_5);
              _jspx_th_liferay$1asset_asset$1links_0.setAssetEntryId( (assetEntry != null) ? assetEntry.getEntryId() : 0 );
              _jspx_th_liferay$1asset_asset$1links_0.setClassName( BlogsEntry.class.getName() );
              _jspx_th_liferay$1asset_asset$1links_0.setClassPK( entry.getEntryId() );
              int _jspx_eval_liferay$1asset_asset$1links_0 = _jspx_th_liferay$1asset_asset$1links_0.doStartTag();
              if (_jspx_th_liferay$1asset_asset$1links_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_liferay$1asset_asset$1links_classPK_className_assetEntryId_nobody.reuse(_jspx_th_liferay$1asset_asset$1links_0);
                return;
              }
              _jspx_tagPool_liferay$1asset_asset$1links_classPK_className_assetEntryId_nobody.reuse(_jspx_th_liferay$1asset_asset$1links_0);
              out.write("\n\t\t\t\t\t</div>\n\t\t\t\t");
            }
            if (_jspx_th_c_if_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_5);
              return;
            }
            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_5);
            out.write("\n\n\t\t\t\t");
            //  liferay-asset:asset-categories-available
            com.liferay.asset.taglib.servlet.taglib.AssetCategoriesAvailableTag _jspx_th_liferay$1asset_asset$1categories$1available_0 = (com.liferay.asset.taglib.servlet.taglib.AssetCategoriesAvailableTag) _jspx_tagPool_liferay$1asset_asset$1categories$1available_classPK_className.get(com.liferay.asset.taglib.servlet.taglib.AssetCategoriesAvailableTag.class);
            _jspx_th_liferay$1asset_asset$1categories$1available_0.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1asset_asset$1categories$1available_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_4);
            _jspx_th_liferay$1asset_asset$1categories$1available_0.setClassName( BlogsEntry.class.getName() );
            _jspx_th_liferay$1asset_asset$1categories$1available_0.setClassPK( entry.getEntryId() );
            int _jspx_eval_liferay$1asset_asset$1categories$1available_0 = _jspx_th_liferay$1asset_asset$1categories$1available_0.doStartTag();
            if (_jspx_eval_liferay$1asset_asset$1categories$1available_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\t\t\t\t\t<div class=\"entry-categories\">\n\t\t\t\t\t\t");
              //  liferay-asset:asset-categories-summary
              com.liferay.asset.taglib.servlet.taglib.AssetCategoriesSummaryTag _jspx_th_liferay$1asset_asset$1categories$1summary_0 = (com.liferay.asset.taglib.servlet.taglib.AssetCategoriesSummaryTag) _jspx_tagPool_liferay$1asset_asset$1categories$1summary_portletURL_classPK_className_nobody.get(com.liferay.asset.taglib.servlet.taglib.AssetCategoriesSummaryTag.class);
              _jspx_th_liferay$1asset_asset$1categories$1summary_0.setPageContext(_jspx_page_context);
              _jspx_th_liferay$1asset_asset$1categories$1summary_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1asset_asset$1categories$1available_0);
              _jspx_th_liferay$1asset_asset$1categories$1summary_0.setClassName( BlogsEntry.class.getName() );
              _jspx_th_liferay$1asset_asset$1categories$1summary_0.setClassPK( entry.getEntryId() );
              _jspx_th_liferay$1asset_asset$1categories$1summary_0.setPortletURL( renderResponse.createRenderURL() );
              int _jspx_eval_liferay$1asset_asset$1categories$1summary_0 = _jspx_th_liferay$1asset_asset$1categories$1summary_0.doStartTag();
              if (_jspx_th_liferay$1asset_asset$1categories$1summary_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_liferay$1asset_asset$1categories$1summary_portletURL_classPK_className_nobody.reuse(_jspx_th_liferay$1asset_asset$1categories$1summary_0);
                return;
              }
              _jspx_tagPool_liferay$1asset_asset$1categories$1summary_portletURL_classPK_className_nobody.reuse(_jspx_th_liferay$1asset_asset$1categories$1summary_0);
              out.write("\n\t\t\t\t\t</div>\n\t\t\t\t");
            }
            if (_jspx_th_liferay$1asset_asset$1categories$1available_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_liferay$1asset_asset$1categories$1available_classPK_className.reuse(_jspx_th_liferay$1asset_asset$1categories$1available_0);
              return;
            }
            _jspx_tagPool_liferay$1asset_asset$1categories$1available_classPK_className.reuse(_jspx_th_liferay$1asset_asset$1categories$1available_0);
            out.write("\n\t\t\t");
          }
          if (_jspx_th_c_if_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_4);
            return;
          }
          _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_4);
          out.write("\n\t\t</div>\n\t");
        }
        if (_jspx_th_c_when_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_0);
          return;
        }
        _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_0);
        out.write('\n');
        out.write('	');
        //  c:otherwise
        com.liferay.taglib.core.OtherwiseTag _jspx_th_c_otherwise_1 = (com.liferay.taglib.core.OtherwiseTag) _jspx_tagPool_c_otherwise.get(com.liferay.taglib.core.OtherwiseTag.class);
        _jspx_th_c_otherwise_1.setPageContext(_jspx_page_context);
        _jspx_th_c_otherwise_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_0);
        int _jspx_eval_c_otherwise_1 = _jspx_th_c_otherwise_1.doStartTag();
        if (_jspx_eval_c_otherwise_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          out.write("\n\n\t\t");

		if (searchContainer != null) {
			searchContainer.setTotal(searchContainer.getTotal() - 1);
		}
		
          out.write("\n\n\t");
        }
        if (_jspx_th_c_otherwise_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_1);
          return;
        }
        _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_1);
        out.write('\n');
      }
      if (_jspx_th_c_choose_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_0);
        return;
      }
      _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_0);
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

  private boolean _jspx_meth_portlet_param_0(javax.servlet.jsp.tagext.JspTag _jspx_th_portlet_renderURL_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:param
    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_0 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
    _jspx_th_portlet_param_0.setPageContext(_jspx_page_context);
    _jspx_th_portlet_param_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_0);
    _jspx_th_portlet_param_0.setName("mvcRenderCommandName");
    _jspx_th_portlet_param_0.setValue("/blogs/view_entry");
    int _jspx_eval_portlet_param_0 = _jspx_th_portlet_param_0.doStartTag();
    if (_jspx_th_portlet_param_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_0);
      return true;
    }
    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_0);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_when_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_0 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_0.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_0);
    _jspx_th_liferay$1ui_message_0.setKey("published-date");
    int _jspx_eval_liferay$1ui_message_0 = _jspx_th_liferay$1ui_message_0.doStartTag();
    if (_jspx_th_liferay$1ui_message_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_0);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_0);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_when_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_0 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_0.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_0);
    int _jspx_eval_portlet_namespace_0 = _jspx_th_portlet_namespace_0.doStartTag();
    if (_jspx_th_portlet_namespace_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_0);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_0);
    return false;
  }
}
