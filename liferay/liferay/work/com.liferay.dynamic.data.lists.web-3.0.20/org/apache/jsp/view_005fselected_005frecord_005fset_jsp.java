package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.liferay.document.library.kernel.exception.DuplicateFileEntryException;
import com.liferay.document.library.kernel.exception.FileSizeException;
import com.liferay.document.library.kernel.util.DLValidatorUtil;
import com.liferay.dynamic.data.lists.constants.DDLActionKeys;
import com.liferay.dynamic.data.lists.constants.DDLPortletKeys;
import com.liferay.dynamic.data.lists.constants.DDLWebKeys;
import com.liferay.dynamic.data.lists.exception.NoSuchRecordException;
import com.liferay.dynamic.data.lists.exception.NoSuchRecordSetException;
import com.liferay.dynamic.data.lists.exception.RecordSetDDMStructureIdException;
import com.liferay.dynamic.data.lists.exception.RecordSetNameException;
import com.liferay.dynamic.data.lists.model.DDLRecord;
import com.liferay.dynamic.data.lists.model.DDLRecordConstants;
import com.liferay.dynamic.data.lists.model.DDLRecordSet;
import com.liferay.dynamic.data.lists.model.DDLRecordSetConstants;
import com.liferay.dynamic.data.lists.model.DDLRecordVersion;
import com.liferay.dynamic.data.lists.service.DDLRecordLocalServiceUtil;
import com.liferay.dynamic.data.lists.service.DDLRecordSetServiceUtil;
import com.liferay.dynamic.data.lists.service.DDLRecordVersionServiceUtil;
import com.liferay.dynamic.data.lists.util.comparator.DDLRecordSetCreateDateComparator;
import com.liferay.dynamic.data.lists.util.comparator.DDLRecordSetModifiedDateComparator;
import com.liferay.dynamic.data.lists.util.comparator.DDLRecordSetNameComparator;
import com.liferay.dynamic.data.lists.web.internal.display.context.DDLDisplayContext;
import com.liferay.dynamic.data.lists.web.internal.display.context.DDLViewRecordsDisplayContext;
import com.liferay.dynamic.data.lists.web.internal.security.permission.resource.DDLRecordSetPermission;
import com.liferay.dynamic.data.lists.web.internal.security.permission.resource.DDMTemplatePermission;
import com.liferay.dynamic.data.lists.web.internal.template.DDLDisplayTemplateTransformer;
import com.liferay.dynamic.data.mapping.exception.NoSuchStructureException;
import com.liferay.dynamic.data.mapping.exception.StorageFieldRequiredException;
import com.liferay.dynamic.data.mapping.model.DDMFormField;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.model.DDMTemplate;
import com.liferay.dynamic.data.mapping.model.DDMTemplateConstants;
import com.liferay.dynamic.data.mapping.render.DDMFormFieldValueRenderer;
import com.liferay.dynamic.data.mapping.render.DDMFormFieldValueRendererRegistryUtil;
import com.liferay.dynamic.data.mapping.service.DDMStorageLinkLocalServiceUtil;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalServiceUtil;
import com.liferay.dynamic.data.mapping.service.DDMTemplateLocalServiceUtil;
import com.liferay.dynamic.data.mapping.storage.DDMFormFieldValue;
import com.liferay.dynamic.data.mapping.storage.DDMFormValues;
import com.liferay.dynamic.data.mapping.util.DDMNavigationHelper;
import com.liferay.dynamic.data.mapping.util.DDMUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.bean.BeanParamUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.search.EmptyOnClickRowChecker;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.NoSuchWorkflowDefinitionLinkException;
import com.liferay.portal.kernel.exception.PortletPreferencesException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.language.UnicodeLanguageUtil;
import com.liferay.portal.kernel.model.*;
import com.liferay.portal.kernel.model.impl.*;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletProvider;
import com.liferay.portal.kernel.portlet.PortletProviderUtil;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.*;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PrefsParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.TextFormatter;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.webdav.WebDAVUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.workflow.WorkflowDefinition;
import com.liferay.portal.kernel.workflow.WorkflowDefinitionManagerUtil;
import com.liferay.portal.kernel.workflow.WorkflowEngineManagerUtil;
import com.liferay.portal.kernel.workflow.WorkflowHandlerRegistryUtil;
import com.liferay.portal.model.impl.*;
import com.liferay.portal.service.*;
import com.liferay.taglib.search.DateSearchEntry;
import com.liferay.taglib.search.ResultRow;
import java.text.Format;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.WindowState;

public final class view_005fselected_005frecord_005fset_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(3);
    _jspx_dependants.add("/init.jsp");
    _jspx_dependants.add("/init-ext.jsp");
    _jspx_dependants.add("/view_selected_record_set_options.jspf");
  }

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1util_param_value_name_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_message_key_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_param_value_name_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_if_test;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1portlet_renderURL_windowState_var_portletName;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_otherwise;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1util_include_servletContext_page;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1theme_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_icon_url_message_label_icon_cssClass_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_choose;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1frontend_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_when_test;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_icon_url_onClick_method_message_label_iconCssClass_cssClass_nobody;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_liferay$1util_param_value_name_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_message_key_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_param_value_name_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_if_test = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1portlet_renderURL_windowState_var_portletName = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_otherwise = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1util_include_servletContext_page = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1theme_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_icon_url_message_label_icon_cssClass_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_choose = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1frontend_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_when_test = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_icon_url_onClick_method_message_label_iconCssClass_cssClass_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_liferay$1util_param_value_name_nobody.release();
    _jspx_tagPool_liferay$1ui_message_key_nobody.release();
    _jspx_tagPool_portlet_param_value_name_nobody.release();
    _jspx_tagPool_c_if_test.release();
    _jspx_tagPool_liferay$1portlet_renderURL_windowState_var_portletName.release();
    _jspx_tagPool_c_otherwise.release();
    _jspx_tagPool_portlet_defineObjects_nobody.release();
    _jspx_tagPool_liferay$1util_include_servletContext_page.release();
    _jspx_tagPool_liferay$1theme_defineObjects_nobody.release();
    _jspx_tagPool_liferay$1ui_icon_url_message_label_icon_cssClass_nobody.release();
    _jspx_tagPool_c_choose.release();
    _jspx_tagPool_liferay$1frontend_defineObjects_nobody.release();
    _jspx_tagPool_c_when_test.release();
    _jspx_tagPool_liferay$1ui_icon_url_onClick_method_message_label_iconCssClass_cssClass_nobody.release();
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

DDLDisplayContext ddlDisplayContext = (DDLDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

      out.write('\n');
      out.write('\n');
      out.write('\n');
      out.write('\n');

String redirect = ParamUtil.getString(request, "redirect", currentURL);

DDLRecordSet recordSet = ddlDisplayContext.getRecordSet();

if (recordSet != null) {
	renderResponse.setTitle(recordSet.getName(locale));
}

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
        _jspx_th_c_when_0.setTest( recordSet == null );
        int _jspx_eval_c_when_0 = _jspx_th_c_when_0.doStartTag();
        if (_jspx_eval_c_when_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          out.write("\n\t\t<div class=\"alert alert-info\">\n\t\t\t");
          if (_jspx_meth_liferay$1ui_message_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_when_0, _jspx_page_context))
            return;
          out.write("\n\t\t</div>\n\t");
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
        _jspx_th_c_when_1.setTest( ddlDisplayContext.isFormView() );
        int _jspx_eval_c_when_1 = _jspx_th_c_when_1.doStartTag();
        if (_jspx_eval_c_when_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          out.write("\n\t\t");
          //  liferay-util:include
          com.liferay.taglib.util.IncludeTag _jspx_th_liferay$1util_include_0 = (com.liferay.taglib.util.IncludeTag) _jspx_tagPool_liferay$1util_include_servletContext_page.get(com.liferay.taglib.util.IncludeTag.class);
          _jspx_th_liferay$1util_include_0.setPageContext(_jspx_page_context);
          _jspx_th_liferay$1util_include_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_1);
          _jspx_th_liferay$1util_include_0.setPage("/edit_record.jsp");
          _jspx_th_liferay$1util_include_0.setServletContext( application );
          int _jspx_eval_liferay$1util_include_0 = _jspx_th_liferay$1util_include_0.doStartTag();
          if (_jspx_eval_liferay$1util_include_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t\t");
            //  liferay-util:param
            com.liferay.taglib.util.ParamTag _jspx_th_liferay$1util_param_0 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_liferay$1util_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
            _jspx_th_liferay$1util_param_0.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1util_param_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1util_include_0);
            _jspx_th_liferay$1util_param_0.setName("redirect");
            _jspx_th_liferay$1util_param_0.setValue( currentURL );
            int _jspx_eval_liferay$1util_param_0 = _jspx_th_liferay$1util_param_0.doStartTag();
            if (_jspx_th_liferay$1util_param_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_liferay$1util_param_value_name_nobody.reuse(_jspx_th_liferay$1util_param_0);
              return;
            }
            _jspx_tagPool_liferay$1util_param_value_name_nobody.reuse(_jspx_th_liferay$1util_param_0);
            out.write("\n\t\t\t");
            //  liferay-util:param
            com.liferay.taglib.util.ParamTag _jspx_th_liferay$1util_param_1 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_liferay$1util_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
            _jspx_th_liferay$1util_param_1.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1util_param_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1util_include_0);
            _jspx_th_liferay$1util_param_1.setName("recordSetId");
            _jspx_th_liferay$1util_param_1.setValue( String.valueOf(recordSet.getRecordSetId()) );
            int _jspx_eval_liferay$1util_param_1 = _jspx_th_liferay$1util_param_1.doStartTag();
            if (_jspx_th_liferay$1util_param_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_liferay$1util_param_value_name_nobody.reuse(_jspx_th_liferay$1util_param_1);
              return;
            }
            _jspx_tagPool_liferay$1util_param_value_name_nobody.reuse(_jspx_th_liferay$1util_param_1);
            out.write("\n\t\t\t");
            //  liferay-util:param
            com.liferay.taglib.util.ParamTag _jspx_th_liferay$1util_param_2 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_liferay$1util_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
            _jspx_th_liferay$1util_param_2.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1util_param_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1util_include_0);
            _jspx_th_liferay$1util_param_2.setName("formDDMTemplateId");
            _jspx_th_liferay$1util_param_2.setValue( String.valueOf(ddlDisplayContext.getFormDDMTemplateId()) );
            int _jspx_eval_liferay$1util_param_2 = _jspx_th_liferay$1util_param_2.doStartTag();
            if (_jspx_th_liferay$1util_param_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_liferay$1util_param_value_name_nobody.reuse(_jspx_th_liferay$1util_param_2);
              return;
            }
            _jspx_tagPool_liferay$1util_param_value_name_nobody.reuse(_jspx_th_liferay$1util_param_2);
            out.write("\n\t\t");
          }
          if (_jspx_th_liferay$1util_include_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_liferay$1util_include_servletContext_page.reuse(_jspx_th_liferay$1util_include_0);
            return;
          }
          _jspx_tagPool_liferay$1util_include_servletContext_page.reuse(_jspx_th_liferay$1util_include_0);
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
        //  c:otherwise
        com.liferay.taglib.core.OtherwiseTag _jspx_th_c_otherwise_0 = (com.liferay.taglib.core.OtherwiseTag) _jspx_tagPool_c_otherwise.get(com.liferay.taglib.core.OtherwiseTag.class);
        _jspx_th_c_otherwise_0.setPageContext(_jspx_page_context);
        _jspx_th_c_otherwise_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_0);
        int _jspx_eval_c_otherwise_0 = _jspx_th_c_otherwise_0.doStartTag();
        if (_jspx_eval_c_otherwise_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          out.write("\n\t\t");
          //  liferay-util:include
          com.liferay.taglib.util.IncludeTag _jspx_th_liferay$1util_include_1 = (com.liferay.taglib.util.IncludeTag) _jspx_tagPool_liferay$1util_include_servletContext_page.get(com.liferay.taglib.util.IncludeTag.class);
          _jspx_th_liferay$1util_include_1.setPageContext(_jspx_page_context);
          _jspx_th_liferay$1util_include_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_0);
          _jspx_th_liferay$1util_include_1.setPage("/view_record_set.jsp");
          _jspx_th_liferay$1util_include_1.setServletContext( application );
          int _jspx_eval_liferay$1util_include_1 = _jspx_th_liferay$1util_include_1.doStartTag();
          if (_jspx_eval_liferay$1util_include_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t\t");
            if (_jspx_meth_liferay$1util_param_3((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1util_include_1, _jspx_page_context))
              return;
            out.write("\n\t\t\t");
            //  liferay-util:param
            com.liferay.taglib.util.ParamTag _jspx_th_liferay$1util_param_4 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_liferay$1util_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
            _jspx_th_liferay$1util_param_4.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1util_param_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1util_include_1);
            _jspx_th_liferay$1util_param_4.setName("displayDDMTemplateId");
            _jspx_th_liferay$1util_param_4.setValue( String.valueOf(ddlDisplayContext.getDisplayDDMTemplateId()) );
            int _jspx_eval_liferay$1util_param_4 = _jspx_th_liferay$1util_param_4.doStartTag();
            if (_jspx_th_liferay$1util_param_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_liferay$1util_param_value_name_nobody.reuse(_jspx_th_liferay$1util_param_4);
              return;
            }
            _jspx_tagPool_liferay$1util_param_value_name_nobody.reuse(_jspx_th_liferay$1util_param_4);
            out.write("\n\t\t\t");
            //  liferay-util:param
            com.liferay.taglib.util.ParamTag _jspx_th_liferay$1util_param_5 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_liferay$1util_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
            _jspx_th_liferay$1util_param_5.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1util_param_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1util_include_1);
            _jspx_th_liferay$1util_param_5.setName("formDDMTemplateId");
            _jspx_th_liferay$1util_param_5.setValue( String.valueOf(ddlDisplayContext.getFormDDMTemplateId()) );
            int _jspx_eval_liferay$1util_param_5 = _jspx_th_liferay$1util_param_5.doStartTag();
            if (_jspx_th_liferay$1util_param_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_liferay$1util_param_value_name_nobody.reuse(_jspx_th_liferay$1util_param_5);
              return;
            }
            _jspx_tagPool_liferay$1util_param_value_name_nobody.reuse(_jspx_th_liferay$1util_param_5);
            out.write("\n\t\t\t");
            //  liferay-util:param
            com.liferay.taglib.util.ParamTag _jspx_th_liferay$1util_param_6 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_liferay$1util_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
            _jspx_th_liferay$1util_param_6.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1util_param_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1util_include_1);
            _jspx_th_liferay$1util_param_6.setName("editable");
            _jspx_th_liferay$1util_param_6.setValue( String.valueOf(ddlDisplayContext.isEditable()) );
            int _jspx_eval_liferay$1util_param_6 = _jspx_th_liferay$1util_param_6.doStartTag();
            if (_jspx_th_liferay$1util_param_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_liferay$1util_param_value_name_nobody.reuse(_jspx_th_liferay$1util_param_6);
              return;
            }
            _jspx_tagPool_liferay$1util_param_value_name_nobody.reuse(_jspx_th_liferay$1util_param_6);
            out.write("\n\t\t\t");
            //  liferay-util:param
            com.liferay.taglib.util.ParamTag _jspx_th_liferay$1util_param_7 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_liferay$1util_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
            _jspx_th_liferay$1util_param_7.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1util_param_7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1util_include_1);
            _jspx_th_liferay$1util_param_7.setName("spreadsheet");
            _jspx_th_liferay$1util_param_7.setValue( String.valueOf(ddlDisplayContext.isSpreadsheet()) );
            int _jspx_eval_liferay$1util_param_7 = _jspx_th_liferay$1util_param_7.doStartTag();
            if (_jspx_th_liferay$1util_param_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_liferay$1util_param_value_name_nobody.reuse(_jspx_th_liferay$1util_param_7);
              return;
            }
            _jspx_tagPool_liferay$1util_param_value_name_nobody.reuse(_jspx_th_liferay$1util_param_7);
            out.write("\n\t\t");
          }
          if (_jspx_th_liferay$1util_include_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_liferay$1util_include_servletContext_page.reuse(_jspx_th_liferay$1util_include_1);
            return;
          }
          _jspx_tagPool_liferay$1util_include_servletContext_page.reuse(_jspx_th_liferay$1util_include_1);
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
      out.write('\n');
      out.write('\n');
      out.write("\n\n<div class=\"btn-group icons-container lfr-meta-actions\">\n\t");
      //  c:if
      com.liferay.taglib.core.IfTag _jspx_th_c_if_0 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
      _jspx_th_c_if_0.setPageContext(_jspx_page_context);
      _jspx_th_c_if_0.setParent(null);
      _jspx_th_c_if_0.setTest( ddlDisplayContext.isShowIconsActions() );
      int _jspx_eval_c_if_0 = _jspx_th_c_if_0.doStartTag();
      if (_jspx_eval_c_if_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        out.write("\n\t\t");
        //  c:if
        com.liferay.taglib.core.IfTag _jspx_th_c_if_1 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
        _jspx_th_c_if_1.setPageContext(_jspx_page_context);
        _jspx_th_c_if_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_0);
        _jspx_th_c_if_1.setTest( ddlDisplayContext.isShowConfigurationIcon() );
        int _jspx_eval_c_if_1 = _jspx_th_c_if_1.doStartTag();
        if (_jspx_eval_c_if_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          out.write("\n\t\t\t<div class=\"btn-group-item\">\n\t\t\t\t");
          //  liferay-ui:icon
          com.liferay.taglib.ui.IconTag _jspx_th_liferay$1ui_icon_0 = (com.liferay.taglib.ui.IconTag) _jspx_tagPool_liferay$1ui_icon_url_onClick_method_message_label_iconCssClass_cssClass_nobody.get(com.liferay.taglib.ui.IconTag.class);
          _jspx_th_liferay$1ui_icon_0.setPageContext(_jspx_page_context);
          _jspx_th_liferay$1ui_icon_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_1);
          _jspx_th_liferay$1ui_icon_0.setCssClass("btn btn-link");
          _jspx_th_liferay$1ui_icon_0.setIconCssClass("icon-cog");
          _jspx_th_liferay$1ui_icon_0.setLabel( true );
          _jspx_th_liferay$1ui_icon_0.setMessage("select-list");
          _jspx_th_liferay$1ui_icon_0.setMethod("get");
          _jspx_th_liferay$1ui_icon_0.setOnClick( portletDisplay.getURLConfigurationJS() );
          _jspx_th_liferay$1ui_icon_0.setUrl( portletDisplay.getURLConfiguration() );
          int _jspx_eval_liferay$1ui_icon_0 = _jspx_th_liferay$1ui_icon_0.doStartTag();
          if (_jspx_th_liferay$1ui_icon_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_liferay$1ui_icon_url_onClick_method_message_label_iconCssClass_cssClass_nobody.reuse(_jspx_th_liferay$1ui_icon_0);
            return;
          }
          _jspx_tagPool_liferay$1ui_icon_url_onClick_method_message_label_iconCssClass_cssClass_nobody.reuse(_jspx_th_liferay$1ui_icon_0);
          out.write("\n\t\t\t</div>\n\t\t");
        }
        if (_jspx_th_c_if_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_1);
          return;
        }
        _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_1);
        out.write("\n\n\t\t");
        //  c:if
        com.liferay.taglib.core.IfTag _jspx_th_c_if_2 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
        _jspx_th_c_if_2.setPageContext(_jspx_page_context);
        _jspx_th_c_if_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_0);
        _jspx_th_c_if_2.setTest( ddlDisplayContext.isShowAddRecordSetIcon() );
        int _jspx_eval_c_if_2 = _jspx_th_c_if_2.doStartTag();
        if (_jspx_eval_c_if_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          out.write("\n\t\t\t");
          //  liferay-portlet:renderURL
          com.liferay.taglib.portlet.RenderURLTag _jspx_th_liferay$1portlet_renderURL_0 = (com.liferay.taglib.portlet.RenderURLTag) _jspx_tagPool_liferay$1portlet_renderURL_windowState_var_portletName.get(com.liferay.taglib.portlet.RenderURLTag.class);
          _jspx_th_liferay$1portlet_renderURL_0.setPageContext(_jspx_page_context);
          _jspx_th_liferay$1portlet_renderURL_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_2);
          _jspx_th_liferay$1portlet_renderURL_0.setPortletName( DDLPortletKeys.DYNAMIC_DATA_LISTS );
          _jspx_th_liferay$1portlet_renderURL_0.setVar("addRecordSetURL");
          _jspx_th_liferay$1portlet_renderURL_0.setWindowState( LiferayWindowState.POP_UP.toString() );
          int _jspx_eval_liferay$1portlet_renderURL_0 = _jspx_th_liferay$1portlet_renderURL_0.doStartTag();
          if (_jspx_eval_liferay$1portlet_renderURL_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t\t\t");
            //  portlet:param
            com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_0 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
            _jspx_th_portlet_param_0.setPageContext(_jspx_page_context);
            _jspx_th_portlet_param_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_0);
            _jspx_th_portlet_param_0.setName("hideDefaultSuccessMessage");
            _jspx_th_portlet_param_0.setValue( Boolean.TRUE.toString() );
            int _jspx_eval_portlet_param_0 = _jspx_th_portlet_param_0.doStartTag();
            if (_jspx_th_portlet_param_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_0);
              return;
            }
            _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_0);
            out.write("\n\t\t\t\t");
            if (_jspx_meth_portlet_param_1((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1portlet_renderURL_0, _jspx_page_context))
              return;
            out.write("\n\t\t\t\t");
            //  portlet:param
            com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_2 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
            _jspx_th_portlet_param_2.setPageContext(_jspx_page_context);
            _jspx_th_portlet_param_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_0);
            _jspx_th_portlet_param_2.setName("closeRedirect");
            _jspx_th_portlet_param_2.setValue( currentURL );
            int _jspx_eval_portlet_param_2 = _jspx_th_portlet_param_2.doStartTag();
            if (_jspx_th_portlet_param_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_2);
              return;
            }
            _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_2);
            out.write("\n\t\t\t\t");
            //  portlet:param
            com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_3 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
            _jspx_th_portlet_param_3.setPageContext(_jspx_page_context);
            _jspx_th_portlet_param_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_0);
            _jspx_th_portlet_param_3.setName("portletResource");
            _jspx_th_portlet_param_3.setValue( portletDisplay.getId() );
            int _jspx_eval_portlet_param_3 = _jspx_th_portlet_param_3.doStartTag();
            if (_jspx_th_portlet_param_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_3);
              return;
            }
            _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_3);
            out.write("\n\t\t\t");
          }
          if (_jspx_th_liferay$1portlet_renderURL_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_liferay$1portlet_renderURL_windowState_var_portletName.reuse(_jspx_th_liferay$1portlet_renderURL_0);
            return;
          }
          _jspx_tagPool_liferay$1portlet_renderURL_windowState_var_portletName.reuse(_jspx_th_liferay$1portlet_renderURL_0);
          java.lang.String addRecordSetURL = null;
          addRecordSetURL = (java.lang.String) _jspx_page_context.findAttribute("addRecordSetURL");
          out.write("\n\n\t\t\t");

			String taglibAddRecordSetURL = "javascript:Liferay.Util.openWindow({dialog: {destroyOnHide: true}, id: '_" + HtmlUtil.escapeJS(portletDisplay.getId()) + "_editAsset', title: '" + LanguageUtil.get(request, "add-list") + "', uri:'" + HtmlUtil.escapeJS(addRecordSetURL.toString()) + "'});";
			
          out.write("\n\n\t\t\t<div class=\"btn-group-item\">\n\t\t\t\t");
          //  liferay-ui:icon
          com.liferay.taglib.ui.IconTag _jspx_th_liferay$1ui_icon_1 = (com.liferay.taglib.ui.IconTag) _jspx_tagPool_liferay$1ui_icon_url_message_label_icon_cssClass_nobody.get(com.liferay.taglib.ui.IconTag.class);
          _jspx_th_liferay$1ui_icon_1.setPageContext(_jspx_page_context);
          _jspx_th_liferay$1ui_icon_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_2);
          _jspx_th_liferay$1ui_icon_1.setCssClass("btn btn-link");
          _jspx_th_liferay$1ui_icon_1.setIcon("plus");
          _jspx_th_liferay$1ui_icon_1.setLabel( true );
          _jspx_th_liferay$1ui_icon_1.setMessage("add-list");
          _jspx_th_liferay$1ui_icon_1.setUrl( taglibAddRecordSetURL );
          int _jspx_eval_liferay$1ui_icon_1 = _jspx_th_liferay$1ui_icon_1.doStartTag();
          if (_jspx_th_liferay$1ui_icon_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_liferay$1ui_icon_url_message_label_icon_cssClass_nobody.reuse(_jspx_th_liferay$1ui_icon_1);
            return;
          }
          _jspx_tagPool_liferay$1ui_icon_url_message_label_icon_cssClass_nobody.reuse(_jspx_th_liferay$1ui_icon_1);
          out.write("\n\t\t\t</div>\n\t\t");
        }
        if (_jspx_th_c_if_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_2);
          return;
        }
        _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_2);
        out.write("\n\n\t\t");
        //  c:if
        com.liferay.taglib.core.IfTag _jspx_th_c_if_3 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
        _jspx_th_c_if_3.setPageContext(_jspx_page_context);
        _jspx_th_c_if_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_0);
        _jspx_th_c_if_3.setTest( ddlDisplayContext.isShowEditRecordSetIcon() );
        int _jspx_eval_c_if_3 = _jspx_th_c_if_3.doStartTag();
        if (_jspx_eval_c_if_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          out.write("\n\t\t\t");
          //  liferay-portlet:renderURL
          com.liferay.taglib.portlet.RenderURLTag _jspx_th_liferay$1portlet_renderURL_1 = (com.liferay.taglib.portlet.RenderURLTag) _jspx_tagPool_liferay$1portlet_renderURL_windowState_var_portletName.get(com.liferay.taglib.portlet.RenderURLTag.class);
          _jspx_th_liferay$1portlet_renderURL_1.setPageContext(_jspx_page_context);
          _jspx_th_liferay$1portlet_renderURL_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_3);
          _jspx_th_liferay$1portlet_renderURL_1.setPortletName( DDLPortletKeys.DYNAMIC_DATA_LISTS );
          _jspx_th_liferay$1portlet_renderURL_1.setVar("editRecordSetURL");
          _jspx_th_liferay$1portlet_renderURL_1.setWindowState( LiferayWindowState.POP_UP.toString() );
          int _jspx_eval_liferay$1portlet_renderURL_1 = _jspx_th_liferay$1portlet_renderURL_1.doStartTag();
          if (_jspx_eval_liferay$1portlet_renderURL_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t\t\t");
            //  portlet:param
            com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_4 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
            _jspx_th_portlet_param_4.setPageContext(_jspx_page_context);
            _jspx_th_portlet_param_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_1);
            _jspx_th_portlet_param_4.setName("hideDefaultSuccessMessage");
            _jspx_th_portlet_param_4.setValue( Boolean.TRUE.toString() );
            int _jspx_eval_portlet_param_4 = _jspx_th_portlet_param_4.doStartTag();
            if (_jspx_th_portlet_param_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_4);
              return;
            }
            _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_4);
            out.write("\n\t\t\t\t");
            if (_jspx_meth_portlet_param_5((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1portlet_renderURL_1, _jspx_page_context))
              return;
            out.write("\n\t\t\t\t");
            //  portlet:param
            com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_6 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
            _jspx_th_portlet_param_6.setPageContext(_jspx_page_context);
            _jspx_th_portlet_param_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_1);
            _jspx_th_portlet_param_6.setName("closeRedirect");
            _jspx_th_portlet_param_6.setValue( currentURL );
            int _jspx_eval_portlet_param_6 = _jspx_th_portlet_param_6.doStartTag();
            if (_jspx_th_portlet_param_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_6);
              return;
            }
            _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_6);
            out.write("\n\t\t\t\t");
            //  portlet:param
            com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_7 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
            _jspx_th_portlet_param_7.setPageContext(_jspx_page_context);
            _jspx_th_portlet_param_7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_1);
            _jspx_th_portlet_param_7.setName("portletResource");
            _jspx_th_portlet_param_7.setValue( portletDisplay.getId() );
            int _jspx_eval_portlet_param_7 = _jspx_th_portlet_param_7.doStartTag();
            if (_jspx_th_portlet_param_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_7);
              return;
            }
            _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_7);
            out.write("\n\t\t\t\t");
            //  portlet:param
            com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_8 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
            _jspx_th_portlet_param_8.setPageContext(_jspx_page_context);
            _jspx_th_portlet_param_8.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_1);
            _jspx_th_portlet_param_8.setName("formDDMTemplateId");
            _jspx_th_portlet_param_8.setValue( String.valueOf(ddlDisplayContext.getFormDDMTemplateId()) );
            int _jspx_eval_portlet_param_8 = _jspx_th_portlet_param_8.doStartTag();
            if (_jspx_th_portlet_param_8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_8);
              return;
            }
            _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_8);
            out.write("\n\t\t\t\t");
            //  portlet:param
            com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_9 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
            _jspx_th_portlet_param_9.setPageContext(_jspx_page_context);
            _jspx_th_portlet_param_9.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_1);
            _jspx_th_portlet_param_9.setName("recordSetId");
            _jspx_th_portlet_param_9.setValue( String.valueOf(ddlDisplayContext.getRecordSetId()) );
            int _jspx_eval_portlet_param_9 = _jspx_th_portlet_param_9.doStartTag();
            if (_jspx_th_portlet_param_9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_9);
              return;
            }
            _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_9);
            out.write("\n\t\t\t");
          }
          if (_jspx_th_liferay$1portlet_renderURL_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_liferay$1portlet_renderURL_windowState_var_portletName.reuse(_jspx_th_liferay$1portlet_renderURL_1);
            return;
          }
          _jspx_tagPool_liferay$1portlet_renderURL_windowState_var_portletName.reuse(_jspx_th_liferay$1portlet_renderURL_1);
          java.lang.String editRecordSetURL = null;
          editRecordSetURL = (java.lang.String) _jspx_page_context.findAttribute("editRecordSetURL");
          out.write("\n\n\t\t\t");

			String taglibEditRecordSetURL = "javascript:Liferay.Util.openWindow({id: '_" + HtmlUtil.escapeJS(portletDisplay.getId()) + "_editAsset', title: '" + LanguageUtil.get(request, "edit-list") + "', uri:'" + HtmlUtil.escapeJS(editRecordSetURL.toString()) + "'});";
			
          out.write("\n\n\t\t\t<div class=\"btn-group-item\">\n\t\t\t\t");
          //  liferay-ui:icon
          com.liferay.taglib.ui.IconTag _jspx_th_liferay$1ui_icon_2 = (com.liferay.taglib.ui.IconTag) _jspx_tagPool_liferay$1ui_icon_url_message_label_icon_cssClass_nobody.get(com.liferay.taglib.ui.IconTag.class);
          _jspx_th_liferay$1ui_icon_2.setPageContext(_jspx_page_context);
          _jspx_th_liferay$1ui_icon_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_3);
          _jspx_th_liferay$1ui_icon_2.setCssClass("btn btn-link");
          _jspx_th_liferay$1ui_icon_2.setIcon("pencil");
          _jspx_th_liferay$1ui_icon_2.setLabel( true );
          _jspx_th_liferay$1ui_icon_2.setMessage("edit-list");
          _jspx_th_liferay$1ui_icon_2.setUrl( taglibEditRecordSetURL );
          int _jspx_eval_liferay$1ui_icon_2 = _jspx_th_liferay$1ui_icon_2.doStartTag();
          if (_jspx_th_liferay$1ui_icon_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_liferay$1ui_icon_url_message_label_icon_cssClass_nobody.reuse(_jspx_th_liferay$1ui_icon_2);
            return;
          }
          _jspx_tagPool_liferay$1ui_icon_url_message_label_icon_cssClass_nobody.reuse(_jspx_th_liferay$1ui_icon_2);
          out.write("\n\t\t\t</div>\n\t\t");
        }
        if (_jspx_th_c_if_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_3);
          return;
        }
        _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_3);
        out.write("\n\n\t\t");
        //  c:if
        com.liferay.taglib.core.IfTag _jspx_th_c_if_4 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
        _jspx_th_c_if_4.setPageContext(_jspx_page_context);
        _jspx_th_c_if_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_0);
        _jspx_th_c_if_4.setTest( ddlDisplayContext.isShowAddDDMFormTemplateIcon() );
        int _jspx_eval_c_if_4 = _jspx_th_c_if_4.doStartTag();
        if (_jspx_eval_c_if_4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          out.write("\n\t\t\t");
          //  liferay-portlet:renderURL
          com.liferay.taglib.portlet.RenderURLTag _jspx_th_liferay$1portlet_renderURL_2 = (com.liferay.taglib.portlet.RenderURLTag) _jspx_tagPool_liferay$1portlet_renderURL_windowState_var_portletName.get(com.liferay.taglib.portlet.RenderURLTag.class);
          _jspx_th_liferay$1portlet_renderURL_2.setPageContext(_jspx_page_context);
          _jspx_th_liferay$1portlet_renderURL_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_4);
          _jspx_th_liferay$1portlet_renderURL_2.setPortletName( PortletProviderUtil.getPortletId(DDMTemplate.class.getName(), PortletProvider.Action.EDIT) );
          _jspx_th_liferay$1portlet_renderURL_2.setVar("addFormTemplateURL");
          _jspx_th_liferay$1portlet_renderURL_2.setWindowState( LiferayWindowState.POP_UP.toString() );
          int _jspx_eval_liferay$1portlet_renderURL_2 = _jspx_th_liferay$1portlet_renderURL_2.doStartTag();
          if (_jspx_eval_liferay$1portlet_renderURL_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t\t\t");
            if (_jspx_meth_portlet_param_10((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1portlet_renderURL_2, _jspx_page_context))
              return;
            out.write("\n\t\t\t\t");
            //  portlet:param
            com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_11 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
            _jspx_th_portlet_param_11.setPageContext(_jspx_page_context);
            _jspx_th_portlet_param_11.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_2);
            _jspx_th_portlet_param_11.setName("navigationStartsOn");
            _jspx_th_portlet_param_11.setValue( DDMNavigationHelper.EDIT_TEMPLATE );
            int _jspx_eval_portlet_param_11 = _jspx_th_portlet_param_11.doStartTag();
            if (_jspx_th_portlet_param_11.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_11);
              return;
            }
            _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_11);
            out.write("\n\t\t\t\t");
            //  portlet:param
            com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_12 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
            _jspx_th_portlet_param_12.setPageContext(_jspx_page_context);
            _jspx_th_portlet_param_12.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_2);
            _jspx_th_portlet_param_12.setName("closeRedirect");
            _jspx_th_portlet_param_12.setValue( currentURL );
            int _jspx_eval_portlet_param_12 = _jspx_th_portlet_param_12.doStartTag();
            if (_jspx_th_portlet_param_12.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_12);
              return;
            }
            _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_12);
            out.write("\n\t\t\t\t");
            //  portlet:param
            com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_13 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
            _jspx_th_portlet_param_13.setPageContext(_jspx_page_context);
            _jspx_th_portlet_param_13.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_2);
            _jspx_th_portlet_param_13.setName("showBackURL");
            _jspx_th_portlet_param_13.setValue( Boolean.FALSE.toString() );
            int _jspx_eval_portlet_param_13 = _jspx_th_portlet_param_13.doStartTag();
            if (_jspx_th_portlet_param_13.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_13);
              return;
            }
            _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_13);
            out.write("\n\t\t\t\t");
            //  portlet:param
            com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_14 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
            _jspx_th_portlet_param_14.setPageContext(_jspx_page_context);
            _jspx_th_portlet_param_14.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_2);
            _jspx_th_portlet_param_14.setName("showHeader");
            _jspx_th_portlet_param_14.setValue( Boolean.FALSE.toString() );
            int _jspx_eval_portlet_param_14 = _jspx_th_portlet_param_14.doStartTag();
            if (_jspx_th_portlet_param_14.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_14);
              return;
            }
            _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_14);
            out.write("\n\t\t\t\t");
            //  portlet:param
            com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_15 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
            _jspx_th_portlet_param_15.setPageContext(_jspx_page_context);
            _jspx_th_portlet_param_15.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_2);
            _jspx_th_portlet_param_15.setName("portletResource");
            _jspx_th_portlet_param_15.setValue( portletDisplay.getId() );
            int _jspx_eval_portlet_param_15 = _jspx_th_portlet_param_15.doStartTag();
            if (_jspx_th_portlet_param_15.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_15);
              return;
            }
            _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_15);
            out.write("\n\t\t\t\t");
            //  portlet:param
            com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_16 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
            _jspx_th_portlet_param_16.setPageContext(_jspx_page_context);
            _jspx_th_portlet_param_16.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_2);
            _jspx_th_portlet_param_16.setName("portletResourceNamespace");
            _jspx_th_portlet_param_16.setValue( renderResponse.getNamespace() );
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
            _jspx_th_portlet_param_17.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_2);
            _jspx_th_portlet_param_17.setName("refererPortletName");
            _jspx_th_portlet_param_17.setValue( portletName );
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
            _jspx_th_portlet_param_18.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_2);
            _jspx_th_portlet_param_18.setName("groupId");
            _jspx_th_portlet_param_18.setValue( String.valueOf(scopeGroupId) );
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
            _jspx_th_portlet_param_19.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_2);
            _jspx_th_portlet_param_19.setName("classNameId");
            _jspx_th_portlet_param_19.setValue( String.valueOf(PortalUtil.getClassNameId(DDMStructure.class)) );
            int _jspx_eval_portlet_param_19 = _jspx_th_portlet_param_19.doStartTag();
            if (_jspx_th_portlet_param_19.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_19);
              return;
            }
            _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_19);
            out.write("\n\t\t\t\t");
            //  portlet:param
            com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_20 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
            _jspx_th_portlet_param_20.setPageContext(_jspx_page_context);
            _jspx_th_portlet_param_20.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_2);
            _jspx_th_portlet_param_20.setName("classPK");
            _jspx_th_portlet_param_20.setValue( String.valueOf(recordSet.getDDMStructureId()) );
            int _jspx_eval_portlet_param_20 = _jspx_th_portlet_param_20.doStartTag();
            if (_jspx_th_portlet_param_20.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_20);
              return;
            }
            _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_20);
            out.write("\n\t\t\t\t");
            //  portlet:param
            com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_21 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
            _jspx_th_portlet_param_21.setPageContext(_jspx_page_context);
            _jspx_th_portlet_param_21.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_2);
            _jspx_th_portlet_param_21.setName("resourceClassNameId");
            _jspx_th_portlet_param_21.setValue( String.valueOf(PortalUtil.getClassNameId(DDLRecordSet.class)) );
            int _jspx_eval_portlet_param_21 = _jspx_th_portlet_param_21.doStartTag();
            if (_jspx_th_portlet_param_21.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_21);
              return;
            }
            _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_21);
            out.write("\n\t\t\t\t");
            //  portlet:param
            com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_22 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
            _jspx_th_portlet_param_22.setPageContext(_jspx_page_context);
            _jspx_th_portlet_param_22.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_2);
            _jspx_th_portlet_param_22.setName("structureAvailableFields");
            _jspx_th_portlet_param_22.setValue( renderResponse.getNamespace() + "getAvailableFields" );
            int _jspx_eval_portlet_param_22 = _jspx_th_portlet_param_22.doStartTag();
            if (_jspx_th_portlet_param_22.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_22);
              return;
            }
            _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_22);
            out.write("\n\t\t\t");
          }
          if (_jspx_th_liferay$1portlet_renderURL_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_liferay$1portlet_renderURL_windowState_var_portletName.reuse(_jspx_th_liferay$1portlet_renderURL_2);
            return;
          }
          _jspx_tagPool_liferay$1portlet_renderURL_windowState_var_portletName.reuse(_jspx_th_liferay$1portlet_renderURL_2);
          java.lang.String addFormTemplateURL = null;
          addFormTemplateURL = (java.lang.String) _jspx_page_context.findAttribute("addFormTemplateURL");
          out.write("\n\n\t\t\t");

			String taglibAddFormTemplateURL = "javascript:Liferay.Util.openWindow({id: '_" + HtmlUtil.escapeJS(portletDisplay.getId()) + "_editAsset', title: '" + HtmlUtil.escapeJS(HtmlUtil.escape(ddlDisplayContext.getAddDDMTemplateTitle())) + "', uri:'" + HtmlUtil.escapeJS(addFormTemplateURL.toString()) + "'});";
			
          out.write("\n\n\t\t\t<div class=\"btn-group-item\">\n\t\t\t\t");
          //  liferay-ui:icon
          com.liferay.taglib.ui.IconTag _jspx_th_liferay$1ui_icon_3 = (com.liferay.taglib.ui.IconTag) _jspx_tagPool_liferay$1ui_icon_url_message_label_icon_cssClass_nobody.get(com.liferay.taglib.ui.IconTag.class);
          _jspx_th_liferay$1ui_icon_3.setPageContext(_jspx_page_context);
          _jspx_th_liferay$1ui_icon_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_4);
          _jspx_th_liferay$1ui_icon_3.setCssClass("btn btn-link");
          _jspx_th_liferay$1ui_icon_3.setIcon("plus");
          _jspx_th_liferay$1ui_icon_3.setLabel( true );
          _jspx_th_liferay$1ui_icon_3.setMessage("add-form-template");
          _jspx_th_liferay$1ui_icon_3.setUrl( taglibAddFormTemplateURL );
          int _jspx_eval_liferay$1ui_icon_3 = _jspx_th_liferay$1ui_icon_3.doStartTag();
          if (_jspx_th_liferay$1ui_icon_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_liferay$1ui_icon_url_message_label_icon_cssClass_nobody.reuse(_jspx_th_liferay$1ui_icon_3);
            return;
          }
          _jspx_tagPool_liferay$1ui_icon_url_message_label_icon_cssClass_nobody.reuse(_jspx_th_liferay$1ui_icon_3);
          out.write("\n\t\t\t</div>\n\t\t");
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
        _jspx_th_c_if_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_0);
        _jspx_th_c_if_5.setTest( ddlDisplayContext.isShowAddDDMDisplayTemplateIcon() );
        int _jspx_eval_c_if_5 = _jspx_th_c_if_5.doStartTag();
        if (_jspx_eval_c_if_5 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          out.write("\n\t\t\t");
          //  liferay-portlet:renderURL
          com.liferay.taglib.portlet.RenderURLTag _jspx_th_liferay$1portlet_renderURL_3 = (com.liferay.taglib.portlet.RenderURLTag) _jspx_tagPool_liferay$1portlet_renderURL_windowState_var_portletName.get(com.liferay.taglib.portlet.RenderURLTag.class);
          _jspx_th_liferay$1portlet_renderURL_3.setPageContext(_jspx_page_context);
          _jspx_th_liferay$1portlet_renderURL_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_5);
          _jspx_th_liferay$1portlet_renderURL_3.setPortletName( PortletProviderUtil.getPortletId(DDMTemplate.class.getName(), PortletProvider.Action.EDIT) );
          _jspx_th_liferay$1portlet_renderURL_3.setVar("addDisplayTemplateURL");
          _jspx_th_liferay$1portlet_renderURL_3.setWindowState( LiferayWindowState.POP_UP.toString() );
          int _jspx_eval_liferay$1portlet_renderURL_3 = _jspx_th_liferay$1portlet_renderURL_3.doStartTag();
          if (_jspx_eval_liferay$1portlet_renderURL_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t\t\t");
            if (_jspx_meth_portlet_param_23((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1portlet_renderURL_3, _jspx_page_context))
              return;
            out.write("\n\t\t\t\t");
            //  portlet:param
            com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_24 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
            _jspx_th_portlet_param_24.setPageContext(_jspx_page_context);
            _jspx_th_portlet_param_24.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_3);
            _jspx_th_portlet_param_24.setName("navigationStartsOn");
            _jspx_th_portlet_param_24.setValue( DDMNavigationHelper.EDIT_TEMPLATE );
            int _jspx_eval_portlet_param_24 = _jspx_th_portlet_param_24.doStartTag();
            if (_jspx_th_portlet_param_24.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_24);
              return;
            }
            _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_24);
            out.write("\n\t\t\t\t");
            //  portlet:param
            com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_25 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
            _jspx_th_portlet_param_25.setPageContext(_jspx_page_context);
            _jspx_th_portlet_param_25.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_3);
            _jspx_th_portlet_param_25.setName("closeRedirect");
            _jspx_th_portlet_param_25.setValue( currentURL );
            int _jspx_eval_portlet_param_25 = _jspx_th_portlet_param_25.doStartTag();
            if (_jspx_th_portlet_param_25.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_25);
              return;
            }
            _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_25);
            out.write("\n\t\t\t\t");
            //  portlet:param
            com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_26 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
            _jspx_th_portlet_param_26.setPageContext(_jspx_page_context);
            _jspx_th_portlet_param_26.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_3);
            _jspx_th_portlet_param_26.setName("showBackURL");
            _jspx_th_portlet_param_26.setValue( Boolean.FALSE.toString() );
            int _jspx_eval_portlet_param_26 = _jspx_th_portlet_param_26.doStartTag();
            if (_jspx_th_portlet_param_26.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_26);
              return;
            }
            _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_26);
            out.write("\n\t\t\t\t");
            //  portlet:param
            com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_27 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
            _jspx_th_portlet_param_27.setPageContext(_jspx_page_context);
            _jspx_th_portlet_param_27.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_3);
            _jspx_th_portlet_param_27.setName("showHeader");
            _jspx_th_portlet_param_27.setValue( Boolean.FALSE.toString() );
            int _jspx_eval_portlet_param_27 = _jspx_th_portlet_param_27.doStartTag();
            if (_jspx_th_portlet_param_27.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_27);
              return;
            }
            _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_27);
            out.write("\n\t\t\t\t");
            //  portlet:param
            com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_28 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
            _jspx_th_portlet_param_28.setPageContext(_jspx_page_context);
            _jspx_th_portlet_param_28.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_3);
            _jspx_th_portlet_param_28.setName("portletResource");
            _jspx_th_portlet_param_28.setValue( portletDisplay.getId() );
            int _jspx_eval_portlet_param_28 = _jspx_th_portlet_param_28.doStartTag();
            if (_jspx_th_portlet_param_28.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_28);
              return;
            }
            _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_28);
            out.write("\n\t\t\t\t");
            //  portlet:param
            com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_29 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
            _jspx_th_portlet_param_29.setPageContext(_jspx_page_context);
            _jspx_th_portlet_param_29.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_3);
            _jspx_th_portlet_param_29.setName("refererPortletName");
            _jspx_th_portlet_param_29.setValue( portletName );
            int _jspx_eval_portlet_param_29 = _jspx_th_portlet_param_29.doStartTag();
            if (_jspx_th_portlet_param_29.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_29);
              return;
            }
            _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_29);
            out.write("\n\t\t\t\t");
            //  portlet:param
            com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_30 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
            _jspx_th_portlet_param_30.setPageContext(_jspx_page_context);
            _jspx_th_portlet_param_30.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_3);
            _jspx_th_portlet_param_30.setName("groupId");
            _jspx_th_portlet_param_30.setValue( String.valueOf(scopeGroupId) );
            int _jspx_eval_portlet_param_30 = _jspx_th_portlet_param_30.doStartTag();
            if (_jspx_th_portlet_param_30.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_30);
              return;
            }
            _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_30);
            out.write("\n\t\t\t\t");
            //  portlet:param
            com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_31 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
            _jspx_th_portlet_param_31.setPageContext(_jspx_page_context);
            _jspx_th_portlet_param_31.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_3);
            _jspx_th_portlet_param_31.setName("classNameId");
            _jspx_th_portlet_param_31.setValue( String.valueOf(PortalUtil.getClassNameId(DDMStructure.class)) );
            int _jspx_eval_portlet_param_31 = _jspx_th_portlet_param_31.doStartTag();
            if (_jspx_th_portlet_param_31.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_31);
              return;
            }
            _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_31);
            out.write("\n\t\t\t\t");
            //  portlet:param
            com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_32 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
            _jspx_th_portlet_param_32.setPageContext(_jspx_page_context);
            _jspx_th_portlet_param_32.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_3);
            _jspx_th_portlet_param_32.setName("classPK");
            _jspx_th_portlet_param_32.setValue( String.valueOf(recordSet.getDDMStructureId()) );
            int _jspx_eval_portlet_param_32 = _jspx_th_portlet_param_32.doStartTag();
            if (_jspx_th_portlet_param_32.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_32);
              return;
            }
            _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_32);
            out.write("\n\t\t\t\t");
            //  portlet:param
            com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_33 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
            _jspx_th_portlet_param_33.setPageContext(_jspx_page_context);
            _jspx_th_portlet_param_33.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_3);
            _jspx_th_portlet_param_33.setName("resourceClassNameId");
            _jspx_th_portlet_param_33.setValue( String.valueOf(PortalUtil.getClassNameId(DDLRecordSet.class)) );
            int _jspx_eval_portlet_param_33 = _jspx_th_portlet_param_33.doStartTag();
            if (_jspx_th_portlet_param_33.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_33);
              return;
            }
            _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_33);
            out.write("\n\t\t\t\t");
            //  portlet:param
            com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_34 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
            _jspx_th_portlet_param_34.setPageContext(_jspx_page_context);
            _jspx_th_portlet_param_34.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_3);
            _jspx_th_portlet_param_34.setName("type");
            _jspx_th_portlet_param_34.setValue( DDMTemplateConstants.TEMPLATE_TYPE_DISPLAY );
            int _jspx_eval_portlet_param_34 = _jspx_th_portlet_param_34.doStartTag();
            if (_jspx_th_portlet_param_34.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_34);
              return;
            }
            _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_34);
            out.write("\n\t\t\t");
          }
          if (_jspx_th_liferay$1portlet_renderURL_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_liferay$1portlet_renderURL_windowState_var_portletName.reuse(_jspx_th_liferay$1portlet_renderURL_3);
            return;
          }
          _jspx_tagPool_liferay$1portlet_renderURL_windowState_var_portletName.reuse(_jspx_th_liferay$1portlet_renderURL_3);
          java.lang.String addDisplayTemplateURL = null;
          addDisplayTemplateURL = (java.lang.String) _jspx_page_context.findAttribute("addDisplayTemplateURL");
          out.write("\n\n\t\t\t");

			String taglibAddDisplayTemplateURL = "javascript:Liferay.Util.openWindow({id: '_" + HtmlUtil.escapeJS(portletDisplay.getId()) + "_editAsset', title: '" + HtmlUtil.escapeJS(HtmlUtil.escape(ddlDisplayContext.getAddDDMTemplateTitle())) + "', uri:'" + HtmlUtil.escapeJS(addDisplayTemplateURL.toString()) + "'});";
			
          out.write("\n\n\t\t\t<div class=\"btn-group-item\">\n\t\t\t\t");
          //  liferay-ui:icon
          com.liferay.taglib.ui.IconTag _jspx_th_liferay$1ui_icon_4 = (com.liferay.taglib.ui.IconTag) _jspx_tagPool_liferay$1ui_icon_url_message_label_icon_cssClass_nobody.get(com.liferay.taglib.ui.IconTag.class);
          _jspx_th_liferay$1ui_icon_4.setPageContext(_jspx_page_context);
          _jspx_th_liferay$1ui_icon_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_5);
          _jspx_th_liferay$1ui_icon_4.setCssClass("lfr-action lfr-icon-action-configuration");
          _jspx_th_liferay$1ui_icon_4.setIcon("plus");
          _jspx_th_liferay$1ui_icon_4.setLabel( true );
          _jspx_th_liferay$1ui_icon_4.setMessage("add-display-template");
          _jspx_th_liferay$1ui_icon_4.setUrl( taglibAddDisplayTemplateURL );
          int _jspx_eval_liferay$1ui_icon_4 = _jspx_th_liferay$1ui_icon_4.doStartTag();
          if (_jspx_th_liferay$1ui_icon_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_liferay$1ui_icon_url_message_label_icon_cssClass_nobody.reuse(_jspx_th_liferay$1ui_icon_4);
            return;
          }
          _jspx_tagPool_liferay$1ui_icon_url_message_label_icon_cssClass_nobody.reuse(_jspx_th_liferay$1ui_icon_4);
          out.write("\n\t\t\t</div>\n\t\t");
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
        _jspx_th_c_if_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_0);
        _jspx_th_c_if_6.setTest( ddlDisplayContext.isShowEditDisplayDDMTemplateIcon() );
        int _jspx_eval_c_if_6 = _jspx_th_c_if_6.doStartTag();
        if (_jspx_eval_c_if_6 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          out.write("\n\t\t\t");
          //  liferay-portlet:renderURL
          com.liferay.taglib.portlet.RenderURLTag _jspx_th_liferay$1portlet_renderURL_4 = (com.liferay.taglib.portlet.RenderURLTag) _jspx_tagPool_liferay$1portlet_renderURL_windowState_var_portletName.get(com.liferay.taglib.portlet.RenderURLTag.class);
          _jspx_th_liferay$1portlet_renderURL_4.setPageContext(_jspx_page_context);
          _jspx_th_liferay$1portlet_renderURL_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_6);
          _jspx_th_liferay$1portlet_renderURL_4.setPortletName( PortletProviderUtil.getPortletId(DDMTemplate.class.getName(), PortletProvider.Action.EDIT) );
          _jspx_th_liferay$1portlet_renderURL_4.setVar("editDisplayTemplateURL");
          _jspx_th_liferay$1portlet_renderURL_4.setWindowState( LiferayWindowState.POP_UP.toString() );
          int _jspx_eval_liferay$1portlet_renderURL_4 = _jspx_th_liferay$1portlet_renderURL_4.doStartTag();
          if (_jspx_eval_liferay$1portlet_renderURL_4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t\t\t");
            if (_jspx_meth_portlet_param_35((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1portlet_renderURL_4, _jspx_page_context))
              return;
            out.write("\n\t\t\t\t");
            //  portlet:param
            com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_36 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
            _jspx_th_portlet_param_36.setPageContext(_jspx_page_context);
            _jspx_th_portlet_param_36.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_4);
            _jspx_th_portlet_param_36.setName("navigationStartsOn");
            _jspx_th_portlet_param_36.setValue( DDMNavigationHelper.EDIT_TEMPLATE );
            int _jspx_eval_portlet_param_36 = _jspx_th_portlet_param_36.doStartTag();
            if (_jspx_th_portlet_param_36.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_36);
              return;
            }
            _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_36);
            out.write("\n\t\t\t\t");
            //  portlet:param
            com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_37 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
            _jspx_th_portlet_param_37.setPageContext(_jspx_page_context);
            _jspx_th_portlet_param_37.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_4);
            _jspx_th_portlet_param_37.setName("closeRedirect");
            _jspx_th_portlet_param_37.setValue( currentURL );
            int _jspx_eval_portlet_param_37 = _jspx_th_portlet_param_37.doStartTag();
            if (_jspx_th_portlet_param_37.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_37);
              return;
            }
            _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_37);
            out.write("\n\t\t\t\t");
            //  portlet:param
            com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_38 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
            _jspx_th_portlet_param_38.setPageContext(_jspx_page_context);
            _jspx_th_portlet_param_38.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_4);
            _jspx_th_portlet_param_38.setName("showBackURL");
            _jspx_th_portlet_param_38.setValue( Boolean.FALSE.toString() );
            int _jspx_eval_portlet_param_38 = _jspx_th_portlet_param_38.doStartTag();
            if (_jspx_th_portlet_param_38.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_38);
              return;
            }
            _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_38);
            out.write("\n\t\t\t\t");
            //  portlet:param
            com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_39 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
            _jspx_th_portlet_param_39.setPageContext(_jspx_page_context);
            _jspx_th_portlet_param_39.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_4);
            _jspx_th_portlet_param_39.setName("showHeader");
            _jspx_th_portlet_param_39.setValue( Boolean.FALSE.toString() );
            int _jspx_eval_portlet_param_39 = _jspx_th_portlet_param_39.doStartTag();
            if (_jspx_th_portlet_param_39.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_39);
              return;
            }
            _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_39);
            out.write("\n\t\t\t\t");
            //  portlet:param
            com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_40 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
            _jspx_th_portlet_param_40.setPageContext(_jspx_page_context);
            _jspx_th_portlet_param_40.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_4);
            _jspx_th_portlet_param_40.setName("portletResource");
            _jspx_th_portlet_param_40.setValue( portletDisplay.getId() );
            int _jspx_eval_portlet_param_40 = _jspx_th_portlet_param_40.doStartTag();
            if (_jspx_th_portlet_param_40.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_40);
              return;
            }
            _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_40);
            out.write("\n\t\t\t\t");
            //  portlet:param
            com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_41 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
            _jspx_th_portlet_param_41.setPageContext(_jspx_page_context);
            _jspx_th_portlet_param_41.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_4);
            _jspx_th_portlet_param_41.setName("refererPortletName");
            _jspx_th_portlet_param_41.setValue( portletName );
            int _jspx_eval_portlet_param_41 = _jspx_th_portlet_param_41.doStartTag();
            if (_jspx_th_portlet_param_41.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_41);
              return;
            }
            _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_41);
            out.write("\n\t\t\t\t");
            //  portlet:param
            com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_42 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
            _jspx_th_portlet_param_42.setPageContext(_jspx_page_context);
            _jspx_th_portlet_param_42.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_4);
            _jspx_th_portlet_param_42.setName("groupId");
            _jspx_th_portlet_param_42.setValue( String.valueOf(scopeGroupId) );
            int _jspx_eval_portlet_param_42 = _jspx_th_portlet_param_42.doStartTag();
            if (_jspx_th_portlet_param_42.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_42);
              return;
            }
            _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_42);
            out.write("\n\t\t\t\t");
            //  portlet:param
            com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_43 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
            _jspx_th_portlet_param_43.setPageContext(_jspx_page_context);
            _jspx_th_portlet_param_43.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_4);
            _jspx_th_portlet_param_43.setName("templateId");
            _jspx_th_portlet_param_43.setValue( String.valueOf(ddlDisplayContext.getDisplayDDMTemplateId()) );
            int _jspx_eval_portlet_param_43 = _jspx_th_portlet_param_43.doStartTag();
            if (_jspx_th_portlet_param_43.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_43);
              return;
            }
            _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_43);
            out.write("\n\t\t\t\t");
            //  portlet:param
            com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_44 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
            _jspx_th_portlet_param_44.setPageContext(_jspx_page_context);
            _jspx_th_portlet_param_44.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_4);
            _jspx_th_portlet_param_44.setName("resourceClassNameId");
            _jspx_th_portlet_param_44.setValue( String.valueOf(PortalUtil.getClassNameId(DDLRecordSet.class)) );
            int _jspx_eval_portlet_param_44 = _jspx_th_portlet_param_44.doStartTag();
            if (_jspx_th_portlet_param_44.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_44);
              return;
            }
            _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_44);
            out.write("\n\t\t\t\t");
            //  portlet:param
            com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_45 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
            _jspx_th_portlet_param_45.setPageContext(_jspx_page_context);
            _jspx_th_portlet_param_45.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_4);
            _jspx_th_portlet_param_45.setName("type");
            _jspx_th_portlet_param_45.setValue( DDMTemplateConstants.TEMPLATE_TYPE_DISPLAY );
            int _jspx_eval_portlet_param_45 = _jspx_th_portlet_param_45.doStartTag();
            if (_jspx_th_portlet_param_45.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_45);
              return;
            }
            _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_45);
            out.write("\n\t\t\t");
          }
          if (_jspx_th_liferay$1portlet_renderURL_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_liferay$1portlet_renderURL_windowState_var_portletName.reuse(_jspx_th_liferay$1portlet_renderURL_4);
            return;
          }
          _jspx_tagPool_liferay$1portlet_renderURL_windowState_var_portletName.reuse(_jspx_th_liferay$1portlet_renderURL_4);
          java.lang.String editDisplayTemplateURL = null;
          editDisplayTemplateURL = (java.lang.String) _jspx_page_context.findAttribute("editDisplayTemplateURL");
          out.write("\n\n\t\t\t");

			String taglibEditDisplayDDMTemplateURL = "javascript:Liferay.Util.openWindow({id: '_" + HtmlUtil.escapeJS(portletDisplay.getId()) + "_editAsset', title: '" + HtmlUtil.escapeJS(HtmlUtil.escape(ddlDisplayContext.getEditDisplayDDMTemplateTitle())) + "', uri:'" + HtmlUtil.escapeJS(editDisplayTemplateURL.toString()) + "'});";
			
          out.write("\n\n\t\t\t<div class=\"btn-group-item\">\n\t\t\t\t");
          //  liferay-ui:icon
          com.liferay.taglib.ui.IconTag _jspx_th_liferay$1ui_icon_5 = (com.liferay.taglib.ui.IconTag) _jspx_tagPool_liferay$1ui_icon_url_message_label_icon_cssClass_nobody.get(com.liferay.taglib.ui.IconTag.class);
          _jspx_th_liferay$1ui_icon_5.setPageContext(_jspx_page_context);
          _jspx_th_liferay$1ui_icon_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_6);
          _jspx_th_liferay$1ui_icon_5.setCssClass("btn btn-link");
          _jspx_th_liferay$1ui_icon_5.setIcon("pencil");
          _jspx_th_liferay$1ui_icon_5.setLabel( true );
          _jspx_th_liferay$1ui_icon_5.setMessage("edit-display-template");
          _jspx_th_liferay$1ui_icon_5.setUrl( taglibEditDisplayDDMTemplateURL );
          int _jspx_eval_liferay$1ui_icon_5 = _jspx_th_liferay$1ui_icon_5.doStartTag();
          if (_jspx_th_liferay$1ui_icon_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_liferay$1ui_icon_url_message_label_icon_cssClass_nobody.reuse(_jspx_th_liferay$1ui_icon_5);
            return;
          }
          _jspx_tagPool_liferay$1ui_icon_url_message_label_icon_cssClass_nobody.reuse(_jspx_th_liferay$1ui_icon_5);
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
        _jspx_th_c_if_7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_0);
        _jspx_th_c_if_7.setTest( ddlDisplayContext.isShowEditFormDDMTemplateIcon() );
        int _jspx_eval_c_if_7 = _jspx_th_c_if_7.doStartTag();
        if (_jspx_eval_c_if_7 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          out.write("\n\t\t\t");
          //  liferay-portlet:renderURL
          com.liferay.taglib.portlet.RenderURLTag _jspx_th_liferay$1portlet_renderURL_5 = (com.liferay.taglib.portlet.RenderURLTag) _jspx_tagPool_liferay$1portlet_renderURL_windowState_var_portletName.get(com.liferay.taglib.portlet.RenderURLTag.class);
          _jspx_th_liferay$1portlet_renderURL_5.setPageContext(_jspx_page_context);
          _jspx_th_liferay$1portlet_renderURL_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_7);
          _jspx_th_liferay$1portlet_renderURL_5.setPortletName( PortletProviderUtil.getPortletId(DDMTemplate.class.getName(), PortletProvider.Action.EDIT) );
          _jspx_th_liferay$1portlet_renderURL_5.setVar("editFormTemplateURL");
          _jspx_th_liferay$1portlet_renderURL_5.setWindowState( LiferayWindowState.POP_UP.toString() );
          int _jspx_eval_liferay$1portlet_renderURL_5 = _jspx_th_liferay$1portlet_renderURL_5.doStartTag();
          if (_jspx_eval_liferay$1portlet_renderURL_5 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t\t\t");
            if (_jspx_meth_portlet_param_46((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1portlet_renderURL_5, _jspx_page_context))
              return;
            out.write("\n\t\t\t\t");
            //  portlet:param
            com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_47 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
            _jspx_th_portlet_param_47.setPageContext(_jspx_page_context);
            _jspx_th_portlet_param_47.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_5);
            _jspx_th_portlet_param_47.setName("navigationStartsOn");
            _jspx_th_portlet_param_47.setValue( DDMNavigationHelper.EDIT_TEMPLATE );
            int _jspx_eval_portlet_param_47 = _jspx_th_portlet_param_47.doStartTag();
            if (_jspx_th_portlet_param_47.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_47);
              return;
            }
            _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_47);
            out.write("\n\t\t\t\t");
            //  portlet:param
            com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_48 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
            _jspx_th_portlet_param_48.setPageContext(_jspx_page_context);
            _jspx_th_portlet_param_48.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_5);
            _jspx_th_portlet_param_48.setName("closeRedirect");
            _jspx_th_portlet_param_48.setValue( currentURL );
            int _jspx_eval_portlet_param_48 = _jspx_th_portlet_param_48.doStartTag();
            if (_jspx_th_portlet_param_48.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_48);
              return;
            }
            _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_48);
            out.write("\n\t\t\t\t");
            //  portlet:param
            com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_49 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
            _jspx_th_portlet_param_49.setPageContext(_jspx_page_context);
            _jspx_th_portlet_param_49.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_5);
            _jspx_th_portlet_param_49.setName("showBackURL");
            _jspx_th_portlet_param_49.setValue( Boolean.FALSE.toString() );
            int _jspx_eval_portlet_param_49 = _jspx_th_portlet_param_49.doStartTag();
            if (_jspx_th_portlet_param_49.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_49);
              return;
            }
            _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_49);
            out.write("\n\t\t\t\t");
            //  portlet:param
            com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_50 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
            _jspx_th_portlet_param_50.setPageContext(_jspx_page_context);
            _jspx_th_portlet_param_50.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_5);
            _jspx_th_portlet_param_50.setName("showHeader");
            _jspx_th_portlet_param_50.setValue( Boolean.FALSE.toString() );
            int _jspx_eval_portlet_param_50 = _jspx_th_portlet_param_50.doStartTag();
            if (_jspx_th_portlet_param_50.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_50);
              return;
            }
            _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_50);
            out.write("\n\t\t\t\t");
            //  portlet:param
            com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_51 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
            _jspx_th_portlet_param_51.setPageContext(_jspx_page_context);
            _jspx_th_portlet_param_51.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_5);
            _jspx_th_portlet_param_51.setName("portletResource");
            _jspx_th_portlet_param_51.setValue( portletDisplay.getId() );
            int _jspx_eval_portlet_param_51 = _jspx_th_portlet_param_51.doStartTag();
            if (_jspx_th_portlet_param_51.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_51);
              return;
            }
            _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_51);
            out.write("\n\t\t\t\t");
            //  portlet:param
            com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_52 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
            _jspx_th_portlet_param_52.setPageContext(_jspx_page_context);
            _jspx_th_portlet_param_52.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_5);
            _jspx_th_portlet_param_52.setName("portletResourceNamespace");
            _jspx_th_portlet_param_52.setValue( renderResponse.getNamespace() );
            int _jspx_eval_portlet_param_52 = _jspx_th_portlet_param_52.doStartTag();
            if (_jspx_th_portlet_param_52.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_52);
              return;
            }
            _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_52);
            out.write("\n\t\t\t\t");
            //  portlet:param
            com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_53 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
            _jspx_th_portlet_param_53.setPageContext(_jspx_page_context);
            _jspx_th_portlet_param_53.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_5);
            _jspx_th_portlet_param_53.setName("refererPortletName");
            _jspx_th_portlet_param_53.setValue( portletName );
            int _jspx_eval_portlet_param_53 = _jspx_th_portlet_param_53.doStartTag();
            if (_jspx_th_portlet_param_53.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_53);
              return;
            }
            _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_53);
            out.write("\n\t\t\t\t");
            //  portlet:param
            com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_54 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
            _jspx_th_portlet_param_54.setPageContext(_jspx_page_context);
            _jspx_th_portlet_param_54.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_5);
            _jspx_th_portlet_param_54.setName("groupId");
            _jspx_th_portlet_param_54.setValue( String.valueOf(scopeGroupId) );
            int _jspx_eval_portlet_param_54 = _jspx_th_portlet_param_54.doStartTag();
            if (_jspx_th_portlet_param_54.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_54);
              return;
            }
            _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_54);
            out.write("\n\t\t\t\t");
            //  portlet:param
            com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_55 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
            _jspx_th_portlet_param_55.setPageContext(_jspx_page_context);
            _jspx_th_portlet_param_55.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_5);
            _jspx_th_portlet_param_55.setName("classNameId");
            _jspx_th_portlet_param_55.setValue( String.valueOf(PortalUtil.getClassNameId(DDMStructure.class)) );
            int _jspx_eval_portlet_param_55 = _jspx_th_portlet_param_55.doStartTag();
            if (_jspx_th_portlet_param_55.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_55);
              return;
            }
            _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_55);
            out.write("\n\t\t\t\t");
            //  portlet:param
            com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_56 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
            _jspx_th_portlet_param_56.setPageContext(_jspx_page_context);
            _jspx_th_portlet_param_56.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_5);
            _jspx_th_portlet_param_56.setName("classPK");
            _jspx_th_portlet_param_56.setValue( String.valueOf(recordSet.getDDMStructureId()) );
            int _jspx_eval_portlet_param_56 = _jspx_th_portlet_param_56.doStartTag();
            if (_jspx_th_portlet_param_56.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_56);
              return;
            }
            _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_56);
            out.write("\n\t\t\t\t");
            //  portlet:param
            com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_57 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
            _jspx_th_portlet_param_57.setPageContext(_jspx_page_context);
            _jspx_th_portlet_param_57.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_5);
            _jspx_th_portlet_param_57.setName("templateId");
            _jspx_th_portlet_param_57.setValue( String.valueOf(ddlDisplayContext.getFormDDMTemplateId()) );
            int _jspx_eval_portlet_param_57 = _jspx_th_portlet_param_57.doStartTag();
            if (_jspx_th_portlet_param_57.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_57);
              return;
            }
            _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_57);
            out.write("\n\t\t\t\t");
            //  portlet:param
            com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_58 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
            _jspx_th_portlet_param_58.setPageContext(_jspx_page_context);
            _jspx_th_portlet_param_58.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_5);
            _jspx_th_portlet_param_58.setName("resourceClassNameId");
            _jspx_th_portlet_param_58.setValue( String.valueOf(PortalUtil.getClassNameId(DDLRecordSet.class)) );
            int _jspx_eval_portlet_param_58 = _jspx_th_portlet_param_58.doStartTag();
            if (_jspx_th_portlet_param_58.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_58);
              return;
            }
            _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_58);
            out.write("\n\t\t\t\t");
            //  portlet:param
            com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_59 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
            _jspx_th_portlet_param_59.setPageContext(_jspx_page_context);
            _jspx_th_portlet_param_59.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_5);
            _jspx_th_portlet_param_59.setName("type");
            _jspx_th_portlet_param_59.setValue( DDMTemplateConstants.TEMPLATE_TYPE_FORM );
            int _jspx_eval_portlet_param_59 = _jspx_th_portlet_param_59.doStartTag();
            if (_jspx_th_portlet_param_59.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_59);
              return;
            }
            _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_59);
            out.write("\n\t\t\t\t");
            //  portlet:param
            com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_60 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
            _jspx_th_portlet_param_60.setPageContext(_jspx_page_context);
            _jspx_th_portlet_param_60.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_5);
            _jspx_th_portlet_param_60.setName("structureAvailableFields");
            _jspx_th_portlet_param_60.setValue( renderResponse.getNamespace() + "getAvailableFields" );
            int _jspx_eval_portlet_param_60 = _jspx_th_portlet_param_60.doStartTag();
            if (_jspx_th_portlet_param_60.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_60);
              return;
            }
            _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_60);
            out.write("\n\t\t\t");
          }
          if (_jspx_th_liferay$1portlet_renderURL_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_liferay$1portlet_renderURL_windowState_var_portletName.reuse(_jspx_th_liferay$1portlet_renderURL_5);
            return;
          }
          _jspx_tagPool_liferay$1portlet_renderURL_windowState_var_portletName.reuse(_jspx_th_liferay$1portlet_renderURL_5);
          java.lang.String editFormTemplateURL = null;
          editFormTemplateURL = (java.lang.String) _jspx_page_context.findAttribute("editFormTemplateURL");
          out.write("\n\n\t\t\t");

			String taglibEditFormDDMTemplateURL = "javascript:Liferay.Util.openWindow({id: '_" + HtmlUtil.escapeJS(portletDisplay.getId()) + "_editAsset', title: '" + HtmlUtil.escapeJS(HtmlUtil.escape(ddlDisplayContext.getEditFormDDMTemplateTitle())) + "', uri:'" + HtmlUtil.escapeJS(editFormTemplateURL.toString()) + "'});";
			
          out.write("\n\n\t\t\t<div class=\"btn-group-item\">\n\t\t\t\t");
          //  liferay-ui:icon
          com.liferay.taglib.ui.IconTag _jspx_th_liferay$1ui_icon_6 = (com.liferay.taglib.ui.IconTag) _jspx_tagPool_liferay$1ui_icon_url_message_label_icon_cssClass_nobody.get(com.liferay.taglib.ui.IconTag.class);
          _jspx_th_liferay$1ui_icon_6.setPageContext(_jspx_page_context);
          _jspx_th_liferay$1ui_icon_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_7);
          _jspx_th_liferay$1ui_icon_6.setCssClass("btn btn-link");
          _jspx_th_liferay$1ui_icon_6.setIcon("pencil");
          _jspx_th_liferay$1ui_icon_6.setLabel( true );
          _jspx_th_liferay$1ui_icon_6.setMessage("edit-form-template");
          _jspx_th_liferay$1ui_icon_6.setUrl( taglibEditFormDDMTemplateURL );
          int _jspx_eval_liferay$1ui_icon_6 = _jspx_th_liferay$1ui_icon_6.doStartTag();
          if (_jspx_th_liferay$1ui_icon_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_liferay$1ui_icon_url_message_label_icon_cssClass_nobody.reuse(_jspx_th_liferay$1ui_icon_6);
            return;
          }
          _jspx_tagPool_liferay$1ui_icon_url_message_label_icon_cssClass_nobody.reuse(_jspx_th_liferay$1ui_icon_6);
          out.write("\n\t\t\t</div>\n\t\t");
        }
        if (_jspx_th_c_if_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_7);
          return;
        }
        _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_7);
        out.write('\n');
        out.write('	');
      }
      if (_jspx_th_c_if_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
        return;
      }
      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
      out.write("\n</div>");
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
    _jspx_th_liferay$1ui_message_0.setKey("select-an-existing-list-or-add-a-list-to-be-displayed-in-this-application");
    int _jspx_eval_liferay$1ui_message_0 = _jspx_th_liferay$1ui_message_0.doStartTag();
    if (_jspx_th_liferay$1ui_message_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_0);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_0);
    return false;
  }

  private boolean _jspx_meth_liferay$1util_param_3(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1util_include_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-util:param
    com.liferay.taglib.util.ParamTag _jspx_th_liferay$1util_param_3 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_liferay$1util_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
    _jspx_th_liferay$1util_param_3.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1util_param_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1util_include_1);
    _jspx_th_liferay$1util_param_3.setName("mvcPath");
    _jspx_th_liferay$1util_param_3.setValue("/view_selected_record_set.jsp");
    int _jspx_eval_liferay$1util_param_3 = _jspx_th_liferay$1util_param_3.doStartTag();
    if (_jspx_th_liferay$1util_param_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1util_param_value_name_nobody.reuse(_jspx_th_liferay$1util_param_3);
      return true;
    }
    _jspx_tagPool_liferay$1util_param_value_name_nobody.reuse(_jspx_th_liferay$1util_param_3);
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
    _jspx_th_portlet_param_1.setName("mvcPath");
    _jspx_th_portlet_param_1.setValue("/edit_record_set.jsp");
    int _jspx_eval_portlet_param_1 = _jspx_th_portlet_param_1.doStartTag();
    if (_jspx_th_portlet_param_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_1);
      return true;
    }
    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_1);
    return false;
  }

  private boolean _jspx_meth_portlet_param_5(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1portlet_renderURL_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:param
    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_5 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
    _jspx_th_portlet_param_5.setPageContext(_jspx_page_context);
    _jspx_th_portlet_param_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_1);
    _jspx_th_portlet_param_5.setName("mvcPath");
    _jspx_th_portlet_param_5.setValue("/edit_record_set.jsp");
    int _jspx_eval_portlet_param_5 = _jspx_th_portlet_param_5.doStartTag();
    if (_jspx_th_portlet_param_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_5);
      return true;
    }
    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_5);
    return false;
  }

  private boolean _jspx_meth_portlet_param_10(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1portlet_renderURL_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:param
    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_10 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
    _jspx_th_portlet_param_10.setPageContext(_jspx_page_context);
    _jspx_th_portlet_param_10.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_2);
    _jspx_th_portlet_param_10.setName("mvcPath");
    _jspx_th_portlet_param_10.setValue("/edit_template.jsp");
    int _jspx_eval_portlet_param_10 = _jspx_th_portlet_param_10.doStartTag();
    if (_jspx_th_portlet_param_10.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_10);
      return true;
    }
    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_10);
    return false;
  }

  private boolean _jspx_meth_portlet_param_23(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1portlet_renderURL_3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:param
    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_23 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
    _jspx_th_portlet_param_23.setPageContext(_jspx_page_context);
    _jspx_th_portlet_param_23.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_3);
    _jspx_th_portlet_param_23.setName("mvcPath");
    _jspx_th_portlet_param_23.setValue("/edit_template.jsp");
    int _jspx_eval_portlet_param_23 = _jspx_th_portlet_param_23.doStartTag();
    if (_jspx_th_portlet_param_23.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_23);
      return true;
    }
    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_23);
    return false;
  }

  private boolean _jspx_meth_portlet_param_35(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1portlet_renderURL_4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:param
    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_35 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
    _jspx_th_portlet_param_35.setPageContext(_jspx_page_context);
    _jspx_th_portlet_param_35.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_4);
    _jspx_th_portlet_param_35.setName("mvcPath");
    _jspx_th_portlet_param_35.setValue("/edit_template.jsp");
    int _jspx_eval_portlet_param_35 = _jspx_th_portlet_param_35.doStartTag();
    if (_jspx_th_portlet_param_35.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_35);
      return true;
    }
    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_35);
    return false;
  }

  private boolean _jspx_meth_portlet_param_46(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1portlet_renderURL_5, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:param
    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_46 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
    _jspx_th_portlet_param_46.setPageContext(_jspx_page_context);
    _jspx_th_portlet_param_46.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_5);
    _jspx_th_portlet_param_46.setName("mvcPath");
    _jspx_th_portlet_param_46.setValue("/edit_template.jsp");
    int _jspx_eval_portlet_param_46 = _jspx_th_portlet_param_46.doStartTag();
    if (_jspx_th_portlet_param_46.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_46);
      return true;
    }
    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_46);
    return false;
  }
}
