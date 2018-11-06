package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.liferay.background.task.kernel.util.comparator.BackgroundTaskCreateDateComparator;
import com.liferay.document.library.kernel.service.DLAppHelperLocalServiceUtil;
import com.liferay.exportimport.kernel.background.task.BackgroundTaskExecutorNames;
import com.liferay.exportimport.kernel.exception.RemoteExportException;
import com.liferay.exportimport.kernel.lar.ExportImportHelperUtil;
import com.liferay.exportimport.kernel.lar.PortletDataHandler;
import com.liferay.exportimport.kernel.staging.StagingConstants;
import com.liferay.exportimport.kernel.staging.StagingUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.backgroundtask.BackgroundTask;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskConstants;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskManagerUtil;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.LocaleException;
import com.liferay.portal.kernel.exception.RemoteOptionsException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.language.UnicodeLanguageUtil;
import com.liferay.portal.kernel.model.LayoutSet;
import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.security.auth.AuthException;
import com.liferay.portal.kernel.security.auth.RemoteAuthException;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.LayoutSetLocalServiceUtil;
import com.liferay.portal.kernel.service.permission.GroupPermissionUtil;
import com.liferay.portal.kernel.service.permission.PortalPermissionUtil;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PortletKeys;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.util.comparator.PortletTitleComparator;
import com.liferay.portal.util.PropsValues;
import com.liferay.portlet.layoutsadmin.display.context.GroupDisplayContextHelper;
import com.liferay.trash.service.TrashEntryLocalServiceUtil;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class view_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(8);
    _jspx_dependants.add("/init.jsp");
    _jspx_dependants.add("/staging_configuration_exceptions.jspf");
    _jspx_dependants.add("/staging_configuration_select_staging_type.jspf");
    _jspx_dependants.add("/staging_configuration_remote_options.jspf");
    _jspx_dependants.add("/error_auth_exception.jspf");
    _jspx_dependants.add("/error_remote_export_exception.jspf");
    _jspx_dependants.add("/error_remote_options_exception.jspf");
    _jspx_dependants.add("/staging_configuration_staged_portlets.jspf");
  }

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_actionURL_var_name;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1util_param_value_name_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_form_onSubmit_name_method_action;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_message_key_arguments_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_param_value_name_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_error_exception;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_if_test;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_input_value_type_size_name_label_cssClass_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_script;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1staging_radio_value_name_label_id_checked_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_script_sandbox;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1util_include_servletContext_page;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_input_value_type_size_name_label_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_error$1marker_value_key_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1staging_radio_value_name_label_id_description_checked_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_choose;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1frontend_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_success_message_key_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_when_test;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_namespace_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1staging_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_message_key_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1staging_checkbox_name_label_id_disabled_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1staging_popover_title_text_id_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1staging_alert_type_dismissible;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_input_value_type_name_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1portlet_renderURL_windowState_var_portletName;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_renderURL_var;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_icon$1delete_url_message_label_confirmation_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_otherwise;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1staging_checkbox_name_label_disabled_checked_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1theme_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_renderURL;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1staging_checkbox_name_label_checked_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1staging_alert_type;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_portlet_actionURL_var_name = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1util_param_value_name_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_form_onSubmit_name_method_action = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_message_key_arguments_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_param_value_name_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_error_exception = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_if_test = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_input_value_type_size_name_label_cssClass_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_script = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1staging_radio_value_name_label_id_checked_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_script_sandbox = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1util_include_servletContext_page = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_input_value_type_size_name_label_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_error$1marker_value_key_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1staging_radio_value_name_label_id_description_checked_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_choose = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1frontend_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_success_message_key_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_when_test = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_namespace_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1staging_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_message_key_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1staging_checkbox_name_label_id_disabled_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1staging_popover_title_text_id_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1staging_alert_type_dismissible = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_input_value_type_name_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1portlet_renderURL_windowState_var_portletName = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_renderURL_var = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_icon$1delete_url_message_label_confirmation_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_otherwise = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1staging_checkbox_name_label_disabled_checked_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1theme_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_renderURL = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1staging_checkbox_name_label_checked_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1staging_alert_type = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_portlet_actionURL_var_name.release();
    _jspx_tagPool_liferay$1util_param_value_name_nobody.release();
    _jspx_tagPool_aui_form_onSubmit_name_method_action.release();
    _jspx_tagPool_liferay$1ui_message_key_arguments_nobody.release();
    _jspx_tagPool_portlet_param_value_name_nobody.release();
    _jspx_tagPool_liferay$1ui_error_exception.release();
    _jspx_tagPool_c_if_test.release();
    _jspx_tagPool_aui_input_value_type_size_name_label_cssClass_nobody.release();
    _jspx_tagPool_aui_script.release();
    _jspx_tagPool_liferay$1staging_radio_value_name_label_id_checked_nobody.release();
    _jspx_tagPool_aui_script_sandbox.release();
    _jspx_tagPool_liferay$1util_include_servletContext_page.release();
    _jspx_tagPool_aui_input_value_type_size_name_label_nobody.release();
    _jspx_tagPool_liferay$1ui_error$1marker_value_key_nobody.release();
    _jspx_tagPool_liferay$1staging_radio_value_name_label_id_description_checked_nobody.release();
    _jspx_tagPool_c_choose.release();
    _jspx_tagPool_liferay$1frontend_defineObjects_nobody.release();
    _jspx_tagPool_liferay$1ui_success_message_key_nobody.release();
    _jspx_tagPool_c_when_test.release();
    _jspx_tagPool_portlet_namespace_nobody.release();
    _jspx_tagPool_liferay$1staging_defineObjects_nobody.release();
    _jspx_tagPool_liferay$1ui_message_key_nobody.release();
    _jspx_tagPool_liferay$1staging_checkbox_name_label_id_disabled_nobody.release();
    _jspx_tagPool_liferay$1staging_popover_title_text_id_nobody.release();
    _jspx_tagPool_liferay$1staging_alert_type_dismissible.release();
    _jspx_tagPool_aui_input_value_type_name_nobody.release();
    _jspx_tagPool_liferay$1portlet_renderURL_windowState_var_portletName.release();
    _jspx_tagPool_portlet_renderURL_var.release();
    _jspx_tagPool_liferay$1ui_icon$1delete_url_message_label_confirmation_nobody.release();
    _jspx_tagPool_c_otherwise.release();
    _jspx_tagPool_portlet_defineObjects_nobody.release();
    _jspx_tagPool_liferay$1staging_checkbox_name_label_disabled_checked_nobody.release();
    _jspx_tagPool_liferay$1theme_defineObjects_nobody.release();
    _jspx_tagPool_portlet_renderURL.release();
    _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody.release();
    _jspx_tagPool_liferay$1staging_checkbox_name_label_checked_nobody.release();
    _jspx_tagPool_liferay$1staging_alert_type.release();
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
      out.write("\n\n\n\n\n\n\n\n\n\n\n\n");
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
      //  liferay-staging:defineObjects
      com.liferay.staging.taglib.servlet.taglib.DefineObjectsTag _jspx_th_liferay$1staging_defineObjects_0 = (com.liferay.staging.taglib.servlet.taglib.DefineObjectsTag) _jspx_tagPool_liferay$1staging_defineObjects_nobody.get(com.liferay.staging.taglib.servlet.taglib.DefineObjectsTag.class);
      _jspx_th_liferay$1staging_defineObjects_0.setPageContext(_jspx_page_context);
      _jspx_th_liferay$1staging_defineObjects_0.setParent(null);
      int _jspx_eval_liferay$1staging_defineObjects_0 = _jspx_th_liferay$1staging_defineObjects_0.doStartTag();
      if (_jspx_th_liferay$1staging_defineObjects_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_liferay$1staging_defineObjects_nobody.reuse(_jspx_th_liferay$1staging_defineObjects_0);
        return;
      }
      _jspx_tagPool_liferay$1staging_defineObjects_nobody.reuse(_jspx_th_liferay$1staging_defineObjects_0);
      com.liferay.portal.kernel.model.Group group = null;
      java.lang.Long groupId = null;
      com.liferay.portal.kernel.model.Group liveGroup = null;
      java.lang.Long liveGroupId = null;
      java.lang.Boolean privateLayout = null;
      com.liferay.portal.kernel.model.Group scopeGroup = null;
      java.lang.Long scopeGroupId = null;
      com.liferay.portal.kernel.model.Group stagingGroup = null;
      java.lang.Long stagingGroupId = null;
      group = (com.liferay.portal.kernel.model.Group) _jspx_page_context.findAttribute("group");
      groupId = (java.lang.Long) _jspx_page_context.findAttribute("groupId");
      liveGroup = (com.liferay.portal.kernel.model.Group) _jspx_page_context.findAttribute("liveGroup");
      liveGroupId = (java.lang.Long) _jspx_page_context.findAttribute("liveGroupId");
      privateLayout = (java.lang.Boolean) _jspx_page_context.findAttribute("privateLayout");
      scopeGroup = (com.liferay.portal.kernel.model.Group) _jspx_page_context.findAttribute("scopeGroup");
      scopeGroupId = (java.lang.Long) _jspx_page_context.findAttribute("scopeGroupId");
      stagingGroup = (com.liferay.portal.kernel.model.Group) _jspx_page_context.findAttribute("stagingGroup");
      stagingGroupId = (java.lang.Long) _jspx_page_context.findAttribute("stagingGroupId");
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

GroupDisplayContextHelper groupDisplayContextHelper = new GroupDisplayContextHelper(request);

liveGroup = groupDisplayContextHelper.getLiveGroup();
liveGroupId = groupDisplayContextHelper.getLiveGroupId();
UnicodeProperties liveGroupTypeSettings = liveGroup.getTypeSettingsProperties();

LayoutSet privateLayoutSet = LayoutSetLocalServiceUtil.getLayoutSet(liveGroup.getGroupId(), true);
LayoutSet publicLayoutSet = LayoutSetLocalServiceUtil.getLayoutSet(liveGroup.getGroupId(), false);

boolean liveGroupRemoteStaging = liveGroup.hasRemoteStagingGroup() && PropsValues.STAGING_LIVE_GROUP_REMOTE_STAGING_ENABLED;
boolean stagedLocally = liveGroup.isStaged() && !liveGroup.isStagedRemotely();
boolean stagedRemotely = liveGroup.isStaged() && !stagedLocally;

if (stagedLocally) {
	stagingGroup = liveGroup.getStagingGroup();
	stagingGroupId = stagingGroup.getGroupId();
}

BackgroundTask lastCompletedInitialPublicationBackgroundTask = BackgroundTaskManagerUtil.fetchFirstBackgroundTask(liveGroupId, BackgroundTaskExecutorNames.LAYOUT_STAGING_BACKGROUND_TASK_EXECUTOR, true, new BackgroundTaskCreateDateComparator(false));

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
        _jspx_th_c_when_0.setTest( GroupPermissionUtil.contains(permissionChecker, liveGroup, ActionKeys.MANAGE_STAGING) && GroupPermissionUtil.contains(permissionChecker, liveGroup, ActionKeys.VIEW_STAGING) );
        int _jspx_eval_c_when_0 = _jspx_th_c_when_0.doStartTag();
        if (_jspx_eval_c_when_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          out.write("\n\t\t");
          out.write('\n');
          out.write('\n');
          //  c:if
          com.liferay.taglib.core.IfTag _jspx_th_c_if_0 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
          _jspx_th_c_if_0.setPageContext(_jspx_page_context);
          _jspx_th_c_if_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_0);
          _jspx_th_c_if_0.setTest( liveGroupRemoteStaging );
          int _jspx_eval_c_if_0 = _jspx_th_c_if_0.doStartTag();
          if (_jspx_eval_c_if_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write('\n');
            out.write('	');
            //  liferay-staging:alert
            com.liferay.staging.taglib.servlet.taglib.AlertTag _jspx_th_liferay$1staging_alert_0 = (com.liferay.staging.taglib.servlet.taglib.AlertTag) _jspx_tagPool_liferay$1staging_alert_type_dismissible.get(com.liferay.staging.taglib.servlet.taglib.AlertTag.class);
            _jspx_th_liferay$1staging_alert_0.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1staging_alert_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_0);
            _jspx_th_liferay$1staging_alert_0.setDismissible( true );
            _jspx_th_liferay$1staging_alert_0.setType(Enum.valueOf(com.liferay.staging.taglib.servlet.taglib.AlertType.class, "WARNING"));
            int _jspx_eval_liferay$1staging_alert_0 = _jspx_th_liferay$1staging_alert_0.doStartTag();
            if (_jspx_eval_liferay$1staging_alert_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              if (_jspx_eval_liferay$1staging_alert_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                out = _jspx_page_context.pushBody();
                _jspx_th_liferay$1staging_alert_0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                _jspx_th_liferay$1staging_alert_0.doInitBody();
              }
              do {
                out.write("\n\t\t");
                //  liferay-ui:message
                com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_0 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
                _jspx_th_liferay$1ui_message_0.setPageContext(_jspx_page_context);
                _jspx_th_liferay$1ui_message_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1staging_alert_0);
                _jspx_th_liferay$1ui_message_0.setKey( LanguageUtil.get(request, "live-group-remote-staging-alert") );
                int _jspx_eval_liferay$1ui_message_0 = _jspx_th_liferay$1ui_message_0.doStartTag();
                if (_jspx_th_liferay$1ui_message_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_0);
                  return;
                }
                _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_0);
                out.write("\n\t\t");
                //  liferay-ui:message
                com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_1 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_arguments_nobody.get(com.liferay.taglib.ui.MessageTag.class);
                _jspx_th_liferay$1ui_message_1.setPageContext(_jspx_page_context);
                _jspx_th_liferay$1ui_message_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1staging_alert_0);
                _jspx_th_liferay$1ui_message_1.setArguments( "javascript:" + renderResponse.getNamespace() + "saveGroup(true);" );
                _jspx_th_liferay$1ui_message_1.setKey("you-can-also-forcibly-disable-remote-staging");
                int _jspx_eval_liferay$1ui_message_1 = _jspx_th_liferay$1ui_message_1.doStartTag();
                if (_jspx_th_liferay$1ui_message_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_liferay$1ui_message_key_arguments_nobody.reuse(_jspx_th_liferay$1ui_message_1);
                  return;
                }
                _jspx_tagPool_liferay$1ui_message_key_arguments_nobody.reuse(_jspx_th_liferay$1ui_message_1);
                out.write('\n');
                out.write('	');
                int evalDoAfterBody = _jspx_th_liferay$1staging_alert_0.doAfterBody();
                if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                  break;
              } while (true);
              if (_jspx_eval_liferay$1staging_alert_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                out = _jspx_page_context.popBody();
            }
            if (_jspx_th_liferay$1staging_alert_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_liferay$1staging_alert_type_dismissible.reuse(_jspx_th_liferay$1staging_alert_0);
              return;
            }
            _jspx_tagPool_liferay$1staging_alert_type_dismissible.reuse(_jspx_th_liferay$1staging_alert_0);
            out.write('\n');
          }
          if (_jspx_th_c_if_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
            return;
          }
          _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
          out.write('\n');
          out.write('\n');
          //  c:if
          com.liferay.taglib.core.IfTag _jspx_th_c_if_1 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
          _jspx_th_c_if_1.setPageContext(_jspx_page_context);
          _jspx_th_c_if_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_0);
          _jspx_th_c_if_1.setTest( (lastCompletedInitialPublicationBackgroundTask != null) && (lastCompletedInitialPublicationBackgroundTask.getStatus() == BackgroundTaskConstants.STATUS_FAILED) );
          int _jspx_eval_c_if_1 = _jspx_th_c_if_1.doStartTag();
          if (_jspx_eval_c_if_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write('\n');
            out.write('	');
            //  liferay-staging:alert
            com.liferay.staging.taglib.servlet.taglib.AlertTag _jspx_th_liferay$1staging_alert_1 = (com.liferay.staging.taglib.servlet.taglib.AlertTag) _jspx_tagPool_liferay$1staging_alert_type_dismissible.get(com.liferay.staging.taglib.servlet.taglib.AlertTag.class);
            _jspx_th_liferay$1staging_alert_1.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1staging_alert_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_1);
            _jspx_th_liferay$1staging_alert_1.setDismissible( true );
            _jspx_th_liferay$1staging_alert_1.setType(Enum.valueOf(com.liferay.staging.taglib.servlet.taglib.AlertType.class, "WARNING"));
            int _jspx_eval_liferay$1staging_alert_1 = _jspx_th_liferay$1staging_alert_1.doStartTag();
            if (_jspx_eval_liferay$1staging_alert_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              if (_jspx_eval_liferay$1staging_alert_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                out = _jspx_page_context.pushBody();
                _jspx_th_liferay$1staging_alert_1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                _jspx_th_liferay$1staging_alert_1.doInitBody();
              }
              do {
                out.write("\n\t\t");
                if (_jspx_meth_liferay$1ui_message_2((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1staging_alert_1, _jspx_page_context))
                  return;
                out.write("\n\n\t\t");
                //  portlet:actionURL
                com.liferay.taglib.portlet.ActionURLTag _jspx_th_portlet_actionURL_0 = (com.liferay.taglib.portlet.ActionURLTag) _jspx_tagPool_portlet_actionURL_var_name.get(com.liferay.taglib.portlet.ActionURLTag.class);
                _jspx_th_portlet_actionURL_0.setPageContext(_jspx_page_context);
                _jspx_th_portlet_actionURL_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1staging_alert_1);
                _jspx_th_portlet_actionURL_0.setName("deleteBackgroundTask");
                _jspx_th_portlet_actionURL_0.setVar("deleteBackgroundTaskURL");
                int _jspx_eval_portlet_actionURL_0 = _jspx_th_portlet_actionURL_0.doStartTag();
                if (_jspx_eval_portlet_actionURL_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  out.write("\n\t\t\t");
                  //  portlet:param
                  com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_0 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                  _jspx_th_portlet_param_0.setPageContext(_jspx_page_context);
                  _jspx_th_portlet_param_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_actionURL_0);
                  _jspx_th_portlet_param_0.setName("redirect");
                  _jspx_th_portlet_param_0.setValue( currentURL );
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
                  _jspx_th_portlet_param_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_actionURL_0);
                  _jspx_th_portlet_param_1.setName("backgroundTaskId");
                  _jspx_th_portlet_param_1.setValue( String.valueOf(lastCompletedInitialPublicationBackgroundTask.getBackgroundTaskId()) );
                  int _jspx_eval_portlet_param_1 = _jspx_th_portlet_param_1.doStartTag();
                  if (_jspx_th_portlet_param_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_1);
                    return;
                  }
                  _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_1);
                  out.write("\n\t\t");
                }
                if (_jspx_th_portlet_actionURL_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_portlet_actionURL_var_name.reuse(_jspx_th_portlet_actionURL_0);
                  return;
                }
                _jspx_tagPool_portlet_actionURL_var_name.reuse(_jspx_th_portlet_actionURL_0);
                java.lang.String deleteBackgroundTaskURL = null;
                deleteBackgroundTaskURL = (java.lang.String) _jspx_page_context.findAttribute("deleteBackgroundTaskURL");
                out.write("\n\n\t\t");
                //  liferay-ui:icon-delete
                com.liferay.taglib.ui.IconDeleteTag _jspx_th_liferay$1ui_icon$1delete_0 = (com.liferay.taglib.ui.IconDeleteTag) _jspx_tagPool_liferay$1ui_icon$1delete_url_message_label_confirmation_nobody.get(com.liferay.taglib.ui.IconDeleteTag.class);
                _jspx_th_liferay$1ui_icon$1delete_0.setPageContext(_jspx_page_context);
                _jspx_th_liferay$1ui_icon$1delete_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1staging_alert_1);
                _jspx_th_liferay$1ui_icon$1delete_0.setConfirmation("are-you-sure-you-want-to-remove-the-initial-staging-publication");
                _jspx_th_liferay$1ui_icon$1delete_0.setLabel( true );
                _jspx_th_liferay$1ui_icon$1delete_0.setMessage("clear");
                _jspx_th_liferay$1ui_icon$1delete_0.setUrl( deleteBackgroundTaskURL );
                int _jspx_eval_liferay$1ui_icon$1delete_0 = _jspx_th_liferay$1ui_icon$1delete_0.doStartTag();
                if (_jspx_th_liferay$1ui_icon$1delete_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_liferay$1ui_icon$1delete_url_message_label_confirmation_nobody.reuse(_jspx_th_liferay$1ui_icon$1delete_0);
                  return;
                }
                _jspx_tagPool_liferay$1ui_icon$1delete_url_message_label_confirmation_nobody.reuse(_jspx_th_liferay$1ui_icon$1delete_0);
                out.write('\n');
                out.write('	');
                int evalDoAfterBody = _jspx_th_liferay$1staging_alert_1.doAfterBody();
                if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                  break;
              } while (true);
              if (_jspx_eval_liferay$1staging_alert_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                out = _jspx_page_context.popBody();
            }
            if (_jspx_th_liferay$1staging_alert_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_liferay$1staging_alert_type_dismissible.reuse(_jspx_th_liferay$1staging_alert_1);
              return;
            }
            _jspx_tagPool_liferay$1staging_alert_type_dismissible.reuse(_jspx_th_liferay$1staging_alert_1);
            out.write("\n\n\t");
            //  liferay-util:include
            com.liferay.taglib.util.IncludeTag _jspx_th_liferay$1util_include_0 = (com.liferay.taglib.util.IncludeTag) _jspx_tagPool_liferay$1util_include_servletContext_page.get(com.liferay.taglib.util.IncludeTag.class);
            _jspx_th_liferay$1util_include_0.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1util_include_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_1);
            _jspx_th_liferay$1util_include_0.setPage("/publish_process_message_task_details.jsp");
            _jspx_th_liferay$1util_include_0.setServletContext( application );
            int _jspx_eval_liferay$1util_include_0 = _jspx_th_liferay$1util_include_0.doStartTag();
            if (_jspx_eval_liferay$1util_include_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\t\t");
              //  liferay-util:param
              com.liferay.taglib.util.ParamTag _jspx_th_liferay$1util_param_0 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_liferay$1util_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
              _jspx_th_liferay$1util_param_0.setPageContext(_jspx_page_context);
              _jspx_th_liferay$1util_param_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1util_include_0);
              _jspx_th_liferay$1util_param_0.setName("backgroundTaskId");
              _jspx_th_liferay$1util_param_0.setValue( String.valueOf(lastCompletedInitialPublicationBackgroundTask.getBackgroundTaskId()) );
              int _jspx_eval_liferay$1util_param_0 = _jspx_th_liferay$1util_param_0.doStartTag();
              if (_jspx_th_liferay$1util_param_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_liferay$1util_param_value_name_nobody.reuse(_jspx_th_liferay$1util_param_0);
                return;
              }
              _jspx_tagPool_liferay$1util_param_value_name_nobody.reuse(_jspx_th_liferay$1util_param_0);
              out.write('\n');
              out.write('	');
            }
            if (_jspx_th_liferay$1util_include_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_liferay$1util_include_servletContext_page.reuse(_jspx_th_liferay$1util_include_0);
              return;
            }
            _jspx_tagPool_liferay$1util_include_servletContext_page.reuse(_jspx_th_liferay$1util_include_0);
            out.write('\n');
          }
          if (_jspx_th_c_if_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_1);
            return;
          }
          _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_1);
          out.write('\n');
          out.write('\n');
          //  c:if
          com.liferay.taglib.core.IfTag _jspx_th_c_if_2 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
          _jspx_th_c_if_2.setPageContext(_jspx_page_context);
          _jspx_th_c_if_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_0);
          _jspx_th_c_if_2.setTest( stagedLocally && (BackgroundTaskManagerUtil.getBackgroundTasksCount(liveGroupId, BackgroundTaskExecutorNames.LAYOUT_STAGING_BACKGROUND_TASK_EXECUTOR, false) > 0) );
          int _jspx_eval_c_if_2 = _jspx_th_c_if_2.doStartTag();
          if (_jspx_eval_c_if_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write('\n');
            out.write('	');
            if (_jspx_meth_liferay$1staging_alert_2((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_2, _jspx_page_context))
              return;
            out.write("\n\n\t");
            //  aui:script
            com.liferay.taglib.aui.ScriptTag _jspx_th_aui_script_0 = (com.liferay.taglib.aui.ScriptTag) _jspx_tagPool_aui_script.get(com.liferay.taglib.aui.ScriptTag.class);
            _jspx_th_aui_script_0.setPageContext(_jspx_page_context);
            _jspx_th_aui_script_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_2);
            int _jspx_eval_aui_script_0 = _jspx_th_aui_script_0.doStartTag();
            if (_jspx_eval_aui_script_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              if (_jspx_eval_aui_script_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                out = _jspx_page_context.pushBody();
                _jspx_th_aui_script_0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                _jspx_th_aui_script_0.doInitBody();
              }
              do {
                out.write("\n\t\t");
                //  liferay-portlet:renderURL
                com.liferay.taglib.portlet.RenderURLTag _jspx_th_liferay$1portlet_renderURL_0 = (com.liferay.taglib.portlet.RenderURLTag) _jspx_tagPool_liferay$1portlet_renderURL_windowState_var_portletName.get(com.liferay.taglib.portlet.RenderURLTag.class);
                _jspx_th_liferay$1portlet_renderURL_0.setPageContext(_jspx_page_context);
                _jspx_th_liferay$1portlet_renderURL_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_0);
                _jspx_th_liferay$1portlet_renderURL_0.setPortletName( PortletKeys.EXPORT_IMPORT );
                _jspx_th_liferay$1portlet_renderURL_0.setVar("publishProcessesURL");
                _jspx_th_liferay$1portlet_renderURL_0.setWindowState( LiferayWindowState.POP_UP.toString() );
                int _jspx_eval_liferay$1portlet_renderURL_0 = _jspx_th_liferay$1portlet_renderURL_0.doStartTag();
                if (_jspx_eval_liferay$1portlet_renderURL_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  out.write("\n\t\t\t");
                  if (_jspx_meth_portlet_param_2((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1portlet_renderURL_0, _jspx_page_context))
                    return;
                  out.write("\n\t\t\t");
                  //  portlet:param
                  com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_3 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                  _jspx_th_portlet_param_3.setPageContext(_jspx_page_context);
                  _jspx_th_portlet_param_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_0);
                  _jspx_th_portlet_param_3.setName( Constants.CMD );
                  _jspx_th_portlet_param_3.setValue("view_processes");
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
                  _jspx_th_portlet_param_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_0);
                  _jspx_th_portlet_param_4.setName( SearchContainer.DEFAULT_CUR_PARAM );
                  _jspx_th_portlet_param_4.setValue( ParamUtil.getString(request, SearchContainer.DEFAULT_CUR_PARAM) );
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
                  _jspx_th_portlet_param_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_0);
                  _jspx_th_portlet_param_5.setName( SearchContainer.DEFAULT_DELTA_PARAM );
                  _jspx_th_portlet_param_5.setValue( ParamUtil.getString(request, SearchContainer.DEFAULT_DELTA_PARAM) );
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
                  _jspx_th_portlet_param_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_0);
                  _jspx_th_portlet_param_6.setName("groupId");
                  _jspx_th_portlet_param_6.setValue( String.valueOf(stagingGroupId) );
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
                  _jspx_th_portlet_param_7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_0);
                  _jspx_th_portlet_param_7.setName("liveGroupId");
                  _jspx_th_portlet_param_7.setValue( String.valueOf(liveGroupId) );
                  int _jspx_eval_portlet_param_7 = _jspx_th_portlet_param_7.doStartTag();
                  if (_jspx_th_portlet_param_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_7);
                    return;
                  }
                  _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_7);
                  out.write("\n\t\t\t");
                  //  portlet:param
                  com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_8 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                  _jspx_th_portlet_param_8.setPageContext(_jspx_page_context);
                  _jspx_th_portlet_param_8.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_0);
                  _jspx_th_portlet_param_8.setName("localPublishing");
                  _jspx_th_portlet_param_8.setValue( String.valueOf(stagedLocally) );
                  int _jspx_eval_portlet_param_8 = _jspx_th_portlet_param_8.doStartTag();
                  if (_jspx_th_portlet_param_8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_8);
                    return;
                  }
                  _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_8);
                  out.write("\n\t\t");
                }
                if (_jspx_th_liferay$1portlet_renderURL_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_liferay$1portlet_renderURL_windowState_var_portletName.reuse(_jspx_th_liferay$1portlet_renderURL_0);
                  return;
                }
                _jspx_tagPool_liferay$1portlet_renderURL_windowState_var_portletName.reuse(_jspx_th_liferay$1portlet_renderURL_0);
                java.lang.String publishProcessesURL = null;
                publishProcessesURL = (java.lang.String) _jspx_page_context.findAttribute("publishProcessesURL");
                out.write("\n\n\t\tAUI.$('#");
                if (_jspx_meth_portlet_namespace_1((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_0, _jspx_page_context))
                  return;
                out.write("publishProcessesLink').on(\n\t\t\t'click',\n\t\t\tfunction(event) {\n\t\t\t\tLiferay.Util.openWindow(\n\t\t\t\t\t{\n\t\t\t\t\t\tid: 'publishProcesses',\n\t\t\t\t\t\ttitle: '");
                if (_jspx_meth_liferay$1ui_message_5((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_0, _jspx_page_context))
                  return;
                out.write("',\n\t\t\t\t\t\turi: '");
                out.print( HtmlUtil.escapeJS(publishProcessesURL.toString()) );
                out.write("'\n\t\t\t\t\t}\n\t\t\t\t);\n\t\t\t}\n\t\t);\n\t");
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
            out.write('\n');
          }
          if (_jspx_th_c_if_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_2);
            return;
          }
          _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_2);
          out.write('\n');
          out.write('\n');
          //  liferay-ui:error-marker
          com.liferay.taglib.ui.ErrorMarkerTag _jspx_th_liferay$1ui_error$1marker_0 = (com.liferay.taglib.ui.ErrorMarkerTag) _jspx_tagPool_liferay$1ui_error$1marker_value_key_nobody.get(com.liferay.taglib.ui.ErrorMarkerTag.class);
          _jspx_th_liferay$1ui_error$1marker_0.setPageContext(_jspx_page_context);
          _jspx_th_liferay$1ui_error$1marker_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_0);
          _jspx_th_liferay$1ui_error$1marker_0.setKey( WebKeys.ERROR_SECTION );
          _jspx_th_liferay$1ui_error$1marker_0.setValue("staging");
          int _jspx_eval_liferay$1ui_error$1marker_0 = _jspx_th_liferay$1ui_error$1marker_0.doStartTag();
          if (_jspx_th_liferay$1ui_error$1marker_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_liferay$1ui_error$1marker_value_key_nobody.reuse(_jspx_th_liferay$1ui_error$1marker_0);
            return;
          }
          _jspx_tagPool_liferay$1ui_error$1marker_value_key_nobody.reuse(_jspx_th_liferay$1ui_error$1marker_0);
          out.write('\n');
          out.write('\n');
          //  c:if
          com.liferay.taglib.core.IfTag _jspx_th_c_if_3 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
          _jspx_th_c_if_3.setPageContext(_jspx_page_context);
          _jspx_th_c_if_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_0);
          _jspx_th_c_if_3.setTest( privateLayoutSet.isLayoutSetPrototypeLinkActive() || publicLayoutSet.isLayoutSetPrototypeLinkActive() );
          int _jspx_eval_c_if_3 = _jspx_th_c_if_3.doStartTag();
          if (_jspx_eval_c_if_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write('\n');
            out.write('	');
            //  liferay-staging:alert
            com.liferay.staging.taglib.servlet.taglib.AlertTag _jspx_th_liferay$1staging_alert_3 = (com.liferay.staging.taglib.servlet.taglib.AlertTag) _jspx_tagPool_liferay$1staging_alert_type.get(com.liferay.staging.taglib.servlet.taglib.AlertTag.class);
            _jspx_th_liferay$1staging_alert_3.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1staging_alert_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_3);
            _jspx_th_liferay$1staging_alert_3.setType(Enum.valueOf(com.liferay.staging.taglib.servlet.taglib.AlertType.class, "WARNING"));
            int _jspx_eval_liferay$1staging_alert_3 = _jspx_th_liferay$1staging_alert_3.doStartTag();
            if (_jspx_eval_liferay$1staging_alert_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              if (_jspx_eval_liferay$1staging_alert_3 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                out = _jspx_page_context.pushBody();
                _jspx_th_liferay$1staging_alert_3.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                _jspx_th_liferay$1staging_alert_3.doInitBody();
              }
              do {
                out.write("\n\t\t");
                if (_jspx_meth_liferay$1ui_message_6((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1staging_alert_3, _jspx_page_context))
                  return;
                out.write("\n\n\t\t");
                //  c:choose
                com.liferay.taglib.core.ChooseTag _jspx_th_c_choose_1 = (com.liferay.taglib.core.ChooseTag) _jspx_tagPool_c_choose.get(com.liferay.taglib.core.ChooseTag.class);
                _jspx_th_c_choose_1.setPageContext(_jspx_page_context);
                _jspx_th_c_choose_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1staging_alert_3);
                int _jspx_eval_c_choose_1 = _jspx_th_c_choose_1.doStartTag();
                if (_jspx_eval_c_choose_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  out.write("\n\t\t\t");
                  //  c:when
                  com.liferay.taglib.core.WhenTag _jspx_th_c_when_1 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
                  _jspx_th_c_when_1.setPageContext(_jspx_page_context);
                  _jspx_th_c_when_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_1);
                  _jspx_th_c_when_1.setTest( PortalPermissionUtil.contains(permissionChecker, ActionKeys.UNLINK_LAYOUT_SET_PROTOTYPE) );
                  int _jspx_eval_c_when_1 = _jspx_th_c_when_1.doStartTag();
                  if (_jspx_eval_c_when_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    out.write("\n\t\t\t\t");
                    if (_jspx_meth_liferay$1ui_message_7((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_when_1, _jspx_page_context))
                      return;
                    out.write("\n\t\t\t");
                  }
                  if (_jspx_th_c_when_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_1);
                    return;
                  }
                  _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_1);
                  out.write("\n\t\t\t");
                  if (_jspx_meth_c_otherwise_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_choose_1, _jspx_page_context))
                    return;
                  out.write("\n\t\t");
                }
                if (_jspx_th_c_choose_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_1);
                  return;
                }
                _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_1);
                out.write('\n');
                out.write('	');
                int evalDoAfterBody = _jspx_th_liferay$1staging_alert_3.doAfterBody();
                if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                  break;
              } while (true);
              if (_jspx_eval_liferay$1staging_alert_3 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                out = _jspx_page_context.popBody();
            }
            if (_jspx_th_liferay$1staging_alert_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_liferay$1staging_alert_type.reuse(_jspx_th_liferay$1staging_alert_3);
              return;
            }
            _jspx_tagPool_liferay$1staging_alert_type.reuse(_jspx_th_liferay$1staging_alert_3);
            out.write('\n');
          }
          if (_jspx_th_c_if_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_3);
            return;
          }
          _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_3);
          out.write('\n');
          out.write('\n');
          //  liferay-ui:error
          com.liferay.taglib.ui.ErrorTag _jspx_th_liferay$1ui_error_0 = (com.liferay.taglib.ui.ErrorTag) _jspx_tagPool_liferay$1ui_error_exception.get(com.liferay.taglib.ui.ErrorTag.class);
          _jspx_th_liferay$1ui_error_0.setPageContext(_jspx_page_context);
          _jspx_th_liferay$1ui_error_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_0);
          _jspx_th_liferay$1ui_error_0.setException( Exception.class );
          int _jspx_eval_liferay$1ui_error_0 = _jspx_th_liferay$1ui_error_0.doStartTag();
          if (_jspx_eval_liferay$1ui_error_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            java.lang.Object errorException = null;
            if (_jspx_eval_liferay$1ui_error_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
              out = _jspx_page_context.pushBody();
              _jspx_th_liferay$1ui_error_0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
              _jspx_th_liferay$1ui_error_0.doInitBody();
            }
            errorException = (java.lang.Object) _jspx_page_context.findAttribute("errorException");
            do {
              out.write('\n');
              out.write('	');
              if (_jspx_meth_liferay$1ui_message_9((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1ui_error_0, _jspx_page_context))
                return;
              out.write("\n\n\t");
              out.print( errorException.toString() );
              out.write('\n');
              int evalDoAfterBody = _jspx_th_liferay$1ui_error_0.doAfterBody();
              errorException = (java.lang.Object) _jspx_page_context.findAttribute("errorException");
              if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                break;
            } while (true);
            if (_jspx_eval_liferay$1ui_error_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
              out = _jspx_page_context.popBody();
          }
          if (_jspx_th_liferay$1ui_error_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_liferay$1ui_error_exception.reuse(_jspx_th_liferay$1ui_error_0);
            return;
          }
          _jspx_tagPool_liferay$1ui_error_exception.reuse(_jspx_th_liferay$1ui_error_0);
          out.write('\n');
          out.write('\n');
          //  liferay-ui:error
          com.liferay.taglib.ui.ErrorTag _jspx_th_liferay$1ui_error_1 = (com.liferay.taglib.ui.ErrorTag) _jspx_tagPool_liferay$1ui_error_exception.get(com.liferay.taglib.ui.ErrorTag.class);
          _jspx_th_liferay$1ui_error_1.setPageContext(_jspx_page_context);
          _jspx_th_liferay$1ui_error_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_0);
          _jspx_th_liferay$1ui_error_1.setException( LocaleException.class );
          int _jspx_eval_liferay$1ui_error_1 = _jspx_th_liferay$1ui_error_1.doStartTag();
          if (_jspx_eval_liferay$1ui_error_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            java.lang.Object errorException = null;
            if (_jspx_eval_liferay$1ui_error_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
              out = _jspx_page_context.pushBody();
              _jspx_th_liferay$1ui_error_1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
              _jspx_th_liferay$1ui_error_1.doInitBody();
            }
            errorException = (java.lang.Object) _jspx_page_context.findAttribute("errorException");
            do {
              out.write("\n\n\t");

	LocaleException le = (LocaleException)errorException;
	
              out.write("\n\n\t");
              //  c:if
              com.liferay.taglib.core.IfTag _jspx_th_c_if_4 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
              _jspx_th_c_if_4.setPageContext(_jspx_page_context);
              _jspx_th_c_if_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_error_1);
              _jspx_th_c_if_4.setTest( le.getType() == LocaleException.TYPE_EXPORT_IMPORT );
              int _jspx_eval_c_if_4 = _jspx_th_c_if_4.doStartTag();
              if (_jspx_eval_c_if_4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\t\t");
                //  liferay-ui:message
                com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_10 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody.get(com.liferay.taglib.ui.MessageTag.class);
                _jspx_th_liferay$1ui_message_10.setPageContext(_jspx_page_context);
                _jspx_th_liferay$1ui_message_10.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_4);
                _jspx_th_liferay$1ui_message_10.setArguments( new String[] {StringUtil.merge(le.getSourceAvailableLocales(), StringPool.COMMA_AND_SPACE), StringUtil.merge(le.getTargetAvailableLocales(), StringPool.COMMA_AND_SPACE)} );
                _jspx_th_liferay$1ui_message_10.setKey("the-default-language-x-does-not-match-the-portal's-available-languages-x");
                _jspx_th_liferay$1ui_message_10.setTranslateArguments( false );
                int _jspx_eval_liferay$1ui_message_10 = _jspx_th_liferay$1ui_message_10.doStartTag();
                if (_jspx_th_liferay$1ui_message_10.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody.reuse(_jspx_th_liferay$1ui_message_10);
                  return;
                }
                _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody.reuse(_jspx_th_liferay$1ui_message_10);
                out.write('\n');
                out.write('	');
              }
              if (_jspx_th_c_if_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_4);
                return;
              }
              _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_4);
              out.write('\n');
              int evalDoAfterBody = _jspx_th_liferay$1ui_error_1.doAfterBody();
              errorException = (java.lang.Object) _jspx_page_context.findAttribute("errorException");
              if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                break;
            } while (true);
            if (_jspx_eval_liferay$1ui_error_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
              out = _jspx_page_context.popBody();
          }
          if (_jspx_th_liferay$1ui_error_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_liferay$1ui_error_exception.reuse(_jspx_th_liferay$1ui_error_1);
            return;
          }
          _jspx_tagPool_liferay$1ui_error_exception.reuse(_jspx_th_liferay$1ui_error_1);
          out.write('\n');
          out.write('\n');
          //  liferay-ui:error
          com.liferay.taglib.ui.ErrorTag _jspx_th_liferay$1ui_error_2 = (com.liferay.taglib.ui.ErrorTag) _jspx_tagPool_liferay$1ui_error_exception.get(com.liferay.taglib.ui.ErrorTag.class);
          _jspx_th_liferay$1ui_error_2.setPageContext(_jspx_page_context);
          _jspx_th_liferay$1ui_error_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_0);
          _jspx_th_liferay$1ui_error_2.setException( SystemException.class );
          int _jspx_eval_liferay$1ui_error_2 = _jspx_th_liferay$1ui_error_2.doStartTag();
          if (_jspx_eval_liferay$1ui_error_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            java.lang.Object errorException = null;
            if (_jspx_eval_liferay$1ui_error_2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
              out = _jspx_page_context.pushBody();
              _jspx_th_liferay$1ui_error_2.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
              _jspx_th_liferay$1ui_error_2.doInitBody();
            }
            errorException = (java.lang.Object) _jspx_page_context.findAttribute("errorException");
            do {
              out.write("\n\n\t");

	SystemException se = (SystemException)errorException;
	
              out.write("\n\n\t");
              //  liferay-ui:message
              com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_11 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
              _jspx_th_liferay$1ui_message_11.setPageContext(_jspx_page_context);
              _jspx_th_liferay$1ui_message_11.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_error_2);
              _jspx_th_liferay$1ui_message_11.setKey( se.getMessage() );
              int _jspx_eval_liferay$1ui_message_11 = _jspx_th_liferay$1ui_message_11.doStartTag();
              if (_jspx_th_liferay$1ui_message_11.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_11);
                return;
              }
              _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_11);
              out.write('\n');
              int evalDoAfterBody = _jspx_th_liferay$1ui_error_2.doAfterBody();
              errorException = (java.lang.Object) _jspx_page_context.findAttribute("errorException");
              if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                break;
            } while (true);
            if (_jspx_eval_liferay$1ui_error_2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
              out = _jspx_page_context.popBody();
          }
          if (_jspx_th_liferay$1ui_error_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_liferay$1ui_error_exception.reuse(_jspx_th_liferay$1ui_error_2);
            return;
          }
          _jspx_tagPool_liferay$1ui_error_exception.reuse(_jspx_th_liferay$1ui_error_2);
          out.write("\n\n\t\t<div class=\"custom-sheet sheet sheet-lg\">\n\t\t\t");
          if (_jspx_meth_liferay$1ui_success_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_when_0, _jspx_page_context))
            return;
          out.write("\n\n\t\t\t");
          if (_jspx_meth_liferay$1ui_success_1((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_when_0, _jspx_page_context))
            return;
          out.write("\n\n\t\t\t");
          if (_jspx_meth_liferay$1ui_success_2((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_when_0, _jspx_page_context))
            return;
          out.write("\n\n\t\t\t");
          //  portlet:actionURL
          com.liferay.taglib.portlet.ActionURLTag _jspx_th_portlet_actionURL_1 = (com.liferay.taglib.portlet.ActionURLTag) _jspx_tagPool_portlet_actionURL_var_name.get(com.liferay.taglib.portlet.ActionURLTag.class);
          _jspx_th_portlet_actionURL_1.setPageContext(_jspx_page_context);
          _jspx_th_portlet_actionURL_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_0);
          _jspx_th_portlet_actionURL_1.setName("editStagingConfiguration");
          _jspx_th_portlet_actionURL_1.setVar("editStagingConfigurationURL");
          int _jspx_eval_portlet_actionURL_1 = _jspx_th_portlet_actionURL_1.doStartTag();
          if (_jspx_eval_portlet_actionURL_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t\t\t");
            if (_jspx_meth_portlet_param_9((javax.servlet.jsp.tagext.JspTag) _jspx_th_portlet_actionURL_1, _jspx_page_context))
              return;
            out.write("\n\t\t\t");
          }
          if (_jspx_th_portlet_actionURL_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_portlet_actionURL_var_name.reuse(_jspx_th_portlet_actionURL_1);
            return;
          }
          _jspx_tagPool_portlet_actionURL_var_name.reuse(_jspx_th_portlet_actionURL_1);
          java.lang.String editStagingConfigurationURL = null;
          editStagingConfigurationURL = (java.lang.String) _jspx_page_context.findAttribute("editStagingConfigurationURL");
          out.write("\n\n\t\t\t");
          //  portlet:renderURL
          com.liferay.taglib.portlet.RenderURLTag _jspx_th_portlet_renderURL_0 = (com.liferay.taglib.portlet.RenderURLTag) _jspx_tagPool_portlet_renderURL_var.get(com.liferay.taglib.portlet.RenderURLTag.class);
          _jspx_th_portlet_renderURL_0.setPageContext(_jspx_page_context);
          _jspx_th_portlet_renderURL_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_0);
          _jspx_th_portlet_renderURL_0.setVar("redirectURL");
          int _jspx_eval_portlet_renderURL_0 = _jspx_th_portlet_renderURL_0.doStartTag();
          if (_jspx_eval_portlet_renderURL_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t\t\t");
            if (_jspx_meth_portlet_param_10((javax.servlet.jsp.tagext.JspTag) _jspx_th_portlet_renderURL_0, _jspx_page_context))
              return;
            out.write("\n\t\t\t");
          }
          if (_jspx_th_portlet_renderURL_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_portlet_renderURL_var.reuse(_jspx_th_portlet_renderURL_0);
            return;
          }
          _jspx_tagPool_portlet_renderURL_var.reuse(_jspx_th_portlet_renderURL_0);
          java.lang.String redirectURL = null;
          redirectURL = (java.lang.String) _jspx_page_context.findAttribute("redirectURL");
          out.write("\n\n\t\t\t");
          //  aui:form
          com.liferay.taglib.aui.FormTag _jspx_th_aui_form_0 = (com.liferay.taglib.aui.FormTag) _jspx_tagPool_aui_form_onSubmit_name_method_action.get(com.liferay.taglib.aui.FormTag.class);
          _jspx_th_aui_form_0.setPageContext(_jspx_page_context);
          _jspx_th_aui_form_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_0);
          _jspx_th_aui_form_0.setAction( editStagingConfigurationURL );
          _jspx_th_aui_form_0.setMethod("post");
          _jspx_th_aui_form_0.setName("fm");
          _jspx_th_aui_form_0.setOnSubmit( "event.preventDefault(); " + renderResponse.getNamespace() + "saveGroup();" );
          int _jspx_eval_aui_form_0 = _jspx_th_aui_form_0.doStartTag();
          if (_jspx_eval_aui_form_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t\t\t");
            //  aui:input
            com.liferay.taglib.aui.InputTag _jspx_th_aui_input_0 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
            _jspx_th_aui_input_0.setPageContext(_jspx_page_context);
            _jspx_th_aui_input_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
            _jspx_th_aui_input_0.setName("redirect");
            _jspx_th_aui_input_0.setType("hidden");
            _jspx_th_aui_input_0.setValue( redirectURL );
            int _jspx_eval_aui_input_0 = _jspx_th_aui_input_0.doStartTag();
            if (_jspx_th_aui_input_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_0);
              return;
            }
            _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_0);
            out.write("\n\t\t\t\t");
            //  aui:input
            com.liferay.taglib.aui.InputTag _jspx_th_aui_input_1 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
            _jspx_th_aui_input_1.setPageContext(_jspx_page_context);
            _jspx_th_aui_input_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
            _jspx_th_aui_input_1.setName("groupId");
            _jspx_th_aui_input_1.setType("hidden");
            _jspx_th_aui_input_1.setValue( liveGroupId );
            int _jspx_eval_aui_input_1 = _jspx_th_aui_input_1.doStartTag();
            if (_jspx_th_aui_input_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_1);
              return;
            }
            _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_1);
            out.write("\n\t\t\t\t");
            //  aui:input
            com.liferay.taglib.aui.InputTag _jspx_th_aui_input_2 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
            _jspx_th_aui_input_2.setPageContext(_jspx_page_context);
            _jspx_th_aui_input_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
            _jspx_th_aui_input_2.setName("liveGroupId");
            _jspx_th_aui_input_2.setType("hidden");
            _jspx_th_aui_input_2.setValue( liveGroupId );
            int _jspx_eval_aui_input_2 = _jspx_th_aui_input_2.doStartTag();
            if (_jspx_th_aui_input_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_2);
              return;
            }
            _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_2);
            out.write("\n\t\t\t\t");
            //  aui:input
            com.liferay.taglib.aui.InputTag _jspx_th_aui_input_3 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
            _jspx_th_aui_input_3.setPageContext(_jspx_page_context);
            _jspx_th_aui_input_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
            _jspx_th_aui_input_3.setName("stagingGroupId");
            _jspx_th_aui_input_3.setType("hidden");
            _jspx_th_aui_input_3.setValue( stagingGroupId );
            int _jspx_eval_aui_input_3 = _jspx_th_aui_input_3.doStartTag();
            if (_jspx_th_aui_input_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_3);
              return;
            }
            _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_3);
            out.write("\n\t\t\t\t");
            //  aui:input
            com.liferay.taglib.aui.InputTag _jspx_th_aui_input_4 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
            _jspx_th_aui_input_4.setPageContext(_jspx_page_context);
            _jspx_th_aui_input_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
            _jspx_th_aui_input_4.setName("forceDisable");
            _jspx_th_aui_input_4.setType("hidden");
            _jspx_th_aui_input_4.setValue( false );
            int _jspx_eval_aui_input_4 = _jspx_th_aui_input_4.doStartTag();
            if (_jspx_th_aui_input_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_4);
              return;
            }
            _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_4);
            out.write("\n\n\t\t\t\t");
            //  c:if
            com.liferay.taglib.core.IfTag _jspx_th_c_if_5 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
            _jspx_th_c_if_5.setPageContext(_jspx_page_context);
            _jspx_th_c_if_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
            _jspx_th_c_if_5.setTest( !privateLayoutSet.isLayoutSetPrototypeLinkActive() && !publicLayoutSet.isLayoutSetPrototypeLinkActive() );
            int _jspx_eval_c_if_5 = _jspx_th_c_if_5.doStartTag();
            if (_jspx_eval_c_if_5 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\t\t\t\t\t<div class=\"sheet-header\">\n\t\t\t\t\t\t<div class=\"sheet-title\">\n\t\t\t\t\t\t\t");
              if (_jspx_meth_liferay$1ui_message_12((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_5, _jspx_page_context))
                return;
              out.write("\n\t\t\t\t\t\t</div>\n\t\t\t\t\t</div>\n\n\t\t\t\t\t");
              out.write("\n\n<div class=\"sheet-section\">\n\t<div class=\"sheet-text\">\n\t\t");
              if (_jspx_meth_liferay$1ui_message_13((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_5, _jspx_page_context))
                return;
              out.write("\n\t</div>\n\n\t<div class=\"form-group\" id=\"");
              if (_jspx_meth_portlet_namespace_2((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_5, _jspx_page_context))
                return;
              out.write("stagingTypes\">\n\t\t<div class=\"custom-distance\">\n\t\t\t");
              //  liferay-staging:radio
              com.liferay.staging.taglib.servlet.taglib.RadioTag _jspx_th_liferay$1staging_radio_0 = (com.liferay.staging.taglib.servlet.taglib.RadioTag) _jspx_tagPool_liferay$1staging_radio_value_name_label_id_checked_nobody.get(com.liferay.staging.taglib.servlet.taglib.RadioTag.class);
              _jspx_th_liferay$1staging_radio_0.setPageContext(_jspx_page_context);
              _jspx_th_liferay$1staging_radio_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_5);
              _jspx_th_liferay$1staging_radio_0.setChecked( !liveGroup.isStaged() );
              _jspx_th_liferay$1staging_radio_0.setId("none");
              _jspx_th_liferay$1staging_radio_0.setLabel("none");
              _jspx_th_liferay$1staging_radio_0.setName("stagingType");
              _jspx_th_liferay$1staging_radio_0.setValue( String.valueOf(StagingConstants.TYPE_NOT_STAGED) );
              int _jspx_eval_liferay$1staging_radio_0 = _jspx_th_liferay$1staging_radio_0.doStartTag();
              if (_jspx_th_liferay$1staging_radio_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_liferay$1staging_radio_value_name_label_id_checked_nobody.reuse(_jspx_th_liferay$1staging_radio_0);
                return;
              }
              _jspx_tagPool_liferay$1staging_radio_value_name_label_id_checked_nobody.reuse(_jspx_th_liferay$1staging_radio_0);
              out.write("\n\t\t</div>\n\n\t\t");
              //  c:if
              com.liferay.taglib.core.IfTag _jspx_th_c_if_6 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
              _jspx_th_c_if_6.setPageContext(_jspx_page_context);
              _jspx_th_c_if_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_5);
              _jspx_th_c_if_6.setTest( !liveGroupRemoteStaging && !stagedRemotely );
              int _jspx_eval_c_if_6 = _jspx_th_c_if_6.doStartTag();
              if (_jspx_eval_c_if_6 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\t\t\t<div class=\"custom-distance\">\n\t\t\t\t");
                //  liferay-staging:radio
                com.liferay.staging.taglib.servlet.taglib.RadioTag _jspx_th_liferay$1staging_radio_1 = (com.liferay.staging.taglib.servlet.taglib.RadioTag) _jspx_tagPool_liferay$1staging_radio_value_name_label_id_description_checked_nobody.get(com.liferay.staging.taglib.servlet.taglib.RadioTag.class);
                _jspx_th_liferay$1staging_radio_1.setPageContext(_jspx_page_context);
                _jspx_th_liferay$1staging_radio_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_6);
                _jspx_th_liferay$1staging_radio_1.setChecked( stagedLocally );
                _jspx_th_liferay$1staging_radio_1.setDescription("staging-type-local");
                _jspx_th_liferay$1staging_radio_1.setId("local");
                _jspx_th_liferay$1staging_radio_1.setLabel("local-live");
                _jspx_th_liferay$1staging_radio_1.setName("stagingType");
                _jspx_th_liferay$1staging_radio_1.setValue( String.valueOf(StagingConstants.TYPE_LOCAL_STAGING) );
                int _jspx_eval_liferay$1staging_radio_1 = _jspx_th_liferay$1staging_radio_1.doStartTag();
                if (_jspx_th_liferay$1staging_radio_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_liferay$1staging_radio_value_name_label_id_description_checked_nobody.reuse(_jspx_th_liferay$1staging_radio_1);
                  return;
                }
                _jspx_tagPool_liferay$1staging_radio_value_name_label_id_description_checked_nobody.reuse(_jspx_th_liferay$1staging_radio_1);
                out.write("\n\t\t\t</div>\n\t\t");
              }
              if (_jspx_th_c_if_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_6);
                return;
              }
              _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_6);
              out.write("\n\n\t\t");
              //  c:if
              com.liferay.taglib.core.IfTag _jspx_th_c_if_7 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
              _jspx_th_c_if_7.setPageContext(_jspx_page_context);
              _jspx_th_c_if_7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_5);
              _jspx_th_c_if_7.setTest( !stagedLocally );
              int _jspx_eval_c_if_7 = _jspx_th_c_if_7.doStartTag();
              if (_jspx_eval_c_if_7 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\t\t\t<div class=\"custom-distance\">\n\t\t\t\t");
                //  liferay-staging:radio
                com.liferay.staging.taglib.servlet.taglib.RadioTag _jspx_th_liferay$1staging_radio_2 = (com.liferay.staging.taglib.servlet.taglib.RadioTag) _jspx_tagPool_liferay$1staging_radio_value_name_label_id_description_checked_nobody.get(com.liferay.staging.taglib.servlet.taglib.RadioTag.class);
                _jspx_th_liferay$1staging_radio_2.setPageContext(_jspx_page_context);
                _jspx_th_liferay$1staging_radio_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_7);
                _jspx_th_liferay$1staging_radio_2.setChecked( stagedRemotely );
                _jspx_th_liferay$1staging_radio_2.setDescription("staging-type-remote");
                _jspx_th_liferay$1staging_radio_2.setId("remote");
                _jspx_th_liferay$1staging_radio_2.setLabel("remote-live");
                _jspx_th_liferay$1staging_radio_2.setName("stagingType");
                _jspx_th_liferay$1staging_radio_2.setValue( String.valueOf(StagingConstants.TYPE_REMOTE_STAGING) );
                int _jspx_eval_liferay$1staging_radio_2 = _jspx_th_liferay$1staging_radio_2.doStartTag();
                if (_jspx_th_liferay$1staging_radio_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_liferay$1staging_radio_value_name_label_id_description_checked_nobody.reuse(_jspx_th_liferay$1staging_radio_2);
                  return;
                }
                _jspx_tagPool_liferay$1staging_radio_value_name_label_id_description_checked_nobody.reuse(_jspx_th_liferay$1staging_radio_2);
                out.write("\n\t\t\t</div>\n\t\t");
              }
              if (_jspx_th_c_if_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_7);
                return;
              }
              _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_7);
              out.write("\n\t</div>\n</div>");
              out.write("\n\n\t\t\t\t\t");
              out.write('\n');
              out.write('\n');

boolean showRemoteOptions = stagedRemotely;

int stagingType = ParamUtil.getInteger(request, "stagingType");

if (stagingType == StagingConstants.TYPE_REMOTE_STAGING) {
	showRemoteOptions = true;
}

              out.write("\n\n<div class=\"");
              out.print( showRemoteOptions ? StringPool.BLANK : "hide" );
              out.write("\" id=\"");
              if (_jspx_meth_portlet_namespace_3((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_5, _jspx_page_context))
                return;
              out.write("remoteStagingOptions\">\n\t");
              out.write('\n');
              out.write('\n');
              //  liferay-ui:error
              com.liferay.taglib.ui.ErrorTag _jspx_th_liferay$1ui_error_3 = (com.liferay.taglib.ui.ErrorTag) _jspx_tagPool_liferay$1ui_error_exception.get(com.liferay.taglib.ui.ErrorTag.class);
              _jspx_th_liferay$1ui_error_3.setPageContext(_jspx_page_context);
              _jspx_th_liferay$1ui_error_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_5);
              _jspx_th_liferay$1ui_error_3.setException( AuthException.class );
              int _jspx_eval_liferay$1ui_error_3 = _jspx_th_liferay$1ui_error_3.doStartTag();
              if (_jspx_eval_liferay$1ui_error_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                java.lang.Object errorException = null;
                if (_jspx_eval_liferay$1ui_error_3 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                  out = _jspx_page_context.pushBody();
                  _jspx_th_liferay$1ui_error_3.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                  _jspx_th_liferay$1ui_error_3.doInitBody();
                }
                errorException = (java.lang.Object) _jspx_page_context.findAttribute("errorException");
                do {
                  out.write("\n\n\t");

	AuthException ae = (AuthException)errorException;
	
                  out.write("\n\n\t");
                  //  c:if
                  com.liferay.taglib.core.IfTag _jspx_th_c_if_8 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                  _jspx_th_c_if_8.setPageContext(_jspx_page_context);
                  _jspx_th_c_if_8.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_error_3);
                  _jspx_th_c_if_8.setTest( ae instanceof RemoteAuthException );
                  int _jspx_eval_c_if_8 = _jspx_th_c_if_8.doStartTag();
                  if (_jspx_eval_c_if_8 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    out.write("\n\n\t\t");

		RemoteAuthException rae = (RemoteAuthException)errorException;
		
                    out.write("\n\n\t\t");
                    //  liferay-ui:message
                    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_14 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody.get(com.liferay.taglib.ui.MessageTag.class);
                    _jspx_th_liferay$1ui_message_14.setPageContext(_jspx_page_context);
                    _jspx_th_liferay$1ui_message_14.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_8);
                    _jspx_th_liferay$1ui_message_14.setArguments( "<em>" + rae.getURL() + "</em>" );
                    _jspx_th_liferay$1ui_message_14.setKey("an-unexpected-error-occurred-in-the-remote-server-at-x");
                    _jspx_th_liferay$1ui_message_14.setTranslateArguments( false );
                    int _jspx_eval_liferay$1ui_message_14 = _jspx_th_liferay$1ui_message_14.doStartTag();
                    if (_jspx_th_liferay$1ui_message_14.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody.reuse(_jspx_th_liferay$1ui_message_14);
                      return;
                    }
                    _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody.reuse(_jspx_th_liferay$1ui_message_14);
                    out.write('\n');
                    out.write('	');
                  }
                  if (_jspx_th_c_if_8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_8);
                    return;
                  }
                  _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_8);
                  out.write("\n\n\t");
                  //  c:if
                  com.liferay.taglib.core.IfTag _jspx_th_c_if_9 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                  _jspx_th_c_if_9.setPageContext(_jspx_page_context);
                  _jspx_th_c_if_9.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_error_3);
                  _jspx_th_c_if_9.setTest( ae.getType() == AuthException.INTERNAL_SERVER_ERROR );
                  int _jspx_eval_c_if_9 = _jspx_th_c_if_9.doStartTag();
                  if (_jspx_eval_c_if_9 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    out.write("\n\t\t");
                    if (_jspx_meth_liferay$1ui_message_15((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_9, _jspx_page_context))
                      return;
                    out.write('\n');
                    out.write('	');
                  }
                  if (_jspx_th_c_if_9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_9);
                    return;
                  }
                  _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_9);
                  out.write("\n\n\t");
                  //  c:if
                  com.liferay.taglib.core.IfTag _jspx_th_c_if_10 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                  _jspx_th_c_if_10.setPageContext(_jspx_page_context);
                  _jspx_th_c_if_10.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_error_3);
                  _jspx_th_c_if_10.setTest( ae.getType() == AuthException.INVALID_SHARED_SECRET );
                  int _jspx_eval_c_if_10 = _jspx_th_c_if_10.doStartTag();
                  if (_jspx_eval_c_if_10 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    out.write("\n\t\t");
                    if (_jspx_meth_liferay$1ui_message_16((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_10, _jspx_page_context))
                      return;
                    out.write('\n');
                    out.write('	');
                  }
                  if (_jspx_th_c_if_10.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_10);
                    return;
                  }
                  _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_10);
                  out.write("\n\n\t");
                  //  c:if
                  com.liferay.taglib.core.IfTag _jspx_th_c_if_11 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                  _jspx_th_c_if_11.setPageContext(_jspx_page_context);
                  _jspx_th_c_if_11.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_error_3);
                  _jspx_th_c_if_11.setTest( ae.getType() == AuthException.NO_SHARED_SECRET );
                  int _jspx_eval_c_if_11 = _jspx_th_c_if_11.doStartTag();
                  if (_jspx_eval_c_if_11 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    out.write("\n\t\t");
                    if (_jspx_meth_liferay$1ui_message_17((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_11, _jspx_page_context))
                      return;
                    out.write('\n');
                    out.write('	');
                  }
                  if (_jspx_th_c_if_11.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_11);
                    return;
                  }
                  _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_11);
                  out.write("\n\n\t");
                  //  c:if
                  com.liferay.taglib.core.IfTag _jspx_th_c_if_12 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                  _jspx_th_c_if_12.setPageContext(_jspx_page_context);
                  _jspx_th_c_if_12.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_error_3);
                  _jspx_th_c_if_12.setTest( ae.getType() == RemoteAuthException.WRONG_SHARED_SECRET );
                  int _jspx_eval_c_if_12 = _jspx_th_c_if_12.doStartTag();
                  if (_jspx_eval_c_if_12 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    out.write("\n\t\t");
                    if (_jspx_meth_liferay$1ui_message_18((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_12, _jspx_page_context))
                      return;
                    out.write('\n');
                    out.write('	');
                  }
                  if (_jspx_th_c_if_12.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_12);
                    return;
                  }
                  _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_12);
                  out.write('\n');
                  int evalDoAfterBody = _jspx_th_liferay$1ui_error_3.doAfterBody();
                  errorException = (java.lang.Object) _jspx_page_context.findAttribute("errorException");
                  if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                    break;
                } while (true);
                if (_jspx_eval_liferay$1ui_error_3 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                  out = _jspx_page_context.popBody();
              }
              if (_jspx_th_liferay$1ui_error_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_liferay$1ui_error_exception.reuse(_jspx_th_liferay$1ui_error_3);
                return;
              }
              _jspx_tagPool_liferay$1ui_error_exception.reuse(_jspx_th_liferay$1ui_error_3);
              out.write("\n\n\t");
              out.write('\n');
              out.write('\n');
              //  liferay-ui:error
              com.liferay.taglib.ui.ErrorTag _jspx_th_liferay$1ui_error_4 = (com.liferay.taglib.ui.ErrorTag) _jspx_tagPool_liferay$1ui_error_exception.get(com.liferay.taglib.ui.ErrorTag.class);
              _jspx_th_liferay$1ui_error_4.setPageContext(_jspx_page_context);
              _jspx_th_liferay$1ui_error_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_5);
              _jspx_th_liferay$1ui_error_4.setException( RemoteExportException.class );
              int _jspx_eval_liferay$1ui_error_4 = _jspx_th_liferay$1ui_error_4.doStartTag();
              if (_jspx_eval_liferay$1ui_error_4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                java.lang.Object errorException = null;
                if (_jspx_eval_liferay$1ui_error_4 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                  out = _jspx_page_context.pushBody();
                  _jspx_th_liferay$1ui_error_4.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                  _jspx_th_liferay$1ui_error_4.doInitBody();
                }
                errorException = (java.lang.Object) _jspx_page_context.findAttribute("errorException");
                do {
                  out.write("\n\n\t");

	RemoteExportException ree = (RemoteExportException)errorException;
	
                  out.write("\n\n\t");
                  //  c:if
                  com.liferay.taglib.core.IfTag _jspx_th_c_if_13 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                  _jspx_th_c_if_13.setPageContext(_jspx_page_context);
                  _jspx_th_c_if_13.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_error_4);
                  _jspx_th_c_if_13.setTest( ree.getType() == RemoteExportException.BAD_CONNECTION );
                  int _jspx_eval_c_if_13 = _jspx_th_c_if_13.doStartTag();
                  if (_jspx_eval_c_if_13 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    out.write("\n\t\t");
                    //  liferay-ui:message
                    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_19 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody.get(com.liferay.taglib.ui.MessageTag.class);
                    _jspx_th_liferay$1ui_message_19.setPageContext(_jspx_page_context);
                    _jspx_th_liferay$1ui_message_19.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_13);
                    _jspx_th_liferay$1ui_message_19.setArguments( "<em>" + ree.getURL() + "</em>" );
                    _jspx_th_liferay$1ui_message_19.setKey("could-not-connect-to-address-x.-please-verify-that-the-specified-port-is-correct-and-that-the-remote-server-is-configured-to-accept-requests-from-this-server");
                    _jspx_th_liferay$1ui_message_19.setTranslateArguments( false );
                    int _jspx_eval_liferay$1ui_message_19 = _jspx_th_liferay$1ui_message_19.doStartTag();
                    if (_jspx_th_liferay$1ui_message_19.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody.reuse(_jspx_th_liferay$1ui_message_19);
                      return;
                    }
                    _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody.reuse(_jspx_th_liferay$1ui_message_19);
                    out.write("\n\n\t\t");
                    //  c:if
                    com.liferay.taglib.core.IfTag _jspx_th_c_if_14 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                    _jspx_th_c_if_14.setPageContext(_jspx_page_context);
                    _jspx_th_c_if_14.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_13);
                    _jspx_th_c_if_14.setTest( liveGroup.isStaged() && liveGroup.isStagedRemotely() );
                    int _jspx_eval_c_if_14 = _jspx_th_c_if_14.doStartTag();
                    if (_jspx_eval_c_if_14 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      out.write("\n\t\t\t");
                      //  c:choose
                      com.liferay.taglib.core.ChooseTag _jspx_th_c_choose_2 = (com.liferay.taglib.core.ChooseTag) _jspx_tagPool_c_choose.get(com.liferay.taglib.core.ChooseTag.class);
                      _jspx_th_c_choose_2.setPageContext(_jspx_page_context);
                      _jspx_th_c_choose_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_14);
                      int _jspx_eval_c_choose_2 = _jspx_th_c_choose_2.doStartTag();
                      if (_jspx_eval_c_choose_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                        out.write("\n\t\t\t\t");
                        //  c:when
                        com.liferay.taglib.core.WhenTag _jspx_th_c_when_2 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
                        _jspx_th_c_when_2.setPageContext(_jspx_page_context);
                        _jspx_th_c_when_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_2);
                        _jspx_th_c_when_2.setTest( layout.isTypeControlPanel() );
                        int _jspx_eval_c_when_2 = _jspx_th_c_when_2.doStartTag();
                        if (_jspx_eval_c_when_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                          out.write("\n\t\t\t\t\t");
                          //  liferay-ui:message
                          com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_20 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_arguments_nobody.get(com.liferay.taglib.ui.MessageTag.class);
                          _jspx_th_liferay$1ui_message_20.setPageContext(_jspx_page_context);
                          _jspx_th_liferay$1ui_message_20.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_2);
                          _jspx_th_liferay$1ui_message_20.setArguments( "javascript:" + renderResponse.getNamespace() + "saveGroup(true);" );
                          _jspx_th_liferay$1ui_message_20.setKey("you-can-also-forcibly-disable-remote-staging");
                          int _jspx_eval_liferay$1ui_message_20 = _jspx_th_liferay$1ui_message_20.doStartTag();
                          if (_jspx_th_liferay$1ui_message_20.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                            _jspx_tagPool_liferay$1ui_message_key_arguments_nobody.reuse(_jspx_th_liferay$1ui_message_20);
                            return;
                          }
                          _jspx_tagPool_liferay$1ui_message_key_arguments_nobody.reuse(_jspx_th_liferay$1ui_message_20);
                          out.write("\n\t\t\t\t");
                        }
                        if (_jspx_th_c_when_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                          _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_2);
                          return;
                        }
                        _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_2);
                        out.write("\n\t\t\t\t");
                        if (_jspx_meth_c_otherwise_1((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_choose_2, _jspx_page_context))
                          return;
                        out.write("\n\t\t\t");
                      }
                      if (_jspx_th_c_choose_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_2);
                        return;
                      }
                      _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_2);
                      out.write("\n\t\t");
                    }
                    if (_jspx_th_c_if_14.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_14);
                      return;
                    }
                    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_14);
                    out.write('\n');
                    out.write('	');
                  }
                  if (_jspx_th_c_if_13.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_13);
                    return;
                  }
                  _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_13);
                  out.write("\n\n\t");
                  //  c:if
                  com.liferay.taglib.core.IfTag _jspx_th_c_if_15 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                  _jspx_th_c_if_15.setPageContext(_jspx_page_context);
                  _jspx_th_c_if_15.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_error_4);
                  _jspx_th_c_if_15.setTest( ree.getType() == RemoteExportException.SAME_GROUP );
                  int _jspx_eval_c_if_15 = _jspx_th_c_if_15.doStartTag();
                  if (_jspx_eval_c_if_15 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    out.write("\n\t\t");
                    //  liferay-ui:message
                    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_22 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody.get(com.liferay.taglib.ui.MessageTag.class);
                    _jspx_th_liferay$1ui_message_22.setPageContext(_jspx_page_context);
                    _jspx_th_liferay$1ui_message_22.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_15);
                    _jspx_th_liferay$1ui_message_22.setArguments( "<em>" + ree.getGroupId() + "</em>" );
                    _jspx_th_liferay$1ui_message_22.setKey("unable-to-activate-remote-staging-on-site-with-id-x.-the-remote-live-site-must-be-different-from-the-stage-site");
                    _jspx_th_liferay$1ui_message_22.setTranslateArguments( false );
                    int _jspx_eval_liferay$1ui_message_22 = _jspx_th_liferay$1ui_message_22.doStartTag();
                    if (_jspx_th_liferay$1ui_message_22.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody.reuse(_jspx_th_liferay$1ui_message_22);
                      return;
                    }
                    _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody.reuse(_jspx_th_liferay$1ui_message_22);
                    out.write('\n');
                    out.write('	');
                  }
                  if (_jspx_th_c_if_15.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_15);
                    return;
                  }
                  _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_15);
                  out.write("\n\n\t");
                  //  c:if
                  com.liferay.taglib.core.IfTag _jspx_th_c_if_16 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                  _jspx_th_c_if_16.setPageContext(_jspx_page_context);
                  _jspx_th_c_if_16.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_error_4);
                  _jspx_th_c_if_16.setTest( ree.getType() == RemoteExportException.INVALID_GROUP );
                  int _jspx_eval_c_if_16 = _jspx_th_c_if_16.doStartTag();
                  if (_jspx_eval_c_if_16 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    out.write("\n\t\t");
                    //  liferay-ui:message
                    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_23 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody.get(com.liferay.taglib.ui.MessageTag.class);
                    _jspx_th_liferay$1ui_message_23.setPageContext(_jspx_page_context);
                    _jspx_th_liferay$1ui_message_23.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_16);
                    _jspx_th_liferay$1ui_message_23.setArguments( "<em>" + ree.getGroupId() + "</em>" );
                    _jspx_th_liferay$1ui_message_23.setKey("unable-to-activate-remote-staging-on-site-with-id-x.-global-site-can-only-be-staged-to-another-global-site");
                    _jspx_th_liferay$1ui_message_23.setTranslateArguments( false );
                    int _jspx_eval_liferay$1ui_message_23 = _jspx_th_liferay$1ui_message_23.doStartTag();
                    if (_jspx_th_liferay$1ui_message_23.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody.reuse(_jspx_th_liferay$1ui_message_23);
                      return;
                    }
                    _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody.reuse(_jspx_th_liferay$1ui_message_23);
                    out.write('\n');
                    out.write('	');
                  }
                  if (_jspx_th_c_if_16.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_16);
                    return;
                  }
                  _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_16);
                  out.write("\n\n\t");
                  //  c:if
                  com.liferay.taglib.core.IfTag _jspx_th_c_if_17 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                  _jspx_th_c_if_17.setPageContext(_jspx_page_context);
                  _jspx_th_c_if_17.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_error_4);
                  _jspx_th_c_if_17.setTest( ree.getType() == RemoteExportException.NO_GROUP );
                  int _jspx_eval_c_if_17 = _jspx_th_c_if_17.doStartTag();
                  if (_jspx_eval_c_if_17 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    out.write("\n\t\t");
                    //  liferay-ui:message
                    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_24 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody.get(com.liferay.taglib.ui.MessageTag.class);
                    _jspx_th_liferay$1ui_message_24.setPageContext(_jspx_page_context);
                    _jspx_th_liferay$1ui_message_24.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_17);
                    _jspx_th_liferay$1ui_message_24.setArguments( "<em>" + ree.getGroupId() + "</em>" );
                    _jspx_th_liferay$1ui_message_24.setKey("no-site-exists-on-the-remote-server-with-site-id-x");
                    _jspx_th_liferay$1ui_message_24.setTranslateArguments( false );
                    int _jspx_eval_liferay$1ui_message_24 = _jspx_th_liferay$1ui_message_24.doStartTag();
                    if (_jspx_th_liferay$1ui_message_24.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody.reuse(_jspx_th_liferay$1ui_message_24);
                      return;
                    }
                    _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody.reuse(_jspx_th_liferay$1ui_message_24);
                    out.write('\n');
                    out.write('	');
                  }
                  if (_jspx_th_c_if_17.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_17);
                    return;
                  }
                  _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_17);
                  out.write("\n\n\t");
                  //  c:if
                  com.liferay.taglib.core.IfTag _jspx_th_c_if_18 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                  _jspx_th_c_if_18.setPageContext(_jspx_page_context);
                  _jspx_th_c_if_18.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_error_4);
                  _jspx_th_c_if_18.setTest( ree.getType() == RemoteExportException.NO_PERMISSIONS );
                  int _jspx_eval_c_if_18 = _jspx_th_c_if_18.doStartTag();
                  if (_jspx_eval_c_if_18 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    out.write("\n\t\t");
                    //  liferay-ui:message
                    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_25 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody.get(com.liferay.taglib.ui.MessageTag.class);
                    _jspx_th_liferay$1ui_message_25.setPageContext(_jspx_page_context);
                    _jspx_th_liferay$1ui_message_25.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_18);
                    _jspx_th_liferay$1ui_message_25.setArguments( "<em>" + ree.getGroupId() + "</em>" );
                    _jspx_th_liferay$1ui_message_25.setKey("you-do-not-have-permissions-to-edit-the-site-with-id-x-on-the-remote-server.-please-verify-that-you-have-the-proper-permissions-in-both-the-live-environment-and-the-staging-environment");
                    _jspx_th_liferay$1ui_message_25.setTranslateArguments( false );
                    int _jspx_eval_liferay$1ui_message_25 = _jspx_th_liferay$1ui_message_25.doStartTag();
                    if (_jspx_th_liferay$1ui_message_25.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody.reuse(_jspx_th_liferay$1ui_message_25);
                      return;
                    }
                    _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody.reuse(_jspx_th_liferay$1ui_message_25);
                    out.write('\n');
                    out.write('	');
                  }
                  if (_jspx_th_c_if_18.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_18);
                    return;
                  }
                  _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_18);
                  out.write('\n');
                  int evalDoAfterBody = _jspx_th_liferay$1ui_error_4.doAfterBody();
                  errorException = (java.lang.Object) _jspx_page_context.findAttribute("errorException");
                  if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                    break;
                } while (true);
                if (_jspx_eval_liferay$1ui_error_4 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                  out = _jspx_page_context.popBody();
              }
              if (_jspx_th_liferay$1ui_error_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_liferay$1ui_error_exception.reuse(_jspx_th_liferay$1ui_error_4);
                return;
              }
              _jspx_tagPool_liferay$1ui_error_exception.reuse(_jspx_th_liferay$1ui_error_4);
              out.write("\n\n\t<div cssClass=\"sheet-section\">\n\t\t<div class=\"sheet-subtitle\">\n\t\t\t");
              if (_jspx_meth_liferay$1ui_message_26((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_5, _jspx_page_context))
                return;
              out.write("\n\t\t</div>\n\n\t\t");
              out.write('\n');
              out.write('\n');
              //  liferay-ui:error
              com.liferay.taglib.ui.ErrorTag _jspx_th_liferay$1ui_error_5 = (com.liferay.taglib.ui.ErrorTag) _jspx_tagPool_liferay$1ui_error_exception.get(com.liferay.taglib.ui.ErrorTag.class);
              _jspx_th_liferay$1ui_error_5.setPageContext(_jspx_page_context);
              _jspx_th_liferay$1ui_error_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_5);
              _jspx_th_liferay$1ui_error_5.setException( RemoteOptionsException.class );
              int _jspx_eval_liferay$1ui_error_5 = _jspx_th_liferay$1ui_error_5.doStartTag();
              if (_jspx_eval_liferay$1ui_error_5 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                java.lang.Object errorException = null;
                if (_jspx_eval_liferay$1ui_error_5 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                  out = _jspx_page_context.pushBody();
                  _jspx_th_liferay$1ui_error_5.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                  _jspx_th_liferay$1ui_error_5.doInitBody();
                }
                errorException = (java.lang.Object) _jspx_page_context.findAttribute("errorException");
                do {
                  out.write("\n\n\t");

	RemoteOptionsException roe = (RemoteOptionsException)errorException;
	
                  out.write("\n\n\t");
                  //  c:if
                  com.liferay.taglib.core.IfTag _jspx_th_c_if_19 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                  _jspx_th_c_if_19.setPageContext(_jspx_page_context);
                  _jspx_th_c_if_19.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_error_5);
                  _jspx_th_c_if_19.setTest( roe.getType() == RemoteOptionsException.REMOTE_ADDRESS );
                  int _jspx_eval_c_if_19 = _jspx_th_c_if_19.doStartTag();
                  if (_jspx_eval_c_if_19 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    out.write("\n\t\t");
                    //  liferay-ui:message
                    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_27 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody.get(com.liferay.taglib.ui.MessageTag.class);
                    _jspx_th_liferay$1ui_message_27.setPageContext(_jspx_page_context);
                    _jspx_th_liferay$1ui_message_27.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_19);
                    _jspx_th_liferay$1ui_message_27.setArguments( roe.getRemoteAddress() );
                    _jspx_th_liferay$1ui_message_27.setKey("the-remote-address-x-is-not-valid");
                    _jspx_th_liferay$1ui_message_27.setTranslateArguments( false );
                    int _jspx_eval_liferay$1ui_message_27 = _jspx_th_liferay$1ui_message_27.doStartTag();
                    if (_jspx_th_liferay$1ui_message_27.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody.reuse(_jspx_th_liferay$1ui_message_27);
                      return;
                    }
                    _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody.reuse(_jspx_th_liferay$1ui_message_27);
                    out.write('\n');
                    out.write('	');
                  }
                  if (_jspx_th_c_if_19.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_19);
                    return;
                  }
                  _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_19);
                  out.write("\n\n\t");
                  //  c:if
                  com.liferay.taglib.core.IfTag _jspx_th_c_if_20 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                  _jspx_th_c_if_20.setPageContext(_jspx_page_context);
                  _jspx_th_c_if_20.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_error_5);
                  _jspx_th_c_if_20.setTest( roe.getType() == RemoteOptionsException.REMOTE_GROUP_ID );
                  int _jspx_eval_c_if_20 = _jspx_th_c_if_20.doStartTag();
                  if (_jspx_eval_c_if_20 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    out.write("\n\t\t");
                    //  liferay-ui:message
                    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_28 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody.get(com.liferay.taglib.ui.MessageTag.class);
                    _jspx_th_liferay$1ui_message_28.setPageContext(_jspx_page_context);
                    _jspx_th_liferay$1ui_message_28.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_20);
                    _jspx_th_liferay$1ui_message_28.setArguments( roe.getRemoteGroupId() );
                    _jspx_th_liferay$1ui_message_28.setKey("the-remote-site-id-x-is-not-valid");
                    _jspx_th_liferay$1ui_message_28.setTranslateArguments( false );
                    int _jspx_eval_liferay$1ui_message_28 = _jspx_th_liferay$1ui_message_28.doStartTag();
                    if (_jspx_th_liferay$1ui_message_28.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody.reuse(_jspx_th_liferay$1ui_message_28);
                      return;
                    }
                    _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody.reuse(_jspx_th_liferay$1ui_message_28);
                    out.write('\n');
                    out.write('	');
                  }
                  if (_jspx_th_c_if_20.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_20);
                    return;
                  }
                  _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_20);
                  out.write("\n\n\t");
                  //  c:if
                  com.liferay.taglib.core.IfTag _jspx_th_c_if_21 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                  _jspx_th_c_if_21.setPageContext(_jspx_page_context);
                  _jspx_th_c_if_21.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_error_5);
                  _jspx_th_c_if_21.setTest( roe.getType() == RemoteOptionsException.REMOTE_PATH_CONTEXT );
                  int _jspx_eval_c_if_21 = _jspx_th_c_if_21.doStartTag();
                  if (_jspx_eval_c_if_21 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    out.write("\n\t\t");
                    //  liferay-ui:message
                    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_29 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody.get(com.liferay.taglib.ui.MessageTag.class);
                    _jspx_th_liferay$1ui_message_29.setPageContext(_jspx_page_context);
                    _jspx_th_liferay$1ui_message_29.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_21);
                    _jspx_th_liferay$1ui_message_29.setArguments( roe.getRemotePathContext() );
                    _jspx_th_liferay$1ui_message_29.setKey("the-remote-path-context-x-is-not-valid");
                    _jspx_th_liferay$1ui_message_29.setTranslateArguments( false );
                    int _jspx_eval_liferay$1ui_message_29 = _jspx_th_liferay$1ui_message_29.doStartTag();
                    if (_jspx_th_liferay$1ui_message_29.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody.reuse(_jspx_th_liferay$1ui_message_29);
                      return;
                    }
                    _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody.reuse(_jspx_th_liferay$1ui_message_29);
                    out.write('\n');
                    out.write('	');
                  }
                  if (_jspx_th_c_if_21.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_21);
                    return;
                  }
                  _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_21);
                  out.write("\n\n\t");
                  //  c:if
                  com.liferay.taglib.core.IfTag _jspx_th_c_if_22 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                  _jspx_th_c_if_22.setPageContext(_jspx_page_context);
                  _jspx_th_c_if_22.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_error_5);
                  _jspx_th_c_if_22.setTest( roe.getType() == RemoteOptionsException.REMOTE_PORT );
                  int _jspx_eval_c_if_22 = _jspx_th_c_if_22.doStartTag();
                  if (_jspx_eval_c_if_22 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    out.write("\n\t\t");
                    //  liferay-ui:message
                    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_30 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody.get(com.liferay.taglib.ui.MessageTag.class);
                    _jspx_th_liferay$1ui_message_30.setPageContext(_jspx_page_context);
                    _jspx_th_liferay$1ui_message_30.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_22);
                    _jspx_th_liferay$1ui_message_30.setArguments( roe.getRemotePort() );
                    _jspx_th_liferay$1ui_message_30.setKey("the-remote-port-x-is-not-valid");
                    _jspx_th_liferay$1ui_message_30.setTranslateArguments( false );
                    int _jspx_eval_liferay$1ui_message_30 = _jspx_th_liferay$1ui_message_30.doStartTag();
                    if (_jspx_th_liferay$1ui_message_30.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody.reuse(_jspx_th_liferay$1ui_message_30);
                      return;
                    }
                    _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody.reuse(_jspx_th_liferay$1ui_message_30);
                    out.write('\n');
                    out.write('	');
                  }
                  if (_jspx_th_c_if_22.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_22);
                    return;
                  }
                  _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_22);
                  out.write('\n');
                  int evalDoAfterBody = _jspx_th_liferay$1ui_error_5.doAfterBody();
                  errorException = (java.lang.Object) _jspx_page_context.findAttribute("errorException");
                  if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                    break;
                } while (true);
                if (_jspx_eval_liferay$1ui_error_5 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                  out = _jspx_page_context.popBody();
              }
              if (_jspx_th_liferay$1ui_error_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_liferay$1ui_error_exception.reuse(_jspx_th_liferay$1ui_error_5);
                return;
              }
              _jspx_tagPool_liferay$1ui_error_exception.reuse(_jspx_th_liferay$1ui_error_5);
              out.write("\n\n\t\t");
              if (_jspx_meth_liferay$1staging_alert_4((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_5, _jspx_page_context))
                return;
              out.write("\n\n\t\t");
              //  aui:input
              com.liferay.taglib.aui.InputTag _jspx_th_aui_input_5 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_size_name_label_nobody.get(com.liferay.taglib.aui.InputTag.class);
              _jspx_th_aui_input_5.setPageContext(_jspx_page_context);
              _jspx_th_aui_input_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_5);
              _jspx_th_aui_input_5.setLabel("remote-host-ip");
              _jspx_th_aui_input_5.setName("remoteAddress");
              _jspx_th_aui_input_5.setDynamicAttribute(null, "size", new String("20"));
              _jspx_th_aui_input_5.setType("text");
              _jspx_th_aui_input_5.setValue( liveGroupTypeSettings.getProperty("remoteAddress") );
              int _jspx_eval_aui_input_5 = _jspx_th_aui_input_5.doStartTag();
              if (_jspx_th_aui_input_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_aui_input_value_type_size_name_label_nobody.reuse(_jspx_th_aui_input_5);
                return;
              }
              _jspx_tagPool_aui_input_value_type_size_name_label_nobody.reuse(_jspx_th_aui_input_5);
              out.write("\n\n\t\t");
              //  aui:input
              com.liferay.taglib.aui.InputTag _jspx_th_aui_input_6 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_size_name_label_cssClass_nobody.get(com.liferay.taglib.aui.InputTag.class);
              _jspx_th_aui_input_6.setPageContext(_jspx_page_context);
              _jspx_th_aui_input_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_5);
              _jspx_th_aui_input_6.setCssClass("remote-port");
              _jspx_th_aui_input_6.setLabel("remote-port");
              _jspx_th_aui_input_6.setName("remotePort");
              _jspx_th_aui_input_6.setDynamicAttribute(null, "size", new String("10"));
              _jspx_th_aui_input_6.setType("text");
              _jspx_th_aui_input_6.setValue( liveGroupTypeSettings.getProperty("remotePort") );
              int _jspx_eval_aui_input_6 = _jspx_th_aui_input_6.doStartTag();
              if (_jspx_th_aui_input_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_aui_input_value_type_size_name_label_cssClass_nobody.reuse(_jspx_th_aui_input_6);
                return;
              }
              _jspx_tagPool_aui_input_value_type_size_name_label_cssClass_nobody.reuse(_jspx_th_aui_input_6);
              out.write("\n\n\t\t");
              //  aui:input
              com.liferay.taglib.aui.InputTag _jspx_th_aui_input_7 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_size_name_label_nobody.get(com.liferay.taglib.aui.InputTag.class);
              _jspx_th_aui_input_7.setPageContext(_jspx_page_context);
              _jspx_th_aui_input_7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_5);
              _jspx_th_aui_input_7.setLabel("remote-path-context");
              _jspx_th_aui_input_7.setName("remotePathContext");
              _jspx_th_aui_input_7.setDynamicAttribute(null, "size", new String("10"));
              _jspx_th_aui_input_7.setType("text");
              _jspx_th_aui_input_7.setValue( liveGroupTypeSettings.getProperty("remotePathContext") );
              int _jspx_eval_aui_input_7 = _jspx_th_aui_input_7.doStartTag();
              if (_jspx_th_aui_input_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_aui_input_value_type_size_name_label_nobody.reuse(_jspx_th_aui_input_7);
                return;
              }
              _jspx_tagPool_aui_input_value_type_size_name_label_nobody.reuse(_jspx_th_aui_input_7);
              out.write("\n\n\t\t");
              //  aui:input
              com.liferay.taglib.aui.InputTag _jspx_th_aui_input_8 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_size_name_label_nobody.get(com.liferay.taglib.aui.InputTag.class);
              _jspx_th_aui_input_8.setPageContext(_jspx_page_context);
              _jspx_th_aui_input_8.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_5);
              _jspx_th_aui_input_8.setLabel( LanguageUtil.get(request, "remote-site-id") );
              _jspx_th_aui_input_8.setName("remoteGroupId");
              _jspx_th_aui_input_8.setDynamicAttribute(null, "size", new String("10"));
              _jspx_th_aui_input_8.setType("text");
              _jspx_th_aui_input_8.setValue( liveGroupTypeSettings.getProperty("remoteGroupId") );
              int _jspx_eval_aui_input_8 = _jspx_th_aui_input_8.doStartTag();
              if (_jspx_th_aui_input_8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_aui_input_value_type_size_name_label_nobody.reuse(_jspx_th_aui_input_8);
                return;
              }
              _jspx_tagPool_aui_input_value_type_size_name_label_nobody.reuse(_jspx_th_aui_input_8);
              out.write("\n\n\t\t<div class=\"form-group\">\n\n\t\t\t");

			boolean secureConnection = GetterUtil.getBoolean(liveGroupTypeSettings.getProperty("secureConnection"));
			
              out.write("\n\n\t\t\t");
              //  liferay-staging:checkbox
              com.liferay.staging.taglib.servlet.taglib.CheckboxTag _jspx_th_liferay$1staging_checkbox_0 = (com.liferay.staging.taglib.servlet.taglib.CheckboxTag) _jspx_tagPool_liferay$1staging_checkbox_name_label_checked_nobody.get(com.liferay.staging.taglib.servlet.taglib.CheckboxTag.class);
              _jspx_th_liferay$1staging_checkbox_0.setPageContext(_jspx_page_context);
              _jspx_th_liferay$1staging_checkbox_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_5);
              _jspx_th_liferay$1staging_checkbox_0.setChecked( secureConnection );
              _jspx_th_liferay$1staging_checkbox_0.setLabel("use-a-secure-network-connection");
              _jspx_th_liferay$1staging_checkbox_0.setName("secureConnection");
              int _jspx_eval_liferay$1staging_checkbox_0 = _jspx_th_liferay$1staging_checkbox_0.doStartTag();
              if (_jspx_th_liferay$1staging_checkbox_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_liferay$1staging_checkbox_name_label_checked_nobody.reuse(_jspx_th_liferay$1staging_checkbox_0);
                return;
              }
              _jspx_tagPool_liferay$1staging_checkbox_name_label_checked_nobody.reuse(_jspx_th_liferay$1staging_checkbox_0);
              out.write("\n\t\t</div>\n\t</div>\n</div>");
              out.write("\n\n\t\t\t\t\t");
              out.write("\n\n<div class=\"");
              out.print( (liveGroup.isStaged() || (stagingType != StagingConstants.TYPE_NOT_STAGED)) ? StringPool.BLANK : "hide" );
              out.write("\" id=\"");
              if (_jspx_meth_portlet_namespace_4((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_5, _jspx_page_context))
                return;
              out.write("stagedPortlets\">\n\t");
              //  c:if
              com.liferay.taglib.core.IfTag _jspx_th_c_if_23 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
              _jspx_th_c_if_23.setPageContext(_jspx_page_context);
              _jspx_th_c_if_23.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_5);
              _jspx_th_c_if_23.setTest( !liveGroup.isCompany() && !liveGroupRemoteStaging );
              int _jspx_eval_c_if_23 = _jspx_th_c_if_23.doStartTag();
              if (_jspx_eval_c_if_23 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\t\t<div class=\"sheet-section\">\n\t\t\t<div class=\"sheet-subtitle\">\n\t\t\t\t");
                if (_jspx_meth_liferay$1ui_message_32((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_23, _jspx_page_context))
                  return;
                out.write("\n\t\t\t</div>\n\n\t\t\t<div class=\"sheet-text\">\n\t\t\t\t");
                if (_jspx_meth_liferay$1ui_message_33((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_23, _jspx_page_context))
                  return;
                out.write("\n\t\t\t</div>\n\n\t\t\t");

			boolean branchingPublic = GetterUtil.getBoolean(liveGroupTypeSettings.getProperty("branchingPublic"));
			boolean branchingPrivate = GetterUtil.getBoolean(liveGroupTypeSettings.getProperty("branchingPrivate"));
			
                out.write("\n\n\t\t\t<div class=\"custom-distance\">\n\t\t\t\t");
                //  liferay-staging:checkbox
                com.liferay.staging.taglib.servlet.taglib.CheckboxTag _jspx_th_liferay$1staging_checkbox_1 = (com.liferay.staging.taglib.servlet.taglib.CheckboxTag) _jspx_tagPool_liferay$1staging_checkbox_name_label_checked_nobody.get(com.liferay.staging.taglib.servlet.taglib.CheckboxTag.class);
                _jspx_th_liferay$1staging_checkbox_1.setPageContext(_jspx_page_context);
                _jspx_th_liferay$1staging_checkbox_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_23);
                _jspx_th_liferay$1staging_checkbox_1.setChecked( branchingPublic );
                _jspx_th_liferay$1staging_checkbox_1.setLabel("enabled-on-public-pages");
                _jspx_th_liferay$1staging_checkbox_1.setName("branchingPublic");
                int _jspx_eval_liferay$1staging_checkbox_1 = _jspx_th_liferay$1staging_checkbox_1.doStartTag();
                if (_jspx_th_liferay$1staging_checkbox_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_liferay$1staging_checkbox_name_label_checked_nobody.reuse(_jspx_th_liferay$1staging_checkbox_1);
                  return;
                }
                _jspx_tagPool_liferay$1staging_checkbox_name_label_checked_nobody.reuse(_jspx_th_liferay$1staging_checkbox_1);
                out.write("\n\t\t\t</div>\n\n\t\t\t<div class=\"custom-distance\">\n\t\t\t\t");
                //  liferay-staging:checkbox
                com.liferay.staging.taglib.servlet.taglib.CheckboxTag _jspx_th_liferay$1staging_checkbox_2 = (com.liferay.staging.taglib.servlet.taglib.CheckboxTag) _jspx_tagPool_liferay$1staging_checkbox_name_label_checked_nobody.get(com.liferay.staging.taglib.servlet.taglib.CheckboxTag.class);
                _jspx_th_liferay$1staging_checkbox_2.setPageContext(_jspx_page_context);
                _jspx_th_liferay$1staging_checkbox_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_23);
                _jspx_th_liferay$1staging_checkbox_2.setChecked( branchingPrivate );
                _jspx_th_liferay$1staging_checkbox_2.setLabel("enabled-on-private-pages");
                _jspx_th_liferay$1staging_checkbox_2.setName("branchingPrivate");
                int _jspx_eval_liferay$1staging_checkbox_2 = _jspx_th_liferay$1staging_checkbox_2.doStartTag();
                if (_jspx_th_liferay$1staging_checkbox_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_liferay$1staging_checkbox_name_label_checked_nobody.reuse(_jspx_th_liferay$1staging_checkbox_2);
                  return;
                }
                _jspx_tagPool_liferay$1staging_checkbox_name_label_checked_nobody.reuse(_jspx_th_liferay$1staging_checkbox_2);
                out.write("\n\t\t\t</div>\n\t\t</div>\n\t");
              }
              if (_jspx_th_c_if_23.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_23);
                return;
              }
              _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_23);
              out.write("\n\n\t<div class=\"sheet-section\">\n\t\t<div class=\"sheet-subtitle\">\n\t\t\t");
              if (_jspx_meth_liferay$1ui_message_34((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_5, _jspx_page_context))
                return;
              out.write("\n\n\t\t\t");
              if (_jspx_meth_liferay$1staging_popover_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_5, _jspx_page_context))
                return;
              out.write("\n\t\t</div>\n\n\t\t<div id=\"");
              if (_jspx_meth_portlet_namespace_5((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_5, _jspx_page_context))
                return;
              out.write("pwcWarning\">\n\t\t\t");
              //  c:if
              com.liferay.taglib.core.IfTag _jspx_th_c_if_24 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
              _jspx_th_c_if_24.setPageContext(_jspx_page_context);
              _jspx_th_c_if_24.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_5);
              _jspx_th_c_if_24.setTest( DLAppHelperLocalServiceUtil.getCheckedOutFileEntriesCount(liveGroup.getGroupId()) > 0 );
              int _jspx_eval_c_if_24 = _jspx_th_c_if_24.doStartTag();
              if (_jspx_eval_c_if_24 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\t\t\t\t");
                if (_jspx_meth_liferay$1staging_alert_5((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_24, _jspx_page_context))
                  return;
                out.write("\n\t\t\t");
              }
              if (_jspx_th_c_if_24.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_24);
                return;
              }
              _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_24);
              out.write("\n\t\t</div>\n\n\t\t<div id=\"");
              if (_jspx_meth_portlet_namespace_6((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_5, _jspx_page_context))
                return;
              out.write("trashWarning\">\n\t\t\t");
              //  c:if
              com.liferay.taglib.core.IfTag _jspx_th_c_if_25 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
              _jspx_th_c_if_25.setPageContext(_jspx_page_context);
              _jspx_th_c_if_25.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_5);
              _jspx_th_c_if_25.setTest( TrashEntryLocalServiceUtil.getEntriesCount(liveGroup.getGroupId()) > 0 );
              int _jspx_eval_c_if_25 = _jspx_th_c_if_25.doStartTag();
              if (_jspx_eval_c_if_25 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\t\t\t\t");
                if (_jspx_meth_liferay$1staging_alert_6((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_25, _jspx_page_context))
                  return;
                out.write("\n\t\t\t");
              }
              if (_jspx_th_c_if_25.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_25);
                return;
              }
              _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_25);
              out.write("\n\t\t</div>\n\n\t\t<div class=\"sheet-text\">\n\t\t\t");
              if (_jspx_meth_liferay$1ui_message_37((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_5, _jspx_page_context))
                return;
              out.write("\n\t\t</div>\n\n\t\t");
              //  c:if
              com.liferay.taglib.core.IfTag _jspx_th_c_if_26 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
              _jspx_th_c_if_26.setPageContext(_jspx_page_context);
              _jspx_th_c_if_26.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_5);
              _jspx_th_c_if_26.setTest( !stagedLocally && !stagedRemotely );
              int _jspx_eval_c_if_26 = _jspx_th_c_if_26.doStartTag();
              if (_jspx_eval_c_if_26 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\n\t\t");
              }
              if (_jspx_th_c_if_26.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_26);
                return;
              }
              _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_26);
              out.write("\n\n\t\t<div class=\"form-group\">\n\t\t\t");
              //  liferay-staging:checkbox
              com.liferay.staging.taglib.servlet.taglib.CheckboxTag _jspx_th_liferay$1staging_checkbox_3 = (com.liferay.staging.taglib.servlet.taglib.CheckboxTag) _jspx_tagPool_liferay$1staging_checkbox_name_label_id_disabled_nobody.get(com.liferay.staging.taglib.servlet.taglib.CheckboxTag.class);
              _jspx_th_liferay$1staging_checkbox_3.setPageContext(_jspx_page_context);
              _jspx_th_liferay$1staging_checkbox_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_5);
              _jspx_th_liferay$1staging_checkbox_3.setDisabled( (liveGroupRemoteStaging && liveGroup.isStagedRemotely()) || liveGroup.isStaged() );
              _jspx_th_liferay$1staging_checkbox_3.setId("selectAllCheckbox");
              _jspx_th_liferay$1staging_checkbox_3.setLabel("select-all");
              _jspx_th_liferay$1staging_checkbox_3.setName("selectAll");
              int _jspx_eval_liferay$1staging_checkbox_3 = _jspx_th_liferay$1staging_checkbox_3.doStartTag();
              if (_jspx_th_liferay$1staging_checkbox_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_liferay$1staging_checkbox_name_label_id_disabled_nobody.reuse(_jspx_th_liferay$1staging_checkbox_3);
                return;
              }
              _jspx_tagPool_liferay$1staging_checkbox_name_label_id_disabled_nobody.reuse(_jspx_th_liferay$1staging_checkbox_3);
              out.write("\n\t\t</div>\n\n\t\t<div class=\"form-group\" id=\"stagingConfigurationControls\">\n\n\t\t\t");

			Set<String> portletDataHandlerClassNames = new HashSet<String>();

			List<Portlet> dataSiteLevelPortlets = ExportImportHelperUtil.getDataSiteLevelPortlets(company.getCompanyId(), true);

			dataSiteLevelPortlets = ListUtil.sort(dataSiteLevelPortlets, new PortletTitleComparator(application, locale));

			for (Portlet curPortlet : dataSiteLevelPortlets) {
				PortletDataHandler portletDataHandler = curPortlet.getPortletDataHandlerInstance();

				if (!portletDataHandler.isConfigurationEnabled()) {
					continue;
				}

				Class<?> portletDataHandlerClass = portletDataHandler.getClass();

				String portletDataHandlerClassName = portletDataHandlerClass.getName();

				if (!portletDataHandlerClassNames.contains(portletDataHandlerClassName)) {
					portletDataHandlerClassNames.add(portletDataHandlerClassName);
				}
				else {
					continue;
				}

				boolean staged = portletDataHandler.isPublishToLiveByDefault();

				if (stagingGroup != null) {
					staged = stagingGroup.isStagedPortlet(StagingUtil.getStagedPortletId(curPortlet.getRootPortletId()));
				}
			
              out.write("\n\n\t\t\t\t<div class=\"custom-distance\">\n\t\t\t\t\t");
              //  liferay-staging:checkbox
              com.liferay.staging.taglib.servlet.taglib.CheckboxTag _jspx_th_liferay$1staging_checkbox_4 = (com.liferay.staging.taglib.servlet.taglib.CheckboxTag) _jspx_tagPool_liferay$1staging_checkbox_name_label_disabled_checked_nobody.get(com.liferay.staging.taglib.servlet.taglib.CheckboxTag.class);
              _jspx_th_liferay$1staging_checkbox_4.setPageContext(_jspx_page_context);
              _jspx_th_liferay$1staging_checkbox_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_5);
              _jspx_th_liferay$1staging_checkbox_4.setChecked( staged );
              _jspx_th_liferay$1staging_checkbox_4.setDisabled( (liveGroupRemoteStaging && liveGroup.isStagedRemotely()) || liveGroup.isStaged() );
              _jspx_th_liferay$1staging_checkbox_4.setLabel( PortalUtil.getPortletTitle(curPortlet, application, locale) );
              _jspx_th_liferay$1staging_checkbox_4.setName( StagingConstants.STAGED_PREFIX + StagingUtil.getStagedPortletId(curPortlet.getRootPortletId()) + StringPool.DOUBLE_DASH );
              int _jspx_eval_liferay$1staging_checkbox_4 = _jspx_th_liferay$1staging_checkbox_4.doStartTag();
              if (_jspx_th_liferay$1staging_checkbox_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_liferay$1staging_checkbox_name_label_disabled_checked_nobody.reuse(_jspx_th_liferay$1staging_checkbox_4);
                return;
              }
              _jspx_tagPool_liferay$1staging_checkbox_name_label_disabled_checked_nobody.reuse(_jspx_th_liferay$1staging_checkbox_4);
              out.write("\n\t\t\t\t</div>\n\n\t\t\t");

			}
			
              out.write("\n\n\t\t</div>\n\t</div>\n</div>");
              out.write("\n\n\t\t\t\t\t<div class=\"sheet-footer\">\n\t\t\t\t\t\t<div class=\"btn-group-item\">\n\t\t\t\t\t\t\t<div class=\"btn-group-item\">\n\t\t\t\t\t\t\t\t<button class=\"btn btn-primary\">\n\t\t\t\t\t\t\t\t\t<span class=\"lfr-btn-label\">\n\t\t\t\t\t\t\t\t\t\t");
              out.print( LanguageUtil.get(request, "save") );
              out.write("\n\t\t\t\t\t\t\t\t\t</span>\n\t\t\t\t\t\t\t\t</button>\n\t\t\t\t\t\t\t</div>\n\t\t\t\t\t\t</div>\n\t\t\t\t\t</div>\n\n\t\t\t\t\t");
              //  aui:script
              com.liferay.taglib.aui.ScriptTag _jspx_th_aui_script_1 = (com.liferay.taglib.aui.ScriptTag) _jspx_tagPool_aui_script_sandbox.get(com.liferay.taglib.aui.ScriptTag.class);
              _jspx_th_aui_script_1.setPageContext(_jspx_page_context);
              _jspx_th_aui_script_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_5);
              _jspx_th_aui_script_1.setSandbox( true );
              int _jspx_eval_aui_script_1 = _jspx_th_aui_script_1.doStartTag();
              if (_jspx_eval_aui_script_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                if (_jspx_eval_aui_script_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                  out = _jspx_page_context.pushBody();
                  _jspx_th_aui_script_1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                  _jspx_th_aui_script_1.doInitBody();
                }
                do {
                  out.write("\n\t\t\t\t\t\tvar pwcWarning = $('#");
                  if (_jspx_meth_portlet_namespace_7((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
                    return;
                  out.write("pwcWarning');\n\t\t\t\t\t\tvar remoteStagingOptions = $('#");
                  if (_jspx_meth_portlet_namespace_8((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
                    return;
                  out.write("remoteStagingOptions');\n\t\t\t\t\t\tvar stagedPortlets = $('#");
                  if (_jspx_meth_portlet_namespace_9((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
                    return;
                  out.write("stagedPortlets');\n\t\t\t\t\t\tvar trashWarning = $('#");
                  if (_jspx_meth_portlet_namespace_10((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
                    return;
                  out.write("trashWarning');\n\n\t\t\t\t\t\tvar stagingTypes = $('#");
                  if (_jspx_meth_portlet_namespace_11((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
                    return;
                  out.write("stagingTypes');\n\n\t\t\t\t\t\tstagingTypes.on(\n\t\t\t\t\t\t\t'click',\n\t\t\t\t\t\t\t'input',\n\t\t\t\t\t\t\tfunction(event) {\n\t\t\t\t\t\t\t\tvar value = $(event.currentTarget).val();\n\n\t\t\t\t\t\t\t\tpwcWarning.toggleClass('hide', value != '");
                  out.print( StagingConstants.TYPE_LOCAL_STAGING );
                  out.write("');\n\n\t\t\t\t\t\t\t\tstagedPortlets.toggleClass('hide', value == '");
                  out.print( StagingConstants.TYPE_NOT_STAGED );
                  out.write("');\n\n\t\t\t\t\t\t\t\tremoteStagingOptions.toggleClass('hide', value != '");
                  out.print( StagingConstants.TYPE_REMOTE_STAGING );
                  out.write("');\n\n\t\t\t\t\t\t\t\ttrashWarning.toggleClass('hide', value != '");
                  out.print( StagingConstants.TYPE_LOCAL_STAGING );
                  out.write("');\n\t\t\t\t\t\t\t}\n\t\t\t\t\t\t);\n\t\t\t\t\t");
                  int evalDoAfterBody = _jspx_th_aui_script_1.doAfterBody();
                  if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                    break;
                } while (true);
                if (_jspx_eval_aui_script_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                  out = _jspx_page_context.popBody();
              }
              if (_jspx_th_aui_script_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_aui_script_sandbox.reuse(_jspx_th_aui_script_1);
                return;
              }
              _jspx_tagPool_aui_script_sandbox.reuse(_jspx_th_aui_script_1);
              out.write("\n\t\t\t\t");
            }
            if (_jspx_th_c_if_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_5);
              return;
            }
            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_5);
            out.write("\n\t\t\t");
          }
          if (_jspx_th_aui_form_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_aui_form_onSubmit_name_method_action.reuse(_jspx_th_aui_form_0);
            return;
          }
          _jspx_tagPool_aui_form_onSubmit_name_method_action.reuse(_jspx_th_aui_form_0);
          out.write("\n\t\t</div>\n\t");
        }
        if (_jspx_th_c_when_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_0);
          return;
        }
        _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_0);
        out.write('\n');
        out.write('	');
        if (_jspx_meth_c_otherwise_2((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_choose_0, _jspx_page_context))
          return;
        out.write('\n');
      }
      if (_jspx_th_c_choose_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_0);
        return;
      }
      _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_0);
      out.write('\n');
      out.write('\n');
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
          if (_jspx_meth_portlet_namespace_12((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_2, _jspx_page_context))
            return;
          out.write("saveGroup(forceDisable) {\n\t\tvar $ = AUI.$;\n\n\t\tvar form = $(document.");
          if (_jspx_meth_portlet_namespace_13((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_2, _jspx_page_context))
            return;
          out.write("fm);\n\n\t\tvar ok = true;\n\n\t\t");
          //  c:if
          com.liferay.taglib.core.IfTag _jspx_th_c_if_27 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
          _jspx_th_c_if_27.setPageContext(_jspx_page_context);
          _jspx_th_c_if_27.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_2);
          _jspx_th_c_if_27.setTest( liveGroup != null );
          int _jspx_eval_c_if_27 = _jspx_th_c_if_27.doStartTag();
          if (_jspx_eval_c_if_27 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t\tvar stagingTypeEl = $('input[name=");
            if (_jspx_meth_portlet_namespace_14((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_27, _jspx_page_context))
              return;
            out.write("stagingType]:checked');\n\n\t\t\tvar oldValue;\n\n\t\t\t");
            //  c:choose
            com.liferay.taglib.core.ChooseTag _jspx_th_c_choose_3 = (com.liferay.taglib.core.ChooseTag) _jspx_tagPool_c_choose.get(com.liferay.taglib.core.ChooseTag.class);
            _jspx_th_c_choose_3.setPageContext(_jspx_page_context);
            _jspx_th_c_choose_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_27);
            int _jspx_eval_c_choose_3 = _jspx_th_c_choose_3.doStartTag();
            if (_jspx_eval_c_choose_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\t\t\t\t");
              //  c:when
              com.liferay.taglib.core.WhenTag _jspx_th_c_when_3 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
              _jspx_th_c_when_3.setPageContext(_jspx_page_context);
              _jspx_th_c_when_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_3);
              _jspx_th_c_when_3.setTest( liveGroup.isStaged() && !liveGroup.isStagedRemotely() );
              int _jspx_eval_c_when_3 = _jspx_th_c_when_3.doStartTag();
              if (_jspx_eval_c_when_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\t\t\t\t\toldValue = ");
                out.print( StagingConstants.TYPE_LOCAL_STAGING );
                out.write(";\n\t\t\t\t");
              }
              if (_jspx_th_c_when_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_3);
                return;
              }
              _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_3);
              out.write("\n\t\t\t\t");
              //  c:when
              com.liferay.taglib.core.WhenTag _jspx_th_c_when_4 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
              _jspx_th_c_when_4.setPageContext(_jspx_page_context);
              _jspx_th_c_when_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_3);
              _jspx_th_c_when_4.setTest( liveGroup.isStaged() && liveGroup.isStagedRemotely() );
              int _jspx_eval_c_when_4 = _jspx_th_c_when_4.doStartTag();
              if (_jspx_eval_c_when_4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\t\t\t\t\toldValue = ");
                out.print( StagingConstants.TYPE_REMOTE_STAGING );
                out.write(";\n\t\t\t\t");
              }
              if (_jspx_th_c_when_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_4);
                return;
              }
              _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_4);
              out.write("\n\t\t\t\t");
              //  c:otherwise
              com.liferay.taglib.core.OtherwiseTag _jspx_th_c_otherwise_3 = (com.liferay.taglib.core.OtherwiseTag) _jspx_tagPool_c_otherwise.get(com.liferay.taglib.core.OtherwiseTag.class);
              _jspx_th_c_otherwise_3.setPageContext(_jspx_page_context);
              _jspx_th_c_otherwise_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_3);
              int _jspx_eval_c_otherwise_3 = _jspx_th_c_otherwise_3.doStartTag();
              if (_jspx_eval_c_otherwise_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\t\t\t\t\toldValue = ");
                out.print( StagingConstants.TYPE_NOT_STAGED );
                out.write(";\n\t\t\t\t");
              }
              if (_jspx_th_c_otherwise_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_3);
                return;
              }
              _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_3);
              out.write("\n\t\t\t");
            }
            if (_jspx_th_c_choose_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_3);
              return;
            }
            _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_3);
            out.write("\n\n\t\t\tvar currentValue = stagingTypeEl.val();\n\n\t\t\tif (stagingTypeEl.length && (currentValue != oldValue)) {\n\t\t\t\tok = false;\n\n\t\t\t\tif (currentValue == ");
            out.print( StagingConstants.TYPE_NOT_STAGED );
            out.write(") {\n\t\t\t\t\tok = confirm('");
            out.print( UnicodeLanguageUtil.format(request, "are-you-sure-you-want-to-deactivate-staging-for-x", liveGroup.getDescriptiveName(locale), false) );
            out.write("');\n\t\t\t\t}\n\t\t\t\telse if (currentValue == ");
            out.print( StagingConstants.TYPE_LOCAL_STAGING );
            out.write(") {\n\t\t\t\t\tok = confirm('");
            out.print( UnicodeLanguageUtil.format(request, "are-you-sure-you-want-to-activate-local-staging-for-x", liveGroup.getDescriptiveName(locale), false) );
            out.write("');\n\t\t\t\t}\n\t\t\t\telse if (currentValue == ");
            out.print( StagingConstants.TYPE_REMOTE_STAGING );
            out.write(") {\n\t\t\t\t\tok = confirm('");
            out.print( UnicodeLanguageUtil.format(request, "are-you-sure-you-want-to-activate-remote-staging-for-x", liveGroup.getDescriptiveName(locale), false) );
            out.write("');\n\t\t\t\t}\n\t\t\t}\n\t\t");
          }
          if (_jspx_th_c_if_27.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_27);
            return;
          }
          _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_27);
          out.write("\n\n\t\tif (ok) {\n\t\t\tif (forceDisable) {\n\t\t\t\tform.fm('forceDisable').val(true);\n\t\t\t\tform.fm('local').prop('checked', false);\n\t\t\t\tform.fm('none').prop('checked', true);\n\t\t\t\tform.fm('redirect').val('");
          //  portlet:renderURL
          com.liferay.taglib.portlet.RenderURLTag _jspx_th_portlet_renderURL_1 = (com.liferay.taglib.portlet.RenderURLTag) _jspx_tagPool_portlet_renderURL.get(com.liferay.taglib.portlet.RenderURLTag.class);
          _jspx_th_portlet_renderURL_1.setPageContext(_jspx_page_context);
          _jspx_th_portlet_renderURL_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_2);
          int _jspx_eval_portlet_renderURL_1 = _jspx_th_portlet_renderURL_1.doStartTag();
          if (_jspx_eval_portlet_renderURL_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            if (_jspx_meth_portlet_param_11((javax.servlet.jsp.tagext.JspTag) _jspx_th_portlet_renderURL_1, _jspx_page_context))
              return;
            //  portlet:param
            com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_12 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
            _jspx_th_portlet_param_12.setPageContext(_jspx_page_context);
            _jspx_th_portlet_param_12.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_1);
            _jspx_th_portlet_param_12.setName("historyKey");
            _jspx_th_portlet_param_12.setValue( renderResponse.getNamespace() + "staging" );
            int _jspx_eval_portlet_param_12 = _jspx_th_portlet_param_12.doStartTag();
            if (_jspx_th_portlet_param_12.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_12);
              return;
            }
            _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_12);
          }
          if (_jspx_th_portlet_renderURL_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_portlet_renderURL.reuse(_jspx_th_portlet_renderURL_1);
            return;
          }
          _jspx_tagPool_portlet_renderURL.reuse(_jspx_th_portlet_renderURL_1);
          out.write("');\n\t\t\t\tform.fm('remote').prop('checked', false);\n\t\t\t}\n\n\t\t\tsubmitForm(form);\n\t\t}\n\t}\n");
          int evalDoAfterBody = _jspx_th_aui_script_2.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
        if (_jspx_eval_aui_script_2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
          out = _jspx_page_context.popBody();
      }
      if (_jspx_th_aui_script_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_aui_script.reuse(_jspx_th_aui_script_2);
        return;
      }
      _jspx_tagPool_aui_script.reuse(_jspx_th_aui_script_2);
      out.write('\n');
      out.write('\n');
      //  aui:script
      com.liferay.taglib.aui.ScriptTag _jspx_th_aui_script_3 = (com.liferay.taglib.aui.ScriptTag) _jspx_tagPool_aui_script_sandbox.get(com.liferay.taglib.aui.ScriptTag.class);
      _jspx_th_aui_script_3.setPageContext(_jspx_page_context);
      _jspx_th_aui_script_3.setParent(null);
      _jspx_th_aui_script_3.setSandbox( true );
      int _jspx_eval_aui_script_3 = _jspx_th_aui_script_3.doStartTag();
      if (_jspx_eval_aui_script_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        if (_jspx_eval_aui_script_3 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
          out = _jspx_page_context.pushBody();
          _jspx_th_aui_script_3.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
          _jspx_th_aui_script_3.doInitBody();
        }
        do {
          out.write("\n\tvar stagingConfigurationControls = $('#stagingConfigurationControls');\n\n\tvar allCheckboxes = stagingConfigurationControls.find('input[type=checkbox]');\n\n\t$('#");
          if (_jspx_meth_portlet_namespace_15((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_3, _jspx_page_context))
            return;
          out.write("selectAllCheckbox').on(\n\t\t'change',\n\t\tfunction() {\n\t\t\tallCheckboxes.prop('checked', this.checked);\n\t\t}\n\t);\n");
          int evalDoAfterBody = _jspx_th_aui_script_3.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
        if (_jspx_eval_aui_script_3 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
          out = _jspx_page_context.popBody();
      }
      if (_jspx_th_aui_script_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_aui_script_sandbox.reuse(_jspx_th_aui_script_3);
        return;
      }
      _jspx_tagPool_aui_script_sandbox.reuse(_jspx_th_aui_script_3);
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

  private boolean _jspx_meth_liferay$1ui_message_2(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1staging_alert_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_2 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_2.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1staging_alert_1);
    _jspx_th_liferay$1ui_message_2.setKey("an-unexpected-error-occurred-with-the-initial-staging-publication");
    int _jspx_eval_liferay$1ui_message_2 = _jspx_th_liferay$1ui_message_2.doStartTag();
    if (_jspx_th_liferay$1ui_message_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_2);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_2);
    return false;
  }

  private boolean _jspx_meth_liferay$1staging_alert_2(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-staging:alert
    com.liferay.staging.taglib.servlet.taglib.AlertTag _jspx_th_liferay$1staging_alert_2 = (com.liferay.staging.taglib.servlet.taglib.AlertTag) _jspx_tagPool_liferay$1staging_alert_type.get(com.liferay.staging.taglib.servlet.taglib.AlertTag.class);
    _jspx_th_liferay$1staging_alert_2.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1staging_alert_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_2);
    _jspx_th_liferay$1staging_alert_2.setType(Enum.valueOf(com.liferay.staging.taglib.servlet.taglib.AlertType.class, "WARNING"));
    int _jspx_eval_liferay$1staging_alert_2 = _jspx_th_liferay$1staging_alert_2.doStartTag();
    if (_jspx_eval_liferay$1staging_alert_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_liferay$1staging_alert_2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_liferay$1staging_alert_2.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_liferay$1staging_alert_2.doInitBody();
      }
      do {
        out.write("\n\t\t");
        if (_jspx_meth_liferay$1ui_message_3((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1staging_alert_2, _jspx_page_context))
          return true;
        out.write("\n\n\t\t<a id=\"");
        if (_jspx_meth_portlet_namespace_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1staging_alert_2, _jspx_page_context))
          return true;
        out.write("publishProcessesLink\">");
        if (_jspx_meth_liferay$1ui_message_4((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1staging_alert_2, _jspx_page_context))
          return true;
        out.write("</a>\n\t");
        int evalDoAfterBody = _jspx_th_liferay$1staging_alert_2.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_liferay$1staging_alert_2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
        out = _jspx_page_context.popBody();
    }
    if (_jspx_th_liferay$1staging_alert_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1staging_alert_type.reuse(_jspx_th_liferay$1staging_alert_2);
      return true;
    }
    _jspx_tagPool_liferay$1staging_alert_type.reuse(_jspx_th_liferay$1staging_alert_2);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_3(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1staging_alert_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_3 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_3.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1staging_alert_2);
    _jspx_th_liferay$1ui_message_3.setKey("an-inital-staging-publication-is-in-progress");
    int _jspx_eval_liferay$1ui_message_3 = _jspx_th_liferay$1ui_message_3.doStartTag();
    if (_jspx_th_liferay$1ui_message_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_3);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_3);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_0(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1staging_alert_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_0 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_0.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1staging_alert_2);
    int _jspx_eval_portlet_namespace_0 = _jspx_th_portlet_namespace_0.doStartTag();
    if (_jspx_th_portlet_namespace_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_0);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_0);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_4(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1staging_alert_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_4 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_4.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1staging_alert_2);
    _jspx_th_liferay$1ui_message_4.setKey("the-status-of-the-publication-can-be-checked-on-the-publish-screen");
    int _jspx_eval_liferay$1ui_message_4 = _jspx_th_liferay$1ui_message_4.doStartTag();
    if (_jspx_th_liferay$1ui_message_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_4);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_4);
    return false;
  }

  private boolean _jspx_meth_portlet_param_2(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1portlet_renderURL_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:param
    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_2 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
    _jspx_th_portlet_param_2.setPageContext(_jspx_page_context);
    _jspx_th_portlet_param_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_0);
    _jspx_th_portlet_param_2.setName("mvcRenderCommandName");
    _jspx_th_portlet_param_2.setValue("publishLayouts");
    int _jspx_eval_portlet_param_2 = _jspx_th_portlet_param_2.doStartTag();
    if (_jspx_th_portlet_param_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_2);
      return true;
    }
    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_2);
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

  private boolean _jspx_meth_liferay$1ui_message_5(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_5 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_5.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_0);
    _jspx_th_liferay$1ui_message_5.setKey("initial-publication");
    int _jspx_eval_liferay$1ui_message_5 = _jspx_th_liferay$1ui_message_5.doStartTag();
    if (_jspx_th_liferay$1ui_message_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_5);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_5);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_6(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1staging_alert_3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_6 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_6.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1staging_alert_3);
    _jspx_th_liferay$1ui_message_6.setKey("staging-cannot-be-used-for-this-site-because-the-propagation-of-changes-from-the-site-template-is-enabled");
    int _jspx_eval_liferay$1ui_message_6 = _jspx_th_liferay$1ui_message_6.doStartTag();
    if (_jspx_th_liferay$1ui_message_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_6);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_6);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_7(javax.servlet.jsp.tagext.JspTag _jspx_th_c_when_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_7 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_7.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_1);
    _jspx_th_liferay$1ui_message_7.setKey("change-the-configuration-in-the-details-section");
    int _jspx_eval_liferay$1ui_message_7 = _jspx_th_liferay$1ui_message_7.doStartTag();
    if (_jspx_th_liferay$1ui_message_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_7);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_7);
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
      out.write("\n\t\t\t\t");
      if (_jspx_meth_liferay$1ui_message_8((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_otherwise_0, _jspx_page_context))
        return true;
      out.write("\n\t\t\t");
    }
    if (_jspx_th_c_otherwise_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_0);
      return true;
    }
    _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_0);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_8(javax.servlet.jsp.tagext.JspTag _jspx_th_c_otherwise_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_8 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_8.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_8.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_0);
    _jspx_th_liferay$1ui_message_8.setKey("contact-your-administrator-to-change-the-configuration");
    int _jspx_eval_liferay$1ui_message_8 = _jspx_th_liferay$1ui_message_8.doStartTag();
    if (_jspx_th_liferay$1ui_message_8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_8);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_8);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_9(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1ui_error_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_9 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_9.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_9.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_error_0);
    _jspx_th_liferay$1ui_message_9.setKey("an-unexpected-error-occurred-with-the-initial-staging-publication");
    int _jspx_eval_liferay$1ui_message_9 = _jspx_th_liferay$1ui_message_9.doStartTag();
    if (_jspx_th_liferay$1ui_message_9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_9);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_9);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_success_0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_when_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:success
    com.liferay.taglib.ui.SuccessTag _jspx_th_liferay$1ui_success_0 = (com.liferay.taglib.ui.SuccessTag) _jspx_tagPool_liferay$1ui_success_message_key_nobody.get(com.liferay.taglib.ui.SuccessTag.class);
    _jspx_th_liferay$1ui_success_0.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_success_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_0);
    _jspx_th_liferay$1ui_success_0.setKey("stagingDisabled");
    _jspx_th_liferay$1ui_success_0.setMessage("staging-is-successfully-disabled");
    int _jspx_eval_liferay$1ui_success_0 = _jspx_th_liferay$1ui_success_0.doStartTag();
    if (_jspx_th_liferay$1ui_success_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_success_message_key_nobody.reuse(_jspx_th_liferay$1ui_success_0);
      return true;
    }
    _jspx_tagPool_liferay$1ui_success_message_key_nobody.reuse(_jspx_th_liferay$1ui_success_0);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_success_1(javax.servlet.jsp.tagext.JspTag _jspx_th_c_when_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:success
    com.liferay.taglib.ui.SuccessTag _jspx_th_liferay$1ui_success_1 = (com.liferay.taglib.ui.SuccessTag) _jspx_tagPool_liferay$1ui_success_message_key_nobody.get(com.liferay.taglib.ui.SuccessTag.class);
    _jspx_th_liferay$1ui_success_1.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_success_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_0);
    _jspx_th_liferay$1ui_success_1.setKey("localStagingModified");
    _jspx_th_liferay$1ui_success_1.setMessage("local-staging-configuration-is-successfully-modified");
    int _jspx_eval_liferay$1ui_success_1 = _jspx_th_liferay$1ui_success_1.doStartTag();
    if (_jspx_th_liferay$1ui_success_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_success_message_key_nobody.reuse(_jspx_th_liferay$1ui_success_1);
      return true;
    }
    _jspx_tagPool_liferay$1ui_success_message_key_nobody.reuse(_jspx_th_liferay$1ui_success_1);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_success_2(javax.servlet.jsp.tagext.JspTag _jspx_th_c_when_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:success
    com.liferay.taglib.ui.SuccessTag _jspx_th_liferay$1ui_success_2 = (com.liferay.taglib.ui.SuccessTag) _jspx_tagPool_liferay$1ui_success_message_key_nobody.get(com.liferay.taglib.ui.SuccessTag.class);
    _jspx_th_liferay$1ui_success_2.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_success_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_0);
    _jspx_th_liferay$1ui_success_2.setKey("remoteStagingModified");
    _jspx_th_liferay$1ui_success_2.setMessage("remote-staging-configuration-is-successfully-modified");
    int _jspx_eval_liferay$1ui_success_2 = _jspx_th_liferay$1ui_success_2.doStartTag();
    if (_jspx_th_liferay$1ui_success_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_success_message_key_nobody.reuse(_jspx_th_liferay$1ui_success_2);
      return true;
    }
    _jspx_tagPool_liferay$1ui_success_message_key_nobody.reuse(_jspx_th_liferay$1ui_success_2);
    return false;
  }

  private boolean _jspx_meth_portlet_param_9(javax.servlet.jsp.tagext.JspTag _jspx_th_portlet_actionURL_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:param
    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_9 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
    _jspx_th_portlet_param_9.setPageContext(_jspx_page_context);
    _jspx_th_portlet_param_9.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_actionURL_1);
    _jspx_th_portlet_param_9.setName("mvcPath");
    _jspx_th_portlet_param_9.setValue("/view.jsp");
    int _jspx_eval_portlet_param_9 = _jspx_th_portlet_param_9.doStartTag();
    if (_jspx_th_portlet_param_9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_9);
      return true;
    }
    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_9);
    return false;
  }

  private boolean _jspx_meth_portlet_param_10(javax.servlet.jsp.tagext.JspTag _jspx_th_portlet_renderURL_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:param
    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_10 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
    _jspx_th_portlet_param_10.setPageContext(_jspx_page_context);
    _jspx_th_portlet_param_10.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_0);
    _jspx_th_portlet_param_10.setName("mvcRenderCommandName");
    _jspx_th_portlet_param_10.setValue("staging");
    int _jspx_eval_portlet_param_10 = _jspx_th_portlet_param_10.doStartTag();
    if (_jspx_th_portlet_param_10.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_10);
      return true;
    }
    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_10);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_12(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_5, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_12 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_12.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_12.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_5);
    _jspx_th_liferay$1ui_message_12.setKey("javax.portlet.title.com_liferay_staging_configuration_web_portlet_StagingConfigurationPortlet");
    int _jspx_eval_liferay$1ui_message_12 = _jspx_th_liferay$1ui_message_12.doStartTag();
    if (_jspx_th_liferay$1ui_message_12.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_12);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_12);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_13(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_5, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_13 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_13.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_13.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_5);
    _jspx_th_liferay$1ui_message_13.setKey("select-one-of-the-options");
    int _jspx_eval_liferay$1ui_message_13 = _jspx_th_liferay$1ui_message_13.doStartTag();
    if (_jspx_th_liferay$1ui_message_13.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_13);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_13);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_2(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_5, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_2 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_2.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_5);
    int _jspx_eval_portlet_namespace_2 = _jspx_th_portlet_namespace_2.doStartTag();
    if (_jspx_th_portlet_namespace_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_2);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_2);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_3(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_5, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_3 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_3.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_5);
    int _jspx_eval_portlet_namespace_3 = _jspx_th_portlet_namespace_3.doStartTag();
    if (_jspx_th_portlet_namespace_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_3);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_3);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_15(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_9, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_15 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_15.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_15.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_9);
    _jspx_th_liferay$1ui_message_15.setKey("internal-server-error");
    int _jspx_eval_liferay$1ui_message_15 = _jspx_th_liferay$1ui_message_15.doStartTag();
    if (_jspx_th_liferay$1ui_message_15.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_15);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_15);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_16(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_10, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_16 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_16.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_16.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_10);
    _jspx_th_liferay$1ui_message_16.setKey("the-tunneling-servlet-shared-secret-must-be-at-least-8-bytes-long");
    int _jspx_eval_liferay$1ui_message_16 = _jspx_th_liferay$1ui_message_16.doStartTag();
    if (_jspx_th_liferay$1ui_message_16.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_16);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_16);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_17(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_11, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_17 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_17.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_17.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_11);
    _jspx_th_liferay$1ui_message_17.setKey("the-tunneling-servlet-shared-secret-is-not-set");
    int _jspx_eval_liferay$1ui_message_17 = _jspx_th_liferay$1ui_message_17.doStartTag();
    if (_jspx_th_liferay$1ui_message_17.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_17);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_17);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_18(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_12, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_18 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_18.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_18.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_12);
    _jspx_th_liferay$1ui_message_18.setKey("the-tunneling-servlet-shared-secrets-do-not-match");
    int _jspx_eval_liferay$1ui_message_18 = _jspx_th_liferay$1ui_message_18.doStartTag();
    if (_jspx_th_liferay$1ui_message_18.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_18);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_18);
    return false;
  }

  private boolean _jspx_meth_c_otherwise_1(javax.servlet.jsp.tagext.JspTag _jspx_th_c_choose_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:otherwise
    com.liferay.taglib.core.OtherwiseTag _jspx_th_c_otherwise_1 = (com.liferay.taglib.core.OtherwiseTag) _jspx_tagPool_c_otherwise.get(com.liferay.taglib.core.OtherwiseTag.class);
    _jspx_th_c_otherwise_1.setPageContext(_jspx_page_context);
    _jspx_th_c_otherwise_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_2);
    int _jspx_eval_c_otherwise_1 = _jspx_th_c_otherwise_1.doStartTag();
    if (_jspx_eval_c_otherwise_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      out.write("\n\t\t\t\t\t");
      if (_jspx_meth_liferay$1ui_message_21((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_otherwise_1, _jspx_page_context))
        return true;
      out.write("\n\t\t\t\t");
    }
    if (_jspx_th_c_otherwise_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_1);
      return true;
    }
    _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_1);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_21(javax.servlet.jsp.tagext.JspTag _jspx_th_c_otherwise_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_21 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_21.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_21.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_1);
    _jspx_th_liferay$1ui_message_21.setKey("if-everything-is-configured-correctly,-but-you-still-encounter-this-error,-the-administrator-has-the-option-to-forcibly-disable-remote-staging");
    int _jspx_eval_liferay$1ui_message_21 = _jspx_th_liferay$1ui_message_21.doStartTag();
    if (_jspx_th_liferay$1ui_message_21.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_21);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_21);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_26(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_5, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_26 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_26.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_26.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_5);
    _jspx_th_liferay$1ui_message_26.setKey("remote-live-connection-settings");
    int _jspx_eval_liferay$1ui_message_26 = _jspx_th_liferay$1ui_message_26.doStartTag();
    if (_jspx_th_liferay$1ui_message_26.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_26);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_26);
    return false;
  }

  private boolean _jspx_meth_liferay$1staging_alert_4(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_5, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-staging:alert
    com.liferay.staging.taglib.servlet.taglib.AlertTag _jspx_th_liferay$1staging_alert_4 = (com.liferay.staging.taglib.servlet.taglib.AlertTag) _jspx_tagPool_liferay$1staging_alert_type.get(com.liferay.staging.taglib.servlet.taglib.AlertTag.class);
    _jspx_th_liferay$1staging_alert_4.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1staging_alert_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_5);
    _jspx_th_liferay$1staging_alert_4.setType(Enum.valueOf(com.liferay.staging.taglib.servlet.taglib.AlertType.class, "INFO"));
    int _jspx_eval_liferay$1staging_alert_4 = _jspx_th_liferay$1staging_alert_4.doStartTag();
    if (_jspx_eval_liferay$1staging_alert_4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_liferay$1staging_alert_4 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_liferay$1staging_alert_4.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_liferay$1staging_alert_4.doInitBody();
      }
      do {
        out.write("\n\t\t\t");
        if (_jspx_meth_liferay$1ui_message_31((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1staging_alert_4, _jspx_page_context))
          return true;
        out.write("\n\t\t");
        int evalDoAfterBody = _jspx_th_liferay$1staging_alert_4.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_liferay$1staging_alert_4 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
        out = _jspx_page_context.popBody();
    }
    if (_jspx_th_liferay$1staging_alert_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1staging_alert_type.reuse(_jspx_th_liferay$1staging_alert_4);
      return true;
    }
    _jspx_tagPool_liferay$1staging_alert_type.reuse(_jspx_th_liferay$1staging_alert_4);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_31(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1staging_alert_4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_31 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_31.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_31.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1staging_alert_4);
    _jspx_th_liferay$1ui_message_31.setKey("remote-publish-help");
    int _jspx_eval_liferay$1ui_message_31 = _jspx_th_liferay$1ui_message_31.doStartTag();
    if (_jspx_th_liferay$1ui_message_31.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_31);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_31);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_4(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_5, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_4 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_4.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_5);
    int _jspx_eval_portlet_namespace_4 = _jspx_th_portlet_namespace_4.doStartTag();
    if (_jspx_th_portlet_namespace_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_4);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_4);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_32(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_23, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_32 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_32.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_32.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_23);
    _jspx_th_liferay$1ui_message_32.setKey("page-versioning");
    int _jspx_eval_liferay$1ui_message_32 = _jspx_th_liferay$1ui_message_32.doStartTag();
    if (_jspx_th_liferay$1ui_message_32.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_32);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_32);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_33(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_23, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_33 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_33.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_33.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_23);
    _jspx_th_liferay$1ui_message_33.setKey("page-versioning-help");
    int _jspx_eval_liferay$1ui_message_33 = _jspx_th_liferay$1ui_message_33.doStartTag();
    if (_jspx_th_liferay$1ui_message_33.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_33);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_33);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_34(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_5, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_34 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_34.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_34.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_5);
    _jspx_th_liferay$1ui_message_34.setKey("staged-content");
    int _jspx_eval_liferay$1ui_message_34 = _jspx_th_liferay$1ui_message_34.doStartTag();
    if (_jspx_th_liferay$1ui_message_34.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_34);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_34);
    return false;
  }

  private boolean _jspx_meth_liferay$1staging_popover_0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_5, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-staging:popover
    com.liferay.staging.taglib.servlet.taglib.PopoverTag _jspx_th_liferay$1staging_popover_0 = (com.liferay.staging.taglib.servlet.taglib.PopoverTag) _jspx_tagPool_liferay$1staging_popover_title_text_id_nobody.get(com.liferay.staging.taglib.servlet.taglib.PopoverTag.class);
    _jspx_th_liferay$1staging_popover_0.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1staging_popover_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_5);
    _jspx_th_liferay$1staging_popover_0.setId("stagedportlets");
    _jspx_th_liferay$1staging_popover_0.setText("staged-applications-help");
    _jspx_th_liferay$1staging_popover_0.setTitle("staged-content");
    int _jspx_eval_liferay$1staging_popover_0 = _jspx_th_liferay$1staging_popover_0.doStartTag();
    if (_jspx_th_liferay$1staging_popover_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1staging_popover_title_text_id_nobody.reuse(_jspx_th_liferay$1staging_popover_0);
      return true;
    }
    _jspx_tagPool_liferay$1staging_popover_title_text_id_nobody.reuse(_jspx_th_liferay$1staging_popover_0);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_5(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_5, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_5 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_5.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_5);
    int _jspx_eval_portlet_namespace_5 = _jspx_th_portlet_namespace_5.doStartTag();
    if (_jspx_th_portlet_namespace_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_5);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_5);
    return false;
  }

  private boolean _jspx_meth_liferay$1staging_alert_5(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_24, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-staging:alert
    com.liferay.staging.taglib.servlet.taglib.AlertTag _jspx_th_liferay$1staging_alert_5 = (com.liferay.staging.taglib.servlet.taglib.AlertTag) _jspx_tagPool_liferay$1staging_alert_type.get(com.liferay.staging.taglib.servlet.taglib.AlertTag.class);
    _jspx_th_liferay$1staging_alert_5.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1staging_alert_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_24);
    _jspx_th_liferay$1staging_alert_5.setType(Enum.valueOf(com.liferay.staging.taglib.servlet.taglib.AlertType.class, "WARNING"));
    int _jspx_eval_liferay$1staging_alert_5 = _jspx_th_liferay$1staging_alert_5.doStartTag();
    if (_jspx_eval_liferay$1staging_alert_5 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_liferay$1staging_alert_5 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_liferay$1staging_alert_5.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_liferay$1staging_alert_5.doInitBody();
      }
      do {
        out.write("\n\t\t\t\t\t");
        if (_jspx_meth_liferay$1ui_message_35((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1staging_alert_5, _jspx_page_context))
          return true;
        out.write("\n\t\t\t\t");
        int evalDoAfterBody = _jspx_th_liferay$1staging_alert_5.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_liferay$1staging_alert_5 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
        out = _jspx_page_context.popBody();
    }
    if (_jspx_th_liferay$1staging_alert_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1staging_alert_type.reuse(_jspx_th_liferay$1staging_alert_5);
      return true;
    }
    _jspx_tagPool_liferay$1staging_alert_type.reuse(_jspx_th_liferay$1staging_alert_5);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_35(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1staging_alert_5, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_35 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_35.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_35.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1staging_alert_5);
    _jspx_th_liferay$1ui_message_35.setKey("local-staging-pwc-warning");
    int _jspx_eval_liferay$1ui_message_35 = _jspx_th_liferay$1ui_message_35.doStartTag();
    if (_jspx_th_liferay$1ui_message_35.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_35);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_35);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_6(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_5, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_6 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_6.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_5);
    int _jspx_eval_portlet_namespace_6 = _jspx_th_portlet_namespace_6.doStartTag();
    if (_jspx_th_portlet_namespace_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_6);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_6);
    return false;
  }

  private boolean _jspx_meth_liferay$1staging_alert_6(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_25, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-staging:alert
    com.liferay.staging.taglib.servlet.taglib.AlertTag _jspx_th_liferay$1staging_alert_6 = (com.liferay.staging.taglib.servlet.taglib.AlertTag) _jspx_tagPool_liferay$1staging_alert_type.get(com.liferay.staging.taglib.servlet.taglib.AlertTag.class);
    _jspx_th_liferay$1staging_alert_6.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1staging_alert_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_25);
    _jspx_th_liferay$1staging_alert_6.setType(Enum.valueOf(com.liferay.staging.taglib.servlet.taglib.AlertType.class, "WARNING"));
    int _jspx_eval_liferay$1staging_alert_6 = _jspx_th_liferay$1staging_alert_6.doStartTag();
    if (_jspx_eval_liferay$1staging_alert_6 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_liferay$1staging_alert_6 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_liferay$1staging_alert_6.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_liferay$1staging_alert_6.doInitBody();
      }
      do {
        out.write("\n\t\t\t\t\t");
        if (_jspx_meth_liferay$1ui_message_36((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1staging_alert_6, _jspx_page_context))
          return true;
        out.write("\n\t\t\t\t");
        int evalDoAfterBody = _jspx_th_liferay$1staging_alert_6.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_liferay$1staging_alert_6 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
        out = _jspx_page_context.popBody();
    }
    if (_jspx_th_liferay$1staging_alert_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1staging_alert_type.reuse(_jspx_th_liferay$1staging_alert_6);
      return true;
    }
    _jspx_tagPool_liferay$1staging_alert_type.reuse(_jspx_th_liferay$1staging_alert_6);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_36(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1staging_alert_6, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_36 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_36.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_36.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1staging_alert_6);
    _jspx_th_liferay$1ui_message_36.setKey("local-staging-trash-warning");
    int _jspx_eval_liferay$1ui_message_36 = _jspx_th_liferay$1ui_message_36.doStartTag();
    if (_jspx_th_liferay$1ui_message_36.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_36);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_36);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_37(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_5, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_37 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_37.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_37.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_5);
    _jspx_th_liferay$1ui_message_37.setKey("staged-applications-alert");
    int _jspx_eval_liferay$1ui_message_37 = _jspx_th_liferay$1ui_message_37.doStartTag();
    if (_jspx_th_liferay$1ui_message_37.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_37);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_37);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_7(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_7 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_7.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    int _jspx_eval_portlet_namespace_7 = _jspx_th_portlet_namespace_7.doStartTag();
    if (_jspx_th_portlet_namespace_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_7);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_7);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_8(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_8 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_8.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_8.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    int _jspx_eval_portlet_namespace_8 = _jspx_th_portlet_namespace_8.doStartTag();
    if (_jspx_th_portlet_namespace_8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_8);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_8);
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

  private boolean _jspx_meth_c_otherwise_2(javax.servlet.jsp.tagext.JspTag _jspx_th_c_choose_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:otherwise
    com.liferay.taglib.core.OtherwiseTag _jspx_th_c_otherwise_2 = (com.liferay.taglib.core.OtherwiseTag) _jspx_tagPool_c_otherwise.get(com.liferay.taglib.core.OtherwiseTag.class);
    _jspx_th_c_otherwise_2.setPageContext(_jspx_page_context);
    _jspx_th_c_otherwise_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_0);
    int _jspx_eval_c_otherwise_2 = _jspx_th_c_otherwise_2.doStartTag();
    if (_jspx_eval_c_otherwise_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      out.write("\n\t\t");
      if (_jspx_meth_liferay$1staging_alert_7((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_otherwise_2, _jspx_page_context))
        return true;
      out.write('\n');
      out.write('	');
    }
    if (_jspx_th_c_otherwise_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_2);
      return true;
    }
    _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_2);
    return false;
  }

  private boolean _jspx_meth_liferay$1staging_alert_7(javax.servlet.jsp.tagext.JspTag _jspx_th_c_otherwise_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-staging:alert
    com.liferay.staging.taglib.servlet.taglib.AlertTag _jspx_th_liferay$1staging_alert_7 = (com.liferay.staging.taglib.servlet.taglib.AlertTag) _jspx_tagPool_liferay$1staging_alert_type.get(com.liferay.staging.taglib.servlet.taglib.AlertTag.class);
    _jspx_th_liferay$1staging_alert_7.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1staging_alert_7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_2);
    _jspx_th_liferay$1staging_alert_7.setType(Enum.valueOf(com.liferay.staging.taglib.servlet.taglib.AlertType.class, "INFO"));
    int _jspx_eval_liferay$1staging_alert_7 = _jspx_th_liferay$1staging_alert_7.doStartTag();
    if (_jspx_eval_liferay$1staging_alert_7 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_liferay$1staging_alert_7 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_liferay$1staging_alert_7.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_liferay$1staging_alert_7.doInitBody();
      }
      do {
        out.write("\n\t\t\t");
        if (_jspx_meth_liferay$1ui_message_38((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1staging_alert_7, _jspx_page_context))
          return true;
        out.write("\n\t\t");
        int evalDoAfterBody = _jspx_th_liferay$1staging_alert_7.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_liferay$1staging_alert_7 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
        out = _jspx_page_context.popBody();
    }
    if (_jspx_th_liferay$1staging_alert_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1staging_alert_type.reuse(_jspx_th_liferay$1staging_alert_7);
      return true;
    }
    _jspx_tagPool_liferay$1staging_alert_type.reuse(_jspx_th_liferay$1staging_alert_7);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_38(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1staging_alert_7, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_38 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_38.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_38.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1staging_alert_7);
    _jspx_th_liferay$1ui_message_38.setKey("you-do-not-have-permission-to-manage-settings-related-to-staging");
    int _jspx_eval_liferay$1ui_message_38 = _jspx_th_liferay$1ui_message_38.doStartTag();
    if (_jspx_th_liferay$1ui_message_38.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_38);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_38);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_12(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_12 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_12.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_12.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_2);
    int _jspx_eval_portlet_namespace_12 = _jspx_th_portlet_namespace_12.doStartTag();
    if (_jspx_th_portlet_namespace_12.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_12);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_12);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_13(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_13 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_13.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_13.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_2);
    int _jspx_eval_portlet_namespace_13 = _jspx_th_portlet_namespace_13.doStartTag();
    if (_jspx_th_portlet_namespace_13.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_13);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_13);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_14(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_27, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_14 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_14.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_14.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_27);
    int _jspx_eval_portlet_namespace_14 = _jspx_th_portlet_namespace_14.doStartTag();
    if (_jspx_th_portlet_namespace_14.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_14);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_14);
    return false;
  }

  private boolean _jspx_meth_portlet_param_11(javax.servlet.jsp.tagext.JspTag _jspx_th_portlet_renderURL_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:param
    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_11 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
    _jspx_th_portlet_param_11.setPageContext(_jspx_page_context);
    _jspx_th_portlet_param_11.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_1);
    _jspx_th_portlet_param_11.setName("mvcPath");
    _jspx_th_portlet_param_11.setValue("/view.jsp");
    int _jspx_eval_portlet_param_11 = _jspx_th_portlet_param_11.doStartTag();
    if (_jspx_th_portlet_param_11.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_11);
      return true;
    }
    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_11);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_15(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_15 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_15.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_15.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_3);
    int _jspx_eval_portlet_namespace_15 = _jspx_th_portlet_namespace_15.doStartTag();
    if (_jspx_th_portlet_namespace_15.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_15);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_15);
    return false;
  }
}
