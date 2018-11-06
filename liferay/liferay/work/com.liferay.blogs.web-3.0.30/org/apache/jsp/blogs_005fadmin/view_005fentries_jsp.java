package org.apache.jsp.blogs_005fadmin;

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

public final class view_005fentries_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(6);
    _jspx_dependants.add("/blogs_admin/init.jsp");
    _jspx_dependants.add("/init.jsp");
    _jspx_dependants.add("/init-ext.jsp");
    _jspx_dependants.add("/blogs_admin/init-ext.jsp");
    _jspx_dependants.add("/blogs_admin/entry_search_columns.jspf");
    _jspx_dependants.add("/blogs_admin/entry_vertical_card.jspf");
  }

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_clay_management$1toolbar_viewTypeItems_sortingURL_sortingOrder_showInfoButton_searchFormName_searchContainerId_searchActionURL_itemsTotal_filterDropdownItems_disabled_creationMenu_componentId_clearResultsURL_actionDropdownItems_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_actionURL_var_name;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1asset_categorization$1filter_portletURL_assetType_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_message_key_arguments_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_param_value_name_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_a_href;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container$1column$1user_userId_showDetails_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container$1column$1date_property_orderable_name_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container$1column$1jsp_path_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_script;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1frontend_icon$1vertical$1card_url_title_rowChecker_resultRow_icon_actionJspServletContext_actionJsp;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1frontend_vertical$1card_url_title_rowChecker_resultRow_imageUrl_cssClass_actionJspServletContext_actionJsp;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_user$1portrait_userId_cssClass_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container$1row_modelVar_keyProperty_escapedModel_className;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1portlet_renderURL_varImpl;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_input_type_name_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_choose;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1frontend_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_orderable_name_href_cssClass_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_when_test;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_orderable_name_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_namespace_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_message_key_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1trash_undo_portletURL_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1frontend_vertical$1card$1header;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container$1column$1text_property_orderable_name_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_input_value_type_name_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container$1column$1status_name_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_actionURL_name_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container$1column$1text_colspan;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_otherwise;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1frontend_vertical$1card$1sticker$1bottom;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_form_name_method_action;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container_searchContainer_rowChecker_id;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1theme_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container$1column$1text;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_workflow$1status_status_showLabel_showIcon_markupView_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1trash_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1frontend_vertical$1card$1footer;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1iterator_markupView_displayStyle_nobody;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_clay_management$1toolbar_viewTypeItems_sortingURL_sortingOrder_showInfoButton_searchFormName_searchContainerId_searchActionURL_itemsTotal_filterDropdownItems_disabled_creationMenu_componentId_clearResultsURL_actionDropdownItems_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_actionURL_var_name = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1asset_categorization$1filter_portletURL_assetType_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_message_key_arguments_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_param_value_name_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_a_href = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container$1column$1user_userId_showDetails_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container$1column$1date_property_orderable_name_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container$1column$1jsp_path_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_script = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1frontend_icon$1vertical$1card_url_title_rowChecker_resultRow_icon_actionJspServletContext_actionJsp = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1frontend_vertical$1card_url_title_rowChecker_resultRow_imageUrl_cssClass_actionJspServletContext_actionJsp = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_user$1portrait_userId_cssClass_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container$1row_modelVar_keyProperty_escapedModel_className = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1portlet_renderURL_varImpl = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_input_type_name_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_choose = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1frontend_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_orderable_name_href_cssClass_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_when_test = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_orderable_name_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_namespace_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_message_key_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1trash_undo_portletURL_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1frontend_vertical$1card$1header = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_property_orderable_name_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_input_value_type_name_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container$1column$1status_name_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_actionURL_name_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_colspan = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_otherwise = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1frontend_vertical$1card$1sticker$1bottom = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_form_name_method_action = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container_searchContainer_rowChecker_id = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1theme_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_workflow$1status_status_showLabel_showIcon_markupView_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1trash_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1frontend_vertical$1card$1footer = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1iterator_markupView_displayStyle_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_clay_management$1toolbar_viewTypeItems_sortingURL_sortingOrder_showInfoButton_searchFormName_searchContainerId_searchActionURL_itemsTotal_filterDropdownItems_disabled_creationMenu_componentId_clearResultsURL_actionDropdownItems_nobody.release();
    _jspx_tagPool_portlet_actionURL_var_name.release();
    _jspx_tagPool_liferay$1asset_categorization$1filter_portletURL_assetType_nobody.release();
    _jspx_tagPool_liferay$1ui_message_key_arguments_nobody.release();
    _jspx_tagPool_portlet_param_value_name_nobody.release();
    _jspx_tagPool_aui_a_href.release();
    _jspx_tagPool_liferay$1ui_search$1container$1column$1user_userId_showDetails_nobody.release();
    _jspx_tagPool_liferay$1ui_search$1container$1column$1date_property_orderable_name_nobody.release();
    _jspx_tagPool_liferay$1ui_search$1container$1column$1jsp_path_nobody.release();
    _jspx_tagPool_aui_script.release();
    _jspx_tagPool_liferay$1frontend_icon$1vertical$1card_url_title_rowChecker_resultRow_icon_actionJspServletContext_actionJsp.release();
    _jspx_tagPool_liferay$1frontend_vertical$1card_url_title_rowChecker_resultRow_imageUrl_cssClass_actionJspServletContext_actionJsp.release();
    _jspx_tagPool_liferay$1ui_user$1portrait_userId_cssClass_nobody.release();
    _jspx_tagPool_liferay$1ui_search$1container$1row_modelVar_keyProperty_escapedModel_className.release();
    _jspx_tagPool_liferay$1portlet_renderURL_varImpl.release();
    _jspx_tagPool_aui_input_type_name_nobody.release();
    _jspx_tagPool_c_choose.release();
    _jspx_tagPool_liferay$1frontend_defineObjects_nobody.release();
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_orderable_name_href_cssClass_nobody.release();
    _jspx_tagPool_c_when_test.release();
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_orderable_name_nobody.release();
    _jspx_tagPool_portlet_namespace_nobody.release();
    _jspx_tagPool_liferay$1ui_message_key_nobody.release();
    _jspx_tagPool_liferay$1trash_undo_portletURL_nobody.release();
    _jspx_tagPool_liferay$1frontend_vertical$1card$1header.release();
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_property_orderable_name_nobody.release();
    _jspx_tagPool_aui_input_value_type_name_nobody.release();
    _jspx_tagPool_liferay$1ui_search$1container$1column$1status_name_nobody.release();
    _jspx_tagPool_portlet_actionURL_name_nobody.release();
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_colspan.release();
    _jspx_tagPool_c_otherwise.release();
    _jspx_tagPool_liferay$1frontend_vertical$1card$1sticker$1bottom.release();
    _jspx_tagPool_portlet_defineObjects_nobody.release();
    _jspx_tagPool_aui_form_name_method_action.release();
    _jspx_tagPool_liferay$1ui_search$1container_searchContainer_rowChecker_id.release();
    _jspx_tagPool_liferay$1theme_defineObjects_nobody.release();
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text.release();
    _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody.release();
    _jspx_tagPool_aui_workflow$1status_status_showLabel_showIcon_markupView_nobody.release();
    _jspx_tagPool_liferay$1trash_defineObjects_nobody.release();
    _jspx_tagPool_liferay$1frontend_vertical$1card$1footer.release();
    _jspx_tagPool_liferay$1ui_search$1iterator_markupView_displayStyle_nobody.release();
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

BlogsGroupServiceSettings blogsGroupServiceSettings = BlogsGroupServiceSettings.getInstance(scopeGroupId);

      out.write('\n');
      out.write('\n');
      out.write('\n');
      out.write('\n');

String entriesNavigation = ParamUtil.getString(request, "entriesNavigation");

int delta = ParamUtil.getInteger(request, SearchContainer.DEFAULT_DELTA_PARAM);
String orderByCol = ParamUtil.getString(request, "orderByCol", "title");
String orderByType = ParamUtil.getString(request, "orderByType", "asc");

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcRenderCommandName", "/blogs/view");

if (delta > 0) {
	portletURL.setParameter("delta", String.valueOf(delta));
}

portletURL.setParameter("orderBycol", orderByCol);
portletURL.setParameter("orderByType", orderByType);

portletURL.setParameter("entriesNavigation", entriesNavigation);

SearchContainer entriesSearchContainer = new SearchContainer(renderRequest, PortletURLUtil.clone(portletURL, liferayPortletResponse), null, "no-entries-were-found");

entriesSearchContainer.setOrderByComparator(BlogsUtil.getOrderByComparator(orderByCol, orderByType));

BlogEntriesDisplayContext blogEntriesDisplayContext = new BlogEntriesDisplayContext(liferayPortletRequest);

blogEntriesDisplayContext.populateResults(entriesSearchContainer);

BlogEntriesManagementToolbarDisplayContext blogEntriesManagementToolbarDisplayContext = new BlogEntriesManagementToolbarDisplayContext(liferayPortletRequest, liferayPortletResponse, request, currentURLObj, trashHelper);

String displayStyle = blogEntriesManagementToolbarDisplayContext.getDisplayStyle();

      out.write('\n');
      out.write('\n');
      //  clay:management-toolbar
      com.liferay.frontend.taglib.clay.servlet.taglib.soy.ManagementToolbarTag _jspx_th_clay_management$1toolbar_0 = (com.liferay.frontend.taglib.clay.servlet.taglib.soy.ManagementToolbarTag) _jspx_tagPool_clay_management$1toolbar_viewTypeItems_sortingURL_sortingOrder_showInfoButton_searchFormName_searchContainerId_searchActionURL_itemsTotal_filterDropdownItems_disabled_creationMenu_componentId_clearResultsURL_actionDropdownItems_nobody.get(com.liferay.frontend.taglib.clay.servlet.taglib.soy.ManagementToolbarTag.class);
      _jspx_th_clay_management$1toolbar_0.setPageContext(_jspx_page_context);
      _jspx_th_clay_management$1toolbar_0.setParent(null);
      _jspx_th_clay_management$1toolbar_0.setActionDropdownItems( blogEntriesManagementToolbarDisplayContext.getActionDropdownItems() );
      _jspx_th_clay_management$1toolbar_0.setClearResultsURL( blogEntriesManagementToolbarDisplayContext.getSearchActionURL() );
      _jspx_th_clay_management$1toolbar_0.setComponentId("blogEntriesManagementToolbar");
      _jspx_th_clay_management$1toolbar_0.setCreationMenu( blogEntriesManagementToolbarDisplayContext.getCreationMenu() );
      _jspx_th_clay_management$1toolbar_0.setDisabled( entriesSearchContainer.getTotal() <= 0 );
      _jspx_th_clay_management$1toolbar_0.setFilterDropdownItems( blogEntriesManagementToolbarDisplayContext.getFilterDropdownItems() );
      _jspx_th_clay_management$1toolbar_0.setItemsTotal( entriesSearchContainer.getTotal() );
      _jspx_th_clay_management$1toolbar_0.setSearchActionURL( blogEntriesManagementToolbarDisplayContext.getSearchActionURL() );
      _jspx_th_clay_management$1toolbar_0.setSearchContainerId("blogEntries");
      _jspx_th_clay_management$1toolbar_0.setSearchFormName("searchFm");
      _jspx_th_clay_management$1toolbar_0.setShowInfoButton( false );
      _jspx_th_clay_management$1toolbar_0.setSortingOrder( blogEntriesManagementToolbarDisplayContext.getOrderByType() );
      _jspx_th_clay_management$1toolbar_0.setSortingURL( String.valueOf(blogEntriesManagementToolbarDisplayContext.getSortingURL()) );
      _jspx_th_clay_management$1toolbar_0.setViewTypeItems( blogEntriesManagementToolbarDisplayContext.getViewTypes() );
      int _jspx_eval_clay_management$1toolbar_0 = _jspx_th_clay_management$1toolbar_0.doStartTag();
      if (_jspx_th_clay_management$1toolbar_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_clay_management$1toolbar_viewTypeItems_sortingURL_sortingOrder_showInfoButton_searchFormName_searchContainerId_searchActionURL_itemsTotal_filterDropdownItems_disabled_creationMenu_componentId_clearResultsURL_actionDropdownItems_nobody.reuse(_jspx_th_clay_management$1toolbar_0);
        return;
      }
      _jspx_tagPool_clay_management$1toolbar_viewTypeItems_sortingURL_sortingOrder_showInfoButton_searchFormName_searchContainerId_searchActionURL_itemsTotal_filterDropdownItems_disabled_creationMenu_componentId_clearResultsURL_actionDropdownItems_nobody.reuse(_jspx_th_clay_management$1toolbar_0);
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
      out.write("\n\n<div class=\"container-fluid-1280 main-content-body\">\n\t");
      //  aui:form
      com.liferay.taglib.aui.FormTag _jspx_th_aui_form_0 = (com.liferay.taglib.aui.FormTag) _jspx_tagPool_aui_form_name_method_action.get(com.liferay.taglib.aui.FormTag.class);
      _jspx_th_aui_form_0.setPageContext(_jspx_page_context);
      _jspx_th_aui_form_0.setParent(null);
      _jspx_th_aui_form_0.setAction( portletURL.toString() );
      _jspx_th_aui_form_0.setMethod("get");
      _jspx_th_aui_form_0.setName("fm");
      int _jspx_eval_aui_form_0 = _jspx_th_aui_form_0.doStartTag();
      if (_jspx_eval_aui_form_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        out.write("\n\t\t");
        //  aui:input
        com.liferay.taglib.aui.InputTag _jspx_th_aui_input_0 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
        _jspx_th_aui_input_0.setPageContext(_jspx_page_context);
        _jspx_th_aui_input_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
        _jspx_th_aui_input_0.setName( Constants.CMD );
        _jspx_th_aui_input_0.setType("hidden");
        int _jspx_eval_aui_input_0 = _jspx_th_aui_input_0.doStartTag();
        if (_jspx_th_aui_input_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_aui_input_type_name_nobody.reuse(_jspx_th_aui_input_0);
          return;
        }
        _jspx_tagPool_aui_input_type_name_nobody.reuse(_jspx_th_aui_input_0);
        out.write("\n\t\t");
        //  aui:input
        com.liferay.taglib.aui.InputTag _jspx_th_aui_input_1 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
        _jspx_th_aui_input_1.setPageContext(_jspx_page_context);
        _jspx_th_aui_input_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
        _jspx_th_aui_input_1.setName("redirect");
        _jspx_th_aui_input_1.setType("hidden");
        _jspx_th_aui_input_1.setValue( portletURL.toString() );
        int _jspx_eval_aui_input_1 = _jspx_th_aui_input_1.doStartTag();
        if (_jspx_th_aui_input_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_1);
          return;
        }
        _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_1);
        out.write("\n\t\t");
        if (_jspx_meth_aui_input_2((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_form_0, _jspx_page_context))
          return;
        out.write("\n\n\t\t");
        //  liferay-asset:categorization-filter
        com.liferay.asset.taglib.servlet.taglib.CategorizationFilterTag _jspx_th_liferay$1asset_categorization$1filter_0 = (com.liferay.asset.taglib.servlet.taglib.CategorizationFilterTag) _jspx_tagPool_liferay$1asset_categorization$1filter_portletURL_assetType_nobody.get(com.liferay.asset.taglib.servlet.taglib.CategorizationFilterTag.class);
        _jspx_th_liferay$1asset_categorization$1filter_0.setPageContext(_jspx_page_context);
        _jspx_th_liferay$1asset_categorization$1filter_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
        _jspx_th_liferay$1asset_categorization$1filter_0.setAssetType("entries");
        _jspx_th_liferay$1asset_categorization$1filter_0.setPortletURL( portletURL );
        int _jspx_eval_liferay$1asset_categorization$1filter_0 = _jspx_th_liferay$1asset_categorization$1filter_0.doStartTag();
        if (_jspx_th_liferay$1asset_categorization$1filter_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_liferay$1asset_categorization$1filter_portletURL_assetType_nobody.reuse(_jspx_th_liferay$1asset_categorization$1filter_0);
          return;
        }
        _jspx_tagPool_liferay$1asset_categorization$1filter_portletURL_assetType_nobody.reuse(_jspx_th_liferay$1asset_categorization$1filter_0);
        out.write("\n\n\t\t");
        //  liferay-ui:search-container
        com.liferay.taglib.ui.SearchContainerTag _jspx_th_liferay$1ui_search$1container_0 = (com.liferay.taglib.ui.SearchContainerTag) _jspx_tagPool_liferay$1ui_search$1container_searchContainer_rowChecker_id.get(com.liferay.taglib.ui.SearchContainerTag.class);
        _jspx_th_liferay$1ui_search$1container_0.setPageContext(_jspx_page_context);
        _jspx_th_liferay$1ui_search$1container_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
        _jspx_th_liferay$1ui_search$1container_0.setId("blogEntries");
        _jspx_th_liferay$1ui_search$1container_0.setRowChecker( new EmptyOnClickRowChecker(renderResponse) );
        _jspx_th_liferay$1ui_search$1container_0.setSearchContainer( entriesSearchContainer );
        int _jspx_eval_liferay$1ui_search$1container_0 = _jspx_th_liferay$1ui_search$1container_0.doStartTag();
        if (_jspx_eval_liferay$1ui_search$1container_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          java.lang.Integer total = null;
          com.liferay.portal.kernel.dao.search.SearchContainer searchContainer = null;
          total = (java.lang.Integer) _jspx_page_context.findAttribute("total");
          searchContainer = (com.liferay.portal.kernel.dao.search.SearchContainer) _jspx_page_context.findAttribute("searchContainer");
          out.write("\n\t\t\t");
          //  liferay-ui:search-container-row
          com.liferay.taglib.ui.SearchContainerRowTag _jspx_th_liferay$1ui_search$1container$1row_0 = (com.liferay.taglib.ui.SearchContainerRowTag) _jspx_tagPool_liferay$1ui_search$1container$1row_modelVar_keyProperty_escapedModel_className.get(com.liferay.taglib.ui.SearchContainerRowTag.class);
          _jspx_th_liferay$1ui_search$1container$1row_0.setPageContext(_jspx_page_context);
          _jspx_th_liferay$1ui_search$1container$1row_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container_0);
          _jspx_th_liferay$1ui_search$1container$1row_0.setClassName("com.liferay.blogs.model.BlogsEntry");
          _jspx_th_liferay$1ui_search$1container$1row_0.setEscapedModel( true );
          _jspx_th_liferay$1ui_search$1container$1row_0.setKeyProperty("entryId");
          _jspx_th_liferay$1ui_search$1container$1row_0.setModelVar("entry");
          int _jspx_eval_liferay$1ui_search$1container$1row_0 = _jspx_th_liferay$1ui_search$1container$1row_0.doStartTag();
          if (_jspx_eval_liferay$1ui_search$1container$1row_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            java.lang.Integer index = null;
            com.liferay.blogs.model.BlogsEntry entry = null;
            com.liferay.portal.kernel.dao.search.ResultRow row = null;
            if (_jspx_eval_liferay$1ui_search$1container$1row_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
              out = _jspx_page_context.pushBody();
              _jspx_th_liferay$1ui_search$1container$1row_0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
              _jspx_th_liferay$1ui_search$1container$1row_0.doInitBody();
            }
            index = (java.lang.Integer) _jspx_page_context.findAttribute("index");
            entry = (com.liferay.blogs.model.BlogsEntry) _jspx_page_context.findAttribute("entry");
            row = (com.liferay.portal.kernel.dao.search.ResultRow) _jspx_page_context.findAttribute("row");
            do {
              out.write("\n\t\t\t\t");
              //  liferay-portlet:renderURL
              com.liferay.taglib.portlet.RenderURLTag _jspx_th_liferay$1portlet_renderURL_0 = (com.liferay.taglib.portlet.RenderURLTag) _jspx_tagPool_liferay$1portlet_renderURL_varImpl.get(com.liferay.taglib.portlet.RenderURLTag.class);
              _jspx_th_liferay$1portlet_renderURL_0.setPageContext(_jspx_page_context);
              _jspx_th_liferay$1portlet_renderURL_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1row_0);
              _jspx_th_liferay$1portlet_renderURL_0.setVarImpl("rowURL");
              int _jspx_eval_liferay$1portlet_renderURL_0 = _jspx_th_liferay$1portlet_renderURL_0.doStartTag();
              if (_jspx_eval_liferay$1portlet_renderURL_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\t\t\t\t\t");
                if (_jspx_meth_portlet_param_1((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1portlet_renderURL_0, _jspx_page_context))
                  return;
                out.write("\n\t\t\t\t\t");
                //  portlet:param
                com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_2 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                _jspx_th_portlet_param_2.setPageContext(_jspx_page_context);
                _jspx_th_portlet_param_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_0);
                _jspx_th_portlet_param_2.setName("redirect");
                _jspx_th_portlet_param_2.setValue( entriesSearchContainer.getIteratorURL().toString() );
                int _jspx_eval_portlet_param_2 = _jspx_th_portlet_param_2.doStartTag();
                if (_jspx_th_portlet_param_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_2);
                  return;
                }
                _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_2);
                out.write("\n\t\t\t\t\t");
                //  portlet:param
                com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_3 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                _jspx_th_portlet_param_3.setPageContext(_jspx_page_context);
                _jspx_th_portlet_param_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_0);
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
              if (_jspx_th_liferay$1portlet_renderURL_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_liferay$1portlet_renderURL_varImpl.reuse(_jspx_th_liferay$1portlet_renderURL_0);
                return;
              }
              _jspx_tagPool_liferay$1portlet_renderURL_varImpl.reuse(_jspx_th_liferay$1portlet_renderURL_0);
              com.liferay.portal.kernel.portlet.LiferayPortletURL rowURL = null;
              rowURL = (com.liferay.portal.kernel.portlet.LiferayPortletURL) _jspx_page_context.findAttribute("rowURL");
              out.write("\n\n\t\t\t\t");
              out.write('\n');
              out.write('\n');
              //  c:choose
              com.liferay.taglib.core.ChooseTag _jspx_th_c_choose_0 = (com.liferay.taglib.core.ChooseTag) _jspx_tagPool_c_choose.get(com.liferay.taglib.core.ChooseTag.class);
              _jspx_th_c_choose_0.setPageContext(_jspx_page_context);
              _jspx_th_c_choose_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1row_0);
              int _jspx_eval_c_choose_0 = _jspx_th_c_choose_0.doStartTag();
              if (_jspx_eval_c_choose_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write('\n');
                out.write('	');
                //  c:when
                com.liferay.taglib.core.WhenTag _jspx_th_c_when_0 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
                _jspx_th_c_when_0.setPageContext(_jspx_page_context);
                _jspx_th_c_when_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_0);
                _jspx_th_c_when_0.setTest( displayStyle.equals("descriptive") );
                int _jspx_eval_c_when_0 = _jspx_th_c_when_0.doStartTag();
                if (_jspx_eval_c_when_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  out.write("\n\t\t");
                  //  liferay-ui:search-container-column-user
                  com.liferay.taglib.ui.SearchContainerColumnUserTag _jspx_th_liferay$1ui_search$1container$1column$1user_0 = (com.liferay.taglib.ui.SearchContainerColumnUserTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1user_userId_showDetails_nobody.get(com.liferay.taglib.ui.SearchContainerColumnUserTag.class);
                  _jspx_th_liferay$1ui_search$1container$1column$1user_0.setPageContext(_jspx_page_context);
                  _jspx_th_liferay$1ui_search$1container$1column$1user_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_0);
                  _jspx_th_liferay$1ui_search$1container$1column$1user_0.setShowDetails( false );
                  _jspx_th_liferay$1ui_search$1container$1column$1user_0.setUserId( entry.getUserId() );
                  int _jspx_eval_liferay$1ui_search$1container$1column$1user_0 = _jspx_th_liferay$1ui_search$1container$1column$1user_0.doStartTag();
                  if (_jspx_th_liferay$1ui_search$1container$1column$1user_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_liferay$1ui_search$1container$1column$1user_userId_showDetails_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1user_0);
                    return;
                  }
                  _jspx_tagPool_liferay$1ui_search$1container$1column$1user_userId_showDetails_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1user_0);
                  out.write("\n\n\t\t");
                  //  liferay-ui:search-container-column-text
                  com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_0 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text_colspan.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
                  _jspx_th_liferay$1ui_search$1container$1column$1text_0.setPageContext(_jspx_page_context);
                  _jspx_th_liferay$1ui_search$1container$1column$1text_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_0);
                  _jspx_th_liferay$1ui_search$1container$1column$1text_0.setColspan( 2 );
                  int _jspx_eval_liferay$1ui_search$1container$1column$1text_0 = _jspx_th_liferay$1ui_search$1container$1column$1text_0.doStartTag();
                  if (_jspx_eval_liferay$1ui_search$1container$1column$1text_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    if (_jspx_eval_liferay$1ui_search$1container$1column$1text_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                      out = _jspx_page_context.pushBody();
                      _jspx_th_liferay$1ui_search$1container$1column$1text_0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                      _jspx_th_liferay$1ui_search$1container$1column$1text_0.doInitBody();
                    }
                    do {
                      out.write("\n\n\t\t\t");

			Date modifiedDate = entry.getModifiedDate();

			String modifiedDateDescription = LanguageUtil.getTimeDescription(request, System.currentTimeMillis() - modifiedDate.getTime(), true);
			
                      out.write("\n\n\t\t\t<h5 class=\"text-default\">\n\t\t\t\t");
                      //  liferay-ui:message
                      com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_0 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_arguments_nobody.get(com.liferay.taglib.ui.MessageTag.class);
                      _jspx_th_liferay$1ui_message_0.setPageContext(_jspx_page_context);
                      _jspx_th_liferay$1ui_message_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1column$1text_0);
                      _jspx_th_liferay$1ui_message_0.setArguments( new String[] {entry.getUserName(), modifiedDateDescription} );
                      _jspx_th_liferay$1ui_message_0.setKey("x-modified-x-ago");
                      int _jspx_eval_liferay$1ui_message_0 = _jspx_th_liferay$1ui_message_0.doStartTag();
                      if (_jspx_th_liferay$1ui_message_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_liferay$1ui_message_key_arguments_nobody.reuse(_jspx_th_liferay$1ui_message_0);
                        return;
                      }
                      _jspx_tagPool_liferay$1ui_message_key_arguments_nobody.reuse(_jspx_th_liferay$1ui_message_0);
                      out.write("\n\t\t\t</h5>\n\n\t\t\t<h4>\n\t\t\t\t");
                      //  c:choose
                      com.liferay.taglib.core.ChooseTag _jspx_th_c_choose_1 = (com.liferay.taglib.core.ChooseTag) _jspx_tagPool_c_choose.get(com.liferay.taglib.core.ChooseTag.class);
                      _jspx_th_c_choose_1.setPageContext(_jspx_page_context);
                      _jspx_th_c_choose_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1column$1text_0);
                      int _jspx_eval_c_choose_1 = _jspx_th_c_choose_1.doStartTag();
                      if (_jspx_eval_c_choose_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                        out.write("\n\t\t\t\t\t");
                        //  c:when
                        com.liferay.taglib.core.WhenTag _jspx_th_c_when_1 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
                        _jspx_th_c_when_1.setPageContext(_jspx_page_context);
                        _jspx_th_c_when_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_1);
                        _jspx_th_c_when_1.setTest( BlogsEntryPermission.contains(permissionChecker, entry, ActionKeys.UPDATE) );
                        int _jspx_eval_c_when_1 = _jspx_th_c_when_1.doStartTag();
                        if (_jspx_eval_c_when_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                          out.write("\n\t\t\t\t\t\t");
                          //  aui:a
                          com.liferay.taglib.aui.ATag _jspx_th_aui_a_0 = (com.liferay.taglib.aui.ATag) _jspx_tagPool_aui_a_href.get(com.liferay.taglib.aui.ATag.class);
                          _jspx_th_aui_a_0.setPageContext(_jspx_page_context);
                          _jspx_th_aui_a_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_1);
                          _jspx_th_aui_a_0.setHref( rowURL.toString() );
                          int _jspx_eval_aui_a_0 = _jspx_th_aui_a_0.doStartTag();
                          if (_jspx_eval_aui_a_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                            out.write("\n\t\t\t\t\t\t\t");
                            out.print( BlogsEntryUtil.getDisplayTitle(resourceBundle, entry) );
                            out.write("\n\t\t\t\t\t\t");
                          }
                          if (_jspx_th_aui_a_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                            _jspx_tagPool_aui_a_href.reuse(_jspx_th_aui_a_0);
                            return;
                          }
                          _jspx_tagPool_aui_a_href.reuse(_jspx_th_aui_a_0);
                          out.write("\n\t\t\t\t\t");
                        }
                        if (_jspx_th_c_when_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                          _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_1);
                          return;
                        }
                        _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_1);
                        out.write("\n\t\t\t\t\t");
                        //  c:otherwise
                        com.liferay.taglib.core.OtherwiseTag _jspx_th_c_otherwise_0 = (com.liferay.taglib.core.OtherwiseTag) _jspx_tagPool_c_otherwise.get(com.liferay.taglib.core.OtherwiseTag.class);
                        _jspx_th_c_otherwise_0.setPageContext(_jspx_page_context);
                        _jspx_th_c_otherwise_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_1);
                        int _jspx_eval_c_otherwise_0 = _jspx_th_c_otherwise_0.doStartTag();
                        if (_jspx_eval_c_otherwise_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                          out.write("\n\t\t\t\t\t\t");
                          out.print( BlogsEntryUtil.getDisplayTitle(resourceBundle, entry) );
                          out.write("\n\t\t\t\t\t");
                        }
                        if (_jspx_th_c_otherwise_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                          _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_0);
                          return;
                        }
                        _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_0);
                        out.write("\n\t\t\t\t");
                      }
                      if (_jspx_th_c_choose_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_1);
                        return;
                      }
                      _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_1);
                      out.write("\n\t\t\t</h4>\n\n\t\t\t<h5 class=\"text-default\">\n\t\t\t\t");
                      //  aui:workflow-status
                      com.liferay.taglib.aui.WorkflowStatusTag _jspx_th_aui_workflow$1status_0 = (com.liferay.taglib.aui.WorkflowStatusTag) _jspx_tagPool_aui_workflow$1status_status_showLabel_showIcon_markupView_nobody.get(com.liferay.taglib.aui.WorkflowStatusTag.class);
                      _jspx_th_aui_workflow$1status_0.setPageContext(_jspx_page_context);
                      _jspx_th_aui_workflow$1status_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1column$1text_0);
                      _jspx_th_aui_workflow$1status_0.setMarkupView("lexicon");
                      _jspx_th_aui_workflow$1status_0.setShowIcon( false );
                      _jspx_th_aui_workflow$1status_0.setShowLabel( false );
                      _jspx_th_aui_workflow$1status_0.setStatus( entry.getStatus() );
                      int _jspx_eval_aui_workflow$1status_0 = _jspx_th_aui_workflow$1status_0.doStartTag();
                      if (_jspx_th_aui_workflow$1status_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_aui_workflow$1status_status_showLabel_showIcon_markupView_nobody.reuse(_jspx_th_aui_workflow$1status_0);
                        return;
                      }
                      _jspx_tagPool_aui_workflow$1status_status_showLabel_showIcon_markupView_nobody.reuse(_jspx_th_aui_workflow$1status_0);
                      out.write("\n\t\t\t</h5>\n\t\t");
                      int evalDoAfterBody = _jspx_th_liferay$1ui_search$1container$1column$1text_0.doAfterBody();
                      if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                        break;
                    } while (true);
                    if (_jspx_eval_liferay$1ui_search$1container$1column$1text_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                      out = _jspx_page_context.popBody();
                  }
                  if (_jspx_th_liferay$1ui_search$1container$1column$1text_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_colspan.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_0);
                    return;
                  }
                  _jspx_tagPool_liferay$1ui_search$1container$1column$1text_colspan.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_0);
                  out.write("\n\n\t\t");
                  if (_jspx_meth_liferay$1ui_search$1container$1column$1jsp_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_when_0, _jspx_page_context))
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
                //  c:when
                com.liferay.taglib.core.WhenTag _jspx_th_c_when_2 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
                _jspx_th_c_when_2.setPageContext(_jspx_page_context);
                _jspx_th_c_when_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_0);
                _jspx_th_c_when_2.setTest( displayStyle.equals("icon") );
                int _jspx_eval_c_when_2 = _jspx_th_c_when_2.doStartTag();
                if (_jspx_eval_c_when_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  out.write("\n\n\t\t");

		row.setCssClass("entry-card lfr-asset-item");
		
                  out.write("\n\n\t\t");
                  //  liferay-ui:search-container-column-text
                  com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_1 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
                  _jspx_th_liferay$1ui_search$1container$1column$1text_1.setPageContext(_jspx_page_context);
                  _jspx_th_liferay$1ui_search$1container$1column$1text_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_2);
                  int _jspx_eval_liferay$1ui_search$1container$1column$1text_1 = _jspx_th_liferay$1ui_search$1container$1column$1text_1.doStartTag();
                  if (_jspx_eval_liferay$1ui_search$1container$1column$1text_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    if (_jspx_eval_liferay$1ui_search$1container$1column$1text_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                      out = _jspx_page_context.pushBody();
                      _jspx_th_liferay$1ui_search$1container$1column$1text_1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                      _jspx_th_liferay$1ui_search$1container$1column$1text_1.doInitBody();
                    }
                    do {
                      out.write("\n\n\t\t\t");

			String entryImageURL = entry.getCoverImageURL(themeDisplay);

			if (Validator.isNull(entryImageURL)) {
				entryImageURL = entry.getSmallImageURL(themeDisplay);
			}
			
                      out.write("\n\n\t\t\t");
                      //  c:choose
                      com.liferay.taglib.core.ChooseTag _jspx_th_c_choose_2 = (com.liferay.taglib.core.ChooseTag) _jspx_tagPool_c_choose.get(com.liferay.taglib.core.ChooseTag.class);
                      _jspx_th_c_choose_2.setPageContext(_jspx_page_context);
                      _jspx_th_c_choose_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1column$1text_1);
                      int _jspx_eval_c_choose_2 = _jspx_th_c_choose_2.doStartTag();
                      if (_jspx_eval_c_choose_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                        out.write("\n\t\t\t\t");
                        //  c:when
                        com.liferay.taglib.core.WhenTag _jspx_th_c_when_3 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
                        _jspx_th_c_when_3.setPageContext(_jspx_page_context);
                        _jspx_th_c_when_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_2);
                        _jspx_th_c_when_3.setTest( Validator.isNotNull(entryImageURL) );
                        int _jspx_eval_c_when_3 = _jspx_th_c_when_3.doStartTag();
                        if (_jspx_eval_c_when_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                          out.write("\n\t\t\t\t\t");
                          //  liferay-frontend:vertical-card
                          com.liferay.frontend.taglib.servlet.taglib.VerticalCardTag _jspx_th_liferay$1frontend_vertical$1card_0 = (com.liferay.frontend.taglib.servlet.taglib.VerticalCardTag) _jspx_tagPool_liferay$1frontend_vertical$1card_url_title_rowChecker_resultRow_imageUrl_cssClass_actionJspServletContext_actionJsp.get(com.liferay.frontend.taglib.servlet.taglib.VerticalCardTag.class);
                          _jspx_th_liferay$1frontend_vertical$1card_0.setPageContext(_jspx_page_context);
                          _jspx_th_liferay$1frontend_vertical$1card_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_3);
                          _jspx_th_liferay$1frontend_vertical$1card_0.setActionJsp("/blogs_admin/entry_action.jsp");
                          _jspx_th_liferay$1frontend_vertical$1card_0.setActionJspServletContext( application );
                          _jspx_th_liferay$1frontend_vertical$1card_0.setCssClass("entry-display-style");
                          _jspx_th_liferay$1frontend_vertical$1card_0.setImageUrl( entryImageURL );
                          _jspx_th_liferay$1frontend_vertical$1card_0.setResultRow( row );
                          _jspx_th_liferay$1frontend_vertical$1card_0.setRowChecker( searchContainer.getRowChecker() );
                          _jspx_th_liferay$1frontend_vertical$1card_0.setTitle( BlogsEntryUtil.getDisplayTitle(resourceBundle, entry) );
                          _jspx_th_liferay$1frontend_vertical$1card_0.setUrl( BlogsEntryPermission.contains(permissionChecker, entry, ActionKeys.UPDATE) ? rowURL.toString() : null );
                          int _jspx_eval_liferay$1frontend_vertical$1card_0 = _jspx_th_liferay$1frontend_vertical$1card_0.doStartTag();
                          if (_jspx_eval_liferay$1frontend_vertical$1card_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                            out.write("\n\t\t\t\t\t\t");
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
                              com.liferay.taglib.ui.UserPortraitTag _jspx_th_liferay$1ui_user$1portrait_0 = (com.liferay.taglib.ui.UserPortraitTag) _jspx_tagPool_liferay$1ui_user$1portrait_userId_cssClass_nobody.get(com.liferay.taglib.ui.UserPortraitTag.class);
                              _jspx_th_liferay$1ui_user$1portrait_0.setPageContext(_jspx_page_context);
                              _jspx_th_liferay$1ui_user$1portrait_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_vertical$1card$1sticker$1bottom_0);
                              _jspx_th_liferay$1ui_user$1portrait_0.setCssClass("sticker sticker-bottom");
                              _jspx_th_liferay$1ui_user$1portrait_0.setUserId( entry.getUserId() );
                              int _jspx_eval_liferay$1ui_user$1portrait_0 = _jspx_th_liferay$1ui_user$1portrait_0.doStartTag();
                              if (_jspx_th_liferay$1ui_user$1portrait_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_liferay$1ui_user$1portrait_userId_cssClass_nobody.reuse(_jspx_th_liferay$1ui_user$1portrait_0);
                              return;
                              }
                              _jspx_tagPool_liferay$1ui_user$1portrait_userId_cssClass_nobody.reuse(_jspx_th_liferay$1ui_user$1portrait_0);
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
                              _jspx_th_aui_workflow$1status_1.setStatus( entry.getStatus() );
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
                              out.write('\n');
                              out.write('	');
                              //  liferay-ui:message
                              com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_1 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody.get(com.liferay.taglib.ui.MessageTag.class);
                              _jspx_th_liferay$1ui_message_1.setPageContext(_jspx_page_context);
                              _jspx_th_liferay$1ui_message_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_vertical$1card$1header_0);
                              _jspx_th_liferay$1ui_message_1.setArguments( new String[] {LanguageUtil.getTimeDescription(locale, System.currentTimeMillis() - entry.getModifiedDate().getTime(), true), HtmlUtil.escape(entry.getUserName())} );
                              _jspx_th_liferay$1ui_message_1.setKey("x-ago-by-x");
                              _jspx_th_liferay$1ui_message_1.setTranslateArguments( true );
                              int _jspx_eval_liferay$1ui_message_1 = _jspx_th_liferay$1ui_message_1.doStartTag();
                              if (_jspx_th_liferay$1ui_message_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody.reuse(_jspx_th_liferay$1ui_message_1);
                              return;
                              }
                              _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody.reuse(_jspx_th_liferay$1ui_message_1);
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
                            out.write("\n\t\t\t\t\t");
                          }
                          if (_jspx_th_liferay$1frontend_vertical$1card_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                            _jspx_tagPool_liferay$1frontend_vertical$1card_url_title_rowChecker_resultRow_imageUrl_cssClass_actionJspServletContext_actionJsp.reuse(_jspx_th_liferay$1frontend_vertical$1card_0);
                            return;
                          }
                          _jspx_tagPool_liferay$1frontend_vertical$1card_url_title_rowChecker_resultRow_imageUrl_cssClass_actionJspServletContext_actionJsp.reuse(_jspx_th_liferay$1frontend_vertical$1card_0);
                          out.write("\n\t\t\t\t");
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
                          //  liferay-frontend:icon-vertical-card
                          com.liferay.frontend.taglib.servlet.taglib.IconVerticalCardTag _jspx_th_liferay$1frontend_icon$1vertical$1card_0 = (com.liferay.frontend.taglib.servlet.taglib.IconVerticalCardTag) _jspx_tagPool_liferay$1frontend_icon$1vertical$1card_url_title_rowChecker_resultRow_icon_actionJspServletContext_actionJsp.get(com.liferay.frontend.taglib.servlet.taglib.IconVerticalCardTag.class);
                          _jspx_th_liferay$1frontend_icon$1vertical$1card_0.setPageContext(_jspx_page_context);
                          _jspx_th_liferay$1frontend_icon$1vertical$1card_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_1);
                          _jspx_th_liferay$1frontend_icon$1vertical$1card_0.setActionJsp("/blogs_admin/entry_action.jsp");
                          _jspx_th_liferay$1frontend_icon$1vertical$1card_0.setActionJspServletContext( application );
                          _jspx_th_liferay$1frontend_icon$1vertical$1card_0.setIcon("blogs");
                          _jspx_th_liferay$1frontend_icon$1vertical$1card_0.setResultRow( row );
                          _jspx_th_liferay$1frontend_icon$1vertical$1card_0.setRowChecker( searchContainer.getRowChecker() );
                          _jspx_th_liferay$1frontend_icon$1vertical$1card_0.setTitle( BlogsEntryUtil.getDisplayTitle(resourceBundle, entry) );
                          _jspx_th_liferay$1frontend_icon$1vertical$1card_0.setUrl( BlogsEntryPermission.contains(permissionChecker, entry, ActionKeys.UPDATE) ? rowURL.toString() : null );
                          int _jspx_eval_liferay$1frontend_icon$1vertical$1card_0 = _jspx_th_liferay$1frontend_icon$1vertical$1card_0.doStartTag();
                          if (_jspx_eval_liferay$1frontend_icon$1vertical$1card_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                            out.write("\n\t\t\t\t\t\t");
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
                              com.liferay.taglib.ui.UserPortraitTag _jspx_th_liferay$1ui_user$1portrait_1 = (com.liferay.taglib.ui.UserPortraitTag) _jspx_tagPool_liferay$1ui_user$1portrait_userId_cssClass_nobody.get(com.liferay.taglib.ui.UserPortraitTag.class);
                              _jspx_th_liferay$1ui_user$1portrait_1.setPageContext(_jspx_page_context);
                              _jspx_th_liferay$1ui_user$1portrait_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_vertical$1card$1sticker$1bottom_1);
                              _jspx_th_liferay$1ui_user$1portrait_1.setCssClass("sticker sticker-bottom");
                              _jspx_th_liferay$1ui_user$1portrait_1.setUserId( entry.getUserId() );
                              int _jspx_eval_liferay$1ui_user$1portrait_1 = _jspx_th_liferay$1ui_user$1portrait_1.doStartTag();
                              if (_jspx_th_liferay$1ui_user$1portrait_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_liferay$1ui_user$1portrait_userId_cssClass_nobody.reuse(_jspx_th_liferay$1ui_user$1portrait_1);
                              return;
                              }
                              _jspx_tagPool_liferay$1ui_user$1portrait_userId_cssClass_nobody.reuse(_jspx_th_liferay$1ui_user$1portrait_1);
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
                              _jspx_th_aui_workflow$1status_2.setStatus( entry.getStatus() );
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
                              out.write('\n');
                              out.write('	');
                              //  liferay-ui:message
                              com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_2 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody.get(com.liferay.taglib.ui.MessageTag.class);
                              _jspx_th_liferay$1ui_message_2.setPageContext(_jspx_page_context);
                              _jspx_th_liferay$1ui_message_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_vertical$1card$1header_1);
                              _jspx_th_liferay$1ui_message_2.setArguments( new String[] {LanguageUtil.getTimeDescription(locale, System.currentTimeMillis() - entry.getModifiedDate().getTime(), true), HtmlUtil.escape(entry.getUserName())} );
                              _jspx_th_liferay$1ui_message_2.setKey("x-ago-by-x");
                              _jspx_th_liferay$1ui_message_2.setTranslateArguments( true );
                              int _jspx_eval_liferay$1ui_message_2 = _jspx_th_liferay$1ui_message_2.doStartTag();
                              if (_jspx_th_liferay$1ui_message_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody.reuse(_jspx_th_liferay$1ui_message_2);
                              return;
                              }
                              _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody.reuse(_jspx_th_liferay$1ui_message_2);
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
                            out.write("\n\t\t\t\t\t");
                          }
                          if (_jspx_th_liferay$1frontend_icon$1vertical$1card_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                            _jspx_tagPool_liferay$1frontend_icon$1vertical$1card_url_title_rowChecker_resultRow_icon_actionJspServletContext_actionJsp.reuse(_jspx_th_liferay$1frontend_icon$1vertical$1card_0);
                            return;
                          }
                          _jspx_tagPool_liferay$1frontend_icon$1vertical$1card_url_title_rowChecker_resultRow_icon_actionJspServletContext_actionJsp.reuse(_jspx_th_liferay$1frontend_icon$1vertical$1card_0);
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
                      int evalDoAfterBody = _jspx_th_liferay$1ui_search$1container$1column$1text_1.doAfterBody();
                      if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                        break;
                    } while (true);
                    if (_jspx_eval_liferay$1ui_search$1container$1column$1text_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                      out = _jspx_page_context.popBody();
                  }
                  if (_jspx_th_liferay$1ui_search$1container$1column$1text_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_liferay$1ui_search$1container$1column$1text.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_1);
                    return;
                  }
                  _jspx_tagPool_liferay$1ui_search$1container$1column$1text.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_1);
                  out.write('\n');
                  out.write('	');
                }
                if (_jspx_th_c_when_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_2);
                  return;
                }
                _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_2);
                out.write('\n');
                out.write('	');
                //  c:otherwise
                com.liferay.taglib.core.OtherwiseTag _jspx_th_c_otherwise_2 = (com.liferay.taglib.core.OtherwiseTag) _jspx_tagPool_c_otherwise.get(com.liferay.taglib.core.OtherwiseTag.class);
                _jspx_th_c_otherwise_2.setPageContext(_jspx_page_context);
                _jspx_th_c_otherwise_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_0);
                int _jspx_eval_c_otherwise_2 = _jspx_th_c_otherwise_2.doStartTag();
                if (_jspx_eval_c_otherwise_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  out.write("\n\t\t");
                  //  liferay-ui:search-container-column-text
                  com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_2 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_orderable_name_href_cssClass_nobody.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
                  _jspx_th_liferay$1ui_search$1container$1column$1text_2.setPageContext(_jspx_page_context);
                  _jspx_th_liferay$1ui_search$1container$1column$1text_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_2);
                  _jspx_th_liferay$1ui_search$1container$1column$1text_2.setCssClass("table-cell-content");
                  _jspx_th_liferay$1ui_search$1container$1column$1text_2.setHref( BlogsEntryPermission.contains(permissionChecker, entry, ActionKeys.UPDATE) ? rowURL : null );
                  _jspx_th_liferay$1ui_search$1container$1column$1text_2.setName("title");
                  _jspx_th_liferay$1ui_search$1container$1column$1text_2.setOrderable( false );
                  _jspx_th_liferay$1ui_search$1container$1column$1text_2.setValue( BlogsEntryUtil.getDisplayTitle(resourceBundle, entry) );
                  int _jspx_eval_liferay$1ui_search$1container$1column$1text_2 = _jspx_th_liferay$1ui_search$1container$1column$1text_2.doStartTag();
                  if (_jspx_th_liferay$1ui_search$1container$1column$1text_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_orderable_name_href_cssClass_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_2);
                    return;
                  }
                  _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_orderable_name_href_cssClass_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_2);
                  out.write("\n\n\t\t");
                  //  liferay-ui:search-container-column-text
                  com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_3 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text_property_orderable_name_nobody.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
                  _jspx_th_liferay$1ui_search$1container$1column$1text_3.setPageContext(_jspx_page_context);
                  _jspx_th_liferay$1ui_search$1container$1column$1text_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_2);
                  _jspx_th_liferay$1ui_search$1container$1column$1text_3.setName("author");
                  _jspx_th_liferay$1ui_search$1container$1column$1text_3.setOrderable( false );
                  _jspx_th_liferay$1ui_search$1container$1column$1text_3.setProperty("userName");
                  int _jspx_eval_liferay$1ui_search$1container$1column$1text_3 = _jspx_th_liferay$1ui_search$1container$1column$1text_3.doStartTag();
                  if (_jspx_th_liferay$1ui_search$1container$1column$1text_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_property_orderable_name_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_3);
                    return;
                  }
                  _jspx_tagPool_liferay$1ui_search$1container$1column$1text_property_orderable_name_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_3);
                  out.write("\n\n\t\t");
                  //  liferay-ui:search-container-column-date
                  com.liferay.taglib.ui.SearchContainerColumnDateTag _jspx_th_liferay$1ui_search$1container$1column$1date_0 = (com.liferay.taglib.ui.SearchContainerColumnDateTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1date_property_orderable_name_nobody.get(com.liferay.taglib.ui.SearchContainerColumnDateTag.class);
                  _jspx_th_liferay$1ui_search$1container$1column$1date_0.setPageContext(_jspx_page_context);
                  _jspx_th_liferay$1ui_search$1container$1column$1date_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_2);
                  _jspx_th_liferay$1ui_search$1container$1column$1date_0.setName("create-date");
                  _jspx_th_liferay$1ui_search$1container$1column$1date_0.setOrderable( false );
                  _jspx_th_liferay$1ui_search$1container$1column$1date_0.setProperty("createDate");
                  int _jspx_eval_liferay$1ui_search$1container$1column$1date_0 = _jspx_th_liferay$1ui_search$1container$1column$1date_0.doStartTag();
                  if (_jspx_th_liferay$1ui_search$1container$1column$1date_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_liferay$1ui_search$1container$1column$1date_property_orderable_name_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1date_0);
                    return;
                  }
                  _jspx_tagPool_liferay$1ui_search$1container$1column$1date_property_orderable_name_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1date_0);
                  out.write("\n\n\t\t");
                  //  liferay-ui:search-container-column-date
                  com.liferay.taglib.ui.SearchContainerColumnDateTag _jspx_th_liferay$1ui_search$1container$1column$1date_1 = (com.liferay.taglib.ui.SearchContainerColumnDateTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1date_property_orderable_name_nobody.get(com.liferay.taglib.ui.SearchContainerColumnDateTag.class);
                  _jspx_th_liferay$1ui_search$1container$1column$1date_1.setPageContext(_jspx_page_context);
                  _jspx_th_liferay$1ui_search$1container$1column$1date_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_2);
                  _jspx_th_liferay$1ui_search$1container$1column$1date_1.setName("display-date");
                  _jspx_th_liferay$1ui_search$1container$1column$1date_1.setOrderable( false );
                  _jspx_th_liferay$1ui_search$1container$1column$1date_1.setProperty("displayDate");
                  int _jspx_eval_liferay$1ui_search$1container$1column$1date_1 = _jspx_th_liferay$1ui_search$1container$1column$1date_1.doStartTag();
                  if (_jspx_th_liferay$1ui_search$1container$1column$1date_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_liferay$1ui_search$1container$1column$1date_property_orderable_name_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1date_1);
                    return;
                  }
                  _jspx_tagPool_liferay$1ui_search$1container$1column$1date_property_orderable_name_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1date_1);
                  out.write("\n\n\t\t");

		AssetEntry assetEntry = AssetEntryLocalServiceUtil.getEntry(BlogsEntry.class.getName(), entry.getEntryId());
		
                  out.write("\n\n\t\t");
                  //  liferay-ui:search-container-column-text
                  com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_4 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_orderable_name_nobody.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
                  _jspx_th_liferay$1ui_search$1container$1column$1text_4.setPageContext(_jspx_page_context);
                  _jspx_th_liferay$1ui_search$1container$1column$1text_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_2);
                  _jspx_th_liferay$1ui_search$1container$1column$1text_4.setName("views");
                  _jspx_th_liferay$1ui_search$1container$1column$1text_4.setOrderable( false );
                  _jspx_th_liferay$1ui_search$1container$1column$1text_4.setValue( String.valueOf(assetEntry.getViewCount()) );
                  int _jspx_eval_liferay$1ui_search$1container$1column$1text_4 = _jspx_th_liferay$1ui_search$1container$1column$1text_4.doStartTag();
                  if (_jspx_th_liferay$1ui_search$1container$1column$1text_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_orderable_name_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_4);
                    return;
                  }
                  _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_orderable_name_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_4);
                  out.write("\n\n\t\t");
                  if (_jspx_meth_liferay$1ui_search$1container$1column$1status_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_otherwise_2, _jspx_page_context))
                    return;
                  out.write("\n\n\t\t");
                  if (_jspx_meth_liferay$1ui_search$1container$1column$1jsp_1((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_otherwise_2, _jspx_page_context))
                    return;
                  out.write('\n');
                  out.write('	');
                }
                if (_jspx_th_c_otherwise_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_2);
                  return;
                }
                _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_2);
                out.write('\n');
              }
              if (_jspx_th_c_choose_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_0);
                return;
              }
              _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_0);
              out.write("\n\t\t\t");
              int evalDoAfterBody = _jspx_th_liferay$1ui_search$1container$1row_0.doAfterBody();
              index = (java.lang.Integer) _jspx_page_context.findAttribute("index");
              entry = (com.liferay.blogs.model.BlogsEntry) _jspx_page_context.findAttribute("entry");
              row = (com.liferay.portal.kernel.dao.search.ResultRow) _jspx_page_context.findAttribute("row");
              if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                break;
            } while (true);
            if (_jspx_eval_liferay$1ui_search$1container$1row_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
              out = _jspx_page_context.popBody();
          }
          if (_jspx_th_liferay$1ui_search$1container$1row_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_liferay$1ui_search$1container$1row_modelVar_keyProperty_escapedModel_className.reuse(_jspx_th_liferay$1ui_search$1container$1row_0);
            return;
          }
          _jspx_tagPool_liferay$1ui_search$1container$1row_modelVar_keyProperty_escapedModel_className.reuse(_jspx_th_liferay$1ui_search$1container$1row_0);
          out.write("\n\n\t\t\t");
          //  liferay-ui:search-iterator
          com.liferay.taglib.ui.SearchIteratorTag _jspx_th_liferay$1ui_search$1iterator_0 = (com.liferay.taglib.ui.SearchIteratorTag) _jspx_tagPool_liferay$1ui_search$1iterator_markupView_displayStyle_nobody.get(com.liferay.taglib.ui.SearchIteratorTag.class);
          _jspx_th_liferay$1ui_search$1iterator_0.setPageContext(_jspx_page_context);
          _jspx_th_liferay$1ui_search$1iterator_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container_0);
          _jspx_th_liferay$1ui_search$1iterator_0.setDisplayStyle( displayStyle );
          _jspx_th_liferay$1ui_search$1iterator_0.setMarkupView("lexicon");
          int _jspx_eval_liferay$1ui_search$1iterator_0 = _jspx_th_liferay$1ui_search$1iterator_0.doStartTag();
          if (_jspx_th_liferay$1ui_search$1iterator_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_liferay$1ui_search$1iterator_markupView_displayStyle_nobody.reuse(_jspx_th_liferay$1ui_search$1iterator_0);
            return;
          }
          _jspx_tagPool_liferay$1ui_search$1iterator_markupView_displayStyle_nobody.reuse(_jspx_th_liferay$1ui_search$1iterator_0);
          out.write("\n\t\t");
        }
        if (_jspx_th_liferay$1ui_search$1container_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_liferay$1ui_search$1container_searchContainer_rowChecker_id.reuse(_jspx_th_liferay$1ui_search$1container_0);
          return;
        }
        _jspx_tagPool_liferay$1ui_search$1container_searchContainer_rowChecker_id.reuse(_jspx_th_liferay$1ui_search$1container_0);
        out.write('\n');
        out.write('	');
      }
      if (_jspx_th_aui_form_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_aui_form_name_method_action.reuse(_jspx_th_aui_form_0);
        return;
      }
      _jspx_tagPool_aui_form_name_method_action.reuse(_jspx_th_aui_form_0);
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
          out.write("\n\tvar deleteEntries = function() {\n\t\tif (");
          out.print( trashHelper.isTrashEnabled(scopeGroupId) );
          out.write(" || confirm('");
          if (_jspx_meth_liferay$1ui_message_3((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_0, _jspx_page_context))
            return;
          out.write("')) {\n\t\t\tvar form = document.getElementById('");
          if (_jspx_meth_portlet_namespace_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_0, _jspx_page_context))
            return;
          out.write("fm');\n\n\t\t\tif (form) {\n\t\t\t\tform.setAttribute('method', 'post');\n\n\t\t\t\tvar cmd = form.querySelector('#");
          if (_jspx_meth_portlet_namespace_1((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_0, _jspx_page_context))
            return;
          out.print( Constants.CMD );
          out.write("');\n\n\t\t\t\tif (cmd) {\n\t\t\t\t\tcmd.setAttribute('value', '");
          out.print( trashHelper.isTrashEnabled(scopeGroupId) ? Constants.MOVE_TO_TRASH : Constants.DELETE );
          out.write("');\n\t\t\t\t}\n\n\t\t\t\tvar deleteEntryIds = form.querySelector('#");
          if (_jspx_meth_portlet_namespace_2((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_0, _jspx_page_context))
            return;
          out.write("deleteEntryIds');\n\n\t\t\t\tif (deleteEntryIds) {\n\t\t\t\t\tdeleteEntryIds.setAttribute('value', Liferay.Util.listCheckedExcept(form, '");
          if (_jspx_meth_portlet_namespace_3((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_0, _jspx_page_context))
            return;
          out.write("allRowIds'));\n\t\t\t\t}\n\n\t\t\t\tsubmitForm(form, '");
          if (_jspx_meth_portlet_actionURL_1((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_0, _jspx_page_context))
            return;
          out.write("');\n\t\t\t}\n\t\t}\n\t};\n\n\tvar ACTIONS = {\n\t\t'deleteEntries': deleteEntries\n\t};\n\n\tLiferay.componentReady('blogEntriesManagementToolbar').then(\n\t\tfunction(managementToolbar) {\n\t\t\tmanagementToolbar.on(\n\t\t\t\t'actionItemClicked',\n\t\t\t\tfunction(event) {\n\t\t\t\t\tvar itemData = event.data.item.data;\n\n\t\t\t\t\tif (itemData && itemData.action && ACTIONS[itemData.action]) {\n\t\t\t\t\t\tACTIONS[itemData.action]();\n\t\t\t\t\t}\n\t\t\t\t}\n\t\t\t);\n\t\t}\n\t);\n");
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

  private boolean _jspx_meth_aui_input_2(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_form_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:input
    com.liferay.taglib.aui.InputTag _jspx_th_aui_input_2 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
    _jspx_th_aui_input_2.setPageContext(_jspx_page_context);
    _jspx_th_aui_input_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
    _jspx_th_aui_input_2.setName("deleteEntryIds");
    _jspx_th_aui_input_2.setType("hidden");
    int _jspx_eval_aui_input_2 = _jspx_th_aui_input_2.doStartTag();
    if (_jspx_th_aui_input_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_input_type_name_nobody.reuse(_jspx_th_aui_input_2);
      return true;
    }
    _jspx_tagPool_aui_input_type_name_nobody.reuse(_jspx_th_aui_input_2);
    return false;
  }

  private boolean _jspx_meth_portlet_param_1(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1portlet_renderURL_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:param
    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_1 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
    _jspx_th_portlet_param_1.setPageContext(_jspx_page_context);
    _jspx_th_portlet_param_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_0);
    _jspx_th_portlet_param_1.setName("mvcRenderCommandName");
    _jspx_th_portlet_param_1.setValue("/blogs/edit_entry");
    int _jspx_eval_portlet_param_1 = _jspx_th_portlet_param_1.doStartTag();
    if (_jspx_th_portlet_param_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_1);
      return true;
    }
    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_1);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_search$1container$1column$1jsp_0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_when_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:search-container-column-jsp
    com.liferay.taglib.ui.SearchContainerColumnJSPTag _jspx_th_liferay$1ui_search$1container$1column$1jsp_0 = (com.liferay.taglib.ui.SearchContainerColumnJSPTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1jsp_path_nobody.get(com.liferay.taglib.ui.SearchContainerColumnJSPTag.class);
    _jspx_th_liferay$1ui_search$1container$1column$1jsp_0.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_search$1container$1column$1jsp_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_0);
    _jspx_th_liferay$1ui_search$1container$1column$1jsp_0.setPath("/blogs_admin/entry_action.jsp");
    int _jspx_eval_liferay$1ui_search$1container$1column$1jsp_0 = _jspx_th_liferay$1ui_search$1container$1column$1jsp_0.doStartTag();
    if (_jspx_th_liferay$1ui_search$1container$1column$1jsp_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_search$1container$1column$1jsp_path_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1jsp_0);
      return true;
    }
    _jspx_tagPool_liferay$1ui_search$1container$1column$1jsp_path_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1jsp_0);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_search$1container$1column$1status_0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_otherwise_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:search-container-column-status
    com.liferay.taglib.ui.SearchContainerColumnStatusTag _jspx_th_liferay$1ui_search$1container$1column$1status_0 = (com.liferay.taglib.ui.SearchContainerColumnStatusTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1status_name_nobody.get(com.liferay.taglib.ui.SearchContainerColumnStatusTag.class);
    _jspx_th_liferay$1ui_search$1container$1column$1status_0.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_search$1container$1column$1status_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_2);
    _jspx_th_liferay$1ui_search$1container$1column$1status_0.setName("status");
    int _jspx_eval_liferay$1ui_search$1container$1column$1status_0 = _jspx_th_liferay$1ui_search$1container$1column$1status_0.doStartTag();
    if (_jspx_th_liferay$1ui_search$1container$1column$1status_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_search$1container$1column$1status_name_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1status_0);
      return true;
    }
    _jspx_tagPool_liferay$1ui_search$1container$1column$1status_name_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1status_0);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_search$1container$1column$1jsp_1(javax.servlet.jsp.tagext.JspTag _jspx_th_c_otherwise_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:search-container-column-jsp
    com.liferay.taglib.ui.SearchContainerColumnJSPTag _jspx_th_liferay$1ui_search$1container$1column$1jsp_1 = (com.liferay.taglib.ui.SearchContainerColumnJSPTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1jsp_path_nobody.get(com.liferay.taglib.ui.SearchContainerColumnJSPTag.class);
    _jspx_th_liferay$1ui_search$1container$1column$1jsp_1.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_search$1container$1column$1jsp_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_2);
    _jspx_th_liferay$1ui_search$1container$1column$1jsp_1.setPath("/blogs_admin/entry_action.jsp");
    int _jspx_eval_liferay$1ui_search$1container$1column$1jsp_1 = _jspx_th_liferay$1ui_search$1container$1column$1jsp_1.doStartTag();
    if (_jspx_th_liferay$1ui_search$1container$1column$1jsp_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_search$1container$1column$1jsp_path_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1jsp_1);
      return true;
    }
    _jspx_tagPool_liferay$1ui_search$1container$1column$1jsp_path_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1jsp_1);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_3(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_3 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_3.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_0);
    _jspx_th_liferay$1ui_message_3.setKey("are-you-sure-you-want-to-delete-the-selected-entries");
    int _jspx_eval_liferay$1ui_message_3 = _jspx_th_liferay$1ui_message_3.doStartTag();
    if (_jspx_th_liferay$1ui_message_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_3);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_3);
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

  private boolean _jspx_meth_portlet_actionURL_1(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:actionURL
    com.liferay.taglib.portlet.ActionURLTag _jspx_th_portlet_actionURL_1 = (com.liferay.taglib.portlet.ActionURLTag) _jspx_tagPool_portlet_actionURL_name_nobody.get(com.liferay.taglib.portlet.ActionURLTag.class);
    _jspx_th_portlet_actionURL_1.setPageContext(_jspx_page_context);
    _jspx_th_portlet_actionURL_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_0);
    _jspx_th_portlet_actionURL_1.setName("/blogs/edit_entry");
    int _jspx_eval_portlet_actionURL_1 = _jspx_th_portlet_actionURL_1.doStartTag();
    if (_jspx_th_portlet_actionURL_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_actionURL_name_nobody.reuse(_jspx_th_portlet_actionURL_1);
      return true;
    }
    _jspx_tagPool_portlet_actionURL_name_nobody.reuse(_jspx_th_portlet_actionURL_1);
    return false;
  }
}
