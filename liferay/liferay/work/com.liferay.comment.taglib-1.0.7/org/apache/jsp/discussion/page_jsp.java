package org.apache.jsp.discussion;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.language.UnicodeLanguageUtil;
import com.liferay.portal.kernel.model.*;
import com.liferay.portal.kernel.model.impl.*;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletURLUtil;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.service.*;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PortletKeys;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.impl.*;
import com.liferay.portal.service.*;
import com.liferay.portal.util.PropsUtil;
import com.liferay.portal.util.PropsValues;
import com.liferay.subscription.service.SubscriptionLocalServiceUtil;
import com.liferay.taglib.aui.AUIUtil;
import com.liferay.taglib.util.TagResourceBundleUtil;
import java.text.Format;
import java.util.Date;
import java.util.ResourceBundle;
import javax.portlet.PortletMode;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.PortletURL;
import com.liferay.comment.constants.CommentConstants;
import com.liferay.portal.comment.display.context.CommentDisplayContextProviderUtil;
import com.liferay.portal.comment.display.context.util.DiscussionRequestHelper;
import com.liferay.portal.comment.display.context.util.DiscussionTaglibHelper;
import com.liferay.portal.kernel.comment.CommentManagerUtil;
import com.liferay.portal.kernel.comment.Discussion;
import com.liferay.portal.kernel.comment.DiscussionComment;
import com.liferay.portal.kernel.comment.DiscussionCommentIterator;
import com.liferay.portal.kernel.comment.DiscussionPermission;
import com.liferay.portal.kernel.comment.WorkflowableComment;
import com.liferay.portal.kernel.comment.display.context.CommentSectionDisplayContext;
import com.liferay.portal.kernel.comment.display.context.CommentTreeDisplayContext;
import com.liferay.portal.kernel.security.auth.AuthTokenUtil;
import com.liferay.portal.kernel.service.ServiceContextFunction;
import com.liferay.staging.StagingGroupHelper;
import com.liferay.staging.StagingGroupHelperUtil;

public final class page_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(2);
    _jspx_dependants.add("/discussion/init.jsp");
    _jspx_dependants.add("/init.jsp");
  }

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_icon_url_message_label_iconCssClass_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_input$1editor_skipEditorLoading_showSource_placeholder_onChangeMethod_name_editorName_contents_configKey_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_input_value_type_name_label_helpMessage_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_fieldset_id_cssClass;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_message_key_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_if_test;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_input_value_type_name_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_otherwise;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_script_sandbox;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_form_name_method_action;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_input_type_name_id_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_icon_url_message_label_iconCssClass_cssClass_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_user$1portrait_user_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_button_value_onClick_id_disabled_cssClass_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1theme_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_input_type_name_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_choose;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_script_use;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1util_include_servletContext_page_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_when_test;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_button$1row;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_liferay$1ui_icon_url_message_label_iconCssClass_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_input$1editor_skipEditorLoading_showSource_placeholder_onChangeMethod_name_editorName_contents_configKey_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_input_value_type_name_label_helpMessage_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_fieldset_id_cssClass = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_message_key_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_if_test = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_input_value_type_name_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_otherwise = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_script_sandbox = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_form_name_method_action = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_input_type_name_id_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_icon_url_message_label_iconCssClass_cssClass_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_user$1portrait_user_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_button_value_onClick_id_disabled_cssClass_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1theme_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_input_type_name_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_choose = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_script_use = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1util_include_servletContext_page_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_when_test = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_button$1row = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_liferay$1ui_icon_url_message_label_iconCssClass_nobody.release();
    _jspx_tagPool_liferay$1ui_input$1editor_skipEditorLoading_showSource_placeholder_onChangeMethod_name_editorName_contents_configKey_nobody.release();
    _jspx_tagPool_aui_input_value_type_name_label_helpMessage_nobody.release();
    _jspx_tagPool_aui_fieldset_id_cssClass.release();
    _jspx_tagPool_liferay$1ui_message_key_nobody.release();
    _jspx_tagPool_c_if_test.release();
    _jspx_tagPool_aui_input_value_type_name_nobody.release();
    _jspx_tagPool_c_otherwise.release();
    _jspx_tagPool_aui_script_sandbox.release();
    _jspx_tagPool_portlet_defineObjects_nobody.release();
    _jspx_tagPool_aui_form_name_method_action.release();
    _jspx_tagPool_aui_input_type_name_id_nobody.release();
    _jspx_tagPool_liferay$1ui_icon_url_message_label_iconCssClass_cssClass_nobody.release();
    _jspx_tagPool_liferay$1ui_user$1portrait_user_nobody.release();
    _jspx_tagPool_aui_button_value_onClick_id_disabled_cssClass_nobody.release();
    _jspx_tagPool_liferay$1theme_defineObjects_nobody.release();
    _jspx_tagPool_aui_input_type_name_nobody.release();
    _jspx_tagPool_c_choose.release();
    _jspx_tagPool_aui_script_use.release();
    _jspx_tagPool_liferay$1util_include_servletContext_page_nobody.release();
    _jspx_tagPool_c_when_test.release();
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
      out.write('\n');
      out.write('\n');
      out.write("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
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

PortletRequest portletRequest = (PortletRequest)request.getAttribute(JavaConstants.JAVAX_PORTLET_REQUEST);

PortletResponse portletResponse = (PortletResponse)request.getAttribute(JavaConstants.JAVAX_PORTLET_RESPONSE);

String namespace = AUIUtil.getNamespace(portletRequest, portletResponse);

if (Validator.isNull(namespace)) {
	namespace = AUIUtil.getNamespace(request);
}

String currentURL = null;

if ((portletRequest != null) && (portletResponse != null)) {
	PortletURL currentURLObj = PortletURLUtil.getCurrent(PortalUtil.getLiferayPortletRequest(portletRequest), PortalUtil.getLiferayPortletResponse(portletResponse));

	currentURL = currentURLObj.toString();
}
else {
	currentURL = PortalUtil.getCurrentURL(request);
}

ResourceBundle resourceBundle = TagResourceBundleUtil.getResourceBundle(request, locale);

pageContext.setAttribute("resourceBundle", resourceBundle);

      out.write("\n\n\n\n");
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

String randomNamespace = StringUtil.randomId() + StringPool.UNDERLINE;

boolean skipEditorLoading = ParamUtil.getBoolean(request, "skipEditorLoading");

DiscussionRequestHelper discussionRequestHelper = new DiscussionRequestHelper(request);
DiscussionTaglibHelper discussionTaglibHelper = new DiscussionTaglibHelper(request);

DiscussionPermission discussionPermission = CommentManagerUtil.getDiscussionPermission(discussionRequestHelper.getPermissionChecker());

Discussion discussion = (Discussion)request.getAttribute("liferay-comment:discussion:discussion");

if (discussion == null) {
	discussion = CommentManagerUtil.getDiscussion(discussionTaglibHelper.getUserId(), discussionRequestHelper.getScopeGroupId(), discussionTaglibHelper.getClassName(), discussionTaglibHelper.getClassPK(), new ServiceContextFunction(renderRequest));
}

DiscussionComment rootDiscussionComment = (discussion == null) ? null : discussion.getRootDiscussionComment();

CommentSectionDisplayContext commentSectionDisplayContext = CommentDisplayContextProviderUtil.getCommentSectionDisplayContext(request, response, discussionPermission, discussion);
StagingGroupHelper stagingGroupHelper = StagingGroupHelperUtil.getStagingGroupHelper();

      out.write("\n\n<section>\n\t<div class=\"lfr-message-response\" id=\"");
      out.print( randomNamespace );
      out.write("discussionStatusMessages\"></div>\n\n\t");
      //  c:if
      com.liferay.taglib.core.IfTag _jspx_th_c_if_0 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
      _jspx_th_c_if_0.setPageContext(_jspx_page_context);
      _jspx_th_c_if_0.setParent(null);
      _jspx_th_c_if_0.setTest( (discussion != null) && discussion.isMaxCommentsLimitExceeded() );
      int _jspx_eval_c_if_0 = _jspx_th_c_if_0.doStartTag();
      if (_jspx_eval_c_if_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        out.write("\n\t\t<div class=\"alert alert-warning\">\n\t\t\t");
        if (_jspx_meth_liferay$1ui_message_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_0, _jspx_page_context))
          return;
        out.write("\n\t\t</div>\n\t");
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
      _jspx_th_c_if_1.setParent(null);
      _jspx_th_c_if_1.setTest( commentSectionDisplayContext.isDiscussionVisible() );
      int _jspx_eval_c_if_1 = _jspx_th_c_if_1.doStartTag();
      if (_jspx_eval_c_if_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        out.write("\n\t\t<div class=\"taglib-discussion\" id=\"");
        out.print( namespace );
        out.write("discussionContainer\">\n\t\t\t");
        //  aui:form
        com.liferay.taglib.aui.FormTag _jspx_th_aui_form_0 = (com.liferay.taglib.aui.FormTag) _jspx_tagPool_aui_form_name_method_action.get(com.liferay.taglib.aui.FormTag.class);
        _jspx_th_aui_form_0.setPageContext(_jspx_page_context);
        _jspx_th_aui_form_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_1);
        _jspx_th_aui_form_0.setAction( discussionTaglibHelper.getFormAction() );
        _jspx_th_aui_form_0.setMethod("post");
        _jspx_th_aui_form_0.setName( discussionTaglibHelper.getFormName() );
        int _jspx_eval_aui_form_0 = _jspx_th_aui_form_0.doStartTag();
        if (_jspx_eval_aui_form_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          out.write("\n\t\t\t\t<input name=\"p_auth\" type=\"hidden\" value=\"");
          out.print( AuthTokenUtil.getToken(request) );
          out.write("\" />\n\t\t\t\t<input name=\"namespace\" type=\"hidden\" value=\"");
          out.print( namespace );
          out.write("\" />\n\n\t\t\t\t");

				String contentURL = PortalUtil.getCanonicalURL(discussionTaglibHelper.getRedirect(), themeDisplay, layout);

				contentURL = HttpUtil.removeParameter(contentURL, namespace + "skipEditorLoading");
				
          out.write("\n\n\t\t\t\t<input name=\"contentURL\" type=\"hidden\" value=\"");
          out.print( contentURL );
          out.write("\" />\n\n\t\t\t\t");
          //  aui:input
          com.liferay.taglib.aui.InputTag _jspx_th_aui_input_0 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
          _jspx_th_aui_input_0.setPageContext(_jspx_page_context);
          _jspx_th_aui_input_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
          _jspx_th_aui_input_0.setName("randomNamespace");
          _jspx_th_aui_input_0.setType("hidden");
          _jspx_th_aui_input_0.setValue( randomNamespace );
          int _jspx_eval_aui_input_0 = _jspx_th_aui_input_0.doStartTag();
          if (_jspx_th_aui_input_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_0);
            return;
          }
          _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_0);
          out.write("\n\t\t\t\t");
          //  aui:input
          com.liferay.taglib.aui.InputTag _jspx_th_aui_input_1 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_type_name_id_nobody.get(com.liferay.taglib.aui.InputTag.class);
          _jspx_th_aui_input_1.setPageContext(_jspx_page_context);
          _jspx_th_aui_input_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
          _jspx_th_aui_input_1.setId( randomNamespace + Constants.CMD );
          _jspx_th_aui_input_1.setName( Constants.CMD );
          _jspx_th_aui_input_1.setType("hidden");
          int _jspx_eval_aui_input_1 = _jspx_th_aui_input_1.doStartTag();
          if (_jspx_th_aui_input_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_aui_input_type_name_id_nobody.reuse(_jspx_th_aui_input_1);
            return;
          }
          _jspx_tagPool_aui_input_type_name_id_nobody.reuse(_jspx_th_aui_input_1);
          out.write("\n\t\t\t\t");
          //  aui:input
          com.liferay.taglib.aui.InputTag _jspx_th_aui_input_2 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
          _jspx_th_aui_input_2.setPageContext(_jspx_page_context);
          _jspx_th_aui_input_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
          _jspx_th_aui_input_2.setName("redirect");
          _jspx_th_aui_input_2.setType("hidden");
          _jspx_th_aui_input_2.setValue( discussionTaglibHelper.getRedirect() );
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
          _jspx_th_aui_input_3.setName("assetEntryVisible");
          _jspx_th_aui_input_3.setType("hidden");
          _jspx_th_aui_input_3.setValue( discussionTaglibHelper.isAssetEntryVisible() );
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
          _jspx_th_aui_input_4.setName("className");
          _jspx_th_aui_input_4.setType("hidden");
          _jspx_th_aui_input_4.setValue( discussionTaglibHelper.getClassName() );
          int _jspx_eval_aui_input_4 = _jspx_th_aui_input_4.doStartTag();
          if (_jspx_th_aui_input_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_4);
            return;
          }
          _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_4);
          out.write("\n\t\t\t\t");
          //  aui:input
          com.liferay.taglib.aui.InputTag _jspx_th_aui_input_5 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
          _jspx_th_aui_input_5.setPageContext(_jspx_page_context);
          _jspx_th_aui_input_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
          _jspx_th_aui_input_5.setName("classPK");
          _jspx_th_aui_input_5.setType("hidden");
          _jspx_th_aui_input_5.setValue( discussionTaglibHelper.getClassPK() );
          int _jspx_eval_aui_input_5 = _jspx_th_aui_input_5.doStartTag();
          if (_jspx_th_aui_input_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_5);
            return;
          }
          _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_5);
          out.write("\n\t\t\t\t");
          if (_jspx_meth_aui_input_6((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_form_0, _jspx_page_context))
            return;
          out.write("\n\t\t\t\t");
          if (_jspx_meth_aui_input_7((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_form_0, _jspx_page_context))
            return;
          out.write("\n\t\t\t\t");
          if (_jspx_meth_aui_input_8((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_form_0, _jspx_page_context))
            return;
          out.write("\n\t\t\t\t");
          //  aui:input
          com.liferay.taglib.aui.InputTag _jspx_th_aui_input_9 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
          _jspx_th_aui_input_9.setPageContext(_jspx_page_context);
          _jspx_th_aui_input_9.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
          _jspx_th_aui_input_9.setName("workflowAction");
          _jspx_th_aui_input_9.setType("hidden");
          _jspx_th_aui_input_9.setValue( String.valueOf(WorkflowConstants.ACTION_PUBLISH) );
          int _jspx_eval_aui_input_9 = _jspx_th_aui_input_9.doStartTag();
          if (_jspx_th_aui_input_9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_9);
            return;
          }
          _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_9);
          out.write("\n\t\t\t\t");
          //  aui:input
          com.liferay.taglib.aui.InputTag _jspx_th_aui_input_10 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
          _jspx_th_aui_input_10.setPageContext(_jspx_page_context);
          _jspx_th_aui_input_10.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
          _jspx_th_aui_input_10.setName("ajax");
          _jspx_th_aui_input_10.setType("hidden");
          _jspx_th_aui_input_10.setValue( true );
          int _jspx_eval_aui_input_10 = _jspx_th_aui_input_10.doStartTag();
          if (_jspx_th_aui_input_10.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_10);
            return;
          }
          _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_10);
          out.write("\n\n\t\t\t\t");

				DiscussionComment discussionComment = rootDiscussionComment;
				
          out.write("\n\n\t\t\t\t");
          //  c:if
          com.liferay.taglib.core.IfTag _jspx_th_c_if_2 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
          _jspx_th_c_if_2.setPageContext(_jspx_page_context);
          _jspx_th_c_if_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
          _jspx_th_c_if_2.setTest( commentSectionDisplayContext.isControlsVisible() );
          int _jspx_eval_c_if_2 = _jspx_th_c_if_2.doStartTag();
          if (_jspx_eval_c_if_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t\t\t\t");
            //  aui:fieldset
            com.liferay.taglib.aui.FieldsetTag _jspx_th_aui_fieldset_0 = (com.liferay.taglib.aui.FieldsetTag) _jspx_tagPool_aui_fieldset_id_cssClass.get(com.liferay.taglib.aui.FieldsetTag.class);
            _jspx_th_aui_fieldset_0.setPageContext(_jspx_page_context);
            _jspx_th_aui_fieldset_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_2);
            _jspx_th_aui_fieldset_0.setCssClass("add-comment");
            _jspx_th_aui_fieldset_0.setId( randomNamespace + "messageScroll0" );
            int _jspx_eval_aui_fieldset_0 = _jspx_th_aui_fieldset_0.doStartTag();
            if (_jspx_eval_aui_fieldset_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\t\t\t\t\t\t");
              //  c:if
              com.liferay.taglib.core.IfTag _jspx_th_c_if_3 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
              _jspx_th_c_if_3.setPageContext(_jspx_page_context);
              _jspx_th_c_if_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_0);
              _jspx_th_c_if_3.setTest( !discussion.isMaxCommentsLimitExceeded() );
              int _jspx_eval_c_if_3 = _jspx_th_c_if_3.doStartTag();
              if (_jspx_eval_c_if_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\t\t\t\t\t\t\t<div id=\"");
                out.print( randomNamespace );
                out.write("messageScroll");
                out.print( rootDiscussionComment.getCommentId() );
                out.write("\">\n\t\t\t\t\t\t\t\t");
                //  aui:input
                com.liferay.taglib.aui.InputTag _jspx_th_aui_input_11 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
                _jspx_th_aui_input_11.setPageContext(_jspx_page_context);
                _jspx_th_aui_input_11.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_3);
                _jspx_th_aui_input_11.setName("commentId0");
                _jspx_th_aui_input_11.setType("hidden");
                _jspx_th_aui_input_11.setValue( rootDiscussionComment.getCommentId() );
                int _jspx_eval_aui_input_11 = _jspx_th_aui_input_11.doStartTag();
                if (_jspx_th_aui_input_11.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_11);
                  return;
                }
                _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_11);
                out.write("\n\t\t\t\t\t\t\t\t");
                //  aui:input
                com.liferay.taglib.aui.InputTag _jspx_th_aui_input_12 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
                _jspx_th_aui_input_12.setPageContext(_jspx_page_context);
                _jspx_th_aui_input_12.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_3);
                _jspx_th_aui_input_12.setName("parentCommentId0");
                _jspx_th_aui_input_12.setType("hidden");
                _jspx_th_aui_input_12.setValue( rootDiscussionComment.getCommentId() );
                int _jspx_eval_aui_input_12 = _jspx_th_aui_input_12.doStartTag();
                if (_jspx_th_aui_input_12.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_12);
                  return;
                }
                _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_12);
                out.write("\n\t\t\t\t\t\t\t</div>\n\t\t\t\t\t\t");
              }
              if (_jspx_th_c_if_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_3);
                return;
              }
              _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_3);
              out.write("\n\n\t\t\t\t\t\t");

						Group siteGroup = themeDisplay.getSiteGroup();

						boolean canSubscribe = !stagingGroupHelper.isLocalStagingGroup(siteGroup) && !stagingGroupHelper.isRemoteStagingGroup(siteGroup) && themeDisplay.isSignedIn() && discussionPermission.hasSubscribePermission(company.getCompanyId(), siteGroup.getGroupId(), discussionTaglibHelper.getClassName(), discussionTaglibHelper.getClassPK());
						boolean subscribed = SubscriptionLocalServiceUtil.isSubscribed(company.getCompanyId(), user.getUserId(), discussionTaglibHelper.getClassName(), discussionTaglibHelper.getClassPK());

						String subscriptionURL = "javascript:" + randomNamespace + "subscribeToComments(" + !subscribed + ");";
						
              out.write("\n\n\t\t\t\t\t\t");
              //  c:if
              com.liferay.taglib.core.IfTag _jspx_th_c_if_4 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
              _jspx_th_c_if_4.setPageContext(_jspx_page_context);
              _jspx_th_c_if_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_0);
              _jspx_th_c_if_4.setTest( canSubscribe );
              int _jspx_eval_c_if_4 = _jspx_th_c_if_4.doStartTag();
              if (_jspx_eval_c_if_4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\t\t\t\t\t\t\t");
                //  c:choose
                com.liferay.taglib.core.ChooseTag _jspx_th_c_choose_0 = (com.liferay.taglib.core.ChooseTag) _jspx_tagPool_c_choose.get(com.liferay.taglib.core.ChooseTag.class);
                _jspx_th_c_choose_0.setPageContext(_jspx_page_context);
                _jspx_th_c_choose_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_4);
                int _jspx_eval_c_choose_0 = _jspx_th_c_choose_0.doStartTag();
                if (_jspx_eval_c_choose_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  out.write("\n\t\t\t\t\t\t\t\t");
                  //  c:when
                  com.liferay.taglib.core.WhenTag _jspx_th_c_when_0 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
                  _jspx_th_c_when_0.setPageContext(_jspx_page_context);
                  _jspx_th_c_when_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_0);
                  _jspx_th_c_when_0.setTest( subscribed );
                  int _jspx_eval_c_when_0 = _jspx_th_c_when_0.doStartTag();
                  if (_jspx_eval_c_when_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    out.write("\n\t\t\t\t\t\t\t\t\t");
                    //  liferay-ui:icon
                    com.liferay.taglib.ui.IconTag _jspx_th_liferay$1ui_icon_0 = (com.liferay.taglib.ui.IconTag) _jspx_tagPool_liferay$1ui_icon_url_message_label_iconCssClass_cssClass_nobody.get(com.liferay.taglib.ui.IconTag.class);
                    _jspx_th_liferay$1ui_icon_0.setPageContext(_jspx_page_context);
                    _jspx_th_liferay$1ui_icon_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_0);
                    _jspx_th_liferay$1ui_icon_0.setCssClass("subscribe-link");
                    _jspx_th_liferay$1ui_icon_0.setIconCssClass("icon-remove-sign");
                    _jspx_th_liferay$1ui_icon_0.setLabel( true );
                    _jspx_th_liferay$1ui_icon_0.setMessage("unsubscribe-from-comments");
                    _jspx_th_liferay$1ui_icon_0.setUrl( subscriptionURL );
                    int _jspx_eval_liferay$1ui_icon_0 = _jspx_th_liferay$1ui_icon_0.doStartTag();
                    if (_jspx_th_liferay$1ui_icon_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_liferay$1ui_icon_url_message_label_iconCssClass_cssClass_nobody.reuse(_jspx_th_liferay$1ui_icon_0);
                      return;
                    }
                    _jspx_tagPool_liferay$1ui_icon_url_message_label_iconCssClass_cssClass_nobody.reuse(_jspx_th_liferay$1ui_icon_0);
                    out.write("\n\t\t\t\t\t\t\t\t");
                  }
                  if (_jspx_th_c_when_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_0);
                    return;
                  }
                  _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_0);
                  out.write("\n\t\t\t\t\t\t\t\t");
                  //  c:otherwise
                  com.liferay.taglib.core.OtherwiseTag _jspx_th_c_otherwise_0 = (com.liferay.taglib.core.OtherwiseTag) _jspx_tagPool_c_otherwise.get(com.liferay.taglib.core.OtherwiseTag.class);
                  _jspx_th_c_otherwise_0.setPageContext(_jspx_page_context);
                  _jspx_th_c_otherwise_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_0);
                  int _jspx_eval_c_otherwise_0 = _jspx_th_c_otherwise_0.doStartTag();
                  if (_jspx_eval_c_otherwise_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    out.write("\n\t\t\t\t\t\t\t\t\t");
                    //  liferay-ui:icon
                    com.liferay.taglib.ui.IconTag _jspx_th_liferay$1ui_icon_1 = (com.liferay.taglib.ui.IconTag) _jspx_tagPool_liferay$1ui_icon_url_message_label_iconCssClass_cssClass_nobody.get(com.liferay.taglib.ui.IconTag.class);
                    _jspx_th_liferay$1ui_icon_1.setPageContext(_jspx_page_context);
                    _jspx_th_liferay$1ui_icon_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_0);
                    _jspx_th_liferay$1ui_icon_1.setCssClass("subscribe-link");
                    _jspx_th_liferay$1ui_icon_1.setIconCssClass("icon-ok-sign");
                    _jspx_th_liferay$1ui_icon_1.setLabel( true );
                    _jspx_th_liferay$1ui_icon_1.setMessage("subscribe-to-comments");
                    _jspx_th_liferay$1ui_icon_1.setUrl( subscriptionURL );
                    int _jspx_eval_liferay$1ui_icon_1 = _jspx_th_liferay$1ui_icon_1.doStartTag();
                    if (_jspx_th_liferay$1ui_icon_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_liferay$1ui_icon_url_message_label_iconCssClass_cssClass_nobody.reuse(_jspx_th_liferay$1ui_icon_1);
                      return;
                    }
                    _jspx_tagPool_liferay$1ui_icon_url_message_label_iconCssClass_cssClass_nobody.reuse(_jspx_th_liferay$1ui_icon_1);
                    out.write("\n\t\t\t\t\t\t\t\t");
                  }
                  if (_jspx_th_c_otherwise_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_0);
                    return;
                  }
                  _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_0);
                  out.write("\n\t\t\t\t\t\t\t");
                }
                if (_jspx_th_c_choose_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_0);
                  return;
                }
                _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_0);
                out.write("\n\t\t\t\t\t\t");
              }
              if (_jspx_th_c_if_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_4);
                return;
              }
              _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_4);
              out.write("\n\n\t\t\t\t\t\t");
              //  c:if
              com.liferay.taglib.core.IfTag _jspx_th_c_if_5 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
              _jspx_th_c_if_5.setPageContext(_jspx_page_context);
              _jspx_th_c_if_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_0);
              _jspx_th_c_if_5.setTest( !discussion.isMaxCommentsLimitExceeded() );
              int _jspx_eval_c_if_5 = _jspx_th_c_if_5.doStartTag();
              if (_jspx_eval_c_if_5 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\t\t\t\t\t\t\t");
                if (_jspx_meth_aui_input_13((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_5, _jspx_page_context))
                  return;
                out.write("\n\n\t\t\t\t\t\t\t");
                //  c:choose
                com.liferay.taglib.core.ChooseTag _jspx_th_c_choose_1 = (com.liferay.taglib.core.ChooseTag) _jspx_tagPool_c_choose.get(com.liferay.taglib.core.ChooseTag.class);
                _jspx_th_c_choose_1.setPageContext(_jspx_page_context);
                _jspx_th_c_choose_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_5);
                int _jspx_eval_c_choose_1 = _jspx_th_c_choose_1.doStartTag();
                if (_jspx_eval_c_choose_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  out.write("\n\t\t\t\t\t\t\t\t");
                  //  c:when
                  com.liferay.taglib.core.WhenTag _jspx_th_c_when_1 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
                  _jspx_th_c_when_1.setPageContext(_jspx_page_context);
                  _jspx_th_c_when_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_1);
                  _jspx_th_c_when_1.setTest( commentSectionDisplayContext.isReplyButtonVisible() );
                  int _jspx_eval_c_when_1 = _jspx_th_c_when_1.doStartTag();
                  if (_jspx_eval_c_when_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    out.write("\n\t\t\t\t\t\t\t\t\t<div class=\"panel\">\n\t\t\t\t\t\t\t\t\t\t<div class=\"panel-body\">\n\t\t\t\t\t\t\t\t\t\t\t<div class=\"lfr-discussion-details\">\n\t\t\t\t\t\t\t\t\t\t\t\t");
                    //  liferay-ui:user-portrait
                    com.liferay.taglib.ui.UserPortraitTag _jspx_th_liferay$1ui_user$1portrait_0 = (com.liferay.taglib.ui.UserPortraitTag) _jspx_tagPool_liferay$1ui_user$1portrait_user_nobody.get(com.liferay.taglib.ui.UserPortraitTag.class);
                    _jspx_th_liferay$1ui_user$1portrait_0.setPageContext(_jspx_page_context);
                    _jspx_th_liferay$1ui_user$1portrait_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_1);
                    _jspx_th_liferay$1ui_user$1portrait_0.setUser( user );
                    int _jspx_eval_liferay$1ui_user$1portrait_0 = _jspx_th_liferay$1ui_user$1portrait_0.doStartTag();
                    if (_jspx_th_liferay$1ui_user$1portrait_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_liferay$1ui_user$1portrait_user_nobody.reuse(_jspx_th_liferay$1ui_user$1portrait_0);
                      return;
                    }
                    _jspx_tagPool_liferay$1ui_user$1portrait_user_nobody.reuse(_jspx_th_liferay$1ui_user$1portrait_0);
                    out.write("\n\t\t\t\t\t\t\t\t\t\t\t</div>\n\n\t\t\t\t\t\t\t\t\t\t\t<div class=\"lfr-discussion-body\">\n\t\t\t\t\t\t\t\t\t\t\t\t");
                    //  liferay-ui:input-editor
                    com.liferay.taglib.ui.InputEditorTag _jspx_th_liferay$1ui_input$1editor_0 = (com.liferay.taglib.ui.InputEditorTag) _jspx_tagPool_liferay$1ui_input$1editor_skipEditorLoading_showSource_placeholder_onChangeMethod_name_editorName_contents_configKey_nobody.get(com.liferay.taglib.ui.InputEditorTag.class);
                    _jspx_th_liferay$1ui_input$1editor_0.setPageContext(_jspx_page_context);
                    _jspx_th_liferay$1ui_input$1editor_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_1);
                    _jspx_th_liferay$1ui_input$1editor_0.setConfigKey("commentEditor");
                    _jspx_th_liferay$1ui_input$1editor_0.setContents("");
                    _jspx_th_liferay$1ui_input$1editor_0.setEditorName( PropsUtil.get("editor.wysiwyg.portal-web.docroot.html.taglib.ui.discussion.jsp") );
                    _jspx_th_liferay$1ui_input$1editor_0.setName( randomNamespace + "postReplyBody0" );
                    _jspx_th_liferay$1ui_input$1editor_0.setOnChangeMethod( randomNamespace + "0ReplyOnChange" );
                    _jspx_th_liferay$1ui_input$1editor_0.setPlaceholder("type-your-comment-here");
                    _jspx_th_liferay$1ui_input$1editor_0.setShowSource( false );
                    _jspx_th_liferay$1ui_input$1editor_0.setSkipEditorLoading( skipEditorLoading );
                    int _jspx_eval_liferay$1ui_input$1editor_0 = _jspx_th_liferay$1ui_input$1editor_0.doStartTag();
                    if (_jspx_th_liferay$1ui_input$1editor_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_liferay$1ui_input$1editor_skipEditorLoading_showSource_placeholder_onChangeMethod_name_editorName_contents_configKey_nobody.reuse(_jspx_th_liferay$1ui_input$1editor_0);
                      return;
                    }
                    _jspx_tagPool_liferay$1ui_input$1editor_skipEditorLoading_showSource_placeholder_onChangeMethod_name_editorName_contents_configKey_nobody.reuse(_jspx_th_liferay$1ui_input$1editor_0);
                    out.write("\n\n\t\t\t\t\t\t\t\t\t\t\t\t");
                    if (_jspx_meth_aui_input_14((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_when_1, _jspx_page_context))
                      return;
                    out.write("\n\n\t\t\t\t\t\t\t\t\t\t\t\t");
                    //  c:if
                    com.liferay.taglib.core.IfTag _jspx_th_c_if_6 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                    _jspx_th_c_if_6.setPageContext(_jspx_page_context);
                    _jspx_th_c_if_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_1);
                    _jspx_th_c_if_6.setTest( canSubscribe && !subscribed );
                    int _jspx_eval_c_if_6 = _jspx_th_c_if_6.doStartTag();
                    if (_jspx_eval_c_if_6 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      out.write("\n\t\t\t\t\t\t\t\t\t\t\t\t\t");
                      //  aui:input
                      com.liferay.taglib.aui.InputTag _jspx_th_aui_input_15 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_label_helpMessage_nobody.get(com.liferay.taglib.aui.InputTag.class);
                      _jspx_th_aui_input_15.setPageContext(_jspx_page_context);
                      _jspx_th_aui_input_15.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_6);
                      _jspx_th_aui_input_15.setHelpMessage("comments-subscribe-me-help");
                      _jspx_th_aui_input_15.setLabel("subscribe-me");
                      _jspx_th_aui_input_15.setName("subscribe");
                      _jspx_th_aui_input_15.setType("checkbox");
                      _jspx_th_aui_input_15.setValue( PropsValues.DISCUSSION_SUBSCRIBE_BY_DEFAULT );
                      int _jspx_eval_aui_input_15 = _jspx_th_aui_input_15.doStartTag();
                      if (_jspx_th_aui_input_15.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_aui_input_value_type_name_label_helpMessage_nobody.reuse(_jspx_th_aui_input_15);
                        return;
                      }
                      _jspx_tagPool_aui_input_value_type_name_label_helpMessage_nobody.reuse(_jspx_th_aui_input_15);
                      out.write("\n\t\t\t\t\t\t\t\t\t\t\t\t");
                    }
                    if (_jspx_th_c_if_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_6);
                      return;
                    }
                    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_6);
                    out.write("\n\n\t\t\t\t\t\t\t\t\t\t\t\t");
                    //  aui:button-row
                    com.liferay.taglib.aui.ButtonRowTag _jspx_th_aui_button$1row_0 = (com.liferay.taglib.aui.ButtonRowTag) _jspx_tagPool_aui_button$1row.get(com.liferay.taglib.aui.ButtonRowTag.class);
                    _jspx_th_aui_button$1row_0.setPageContext(_jspx_page_context);
                    _jspx_th_aui_button$1row_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_1);
                    int _jspx_eval_aui_button$1row_0 = _jspx_th_aui_button$1row_0.doStartTag();
                    if (_jspx_eval_aui_button$1row_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      out.write("\n\t\t\t\t\t\t\t\t\t\t\t\t\t");
                      //  aui:button
                      com.liferay.taglib.aui.ButtonTag _jspx_th_aui_button_0 = (com.liferay.taglib.aui.ButtonTag) _jspx_tagPool_aui_button_value_onClick_id_disabled_cssClass_nobody.get(com.liferay.taglib.aui.ButtonTag.class);
                      _jspx_th_aui_button_0.setPageContext(_jspx_page_context);
                      _jspx_th_aui_button_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_button$1row_0);
                      _jspx_th_aui_button_0.setCssClass("btn-comment btn-primary");
                      _jspx_th_aui_button_0.setDisabled( true );
                      _jspx_th_aui_button_0.setId( randomNamespace + "postReplyButton0" );
                      _jspx_th_aui_button_0.setOnClick( randomNamespace + "postReply(0);" );
                      _jspx_th_aui_button_0.setValue( themeDisplay.isSignedIn() ? "reply" : "reply-as" );
                      int _jspx_eval_aui_button_0 = _jspx_th_aui_button_0.doStartTag();
                      if (_jspx_th_aui_button_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_aui_button_value_onClick_id_disabled_cssClass_nobody.reuse(_jspx_th_aui_button_0);
                        return;
                      }
                      _jspx_tagPool_aui_button_value_onClick_id_disabled_cssClass_nobody.reuse(_jspx_th_aui_button_0);
                      out.write("\n\t\t\t\t\t\t\t\t\t\t\t\t");
                    }
                    if (_jspx_th_aui_button$1row_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_aui_button$1row.reuse(_jspx_th_aui_button$1row_0);
                      return;
                    }
                    _jspx_tagPool_aui_button$1row.reuse(_jspx_th_aui_button$1row_0);
                    out.write("\n\t\t\t\t\t\t\t\t\t\t\t</div>\n\t\t\t\t\t\t\t\t\t\t</div>\n\t\t\t\t\t\t\t\t\t</div>\n\t\t\t\t\t\t\t\t");
                  }
                  if (_jspx_th_c_when_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_1);
                    return;
                  }
                  _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_1);
                  out.write("\n\t\t\t\t\t\t\t\t");
                  //  c:otherwise
                  com.liferay.taglib.core.OtherwiseTag _jspx_th_c_otherwise_1 = (com.liferay.taglib.core.OtherwiseTag) _jspx_tagPool_c_otherwise.get(com.liferay.taglib.core.OtherwiseTag.class);
                  _jspx_th_c_otherwise_1.setPageContext(_jspx_page_context);
                  _jspx_th_c_otherwise_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_1);
                  int _jspx_eval_c_otherwise_1 = _jspx_th_c_otherwise_1.doStartTag();
                  if (_jspx_eval_c_otherwise_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    out.write("\n\t\t\t\t\t\t\t\t\t");
                    //  c:choose
                    com.liferay.taglib.core.ChooseTag _jspx_th_c_choose_2 = (com.liferay.taglib.core.ChooseTag) _jspx_tagPool_c_choose.get(com.liferay.taglib.core.ChooseTag.class);
                    _jspx_th_c_choose_2.setPageContext(_jspx_page_context);
                    _jspx_th_c_choose_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_1);
                    int _jspx_eval_c_choose_2 = _jspx_th_c_choose_2.doStartTag();
                    if (_jspx_eval_c_choose_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      out.write("\n\t\t\t\t\t\t\t\t\t\t");
                      //  c:when
                      com.liferay.taglib.core.WhenTag _jspx_th_c_when_2 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
                      _jspx_th_c_when_2.setPageContext(_jspx_page_context);
                      _jspx_th_c_when_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_2);
                      _jspx_th_c_when_2.setTest( stagingGroupHelper.isLocalStagingGroup(siteGroup) || stagingGroupHelper.isRemoteStagingGroup(siteGroup) );
                      int _jspx_eval_c_when_2 = _jspx_th_c_when_2.doStartTag();
                      if (_jspx_eval_c_when_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                        out.write("\n\t\t\t\t\t\t\t\t\t\t\t<div class=\"alert alert-info\">\n\t\t\t\t\t\t\t\t\t\t\t\t");
                        if (_jspx_meth_liferay$1ui_message_1((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_when_2, _jspx_page_context))
                          return;
                        out.write("\n\t\t\t\t\t\t\t\t\t\t\t</div>\n\t\t\t\t\t\t\t\t\t\t");
                      }
                      if (_jspx_th_c_when_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_2);
                        return;
                      }
                      _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_2);
                      out.write("\n\t\t\t\t\t\t\t\t\t\t");
                      //  c:otherwise
                      com.liferay.taglib.core.OtherwiseTag _jspx_th_c_otherwise_2 = (com.liferay.taglib.core.OtherwiseTag) _jspx_tagPool_c_otherwise.get(com.liferay.taglib.core.OtherwiseTag.class);
                      _jspx_th_c_otherwise_2.setPageContext(_jspx_page_context);
                      _jspx_th_c_otherwise_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_2);
                      int _jspx_eval_c_otherwise_2 = _jspx_th_c_otherwise_2.doStartTag();
                      if (_jspx_eval_c_otherwise_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                        out.write("\n\t\t\t\t\t\t\t\t\t\t\t");
                        //  liferay-ui:icon
                        com.liferay.taglib.ui.IconTag _jspx_th_liferay$1ui_icon_2 = (com.liferay.taglib.ui.IconTag) _jspx_tagPool_liferay$1ui_icon_url_message_label_iconCssClass_nobody.get(com.liferay.taglib.ui.IconTag.class);
                        _jspx_th_liferay$1ui_icon_2.setPageContext(_jspx_page_context);
                        _jspx_th_liferay$1ui_icon_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_2);
                        _jspx_th_liferay$1ui_icon_2.setIconCssClass("icon-reply");
                        _jspx_th_liferay$1ui_icon_2.setLabel( true );
                        _jspx_th_liferay$1ui_icon_2.setMessage("please-sign-in-to-comment");
                        _jspx_th_liferay$1ui_icon_2.setUrl( themeDisplay.getURLSignIn() );
                        int _jspx_eval_liferay$1ui_icon_2 = _jspx_th_liferay$1ui_icon_2.doStartTag();
                        if (_jspx_th_liferay$1ui_icon_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                          _jspx_tagPool_liferay$1ui_icon_url_message_label_iconCssClass_nobody.reuse(_jspx_th_liferay$1ui_icon_2);
                          return;
                        }
                        _jspx_tagPool_liferay$1ui_icon_url_message_label_iconCssClass_nobody.reuse(_jspx_th_liferay$1ui_icon_2);
                        out.write("\n\t\t\t\t\t\t\t\t\t\t");
                      }
                      if (_jspx_th_c_otherwise_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_2);
                        return;
                      }
                      _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_2);
                      out.write("\n\t\t\t\t\t\t\t\t\t");
                    }
                    if (_jspx_th_c_choose_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_2);
                      return;
                    }
                    _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_2);
                    out.write("\n\t\t\t\t\t\t\t\t");
                  }
                  if (_jspx_th_c_otherwise_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_1);
                    return;
                  }
                  _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_1);
                  out.write("\n\t\t\t\t\t\t\t");
                }
                if (_jspx_th_c_choose_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_1);
                  return;
                }
                _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_1);
                out.write("\n\t\t\t\t\t\t");
              }
              if (_jspx_th_c_if_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_5);
                return;
              }
              _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_5);
              out.write("\n\t\t\t\t\t");
            }
            if (_jspx_th_aui_fieldset_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_aui_fieldset_id_cssClass.reuse(_jspx_th_aui_fieldset_0);
              return;
            }
            _jspx_tagPool_aui_fieldset_id_cssClass.reuse(_jspx_th_aui_fieldset_0);
            out.write("\n\t\t\t\t");
          }
          if (_jspx_th_c_if_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_2);
            return;
          }
          _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_2);
          out.write("\n\n\t\t\t\t");
          //  c:if
          com.liferay.taglib.core.IfTag _jspx_th_c_if_7 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
          _jspx_th_c_if_7.setPageContext(_jspx_page_context);
          _jspx_th_c_if_7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
          _jspx_th_c_if_7.setTest( commentSectionDisplayContext.isMessageThreadVisible() );
          int _jspx_eval_c_if_7 = _jspx_th_c_if_7.doStartTag();
          if (_jspx_eval_c_if_7 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t\t\t\t<a name=\"");
            out.print( randomNamespace );
            out.write("messages_top\"></a>\n\n\t\t\t\t\t<div>\n\n\t\t\t\t\t\t");

						int index = 0;
						int rootIndexPage = 0;
						boolean moreCommentsPagination = false;

						DiscussionCommentIterator discussionCommentIterator = rootDiscussionComment.getThreadDiscussionCommentIterator();

						while (discussionCommentIterator.hasNext()) {
							index = GetterUtil.getInteger(request.getAttribute("liferay-comment:discussion:index"), 1);

							rootIndexPage = discussionCommentIterator.getIndexPage();

							if ((index + 1) > PropsValues.DISCUSSION_COMMENTS_DELTA_VALUE) {
								moreCommentsPagination = true;

								break;
							}

							request.setAttribute("liferay-comment:discussion:depth", 0);
							request.setAttribute("liferay-comment:discussion:discussion", discussion);
							request.setAttribute("liferay-comment:discussion:discussionComment", discussionCommentIterator.next());
							request.setAttribute("liferay-comment:discussion:randomNamespace", randomNamespace);
						
            out.write("\n\n\t\t\t\t\t\t\t");
            //  liferay-util:include
            com.liferay.taglib.util.IncludeTag _jspx_th_liferay$1util_include_0 = (com.liferay.taglib.util.IncludeTag) _jspx_tagPool_liferay$1util_include_servletContext_page_nobody.get(com.liferay.taglib.util.IncludeTag.class);
            _jspx_th_liferay$1util_include_0.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1util_include_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_7);
            _jspx_th_liferay$1util_include_0.setPage("/discussion/view_message_thread.jsp");
            _jspx_th_liferay$1util_include_0.setServletContext( application );
            int _jspx_eval_liferay$1util_include_0 = _jspx_th_liferay$1util_include_0.doStartTag();
            if (_jspx_th_liferay$1util_include_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_liferay$1util_include_servletContext_page_nobody.reuse(_jspx_th_liferay$1util_include_0);
              return;
            }
            _jspx_tagPool_liferay$1util_include_servletContext_page_nobody.reuse(_jspx_th_liferay$1util_include_0);
            out.write("\n\n\t\t\t\t\t\t");

						}
						
            out.write("\n\n\t\t\t\t\t\t");
            //  c:if
            com.liferay.taglib.core.IfTag _jspx_th_c_if_8 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
            _jspx_th_c_if_8.setPageContext(_jspx_page_context);
            _jspx_th_c_if_8.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_7);
            _jspx_th_c_if_8.setTest( moreCommentsPagination );
            int _jspx_eval_c_if_8 = _jspx_th_c_if_8.doStartTag();
            if (_jspx_eval_c_if_8 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\t\t\t\t\t\t\t<div id=\"");
              out.print( namespace );
              out.write("moreCommentsPage\"></div>\n\n\t\t\t\t\t\t\t<a class=\"btn btn-default\" href=\"javascript:;\" id=\"");
              out.print( namespace );
              out.write("moreComments\">");
              if (_jspx_meth_liferay$1ui_message_2((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_8, _jspx_page_context))
                return;
              out.write("</a>\n\n\t\t\t\t\t\t\t");
              //  aui:input
              com.liferay.taglib.aui.InputTag _jspx_th_aui_input_16 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
              _jspx_th_aui_input_16.setPageContext(_jspx_page_context);
              _jspx_th_aui_input_16.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_8);
              _jspx_th_aui_input_16.setName("rootIndexPage");
              _jspx_th_aui_input_16.setType("hidden");
              _jspx_th_aui_input_16.setValue( String.valueOf(rootIndexPage) );
              int _jspx_eval_aui_input_16 = _jspx_th_aui_input_16.doStartTag();
              if (_jspx_th_aui_input_16.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_16);
                return;
              }
              _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_16);
              out.write("\n\t\t\t\t\t\t\t");
              //  aui:input
              com.liferay.taglib.aui.InputTag _jspx_th_aui_input_17 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
              _jspx_th_aui_input_17.setPageContext(_jspx_page_context);
              _jspx_th_aui_input_17.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_8);
              _jspx_th_aui_input_17.setName("index");
              _jspx_th_aui_input_17.setType("hidden");
              _jspx_th_aui_input_17.setValue( String.valueOf(index) );
              int _jspx_eval_aui_input_17 = _jspx_th_aui_input_17.doStartTag();
              if (_jspx_th_aui_input_17.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_17);
                return;
              }
              _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_17);
              out.write("\n\t\t\t\t\t\t");
            }
            if (_jspx_th_c_if_8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_8);
              return;
            }
            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_8);
            out.write("\n\t\t\t\t\t</div>\n\t\t\t\t");
          }
          if (_jspx_th_c_if_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_7);
            return;
          }
          _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_7);
          out.write("\n\t\t\t");
        }
        if (_jspx_th_aui_form_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_aui_form_name_method_action.reuse(_jspx_th_aui_form_0);
          return;
        }
        _jspx_tagPool_aui_form_name_method_action.reuse(_jspx_th_aui_form_0);
        out.write("\n\t\t</div>\n\n\t\t");

		PortletURL loginURL = PortletURLFactoryUtil.create(request, PortletKeys.FAST_LOGIN, PortletRequest.RENDER_PHASE);

		loginURL.setParameter("saveLastPath", Boolean.FALSE.toString());
		loginURL.setParameter("mvcRenderCommandName", "/login/login");
		loginURL.setPortletMode(PortletMode.VIEW);
		loginURL.setWindowState(LiferayWindowState.POP_UP);
		
        out.write("\n\n\t\t");
        //  aui:script
        com.liferay.taglib.aui.ScriptTag _jspx_th_aui_script_0 = (com.liferay.taglib.aui.ScriptTag) _jspx_tagPool_aui_script_use.get(com.liferay.taglib.aui.ScriptTag.class);
        _jspx_th_aui_script_0.setPageContext(_jspx_page_context);
        _jspx_th_aui_script_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_1);
        _jspx_th_aui_script_0.setUse("aui-base");
        int _jspx_eval_aui_script_0 = _jspx_th_aui_script_0.doStartTag();
        if (_jspx_eval_aui_script_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          if (_jspx_eval_aui_script_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
            out = _jspx_page_context.pushBody();
            _jspx_th_aui_script_0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
            _jspx_th_aui_script_0.doInitBody();
          }
          do {
            out.write("\n\t\t\tLiferay.provide(\n\t\t\t\twindow,\n\t\t\t\t'");
            out.print( namespace + randomNamespace );
            out.write("0ReplyOnChange',\n\t\t\t\tfunction(html) {\n\t\t\t\t\tLiferay.Util.toggleDisabled('#");
            out.print( namespace + randomNamespace );
            out.write("postReplyButton0', html.trim() === '');\n\t\t\t\t}\n\t\t\t);\n\n\t\t\tLiferay.provide(\n\t\t\t\twindow,\n\t\t\t\t'");
            out.print( randomNamespace );
            out.write("afterLogin',\n\t\t\t\tfunction(emailAddress, anonymousAccount) {\n\t\t\t\t\tvar form = AUI.$('#");
            out.print( namespace );
            out.print( HtmlUtil.escapeJS(discussionTaglibHelper.getFormName()) );
            out.write("');\n\n\t\t\t\t\tform.fm('emailAddress').val(emailAddress);\n\n\t\t\t\t\t");
            out.print( namespace );
            out.write("sendMessage(form, !anonymousAccount);\n\t\t\t\t}\n\t\t\t);\n\n\t\t\tLiferay.provide(\n\t\t\t\twindow,\n\t\t\t\t'");
            out.print( randomNamespace );
            out.write("deleteMessage',\n\t\t\t\tfunction(i) {\n\t\t\t\t\tvar form = AUI.$('#");
            out.print( namespace );
            out.print( HtmlUtil.escapeJS(discussionTaglibHelper.getFormName()) );
            out.write("');\n\n\t\t\t\t\tvar commentId = form.fm('commentId' + i).val();\n\n\t\t\t\t\tform.fm('");
            out.print( randomNamespace );
            out.print( Constants.CMD );
            out.write("').val('");
            out.print( Constants.DELETE );
            out.write("');\n\t\t\t\t\tform.fm('commentId').val(commentId);\n\n\t\t\t\t\t");
            out.print( namespace );
            out.write("sendMessage(form);\n\t\t\t\t}\n\t\t\t);\n\n\t\t\tLiferay.provide(\n\t\t\t\twindow,\n\t\t\t\t'");
            out.print( randomNamespace );
            out.write("hideEl',\n\t\t\t\tfunction(elId) {\n\t\t\t\t\tAUI.$('#' + elId).css('display', 'none');\n\t\t\t\t}\n\t\t\t);\n\n\t\t\tLiferay.provide(\n\t\t\t\twindow,\n\t\t\t\t'");
            out.print( randomNamespace );
            out.write("hideEditor',\n\t\t\t\tfunction(editorName, formId) {\n\t\t\t\t\tvar editor = window['");
            out.print( namespace );
            out.write("' + editorName];\n\n\t\t\t\t\tif (editor) {\n\t\t\t\t\t\teditor.destroy();\n\t\t\t\t\t}\n\n\t\t\t\t\t");
            out.print( randomNamespace );
            out.write("hideEl(formId);\n\t\t\t\t}\n\t\t\t);\n\n\t\t\tLiferay.provide(\n\t\t\t\twindow,\n\t\t\t\t'");
            out.print( randomNamespace );
            out.write("onMessagePosted',\n\t\t\t\tfunction(response, refreshPage) {\n\t\t\t\t\tLiferay.onceAfter(\n\t\t\t\t\t\t'");
            out.print( portletDisplay.getId() );
            out.write(":portletRefreshed',\n\t\t\t\t\t\tfunction(event) {\n\t\t\t\t\t\t\tvar randomNamespaceNodes = AUI.$('input[id^=\"");
            out.print( namespace );
            out.write("randomNamespace\"]');\n\n\t\t\t\t\t\t\trandomNamespaceNodes.each(\n\t\t\t\t\t\t\t\tfunction(index, item) {\n\t\t\t\t\t\t\t\t\tvar randomId = item.value;\n\n\t\t\t\t\t\t\t\t\tif (index === 0) {\n\t\t\t\t\t\t\t\t\t\t");
            out.print( randomNamespace );
            out.write("showStatusMessage(\n\t\t\t\t\t\t\t\t\t\t\t{\n\t\t\t\t\t\t\t\t\t\t\t\tid: randomId,\n\t\t\t\t\t\t\t\t\t\t\t\tmessage: '");
            out.print( UnicodeLanguageUtil.get(resourceBundle, "your-request-completed-successfully") );
            out.write("',\n\t\t\t\t\t\t\t\t\t\t\t\ttitle: '");
            out.print( UnicodeLanguageUtil.get(resourceBundle, "success") );
            out.write("',\n\t\t\t\t\t\t\t\t\t\t\t\ttype: 'success'\n\t\t\t\t\t\t\t\t\t\t\t}\n\t\t\t\t\t\t\t\t\t\t);\n\t\t\t\t\t\t\t\t\t}\n\n\t\t\t\t\t\t\t\t\tvar currentMessageSelector = '#' + randomId + 'message_' + response.commentId;\n\n\t\t\t\t\t\t\t\t\tvar targetNode = AUI.$(currentMessageSelector);\n\n\t\t\t\t\t\t\t\t\tif (targetNode.length) {\n\t\t\t\t\t\t\t\t\t\tlocation.hash = currentMessageSelector;\n\t\t\t\t\t\t\t\t\t}\n\t\t\t\t\t\t\t\t}\n\t\t\t\t\t\t\t);\n\t\t\t\t\t\t}\n\t\t\t\t\t);\n\n\t\t\t\t\tLiferay.fire(\n\t\t\t\t\t\t'messagePosted',\n\t\t\t\t\t\t{\n\t\t\t\t\t\t\tclassName: '");
            out.print( discussionTaglibHelper.getClassName() );
            out.write("',\n\t\t\t\t\t\t\tclassPK: '");
            out.print( discussionTaglibHelper.getClassPK() );
            out.write("',\n\t\t\t\t\t\t\tcommentId: response.commentId,\n\t\t\t\t\t\t\ttext: AUI.$('input[name^=\"");
            out.print( namespace );
            out.write("body\"]').val()\n\t\t\t\t\t\t}\n\t\t\t\t\t);\n\n\t\t\t\t\tif (refreshPage) {\n\t\t\t\t\t\twindow.location.reload();\n\t\t\t\t\t}\n\t\t\t\t\telse {\n\t\t\t\t\t\tvar portletNodeId = '#p_p_id_");
            out.print( portletDisplay.getId() );
            out.write("_';\n\n\t\t\t\t\t\tvar portletNode = A.one(portletNodeId);\n\n\t\t\t\t\t\tLiferay.Portlet.refresh(\n\t\t\t\t\t\t\tportletNodeId,\n\t\t\t\t\t\t\tA.merge(\n\t\t\t\t\t\t\t\tLiferay.Util.ns(\n\t\t\t\t\t\t\t\t\t'");
            out.print( namespace );
            out.write("',\n\t\t\t\t\t\t\t\t\t{\n\t\t\t\t\t\t\t\t\t\tskipEditorLoading: true\n\t\t\t\t\t\t\t\t\t}\n\t\t\t\t\t\t\t\t),\n\t\t\t\t\t\t\t\tportletNode.refreshURLData || {}\n\t\t\t\t\t\t\t)\n\t\t\t\t\t\t);\n\t\t\t\t\t}\n\t\t\t\t}\n\t\t\t);\n\n\t\t\tLiferay.provide(\n\t\t\t\twindow,\n\t\t\t\t'");
            out.print( randomNamespace );
            out.write("postReply',\n\t\t\t\tfunction(i) {\n\t\t\t\t\tvar form = AUI.$('#");
            out.print( namespace );
            out.print( HtmlUtil.escapeJS(discussionTaglibHelper.getFormName()) );
            out.write("');\n\n\t\t\t\t\tvar editorInstance = window['");
            out.print( namespace + randomNamespace );
            out.write("postReplyBody' + i];\n\n\t\t\t\t\tvar parentCommentId = form.fm('parentCommentId' + i).val();\n\n\t\t\t\t\tform.fm('");
            out.print( randomNamespace );
            out.print( Constants.CMD );
            out.write("').val('");
            out.print( Constants.ADD );
            out.write("');\n\t\t\t\t\tform.fm('parentCommentId').val(parentCommentId);\n\t\t\t\t\tform.fm('body').val(editorInstance.getHTML());\n\n\t\t\t\t\tif (!themeDisplay.isSignedIn()) {\n\t\t\t\t\t\twindow.namespace = '");
            out.print( namespace );
            out.write("';\n\t\t\t\t\t\twindow.randomNamespace = '");
            out.print( randomNamespace );
            out.write("';\n\n\t\t\t\t\t\tLiferay.Util.openWindow(\n\t\t\t\t\t\t\t{\n\t\t\t\t\t\t\t\tdialog: {\n\t\t\t\t\t\t\t\t\theight: 450,\n\t\t\t\t\t\t\t\t\twidth: 560\n\t\t\t\t\t\t\t\t},\n\t\t\t\t\t\t\t\tid: '");
            out.print( namespace );
            out.write("signInDialog',\n\t\t\t\t\t\t\t\ttitle: '");
            out.print( UnicodeLanguageUtil.get(resourceBundle, "sign-in") );
            out.write("',\n\t\t\t\t\t\t\t\turi: '");
            out.print( loginURL.toString() );
            out.write("'\n\t\t\t\t\t\t\t}\n\t\t\t\t\t\t);\n\t\t\t\t\t}\n\t\t\t\t\telse {\n\t\t\t\t\t\t");
            out.print( namespace );
            out.write("sendMessage(form);\n\n\t\t\t\t\t\teditorInstance.dispose();\n\t\t\t\t\t}\n\t\t\t\t}\n\t\t\t);\n\n\t\t\tLiferay.provide(\n\t\t\t\twindow,\n\t\t\t\t'");
            out.print( randomNamespace );
            out.write("scrollIntoView',\n\t\t\t\tfunction(commentId) {\n\t\t\t\t\tdocument.getElementById('");
            out.print( randomNamespace );
            out.write("messageScroll' + commentId).scrollIntoView();\n\t\t\t\t}\n\t\t\t);\n\n\t\t\tLiferay.provide(\n\t\t\t\twindow,\n\t\t\t\t'");
            out.print( namespace );
            out.write("sendMessage',\n\t\t\t\tfunction(form, refreshPage) {\n\t\t\t\t\tvar Util = Liferay.Util;\n\n\t\t\t\t\tform = AUI.$(form);\n\n\t\t\t\t\tvar commentButtonList = form.find('.btn-comment');\n\n\t\t\t\t\tvar cmd = form.fm('");
            out.print( randomNamespace );
            out.print( Constants.CMD );
            out.write("').val();\n\n\t\t\t\t\tvar dataType = cmd === '");
            out.print( Constants.ADD );
            out.write("' || cmd === '");
            out.print( Constants.UPDATE );
            out.write("' ? 'json' : null;\n\n\t\t\t\t\tform.ajaxSubmit(\n\t\t\t\t\t\t{\n\t\t\t\t\t\t\tdata: {\n\t\t\t\t\t\t\t\tdoAsUserId: themeDisplay.getDoAsUserIdEncoded()\n\t\t\t\t\t\t\t},\n\t\t\t\t\t\t\tbeforeSubmit: function() {\n\t\t\t\t\t\t\t\tUtil.toggleDisabled(commentButtonList, true);\n\t\t\t\t\t\t\t},\n\t\t\t\t\t\t\tdataType: dataType,\n\t\t\t\t\t\t\tcomplete: function() {\n\t\t\t\t\t\t\t\tUtil.toggleDisabled(commentButtonList, false);\n\t\t\t\t\t\t\t},\n\t\t\t\t\t\t\terror: function() {\n\t\t\t\t\t\t\t\t");
            out.print( randomNamespace );
            out.write("showStatusMessage(\n\t\t\t\t\t\t\t\t\t{\n\t\t\t\t\t\t\t\t\t\tid: '");
            out.print( randomNamespace );
            out.write("',\n\t\t\t\t\t\t\t\t\t\tmessage: '");
            out.print( UnicodeLanguageUtil.get(resourceBundle, "your-request-failed-to-complete") );
            out.write("',\n\t\t\t\t\t\t\t\t\t\ttitle: '");
            out.print( UnicodeLanguageUtil.get(resourceBundle, "error") );
            out.write("',\n\t\t\t\t\t\t\t\t\t\ttype: 'danger'\n\t\t\t\t\t\t\t\t\t}\n\t\t\t\t\t\t\t\t);\n\t\t\t\t\t\t\t},\n\t\t\t\t\t\t\tsuccess: function(response) {\n\t\t\t\t\t\t\t\tvar exception = response.exception;\n\n\t\t\t\t\t\t\t\tif (!exception) {\n\t\t\t\t\t\t\t\t\tLiferay.onceAfter(\n\t\t\t\t\t\t\t\t\t\t'");
            out.print( portletDisplay.getId() );
            out.write(":messagePosted',\n\t\t\t\t\t\t\t\t\t\tfunction(event) {\n\t\t\t\t\t\t\t\t\t\t\t");
            out.print( randomNamespace );
            out.write("onMessagePosted(response, refreshPage);\n\t\t\t\t\t\t\t\t\t\t}\n\t\t\t\t\t\t\t\t\t);\n\n\t\t\t\t\t\t\t\t\tLiferay.fire('");
            out.print( portletDisplay.getId() );
            out.write(":messagePosted', response);\n\t\t\t\t\t\t\t\t}\n\t\t\t\t\t\t\t\telse {\n\t\t\t\t\t\t\t\t\tvar errorKey = '");
            out.print( UnicodeLanguageUtil.get(resourceBundle, "your-request-failed-to-complete") );
            out.write("';\n\n\t\t\t\t\t\t\t\t\tif (exception.indexOf('DiscussionMaxCommentsException') > -1) {\n\t\t\t\t\t\t\t\t\t\terrorKey = '");
            out.print( UnicodeLanguageUtil.get(resourceBundle, "maximum-number-of-comments-has-been-reached") );
            out.write("';\n\t\t\t\t\t\t\t\t\t}\n\t\t\t\t\t\t\t\t\telse if (exception.indexOf('MessageBodyException') > -1) {\n\t\t\t\t\t\t\t\t\t\terrorKey = '");
            out.print( UnicodeLanguageUtil.get(resourceBundle, "please-enter-a-valid-message") );
            out.write("';\n\t\t\t\t\t\t\t\t\t}\n\t\t\t\t\t\t\t\t\telse if (exception.indexOf('NoSuchMessageException') > -1) {\n\t\t\t\t\t\t\t\t\t\terrorKey = '");
            out.print( UnicodeLanguageUtil.get(resourceBundle, "the-message-could-not-be-found") );
            out.write("';\n\t\t\t\t\t\t\t\t\t}\n\t\t\t\t\t\t\t\t\telse if (exception.indexOf('PrincipalException') > -1) {\n\t\t\t\t\t\t\t\t\t\terrorKey = '");
            out.print( UnicodeLanguageUtil.get(resourceBundle, "you-do-not-have-the-required-permissions") );
            out.write("';\n\t\t\t\t\t\t\t\t\t}\n\t\t\t\t\t\t\t\t\telse if (exception.indexOf('RequiredMessageException') > -1) {\n\t\t\t\t\t\t\t\t\t\terrorKey = '");
            out.print( UnicodeLanguageUtil.get(resourceBundle, "you-cannot-delete-a-root-message-that-has-more-than-one-immediate-reply") );
            out.write("';\n\t\t\t\t\t\t\t\t\t}\n\n\t\t\t\t\t\t\t\t\t");
            out.print( randomNamespace );
            out.write("showStatusMessage(\n\t\t\t\t\t\t\t\t\t\t{\n\t\t\t\t\t\t\t\t\t\t\tid: '");
            out.print( randomNamespace );
            out.write("',\n\t\t\t\t\t\t\t\t\t\t\tmessage: errorKey,\n\t\t\t\t\t\t\t\t\t\t\ttitle: '");
            out.print( UnicodeLanguageUtil.get(resourceBundle, "error") );
            out.write("',\n\t\t\t\t\t\t\t\t\t\t\ttype: 'danger'\n\t\t\t\t\t\t\t\t\t\t}\n\t\t\t\t\t\t\t\t\t);\n\t\t\t\t\t\t\t\t}\n\t\t\t\t\t\t\t}\n\t\t\t\t\t\t}\n\t\t\t\t\t);\n\t\t\t\t}\n\t\t\t);\n\n\t\t\tLiferay.provide(\n\t\t\t\twindow,\n\t\t\t\t'");
            out.print( randomNamespace );
            out.write("showEl',\n\t\t\t\tfunction(elId) {\n\t\t\t\t\tAUI.$('#' + elId).css('display', '');\n\t\t\t\t}\n\t\t\t);\n\n\t\t\tLiferay.provide(\n\t\t\t\twindow,\n\t\t\t\t'");
            out.print( randomNamespace );
            out.write("showEditor',\n\t\t\t\tfunction(formId, options) {\n\t\t\t\t\tif (!window['");
            out.print( namespace );
            out.write("' + options.name]) {\n\n\t\t\t\t\t\t");

						String editorURL = GetterUtil.getString(request.getAttribute("liferay-comment:discussion:editorURL"));

						editorURL = HttpUtil.addParameter(editorURL, "namespace", namespace);
						
            out.write("\n\n\t\t\t\t\t\t$.ajax(\n\t\t\t\t\t\t\t'");
            out.print( editorURL );
            out.write("',\n\t\t\t\t\t\t\t{\n\t\t\t\t\t\t\t\tdata: Liferay.Util.ns('");
            out.print( namespace );
            out.write("', options),\n\t\t\t\t\t\t\t\terror: function() {\n\t\t\t\t\t\t\t\t\t");
            out.print( randomNamespace );
            out.write("showStatusMessage(\n\t\t\t\t\t\t\t\t\t\t{\n\t\t\t\t\t\t\t\t\t\t\tid: ");
            out.print( randomNamespace );
            out.write(",\n\t\t\t\t\t\t\t\t\t\t\tmessage: '");
            out.print( UnicodeLanguageUtil.get(resourceBundle, "your-request-failed-to-complete") );
            out.write("',\n\t\t\t\t\t\t\t\t\t\t\ttype: 'danger'\n\t\t\t\t\t\t\t\t\t\t}\n\t\t\t\t\t\t\t\t\t);\n\t\t\t\t\t\t\t\t},\n\t\t\t\t\t\t\t\tsuccess: function(data) {\n\t\t\t\t\t\t\t\t\tvar editorWrapper = $('#' + formId + ' .editor-wrapper');\n\n\t\t\t\t\t\t\t\t\teditorWrapper.html(data);\n\n\t\t\t\t\t\t\t\t\tLiferay.Util.toggleDisabled('#' + options.name.replace('Body', 'Button'), options.contents === '');\n\n\t\t\t\t\t\t\t\t\t");
            out.print( randomNamespace );
            out.write("showEl(formId);\n\t\t\t\t\t\t\t\t}\n\t\t\t\t\t\t\t}\n\t\t\t\t\t\t);\n\t\t\t\t\t}\n\t\t\t\t}\n\t\t\t);\n\n\t\t\tLiferay.provide(\n\t\t\t\twindow,\n\t\t\t\t'");
            out.print( randomNamespace );
            out.write("showPostReplyEditor',\n\t\t\t\tfunction(index) {\n\t\t\t\t\t");
            out.print( randomNamespace );
            out.write("showEditor(\n\t\t\t\t\t\t'");
            out.print( namespace + randomNamespace );
            out.write("' + 'postReplyForm' + index,\n\t\t\t\t\t\t{\n\t\t\t\t\t\t\tname: '");
            out.print( randomNamespace );
            out.write("' + 'postReplyBody' + index,\n\t\t\t\t\t\t\tonChangeMethod: '");
            out.print( randomNamespace );
            out.write("' + index + 'ReplyOnChange',\n\t\t\t\t\t\t\tplaceholder: 'type-your-comment-here'\n\t\t\t\t\t\t}\n\t\t\t\t\t);\n\n\t\t\t\t\t");
            out.print( randomNamespace );
            out.write("hideEditor(\n\t\t\t\t\t\t'");
            out.print( randomNamespace );
            out.write("' + 'editReplyBody' + index,\n\t\t\t\t\t\t'");
            out.print( namespace + randomNamespace );
            out.write("' + 'editForm' + index\n\t\t\t\t\t);\n\n\t\t\t\t\t");
            out.print( randomNamespace );
            out.write("showEl('");
            out.print( namespace + randomNamespace );
            out.write("' + 'discussionMessage' + index);\n\t\t\t\t}\n\t\t\t);\n\n\t\t\twindow.");
            out.print( randomNamespace );
            out.write("showStatusMessage = Liferay.lazyLoad(\n\t\t\t\t'frontend-js-web/liferay/toast/commands/OpenToast.es',\n\t\t\t\tfunction(toastCommands, data) {\n\t\t\t\t\ttoastCommands.openToast(data);\n\t\t\t\t}\n\t\t\t);\n\n\t\t\tLiferay.provide(\n\t\t\t\twindow,\n\t\t\t\t'");
            out.print( randomNamespace );
            out.write("showEditReplyEditor',\n\t\t\t\tfunction(index) {\n\t\t\t\t\tvar discussionId = '");
            out.print( namespace + randomNamespace );
            out.write("' + 'discussionMessage' + index;\n\n\t\t\t\t\tvar contents = $('#' + discussionId).html();\n\n\t\t\t\t\t");
            out.print( randomNamespace );
            out.write("showEditor(\n\t\t\t\t\t\t'");
            out.print( namespace + randomNamespace );
            out.write("' + 'editForm' + index,\n\t\t\t\t\t\t{\n\t\t\t\t\t\t\tcontents: contents,\n\t\t\t\t\t\t\tname: '");
            out.print( randomNamespace );
            out.write("' + 'editReplyBody' + index,\n\t\t\t\t\t\t\tonChangeMethod: '");
            out.print( randomNamespace );
            out.write("' + index + 'EditOnChange'\n\t\t\t\t\t\t}\n\t\t\t\t\t);\n\n\t\t\t\t\t");
            out.print( randomNamespace );
            out.write("hideEditor(\n\t\t\t\t\t\t'");
            out.print( randomNamespace );
            out.write("' + 'postReplyBody' + index,\n\t\t\t\t\t\t'");
            out.print( namespace + randomNamespace );
            out.write("' + 'postReplyForm' + index\n\t\t\t\t\t);\n\n\t\t\t\t\t");
            out.print( randomNamespace );
            out.write("hideEl(discussionId);\n\t\t\t\t}\n\t\t\t);\n\n\t\t\tLiferay.provide(\n\t\t\t\twindow,\n\t\t\t\t'");
            out.print( randomNamespace );
            out.write("subscribeToComments',\n\t\t\t\tfunction(subscribe) {\n\t\t\t\t\tvar form = AUI.$('#");
            out.print( namespace );
            out.print( HtmlUtil.escapeJS(discussionTaglibHelper.getFormName()) );
            out.write("');\n\n\t\t\t\t\tvar cmd = '");
            out.print( Constants.UNSUBSCRIBE_FROM_COMMENTS );
            out.write("';\n\n\t\t\t\t\tif (subscribe) {\n\t\t\t\t\t\tcmd = '");
            out.print( Constants.SUBSCRIBE_TO_COMMENTS );
            out.write("';\n\t\t\t\t\t}\n\n\t\t\t\t\tform.fm('");
            out.print( randomNamespace );
            out.print( Constants.CMD );
            out.write("').val(cmd);\n\n\t\t\t\t\t");
            out.print( namespace );
            out.write("sendMessage(form);\n\t\t\t\t}\n\t\t\t);\n\n\t\t\tLiferay.provide(\n\t\t\t\twindow,\n\t\t\t\t'");
            out.print( randomNamespace );
            out.write("updateMessage',\n\t\t\t\tfunction(i, pending) {\n\t\t\t\t\tvar form = AUI.$('#");
            out.print( namespace );
            out.print( HtmlUtil.escapeJS(discussionTaglibHelper.getFormName()) );
            out.write("');\n\n\t\t\t\t\tvar editorInstance = window['");
            out.print( namespace + randomNamespace );
            out.write("editReplyBody' + i];\n\n\t\t\t\t\tvar commentId = form.fm('commentId' + i).val();\n\n\t\t\t\t\tif (pending) {\n\t\t\t\t\t\tform.fm('workflowAction').val('");
            out.print( WorkflowConstants.ACTION_SAVE_DRAFT );
            out.write("');\n\t\t\t\t\t}\n\n\t\t\t\t\tform.fm('");
            out.print( randomNamespace );
            out.print( Constants.CMD );
            out.write("').val('");
            out.print( Constants.UPDATE );
            out.write("');\n\t\t\t\t\tform.fm('commentId').val(commentId);\n\t\t\t\t\tform.fm('body').val(editorInstance.getHTML());\n\n\t\t\t\t\t");
            out.print( namespace );
            out.write("sendMessage(form);\n\n\t\t\t\t\teditorInstance.dispose();\n\t\t\t\t}\n\t\t\t);\n\n\t\t\t");

			String messageId = ParamUtil.getString(request, "messageId");
			
            out.write("\n\n\t\t\t");
            //  c:if
            com.liferay.taglib.core.IfTag _jspx_th_c_if_9 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
            _jspx_th_c_if_9.setPageContext(_jspx_page_context);
            _jspx_th_c_if_9.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_0);
            _jspx_th_c_if_9.setTest( Validator.isNotNull(messageId) );
            int _jspx_eval_c_if_9 = _jspx_th_c_if_9.doStartTag();
            if (_jspx_eval_c_if_9 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\t\t\t\t");
              out.print( randomNamespace );
              out.write("scrollIntoView(");
              out.print( messageId );
              out.write(");\n\t\t\t");
            }
            if (_jspx_th_c_if_9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_9);
              return;
            }
            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_9);
            out.write("\n\t\t");
            int evalDoAfterBody = _jspx_th_aui_script_0.doAfterBody();
            if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
              break;
          } while (true);
          if (_jspx_eval_aui_script_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
            out = _jspx_page_context.popBody();
        }
        if (_jspx_th_aui_script_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_aui_script_use.reuse(_jspx_th_aui_script_0);
          return;
        }
        _jspx_tagPool_aui_script_use.reuse(_jspx_th_aui_script_0);
        out.write("\n\n\t\t");
        //  aui:script
        com.liferay.taglib.aui.ScriptTag _jspx_th_aui_script_1 = (com.liferay.taglib.aui.ScriptTag) _jspx_tagPool_aui_script_sandbox.get(com.liferay.taglib.aui.ScriptTag.class);
        _jspx_th_aui_script_1.setPageContext(_jspx_page_context);
        _jspx_th_aui_script_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_1);
        _jspx_th_aui_script_1.setSandbox( true );
        int _jspx_eval_aui_script_1 = _jspx_th_aui_script_1.doStartTag();
        if (_jspx_eval_aui_script_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          if (_jspx_eval_aui_script_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
            out = _jspx_page_context.pushBody();
            _jspx_th_aui_script_1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
            _jspx_th_aui_script_1.doInitBody();
          }
          do {
            out.write("\n\t\t\t$('#");
            out.print( namespace );
            out.write("moreComments').on(\n\t\t\t\t'click',\n\t\t\t\tfunction(event) {\n\t\t\t\t\tvar form = $('#");
            out.print( namespace );
            out.print( HtmlUtil.escapeJS(discussionTaglibHelper.getFormName()) );
            out.write("');\n\n\t\t\t\t\tvar data = Liferay.Util.ns(\n\t\t\t\t\t\t'");
            out.print( namespace );
            out.write("',\n\t\t\t\t\t\t{\n\t\t\t\t\t\t\tclassName: '");
            out.print( discussionTaglibHelper.getClassName() );
            out.write("',\n\t\t\t\t\t\t\tclassPK: ");
            out.print( discussionTaglibHelper.getClassPK() );
            out.write(",\n\t\t\t\t\t\t\thideControls: '");
            out.print( discussionTaglibHelper.isHideControls() );
            out.write("',\n\t\t\t\t\t\t\tindex: form.fm('index').val(),\n\t\t\t\t\t\t\trandomNamespace: '");
            out.print( randomNamespace );
            out.write("',\n\t\t\t\t\t\t\tratingsEnabled: '");
            out.print( discussionTaglibHelper.isRatingsEnabled() );
            out.write("',\n\t\t\t\t\t\t\trootIndexPage: form.fm('rootIndexPage').val(),\n\t\t\t\t\t\t\tuserId: '");
            out.print( discussionTaglibHelper.getUserId() );
            out.write("'\n\t\t\t\t\t\t}\n\t\t\t\t\t);\n\n\t\t\t\t\t");

					String paginationURL = HttpUtil.addParameter(discussionTaglibHelper.getPaginationURL(), "namespace", namespace);

					paginationURL = HttpUtil.addParameter(paginationURL, "skipEditorLoading", "true");
					
            out.write("\n\n\t\t\t\t\t$.ajax(\n\t\t\t\t\t\t'");
            out.print( paginationURL );
            out.write("',\n\t\t\t\t\t\t{\n\t\t\t\t\t\t\tdata: data,\n\t\t\t\t\t\t\terror: function() {\n\t\t\t\t\t\t\t\t");
            out.print( randomNamespace );
            out.write("showStatusMessage(\n\t\t\t\t\t\t\t\t\t{\n\t\t\t\t\t\t\t\t\t\tid: ");
            out.print( randomNamespace );
            out.write(",\n\t\t\t\t\t\t\t\t\t\tmessage: '");
            out.print( UnicodeLanguageUtil.get(resourceBundle, "your-request-failed-to-complete") );
            out.write("',\n\t\t\t\t\t\t\t\t\t\ttitle: '");
            out.print( UnicodeLanguageUtil.get(resourceBundle, "error") );
            out.write("',\n\t\t\t\t\t\t\t\t\t\ttype: 'danger'\n\t\t\t\t\t\t\t\t\t}\n\t\t\t\t\t\t\t\t);\n\t\t\t\t\t\t\t},\n\t\t\t\t\t\t\tsuccess: function(data) {\n\t\t\t\t\t\t\t\t$('#");
            out.print( namespace );
            out.write("moreCommentsPage').append(data);\n\t\t\t\t\t\t\t}\n\t\t\t\t\t\t}\n\t\t\t\t\t);\n\t\t\t\t}\n\t\t\t);\n\t\t");
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
        out.write("\n\n\t\t");
        //  aui:script
        com.liferay.taglib.aui.ScriptTag _jspx_th_aui_script_2 = (com.liferay.taglib.aui.ScriptTag) _jspx_tagPool_aui_script_use.get(com.liferay.taglib.aui.ScriptTag.class);
        _jspx_th_aui_script_2.setPageContext(_jspx_page_context);
        _jspx_th_aui_script_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_1);
        _jspx_th_aui_script_2.setUse("aui-popover,event-outside");
        int _jspx_eval_aui_script_2 = _jspx_th_aui_script_2.doStartTag();
        if (_jspx_eval_aui_script_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          if (_jspx_eval_aui_script_2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
            out = _jspx_page_context.pushBody();
            _jspx_th_aui_script_2.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
            _jspx_th_aui_script_2.doInitBody();
          }
          do {
            out.write("\n\t\t\tvar discussionContainer = A.one('#");
            out.print( namespace );
            out.write("discussionContainer');\n\n\t\t\tvar popover = new A.Popover(\n\t\t\t\t{\n\t\t\t\t\tconstrain: true,\n\t\t\t\t\tcssClass: 'lfr-discussion-reply',\n\t\t\t\t\tposition: 'top',\n\t\t\t\t\tvisible: false,\n\t\t\t\t\twidth: 400,\n\t\t\t\t\tzIndex: Liferay.zIndex.OVERLAY\n\t\t\t\t}\n\t\t\t).render(discussionContainer);\n\n\t\t\tvar handle;\n\n\t\t\tvar boundingBox = popover.get('boundingBox');\n\n\t\t\tdiscussionContainer.delegate(\n\t\t\t\t'click',\n\t\t\t\tfunction(event) {\n\t\t\t\t\tevent.stopPropagation();\n\n\t\t\t\t\tif (handle) {\n\t\t\t\t\t\thandle.detach();\n\n\t\t\t\t\t\thandle = null;\n\t\t\t\t\t}\n\n\t\t\t\t\thandle = boundingBox.once('clickoutside', popover.hide, popover);\n\n\t\t\t\t\tpopover.hide();\n\n\t\t\t\t\tvar currentTarget = event.currentTarget;\n\n\t\t\t\t\tpopover.set('align.node', currentTarget);\n\t\t\t\t\tpopover.set('bodyContent', currentTarget.attr('data-metaData'));\n\t\t\t\t\tpopover.set('headerContent', currentTarget.attr('data-title'));\n\n\t\t\t\t\tpopover.show();\n\t\t\t\t},\n\t\t\t\t'.lfr-discussion-parent-link'\n\t\t\t);\n\t\t");
            int evalDoAfterBody = _jspx_th_aui_script_2.doAfterBody();
            if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
              break;
          } while (true);
          if (_jspx_eval_aui_script_2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
            out = _jspx_page_context.popBody();
        }
        if (_jspx_th_aui_script_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_aui_script_use.reuse(_jspx_th_aui_script_2);
          return;
        }
        _jspx_tagPool_aui_script_use.reuse(_jspx_th_aui_script_2);
        out.write('\n');
        out.write('	');
      }
      if (_jspx_th_c_if_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_1);
        return;
      }
      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_1);
      out.write("\n</section>");
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
    _jspx_th_liferay$1ui_message_0.setKey("maximum-number-of-comments-has-been-reached");
    int _jspx_eval_liferay$1ui_message_0 = _jspx_th_liferay$1ui_message_0.doStartTag();
    if (_jspx_th_liferay$1ui_message_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_0);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_0);
    return false;
  }

  private boolean _jspx_meth_aui_input_6(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_form_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:input
    com.liferay.taglib.aui.InputTag _jspx_th_aui_input_6 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
    _jspx_th_aui_input_6.setPageContext(_jspx_page_context);
    _jspx_th_aui_input_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
    _jspx_th_aui_input_6.setName("commentId");
    _jspx_th_aui_input_6.setType("hidden");
    int _jspx_eval_aui_input_6 = _jspx_th_aui_input_6.doStartTag();
    if (_jspx_th_aui_input_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_input_type_name_nobody.reuse(_jspx_th_aui_input_6);
      return true;
    }
    _jspx_tagPool_aui_input_type_name_nobody.reuse(_jspx_th_aui_input_6);
    return false;
  }

  private boolean _jspx_meth_aui_input_7(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_form_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:input
    com.liferay.taglib.aui.InputTag _jspx_th_aui_input_7 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
    _jspx_th_aui_input_7.setPageContext(_jspx_page_context);
    _jspx_th_aui_input_7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
    _jspx_th_aui_input_7.setName("parentCommentId");
    _jspx_th_aui_input_7.setType("hidden");
    int _jspx_eval_aui_input_7 = _jspx_th_aui_input_7.doStartTag();
    if (_jspx_th_aui_input_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_input_type_name_nobody.reuse(_jspx_th_aui_input_7);
      return true;
    }
    _jspx_tagPool_aui_input_type_name_nobody.reuse(_jspx_th_aui_input_7);
    return false;
  }

  private boolean _jspx_meth_aui_input_8(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_form_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:input
    com.liferay.taglib.aui.InputTag _jspx_th_aui_input_8 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
    _jspx_th_aui_input_8.setPageContext(_jspx_page_context);
    _jspx_th_aui_input_8.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
    _jspx_th_aui_input_8.setName("body");
    _jspx_th_aui_input_8.setType("hidden");
    int _jspx_eval_aui_input_8 = _jspx_th_aui_input_8.doStartTag();
    if (_jspx_th_aui_input_8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_input_type_name_nobody.reuse(_jspx_th_aui_input_8);
      return true;
    }
    _jspx_tagPool_aui_input_type_name_nobody.reuse(_jspx_th_aui_input_8);
    return false;
  }

  private boolean _jspx_meth_aui_input_13(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_5, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:input
    com.liferay.taglib.aui.InputTag _jspx_th_aui_input_13 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
    _jspx_th_aui_input_13.setPageContext(_jspx_page_context);
    _jspx_th_aui_input_13.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_5);
    _jspx_th_aui_input_13.setName("emailAddress");
    _jspx_th_aui_input_13.setType("hidden");
    int _jspx_eval_aui_input_13 = _jspx_th_aui_input_13.doStartTag();
    if (_jspx_th_aui_input_13.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_input_type_name_nobody.reuse(_jspx_th_aui_input_13);
      return true;
    }
    _jspx_tagPool_aui_input_type_name_nobody.reuse(_jspx_th_aui_input_13);
    return false;
  }

  private boolean _jspx_meth_aui_input_14(javax.servlet.jsp.tagext.JspTag _jspx_th_c_when_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:input
    com.liferay.taglib.aui.InputTag _jspx_th_aui_input_14 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
    _jspx_th_aui_input_14.setPageContext(_jspx_page_context);
    _jspx_th_aui_input_14.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_1);
    _jspx_th_aui_input_14.setName("postReplyBody0");
    _jspx_th_aui_input_14.setType("hidden");
    int _jspx_eval_aui_input_14 = _jspx_th_aui_input_14.doStartTag();
    if (_jspx_th_aui_input_14.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_input_type_name_nobody.reuse(_jspx_th_aui_input_14);
      return true;
    }
    _jspx_tagPool_aui_input_type_name_nobody.reuse(_jspx_th_aui_input_14);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_1(javax.servlet.jsp.tagext.JspTag _jspx_th_c_when_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_1 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_1.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_2);
    _jspx_th_liferay$1ui_message_1.setKey("comments-are-read-only-in-staging");
    int _jspx_eval_liferay$1ui_message_1 = _jspx_th_liferay$1ui_message_1.doStartTag();
    if (_jspx_th_liferay$1ui_message_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_1);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_1);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_2(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_8, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_2 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_2.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_8);
    _jspx_th_liferay$1ui_message_2.setKey("more-comments");
    int _jspx_eval_liferay$1ui_message_2 = _jspx_th_liferay$1ui_message_2.doStartTag();
    if (_jspx_th_liferay$1ui_message_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_2);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_2);
    return false;
  }
}
