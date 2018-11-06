package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.language.UnicodeLanguageUtil;
import com.liferay.portal.kernel.portlet.PortalPreferences;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.HitsOpenSearchImpl;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.OpenSearch;
import com.liferay.portal.kernel.search.facet.Facet;
import com.liferay.portal.kernel.search.facet.collector.FacetCollector;
import com.liferay.portal.kernel.search.facet.collector.TermCollector;
import com.liferay.portal.kernel.search.facet.config.FacetConfiguration;
import com.liferay.portal.kernel.search.highlight.HighlightUtil;
import com.liferay.portal.kernel.security.permission.ResourceActionsUtil;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.servlet.BrowserSnifferUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.KeyValuePair;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Tuple;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.search.web.constants.SearchPortletKeys;
import com.liferay.portal.search.web.constants.SearchPortletParameterNames;
import com.liferay.portal.search.web.facet.SearchFacet;
import com.liferay.portal.search.web.facet.util.comparator.SearchFacetComparator;
import com.liferay.portal.search.web.internal.display.context.SearchDisplayContext;
import com.liferay.portal.search.web.internal.display.context.SearchDisplayContextFactoryUtil;
import com.liferay.portal.search.web.internal.facet.AssetEntriesSearchFacet;
import com.liferay.portal.search.web.internal.result.display.context.SearchResultFieldDisplayContext;
import com.liferay.portal.search.web.internal.search.suggest.KeywordsSuggestionHolder;
import com.liferay.taglib.aui.AUIUtil;
import com.liferay.taglib.servlet.PipingServletResponse;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.portlet.PortletMode;
import javax.portlet.PortletURL;
import javax.portlet.WindowState;

public final class search_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(6);
    _jspx_dependants.add("/init.jsp");
    _jspx_dependants.add("/init-ext.jsp");
    _jspx_dependants.add("/main_search.jspf");
    _jspx_dependants.add("/main_search_suggest.jspf");
    _jspx_dependants.add("/main_search_result_form.jspf");
    _jspx_dependants.add("/open_search.jspf");
  }

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_form_onSubmit_name_method_action;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_message_key_arguments_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_param_value_name_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_a_href;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_if_test;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_script_sandbox;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_script;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1asset_asset$1tags$1summary_portletURL_paramName_classPK_className_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container$1row_stringKey_modelVar_keyProperty_escapedModel_className;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_a_onClick_href;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1portlet_renderURL_varImpl;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container$1row_modelVar_className;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_choose;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_resourceURL_var;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1frontend_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_script_use;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1asset_asset$1categories$1summary_portletURL_paramName_classPK_className_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1paginator_type_searchContainer_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_user$1portrait_userId_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_when_test;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_namespace_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1iterator_paginate_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_a_onclick_href;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1portlet_renderURLParams_varImpl_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_fieldset_id;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_field$1wrapper_inlineField;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_row_cssClass;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_icon_message_image_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_message_key_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_input_value_type_name_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container$1results_results_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_input_value_title_size_name_label_inlineField_autoFocus_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_renderURL_var;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container$1column$1text_colspan;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_otherwise;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_button_value_onClick_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_button_value_type_onClick_icon_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1iterator_type_markupView_displayStyle_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1theme_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container$1column$1text;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_col_span_first_cssClass;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container_total_searchContainer;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container_searchContainer_id;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_panel_title_persistState_id_extended_cssClass_collapsible;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_col_span_id;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_button$1row;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_aui_form_onSubmit_name_method_action = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_message_key_arguments_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_param_value_name_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_a_href = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_if_test = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_script_sandbox = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_script = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1asset_asset$1tags$1summary_portletURL_paramName_classPK_className_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container$1row_stringKey_modelVar_keyProperty_escapedModel_className = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_a_onClick_href = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1portlet_renderURL_varImpl = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container$1row_modelVar_className = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_choose = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_resourceURL_var = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1frontend_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_script_use = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1asset_asset$1categories$1summary_portletURL_paramName_classPK_className_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1paginator_type_searchContainer_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_user$1portrait_userId_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_when_test = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_namespace_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1iterator_paginate_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_a_onclick_href = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1portlet_renderURLParams_varImpl_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_fieldset_id = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_field$1wrapper_inlineField = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_row_cssClass = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_icon_message_image_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_message_key_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_input_value_type_name_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container$1results_results_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_input_value_title_size_name_label_inlineField_autoFocus_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_renderURL_var = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_colspan = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_otherwise = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_button_value_onClick_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_button_value_type_onClick_icon_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1iterator_type_markupView_displayStyle_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1theme_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_col_span_first_cssClass = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container_total_searchContainer = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container_searchContainer_id = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_panel_title_persistState_id_extended_cssClass_collapsible = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_col_span_id = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_button$1row = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_aui_form_onSubmit_name_method_action.release();
    _jspx_tagPool_liferay$1ui_message_key_arguments_nobody.release();
    _jspx_tagPool_portlet_param_value_name_nobody.release();
    _jspx_tagPool_aui_a_href.release();
    _jspx_tagPool_c_if_test.release();
    _jspx_tagPool_aui_script_sandbox.release();
    _jspx_tagPool_aui_script.release();
    _jspx_tagPool_liferay$1asset_asset$1tags$1summary_portletURL_paramName_classPK_className_nobody.release();
    _jspx_tagPool_liferay$1ui_search$1container$1row_stringKey_modelVar_keyProperty_escapedModel_className.release();
    _jspx_tagPool_aui_a_onClick_href.release();
    _jspx_tagPool_liferay$1portlet_renderURL_varImpl.release();
    _jspx_tagPool_liferay$1ui_search$1container$1row_modelVar_className.release();
    _jspx_tagPool_c_choose.release();
    _jspx_tagPool_portlet_resourceURL_var.release();
    _jspx_tagPool_liferay$1frontend_defineObjects_nobody.release();
    _jspx_tagPool_aui_script_use.release();
    _jspx_tagPool_liferay$1asset_asset$1categories$1summary_portletURL_paramName_classPK_className_nobody.release();
    _jspx_tagPool_liferay$1ui_search$1paginator_type_searchContainer_nobody.release();
    _jspx_tagPool_liferay$1ui_user$1portrait_userId_nobody.release();
    _jspx_tagPool_c_when_test.release();
    _jspx_tagPool_portlet_namespace_nobody.release();
    _jspx_tagPool_liferay$1ui_search$1iterator_paginate_nobody.release();
    _jspx_tagPool_aui_a_onclick_href.release();
    _jspx_tagPool_liferay$1portlet_renderURLParams_varImpl_nobody.release();
    _jspx_tagPool_aui_fieldset_id.release();
    _jspx_tagPool_aui_field$1wrapper_inlineField.release();
    _jspx_tagPool_aui_row_cssClass.release();
    _jspx_tagPool_liferay$1ui_icon_message_image_nobody.release();
    _jspx_tagPool_liferay$1ui_message_key_nobody.release();
    _jspx_tagPool_aui_input_value_type_name_nobody.release();
    _jspx_tagPool_liferay$1ui_search$1container$1results_results_nobody.release();
    _jspx_tagPool_aui_input_value_title_size_name_label_inlineField_autoFocus_nobody.release();
    _jspx_tagPool_portlet_renderURL_var.release();
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_colspan.release();
    _jspx_tagPool_c_otherwise.release();
    _jspx_tagPool_aui_button_value_onClick_nobody.release();
    _jspx_tagPool_portlet_defineObjects_nobody.release();
    _jspx_tagPool_aui_button_value_type_onClick_icon_nobody.release();
    _jspx_tagPool_liferay$1ui_search$1iterator_type_markupView_displayStyle_nobody.release();
    _jspx_tagPool_liferay$1theme_defineObjects_nobody.release();
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text.release();
    _jspx_tagPool_aui_col_span_first_cssClass.release();
    _jspx_tagPool_liferay$1ui_search$1container_total_searchContainer.release();
    _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody.release();
    _jspx_tagPool_liferay$1ui_search$1container_searchContainer_id.release();
    _jspx_tagPool_liferay$1ui_panel_title_persistState_id_extended_cssClass_collapsible.release();
    _jspx_tagPool_aui_col_span_id.release();
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

PortalPreferences portalPreferences = PortletPreferencesFactoryUtil.getPortalPreferences(request);

SearchDisplayContext searchDisplayContext = (SearchDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

if (searchDisplayContext == null) {
	searchDisplayContext = SearchDisplayContextFactoryUtil.create(renderRequest, renderResponse, portletPreferences);
}

      out.write('\n');
      out.write('\n');
      out.write('\n');
      out.write('\n');

String redirect = ParamUtil.getString(request, "redirect");

if (Validator.isNotNull(redirect)) {
	portletDisplay.setURLBack(redirect);
}

long groupId = ParamUtil.getLong(request, SearchPortletParameterNames.GROUP_ID);

String format = ParamUtil.getString(request, SearchPortletParameterNames.FORMAT);

      out.write('\n');
      out.write('\n');
      //  liferay-portlet:renderURL
      com.liferay.taglib.portlet.RenderURLTag _jspx_th_liferay$1portlet_renderURL_0 = (com.liferay.taglib.portlet.RenderURLTag) _jspx_tagPool_liferay$1portlet_renderURL_varImpl.get(com.liferay.taglib.portlet.RenderURLTag.class);
      _jspx_th_liferay$1portlet_renderURL_0.setPageContext(_jspx_page_context);
      _jspx_th_liferay$1portlet_renderURL_0.setParent(null);
      _jspx_th_liferay$1portlet_renderURL_0.setVarImpl("searchURL");
      int _jspx_eval_liferay$1portlet_renderURL_0 = _jspx_th_liferay$1portlet_renderURL_0.doStartTag();
      if (_jspx_eval_liferay$1portlet_renderURL_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        out.write('\n');
        out.write('	');
        if (_jspx_meth_portlet_param_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1portlet_renderURL_0, _jspx_page_context))
          return;
        out.write('\n');
      }
      if (_jspx_th_liferay$1portlet_renderURL_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_liferay$1portlet_renderURL_varImpl.reuse(_jspx_th_liferay$1portlet_renderURL_0);
        return;
      }
      _jspx_tagPool_liferay$1portlet_renderURL_varImpl.reuse(_jspx_th_liferay$1portlet_renderURL_0);
      com.liferay.portal.kernel.portlet.LiferayPortletURL searchURL = null;
      searchURL = (com.liferay.portal.kernel.portlet.LiferayPortletURL) _jspx_page_context.findAttribute("searchURL");
      out.write('\n');
      out.write('\n');
      //  aui:form
      com.liferay.taglib.aui.FormTag _jspx_th_aui_form_0 = (com.liferay.taglib.aui.FormTag) _jspx_tagPool_aui_form_onSubmit_name_method_action.get(com.liferay.taglib.aui.FormTag.class);
      _jspx_th_aui_form_0.setPageContext(_jspx_page_context);
      _jspx_th_aui_form_0.setParent(null);
      _jspx_th_aui_form_0.setAction( searchURL );
      _jspx_th_aui_form_0.setMethod("get");
      _jspx_th_aui_form_0.setName("fm");
      _jspx_th_aui_form_0.setOnSubmit("event.preventDefault();");
      int _jspx_eval_aui_form_0 = _jspx_th_aui_form_0.doStartTag();
      if (_jspx_eval_aui_form_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        out.write('\n');
        out.write('	');
        if (_jspx_meth_liferay$1portlet_renderURLParams_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_form_0, _jspx_page_context))
          return;
        out.write('\n');
        out.write('	');
        //  aui:input
        com.liferay.taglib.aui.InputTag _jspx_th_aui_input_0 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
        _jspx_th_aui_input_0.setPageContext(_jspx_page_context);
        _jspx_th_aui_input_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
        _jspx_th_aui_input_0.setName( SearchContainer.DEFAULT_CUR_PARAM );
        _jspx_th_aui_input_0.setType("hidden");
        _jspx_th_aui_input_0.setValue( ParamUtil.getInteger(request, SearchContainer.DEFAULT_CUR_PARAM, SearchContainer.DEFAULT_CUR) );
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
        _jspx_th_aui_input_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
        _jspx_th_aui_input_1.setName("format");
        _jspx_th_aui_input_1.setType("hidden");
        _jspx_th_aui_input_1.setValue( format );
        int _jspx_eval_aui_input_1 = _jspx_th_aui_input_1.doStartTag();
        if (_jspx_th_aui_input_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_1);
          return;
        }
        _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_1);
        out.write("\n\n\t");
        //  aui:fieldset
        com.liferay.taglib.aui.FieldsetTag _jspx_th_aui_fieldset_0 = (com.liferay.taglib.aui.FieldsetTag) _jspx_tagPool_aui_fieldset_id.get(com.liferay.taglib.aui.FieldsetTag.class);
        _jspx_th_aui_fieldset_0.setPageContext(_jspx_page_context);
        _jspx_th_aui_fieldset_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
        _jspx_th_aui_fieldset_0.setId( renderResponse.getNamespace() + "searchContainer" );
        int _jspx_eval_aui_fieldset_0 = _jspx_th_aui_fieldset_0.doStartTag();
        if (_jspx_eval_aui_fieldset_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          out.write("\n\t\t");
          //  aui:input
          com.liferay.taglib.aui.InputTag _jspx_th_aui_input_2 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_title_size_name_label_inlineField_autoFocus_nobody.get(com.liferay.taglib.aui.InputTag.class);
          _jspx_th_aui_input_2.setPageContext(_jspx_page_context);
          _jspx_th_aui_input_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_0);
          _jspx_th_aui_input_2.setAutoFocus( windowState.equals(WindowState.MAXIMIZED) );
          _jspx_th_aui_input_2.setInlineField( true );
          _jspx_th_aui_input_2.setLabel("");
          _jspx_th_aui_input_2.setName("keywords");
          _jspx_th_aui_input_2.setDynamicAttribute(null, "size", new String("30"));
          _jspx_th_aui_input_2.setTitle("search");
          _jspx_th_aui_input_2.setValue( HtmlUtil.escape(searchDisplayContext.getKeywords()) );
          int _jspx_eval_aui_input_2 = _jspx_th_aui_input_2.doStartTag();
          if (_jspx_th_aui_input_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_aui_input_value_title_size_name_label_inlineField_autoFocus_nobody.reuse(_jspx_th_aui_input_2);
            return;
          }
          _jspx_tagPool_aui_input_value_title_size_name_label_inlineField_autoFocus_nobody.reuse(_jspx_th_aui_input_2);
          out.write("\n\t\t");
          //  aui:input
          com.liferay.taglib.aui.InputTag _jspx_th_aui_input_3 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
          _jspx_th_aui_input_3.setPageContext(_jspx_page_context);
          _jspx_th_aui_input_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_0);
          _jspx_th_aui_input_3.setName("scope");
          _jspx_th_aui_input_3.setType("hidden");
          _jspx_th_aui_input_3.setValue( searchDisplayContext.getSearchScopeParameterString() );
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
          _jspx_th_aui_input_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_0);
          _jspx_th_aui_input_4.setName("useAdvancedSearchSyntax");
          _jspx_th_aui_input_4.setType("hidden");
          _jspx_th_aui_input_4.setValue( searchDisplayContext.isUseAdvancedSearchSyntax() );
          int _jspx_eval_aui_input_4 = _jspx_th_aui_input_4.doStartTag();
          if (_jspx_th_aui_input_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_4);
            return;
          }
          _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_4);
          out.write("\n\n\t\t");
          //  aui:field-wrapper
          com.liferay.taglib.aui.FieldWrapperTag _jspx_th_aui_field$1wrapper_0 = (com.liferay.taglib.aui.FieldWrapperTag) _jspx_tagPool_aui_field$1wrapper_inlineField.get(com.liferay.taglib.aui.FieldWrapperTag.class);
          _jspx_th_aui_field$1wrapper_0.setPageContext(_jspx_page_context);
          _jspx_th_aui_field$1wrapper_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_0);
          _jspx_th_aui_field$1wrapper_0.setInlineField( true );
          int _jspx_eval_aui_field$1wrapper_0 = _jspx_th_aui_field$1wrapper_0.doStartTag();
          if (_jspx_eval_aui_field$1wrapper_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t\t");
            //  aui:button
            com.liferay.taglib.aui.ButtonTag _jspx_th_aui_button_0 = (com.liferay.taglib.aui.ButtonTag) _jspx_tagPool_aui_button_value_type_onClick_icon_nobody.get(com.liferay.taglib.aui.ButtonTag.class);
            _jspx_th_aui_button_0.setPageContext(_jspx_page_context);
            _jspx_th_aui_button_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_field$1wrapper_0);
            _jspx_th_aui_button_0.setIcon("icon-search");
            _jspx_th_aui_button_0.setOnClick( renderResponse.getNamespace() + "search();" );
            _jspx_th_aui_button_0.setType("submit");
            _jspx_th_aui_button_0.setValue("search");
            int _jspx_eval_aui_button_0 = _jspx_th_aui_button_0.doStartTag();
            if (_jspx_th_aui_button_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_aui_button_value_type_onClick_icon_nobody.reuse(_jspx_th_aui_button_0);
              return;
            }
            _jspx_tagPool_aui_button_value_type_onClick_icon_nobody.reuse(_jspx_th_aui_button_0);
            out.write("\n\t\t");
          }
          if (_jspx_th_aui_field$1wrapper_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_aui_field$1wrapper_inlineField.reuse(_jspx_th_aui_field$1wrapper_0);
            return;
          }
          _jspx_tagPool_aui_field$1wrapper_inlineField.reuse(_jspx_th_aui_field$1wrapper_0);
          out.write('\n');
          out.write('	');
        }
        if (_jspx_th_aui_fieldset_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_aui_fieldset_id.reuse(_jspx_th_aui_fieldset_0);
          return;
        }
        _jspx_tagPool_aui_fieldset_id.reuse(_jspx_th_aui_fieldset_0);
        out.write("\n\n\t");
        out.write('\n');
        out.write('\n');

Hits hits = searchDisplayContext.getHits();

        out.write('\n');
        out.write('\n');
        //  aui:row
        com.liferay.taglib.aui.RowTag _jspx_th_aui_row_0 = (com.liferay.taglib.aui.RowTag) _jspx_tagPool_aui_row_cssClass.get(com.liferay.taglib.aui.RowTag.class);
        _jspx_th_aui_row_0.setPageContext(_jspx_page_context);
        _jspx_th_aui_row_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
        _jspx_th_aui_row_0.setCssClass( "search-layout" + (searchDisplayContext.isShowMenu() ? " menu-column" : StringPool.BLANK) );
        int _jspx_eval_aui_row_0 = _jspx_th_aui_row_0.doStartTag();
        if (_jspx_eval_aui_row_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          out.write('\n');
          out.write('	');
          //  c:if
          com.liferay.taglib.core.IfTag _jspx_th_c_if_0 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
          _jspx_th_c_if_0.setPageContext(_jspx_page_context);
          _jspx_th_c_if_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_row_0);
          _jspx_th_c_if_0.setTest( searchDisplayContext.isShowMenu() );
          int _jspx_eval_c_if_0 = _jspx_th_c_if_0.doStartTag();
          if (_jspx_eval_c_if_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t");
            //  aui:col
            com.liferay.taglib.aui.ColTag _jspx_th_aui_col_0 = (com.liferay.taglib.aui.ColTag) _jspx_tagPool_aui_col_span_id.get(com.liferay.taglib.aui.ColTag.class);
            _jspx_th_aui_col_0.setPageContext(_jspx_page_context);
            _jspx_th_aui_col_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_0);
            _jspx_th_aui_col_0.setId("facetNavigation");
            _jspx_th_aui_col_0.setSpan( 3 );
            int _jspx_eval_aui_col_0 = _jspx_th_aui_col_0.doStartTag();
            if (_jspx_eval_aui_col_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\n\t\t\t");

			for (SearchFacet searchFacet : ListUtil.sort(searchDisplayContext.getEnabledSearchFacets(), SearchFacetComparator.INSTANCE)) {
				if (searchFacet.isStatic()) {
					continue;
				}

				request.setAttribute("search.jsp-facet", searchFacet.getFacet());

				searchFacet.includeView(request, PipingServletResponse.createPipingServletResponse(pageContext));
			}
			
              out.write("\n\n\t\t\t");
              //  aui:script
              com.liferay.taglib.aui.ScriptTag _jspx_th_aui_script_0 = (com.liferay.taglib.aui.ScriptTag) _jspx_tagPool_aui_script_sandbox.get(com.liferay.taglib.aui.ScriptTag.class);
              _jspx_th_aui_script_0.setPageContext(_jspx_page_context);
              _jspx_th_aui_script_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_col_0);
              _jspx_th_aui_script_0.setSandbox( true );
              int _jspx_eval_aui_script_0 = _jspx_th_aui_script_0.doStartTag();
              if (_jspx_eval_aui_script_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                if (_jspx_eval_aui_script_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                  out = _jspx_page_context.pushBody();
                  _jspx_th_aui_script_0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                  _jspx_th_aui_script_0.doInitBody();
                }
                do {
                  out.write("\n\t\t\t\t$('#");
                  if (_jspx_meth_portlet_namespace_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_0, _jspx_page_context))
                    return;
                  out.write("facetNavigation').on(\n\t\t\t\t\t'click',\n\t\t\t\t\t'.facet-value a',\n\t\t\t\t\tfunction(event) {\n\t\t\t\t\t\tevent.preventDefault();\n\n\t\t\t\t\t\tvar term = $(event.currentTarget);\n\n\t\t\t\t\t\tvar facetValue = term.parentsUntil('.search-facet', '.facet-value');\n\n\t\t\t\t\t\tif (!facetValue.hasClass('active')) {\n\t\t\t\t\t\t\tfacetValue.addClass('active');\n\n\t\t\t\t\t\t\tfacetValue.siblings().removeClass('active');\n\n\t\t\t\t\t\t\tvar searchFacet = facetValue.parentsUntil('.search-layout', '.search-facet');\n\n\t\t\t\t\t\t\tvar form = $(document.");
                  if (_jspx_meth_portlet_namespace_1((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_0, _jspx_page_context))
                    return;
                  out.write("fm);\n\n\t\t\t\t\t\t\tvar field = form.fm(searchFacet.data('facetfieldname'));\n\t\t\t\t\t\t\tvar fieldSelection = form.fm(searchFacet.data('facetfieldname') + 'selection');\n\n\t\t\t\t\t\t\tif (field.length) {\n\t\t\t\t\t\t\t\tfield.val(term.data('value'));\n\n\t\t\t\t\t\t\t\tif (fieldSelection.length) {\n\t\t\t\t\t\t\t\t\tfieldSelection.val(term.data('selection'));\n\t\t\t\t\t\t\t\t}\n\n\t\t\t\t\t\t\t\tform.fm('");
                  out.print( SearchContainer.DEFAULT_CUR_PARAM );
                  out.write("').val(1);\n\n\t\t\t\t\t\t\t\tsubmitForm(form);\n\t\t\t\t\t\t\t}\n\t\t\t\t\t\t}\n\t\t\t\t\t}\n\t\t\t\t);\n\t\t\t");
                  int evalDoAfterBody = _jspx_th_aui_script_0.doAfterBody();
                  if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                    break;
                } while (true);
                if (_jspx_eval_aui_script_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                  out = _jspx_page_context.popBody();
              }
              if (_jspx_th_aui_script_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_aui_script_sandbox.reuse(_jspx_th_aui_script_0);
                return;
              }
              _jspx_tagPool_aui_script_sandbox.reuse(_jspx_th_aui_script_0);
              out.write("\n\t\t");
            }
            if (_jspx_th_aui_col_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_aui_col_span_id.reuse(_jspx_th_aui_col_0);
              return;
            }
            _jspx_tagPool_aui_col_span_id.reuse(_jspx_th_aui_col_0);
            out.write('\n');
            out.write('	');
          }
          if (_jspx_th_c_if_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
            return;
          }
          _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
          out.write("\n\n\t");
          //  aui:col
          com.liferay.taglib.aui.ColTag _jspx_th_aui_col_1 = (com.liferay.taglib.aui.ColTag) _jspx_tagPool_aui_col_span_first_cssClass.get(com.liferay.taglib.aui.ColTag.class);
          _jspx_th_aui_col_1.setPageContext(_jspx_page_context);
          _jspx_th_aui_col_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_row_0);
          _jspx_th_aui_col_1.setCssClass("result");
          _jspx_th_aui_col_1.setDynamicAttribute(null, "first",  !searchDisplayContext.isShowMenu() );
          _jspx_th_aui_col_1.setSpan( 9 );
          int _jspx_eval_aui_col_1 = _jspx_th_aui_col_1.doStartTag();
          if (_jspx_eval_aui_col_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t");
            out.write("\n\n<div class=\"search-suggested-spelling\">\n\t");
            //  c:if
            com.liferay.taglib.core.IfTag _jspx_th_c_if_1 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
            _jspx_th_c_if_1.setPageContext(_jspx_page_context);
            _jspx_th_c_if_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_col_1);
            _jspx_th_c_if_1.setTest( searchDisplayContext.isCollatedSpellCheckResultEnabled() && !Validator.isBlank(hits.getCollatedSpellCheckResult()) && !Objects.equals(hits.getCollatedSpellCheckResult(), searchDisplayContext.getKeywords()) );
            int _jspx_eval_c_if_1 = _jspx_th_c_if_1.doStartTag();
            if (_jspx_eval_c_if_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\t\t<ul class=\"list-inline suggested-keywords\">\n\t\t\t<li class=\"label label-default\">\n\t\t\t\t");
              if (_jspx_meth_liferay$1ui_message_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_1, _jspx_page_context))
                return;
              out.write(":\n\t\t\t</li>\n\t\t\t<li>\n\n\t\t\t\t");

				String taglibSearchCollatedKeywords = renderResponse.getNamespace() + "searchKeywords('" + HtmlUtil.escapeJS(hits.getCollatedSpellCheckResult()) + "')";
				
              out.write("\n\n\t\t\t\t");
              //  aui:a
              com.liferay.taglib.aui.ATag _jspx_th_aui_a_0 = (com.liferay.taglib.aui.ATag) _jspx_tagPool_aui_a_onclick_href.get(com.liferay.taglib.aui.ATag.class);
              _jspx_th_aui_a_0.setPageContext(_jspx_page_context);
              _jspx_th_aui_a_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_1);
              _jspx_th_aui_a_0.setHref("javascript:;");
              _jspx_th_aui_a_0.setDynamicAttribute(null, "onclick",  taglibSearchCollatedKeywords );
              int _jspx_eval_aui_a_0 = _jspx_th_aui_a_0.doStartTag();
              if (_jspx_eval_aui_a_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\n\t\t\t\t\t");

					KeywordsSuggestionHolder keywordsSuggestionHolder = new KeywordsSuggestionHolder(hits.getCollatedSpellCheckResult(), searchDisplayContext.getKeywords());

					for (String suggestedKeyword : keywordsSuggestionHolder.getSuggestedKeywords()) {
					
                out.write("\n\n\t\t\t\t\t\t<span class=\"");
                out.print( keywordsSuggestionHolder.hasChanged(suggestedKeyword) ? "changed-keyword" : "unchanged-keyword" );
                out.write("\">\n\t\t\t\t\t\t\t");
                out.print( HtmlUtil.escape(suggestedKeyword) );
                out.write("\n\t\t\t\t\t\t</span>\n\n\t\t\t\t\t");

					}
					
                out.write("\n\n\t\t\t\t");
              }
              if (_jspx_th_aui_a_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_aui_a_onclick_href.reuse(_jspx_th_aui_a_0);
                return;
              }
              _jspx_tagPool_aui_a_onclick_href.reuse(_jspx_th_aui_a_0);
              out.write("\n\t\t\t</li>\n\t\t</ul>\n\t");
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
            _jspx_th_c_if_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_col_1);
            _jspx_th_c_if_2.setTest( searchDisplayContext.isQuerySuggestionsEnabled() && ArrayUtil.isNotEmpty(hits.getQuerySuggestions()) );
            int _jspx_eval_c_if_2 = _jspx_th_c_if_2.doStartTag();
            if (_jspx_eval_c_if_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\t\t<ul class=\"list-inline related-queries\">\n\t\t\t<li class=\"label label-default\">\n\t\t\t\t");
              if (_jspx_meth_liferay$1ui_message_1((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_2, _jspx_page_context))
                return;
              out.write(":\n\t\t\t</li>\n\n\t\t\t");

			for (String querySuggestion : hits.getQuerySuggestions()) {
			
              out.write("\n\n\t\t\t\t");
              //  c:if
              com.liferay.taglib.core.IfTag _jspx_th_c_if_3 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
              _jspx_th_c_if_3.setPageContext(_jspx_page_context);
              _jspx_th_c_if_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_2);
              _jspx_th_c_if_3.setTest( !Validator.isBlank(querySuggestion) && !querySuggestion.equals(searchDisplayContext.getKeywords()) );
              int _jspx_eval_c_if_3 = _jspx_th_c_if_3.doStartTag();
              if (_jspx_eval_c_if_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\t\t\t\t\t<li>\n\n\t\t\t\t\t\t");

						String taglibOnClick = renderResponse.getNamespace() + "searchKeywords('" + HtmlUtil.escapeJS(querySuggestion) + "')";
						
                out.write("\n\n\t\t\t\t\t\t");
                //  aui:a
                com.liferay.taglib.aui.ATag _jspx_th_aui_a_1 = (com.liferay.taglib.aui.ATag) _jspx_tagPool_aui_a_onClick_href.get(com.liferay.taglib.aui.ATag.class);
                _jspx_th_aui_a_1.setPageContext(_jspx_page_context);
                _jspx_th_aui_a_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_3);
                _jspx_th_aui_a_1.setHref("javascript:;");
                _jspx_th_aui_a_1.setOnClick( taglibOnClick );
                int _jspx_eval_aui_a_1 = _jspx_th_aui_a_1.doStartTag();
                if (_jspx_eval_aui_a_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  out.write("\n\n\t\t\t\t\t\t\t");

							KeywordsSuggestionHolder keywordsSuggestionHolder = new KeywordsSuggestionHolder(querySuggestion, searchDisplayContext.getKeywords());

							for (String suggestedKeyword : keywordsSuggestionHolder.getSuggestedKeywords()) {
							
                  out.write("\n\n\t\t\t\t\t\t\t\t<span class=\"");
                  out.print( keywordsSuggestionHolder.hasChanged(suggestedKeyword) ? "changed-keyword" : "unchanged-keyword" );
                  out.write("\">\n\t\t\t\t\t\t\t\t\t");
                  out.print( HtmlUtil.escape(suggestedKeyword) );
                  out.write("\n\t\t\t\t\t\t\t\t</span>\n\n\t\t\t\t\t\t\t");

							}
							
                  out.write("\n\n\t\t\t\t\t\t");
                }
                if (_jspx_th_aui_a_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_aui_a_onClick_href.reuse(_jspx_th_aui_a_1);
                  return;
                }
                _jspx_tagPool_aui_a_onClick_href.reuse(_jspx_th_aui_a_1);
                out.write("\n\t\t\t\t\t</li>\n\t\t\t\t");
              }
              if (_jspx_th_c_if_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_3);
                return;
              }
              _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_3);
              out.write("\n\n\t\t\t");

			}
			
              out.write("\n\n\t\t</ul>\n\t");
            }
            if (_jspx_th_c_if_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_2);
              return;
            }
            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_2);
            out.write("\n</div>\n\n");
            if (_jspx_meth_aui_script_1((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_col_1, _jspx_page_context))
              return;
            out.write("\n\n\t\t");
            //  liferay-ui:search-container
            com.liferay.taglib.ui.SearchContainerTag _jspx_th_liferay$1ui_search$1container_0 = (com.liferay.taglib.ui.SearchContainerTag) _jspx_tagPool_liferay$1ui_search$1container_searchContainer_id.get(com.liferay.taglib.ui.SearchContainerTag.class);
            _jspx_th_liferay$1ui_search$1container_0.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1ui_search$1container_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_col_1);
            _jspx_th_liferay$1ui_search$1container_0.setId("search");
            _jspx_th_liferay$1ui_search$1container_0.setSearchContainer( searchDisplayContext.getSearchContainer() );
            int _jspx_eval_liferay$1ui_search$1container_0 = _jspx_th_liferay$1ui_search$1container_0.doStartTag();
            if (_jspx_eval_liferay$1ui_search$1container_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              java.lang.Integer total = null;
              com.liferay.portal.kernel.dao.search.SearchContainer searchContainer = null;
              total = (java.lang.Integer) _jspx_page_context.findAttribute("total");
              searchContainer = (com.liferay.portal.kernel.dao.search.SearchContainer) _jspx_page_context.findAttribute("searchContainer");
              out.write("\n\t\t\t");
              //  liferay-ui:search-container-row
              com.liferay.taglib.ui.SearchContainerRowTag _jspx_th_liferay$1ui_search$1container$1row_0 = (com.liferay.taglib.ui.SearchContainerRowTag) _jspx_tagPool_liferay$1ui_search$1container$1row_stringKey_modelVar_keyProperty_escapedModel_className.get(com.liferay.taglib.ui.SearchContainerRowTag.class);
              _jspx_th_liferay$1ui_search$1container$1row_0.setPageContext(_jspx_page_context);
              _jspx_th_liferay$1ui_search$1container$1row_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container_0);
              _jspx_th_liferay$1ui_search$1container$1row_0.setClassName("com.liferay.portal.kernel.search.Document");
              _jspx_th_liferay$1ui_search$1container$1row_0.setEscapedModel( false );
              _jspx_th_liferay$1ui_search$1container$1row_0.setKeyProperty("UID");
              _jspx_th_liferay$1ui_search$1container$1row_0.setModelVar("document");
              _jspx_th_liferay$1ui_search$1container$1row_0.setStringKey( true );
              int _jspx_eval_liferay$1ui_search$1container$1row_0 = _jspx_th_liferay$1ui_search$1container$1row_0.doStartTag();
              if (_jspx_eval_liferay$1ui_search$1container$1row_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                java.lang.Integer index = null;
                com.liferay.portal.kernel.search.Document document = null;
                com.liferay.portal.kernel.dao.search.ResultRow row = null;
                if (_jspx_eval_liferay$1ui_search$1container$1row_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                  out = _jspx_page_context.pushBody();
                  _jspx_th_liferay$1ui_search$1container$1row_0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                  _jspx_th_liferay$1ui_search$1container$1row_0.doInitBody();
                }
                index = (java.lang.Integer) _jspx_page_context.findAttribute("index");
                document = (com.liferay.portal.kernel.search.Document) _jspx_page_context.findAttribute("document");
                row = (com.liferay.portal.kernel.dao.search.ResultRow) _jspx_page_context.findAttribute("row");
                do {
                  out.write("\n\n\t\t\t\t");

				com.liferay.portal.search.web.internal.result.display.builder.SearchResultSummaryDisplayBuilder searchResultSummaryDisplayBuilder = new com.liferay.portal.search.web.internal.result.display.builder.SearchResultSummaryDisplayBuilder();

				searchResultSummaryDisplayBuilder.setAssetEntryLocalService(com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil.getService());
				searchResultSummaryDisplayBuilder.setCurrentURL(currentURL);
				searchResultSummaryDisplayBuilder.setDocument(document);
				searchResultSummaryDisplayBuilder.setFastDateFormatFactory(com.liferay.portal.kernel.util.FastDateFormatFactoryUtil.getFastDateFormatFactory());
				searchResultSummaryDisplayBuilder.setHighlightEnabled(searchDisplayContext.isHighlightEnabled());
				searchResultSummaryDisplayBuilder.setLanguage(com.liferay.portal.kernel.language.LanguageUtil.getLanguage());
				searchResultSummaryDisplayBuilder.setLocale(locale);
				searchResultSummaryDisplayBuilder.setPortletURLFactory(searchDisplayContext.getPortletURLFactory());
				searchResultSummaryDisplayBuilder.setRenderRequest(renderRequest);
				searchResultSummaryDisplayBuilder.setRenderResponse(renderResponse);
				searchResultSummaryDisplayBuilder.setRequest(request);
				searchResultSummaryDisplayBuilder.setResourceActions(com.liferay.portal.kernel.security.permission.ResourceActionsUtil.getResourceActions());
				searchResultSummaryDisplayBuilder.setSearchResultPreferences(searchDisplayContext.getSearchResultPreferences());
				searchResultSummaryDisplayBuilder.setSummaryBuilderFactory(searchDisplayContext.getSummaryBuilderFactory());
				searchResultSummaryDisplayBuilder.setThemeDisplay(themeDisplay);

				com.liferay.portal.search.web.internal.result.display.context.SearchResultSummaryDisplayContext searchResultSummaryDisplayContext = searchResultSummaryDisplayBuilder.build();
				
                  out.write("\n\n\t\t\t\t");
                  //  c:if
                  com.liferay.taglib.core.IfTag _jspx_th_c_if_4 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                  _jspx_th_c_if_4.setPageContext(_jspx_page_context);
                  _jspx_th_c_if_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1row_0);
                  _jspx_th_c_if_4.setTest( searchResultSummaryDisplayContext != null );
                  int _jspx_eval_c_if_4 = _jspx_th_c_if_4.doStartTag();
                  if (_jspx_eval_c_if_4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    out.write("\n\t\t\t\t\t");
                    out.write('\n');
                    out.write('\n');
                    //  c:choose
                    com.liferay.taglib.core.ChooseTag _jspx_th_c_choose_0 = (com.liferay.taglib.core.ChooseTag) _jspx_tagPool_c_choose.get(com.liferay.taglib.core.ChooseTag.class);
                    _jspx_th_c_choose_0.setPageContext(_jspx_page_context);
                    _jspx_th_c_choose_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_4);
                    int _jspx_eval_c_choose_0 = _jspx_th_c_choose_0.doStartTag();
                    if (_jspx_eval_c_choose_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      out.write('\n');
                      out.write('	');
                      //  c:when
                      com.liferay.taglib.core.WhenTag _jspx_th_c_when_0 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
                      _jspx_th_c_when_0.setPageContext(_jspx_page_context);
                      _jspx_th_c_when_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_0);
                      _jspx_th_c_when_0.setTest( !searchResultSummaryDisplayContext.isTemporarilyUnavailable() );
                      int _jspx_eval_c_when_0 = _jspx_th_c_when_0.doStartTag();
                      if (_jspx_eval_c_when_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                        out.write("\n\t\t");
                        //  c:if
                        com.liferay.taglib.core.IfTag _jspx_th_c_if_5 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                        _jspx_th_c_if_5.setPageContext(_jspx_page_context);
                        _jspx_th_c_if_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_0);
                        _jspx_th_c_if_5.setTest( searchResultSummaryDisplayContext.isUserPortraitVisible() );
                        int _jspx_eval_c_if_5 = _jspx_th_c_if_5.doStartTag();
                        if (_jspx_eval_c_if_5 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                          out.write("\n\t\t\t");
                          //  liferay-ui:search-container-column-text
                          com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_0 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
                          _jspx_th_liferay$1ui_search$1container$1column$1text_0.setPageContext(_jspx_page_context);
                          _jspx_th_liferay$1ui_search$1container$1column$1text_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_5);
                          int _jspx_eval_liferay$1ui_search$1container$1column$1text_0 = _jspx_th_liferay$1ui_search$1container$1column$1text_0.doStartTag();
                          if (_jspx_eval_liferay$1ui_search$1container$1column$1text_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                            if (_jspx_eval_liferay$1ui_search$1container$1column$1text_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                              out = _jspx_page_context.pushBody();
                              _jspx_th_liferay$1ui_search$1container$1column$1text_0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                              _jspx_th_liferay$1ui_search$1container$1column$1text_0.doInitBody();
                            }
                            do {
                              out.write("\n\t\t\t\t");
                              //  liferay-ui:user-portrait
                              com.liferay.taglib.ui.UserPortraitTag _jspx_th_liferay$1ui_user$1portrait_0 = (com.liferay.taglib.ui.UserPortraitTag) _jspx_tagPool_liferay$1ui_user$1portrait_userId_nobody.get(com.liferay.taglib.ui.UserPortraitTag.class);
                              _jspx_th_liferay$1ui_user$1portrait_0.setPageContext(_jspx_page_context);
                              _jspx_th_liferay$1ui_user$1portrait_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1column$1text_0);
                              _jspx_th_liferay$1ui_user$1portrait_0.setUserId( searchResultSummaryDisplayContext.getAssetEntryUserId() );
                              int _jspx_eval_liferay$1ui_user$1portrait_0 = _jspx_th_liferay$1ui_user$1portrait_0.doStartTag();
                              if (_jspx_th_liferay$1ui_user$1portrait_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_liferay$1ui_user$1portrait_userId_nobody.reuse(_jspx_th_liferay$1ui_user$1portrait_0);
                              return;
                              }
                              _jspx_tagPool_liferay$1ui_user$1portrait_userId_nobody.reuse(_jspx_th_liferay$1ui_user$1portrait_0);
                              out.write("\n\t\t\t");
                              int evalDoAfterBody = _jspx_th_liferay$1ui_search$1container$1column$1text_0.doAfterBody();
                              if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                              break;
                            } while (true);
                            if (_jspx_eval_liferay$1ui_search$1container$1column$1text_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                              out = _jspx_page_context.popBody();
                          }
                          if (_jspx_th_liferay$1ui_search$1container$1column$1text_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                            _jspx_tagPool_liferay$1ui_search$1container$1column$1text.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_0);
                            return;
                          }
                          _jspx_tagPool_liferay$1ui_search$1container$1column$1text.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_0);
                          out.write("\n\t\t");
                        }
                        if (_jspx_th_c_if_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                          _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_5);
                          return;
                        }
                        _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_5);
                        out.write("\n\n\t\t");
                        //  liferay-ui:search-container-column-text
                        com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_1 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text_colspan.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
                        _jspx_th_liferay$1ui_search$1container$1column$1text_1.setPageContext(_jspx_page_context);
                        _jspx_th_liferay$1ui_search$1container$1column$1text_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_0);
                        _jspx_th_liferay$1ui_search$1container$1column$1text_1.setColspan( 2 );
                        int _jspx_eval_liferay$1ui_search$1container$1column$1text_1 = _jspx_th_liferay$1ui_search$1container$1column$1text_1.doStartTag();
                        if (_jspx_eval_liferay$1ui_search$1container$1column$1text_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                          if (_jspx_eval_liferay$1ui_search$1container$1column$1text_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                            out = _jspx_page_context.pushBody();
                            _jspx_th_liferay$1ui_search$1container$1column$1text_1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                            _jspx_th_liferay$1ui_search$1container$1column$1text_1.doInitBody();
                          }
                          do {
                            out.write("\n\t\t\t<h5>\n\t\t\t\t<a href=\"");
                            out.print( searchResultSummaryDisplayContext.getViewURL() );
                            out.write("\">\n\t\t\t\t\t<strong>");
                            out.print( searchResultSummaryDisplayContext.getHighlightedTitle() );
                            out.write("</strong>\n\t\t\t\t</a>\n\n\t\t\t\t");
                            //  c:if
                            com.liferay.taglib.core.IfTag _jspx_th_c_if_6 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                            _jspx_th_c_if_6.setPageContext(_jspx_page_context);
                            _jspx_th_c_if_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1column$1text_1);
                            _jspx_th_c_if_6.setTest( searchResultSummaryDisplayContext.isAssetRendererURLDownloadVisible() );
                            int _jspx_eval_c_if_6 = _jspx_th_c_if_6.doStartTag();
                            if (_jspx_eval_c_if_6 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                              out.write("\n\t\t\t\t\t");
                              //  aui:a
                              com.liferay.taglib.aui.ATag _jspx_th_aui_a_2 = (com.liferay.taglib.aui.ATag) _jspx_tagPool_aui_a_href.get(com.liferay.taglib.aui.ATag.class);
                              _jspx_th_aui_a_2.setPageContext(_jspx_page_context);
                              _jspx_th_aui_a_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_6);
                              _jspx_th_aui_a_2.setHref( searchResultSummaryDisplayContext.getAssetRendererURLDownload() );
                              int _jspx_eval_aui_a_2 = _jspx_th_aui_a_2.doStartTag();
                              if (_jspx_eval_aui_a_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                              out.write("\n\t\t\t\t\t\t");
                              //  liferay-ui:message
                              com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_2 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_arguments_nobody.get(com.liferay.taglib.ui.MessageTag.class);
                              _jspx_th_liferay$1ui_message_2.setPageContext(_jspx_page_context);
                              _jspx_th_liferay$1ui_message_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_a_2);
                              _jspx_th_liferay$1ui_message_2.setArguments( HtmlUtil.escape(searchResultSummaryDisplayContext.getTitle()) );
                              _jspx_th_liferay$1ui_message_2.setKey("download-x");
                              int _jspx_eval_liferay$1ui_message_2 = _jspx_th_liferay$1ui_message_2.doStartTag();
                              if (_jspx_th_liferay$1ui_message_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_liferay$1ui_message_key_arguments_nobody.reuse(_jspx_th_liferay$1ui_message_2);
                              return;
                              }
                              _jspx_tagPool_liferay$1ui_message_key_arguments_nobody.reuse(_jspx_th_liferay$1ui_message_2);
                              out.write("\n\t\t\t\t\t");
                              }
                              if (_jspx_th_aui_a_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_aui_a_href.reuse(_jspx_th_aui_a_2);
                              return;
                              }
                              _jspx_tagPool_aui_a_href.reuse(_jspx_th_aui_a_2);
                              out.write("\n\t\t\t\t");
                            }
                            if (_jspx_th_c_if_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_6);
                              return;
                            }
                            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_6);
                            out.write("\n\t\t\t</h5>\n\n\t\t\t<h6 class=\"text-default\">\n\t\t\t\t");
                            out.print( searchResultSummaryDisplayContext.getModelResource() );
                            out.write("\n\n\t\t\t\t");
                            //  c:if
                            com.liferay.taglib.core.IfTag _jspx_th_c_if_7 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                            _jspx_th_c_if_7.setPageContext(_jspx_page_context);
                            _jspx_th_c_if_7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1column$1text_1);
                            _jspx_th_c_if_7.setTest( searchResultSummaryDisplayContext.isLocaleReminderVisible() );
                            int _jspx_eval_c_if_7 = _jspx_th_c_if_7.doStartTag();
                            if (_jspx_eval_c_if_7 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                              out.write("\n\t\t\t\t\t");
                              //  liferay-ui:icon
                              com.liferay.taglib.ui.IconTag _jspx_th_liferay$1ui_icon_0 = (com.liferay.taglib.ui.IconTag) _jspx_tagPool_liferay$1ui_icon_message_image_nobody.get(com.liferay.taglib.ui.IconTag.class);
                              _jspx_th_liferay$1ui_icon_0.setPageContext(_jspx_page_context);
                              _jspx_th_liferay$1ui_icon_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_7);
                              _jspx_th_liferay$1ui_icon_0.setImage( "../language/" + searchResultSummaryDisplayContext.getLocaleLanguageId() );
                              _jspx_th_liferay$1ui_icon_0.setMessage( searchResultSummaryDisplayContext.getLocaleReminder() );
                              int _jspx_eval_liferay$1ui_icon_0 = _jspx_th_liferay$1ui_icon_0.doStartTag();
                              if (_jspx_th_liferay$1ui_icon_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_liferay$1ui_icon_message_image_nobody.reuse(_jspx_th_liferay$1ui_icon_0);
                              return;
                              }
                              _jspx_tagPool_liferay$1ui_icon_message_image_nobody.reuse(_jspx_th_liferay$1ui_icon_0);
                              out.write("\n\t\t\t\t");
                            }
                            if (_jspx_th_c_if_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_7);
                              return;
                            }
                            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_7);
                            out.write("\n\t\t\t</h6>\n\n\t\t\t");
                            //  c:if
                            com.liferay.taglib.core.IfTag _jspx_th_c_if_8 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                            _jspx_th_c_if_8.setPageContext(_jspx_page_context);
                            _jspx_th_c_if_8.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1column$1text_1);
                            _jspx_th_c_if_8.setTest( searchResultSummaryDisplayContext.isContentVisible() );
                            int _jspx_eval_c_if_8 = _jspx_th_c_if_8.doStartTag();
                            if (_jspx_eval_c_if_8 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                              out.write("\n\t\t\t\t<h6 class=\"text-default\">\n\t\t\t\t\t");
                              out.print( searchResultSummaryDisplayContext.getContent() );
                              out.write("\n\t\t\t\t</h6>\n\t\t\t");
                            }
                            if (_jspx_th_c_if_8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_8);
                              return;
                            }
                            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_8);
                            out.write("\n\n\t\t\t");
                            //  c:if
                            com.liferay.taglib.core.IfTag _jspx_th_c_if_9 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                            _jspx_th_c_if_9.setPageContext(_jspx_page_context);
                            _jspx_th_c_if_9.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1column$1text_1);
                            _jspx_th_c_if_9.setTest( searchResultSummaryDisplayContext.isAssetCategoriesOrTagsVisible() );
                            int _jspx_eval_c_if_9 = _jspx_th_c_if_9.doStartTag();
                            if (_jspx_eval_c_if_9 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                              out.write("\n\t\t\t\t<h6 class=\"text-default\">\n\t\t\t\t\t");
                              //  liferay-asset:asset-tags-summary
                              com.liferay.asset.taglib.servlet.taglib.AssetTagsSummaryTag _jspx_th_liferay$1asset_asset$1tags$1summary_0 = (com.liferay.asset.taglib.servlet.taglib.AssetTagsSummaryTag) _jspx_tagPool_liferay$1asset_asset$1tags$1summary_portletURL_paramName_classPK_className_nobody.get(com.liferay.asset.taglib.servlet.taglib.AssetTagsSummaryTag.class);
                              _jspx_th_liferay$1asset_asset$1tags$1summary_0.setPageContext(_jspx_page_context);
                              _jspx_th_liferay$1asset_asset$1tags$1summary_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_9);
                              _jspx_th_liferay$1asset_asset$1tags$1summary_0.setClassName( searchResultSummaryDisplayContext.getClassName() );
                              _jspx_th_liferay$1asset_asset$1tags$1summary_0.setClassPK( searchResultSummaryDisplayContext.getClassPK() );
                              _jspx_th_liferay$1asset_asset$1tags$1summary_0.setParamName( searchResultSummaryDisplayContext.getFieldAssetTagNames() );
                              _jspx_th_liferay$1asset_asset$1tags$1summary_0.setPortletURL( searchResultSummaryDisplayContext.getPortletURL() );
                              int _jspx_eval_liferay$1asset_asset$1tags$1summary_0 = _jspx_th_liferay$1asset_asset$1tags$1summary_0.doStartTag();
                              if (_jspx_th_liferay$1asset_asset$1tags$1summary_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_liferay$1asset_asset$1tags$1summary_portletURL_paramName_classPK_className_nobody.reuse(_jspx_th_liferay$1asset_asset$1tags$1summary_0);
                              return;
                              }
                              _jspx_tagPool_liferay$1asset_asset$1tags$1summary_portletURL_paramName_classPK_className_nobody.reuse(_jspx_th_liferay$1asset_asset$1tags$1summary_0);
                              out.write("\n\n\t\t\t\t\t");
                              //  liferay-asset:asset-categories-summary
                              com.liferay.asset.taglib.servlet.taglib.AssetCategoriesSummaryTag _jspx_th_liferay$1asset_asset$1categories$1summary_0 = (com.liferay.asset.taglib.servlet.taglib.AssetCategoriesSummaryTag) _jspx_tagPool_liferay$1asset_asset$1categories$1summary_portletURL_paramName_classPK_className_nobody.get(com.liferay.asset.taglib.servlet.taglib.AssetCategoriesSummaryTag.class);
                              _jspx_th_liferay$1asset_asset$1categories$1summary_0.setPageContext(_jspx_page_context);
                              _jspx_th_liferay$1asset_asset$1categories$1summary_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_9);
                              _jspx_th_liferay$1asset_asset$1categories$1summary_0.setClassName( searchResultSummaryDisplayContext.getClassName() );
                              _jspx_th_liferay$1asset_asset$1categories$1summary_0.setClassPK( searchResultSummaryDisplayContext.getClassPK() );
                              _jspx_th_liferay$1asset_asset$1categories$1summary_0.setParamName( searchResultSummaryDisplayContext.getFieldAssetCategoryIds() );
                              _jspx_th_liferay$1asset_asset$1categories$1summary_0.setPortletURL( searchResultSummaryDisplayContext.getPortletURL() );
                              int _jspx_eval_liferay$1asset_asset$1categories$1summary_0 = _jspx_th_liferay$1asset_asset$1categories$1summary_0.doStartTag();
                              if (_jspx_th_liferay$1asset_asset$1categories$1summary_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_liferay$1asset_asset$1categories$1summary_portletURL_paramName_classPK_className_nobody.reuse(_jspx_th_liferay$1asset_asset$1categories$1summary_0);
                              return;
                              }
                              _jspx_tagPool_liferay$1asset_asset$1categories$1summary_portletURL_paramName_classPK_className_nobody.reuse(_jspx_th_liferay$1asset_asset$1categories$1summary_0);
                              out.write("\n\t\t\t\t</h6>\n\t\t\t");
                            }
                            if (_jspx_th_c_if_9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_9);
                              return;
                            }
                            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_9);
                            out.write("\n\n\t\t\t");
                            //  c:if
                            com.liferay.taglib.core.IfTag _jspx_th_c_if_10 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                            _jspx_th_c_if_10.setPageContext(_jspx_page_context);
                            _jspx_th_c_if_10.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1column$1text_1);
                            _jspx_th_c_if_10.setTest( searchResultSummaryDisplayContext.isDocumentFormVisible() );
                            int _jspx_eval_c_if_10 = _jspx_th_c_if_10.doStartTag();
                            if (_jspx_eval_c_if_10 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                              out.write("\n\t\t\t\t<h6 class=\"expand-details text-default\"><a href=\"javascript:;\">");
                              if (_jspx_meth_liferay$1ui_message_3((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_10, _jspx_page_context))
                              return;
                              out.write("...</a></h6>\n\n\t\t\t\t<div class=\"hide table-details table-responsive\">\n\t\t\t\t\t<table class=\"table\">\n\t\t\t\t\t\t<thead>\n\t\t\t\t\t\t\t<tr>\n\t\t\t\t\t\t\t\t<th>\n\t\t\t\t\t\t\t\t\t");
                              if (_jspx_meth_liferay$1ui_message_4((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_10, _jspx_page_context))
                              return;
                              out.write("\n\t\t\t\t\t\t\t\t</th>\n\t\t\t\t\t\t\t\t<th>\n\t\t\t\t\t\t\t\t\t");
                              if (_jspx_meth_liferay$1ui_message_5((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_10, _jspx_page_context))
                              return;
                              out.write("\n\t\t\t\t\t\t\t\t</th>\n\t\t\t\t\t\t\t</tr>\n\t\t\t\t\t\t</thead>\n\n\t\t\t\t\t\t<tbody>\n\n\t\t\t\t\t\t\t");

							for (SearchResultFieldDisplayContext searchResultFieldDisplayContext : searchResultSummaryDisplayContext.getDocumentFormFieldDisplayContexts()) {
							
                              out.write("\n\n\t\t\t\t\t\t\t\t<tr>\n\t\t\t\t\t\t\t\t\t<td>\n\t\t\t\t\t\t\t\t\t\t<strong>");
                              out.print( HtmlUtil.escape(searchResultFieldDisplayContext.getName()) );
                              out.write("</strong>\n\n\t\t\t\t\t\t\t\t\t\t<br />\n\n\t\t\t\t\t\t\t\t\t\t<em>\n\t\t\t\t\t\t\t\t\t\t\t");
                              if (_jspx_meth_liferay$1ui_message_6((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_10, _jspx_page_context))
                              return;
                              out.write(" = ");
                              out.print( searchResultFieldDisplayContext.isArray() );
                              out.write(',');
                              out.write(' ');
                              if (_jspx_meth_liferay$1ui_message_7((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_10, _jspx_page_context))
                              return;
                              out.write(" = ");
                              out.print( searchResultFieldDisplayContext.getBoost() );
                              out.write(",<br />\n\n\t\t\t\t\t\t\t\t\t\t\t");
                              if (_jspx_meth_liferay$1ui_message_8((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_10, _jspx_page_context))
                              return;
                              out.write(" = ");
                              out.print( searchResultFieldDisplayContext.isNumeric() );
                              out.write(',');
                              out.write(' ');
                              if (_jspx_meth_liferay$1ui_message_9((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_10, _jspx_page_context))
                              return;
                              out.write(" = ");
                              out.print( searchResultFieldDisplayContext.isTokenized() );
                              out.write("\n\t\t\t\t\t\t\t\t\t\t</em>\n\t\t\t\t\t\t\t\t\t</td>\n\t\t\t\t\t\t\t\t\t<td>\n\t\t\t\t\t\t\t\t\t\t<code>\n\t\t\t\t\t\t\t\t\t\t\t");
                              out.print( searchResultFieldDisplayContext.getValuesToString() );
                              out.write("\n\t\t\t\t\t\t\t\t\t\t</code>\n\t\t\t\t\t\t\t\t\t</td>\n\t\t\t\t\t\t\t\t</tr>\n\n\t\t\t\t\t\t\t");

							}
							
                              out.write("\n\n\t\t\t\t\t\t</tbody>\n\t\t\t\t\t</table>\n\t\t\t\t</div>\n\t\t\t");
                            }
                            if (_jspx_th_c_if_10.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_10);
                              return;
                            }
                            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_10);
                            out.write("\n\t\t");
                            int evalDoAfterBody = _jspx_th_liferay$1ui_search$1container$1column$1text_1.doAfterBody();
                            if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                              break;
                          } while (true);
                          if (_jspx_eval_liferay$1ui_search$1container$1column$1text_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                            out = _jspx_page_context.popBody();
                        }
                        if (_jspx_th_liferay$1ui_search$1container$1column$1text_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                          _jspx_tagPool_liferay$1ui_search$1container$1column$1text_colspan.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_1);
                          return;
                        }
                        _jspx_tagPool_liferay$1ui_search$1container$1column$1text_colspan.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_1);
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
                      //  c:otherwise
                      com.liferay.taglib.core.OtherwiseTag _jspx_th_c_otherwise_0 = (com.liferay.taglib.core.OtherwiseTag) _jspx_tagPool_c_otherwise.get(com.liferay.taglib.core.OtherwiseTag.class);
                      _jspx_th_c_otherwise_0.setPageContext(_jspx_page_context);
                      _jspx_th_c_otherwise_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_0);
                      int _jspx_eval_c_otherwise_0 = _jspx_th_c_otherwise_0.doStartTag();
                      if (_jspx_eval_c_otherwise_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                        out.write("\n\t\t");
                        //  liferay-ui:search-container-column-text
                        com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_2 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text_colspan.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
                        _jspx_th_liferay$1ui_search$1container$1column$1text_2.setPageContext(_jspx_page_context);
                        _jspx_th_liferay$1ui_search$1container$1column$1text_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_0);
                        _jspx_th_liferay$1ui_search$1container$1column$1text_2.setColspan( 3 );
                        int _jspx_eval_liferay$1ui_search$1container$1column$1text_2 = _jspx_th_liferay$1ui_search$1container$1column$1text_2.doStartTag();
                        if (_jspx_eval_liferay$1ui_search$1container$1column$1text_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                          if (_jspx_eval_liferay$1ui_search$1container$1column$1text_2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                            out = _jspx_page_context.pushBody();
                            _jspx_th_liferay$1ui_search$1container$1column$1text_2.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                            _jspx_th_liferay$1ui_search$1container$1column$1text_2.doInitBody();
                          }
                          do {
                            out.write("\n\t\t\t<div class=\"alert alert-danger\">\n\t\t\t\t");
                            //  liferay-ui:message
                            com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_10 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody.get(com.liferay.taglib.ui.MessageTag.class);
                            _jspx_th_liferay$1ui_message_10.setPageContext(_jspx_page_context);
                            _jspx_th_liferay$1ui_message_10.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1column$1text_2);
                            _jspx_th_liferay$1ui_message_10.setArguments(new String("result"));
                            _jspx_th_liferay$1ui_message_10.setKey("is-temporarily-unavailable");
                            _jspx_th_liferay$1ui_message_10.setTranslateArguments( true );
                            int _jspx_eval_liferay$1ui_message_10 = _jspx_th_liferay$1ui_message_10.doStartTag();
                            if (_jspx_th_liferay$1ui_message_10.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody.reuse(_jspx_th_liferay$1ui_message_10);
                              return;
                            }
                            _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody.reuse(_jspx_th_liferay$1ui_message_10);
                            out.write("\n\t\t\t</div>\n\t\t");
                            int evalDoAfterBody = _jspx_th_liferay$1ui_search$1container$1column$1text_2.doAfterBody();
                            if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                              break;
                          } while (true);
                          if (_jspx_eval_liferay$1ui_search$1container$1column$1text_2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                            out = _jspx_page_context.popBody();
                        }
                        if (_jspx_th_liferay$1ui_search$1container$1column$1text_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                          _jspx_tagPool_liferay$1ui_search$1container$1column$1text_colspan.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_2);
                          return;
                        }
                        _jspx_tagPool_liferay$1ui_search$1container$1column$1text_colspan.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_2);
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
                    out.write("\n\t\t\t\t");
                  }
                  if (_jspx_th_c_if_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_4);
                    return;
                  }
                  _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_4);
                  out.write("\n\t\t\t");
                  int evalDoAfterBody = _jspx_th_liferay$1ui_search$1container$1row_0.doAfterBody();
                  index = (java.lang.Integer) _jspx_page_context.findAttribute("index");
                  document = (com.liferay.portal.kernel.search.Document) _jspx_page_context.findAttribute("document");
                  row = (com.liferay.portal.kernel.dao.search.ResultRow) _jspx_page_context.findAttribute("row");
                  if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                    break;
                } while (true);
                if (_jspx_eval_liferay$1ui_search$1container$1row_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                  out = _jspx_page_context.popBody();
              }
              if (_jspx_th_liferay$1ui_search$1container$1row_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_liferay$1ui_search$1container$1row_stringKey_modelVar_keyProperty_escapedModel_className.reuse(_jspx_th_liferay$1ui_search$1container$1row_0);
                return;
              }
              _jspx_tagPool_liferay$1ui_search$1container$1row_stringKey_modelVar_keyProperty_escapedModel_className.reuse(_jspx_th_liferay$1ui_search$1container$1row_0);
              out.write("\n\n\t\t\t");
              if (_jspx_meth_liferay$1ui_search$1iterator_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1ui_search$1container_0, _jspx_page_context))
                return;
              out.write("\n\n\t\t\t");
              //  c:if
              com.liferay.taglib.core.IfTag _jspx_th_c_if_11 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
              _jspx_th_c_if_11.setPageContext(_jspx_page_context);
              _jspx_th_c_if_11.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container_0);
              _jspx_th_c_if_11.setTest( searchDisplayContext.isDisplayMainQuery() && (searchDisplayContext.getQueryString() != null) );
              int _jspx_eval_c_if_11 = _jspx_th_c_if_11.doStartTag();
              if (_jspx_eval_c_if_11 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\t\t\t\t<div class=\"full-query\">\n\t\t\t\t\t<code>\n\t\t\t\t\t\t");
                out.print( HtmlUtil.escape(searchDisplayContext.getQueryString()) );
                out.write("\n\t\t\t\t\t</code>\n\t\t\t\t</div>\n\t\t\t");
              }
              if (_jspx_th_c_if_11.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_11);
                return;
              }
              _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_11);
              out.write("\n\t\t");
            }
            if (_jspx_th_liferay$1ui_search$1container_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_liferay$1ui_search$1container_searchContainer_id.reuse(_jspx_th_liferay$1ui_search$1container_0);
              return;
            }
            _jspx_tagPool_liferay$1ui_search$1container_searchContainer_id.reuse(_jspx_th_liferay$1ui_search$1container_0);
            out.write('\n');
            out.write('	');
          }
          if (_jspx_th_aui_col_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_aui_col_span_first_cssClass.reuse(_jspx_th_aui_col_1);
            return;
          }
          _jspx_tagPool_aui_col_span_first_cssClass.reuse(_jspx_th_aui_col_1);
          out.write('\n');
        }
        if (_jspx_th_aui_row_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_aui_row_cssClass.reuse(_jspx_th_aui_row_0);
          return;
        }
        _jspx_tagPool_aui_row_cssClass.reuse(_jspx_th_aui_row_0);
        out.write('\n');
        out.write('\n');
        if (_jspx_meth_aui_script_2((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_form_0, _jspx_page_context))
          return;
        out.write("\n\n\t");
        //  c:if
        com.liferay.taglib.core.IfTag _jspx_th_c_if_12 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
        _jspx_th_c_if_12.setPageContext(_jspx_page_context);
        _jspx_th_c_if_12.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
        _jspx_th_c_if_12.setTest( searchDisplayContext.isDisplayOpenSearchResults() );
        int _jspx_eval_c_if_12 = _jspx_th_c_if_12.doStartTag();
        if (_jspx_eval_c_if_12 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          out.write("\n\t\t");
          //  liferay-ui:panel
          com.liferay.taglib.ui.PanelTag _jspx_th_liferay$1ui_panel_0 = (com.liferay.taglib.ui.PanelTag) _jspx_tagPool_liferay$1ui_panel_title_persistState_id_extended_cssClass_collapsible.get(com.liferay.taglib.ui.PanelTag.class);
          _jspx_th_liferay$1ui_panel_0.setPageContext(_jspx_page_context);
          _jspx_th_liferay$1ui_panel_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_12);
          _jspx_th_liferay$1ui_panel_0.setCollapsible( true );
          _jspx_th_liferay$1ui_panel_0.setCssClass("open-search-panel");
          _jspx_th_liferay$1ui_panel_0.setExtended( true );
          _jspx_th_liferay$1ui_panel_0.setId("searchOpenSearchPanelContainer");
          _jspx_th_liferay$1ui_panel_0.setPersistState( true );
          _jspx_th_liferay$1ui_panel_0.setTitle("open-search");
          int _jspx_eval_liferay$1ui_panel_0 = _jspx_th_liferay$1ui_panel_0.doStartTag();
          if (_jspx_eval_liferay$1ui_panel_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t\t");
            out.write('\n');
            out.write('\n');
            //  aui:button-row
            com.liferay.taglib.aui.ButtonRowTag _jspx_th_aui_button$1row_0 = (com.liferay.taglib.aui.ButtonRowTag) _jspx_tagPool_aui_button$1row.get(com.liferay.taglib.aui.ButtonRowTag.class);
            _jspx_th_aui_button$1row_0.setPageContext(_jspx_page_context);
            _jspx_th_aui_button$1row_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_panel_0);
            int _jspx_eval_aui_button$1row_0 = _jspx_th_aui_button$1row_0.doStartTag();
            if (_jspx_eval_aui_button$1row_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write('\n');
              out.write('	');
              //  aui:button
              com.liferay.taglib.aui.ButtonTag _jspx_th_aui_button_1 = (com.liferay.taglib.aui.ButtonTag) _jspx_tagPool_aui_button_value_onClick_nobody.get(com.liferay.taglib.aui.ButtonTag.class);
              _jspx_th_aui_button_1.setPageContext(_jspx_page_context);
              _jspx_th_aui_button_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_button$1row_0);
              _jspx_th_aui_button_1.setOnClick( renderResponse.getNamespace() + "addSearchProvider();" );
              _jspx_th_aui_button_1.setValue( LanguageUtil.format(request, "add-x-as-a-search-provider", HtmlUtil.escape(company.getName()), false) );
              int _jspx_eval_aui_button_1 = _jspx_th_aui_button_1.doStartTag();
              if (_jspx_th_aui_button_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_aui_button_value_onClick_nobody.reuse(_jspx_th_aui_button_1);
                return;
              }
              _jspx_tagPool_aui_button_value_onClick_nobody.reuse(_jspx_th_aui_button_1);
              out.write('\n');
            }
            if (_jspx_th_aui_button$1row_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_aui_button$1row.reuse(_jspx_th_aui_button$1row_0);
              return;
            }
            _jspx_tagPool_aui_button$1row.reuse(_jspx_th_aui_button$1row_0);
            out.write('\n');
            out.write('\n');

String primarySearch = ParamUtil.getString(request, "primarySearch");

if (Validator.isNotNull(primarySearch)) {
	portalPreferences.setValue(SearchPortletKeys.SEARCH, "primary-search", primarySearch);
}
else {
	primarySearch = portalPreferences.getValue(SearchPortletKeys.SEARCH, "primary-search", StringPool.BLANK);
}

List<OpenSearch> openSearchInstances = com.liferay.portal.search.web.internal.util.SearchUtil.getOpenSearchInstances(primarySearch);

List<String> modelResources = new ArrayList<String>();

for (OpenSearch openSearchInstance : openSearchInstances) {
	modelResources.add(ResourceActionsUtil.getModelResource(locale, openSearchInstance.getClassName()));
}

            out.write("\n\n<div class=\"search-msg\">\n\t");
            //  c:choose
            com.liferay.taglib.core.ChooseTag _jspx_th_c_choose_1 = (com.liferay.taglib.core.ChooseTag) _jspx_tagPool_c_choose.get(com.liferay.taglib.core.ChooseTag.class);
            _jspx_th_c_choose_1.setPageContext(_jspx_page_context);
            _jspx_th_c_choose_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_panel_0);
            int _jspx_eval_c_choose_1 = _jspx_th_c_choose_1.doStartTag();
            if (_jspx_eval_c_choose_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\t\t");
              //  c:when
              com.liferay.taglib.core.WhenTag _jspx_th_c_when_1 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
              _jspx_th_c_when_1.setPageContext(_jspx_page_context);
              _jspx_th_c_when_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_1);
              _jspx_th_c_when_1.setTest( modelResources.isEmpty() );
              int _jspx_eval_c_when_1 = _jspx_th_c_when_1.doStartTag();
              if (_jspx_eval_c_when_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\t\t\t");
                if (_jspx_meth_liferay$1ui_message_11((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_when_1, _jspx_page_context))
                  return;
                out.write("\n\t\t");
              }
              if (_jspx_th_c_when_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_1);
                return;
              }
              _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_1);
              out.write("\n\t\t");
              //  c:otherwise
              com.liferay.taglib.core.OtherwiseTag _jspx_th_c_otherwise_1 = (com.liferay.taglib.core.OtherwiseTag) _jspx_tagPool_c_otherwise.get(com.liferay.taglib.core.OtherwiseTag.class);
              _jspx_th_c_otherwise_1.setPageContext(_jspx_page_context);
              _jspx_th_c_otherwise_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_1);
              int _jspx_eval_c_otherwise_1 = _jspx_th_c_otherwise_1.doStartTag();
              if (_jspx_eval_c_otherwise_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\t\t\t");
                if (_jspx_meth_liferay$1ui_message_12((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_otherwise_1, _jspx_page_context))
                  return;
                out.write(' ');
                out.print( StringUtil.merge(modelResources, StringPool.COMMA_AND_SPACE) );
                out.write("\n\t\t");
              }
              if (_jspx_th_c_otherwise_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_1);
                return;
              }
              _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_1);
              out.write('\n');
              out.write('	');
            }
            if (_jspx_th_c_choose_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_1);
              return;
            }
            _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_1);
            out.write("\n</div>\n\n");

int totalResults = 0;

for (int i = 0; i < openSearchInstances.size(); i++) {
	OpenSearch openSearch = openSearchInstances.get(i);

	int delta = 5;

	if (Validator.isNotNull(primarySearch) && primarySearch.equals(openSearch.getClassName())) {
		delta = ParamUtil.getInteger(request, SearchContainer.DEFAULT_DELTA_PARAM + i, SearchContainer.DEFAULT_DELTA);
	}

	SearchContainer openSearchSearchContainer = new SearchContainer(renderRequest, null, null, SearchContainer.DEFAULT_CUR_PARAM + i, delta, searchDisplayContext.getPortletURL(), null, LanguageUtil.format(request, "no-results-were-found-that-matched-the-keywords-x", "<strong>" + HtmlUtil.escape(searchDisplayContext.getKeywords()) + "</strong>", false));

	String xml = openSearch.search(request, groupId, themeDisplay.getUserId(), searchDisplayContext.getKeywords(), openSearchSearchContainer.getCur(), openSearchSearchContainer.getDelta(), format);

	LinkedHashMap<String, Object> groupParams = new LinkedHashMap<String, Object>();

	groupParams.put("active", Boolean.FALSE);

	int inactiveGroupsCount = GroupLocalServiceUtil.searchCount(themeDisplay.getCompanyId(), null, null, groupParams);

	Tuple tuple = com.liferay.portal.search.web.internal.util.SearchUtil.getElements(xml, openSearch.getClassName(), inactiveGroupsCount);

	List<Element> resultRows = (List<Element>)tuple.getObject(0);
	int totalRows = (Integer)tuple.getObject(1);

            out.write("\n\n\t");
            //  c:if
            com.liferay.taglib.core.IfTag _jspx_th_c_if_13 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
            _jspx_th_c_if_13.setPageContext(_jspx_page_context);
            _jspx_th_c_if_13.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_panel_0);
            _jspx_th_c_if_13.setTest( !resultRows.isEmpty() );
            int _jspx_eval_c_if_13 = _jspx_th_c_if_13.doStartTag();
            if (_jspx_eval_c_if_13 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\n\t\t");

		totalResults = totalResults + totalRows;
		
              out.write("\n\n\t\t");
              //  liferay-ui:search-container
              com.liferay.taglib.ui.SearchContainerTag _jspx_th_liferay$1ui_search$1container_1 = (com.liferay.taglib.ui.SearchContainerTag) _jspx_tagPool_liferay$1ui_search$1container_total_searchContainer.get(com.liferay.taglib.ui.SearchContainerTag.class);
              _jspx_th_liferay$1ui_search$1container_1.setPageContext(_jspx_page_context);
              _jspx_th_liferay$1ui_search$1container_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_13);
              _jspx_th_liferay$1ui_search$1container_1.setSearchContainer( openSearchSearchContainer );
              _jspx_th_liferay$1ui_search$1container_1.setTotal( totalRows );
              int _jspx_eval_liferay$1ui_search$1container_1 = _jspx_th_liferay$1ui_search$1container_1.doStartTag();
              if (_jspx_eval_liferay$1ui_search$1container_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                java.lang.Integer total = null;
                com.liferay.portal.kernel.dao.search.SearchContainer searchContainer = null;
                total = (java.lang.Integer) _jspx_page_context.findAttribute("total");
                searchContainer = (com.liferay.portal.kernel.dao.search.SearchContainer) _jspx_page_context.findAttribute("searchContainer");
                out.write("\n\t\t\t");
                //  liferay-ui:search-container-results
                java.util.List results = null;
                java.lang.Integer deprecatedTotal = null;
                com.liferay.taglib.ui.SearchContainerResultsTag _jspx_th_liferay$1ui_search$1container$1results_0 = (com.liferay.taglib.ui.SearchContainerResultsTag) _jspx_tagPool_liferay$1ui_search$1container$1results_results_nobody.get(com.liferay.taglib.ui.SearchContainerResultsTag.class);
                _jspx_th_liferay$1ui_search$1container$1results_0.setPageContext(_jspx_page_context);
                _jspx_th_liferay$1ui_search$1container$1results_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container_1);
                _jspx_th_liferay$1ui_search$1container$1results_0.setResults( resultRows );
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
                out.write("\n\n\t\t\t<div class=\"section-title\">\n\n\t\t\t\t");

				boolean filterSearch = false;

				if (openSearch instanceof HitsOpenSearchImpl) {
					HitsOpenSearchImpl hitsOpenSearchImpl = (HitsOpenSearchImpl)openSearch;

					Indexer hitsOpenSearchImplIndexer = hitsOpenSearchImpl.getIndexer();

					filterSearch = hitsOpenSearchImplIndexer.isFilterSearch();
				}

				// When the total is <= to the delta, we know the result is accurate
				// since we've filtered the entire result set. Otherwise, we simply
				// indicate that there are possibly many more. (ï¿½ la Google)

				
                out.write("\n\n\t\t\t\t");
                //  c:choose
                com.liferay.taglib.core.ChooseTag _jspx_th_c_choose_2 = (com.liferay.taglib.core.ChooseTag) _jspx_tagPool_c_choose.get(com.liferay.taglib.core.ChooseTag.class);
                _jspx_th_c_choose_2.setPageContext(_jspx_page_context);
                _jspx_th_c_choose_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container_1);
                int _jspx_eval_c_choose_2 = _jspx_th_c_choose_2.doStartTag();
                if (_jspx_eval_c_choose_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  out.write("\n\t\t\t\t\t");
                  //  c:when
                  com.liferay.taglib.core.WhenTag _jspx_th_c_when_2 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
                  _jspx_th_c_when_2.setPageContext(_jspx_page_context);
                  _jspx_th_c_when_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_2);
                  _jspx_th_c_when_2.setTest( filterSearch && (searchContainer.getTotal() > searchContainer.getDelta()) );
                  int _jspx_eval_c_when_2 = _jspx_th_c_when_2.doStartTag();
                  if (_jspx_eval_c_when_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    out.write("\n\t\t\t\t\t\t");
                    out.print( ResourceActionsUtil.getModelResource(locale, openSearch.getClassName()) );
                    out.write(' ');
                    out.write('(');
                    out.print( searchContainer.getDelta() );
                    out.write(' ');
                    if (_jspx_meth_liferay$1ui_message_13((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_when_2, _jspx_page_context))
                      return;
                    out.write(")\n\t\t\t\t\t");
                  }
                  if (_jspx_th_c_when_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_2);
                    return;
                  }
                  _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_2);
                  out.write("\n\t\t\t\t\t");
                  //  c:otherwise
                  com.liferay.taglib.core.OtherwiseTag _jspx_th_c_otherwise_2 = (com.liferay.taglib.core.OtherwiseTag) _jspx_tagPool_c_otherwise.get(com.liferay.taglib.core.OtherwiseTag.class);
                  _jspx_th_c_otherwise_2.setPageContext(_jspx_page_context);
                  _jspx_th_c_otherwise_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_2);
                  int _jspx_eval_c_otherwise_2 = _jspx_th_c_otherwise_2.doStartTag();
                  if (_jspx_eval_c_otherwise_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    out.write("\n\t\t\t\t\t\t");
                    out.print( ResourceActionsUtil.getModelResource(locale, openSearch.getClassName()) );
                    out.write(' ');
                    out.write('(');
                    out.print( searchContainer.getTotal() );
                    out.write(")\n\t\t\t\t\t");
                  }
                  if (_jspx_th_c_otherwise_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_2);
                    return;
                  }
                  _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_2);
                  out.write("\n\t\t\t\t");
                }
                if (_jspx_th_c_choose_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_2);
                  return;
                }
                _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_2);
                out.write("\n\t\t\t</div>\n\n\t\t\t");
                //  liferay-ui:search-container-row
                com.liferay.taglib.ui.SearchContainerRowTag _jspx_th_liferay$1ui_search$1container$1row_1 = (com.liferay.taglib.ui.SearchContainerRowTag) _jspx_tagPool_liferay$1ui_search$1container$1row_modelVar_className.get(com.liferay.taglib.ui.SearchContainerRowTag.class);
                _jspx_th_liferay$1ui_search$1container$1row_1.setPageContext(_jspx_page_context);
                _jspx_th_liferay$1ui_search$1container$1row_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container_1);
                _jspx_th_liferay$1ui_search$1container$1row_1.setClassName("com.liferay.portal.kernel.xml.Element");
                _jspx_th_liferay$1ui_search$1container$1row_1.setModelVar("element");
                int _jspx_eval_liferay$1ui_search$1container$1row_1 = _jspx_th_liferay$1ui_search$1container$1row_1.doStartTag();
                if (_jspx_eval_liferay$1ui_search$1container$1row_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  java.lang.Integer index = null;
                  com.liferay.portal.kernel.xml.Element element = null;
                  com.liferay.portal.kernel.dao.search.ResultRow row = null;
                  if (_jspx_eval_liferay$1ui_search$1container$1row_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                    out = _jspx_page_context.pushBody();
                    _jspx_th_liferay$1ui_search$1container$1row_1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                    _jspx_th_liferay$1ui_search$1container$1row_1.doInitBody();
                  }
                  index = (java.lang.Integer) _jspx_page_context.findAttribute("index");
                  element = (com.liferay.portal.kernel.xml.Element) _jspx_page_context.findAttribute("element");
                  row = (com.liferay.portal.kernel.dao.search.ResultRow) _jspx_page_context.findAttribute("row");
                  do {
                    out.write("\n\t\t\t\t");
                    //  liferay-ui:search-container-column-text
                    com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_3 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
                    _jspx_th_liferay$1ui_search$1container$1column$1text_3.setPageContext(_jspx_page_context);
                    _jspx_th_liferay$1ui_search$1container$1column$1text_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1row_1);
                    int _jspx_eval_liferay$1ui_search$1container$1column$1text_3 = _jspx_th_liferay$1ui_search$1container$1column$1text_3.doStartTag();
                    if (_jspx_eval_liferay$1ui_search$1container$1column$1text_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      if (_jspx_eval_liferay$1ui_search$1container$1column$1text_3 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                        out = _jspx_page_context.pushBody();
                        _jspx_th_liferay$1ui_search$1container$1column$1text_3.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                        _jspx_th_liferay$1ui_search$1container$1column$1text_3.doInitBody();
                      }
                      do {
                        out.write("\n\n\t\t\t\t\t");

					String entryTitle = element.elementText("title");
					String summary = element.elementText("summary");
					String entryClassName = element.elementText("entryClassName");
					long entryClassPK = GetterUtil.getLong(element.elementText("entryClassPK"));
					String[] queryTerms = StringUtil.split(element.elementText("queryTerms"), StringPool.COMMA_AND_SPACE);
					
                        out.write("\n\n\t\t\t\t\t<a class=\"entry-title\" href=\"");
                        out.print( com.liferay.portal.search.web.internal.util.SearchUtil.getSearchResultViewURL(renderRequest, renderResponse, entryClassName, entryClassPK, searchDisplayContext.isViewInContext(), currentURL) );
                        out.write("\">\n\t\t\t\t\t\t");
                        out.print( HighlightUtil.highlight(HtmlUtil.escape(entryTitle), queryTerms) );
                        out.write("\n\t\t\t\t\t</a>\n\n\t\t\t\t\t");
                        //  c:if
                        com.liferay.taglib.core.IfTag _jspx_th_c_if_14 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                        _jspx_th_c_if_14.setPageContext(_jspx_page_context);
                        _jspx_th_c_if_14.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1column$1text_3);
                        _jspx_th_c_if_14.setTest( Validator.isNotNull(summary) );
                        int _jspx_eval_c_if_14 = _jspx_th_c_if_14.doStartTag();
                        if (_jspx_eval_c_if_14 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                          out.write("\n\t\t\t\t\t\t<br />\n\n\t\t\t\t\t\t");
                          out.print( HighlightUtil.highlight(HtmlUtil.escape(summary), queryTerms) );
                          out.write("\n\t\t\t\t\t");
                        }
                        if (_jspx_th_c_if_14.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                          _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_14);
                          return;
                        }
                        _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_14);
                        out.write("\n\n\t\t\t\t\t<br />\n\n\t\t\t\t\t");
                        //  liferay-asset:asset-tags-summary
                        com.liferay.asset.taglib.servlet.taglib.AssetTagsSummaryTag _jspx_th_liferay$1asset_asset$1tags$1summary_1 = (com.liferay.asset.taglib.servlet.taglib.AssetTagsSummaryTag) _jspx_tagPool_liferay$1asset_asset$1tags$1summary_portletURL_paramName_classPK_className_nobody.get(com.liferay.asset.taglib.servlet.taglib.AssetTagsSummaryTag.class);
                        _jspx_th_liferay$1asset_asset$1tags$1summary_1.setPageContext(_jspx_page_context);
                        _jspx_th_liferay$1asset_asset$1tags$1summary_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1column$1text_3);
                        _jspx_th_liferay$1asset_asset$1tags$1summary_1.setClassName( entryClassName );
                        _jspx_th_liferay$1asset_asset$1tags$1summary_1.setClassPK( entryClassPK );
                        _jspx_th_liferay$1asset_asset$1tags$1summary_1.setParamName( Field.ASSET_TAG_NAMES );
                        _jspx_th_liferay$1asset_asset$1tags$1summary_1.setPortletURL( searchDisplayContext.getPortletURL() );
                        int _jspx_eval_liferay$1asset_asset$1tags$1summary_1 = _jspx_th_liferay$1asset_asset$1tags$1summary_1.doStartTag();
                        if (_jspx_th_liferay$1asset_asset$1tags$1summary_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                          _jspx_tagPool_liferay$1asset_asset$1tags$1summary_portletURL_paramName_classPK_className_nobody.reuse(_jspx_th_liferay$1asset_asset$1tags$1summary_1);
                          return;
                        }
                        _jspx_tagPool_liferay$1asset_asset$1tags$1summary_portletURL_paramName_classPK_className_nobody.reuse(_jspx_th_liferay$1asset_asset$1tags$1summary_1);
                        out.write("\n\t\t\t\t");
                        int evalDoAfterBody = _jspx_th_liferay$1ui_search$1container$1column$1text_3.doAfterBody();
                        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                          break;
                      } while (true);
                      if (_jspx_eval_liferay$1ui_search$1container$1column$1text_3 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                        out = _jspx_page_context.popBody();
                    }
                    if (_jspx_th_liferay$1ui_search$1container$1column$1text_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_liferay$1ui_search$1container$1column$1text.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_3);
                      return;
                    }
                    _jspx_tagPool_liferay$1ui_search$1container$1column$1text.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_3);
                    out.write("\n\t\t\t");
                    int evalDoAfterBody = _jspx_th_liferay$1ui_search$1container$1row_1.doAfterBody();
                    index = (java.lang.Integer) _jspx_page_context.findAttribute("index");
                    element = (com.liferay.portal.kernel.xml.Element) _jspx_page_context.findAttribute("element");
                    row = (com.liferay.portal.kernel.dao.search.ResultRow) _jspx_page_context.findAttribute("row");
                    if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                      break;
                  } while (true);
                  if (_jspx_eval_liferay$1ui_search$1container$1row_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                    out = _jspx_page_context.popBody();
                }
                if (_jspx_th_liferay$1ui_search$1container$1row_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_liferay$1ui_search$1container$1row_modelVar_className.reuse(_jspx_th_liferay$1ui_search$1container$1row_1);
                  return;
                }
                _jspx_tagPool_liferay$1ui_search$1container$1row_modelVar_className.reuse(_jspx_th_liferay$1ui_search$1container$1row_1);
                out.write("\n\n\t\t\t");
                //  liferay-ui:search-iterator
                com.liferay.taglib.ui.SearchIteratorTag _jspx_th_liferay$1ui_search$1iterator_1 = (com.liferay.taglib.ui.SearchIteratorTag) _jspx_tagPool_liferay$1ui_search$1iterator_paginate_nobody.get(com.liferay.taglib.ui.SearchIteratorTag.class);
                _jspx_th_liferay$1ui_search$1iterator_1.setPageContext(_jspx_page_context);
                _jspx_th_liferay$1ui_search$1iterator_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container_1);
                _jspx_th_liferay$1ui_search$1iterator_1.setPaginate( false );
                int _jspx_eval_liferay$1ui_search$1iterator_1 = _jspx_th_liferay$1ui_search$1iterator_1.doStartTag();
                if (_jspx_th_liferay$1ui_search$1iterator_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_liferay$1ui_search$1iterator_paginate_nobody.reuse(_jspx_th_liferay$1ui_search$1iterator_1);
                  return;
                }
                _jspx_tagPool_liferay$1ui_search$1iterator_paginate_nobody.reuse(_jspx_th_liferay$1ui_search$1iterator_1);
                out.write("\n\n\t\t\t");
                //  c:choose
                com.liferay.taglib.core.ChooseTag _jspx_th_c_choose_3 = (com.liferay.taglib.core.ChooseTag) _jspx_tagPool_c_choose.get(com.liferay.taglib.core.ChooseTag.class);
                _jspx_th_c_choose_3.setPageContext(_jspx_page_context);
                _jspx_th_c_choose_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container_1);
                int _jspx_eval_c_choose_3 = _jspx_th_c_choose_3.doStartTag();
                if (_jspx_eval_c_choose_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  out.write("\n\t\t\t\t");
                  //  c:when
                  com.liferay.taglib.core.WhenTag _jspx_th_c_when_3 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
                  _jspx_th_c_when_3.setPageContext(_jspx_page_context);
                  _jspx_th_c_when_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_3);
                  _jspx_th_c_when_3.setTest( (searchContainer.getTotal() == resultRows.size()) || (Validator.isNotNull(primarySearch) && openSearch.getClassName().equals(primarySearch)) );
                  int _jspx_eval_c_when_3 = _jspx_th_c_when_3.doStartTag();
                  if (_jspx_eval_c_when_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    out.write("\n\t\t\t\t\t<div class=\"search-paginator-container\">\n\t\t\t\t\t\t");
                    //  liferay-ui:search-paginator
                    com.liferay.taglib.ui.SearchPaginatorTag _jspx_th_liferay$1ui_search$1paginator_0 = (com.liferay.taglib.ui.SearchPaginatorTag) _jspx_tagPool_liferay$1ui_search$1paginator_type_searchContainer_nobody.get(com.liferay.taglib.ui.SearchPaginatorTag.class);
                    _jspx_th_liferay$1ui_search$1paginator_0.setPageContext(_jspx_page_context);
                    _jspx_th_liferay$1ui_search$1paginator_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_3);
                    _jspx_th_liferay$1ui_search$1paginator_0.setSearchContainer( searchContainer );
                    _jspx_th_liferay$1ui_search$1paginator_0.setType("more");
                    int _jspx_eval_liferay$1ui_search$1paginator_0 = _jspx_th_liferay$1ui_search$1paginator_0.doStartTag();
                    if (_jspx_th_liferay$1ui_search$1paginator_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_liferay$1ui_search$1paginator_type_searchContainer_nobody.reuse(_jspx_th_liferay$1ui_search$1paginator_0);
                      return;
                    }
                    _jspx_tagPool_liferay$1ui_search$1paginator_type_searchContainer_nobody.reuse(_jspx_th_liferay$1ui_search$1paginator_0);
                    out.write("\n\t\t\t\t\t</div>\n\t\t\t\t");
                  }
                  if (_jspx_th_c_when_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_3);
                    return;
                  }
                  _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_3);
                  out.write("\n\t\t\t\t");
                  //  c:otherwise
                  com.liferay.taglib.core.OtherwiseTag _jspx_th_c_otherwise_3 = (com.liferay.taglib.core.OtherwiseTag) _jspx_tagPool_c_otherwise.get(com.liferay.taglib.core.OtherwiseTag.class);
                  _jspx_th_c_otherwise_3.setPageContext(_jspx_page_context);
                  _jspx_th_c_otherwise_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_3);
                  int _jspx_eval_c_otherwise_3 = _jspx_th_c_otherwise_3.doStartTag();
                  if (_jspx_eval_c_otherwise_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    out.write("\n\t\t\t\t\t<div class=\"more-results\">\n\t\t\t\t\t\t");
                    //  portlet:renderURL
                    com.liferay.taglib.portlet.RenderURLTag _jspx_th_portlet_renderURL_0 = (com.liferay.taglib.portlet.RenderURLTag) _jspx_tagPool_portlet_renderURL_var.get(com.liferay.taglib.portlet.RenderURLTag.class);
                    _jspx_th_portlet_renderURL_0.setPageContext(_jspx_page_context);
                    _jspx_th_portlet_renderURL_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_3);
                    _jspx_th_portlet_renderURL_0.setVar("moreResultsURL");
                    int _jspx_eval_portlet_renderURL_0 = _jspx_th_portlet_renderURL_0.doStartTag();
                    if (_jspx_eval_portlet_renderURL_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      out.write("\n\t\t\t\t\t\t\t");
                      if (_jspx_meth_portlet_param_1((javax.servlet.jsp.tagext.JspTag) _jspx_th_portlet_renderURL_0, _jspx_page_context))
                        return;
                      out.write("\n\t\t\t\t\t\t\t");
                      //  portlet:param
                      com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_2 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                      _jspx_th_portlet_param_2.setPageContext(_jspx_page_context);
                      _jspx_th_portlet_param_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_0);
                      _jspx_th_portlet_param_2.setName("groupId");
                      _jspx_th_portlet_param_2.setValue( String.valueOf(groupId) );
                      int _jspx_eval_portlet_param_2 = _jspx_th_portlet_param_2.doStartTag();
                      if (_jspx_th_portlet_param_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_2);
                        return;
                      }
                      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_2);
                      out.write("\n\t\t\t\t\t\t\t");
                      //  portlet:param
                      com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_3 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                      _jspx_th_portlet_param_3.setPageContext(_jspx_page_context);
                      _jspx_th_portlet_param_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_0);
                      _jspx_th_portlet_param_3.setName("primarySearch");
                      _jspx_th_portlet_param_3.setValue( openSearch.getClassName() );
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
                      _jspx_th_portlet_param_4.setName("keywords");
                      _jspx_th_portlet_param_4.setValue( HtmlUtil.escape(searchDisplayContext.getKeywords()) );
                      int _jspx_eval_portlet_param_4 = _jspx_th_portlet_param_4.doStartTag();
                      if (_jspx_th_portlet_param_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_4);
                        return;
                      }
                      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_4);
                      out.write("\n\t\t\t\t\t\t\t");
                      //  portlet:param
                      com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_5 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                      _jspx_th_portlet_param_5.setPageContext(_jspx_page_context);
                      _jspx_th_portlet_param_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_0);
                      _jspx_th_portlet_param_5.setName("format");
                      _jspx_th_portlet_param_5.setValue( format );
                      int _jspx_eval_portlet_param_5 = _jspx_th_portlet_param_5.doStartTag();
                      if (_jspx_th_portlet_param_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_5);
                        return;
                      }
                      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_5);
                      out.write("\n\t\t\t\t\t\t");
                    }
                    if (_jspx_th_portlet_renderURL_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_portlet_renderURL_var.reuse(_jspx_th_portlet_renderURL_0);
                      return;
                    }
                    _jspx_tagPool_portlet_renderURL_var.reuse(_jspx_th_portlet_renderURL_0);
                    java.lang.String moreResultsURL = null;
                    moreResultsURL = (java.lang.String) _jspx_page_context.findAttribute("moreResultsURL");
                    out.write("\n\n\t\t\t\t\t\t");
                    //  aui:a
                    com.liferay.taglib.aui.ATag _jspx_th_aui_a_3 = (com.liferay.taglib.aui.ATag) _jspx_tagPool_aui_a_href.get(com.liferay.taglib.aui.ATag.class);
                    _jspx_th_aui_a_3.setPageContext(_jspx_page_context);
                    _jspx_th_aui_a_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_3);
                    _jspx_th_aui_a_3.setHref( moreResultsURL );
                    int _jspx_eval_aui_a_3 = _jspx_th_aui_a_3.doStartTag();
                    if (_jspx_eval_aui_a_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      out.write("\n\t\t\t\t\t\t\t");
                      //  liferay-ui:message
                      com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_14 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody.get(com.liferay.taglib.ui.MessageTag.class);
                      _jspx_th_liferay$1ui_message_14.setPageContext(_jspx_page_context);
                      _jspx_th_liferay$1ui_message_14.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_a_3);
                      _jspx_th_liferay$1ui_message_14.setArguments( ResourceActionsUtil.getModelResource(locale, openSearch.getClassName()) );
                      _jspx_th_liferay$1ui_message_14.setKey("more-x-results");
                      _jspx_th_liferay$1ui_message_14.setTranslateArguments( false );
                      int _jspx_eval_liferay$1ui_message_14 = _jspx_th_liferay$1ui_message_14.doStartTag();
                      if (_jspx_th_liferay$1ui_message_14.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody.reuse(_jspx_th_liferay$1ui_message_14);
                        return;
                      }
                      _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody.reuse(_jspx_th_liferay$1ui_message_14);
                      out.write(" &raquo;\n\t\t\t\t\t\t");
                    }
                    if (_jspx_th_aui_a_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_aui_a_href.reuse(_jspx_th_aui_a_3);
                      return;
                    }
                    _jspx_tagPool_aui_a_href.reuse(_jspx_th_aui_a_3);
                    out.write("\n\t\t\t\t\t</div>\n\t\t\t\t");
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
                out.write("\n\t\t");
              }
              if (_jspx_th_liferay$1ui_search$1container_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_liferay$1ui_search$1container_total_searchContainer.reuse(_jspx_th_liferay$1ui_search$1container_1);
                return;
              }
              _jspx_tagPool_liferay$1ui_search$1container_total_searchContainer.reuse(_jspx_th_liferay$1ui_search$1container_1);
              out.write('\n');
              out.write('	');
            }
            if (_jspx_th_c_if_13.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_13);
              return;
            }
            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_13);
            out.write('\n');
            out.write('\n');

}

            out.write('\n');
            out.write('\n');
            //  c:if
            com.liferay.taglib.core.IfTag _jspx_th_c_if_15 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
            _jspx_th_c_if_15.setPageContext(_jspx_page_context);
            _jspx_th_c_if_15.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_panel_0);
            _jspx_th_c_if_15.setTest( totalResults == 0 );
            int _jspx_eval_c_if_15 = _jspx_th_c_if_15.doStartTag();
            if (_jspx_eval_c_if_15 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\t<div class=\"no-results\">\n\t\t");
              //  liferay-ui:message
              com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_15 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody.get(com.liferay.taglib.ui.MessageTag.class);
              _jspx_th_liferay$1ui_message_15.setPageContext(_jspx_page_context);
              _jspx_th_liferay$1ui_message_15.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_15);
              _jspx_th_liferay$1ui_message_15.setArguments( "<strong>" + HtmlUtil.escape(searchDisplayContext.getKeywords()) + "</strong>" );
              _jspx_th_liferay$1ui_message_15.setKey("no-results-were-found-that-matched-the-keywords-x");
              _jspx_th_liferay$1ui_message_15.setTranslateArguments( false );
              int _jspx_eval_liferay$1ui_message_15 = _jspx_th_liferay$1ui_message_15.doStartTag();
              if (_jspx_th_liferay$1ui_message_15.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody.reuse(_jspx_th_liferay$1ui_message_15);
                return;
              }
              _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody.reuse(_jspx_th_liferay$1ui_message_15);
              out.write("\n\t</div>\n");
            }
            if (_jspx_th_c_if_15.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_15);
              return;
            }
            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_15);
            out.write('\n');
            out.write('\n');

String pageDescription = LanguageUtil.get(request, "search-results");

if (!modelResources.isEmpty()) {
	pageDescription = LanguageUtil.get(request, "searched") + StringPool.SPACE + StringUtil.merge(modelResources, StringPool.COMMA_AND_SPACE);
}

PortalUtil.setPageDescription(pageDescription, request);

            out.write("\n\t\t");
          }
          if (_jspx_th_liferay$1ui_panel_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_liferay$1ui_panel_title_persistState_id_extended_cssClass_collapsible.reuse(_jspx_th_liferay$1ui_panel_0);
            return;
          }
          _jspx_tagPool_liferay$1ui_panel_title_persistState_id_extended_cssClass_collapsible.reuse(_jspx_th_liferay$1ui_panel_0);
          out.write('\n');
          out.write('	');
        }
        if (_jspx_th_c_if_12.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_12);
          return;
        }
        _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_12);
        out.write('\n');
      }
      if (_jspx_th_aui_form_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_aui_form_onSubmit_name_method_action.reuse(_jspx_th_aui_form_0);
        return;
      }
      _jspx_tagPool_aui_form_onSubmit_name_method_action.reuse(_jspx_th_aui_form_0);
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
          out.write("\n\t$('#");
          if (_jspx_meth_portlet_namespace_5((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_3, _jspx_page_context))
            return;
          out.write("keywords').on(\n\t\t'keydown',\n\t\tfunction(event) {\n\t\t\tif (event.keyCode === 13) {\n\t\t\t\t");
          if (_jspx_meth_portlet_namespace_6((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_3, _jspx_page_context))
            return;
          out.write("search();\n\t\t\t}\n\t\t}\n\t);\n");
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
      out.write('\n');
      out.write('\n');
      //  aui:script
      com.liferay.taglib.aui.ScriptTag _jspx_th_aui_script_4 = (com.liferay.taglib.aui.ScriptTag) _jspx_tagPool_aui_script.get(com.liferay.taglib.aui.ScriptTag.class);
      _jspx_th_aui_script_4.setPageContext(_jspx_page_context);
      _jspx_th_aui_script_4.setParent(null);
      int _jspx_eval_aui_script_4 = _jspx_th_aui_script_4.doStartTag();
      if (_jspx_eval_aui_script_4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        if (_jspx_eval_aui_script_4 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
          out = _jspx_page_context.pushBody();
          _jspx_th_aui_script_4.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
          _jspx_th_aui_script_4.doInitBody();
        }
        do {
          out.write("\n\tfunction ");
          if (_jspx_meth_portlet_namespace_7((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_4, _jspx_page_context))
            return;
          out.write("addSearchProvider() {\n\t\t");
          //  portlet:resourceURL
          com.liferay.taglib.portlet.ResourceURLTag _jspx_th_portlet_resourceURL_0 = (com.liferay.taglib.portlet.ResourceURLTag) _jspx_tagPool_portlet_resourceURL_var.get(com.liferay.taglib.portlet.ResourceURLTag.class);
          _jspx_th_portlet_resourceURL_0.setPageContext(_jspx_page_context);
          _jspx_th_portlet_resourceURL_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_4);
          _jspx_th_portlet_resourceURL_0.setVar("openSearchDescriptionXMLURL");
          int _jspx_eval_portlet_resourceURL_0 = _jspx_th_portlet_resourceURL_0.doStartTag();
          if (_jspx_eval_portlet_resourceURL_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t\t");
            if (_jspx_meth_portlet_param_6((javax.servlet.jsp.tagext.JspTag) _jspx_th_portlet_resourceURL_0, _jspx_page_context))
              return;
            out.write("\n\t\t\t");
            //  portlet:param
            com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_7 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
            _jspx_th_portlet_param_7.setPageContext(_jspx_page_context);
            _jspx_th_portlet_param_7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_resourceURL_0);
            _jspx_th_portlet_param_7.setName("groupId");
            _jspx_th_portlet_param_7.setValue( String.valueOf(groupId) );
            int _jspx_eval_portlet_param_7 = _jspx_th_portlet_param_7.doStartTag();
            if (_jspx_th_portlet_param_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_7);
              return;
            }
            _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_7);
            out.write("\n\t\t");
          }
          if (_jspx_th_portlet_resourceURL_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_portlet_resourceURL_var.reuse(_jspx_th_portlet_resourceURL_0);
            return;
          }
          _jspx_tagPool_portlet_resourceURL_var.reuse(_jspx_th_portlet_resourceURL_0);
          java.lang.String openSearchDescriptionXMLURL = null;
          openSearchDescriptionXMLURL = (java.lang.String) _jspx_page_context.findAttribute("openSearchDescriptionXMLURL");
          out.write("\n\n\t\twindow.external.AddSearchProvider('");
          out.print( openSearchDescriptionXMLURL.toString() );
          out.write("');\n\t}\n\n\twindow.");
          if (_jspx_meth_portlet_namespace_8((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_4, _jspx_page_context))
            return;
          out.write("search = function() {\n\t\tvar form = AUI.$(document.");
          if (_jspx_meth_portlet_namespace_9((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_4, _jspx_page_context))
            return;
          out.write("fm);\n\n\t\tform.fm('");
          out.print( SearchContainer.DEFAULT_CUR_PARAM );
          out.write("').val(1);\n\n\t\tvar keywords = form.fm('keywords').val();\n\n\t\tkeywords = keywords.replace(/^\\s+|\\s+$/, '');\n\n\t\tif (keywords != '') {\n\t\t\tsubmitForm(form);\n\t\t}\n\t}\n");
          int evalDoAfterBody = _jspx_th_aui_script_4.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
        if (_jspx_eval_aui_script_4 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
          out = _jspx_page_context.popBody();
      }
      if (_jspx_th_aui_script_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_aui_script.reuse(_jspx_th_aui_script_4);
        return;
      }
      _jspx_tagPool_aui_script.reuse(_jspx_th_aui_script_4);
      out.write('\n');
      out.write('\n');

String pageSubtitle = LanguageUtil.get(request, "search-results");
String pageKeywords = LanguageUtil.get(request, "search");

if (Validator.isNotNull(searchDisplayContext.getKeywords())) {
	pageKeywords = searchDisplayContext.getKeywords();

	if (StringUtil.startsWith(pageKeywords, Field.ASSET_TAG_NAMES + StringPool.COLON)) {
		pageKeywords = StringUtil.replace(pageKeywords, Field.ASSET_TAG_NAMES + StringPool.COLON, StringPool.BLANK);
	}
}

PortalUtil.setPageSubtitle(pageSubtitle, request);
PortalUtil.setPageKeywords(pageKeywords, request);

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

  private boolean _jspx_meth_portlet_param_0(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1portlet_renderURL_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:param
    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_0 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
    _jspx_th_portlet_param_0.setPageContext(_jspx_page_context);
    _jspx_th_portlet_param_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_0);
    _jspx_th_portlet_param_0.setName("mvcPath");
    _jspx_th_portlet_param_0.setValue("/search.jsp");
    int _jspx_eval_portlet_param_0 = _jspx_th_portlet_param_0.doStartTag();
    if (_jspx_th_portlet_param_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_0);
      return true;
    }
    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_0);
    return false;
  }

  private boolean _jspx_meth_liferay$1portlet_renderURLParams_0(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_form_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-portlet:renderURLParams
    com.liferay.taglib.portlet.RenderURLParamsTag _jspx_th_liferay$1portlet_renderURLParams_0 = (com.liferay.taglib.portlet.RenderURLParamsTag) _jspx_tagPool_liferay$1portlet_renderURLParams_varImpl_nobody.get(com.liferay.taglib.portlet.RenderURLParamsTag.class);
    _jspx_th_liferay$1portlet_renderURLParams_0.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1portlet_renderURLParams_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
    _jspx_th_liferay$1portlet_renderURLParams_0.setVarImpl("searchURL");
    int _jspx_eval_liferay$1portlet_renderURLParams_0 = _jspx_th_liferay$1portlet_renderURLParams_0.doStartTag();
    if (_jspx_th_liferay$1portlet_renderURLParams_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1portlet_renderURLParams_varImpl_nobody.reuse(_jspx_th_liferay$1portlet_renderURLParams_0);
      return true;
    }
    _jspx_tagPool_liferay$1portlet_renderURLParams_varImpl_nobody.reuse(_jspx_th_liferay$1portlet_renderURLParams_0);
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

  private boolean _jspx_meth_liferay$1ui_message_0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_0 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_0.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_1);
    _jspx_th_liferay$1ui_message_0.setKey("did-you-mean");
    int _jspx_eval_liferay$1ui_message_0 = _jspx_th_liferay$1ui_message_0.doStartTag();
    if (_jspx_th_liferay$1ui_message_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_0);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_0);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_1(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_1 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_1.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_2);
    _jspx_th_liferay$1ui_message_1.setKey("related-queries");
    int _jspx_eval_liferay$1ui_message_1 = _jspx_th_liferay$1ui_message_1.doStartTag();
    if (_jspx_th_liferay$1ui_message_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_1);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_1);
    return false;
  }

  private boolean _jspx_meth_aui_script_1(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_col_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:script
    com.liferay.taglib.aui.ScriptTag _jspx_th_aui_script_1 = (com.liferay.taglib.aui.ScriptTag) _jspx_tagPool_aui_script.get(com.liferay.taglib.aui.ScriptTag.class);
    _jspx_th_aui_script_1.setPageContext(_jspx_page_context);
    _jspx_th_aui_script_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_col_1);
    int _jspx_eval_aui_script_1 = _jspx_th_aui_script_1.doStartTag();
    if (_jspx_eval_aui_script_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_aui_script_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_aui_script_1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_aui_script_1.doInitBody();
      }
      do {
        out.write("\n\tfunction ");
        if (_jspx_meth_portlet_namespace_2((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
          return true;
        out.write("searchKeywords(newKeywords) {\n\t\tvar form = AUI.$(document.");
        if (_jspx_meth_portlet_namespace_3((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
          return true;
        out.write("fm);\n\n\t\tform.fm('keywords').val(newKeywords);\n\n\t\tsubmitForm(form);\n\t}\n");
        int evalDoAfterBody = _jspx_th_aui_script_1.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_aui_script_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
        out = _jspx_page_context.popBody();
    }
    if (_jspx_th_aui_script_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_script.reuse(_jspx_th_aui_script_1);
      return true;
    }
    _jspx_tagPool_aui_script.reuse(_jspx_th_aui_script_1);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_2(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_2 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_2.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    int _jspx_eval_portlet_namespace_2 = _jspx_th_portlet_namespace_2.doStartTag();
    if (_jspx_th_portlet_namespace_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_2);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_2);
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

  private boolean _jspx_meth_liferay$1ui_message_3(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_10, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_3 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_3.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_10);
    _jspx_th_liferay$1ui_message_3.setKey("details");
    int _jspx_eval_liferay$1ui_message_3 = _jspx_th_liferay$1ui_message_3.doStartTag();
    if (_jspx_th_liferay$1ui_message_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_3);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_3);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_4(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_10, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_4 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_4.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_10);
    _jspx_th_liferay$1ui_message_4.setKey("key");
    int _jspx_eval_liferay$1ui_message_4 = _jspx_th_liferay$1ui_message_4.doStartTag();
    if (_jspx_th_liferay$1ui_message_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_4);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_4);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_5(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_10, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_5 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_5.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_10);
    _jspx_th_liferay$1ui_message_5.setKey("value");
    int _jspx_eval_liferay$1ui_message_5 = _jspx_th_liferay$1ui_message_5.doStartTag();
    if (_jspx_th_liferay$1ui_message_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_5);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_5);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_6(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_10, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_6 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_6.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_10);
    _jspx_th_liferay$1ui_message_6.setKey("array");
    int _jspx_eval_liferay$1ui_message_6 = _jspx_th_liferay$1ui_message_6.doStartTag();
    if (_jspx_th_liferay$1ui_message_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_6);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_6);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_7(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_10, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_7 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_7.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_10);
    _jspx_th_liferay$1ui_message_7.setKey("boost");
    int _jspx_eval_liferay$1ui_message_7 = _jspx_th_liferay$1ui_message_7.doStartTag();
    if (_jspx_th_liferay$1ui_message_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_7);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_7);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_8(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_10, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_8 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_8.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_8.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_10);
    _jspx_th_liferay$1ui_message_8.setKey("numeric");
    int _jspx_eval_liferay$1ui_message_8 = _jspx_th_liferay$1ui_message_8.doStartTag();
    if (_jspx_th_liferay$1ui_message_8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_8);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_8);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_9(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_10, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_9 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_9.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_9.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_10);
    _jspx_th_liferay$1ui_message_9.setKey("tokenized");
    int _jspx_eval_liferay$1ui_message_9 = _jspx_th_liferay$1ui_message_9.doStartTag();
    if (_jspx_th_liferay$1ui_message_9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_9);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_9);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_search$1iterator_0(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1ui_search$1container_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:search-iterator
    com.liferay.taglib.ui.SearchIteratorTag _jspx_th_liferay$1ui_search$1iterator_0 = (com.liferay.taglib.ui.SearchIteratorTag) _jspx_tagPool_liferay$1ui_search$1iterator_type_markupView_displayStyle_nobody.get(com.liferay.taglib.ui.SearchIteratorTag.class);
    _jspx_th_liferay$1ui_search$1iterator_0.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_search$1iterator_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container_0);
    _jspx_th_liferay$1ui_search$1iterator_0.setDisplayStyle("descriptive");
    _jspx_th_liferay$1ui_search$1iterator_0.setMarkupView("lexicon");
    _jspx_th_liferay$1ui_search$1iterator_0.setType("more");
    int _jspx_eval_liferay$1ui_search$1iterator_0 = _jspx_th_liferay$1ui_search$1iterator_0.doStartTag();
    if (_jspx_th_liferay$1ui_search$1iterator_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_search$1iterator_type_markupView_displayStyle_nobody.reuse(_jspx_th_liferay$1ui_search$1iterator_0);
      return true;
    }
    _jspx_tagPool_liferay$1ui_search$1iterator_type_markupView_displayStyle_nobody.reuse(_jspx_th_liferay$1ui_search$1iterator_0);
    return false;
  }

  private boolean _jspx_meth_aui_script_2(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_form_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:script
    com.liferay.taglib.aui.ScriptTag _jspx_th_aui_script_2 = (com.liferay.taglib.aui.ScriptTag) _jspx_tagPool_aui_script_use.get(com.liferay.taglib.aui.ScriptTag.class);
    _jspx_th_aui_script_2.setPageContext(_jspx_page_context);
    _jspx_th_aui_script_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
    _jspx_th_aui_script_2.setUse("aui-base");
    int _jspx_eval_aui_script_2 = _jspx_th_aui_script_2.doStartTag();
    if (_jspx_eval_aui_script_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_aui_script_2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_aui_script_2.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_aui_script_2.doInitBody();
      }
      do {
        out.write("\n\tA.one('#");
        if (_jspx_meth_portlet_namespace_4((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_2, _jspx_page_context))
          return true;
        out.write("search').delegate(\n\t\t'click',\n\t\tfunction(event) {\n\t\t\tvar currentTarget = event.currentTarget;\n\n\t\t\tcurrentTarget.siblings('.table-details').toggleClass('hide');\n\t\t},\n\t\t'.expand-details'\n\t);\n");
        int evalDoAfterBody = _jspx_th_aui_script_2.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_aui_script_2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
        out = _jspx_page_context.popBody();
    }
    if (_jspx_th_aui_script_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_script_use.reuse(_jspx_th_aui_script_2);
      return true;
    }
    _jspx_tagPool_aui_script_use.reuse(_jspx_th_aui_script_2);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_4(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_4 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_4.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_2);
    int _jspx_eval_portlet_namespace_4 = _jspx_th_portlet_namespace_4.doStartTag();
    if (_jspx_th_portlet_namespace_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_4);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_4);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_11(javax.servlet.jsp.tagext.JspTag _jspx_th_c_when_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_11 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_11.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_11.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_1);
    _jspx_th_liferay$1ui_message_11.setKey("no-widgets-were-searched");
    int _jspx_eval_liferay$1ui_message_11 = _jspx_th_liferay$1ui_message_11.doStartTag();
    if (_jspx_th_liferay$1ui_message_11.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_11);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_11);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_12(javax.servlet.jsp.tagext.JspTag _jspx_th_c_otherwise_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_12 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_12.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_12.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_1);
    _jspx_th_liferay$1ui_message_12.setKey("searched");
    int _jspx_eval_liferay$1ui_message_12 = _jspx_th_liferay$1ui_message_12.doStartTag();
    if (_jspx_th_liferay$1ui_message_12.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_12);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_12);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_13(javax.servlet.jsp.tagext.JspTag _jspx_th_c_when_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_13 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_13.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_13.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_2);
    _jspx_th_liferay$1ui_message_13.setKey("of-many");
    int _jspx_eval_liferay$1ui_message_13 = _jspx_th_liferay$1ui_message_13.doStartTag();
    if (_jspx_th_liferay$1ui_message_13.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_13);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_13);
    return false;
  }

  private boolean _jspx_meth_portlet_param_1(javax.servlet.jsp.tagext.JspTag _jspx_th_portlet_renderURL_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:param
    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_1 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
    _jspx_th_portlet_param_1.setPageContext(_jspx_page_context);
    _jspx_th_portlet_param_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_0);
    _jspx_th_portlet_param_1.setName("struts_action");
    _jspx_th_portlet_param_1.setValue("/search/search");
    int _jspx_eval_portlet_param_1 = _jspx_th_portlet_param_1.doStartTag();
    if (_jspx_th_portlet_param_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_1);
      return true;
    }
    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_1);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_5(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_5 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_5.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_3);
    int _jspx_eval_portlet_namespace_5 = _jspx_th_portlet_namespace_5.doStartTag();
    if (_jspx_th_portlet_namespace_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_5);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_5);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_6(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_6 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_6.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_3);
    int _jspx_eval_portlet_namespace_6 = _jspx_th_portlet_namespace_6.doStartTag();
    if (_jspx_th_portlet_namespace_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_6);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_6);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_7(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_7 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_7.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_4);
    int _jspx_eval_portlet_namespace_7 = _jspx_th_portlet_namespace_7.doStartTag();
    if (_jspx_th_portlet_namespace_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_7);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_7);
    return false;
  }

  private boolean _jspx_meth_portlet_param_6(javax.servlet.jsp.tagext.JspTag _jspx_th_portlet_resourceURL_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:param
    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_6 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
    _jspx_th_portlet_param_6.setPageContext(_jspx_page_context);
    _jspx_th_portlet_param_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_resourceURL_0);
    _jspx_th_portlet_param_6.setName("mvcPath");
    _jspx_th_portlet_param_6.setValue("/open_search_description.jsp");
    int _jspx_eval_portlet_param_6 = _jspx_th_portlet_param_6.doStartTag();
    if (_jspx_th_portlet_param_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_6);
      return true;
    }
    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_6);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_8(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_8 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_8.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_8.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_4);
    int _jspx_eval_portlet_namespace_8 = _jspx_th_portlet_namespace_8.doStartTag();
    if (_jspx_th_portlet_namespace_8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_8);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_8);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_9(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_9 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_9.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_9.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_4);
    int _jspx_eval_portlet_namespace_9 = _jspx_th_portlet_namespace_9.doStartTag();
    if (_jspx_th_portlet_namespace_9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_9);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_9);
    return false;
  }
}
