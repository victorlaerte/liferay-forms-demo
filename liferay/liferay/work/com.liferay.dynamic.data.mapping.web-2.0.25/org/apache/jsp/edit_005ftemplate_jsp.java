package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.liferay.dynamic.data.mapping.constants.DDMPortletKeys;
import com.liferay.dynamic.data.mapping.constants.DDMWebKeys;
import com.liferay.dynamic.data.mapping.exception.NoSuchStructureException;
import com.liferay.dynamic.data.mapping.exception.RequiredStructureException;
import com.liferay.dynamic.data.mapping.exception.RequiredTemplateException;
import com.liferay.dynamic.data.mapping.exception.StructureDefinitionException;
import com.liferay.dynamic.data.mapping.exception.StructureDuplicateElementException;
import com.liferay.dynamic.data.mapping.exception.StructureFieldException;
import com.liferay.dynamic.data.mapping.exception.StructureNameException;
import com.liferay.dynamic.data.mapping.exception.TemplateNameException;
import com.liferay.dynamic.data.mapping.exception.TemplateScriptException;
import com.liferay.dynamic.data.mapping.exception.TemplateSmallImageContentException;
import com.liferay.dynamic.data.mapping.exception.TemplateSmallImageNameException;
import com.liferay.dynamic.data.mapping.exception.TemplateSmallImageSizeException;
import com.liferay.dynamic.data.mapping.model.DDMForm;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.model.DDMStructureConstants;
import com.liferay.dynamic.data.mapping.model.DDMStructureVersion;
import com.liferay.dynamic.data.mapping.model.DDMTemplate;
import com.liferay.dynamic.data.mapping.model.DDMTemplateConstants;
import com.liferay.dynamic.data.mapping.model.DDMTemplateVersion;
import com.liferay.dynamic.data.mapping.service.DDMStorageLinkLocalServiceUtil;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalServiceUtil;
import com.liferay.dynamic.data.mapping.service.DDMStructureServiceUtil;
import com.liferay.dynamic.data.mapping.service.DDMStructureVersionServiceUtil;
import com.liferay.dynamic.data.mapping.service.DDMTemplateLocalServiceUtil;
import com.liferay.dynamic.data.mapping.service.DDMTemplateServiceUtil;
import com.liferay.dynamic.data.mapping.service.DDMTemplateVersionServiceUtil;
import com.liferay.dynamic.data.mapping.storage.StorageType;
import com.liferay.dynamic.data.mapping.util.DDMDisplay;
import com.liferay.dynamic.data.mapping.util.DDMNavigationHelper;
import com.liferay.dynamic.data.mapping.util.DDMUtil;
import com.liferay.dynamic.data.mapping.validator.DDMFormLayoutValidationException;
import com.liferay.dynamic.data.mapping.validator.DDMFormValidationException;
import com.liferay.dynamic.data.mapping.web.configuration.DDMWebConfigurationKeys;
import com.liferay.dynamic.data.mapping.web.configuration.DDMWebConfigurationUtil;
import com.liferay.dynamic.data.mapping.web.internal.display.context.DDMDisplayContext;
import com.liferay.dynamic.data.mapping.web.internal.search.DDMStructureRowChecker;
import com.liferay.dynamic.data.mapping.web.internal.search.DDMTemplateRowChecker;
import com.liferay.dynamic.data.mapping.web.internal.search.StructureSearch;
import com.liferay.dynamic.data.mapping.web.internal.search.TemplateSearch;
import com.liferay.dynamic.data.mapping.web.internal.security.permission.resource.DDMStructurePermission;
import com.liferay.dynamic.data.mapping.web.internal.security.permission.resource.DDMTemplatePermission;
import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.bean.BeanParamUtil;
import com.liferay.portal.kernel.configuration.Filter;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.editor.EditorModeUtil;
import com.liferay.portal.kernel.exception.LocaleException;
import com.liferay.portal.kernel.exception.PortletPreferencesException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.language.UnicodeLanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.ResourceActionsUtil;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.PortletLocalServiceUtil;
import com.liferay.portal.kernel.template.TemplateConstants;
import com.liferay.portal.kernel.template.TemplateHandler;
import com.liferay.portal.kernel.template.TemplateHandlerRegistryUtil;
import com.liferay.portal.kernel.template.TemplateVariableDefinition;
import com.liferay.portal.kernel.template.TemplateVariableGroup;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ResourceBundleLoader;
import com.liferay.portal.kernel.util.ResourceBundleLoaderUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.TextFormatter;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.template.TemplateContextHelper;
import com.liferay.taglib.search.ResultRow;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.StringTokenizer;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.WindowState;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

public final class edit_005ftemplate_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {


private void _addFormTemplateFieldAttributes(DDMStructure structure, JSONArray jsonArray) throws Exception {
	for (int i = 0; i < jsonArray.length(); i++) {
		JSONObject jsonObject = jsonArray.getJSONObject(i);

		String fieldName = jsonObject.getString("name");

		try {
			jsonObject.put("readOnlyAttributes", _getFieldReadOnlyAttributes(structure, fieldName));
			jsonObject.put("unique", true);
		}
		catch (StructureFieldException sfe) {
		}
	}
}

private JSONArray _getFieldReadOnlyAttributes(DDMStructure structure, String fieldName) throws Exception {
	JSONArray readOnlyAttributesJSONArray = JSONFactoryUtil.createJSONArray();

	readOnlyAttributesJSONArray.put("indexType");
	readOnlyAttributesJSONArray.put("name");
	readOnlyAttributesJSONArray.put("options");
	readOnlyAttributesJSONArray.put("repeatable");

	boolean required = structure.getFieldRequired(fieldName);

	if (required) {
		readOnlyAttributesJSONArray.put("required");
	}

	return readOnlyAttributesJSONArray;
}

private JSONArray _getFormTemplateFieldsJSONArray(DDMStructure structure, String script) throws Exception {
	JSONArray jsonArray = null;

	if (Objects.equals(structure.getDefinition(), script)) {
		jsonArray = DDMUtil.getDDMFormFieldsJSONArray(structure, script);
	}
	else {
		jsonArray = DDMUtil.getDDMFormFieldsJSONArray((DDMStructure)null, script);
	}

	_addFormTemplateFieldAttributes(structure, jsonArray);

	return jsonArray;
}


private String _getAccessor(String accessor, String language) {
	if (StringUtil.equalsIgnoreCase(language, "vm")) {
		if (!accessor.contains(StringPool.OPEN_PARENTHESIS)) {
			return accessor;
		}

		StringTokenizer st = new StringTokenizer(accessor, "(,");

		StringBundler sb = new StringBundler(st.countTokens() * 2);

		sb.append(st.nextToken());
		sb.append(StringPool.OPEN_PARENTHESIS);

		while (st.hasMoreTokens()) {
			sb.append(StringPool.DOLLAR);
			sb.append(st.nextToken());
		}

		accessor = sb.toString();
	}

	return accessor;
}

private String _getDataContent(TemplateVariableDefinition templateVariableDefinition, String language) {
	String dataContent = StringPool.BLANK;

	String dataType = templateVariableDefinition.getDataType();

	if (templateVariableDefinition.isCollection()) {
		TemplateVariableDefinition itemTemplateVariableDefinition = templateVariableDefinition.getItemTemplateVariableDefinition();

		dataContent = _getListCode(templateVariableDefinition.getName(), itemTemplateVariableDefinition.getName(), itemTemplateVariableDefinition.getAccessor(), language);
	}
	else if (Validator.isNull(dataType)) {
		dataContent = _getVariableReferenceCode(templateVariableDefinition.getName(), templateVariableDefinition.getAccessor(), language);
	}
	else if (dataType.equals("service-locator")) {
		Class<?> templateVariableDefinitionClass = templateVariableDefinition.getClazz();

		String variableName = templateVariableDefinitionClass.getSimpleName();

		StringBundler sb = new StringBundler(3);

		sb.append(_getVariableAssignmentCode(variableName, "serviceLocator.findService(\"" + templateVariableDefinition.getName() + "\")", language));
		sb.append("[$CURSOR$]");
		sb.append(_getVariableReferenceCode(variableName, null, language));

		dataContent = sb.toString();
	}
	else {
		try {
			String[] generateCode = templateVariableDefinition.generateCode(language);

			dataContent = generateCode[0];
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	return dataContent;
}

private String _getListCode(String variableName, String itemName, String accessor, String language) {
	if (StringUtil.equalsIgnoreCase(language, "ftl")) {
		StringBundler sb = new StringBundler(10);

		sb.append("<#if ");
		sb.append(variableName);
		sb.append("?has_content>\n\t<#list ");
		sb.append(variableName);
		sb.append(" as ");
		sb.append(itemName);
		sb.append(">\n\t\t");
		sb.append(_getVariableReferenceCode(itemName, accessor, language));
		sb.append("[$CURSOR$]");
		sb.append("\n\t</#list>\n</#if>");

		return sb.toString();
	}
	else if (StringUtil.equalsIgnoreCase(language, "vm")) {
		StringBundler sb = new StringBundler(10);

		sb.append("#if (!$");
		sb.append(variableName);
		sb.append(".isEmpty())\n\t#foreach ($");
		sb.append(itemName);
		sb.append(" in $");
		sb.append(variableName);
		sb.append(")\n\t\t");
		sb.append(_getVariableReferenceCode(itemName, accessor, language));
		sb.append("[$CURSOR$]");
		sb.append("#end\n#end");

		return sb.toString();
	}

	return StringPool.BLANK;
}

private String _getPaletteItemTitle(HttpServletRequest request, String label, Class<?> clazz) {
	StringBundler sb = new StringBundler(10);

	if (clazz == null) {
		return StringPool.BLANK;
	}

	String className = clazz.getName();

	sb.append("<br />");
	sb.append(LanguageUtil.get(request, label));
	sb.append(StringPool.COLON);
	sb.append("&nbsp;");

	String javadocURL = null;

	if (className.startsWith("com.liferay.portal.kernel")) {
		javadocURL = "http://docs.liferay.com/portal/7.0/javadocs/portal-kernel/";
	}

	if (Validator.isNotNull(javadocURL)) {
		sb.append("<a href=\"");
		sb.append(javadocURL);
		sb.append(StringUtil.replace(className, CharPool.PERIOD, CharPool.SLASH));
		sb.append(".html\" target=\"_blank\">");
	}

	sb.append(clazz.getSimpleName());

	if (Validator.isNull(javadocURL)) {
		sb.append("</a>");
	}

	return sb.toString();
}

private String _getPaletteItemTitle(HttpServletRequest request, ResourceBundle resourceBundle, TemplateVariableDefinition templateVariableDefinition) {
	StringBundler sb = new StringBundler(12);

	String help = templateVariableDefinition.getHelp();

	if (Validator.isNotNull(help)) {
		sb.append("<p>");
		sb.append(HtmlUtil.escape(LanguageUtil.get(request, resourceBundle, help)));
		sb.append("</p>");
	}

	if (templateVariableDefinition.isCollection()) {
		sb.append("<p><i>*");
		sb.append(LanguageUtil.get(request, "this-is-a-collection-of-fields"));
		sb.append("</i></p>");
	}
	else if (templateVariableDefinition.isRepeatable()) {
		sb.append("<p><i>*");
		sb.append(LanguageUtil.get(request, "this-is-a-repeatable-field"));
		sb.append("</i></p>");
	}

	if (!Objects.equals(templateVariableDefinition.getDataType(), "service-locator")) {
		sb.append(LanguageUtil.get(request, "variable"));
		sb.append(StringPool.COLON);
		sb.append("&nbsp;");
		sb.append(HtmlUtil.escape(templateVariableDefinition.getName()));
	}

	sb.append(_getPaletteItemTitle(request, "class", templateVariableDefinition.getClazz()));

	if (templateVariableDefinition.isCollection()) {
		TemplateVariableDefinition itemTemplateVariableDefinition = templateVariableDefinition.getItemTemplateVariableDefinition();

		sb.append(_getPaletteItemTitle(request, "items-class", itemTemplateVariableDefinition.getClazz()));
	}

	return sb.toString();
}

private String _getVariableAssignmentCode(String variableName, String variableValue, String language) {
	if (StringUtil.equalsIgnoreCase(language, "ftl")) {
		return "<#assign " + variableName + " = " + variableValue + ">";
	}
	else if (StringUtil.equalsIgnoreCase(language, "vm")) {
		if (!variableValue.startsWith(StringPool.DOUBLE_QUOTE) && !variableValue.startsWith(StringPool.OPEN_BRACKET) && !variableValue.startsWith(StringPool.OPEN_CURLY_BRACE) && !variableValue.startsWith(StringPool.QUOTE) && !Validator.isNumber(variableValue)) {
			variableValue = StringPool.DOLLAR + variableValue;
		}

		return "#set ($" + variableName + " = " + variableValue + ")";
	}

	return variableName;
}

private String _getVariableReferenceCode(String variableName, String accessor, String language) {
	String methodInvocation = StringPool.BLANK;

	if (Validator.isNotNull(accessor)) {
		methodInvocation = StringPool.PERIOD + _getAccessor(accessor, language);
	}

	if (StringUtil.equalsIgnoreCase(language, "ftl")) {
		return "${" + variableName + methodInvocation + "}";
	}
	else if (StringUtil.equalsIgnoreCase(language, "vm")) {
		return "$" + variableName + methodInvocation;
	}

	return variableName;
}


private static Log _log = LogFactoryUtil.getLog("com_liferay_dynamic_data_mapping_web.edit_template_display_jspf");

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(5);
    _jspx_dependants.add("/init.jsp");
    _jspx_dependants.add("/init-ext.jsp");
    _jspx_dependants.add("/edit_template_form.jspf");
    _jspx_dependants.add("/form_builder.jspf");
    _jspx_dependants.add("/edit_template_display.jspf");
  }

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_param_value_name_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_error_exception;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_model$1context_model_bean_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_panel$1container_persistState_id_extended_cssClass;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_option_label_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_workflow$1status_version_status_model_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_select_name_label_helpMessage;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_option_value_selected_label_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_input_value_name_helpMessage_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_fieldset$1group_markupView;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_input_type_name_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_button_value_primary_onClick_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_input_name_autoFocus_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_translation$1manager_readOnly_initialize_id_defaultLanguageId_changeableDefaultLanguage_availableLocales_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_fieldset;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_when_test;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_input_value_type_name_helpMessage_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_button_type_href_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_message_key_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_button_value_onClick_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_form_onSubmit_name_method_enctype_cssClass_action;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_resourceURL_var_id;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_input_name_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_select_name_label_helpMessage_changesContext;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_actionURL_var_name;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_section;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_row;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_if_test;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_icon_url_message_linkCssClass_label_iconCssClass_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_script;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_input_type_name_label_inlineLabel_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_panel_title_persistState_markupView_id_extended_defaultState_collapsible;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_col_width;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_input_title_name_label_inlineField_cssClass_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_panel_title_id_extended_cssClass_collapsible;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_choose;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_input_name_disabled_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1frontend_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_script_use;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_field$1wrapper;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_namespace_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_input_value_type_name_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_renderURL_var;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_otherwise;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_tabs_type_refresh_names;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_error_message_exception_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_header_title_showBackURL_localizeTitle_backURL_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1theme_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_message_key_escapeAttribute_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_panel_title_persistState_markupView_id_extended_collapsible;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_input_type_name_label_inlineField_cssClass_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_button$1row;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_panel$1container_persistState_markupView_id_extended;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_portlet_param_value_name_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_error_exception = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_model$1context_model_bean_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_panel$1container_persistState_id_extended_cssClass = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_option_label_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_workflow$1status_version_status_model_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_select_name_label_helpMessage = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_option_value_selected_label_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_input_value_name_helpMessage_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_fieldset$1group_markupView = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_input_type_name_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_button_value_primary_onClick_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_input_name_autoFocus_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_translation$1manager_readOnly_initialize_id_defaultLanguageId_changeableDefaultLanguage_availableLocales_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_fieldset = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_when_test = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_input_value_type_name_helpMessage_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_button_type_href_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_message_key_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_button_value_onClick_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_form_onSubmit_name_method_enctype_cssClass_action = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_resourceURL_var_id = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_input_name_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_select_name_label_helpMessage_changesContext = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_actionURL_var_name = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_section = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_row = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_if_test = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_icon_url_message_linkCssClass_label_iconCssClass_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_script = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_input_type_name_label_inlineLabel_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_panel_title_persistState_markupView_id_extended_defaultState_collapsible = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_col_width = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_input_title_name_label_inlineField_cssClass_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_panel_title_id_extended_cssClass_collapsible = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_choose = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_input_name_disabled_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1frontend_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_script_use = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_field$1wrapper = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_namespace_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_input_value_type_name_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_renderURL_var = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_otherwise = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_tabs_type_refresh_names = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_error_message_exception_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_header_title_showBackURL_localizeTitle_backURL_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1theme_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_message_key_escapeAttribute_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_panel_title_persistState_markupView_id_extended_collapsible = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_input_type_name_label_inlineField_cssClass_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_button$1row = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_panel$1container_persistState_markupView_id_extended = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_portlet_param_value_name_nobody.release();
    _jspx_tagPool_liferay$1ui_error_exception.release();
    _jspx_tagPool_aui_model$1context_model_bean_nobody.release();
    _jspx_tagPool_liferay$1ui_panel$1container_persistState_id_extended_cssClass.release();
    _jspx_tagPool_aui_option_label_nobody.release();
    _jspx_tagPool_aui_workflow$1status_version_status_model_nobody.release();
    _jspx_tagPool_aui_select_name_label_helpMessage.release();
    _jspx_tagPool_aui_option_value_selected_label_nobody.release();
    _jspx_tagPool_aui_input_value_name_helpMessage_nobody.release();
    _jspx_tagPool_aui_fieldset$1group_markupView.release();
    _jspx_tagPool_aui_input_type_name_nobody.release();
    _jspx_tagPool_aui_button_value_primary_onClick_nobody.release();
    _jspx_tagPool_aui_input_name_autoFocus_nobody.release();
    _jspx_tagPool_aui_translation$1manager_readOnly_initialize_id_defaultLanguageId_changeableDefaultLanguage_availableLocales_nobody.release();
    _jspx_tagPool_aui_fieldset.release();
    _jspx_tagPool_c_when_test.release();
    _jspx_tagPool_aui_input_value_type_name_helpMessage_nobody.release();
    _jspx_tagPool_aui_button_type_href_nobody.release();
    _jspx_tagPool_liferay$1ui_message_key_nobody.release();
    _jspx_tagPool_aui_button_value_onClick_nobody.release();
    _jspx_tagPool_portlet_defineObjects_nobody.release();
    _jspx_tagPool_aui_form_onSubmit_name_method_enctype_cssClass_action.release();
    _jspx_tagPool_portlet_resourceURL_var_id.release();
    _jspx_tagPool_aui_input_name_nobody.release();
    _jspx_tagPool_aui_select_name_label_helpMessage_changesContext.release();
    _jspx_tagPool_portlet_actionURL_var_name.release();
    _jspx_tagPool_liferay$1ui_section.release();
    _jspx_tagPool_aui_row.release();
    _jspx_tagPool_c_if_test.release();
    _jspx_tagPool_liferay$1ui_icon_url_message_linkCssClass_label_iconCssClass_nobody.release();
    _jspx_tagPool_aui_script.release();
    _jspx_tagPool_aui_input_type_name_label_inlineLabel_nobody.release();
    _jspx_tagPool_liferay$1ui_panel_title_persistState_markupView_id_extended_defaultState_collapsible.release();
    _jspx_tagPool_aui_col_width.release();
    _jspx_tagPool_aui_input_title_name_label_inlineField_cssClass_nobody.release();
    _jspx_tagPool_liferay$1ui_panel_title_id_extended_cssClass_collapsible.release();
    _jspx_tagPool_c_choose.release();
    _jspx_tagPool_aui_input_name_disabled_nobody.release();
    _jspx_tagPool_liferay$1frontend_defineObjects_nobody.release();
    _jspx_tagPool_aui_script_use.release();
    _jspx_tagPool_aui_field$1wrapper.release();
    _jspx_tagPool_portlet_namespace_nobody.release();
    _jspx_tagPool_aui_input_value_type_name_nobody.release();
    _jspx_tagPool_portlet_renderURL_var.release();
    _jspx_tagPool_c_otherwise.release();
    _jspx_tagPool_liferay$1ui_tabs_type_refresh_names.release();
    _jspx_tagPool_liferay$1ui_error_message_exception_nobody.release();
    _jspx_tagPool_liferay$1ui_header_title_showBackURL_localizeTitle_backURL_nobody.release();
    _jspx_tagPool_liferay$1theme_defineObjects_nobody.release();
    _jspx_tagPool_liferay$1ui_message_key_escapeAttribute_nobody.release();
    _jspx_tagPool_liferay$1ui_panel_title_persistState_markupView_id_extended_collapsible.release();
    _jspx_tagPool_aui_input_type_name_label_inlineField_cssClass_nobody.release();
    _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody.release();
    _jspx_tagPool_aui_button$1row.release();
    _jspx_tagPool_liferay$1ui_panel$1container_persistState_markupView_id_extended.release();
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

String refererWebDAVToken = ParamUtil.getString(request, "refererWebDAVToken", portletConfig.getInitParameter("refererWebDAVToken"));
String scopeTitle = ParamUtil.getString(request, "scopeTitle");
boolean showAncestorScopes = ParamUtil.getBoolean(request, "showAncestorScopes");
boolean showManageTemplates = ParamUtil.getBoolean(request, "showManageTemplates", true);

DDMDisplay ddmDisplay = null;

boolean changeableDefaultLanguage = false;
String refererPortletName = StringPool.BLANK;
String scopeAvailableFields = StringPool.BLANK;
long scopeClassNameId = 0;
String scopeStorageType = StringPool.BLANK;
String scopeTemplateType = StringPool.BLANK;

DDMDisplayContext ddmDisplayContext = (DDMDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

if (ddmDisplayContext != null) {
	ddmDisplay = ddmDisplayContext.getDDMDisplay();

	changeableDefaultLanguage = ddmDisplayContext.changeableDefaultLanguage();
	refererPortletName = ddmDisplayContext.getRefererPortletName();
	scopeAvailableFields = ddmDisplay.getAvailableFields();
	scopeClassNameId = PortalUtil.getClassNameId(ddmDisplay.getStructureType());
	scopeStorageType = ddmDisplay.getStorageType();
	scopeTemplateType = ddmDisplay.getTemplateType();
}

String storageTypeValue = StringPool.BLANK;

if (scopeStorageType.equals("json")) {
	storageTypeValue = StorageType.JSON.getValue();
}

String templateTypeValue = StringPool.BLANK;

if (scopeTemplateType.equals(DDMTemplateConstants.TEMPLATE_TYPE_DISPLAY)) {
	templateTypeValue = DDMTemplateConstants.TEMPLATE_TYPE_DISPLAY;
}
else if (scopeTemplateType.equals(DDMTemplateConstants.TEMPLATE_TYPE_FORM)) {
	templateTypeValue = DDMTemplateConstants.TEMPLATE_TYPE_FORM;
}

      out.write('\n');
      out.write('\n');
      out.write('\n');
      out.write('\n');
      out.write('\n');
      out.write('\n');

String closeRedirect = ParamUtil.getString(request, "closeRedirect");

String portletResource = ParamUtil.getString(request, "portletResource");

String portletResourceNamespace = ParamUtil.getString(request, "portletResourceNamespace");

DDMTemplate template = (DDMTemplate)request.getAttribute(DDMWebKeys.DYNAMIC_DATA_MAPPING_TEMPLATE);

long templateId = BeanParamUtil.getLong(template, request, "templateId");

long groupId = BeanParamUtil.getLong(template, request, "groupId", PortalUtil.getScopeGroupId(request, refererPortletName));
long classNameId = BeanParamUtil.getLong(template, request, "classNameId");
long classPK = BeanParamUtil.getLong(template, request, "classPK");
long resourceClassNameId = BeanParamUtil.getLong(template, request, "resourceClassNameId");

boolean cacheable = BeanParamUtil.getBoolean(template, request, "cacheable", true);
boolean smallImage = BeanParamUtil.getBoolean(template, request, "smallImage");

DDMStructure structure = (DDMStructure)request.getAttribute(DDMWebKeys.DYNAMIC_DATA_MAPPING_STRUCTURE);

if ((structure == null) && (template != null)) {
	structure = ddmDisplayContext.fetchStructure(template);
}

String type = BeanParamUtil.getString(template, request, "type", DDMTemplateConstants.TEMPLATE_TYPE_FORM);
String mode = BeanParamUtil.getString(template, request, "mode", DDMTemplateConstants.TEMPLATE_MODE_CREATE);
String language = BeanParamUtil.getString(template, request, "language", ddmDisplay.getDefaultTemplateLanguage());
String script = BeanParamUtil.getString(template, request, "script");

if (Validator.isNull(script) && type.equals(DDMTemplateConstants.TEMPLATE_TYPE_DISPLAY)) {
	TemplateHandler templateHandler = TemplateHandlerRegistryUtil.getTemplateHandler(classNameId);

	if ((templateHandler == null) && (structure != null)) {
		templateHandler = TemplateHandlerRegistryUtil.getTemplateHandler(structure.getClassNameId());
	}

	if (templateHandler != null) {
		script = templateHandler.getTemplatesHelpContent(language);
	}
	else {
		script = StringUtil.read(DDMWebConfigurationUtil.class.getClassLoader(), DDMWebConfigurationUtil.get(DDMWebConfigurationKeys.DYNAMIC_DATA_MAPPING_TEMPLATE_LANGUAGE_CONTENT, new Filter(language)));
	}
}

DDMTemplateVersion templateVersion = null;

if (template != null) {
	templateVersion = template.getLatestTemplateVersion();
}

String structureAvailableFields = ParamUtil.getString(request, "structureAvailableFields");

if (Validator.isNotNull(structureAvailableFields)) {
	scopeAvailableFields = structureAvailableFields;
}

boolean showBackURL = ParamUtil.getBoolean(request, "showBackURL", true);
boolean showCacheableInput = ParamUtil.getBoolean(request, "showCacheableInput");
boolean showHeader = ParamUtil.getBoolean(request, "showHeader", true);

DDMNavigationHelper ddmNavigationHelper = ddmDisplay.getDDMNavigationHelper();

      out.write('\n');
      out.write('\n');
      //  portlet:actionURL
      com.liferay.taglib.portlet.ActionURLTag _jspx_th_portlet_actionURL_0 = (com.liferay.taglib.portlet.ActionURLTag) _jspx_tagPool_portlet_actionURL_var_name.get(com.liferay.taglib.portlet.ActionURLTag.class);
      _jspx_th_portlet_actionURL_0.setPageContext(_jspx_page_context);
      _jspx_th_portlet_actionURL_0.setParent(null);
      _jspx_th_portlet_actionURL_0.setName("addTemplate");
      _jspx_th_portlet_actionURL_0.setVar("addTemplateURL");
      int _jspx_eval_portlet_actionURL_0 = _jspx_th_portlet_actionURL_0.doStartTag();
      if (_jspx_eval_portlet_actionURL_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        out.write('\n');
        out.write('	');
        if (_jspx_meth_portlet_param_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_portlet_actionURL_0, _jspx_page_context))
          return;
        out.write('\n');
      }
      if (_jspx_th_portlet_actionURL_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_portlet_actionURL_var_name.reuse(_jspx_th_portlet_actionURL_0);
        return;
      }
      _jspx_tagPool_portlet_actionURL_var_name.reuse(_jspx_th_portlet_actionURL_0);
      java.lang.String addTemplateURL = null;
      addTemplateURL = (java.lang.String) _jspx_page_context.findAttribute("addTemplateURL");
      out.write('\n');
      out.write('\n');
      //  portlet:actionURL
      com.liferay.taglib.portlet.ActionURLTag _jspx_th_portlet_actionURL_1 = (com.liferay.taglib.portlet.ActionURLTag) _jspx_tagPool_portlet_actionURL_var_name.get(com.liferay.taglib.portlet.ActionURLTag.class);
      _jspx_th_portlet_actionURL_1.setPageContext(_jspx_page_context);
      _jspx_th_portlet_actionURL_1.setParent(null);
      _jspx_th_portlet_actionURL_1.setName("updateTemplate");
      _jspx_th_portlet_actionURL_1.setVar("updateTemplateURL");
      int _jspx_eval_portlet_actionURL_1 = _jspx_th_portlet_actionURL_1.doStartTag();
      if (_jspx_eval_portlet_actionURL_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        out.write('\n');
        out.write('	');
        if (_jspx_meth_portlet_param_1((javax.servlet.jsp.tagext.JspTag) _jspx_th_portlet_actionURL_1, _jspx_page_context))
          return;
        out.write('\n');
      }
      if (_jspx_th_portlet_actionURL_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_portlet_actionURL_var_name.reuse(_jspx_th_portlet_actionURL_1);
        return;
      }
      _jspx_tagPool_portlet_actionURL_var_name.reuse(_jspx_th_portlet_actionURL_1);
      java.lang.String updateTemplateURL = null;
      updateTemplateURL = (java.lang.String) _jspx_page_context.findAttribute("updateTemplateURL");
      out.write("\n\n<div class=\"container-fluid-1280\">\n\t");
      //  aui:form
      com.liferay.taglib.aui.FormTag _jspx_th_aui_form_0 = (com.liferay.taglib.aui.FormTag) _jspx_tagPool_aui_form_onSubmit_name_method_enctype_cssClass_action.get(com.liferay.taglib.aui.FormTag.class);
      _jspx_th_aui_form_0.setPageContext(_jspx_page_context);
      _jspx_th_aui_form_0.setParent(null);
      _jspx_th_aui_form_0.setAction( (template == null) ? addTemplateURL : updateTemplateURL );
      _jspx_th_aui_form_0.setCssClass("container-fluid-1280");
      _jspx_th_aui_form_0.setDynamicAttribute(null, "enctype", new String("multipart/form-data"));
      _jspx_th_aui_form_0.setMethod("post");
      _jspx_th_aui_form_0.setName("fm");
      _jspx_th_aui_form_0.setOnSubmit( "event.preventDefault();" );
      int _jspx_eval_aui_form_0 = _jspx_th_aui_form_0.doStartTag();
      if (_jspx_eval_aui_form_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        out.write("\n\t\t");
        //  aui:input
        com.liferay.taglib.aui.InputTag _jspx_th_aui_input_0 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
        _jspx_th_aui_input_0.setPageContext(_jspx_page_context);
        _jspx_th_aui_input_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
        _jspx_th_aui_input_0.setName("redirect");
        _jspx_th_aui_input_0.setType("hidden");
        _jspx_th_aui_input_0.setValue( ddmDisplay.getEditTemplateBackURL(liferayPortletRequest, liferayPortletResponse, classNameId, classPK, resourceClassNameId, portletResource) );
        int _jspx_eval_aui_input_0 = _jspx_th_aui_input_0.doStartTag();
        if (_jspx_th_aui_input_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_0);
          return;
        }
        _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_0);
        out.write("\n\t\t");
        //  aui:input
        com.liferay.taglib.aui.InputTag _jspx_th_aui_input_1 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
        _jspx_th_aui_input_1.setPageContext(_jspx_page_context);
        _jspx_th_aui_input_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
        _jspx_th_aui_input_1.setName("closeRedirect");
        _jspx_th_aui_input_1.setType("hidden");
        _jspx_th_aui_input_1.setValue( closeRedirect );
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
        _jspx_th_aui_input_2.setName("portletResource");
        _jspx_th_aui_input_2.setType("hidden");
        _jspx_th_aui_input_2.setValue( portletResource );
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
        _jspx_th_aui_input_3.setName("portletResourceNamespace");
        _jspx_th_aui_input_3.setType("hidden");
        _jspx_th_aui_input_3.setValue( portletResourceNamespace );
        int _jspx_eval_aui_input_3 = _jspx_th_aui_input_3.doStartTag();
        if (_jspx_th_aui_input_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_3);
          return;
        }
        _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_3);
        out.write("\n\t\t");
        //  aui:input
        com.liferay.taglib.aui.InputTag _jspx_th_aui_input_4 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
        _jspx_th_aui_input_4.setPageContext(_jspx_page_context);
        _jspx_th_aui_input_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
        _jspx_th_aui_input_4.setName("templateId");
        _jspx_th_aui_input_4.setType("hidden");
        _jspx_th_aui_input_4.setValue( templateId );
        int _jspx_eval_aui_input_4 = _jspx_th_aui_input_4.doStartTag();
        if (_jspx_th_aui_input_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_4);
          return;
        }
        _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_4);
        out.write("\n\t\t");
        //  aui:input
        com.liferay.taglib.aui.InputTag _jspx_th_aui_input_5 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
        _jspx_th_aui_input_5.setPageContext(_jspx_page_context);
        _jspx_th_aui_input_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
        _jspx_th_aui_input_5.setName("groupId");
        _jspx_th_aui_input_5.setType("hidden");
        _jspx_th_aui_input_5.setValue( groupId );
        int _jspx_eval_aui_input_5 = _jspx_th_aui_input_5.doStartTag();
        if (_jspx_th_aui_input_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_5);
          return;
        }
        _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_5);
        out.write("\n\t\t");
        //  aui:input
        com.liferay.taglib.aui.InputTag _jspx_th_aui_input_6 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
        _jspx_th_aui_input_6.setPageContext(_jspx_page_context);
        _jspx_th_aui_input_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
        _jspx_th_aui_input_6.setName("classNameId");
        _jspx_th_aui_input_6.setType("hidden");
        _jspx_th_aui_input_6.setValue( classNameId );
        int _jspx_eval_aui_input_6 = _jspx_th_aui_input_6.doStartTag();
        if (_jspx_th_aui_input_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_6);
          return;
        }
        _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_6);
        out.write("\n\t\t");
        //  aui:input
        com.liferay.taglib.aui.InputTag _jspx_th_aui_input_7 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
        _jspx_th_aui_input_7.setPageContext(_jspx_page_context);
        _jspx_th_aui_input_7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
        _jspx_th_aui_input_7.setName("classPK");
        _jspx_th_aui_input_7.setType("hidden");
        _jspx_th_aui_input_7.setValue( classPK );
        int _jspx_eval_aui_input_7 = _jspx_th_aui_input_7.doStartTag();
        if (_jspx_th_aui_input_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_7);
          return;
        }
        _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_7);
        out.write("\n\t\t");
        //  aui:input
        com.liferay.taglib.aui.InputTag _jspx_th_aui_input_8 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
        _jspx_th_aui_input_8.setPageContext(_jspx_page_context);
        _jspx_th_aui_input_8.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
        _jspx_th_aui_input_8.setName("resourceClassNameId");
        _jspx_th_aui_input_8.setType("hidden");
        _jspx_th_aui_input_8.setValue( resourceClassNameId );
        int _jspx_eval_aui_input_8 = _jspx_th_aui_input_8.doStartTag();
        if (_jspx_th_aui_input_8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_8);
          return;
        }
        _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_8);
        out.write("\n\t\t");
        //  aui:input
        com.liferay.taglib.aui.InputTag _jspx_th_aui_input_9 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
        _jspx_th_aui_input_9.setPageContext(_jspx_page_context);
        _jspx_th_aui_input_9.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
        _jspx_th_aui_input_9.setName("type");
        _jspx_th_aui_input_9.setType("hidden");
        _jspx_th_aui_input_9.setValue( type );
        int _jspx_eval_aui_input_9 = _jspx_th_aui_input_9.doStartTag();
        if (_jspx_th_aui_input_9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_9);
          return;
        }
        _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_9);
        out.write("\n\t\t");
        //  aui:input
        com.liferay.taglib.aui.InputTag _jspx_th_aui_input_10 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
        _jspx_th_aui_input_10.setPageContext(_jspx_page_context);
        _jspx_th_aui_input_10.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
        _jspx_th_aui_input_10.setName("status");
        _jspx_th_aui_input_10.setType("hidden");
        _jspx_th_aui_input_10.setValue( String.valueOf(WorkflowConstants.STATUS_APPROVED) );
        int _jspx_eval_aui_input_10 = _jspx_th_aui_input_10.doStartTag();
        if (_jspx_th_aui_input_10.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_10);
          return;
        }
        _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_10);
        out.write("\n\t\t");
        //  aui:input
        com.liferay.taglib.aui.InputTag _jspx_th_aui_input_11 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
        _jspx_th_aui_input_11.setPageContext(_jspx_page_context);
        _jspx_th_aui_input_11.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
        _jspx_th_aui_input_11.setName("structureAvailableFields");
        _jspx_th_aui_input_11.setType("hidden");
        _jspx_th_aui_input_11.setValue( structureAvailableFields );
        int _jspx_eval_aui_input_11 = _jspx_th_aui_input_11.doStartTag();
        if (_jspx_th_aui_input_11.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_11);
          return;
        }
        _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_11);
        out.write("\n\t\t");
        //  aui:input
        com.liferay.taglib.aui.InputTag _jspx_th_aui_input_12 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
        _jspx_th_aui_input_12.setPageContext(_jspx_page_context);
        _jspx_th_aui_input_12.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
        _jspx_th_aui_input_12.setName("saveAndContinue");
        _jspx_th_aui_input_12.setType("hidden");
        _jspx_th_aui_input_12.setValue( false );
        int _jspx_eval_aui_input_12 = _jspx_th_aui_input_12.doStartTag();
        if (_jspx_th_aui_input_12.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_12);
          return;
        }
        _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_12);
        out.write("\n\n\t\t<div class=\"lfr-form-content\">\n\t\t\t");
        //  liferay-ui:error
        com.liferay.taglib.ui.ErrorTag _jspx_th_liferay$1ui_error_0 = (com.liferay.taglib.ui.ErrorTag) _jspx_tagPool_liferay$1ui_error_message_exception_nobody.get(com.liferay.taglib.ui.ErrorTag.class);
        _jspx_th_liferay$1ui_error_0.setPageContext(_jspx_page_context);
        _jspx_th_liferay$1ui_error_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
        _jspx_th_liferay$1ui_error_0.setException( TemplateNameException.class );
        _jspx_th_liferay$1ui_error_0.setMessage("please-enter-a-valid-name");
        int _jspx_eval_liferay$1ui_error_0 = _jspx_th_liferay$1ui_error_0.doStartTag();
        if (_jspx_th_liferay$1ui_error_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_liferay$1ui_error_message_exception_nobody.reuse(_jspx_th_liferay$1ui_error_0);
          return;
        }
        _jspx_tagPool_liferay$1ui_error_message_exception_nobody.reuse(_jspx_th_liferay$1ui_error_0);
        out.write("\n\t\t\t");
        //  liferay-ui:error
        com.liferay.taglib.ui.ErrorTag _jspx_th_liferay$1ui_error_1 = (com.liferay.taglib.ui.ErrorTag) _jspx_tagPool_liferay$1ui_error_message_exception_nobody.get(com.liferay.taglib.ui.ErrorTag.class);
        _jspx_th_liferay$1ui_error_1.setPageContext(_jspx_page_context);
        _jspx_th_liferay$1ui_error_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
        _jspx_th_liferay$1ui_error_1.setException( TemplateScriptException.class );
        _jspx_th_liferay$1ui_error_1.setMessage("please-enter-a-valid-script");
        int _jspx_eval_liferay$1ui_error_1 = _jspx_th_liferay$1ui_error_1.doStartTag();
        if (_jspx_th_liferay$1ui_error_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_liferay$1ui_error_message_exception_nobody.reuse(_jspx_th_liferay$1ui_error_1);
          return;
        }
        _jspx_tagPool_liferay$1ui_error_message_exception_nobody.reuse(_jspx_th_liferay$1ui_error_1);
        out.write("\n\t\t\t");
        //  liferay-ui:error
        com.liferay.taglib.ui.ErrorTag _jspx_th_liferay$1ui_error_2 = (com.liferay.taglib.ui.ErrorTag) _jspx_tagPool_liferay$1ui_error_message_exception_nobody.get(com.liferay.taglib.ui.ErrorTag.class);
        _jspx_th_liferay$1ui_error_2.setPageContext(_jspx_page_context);
        _jspx_th_liferay$1ui_error_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
        _jspx_th_liferay$1ui_error_2.setException( TemplateSmallImageContentException.class );
        _jspx_th_liferay$1ui_error_2.setMessage("the-small-image-file-could-not-be-saved");
        int _jspx_eval_liferay$1ui_error_2 = _jspx_th_liferay$1ui_error_2.doStartTag();
        if (_jspx_th_liferay$1ui_error_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_liferay$1ui_error_message_exception_nobody.reuse(_jspx_th_liferay$1ui_error_2);
          return;
        }
        _jspx_tagPool_liferay$1ui_error_message_exception_nobody.reuse(_jspx_th_liferay$1ui_error_2);
        out.write("\n\n\t\t\t");
        //  liferay-ui:error
        com.liferay.taglib.ui.ErrorTag _jspx_th_liferay$1ui_error_3 = (com.liferay.taglib.ui.ErrorTag) _jspx_tagPool_liferay$1ui_error_exception.get(com.liferay.taglib.ui.ErrorTag.class);
        _jspx_th_liferay$1ui_error_3.setPageContext(_jspx_page_context);
        _jspx_th_liferay$1ui_error_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
        _jspx_th_liferay$1ui_error_3.setException( TemplateSmallImageNameException.class );
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
            out.write("\n\n\t\t\t\t");

				String[] imageExtensions = ddmDisplayContext.smallImageExtensions();
				
            out.write("\n\n\t\t\t\t");
            if (_jspx_meth_liferay$1ui_message_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1ui_error_3, _jspx_page_context))
              return;
            out.write(' ');
            out.print( HtmlUtil.escape(StringUtil.merge(imageExtensions, StringPool.COMMA)) );
            out.write(".\n\t\t\t");
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
        out.write("\n\n\t\t\t");
        //  liferay-ui:error
        com.liferay.taglib.ui.ErrorTag _jspx_th_liferay$1ui_error_4 = (com.liferay.taglib.ui.ErrorTag) _jspx_tagPool_liferay$1ui_error_exception.get(com.liferay.taglib.ui.ErrorTag.class);
        _jspx_th_liferay$1ui_error_4.setPageContext(_jspx_page_context);
        _jspx_th_liferay$1ui_error_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
        _jspx_th_liferay$1ui_error_4.setException( TemplateSmallImageSizeException.class );
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
            out.write("\n\n\t\t\t\t");

				long imageMaxSize = ddmDisplayContext.smallImageMaxSize();
				
            out.write("\n\n\t\t\t\t");
            //  liferay-ui:message
            com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_1 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody.get(com.liferay.taglib.ui.MessageTag.class);
            _jspx_th_liferay$1ui_message_1.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1ui_message_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_error_4);
            _jspx_th_liferay$1ui_message_1.setArguments( TextFormatter.formatStorageSize(imageMaxSize, locale) );
            _jspx_th_liferay$1ui_message_1.setKey("please-enter-a-small-image-with-a-valid-file-size-no-larger-than-x");
            _jspx_th_liferay$1ui_message_1.setTranslateArguments( false );
            int _jspx_eval_liferay$1ui_message_1 = _jspx_th_liferay$1ui_message_1.doStartTag();
            if (_jspx_th_liferay$1ui_message_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody.reuse(_jspx_th_liferay$1ui_message_1);
              return;
            }
            _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody.reuse(_jspx_th_liferay$1ui_message_1);
            out.write("\n\t\t\t");
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
        out.write("\n\n\t\t\t");
        //  c:if
        com.liferay.taglib.core.IfTag _jspx_th_c_if_0 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
        _jspx_th_c_if_0.setPageContext(_jspx_page_context);
        _jspx_th_c_if_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
        _jspx_th_c_if_0.setTest( showHeader );
        int _jspx_eval_c_if_0 = _jspx_th_c_if_0.doStartTag();
        if (_jspx_eval_c_if_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          out.write("\n\n\t\t\t\t");

				String title = StringPool.BLANK;

				if ((structure != null) || (template != null)) {
					title = ddmDisplay.getEditTemplateTitle(structure, template, locale);
				}
				else {
					title = ddmDisplay.getEditTemplateTitle(classNameId, locale);
				}
				
          out.write("\n\n\t\t\t\t");
          //  c:choose
          com.liferay.taglib.core.ChooseTag _jspx_th_c_choose_0 = (com.liferay.taglib.core.ChooseTag) _jspx_tagPool_c_choose.get(com.liferay.taglib.core.ChooseTag.class);
          _jspx_th_c_choose_0.setPageContext(_jspx_page_context);
          _jspx_th_c_choose_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_0);
          int _jspx_eval_c_choose_0 = _jspx_th_c_choose_0.doStartTag();
          if (_jspx_eval_c_choose_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t\t\t\t");
            //  c:when
            com.liferay.taglib.core.WhenTag _jspx_th_c_when_0 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
            _jspx_th_c_when_0.setPageContext(_jspx_page_context);
            _jspx_th_c_when_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_0);
            _jspx_th_c_when_0.setTest( ddmDisplay.isShowBackURLInTitleBar() );
            int _jspx_eval_c_when_0 = _jspx_th_c_when_0.doStartTag();
            if (_jspx_eval_c_when_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\n\t\t\t\t\t\t");

						portletDisplay.setShowBackIcon(true);
						portletDisplay.setURLBack(PortalUtil.escapeRedirect(ddmDisplay.getEditTemplateBackURL(liferayPortletRequest, liferayPortletResponse, classNameId, classPK, resourceClassNameId, portletResource)));

						renderResponse.setTitle(title);
						
              out.write("\n\n\t\t\t\t\t");
            }
            if (_jspx_th_c_when_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_0);
              return;
            }
            _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_0);
            out.write("\n\t\t\t\t\t");
            //  c:otherwise
            com.liferay.taglib.core.OtherwiseTag _jspx_th_c_otherwise_0 = (com.liferay.taglib.core.OtherwiseTag) _jspx_tagPool_c_otherwise.get(com.liferay.taglib.core.OtherwiseTag.class);
            _jspx_th_c_otherwise_0.setPageContext(_jspx_page_context);
            _jspx_th_c_otherwise_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_0);
            int _jspx_eval_c_otherwise_0 = _jspx_th_c_otherwise_0.doStartTag();
            if (_jspx_eval_c_otherwise_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\t\t\t\t\t\t");
              //  liferay-ui:header
              com.liferay.taglib.ui.HeaderTag _jspx_th_liferay$1ui_header_0 = (com.liferay.taglib.ui.HeaderTag) _jspx_tagPool_liferay$1ui_header_title_showBackURL_localizeTitle_backURL_nobody.get(com.liferay.taglib.ui.HeaderTag.class);
              _jspx_th_liferay$1ui_header_0.setPageContext(_jspx_page_context);
              _jspx_th_liferay$1ui_header_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_0);
              _jspx_th_liferay$1ui_header_0.setBackURL( PortalUtil.escapeRedirect(ddmDisplay.getEditTemplateBackURL(liferayPortletRequest, liferayPortletResponse, classNameId, classPK, resourceClassNameId, portletResource)) );
              _jspx_th_liferay$1ui_header_0.setLocalizeTitle( false );
              _jspx_th_liferay$1ui_header_0.setShowBackURL( showBackURL );
              _jspx_th_liferay$1ui_header_0.setTitle( title );
              int _jspx_eval_liferay$1ui_header_0 = _jspx_th_liferay$1ui_header_0.doStartTag();
              if (_jspx_th_liferay$1ui_header_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_liferay$1ui_header_title_showBackURL_localizeTitle_backURL_nobody.reuse(_jspx_th_liferay$1ui_header_0);
                return;
              }
              _jspx_tagPool_liferay$1ui_header_title_showBackURL_localizeTitle_backURL_nobody.reuse(_jspx_th_liferay$1ui_header_0);
              out.write("\n\t\t\t\t\t");
            }
            if (_jspx_th_c_otherwise_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_0);
              return;
            }
            _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_0);
            out.write("\n\t\t\t\t");
          }
          if (_jspx_th_c_choose_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_0);
            return;
          }
          _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_0);
          out.write("\n\t\t\t");
        }
        if (_jspx_th_c_if_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
          return;
        }
        _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
        out.write("\n\n\t\t\t");
        //  aui:model-context
        com.liferay.taglib.aui.ModelContextTag _jspx_th_aui_model$1context_0 = (com.liferay.taglib.aui.ModelContextTag) _jspx_tagPool_aui_model$1context_model_bean_nobody.get(com.liferay.taglib.aui.ModelContextTag.class);
        _jspx_th_aui_model$1context_0.setPageContext(_jspx_page_context);
        _jspx_th_aui_model$1context_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
        _jspx_th_aui_model$1context_0.setBean( template );
        _jspx_th_aui_model$1context_0.setModel( DDMTemplate.class );
        int _jspx_eval_aui_model$1context_0 = _jspx_th_aui_model$1context_0.doStartTag();
        if (_jspx_th_aui_model$1context_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_aui_model$1context_model_bean_nobody.reuse(_jspx_th_aui_model$1context_0);
          return;
        }
        _jspx_tagPool_aui_model$1context_model_bean_nobody.reuse(_jspx_th_aui_model$1context_0);
        out.write("\n\n\t\t\t");
        //  c:if
        com.liferay.taglib.core.IfTag _jspx_th_c_if_1 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
        _jspx_th_c_if_1.setPageContext(_jspx_page_context);
        _jspx_th_c_if_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
        _jspx_th_c_if_1.setTest( (templateVersion != null) && ddmDisplay.isVersioningEnabled() );
        int _jspx_eval_c_if_1 = _jspx_th_c_if_1.doStartTag();
        if (_jspx_eval_c_if_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          out.write("\n\t\t\t\t");
          //  aui:workflow-status
          com.liferay.taglib.aui.WorkflowStatusTag _jspx_th_aui_workflow$1status_0 = (com.liferay.taglib.aui.WorkflowStatusTag) _jspx_tagPool_aui_workflow$1status_version_status_model_nobody.get(com.liferay.taglib.aui.WorkflowStatusTag.class);
          _jspx_th_aui_workflow$1status_0.setPageContext(_jspx_page_context);
          _jspx_th_aui_workflow$1status_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_1);
          _jspx_th_aui_workflow$1status_0.setModel( DDMTemplate.class );
          _jspx_th_aui_workflow$1status_0.setStatus( templateVersion.getStatus() );
          _jspx_th_aui_workflow$1status_0.setVersion( templateVersion.getVersion() );
          int _jspx_eval_aui_workflow$1status_0 = _jspx_th_aui_workflow$1status_0.doStartTag();
          if (_jspx_th_aui_workflow$1status_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_aui_workflow$1status_version_status_model_nobody.reuse(_jspx_th_aui_workflow$1status_0);
            return;
          }
          _jspx_tagPool_aui_workflow$1status_version_status_model_nobody.reuse(_jspx_th_aui_workflow$1status_0);
          out.write("\n\n\t\t\t\t<div class=\"template-history-toolbar\" id=\"");
          if (_jspx_meth_portlet_namespace_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_1, _jspx_page_context))
            return;
          out.write("templateHistoryToolbar\"></div>\n\n\t\t\t\t");
          //  aui:script
          com.liferay.taglib.aui.ScriptTag _jspx_th_aui_script_0 = (com.liferay.taglib.aui.ScriptTag) _jspx_tagPool_aui_script_use.get(com.liferay.taglib.aui.ScriptTag.class);
          _jspx_th_aui_script_0.setPageContext(_jspx_page_context);
          _jspx_th_aui_script_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_1);
          _jspx_th_aui_script_0.setUse("aui-toolbar,aui-dialog-iframe-deprecated,liferay-util-window");
          int _jspx_eval_aui_script_0 = _jspx_th_aui_script_0.doStartTag();
          if (_jspx_eval_aui_script_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            if (_jspx_eval_aui_script_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
              out = _jspx_page_context.pushBody();
              _jspx_th_aui_script_0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
              _jspx_th_aui_script_0.doInitBody();
            }
            do {
              out.write("\n\t\t\t\t\tvar toolbarChildren = [\n\t\t\t\t\t\t");
              //  portlet:renderURL
              com.liferay.taglib.portlet.RenderURLTag _jspx_th_portlet_renderURL_0 = (com.liferay.taglib.portlet.RenderURLTag) _jspx_tagPool_portlet_renderURL_var.get(com.liferay.taglib.portlet.RenderURLTag.class);
              _jspx_th_portlet_renderURL_0.setPageContext(_jspx_page_context);
              _jspx_th_portlet_renderURL_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_0);
              _jspx_th_portlet_renderURL_0.setVar("viewHistoryURL");
              int _jspx_eval_portlet_renderURL_0 = _jspx_th_portlet_renderURL_0.doStartTag();
              if (_jspx_eval_portlet_renderURL_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\t\t\t\t\t\t\t");
                if (_jspx_meth_portlet_param_2((javax.servlet.jsp.tagext.JspTag) _jspx_th_portlet_renderURL_0, _jspx_page_context))
                  return;
                out.write("\n\t\t\t\t\t\t\t");
                //  portlet:param
                com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_3 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                _jspx_th_portlet_param_3.setPageContext(_jspx_page_context);
                _jspx_th_portlet_param_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_0);
                _jspx_th_portlet_param_3.setName("redirect");
                _jspx_th_portlet_param_3.setValue( ddmDisplay.getEditTemplateBackURL(liferayPortletRequest, liferayPortletResponse, classNameId, classPK, resourceClassNameId, portletResource) );
                int _jspx_eval_portlet_param_3 = _jspx_th_portlet_param_3.doStartTag();
                if (_jspx_th_portlet_param_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_3);
                  return;
                }
                _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_3);
                out.write("\n\t\t\t\t\t\t\t");
                //  portlet:param
                com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_4 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                _jspx_th_portlet_param_4.setPageContext(_jspx_page_context);
                _jspx_th_portlet_param_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_0);
                _jspx_th_portlet_param_4.setName("templateId");
                _jspx_th_portlet_param_4.setValue( String.valueOf(template.getTemplateId()) );
                int _jspx_eval_portlet_param_4 = _jspx_th_portlet_param_4.doStartTag();
                if (_jspx_th_portlet_param_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_4);
                  return;
                }
                _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_4);
                out.write("\n\t\t\t\t\t\t");
              }
              if (_jspx_th_portlet_renderURL_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_portlet_renderURL_var.reuse(_jspx_th_portlet_renderURL_0);
                return;
              }
              _jspx_tagPool_portlet_renderURL_var.reuse(_jspx_th_portlet_renderURL_0);
              java.lang.String viewHistoryURL = null;
              viewHistoryURL = (java.lang.String) _jspx_page_context.findAttribute("viewHistoryURL");
              out.write("\n\n\t\t\t\t\t\t{\n\t\t\t\t\t\t\ticon: 'icon-time',\n\t\t\t\t\t\t\tlabel: '");
              out.print( UnicodeLanguageUtil.get(request, "view-history") );
              out.write("',\n\t\t\t\t\t\t\ton: {\n\t\t\t\t\t\t\t\tclick: function(event) {\n\t\t\t\t\t\t\t\t\tevent.domEvent.preventDefault();\n\n\t\t\t\t\t\t\t\t\twindow.location.href = '");
              out.print( viewHistoryURL );
              out.write("';\n\t\t\t\t\t\t\t\t}\n\t\t\t\t\t\t\t}\n\t\t\t\t\t\t}\n\t\t\t\t\t];\n\n\t\t\t\t\tnew A.Toolbar(\n\t\t\t\t\t\t{\n\t\t\t\t\t\t\tboundingBox: '#");
              if (_jspx_meth_portlet_namespace_1((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_0, _jspx_page_context))
                return;
              out.write("templateHistoryToolbar',\n\t\t\t\t\t\t\tchildren: toolbarChildren\n\t\t\t\t\t\t}\n\t\t\t\t\t).render();\n\t\t\t\t");
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
          out.write("\n\t\t\t");
        }
        if (_jspx_th_c_if_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_1);
          return;
        }
        _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_1);
        out.write("\n\n\t\t\t");
        //  aui:fieldset-group
        com.liferay.taglib.aui.FieldsetGroupTag _jspx_th_aui_fieldset$1group_0 = (com.liferay.taglib.aui.FieldsetGroupTag) _jspx_tagPool_aui_fieldset$1group_markupView.get(com.liferay.taglib.aui.FieldsetGroupTag.class);
        _jspx_th_aui_fieldset$1group_0.setPageContext(_jspx_page_context);
        _jspx_th_aui_fieldset$1group_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
        _jspx_th_aui_fieldset$1group_0.setMarkupView("lexicon");
        int _jspx_eval_aui_fieldset$1group_0 = _jspx_th_aui_fieldset$1group_0.doStartTag();
        if (_jspx_eval_aui_fieldset$1group_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          out.write("\n\t\t\t\t");
          //  aui:fieldset
          com.liferay.taglib.aui.FieldsetTag _jspx_th_aui_fieldset_0 = (com.liferay.taglib.aui.FieldsetTag) _jspx_tagPool_aui_fieldset.get(com.liferay.taglib.aui.FieldsetTag.class);
          _jspx_th_aui_fieldset_0.setPageContext(_jspx_page_context);
          _jspx_th_aui_fieldset_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset$1group_0);
          int _jspx_eval_aui_fieldset_0 = _jspx_th_aui_fieldset_0.doStartTag();
          if (_jspx_eval_aui_fieldset_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t\t\t\t");
            //  c:if
            com.liferay.taglib.core.IfTag _jspx_th_c_if_2 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
            _jspx_th_c_if_2.setPageContext(_jspx_page_context);
            _jspx_th_c_if_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_0);
            _jspx_th_c_if_2.setTest( (template != null) && (groupId != PortalUtil.getScopeGroupId(request, refererPortletName)) );
            int _jspx_eval_c_if_2 = _jspx_th_c_if_2.doStartTag();
            if (_jspx_eval_c_if_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\t\t\t\t\t\t");
              if (_jspx_meth_aui_field$1wrapper_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_2, _jspx_page_context))
                return;
              out.write("\n\t\t\t\t\t");
            }
            if (_jspx_th_c_if_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_2);
              return;
            }
            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_2);
            out.write("\n\n\t\t\t\t\t");
            //  aui:input
            com.liferay.taglib.aui.InputTag _jspx_th_aui_input_13 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_name_autoFocus_nobody.get(com.liferay.taglib.aui.InputTag.class);
            _jspx_th_aui_input_13.setPageContext(_jspx_page_context);
            _jspx_th_aui_input_13.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_0);
            _jspx_th_aui_input_13.setAutoFocus( windowState.equals(WindowState.MAXIMIZED) || windowState.equals(LiferayWindowState.POP_UP) );
            _jspx_th_aui_input_13.setName("name");
            int _jspx_eval_aui_input_13 = _jspx_th_aui_input_13.doStartTag();
            if (_jspx_th_aui_input_13.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_aui_input_name_autoFocus_nobody.reuse(_jspx_th_aui_input_13);
              return;
            }
            _jspx_tagPool_aui_input_name_autoFocus_nobody.reuse(_jspx_th_aui_input_13);
            out.write("\n\n\t\t\t\t\t");
            //  liferay-ui:panel-container
            com.liferay.taglib.ui.PanelContainerTag _jspx_th_liferay$1ui_panel$1container_0 = (com.liferay.taglib.ui.PanelContainerTag) _jspx_tagPool_liferay$1ui_panel$1container_persistState_id_extended_cssClass.get(com.liferay.taglib.ui.PanelContainerTag.class);
            _jspx_th_liferay$1ui_panel$1container_0.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1ui_panel$1container_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_0);
            _jspx_th_liferay$1ui_panel$1container_0.setCssClass("lfr-structure-entry-details-container");
            _jspx_th_liferay$1ui_panel$1container_0.setExtended( false );
            _jspx_th_liferay$1ui_panel$1container_0.setId("templateDetailsPanelContainer");
            _jspx_th_liferay$1ui_panel$1container_0.setPersistState( true );
            int _jspx_eval_liferay$1ui_panel$1container_0 = _jspx_th_liferay$1ui_panel$1container_0.doStartTag();
            if (_jspx_eval_liferay$1ui_panel$1container_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              if (_jspx_eval_liferay$1ui_panel$1container_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                out = _jspx_page_context.pushBody();
                _jspx_th_liferay$1ui_panel$1container_0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                _jspx_th_liferay$1ui_panel$1container_0.doInitBody();
              }
              do {
                out.write("\n\t\t\t\t\t\t");
                //  liferay-ui:panel
                com.liferay.taglib.ui.PanelTag _jspx_th_liferay$1ui_panel_0 = (com.liferay.taglib.ui.PanelTag) _jspx_tagPool_liferay$1ui_panel_title_persistState_markupView_id_extended_defaultState_collapsible.get(com.liferay.taglib.ui.PanelTag.class);
                _jspx_th_liferay$1ui_panel_0.setPageContext(_jspx_page_context);
                _jspx_th_liferay$1ui_panel_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_panel$1container_0);
                _jspx_th_liferay$1ui_panel_0.setCollapsible( true );
                _jspx_th_liferay$1ui_panel_0.setDefaultState("closed");
                _jspx_th_liferay$1ui_panel_0.setExtended( false );
                _jspx_th_liferay$1ui_panel_0.setId("templateDetailsSectionPanel");
                _jspx_th_liferay$1ui_panel_0.setMarkupView("lexicon");
                _jspx_th_liferay$1ui_panel_0.setPersistState( true );
                _jspx_th_liferay$1ui_panel_0.setTitle("details");
                int _jspx_eval_liferay$1ui_panel_0 = _jspx_th_liferay$1ui_panel_0.doStartTag();
                if (_jspx_eval_liferay$1ui_panel_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  out.write("\n\t\t\t\t\t\t\t");
                  //  c:if
                  com.liferay.taglib.core.IfTag _jspx_th_c_if_3 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                  _jspx_th_c_if_3.setPageContext(_jspx_page_context);
                  _jspx_th_c_if_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_panel_0);
                  _jspx_th_c_if_3.setTest( ddmDisplay.isShowStructureSelector() );
                  int _jspx_eval_c_if_3 = _jspx_th_c_if_3.doStartTag();
                  if (_jspx_eval_c_if_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    out.write("\n\t\t\t\t\t\t\t\t<div class=\"form-group\">\n\t\t\t\t\t\t\t\t\t");
                    //  aui:input
                    com.liferay.taglib.aui.InputTag _jspx_th_aui_input_14 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_helpMessage_nobody.get(com.liferay.taglib.aui.InputTag.class);
                    _jspx_th_aui_input_14.setPageContext(_jspx_page_context);
                    _jspx_th_aui_input_14.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_3);
                    _jspx_th_aui_input_14.setHelpMessage("structure-help");
                    _jspx_th_aui_input_14.setName("structure");
                    _jspx_th_aui_input_14.setType("resource");
                    _jspx_th_aui_input_14.setValue( (structure != null) ? structure.getName(locale) : StringPool.BLANK );
                    int _jspx_eval_aui_input_14 = _jspx_th_aui_input_14.doStartTag();
                    if (_jspx_th_aui_input_14.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_aui_input_value_type_name_helpMessage_nobody.reuse(_jspx_th_aui_input_14);
                      return;
                    }
                    _jspx_tagPool_aui_input_value_type_name_helpMessage_nobody.reuse(_jspx_th_aui_input_14);
                    out.write("\n\n\t\t\t\t\t\t\t\t\t");
                    //  c:if
                    com.liferay.taglib.core.IfTag _jspx_th_c_if_4 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                    _jspx_th_c_if_4.setPageContext(_jspx_page_context);
                    _jspx_th_c_if_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_3);
                    _jspx_th_c_if_4.setTest( ddmNavigationHelper.isNavigationStartsOnViewTemplates(liferayPortletRequest) && ((template == null) || (template.getClassPK() == 0)) );
                    int _jspx_eval_c_if_4 = _jspx_th_c_if_4.doStartTag();
                    if (_jspx_eval_c_if_4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      out.write("\n\t\t\t\t\t\t\t\t\t\t");
                      //  liferay-ui:icon
                      com.liferay.taglib.ui.IconTag _jspx_th_liferay$1ui_icon_0 = (com.liferay.taglib.ui.IconTag) _jspx_tagPool_liferay$1ui_icon_url_message_linkCssClass_label_iconCssClass_nobody.get(com.liferay.taglib.ui.IconTag.class);
                      _jspx_th_liferay$1ui_icon_0.setPageContext(_jspx_page_context);
                      _jspx_th_liferay$1ui_icon_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_4);
                      _jspx_th_liferay$1ui_icon_0.setIconCssClass("icon-search");
                      _jspx_th_liferay$1ui_icon_0.setLabel( true );
                      _jspx_th_liferay$1ui_icon_0.setLinkCssClass("btn btn-default");
                      _jspx_th_liferay$1ui_icon_0.setMessage("select");
                      _jspx_th_liferay$1ui_icon_0.setUrl( "javascript:" + renderResponse.getNamespace() + "openDDMStructureSelector();" );
                      int _jspx_eval_liferay$1ui_icon_0 = _jspx_th_liferay$1ui_icon_0.doStartTag();
                      if (_jspx_th_liferay$1ui_icon_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_liferay$1ui_icon_url_message_linkCssClass_label_iconCssClass_nobody.reuse(_jspx_th_liferay$1ui_icon_0);
                        return;
                      }
                      _jspx_tagPool_liferay$1ui_icon_url_message_linkCssClass_label_iconCssClass_nobody.reuse(_jspx_th_liferay$1ui_icon_0);
                      out.write("\n\t\t\t\t\t\t\t\t\t");
                    }
                    if (_jspx_th_c_if_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_4);
                      return;
                    }
                    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_4);
                    out.write("\n\t\t\t\t\t\t\t\t</div>\n\t\t\t\t\t\t\t");
                  }
                  if (_jspx_th_c_if_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_3);
                    return;
                  }
                  _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_3);
                  out.write("\n\n\t\t\t\t\t\t\t");
                  //  c:if
                  com.liferay.taglib.core.IfTag _jspx_th_c_if_5 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                  _jspx_th_c_if_5.setPageContext(_jspx_page_context);
                  _jspx_th_c_if_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_panel_0);
                  _jspx_th_c_if_5.setTest( type.equals(DDMTemplateConstants.TEMPLATE_TYPE_DISPLAY) );
                  int _jspx_eval_c_if_5 = _jspx_th_c_if_5.doStartTag();
                  if (_jspx_eval_c_if_5 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    out.write("\n\t\t\t\t\t\t\t\t");
                    //  aui:select
                    com.liferay.taglib.aui.SelectTag _jspx_th_aui_select_0 = (com.liferay.taglib.aui.SelectTag) _jspx_tagPool_aui_select_name_label_helpMessage_changesContext.get(com.liferay.taglib.aui.SelectTag.class);
                    _jspx_th_aui_select_0.setPageContext(_jspx_page_context);
                    _jspx_th_aui_select_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_5);
                    _jspx_th_aui_select_0.setChangesContext( true );
                    _jspx_th_aui_select_0.setHelpMessage( (template == null) ? StringPool.BLANK : "changing-the-language-does-not-automatically-translate-the-existing-template-script" );
                    _jspx_th_aui_select_0.setLabel("language");
                    _jspx_th_aui_select_0.setName("language");
                    int _jspx_eval_aui_select_0 = _jspx_th_aui_select_0.doStartTag();
                    if (_jspx_eval_aui_select_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      if (_jspx_eval_aui_select_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                        out = _jspx_page_context.pushBody();
                        _jspx_th_aui_select_0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                        _jspx_th_aui_select_0.doInitBody();
                      }
                      do {
                        out.write("\n\n\t\t\t\t\t\t\t\t\t");

									for (String curLangType : ddmDisplay.getTemplateLanguageTypes()) {
										StringBundler sb = new StringBundler(6);

										sb.append(LanguageUtil.get(request, curLangType + "[stands-for]"));
										sb.append(StringPool.SPACE);
										sb.append(StringPool.OPEN_PARENTHESIS);
										sb.append(StringPool.PERIOD);
										sb.append(curLangType);
										sb.append(StringPool.CLOSE_PARENTHESIS);
									
                        out.write("\n\n\t\t\t\t\t\t\t\t\t\t");
                        //  aui:option
                        com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_0 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_value_selected_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
                        _jspx_th_aui_option_0.setPageContext(_jspx_page_context);
                        _jspx_th_aui_option_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_0);
                        _jspx_th_aui_option_0.setLabel( sb.toString() );
                        _jspx_th_aui_option_0.setSelected( language.equals(curLangType) );
                        _jspx_th_aui_option_0.setValue( curLangType );
                        int _jspx_eval_aui_option_0 = _jspx_th_aui_option_0.doStartTag();
                        if (_jspx_th_aui_option_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                          _jspx_tagPool_aui_option_value_selected_label_nobody.reuse(_jspx_th_aui_option_0);
                          return;
                        }
                        _jspx_tagPool_aui_option_value_selected_label_nobody.reuse(_jspx_th_aui_option_0);
                        out.write("\n\n\t\t\t\t\t\t\t\t\t");

									}
									
                        out.write("\n\n\t\t\t\t\t\t\t\t");
                        int evalDoAfterBody = _jspx_th_aui_select_0.doAfterBody();
                        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                          break;
                      } while (true);
                      if (_jspx_eval_aui_select_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                        out = _jspx_page_context.popBody();
                    }
                    if (_jspx_th_aui_select_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_aui_select_name_label_helpMessage_changesContext.reuse(_jspx_th_aui_select_0);
                      return;
                    }
                    _jspx_tagPool_aui_select_name_label_helpMessage_changesContext.reuse(_jspx_th_aui_select_0);
                    out.write("\n\t\t\t\t\t\t\t");
                  }
                  if (_jspx_th_c_if_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_5);
                    return;
                  }
                  _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_5);
                  out.write("\n\n\t\t\t\t\t\t\t");
                  //  c:if
                  com.liferay.taglib.core.IfTag _jspx_th_c_if_6 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                  _jspx_th_c_if_6.setPageContext(_jspx_page_context);
                  _jspx_th_c_if_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_panel_0);
                  _jspx_th_c_if_6.setTest( !ddmDisplayContext.autogenerateTemplateKey() );
                  int _jspx_eval_c_if_6 = _jspx_th_c_if_6.doStartTag();
                  if (_jspx_eval_c_if_6 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    out.write("\n\t\t\t\t\t\t\t\t");
                    //  aui:input
                    com.liferay.taglib.aui.InputTag _jspx_th_aui_input_15 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_name_disabled_nobody.get(com.liferay.taglib.aui.InputTag.class);
                    _jspx_th_aui_input_15.setPageContext(_jspx_page_context);
                    _jspx_th_aui_input_15.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_6);
                    _jspx_th_aui_input_15.setDisabled( (template != null) ? true : false );
                    _jspx_th_aui_input_15.setName("templateKey");
                    int _jspx_eval_aui_input_15 = _jspx_th_aui_input_15.doStartTag();
                    if (_jspx_th_aui_input_15.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_aui_input_name_disabled_nobody.reuse(_jspx_th_aui_input_15);
                      return;
                    }
                    _jspx_tagPool_aui_input_name_disabled_nobody.reuse(_jspx_th_aui_input_15);
                    out.write("\n\t\t\t\t\t\t\t");
                  }
                  if (_jspx_th_c_if_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_6);
                    return;
                  }
                  _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_6);
                  out.write("\n\n\t\t\t\t\t\t\t");
                  if (_jspx_meth_aui_input_16((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1ui_panel_0, _jspx_page_context))
                    return;
                  out.write("\n\n\t\t\t\t\t\t\t");
                  //  c:if
                  com.liferay.taglib.core.IfTag _jspx_th_c_if_7 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                  _jspx_th_c_if_7.setPageContext(_jspx_page_context);
                  _jspx_th_c_if_7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_panel_0);
                  _jspx_th_c_if_7.setTest( template != null );
                  int _jspx_eval_c_if_7 = _jspx_th_c_if_7.doStartTag();
                  if (_jspx_eval_c_if_7 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    out.write("\n\t\t\t\t\t\t\t\t");
                    //  aui:input
                    com.liferay.taglib.aui.InputTag _jspx_th_aui_input_17 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_helpMessage_nobody.get(com.liferay.taglib.aui.InputTag.class);
                    _jspx_th_aui_input_17.setPageContext(_jspx_page_context);
                    _jspx_th_aui_input_17.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_7);
                    _jspx_th_aui_input_17.setHelpMessage("template-key-help");
                    _jspx_th_aui_input_17.setName("templateKey");
                    _jspx_th_aui_input_17.setType("resource");
                    _jspx_th_aui_input_17.setValue( template.getTemplateKey() );
                    int _jspx_eval_aui_input_17 = _jspx_th_aui_input_17.doStartTag();
                    if (_jspx_th_aui_input_17.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_aui_input_value_type_name_helpMessage_nobody.reuse(_jspx_th_aui_input_17);
                      return;
                    }
                    _jspx_tagPool_aui_input_value_type_name_helpMessage_nobody.reuse(_jspx_th_aui_input_17);
                    out.write("\n\n\t\t\t\t\t\t\t\t");
                    //  portlet:resourceURL
                    com.liferay.taglib.portlet.ResourceURLTag _jspx_th_portlet_resourceURL_0 = (com.liferay.taglib.portlet.ResourceURLTag) _jspx_tagPool_portlet_resourceURL_var_id.get(com.liferay.taglib.portlet.ResourceURLTag.class);
                    _jspx_th_portlet_resourceURL_0.setPageContext(_jspx_page_context);
                    _jspx_th_portlet_resourceURL_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_7);
                    _jspx_th_portlet_resourceURL_0.setId("getTemplate");
                    _jspx_th_portlet_resourceURL_0.setVar("getTemplateURL");
                    int _jspx_eval_portlet_resourceURL_0 = _jspx_th_portlet_resourceURL_0.doStartTag();
                    if (_jspx_eval_portlet_resourceURL_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      out.write("\n\t\t\t\t\t\t\t\t\t");
                      //  portlet:param
                      com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_5 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                      _jspx_th_portlet_param_5.setPageContext(_jspx_page_context);
                      _jspx_th_portlet_param_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_resourceURL_0);
                      _jspx_th_portlet_param_5.setName("templateId");
                      _jspx_th_portlet_param_5.setValue( String.valueOf(templateId) );
                      int _jspx_eval_portlet_param_5 = _jspx_th_portlet_param_5.doStartTag();
                      if (_jspx_th_portlet_param_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_5);
                        return;
                      }
                      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_5);
                      out.write("\n\t\t\t\t\t\t\t\t");
                    }
                    if (_jspx_th_portlet_resourceURL_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_portlet_resourceURL_var_id.reuse(_jspx_th_portlet_resourceURL_0);
                      return;
                    }
                    _jspx_tagPool_portlet_resourceURL_var_id.reuse(_jspx_th_portlet_resourceURL_0);
                    java.lang.String getTemplateURL = null;
                    getTemplateURL = (java.lang.String) _jspx_page_context.findAttribute("getTemplateURL");
                    out.write("\n\n\t\t\t\t\t\t\t\t");
                    //  aui:input
                    com.liferay.taglib.aui.InputTag _jspx_th_aui_input_18 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
                    _jspx_th_aui_input_18.setPageContext(_jspx_page_context);
                    _jspx_th_aui_input_18.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_7);
                    _jspx_th_aui_input_18.setName("url");
                    _jspx_th_aui_input_18.setType("resource");
                    _jspx_th_aui_input_18.setValue( getTemplateURL.toString() );
                    int _jspx_eval_aui_input_18 = _jspx_th_aui_input_18.doStartTag();
                    if (_jspx_th_aui_input_18.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_18);
                      return;
                    }
                    _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_18);
                    out.write("\n\n\t\t\t\t\t\t\t\t");
                    //  c:if
                    com.liferay.taglib.core.IfTag _jspx_th_c_if_8 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                    _jspx_th_c_if_8.setPageContext(_jspx_page_context);
                    _jspx_th_c_if_8.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_7);
                    _jspx_th_c_if_8.setTest( Validator.isNotNull(refererWebDAVToken) );
                    int _jspx_eval_c_if_8 = _jspx_th_c_if_8.doStartTag();
                    if (_jspx_eval_c_if_8 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      out.write("\n\t\t\t\t\t\t\t\t\t");
                      //  aui:input
                      com.liferay.taglib.aui.InputTag _jspx_th_aui_input_19 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
                      _jspx_th_aui_input_19.setPageContext(_jspx_page_context);
                      _jspx_th_aui_input_19.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_8);
                      _jspx_th_aui_input_19.setName("webDavURL");
                      _jspx_th_aui_input_19.setType("resource");
                      _jspx_th_aui_input_19.setValue( template.getWebDavURL(themeDisplay, refererWebDAVToken) );
                      int _jspx_eval_aui_input_19 = _jspx_th_aui_input_19.doStartTag();
                      if (_jspx_th_aui_input_19.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_19);
                        return;
                      }
                      _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_19);
                      out.write("\n\t\t\t\t\t\t\t\t");
                    }
                    if (_jspx_th_c_if_8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_8);
                      return;
                    }
                    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_8);
                    out.write("\n\t\t\t\t\t\t\t");
                  }
                  if (_jspx_th_c_if_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_7);
                    return;
                  }
                  _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_7);
                  out.write("\n\n\t\t\t\t\t\t\t");
                  //  c:choose
                  com.liferay.taglib.core.ChooseTag _jspx_th_c_choose_1 = (com.liferay.taglib.core.ChooseTag) _jspx_tagPool_c_choose.get(com.liferay.taglib.core.ChooseTag.class);
                  _jspx_th_c_choose_1.setPageContext(_jspx_page_context);
                  _jspx_th_c_choose_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_panel_0);
                  int _jspx_eval_c_choose_1 = _jspx_th_c_choose_1.doStartTag();
                  if (_jspx_eval_c_choose_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    out.write("\n\t\t\t\t\t\t\t\t");
                    //  c:when
                    com.liferay.taglib.core.WhenTag _jspx_th_c_when_1 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
                    _jspx_th_c_when_1.setPageContext(_jspx_page_context);
                    _jspx_th_c_when_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_1);
                    _jspx_th_c_when_1.setTest( type.equals(DDMTemplateConstants.TEMPLATE_TYPE_FORM) );
                    int _jspx_eval_c_when_1 = _jspx_th_c_when_1.doStartTag();
                    if (_jspx_eval_c_when_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      out.write("\n\t\t\t\t\t\t\t\t\t");
                      if (_jspx_meth_aui_select_1((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_when_1, _jspx_page_context))
                        return;
                      out.write("\n\t\t\t\t\t\t\t\t");
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
                      //  c:if
                      com.liferay.taglib.core.IfTag _jspx_th_c_if_9 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                      _jspx_th_c_if_9.setPageContext(_jspx_page_context);
                      _jspx_th_c_if_9.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_1);
                      _jspx_th_c_if_9.setTest( showCacheableInput );
                      int _jspx_eval_c_if_9 = _jspx_th_c_if_9.doStartTag();
                      if (_jspx_eval_c_if_9 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                        out.write("\n\t\t\t\t\t\t\t\t\t\t");
                        //  aui:input
                        com.liferay.taglib.aui.InputTag _jspx_th_aui_input_20 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_name_helpMessage_nobody.get(com.liferay.taglib.aui.InputTag.class);
                        _jspx_th_aui_input_20.setPageContext(_jspx_page_context);
                        _jspx_th_aui_input_20.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_9);
                        _jspx_th_aui_input_20.setHelpMessage("journal-template-cacheable-help");
                        _jspx_th_aui_input_20.setName("cacheable");
                        _jspx_th_aui_input_20.setValue( cacheable );
                        int _jspx_eval_aui_input_20 = _jspx_th_aui_input_20.doStartTag();
                        if (_jspx_th_aui_input_20.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                          _jspx_tagPool_aui_input_value_name_helpMessage_nobody.reuse(_jspx_th_aui_input_20);
                          return;
                        }
                        _jspx_tagPool_aui_input_value_name_helpMessage_nobody.reuse(_jspx_th_aui_input_20);
                        out.write("\n\t\t\t\t\t\t\t\t\t");
                      }
                      if (_jspx_th_c_if_9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_9);
                        return;
                      }
                      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_9);
                      out.write("\n\n\t\t\t\t\t\t\t\t\t<div id=\"");
                      if (_jspx_meth_portlet_namespace_2((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_otherwise_1, _jspx_page_context))
                        return;
                      out.write("smallImageContainer\">\n\t\t\t\t\t\t\t\t\t\t<div class=\"lfr-ddm-small-image-header\">\n\t\t\t\t\t\t\t\t\t\t\t");
                      if (_jspx_meth_aui_input_21((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_otherwise_1, _jspx_page_context))
                        return;
                      out.write("\n\t\t\t\t\t\t\t\t\t\t</div>\n\n\t\t\t\t\t\t\t\t\t\t<div class=\"lfr-ddm-small-image-content toggler-content-collapsed\">\n\t\t\t\t\t\t\t\t\t\t\t");
                      //  aui:row
                      com.liferay.taglib.aui.RowTag _jspx_th_aui_row_0 = (com.liferay.taglib.aui.RowTag) _jspx_tagPool_aui_row.get(com.liferay.taglib.aui.RowTag.class);
                      _jspx_th_aui_row_0.setPageContext(_jspx_page_context);
                      _jspx_th_aui_row_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_1);
                      int _jspx_eval_aui_row_0 = _jspx_th_aui_row_0.doStartTag();
                      if (_jspx_eval_aui_row_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                        out.write("\n\t\t\t\t\t\t\t\t\t\t\t\t");
                        //  c:if
                        com.liferay.taglib.core.IfTag _jspx_th_c_if_10 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                        _jspx_th_c_if_10.setPageContext(_jspx_page_context);
                        _jspx_th_c_if_10.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_row_0);
                        _jspx_th_c_if_10.setTest( smallImage && (template != null) );
                        int _jspx_eval_c_if_10 = _jspx_th_c_if_10.doStartTag();
                        if (_jspx_eval_c_if_10 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                          out.write("\n\t\t\t\t\t\t\t\t\t\t\t\t\t");
                          //  aui:col
                          com.liferay.taglib.aui.ColTag _jspx_th_aui_col_0 = (com.liferay.taglib.aui.ColTag) _jspx_tagPool_aui_col_width.get(com.liferay.taglib.aui.ColTag.class);
                          _jspx_th_aui_col_0.setPageContext(_jspx_page_context);
                          _jspx_th_aui_col_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_10);
                          _jspx_th_aui_col_0.setWidth( 50 );
                          int _jspx_eval_aui_col_0 = _jspx_th_aui_col_0.doStartTag();
                          if (_jspx_eval_aui_col_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                            out.write("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t<img alt=\"");
                            //  liferay-ui:message
                            com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_3 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_escapeAttribute_nobody.get(com.liferay.taglib.ui.MessageTag.class);
                            _jspx_th_liferay$1ui_message_3.setPageContext(_jspx_page_context);
                            _jspx_th_liferay$1ui_message_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_col_0);
                            _jspx_th_liferay$1ui_message_3.setEscapeAttribute( true );
                            _jspx_th_liferay$1ui_message_3.setKey("preview");
                            int _jspx_eval_liferay$1ui_message_3 = _jspx_th_liferay$1ui_message_3.doStartTag();
                            if (_jspx_th_liferay$1ui_message_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_liferay$1ui_message_key_escapeAttribute_nobody.reuse(_jspx_th_liferay$1ui_message_3);
                              return;
                            }
                            _jspx_tagPool_liferay$1ui_message_key_escapeAttribute_nobody.reuse(_jspx_th_liferay$1ui_message_3);
                            out.write("\" class=\"lfr-ddm-small-image-preview\" src=\"");
                            out.print( HtmlUtil.escapeAttribute(template.getTemplateImageURL(themeDisplay)) );
                            out.write("\" />\n\t\t\t\t\t\t\t\t\t\t\t\t\t");
                          }
                          if (_jspx_th_aui_col_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                            _jspx_tagPool_aui_col_width.reuse(_jspx_th_aui_col_0);
                            return;
                          }
                          _jspx_tagPool_aui_col_width.reuse(_jspx_th_aui_col_0);
                          out.write("\n\t\t\t\t\t\t\t\t\t\t\t\t");
                        }
                        if (_jspx_th_c_if_10.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                          _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_10);
                          return;
                        }
                        _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_10);
                        out.write("\n\n\t\t\t\t\t\t\t\t\t\t\t\t");
                        //  aui:col
                        com.liferay.taglib.aui.ColTag _jspx_th_aui_col_1 = (com.liferay.taglib.aui.ColTag) _jspx_tagPool_aui_col_width.get(com.liferay.taglib.aui.ColTag.class);
                        _jspx_th_aui_col_1.setPageContext(_jspx_page_context);
                        _jspx_th_aui_col_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_row_0);
                        _jspx_th_aui_col_1.setWidth( (smallImage && (template != null)) ? 50 : 100 );
                        int _jspx_eval_aui_col_1 = _jspx_th_aui_col_1.doStartTag();
                        if (_jspx_eval_aui_col_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                          out.write("\n\t\t\t\t\t\t\t\t\t\t\t\t\t");
                          //  aui:fieldset
                          com.liferay.taglib.aui.FieldsetTag _jspx_th_aui_fieldset_1 = (com.liferay.taglib.aui.FieldsetTag) _jspx_tagPool_aui_fieldset.get(com.liferay.taglib.aui.FieldsetTag.class);
                          _jspx_th_aui_fieldset_1.setPageContext(_jspx_page_context);
                          _jspx_th_aui_fieldset_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_col_1);
                          int _jspx_eval_aui_fieldset_1 = _jspx_th_aui_fieldset_1.doStartTag();
                          if (_jspx_eval_aui_fieldset_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                            out.write("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
                            //  aui:input
                            com.liferay.taglib.aui.InputTag _jspx_th_aui_input_22 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_type_name_label_inlineField_cssClass_nobody.get(com.liferay.taglib.aui.InputTag.class);
                            _jspx_th_aui_input_22.setPageContext(_jspx_page_context);
                            _jspx_th_aui_input_22.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_1);
                            _jspx_th_aui_input_22.setCssClass("lfr-ddm-small-image-type");
                            _jspx_th_aui_input_22.setInlineField( true );
                            _jspx_th_aui_input_22.setLabel("small-image-url");
                            _jspx_th_aui_input_22.setName("type");
                            _jspx_th_aui_input_22.setType("radio");
                            int _jspx_eval_aui_input_22 = _jspx_th_aui_input_22.doStartTag();
                            if (_jspx_th_aui_input_22.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_aui_input_type_name_label_inlineField_cssClass_nobody.reuse(_jspx_th_aui_input_22);
                              return;
                            }
                            _jspx_tagPool_aui_input_type_name_label_inlineField_cssClass_nobody.reuse(_jspx_th_aui_input_22);
                            out.write("\n\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
                            //  aui:input
                            com.liferay.taglib.aui.InputTag _jspx_th_aui_input_23 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_title_name_label_inlineField_cssClass_nobody.get(com.liferay.taglib.aui.InputTag.class);
                            _jspx_th_aui_input_23.setPageContext(_jspx_page_context);
                            _jspx_th_aui_input_23.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_1);
                            _jspx_th_aui_input_23.setCssClass("lfr-ddm-small-image-value");
                            _jspx_th_aui_input_23.setInlineField( true );
                            _jspx_th_aui_input_23.setLabel("");
                            _jspx_th_aui_input_23.setName("smallImageURL");
                            _jspx_th_aui_input_23.setTitle("small-image-url");
                            int _jspx_eval_aui_input_23 = _jspx_th_aui_input_23.doStartTag();
                            if (_jspx_th_aui_input_23.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_aui_input_title_name_label_inlineField_cssClass_nobody.reuse(_jspx_th_aui_input_23);
                              return;
                            }
                            _jspx_tagPool_aui_input_title_name_label_inlineField_cssClass_nobody.reuse(_jspx_th_aui_input_23);
                            out.write("\n\t\t\t\t\t\t\t\t\t\t\t\t\t");
                          }
                          if (_jspx_th_aui_fieldset_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                            _jspx_tagPool_aui_fieldset.reuse(_jspx_th_aui_fieldset_1);
                            return;
                          }
                          _jspx_tagPool_aui_fieldset.reuse(_jspx_th_aui_fieldset_1);
                          out.write("\n\n\t\t\t\t\t\t\t\t\t\t\t\t\t");
                          //  aui:fieldset
                          com.liferay.taglib.aui.FieldsetTag _jspx_th_aui_fieldset_2 = (com.liferay.taglib.aui.FieldsetTag) _jspx_tagPool_aui_fieldset.get(com.liferay.taglib.aui.FieldsetTag.class);
                          _jspx_th_aui_fieldset_2.setPageContext(_jspx_page_context);
                          _jspx_th_aui_fieldset_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_col_1);
                          int _jspx_eval_aui_fieldset_2 = _jspx_th_aui_fieldset_2.doStartTag();
                          if (_jspx_eval_aui_fieldset_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                            out.write("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
                            //  aui:input
                            com.liferay.taglib.aui.InputTag _jspx_th_aui_input_24 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_type_name_label_inlineField_cssClass_nobody.get(com.liferay.taglib.aui.InputTag.class);
                            _jspx_th_aui_input_24.setPageContext(_jspx_page_context);
                            _jspx_th_aui_input_24.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_2);
                            _jspx_th_aui_input_24.setCssClass("lfr-ddm-small-image-type");
                            _jspx_th_aui_input_24.setInlineField( true );
                            _jspx_th_aui_input_24.setLabel("small-image");
                            _jspx_th_aui_input_24.setName("type");
                            _jspx_th_aui_input_24.setType("radio");
                            int _jspx_eval_aui_input_24 = _jspx_th_aui_input_24.doStartTag();
                            if (_jspx_th_aui_input_24.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_aui_input_type_name_label_inlineField_cssClass_nobody.reuse(_jspx_th_aui_input_24);
                              return;
                            }
                            _jspx_tagPool_aui_input_type_name_label_inlineField_cssClass_nobody.reuse(_jspx_th_aui_input_24);
                            out.write("\n\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
                            //  aui:input
                            com.liferay.taglib.aui.InputTag _jspx_th_aui_input_25 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_type_name_label_inlineField_cssClass_nobody.get(com.liferay.taglib.aui.InputTag.class);
                            _jspx_th_aui_input_25.setPageContext(_jspx_page_context);
                            _jspx_th_aui_input_25.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_2);
                            _jspx_th_aui_input_25.setCssClass("lfr-ddm-small-image-value");
                            _jspx_th_aui_input_25.setInlineField( true );
                            _jspx_th_aui_input_25.setLabel("");
                            _jspx_th_aui_input_25.setName("smallImageFile");
                            _jspx_th_aui_input_25.setType("file");
                            int _jspx_eval_aui_input_25 = _jspx_th_aui_input_25.doStartTag();
                            if (_jspx_th_aui_input_25.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_aui_input_type_name_label_inlineField_cssClass_nobody.reuse(_jspx_th_aui_input_25);
                              return;
                            }
                            _jspx_tagPool_aui_input_type_name_label_inlineField_cssClass_nobody.reuse(_jspx_th_aui_input_25);
                            out.write("\n\t\t\t\t\t\t\t\t\t\t\t\t\t");
                          }
                          if (_jspx_th_aui_fieldset_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                            _jspx_tagPool_aui_fieldset.reuse(_jspx_th_aui_fieldset_2);
                            return;
                          }
                          _jspx_tagPool_aui_fieldset.reuse(_jspx_th_aui_fieldset_2);
                          out.write("\n\t\t\t\t\t\t\t\t\t\t\t\t");
                        }
                        if (_jspx_th_aui_col_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                          _jspx_tagPool_aui_col_width.reuse(_jspx_th_aui_col_1);
                          return;
                        }
                        _jspx_tagPool_aui_col_width.reuse(_jspx_th_aui_col_1);
                        out.write("\n\t\t\t\t\t\t\t\t\t\t\t");
                      }
                      if (_jspx_th_aui_row_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_aui_row.reuse(_jspx_th_aui_row_0);
                        return;
                      }
                      _jspx_tagPool_aui_row.reuse(_jspx_th_aui_row_0);
                      out.write("\n\t\t\t\t\t\t\t\t\t\t</div>\n\t\t\t\t\t\t\t\t\t</div>\n\t\t\t\t\t\t\t\t");
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
                if (_jspx_th_liferay$1ui_panel_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_liferay$1ui_panel_title_persistState_markupView_id_extended_defaultState_collapsible.reuse(_jspx_th_liferay$1ui_panel_0);
                  return;
                }
                _jspx_tagPool_liferay$1ui_panel_title_persistState_markupView_id_extended_defaultState_collapsible.reuse(_jspx_th_liferay$1ui_panel_0);
                out.write("\n\t\t\t\t\t");
                int evalDoAfterBody = _jspx_th_liferay$1ui_panel$1container_0.doAfterBody();
                if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                  break;
              } while (true);
              if (_jspx_eval_liferay$1ui_panel$1container_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                out = _jspx_page_context.popBody();
            }
            if (_jspx_th_liferay$1ui_panel$1container_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_liferay$1ui_panel$1container_persistState_id_extended_cssClass.reuse(_jspx_th_liferay$1ui_panel$1container_0);
              return;
            }
            _jspx_tagPool_liferay$1ui_panel$1container_persistState_id_extended_cssClass.reuse(_jspx_th_liferay$1ui_panel$1container_0);
            out.write("\n\n\t\t\t\t\t");
            //  c:choose
            com.liferay.taglib.core.ChooseTag _jspx_th_c_choose_2 = (com.liferay.taglib.core.ChooseTag) _jspx_tagPool_c_choose.get(com.liferay.taglib.core.ChooseTag.class);
            _jspx_th_c_choose_2.setPageContext(_jspx_page_context);
            _jspx_th_c_choose_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_0);
            int _jspx_eval_c_choose_2 = _jspx_th_c_choose_2.doStartTag();
            if (_jspx_eval_c_choose_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\t\t\t\t\t\t");
              //  c:when
              com.liferay.taglib.core.WhenTag _jspx_th_c_when_2 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
              _jspx_th_c_when_2.setPageContext(_jspx_page_context);
              _jspx_th_c_when_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_2);
              _jspx_th_c_when_2.setTest( type.equals(DDMTemplateConstants.TEMPLATE_TYPE_FORM) );
              int _jspx_eval_c_when_2 = _jspx_th_c_when_2.doStartTag();
              if (_jspx_eval_c_when_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\t\t\t\t\t\t\t");
                out.write('\n');
                out.write('\n');

String namespace = renderResponse.getNamespace();

if (Validator.isNotNull(portletResourceNamespace)) {
	namespace = portletResourceNamespace;
}

if (Validator.isNull(script)) {
	script = structure.getDefinition();
}

JSONArray fieldsJSONArray = _getFormTemplateFieldsJSONArray(structure, script);

String fieldsJSONArrayString = fieldsJSONArray.toString();

                out.write('\n');
                out.write('\n');
                if (_jspx_meth_aui_input_26((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_when_2, _jspx_page_context))
                  return;
                out.write('\n');
                if (_jspx_meth_aui_input_27((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_when_2, _jspx_page_context))
                  return;
                out.write('\n');
                out.write('\n');
                //  aui:script
                com.liferay.taglib.aui.ScriptTag _jspx_th_aui_script_1 = (com.liferay.taglib.aui.ScriptTag) _jspx_tagPool_aui_script.get(com.liferay.taglib.aui.ScriptTag.class);
                _jspx_th_aui_script_1.setPageContext(_jspx_page_context);
                _jspx_th_aui_script_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_2);
                int _jspx_eval_aui_script_1 = _jspx_th_aui_script_1.doStartTag();
                if (_jspx_eval_aui_script_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  if (_jspx_eval_aui_script_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                    out = _jspx_page_context.pushBody();
                    _jspx_th_aui_script_1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                    _jspx_th_aui_script_1.doInitBody();
                  }
                  do {
                    out.write("\n\tfunction ");
                    if (_jspx_meth_portlet_namespace_3((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
                      return;
                    out.write("attachValueChange(formBuilder) {\n\t\tAUI.$('#");
                    if (_jspx_meth_portlet_namespace_4((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
                      return;
                    out.write("mode').on(\n\t\t\t'change',\n\t\t\tfunction(event) {\n\t\t\t\t");
                    if (_jspx_meth_portlet_namespace_5((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
                      return;
                    out.write("toggleMode(formBuilder, AUI.$(event.currentTarget).val());\n\t\t\t}\n\t\t);\n\t}\n\n\tLiferay.on(\n\t\t'");
                    if (_jspx_meth_portlet_namespace_6((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
                      return;
                    out.write("formBuilderLoaded',\n\t\tfunction(event) {\n\t\t\tvar formBuilder = event.formBuilder;\n\n\t\t\t");
                    if (_jspx_meth_portlet_namespace_7((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
                      return;
                    out.write("attachValueChange(formBuilder);\n\n\t\t\t");
                    if (_jspx_meth_portlet_namespace_8((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
                      return;
                    out.write("toggleMode(formBuilder, '");
                    out.print( HtmlUtil.escape(mode) );
                    out.write("');\n\t\t}\n\t);\n\n\tfunction ");
                    if (_jspx_meth_portlet_namespace_9((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
                      return;
                    out.write("setFieldsHiddenAttributes(formBuilder, mode, item, index) {\n\t\tvar hiddenAttributesMap = formBuilder.MAP_HIDDEN_FIELD_ATTRS;\n\t\tvar hiddenAttributes = hiddenAttributesMap[item.get('type')] || hiddenAttributesMap.DEFAULT;\n\t\tvar without = function(array) {\n\t\t\tvar elems = Array.from(arguments).slice(1);\n\n\t\t\t\telems.forEach(\n\t\t\t\t\tfunction(value) {\n\t\t\t\t\t\tarray = array.filter(\n\t\t\t\t\t\t\tfunction(elem) {\n\t\t\t\t\t\t\t\treturn elem != value;\n\t\t\t\t\t\t\t}\n\t\t\t\t\t\t);\n\t\t\t\t\t}\n\t\t\t\t);\n\n\t\t\treturn array;\n\t\t}\n\n\t\tif (mode === '");
                    out.print( DDMTemplateConstants.TEMPLATE_MODE_EDIT );
                    out.write("') {\n\t\t\thiddenAttributes = without(hiddenAttributes, 'readOnly');\n\t\t}\n\n\t\titem.set('hiddenAttributes', hiddenAttributes);\n\t}\n\n\tfunction ");
                    if (_jspx_meth_portlet_namespace_10((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
                      return;
                    out.write("toggleMode(formBuilder, mode) {\n\t\tvar modeEdit = (mode === '");
                    out.print( DDMTemplateConstants.TEMPLATE_MODE_EDIT );
                    out.write("');\n\n\t\tformBuilder.set('allowRemoveRequiredFields', modeEdit);\n\n\t\tvar setFieldsHiddenAttributes = ");
                    if (_jspx_meth_portlet_namespace_11((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
                      return;
                    out.write("setFieldsHiddenAttributes.bind(this, formBuilder, mode)\n\t\t_.forEach(formBuilder.get('fields')._items, setFieldsHiddenAttributes);\n\t\t_.forEach(formBuilder.get('availableFields'), setFieldsHiddenAttributes);\n\n\t\tvar editingField = formBuilder.editingField;\n\n\t\tif (editingField) {\n\t\t\tformBuilder.propertyList.set('data', formBuilder.getFieldProperties(editingField, true));\n\t\t}\n\t}\n\n\twindow['");
                    out.print( HtmlUtil.escapeJS(namespace) );
                    out.write("getAvailableFields'] = function(A, FormBuilder) {\n\n\t\t");

		JSONArray structureJSONArray = _getFormTemplateFieldsJSONArray(structure, structure.getDefinition());
		
                    out.write("\n\n\t\tvar availableFields = ");
                    out.print( structureJSONArray.toString() );
                    out.write(";\n\n\t\tAUI._.forEach(\n\t\t\tavailableFields,\n\t\t\tfunction(item, index) {\n\t\t\t\titem.iconClass = FormBuilder.DEFAULT_ICON_CLASS;\n\t\t\t}\n\t\t);\n\n\t\treturn availableFields.concat(FormBuilder.AVAILABLE_FIELDS.DDM_TEMPLATE);\n\t};\n");
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
                out.write('\n');
                out.write('\n');
                out.write('\n');
                out.write('\n');

String defaultLanguageId = LocaleUtil.toLanguageId(LocaleUtil.getSiteDefault());

Locale[] availableLocales = {LocaleUtil.getSiteDefault()};

if (Validator.isNotNull(script)) {
	try {
		DDMForm ddmForm = DDMUtil.getDDMForm(script);

		String ddmFormDefaultLanguageId = LocaleUtil.toLanguageId(ddmForm.getDefaultLocale());

		if (!Objects.equals(defaultLanguageId, ddmFormDefaultLanguageId)) {
			changeableDefaultLanguage = true;
		}

		defaultLanguageId = ddmFormDefaultLanguageId;

		Set<Locale> ddmFormAvailableLocales = ddmForm.getAvailableLocales();

		availableLocales = ddmFormAvailableLocales.toArray(new Locale[ddmFormAvailableLocales.size()]);
	}
	catch (Exception e) {
	}
}

                out.write("\n\n<div class=\"separator\"><!-- --></div>\n\n<div class=\"alert alert-danger hide lfr-message-response\" id=\"");
                if (_jspx_meth_portlet_namespace_12((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_when_2, _jspx_page_context))
                  return;
                out.write("messageContainer\"></div>\n\n");
                //  liferay-ui:tabs
                com.liferay.taglib.ui.TabsTag _jspx_th_liferay$1ui_tabs_0 = (com.liferay.taglib.ui.TabsTag) _jspx_tagPool_liferay$1ui_tabs_type_refresh_names.get(com.liferay.taglib.ui.TabsTag.class);
                _jspx_th_liferay$1ui_tabs_0.setPageContext(_jspx_page_context);
                _jspx_th_liferay$1ui_tabs_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_2);
                _jspx_th_liferay$1ui_tabs_0.setNames( LanguageUtil.get(request, "view[action]") + "," + LanguageUtil.get(request, "source") );
                _jspx_th_liferay$1ui_tabs_0.setRefresh( false );
                _jspx_th_liferay$1ui_tabs_0.setType("tabs nav-tabs-default");
                int _jspx_eval_liferay$1ui_tabs_0 = _jspx_th_liferay$1ui_tabs_0.doStartTag();
                if (_jspx_eval_liferay$1ui_tabs_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  out.write('\n');
                  out.write('	');
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
                    out.write("\n\t\t<div id=\"");
                    if (_jspx_meth_portlet_namespace_13((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1ui_section_0, _jspx_page_context))
                      return;
                    out.write("formBuilderTab\">\n\t\t\t");
                    //  aui:translation-manager
                    com.liferay.taglib.aui.TranslationManagerTag _jspx_th_aui_translation$1manager_0 = (com.liferay.taglib.aui.TranslationManagerTag) _jspx_tagPool_aui_translation$1manager_readOnly_initialize_id_defaultLanguageId_changeableDefaultLanguage_availableLocales_nobody.get(com.liferay.taglib.aui.TranslationManagerTag.class);
                    _jspx_th_aui_translation$1manager_0.setPageContext(_jspx_page_context);
                    _jspx_th_aui_translation$1manager_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_section_0);
                    _jspx_th_aui_translation$1manager_0.setAvailableLocales( availableLocales );
                    _jspx_th_aui_translation$1manager_0.setChangeableDefaultLanguage( changeableDefaultLanguage );
                    _jspx_th_aui_translation$1manager_0.setDefaultLanguageId( defaultLanguageId );
                    _jspx_th_aui_translation$1manager_0.setId("translationManager");
                    _jspx_th_aui_translation$1manager_0.setInitialize( false );
                    _jspx_th_aui_translation$1manager_0.setReadOnly( false );
                    int _jspx_eval_aui_translation$1manager_0 = _jspx_th_aui_translation$1manager_0.doStartTag();
                    if (_jspx_th_aui_translation$1manager_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_aui_translation$1manager_readOnly_initialize_id_defaultLanguageId_changeableDefaultLanguage_availableLocales_nobody.reuse(_jspx_th_aui_translation$1manager_0);
                      return;
                    }
                    _jspx_tagPool_aui_translation$1manager_readOnly_initialize_id_defaultLanguageId_changeableDefaultLanguage_availableLocales_nobody.reuse(_jspx_th_aui_translation$1manager_0);
                    out.write("\n\n\t\t\t<div class=\"diagram-builder form-builder\" id=\"");
                    if (_jspx_meth_portlet_namespace_14((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1ui_section_0, _jspx_page_context))
                      return;
                    out.write("formBuilder\">\n\t\t\t\t<div class=\"diagram-builder-content\" id=\"");
                    if (_jspx_meth_portlet_namespace_15((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1ui_section_0, _jspx_page_context))
                      return;
                    out.write("formBuilderContent\">\n\t\t\t\t\t<div class=\"tabbable\">\n\t\t\t\t\t\t<div class=\"tabbable-content\">\n\t\t\t\t\t\t\t<ul class=\"lfr-nav nav nav-tabs nav-tabs-default\">\n\t\t\t\t\t\t\t\t<li class=\"active\">\n\t\t\t\t\t\t\t\t\t<a href=\"javascript:;\">\n\t\t\t\t\t\t\t\t\t\t");
                    if (_jspx_meth_liferay$1ui_message_4((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1ui_section_0, _jspx_page_context))
                      return;
                    out.write("\n\t\t\t\t\t\t\t\t\t</a>\n\t\t\t\t\t\t\t\t</li>\n\t\t\t\t\t\t\t\t<li class=\"disabled\">\n\t\t\t\t\t\t\t\t\t<a href=\"javascript:;\">\n\t\t\t\t\t\t\t\t\t\t");
                    if (_jspx_meth_liferay$1ui_message_5((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1ui_section_0, _jspx_page_context))
                      return;
                    out.write("\n\t\t\t\t\t\t\t\t\t</a>\n\t\t\t\t\t\t\t\t</li>\n\t\t\t\t\t\t\t</ul>\n\n\t\t\t\t\t\t\t<div class=\"tab-content\">\n\t\t\t\t\t\t\t\t<div class=\"tab-pane\"></div>\n\t\t\t\t\t\t\t\t<div class=\"tab-pane\"></div>\n\t\t\t\t\t\t\t</div>\n\t\t\t\t\t\t</div>\n\t\t\t\t\t</div>\n\n\t\t\t\t\t<div class=\"diagram-builder-content-container\">\n\t\t\t\t\t\t<div class=\"diagram-builder-canvas\">\n\t\t\t\t\t\t\t<div class=\"diagram-builder-drop-container\"></div>\n\t\t\t\t\t\t</div>\n\t\t\t\t\t</div>\n\t\t\t\t</div>\n\t\t\t</div>\n\t\t</div>\n\t");
                  }
                  if (_jspx_th_liferay$1ui_section_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_liferay$1ui_section.reuse(_jspx_th_liferay$1ui_section_0);
                    return;
                  }
                  _jspx_tagPool_liferay$1ui_section.reuse(_jspx_th_liferay$1ui_section_0);
                  out.write("\n\n\t");
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
                    out.write("\n\t\t<div class=\"form-builder-source-wrapper\" id=\"");
                    if (_jspx_meth_portlet_namespace_16((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1ui_section_1, _jspx_page_context))
                      return;
                    out.write("formBuilderSourceWrapper\">\n\t\t\t<div class=\"form-builder-source\" id=\"");
                    if (_jspx_meth_portlet_namespace_17((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1ui_section_1, _jspx_page_context))
                      return;
                    out.write("formBuilderEditor\"></div>\n\t\t</div>\n\t");
                  }
                  if (_jspx_th_liferay$1ui_section_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_liferay$1ui_section.reuse(_jspx_th_liferay$1ui_section_1);
                    return;
                  }
                  _jspx_tagPool_liferay$1ui_section.reuse(_jspx_th_liferay$1ui_section_1);
                  out.write('\n');
                }
                if (_jspx_th_liferay$1ui_tabs_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_liferay$1ui_tabs_type_refresh_names.reuse(_jspx_th_liferay$1ui_tabs_0);
                  return;
                }
                _jspx_tagPool_liferay$1ui_tabs_type_refresh_names.reuse(_jspx_th_liferay$1ui_tabs_0);
                out.write('\n');
                out.write('\n');

JSONArray availableLocalesJSONArray = JSONFactoryUtil.createJSONArray();

for (int i = 0; i < availableLocales.length; i++) {
	availableLocalesJSONArray.put(LanguageUtil.getLanguageId(availableLocales[i]));
}

JSONObject localesMapJSONObject = JSONFactoryUtil.createJSONObject();

for (Locale availableLocale : LanguageUtil.getAvailableLocales(themeDisplay.getSiteGroupId())) {
	localesMapJSONObject.put(LocaleUtil.toLanguageId(availableLocale), availableLocale.getDisplayName(locale));
}

                out.write('\n');
                out.write('\n');
                //  aui:script
                com.liferay.taglib.aui.ScriptTag _jspx_th_aui_script_2 = (com.liferay.taglib.aui.ScriptTag) _jspx_tagPool_aui_script_use.get(com.liferay.taglib.aui.ScriptTag.class);
                _jspx_th_aui_script_2.setPageContext(_jspx_page_context);
                _jspx_th_aui_script_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_2);
                _jspx_th_aui_script_2.setUse("aui-datepicker-deprecated,event-custom-base,json,liferay-portlet-dynamic-data-lists,liferay-portlet-dynamic-data-mapping,liferay-portlet-dynamic-data-mapping-custom-fields,aui-tabview,aui-ace-editor,liferay-xml-formatter");
                int _jspx_eval_aui_script_2 = _jspx_th_aui_script_2.doStartTag();
                if (_jspx_eval_aui_script_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  if (_jspx_eval_aui_script_2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                    out = _jspx_page_context.pushBody();
                    _jspx_th_aui_script_2.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                    _jspx_th_aui_script_2.doInitBody();
                  }
                  do {
                    out.write("\n\tvar Lang = A.Lang;\n\n\tvar STR_VALUE = 'value';\n\n\tvar availableFields;\n\n\tvar displayWarning = function(message) {\n\t\tnew Liferay.Notification(\n\t\t\t{\n\t\t\t\tcloseable: true,\n\t\t\t\tdelay: {\n\t\t\t\t\thide: 5000,\n\t\t\t\t\tshow: 0\n\t\t\t\t},\n\t\t\t\tduration: 500,\n\t\t\t\tmessage: message,\n\t\t\t\ttitle: Liferay.Language.get('warning'),\n\t\t\t\ttype: 'warning'\n\t\t\t}\n\t\t).render('body');\n\t};\n\n\tvar formEditor;\n\n\tvar getContentValue = function() {\n\t\tvar content;\n\n\t\tif (formEditor && !isViewTabActive()) {\n\t\t\tcontent = formEditor.get(STR_VALUE);\n\t\t}\n\t\telse {\n\t\t\tcontent = formBuilder.getContent();\n\t\t}\n\n\t\treturn content;\n\t};\n\n\tvar getFormEditor = function() {\n\t\tif (!formEditor) {\n\t\t\tformEditor = new A.AceEditor(\n\t\t\t\t{\n\t\t\t\t\tboundingBox: '#");
                    if (_jspx_meth_portlet_namespace_18((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_2, _jspx_page_context))
                      return;
                    out.write("formBuilderEditor',\n\t\t\t\t\theight: 600,\n\t\t\t\t\tmode: 'xml',\n\t\t\t\t\ttabSize: 4,\n\t\t\t\t\twidth: 600\n\t\t\t\t}\n\t\t\t).render();\n\t\t}\n\n\t\treturn formEditor;\n\t};\n\n\tvar isViewTabActive = function() {\n\t\tvar formBuilderTab = A.one('#");
                    if (_jspx_meth_portlet_namespace_19((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_2, _jspx_page_context))
                      return;
                    out.write("formBuilderTab');\n\n\t\tif (!formBuilderTab) {\n\t\t\treturn false;\n\t\t}\n\n\t\tvar ancestor = formBuilderTab.ancestor();\n\n\t\treturn !ancestor.hasClass('hide');\n\t};\n\n\tvar reloadFormBuilderData = function(content) {\n\t\tif (!Lang.isValue(content)) {\n\t\t\tcontent = window.");
                    if (_jspx_meth_portlet_namespace_20((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_2, _jspx_page_context))
                      return;
                    out.write("getContentDefinition();\n\t\t}\n\n\t\tcontent = content.replace(/nestedFields/g, 'fields');\n\n\t\tif (content.indexOf('availableLanguageIds') === -1 || content.indexOf('defaultLanguageId') === -1 || content.indexOf('fields') === -1) {\n\t\t\tdisplayWarning('");
                    out.print( UnicodeLanguageUtil.get(resourceBundle, "you-cannot-remove-default-attributes") );
                    out.write("');\n\t\t}\n\t\telse {\n\t\t\ttry {\n\t\t\t\tcontent = JSON.parse(content);\n\t\t\t}\n\t\t\tcatch (e) {\n\t\t\t\tdisplayWarning('");
                    out.print( UnicodeLanguageUtil.get(resourceBundle, "you-have-entered-invalid-json") );
                    out.write("');\n\n\t\t\t\treturn;\n\t\t\t}\n\n\t\t\tformBuilder.translationManager.set('availableLocales', content.availableLanguageIds);\n\n\t\t\tcontent = formBuilder.deserializeDefinitionFields(content);\n\n\t\t\tformBuilder.set('fields', content);\n\t\t}\n\t};\n\n\tvar setEditorSize = function() {\n\t\tif (!isViewTabActive()) {\n\t\t\tgetFormEditor().set('width', A.one('#");
                    if (_jspx_meth_portlet_namespace_21((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_2, _jspx_page_context))
                      return;
                    out.write("formBuilderSourceWrapper').get('clientWidth'));\n\t\t}\n\t};\n\n\tvar switchToSource = function() {\n\t\tsetEditorSize();\n\n\t\tvar content = formBuilder.getContent();\n\n\t\tgetFormEditor().set(STR_VALUE, content);\n\t};\n\n\tvar switchToView = function() {\n\t\tif (formEditor) {\n\t\t\treloadFormBuilderData(formEditor.get(STR_VALUE));\n\t\t}\n\t\telse if (formBuilder) {\n\t\t\treloadFormBuilderData(formBuilder.getContent());\n\t\t}\n\t};\n\n\t");
                    //  c:if
                    com.liferay.taglib.core.IfTag _jspx_th_c_if_11 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                    _jspx_th_c_if_11.setPageContext(_jspx_page_context);
                    _jspx_th_c_if_11.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_2);
                    _jspx_th_c_if_11.setTest( Validator.isNotNull(scopeAvailableFields) );
                    int _jspx_eval_c_if_11 = _jspx_th_c_if_11.doStartTag();
                    if (_jspx_eval_c_if_11 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      out.write("\n\t\tavailableFields = A.Object.getValue(window, '");
                      out.print( HtmlUtil.escapeJS(scopeAvailableFields) );
                      out.write("'.split('.'));\n\n\t\tif (A.Lang.isFunction(availableFields)) {\n\t\t\tavailableFields = availableFields(A, Liferay.FormBuilder);\n\t\t}\n\t");
                    }
                    if (_jspx_th_c_if_11.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_11);
                      return;
                    }
                    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_11);
                    out.write("\n\n\tvar formBuilder = new Liferay.FormBuilder(\n\t\t{\n\t\t\tallowRemoveRequiredFields: true,\n\t\t\tavailableFields: availableFields,\n\t\t\tboundingBox: '#");
                    if (_jspx_meth_portlet_namespace_22((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_2, _jspx_page_context))
                      return;
                    out.write("formBuilder',\n\t\t\tenableEditing: false,\n\t\t\tfieldNameEditionDisabled: ");
                    out.print( (structure != null) && (DDMStorageLinkLocalServiceUtil.getStructureStorageLinksCount(structure.getStructureId()) > 0) );
                    out.write(",\n\n\t\t\t");
                    //  c:if
                    com.liferay.taglib.core.IfTag _jspx_th_c_if_12 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                    _jspx_th_c_if_12.setPageContext(_jspx_page_context);
                    _jspx_th_c_if_12.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_2);
                    _jspx_th_c_if_12.setTest( Validator.isNotNull(fieldsJSONArrayString) );
                    int _jspx_eval_c_if_12 = _jspx_th_c_if_12.doStartTag();
                    if (_jspx_eval_c_if_12 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      out.write("\n\t\t\t\tfields: ");
                      out.print( fieldsJSONArrayString );
                      out.write(",\n\t\t\t");
                    }
                    if (_jspx_th_c_if_12.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_12);
                      return;
                    }
                    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_12);
                    out.write("\n\n\t\t\tportletNamespace: '");
                    if (_jspx_meth_portlet_namespace_23((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_2, _jspx_page_context))
                      return;
                    out.write("',\n\t\t\tportletResourceNamespace: '");
                    out.print( HtmlUtil.escapeJS(portletResourceNamespace) );
                    out.write("',\n\t\t\treadOnly: ");
                    out.print( ParamUtil.getBoolean(request, "formBuilderReadOnly") );
                    out.write(",\n\t\t\tsrcNode: '#");
                    if (_jspx_meth_portlet_namespace_24((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_2, _jspx_page_context))
                      return;
                    out.write("formBuilderContent',\n\t\t\ttranslationManager: {\n\n\t\t\t\t");
                    //  c:if
                    com.liferay.taglib.core.IfTag _jspx_th_c_if_13 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                    _jspx_th_c_if_13.setPageContext(_jspx_page_context);
                    _jspx_th_c_if_13.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_2);
                    _jspx_th_c_if_13.setTest( availableLocalesJSONArray.length() > 0 );
                    int _jspx_eval_c_if_13 = _jspx_th_c_if_13.doStartTag();
                    if (_jspx_eval_c_if_13 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      out.write("\n\t\t\t\t\tavailableLocales: ");
                      out.print( availableLocalesJSONArray.toString() );
                      out.write(",\n\t\t\t\t");
                    }
                    if (_jspx_th_c_if_13.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_13);
                      return;
                    }
                    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_13);
                    out.write("\n\n\t\t\t\tboundingBox: '#");
                    if (_jspx_meth_portlet_namespace_25((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_2, _jspx_page_context))
                      return;
                    out.write("translationManager',\n\t\t\t\tchangeableDefaultLanguage: ");
                    out.print( changeableDefaultLanguage );
                    out.write(",\n\t\t\t\tdefaultLocale: '");
                    out.print( HtmlUtil.escapeJS(defaultLanguageId) );
                    out.write("',\n\t\t\t\tlocalesMap: ");
                    out.print( localesMapJSONObject.toString() );
                    out.write(",\n\t\t\t\tsrcNode: '#");
                    if (_jspx_meth_portlet_namespace_26((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_2, _jspx_page_context))
                      return;
                    out.write("translationManager .lfr-translation-manager-content'\n\t\t\t}\n\t\t}\n\t).render();\n\n\tvar dialog = Liferay.Util.getWindow();\n\n\tif (dialog) {\n\t\tdialog.after('widthChange', setEditorSize);\n\t}\n\n\tvar afterShowTab = function(event) {\n\t\tif (isViewTabActive()) {\n\t\t\tswitchToView();\n\t\t}\n\t\telse {\n\t\t\tswitchToSource();\n\t\t}\n\t};\n\n\tLiferay.after('showTab', afterShowTab);\n\n\tvar onDestroyPortlet = function(event) {\n\t\tif (event.portletId === '");
                    out.print( portletDisplay.getRootPortletId() );
                    out.write("') {\n\t\t\tLiferay.detach('showTab', afterShowTab);\n\t\t\tLiferay.detach('destroyPortlet', onDestroyPortlet);\n\n\t\t\tvar propertyList = formBuilder.propertyList;\n\n\t\t\tif (propertyList) {\n\t\t\t\tpropertyList.get('data').each(\n\t\t\t\t\tfunction(model) {\n\t\t\t\t\t\tvar editor = model.get('editor');\n\n\t\t\t\t\t\tif (editor) {\n\t\t\t\t\t\t\teditor.destroy();\n\t\t\t\t\t\t}\n\t\t\t\t\t}\n\t\t\t\t);\n\t\t\t}\n\n\t\t\tformBuilder.destroy();\n\t\t}\n\t};\n\n\tLiferay.on('destroyPortlet', onDestroyPortlet);\n\n\twindow['");
                    out.print( HtmlUtil.escapeJS(portletResourceNamespace) );
                    out.write("formBuilder'] = formBuilder;\n\n\twindow['");
                    out.print( HtmlUtil.escapeJS(portletResourceNamespace) );
                    out.write("getContentValue'] = getContentValue;\n\n\tLiferay.on(\n\t\t'");
                    if (_jspx_meth_portlet_namespace_27((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_2, _jspx_page_context))
                      return;
                    out.write("saveTemplate',\n\t\tfunction(event) {\n\t\t\tA.one('#");
                    if (_jspx_meth_portlet_namespace_28((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_2, _jspx_page_context))
                      return;
                    out.write("scriptContent').val(getContentValue());\n\t\t}\n\t);\n\n\tLiferay.fire(\n\t\t'");
                    if (_jspx_meth_portlet_namespace_29((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_2, _jspx_page_context))
                      return;
                    out.write("formBuilderLoaded',\n\t\t{\n\t\t\tformBuilder: formBuilder\n\t\t}\n\t);\n");
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
                out.write("\n\t\t\t\t\t\t");
              }
              if (_jspx_th_c_when_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_2);
                return;
              }
              _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_2);
              out.write("\n\t\t\t\t\t\t");
              //  c:otherwise
              com.liferay.taglib.core.OtherwiseTag _jspx_th_c_otherwise_2 = (com.liferay.taglib.core.OtherwiseTag) _jspx_tagPool_c_otherwise.get(com.liferay.taglib.core.OtherwiseTag.class);
              _jspx_th_c_otherwise_2.setPageContext(_jspx_page_context);
              _jspx_th_c_otherwise_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_2);
              int _jspx_eval_c_otherwise_2 = _jspx_th_c_otherwise_2.doStartTag();
              if (_jspx_eval_c_otherwise_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\t\t\t\t\t\t\t");
                out.write('\n');
                out.write('\n');

String scriptContent = ParamUtil.getString(request, "scriptContent");

if (Validator.isNotNull(scriptContent)) {
	script = scriptContent;
}

                out.write('\n');
                out.write('\n');
                //  aui:input
                com.liferay.taglib.aui.InputTag _jspx_th_aui_input_28 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
                _jspx_th_aui_input_28.setPageContext(_jspx_page_context);
                _jspx_th_aui_input_28.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_2);
                _jspx_th_aui_input_28.setName("scriptContent");
                _jspx_th_aui_input_28.setType("hidden");
                _jspx_th_aui_input_28.setValue( script );
                int _jspx_eval_aui_input_28 = _jspx_th_aui_input_28.doStartTag();
                if (_jspx_th_aui_input_28.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_28);
                  return;
                }
                _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_28);
                out.write('\n');
                out.write('\n');
                //  liferay-ui:panel-container
                com.liferay.taglib.ui.PanelContainerTag _jspx_th_liferay$1ui_panel$1container_1 = (com.liferay.taglib.ui.PanelContainerTag) _jspx_tagPool_liferay$1ui_panel$1container_persistState_markupView_id_extended.get(com.liferay.taglib.ui.PanelContainerTag.class);
                _jspx_th_liferay$1ui_panel$1container_1.setPageContext(_jspx_page_context);
                _jspx_th_liferay$1ui_panel$1container_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_2);
                _jspx_th_liferay$1ui_panel$1container_1.setExtended( true );
                _jspx_th_liferay$1ui_panel$1container_1.setId("templateScriptContainer");
                _jspx_th_liferay$1ui_panel$1container_1.setMarkupView("lexicon");
                _jspx_th_liferay$1ui_panel$1container_1.setPersistState( true );
                int _jspx_eval_liferay$1ui_panel$1container_1 = _jspx_th_liferay$1ui_panel$1container_1.doStartTag();
                if (_jspx_eval_liferay$1ui_panel$1container_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  if (_jspx_eval_liferay$1ui_panel$1container_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                    out = _jspx_page_context.pushBody();
                    _jspx_th_liferay$1ui_panel$1container_1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                    _jspx_th_liferay$1ui_panel$1container_1.doInitBody();
                  }
                  do {
                    out.write('\n');
                    out.write('	');
                    //  liferay-ui:panel
                    com.liferay.taglib.ui.PanelTag _jspx_th_liferay$1ui_panel_1 = (com.liferay.taglib.ui.PanelTag) _jspx_tagPool_liferay$1ui_panel_title_persistState_markupView_id_extended_collapsible.get(com.liferay.taglib.ui.PanelTag.class);
                    _jspx_th_liferay$1ui_panel_1.setPageContext(_jspx_page_context);
                    _jspx_th_liferay$1ui_panel_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_panel$1container_1);
                    _jspx_th_liferay$1ui_panel_1.setCollapsible( true );
                    _jspx_th_liferay$1ui_panel_1.setExtended( true );
                    _jspx_th_liferay$1ui_panel_1.setId("templateScriptSectionPanel");
                    _jspx_th_liferay$1ui_panel_1.setMarkupView("lexicon");
                    _jspx_th_liferay$1ui_panel_1.setPersistState( true );
                    _jspx_th_liferay$1ui_panel_1.setTitle("script");
                    int _jspx_eval_liferay$1ui_panel_1 = _jspx_th_liferay$1ui_panel_1.doStartTag();
                    if (_jspx_eval_liferay$1ui_panel_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      out.write("\n\t\t<div class=\"form-group lfr-template-editor-container\">\n\t\t\t");
                      //  c:if
                      com.liferay.taglib.core.IfTag _jspx_th_c_if_14 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                      _jspx_th_c_if_14.setPageContext(_jspx_page_context);
                      _jspx_th_c_if_14.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_panel_1);
                      _jspx_th_c_if_14.setTest( ddmDisplayContext.isAutocompleteEnabled(language) );
                      int _jspx_eval_c_if_14 = _jspx_th_c_if_14.doStartTag();
                      if (_jspx_eval_c_if_14 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                        out.write("\n\t\t\t\t<div class=\"lfr-template-palette-container pull-left\" id=\"");
                        if (_jspx_meth_portlet_namespace_30((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_14, _jspx_page_context))
                          return;
                        out.write("templatePaletteContainer\">\n\t\t\t\t\t<div class=\"search\" id=\"");
                        if (_jspx_meth_portlet_namespace_31((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_14, _jspx_page_context))
                          return;
                        out.write("paletteSearchContainer\">\n\t\t\t\t\t\t<input class=\"col-md-12 field form-control search-query\" id=\"");
                        if (_jspx_meth_portlet_namespace_32((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_14, _jspx_page_context))
                          return;
                        out.write("paletteSearch\" placeholder=\"");
                        if (_jspx_meth_liferay$1ui_message_6((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_14, _jspx_page_context))
                          return;
                        out.write("\" type=\"text\" />\n\t\t\t\t\t</div>\n\n\t\t\t\t\t<div class=\"lfr-template-palette\" id=\"");
                        if (_jspx_meth_portlet_namespace_33((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_14, _jspx_page_context))
                          return;
                        out.write("paletteDataContainer\">\n\t\t\t\t\t\t<div id=\"");
                        if (_jspx_meth_portlet_namespace_34((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_14, _jspx_page_context))
                          return;
                        out.write("paletteData\">\n\n\t\t\t\t\t\t\t");

							long templateHandlerClassNameId = ddmDisplay.getTemplateHandlerClassNameId(template, classNameId);

							Map<String, TemplateVariableGroup> templateVariableGroups = TemplateContextHelper.getTemplateVariableGroups(templateHandlerClassNameId, classPK, language, locale);

							TemplateHandler templateHandler = TemplateHandlerRegistryUtil.getTemplateHandler(templateHandlerClassNameId);

							Class<?> clazz = ddmDisplay.getClass();

							if (templateHandler != null) {
								clazz = templateHandler.getClass();
							}

							Bundle bundle = FrameworkUtil.getBundle(clazz);

							ResourceBundleLoader resourceBundleLoader = ResourceBundleLoaderUtil.getResourceBundleLoaderByBundleSymbolicName(bundle.getSymbolicName());

							ResourceBundle templateHandlerResourceBundle = resourceBundleLoader.loadResourceBundle(locale);

							for (TemplateVariableGroup templateVariableGroup : templateVariableGroups.values()) {
								if (templateVariableGroup.isEmpty()) {
									continue;
								}
							
                        out.write("\n\n\t\t\t\t\t\t\t\t");
                        //  liferay-ui:panel
                        com.liferay.taglib.ui.PanelTag _jspx_th_liferay$1ui_panel_2 = (com.liferay.taglib.ui.PanelTag) _jspx_tagPool_liferay$1ui_panel_title_id_extended_cssClass_collapsible.get(com.liferay.taglib.ui.PanelTag.class);
                        _jspx_th_liferay$1ui_panel_2.setPageContext(_jspx_page_context);
                        _jspx_th_liferay$1ui_panel_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_14);
                        _jspx_th_liferay$1ui_panel_2.setCollapsible( true );
                        _jspx_th_liferay$1ui_panel_2.setCssClass("palette-section");
                        _jspx_th_liferay$1ui_panel_2.setExtended( false );
                        _jspx_th_liferay$1ui_panel_2.setId( HtmlUtil.getAUICompatibleId(templateVariableGroup.getLabel()) );
                        _jspx_th_liferay$1ui_panel_2.setTitle( LanguageUtil.get(request, templateHandlerResourceBundle, HtmlUtil.escape(templateVariableGroup.getLabel())) );
                        int _jspx_eval_liferay$1ui_panel_2 = _jspx_th_liferay$1ui_panel_2.doStartTag();
                        if (_jspx_eval_liferay$1ui_panel_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                          out.write("\n\t\t\t\t\t\t\t\t\t<ul class=\"palette-item-content\">\n\n\t\t\t\t\t\t\t\t\t\t");

										for (TemplateVariableDefinition templateVariableDefinition : templateVariableGroup.getTemplateVariableDefinitions()) {
										
                          out.write("\n\n\t\t\t\t\t\t\t\t\t\t\t<li class=\"palette-item-container\">\n\t\t\t\t\t\t\t\t\t\t\t\t<span class=\"palette-item\" data-content=\"");
                          out.print( HtmlUtil.escapeAttribute(_getDataContent(templateVariableDefinition, language)) );
                          out.write("\" data-title=\"");
                          out.print( HtmlUtil.escapeAttribute(_getPaletteItemTitle(request, templateHandlerResourceBundle, templateVariableDefinition)) );
                          out.write("\">\n\t\t\t\t\t\t\t\t\t\t\t\t\t");
                          out.print( HtmlUtil.escape(LanguageUtil.get(request, templateHandlerResourceBundle, templateVariableDefinition.getLabel())) );
                          out.write("\n\n\t\t\t\t\t\t\t\t\t\t\t\t\t");
                          //  c:if
                          com.liferay.taglib.core.IfTag _jspx_th_c_if_15 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                          _jspx_th_c_if_15.setPageContext(_jspx_page_context);
                          _jspx_th_c_if_15.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_panel_2);
                          _jspx_th_c_if_15.setTest( templateVariableDefinition.isCollection() || templateVariableDefinition.isRepeatable() );
                          int _jspx_eval_c_if_15 = _jspx_th_c_if_15.doStartTag();
                          if (_jspx_eval_c_if_15 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                            out.write('*');
                          }
                          if (_jspx_th_c_if_15.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_15);
                            return;
                          }
                          _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_15);
                          out.write("\n\t\t\t\t\t\t\t\t\t\t\t\t</span>\n\t\t\t\t\t\t\t\t\t\t\t</li>\n\n\t\t\t\t\t\t\t\t\t\t");

										}
										
                          out.write("\n\n\t\t\t\t\t\t\t\t\t</ul>\n\t\t\t\t\t\t\t\t");
                        }
                        if (_jspx_th_liferay$1ui_panel_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                          _jspx_tagPool_liferay$1ui_panel_title_id_extended_cssClass_collapsible.reuse(_jspx_th_liferay$1ui_panel_2);
                          return;
                        }
                        _jspx_tagPool_liferay$1ui_panel_title_id_extended_cssClass_collapsible.reuse(_jspx_th_liferay$1ui_panel_2);
                        out.write("\n\n\t\t\t\t\t\t\t");

							}
							
                        out.write("\n\n\t\t\t\t\t\t</div>\n\t\t\t\t\t</div>\n\t\t\t\t</div>\n\t\t\t");
                      }
                      if (_jspx_th_c_if_14.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_14);
                        return;
                      }
                      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_14);
                      out.write("\n\n\t\t\t");

			String editorContainerClass = "lfr-editor-container";

			if (!ddmDisplayContext.isAutocompleteEnabled(language)) {
				editorContainerClass += " lfr-editor-container-full";
			}
			
                      out.write("\n\n\t\t\t<div class=\"");
                      out.print( editorContainerClass );
                      out.write("\" id=\"");
                      if (_jspx_meth_portlet_namespace_35((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1ui_panel_1, _jspx_page_context))
                        return;
                      out.write("editorContainer\">\n\t\t\t\t<div class=\"lfr-rich-editor\" id=\"");
                      if (_jspx_meth_portlet_namespace_36((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1ui_panel_1, _jspx_page_context))
                        return;
                      out.write("richEditor\"></div>\n\t\t\t</div>\n\t\t</div>\n\n\t\t");
                      if (_jspx_meth_aui_input_29((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1ui_panel_1, _jspx_page_context))
                        return;
                      out.write('\n');
                      out.write('	');
                    }
                    if (_jspx_th_liferay$1ui_panel_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_liferay$1ui_panel_title_persistState_markupView_id_extended_collapsible.reuse(_jspx_th_liferay$1ui_panel_1);
                      return;
                    }
                    _jspx_tagPool_liferay$1ui_panel_title_persistState_markupView_id_extended_collapsible.reuse(_jspx_th_liferay$1ui_panel_1);
                    out.write('\n');
                    int evalDoAfterBody = _jspx_th_liferay$1ui_panel$1container_1.doAfterBody();
                    if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                      break;
                  } while (true);
                  if (_jspx_eval_liferay$1ui_panel$1container_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                    out = _jspx_page_context.popBody();
                }
                if (_jspx_th_liferay$1ui_panel$1container_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_liferay$1ui_panel$1container_persistState_markupView_id_extended.reuse(_jspx_th_liferay$1ui_panel$1container_1);
                  return;
                }
                _jspx_tagPool_liferay$1ui_panel$1container_persistState_markupView_id_extended.reuse(_jspx_th_liferay$1ui_panel$1container_1);
                out.write('\n');
                out.write('\n');
                //  aui:script
                com.liferay.taglib.aui.ScriptTag _jspx_th_aui_script_3 = (com.liferay.taglib.aui.ScriptTag) _jspx_tagPool_aui_script_use.get(com.liferay.taglib.aui.ScriptTag.class);
                _jspx_th_aui_script_3.setPageContext(_jspx_page_context);
                _jspx_th_aui_script_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_2);
                _jspx_th_aui_script_3.setUse("aui-ace-autocomplete-freemarker,aui-ace-autocomplete-plugin,aui-ace-autocomplete-velocity,aui-toggler,aui-tooltip,autocomplete-base,autocomplete-filters,event-mouseenter,event-outside,liferay-util-window,resize,transition");
                int _jspx_eval_aui_script_3 = _jspx_th_aui_script_3.doStartTag();
                if (_jspx_eval_aui_script_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  if (_jspx_eval_aui_script_3 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                    out = _jspx_page_context.pushBody();
                    _jspx_th_aui_script_3.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                    _jspx_th_aui_script_3.doInitBody();
                  }
                  do {
                    out.write("\n\tvar ACPlugin = A.Plugin.AceAutoComplete;\n\tvar AObject = A.Object;\n\tvar Util = Liferay.Util;\n\n\tvar STR_EMPTY = '';\n\n\tvar STR_HEIGHT = 'height';\n\n\tvar selectLanguageNode = A.one('#");
                    if (_jspx_meth_portlet_namespace_37((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_3, _jspx_page_context))
                      return;
                    out.write("language');\n\n\tvar panelScriptContainer = A.one('#templateScriptContainer');\n\n\tif (Util.getTop() !== A.config.win) {\n\t\tvar dialog = Util.getWindow();\n\n\t\tif (dialog && A.Lang.isFunction(dialog._detachUIHandlesAutohide)) {\n\t\t\tdialog._detachUIHandlesAutohide();\n\n\t\t\tif (dialog.iframe) {\n\t\t\t\tdialog.iframe.set('closeOnEscape', false);\n\t\t\t}\n\t\t}\n\t}\n\n\tvar prevEditorContent;\n\tvar richEditor;\n\n\t");
                    //  c:if
                    com.liferay.taglib.core.IfTag _jspx_th_c_if_16 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                    _jspx_th_c_if_16.setPageContext(_jspx_page_context);
                    _jspx_th_c_if_16.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_3);
                    _jspx_th_c_if_16.setTest( ddmDisplayContext.isAutocompleteEnabled(language) );
                    int _jspx_eval_c_if_16 = _jspx_th_c_if_16.doStartTag();
                    if (_jspx_eval_c_if_16 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      out.write("\n\t\tvar paletteContainer = panelScriptContainer.one('#");
                      if (_jspx_meth_portlet_namespace_38((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_16, _jspx_page_context))
                        return;
                      out.write("templatePaletteContainer');\n\t\tvar paletteDataContainer = panelScriptContainer.one('#");
                      if (_jspx_meth_portlet_namespace_39((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_16, _jspx_page_context))
                        return;
                      out.write("paletteDataContainer');\n\n\t\tfunction createLiveSearch() {\n\t\t\tvar PaletteSearch = A.Component.create(\n\t\t\t\t{\n\t\t\t\t\tAUGMENTS: [A.AutoCompleteBase],\n\n\t\t\t\t\tEXTENDS: A.Base,\n\n\t\t\t\t\tNAME: 'searchpalette',\n\n\t\t\t\t\tprototype: {\n\t\t\t\t\t\tinitializer: function() {\n\t\t\t\t\t\t\tvar instance = this;\n\n\t\t\t\t\t\t\tinstance._bindUIACBase();\n\t\t\t\t\t\t\tinstance._syncUIACBase();\n\t\t\t\t\t\t}\n\t\t\t\t\t}\n\t\t\t\t}\n\t\t\t);\n\n\t\t\tvar getItems = function() {\n\t\t\t\tvar results = [];\n\n\t\t\t\tpaletteItems.each(\n\t\t\t\t\tfunction(item, index) {\n\t\t\t\t\t\tresults.push(\n\t\t\t\t\t\t\t{\n\t\t\t\t\t\t\t\tdata: item.text().trim(),\n\t\t\t\t\t\t\t\tnode: item.ancestor()\n\t\t\t\t\t\t\t}\n\t\t\t\t\t\t);\n\t\t\t\t\t}\n\t\t\t\t);\n\n\t\t\t\treturn results;\n\t\t\t};\n\n\t\t\tvar getNoResultsNode = function() {\n\t\t\t\tif (!noResultsNode) {\n\t\t\t\t\tnoResultsNode = A.Node.create('<div class=\"alert\">");
                      out.print( UnicodeLanguageUtil.get(request, "there-are-no-results") );
                      out.write("</div>');\n\t\t\t\t}\n\n\t\t\t\treturn noResultsNode;\n\t\t\t};\n\n\t\t\tvar paletteItems = paletteDataContainer.all('.palette-item');\n\t\t\tvar paletteSectionsNode = paletteDataContainer.all('.palette-section');\n\n\t\t\tvar noResultsNode;\n\n\t\t\tvar paletteSearch = new PaletteSearch(\n\t\t\t\t{\n\t\t\t\t\tinputNode: '#");
                      if (_jspx_meth_portlet_namespace_40((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_16, _jspx_page_context))
                        return;
                      out.write("paletteSearch',\n\t\t\t\t\tminQueryLength: 0,\n\t\t\t\t\tnodes: '.palette-item-container',\n\t\t\t\t\tresultFilters: 'phraseMatch',\n\t\t\t\t\tresultTextLocator: 'data',\n\t\t\t\t\tsource: getItems()\n\t\t\t\t}\n\t\t\t);\n\n\t\t\tpaletteSearch.on(\n\t\t\t\t'results',\n\t\t\t\tfunction(event) {\n\t\t\t\t\tpaletteItems.each(\n\t\t\t\t\t\tfunction(item, index) {\n\t\t\t\t\t\t\titem.ancestor().addClass('hide');\n\t\t\t\t\t\t}\n\t\t\t\t\t);\n\n\t\t\t\t\tevent.results.forEach(\n\t\t\t\t\t\tfunction(item, index) {\n\t\t\t\t\t\t\titem.raw.node.removeClass('hide');\n\t\t\t\t\t\t}\n\t\t\t\t\t);\n\n\t\t\t\t\tvar foundVisibleSection;\n\n\t\t\t\t\tpaletteSectionsNode.each(\n\t\t\t\t\t\tfunction(item, index) {\n\t\t\t\t\t\t\tvar visibleItem = item.one('.palette-item-container:not(.hide)');\n\n\t\t\t\t\t\t\tif (visibleItem) {\n\t\t\t\t\t\t\t\tfoundVisibleSection = true;\n\t\t\t\t\t\t\t}\n\n\t\t\t\t\t\t\titem.toggleClass('hide', !visibleItem);\n\t\t\t\t\t\t}\n\t\t\t\t\t);\n\n\t\t\t\t\tvar noResultsNode = getNoResultsNode();\n\n\t\t\t\t\tif (foundVisibleSection) {\n\t\t\t\t\t\tnoResultsNode.remove();\n\t\t\t\t\t}\n\t\t\t\t\telse {\n\t\t\t\t\t\tpaletteDataContainer.appendChild(noResultsNode);\n\t\t\t\t\t}\n\t\t\t\t}\n\t\t\t);\n\t\t}\n\n\t\tfunction onPaletteItemChosen(event) {\n\t\t\tvar editor = richEditor.getEditor();\n");
                      out.write("\n\t\t\tvar item = event.currentTarget;\n\n\t\t\tvar aceAutocomplete = richEditor['ace-autocomplete-plugin'];\n\n\t\t\taceAutocomplete._lockEditor = true;\n\n\t\t\tvar content = item.attr('data-content');\n\n\t\t\tvar fragments = content.split('[$CURSOR$]');\n\n\t\t\tvar cursorPos;\n\t\t\tvar processed;\n\n\t\t\tAObject.each(\n\t\t\t\tfragments,\n\t\t\t\tfunction(item, index) {\n\t\t\t\t\tif (processed) {\n\t\t\t\t\t\tcursorPos = editor.getCursorPosition();\n\t\t\t\t\t}\n\n\t\t\t\t\tprocessed = true;\n\n\t\t\t\t\teditor.insert(item);\n\t\t\t\t}\n\t\t\t);\n\n\t\t\tif (cursorPos) {\n\t\t\t\teditor.moveCursorToPosition(cursorPos);\n\t\t\t}\n\n\t\t\teditor.focus();\n\n\t\t\taceAutocomplete._lockEditor = false;\n\t\t}\n\t");
                    }
                    if (_jspx_th_c_if_16.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_16);
                      return;
                    }
                    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_16);
                    out.write("\n\n\tfunction getEditorContent() {\n\t\tvar content = richEditor.getSession().getValue();\n\n\t\treturn content;\n\t}\n\n\tvar paletteSearchContainer = panelScriptContainer.one('#");
                    if (_jspx_meth_portlet_namespace_41((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_3, _jspx_page_context))
                      return;
                    out.write("paletteSearchContainer');\n\n\tfunction resizeEditor(event) {\n\t\tvar info = event.info;\n\n\t\trichEditor.set(STR_HEIGHT, info.offsetHeight);\n\t\trichEditor.set('width', info.offsetWidth);\n\n\t\tif (!Util.isPhone()) {\n\t\t\tpaletteDataContainer.setStyle(STR_HEIGHT, info.offsetHeight - paletteSearchContainer.height());\n\t\t}\n\t}\n\n\tfunction setEditorContent(content) {\n\t\trichEditor.getSession().setValue(content);\n\n\t\tprevEditorContent = content;\n\t}\n\n\tfunction setEditorPlugins(event) {\n\t\tvar AutoComplete;\n\n\t\t");
                    //  c:choose
                    com.liferay.taglib.core.ChooseTag _jspx_th_c_choose_3 = (com.liferay.taglib.core.ChooseTag) _jspx_tagPool_c_choose.get(com.liferay.taglib.core.ChooseTag.class);
                    _jspx_th_c_choose_3.setPageContext(_jspx_page_context);
                    _jspx_th_c_choose_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_3);
                    int _jspx_eval_c_choose_3 = _jspx_th_c_choose_3.doStartTag();
                    if (_jspx_eval_c_choose_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      out.write("\n\t\t\t");
                      //  c:when
                      com.liferay.taglib.core.WhenTag _jspx_th_c_when_3 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
                      _jspx_th_c_when_3.setPageContext(_jspx_page_context);
                      _jspx_th_c_when_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_3);
                      _jspx_th_c_when_3.setTest( language.equals(TemplateConstants.LANG_TYPE_FTL) );
                      int _jspx_eval_c_when_3 = _jspx_th_c_when_3.doStartTag();
                      if (_jspx_eval_c_when_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                        out.write("\n\t\t\t\tAutoComplete = A.AceEditor.AutoCompleteFreemarker;\n\t\t\t");
                      }
                      if (_jspx_th_c_when_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_3);
                        return;
                      }
                      _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_3);
                      out.write("\n\t\t\t");
                      //  c:when
                      com.liferay.taglib.core.WhenTag _jspx_th_c_when_4 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
                      _jspx_th_c_when_4.setPageContext(_jspx_page_context);
                      _jspx_th_c_when_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_3);
                      _jspx_th_c_when_4.setTest( language.equals(TemplateConstants.LANG_TYPE_VM) );
                      int _jspx_eval_c_when_4 = _jspx_th_c_when_4.doStartTag();
                      if (_jspx_eval_c_when_4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                        out.write("\n\t\t\t\tAutoComplete = A.AceEditor.AutoCompleteVelocity;\n\t\t\t");
                      }
                      if (_jspx_th_c_when_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_4);
                        return;
                      }
                      _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_4);
                      out.write("\n\t\t");
                    }
                    if (_jspx_th_c_choose_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_3);
                      return;
                    }
                    _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_3);
                    out.write("\n\n\t\tif (AutoComplete) {\n\t\t\tvar processor = new AutoComplete(\n\t\t\t\t{\n\t\t\t\t\tvariables: ");
                    out.print( ddmDisplayContext.getAutocompleteJSON(request, language) );
                    out.write("\n\t\t\t\t}\n\t\t\t);\n\n\t\t\tif (processor) {\n\t\t\t\trichEditor.unplug(ACPlugin);\n\n\t\t\t\trichEditor.plug(\n\t\t\t\t\tACPlugin,\n\t\t\t\t\t{\n\t\t\t\t\t\tprocessor: processor,\n\t\t\t\t\t\trender: true,\n\t\t\t\t\t\tvisible: false,\n\t\t\t\t\t\tzIndex: 10000\n\t\t\t\t\t}\n\t\t\t\t);\n\t\t\t}\n\t\t\telse {\n\t\t\t\trichEditor.unplug(ACPlugin);\n\t\t\t}\n\t\t}\n\t}\n\n\t");

	String langType = ParamUtil.getString(request, "langType");
	
                    out.write("\n\n\tvar editorContentElement = A.one('#");
                    if (_jspx_meth_portlet_namespace_42((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_3, _jspx_page_context))
                      return;
                    out.write("scriptContent');\n\n\tvar editorNode = A.one('#");
                    if (_jspx_meth_portlet_namespace_43((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_3, _jspx_page_context))
                      return;
                    out.write("richEditor');\n\n\tA.on(\n\t\t'domready',\n\t\tfunction(event) {\n\t\t\trichEditor = new A.AceEditor(\n\t\t\t\t{\n\t\t\t\t\tboundingBox: editorNode,\n\t\t\t\t\theight: 400,\n\t\t\t\t\tmode: '");
                    out.print( EditorModeUtil.getEditorMode(langType) );
                    out.write("',\n\t\t\t\t\twidth: '100%'\n\t\t\t\t}\n\t\t\t).render();\n\n\t\t\tnew A.Resize(\n\t\t\t\t{\n\t\t\t\t\thandles: ['br'],\n\t\t\t\t\tnode: editorNode,\n\t\t\t\t\ton: {\n\t\t\t\t\t\tresize: resizeEditor\n\t\t\t\t\t}\n\t\t\t\t}\n\t\t\t);\n\n\t\t\tif (editorContentElement) {\n\t\t\t\tsetEditorContent(editorContentElement.val());\n\t\t\t}\n\n\t\t\tLiferay.on(\n\t\t\t\t'");
                    if (_jspx_meth_portlet_namespace_44((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_3, _jspx_page_context))
                      return;
                    out.write("saveTemplate',\n\t\t\t\tfunction(event) {\n\t\t\t\t\teditorContentElement.val(getEditorContent());\n\t\t\t\t}\n\t\t\t);\n\n\t\t\tselectLanguageNode.on(\n\t\t\t\t'change',\n\t\t\t\tfunction(event) {\n\t\t\t\t\tLiferay.fire('");
                    if (_jspx_meth_portlet_namespace_45((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_3, _jspx_page_context))
                      return;
                    out.write("refreshEditor');\n\t\t\t\t}\n\t\t\t);\n\n\t\t\tsetEditorPlugins();\n\n\t\t\t");
                    //  c:if
                    com.liferay.taglib.core.IfTag _jspx_th_c_if_17 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                    _jspx_th_c_if_17.setPageContext(_jspx_page_context);
                    _jspx_th_c_if_17.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_3);
                    _jspx_th_c_if_17.setTest( ddmDisplayContext.isAutocompleteEnabled(language) );
                    int _jspx_eval_c_if_17 = _jspx_th_c_if_17.doStartTag();
                    if (_jspx_eval_c_if_17 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      out.write("\n\t\t\t\tpaletteContainer.delegate('click', onPaletteItemChosen, '.palette-item');\n\n\t\t\t\tnew A.TogglerDelegate(\n\t\t\t\t\t{\n\t\t\t\t\t\tanimated: true,\n\t\t\t\t\t\tcontainer: paletteDataContainer,\n\t\t\t\t\t\tcontent: '.palette-item-content',\n\t\t\t\t\t\theader: '.palette-item-header'\n\t\t\t\t\t}\n\t\t\t\t);\n\n\t\t\t\tnew A.TooltipDelegate(\n\t\t\t\t\t{\n\t\t\t\t\t\talign: {\n\t\t\t\t\t\t\tpoints: [A.WidgetPositionAlign.LC, A.WidgetPositionAlign.RC]\n\t\t\t\t\t\t},\n\t\t\t\t\t\tduration: 0.5,\n\t\t\t\t\t\thtml: true,\n\t\t\t\t\t\tposition: 'right',\n\t\t\t\t\t\ttrigger: '#");
                      if (_jspx_meth_portlet_namespace_46((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_17, _jspx_page_context))
                        return;
                      out.write("templatePaletteContainer .palette-item',\n\t\t\t\t\t\tvisible: false,\n\t\t\t\t\t\tzIndex: 6\n\t\t\t\t\t}\n\t\t\t\t);\n\n\t\t\t\tcreateLiveSearch();\n\t\t\t");
                    }
                    if (_jspx_th_c_if_17.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_17);
                      return;
                    }
                    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_17);
                    out.write("\n\t\t},\n\t\t'#");
                    if (_jspx_meth_portlet_namespace_47((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_3, _jspx_page_context))
                      return;
                    out.write("richEditor'\n\t);\n\n\tLiferay.on(\n\t\t'");
                    if (_jspx_meth_portlet_namespace_48((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_3, _jspx_page_context))
                      return;
                    out.write("refreshEditor',\n\t\tfunction(event) {\n\t\t\tvar form = A.one('#");
                    if (_jspx_meth_portlet_namespace_49((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_3, _jspx_page_context))
                      return;
                    out.write("fm');\n\n\t\t\t");
                    //  portlet:renderURL
                    com.liferay.taglib.portlet.RenderURLTag _jspx_th_portlet_renderURL_1 = (com.liferay.taglib.portlet.RenderURLTag) _jspx_tagPool_portlet_renderURL_var.get(com.liferay.taglib.portlet.RenderURLTag.class);
                    _jspx_th_portlet_renderURL_1.setPageContext(_jspx_page_context);
                    _jspx_th_portlet_renderURL_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_3);
                    _jspx_th_portlet_renderURL_1.setVar("refreshTemplateURL");
                    int _jspx_eval_portlet_renderURL_1 = _jspx_th_portlet_renderURL_1.doStartTag();
                    if (_jspx_eval_portlet_renderURL_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      out.write("\n\t\t\t\t");
                      if (_jspx_meth_portlet_param_6((javax.servlet.jsp.tagext.JspTag) _jspx_th_portlet_renderURL_1, _jspx_page_context))
                        return;
                      out.write("\n\t\t\t");
                    }
                    if (_jspx_th_portlet_renderURL_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_portlet_renderURL_var.reuse(_jspx_th_portlet_renderURL_1);
                      return;
                    }
                    _jspx_tagPool_portlet_renderURL_var.reuse(_jspx_th_portlet_renderURL_1);
                    java.lang.String refreshTemplateURL = null;
                    refreshTemplateURL = (java.lang.String) _jspx_page_context.findAttribute("refreshTemplateURL");
                    out.write("\n\n\t\t\tform.attr('action', '");
                    out.print( refreshTemplateURL );
                    out.write("');\n\n\t\t\tif (richEditor.getEditor().getSession().getUndoManager().hasUndo()) {\n\t\t\t\tLiferay.fire('");
                    if (_jspx_meth_portlet_namespace_50((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_3, _jspx_page_context))
                      return;
                    out.write("saveTemplate');\n\t\t\t}\n\t\t\t");
                    //  c:if
                    com.liferay.taglib.core.IfTag _jspx_th_c_if_18 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                    _jspx_th_c_if_18.setPageContext(_jspx_page_context);
                    _jspx_th_c_if_18.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_3);
                    _jspx_th_c_if_18.setTest( template == null );
                    int _jspx_eval_c_if_18 = _jspx_th_c_if_18.doStartTag();
                    if (_jspx_eval_c_if_18 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      out.write("\n\t\t\t\telse {\n\t\t\t\t\teditorContentElement.val(STR_EMPTY);\n\t\t\t\t}\n\t\t\t");
                    }
                    if (_jspx_th_c_if_18.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_18);
                      return;
                    }
                    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_18);
                    out.write("\n\n\t\t\tsubmitForm(form, null, null, false);\n\t\t}\n\t);\n");
                    int evalDoAfterBody = _jspx_th_aui_script_3.doAfterBody();
                    if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                      break;
                  } while (true);
                  if (_jspx_eval_aui_script_3 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                    out = _jspx_page_context.popBody();
                }
                if (_jspx_th_aui_script_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_aui_script_use.reuse(_jspx_th_aui_script_3);
                  return;
                }
                _jspx_tagPool_aui_script_use.reuse(_jspx_th_aui_script_3);
                out.write('\n');
                out.write('\n');
                out.write('\n');
                out.write('\n');
                out.write("\n\t\t\t\t\t\t");
              }
              if (_jspx_th_c_otherwise_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_2);
                return;
              }
              _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_2);
              out.write("\n\t\t\t\t\t");
            }
            if (_jspx_th_c_choose_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_2);
              return;
            }
            _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_2);
            out.write("\n\t\t\t\t");
          }
          if (_jspx_th_aui_fieldset_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_aui_fieldset.reuse(_jspx_th_aui_fieldset_0);
            return;
          }
          _jspx_tagPool_aui_fieldset.reuse(_jspx_th_aui_fieldset_0);
          out.write("\n\t\t\t");
        }
        if (_jspx_th_aui_fieldset$1group_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_aui_fieldset$1group_markupView.reuse(_jspx_th_aui_fieldset$1group_0);
          return;
        }
        _jspx_tagPool_aui_fieldset$1group_markupView.reuse(_jspx_th_aui_fieldset$1group_0);
        out.write("\n\t\t</div>\n\t");
      }
      if (_jspx_th_aui_form_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_aui_form_onSubmit_name_method_enctype_cssClass_action.reuse(_jspx_th_aui_form_0);
        return;
      }
      _jspx_tagPool_aui_form_onSubmit_name_method_enctype_cssClass_action.reuse(_jspx_th_aui_form_0);
      out.write("\n\n\t");
      //  c:if
      com.liferay.taglib.core.IfTag _jspx_th_c_if_19 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
      _jspx_th_c_if_19.setPageContext(_jspx_page_context);
      _jspx_th_c_if_19.setParent(null);
      _jspx_th_c_if_19.setTest( type.equals(DDMTemplateConstants.TEMPLATE_TYPE_DISPLAY) );
      int _jspx_eval_c_if_19 = _jspx_th_c_if_19.doStartTag();
      if (_jspx_eval_c_if_19 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        out.write("\n\t\t");
        //  aui:script
        com.liferay.taglib.aui.ScriptTag _jspx_th_aui_script_4 = (com.liferay.taglib.aui.ScriptTag) _jspx_tagPool_aui_script_use.get(com.liferay.taglib.aui.ScriptTag.class);
        _jspx_th_aui_script_4.setPageContext(_jspx_page_context);
        _jspx_th_aui_script_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_19);
        _jspx_th_aui_script_4.setUse("aui-toggler");
        int _jspx_eval_aui_script_4 = _jspx_th_aui_script_4.doStartTag();
        if (_jspx_eval_aui_script_4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          if (_jspx_eval_aui_script_4 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
            out = _jspx_page_context.pushBody();
            _jspx_th_aui_script_4.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
            _jspx_th_aui_script_4.doInitBody();
          }
          do {
            out.write("\n\t\t\tvar container = A.one('#");
            if (_jspx_meth_portlet_namespace_51((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_4, _jspx_page_context))
              return;
            out.write("smallImageContainer');\n\n\t\t\tvar types = container.all('.lfr-ddm-small-image-type');\n\t\t\tvar values = container.all('.lfr-ddm-small-image-value');\n\n\t\t\tvar selectSmallImageType = function(index) {\n\t\t\t\ttypes.attr('checked', false);\n\n\t\t\t\ttypes.item(index).attr('checked', true);\n\n\t\t\t\tvalues.attr('disabled', true);\n\n\t\t\t\tvalues.item(index).attr('disabled', false);\n\t\t\t};\n\n\t\t\tcontainer.delegate(\n\t\t\t\t'change',\n\t\t\t\tfunction(event) {\n\t\t\t\t\tvar index = types.indexOf(event.currentTarget);\n\n\t\t\t\t\tselectSmallImageType(index);\n\t\t\t\t},\n\t\t\t\t'.lfr-ddm-small-image-type'\n\t\t\t);\n\n\t\t\tnew A.Toggler(\n\t\t\t\t{\n\t\t\t\t\tanimated: true,\n\t\t\t\t\tcontent: '#");
            if (_jspx_meth_portlet_namespace_52((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_4, _jspx_page_context))
              return;
            out.write("smallImageContainer .lfr-ddm-small-image-content',\n\t\t\t\t\texpanded: ");
            out.print( smallImage );
            out.write(",\n\t\t\t\t\theader: '#");
            if (_jspx_meth_portlet_namespace_53((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_4, _jspx_page_context))
              return;
            out.write("smallImageContainer .lfr-ddm-small-image-header',\n\t\t\t\t\ton: {\n\t\t\t\t\t\tanimatingChange: function(event) {\n\t\t\t\t\t\t\tvar instance = this;\n\n\t\t\t\t\t\t\tvar expanded = !instance.get('expanded');\n\n\t\t\t\t\t\t\tA.one('#");
            if (_jspx_meth_portlet_namespace_54((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_4, _jspx_page_context))
              return;
            out.write("smallImage').attr('checked', expanded);\n\n\t\t\t\t\t\t\tif (expanded) {\n\t\t\t\t\t\t\t\ttypes.each(\n\t\t\t\t\t\t\t\t\tfunction(item, index) {\n\t\t\t\t\t\t\t\t\t\tif (item.get('checked')) {\n\t\t\t\t\t\t\t\t\t\t\tvalues.item(index).attr('disabled', false);\n\t\t\t\t\t\t\t\t\t\t}\n\t\t\t\t\t\t\t\t\t}\n\t\t\t\t\t\t\t\t);\n\t\t\t\t\t\t\t}\n\t\t\t\t\t\t\telse {\n\t\t\t\t\t\t\t\tvalues.attr('disabled', true);\n\t\t\t\t\t\t\t}\n\t\t\t\t\t\t}\n\t\t\t\t\t}\n\t\t\t\t}\n\t\t\t);\n\n\t\t\tselectSmallImageType('");
            out.print( ((template != null) && Validator.isNotNull(template.getSmallImageURL())) ? 0 : 1 );
            out.write("');\n\t\t");
            int evalDoAfterBody = _jspx_th_aui_script_4.doAfterBody();
            if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
              break;
          } while (true);
          if (_jspx_eval_aui_script_4 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
            out = _jspx_page_context.popBody();
        }
        if (_jspx_th_aui_script_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_aui_script_use.reuse(_jspx_th_aui_script_4);
          return;
        }
        _jspx_tagPool_aui_script_use.reuse(_jspx_th_aui_script_4);
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
      _jspx_th_c_if_20.setParent(null);
      _jspx_th_c_if_20.setTest( ddmDisplay.isShowStructureSelector() && ((template == null) || (template.getClassPK() == 0)) );
      int _jspx_eval_c_if_20 = _jspx_th_c_if_20.doStartTag();
      if (_jspx_eval_c_if_20 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        out.write("\n\t\t");
        //  aui:script
        com.liferay.taglib.aui.ScriptTag _jspx_th_aui_script_5 = (com.liferay.taglib.aui.ScriptTag) _jspx_tagPool_aui_script.get(com.liferay.taglib.aui.ScriptTag.class);
        _jspx_th_aui_script_5.setPageContext(_jspx_page_context);
        _jspx_th_aui_script_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_20);
        int _jspx_eval_aui_script_5 = _jspx_th_aui_script_5.doStartTag();
        if (_jspx_eval_aui_script_5 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          if (_jspx_eval_aui_script_5 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
            out = _jspx_page_context.pushBody();
            _jspx_th_aui_script_5.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
            _jspx_th_aui_script_5.doInitBody();
          }
          do {
            out.write("\n\t\t\tfunction ");
            if (_jspx_meth_portlet_namespace_55((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_5, _jspx_page_context))
              return;
            out.write("openDDMStructureSelector() {\n\t\t\t\tLiferay.Util.openDDMPortlet(\n\t\t\t\t\t{\n\t\t\t\t\t\tbasePortletURL: '");
            out.print( PortletURLFactoryUtil.create(request, DDMPortletKeys.DYNAMIC_DATA_MAPPING, PortletRequest.RENDER_PHASE) );
            out.write("',\n\t\t\t\t\t\tclassNameId: '");
            out.print( PortalUtil.getClassNameId(DDMStructure.class) );
            out.write("',\n\t\t\t\t\t\tclassPK: 0,\n\t\t\t\t\t\teventName: '");
            if (_jspx_meth_portlet_namespace_56((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_5, _jspx_page_context))
              return;
            out.write("selectStructure',\n\t\t\t\t\t\tgroupId: ");
            out.print( groupId );
            out.write(",\n\t\t\t\t\t\tmvcPath: '/select_structure.jsp',\n\t\t\t\t\t\tnavigationStartsOn: '");
            out.print( DDMNavigationHelper.SELECT_STRUCTURE );
            out.write("',\n\t\t\t\t\t\tshowAncestorScopes: true,\n\t\t\t\t\t\ttitle: '");
            out.print( UnicodeLanguageUtil.get(request, "structures") );
            out.write("'\n\t\t\t\t\t},\n\t\t\t\t\tfunction(event) {\n\t\t\t\t\t\tif (document.");
            if (_jspx_meth_portlet_namespace_57((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_5, _jspx_page_context))
              return;
            out.write("fm.");
            if (_jspx_meth_portlet_namespace_58((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_5, _jspx_page_context))
              return;
            out.write("classPK.value != event.ddmstructureid) {\n\t\t\t\t\t\t\tdocument.");
            if (_jspx_meth_portlet_namespace_59((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_5, _jspx_page_context))
              return;
            out.write("fm.");
            if (_jspx_meth_portlet_namespace_60((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_5, _jspx_page_context))
              return;
            out.write("classPK.value = event.ddmstructureid;\n\n\t\t\t\t\t\t\tLiferay.fire('");
            if (_jspx_meth_portlet_namespace_61((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_5, _jspx_page_context))
              return;
            out.write("refreshEditor');\n\t\t\t\t\t\t}\n\t\t\t\t\t}\n\t\t\t\t);\n\t\t\t}\n\t\t");
            int evalDoAfterBody = _jspx_th_aui_script_5.doAfterBody();
            if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
              break;
          } while (true);
          if (_jspx_eval_aui_script_5 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
            out = _jspx_page_context.popBody();
        }
        if (_jspx_th_aui_script_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_aui_script.reuse(_jspx_th_aui_script_5);
          return;
        }
        _jspx_tagPool_aui_script.reuse(_jspx_th_aui_script_5);
        out.write('\n');
        out.write('	');
      }
      if (_jspx_th_c_if_20.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_20);
        return;
      }
      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_20);
      out.write("\n\n\t");
      //  aui:button-row
      com.liferay.taglib.aui.ButtonRowTag _jspx_th_aui_button$1row_0 = (com.liferay.taglib.aui.ButtonRowTag) _jspx_tagPool_aui_button$1row.get(com.liferay.taglib.aui.ButtonRowTag.class);
      _jspx_th_aui_button$1row_0.setPageContext(_jspx_page_context);
      _jspx_th_aui_button$1row_0.setParent(null);
      int _jspx_eval_aui_button$1row_0 = _jspx_th_aui_button$1row_0.doStartTag();
      if (_jspx_eval_aui_button$1row_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        out.write("\n\t\t");
        //  aui:script
        com.liferay.taglib.aui.ScriptTag _jspx_th_aui_script_6 = (com.liferay.taglib.aui.ScriptTag) _jspx_tagPool_aui_script.get(com.liferay.taglib.aui.ScriptTag.class);
        _jspx_th_aui_script_6.setPageContext(_jspx_page_context);
        _jspx_th_aui_script_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_button$1row_0);
        int _jspx_eval_aui_script_6 = _jspx_th_aui_script_6.doStartTag();
        if (_jspx_eval_aui_script_6 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          if (_jspx_eval_aui_script_6 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
            out = _jspx_page_context.pushBody();
            _jspx_th_aui_script_6.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
            _jspx_th_aui_script_6.doInitBody();
          }
          do {
            out.write("\n\t\t\tLiferay.after(\n\t\t\t\t'");
            if (_jspx_meth_portlet_namespace_62((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_6, _jspx_page_context))
              return;
            out.write("saveTemplate',\n\t\t\t\tfunction() {\n\t\t\t\t\tsubmitForm(document.");
            if (_jspx_meth_portlet_namespace_63((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_6, _jspx_page_context))
              return;
            out.write("fm);\n\t\t\t\t}\n\t\t\t);\n\n\t\t\tfunction ");
            if (_jspx_meth_portlet_namespace_64((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_6, _jspx_page_context))
              return;
            out.write("saveDraftTemplate() {\n\t\t\t\tvar form = AUI.$('#");
            if (_jspx_meth_portlet_namespace_65((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_6, _jspx_page_context))
              return;
            out.write("fm');\n\n\t\t\t\tform.fm('status').val(");
            out.print( String.valueOf(WorkflowConstants.STATUS_DRAFT) );
            out.write(");\n\n\t\t\t\tLiferay.fire('");
            out.print( renderResponse.getNamespace() + "saveTemplate" );
            out.write("');\n\t\t\t}\n\n\t\t\tfunction ");
            if (_jspx_meth_portlet_namespace_66((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_6, _jspx_page_context))
              return;
            out.write("saveAndContinueTemplate() {\n\t\t\t\tdocument.");
            if (_jspx_meth_portlet_namespace_67((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_6, _jspx_page_context))
              return;
            out.write("fm.");
            if (_jspx_meth_portlet_namespace_68((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_6, _jspx_page_context))
              return;
            out.write("saveAndContinue.value = '1';\n\n\t\t\t\tLiferay.fire('");
            if (_jspx_meth_portlet_namespace_69((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_6, _jspx_page_context))
              return;
            out.write("saveTemplate');\n\t\t\t}\n\t\t");
            int evalDoAfterBody = _jspx_th_aui_script_6.doAfterBody();
            if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
              break;
          } while (true);
          if (_jspx_eval_aui_script_6 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
            out = _jspx_page_context.popBody();
        }
        if (_jspx_th_aui_script_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_aui_script.reuse(_jspx_th_aui_script_6);
          return;
        }
        _jspx_tagPool_aui_script.reuse(_jspx_th_aui_script_6);
        out.write("\n\n\t\t");

		String taglibOnClick = "Liferay.fire('" + liferayPortletResponse.getNamespace() + "saveTemplate');";
		
        out.write("\n\n\t\t");
        //  aui:button
        com.liferay.taglib.aui.ButtonTag _jspx_th_aui_button_0 = (com.liferay.taglib.aui.ButtonTag) _jspx_tagPool_aui_button_value_primary_onClick_nobody.get(com.liferay.taglib.aui.ButtonTag.class);
        _jspx_th_aui_button_0.setPageContext(_jspx_page_context);
        _jspx_th_aui_button_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_button$1row_0);
        _jspx_th_aui_button_0.setOnClick( taglibOnClick );
        _jspx_th_aui_button_0.setPrimary( true );
        _jspx_th_aui_button_0.setValue( LanguageUtil.get(request, "save") );
        int _jspx_eval_aui_button_0 = _jspx_th_aui_button_0.doStartTag();
        if (_jspx_th_aui_button_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_aui_button_value_primary_onClick_nobody.reuse(_jspx_th_aui_button_0);
          return;
        }
        _jspx_tagPool_aui_button_value_primary_onClick_nobody.reuse(_jspx_th_aui_button_0);
        out.write("\n\n\t\t");
        //  aui:button
        com.liferay.taglib.aui.ButtonTag _jspx_th_aui_button_1 = (com.liferay.taglib.aui.ButtonTag) _jspx_tagPool_aui_button_value_onClick_nobody.get(com.liferay.taglib.aui.ButtonTag.class);
        _jspx_th_aui_button_1.setPageContext(_jspx_page_context);
        _jspx_th_aui_button_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_button$1row_0);
        _jspx_th_aui_button_1.setOnClick( renderResponse.getNamespace() + "saveAndContinueTemplate();" );
        _jspx_th_aui_button_1.setValue( LanguageUtil.get(resourceBundle, "save-and-continue") );
        int _jspx_eval_aui_button_1 = _jspx_th_aui_button_1.doStartTag();
        if (_jspx_th_aui_button_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_aui_button_value_onClick_nobody.reuse(_jspx_th_aui_button_1);
          return;
        }
        _jspx_tagPool_aui_button_value_onClick_nobody.reuse(_jspx_th_aui_button_1);
        out.write("\n\n\t\t");
        //  c:if
        com.liferay.taglib.core.IfTag _jspx_th_c_if_21 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
        _jspx_th_c_if_21.setPageContext(_jspx_page_context);
        _jspx_th_c_if_21.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_button$1row_0);
        _jspx_th_c_if_21.setTest( ddmDisplay.isVersioningEnabled() );
        int _jspx_eval_c_if_21 = _jspx_th_c_if_21.doStartTag();
        if (_jspx_eval_c_if_21 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          out.write("\n\t\t\t");
          //  aui:button
          com.liferay.taglib.aui.ButtonTag _jspx_th_aui_button_2 = (com.liferay.taglib.aui.ButtonTag) _jspx_tagPool_aui_button_value_onClick_nobody.get(com.liferay.taglib.aui.ButtonTag.class);
          _jspx_th_aui_button_2.setPageContext(_jspx_page_context);
          _jspx_th_aui_button_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_21);
          _jspx_th_aui_button_2.setOnClick( renderResponse.getNamespace() + "saveDraftTemplate();" );
          _jspx_th_aui_button_2.setValue( LanguageUtil.get(request, "save-draft") );
          int _jspx_eval_aui_button_2 = _jspx_th_aui_button_2.doStartTag();
          if (_jspx_th_aui_button_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_aui_button_value_onClick_nobody.reuse(_jspx_th_aui_button_2);
            return;
          }
          _jspx_tagPool_aui_button_value_onClick_nobody.reuse(_jspx_th_aui_button_2);
          out.write("\n\t\t");
        }
        if (_jspx_th_c_if_21.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_21);
          return;
        }
        _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_21);
        out.write("\n\n\t\t");
        //  aui:button
        com.liferay.taglib.aui.ButtonTag _jspx_th_aui_button_3 = (com.liferay.taglib.aui.ButtonTag) _jspx_tagPool_aui_button_type_href_nobody.get(com.liferay.taglib.aui.ButtonTag.class);
        _jspx_th_aui_button_3.setPageContext(_jspx_page_context);
        _jspx_th_aui_button_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_button$1row_0);
        _jspx_th_aui_button_3.setHref( ddmDisplay.getEditTemplateBackURL(liferayPortletRequest, liferayPortletResponse, classNameId, classPK, resourceClassNameId, portletResource) );
        _jspx_th_aui_button_3.setType("cancel");
        int _jspx_eval_aui_button_3 = _jspx_th_aui_button_3.doStartTag();
        if (_jspx_th_aui_button_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_aui_button_type_href_nobody.reuse(_jspx_th_aui_button_3);
          return;
        }
        _jspx_tagPool_aui_button_type_href_nobody.reuse(_jspx_th_aui_button_3);
        out.write('\n');
        out.write('	');
      }
      if (_jspx_th_aui_button$1row_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_aui_button$1row.reuse(_jspx_th_aui_button$1row_0);
        return;
      }
      _jspx_tagPool_aui_button$1row.reuse(_jspx_th_aui_button$1row_0);
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

  private boolean _jspx_meth_portlet_param_0(javax.servlet.jsp.tagext.JspTag _jspx_th_portlet_actionURL_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:param
    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_0 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
    _jspx_th_portlet_param_0.setPageContext(_jspx_page_context);
    _jspx_th_portlet_param_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_actionURL_0);
    _jspx_th_portlet_param_0.setName("mvcPath");
    _jspx_th_portlet_param_0.setValue("/edit_template.jsp");
    int _jspx_eval_portlet_param_0 = _jspx_th_portlet_param_0.doStartTag();
    if (_jspx_th_portlet_param_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_0);
      return true;
    }
    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_0);
    return false;
  }

  private boolean _jspx_meth_portlet_param_1(javax.servlet.jsp.tagext.JspTag _jspx_th_portlet_actionURL_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:param
    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_1 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
    _jspx_th_portlet_param_1.setPageContext(_jspx_page_context);
    _jspx_th_portlet_param_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_actionURL_1);
    _jspx_th_portlet_param_1.setName("mvcPath");
    _jspx_th_portlet_param_1.setValue("/edit_template.jsp");
    int _jspx_eval_portlet_param_1 = _jspx_th_portlet_param_1.doStartTag();
    if (_jspx_th_portlet_param_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_1);
      return true;
    }
    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_1);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_0(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1ui_error_3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_0 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_0.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_error_3);
    _jspx_th_liferay$1ui_message_0.setKey("image-names-must-end-with-one-of-the-following-extensions");
    int _jspx_eval_liferay$1ui_message_0 = _jspx_th_liferay$1ui_message_0.doStartTag();
    if (_jspx_th_liferay$1ui_message_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_0);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_0);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_0 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_0.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_1);
    int _jspx_eval_portlet_namespace_0 = _jspx_th_portlet_namespace_0.doStartTag();
    if (_jspx_th_portlet_namespace_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_0);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_0);
    return false;
  }

  private boolean _jspx_meth_portlet_param_2(javax.servlet.jsp.tagext.JspTag _jspx_th_portlet_renderURL_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:param
    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_2 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
    _jspx_th_portlet_param_2.setPageContext(_jspx_page_context);
    _jspx_th_portlet_param_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_0);
    _jspx_th_portlet_param_2.setName("mvcPath");
    _jspx_th_portlet_param_2.setValue("/view_template_history.jsp");
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

  private boolean _jspx_meth_aui_field$1wrapper_0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:field-wrapper
    com.liferay.taglib.aui.FieldWrapperTag _jspx_th_aui_field$1wrapper_0 = (com.liferay.taglib.aui.FieldWrapperTag) _jspx_tagPool_aui_field$1wrapper.get(com.liferay.taglib.aui.FieldWrapperTag.class);
    _jspx_th_aui_field$1wrapper_0.setPageContext(_jspx_page_context);
    _jspx_th_aui_field$1wrapper_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_2);
    int _jspx_eval_aui_field$1wrapper_0 = _jspx_th_aui_field$1wrapper_0.doStartTag();
    if (_jspx_eval_aui_field$1wrapper_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      out.write("\n\t\t\t\t\t\t\t<div class=\"alert alert-warning\">\n\t\t\t\t\t\t\t\t");
      if (_jspx_meth_liferay$1ui_message_2((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_field$1wrapper_0, _jspx_page_context))
        return true;
      out.write("\n\t\t\t\t\t\t\t</div>\n\t\t\t\t\t\t");
    }
    if (_jspx_th_aui_field$1wrapper_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_field$1wrapper.reuse(_jspx_th_aui_field$1wrapper_0);
      return true;
    }
    _jspx_tagPool_aui_field$1wrapper.reuse(_jspx_th_aui_field$1wrapper_0);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_2(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_field$1wrapper_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_2 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_2.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_field$1wrapper_0);
    _jspx_th_liferay$1ui_message_2.setKey("this-template-does-not-belong-to-this-site.-you-may-affect-other-sites-if-you-edit-this-template");
    int _jspx_eval_liferay$1ui_message_2 = _jspx_th_liferay$1ui_message_2.doStartTag();
    if (_jspx_th_liferay$1ui_message_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_2);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_2);
    return false;
  }

  private boolean _jspx_meth_aui_input_16(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1ui_panel_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:input
    com.liferay.taglib.aui.InputTag _jspx_th_aui_input_16 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
    _jspx_th_aui_input_16.setPageContext(_jspx_page_context);
    _jspx_th_aui_input_16.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_panel_0);
    _jspx_th_aui_input_16.setName("description");
    int _jspx_eval_aui_input_16 = _jspx_th_aui_input_16.doStartTag();
    if (_jspx_th_aui_input_16.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_input_name_nobody.reuse(_jspx_th_aui_input_16);
      return true;
    }
    _jspx_tagPool_aui_input_name_nobody.reuse(_jspx_th_aui_input_16);
    return false;
  }

  private boolean _jspx_meth_aui_select_1(javax.servlet.jsp.tagext.JspTag _jspx_th_c_when_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:select
    com.liferay.taglib.aui.SelectTag _jspx_th_aui_select_1 = (com.liferay.taglib.aui.SelectTag) _jspx_tagPool_aui_select_name_label_helpMessage.get(com.liferay.taglib.aui.SelectTag.class);
    _jspx_th_aui_select_1.setPageContext(_jspx_page_context);
    _jspx_th_aui_select_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_1);
    _jspx_th_aui_select_1.setHelpMessage("only-allow-deleting-required-fields-in-edit-mode");
    _jspx_th_aui_select_1.setLabel("mode");
    _jspx_th_aui_select_1.setName("mode");
    int _jspx_eval_aui_select_1 = _jspx_th_aui_select_1.doStartTag();
    if (_jspx_eval_aui_select_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_aui_select_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_aui_select_1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_aui_select_1.doInitBody();
      }
      do {
        out.write("\n\t\t\t\t\t\t\t\t\t\t");
        if (_jspx_meth_aui_option_1((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_select_1, _jspx_page_context))
          return true;
        out.write("\n\t\t\t\t\t\t\t\t\t\t");
        if (_jspx_meth_aui_option_2((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_select_1, _jspx_page_context))
          return true;
        out.write("\n\t\t\t\t\t\t\t\t\t");
        int evalDoAfterBody = _jspx_th_aui_select_1.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_aui_select_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
        out = _jspx_page_context.popBody();
    }
    if (_jspx_th_aui_select_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_select_name_label_helpMessage.reuse(_jspx_th_aui_select_1);
      return true;
    }
    _jspx_tagPool_aui_select_name_label_helpMessage.reuse(_jspx_th_aui_select_1);
    return false;
  }

  private boolean _jspx_meth_aui_option_1(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_select_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:option
    com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_1 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
    _jspx_th_aui_option_1.setPageContext(_jspx_page_context);
    _jspx_th_aui_option_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_1);
    _jspx_th_aui_option_1.setLabel(new String("create"));
    int _jspx_eval_aui_option_1 = _jspx_th_aui_option_1.doStartTag();
    if (_jspx_th_aui_option_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_option_label_nobody.reuse(_jspx_th_aui_option_1);
      return true;
    }
    _jspx_tagPool_aui_option_label_nobody.reuse(_jspx_th_aui_option_1);
    return false;
  }

  private boolean _jspx_meth_aui_option_2(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_select_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:option
    com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_2 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
    _jspx_th_aui_option_2.setPageContext(_jspx_page_context);
    _jspx_th_aui_option_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_1);
    _jspx_th_aui_option_2.setLabel(new String("edit"));
    int _jspx_eval_aui_option_2 = _jspx_th_aui_option_2.doStartTag();
    if (_jspx_th_aui_option_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_option_label_nobody.reuse(_jspx_th_aui_option_2);
      return true;
    }
    _jspx_tagPool_aui_option_label_nobody.reuse(_jspx_th_aui_option_2);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_2(javax.servlet.jsp.tagext.JspTag _jspx_th_c_otherwise_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_2 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_2.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_1);
    int _jspx_eval_portlet_namespace_2 = _jspx_th_portlet_namespace_2.doStartTag();
    if (_jspx_th_portlet_namespace_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_2);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_2);
    return false;
  }

  private boolean _jspx_meth_aui_input_21(javax.servlet.jsp.tagext.JspTag _jspx_th_c_otherwise_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:input
    com.liferay.taglib.aui.InputTag _jspx_th_aui_input_21 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
    _jspx_th_aui_input_21.setPageContext(_jspx_page_context);
    _jspx_th_aui_input_21.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_1);
    _jspx_th_aui_input_21.setName("smallImage");
    int _jspx_eval_aui_input_21 = _jspx_th_aui_input_21.doStartTag();
    if (_jspx_th_aui_input_21.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_input_name_nobody.reuse(_jspx_th_aui_input_21);
      return true;
    }
    _jspx_tagPool_aui_input_name_nobody.reuse(_jspx_th_aui_input_21);
    return false;
  }

  private boolean _jspx_meth_aui_input_26(javax.servlet.jsp.tagext.JspTag _jspx_th_c_when_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:input
    com.liferay.taglib.aui.InputTag _jspx_th_aui_input_26 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
    _jspx_th_aui_input_26.setPageContext(_jspx_page_context);
    _jspx_th_aui_input_26.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_2);
    _jspx_th_aui_input_26.setName("language");
    _jspx_th_aui_input_26.setType("hidden");
    _jspx_th_aui_input_26.setValue(new String("json"));
    int _jspx_eval_aui_input_26 = _jspx_th_aui_input_26.doStartTag();
    if (_jspx_th_aui_input_26.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_26);
      return true;
    }
    _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_26);
    return false;
  }

  private boolean _jspx_meth_aui_input_27(javax.servlet.jsp.tagext.JspTag _jspx_th_c_when_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:input
    com.liferay.taglib.aui.InputTag _jspx_th_aui_input_27 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
    _jspx_th_aui_input_27.setPageContext(_jspx_page_context);
    _jspx_th_aui_input_27.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_2);
    _jspx_th_aui_input_27.setName("scriptContent");
    _jspx_th_aui_input_27.setType("hidden");
    int _jspx_eval_aui_input_27 = _jspx_th_aui_input_27.doStartTag();
    if (_jspx_th_aui_input_27.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_input_type_name_nobody.reuse(_jspx_th_aui_input_27);
      return true;
    }
    _jspx_tagPool_aui_input_type_name_nobody.reuse(_jspx_th_aui_input_27);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_3(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_3 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_3.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    int _jspx_eval_portlet_namespace_3 = _jspx_th_portlet_namespace_3.doStartTag();
    if (_jspx_th_portlet_namespace_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_3);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_3);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_4(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_4 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_4.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    int _jspx_eval_portlet_namespace_4 = _jspx_th_portlet_namespace_4.doStartTag();
    if (_jspx_th_portlet_namespace_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_4);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_4);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_5(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_5 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_5.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    int _jspx_eval_portlet_namespace_5 = _jspx_th_portlet_namespace_5.doStartTag();
    if (_jspx_th_portlet_namespace_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_5);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_5);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_6(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_6 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_6.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    int _jspx_eval_portlet_namespace_6 = _jspx_th_portlet_namespace_6.doStartTag();
    if (_jspx_th_portlet_namespace_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_6);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_6);
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

  private boolean _jspx_meth_portlet_namespace_12(javax.servlet.jsp.tagext.JspTag _jspx_th_c_when_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_12 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_12.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_12.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_2);
    int _jspx_eval_portlet_namespace_12 = _jspx_th_portlet_namespace_12.doStartTag();
    if (_jspx_th_portlet_namespace_12.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_12);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_12);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_13(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1ui_section_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_13 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_13.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_13.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_section_0);
    int _jspx_eval_portlet_namespace_13 = _jspx_th_portlet_namespace_13.doStartTag();
    if (_jspx_th_portlet_namespace_13.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_13);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_13);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_14(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1ui_section_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_14 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_14.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_14.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_section_0);
    int _jspx_eval_portlet_namespace_14 = _jspx_th_portlet_namespace_14.doStartTag();
    if (_jspx_th_portlet_namespace_14.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_14);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_14);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_15(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1ui_section_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_15 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_15.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_15.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_section_0);
    int _jspx_eval_portlet_namespace_15 = _jspx_th_portlet_namespace_15.doStartTag();
    if (_jspx_th_portlet_namespace_15.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_15);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_15);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_4(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1ui_section_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_4 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_4.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_section_0);
    _jspx_th_liferay$1ui_message_4.setKey("fields");
    int _jspx_eval_liferay$1ui_message_4 = _jspx_th_liferay$1ui_message_4.doStartTag();
    if (_jspx_th_liferay$1ui_message_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_4);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_4);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_5(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1ui_section_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_5 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_5.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_section_0);
    _jspx_th_liferay$1ui_message_5.setKey("settings");
    int _jspx_eval_liferay$1ui_message_5 = _jspx_th_liferay$1ui_message_5.doStartTag();
    if (_jspx_th_liferay$1ui_message_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_5);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_5);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_16(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1ui_section_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_16 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_16.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_16.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_section_1);
    int _jspx_eval_portlet_namespace_16 = _jspx_th_portlet_namespace_16.doStartTag();
    if (_jspx_th_portlet_namespace_16.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_16);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_16);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_17(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1ui_section_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_17 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_17.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_17.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_section_1);
    int _jspx_eval_portlet_namespace_17 = _jspx_th_portlet_namespace_17.doStartTag();
    if (_jspx_th_portlet_namespace_17.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_17);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_17);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_18(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_18 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_18.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_18.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_2);
    int _jspx_eval_portlet_namespace_18 = _jspx_th_portlet_namespace_18.doStartTag();
    if (_jspx_th_portlet_namespace_18.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_18);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_18);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_19(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_19 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_19.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_19.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_2);
    int _jspx_eval_portlet_namespace_19 = _jspx_th_portlet_namespace_19.doStartTag();
    if (_jspx_th_portlet_namespace_19.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_19);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_19);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_20(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_20 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_20.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_20.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_2);
    int _jspx_eval_portlet_namespace_20 = _jspx_th_portlet_namespace_20.doStartTag();
    if (_jspx_th_portlet_namespace_20.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_20);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_20);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_21(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_21 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_21.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_21.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_2);
    int _jspx_eval_portlet_namespace_21 = _jspx_th_portlet_namespace_21.doStartTag();
    if (_jspx_th_portlet_namespace_21.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_21);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_21);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_22(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_22 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_22.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_22.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_2);
    int _jspx_eval_portlet_namespace_22 = _jspx_th_portlet_namespace_22.doStartTag();
    if (_jspx_th_portlet_namespace_22.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_22);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_22);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_23(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_23 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_23.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_23.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_2);
    int _jspx_eval_portlet_namespace_23 = _jspx_th_portlet_namespace_23.doStartTag();
    if (_jspx_th_portlet_namespace_23.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_23);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_23);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_24(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_24 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_24.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_24.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_2);
    int _jspx_eval_portlet_namespace_24 = _jspx_th_portlet_namespace_24.doStartTag();
    if (_jspx_th_portlet_namespace_24.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_24);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_24);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_25(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_25 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_25.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_25.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_2);
    int _jspx_eval_portlet_namespace_25 = _jspx_th_portlet_namespace_25.doStartTag();
    if (_jspx_th_portlet_namespace_25.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_25);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_25);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_26(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_26 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_26.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_26.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_2);
    int _jspx_eval_portlet_namespace_26 = _jspx_th_portlet_namespace_26.doStartTag();
    if (_jspx_th_portlet_namespace_26.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_26);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_26);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_27(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_27 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_27.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_27.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_2);
    int _jspx_eval_portlet_namespace_27 = _jspx_th_portlet_namespace_27.doStartTag();
    if (_jspx_th_portlet_namespace_27.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_27);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_27);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_28(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_28 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_28.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_28.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_2);
    int _jspx_eval_portlet_namespace_28 = _jspx_th_portlet_namespace_28.doStartTag();
    if (_jspx_th_portlet_namespace_28.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_28);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_28);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_29(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_29 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_29.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_29.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_2);
    int _jspx_eval_portlet_namespace_29 = _jspx_th_portlet_namespace_29.doStartTag();
    if (_jspx_th_portlet_namespace_29.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_29);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_29);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_30(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_14, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_30 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_30.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_30.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_14);
    int _jspx_eval_portlet_namespace_30 = _jspx_th_portlet_namespace_30.doStartTag();
    if (_jspx_th_portlet_namespace_30.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_30);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_30);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_31(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_14, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_31 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_31.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_31.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_14);
    int _jspx_eval_portlet_namespace_31 = _jspx_th_portlet_namespace_31.doStartTag();
    if (_jspx_th_portlet_namespace_31.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_31);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_31);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_32(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_14, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_32 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_32.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_32.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_14);
    int _jspx_eval_portlet_namespace_32 = _jspx_th_portlet_namespace_32.doStartTag();
    if (_jspx_th_portlet_namespace_32.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_32);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_32);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_6(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_14, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_6 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_6.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_14);
    _jspx_th_liferay$1ui_message_6.setKey("search");
    int _jspx_eval_liferay$1ui_message_6 = _jspx_th_liferay$1ui_message_6.doStartTag();
    if (_jspx_th_liferay$1ui_message_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_6);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_6);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_33(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_14, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_33 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_33.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_33.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_14);
    int _jspx_eval_portlet_namespace_33 = _jspx_th_portlet_namespace_33.doStartTag();
    if (_jspx_th_portlet_namespace_33.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_33);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_33);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_34(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_14, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_34 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_34.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_34.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_14);
    int _jspx_eval_portlet_namespace_34 = _jspx_th_portlet_namespace_34.doStartTag();
    if (_jspx_th_portlet_namespace_34.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_34);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_34);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_35(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1ui_panel_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_35 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_35.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_35.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_panel_1);
    int _jspx_eval_portlet_namespace_35 = _jspx_th_portlet_namespace_35.doStartTag();
    if (_jspx_th_portlet_namespace_35.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_35);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_35);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_36(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1ui_panel_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_36 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_36.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_36.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_panel_1);
    int _jspx_eval_portlet_namespace_36 = _jspx_th_portlet_namespace_36.doStartTag();
    if (_jspx_th_portlet_namespace_36.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_36);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_36);
    return false;
  }

  private boolean _jspx_meth_aui_input_29(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1ui_panel_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:input
    com.liferay.taglib.aui.InputTag _jspx_th_aui_input_29 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_type_name_label_inlineLabel_nobody.get(com.liferay.taglib.aui.InputTag.class);
    _jspx_th_aui_input_29.setPageContext(_jspx_page_context);
    _jspx_th_aui_input_29.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_panel_1);
    _jspx_th_aui_input_29.setInlineLabel("left");
    _jspx_th_aui_input_29.setLabel("script-file");
    _jspx_th_aui_input_29.setName("script");
    _jspx_th_aui_input_29.setType("file");
    int _jspx_eval_aui_input_29 = _jspx_th_aui_input_29.doStartTag();
    if (_jspx_th_aui_input_29.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_input_type_name_label_inlineLabel_nobody.reuse(_jspx_th_aui_input_29);
      return true;
    }
    _jspx_tagPool_aui_input_type_name_label_inlineLabel_nobody.reuse(_jspx_th_aui_input_29);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_37(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_37 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_37.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_37.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_3);
    int _jspx_eval_portlet_namespace_37 = _jspx_th_portlet_namespace_37.doStartTag();
    if (_jspx_th_portlet_namespace_37.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_37);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_37);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_38(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_16, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_38 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_38.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_38.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_16);
    int _jspx_eval_portlet_namespace_38 = _jspx_th_portlet_namespace_38.doStartTag();
    if (_jspx_th_portlet_namespace_38.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_38);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_38);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_39(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_16, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_39 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_39.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_39.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_16);
    int _jspx_eval_portlet_namespace_39 = _jspx_th_portlet_namespace_39.doStartTag();
    if (_jspx_th_portlet_namespace_39.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_39);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_39);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_40(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_16, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_40 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_40.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_40.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_16);
    int _jspx_eval_portlet_namespace_40 = _jspx_th_portlet_namespace_40.doStartTag();
    if (_jspx_th_portlet_namespace_40.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_40);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_40);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_41(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_41 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_41.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_41.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_3);
    int _jspx_eval_portlet_namespace_41 = _jspx_th_portlet_namespace_41.doStartTag();
    if (_jspx_th_portlet_namespace_41.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_41);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_41);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_42(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_42 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_42.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_42.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_3);
    int _jspx_eval_portlet_namespace_42 = _jspx_th_portlet_namespace_42.doStartTag();
    if (_jspx_th_portlet_namespace_42.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_42);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_42);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_43(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_43 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_43.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_43.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_3);
    int _jspx_eval_portlet_namespace_43 = _jspx_th_portlet_namespace_43.doStartTag();
    if (_jspx_th_portlet_namespace_43.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_43);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_43);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_44(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_44 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_44.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_44.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_3);
    int _jspx_eval_portlet_namespace_44 = _jspx_th_portlet_namespace_44.doStartTag();
    if (_jspx_th_portlet_namespace_44.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_44);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_44);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_45(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_45 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_45.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_45.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_3);
    int _jspx_eval_portlet_namespace_45 = _jspx_th_portlet_namespace_45.doStartTag();
    if (_jspx_th_portlet_namespace_45.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_45);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_45);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_46(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_17, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_46 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_46.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_46.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_17);
    int _jspx_eval_portlet_namespace_46 = _jspx_th_portlet_namespace_46.doStartTag();
    if (_jspx_th_portlet_namespace_46.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_46);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_46);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_47(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_47 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_47.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_47.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_3);
    int _jspx_eval_portlet_namespace_47 = _jspx_th_portlet_namespace_47.doStartTag();
    if (_jspx_th_portlet_namespace_47.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_47);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_47);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_48(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_48 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_48.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_48.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_3);
    int _jspx_eval_portlet_namespace_48 = _jspx_th_portlet_namespace_48.doStartTag();
    if (_jspx_th_portlet_namespace_48.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_48);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_48);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_49(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_49 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_49.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_49.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_3);
    int _jspx_eval_portlet_namespace_49 = _jspx_th_portlet_namespace_49.doStartTag();
    if (_jspx_th_portlet_namespace_49.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_49);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_49);
    return false;
  }

  private boolean _jspx_meth_portlet_param_6(javax.servlet.jsp.tagext.JspTag _jspx_th_portlet_renderURL_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:param
    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_6 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
    _jspx_th_portlet_param_6.setPageContext(_jspx_page_context);
    _jspx_th_portlet_param_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_1);
    _jspx_th_portlet_param_6.setName("mvcPath");
    _jspx_th_portlet_param_6.setValue("/edit_template.jsp");
    int _jspx_eval_portlet_param_6 = _jspx_th_portlet_param_6.doStartTag();
    if (_jspx_th_portlet_param_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_6);
      return true;
    }
    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_6);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_50(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_50 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_50.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_50.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_3);
    int _jspx_eval_portlet_namespace_50 = _jspx_th_portlet_namespace_50.doStartTag();
    if (_jspx_th_portlet_namespace_50.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_50);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_50);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_51(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_51 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_51.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_51.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_4);
    int _jspx_eval_portlet_namespace_51 = _jspx_th_portlet_namespace_51.doStartTag();
    if (_jspx_th_portlet_namespace_51.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_51);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_51);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_52(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_52 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_52.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_52.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_4);
    int _jspx_eval_portlet_namespace_52 = _jspx_th_portlet_namespace_52.doStartTag();
    if (_jspx_th_portlet_namespace_52.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_52);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_52);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_53(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_53 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_53.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_53.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_4);
    int _jspx_eval_portlet_namespace_53 = _jspx_th_portlet_namespace_53.doStartTag();
    if (_jspx_th_portlet_namespace_53.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_53);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_53);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_54(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_54 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_54.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_54.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_4);
    int _jspx_eval_portlet_namespace_54 = _jspx_th_portlet_namespace_54.doStartTag();
    if (_jspx_th_portlet_namespace_54.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_54);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_54);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_55(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_5, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_55 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_55.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_55.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_5);
    int _jspx_eval_portlet_namespace_55 = _jspx_th_portlet_namespace_55.doStartTag();
    if (_jspx_th_portlet_namespace_55.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_55);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_55);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_56(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_5, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_56 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_56.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_56.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_5);
    int _jspx_eval_portlet_namespace_56 = _jspx_th_portlet_namespace_56.doStartTag();
    if (_jspx_th_portlet_namespace_56.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_56);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_56);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_57(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_5, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_57 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_57.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_57.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_5);
    int _jspx_eval_portlet_namespace_57 = _jspx_th_portlet_namespace_57.doStartTag();
    if (_jspx_th_portlet_namespace_57.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_57);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_57);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_58(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_5, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_58 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_58.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_58.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_5);
    int _jspx_eval_portlet_namespace_58 = _jspx_th_portlet_namespace_58.doStartTag();
    if (_jspx_th_portlet_namespace_58.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_58);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_58);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_59(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_5, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_59 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_59.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_59.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_5);
    int _jspx_eval_portlet_namespace_59 = _jspx_th_portlet_namespace_59.doStartTag();
    if (_jspx_th_portlet_namespace_59.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_59);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_59);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_60(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_5, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_60 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_60.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_60.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_5);
    int _jspx_eval_portlet_namespace_60 = _jspx_th_portlet_namespace_60.doStartTag();
    if (_jspx_th_portlet_namespace_60.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_60);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_60);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_61(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_5, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_61 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_61.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_61.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_5);
    int _jspx_eval_portlet_namespace_61 = _jspx_th_portlet_namespace_61.doStartTag();
    if (_jspx_th_portlet_namespace_61.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_61);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_61);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_62(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_6, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_62 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_62.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_62.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_6);
    int _jspx_eval_portlet_namespace_62 = _jspx_th_portlet_namespace_62.doStartTag();
    if (_jspx_th_portlet_namespace_62.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_62);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_62);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_63(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_6, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_63 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_63.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_63.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_6);
    int _jspx_eval_portlet_namespace_63 = _jspx_th_portlet_namespace_63.doStartTag();
    if (_jspx_th_portlet_namespace_63.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_63);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_63);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_64(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_6, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_64 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_64.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_64.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_6);
    int _jspx_eval_portlet_namespace_64 = _jspx_th_portlet_namespace_64.doStartTag();
    if (_jspx_th_portlet_namespace_64.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_64);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_64);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_65(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_6, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_65 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_65.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_65.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_6);
    int _jspx_eval_portlet_namespace_65 = _jspx_th_portlet_namespace_65.doStartTag();
    if (_jspx_th_portlet_namespace_65.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_65);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_65);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_66(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_6, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_66 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_66.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_66.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_6);
    int _jspx_eval_portlet_namespace_66 = _jspx_th_portlet_namespace_66.doStartTag();
    if (_jspx_th_portlet_namespace_66.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_66);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_66);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_67(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_6, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_67 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_67.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_67.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_6);
    int _jspx_eval_portlet_namespace_67 = _jspx_th_portlet_namespace_67.doStartTag();
    if (_jspx_th_portlet_namespace_67.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_67);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_67);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_68(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_6, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_68 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_68.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_68.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_6);
    int _jspx_eval_portlet_namespace_68 = _jspx_th_portlet_namespace_68.doStartTag();
    if (_jspx_th_portlet_namespace_68.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_68);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_68);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_69(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_6, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_69 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_69.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_69.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_6);
    int _jspx_eval_portlet_namespace_69 = _jspx_th_portlet_namespace_69.doStartTag();
    if (_jspx_th_portlet_namespace_69.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_69);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_69);
    return false;
  }
}
