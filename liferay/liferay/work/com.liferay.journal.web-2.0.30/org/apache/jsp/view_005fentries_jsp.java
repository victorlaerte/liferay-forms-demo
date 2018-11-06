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

public final class view_005fentries_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(3);
    _jspx_dependants.add("/init.jsp");
    _jspx_dependants.add("/init-ext.jsp");
    _jspx_dependants.add("/article_vertical_card.jspf");
  }

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_message_key_arguments_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_a_href;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1frontend_horizontal$1card_url_text_rowChecker_resultRow_actionJspServletContext_actionJsp;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1iterator_searchContainer_resultRowSplitter_markupView_displayStyle_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_if_test;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container$1column$1jsp_path_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container$1row_modelVar_cssClass_className;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1frontend_icon$1vertical$1card_url_title_rowChecker_resultRow_icon_actionJspServletContext_actionJsp;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_user$1portrait_userId_cssClass_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1frontend_horizontal$1card$1col;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_choose;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1frontend_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_cssClass_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container_searchContainer_id_emptyResultsMessage;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_user$1portrait_userId_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container$1column$1icon_toggleRowChecker_icon_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_when_test;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_href_cssClass_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1frontend_vertical$1card$1header;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container$1column$1jsp_path_name_href_cssClass_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container$1column$1status_name_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container$1column$1text_colspan;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_otherwise;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1frontend_vertical$1card$1sticker$1bottom;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1theme_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1frontend_horizontal$1card$1icon_icon_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container$1column$1text;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container$1column$1date_value_name_cssClass_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_workflow$1status_status_showLabel_showIcon_markupView_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1frontend_vertical$1card_url_title_rowChecker_resultRow_imageUrl_actionJspServletContext_actionJsp;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1trash_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1frontend_vertical$1card$1footer;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_liferay$1ui_message_key_arguments_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_a_href = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1frontend_horizontal$1card_url_text_rowChecker_resultRow_actionJspServletContext_actionJsp = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1iterator_searchContainer_resultRowSplitter_markupView_displayStyle_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_if_test = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container$1column$1jsp_path_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container$1row_modelVar_cssClass_className = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1frontend_icon$1vertical$1card_url_title_rowChecker_resultRow_icon_actionJspServletContext_actionJsp = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_user$1portrait_userId_cssClass_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1frontend_horizontal$1card$1col = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_choose = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1frontend_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_cssClass_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container_searchContainer_id_emptyResultsMessage = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_user$1portrait_userId_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container$1column$1icon_toggleRowChecker_icon_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_when_test = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_href_cssClass_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1frontend_vertical$1card$1header = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container$1column$1jsp_path_name_href_cssClass_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container$1column$1status_name_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_colspan = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_otherwise = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1frontend_vertical$1card$1sticker$1bottom = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1theme_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1frontend_horizontal$1card$1icon_icon_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container$1column$1date_value_name_cssClass_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_workflow$1status_status_showLabel_showIcon_markupView_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1frontend_vertical$1card_url_title_rowChecker_resultRow_imageUrl_actionJspServletContext_actionJsp = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1trash_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1frontend_vertical$1card$1footer = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_liferay$1ui_message_key_arguments_nobody.release();
    _jspx_tagPool_aui_a_href.release();
    _jspx_tagPool_liferay$1frontend_horizontal$1card_url_text_rowChecker_resultRow_actionJspServletContext_actionJsp.release();
    _jspx_tagPool_liferay$1ui_search$1iterator_searchContainer_resultRowSplitter_markupView_displayStyle_nobody.release();
    _jspx_tagPool_c_if_test.release();
    _jspx_tagPool_liferay$1ui_search$1container$1column$1jsp_path_nobody.release();
    _jspx_tagPool_liferay$1ui_search$1container$1row_modelVar_cssClass_className.release();
    _jspx_tagPool_liferay$1frontend_icon$1vertical$1card_url_title_rowChecker_resultRow_icon_actionJspServletContext_actionJsp.release();
    _jspx_tagPool_liferay$1ui_user$1portrait_userId_cssClass_nobody.release();
    _jspx_tagPool_liferay$1frontend_horizontal$1card$1col.release();
    _jspx_tagPool_c_choose.release();
    _jspx_tagPool_liferay$1frontend_defineObjects_nobody.release();
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_cssClass_nobody.release();
    _jspx_tagPool_liferay$1ui_search$1container_searchContainer_id_emptyResultsMessage.release();
    _jspx_tagPool_liferay$1ui_user$1portrait_userId_nobody.release();
    _jspx_tagPool_liferay$1ui_search$1container$1column$1icon_toggleRowChecker_icon_nobody.release();
    _jspx_tagPool_c_when_test.release();
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_href_cssClass_nobody.release();
    _jspx_tagPool_liferay$1frontend_vertical$1card$1header.release();
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_nobody.release();
    _jspx_tagPool_liferay$1ui_search$1container$1column$1jsp_path_name_href_cssClass_nobody.release();
    _jspx_tagPool_liferay$1ui_search$1container$1column$1status_name_nobody.release();
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_colspan.release();
    _jspx_tagPool_c_otherwise.release();
    _jspx_tagPool_liferay$1frontend_vertical$1card$1sticker$1bottom.release();
    _jspx_tagPool_portlet_defineObjects_nobody.release();
    _jspx_tagPool_liferay$1theme_defineObjects_nobody.release();
    _jspx_tagPool_liferay$1frontend_horizontal$1card$1icon_icon_nobody.release();
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text.release();
    _jspx_tagPool_liferay$1ui_search$1container$1column$1date_value_name_cssClass_nobody.release();
    _jspx_tagPool_aui_workflow$1status_status_showLabel_showIcon_markupView_nobody.release();
    _jspx_tagPool_liferay$1frontend_vertical$1card_url_title_rowChecker_resultRow_imageUrl_actionJspServletContext_actionJsp.release();
    _jspx_tagPool_liferay$1trash_defineObjects_nobody.release();
    _jspx_tagPool_liferay$1frontend_vertical$1card$1footer.release();
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

String referringPortletResource = ParamUtil.getString(request, "referringPortletResource");

SearchContainer articleSearchContainer = journalDisplayContext.getSearchContainer(false);

String displayStyle = journalDisplayContext.getDisplayStyle();

String searchContainerId = ParamUtil.getString(request, "searchContainerId");

      out.write('\n');
      out.write('\n');
      //  liferay-ui:search-container
      com.liferay.taglib.ui.SearchContainerTag _jspx_th_liferay$1ui_search$1container_0 = (com.liferay.taglib.ui.SearchContainerTag) _jspx_tagPool_liferay$1ui_search$1container_searchContainer_id_emptyResultsMessage.get(com.liferay.taglib.ui.SearchContainerTag.class);
      _jspx_th_liferay$1ui_search$1container_0.setPageContext(_jspx_page_context);
      _jspx_th_liferay$1ui_search$1container_0.setParent(null);
      _jspx_th_liferay$1ui_search$1container_0.setEmptyResultsMessage("no-web-content-was-found");
      _jspx_th_liferay$1ui_search$1container_0.setId( searchContainerId );
      _jspx_th_liferay$1ui_search$1container_0.setSearchContainer( articleSearchContainer );
      int _jspx_eval_liferay$1ui_search$1container_0 = _jspx_th_liferay$1ui_search$1container_0.doStartTag();
      if (_jspx_eval_liferay$1ui_search$1container_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        java.lang.Integer total = null;
        com.liferay.portal.kernel.dao.search.SearchContainer searchContainer = null;
        total = (java.lang.Integer) _jspx_page_context.findAttribute("total");
        searchContainer = (com.liferay.portal.kernel.dao.search.SearchContainer) _jspx_page_context.findAttribute("searchContainer");
        out.write('\n');
        out.write('	');
        //  liferay-ui:search-container-row
        com.liferay.taglib.ui.SearchContainerRowTag _jspx_th_liferay$1ui_search$1container$1row_0 = (com.liferay.taglib.ui.SearchContainerRowTag) _jspx_tagPool_liferay$1ui_search$1container$1row_modelVar_cssClass_className.get(com.liferay.taglib.ui.SearchContainerRowTag.class);
        _jspx_th_liferay$1ui_search$1container$1row_0.setPageContext(_jspx_page_context);
        _jspx_th_liferay$1ui_search$1container$1row_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container_0);
        _jspx_th_liferay$1ui_search$1container$1row_0.setClassName("Object");
        _jspx_th_liferay$1ui_search$1container$1row_0.setCssClass("entry-display-style");
        _jspx_th_liferay$1ui_search$1container$1row_0.setModelVar("object");
        int _jspx_eval_liferay$1ui_search$1container$1row_0 = _jspx_th_liferay$1ui_search$1container$1row_0.doStartTag();
        if (_jspx_eval_liferay$1ui_search$1container$1row_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          java.lang.Integer index = null;
          Object object = null;
          com.liferay.portal.kernel.dao.search.ResultRow row = null;
          if (_jspx_eval_liferay$1ui_search$1container$1row_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
            out = _jspx_page_context.pushBody();
            _jspx_th_liferay$1ui_search$1container$1row_0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
            _jspx_th_liferay$1ui_search$1container$1row_0.doInitBody();
          }
          index = (java.lang.Integer) _jspx_page_context.findAttribute("index");
          object = (Object) _jspx_page_context.findAttribute("object");
          row = (com.liferay.portal.kernel.dao.search.ResultRow) _jspx_page_context.findAttribute("row");
          do {
            out.write("\n\n\t\t");

		JournalArticle curArticle = null;
		JournalFolder curFolder = null;

		Object result = row.getObject();

		if (result instanceof JournalFolder) {
			curFolder = (JournalFolder)result;
		}
		else {
			curArticle = (JournalArticle)result;
		}
		
            out.write("\n\n\t\t");
            //  c:choose
            com.liferay.taglib.core.ChooseTag _jspx_th_c_choose_0 = (com.liferay.taglib.core.ChooseTag) _jspx_tagPool_c_choose.get(com.liferay.taglib.core.ChooseTag.class);
            _jspx_th_c_choose_0.setPageContext(_jspx_page_context);
            _jspx_th_c_choose_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1row_0);
            int _jspx_eval_c_choose_0 = _jspx_th_c_choose_0.doStartTag();
            if (_jspx_eval_c_choose_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\t\t\t");
              //  c:when
              com.liferay.taglib.core.WhenTag _jspx_th_c_when_0 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
              _jspx_th_c_when_0.setPageContext(_jspx_page_context);
              _jspx_th_c_when_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_0);
              _jspx_th_c_when_0.setTest( curArticle != null );
              int _jspx_eval_c_when_0 = _jspx_th_c_when_0.doStartTag();
              if (_jspx_eval_c_when_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\n\t\t\t\t");

				Map<String, Object> rowData = new HashMap<String, Object>();

				if (journalDisplayContext.isShowEditActions()) {
					rowData.put("draggable", JournalArticlePermission.contains(permissionChecker, curArticle, ActionKeys.DELETE) || JournalArticlePermission.contains(permissionChecker, curArticle, ActionKeys.UPDATE));
				}

				rowData.put("title", HtmlUtil.escape(curArticle.getTitle(locale)));

				row.setData(rowData);

				row.setPrimaryKey(HtmlUtil.escape(curArticle.getArticleId()));

				String editURL = StringPool.BLANK;

				if (JournalArticlePermission.contains(permissionChecker, curArticle, ActionKeys.UPDATE)) {
					PortletURL editArticleURL = liferayPortletResponse.createRenderURL();

					editArticleURL.setParameter("mvcPath", "/edit_article.jsp");
					editArticleURL.setParameter("redirect", currentURL);
					editArticleURL.setParameter("referringPortletResource", referringPortletResource);
					editArticleURL.setParameter("groupId", String.valueOf(curArticle.getGroupId()));
					editArticleURL.setParameter("folderId", String.valueOf(curArticle.getFolderId()));
					editArticleURL.setParameter("articleId", curArticle.getArticleId());
					editArticleURL.setParameter("version", String.valueOf(curArticle.getVersion()));

					editURL = editArticleURL.toString();
				}
				
                out.write("\n\n\t\t\t\t");
                //  c:choose
                com.liferay.taglib.core.ChooseTag _jspx_th_c_choose_1 = (com.liferay.taglib.core.ChooseTag) _jspx_tagPool_c_choose.get(com.liferay.taglib.core.ChooseTag.class);
                _jspx_th_c_choose_1.setPageContext(_jspx_page_context);
                _jspx_th_c_choose_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_0);
                int _jspx_eval_c_choose_1 = _jspx_th_c_choose_1.doStartTag();
                if (_jspx_eval_c_choose_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  out.write("\n\t\t\t\t\t");
                  //  c:when
                  com.liferay.taglib.core.WhenTag _jspx_th_c_when_1 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
                  _jspx_th_c_when_1.setPageContext(_jspx_page_context);
                  _jspx_th_c_when_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_1);
                  _jspx_th_c_when_1.setTest( displayStyle.equals("descriptive") );
                  int _jspx_eval_c_when_1 = _jspx_th_c_when_1.doStartTag();
                  if (_jspx_eval_c_when_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    out.write("\n\t\t\t\t\t\t");
                    //  liferay-ui:search-container-column-text
                    com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_0 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
                    _jspx_th_liferay$1ui_search$1container$1column$1text_0.setPageContext(_jspx_page_context);
                    _jspx_th_liferay$1ui_search$1container$1column$1text_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_1);
                    int _jspx_eval_liferay$1ui_search$1container$1column$1text_0 = _jspx_th_liferay$1ui_search$1container$1column$1text_0.doStartTag();
                    if (_jspx_eval_liferay$1ui_search$1container$1column$1text_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      if (_jspx_eval_liferay$1ui_search$1container$1column$1text_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                        out = _jspx_page_context.pushBody();
                        _jspx_th_liferay$1ui_search$1container$1column$1text_0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                        _jspx_th_liferay$1ui_search$1container$1column$1text_0.doInitBody();
                      }
                      do {
                        out.write("\n\t\t\t\t\t\t\t");
                        //  liferay-ui:user-portrait
                        com.liferay.taglib.ui.UserPortraitTag _jspx_th_liferay$1ui_user$1portrait_0 = (com.liferay.taglib.ui.UserPortraitTag) _jspx_tagPool_liferay$1ui_user$1portrait_userId_nobody.get(com.liferay.taglib.ui.UserPortraitTag.class);
                        _jspx_th_liferay$1ui_user$1portrait_0.setPageContext(_jspx_page_context);
                        _jspx_th_liferay$1ui_user$1portrait_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1column$1text_0);
                        _jspx_th_liferay$1ui_user$1portrait_0.setUserId( curArticle.getUserId() );
                        int _jspx_eval_liferay$1ui_user$1portrait_0 = _jspx_th_liferay$1ui_user$1portrait_0.doStartTag();
                        if (_jspx_th_liferay$1ui_user$1portrait_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                          _jspx_tagPool_liferay$1ui_user$1portrait_userId_nobody.reuse(_jspx_th_liferay$1ui_user$1portrait_0);
                          return;
                        }
                        _jspx_tagPool_liferay$1ui_user$1portrait_userId_nobody.reuse(_jspx_th_liferay$1ui_user$1portrait_0);
                        out.write("\n\t\t\t\t\t\t");
                        int evalDoAfterBody = _jspx_th_liferay$1ui_search$1container$1column$1text_0.doAfterBody();
                        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                          break;
                      } while (true);
                      if (_jspx_eval_liferay$1ui_search$1container$1column$1text_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                        out = _jspx_page_context.popBody();
                    }
                    if (_jspx_th_liferay$1ui_search$1container$1column$1text_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_liferay$1ui_search$1container$1column$1text.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_0);
                      return;
                    }
                    _jspx_tagPool_liferay$1ui_search$1container$1column$1text.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_0);
                    out.write("\n\n\t\t\t\t\t\t");
                    //  liferay-ui:search-container-column-text
                    com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_1 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text_colspan.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
                    _jspx_th_liferay$1ui_search$1container$1column$1text_1.setPageContext(_jspx_page_context);
                    _jspx_th_liferay$1ui_search$1container$1column$1text_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_1);
                    _jspx_th_liferay$1ui_search$1container$1column$1text_1.setColspan( 2 );
                    int _jspx_eval_liferay$1ui_search$1container$1column$1text_1 = _jspx_th_liferay$1ui_search$1container$1column$1text_1.doStartTag();
                    if (_jspx_eval_liferay$1ui_search$1container$1column$1text_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      if (_jspx_eval_liferay$1ui_search$1container$1column$1text_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                        out = _jspx_page_context.pushBody();
                        _jspx_th_liferay$1ui_search$1container$1column$1text_1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                        _jspx_th_liferay$1ui_search$1container$1column$1text_1.doInitBody();
                      }
                      do {
                        out.write("\n\n\t\t\t\t\t\t\t");

							Date createDate = curArticle.getModifiedDate();

							String modifiedDateDescription = LanguageUtil.getTimeDescription(request, System.currentTimeMillis() - createDate.getTime(), true);
							
                        out.write("\n\n\t\t\t\t\t\t\t<h6 class=\"text-default\">\n\t\t\t\t\t\t\t\t");
                        //  liferay-ui:message
                        com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_0 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_arguments_nobody.get(com.liferay.taglib.ui.MessageTag.class);
                        _jspx_th_liferay$1ui_message_0.setPageContext(_jspx_page_context);
                        _jspx_th_liferay$1ui_message_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1column$1text_1);
                        _jspx_th_liferay$1ui_message_0.setArguments( new String[] {HtmlUtil.escape(curArticle.getUserName()), modifiedDateDescription} );
                        _jspx_th_liferay$1ui_message_0.setKey("x-modified-x-ago");
                        int _jspx_eval_liferay$1ui_message_0 = _jspx_th_liferay$1ui_message_0.doStartTag();
                        if (_jspx_th_liferay$1ui_message_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                          _jspx_tagPool_liferay$1ui_message_key_arguments_nobody.reuse(_jspx_th_liferay$1ui_message_0);
                          return;
                        }
                        _jspx_tagPool_liferay$1ui_message_key_arguments_nobody.reuse(_jspx_th_liferay$1ui_message_0);
                        out.write("\n\t\t\t\t\t\t\t</h6>\n\n\t\t\t\t\t\t\t<h5>\n\t\t\t\t\t\t\t\t");
                        //  aui:a
                        com.liferay.taglib.aui.ATag _jspx_th_aui_a_0 = (com.liferay.taglib.aui.ATag) _jspx_tagPool_aui_a_href.get(com.liferay.taglib.aui.ATag.class);
                        _jspx_th_aui_a_0.setPageContext(_jspx_page_context);
                        _jspx_th_aui_a_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1column$1text_1);
                        _jspx_th_aui_a_0.setHref( editURL );
                        int _jspx_eval_aui_a_0 = _jspx_th_aui_a_0.doStartTag();
                        if (_jspx_eval_aui_a_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                          out.write("\n\t\t\t\t\t\t\t\t\t");
                          out.print( HtmlUtil.escape(curArticle.getTitle(locale)) );
                          out.write("\n\t\t\t\t\t\t\t\t");
                        }
                        if (_jspx_th_aui_a_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                          _jspx_tagPool_aui_a_href.reuse(_jspx_th_aui_a_0);
                          return;
                        }
                        _jspx_tagPool_aui_a_href.reuse(_jspx_th_aui_a_0);
                        out.write("\n\t\t\t\t\t\t\t</h5>\n\n\t\t\t\t\t\t\t");
                        //  c:if
                        com.liferay.taglib.core.IfTag _jspx_th_c_if_0 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                        _jspx_th_c_if_0.setPageContext(_jspx_page_context);
                        _jspx_th_c_if_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1column$1text_1);
                        _jspx_th_c_if_0.setTest( journalDisplayContext.isSearch() );
                        int _jspx_eval_c_if_0 = _jspx_th_c_if_0.doStartTag();
                        if (_jspx_eval_c_if_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                          out.write("\n\t\t\t\t\t\t\t\t<h5>\n\t\t\t\t\t\t\t\t\t");
                          out.print( JournalHelperUtil.getAbsolutePath(liferayPortletRequest, curArticle.getFolderId()) );
                          out.write("\n\t\t\t\t\t\t\t\t</h5>\n\t\t\t\t\t\t\t");
                        }
                        if (_jspx_th_c_if_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                          _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
                          return;
                        }
                        _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
                        out.write("\n\n\t\t\t\t\t\t\t<h6 class=\"text-default\">\n\t\t\t\t\t\t\t\t");
                        //  aui:workflow-status
                        com.liferay.taglib.aui.WorkflowStatusTag _jspx_th_aui_workflow$1status_0 = (com.liferay.taglib.aui.WorkflowStatusTag) _jspx_tagPool_aui_workflow$1status_status_showLabel_showIcon_markupView_nobody.get(com.liferay.taglib.aui.WorkflowStatusTag.class);
                        _jspx_th_aui_workflow$1status_0.setPageContext(_jspx_page_context);
                        _jspx_th_aui_workflow$1status_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1column$1text_1);
                        _jspx_th_aui_workflow$1status_0.setMarkupView("lexicon");
                        _jspx_th_aui_workflow$1status_0.setShowIcon( false );
                        _jspx_th_aui_workflow$1status_0.setShowLabel( false );
                        _jspx_th_aui_workflow$1status_0.setStatus( curArticle.getStatus() );
                        int _jspx_eval_aui_workflow$1status_0 = _jspx_th_aui_workflow$1status_0.doStartTag();
                        if (_jspx_th_aui_workflow$1status_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                          _jspx_tagPool_aui_workflow$1status_status_showLabel_showIcon_markupView_nobody.reuse(_jspx_th_aui_workflow$1status_0);
                          return;
                        }
                        _jspx_tagPool_aui_workflow$1status_status_showLabel_showIcon_markupView_nobody.reuse(_jspx_th_aui_workflow$1status_0);
                        out.write("\n\t\t\t\t\t\t\t</h6>\n\t\t\t\t\t\t");
                        int evalDoAfterBody = _jspx_th_liferay$1ui_search$1container$1column$1text_1.doAfterBody();
                        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                          break;
                      } while (true);
                      if (_jspx_eval_liferay$1ui_search$1container$1column$1text_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                        out = _jspx_page_context.popBody();
                    }
                    if (_jspx_th_liferay$1ui_search$1container$1column$1text_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_liferay$1ui_search$1container$1column$1text_colspan.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_1);
                      return;
                    }
                    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_colspan.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_1);
                    out.write("\n\n\t\t\t\t\t\t");
                    //  c:if
                    com.liferay.taglib.core.IfTag _jspx_th_c_if_1 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                    _jspx_th_c_if_1.setPageContext(_jspx_page_context);
                    _jspx_th_c_if_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_1);
                    _jspx_th_c_if_1.setTest( journalDisplayContext.isShowEditActions() );
                    int _jspx_eval_c_if_1 = _jspx_th_c_if_1.doStartTag();
                    if (_jspx_eval_c_if_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      out.write("\n\t\t\t\t\t\t\t");
                      if (_jspx_meth_liferay$1ui_search$1container$1column$1jsp_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_1, _jspx_page_context))
                        return;
                      out.write("\n\t\t\t\t\t\t");
                    }
                    if (_jspx_th_c_if_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_1);
                      return;
                    }
                    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_1);
                    out.write("\n\t\t\t\t\t");
                  }
                  if (_jspx_th_c_when_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_1);
                    return;
                  }
                  _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_1);
                  out.write("\n\t\t\t\t\t");
                  //  c:when
                  com.liferay.taglib.core.WhenTag _jspx_th_c_when_2 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
                  _jspx_th_c_when_2.setPageContext(_jspx_page_context);
                  _jspx_th_c_when_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_1);
                  _jspx_th_c_when_2.setTest( displayStyle.equals("icon") );
                  int _jspx_eval_c_when_2 = _jspx_th_c_when_2.doStartTag();
                  if (_jspx_eval_c_when_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    out.write("\n\n\t\t\t\t\t\t");

						row.setCssClass("entry-card lfr-asset-item " + row.getCssClass());
						
                    out.write("\n\n\t\t\t\t\t\t");
                    //  liferay-ui:search-container-column-text
                    com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_2 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
                    _jspx_th_liferay$1ui_search$1container$1column$1text_2.setPageContext(_jspx_page_context);
                    _jspx_th_liferay$1ui_search$1container$1column$1text_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_2);
                    int _jspx_eval_liferay$1ui_search$1container$1column$1text_2 = _jspx_th_liferay$1ui_search$1container$1column$1text_2.doStartTag();
                    if (_jspx_eval_liferay$1ui_search$1container$1column$1text_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      if (_jspx_eval_liferay$1ui_search$1container$1column$1text_2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                        out = _jspx_page_context.pushBody();
                        _jspx_th_liferay$1ui_search$1container$1column$1text_2.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                        _jspx_th_liferay$1ui_search$1container$1column$1text_2.doInitBody();
                      }
                      do {
                        out.write("\n\n\t\t\t\t\t\t\t");

							String articleImageURL = curArticle.getArticleImageURL(themeDisplay);
							
                        out.write("\n\n\t\t\t\t\t\t\t");
                        //  c:choose
                        com.liferay.taglib.core.ChooseTag _jspx_th_c_choose_2 = (com.liferay.taglib.core.ChooseTag) _jspx_tagPool_c_choose.get(com.liferay.taglib.core.ChooseTag.class);
                        _jspx_th_c_choose_2.setPageContext(_jspx_page_context);
                        _jspx_th_c_choose_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1column$1text_2);
                        int _jspx_eval_c_choose_2 = _jspx_th_c_choose_2.doStartTag();
                        if (_jspx_eval_c_choose_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                          out.write("\n\t\t\t\t\t\t\t\t");
                          //  c:when
                          com.liferay.taglib.core.WhenTag _jspx_th_c_when_3 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
                          _jspx_th_c_when_3.setPageContext(_jspx_page_context);
                          _jspx_th_c_when_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_2);
                          _jspx_th_c_when_3.setTest( Validator.isNotNull(articleImageURL) );
                          int _jspx_eval_c_when_3 = _jspx_th_c_when_3.doStartTag();
                          if (_jspx_eval_c_when_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                            out.write("\n\t\t\t\t\t\t\t\t\t");
                            //  liferay-frontend:vertical-card
                            com.liferay.frontend.taglib.servlet.taglib.VerticalCardTag _jspx_th_liferay$1frontend_vertical$1card_0 = (com.liferay.frontend.taglib.servlet.taglib.VerticalCardTag) _jspx_tagPool_liferay$1frontend_vertical$1card_url_title_rowChecker_resultRow_imageUrl_actionJspServletContext_actionJsp.get(com.liferay.frontend.taglib.servlet.taglib.VerticalCardTag.class);
                            _jspx_th_liferay$1frontend_vertical$1card_0.setPageContext(_jspx_page_context);
                            _jspx_th_liferay$1frontend_vertical$1card_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_3);
                            _jspx_th_liferay$1frontend_vertical$1card_0.setActionJsp( journalDisplayContext.isShowEditActions() ? "/article_action.jsp" : null );
                            _jspx_th_liferay$1frontend_vertical$1card_0.setActionJspServletContext( application );
                            _jspx_th_liferay$1frontend_vertical$1card_0.setImageUrl( HtmlUtil.escape(articleImageURL) );
                            _jspx_th_liferay$1frontend_vertical$1card_0.setResultRow( row );
                            _jspx_th_liferay$1frontend_vertical$1card_0.setRowChecker( articleSearchContainer.getRowChecker() );
                            _jspx_th_liferay$1frontend_vertical$1card_0.setTitle( curArticle.getTitle(locale) );
                            _jspx_th_liferay$1frontend_vertical$1card_0.setUrl( editURL );
                            int _jspx_eval_liferay$1frontend_vertical$1card_0 = _jspx_th_liferay$1frontend_vertical$1card_0.doStartTag();
                            if (_jspx_eval_liferay$1frontend_vertical$1card_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                              out.write("\n\t\t\t\t\t\t\t\t\t\t");
                              out.write('\n');
                              out.write('\n');
                              //  liferay-frontend:vertical-card-sticker-bottom
                              com.liferay.frontend.taglib.servlet.taglib.VerticalCardStickerBottomTag _jspx_th_liferay$1frontend_vertical$1card$1sticker$1bottom_0 = (com.liferay.frontend.taglib.servlet.taglib.VerticalCardStickerBottomTag) _jspx_tagPool_liferay$1frontend_vertical$1card$1sticker$1bottom.get(com.liferay.frontend.taglib.servlet.taglib.VerticalCardStickerBottomTag.class);
                              _jspx_th_liferay$1frontend_vertical$1card$1sticker$1bottom_0.setPageContext(_jspx_page_context);
                              _jspx_th_liferay$1frontend_vertical$1card$1sticker$1bottom_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_vertical$1card_0);
                              int _jspx_eval_liferay$1frontend_vertical$1card$1sticker$1bottom_0 = _jspx_th_liferay$1frontend_vertical$1card$1sticker$1bottom_0.doStartTag();
                              if (_jspx_eval_liferay$1frontend_vertical$1card$1sticker$1bottom_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                              if (_jspx_eval_liferay$1frontend_vertical$1card$1sticker$1bottom_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                              out = _jspx_page_context.pushBody();
                              _jspx_th_liferay$1frontend_vertical$1card$1sticker$1bottom_0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                              _jspx_th_liferay$1frontend_vertical$1card$1sticker$1bottom_0.doInitBody();
                              }
                              do {
                              out.write('\n');
                              out.write('	');
                              //  liferay-ui:user-portrait
                              com.liferay.taglib.ui.UserPortraitTag _jspx_th_liferay$1ui_user$1portrait_1 = (com.liferay.taglib.ui.UserPortraitTag) _jspx_tagPool_liferay$1ui_user$1portrait_userId_cssClass_nobody.get(com.liferay.taglib.ui.UserPortraitTag.class);
                              _jspx_th_liferay$1ui_user$1portrait_1.setPageContext(_jspx_page_context);
                              _jspx_th_liferay$1ui_user$1portrait_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_vertical$1card$1sticker$1bottom_0);
                              _jspx_th_liferay$1ui_user$1portrait_1.setCssClass("sticker sticker-bottom");
                              _jspx_th_liferay$1ui_user$1portrait_1.setUserId( curArticle.getUserId() );
                              int _jspx_eval_liferay$1ui_user$1portrait_1 = _jspx_th_liferay$1ui_user$1portrait_1.doStartTag();
                              if (_jspx_th_liferay$1ui_user$1portrait_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_liferay$1ui_user$1portrait_userId_cssClass_nobody.reuse(_jspx_th_liferay$1ui_user$1portrait_1);
                              return;
                              }
                              _jspx_tagPool_liferay$1ui_user$1portrait_userId_cssClass_nobody.reuse(_jspx_th_liferay$1ui_user$1portrait_1);
                              out.write('\n');
                              int evalDoAfterBody = _jspx_th_liferay$1frontend_vertical$1card$1sticker$1bottom_0.doAfterBody();
                              if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                              break;
                              } while (true);
                              if (_jspx_eval_liferay$1frontend_vertical$1card$1sticker$1bottom_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                              out = _jspx_page_context.popBody();
                              }
                              if (_jspx_th_liferay$1frontend_vertical$1card$1sticker$1bottom_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_liferay$1frontend_vertical$1card$1sticker$1bottom.reuse(_jspx_th_liferay$1frontend_vertical$1card$1sticker$1bottom_0);
                              return;
                              }
                              _jspx_tagPool_liferay$1frontend_vertical$1card$1sticker$1bottom.reuse(_jspx_th_liferay$1frontend_vertical$1card$1sticker$1bottom_0);
                              out.write('\n');
                              out.write('\n');
                              //  liferay-frontend:vertical-card-header
                              com.liferay.frontend.taglib.servlet.taglib.VerticalCardHeaderTag _jspx_th_liferay$1frontend_vertical$1card$1header_0 = (com.liferay.frontend.taglib.servlet.taglib.VerticalCardHeaderTag) _jspx_tagPool_liferay$1frontend_vertical$1card$1header.get(com.liferay.frontend.taglib.servlet.taglib.VerticalCardHeaderTag.class);
                              _jspx_th_liferay$1frontend_vertical$1card$1header_0.setPageContext(_jspx_page_context);
                              _jspx_th_liferay$1frontend_vertical$1card$1header_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_vertical$1card_0);
                              int _jspx_eval_liferay$1frontend_vertical$1card$1header_0 = _jspx_th_liferay$1frontend_vertical$1card$1header_0.doStartTag();
                              if (_jspx_eval_liferay$1frontend_vertical$1card$1header_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                              if (_jspx_eval_liferay$1frontend_vertical$1card$1header_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                              out = _jspx_page_context.pushBody();
                              _jspx_th_liferay$1frontend_vertical$1card$1header_0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                              _jspx_th_liferay$1frontend_vertical$1card$1header_0.doInitBody();
                              }
                              do {
                              out.write("\n\n\t");

	Date createDate = curArticle.getModifiedDate();

	String modifiedDateDescription = LanguageUtil.getTimeDescription(request, System.currentTimeMillis() - createDate.getTime(), true);
	
                              out.write("\n\n\t");
                              //  liferay-ui:message
                              com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_1 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_arguments_nobody.get(com.liferay.taglib.ui.MessageTag.class);
                              _jspx_th_liferay$1ui_message_1.setPageContext(_jspx_page_context);
                              _jspx_th_liferay$1ui_message_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_vertical$1card$1header_0);
                              _jspx_th_liferay$1ui_message_1.setArguments( new String[] {HtmlUtil.escape(curArticle.getUserName()), modifiedDateDescription} );
                              _jspx_th_liferay$1ui_message_1.setKey("x-modified-x-ago");
                              int _jspx_eval_liferay$1ui_message_1 = _jspx_th_liferay$1ui_message_1.doStartTag();
                              if (_jspx_th_liferay$1ui_message_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_liferay$1ui_message_key_arguments_nobody.reuse(_jspx_th_liferay$1ui_message_1);
                              return;
                              }
                              _jspx_tagPool_liferay$1ui_message_key_arguments_nobody.reuse(_jspx_th_liferay$1ui_message_1);
                              out.write('\n');
                              int evalDoAfterBody = _jspx_th_liferay$1frontend_vertical$1card$1header_0.doAfterBody();
                              if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                              break;
                              } while (true);
                              if (_jspx_eval_liferay$1frontend_vertical$1card$1header_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                              out = _jspx_page_context.popBody();
                              }
                              if (_jspx_th_liferay$1frontend_vertical$1card$1header_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_liferay$1frontend_vertical$1card$1header.reuse(_jspx_th_liferay$1frontend_vertical$1card$1header_0);
                              return;
                              }
                              _jspx_tagPool_liferay$1frontend_vertical$1card$1header.reuse(_jspx_th_liferay$1frontend_vertical$1card$1header_0);
                              out.write('\n');
                              out.write('\n');
                              //  liferay-frontend:vertical-card-footer
                              com.liferay.frontend.taglib.servlet.taglib.VerticalCardFooterTag _jspx_th_liferay$1frontend_vertical$1card$1footer_0 = (com.liferay.frontend.taglib.servlet.taglib.VerticalCardFooterTag) _jspx_tagPool_liferay$1frontend_vertical$1card$1footer.get(com.liferay.frontend.taglib.servlet.taglib.VerticalCardFooterTag.class);
                              _jspx_th_liferay$1frontend_vertical$1card$1footer_0.setPageContext(_jspx_page_context);
                              _jspx_th_liferay$1frontend_vertical$1card$1footer_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_vertical$1card_0);
                              int _jspx_eval_liferay$1frontend_vertical$1card$1footer_0 = _jspx_th_liferay$1frontend_vertical$1card$1footer_0.doStartTag();
                              if (_jspx_eval_liferay$1frontend_vertical$1card$1footer_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                              if (_jspx_eval_liferay$1frontend_vertical$1card$1footer_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                              out = _jspx_page_context.pushBody();
                              _jspx_th_liferay$1frontend_vertical$1card$1footer_0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                              _jspx_th_liferay$1frontend_vertical$1card$1footer_0.doInitBody();
                              }
                              do {
                              out.write('\n');
                              out.write('	');
                              //  aui:workflow-status
                              com.liferay.taglib.aui.WorkflowStatusTag _jspx_th_aui_workflow$1status_1 = (com.liferay.taglib.aui.WorkflowStatusTag) _jspx_tagPool_aui_workflow$1status_status_showLabel_showIcon_markupView_nobody.get(com.liferay.taglib.aui.WorkflowStatusTag.class);
                              _jspx_th_aui_workflow$1status_1.setPageContext(_jspx_page_context);
                              _jspx_th_aui_workflow$1status_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_vertical$1card$1footer_0);
                              _jspx_th_aui_workflow$1status_1.setMarkupView("lexicon");
                              _jspx_th_aui_workflow$1status_1.setShowIcon( false );
                              _jspx_th_aui_workflow$1status_1.setShowLabel( false );
                              _jspx_th_aui_workflow$1status_1.setStatus( curArticle.getStatus() );
                              int _jspx_eval_aui_workflow$1status_1 = _jspx_th_aui_workflow$1status_1.doStartTag();
                              if (_jspx_th_aui_workflow$1status_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_aui_workflow$1status_status_showLabel_showIcon_markupView_nobody.reuse(_jspx_th_aui_workflow$1status_1);
                              return;
                              }
                              _jspx_tagPool_aui_workflow$1status_status_showLabel_showIcon_markupView_nobody.reuse(_jspx_th_aui_workflow$1status_1);
                              out.write('\n');
                              int evalDoAfterBody = _jspx_th_liferay$1frontend_vertical$1card$1footer_0.doAfterBody();
                              if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                              break;
                              } while (true);
                              if (_jspx_eval_liferay$1frontend_vertical$1card$1footer_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                              out = _jspx_page_context.popBody();
                              }
                              if (_jspx_th_liferay$1frontend_vertical$1card$1footer_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_liferay$1frontend_vertical$1card$1footer.reuse(_jspx_th_liferay$1frontend_vertical$1card$1footer_0);
                              return;
                              }
                              _jspx_tagPool_liferay$1frontend_vertical$1card$1footer.reuse(_jspx_th_liferay$1frontend_vertical$1card$1footer_0);
                              out.write("\n\t\t\t\t\t\t\t\t\t");
                            }
                            if (_jspx_th_liferay$1frontend_vertical$1card_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_liferay$1frontend_vertical$1card_url_title_rowChecker_resultRow_imageUrl_actionJspServletContext_actionJsp.reuse(_jspx_th_liferay$1frontend_vertical$1card_0);
                              return;
                            }
                            _jspx_tagPool_liferay$1frontend_vertical$1card_url_title_rowChecker_resultRow_imageUrl_actionJspServletContext_actionJsp.reuse(_jspx_th_liferay$1frontend_vertical$1card_0);
                            out.write("\n\t\t\t\t\t\t\t\t");
                          }
                          if (_jspx_th_c_when_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                            _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_3);
                            return;
                          }
                          _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_3);
                          out.write("\n\t\t\t\t\t\t\t\t");
                          //  c:otherwise
                          com.liferay.taglib.core.OtherwiseTag _jspx_th_c_otherwise_0 = (com.liferay.taglib.core.OtherwiseTag) _jspx_tagPool_c_otherwise.get(com.liferay.taglib.core.OtherwiseTag.class);
                          _jspx_th_c_otherwise_0.setPageContext(_jspx_page_context);
                          _jspx_th_c_otherwise_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_2);
                          int _jspx_eval_c_otherwise_0 = _jspx_th_c_otherwise_0.doStartTag();
                          if (_jspx_eval_c_otherwise_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                            out.write("\n\t\t\t\t\t\t\t\t\t");
                            //  liferay-frontend:icon-vertical-card
                            com.liferay.frontend.taglib.servlet.taglib.IconVerticalCardTag _jspx_th_liferay$1frontend_icon$1vertical$1card_0 = (com.liferay.frontend.taglib.servlet.taglib.IconVerticalCardTag) _jspx_tagPool_liferay$1frontend_icon$1vertical$1card_url_title_rowChecker_resultRow_icon_actionJspServletContext_actionJsp.get(com.liferay.frontend.taglib.servlet.taglib.IconVerticalCardTag.class);
                            _jspx_th_liferay$1frontend_icon$1vertical$1card_0.setPageContext(_jspx_page_context);
                            _jspx_th_liferay$1frontend_icon$1vertical$1card_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_0);
                            _jspx_th_liferay$1frontend_icon$1vertical$1card_0.setActionJsp( journalDisplayContext.isShowEditActions() ? "/article_action.jsp" : null );
                            _jspx_th_liferay$1frontend_icon$1vertical$1card_0.setActionJspServletContext( application );
                            _jspx_th_liferay$1frontend_icon$1vertical$1card_0.setIcon("web-content");
                            _jspx_th_liferay$1frontend_icon$1vertical$1card_0.setResultRow( row );
                            _jspx_th_liferay$1frontend_icon$1vertical$1card_0.setRowChecker( articleSearchContainer.getRowChecker() );
                            _jspx_th_liferay$1frontend_icon$1vertical$1card_0.setTitle( curArticle.getTitle(locale) );
                            _jspx_th_liferay$1frontend_icon$1vertical$1card_0.setUrl( editURL );
                            int _jspx_eval_liferay$1frontend_icon$1vertical$1card_0 = _jspx_th_liferay$1frontend_icon$1vertical$1card_0.doStartTag();
                            if (_jspx_eval_liferay$1frontend_icon$1vertical$1card_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                              out.write("\n\t\t\t\t\t\t\t\t\t\t");
                              out.write('\n');
                              out.write('\n');
                              //  liferay-frontend:vertical-card-sticker-bottom
                              com.liferay.frontend.taglib.servlet.taglib.VerticalCardStickerBottomTag _jspx_th_liferay$1frontend_vertical$1card$1sticker$1bottom_1 = (com.liferay.frontend.taglib.servlet.taglib.VerticalCardStickerBottomTag) _jspx_tagPool_liferay$1frontend_vertical$1card$1sticker$1bottom.get(com.liferay.frontend.taglib.servlet.taglib.VerticalCardStickerBottomTag.class);
                              _jspx_th_liferay$1frontend_vertical$1card$1sticker$1bottom_1.setPageContext(_jspx_page_context);
                              _jspx_th_liferay$1frontend_vertical$1card$1sticker$1bottom_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_icon$1vertical$1card_0);
                              int _jspx_eval_liferay$1frontend_vertical$1card$1sticker$1bottom_1 = _jspx_th_liferay$1frontend_vertical$1card$1sticker$1bottom_1.doStartTag();
                              if (_jspx_eval_liferay$1frontend_vertical$1card$1sticker$1bottom_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                              if (_jspx_eval_liferay$1frontend_vertical$1card$1sticker$1bottom_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                              out = _jspx_page_context.pushBody();
                              _jspx_th_liferay$1frontend_vertical$1card$1sticker$1bottom_1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                              _jspx_th_liferay$1frontend_vertical$1card$1sticker$1bottom_1.doInitBody();
                              }
                              do {
                              out.write('\n');
                              out.write('	');
                              //  liferay-ui:user-portrait
                              com.liferay.taglib.ui.UserPortraitTag _jspx_th_liferay$1ui_user$1portrait_2 = (com.liferay.taglib.ui.UserPortraitTag) _jspx_tagPool_liferay$1ui_user$1portrait_userId_cssClass_nobody.get(com.liferay.taglib.ui.UserPortraitTag.class);
                              _jspx_th_liferay$1ui_user$1portrait_2.setPageContext(_jspx_page_context);
                              _jspx_th_liferay$1ui_user$1portrait_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_vertical$1card$1sticker$1bottom_1);
                              _jspx_th_liferay$1ui_user$1portrait_2.setCssClass("sticker sticker-bottom");
                              _jspx_th_liferay$1ui_user$1portrait_2.setUserId( curArticle.getUserId() );
                              int _jspx_eval_liferay$1ui_user$1portrait_2 = _jspx_th_liferay$1ui_user$1portrait_2.doStartTag();
                              if (_jspx_th_liferay$1ui_user$1portrait_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_liferay$1ui_user$1portrait_userId_cssClass_nobody.reuse(_jspx_th_liferay$1ui_user$1portrait_2);
                              return;
                              }
                              _jspx_tagPool_liferay$1ui_user$1portrait_userId_cssClass_nobody.reuse(_jspx_th_liferay$1ui_user$1portrait_2);
                              out.write('\n');
                              int evalDoAfterBody = _jspx_th_liferay$1frontend_vertical$1card$1sticker$1bottom_1.doAfterBody();
                              if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                              break;
                              } while (true);
                              if (_jspx_eval_liferay$1frontend_vertical$1card$1sticker$1bottom_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                              out = _jspx_page_context.popBody();
                              }
                              if (_jspx_th_liferay$1frontend_vertical$1card$1sticker$1bottom_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_liferay$1frontend_vertical$1card$1sticker$1bottom.reuse(_jspx_th_liferay$1frontend_vertical$1card$1sticker$1bottom_1);
                              return;
                              }
                              _jspx_tagPool_liferay$1frontend_vertical$1card$1sticker$1bottom.reuse(_jspx_th_liferay$1frontend_vertical$1card$1sticker$1bottom_1);
                              out.write('\n');
                              out.write('\n');
                              //  liferay-frontend:vertical-card-header
                              com.liferay.frontend.taglib.servlet.taglib.VerticalCardHeaderTag _jspx_th_liferay$1frontend_vertical$1card$1header_1 = (com.liferay.frontend.taglib.servlet.taglib.VerticalCardHeaderTag) _jspx_tagPool_liferay$1frontend_vertical$1card$1header.get(com.liferay.frontend.taglib.servlet.taglib.VerticalCardHeaderTag.class);
                              _jspx_th_liferay$1frontend_vertical$1card$1header_1.setPageContext(_jspx_page_context);
                              _jspx_th_liferay$1frontend_vertical$1card$1header_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_icon$1vertical$1card_0);
                              int _jspx_eval_liferay$1frontend_vertical$1card$1header_1 = _jspx_th_liferay$1frontend_vertical$1card$1header_1.doStartTag();
                              if (_jspx_eval_liferay$1frontend_vertical$1card$1header_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                              if (_jspx_eval_liferay$1frontend_vertical$1card$1header_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                              out = _jspx_page_context.pushBody();
                              _jspx_th_liferay$1frontend_vertical$1card$1header_1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                              _jspx_th_liferay$1frontend_vertical$1card$1header_1.doInitBody();
                              }
                              do {
                              out.write("\n\n\t");

	Date createDate = curArticle.getModifiedDate();

	String modifiedDateDescription = LanguageUtil.getTimeDescription(request, System.currentTimeMillis() - createDate.getTime(), true);
	
                              out.write("\n\n\t");
                              //  liferay-ui:message
                              com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_2 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_arguments_nobody.get(com.liferay.taglib.ui.MessageTag.class);
                              _jspx_th_liferay$1ui_message_2.setPageContext(_jspx_page_context);
                              _jspx_th_liferay$1ui_message_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_vertical$1card$1header_1);
                              _jspx_th_liferay$1ui_message_2.setArguments( new String[] {HtmlUtil.escape(curArticle.getUserName()), modifiedDateDescription} );
                              _jspx_th_liferay$1ui_message_2.setKey("x-modified-x-ago");
                              int _jspx_eval_liferay$1ui_message_2 = _jspx_th_liferay$1ui_message_2.doStartTag();
                              if (_jspx_th_liferay$1ui_message_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_liferay$1ui_message_key_arguments_nobody.reuse(_jspx_th_liferay$1ui_message_2);
                              return;
                              }
                              _jspx_tagPool_liferay$1ui_message_key_arguments_nobody.reuse(_jspx_th_liferay$1ui_message_2);
                              out.write('\n');
                              int evalDoAfterBody = _jspx_th_liferay$1frontend_vertical$1card$1header_1.doAfterBody();
                              if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                              break;
                              } while (true);
                              if (_jspx_eval_liferay$1frontend_vertical$1card$1header_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                              out = _jspx_page_context.popBody();
                              }
                              if (_jspx_th_liferay$1frontend_vertical$1card$1header_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_liferay$1frontend_vertical$1card$1header.reuse(_jspx_th_liferay$1frontend_vertical$1card$1header_1);
                              return;
                              }
                              _jspx_tagPool_liferay$1frontend_vertical$1card$1header.reuse(_jspx_th_liferay$1frontend_vertical$1card$1header_1);
                              out.write('\n');
                              out.write('\n');
                              //  liferay-frontend:vertical-card-footer
                              com.liferay.frontend.taglib.servlet.taglib.VerticalCardFooterTag _jspx_th_liferay$1frontend_vertical$1card$1footer_1 = (com.liferay.frontend.taglib.servlet.taglib.VerticalCardFooterTag) _jspx_tagPool_liferay$1frontend_vertical$1card$1footer.get(com.liferay.frontend.taglib.servlet.taglib.VerticalCardFooterTag.class);
                              _jspx_th_liferay$1frontend_vertical$1card$1footer_1.setPageContext(_jspx_page_context);
                              _jspx_th_liferay$1frontend_vertical$1card$1footer_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_icon$1vertical$1card_0);
                              int _jspx_eval_liferay$1frontend_vertical$1card$1footer_1 = _jspx_th_liferay$1frontend_vertical$1card$1footer_1.doStartTag();
                              if (_jspx_eval_liferay$1frontend_vertical$1card$1footer_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                              if (_jspx_eval_liferay$1frontend_vertical$1card$1footer_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                              out = _jspx_page_context.pushBody();
                              _jspx_th_liferay$1frontend_vertical$1card$1footer_1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                              _jspx_th_liferay$1frontend_vertical$1card$1footer_1.doInitBody();
                              }
                              do {
                              out.write('\n');
                              out.write('	');
                              //  aui:workflow-status
                              com.liferay.taglib.aui.WorkflowStatusTag _jspx_th_aui_workflow$1status_2 = (com.liferay.taglib.aui.WorkflowStatusTag) _jspx_tagPool_aui_workflow$1status_status_showLabel_showIcon_markupView_nobody.get(com.liferay.taglib.aui.WorkflowStatusTag.class);
                              _jspx_th_aui_workflow$1status_2.setPageContext(_jspx_page_context);
                              _jspx_th_aui_workflow$1status_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_vertical$1card$1footer_1);
                              _jspx_th_aui_workflow$1status_2.setMarkupView("lexicon");
                              _jspx_th_aui_workflow$1status_2.setShowIcon( false );
                              _jspx_th_aui_workflow$1status_2.setShowLabel( false );
                              _jspx_th_aui_workflow$1status_2.setStatus( curArticle.getStatus() );
                              int _jspx_eval_aui_workflow$1status_2 = _jspx_th_aui_workflow$1status_2.doStartTag();
                              if (_jspx_th_aui_workflow$1status_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_aui_workflow$1status_status_showLabel_showIcon_markupView_nobody.reuse(_jspx_th_aui_workflow$1status_2);
                              return;
                              }
                              _jspx_tagPool_aui_workflow$1status_status_showLabel_showIcon_markupView_nobody.reuse(_jspx_th_aui_workflow$1status_2);
                              out.write('\n');
                              int evalDoAfterBody = _jspx_th_liferay$1frontend_vertical$1card$1footer_1.doAfterBody();
                              if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                              break;
                              } while (true);
                              if (_jspx_eval_liferay$1frontend_vertical$1card$1footer_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                              out = _jspx_page_context.popBody();
                              }
                              if (_jspx_th_liferay$1frontend_vertical$1card$1footer_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_liferay$1frontend_vertical$1card$1footer.reuse(_jspx_th_liferay$1frontend_vertical$1card$1footer_1);
                              return;
                              }
                              _jspx_tagPool_liferay$1frontend_vertical$1card$1footer.reuse(_jspx_th_liferay$1frontend_vertical$1card$1footer_1);
                              out.write("\n\t\t\t\t\t\t\t\t\t");
                            }
                            if (_jspx_th_liferay$1frontend_icon$1vertical$1card_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_liferay$1frontend_icon$1vertical$1card_url_title_rowChecker_resultRow_icon_actionJspServletContext_actionJsp.reuse(_jspx_th_liferay$1frontend_icon$1vertical$1card_0);
                              return;
                            }
                            _jspx_tagPool_liferay$1frontend_icon$1vertical$1card_url_title_rowChecker_resultRow_icon_actionJspServletContext_actionJsp.reuse(_jspx_th_liferay$1frontend_icon$1vertical$1card_0);
                            out.write("\n\t\t\t\t\t\t\t\t");
                          }
                          if (_jspx_th_c_otherwise_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                            _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_0);
                            return;
                          }
                          _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_0);
                          out.write("\n\t\t\t\t\t\t\t");
                        }
                        if (_jspx_th_c_choose_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                          _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_2);
                          return;
                        }
                        _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_2);
                        out.write("\n\t\t\t\t\t\t");
                        int evalDoAfterBody = _jspx_th_liferay$1ui_search$1container$1column$1text_2.doAfterBody();
                        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                          break;
                      } while (true);
                      if (_jspx_eval_liferay$1ui_search$1container$1column$1text_2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                        out = _jspx_page_context.popBody();
                    }
                    if (_jspx_th_liferay$1ui_search$1container$1column$1text_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_liferay$1ui_search$1container$1column$1text.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_2);
                      return;
                    }
                    _jspx_tagPool_liferay$1ui_search$1container$1column$1text.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_2);
                    out.write("\n\t\t\t\t\t");
                  }
                  if (_jspx_th_c_when_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_2);
                    return;
                  }
                  _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_2);
                  out.write("\n\t\t\t\t\t");
                  //  c:otherwise
                  com.liferay.taglib.core.OtherwiseTag _jspx_th_c_otherwise_1 = (com.liferay.taglib.core.OtherwiseTag) _jspx_tagPool_c_otherwise.get(com.liferay.taglib.core.OtherwiseTag.class);
                  _jspx_th_c_otherwise_1.setPageContext(_jspx_page_context);
                  _jspx_th_c_otherwise_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_1);
                  int _jspx_eval_c_otherwise_1 = _jspx_th_c_otherwise_1.doStartTag();
                  if (_jspx_eval_c_otherwise_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    out.write("\n\t\t\t\t\t\t");
                    //  c:if
                    com.liferay.taglib.core.IfTag _jspx_th_c_if_2 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                    _jspx_th_c_if_2.setPageContext(_jspx_page_context);
                    _jspx_th_c_if_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_1);
                    _jspx_th_c_if_2.setTest( !journalWebConfiguration.journalArticleForceAutogenerateId() );
                    int _jspx_eval_c_if_2 = _jspx_th_c_if_2.doStartTag();
                    if (_jspx_eval_c_if_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      out.write("\n\t\t\t\t\t\t\t");
                      //  liferay-ui:search-container-column-text
                      com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_3 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_nobody.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
                      _jspx_th_liferay$1ui_search$1container$1column$1text_3.setPageContext(_jspx_page_context);
                      _jspx_th_liferay$1ui_search$1container$1column$1text_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_2);
                      _jspx_th_liferay$1ui_search$1container$1column$1text_3.setName("id");
                      _jspx_th_liferay$1ui_search$1container$1column$1text_3.setValue( HtmlUtil.escape(curArticle.getArticleId()) );
                      int _jspx_eval_liferay$1ui_search$1container$1column$1text_3 = _jspx_th_liferay$1ui_search$1container$1column$1text_3.doStartTag();
                      if (_jspx_th_liferay$1ui_search$1container$1column$1text_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_3);
                        return;
                      }
                      _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_3);
                      out.write("\n\t\t\t\t\t\t");
                    }
                    if (_jspx_th_c_if_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_2);
                      return;
                    }
                    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_2);
                    out.write("\n\n\t\t\t\t\t\t");
                    //  liferay-ui:search-container-column-jsp
                    com.liferay.taglib.ui.SearchContainerColumnJSPTag _jspx_th_liferay$1ui_search$1container$1column$1jsp_1 = (com.liferay.taglib.ui.SearchContainerColumnJSPTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1jsp_path_name_href_cssClass_nobody.get(com.liferay.taglib.ui.SearchContainerColumnJSPTag.class);
                    _jspx_th_liferay$1ui_search$1container$1column$1jsp_1.setPageContext(_jspx_page_context);
                    _jspx_th_liferay$1ui_search$1container$1column$1jsp_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_1);
                    _jspx_th_liferay$1ui_search$1container$1column$1jsp_1.setCssClass("table-cell-expand table-cell-minw-200 table-title");
                    _jspx_th_liferay$1ui_search$1container$1column$1jsp_1.setHref( editURL );
                    _jspx_th_liferay$1ui_search$1container$1column$1jsp_1.setName("title");
                    _jspx_th_liferay$1ui_search$1container$1column$1jsp_1.setPath("/article_title.jsp");
                    int _jspx_eval_liferay$1ui_search$1container$1column$1jsp_1 = _jspx_th_liferay$1ui_search$1container$1column$1jsp_1.doStartTag();
                    if (_jspx_th_liferay$1ui_search$1container$1column$1jsp_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_liferay$1ui_search$1container$1column$1jsp_path_name_href_cssClass_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1jsp_1);
                      return;
                    }
                    _jspx_tagPool_liferay$1ui_search$1container$1column$1jsp_path_name_href_cssClass_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1jsp_1);
                    out.write("\n\n\t\t\t\t\t\t");
                    //  liferay-ui:search-container-column-text
                    com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_4 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_cssClass_nobody.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
                    _jspx_th_liferay$1ui_search$1container$1column$1text_4.setPageContext(_jspx_page_context);
                    _jspx_th_liferay$1ui_search$1container$1column$1text_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_1);
                    _jspx_th_liferay$1ui_search$1container$1column$1text_4.setCssClass("table-cell-expand table-cell-minw-200");
                    _jspx_th_liferay$1ui_search$1container$1column$1text_4.setName("description");
                    _jspx_th_liferay$1ui_search$1container$1column$1text_4.setValue( StringUtil.shorten(HtmlUtil.stripHtml(curArticle.getDescription(locale)), 200) );
                    int _jspx_eval_liferay$1ui_search$1container$1column$1text_4 = _jspx_th_liferay$1ui_search$1container$1column$1text_4.doStartTag();
                    if (_jspx_th_liferay$1ui_search$1container$1column$1text_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_cssClass_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_4);
                      return;
                    }
                    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_cssClass_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_4);
                    out.write("\n\n\t\t\t\t\t\t");
                    //  c:if
                    com.liferay.taglib.core.IfTag _jspx_th_c_if_3 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                    _jspx_th_c_if_3.setPageContext(_jspx_page_context);
                    _jspx_th_c_if_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_1);
                    _jspx_th_c_if_3.setTest( journalDisplayContext.isSearch() );
                    int _jspx_eval_c_if_3 = _jspx_th_c_if_3.doStartTag();
                    if (_jspx_eval_c_if_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      out.write("\n\t\t\t\t\t\t\t");
                      //  liferay-ui:search-container-column-text
                      com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_5 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_cssClass_nobody.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
                      _jspx_th_liferay$1ui_search$1container$1column$1text_5.setPageContext(_jspx_page_context);
                      _jspx_th_liferay$1ui_search$1container$1column$1text_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_3);
                      _jspx_th_liferay$1ui_search$1container$1column$1text_5.setCssClass("table-cell-expand-smallest table-cell-minw-200");
                      _jspx_th_liferay$1ui_search$1container$1column$1text_5.setName("path");
                      _jspx_th_liferay$1ui_search$1container$1column$1text_5.setValue( JournalHelperUtil.getAbsolutePath(liferayPortletRequest, curArticle.getFolderId()) );
                      int _jspx_eval_liferay$1ui_search$1container$1column$1text_5 = _jspx_th_liferay$1ui_search$1container$1column$1text_5.doStartTag();
                      if (_jspx_th_liferay$1ui_search$1container$1column$1text_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_cssClass_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_5);
                        return;
                      }
                      _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_cssClass_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_5);
                      out.write("\n\t\t\t\t\t\t");
                    }
                    if (_jspx_th_c_if_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_3);
                      return;
                    }
                    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_3);
                    out.write("\n\n\t\t\t\t\t\t");
                    //  liferay-ui:search-container-column-text
                    com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_6 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_cssClass_nobody.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
                    _jspx_th_liferay$1ui_search$1container$1column$1text_6.setPageContext(_jspx_page_context);
                    _jspx_th_liferay$1ui_search$1container$1column$1text_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_1);
                    _jspx_th_liferay$1ui_search$1container$1column$1text_6.setCssClass("table-cell-expand-smallest table-cell-minw-100");
                    _jspx_th_liferay$1ui_search$1container$1column$1text_6.setName("author");
                    _jspx_th_liferay$1ui_search$1container$1column$1text_6.setValue( HtmlUtil.escape(PortalUtil.getUserName(curArticle)) );
                    int _jspx_eval_liferay$1ui_search$1container$1column$1text_6 = _jspx_th_liferay$1ui_search$1container$1column$1text_6.doStartTag();
                    if (_jspx_th_liferay$1ui_search$1container$1column$1text_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_cssClass_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_6);
                      return;
                    }
                    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_cssClass_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_6);
                    out.write("\n\n\t\t\t\t\t\t");
                    if (_jspx_meth_liferay$1ui_search$1container$1column$1status_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_otherwise_1, _jspx_page_context))
                      return;
                    out.write("\n\n\t\t\t\t\t\t");
                    //  liferay-ui:search-container-column-date
                    com.liferay.taglib.ui.SearchContainerColumnDateTag _jspx_th_liferay$1ui_search$1container$1column$1date_0 = (com.liferay.taglib.ui.SearchContainerColumnDateTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1date_value_name_cssClass_nobody.get(com.liferay.taglib.ui.SearchContainerColumnDateTag.class);
                    _jspx_th_liferay$1ui_search$1container$1column$1date_0.setPageContext(_jspx_page_context);
                    _jspx_th_liferay$1ui_search$1container$1column$1date_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_1);
                    _jspx_th_liferay$1ui_search$1container$1column$1date_0.setCssClass("table-cell-expand-smallest table-cell-ws-nowrap");
                    _jspx_th_liferay$1ui_search$1container$1column$1date_0.setName("modified-date");
                    _jspx_th_liferay$1ui_search$1container$1column$1date_0.setValue( curArticle.getModifiedDate() );
                    int _jspx_eval_liferay$1ui_search$1container$1column$1date_0 = _jspx_th_liferay$1ui_search$1container$1column$1date_0.doStartTag();
                    if (_jspx_th_liferay$1ui_search$1container$1column$1date_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_liferay$1ui_search$1container$1column$1date_value_name_cssClass_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1date_0);
                      return;
                    }
                    _jspx_tagPool_liferay$1ui_search$1container$1column$1date_value_name_cssClass_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1date_0);
                    out.write("\n\n\t\t\t\t\t\t");
                    //  liferay-ui:search-container-column-date
                    com.liferay.taglib.ui.SearchContainerColumnDateTag _jspx_th_liferay$1ui_search$1container$1column$1date_1 = (com.liferay.taglib.ui.SearchContainerColumnDateTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1date_value_name_cssClass_nobody.get(com.liferay.taglib.ui.SearchContainerColumnDateTag.class);
                    _jspx_th_liferay$1ui_search$1container$1column$1date_1.setPageContext(_jspx_page_context);
                    _jspx_th_liferay$1ui_search$1container$1column$1date_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_1);
                    _jspx_th_liferay$1ui_search$1container$1column$1date_1.setCssClass("table-cell-expand-smallest table-cell-ws-nowrap");
                    _jspx_th_liferay$1ui_search$1container$1column$1date_1.setName("display-date");
                    _jspx_th_liferay$1ui_search$1container$1column$1date_1.setValue( curArticle.getDisplayDate() );
                    int _jspx_eval_liferay$1ui_search$1container$1column$1date_1 = _jspx_th_liferay$1ui_search$1container$1column$1date_1.doStartTag();
                    if (_jspx_th_liferay$1ui_search$1container$1column$1date_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_liferay$1ui_search$1container$1column$1date_value_name_cssClass_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1date_1);
                      return;
                    }
                    _jspx_tagPool_liferay$1ui_search$1container$1column$1date_value_name_cssClass_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1date_1);
                    out.write("\n\n\t\t\t\t\t\t");

						DDMStructure ddmStructure = DDMStructureLocalServiceUtil.getStructure(scopeGroupId, PortalUtil.getClassNameId(JournalArticle.class), curArticle.getDDMStructureKey(), true);
						
                    out.write("\n\n\t\t\t\t\t\t");
                    //  liferay-ui:search-container-column-text
                    com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_7 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_cssClass_nobody.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
                    _jspx_th_liferay$1ui_search$1container$1column$1text_7.setPageContext(_jspx_page_context);
                    _jspx_th_liferay$1ui_search$1container$1column$1text_7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_1);
                    _jspx_th_liferay$1ui_search$1container$1column$1text_7.setCssClass("table-cell-expand-smallest table-cell-minw-100");
                    _jspx_th_liferay$1ui_search$1container$1column$1text_7.setName("type");
                    _jspx_th_liferay$1ui_search$1container$1column$1text_7.setValue( HtmlUtil.escape(ddmStructure.getName(locale)) );
                    int _jspx_eval_liferay$1ui_search$1container$1column$1text_7 = _jspx_th_liferay$1ui_search$1container$1column$1text_7.doStartTag();
                    if (_jspx_th_liferay$1ui_search$1container$1column$1text_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_cssClass_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_7);
                      return;
                    }
                    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_cssClass_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_7);
                    out.write("\n\n\t\t\t\t\t\t");
                    //  c:if
                    com.liferay.taglib.core.IfTag _jspx_th_c_if_4 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                    _jspx_th_c_if_4.setPageContext(_jspx_page_context);
                    _jspx_th_c_if_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_1);
                    _jspx_th_c_if_4.setTest( journalDisplayContext.isShowEditActions() );
                    int _jspx_eval_c_if_4 = _jspx_th_c_if_4.doStartTag();
                    if (_jspx_eval_c_if_4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      out.write("\n\t\t\t\t\t\t\t");
                      if (_jspx_meth_liferay$1ui_search$1container$1column$1jsp_2((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_4, _jspx_page_context))
                        return;
                      out.write("\n\t\t\t\t\t\t");
                    }
                    if (_jspx_th_c_if_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_4);
                      return;
                    }
                    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_4);
                    out.write("\n\t\t\t\t\t");
                  }
                  if (_jspx_th_c_otherwise_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_1);
                    return;
                  }
                  _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_1);
                  out.write("\n\t\t\t\t");
                }
                if (_jspx_th_c_choose_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_1);
                  return;
                }
                _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_1);
                out.write("\n\t\t\t");
              }
              if (_jspx_th_c_when_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_0);
                return;
              }
              _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_0);
              out.write("\n\t\t\t");
              //  c:when
              com.liferay.taglib.core.WhenTag _jspx_th_c_when_4 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
              _jspx_th_c_when_4.setPageContext(_jspx_page_context);
              _jspx_th_c_when_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_0);
              _jspx_th_c_when_4.setTest( curFolder != null );
              int _jspx_eval_c_when_4 = _jspx_th_c_when_4.doStartTag();
              if (_jspx_eval_c_when_4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\n\t\t\t\t");

				Map<String, Object> rowData = new HashMap<String, Object>();

				rowData.put("draggable", JournalFolderPermission.contains(permissionChecker, curFolder, ActionKeys.DELETE) || JournalFolderPermission.contains(permissionChecker, curFolder, ActionKeys.UPDATE));
				rowData.put("folder", true);
				rowData.put("folder-id", curFolder.getFolderId());
				rowData.put("title", HtmlUtil.escape(curFolder.getName()));

				row.setData(rowData);
				row.setPrimaryKey(String.valueOf(curFolder.getPrimaryKey()));

				PortletURL rowURL = liferayPortletResponse.createRenderURL();

				rowURL.setParameter("redirect", currentURL);
				rowURL.setParameter("groupId", String.valueOf(curFolder.getGroupId()));
				rowURL.setParameter("folderId", String.valueOf(curFolder.getFolderId()));
				rowURL.setParameter("displayStyle", displayStyle);
				rowURL.setParameter("showEditActions", String.valueOf(journalDisplayContext.isShowEditActions()));
				
                out.write("\n\n\t\t\t\t");
                //  c:choose
                com.liferay.taglib.core.ChooseTag _jspx_th_c_choose_3 = (com.liferay.taglib.core.ChooseTag) _jspx_tagPool_c_choose.get(com.liferay.taglib.core.ChooseTag.class);
                _jspx_th_c_choose_3.setPageContext(_jspx_page_context);
                _jspx_th_c_choose_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_4);
                int _jspx_eval_c_choose_3 = _jspx_th_c_choose_3.doStartTag();
                if (_jspx_eval_c_choose_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  out.write("\n\t\t\t\t\t");
                  //  c:when
                  com.liferay.taglib.core.WhenTag _jspx_th_c_when_5 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
                  _jspx_th_c_when_5.setPageContext(_jspx_page_context);
                  _jspx_th_c_when_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_3);
                  _jspx_th_c_when_5.setTest( displayStyle.equals("descriptive") );
                  int _jspx_eval_c_when_5 = _jspx_th_c_when_5.doStartTag();
                  if (_jspx_eval_c_when_5 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    out.write("\n\t\t\t\t\t\t");
                    //  liferay-ui:search-container-column-icon
                    com.liferay.taglib.ui.SearchContainerColumnIconTag _jspx_th_liferay$1ui_search$1container$1column$1icon_0 = (com.liferay.taglib.ui.SearchContainerColumnIconTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1icon_toggleRowChecker_icon_nobody.get(com.liferay.taglib.ui.SearchContainerColumnIconTag.class);
                    _jspx_th_liferay$1ui_search$1container$1column$1icon_0.setPageContext(_jspx_page_context);
                    _jspx_th_liferay$1ui_search$1container$1column$1icon_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_5);
                    _jspx_th_liferay$1ui_search$1container$1column$1icon_0.setIcon("folder");
                    _jspx_th_liferay$1ui_search$1container$1column$1icon_0.setToggleRowChecker( true );
                    int _jspx_eval_liferay$1ui_search$1container$1column$1icon_0 = _jspx_th_liferay$1ui_search$1container$1column$1icon_0.doStartTag();
                    if (_jspx_th_liferay$1ui_search$1container$1column$1icon_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_liferay$1ui_search$1container$1column$1icon_toggleRowChecker_icon_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1icon_0);
                      return;
                    }
                    _jspx_tagPool_liferay$1ui_search$1container$1column$1icon_toggleRowChecker_icon_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1icon_0);
                    out.write("\n\n\t\t\t\t\t\t");
                    //  liferay-ui:search-container-column-text
                    com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_8 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text_colspan.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
                    _jspx_th_liferay$1ui_search$1container$1column$1text_8.setPageContext(_jspx_page_context);
                    _jspx_th_liferay$1ui_search$1container$1column$1text_8.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_5);
                    _jspx_th_liferay$1ui_search$1container$1column$1text_8.setColspan( 2 );
                    int _jspx_eval_liferay$1ui_search$1container$1column$1text_8 = _jspx_th_liferay$1ui_search$1container$1column$1text_8.doStartTag();
                    if (_jspx_eval_liferay$1ui_search$1container$1column$1text_8 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      if (_jspx_eval_liferay$1ui_search$1container$1column$1text_8 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                        out = _jspx_page_context.pushBody();
                        _jspx_th_liferay$1ui_search$1container$1column$1text_8.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                        _jspx_th_liferay$1ui_search$1container$1column$1text_8.doInitBody();
                      }
                      do {
                        out.write("\n\n\t\t\t\t\t\t\t");

							Date createDate = curFolder.getCreateDate();

							String createDateDescription = LanguageUtil.getTimeDescription(request, System.currentTimeMillis() - createDate.getTime(), true);
							
                        out.write("\n\n\t\t\t\t\t\t\t<h6 class=\"text-default\">\n\t\t\t\t\t\t\t\t");
                        //  liferay-ui:message
                        com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_3 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_arguments_nobody.get(com.liferay.taglib.ui.MessageTag.class);
                        _jspx_th_liferay$1ui_message_3.setPageContext(_jspx_page_context);
                        _jspx_th_liferay$1ui_message_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1column$1text_8);
                        _jspx_th_liferay$1ui_message_3.setArguments( new String[] {HtmlUtil.escape(curFolder.getUserName()), createDateDescription} );
                        _jspx_th_liferay$1ui_message_3.setKey("x-modified-x-ago");
                        int _jspx_eval_liferay$1ui_message_3 = _jspx_th_liferay$1ui_message_3.doStartTag();
                        if (_jspx_th_liferay$1ui_message_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                          _jspx_tagPool_liferay$1ui_message_key_arguments_nobody.reuse(_jspx_th_liferay$1ui_message_3);
                          return;
                        }
                        _jspx_tagPool_liferay$1ui_message_key_arguments_nobody.reuse(_jspx_th_liferay$1ui_message_3);
                        out.write("\n\t\t\t\t\t\t\t</h6>\n\n\t\t\t\t\t\t\t<h5>\n\t\t\t\t\t\t\t\t");
                        //  aui:a
                        com.liferay.taglib.aui.ATag _jspx_th_aui_a_1 = (com.liferay.taglib.aui.ATag) _jspx_tagPool_aui_a_href.get(com.liferay.taglib.aui.ATag.class);
                        _jspx_th_aui_a_1.setPageContext(_jspx_page_context);
                        _jspx_th_aui_a_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1column$1text_8);
                        _jspx_th_aui_a_1.setHref( (rowURL != null) ? rowURL.toString() : null );
                        int _jspx_eval_aui_a_1 = _jspx_th_aui_a_1.doStartTag();
                        if (_jspx_eval_aui_a_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                          out.write("\n\t\t\t\t\t\t\t\t\t");
                          out.print( HtmlUtil.escape(curFolder.getName()) );
                          out.write("\n\t\t\t\t\t\t\t\t");
                        }
                        if (_jspx_th_aui_a_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                          _jspx_tagPool_aui_a_href.reuse(_jspx_th_aui_a_1);
                          return;
                        }
                        _jspx_tagPool_aui_a_href.reuse(_jspx_th_aui_a_1);
                        out.write("\n\t\t\t\t\t\t\t</h5>\n\n\t\t\t\t\t\t\t<h6 class=\"text-default\">\n\t\t\t\t\t\t\t\t");
                        //  aui:workflow-status
                        com.liferay.taglib.aui.WorkflowStatusTag _jspx_th_aui_workflow$1status_3 = (com.liferay.taglib.aui.WorkflowStatusTag) _jspx_tagPool_aui_workflow$1status_status_showLabel_showIcon_markupView_nobody.get(com.liferay.taglib.aui.WorkflowStatusTag.class);
                        _jspx_th_aui_workflow$1status_3.setPageContext(_jspx_page_context);
                        _jspx_th_aui_workflow$1status_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1column$1text_8);
                        _jspx_th_aui_workflow$1status_3.setMarkupView("lexicon");
                        _jspx_th_aui_workflow$1status_3.setShowIcon( false );
                        _jspx_th_aui_workflow$1status_3.setShowLabel( false );
                        _jspx_th_aui_workflow$1status_3.setStatus( curFolder.getStatus() );
                        int _jspx_eval_aui_workflow$1status_3 = _jspx_th_aui_workflow$1status_3.doStartTag();
                        if (_jspx_th_aui_workflow$1status_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                          _jspx_tagPool_aui_workflow$1status_status_showLabel_showIcon_markupView_nobody.reuse(_jspx_th_aui_workflow$1status_3);
                          return;
                        }
                        _jspx_tagPool_aui_workflow$1status_status_showLabel_showIcon_markupView_nobody.reuse(_jspx_th_aui_workflow$1status_3);
                        out.write("\n\t\t\t\t\t\t\t</h6>\n\t\t\t\t\t\t");
                        int evalDoAfterBody = _jspx_th_liferay$1ui_search$1container$1column$1text_8.doAfterBody();
                        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                          break;
                      } while (true);
                      if (_jspx_eval_liferay$1ui_search$1container$1column$1text_8 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                        out = _jspx_page_context.popBody();
                    }
                    if (_jspx_th_liferay$1ui_search$1container$1column$1text_8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_liferay$1ui_search$1container$1column$1text_colspan.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_8);
                      return;
                    }
                    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_colspan.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_8);
                    out.write("\n\n\t\t\t\t\t\t");
                    //  c:if
                    com.liferay.taglib.core.IfTag _jspx_th_c_if_5 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                    _jspx_th_c_if_5.setPageContext(_jspx_page_context);
                    _jspx_th_c_if_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_5);
                    _jspx_th_c_if_5.setTest( journalDisplayContext.isShowEditActions() );
                    int _jspx_eval_c_if_5 = _jspx_th_c_if_5.doStartTag();
                    if (_jspx_eval_c_if_5 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      out.write("\n\t\t\t\t\t\t\t");
                      if (_jspx_meth_liferay$1ui_search$1container$1column$1jsp_3((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_5, _jspx_page_context))
                        return;
                      out.write("\n\t\t\t\t\t\t");
                    }
                    if (_jspx_th_c_if_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_5);
                      return;
                    }
                    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_5);
                    out.write("\n\t\t\t\t\t");
                  }
                  if (_jspx_th_c_when_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_5);
                    return;
                  }
                  _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_5);
                  out.write("\n\t\t\t\t\t");
                  //  c:when
                  com.liferay.taglib.core.WhenTag _jspx_th_c_when_6 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
                  _jspx_th_c_when_6.setPageContext(_jspx_page_context);
                  _jspx_th_c_when_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_3);
                  _jspx_th_c_when_6.setTest( displayStyle.equals("icon") );
                  int _jspx_eval_c_when_6 = _jspx_th_c_when_6.doStartTag();
                  if (_jspx_eval_c_when_6 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    out.write("\n\n\t\t\t\t\t\t");

						row.setCssClass("entry-card lfr-asset-folder " + row.getCssClass());
						
                    out.write("\n\n\t\t\t\t\t\t");
                    //  liferay-ui:search-container-column-text
                    com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_9 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text_colspan.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
                    _jspx_th_liferay$1ui_search$1container$1column$1text_9.setPageContext(_jspx_page_context);
                    _jspx_th_liferay$1ui_search$1container$1column$1text_9.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_6);
                    _jspx_th_liferay$1ui_search$1container$1column$1text_9.setColspan( 2 );
                    int _jspx_eval_liferay$1ui_search$1container$1column$1text_9 = _jspx_th_liferay$1ui_search$1container$1column$1text_9.doStartTag();
                    if (_jspx_eval_liferay$1ui_search$1container$1column$1text_9 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      if (_jspx_eval_liferay$1ui_search$1container$1column$1text_9 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                        out = _jspx_page_context.pushBody();
                        _jspx_th_liferay$1ui_search$1container$1column$1text_9.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                        _jspx_th_liferay$1ui_search$1container$1column$1text_9.doInitBody();
                      }
                      do {
                        out.write("\n\t\t\t\t\t\t\t");
                        //  liferay-frontend:horizontal-card
                        com.liferay.frontend.taglib.servlet.taglib.HorizontalCardTag _jspx_th_liferay$1frontend_horizontal$1card_0 = (com.liferay.frontend.taglib.servlet.taglib.HorizontalCardTag) _jspx_tagPool_liferay$1frontend_horizontal$1card_url_text_rowChecker_resultRow_actionJspServletContext_actionJsp.get(com.liferay.frontend.taglib.servlet.taglib.HorizontalCardTag.class);
                        _jspx_th_liferay$1frontend_horizontal$1card_0.setPageContext(_jspx_page_context);
                        _jspx_th_liferay$1frontend_horizontal$1card_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1column$1text_9);
                        _jspx_th_liferay$1frontend_horizontal$1card_0.setActionJsp( journalDisplayContext.isShowEditActions() ? "/folder_action.jsp" : null );
                        _jspx_th_liferay$1frontend_horizontal$1card_0.setActionJspServletContext( application );
                        _jspx_th_liferay$1frontend_horizontal$1card_0.setResultRow( row );
                        _jspx_th_liferay$1frontend_horizontal$1card_0.setRowChecker( articleSearchContainer.getRowChecker() );
                        _jspx_th_liferay$1frontend_horizontal$1card_0.setText( HtmlUtil.escape(curFolder.getName()) );
                        _jspx_th_liferay$1frontend_horizontal$1card_0.setUrl( rowURL.toString() );
                        int _jspx_eval_liferay$1frontend_horizontal$1card_0 = _jspx_th_liferay$1frontend_horizontal$1card_0.doStartTag();
                        if (_jspx_eval_liferay$1frontend_horizontal$1card_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                          out.write("\n\t\t\t\t\t\t\t\t");
                          if (_jspx_meth_liferay$1frontend_horizontal$1card$1col_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1frontend_horizontal$1card_0, _jspx_page_context))
                            return;
                          out.write("\n\t\t\t\t\t\t\t");
                        }
                        if (_jspx_th_liferay$1frontend_horizontal$1card_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                          _jspx_tagPool_liferay$1frontend_horizontal$1card_url_text_rowChecker_resultRow_actionJspServletContext_actionJsp.reuse(_jspx_th_liferay$1frontend_horizontal$1card_0);
                          return;
                        }
                        _jspx_tagPool_liferay$1frontend_horizontal$1card_url_text_rowChecker_resultRow_actionJspServletContext_actionJsp.reuse(_jspx_th_liferay$1frontend_horizontal$1card_0);
                        out.write("\n\t\t\t\t\t\t");
                        int evalDoAfterBody = _jspx_th_liferay$1ui_search$1container$1column$1text_9.doAfterBody();
                        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                          break;
                      } while (true);
                      if (_jspx_eval_liferay$1ui_search$1container$1column$1text_9 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                        out = _jspx_page_context.popBody();
                    }
                    if (_jspx_th_liferay$1ui_search$1container$1column$1text_9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_liferay$1ui_search$1container$1column$1text_colspan.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_9);
                      return;
                    }
                    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_colspan.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_9);
                    out.write("\n\t\t\t\t\t");
                  }
                  if (_jspx_th_c_when_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_6);
                    return;
                  }
                  _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_6);
                  out.write("\n\t\t\t\t\t");
                  //  c:otherwise
                  com.liferay.taglib.core.OtherwiseTag _jspx_th_c_otherwise_2 = (com.liferay.taglib.core.OtherwiseTag) _jspx_tagPool_c_otherwise.get(com.liferay.taglib.core.OtherwiseTag.class);
                  _jspx_th_c_otherwise_2.setPageContext(_jspx_page_context);
                  _jspx_th_c_otherwise_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_3);
                  int _jspx_eval_c_otherwise_2 = _jspx_th_c_otherwise_2.doStartTag();
                  if (_jspx_eval_c_otherwise_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    out.write("\n\t\t\t\t\t\t");
                    //  c:if
                    com.liferay.taglib.core.IfTag _jspx_th_c_if_6 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                    _jspx_th_c_if_6.setPageContext(_jspx_page_context);
                    _jspx_th_c_if_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_2);
                    _jspx_th_c_if_6.setTest( !journalWebConfiguration.journalArticleForceAutogenerateId() );
                    int _jspx_eval_c_if_6 = _jspx_th_c_if_6.doStartTag();
                    if (_jspx_eval_c_if_6 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      out.write("\n\t\t\t\t\t\t\t");
                      //  liferay-ui:search-container-column-text
                      com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_10 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_nobody.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
                      _jspx_th_liferay$1ui_search$1container$1column$1text_10.setPageContext(_jspx_page_context);
                      _jspx_th_liferay$1ui_search$1container$1column$1text_10.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_6);
                      _jspx_th_liferay$1ui_search$1container$1column$1text_10.setName("id");
                      _jspx_th_liferay$1ui_search$1container$1column$1text_10.setValue( HtmlUtil.escape(String.valueOf(curFolder.getFolderId())) );
                      int _jspx_eval_liferay$1ui_search$1container$1column$1text_10 = _jspx_th_liferay$1ui_search$1container$1column$1text_10.doStartTag();
                      if (_jspx_th_liferay$1ui_search$1container$1column$1text_10.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_10);
                        return;
                      }
                      _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_10);
                      out.write("\n\t\t\t\t\t\t");
                    }
                    if (_jspx_th_c_if_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_6);
                      return;
                    }
                    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_6);
                    out.write("\n\n\t\t\t\t\t\t");
                    //  liferay-ui:search-container-column-text
                    com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_11 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_href_cssClass_nobody.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
                    _jspx_th_liferay$1ui_search$1container$1column$1text_11.setPageContext(_jspx_page_context);
                    _jspx_th_liferay$1ui_search$1container$1column$1text_11.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_2);
                    _jspx_th_liferay$1ui_search$1container$1column$1text_11.setCssClass("table-cell-expand table-cell-minw-200 table-list-title");
                    _jspx_th_liferay$1ui_search$1container$1column$1text_11.setHref( rowURL.toString() );
                    _jspx_th_liferay$1ui_search$1container$1column$1text_11.setName("title");
                    _jspx_th_liferay$1ui_search$1container$1column$1text_11.setValue( HtmlUtil.escape(curFolder.getName()) );
                    int _jspx_eval_liferay$1ui_search$1container$1column$1text_11 = _jspx_th_liferay$1ui_search$1container$1column$1text_11.doStartTag();
                    if (_jspx_th_liferay$1ui_search$1container$1column$1text_11.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_href_cssClass_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_11);
                      return;
                    }
                    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_href_cssClass_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_11);
                    out.write("\n\n\t\t\t\t\t\t");
                    //  liferay-ui:search-container-column-text
                    com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_12 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_cssClass_nobody.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
                    _jspx_th_liferay$1ui_search$1container$1column$1text_12.setPageContext(_jspx_page_context);
                    _jspx_th_liferay$1ui_search$1container$1column$1text_12.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_2);
                    _jspx_th_liferay$1ui_search$1container$1column$1text_12.setCssClass("table-cell-expand table-cell-minw-200");
                    _jspx_th_liferay$1ui_search$1container$1column$1text_12.setName("description");
                    _jspx_th_liferay$1ui_search$1container$1column$1text_12.setValue( HtmlUtil.escape(curFolder.getDescription()) );
                    int _jspx_eval_liferay$1ui_search$1container$1column$1text_12 = _jspx_th_liferay$1ui_search$1container$1column$1text_12.doStartTag();
                    if (_jspx_th_liferay$1ui_search$1container$1column$1text_12.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_cssClass_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_12);
                      return;
                    }
                    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_cssClass_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_12);
                    out.write("\n\n\t\t\t\t\t\t");
                    //  liferay-ui:search-container-column-text
                    com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_13 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_cssClass_nobody.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
                    _jspx_th_liferay$1ui_search$1container$1column$1text_13.setPageContext(_jspx_page_context);
                    _jspx_th_liferay$1ui_search$1container$1column$1text_13.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_2);
                    _jspx_th_liferay$1ui_search$1container$1column$1text_13.setCssClass("table-cell-expand-smallest table-cell-minw-150");
                    _jspx_th_liferay$1ui_search$1container$1column$1text_13.setName("author");
                    _jspx_th_liferay$1ui_search$1container$1column$1text_13.setValue( HtmlUtil.escape(PortalUtil.getUserName(curFolder)) );
                    int _jspx_eval_liferay$1ui_search$1container$1column$1text_13 = _jspx_th_liferay$1ui_search$1container$1column$1text_13.doStartTag();
                    if (_jspx_th_liferay$1ui_search$1container$1column$1text_13.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_cssClass_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_13);
                      return;
                    }
                    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_cssClass_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_13);
                    out.write("\n\n\t\t\t\t\t\t");
                    if (_jspx_meth_liferay$1ui_search$1container$1column$1text_14((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_otherwise_2, _jspx_page_context))
                      return;
                    out.write("\n\n\t\t\t\t\t\t");
                    //  liferay-ui:search-container-column-date
                    com.liferay.taglib.ui.SearchContainerColumnDateTag _jspx_th_liferay$1ui_search$1container$1column$1date_2 = (com.liferay.taglib.ui.SearchContainerColumnDateTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1date_value_name_cssClass_nobody.get(com.liferay.taglib.ui.SearchContainerColumnDateTag.class);
                    _jspx_th_liferay$1ui_search$1container$1column$1date_2.setPageContext(_jspx_page_context);
                    _jspx_th_liferay$1ui_search$1container$1column$1date_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_2);
                    _jspx_th_liferay$1ui_search$1container$1column$1date_2.setCssClass("table-cell-expand-smallest table-cell-ws-nowrap");
                    _jspx_th_liferay$1ui_search$1container$1column$1date_2.setName("modified-date");
                    _jspx_th_liferay$1ui_search$1container$1column$1date_2.setValue( curFolder.getModifiedDate() );
                    int _jspx_eval_liferay$1ui_search$1container$1column$1date_2 = _jspx_th_liferay$1ui_search$1container$1column$1date_2.doStartTag();
                    if (_jspx_th_liferay$1ui_search$1container$1column$1date_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_liferay$1ui_search$1container$1column$1date_value_name_cssClass_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1date_2);
                      return;
                    }
                    _jspx_tagPool_liferay$1ui_search$1container$1column$1date_value_name_cssClass_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1date_2);
                    out.write("\n\n\t\t\t\t\t\t");
                    if (_jspx_meth_liferay$1ui_search$1container$1column$1text_15((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_otherwise_2, _jspx_page_context))
                      return;
                    out.write("\n\n\t\t\t\t\t\t");
                    //  liferay-ui:search-container-column-text
                    com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_16 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_cssClass_nobody.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
                    _jspx_th_liferay$1ui_search$1container$1column$1text_16.setPageContext(_jspx_page_context);
                    _jspx_th_liferay$1ui_search$1container$1column$1text_16.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_2);
                    _jspx_th_liferay$1ui_search$1container$1column$1text_16.setCssClass("table-cell-expand-smallest table-cell-minw-150");
                    _jspx_th_liferay$1ui_search$1container$1column$1text_16.setName("type");
                    _jspx_th_liferay$1ui_search$1container$1column$1text_16.setValue( LanguageUtil.get(request, "folder") );
                    int _jspx_eval_liferay$1ui_search$1container$1column$1text_16 = _jspx_th_liferay$1ui_search$1container$1column$1text_16.doStartTag();
                    if (_jspx_th_liferay$1ui_search$1container$1column$1text_16.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_cssClass_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_16);
                      return;
                    }
                    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_cssClass_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_16);
                    out.write("\n\n\t\t\t\t\t\t");
                    //  c:if
                    com.liferay.taglib.core.IfTag _jspx_th_c_if_7 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                    _jspx_th_c_if_7.setPageContext(_jspx_page_context);
                    _jspx_th_c_if_7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_2);
                    _jspx_th_c_if_7.setTest( journalDisplayContext.isShowEditActions() );
                    int _jspx_eval_c_if_7 = _jspx_th_c_if_7.doStartTag();
                    if (_jspx_eval_c_if_7 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      out.write("\n\t\t\t\t\t\t\t");
                      if (_jspx_meth_liferay$1ui_search$1container$1column$1jsp_4((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_7, _jspx_page_context))
                        return;
                      out.write("\n\t\t\t\t\t\t");
                    }
                    if (_jspx_th_c_if_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_7);
                      return;
                    }
                    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_7);
                    out.write("\n\t\t\t\t\t");
                  }
                  if (_jspx_th_c_otherwise_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_2);
                    return;
                  }
                  _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_2);
                  out.write("\n\t\t\t\t");
                }
                if (_jspx_th_c_choose_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_3);
                  return;
                }
                _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_3);
                out.write("\n\t\t\t");
              }
              if (_jspx_th_c_when_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_4);
                return;
              }
              _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_4);
              out.write("\n\t\t");
            }
            if (_jspx_th_c_choose_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_0);
              return;
            }
            _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_0);
            out.write('\n');
            out.write('	');
            int evalDoAfterBody = _jspx_th_liferay$1ui_search$1container$1row_0.doAfterBody();
            index = (java.lang.Integer) _jspx_page_context.findAttribute("index");
            object = (Object) _jspx_page_context.findAttribute("object");
            row = (com.liferay.portal.kernel.dao.search.ResultRow) _jspx_page_context.findAttribute("row");
            if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
              break;
          } while (true);
          if (_jspx_eval_liferay$1ui_search$1container$1row_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
            out = _jspx_page_context.popBody();
        }
        if (_jspx_th_liferay$1ui_search$1container$1row_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_liferay$1ui_search$1container$1row_modelVar_cssClass_className.reuse(_jspx_th_liferay$1ui_search$1container$1row_0);
          return;
        }
        _jspx_tagPool_liferay$1ui_search$1container$1row_modelVar_cssClass_className.reuse(_jspx_th_liferay$1ui_search$1container$1row_0);
        out.write("\n\n\t");
        //  liferay-ui:search-iterator
        com.liferay.taglib.ui.SearchIteratorTag _jspx_th_liferay$1ui_search$1iterator_0 = (com.liferay.taglib.ui.SearchIteratorTag) _jspx_tagPool_liferay$1ui_search$1iterator_searchContainer_resultRowSplitter_markupView_displayStyle_nobody.get(com.liferay.taglib.ui.SearchIteratorTag.class);
        _jspx_th_liferay$1ui_search$1iterator_0.setPageContext(_jspx_page_context);
        _jspx_th_liferay$1ui_search$1iterator_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container_0);
        _jspx_th_liferay$1ui_search$1iterator_0.setDisplayStyle( displayStyle );
        _jspx_th_liferay$1ui_search$1iterator_0.setMarkupView("lexicon");
        _jspx_th_liferay$1ui_search$1iterator_0.setResultRowSplitter( journalDisplayContext.isSearch() ? null : new JournalResultRowSplitter() );
        _jspx_th_liferay$1ui_search$1iterator_0.setSearchContainer( articleSearchContainer );
        int _jspx_eval_liferay$1ui_search$1iterator_0 = _jspx_th_liferay$1ui_search$1iterator_0.doStartTag();
        if (_jspx_th_liferay$1ui_search$1iterator_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_liferay$1ui_search$1iterator_searchContainer_resultRowSplitter_markupView_displayStyle_nobody.reuse(_jspx_th_liferay$1ui_search$1iterator_0);
          return;
        }
        _jspx_tagPool_liferay$1ui_search$1iterator_searchContainer_resultRowSplitter_markupView_displayStyle_nobody.reuse(_jspx_th_liferay$1ui_search$1iterator_0);
        out.write('\n');
      }
      if (_jspx_th_liferay$1ui_search$1container_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_liferay$1ui_search$1container_searchContainer_id_emptyResultsMessage.reuse(_jspx_th_liferay$1ui_search$1container_0);
        return;
      }
      _jspx_tagPool_liferay$1ui_search$1container_searchContainer_id_emptyResultsMessage.reuse(_jspx_th_liferay$1ui_search$1container_0);
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

  private boolean _jspx_meth_liferay$1ui_search$1container$1column$1jsp_0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:search-container-column-jsp
    com.liferay.taglib.ui.SearchContainerColumnJSPTag _jspx_th_liferay$1ui_search$1container$1column$1jsp_0 = (com.liferay.taglib.ui.SearchContainerColumnJSPTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1jsp_path_nobody.get(com.liferay.taglib.ui.SearchContainerColumnJSPTag.class);
    _jspx_th_liferay$1ui_search$1container$1column$1jsp_0.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_search$1container$1column$1jsp_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_1);
    _jspx_th_liferay$1ui_search$1container$1column$1jsp_0.setPath("/article_action.jsp");
    int _jspx_eval_liferay$1ui_search$1container$1column$1jsp_0 = _jspx_th_liferay$1ui_search$1container$1column$1jsp_0.doStartTag();
    if (_jspx_th_liferay$1ui_search$1container$1column$1jsp_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_search$1container$1column$1jsp_path_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1jsp_0);
      return true;
    }
    _jspx_tagPool_liferay$1ui_search$1container$1column$1jsp_path_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1jsp_0);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_search$1container$1column$1status_0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_otherwise_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:search-container-column-status
    com.liferay.taglib.ui.SearchContainerColumnStatusTag _jspx_th_liferay$1ui_search$1container$1column$1status_0 = (com.liferay.taglib.ui.SearchContainerColumnStatusTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1status_name_nobody.get(com.liferay.taglib.ui.SearchContainerColumnStatusTag.class);
    _jspx_th_liferay$1ui_search$1container$1column$1status_0.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_search$1container$1column$1status_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_1);
    _jspx_th_liferay$1ui_search$1container$1column$1status_0.setName("status");
    int _jspx_eval_liferay$1ui_search$1container$1column$1status_0 = _jspx_th_liferay$1ui_search$1container$1column$1status_0.doStartTag();
    if (_jspx_th_liferay$1ui_search$1container$1column$1status_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_search$1container$1column$1status_name_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1status_0);
      return true;
    }
    _jspx_tagPool_liferay$1ui_search$1container$1column$1status_name_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1status_0);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_search$1container$1column$1jsp_2(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:search-container-column-jsp
    com.liferay.taglib.ui.SearchContainerColumnJSPTag _jspx_th_liferay$1ui_search$1container$1column$1jsp_2 = (com.liferay.taglib.ui.SearchContainerColumnJSPTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1jsp_path_nobody.get(com.liferay.taglib.ui.SearchContainerColumnJSPTag.class);
    _jspx_th_liferay$1ui_search$1container$1column$1jsp_2.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_search$1container$1column$1jsp_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_4);
    _jspx_th_liferay$1ui_search$1container$1column$1jsp_2.setPath("/article_action.jsp");
    int _jspx_eval_liferay$1ui_search$1container$1column$1jsp_2 = _jspx_th_liferay$1ui_search$1container$1column$1jsp_2.doStartTag();
    if (_jspx_th_liferay$1ui_search$1container$1column$1jsp_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_search$1container$1column$1jsp_path_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1jsp_2);
      return true;
    }
    _jspx_tagPool_liferay$1ui_search$1container$1column$1jsp_path_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1jsp_2);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_search$1container$1column$1jsp_3(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_5, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:search-container-column-jsp
    com.liferay.taglib.ui.SearchContainerColumnJSPTag _jspx_th_liferay$1ui_search$1container$1column$1jsp_3 = (com.liferay.taglib.ui.SearchContainerColumnJSPTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1jsp_path_nobody.get(com.liferay.taglib.ui.SearchContainerColumnJSPTag.class);
    _jspx_th_liferay$1ui_search$1container$1column$1jsp_3.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_search$1container$1column$1jsp_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_5);
    _jspx_th_liferay$1ui_search$1container$1column$1jsp_3.setPath("/folder_action.jsp");
    int _jspx_eval_liferay$1ui_search$1container$1column$1jsp_3 = _jspx_th_liferay$1ui_search$1container$1column$1jsp_3.doStartTag();
    if (_jspx_th_liferay$1ui_search$1container$1column$1jsp_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_search$1container$1column$1jsp_path_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1jsp_3);
      return true;
    }
    _jspx_tagPool_liferay$1ui_search$1container$1column$1jsp_path_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1jsp_3);
    return false;
  }

  private boolean _jspx_meth_liferay$1frontend_horizontal$1card$1col_0(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1frontend_horizontal$1card_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-frontend:horizontal-card-col
    com.liferay.frontend.taglib.servlet.taglib.HorizontalCardColTag _jspx_th_liferay$1frontend_horizontal$1card$1col_0 = (com.liferay.frontend.taglib.servlet.taglib.HorizontalCardColTag) _jspx_tagPool_liferay$1frontend_horizontal$1card$1col.get(com.liferay.frontend.taglib.servlet.taglib.HorizontalCardColTag.class);
    _jspx_th_liferay$1frontend_horizontal$1card$1col_0.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1frontend_horizontal$1card$1col_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_horizontal$1card_0);
    int _jspx_eval_liferay$1frontend_horizontal$1card$1col_0 = _jspx_th_liferay$1frontend_horizontal$1card$1col_0.doStartTag();
    if (_jspx_eval_liferay$1frontend_horizontal$1card$1col_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_liferay$1frontend_horizontal$1card$1col_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_liferay$1frontend_horizontal$1card$1col_0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_liferay$1frontend_horizontal$1card$1col_0.doInitBody();
      }
      do {
        out.write("\n\t\t\t\t\t\t\t\t\t");
        if (_jspx_meth_liferay$1frontend_horizontal$1card$1icon_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1frontend_horizontal$1card$1col_0, _jspx_page_context))
          return true;
        out.write("\n\t\t\t\t\t\t\t\t");
        int evalDoAfterBody = _jspx_th_liferay$1frontend_horizontal$1card$1col_0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_liferay$1frontend_horizontal$1card$1col_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
        out = _jspx_page_context.popBody();
    }
    if (_jspx_th_liferay$1frontend_horizontal$1card$1col_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1frontend_horizontal$1card$1col.reuse(_jspx_th_liferay$1frontend_horizontal$1card$1col_0);
      return true;
    }
    _jspx_tagPool_liferay$1frontend_horizontal$1card$1col.reuse(_jspx_th_liferay$1frontend_horizontal$1card$1col_0);
    return false;
  }

  private boolean _jspx_meth_liferay$1frontend_horizontal$1card$1icon_0(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1frontend_horizontal$1card$1col_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-frontend:horizontal-card-icon
    com.liferay.frontend.taglib.servlet.taglib.HorizontalCardIconTag _jspx_th_liferay$1frontend_horizontal$1card$1icon_0 = (com.liferay.frontend.taglib.servlet.taglib.HorizontalCardIconTag) _jspx_tagPool_liferay$1frontend_horizontal$1card$1icon_icon_nobody.get(com.liferay.frontend.taglib.servlet.taglib.HorizontalCardIconTag.class);
    _jspx_th_liferay$1frontend_horizontal$1card$1icon_0.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1frontend_horizontal$1card$1icon_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_horizontal$1card$1col_0);
    _jspx_th_liferay$1frontend_horizontal$1card$1icon_0.setIcon("folder");
    int _jspx_eval_liferay$1frontend_horizontal$1card$1icon_0 = _jspx_th_liferay$1frontend_horizontal$1card$1icon_0.doStartTag();
    if (_jspx_th_liferay$1frontend_horizontal$1card$1icon_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1frontend_horizontal$1card$1icon_icon_nobody.reuse(_jspx_th_liferay$1frontend_horizontal$1card$1icon_0);
      return true;
    }
    _jspx_tagPool_liferay$1frontend_horizontal$1card$1icon_icon_nobody.reuse(_jspx_th_liferay$1frontend_horizontal$1card$1icon_0);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_search$1container$1column$1text_14(javax.servlet.jsp.tagext.JspTag _jspx_th_c_otherwise_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:search-container-column-text
    com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_14 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_nobody.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
    _jspx_th_liferay$1ui_search$1container$1column$1text_14.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_search$1container$1column$1text_14.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_2);
    _jspx_th_liferay$1ui_search$1container$1column$1text_14.setName("status");
    _jspx_th_liferay$1ui_search$1container$1column$1text_14.setValue("--");
    int _jspx_eval_liferay$1ui_search$1container$1column$1text_14 = _jspx_th_liferay$1ui_search$1container$1column$1text_14.doStartTag();
    if (_jspx_th_liferay$1ui_search$1container$1column$1text_14.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_14);
      return true;
    }
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_14);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_search$1container$1column$1text_15(javax.servlet.jsp.tagext.JspTag _jspx_th_c_otherwise_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:search-container-column-text
    com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_15 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_cssClass_nobody.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
    _jspx_th_liferay$1ui_search$1container$1column$1text_15.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_search$1container$1column$1text_15.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_2);
    _jspx_th_liferay$1ui_search$1container$1column$1text_15.setCssClass("table-cell-expand-smallest table-cell-ws-nowrap");
    _jspx_th_liferay$1ui_search$1container$1column$1text_15.setName("display-date");
    _jspx_th_liferay$1ui_search$1container$1column$1text_15.setValue("--");
    int _jspx_eval_liferay$1ui_search$1container$1column$1text_15 = _jspx_th_liferay$1ui_search$1container$1column$1text_15.doStartTag();
    if (_jspx_th_liferay$1ui_search$1container$1column$1text_15.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_cssClass_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_15);
      return true;
    }
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_cssClass_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_15);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_search$1container$1column$1jsp_4(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_7, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:search-container-column-jsp
    com.liferay.taglib.ui.SearchContainerColumnJSPTag _jspx_th_liferay$1ui_search$1container$1column$1jsp_4 = (com.liferay.taglib.ui.SearchContainerColumnJSPTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1jsp_path_nobody.get(com.liferay.taglib.ui.SearchContainerColumnJSPTag.class);
    _jspx_th_liferay$1ui_search$1container$1column$1jsp_4.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_search$1container$1column$1jsp_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_7);
    _jspx_th_liferay$1ui_search$1container$1column$1jsp_4.setPath("/folder_action.jsp");
    int _jspx_eval_liferay$1ui_search$1container$1column$1jsp_4 = _jspx_th_liferay$1ui_search$1container$1column$1jsp_4.doStartTag();
    if (_jspx_th_liferay$1ui_search$1container$1column$1jsp_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_search$1container$1column$1jsp_path_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1jsp_4);
      return true;
    }
    _jspx_tagPool_liferay$1ui_search$1container$1column$1jsp_path_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1jsp_4);
    return false;
  }
}
