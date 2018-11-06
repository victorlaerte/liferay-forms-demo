package org.apache.jsp.admin.common;

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
import com.liferay.document.library.configuration.DLConfiguration;
import com.liferay.document.library.kernel.util.DLValidatorUtil;

public final class article_005fsuggestions_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(2);
    _jspx_dependants.add("/admin/common/init.jsp");
    _jspx_dependants.add("/init.jsp");
  }

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_message_key_arguments_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_param_value_name_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1portlet_actionURL_var_name;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_if_test;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_button$1row_cssClass;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_button_value_type_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container$1column$1text_orderable_name_cssClass;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1portlet_renderURL_varImpl;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container$1row_modelVar_className;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_choose;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1frontend_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container$1column$1date_value_orderableProperty_orderable_name_cssClass_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1util_include_servletContext_page_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_fieldset;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_success_message_key_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_when_test;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_namespace_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container_total_orderByComparator_iteratorURL_emptyResultsMessage;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_message_key_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_model$1context_model_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1iterator_markupView_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_input_value_type_name_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container$1results_results_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_input_name_label_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_otherwise;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_error_message_exception_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_form_name_method_action;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1theme_defineObjects_nobody;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_liferay$1ui_message_key_arguments_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_param_value_name_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1portlet_actionURL_var_name = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_if_test = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_button$1row_cssClass = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_button_value_type_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_orderable_name_cssClass = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1portlet_renderURL_varImpl = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container$1row_modelVar_className = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_choose = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1frontend_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container$1column$1date_value_orderableProperty_orderable_name_cssClass_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1util_include_servletContext_page_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_fieldset = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_success_message_key_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_when_test = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_namespace_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container_total_orderByComparator_iteratorURL_emptyResultsMessage = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_message_key_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_model$1context_model_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1iterator_markupView_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_input_value_type_name_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container$1results_results_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_input_name_label_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_otherwise = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_error_message_exception_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_form_name_method_action = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1theme_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_liferay$1ui_message_key_arguments_nobody.release();
    _jspx_tagPool_portlet_param_value_name_nobody.release();
    _jspx_tagPool_liferay$1portlet_actionURL_var_name.release();
    _jspx_tagPool_c_if_test.release();
    _jspx_tagPool_aui_button$1row_cssClass.release();
    _jspx_tagPool_aui_button_value_type_nobody.release();
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_orderable_name_cssClass.release();
    _jspx_tagPool_liferay$1portlet_renderURL_varImpl.release();
    _jspx_tagPool_liferay$1ui_search$1container$1row_modelVar_className.release();
    _jspx_tagPool_c_choose.release();
    _jspx_tagPool_liferay$1frontend_defineObjects_nobody.release();
    _jspx_tagPool_liferay$1ui_search$1container$1column$1date_value_orderableProperty_orderable_name_cssClass_nobody.release();
    _jspx_tagPool_liferay$1util_include_servletContext_page_nobody.release();
    _jspx_tagPool_aui_fieldset.release();
    _jspx_tagPool_liferay$1ui_success_message_key_nobody.release();
    _jspx_tagPool_c_when_test.release();
    _jspx_tagPool_portlet_namespace_nobody.release();
    _jspx_tagPool_liferay$1ui_search$1container_total_orderByComparator_iteratorURL_emptyResultsMessage.release();
    _jspx_tagPool_liferay$1ui_message_key_nobody.release();
    _jspx_tagPool_aui_model$1context_model_nobody.release();
    _jspx_tagPool_liferay$1ui_search$1iterator_markupView_nobody.release();
    _jspx_tagPool_aui_input_value_type_name_nobody.release();
    _jspx_tagPool_liferay$1ui_search$1container$1results_results_nobody.release();
    _jspx_tagPool_aui_input_name_label_nobody.release();
    _jspx_tagPool_c_otherwise.release();
    _jspx_tagPool_portlet_defineObjects_nobody.release();
    _jspx_tagPool_liferay$1ui_error_message_exception_nobody.release();
    _jspx_tagPool_aui_form_name_method_action.release();
    _jspx_tagPool_liferay$1theme_defineObjects_nobody.release();
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

      out.write("\n\n\n\n");

long kbFolderClassNameId = PortalUtil.getClassNameId(KBFolderConstants.getClassName());

long resourceClassNameId = GetterUtil.getLong(request.getAttribute("init.jsp-resourceClassNameId"));

if (resourceClassNameId == 0) {
	resourceClassNameId = kbFolderClassNameId;
}

long resourcePrimKey = GetterUtil.getLong(request.getAttribute("init.jsp-resourcePrimKey"));

boolean enableKBArticleDescription = GetterUtil.getBoolean(request.getAttribute("init.jsp-enableKBArticleDescription"));
boolean enableKBArticleRatings = GetterUtil.getBoolean(request.getAttribute("init.jsp-enableKBArticleRatings"));
boolean showKBArticleAssetEntries = GetterUtil.getBoolean(request.getAttribute("init.jsp-showKBArticleAssetEntries"));
boolean showKBArticleAttachments = GetterUtil.getBoolean(request.getAttribute("init.jsp-showKBArticleAttachments"));
boolean enableKBArticleAssetLinks = GetterUtil.getBoolean(request.getAttribute("init.jsp-enableKBArticleAssetLinks"));
boolean enableKBArticleViewCountIncrement = GetterUtil.getBoolean(request.getAttribute("init.jsp-enableKBArticleViewCountIncrement"));
boolean enableKBArticleSubscriptions = GetterUtil.getBoolean(request.getAttribute("init.jsp-enableKBArticleSubscriptions"));
boolean enableKBArticleHistory = GetterUtil.getBoolean(request.getAttribute("init.jsp-enableKBArticleHistory"));
boolean enableKBArticlePrint = GetterUtil.getBoolean(request.getAttribute("init.jsp-enableKBArticlePrint"));
String socialBookmarksDisplayStyle = GetterUtil.getString(request.getAttribute("init.jsp-socialBookmarksDisplayStyle"));
String socialBookmarksTypes = GetterUtil.getString(request.getAttribute("init.jsp-socialBookmarksTypes"), null);

boolean enableRSS = kbGroupServiceConfiguration.enableRSS();
int rssDelta = kbGroupServiceConfiguration.rssDelta();
String rssDisplayStyle = kbGroupServiceConfiguration.rssDisplayStyle();
String rssFeedType = kbGroupServiceConfiguration.rssFeedType();

      out.write('\n');
      out.write('\n');

KBArticle kbArticle = (KBArticle)request.getAttribute(KBWebKeys.KNOWLEDGE_BASE_KB_ARTICLE);

boolean showAdminSuggestionView = false;

if (AdminPermission.contains(permissionChecker, scopeGroupId, KBActionKeys.VIEW_SUGGESTIONS) || KBArticlePermission.contains(permissionChecker, kbArticle, KBActionKeys.UPDATE)) {
	showAdminSuggestionView = true;
}

KBArticleURLHelper kbArticleURLHelper = new KBArticleURLHelper(renderRequest, renderResponse, templatePath);

int kbCommentsCount = 0;
int pendingKBCommentsCount = 0;

if (showAdminSuggestionView) {
	kbCommentsCount = KBCommentLocalServiceUtil.getKBCommentsCount(KBArticle.class.getName(), kbArticle.getResourcePrimKey());
	pendingKBCommentsCount = KBCommentLocalServiceUtil.getKBCommentsCount(KBArticle.class.getName(), kbArticle.getResourcePrimKey(), new int[] {KBCommentConstants.STATUS_IN_PROGRESS, KBCommentConstants.STATUS_NEW});
}
else {
	kbCommentsCount = KBCommentLocalServiceUtil.getKBCommentsCount(themeDisplay.getUserId(), KBArticle.class.getName(), kbArticle.getResourcePrimKey());
}

RatingsType ratingsType = PortletRatingsDefinitionUtil.getRatingsType(themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId(), KBArticle.class.getName());

if (ratingsType == null) {
	ratingsType = RatingsType.THUMBS;
}

      out.write('\n');
      out.write('\n');
      //  c:if
      com.liferay.taglib.core.IfTag _jspx_th_c_if_0 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
      _jspx_th_c_if_0.setPageContext(_jspx_page_context);
      _jspx_th_c_if_0.setParent(null);
      _jspx_th_c_if_0.setTest( ratingsType.equals(RatingsType.THUMBS) && themeDisplay.isSignedIn() );
      int _jspx_eval_c_if_0 = _jspx_th_c_if_0.doStartTag();
      if (_jspx_eval_c_if_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        out.write("\n\t<h5>");
        if (_jspx_meth_liferay$1ui_message_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_0, _jspx_page_context))
          return;
        out.write("</h5>\n\n\t<a name=\"kbSuggestions\"></a>\n\n\t<div id=\"");
        if (_jspx_meth_portlet_namespace_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_0, _jspx_page_context))
          return;
        out.write("suggestionContainer\">\n\n\t\t");

		PortletURL viewKBArticleURL = kbArticleURLHelper.createViewWithCommentsURL(kbArticle);
		
        out.write("\n\n\t\t");
        //  liferay-portlet:actionURL
        com.liferay.taglib.portlet.ActionURLTag _jspx_th_liferay$1portlet_actionURL_0 = (com.liferay.taglib.portlet.ActionURLTag) _jspx_tagPool_liferay$1portlet_actionURL_var_name.get(com.liferay.taglib.portlet.ActionURLTag.class);
        _jspx_th_liferay$1portlet_actionURL_0.setPageContext(_jspx_page_context);
        _jspx_th_liferay$1portlet_actionURL_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_0);
        _jspx_th_liferay$1portlet_actionURL_0.setName("updateKBComment");
        _jspx_th_liferay$1portlet_actionURL_0.setVar("updateKBCommentURL");
        int _jspx_eval_liferay$1portlet_actionURL_0 = _jspx_th_liferay$1portlet_actionURL_0.doStartTag();
        if (_jspx_eval_liferay$1portlet_actionURL_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          out.write("\n\t\t\t");
          //  portlet:param
          com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_0 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
          _jspx_th_portlet_param_0.setPageContext(_jspx_page_context);
          _jspx_th_portlet_param_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_actionURL_0);
          _jspx_th_portlet_param_0.setName("redirect");
          _jspx_th_portlet_param_0.setValue( viewKBArticleURL.toString() );
          int _jspx_eval_portlet_param_0 = _jspx_th_portlet_param_0.doStartTag();
          if (_jspx_th_portlet_param_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_0);
            return;
          }
          _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_0);
          out.write("\n\t\t");
        }
        if (_jspx_th_liferay$1portlet_actionURL_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_liferay$1portlet_actionURL_var_name.reuse(_jspx_th_liferay$1portlet_actionURL_0);
          return;
        }
        _jspx_tagPool_liferay$1portlet_actionURL_var_name.reuse(_jspx_th_liferay$1portlet_actionURL_0);
        java.lang.String updateKBCommentURL = null;
        updateKBCommentURL = (java.lang.String) _jspx_page_context.findAttribute("updateKBCommentURL");
        out.write("\n\n\t\t");
        //  aui:form
        com.liferay.taglib.aui.FormTag _jspx_th_aui_form_0 = (com.liferay.taglib.aui.FormTag) _jspx_tagPool_aui_form_name_method_action.get(com.liferay.taglib.aui.FormTag.class);
        _jspx_th_aui_form_0.setPageContext(_jspx_page_context);
        _jspx_th_aui_form_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_0);
        _jspx_th_aui_form_0.setAction( updateKBCommentURL + "#kbSuggestions" );
        _jspx_th_aui_form_0.setMethod("post");
        _jspx_th_aui_form_0.setName("suggestionFm");
        int _jspx_eval_aui_form_0 = _jspx_th_aui_form_0.doStartTag();
        if (_jspx_eval_aui_form_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          out.write("\n\t\t\t");
          //  aui:input
          com.liferay.taglib.aui.InputTag _jspx_th_aui_input_0 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
          _jspx_th_aui_input_0.setPageContext(_jspx_page_context);
          _jspx_th_aui_input_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
          _jspx_th_aui_input_0.setName( Constants.CMD );
          _jspx_th_aui_input_0.setType("hidden");
          _jspx_th_aui_input_0.setValue( Constants.ADD );
          int _jspx_eval_aui_input_0 = _jspx_th_aui_input_0.doStartTag();
          if (_jspx_th_aui_input_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_0);
            return;
          }
          _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_0);
          out.write("\n\t\t\t");
          //  aui:input
          com.liferay.taglib.aui.InputTag _jspx_th_aui_input_1 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
          _jspx_th_aui_input_1.setPageContext(_jspx_page_context);
          _jspx_th_aui_input_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
          _jspx_th_aui_input_1.setName("classNameId");
          _jspx_th_aui_input_1.setType("hidden");
          _jspx_th_aui_input_1.setValue( PortalUtil.getClassNameId(KBArticle.class) );
          int _jspx_eval_aui_input_1 = _jspx_th_aui_input_1.doStartTag();
          if (_jspx_th_aui_input_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_1);
            return;
          }
          _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_1);
          out.write("\n\t\t\t");
          //  aui:input
          com.liferay.taglib.aui.InputTag _jspx_th_aui_input_2 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
          _jspx_th_aui_input_2.setPageContext(_jspx_page_context);
          _jspx_th_aui_input_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
          _jspx_th_aui_input_2.setName("classPK");
          _jspx_th_aui_input_2.setType("hidden");
          _jspx_th_aui_input_2.setValue( kbArticle.getResourcePrimKey() );
          int _jspx_eval_aui_input_2 = _jspx_th_aui_input_2.doStartTag();
          if (_jspx_th_aui_input_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_2);
            return;
          }
          _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_2);
          out.write("\n\n\t\t\t");
          //  liferay-ui:error
          com.liferay.taglib.ui.ErrorTag _jspx_th_liferay$1ui_error_0 = (com.liferay.taglib.ui.ErrorTag) _jspx_tagPool_liferay$1ui_error_message_exception_nobody.get(com.liferay.taglib.ui.ErrorTag.class);
          _jspx_th_liferay$1ui_error_0.setPageContext(_jspx_page_context);
          _jspx_th_liferay$1ui_error_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
          _jspx_th_liferay$1ui_error_0.setException( KBCommentContentException.class );
          _jspx_th_liferay$1ui_error_0.setMessage("please-enter-valid-content");
          int _jspx_eval_liferay$1ui_error_0 = _jspx_th_liferay$1ui_error_0.doStartTag();
          if (_jspx_th_liferay$1ui_error_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_liferay$1ui_error_message_exception_nobody.reuse(_jspx_th_liferay$1ui_error_0);
            return;
          }
          _jspx_tagPool_liferay$1ui_error_message_exception_nobody.reuse(_jspx_th_liferay$1ui_error_0);
          out.write("\n\n\t\t\t");
          //  aui:model-context
          com.liferay.taglib.aui.ModelContextTag _jspx_th_aui_model$1context_0 = (com.liferay.taglib.aui.ModelContextTag) _jspx_tagPool_aui_model$1context_model_nobody.get(com.liferay.taglib.aui.ModelContextTag.class);
          _jspx_th_aui_model$1context_0.setPageContext(_jspx_page_context);
          _jspx_th_aui_model$1context_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
          _jspx_th_aui_model$1context_0.setModel( KBComment.class );
          int _jspx_eval_aui_model$1context_0 = _jspx_th_aui_model$1context_0.doStartTag();
          if (_jspx_th_aui_model$1context_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_aui_model$1context_model_nobody.reuse(_jspx_th_aui_model$1context_0);
            return;
          }
          _jspx_tagPool_aui_model$1context_model_nobody.reuse(_jspx_th_aui_model$1context_0);
          out.write("\n\n\t\t\t");
          if (_jspx_meth_aui_fieldset_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_form_0, _jspx_page_context))
            return;
          out.write("\n\t\t");
        }
        if (_jspx_th_aui_form_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_aui_form_name_method_action.reuse(_jspx_th_aui_form_0);
          return;
        }
        _jspx_tagPool_aui_form_name_method_action.reuse(_jspx_th_aui_form_0);
        out.write("\n\t</div>\n\n\t");
        if (_jspx_meth_liferay$1ui_success_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_0, _jspx_page_context))
          return;
        out.write("\n\n\t");
        if (_jspx_meth_liferay$1ui_success_1((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_0, _jspx_page_context))
          return;
        out.write("\n\n\t");
        if (_jspx_meth_liferay$1ui_success_2((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_0, _jspx_page_context))
          return;
        out.write("\n\n\t");
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
          _jspx_th_c_when_0.setTest( kbCommentsCount == 1 );
          int _jspx_eval_c_when_0 = _jspx_th_c_when_0.doStartTag();
          if (_jspx_eval_c_when_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t\t");
            //  c:choose
            com.liferay.taglib.core.ChooseTag _jspx_th_c_choose_1 = (com.liferay.taglib.core.ChooseTag) _jspx_tagPool_c_choose.get(com.liferay.taglib.core.ChooseTag.class);
            _jspx_th_c_choose_1.setPageContext(_jspx_page_context);
            _jspx_th_c_choose_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_0);
            int _jspx_eval_c_choose_1 = _jspx_th_c_choose_1.doStartTag();
            if (_jspx_eval_c_choose_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\t\t\t\t");
              //  c:when
              com.liferay.taglib.core.WhenTag _jspx_th_c_when_1 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
              _jspx_th_c_when_1.setPageContext(_jspx_page_context);
              _jspx_th_c_when_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_1);
              _jspx_th_c_when_1.setTest( showAdminSuggestionView );
              int _jspx_eval_c_when_1 = _jspx_th_c_when_1.doStartTag();
              if (_jspx_eval_c_when_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\t\t\t\t\t<h5>\n\t\t\t\t\t\t");
                if (_jspx_meth_liferay$1ui_message_1((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_when_1, _jspx_page_context))
                  return;
                out.write("\n\n\t\t\t\t\t\t");
                //  c:if
                com.liferay.taglib.core.IfTag _jspx_th_c_if_1 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                _jspx_th_c_if_1.setPageContext(_jspx_page_context);
                _jspx_th_c_if_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_1);
                _jspx_th_c_if_1.setTest( pendingKBCommentsCount > 0 );
                int _jspx_eval_c_if_1 = _jspx_th_c_if_1.doStartTag();
                if (_jspx_eval_c_if_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  out.write("\n\t\t\t\t\t\t\t(");
                  //  liferay-ui:message
                  com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_2 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_arguments_nobody.get(com.liferay.taglib.ui.MessageTag.class);
                  _jspx_th_liferay$1ui_message_2.setPageContext(_jspx_page_context);
                  _jspx_th_liferay$1ui_message_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_1);
                  _jspx_th_liferay$1ui_message_2.setArguments( pendingKBCommentsCount );
                  _jspx_th_liferay$1ui_message_2.setKey("x-pending");
                  int _jspx_eval_liferay$1ui_message_2 = _jspx_th_liferay$1ui_message_2.doStartTag();
                  if (_jspx_th_liferay$1ui_message_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_liferay$1ui_message_key_arguments_nobody.reuse(_jspx_th_liferay$1ui_message_2);
                    return;
                  }
                  _jspx_tagPool_liferay$1ui_message_key_arguments_nobody.reuse(_jspx_th_liferay$1ui_message_2);
                  out.write(")\n\t\t\t\t\t\t");
                }
                if (_jspx_th_c_if_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_1);
                  return;
                }
                _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_1);
                out.write("\n\t\t\t\t\t</h5>\n\t\t\t\t");
              }
              if (_jspx_th_c_when_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_1);
                return;
              }
              _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_1);
              out.write("\n\t\t\t\t");
              if (_jspx_meth_c_otherwise_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_choose_1, _jspx_page_context))
                return;
              out.write("\n\t\t\t");
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
          //  c:when
          com.liferay.taglib.core.WhenTag _jspx_th_c_when_2 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
          _jspx_th_c_when_2.setPageContext(_jspx_page_context);
          _jspx_th_c_when_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_0);
          _jspx_th_c_when_2.setTest( kbCommentsCount > 1 );
          int _jspx_eval_c_when_2 = _jspx_th_c_when_2.doStartTag();
          if (_jspx_eval_c_when_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t\t");
            //  c:choose
            com.liferay.taglib.core.ChooseTag _jspx_th_c_choose_2 = (com.liferay.taglib.core.ChooseTag) _jspx_tagPool_c_choose.get(com.liferay.taglib.core.ChooseTag.class);
            _jspx_th_c_choose_2.setPageContext(_jspx_page_context);
            _jspx_th_c_choose_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_2);
            int _jspx_eval_c_choose_2 = _jspx_th_c_choose_2.doStartTag();
            if (_jspx_eval_c_choose_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\t\t\t\t");
              //  c:when
              com.liferay.taglib.core.WhenTag _jspx_th_c_when_3 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
              _jspx_th_c_when_3.setPageContext(_jspx_page_context);
              _jspx_th_c_when_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_2);
              _jspx_th_c_when_3.setTest( showAdminSuggestionView );
              int _jspx_eval_c_when_3 = _jspx_th_c_when_3.doStartTag();
              if (_jspx_eval_c_when_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\t\t\t\t\t<h5>\n\t\t\t\t\t\t");
                //  liferay-ui:message
                com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_4 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_arguments_nobody.get(com.liferay.taglib.ui.MessageTag.class);
                _jspx_th_liferay$1ui_message_4.setPageContext(_jspx_page_context);
                _jspx_th_liferay$1ui_message_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_3);
                _jspx_th_liferay$1ui_message_4.setArguments( kbCommentsCount );
                _jspx_th_liferay$1ui_message_4.setKey("there-are-x-suggestions");
                int _jspx_eval_liferay$1ui_message_4 = _jspx_th_liferay$1ui_message_4.doStartTag();
                if (_jspx_th_liferay$1ui_message_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_liferay$1ui_message_key_arguments_nobody.reuse(_jspx_th_liferay$1ui_message_4);
                  return;
                }
                _jspx_tagPool_liferay$1ui_message_key_arguments_nobody.reuse(_jspx_th_liferay$1ui_message_4);
                out.write("\n\n\t\t\t\t\t\t");
                //  c:if
                com.liferay.taglib.core.IfTag _jspx_th_c_if_2 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                _jspx_th_c_if_2.setPageContext(_jspx_page_context);
                _jspx_th_c_if_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_3);
                _jspx_th_c_if_2.setTest( pendingKBCommentsCount > 0 );
                int _jspx_eval_c_if_2 = _jspx_th_c_if_2.doStartTag();
                if (_jspx_eval_c_if_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  out.write("\n\t\t\t\t\t\t\t(");
                  //  liferay-ui:message
                  com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_5 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_arguments_nobody.get(com.liferay.taglib.ui.MessageTag.class);
                  _jspx_th_liferay$1ui_message_5.setPageContext(_jspx_page_context);
                  _jspx_th_liferay$1ui_message_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_2);
                  _jspx_th_liferay$1ui_message_5.setArguments( pendingKBCommentsCount );
                  _jspx_th_liferay$1ui_message_5.setKey("x-pending");
                  int _jspx_eval_liferay$1ui_message_5 = _jspx_th_liferay$1ui_message_5.doStartTag();
                  if (_jspx_th_liferay$1ui_message_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_liferay$1ui_message_key_arguments_nobody.reuse(_jspx_th_liferay$1ui_message_5);
                    return;
                  }
                  _jspx_tagPool_liferay$1ui_message_key_arguments_nobody.reuse(_jspx_th_liferay$1ui_message_5);
                  out.write(")\n\t\t\t\t\t\t");
                }
                if (_jspx_th_c_if_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_2);
                  return;
                }
                _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_2);
                out.write("\n\t\t\t\t\t</h5>\n\t\t\t\t");
              }
              if (_jspx_th_c_when_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_3);
                return;
              }
              _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_3);
              out.write("\n\t\t\t\t");
              //  c:otherwise
              com.liferay.taglib.core.OtherwiseTag _jspx_th_c_otherwise_1 = (com.liferay.taglib.core.OtherwiseTag) _jspx_tagPool_c_otherwise.get(com.liferay.taglib.core.OtherwiseTag.class);
              _jspx_th_c_otherwise_1.setPageContext(_jspx_page_context);
              _jspx_th_c_otherwise_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_2);
              int _jspx_eval_c_otherwise_1 = _jspx_th_c_otherwise_1.doStartTag();
              if (_jspx_eval_c_otherwise_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\t\t\t\t\t");
                //  liferay-ui:message
                com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_6 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_arguments_nobody.get(com.liferay.taglib.ui.MessageTag.class);
                _jspx_th_liferay$1ui_message_6.setPageContext(_jspx_page_context);
                _jspx_th_liferay$1ui_message_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_1);
                _jspx_th_liferay$1ui_message_6.setArguments( kbCommentsCount );
                _jspx_th_liferay$1ui_message_6.setKey("you-sent-x-suggestions-for-this-article");
                int _jspx_eval_liferay$1ui_message_6 = _jspx_th_liferay$1ui_message_6.doStartTag();
                if (_jspx_th_liferay$1ui_message_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_liferay$1ui_message_key_arguments_nobody.reuse(_jspx_th_liferay$1ui_message_6);
                  return;
                }
                _jspx_tagPool_liferay$1ui_message_key_arguments_nobody.reuse(_jspx_th_liferay$1ui_message_6);
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
            out.write("\n\t\t");
          }
          if (_jspx_th_c_when_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_2);
            return;
          }
          _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_2);
          out.write('\n');
          out.write('	');
        }
        if (_jspx_th_c_choose_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_0);
          return;
        }
        _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_0);
        out.write("\n\n\t");

	boolean expanded = ParamUtil.getBoolean(request, "expanded");
	
        out.write("\n\n\t");
        //  c:choose
        com.liferay.taglib.core.ChooseTag _jspx_th_c_choose_3 = (com.liferay.taglib.core.ChooseTag) _jspx_tagPool_c_choose.get(com.liferay.taglib.core.ChooseTag.class);
        _jspx_th_c_choose_3.setPageContext(_jspx_page_context);
        _jspx_th_c_choose_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_0);
        int _jspx_eval_c_choose_3 = _jspx_th_c_choose_3.doStartTag();
        if (_jspx_eval_c_choose_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          out.write("\n\t\t");
          //  c:when
          com.liferay.taglib.core.WhenTag _jspx_th_c_when_4 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
          _jspx_th_c_when_4.setPageContext(_jspx_page_context);
          _jspx_th_c_when_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_3);
          _jspx_th_c_when_4.setTest( showAdminSuggestionView );
          int _jspx_eval_c_when_4 = _jspx_th_c_when_4.doStartTag();
          if (_jspx_eval_c_when_4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\n\t\t\t");

			KBSuggestionListDisplayContext kbSuggestionListDisplayContext = new KBSuggestionListDisplayContext(request, templatePath, kbArticle);

			request.setAttribute(KBWebKeys.KNOWLEDGE_BASE_KB_SUGGESTION_LIST_DISPLAY_CONTEXT, kbSuggestionListDisplayContext);

			SearchContainer kbCommentsSearchContainer = new SearchContainer(renderRequest, null, null, SearchContainer.DEFAULT_CUR_PARAM, SearchContainer.DEFAULT_DELTA, currentURLObj, null, kbSuggestionListDisplayContext.getEmptyResultsMessage());

			kbSuggestionListDisplayContext.populateResultsAndTotal(kbCommentsSearchContainer);

			request.setAttribute("view_suggestions.jsp-resultRowSplitter", new KBCommentResultRowSplitter(kbSuggestionListDisplayContext, resourceBundle));
			request.setAttribute("view_suggestions.jsp-searchContainer", kbCommentsSearchContainer);
			
            out.write("\n\n\t\t\t");
            //  liferay-util:include
            com.liferay.taglib.util.IncludeTag _jspx_th_liferay$1util_include_0 = (com.liferay.taglib.util.IncludeTag) _jspx_tagPool_liferay$1util_include_servletContext_page_nobody.get(com.liferay.taglib.util.IncludeTag.class);
            _jspx_th_liferay$1util_include_0.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1util_include_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_4);
            _jspx_th_liferay$1util_include_0.setPage("/admin/common/view_suggestions_by_status.jsp");
            _jspx_th_liferay$1util_include_0.setServletContext( application );
            int _jspx_eval_liferay$1util_include_0 = _jspx_th_liferay$1util_include_0.doStartTag();
            if (_jspx_th_liferay$1util_include_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_liferay$1util_include_servletContext_page_nobody.reuse(_jspx_th_liferay$1util_include_0);
              return;
            }
            _jspx_tagPool_liferay$1util_include_servletContext_page_nobody.reuse(_jspx_th_liferay$1util_include_0);
            out.write("\n\t\t");
          }
          if (_jspx_th_c_when_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_4);
            return;
          }
          _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_4);
          out.write("\n\t\t");
          //  c:otherwise
          com.liferay.taglib.core.OtherwiseTag _jspx_th_c_otherwise_2 = (com.liferay.taglib.core.OtherwiseTag) _jspx_tagPool_c_otherwise.get(com.liferay.taglib.core.OtherwiseTag.class);
          _jspx_th_c_otherwise_2.setPageContext(_jspx_page_context);
          _jspx_th_c_otherwise_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_3);
          int _jspx_eval_c_otherwise_2 = _jspx_th_c_otherwise_2.doStartTag();
          if (_jspx_eval_c_otherwise_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t\t");
            //  c:if
            com.liferay.taglib.core.IfTag _jspx_th_c_if_3 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
            _jspx_th_c_if_3.setPageContext(_jspx_page_context);
            _jspx_th_c_if_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_2);
            _jspx_th_c_if_3.setTest( kbCommentsCount > 0 );
            int _jspx_eval_c_if_3 = _jspx_th_c_if_3.doStartTag();
            if (_jspx_eval_c_if_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\t\t\t\t");
              //  liferay-portlet:renderURL
              com.liferay.taglib.portlet.RenderURLTag _jspx_th_liferay$1portlet_renderURL_0 = (com.liferay.taglib.portlet.RenderURLTag) _jspx_tagPool_liferay$1portlet_renderURL_varImpl.get(com.liferay.taglib.portlet.RenderURLTag.class);
              _jspx_th_liferay$1portlet_renderURL_0.setPageContext(_jspx_page_context);
              _jspx_th_liferay$1portlet_renderURL_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_3);
              _jspx_th_liferay$1portlet_renderURL_0.setVarImpl("iteratorURL");
              int _jspx_eval_liferay$1portlet_renderURL_0 = _jspx_th_liferay$1portlet_renderURL_0.doStartTag();
              if (_jspx_eval_liferay$1portlet_renderURL_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\t\t\t\t\t");
                //  portlet:param
                com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_1 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                _jspx_th_portlet_param_1.setPageContext(_jspx_page_context);
                _jspx_th_portlet_param_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_0);
                _jspx_th_portlet_param_1.setName("expanded");
                _jspx_th_portlet_param_1.setValue( Boolean.TRUE.toString() );
                int _jspx_eval_portlet_param_1 = _jspx_th_portlet_param_1.doStartTag();
                if (_jspx_th_portlet_param_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_1);
                  return;
                }
                _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_1);
                out.write("\n\t\t\t\t");
              }
              if (_jspx_th_liferay$1portlet_renderURL_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_liferay$1portlet_renderURL_varImpl.reuse(_jspx_th_liferay$1portlet_renderURL_0);
                return;
              }
              _jspx_tagPool_liferay$1portlet_renderURL_varImpl.reuse(_jspx_th_liferay$1portlet_renderURL_0);
              com.liferay.portal.kernel.portlet.LiferayPortletURL iteratorURL = null;
              iteratorURL = (com.liferay.portal.kernel.portlet.LiferayPortletURL) _jspx_page_context.findAttribute("iteratorURL");
              out.write("\n\n\t\t\t\t");
              //  liferay-ui:search-container
              com.liferay.taglib.ui.SearchContainerTag _jspx_th_liferay$1ui_search$1container_0 = (com.liferay.taglib.ui.SearchContainerTag) _jspx_tagPool_liferay$1ui_search$1container_total_orderByComparator_iteratorURL_emptyResultsMessage.get(com.liferay.taglib.ui.SearchContainerTag.class);
              _jspx_th_liferay$1ui_search$1container_0.setPageContext(_jspx_page_context);
              _jspx_th_liferay$1ui_search$1container_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_3);
              _jspx_th_liferay$1ui_search$1container_0.setEmptyResultsMessage("no-comments-found");
              _jspx_th_liferay$1ui_search$1container_0.setIteratorURL( iteratorURL );
              _jspx_th_liferay$1ui_search$1container_0.setOrderByComparator( KBUtil.getKBCommentOrderByComparator("modified-date", "desc") );
              _jspx_th_liferay$1ui_search$1container_0.setTotal( kbCommentsCount );
              int _jspx_eval_liferay$1ui_search$1container_0 = _jspx_th_liferay$1ui_search$1container_0.doStartTag();
              if (_jspx_eval_liferay$1ui_search$1container_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                java.lang.Integer total = null;
                com.liferay.portal.kernel.dao.search.SearchContainer searchContainer = null;
                total = (java.lang.Integer) _jspx_page_context.findAttribute("total");
                searchContainer = (com.liferay.portal.kernel.dao.search.SearchContainer) _jspx_page_context.findAttribute("searchContainer");
                out.write("\n\t\t\t\t\t");
                //  liferay-ui:search-container-results
                java.util.List results = null;
                java.lang.Integer deprecatedTotal = null;
                com.liferay.taglib.ui.SearchContainerResultsTag _jspx_th_liferay$1ui_search$1container$1results_0 = (com.liferay.taglib.ui.SearchContainerResultsTag) _jspx_tagPool_liferay$1ui_search$1container$1results_results_nobody.get(com.liferay.taglib.ui.SearchContainerResultsTag.class);
                _jspx_th_liferay$1ui_search$1container$1results_0.setPageContext(_jspx_page_context);
                _jspx_th_liferay$1ui_search$1container$1results_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container_0);
                _jspx_th_liferay$1ui_search$1container$1results_0.setResults( KBCommentLocalServiceUtil.getKBComments(themeDisplay.getUserId(), KBArticle.class.getName(), kbArticle.getResourcePrimKey(), searchContainer.getStart(), searchContainer.getEnd(), searchContainer.getOrderByComparator()) );
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
                out.write("\n\n\t\t\t\t\t");
                //  liferay-ui:search-container-row
                com.liferay.taglib.ui.SearchContainerRowTag _jspx_th_liferay$1ui_search$1container$1row_0 = (com.liferay.taglib.ui.SearchContainerRowTag) _jspx_tagPool_liferay$1ui_search$1container$1row_modelVar_className.get(com.liferay.taglib.ui.SearchContainerRowTag.class);
                _jspx_th_liferay$1ui_search$1container$1row_0.setPageContext(_jspx_page_context);
                _jspx_th_liferay$1ui_search$1container$1row_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container_0);
                _jspx_th_liferay$1ui_search$1container$1row_0.setClassName("com.liferay.knowledge.base.model.KBComment");
                _jspx_th_liferay$1ui_search$1container$1row_0.setModelVar("kbComment");
                int _jspx_eval_liferay$1ui_search$1container$1row_0 = _jspx_th_liferay$1ui_search$1container$1row_0.doStartTag();
                if (_jspx_eval_liferay$1ui_search$1container$1row_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  java.lang.Integer index = null;
                  com.liferay.knowledge.base.model.KBComment kbComment = null;
                  com.liferay.portal.kernel.dao.search.ResultRow row = null;
                  if (_jspx_eval_liferay$1ui_search$1container$1row_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                    out = _jspx_page_context.pushBody();
                    _jspx_th_liferay$1ui_search$1container$1row_0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                    _jspx_th_liferay$1ui_search$1container$1row_0.doInitBody();
                  }
                  index = (java.lang.Integer) _jspx_page_context.findAttribute("index");
                  kbComment = (com.liferay.knowledge.base.model.KBComment) _jspx_page_context.findAttribute("kbComment");
                  row = (com.liferay.portal.kernel.dao.search.ResultRow) _jspx_page_context.findAttribute("row");
                  do {
                    out.write("\n\t\t\t\t\t\t");
                    //  liferay-ui:search-container-column-text
                    com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_0 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text_orderable_name_cssClass.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
                    _jspx_th_liferay$1ui_search$1container$1column$1text_0.setPageContext(_jspx_page_context);
                    _jspx_th_liferay$1ui_search$1container$1column$1text_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1row_0);
                    _jspx_th_liferay$1ui_search$1container$1column$1text_0.setCssClass("kb-column-no-wrap");
                    _jspx_th_liferay$1ui_search$1container$1column$1text_0.setName("comment");
                    _jspx_th_liferay$1ui_search$1container$1column$1text_0.setOrderable( true );
                    int _jspx_eval_liferay$1ui_search$1container$1column$1text_0 = _jspx_th_liferay$1ui_search$1container$1column$1text_0.doStartTag();
                    if (_jspx_eval_liferay$1ui_search$1container$1column$1text_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      if (_jspx_eval_liferay$1ui_search$1container$1column$1text_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                        out = _jspx_page_context.pushBody();
                        _jspx_th_liferay$1ui_search$1container$1column$1text_0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                        _jspx_th_liferay$1ui_search$1container$1column$1text_0.doInitBody();
                      }
                      do {
                        out.write("\n\t\t\t\t\t\t\t");
                        out.print( HtmlUtil.escape(kbComment.getContent()) );
                        out.write("\n\t\t\t\t\t\t");
                        int evalDoAfterBody = _jspx_th_liferay$1ui_search$1container$1column$1text_0.doAfterBody();
                        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                          break;
                      } while (true);
                      if (_jspx_eval_liferay$1ui_search$1container$1column$1text_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                        out = _jspx_page_context.popBody();
                    }
                    if (_jspx_th_liferay$1ui_search$1container$1column$1text_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_liferay$1ui_search$1container$1column$1text_orderable_name_cssClass.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_0);
                      return;
                    }
                    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_orderable_name_cssClass.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_0);
                    out.write("\n\n\t\t\t\t\t\t");
                    //  liferay-ui:search-container-column-date
                    com.liferay.taglib.ui.SearchContainerColumnDateTag _jspx_th_liferay$1ui_search$1container$1column$1date_0 = (com.liferay.taglib.ui.SearchContainerColumnDateTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1date_value_orderableProperty_orderable_name_cssClass_nobody.get(com.liferay.taglib.ui.SearchContainerColumnDateTag.class);
                    _jspx_th_liferay$1ui_search$1container$1column$1date_0.setPageContext(_jspx_page_context);
                    _jspx_th_liferay$1ui_search$1container$1column$1date_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1row_0);
                    _jspx_th_liferay$1ui_search$1container$1column$1date_0.setCssClass("kb-column-no-wrap");
                    _jspx_th_liferay$1ui_search$1container$1column$1date_0.setName("date");
                    _jspx_th_liferay$1ui_search$1container$1column$1date_0.setOrderable( true );
                    _jspx_th_liferay$1ui_search$1container$1column$1date_0.setOrderableProperty("modified-date");
                    _jspx_th_liferay$1ui_search$1container$1column$1date_0.setValue( kbComment.getModifiedDate() );
                    int _jspx_eval_liferay$1ui_search$1container$1column$1date_0 = _jspx_th_liferay$1ui_search$1container$1column$1date_0.doStartTag();
                    if (_jspx_th_liferay$1ui_search$1container$1column$1date_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_liferay$1ui_search$1container$1column$1date_value_orderableProperty_orderable_name_cssClass_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1date_0);
                      return;
                    }
                    _jspx_tagPool_liferay$1ui_search$1container$1column$1date_value_orderableProperty_orderable_name_cssClass_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1date_0);
                    out.write("\n\n\t\t\t\t\t\t");
                    //  liferay-ui:search-container-column-text
                    com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_1 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text_orderable_name_cssClass.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
                    _jspx_th_liferay$1ui_search$1container$1column$1text_1.setPageContext(_jspx_page_context);
                    _jspx_th_liferay$1ui_search$1container$1column$1text_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1row_0);
                    _jspx_th_liferay$1ui_search$1container$1column$1text_1.setCssClass("kb-column-no-wrap");
                    _jspx_th_liferay$1ui_search$1container$1column$1text_1.setName("status");
                    _jspx_th_liferay$1ui_search$1container$1column$1text_1.setOrderable( true );
                    int _jspx_eval_liferay$1ui_search$1container$1column$1text_1 = _jspx_th_liferay$1ui_search$1container$1column$1text_1.doStartTag();
                    if (_jspx_eval_liferay$1ui_search$1container$1column$1text_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      if (_jspx_eval_liferay$1ui_search$1container$1column$1text_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                        out = _jspx_page_context.pushBody();
                        _jspx_th_liferay$1ui_search$1container$1column$1text_1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                        _jspx_th_liferay$1ui_search$1container$1column$1text_1.doInitBody();
                      }
                      do {
                        out.write("\n\t\t\t\t\t\t\t");
                        //  liferay-ui:message
                        com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_7 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
                        _jspx_th_liferay$1ui_message_7.setPageContext(_jspx_page_context);
                        _jspx_th_liferay$1ui_message_7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1column$1text_1);
                        _jspx_th_liferay$1ui_message_7.setKey( KBUtil.getStatusLabel(kbComment.getStatus()) );
                        int _jspx_eval_liferay$1ui_message_7 = _jspx_th_liferay$1ui_message_7.doStartTag();
                        if (_jspx_th_liferay$1ui_message_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                          _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_7);
                          return;
                        }
                        _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_7);
                        out.write("\n\t\t\t\t\t\t");
                        int evalDoAfterBody = _jspx_th_liferay$1ui_search$1container$1column$1text_1.doAfterBody();
                        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                          break;
                      } while (true);
                      if (_jspx_eval_liferay$1ui_search$1container$1column$1text_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                        out = _jspx_page_context.popBody();
                    }
                    if (_jspx_th_liferay$1ui_search$1container$1column$1text_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_liferay$1ui_search$1container$1column$1text_orderable_name_cssClass.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_1);
                      return;
                    }
                    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_orderable_name_cssClass.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_1);
                    out.write("\n\t\t\t\t\t");
                    int evalDoAfterBody = _jspx_th_liferay$1ui_search$1container$1row_0.doAfterBody();
                    index = (java.lang.Integer) _jspx_page_context.findAttribute("index");
                    kbComment = (com.liferay.knowledge.base.model.KBComment) _jspx_page_context.findAttribute("kbComment");
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
                out.write("\n\n\t\t\t\t\t");
                if (_jspx_meth_liferay$1ui_search$1iterator_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1ui_search$1container_0, _jspx_page_context))
                  return;
                out.write("\n\t\t\t\t");
              }
              if (_jspx_th_liferay$1ui_search$1container_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_liferay$1ui_search$1container_total_orderByComparator_iteratorURL_emptyResultsMessage.reuse(_jspx_th_liferay$1ui_search$1container_0);
                return;
              }
              _jspx_tagPool_liferay$1ui_search$1container_total_orderByComparator_iteratorURL_emptyResultsMessage.reuse(_jspx_th_liferay$1ui_search$1container_0);
              out.write("\n\t\t\t");
            }
            if (_jspx_th_c_if_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_3);
              return;
            }
            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_3);
            out.write("\n\t\t");
          }
          if (_jspx_th_c_otherwise_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_2);
            return;
          }
          _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_2);
          out.write('\n');
          out.write('	');
        }
        if (_jspx_th_c_choose_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_3);
          return;
        }
        _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_3);
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

  private boolean _jspx_meth_liferay$1ui_message_0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_0 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_0.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_0);
    _jspx_th_liferay$1ui_message_0.setKey("do-you-have-any-suggestions");
    int _jspx_eval_liferay$1ui_message_0 = _jspx_th_liferay$1ui_message_0.doStartTag();
    if (_jspx_th_liferay$1ui_message_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_0);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_0);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_0 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_0.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_0);
    int _jspx_eval_portlet_namespace_0 = _jspx_th_portlet_namespace_0.doStartTag();
    if (_jspx_th_portlet_namespace_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_0);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_0);
    return false;
  }

  private boolean _jspx_meth_aui_fieldset_0(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_form_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:fieldset
    com.liferay.taglib.aui.FieldsetTag _jspx_th_aui_fieldset_0 = (com.liferay.taglib.aui.FieldsetTag) _jspx_tagPool_aui_fieldset.get(com.liferay.taglib.aui.FieldsetTag.class);
    _jspx_th_aui_fieldset_0.setPageContext(_jspx_page_context);
    _jspx_th_aui_fieldset_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
    int _jspx_eval_aui_fieldset_0 = _jspx_th_aui_fieldset_0.doStartTag();
    if (_jspx_eval_aui_fieldset_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      out.write("\n\t\t\t\t");
      if (_jspx_meth_aui_input_3((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_fieldset_0, _jspx_page_context))
        return true;
      out.write("\n\n\t\t\t\t");
      if (_jspx_meth_aui_button$1row_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_fieldset_0, _jspx_page_context))
        return true;
      out.write("\n\t\t\t");
    }
    if (_jspx_th_aui_fieldset_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_fieldset.reuse(_jspx_th_aui_fieldset_0);
      return true;
    }
    _jspx_tagPool_aui_fieldset.reuse(_jspx_th_aui_fieldset_0);
    return false;
  }

  private boolean _jspx_meth_aui_input_3(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_fieldset_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:input
    com.liferay.taglib.aui.InputTag _jspx_th_aui_input_3 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_name_label_nobody.get(com.liferay.taglib.aui.InputTag.class);
    _jspx_th_aui_input_3.setPageContext(_jspx_page_context);
    _jspx_th_aui_input_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_0);
    _jspx_th_aui_input_3.setLabel("");
    _jspx_th_aui_input_3.setName("content");
    int _jspx_eval_aui_input_3 = _jspx_th_aui_input_3.doStartTag();
    if (_jspx_th_aui_input_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_input_name_label_nobody.reuse(_jspx_th_aui_input_3);
      return true;
    }
    _jspx_tagPool_aui_input_name_label_nobody.reuse(_jspx_th_aui_input_3);
    return false;
  }

  private boolean _jspx_meth_aui_button$1row_0(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_fieldset_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:button-row
    com.liferay.taglib.aui.ButtonRowTag _jspx_th_aui_button$1row_0 = (com.liferay.taglib.aui.ButtonRowTag) _jspx_tagPool_aui_button$1row_cssClass.get(com.liferay.taglib.aui.ButtonRowTag.class);
    _jspx_th_aui_button$1row_0.setPageContext(_jspx_page_context);
    _jspx_th_aui_button$1row_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_0);
    _jspx_th_aui_button$1row_0.setCssClass("kb-submit-buttons");
    int _jspx_eval_aui_button$1row_0 = _jspx_th_aui_button$1row_0.doStartTag();
    if (_jspx_eval_aui_button$1row_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      out.write("\n\t\t\t\t\t");
      if (_jspx_meth_aui_button_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_button$1row_0, _jspx_page_context))
        return true;
      out.write("\n\t\t\t\t");
    }
    if (_jspx_th_aui_button$1row_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_button$1row_cssClass.reuse(_jspx_th_aui_button$1row_0);
      return true;
    }
    _jspx_tagPool_aui_button$1row_cssClass.reuse(_jspx_th_aui_button$1row_0);
    return false;
  }

  private boolean _jspx_meth_aui_button_0(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_button$1row_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:button
    com.liferay.taglib.aui.ButtonTag _jspx_th_aui_button_0 = (com.liferay.taglib.aui.ButtonTag) _jspx_tagPool_aui_button_value_type_nobody.get(com.liferay.taglib.aui.ButtonTag.class);
    _jspx_th_aui_button_0.setPageContext(_jspx_page_context);
    _jspx_th_aui_button_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_button$1row_0);
    _jspx_th_aui_button_0.setType("submit");
    _jspx_th_aui_button_0.setValue("submit");
    int _jspx_eval_aui_button_0 = _jspx_th_aui_button_0.doStartTag();
    if (_jspx_th_aui_button_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_button_value_type_nobody.reuse(_jspx_th_aui_button_0);
      return true;
    }
    _jspx_tagPool_aui_button_value_type_nobody.reuse(_jspx_th_aui_button_0);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_success_0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:success
    com.liferay.taglib.ui.SuccessTag _jspx_th_liferay$1ui_success_0 = (com.liferay.taglib.ui.SuccessTag) _jspx_tagPool_liferay$1ui_success_message_key_nobody.get(com.liferay.taglib.ui.SuccessTag.class);
    _jspx_th_liferay$1ui_success_0.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_success_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_0);
    _jspx_th_liferay$1ui_success_0.setKey("suggestionDeleted");
    _jspx_th_liferay$1ui_success_0.setMessage("suggestion-deleted-successfully");
    int _jspx_eval_liferay$1ui_success_0 = _jspx_th_liferay$1ui_success_0.doStartTag();
    if (_jspx_th_liferay$1ui_success_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_success_message_key_nobody.reuse(_jspx_th_liferay$1ui_success_0);
      return true;
    }
    _jspx_tagPool_liferay$1ui_success_message_key_nobody.reuse(_jspx_th_liferay$1ui_success_0);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_success_1(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:success
    com.liferay.taglib.ui.SuccessTag _jspx_th_liferay$1ui_success_1 = (com.liferay.taglib.ui.SuccessTag) _jspx_tagPool_liferay$1ui_success_message_key_nobody.get(com.liferay.taglib.ui.SuccessTag.class);
    _jspx_th_liferay$1ui_success_1.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_success_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_0);
    _jspx_th_liferay$1ui_success_1.setKey("suggestionStatusUpdated");
    _jspx_th_liferay$1ui_success_1.setMessage("suggestion-status-updated-successfully");
    int _jspx_eval_liferay$1ui_success_1 = _jspx_th_liferay$1ui_success_1.doStartTag();
    if (_jspx_th_liferay$1ui_success_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_success_message_key_nobody.reuse(_jspx_th_liferay$1ui_success_1);
      return true;
    }
    _jspx_tagPool_liferay$1ui_success_message_key_nobody.reuse(_jspx_th_liferay$1ui_success_1);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_success_2(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:success
    com.liferay.taglib.ui.SuccessTag _jspx_th_liferay$1ui_success_2 = (com.liferay.taglib.ui.SuccessTag) _jspx_tagPool_liferay$1ui_success_message_key_nobody.get(com.liferay.taglib.ui.SuccessTag.class);
    _jspx_th_liferay$1ui_success_2.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_success_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_0);
    _jspx_th_liferay$1ui_success_2.setKey("suggestionSaved");
    _jspx_th_liferay$1ui_success_2.setMessage("suggestion-saved-successfully");
    int _jspx_eval_liferay$1ui_success_2 = _jspx_th_liferay$1ui_success_2.doStartTag();
    if (_jspx_th_liferay$1ui_success_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_success_message_key_nobody.reuse(_jspx_th_liferay$1ui_success_2);
      return true;
    }
    _jspx_tagPool_liferay$1ui_success_message_key_nobody.reuse(_jspx_th_liferay$1ui_success_2);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_1(javax.servlet.jsp.tagext.JspTag _jspx_th_c_when_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_1 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_1.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_1);
    _jspx_th_liferay$1ui_message_1.setKey("there-is-one-suggestion");
    int _jspx_eval_liferay$1ui_message_1 = _jspx_th_liferay$1ui_message_1.doStartTag();
    if (_jspx_th_liferay$1ui_message_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_1);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_1);
    return false;
  }

  private boolean _jspx_meth_c_otherwise_0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_choose_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:otherwise
    com.liferay.taglib.core.OtherwiseTag _jspx_th_c_otherwise_0 = (com.liferay.taglib.core.OtherwiseTag) _jspx_tagPool_c_otherwise.get(com.liferay.taglib.core.OtherwiseTag.class);
    _jspx_th_c_otherwise_0.setPageContext(_jspx_page_context);
    _jspx_th_c_otherwise_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_1);
    int _jspx_eval_c_otherwise_0 = _jspx_th_c_otherwise_0.doStartTag();
    if (_jspx_eval_c_otherwise_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      out.write("\n\t\t\t\t\t");
      if (_jspx_meth_liferay$1ui_message_3((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_otherwise_0, _jspx_page_context))
        return true;
      out.write("\n\t\t\t\t");
    }
    if (_jspx_th_c_otherwise_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_0);
      return true;
    }
    _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_0);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_3(javax.servlet.jsp.tagext.JspTag _jspx_th_c_otherwise_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_3 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_3.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_0);
    _jspx_th_liferay$1ui_message_3.setKey("you-sent-one-suggestion-for-this-article");
    int _jspx_eval_liferay$1ui_message_3 = _jspx_th_liferay$1ui_message_3.doStartTag();
    if (_jspx_th_liferay$1ui_message_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_3);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_3);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_search$1iterator_0(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1ui_search$1container_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:search-iterator
    com.liferay.taglib.ui.SearchIteratorTag _jspx_th_liferay$1ui_search$1iterator_0 = (com.liferay.taglib.ui.SearchIteratorTag) _jspx_tagPool_liferay$1ui_search$1iterator_markupView_nobody.get(com.liferay.taglib.ui.SearchIteratorTag.class);
    _jspx_th_liferay$1ui_search$1iterator_0.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_search$1iterator_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container_0);
    _jspx_th_liferay$1ui_search$1iterator_0.setMarkupView("lexicon");
    int _jspx_eval_liferay$1ui_search$1iterator_0 = _jspx_th_liferay$1ui_search$1iterator_0.doStartTag();
    if (_jspx_th_liferay$1ui_search$1iterator_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_search$1iterator_markupView_nobody.reuse(_jspx_th_liferay$1ui_search$1iterator_0);
      return true;
    }
    _jspx_tagPool_liferay$1ui_search$1iterator_markupView_nobody.reuse(_jspx_th_liferay$1ui_search$1iterator_0);
    return false;
  }
}
