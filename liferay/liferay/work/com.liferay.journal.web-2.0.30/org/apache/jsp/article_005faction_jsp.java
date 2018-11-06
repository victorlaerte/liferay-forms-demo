package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.liferay.asset.display.page.constants.AssetDisplayPageConstants;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.document.library.kernel.exception.DuplicateFileEntryException;
import com.liferay.document.library.kernel.exception.FileSizeException;
import com.liferay.document.library.kernel.exception.NoSuchFileEntryException;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.util.DLValidatorUtil;
import com.liferay.dynamic.data.mapping.exception.NoSuchStructureException;
import com.liferay.dynamic.data.mapping.exception.NoSuchTemplateException;
import com.liferay.dynamic.data.mapping.exception.StorageFieldRequiredException;
import com.liferay.dynamic.data.mapping.model.DDMForm;
import com.liferay.dynamic.data.mapping.model.DDMFormField;
import com.liferay.dynamic.data.mapping.model.DDMFormFieldOptions;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.model.DDMTemplate;
import com.liferay.dynamic.data.mapping.model.LocalizedValue;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalServiceUtil;
import com.liferay.dynamic.data.mapping.service.DDMTemplateLocalServiceUtil;
import com.liferay.dynamic.data.mapping.service.DDMTemplateServiceUtil;
import com.liferay.dynamic.data.mapping.util.DDMNavigationHelper;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.JSPDropdownItemList;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.JSPNavigationItemList;
import com.liferay.journal.configuration.JournalFileUploadsConfiguration;
import com.liferay.journal.configuration.JournalGroupServiceConfiguration;
import com.liferay.journal.constants.JournalConstants;
import com.liferay.journal.constants.JournalPortletKeys;
import com.liferay.journal.constants.JournalWebKeys;
import com.liferay.journal.exception.ArticleContentException;
import com.liferay.journal.exception.ArticleContentSizeException;
import com.liferay.journal.exception.ArticleDisplayDateException;
import com.liferay.journal.exception.ArticleExpirationDateException;
import com.liferay.journal.exception.ArticleFriendlyURLException;
import com.liferay.journal.exception.ArticleIdException;
import com.liferay.journal.exception.ArticleSmallImageNameException;
import com.liferay.journal.exception.ArticleSmallImageSizeException;
import com.liferay.journal.exception.ArticleTitleException;
import com.liferay.journal.exception.ArticleVersionException;
import com.liferay.journal.exception.DuplicateArticleIdException;
import com.liferay.journal.exception.DuplicateFeedIdException;
import com.liferay.journal.exception.DuplicateFolderNameException;
import com.liferay.journal.exception.FeedContentFieldException;
import com.liferay.journal.exception.FeedIdException;
import com.liferay.journal.exception.FeedNameException;
import com.liferay.journal.exception.FeedTargetLayoutFriendlyUrlException;
import com.liferay.journal.exception.FeedTargetPortletIdException;
import com.liferay.journal.exception.FolderNameException;
import com.liferay.journal.exception.InvalidDDMStructureException;
import com.liferay.journal.exception.MaxAddMenuFavItemsException;
import com.liferay.journal.exception.NoSuchArticleException;
import com.liferay.journal.exception.NoSuchFolderException;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.model.JournalArticleConstants;
import com.liferay.journal.model.JournalArticleDisplay;
import com.liferay.journal.model.JournalArticleLocalization;
import com.liferay.journal.model.JournalFeed;
import com.liferay.journal.model.JournalFeedConstants;
import com.liferay.journal.model.JournalFolder;
import com.liferay.journal.model.JournalFolderConstants;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.journal.service.JournalArticleServiceUtil;
import com.liferay.journal.service.JournalFolderLocalServiceUtil;
import com.liferay.journal.service.JournalFolderServiceUtil;
import com.liferay.journal.util.JournalContent;
import com.liferay.journal.util.comparator.ArticleVersionComparator;
import com.liferay.journal.web.asset.JournalArticleAssetRenderer;
import com.liferay.journal.web.configuration.JournalWebConfiguration;
import com.liferay.journal.web.internal.dao.search.JournalResultRowSplitter;
import com.liferay.journal.web.internal.display.context.EditArticleDisplayPageDisplayContext;
import com.liferay.journal.web.internal.display.context.JournalDisplayContext;
import com.liferay.journal.web.internal.display.context.JournalFeedsDisplayContext;
import com.liferay.journal.web.internal.display.context.JournalHistoryDisplayContext;
import com.liferay.journal.web.internal.display.context.JournalMoveEntriesDisplayContext;
import com.liferay.journal.web.internal.display.context.JournalSelectDDMStructureDisplayContext;
import com.liferay.journal.web.internal.display.context.JournalSelectDDMTemplateDisplayContext;
import com.liferay.journal.web.internal.display.context.JournalViewMoreMenuItemsDisplayContext;
import com.liferay.journal.web.internal.display.context.util.JournalWebRequestHelper;
import com.liferay.journal.web.internal.portlet.JournalPortlet;
import com.liferay.journal.web.internal.portlet.action.ActionUtil;
import com.liferay.journal.web.internal.security.permission.resource.DDMStructurePermission;
import com.liferay.journal.web.internal.security.permission.resource.DDMTemplatePermission;
import com.liferay.journal.web.internal.security.permission.resource.JournalArticlePermission;
import com.liferay.journal.web.internal.security.permission.resource.JournalFeedPermission;
import com.liferay.journal.web.internal.security.permission.resource.JournalFolderPermission;
import com.liferay.journal.web.internal.security.permission.resource.JournalPermission;
import com.liferay.journal.web.internal.util.JournalHelperUtil;
import com.liferay.journal.web.util.JournalPortletUtil;
import com.liferay.journal.web.util.JournalUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.bean.BeanParamUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.LocaleException;
import com.liferay.portal.kernel.exception.NoSuchImageException;
import com.liferay.portal.kernel.exception.NoSuchLayoutException;
import com.liferay.portal.kernel.exception.NoSuchWorkflowDefinitionLinkException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.language.UnicodeLanguageUtil;
import com.liferay.portal.kernel.model.*;
import com.liferay.portal.kernel.model.ModelHintsUtil;
import com.liferay.portal.kernel.model.impl.*;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.portlet.PortletProvider;
import com.liferay.portal.kernel.portlet.PortletProviderUtil;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.*;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.servlet.taglib.ui.FormNavigatorConstants;
import com.liferay.portal.kernel.upload.LiferayFileItemException;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.TextFormatter;
import com.liferay.portal.kernel.util.UnicodeFormatter;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.workflow.WorkflowDefinition;
import com.liferay.portal.kernel.workflow.WorkflowDefinitionManagerUtil;
import com.liferay.portal.kernel.workflow.WorkflowEngineManagerUtil;
import com.liferay.portal.kernel.workflow.WorkflowHandlerRegistryUtil;
import com.liferay.portal.model.impl.*;
import com.liferay.portal.service.*;
import com.liferay.portal.upload.LiferayFileItem;
import com.liferay.rss.util.RSSUtil;
import com.liferay.taglib.search.ResultRow;
import com.liferay.taglib.util.CustomAttributesUtil;
import java.text.Format;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import javax.portlet.ActionRequest;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.ResourceURL;
import javax.portlet.WindowState;

public final class article_005faction_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(2);
    _jspx_dependants.add("/init.jsp");
    _jspx_dependants.add("/init-ext.jsp");
  }

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_actionURL_var_name;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_param_value_name_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_if_test;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1portlet_renderURL_windowState_var_plid;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_icon$1delete_url_message_confirmation_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_renderURL_var;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1security_permissionsURL_windowState_var_resourcePrimKey_modelResourceDescription_modelResource_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_otherwise;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_icon_useDialog_url_method_message_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1theme_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_icon$1menu_showWhenSingleIcon_message_markupView_icon_direction;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_choose;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1frontend_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_icon_url_message_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_icon_url_onClick_message_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_icon$1delete_url_trash_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1trash_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_when_test;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_portlet_actionURL_var_name = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_param_value_name_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_if_test = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1portlet_renderURL_windowState_var_plid = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_icon$1delete_url_message_confirmation_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_renderURL_var = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1security_permissionsURL_windowState_var_resourcePrimKey_modelResourceDescription_modelResource_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_otherwise = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_icon_useDialog_url_method_message_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1theme_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_icon$1menu_showWhenSingleIcon_message_markupView_icon_direction = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_choose = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1frontend_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_icon_url_message_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_icon_url_onClick_message_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_icon$1delete_url_trash_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1trash_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_when_test = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_portlet_actionURL_var_name.release();
    _jspx_tagPool_portlet_param_value_name_nobody.release();
    _jspx_tagPool_c_if_test.release();
    _jspx_tagPool_liferay$1portlet_renderURL_windowState_var_plid.release();
    _jspx_tagPool_liferay$1ui_icon$1delete_url_message_confirmation_nobody.release();
    _jspx_tagPool_portlet_renderURL_var.release();
    _jspx_tagPool_liferay$1security_permissionsURL_windowState_var_resourcePrimKey_modelResourceDescription_modelResource_nobody.release();
    _jspx_tagPool_c_otherwise.release();
    _jspx_tagPool_portlet_defineObjects_nobody.release();
    _jspx_tagPool_liferay$1ui_icon_useDialog_url_method_message_nobody.release();
    _jspx_tagPool_liferay$1theme_defineObjects_nobody.release();
    _jspx_tagPool_liferay$1ui_icon$1menu_showWhenSingleIcon_message_markupView_icon_direction.release();
    _jspx_tagPool_c_choose.release();
    _jspx_tagPool_liferay$1frontend_defineObjects_nobody.release();
    _jspx_tagPool_liferay$1ui_icon_url_message_nobody.release();
    _jspx_tagPool_liferay$1ui_icon_url_onClick_message_nobody.release();
    _jspx_tagPool_liferay$1ui_icon$1delete_url_trash_nobody.release();
    _jspx_tagPool_liferay$1trash_defineObjects_nobody.release();
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

JournalWebConfiguration journalWebConfiguration = (JournalWebConfiguration)request.getAttribute(JournalWebConfiguration.class.getName());

JournalDisplayContext journalDisplayContext = new JournalDisplayContext(request, liferayPortletRequest, liferayPortletResponse, portletPreferences, trashHelper);

JournalWebRequestHelper journalWebRequestHelper = new JournalWebRequestHelper(request);

JournalGroupServiceConfiguration journalGroupServiceConfiguration = journalWebRequestHelper.getJournalGroupServiceConfiguration();

Format dateFormatDateTime = FastDateFormatFactoryUtil.getDateTime(locale, timeZone);

      out.write('\n');
      out.write('\n');
      out.write('\n');
      out.write('\n');

String redirect = ParamUtil.getString(request, "redirect");

String referringPortletResource = ParamUtil.getString(request, "referringPortletResource");

ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

JournalArticle article = null;

if (row != null) {
	article = (JournalArticle)row.getObject();
}
else {
	article = (JournalArticle)request.getAttribute("info_panel.jsp-entry");
}

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
          _jspx_th_c_if_0.setTest( JournalArticlePermission.contains(permissionChecker, article, ActionKeys.UPDATE) );
          int _jspx_eval_c_if_0 = _jspx_th_c_if_0.doStartTag();
          if (_jspx_eval_c_if_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t");
            //  portlet:renderURL
            com.liferay.taglib.portlet.RenderURLTag _jspx_th_portlet_renderURL_0 = (com.liferay.taglib.portlet.RenderURLTag) _jspx_tagPool_portlet_renderURL_var.get(com.liferay.taglib.portlet.RenderURLTag.class);
            _jspx_th_portlet_renderURL_0.setPageContext(_jspx_page_context);
            _jspx_th_portlet_renderURL_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_0);
            _jspx_th_portlet_renderURL_0.setVar("editURL");
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
              out.write("\n\t\t\t");
              //  portlet:param
              com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_2 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
              _jspx_th_portlet_param_2.setPageContext(_jspx_page_context);
              _jspx_th_portlet_param_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_0);
              _jspx_th_portlet_param_2.setName("referringPortletResource");
              _jspx_th_portlet_param_2.setValue( referringPortletResource );
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
              _jspx_th_portlet_param_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_0);
              _jspx_th_portlet_param_3.setName("groupId");
              _jspx_th_portlet_param_3.setValue( String.valueOf(article.getGroupId()) );
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
              _jspx_th_portlet_param_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_0);
              _jspx_th_portlet_param_4.setName("folderId");
              _jspx_th_portlet_param_4.setValue( String.valueOf(article.getFolderId()) );
              int _jspx_eval_portlet_param_4 = _jspx_th_portlet_param_4.doStartTag();
              if (_jspx_th_portlet_param_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_4);
                return;
              }
              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_4);
              out.write("\n\t\t\t");
              //  portlet:param
              com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_5 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
              _jspx_th_portlet_param_5.setPageContext(_jspx_page_context);
              _jspx_th_portlet_param_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_0);
              _jspx_th_portlet_param_5.setName("articleId");
              _jspx_th_portlet_param_5.setValue( article.getArticleId() );
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
              _jspx_th_portlet_param_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_0);
              _jspx_th_portlet_param_6.setName("version");
              _jspx_th_portlet_param_6.setValue( String.valueOf(article.getVersion()) );
              int _jspx_eval_portlet_param_6 = _jspx_th_portlet_param_6.doStartTag();
              if (_jspx_th_portlet_param_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_6);
                return;
              }
              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_6);
              out.write("\n\t\t");
            }
            if (_jspx_th_portlet_renderURL_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_portlet_renderURL_var.reuse(_jspx_th_portlet_renderURL_0);
              return;
            }
            _jspx_tagPool_portlet_renderURL_var.reuse(_jspx_th_portlet_renderURL_0);
            java.lang.String editURL = null;
            editURL = (java.lang.String) _jspx_page_context.findAttribute("editURL");
            out.write("\n\n\t\t");
            //  liferay-ui:icon
            com.liferay.taglib.ui.IconTag _jspx_th_liferay$1ui_icon_0 = (com.liferay.taglib.ui.IconTag) _jspx_tagPool_liferay$1ui_icon_url_message_nobody.get(com.liferay.taglib.ui.IconTag.class);
            _jspx_th_liferay$1ui_icon_0.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1ui_icon_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_0);
            _jspx_th_liferay$1ui_icon_0.setMessage("edit");
            _jspx_th_liferay$1ui_icon_0.setUrl( editURL );
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
          _jspx_th_c_if_1.setTest( JournalArticlePermission.contains(permissionChecker, article, ActionKeys.UPDATE) );
          int _jspx_eval_c_if_1 = _jspx_th_c_if_1.doStartTag();
          if (_jspx_eval_c_if_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t");
            //  portlet:renderURL
            com.liferay.taglib.portlet.RenderURLTag _jspx_th_portlet_renderURL_1 = (com.liferay.taglib.portlet.RenderURLTag) _jspx_tagPool_portlet_renderURL_var.get(com.liferay.taglib.portlet.RenderURLTag.class);
            _jspx_th_portlet_renderURL_1.setPageContext(_jspx_page_context);
            _jspx_th_portlet_renderURL_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_1);
            _jspx_th_portlet_renderURL_1.setVar("moveURL");
            int _jspx_eval_portlet_renderURL_1 = _jspx_th_portlet_renderURL_1.doStartTag();
            if (_jspx_eval_portlet_renderURL_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\t\t\t");
              if (_jspx_meth_portlet_param_7((javax.servlet.jsp.tagext.JspTag) _jspx_th_portlet_renderURL_1, _jspx_page_context))
                return;
              out.write("\n\t\t\t");
              //  portlet:param
              com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_8 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
              _jspx_th_portlet_param_8.setPageContext(_jspx_page_context);
              _jspx_th_portlet_param_8.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_1);
              _jspx_th_portlet_param_8.setName("redirect");
              _jspx_th_portlet_param_8.setValue( currentURL );
              int _jspx_eval_portlet_param_8 = _jspx_th_portlet_param_8.doStartTag();
              if (_jspx_th_portlet_param_8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_8);
                return;
              }
              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_8);
              out.write("\n\t\t\t");
              //  portlet:param
              com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_9 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
              _jspx_th_portlet_param_9.setPageContext(_jspx_page_context);
              _jspx_th_portlet_param_9.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_1);
              _jspx_th_portlet_param_9.setName("referringPortletResource");
              _jspx_th_portlet_param_9.setValue( referringPortletResource );
              int _jspx_eval_portlet_param_9 = _jspx_th_portlet_param_9.doStartTag();
              if (_jspx_th_portlet_param_9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_9);
                return;
              }
              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_9);
              out.write("\n\t\t\t");
              //  portlet:param
              com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_10 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
              _jspx_th_portlet_param_10.setPageContext(_jspx_page_context);
              _jspx_th_portlet_param_10.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_1);
              _jspx_th_portlet_param_10.setName("rowIdsJournalArticle");
              _jspx_th_portlet_param_10.setValue( article.getArticleId() );
              int _jspx_eval_portlet_param_10 = _jspx_th_portlet_param_10.doStartTag();
              if (_jspx_th_portlet_param_10.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_10);
                return;
              }
              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_10);
              out.write("\n\t\t");
            }
            if (_jspx_th_portlet_renderURL_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_portlet_renderURL_var.reuse(_jspx_th_portlet_renderURL_1);
              return;
            }
            _jspx_tagPool_portlet_renderURL_var.reuse(_jspx_th_portlet_renderURL_1);
            java.lang.String moveURL = null;
            moveURL = (java.lang.String) _jspx_page_context.findAttribute("moveURL");
            out.write("\n\n\t\t");
            //  liferay-ui:icon
            com.liferay.taglib.ui.IconTag _jspx_th_liferay$1ui_icon_1 = (com.liferay.taglib.ui.IconTag) _jspx_tagPool_liferay$1ui_icon_url_message_nobody.get(com.liferay.taglib.ui.IconTag.class);
            _jspx_th_liferay$1ui_icon_1.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1ui_icon_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_1);
            _jspx_th_liferay$1ui_icon_1.setMessage("move");
            _jspx_th_liferay$1ui_icon_1.setUrl( moveURL );
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
          _jspx_th_c_if_2.setTest( JournalArticlePermission.contains(permissionChecker, article, ActionKeys.PERMISSIONS) );
          int _jspx_eval_c_if_2 = _jspx_th_c_if_2.doStartTag();
          if (_jspx_eval_c_if_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t");
            //  liferay-security:permissionsURL
            com.liferay.taglib.security.PermissionsURLTag _jspx_th_liferay$1security_permissionsURL_0 = (com.liferay.taglib.security.PermissionsURLTag) _jspx_tagPool_liferay$1security_permissionsURL_windowState_var_resourcePrimKey_modelResourceDescription_modelResource_nobody.get(com.liferay.taglib.security.PermissionsURLTag.class);
            _jspx_th_liferay$1security_permissionsURL_0.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1security_permissionsURL_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_2);
            _jspx_th_liferay$1security_permissionsURL_0.setModelResource( JournalArticle.class.getName() );
            _jspx_th_liferay$1security_permissionsURL_0.setModelResourceDescription( HtmlUtil.escape(article.getTitle(locale)) );
            _jspx_th_liferay$1security_permissionsURL_0.setResourcePrimKey( String.valueOf(article.getResourcePrimKey()) );
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
          _jspx_th_c_if_3.setTest( JournalArticlePermission.contains(permissionChecker, article, ActionKeys.VIEW) );
          int _jspx_eval_c_if_3 = _jspx_th_c_if_3.doStartTag();
          if (_jspx_eval_c_if_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t");
            //  liferay-portlet:renderURL
            com.liferay.taglib.portlet.RenderURLTag _jspx_th_liferay$1portlet_renderURL_0 = (com.liferay.taglib.portlet.RenderURLTag) _jspx_tagPool_liferay$1portlet_renderURL_windowState_var_plid.get(com.liferay.taglib.portlet.RenderURLTag.class);
            _jspx_th_liferay$1portlet_renderURL_0.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1portlet_renderURL_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_3);
            _jspx_th_liferay$1portlet_renderURL_0.setPlid( JournalUtil.getPreviewPlid(article, themeDisplay) );
            _jspx_th_liferay$1portlet_renderURL_0.setVar("previewArticleContentURL");
            _jspx_th_liferay$1portlet_renderURL_0.setWindowState( LiferayWindowState.POP_UP.toString() );
            int _jspx_eval_liferay$1portlet_renderURL_0 = _jspx_th_liferay$1portlet_renderURL_0.doStartTag();
            if (_jspx_eval_liferay$1portlet_renderURL_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\t\t\t");
              if (_jspx_meth_portlet_param_11((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1portlet_renderURL_0, _jspx_page_context))
                return;
              out.write("\n\t\t\t");
              //  portlet:param
              com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_12 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
              _jspx_th_portlet_param_12.setPageContext(_jspx_page_context);
              _jspx_th_portlet_param_12.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_0);
              _jspx_th_portlet_param_12.setName("groupId");
              _jspx_th_portlet_param_12.setValue( String.valueOf(article.getGroupId()) );
              int _jspx_eval_portlet_param_12 = _jspx_th_portlet_param_12.doStartTag();
              if (_jspx_th_portlet_param_12.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_12);
                return;
              }
              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_12);
              out.write("\n\t\t\t");
              //  portlet:param
              com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_13 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
              _jspx_th_portlet_param_13.setPageContext(_jspx_page_context);
              _jspx_th_portlet_param_13.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_0);
              _jspx_th_portlet_param_13.setName("articleId");
              _jspx_th_portlet_param_13.setValue( article.getArticleId() );
              int _jspx_eval_portlet_param_13 = _jspx_th_portlet_param_13.doStartTag();
              if (_jspx_th_portlet_param_13.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_13);
                return;
              }
              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_13);
              out.write("\n\t\t\t");
              //  portlet:param
              com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_14 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
              _jspx_th_portlet_param_14.setPageContext(_jspx_page_context);
              _jspx_th_portlet_param_14.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_0);
              _jspx_th_portlet_param_14.setName("version");
              _jspx_th_portlet_param_14.setValue( String.valueOf(article.getVersion()) );
              int _jspx_eval_portlet_param_14 = _jspx_th_portlet_param_14.doStartTag();
              if (_jspx_th_portlet_param_14.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_14);
                return;
              }
              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_14);
              out.write("\n\t\t");
            }
            if (_jspx_th_liferay$1portlet_renderURL_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_liferay$1portlet_renderURL_windowState_var_plid.reuse(_jspx_th_liferay$1portlet_renderURL_0);
              return;
            }
            _jspx_tagPool_liferay$1portlet_renderURL_windowState_var_plid.reuse(_jspx_th_liferay$1portlet_renderURL_0);
            java.lang.String previewArticleContentURL = null;
            previewArticleContentURL = (java.lang.String) _jspx_page_context.findAttribute("previewArticleContentURL");
            out.write("\n\n\t\t");

		String taglibOnClick = "Liferay.fire('previewArticle', {title: '" + HtmlUtil.escapeJS(article.getTitle(locale)) + "', uri: '" + HtmlUtil.escapeJS(previewArticleContentURL.toString()) + "'});";
		
            out.write("\n\n\t\t");
            //  liferay-ui:icon
            com.liferay.taglib.ui.IconTag _jspx_th_liferay$1ui_icon_3 = (com.liferay.taglib.ui.IconTag) _jspx_tagPool_liferay$1ui_icon_url_onClick_message_nobody.get(com.liferay.taglib.ui.IconTag.class);
            _jspx_th_liferay$1ui_icon_3.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1ui_icon_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_3);
            _jspx_th_liferay$1ui_icon_3.setMessage("preview");
            _jspx_th_liferay$1ui_icon_3.setOnClick( taglibOnClick );
            _jspx_th_liferay$1ui_icon_3.setUrl("javascript:;");
            int _jspx_eval_liferay$1ui_icon_3 = _jspx_th_liferay$1ui_icon_3.doStartTag();
            if (_jspx_th_liferay$1ui_icon_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_liferay$1ui_icon_url_onClick_message_nobody.reuse(_jspx_th_liferay$1ui_icon_3);
              return;
            }
            _jspx_tagPool_liferay$1ui_icon_url_onClick_message_nobody.reuse(_jspx_th_liferay$1ui_icon_3);
            out.write("\n\n\t\t");
            //  c:if
            com.liferay.taglib.core.IfTag _jspx_th_c_if_4 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
            _jspx_th_c_if_4.setPageContext(_jspx_page_context);
            _jspx_th_c_if_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_3);
            _jspx_th_c_if_4.setTest( JournalArticlePermission.contains(permissionChecker, article, ActionKeys.UPDATE) );
            int _jspx_eval_c_if_4 = _jspx_th_c_if_4.doStartTag();
            if (_jspx_eval_c_if_4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\t\t\t");
              //  portlet:renderURL
              com.liferay.taglib.portlet.RenderURLTag _jspx_th_portlet_renderURL_2 = (com.liferay.taglib.portlet.RenderURLTag) _jspx_tagPool_portlet_renderURL_var.get(com.liferay.taglib.portlet.RenderURLTag.class);
              _jspx_th_portlet_renderURL_2.setPageContext(_jspx_page_context);
              _jspx_th_portlet_renderURL_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_4);
              _jspx_th_portlet_renderURL_2.setVar("viewHistoryURL");
              int _jspx_eval_portlet_renderURL_2 = _jspx_th_portlet_renderURL_2.doStartTag();
              if (_jspx_eval_portlet_renderURL_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\t\t\t\t");
                if (_jspx_meth_portlet_param_15((javax.servlet.jsp.tagext.JspTag) _jspx_th_portlet_renderURL_2, _jspx_page_context))
                  return;
                out.write("\n\t\t\t\t");
                //  portlet:param
                com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_16 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                _jspx_th_portlet_param_16.setPageContext(_jspx_page_context);
                _jspx_th_portlet_param_16.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_2);
                _jspx_th_portlet_param_16.setName("redirect");
                _jspx_th_portlet_param_16.setValue( redirect );
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
                _jspx_th_portlet_param_17.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_2);
                _jspx_th_portlet_param_17.setName("backURL");
                _jspx_th_portlet_param_17.setValue( currentURL );
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
                _jspx_th_portlet_param_18.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_2);
                _jspx_th_portlet_param_18.setName("referringPortletResource");
                _jspx_th_portlet_param_18.setValue( referringPortletResource );
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
                _jspx_th_portlet_param_19.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_2);
                _jspx_th_portlet_param_19.setName("articleId");
                _jspx_th_portlet_param_19.setValue( article.getArticleId() );
                int _jspx_eval_portlet_param_19 = _jspx_th_portlet_param_19.doStartTag();
                if (_jspx_th_portlet_param_19.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_19);
                  return;
                }
                _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_19);
                out.write("\n\t\t\t");
              }
              if (_jspx_th_portlet_renderURL_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_portlet_renderURL_var.reuse(_jspx_th_portlet_renderURL_2);
                return;
              }
              _jspx_tagPool_portlet_renderURL_var.reuse(_jspx_th_portlet_renderURL_2);
              java.lang.String viewHistoryURL = null;
              viewHistoryURL = (java.lang.String) _jspx_page_context.findAttribute("viewHistoryURL");
              out.write("\n\n\t\t\t");
              //  liferay-ui:icon
              com.liferay.taglib.ui.IconTag _jspx_th_liferay$1ui_icon_4 = (com.liferay.taglib.ui.IconTag) _jspx_tagPool_liferay$1ui_icon_url_message_nobody.get(com.liferay.taglib.ui.IconTag.class);
              _jspx_th_liferay$1ui_icon_4.setPageContext(_jspx_page_context);
              _jspx_th_liferay$1ui_icon_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_4);
              _jspx_th_liferay$1ui_icon_4.setMessage("view-history");
              _jspx_th_liferay$1ui_icon_4.setUrl( viewHistoryURL.toString() );
              int _jspx_eval_liferay$1ui_icon_4 = _jspx_th_liferay$1ui_icon_4.doStartTag();
              if (_jspx_th_liferay$1ui_icon_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_liferay$1ui_icon_url_message_nobody.reuse(_jspx_th_liferay$1ui_icon_4);
                return;
              }
              _jspx_tagPool_liferay$1ui_icon_url_message_nobody.reuse(_jspx_th_liferay$1ui_icon_4);
              out.write("\n\t\t");
            }
            if (_jspx_th_c_if_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_4);
              return;
            }
            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_4);
            out.write("\n\n\t\t");
            //  c:if
            com.liferay.taglib.core.IfTag _jspx_th_c_if_5 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
            _jspx_th_c_if_5.setPageContext(_jspx_page_context);
            _jspx_th_c_if_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_3);
            _jspx_th_c_if_5.setTest( JournalArticlePermission.contains(permissionChecker, article, ActionKeys.SUBSCRIBE) );
            int _jspx_eval_c_if_5 = _jspx_th_c_if_5.doStartTag();
            if (_jspx_eval_c_if_5 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\t\t\t");
              //  c:choose
              com.liferay.taglib.core.ChooseTag _jspx_th_c_choose_0 = (com.liferay.taglib.core.ChooseTag) _jspx_tagPool_c_choose.get(com.liferay.taglib.core.ChooseTag.class);
              _jspx_th_c_choose_0.setPageContext(_jspx_page_context);
              _jspx_th_c_choose_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_5);
              int _jspx_eval_c_choose_0 = _jspx_th_c_choose_0.doStartTag();
              if (_jspx_eval_c_choose_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\t\t\t\t");
                //  c:when
                com.liferay.taglib.core.WhenTag _jspx_th_c_when_0 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
                _jspx_th_c_when_0.setPageContext(_jspx_page_context);
                _jspx_th_c_when_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_0);
                _jspx_th_c_when_0.setTest( JournalUtil.isSubscribedToArticle(article.getCompanyId(), scopeGroupId, themeDisplay.getUserId(), article.getResourcePrimKey()) );
                int _jspx_eval_c_when_0 = _jspx_th_c_when_0.doStartTag();
                if (_jspx_eval_c_when_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  out.write("\n\t\t\t\t\t");
                  //  portlet:actionURL
                  com.liferay.taglib.portlet.ActionURLTag _jspx_th_portlet_actionURL_0 = (com.liferay.taglib.portlet.ActionURLTag) _jspx_tagPool_portlet_actionURL_var_name.get(com.liferay.taglib.portlet.ActionURLTag.class);
                  _jspx_th_portlet_actionURL_0.setPageContext(_jspx_page_context);
                  _jspx_th_portlet_actionURL_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_0);
                  _jspx_th_portlet_actionURL_0.setName("unsubscribeArticle");
                  _jspx_th_portlet_actionURL_0.setVar("subscribeArticleURL");
                  int _jspx_eval_portlet_actionURL_0 = _jspx_th_portlet_actionURL_0.doStartTag();
                  if (_jspx_eval_portlet_actionURL_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    out.write("\n\t\t\t\t\t\t");
                    //  portlet:param
                    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_20 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                    _jspx_th_portlet_param_20.setPageContext(_jspx_page_context);
                    _jspx_th_portlet_param_20.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_actionURL_0);
                    _jspx_th_portlet_param_20.setName("redirect");
                    _jspx_th_portlet_param_20.setValue( currentURL );
                    int _jspx_eval_portlet_param_20 = _jspx_th_portlet_param_20.doStartTag();
                    if (_jspx_th_portlet_param_20.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_20);
                      return;
                    }
                    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_20);
                    out.write("\n\t\t\t\t\t\t");
                    //  portlet:param
                    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_21 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                    _jspx_th_portlet_param_21.setPageContext(_jspx_page_context);
                    _jspx_th_portlet_param_21.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_actionURL_0);
                    _jspx_th_portlet_param_21.setName("articleId");
                    _jspx_th_portlet_param_21.setValue( String.valueOf(article.getResourcePrimKey()) );
                    int _jspx_eval_portlet_param_21 = _jspx_th_portlet_param_21.doStartTag();
                    if (_jspx_th_portlet_param_21.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_21);
                      return;
                    }
                    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_21);
                    out.write("\n\t\t\t\t\t");
                  }
                  if (_jspx_th_portlet_actionURL_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_portlet_actionURL_var_name.reuse(_jspx_th_portlet_actionURL_0);
                    return;
                  }
                  _jspx_tagPool_portlet_actionURL_var_name.reuse(_jspx_th_portlet_actionURL_0);
                  java.lang.String subscribeArticleURL = null;
                  subscribeArticleURL = (java.lang.String) _jspx_page_context.findAttribute("subscribeArticleURL");
                  out.write("\n\n\t\t\t\t\t");
                  //  liferay-ui:icon
                  com.liferay.taglib.ui.IconTag _jspx_th_liferay$1ui_icon_5 = (com.liferay.taglib.ui.IconTag) _jspx_tagPool_liferay$1ui_icon_url_message_nobody.get(com.liferay.taglib.ui.IconTag.class);
                  _jspx_th_liferay$1ui_icon_5.setPageContext(_jspx_page_context);
                  _jspx_th_liferay$1ui_icon_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_0);
                  _jspx_th_liferay$1ui_icon_5.setMessage("unsubscribe");
                  _jspx_th_liferay$1ui_icon_5.setUrl( subscribeArticleURL.toString() );
                  int _jspx_eval_liferay$1ui_icon_5 = _jspx_th_liferay$1ui_icon_5.doStartTag();
                  if (_jspx_th_liferay$1ui_icon_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_liferay$1ui_icon_url_message_nobody.reuse(_jspx_th_liferay$1ui_icon_5);
                    return;
                  }
                  _jspx_tagPool_liferay$1ui_icon_url_message_nobody.reuse(_jspx_th_liferay$1ui_icon_5);
                  out.write("\n\t\t\t\t");
                }
                if (_jspx_th_c_when_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_0);
                  return;
                }
                _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_0);
                out.write("\n\t\t\t\t");
                //  c:otherwise
                com.liferay.taglib.core.OtherwiseTag _jspx_th_c_otherwise_0 = (com.liferay.taglib.core.OtherwiseTag) _jspx_tagPool_c_otherwise.get(com.liferay.taglib.core.OtherwiseTag.class);
                _jspx_th_c_otherwise_0.setPageContext(_jspx_page_context);
                _jspx_th_c_otherwise_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_0);
                int _jspx_eval_c_otherwise_0 = _jspx_th_c_otherwise_0.doStartTag();
                if (_jspx_eval_c_otherwise_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  out.write("\n\t\t\t\t\t");
                  //  portlet:actionURL
                  com.liferay.taglib.portlet.ActionURLTag _jspx_th_portlet_actionURL_1 = (com.liferay.taglib.portlet.ActionURLTag) _jspx_tagPool_portlet_actionURL_var_name.get(com.liferay.taglib.portlet.ActionURLTag.class);
                  _jspx_th_portlet_actionURL_1.setPageContext(_jspx_page_context);
                  _jspx_th_portlet_actionURL_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_0);
                  _jspx_th_portlet_actionURL_1.setName("subscribeArticle ");
                  _jspx_th_portlet_actionURL_1.setVar("subscribeArticleURL");
                  int _jspx_eval_portlet_actionURL_1 = _jspx_th_portlet_actionURL_1.doStartTag();
                  if (_jspx_eval_portlet_actionURL_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    out.write("\n\t\t\t\t\t\t");
                    //  portlet:param
                    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_22 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                    _jspx_th_portlet_param_22.setPageContext(_jspx_page_context);
                    _jspx_th_portlet_param_22.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_actionURL_1);
                    _jspx_th_portlet_param_22.setName("redirect");
                    _jspx_th_portlet_param_22.setValue( currentURL );
                    int _jspx_eval_portlet_param_22 = _jspx_th_portlet_param_22.doStartTag();
                    if (_jspx_th_portlet_param_22.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_22);
                      return;
                    }
                    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_22);
                    out.write("\n\t\t\t\t\t\t");
                    //  portlet:param
                    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_23 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                    _jspx_th_portlet_param_23.setPageContext(_jspx_page_context);
                    _jspx_th_portlet_param_23.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_actionURL_1);
                    _jspx_th_portlet_param_23.setName("articleId");
                    _jspx_th_portlet_param_23.setValue( String.valueOf(article.getResourcePrimKey()) );
                    int _jspx_eval_portlet_param_23 = _jspx_th_portlet_param_23.doStartTag();
                    if (_jspx_th_portlet_param_23.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_23);
                      return;
                    }
                    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_23);
                    out.write("\n\t\t\t\t\t");
                  }
                  if (_jspx_th_portlet_actionURL_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_portlet_actionURL_var_name.reuse(_jspx_th_portlet_actionURL_1);
                    return;
                  }
                  _jspx_tagPool_portlet_actionURL_var_name.reuse(_jspx_th_portlet_actionURL_1);
                  java.lang.String subscribeArticleURL = null;
                  subscribeArticleURL = (java.lang.String) _jspx_page_context.findAttribute("subscribeArticleURL");
                  out.write("\n\n\t\t\t\t\t");
                  //  liferay-ui:icon
                  com.liferay.taglib.ui.IconTag _jspx_th_liferay$1ui_icon_6 = (com.liferay.taglib.ui.IconTag) _jspx_tagPool_liferay$1ui_icon_url_message_nobody.get(com.liferay.taglib.ui.IconTag.class);
                  _jspx_th_liferay$1ui_icon_6.setPageContext(_jspx_page_context);
                  _jspx_th_liferay$1ui_icon_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_0);
                  _jspx_th_liferay$1ui_icon_6.setMessage("subscribe");
                  _jspx_th_liferay$1ui_icon_6.setUrl( subscribeArticleURL.toString() );
                  int _jspx_eval_liferay$1ui_icon_6 = _jspx_th_liferay$1ui_icon_6.doStartTag();
                  if (_jspx_th_liferay$1ui_icon_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_liferay$1ui_icon_url_message_nobody.reuse(_jspx_th_liferay$1ui_icon_6);
                    return;
                  }
                  _jspx_tagPool_liferay$1ui_icon_url_message_nobody.reuse(_jspx_th_liferay$1ui_icon_6);
                  out.write("\n\t\t\t\t");
                }
                if (_jspx_th_c_otherwise_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_0);
                  return;
                }
                _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_0);
                out.write("\n\t\t\t");
              }
              if (_jspx_th_c_choose_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_0);
                return;
              }
              _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_0);
              out.write("\n\t\t");
            }
            if (_jspx_th_c_if_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_5);
              return;
            }
            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_5);
            out.write("\n\n\t\t");
            //  c:if
            com.liferay.taglib.core.IfTag _jspx_th_c_if_6 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
            _jspx_th_c_if_6.setPageContext(_jspx_page_context);
            _jspx_th_c_if_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_3);
            _jspx_th_c_if_6.setTest( JournalFolderPermission.contains(permissionChecker, scopeGroupId, article.getFolderId(), ActionKeys.ADD_ARTICLE) );
            int _jspx_eval_c_if_6 = _jspx_th_c_if_6.doStartTag();
            if (_jspx_eval_c_if_6 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\t\t\t");
              //  c:choose
              com.liferay.taglib.core.ChooseTag _jspx_th_c_choose_1 = (com.liferay.taglib.core.ChooseTag) _jspx_tagPool_c_choose.get(com.liferay.taglib.core.ChooseTag.class);
              _jspx_th_c_choose_1.setPageContext(_jspx_page_context);
              _jspx_th_c_choose_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_6);
              int _jspx_eval_c_choose_1 = _jspx_th_c_choose_1.doStartTag();
              if (_jspx_eval_c_choose_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\t\t\t\t");
                //  c:when
                com.liferay.taglib.core.WhenTag _jspx_th_c_when_1 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
                _jspx_th_c_when_1.setPageContext(_jspx_page_context);
                _jspx_th_c_when_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_1);
                _jspx_th_c_when_1.setTest( journalWebConfiguration.journalArticleForceAutogenerateId() );
                int _jspx_eval_c_when_1 = _jspx_th_c_when_1.doStartTag();
                if (_jspx_eval_c_when_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  out.write("\n\t\t\t\t\t");
                  //  portlet:actionURL
                  com.liferay.taglib.portlet.ActionURLTag _jspx_th_portlet_actionURL_2 = (com.liferay.taglib.portlet.ActionURLTag) _jspx_tagPool_portlet_actionURL_var_name.get(com.liferay.taglib.portlet.ActionURLTag.class);
                  _jspx_th_portlet_actionURL_2.setPageContext(_jspx_page_context);
                  _jspx_th_portlet_actionURL_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_1);
                  _jspx_th_portlet_actionURL_2.setName("copyArticle");
                  _jspx_th_portlet_actionURL_2.setVar("copyArticleURL");
                  int _jspx_eval_portlet_actionURL_2 = _jspx_th_portlet_actionURL_2.doStartTag();
                  if (_jspx_eval_portlet_actionURL_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    out.write("\n\t\t\t\t\t\t");
                    //  portlet:param
                    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_24 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                    _jspx_th_portlet_param_24.setPageContext(_jspx_page_context);
                    _jspx_th_portlet_param_24.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_actionURL_2);
                    _jspx_th_portlet_param_24.setName("redirect");
                    _jspx_th_portlet_param_24.setValue( currentURL );
                    int _jspx_eval_portlet_param_24 = _jspx_th_portlet_param_24.doStartTag();
                    if (_jspx_th_portlet_param_24.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_24);
                      return;
                    }
                    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_24);
                    out.write("\n\t\t\t\t\t\t");
                    //  portlet:param
                    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_25 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                    _jspx_th_portlet_param_25.setPageContext(_jspx_page_context);
                    _jspx_th_portlet_param_25.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_actionURL_2);
                    _jspx_th_portlet_param_25.setName("groupId");
                    _jspx_th_portlet_param_25.setValue( String.valueOf(article.getGroupId()) );
                    int _jspx_eval_portlet_param_25 = _jspx_th_portlet_param_25.doStartTag();
                    if (_jspx_th_portlet_param_25.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_25);
                      return;
                    }
                    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_25);
                    out.write("\n\t\t\t\t\t\t");
                    //  portlet:param
                    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_26 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                    _jspx_th_portlet_param_26.setPageContext(_jspx_page_context);
                    _jspx_th_portlet_param_26.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_actionURL_2);
                    _jspx_th_portlet_param_26.setName("oldArticleId");
                    _jspx_th_portlet_param_26.setValue( article.getArticleId() );
                    int _jspx_eval_portlet_param_26 = _jspx_th_portlet_param_26.doStartTag();
                    if (_jspx_th_portlet_param_26.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_26);
                      return;
                    }
                    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_26);
                    out.write("\n\t\t\t\t\t\t");
                    //  portlet:param
                    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_27 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                    _jspx_th_portlet_param_27.setPageContext(_jspx_page_context);
                    _jspx_th_portlet_param_27.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_actionURL_2);
                    _jspx_th_portlet_param_27.setName("version");
                    _jspx_th_portlet_param_27.setValue( String.valueOf(article.getVersion()) );
                    int _jspx_eval_portlet_param_27 = _jspx_th_portlet_param_27.doStartTag();
                    if (_jspx_th_portlet_param_27.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_27);
                      return;
                    }
                    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_27);
                    out.write("\n\t\t\t\t\t\t");
                    //  portlet:param
                    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_28 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                    _jspx_th_portlet_param_28.setPageContext(_jspx_page_context);
                    _jspx_th_portlet_param_28.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_actionURL_2);
                    _jspx_th_portlet_param_28.setName("autoArticleId");
                    _jspx_th_portlet_param_28.setValue( Boolean.TRUE.toString() );
                    int _jspx_eval_portlet_param_28 = _jspx_th_portlet_param_28.doStartTag();
                    if (_jspx_th_portlet_param_28.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_28);
                      return;
                    }
                    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_28);
                    out.write("\n\t\t\t\t\t");
                  }
                  if (_jspx_th_portlet_actionURL_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_portlet_actionURL_var_name.reuse(_jspx_th_portlet_actionURL_2);
                    return;
                  }
                  _jspx_tagPool_portlet_actionURL_var_name.reuse(_jspx_th_portlet_actionURL_2);
                  java.lang.String copyArticleURL = null;
                  copyArticleURL = (java.lang.String) _jspx_page_context.findAttribute("copyArticleURL");
                  out.write("\n\n\t\t\t\t\t");
                  //  liferay-ui:icon
                  com.liferay.taglib.ui.IconTag _jspx_th_liferay$1ui_icon_7 = (com.liferay.taglib.ui.IconTag) _jspx_tagPool_liferay$1ui_icon_url_message_nobody.get(com.liferay.taglib.ui.IconTag.class);
                  _jspx_th_liferay$1ui_icon_7.setPageContext(_jspx_page_context);
                  _jspx_th_liferay$1ui_icon_7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_1);
                  _jspx_th_liferay$1ui_icon_7.setMessage("copy");
                  _jspx_th_liferay$1ui_icon_7.setUrl( copyArticleURL.toString() );
                  int _jspx_eval_liferay$1ui_icon_7 = _jspx_th_liferay$1ui_icon_7.doStartTag();
                  if (_jspx_th_liferay$1ui_icon_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_liferay$1ui_icon_url_message_nobody.reuse(_jspx_th_liferay$1ui_icon_7);
                    return;
                  }
                  _jspx_tagPool_liferay$1ui_icon_url_message_nobody.reuse(_jspx_th_liferay$1ui_icon_7);
                  out.write("\n\t\t\t\t");
                }
                if (_jspx_th_c_when_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_1);
                  return;
                }
                _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_1);
                out.write("\n\t\t\t\t");
                //  c:otherwise
                com.liferay.taglib.core.OtherwiseTag _jspx_th_c_otherwise_1 = (com.liferay.taglib.core.OtherwiseTag) _jspx_tagPool_c_otherwise.get(com.liferay.taglib.core.OtherwiseTag.class);
                _jspx_th_c_otherwise_1.setPageContext(_jspx_page_context);
                _jspx_th_c_otherwise_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_1);
                int _jspx_eval_c_otherwise_1 = _jspx_th_c_otherwise_1.doStartTag();
                if (_jspx_eval_c_otherwise_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  out.write("\n\t\t\t\t\t");
                  //  portlet:renderURL
                  com.liferay.taglib.portlet.RenderURLTag _jspx_th_portlet_renderURL_3 = (com.liferay.taglib.portlet.RenderURLTag) _jspx_tagPool_portlet_renderURL_var.get(com.liferay.taglib.portlet.RenderURLTag.class);
                  _jspx_th_portlet_renderURL_3.setPageContext(_jspx_page_context);
                  _jspx_th_portlet_renderURL_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_1);
                  _jspx_th_portlet_renderURL_3.setVar("copyURL");
                  int _jspx_eval_portlet_renderURL_3 = _jspx_th_portlet_renderURL_3.doStartTag();
                  if (_jspx_eval_portlet_renderURL_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    out.write("\n\t\t\t\t\t\t");
                    if (_jspx_meth_portlet_param_29((javax.servlet.jsp.tagext.JspTag) _jspx_th_portlet_renderURL_3, _jspx_page_context))
                      return;
                    out.write("\n\t\t\t\t\t\t");
                    //  portlet:param
                    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_30 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                    _jspx_th_portlet_param_30.setPageContext(_jspx_page_context);
                    _jspx_th_portlet_param_30.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_3);
                    _jspx_th_portlet_param_30.setName("redirect");
                    _jspx_th_portlet_param_30.setValue( currentURL );
                    int _jspx_eval_portlet_param_30 = _jspx_th_portlet_param_30.doStartTag();
                    if (_jspx_th_portlet_param_30.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_30);
                      return;
                    }
                    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_30);
                    out.write("\n\t\t\t\t\t\t");
                    //  portlet:param
                    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_31 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                    _jspx_th_portlet_param_31.setPageContext(_jspx_page_context);
                    _jspx_th_portlet_param_31.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_3);
                    _jspx_th_portlet_param_31.setName("groupId");
                    _jspx_th_portlet_param_31.setValue( String.valueOf(article.getGroupId()) );
                    int _jspx_eval_portlet_param_31 = _jspx_th_portlet_param_31.doStartTag();
                    if (_jspx_th_portlet_param_31.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_31);
                      return;
                    }
                    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_31);
                    out.write("\n\t\t\t\t\t\t");
                    //  portlet:param
                    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_32 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                    _jspx_th_portlet_param_32.setPageContext(_jspx_page_context);
                    _jspx_th_portlet_param_32.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_3);
                    _jspx_th_portlet_param_32.setName("oldArticleId");
                    _jspx_th_portlet_param_32.setValue( article.getArticleId() );
                    int _jspx_eval_portlet_param_32 = _jspx_th_portlet_param_32.doStartTag();
                    if (_jspx_th_portlet_param_32.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_32);
                      return;
                    }
                    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_32);
                    out.write("\n\t\t\t\t\t\t");
                    //  portlet:param
                    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_33 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                    _jspx_th_portlet_param_33.setPageContext(_jspx_page_context);
                    _jspx_th_portlet_param_33.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_3);
                    _jspx_th_portlet_param_33.setName("version");
                    _jspx_th_portlet_param_33.setValue( String.valueOf(article.getVersion()) );
                    int _jspx_eval_portlet_param_33 = _jspx_th_portlet_param_33.doStartTag();
                    if (_jspx_th_portlet_param_33.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_33);
                      return;
                    }
                    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_33);
                    out.write("\n\t\t\t\t\t");
                  }
                  if (_jspx_th_portlet_renderURL_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_portlet_renderURL_var.reuse(_jspx_th_portlet_renderURL_3);
                    return;
                  }
                  _jspx_tagPool_portlet_renderURL_var.reuse(_jspx_th_portlet_renderURL_3);
                  java.lang.String copyURL = null;
                  copyURL = (java.lang.String) _jspx_page_context.findAttribute("copyURL");
                  out.write("\n\n\t\t\t\t\t");
                  //  liferay-ui:icon
                  com.liferay.taglib.ui.IconTag _jspx_th_liferay$1ui_icon_8 = (com.liferay.taglib.ui.IconTag) _jspx_tagPool_liferay$1ui_icon_url_message_nobody.get(com.liferay.taglib.ui.IconTag.class);
                  _jspx_th_liferay$1ui_icon_8.setPageContext(_jspx_page_context);
                  _jspx_th_liferay$1ui_icon_8.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_1);
                  _jspx_th_liferay$1ui_icon_8.setMessage("copy");
                  _jspx_th_liferay$1ui_icon_8.setUrl( copyURL.toString() );
                  int _jspx_eval_liferay$1ui_icon_8 = _jspx_th_liferay$1ui_icon_8.doStartTag();
                  if (_jspx_th_liferay$1ui_icon_8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_liferay$1ui_icon_url_message_nobody.reuse(_jspx_th_liferay$1ui_icon_8);
                    return;
                  }
                  _jspx_tagPool_liferay$1ui_icon_url_message_nobody.reuse(_jspx_th_liferay$1ui_icon_8);
                  out.write("\n\t\t\t\t");
                }
                if (_jspx_th_c_otherwise_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_1);
                  return;
                }
                _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_1);
                out.write("\n\t\t\t");
              }
              if (_jspx_th_c_choose_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_1);
                return;
              }
              _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_1);
              out.write("\n\t\t");
            }
            if (_jspx_th_c_if_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_6);
              return;
            }
            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_6);
            out.write('\n');
            out.write('	');
          }
          if (_jspx_th_c_if_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_3);
            return;
          }
          _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_3);
          out.write("\n\n\t");
          //  c:if
          com.liferay.taglib.core.IfTag _jspx_th_c_if_7 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
          _jspx_th_c_if_7.setPageContext(_jspx_page_context);
          _jspx_th_c_if_7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_icon$1menu_0);
          _jspx_th_c_if_7.setTest( JournalArticlePermission.contains(permissionChecker, article, ActionKeys.EXPIRE) && article.hasApprovedVersion() );
          int _jspx_eval_c_if_7 = _jspx_th_c_if_7.doStartTag();
          if (_jspx_eval_c_if_7 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t");
            //  portlet:actionURL
            com.liferay.taglib.portlet.ActionURLTag _jspx_th_portlet_actionURL_3 = (com.liferay.taglib.portlet.ActionURLTag) _jspx_tagPool_portlet_actionURL_var_name.get(com.liferay.taglib.portlet.ActionURLTag.class);
            _jspx_th_portlet_actionURL_3.setPageContext(_jspx_page_context);
            _jspx_th_portlet_actionURL_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_7);
            _jspx_th_portlet_actionURL_3.setName("expireArticles");
            _jspx_th_portlet_actionURL_3.setVar("expireURL");
            int _jspx_eval_portlet_actionURL_3 = _jspx_th_portlet_actionURL_3.doStartTag();
            if (_jspx_eval_portlet_actionURL_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\t\t\t");
              //  portlet:param
              com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_34 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
              _jspx_th_portlet_param_34.setPageContext(_jspx_page_context);
              _jspx_th_portlet_param_34.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_actionURL_3);
              _jspx_th_portlet_param_34.setName("redirect");
              _jspx_th_portlet_param_34.setValue( currentURL );
              int _jspx_eval_portlet_param_34 = _jspx_th_portlet_param_34.doStartTag();
              if (_jspx_th_portlet_param_34.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_34);
                return;
              }
              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_34);
              out.write("\n\t\t\t");
              //  portlet:param
              com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_35 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
              _jspx_th_portlet_param_35.setPageContext(_jspx_page_context);
              _jspx_th_portlet_param_35.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_actionURL_3);
              _jspx_th_portlet_param_35.setName("groupId");
              _jspx_th_portlet_param_35.setValue( String.valueOf(article.getGroupId()) );
              int _jspx_eval_portlet_param_35 = _jspx_th_portlet_param_35.doStartTag();
              if (_jspx_th_portlet_param_35.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_35);
                return;
              }
              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_35);
              out.write("\n\t\t\t");
              //  portlet:param
              com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_36 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
              _jspx_th_portlet_param_36.setPageContext(_jspx_page_context);
              _jspx_th_portlet_param_36.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_actionURL_3);
              _jspx_th_portlet_param_36.setName("articleId");
              _jspx_th_portlet_param_36.setValue( article.getArticleId() );
              int _jspx_eval_portlet_param_36 = _jspx_th_portlet_param_36.doStartTag();
              if (_jspx_th_portlet_param_36.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_36);
                return;
              }
              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_36);
              out.write("\n\t\t");
            }
            if (_jspx_th_portlet_actionURL_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_portlet_actionURL_var_name.reuse(_jspx_th_portlet_actionURL_3);
              return;
            }
            _jspx_tagPool_portlet_actionURL_var_name.reuse(_jspx_th_portlet_actionURL_3);
            java.lang.String expireURL = null;
            expireURL = (java.lang.String) _jspx_page_context.findAttribute("expireURL");
            out.write("\n\n\t\t");
            //  liferay-ui:icon
            com.liferay.taglib.ui.IconTag _jspx_th_liferay$1ui_icon_9 = (com.liferay.taglib.ui.IconTag) _jspx_tagPool_liferay$1ui_icon_url_message_nobody.get(com.liferay.taglib.ui.IconTag.class);
            _jspx_th_liferay$1ui_icon_9.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1ui_icon_9.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_7);
            _jspx_th_liferay$1ui_icon_9.setMessage("expire");
            _jspx_th_liferay$1ui_icon_9.setUrl( expireURL );
            int _jspx_eval_liferay$1ui_icon_9 = _jspx_th_liferay$1ui_icon_9.doStartTag();
            if (_jspx_th_liferay$1ui_icon_9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_liferay$1ui_icon_url_message_nobody.reuse(_jspx_th_liferay$1ui_icon_9);
              return;
            }
            _jspx_tagPool_liferay$1ui_icon_url_message_nobody.reuse(_jspx_th_liferay$1ui_icon_9);
            out.write('\n');
            out.write('	');
          }
          if (_jspx_th_c_if_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_7);
            return;
          }
          _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_7);
          out.write("\n\n\t");
          //  c:if
          com.liferay.taglib.core.IfTag _jspx_th_c_if_8 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
          _jspx_th_c_if_8.setPageContext(_jspx_page_context);
          _jspx_th_c_if_8.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_icon$1menu_0);
          _jspx_th_c_if_8.setTest( JournalArticlePermission.contains(permissionChecker, article, ActionKeys.DELETE) );
          int _jspx_eval_c_if_8 = _jspx_th_c_if_8.doStartTag();
          if (_jspx_eval_c_if_8 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t");
            //  portlet:actionURL
            com.liferay.taglib.portlet.ActionURLTag _jspx_th_portlet_actionURL_4 = (com.liferay.taglib.portlet.ActionURLTag) _jspx_tagPool_portlet_actionURL_var_name.get(com.liferay.taglib.portlet.ActionURLTag.class);
            _jspx_th_portlet_actionURL_4.setPageContext(_jspx_page_context);
            _jspx_th_portlet_actionURL_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_8);
            _jspx_th_portlet_actionURL_4.setName( trashHelper.isTrashEnabled(scopeGroupId) ? "moveToTrash" : "deleteArticles" );
            _jspx_th_portlet_actionURL_4.setVar("deleteURL");
            int _jspx_eval_portlet_actionURL_4 = _jspx_th_portlet_actionURL_4.doStartTag();
            if (_jspx_eval_portlet_actionURL_4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\t\t\t");
              //  portlet:param
              com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_37 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
              _jspx_th_portlet_param_37.setPageContext(_jspx_page_context);
              _jspx_th_portlet_param_37.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_actionURL_4);
              _jspx_th_portlet_param_37.setName("redirect");
              _jspx_th_portlet_param_37.setValue( currentURL );
              int _jspx_eval_portlet_param_37 = _jspx_th_portlet_param_37.doStartTag();
              if (_jspx_th_portlet_param_37.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_37);
                return;
              }
              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_37);
              out.write("\n\t\t\t");
              //  portlet:param
              com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_38 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
              _jspx_th_portlet_param_38.setPageContext(_jspx_page_context);
              _jspx_th_portlet_param_38.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_actionURL_4);
              _jspx_th_portlet_param_38.setName("groupId");
              _jspx_th_portlet_param_38.setValue( String.valueOf(article.getGroupId()) );
              int _jspx_eval_portlet_param_38 = _jspx_th_portlet_param_38.doStartTag();
              if (_jspx_th_portlet_param_38.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_38);
                return;
              }
              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_38);
              out.write("\n\t\t\t");
              //  portlet:param
              com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_39 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
              _jspx_th_portlet_param_39.setPageContext(_jspx_page_context);
              _jspx_th_portlet_param_39.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_actionURL_4);
              _jspx_th_portlet_param_39.setName("articleId");
              _jspx_th_portlet_param_39.setValue( article.getArticleId() );
              int _jspx_eval_portlet_param_39 = _jspx_th_portlet_param_39.doStartTag();
              if (_jspx_th_portlet_param_39.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_39);
                return;
              }
              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_39);
              out.write("\n\t\t");
            }
            if (_jspx_th_portlet_actionURL_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_portlet_actionURL_var_name.reuse(_jspx_th_portlet_actionURL_4);
              return;
            }
            _jspx_tagPool_portlet_actionURL_var_name.reuse(_jspx_th_portlet_actionURL_4);
            java.lang.String deleteURL = null;
            deleteURL = (java.lang.String) _jspx_page_context.findAttribute("deleteURL");
            out.write("\n\n\t\t");
            //  liferay-ui:icon-delete
            com.liferay.taglib.ui.IconDeleteTag _jspx_th_liferay$1ui_icon$1delete_0 = (com.liferay.taglib.ui.IconDeleteTag) _jspx_tagPool_liferay$1ui_icon$1delete_url_trash_nobody.get(com.liferay.taglib.ui.IconDeleteTag.class);
            _jspx_th_liferay$1ui_icon$1delete_0.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1ui_icon$1delete_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_8);
            _jspx_th_liferay$1ui_icon$1delete_0.setTrash( trashHelper.isTrashEnabled(scopeGroupId) );
            _jspx_th_liferay$1ui_icon$1delete_0.setUrl( deleteURL );
            int _jspx_eval_liferay$1ui_icon$1delete_0 = _jspx_th_liferay$1ui_icon$1delete_0.doStartTag();
            if (_jspx_th_liferay$1ui_icon$1delete_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_liferay$1ui_icon$1delete_url_trash_nobody.reuse(_jspx_th_liferay$1ui_icon$1delete_0);
              return;
            }
            _jspx_tagPool_liferay$1ui_icon$1delete_url_trash_nobody.reuse(_jspx_th_liferay$1ui_icon$1delete_0);
            out.write('\n');
            out.write('	');
          }
          if (_jspx_th_c_if_8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_8);
            return;
          }
          _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_8);
          out.write("\n\n\t");

	Group group = themeDisplay.getScopeGroup();
	
          out.write("\n\n\t");
          //  c:if
          com.liferay.taglib.core.IfTag _jspx_th_c_if_9 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
          _jspx_th_c_if_9.setPageContext(_jspx_page_context);
          _jspx_th_c_if_9.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_icon$1menu_0);
          _jspx_th_c_if_9.setTest( journalDisplayContext.isShowPublishArticleAction(article) && !group.isLayout() );
          int _jspx_eval_c_if_9 = _jspx_th_c_if_9.doStartTag();
          if (_jspx_eval_c_if_9 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t");
            //  portlet:actionURL
            com.liferay.taglib.portlet.ActionURLTag _jspx_th_portlet_actionURL_5 = (com.liferay.taglib.portlet.ActionURLTag) _jspx_tagPool_portlet_actionURL_var_name.get(com.liferay.taglib.portlet.ActionURLTag.class);
            _jspx_th_portlet_actionURL_5.setPageContext(_jspx_page_context);
            _jspx_th_portlet_actionURL_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_9);
            _jspx_th_portlet_actionURL_5.setName("/journal/publish_article");
            _jspx_th_portlet_actionURL_5.setVar("publishArticleURL");
            int _jspx_eval_portlet_actionURL_5 = _jspx_th_portlet_actionURL_5.doStartTag();
            if (_jspx_eval_portlet_actionURL_5 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\t\t\t");
              //  portlet:param
              com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_40 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
              _jspx_th_portlet_param_40.setPageContext(_jspx_page_context);
              _jspx_th_portlet_param_40.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_actionURL_5);
              _jspx_th_portlet_param_40.setName("backURL");
              _jspx_th_portlet_param_40.setValue( currentURL );
              int _jspx_eval_portlet_param_40 = _jspx_th_portlet_param_40.doStartTag();
              if (_jspx_th_portlet_param_40.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_40);
                return;
              }
              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_40);
              out.write("\n\t\t\t");
              //  portlet:param
              com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_41 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
              _jspx_th_portlet_param_41.setPageContext(_jspx_page_context);
              _jspx_th_portlet_param_41.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_actionURL_5);
              _jspx_th_portlet_param_41.setName("groupId");
              _jspx_th_portlet_param_41.setValue( String.valueOf(article.getGroupId()) );
              int _jspx_eval_portlet_param_41 = _jspx_th_portlet_param_41.doStartTag();
              if (_jspx_th_portlet_param_41.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_41);
                return;
              }
              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_41);
              out.write("\n\t\t\t");
              //  portlet:param
              com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_42 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
              _jspx_th_portlet_param_42.setPageContext(_jspx_page_context);
              _jspx_th_portlet_param_42.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_actionURL_5);
              _jspx_th_portlet_param_42.setName("articleId");
              _jspx_th_portlet_param_42.setValue( article.getArticleId() );
              int _jspx_eval_portlet_param_42 = _jspx_th_portlet_param_42.doStartTag();
              if (_jspx_th_portlet_param_42.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_42);
                return;
              }
              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_42);
              out.write("\n\t\t");
            }
            if (_jspx_th_portlet_actionURL_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_portlet_actionURL_var_name.reuse(_jspx_th_portlet_actionURL_5);
              return;
            }
            _jspx_tagPool_portlet_actionURL_var_name.reuse(_jspx_th_portlet_actionURL_5);
            java.lang.String publishArticleURL = null;
            publishArticleURL = (java.lang.String) _jspx_page_context.findAttribute("publishArticleURL");
            out.write("\n\n\t\t");
            //  liferay-ui:icon-delete
            com.liferay.taglib.ui.IconDeleteTag _jspx_th_liferay$1ui_icon$1delete_1 = (com.liferay.taglib.ui.IconDeleteTag) _jspx_tagPool_liferay$1ui_icon$1delete_url_message_confirmation_nobody.get(com.liferay.taglib.ui.IconDeleteTag.class);
            _jspx_th_liferay$1ui_icon$1delete_1.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1ui_icon$1delete_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_9);
            _jspx_th_liferay$1ui_icon$1delete_1.setConfirmation("are-you-sure-you-want-to-publish-the-selected-web-content");
            _jspx_th_liferay$1ui_icon$1delete_1.setMessage("publish-to-live");
            _jspx_th_liferay$1ui_icon$1delete_1.setUrl( publishArticleURL );
            int _jspx_eval_liferay$1ui_icon$1delete_1 = _jspx_th_liferay$1ui_icon$1delete_1.doStartTag();
            if (_jspx_th_liferay$1ui_icon$1delete_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_liferay$1ui_icon$1delete_url_message_confirmation_nobody.reuse(_jspx_th_liferay$1ui_icon$1delete_1);
              return;
            }
            _jspx_tagPool_liferay$1ui_icon$1delete_url_message_confirmation_nobody.reuse(_jspx_th_liferay$1ui_icon$1delete_1);
            out.write('\n');
            out.write('	');
          }
          if (_jspx_th_c_if_9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_9);
            return;
          }
          _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_9);
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

  private boolean _jspx_meth_portlet_param_0(javax.servlet.jsp.tagext.JspTag _jspx_th_portlet_renderURL_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:param
    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_0 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
    _jspx_th_portlet_param_0.setPageContext(_jspx_page_context);
    _jspx_th_portlet_param_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_0);
    _jspx_th_portlet_param_0.setName("mvcPath");
    _jspx_th_portlet_param_0.setValue("/edit_article.jsp");
    int _jspx_eval_portlet_param_0 = _jspx_th_portlet_param_0.doStartTag();
    if (_jspx_th_portlet_param_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_0);
      return true;
    }
    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_0);
    return false;
  }

  private boolean _jspx_meth_portlet_param_7(javax.servlet.jsp.tagext.JspTag _jspx_th_portlet_renderURL_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:param
    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_7 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
    _jspx_th_portlet_param_7.setPageContext(_jspx_page_context);
    _jspx_th_portlet_param_7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_1);
    _jspx_th_portlet_param_7.setName("mvcPath");
    _jspx_th_portlet_param_7.setValue("/move_entries.jsp");
    int _jspx_eval_portlet_param_7 = _jspx_th_portlet_param_7.doStartTag();
    if (_jspx_th_portlet_param_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_7);
      return true;
    }
    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_7);
    return false;
  }

  private boolean _jspx_meth_portlet_param_11(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1portlet_renderURL_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:param
    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_11 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
    _jspx_th_portlet_param_11.setPageContext(_jspx_page_context);
    _jspx_th_portlet_param_11.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_0);
    _jspx_th_portlet_param_11.setName("mvcPath");
    _jspx_th_portlet_param_11.setValue("/preview_article_content.jsp");
    int _jspx_eval_portlet_param_11 = _jspx_th_portlet_param_11.doStartTag();
    if (_jspx_th_portlet_param_11.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_11);
      return true;
    }
    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_11);
    return false;
  }

  private boolean _jspx_meth_portlet_param_15(javax.servlet.jsp.tagext.JspTag _jspx_th_portlet_renderURL_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:param
    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_15 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
    _jspx_th_portlet_param_15.setPageContext(_jspx_page_context);
    _jspx_th_portlet_param_15.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_2);
    _jspx_th_portlet_param_15.setName("mvcPath");
    _jspx_th_portlet_param_15.setValue("/view_article_history.jsp");
    int _jspx_eval_portlet_param_15 = _jspx_th_portlet_param_15.doStartTag();
    if (_jspx_th_portlet_param_15.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_15);
      return true;
    }
    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_15);
    return false;
  }

  private boolean _jspx_meth_portlet_param_29(javax.servlet.jsp.tagext.JspTag _jspx_th_portlet_renderURL_3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:param
    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_29 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
    _jspx_th_portlet_param_29.setPageContext(_jspx_page_context);
    _jspx_th_portlet_param_29.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_3);
    _jspx_th_portlet_param_29.setName("mvcPath");
    _jspx_th_portlet_param_29.setValue("/copy_article.jsp");
    int _jspx_eval_portlet_param_29 = _jspx_th_portlet_param_29.doStartTag();
    if (_jspx_th_portlet_param_29.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_29);
      return true;
    }
    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_29);
    return false;
  }
}
