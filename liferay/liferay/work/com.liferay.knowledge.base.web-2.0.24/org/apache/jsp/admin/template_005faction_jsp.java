package org.apache.jsp.admin;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.liferay.admin.kernel.util.PortalSearchApplicationType;
import com.liferay.asset.kernel.AssetRendererFactoryRegistryUtil;
import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetEntryServiceUtil;
import com.liferay.asset.kernel.service.AssetVocabularyLocalServiceUtil;
import com.liferay.asset.kernel.service.persistence.AssetEntryQuery;
import com.liferay.blogs.model.BlogsEntry;
import com.liferay.document.library.display.context.DLMimeTypeDisplayContext;
import com.liferay.document.library.kernel.exception.DuplicateFileException;
import com.liferay.document.library.kernel.exception.FileNameException;
import com.liferay.document.library.kernel.exception.FileSizeException;
import com.liferay.document.library.kernel.exception.NoSuchFileException;
import com.liferay.journal.model.JournalArticle;
import com.liferay.knowledge.base.configuration.KBGroupServiceConfiguration;
import com.liferay.knowledge.base.constants.KBActionKeys;
import com.liferay.knowledge.base.constants.KBArticleConstants;
import com.liferay.knowledge.base.constants.KBCommentConstants;
import com.liferay.knowledge.base.constants.KBConstants;
import com.liferay.knowledge.base.constants.KBFolderConstants;
import com.liferay.knowledge.base.constants.KBPortletKeys;
import com.liferay.knowledge.base.exception.DuplicateKBFolderNameException;
import com.liferay.knowledge.base.exception.InvalidKBFolderNameException;
import com.liferay.knowledge.base.exception.KBArticleContentException;
import com.liferay.knowledge.base.exception.KBArticleImportException;
import com.liferay.knowledge.base.exception.KBArticlePriorityException;
import com.liferay.knowledge.base.exception.KBArticleSourceURLException;
import com.liferay.knowledge.base.exception.KBArticleStatusException;
import com.liferay.knowledge.base.exception.KBArticleTitleException;
import com.liferay.knowledge.base.exception.KBArticleUrlTitleException;
import com.liferay.knowledge.base.exception.KBCommentContentException;
import com.liferay.knowledge.base.exception.KBTemplateContentException;
import com.liferay.knowledge.base.exception.KBTemplateTitleException;
import com.liferay.knowledge.base.exception.NoSuchArticleException;
import com.liferay.knowledge.base.exception.NoSuchCommentException;
import com.liferay.knowledge.base.exception.NoSuchTemplateException;
import com.liferay.knowledge.base.model.KBArticle;
import com.liferay.knowledge.base.model.KBComment;
import com.liferay.knowledge.base.model.KBFolder;
import com.liferay.knowledge.base.model.KBTemplate;
import com.liferay.knowledge.base.service.KBArticleLocalServiceUtil;
import com.liferay.knowledge.base.service.KBArticleServiceUtil;
import com.liferay.knowledge.base.service.KBCommentLocalServiceUtil;
import com.liferay.knowledge.base.service.KBCommentServiceUtil;
import com.liferay.knowledge.base.service.KBFolderLocalServiceUtil;
import com.liferay.knowledge.base.service.KBFolderServiceUtil;
import com.liferay.knowledge.base.util.KnowledgeBaseUtil;
import com.liferay.knowledge.base.util.comparator.KBArticlePriorityComparator;
import com.liferay.knowledge.base.util.comparator.KBObjectsTitleComparator;
import com.liferay.knowledge.base.web.internal.KBUtil;
import com.liferay.knowledge.base.web.internal.application.dao.search.KBCommentResultRowSplitter;
import com.liferay.knowledge.base.web.internal.application.dao.search.KBResultRowSplitter;
import com.liferay.knowledge.base.web.internal.configuration.KBArticlePortletInstanceConfiguration;
import com.liferay.knowledge.base.web.internal.configuration.KBDisplayPortletInstanceConfiguration;
import com.liferay.knowledge.base.web.internal.configuration.KBSearchPortletInstanceConfiguration;
import com.liferay.knowledge.base.web.internal.configuration.KBSectionPortletInstanceConfiguration;
import com.liferay.knowledge.base.web.internal.constants.KBWebKeys;
import com.liferay.knowledge.base.web.internal.display.context.KBAdminManagementToolbarDisplayContext;
import com.liferay.knowledge.base.web.internal.display.context.KBAdminNavigationDisplayContext;
import com.liferay.knowledge.base.web.internal.display.context.KBAdminViewDisplayContext;
import com.liferay.knowledge.base.web.internal.display.context.KBNavigationDisplayContext;
import com.liferay.knowledge.base.web.internal.display.context.KBSelectParentDisplayContext;
import com.liferay.knowledge.base.web.internal.display.context.KBSuggestionListDisplayContext;
import com.liferay.knowledge.base.web.internal.display.context.KBSuggestionListManagementToolbarDisplayContext;
import com.liferay.knowledge.base.web.internal.display.context.KBTemplatesManagementToolbarDisplayContext;
import com.liferay.knowledge.base.web.internal.display.context.util.KBArticleURLHelper;
import com.liferay.knowledge.base.web.internal.search.KBCommentsChecker;
import com.liferay.knowledge.base.web.internal.search.KBObjectsSearch;
import com.liferay.knowledge.base.web.internal.security.permission.resource.AdminPermission;
import com.liferay.knowledge.base.web.internal.security.permission.resource.DisplayPermission;
import com.liferay.knowledge.base.web.internal.security.permission.resource.KBArticlePermission;
import com.liferay.knowledge.base.web.internal.security.permission.resource.KBCommentPermission;
import com.liferay.knowledge.base.web.internal.security.permission.resource.KBFolderPermission;
import com.liferay.knowledge.base.web.internal.security.permission.resource.KBTemplatePermission;
import com.liferay.knowledge.base.web.internal.social.SocialBookmarksUtil;
import com.liferay.knowledge.base.web.internal.util.AdminUtil;
import com.liferay.knowledge.base.web.internal.util.KBArticleAssetEntriesUtil;
import com.liferay.message.boards.model.MBMessage;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.configuration.metatype.util.ParameterMapUtil;
import com.liferay.portal.kernel.bean.BeanParamUtil;
import com.liferay.portal.kernel.bean.BeanPropertiesUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.search.RowChecker;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.ModelHintsUtil;
import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.model.Ticket;
import com.liferay.portal.kernel.model.TicketConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.module.configuration.ConfigurationProviderUtil;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.portlet.PortalPreferences;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletProvider;
import com.liferay.portal.kernel.portlet.PortletProviderUtil;
import com.liferay.portal.kernel.portletfilerepository.PortletFileRepositoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchContextFactory;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.ClassNameLocalServiceUtil;
import com.liferay.portal.kernel.service.PortletLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.TicketLocalServiceUtil;
import com.liferay.portal.kernel.service.WorkflowDefinitionLinkLocalServiceUtil;
import com.liferay.portal.kernel.service.permission.GroupPermissionUtil;
import com.liferay.portal.kernel.service.permission.PortletPermissionUtil;
import com.liferay.portal.kernel.servlet.HttpHeaders;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.settings.GroupServiceSettingsLocator;
import com.liferay.portal.kernel.upload.UploadRequestSizeException;
import com.liferay.portal.kernel.upload.UploadServletRequestConfigurationHelperUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.FastDateFormatConstants;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.TextFormatter;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.util.Tuple;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.ratings.kernel.RatingsType;
import com.liferay.ratings.kernel.definition.PortletRatingsDefinitionUtil;
import com.liferay.subscription.exception.NoSuchSubscriptionException;
import com.liferay.subscription.service.SubscriptionLocalServiceUtil;
import com.liferay.taglib.search.ResultRow;
import com.liferay.wiki.model.WikiPage;
import java.math.BigDecimal;
import java.text.Format;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import javax.portlet.PortletMode;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.WindowState;

public final class template_005faction_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(2);
    _jspx_dependants.add("/admin/init.jsp");
    _jspx_dependants.add("/init.jsp");
  }

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_icon$1delete_url_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1portlet_renderURL_var;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_param_value_name_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1theme_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1portlet_actionURL_var_name;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_icon$1menu_showWhenSingleIcon_message_markupView_icon_direction;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_if_test;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1frontend_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_icon_url_message_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1security_permissionsURL_windowState_var_resourcePrimKey_modelResourceDescription_modelResource_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_icon_useDialog_url_method_message_nobody;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_liferay$1ui_icon$1delete_url_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1portlet_renderURL_var = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_param_value_name_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1theme_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1portlet_actionURL_var_name = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_icon$1menu_showWhenSingleIcon_message_markupView_icon_direction = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_if_test = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1frontend_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_icon_url_message_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1security_permissionsURL_windowState_var_resourcePrimKey_modelResourceDescription_modelResource_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_icon_useDialog_url_method_message_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_liferay$1ui_icon$1delete_url_nobody.release();
    _jspx_tagPool_liferay$1portlet_renderURL_var.release();
    _jspx_tagPool_portlet_param_value_name_nobody.release();
    _jspx_tagPool_liferay$1theme_defineObjects_nobody.release();
    _jspx_tagPool_liferay$1portlet_actionURL_var_name.release();
    _jspx_tagPool_liferay$1ui_icon$1menu_showWhenSingleIcon_message_markupView_icon_direction.release();
    _jspx_tagPool_c_if_test.release();
    _jspx_tagPool_liferay$1frontend_defineObjects_nobody.release();
    _jspx_tagPool_liferay$1ui_icon_url_message_nobody.release();
    _jspx_tagPool_liferay$1security_permissionsURL_windowState_var_resourcePrimKey_modelResourceDescription_modelResource_nobody.release();
    _jspx_tagPool_portlet_defineObjects_nobody.release();
    _jspx_tagPool_liferay$1ui_icon_useDialog_url_method_message_nobody.release();
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

String redirect = PortalUtil.escapeRedirect(ParamUtil.getString(request, "redirect", currentURL));

String rootPortletId = portletDisplay.getRootPortletId();

String templatePath = portletConfig.getInitParameter("template-path");

KBGroupServiceConfiguration kbGroupServiceConfiguration = ConfigurationProviderUtil.getConfiguration(KBGroupServiceConfiguration.class, new GroupServiceSettingsLocator(themeDisplay.getScopeGroupId(), KBConstants.SERVICE_NAME));

KBSectionPortletInstanceConfiguration kbSectionPortletInstanceConfiguration = portletDisplay.getPortletInstanceConfiguration(KBSectionPortletInstanceConfiguration.class);

Format dateFormatDate = FastDateFormatFactoryUtil.getDate(FastDateFormatConstants.LONG, locale, timeZone);
Format dateFormatDateTime = FastDateFormatFactoryUtil.getDateTime(FastDateFormatConstants.LONG, FastDateFormatConstants.SHORT, locale, timeZone);

      out.write('\n');
      out.write('\n');

request.setAttribute("init.jsp-enableKBArticleAssetLinks", true);
request.setAttribute("init.jsp-enableKBArticleDescription", true);
request.setAttribute("init.jsp-enableKBArticleHistory", true);
request.setAttribute("init.jsp-enableKBArticlePrint", true);
request.setAttribute("init.jsp-enableKBArticleRatings", true);
request.setAttribute("init.jsp-enableKBArticleSubscriptions", true);
request.setAttribute("init.jsp-enableKBArticleViewCountIncrement", true);
request.setAttribute("init.jsp-showKBArticleAssetEntries", true);
request.setAttribute("init.jsp-showKBArticleAttachments", true);
request.setAttribute("init.jsp-socialBookmarksDisplayStyle", "menu");
request.setAttribute("init.jsp-socialBookmarksTypes", null);

PortalPreferences portalPreferences = PortletPreferencesFactoryUtil.getPortalPreferences(liferayPortletRequest);

      out.write('\n');
      out.write('\n');

String mvcPath = ParamUtil.getString(request, "mvcPath");

ResultRow row = (ResultRow)request.getAttribute(KBWebKeys.SEARCH_CONTAINER_RESULT_ROW);

KBTemplate kbTemplate = (KBTemplate)row.getObject();

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
          _jspx_th_c_if_0.setTest( KBTemplatePermission.contains(permissionChecker, kbTemplate, KBActionKeys.VIEW) );
          int _jspx_eval_c_if_0 = _jspx_th_c_if_0.doStartTag();
          if (_jspx_eval_c_if_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t");
            //  liferay-portlet:renderURL
            com.liferay.taglib.portlet.RenderURLTag _jspx_th_liferay$1portlet_renderURL_0 = (com.liferay.taglib.portlet.RenderURLTag) _jspx_tagPool_liferay$1portlet_renderURL_var.get(com.liferay.taglib.portlet.RenderURLTag.class);
            _jspx_th_liferay$1portlet_renderURL_0.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1portlet_renderURL_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_0);
            _jspx_th_liferay$1portlet_renderURL_0.setVar("viewURL");
            int _jspx_eval_liferay$1portlet_renderURL_0 = _jspx_th_liferay$1portlet_renderURL_0.doStartTag();
            if (_jspx_eval_liferay$1portlet_renderURL_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\t\t\t");
              //  portlet:param
              com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_0 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
              _jspx_th_portlet_param_0.setPageContext(_jspx_page_context);
              _jspx_th_portlet_param_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_0);
              _jspx_th_portlet_param_0.setName("mvcPath");
              _jspx_th_portlet_param_0.setValue( templatePath + "view_template.jsp" );
              int _jspx_eval_portlet_param_0 = _jspx_th_portlet_param_0.doStartTag();
              if (_jspx_th_portlet_param_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_0);
                return;
              }
              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_0);
              out.write("\n\t\t\t");
              //  portlet:param
              com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_1 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
              _jspx_th_portlet_param_1.setPageContext(_jspx_page_context);
              _jspx_th_portlet_param_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_0);
              _jspx_th_portlet_param_1.setName("kbTemplateId");
              _jspx_th_portlet_param_1.setValue( String.valueOf(kbTemplate.getKbTemplateId()) );
              int _jspx_eval_portlet_param_1 = _jspx_th_portlet_param_1.doStartTag();
              if (_jspx_th_portlet_param_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_1);
                return;
              }
              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_1);
              out.write("\n\t\t");
            }
            if (_jspx_th_liferay$1portlet_renderURL_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_liferay$1portlet_renderURL_var.reuse(_jspx_th_liferay$1portlet_renderURL_0);
              return;
            }
            _jspx_tagPool_liferay$1portlet_renderURL_var.reuse(_jspx_th_liferay$1portlet_renderURL_0);
            java.lang.String viewURL = null;
            viewURL = (java.lang.String) _jspx_page_context.findAttribute("viewURL");
            out.write("\n\n\t\t");
            //  liferay-ui:icon
            com.liferay.taglib.ui.IconTag _jspx_th_liferay$1ui_icon_0 = (com.liferay.taglib.ui.IconTag) _jspx_tagPool_liferay$1ui_icon_url_message_nobody.get(com.liferay.taglib.ui.IconTag.class);
            _jspx_th_liferay$1ui_icon_0.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1ui_icon_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_0);
            _jspx_th_liferay$1ui_icon_0.setMessage("view");
            _jspx_th_liferay$1ui_icon_0.setUrl( viewURL );
            int _jspx_eval_liferay$1ui_icon_0 = _jspx_th_liferay$1ui_icon_0.doStartTag();
            if (_jspx_th_liferay$1ui_icon_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_liferay$1ui_icon_url_message_nobody.reuse(_jspx_th_liferay$1ui_icon_0);
              return;
            }
            _jspx_tagPool_liferay$1ui_icon_url_message_nobody.reuse(_jspx_th_liferay$1ui_icon_0);
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
          com.liferay.taglib.core.IfTag _jspx_th_c_if_1 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
          _jspx_th_c_if_1.setPageContext(_jspx_page_context);
          _jspx_th_c_if_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_icon$1menu_0);
          _jspx_th_c_if_1.setTest( KBTemplatePermission.contains(permissionChecker, kbTemplate, KBActionKeys.UPDATE) );
          int _jspx_eval_c_if_1 = _jspx_th_c_if_1.doStartTag();
          if (_jspx_eval_c_if_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t");
            //  liferay-portlet:renderURL
            com.liferay.taglib.portlet.RenderURLTag _jspx_th_liferay$1portlet_renderURL_1 = (com.liferay.taglib.portlet.RenderURLTag) _jspx_tagPool_liferay$1portlet_renderURL_var.get(com.liferay.taglib.portlet.RenderURLTag.class);
            _jspx_th_liferay$1portlet_renderURL_1.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1portlet_renderURL_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_1);
            _jspx_th_liferay$1portlet_renderURL_1.setVar("editURL");
            int _jspx_eval_liferay$1portlet_renderURL_1 = _jspx_th_liferay$1portlet_renderURL_1.doStartTag();
            if (_jspx_eval_liferay$1portlet_renderURL_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\t\t\t");
              //  portlet:param
              com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_2 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
              _jspx_th_portlet_param_2.setPageContext(_jspx_page_context);
              _jspx_th_portlet_param_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_1);
              _jspx_th_portlet_param_2.setName("mvcPath");
              _jspx_th_portlet_param_2.setValue( templatePath + "edit_template.jsp" );
              int _jspx_eval_portlet_param_2 = _jspx_th_portlet_param_2.doStartTag();
              if (_jspx_th_portlet_param_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_2);
                return;
              }
              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_2);
              out.write("\n\t\t\t");
              //  portlet:param
              com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_3 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
              _jspx_th_portlet_param_3.setPageContext(_jspx_page_context);
              _jspx_th_portlet_param_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_1);
              _jspx_th_portlet_param_3.setName("redirect");
              _jspx_th_portlet_param_3.setValue( currentURL );
              int _jspx_eval_portlet_param_3 = _jspx_th_portlet_param_3.doStartTag();
              if (_jspx_th_portlet_param_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_3);
                return;
              }
              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_3);
              out.write("\n\t\t\t");
              //  portlet:param
              com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_4 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
              _jspx_th_portlet_param_4.setPageContext(_jspx_page_context);
              _jspx_th_portlet_param_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_1);
              _jspx_th_portlet_param_4.setName("kbTemplateId");
              _jspx_th_portlet_param_4.setValue( String.valueOf(kbTemplate.getKbTemplateId()) );
              int _jspx_eval_portlet_param_4 = _jspx_th_portlet_param_4.doStartTag();
              if (_jspx_th_portlet_param_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_4);
                return;
              }
              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_4);
              out.write("\n\t\t");
            }
            if (_jspx_th_liferay$1portlet_renderURL_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_liferay$1portlet_renderURL_var.reuse(_jspx_th_liferay$1portlet_renderURL_1);
              return;
            }
            _jspx_tagPool_liferay$1portlet_renderURL_var.reuse(_jspx_th_liferay$1portlet_renderURL_1);
            java.lang.String editURL = null;
            editURL = (java.lang.String) _jspx_page_context.findAttribute("editURL");
            out.write("\n\n\t\t");
            //  liferay-ui:icon
            com.liferay.taglib.ui.IconTag _jspx_th_liferay$1ui_icon_1 = (com.liferay.taglib.ui.IconTag) _jspx_tagPool_liferay$1ui_icon_url_message_nobody.get(com.liferay.taglib.ui.IconTag.class);
            _jspx_th_liferay$1ui_icon_1.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1ui_icon_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_1);
            _jspx_th_liferay$1ui_icon_1.setMessage("edit");
            _jspx_th_liferay$1ui_icon_1.setUrl( editURL );
            int _jspx_eval_liferay$1ui_icon_1 = _jspx_th_liferay$1ui_icon_1.doStartTag();
            if (_jspx_th_liferay$1ui_icon_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_liferay$1ui_icon_url_message_nobody.reuse(_jspx_th_liferay$1ui_icon_1);
              return;
            }
            _jspx_tagPool_liferay$1ui_icon_url_message_nobody.reuse(_jspx_th_liferay$1ui_icon_1);
            out.write('\n');
            out.write('	');
          }
          if (_jspx_th_c_if_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_1);
            return;
          }
          _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_1);
          out.write("\n\n\t");
          //  c:if
          com.liferay.taglib.core.IfTag _jspx_th_c_if_2 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
          _jspx_th_c_if_2.setPageContext(_jspx_page_context);
          _jspx_th_c_if_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_icon$1menu_0);
          _jspx_th_c_if_2.setTest( KBTemplatePermission.contains(permissionChecker, kbTemplate, KBActionKeys.PERMISSIONS) );
          int _jspx_eval_c_if_2 = _jspx_th_c_if_2.doStartTag();
          if (_jspx_eval_c_if_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t");
            //  liferay-security:permissionsURL
            com.liferay.taglib.security.PermissionsURLTag _jspx_th_liferay$1security_permissionsURL_0 = (com.liferay.taglib.security.PermissionsURLTag) _jspx_tagPool_liferay$1security_permissionsURL_windowState_var_resourcePrimKey_modelResourceDescription_modelResource_nobody.get(com.liferay.taglib.security.PermissionsURLTag.class);
            _jspx_th_liferay$1security_permissionsURL_0.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1security_permissionsURL_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_2);
            _jspx_th_liferay$1security_permissionsURL_0.setModelResource( KBTemplate.class.getName() );
            _jspx_th_liferay$1security_permissionsURL_0.setModelResourceDescription( kbTemplate.getTitle() );
            _jspx_th_liferay$1security_permissionsURL_0.setResourcePrimKey( String.valueOf(kbTemplate.getKbTemplateId()) );
            _jspx_th_liferay$1security_permissionsURL_0.setVar("permissionsURL");
            _jspx_th_liferay$1security_permissionsURL_0.setWindowState( LiferayWindowState.POP_UP.toString() );
            int _jspx_eval_liferay$1security_permissionsURL_0 = _jspx_th_liferay$1security_permissionsURL_0.doStartTag();
            if (_jspx_th_liferay$1security_permissionsURL_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_liferay$1security_permissionsURL_windowState_var_resourcePrimKey_modelResourceDescription_modelResource_nobody.reuse(_jspx_th_liferay$1security_permissionsURL_0);
              return;
            }
            _jspx_tagPool_liferay$1security_permissionsURL_windowState_var_resourcePrimKey_modelResourceDescription_modelResource_nobody.reuse(_jspx_th_liferay$1security_permissionsURL_0);
            java.lang.String permissionsURL = null;
            permissionsURL = (java.lang.String) _jspx_page_context.findAttribute("permissionsURL");
            out.write("\n\n\t\t");
            //  liferay-ui:icon
            com.liferay.taglib.ui.IconTag _jspx_th_liferay$1ui_icon_2 = (com.liferay.taglib.ui.IconTag) _jspx_tagPool_liferay$1ui_icon_useDialog_url_method_message_nobody.get(com.liferay.taglib.ui.IconTag.class);
            _jspx_th_liferay$1ui_icon_2.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1ui_icon_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_2);
            _jspx_th_liferay$1ui_icon_2.setMessage("permissions");
            _jspx_th_liferay$1ui_icon_2.setMethod("get");
            _jspx_th_liferay$1ui_icon_2.setUrl( permissionsURL );
            _jspx_th_liferay$1ui_icon_2.setUseDialog( true );
            int _jspx_eval_liferay$1ui_icon_2 = _jspx_th_liferay$1ui_icon_2.doStartTag();
            if (_jspx_th_liferay$1ui_icon_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_liferay$1ui_icon_useDialog_url_method_message_nobody.reuse(_jspx_th_liferay$1ui_icon_2);
              return;
            }
            _jspx_tagPool_liferay$1ui_icon_useDialog_url_method_message_nobody.reuse(_jspx_th_liferay$1ui_icon_2);
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
          _jspx_th_c_if_3.setTest( KBTemplatePermission.contains(permissionChecker, kbTemplate, KBActionKeys.DELETE) );
          int _jspx_eval_c_if_3 = _jspx_th_c_if_3.doStartTag();
          if (_jspx_eval_c_if_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t");
            //  liferay-portlet:actionURL
            com.liferay.taglib.portlet.ActionURLTag _jspx_th_liferay$1portlet_actionURL_0 = (com.liferay.taglib.portlet.ActionURLTag) _jspx_tagPool_liferay$1portlet_actionURL_var_name.get(com.liferay.taglib.portlet.ActionURLTag.class);
            _jspx_th_liferay$1portlet_actionURL_0.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1portlet_actionURL_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_3);
            _jspx_th_liferay$1portlet_actionURL_0.setName("deleteKBTemplate");
            _jspx_th_liferay$1portlet_actionURL_0.setVar("deleteURL");
            int _jspx_eval_liferay$1portlet_actionURL_0 = _jspx_th_liferay$1portlet_actionURL_0.doStartTag();
            if (_jspx_eval_liferay$1portlet_actionURL_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\t\t\t");
              //  portlet:param
              com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_5 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
              _jspx_th_portlet_param_5.setPageContext(_jspx_page_context);
              _jspx_th_portlet_param_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_actionURL_0);
              _jspx_th_portlet_param_5.setName("mvcPath");
              _jspx_th_portlet_param_5.setValue( mvcPath );
              int _jspx_eval_portlet_param_5 = _jspx_th_portlet_param_5.doStartTag();
              if (_jspx_th_portlet_param_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_5);
                return;
              }
              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_5);
              out.write("\n\t\t\t");
              //  portlet:param
              com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_6 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
              _jspx_th_portlet_param_6.setPageContext(_jspx_page_context);
              _jspx_th_portlet_param_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_actionURL_0);
              _jspx_th_portlet_param_6.setName("redirect");
              _jspx_th_portlet_param_6.setValue( currentURL );
              int _jspx_eval_portlet_param_6 = _jspx_th_portlet_param_6.doStartTag();
              if (_jspx_th_portlet_param_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_6);
                return;
              }
              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_6);
              out.write("\n\t\t\t");
              //  portlet:param
              com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_7 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
              _jspx_th_portlet_param_7.setPageContext(_jspx_page_context);
              _jspx_th_portlet_param_7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_actionURL_0);
              _jspx_th_portlet_param_7.setName("kbTemplateId");
              _jspx_th_portlet_param_7.setValue( String.valueOf(kbTemplate.getKbTemplateId()) );
              int _jspx_eval_portlet_param_7 = _jspx_th_portlet_param_7.doStartTag();
              if (_jspx_th_portlet_param_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_7);
                return;
              }
              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_7);
              out.write("\n\t\t");
            }
            if (_jspx_th_liferay$1portlet_actionURL_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_liferay$1portlet_actionURL_var_name.reuse(_jspx_th_liferay$1portlet_actionURL_0);
              return;
            }
            _jspx_tagPool_liferay$1portlet_actionURL_var_name.reuse(_jspx_th_liferay$1portlet_actionURL_0);
            java.lang.String deleteURL = null;
            deleteURL = (java.lang.String) _jspx_page_context.findAttribute("deleteURL");
            out.write("\n\n\t\t");
            //  liferay-ui:icon-delete
            com.liferay.taglib.ui.IconDeleteTag _jspx_th_liferay$1ui_icon$1delete_0 = (com.liferay.taglib.ui.IconDeleteTag) _jspx_tagPool_liferay$1ui_icon$1delete_url_nobody.get(com.liferay.taglib.ui.IconDeleteTag.class);
            _jspx_th_liferay$1ui_icon$1delete_0.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1ui_icon$1delete_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_3);
            _jspx_th_liferay$1ui_icon$1delete_0.setUrl( deleteURL );
            int _jspx_eval_liferay$1ui_icon$1delete_0 = _jspx_th_liferay$1ui_icon$1delete_0.doStartTag();
            if (_jspx_th_liferay$1ui_icon$1delete_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_liferay$1ui_icon$1delete_url_nobody.reuse(_jspx_th_liferay$1ui_icon$1delete_0);
              return;
            }
            _jspx_tagPool_liferay$1ui_icon$1delete_url_nobody.reuse(_jspx_th_liferay$1ui_icon$1delete_0);
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
}
