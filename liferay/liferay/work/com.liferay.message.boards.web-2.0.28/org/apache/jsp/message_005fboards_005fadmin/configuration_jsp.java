package org.apache.jsp.message_005fboards_005fadmin;

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

public final class configuration_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(2);
    _jspx_dependants.add("/message_boards/init.jsp");
    _jspx_dependants.add("/message_boards/init-ext.jsp");
  }

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_section;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_input_value_type_name_label_helpMessage_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_param_value_name_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1portlet_renderURL_var_portletConfiguration_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_if_test;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1rss_rss$1settings_feedType_enabled_displayStyle_delta_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1frontend_edit$1form_onSubmit_name_method_action;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_input_type_title_onChange_name_label_cssClass_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_option_value_label_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_script;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_button_type_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_option_value_selected_label_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_error_message_key_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1frontend_fieldset;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1frontend_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_input_wrapperCssClass_value_type_name_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_input_value_type_name_label_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_namespace_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_input_value_title_size_name_label_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_input_value_type_title_name_label_cssClass_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1frontend_email$1notification$1settings_emailSubject_emailParam_emailEnabled_emailDefinitionTerms_emailBody_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_message_key_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_input_value_type_name_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_input_title_size_onChange_name_label_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_tabs_type_refresh_names;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1frontend_edit$1form$1body;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_select_showEmptyOption_onChange_name_label;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1frontend_edit$1form$1footer;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_select_name;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1frontend_fieldset_label_collapsible_collapsed;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1theme_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1frontend_fieldset$1group;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_select_value_name_label;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_input_value_name_label_cssClass_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1trash_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1portlet_actionURL_var_portletConfiguration;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_liferay$1ui_section = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_input_value_type_name_label_helpMessage_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_param_value_name_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1portlet_renderURL_var_portletConfiguration_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_if_test = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1rss_rss$1settings_feedType_enabled_displayStyle_delta_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1frontend_edit$1form_onSubmit_name_method_action = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_input_type_title_onChange_name_label_cssClass_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_option_value_label_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_script = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_button_type_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_option_value_selected_label_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_error_message_key_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1frontend_fieldset = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1frontend_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_input_wrapperCssClass_value_type_name_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_input_value_type_name_label_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_namespace_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_input_value_title_size_name_label_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_input_value_type_title_name_label_cssClass_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1frontend_email$1notification$1settings_emailSubject_emailParam_emailEnabled_emailDefinitionTerms_emailBody_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_message_key_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_input_value_type_name_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_input_title_size_onChange_name_label_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_tabs_type_refresh_names = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1frontend_edit$1form$1body = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_select_showEmptyOption_onChange_name_label = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1frontend_edit$1form$1footer = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_select_name = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1frontend_fieldset_label_collapsible_collapsed = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1theme_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1frontend_fieldset$1group = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_select_value_name_label = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_input_value_name_label_cssClass_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1trash_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1portlet_actionURL_var_portletConfiguration = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_liferay$1ui_section.release();
    _jspx_tagPool_aui_input_value_type_name_label_helpMessage_nobody.release();
    _jspx_tagPool_portlet_param_value_name_nobody.release();
    _jspx_tagPool_liferay$1portlet_renderURL_var_portletConfiguration_nobody.release();
    _jspx_tagPool_c_if_test.release();
    _jspx_tagPool_liferay$1rss_rss$1settings_feedType_enabled_displayStyle_delta_nobody.release();
    _jspx_tagPool_liferay$1frontend_edit$1form_onSubmit_name_method_action.release();
    _jspx_tagPool_aui_input_type_title_onChange_name_label_cssClass_nobody.release();
    _jspx_tagPool_aui_option_value_label_nobody.release();
    _jspx_tagPool_aui_script.release();
    _jspx_tagPool_aui_button_type_nobody.release();
    _jspx_tagPool_aui_option_value_selected_label_nobody.release();
    _jspx_tagPool_liferay$1ui_error_message_key_nobody.release();
    _jspx_tagPool_liferay$1frontend_fieldset.release();
    _jspx_tagPool_liferay$1frontend_defineObjects_nobody.release();
    _jspx_tagPool_aui_input_wrapperCssClass_value_type_name_nobody.release();
    _jspx_tagPool_aui_input_value_type_name_label_nobody.release();
    _jspx_tagPool_portlet_namespace_nobody.release();
    _jspx_tagPool_aui_input_value_title_size_name_label_nobody.release();
    _jspx_tagPool_aui_input_value_type_title_name_label_cssClass_nobody.release();
    _jspx_tagPool_liferay$1frontend_email$1notification$1settings_emailSubject_emailParam_emailEnabled_emailDefinitionTerms_emailBody_nobody.release();
    _jspx_tagPool_liferay$1ui_message_key_nobody.release();
    _jspx_tagPool_aui_input_value_type_name_nobody.release();
    _jspx_tagPool_aui_input_title_size_onChange_name_label_nobody.release();
    _jspx_tagPool_liferay$1ui_tabs_type_refresh_names.release();
    _jspx_tagPool_portlet_defineObjects_nobody.release();
    _jspx_tagPool_liferay$1frontend_edit$1form$1body.release();
    _jspx_tagPool_aui_select_showEmptyOption_onChange_name_label.release();
    _jspx_tagPool_liferay$1frontend_edit$1form$1footer.release();
    _jspx_tagPool_aui_select_name.release();
    _jspx_tagPool_liferay$1frontend_fieldset_label_collapsible_collapsed.release();
    _jspx_tagPool_liferay$1theme_defineObjects_nobody.release();
    _jspx_tagPool_liferay$1frontend_fieldset$1group.release();
    _jspx_tagPool_aui_select_value_name_label.release();
    _jspx_tagPool_aui_input_value_name_label_cssClass_nobody.release();
    _jspx_tagPool_liferay$1trash_defineObjects_nobody.release();
    _jspx_tagPool_liferay$1portlet_actionURL_var_portletConfiguration.release();
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

Set<Locale> locales = LanguageUtil.getAvailableLocales(themeDisplay.getSiteGroupId());

mbGroupServiceSettings = MBGroupServiceSettings.getInstance(themeDisplay.getSiteGroupId(), request.getParameterMap());

      out.write('\n');
      out.write('\n');
      //  liferay-portlet:actionURL
      com.liferay.taglib.portlet.ActionURLTag _jspx_th_liferay$1portlet_actionURL_0 = (com.liferay.taglib.portlet.ActionURLTag) _jspx_tagPool_liferay$1portlet_actionURL_var_portletConfiguration.get(com.liferay.taglib.portlet.ActionURLTag.class);
      _jspx_th_liferay$1portlet_actionURL_0.setPageContext(_jspx_page_context);
      _jspx_th_liferay$1portlet_actionURL_0.setParent(null);
      _jspx_th_liferay$1portlet_actionURL_0.setPortletConfiguration( true );
      _jspx_th_liferay$1portlet_actionURL_0.setVar("configurationActionURL");
      int _jspx_eval_liferay$1portlet_actionURL_0 = _jspx_th_liferay$1portlet_actionURL_0.doStartTag();
      if (_jspx_eval_liferay$1portlet_actionURL_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        out.write('\n');
        out.write('	');
        //  portlet:param
        com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_0 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
        _jspx_th_portlet_param_0.setPageContext(_jspx_page_context);
        _jspx_th_portlet_param_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_actionURL_0);
        _jspx_th_portlet_param_0.setName("serviceName");
        _jspx_th_portlet_param_0.setValue( MBConstants.SERVICE_NAME );
        int _jspx_eval_portlet_param_0 = _jspx_th_portlet_param_0.doStartTag();
        if (_jspx_th_portlet_param_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_0);
          return;
        }
        _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_0);
        out.write('\n');
        out.write('	');
        if (_jspx_meth_portlet_param_1((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1portlet_actionURL_0, _jspx_page_context))
          return;
        out.write('\n');
      }
      if (_jspx_th_liferay$1portlet_actionURL_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_liferay$1portlet_actionURL_var_portletConfiguration.reuse(_jspx_th_liferay$1portlet_actionURL_0);
        return;
      }
      _jspx_tagPool_liferay$1portlet_actionURL_var_portletConfiguration.reuse(_jspx_th_liferay$1portlet_actionURL_0);
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
      com.liferay.frontend.taglib.servlet.taglib.EditFormTag _jspx_th_liferay$1frontend_edit$1form_0 = (com.liferay.frontend.taglib.servlet.taglib.EditFormTag) _jspx_tagPool_liferay$1frontend_edit$1form_onSubmit_name_method_action.get(com.liferay.frontend.taglib.servlet.taglib.EditFormTag.class);
      _jspx_th_liferay$1frontend_edit$1form_0.setPageContext(_jspx_page_context);
      _jspx_th_liferay$1frontend_edit$1form_0.setParent(null);
      _jspx_th_liferay$1frontend_edit$1form_0.setAction( configurationActionURL );
      _jspx_th_liferay$1frontend_edit$1form_0.setMethod("post");
      _jspx_th_liferay$1frontend_edit$1form_0.setName("fm");
      _jspx_th_liferay$1frontend_edit$1form_0.setOnSubmit( "event.preventDefault(); " + renderResponse.getNamespace() + "saveConfiguration();" );
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
        out.write("\n\n\t");

	String tabs2Names = "general,email-from,message-added-email,message-updated-email,thread-priorities,user-ranks";

	if (PortalUtil.isRSSFeedsEnabled()) {
		tabs2Names += ",rss";
	}
	
        out.write("\n\n\t");
        //  liferay-frontend:edit-form-body
        com.liferay.frontend.taglib.servlet.taglib.EditFormBodyTag _jspx_th_liferay$1frontend_edit$1form$1body_0 = (com.liferay.frontend.taglib.servlet.taglib.EditFormBodyTag) _jspx_tagPool_liferay$1frontend_edit$1form$1body.get(com.liferay.frontend.taglib.servlet.taglib.EditFormBodyTag.class);
        _jspx_th_liferay$1frontend_edit$1form$1body_0.setPageContext(_jspx_page_context);
        _jspx_th_liferay$1frontend_edit$1form$1body_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_edit$1form_0);
        int _jspx_eval_liferay$1frontend_edit$1form$1body_0 = _jspx_th_liferay$1frontend_edit$1form$1body_0.doStartTag();
        if (_jspx_eval_liferay$1frontend_edit$1form$1body_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          out.write("\n\t\t");
          //  liferay-ui:tabs
          com.liferay.taglib.ui.TabsTag _jspx_th_liferay$1ui_tabs_0 = (com.liferay.taglib.ui.TabsTag) _jspx_tagPool_liferay$1ui_tabs_type_refresh_names.get(com.liferay.taglib.ui.TabsTag.class);
          _jspx_th_liferay$1ui_tabs_0.setPageContext(_jspx_page_context);
          _jspx_th_liferay$1ui_tabs_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_edit$1form$1body_0);
          _jspx_th_liferay$1ui_tabs_0.setNames( tabs2Names );
          _jspx_th_liferay$1ui_tabs_0.setRefresh( false );
          _jspx_th_liferay$1ui_tabs_0.setType("tabs nav-tabs-default");
          int _jspx_eval_liferay$1ui_tabs_0 = _jspx_th_liferay$1ui_tabs_0.doStartTag();
          if (_jspx_eval_liferay$1ui_tabs_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t\t");
            //  liferay-ui:error
            com.liferay.taglib.ui.ErrorTag _jspx_th_liferay$1ui_error_0 = (com.liferay.taglib.ui.ErrorTag) _jspx_tagPool_liferay$1ui_error_message_key_nobody.get(com.liferay.taglib.ui.ErrorTag.class);
            _jspx_th_liferay$1ui_error_0.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1ui_error_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_tabs_0);
            _jspx_th_liferay$1ui_error_0.setKey("emailFromAddress");
            _jspx_th_liferay$1ui_error_0.setMessage("please-enter-a-valid-email-address");
            int _jspx_eval_liferay$1ui_error_0 = _jspx_th_liferay$1ui_error_0.doStartTag();
            if (_jspx_th_liferay$1ui_error_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_liferay$1ui_error_message_key_nobody.reuse(_jspx_th_liferay$1ui_error_0);
              return;
            }
            _jspx_tagPool_liferay$1ui_error_message_key_nobody.reuse(_jspx_th_liferay$1ui_error_0);
            out.write("\n\t\t\t");
            //  liferay-ui:error
            com.liferay.taglib.ui.ErrorTag _jspx_th_liferay$1ui_error_1 = (com.liferay.taglib.ui.ErrorTag) _jspx_tagPool_liferay$1ui_error_message_key_nobody.get(com.liferay.taglib.ui.ErrorTag.class);
            _jspx_th_liferay$1ui_error_1.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1ui_error_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_tabs_0);
            _jspx_th_liferay$1ui_error_1.setKey("emailFromName");
            _jspx_th_liferay$1ui_error_1.setMessage("please-enter-a-valid-name");
            int _jspx_eval_liferay$1ui_error_1 = _jspx_th_liferay$1ui_error_1.doStartTag();
            if (_jspx_th_liferay$1ui_error_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_liferay$1ui_error_message_key_nobody.reuse(_jspx_th_liferay$1ui_error_1);
              return;
            }
            _jspx_tagPool_liferay$1ui_error_message_key_nobody.reuse(_jspx_th_liferay$1ui_error_1);
            out.write("\n\t\t\t");
            //  liferay-ui:error
            com.liferay.taglib.ui.ErrorTag _jspx_th_liferay$1ui_error_2 = (com.liferay.taglib.ui.ErrorTag) _jspx_tagPool_liferay$1ui_error_message_key_nobody.get(com.liferay.taglib.ui.ErrorTag.class);
            _jspx_th_liferay$1ui_error_2.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1ui_error_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_tabs_0);
            _jspx_th_liferay$1ui_error_2.setKey("emailMessageAddedBody");
            _jspx_th_liferay$1ui_error_2.setMessage("please-enter-a-valid-body");
            int _jspx_eval_liferay$1ui_error_2 = _jspx_th_liferay$1ui_error_2.doStartTag();
            if (_jspx_th_liferay$1ui_error_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_liferay$1ui_error_message_key_nobody.reuse(_jspx_th_liferay$1ui_error_2);
              return;
            }
            _jspx_tagPool_liferay$1ui_error_message_key_nobody.reuse(_jspx_th_liferay$1ui_error_2);
            out.write("\n\t\t\t");
            //  liferay-ui:error
            com.liferay.taglib.ui.ErrorTag _jspx_th_liferay$1ui_error_3 = (com.liferay.taglib.ui.ErrorTag) _jspx_tagPool_liferay$1ui_error_message_key_nobody.get(com.liferay.taglib.ui.ErrorTag.class);
            _jspx_th_liferay$1ui_error_3.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1ui_error_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_tabs_0);
            _jspx_th_liferay$1ui_error_3.setKey("emailMessageAddedSubject");
            _jspx_th_liferay$1ui_error_3.setMessage("please-enter-a-valid-subject");
            int _jspx_eval_liferay$1ui_error_3 = _jspx_th_liferay$1ui_error_3.doStartTag();
            if (_jspx_th_liferay$1ui_error_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_liferay$1ui_error_message_key_nobody.reuse(_jspx_th_liferay$1ui_error_3);
              return;
            }
            _jspx_tagPool_liferay$1ui_error_message_key_nobody.reuse(_jspx_th_liferay$1ui_error_3);
            out.write("\n\t\t\t");
            //  liferay-ui:error
            com.liferay.taglib.ui.ErrorTag _jspx_th_liferay$1ui_error_4 = (com.liferay.taglib.ui.ErrorTag) _jspx_tagPool_liferay$1ui_error_message_key_nobody.get(com.liferay.taglib.ui.ErrorTag.class);
            _jspx_th_liferay$1ui_error_4.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1ui_error_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_tabs_0);
            _jspx_th_liferay$1ui_error_4.setKey("emailMessageUpdatedBody");
            _jspx_th_liferay$1ui_error_4.setMessage("please-enter-a-valid-body");
            int _jspx_eval_liferay$1ui_error_4 = _jspx_th_liferay$1ui_error_4.doStartTag();
            if (_jspx_th_liferay$1ui_error_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_liferay$1ui_error_message_key_nobody.reuse(_jspx_th_liferay$1ui_error_4);
              return;
            }
            _jspx_tagPool_liferay$1ui_error_message_key_nobody.reuse(_jspx_th_liferay$1ui_error_4);
            out.write("\n\t\t\t");
            //  liferay-ui:error
            com.liferay.taglib.ui.ErrorTag _jspx_th_liferay$1ui_error_5 = (com.liferay.taglib.ui.ErrorTag) _jspx_tagPool_liferay$1ui_error_message_key_nobody.get(com.liferay.taglib.ui.ErrorTag.class);
            _jspx_th_liferay$1ui_error_5.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1ui_error_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_tabs_0);
            _jspx_th_liferay$1ui_error_5.setKey("emailMessageUpdatedSubject");
            _jspx_th_liferay$1ui_error_5.setMessage("please-enter-a-valid-subject");
            int _jspx_eval_liferay$1ui_error_5 = _jspx_th_liferay$1ui_error_5.doStartTag();
            if (_jspx_th_liferay$1ui_error_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_liferay$1ui_error_message_key_nobody.reuse(_jspx_th_liferay$1ui_error_5);
              return;
            }
            _jspx_tagPool_liferay$1ui_error_message_key_nobody.reuse(_jspx_th_liferay$1ui_error_5);
            out.write("\n\t\t\t");
            //  liferay-ui:error
            com.liferay.taglib.ui.ErrorTag _jspx_th_liferay$1ui_error_6 = (com.liferay.taglib.ui.ErrorTag) _jspx_tagPool_liferay$1ui_error_message_key_nobody.get(com.liferay.taglib.ui.ErrorTag.class);
            _jspx_th_liferay$1ui_error_6.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1ui_error_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_tabs_0);
            _jspx_th_liferay$1ui_error_6.setKey("userRank");
            _jspx_th_liferay$1ui_error_6.setMessage("please-enter-valid-user-ranks");
            int _jspx_eval_liferay$1ui_error_6 = _jspx_th_liferay$1ui_error_6.doStartTag();
            if (_jspx_th_liferay$1ui_error_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_liferay$1ui_error_message_key_nobody.reuse(_jspx_th_liferay$1ui_error_6);
              return;
            }
            _jspx_tagPool_liferay$1ui_error_message_key_nobody.reuse(_jspx_th_liferay$1ui_error_6);
            out.write("\n\n\t\t\t");
            //  liferay-ui:section
            com.liferay.taglib.ui.SectionTag _jspx_th_liferay$1ui_section_0 = (com.liferay.taglib.ui.SectionTag) _jspx_tagPool_liferay$1ui_section.get(com.liferay.taglib.ui.SectionTag.class);
            _jspx_th_liferay$1ui_section_0.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1ui_section_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_tabs_0);
            int _jspx_eval_liferay$1ui_section_0 = _jspx_th_liferay$1ui_section_0.doStartTag();
            if (_jspx_eval_liferay$1ui_section_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              java.lang.String sectionParam = null;
              java.lang.String sectionName = null;
              java.lang.Boolean sectionSelected = null;
              java.lang.String sectionScroll = null;
              java.lang.String sectionRedirectParams = null;
              sectionParam = (java.lang.String) _jspx_page_context.findAttribute("sectionParam");
              sectionName = (java.lang.String) _jspx_page_context.findAttribute("sectionName");
              sectionSelected = (java.lang.Boolean) _jspx_page_context.findAttribute("sectionSelected");
              sectionScroll = (java.lang.String) _jspx_page_context.findAttribute("sectionScroll");
              sectionRedirectParams = (java.lang.String) _jspx_page_context.findAttribute("sectionRedirectParams");
              out.write("\n\t\t\t\t");
              //  liferay-frontend:fieldset-group
              com.liferay.frontend.taglib.servlet.taglib.FieldsetGroupTag _jspx_th_liferay$1frontend_fieldset$1group_0 = (com.liferay.frontend.taglib.servlet.taglib.FieldsetGroupTag) _jspx_tagPool_liferay$1frontend_fieldset$1group.get(com.liferay.frontend.taglib.servlet.taglib.FieldsetGroupTag.class);
              _jspx_th_liferay$1frontend_fieldset$1group_0.setPageContext(_jspx_page_context);
              _jspx_th_liferay$1frontend_fieldset$1group_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_section_0);
              int _jspx_eval_liferay$1frontend_fieldset$1group_0 = _jspx_th_liferay$1frontend_fieldset$1group_0.doStartTag();
              if (_jspx_eval_liferay$1frontend_fieldset$1group_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\t\t\t\t\t");
                //  liferay-frontend:fieldset
                com.liferay.frontend.taglib.servlet.taglib.FieldsetTag _jspx_th_liferay$1frontend_fieldset_0 = (com.liferay.frontend.taglib.servlet.taglib.FieldsetTag) _jspx_tagPool_liferay$1frontend_fieldset.get(com.liferay.frontend.taglib.servlet.taglib.FieldsetTag.class);
                _jspx_th_liferay$1frontend_fieldset_0.setPageContext(_jspx_page_context);
                _jspx_th_liferay$1frontend_fieldset_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_fieldset$1group_0);
                int _jspx_eval_liferay$1frontend_fieldset_0 = _jspx_th_liferay$1frontend_fieldset_0.doStartTag();
                if (_jspx_eval_liferay$1frontend_fieldset_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  out.write("\n\t\t\t\t\t\t");
                  //  aui:input
                  com.liferay.taglib.aui.InputTag _jspx_th_aui_input_2 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
                  _jspx_th_aui_input_2.setPageContext(_jspx_page_context);
                  _jspx_th_aui_input_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_fieldset_0);
                  _jspx_th_aui_input_2.setName("preferences--allowAnonymousPosting--");
                  _jspx_th_aui_input_2.setType("checkbox");
                  _jspx_th_aui_input_2.setValue( mbGroupServiceSettings.isAllowAnonymousPosting() );
                  int _jspx_eval_aui_input_2 = _jspx_th_aui_input_2.doStartTag();
                  if (_jspx_th_aui_input_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_2);
                    return;
                  }
                  _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_2);
                  out.write("\n\n\t\t\t\t\t\t");
                  //  aui:input
                  com.liferay.taglib.aui.InputTag _jspx_th_aui_input_3 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_label_helpMessage_nobody.get(com.liferay.taglib.aui.InputTag.class);
                  _jspx_th_aui_input_3.setPageContext(_jspx_page_context);
                  _jspx_th_aui_input_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_fieldset_0);
                  _jspx_th_aui_input_3.setHelpMessage("message-boards-message-subscribe-by-default-help");
                  _jspx_th_aui_input_3.setLabel("subscribe-by-default");
                  _jspx_th_aui_input_3.setName("preferences--subscribeByDefault--");
                  _jspx_th_aui_input_3.setType("checkbox");
                  _jspx_th_aui_input_3.setValue( subscribeByDefault );
                  int _jspx_eval_aui_input_3 = _jspx_th_aui_input_3.doStartTag();
                  if (_jspx_th_aui_input_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_aui_input_value_type_name_label_helpMessage_nobody.reuse(_jspx_th_aui_input_3);
                    return;
                  }
                  _jspx_tagPool_aui_input_value_type_name_label_helpMessage_nobody.reuse(_jspx_th_aui_input_3);
                  out.write("\n\n\t\t\t\t\t\t");
                  //  aui:select
                  com.liferay.taglib.aui.SelectTag _jspx_th_aui_select_0 = (com.liferay.taglib.aui.SelectTag) _jspx_tagPool_aui_select_name.get(com.liferay.taglib.aui.SelectTag.class);
                  _jspx_th_aui_select_0.setPageContext(_jspx_page_context);
                  _jspx_th_aui_select_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_fieldset_0);
                  _jspx_th_aui_select_0.setName("preferences--messageFormat--");
                  int _jspx_eval_aui_select_0 = _jspx_th_aui_select_0.doStartTag();
                  if (_jspx_eval_aui_select_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    if (_jspx_eval_aui_select_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                      out = _jspx_page_context.pushBody();
                      _jspx_th_aui_select_0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                      _jspx_th_aui_select_0.doInitBody();
                    }
                    do {
                      out.write("\n\n\t\t\t\t\t\t\t");

							for (int i = 0; i < MBMessageConstants.FORMATS.length; i++) {
							
                      out.write("\n\n\t\t\t\t\t\t\t\t");
                      //  c:if
                      com.liferay.taglib.core.IfTag _jspx_th_c_if_0 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                      _jspx_th_c_if_0.setPageContext(_jspx_page_context);
                      _jspx_th_c_if_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_0);
                      _jspx_th_c_if_0.setTest( com.liferay.message.boards.util.MBUtil.isValidMessageFormat(MBMessageConstants.FORMATS[i]) );
                      int _jspx_eval_c_if_0 = _jspx_th_c_if_0.doStartTag();
                      if (_jspx_eval_c_if_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                        out.write("\n\t\t\t\t\t\t\t\t\t");
                        //  aui:option
                        com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_0 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_value_selected_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
                        _jspx_th_aui_option_0.setPageContext(_jspx_page_context);
                        _jspx_th_aui_option_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_0);
                        _jspx_th_aui_option_0.setLabel( LanguageUtil.get(request, "message-boards.message-formats." + MBMessageConstants.FORMATS[i]) );
                        _jspx_th_aui_option_0.setSelected( messageFormat.equals(MBMessageConstants.FORMATS[i]) );
                        _jspx_th_aui_option_0.setValue( MBMessageConstants.FORMATS[i] );
                        int _jspx_eval_aui_option_0 = _jspx_th_aui_option_0.doStartTag();
                        if (_jspx_th_aui_option_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                          _jspx_tagPool_aui_option_value_selected_label_nobody.reuse(_jspx_th_aui_option_0);
                          return;
                        }
                        _jspx_tagPool_aui_option_value_selected_label_nobody.reuse(_jspx_th_aui_option_0);
                        out.write("\n\t\t\t\t\t\t\t\t");
                      }
                      if (_jspx_th_c_if_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
                        return;
                      }
                      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
                      out.write("\n\n\t\t\t\t\t\t\t");

							}
							
                      out.write("\n\n\t\t\t\t\t\t");
                      int evalDoAfterBody = _jspx_th_aui_select_0.doAfterBody();
                      if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                        break;
                    } while (true);
                    if (_jspx_eval_aui_select_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                      out = _jspx_page_context.popBody();
                  }
                  if (_jspx_th_aui_select_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_aui_select_name.reuse(_jspx_th_aui_select_0);
                    return;
                  }
                  _jspx_tagPool_aui_select_name.reuse(_jspx_th_aui_select_0);
                  out.write("\n\n\t\t\t\t\t\t");
                  //  aui:input
                  com.liferay.taglib.aui.InputTag _jspx_th_aui_input_4 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_label_nobody.get(com.liferay.taglib.aui.InputTag.class);
                  _jspx_th_aui_input_4.setPageContext(_jspx_page_context);
                  _jspx_th_aui_input_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_fieldset_0);
                  _jspx_th_aui_input_4.setLabel("enable-report-inappropriate-content");
                  _jspx_th_aui_input_4.setName("preferences--enableFlags--");
                  _jspx_th_aui_input_4.setType("checkbox");
                  _jspx_th_aui_input_4.setValue( enableFlags );
                  int _jspx_eval_aui_input_4 = _jspx_th_aui_input_4.doStartTag();
                  if (_jspx_th_aui_input_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_aui_input_value_type_name_label_nobody.reuse(_jspx_th_aui_input_4);
                    return;
                  }
                  _jspx_tagPool_aui_input_value_type_name_label_nobody.reuse(_jspx_th_aui_input_4);
                  out.write("\n\n\t\t\t\t\t\t");
                  //  aui:input
                  com.liferay.taglib.aui.InputTag _jspx_th_aui_input_5 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
                  _jspx_th_aui_input_5.setPageContext(_jspx_page_context);
                  _jspx_th_aui_input_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_fieldset_0);
                  _jspx_th_aui_input_5.setName("preferences--enableRatings--");
                  _jspx_th_aui_input_5.setType("checkbox");
                  _jspx_th_aui_input_5.setValue( enableRatings );
                  int _jspx_eval_aui_input_5 = _jspx_th_aui_input_5.doStartTag();
                  if (_jspx_th_aui_input_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_5);
                    return;
                  }
                  _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_5);
                  out.write("\n\n\t\t\t\t\t\t");
                  //  aui:input
                  com.liferay.taglib.aui.InputTag _jspx_th_aui_input_6 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
                  _jspx_th_aui_input_6.setPageContext(_jspx_page_context);
                  _jspx_th_aui_input_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_fieldset_0);
                  _jspx_th_aui_input_6.setName("preferences--threadAsQuestionByDefault--");
                  _jspx_th_aui_input_6.setType("checkbox");
                  _jspx_th_aui_input_6.setValue( threadAsQuestionByDefault );
                  int _jspx_eval_aui_input_6 = _jspx_th_aui_input_6.doStartTag();
                  if (_jspx_th_aui_input_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_6);
                    return;
                  }
                  _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_6);
                  out.write("\n\n\t\t\t\t\t\t");
                  //  aui:select
                  com.liferay.taglib.aui.SelectTag _jspx_th_aui_select_1 = (com.liferay.taglib.aui.SelectTag) _jspx_tagPool_aui_select_value_name_label.get(com.liferay.taglib.aui.SelectTag.class);
                  _jspx_th_aui_select_1.setPageContext(_jspx_page_context);
                  _jspx_th_aui_select_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_fieldset_0);
                  _jspx_th_aui_select_1.setLabel("show-recent-posts-from-last");
                  _jspx_th_aui_select_1.setName("preferences--recentPostsDateOffset--");
                  _jspx_th_aui_select_1.setValue( mbGroupServiceSettings.getRecentPostsDateOffset() );
                  int _jspx_eval_aui_select_1 = _jspx_th_aui_select_1.doStartTag();
                  if (_jspx_eval_aui_select_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    if (_jspx_eval_aui_select_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                      out = _jspx_page_context.pushBody();
                      _jspx_th_aui_select_1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                      _jspx_th_aui_select_1.doInitBody();
                    }
                    do {
                      out.write("\n\t\t\t\t\t\t\t");
                      //  aui:option
                      com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_1 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_value_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
                      _jspx_th_aui_option_1.setPageContext(_jspx_page_context);
                      _jspx_th_aui_option_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_1);
                      _jspx_th_aui_option_1.setLabel( LanguageUtil.format(request, "x-hours", "24", false) );
                      _jspx_th_aui_option_1.setValue(new String("1"));
                      int _jspx_eval_aui_option_1 = _jspx_th_aui_option_1.doStartTag();
                      if (_jspx_th_aui_option_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_aui_option_value_label_nobody.reuse(_jspx_th_aui_option_1);
                        return;
                      }
                      _jspx_tagPool_aui_option_value_label_nobody.reuse(_jspx_th_aui_option_1);
                      out.write("\n\t\t\t\t\t\t\t");
                      //  aui:option
                      com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_2 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_value_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
                      _jspx_th_aui_option_2.setPageContext(_jspx_page_context);
                      _jspx_th_aui_option_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_1);
                      _jspx_th_aui_option_2.setLabel( LanguageUtil.format(request, "x-days", "7", false) );
                      _jspx_th_aui_option_2.setValue(new String("7"));
                      int _jspx_eval_aui_option_2 = _jspx_th_aui_option_2.doStartTag();
                      if (_jspx_th_aui_option_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_aui_option_value_label_nobody.reuse(_jspx_th_aui_option_2);
                        return;
                      }
                      _jspx_tagPool_aui_option_value_label_nobody.reuse(_jspx_th_aui_option_2);
                      out.write("\n\t\t\t\t\t\t\t");
                      //  aui:option
                      com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_3 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_value_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
                      _jspx_th_aui_option_3.setPageContext(_jspx_page_context);
                      _jspx_th_aui_option_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_1);
                      _jspx_th_aui_option_3.setLabel( LanguageUtil.format(request, "x-days", "30", false) );
                      _jspx_th_aui_option_3.setValue(new String("30"));
                      int _jspx_eval_aui_option_3 = _jspx_th_aui_option_3.doStartTag();
                      if (_jspx_th_aui_option_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_aui_option_value_label_nobody.reuse(_jspx_th_aui_option_3);
                        return;
                      }
                      _jspx_tagPool_aui_option_value_label_nobody.reuse(_jspx_th_aui_option_3);
                      out.write("\n\t\t\t\t\t\t\t");
                      //  aui:option
                      com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_4 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_value_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
                      _jspx_th_aui_option_4.setPageContext(_jspx_page_context);
                      _jspx_th_aui_option_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_1);
                      _jspx_th_aui_option_4.setLabel( LanguageUtil.format(request, "x-days", "365", false) );
                      _jspx_th_aui_option_4.setValue(new String("365"));
                      int _jspx_eval_aui_option_4 = _jspx_th_aui_option_4.doStartTag();
                      if (_jspx_th_aui_option_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_aui_option_value_label_nobody.reuse(_jspx_th_aui_option_4);
                        return;
                      }
                      _jspx_tagPool_aui_option_value_label_nobody.reuse(_jspx_th_aui_option_4);
                      out.write("\n\t\t\t\t\t\t");
                      int evalDoAfterBody = _jspx_th_aui_select_1.doAfterBody();
                      if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                        break;
                    } while (true);
                    if (_jspx_eval_aui_select_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                      out = _jspx_page_context.popBody();
                  }
                  if (_jspx_th_aui_select_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_aui_select_value_name_label.reuse(_jspx_th_aui_select_1);
                    return;
                  }
                  _jspx_tagPool_aui_select_value_name_label.reuse(_jspx_th_aui_select_1);
                  out.write("\n\t\t\t\t\t");
                }
                if (_jspx_th_liferay$1frontend_fieldset_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_liferay$1frontend_fieldset.reuse(_jspx_th_liferay$1frontend_fieldset_0);
                  return;
                }
                _jspx_tagPool_liferay$1frontend_fieldset.reuse(_jspx_th_liferay$1frontend_fieldset_0);
                out.write("\n\t\t\t\t");
              }
              if (_jspx_th_liferay$1frontend_fieldset$1group_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_liferay$1frontend_fieldset$1group.reuse(_jspx_th_liferay$1frontend_fieldset$1group_0);
                return;
              }
              _jspx_tagPool_liferay$1frontend_fieldset$1group.reuse(_jspx_th_liferay$1frontend_fieldset$1group_0);
              out.write("\n\t\t\t");
            }
            if (_jspx_th_liferay$1ui_section_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_liferay$1ui_section.reuse(_jspx_th_liferay$1ui_section_0);
              return;
            }
            _jspx_tagPool_liferay$1ui_section.reuse(_jspx_th_liferay$1ui_section_0);
            out.write("\n\n\t\t\t");
            //  liferay-ui:section
            com.liferay.taglib.ui.SectionTag _jspx_th_liferay$1ui_section_1 = (com.liferay.taglib.ui.SectionTag) _jspx_tagPool_liferay$1ui_section.get(com.liferay.taglib.ui.SectionTag.class);
            _jspx_th_liferay$1ui_section_1.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1ui_section_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_tabs_0);
            int _jspx_eval_liferay$1ui_section_1 = _jspx_th_liferay$1ui_section_1.doStartTag();
            if (_jspx_eval_liferay$1ui_section_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              java.lang.String sectionParam = null;
              java.lang.String sectionName = null;
              java.lang.Boolean sectionSelected = null;
              java.lang.String sectionScroll = null;
              java.lang.String sectionRedirectParams = null;
              sectionParam = (java.lang.String) _jspx_page_context.findAttribute("sectionParam");
              sectionName = (java.lang.String) _jspx_page_context.findAttribute("sectionName");
              sectionSelected = (java.lang.Boolean) _jspx_page_context.findAttribute("sectionSelected");
              sectionScroll = (java.lang.String) _jspx_page_context.findAttribute("sectionScroll");
              sectionRedirectParams = (java.lang.String) _jspx_page_context.findAttribute("sectionRedirectParams");
              out.write("\n\t\t\t\t");
              //  liferay-frontend:fieldset-group
              com.liferay.frontend.taglib.servlet.taglib.FieldsetGroupTag _jspx_th_liferay$1frontend_fieldset$1group_1 = (com.liferay.frontend.taglib.servlet.taglib.FieldsetGroupTag) _jspx_tagPool_liferay$1frontend_fieldset$1group.get(com.liferay.frontend.taglib.servlet.taglib.FieldsetGroupTag.class);
              _jspx_th_liferay$1frontend_fieldset$1group_1.setPageContext(_jspx_page_context);
              _jspx_th_liferay$1frontend_fieldset$1group_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_section_1);
              int _jspx_eval_liferay$1frontend_fieldset$1group_1 = _jspx_th_liferay$1frontend_fieldset$1group_1.doStartTag();
              if (_jspx_eval_liferay$1frontend_fieldset$1group_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\t\t\t\t\t");
                //  liferay-frontend:fieldset
                com.liferay.frontend.taglib.servlet.taglib.FieldsetTag _jspx_th_liferay$1frontend_fieldset_1 = (com.liferay.frontend.taglib.servlet.taglib.FieldsetTag) _jspx_tagPool_liferay$1frontend_fieldset.get(com.liferay.frontend.taglib.servlet.taglib.FieldsetTag.class);
                _jspx_th_liferay$1frontend_fieldset_1.setPageContext(_jspx_page_context);
                _jspx_th_liferay$1frontend_fieldset_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_fieldset$1group_1);
                int _jspx_eval_liferay$1frontend_fieldset_1 = _jspx_th_liferay$1frontend_fieldset_1.doStartTag();
                if (_jspx_eval_liferay$1frontend_fieldset_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  out.write("\n\t\t\t\t\t\t");
                  //  aui:input
                  com.liferay.taglib.aui.InputTag _jspx_th_aui_input_7 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_name_label_cssClass_nobody.get(com.liferay.taglib.aui.InputTag.class);
                  _jspx_th_aui_input_7.setPageContext(_jspx_page_context);
                  _jspx_th_aui_input_7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_fieldset_1);
                  _jspx_th_aui_input_7.setCssClass("lfr-input-text-container");
                  _jspx_th_aui_input_7.setLabel("name");
                  _jspx_th_aui_input_7.setName("preferences--emailFromName--");
                  _jspx_th_aui_input_7.setValue( mbGroupServiceSettings.getEmailFromName() );
                  int _jspx_eval_aui_input_7 = _jspx_th_aui_input_7.doStartTag();
                  if (_jspx_th_aui_input_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_aui_input_value_name_label_cssClass_nobody.reuse(_jspx_th_aui_input_7);
                    return;
                  }
                  _jspx_tagPool_aui_input_value_name_label_cssClass_nobody.reuse(_jspx_th_aui_input_7);
                  out.write("\n\n\t\t\t\t\t\t");
                  //  aui:input
                  com.liferay.taglib.aui.InputTag _jspx_th_aui_input_8 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_name_label_cssClass_nobody.get(com.liferay.taglib.aui.InputTag.class);
                  _jspx_th_aui_input_8.setPageContext(_jspx_page_context);
                  _jspx_th_aui_input_8.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_fieldset_1);
                  _jspx_th_aui_input_8.setCssClass("lfr-input-text-container");
                  _jspx_th_aui_input_8.setLabel("address");
                  _jspx_th_aui_input_8.setName("preferences--emailFromAddress--");
                  _jspx_th_aui_input_8.setValue( mbGroupServiceSettings.getEmailFromAddress() );
                  int _jspx_eval_aui_input_8 = _jspx_th_aui_input_8.doStartTag();
                  if (_jspx_th_aui_input_8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_aui_input_value_name_label_cssClass_nobody.reuse(_jspx_th_aui_input_8);
                    return;
                  }
                  _jspx_tagPool_aui_input_value_name_label_cssClass_nobody.reuse(_jspx_th_aui_input_8);
                  out.write("\n\n\t\t\t\t\t\t");
                  //  aui:input
                  com.liferay.taglib.aui.InputTag _jspx_th_aui_input_9 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_label_nobody.get(com.liferay.taglib.aui.InputTag.class);
                  _jspx_th_aui_input_9.setPageContext(_jspx_page_context);
                  _jspx_th_aui_input_9.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_fieldset_1);
                  _jspx_th_aui_input_9.setLabel("html-format");
                  _jspx_th_aui_input_9.setName("preferences--emailHtmlFormat--");
                  _jspx_th_aui_input_9.setType("checkbox");
                  _jspx_th_aui_input_9.setValue( mbGroupServiceSettings.isEmailHtmlFormat() );
                  int _jspx_eval_aui_input_9 = _jspx_th_aui_input_9.doStartTag();
                  if (_jspx_th_aui_input_9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_aui_input_value_type_name_label_nobody.reuse(_jspx_th_aui_input_9);
                    return;
                  }
                  _jspx_tagPool_aui_input_value_type_name_label_nobody.reuse(_jspx_th_aui_input_9);
                  out.write("\n\t\t\t\t\t");
                }
                if (_jspx_th_liferay$1frontend_fieldset_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_liferay$1frontend_fieldset.reuse(_jspx_th_liferay$1frontend_fieldset_1);
                  return;
                }
                _jspx_tagPool_liferay$1frontend_fieldset.reuse(_jspx_th_liferay$1frontend_fieldset_1);
                out.write("\n\n\t\t\t\t\t");
                //  liferay-frontend:fieldset
                com.liferay.frontend.taglib.servlet.taglib.FieldsetTag _jspx_th_liferay$1frontend_fieldset_2 = (com.liferay.frontend.taglib.servlet.taglib.FieldsetTag) _jspx_tagPool_liferay$1frontend_fieldset_label_collapsible_collapsed.get(com.liferay.frontend.taglib.servlet.taglib.FieldsetTag.class);
                _jspx_th_liferay$1frontend_fieldset_2.setPageContext(_jspx_page_context);
                _jspx_th_liferay$1frontend_fieldset_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_fieldset$1group_1);
                _jspx_th_liferay$1frontend_fieldset_2.setCollapsed( true );
                _jspx_th_liferay$1frontend_fieldset_2.setCollapsible( true );
                _jspx_th_liferay$1frontend_fieldset_2.setLabel("definition-of-terms");
                int _jspx_eval_liferay$1frontend_fieldset_2 = _jspx_th_liferay$1frontend_fieldset_2.doStartTag();
                if (_jspx_eval_liferay$1frontend_fieldset_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  out.write("\n\t\t\t\t\t\t<dl>\n\n\t\t\t\t\t\t\t");

							Map<String, String> emailDefinitionTerms = MBMailUtil.getEmailFromDefinitionTerms(renderRequest);

							for (Map.Entry<String, String> entry : emailDefinitionTerms.entrySet()) {
							
                  out.write("\n\n\t\t\t\t\t\t\t\t<dt>\n\t\t\t\t\t\t\t\t\t");
                  out.print( entry.getKey() );
                  out.write("\n\t\t\t\t\t\t\t\t</dt>\n\t\t\t\t\t\t\t\t<dd>\n\t\t\t\t\t\t\t\t\t");
                  out.print( entry.getValue() );
                  out.write("\n\t\t\t\t\t\t\t\t</dd>\n\n\t\t\t\t\t\t\t");

							}
							
                  out.write("\n\n\t\t\t\t\t\t</dl>\n\t\t\t\t\t");
                }
                if (_jspx_th_liferay$1frontend_fieldset_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_liferay$1frontend_fieldset_label_collapsible_collapsed.reuse(_jspx_th_liferay$1frontend_fieldset_2);
                  return;
                }
                _jspx_tagPool_liferay$1frontend_fieldset_label_collapsible_collapsed.reuse(_jspx_th_liferay$1frontend_fieldset_2);
                out.write("\n\t\t\t\t");
              }
              if (_jspx_th_liferay$1frontend_fieldset$1group_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_liferay$1frontend_fieldset$1group.reuse(_jspx_th_liferay$1frontend_fieldset$1group_1);
                return;
              }
              _jspx_tagPool_liferay$1frontend_fieldset$1group.reuse(_jspx_th_liferay$1frontend_fieldset$1group_1);
              out.write("\n\t\t\t");
            }
            if (_jspx_th_liferay$1ui_section_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_liferay$1ui_section.reuse(_jspx_th_liferay$1ui_section_1);
              return;
            }
            _jspx_tagPool_liferay$1ui_section.reuse(_jspx_th_liferay$1ui_section_1);
            out.write("\n\n\t\t\t");

			Map<String, String> emailDefinitionTerms = MBMailUtil.getEmailDefinitionTerms(renderRequest, mbGroupServiceSettings.getEmailFromAddress(), mbGroupServiceSettings.getEmailFromName());
			
            out.write("\n\n\t\t\t");
            //  liferay-ui:section
            com.liferay.taglib.ui.SectionTag _jspx_th_liferay$1ui_section_2 = (com.liferay.taglib.ui.SectionTag) _jspx_tagPool_liferay$1ui_section.get(com.liferay.taglib.ui.SectionTag.class);
            _jspx_th_liferay$1ui_section_2.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1ui_section_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_tabs_0);
            int _jspx_eval_liferay$1ui_section_2 = _jspx_th_liferay$1ui_section_2.doStartTag();
            if (_jspx_eval_liferay$1ui_section_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              java.lang.String sectionParam = null;
              java.lang.String sectionName = null;
              java.lang.Boolean sectionSelected = null;
              java.lang.String sectionScroll = null;
              java.lang.String sectionRedirectParams = null;
              sectionParam = (java.lang.String) _jspx_page_context.findAttribute("sectionParam");
              sectionName = (java.lang.String) _jspx_page_context.findAttribute("sectionName");
              sectionSelected = (java.lang.Boolean) _jspx_page_context.findAttribute("sectionSelected");
              sectionScroll = (java.lang.String) _jspx_page_context.findAttribute("sectionScroll");
              sectionRedirectParams = (java.lang.String) _jspx_page_context.findAttribute("sectionRedirectParams");
              out.write("\n\t\t\t\t");
              //  liferay-frontend:fieldset-group
              com.liferay.frontend.taglib.servlet.taglib.FieldsetGroupTag _jspx_th_liferay$1frontend_fieldset$1group_2 = (com.liferay.frontend.taglib.servlet.taglib.FieldsetGroupTag) _jspx_tagPool_liferay$1frontend_fieldset$1group.get(com.liferay.frontend.taglib.servlet.taglib.FieldsetGroupTag.class);
              _jspx_th_liferay$1frontend_fieldset$1group_2.setPageContext(_jspx_page_context);
              _jspx_th_liferay$1frontend_fieldset$1group_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_section_2);
              int _jspx_eval_liferay$1frontend_fieldset$1group_2 = _jspx_th_liferay$1frontend_fieldset$1group_2.doStartTag();
              if (_jspx_eval_liferay$1frontend_fieldset$1group_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\t\t\t\t\t");
                //  liferay-frontend:email-notification-settings
                com.liferay.frontend.taglib.servlet.taglib.EmailNotificationSettingsTag _jspx_th_liferay$1frontend_email$1notification$1settings_0 = (com.liferay.frontend.taglib.servlet.taglib.EmailNotificationSettingsTag) _jspx_tagPool_liferay$1frontend_email$1notification$1settings_emailSubject_emailParam_emailEnabled_emailDefinitionTerms_emailBody_nobody.get(com.liferay.frontend.taglib.servlet.taglib.EmailNotificationSettingsTag.class);
                _jspx_th_liferay$1frontend_email$1notification$1settings_0.setPageContext(_jspx_page_context);
                _jspx_th_liferay$1frontend_email$1notification$1settings_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_fieldset$1group_2);
                _jspx_th_liferay$1frontend_email$1notification$1settings_0.setEmailBody( mbGroupServiceSettings.getEmailMessageAddedBodyXml() );
                _jspx_th_liferay$1frontend_email$1notification$1settings_0.setEmailDefinitionTerms( emailDefinitionTerms );
                _jspx_th_liferay$1frontend_email$1notification$1settings_0.setEmailEnabled( mbGroupServiceSettings.isEmailMessageAddedEnabled() );
                _jspx_th_liferay$1frontend_email$1notification$1settings_0.setEmailParam("emailMessageAdded");
                _jspx_th_liferay$1frontend_email$1notification$1settings_0.setEmailSubject( mbGroupServiceSettings.getEmailMessageAddedSubjectXml() );
                int _jspx_eval_liferay$1frontend_email$1notification$1settings_0 = _jspx_th_liferay$1frontend_email$1notification$1settings_0.doStartTag();
                if (_jspx_th_liferay$1frontend_email$1notification$1settings_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_liferay$1frontend_email$1notification$1settings_emailSubject_emailParam_emailEnabled_emailDefinitionTerms_emailBody_nobody.reuse(_jspx_th_liferay$1frontend_email$1notification$1settings_0);
                  return;
                }
                _jspx_tagPool_liferay$1frontend_email$1notification$1settings_emailSubject_emailParam_emailEnabled_emailDefinitionTerms_emailBody_nobody.reuse(_jspx_th_liferay$1frontend_email$1notification$1settings_0);
                out.write("\n\t\t\t\t");
              }
              if (_jspx_th_liferay$1frontend_fieldset$1group_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_liferay$1frontend_fieldset$1group.reuse(_jspx_th_liferay$1frontend_fieldset$1group_2);
                return;
              }
              _jspx_tagPool_liferay$1frontend_fieldset$1group.reuse(_jspx_th_liferay$1frontend_fieldset$1group_2);
              out.write("\n\t\t\t");
            }
            if (_jspx_th_liferay$1ui_section_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_liferay$1ui_section.reuse(_jspx_th_liferay$1ui_section_2);
              return;
            }
            _jspx_tagPool_liferay$1ui_section.reuse(_jspx_th_liferay$1ui_section_2);
            out.write("\n\n\t\t\t");
            //  liferay-ui:section
            com.liferay.taglib.ui.SectionTag _jspx_th_liferay$1ui_section_3 = (com.liferay.taglib.ui.SectionTag) _jspx_tagPool_liferay$1ui_section.get(com.liferay.taglib.ui.SectionTag.class);
            _jspx_th_liferay$1ui_section_3.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1ui_section_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_tabs_0);
            int _jspx_eval_liferay$1ui_section_3 = _jspx_th_liferay$1ui_section_3.doStartTag();
            if (_jspx_eval_liferay$1ui_section_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              java.lang.String sectionParam = null;
              java.lang.String sectionName = null;
              java.lang.Boolean sectionSelected = null;
              java.lang.String sectionScroll = null;
              java.lang.String sectionRedirectParams = null;
              sectionParam = (java.lang.String) _jspx_page_context.findAttribute("sectionParam");
              sectionName = (java.lang.String) _jspx_page_context.findAttribute("sectionName");
              sectionSelected = (java.lang.Boolean) _jspx_page_context.findAttribute("sectionSelected");
              sectionScroll = (java.lang.String) _jspx_page_context.findAttribute("sectionScroll");
              sectionRedirectParams = (java.lang.String) _jspx_page_context.findAttribute("sectionRedirectParams");
              out.write("\n\t\t\t\t");
              //  liferay-frontend:fieldset-group
              com.liferay.frontend.taglib.servlet.taglib.FieldsetGroupTag _jspx_th_liferay$1frontend_fieldset$1group_3 = (com.liferay.frontend.taglib.servlet.taglib.FieldsetGroupTag) _jspx_tagPool_liferay$1frontend_fieldset$1group.get(com.liferay.frontend.taglib.servlet.taglib.FieldsetGroupTag.class);
              _jspx_th_liferay$1frontend_fieldset$1group_3.setPageContext(_jspx_page_context);
              _jspx_th_liferay$1frontend_fieldset$1group_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_section_3);
              int _jspx_eval_liferay$1frontend_fieldset$1group_3 = _jspx_th_liferay$1frontend_fieldset$1group_3.doStartTag();
              if (_jspx_eval_liferay$1frontend_fieldset$1group_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\t\t\t\t\t");
                //  liferay-frontend:email-notification-settings
                com.liferay.frontend.taglib.servlet.taglib.EmailNotificationSettingsTag _jspx_th_liferay$1frontend_email$1notification$1settings_1 = (com.liferay.frontend.taglib.servlet.taglib.EmailNotificationSettingsTag) _jspx_tagPool_liferay$1frontend_email$1notification$1settings_emailSubject_emailParam_emailEnabled_emailDefinitionTerms_emailBody_nobody.get(com.liferay.frontend.taglib.servlet.taglib.EmailNotificationSettingsTag.class);
                _jspx_th_liferay$1frontend_email$1notification$1settings_1.setPageContext(_jspx_page_context);
                _jspx_th_liferay$1frontend_email$1notification$1settings_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_fieldset$1group_3);
                _jspx_th_liferay$1frontend_email$1notification$1settings_1.setEmailBody( mbGroupServiceSettings.getEmailMessageUpdatedBodyXml() );
                _jspx_th_liferay$1frontend_email$1notification$1settings_1.setEmailDefinitionTerms( emailDefinitionTerms );
                _jspx_th_liferay$1frontend_email$1notification$1settings_1.setEmailEnabled( mbGroupServiceSettings.isEmailMessageUpdatedEnabled() );
                _jspx_th_liferay$1frontend_email$1notification$1settings_1.setEmailParam("emailMessageUpdated");
                _jspx_th_liferay$1frontend_email$1notification$1settings_1.setEmailSubject( mbGroupServiceSettings.getEmailMessageUpdatedSubjectXml() );
                int _jspx_eval_liferay$1frontend_email$1notification$1settings_1 = _jspx_th_liferay$1frontend_email$1notification$1settings_1.doStartTag();
                if (_jspx_th_liferay$1frontend_email$1notification$1settings_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_liferay$1frontend_email$1notification$1settings_emailSubject_emailParam_emailEnabled_emailDefinitionTerms_emailBody_nobody.reuse(_jspx_th_liferay$1frontend_email$1notification$1settings_1);
                  return;
                }
                _jspx_tagPool_liferay$1frontend_email$1notification$1settings_emailSubject_emailParam_emailEnabled_emailDefinitionTerms_emailBody_nobody.reuse(_jspx_th_liferay$1frontend_email$1notification$1settings_1);
                out.write("\n\t\t\t\t");
              }
              if (_jspx_th_liferay$1frontend_fieldset$1group_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_liferay$1frontend_fieldset$1group.reuse(_jspx_th_liferay$1frontend_fieldset$1group_3);
                return;
              }
              _jspx_tagPool_liferay$1frontend_fieldset$1group.reuse(_jspx_th_liferay$1frontend_fieldset$1group_3);
              out.write("\n\t\t\t");
            }
            if (_jspx_th_liferay$1ui_section_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_liferay$1ui_section.reuse(_jspx_th_liferay$1ui_section_3);
              return;
            }
            _jspx_tagPool_liferay$1ui_section.reuse(_jspx_th_liferay$1ui_section_3);
            out.write("\n\n\t\t\t");
            //  liferay-ui:section
            com.liferay.taglib.ui.SectionTag _jspx_th_liferay$1ui_section_4 = (com.liferay.taglib.ui.SectionTag) _jspx_tagPool_liferay$1ui_section.get(com.liferay.taglib.ui.SectionTag.class);
            _jspx_th_liferay$1ui_section_4.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1ui_section_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_tabs_0);
            int _jspx_eval_liferay$1ui_section_4 = _jspx_th_liferay$1ui_section_4.doStartTag();
            if (_jspx_eval_liferay$1ui_section_4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              java.lang.String sectionParam = null;
              java.lang.String sectionName = null;
              java.lang.Boolean sectionSelected = null;
              java.lang.String sectionScroll = null;
              java.lang.String sectionRedirectParams = null;
              sectionParam = (java.lang.String) _jspx_page_context.findAttribute("sectionParam");
              sectionName = (java.lang.String) _jspx_page_context.findAttribute("sectionName");
              sectionSelected = (java.lang.Boolean) _jspx_page_context.findAttribute("sectionSelected");
              sectionScroll = (java.lang.String) _jspx_page_context.findAttribute("sectionScroll");
              sectionRedirectParams = (java.lang.String) _jspx_page_context.findAttribute("sectionRedirectParams");
              out.write("\n\t\t\t\t<div class=\"alert alert-info\">\n\t\t\t\t\t");
              if (_jspx_meth_liferay$1ui_message_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1ui_section_4, _jspx_page_context))
                return;
              out.write("\n\t\t\t\t</div>\n\n\t\t\t\t");
              //  liferay-frontend:fieldset-group
              com.liferay.frontend.taglib.servlet.taglib.FieldsetGroupTag _jspx_th_liferay$1frontend_fieldset$1group_4 = (com.liferay.frontend.taglib.servlet.taglib.FieldsetGroupTag) _jspx_tagPool_liferay$1frontend_fieldset$1group.get(com.liferay.frontend.taglib.servlet.taglib.FieldsetGroupTag.class);
              _jspx_th_liferay$1frontend_fieldset$1group_4.setPageContext(_jspx_page_context);
              _jspx_th_liferay$1frontend_fieldset$1group_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_section_4);
              int _jspx_eval_liferay$1frontend_fieldset$1group_4 = _jspx_th_liferay$1frontend_fieldset$1group_4.doStartTag();
              if (_jspx_eval_liferay$1frontend_fieldset$1group_4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\t\t\t\t\t");
                //  liferay-frontend:fieldset
                com.liferay.frontend.taglib.servlet.taglib.FieldsetTag _jspx_th_liferay$1frontend_fieldset_3 = (com.liferay.frontend.taglib.servlet.taglib.FieldsetTag) _jspx_tagPool_liferay$1frontend_fieldset.get(com.liferay.frontend.taglib.servlet.taglib.FieldsetTag.class);
                _jspx_th_liferay$1frontend_fieldset_3.setPageContext(_jspx_page_context);
                _jspx_th_liferay$1frontend_fieldset_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_fieldset$1group_4);
                int _jspx_eval_liferay$1frontend_fieldset_3 = _jspx_th_liferay$1frontend_fieldset_3.doStartTag();
                if (_jspx_eval_liferay$1frontend_fieldset_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  out.write("\n\t\t\t\t\t\t<table class=\"lfr-table\">\n\t\t\t\t\t\t\t<tr>\n\t\t\t\t\t\t\t\t<td>\n\t\t\t\t\t\t\t\t\t");
                  //  aui:input
                  com.liferay.taglib.aui.InputTag _jspx_th_aui_input_10 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
                  _jspx_th_aui_input_10.setPageContext(_jspx_page_context);
                  _jspx_th_aui_input_10.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_fieldset_3);
                  _jspx_th_aui_input_10.setName("defaultLanguage");
                  _jspx_th_aui_input_10.setType("resource");
                  _jspx_th_aui_input_10.setValue( defaultLocale.getDisplayName(defaultLocale) );
                  int _jspx_eval_aui_input_10 = _jspx_th_aui_input_10.doStartTag();
                  if (_jspx_th_aui_input_10.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_10);
                    return;
                  }
                  _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_10);
                  out.write("\n\t\t\t\t\t\t\t\t</td>\n\t\t\t\t\t\t\t\t<td>\n\t\t\t\t\t\t\t\t\t");
                  //  aui:select
                  com.liferay.taglib.aui.SelectTag _jspx_th_aui_select_2 = (com.liferay.taglib.aui.SelectTag) _jspx_tagPool_aui_select_showEmptyOption_onChange_name_label.get(com.liferay.taglib.aui.SelectTag.class);
                  _jspx_th_aui_select_2.setPageContext(_jspx_page_context);
                  _jspx_th_aui_select_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_fieldset_3);
                  _jspx_th_aui_select_2.setLabel("localized-language");
                  _jspx_th_aui_select_2.setName("prioritiesLanguageId");
                  _jspx_th_aui_select_2.setOnChange( renderResponse.getNamespace() + "updatePrioritiesLanguage();" );
                  _jspx_th_aui_select_2.setShowEmptyOption( true );
                  int _jspx_eval_aui_select_2 = _jspx_th_aui_select_2.doStartTag();
                  if (_jspx_eval_aui_select_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    if (_jspx_eval_aui_select_2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                      out = _jspx_page_context.pushBody();
                      _jspx_th_aui_select_2.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                      _jspx_th_aui_select_2.doInitBody();
                    }
                    do {
                      out.write("\n\n\t\t\t\t\t\t\t\t\t\t");

										for (Locale curLocale : locales) {
											if (curLocale.equals(defaultLocale)) {
												continue;
											}
										
                      out.write("\n\n\t\t\t\t\t\t\t\t\t\t\t");
                      //  aui:option
                      com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_5 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_value_selected_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
                      _jspx_th_aui_option_5.setPageContext(_jspx_page_context);
                      _jspx_th_aui_option_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_2);
                      _jspx_th_aui_option_5.setLabel( curLocale.getDisplayName(locale) );
                      _jspx_th_aui_option_5.setSelected( currentLanguageId.equals(LocaleUtil.toLanguageId(curLocale)) );
                      _jspx_th_aui_option_5.setValue( LocaleUtil.toLanguageId(curLocale) );
                      int _jspx_eval_aui_option_5 = _jspx_th_aui_option_5.doStartTag();
                      if (_jspx_th_aui_option_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_aui_option_value_selected_label_nobody.reuse(_jspx_th_aui_option_5);
                        return;
                      }
                      _jspx_tagPool_aui_option_value_selected_label_nobody.reuse(_jspx_th_aui_option_5);
                      out.write("\n\n\t\t\t\t\t\t\t\t\t\t");

										}
										
                      out.write("\n\n\t\t\t\t\t\t\t\t\t");
                      int evalDoAfterBody = _jspx_th_aui_select_2.doAfterBody();
                      if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                        break;
                    } while (true);
                    if (_jspx_eval_aui_select_2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                      out = _jspx_page_context.popBody();
                  }
                  if (_jspx_th_aui_select_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_aui_select_showEmptyOption_onChange_name_label.reuse(_jspx_th_aui_select_2);
                    return;
                  }
                  _jspx_tagPool_aui_select_showEmptyOption_onChange_name_label.reuse(_jspx_th_aui_select_2);
                  out.write("\n\t\t\t\t\t\t\t\t</td>\n\t\t\t\t\t\t\t</tr>\n\t\t\t\t\t\t\t<tr>\n\t\t\t\t\t\t\t\t<td colspan=\"2\">\n\t\t\t\t\t\t\t\t\t<br />\n\t\t\t\t\t\t\t\t</td>\n\t\t\t\t\t\t\t</tr>\n\t\t\t\t\t\t\t<tr>\n\t\t\t\t\t\t\t\t<td>\n\t\t\t\t\t\t\t\t\t<table class=\"lfr-table\">\n\t\t\t\t\t\t\t\t\t\t<tr>\n\t\t\t\t\t\t\t\t\t\t\t<td class=\"lfr-label\">\n\t\t\t\t\t\t\t\t\t\t\t\t");
                  if (_jspx_meth_liferay$1ui_message_1((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1frontend_fieldset_3, _jspx_page_context))
                    return;
                  out.write("\n\t\t\t\t\t\t\t\t\t\t\t</td>\n\t\t\t\t\t\t\t\t\t\t\t<td class=\"lfr-label\">\n\t\t\t\t\t\t\t\t\t\t\t\t");
                  if (_jspx_meth_liferay$1ui_message_2((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1frontend_fieldset_3, _jspx_page_context))
                    return;
                  out.write("\n\t\t\t\t\t\t\t\t\t\t\t</td>\n\t\t\t\t\t\t\t\t\t\t\t<td class=\"lfr-label\">\n\t\t\t\t\t\t\t\t\t\t\t\t");
                  if (_jspx_meth_liferay$1ui_message_3((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1frontend_fieldset_3, _jspx_page_context))
                    return;
                  out.write("\n\t\t\t\t\t\t\t\t\t\t\t</td>\n\t\t\t\t\t\t\t\t\t\t</tr>\n\n\t\t\t\t\t\t\t\t\t\t");

										priorities = mbGroupServiceSettings.getPriorities(defaultLanguageId);

										for (int i = 0; i < 10; i++) {
											String name = StringPool.BLANK;
											String image = StringPool.BLANK;
											String value = StringPool.BLANK;

											if (priorities.length > i) {
												String[] priority = StringUtil.split(priorities[i], StringPool.PIPE);

												try {
													name = priority[0];
													image = priority[1];
													value = priority[2];
												}
												catch (Exception e) {
												}

												if (Validator.isNull(name) && Validator.isNull(image)) {
													value = StringPool.BLANK;
												}
											}
										
                  out.write("\n\n\t\t\t\t\t\t\t\t\t\t\t<tr>\n\t\t\t\t\t\t\t\t\t\t\t\t<td>\n\t\t\t\t\t\t\t\t\t\t\t\t\t");
                  //  aui:input
                  com.liferay.taglib.aui.InputTag _jspx_th_aui_input_11 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_title_size_name_label_nobody.get(com.liferay.taglib.aui.InputTag.class);
                  _jspx_th_aui_input_11.setPageContext(_jspx_page_context);
                  _jspx_th_aui_input_11.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_fieldset_3);
                  _jspx_th_aui_input_11.setLabel("");
                  _jspx_th_aui_input_11.setName( "priorityName" + i + "_" + defaultLanguageId );
                  _jspx_th_aui_input_11.setDynamicAttribute(null, "size", new String("15"));
                  _jspx_th_aui_input_11.setTitle("priority-name");
                  _jspx_th_aui_input_11.setValue( name );
                  int _jspx_eval_aui_input_11 = _jspx_th_aui_input_11.doStartTag();
                  if (_jspx_th_aui_input_11.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_aui_input_value_title_size_name_label_nobody.reuse(_jspx_th_aui_input_11);
                    return;
                  }
                  _jspx_tagPool_aui_input_value_title_size_name_label_nobody.reuse(_jspx_th_aui_input_11);
                  out.write("\n\t\t\t\t\t\t\t\t\t\t\t\t</td>\n\t\t\t\t\t\t\t\t\t\t\t\t<td>\n\t\t\t\t\t\t\t\t\t\t\t\t\t");
                  //  aui:input
                  com.liferay.taglib.aui.InputTag _jspx_th_aui_input_12 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_title_size_name_label_nobody.get(com.liferay.taglib.aui.InputTag.class);
                  _jspx_th_aui_input_12.setPageContext(_jspx_page_context);
                  _jspx_th_aui_input_12.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_fieldset_3);
                  _jspx_th_aui_input_12.setLabel("");
                  _jspx_th_aui_input_12.setName( "priorityImage" + i + "_" + defaultLanguageId );
                  _jspx_th_aui_input_12.setDynamicAttribute(null, "size", new String("40"));
                  _jspx_th_aui_input_12.setTitle("priority-image");
                  _jspx_th_aui_input_12.setValue( image );
                  int _jspx_eval_aui_input_12 = _jspx_th_aui_input_12.doStartTag();
                  if (_jspx_th_aui_input_12.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_aui_input_value_title_size_name_label_nobody.reuse(_jspx_th_aui_input_12);
                    return;
                  }
                  _jspx_tagPool_aui_input_value_title_size_name_label_nobody.reuse(_jspx_th_aui_input_12);
                  out.write("\n\t\t\t\t\t\t\t\t\t\t\t\t</td>\n\t\t\t\t\t\t\t\t\t\t\t\t<td>\n\t\t\t\t\t\t\t\t\t\t\t\t\t");
                  //  aui:input
                  com.liferay.taglib.aui.InputTag _jspx_th_aui_input_13 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_title_size_name_label_nobody.get(com.liferay.taglib.aui.InputTag.class);
                  _jspx_th_aui_input_13.setPageContext(_jspx_page_context);
                  _jspx_th_aui_input_13.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_fieldset_3);
                  _jspx_th_aui_input_13.setLabel("");
                  _jspx_th_aui_input_13.setName( "priorityValue" + i + "_" + defaultLanguageId );
                  _jspx_th_aui_input_13.setDynamicAttribute(null, "size", new String("4"));
                  _jspx_th_aui_input_13.setTitle("priority-value");
                  _jspx_th_aui_input_13.setValue( value );
                  int _jspx_eval_aui_input_13 = _jspx_th_aui_input_13.doStartTag();
                  if (_jspx_th_aui_input_13.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_aui_input_value_title_size_name_label_nobody.reuse(_jspx_th_aui_input_13);
                    return;
                  }
                  _jspx_tagPool_aui_input_value_title_size_name_label_nobody.reuse(_jspx_th_aui_input_13);
                  out.write("\n\t\t\t\t\t\t\t\t\t\t\t\t</td>\n\t\t\t\t\t\t\t\t\t\t\t</tr>\n\n\t\t\t\t\t\t\t\t\t\t");

										}
										
                  out.write("\n\n\t\t\t\t\t\t\t\t\t</table>\n\t\t\t\t\t\t\t\t</td>\n\t\t\t\t\t\t\t\t<td>\n\t\t\t\t\t\t\t\t\t<table class=\"");
                  out.print( (currentLocale.equals(defaultLocale) ? "hide" : "") + " lfr-table" );
                  out.write("\" id=\"");
                  if (_jspx_meth_portlet_namespace_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1frontend_fieldset_3, _jspx_page_context))
                    return;
                  out.write("localized-priorities-table\">\n\t\t\t\t\t\t\t\t\t\t<tr>\n\t\t\t\t\t\t\t\t\t\t\t<td class=\"lfr-label\">\n\t\t\t\t\t\t\t\t\t\t\t\t");
                  if (_jspx_meth_liferay$1ui_message_4((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1frontend_fieldset_3, _jspx_page_context))
                    return;
                  out.write("\n\t\t\t\t\t\t\t\t\t\t\t</td>\n\t\t\t\t\t\t\t\t\t\t\t<td class=\"lfr-label\">\n\t\t\t\t\t\t\t\t\t\t\t\t");
                  if (_jspx_meth_liferay$1ui_message_5((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1frontend_fieldset_3, _jspx_page_context))
                    return;
                  out.write("\n\t\t\t\t\t\t\t\t\t\t\t</td>\n\t\t\t\t\t\t\t\t\t\t\t<td class=\"lfr-label\">\n\t\t\t\t\t\t\t\t\t\t\t\t");
                  if (_jspx_meth_liferay$1ui_message_6((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1frontend_fieldset_3, _jspx_page_context))
                    return;
                  out.write("\n\t\t\t\t\t\t\t\t\t\t\t</td>\n\t\t\t\t\t\t\t\t\t\t</tr>\n\n\t\t\t\t\t\t\t\t\t\t");

										for (int i = 0; i < 10; i++) {
										
                  out.write("\n\n\t\t\t\t\t\t\t\t\t\t\t<tr>\n\t\t\t\t\t\t\t\t\t\t\t\t<td>\n\t\t\t\t\t\t\t\t\t\t\t\t\t");
                  //  aui:input
                  com.liferay.taglib.aui.InputTag _jspx_th_aui_input_14 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_title_size_onChange_name_label_nobody.get(com.liferay.taglib.aui.InputTag.class);
                  _jspx_th_aui_input_14.setPageContext(_jspx_page_context);
                  _jspx_th_aui_input_14.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_fieldset_3);
                  _jspx_th_aui_input_14.setLabel("");
                  _jspx_th_aui_input_14.setName( "priorityName" + i + "_temp" );
                  _jspx_th_aui_input_14.setOnChange( renderResponse.getNamespace() + "onPrioritiesChanged();" );
                  _jspx_th_aui_input_14.setDynamicAttribute(null, "size", new String("15"));
                  _jspx_th_aui_input_14.setTitle("priority-name");
                  int _jspx_eval_aui_input_14 = _jspx_th_aui_input_14.doStartTag();
                  if (_jspx_th_aui_input_14.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_aui_input_title_size_onChange_name_label_nobody.reuse(_jspx_th_aui_input_14);
                    return;
                  }
                  _jspx_tagPool_aui_input_title_size_onChange_name_label_nobody.reuse(_jspx_th_aui_input_14);
                  out.write("\n\t\t\t\t\t\t\t\t\t\t\t\t</td>\n\t\t\t\t\t\t\t\t\t\t\t\t<td>\n\t\t\t\t\t\t\t\t\t\t\t\t\t");
                  //  aui:input
                  com.liferay.taglib.aui.InputTag _jspx_th_aui_input_15 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_title_size_onChange_name_label_nobody.get(com.liferay.taglib.aui.InputTag.class);
                  _jspx_th_aui_input_15.setPageContext(_jspx_page_context);
                  _jspx_th_aui_input_15.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_fieldset_3);
                  _jspx_th_aui_input_15.setLabel("");
                  _jspx_th_aui_input_15.setName( "priorityImage" + i + "_temp" );
                  _jspx_th_aui_input_15.setOnChange( renderResponse.getNamespace() + "onPrioritiesChanged();" );
                  _jspx_th_aui_input_15.setDynamicAttribute(null, "size", new String("40"));
                  _jspx_th_aui_input_15.setTitle("priority-image");
                  int _jspx_eval_aui_input_15 = _jspx_th_aui_input_15.doStartTag();
                  if (_jspx_th_aui_input_15.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_aui_input_title_size_onChange_name_label_nobody.reuse(_jspx_th_aui_input_15);
                    return;
                  }
                  _jspx_tagPool_aui_input_title_size_onChange_name_label_nobody.reuse(_jspx_th_aui_input_15);
                  out.write("\n\t\t\t\t\t\t\t\t\t\t\t\t</td>\n\t\t\t\t\t\t\t\t\t\t\t\t<td>\n\t\t\t\t\t\t\t\t\t\t\t\t\t");
                  //  aui:input
                  com.liferay.taglib.aui.InputTag _jspx_th_aui_input_16 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_title_size_onChange_name_label_nobody.get(com.liferay.taglib.aui.InputTag.class);
                  _jspx_th_aui_input_16.setPageContext(_jspx_page_context);
                  _jspx_th_aui_input_16.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_fieldset_3);
                  _jspx_th_aui_input_16.setLabel("");
                  _jspx_th_aui_input_16.setName( "priorityValue" + i + "_temp" );
                  _jspx_th_aui_input_16.setOnChange( renderResponse.getNamespace() + "onPrioritiesChanged();" );
                  _jspx_th_aui_input_16.setDynamicAttribute(null, "size", new String("4"));
                  _jspx_th_aui_input_16.setTitle("priority-value");
                  int _jspx_eval_aui_input_16 = _jspx_th_aui_input_16.doStartTag();
                  if (_jspx_th_aui_input_16.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_aui_input_title_size_onChange_name_label_nobody.reuse(_jspx_th_aui_input_16);
                    return;
                  }
                  _jspx_tagPool_aui_input_title_size_onChange_name_label_nobody.reuse(_jspx_th_aui_input_16);
                  out.write("\n\t\t\t\t\t\t\t\t\t\t\t\t</td>\n\t\t\t\t\t\t\t\t\t\t\t</tr>\n\n\t\t\t\t\t\t\t\t\t\t");

										}
										
                  out.write("\n\n\t\t\t\t\t\t\t\t\t</table>\n\n\t\t\t\t\t\t\t\t\t");

									for (Locale curLocale : locales) {
										if (curLocale.equals(defaultLocale)) {
											continue;
										}

										String[] tempPriorities = mbGroupServiceSettings.getPriorities(LocaleUtil.toLanguageId(curLocale));

										for (int j = 0; j < 10; j++) {
											String name = StringPool.BLANK;
											String image = StringPool.BLANK;
											String value = StringPool.BLANK;

											if (tempPriorities.length > j) {
												String[] priority = StringUtil.split(tempPriorities[j], StringPool.PIPE);

												try {
													name = priority[0];
													image = priority[1];
													value = priority[2];
												}
												catch (Exception e) {
												}

												if (Validator.isNull(name) && Validator.isNull(image)) {
													value = StringPool.BLANK;
												}
											}
									
                  out.write("\n\n\t\t\t\t\t\t\t\t\t\t\t");
                  //  aui:input
                  com.liferay.taglib.aui.InputTag _jspx_th_aui_input_17 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
                  _jspx_th_aui_input_17.setPageContext(_jspx_page_context);
                  _jspx_th_aui_input_17.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_fieldset_3);
                  _jspx_th_aui_input_17.setName( "priorityName" + j + "_" + LocaleUtil.toLanguageId(curLocale) );
                  _jspx_th_aui_input_17.setType("hidden");
                  _jspx_th_aui_input_17.setValue( name );
                  int _jspx_eval_aui_input_17 = _jspx_th_aui_input_17.doStartTag();
                  if (_jspx_th_aui_input_17.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_17);
                    return;
                  }
                  _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_17);
                  out.write("\n\t\t\t\t\t\t\t\t\t\t\t");
                  //  aui:input
                  com.liferay.taglib.aui.InputTag _jspx_th_aui_input_18 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
                  _jspx_th_aui_input_18.setPageContext(_jspx_page_context);
                  _jspx_th_aui_input_18.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_fieldset_3);
                  _jspx_th_aui_input_18.setName( "priorityImage" + j + "_" + LocaleUtil.toLanguageId(curLocale) );
                  _jspx_th_aui_input_18.setType("hidden");
                  _jspx_th_aui_input_18.setValue( image );
                  int _jspx_eval_aui_input_18 = _jspx_th_aui_input_18.doStartTag();
                  if (_jspx_th_aui_input_18.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_18);
                    return;
                  }
                  _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_18);
                  out.write("\n\t\t\t\t\t\t\t\t\t\t\t");
                  //  aui:input
                  com.liferay.taglib.aui.InputTag _jspx_th_aui_input_19 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
                  _jspx_th_aui_input_19.setPageContext(_jspx_page_context);
                  _jspx_th_aui_input_19.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_fieldset_3);
                  _jspx_th_aui_input_19.setName( "priorityValue" + j + "_" + LocaleUtil.toLanguageId(curLocale) );
                  _jspx_th_aui_input_19.setType("hidden");
                  _jspx_th_aui_input_19.setValue( value );
                  int _jspx_eval_aui_input_19 = _jspx_th_aui_input_19.doStartTag();
                  if (_jspx_th_aui_input_19.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_19);
                    return;
                  }
                  _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_19);
                  out.write("\n\n\t\t\t\t\t\t\t\t\t");

										}
									}
									
                  out.write("\n\n\t\t\t\t\t\t\t\t</td>\n\t\t\t\t\t\t\t</tr>\n\t\t\t\t\t\t</table>\n\t\t\t\t\t");
                }
                if (_jspx_th_liferay$1frontend_fieldset_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_liferay$1frontend_fieldset.reuse(_jspx_th_liferay$1frontend_fieldset_3);
                  return;
                }
                _jspx_tagPool_liferay$1frontend_fieldset.reuse(_jspx_th_liferay$1frontend_fieldset_3);
                out.write("\n\t\t\t\t");
              }
              if (_jspx_th_liferay$1frontend_fieldset$1group_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_liferay$1frontend_fieldset$1group.reuse(_jspx_th_liferay$1frontend_fieldset$1group_4);
                return;
              }
              _jspx_tagPool_liferay$1frontend_fieldset$1group.reuse(_jspx_th_liferay$1frontend_fieldset$1group_4);
              out.write("\n\n\t\t\t\t");
              //  aui:script
              com.liferay.taglib.aui.ScriptTag _jspx_th_aui_script_0 = (com.liferay.taglib.aui.ScriptTag) _jspx_tagPool_aui_script.get(com.liferay.taglib.aui.ScriptTag.class);
              _jspx_th_aui_script_0.setPageContext(_jspx_page_context);
              _jspx_th_aui_script_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_section_4);
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
                  out.write("prioritiesChanged = false;\n\t\t\t\t\tvar ");
                  if (_jspx_meth_portlet_namespace_2((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_0, _jspx_page_context))
                    return;
                  out.write("prioritiesLastLanguageId = '");
                  out.print( currentLanguageId );
                  out.write("';\n\n\t\t\t\t\tfunction ");
                  if (_jspx_meth_portlet_namespace_3((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_0, _jspx_page_context))
                    return;
                  out.write("onPrioritiesChanged() {\n\t\t\t\t\t\t");
                  if (_jspx_meth_portlet_namespace_4((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_0, _jspx_page_context))
                    return;
                  out.write("prioritiesChanged = true;\n\t\t\t\t\t}\n\n\t\t\t\t\tfunction ");
                  if (_jspx_meth_portlet_namespace_5((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_0, _jspx_page_context))
                    return;
                  out.write("updatePrioritiesLanguage() {\n\t\t\t\t\t\tvar $ = AUI.$;\n\n\t\t\t\t\t\tvar form = $(document.");
                  if (_jspx_meth_portlet_namespace_6((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_0, _jspx_page_context))
                    return;
                  out.write("fm);\n\n\t\t\t\t\t\tif (");
                  if (_jspx_meth_portlet_namespace_7((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_0, _jspx_page_context))
                    return;
                  out.write("prioritiesChanged && (");
                  if (_jspx_meth_portlet_namespace_8((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_0, _jspx_page_context))
                    return;
                  out.write("prioritiesLastLanguageId != '");
                  out.print( defaultLanguageId );
                  out.write("')) {\n\t\t\t\t\t\t\tfor (var i = 0; i < 10; i++) {\n\t\t\t\t\t\t\t\tvar priorityImage = form.fm('priorityImage' + i + '_temp').val();\n\t\t\t\t\t\t\t\tvar priorityName = form.fm('priorityName' + i + '_temp').val();\n\t\t\t\t\t\t\t\tvar priorityValue = form.fm('priorityValue' + i + '_temp').val();\n\n\t\t\t\t\t\t\t\tform.fm('priorityName' + i + '_' + ");
                  if (_jspx_meth_portlet_namespace_9((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_0, _jspx_page_context))
                    return;
                  out.write("prioritiesLastLanguageId).val(priorityName);\n\t\t\t\t\t\t\t\tform.fm('priorityImage' + i + '_' + ");
                  if (_jspx_meth_portlet_namespace_10((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_0, _jspx_page_context))
                    return;
                  out.write("prioritiesLastLanguageId).val(priorityImage);\n\t\t\t\t\t\t\t\tform.fm('priorityValue' + i + '_' + ");
                  if (_jspx_meth_portlet_namespace_11((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_0, _jspx_page_context))
                    return;
                  out.write("prioritiesLastLanguageId).val(priorityValue);\n\t\t\t\t\t\t\t}\n\n\t\t\t\t\t\t\t");
                  if (_jspx_meth_portlet_namespace_12((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_0, _jspx_page_context))
                    return;
                  out.write("prioritiesChanged = false;\n\t\t\t\t\t\t}\n\n\t\t\t\t\t\tvar selLanguageId = form.fm('prioritiesLanguageId').val();\n\n\t\t\t\t\t\tif (selLanguageId) {\n\t\t\t\t\t\t\t");
                  if (_jspx_meth_portlet_namespace_13((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_0, _jspx_page_context))
                    return;
                  out.write("updatePrioritiesLanguageTemps(selLanguageId);\n\t\t\t\t\t\t}\n\n\t\t\t\t\t\t$('#");
                  if (_jspx_meth_portlet_namespace_14((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_0, _jspx_page_context))
                    return;
                  out.write("localized-priorities-table').toggleClass('hide', !selLanguageId);\n\n\t\t\t\t\t\t");
                  if (_jspx_meth_portlet_namespace_15((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_0, _jspx_page_context))
                    return;
                  out.write("prioritiesLastLanguageId = selLanguageId;\n\t\t\t\t\t}\n\n\t\t\t\t\tfunction ");
                  if (_jspx_meth_portlet_namespace_16((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_0, _jspx_page_context))
                    return;
                  out.write("updatePrioritiesLanguageTemps(lang) {\n\t\t\t\t\t\tvar form = AUI.$(document.");
                  if (_jspx_meth_portlet_namespace_17((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_0, _jspx_page_context))
                    return;
                  out.write("fm);\n\n\t\t\t\t\t\tif (lang != '");
                  out.print( defaultLanguageId );
                  out.write("') {\n\t\t\t\t\t\t\tfor (var i = 0; i < 10; i++) {\n\t\t\t\t\t\t\t\tvar defaultImage = form.fm('priorityImage' + i + '_");
                  out.print( defaultLanguageId );
                  out.write("').val();\n\t\t\t\t\t\t\t\tvar defaultName = form.fm('priorityName' + i + '_");
                  out.print( defaultLanguageId );
                  out.write("').val();\n\t\t\t\t\t\t\t\tvar defaultValue = form.fm('priorityValue' + i + '_");
                  out.print( defaultLanguageId );
                  out.write("').val();\n\n\t\t\t\t\t\t\t\tvar priorityImage = form.fm('priorityImage' + i + '_' + lang).val();\n\t\t\t\t\t\t\t\tvar priorityName = form.fm('priorityName' + i + '_' + lang).val();\n\t\t\t\t\t\t\t\tvar priorityValue = form.fm('priorityValue' + i + '_' + lang).val();\n\n\t\t\t\t\t\t\t\tvar image = priorityImage || defaultImage;\n\t\t\t\t\t\t\t\tvar name = priorityName || defaultName;\n\t\t\t\t\t\t\t\tvar value = priorityValue || defaultValue;\n\n\t\t\t\t\t\t\t\tform.fm('priorityName' + i + '_temp').val(name);\n\t\t\t\t\t\t\t\tform.fm('priorityImage' + i + '_temp').val(image);\n\t\t\t\t\t\t\t\tform.fm('priorityValue' + i + '_temp').val(value);\n\t\t\t\t\t\t\t}\n\t\t\t\t\t\t}\n\t\t\t\t\t}\n\n\t\t\t\t\t");
                  if (_jspx_meth_portlet_namespace_18((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_0, _jspx_page_context))
                    return;
                  out.write("updatePrioritiesLanguageTemps(");
                  if (_jspx_meth_portlet_namespace_19((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_0, _jspx_page_context))
                    return;
                  out.write("prioritiesLastLanguageId);\n\t\t\t\t");
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
              out.write("\n\t\t\t");
            }
            if (_jspx_th_liferay$1ui_section_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_liferay$1ui_section.reuse(_jspx_th_liferay$1ui_section_4);
              return;
            }
            _jspx_tagPool_liferay$1ui_section.reuse(_jspx_th_liferay$1ui_section_4);
            out.write("\n\n\t\t\t");
            //  liferay-ui:section
            com.liferay.taglib.ui.SectionTag _jspx_th_liferay$1ui_section_5 = (com.liferay.taglib.ui.SectionTag) _jspx_tagPool_liferay$1ui_section.get(com.liferay.taglib.ui.SectionTag.class);
            _jspx_th_liferay$1ui_section_5.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1ui_section_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_tabs_0);
            int _jspx_eval_liferay$1ui_section_5 = _jspx_th_liferay$1ui_section_5.doStartTag();
            if (_jspx_eval_liferay$1ui_section_5 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              java.lang.String sectionParam = null;
              java.lang.String sectionName = null;
              java.lang.Boolean sectionSelected = null;
              java.lang.String sectionScroll = null;
              java.lang.String sectionRedirectParams = null;
              sectionParam = (java.lang.String) _jspx_page_context.findAttribute("sectionParam");
              sectionName = (java.lang.String) _jspx_page_context.findAttribute("sectionName");
              sectionSelected = (java.lang.Boolean) _jspx_page_context.findAttribute("sectionSelected");
              sectionScroll = (java.lang.String) _jspx_page_context.findAttribute("sectionScroll");
              sectionRedirectParams = (java.lang.String) _jspx_page_context.findAttribute("sectionRedirectParams");
              out.write("\n\t\t\t\t<div class=\"alert alert-info\">\n\t\t\t\t\t");
              if (_jspx_meth_liferay$1ui_message_7((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1ui_section_5, _jspx_page_context))
                return;
              out.write("\n\t\t\t\t</div>\n\n\t\t\t\t");
              //  liferay-frontend:fieldset-group
              com.liferay.frontend.taglib.servlet.taglib.FieldsetGroupTag _jspx_th_liferay$1frontend_fieldset$1group_5 = (com.liferay.frontend.taglib.servlet.taglib.FieldsetGroupTag) _jspx_tagPool_liferay$1frontend_fieldset$1group.get(com.liferay.frontend.taglib.servlet.taglib.FieldsetGroupTag.class);
              _jspx_th_liferay$1frontend_fieldset$1group_5.setPageContext(_jspx_page_context);
              _jspx_th_liferay$1frontend_fieldset$1group_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_section_5);
              int _jspx_eval_liferay$1frontend_fieldset$1group_5 = _jspx_th_liferay$1frontend_fieldset$1group_5.doStartTag();
              if (_jspx_eval_liferay$1frontend_fieldset$1group_5 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\t\t\t\t\t");
                //  liferay-frontend:fieldset
                com.liferay.frontend.taglib.servlet.taglib.FieldsetTag _jspx_th_liferay$1frontend_fieldset_4 = (com.liferay.frontend.taglib.servlet.taglib.FieldsetTag) _jspx_tagPool_liferay$1frontend_fieldset.get(com.liferay.frontend.taglib.servlet.taglib.FieldsetTag.class);
                _jspx_th_liferay$1frontend_fieldset_4.setPageContext(_jspx_page_context);
                _jspx_th_liferay$1frontend_fieldset_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_fieldset$1group_5);
                int _jspx_eval_liferay$1frontend_fieldset_4 = _jspx_th_liferay$1frontend_fieldset_4.doStartTag();
                if (_jspx_eval_liferay$1frontend_fieldset_4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  out.write("\n\t\t\t\t\t\t<table class=\"lfr-table\">\n\t\t\t\t\t\t\t<tr>\n\t\t\t\t\t\t\t\t<td class=\"lfr-label\">\n\t\t\t\t\t\t\t\t\t");
                  //  aui:input
                  com.liferay.taglib.aui.InputTag _jspx_th_aui_input_20 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
                  _jspx_th_aui_input_20.setPageContext(_jspx_page_context);
                  _jspx_th_aui_input_20.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_fieldset_4);
                  _jspx_th_aui_input_20.setName("defaultLanguage");
                  _jspx_th_aui_input_20.setType("resource");
                  _jspx_th_aui_input_20.setValue( defaultLocale.getDisplayName(defaultLocale) );
                  int _jspx_eval_aui_input_20 = _jspx_th_aui_input_20.doStartTag();
                  if (_jspx_th_aui_input_20.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_20);
                    return;
                  }
                  _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_20);
                  out.write("\n\t\t\t\t\t\t\t\t</td>\n\t\t\t\t\t\t\t\t<td class=\"lfr-label\">\n\t\t\t\t\t\t\t\t\t");
                  //  aui:select
                  com.liferay.taglib.aui.SelectTag _jspx_th_aui_select_3 = (com.liferay.taglib.aui.SelectTag) _jspx_tagPool_aui_select_showEmptyOption_onChange_name_label.get(com.liferay.taglib.aui.SelectTag.class);
                  _jspx_th_aui_select_3.setPageContext(_jspx_page_context);
                  _jspx_th_aui_select_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_fieldset_4);
                  _jspx_th_aui_select_3.setLabel("localized-language");
                  _jspx_th_aui_select_3.setName("ranksLanguageId");
                  _jspx_th_aui_select_3.setOnChange( renderResponse.getNamespace() + "updateRanksLanguage();" );
                  _jspx_th_aui_select_3.setShowEmptyOption( true );
                  int _jspx_eval_aui_select_3 = _jspx_th_aui_select_3.doStartTag();
                  if (_jspx_eval_aui_select_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    if (_jspx_eval_aui_select_3 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                      out = _jspx_page_context.pushBody();
                      _jspx_th_aui_select_3.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                      _jspx_th_aui_select_3.doInitBody();
                    }
                    do {
                      out.write("\n\n\t\t\t\t\t\t\t\t\t\t");

										for (Locale curLocale : locales) {
											if (curLocale.equals(defaultLocale)) {
												continue;
											}
										
                      out.write("\n\n\t\t\t\t\t\t\t\t\t\t\t");
                      //  aui:option
                      com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_6 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_value_selected_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
                      _jspx_th_aui_option_6.setPageContext(_jspx_page_context);
                      _jspx_th_aui_option_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_3);
                      _jspx_th_aui_option_6.setLabel( curLocale.getDisplayName(locale) );
                      _jspx_th_aui_option_6.setSelected( currentLanguageId.equals(LocaleUtil.toLanguageId(curLocale)) );
                      _jspx_th_aui_option_6.setValue( LocaleUtil.toLanguageId(curLocale) );
                      int _jspx_eval_aui_option_6 = _jspx_th_aui_option_6.doStartTag();
                      if (_jspx_th_aui_option_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_aui_option_value_selected_label_nobody.reuse(_jspx_th_aui_option_6);
                        return;
                      }
                      _jspx_tagPool_aui_option_value_selected_label_nobody.reuse(_jspx_th_aui_option_6);
                      out.write("\n\n\t\t\t\t\t\t\t\t\t\t");

										}
										
                      out.write("\n\n\t\t\t\t\t\t\t\t\t");
                      int evalDoAfterBody = _jspx_th_aui_select_3.doAfterBody();
                      if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                        break;
                    } while (true);
                    if (_jspx_eval_aui_select_3 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                      out = _jspx_page_context.popBody();
                  }
                  if (_jspx_th_aui_select_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_aui_select_showEmptyOption_onChange_name_label.reuse(_jspx_th_aui_select_3);
                    return;
                  }
                  _jspx_tagPool_aui_select_showEmptyOption_onChange_name_label.reuse(_jspx_th_aui_select_3);
                  out.write("\n\t\t\t\t\t\t\t\t</td>\n\t\t\t\t\t\t\t</tr>\n\t\t\t\t\t\t\t<tr>\n\t\t\t\t\t\t\t\t<td>\n\t\t\t\t\t\t\t\t\t");
                  //  aui:input
                  com.liferay.taglib.aui.InputTag _jspx_th_aui_input_21 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_title_name_label_cssClass_nobody.get(com.liferay.taglib.aui.InputTag.class);
                  _jspx_th_aui_input_21.setPageContext(_jspx_page_context);
                  _jspx_th_aui_input_21.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_fieldset_4);
                  _jspx_th_aui_input_21.setCssClass("lfr-textarea-container");
                  _jspx_th_aui_input_21.setLabel("");
                  _jspx_th_aui_input_21.setName( "ranks_" + defaultLanguageId );
                  _jspx_th_aui_input_21.setTitle("ranks");
                  _jspx_th_aui_input_21.setType("textarea");
                  _jspx_th_aui_input_21.setValue( StringUtil.merge(mbGroupServiceSettings.getRanks(defaultLanguageId), StringPool.NEW_LINE) );
                  int _jspx_eval_aui_input_21 = _jspx_th_aui_input_21.doStartTag();
                  if (_jspx_th_aui_input_21.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_aui_input_value_type_title_name_label_cssClass_nobody.reuse(_jspx_th_aui_input_21);
                    return;
                  }
                  _jspx_tagPool_aui_input_value_type_title_name_label_cssClass_nobody.reuse(_jspx_th_aui_input_21);
                  out.write("\n\t\t\t\t\t\t\t\t</td>\n\t\t\t\t\t\t\t\t<td>\n\n\t\t\t\t\t\t\t\t\t");

									for (Locale curLocale : locales) {
										if (curLocale.equals(defaultLocale)) {
											continue;
										}
									
                  out.write("\n\n\t\t\t\t\t\t\t\t\t\t");
                  //  aui:input
                  com.liferay.taglib.aui.InputTag _jspx_th_aui_input_22 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_wrapperCssClass_value_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
                  _jspx_th_aui_input_22.setPageContext(_jspx_page_context);
                  _jspx_th_aui_input_22.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_fieldset_4);
                  _jspx_th_aui_input_22.setName( "ranks_" + LocaleUtil.toLanguageId(curLocale) );
                  _jspx_th_aui_input_22.setType("textarea");
                  _jspx_th_aui_input_22.setValue( StringUtil.merge(mbGroupServiceSettings.getRanks(LocaleUtil.toLanguageId(curLocale)), StringPool.NEW_LINE) );
                  _jspx_th_aui_input_22.setWrapperCssClass("hide");
                  int _jspx_eval_aui_input_22 = _jspx_th_aui_input_22.doStartTag();
                  if (_jspx_th_aui_input_22.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_aui_input_wrapperCssClass_value_type_name_nobody.reuse(_jspx_th_aui_input_22);
                    return;
                  }
                  _jspx_tagPool_aui_input_wrapperCssClass_value_type_name_nobody.reuse(_jspx_th_aui_input_22);
                  out.write("\n\n\t\t\t\t\t\t\t\t\t");

									}
									
                  out.write("\n\n\t\t\t\t\t\t\t\t\t");
                  //  aui:input
                  com.liferay.taglib.aui.InputTag _jspx_th_aui_input_23 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_type_title_onChange_name_label_cssClass_nobody.get(com.liferay.taglib.aui.InputTag.class);
                  _jspx_th_aui_input_23.setPageContext(_jspx_page_context);
                  _jspx_th_aui_input_23.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_fieldset_4);
                  _jspx_th_aui_input_23.setCssClass("hide lfr-textarea-container");
                  _jspx_th_aui_input_23.setLabel("");
                  _jspx_th_aui_input_23.setName("ranks_temp");
                  _jspx_th_aui_input_23.setOnChange( renderResponse.getNamespace() + "onRanksChanged();" );
                  _jspx_th_aui_input_23.setTitle("ranks");
                  _jspx_th_aui_input_23.setType("textarea");
                  int _jspx_eval_aui_input_23 = _jspx_th_aui_input_23.doStartTag();
                  if (_jspx_th_aui_input_23.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_aui_input_type_title_onChange_name_label_cssClass_nobody.reuse(_jspx_th_aui_input_23);
                    return;
                  }
                  _jspx_tagPool_aui_input_type_title_onChange_name_label_cssClass_nobody.reuse(_jspx_th_aui_input_23);
                  out.write("\n\t\t\t\t\t\t\t\t</td>\n\t\t\t\t\t\t\t</tr>\n\t\t\t\t\t\t</table>\n\t\t\t\t\t");
                }
                if (_jspx_th_liferay$1frontend_fieldset_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_liferay$1frontend_fieldset.reuse(_jspx_th_liferay$1frontend_fieldset_4);
                  return;
                }
                _jspx_tagPool_liferay$1frontend_fieldset.reuse(_jspx_th_liferay$1frontend_fieldset_4);
                out.write("\n\t\t\t\t");
              }
              if (_jspx_th_liferay$1frontend_fieldset$1group_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_liferay$1frontend_fieldset$1group.reuse(_jspx_th_liferay$1frontend_fieldset$1group_5);
                return;
              }
              _jspx_tagPool_liferay$1frontend_fieldset$1group.reuse(_jspx_th_liferay$1frontend_fieldset$1group_5);
              out.write("\n\n\t\t\t\t");
              //  aui:script
              com.liferay.taglib.aui.ScriptTag _jspx_th_aui_script_1 = (com.liferay.taglib.aui.ScriptTag) _jspx_tagPool_aui_script.get(com.liferay.taglib.aui.ScriptTag.class);
              _jspx_th_aui_script_1.setPageContext(_jspx_page_context);
              _jspx_th_aui_script_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_section_5);
              int _jspx_eval_aui_script_1 = _jspx_th_aui_script_1.doStartTag();
              if (_jspx_eval_aui_script_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                if (_jspx_eval_aui_script_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                  out = _jspx_page_context.pushBody();
                  _jspx_th_aui_script_1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                  _jspx_th_aui_script_1.doInitBody();
                }
                do {
                  out.write("\n\t\t\t\t\tvar ");
                  if (_jspx_meth_portlet_namespace_20((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
                    return;
                  out.write("ranksChanged = false;\n\t\t\t\t\tvar ");
                  if (_jspx_meth_portlet_namespace_21((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
                    return;
                  out.write("ranksLastLanguageId = '");
                  out.print( currentLanguageId );
                  out.write("';\n\n\t\t\t\t\tfunction ");
                  if (_jspx_meth_portlet_namespace_22((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
                    return;
                  out.write("onRanksChanged() {\n\t\t\t\t\t\t");
                  if (_jspx_meth_portlet_namespace_23((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
                    return;
                  out.write("ranksChanged = true;\n\t\t\t\t\t}\n\n\t\t\t\t\tfunction ");
                  if (_jspx_meth_portlet_namespace_24((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
                    return;
                  out.write("updateRanksLanguage() {\n\t\t\t\t\t\tvar form = AUI.$(document.");
                  if (_jspx_meth_portlet_namespace_25((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
                    return;
                  out.write("fm);\n\n\t\t\t\t\t\tif (");
                  if (_jspx_meth_portlet_namespace_26((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
                    return;
                  out.write("ranksChanged && (");
                  if (_jspx_meth_portlet_namespace_27((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
                    return;
                  out.write("ranksLastLanguageId != '");
                  out.print( defaultLanguageId );
                  out.write("')) {\n\t\t\t\t\t\t\tvar ranksValue = form.fm('ranks_temp').val();\n\n\t\t\t\t\t\t\tform.fm('ranks_' + ");
                  if (_jspx_meth_portlet_namespace_28((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
                    return;
                  out.write("ranksLastLanguageId).val(ranksValue);\n\n\t\t\t\t\t\t\t");
                  if (_jspx_meth_portlet_namespace_29((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
                    return;
                  out.write("ranksChanged = false;\n\t\t\t\t\t\t}\n\n\t\t\t\t\t\tvar selLanguageId = form.fm('ranksLanguageId').val();\n\n\t\t\t\t\t\tif (selLanguageId) {\n\t\t\t\t\t\t\t");
                  if (_jspx_meth_portlet_namespace_30((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
                    return;
                  out.write("updateRanksLanguageTemps(selLanguageId);\n\t\t\t\t\t\t}\n\n\t\t\t\t\t\tform.fm('ranks_temp').toggleClass('hide', !selLanguageId);\n\n\t\t\t\t\t\t");
                  if (_jspx_meth_portlet_namespace_31((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
                    return;
                  out.write("ranksLastLanguageId = selLanguageId;\n\t\t\t\t\t}\n\n\t\t\t\t\tfunction ");
                  if (_jspx_meth_portlet_namespace_32((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
                    return;
                  out.write("updateRanksLanguageTemps(lang) {\n\t\t\t\t\t\tvar form = AUI.$(document.");
                  if (_jspx_meth_portlet_namespace_33((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
                    return;
                  out.write("fm);\n\n\t\t\t\t\t\tif (lang != '");
                  out.print( defaultLanguageId );
                  out.write("') {\n\t\t\t\t\t\t\tvar ranksValue = form.fm('ranks_' + lang).val();\n\n\t\t\t\t\t\t\tvar defaultRanksValue = form.fm('ranks_");
                  out.print( defaultLanguageId );
                  out.write("').val();\n\n\t\t\t\t\t\t\tvar value = ranksValue || defaultRanksValue;\n\n\t\t\t\t\t\t\tform.fm('ranks_temp').val(value);\n\t\t\t\t\t\t}\n\t\t\t\t\t}\n\n\t\t\t\t\t");
                  if (_jspx_meth_portlet_namespace_34((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
                    return;
                  out.write("updateRanksLanguageTemps(");
                  if (_jspx_meth_portlet_namespace_35((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
                    return;
                  out.write("ranksLastLanguageId);\n\t\t\t\t");
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
              out.write("\n\t\t\t");
            }
            if (_jspx_th_liferay$1ui_section_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_liferay$1ui_section.reuse(_jspx_th_liferay$1ui_section_5);
              return;
            }
            _jspx_tagPool_liferay$1ui_section.reuse(_jspx_th_liferay$1ui_section_5);
            out.write("\n\n\t\t\t");
            //  c:if
            com.liferay.taglib.core.IfTag _jspx_th_c_if_1 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
            _jspx_th_c_if_1.setPageContext(_jspx_page_context);
            _jspx_th_c_if_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_tabs_0);
            _jspx_th_c_if_1.setTest( PortalUtil.isRSSFeedsEnabled() );
            int _jspx_eval_c_if_1 = _jspx_th_c_if_1.doStartTag();
            if (_jspx_eval_c_if_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\t\t\t\t");
              //  liferay-ui:section
              com.liferay.taglib.ui.SectionTag _jspx_th_liferay$1ui_section_6 = (com.liferay.taglib.ui.SectionTag) _jspx_tagPool_liferay$1ui_section.get(com.liferay.taglib.ui.SectionTag.class);
              _jspx_th_liferay$1ui_section_6.setPageContext(_jspx_page_context);
              _jspx_th_liferay$1ui_section_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_1);
              int _jspx_eval_liferay$1ui_section_6 = _jspx_th_liferay$1ui_section_6.doStartTag();
              if (_jspx_eval_liferay$1ui_section_6 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                java.lang.String sectionParam = null;
                java.lang.String sectionName = null;
                java.lang.Boolean sectionSelected = null;
                java.lang.String sectionScroll = null;
                java.lang.String sectionRedirectParams = null;
                sectionParam = (java.lang.String) _jspx_page_context.findAttribute("sectionParam");
                sectionName = (java.lang.String) _jspx_page_context.findAttribute("sectionName");
                sectionSelected = (java.lang.Boolean) _jspx_page_context.findAttribute("sectionSelected");
                sectionScroll = (java.lang.String) _jspx_page_context.findAttribute("sectionScroll");
                sectionRedirectParams = (java.lang.String) _jspx_page_context.findAttribute("sectionRedirectParams");
                out.write("\n\t\t\t\t\t");
                //  liferay-frontend:fieldset-group
                com.liferay.frontend.taglib.servlet.taglib.FieldsetGroupTag _jspx_th_liferay$1frontend_fieldset$1group_6 = (com.liferay.frontend.taglib.servlet.taglib.FieldsetGroupTag) _jspx_tagPool_liferay$1frontend_fieldset$1group.get(com.liferay.frontend.taglib.servlet.taglib.FieldsetGroupTag.class);
                _jspx_th_liferay$1frontend_fieldset$1group_6.setPageContext(_jspx_page_context);
                _jspx_th_liferay$1frontend_fieldset$1group_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_section_6);
                int _jspx_eval_liferay$1frontend_fieldset$1group_6 = _jspx_th_liferay$1frontend_fieldset$1group_6.doStartTag();
                if (_jspx_eval_liferay$1frontend_fieldset$1group_6 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  out.write("\n\t\t\t\t\t\t");
                  //  liferay-rss:rss-settings
                  com.liferay.rss.taglib.servlet.taglib.RSSSettingsTag _jspx_th_liferay$1rss_rss$1settings_0 = (com.liferay.rss.taglib.servlet.taglib.RSSSettingsTag) _jspx_tagPool_liferay$1rss_rss$1settings_feedType_enabled_displayStyle_delta_nobody.get(com.liferay.rss.taglib.servlet.taglib.RSSSettingsTag.class);
                  _jspx_th_liferay$1rss_rss$1settings_0.setPageContext(_jspx_page_context);
                  _jspx_th_liferay$1rss_rss$1settings_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_fieldset$1group_6);
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
                  out.write("\n\t\t\t\t\t");
                }
                if (_jspx_th_liferay$1frontend_fieldset$1group_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_liferay$1frontend_fieldset$1group.reuse(_jspx_th_liferay$1frontend_fieldset$1group_6);
                  return;
                }
                _jspx_tagPool_liferay$1frontend_fieldset$1group.reuse(_jspx_th_liferay$1frontend_fieldset$1group_6);
                out.write("\n\t\t\t\t");
              }
              if (_jspx_th_liferay$1ui_section_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_liferay$1ui_section.reuse(_jspx_th_liferay$1ui_section_6);
                return;
              }
              _jspx_tagPool_liferay$1ui_section.reuse(_jspx_th_liferay$1ui_section_6);
              out.write("\n\t\t\t");
            }
            if (_jspx_th_c_if_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_1);
              return;
            }
            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_1);
            out.write("\n\t\t");
          }
          if (_jspx_th_liferay$1ui_tabs_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_liferay$1ui_tabs_type_refresh_names.reuse(_jspx_th_liferay$1ui_tabs_0);
            return;
          }
          _jspx_tagPool_liferay$1ui_tabs_type_refresh_names.reuse(_jspx_th_liferay$1ui_tabs_0);
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
        _jspx_tagPool_liferay$1frontend_edit$1form_onSubmit_name_method_action.reuse(_jspx_th_liferay$1frontend_edit$1form_0);
        return;
      }
      _jspx_tagPool_liferay$1frontend_edit$1form_onSubmit_name_method_action.reuse(_jspx_th_liferay$1frontend_edit$1form_0);
      out.write('\n');
      out.write('\n');
      if (_jspx_meth_aui_script_2(_jspx_page_context))
        return;
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

  private boolean _jspx_meth_portlet_param_1(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1portlet_actionURL_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:param
    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_1 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
    _jspx_th_portlet_param_1.setPageContext(_jspx_page_context);
    _jspx_th_portlet_param_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_actionURL_0);
    _jspx_th_portlet_param_1.setName("settingsScope");
    _jspx_th_portlet_param_1.setValue("group");
    int _jspx_eval_portlet_param_1 = _jspx_th_portlet_param_1.doStartTag();
    if (_jspx_th_portlet_param_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_1);
      return true;
    }
    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_1);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_0(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1ui_section_4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_0 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_0.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_section_4);
    _jspx_th_liferay$1ui_message_0.setKey("enter-the-name,-image,-and-priority-level-in-descending-order");
    int _jspx_eval_liferay$1ui_message_0 = _jspx_th_liferay$1ui_message_0.doStartTag();
    if (_jspx_th_liferay$1ui_message_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_0);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_0);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_1(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1frontend_fieldset_3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_1 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_1.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_fieldset_3);
    _jspx_th_liferay$1ui_message_1.setKey("name");
    int _jspx_eval_liferay$1ui_message_1 = _jspx_th_liferay$1ui_message_1.doStartTag();
    if (_jspx_th_liferay$1ui_message_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_1);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_1);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_2(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1frontend_fieldset_3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_2 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_2.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_fieldset_3);
    _jspx_th_liferay$1ui_message_2.setKey("image");
    int _jspx_eval_liferay$1ui_message_2 = _jspx_th_liferay$1ui_message_2.doStartTag();
    if (_jspx_th_liferay$1ui_message_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_2);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_2);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_3(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1frontend_fieldset_3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_3 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_3.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_fieldset_3);
    _jspx_th_liferay$1ui_message_3.setKey("priority");
    int _jspx_eval_liferay$1ui_message_3 = _jspx_th_liferay$1ui_message_3.doStartTag();
    if (_jspx_th_liferay$1ui_message_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_3);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_3);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_0(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1frontend_fieldset_3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_0 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_0.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_fieldset_3);
    int _jspx_eval_portlet_namespace_0 = _jspx_th_portlet_namespace_0.doStartTag();
    if (_jspx_th_portlet_namespace_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_0);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_0);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_4(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1frontend_fieldset_3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_4 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_4.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_fieldset_3);
    _jspx_th_liferay$1ui_message_4.setKey("name");
    int _jspx_eval_liferay$1ui_message_4 = _jspx_th_liferay$1ui_message_4.doStartTag();
    if (_jspx_th_liferay$1ui_message_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_4);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_4);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_5(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1frontend_fieldset_3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_5 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_5.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_fieldset_3);
    _jspx_th_liferay$1ui_message_5.setKey("image");
    int _jspx_eval_liferay$1ui_message_5 = _jspx_th_liferay$1ui_message_5.doStartTag();
    if (_jspx_th_liferay$1ui_message_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_5);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_5);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_6(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1frontend_fieldset_3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_6 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_6.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_fieldset_3);
    _jspx_th_liferay$1ui_message_6.setKey("priority");
    int _jspx_eval_liferay$1ui_message_6 = _jspx_th_liferay$1ui_message_6.doStartTag();
    if (_jspx_th_liferay$1ui_message_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_6);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_6);
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

  private boolean _jspx_meth_portlet_namespace_9(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_9 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_9.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_9.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_0);
    int _jspx_eval_portlet_namespace_9 = _jspx_th_portlet_namespace_9.doStartTag();
    if (_jspx_th_portlet_namespace_9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_9);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_9);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_10(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_10 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_10.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_10.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_0);
    int _jspx_eval_portlet_namespace_10 = _jspx_th_portlet_namespace_10.doStartTag();
    if (_jspx_th_portlet_namespace_10.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_10);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_10);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_11(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_11 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_11.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_11.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_0);
    int _jspx_eval_portlet_namespace_11 = _jspx_th_portlet_namespace_11.doStartTag();
    if (_jspx_th_portlet_namespace_11.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_11);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_11);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_12(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_12 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_12.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_12.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_0);
    int _jspx_eval_portlet_namespace_12 = _jspx_th_portlet_namespace_12.doStartTag();
    if (_jspx_th_portlet_namespace_12.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_12);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_12);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_13(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_13 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_13.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_13.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_0);
    int _jspx_eval_portlet_namespace_13 = _jspx_th_portlet_namespace_13.doStartTag();
    if (_jspx_th_portlet_namespace_13.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_13);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_13);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_14(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_14 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_14.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_14.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_0);
    int _jspx_eval_portlet_namespace_14 = _jspx_th_portlet_namespace_14.doStartTag();
    if (_jspx_th_portlet_namespace_14.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_14);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_14);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_15(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_15 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_15.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_15.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_0);
    int _jspx_eval_portlet_namespace_15 = _jspx_th_portlet_namespace_15.doStartTag();
    if (_jspx_th_portlet_namespace_15.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_15);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_15);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_16(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_16 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_16.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_16.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_0);
    int _jspx_eval_portlet_namespace_16 = _jspx_th_portlet_namespace_16.doStartTag();
    if (_jspx_th_portlet_namespace_16.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_16);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_16);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_17(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_17 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_17.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_17.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_0);
    int _jspx_eval_portlet_namespace_17 = _jspx_th_portlet_namespace_17.doStartTag();
    if (_jspx_th_portlet_namespace_17.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_17);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_17);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_18(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_18 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_18.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_18.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_0);
    int _jspx_eval_portlet_namespace_18 = _jspx_th_portlet_namespace_18.doStartTag();
    if (_jspx_th_portlet_namespace_18.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_18);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_18);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_19(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_19 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_19.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_19.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_0);
    int _jspx_eval_portlet_namespace_19 = _jspx_th_portlet_namespace_19.doStartTag();
    if (_jspx_th_portlet_namespace_19.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_19);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_19);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_7(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1ui_section_5, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_7 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_7.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_section_5);
    _jspx_th_liferay$1ui_message_7.setKey("enter-rank-and-minimum-post-pairs-per-line");
    int _jspx_eval_liferay$1ui_message_7 = _jspx_th_liferay$1ui_message_7.doStartTag();
    if (_jspx_th_liferay$1ui_message_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_7);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_7);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_20(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_20 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_20.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_20.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    int _jspx_eval_portlet_namespace_20 = _jspx_th_portlet_namespace_20.doStartTag();
    if (_jspx_th_portlet_namespace_20.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_20);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_20);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_21(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_21 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_21.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_21.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    int _jspx_eval_portlet_namespace_21 = _jspx_th_portlet_namespace_21.doStartTag();
    if (_jspx_th_portlet_namespace_21.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_21);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_21);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_22(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_22 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_22.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_22.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    int _jspx_eval_portlet_namespace_22 = _jspx_th_portlet_namespace_22.doStartTag();
    if (_jspx_th_portlet_namespace_22.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_22);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_22);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_23(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_23 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_23.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_23.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    int _jspx_eval_portlet_namespace_23 = _jspx_th_portlet_namespace_23.doStartTag();
    if (_jspx_th_portlet_namespace_23.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_23);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_23);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_24(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_24 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_24.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_24.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    int _jspx_eval_portlet_namespace_24 = _jspx_th_portlet_namespace_24.doStartTag();
    if (_jspx_th_portlet_namespace_24.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_24);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_24);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_25(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_25 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_25.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_25.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    int _jspx_eval_portlet_namespace_25 = _jspx_th_portlet_namespace_25.doStartTag();
    if (_jspx_th_portlet_namespace_25.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_25);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_25);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_26(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_26 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_26.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_26.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    int _jspx_eval_portlet_namespace_26 = _jspx_th_portlet_namespace_26.doStartTag();
    if (_jspx_th_portlet_namespace_26.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_26);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_26);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_27(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_27 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_27.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_27.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    int _jspx_eval_portlet_namespace_27 = _jspx_th_portlet_namespace_27.doStartTag();
    if (_jspx_th_portlet_namespace_27.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_27);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_27);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_28(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_28 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_28.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_28.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    int _jspx_eval_portlet_namespace_28 = _jspx_th_portlet_namespace_28.doStartTag();
    if (_jspx_th_portlet_namespace_28.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_28);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_28);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_29(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_29 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_29.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_29.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    int _jspx_eval_portlet_namespace_29 = _jspx_th_portlet_namespace_29.doStartTag();
    if (_jspx_th_portlet_namespace_29.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_29);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_29);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_30(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_30 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_30.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_30.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    int _jspx_eval_portlet_namespace_30 = _jspx_th_portlet_namespace_30.doStartTag();
    if (_jspx_th_portlet_namespace_30.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_30);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_30);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_31(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_31 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_31.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_31.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    int _jspx_eval_portlet_namespace_31 = _jspx_th_portlet_namespace_31.doStartTag();
    if (_jspx_th_portlet_namespace_31.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_31);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_31);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_32(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_32 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_32.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_32.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    int _jspx_eval_portlet_namespace_32 = _jspx_th_portlet_namespace_32.doStartTag();
    if (_jspx_th_portlet_namespace_32.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_32);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_32);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_33(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_33 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_33.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_33.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    int _jspx_eval_portlet_namespace_33 = _jspx_th_portlet_namespace_33.doStartTag();
    if (_jspx_th_portlet_namespace_33.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_33);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_33);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_34(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_34 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_34.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_34.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    int _jspx_eval_portlet_namespace_34 = _jspx_th_portlet_namespace_34.doStartTag();
    if (_jspx_th_portlet_namespace_34.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_34);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_34);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_35(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_35 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_35.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_35.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    int _jspx_eval_portlet_namespace_35 = _jspx_th_portlet_namespace_35.doStartTag();
    if (_jspx_th_portlet_namespace_35.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_35);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_35);
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
      if (_jspx_meth_aui_button_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1frontend_edit$1form$1footer_0, _jspx_page_context))
        return true;
      out.write("\n\n\t\t");
      if (_jspx_meth_aui_button_1((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1frontend_edit$1form$1footer_0, _jspx_page_context))
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

  private boolean _jspx_meth_aui_button_0(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1frontend_edit$1form$1footer_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:button
    com.liferay.taglib.aui.ButtonTag _jspx_th_aui_button_0 = (com.liferay.taglib.aui.ButtonTag) _jspx_tagPool_aui_button_type_nobody.get(com.liferay.taglib.aui.ButtonTag.class);
    _jspx_th_aui_button_0.setPageContext(_jspx_page_context);
    _jspx_th_aui_button_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_edit$1form$1footer_0);
    _jspx_th_aui_button_0.setType("submit");
    int _jspx_eval_aui_button_0 = _jspx_th_aui_button_0.doStartTag();
    if (_jspx_th_aui_button_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_button_type_nobody.reuse(_jspx_th_aui_button_0);
      return true;
    }
    _jspx_tagPool_aui_button_type_nobody.reuse(_jspx_th_aui_button_0);
    return false;
  }

  private boolean _jspx_meth_aui_button_1(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1frontend_edit$1form$1footer_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:button
    com.liferay.taglib.aui.ButtonTag _jspx_th_aui_button_1 = (com.liferay.taglib.aui.ButtonTag) _jspx_tagPool_aui_button_type_nobody.get(com.liferay.taglib.aui.ButtonTag.class);
    _jspx_th_aui_button_1.setPageContext(_jspx_page_context);
    _jspx_th_aui_button_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_edit$1form$1footer_0);
    _jspx_th_aui_button_1.setType("cancel");
    int _jspx_eval_aui_button_1 = _jspx_th_aui_button_1.doStartTag();
    if (_jspx_th_aui_button_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_button_type_nobody.reuse(_jspx_th_aui_button_1);
      return true;
    }
    _jspx_tagPool_aui_button_type_nobody.reuse(_jspx_th_aui_button_1);
    return false;
  }

  private boolean _jspx_meth_aui_script_2(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:script
    com.liferay.taglib.aui.ScriptTag _jspx_th_aui_script_2 = (com.liferay.taglib.aui.ScriptTag) _jspx_tagPool_aui_script.get(com.liferay.taglib.aui.ScriptTag.class);
    _jspx_th_aui_script_2.setPageContext(_jspx_page_context);
    _jspx_th_aui_script_2.setParent(null);
    int _jspx_eval_aui_script_2 = _jspx_th_aui_script_2.doStartTag();
    if (_jspx_eval_aui_script_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_aui_script_2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_aui_script_2.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_aui_script_2.doInitBody();
      }
      do {
        out.write("\n\tfunction ");
        if (_jspx_meth_portlet_namespace_36((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_2, _jspx_page_context))
          return true;
        out.write("saveConfiguration() {\n\t\t");
        if (_jspx_meth_portlet_namespace_37((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_2, _jspx_page_context))
          return true;
        out.write("saveEmails();\n\t\t");
        if (_jspx_meth_portlet_namespace_38((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_2, _jspx_page_context))
          return true;
        out.write("updatePrioritiesLanguage();\n\t\t");
        if (_jspx_meth_portlet_namespace_39((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_2, _jspx_page_context))
          return true;
        out.write("updateRanksLanguage();\n\n\t\tsubmitForm(document.");
        if (_jspx_meth_portlet_namespace_40((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_2, _jspx_page_context))
          return true;
        out.write("fm);\n\t}\n\n\tfunction ");
        if (_jspx_meth_portlet_namespace_41((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_2, _jspx_page_context))
          return true;
        out.write("saveEmails() {\n\t\tvar form = AUI.$(document.");
        if (_jspx_meth_portlet_namespace_42((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_2, _jspx_page_context))
          return true;
        out.write("fm);\n\n\t\tvar emailMessageAdded = window['");
        if (_jspx_meth_portlet_namespace_43((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_2, _jspx_page_context))
          return true;
        out.write("emailMessageAdded'];\n\n\t\tif (emailMessageAdded) {\n\t\t\tform.fm('preferences--emailMessageAddedBody--').val(emailMessageAdded.getHTML());\n\t\t}\n\n\t\tvar emailMessageUpdated = window['");
        if (_jspx_meth_portlet_namespace_44((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_2, _jspx_page_context))
          return true;
        out.write("emailMessageUpdated'];\n\n\t\tif (emailMessageUpdated) {\n\t\t\tform.fm('preferences--emailMessageUpdatedBody--').val(emailMessageUpdated.getHTML());\n\t\t}\n\t}\n");
        int evalDoAfterBody = _jspx_th_aui_script_2.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_aui_script_2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
        out = _jspx_page_context.popBody();
    }
    if (_jspx_th_aui_script_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_script.reuse(_jspx_th_aui_script_2);
      return true;
    }
    _jspx_tagPool_aui_script.reuse(_jspx_th_aui_script_2);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_36(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_36 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_36.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_36.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_2);
    int _jspx_eval_portlet_namespace_36 = _jspx_th_portlet_namespace_36.doStartTag();
    if (_jspx_th_portlet_namespace_36.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_36);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_36);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_37(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_37 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_37.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_37.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_2);
    int _jspx_eval_portlet_namespace_37 = _jspx_th_portlet_namespace_37.doStartTag();
    if (_jspx_th_portlet_namespace_37.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_37);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_37);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_38(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_38 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_38.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_38.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_2);
    int _jspx_eval_portlet_namespace_38 = _jspx_th_portlet_namespace_38.doStartTag();
    if (_jspx_th_portlet_namespace_38.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_38);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_38);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_39(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_39 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_39.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_39.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_2);
    int _jspx_eval_portlet_namespace_39 = _jspx_th_portlet_namespace_39.doStartTag();
    if (_jspx_th_portlet_namespace_39.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_39);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_39);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_40(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_40 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_40.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_40.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_2);
    int _jspx_eval_portlet_namespace_40 = _jspx_th_portlet_namespace_40.doStartTag();
    if (_jspx_th_portlet_namespace_40.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_40);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_40);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_41(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_41 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_41.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_41.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_2);
    int _jspx_eval_portlet_namespace_41 = _jspx_th_portlet_namespace_41.doStartTag();
    if (_jspx_th_portlet_namespace_41.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_41);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_41);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_42(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_42 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_42.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_42.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_2);
    int _jspx_eval_portlet_namespace_42 = _jspx_th_portlet_namespace_42.doStartTag();
    if (_jspx_th_portlet_namespace_42.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_42);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_42);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_43(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_43 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_43.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_43.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_2);
    int _jspx_eval_portlet_namespace_43 = _jspx_th_portlet_namespace_43.doStartTag();
    if (_jspx_th_portlet_namespace_43.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_43);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_43);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_44(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_44 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_44.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_44.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_2);
    int _jspx_eval_portlet_namespace_44 = _jspx_th_portlet_namespace_44.doStartTag();
    if (_jspx_th_portlet_namespace_44.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_44);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_44);
    return false;
  }
}
