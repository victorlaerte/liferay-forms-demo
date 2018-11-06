package org.apache.jsp.ddm_005fform_005fbuilder;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.liferay.portal.json.JSONFactoryImpl;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.util.HtmlUtil;

public final class start_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {


private static ArrayList<Object> _toArrayList(Object obj) {
	return (ArrayList<Object>)_deserialize(obj);
}

private static HashMap<String, Object> _toHashMap(Object obj) {
	return (HashMap<String, Object>)_deserialize(obj);
}

public static void _initJSONFactoryUtil() {
	if (JSONFactoryUtil.getJSONFactory() == null) {
		(new JSONFactoryUtil()).setJSONFactory(new JSONFactoryImpl());
	}
}

private static Object _deserialize(Object obj) {
	if (obj != null) {
		String json = JSONFactoryUtil.looseSerialize(obj);

		json = StringUtil.unquote(json);

		return JSONFactoryUtil.looseDeserialize(json);
	}

	return null;
}

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(3);
    _jspx_dependants.add("/ddm_form_builder/init.jsp");
    _jspx_dependants.add("/init.jsp");
    _jspx_dependants.add("/ddm_form_builder/init-ext.jspf");
  }

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_script_use;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_aui_script_use = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_aui_script_use.release();
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
      out.write("\n\n\n\n\n\n");
      out.write('\n');
      out.write('\n');

_initJSONFactoryUtil();

      out.write('\n');
      out.write('\n');

java.lang.Long ddmStructureId = GetterUtil.getLong(String.valueOf(request.getAttribute("liferay-form:ddm-form-builder:ddmStructureId")));
java.lang.Long ddmStructureVersionId = GetterUtil.getLong(String.valueOf(request.getAttribute("liferay-form:ddm-form-builder:ddmStructureVersionId")));
java.lang.String defaultLanguageId = GetterUtil.getString((java.lang.String)request.getAttribute("liferay-form:ddm-form-builder:defaultLanguageId"));
java.lang.String editingLanguageId = GetterUtil.getString((java.lang.String)request.getAttribute("liferay-form:ddm-form-builder:editingLanguageId"));
long fieldSetClassNameId = GetterUtil.getLong(String.valueOf(request.getAttribute("liferay-form:ddm-form-builder:fieldSetClassNameId")));
java.lang.String refererPortletNamespace = GetterUtil.getString((java.lang.String)request.getAttribute("liferay-form:ddm-form-builder:refererPortletNamespace"));
boolean showPagination = GetterUtil.getBoolean(String.valueOf(request.getAttribute("liferay-form:ddm-form-builder:showPagination")), true);
Map<String, Object> dynamicAttributes = (Map<String, Object>)request.getAttribute("liferay-form:ddm-form-builder:dynamicAttributes");

      out.write('\n');
      out.write('\n');
      out.write("\n\n\n\n\n\n");

String dataProviderInstancesURL = (String)request.getAttribute("liferay-form:ddm-form-builder:dataProviderInstancesURL");
String dataProviderInstanceParameterSettingsURL = (String)request.getAttribute("liferay-form:ddm-form-builder:dataProviderInstanceParameterSettingsURL");
String evaluatorURL = (String)request.getAttribute("liferay-form:ddm-form-builder:evaluatorURL");
String fieldSetDefinitionURL = (String)request.getAttribute("liferay-form:ddm-form-builder:fieldSetDefinitionURL");
JSONArray fieldSets = (JSONArray)request.getAttribute("liferay-form:ddm-form-builder:fieldSets");
String fieldSettingsDDMFormContextURL = (String)request.getAttribute("liferay-form:ddm-form-builder:fieldSettingsDDMFormContextURL");
String formBuilderContext = (String)request.getAttribute("liferay-form:ddm-form-builder:formBuilderContext");
String functionsMetadata = (String)request.getAttribute("liferay-form:ddm-form-builder:functionsMetadata");
String functionsURL = (String)request.getAttribute("liferay-form:ddm-form-builder:functionsURL");
String rolesURL = (String)request.getAttribute("liferay-form:ddm-form-builder:rolesURL");
String serializedDDMFormRules = (String)request.getAttribute("liferay-form:ddm-form-builder:serializedDDMFormRules");

      out.write('\n');
      out.write('\n');
      //  aui:script
      com.liferay.taglib.aui.ScriptTag _jspx_th_aui_script_0 = (com.liferay.taglib.aui.ScriptTag) _jspx_tagPool_aui_script_use.get(com.liferay.taglib.aui.ScriptTag.class);
      _jspx_th_aui_script_0.setPageContext(_jspx_page_context);
      _jspx_th_aui_script_0.setParent(null);
      _jspx_th_aui_script_0.setUse("liferay-ddm-form-builder, liferay-ddm-form-builder-fieldset, liferay-ddm-form-builder-rule-builder");
      int _jspx_eval_aui_script_0 = _jspx_th_aui_script_0.doStartTag();
      if (_jspx_eval_aui_script_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        if (_jspx_eval_aui_script_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
          out = _jspx_page_context.pushBody();
          _jspx_th_aui_script_0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
          _jspx_th_aui_script_0.doInitBody();
        }
        do {
          out.write("\n\n\tLiferay.namespace('DDM').Settings = {\n\t\tevaluatorURL: '");
          out.print( HtmlUtil.escapeJS(evaluatorURL) );
          out.write("',\n\t\tfieldSetDefinitionURL: '");
          out.print( HtmlUtil.escapeJS(fieldSetDefinitionURL) );
          out.write("',\n\t\tfunctionsMetadata: JSON.parse('");
          out.print( HtmlUtil.escapeJS(functionsMetadata) );
          out.write("'),\n\t\tgetDataProviderInstancesURL: '");
          out.print( HtmlUtil.escapeJS(dataProviderInstancesURL) );
          out.write("',\n\t\tgetDataProviderParametersSettingsURL: '");
          out.print( HtmlUtil.escapeJS(dataProviderInstanceParameterSettingsURL) );
          out.write("',\n\t\tgetFieldTypeSettingFormContextURL: '");
          out.print( HtmlUtil.escapeJS(fieldSettingsDDMFormContextURL) );
          out.write("',\n\t\tgetFunctionsURL: '");
          out.print( HtmlUtil.escapeJS(functionsURL) );
          out.write("',\n\t\tgetRolesURL: '");
          out.print( HtmlUtil.escapeJS(rolesURL) );
          out.write("',\n\t\tportletNamespace: '");
          out.print( HtmlUtil.escapeJS(refererPortletNamespace) );
          out.write("'\n\t};\n\n\tLiferay.DDM.FieldSets.register(");
          out.print( fieldSets );
          out.write(");\n\n\tLiferay.component(\n\t\t'");
          out.print( HtmlUtil.escapeJS(refererPortletNamespace) );
          out.write("formBuilder',\n\t\tfunction() {\n\t\t\treturn new Liferay.DDM.FormBuilder(\n\t\t\t\t{\n\t\t\t\t\tcontext: JSON.parse('");
          out.print( HtmlUtil.escapeJS(formBuilderContext) );
          out.write("'),\n\t\t\t\t\tdefaultLanguageId: '");
          out.print( HtmlUtil.escapeJS(defaultLanguageId) );
          out.write("',\n\t\t\t\t\teditingLanguageId: '");
          out.print( HtmlUtil.escapeJS(editingLanguageId) );
          out.write("',\n\t\t\t\t\tshowPagination: ");
          out.print( showPagination );
          out.write("\n\t\t\t\t}\n\t\t\t);\n\t\t}\n\t);\n\n\tLiferay.component(\n\t\t'");
          out.print( HtmlUtil.escapeJS(refererPortletNamespace) );
          out.write("ruleBuilder',\n\t\tfunction() {\n\t\t\treturn new Liferay.DDM.FormBuilderRuleBuilder(\n\t\t\t\t{\n\t\t\t\t\tformBuilder: Liferay.component('");
          out.print( HtmlUtil.escapeJS(refererPortletNamespace) );
          out.write("formBuilder'),\n\t\t\t\t\trules: JSON.parse('");
          out.print( HtmlUtil.escapeJS(serializedDDMFormRules) );
          out.write("'),\n\t\t\t\t\tvisible: false\n\t\t\t\t}\n\t\t\t);\n\t\t}\n\t);\n");
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
}
