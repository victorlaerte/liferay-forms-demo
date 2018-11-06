package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.liferay.frontend.editor.ckeditor.web.internal.constants.CKEditorConstants;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.editor.configuration.EditorOptions;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.servlet.PortalWebResourceConstants;
import com.liferay.portal.kernel.servlet.PortalWebResourcesUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.SessionClicks;
import com.liferay.portal.kernel.util.TextFormatter;
import com.liferay.portal.kernel.util.URLCodec;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.taglib.aui.AUIUtil;
import java.util.Map;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

public final class ckeditor_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {


public String marshallParams(Map<String, String> params) {
	if (params == null) {
		return StringPool.BLANK;
	}

	StringBundler sb = new StringBundler(4 * params.size());

	for (Map.Entry<String, String> configParam : params.entrySet()) {
		sb.append(StringPool.AMPERSAND);
		sb.append(configParam.getKey());
		sb.append(StringPool.EQUAL);
		sb.append(URLCodec.encodeURL(configParam.getValue()));
	}

	return sb.toString();
}

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(1);
    _jspx_dependants.add("/init.jsp");
  }

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1util_dynamic$1include_key_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_namespace_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1theme_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_if_test;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1util_buffer_var;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_choose;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1frontend_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_script_use;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1editor_resources_inlineEditSaveURL_inlineEdit_editorName_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_otherwise;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_when_test;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_liferay$1util_dynamic$1include_key_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_namespace_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1theme_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_if_test = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1util_buffer_var = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_choose = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1frontend_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_script_use = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1editor_resources_inlineEditSaveURL_inlineEdit_editorName_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_otherwise = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_when_test = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_liferay$1util_dynamic$1include_key_nobody.release();
    _jspx_tagPool_portlet_namespace_nobody.release();
    _jspx_tagPool_liferay$1theme_defineObjects_nobody.release();
    _jspx_tagPool_c_if_test.release();
    _jspx_tagPool_liferay$1util_buffer_var.release();
    _jspx_tagPool_c_choose.release();
    _jspx_tagPool_liferay$1frontend_defineObjects_nobody.release();
    _jspx_tagPool_aui_script_use.release();
    _jspx_tagPool_liferay$1editor_resources_inlineEditSaveURL_inlineEdit_editorName_nobody.release();
    _jspx_tagPool_c_otherwise.release();
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
      out.write("\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
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

PortletRequest portletRequest = (PortletRequest)request.getAttribute(JavaConstants.JAVAX_PORTLET_REQUEST);

PortletResponse portletResponse = (PortletResponse)request.getAttribute(JavaConstants.JAVAX_PORTLET_RESPONSE);

String namespace = AUIUtil.getNamespace(portletRequest, portletResponse);

if (Validator.isNull(namespace)) {
	namespace = AUIUtil.getNamespace(request);
}

      out.write('\n');
      out.write('\n');

String portletId = portletDisplay.getRootPortletId();

boolean autoCreate = GetterUtil.getBoolean((String)request.getAttribute(CKEditorConstants.ATTRIBUTE_NAMESPACE + ":autoCreate"));
String contents = (String)request.getAttribute(CKEditorConstants.ATTRIBUTE_NAMESPACE + ":contents");
String cssClass = GetterUtil.getString((String)request.getAttribute(CKEditorConstants.ATTRIBUTE_NAMESPACE + ":cssClass"));
Map<String, Object> data = (Map<String, Object>)request.getAttribute(CKEditorConstants.ATTRIBUTE_NAMESPACE + ":data");
String editorName = (String)request.getAttribute(CKEditorConstants.ATTRIBUTE_NAMESPACE + ":editorName");
String initMethod = (String)request.getAttribute(CKEditorConstants.ATTRIBUTE_NAMESPACE + ":initMethod");
boolean inlineEdit = GetterUtil.getBoolean((String)request.getAttribute(CKEditorConstants.ATTRIBUTE_NAMESPACE + ":inlineEdit"));
String inlineEditSaveURL = GetterUtil.getString((String)request.getAttribute(CKEditorConstants.ATTRIBUTE_NAMESPACE + ":inlineEditSaveURL"));
String name = GetterUtil.getString((String)request.getAttribute(CKEditorConstants.ATTRIBUTE_NAMESPACE + ":name"));

String onBlurMethod = (String)request.getAttribute(CKEditorConstants.ATTRIBUTE_NAMESPACE + ":onBlurMethod");

if (Validator.isNotNull(onBlurMethod)) {
	onBlurMethod = namespace + onBlurMethod;
}

String onChangeMethod = (String)request.getAttribute(CKEditorConstants.ATTRIBUTE_NAMESPACE + ":onChangeMethod");

if (Validator.isNotNull(onChangeMethod)) {
	onChangeMethod = namespace + onChangeMethod;
}

String onFocusMethod = (String)request.getAttribute(CKEditorConstants.ATTRIBUTE_NAMESPACE + ":onFocusMethod");

if (Validator.isNotNull(onFocusMethod)) {
	onFocusMethod = namespace + onFocusMethod;
}

String onInitMethod = (String)request.getAttribute(CKEditorConstants.ATTRIBUTE_NAMESPACE + ":onInitMethod");

if (Validator.isNotNull(onInitMethod)) {
	onInitMethod = namespace + onInitMethod;
}

boolean skipEditorLoading = GetterUtil.getBoolean((String)request.getAttribute(CKEditorConstants.ATTRIBUTE_NAMESPACE + ":skipEditorLoading"));
String toolbarSet = (String)request.getAttribute(CKEditorConstants.ATTRIBUTE_NAMESPACE + ":toolbarSet");

if (!inlineEdit) {
	name = namespace + name;
}

JSONObject editorConfigJSONObject = null;

if (data != null) {
	editorConfigJSONObject = (JSONObject)data.get("editorConfig");
}

EditorOptions editorOptions = null;

if (data != null) {
	editorOptions = (EditorOptions)data.get("editorOptions");
}

Map<String, Object> editorOptionsDynamicAttributes = null;

if (editorOptions != null) {
	editorOptionsDynamicAttributes = editorOptions.getDynamicAttributes();
}

      out.write('\n');
      out.write('\n');
      //  c:if
      com.liferay.taglib.core.IfTag _jspx_th_c_if_0 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
      _jspx_th_c_if_0.setPageContext(_jspx_page_context);
      _jspx_th_c_if_0.setParent(null);
      _jspx_th_c_if_0.setTest( !skipEditorLoading );
      int _jspx_eval_c_if_0 = _jspx_th_c_if_0.doStartTag();
      if (_jspx_eval_c_if_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        out.write('\n');
        out.write('	');
        //  liferay-editor:resources
        com.liferay.frontend.editor.taglib.servlet.taglib.ResourcesTag _jspx_th_liferay$1editor_resources_0 = (com.liferay.frontend.editor.taglib.servlet.taglib.ResourcesTag) _jspx_tagPool_liferay$1editor_resources_inlineEditSaveURL_inlineEdit_editorName_nobody.get(com.liferay.frontend.editor.taglib.servlet.taglib.ResourcesTag.class);
        _jspx_th_liferay$1editor_resources_0.setPageContext(_jspx_page_context);
        _jspx_th_liferay$1editor_resources_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_0);
        _jspx_th_liferay$1editor_resources_0.setEditorName( editorName );
        _jspx_th_liferay$1editor_resources_0.setInlineEdit( inlineEdit );
        _jspx_th_liferay$1editor_resources_0.setInlineEditSaveURL( inlineEditSaveURL );
        int _jspx_eval_liferay$1editor_resources_0 = _jspx_th_liferay$1editor_resources_0.doStartTag();
        if (_jspx_th_liferay$1editor_resources_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_liferay$1editor_resources_inlineEditSaveURL_inlineEdit_editorName_nobody.reuse(_jspx_th_liferay$1editor_resources_0);
          return;
        }
        _jspx_tagPool_liferay$1editor_resources_inlineEditSaveURL_inlineEdit_editorName_nobody.reuse(_jspx_th_liferay$1editor_resources_0);
        out.write('\n');
      }
      if (_jspx_th_c_if_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
        return;
      }
      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
      out.write('\n');
      out.write('\n');

String textareaName = HtmlUtil.escapeAttribute(name);

String modules = "aui-node-base";

if (inlineEdit && Validator.isNotNull(inlineEditSaveURL)) {
	textareaName = textareaName + "_original";

	modules += ",inline-editor-ckeditor";
}

      out.write('\n');
      out.write('\n');
      //  liferay-util:buffer
      com.liferay.taglib.util.BufferTag _jspx_th_liferay$1util_buffer_0 = (com.liferay.taglib.util.BufferTag) _jspx_tagPool_liferay$1util_buffer_var.get(com.liferay.taglib.util.BufferTag.class);
      _jspx_th_liferay$1util_buffer_0.setPageContext(_jspx_page_context);
      _jspx_th_liferay$1util_buffer_0.setParent(null);
      _jspx_th_liferay$1util_buffer_0.setVar("editor");
      int _jspx_eval_liferay$1util_buffer_0 = _jspx_th_liferay$1util_buffer_0.doStartTag();
      if (_jspx_eval_liferay$1util_buffer_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        if (_jspx_eval_liferay$1util_buffer_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
          out = _jspx_page_context.pushBody();
          _jspx_th_liferay$1util_buffer_0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
          _jspx_th_liferay$1util_buffer_0.doInitBody();
        }
        do {
          out.write("\n\t<textarea id=\"");
          out.print( textareaName );
          out.write("\" name=\"");
          out.print( textareaName );
          out.write("\" style=\"display: none;\"></textarea>\n");
          int evalDoAfterBody = _jspx_th_liferay$1util_buffer_0.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
        if (_jspx_eval_liferay$1util_buffer_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
          out = _jspx_page_context.popBody();
      }
      if (_jspx_th_liferay$1util_buffer_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_liferay$1util_buffer_var.reuse(_jspx_th_liferay$1util_buffer_0);
        return;
      }
      _jspx_tagPool_liferay$1util_buffer_var.reuse(_jspx_th_liferay$1util_buffer_0);
      java.lang.String editor = null;
      editor = (java.lang.String) _jspx_page_context.findAttribute("editor");
      out.write("\n\n<div class=\"");
      out.print( HtmlUtil.escapeAttribute(cssClass) );
      out.write("\" id=\"");
      out.print( HtmlUtil.escapeAttribute(name) );
      out.write("Container\">\n\t");
      //  c:if
      com.liferay.taglib.core.IfTag _jspx_th_c_if_1 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
      _jspx_th_c_if_1.setPageContext(_jspx_page_context);
      _jspx_th_c_if_1.setParent(null);
      _jspx_th_c_if_1.setTest( autoCreate );
      int _jspx_eval_c_if_1 = _jspx_th_c_if_1.doStartTag();
      if (_jspx_eval_c_if_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        out.write("\n\t\t");
        out.print( editor );
        out.write('\n');
        out.write('	');
      }
      if (_jspx_th_c_if_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_1);
        return;
      }
      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_1);
      out.write("\n</div>\n\n<script type=\"text/javascript\">\n\tCKEDITOR.disableAutoInline = true;\n\n\tCKEDITOR.dtd.$removeEmpty.i = 0;\n\tCKEDITOR.dtd.$removeEmpty.span = 0;\n</script>\n\n");

name = HtmlUtil.escapeJS(name);

      out.write('\n');
      out.write('\n');
      //  aui:script
      com.liferay.taglib.aui.ScriptTag _jspx_th_aui_script_0 = (com.liferay.taglib.aui.ScriptTag) _jspx_tagPool_aui_script_use.get(com.liferay.taglib.aui.ScriptTag.class);
      _jspx_th_aui_script_0.setPageContext(_jspx_page_context);
      _jspx_th_aui_script_0.setParent(null);
      _jspx_th_aui_script_0.setUse( modules );
      int _jspx_eval_aui_script_0 = _jspx_th_aui_script_0.doStartTag();
      if (_jspx_eval_aui_script_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        if (_jspx_eval_aui_script_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
          out = _jspx_page_context.pushBody();
          _jspx_th_aui_script_0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
          _jspx_th_aui_script_0.doInitBody();
        }
        do {
          out.write("\n\tvar UA = A.UA;\n\n\tvar contents = '");
          out.print( HtmlUtil.escapeJS(contents) );
          out.write("';\n\n\tvar instanceDataReady = false;\n\tvar instancePendingData;\n\n\tvar getInitialContent = function() {\n\t\tvar data;\n\n\t\tif (window['");
          out.print( HtmlUtil.escapeJS(namespace + initMethod) );
          out.write("']) {\n\t\t\tdata = ");
          out.print( HtmlUtil.escapeJS(namespace + initMethod) );
          out.write("();\n\t\t}\n\t\telse {\n\t\t\tdata = '");
          out.print( (contents != null) ? HtmlUtil.escapeJS(contents) : StringPool.BLANK );
          out.write("';\n\t\t}\n\n\t\treturn data;\n\t};\n\n\tvar onLocaleChangedHandler = function(event) {\n\t\tvar contentsLanguage = event.item.getAttribute('data-value');\n\t\tvar contentsLanguageDir = Liferay.Language.direction[contentsLanguage];\n\n\t\tvar nativeEditor = window['");
          out.print( name );
          out.write("'].getNativeEditor();\n\n\t\tnativeEditor.config.contentsLanguage = contentsLanguage;\n\t\tnativeEditor.config.contentsLangDirection = contentsLanguageDir;\n\t};\n\n\tvar eventHandles = [\n\t\tLiferay.on('inputLocalized:localeChanged', onLocaleChangedHandler)\n\t];\n\n\twindow['");
          out.print( name );
          out.write("'] = {\n\t\tcreate: function() {\n\t\t\tif (!window['");
          out.print( name );
          out.write("'].instanceReady) {\n\t\t\t\tvar editorNode = A.Node.create('");
          out.print( HtmlUtil.escapeJS(editor) );
          out.write("');\n\n\t\t\t\tvar editorContainer = A.one('#");
          out.print( name );
          out.write("Container');\n\n\t\t\t\teditorContainer.appendChild(editorNode);\n\n\t\t\t\tcreateEditor();\n\t\t\t}\n\t\t},\n\n\t\tdestroy: function() {\n\t\t\twindow['");
          out.print( name );
          out.write("'].dispose();\n\n\t\t\twindow['");
          out.print( name );
          out.write("'] = null;\n\n\t\t\tLiferay.namespace('EDITORS').ckeditor.removeInstance();\n\t\t},\n\n\t\tdispose: function() {\n\t\t\tvar editor = CKEDITOR.instances['");
          out.print( name );
          out.write("'];\n\n\t\t\tif (editor) {\n\t\t\t\teditor.destroy();\n\n\t\t\t\twindow['");
          out.print( name );
          out.write("'].instanceReady = false;\n\t\t\t}\n\n\t\t\t(new A.EventHandle(eventHandles)).detach();\n\n\t\t\tvar editorEl = document.getElementById('");
          out.print( name );
          out.write("');\n\n\t\t\tif (editorEl) {\n\t\t\t\teditorEl.parentNode.removeChild(editorEl);\n\t\t\t}\n\t\t},\n\n\t\tfocus: function() {\n\t\t\tCKEDITOR.instances['");
          out.print( name );
          out.write("'].focus();\n\t\t},\n\n\t\tgetCkData: function() {\n\t\t\tvar data;\n\n\t\t\tif (!window['");
          out.print( name );
          out.write("'].instanceReady) {\n\t\t\t\tdata = getInitialContent();\n\t\t\t}\n\t\t\telse {\n\t\t\t\tdata = CKEDITOR.instances['");
          out.print( name );
          out.write("'].getData();\n\n\t\t\t\tif (CKEDITOR.env.gecko && (CKEDITOR.tools.trim(data) == '<br />')) {\n\t\t\t\t\tdata = '';\n\t\t\t\t}\n\t\t\t}\n\n\t\t\treturn data;\n\t\t},\n\n\t\tgetHTML: function() {\n\t\t\treturn window['");
          out.print( name );
          out.write("'].getCkData();\n\t\t},\n\n\t\tgetNativeEditor: function() {\n\t\t\treturn CKEDITOR.instances['");
          out.print( name );
          out.write("'];\n\t\t},\n\n\t\tgetText: function() {\n\t\t\tvar data;\n\n\t\t\tif (!window['");
          out.print( name );
          out.write("'].instanceReady) {\n\t\t\t\tdata = getInitialContent();\n\t\t\t}\n\t\t\telse {\n\t\t\t\tvar editor = CKEDITOR.instances['");
          out.print( name );
          out.write("'];\n\n\t\t\t\tdata = editor.editable().getText();\n\t\t\t}\n\n\t\t\treturn data;\n\t\t},\n\n\t\tinstanceReady: false,\n\n\t\t");
          //  c:if
          com.liferay.taglib.core.IfTag _jspx_th_c_if_2 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
          _jspx_th_c_if_2.setPageContext(_jspx_page_context);
          _jspx_th_c_if_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_0);
          _jspx_th_c_if_2.setTest( Validator.isNotNull(onBlurMethod) );
          int _jspx_eval_c_if_2 = _jspx_th_c_if_2.doStartTag();
          if (_jspx_eval_c_if_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t\tonBlurCallback: function() {\n\t\t\t\twindow['");
            out.print( HtmlUtil.escapeJS(onBlurMethod) );
            out.write("'](CKEDITOR.instances['");
            out.print( name );
            out.write("']);\n\t\t\t},\n\t\t");
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
          _jspx_th_c_if_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_0);
          _jspx_th_c_if_3.setTest( Validator.isNotNull(onChangeMethod) );
          int _jspx_eval_c_if_3 = _jspx_th_c_if_3.doStartTag();
          if (_jspx_eval_c_if_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t\tonChangeCallback: function() {\n\t\t\t\tvar ckEditor = CKEDITOR.instances['");
            out.print( name );
            out.write("'];\n\t\t\t\tvar dirty = ckEditor.checkDirty();\n\n\t\t\t\tif (dirty) {\n\t\t\t\t\twindow['");
            out.print( HtmlUtil.escapeJS(onChangeMethod) );
            out.write("'](window['");
            out.print( name );
            out.write("'].getHTML());\n\n\t\t\t\t\tckEditor.resetDirty();\n\t\t\t\t}\n\t\t\t},\n\t\t");
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
          _jspx_th_c_if_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_0);
          _jspx_th_c_if_4.setTest( Validator.isNotNull(onFocusMethod) );
          int _jspx_eval_c_if_4 = _jspx_th_c_if_4.doStartTag();
          if (_jspx_eval_c_if_4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t\tonFocusCallback: function() {\n\t\t\t\twindow['");
            out.print( HtmlUtil.escapeJS(onFocusMethod) );
            out.write("'](CKEDITOR.instances['");
            out.print( name );
            out.write("']);\n\t\t\t},\n\t\t");
          }
          if (_jspx_th_c_if_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_4);
            return;
          }
          _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_4);
          out.write("\n\n\t\tsetHTML: function(value) {\n\t\t\tvar ckEditorInstance = CKEDITOR.instances['");
          out.print( name );
          out.write("'];\n\n\t\t\tvar win = window['");
          out.print( name );
          out.write("'];\n\n\t\t\tvar setHTML = function(data) {\n\t\t\t\tif (instanceDataReady) {\n\t\t\t\t\tckEditorInstance.setData(data);\n\t\t\t\t}\n\t\t\t\telse {\n\t\t\t\t\tinstancePendingData = data;\n\t\t\t\t}\n\n\t\t\t\twin._setStyles();\n\t\t\t};\n\n\t\t\tif (win.instanceReady) {\n\t\t\t\tsetHTML(value);\n\t\t\t}\n\t\t\telse {\n\t\t\t\tcontents = value;\n\t\t\t}\n\t\t}\n\t};\n\n\tvar addAUIClass = function(iframe) {\n\t\tif (iframe) {\n\t\t\tvar iframeWin = iframe.getDOM().contentWindow;\n\n\t\t\tif (iframeWin) {\n\t\t\t\tvar iframeDoc = iframeWin.document.documentElement;\n\n\t\t\t\tA.one(iframeDoc).addClass('aui');\n\t\t\t}\n\t\t}\n\t};\n\n\twindow['");
          out.print( name );
          out.write("']._setStyles = function() {\n\t\tvar ckEditor = A.one('#cke_");
          out.print( name );
          out.write("');\n\n\t\tif (ckEditor) {\n\t\t\tvar iframe = ckEditor.one('iframe');\n\n\t\t\taddAUIClass(iframe);\n\n\t\t\tvar ckePanelDelegate = Liferay.Data['");
          out.print( name );
          out.write("Handle'];\n\n\t\t\tif (!ckePanelDelegate) {\n\t\t\t\tckePanelDelegate = ckEditor.delegate(\n\t\t\t\t\t'click',\n\t\t\t\t\tfunction(event) {\n\t\t\t\t\t\tvar panelFrame = A.one('.cke_combopanel .cke_panel_frame');\n\n\t\t\t\t\t\taddAUIClass(panelFrame);\n\n\t\t\t\t\t\tckePanelDelegate.detach();\n\n\t\t\t\t\t\tLiferay.Data['");
          out.print( name );
          out.write("Handle'] = null;\n\t\t\t\t\t},\n\t\t\t\t\t'.cke_combo'\n\t\t\t\t);\n\n\t\t\t\tLiferay.Data['");
          out.print( name );
          out.write("Handle'] = ckePanelDelegate;\n\t\t\t}\n\t\t}\n\t};\n\n\tLiferay.fire(\n\t\t'editorAPIReady',\n\t\t{\n\t\t\teditor: window['");
          out.print( name );
          out.write("'],\n\t\t\teditorName: '");
          out.print( name );
          out.write("'\n\t\t}\n\t);\n\n\t");
          //  c:if
          com.liferay.taglib.core.IfTag _jspx_th_c_if_5 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
          _jspx_th_c_if_5.setPageContext(_jspx_page_context);
          _jspx_th_c_if_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_0);
          _jspx_th_c_if_5.setTest( inlineEdit && Validator.isNotNull(inlineEditSaveURL) );
          int _jspx_eval_c_if_5 = _jspx_th_c_if_5.doStartTag();
          if (_jspx_eval_c_if_5 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\tvar inlineEditor;\n\n\t\tLiferay.on(\n\t\t\t'toggleControls',\n\t\t\tfunction(event) {\n\t\t\t\tif (event.src === 'ui') {\n\t\t\t\t\tvar ckEditor = CKEDITOR.instances['");
            out.print( name );
            out.write("'];\n\n\t\t\t\t\tif (event.enabled && !ckEditor) {\n\t\t\t\t\t\tcreateEditor();\n\t\t\t\t\t}\n\t\t\t\t\telse if (ckEditor) {\n\t\t\t\t\t\tinlineEditor.destroy();\n\t\t\t\t\t\tckEditor.destroy();\n\n\t\t\t\t\t\tvar editorNode = A.one('#");
            out.print( name );
            out.write("');\n\n\t\t\t\t\t\tif (editorNode) {\n\t\t\t\t\t\t\teditorNode.removeAttribute('contenteditable');\n\t\t\t\t\t\t\teditorNode.removeClass('lfr-editable');\n\t\t\t\t\t\t}\n\t\t\t\t\t}\n\t\t\t\t}\n\t\t\t}\n\t\t);\n\t");
          }
          if (_jspx_th_c_if_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_5);
            return;
          }
          _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_5);
          out.write("\n\n\tvar ckEditorContent;\n\tvar currentToolbarSet;\n\n\tvar initialToolbarSet = '");
          out.print( TextFormatter.format(HtmlUtil.escapeJS(toolbarSet), TextFormatter.M) );
          out.write("';\n\n\tfunction getToolbarSet(toolbarSet) {\n\t\tvar Util = Liferay.Util;\n\n\t\tif (Util.isPhone()) {\n\t\t\ttoolbarSet = 'phone';\n\t\t}\n\t\telse if (Util.isTablet()) {\n\t\t\ttoolbarSet = 'tablet';\n\t\t}\n\n\t\treturn toolbarSet;\n\t}\n\n\tvar afterVal = function() {\n\t\treturn new A.Do.AlterReturn(\n\t\t\t'Return editor content',\n\t\t\twindow['");
          out.print( name );
          out.write("'].getHTML()\n\t\t);\n\t};\n\n\tvar createEditor = function() {\n\t\tvar editorNode = A.one('#");
          out.print( name );
          out.write("');\n\n\t\tif (editorNode) {\n\t\t\teditorNode.attr('contenteditable', true);\n\t\t\teditorNode.addClass('lfr-editable');\n\n\t\t\teventHandles.push(\n\t\t\t\tA.Do.after(afterVal, editorNode, 'val', this)\n\t\t\t);\n\t\t}\n\n\t\tfunction initData() {\n\t\t\tif (!ckEditorContent) {\n\t\t\t\tckEditorContent = getInitialContent();\n\t\t\t}\n\n\t\t\tckEditor.setData(\n\t\t\t\tckEditorContent,\n\t\t\t\tfunction() {\n\t\t\t\t\tckEditor.resetDirty();\n\n\t\t\t\t\tckEditorContent = '';\n\t\t\t\t}\n\t\t\t);\n\n\t\t\twindow['");
          out.print( name );
          out.write("']._setStyles();\n\n\t\t\t");
          //  c:if
          com.liferay.taglib.core.IfTag _jspx_th_c_if_6 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
          _jspx_th_c_if_6.setPageContext(_jspx_page_context);
          _jspx_th_c_if_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_0);
          _jspx_th_c_if_6.setTest( Validator.isNotNull(onInitMethod) );
          int _jspx_eval_c_if_6 = _jspx_th_c_if_6.doStartTag();
          if (_jspx_eval_c_if_6 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t\t\twindow['");
            out.print( HtmlUtil.escapeJS(onInitMethod) );
            out.write("']();\n\t\t\t");
          }
          if (_jspx_th_c_if_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_6);
            return;
          }
          _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_6);
          out.write("\n\n\t\t\twindow['");
          out.print( name );
          out.write("'].instanceReady = true;\n\n\t\t\tLiferay.component(\n\t\t\t\t'");
          out.print( name );
          out.write("',\n\t\t\t\twindow['");
          out.print( name );
          out.write("'],\n\t\t\t\t{\n\t\t\t\t\tportletId: '");
          out.print( portletId );
          out.write("'\n\t\t\t\t}\n\t\t\t);\n\t\t}\n\n\t\tcurrentToolbarSet = getToolbarSet(initialToolbarSet);\n\n\t\tvar defaultConfig = {\n\t\t\tfilebrowserBrowseUrl: '',\n\t\t\tfilebrowserFlashBrowseUrl: '',\n\t\t\tfilebrowserImageBrowseLinkUrl: '',\n\t\t\tfilebrowserImageBrowseUrl: '',\n\t\t\tfilebrowserUploadUrl: null,\n\t\t\ttoolbar: currentToolbarSet\n\t\t};\n\n\t\tvar editorConfig = ");
          out.print( Validator.isNotNull(editorConfigJSONObject) ? editorConfigJSONObject : "{}" );
          out.write(";\n\n\t\tvar config = A.merge(defaultConfig, editorConfig);\n\n\t\tCKEDITOR.");
          out.print( inlineEdit ? "inline" : "replace" );
          out.write('(');
          out.write('\'');
          out.print( name );
          out.write("', config);\n\n\t\tLiferay.on(\n\t\t\t'");
          out.print( name );
          out.write("selectItem',\n\t\t\tfunction(event) {\n\t\t\t\tCKEDITOR.tools.callFunction(event.ckeditorfuncnum, event.value);\n\t\t\t}\n\t\t);\n\n\t\tvar ckEditor = CKEDITOR.instances['");
          out.print( name );
          out.write("'];\n\n\t\t");
          //  liferay-util:dynamic-include
          com.liferay.taglib.util.DynamicIncludeTag _jspx_th_liferay$1util_dynamic$1include_0 = (com.liferay.taglib.util.DynamicIncludeTag) _jspx_tagPool_liferay$1util_dynamic$1include_key_nobody.get(com.liferay.taglib.util.DynamicIncludeTag.class);
          _jspx_th_liferay$1util_dynamic$1include_0.setPageContext(_jspx_page_context);
          _jspx_th_liferay$1util_dynamic$1include_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_0);
          _jspx_th_liferay$1util_dynamic$1include_0.setKey( "com.liferay.frontend.editor.ckeditor.web#" + editorName + "#onEditorCreate" );
          int _jspx_eval_liferay$1util_dynamic$1include_0 = _jspx_th_liferay$1util_dynamic$1include_0.doStartTag();
          if (_jspx_th_liferay$1util_dynamic$1include_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_liferay$1util_dynamic$1include_key_nobody.reuse(_jspx_th_liferay$1util_dynamic$1include_0);
            return;
          }
          _jspx_tagPool_liferay$1util_dynamic$1include_key_nobody.reuse(_jspx_th_liferay$1util_dynamic$1include_0);
          out.write("\n\n\t\tLiferay.namespace('EDITORS').ckeditor.addInstance();\n\n\t\t");
          //  c:if
          com.liferay.taglib.core.IfTag _jspx_th_c_if_7 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
          _jspx_th_c_if_7.setPageContext(_jspx_page_context);
          _jspx_th_c_if_7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_0);
          _jspx_th_c_if_7.setTest( inlineEdit && Validator.isNotNull(inlineEditSaveURL) );
          int _jspx_eval_c_if_7 = _jspx_th_c_if_7.doStartTag();
          if (_jspx_eval_c_if_7 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t\tinlineEditor = new Liferay.CKEditorInline(\n\t\t\t\t{\n\t\t\t\t\teditor: ckEditor,\n\t\t\t\t\teditorName: '");
            out.print( name );
            out.write("',\n\t\t\t\t\tnamespace: '");
            if (_jspx_meth_portlet_namespace_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_7, _jspx_page_context))
              return;
            out.write("',\n\t\t\t\t\tsaveURL: '");
            out.print( inlineEditSaveURL );
            out.write("'\n\t\t\t\t}\n\t\t\t);\n\t\t");
          }
          if (_jspx_th_c_if_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_7);
            return;
          }
          _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_7);
          out.write("\n\n\t\tvar customDataProcessorLoaded = false;\n\n\t\t");

		boolean useCustomDataProcessor = (editorOptionsDynamicAttributes != null) && GetterUtil.getBoolean(editorOptionsDynamicAttributes.get("useCustomDataProcessor"));
		
          out.write("\n\n\t\t");
          //  c:if
          com.liferay.taglib.core.IfTag _jspx_th_c_if_8 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
          _jspx_th_c_if_8.setPageContext(_jspx_page_context);
          _jspx_th_c_if_8.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_0);
          _jspx_th_c_if_8.setTest( useCustomDataProcessor );
          int _jspx_eval_c_if_8 = _jspx_th_c_if_8.doStartTag();
          if (_jspx_eval_c_if_8 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t\tckEditor.on(\n\t\t\t\t'customDataProcessorLoaded',\n\t\t\t\tfunction() {\n\t\t\t\t\tcustomDataProcessorLoaded = true;\n\n\t\t\t\t\tif (instanceReady) {\n\t\t\t\t\t\tinitData();\n\t\t\t\t\t}\n\t\t\t\t}\n\t\t\t);\n\t\t");
          }
          if (_jspx_th_c_if_8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_8);
            return;
          }
          _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_8);
          out.write("\n\n\t\tvar instanceReady = false;\n\n\t\tckEditor.on(\n\t\t\t'instanceReady',\n\t\t\tfunction() {\n\n\t\t\t\t");
          //  c:choose
          com.liferay.taglib.core.ChooseTag _jspx_th_c_choose_0 = (com.liferay.taglib.core.ChooseTag) _jspx_tagPool_c_choose.get(com.liferay.taglib.core.ChooseTag.class);
          _jspx_th_c_choose_0.setPageContext(_jspx_page_context);
          _jspx_th_c_choose_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_0);
          int _jspx_eval_c_choose_0 = _jspx_th_c_choose_0.doStartTag();
          if (_jspx_eval_c_choose_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t\t\t\t");
            //  c:when
            com.liferay.taglib.core.WhenTag _jspx_th_c_when_0 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
            _jspx_th_c_when_0.setPageContext(_jspx_page_context);
            _jspx_th_c_when_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_0);
            _jspx_th_c_when_0.setTest( useCustomDataProcessor );
            int _jspx_eval_c_when_0 = _jspx_th_c_when_0.doStartTag();
            if (_jspx_eval_c_when_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\t\t\t\t\t\tinstanceReady = true;\n\n\t\t\t\t\t\tif (customDataProcessorLoaded) {\n\t\t\t\t\t\t\tinitData();\n\t\t\t\t\t\t}\n\t\t\t\t\t");
            }
            if (_jspx_th_c_when_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_0);
              return;
            }
            _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_0);
            out.write("\n\t\t\t\t\t");
            if (_jspx_meth_c_otherwise_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_choose_0, _jspx_page_context))
              return;
            out.write("\n\t\t\t\t");
          }
          if (_jspx_th_c_choose_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_0);
            return;
          }
          _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_0);
          out.write("\n\n\t\t\t\t");
          //  c:if
          com.liferay.taglib.core.IfTag _jspx_th_c_if_9 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
          _jspx_th_c_if_9.setPageContext(_jspx_page_context);
          _jspx_th_c_if_9.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_0);
          _jspx_th_c_if_9.setTest( Validator.isNotNull(onBlurMethod) );
          int _jspx_eval_c_if_9 = _jspx_th_c_if_9.doStartTag();
          if (_jspx_eval_c_if_9 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t\t\t\tCKEDITOR.instances['");
            out.print( name );
            out.write("'].on('blur', window['");
            out.print( name );
            out.write("'].onBlurCallback);\n\t\t\t\t");
          }
          if (_jspx_th_c_if_9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_9);
            return;
          }
          _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_9);
          out.write("\n\n\t\t\t\t");
          //  c:if
          com.liferay.taglib.core.IfTag _jspx_th_c_if_10 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
          _jspx_th_c_if_10.setPageContext(_jspx_page_context);
          _jspx_th_c_if_10.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_0);
          _jspx_th_c_if_10.setTest( Validator.isNotNull(onChangeMethod) );
          int _jspx_eval_c_if_10 = _jspx_th_c_if_10.doStartTag();
          if (_jspx_eval_c_if_10 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t\t\t\tvar contentChangeHandle = setInterval(\n\t\t\t\t\t\tfunction() {\n\t\t\t\t\t\t\ttry {\n\t\t\t\t\t\t\t\twindow['");
            out.print( name );
            out.write("'].onChangeCallback();\n\t\t\t\t\t\t\t}\n\t\t\t\t\t\t\tcatch (e) {\n\t\t\t\t\t\t\t}\n\t\t\t\t\t\t},\n\t\t\t\t\t\t300\n\t\t\t\t\t);\n\n\t\t\t\t\tvar clearContentChangeHandle = function(event) {\n\t\t\t\t\t\tif (event.portletId === '");
            out.print( portletId );
            out.write("') {\n\t\t\t\t\t\t\tclearInterval(contentChangeHandle);\n\n\t\t\t\t\t\t\tLiferay.detach('destroyPortlet', clearContentChangeHandle);\n\t\t\t\t\t\t}\n\t\t\t\t\t};\n\n\t\t\t\t\tLiferay.on('destroyPortlet', clearContentChangeHandle);\n\t\t\t\t");
          }
          if (_jspx_th_c_if_10.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_10);
            return;
          }
          _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_10);
          out.write("\n\n\t\t\t\t");
          //  c:if
          com.liferay.taglib.core.IfTag _jspx_th_c_if_11 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
          _jspx_th_c_if_11.setPageContext(_jspx_page_context);
          _jspx_th_c_if_11.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_0);
          _jspx_th_c_if_11.setTest( Validator.isNotNull(onFocusMethod) );
          int _jspx_eval_c_if_11 = _jspx_th_c_if_11.doStartTag();
          if (_jspx_eval_c_if_11 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t\t\t\tCKEDITOR.instances['");
            out.print( name );
            out.write("'].on('focus', window['");
            out.print( name );
            out.write("'].onFocusCallback);\n\t\t\t\t");
          }
          if (_jspx_th_c_if_11.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_11);
            return;
          }
          _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_11);
          out.write("\n\n\t\t\t\t");
          //  c:if
          com.liferay.taglib.core.IfTag _jspx_th_c_if_12 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
          _jspx_th_c_if_12.setPageContext(_jspx_page_context);
          _jspx_th_c_if_12.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_0);
          _jspx_th_c_if_12.setTest( !(inlineEdit && Validator.isNotNull(inlineEditSaveURL)) );
          int _jspx_eval_c_if_12 = _jspx_th_c_if_12.doStartTag();
          if (_jspx_eval_c_if_12 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t\t\t\tvar initialEditor = CKEDITOR.instances['");
            out.print( name );
            out.write("'].id;\n\n\t\t\t\t\teventHandles.push(\n\t\t\t\t\t\tA.getWin().on(\n\t\t\t\t\t\t\t'resize',\n\t\t\t\t\t\t\tA.debounce(\n\t\t\t\t\t\t\t\tfunction() {\n\t\t\t\t\t\t\t\t\tif (currentToolbarSet != getToolbarSet(initialToolbarSet)) {\n\t\t\t\t\t\t\t\t\t\tvar ckeditorInstance = CKEDITOR.instances['");
            out.print( name );
            out.write("'];\n\n\t\t\t\t\t\t\t\t\t\tif (ckeditorInstance) {\n\t\t\t\t\t\t\t\t\t\t\tvar currentEditor = ckeditorInstance.id;\n\n\t\t\t\t\t\t\t\t\t\t\tif (currentEditor === initialEditor) {\n\t\t\t\t\t\t\t\t\t\t\t\tvar currentDialog = CKEDITOR.dialog.getCurrent();\n\n\t\t\t\t\t\t\t\t\t\t\t\tif (currentDialog) {\n\t\t\t\t\t\t\t\t\t\t\t\t\tcurrentDialog.hide();\n\t\t\t\t\t\t\t\t\t\t\t\t}\n\n\t\t\t\t\t\t\t\t\t\t\t\tckEditorContent = ckeditorInstance.getData();\n\n\t\t\t\t\t\t\t\t\t\t\t\twindow['");
            out.print( name );
            out.write("'].dispose();\n\n\t\t\t\t\t\t\t\t\t\t\t\twindow['");
            out.print( name );
            out.write("'].create();\n\n\t\t\t\t\t\t\t\t\t\t\t\twindow['");
            out.print( name );
            out.write("'].setHTML(ckEditorContent);\n\n\t\t\t\t\t\t\t\t\t\t\t\tinitialEditor = CKEDITOR.instances['");
            out.print( name );
            out.write("'].id;\n\t\t\t\t\t\t\t\t\t\t\t}\n\t\t\t\t\t\t\t\t\t\t}\n\t\t\t\t\t\t\t\t\t}\n\t\t\t\t\t\t\t\t},\n\t\t\t\t\t\t\t\t250\n\t\t\t\t\t\t\t)\n\t\t\t\t\t\t)\n\t\t\t\t\t);\n\t\t\t\t");
          }
          if (_jspx_th_c_if_12.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_12);
            return;
          }
          _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_12);
          out.write("\n\t\t\t}\n\t\t);\n\n\t\tckEditor.on(\n\t\t\t'dataReady',\n\t\t\tfunction(event) {\n\t\t\t\tif (instancePendingData) {\n\t\t\t\t\tvar pendingData = instancePendingData;\n\n\t\t\t\t\tinstancePendingData = null;\n\n\t\t\t\t\tckEditor.setData(pendingData);\n\t\t\t\t}\n\t\t\t\telse {\n\t\t\t\t\tinstanceDataReady = true;\n\t\t\t\t}\n\n\t\t\t\twindow['");
          out.print( name );
          out.write("']._setStyles();\n\t\t\t}\n\t\t);\n\n\t\tckEditor.on(\n\t\t\t'setData',\n\t\t\tfunction(event) {\n\t\t\t\tinstanceDataReady = false;\n\t\t\t}\n\t\t);\n\n\t\tif (UA.edge && parseInt(UA.edge, 10) >= 14) {\n\t\t\tvar resetActiveElementValidation = function(activeElement) {\n\t\t\t\tactiveElement = A.one(activeElement);\n\n\t\t\t\tvar activeElementAncestor = activeElement.ancestor();\n\n\t\t\t\tif (activeElementAncestor.hasClass('has-error') || activeElementAncestor.hasClass('has-success')) {\n\t\t\t\t\tactiveElementAncestor.removeClass('has-error');\n\t\t\t\t\tactiveElementAncestor.removeClass('has-success');\n\n\t\t\t\t\tvar formValidatorStack = activeElementAncestor.one('.form-validator-stack');\n\n\t\t\t\t\tif (formValidatorStack) {\n\t\t\t\t\t\tformValidatorStack.remove();\n\t\t\t\t\t}\n\t\t\t\t}\n\t\t\t};\n\n\t\t\tvar onBlur = function(activeElement) {\n\t\t\t\tresetActiveElementValidation(activeElement);\n\n\t\t\t\tsetTimeout(\n\t\t\t\t\tfunction() {\n\t\t\t\t\t\tif (activeElement) {\n\t\t\t\t\t\t\tckEditor.focusManager.blur(true);\n\t\t\t\t\t\t\tactiveElement.focus();\n\t\t\t\t\t\t}\n\t\t\t\t\t},\n\t\t\t\t\t0\n\t\t\t\t);\n\t\t\t};\n\n\t\t\tckEditor.on(\n\t\t\t\t'instanceReady',\n\t\t\t\tfunction() {\n");
          out.write("\t\t\t\t\tvar editorWrapper = A.one('#cke_");
          out.print( name );
          out.write("');\n\n\t\t\t\t\tif (editorWrapper) {\n\t\t\t\t\t\teditorWrapper.once(\n\t\t\t\t\t\t\t'mouseenter',\n\t\t\t\t\t\t\tfunction(event) {\n\t\t\t\t\t\t\t\tckEditor.once('focus', onBlur.bind(this, document.activeElement));\n\t\t\t\t\t\t\t\tckEditor.focus();\n\t\t\t\t\t\t\t}\n\t\t\t\t\t\t);\n\t\t\t\t\t}\n\t\t\t\t}\n\t\t\t);\n\t\t}\n\t};\n\n\t");

	String toogleControlsStatus = GetterUtil.getString(SessionClicks.get(request, "com.liferay.frontend.js.web_toggleControls", "visible"));
	
          out.write("\n\n\t");
          //  c:if
          com.liferay.taglib.core.IfTag _jspx_th_c_if_13 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
          _jspx_th_c_if_13.setPageContext(_jspx_page_context);
          _jspx_th_c_if_13.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_0);
          _jspx_th_c_if_13.setTest( autoCreate && ((inlineEdit && toogleControlsStatus.equals("visible")) || !inlineEdit) );
          int _jspx_eval_c_if_13 = _jspx_th_c_if_13.doStartTag();
          if (_jspx_eval_c_if_13 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\tcreateEditor();\n\t");
          }
          if (_jspx_th_c_if_13.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_13);
            return;
          }
          _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_13);
          out.write('\n');
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

  private boolean _jspx_meth_portlet_namespace_0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_7, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_0 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_0.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_7);
    int _jspx_eval_portlet_namespace_0 = _jspx_th_portlet_namespace_0.doStartTag();
    if (_jspx_th_portlet_namespace_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_0);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_0);
    return false;
  }

  private boolean _jspx_meth_c_otherwise_0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_choose_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:otherwise
    com.liferay.taglib.core.OtherwiseTag _jspx_th_c_otherwise_0 = (com.liferay.taglib.core.OtherwiseTag) _jspx_tagPool_c_otherwise.get(com.liferay.taglib.core.OtherwiseTag.class);
    _jspx_th_c_otherwise_0.setPageContext(_jspx_page_context);
    _jspx_th_c_otherwise_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_0);
    int _jspx_eval_c_otherwise_0 = _jspx_th_c_otherwise_0.doStartTag();
    if (_jspx_eval_c_otherwise_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      out.write("\n\t\t\t\t\t\tinitData();\n\t\t\t\t\t");
    }
    if (_jspx_th_c_otherwise_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_0);
      return true;
    }
    _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_0);
    return false;
  }
}
