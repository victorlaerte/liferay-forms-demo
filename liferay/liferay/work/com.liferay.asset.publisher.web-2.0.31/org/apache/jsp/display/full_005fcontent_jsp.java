package org.apache.jsp.display;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.liferay.asset.constants.AssetWebKeys;
import com.liferay.asset.kernel.AssetRendererFactoryRegistryUtil;
import com.liferay.asset.kernel.action.AssetEntryAction;
import com.liferay.asset.kernel.exception.DuplicateQueryRuleException;
import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.model.ClassType;
import com.liferay.asset.kernel.model.ClassTypeField;
import com.liferay.asset.kernel.model.ClassTypeReader;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetVocabularyLocalServiceUtil;
import com.liferay.asset.publisher.constants.AssetPublisherConstants;
import com.liferay.asset.publisher.constants.AssetPublisherPortletKeys;
import com.liferay.asset.publisher.constants.AssetPublisherWebKeys;
import com.liferay.asset.publisher.web.configuration.AssetPublisherPortletInstanceConfiguration;
import com.liferay.asset.publisher.web.configuration.AssetPublisherWebConfiguration;
import com.liferay.asset.publisher.web.display.context.AssetEntryResult;
import com.liferay.asset.publisher.web.display.context.AssetPublisherDisplayContext;
import com.liferay.asset.publisher.web.display.context.ItemSelectorViewDisplayContext;
import com.liferay.asset.publisher.web.internal.util.AssetPublisherWebUtil;
import com.liferay.asset.publisher.web.util.AssetPublisherCustomizer;
import com.liferay.asset.publisher.web.util.AssetPublisherHelper;
import com.liferay.asset.publisher.web.util.AssetPublisherUtil;
import com.liferay.asset.util.AssetHelper;
import com.liferay.asset.util.AssetPublisherAddItemHolder;
import com.liferay.asset.util.comparator.AssetRendererFactoryTypeNameComparator;
import com.liferay.document.library.kernel.document.conversion.DocumentConversionUtil;
import com.liferay.document.library.kernel.util.DLUtil;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.item.selector.ItemSelector;
import com.liferay.item.selector.ItemSelectorReturnType;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.search.ResultRow;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.NoSuchGroupException;
import com.liferay.portal.kernel.exception.NoSuchModelException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.ClassName;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.portlet.PortletProvider;
import com.liferay.portal.kernel.portlet.PortletProviderUtil;
import com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactoryUtil;
import com.liferay.portal.kernel.portletdisplaytemplate.PortletDisplayTemplateManager;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.ResourceActionsUtil;
import com.liferay.portal.kernel.service.ClassNameLocalServiceUtil;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.PortletLocalServiceUtil;
import com.liferay.portal.kernel.service.WorkflowDefinitionLinkLocalServiceUtil;
import com.liferay.portal.kernel.service.permission.PortletPermissionUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.trash.TrashHandler;
import com.liferay.portal.kernel.trash.TrashHandlerRegistryUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.FriendlyURLNormalizerUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.KeyValuePair;
import com.liferay.portal.kernel.util.KeyValuePairComparator;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.TextFormatter;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portlet.usersadmin.search.GroupSearch;
import com.liferay.rss.util.RSSUtil;
import com.liferay.site.item.selector.criteria.SiteItemSelectorReturnType;
import com.liferay.site.item.selector.criterion.SiteItemSelectorCriterion;
import java.io.Serializable;
import java.text.Format;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import javax.portlet.PortletMode;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

public final class full_005fcontent_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {



  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(4);
    _jspx_dependants.add("/init.jsp");
    _jspx_dependants.add("/init-ext.jsp");
    _jspx_dependants.add("/asset_print.jspf");
    _jspx_dependants.add("/asset_export.jspf");
  }

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_namespace_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_icon_url_method_message_label_iconCssClass_data_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_icon_url_markupView_icon_cssClass_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1asset_asset$1display_showExtraInfo_assetRendererFactory_assetRenderer_assetEntry_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_ratings_classPK_className_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_message_key_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_if_test;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1comment_discussion_userId_redirect_ratingsEnabled_formName_classPK_className_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1asset_asset$1links_viewInContext_portletURL_assetEntryId_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_icon_url_message_markupView_label_icon_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_language_languageIds_languageId_formAction_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_script;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_otherwise;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1social$1bookmarks_bookmarks_urlImpl_types_title_target_displayStyle_classPK_className_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1asset_asset$1metadata_metadataFields_filterByMetadata_classPK_className_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1theme_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_choose;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1frontend_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1flags_flags_reportedUserId_message_enabled_contentTitle_classPK_className_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1util_include_servletContext_page_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_when_test;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_icon$1list;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_portlet_namespace_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_icon_url_method_message_label_iconCssClass_data_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_icon_url_markupView_icon_cssClass_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1asset_asset$1display_showExtraInfo_assetRendererFactory_assetRenderer_assetEntry_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_ratings_classPK_className_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_message_key_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_if_test = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1comment_discussion_userId_redirect_ratingsEnabled_formName_classPK_className_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1asset_asset$1links_viewInContext_portletURL_assetEntryId_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_icon_url_message_markupView_label_icon_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_language_languageIds_languageId_formAction_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_script = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_otherwise = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1social$1bookmarks_bookmarks_urlImpl_types_title_target_displayStyle_classPK_className_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1asset_asset$1metadata_metadataFields_filterByMetadata_classPK_className_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1theme_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_choose = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1frontend_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1flags_flags_reportedUserId_message_enabled_contentTitle_classPK_className_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1util_include_servletContext_page_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_when_test = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_icon$1list = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_portlet_namespace_nobody.release();
    _jspx_tagPool_liferay$1ui_icon_url_method_message_label_iconCssClass_data_nobody.release();
    _jspx_tagPool_liferay$1ui_icon_url_markupView_icon_cssClass_nobody.release();
    _jspx_tagPool_liferay$1asset_asset$1display_showExtraInfo_assetRendererFactory_assetRenderer_assetEntry_nobody.release();
    _jspx_tagPool_liferay$1ui_ratings_classPK_className_nobody.release();
    _jspx_tagPool_liferay$1ui_message_key_nobody.release();
    _jspx_tagPool_c_if_test.release();
    _jspx_tagPool_liferay$1comment_discussion_userId_redirect_ratingsEnabled_formName_classPK_className_nobody.release();
    _jspx_tagPool_liferay$1asset_asset$1links_viewInContext_portletURL_assetEntryId_nobody.release();
    _jspx_tagPool_liferay$1ui_icon_url_message_markupView_label_icon_nobody.release();
    _jspx_tagPool_liferay$1ui_language_languageIds_languageId_formAction_nobody.release();
    _jspx_tagPool_aui_script.release();
    _jspx_tagPool_c_otherwise.release();
    _jspx_tagPool_liferay$1social$1bookmarks_bookmarks_urlImpl_types_title_target_displayStyle_classPK_className_nobody.release();
    _jspx_tagPool_portlet_defineObjects_nobody.release();
    _jspx_tagPool_liferay$1asset_asset$1metadata_metadataFields_filterByMetadata_classPK_className_nobody.release();
    _jspx_tagPool_liferay$1theme_defineObjects_nobody.release();
    _jspx_tagPool_c_choose.release();
    _jspx_tagPool_liferay$1frontend_defineObjects_nobody.release();
    _jspx_tagPool_liferay$1flags_flags_reportedUserId_message_enabled_contentTitle_classPK_className_nobody.release();
    _jspx_tagPool_liferay$1util_include_servletContext_page_nobody.release();
    _jspx_tagPool_c_when_test.release();
    _jspx_tagPool_liferay$1ui_icon$1list.release();
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

AssetHelper assetHelper = (AssetHelper)request.getAttribute(AssetWebKeys.ASSET_HELPER);
AssetPublisherCustomizer assetPublisherCustomizer = (AssetPublisherCustomizer)request.getAttribute(AssetPublisherWebKeys.ASSET_PUBLISHER_CUSTOMIZER);
AssetPublisherDisplayContext assetPublisherDisplayContext = new AssetPublisherDisplayContext(assetPublisherCustomizer, liferayPortletRequest, liferayPortletResponse, portletPreferences);
AssetPublisherPortletInstanceConfiguration assetPublisherPortletInstanceConfiguration = (AssetPublisherPortletInstanceConfiguration)request.getAttribute(AssetPublisherWebKeys.ASSET_PUBLISHER_PORTLET_INSTANCE_CONFIGURATION);
AssetPublisherWebConfiguration assetPublisherWebConfiguration = (AssetPublisherWebConfiguration)request.getAttribute(AssetPublisherWebKeys.ASSET_PUBLISHER_WEB_CONFIGURATION);
AssetPublisherWebUtil assetPublisherWebUtil = (AssetPublisherWebUtil)request.getAttribute(AssetPublisherWebKeys.ASSET_PUBLISHER_WEB_UTIL);

Format dateFormatDate = FastDateFormatFactoryUtil.getDate(locale, timeZone);

      out.write('\n');
      out.write('\n');
      out.write('\n');
      out.write('\n');

String redirect = PortalUtil.escapeRedirect(ParamUtil.getString(request, "redirect"));

if (Validator.isNull(redirect)) {
	redirect = ParamUtil.getString(PortalUtil.getOriginalServletRequest(request), "redirect");
}

boolean showBackURL = GetterUtil.getBoolean(request.getAttribute("view.jsp-showBackURL"));

if (Validator.isNull(redirect)) {
	PortletURL portletURL = renderResponse.createRenderURL();

	portletURL.setParameter("mvcPath", "/view.jsp");

	redirect = portletURL.toString();
}

List results = (List)request.getAttribute("view.jsp-results");

int assetEntryIndex = ((Integer)request.getAttribute("view.jsp-assetEntryIndex")).intValue();

AssetEntry assetEntry = (AssetEntry)request.getAttribute("view.jsp-assetEntry");
AssetRendererFactory<?> assetRendererFactory = (AssetRendererFactory<?>)request.getAttribute("view.jsp-assetRendererFactory");
AssetRenderer<?> assetRenderer = (AssetRenderer<?>)request.getAttribute("view.jsp-assetRenderer");

String languageId = LanguageUtil.getLanguageId(request);

String title = assetRenderer.getTitle(LocaleUtil.fromLanguageId(languageId));

boolean print = ((Boolean)request.getAttribute("view.jsp-print")).booleanValue();
boolean viewInContext = ((Boolean)request.getAttribute("view.jsp-viewInContext")).booleanValue();
boolean workflowEnabled = WorkflowDefinitionLinkLocalServiceUtil.hasWorkflowDefinitionLink(assetEntry.getCompanyId(), assetEntry.getGroupId(), assetEntry.getClassName());

assetPublisherDisplayContext.setLayoutAssetEntry(assetEntry);

assetEntry = assetPublisherDisplayContext.incrementViewCounter(assetEntry);

request.setAttribute("view.jsp-fullContentRedirect", workflowEnabled ? redirect : currentURL);
request.setAttribute("view.jsp-showIconLabel", true);

      out.write("\n\n<div class=\"h2\">\n\t");
      //  c:if
      com.liferay.taglib.core.IfTag _jspx_th_c_if_0 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
      _jspx_th_c_if_0.setPageContext(_jspx_page_context);
      _jspx_th_c_if_0.setParent(null);
      _jspx_th_c_if_0.setTest( assetPublisherDisplayContext.isShowAssetTitle() );
      int _jspx_eval_c_if_0 = _jspx_th_c_if_0.doStartTag();
      if (_jspx_eval_c_if_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        out.write("\n\t\t");
        //  c:if
        com.liferay.taglib.core.IfTag _jspx_th_c_if_1 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
        _jspx_th_c_if_1.setPageContext(_jspx_page_context);
        _jspx_th_c_if_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_0);
        _jspx_th_c_if_1.setTest( showBackURL && Validator.isNotNull(redirect) );
        int _jspx_eval_c_if_1 = _jspx_th_c_if_1.doStartTag();
        if (_jspx_eval_c_if_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          out.write("\n\t\t\t");
          //  liferay-ui:icon
          com.liferay.taglib.ui.IconTag _jspx_th_liferay$1ui_icon_0 = (com.liferay.taglib.ui.IconTag) _jspx_tagPool_liferay$1ui_icon_url_markupView_icon_cssClass_nobody.get(com.liferay.taglib.ui.IconTag.class);
          _jspx_th_liferay$1ui_icon_0.setPageContext(_jspx_page_context);
          _jspx_th_liferay$1ui_icon_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_1);
          _jspx_th_liferay$1ui_icon_0.setCssClass("header-back-to");
          _jspx_th_liferay$1ui_icon_0.setIcon("angle-left");
          _jspx_th_liferay$1ui_icon_0.setMarkupView("lexicon");
          _jspx_th_liferay$1ui_icon_0.setUrl( redirect );
          int _jspx_eval_liferay$1ui_icon_0 = _jspx_th_liferay$1ui_icon_0.doStartTag();
          if (_jspx_th_liferay$1ui_icon_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_liferay$1ui_icon_url_markupView_icon_cssClass_nobody.reuse(_jspx_th_liferay$1ui_icon_0);
            return;
          }
          _jspx_tagPool_liferay$1ui_icon_url_markupView_icon_cssClass_nobody.reuse(_jspx_th_liferay$1ui_icon_0);
          out.write("\n\t\t");
        }
        if (_jspx_th_c_if_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_1);
          return;
        }
        _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_1);
        out.write("\n\n\t\t<span class=\"header-title\">");
        out.print( HtmlUtil.escape(title) );
        out.write("</span>\n\t");
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
      _jspx_th_c_if_2.setParent(null);
      _jspx_th_c_if_2.setTest( !print );
      int _jspx_eval_c_if_2 = _jspx_th_c_if_2.doStartTag();
      if (_jspx_eval_c_if_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        out.write("\n\t\t");
        //  liferay-util:include
        com.liferay.taglib.util.IncludeTag _jspx_th_liferay$1util_include_0 = (com.liferay.taglib.util.IncludeTag) _jspx_tagPool_liferay$1util_include_servletContext_page_nobody.get(com.liferay.taglib.util.IncludeTag.class);
        _jspx_th_liferay$1util_include_0.setPageContext(_jspx_page_context);
        _jspx_th_liferay$1util_include_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_2);
        _jspx_th_liferay$1util_include_0.setPage("/asset_actions.jsp");
        _jspx_th_liferay$1util_include_0.setServletContext( application );
        int _jspx_eval_liferay$1util_include_0 = _jspx_th_liferay$1util_include_0.doStartTag();
        if (_jspx_th_liferay$1util_include_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_liferay$1util_include_servletContext_page_nobody.reuse(_jspx_th_liferay$1util_include_0);
          return;
        }
        _jspx_tagPool_liferay$1util_include_servletContext_page_nobody.reuse(_jspx_th_liferay$1util_include_0);
        out.write('\n');
        out.write('	');
      }
      if (_jspx_th_c_if_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_2);
        return;
      }
      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_2);
      out.write("\n</div>\n\n<div class=\"asset-full-content clearfix ");
      out.print( assetPublisherDisplayContext.isDefaultAssetPublisher() ? "default-asset-publisher" : StringPool.BLANK );
      out.write(' ');
      out.print( assetPublisherDisplayContext.isShowAssetTitle() ? "show-asset-title" : "no-title" );
      out.write("\">\n\t");
      //  c:if
      com.liferay.taglib.core.IfTag _jspx_th_c_if_3 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
      _jspx_th_c_if_3.setPageContext(_jspx_page_context);
      _jspx_th_c_if_3.setParent(null);
      _jspx_th_c_if_3.setTest( (assetPublisherDisplayContext.isEnableConversions() && assetRenderer.isConvertible()) || (assetPublisherDisplayContext.isEnablePrint() && assetRenderer.isPrintable()) || (assetPublisherDisplayContext.isShowAvailableLocales() && assetRenderer.isLocalizable()) );
      int _jspx_eval_c_if_3 = _jspx_th_c_if_3.doStartTag();
      if (_jspx_eval_c_if_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        out.write("\n\t\t<div class=\"asset-user-actions\">\n\t\t\t");
        //  c:if
        com.liferay.taglib.core.IfTag _jspx_th_c_if_4 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
        _jspx_th_c_if_4.setPageContext(_jspx_page_context);
        _jspx_th_c_if_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_3);
        _jspx_th_c_if_4.setTest( assetPublisherDisplayContext.isEnablePrint() );
        int _jspx_eval_c_if_4 = _jspx_th_c_if_4.doStartTag();
        if (_jspx_eval_c_if_4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          out.write("\n\t\t\t\t<div class=\"print-action\">\n\t\t\t\t\t");
          out.write('\n');
          out.write('\n');

PortletURL printAssetURL = renderResponse.createRenderURL();

printAssetURL.setParameter("mvcPath", "/view_content.jsp");
printAssetURL.setParameter("assetEntryId", String.valueOf(assetEntry.getEntryId()));
printAssetURL.setParameter("viewMode", Constants.PRINT);
printAssetURL.setParameter("type", assetRendererFactory.getType());
printAssetURL.setParameter("languageId", languageId);

if (Validator.isNotNull(assetRenderer.getUrlTitle())) {
	if (assetRenderer.getGroupId() != scopeGroupId) {
		printAssetURL.setParameter("groupId", String.valueOf(assetRenderer.getGroupId()));
	}

	printAssetURL.setParameter("urlTitle", assetRenderer.getUrlTitle());
}

printAssetURL.setWindowState(LiferayWindowState.POP_UP);

          out.write('\n');
          out.write('\n');
          //  c:choose
          com.liferay.taglib.core.ChooseTag _jspx_th_c_choose_0 = (com.liferay.taglib.core.ChooseTag) _jspx_tagPool_c_choose.get(com.liferay.taglib.core.ChooseTag.class);
          _jspx_th_c_choose_0.setPageContext(_jspx_page_context);
          _jspx_th_c_choose_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_4);
          int _jspx_eval_c_choose_0 = _jspx_th_c_choose_0.doStartTag();
          if (_jspx_eval_c_choose_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write('\n');
            out.write('	');
            //  c:when
            com.liferay.taglib.core.WhenTag _jspx_th_c_when_0 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
            _jspx_th_c_when_0.setPageContext(_jspx_page_context);
            _jspx_th_c_when_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_0);
            _jspx_th_c_when_0.setTest( print );
            int _jspx_eval_c_when_0 = _jspx_th_c_when_0.doStartTag();
            if (_jspx_eval_c_when_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\t\t");
              //  liferay-ui:icon
              com.liferay.taglib.ui.IconTag _jspx_th_liferay$1ui_icon_1 = (com.liferay.taglib.ui.IconTag) _jspx_tagPool_liferay$1ui_icon_url_message_markupView_label_icon_nobody.get(com.liferay.taglib.ui.IconTag.class);
              _jspx_th_liferay$1ui_icon_1.setPageContext(_jspx_page_context);
              _jspx_th_liferay$1ui_icon_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_0);
              _jspx_th_liferay$1ui_icon_1.setIcon("print");
              _jspx_th_liferay$1ui_icon_1.setLabel( true );
              _jspx_th_liferay$1ui_icon_1.setMarkupView("lexicon");
              _jspx_th_liferay$1ui_icon_1.setMessage( LanguageUtil.format(request, "print-x-x", new Object[] {"hide-accessible", HtmlUtil.escape(assetRenderer.getTitle(locale))}, false) );
              _jspx_th_liferay$1ui_icon_1.setUrl("javascript:print();");
              int _jspx_eval_liferay$1ui_icon_1 = _jspx_th_liferay$1ui_icon_1.doStartTag();
              if (_jspx_th_liferay$1ui_icon_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_liferay$1ui_icon_url_message_markupView_label_icon_nobody.reuse(_jspx_th_liferay$1ui_icon_1);
                return;
              }
              _jspx_tagPool_liferay$1ui_icon_url_message_markupView_label_icon_nobody.reuse(_jspx_th_liferay$1ui_icon_1);
              out.write("\n\n\t\t");
              if (_jspx_meth_aui_script_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_when_0, _jspx_page_context))
                return;
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
            //  c:otherwise
            com.liferay.taglib.core.OtherwiseTag _jspx_th_c_otherwise_0 = (com.liferay.taglib.core.OtherwiseTag) _jspx_tagPool_c_otherwise.get(com.liferay.taglib.core.OtherwiseTag.class);
            _jspx_th_c_otherwise_0.setPageContext(_jspx_page_context);
            _jspx_th_c_otherwise_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_0);
            int _jspx_eval_c_otherwise_0 = _jspx_th_c_otherwise_0.doStartTag();
            if (_jspx_eval_c_otherwise_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\t\t");
              //  liferay-ui:icon
              com.liferay.taglib.ui.IconTag _jspx_th_liferay$1ui_icon_2 = (com.liferay.taglib.ui.IconTag) _jspx_tagPool_liferay$1ui_icon_url_message_markupView_label_icon_nobody.get(com.liferay.taglib.ui.IconTag.class);
              _jspx_th_liferay$1ui_icon_2.setPageContext(_jspx_page_context);
              _jspx_th_liferay$1ui_icon_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_0);
              _jspx_th_liferay$1ui_icon_2.setIcon("print");
              _jspx_th_liferay$1ui_icon_2.setLabel( true );
              _jspx_th_liferay$1ui_icon_2.setMarkupView("lexicon");
              _jspx_th_liferay$1ui_icon_2.setMessage( LanguageUtil.format(request, "print-x-x", new Object[] {"hide-accessible", HtmlUtil.escape(assetRenderer.getTitle(locale))}, false) );
              _jspx_th_liferay$1ui_icon_2.setUrl( "javascript:" + renderResponse.getNamespace() + "printPage_" + assetEntryIndex + "();" );
              int _jspx_eval_liferay$1ui_icon_2 = _jspx_th_liferay$1ui_icon_2.doStartTag();
              if (_jspx_th_liferay$1ui_icon_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_liferay$1ui_icon_url_message_markupView_label_icon_nobody.reuse(_jspx_th_liferay$1ui_icon_2);
                return;
              }
              _jspx_tagPool_liferay$1ui_icon_url_message_markupView_label_icon_nobody.reuse(_jspx_th_liferay$1ui_icon_2);
              out.write("\n\n\t\t");
              //  aui:script
              com.liferay.taglib.aui.ScriptTag _jspx_th_aui_script_1 = (com.liferay.taglib.aui.ScriptTag) _jspx_tagPool_aui_script.get(com.liferay.taglib.aui.ScriptTag.class);
              _jspx_th_aui_script_1.setPageContext(_jspx_page_context);
              _jspx_th_aui_script_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_0);
              int _jspx_eval_aui_script_1 = _jspx_th_aui_script_1.doStartTag();
              if (_jspx_eval_aui_script_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                if (_jspx_eval_aui_script_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                  out = _jspx_page_context.pushBody();
                  _jspx_th_aui_script_1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                  _jspx_th_aui_script_1.doInitBody();
                }
                do {
                  out.write("\n\t\t\tfunction ");
                  if (_jspx_meth_portlet_namespace_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
                    return;
                  out.write("printPage_");
                  out.print( assetEntryIndex );
                  out.write("() {\n\t\t\t\twindow.open('");
                  out.print( printAssetURL );
                  out.write("', '', 'directories=0,height=480,left=80,location=1,menubar=1,resizable=1,scrollbars=yes,status=0,toolbar=0,top=180,width=640');\n\t\t\t}\n\t\t");
                  int evalDoAfterBody = _jspx_th_aui_script_1.doAfterBody();
                  if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                    break;
                } while (true);
                if (_jspx_eval_aui_script_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                  out = _jspx_page_context.popBody();
              }
              if (_jspx_th_aui_script_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_aui_script.reuse(_jspx_th_aui_script_1);
                return;
              }
              _jspx_tagPool_aui_script.reuse(_jspx_th_aui_script_1);
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
          if (_jspx_th_c_choose_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_0);
            return;
          }
          _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_0);
          out.write("\n\t\t\t\t</div>\n\t\t\t");
        }
        if (_jspx_th_c_if_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_4);
          return;
        }
        _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_4);
        out.write("\n\n\t\t\t");
        //  c:if
        com.liferay.taglib.core.IfTag _jspx_th_c_if_5 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
        _jspx_th_c_if_5.setPageContext(_jspx_page_context);
        _jspx_th_c_if_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_3);
        _jspx_th_c_if_5.setTest( assetPublisherDisplayContext.isEnableConversions() && assetRenderer.isConvertible() && !print );
        int _jspx_eval_c_if_5 = _jspx_th_c_if_5.doStartTag();
        if (_jspx_eval_c_if_5 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          out.write("\n\t\t\t\t<div class=\"export-actions\">\n\t\t\t\t\t");
          out.write('\n');
          out.write('\n');

PortletURL exportAssetURL = assetRenderer.getURLExport(liferayPortletRequest, liferayPortletResponse);

exportAssetURL.setParameter("plid", String.valueOf(themeDisplay.getPlid()));
exportAssetURL.setParameter("portletResource", portletDisplay.getId());
exportAssetURL.setWindowState(LiferayWindowState.EXCLUSIVE);

          out.write('\n');
          out.write('\n');
          //  liferay-ui:icon-list
          com.liferay.taglib.ui.IconListTag _jspx_th_liferay$1ui_icon$1list_0 = (com.liferay.taglib.ui.IconListTag) _jspx_tagPool_liferay$1ui_icon$1list.get(com.liferay.taglib.ui.IconListTag.class);
          _jspx_th_liferay$1ui_icon$1list_0.setPageContext(_jspx_page_context);
          _jspx_th_liferay$1ui_icon$1list_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_5);
          int _jspx_eval_liferay$1ui_icon$1list_0 = _jspx_th_liferay$1ui_icon$1list_0.doStartTag();
          if (_jspx_eval_liferay$1ui_icon$1list_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            if (_jspx_eval_liferay$1ui_icon$1list_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
              out = _jspx_page_context.pushBody();
              _jspx_th_liferay$1ui_icon$1list_0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
              _jspx_th_liferay$1ui_icon$1list_0.doInitBody();
            }
            do {
              out.write("\n\n\t");

	for (String extension : assetPublisherDisplayContext.getExtensions(assetRenderer)) {
		Map<String, Object> data = new HashMap<String, Object>();

		exportAssetURL.setParameter("targetExtension", extension);

		data.put("resource-href", exportAssetURL.toString());
	
              out.write("\n\n\t\t");
              //  liferay-ui:icon
              com.liferay.taglib.ui.IconTag _jspx_th_liferay$1ui_icon_3 = (com.liferay.taglib.ui.IconTag) _jspx_tagPool_liferay$1ui_icon_url_method_message_label_iconCssClass_data_nobody.get(com.liferay.taglib.ui.IconTag.class);
              _jspx_th_liferay$1ui_icon_3.setPageContext(_jspx_page_context);
              _jspx_th_liferay$1ui_icon_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_icon$1list_0);
              _jspx_th_liferay$1ui_icon_3.setData( data );
              _jspx_th_liferay$1ui_icon_3.setIconCssClass( DLUtil.getFileIconCssClass(extension) );
              _jspx_th_liferay$1ui_icon_3.setLabel( true );
              _jspx_th_liferay$1ui_icon_3.setMessage( LanguageUtil.format(request, "x-convert-x-to-x", new Object[] {"hide-accessible", assetRenderer.getTitle(locale), StringUtil.toUpperCase(HtmlUtil.escape(extension))}, false) );
              _jspx_th_liferay$1ui_icon_3.setMethod("get");
              _jspx_th_liferay$1ui_icon_3.setUrl( exportAssetURL.toString() );
              int _jspx_eval_liferay$1ui_icon_3 = _jspx_th_liferay$1ui_icon_3.doStartTag();
              if (_jspx_th_liferay$1ui_icon_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_liferay$1ui_icon_url_method_message_label_iconCssClass_data_nobody.reuse(_jspx_th_liferay$1ui_icon_3);
                return;
              }
              _jspx_tagPool_liferay$1ui_icon_url_method_message_label_iconCssClass_data_nobody.reuse(_jspx_th_liferay$1ui_icon_3);
              out.write("\n\n\t");

	}
	
              out.write('\n');
              out.write('\n');
              int evalDoAfterBody = _jspx_th_liferay$1ui_icon$1list_0.doAfterBody();
              if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                break;
            } while (true);
            if (_jspx_eval_liferay$1ui_icon$1list_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
              out = _jspx_page_context.popBody();
          }
          if (_jspx_th_liferay$1ui_icon$1list_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_liferay$1ui_icon$1list.reuse(_jspx_th_liferay$1ui_icon$1list_0);
            return;
          }
          _jspx_tagPool_liferay$1ui_icon$1list.reuse(_jspx_th_liferay$1ui_icon$1list_0);
          out.write("\n\t\t\t\t</div>\n\t\t\t");
        }
        if (_jspx_th_c_if_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_5);
          return;
        }
        _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_5);
        out.write("\n\n\t\t\t");
        //  c:if
        com.liferay.taglib.core.IfTag _jspx_th_c_if_6 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
        _jspx_th_c_if_6.setPageContext(_jspx_page_context);
        _jspx_th_c_if_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_3);
        _jspx_th_c_if_6.setTest( assetPublisherDisplayContext.isShowAvailableLocales() && assetRenderer.isLocalizable() && !print );
        int _jspx_eval_c_if_6 = _jspx_th_c_if_6.doStartTag();
        if (_jspx_eval_c_if_6 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          out.write("\n\n\t\t\t\t");

				String[] availableLanguageIds = assetRenderer.getAvailableLanguageIds();
				
          out.write("\n\n\t\t\t\t");
          //  c:if
          com.liferay.taglib.core.IfTag _jspx_th_c_if_7 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
          _jspx_th_c_if_7.setPageContext(_jspx_page_context);
          _jspx_th_c_if_7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_6);
          _jspx_th_c_if_7.setTest( availableLanguageIds.length > 1 );
          int _jspx_eval_c_if_7 = _jspx_th_c_if_7.doStartTag();
          if (_jspx_eval_c_if_7 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t\t\t\t");
            //  c:if
            com.liferay.taglib.core.IfTag _jspx_th_c_if_8 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
            _jspx_th_c_if_8.setPageContext(_jspx_page_context);
            _jspx_th_c_if_8.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_7);
            _jspx_th_c_if_8.setTest( assetPublisherDisplayContext.isEnableConversions() || assetPublisherDisplayContext.isEnablePrint() );
            int _jspx_eval_c_if_8 = _jspx_th_c_if_8.doStartTag();
            if (_jspx_eval_c_if_8 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\t\t\t\t\t\t<div class=\"locale-separator\"> </div>\n\t\t\t\t\t");
            }
            if (_jspx_th_c_if_8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_8);
              return;
            }
            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_8);
            out.write("\n\n\t\t\t\t\t<div class=\"locale-actions\">\n\t\t\t\t\t\t");
            //  liferay-ui:language
            com.liferay.taglib.ui.LanguageTag _jspx_th_liferay$1ui_language_0 = (com.liferay.taglib.ui.LanguageTag) _jspx_tagPool_liferay$1ui_language_languageIds_languageId_formAction_nobody.get(com.liferay.taglib.ui.LanguageTag.class);
            _jspx_th_liferay$1ui_language_0.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1ui_language_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_7);
            _jspx_th_liferay$1ui_language_0.setFormAction( currentURL );
            _jspx_th_liferay$1ui_language_0.setLanguageId( languageId );
            _jspx_th_liferay$1ui_language_0.setLanguageIds( availableLanguageIds );
            int _jspx_eval_liferay$1ui_language_0 = _jspx_th_liferay$1ui_language_0.doStartTag();
            if (_jspx_th_liferay$1ui_language_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_liferay$1ui_language_languageIds_languageId_formAction_nobody.reuse(_jspx_th_liferay$1ui_language_0);
              return;
            }
            _jspx_tagPool_liferay$1ui_language_languageIds_languageId_formAction_nobody.reuse(_jspx_th_liferay$1ui_language_0);
            out.write("\n\t\t\t\t\t</div>\n\t\t\t\t");
          }
          if (_jspx_th_c_if_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_7);
            return;
          }
          _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_7);
          out.write("\n\t\t\t");
        }
        if (_jspx_th_c_if_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_6);
          return;
        }
        _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_6);
        out.write("\n\t\t</div>\n\t");
      }
      if (_jspx_th_c_if_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_3);
        return;
      }
      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_3);
      out.write("\n\n\t");

	PortletURL viewFullContentURL = renderResponse.createRenderURL();

	viewFullContentURL.setParameter("mvcPath", "/view_content.jsp");
	viewFullContentURL.setParameter("type", assetRendererFactory.getType());

	if (print) {
		viewFullContentURL.setParameter("viewMode", Constants.PRINT);
	}

	if (Validator.isNotNull(assetRenderer.getUrlTitle())) {
		if (assetRenderer.getGroupId() != scopeGroupId) {
			viewFullContentURL.setParameter("groupId", String.valueOf(assetRenderer.getGroupId()));
		}

		viewFullContentURL.setParameter("urlTitle", assetRenderer.getUrlTitle());
	}
	
      out.write("\n\n\t<div class=\"asset-content\" id=\"");
      if (_jspx_meth_portlet_namespace_1(_jspx_page_context))
        return;
      out.print( assetEntry.getEntryId() );
      out.write("\">\n\t\t");
      //  liferay-asset:asset-display
      com.liferay.asset.taglib.servlet.taglib.AssetDisplayTag _jspx_th_liferay$1asset_asset$1display_0 = (com.liferay.asset.taglib.servlet.taglib.AssetDisplayTag) _jspx_tagPool_liferay$1asset_asset$1display_showExtraInfo_assetRendererFactory_assetRenderer_assetEntry_nobody.get(com.liferay.asset.taglib.servlet.taglib.AssetDisplayTag.class);
      _jspx_th_liferay$1asset_asset$1display_0.setPageContext(_jspx_page_context);
      _jspx_th_liferay$1asset_asset$1display_0.setParent(null);
      _jspx_th_liferay$1asset_asset$1display_0.setAssetEntry( assetEntry );
      _jspx_th_liferay$1asset_asset$1display_0.setAssetRenderer( assetRenderer );
      _jspx_th_liferay$1asset_asset$1display_0.setAssetRendererFactory( assetRendererFactory );
      _jspx_th_liferay$1asset_asset$1display_0.setShowExtraInfo( assetPublisherDisplayContext.isShowExtraInfo() );
      int _jspx_eval_liferay$1asset_asset$1display_0 = _jspx_th_liferay$1asset_asset$1display_0.doStartTag();
      if (_jspx_th_liferay$1asset_asset$1display_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_liferay$1asset_asset$1display_showExtraInfo_assetRendererFactory_assetRenderer_assetEntry_nobody.reuse(_jspx_th_liferay$1asset_asset$1display_0);
        return;
      }
      _jspx_tagPool_liferay$1asset_asset$1display_showExtraInfo_assetRendererFactory_assetRenderer_assetEntry_nobody.reuse(_jspx_th_liferay$1asset_asset$1display_0);
      out.write("\n\n\t\t<div class=\"pull-right\">\n\t\t\t");
      //  liferay-social-bookmarks:bookmarks
      com.liferay.social.bookmarks.taglib.servlet.taglib.SocialBookmarksTag _jspx_th_liferay$1social$1bookmarks_bookmarks_0 = (com.liferay.social.bookmarks.taglib.servlet.taglib.SocialBookmarksTag) _jspx_tagPool_liferay$1social$1bookmarks_bookmarks_urlImpl_types_title_target_displayStyle_classPK_className_nobody.get(com.liferay.social.bookmarks.taglib.servlet.taglib.SocialBookmarksTag.class);
      _jspx_th_liferay$1social$1bookmarks_bookmarks_0.setPageContext(_jspx_page_context);
      _jspx_th_liferay$1social$1bookmarks_bookmarks_0.setParent(null);
      _jspx_th_liferay$1social$1bookmarks_bookmarks_0.setClassName( assetEntry.getClassName() );
      _jspx_th_liferay$1social$1bookmarks_bookmarks_0.setClassPK( assetEntry.getClassPK() );
      _jspx_th_liferay$1social$1bookmarks_bookmarks_0.setDisplayStyle( assetPublisherDisplayContext.getSocialBookmarksDisplayStyle() );
      _jspx_th_liferay$1social$1bookmarks_bookmarks_0.setTarget("_blank");
      _jspx_th_liferay$1social$1bookmarks_bookmarks_0.setTitle( title );
      _jspx_th_liferay$1social$1bookmarks_bookmarks_0.setTypes( assetPublisherDisplayContext.getSocialBookmarksTypes() );
      _jspx_th_liferay$1social$1bookmarks_bookmarks_0.setUrlImpl( viewFullContentURL );
      int _jspx_eval_liferay$1social$1bookmarks_bookmarks_0 = _jspx_th_liferay$1social$1bookmarks_bookmarks_0.doStartTag();
      if (_jspx_th_liferay$1social$1bookmarks_bookmarks_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_liferay$1social$1bookmarks_bookmarks_urlImpl_types_title_target_displayStyle_classPK_className_nobody.reuse(_jspx_th_liferay$1social$1bookmarks_bookmarks_0);
        return;
      }
      _jspx_tagPool_liferay$1social$1bookmarks_bookmarks_urlImpl_types_title_target_displayStyle_classPK_className_nobody.reuse(_jspx_th_liferay$1social$1bookmarks_bookmarks_0);
      out.write("\n\t\t</div>\n\n\t\t");
      //  c:if
      com.liferay.taglib.core.IfTag _jspx_th_c_if_9 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
      _jspx_th_c_if_9.setPageContext(_jspx_page_context);
      _jspx_th_c_if_9.setParent(null);
      _jspx_th_c_if_9.setTest( assetPublisherDisplayContext.isEnableFlags() );
      int _jspx_eval_c_if_9 = _jspx_th_c_if_9.doStartTag();
      if (_jspx_eval_c_if_9 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        out.write("\n\n\t\t\t");

			TrashHandler trashHandler = TrashHandlerRegistryUtil.getTrashHandler(assetRenderer.getClassName());

			boolean inTrash = trashHandler.isInTrash(assetEntry.getClassPK());
			
        out.write("\n\n\t\t\t<div class=\"asset-flag\">\n\t\t\t\t");
        //  liferay-flags:flags
        com.liferay.flags.taglib.servlet.taglib.soy.FlagsTag _jspx_th_liferay$1flags_flags_0 = (com.liferay.flags.taglib.servlet.taglib.soy.FlagsTag) _jspx_tagPool_liferay$1flags_flags_reportedUserId_message_enabled_contentTitle_classPK_className_nobody.get(com.liferay.flags.taglib.servlet.taglib.soy.FlagsTag.class);
        _jspx_th_liferay$1flags_flags_0.setPageContext(_jspx_page_context);
        _jspx_th_liferay$1flags_flags_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_9);
        _jspx_th_liferay$1flags_flags_0.setClassName( assetEntry.getClassName() );
        _jspx_th_liferay$1flags_flags_0.setClassPK( assetEntry.getClassPK() );
        _jspx_th_liferay$1flags_flags_0.setContentTitle( title );
        _jspx_th_liferay$1flags_flags_0.setEnabled( !inTrash );
        _jspx_th_liferay$1flags_flags_0.setMessage( inTrash ? "flags-are-disabled-because-this-entry-is-in-the-recycle-bin" : StringPool.BLANK );
        _jspx_th_liferay$1flags_flags_0.setReportedUserId( assetRenderer.getUserId() );
        int _jspx_eval_liferay$1flags_flags_0 = _jspx_th_liferay$1flags_flags_0.doStartTag();
        if (_jspx_th_liferay$1flags_flags_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_liferay$1flags_flags_reportedUserId_message_enabled_contentTitle_classPK_className_nobody.reuse(_jspx_th_liferay$1flags_flags_0);
          return;
        }
        _jspx_tagPool_liferay$1flags_flags_reportedUserId_message_enabled_contentTitle_classPK_className_nobody.reuse(_jspx_th_liferay$1flags_flags_0);
        out.write("\n\t\t\t</div>\n\t\t");
      }
      if (_jspx_th_c_if_9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_9);
        return;
      }
      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_9);
      out.write("\n\n\t\t");
      //  c:if
      com.liferay.taglib.core.IfTag _jspx_th_c_if_10 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
      _jspx_th_c_if_10.setPageContext(_jspx_page_context);
      _jspx_th_c_if_10.setParent(null);
      _jspx_th_c_if_10.setTest( assetPublisherDisplayContext.isEnableRatings() && assetRenderer.isRatable() );
      int _jspx_eval_c_if_10 = _jspx_th_c_if_10.doStartTag();
      if (_jspx_eval_c_if_10 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        out.write("\n\t\t\t<div class=\"asset-ratings\">\n\t\t\t\t");
        //  liferay-ui:ratings
        com.liferay.taglib.ui.RatingsTag _jspx_th_liferay$1ui_ratings_0 = (com.liferay.taglib.ui.RatingsTag) _jspx_tagPool_liferay$1ui_ratings_classPK_className_nobody.get(com.liferay.taglib.ui.RatingsTag.class);
        _jspx_th_liferay$1ui_ratings_0.setPageContext(_jspx_page_context);
        _jspx_th_liferay$1ui_ratings_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_10);
        _jspx_th_liferay$1ui_ratings_0.setClassName( assetEntry.getClassName() );
        _jspx_th_liferay$1ui_ratings_0.setClassPK( assetEntry.getClassPK() );
        int _jspx_eval_liferay$1ui_ratings_0 = _jspx_th_liferay$1ui_ratings_0.doStartTag();
        if (_jspx_th_liferay$1ui_ratings_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_liferay$1ui_ratings_classPK_className_nobody.reuse(_jspx_th_liferay$1ui_ratings_0);
          return;
        }
        _jspx_tagPool_liferay$1ui_ratings_classPK_className_nobody.reuse(_jspx_th_liferay$1ui_ratings_0);
        out.write("\n\t\t\t</div>\n\t\t");
      }
      if (_jspx_th_c_if_10.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_10);
        return;
      }
      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_10);
      out.write("\n\n\t\t");
      //  c:if
      com.liferay.taglib.core.IfTag _jspx_th_c_if_11 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
      _jspx_th_c_if_11.setPageContext(_jspx_page_context);
      _jspx_th_c_if_11.setParent(null);
      _jspx_th_c_if_11.setTest( assetPublisherDisplayContext.isShowContextLink(assetRenderer.getGroupId(), assetRendererFactory.getPortletId()) && !print && assetEntry.isVisible() );
      int _jspx_eval_c_if_11 = _jspx_th_c_if_11.doStartTag();
      if (_jspx_eval_c_if_11 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        out.write("\n\t\t\t<div class=\"asset-more\">\n\t\t\t\t<a href=\"");
        out.print( assetRenderer.getURLViewInContext(liferayPortletRequest, liferayPortletResponse, HttpUtil.setParameter(viewFullContentURL.toString(), "redirect", currentURL)) );
        out.write('"');
        out.write('>');
        //  liferay-ui:message
        com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_0 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
        _jspx_th_liferay$1ui_message_0.setPageContext(_jspx_page_context);
        _jspx_th_liferay$1ui_message_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_11);
        _jspx_th_liferay$1ui_message_0.setKey( assetRenderer.getViewInContextMessage() );
        int _jspx_eval_liferay$1ui_message_0 = _jspx_th_liferay$1ui_message_0.doStartTag();
        if (_jspx_th_liferay$1ui_message_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_0);
          return;
        }
        _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_0);
        out.write(" &raquo;</a>\n\t\t\t</div>\n\t\t");
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
      _jspx_th_c_if_12.setParent(null);
      _jspx_th_c_if_12.setTest( assetPublisherDisplayContext.isEnableRelatedAssets() );
      int _jspx_eval_c_if_12 = _jspx_th_c_if_12.doStartTag();
      if (_jspx_eval_c_if_12 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        out.write("\n\n\t\t\t");

			PortletURL assetLingsURL = renderResponse.createRenderURL();

			assetLingsURL.setParameter("mvcPath", "/view_content.jsp");

			if (print) {
				assetLingsURL.setParameter("viewMode", Constants.PRINT);
			}
			
        out.write("\n\n\t\t\t");
        //  liferay-asset:asset-links
        com.liferay.asset.taglib.servlet.taglib.AssetLinksTag _jspx_th_liferay$1asset_asset$1links_0 = (com.liferay.asset.taglib.servlet.taglib.AssetLinksTag) _jspx_tagPool_liferay$1asset_asset$1links_viewInContext_portletURL_assetEntryId_nobody.get(com.liferay.asset.taglib.servlet.taglib.AssetLinksTag.class);
        _jspx_th_liferay$1asset_asset$1links_0.setPageContext(_jspx_page_context);
        _jspx_th_liferay$1asset_asset$1links_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_12);
        _jspx_th_liferay$1asset_asset$1links_0.setAssetEntryId( assetEntry.getEntryId() );
        _jspx_th_liferay$1asset_asset$1links_0.setPortletURL( assetLingsURL );
        _jspx_th_liferay$1asset_asset$1links_0.setViewInContext( viewInContext );
        int _jspx_eval_liferay$1asset_asset$1links_0 = _jspx_th_liferay$1asset_asset$1links_0.doStartTag();
        if (_jspx_th_liferay$1asset_asset$1links_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_liferay$1asset_asset$1links_viewInContext_portletURL_assetEntryId_nobody.reuse(_jspx_th_liferay$1asset_asset$1links_0);
          return;
        }
        _jspx_tagPool_liferay$1asset_asset$1links_viewInContext_portletURL_assetEntryId_nobody.reuse(_jspx_th_liferay$1asset_asset$1links_0);
        out.write("\n\t\t");
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
      _jspx_th_c_if_13.setParent(null);
      _jspx_th_c_if_13.setTest( assetPublisherDisplayContext.isEnableComments() && assetRenderer.isCommentable() );
      int _jspx_eval_c_if_13 = _jspx_th_c_if_13.doStartTag();
      if (_jspx_eval_c_if_13 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        out.write("\n\t\t\t<div class=\"col-md-12\">\n\t\t\t\t");
        //  liferay-comment:discussion
        com.liferay.comment.taglib.servlet.taglib.DiscussionTag _jspx_th_liferay$1comment_discussion_0 = (com.liferay.comment.taglib.servlet.taglib.DiscussionTag) _jspx_tagPool_liferay$1comment_discussion_userId_redirect_ratingsEnabled_formName_classPK_className_nobody.get(com.liferay.comment.taglib.servlet.taglib.DiscussionTag.class);
        _jspx_th_liferay$1comment_discussion_0.setPageContext(_jspx_page_context);
        _jspx_th_liferay$1comment_discussion_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_13);
        _jspx_th_liferay$1comment_discussion_0.setClassName( assetEntry.getClassName() );
        _jspx_th_liferay$1comment_discussion_0.setClassPK( assetEntry.getClassPK() );
        _jspx_th_liferay$1comment_discussion_0.setFormName( "fm" + assetEntry.getClassPK() );
        _jspx_th_liferay$1comment_discussion_0.setRatingsEnabled( assetPublisherDisplayContext.isEnableCommentRatings() );
        _jspx_th_liferay$1comment_discussion_0.setRedirect( currentURL );
        _jspx_th_liferay$1comment_discussion_0.setUserId( assetRenderer.getUserId() );
        int _jspx_eval_liferay$1comment_discussion_0 = _jspx_th_liferay$1comment_discussion_0.doStartTag();
        if (_jspx_th_liferay$1comment_discussion_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_liferay$1comment_discussion_userId_redirect_ratingsEnabled_formName_classPK_className_nobody.reuse(_jspx_th_liferay$1comment_discussion_0);
          return;
        }
        _jspx_tagPool_liferay$1comment_discussion_userId_redirect_ratingsEnabled_formName_classPK_className_nobody.reuse(_jspx_th_liferay$1comment_discussion_0);
        out.write("\n\t\t\t</div>\n\t\t");
      }
      if (_jspx_th_c_if_13.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_13);
        return;
      }
      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_13);
      out.write("\n\t</div>\n\n\t");
      //  liferay-asset:asset-metadata
      com.liferay.asset.taglib.servlet.taglib.AssetMetadataTag _jspx_th_liferay$1asset_asset$1metadata_0 = (com.liferay.asset.taglib.servlet.taglib.AssetMetadataTag) _jspx_tagPool_liferay$1asset_asset$1metadata_metadataFields_filterByMetadata_classPK_className_nobody.get(com.liferay.asset.taglib.servlet.taglib.AssetMetadataTag.class);
      _jspx_th_liferay$1asset_asset$1metadata_0.setPageContext(_jspx_page_context);
      _jspx_th_liferay$1asset_asset$1metadata_0.setParent(null);
      _jspx_th_liferay$1asset_asset$1metadata_0.setClassName( assetEntry.getClassName() );
      _jspx_th_liferay$1asset_asset$1metadata_0.setClassPK( assetEntry.getClassPK() );
      _jspx_th_liferay$1asset_asset$1metadata_0.setFilterByMetadata( true );
      _jspx_th_liferay$1asset_asset$1metadata_0.setMetadataFields( assetPublisherDisplayContext.getMetadataFields() );
      int _jspx_eval_liferay$1asset_asset$1metadata_0 = _jspx_th_liferay$1asset_asset$1metadata_0.doStartTag();
      if (_jspx_th_liferay$1asset_asset$1metadata_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_liferay$1asset_asset$1metadata_metadataFields_filterByMetadata_classPK_className_nobody.reuse(_jspx_th_liferay$1asset_asset$1metadata_0);
        return;
      }
      _jspx_tagPool_liferay$1asset_asset$1metadata_metadataFields_filterByMetadata_classPK_className_nobody.reuse(_jspx_th_liferay$1asset_asset$1metadata_0);
      out.write("\n</div>\n\n");
      //  c:if
      com.liferay.taglib.core.IfTag _jspx_th_c_if_14 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
      _jspx_th_c_if_14.setPageContext(_jspx_page_context);
      _jspx_th_c_if_14.setParent(null);
      _jspx_th_c_if_14.setTest( !assetPublisherDisplayContext.isShowAssetTitle() && ((assetEntryIndex + 1) < results.size()) );
      int _jspx_eval_c_if_14 = _jspx_th_c_if_14.doStartTag();
      if (_jspx_eval_c_if_14 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        out.write("\n\t<div class=\"separator\"><!-- --></div>\n");
      }
      if (_jspx_th_c_if_14.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_14);
        return;
      }
      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_14);
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
        out.write("\n\t\t\tprint();\n\t\t");
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

  private boolean _jspx_meth_portlet_namespace_0(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_0 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_0.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    int _jspx_eval_portlet_namespace_0 = _jspx_th_portlet_namespace_0.doStartTag();
    if (_jspx_th_portlet_namespace_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_0);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_0);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_1(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_1 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_1.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_1.setParent(null);
    int _jspx_eval_portlet_namespace_1 = _jspx_th_portlet_namespace_1.doStartTag();
    if (_jspx_th_portlet_namespace_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_1);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_1);
    return false;
  }
}
