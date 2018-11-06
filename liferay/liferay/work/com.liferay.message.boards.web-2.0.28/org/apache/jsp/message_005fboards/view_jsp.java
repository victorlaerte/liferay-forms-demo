package org.apache.jsp.message_005fboards;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.liferay.asset.constants.AssetWebKeys;
import com.liferay.asset.kernel.AssetRendererFactoryRegistryUtil;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.kernel.model.AssetTag;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetEntryServiceUtil;
import com.liferay.asset.kernel.service.AssetTagLocalServiceUtil;
import com.liferay.asset.kernel.service.persistence.AssetEntryQuery;
import com.liferay.asset.util.AssetHelper;
import com.liferay.captcha.configuration.CaptchaConfiguration;
import com.liferay.document.library.configuration.DLConfiguration;
import com.liferay.document.library.kernel.antivirus.AntivirusScannerException;
import com.liferay.document.library.kernel.exception.DuplicateFileEntryException;
import com.liferay.document.library.kernel.exception.FileExtensionException;
import com.liferay.document.library.kernel.exception.FileNameException;
import com.liferay.document.library.kernel.exception.FileSizeException;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.util.DLValidatorUtil;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.JSPNavigationItemList;
import com.liferay.message.boards.constants.MBCategoryConstants;
import com.liferay.message.boards.constants.MBConstants;
import com.liferay.message.boards.constants.MBMessageConstants;
import com.liferay.message.boards.constants.MBPortletKeys;
import com.liferay.message.boards.display.context.MBAdminListDisplayContext;
import com.liferay.message.boards.display.context.MBHomeDisplayContext;
import com.liferay.message.boards.display.context.MBListDisplayContext;
import com.liferay.message.boards.exception.BannedUserException;
import com.liferay.message.boards.exception.CategoryNameException;
import com.liferay.message.boards.exception.LockedThreadException;
import com.liferay.message.boards.exception.MailingListEmailAddressException;
import com.liferay.message.boards.exception.MailingListInServerNameException;
import com.liferay.message.boards.exception.MailingListInUserNameException;
import com.liferay.message.boards.exception.MailingListOutEmailAddressException;
import com.liferay.message.boards.exception.MailingListOutServerNameException;
import com.liferay.message.boards.exception.MailingListOutUserNameException;
import com.liferay.message.boards.exception.MessageBodyException;
import com.liferay.message.boards.exception.MessageSubjectException;
import com.liferay.message.boards.exception.NoSuchCategoryException;
import com.liferay.message.boards.exception.NoSuchMessageException;
import com.liferay.message.boards.exception.RequiredMessageException;
import com.liferay.message.boards.exception.SplitThreadException;
import com.liferay.message.boards.model.MBBan;
import com.liferay.message.boards.model.MBCategory;
import com.liferay.message.boards.model.MBMailingList;
import com.liferay.message.boards.model.MBMessage;
import com.liferay.message.boards.model.MBMessageDisplay;
import com.liferay.message.boards.model.MBStatsUser;
import com.liferay.message.boards.model.MBThread;
import com.liferay.message.boards.model.MBThreadFlag;
import com.liferay.message.boards.model.MBTreeWalker;
import com.liferay.message.boards.service.MBBanLocalServiceUtil;
import com.liferay.message.boards.service.MBCategoryLocalServiceUtil;
import com.liferay.message.boards.service.MBCategoryServiceUtil;
import com.liferay.message.boards.service.MBMailingListLocalServiceUtil;
import com.liferay.message.boards.service.MBMessageLocalServiceUtil;
import com.liferay.message.boards.service.MBMessageServiceUtil;
import com.liferay.message.boards.service.MBStatsUserLocalServiceUtil;
import com.liferay.message.boards.service.MBThreadFlagLocalServiceUtil;
import com.liferay.message.boards.service.MBThreadLocalServiceUtil;
import com.liferay.message.boards.service.MBThreadServiceUtil;
import com.liferay.message.boards.settings.MBGroupServiceSettings;
import com.liferay.message.boards.util.comparator.ThreadModifiedDateComparator;
import com.liferay.message.boards.web.internal.dao.search.MBResultRowSplitter;
import com.liferay.message.boards.web.internal.display.MBCategoryDisplay;
import com.liferay.message.boards.web.internal.display.context.MBBannedUsersManagementToolbarDisplayContext;
import com.liferay.message.boards.web.internal.display.context.MBDisplayContextProvider;
import com.liferay.message.boards.web.internal.display.context.MBEntriesManagementToolbarDisplayContext;
import com.liferay.message.boards.web.internal.display.context.util.MBRequestHelper;
import com.liferay.message.boards.web.internal.search.EntriesChecker;
import com.liferay.message.boards.web.internal.security.permission.MBCategoryPermission;
import com.liferay.message.boards.web.internal.security.permission.MBMessagePermission;
import com.liferay.message.boards.web.internal.security.permission.MBResourcePermission;
import com.liferay.message.boards.web.internal.util.MBBreadcrumbUtil;
import com.liferay.message.boards.web.internal.util.MBMailUtil;
import com.liferay.message.boards.web.internal.util.MBMessageIterator;
import com.liferay.message.boards.web.internal.util.MBRSSUtil;
import com.liferay.message.boards.web.internal.util.MBSubscriptionUtil;
import com.liferay.message.boards.web.internal.util.MBUserRankUtil;
import com.liferay.message.boards.web.internal.util.MBUtil;
import com.liferay.message.boards.web.internal.util.MBWebComponentProvider;
import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.bean.BeanParamUtil;
import com.liferay.portal.kernel.bean.BeanPropertiesUtil;
import com.liferay.portal.kernel.captcha.CaptchaConfigurationException;
import com.liferay.portal.kernel.captcha.CaptchaTextException;
import com.liferay.portal.kernel.dao.search.EmptyOnClickRowChecker;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.editor.Editor;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.language.UnicodeLanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.ModelHintsConstants;
import com.liferay.portal.kernel.model.Ticket;
import com.liferay.portal.kernel.model.TicketConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.module.configuration.ConfigurationProviderUtil;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.portletfilerepository.PortletFileRepositoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.search.SearchResult;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.ResourceActionsUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.TicketLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.service.WorkflowDefinitionLinkLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.LiferayFileItemException;
import com.liferay.portal.kernel.upload.UploadRequestSizeException;
import com.liferay.portal.kernel.upload.UploadServletRequestConfigurationHelperUtil;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.TempFileEntryUtil;
import com.liferay.portal.kernel.util.TextFormatter;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.upload.LiferayFileItem;
import com.liferay.portal.util.PropsValues;
import com.liferay.subscription.service.SubscriptionLocalServiceUtil;
import com.liferay.taglib.search.ResultRow;
import com.liferay.taglib.ui.InputEditorTag;
import java.text.DecimalFormatSymbols;
import java.text.Format;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.WindowState;

public final class view_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {


private void _buildCategoryBreadcrumb(HttpServletRequest request, ThemeDisplay themeDisplay, MBCategory category, StringBundler sb) throws Exception {
	List<MBCategory> ancestorCategories = category.getAncestors();

	Collections.reverse(ancestorCategories);

	sb.append("<ul class=\"breadcrumb\">");

	for (int i = 0; i < ancestorCategories.size(); i++) {
		MBCategory ancestorCategory = ancestorCategories.get(i);

		PortletURL portletURL = PortletURLFactoryUtil.create(request, MBPortletKeys.MESSAGE_BOARDS, PortletRequest.RENDER_PHASE);

		portletURL.setParameter("mvcRenderCommandName", "/message_boards/view_category");
		portletURL.setParameter("mbCategoryId", String.valueOf(ancestorCategory.getCategoryId()));

		String cssClass = StringPool.BLANK;

		if (i == 0) {
			cssClass = "first";
		}

		sb.append("<li class=\"");
		sb.append(cssClass);
		sb.append("\"><a href=\"");
		sb.append(portletURL.toString());
		sb.append("\">");
		sb.append(HtmlUtil.escape(ancestorCategory.getName()));
		sb.append("</a></li>");
	}

	PortletURL portletURL = PortletURLFactoryUtil.create(request, MBPortletKeys.MESSAGE_BOARDS, PortletRequest.RENDER_PHASE);

	portletURL.setParameter("mvcRenderCommandName", "/message_boards/view_category");
	portletURL.setParameter("mbCategoryId", String.valueOf(category.getCategoryId()));

	sb.append("<li class=\"active last\">");
	sb.append("<strong>");
	sb.append("<a href=\"");
	sb.append(portletURL.toString());
	sb.append("\">");
	sb.append(category.getName());
	sb.append("</strong>");
	sb.append("</a>");

	if (Validator.isNotNull(category.getDescription())) {
		sb.append("<p>");
		sb.append(category.getDescription());
		sb.append("</p>");
	}

	sb.append("</li>");
	sb.append("</ul>");
}


private static Log _log = LogFactoryUtil.getLog("com_liferay_message_boards_web.message_boards.view_jsp");

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(7);
    _jspx_dependants.add("/message_boards/init.jsp");
    _jspx_dependants.add("/message_boards/init-ext.jsp");
    _jspx_dependants.add("/message_boards/subscribed_category_columns.jspf");
    _jspx_dependants.add("/message_boards/subscribed_category_columns_action.jspf");
    _jspx_dependants.add("/message_boards/view_threads.jspf");
    _jspx_dependants.add("/message_boards/thread_cast_result.jspf");
    _jspx_dependants.add("/message_boards/user_thread_columns.jspf");
  }

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_actionURL_var_name;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1rss_rss_url_message_feedType_displayStyle_delta_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1asset_categorization$1filter_portletURL_assetType_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_breadcrumb_showParentGroups_showLayout_showGuestGroup_showCurrentGroup_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_href_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container$1column$1user_userId_property_name_date_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_param_value_name_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_if_test;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1security_permissionsURL_windowState_var_resourcePrimKey_modelResourceDescription_modelResource_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_clay_link_label_href_elementClasses_buttonStyle_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container$1column$1text_name_href;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_icon_url_message_label_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container_iteratorURL_headerNames;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container$1row_modelVar_keyProperty_escapedModel_className;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container$1results;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1portlet_renderURL_varImpl;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_choose;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1frontend_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1util_include_servletContext_page_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_header_title_backURL_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_empty$1result$1message_message_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_when_test;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_message_key_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1trash_undo_portletURL_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container$1column$1text_name;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1iterator_markupView_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container$1results_results_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container$1row_modelVar_keyProperty_className;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_renderURL_var;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_otherwise;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container$1column$1jsp_path_cssClass_align_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_icon_useDialog_url_method_message_label_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1theme_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container$1column$1status_status_name_href_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1iterator_type_markupView_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_icon$1menu_showWhenSingleIcon_message_markupView_icon_direction;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container_total_iteratorURL_headerNames_emptyResultsMessage_deltaConfigurable_curParam;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1rss_rss_url_feedType_displayStyle_delta_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1trash_defineObjects_nobody;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_portlet_actionURL_var_name = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1rss_rss_url_message_feedType_displayStyle_delta_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1asset_categorization$1filter_portletURL_assetType_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_breadcrumb_showParentGroups_showLayout_showGuestGroup_showCurrentGroup_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_href_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container$1column$1user_userId_property_name_date_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_param_value_name_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_if_test = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1security_permissionsURL_windowState_var_resourcePrimKey_modelResourceDescription_modelResource_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_clay_link_label_href_elementClasses_buttonStyle_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_name_href = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_icon_url_message_label_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container_iteratorURL_headerNames = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container$1row_modelVar_keyProperty_escapedModel_className = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container$1results = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1portlet_renderURL_varImpl = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_choose = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1frontend_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1util_include_servletContext_page_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_header_title_backURL_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_empty$1result$1message_message_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_when_test = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_message_key_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1trash_undo_portletURL_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_name = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1iterator_markupView_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container$1results_results_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container$1row_modelVar_keyProperty_className = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_renderURL_var = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_otherwise = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container$1column$1jsp_path_cssClass_align_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_icon_useDialog_url_method_message_label_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1theme_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container$1column$1status_status_name_href_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1iterator_type_markupView_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_icon$1menu_showWhenSingleIcon_message_markupView_icon_direction = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container_total_iteratorURL_headerNames_emptyResultsMessage_deltaConfigurable_curParam = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1rss_rss_url_feedType_displayStyle_delta_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1trash_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_portlet_actionURL_var_name.release();
    _jspx_tagPool_liferay$1rss_rss_url_message_feedType_displayStyle_delta_nobody.release();
    _jspx_tagPool_liferay$1asset_categorization$1filter_portletURL_assetType_nobody.release();
    _jspx_tagPool_liferay$1ui_breadcrumb_showParentGroups_showLayout_showGuestGroup_showCurrentGroup_nobody.release();
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_href_nobody.release();
    _jspx_tagPool_liferay$1ui_search$1container$1column$1user_userId_property_name_date_nobody.release();
    _jspx_tagPool_portlet_param_value_name_nobody.release();
    _jspx_tagPool_c_if_test.release();
    _jspx_tagPool_liferay$1security_permissionsURL_windowState_var_resourcePrimKey_modelResourceDescription_modelResource_nobody.release();
    _jspx_tagPool_clay_link_label_href_elementClasses_buttonStyle_nobody.release();
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_name_href.release();
    _jspx_tagPool_liferay$1ui_icon_url_message_label_nobody.release();
    _jspx_tagPool_liferay$1ui_search$1container_iteratorURL_headerNames.release();
    _jspx_tagPool_liferay$1ui_search$1container$1row_modelVar_keyProperty_escapedModel_className.release();
    _jspx_tagPool_liferay$1ui_search$1container$1results.release();
    _jspx_tagPool_liferay$1portlet_renderURL_varImpl.release();
    _jspx_tagPool_c_choose.release();
    _jspx_tagPool_liferay$1frontend_defineObjects_nobody.release();
    _jspx_tagPool_liferay$1util_include_servletContext_page_nobody.release();
    _jspx_tagPool_liferay$1ui_header_title_backURL_nobody.release();
    _jspx_tagPool_liferay$1ui_empty$1result$1message_message_nobody.release();
    _jspx_tagPool_c_when_test.release();
    _jspx_tagPool_liferay$1ui_message_key_nobody.release();
    _jspx_tagPool_liferay$1trash_undo_portletURL_nobody.release();
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_name.release();
    _jspx_tagPool_liferay$1ui_search$1iterator_markupView_nobody.release();
    _jspx_tagPool_liferay$1ui_search$1container$1results_results_nobody.release();
    _jspx_tagPool_liferay$1ui_search$1container$1row_modelVar_keyProperty_className.release();
    _jspx_tagPool_portlet_renderURL_var.release();
    _jspx_tagPool_c_otherwise.release();
    _jspx_tagPool_portlet_defineObjects_nobody.release();
    _jspx_tagPool_liferay$1ui_search$1container$1column$1jsp_path_cssClass_align_nobody.release();
    _jspx_tagPool_liferay$1ui_icon_useDialog_url_method_message_label_nobody.release();
    _jspx_tagPool_liferay$1theme_defineObjects_nobody.release();
    _jspx_tagPool_liferay$1ui_search$1container$1column$1status_status_name_href_nobody.release();
    _jspx_tagPool_liferay$1ui_search$1iterator_type_markupView_nobody.release();
    _jspx_tagPool_liferay$1ui_icon$1menu_showWhenSingleIcon_message_markupView_icon_direction.release();
    _jspx_tagPool_liferay$1ui_search$1container_total_iteratorURL_headerNames_emptyResultsMessage_deltaConfigurable_curParam.release();
    _jspx_tagPool_liferay$1rss_rss_url_feedType_displayStyle_delta_nobody.release();
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

String currentLanguageId = LanguageUtil.getLanguageId(request);
Locale currentLocale = LocaleUtil.fromLanguageId(currentLanguageId);
Locale defaultLocale = themeDisplay.getSiteDefaultLocale();
String defaultLanguageId = LocaleUtil.toLanguageId(defaultLocale);

CaptchaConfiguration captchaConfiguration = (CaptchaConfiguration)ConfigurationProviderUtil.getSystemConfiguration(CaptchaConfiguration.class);

MBGroupServiceSettings mbGroupServiceSettings = MBGroupServiceSettings.getInstance(themeDisplay.getSiteGroupId());

String[] priorities = mbGroupServiceSettings.getPriorities(currentLanguageId);

boolean allowAnonymousPosting = mbGroupServiceSettings.isAllowAnonymousPosting();
boolean enableFlags = mbGroupServiceSettings.isEnableFlags();
boolean enableRatings = mbGroupServiceSettings.isEnableRatings();
String messageFormat = mbGroupServiceSettings.getMessageFormat();
boolean subscribeByDefault = mbGroupServiceSettings.isSubscribeByDefault();
boolean threadAsQuestionByDefault = mbGroupServiceSettings.isThreadAsQuestionByDefault();

boolean enableRSS = mbGroupServiceSettings.isEnableRSS();
int rssDelta = mbGroupServiceSettings.getRSSDelta();
String rssDisplayStyle = mbGroupServiceSettings.getRSSDisplayStyle();
String rssFeedType = mbGroupServiceSettings.getRSSFeedType();

MBWebComponentProvider mbWebComponentProvider = MBWebComponentProvider.getMBWebComponentProvider();

MBDisplayContextProvider mbDisplayContextProvider = mbWebComponentProvider.getMBDisplayContextProvider();

Format dateFormatDate = FastDateFormatFactoryUtil.getDate(locale, timeZone);
Format dateFormatDateTime = FastDateFormatFactoryUtil.getDateTime(locale, timeZone);

NumberFormat numberFormat = NumberFormat.getNumberInstance(locale);

      out.write('\n');
      out.write('\n');
      out.write('\n');
      out.write('\n');

String redirect = ParamUtil.getString(request, "redirect");

String mvcRenderCommandName = ParamUtil.getString(request, "mvcRenderCommandName", "/message_boards/view");

MBCategory category = (MBCategory)request.getAttribute(WebKeys.MESSAGE_BOARDS_CATEGORY);

long categoryId = MBUtil.getCategoryId(request, category);

Set<Long> categorySubscriptionClassPKs = null;
Set<Long> threadSubscriptionClassPKs = null;

if (themeDisplay.isSignedIn()) {
	categorySubscriptionClassPKs = MBSubscriptionUtil.getCategorySubscriptionClassPKs(user.getUserId());
	threadSubscriptionClassPKs = MBSubscriptionUtil.getThreadSubscriptionClassPKs(user.getUserId());
}

long groupThreadsUserId = ParamUtil.getLong(request, "groupThreadsUserId");

String assetTagName = ParamUtil.getString(request, "tag");

boolean useAssetEntryQuery = Validator.isNotNull(assetTagName);

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcRenderCommandName", mvcRenderCommandName);
portletURL.setParameter("mbCategoryId", String.valueOf(categoryId));

String keywords = ParamUtil.getString(request, "keywords");

if (Validator.isNotNull(keywords)) {
	portletURL.setParameter("keywords", keywords);
}

String orderByCol = ParamUtil.getString(request, "orderByCol", "modified-date");

boolean orderByAsc = false;

String orderByType = ParamUtil.getString(request, "orderByType", "desc");

if (orderByType.equals("asc")) {
	orderByAsc = true;
}

OrderByComparator orderByComparator = null;

if (orderByCol.equals("modified-date")) {
	orderByComparator = new ThreadModifiedDateComparator(orderByAsc);
}

MBListDisplayContext mbListDisplayContext = mbDisplayContextProvider.getMbListDisplayContext(request, response, categoryId);

request.setAttribute("view.jsp-categorySubscriptionClassPKs", categorySubscriptionClassPKs);
request.setAttribute("view.jsp-threadSubscriptionClassPKs", threadSubscriptionClassPKs);

request.setAttribute("view.jsp-categoryId", categoryId);
request.setAttribute("view.jsp-viewCategory", Boolean.TRUE.toString());

      out.write('\n');
      out.write('\n');
      //  portlet:actionURL
      com.liferay.taglib.portlet.ActionURLTag _jspx_th_portlet_actionURL_0 = (com.liferay.taglib.portlet.ActionURLTag) _jspx_tagPool_portlet_actionURL_var_name.get(com.liferay.taglib.portlet.ActionURLTag.class);
      _jspx_th_portlet_actionURL_0.setPageContext(_jspx_page_context);
      _jspx_th_portlet_actionURL_0.setParent(null);
      _jspx_th_portlet_actionURL_0.setName("/message_boards/edit_category");
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
      //  liferay-util:include
      com.liferay.taglib.util.IncludeTag _jspx_th_liferay$1util_include_0 = (com.liferay.taglib.util.IncludeTag) _jspx_tagPool_liferay$1util_include_servletContext_page_nobody.get(com.liferay.taglib.util.IncludeTag.class);
      _jspx_th_liferay$1util_include_0.setPageContext(_jspx_page_context);
      _jspx_th_liferay$1util_include_0.setParent(null);
      _jspx_th_liferay$1util_include_0.setPage("/message_boards/nav.jsp");
      _jspx_th_liferay$1util_include_0.setServletContext( application );
      int _jspx_eval_liferay$1util_include_0 = _jspx_th_liferay$1util_include_0.doStartTag();
      if (_jspx_th_liferay$1util_include_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_liferay$1util_include_servletContext_page_nobody.reuse(_jspx_th_liferay$1util_include_0);
        return;
      }
      _jspx_tagPool_liferay$1util_include_servletContext_page_nobody.reuse(_jspx_th_liferay$1util_include_0);
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
        _jspx_th_c_when_0.setTest( mvcRenderCommandName.equals("/message_boards/view_my_subscriptions") );
        int _jspx_eval_c_when_0 = _jspx_th_c_when_0.doStartTag();
        if (_jspx_eval_c_when_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          out.write("\n\n\t\t");

		if (themeDisplay.isSignedIn()) {
			groupThreadsUserId = user.getUserId();
		}

		if (groupThreadsUserId > 0) {
			portletURL.setParameter("groupThreadsUserId", String.valueOf(groupThreadsUserId));
		}

		MBCategoryDisplay categoryDisplay = new MBCategoryDisplay(scopeGroupId, categoryId);
		
          out.write("\n\n\t\t<div class=\"main-content-body\">\n\t\t\t<h3>");
          if (_jspx_meth_liferay$1ui_message_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_when_0, _jspx_page_context))
            return;
          out.write("</h3>\n\n\t\t\t");
          //  liferay-ui:search-container
          com.liferay.taglib.ui.SearchContainerTag _jspx_th_liferay$1ui_search$1container_0 = (com.liferay.taglib.ui.SearchContainerTag) _jspx_tagPool_liferay$1ui_search$1container_total_iteratorURL_headerNames_emptyResultsMessage_deltaConfigurable_curParam.get(com.liferay.taglib.ui.SearchContainerTag.class);
          _jspx_th_liferay$1ui_search$1container_0.setPageContext(_jspx_page_context);
          _jspx_th_liferay$1ui_search$1container_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_0);
          _jspx_th_liferay$1ui_search$1container_0.setCurParam("cur1");
          _jspx_th_liferay$1ui_search$1container_0.setDeltaConfigurable( false );
          _jspx_th_liferay$1ui_search$1container_0.setEmptyResultsMessage("you-are-not-subscribed-to-any-categories");
          _jspx_th_liferay$1ui_search$1container_0.setHeaderNames("category,categories,threads,posts");
          _jspx_th_liferay$1ui_search$1container_0.setIteratorURL( portletURL );
          _jspx_th_liferay$1ui_search$1container_0.setTotal( MBCategoryServiceUtil.getSubscribedCategoriesCount(scopeGroupId, user.getUserId()) );
          int _jspx_eval_liferay$1ui_search$1container_0 = _jspx_th_liferay$1ui_search$1container_0.doStartTag();
          if (_jspx_eval_liferay$1ui_search$1container_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            java.lang.Integer total = null;
            com.liferay.portal.kernel.dao.search.SearchContainer searchContainer = null;
            total = (java.lang.Integer) _jspx_page_context.findAttribute("total");
            searchContainer = (com.liferay.portal.kernel.dao.search.SearchContainer) _jspx_page_context.findAttribute("searchContainer");
            out.write("\n\t\t\t\t");
            //  liferay-ui:search-container-results
            java.util.List results = null;
            java.lang.Integer deprecatedTotal = null;
            com.liferay.taglib.ui.SearchContainerResultsTag _jspx_th_liferay$1ui_search$1container$1results_0 = (com.liferay.taglib.ui.SearchContainerResultsTag) _jspx_tagPool_liferay$1ui_search$1container$1results_results_nobody.get(com.liferay.taglib.ui.SearchContainerResultsTag.class);
            _jspx_th_liferay$1ui_search$1container$1results_0.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1ui_search$1container$1results_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container_0);
            _jspx_th_liferay$1ui_search$1container$1results_0.setResults( MBCategoryServiceUtil.getSubscribedCategories(scopeGroupId, user.getUserId(), searchContainer.getStart(), searchContainer.getEnd()) );
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
            out.write("\n\n\t\t\t\t");
            //  liferay-ui:search-container-row
            com.liferay.taglib.ui.SearchContainerRowTag _jspx_th_liferay$1ui_search$1container$1row_0 = (com.liferay.taglib.ui.SearchContainerRowTag) _jspx_tagPool_liferay$1ui_search$1container$1row_modelVar_keyProperty_escapedModel_className.get(com.liferay.taglib.ui.SearchContainerRowTag.class);
            _jspx_th_liferay$1ui_search$1container$1row_0.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1ui_search$1container$1row_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container_0);
            _jspx_th_liferay$1ui_search$1container$1row_0.setClassName("com.liferay.message.boards.model.MBCategory");
            _jspx_th_liferay$1ui_search$1container$1row_0.setEscapedModel( true );
            _jspx_th_liferay$1ui_search$1container$1row_0.setKeyProperty("categoryId");
            _jspx_th_liferay$1ui_search$1container$1row_0.setModelVar("curCategory");
            int _jspx_eval_liferay$1ui_search$1container$1row_0 = _jspx_th_liferay$1ui_search$1container$1row_0.doStartTag();
            if (_jspx_eval_liferay$1ui_search$1container$1row_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              java.lang.Integer index = null;
              com.liferay.message.boards.model.MBCategory curCategory = null;
              com.liferay.portal.kernel.dao.search.ResultRow row = null;
              if (_jspx_eval_liferay$1ui_search$1container$1row_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                out = _jspx_page_context.pushBody();
                _jspx_th_liferay$1ui_search$1container$1row_0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                _jspx_th_liferay$1ui_search$1container$1row_0.doInitBody();
              }
              index = (java.lang.Integer) _jspx_page_context.findAttribute("index");
              curCategory = (com.liferay.message.boards.model.MBCategory) _jspx_page_context.findAttribute("curCategory");
              row = (com.liferay.portal.kernel.dao.search.ResultRow) _jspx_page_context.findAttribute("row");
              do {
                out.write("\n\t\t\t\t\t");
                //  liferay-portlet:renderURL
                com.liferay.taglib.portlet.RenderURLTag _jspx_th_liferay$1portlet_renderURL_0 = (com.liferay.taglib.portlet.RenderURLTag) _jspx_tagPool_liferay$1portlet_renderURL_varImpl.get(com.liferay.taglib.portlet.RenderURLTag.class);
                _jspx_th_liferay$1portlet_renderURL_0.setPageContext(_jspx_page_context);
                _jspx_th_liferay$1portlet_renderURL_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1row_0);
                _jspx_th_liferay$1portlet_renderURL_0.setVarImpl("rowURL");
                int _jspx_eval_liferay$1portlet_renderURL_0 = _jspx_th_liferay$1portlet_renderURL_0.doStartTag();
                if (_jspx_eval_liferay$1portlet_renderURL_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  out.write("\n\t\t\t\t\t\t");
                  if (_jspx_meth_portlet_param_1((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1portlet_renderURL_0, _jspx_page_context))
                    return;
                  out.write("\n\t\t\t\t\t\t");
                  //  portlet:param
                  com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_2 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                  _jspx_th_portlet_param_2.setPageContext(_jspx_page_context);
                  _jspx_th_portlet_param_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_0);
                  _jspx_th_portlet_param_2.setName("mbCategoryId");
                  _jspx_th_portlet_param_2.setValue( String.valueOf(curCategory.getCategoryId()) );
                  int _jspx_eval_portlet_param_2 = _jspx_th_portlet_param_2.doStartTag();
                  if (_jspx_th_portlet_param_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_2);
                    return;
                  }
                  _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_2);
                  out.write("\n\t\t\t\t\t");
                }
                if (_jspx_th_liferay$1portlet_renderURL_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_liferay$1portlet_renderURL_varImpl.reuse(_jspx_th_liferay$1portlet_renderURL_0);
                  return;
                }
                _jspx_tagPool_liferay$1portlet_renderURL_varImpl.reuse(_jspx_th_liferay$1portlet_renderURL_0);
                com.liferay.portal.kernel.portlet.LiferayPortletURL rowURL = null;
                rowURL = (com.liferay.portal.kernel.portlet.LiferayPortletURL) _jspx_page_context.findAttribute("rowURL");
                out.write("\n\n\t\t\t\t\t");
                out.write('\n');
                out.write('\n');
                //  liferay-ui:search-container-column-text
                com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_0 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text_name.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
                _jspx_th_liferay$1ui_search$1container$1column$1text_0.setPageContext(_jspx_page_context);
                _jspx_th_liferay$1ui_search$1container$1column$1text_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1row_0);
                _jspx_th_liferay$1ui_search$1container$1column$1text_0.setName("category");
                int _jspx_eval_liferay$1ui_search$1container$1column$1text_0 = _jspx_th_liferay$1ui_search$1container$1column$1text_0.doStartTag();
                if (_jspx_eval_liferay$1ui_search$1container$1column$1text_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  if (_jspx_eval_liferay$1ui_search$1container$1column$1text_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                    out = _jspx_page_context.pushBody();
                    _jspx_th_liferay$1ui_search$1container$1column$1text_0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                    _jspx_th_liferay$1ui_search$1container$1column$1text_0.doInitBody();
                  }
                  do {
                    out.write("\n\n\t");

	StringBundler sb = new StringBundler();

	_buildCategoryBreadcrumb(request, themeDisplay, curCategory, sb);
	
                    out.write("\n\n\t");
                    out.print( sb.toString() );
                    out.write('\n');
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
                out.write('\n');
                out.write('\n');
                //  liferay-ui:search-container-column-text
                com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_1 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_href_nobody.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
                _jspx_th_liferay$1ui_search$1container$1column$1text_1.setPageContext(_jspx_page_context);
                _jspx_th_liferay$1ui_search$1container$1column$1text_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1row_0);
                _jspx_th_liferay$1ui_search$1container$1column$1text_1.setHref( rowURL );
                _jspx_th_liferay$1ui_search$1container$1column$1text_1.setName("categories");
                _jspx_th_liferay$1ui_search$1container$1column$1text_1.setValue( String.valueOf(categoryDisplay.getSubcategoriesCount(curCategory)) );
                int _jspx_eval_liferay$1ui_search$1container$1column$1text_1 = _jspx_th_liferay$1ui_search$1container$1column$1text_1.doStartTag();
                if (_jspx_th_liferay$1ui_search$1container$1column$1text_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_href_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_1);
                  return;
                }
                _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_href_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_1);
                out.write('\n');
                out.write('\n');
                //  liferay-ui:search-container-column-text
                com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_2 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_href_nobody.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
                _jspx_th_liferay$1ui_search$1container$1column$1text_2.setPageContext(_jspx_page_context);
                _jspx_th_liferay$1ui_search$1container$1column$1text_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1row_0);
                _jspx_th_liferay$1ui_search$1container$1column$1text_2.setHref( rowURL );
                _jspx_th_liferay$1ui_search$1container$1column$1text_2.setName("threads");
                _jspx_th_liferay$1ui_search$1container$1column$1text_2.setValue( String.valueOf(categoryDisplay.getSubcategoriesThreadsCount(curCategory)) );
                int _jspx_eval_liferay$1ui_search$1container$1column$1text_2 = _jspx_th_liferay$1ui_search$1container$1column$1text_2.doStartTag();
                if (_jspx_th_liferay$1ui_search$1container$1column$1text_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_href_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_2);
                  return;
                }
                _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_href_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_2);
                out.write('\n');
                out.write('\n');
                //  liferay-ui:search-container-column-text
                com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_3 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_href_nobody.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
                _jspx_th_liferay$1ui_search$1container$1column$1text_3.setPageContext(_jspx_page_context);
                _jspx_th_liferay$1ui_search$1container$1column$1text_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1row_0);
                _jspx_th_liferay$1ui_search$1container$1column$1text_3.setHref( rowURL );
                _jspx_th_liferay$1ui_search$1container$1column$1text_3.setName("posts");
                _jspx_th_liferay$1ui_search$1container$1column$1text_3.setValue( String.valueOf(categoryDisplay.getSubcategoriesMessagesCount(curCategory)) );
                int _jspx_eval_liferay$1ui_search$1container$1column$1text_3 = _jspx_th_liferay$1ui_search$1container$1column$1text_3.doStartTag();
                if (_jspx_th_liferay$1ui_search$1container$1column$1text_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_href_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_3);
                  return;
                }
                _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_href_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_3);
                out.write('\n');
                out.write('\n');
                out.write('\n');
                out.write('\n');
                if (_jspx_meth_liferay$1ui_search$1container$1column$1jsp_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1ui_search$1container$1row_0, _jspx_page_context))
                  return;
                out.write('\n');
                out.write('\n');
                out.write("\n\t\t\t\t");
                int evalDoAfterBody = _jspx_th_liferay$1ui_search$1container$1row_0.doAfterBody();
                index = (java.lang.Integer) _jspx_page_context.findAttribute("index");
                curCategory = (com.liferay.message.boards.model.MBCategory) _jspx_page_context.findAttribute("curCategory");
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
            out.write("\n\n\t\t\t\t");
            if (_jspx_meth_liferay$1ui_search$1iterator_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1ui_search$1container_0, _jspx_page_context))
              return;
            out.write("\n\t\t\t");
          }
          if (_jspx_th_liferay$1ui_search$1container_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_liferay$1ui_search$1container_total_iteratorURL_headerNames_emptyResultsMessage_deltaConfigurable_curParam.reuse(_jspx_th_liferay$1ui_search$1container_0);
            return;
          }
          _jspx_tagPool_liferay$1ui_search$1container_total_iteratorURL_headerNames_emptyResultsMessage_deltaConfigurable_curParam.reuse(_jspx_th_liferay$1ui_search$1container_0);
          out.write("\n\n\t\t\t");
          out.write('\n');
          out.write('\n');
          //  liferay-ui:search-container
          com.liferay.taglib.ui.SearchContainerTag _jspx_th_liferay$1ui_search$1container_1 = (com.liferay.taglib.ui.SearchContainerTag) _jspx_tagPool_liferay$1ui_search$1container_iteratorURL_headerNames.get(com.liferay.taglib.ui.SearchContainerTag.class);
          _jspx_th_liferay$1ui_search$1container_1.setPageContext(_jspx_page_context);
          _jspx_th_liferay$1ui_search$1container_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_0);
          _jspx_th_liferay$1ui_search$1container_1.setHeaderNames("thread,started-by,posts,views,last-post");
          _jspx_th_liferay$1ui_search$1container_1.setIteratorURL( portletURL );
          int _jspx_eval_liferay$1ui_search$1container_1 = _jspx_th_liferay$1ui_search$1container_1.doStartTag();
          if (_jspx_eval_liferay$1ui_search$1container_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            java.lang.Integer total = null;
            com.liferay.portal.kernel.dao.search.SearchContainer searchContainer = null;
            total = (java.lang.Integer) _jspx_page_context.findAttribute("total");
            searchContainer = (com.liferay.portal.kernel.dao.search.SearchContainer) _jspx_page_context.findAttribute("searchContainer");
            out.write("\n\n\t");

	String emptyResultsMessage = null;

	if (mvcRenderCommandName.equals("/message_boards/view_my_subscriptions")) {
		emptyResultsMessage = "you-are-not-subscribed-to-any-threads";
	}
	else {
		emptyResultsMessage = "there-are-no-threads-in-this-category";
	}

	searchContainer.setEmptyResultsMessage(emptyResultsMessage);
	
            out.write("\n\n\t");
            //  liferay-ui:search-container-results
            java.util.List results = null;
            java.lang.Integer deprecatedTotal = null;
            com.liferay.taglib.ui.SearchContainerResultsTag _jspx_th_liferay$1ui_search$1container$1results_1 = (com.liferay.taglib.ui.SearchContainerResultsTag) _jspx_tagPool_liferay$1ui_search$1container$1results.get(com.liferay.taglib.ui.SearchContainerResultsTag.class);
            _jspx_th_liferay$1ui_search$1container$1results_1.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1ui_search$1container$1results_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container_1);
            int _jspx_eval_liferay$1ui_search$1container$1results_1 = _jspx_th_liferay$1ui_search$1container$1results_1.doStartTag();
            results = (java.util.List) _jspx_page_context.findAttribute("results");
            deprecatedTotal = (java.lang.Integer) _jspx_page_context.findAttribute("deprecatedTotal");
            if (_jspx_eval_liferay$1ui_search$1container$1results_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              do {
                out.write("\n\n\t\t");

		if (mvcRenderCommandName.equals("/message_boards/view_my_subscriptions")) {
			total = MBThreadServiceUtil.getGroupThreadsCount(scopeGroupId, groupThreadsUserId, WorkflowConstants.STATUS_APPROVED, true);

			searchContainer.setTotal(total);

			results = MBThreadServiceUtil.getGroupThreads(scopeGroupId, groupThreadsUserId, WorkflowConstants.STATUS_APPROVED, true, searchContainer.getStart(), searchContainer.getEnd());
		}
		else if (useAssetEntryQuery) {
			AssetEntryQuery assetEntryQuery = new AssetEntryQuery(MBMessage.class.getName(), searchContainer);

			assetEntryQuery.setEnablePermissions(true);
			assetEntryQuery.setExcludeZeroViewCount(false);

			total = AssetEntryServiceUtil.getEntriesCount(assetEntryQuery);

			searchContainer.setTotal(total);

			assetEntryQuery.setEnd(searchContainer.getEnd());
			assetEntryQuery.setStart(searchContainer.getStart());

			results = AssetEntryServiceUtil.getEntries(assetEntryQuery);
		}

		searchContainer.setResults(results);
		
                out.write("\n\n\t");
                int evalDoAfterBody = _jspx_th_liferay$1ui_search$1container$1results_1.doAfterBody();
                results = (java.util.List) _jspx_page_context.findAttribute("results");
                deprecatedTotal = (java.lang.Integer) _jspx_page_context.findAttribute("deprecatedTotal");
                if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                  break;
              } while (true);
            }
            if (_jspx_th_liferay$1ui_search$1container$1results_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_liferay$1ui_search$1container$1results.reuse(_jspx_th_liferay$1ui_search$1container$1results_1);
              return;
            }
            results = (java.util.List) _jspx_page_context.findAttribute("results");
            deprecatedTotal = (java.lang.Integer) _jspx_page_context.findAttribute("deprecatedTotal");
            _jspx_tagPool_liferay$1ui_search$1container$1results.reuse(_jspx_th_liferay$1ui_search$1container$1results_1);
            out.write("\n\n\t");
            //  liferay-ui:search-container-row
            com.liferay.taglib.ui.SearchContainerRowTag _jspx_th_liferay$1ui_search$1container$1row_1 = (com.liferay.taglib.ui.SearchContainerRowTag) _jspx_tagPool_liferay$1ui_search$1container$1row_modelVar_keyProperty_className.get(com.liferay.taglib.ui.SearchContainerRowTag.class);
            _jspx_th_liferay$1ui_search$1container$1row_1.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1ui_search$1container$1row_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container_1);
            _jspx_th_liferay$1ui_search$1container$1row_1.setClassName("com.liferay.portal.kernel.model.BaseModel");
            _jspx_th_liferay$1ui_search$1container$1row_1.setKeyProperty("primaryKeyObj");
            _jspx_th_liferay$1ui_search$1container$1row_1.setModelVar("result");
            int _jspx_eval_liferay$1ui_search$1container$1row_1 = _jspx_th_liferay$1ui_search$1container$1row_1.doStartTag();
            if (_jspx_eval_liferay$1ui_search$1container$1row_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              java.lang.Integer index = null;
              com.liferay.portal.kernel.model.BaseModel result = null;
              com.liferay.portal.kernel.dao.search.ResultRow row = null;
              if (_jspx_eval_liferay$1ui_search$1container$1row_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                out = _jspx_page_context.pushBody();
                _jspx_th_liferay$1ui_search$1container$1row_1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                _jspx_th_liferay$1ui_search$1container$1row_1.doInitBody();
              }
              index = (java.lang.Integer) _jspx_page_context.findAttribute("index");
              result = (com.liferay.portal.kernel.model.BaseModel) _jspx_page_context.findAttribute("result");
              row = (com.liferay.portal.kernel.dao.search.ResultRow) _jspx_page_context.findAttribute("row");
              do {
                out.write("\n\t\t");
                out.write('\n');
                out.write('\n');

MBThread thread = null;

if (result instanceof AssetEntry) {
	AssetEntry assetEntry = (AssetEntry)result;

	MBMessage message = MBMessageLocalServiceUtil.getMessage(assetEntry.getClassPK());

	thread = message.getThread();
}
else {
	thread = (MBThread)result;
}

                out.write("\n\n\t\t");

		MBMessage message = MBMessageLocalServiceUtil.fetchMBMessage(thread.getRootMessageId());

		if (message == null) {
			_log.error("Thread requires missing root message id " + thread.getRootMessageId());

			row.setSkip(true);
		}

		if (message != null) {
			message = message.toEscapedModel();

			row.setBold(!MBThreadFlagLocalServiceUtil.hasThreadFlag(themeDisplay.getUserId(), thread));
			row.setRestricted(!MBMessagePermission.contains(permissionChecker, message, ActionKeys.VIEW));
		}
		
                out.write("\n\n\t\t");
                //  liferay-portlet:renderURL
                com.liferay.taglib.portlet.RenderURLTag _jspx_th_liferay$1portlet_renderURL_1 = (com.liferay.taglib.portlet.RenderURLTag) _jspx_tagPool_liferay$1portlet_renderURL_varImpl.get(com.liferay.taglib.portlet.RenderURLTag.class);
                _jspx_th_liferay$1portlet_renderURL_1.setPageContext(_jspx_page_context);
                _jspx_th_liferay$1portlet_renderURL_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1row_1);
                _jspx_th_liferay$1portlet_renderURL_1.setVarImpl("rowURL");
                int _jspx_eval_liferay$1portlet_renderURL_1 = _jspx_th_liferay$1portlet_renderURL_1.doStartTag();
                if (_jspx_eval_liferay$1portlet_renderURL_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  out.write("\n\t\t\t");
                  if (_jspx_meth_portlet_param_3((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1portlet_renderURL_1, _jspx_page_context))
                    return;
                  out.write("\n\t\t\t");
                  //  portlet:param
                  com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_4 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                  _jspx_th_portlet_param_4.setPageContext(_jspx_page_context);
                  _jspx_th_portlet_param_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_1);
                  _jspx_th_portlet_param_4.setName("messageId");
                  _jspx_th_portlet_param_4.setValue( (message != null) ? String.valueOf(message.getMessageId()) : String.valueOf(MBMessageConstants.DEFAULT_PARENT_MESSAGE_ID) );
                  int _jspx_eval_portlet_param_4 = _jspx_th_portlet_param_4.doStartTag();
                  if (_jspx_th_portlet_param_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_4);
                    return;
                  }
                  _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_4);
                  out.write("\n\t\t");
                }
                if (_jspx_th_liferay$1portlet_renderURL_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_liferay$1portlet_renderURL_varImpl.reuse(_jspx_th_liferay$1portlet_renderURL_1);
                  return;
                }
                _jspx_tagPool_liferay$1portlet_renderURL_varImpl.reuse(_jspx_th_liferay$1portlet_renderURL_1);
                com.liferay.portal.kernel.portlet.LiferayPortletURL rowURL = null;
                rowURL = (com.liferay.portal.kernel.portlet.LiferayPortletURL) _jspx_page_context.findAttribute("rowURL");
                out.write("\n\n\t\t");
                out.write('\n');
                out.write('\n');
                //  liferay-ui:search-container-column-text
                com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_4 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text_name_href.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
                _jspx_th_liferay$1ui_search$1container$1column$1text_4.setPageContext(_jspx_page_context);
                _jspx_th_liferay$1ui_search$1container$1column$1text_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1row_1);
                _jspx_th_liferay$1ui_search$1container$1column$1text_4.setHref( rowURL );
                _jspx_th_liferay$1ui_search$1container$1column$1text_4.setName("thread");
                int _jspx_eval_liferay$1ui_search$1container$1column$1text_4 = _jspx_th_liferay$1ui_search$1container$1column$1text_4.doStartTag();
                if (_jspx_eval_liferay$1ui_search$1container$1column$1text_4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  if (_jspx_eval_liferay$1ui_search$1container$1column$1text_4 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                    out = _jspx_page_context.pushBody();
                    _jspx_th_liferay$1ui_search$1container$1column$1text_4.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                    _jspx_th_liferay$1ui_search$1container$1column$1text_4.doInitBody();
                  }
                  do {
                    out.write("\n\n\t");

	String[] threadPriority = MBUtil.getThreadPriority(mbGroupServiceSettings, themeDisplay.getLanguageId(), thread.getPriority());
	
                    out.write("\n\n\t");
                    //  c:if
                    com.liferay.taglib.core.IfTag _jspx_th_c_if_0 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                    _jspx_th_c_if_0.setPageContext(_jspx_page_context);
                    _jspx_th_c_if_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1column$1text_4);
                    _jspx_th_c_if_0.setTest( (threadPriority != null) && (thread.getPriority() > 0) );
                    int _jspx_eval_c_if_0 = _jspx_th_c_if_0.doStartTag();
                    if (_jspx_eval_c_if_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      out.write("\n\t\t<span class=\"text-default ");
                      out.print( threadPriority[1] );
                      out.write("\" title=\"");
                      out.print( HtmlUtil.escapeAttribute(threadPriority[0]) );
                      out.write("\"></span>\n\t");
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
                    _jspx_th_c_if_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1column$1text_4);
                    _jspx_th_c_if_1.setTest( thread.isLocked() );
                    int _jspx_eval_c_if_1 = _jspx_th_c_if_1.doStartTag();
                    if (_jspx_eval_c_if_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      out.write("\n\t\t<i class=\"icon-lock\" title=\"");
                      if (_jspx_meth_liferay$1ui_message_1((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_1, _jspx_page_context))
                        return;
                      out.write("\"></i>\n\t");
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
                    _jspx_th_c_if_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1column$1text_4);
                    _jspx_th_c_if_2.setTest( message != null );
                    int _jspx_eval_c_if_2 = _jspx_th_c_if_2.doStartTag();
                    if (_jspx_eval_c_if_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      out.write("\n\t\t");
                      out.print( message.getSubject() );
                      out.write('\n');
                      out.write('	');
                    }
                    if (_jspx_th_c_if_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_2);
                      return;
                    }
                    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_2);
                    out.write('\n');
                    int evalDoAfterBody = _jspx_th_liferay$1ui_search$1container$1column$1text_4.doAfterBody();
                    if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                      break;
                  } while (true);
                  if (_jspx_eval_liferay$1ui_search$1container$1column$1text_4 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                    out = _jspx_page_context.popBody();
                }
                if (_jspx_th_liferay$1ui_search$1container$1column$1text_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_liferay$1ui_search$1container$1column$1text_name_href.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_4);
                  return;
                }
                _jspx_tagPool_liferay$1ui_search$1container$1column$1text_name_href.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_4);
                out.write('\n');
                out.write('\n');
                //  liferay-ui:search-container-column-text
                com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_5 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_href_nobody.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
                _jspx_th_liferay$1ui_search$1container$1column$1text_5.setPageContext(_jspx_page_context);
                _jspx_th_liferay$1ui_search$1container$1column$1text_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1row_1);
                _jspx_th_liferay$1ui_search$1container$1column$1text_5.setHref( rowURL );
                _jspx_th_liferay$1ui_search$1container$1column$1text_5.setName("started-by");
                _jspx_th_liferay$1ui_search$1container$1column$1text_5.setValue( ((message == null) || message.isAnonymous()) ? LanguageUtil.get(request, "anonymous") : PortalUtil.getUserName(message) );
                int _jspx_eval_liferay$1ui_search$1container$1column$1text_5 = _jspx_th_liferay$1ui_search$1container$1column$1text_5.doStartTag();
                if (_jspx_th_liferay$1ui_search$1container$1column$1text_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_href_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_5);
                  return;
                }
                _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_href_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_5);
                out.write('\n');
                out.write('\n');
                //  liferay-ui:search-container-column-text
                com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_6 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_href_nobody.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
                _jspx_th_liferay$1ui_search$1container$1column$1text_6.setPageContext(_jspx_page_context);
                _jspx_th_liferay$1ui_search$1container$1column$1text_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1row_1);
                _jspx_th_liferay$1ui_search$1container$1column$1text_6.setHref( rowURL );
                _jspx_th_liferay$1ui_search$1container$1column$1text_6.setName("replies");
                _jspx_th_liferay$1ui_search$1container$1column$1text_6.setValue( String.valueOf(Math.max(thread.getMessageCount() - 1, 0)) );
                int _jspx_eval_liferay$1ui_search$1container$1column$1text_6 = _jspx_th_liferay$1ui_search$1container$1column$1text_6.doStartTag();
                if (_jspx_th_liferay$1ui_search$1container$1column$1text_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_href_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_6);
                  return;
                }
                _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_href_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_6);
                out.write('\n');
                out.write('\n');
                //  liferay-ui:search-container-column-text
                com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_7 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_href_nobody.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
                _jspx_th_liferay$1ui_search$1container$1column$1text_7.setPageContext(_jspx_page_context);
                _jspx_th_liferay$1ui_search$1container$1column$1text_7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1row_1);
                _jspx_th_liferay$1ui_search$1container$1column$1text_7.setHref( rowURL );
                _jspx_th_liferay$1ui_search$1container$1column$1text_7.setName("views");
                _jspx_th_liferay$1ui_search$1container$1column$1text_7.setValue( String.valueOf(thread.getViewCount()) );
                int _jspx_eval_liferay$1ui_search$1container$1column$1text_7 = _jspx_th_liferay$1ui_search$1container$1column$1text_7.doStartTag();
                if (_jspx_th_liferay$1ui_search$1container$1column$1text_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_href_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_7);
                  return;
                }
                _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_href_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_7);
                out.write('\n');
                out.write('\n');
                //  liferay-ui:search-container-column-user
                com.liferay.taglib.ui.SearchContainerColumnUserTag _jspx_th_liferay$1ui_search$1container$1column$1user_0 = (com.liferay.taglib.ui.SearchContainerColumnUserTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1user_userId_property_name_date_nobody.get(com.liferay.taglib.ui.SearchContainerColumnUserTag.class);
                _jspx_th_liferay$1ui_search$1container$1column$1user_0.setPageContext(_jspx_page_context);
                _jspx_th_liferay$1ui_search$1container$1column$1user_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1row_1);
                _jspx_th_liferay$1ui_search$1container$1column$1user_0.setDate( thread.getLastPostDate() );
                _jspx_th_liferay$1ui_search$1container$1column$1user_0.setName("last-post");
                _jspx_th_liferay$1ui_search$1container$1column$1user_0.setProperty("lastPostByUserId");
                _jspx_th_liferay$1ui_search$1container$1column$1user_0.setUserId( thread.getLastPostByUserId() );
                int _jspx_eval_liferay$1ui_search$1container$1column$1user_0 = _jspx_th_liferay$1ui_search$1container$1column$1user_0.doStartTag();
                if (_jspx_th_liferay$1ui_search$1container$1column$1user_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_liferay$1ui_search$1container$1column$1user_userId_property_name_date_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1user_0);
                  return;
                }
                _jspx_tagPool_liferay$1ui_search$1container$1column$1user_userId_property_name_date_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1user_0);
                out.write('\n');
                out.write('\n');
                //  c:if
                com.liferay.taglib.core.IfTag _jspx_th_c_if_3 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                _jspx_th_c_if_3.setPageContext(_jspx_page_context);
                _jspx_th_c_if_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1row_1);
                _jspx_th_c_if_3.setTest( mvcRenderCommandName.equals("/message_boards/view_my_posts") );
                int _jspx_eval_c_if_3 = _jspx_th_c_if_3.doStartTag();
                if (_jspx_eval_c_if_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  out.write('\n');
                  out.write('	');
                  //  liferay-ui:search-container-column-status
                  com.liferay.taglib.ui.SearchContainerColumnStatusTag _jspx_th_liferay$1ui_search$1container$1column$1status_0 = (com.liferay.taglib.ui.SearchContainerColumnStatusTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1status_status_name_href_nobody.get(com.liferay.taglib.ui.SearchContainerColumnStatusTag.class);
                  _jspx_th_liferay$1ui_search$1container$1column$1status_0.setPageContext(_jspx_page_context);
                  _jspx_th_liferay$1ui_search$1container$1column$1status_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_3);
                  _jspx_th_liferay$1ui_search$1container$1column$1status_0.setHref( rowURL );
                  _jspx_th_liferay$1ui_search$1container$1column$1status_0.setName("status");
                  _jspx_th_liferay$1ui_search$1container$1column$1status_0.setStatus( thread.getStatus() );
                  int _jspx_eval_liferay$1ui_search$1container$1column$1status_0 = _jspx_th_liferay$1ui_search$1container$1column$1status_0.doStartTag();
                  if (_jspx_th_liferay$1ui_search$1container$1column$1status_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_liferay$1ui_search$1container$1column$1status_status_name_href_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1status_0);
                    return;
                  }
                  _jspx_tagPool_liferay$1ui_search$1container$1column$1status_status_name_href_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1status_0);
                  out.write('\n');
                }
                if (_jspx_th_c_if_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_3);
                  return;
                }
                _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_3);
                out.write('\n');
                out.write('\n');

row.setObject(new Object[] {message, threadSubscriptionClassPKs});

                out.write('\n');
                out.write('\n');
                //  c:if
                com.liferay.taglib.core.IfTag _jspx_th_c_if_4 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                _jspx_th_c_if_4.setPageContext(_jspx_page_context);
                _jspx_th_c_if_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1row_1);
                _jspx_th_c_if_4.setTest( message != null );
                int _jspx_eval_c_if_4 = _jspx_th_c_if_4.doStartTag();
                if (_jspx_eval_c_if_4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  out.write('\n');
                  out.write('	');
                  if (_jspx_meth_liferay$1ui_search$1container$1column$1jsp_1((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_4, _jspx_page_context))
                    return;
                  out.write('\n');
                }
                if (_jspx_th_c_if_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_4);
                  return;
                }
                _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_4);
                out.write('\n');
                out.write('	');
                int evalDoAfterBody = _jspx_th_liferay$1ui_search$1container$1row_1.doAfterBody();
                index = (java.lang.Integer) _jspx_page_context.findAttribute("index");
                result = (com.liferay.portal.kernel.model.BaseModel) _jspx_page_context.findAttribute("result");
                row = (com.liferay.portal.kernel.dao.search.ResultRow) _jspx_page_context.findAttribute("row");
                if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                  break;
              } while (true);
              if (_jspx_eval_liferay$1ui_search$1container$1row_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                out = _jspx_page_context.popBody();
            }
            if (_jspx_th_liferay$1ui_search$1container$1row_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_liferay$1ui_search$1container$1row_modelVar_keyProperty_className.reuse(_jspx_th_liferay$1ui_search$1container$1row_1);
              return;
            }
            _jspx_tagPool_liferay$1ui_search$1container$1row_modelVar_keyProperty_className.reuse(_jspx_th_liferay$1ui_search$1container$1row_1);
            out.write("\n\n\t");
            if (_jspx_meth_liferay$1ui_search$1iterator_1((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1ui_search$1container_1, _jspx_page_context))
              return;
            out.write('\n');
          }
          if (_jspx_th_liferay$1ui_search$1container_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_liferay$1ui_search$1container_iteratorURL_headerNames.reuse(_jspx_th_liferay$1ui_search$1container_1);
            return;
          }
          _jspx_tagPool_liferay$1ui_search$1container_iteratorURL_headerNames.reuse(_jspx_th_liferay$1ui_search$1container_1);
          out.write("\n\n\t\t\t");

			PortalUtil.setPageSubtitle(LanguageUtil.get(request, StringUtil.replace("my-subscriptions", CharPool.UNDERLINE, CharPool.DASH)), request);
			PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(request, TextFormatter.format("my-subscriptions", TextFormatter.O)), portletURL.toString());
			
          out.write("\n\n\t\t</div>\n\t");
        }
        if (_jspx_th_c_when_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_0);
          return;
        }
        _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_0);
        out.write('\n');
        out.write('	');
        //  c:when
        com.liferay.taglib.core.WhenTag _jspx_th_c_when_1 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
        _jspx_th_c_when_1.setPageContext(_jspx_page_context);
        _jspx_th_c_when_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_0);
        _jspx_th_c_when_1.setTest( useAssetEntryQuery && !mbListDisplayContext.isShowMyPosts() );
        int _jspx_eval_c_when_1 = _jspx_th_c_when_1.doStartTag();
        if (_jspx_eval_c_when_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          out.write("\n\t\t");
          //  liferay-asset:categorization-filter
          com.liferay.asset.taglib.servlet.taglib.CategorizationFilterTag _jspx_th_liferay$1asset_categorization$1filter_0 = (com.liferay.asset.taglib.servlet.taglib.CategorizationFilterTag) _jspx_tagPool_liferay$1asset_categorization$1filter_portletURL_assetType_nobody.get(com.liferay.asset.taglib.servlet.taglib.CategorizationFilterTag.class);
          _jspx_th_liferay$1asset_categorization$1filter_0.setPageContext(_jspx_page_context);
          _jspx_th_liferay$1asset_categorization$1filter_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_1);
          _jspx_th_liferay$1asset_categorization$1filter_0.setAssetType("threads");
          _jspx_th_liferay$1asset_categorization$1filter_0.setPortletURL( portletURL );
          int _jspx_eval_liferay$1asset_categorization$1filter_0 = _jspx_th_liferay$1asset_categorization$1filter_0.doStartTag();
          if (_jspx_th_liferay$1asset_categorization$1filter_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_liferay$1asset_categorization$1filter_portletURL_assetType_nobody.reuse(_jspx_th_liferay$1asset_categorization$1filter_0);
            return;
          }
          _jspx_tagPool_liferay$1asset_categorization$1filter_portletURL_assetType_nobody.reuse(_jspx_th_liferay$1asset_categorization$1filter_0);
          out.write("\n\n\t\t");
          out.write('\n');
          out.write('\n');
          //  liferay-ui:search-container
          com.liferay.taglib.ui.SearchContainerTag _jspx_th_liferay$1ui_search$1container_2 = (com.liferay.taglib.ui.SearchContainerTag) _jspx_tagPool_liferay$1ui_search$1container_iteratorURL_headerNames.get(com.liferay.taglib.ui.SearchContainerTag.class);
          _jspx_th_liferay$1ui_search$1container_2.setPageContext(_jspx_page_context);
          _jspx_th_liferay$1ui_search$1container_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_1);
          _jspx_th_liferay$1ui_search$1container_2.setHeaderNames("thread,started-by,posts,views,last-post");
          _jspx_th_liferay$1ui_search$1container_2.setIteratorURL( portletURL );
          int _jspx_eval_liferay$1ui_search$1container_2 = _jspx_th_liferay$1ui_search$1container_2.doStartTag();
          if (_jspx_eval_liferay$1ui_search$1container_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            java.lang.Integer total = null;
            com.liferay.portal.kernel.dao.search.SearchContainer searchContainer = null;
            total = (java.lang.Integer) _jspx_page_context.findAttribute("total");
            searchContainer = (com.liferay.portal.kernel.dao.search.SearchContainer) _jspx_page_context.findAttribute("searchContainer");
            out.write("\n\n\t");

	String emptyResultsMessage = null;

	if (mvcRenderCommandName.equals("/message_boards/view_my_subscriptions")) {
		emptyResultsMessage = "you-are-not-subscribed-to-any-threads";
	}
	else {
		emptyResultsMessage = "there-are-no-threads-in-this-category";
	}

	searchContainer.setEmptyResultsMessage(emptyResultsMessage);
	
            out.write("\n\n\t");
            //  liferay-ui:search-container-results
            java.util.List results = null;
            java.lang.Integer deprecatedTotal = null;
            com.liferay.taglib.ui.SearchContainerResultsTag _jspx_th_liferay$1ui_search$1container$1results_2 = (com.liferay.taglib.ui.SearchContainerResultsTag) _jspx_tagPool_liferay$1ui_search$1container$1results.get(com.liferay.taglib.ui.SearchContainerResultsTag.class);
            _jspx_th_liferay$1ui_search$1container$1results_2.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1ui_search$1container$1results_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container_2);
            int _jspx_eval_liferay$1ui_search$1container$1results_2 = _jspx_th_liferay$1ui_search$1container$1results_2.doStartTag();
            results = (java.util.List) _jspx_page_context.findAttribute("results");
            deprecatedTotal = (java.lang.Integer) _jspx_page_context.findAttribute("deprecatedTotal");
            if (_jspx_eval_liferay$1ui_search$1container$1results_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              do {
                out.write("\n\n\t\t");

		if (mvcRenderCommandName.equals("/message_boards/view_my_subscriptions")) {
			total = MBThreadServiceUtil.getGroupThreadsCount(scopeGroupId, groupThreadsUserId, WorkflowConstants.STATUS_APPROVED, true);

			searchContainer.setTotal(total);

			results = MBThreadServiceUtil.getGroupThreads(scopeGroupId, groupThreadsUserId, WorkflowConstants.STATUS_APPROVED, true, searchContainer.getStart(), searchContainer.getEnd());
		}
		else if (useAssetEntryQuery) {
			AssetEntryQuery assetEntryQuery = new AssetEntryQuery(MBMessage.class.getName(), searchContainer);

			assetEntryQuery.setEnablePermissions(true);
			assetEntryQuery.setExcludeZeroViewCount(false);

			total = AssetEntryServiceUtil.getEntriesCount(assetEntryQuery);

			searchContainer.setTotal(total);

			assetEntryQuery.setEnd(searchContainer.getEnd());
			assetEntryQuery.setStart(searchContainer.getStart());

			results = AssetEntryServiceUtil.getEntries(assetEntryQuery);
		}

		searchContainer.setResults(results);
		
                out.write("\n\n\t");
                int evalDoAfterBody = _jspx_th_liferay$1ui_search$1container$1results_2.doAfterBody();
                results = (java.util.List) _jspx_page_context.findAttribute("results");
                deprecatedTotal = (java.lang.Integer) _jspx_page_context.findAttribute("deprecatedTotal");
                if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                  break;
              } while (true);
            }
            if (_jspx_th_liferay$1ui_search$1container$1results_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_liferay$1ui_search$1container$1results.reuse(_jspx_th_liferay$1ui_search$1container$1results_2);
              return;
            }
            results = (java.util.List) _jspx_page_context.findAttribute("results");
            deprecatedTotal = (java.lang.Integer) _jspx_page_context.findAttribute("deprecatedTotal");
            _jspx_tagPool_liferay$1ui_search$1container$1results.reuse(_jspx_th_liferay$1ui_search$1container$1results_2);
            out.write("\n\n\t");
            //  liferay-ui:search-container-row
            com.liferay.taglib.ui.SearchContainerRowTag _jspx_th_liferay$1ui_search$1container$1row_2 = (com.liferay.taglib.ui.SearchContainerRowTag) _jspx_tagPool_liferay$1ui_search$1container$1row_modelVar_keyProperty_className.get(com.liferay.taglib.ui.SearchContainerRowTag.class);
            _jspx_th_liferay$1ui_search$1container$1row_2.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1ui_search$1container$1row_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container_2);
            _jspx_th_liferay$1ui_search$1container$1row_2.setClassName("com.liferay.portal.kernel.model.BaseModel");
            _jspx_th_liferay$1ui_search$1container$1row_2.setKeyProperty("primaryKeyObj");
            _jspx_th_liferay$1ui_search$1container$1row_2.setModelVar("result");
            int _jspx_eval_liferay$1ui_search$1container$1row_2 = _jspx_th_liferay$1ui_search$1container$1row_2.doStartTag();
            if (_jspx_eval_liferay$1ui_search$1container$1row_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              java.lang.Integer index = null;
              com.liferay.portal.kernel.model.BaseModel result = null;
              com.liferay.portal.kernel.dao.search.ResultRow row = null;
              if (_jspx_eval_liferay$1ui_search$1container$1row_2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                out = _jspx_page_context.pushBody();
                _jspx_th_liferay$1ui_search$1container$1row_2.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                _jspx_th_liferay$1ui_search$1container$1row_2.doInitBody();
              }
              index = (java.lang.Integer) _jspx_page_context.findAttribute("index");
              result = (com.liferay.portal.kernel.model.BaseModel) _jspx_page_context.findAttribute("result");
              row = (com.liferay.portal.kernel.dao.search.ResultRow) _jspx_page_context.findAttribute("row");
              do {
                out.write("\n\t\t");
                out.write('\n');
                out.write('\n');

MBThread thread = null;

if (result instanceof AssetEntry) {
	AssetEntry assetEntry = (AssetEntry)result;

	MBMessage message = MBMessageLocalServiceUtil.getMessage(assetEntry.getClassPK());

	thread = message.getThread();
}
else {
	thread = (MBThread)result;
}

                out.write("\n\n\t\t");

		MBMessage message = MBMessageLocalServiceUtil.fetchMBMessage(thread.getRootMessageId());

		if (message == null) {
			_log.error("Thread requires missing root message id " + thread.getRootMessageId());

			row.setSkip(true);
		}

		if (message != null) {
			message = message.toEscapedModel();

			row.setBold(!MBThreadFlagLocalServiceUtil.hasThreadFlag(themeDisplay.getUserId(), thread));
			row.setRestricted(!MBMessagePermission.contains(permissionChecker, message, ActionKeys.VIEW));
		}
		
                out.write("\n\n\t\t");
                //  liferay-portlet:renderURL
                com.liferay.taglib.portlet.RenderURLTag _jspx_th_liferay$1portlet_renderURL_2 = (com.liferay.taglib.portlet.RenderURLTag) _jspx_tagPool_liferay$1portlet_renderURL_varImpl.get(com.liferay.taglib.portlet.RenderURLTag.class);
                _jspx_th_liferay$1portlet_renderURL_2.setPageContext(_jspx_page_context);
                _jspx_th_liferay$1portlet_renderURL_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1row_2);
                _jspx_th_liferay$1portlet_renderURL_2.setVarImpl("rowURL");
                int _jspx_eval_liferay$1portlet_renderURL_2 = _jspx_th_liferay$1portlet_renderURL_2.doStartTag();
                if (_jspx_eval_liferay$1portlet_renderURL_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  out.write("\n\t\t\t");
                  if (_jspx_meth_portlet_param_5((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1portlet_renderURL_2, _jspx_page_context))
                    return;
                  out.write("\n\t\t\t");
                  //  portlet:param
                  com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_6 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                  _jspx_th_portlet_param_6.setPageContext(_jspx_page_context);
                  _jspx_th_portlet_param_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_2);
                  _jspx_th_portlet_param_6.setName("messageId");
                  _jspx_th_portlet_param_6.setValue( (message != null) ? String.valueOf(message.getMessageId()) : String.valueOf(MBMessageConstants.DEFAULT_PARENT_MESSAGE_ID) );
                  int _jspx_eval_portlet_param_6 = _jspx_th_portlet_param_6.doStartTag();
                  if (_jspx_th_portlet_param_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_6);
                    return;
                  }
                  _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_6);
                  out.write("\n\t\t");
                }
                if (_jspx_th_liferay$1portlet_renderURL_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_liferay$1portlet_renderURL_varImpl.reuse(_jspx_th_liferay$1portlet_renderURL_2);
                  return;
                }
                _jspx_tagPool_liferay$1portlet_renderURL_varImpl.reuse(_jspx_th_liferay$1portlet_renderURL_2);
                com.liferay.portal.kernel.portlet.LiferayPortletURL rowURL = null;
                rowURL = (com.liferay.portal.kernel.portlet.LiferayPortletURL) _jspx_page_context.findAttribute("rowURL");
                out.write("\n\n\t\t");
                out.write('\n');
                out.write('\n');
                //  liferay-ui:search-container-column-text
                com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_8 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text_name_href.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
                _jspx_th_liferay$1ui_search$1container$1column$1text_8.setPageContext(_jspx_page_context);
                _jspx_th_liferay$1ui_search$1container$1column$1text_8.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1row_2);
                _jspx_th_liferay$1ui_search$1container$1column$1text_8.setHref( rowURL );
                _jspx_th_liferay$1ui_search$1container$1column$1text_8.setName("thread");
                int _jspx_eval_liferay$1ui_search$1container$1column$1text_8 = _jspx_th_liferay$1ui_search$1container$1column$1text_8.doStartTag();
                if (_jspx_eval_liferay$1ui_search$1container$1column$1text_8 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  if (_jspx_eval_liferay$1ui_search$1container$1column$1text_8 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                    out = _jspx_page_context.pushBody();
                    _jspx_th_liferay$1ui_search$1container$1column$1text_8.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                    _jspx_th_liferay$1ui_search$1container$1column$1text_8.doInitBody();
                  }
                  do {
                    out.write("\n\n\t");

	String[] threadPriority = MBUtil.getThreadPriority(mbGroupServiceSettings, themeDisplay.getLanguageId(), thread.getPriority());
	
                    out.write("\n\n\t");
                    //  c:if
                    com.liferay.taglib.core.IfTag _jspx_th_c_if_5 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                    _jspx_th_c_if_5.setPageContext(_jspx_page_context);
                    _jspx_th_c_if_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1column$1text_8);
                    _jspx_th_c_if_5.setTest( (threadPriority != null) && (thread.getPriority() > 0) );
                    int _jspx_eval_c_if_5 = _jspx_th_c_if_5.doStartTag();
                    if (_jspx_eval_c_if_5 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      out.write("\n\t\t<span class=\"text-default ");
                      out.print( threadPriority[1] );
                      out.write("\" title=\"");
                      out.print( HtmlUtil.escapeAttribute(threadPriority[0]) );
                      out.write("\"></span>\n\t");
                    }
                    if (_jspx_th_c_if_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_5);
                      return;
                    }
                    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_5);
                    out.write("\n\n\t");
                    //  c:if
                    com.liferay.taglib.core.IfTag _jspx_th_c_if_6 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                    _jspx_th_c_if_6.setPageContext(_jspx_page_context);
                    _jspx_th_c_if_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1column$1text_8);
                    _jspx_th_c_if_6.setTest( thread.isLocked() );
                    int _jspx_eval_c_if_6 = _jspx_th_c_if_6.doStartTag();
                    if (_jspx_eval_c_if_6 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      out.write("\n\t\t<i class=\"icon-lock\" title=\"");
                      if (_jspx_meth_liferay$1ui_message_2((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_6, _jspx_page_context))
                        return;
                      out.write("\"></i>\n\t");
                    }
                    if (_jspx_th_c_if_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_6);
                      return;
                    }
                    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_6);
                    out.write("\n\n\t");
                    //  c:if
                    com.liferay.taglib.core.IfTag _jspx_th_c_if_7 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                    _jspx_th_c_if_7.setPageContext(_jspx_page_context);
                    _jspx_th_c_if_7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1column$1text_8);
                    _jspx_th_c_if_7.setTest( message != null );
                    int _jspx_eval_c_if_7 = _jspx_th_c_if_7.doStartTag();
                    if (_jspx_eval_c_if_7 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      out.write("\n\t\t");
                      out.print( message.getSubject() );
                      out.write('\n');
                      out.write('	');
                    }
                    if (_jspx_th_c_if_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_7);
                      return;
                    }
                    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_7);
                    out.write('\n');
                    int evalDoAfterBody = _jspx_th_liferay$1ui_search$1container$1column$1text_8.doAfterBody();
                    if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                      break;
                  } while (true);
                  if (_jspx_eval_liferay$1ui_search$1container$1column$1text_8 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                    out = _jspx_page_context.popBody();
                }
                if (_jspx_th_liferay$1ui_search$1container$1column$1text_8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_liferay$1ui_search$1container$1column$1text_name_href.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_8);
                  return;
                }
                _jspx_tagPool_liferay$1ui_search$1container$1column$1text_name_href.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_8);
                out.write('\n');
                out.write('\n');
                //  liferay-ui:search-container-column-text
                com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_9 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_href_nobody.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
                _jspx_th_liferay$1ui_search$1container$1column$1text_9.setPageContext(_jspx_page_context);
                _jspx_th_liferay$1ui_search$1container$1column$1text_9.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1row_2);
                _jspx_th_liferay$1ui_search$1container$1column$1text_9.setHref( rowURL );
                _jspx_th_liferay$1ui_search$1container$1column$1text_9.setName("started-by");
                _jspx_th_liferay$1ui_search$1container$1column$1text_9.setValue( ((message == null) || message.isAnonymous()) ? LanguageUtil.get(request, "anonymous") : PortalUtil.getUserName(message) );
                int _jspx_eval_liferay$1ui_search$1container$1column$1text_9 = _jspx_th_liferay$1ui_search$1container$1column$1text_9.doStartTag();
                if (_jspx_th_liferay$1ui_search$1container$1column$1text_9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_href_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_9);
                  return;
                }
                _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_href_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_9);
                out.write('\n');
                out.write('\n');
                //  liferay-ui:search-container-column-text
                com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_10 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_href_nobody.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
                _jspx_th_liferay$1ui_search$1container$1column$1text_10.setPageContext(_jspx_page_context);
                _jspx_th_liferay$1ui_search$1container$1column$1text_10.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1row_2);
                _jspx_th_liferay$1ui_search$1container$1column$1text_10.setHref( rowURL );
                _jspx_th_liferay$1ui_search$1container$1column$1text_10.setName("replies");
                _jspx_th_liferay$1ui_search$1container$1column$1text_10.setValue( String.valueOf(Math.max(thread.getMessageCount() - 1, 0)) );
                int _jspx_eval_liferay$1ui_search$1container$1column$1text_10 = _jspx_th_liferay$1ui_search$1container$1column$1text_10.doStartTag();
                if (_jspx_th_liferay$1ui_search$1container$1column$1text_10.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_href_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_10);
                  return;
                }
                _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_href_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_10);
                out.write('\n');
                out.write('\n');
                //  liferay-ui:search-container-column-text
                com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_11 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_href_nobody.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
                _jspx_th_liferay$1ui_search$1container$1column$1text_11.setPageContext(_jspx_page_context);
                _jspx_th_liferay$1ui_search$1container$1column$1text_11.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1row_2);
                _jspx_th_liferay$1ui_search$1container$1column$1text_11.setHref( rowURL );
                _jspx_th_liferay$1ui_search$1container$1column$1text_11.setName("views");
                _jspx_th_liferay$1ui_search$1container$1column$1text_11.setValue( String.valueOf(thread.getViewCount()) );
                int _jspx_eval_liferay$1ui_search$1container$1column$1text_11 = _jspx_th_liferay$1ui_search$1container$1column$1text_11.doStartTag();
                if (_jspx_th_liferay$1ui_search$1container$1column$1text_11.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_href_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_11);
                  return;
                }
                _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_href_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_11);
                out.write('\n');
                out.write('\n');
                //  liferay-ui:search-container-column-user
                com.liferay.taglib.ui.SearchContainerColumnUserTag _jspx_th_liferay$1ui_search$1container$1column$1user_1 = (com.liferay.taglib.ui.SearchContainerColumnUserTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1user_userId_property_name_date_nobody.get(com.liferay.taglib.ui.SearchContainerColumnUserTag.class);
                _jspx_th_liferay$1ui_search$1container$1column$1user_1.setPageContext(_jspx_page_context);
                _jspx_th_liferay$1ui_search$1container$1column$1user_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1row_2);
                _jspx_th_liferay$1ui_search$1container$1column$1user_1.setDate( thread.getLastPostDate() );
                _jspx_th_liferay$1ui_search$1container$1column$1user_1.setName("last-post");
                _jspx_th_liferay$1ui_search$1container$1column$1user_1.setProperty("lastPostByUserId");
                _jspx_th_liferay$1ui_search$1container$1column$1user_1.setUserId( thread.getLastPostByUserId() );
                int _jspx_eval_liferay$1ui_search$1container$1column$1user_1 = _jspx_th_liferay$1ui_search$1container$1column$1user_1.doStartTag();
                if (_jspx_th_liferay$1ui_search$1container$1column$1user_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_liferay$1ui_search$1container$1column$1user_userId_property_name_date_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1user_1);
                  return;
                }
                _jspx_tagPool_liferay$1ui_search$1container$1column$1user_userId_property_name_date_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1user_1);
                out.write('\n');
                out.write('\n');
                //  c:if
                com.liferay.taglib.core.IfTag _jspx_th_c_if_8 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                _jspx_th_c_if_8.setPageContext(_jspx_page_context);
                _jspx_th_c_if_8.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1row_2);
                _jspx_th_c_if_8.setTest( mvcRenderCommandName.equals("/message_boards/view_my_posts") );
                int _jspx_eval_c_if_8 = _jspx_th_c_if_8.doStartTag();
                if (_jspx_eval_c_if_8 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  out.write('\n');
                  out.write('	');
                  //  liferay-ui:search-container-column-status
                  com.liferay.taglib.ui.SearchContainerColumnStatusTag _jspx_th_liferay$1ui_search$1container$1column$1status_1 = (com.liferay.taglib.ui.SearchContainerColumnStatusTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1status_status_name_href_nobody.get(com.liferay.taglib.ui.SearchContainerColumnStatusTag.class);
                  _jspx_th_liferay$1ui_search$1container$1column$1status_1.setPageContext(_jspx_page_context);
                  _jspx_th_liferay$1ui_search$1container$1column$1status_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_8);
                  _jspx_th_liferay$1ui_search$1container$1column$1status_1.setHref( rowURL );
                  _jspx_th_liferay$1ui_search$1container$1column$1status_1.setName("status");
                  _jspx_th_liferay$1ui_search$1container$1column$1status_1.setStatus( thread.getStatus() );
                  int _jspx_eval_liferay$1ui_search$1container$1column$1status_1 = _jspx_th_liferay$1ui_search$1container$1column$1status_1.doStartTag();
                  if (_jspx_th_liferay$1ui_search$1container$1column$1status_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_liferay$1ui_search$1container$1column$1status_status_name_href_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1status_1);
                    return;
                  }
                  _jspx_tagPool_liferay$1ui_search$1container$1column$1status_status_name_href_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1status_1);
                  out.write('\n');
                }
                if (_jspx_th_c_if_8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_8);
                  return;
                }
                _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_8);
                out.write('\n');
                out.write('\n');

row.setObject(new Object[] {message, threadSubscriptionClassPKs});

                out.write('\n');
                out.write('\n');
                //  c:if
                com.liferay.taglib.core.IfTag _jspx_th_c_if_9 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                _jspx_th_c_if_9.setPageContext(_jspx_page_context);
                _jspx_th_c_if_9.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1row_2);
                _jspx_th_c_if_9.setTest( message != null );
                int _jspx_eval_c_if_9 = _jspx_th_c_if_9.doStartTag();
                if (_jspx_eval_c_if_9 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  out.write('\n');
                  out.write('	');
                  if (_jspx_meth_liferay$1ui_search$1container$1column$1jsp_2((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_9, _jspx_page_context))
                    return;
                  out.write('\n');
                }
                if (_jspx_th_c_if_9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_9);
                  return;
                }
                _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_9);
                out.write('\n');
                out.write('	');
                int evalDoAfterBody = _jspx_th_liferay$1ui_search$1container$1row_2.doAfterBody();
                index = (java.lang.Integer) _jspx_page_context.findAttribute("index");
                result = (com.liferay.portal.kernel.model.BaseModel) _jspx_page_context.findAttribute("result");
                row = (com.liferay.portal.kernel.dao.search.ResultRow) _jspx_page_context.findAttribute("row");
                if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                  break;
              } while (true);
              if (_jspx_eval_liferay$1ui_search$1container$1row_2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                out = _jspx_page_context.popBody();
            }
            if (_jspx_th_liferay$1ui_search$1container$1row_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_liferay$1ui_search$1container$1row_modelVar_keyProperty_className.reuse(_jspx_th_liferay$1ui_search$1container$1row_2);
              return;
            }
            _jspx_tagPool_liferay$1ui_search$1container$1row_modelVar_keyProperty_className.reuse(_jspx_th_liferay$1ui_search$1container$1row_2);
            out.write("\n\n\t");
            if (_jspx_meth_liferay$1ui_search$1iterator_2((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1ui_search$1container_2, _jspx_page_context))
              return;
            out.write('\n');
          }
          if (_jspx_th_liferay$1ui_search$1container_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_liferay$1ui_search$1container_iteratorURL_headerNames.reuse(_jspx_th_liferay$1ui_search$1container_2);
            return;
          }
          _jspx_tagPool_liferay$1ui_search$1container_iteratorURL_headerNames.reuse(_jspx_th_liferay$1ui_search$1container_2);
          out.write('\n');
          out.write('	');
        }
        if (_jspx_th_c_when_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_1);
          return;
        }
        _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_1);
        out.write('\n');
        out.write('	');
        //  c:when
        com.liferay.taglib.core.WhenTag _jspx_th_c_when_2 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
        _jspx_th_c_when_2.setPageContext(_jspx_page_context);
        _jspx_th_c_when_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_0);
        _jspx_th_c_when_2.setTest( mbListDisplayContext.isShowSearch() || mvcRenderCommandName.equals("/message_boards/view") || mvcRenderCommandName.equals("/message_boards/view_category") || mbListDisplayContext.isShowMyPosts() || mbListDisplayContext.isShowRecentPosts() );
        int _jspx_eval_c_when_2 = _jspx_th_c_when_2.doStartTag();
        if (_jspx_eval_c_when_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          out.write("\n\t\t");
          //  c:choose
          com.liferay.taglib.core.ChooseTag _jspx_th_c_choose_1 = (com.liferay.taglib.core.ChooseTag) _jspx_tagPool_c_choose.get(com.liferay.taglib.core.ChooseTag.class);
          _jspx_th_c_choose_1.setPageContext(_jspx_page_context);
          _jspx_th_c_choose_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_2);
          int _jspx_eval_c_choose_1 = _jspx_th_c_choose_1.doStartTag();
          if (_jspx_eval_c_choose_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t\t");
            //  c:when
            com.liferay.taglib.core.WhenTag _jspx_th_c_when_3 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
            _jspx_th_c_when_3.setPageContext(_jspx_page_context);
            _jspx_th_c_when_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_1);
            _jspx_th_c_when_3.setTest( mvcRenderCommandName.equals("/message_boards/search") || mvcRenderCommandName.equals("/message_boards/view") || mvcRenderCommandName.equals("/message_boards/view_category") );
            int _jspx_eval_c_when_3 = _jspx_th_c_when_3.doStartTag();
            if (_jspx_eval_c_when_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\t\t\t\t<div class=\"main-content-body\">\n\t\t\t\t\t");
              //  c:if
              com.liferay.taglib.core.IfTag _jspx_th_c_if_10 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
              _jspx_th_c_if_10.setPageContext(_jspx_page_context);
              _jspx_th_c_if_10.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_3);
              _jspx_th_c_if_10.setTest( mbListDisplayContext.isShowSearch() );
              int _jspx_eval_c_if_10 = _jspx_th_c_if_10.doStartTag();
              if (_jspx_eval_c_if_10 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\t\t\t\t\t\t");
                //  liferay-ui:header
                com.liferay.taglib.ui.HeaderTag _jspx_th_liferay$1ui_header_0 = (com.liferay.taglib.ui.HeaderTag) _jspx_tagPool_liferay$1ui_header_title_backURL_nobody.get(com.liferay.taglib.ui.HeaderTag.class);
                _jspx_th_liferay$1ui_header_0.setPageContext(_jspx_page_context);
                _jspx_th_liferay$1ui_header_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_10);
                _jspx_th_liferay$1ui_header_0.setBackURL( redirect );
                _jspx_th_liferay$1ui_header_0.setTitle("search");
                int _jspx_eval_liferay$1ui_header_0 = _jspx_th_liferay$1ui_header_0.doStartTag();
                if (_jspx_th_liferay$1ui_header_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_liferay$1ui_header_title_backURL_nobody.reuse(_jspx_th_liferay$1ui_header_0);
                  return;
                }
                _jspx_tagPool_liferay$1ui_header_title_backURL_nobody.reuse(_jspx_th_liferay$1ui_header_0);
                out.write("\n\t\t\t\t\t");
              }
              if (_jspx_th_c_if_10.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_10);
                return;
              }
              _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_10);
              out.write("\n\n\t\t\t\t\t");

					boolean showAddCategoryButton = MBCategoryPermission.contains(permissionChecker, scopeGroupId, categoryId, ActionKeys.ADD_CATEGORY);
					boolean showAddMessageButton = MBCategoryPermission.contains(permissionChecker, scopeGroupId, categoryId, ActionKeys.ADD_MESSAGE);
					boolean showPermissionsButton = MBResourcePermission.contains(permissionChecker, scopeGroupId, ActionKeys.PERMISSIONS);

					if (showAddMessageButton && !themeDisplay.isSignedIn()) {
						if (!allowAnonymousPosting) {
							showAddMessageButton = false;
						}
					}
					
              out.write("\n\n\t\t\t\t\t");
              //  c:if
              com.liferay.taglib.core.IfTag _jspx_th_c_if_11 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
              _jspx_th_c_if_11.setPageContext(_jspx_page_context);
              _jspx_th_c_if_11.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_3);
              _jspx_th_c_if_11.setTest( category != null );
              int _jspx_eval_c_if_11 = _jspx_th_c_if_11.doStartTag();
              if (_jspx_eval_c_if_11 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\n\t\t\t\t\t\t");

						MBBreadcrumbUtil.addPortletBreadcrumbEntries(categoryId, request, renderResponse);
						
                out.write("\n\n\t\t\t\t\t\t");
                //  liferay-ui:breadcrumb
                com.liferay.taglib.ui.BreadcrumbTag _jspx_th_liferay$1ui_breadcrumb_0 = (com.liferay.taglib.ui.BreadcrumbTag) _jspx_tagPool_liferay$1ui_breadcrumb_showParentGroups_showLayout_showGuestGroup_showCurrentGroup_nobody.get(com.liferay.taglib.ui.BreadcrumbTag.class);
                _jspx_th_liferay$1ui_breadcrumb_0.setPageContext(_jspx_page_context);
                _jspx_th_liferay$1ui_breadcrumb_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_11);
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
                out.write("\n\t\t\t\t\t");
              }
              if (_jspx_th_c_if_11.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_11);
                return;
              }
              _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_11);
              out.write("\n\n\t\t\t\t\t<div class=\"autofit-float autofit-row\">\n\t\t\t\t\t\t<div class=\"autofit-col autofit-col-expand\">\n\t\t\t\t\t\t\t");
              //  c:choose
              com.liferay.taglib.core.ChooseTag _jspx_th_c_choose_2 = (com.liferay.taglib.core.ChooseTag) _jspx_tagPool_c_choose.get(com.liferay.taglib.core.ChooseTag.class);
              _jspx_th_c_choose_2.setPageContext(_jspx_page_context);
              _jspx_th_c_choose_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_3);
              int _jspx_eval_c_choose_2 = _jspx_th_c_choose_2.doStartTag();
              if (_jspx_eval_c_choose_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\t\t\t\t\t\t\t\t");
                //  c:when
                com.liferay.taglib.core.WhenTag _jspx_th_c_when_4 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
                _jspx_th_c_when_4.setPageContext(_jspx_page_context);
                _jspx_th_c_when_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_2);
                _jspx_th_c_when_4.setTest( category != null );
                int _jspx_eval_c_when_4 = _jspx_th_c_when_4.doStartTag();
                if (_jspx_eval_c_when_4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  out.write("\n\t\t\t\t\t\t\t\t\t<h3>");
                  out.print( HtmlUtil.escape(category.getName()) );
                  out.write("</h3>\n\t\t\t\t\t\t\t\t");
                }
                if (_jspx_th_c_when_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_4);
                  return;
                }
                _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_4);
                out.write("\n\t\t\t\t\t\t\t\t");
                //  c:otherwise
                com.liferay.taglib.core.OtherwiseTag _jspx_th_c_otherwise_0 = (com.liferay.taglib.core.OtherwiseTag) _jspx_tagPool_c_otherwise.get(com.liferay.taglib.core.OtherwiseTag.class);
                _jspx_th_c_otherwise_0.setPageContext(_jspx_page_context);
                _jspx_th_c_otherwise_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_2);
                int _jspx_eval_c_otherwise_0 = _jspx_th_c_otherwise_0.doStartTag();
                if (_jspx_eval_c_otherwise_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  out.write("\n\n\t\t\t\t\t\t\t\t\t");

									MBBreadcrumbUtil.addPortletBreadcrumbEntries(categoryId, request, renderResponse);
									
                  out.write("\n\n\t\t\t\t\t\t\t\t\t");
                  //  liferay-ui:breadcrumb
                  com.liferay.taglib.ui.BreadcrumbTag _jspx_th_liferay$1ui_breadcrumb_1 = (com.liferay.taglib.ui.BreadcrumbTag) _jspx_tagPool_liferay$1ui_breadcrumb_showParentGroups_showLayout_showGuestGroup_showCurrentGroup_nobody.get(com.liferay.taglib.ui.BreadcrumbTag.class);
                  _jspx_th_liferay$1ui_breadcrumb_1.setPageContext(_jspx_page_context);
                  _jspx_th_liferay$1ui_breadcrumb_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_0);
                  _jspx_th_liferay$1ui_breadcrumb_1.setShowCurrentGroup( false );
                  _jspx_th_liferay$1ui_breadcrumb_1.setShowGuestGroup( false );
                  _jspx_th_liferay$1ui_breadcrumb_1.setShowLayout( false );
                  _jspx_th_liferay$1ui_breadcrumb_1.setShowParentGroups( false );
                  int _jspx_eval_liferay$1ui_breadcrumb_1 = _jspx_th_liferay$1ui_breadcrumb_1.doStartTag();
                  if (_jspx_th_liferay$1ui_breadcrumb_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_liferay$1ui_breadcrumb_showParentGroups_showLayout_showGuestGroup_showCurrentGroup_nobody.reuse(_jspx_th_liferay$1ui_breadcrumb_1);
                    return;
                  }
                  _jspx_tagPool_liferay$1ui_breadcrumb_showParentGroups_showLayout_showGuestGroup_showCurrentGroup_nobody.reuse(_jspx_th_liferay$1ui_breadcrumb_1);
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
              out.write("\n\t\t\t\t\t\t</div>\n\n\t\t\t\t\t\t<div class=\"autofit-col autofit-col-end\">\n\t\t\t\t\t\t\t<div class=\"btn-group\">\n\t\t\t\t\t\t\t\t");
              //  c:if
              com.liferay.taglib.core.IfTag _jspx_th_c_if_12 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
              _jspx_th_c_if_12.setPageContext(_jspx_page_context);
              _jspx_th_c_if_12.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_3);
              _jspx_th_c_if_12.setTest( showAddCategoryButton );
              int _jspx_eval_c_if_12 = _jspx_th_c_if_12.doStartTag();
              if (_jspx_eval_c_if_12 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\t\t\t\t\t\t\t\t\t");
                //  portlet:renderURL
                com.liferay.taglib.portlet.RenderURLTag _jspx_th_portlet_renderURL_0 = (com.liferay.taglib.portlet.RenderURLTag) _jspx_tagPool_portlet_renderURL_var.get(com.liferay.taglib.portlet.RenderURLTag.class);
                _jspx_th_portlet_renderURL_0.setPageContext(_jspx_page_context);
                _jspx_th_portlet_renderURL_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_12);
                _jspx_th_portlet_renderURL_0.setVar("editCategoryURL");
                int _jspx_eval_portlet_renderURL_0 = _jspx_th_portlet_renderURL_0.doStartTag();
                if (_jspx_eval_portlet_renderURL_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  out.write("\n\t\t\t\t\t\t\t\t\t\t");
                  if (_jspx_meth_portlet_param_7((javax.servlet.jsp.tagext.JspTag) _jspx_th_portlet_renderURL_0, _jspx_page_context))
                    return;
                  out.write("\n\t\t\t\t\t\t\t\t\t\t");
                  //  portlet:param
                  com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_8 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                  _jspx_th_portlet_param_8.setPageContext(_jspx_page_context);
                  _jspx_th_portlet_param_8.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_0);
                  _jspx_th_portlet_param_8.setName("redirect");
                  _jspx_th_portlet_param_8.setValue( currentURL );
                  int _jspx_eval_portlet_param_8 = _jspx_th_portlet_param_8.doStartTag();
                  if (_jspx_th_portlet_param_8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_8);
                    return;
                  }
                  _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_8);
                  out.write("\n\t\t\t\t\t\t\t\t\t\t");
                  //  portlet:param
                  com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_9 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                  _jspx_th_portlet_param_9.setPageContext(_jspx_page_context);
                  _jspx_th_portlet_param_9.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_0);
                  _jspx_th_portlet_param_9.setName("parentCategoryId");
                  _jspx_th_portlet_param_9.setValue( String.valueOf(categoryId) );
                  int _jspx_eval_portlet_param_9 = _jspx_th_portlet_param_9.doStartTag();
                  if (_jspx_th_portlet_param_9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_9);
                    return;
                  }
                  _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_9);
                  out.write("\n\t\t\t\t\t\t\t\t\t");
                }
                if (_jspx_th_portlet_renderURL_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_portlet_renderURL_var.reuse(_jspx_th_portlet_renderURL_0);
                  return;
                }
                _jspx_tagPool_portlet_renderURL_var.reuse(_jspx_th_portlet_renderURL_0);
                java.lang.String editCategoryURL = null;
                editCategoryURL = (java.lang.String) _jspx_page_context.findAttribute("editCategoryURL");
                out.write("\n\n\t\t\t\t\t\t\t\t\t<div class=\"btn-group-item\">\n\t\t\t\t\t\t\t\t\t\t");
                //  clay:link
                com.liferay.frontend.taglib.clay.servlet.taglib.soy.LinkTag _jspx_th_clay_link_0 = (com.liferay.frontend.taglib.clay.servlet.taglib.soy.LinkTag) _jspx_tagPool_clay_link_label_href_elementClasses_buttonStyle_nobody.get(com.liferay.frontend.taglib.clay.servlet.taglib.soy.LinkTag.class);
                _jspx_th_clay_link_0.setPageContext(_jspx_page_context);
                _jspx_th_clay_link_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_12);
                _jspx_th_clay_link_0.setButtonStyle("secondary");
                _jspx_th_clay_link_0.setElementClasses("btn-sm");
                _jspx_th_clay_link_0.setHref( editCategoryURL );
                _jspx_th_clay_link_0.setLabel( LanguageUtil.get(request, "add-category[message-board]") );
                int _jspx_eval_clay_link_0 = _jspx_th_clay_link_0.doStartTag();
                if (_jspx_th_clay_link_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_clay_link_label_href_elementClasses_buttonStyle_nobody.reuse(_jspx_th_clay_link_0);
                  return;
                }
                _jspx_tagPool_clay_link_label_href_elementClasses_buttonStyle_nobody.reuse(_jspx_th_clay_link_0);
                out.write("\n\t\t\t\t\t\t\t\t\t</div>\n\t\t\t\t\t\t\t\t");
              }
              if (_jspx_th_c_if_12.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_12);
                return;
              }
              _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_12);
              out.write("\n\n\t\t\t\t\t\t\t\t");
              //  c:if
              com.liferay.taglib.core.IfTag _jspx_th_c_if_13 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
              _jspx_th_c_if_13.setPageContext(_jspx_page_context);
              _jspx_th_c_if_13.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_3);
              _jspx_th_c_if_13.setTest( showAddMessageButton );
              int _jspx_eval_c_if_13 = _jspx_th_c_if_13.doStartTag();
              if (_jspx_eval_c_if_13 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\t\t\t\t\t\t\t\t\t");
                //  portlet:renderURL
                com.liferay.taglib.portlet.RenderURLTag _jspx_th_portlet_renderURL_1 = (com.liferay.taglib.portlet.RenderURLTag) _jspx_tagPool_portlet_renderURL_var.get(com.liferay.taglib.portlet.RenderURLTag.class);
                _jspx_th_portlet_renderURL_1.setPageContext(_jspx_page_context);
                _jspx_th_portlet_renderURL_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_13);
                _jspx_th_portlet_renderURL_1.setVar("editMessageURL");
                int _jspx_eval_portlet_renderURL_1 = _jspx_th_portlet_renderURL_1.doStartTag();
                if (_jspx_eval_portlet_renderURL_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  out.write("\n\t\t\t\t\t\t\t\t\t\t");
                  if (_jspx_meth_portlet_param_10((javax.servlet.jsp.tagext.JspTag) _jspx_th_portlet_renderURL_1, _jspx_page_context))
                    return;
                  out.write("\n\t\t\t\t\t\t\t\t\t\t");
                  //  portlet:param
                  com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_11 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                  _jspx_th_portlet_param_11.setPageContext(_jspx_page_context);
                  _jspx_th_portlet_param_11.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_1);
                  _jspx_th_portlet_param_11.setName("redirect");
                  _jspx_th_portlet_param_11.setValue( currentURL );
                  int _jspx_eval_portlet_param_11 = _jspx_th_portlet_param_11.doStartTag();
                  if (_jspx_th_portlet_param_11.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_11);
                    return;
                  }
                  _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_11);
                  out.write("\n\t\t\t\t\t\t\t\t\t\t");
                  //  portlet:param
                  com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_12 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                  _jspx_th_portlet_param_12.setPageContext(_jspx_page_context);
                  _jspx_th_portlet_param_12.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_1);
                  _jspx_th_portlet_param_12.setName("mbCategoryId");
                  _jspx_th_portlet_param_12.setValue( String.valueOf(categoryId) );
                  int _jspx_eval_portlet_param_12 = _jspx_th_portlet_param_12.doStartTag();
                  if (_jspx_th_portlet_param_12.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_12);
                    return;
                  }
                  _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_12);
                  out.write("\n\t\t\t\t\t\t\t\t\t");
                }
                if (_jspx_th_portlet_renderURL_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_portlet_renderURL_var.reuse(_jspx_th_portlet_renderURL_1);
                  return;
                }
                _jspx_tagPool_portlet_renderURL_var.reuse(_jspx_th_portlet_renderURL_1);
                java.lang.String editMessageURL = null;
                editMessageURL = (java.lang.String) _jspx_page_context.findAttribute("editMessageURL");
                out.write("\n\n\t\t\t\t\t\t\t\t\t<div class=\"btn-group-item\">\n\t\t\t\t\t\t\t\t\t\t");
                //  clay:link
                com.liferay.frontend.taglib.clay.servlet.taglib.soy.LinkTag _jspx_th_clay_link_1 = (com.liferay.frontend.taglib.clay.servlet.taglib.soy.LinkTag) _jspx_tagPool_clay_link_label_href_elementClasses_buttonStyle_nobody.get(com.liferay.frontend.taglib.clay.servlet.taglib.soy.LinkTag.class);
                _jspx_th_clay_link_1.setPageContext(_jspx_page_context);
                _jspx_th_clay_link_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_13);
                _jspx_th_clay_link_1.setButtonStyle("primary");
                _jspx_th_clay_link_1.setElementClasses("btn-sm");
                _jspx_th_clay_link_1.setHref( editMessageURL );
                _jspx_th_clay_link_1.setLabel( LanguageUtil.get(request, "new-thread") );
                int _jspx_eval_clay_link_1 = _jspx_th_clay_link_1.doStartTag();
                if (_jspx_th_clay_link_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_clay_link_label_href_elementClasses_buttonStyle_nobody.reuse(_jspx_th_clay_link_1);
                  return;
                }
                _jspx_tagPool_clay_link_label_href_elementClasses_buttonStyle_nobody.reuse(_jspx_th_clay_link_1);
                out.write("\n\t\t\t\t\t\t\t\t\t</div>\n\t\t\t\t\t\t\t\t");
              }
              if (_jspx_th_c_if_13.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_13);
                return;
              }
              _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_13);
              out.write("\n\n\t\t\t\t\t\t\t\t");
              //  liferay-ui:icon-menu
              com.liferay.taglib.ui.IconMenuTag _jspx_th_liferay$1ui_icon$1menu_0 = (com.liferay.taglib.ui.IconMenuTag) _jspx_tagPool_liferay$1ui_icon$1menu_showWhenSingleIcon_message_markupView_icon_direction.get(com.liferay.taglib.ui.IconMenuTag.class);
              _jspx_th_liferay$1ui_icon$1menu_0.setPageContext(_jspx_page_context);
              _jspx_th_liferay$1ui_icon$1menu_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_3);
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
                  out.write("\n\t\t\t\t\t\t\t\t\t");
                  //  c:if
                  com.liferay.taglib.core.IfTag _jspx_th_c_if_14 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                  _jspx_th_c_if_14.setPageContext(_jspx_page_context);
                  _jspx_th_c_if_14.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_icon$1menu_0);
                  _jspx_th_c_if_14.setTest( showPermissionsButton );
                  int _jspx_eval_c_if_14 = _jspx_th_c_if_14.doStartTag();
                  if (_jspx_eval_c_if_14 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    out.write("\n\n\t\t\t\t\t\t\t\t\t\t");

										String modelResource = "com.liferay.message.boards";
										String modelResourceDescription = themeDisplay.getScopeGroupName();
										String resourcePrimKey = String.valueOf(scopeGroupId);

										if (category != null) {
											modelResource = MBCategory.class.getName();
											modelResourceDescription = category.getName();
											resourcePrimKey = String.valueOf(category.getCategoryId());
										}
										
                    out.write("\n\n\t\t\t\t\t\t\t\t\t\t");
                    //  liferay-security:permissionsURL
                    com.liferay.taglib.security.PermissionsURLTag _jspx_th_liferay$1security_permissionsURL_0 = (com.liferay.taglib.security.PermissionsURLTag) _jspx_tagPool_liferay$1security_permissionsURL_windowState_var_resourcePrimKey_modelResourceDescription_modelResource_nobody.get(com.liferay.taglib.security.PermissionsURLTag.class);
                    _jspx_th_liferay$1security_permissionsURL_0.setPageContext(_jspx_page_context);
                    _jspx_th_liferay$1security_permissionsURL_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_14);
                    _jspx_th_liferay$1security_permissionsURL_0.setModelResource( modelResource );
                    _jspx_th_liferay$1security_permissionsURL_0.setModelResourceDescription( HtmlUtil.escape(modelResourceDescription) );
                    _jspx_th_liferay$1security_permissionsURL_0.setResourcePrimKey( resourcePrimKey );
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
                    out.write("\n\n\t\t\t\t\t\t\t\t\t\t");
                    //  liferay-ui:icon
                    com.liferay.taglib.ui.IconTag _jspx_th_liferay$1ui_icon_0 = (com.liferay.taglib.ui.IconTag) _jspx_tagPool_liferay$1ui_icon_useDialog_url_method_message_label_nobody.get(com.liferay.taglib.ui.IconTag.class);
                    _jspx_th_liferay$1ui_icon_0.setPageContext(_jspx_page_context);
                    _jspx_th_liferay$1ui_icon_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_14);
                    _jspx_th_liferay$1ui_icon_0.setLabel( true );
                    _jspx_th_liferay$1ui_icon_0.setMessage("permissions");
                    _jspx_th_liferay$1ui_icon_0.setMethod("get");
                    _jspx_th_liferay$1ui_icon_0.setUrl( permissionsURL );
                    _jspx_th_liferay$1ui_icon_0.setUseDialog( true );
                    int _jspx_eval_liferay$1ui_icon_0 = _jspx_th_liferay$1ui_icon_0.doStartTag();
                    if (_jspx_th_liferay$1ui_icon_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_liferay$1ui_icon_useDialog_url_method_message_label_nobody.reuse(_jspx_th_liferay$1ui_icon_0);
                      return;
                    }
                    _jspx_tagPool_liferay$1ui_icon_useDialog_url_method_message_label_nobody.reuse(_jspx_th_liferay$1ui_icon_0);
                    out.write("\n\t\t\t\t\t\t\t\t\t");
                  }
                  if (_jspx_th_c_if_14.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_14);
                    return;
                  }
                  _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_14);
                  out.write("\n\n\t\t\t\t\t\t\t\t\t");
                  //  c:choose
                  com.liferay.taglib.core.ChooseTag _jspx_th_c_choose_3 = (com.liferay.taglib.core.ChooseTag) _jspx_tagPool_c_choose.get(com.liferay.taglib.core.ChooseTag.class);
                  _jspx_th_c_choose_3.setPageContext(_jspx_page_context);
                  _jspx_th_c_choose_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_icon$1menu_0);
                  int _jspx_eval_c_choose_3 = _jspx_th_c_choose_3.doStartTag();
                  if (_jspx_eval_c_choose_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    out.write("\n\t\t\t\t\t\t\t\t\t\t");
                    //  c:when
                    com.liferay.taglib.core.WhenTag _jspx_th_c_when_5 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
                    _jspx_th_c_when_5.setPageContext(_jspx_page_context);
                    _jspx_th_c_when_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_3);
                    _jspx_th_c_when_5.setTest( category == null );
                    int _jspx_eval_c_when_5 = _jspx_th_c_when_5.doStartTag();
                    if (_jspx_eval_c_when_5 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      out.write("\n\t\t\t\t\t\t\t\t\t\t\t");
                      //  c:if
                      com.liferay.taglib.core.IfTag _jspx_th_c_if_15 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                      _jspx_th_c_if_15.setPageContext(_jspx_page_context);
                      _jspx_th_c_if_15.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_5);
                      _jspx_th_c_if_15.setTest( MBResourcePermission.contains(permissionChecker, scopeGroupId, ActionKeys.SUBSCRIBE) && (mbGroupServiceSettings.isEmailMessageAddedEnabled() || mbGroupServiceSettings.isEmailMessageUpdatedEnabled()) );
                      int _jspx_eval_c_if_15 = _jspx_th_c_if_15.doStartTag();
                      if (_jspx_eval_c_if_15 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                        out.write("\n\t\t\t\t\t\t\t\t\t\t\t\t");
                        //  c:choose
                        com.liferay.taglib.core.ChooseTag _jspx_th_c_choose_4 = (com.liferay.taglib.core.ChooseTag) _jspx_tagPool_c_choose.get(com.liferay.taglib.core.ChooseTag.class);
                        _jspx_th_c_choose_4.setPageContext(_jspx_page_context);
                        _jspx_th_c_choose_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_15);
                        int _jspx_eval_c_choose_4 = _jspx_th_c_choose_4.doStartTag();
                        if (_jspx_eval_c_choose_4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                          out.write("\n\t\t\t\t\t\t\t\t\t\t\t\t\t");
                          //  c:when
                          com.liferay.taglib.core.WhenTag _jspx_th_c_when_6 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
                          _jspx_th_c_when_6.setPageContext(_jspx_page_context);
                          _jspx_th_c_when_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_4);
                          _jspx_th_c_when_6.setTest( SubscriptionLocalServiceUtil.isSubscribed(user.getCompanyId(), user.getUserId(), MBCategory.class.getName(), scopeGroupId) );
                          int _jspx_eval_c_when_6 = _jspx_th_c_when_6.doStartTag();
                          if (_jspx_eval_c_when_6 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                            out.write("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
                            //  portlet:actionURL
                            com.liferay.taglib.portlet.ActionURLTag _jspx_th_portlet_actionURL_1 = (com.liferay.taglib.portlet.ActionURLTag) _jspx_tagPool_portlet_actionURL_var_name.get(com.liferay.taglib.portlet.ActionURLTag.class);
                            _jspx_th_portlet_actionURL_1.setPageContext(_jspx_page_context);
                            _jspx_th_portlet_actionURL_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_6);
                            _jspx_th_portlet_actionURL_1.setName("/message_boards/edit_category");
                            _jspx_th_portlet_actionURL_1.setVar("unsubscribeURL");
                            int _jspx_eval_portlet_actionURL_1 = _jspx_th_portlet_actionURL_1.doStartTag();
                            if (_jspx_eval_portlet_actionURL_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                              out.write("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
                              //  portlet:param
                              com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_13 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                              _jspx_th_portlet_param_13.setPageContext(_jspx_page_context);
                              _jspx_th_portlet_param_13.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_actionURL_1);
                              _jspx_th_portlet_param_13.setName( Constants.CMD );
                              _jspx_th_portlet_param_13.setValue( Constants.UNSUBSCRIBE );
                              int _jspx_eval_portlet_param_13 = _jspx_th_portlet_param_13.doStartTag();
                              if (_jspx_th_portlet_param_13.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_13);
                              return;
                              }
                              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_13);
                              out.write("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
                              //  portlet:param
                              com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_14 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                              _jspx_th_portlet_param_14.setPageContext(_jspx_page_context);
                              _jspx_th_portlet_param_14.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_actionURL_1);
                              _jspx_th_portlet_param_14.setName("redirect");
                              _jspx_th_portlet_param_14.setValue( currentURL );
                              int _jspx_eval_portlet_param_14 = _jspx_th_portlet_param_14.doStartTag();
                              if (_jspx_th_portlet_param_14.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_14);
                              return;
                              }
                              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_14);
                              out.write("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
                              //  portlet:param
                              com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_15 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                              _jspx_th_portlet_param_15.setPageContext(_jspx_page_context);
                              _jspx_th_portlet_param_15.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_actionURL_1);
                              _jspx_th_portlet_param_15.setName("mbCategoryId");
                              _jspx_th_portlet_param_15.setValue( String.valueOf(MBCategoryConstants.DEFAULT_PARENT_CATEGORY_ID) );
                              int _jspx_eval_portlet_param_15 = _jspx_th_portlet_param_15.doStartTag();
                              if (_jspx_th_portlet_param_15.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_15);
                              return;
                              }
                              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_15);
                              out.write("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
                            }
                            if (_jspx_th_portlet_actionURL_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_portlet_actionURL_var_name.reuse(_jspx_th_portlet_actionURL_1);
                              return;
                            }
                            _jspx_tagPool_portlet_actionURL_var_name.reuse(_jspx_th_portlet_actionURL_1);
                            java.lang.String unsubscribeURL = null;
                            unsubscribeURL = (java.lang.String) _jspx_page_context.findAttribute("unsubscribeURL");
                            out.write("\n\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
                            //  liferay-ui:icon
                            com.liferay.taglib.ui.IconTag _jspx_th_liferay$1ui_icon_1 = (com.liferay.taglib.ui.IconTag) _jspx_tagPool_liferay$1ui_icon_url_message_label_nobody.get(com.liferay.taglib.ui.IconTag.class);
                            _jspx_th_liferay$1ui_icon_1.setPageContext(_jspx_page_context);
                            _jspx_th_liferay$1ui_icon_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_6);
                            _jspx_th_liferay$1ui_icon_1.setLabel( true );
                            _jspx_th_liferay$1ui_icon_1.setMessage("unsubscribe");
                            _jspx_th_liferay$1ui_icon_1.setUrl( unsubscribeURL );
                            int _jspx_eval_liferay$1ui_icon_1 = _jspx_th_liferay$1ui_icon_1.doStartTag();
                            if (_jspx_th_liferay$1ui_icon_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_liferay$1ui_icon_url_message_label_nobody.reuse(_jspx_th_liferay$1ui_icon_1);
                              return;
                            }
                            _jspx_tagPool_liferay$1ui_icon_url_message_label_nobody.reuse(_jspx_th_liferay$1ui_icon_1);
                            out.write("\n\t\t\t\t\t\t\t\t\t\t\t\t\t");
                          }
                          if (_jspx_th_c_when_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                            _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_6);
                            return;
                          }
                          _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_6);
                          out.write("\n\t\t\t\t\t\t\t\t\t\t\t\t\t");
                          //  c:otherwise
                          com.liferay.taglib.core.OtherwiseTag _jspx_th_c_otherwise_1 = (com.liferay.taglib.core.OtherwiseTag) _jspx_tagPool_c_otherwise.get(com.liferay.taglib.core.OtherwiseTag.class);
                          _jspx_th_c_otherwise_1.setPageContext(_jspx_page_context);
                          _jspx_th_c_otherwise_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_4);
                          int _jspx_eval_c_otherwise_1 = _jspx_th_c_otherwise_1.doStartTag();
                          if (_jspx_eval_c_otherwise_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                            out.write("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
                            //  portlet:actionURL
                            com.liferay.taglib.portlet.ActionURLTag _jspx_th_portlet_actionURL_2 = (com.liferay.taglib.portlet.ActionURLTag) _jspx_tagPool_portlet_actionURL_var_name.get(com.liferay.taglib.portlet.ActionURLTag.class);
                            _jspx_th_portlet_actionURL_2.setPageContext(_jspx_page_context);
                            _jspx_th_portlet_actionURL_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_1);
                            _jspx_th_portlet_actionURL_2.setName("/message_boards/edit_category");
                            _jspx_th_portlet_actionURL_2.setVar("subscribeURL");
                            int _jspx_eval_portlet_actionURL_2 = _jspx_th_portlet_actionURL_2.doStartTag();
                            if (_jspx_eval_portlet_actionURL_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                              out.write("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
                              //  portlet:param
                              com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_16 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                              _jspx_th_portlet_param_16.setPageContext(_jspx_page_context);
                              _jspx_th_portlet_param_16.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_actionURL_2);
                              _jspx_th_portlet_param_16.setName( Constants.CMD );
                              _jspx_th_portlet_param_16.setValue( Constants.SUBSCRIBE );
                              int _jspx_eval_portlet_param_16 = _jspx_th_portlet_param_16.doStartTag();
                              if (_jspx_th_portlet_param_16.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_16);
                              return;
                              }
                              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_16);
                              out.write("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
                              //  portlet:param
                              com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_17 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                              _jspx_th_portlet_param_17.setPageContext(_jspx_page_context);
                              _jspx_th_portlet_param_17.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_actionURL_2);
                              _jspx_th_portlet_param_17.setName("redirect");
                              _jspx_th_portlet_param_17.setValue( currentURL );
                              int _jspx_eval_portlet_param_17 = _jspx_th_portlet_param_17.doStartTag();
                              if (_jspx_th_portlet_param_17.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_17);
                              return;
                              }
                              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_17);
                              out.write("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
                              //  portlet:param
                              com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_18 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                              _jspx_th_portlet_param_18.setPageContext(_jspx_page_context);
                              _jspx_th_portlet_param_18.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_actionURL_2);
                              _jspx_th_portlet_param_18.setName("mbCategoryId");
                              _jspx_th_portlet_param_18.setValue( String.valueOf(MBCategoryConstants.DEFAULT_PARENT_CATEGORY_ID) );
                              int _jspx_eval_portlet_param_18 = _jspx_th_portlet_param_18.doStartTag();
                              if (_jspx_th_portlet_param_18.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_18);
                              return;
                              }
                              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_18);
                              out.write("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
                            }
                            if (_jspx_th_portlet_actionURL_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_portlet_actionURL_var_name.reuse(_jspx_th_portlet_actionURL_2);
                              return;
                            }
                            _jspx_tagPool_portlet_actionURL_var_name.reuse(_jspx_th_portlet_actionURL_2);
                            java.lang.String subscribeURL = null;
                            subscribeURL = (java.lang.String) _jspx_page_context.findAttribute("subscribeURL");
                            out.write("\n\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
                            //  liferay-ui:icon
                            com.liferay.taglib.ui.IconTag _jspx_th_liferay$1ui_icon_2 = (com.liferay.taglib.ui.IconTag) _jspx_tagPool_liferay$1ui_icon_url_message_label_nobody.get(com.liferay.taglib.ui.IconTag.class);
                            _jspx_th_liferay$1ui_icon_2.setPageContext(_jspx_page_context);
                            _jspx_th_liferay$1ui_icon_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_1);
                            _jspx_th_liferay$1ui_icon_2.setLabel( true );
                            _jspx_th_liferay$1ui_icon_2.setMessage("subscribe");
                            _jspx_th_liferay$1ui_icon_2.setUrl( subscribeURL );
                            int _jspx_eval_liferay$1ui_icon_2 = _jspx_th_liferay$1ui_icon_2.doStartTag();
                            if (_jspx_th_liferay$1ui_icon_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_liferay$1ui_icon_url_message_label_nobody.reuse(_jspx_th_liferay$1ui_icon_2);
                              return;
                            }
                            _jspx_tagPool_liferay$1ui_icon_url_message_label_nobody.reuse(_jspx_th_liferay$1ui_icon_2);
                            out.write("\n\t\t\t\t\t\t\t\t\t\t\t\t\t");
                          }
                          if (_jspx_th_c_otherwise_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                            _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_1);
                            return;
                          }
                          _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_1);
                          out.write("\n\t\t\t\t\t\t\t\t\t\t\t\t");
                        }
                        if (_jspx_th_c_choose_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                          _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_4);
                          return;
                        }
                        _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_4);
                        out.write("\n\t\t\t\t\t\t\t\t\t\t\t");
                      }
                      if (_jspx_th_c_if_15.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_15);
                        return;
                      }
                      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_15);
                      out.write("\n\n\t\t\t\t\t\t\t\t\t\t\t");
                      //  c:if
                      com.liferay.taglib.core.IfTag _jspx_th_c_if_16 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                      _jspx_th_c_if_16.setPageContext(_jspx_page_context);
                      _jspx_th_c_if_16.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_5);
                      _jspx_th_c_if_16.setTest( enableRSS );
                      int _jspx_eval_c_if_16 = _jspx_th_c_if_16.doStartTag();
                      if (_jspx_eval_c_if_16 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                        out.write("\n\t\t\t\t\t\t\t\t\t\t\t\t");
                        //  liferay-rss:rss
                        com.liferay.rss.taglib.servlet.taglib.RSSTag _jspx_th_liferay$1rss_rss_0 = (com.liferay.rss.taglib.servlet.taglib.RSSTag) _jspx_tagPool_liferay$1rss_rss_url_feedType_displayStyle_delta_nobody.get(com.liferay.rss.taglib.servlet.taglib.RSSTag.class);
                        _jspx_th_liferay$1rss_rss_0.setPageContext(_jspx_page_context);
                        _jspx_th_liferay$1rss_rss_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_16);
                        _jspx_th_liferay$1rss_rss_0.setDelta( rssDelta );
                        _jspx_th_liferay$1rss_rss_0.setDisplayStyle( rssDisplayStyle );
                        _jspx_th_liferay$1rss_rss_0.setFeedType( rssFeedType );
                        _jspx_th_liferay$1rss_rss_0.setUrl( MBRSSUtil.getRSSURL(plid, 0, 0, 0, themeDisplay) );
                        int _jspx_eval_liferay$1rss_rss_0 = _jspx_th_liferay$1rss_rss_0.doStartTag();
                        if (_jspx_th_liferay$1rss_rss_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                          _jspx_tagPool_liferay$1rss_rss_url_feedType_displayStyle_delta_nobody.reuse(_jspx_th_liferay$1rss_rss_0);
                          return;
                        }
                        _jspx_tagPool_liferay$1rss_rss_url_feedType_displayStyle_delta_nobody.reuse(_jspx_th_liferay$1rss_rss_0);
                        out.write("\n\t\t\t\t\t\t\t\t\t\t\t");
                      }
                      if (_jspx_th_c_if_16.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_16);
                        return;
                      }
                      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_16);
                      out.write("\n\t\t\t\t\t\t\t\t\t\t");
                    }
                    if (_jspx_th_c_when_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_5);
                      return;
                    }
                    _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_5);
                    out.write("\n\t\t\t\t\t\t\t\t\t\t");
                    //  c:otherwise
                    com.liferay.taglib.core.OtherwiseTag _jspx_th_c_otherwise_2 = (com.liferay.taglib.core.OtherwiseTag) _jspx_tagPool_c_otherwise.get(com.liferay.taglib.core.OtherwiseTag.class);
                    _jspx_th_c_otherwise_2.setPageContext(_jspx_page_context);
                    _jspx_th_c_otherwise_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_3);
                    int _jspx_eval_c_otherwise_2 = _jspx_th_c_otherwise_2.doStartTag();
                    if (_jspx_eval_c_otherwise_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      out.write("\n\t\t\t\t\t\t\t\t\t\t\t");
                      //  c:if
                      com.liferay.taglib.core.IfTag _jspx_th_c_if_17 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                      _jspx_th_c_if_17.setPageContext(_jspx_page_context);
                      _jspx_th_c_if_17.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_2);
                      _jspx_th_c_if_17.setTest( enableRSS );
                      int _jspx_eval_c_if_17 = _jspx_th_c_if_17.doStartTag();
                      if (_jspx_eval_c_if_17 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                        out.write("\n\t\t\t\t\t\t\t\t\t\t\t\t");
                        //  liferay-rss:rss
                        com.liferay.rss.taglib.servlet.taglib.RSSTag _jspx_th_liferay$1rss_rss_1 = (com.liferay.rss.taglib.servlet.taglib.RSSTag) _jspx_tagPool_liferay$1rss_rss_url_feedType_displayStyle_delta_nobody.get(com.liferay.rss.taglib.servlet.taglib.RSSTag.class);
                        _jspx_th_liferay$1rss_rss_1.setPageContext(_jspx_page_context);
                        _jspx_th_liferay$1rss_rss_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_17);
                        _jspx_th_liferay$1rss_rss_1.setDelta( rssDelta );
                        _jspx_th_liferay$1rss_rss_1.setDisplayStyle( rssDisplayStyle );
                        _jspx_th_liferay$1rss_rss_1.setFeedType( rssFeedType );
                        _jspx_th_liferay$1rss_rss_1.setUrl( MBRSSUtil.getRSSURL(plid, category.getCategoryId(), 0, 0, themeDisplay) );
                        int _jspx_eval_liferay$1rss_rss_1 = _jspx_th_liferay$1rss_rss_1.doStartTag();
                        if (_jspx_th_liferay$1rss_rss_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                          _jspx_tagPool_liferay$1rss_rss_url_feedType_displayStyle_delta_nobody.reuse(_jspx_th_liferay$1rss_rss_1);
                          return;
                        }
                        _jspx_tagPool_liferay$1rss_rss_url_feedType_displayStyle_delta_nobody.reuse(_jspx_th_liferay$1rss_rss_1);
                        out.write("\n\t\t\t\t\t\t\t\t\t\t\t");
                      }
                      if (_jspx_th_c_if_17.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_17);
                        return;
                      }
                      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_17);
                      out.write("\n\n\t\t\t\t\t\t\t\t\t\t\t");
                      //  c:if
                      com.liferay.taglib.core.IfTag _jspx_th_c_if_18 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                      _jspx_th_c_if_18.setPageContext(_jspx_page_context);
                      _jspx_th_c_if_18.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_2);
                      _jspx_th_c_if_18.setTest( MBCategoryPermission.contains(permissionChecker, category, ActionKeys.SUBSCRIBE) && (mbGroupServiceSettings.isEmailMessageAddedEnabled() || mbGroupServiceSettings.isEmailMessageUpdatedEnabled()) );
                      int _jspx_eval_c_if_18 = _jspx_th_c_if_18.doStartTag();
                      if (_jspx_eval_c_if_18 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                        out.write("\n\t\t\t\t\t\t\t\t\t\t\t\t");
                        //  c:choose
                        com.liferay.taglib.core.ChooseTag _jspx_th_c_choose_5 = (com.liferay.taglib.core.ChooseTag) _jspx_tagPool_c_choose.get(com.liferay.taglib.core.ChooseTag.class);
                        _jspx_th_c_choose_5.setPageContext(_jspx_page_context);
                        _jspx_th_c_choose_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_18);
                        int _jspx_eval_c_choose_5 = _jspx_th_c_choose_5.doStartTag();
                        if (_jspx_eval_c_choose_5 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                          out.write("\n\t\t\t\t\t\t\t\t\t\t\t\t\t");
                          //  c:when
                          com.liferay.taglib.core.WhenTag _jspx_th_c_when_7 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
                          _jspx_th_c_when_7.setPageContext(_jspx_page_context);
                          _jspx_th_c_when_7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_5);
                          _jspx_th_c_when_7.setTest( (categorySubscriptionClassPKs != null) && categorySubscriptionClassPKs.contains(category.getCategoryId()) );
                          int _jspx_eval_c_when_7 = _jspx_th_c_when_7.doStartTag();
                          if (_jspx_eval_c_when_7 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                            out.write("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
                            //  portlet:actionURL
                            com.liferay.taglib.portlet.ActionURLTag _jspx_th_portlet_actionURL_3 = (com.liferay.taglib.portlet.ActionURLTag) _jspx_tagPool_portlet_actionURL_var_name.get(com.liferay.taglib.portlet.ActionURLTag.class);
                            _jspx_th_portlet_actionURL_3.setPageContext(_jspx_page_context);
                            _jspx_th_portlet_actionURL_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_7);
                            _jspx_th_portlet_actionURL_3.setName("/message_boards/edit_category");
                            _jspx_th_portlet_actionURL_3.setVar("unsubscribeURL");
                            int _jspx_eval_portlet_actionURL_3 = _jspx_th_portlet_actionURL_3.doStartTag();
                            if (_jspx_eval_portlet_actionURL_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                              out.write("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
                              //  portlet:param
                              com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_19 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                              _jspx_th_portlet_param_19.setPageContext(_jspx_page_context);
                              _jspx_th_portlet_param_19.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_actionURL_3);
                              _jspx_th_portlet_param_19.setName( Constants.CMD );
                              _jspx_th_portlet_param_19.setValue( Constants.UNSUBSCRIBE );
                              int _jspx_eval_portlet_param_19 = _jspx_th_portlet_param_19.doStartTag();
                              if (_jspx_th_portlet_param_19.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_19);
                              return;
                              }
                              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_19);
                              out.write("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
                              //  portlet:param
                              com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_20 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                              _jspx_th_portlet_param_20.setPageContext(_jspx_page_context);
                              _jspx_th_portlet_param_20.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_actionURL_3);
                              _jspx_th_portlet_param_20.setName("redirect");
                              _jspx_th_portlet_param_20.setValue( currentURL );
                              int _jspx_eval_portlet_param_20 = _jspx_th_portlet_param_20.doStartTag();
                              if (_jspx_th_portlet_param_20.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_20);
                              return;
                              }
                              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_20);
                              out.write("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
                              //  portlet:param
                              com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_21 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                              _jspx_th_portlet_param_21.setPageContext(_jspx_page_context);
                              _jspx_th_portlet_param_21.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_actionURL_3);
                              _jspx_th_portlet_param_21.setName("mbCategoryId");
                              _jspx_th_portlet_param_21.setValue( String.valueOf(category.getCategoryId()) );
                              int _jspx_eval_portlet_param_21 = _jspx_th_portlet_param_21.doStartTag();
                              if (_jspx_th_portlet_param_21.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_21);
                              return;
                              }
                              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_21);
                              out.write("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
                            }
                            if (_jspx_th_portlet_actionURL_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_portlet_actionURL_var_name.reuse(_jspx_th_portlet_actionURL_3);
                              return;
                            }
                            _jspx_tagPool_portlet_actionURL_var_name.reuse(_jspx_th_portlet_actionURL_3);
                            java.lang.String unsubscribeURL = null;
                            unsubscribeURL = (java.lang.String) _jspx_page_context.findAttribute("unsubscribeURL");
                            out.write("\n\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
                            //  liferay-ui:icon
                            com.liferay.taglib.ui.IconTag _jspx_th_liferay$1ui_icon_3 = (com.liferay.taglib.ui.IconTag) _jspx_tagPool_liferay$1ui_icon_url_message_label_nobody.get(com.liferay.taglib.ui.IconTag.class);
                            _jspx_th_liferay$1ui_icon_3.setPageContext(_jspx_page_context);
                            _jspx_th_liferay$1ui_icon_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_7);
                            _jspx_th_liferay$1ui_icon_3.setLabel( true );
                            _jspx_th_liferay$1ui_icon_3.setMessage("unsubscribe");
                            _jspx_th_liferay$1ui_icon_3.setUrl( unsubscribeURL );
                            int _jspx_eval_liferay$1ui_icon_3 = _jspx_th_liferay$1ui_icon_3.doStartTag();
                            if (_jspx_th_liferay$1ui_icon_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_liferay$1ui_icon_url_message_label_nobody.reuse(_jspx_th_liferay$1ui_icon_3);
                              return;
                            }
                            _jspx_tagPool_liferay$1ui_icon_url_message_label_nobody.reuse(_jspx_th_liferay$1ui_icon_3);
                            out.write("\n\t\t\t\t\t\t\t\t\t\t\t\t\t");
                          }
                          if (_jspx_th_c_when_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                            _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_7);
                            return;
                          }
                          _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_7);
                          out.write("\n\t\t\t\t\t\t\t\t\t\t\t\t\t");
                          //  c:otherwise
                          com.liferay.taglib.core.OtherwiseTag _jspx_th_c_otherwise_3 = (com.liferay.taglib.core.OtherwiseTag) _jspx_tagPool_c_otherwise.get(com.liferay.taglib.core.OtherwiseTag.class);
                          _jspx_th_c_otherwise_3.setPageContext(_jspx_page_context);
                          _jspx_th_c_otherwise_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_5);
                          int _jspx_eval_c_otherwise_3 = _jspx_th_c_otherwise_3.doStartTag();
                          if (_jspx_eval_c_otherwise_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                            out.write("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
                            //  portlet:actionURL
                            com.liferay.taglib.portlet.ActionURLTag _jspx_th_portlet_actionURL_4 = (com.liferay.taglib.portlet.ActionURLTag) _jspx_tagPool_portlet_actionURL_var_name.get(com.liferay.taglib.portlet.ActionURLTag.class);
                            _jspx_th_portlet_actionURL_4.setPageContext(_jspx_page_context);
                            _jspx_th_portlet_actionURL_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_3);
                            _jspx_th_portlet_actionURL_4.setName("/message_boards/edit_category");
                            _jspx_th_portlet_actionURL_4.setVar("subscribeURL");
                            int _jspx_eval_portlet_actionURL_4 = _jspx_th_portlet_actionURL_4.doStartTag();
                            if (_jspx_eval_portlet_actionURL_4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                              out.write("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
                              //  portlet:param
                              com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_22 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                              _jspx_th_portlet_param_22.setPageContext(_jspx_page_context);
                              _jspx_th_portlet_param_22.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_actionURL_4);
                              _jspx_th_portlet_param_22.setName( Constants.CMD );
                              _jspx_th_portlet_param_22.setValue( Constants.SUBSCRIBE );
                              int _jspx_eval_portlet_param_22 = _jspx_th_portlet_param_22.doStartTag();
                              if (_jspx_th_portlet_param_22.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_22);
                              return;
                              }
                              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_22);
                              out.write("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
                              //  portlet:param
                              com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_23 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                              _jspx_th_portlet_param_23.setPageContext(_jspx_page_context);
                              _jspx_th_portlet_param_23.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_actionURL_4);
                              _jspx_th_portlet_param_23.setName("redirect");
                              _jspx_th_portlet_param_23.setValue( currentURL );
                              int _jspx_eval_portlet_param_23 = _jspx_th_portlet_param_23.doStartTag();
                              if (_jspx_th_portlet_param_23.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_23);
                              return;
                              }
                              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_23);
                              out.write("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
                              //  portlet:param
                              com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_24 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                              _jspx_th_portlet_param_24.setPageContext(_jspx_page_context);
                              _jspx_th_portlet_param_24.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_actionURL_4);
                              _jspx_th_portlet_param_24.setName("mbCategoryId");
                              _jspx_th_portlet_param_24.setValue( String.valueOf(category.getCategoryId()) );
                              int _jspx_eval_portlet_param_24 = _jspx_th_portlet_param_24.doStartTag();
                              if (_jspx_th_portlet_param_24.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_24);
                              return;
                              }
                              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_24);
                              out.write("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
                            }
                            if (_jspx_th_portlet_actionURL_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_portlet_actionURL_var_name.reuse(_jspx_th_portlet_actionURL_4);
                              return;
                            }
                            _jspx_tagPool_portlet_actionURL_var_name.reuse(_jspx_th_portlet_actionURL_4);
                            java.lang.String subscribeURL = null;
                            subscribeURL = (java.lang.String) _jspx_page_context.findAttribute("subscribeURL");
                            out.write("\n\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
                            //  liferay-ui:icon
                            com.liferay.taglib.ui.IconTag _jspx_th_liferay$1ui_icon_4 = (com.liferay.taglib.ui.IconTag) _jspx_tagPool_liferay$1ui_icon_url_message_label_nobody.get(com.liferay.taglib.ui.IconTag.class);
                            _jspx_th_liferay$1ui_icon_4.setPageContext(_jspx_page_context);
                            _jspx_th_liferay$1ui_icon_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_3);
                            _jspx_th_liferay$1ui_icon_4.setLabel( true );
                            _jspx_th_liferay$1ui_icon_4.setMessage("subscribe");
                            _jspx_th_liferay$1ui_icon_4.setUrl( subscribeURL );
                            int _jspx_eval_liferay$1ui_icon_4 = _jspx_th_liferay$1ui_icon_4.doStartTag();
                            if (_jspx_th_liferay$1ui_icon_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_liferay$1ui_icon_url_message_label_nobody.reuse(_jspx_th_liferay$1ui_icon_4);
                              return;
                            }
                            _jspx_tagPool_liferay$1ui_icon_url_message_label_nobody.reuse(_jspx_th_liferay$1ui_icon_4);
                            out.write("\n\t\t\t\t\t\t\t\t\t\t\t\t\t");
                          }
                          if (_jspx_th_c_otherwise_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                            _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_3);
                            return;
                          }
                          _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_3);
                          out.write("\n\t\t\t\t\t\t\t\t\t\t\t\t");
                        }
                        if (_jspx_th_c_choose_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                          _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_5);
                          return;
                        }
                        _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_5);
                        out.write("\n\t\t\t\t\t\t\t\t\t\t\t");
                      }
                      if (_jspx_th_c_if_18.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_18);
                        return;
                      }
                      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_18);
                      out.write("\n\t\t\t\t\t\t\t\t\t\t");
                    }
                    if (_jspx_th_c_otherwise_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_2);
                      return;
                    }
                    _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_2);
                    out.write("\n\t\t\t\t\t\t\t\t\t");
                  }
                  if (_jspx_th_c_choose_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_3);
                    return;
                  }
                  _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_3);
                  out.write("\n\t\t\t\t\t\t\t\t");
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
              out.write("\n\t\t\t\t\t\t\t</div>\n\t\t\t\t\t\t</div>\n\t\t\t\t\t</div>\n\n\t\t\t\t\t");

					SearchContainer categoryEntriesSearchContainer = new SearchContainer(renderRequest, null, null, "cur1", 0, mbListDisplayContext.getCategoryEntriesDelta(), portletURL, null, "there-are-no-threads-or-categories");

					mbListDisplayContext.setCategoryEntriesDelta(categoryEntriesSearchContainer);

					categoryEntriesSearchContainer.setOrderByCol(orderByCol);
					categoryEntriesSearchContainer.setOrderByComparator(orderByComparator);
					categoryEntriesSearchContainer.setOrderByType(orderByType);

					mbListDisplayContext.populateCategoriesResultsAndTotal(categoryEntriesSearchContainer);

					request.setAttribute("view.jsp-categoryEntriesSearchContainer", categoryEntriesSearchContainer);
					
              out.write("\n\n\t\t\t\t\t");
              //  c:if
              com.liferay.taglib.core.IfTag _jspx_th_c_if_19 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
              _jspx_th_c_if_19.setPageContext(_jspx_page_context);
              _jspx_th_c_if_19.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_3);
              _jspx_th_c_if_19.setTest( categoryEntriesSearchContainer.getTotal() > 0 );
              int _jspx_eval_c_if_19 = _jspx_th_c_if_19.doStartTag();
              if (_jspx_eval_c_if_19 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\t\t\t\t\t\t");
                //  liferay-util:include
                com.liferay.taglib.util.IncludeTag _jspx_th_liferay$1util_include_1 = (com.liferay.taglib.util.IncludeTag) _jspx_tagPool_liferay$1util_include_servletContext_page_nobody.get(com.liferay.taglib.util.IncludeTag.class);
                _jspx_th_liferay$1util_include_1.setPageContext(_jspx_page_context);
                _jspx_th_liferay$1util_include_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_19);
                _jspx_th_liferay$1util_include_1.setPage( "/message_boards/view_category_entries.jsp" );
                _jspx_th_liferay$1util_include_1.setServletContext( application );
                int _jspx_eval_liferay$1util_include_1 = _jspx_th_liferay$1util_include_1.doStartTag();
                if (_jspx_th_liferay$1util_include_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_liferay$1util_include_servletContext_page_nobody.reuse(_jspx_th_liferay$1util_include_1);
                  return;
                }
                _jspx_tagPool_liferay$1util_include_servletContext_page_nobody.reuse(_jspx_th_liferay$1util_include_1);
                out.write("\n\t\t\t\t\t");
              }
              if (_jspx_th_c_if_19.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_19);
                return;
              }
              _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_19);
              out.write("\n\n\t\t\t\t\t");

					SearchContainer threadEntriesSearchContainer = new SearchContainer(renderRequest, null, null, "cur2", 0, mbListDisplayContext.getThreadEntriesDelta(), portletURL, null, "there-are-no-threads-or-categories");

					mbListDisplayContext.setThreadEntriesDelta(categoryEntriesSearchContainer);

					threadEntriesSearchContainer.setOrderByCol(orderByCol);
					threadEntriesSearchContainer.setOrderByComparator(orderByComparator);
					threadEntriesSearchContainer.setOrderByType(orderByType);

					mbListDisplayContext.populateThreadsResultsAndTotal(threadEntriesSearchContainer);

					request.setAttribute("view.jsp-threadEntriesSearchContainer", threadEntriesSearchContainer);
					
              out.write("\n\n\t\t\t\t\t");
              //  c:if
              com.liferay.taglib.core.IfTag _jspx_th_c_if_20 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
              _jspx_th_c_if_20.setPageContext(_jspx_page_context);
              _jspx_th_c_if_20.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_3);
              _jspx_th_c_if_20.setTest( threadEntriesSearchContainer.getTotal() > 0 );
              int _jspx_eval_c_if_20 = _jspx_th_c_if_20.doStartTag();
              if (_jspx_eval_c_if_20 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\t\t\t\t\t\t");
                //  liferay-util:include
                com.liferay.taglib.util.IncludeTag _jspx_th_liferay$1util_include_2 = (com.liferay.taglib.util.IncludeTag) _jspx_tagPool_liferay$1util_include_servletContext_page_nobody.get(com.liferay.taglib.util.IncludeTag.class);
                _jspx_th_liferay$1util_include_2.setPageContext(_jspx_page_context);
                _jspx_th_liferay$1util_include_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_20);
                _jspx_th_liferay$1util_include_2.setPage( "/message_boards/view_thread_entries.jsp" );
                _jspx_th_liferay$1util_include_2.setServletContext( application );
                int _jspx_eval_liferay$1util_include_2 = _jspx_th_liferay$1util_include_2.doStartTag();
                if (_jspx_th_liferay$1util_include_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_liferay$1util_include_servletContext_page_nobody.reuse(_jspx_th_liferay$1util_include_2);
                  return;
                }
                _jspx_tagPool_liferay$1util_include_servletContext_page_nobody.reuse(_jspx_th_liferay$1util_include_2);
                out.write("\n\t\t\t\t\t");
              }
              if (_jspx_th_c_if_20.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_20);
                return;
              }
              _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_20);
              out.write("\n\n\t\t\t\t\t");
              //  c:if
              com.liferay.taglib.core.IfTag _jspx_th_c_if_21 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
              _jspx_th_c_if_21.setPageContext(_jspx_page_context);
              _jspx_th_c_if_21.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_3);
              _jspx_th_c_if_21.setTest( (categoryEntriesSearchContainer.getTotal() <= 0) && (threadEntriesSearchContainer.getTotal() <= 0) );
              int _jspx_eval_c_if_21 = _jspx_th_c_if_21.doStartTag();
              if (_jspx_eval_c_if_21 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\t\t\t\t\t\t");
                if (_jspx_meth_liferay$1ui_empty$1result$1message_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_21, _jspx_page_context))
                  return;
                out.write("\n\t\t\t\t\t");
              }
              if (_jspx_th_c_if_21.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_21);
                return;
              }
              _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_21);
              out.write("\n\n\t\t\t\t\t");

					if (category != null) {
						PortalUtil.setPageSubtitle(category.getName(), request);
						PortalUtil.setPageDescription(category.getDescription(), request);
					}
					
              out.write("\n\n\t\t\t\t</div>\n\t\t\t");
            }
            if (_jspx_th_c_when_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_3);
              return;
            }
            _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_3);
            out.write("\n\t\t\t");
            //  c:when
            com.liferay.taglib.core.WhenTag _jspx_th_c_when_8 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
            _jspx_th_c_when_8.setPageContext(_jspx_page_context);
            _jspx_th_c_when_8.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_1);
            _jspx_th_c_when_8.setTest( mbListDisplayContext.isShowMyPosts() || mbListDisplayContext.isShowRecentPosts() );
            int _jspx_eval_c_when_8 = _jspx_th_c_when_8.doStartTag();
            if (_jspx_eval_c_when_8 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\t\t\t\t<div class=\"main-content-body\">\n\t\t\t\t\t");
              //  c:choose
              com.liferay.taglib.core.ChooseTag _jspx_th_c_choose_6 = (com.liferay.taglib.core.ChooseTag) _jspx_tagPool_c_choose.get(com.liferay.taglib.core.ChooseTag.class);
              _jspx_th_c_choose_6.setPageContext(_jspx_page_context);
              _jspx_th_c_choose_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_8);
              int _jspx_eval_c_choose_6 = _jspx_th_c_choose_6.doStartTag();
              if (_jspx_eval_c_choose_6 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\t\t\t\t\t\t");
                //  c:when
                com.liferay.taglib.core.WhenTag _jspx_th_c_when_9 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
                _jspx_th_c_when_9.setPageContext(_jspx_page_context);
                _jspx_th_c_when_9.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_6);
                _jspx_th_c_when_9.setTest( mbListDisplayContext.isShowRecentPosts() );
                int _jspx_eval_c_when_9 = _jspx_th_c_when_9.doStartTag();
                if (_jspx_eval_c_when_9 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  out.write("\n\t\t\t\t\t\t\t<div class=\"autofit-float autofit-row\">\n\t\t\t\t\t\t\t\t<div class=\"autofit-col autofit-col-expand\">\n\t\t\t\t\t\t\t\t\t<h3>");
                  if (_jspx_meth_liferay$1ui_message_3((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_when_9, _jspx_page_context))
                    return;
                  out.write("</h3>\n\t\t\t\t\t\t\t\t</div>\n\n\t\t\t\t\t\t\t\t<div class=\"autofit-col autofit-col-end\">\n\t\t\t\t\t\t\t\t\t<div class=\"btn-group\">\n\t\t\t\t\t\t\t\t\t\t");
                  //  c:if
                  com.liferay.taglib.core.IfTag _jspx_th_c_if_22 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                  _jspx_th_c_if_22.setPageContext(_jspx_page_context);
                  _jspx_th_c_if_22.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_9);
                  _jspx_th_c_if_22.setTest( enableRSS );
                  int _jspx_eval_c_if_22 = _jspx_th_c_if_22.doStartTag();
                  if (_jspx_eval_c_if_22 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    out.write("\n\t\t\t\t\t\t\t\t\t\t\t");
                    //  liferay-ui:icon-menu
                    com.liferay.taglib.ui.IconMenuTag _jspx_th_liferay$1ui_icon$1menu_1 = (com.liferay.taglib.ui.IconMenuTag) _jspx_tagPool_liferay$1ui_icon$1menu_showWhenSingleIcon_message_markupView_icon_direction.get(com.liferay.taglib.ui.IconMenuTag.class);
                    _jspx_th_liferay$1ui_icon$1menu_1.setPageContext(_jspx_page_context);
                    _jspx_th_liferay$1ui_icon$1menu_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_22);
                    _jspx_th_liferay$1ui_icon$1menu_1.setDirection("left-side");
                    _jspx_th_liferay$1ui_icon$1menu_1.setIcon( StringPool.BLANK );
                    _jspx_th_liferay$1ui_icon$1menu_1.setMarkupView("lexicon");
                    _jspx_th_liferay$1ui_icon$1menu_1.setMessage( StringPool.BLANK );
                    _jspx_th_liferay$1ui_icon$1menu_1.setShowWhenSingleIcon( true );
                    int _jspx_eval_liferay$1ui_icon$1menu_1 = _jspx_th_liferay$1ui_icon$1menu_1.doStartTag();
                    if (_jspx_eval_liferay$1ui_icon$1menu_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      if (_jspx_eval_liferay$1ui_icon$1menu_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                        out = _jspx_page_context.pushBody();
                        _jspx_th_liferay$1ui_icon$1menu_1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                        _jspx_th_liferay$1ui_icon$1menu_1.doInitBody();
                      }
                      do {
                        out.write("\n\t\t\t\t\t\t\t\t\t\t\t\t");
                        //  liferay-rss:rss
                        com.liferay.rss.taglib.servlet.taglib.RSSTag _jspx_th_liferay$1rss_rss_2 = (com.liferay.rss.taglib.servlet.taglib.RSSTag) _jspx_tagPool_liferay$1rss_rss_url_message_feedType_displayStyle_delta_nobody.get(com.liferay.rss.taglib.servlet.taglib.RSSTag.class);
                        _jspx_th_liferay$1rss_rss_2.setPageContext(_jspx_page_context);
                        _jspx_th_liferay$1rss_rss_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_icon$1menu_1);
                        _jspx_th_liferay$1rss_rss_2.setDelta( rssDelta );
                        _jspx_th_liferay$1rss_rss_2.setDisplayStyle( rssDisplayStyle );
                        _jspx_th_liferay$1rss_rss_2.setFeedType( rssFeedType );
                        _jspx_th_liferay$1rss_rss_2.setMessage("rss");
                        _jspx_th_liferay$1rss_rss_2.setUrl( MBRSSUtil.getRSSURL(plid, 0, 0, groupThreadsUserId, themeDisplay) );
                        int _jspx_eval_liferay$1rss_rss_2 = _jspx_th_liferay$1rss_rss_2.doStartTag();
                        if (_jspx_th_liferay$1rss_rss_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                          _jspx_tagPool_liferay$1rss_rss_url_message_feedType_displayStyle_delta_nobody.reuse(_jspx_th_liferay$1rss_rss_2);
                          return;
                        }
                        _jspx_tagPool_liferay$1rss_rss_url_message_feedType_displayStyle_delta_nobody.reuse(_jspx_th_liferay$1rss_rss_2);
                        out.write("\n\t\t\t\t\t\t\t\t\t\t\t");
                        int evalDoAfterBody = _jspx_th_liferay$1ui_icon$1menu_1.doAfterBody();
                        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                          break;
                      } while (true);
                      if (_jspx_eval_liferay$1ui_icon$1menu_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                        out = _jspx_page_context.popBody();
                    }
                    if (_jspx_th_liferay$1ui_icon$1menu_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_liferay$1ui_icon$1menu_showWhenSingleIcon_message_markupView_icon_direction.reuse(_jspx_th_liferay$1ui_icon$1menu_1);
                      return;
                    }
                    _jspx_tagPool_liferay$1ui_icon$1menu_showWhenSingleIcon_message_markupView_icon_direction.reuse(_jspx_th_liferay$1ui_icon$1menu_1);
                    out.write("\n\t\t\t\t\t\t\t\t\t\t");
                  }
                  if (_jspx_th_c_if_22.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_22);
                    return;
                  }
                  _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_22);
                  out.write("\n\t\t\t\t\t\t\t\t\t</div>\n\t\t\t\t\t\t\t\t</div>\n\t\t\t\t\t\t\t</div>\n\n\t\t\t\t\t\t\t");
                  //  c:if
                  com.liferay.taglib.core.IfTag _jspx_th_c_if_23 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                  _jspx_th_c_if_23.setPageContext(_jspx_page_context);
                  _jspx_th_c_if_23.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_9);
                  _jspx_th_c_if_23.setTest( groupThreadsUserId > 0 );
                  int _jspx_eval_c_if_23 = _jspx_th_c_if_23.doStartTag();
                  if (_jspx_eval_c_if_23 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    out.write("\n\t\t\t\t\t\t\t\t<div class=\"alert alert-info\">\n\t\t\t\t\t\t\t\t\t");
                    if (_jspx_meth_liferay$1ui_message_4((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_23, _jspx_page_context))
                      return;
                    out.write(':');
                    out.write(' ');
                    out.print( HtmlUtil.escape(PortalUtil.getUserName(groupThreadsUserId, StringPool.BLANK)) );
                    out.write("\n\t\t\t\t\t\t\t\t</div>\n\t\t\t\t\t\t\t");
                  }
                  if (_jspx_th_c_if_23.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_23);
                    return;
                  }
                  _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_23);
                  out.write("\n\t\t\t\t\t\t");
                }
                if (_jspx_th_c_when_9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_9);
                  return;
                }
                _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_9);
                out.write("\n\t\t\t\t\t\t");
                //  c:otherwise
                com.liferay.taglib.core.OtherwiseTag _jspx_th_c_otherwise_4 = (com.liferay.taglib.core.OtherwiseTag) _jspx_tagPool_c_otherwise.get(com.liferay.taglib.core.OtherwiseTag.class);
                _jspx_th_c_otherwise_4.setPageContext(_jspx_page_context);
                _jspx_th_c_otherwise_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_6);
                int _jspx_eval_c_otherwise_4 = _jspx_th_c_otherwise_4.doStartTag();
                if (_jspx_eval_c_otherwise_4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  out.write("\n\n\t\t\t\t\t\t\t");

							if (themeDisplay.isSignedIn()) {
								groupThreadsUserId = user.getUserId();
							}
							
                  out.write("\n\n\t\t\t\t\t\t\t<div class=\"autofit-float autofit-row\">\n\t\t\t\t\t\t\t\t<div class=\"autofit-col autofit-col-expand\">\n\t\t\t\t\t\t\t\t\t<h3>");
                  if (_jspx_meth_liferay$1ui_message_5((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_otherwise_4, _jspx_page_context))
                    return;
                  out.write("</h3>\n\t\t\t\t\t\t\t\t</div>\n\n\t\t\t\t\t\t\t\t<div class=\"autofit-col autofit-col-end\">\n\t\t\t\t\t\t\t\t\t<div class=\"btn-group\">\n\t\t\t\t\t\t\t\t\t\t");
                  //  c:if
                  com.liferay.taglib.core.IfTag _jspx_th_c_if_24 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                  _jspx_th_c_if_24.setPageContext(_jspx_page_context);
                  _jspx_th_c_if_24.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_4);
                  _jspx_th_c_if_24.setTest( enableRSS );
                  int _jspx_eval_c_if_24 = _jspx_th_c_if_24.doStartTag();
                  if (_jspx_eval_c_if_24 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    out.write("\n\t\t\t\t\t\t\t\t\t\t\t");
                    //  liferay-ui:icon-menu
                    com.liferay.taglib.ui.IconMenuTag _jspx_th_liferay$1ui_icon$1menu_2 = (com.liferay.taglib.ui.IconMenuTag) _jspx_tagPool_liferay$1ui_icon$1menu_showWhenSingleIcon_message_markupView_icon_direction.get(com.liferay.taglib.ui.IconMenuTag.class);
                    _jspx_th_liferay$1ui_icon$1menu_2.setPageContext(_jspx_page_context);
                    _jspx_th_liferay$1ui_icon$1menu_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_24);
                    _jspx_th_liferay$1ui_icon$1menu_2.setDirection("left-side");
                    _jspx_th_liferay$1ui_icon$1menu_2.setIcon( StringPool.BLANK );
                    _jspx_th_liferay$1ui_icon$1menu_2.setMarkupView("lexicon");
                    _jspx_th_liferay$1ui_icon$1menu_2.setMessage( StringPool.BLANK );
                    _jspx_th_liferay$1ui_icon$1menu_2.setShowWhenSingleIcon( true );
                    int _jspx_eval_liferay$1ui_icon$1menu_2 = _jspx_th_liferay$1ui_icon$1menu_2.doStartTag();
                    if (_jspx_eval_liferay$1ui_icon$1menu_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      if (_jspx_eval_liferay$1ui_icon$1menu_2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                        out = _jspx_page_context.pushBody();
                        _jspx_th_liferay$1ui_icon$1menu_2.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                        _jspx_th_liferay$1ui_icon$1menu_2.doInitBody();
                      }
                      do {
                        out.write("\n\t\t\t\t\t\t\t\t\t\t\t\t");
                        //  liferay-rss:rss
                        com.liferay.rss.taglib.servlet.taglib.RSSTag _jspx_th_liferay$1rss_rss_3 = (com.liferay.rss.taglib.servlet.taglib.RSSTag) _jspx_tagPool_liferay$1rss_rss_url_message_feedType_displayStyle_delta_nobody.get(com.liferay.rss.taglib.servlet.taglib.RSSTag.class);
                        _jspx_th_liferay$1rss_rss_3.setPageContext(_jspx_page_context);
                        _jspx_th_liferay$1rss_rss_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_icon$1menu_2);
                        _jspx_th_liferay$1rss_rss_3.setDelta( rssDelta );
                        _jspx_th_liferay$1rss_rss_3.setDisplayStyle( rssDisplayStyle );
                        _jspx_th_liferay$1rss_rss_3.setFeedType( rssFeedType );
                        _jspx_th_liferay$1rss_rss_3.setMessage("rss");
                        _jspx_th_liferay$1rss_rss_3.setUrl( MBRSSUtil.getRSSURL(plid, 0, 0, groupThreadsUserId, themeDisplay) );
                        int _jspx_eval_liferay$1rss_rss_3 = _jspx_th_liferay$1rss_rss_3.doStartTag();
                        if (_jspx_th_liferay$1rss_rss_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                          _jspx_tagPool_liferay$1rss_rss_url_message_feedType_displayStyle_delta_nobody.reuse(_jspx_th_liferay$1rss_rss_3);
                          return;
                        }
                        _jspx_tagPool_liferay$1rss_rss_url_message_feedType_displayStyle_delta_nobody.reuse(_jspx_th_liferay$1rss_rss_3);
                        out.write("\n\t\t\t\t\t\t\t\t\t\t\t");
                        int evalDoAfterBody = _jspx_th_liferay$1ui_icon$1menu_2.doAfterBody();
                        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                          break;
                      } while (true);
                      if (_jspx_eval_liferay$1ui_icon$1menu_2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                        out = _jspx_page_context.popBody();
                    }
                    if (_jspx_th_liferay$1ui_icon$1menu_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_liferay$1ui_icon$1menu_showWhenSingleIcon_message_markupView_icon_direction.reuse(_jspx_th_liferay$1ui_icon$1menu_2);
                      return;
                    }
                    _jspx_tagPool_liferay$1ui_icon$1menu_showWhenSingleIcon_message_markupView_icon_direction.reuse(_jspx_th_liferay$1ui_icon$1menu_2);
                    out.write("\n\t\t\t\t\t\t\t\t\t\t");
                  }
                  if (_jspx_th_c_if_24.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_24);
                    return;
                  }
                  _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_24);
                  out.write("\n\t\t\t\t\t\t\t\t\t</div>\n\t\t\t\t\t\t\t\t</div>\n\t\t\t\t\t\t\t</div>\n\t\t\t\t\t\t");
                }
                if (_jspx_th_c_otherwise_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_4);
                  return;
                }
                _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_4);
                out.write("\n\t\t\t\t\t");
              }
              if (_jspx_th_c_choose_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_6);
                return;
              }
              _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_6);
              out.write("\n\n\t\t\t\t\t");

					if (groupThreadsUserId > 0) {
						portletURL.setParameter("groupThreadsUserId", String.valueOf(groupThreadsUserId));
					}
					
              out.write("\n\n\t\t\t\t\t");

					SearchContainer threadEntriesSearchContainer = new SearchContainer(renderRequest, null, null, "cur1", 0, mbListDisplayContext.getThreadEntriesDelta(), portletURL, null, "there-are-no-threads");

					mbListDisplayContext.setThreadEntriesDelta(threadEntriesSearchContainer);

					threadEntriesSearchContainer.setOrderByCol(orderByCol);
					threadEntriesSearchContainer.setOrderByComparator(orderByComparator);
					threadEntriesSearchContainer.setOrderByType(orderByType);

					mbListDisplayContext.populateThreadsResultsAndTotal(threadEntriesSearchContainer);

					request.setAttribute("view.jsp-threadEntriesSearchContainer", threadEntriesSearchContainer);
					
              out.write("\n\n\t\t\t\t\t");
              //  liferay-util:include
              com.liferay.taglib.util.IncludeTag _jspx_th_liferay$1util_include_3 = (com.liferay.taglib.util.IncludeTag) _jspx_tagPool_liferay$1util_include_servletContext_page_nobody.get(com.liferay.taglib.util.IncludeTag.class);
              _jspx_th_liferay$1util_include_3.setPageContext(_jspx_page_context);
              _jspx_th_liferay$1util_include_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_8);
              _jspx_th_liferay$1util_include_3.setPage( "/message_boards/view_thread_entries.jsp" );
              _jspx_th_liferay$1util_include_3.setServletContext( application );
              int _jspx_eval_liferay$1util_include_3 = _jspx_th_liferay$1util_include_3.doStartTag();
              if (_jspx_th_liferay$1util_include_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_liferay$1util_include_servletContext_page_nobody.reuse(_jspx_th_liferay$1util_include_3);
                return;
              }
              _jspx_tagPool_liferay$1util_include_servletContext_page_nobody.reuse(_jspx_th_liferay$1util_include_3);
              out.write("\n\n\t\t\t\t\t");

					String pageSubtitle = null;

					if (mbListDisplayContext.isShowMyPosts()) {
						pageSubtitle = "my-posts";
					}
					else if (mbListDisplayContext.isShowRecentPosts()) {
						pageSubtitle = "recent-posts";
					}

					PortalUtil.setPageSubtitle(LanguageUtil.get(request, StringUtil.replace(pageSubtitle, CharPool.UNDERLINE, CharPool.DASH)), request);
					PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(request, TextFormatter.format(pageSubtitle, TextFormatter.O)), portletURL.toString());
					
              out.write("\n\n\t\t\t\t</div>\n\t\t\t");
            }
            if (_jspx_th_c_when_8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_8);
              return;
            }
            _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_8);
            out.write("\n\t\t");
          }
          if (_jspx_th_c_choose_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_1);
            return;
          }
          _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_1);
          out.write('\n');
          out.write('	');
        }
        if (_jspx_th_c_when_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_2);
          return;
        }
        _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_2);
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

  private boolean _jspx_meth_liferay$1ui_message_0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_when_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_0 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_0.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_0);
    _jspx_th_liferay$1ui_message_0.setKey("my-subscriptions");
    int _jspx_eval_liferay$1ui_message_0 = _jspx_th_liferay$1ui_message_0.doStartTag();
    if (_jspx_th_liferay$1ui_message_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_0);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_0);
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
    _jspx_th_portlet_param_1.setValue("/message_boards/view_category");
    int _jspx_eval_portlet_param_1 = _jspx_th_portlet_param_1.doStartTag();
    if (_jspx_th_portlet_param_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_1);
      return true;
    }
    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_1);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_search$1container$1column$1jsp_0(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1ui_search$1container$1row_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:search-container-column-jsp
    com.liferay.taglib.ui.SearchContainerColumnJSPTag _jspx_th_liferay$1ui_search$1container$1column$1jsp_0 = (com.liferay.taglib.ui.SearchContainerColumnJSPTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1jsp_path_cssClass_align_nobody.get(com.liferay.taglib.ui.SearchContainerColumnJSPTag.class);
    _jspx_th_liferay$1ui_search$1container$1column$1jsp_0.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_search$1container$1column$1jsp_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1row_0);
    _jspx_th_liferay$1ui_search$1container$1column$1jsp_0.setAlign("right");
    _jspx_th_liferay$1ui_search$1container$1column$1jsp_0.setCssClass("entry-action");
    _jspx_th_liferay$1ui_search$1container$1column$1jsp_0.setPath("/message_boards/category_action.jsp");
    int _jspx_eval_liferay$1ui_search$1container$1column$1jsp_0 = _jspx_th_liferay$1ui_search$1container$1column$1jsp_0.doStartTag();
    if (_jspx_th_liferay$1ui_search$1container$1column$1jsp_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_search$1container$1column$1jsp_path_cssClass_align_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1jsp_0);
      return true;
    }
    _jspx_tagPool_liferay$1ui_search$1container$1column$1jsp_path_cssClass_align_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1jsp_0);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_search$1iterator_0(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1ui_search$1container_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:search-iterator
    com.liferay.taglib.ui.SearchIteratorTag _jspx_th_liferay$1ui_search$1iterator_0 = (com.liferay.taglib.ui.SearchIteratorTag) _jspx_tagPool_liferay$1ui_search$1iterator_type_markupView_nobody.get(com.liferay.taglib.ui.SearchIteratorTag.class);
    _jspx_th_liferay$1ui_search$1iterator_0.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_search$1iterator_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container_0);
    _jspx_th_liferay$1ui_search$1iterator_0.setMarkupView("lexicon");
    _jspx_th_liferay$1ui_search$1iterator_0.setType("more");
    int _jspx_eval_liferay$1ui_search$1iterator_0 = _jspx_th_liferay$1ui_search$1iterator_0.doStartTag();
    if (_jspx_th_liferay$1ui_search$1iterator_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_search$1iterator_type_markupView_nobody.reuse(_jspx_th_liferay$1ui_search$1iterator_0);
      return true;
    }
    _jspx_tagPool_liferay$1ui_search$1iterator_type_markupView_nobody.reuse(_jspx_th_liferay$1ui_search$1iterator_0);
    return false;
  }

  private boolean _jspx_meth_portlet_param_3(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1portlet_renderURL_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:param
    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_3 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
    _jspx_th_portlet_param_3.setPageContext(_jspx_page_context);
    _jspx_th_portlet_param_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_1);
    _jspx_th_portlet_param_3.setName("mvcRenderCommandName");
    _jspx_th_portlet_param_3.setValue("/message_boards/view_message");
    int _jspx_eval_portlet_param_3 = _jspx_th_portlet_param_3.doStartTag();
    if (_jspx_th_portlet_param_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_3);
      return true;
    }
    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_3);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_1(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_1 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_1.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_1);
    _jspx_th_liferay$1ui_message_1.setKey("thread-locked");
    int _jspx_eval_liferay$1ui_message_1 = _jspx_th_liferay$1ui_message_1.doStartTag();
    if (_jspx_th_liferay$1ui_message_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_1);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_1);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_search$1container$1column$1jsp_1(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:search-container-column-jsp
    com.liferay.taglib.ui.SearchContainerColumnJSPTag _jspx_th_liferay$1ui_search$1container$1column$1jsp_1 = (com.liferay.taglib.ui.SearchContainerColumnJSPTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1jsp_path_cssClass_align_nobody.get(com.liferay.taglib.ui.SearchContainerColumnJSPTag.class);
    _jspx_th_liferay$1ui_search$1container$1column$1jsp_1.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_search$1container$1column$1jsp_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_4);
    _jspx_th_liferay$1ui_search$1container$1column$1jsp_1.setAlign("right");
    _jspx_th_liferay$1ui_search$1container$1column$1jsp_1.setCssClass("entry-action");
    _jspx_th_liferay$1ui_search$1container$1column$1jsp_1.setPath("/message_boards/message_action.jsp");
    int _jspx_eval_liferay$1ui_search$1container$1column$1jsp_1 = _jspx_th_liferay$1ui_search$1container$1column$1jsp_1.doStartTag();
    if (_jspx_th_liferay$1ui_search$1container$1column$1jsp_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_search$1container$1column$1jsp_path_cssClass_align_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1jsp_1);
      return true;
    }
    _jspx_tagPool_liferay$1ui_search$1container$1column$1jsp_path_cssClass_align_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1jsp_1);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_search$1iterator_1(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1ui_search$1container_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:search-iterator
    com.liferay.taglib.ui.SearchIteratorTag _jspx_th_liferay$1ui_search$1iterator_1 = (com.liferay.taglib.ui.SearchIteratorTag) _jspx_tagPool_liferay$1ui_search$1iterator_markupView_nobody.get(com.liferay.taglib.ui.SearchIteratorTag.class);
    _jspx_th_liferay$1ui_search$1iterator_1.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_search$1iterator_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container_1);
    _jspx_th_liferay$1ui_search$1iterator_1.setMarkupView("lexicon");
    int _jspx_eval_liferay$1ui_search$1iterator_1 = _jspx_th_liferay$1ui_search$1iterator_1.doStartTag();
    if (_jspx_th_liferay$1ui_search$1iterator_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_search$1iterator_markupView_nobody.reuse(_jspx_th_liferay$1ui_search$1iterator_1);
      return true;
    }
    _jspx_tagPool_liferay$1ui_search$1iterator_markupView_nobody.reuse(_jspx_th_liferay$1ui_search$1iterator_1);
    return false;
  }

  private boolean _jspx_meth_portlet_param_5(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1portlet_renderURL_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:param
    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_5 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
    _jspx_th_portlet_param_5.setPageContext(_jspx_page_context);
    _jspx_th_portlet_param_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_2);
    _jspx_th_portlet_param_5.setName("mvcRenderCommandName");
    _jspx_th_portlet_param_5.setValue("/message_boards/view_message");
    int _jspx_eval_portlet_param_5 = _jspx_th_portlet_param_5.doStartTag();
    if (_jspx_th_portlet_param_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_5);
      return true;
    }
    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_5);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_2(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_6, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_2 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_2.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_6);
    _jspx_th_liferay$1ui_message_2.setKey("thread-locked");
    int _jspx_eval_liferay$1ui_message_2 = _jspx_th_liferay$1ui_message_2.doStartTag();
    if (_jspx_th_liferay$1ui_message_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_2);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_2);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_search$1container$1column$1jsp_2(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_9, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:search-container-column-jsp
    com.liferay.taglib.ui.SearchContainerColumnJSPTag _jspx_th_liferay$1ui_search$1container$1column$1jsp_2 = (com.liferay.taglib.ui.SearchContainerColumnJSPTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1jsp_path_cssClass_align_nobody.get(com.liferay.taglib.ui.SearchContainerColumnJSPTag.class);
    _jspx_th_liferay$1ui_search$1container$1column$1jsp_2.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_search$1container$1column$1jsp_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_9);
    _jspx_th_liferay$1ui_search$1container$1column$1jsp_2.setAlign("right");
    _jspx_th_liferay$1ui_search$1container$1column$1jsp_2.setCssClass("entry-action");
    _jspx_th_liferay$1ui_search$1container$1column$1jsp_2.setPath("/message_boards/message_action.jsp");
    int _jspx_eval_liferay$1ui_search$1container$1column$1jsp_2 = _jspx_th_liferay$1ui_search$1container$1column$1jsp_2.doStartTag();
    if (_jspx_th_liferay$1ui_search$1container$1column$1jsp_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_search$1container$1column$1jsp_path_cssClass_align_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1jsp_2);
      return true;
    }
    _jspx_tagPool_liferay$1ui_search$1container$1column$1jsp_path_cssClass_align_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1jsp_2);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_search$1iterator_2(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1ui_search$1container_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:search-iterator
    com.liferay.taglib.ui.SearchIteratorTag _jspx_th_liferay$1ui_search$1iterator_2 = (com.liferay.taglib.ui.SearchIteratorTag) _jspx_tagPool_liferay$1ui_search$1iterator_markupView_nobody.get(com.liferay.taglib.ui.SearchIteratorTag.class);
    _jspx_th_liferay$1ui_search$1iterator_2.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_search$1iterator_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container_2);
    _jspx_th_liferay$1ui_search$1iterator_2.setMarkupView("lexicon");
    int _jspx_eval_liferay$1ui_search$1iterator_2 = _jspx_th_liferay$1ui_search$1iterator_2.doStartTag();
    if (_jspx_th_liferay$1ui_search$1iterator_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_search$1iterator_markupView_nobody.reuse(_jspx_th_liferay$1ui_search$1iterator_2);
      return true;
    }
    _jspx_tagPool_liferay$1ui_search$1iterator_markupView_nobody.reuse(_jspx_th_liferay$1ui_search$1iterator_2);
    return false;
  }

  private boolean _jspx_meth_portlet_param_7(javax.servlet.jsp.tagext.JspTag _jspx_th_portlet_renderURL_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:param
    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_7 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
    _jspx_th_portlet_param_7.setPageContext(_jspx_page_context);
    _jspx_th_portlet_param_7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_0);
    _jspx_th_portlet_param_7.setName("mvcRenderCommandName");
    _jspx_th_portlet_param_7.setValue("/message_boards/edit_category");
    int _jspx_eval_portlet_param_7 = _jspx_th_portlet_param_7.doStartTag();
    if (_jspx_th_portlet_param_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_7);
      return true;
    }
    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_7);
    return false;
  }

  private boolean _jspx_meth_portlet_param_10(javax.servlet.jsp.tagext.JspTag _jspx_th_portlet_renderURL_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:param
    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_10 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
    _jspx_th_portlet_param_10.setPageContext(_jspx_page_context);
    _jspx_th_portlet_param_10.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_1);
    _jspx_th_portlet_param_10.setName("mvcRenderCommandName");
    _jspx_th_portlet_param_10.setValue("/message_boards/edit_message");
    int _jspx_eval_portlet_param_10 = _jspx_th_portlet_param_10.doStartTag();
    if (_jspx_th_portlet_param_10.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_10);
      return true;
    }
    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_10);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_empty$1result$1message_0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_21, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:empty-result-message
    com.liferay.taglib.ui.EmptyResultMessageTag _jspx_th_liferay$1ui_empty$1result$1message_0 = (com.liferay.taglib.ui.EmptyResultMessageTag) _jspx_tagPool_liferay$1ui_empty$1result$1message_message_nobody.get(com.liferay.taglib.ui.EmptyResultMessageTag.class);
    _jspx_th_liferay$1ui_empty$1result$1message_0.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_empty$1result$1message_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_21);
    _jspx_th_liferay$1ui_empty$1result$1message_0.setMessage("there-are-no-threads-or-categories");
    int _jspx_eval_liferay$1ui_empty$1result$1message_0 = _jspx_th_liferay$1ui_empty$1result$1message_0.doStartTag();
    if (_jspx_th_liferay$1ui_empty$1result$1message_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_empty$1result$1message_message_nobody.reuse(_jspx_th_liferay$1ui_empty$1result$1message_0);
      return true;
    }
    _jspx_tagPool_liferay$1ui_empty$1result$1message_message_nobody.reuse(_jspx_th_liferay$1ui_empty$1result$1message_0);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_3(javax.servlet.jsp.tagext.JspTag _jspx_th_c_when_9, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_3 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_3.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_9);
    _jspx_th_liferay$1ui_message_3.setKey("recent-posts");
    int _jspx_eval_liferay$1ui_message_3 = _jspx_th_liferay$1ui_message_3.doStartTag();
    if (_jspx_th_liferay$1ui_message_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_3);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_3);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_4(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_23, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_4 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_4.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_23);
    _jspx_th_liferay$1ui_message_4.setKey("filter-by-user");
    int _jspx_eval_liferay$1ui_message_4 = _jspx_th_liferay$1ui_message_4.doStartTag();
    if (_jspx_th_liferay$1ui_message_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_4);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_4);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_5(javax.servlet.jsp.tagext.JspTag _jspx_th_c_otherwise_4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_5 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_5.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_4);
    _jspx_th_liferay$1ui_message_5.setKey("my-posts");
    int _jspx_eval_liferay$1ui_message_5 = _jspx_th_liferay$1ui_message_5.doStartTag();
    if (_jspx_th_liferay$1ui_message_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_5);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_5);
    return false;
  }
}
