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

public final class edit_005fcategory_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(2);
    _jspx_dependants.add("/message_boards/init.jsp");
    _jspx_dependants.add("/message_boards/init-ext.jsp");
  }

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_actionURL_var_name;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_form_onSubmit_name_method_action;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_param_value_name_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_model$1context_model_bean_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_if_test;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_input$1permissions_modelName_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1expando_custom$1attributes$1available_className;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_script;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_input_value_type_name_label_checked_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_resourceURL_var_id_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_button_type_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1captcha_captcha_url_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_option_selected_label_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_input_type_name_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_fieldset$1group_markupView;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1frontend_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_input_name_autoFocus_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_fieldset;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_fieldset_label;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_input_value_type_name_label_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_namespace_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_button_type_href_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_input_value_type_name_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_field$1wrapper_label;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_fieldset_label_collapsible_collapsed;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_input_name_label_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_error_message_exception_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1expando_custom$1attribute$1list_label_editable_classPK_className_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_select_name;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1theme_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_input_value_name_label_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_input_name_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1trash_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_button$1row;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_portlet_actionURL_var_name = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_form_onSubmit_name_method_action = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_param_value_name_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_model$1context_model_bean_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_if_test = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_input$1permissions_modelName_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1expando_custom$1attributes$1available_className = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_script = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_input_value_type_name_label_checked_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_resourceURL_var_id_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_button_type_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1captcha_captcha_url_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_option_selected_label_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_input_type_name_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_fieldset$1group_markupView = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1frontend_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_input_name_autoFocus_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_fieldset = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_fieldset_label = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_input_value_type_name_label_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_namespace_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_button_type_href_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_input_value_type_name_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_field$1wrapper_label = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_fieldset_label_collapsible_collapsed = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_input_name_label_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_error_message_exception_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1expando_custom$1attribute$1list_label_editable_classPK_className_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_select_name = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1theme_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_input_value_name_label_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_input_name_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1trash_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_button$1row = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_portlet_actionURL_var_name.release();
    _jspx_tagPool_aui_form_onSubmit_name_method_action.release();
    _jspx_tagPool_portlet_param_value_name_nobody.release();
    _jspx_tagPool_aui_model$1context_model_bean_nobody.release();
    _jspx_tagPool_c_if_test.release();
    _jspx_tagPool_liferay$1ui_input$1permissions_modelName_nobody.release();
    _jspx_tagPool_liferay$1expando_custom$1attributes$1available_className.release();
    _jspx_tagPool_aui_script.release();
    _jspx_tagPool_aui_input_value_type_name_label_checked_nobody.release();
    _jspx_tagPool_portlet_resourceURL_var_id_nobody.release();
    _jspx_tagPool_aui_button_type_nobody.release();
    _jspx_tagPool_liferay$1captcha_captcha_url_nobody.release();
    _jspx_tagPool_aui_option_selected_label_nobody.release();
    _jspx_tagPool_aui_input_type_name_nobody.release();
    _jspx_tagPool_aui_fieldset$1group_markupView.release();
    _jspx_tagPool_liferay$1frontend_defineObjects_nobody.release();
    _jspx_tagPool_aui_input_name_autoFocus_nobody.release();
    _jspx_tagPool_aui_fieldset.release();
    _jspx_tagPool_aui_fieldset_label.release();
    _jspx_tagPool_aui_input_value_type_name_label_nobody.release();
    _jspx_tagPool_portlet_namespace_nobody.release();
    _jspx_tagPool_aui_button_type_href_nobody.release();
    _jspx_tagPool_aui_input_value_type_name_nobody.release();
    _jspx_tagPool_aui_field$1wrapper_label.release();
    _jspx_tagPool_aui_fieldset_label_collapsible_collapsed.release();
    _jspx_tagPool_aui_input_name_label_nobody.release();
    _jspx_tagPool_portlet_defineObjects_nobody.release();
    _jspx_tagPool_liferay$1ui_error_message_exception_nobody.release();
    _jspx_tagPool_liferay$1expando_custom$1attribute$1list_label_editable_classPK_className_nobody.release();
    _jspx_tagPool_aui_select_name.release();
    _jspx_tagPool_liferay$1theme_defineObjects_nobody.release();
    _jspx_tagPool_aui_input_value_name_label_nobody.release();
    _jspx_tagPool_aui_input_name_nobody.release();
    _jspx_tagPool_liferay$1trash_defineObjects_nobody.release();
    _jspx_tagPool_aui_button$1row.release();
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

MBRequestHelper mbRequestHelper = new MBRequestHelper(request);

MBCategory category = mbRequestHelper.getCategory();

long categoryId = MBUtil.getCategoryId(request, category);

long parentCategoryId = mbRequestHelper.getParentCategoryId();

String defaultDisplayStyle = MBCategoryConstants.DEFAULT_DISPLAY_STYLE;

if ((category == null) && (parentCategoryId > 0)) {
	MBCategory parentCategory = MBCategoryLocalServiceUtil.getCategory(parentCategoryId);

	defaultDisplayStyle = parentCategory.getDisplayStyle();
}

String displayStyle = BeanParamUtil.getString(category, request, "displayStyle", defaultDisplayStyle);

MBMailingList mailingList = null;

if (categoryId > 0) {
	mailingList = MBMailingListLocalServiceUtil.fetchCategoryMailingList(scopeGroupId, categoryId);
}

if ((category == null) && (mailingList == null)) {
	if (parentCategoryId > 0) {
		mailingList = MBMailingListLocalServiceUtil.fetchCategoryMailingList(scopeGroupId, parentCategoryId);
	}
}

if (category != null) {
	MBBreadcrumbUtil.addPortletBreadcrumbEntries(category, request, renderResponse);

	if (!layout.isTypeControlPanel()) {
		PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(request, "edit"), currentURL);
	}
}
else {
	MBBreadcrumbUtil.addPortletBreadcrumbEntries(parentCategoryId, request, renderResponse);

	if (!layout.isTypeControlPanel()) {
		PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(request, "add-category[message-board]"), currentURL);
	}
}

boolean portletTitleBasedNavigation = GetterUtil.getBoolean(portletConfig.getInitParameter("portlet-title-based-navigation"));

MBHomeDisplayContext mbHomeDisplayContext = mbDisplayContextProvider.getMBHomeDisplayContext(request, response);

if (portletTitleBasedNavigation) {
	portletDisplay.setShowBackIcon(true);
	portletDisplay.setURLBack(redirect);

	renderResponse.setTitle(LanguageUtil.get(request, mbHomeDisplayContext.getTitle()));
}

      out.write("\n\n<div ");
      out.print( portletTitleBasedNavigation ? "class=\"container-fluid-1280\"" : StringPool.BLANK );
      out.write(">\n\t");
      //  c:if
      com.liferay.taglib.core.IfTag _jspx_th_c_if_0 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
      _jspx_th_c_if_0.setPageContext(_jspx_page_context);
      _jspx_th_c_if_0.setParent(null);
      _jspx_th_c_if_0.setTest( !portletTitleBasedNavigation );
      int _jspx_eval_c_if_0 = _jspx_th_c_if_0.doStartTag();
      if (_jspx_eval_c_if_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        out.write("\n\t\t<h3>");
        out.print( LanguageUtil.get(request, mbHomeDisplayContext.getTitle()) );
        out.write("</h3>\n\t");
      }
      if (_jspx_th_c_if_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
        return;
      }
      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
      out.write("\n\n\t");
      //  portlet:actionURL
      com.liferay.taglib.portlet.ActionURLTag _jspx_th_portlet_actionURL_0 = (com.liferay.taglib.portlet.ActionURLTag) _jspx_tagPool_portlet_actionURL_var_name.get(com.liferay.taglib.portlet.ActionURLTag.class);
      _jspx_th_portlet_actionURL_0.setPageContext(_jspx_page_context);
      _jspx_th_portlet_actionURL_0.setParent(null);
      _jspx_th_portlet_actionURL_0.setName("/message_boards/edit_category");
      _jspx_th_portlet_actionURL_0.setVar("editCategoryURL");
      int _jspx_eval_portlet_actionURL_0 = _jspx_th_portlet_actionURL_0.doStartTag();
      if (_jspx_eval_portlet_actionURL_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        out.write("\n\t\t");
        if (_jspx_meth_portlet_param_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_portlet_actionURL_0, _jspx_page_context))
          return;
        out.write('\n');
        out.write('	');
      }
      if (_jspx_th_portlet_actionURL_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_portlet_actionURL_var_name.reuse(_jspx_th_portlet_actionURL_0);
        return;
      }
      _jspx_tagPool_portlet_actionURL_var_name.reuse(_jspx_th_portlet_actionURL_0);
      java.lang.String editCategoryURL = null;
      editCategoryURL = (java.lang.String) _jspx_page_context.findAttribute("editCategoryURL");
      out.write("\n\n\t");
      //  aui:form
      com.liferay.taglib.aui.FormTag _jspx_th_aui_form_0 = (com.liferay.taglib.aui.FormTag) _jspx_tagPool_aui_form_onSubmit_name_method_action.get(com.liferay.taglib.aui.FormTag.class);
      _jspx_th_aui_form_0.setPageContext(_jspx_page_context);
      _jspx_th_aui_form_0.setParent(null);
      _jspx_th_aui_form_0.setAction( editCategoryURL );
      _jspx_th_aui_form_0.setMethod("post");
      _jspx_th_aui_form_0.setName("fm");
      _jspx_th_aui_form_0.setOnSubmit( "event.preventDefault(); " + renderResponse.getNamespace() + "saveCategory();" );
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
        _jspx_th_aui_input_1.setValue( redirect );
        int _jspx_eval_aui_input_1 = _jspx_th_aui_input_1.doStartTag();
        if (_jspx_th_aui_input_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_1);
          return;
        }
        _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_1);
        out.write("\n\t\t");
        //  aui:input
        com.liferay.taglib.aui.InputTag _jspx_th_aui_input_2 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
        _jspx_th_aui_input_2.setPageContext(_jspx_page_context);
        _jspx_th_aui_input_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
        _jspx_th_aui_input_2.setName("mbCategoryId");
        _jspx_th_aui_input_2.setType("hidden");
        _jspx_th_aui_input_2.setValue( categoryId );
        int _jspx_eval_aui_input_2 = _jspx_th_aui_input_2.doStartTag();
        if (_jspx_th_aui_input_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_2);
          return;
        }
        _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_2);
        out.write("\n\t\t");
        //  aui:input
        com.liferay.taglib.aui.InputTag _jspx_th_aui_input_3 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
        _jspx_th_aui_input_3.setPageContext(_jspx_page_context);
        _jspx_th_aui_input_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
        _jspx_th_aui_input_3.setName("parentCategoryId");
        _jspx_th_aui_input_3.setType("hidden");
        _jspx_th_aui_input_3.setValue( parentCategoryId );
        int _jspx_eval_aui_input_3 = _jspx_th_aui_input_3.doStartTag();
        if (_jspx_th_aui_input_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_3);
          return;
        }
        _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_3);
        out.write("\n\n\t\t");
        //  liferay-ui:error
        com.liferay.taglib.ui.ErrorTag _jspx_th_liferay$1ui_error_0 = (com.liferay.taglib.ui.ErrorTag) _jspx_tagPool_liferay$1ui_error_message_exception_nobody.get(com.liferay.taglib.ui.ErrorTag.class);
        _jspx_th_liferay$1ui_error_0.setPageContext(_jspx_page_context);
        _jspx_th_liferay$1ui_error_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
        _jspx_th_liferay$1ui_error_0.setException( CaptchaConfigurationException.class );
        _jspx_th_liferay$1ui_error_0.setMessage("a-captcha-error-occurred-please-contact-an-administrator");
        int _jspx_eval_liferay$1ui_error_0 = _jspx_th_liferay$1ui_error_0.doStartTag();
        if (_jspx_th_liferay$1ui_error_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_liferay$1ui_error_message_exception_nobody.reuse(_jspx_th_liferay$1ui_error_0);
          return;
        }
        _jspx_tagPool_liferay$1ui_error_message_exception_nobody.reuse(_jspx_th_liferay$1ui_error_0);
        out.write("\n\t\t");
        //  liferay-ui:error
        com.liferay.taglib.ui.ErrorTag _jspx_th_liferay$1ui_error_1 = (com.liferay.taglib.ui.ErrorTag) _jspx_tagPool_liferay$1ui_error_message_exception_nobody.get(com.liferay.taglib.ui.ErrorTag.class);
        _jspx_th_liferay$1ui_error_1.setPageContext(_jspx_page_context);
        _jspx_th_liferay$1ui_error_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
        _jspx_th_liferay$1ui_error_1.setException( CaptchaTextException.class );
        _jspx_th_liferay$1ui_error_1.setMessage("text-verification-failed");
        int _jspx_eval_liferay$1ui_error_1 = _jspx_th_liferay$1ui_error_1.doStartTag();
        if (_jspx_th_liferay$1ui_error_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_liferay$1ui_error_message_exception_nobody.reuse(_jspx_th_liferay$1ui_error_1);
          return;
        }
        _jspx_tagPool_liferay$1ui_error_message_exception_nobody.reuse(_jspx_th_liferay$1ui_error_1);
        out.write("\n\t\t");
        //  liferay-ui:error
        com.liferay.taglib.ui.ErrorTag _jspx_th_liferay$1ui_error_2 = (com.liferay.taglib.ui.ErrorTag) _jspx_tagPool_liferay$1ui_error_message_exception_nobody.get(com.liferay.taglib.ui.ErrorTag.class);
        _jspx_th_liferay$1ui_error_2.setPageContext(_jspx_page_context);
        _jspx_th_liferay$1ui_error_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
        _jspx_th_liferay$1ui_error_2.setException( CategoryNameException.class );
        _jspx_th_liferay$1ui_error_2.setMessage("please-enter-a-valid-name");
        int _jspx_eval_liferay$1ui_error_2 = _jspx_th_liferay$1ui_error_2.doStartTag();
        if (_jspx_th_liferay$1ui_error_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_liferay$1ui_error_message_exception_nobody.reuse(_jspx_th_liferay$1ui_error_2);
          return;
        }
        _jspx_tagPool_liferay$1ui_error_message_exception_nobody.reuse(_jspx_th_liferay$1ui_error_2);
        out.write("\n\t\t");
        //  liferay-ui:error
        com.liferay.taglib.ui.ErrorTag _jspx_th_liferay$1ui_error_3 = (com.liferay.taglib.ui.ErrorTag) _jspx_tagPool_liferay$1ui_error_message_exception_nobody.get(com.liferay.taglib.ui.ErrorTag.class);
        _jspx_th_liferay$1ui_error_3.setPageContext(_jspx_page_context);
        _jspx_th_liferay$1ui_error_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
        _jspx_th_liferay$1ui_error_3.setException( MailingListEmailAddressException.class );
        _jspx_th_liferay$1ui_error_3.setMessage("please-enter-a-valid-email-address");
        int _jspx_eval_liferay$1ui_error_3 = _jspx_th_liferay$1ui_error_3.doStartTag();
        if (_jspx_th_liferay$1ui_error_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_liferay$1ui_error_message_exception_nobody.reuse(_jspx_th_liferay$1ui_error_3);
          return;
        }
        _jspx_tagPool_liferay$1ui_error_message_exception_nobody.reuse(_jspx_th_liferay$1ui_error_3);
        out.write("\n\t\t");
        //  liferay-ui:error
        com.liferay.taglib.ui.ErrorTag _jspx_th_liferay$1ui_error_4 = (com.liferay.taglib.ui.ErrorTag) _jspx_tagPool_liferay$1ui_error_message_exception_nobody.get(com.liferay.taglib.ui.ErrorTag.class);
        _jspx_th_liferay$1ui_error_4.setPageContext(_jspx_page_context);
        _jspx_th_liferay$1ui_error_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
        _jspx_th_liferay$1ui_error_4.setException( MailingListInServerNameException.class );
        _jspx_th_liferay$1ui_error_4.setMessage("please-enter-a-valid-incoming-server-name");
        int _jspx_eval_liferay$1ui_error_4 = _jspx_th_liferay$1ui_error_4.doStartTag();
        if (_jspx_th_liferay$1ui_error_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_liferay$1ui_error_message_exception_nobody.reuse(_jspx_th_liferay$1ui_error_4);
          return;
        }
        _jspx_tagPool_liferay$1ui_error_message_exception_nobody.reuse(_jspx_th_liferay$1ui_error_4);
        out.write("\n\t\t");
        //  liferay-ui:error
        com.liferay.taglib.ui.ErrorTag _jspx_th_liferay$1ui_error_5 = (com.liferay.taglib.ui.ErrorTag) _jspx_tagPool_liferay$1ui_error_message_exception_nobody.get(com.liferay.taglib.ui.ErrorTag.class);
        _jspx_th_liferay$1ui_error_5.setPageContext(_jspx_page_context);
        _jspx_th_liferay$1ui_error_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
        _jspx_th_liferay$1ui_error_5.setException( MailingListInUserNameException.class );
        _jspx_th_liferay$1ui_error_5.setMessage("please-enter-a-valid-incoming-user-name");
        int _jspx_eval_liferay$1ui_error_5 = _jspx_th_liferay$1ui_error_5.doStartTag();
        if (_jspx_th_liferay$1ui_error_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_liferay$1ui_error_message_exception_nobody.reuse(_jspx_th_liferay$1ui_error_5);
          return;
        }
        _jspx_tagPool_liferay$1ui_error_message_exception_nobody.reuse(_jspx_th_liferay$1ui_error_5);
        out.write("\n\t\t");
        //  liferay-ui:error
        com.liferay.taglib.ui.ErrorTag _jspx_th_liferay$1ui_error_6 = (com.liferay.taglib.ui.ErrorTag) _jspx_tagPool_liferay$1ui_error_message_exception_nobody.get(com.liferay.taglib.ui.ErrorTag.class);
        _jspx_th_liferay$1ui_error_6.setPageContext(_jspx_page_context);
        _jspx_th_liferay$1ui_error_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
        _jspx_th_liferay$1ui_error_6.setException( MailingListOutEmailAddressException.class );
        _jspx_th_liferay$1ui_error_6.setMessage("please-enter-a-valid-outgoing-email-address");
        int _jspx_eval_liferay$1ui_error_6 = _jspx_th_liferay$1ui_error_6.doStartTag();
        if (_jspx_th_liferay$1ui_error_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_liferay$1ui_error_message_exception_nobody.reuse(_jspx_th_liferay$1ui_error_6);
          return;
        }
        _jspx_tagPool_liferay$1ui_error_message_exception_nobody.reuse(_jspx_th_liferay$1ui_error_6);
        out.write("\n\t\t");
        //  liferay-ui:error
        com.liferay.taglib.ui.ErrorTag _jspx_th_liferay$1ui_error_7 = (com.liferay.taglib.ui.ErrorTag) _jspx_tagPool_liferay$1ui_error_message_exception_nobody.get(com.liferay.taglib.ui.ErrorTag.class);
        _jspx_th_liferay$1ui_error_7.setPageContext(_jspx_page_context);
        _jspx_th_liferay$1ui_error_7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
        _jspx_th_liferay$1ui_error_7.setException( MailingListOutServerNameException.class );
        _jspx_th_liferay$1ui_error_7.setMessage("please-enter-a-valid-outgoing-server-name");
        int _jspx_eval_liferay$1ui_error_7 = _jspx_th_liferay$1ui_error_7.doStartTag();
        if (_jspx_th_liferay$1ui_error_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_liferay$1ui_error_message_exception_nobody.reuse(_jspx_th_liferay$1ui_error_7);
          return;
        }
        _jspx_tagPool_liferay$1ui_error_message_exception_nobody.reuse(_jspx_th_liferay$1ui_error_7);
        out.write("\n\t\t");
        //  liferay-ui:error
        com.liferay.taglib.ui.ErrorTag _jspx_th_liferay$1ui_error_8 = (com.liferay.taglib.ui.ErrorTag) _jspx_tagPool_liferay$1ui_error_message_exception_nobody.get(com.liferay.taglib.ui.ErrorTag.class);
        _jspx_th_liferay$1ui_error_8.setPageContext(_jspx_page_context);
        _jspx_th_liferay$1ui_error_8.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
        _jspx_th_liferay$1ui_error_8.setException( MailingListOutUserNameException.class );
        _jspx_th_liferay$1ui_error_8.setMessage("please-enter-a-valid-outgoing-user-name");
        int _jspx_eval_liferay$1ui_error_8 = _jspx_th_liferay$1ui_error_8.doStartTag();
        if (_jspx_th_liferay$1ui_error_8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_liferay$1ui_error_message_exception_nobody.reuse(_jspx_th_liferay$1ui_error_8);
          return;
        }
        _jspx_tagPool_liferay$1ui_error_message_exception_nobody.reuse(_jspx_th_liferay$1ui_error_8);
        out.write("\n\n\t\t");
        //  aui:model-context
        com.liferay.taglib.aui.ModelContextTag _jspx_th_aui_model$1context_0 = (com.liferay.taglib.aui.ModelContextTag) _jspx_tagPool_aui_model$1context_model_bean_nobody.get(com.liferay.taglib.aui.ModelContextTag.class);
        _jspx_th_aui_model$1context_0.setPageContext(_jspx_page_context);
        _jspx_th_aui_model$1context_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
        _jspx_th_aui_model$1context_0.setBean( category );
        _jspx_th_aui_model$1context_0.setModel( MBCategory.class );
        int _jspx_eval_aui_model$1context_0 = _jspx_th_aui_model$1context_0.doStartTag();
        if (_jspx_th_aui_model$1context_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_aui_model$1context_model_bean_nobody.reuse(_jspx_th_aui_model$1context_0);
          return;
        }
        _jspx_tagPool_aui_model$1context_model_bean_nobody.reuse(_jspx_th_aui_model$1context_0);
        out.write("\n\n\t\t");
        //  aui:fieldset-group
        com.liferay.taglib.aui.FieldsetGroupTag _jspx_th_aui_fieldset$1group_0 = (com.liferay.taglib.aui.FieldsetGroupTag) _jspx_tagPool_aui_fieldset$1group_markupView.get(com.liferay.taglib.aui.FieldsetGroupTag.class);
        _jspx_th_aui_fieldset$1group_0.setPageContext(_jspx_page_context);
        _jspx_th_aui_fieldset$1group_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
        _jspx_th_aui_fieldset$1group_0.setMarkupView("lexicon");
        int _jspx_eval_aui_fieldset$1group_0 = _jspx_th_aui_fieldset$1group_0.doStartTag();
        if (_jspx_eval_aui_fieldset$1group_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          out.write("\n\t\t\t");
          //  aui:fieldset
          com.liferay.taglib.aui.FieldsetTag _jspx_th_aui_fieldset_0 = (com.liferay.taglib.aui.FieldsetTag) _jspx_tagPool_aui_fieldset.get(com.liferay.taglib.aui.FieldsetTag.class);
          _jspx_th_aui_fieldset_0.setPageContext(_jspx_page_context);
          _jspx_th_aui_fieldset_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset$1group_0);
          int _jspx_eval_aui_fieldset_0 = _jspx_th_aui_fieldset_0.doStartTag();
          if (_jspx_eval_aui_fieldset_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t\t\t");
            //  c:if
            com.liferay.taglib.core.IfTag _jspx_th_c_if_1 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
            _jspx_th_c_if_1.setPageContext(_jspx_page_context);
            _jspx_th_c_if_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_0);
            _jspx_th_c_if_1.setTest( parentCategoryId != MBCategoryConstants.DEFAULT_PARENT_CATEGORY_ID );
            int _jspx_eval_c_if_1 = _jspx_th_c_if_1.doStartTag();
            if (_jspx_eval_c_if_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\n\t\t\t\t\t");

					String parentCategoryName = StringPool.BLANK;

					try {
						MBCategory parentCategory = MBCategoryLocalServiceUtil.getCategory(parentCategoryId);

						parentCategoryName = parentCategory.getName();
					}
					catch (NoSuchCategoryException nsce) {
					}
					
              out.write("\n\n\t\t\t\t\t");
              //  c:if
              com.liferay.taglib.core.IfTag _jspx_th_c_if_2 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
              _jspx_th_c_if_2.setPageContext(_jspx_page_context);
              _jspx_th_c_if_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_1);
              _jspx_th_c_if_2.setTest( category != null );
              int _jspx_eval_c_if_2 = _jspx_th_c_if_2.doStartTag();
              if (_jspx_eval_c_if_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\t\t\t\t\t\t");
                //  aui:input
                com.liferay.taglib.aui.InputTag _jspx_th_aui_input_4 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_label_nobody.get(com.liferay.taglib.aui.InputTag.class);
                _jspx_th_aui_input_4.setPageContext(_jspx_page_context);
                _jspx_th_aui_input_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_2);
                _jspx_th_aui_input_4.setLabel("parent-category[message-board]");
                _jspx_th_aui_input_4.setName("parentCategoryName");
                _jspx_th_aui_input_4.setType("resource");
                _jspx_th_aui_input_4.setValue( parentCategoryName );
                int _jspx_eval_aui_input_4 = _jspx_th_aui_input_4.doStartTag();
                if (_jspx_th_aui_input_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_aui_input_value_type_name_label_nobody.reuse(_jspx_th_aui_input_4);
                  return;
                }
                _jspx_tagPool_aui_input_value_type_name_label_nobody.reuse(_jspx_th_aui_input_4);
                out.write("\n\t\t\t\t\t");
              }
              if (_jspx_th_c_if_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_2);
                return;
              }
              _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_2);
              out.write("\n\t\t\t\t");
            }
            if (_jspx_th_c_if_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_1);
              return;
            }
            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_1);
            out.write("\n\n\t\t\t\t");
            //  aui:input
            com.liferay.taglib.aui.InputTag _jspx_th_aui_input_5 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_name_autoFocus_nobody.get(com.liferay.taglib.aui.InputTag.class);
            _jspx_th_aui_input_5.setPageContext(_jspx_page_context);
            _jspx_th_aui_input_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_0);
            _jspx_th_aui_input_5.setAutoFocus( windowState.equals(WindowState.MAXIMIZED) );
            _jspx_th_aui_input_5.setName("name");
            int _jspx_eval_aui_input_5 = _jspx_th_aui_input_5.doStartTag();
            if (_jspx_th_aui_input_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_aui_input_name_autoFocus_nobody.reuse(_jspx_th_aui_input_5);
              return;
            }
            _jspx_tagPool_aui_input_name_autoFocus_nobody.reuse(_jspx_th_aui_input_5);
            out.write("\n\n\t\t\t\t");
            if (_jspx_meth_aui_input_6((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_fieldset_0, _jspx_page_context))
              return;
            out.write("\n\n\t\t\t\t");
            //  aui:select
            com.liferay.taglib.aui.SelectTag _jspx_th_aui_select_0 = (com.liferay.taglib.aui.SelectTag) _jspx_tagPool_aui_select_name.get(com.liferay.taglib.aui.SelectTag.class);
            _jspx_th_aui_select_0.setPageContext(_jspx_page_context);
            _jspx_th_aui_select_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_0);
            _jspx_th_aui_select_0.setName("displayStyle");
            int _jspx_eval_aui_select_0 = _jspx_th_aui_select_0.doStartTag();
            if (_jspx_eval_aui_select_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              if (_jspx_eval_aui_select_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                out = _jspx_page_context.pushBody();
                _jspx_th_aui_select_0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                _jspx_th_aui_select_0.doInitBody();
              }
              do {
                out.write("\n\n\t\t\t\t\t");

					for (int i = 0; i < MBCategoryConstants.DISPLAY_STYLES.length; i++) {
					
                out.write("\n\n\t\t\t\t\t\t");
                //  aui:option
                com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_0 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_selected_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
                _jspx_th_aui_option_0.setPageContext(_jspx_page_context);
                _jspx_th_aui_option_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_0);
                _jspx_th_aui_option_0.setLabel( MBCategoryConstants.DISPLAY_STYLES[i] );
                _jspx_th_aui_option_0.setSelected( displayStyle.equals(MBCategoryConstants.DISPLAY_STYLES[i]) );
                int _jspx_eval_aui_option_0 = _jspx_th_aui_option_0.doStartTag();
                if (_jspx_th_aui_option_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_aui_option_selected_label_nobody.reuse(_jspx_th_aui_option_0);
                  return;
                }
                _jspx_tagPool_aui_option_selected_label_nobody.reuse(_jspx_th_aui_option_0);
                out.write("\n\n\t\t\t\t\t");

					}
					
                out.write("\n\n\t\t\t\t");
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
            out.write("\n\t\t\t");
          }
          if (_jspx_th_aui_fieldset_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_aui_fieldset.reuse(_jspx_th_aui_fieldset_0);
            return;
          }
          _jspx_tagPool_aui_fieldset.reuse(_jspx_th_aui_fieldset_0);
          out.write("\n\n\t\t\t");
          //  aui:fieldset
          com.liferay.taglib.aui.FieldsetTag _jspx_th_aui_fieldset_1 = (com.liferay.taglib.aui.FieldsetTag) _jspx_tagPool_aui_fieldset_label_collapsible_collapsed.get(com.liferay.taglib.aui.FieldsetTag.class);
          _jspx_th_aui_fieldset_1.setPageContext(_jspx_page_context);
          _jspx_th_aui_fieldset_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset$1group_0);
          _jspx_th_aui_fieldset_1.setCollapsed( true );
          _jspx_th_aui_fieldset_1.setCollapsible( true );
          _jspx_th_aui_fieldset_1.setLabel("mailing-list");
          int _jspx_eval_aui_fieldset_1 = _jspx_th_aui_fieldset_1.doStartTag();
          if (_jspx_eval_aui_fieldset_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t\t\t");
            //  aui:model-context
            com.liferay.taglib.aui.ModelContextTag _jspx_th_aui_model$1context_1 = (com.liferay.taglib.aui.ModelContextTag) _jspx_tagPool_aui_model$1context_model_bean_nobody.get(com.liferay.taglib.aui.ModelContextTag.class);
            _jspx_th_aui_model$1context_1.setPageContext(_jspx_page_context);
            _jspx_th_aui_model$1context_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_1);
            _jspx_th_aui_model$1context_1.setBean( mailingList );
            _jspx_th_aui_model$1context_1.setModel( MBMailingList.class );
            int _jspx_eval_aui_model$1context_1 = _jspx_th_aui_model$1context_1.doStartTag();
            if (_jspx_th_aui_model$1context_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_aui_model$1context_model_bean_nobody.reuse(_jspx_th_aui_model$1context_1);
              return;
            }
            _jspx_tagPool_aui_model$1context_model_bean_nobody.reuse(_jspx_th_aui_model$1context_1);
            out.write("\n\n\t\t\t\t");
            //  aui:input
            com.liferay.taglib.aui.InputTag _jspx_th_aui_input_7 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_label_nobody.get(com.liferay.taglib.aui.InputTag.class);
            _jspx_th_aui_input_7.setPageContext(_jspx_page_context);
            _jspx_th_aui_input_7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_1);
            _jspx_th_aui_input_7.setLabel("active");
            _jspx_th_aui_input_7.setName("mailingListActive");
            _jspx_th_aui_input_7.setType("toggle-switch");
            _jspx_th_aui_input_7.setValue( ParamUtil.getBoolean(request, "mailingListActive", BeanPropertiesUtil.getBoolean(mailingList, "active")) );
            int _jspx_eval_aui_input_7 = _jspx_th_aui_input_7.doStartTag();
            if (_jspx_th_aui_input_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_aui_input_value_type_name_label_nobody.reuse(_jspx_th_aui_input_7);
              return;
            }
            _jspx_tagPool_aui_input_value_type_name_label_nobody.reuse(_jspx_th_aui_input_7);
            out.write("\n\n\t\t\t\t");
            //  aui:input
            com.liferay.taglib.aui.InputTag _jspx_th_aui_input_8 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_label_nobody.get(com.liferay.taglib.aui.InputTag.class);
            _jspx_th_aui_input_8.setPageContext(_jspx_page_context);
            _jspx_th_aui_input_8.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_1);
            _jspx_th_aui_input_8.setLabel("allow-anonymous-emails");
            _jspx_th_aui_input_8.setName("allowAnonymous");
            _jspx_th_aui_input_8.setType("toggle-switch");
            _jspx_th_aui_input_8.setValue( BeanParamUtil.getBoolean(mailingList, request, "allowAnonymous") );
            int _jspx_eval_aui_input_8 = _jspx_th_aui_input_8.doStartTag();
            if (_jspx_th_aui_input_8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_aui_input_value_type_name_label_nobody.reuse(_jspx_th_aui_input_8);
              return;
            }
            _jspx_tagPool_aui_input_value_type_name_label_nobody.reuse(_jspx_th_aui_input_8);
            out.write("\n\n\t\t\t\t<div id=\"");
            if (_jspx_meth_portlet_namespace_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_fieldset_1, _jspx_page_context))
              return;
            out.write("mailingListSettings\">\n\t\t\t\t\t");
            if (_jspx_meth_aui_input_9((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_fieldset_1, _jspx_page_context))
              return;
            out.write("\n\n\t\t\t\t\t<br />\n\n\t\t\t\t\t");
            //  aui:fieldset
            com.liferay.taglib.aui.FieldsetTag _jspx_th_aui_fieldset_2 = (com.liferay.taglib.aui.FieldsetTag) _jspx_tagPool_aui_fieldset_label.get(com.liferay.taglib.aui.FieldsetTag.class);
            _jspx_th_aui_fieldset_2.setPageContext(_jspx_page_context);
            _jspx_th_aui_fieldset_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_1);
            _jspx_th_aui_fieldset_2.setLabel("incoming");
            int _jspx_eval_aui_fieldset_2 = _jspx_th_aui_fieldset_2.doStartTag();
            if (_jspx_eval_aui_fieldset_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\n\t\t\t\t\t\t");

						String protocol = BeanParamUtil.getString(mailingList, request, "inProtocol", "pop3");
						
              out.write("\n\n\t\t\t\t\t\t");
              //  aui:field-wrapper
              com.liferay.taglib.aui.FieldWrapperTag _jspx_th_aui_field$1wrapper_0 = (com.liferay.taglib.aui.FieldWrapperTag) _jspx_tagPool_aui_field$1wrapper_label.get(com.liferay.taglib.aui.FieldWrapperTag.class);
              _jspx_th_aui_field$1wrapper_0.setPageContext(_jspx_page_context);
              _jspx_th_aui_field$1wrapper_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_2);
              _jspx_th_aui_field$1wrapper_0.setLabel("protocol");
              int _jspx_eval_aui_field$1wrapper_0 = _jspx_th_aui_field$1wrapper_0.doStartTag();
              if (_jspx_eval_aui_field$1wrapper_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\t\t\t\t\t\t\t");
                //  aui:input
                com.liferay.taglib.aui.InputTag _jspx_th_aui_input_10 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_label_checked_nobody.get(com.liferay.taglib.aui.InputTag.class);
                _jspx_th_aui_input_10.setPageContext(_jspx_page_context);
                _jspx_th_aui_input_10.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_field$1wrapper_0);
                _jspx_th_aui_input_10.setChecked( protocol.startsWith("pop3") );
                _jspx_th_aui_input_10.setLabel("pop");
                _jspx_th_aui_input_10.setName("inProtocol");
                _jspx_th_aui_input_10.setType("radio");
                _jspx_th_aui_input_10.setValue(new String("pop3"));
                int _jspx_eval_aui_input_10 = _jspx_th_aui_input_10.doStartTag();
                if (_jspx_th_aui_input_10.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_aui_input_value_type_name_label_checked_nobody.reuse(_jspx_th_aui_input_10);
                  return;
                }
                _jspx_tagPool_aui_input_value_type_name_label_checked_nobody.reuse(_jspx_th_aui_input_10);
                out.write("\n\t\t\t\t\t\t\t");
                //  aui:input
                com.liferay.taglib.aui.InputTag _jspx_th_aui_input_11 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_label_checked_nobody.get(com.liferay.taglib.aui.InputTag.class);
                _jspx_th_aui_input_11.setPageContext(_jspx_page_context);
                _jspx_th_aui_input_11.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_field$1wrapper_0);
                _jspx_th_aui_input_11.setChecked( protocol.startsWith("imap") );
                _jspx_th_aui_input_11.setLabel("imap");
                _jspx_th_aui_input_11.setName("inProtocol");
                _jspx_th_aui_input_11.setType("radio");
                _jspx_th_aui_input_11.setValue(new String("imap"));
                int _jspx_eval_aui_input_11 = _jspx_th_aui_input_11.doStartTag();
                if (_jspx_th_aui_input_11.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_aui_input_value_type_name_label_checked_nobody.reuse(_jspx_th_aui_input_11);
                  return;
                }
                _jspx_tagPool_aui_input_value_type_name_label_checked_nobody.reuse(_jspx_th_aui_input_11);
                out.write("\n\t\t\t\t\t\t");
              }
              if (_jspx_th_aui_field$1wrapper_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_aui_field$1wrapper_label.reuse(_jspx_th_aui_field$1wrapper_0);
                return;
              }
              _jspx_tagPool_aui_field$1wrapper_label.reuse(_jspx_th_aui_field$1wrapper_0);
              out.write("\n\n\t\t\t\t\t\t");
              if (_jspx_meth_aui_input_12((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_fieldset_2, _jspx_page_context))
                return;
              out.write("\n\n\t\t\t\t\t\t");
              if (_jspx_meth_aui_input_13((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_fieldset_2, _jspx_page_context))
                return;
              out.write("\n\n\t\t\t\t\t\t");
              if (_jspx_meth_aui_input_14((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_fieldset_2, _jspx_page_context))
                return;
              out.write("\n\n\t\t\t\t\t\t");
              if (_jspx_meth_aui_input_15((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_fieldset_2, _jspx_page_context))
                return;
              out.write("\n\n\t\t\t\t\t\t");
              if (_jspx_meth_aui_input_16((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_fieldset_2, _jspx_page_context))
                return;
              out.write("\n\n\t\t\t\t\t\t");
              if (_jspx_meth_aui_input_17((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_fieldset_2, _jspx_page_context))
                return;
              out.write("\n\t\t\t\t\t");
            }
            if (_jspx_th_aui_fieldset_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_aui_fieldset_label.reuse(_jspx_th_aui_fieldset_2);
              return;
            }
            _jspx_tagPool_aui_fieldset_label.reuse(_jspx_th_aui_fieldset_2);
            out.write("\n\n\t\t\t\t\t");
            if (_jspx_meth_aui_fieldset_3((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_fieldset_1, _jspx_page_context))
              return;
            out.write("\n\t\t\t\t</div>\n\n\t\t\t\t");
            //  c:if
            com.liferay.taglib.core.IfTag _jspx_th_c_if_3 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
            _jspx_th_c_if_3.setPageContext(_jspx_page_context);
            _jspx_th_c_if_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_1);
            _jspx_th_c_if_3.setTest( (category == null) && captchaConfiguration.messageBoardsEditCategoryCaptchaEnabled() );
            int _jspx_eval_c_if_3 = _jspx_th_c_if_3.doStartTag();
            if (_jspx_eval_c_if_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\t\t\t\t\t");
              //  portlet:resourceURL
              com.liferay.taglib.portlet.ResourceURLTag _jspx_th_portlet_resourceURL_0 = (com.liferay.taglib.portlet.ResourceURLTag) _jspx_tagPool_portlet_resourceURL_var_id_nobody.get(com.liferay.taglib.portlet.ResourceURLTag.class);
              _jspx_th_portlet_resourceURL_0.setPageContext(_jspx_page_context);
              _jspx_th_portlet_resourceURL_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_3);
              _jspx_th_portlet_resourceURL_0.setId("/message_boards/captcha");
              _jspx_th_portlet_resourceURL_0.setVar("captchaURL");
              int _jspx_eval_portlet_resourceURL_0 = _jspx_th_portlet_resourceURL_0.doStartTag();
              if (_jspx_th_portlet_resourceURL_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_portlet_resourceURL_var_id_nobody.reuse(_jspx_th_portlet_resourceURL_0);
                return;
              }
              _jspx_tagPool_portlet_resourceURL_var_id_nobody.reuse(_jspx_th_portlet_resourceURL_0);
              java.lang.String captchaURL = null;
              captchaURL = (java.lang.String) _jspx_page_context.findAttribute("captchaURL");
              out.write("\n\n\t\t\t\t\t");
              //  liferay-captcha:captcha
              com.liferay.captcha.taglib.servlet.taglib.CaptchaTag _jspx_th_liferay$1captcha_captcha_0 = (com.liferay.captcha.taglib.servlet.taglib.CaptchaTag) _jspx_tagPool_liferay$1captcha_captcha_url_nobody.get(com.liferay.captcha.taglib.servlet.taglib.CaptchaTag.class);
              _jspx_th_liferay$1captcha_captcha_0.setPageContext(_jspx_page_context);
              _jspx_th_liferay$1captcha_captcha_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_3);
              _jspx_th_liferay$1captcha_captcha_0.setUrl( captchaURL );
              int _jspx_eval_liferay$1captcha_captcha_0 = _jspx_th_liferay$1captcha_captcha_0.doStartTag();
              if (_jspx_th_liferay$1captcha_captcha_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_liferay$1captcha_captcha_url_nobody.reuse(_jspx_th_liferay$1captcha_captcha_0);
                return;
              }
              _jspx_tagPool_liferay$1captcha_captcha_url_nobody.reuse(_jspx_th_liferay$1captcha_captcha_0);
              out.write("\n\t\t\t\t");
            }
            if (_jspx_th_c_if_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_3);
              return;
            }
            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_3);
            out.write("\n\t\t\t");
          }
          if (_jspx_th_aui_fieldset_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_aui_fieldset_label_collapsible_collapsed.reuse(_jspx_th_aui_fieldset_1);
            return;
          }
          _jspx_tagPool_aui_fieldset_label_collapsible_collapsed.reuse(_jspx_th_aui_fieldset_1);
          out.write("\n\n\t\t\t");
          //  liferay-expando:custom-attributes-available
          com.liferay.expando.taglib.servlet.taglib.CustomAttributesAvailableTag _jspx_th_liferay$1expando_custom$1attributes$1available_0 = (com.liferay.expando.taglib.servlet.taglib.CustomAttributesAvailableTag) _jspx_tagPool_liferay$1expando_custom$1attributes$1available_className.get(com.liferay.expando.taglib.servlet.taglib.CustomAttributesAvailableTag.class);
          _jspx_th_liferay$1expando_custom$1attributes$1available_0.setPageContext(_jspx_page_context);
          _jspx_th_liferay$1expando_custom$1attributes$1available_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset$1group_0);
          _jspx_th_liferay$1expando_custom$1attributes$1available_0.setClassName( MBCategory.class.getName() );
          int _jspx_eval_liferay$1expando_custom$1attributes$1available_0 = _jspx_th_liferay$1expando_custom$1attributes$1available_0.doStartTag();
          if (_jspx_eval_liferay$1expando_custom$1attributes$1available_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t\t\t");
            //  aui:fieldset
            com.liferay.taglib.aui.FieldsetTag _jspx_th_aui_fieldset_4 = (com.liferay.taglib.aui.FieldsetTag) _jspx_tagPool_aui_fieldset_label_collapsible_collapsed.get(com.liferay.taglib.aui.FieldsetTag.class);
            _jspx_th_aui_fieldset_4.setPageContext(_jspx_page_context);
            _jspx_th_aui_fieldset_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1expando_custom$1attributes$1available_0);
            _jspx_th_aui_fieldset_4.setCollapsed( true );
            _jspx_th_aui_fieldset_4.setCollapsible( true );
            _jspx_th_aui_fieldset_4.setLabel("custom-fields");
            int _jspx_eval_aui_fieldset_4 = _jspx_th_aui_fieldset_4.doStartTag();
            if (_jspx_eval_aui_fieldset_4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\t\t\t\t\t");
              //  liferay-expando:custom-attribute-list
              com.liferay.expando.taglib.servlet.taglib.CustomAttributeListTag _jspx_th_liferay$1expando_custom$1attribute$1list_0 = (com.liferay.expando.taglib.servlet.taglib.CustomAttributeListTag) _jspx_tagPool_liferay$1expando_custom$1attribute$1list_label_editable_classPK_className_nobody.get(com.liferay.expando.taglib.servlet.taglib.CustomAttributeListTag.class);
              _jspx_th_liferay$1expando_custom$1attribute$1list_0.setPageContext(_jspx_page_context);
              _jspx_th_liferay$1expando_custom$1attribute$1list_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_4);
              _jspx_th_liferay$1expando_custom$1attribute$1list_0.setClassName( MBCategory.class.getName() );
              _jspx_th_liferay$1expando_custom$1attribute$1list_0.setClassPK( (category != null) ? category.getCategoryId() : 0 );
              _jspx_th_liferay$1expando_custom$1attribute$1list_0.setEditable( true );
              _jspx_th_liferay$1expando_custom$1attribute$1list_0.setLabel( true );
              int _jspx_eval_liferay$1expando_custom$1attribute$1list_0 = _jspx_th_liferay$1expando_custom$1attribute$1list_0.doStartTag();
              if (_jspx_th_liferay$1expando_custom$1attribute$1list_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_liferay$1expando_custom$1attribute$1list_label_editable_classPK_className_nobody.reuse(_jspx_th_liferay$1expando_custom$1attribute$1list_0);
                return;
              }
              _jspx_tagPool_liferay$1expando_custom$1attribute$1list_label_editable_classPK_className_nobody.reuse(_jspx_th_liferay$1expando_custom$1attribute$1list_0);
              out.write("\n\t\t\t\t");
            }
            if (_jspx_th_aui_fieldset_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_aui_fieldset_label_collapsible_collapsed.reuse(_jspx_th_aui_fieldset_4);
              return;
            }
            _jspx_tagPool_aui_fieldset_label_collapsible_collapsed.reuse(_jspx_th_aui_fieldset_4);
            out.write("\n\t\t\t");
          }
          if (_jspx_th_liferay$1expando_custom$1attributes$1available_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_liferay$1expando_custom$1attributes$1available_className.reuse(_jspx_th_liferay$1expando_custom$1attributes$1available_0);
            return;
          }
          _jspx_tagPool_liferay$1expando_custom$1attributes$1available_className.reuse(_jspx_th_liferay$1expando_custom$1attributes$1available_0);
          out.write("\n\n\t\t\t");
          //  c:if
          com.liferay.taglib.core.IfTag _jspx_th_c_if_4 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
          _jspx_th_c_if_4.setPageContext(_jspx_page_context);
          _jspx_th_c_if_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset$1group_0);
          _jspx_th_c_if_4.setTest( category == null );
          int _jspx_eval_c_if_4 = _jspx_th_c_if_4.doStartTag();
          if (_jspx_eval_c_if_4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t\t\t");
            //  aui:fieldset
            com.liferay.taglib.aui.FieldsetTag _jspx_th_aui_fieldset_5 = (com.liferay.taglib.aui.FieldsetTag) _jspx_tagPool_aui_fieldset_label_collapsible_collapsed.get(com.liferay.taglib.aui.FieldsetTag.class);
            _jspx_th_aui_fieldset_5.setPageContext(_jspx_page_context);
            _jspx_th_aui_fieldset_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_4);
            _jspx_th_aui_fieldset_5.setCollapsed( true );
            _jspx_th_aui_fieldset_5.setCollapsible( true );
            _jspx_th_aui_fieldset_5.setLabel("permissions");
            int _jspx_eval_aui_fieldset_5 = _jspx_th_aui_fieldset_5.doStartTag();
            if (_jspx_eval_aui_fieldset_5 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\t\t\t\t\t");
              //  liferay-ui:input-permissions
              com.liferay.taglib.ui.InputPermissionsTag _jspx_th_liferay$1ui_input$1permissions_0 = (com.liferay.taglib.ui.InputPermissionsTag) _jspx_tagPool_liferay$1ui_input$1permissions_modelName_nobody.get(com.liferay.taglib.ui.InputPermissionsTag.class);
              _jspx_th_liferay$1ui_input$1permissions_0.setPageContext(_jspx_page_context);
              _jspx_th_liferay$1ui_input$1permissions_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_5);
              _jspx_th_liferay$1ui_input$1permissions_0.setModelName( MBCategory.class.getName() );
              int _jspx_eval_liferay$1ui_input$1permissions_0 = _jspx_th_liferay$1ui_input$1permissions_0.doStartTag();
              if (_jspx_th_liferay$1ui_input$1permissions_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_liferay$1ui_input$1permissions_modelName_nobody.reuse(_jspx_th_liferay$1ui_input$1permissions_0);
                return;
              }
              _jspx_tagPool_liferay$1ui_input$1permissions_modelName_nobody.reuse(_jspx_th_liferay$1ui_input$1permissions_0);
              out.write("\n\t\t\t\t");
            }
            if (_jspx_th_aui_fieldset_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_aui_fieldset_label_collapsible_collapsed.reuse(_jspx_th_aui_fieldset_5);
              return;
            }
            _jspx_tagPool_aui_fieldset_label_collapsible_collapsed.reuse(_jspx_th_aui_fieldset_5);
            out.write("\n\t\t\t");
          }
          if (_jspx_th_c_if_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_4);
            return;
          }
          _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_4);
          out.write("\n\t\t");
        }
        if (_jspx_th_aui_fieldset$1group_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_aui_fieldset$1group_markupView.reuse(_jspx_th_aui_fieldset$1group_0);
          return;
        }
        _jspx_tagPool_aui_fieldset$1group_markupView.reuse(_jspx_th_aui_fieldset$1group_0);
        out.write("\n\n\t\t");
        //  aui:button-row
        com.liferay.taglib.aui.ButtonRowTag _jspx_th_aui_button$1row_0 = (com.liferay.taglib.aui.ButtonRowTag) _jspx_tagPool_aui_button$1row.get(com.liferay.taglib.aui.ButtonRowTag.class);
        _jspx_th_aui_button$1row_0.setPageContext(_jspx_page_context);
        _jspx_th_aui_button$1row_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
        int _jspx_eval_aui_button$1row_0 = _jspx_th_aui_button$1row_0.doStartTag();
        if (_jspx_eval_aui_button$1row_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          out.write("\n\t\t\t");
          if (_jspx_meth_aui_button_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_button$1row_0, _jspx_page_context))
            return;
          out.write("\n\n\t\t\t");
          //  aui:button
          com.liferay.taglib.aui.ButtonTag _jspx_th_aui_button_1 = (com.liferay.taglib.aui.ButtonTag) _jspx_tagPool_aui_button_type_href_nobody.get(com.liferay.taglib.aui.ButtonTag.class);
          _jspx_th_aui_button_1.setPageContext(_jspx_page_context);
          _jspx_th_aui_button_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_button$1row_0);
          _jspx_th_aui_button_1.setHref( redirect );
          _jspx_th_aui_button_1.setType("cancel");
          int _jspx_eval_aui_button_1 = _jspx_th_aui_button_1.doStartTag();
          if (_jspx_th_aui_button_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_aui_button_type_href_nobody.reuse(_jspx_th_aui_button_1);
            return;
          }
          _jspx_tagPool_aui_button_type_href_nobody.reuse(_jspx_th_aui_button_1);
          out.write("\n\t\t");
        }
        if (_jspx_th_aui_button$1row_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_aui_button$1row.reuse(_jspx_th_aui_button$1row_0);
          return;
        }
        _jspx_tagPool_aui_button$1row.reuse(_jspx_th_aui_button$1row_0);
        out.write('\n');
        out.write('	');
      }
      if (_jspx_th_aui_form_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_aui_form_onSubmit_name_method_action.reuse(_jspx_th_aui_form_0);
        return;
      }
      _jspx_tagPool_aui_form_onSubmit_name_method_action.reuse(_jspx_th_aui_form_0);
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
          out.write("\n\tfunction ");
          if (_jspx_meth_portlet_namespace_2((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_0, _jspx_page_context))
            return;
          out.write("saveCategory() {\n\t\tdocument.");
          if (_jspx_meth_portlet_namespace_3((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_0, _jspx_page_context))
            return;
          out.write("fm.");
          if (_jspx_meth_portlet_namespace_4((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_0, _jspx_page_context))
            return;
          out.print( Constants.CMD );
          out.write(".value = '");
          out.print( (category == null) ? Constants.ADD : Constants.UPDATE );
          out.write("';\n\n\t\tsubmitForm(document.");
          if (_jspx_meth_portlet_namespace_5((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_0, _jspx_page_context))
            return;
          out.write("fm);\n\t}\n\n\tLiferay.Util.toggleBoxes('");
          if (_jspx_meth_portlet_namespace_6((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_0, _jspx_page_context))
            return;
          out.write("mailingListActive', '");
          if (_jspx_meth_portlet_namespace_7((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_0, _jspx_page_context))
            return;
          out.write("mailingListSettings');\n\tLiferay.Util.toggleBoxes('");
          if (_jspx_meth_portlet_namespace_8((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_0, _jspx_page_context))
            return;
          out.write("outCustom', '");
          if (_jspx_meth_portlet_namespace_9((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_0, _jspx_page_context))
            return;
          out.write("outCustomSettings');\n");
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

  private boolean _jspx_meth_portlet_param_0(javax.servlet.jsp.tagext.JspTag _jspx_th_portlet_actionURL_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:param
    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_0 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
    _jspx_th_portlet_param_0.setPageContext(_jspx_page_context);
    _jspx_th_portlet_param_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_actionURL_0);
    _jspx_th_portlet_param_0.setName("mvcRenderCommandName");
    _jspx_th_portlet_param_0.setValue("/message_boards/edit_category");
    int _jspx_eval_portlet_param_0 = _jspx_th_portlet_param_0.doStartTag();
    if (_jspx_th_portlet_param_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_0);
      return true;
    }
    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_0);
    return false;
  }

  private boolean _jspx_meth_aui_input_6(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_fieldset_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:input
    com.liferay.taglib.aui.InputTag _jspx_th_aui_input_6 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
    _jspx_th_aui_input_6.setPageContext(_jspx_page_context);
    _jspx_th_aui_input_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_0);
    _jspx_th_aui_input_6.setName("description");
    int _jspx_eval_aui_input_6 = _jspx_th_aui_input_6.doStartTag();
    if (_jspx_th_aui_input_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_input_name_nobody.reuse(_jspx_th_aui_input_6);
      return true;
    }
    _jspx_tagPool_aui_input_name_nobody.reuse(_jspx_th_aui_input_6);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_0(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_fieldset_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_0 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_0.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_1);
    int _jspx_eval_portlet_namespace_0 = _jspx_th_portlet_namespace_0.doStartTag();
    if (_jspx_th_portlet_namespace_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_0);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_0);
    return false;
  }

  private boolean _jspx_meth_aui_input_9(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_fieldset_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:input
    com.liferay.taglib.aui.InputTag _jspx_th_aui_input_9 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
    _jspx_th_aui_input_9.setPageContext(_jspx_page_context);
    _jspx_th_aui_input_9.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_1);
    _jspx_th_aui_input_9.setName("emailAddress");
    int _jspx_eval_aui_input_9 = _jspx_th_aui_input_9.doStartTag();
    if (_jspx_th_aui_input_9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_input_name_nobody.reuse(_jspx_th_aui_input_9);
      return true;
    }
    _jspx_tagPool_aui_input_name_nobody.reuse(_jspx_th_aui_input_9);
    return false;
  }

  private boolean _jspx_meth_aui_input_12(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_fieldset_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:input
    com.liferay.taglib.aui.InputTag _jspx_th_aui_input_12 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_name_label_nobody.get(com.liferay.taglib.aui.InputTag.class);
    _jspx_th_aui_input_12.setPageContext(_jspx_page_context);
    _jspx_th_aui_input_12.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_2);
    _jspx_th_aui_input_12.setLabel("server-name");
    _jspx_th_aui_input_12.setName("inServerName");
    int _jspx_eval_aui_input_12 = _jspx_th_aui_input_12.doStartTag();
    if (_jspx_th_aui_input_12.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_input_name_label_nobody.reuse(_jspx_th_aui_input_12);
      return true;
    }
    _jspx_tagPool_aui_input_name_label_nobody.reuse(_jspx_th_aui_input_12);
    return false;
  }

  private boolean _jspx_meth_aui_input_13(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_fieldset_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:input
    com.liferay.taglib.aui.InputTag _jspx_th_aui_input_13 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_name_label_nobody.get(com.liferay.taglib.aui.InputTag.class);
    _jspx_th_aui_input_13.setPageContext(_jspx_page_context);
    _jspx_th_aui_input_13.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_2);
    _jspx_th_aui_input_13.setLabel("server-port");
    _jspx_th_aui_input_13.setName("inServerPort");
    _jspx_th_aui_input_13.setValue(new String("110"));
    int _jspx_eval_aui_input_13 = _jspx_th_aui_input_13.doStartTag();
    if (_jspx_th_aui_input_13.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_input_value_name_label_nobody.reuse(_jspx_th_aui_input_13);
      return true;
    }
    _jspx_tagPool_aui_input_value_name_label_nobody.reuse(_jspx_th_aui_input_13);
    return false;
  }

  private boolean _jspx_meth_aui_input_14(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_fieldset_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:input
    com.liferay.taglib.aui.InputTag _jspx_th_aui_input_14 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_name_label_nobody.get(com.liferay.taglib.aui.InputTag.class);
    _jspx_th_aui_input_14.setPageContext(_jspx_page_context);
    _jspx_th_aui_input_14.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_2);
    _jspx_th_aui_input_14.setLabel("use-a-secure-network-connection");
    _jspx_th_aui_input_14.setName("inUseSSL");
    int _jspx_eval_aui_input_14 = _jspx_th_aui_input_14.doStartTag();
    if (_jspx_th_aui_input_14.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_input_name_label_nobody.reuse(_jspx_th_aui_input_14);
      return true;
    }
    _jspx_tagPool_aui_input_name_label_nobody.reuse(_jspx_th_aui_input_14);
    return false;
  }

  private boolean _jspx_meth_aui_input_15(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_fieldset_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:input
    com.liferay.taglib.aui.InputTag _jspx_th_aui_input_15 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_name_label_nobody.get(com.liferay.taglib.aui.InputTag.class);
    _jspx_th_aui_input_15.setPageContext(_jspx_page_context);
    _jspx_th_aui_input_15.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_2);
    _jspx_th_aui_input_15.setLabel("user-name");
    _jspx_th_aui_input_15.setName("inUserName");
    int _jspx_eval_aui_input_15 = _jspx_th_aui_input_15.doStartTag();
    if (_jspx_th_aui_input_15.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_input_name_label_nobody.reuse(_jspx_th_aui_input_15);
      return true;
    }
    _jspx_tagPool_aui_input_name_label_nobody.reuse(_jspx_th_aui_input_15);
    return false;
  }

  private boolean _jspx_meth_aui_input_16(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_fieldset_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:input
    com.liferay.taglib.aui.InputTag _jspx_th_aui_input_16 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_name_label_nobody.get(com.liferay.taglib.aui.InputTag.class);
    _jspx_th_aui_input_16.setPageContext(_jspx_page_context);
    _jspx_th_aui_input_16.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_2);
    _jspx_th_aui_input_16.setLabel("password");
    _jspx_th_aui_input_16.setName("inPassword");
    int _jspx_eval_aui_input_16 = _jspx_th_aui_input_16.doStartTag();
    if (_jspx_th_aui_input_16.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_input_name_label_nobody.reuse(_jspx_th_aui_input_16);
      return true;
    }
    _jspx_tagPool_aui_input_name_label_nobody.reuse(_jspx_th_aui_input_16);
    return false;
  }

  private boolean _jspx_meth_aui_input_17(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_fieldset_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:input
    com.liferay.taglib.aui.InputTag _jspx_th_aui_input_17 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_name_label_nobody.get(com.liferay.taglib.aui.InputTag.class);
    _jspx_th_aui_input_17.setPageContext(_jspx_page_context);
    _jspx_th_aui_input_17.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_2);
    _jspx_th_aui_input_17.setLabel("read-interval-minutes");
    _jspx_th_aui_input_17.setName("inReadInterval");
    _jspx_th_aui_input_17.setValue(new String("5"));
    int _jspx_eval_aui_input_17 = _jspx_th_aui_input_17.doStartTag();
    if (_jspx_th_aui_input_17.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_input_value_name_label_nobody.reuse(_jspx_th_aui_input_17);
      return true;
    }
    _jspx_tagPool_aui_input_value_name_label_nobody.reuse(_jspx_th_aui_input_17);
    return false;
  }

  private boolean _jspx_meth_aui_fieldset_3(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_fieldset_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:fieldset
    com.liferay.taglib.aui.FieldsetTag _jspx_th_aui_fieldset_3 = (com.liferay.taglib.aui.FieldsetTag) _jspx_tagPool_aui_fieldset_label.get(com.liferay.taglib.aui.FieldsetTag.class);
    _jspx_th_aui_fieldset_3.setPageContext(_jspx_page_context);
    _jspx_th_aui_fieldset_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_1);
    _jspx_th_aui_fieldset_3.setLabel("outgoing");
    int _jspx_eval_aui_fieldset_3 = _jspx_th_aui_fieldset_3.doStartTag();
    if (_jspx_eval_aui_fieldset_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      out.write("\n\t\t\t\t\t\t");
      if (_jspx_meth_aui_input_18((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_fieldset_3, _jspx_page_context))
        return true;
      out.write("\n\n\t\t\t\t\t\t");
      if (_jspx_meth_aui_input_19((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_fieldset_3, _jspx_page_context))
        return true;
      out.write("\n\n\t\t\t\t\t\t<div id=\"");
      if (_jspx_meth_portlet_namespace_1((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_fieldset_3, _jspx_page_context))
        return true;
      out.write("outCustomSettings\">\n\t\t\t\t\t\t\t");
      if (_jspx_meth_aui_input_20((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_fieldset_3, _jspx_page_context))
        return true;
      out.write("\n\n\t\t\t\t\t\t\t");
      if (_jspx_meth_aui_input_21((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_fieldset_3, _jspx_page_context))
        return true;
      out.write("\n\n\t\t\t\t\t\t\t");
      if (_jspx_meth_aui_input_22((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_fieldset_3, _jspx_page_context))
        return true;
      out.write("\n\n\t\t\t\t\t\t\t");
      if (_jspx_meth_aui_input_23((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_fieldset_3, _jspx_page_context))
        return true;
      out.write("\n\n\t\t\t\t\t\t\t");
      if (_jspx_meth_aui_input_24((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_fieldset_3, _jspx_page_context))
        return true;
      out.write("\n\t\t\t\t\t\t</div>\n\t\t\t\t\t");
    }
    if (_jspx_th_aui_fieldset_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_fieldset_label.reuse(_jspx_th_aui_fieldset_3);
      return true;
    }
    _jspx_tagPool_aui_fieldset_label.reuse(_jspx_th_aui_fieldset_3);
    return false;
  }

  private boolean _jspx_meth_aui_input_18(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_fieldset_3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:input
    com.liferay.taglib.aui.InputTag _jspx_th_aui_input_18 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_name_label_nobody.get(com.liferay.taglib.aui.InputTag.class);
    _jspx_th_aui_input_18.setPageContext(_jspx_page_context);
    _jspx_th_aui_input_18.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_3);
    _jspx_th_aui_input_18.setLabel("email-address");
    _jspx_th_aui_input_18.setName("outEmailAddress");
    int _jspx_eval_aui_input_18 = _jspx_th_aui_input_18.doStartTag();
    if (_jspx_th_aui_input_18.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_input_name_label_nobody.reuse(_jspx_th_aui_input_18);
      return true;
    }
    _jspx_tagPool_aui_input_name_label_nobody.reuse(_jspx_th_aui_input_18);
    return false;
  }

  private boolean _jspx_meth_aui_input_19(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_fieldset_3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:input
    com.liferay.taglib.aui.InputTag _jspx_th_aui_input_19 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_name_label_nobody.get(com.liferay.taglib.aui.InputTag.class);
    _jspx_th_aui_input_19.setPageContext(_jspx_page_context);
    _jspx_th_aui_input_19.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_3);
    _jspx_th_aui_input_19.setLabel("use-custom-outgoing-server");
    _jspx_th_aui_input_19.setName("outCustom");
    int _jspx_eval_aui_input_19 = _jspx_th_aui_input_19.doStartTag();
    if (_jspx_th_aui_input_19.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_input_name_label_nobody.reuse(_jspx_th_aui_input_19);
      return true;
    }
    _jspx_tagPool_aui_input_name_label_nobody.reuse(_jspx_th_aui_input_19);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_1(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_fieldset_3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_1 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_1.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_3);
    int _jspx_eval_portlet_namespace_1 = _jspx_th_portlet_namespace_1.doStartTag();
    if (_jspx_th_portlet_namespace_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_1);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_1);
    return false;
  }

  private boolean _jspx_meth_aui_input_20(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_fieldset_3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:input
    com.liferay.taglib.aui.InputTag _jspx_th_aui_input_20 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_name_label_nobody.get(com.liferay.taglib.aui.InputTag.class);
    _jspx_th_aui_input_20.setPageContext(_jspx_page_context);
    _jspx_th_aui_input_20.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_3);
    _jspx_th_aui_input_20.setLabel("server-name");
    _jspx_th_aui_input_20.setName("outServerName");
    int _jspx_eval_aui_input_20 = _jspx_th_aui_input_20.doStartTag();
    if (_jspx_th_aui_input_20.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_input_name_label_nobody.reuse(_jspx_th_aui_input_20);
      return true;
    }
    _jspx_tagPool_aui_input_name_label_nobody.reuse(_jspx_th_aui_input_20);
    return false;
  }

  private boolean _jspx_meth_aui_input_21(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_fieldset_3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:input
    com.liferay.taglib.aui.InputTag _jspx_th_aui_input_21 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_name_label_nobody.get(com.liferay.taglib.aui.InputTag.class);
    _jspx_th_aui_input_21.setPageContext(_jspx_page_context);
    _jspx_th_aui_input_21.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_3);
    _jspx_th_aui_input_21.setLabel("server-port");
    _jspx_th_aui_input_21.setName("outServerPort");
    _jspx_th_aui_input_21.setValue(new String("25"));
    int _jspx_eval_aui_input_21 = _jspx_th_aui_input_21.doStartTag();
    if (_jspx_th_aui_input_21.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_input_value_name_label_nobody.reuse(_jspx_th_aui_input_21);
      return true;
    }
    _jspx_tagPool_aui_input_value_name_label_nobody.reuse(_jspx_th_aui_input_21);
    return false;
  }

  private boolean _jspx_meth_aui_input_22(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_fieldset_3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:input
    com.liferay.taglib.aui.InputTag _jspx_th_aui_input_22 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_name_label_nobody.get(com.liferay.taglib.aui.InputTag.class);
    _jspx_th_aui_input_22.setPageContext(_jspx_page_context);
    _jspx_th_aui_input_22.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_3);
    _jspx_th_aui_input_22.setLabel("use-a-secure-network-connection");
    _jspx_th_aui_input_22.setName("outUseSSL");
    int _jspx_eval_aui_input_22 = _jspx_th_aui_input_22.doStartTag();
    if (_jspx_th_aui_input_22.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_input_name_label_nobody.reuse(_jspx_th_aui_input_22);
      return true;
    }
    _jspx_tagPool_aui_input_name_label_nobody.reuse(_jspx_th_aui_input_22);
    return false;
  }

  private boolean _jspx_meth_aui_input_23(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_fieldset_3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:input
    com.liferay.taglib.aui.InputTag _jspx_th_aui_input_23 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_name_label_nobody.get(com.liferay.taglib.aui.InputTag.class);
    _jspx_th_aui_input_23.setPageContext(_jspx_page_context);
    _jspx_th_aui_input_23.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_3);
    _jspx_th_aui_input_23.setLabel("user-name");
    _jspx_th_aui_input_23.setName("outUserName");
    int _jspx_eval_aui_input_23 = _jspx_th_aui_input_23.doStartTag();
    if (_jspx_th_aui_input_23.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_input_name_label_nobody.reuse(_jspx_th_aui_input_23);
      return true;
    }
    _jspx_tagPool_aui_input_name_label_nobody.reuse(_jspx_th_aui_input_23);
    return false;
  }

  private boolean _jspx_meth_aui_input_24(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_fieldset_3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:input
    com.liferay.taglib.aui.InputTag _jspx_th_aui_input_24 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_name_label_nobody.get(com.liferay.taglib.aui.InputTag.class);
    _jspx_th_aui_input_24.setPageContext(_jspx_page_context);
    _jspx_th_aui_input_24.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_3);
    _jspx_th_aui_input_24.setLabel("password");
    _jspx_th_aui_input_24.setName("outPassword");
    int _jspx_eval_aui_input_24 = _jspx_th_aui_input_24.doStartTag();
    if (_jspx_th_aui_input_24.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_input_name_label_nobody.reuse(_jspx_th_aui_input_24);
      return true;
    }
    _jspx_tagPool_aui_input_name_label_nobody.reuse(_jspx_th_aui_input_24);
    return false;
  }

  private boolean _jspx_meth_aui_button_0(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_button$1row_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:button
    com.liferay.taglib.aui.ButtonTag _jspx_th_aui_button_0 = (com.liferay.taglib.aui.ButtonTag) _jspx_tagPool_aui_button_type_nobody.get(com.liferay.taglib.aui.ButtonTag.class);
    _jspx_th_aui_button_0.setPageContext(_jspx_page_context);
    _jspx_th_aui_button_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_button$1row_0);
    _jspx_th_aui_button_0.setType("submit");
    int _jspx_eval_aui_button_0 = _jspx_th_aui_button_0.doStartTag();
    if (_jspx_th_aui_button_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_button_type_nobody.reuse(_jspx_th_aui_button_0);
      return true;
    }
    _jspx_tagPool_aui_button_type_nobody.reuse(_jspx_th_aui_button_0);
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
}
