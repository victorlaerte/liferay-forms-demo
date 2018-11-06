package org.apache.jsp.blogs_005faggregator;

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

public final class configuration_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(4);
    _jspx_dependants.add("/blogs_aggregator/init.jsp");
    _jspx_dependants.add("/init.jsp");
    _jspx_dependants.add("/init-ext.jsp");
    _jspx_dependants.add("/blogs_aggregator/init-ext.jsp");
  }

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_message_key_arguments_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1portlet_renderURL_var_portletConfiguration_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_if_test;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_option_label_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1rss_rss$1settings_feedType_enabled_displayStyle_delta_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_script_require;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_button_value_name_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_script;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_button_value_onClick_name_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_button_type_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1frontend_fieldset;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1frontend_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1frontend_edit$1form_name_method_action;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_namespace_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_input_value_type_name_label_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_input_value_type_name_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1frontend_edit$1form$1body;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_select_value_name;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1frontend_edit$1form$1footer;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1theme_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1frontend_fieldset$1group;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1portlet_actionURL_var_portletConfiguration_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_select_value_name_label;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1trash_defineObjects_nobody;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_liferay$1ui_message_key_arguments_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1portlet_renderURL_var_portletConfiguration_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_if_test = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_option_label_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1rss_rss$1settings_feedType_enabled_displayStyle_delta_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_script_require = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_button_value_name_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_script = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_button_value_onClick_name_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_button_type_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1frontend_fieldset = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1frontend_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1frontend_edit$1form_name_method_action = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_namespace_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_input_value_type_name_label_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_input_value_type_name_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1frontend_edit$1form$1body = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_select_value_name = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1frontend_edit$1form$1footer = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1theme_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1frontend_fieldset$1group = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1portlet_actionURL_var_portletConfiguration_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_select_value_name_label = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1trash_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_liferay$1ui_message_key_arguments_nobody.release();
    _jspx_tagPool_liferay$1portlet_renderURL_var_portletConfiguration_nobody.release();
    _jspx_tagPool_c_if_test.release();
    _jspx_tagPool_aui_option_label_nobody.release();
    _jspx_tagPool_liferay$1rss_rss$1settings_feedType_enabled_displayStyle_delta_nobody.release();
    _jspx_tagPool_aui_script_require.release();
    _jspx_tagPool_aui_button_value_name_nobody.release();
    _jspx_tagPool_aui_script.release();
    _jspx_tagPool_aui_button_value_onClick_name_nobody.release();
    _jspx_tagPool_aui_button_type_nobody.release();
    _jspx_tagPool_liferay$1frontend_fieldset.release();
    _jspx_tagPool_liferay$1frontend_defineObjects_nobody.release();
    _jspx_tagPool_liferay$1frontend_edit$1form_name_method_action.release();
    _jspx_tagPool_portlet_namespace_nobody.release();
    _jspx_tagPool_aui_input_value_type_name_label_nobody.release();
    _jspx_tagPool_aui_input_value_type_name_nobody.release();
    _jspx_tagPool_portlet_defineObjects_nobody.release();
    _jspx_tagPool_liferay$1frontend_edit$1form$1body.release();
    _jspx_tagPool_aui_select_value_name.release();
    _jspx_tagPool_liferay$1frontend_edit$1form$1footer.release();
    _jspx_tagPool_liferay$1theme_defineObjects_nobody.release();
    _jspx_tagPool_liferay$1frontend_fieldset$1group.release();
    _jspx_tagPool_liferay$1portlet_actionURL_var_portletConfiguration_nobody.release();
    _jspx_tagPool_aui_select_value_name_label.release();
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
      out.write('\n');
      out.write('\n');

String selectionMethod = portletPreferences.getValue("selectionMethod", "users");
long organizationId = GetterUtil.getLong(portletPreferences.getValue("organizationId", "0"));
String displayStyle = portletPreferences.getValue("displayStyle", "abstract");
int max = GetterUtil.getInteger(portletPreferences.getValue("max", "20"));
boolean showTags = GetterUtil.getBoolean(portletPreferences.getValue("showTags", null), true);

boolean enableRSS = !PortalUtil.isRSSFeedsEnabled() ? false : GetterUtil.getBoolean(portletPreferences.getValue("enableRss", null), true);
int rssDelta = GetterUtil.getInteger(portletPreferences.getValue("rssDelta", StringPool.BLANK), SearchContainer.DEFAULT_DELTA);
String rssDisplayStyle = portletPreferences.getValue("rssDisplayStyle", RSSUtil.DISPLAY_STYLE_DEFAULT);
String rssFeedType = portletPreferences.getValue("rssFeedType", RSSUtil.FEED_TYPE_DEFAULT);

if (organizationId == 0) {
	Group group = GroupLocalServiceUtil.getGroup(scopeGroupId);

	if (group.isOrganization()) {
		organizationId = group.getOrganizationId();
	}
}

      out.write('\n');
      out.write('\n');
      out.write('\n');
      out.write('\n');

String organizationName = StringPool.BLANK;

Organization organization = null;

if (organizationId > 0) {
	organization = OrganizationLocalServiceUtil.getOrganization(organizationId);

	organizationName = organization.getName();
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
        out.write('\n');
        out.write('	');
        //  aui:input
        com.liferay.taglib.aui.InputTag _jspx_th_aui_input_1 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
        _jspx_th_aui_input_1.setPageContext(_jspx_page_context);
        _jspx_th_aui_input_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_edit$1form_0);
        _jspx_th_aui_input_1.setName("redirect");
        _jspx_th_aui_input_1.setType("hidden");
        _jspx_th_aui_input_1.setValue( configurationRenderURL );
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
        _jspx_th_aui_input_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_edit$1form_0);
        _jspx_th_aui_input_2.setName("preferences--organizationId--");
        _jspx_th_aui_input_2.setType("hidden");
        _jspx_th_aui_input_2.setValue( organizationId );
        int _jspx_eval_aui_input_2 = _jspx_th_aui_input_2.doStartTag();
        if (_jspx_th_aui_input_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_2);
          return;
        }
        _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_2);
        out.write("\n\n\t");
        //  liferay-frontend:edit-form-body
        com.liferay.frontend.taglib.servlet.taglib.EditFormBodyTag _jspx_th_liferay$1frontend_edit$1form$1body_0 = (com.liferay.frontend.taglib.servlet.taglib.EditFormBodyTag) _jspx_tagPool_liferay$1frontend_edit$1form$1body.get(com.liferay.frontend.taglib.servlet.taglib.EditFormBodyTag.class);
        _jspx_th_liferay$1frontend_edit$1form$1body_0.setPageContext(_jspx_page_context);
        _jspx_th_liferay$1frontend_edit$1form$1body_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_edit$1form_0);
        int _jspx_eval_liferay$1frontend_edit$1form$1body_0 = _jspx_th_liferay$1frontend_edit$1form$1body_0.doStartTag();
        if (_jspx_eval_liferay$1frontend_edit$1form$1body_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          out.write("\n\t\t");
          //  liferay-frontend:fieldset-group
          com.liferay.frontend.taglib.servlet.taglib.FieldsetGroupTag _jspx_th_liferay$1frontend_fieldset$1group_0 = (com.liferay.frontend.taglib.servlet.taglib.FieldsetGroupTag) _jspx_tagPool_liferay$1frontend_fieldset$1group.get(com.liferay.frontend.taglib.servlet.taglib.FieldsetGroupTag.class);
          _jspx_th_liferay$1frontend_fieldset$1group_0.setPageContext(_jspx_page_context);
          _jspx_th_liferay$1frontend_fieldset$1group_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_edit$1form$1body_0);
          int _jspx_eval_liferay$1frontend_fieldset$1group_0 = _jspx_th_liferay$1frontend_fieldset$1group_0.doStartTag();
          if (_jspx_eval_liferay$1frontend_fieldset$1group_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t\t");
            //  liferay-frontend:fieldset
            com.liferay.frontend.taglib.servlet.taglib.FieldsetTag _jspx_th_liferay$1frontend_fieldset_0 = (com.liferay.frontend.taglib.servlet.taglib.FieldsetTag) _jspx_tagPool_liferay$1frontend_fieldset.get(com.liferay.frontend.taglib.servlet.taglib.FieldsetTag.class);
            _jspx_th_liferay$1frontend_fieldset_0.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1frontend_fieldset_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_fieldset$1group_0);
            int _jspx_eval_liferay$1frontend_fieldset_0 = _jspx_th_liferay$1frontend_fieldset_0.doStartTag();
            if (_jspx_eval_liferay$1frontend_fieldset_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\t\t\t\t");
              //  aui:select
              com.liferay.taglib.aui.SelectTag _jspx_th_aui_select_0 = (com.liferay.taglib.aui.SelectTag) _jspx_tagPool_aui_select_value_name.get(com.liferay.taglib.aui.SelectTag.class);
              _jspx_th_aui_select_0.setPageContext(_jspx_page_context);
              _jspx_th_aui_select_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_fieldset_0);
              _jspx_th_aui_select_0.setName("preferences--selectionMethod--");
              _jspx_th_aui_select_0.setValue( selectionMethod );
              int _jspx_eval_aui_select_0 = _jspx_th_aui_select_0.doStartTag();
              if (_jspx_eval_aui_select_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                if (_jspx_eval_aui_select_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                  out = _jspx_page_context.pushBody();
                  _jspx_th_aui_select_0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                  _jspx_th_aui_select_0.doInitBody();
                }
                do {
                  out.write("\n\t\t\t\t\t");
                  if (_jspx_meth_aui_option_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_select_0, _jspx_page_context))
                    return;
                  out.write("\n\t\t\t\t\t");
                  if (_jspx_meth_aui_option_1((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_select_0, _jspx_page_context))
                    return;
                  out.write("\n\t\t\t\t");
                  int evalDoAfterBody = _jspx_th_aui_select_0.doAfterBody();
                  if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                    break;
                } while (true);
                if (_jspx_eval_aui_select_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                  out = _jspx_page_context.popBody();
              }
              if (_jspx_th_aui_select_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_aui_select_value_name.reuse(_jspx_th_aui_select_0);
                return;
              }
              _jspx_tagPool_aui_select_value_name.reuse(_jspx_th_aui_select_0);
              out.write("\n\n\t\t\t\t<div class=\"form-group ");
              out.print( selectionMethod.equals("users") ? StringPool.BLANK : "hide" );
              out.write("\" id=\"");
              if (_jspx_meth_portlet_namespace_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1frontend_fieldset_0, _jspx_page_context))
                return;
              out.write("usersSelectionOptions\">\n\t\t\t\t\t");
              //  aui:input
              com.liferay.taglib.aui.InputTag _jspx_th_aui_input_3 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_label_nobody.get(com.liferay.taglib.aui.InputTag.class);
              _jspx_th_aui_input_3.setPageContext(_jspx_page_context);
              _jspx_th_aui_input_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_fieldset_0);
              _jspx_th_aui_input_3.setLabel("organization");
              _jspx_th_aui_input_3.setName("organizationName");
              _jspx_th_aui_input_3.setType("resource");
              _jspx_th_aui_input_3.setValue( organizationName );
              int _jspx_eval_aui_input_3 = _jspx_th_aui_input_3.doStartTag();
              if (_jspx_th_aui_input_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_aui_input_value_type_name_label_nobody.reuse(_jspx_th_aui_input_3);
                return;
              }
              _jspx_tagPool_aui_input_value_type_name_label_nobody.reuse(_jspx_th_aui_input_3);
              out.write("\n\n\t\t\t\t\t");
              if (_jspx_meth_aui_button_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1frontend_fieldset_0, _jspx_page_context))
                return;
              out.write("\n\n\t\t\t\t\t");

					String taglibRemoveFolder = "Liferay.Util.removeEntitySelection('organizationId', 'organizationName', this, '" + renderResponse.getNamespace() + "');";
					
              out.write("\n\n\t\t\t\t\t");
              //  aui:button
              com.liferay.taglib.aui.ButtonTag _jspx_th_aui_button_1 = (com.liferay.taglib.aui.ButtonTag) _jspx_tagPool_aui_button_value_onClick_name_nobody.get(com.liferay.taglib.aui.ButtonTag.class);
              _jspx_th_aui_button_1.setPageContext(_jspx_page_context);
              _jspx_th_aui_button_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_fieldset_0);
              _jspx_th_aui_button_1.setName("removeOrganizationButton");
              _jspx_th_aui_button_1.setOnClick( taglibRemoveFolder );
              _jspx_th_aui_button_1.setValue("remove");
              int _jspx_eval_aui_button_1 = _jspx_th_aui_button_1.doStartTag();
              if (_jspx_th_aui_button_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_aui_button_value_onClick_name_nobody.reuse(_jspx_th_aui_button_1);
                return;
              }
              _jspx_tagPool_aui_button_value_onClick_name_nobody.reuse(_jspx_th_aui_button_1);
              out.write("\n\t\t\t\t</div>\n\n\t\t\t\t");
              //  aui:script
              com.liferay.taglib.aui.ScriptTag _jspx_th_aui_script_0 = (com.liferay.taglib.aui.ScriptTag) _jspx_tagPool_aui_script.get(com.liferay.taglib.aui.ScriptTag.class);
              _jspx_th_aui_script_0.setPageContext(_jspx_page_context);
              _jspx_th_aui_script_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_fieldset_0);
              int _jspx_eval_aui_script_0 = _jspx_th_aui_script_0.doStartTag();
              if (_jspx_eval_aui_script_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                if (_jspx_eval_aui_script_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                  out = _jspx_page_context.pushBody();
                  _jspx_th_aui_script_0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                  _jspx_th_aui_script_0.doInitBody();
                }
                do {
                  out.write("\n\t\t\t\t\tvar ");
                  if (_jspx_meth_portlet_namespace_1((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_0, _jspx_page_context))
                    return;
                  out.write("selectOrganizationButton = document.getElementById('");
                  if (_jspx_meth_portlet_namespace_2((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_0, _jspx_page_context))
                    return;
                  out.write("selectOrganizationButton');\n\n\t\t\t\t\tif (");
                  if (_jspx_meth_portlet_namespace_3((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_0, _jspx_page_context))
                    return;
                  out.write("selectOrganizationButton) {\n\t\t\t\t\t\t");
                  if (_jspx_meth_portlet_namespace_4((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_0, _jspx_page_context))
                    return;
                  out.write("selectOrganizationButton.addEventListener(\n\t\t\t\t\t\t\t'click',\n\t\t\t\t\t\t\tfunction(event) {\n\t\t\t\t\t\t\t\tLiferay.Util.selectEntity(\n\t\t\t\t\t\t\t\t\t{\n\t\t\t\t\t\t\t\t\t\tdialog: {\n\t\t\t\t\t\t\t\t\t\t\tconstrain: true,\n\t\t\t\t\t\t\t\t\t\t\tdestroyOnHide: true,\n\t\t\t\t\t\t\t\t\t\t\tmodal: true\n\t\t\t\t\t\t\t\t\t\t},\n\n\t\t\t\t\t\t\t\t\t\t");

										String portletId = PortletProviderUtil.getPortletId(User.class.getName(), PortletProvider.Action.VIEW);
										
                  out.write("\n\n\t\t\t\t\t\t\t\t\t\tid: '");
                  out.print( PortalUtil.getPortletNamespace(portletId) );
                  out.write("selectOrganization',\n\t\t\t\t\t\t\t\t\t\ttitle: '");
                  if (_jspx_meth_liferay$1ui_message_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_0, _jspx_page_context))
                    return;
                  out.write("',\n\n\t\t\t\t\t\t\t\t\t\t");

										PortletURL selectOrganizationURL = PortletProviderUtil.getPortletURL(request, Organization.class.getName(), PortletProvider.Action.BROWSE);

										selectOrganizationURL.setParameter("tabs1", "organizations");
										selectOrganizationURL.setWindowState(LiferayWindowState.POP_UP);
										
                  out.write("\n\n\t\t\t\t\t\t\t\t\t\turi: '");
                  out.print( selectOrganizationURL.toString() );
                  out.write("'\n\t\t\t\t\t\t\t\t\t},\n\t\t\t\t\t\t\t\t\tfunction(event) {\n\t\t\t\t\t\t\t\t\t\tvar form = document.getElementById('");
                  if (_jspx_meth_portlet_namespace_5((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_0, _jspx_page_context))
                    return;
                  out.write("fm');\n\n\t\t\t\t\t\t\t\t\t\tif (form) {\n\t\t\t\t\t\t\t\t\t\t\tvar organizationId = form.querySelector('#");
                  if (_jspx_meth_portlet_namespace_6((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_0, _jspx_page_context))
                    return;
                  out.write("organizationId');\n\n\t\t\t\t\t\t\t\t\t\t\tif (organizationId) {\n\t\t\t\t\t\t\t\t\t\t\t\torganizationId.setAttribute('value', event.entityid);\n\t\t\t\t\t\t\t\t\t\t\t}\n\n\t\t\t\t\t\t\t\t\t\t\tvar organizationName = form.querySelector('#");
                  if (_jspx_meth_portlet_namespace_7((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_0, _jspx_page_context))
                    return;
                  out.write("organizationName');\n\n\t\t\t\t\t\t\t\t\t\t\tif (organizationName) {\n\t\t\t\t\t\t\t\t\t\t\t\torganizationName.setAttribute('value' ,event.entityname);\n\t\t\t\t\t\t\t\t\t\t\t}\n\t\t\t\t\t\t\t\t\t\t}\n\n\t\t\t\t\t\t\t\t\t\tLiferay.Util.toggleDisabled('#");
                  if (_jspx_meth_portlet_namespace_8((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_0, _jspx_page_context))
                    return;
                  out.write("removeOrganizationButton', false);\n\t\t\t\t\t\t\t\t\t}\n\t\t\t\t\t\t\t\t);\n\t\t\t\t\t\t\t}\n\t\t\t\t\t\t);\n\t\t\t\t\t}\n\t\t\t\t");
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
              out.write("\n\n\t\t\t\t");
              if (_jspx_meth_aui_script_1((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1frontend_fieldset_0, _jspx_page_context))
                return;
              out.write("\n\n\t\t\t\t");
              //  aui:select
              com.liferay.taglib.aui.SelectTag _jspx_th_aui_select_1 = (com.liferay.taglib.aui.SelectTag) _jspx_tagPool_aui_select_value_name.get(com.liferay.taglib.aui.SelectTag.class);
              _jspx_th_aui_select_1.setPageContext(_jspx_page_context);
              _jspx_th_aui_select_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_fieldset_0);
              _jspx_th_aui_select_1.setName("preferences--displayStyle--");
              _jspx_th_aui_select_1.setValue( displayStyle );
              int _jspx_eval_aui_select_1 = _jspx_th_aui_select_1.doStartTag();
              if (_jspx_eval_aui_select_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                if (_jspx_eval_aui_select_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                  out = _jspx_page_context.pushBody();
                  _jspx_th_aui_select_1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                  _jspx_th_aui_select_1.doInitBody();
                }
                do {
                  out.write("\n\t\t\t\t\t");
                  if (_jspx_meth_aui_option_2((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_select_1, _jspx_page_context))
                    return;
                  out.write("\n\t\t\t\t\t");
                  if (_jspx_meth_aui_option_3((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_select_1, _jspx_page_context))
                    return;
                  out.write("\n\t\t\t\t\t");
                  if (_jspx_meth_aui_option_4((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_select_1, _jspx_page_context))
                    return;
                  out.write("\n\t\t\t\t\t");
                  if (_jspx_meth_aui_option_5((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_select_1, _jspx_page_context))
                    return;
                  out.write("\n\t\t\t\t\t");
                  if (_jspx_meth_aui_option_6((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_select_1, _jspx_page_context))
                    return;
                  out.write("\n\t\t\t\t\t");
                  if (_jspx_meth_aui_option_7((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_select_1, _jspx_page_context))
                    return;
                  out.write("\n\t\t\t\t\t");
                  if (_jspx_meth_aui_option_8((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_select_1, _jspx_page_context))
                    return;
                  out.write("\n\t\t\t\t");
                  int evalDoAfterBody = _jspx_th_aui_select_1.doAfterBody();
                  if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                    break;
                } while (true);
                if (_jspx_eval_aui_select_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                  out = _jspx_page_context.popBody();
              }
              if (_jspx_th_aui_select_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_aui_select_value_name.reuse(_jspx_th_aui_select_1);
                return;
              }
              _jspx_tagPool_aui_select_value_name.reuse(_jspx_th_aui_select_1);
              out.write("\n\n\t\t\t\t");
              //  aui:select
              com.liferay.taglib.aui.SelectTag _jspx_th_aui_select_2 = (com.liferay.taglib.aui.SelectTag) _jspx_tagPool_aui_select_value_name_label.get(com.liferay.taglib.aui.SelectTag.class);
              _jspx_th_aui_select_2.setPageContext(_jspx_page_context);
              _jspx_th_aui_select_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_fieldset_0);
              _jspx_th_aui_select_2.setLabel("maximum-items-to-display");
              _jspx_th_aui_select_2.setName("preferences--max--");
              _jspx_th_aui_select_2.setValue( max );
              int _jspx_eval_aui_select_2 = _jspx_th_aui_select_2.doStartTag();
              if (_jspx_eval_aui_select_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                if (_jspx_eval_aui_select_2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                  out = _jspx_page_context.pushBody();
                  _jspx_th_aui_select_2.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                  _jspx_th_aui_select_2.doInitBody();
                }
                do {
                  out.write("\n\t\t\t\t\t");
                  if (_jspx_meth_aui_option_9((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_select_2, _jspx_page_context))
                    return;
                  out.write("\n\t\t\t\t\t");
                  if (_jspx_meth_aui_option_10((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_select_2, _jspx_page_context))
                    return;
                  out.write("\n\t\t\t\t\t");
                  if (_jspx_meth_aui_option_11((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_select_2, _jspx_page_context))
                    return;
                  out.write("\n\t\t\t\t\t");
                  if (_jspx_meth_aui_option_12((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_select_2, _jspx_page_context))
                    return;
                  out.write("\n\t\t\t\t\t");
                  if (_jspx_meth_aui_option_13((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_select_2, _jspx_page_context))
                    return;
                  out.write("\n\t\t\t\t\t");
                  if (_jspx_meth_aui_option_14((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_select_2, _jspx_page_context))
                    return;
                  out.write("\n\t\t\t\t\t");
                  if (_jspx_meth_aui_option_15((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_select_2, _jspx_page_context))
                    return;
                  out.write("\n\t\t\t\t\t");
                  if (_jspx_meth_aui_option_16((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_select_2, _jspx_page_context))
                    return;
                  out.write("\n\t\t\t\t\t");
                  if (_jspx_meth_aui_option_17((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_select_2, _jspx_page_context))
                    return;
                  out.write("\n\t\t\t\t\t");
                  if (_jspx_meth_aui_option_18((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_select_2, _jspx_page_context))
                    return;
                  out.write("\n\t\t\t\t\t");
                  if (_jspx_meth_aui_option_19((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_select_2, _jspx_page_context))
                    return;
                  out.write("\n\t\t\t\t\t");
                  if (_jspx_meth_aui_option_20((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_select_2, _jspx_page_context))
                    return;
                  out.write("\n\t\t\t\t\t");
                  if (_jspx_meth_aui_option_21((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_select_2, _jspx_page_context))
                    return;
                  out.write("\n\t\t\t\t\t");
                  if (_jspx_meth_aui_option_22((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_select_2, _jspx_page_context))
                    return;
                  out.write("\n\t\t\t\t\t");
                  if (_jspx_meth_aui_option_23((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_select_2, _jspx_page_context))
                    return;
                  out.write("\n\t\t\t\t\t");
                  if (_jspx_meth_aui_option_24((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_select_2, _jspx_page_context))
                    return;
                  out.write("\n\t\t\t\t\t");
                  if (_jspx_meth_aui_option_25((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_select_2, _jspx_page_context))
                    return;
                  out.write("\n\t\t\t\t");
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
              out.write("\n\n\t\t\t\t");
              //  c:if
              com.liferay.taglib.core.IfTag _jspx_th_c_if_0 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
              _jspx_th_c_if_0.setPageContext(_jspx_page_context);
              _jspx_th_c_if_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_fieldset_0);
              _jspx_th_c_if_0.setTest( PortalUtil.isRSSFeedsEnabled() );
              int _jspx_eval_c_if_0 = _jspx_th_c_if_0.doStartTag();
              if (_jspx_eval_c_if_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\t\t\t\t\t");
                //  liferay-rss:rss-settings
                com.liferay.rss.taglib.servlet.taglib.RSSSettingsTag _jspx_th_liferay$1rss_rss$1settings_0 = (com.liferay.rss.taglib.servlet.taglib.RSSSettingsTag) _jspx_tagPool_liferay$1rss_rss$1settings_feedType_enabled_displayStyle_delta_nobody.get(com.liferay.rss.taglib.servlet.taglib.RSSSettingsTag.class);
                _jspx_th_liferay$1rss_rss$1settings_0.setPageContext(_jspx_page_context);
                _jspx_th_liferay$1rss_rss$1settings_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_0);
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
                out.write("\n\t\t\t\t");
              }
              if (_jspx_th_c_if_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
                return;
              }
              _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
              out.write("\n\n\t\t\t\t");
              //  aui:input
              com.liferay.taglib.aui.InputTag _jspx_th_aui_input_4 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
              _jspx_th_aui_input_4.setPageContext(_jspx_page_context);
              _jspx_th_aui_input_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_fieldset_0);
              _jspx_th_aui_input_4.setName("preferences--showTags--");
              _jspx_th_aui_input_4.setType("checkbox");
              _jspx_th_aui_input_4.setValue( showTags );
              int _jspx_eval_aui_input_4 = _jspx_th_aui_input_4.doStartTag();
              if (_jspx_th_aui_input_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_4);
                return;
              }
              _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_4);
              out.write("\n\t\t\t");
            }
            if (_jspx_th_liferay$1frontend_fieldset_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_liferay$1frontend_fieldset.reuse(_jspx_th_liferay$1frontend_fieldset_0);
              return;
            }
            _jspx_tagPool_liferay$1frontend_fieldset.reuse(_jspx_th_liferay$1frontend_fieldset_0);
            out.write("\n\t\t");
          }
          if (_jspx_th_liferay$1frontend_fieldset$1group_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_liferay$1frontend_fieldset$1group.reuse(_jspx_th_liferay$1frontend_fieldset$1group_0);
            return;
          }
          _jspx_tagPool_liferay$1frontend_fieldset$1group.reuse(_jspx_th_liferay$1frontend_fieldset$1group_0);
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

  private boolean _jspx_meth_aui_option_0(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_select_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:option
    com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_0 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
    _jspx_th_aui_option_0.setPageContext(_jspx_page_context);
    _jspx_th_aui_option_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_0);
    _jspx_th_aui_option_0.setLabel(new String("users"));
    int _jspx_eval_aui_option_0 = _jspx_th_aui_option_0.doStartTag();
    if (_jspx_th_aui_option_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_option_label_nobody.reuse(_jspx_th_aui_option_0);
      return true;
    }
    _jspx_tagPool_aui_option_label_nobody.reuse(_jspx_th_aui_option_0);
    return false;
  }

  private boolean _jspx_meth_aui_option_1(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_select_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:option
    com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_1 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
    _jspx_th_aui_option_1.setPageContext(_jspx_page_context);
    _jspx_th_aui_option_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_0);
    _jspx_th_aui_option_1.setLabel(new String("scope"));
    int _jspx_eval_aui_option_1 = _jspx_th_aui_option_1.doStartTag();
    if (_jspx_th_aui_option_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_option_label_nobody.reuse(_jspx_th_aui_option_1);
      return true;
    }
    _jspx_tagPool_aui_option_label_nobody.reuse(_jspx_th_aui_option_1);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_0(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1frontend_fieldset_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_0 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_0.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_fieldset_0);
    int _jspx_eval_portlet_namespace_0 = _jspx_th_portlet_namespace_0.doStartTag();
    if (_jspx_th_portlet_namespace_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_0);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_0);
    return false;
  }

  private boolean _jspx_meth_aui_button_0(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1frontend_fieldset_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:button
    com.liferay.taglib.aui.ButtonTag _jspx_th_aui_button_0 = (com.liferay.taglib.aui.ButtonTag) _jspx_tagPool_aui_button_value_name_nobody.get(com.liferay.taglib.aui.ButtonTag.class);
    _jspx_th_aui_button_0.setPageContext(_jspx_page_context);
    _jspx_th_aui_button_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_fieldset_0);
    _jspx_th_aui_button_0.setName("selectOrganizationButton");
    _jspx_th_aui_button_0.setValue("select");
    int _jspx_eval_aui_button_0 = _jspx_th_aui_button_0.doStartTag();
    if (_jspx_th_aui_button_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_button_value_name_nobody.reuse(_jspx_th_aui_button_0);
      return true;
    }
    _jspx_tagPool_aui_button_value_name_nobody.reuse(_jspx_th_aui_button_0);
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

  private boolean _jspx_meth_liferay$1ui_message_0(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_0 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_arguments_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_0.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_0);
    _jspx_th_liferay$1ui_message_0.setArguments(new String("organization"));
    _jspx_th_liferay$1ui_message_0.setKey("select-x");
    int _jspx_eval_liferay$1ui_message_0 = _jspx_th_liferay$1ui_message_0.doStartTag();
    if (_jspx_th_liferay$1ui_message_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_arguments_nobody.reuse(_jspx_th_liferay$1ui_message_0);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_arguments_nobody.reuse(_jspx_th_liferay$1ui_message_0);
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

  private boolean _jspx_meth_portlet_namespace_6(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_6 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_6.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_0);
    int _jspx_eval_portlet_namespace_6 = _jspx_th_portlet_namespace_6.doStartTag();
    if (_jspx_th_portlet_namespace_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_6);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_6);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_7(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_7 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_7.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_0);
    int _jspx_eval_portlet_namespace_7 = _jspx_th_portlet_namespace_7.doStartTag();
    if (_jspx_th_portlet_namespace_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_7);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_7);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_8(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_8 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_8.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_8.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_0);
    int _jspx_eval_portlet_namespace_8 = _jspx_th_portlet_namespace_8.doStartTag();
    if (_jspx_th_portlet_namespace_8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_8);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_8);
    return false;
  }

  private boolean _jspx_meth_aui_script_1(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1frontend_fieldset_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:script
    com.liferay.taglib.aui.ScriptTag _jspx_th_aui_script_1 = (com.liferay.taglib.aui.ScriptTag) _jspx_tagPool_aui_script_require.get(com.liferay.taglib.aui.ScriptTag.class);
    _jspx_th_aui_script_1.setPageContext(_jspx_page_context);
    _jspx_th_aui_script_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_fieldset_0);
    _jspx_th_aui_script_1.setRequire("metal-dom/src/dom");
    int _jspx_eval_aui_script_1 = _jspx_th_aui_script_1.doStartTag();
    if (_jspx_eval_aui_script_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_aui_script_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_aui_script_1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_aui_script_1.doInitBody();
      }
      do {
        out.write("\n\t\t\t\t\tlet dom = metalDomSrcDom.default;\n\n\t\t\t\t\tvar ");
        if (_jspx_meth_portlet_namespace_9((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
          return true;
        out.write("selectionMethod = document.getElementById('");
        if (_jspx_meth_portlet_namespace_10((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
          return true;
        out.write("selectionMethod');\n\n\t\t\t\t\tif (");
        if (_jspx_meth_portlet_namespace_11((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
          return true;
        out.write("selectionMethod) {\n\t\t\t\t\t\t");
        if (_jspx_meth_portlet_namespace_12((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
          return true;
        out.write("selectionMethod.addEventListener(\n\t\t\t\t\t\t\t'change',\n\t\t\t\t\t\t\tfunction(event) {\n\t\t\t\t\t\t\t\tvar usersSelectionOptions = document.getElementById('");
        if (_jspx_meth_portlet_namespace_13((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
          return true;
        out.write("usersSelectionOptions');\n\n\t\t\t\t\t\t\t\tif (usersSelectionOptions) {\n\t\t\t\t\t\t\t\t\tvar showUsersSelectionOptions = !(selectionMethod.val() === 'users');\n\n\t\t\t\t\t\t\t\t\tif (showUsersSelectionOptions) {\n\t\t\t\t\t\t\t\t\t\tdom.addClasses(usersSelectionOptions, 'hide');\n\t\t\t\t\t\t\t\t\t}\n\t\t\t\t\t\t\t\t\telse {\n\t\t\t\t\t\t\t\t\t\tdom.removeClasses(usersSelectionOptions, 'hide');\n\t\t\t\t\t\t\t\t\t}\n\t\t\t\t\t\t\t\t}\n\t\t\t\t\t\t\t}\n\t\t\t\t\t\t);\n\t\t\t\t\t}\n\t\t\t\t");
        int evalDoAfterBody = _jspx_th_aui_script_1.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_aui_script_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
        out = _jspx_page_context.popBody();
    }
    if (_jspx_th_aui_script_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_script_require.reuse(_jspx_th_aui_script_1);
      return true;
    }
    _jspx_tagPool_aui_script_require.reuse(_jspx_th_aui_script_1);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_9(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_9 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_9.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_9.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    int _jspx_eval_portlet_namespace_9 = _jspx_th_portlet_namespace_9.doStartTag();
    if (_jspx_th_portlet_namespace_9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_9);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_9);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_10(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_10 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_10.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_10.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    int _jspx_eval_portlet_namespace_10 = _jspx_th_portlet_namespace_10.doStartTag();
    if (_jspx_th_portlet_namespace_10.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_10);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_10);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_11(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_11 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_11.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_11.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    int _jspx_eval_portlet_namespace_11 = _jspx_th_portlet_namespace_11.doStartTag();
    if (_jspx_th_portlet_namespace_11.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_11);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_11);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_12(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_12 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_12.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_12.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    int _jspx_eval_portlet_namespace_12 = _jspx_th_portlet_namespace_12.doStartTag();
    if (_jspx_th_portlet_namespace_12.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_12);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_12);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_13(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_13 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_13.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_13.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    int _jspx_eval_portlet_namespace_13 = _jspx_th_portlet_namespace_13.doStartTag();
    if (_jspx_th_portlet_namespace_13.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_13);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_13);
    return false;
  }

  private boolean _jspx_meth_aui_option_2(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_select_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:option
    com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_2 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
    _jspx_th_aui_option_2.setPageContext(_jspx_page_context);
    _jspx_th_aui_option_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_1);
    _jspx_th_aui_option_2.setLabel(new String("body-and-image"));
    int _jspx_eval_aui_option_2 = _jspx_th_aui_option_2.doStartTag();
    if (_jspx_th_aui_option_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_option_label_nobody.reuse(_jspx_th_aui_option_2);
      return true;
    }
    _jspx_tagPool_aui_option_label_nobody.reuse(_jspx_th_aui_option_2);
    return false;
  }

  private boolean _jspx_meth_aui_option_3(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_select_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:option
    com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_3 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
    _jspx_th_aui_option_3.setPageContext(_jspx_page_context);
    _jspx_th_aui_option_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_1);
    _jspx_th_aui_option_3.setLabel(new String("body"));
    int _jspx_eval_aui_option_3 = _jspx_th_aui_option_3.doStartTag();
    if (_jspx_th_aui_option_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_option_label_nobody.reuse(_jspx_th_aui_option_3);
      return true;
    }
    _jspx_tagPool_aui_option_label_nobody.reuse(_jspx_th_aui_option_3);
    return false;
  }

  private boolean _jspx_meth_aui_option_4(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_select_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:option
    com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_4 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
    _jspx_th_aui_option_4.setPageContext(_jspx_page_context);
    _jspx_th_aui_option_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_1);
    _jspx_th_aui_option_4.setLabel(new String("abstract"));
    int _jspx_eval_aui_option_4 = _jspx_th_aui_option_4.doStartTag();
    if (_jspx_th_aui_option_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_option_label_nobody.reuse(_jspx_th_aui_option_4);
      return true;
    }
    _jspx_tagPool_aui_option_label_nobody.reuse(_jspx_th_aui_option_4);
    return false;
  }

  private boolean _jspx_meth_aui_option_5(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_select_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:option
    com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_5 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
    _jspx_th_aui_option_5.setPageContext(_jspx_page_context);
    _jspx_th_aui_option_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_1);
    _jspx_th_aui_option_5.setLabel(new String("abstract-without-title"));
    int _jspx_eval_aui_option_5 = _jspx_th_aui_option_5.doStartTag();
    if (_jspx_th_aui_option_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_option_label_nobody.reuse(_jspx_th_aui_option_5);
      return true;
    }
    _jspx_tagPool_aui_option_label_nobody.reuse(_jspx_th_aui_option_5);
    return false;
  }

  private boolean _jspx_meth_aui_option_6(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_select_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:option
    com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_6 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
    _jspx_th_aui_option_6.setPageContext(_jspx_page_context);
    _jspx_th_aui_option_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_1);
    _jspx_th_aui_option_6.setLabel(new String("quote"));
    int _jspx_eval_aui_option_6 = _jspx_th_aui_option_6.doStartTag();
    if (_jspx_th_aui_option_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_option_label_nobody.reuse(_jspx_th_aui_option_6);
      return true;
    }
    _jspx_tagPool_aui_option_label_nobody.reuse(_jspx_th_aui_option_6);
    return false;
  }

  private boolean _jspx_meth_aui_option_7(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_select_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:option
    com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_7 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
    _jspx_th_aui_option_7.setPageContext(_jspx_page_context);
    _jspx_th_aui_option_7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_1);
    _jspx_th_aui_option_7.setLabel(new String("quote-without-title"));
    int _jspx_eval_aui_option_7 = _jspx_th_aui_option_7.doStartTag();
    if (_jspx_th_aui_option_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_option_label_nobody.reuse(_jspx_th_aui_option_7);
      return true;
    }
    _jspx_tagPool_aui_option_label_nobody.reuse(_jspx_th_aui_option_7);
    return false;
  }

  private boolean _jspx_meth_aui_option_8(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_select_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:option
    com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_8 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
    _jspx_th_aui_option_8.setPageContext(_jspx_page_context);
    _jspx_th_aui_option_8.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_1);
    _jspx_th_aui_option_8.setLabel(new String("title"));
    int _jspx_eval_aui_option_8 = _jspx_th_aui_option_8.doStartTag();
    if (_jspx_th_aui_option_8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_option_label_nobody.reuse(_jspx_th_aui_option_8);
      return true;
    }
    _jspx_tagPool_aui_option_label_nobody.reuse(_jspx_th_aui_option_8);
    return false;
  }

  private boolean _jspx_meth_aui_option_9(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_select_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:option
    com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_9 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
    _jspx_th_aui_option_9.setPageContext(_jspx_page_context);
    _jspx_th_aui_option_9.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_2);
    _jspx_th_aui_option_9.setLabel(new String("1"));
    int _jspx_eval_aui_option_9 = _jspx_th_aui_option_9.doStartTag();
    if (_jspx_th_aui_option_9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_option_label_nobody.reuse(_jspx_th_aui_option_9);
      return true;
    }
    _jspx_tagPool_aui_option_label_nobody.reuse(_jspx_th_aui_option_9);
    return false;
  }

  private boolean _jspx_meth_aui_option_10(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_select_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:option
    com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_10 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
    _jspx_th_aui_option_10.setPageContext(_jspx_page_context);
    _jspx_th_aui_option_10.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_2);
    _jspx_th_aui_option_10.setLabel(new String("2"));
    int _jspx_eval_aui_option_10 = _jspx_th_aui_option_10.doStartTag();
    if (_jspx_th_aui_option_10.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_option_label_nobody.reuse(_jspx_th_aui_option_10);
      return true;
    }
    _jspx_tagPool_aui_option_label_nobody.reuse(_jspx_th_aui_option_10);
    return false;
  }

  private boolean _jspx_meth_aui_option_11(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_select_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:option
    com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_11 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
    _jspx_th_aui_option_11.setPageContext(_jspx_page_context);
    _jspx_th_aui_option_11.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_2);
    _jspx_th_aui_option_11.setLabel(new String("3"));
    int _jspx_eval_aui_option_11 = _jspx_th_aui_option_11.doStartTag();
    if (_jspx_th_aui_option_11.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_option_label_nobody.reuse(_jspx_th_aui_option_11);
      return true;
    }
    _jspx_tagPool_aui_option_label_nobody.reuse(_jspx_th_aui_option_11);
    return false;
  }

  private boolean _jspx_meth_aui_option_12(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_select_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:option
    com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_12 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
    _jspx_th_aui_option_12.setPageContext(_jspx_page_context);
    _jspx_th_aui_option_12.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_2);
    _jspx_th_aui_option_12.setLabel(new String("4"));
    int _jspx_eval_aui_option_12 = _jspx_th_aui_option_12.doStartTag();
    if (_jspx_th_aui_option_12.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_option_label_nobody.reuse(_jspx_th_aui_option_12);
      return true;
    }
    _jspx_tagPool_aui_option_label_nobody.reuse(_jspx_th_aui_option_12);
    return false;
  }

  private boolean _jspx_meth_aui_option_13(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_select_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:option
    com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_13 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
    _jspx_th_aui_option_13.setPageContext(_jspx_page_context);
    _jspx_th_aui_option_13.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_2);
    _jspx_th_aui_option_13.setLabel(new String("5"));
    int _jspx_eval_aui_option_13 = _jspx_th_aui_option_13.doStartTag();
    if (_jspx_th_aui_option_13.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_option_label_nobody.reuse(_jspx_th_aui_option_13);
      return true;
    }
    _jspx_tagPool_aui_option_label_nobody.reuse(_jspx_th_aui_option_13);
    return false;
  }

  private boolean _jspx_meth_aui_option_14(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_select_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:option
    com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_14 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
    _jspx_th_aui_option_14.setPageContext(_jspx_page_context);
    _jspx_th_aui_option_14.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_2);
    _jspx_th_aui_option_14.setLabel(new String("10"));
    int _jspx_eval_aui_option_14 = _jspx_th_aui_option_14.doStartTag();
    if (_jspx_th_aui_option_14.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_option_label_nobody.reuse(_jspx_th_aui_option_14);
      return true;
    }
    _jspx_tagPool_aui_option_label_nobody.reuse(_jspx_th_aui_option_14);
    return false;
  }

  private boolean _jspx_meth_aui_option_15(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_select_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:option
    com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_15 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
    _jspx_th_aui_option_15.setPageContext(_jspx_page_context);
    _jspx_th_aui_option_15.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_2);
    _jspx_th_aui_option_15.setLabel(new String("15"));
    int _jspx_eval_aui_option_15 = _jspx_th_aui_option_15.doStartTag();
    if (_jspx_th_aui_option_15.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_option_label_nobody.reuse(_jspx_th_aui_option_15);
      return true;
    }
    _jspx_tagPool_aui_option_label_nobody.reuse(_jspx_th_aui_option_15);
    return false;
  }

  private boolean _jspx_meth_aui_option_16(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_select_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:option
    com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_16 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
    _jspx_th_aui_option_16.setPageContext(_jspx_page_context);
    _jspx_th_aui_option_16.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_2);
    _jspx_th_aui_option_16.setLabel(new String("20"));
    int _jspx_eval_aui_option_16 = _jspx_th_aui_option_16.doStartTag();
    if (_jspx_th_aui_option_16.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_option_label_nobody.reuse(_jspx_th_aui_option_16);
      return true;
    }
    _jspx_tagPool_aui_option_label_nobody.reuse(_jspx_th_aui_option_16);
    return false;
  }

  private boolean _jspx_meth_aui_option_17(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_select_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:option
    com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_17 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
    _jspx_th_aui_option_17.setPageContext(_jspx_page_context);
    _jspx_th_aui_option_17.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_2);
    _jspx_th_aui_option_17.setLabel(new String("25"));
    int _jspx_eval_aui_option_17 = _jspx_th_aui_option_17.doStartTag();
    if (_jspx_th_aui_option_17.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_option_label_nobody.reuse(_jspx_th_aui_option_17);
      return true;
    }
    _jspx_tagPool_aui_option_label_nobody.reuse(_jspx_th_aui_option_17);
    return false;
  }

  private boolean _jspx_meth_aui_option_18(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_select_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:option
    com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_18 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
    _jspx_th_aui_option_18.setPageContext(_jspx_page_context);
    _jspx_th_aui_option_18.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_2);
    _jspx_th_aui_option_18.setLabel(new String("30"));
    int _jspx_eval_aui_option_18 = _jspx_th_aui_option_18.doStartTag();
    if (_jspx_th_aui_option_18.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_option_label_nobody.reuse(_jspx_th_aui_option_18);
      return true;
    }
    _jspx_tagPool_aui_option_label_nobody.reuse(_jspx_th_aui_option_18);
    return false;
  }

  private boolean _jspx_meth_aui_option_19(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_select_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:option
    com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_19 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
    _jspx_th_aui_option_19.setPageContext(_jspx_page_context);
    _jspx_th_aui_option_19.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_2);
    _jspx_th_aui_option_19.setLabel(new String("40"));
    int _jspx_eval_aui_option_19 = _jspx_th_aui_option_19.doStartTag();
    if (_jspx_th_aui_option_19.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_option_label_nobody.reuse(_jspx_th_aui_option_19);
      return true;
    }
    _jspx_tagPool_aui_option_label_nobody.reuse(_jspx_th_aui_option_19);
    return false;
  }

  private boolean _jspx_meth_aui_option_20(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_select_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:option
    com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_20 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
    _jspx_th_aui_option_20.setPageContext(_jspx_page_context);
    _jspx_th_aui_option_20.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_2);
    _jspx_th_aui_option_20.setLabel(new String("50"));
    int _jspx_eval_aui_option_20 = _jspx_th_aui_option_20.doStartTag();
    if (_jspx_th_aui_option_20.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_option_label_nobody.reuse(_jspx_th_aui_option_20);
      return true;
    }
    _jspx_tagPool_aui_option_label_nobody.reuse(_jspx_th_aui_option_20);
    return false;
  }

  private boolean _jspx_meth_aui_option_21(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_select_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:option
    com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_21 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
    _jspx_th_aui_option_21.setPageContext(_jspx_page_context);
    _jspx_th_aui_option_21.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_2);
    _jspx_th_aui_option_21.setLabel(new String("60"));
    int _jspx_eval_aui_option_21 = _jspx_th_aui_option_21.doStartTag();
    if (_jspx_th_aui_option_21.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_option_label_nobody.reuse(_jspx_th_aui_option_21);
      return true;
    }
    _jspx_tagPool_aui_option_label_nobody.reuse(_jspx_th_aui_option_21);
    return false;
  }

  private boolean _jspx_meth_aui_option_22(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_select_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:option
    com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_22 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
    _jspx_th_aui_option_22.setPageContext(_jspx_page_context);
    _jspx_th_aui_option_22.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_2);
    _jspx_th_aui_option_22.setLabel(new String("70"));
    int _jspx_eval_aui_option_22 = _jspx_th_aui_option_22.doStartTag();
    if (_jspx_th_aui_option_22.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_option_label_nobody.reuse(_jspx_th_aui_option_22);
      return true;
    }
    _jspx_tagPool_aui_option_label_nobody.reuse(_jspx_th_aui_option_22);
    return false;
  }

  private boolean _jspx_meth_aui_option_23(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_select_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:option
    com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_23 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
    _jspx_th_aui_option_23.setPageContext(_jspx_page_context);
    _jspx_th_aui_option_23.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_2);
    _jspx_th_aui_option_23.setLabel(new String("80"));
    int _jspx_eval_aui_option_23 = _jspx_th_aui_option_23.doStartTag();
    if (_jspx_th_aui_option_23.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_option_label_nobody.reuse(_jspx_th_aui_option_23);
      return true;
    }
    _jspx_tagPool_aui_option_label_nobody.reuse(_jspx_th_aui_option_23);
    return false;
  }

  private boolean _jspx_meth_aui_option_24(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_select_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:option
    com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_24 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
    _jspx_th_aui_option_24.setPageContext(_jspx_page_context);
    _jspx_th_aui_option_24.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_2);
    _jspx_th_aui_option_24.setLabel(new String("90"));
    int _jspx_eval_aui_option_24 = _jspx_th_aui_option_24.doStartTag();
    if (_jspx_th_aui_option_24.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_option_label_nobody.reuse(_jspx_th_aui_option_24);
      return true;
    }
    _jspx_tagPool_aui_option_label_nobody.reuse(_jspx_th_aui_option_24);
    return false;
  }

  private boolean _jspx_meth_aui_option_25(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_select_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:option
    com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_25 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
    _jspx_th_aui_option_25.setPageContext(_jspx_page_context);
    _jspx_th_aui_option_25.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_2);
    _jspx_th_aui_option_25.setLabel(new String("100"));
    int _jspx_eval_aui_option_25 = _jspx_th_aui_option_25.doStartTag();
    if (_jspx_th_aui_option_25.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_option_label_nobody.reuse(_jspx_th_aui_option_25);
      return true;
    }
    _jspx_tagPool_aui_option_label_nobody.reuse(_jspx_th_aui_option_25);
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
      if (_jspx_meth_aui_button_2((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1frontend_edit$1form$1footer_0, _jspx_page_context))
        return true;
      out.write("\n\n\t\t");
      if (_jspx_meth_aui_button_3((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1frontend_edit$1form$1footer_0, _jspx_page_context))
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

  private boolean _jspx_meth_aui_button_2(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1frontend_edit$1form$1footer_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:button
    com.liferay.taglib.aui.ButtonTag _jspx_th_aui_button_2 = (com.liferay.taglib.aui.ButtonTag) _jspx_tagPool_aui_button_type_nobody.get(com.liferay.taglib.aui.ButtonTag.class);
    _jspx_th_aui_button_2.setPageContext(_jspx_page_context);
    _jspx_th_aui_button_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_edit$1form$1footer_0);
    _jspx_th_aui_button_2.setType("submit");
    int _jspx_eval_aui_button_2 = _jspx_th_aui_button_2.doStartTag();
    if (_jspx_th_aui_button_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_button_type_nobody.reuse(_jspx_th_aui_button_2);
      return true;
    }
    _jspx_tagPool_aui_button_type_nobody.reuse(_jspx_th_aui_button_2);
    return false;
  }

  private boolean _jspx_meth_aui_button_3(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1frontend_edit$1form$1footer_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:button
    com.liferay.taglib.aui.ButtonTag _jspx_th_aui_button_3 = (com.liferay.taglib.aui.ButtonTag) _jspx_tagPool_aui_button_type_nobody.get(com.liferay.taglib.aui.ButtonTag.class);
    _jspx_th_aui_button_3.setPageContext(_jspx_page_context);
    _jspx_th_aui_button_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_edit$1form$1footer_0);
    _jspx_th_aui_button_3.setType("cancel");
    int _jspx_eval_aui_button_3 = _jspx_th_aui_button_3.doStartTag();
    if (_jspx_th_aui_button_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_button_type_nobody.reuse(_jspx_th_aui_button_3);
      return true;
    }
    _jspx_tagPool_aui_button_type_nobody.reuse(_jspx_th_aui_button_3);
    return false;
  }
}
